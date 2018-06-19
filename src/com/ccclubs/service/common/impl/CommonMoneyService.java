package com.ccclubs.service.common.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.action.app.official.util.CouponBuyUtil;
import com.ccclubs.config.CommonMessage;
import com.ccclubs.config.Constant;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.config.SYSTEM.GrowRecordType;
import com.ccclubs.config.SYSTEM.IntegralType;
import com.ccclubs.config.SYSTEM.RecordType;
import com.ccclubs.dao.ICsFreeHourDao;
import com.ccclubs.dao.ICsIntegralRuleDao;
import com.ccclubs.dao.ICsMemberDao;
import com.ccclubs.dao.ICsOrderDao;
import com.ccclubs.dao.ICsRechargeCardDao;
import com.ccclubs.dao.ICsRecordDao;
import com.ccclubs.dao.ICsRefundDao;
import com.ccclubs.dao.ICsUseRecordDao;
import com.ccclubs.entity.Invoice;
import com.ccclubs.exception.ErrorCode;
import com.ccclubs.exception.ErrorException;
import com.ccclubs.exception.MessageException;
import com.ccclubs.helper.LoginHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsCoin;
import com.ccclubs.model.CsCreditBill;
import com.ccclubs.model.CsCreditCard;
import com.ccclubs.model.CsFreeHour;
import com.ccclubs.model.CsGrowRecord;
import com.ccclubs.model.CsIntegralRecord;
import com.ccclubs.model.CsIntegralRule;
import com.ccclubs.model.CsInvoice;
import com.ccclubs.model.CsItem;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOperRecord;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOrderCluster;
import com.ccclubs.model.CsOrderLog;
import com.ccclubs.model.CsRechargeCard;
import com.ccclubs.model.CsRecord;
import com.ccclubs.model.CsRecordSubject;
import com.ccclubs.model.CsRecordType;
import com.ccclubs.model.CsTrouble;
import com.ccclubs.model.CsUseRecord;
import com.ccclubs.model.CsViolat;
import com.ccclubs.model.SrvHost;
import com.ccclubs.model.SrvLock;
import com.ccclubs.service.admin.ICsItemService;
import com.ccclubs.service.admin.ICsRechargeCardService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.LZ;

public class CommonMoneyService implements ICommonMoneyService {
	
	ICsMemberDao		csMemberDao;
	ICsRecordDao		csRecordDao;
	ICsIntegralRuleDao	csIntegralRuleDao;
	ICsFreeHourDao		csFreeHourDao;
	ICsUseRecordDao		csUseRecordDao;
	ICsOrderDao			csOrderDao;
	ICsRefundDao		csRefundDao;
	ICsRechargeCardDao	csRechargeCardDao;
	
	public static ICommonMoneyService getBean(){
		return $.getBean("commonMoneyService");
	}

	/**
	 * 修改会员帐户信息
	 * 
	 * @param memberId会员ID
	 * @param moneyType帐户类型
	 * @param amount改变额
	 * @param recordType
	 *            类型描述
	 * @param remark
	 *            备注信息
	 * @param order
	 *            关联订单
	 * @param relate
	 *            其它关联对象ID
	 * @throws MessageException 
	 */
	public CsRecord updateMoney(Long hostid,Long memberId, MoneyType moneyType, Double amount, RecordType recordType, String remark, Long order, Long relate,Class relateClass) throws MessageException {
		//根据RecordType取消费科目，没有则创建一个
		CsRecordSubject csRecordSubject = getRecordSubject(recordType);
		
		//根据消费科目的会员或运营商收入项 与 传入的金额 判定传入的数据是否是合法的
		if(csRecordSubject!=null){
			//amount大于0，则必须是会员收入项
			if((amount>0 && (!csRecordSubject.getCsrsIsUserIn() || csRecordSubject.getCsrsIsUserOut()))
			 ||(amount<0 && (!csRecordSubject.getCsrsIsUserOut() || csRecordSubject.getCsrsIsUserIn())))
					throw new MessageException(ErrorCode.MONEY_RECORD_TYPE_ERROR,1,"发生消费额与消费类型不匹配(额:"+amount+",会员收入:"+csRecordSubject.getCsrsIsUserIn()+",会员支出:"+csRecordSubject.getCsrsIsUserOut()+")");
			if((amount>0 && csRecordSubject.getCsrsIsOperIn())
			 ||(amount<0 && csRecordSubject.getCsrsIsOperOut()))
					throw new MessageException(ErrorCode.MONEY_RECORD_TYPE_ERROR,2,"发生消费额与消费类型不匹配(额:"+amount+",运营商收入:"+csRecordSubject.getCsrsIsUserIn()+",运营商支出:"+csRecordSubject.getCsrsIsUserOut()+")");
			if(csRecordSubject.getCsrsIsUserIn() && csRecordSubject.getCsrsIsUserOut())
				throw new MessageException(ErrorCode.MONEY_RECORD_TYPE_ERROR,3,"消费类型错误(同时为会员收入支出项)");
			if(csRecordSubject.getCsrsIsOperIn() && csRecordSubject.getCsrsIsOperOut())
				throw new MessageException(ErrorCode.MONEY_RECORD_TYPE_ERROR,4,"消费类型错误(同时为运营商收入支出项)");
		}
		
		
		/**************************保存会员消费记录**************************/
		CsMember member = csMemberDao.getCsMemberById(memberId);
		Double remain = $(amount);
		if (moneyType == MoneyType.Money) {// 保证金
			if($(member.getCsmMoney() + amount)<0 && amount<0)
				throw new MessageException(ErrorCode.MONEY_LESS,5,"会员帐户余额不足");
			csMemberDao.updateCsMember$NotNull(new CsMember().csmId(memberId).csmMoney(remain = $(member.getCsmMoney() + amount)));
		} else if (moneyType == MoneyType.Coupon) {// 现金券
			if($(member.getCsmCoupon() + amount)<0 && amount<0)
				throw new MessageException(ErrorCode.MONEY_LESS,6,"会员现金券余额不足");
			csMemberDao.updateCsMember$NotNull(new CsMember().csmId(memberId).csmCoupon(remain = $(member.getCsmCoupon() + amount)));
		}
		CsRecord csRecord = new CsRecord(
			hostid//城市 [非空]
			,remark//描述
		 	,member.getCsmId()//消费会员 [非空]
		 	,(short)moneyType.ordinal()//类型
		 	,csRecordSubject==null?0l:csRecordSubject.getCsrsId()//会计科目
		 	,0l//消费类型 [非空]
		 	,$(amount)//数量 [非空]
		 	,$(remain)//余额 [非空]			 	
		 	,new Date()//时间 [非空]
		 	,order//关联订单
		 	,relate//关联编号
		 	,relateClass==null?null:(relateClass.getSimpleName()+"@"+relate)//其它关联
		 	,LoginHelper.getLoginId() == null ? 0l : LoginHelper.getLoginId()//操作人 [非空]
		 ).save();
		
		
		/**************************保存地区收支记录**************************/
		if(csRecordSubject!=null && (csRecordSubject.getCsrsIsOperIn()||csRecordSubject.getCsrsIsOperOut())){
			if(member.getCsmNotRevenue()==null || member.getCsmNotRevenue().shortValue()==0){
				new CsOperRecord(
					hostid//城市 [非空]
				 	,remark//摘要 [非空]
				 	,(short)moneyType.ordinal()//金额类型 [非空]
				 	,csRecordSubject.getCsrsId()//科目类型 [非空]
				 	,csRecordSubject.getCsrsIsOperIn()?Math.abs(amount):null//收入 [非空]
				 	,csRecordSubject.getCsrsIsOperOut()?Math.abs(amount):null//支出 [非空]
				 	,csRecord.getCsrId()//关联消费记录 [非空]
				 	,memberId//关联会员
				 	,order//关联订单
				 	,relateClass==null?null:(relateClass.getSimpleName()+"@"+relate)//其它关联
				 	,new Date()//发生时间 [非空]
				 	,null
				 	,LoginHelper.getLoginId() == null ? 0l : LoginHelper.getLoginId()//添加人 [非空]
				 	,(short)1//状态 [非空]
				 ).save();
			}else{
				new CsOperRecord(
					hostid//城市 [非空]
				 	,"(不计营收)"+remark//摘要 [非空]
				 	,(short)moneyType.ordinal()//金额类型 [非空]
				 	,csRecordSubject.getCsrsId()//科目类型 [非空]
				 	,0d//收入 [非空]
				 	,csRecordSubject.getCsrsIsOperOut()?Math.abs(amount):null//支出 [非空]
				 	,csRecord.getCsrId()//关联消费记录 [非空]
				 	,memberId//关联会员
				 	,order//关联订单
				 	,relateClass==null?null:(relateClass.getSimpleName()+"@"+relate)//其它关联
				 	,new Date()//发生时间 [非空]
				 	,null
				 	,LoginHelper.getLoginId() == null ? 0l : LoginHelper.getLoginId()//添加人 [非空]
				 	,(short)1//状态 [非空]
				 ).save();
			}
		}
		
		return csRecord;
	}

	private CsRecordSubject getRecordSubject(RecordType recordType) {
		CsRecordSubject csRecordSubject = CsRecordSubject.where().csrsName(recordType.name()).get();
		if(csRecordSubject==null){
			csRecordSubject =new CsRecordSubject(
				recordType.name()//科目名称 [非空]
			 	,recordType.getIncome()//系统收入项 [非空]
			 	,recordType.getOperin()//商家收入项 [非空]
			 	,recordType.getOperout()//商家支出项 [非空]
			 	,recordType.getUserin()//会员收入项 [非空]
			 	,recordType.getUserout()//会员支出项 [非空]
			 	,recordType.getArtif()//人工操作项 [非空]
			 	,recordType.getOrder()//订单相关项 [非空]
			 	,false//是否开票项 [非空]
			 ).save();
		}
		return csRecordSubject;
	}
	public CsRecord updateMoney(Long memberId, MoneyType moneyType, Double amount, RecordType recordType, String remark, Long order,Long relate,Class relateClass)throws MessageException{
		return this.updateMoney(CsMember.get(memberId).getCsmHost(), memberId, moneyType, amount, recordType, remark, order, relate,relateClass);
	}
	
	
	/**
	 * 信用支付订单
	 * @param csoHost
	 * @param csmId
	 * @param payReal
	 * @param descript
	 * @param csoId
	 * @return 
	 */
	@Override
	public Long payCredit(Long hostid, Long memberid, Long creditCard,
			double payReal, String descript, Long orderid, Long violatid,Long troubleid) {
		if(payReal<=0d)return null;
		CsCreditBill csCreditBill = new CsCreditBill(
			hostid//所在城市
			,descript//账单描述 [非空]
		 	,memberid//所属会员 [非空]
		 	,creditCard//所属信用卡 [非空]
		 	,orderid//关联订单
		 	,violatid//关联违章
		 	,troubleid//关联事故
		 	,payReal//总欠账额 [非空]
		 	,0d//已付额 [非空]
		 	,null//还款时间
		 	,new Date()//修改时间 [非空]
		 	,new Date()//添加时间 [非空]
			,LoginHelper.getLoginId()
			,""
			,""
			,""
			,(short)0//同步状态
		 	,payReal>0?(short)0:(short)1//状态 [非空]
		 ).save();
		
		return csCreditBill.getCscbId();
	}

	
	/**
	 * 使用帐户余额还信用卡帐单（目前只支持保证金还帐单）
	 * @param creditBill
	 */
	public String repayCreditBillByAccount(final Long creditBill,final String remark) {
		final CsCreditBill csCreditBill = CsCreditBill.get(creditBill);
		if(csCreditBill.getCscbStatus().shortValue()!=0)
			return "只有状态为待还款的帐单才能代付";
		Double canMoney =  getUsableAmount(csCreditBill.getCscbMember());
		if(canMoney < csCreditBill.getCscbValue())
			return "帐户余额不足，不能代付";

		return CsCreditBill.execute(new Function(){
			@Override
			public <T> T execute(Object... arg0) {
		
				SrvLock.lock(SrvLock.LockType.订单事务锁, "订单"+csCreditBill.getCscbOrder()+"信用转余额代付");
				
				CsMember csMember = csCreditBill.get$cscbMember();		
				Double payCoupon = $(Math.min(csMember.getCsmCoupon(), csCreditBill.getCscbValue()));//现金券支付(不能支付里程费)
				if(payCoupon<0)payCoupon=0d;
				Double payMoney = $(csCreditBill.getCscbValue()-payCoupon);//余额支付数
				
				if(payCoupon>0){//现金券支付
					updateMoney(
						csCreditBill.getCscbHost(),
						csCreditBill.getCscbMember(), 
						ICommonMoneyService.MoneyType.Coupon, 
						-payCoupon,
						SYSTEM.RecordType.信用卡支付,
						"帐户现金券代付信用卡帐单("+remark+")",
						csCreditBill.getCscbOrder(), 
						csCreditBill.getCscbId(),
						CsCreditBill.class
					);
				}
				
				if(payMoney>0){//余额支付
					updateMoney(
						csCreditBill.getCscbHost(),
						csCreditBill.getCscbMember(), 
						ICommonMoneyService.MoneyType.Money, 
						-payMoney,
						SYSTEM.RecordType.信用卡支付,
						"帐户余额代付信用卡帐单("+remark+")",
						csCreditBill.getCscbOrder(), 
						csCreditBill.getCscbId(),
						CsCreditBill.class
					);
				}
				
				CsCreditBill updateCsCreditBill = new CsCreditBill(creditBill);
				updateCsCreditBill.cscbRemark("该帐单由用户帐户余额代付:"+remark);
				updateCsCreditBill.cscbStatus((short)3);
				if(csCreditBill.getCscbState().shortValue()==0)
					updateCsCreditBill.cscbState((short)2);
				else if(csCreditBill.getCscbState().shortValue()==1)
					updateCsCreditBill.cscbState((short)3);
				updateCsCreditBill.cscbUpdateTime(new Date());
				updateCsCreditBill.cscbEditor(LoginHelper.getLoginId());
				
				updateCsCreditBill.update();
				
				return (T) "帐户余额代付信用卡帐单成功";
			}
		});
	}
	
	
	/**
	 * 撤销信用卡帐单
	 * @param cscbId
	 * @param cscbRemark
	 * @return
	 */
	public String cancelCreditBill(final Long creditBill, final String remark){
		final CsCreditBill csCreditBill = CsCreditBill.get(creditBill);
		if(csCreditBill.getCscbStatus().shortValue()!=0)
			return "只有状态为待还款的帐单才能撤销";	
		
		return CsCreditBill.execute(new Function(){
			@Override
			public <T> T execute(Object... arg0) {
		
				SrvLock.lock(SrvLock.LockType.订单事务锁, "订单"+csCreditBill.getCscbOrder()+"信用帐单撤销");
				
				CsCreditBill updateCsCreditBill = new CsCreditBill(creditBill);
				updateCsCreditBill.cscbRemark("信用帐单撤销:"+remark);
				updateCsCreditBill.cscbStatus((short)2);
				if(csCreditBill.getCscbState().shortValue()==0)
					updateCsCreditBill.cscbState((short)2);
				else if(csCreditBill.getCscbState().shortValue()==1)
					updateCsCreditBill.cscbState((short)3);
				updateCsCreditBill.cscbUpdateTime(new Date());
				updateCsCreditBill.cscbEditor(LoginHelper.getLoginId());
				
				updateCsCreditBill.update();
				
				return (T) "信用帐单撤销成功";
			}
		});
	}
	
	
	/**
	 * 设置信用卡帐单为已付
	 * @param cscbId
	 * @param cscbPaySerial
	 * @param cscbRemark
	 * @param bManual
	 * @return
	 */
	public String setCreditBillPayed(final Long creditBill, final String paySerial,final String remark,final Boolean bManual){
		
		final CsCreditBill csCreditBill = CsCreditBill.get(creditBill);
		if(csCreditBill.getCscbStatus().shortValue()!=0)// && csCreditBill.getCscbStatus().shortValue()!=1
			return "只有状态为待还款的帐单才能已付处理";//和已还款
		
		return CsCreditBill.execute(new Function(){
			@Override
			public <T> T execute(Object... arg0) {
				
				SrvLock.lock(SrvLock.LockType.订单事务锁, "订单"+csCreditBill.getCscbOrder()+"信用还款");
				
				new CsOrderLog(//添加订单日志
					 	"订单信用还款"//操作项目 [非空]
					 	,csCreditBill.getCscbOrder()//所属订单 [非空]
					 	,$.or(LoginHelper.getLoginId(),0l)//操作人 [非空]
					 	,null
					 	,null
					 	,null
					 	,""//备注信息
					 	,new Date()//添加时间 [非空]
					 ).save();
				
				
				//先把钱加到会员帐户中
				CsRecordSubject inRecordSubject = getRecordSubject(SYSTEM.RecordType.信用卡入帐);
				if((CsRecord.where().csrRecordSubject(inRecordSubject.getCsrsId()).csrObject("CsCreditBill@"+csCreditBill.getCscbId()).get())==null){
					updateMoney(
						csCreditBill.getCscbHost(),
						csCreditBill.getCscbMember(), 
						ICommonMoneyService.MoneyType.Money, 
						csCreditBill.getCscbValue(),
						SYSTEM.RecordType.信用卡入帐,
						(bManual?"(客服处理)":"(系统处理)")+"信用卡帐单还款入帐("+remark+") 交易号："+paySerial,
						csCreditBill.getCscbOrder(),
						csCreditBill.getCscbId(),
						CsCreditBill.class
					);
				}
				
				//再把钱扣掉
				CsRecordSubject payRecordSubject = getRecordSubject(SYSTEM.RecordType.信用卡支付);
				if((CsRecord.where().csrRecordSubject(payRecordSubject.getCsrsId()).csrObject("CsCreditBill@"+csCreditBill.getCscbId()).get())==null){
					updateMoney(
						csCreditBill.getCscbHost(),
						csCreditBill.getCscbMember(), 
						ICommonMoneyService.MoneyType.Money, 
						-csCreditBill.getCscbValue(),
						SYSTEM.RecordType.信用卡支付,
						(bManual?"(客服处理)":"(系统处理)")+"信用卡帐单结算支付("+remark+") 交易号："+paySerial,
						csCreditBill.getCscbOrder(), 
						csCreditBill.getCscbId(),
						CsCreditBill.class
					);
				}
				
				CsCreditBill updateCsCreditBill = new CsCreditBill(creditBill);
				updateCsCreditBill.cscbRemark((bManual?"人工":"系统")+"信用卡帐单结算支付("+remark+") 交易号："+paySerial);
				updateCsCreditBill.cscbStatus((short)1);
				updateCsCreditBill.cscbUpdateTime(new Date());
				updateCsCreditBill.cscbRepayTime(new Date());
				updateCsCreditBill.cscbEditor(LoginHelper.getLoginId());
				updateCsCreditBill.cscbPayment(csCreditBill.getCscbValue());
				updateCsCreditBill.setCscbPaySerial(paySerial);
				
				/*
				if(csCreditBill.getCscbState().shortValue()==0)
					updateCsCreditBill.cscbState((short)2);
				else if(csCreditBill.getCscbState().shortValue()==1)
					updateCsCreditBill.cscbState((short)3);
				*/
				
				updateCsCreditBill.update();
				
				return (T) "设置信用卡帐单为已付操作成功";	
			}
		});
		
	}
	
	/**
	 * 设置信用卡帐单为坏帐
	 * @param cscbId
	 * @param cscbRemark
	 * @return
	 */
	public String setCreditBillBad(final Long creditBill, final String remark){
		final CsCreditBill csCreditBill = CsCreditBill.get(creditBill);
		if(csCreditBill.getCscbStatus().shortValue()!=0)
			return "只有状态为待还款的帐单才能设置为坏帐";		
		
		return CsCreditBill.execute(new Function(){
			@Override
			public <T> T execute(Object... arg0) {		
				SrvLock.lock(SrvLock.LockType.订单事务锁, "订单"+csCreditBill.getCscbOrder()+"信用帐单设为坏帐");
				
				CsCreditBill updateCsCreditBill = new CsCreditBill(creditBill);
				updateCsCreditBill.cscbRemark("信用帐单坏帐:"+remark);
				updateCsCreditBill.cscbStatus((short)4);
				if(csCreditBill.getCscbState().shortValue()==0)
					updateCsCreditBill.cscbState((short)2);
				else if(csCreditBill.getCscbState().shortValue()==1)
					updateCsCreditBill.cscbState((short)3);
				
				updateCsCreditBill.cscbUpdateTime(new Date());
				updateCsCreditBill.cscbEditor(LoginHelper.getLoginId());
				
				updateCsCreditBill.update();
				
				return (T) "设置信用帐单坏帐操作成功";
			}
		});
	}
	
	
	@Override
	public int executeRechangeCard(String csrcNumber, String csrcPassword, Long member,String remark) throws MessageException {
		CsMember csMember = csMemberDao.getCsMemberById(member);
		
		if (csMember == null) {
			// 用户不存在
			return 201;
		}
		/*if (csRechargeCard == null) {
			// 兑换现金券实例不存在
			return 202;
		}*/

		ICsRechargeCardService csRechargeCardService = $.GetSpringBean("csRechargeCardService");
		CsRechargeCard csRechargeCard = csRechargeCardService.getCsRechargeCard($.add(CsRechargeCard.F.csrcNumber, csrcNumber).add(CsRechargeCard.F.csrcPassword, csrcPassword));
		if (csRechargeCard == null) {
			// 卡号或密码错误
			return 104;
		}
		
		// 如果卡的所属人与当前登录会员账号不一致，充值失败 
		if (csRechargeCard.getCsrcMember()!=null) {
			if(csRechargeCard.getCsrcMember().longValue()!=member){
				return 106;
			}
		}
		
		if (csRechargeCard.getCsrcStatus() != 2) {
			// 只有出库的才能进行兑换
			return 203;
		}
		
		//充值卡已超出兑换有效期
		if(csRechargeCard.getCsrcUtil()!=null && csRechargeCard.getCsrcUtil().before(new Date())){
			return 205;
		}
		
		//唯一性判断
		if (csRechargeCard.get$csrcMethod() != null){
			if("REGISTER_CARD".equals(csRechargeCard.get$csrcMethod().getSpFlag())||csRechargeCard.get$csrcMethod().getSpFlag().indexOf("SOLE_CARD")>-1) {
				CsRechargeCard existRechargeCard = CsRechargeCard.where().csrcMethod(csRechargeCard.getCsrcMethod()).csrcMember(member).get();
				if (existRechargeCard != null) {//不能重复兑换
					return 204;
				}
			}
		} 

		// 开始兑换
		CsRechargeCard newCsRechargeCard = new CsRechargeCard();
		newCsRechargeCard.setCsrcId(csRechargeCard.getCsrcId());
		newCsRechargeCard.setCsrcHost(csRechargeCard.getCsrcHost());
		newCsRechargeCard.setCsrcMember(member);
		newCsRechargeCard.setCsrcStatus((short) 3);
		newCsRechargeCard.setCsrcUseTime(new Date());
		// 更新兑换现金券实例状态，会员，使用时间等。。
		csRechargeCardDao.updateCsRechargeCard$NotNull(newCsRechargeCard);
		final short status_normal = 1;			//默认正常状态
		SimpleDateFormat  sFmt =  new SimpleDateFormat("yyyy年MM月dd日");
		//红包
		if(csRechargeCard.getCsrcType() == 2){
			CsCoin csCoin = new CsCoin();
			csCoin.setCscHost(csRechargeCard.getCsrcHost());
			csCoin.setCscMember(member);
			csCoin.setCscCount(csRechargeCard.getCsrcDenomination());
			csCoin.setCscRemain(csRechargeCard.getCsrcDenomination());
			csCoin.setCscValidity(csRechargeCard.getCsrcValidity());	
			if(csRechargeCard.getCsrcValidity().shortValue()==0)
				csCoin.setCscEnd(csRechargeCard.getCsrcValidityUtil());
			else
				csCoin.setCscEnd(com.ccclubs.helper.SystemHelper.getValidUntil(csRechargeCard.getCsrcValidity()));
			csCoin.setCscEditor(member);
			csCoin.setCscUpdateTime(Calendar.getInstance().getTime());
			csCoin.setCscAddTime(Calendar.getInstance().getTime());
			csCoin.setCscRemark("您的红包将在"+sFmt.format(csCoin.getCscEnd())+"过期，赶紧去使用哦。");
			csCoin.setCscStatus((short)status_normal);	
			csCoin.setCscSerial(SystemHelper.getCoinSerial(csCoin));
			csCoin.save();
		}else if(csRechargeCard.getCsrcType() == 1){
			CsFreeHour freeHour = new CsFreeHour();
			freeHour.setCsfhHost(csRechargeCard.getCsrcHost());
			freeHour.setCsfhMember(member);
			freeHour.setCsfhCount(csRechargeCard.getCsrcDenomination());
			freeHour.setCsfhRemain(csRechargeCard.getCsrcDenomination());
			freeHour.setCsfhValidity(csRechargeCard.getCsrcValidity());
			if(csRechargeCard.getCsrcValidity().shortValue()==0)
				freeHour.setCsfhEnd(csRechargeCard.getCsrcValidityUtil());
			else
				freeHour.setCsfhEnd(com.ccclubs.helper.SystemHelper.getValidUntil(csRechargeCard.getCsrcValidity()));
			freeHour.setCsfhEditor(member);
			freeHour.setCsfhAddTime(Calendar.getInstance().getTime());
			freeHour.setCsfhUpdateTime(Calendar.getInstance().getTime());
			freeHour.setCsfhRemark("您的免费小时将在"+sFmt.format(com.ccclubs.helper.SystemHelper.getValidUntil(csRechargeCard.getCsrcValidity()))+"过期，赶紧去使用哦。");
			freeHour.setCsfhStatus(status_normal);
			freeHour.save();
		}else{
			updateMoney(csRechargeCard.getCsrcHost(),member, MoneyType.Coupon, csRechargeCard.getCsrcDenomination(), RecordType.兑换现金券, remark, null, csRechargeCard.getCsrcId(),CsRechargeCard.class);
		}
			
		return 200;
	}
	
	
	/**
	 * 完善资料送88红包
	 * @param member
	 */
	public void giveCoinByPerfectInfo(Long member){
		GiveCoinByPerfectInfo(member);
	}
	/**
	 * 静态方法  完善资料送88红包
	 * @param member
	 */
	public static void GiveCoinByPerfectInfo(final Long member){
		new Thread(){
			public void run(){
				String flag = "完善资料送红包";
				if(CsCoin.where().cscFlag(flag).cscMember(member).get()==null){
					CsMember csMember = CsMember.get(member);
					Calendar endCalendar = Calendar.getInstance();
					endCalendar.add(Calendar.MONTH, validity);
					String geveHosts = SYSTEM.getArgumentValue("完善资料送红包地区列表", "GiveCoinHosts","");
					if($.empty(geveHosts) || !$.idin(geveHosts, csMember.getCsmHost()))
						return;
					SrvHost srvHost = csMember.get$csmHost();
					Double giveValue = SYSTEM.getArgumentValue(srvHost.getShName()+"地区完善资料送红包", "GiveCoinByPerfectInfo"+srvHost.getShId(), 0d);
					if(giveValue<=0)
						return;
					
					$.trace(flag,csMember.getCsmName());
					
					CsCoin csCoin = new CsCoin();
					csCoin.setCscHost(csMember.getCsmHost());
					csCoin.setCscMember(member);
					csCoin.setCscCount(giveValue);
					csCoin.setCscFlag(flag);
					csCoin.setCscRemain(giveValue);
					csCoin.setCscValidity(validity);	
					csCoin.setCscEnd(endCalendar.getTime());
					csCoin.setCscEditor(member);
					csCoin.setCscSerial(SystemHelper.getCoinSerial(csCoin));
					csCoin.setCscUpdateTime(Calendar.getInstance().getTime());
					csCoin.setCscAddTime(Calendar.getInstance().getTime());
					csCoin.setCscRemark("您的红包将在"+$.date(endCalendar.getTime(), "yyyy年MM月dd日")+"过期，赶紧去使用哦。");
					csCoin.setCscStatus((short)1);
					csCoin.save();
				}
			}
		}.start();
		
	}
	

	public Double $(Double value) {
		return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 获取会员可用余额
	 * 
	 * @param csoPayMember
	 *            会员ID
	 * @return
	 */
	public Double getUsableAmount(Long memberid) {
		CsMember member = csMemberDao.getCsMemberById(memberid);
		
		//获取所有的未完成的订单占用的费用
		Double sumOrderPrice = CsOrder.getCsOrderEval("sum(cso_margin_need+cso_predict+cso_pay_need-cso_pay_coin)", $.add("csoPayMember",memberid).add("definex", "cso_credit_card is null and (cso_status=0||cso_status=1||cso_status=2||cso_status=5)"));
		sumOrderPrice = (sumOrderPrice==null?0d:sumOrderPrice);

		/**
		 * 订单簇冻结金额只包含（margin_need保证金）
		 */
		Double sumOrderClusterPrice = CsOrderCluster.getCsOrderClusterEval("sum(csoc_margin_need)", $.add("definex", " csoc_pay_member="+memberid+" and (csoc_status=0||csoc_status=1)"));
		sumOrderClusterPrice = (sumOrderClusterPrice==null?0d:sumOrderClusterPrice);
		
		Double canUserMoney = member.getCsmMoney() + member.getCsmCoupon() - sumOrderPrice - sumOrderClusterPrice;
		return new BigDecimal(canUserMoney).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 静态方法获取会员可用余额
	 * @param memberid
	 * @return
	 */
	public static Double GetAmount(Long memberid){
		return CommonMoneyService.getBean().getUsableAmount(memberid);
	}
	
	
	/**
	 * 获取会员可用信用额
	 * 
	 * @param csoPayMember
	 *            会员ID
	 * @return
	 */
	public Double getUsableCredit(Long memberid){
		//获取所有信用额度
		Double sumLimit = CsCreditCard.getCsCreditCardEval("sum(cscc_limit)", $.add(CsCreditCard.F.csccMember, memberid));
		sumLimit = (sumLimit==null?0d:sumLimit);
		//获取所有用了信用卡的未完成的订单
		Double sumOrderPrice = CsOrder.getCsOrderEval("sum(cso_margin_need+cso_predict+cso_pay_need-cso_pay_coin)", $.add("csoPayMember",memberid).add("definex", "cso_credit_card is not null and (cso_status=0||cso_status=1||cso_status=2||cso_status=5)"));
		sumOrderPrice = (sumOrderPrice==null?0d:sumOrderPrice);
		return new BigDecimal(sumLimit-sumOrderPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	

	/**
	 * 获取会员可用余额和现金券和信用值和红包
	 * 
	 * @param csoPayMember
	 *            会员ID
	 * @return
	 */
	public Double getUsableMoneyAndCouponAndCredit(Long memberid){
		Double moneyAndCoupon = getUsableAmount(memberid);
		Double canCredit = getUsableCredit(memberid);
		return new BigDecimal(moneyAndCoupon+canCredit).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	

	/**
	 * 根据积分规则标识获取积分规则，如果没有，则自动创建一个
	 * 
	 * @param name
	 *            名称
	 * @param flag
	 *            标识
	 * @param decipt
	 *            描述
	 * @return
	 */
	public CsIntegralRule getIntegralByFlag(String name, String flag, String decipt) {
		CsIntegralRule csIntegralRule = csIntegralRuleDao.getCsIntegralRule($.add("csirFlag", flag));
		if (csIntegralRule == null) {
			csIntegralRule = csIntegralRuleDao.saveCsIntegralRule(new CsIntegralRule().csirAddTime(new Date()).csirDepict(decipt).csirFlag(flag).csirIntegral(0d).csirStatus((short) 1).csirName(name).csirUpdateTime(new Date()));
		}
		return csIntegralRule;
	}
	public void updateIntegral(Long memberId, Double amount, IntegralType flag, String remark, Long relate){
		this.updateIntegral($.or(SYSTEM.getDefaultHost(),CsMember.get(memberId).getCsmHost()), memberId, amount, flag, remark, relate);
	}
	public void updateIntegralForWeixin(Long hostId,Long memberId, Double amount, IntegralType flag, String remark, Long relate){
		this.updateIntegral(hostId, memberId, amount, flag, remark, relate);
	}
	public void updateIntegral(Long hostid,Long memberId, Double amount, IntegralType recordType, String remark, Long relate) {
		CsMember member = csMemberDao.getCsMemberById(memberId);
		Double remain = $(amount);
		if($(member.getCsmIntegral() + amount)<0)
			throw new ErrorException("INTEGRAL_LESS", "会员积分不足", "会员积分不足");
		csMemberDao.updateCsMember$NotNull(new CsMember().csmId(memberId).csmIntegral(remain = $(member.getCsmIntegral() + amount)));
		new CsIntegralRecord(
				hostid//城市 [非空]
			 	,member.getCsmId()//消费会员 [非空]
			 	,recordType.name()//类型 [非空]
			 	,$(amount)//数量 [非空]
			 	,$(remain)//余额 [非空]
			 	,remark//描述
			 	,new Date()//时间 [非空]
			 	,null//关联订单
			 	,relate//关联编号
			 	,$.md5(hostid+""+member.getCsmId()+""+$(amount)+""+(relate==null?0:relate)+""+remark+$.date(new Date(), "yyyy-MM-dd HH"))//序列号
			 	,LoginHelper.getLoginId() == null ? 0l : LoginHelper.getLoginId()//操作人 [非空]
			 ).save();
	}
	public void updateIntegralByRule(Long hostid,Long memberId, Double arg, IntegralType recordType, String remark, Long relate) {
		CsIntegralRule csIntegralRule = this.getIntegralByFlag(recordType.name(), recordType.name(), remark);
		if (csIntegralRule.getCsirStatus().shortValue() != 1 || csIntegralRule.getCsirIntegral().doubleValue() == 0d){
			new ErrorException("INTEGRAL_RULE_ERROR", "未配置" + recordType.name() + "积分规则参数", "未配置" + recordType.name() + "积分规则参数");
			return;			
		}
		this.updateIntegral(hostid, memberId, $(arg * csIntegralRule.getCsirIntegral()), recordType, remark, relate);
	}
	public void updateIntegralByRule(Long memberId, Double arg, IntegralType flag, String remark, Long relate){
		this.updateIntegralByRule($.or(SYSTEM.getDefaultHost(),CsMember.get(memberId).getCsmHost()), memberId, arg, flag, remark, relate);
	}

	
	
	
	/**
	 * [系统]更新会员成长值，并自动保存成长值记录
	 * @param memberId 会员ID
	 * @param growRecordType 类型枚举
	 * @param relate 关联ID
	 * @param args 参考数值
	 */
	public void updateGrow(Long member, GrowRecordType growRecordType, Long relate, Number...args){
		if(growRecordType.equals(GrowRecordType.订单消费)){
			this.updateGrow(
				CsMember.get(member).getCsmHost(), 
				member, 
				(int)(10+Math.sqrt((Double)args[0])), 
				growRecordType.name(), 
				"订单消费，成长值+10+"+(int)Math.sqrt((Double)args[0]), 
				CsOrder.class,
				relate
			);
		}else if(growRecordType.equals(GrowRecordType.电动车租用)){
			this.updateGrow(
				CsMember.get(member).getCsmHost(), 
				member, 
				5, 
				growRecordType.name(), 
				"电动车租用，成长值+5",
				CsOrder.class,
				relate
			);
		}else if(growRecordType.equals(GrowRecordType.还车超时)){
			this.updateGrow(
				CsMember.get(member).getCsmHost(), 
				member, 
				-20, 
				growRecordType.name(), 
				"还车超时，成长值-20",
				CsOrder.class,
				relate
			);
		}else if(growRecordType.equals(GrowRecordType.交通事故)){
			/**每1000元扣10分*/
			Double money = (Double) args[0];
			Integer grow = ((money.intValue()/1000)+1)*10;
			this.updateGrow(
				CsMember.get(member).getCsmHost(), 
				member, 
				-grow,
				growRecordType.name(), 
				"交通事故，成长值-"+grow,
				CsTrouble.class,
				relate
			);
		}else if(growRecordType.equals(GrowRecordType.违章处理超时)){
			Integer grow = 20;
			this.updateGrow(
				CsMember.get(member).getCsmHost(), 
				member, 
				-grow,
				growRecordType.name(), 
				"违章处理超时，成长值-"+grow,
				CsViolat.class,
				relate
			);
		}
	};
	
	
	private void updateGrow(Long hostid,Long memberId, Integer amount, String growTypeName, String desc,Class relateClass, Long relate) {
		CsMember member = CsMember.get(memberId);
		Integer grow = amount;
		Double rebate = 1.0d;
		//更新会员的成长值
		csMemberDao.updateCsMember$NotNull(new CsMember().csmId(memberId).csmGrow(grow=($.or(member.getCsmGrow(),0) + amount)));
		//添加成长记录
		new CsGrowRecord(
			hostid//城市 [非空]
		 	,member.getCsmId()//消费会员 [非空]
		 	,desc//描述 [非空]
		 	,growTypeName//类型 [非空]
		 	,amount//变化值 [非空]
		 	,relateClass.getSimpleName()+"@"+relate//关联数据
		 	,new Date()//修改时间 [非空]
		 	,new Date()//添加时间 [非空]
		 	,(short)1//状态 [非空]
		 ).save();
		//用户等级
		Integer grade = 0;
		//根据成长值计算出等级和折扣
		if(grow>5000){
			grade = 4;
			rebate = 8.5d;
		}else if(grow>2000){
			grade = 3;
			rebate = 9.0d;
		}else if(grow>500){
			grade = 2;
			rebate = 9.5d;
		}else if(grow>0){
			grade = 1;
			rebate = 1.0d;
		}else{
			grade = 0;
			rebate = 1.0d;
		}
		//更新会员的等级和折扣
		new CsMember(memberId)
		//折扣暂时不更新.csmRebate(rebate)
		.csmGrade(grade)
		.update();		
	}
	
	
	
	/**
	 * 获取会员在某个城市的红包
	 * @param host
	 * @param memberid
	 * @param date
	 * @return
	 */
	public Double getCoin(Long host,Long memberid,Date date) {
		Double remain = CsCoin.getCsCoinEval("sum(csc_remain)", 
				$.add(CsCoin.F.cscHost, host)
				.add(CsCoin.F.cscMember, memberid)
				.add(CsCoin.F.cscStatus, 1)// 有效的
				//.add(CsCoin.F.cscEndStart, date)// 未过期的
				
				.add("definex", "csc_remain>0 and '"+$.date(date, "yyyy-MM-dd")+"'<=csc_end")
				.add("asc", "csc_end"));
		return new BigDecimal($.or(remain, 0d)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	
	/**
	 * 获取会员可用的红包
	 * @param csoPayMember
	 * @param csoStartTime
	 * @return
	 */
	public List<CsCoin> getCanUseCoinList(Long host,Long memberid, Date date){
		return CsCoin.list(
			$.add(CsCoin.F.cscHost, host)
			.add(CsCoin.F.cscMember, memberid)
			.add(CsCoin.F.cscStatus, 1)// 有效的
			.add(CsCoin.F.cscEndStart, date)// 未过期的
			.add("definex", "csc_remain>0")
			.add("asc", "csc_end")
			, -1);
	}
	
	
	
	
	
	/**
	 * 获取会员所有的免费小时
	 * 
	 * @param memberid
	 *            会员ID
	 * @param date
	 *            时间点:理论上应该是当前时间，实际上有可能是某个订单的开始时间
	 * @return
	 */
	public Double getCanUseFreeHour(Long memberid, Date date) {
		List<CsFreeHour> freehours = getCanUseFreeHourList(memberid, date);
		Double freehour = 0d;
		for (CsFreeHour csFreeHour : freehours)
			freehour += csFreeHour.getCsfhRemain();

		return new BigDecimal(freehour).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 获取会员所有的免费小时列表
	 * 
	 * @param memberid
	 * @param date
	 *            时间点:理论上应该是当前时间，实际上有可能是某个订单的开始时间
	 * @return
	 */
	public List<CsFreeHour> getCanUseFreeHourList(Long memberid, Date date) {
		return csFreeHourDao.getCsFreeHourList($.add("csfhMember", memberid)// 所属会员
				.add("csfhStatus", 1)// 有效的
				.add(CsFreeHour.F.csfhEndStart, date)// 未过期的
				.add("definex", "csfh_remain>0").add("asc", "csfh_end"), -1);
	}

	/**
	 * 获取被某个订单冻结的免费小时数
	 * 
	 * @param orderid
	 *            订单ID
	 * @return
	 */
	public Double getFreezeFreeHour(Long orderid) {
		List<CsUseRecord> freezes = csUseRecordDao.getCsUseRecordList($.add("csurOrder", orderid).add("definex", "csur_free_hour is not null"), -1);
		Double freezeFreeHours = 0d;
		for (CsUseRecord csUseRecord : freezes)
			freezeFreeHours += csUseRecord.getCsurCount();
		return freezeFreeHours;
	}

	/**
	 * 获取被某个订单冻结的优惠项数
	 * 
	 * @param orderid
	 *            订单ID
	 * @param giftid
	 *            优惠项ID
	 * @return
	 */
	public Double getFreezeGift(Long orderid, Long giftid) {
		return $.or((Double)csUseRecordDao.getCsUseRecordEval("(sum(csur_count))", $.add("csurOrder", orderid).add("csurGift", giftid)),0d);
		//CsUseRecord csUseRecord = csUseRecordDao.getCsUseRecord($.add("csurOrder", orderid).add("csurGift", giftid));
		//return csUseRecord == null ? 0d : csUseRecord.getCsurCount();
	}

	/**
	 * 查看会员是否有未完成的订单
	 */
	public Boolean haveUnFinishOrder(Long memberid) {
		return 0 < csOrderDao.getCsOrderCount($.add("csoPayMember", memberid).add("definex", "(cso_status=0||cso_status=1||cso_status=2||cso_status=5)"));
	}

	/**
	 * 查看会员是否正在退款
	 * 
	 * @param memberid
	 * @return
	 */
	public Boolean isRefunding(Long memberid) {
		return 0 < csRefundDao.getCsRefundCount($.add("csrMember", memberid).add("definex", "(csr_status=0||csr_status=1)"));
	}

		
	/**
	 * 获取会员发票信息
	 * @param memberid
	 * @return
	 */
	public Invoice getInvoiceInfo(Long memberid) {

		Invoice invoice = new Invoice();
		invoice.setMember(memberid);
		
		//总消费额 
		Map<String, Object> leftJoinMap = new HashMap<String, Object>();
		leftJoinMap.put(CsRecordType.F.csrtMoneyType, 0);
		leftJoinMap.put(CsRecordType.F.csrtCanInvoice, true);
		Double consume = CsRecord.getCsRecordEval("sum(csr_amount)",
			$.add(CsRecord.F.csrMember, memberid)
			.add("CsRecordType", leftJoinMap)
			.add(CsRecord.F.csrAmountMax,0)
		);
		invoice.setConsume(Math.abs($.or(consume,0d)));
		
		//已开票额
		Double already = CsInvoice.getCsInvoiceEval("sum(csi_money)", 
			$.add(CsInvoice.F.csiMember, memberid)
			.add(CsInvoice.F.csiStatus, 2)
		);
		invoice.setAlready($.or(already,0d));
		
		//可开票额
		invoice.setViable(invoice.getConsume()-invoice.getAlready());
		
		return invoice;
	}
	
	@Override
	public int chargeCoupon(Long memberId,  CsItem item, int count) throws MessageException{
		final ICsItemService csItemService = $.GetSpringBean("csItemService");
		//处理现金券购买
		if(item.getCsiType().intValue() == 1){
			CouponBuyUtil couponBuy = new CouponBuyUtil();
			
			couponBuy = couponBuy.calculate(item, count, memberId);
			
			CsMember member = CsMember.get(memberId);
			final double money  = couponBuy.getNeedPayTotalFee();
			final double coupon = couponBuy.getCouponAmount();
			final double sendCoin   = couponBuy.getCoinAmount(); 
			
			String remark = CommonMessage.MSG0307.replace("{money}", String.valueOf(LZ.digit(money, 2))).replace("{coupon}", String.valueOf(LZ.digit(coupon, 2)));
			
			// 加现金券
			CsRecord chargeCouponRecord = updateMoney(memberId, MoneyType.Coupon, coupon, RecordType.现金券充值, remark, null, item.getCsiId(), CsItem.class);
			
			// 减充值帐户
			updateMoney(memberId, MoneyType.Money, money * -1.00, RecordType.购买现金券, remark, null, item.getCsiId(), CsItem.class);
			
			if(sendCoin > 0d){
				Short validity 	= 6;
				String serial   = memberId +"-"+ item.getCsiId() +"-"+ chargeCouponRecord.getCsrId();
				
				CsCoin csCoin 	= new CsCoin(member.getCsmHost(), memberId, sendCoin, sendCoin, validity, CsItem.class.getSimpleName()+"@"+item.getCsiId(), SystemHelper.getValidUntil(validity), serial, memberId, new Date(), new Date(), remark, (short)1, 1L);
				csCoin = csCoin.save();
			}
			
			//更新宝贝已售数
			item.setCsiSale(item.getCsiSale() == null ? 0 : item.getCsiSale()+count); 
			csItemService.updateCsItem(item);
		}
		return 0;
	}

	public ICsMemberDao getCsMemberDao() {
		return csMemberDao;
	}

	public void setCsMemberDao(ICsMemberDao csMemberDao) {
		this.csMemberDao = csMemberDao;
	}

	public ICsRecordDao getCsRecordDao() {
		return csRecordDao;
	}

	public void setCsRecordDao(ICsRecordDao csRecordDao) {
		this.csRecordDao = csRecordDao;
	}

	public ICsIntegralRuleDao getCsIntegralRuleDao() {
		return csIntegralRuleDao;
	}

	public void setCsIntegralRuleDao(ICsIntegralRuleDao csIntegralRuleDao) {
		this.csIntegralRuleDao = csIntegralRuleDao;
	}

	public ICsFreeHourDao getCsFreeHourDao() {
		return csFreeHourDao;
	}

	public void setCsFreeHourDao(ICsFreeHourDao csFreeHourDao) {
		this.csFreeHourDao = csFreeHourDao;
	}

	public ICsUseRecordDao getCsUseRecordDao() {
		return csUseRecordDao;
	}

	public void setCsUseRecordDao(ICsUseRecordDao csUseRecordDao) {
		this.csUseRecordDao = csUseRecordDao;
	}

	public ICsOrderDao getCsOrderDao() {
		return csOrderDao;
	}

	public void setCsOrderDao(ICsOrderDao csOrderDao) {
		this.csOrderDao = csOrderDao;
	}

	public ICsRefundDao getCsRefundDao() {
		return csRefundDao;
	}

	public void setCsRefundDao(ICsRefundDao csRefundDao) {
		this.csRefundDao = csRefundDao;
	}

	public ICsRechargeCardDao getCsRechargeCardDao() {
		return csRechargeCardDao;
	}

	public void setCsRechargeCardDao(ICsRechargeCardDao csRechargeCardDao) {
		this.csRechargeCardDao = csRechargeCardDao;
	}
	
	
}
