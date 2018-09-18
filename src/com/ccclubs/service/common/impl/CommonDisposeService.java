package com.ccclubs.service.common.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.ccclubs.action.app.official.meal.MealExpress;
import com.ccclubs.action.app.official.meal.MealHelper;
import com.ccclubs.action.weixin.WeixinHelper;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.config.SYSTEM.GrowRecordType;
import com.ccclubs.config.SYSTEM.IntegralType;
import com.ccclubs.dao.ICsCarDao;
import com.ccclubs.dao.ICsCoinDao;
import com.ccclubs.dao.ICsFreeHourDao;
import com.ccclubs.dao.ICsGiftDao;
import com.ccclubs.dao.ICsMemberDao;
import com.ccclubs.dao.ICsOrderDao;
import com.ccclubs.dao.ICsOrderDetailDao;
import com.ccclubs.dao.ICsUseRecordDao;
import com.ccclubs.exception.ErrorCode;
import com.ccclubs.exception.ErrorException;
import com.ccclubs.exception.MessageException;
import com.ccclubs.helper.LoginHelper;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.helper.WeixinHelper.WxTemplateMsgType;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsCoin;
import com.ccclubs.model.CsCreditBill;
import com.ccclubs.model.CsCreditCard;
import com.ccclubs.model.CsFeeTypeSet;
import com.ccclubs.model.CsFreeHour;
import com.ccclubs.model.CsGift;
import com.ccclubs.model.CsItem;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOrderCluster;
import com.ccclubs.model.CsOrderDetail;
import com.ccclubs.model.CsOrderLog;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsProduct;
import com.ccclubs.model.CsUnitOrder;
import com.ccclubs.model.CsUseRecord;
import com.ccclubs.model.SrvHost;
import com.ccclubs.model.SrvLock;
import com.ccclubs.model.TbAbAffirm;
import com.ccclubs.param.FeeTypeUitl;
import com.ccclubs.param.TimeSlot;
import com.ccclubs.service.admin.ICsArgumentService;
import com.ccclubs.service.admin.ICsCreditBillService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.common.From;
import com.ccclubs.service.common.ICommonDisposeService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonOrderService;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.ccclubs.service.common.script.Package2016GiftLimit;
import com.ccclubs.util.DateUtil;
import com.lazy3q.web.helper.$;

public class CommonDisposeService implements ICommonDisposeService {

	ICsOrderDao csOrderDao;
	ICsFreeHourDao csFreeHourDao;
	ICsUseRecordDao csUseRecordDao;
	ICsGiftDao csGiftDao;
	ICsCarDao csCarDao;
	ICsMemberDao csMemberDao;
	ICsOrderDetailDao csOrderDetailDao;
	ICommonOrderService commonOrderService;
	ICommonMoneyService commonMoneyService;
	ICsArgumentService csArgumentService;
	
	public static ICommonDisposeService getBean() {
		return $.getBean("commonDisposeService");
	}

	/**
	 * 生成订单
	 * 
	 * @param payMemberId
	 *            支付会员ID
	 * @param useMemberId
	 *            使用会员ID
	 * @param carId
	 *            预订车辆ID
	 * @param start
	 *            开始时间
	 * @param finish
	 *            结束时间
	 * @param feeType
	 *            计费类型
	 * @param insureType
	 *            保险类型
	 * @param longPrice
	 *            长租价格
	 * @param freeHours
	 *            免费小时
	 * @param defineMargin
	 *            保证金
	 * @param editor
	 *            添加人
	 * @param from
	 *            订单来源
	 * @return
	 */
	public CsOrder executeSaveOrder(Long payMemberId, Long useMemberId, Long creditCard, Long carId, Date start, Date finish, Long feeType, Long insureType,
			Double longPrice, Double freeHours, Double defineMargin, Long editor, From from, String src) throws MessageException {
		return this.saveOrder(payMemberId, useMemberId, null, creditCard, carId, null, null, start, finish, feeType, insureType, longPrice, freeHours,
				defineMargin, editor, from, src);
	}

	public CsOrder executeSaveOrder(Long payMemberId, Long useMemberId, String mobile, Long carId, Date start, Date finish, Long feeType, Long insureType,
			Double longPrice, Double freeHours, Double defineMargin, Long editor, From from, String src) throws MessageException {
		return this.saveOrder(payMemberId, useMemberId, mobile, null, carId, null, null, start, finish, feeType, insureType, longPrice, freeHours,
				defineMargin, editor, from, src);
	}

	public CsOrder executeSaveOrder(Long payMemberId, Long useMemberId, Long carId, Date start, Date finish, Long feeType, Long insureType, Double longPrice,
			Double freeHours, Double defineMargin, Long editor, From from, String src) throws MessageException {
		return this.saveOrder(payMemberId, useMemberId, null, null, carId, null, null, start, finish, feeType, insureType, longPrice, freeHours, defineMargin,
				editor, from, src);
	}

	public CsOrder executeSaveOrder(Long payMemberId, Long useMemberId, Long carId, Long outlets_get, Long outlets_ret, Date start, Date finish, Long feeType,
			Long insureType, Double longPrice, Double freeHours, Double defineMargin, Long editor, From from, String src) throws MessageException {
		return this.saveOrder(payMemberId, useMemberId, null, null, carId, outlets_get, outlets_ret, start, finish, feeType, insureType, longPrice, freeHours,
				defineMargin, editor, from, src);
	}

	public CsOrder executeSaveOrder(Long payMemberId, Long useMemberId, Long creditCard, Long carId, Long outlets_get, Long outlets_ret, Date start,
			Date finish, Long feeType, Long insureType, Double longPrice, Double freeHours, Double defineMargin, Long editor, From from, String src)
			throws MessageException {
		return this.saveOrder(payMemberId, useMemberId, null, creditCard, carId, outlets_get, outlets_ret, start, finish, feeType, insureType, longPrice,
				freeHours, defineMargin, editor, from, src);
	}

	public synchronized CsOrder saveOrder(Long payMemberId, Long useMemberId, String mobile, Long creditCard, Long carId, Long outlets_get_id,
			Long outlets_ret_id, Date start, Date finish, Long feeType, Long insureType, Double longPrice, Double freeHours, Double defineMargin, Long editor,
			From from, String src) throws MessageException {

		SrvLock.lock(SrvLock.LockType.订单事务锁, "订单添加");

		CsOrderLog csOrderLog = new CsOrderLog(// 添加订单日志
				"订单添加"// 操作项目 [非空]
				, 0l// 所属订单 [非空]
				, $.or(LoginHelper.getLoginId(), 0l)// 操作人 [非空]
				, LoginHelper.getLoginId() != null ? null : useMemberId, (short) from.ordinal(), src, $.getString("csOrder.csoRemark")// 备注信息
				, new Date()// 添加时间 [非空]
		).save();

		start = resetSecond(start);
		finish = resetSecond(finish);

		CsCar csoCar = csCarDao.getCsCarById(carId);

		//判断车辆是否已被租用
		Long carCount=csOrderDao.getCsOrderCount($.add("csoCar", csoCar.getCscId()).add("definex", " cso_status in (0,1)"));
		if(carCount!=null&&carCount>0) {
			throw new MessageException(ErrorCode.ORDER_COUNT_LIMIT, "车辆已被租用，请更换车辆");
		}
		
		//
		//判断会员是否已存在订单（已预订、使用中）
		Long count=csOrderDao.getCsOrderCount($.add("csoUseMember", useMemberId).add("definex", " cso_status in (0,1)"));
		if(count!=null&&count>0) {
			throw new MessageException(ErrorCode.ORDER_COUNT_LIMIT, "每个会员仅允许租用一辆车");
		}
		
		if (payMemberId == null)
			payMemberId = useMemberId;

		if (commonMoneyService.isRefunding(payMemberId))
			throw new MessageException(ErrorCode.ORDER_NO_REFUND, "当前支付会员正在退款，不能下单");

		CsOrder csOrder = new CsOrder();
		csOrder.setCsoPayMember(payMemberId);
		csOrder.setCsoUseMember(useMemberId);
		csOrder.setCsoCreditCard(creditCard);
		// csOrder.setCsoGroup((csOrder.get$csoPayMember()).getCsmGroup());
		csOrder.setCsoCar(carId);
		csOrder.setCsoStartTime(start);
		csOrder.setCsoFinishTime(finish);
		csOrder.setCsoInsureType(insureType);
		csOrder.setCsoFreehour(freeHours);
		csOrder.setCsoArea((csoCar.get$cscOutlets()).getCsoArea());
		csOrder.setCsoDeviceVesion(csoCar.getCscDeviceVesion());
		csOrder.setCsoHost(csoCar.getCscHost());
		csOrder.setCsoUseIndex(CsOrder.where().csoUseMember(useMemberId).csoStatus((short) 4).count().intValue());
		Short notRevenue = $.or(csOrder.get$csoPayMember().getCsmNotRevenue(), (short) 0);
		csOrder.setCsoIncome((short) (notRevenue.shortValue() == 0 ? 1 : 0));
		csOrder.setCsoOutlets($.or(outlets_get_id, csoCar.getCscOutlets()));// 取车网点
		csOrder.setCsoOutletsRet($.or(outlets_ret_id, csoCar.getCscOutlets()));// 还车网点

		/***************************** 分析取还车网点 ********************************/
		String schedulingError = SchedulingService.checkGetRetOutlets(carId, csOrder.getCsoOutlets(), csOrder.getCsoOutletsRet(), start, finish);
		if (!$.empty(schedulingError))
			throw new MessageException(ErrorCode.ORDER_A_B_OUTLETS_ERROR, schedulingError);
		/**************************** 分析取还车网点 *********************************/

		csOrder.setCsoModel(csoCar.getCscModel());
		csOrder.setCsoMask(0l);
		csOrder.setCsoCarNumber((csOrder.get$csoCar()).getCscNumber());// 车牌号
		csOrder.setCsoMobile(csOrder.get$csoUseMember().getCsmMobile());// 手机号码
		if (!$.empty(mobile))
			csOrder.setCsoMobile(mobile);
		if ((csOrder.get$csoUseMember()).getCsmVDrive().shortValue() != 1)
			throw new MessageException(ErrorCode.ORDER_MEMBERINFO_ERROR, "该会员资料未审核通过，不能下单");
		if (csOrder.get$csoUseMember().getCsmStatus().shortValue() != 1)
			throw new MessageException(ErrorCode.ORDER_MEMBER_DISABLE, "该会员帐号已被禁用，不能下单");
		if ((csOrder.get$csoUseMember()).get$csmEvcard() == null)
			throw new MessageException(ErrorCode.ORDER_NO_EV_CARD, "没有绑定EV卡，不能下单");
		else
			csOrder.setCsoEvRfid(((csOrder.get$csoUseMember()).get$csmEvcard()).getCsecRfid());// ev卡号
		csOrder.setCsoProvid((csOrder.get$csoCar()).getCscProvid());// 所属商家

		csOrder.setCsoIsAdCar(csoCar.getCscIsAd());// 保存车辆是否宣传车
		csOrder.setCsoRebate(csoCar.getCscRebate());// 保存车辆的折扣状态

		// 设置默认值
		csOrder.setCsoFuel(0d);
		csOrder.setCsoElectric(0d);
		csOrder.setCsoPayReal(0d);

		csOrder.setCsoFrom((short) from.ordinal());
		csOrder.setCsoUpdateTime(new Date());
		csOrder.setCsoAddTime(new Date());
		csOrder.setCsoCode($.zerofill($.rand(999999), 6).replaceAll("0", "1"));
		csOrder.setCsoEditor(editor);
		csOrder.setCsoAlarm((short) 0);
		csOrder.setCsoState((short) 0);
		csOrder.setCsoStatus((short) 0);

		// 订单序列号=会员ID+车辆ID+订单开始时间
		csOrder.setCsoSerial(csOrder.getCsoUseMember() + "-" + csOrder.getCsoCar() + "-" + csOrder.getCsoStartTime().getTime());

		csOrder.setCsoLongPrice(longPrice);

//		if (commonOrderService.isExistOrderByTime(carId, start, finish, null))
//			throw new MessageException(ErrorCode.ORDER_CONFLICT, "当前订单时间已经被其它订单占用");

		// 获取小时数
		int allhours = (int) ((finish.getTime() - start.getTime()) / SYSTEM.HOUR);
		$.setLocal("defineactual", commonOrderService.getPredictKms(allhours) * 1);

		// 拆分订单
		OrderInfo orderinfo = commonOrderService.splitOrderDetails(payMemberId, carId, start, finish, null, freeHours, null, null, feeType, insureType,
				longPrice, null);

		// 计费类型
		csOrder.setCsoFeeType(orderinfo.feetype);

		// 设置订单需付额
		csOrder.setCsoPayNeed(orderinfo.getPrice());
		// 设置订单需保证金额
		csOrder.setCsoMarginNeed(orderinfo.margin);
		if (defineMargin != null && defineMargin > 0)
			csOrder.setCsoMarginNeed(defineMargin);
		// 设置订单预计里程费
		csOrder.setCsoPredict(orderinfo.feeee);

		// 订单时长
		csOrder.setCsoDuration(orderinfo.getTime().doubleValue() / SYSTEM.HOUR.doubleValue());

		// 设置红包支付
		csOrder.setCsoPayCoin(orderinfo.usecoin);

		// 检查保证金是否可用
		Double canMoney = csOrder.getCsoCreditCard() == null ? commonMoneyService.getUsableAmount(csOrder.getCsoPayMember()) : commonMoneyService
				.getUsableMoneyAndCouponAndCredit(csOrder.getCsoPayMember());
		// 如果当前会员余额不足并且订单不是后台管理员下单，则不允许下单
		if (LoginHelper.getLogin() == null) {
			// 如果打算使用信用卡，但如果有未还款的信用帐单，那么不给下单
			CsCreditCard csCreditCard = csOrder.get$csoCreditCard();
			if (csCreditCard != null && CsCreditBill.Get($.add(CsCreditBill.F.cscbMember, csOrder.getCsoUseMember()).add(CsCreditBill.F.cscbStatus, 0)) != null) {
				throw new MessageException(ErrorCode.ORDER_CREDIT_UN_REPAY, "会员有未结算的信用帐单，不能以信用模式租车");
			}
			if (csCreditCard == null && canMoney < $(csOrder.getCsoMarginNeed() + csOrder.getCsoPayNeed() + csOrder.getCsoPredict() - csOrder.getCsoPayCoin()))
				throw new MessageException(ErrorCode.ORDER_MONEY_LESS, "当前账户[余额+现金券]不足，不允许下单");
			if (canMoney < 0)
				throw new MessageException(ErrorCode.ORDER_MONEY_LESS, "会员已欠费，不能下单");
		}
		
		Package2016GiftLimit.doCheckOrderPayment(csOrder, orderinfo, canMoney);

		// 信用检测
		if (CsCreditBill.Get($.add(CsCreditBill.F.cscbMember, csOrder.getCsoUseMember()).add(CsCreditBill.F.cscbStatus, 4)) != null) {
			throw new MessageException(ErrorCode.ORDER_CREDIT_BAD_PAY, "会员有信用帐单坏帐，不能租车");
		}

		// 如果订单网点和取车网点不一样，则把车辆自动下线，并且添加一条异借确认到外勤系统
		if (csOrder.getCsoOutlets().longValue() != csOrder.getCsoOutletsRet().shortValue()) {
			SchedulingService.onlineCar(carId, (short) 2, "异地预订订单车辆自动下线", null);
			csOrder.setCsoUseType((short) 1);
		} else {
			csOrder.setCsoUseType((short) 0);
		}

		csOrder = csOrderDao.saveCsOrder(csOrder);

		// 如果订单网点和取车网点不一样，则把车辆自动下线，并且添加一条异借确认到外勤系统
		if (csOrder.getCsoOutlets().longValue() != csOrder.getCsoOutletsRet().shortValue()) {
			new TbAbAffirm(csOrder.getCsoHost()// 城市 [非空]
					, csOrder.getCsoCar()// 所属车辆 [非空]
					, csOrder.getCsoId()// 所属订单
					, csOrder.getCsoOutlets()// 借出网点
					, csOrder.getCsoOutletsRet()// 预计还入网点
					, null// 实际还入网点
					, csOrder.getCsoFinishTime()// 预计还入时间
					, null// 实际还入网点
					, null// 确认人
					, LoginHelper.getLoginId()// 添加人 [非空]
					, LoginHelper.getLoginId()// 修改人 [非空]
					, null// 确认时间
					, new Date()// 修改时间 [非空]
					, new Date()// 添加时间 [非空]
					, null// 备注
					, (short) 0// 状态 [非空]
			).save();
		}

		// 更新日志
		csOrderLog.csolOrder(csOrder.getCsoId()).update();

		// 插入调度记录数据
		SchedulingService.scheduling(carId, csOrder.getCsoOutlets(), csOrder.getCsoOutletsRet(), csOrder.getCsoStartTime(), csOrder.getCsoFinishTime(),
				com.ccclubs.service.common.impl.SchedulingService.DispatchType.定单调度, csOrder.getCsoId(), "订单自动调度");

		this.saveOrderInfos(csOrder.getCsoId(), orderinfo);

		Boolean bUnSendSms = false;
		try {
			bUnSendSms = $.getBoolean("checkbox", false);
		} catch (Exception ex) {
			bUnSendSms = false;
		}

		// 发送短信
		if ($.getLocal(ICommonDisposeService.DONOTSENDSMS) == null && bUnSendSms == false) {
			UtilHelper.sendTemplateSMS(
					csOrder.getCsoHost(),
					"ADD_ORDER",
					csOrder.getCsoMobile(),
					"您的订单时间{start}到{finish}，取车点{outlets}，车牌号{number}，授权码{code}[车纷享]",
					SMSType.通知类短信,
					$.add("start", $.date(csOrder.getCsoStartTime(), "MM月dd日HH时mm分")).add("finish", $.date(csOrder.getCsoFinishTime(), "MM月dd日HH时mm分"))
							.add("outlets", (csOrder.get$csoOutlets()).getCsoName()).add("number", (csOrder.get$csoCar()).getCscNumber())
							.add("code", csOrder.getCsoCode()));
			// 发送微信消息
			com.ccclubs.helper.WeixinHelper.sendWxTemplateMessage(csOrder.getCsoUseMember(), WxTemplateMsgType.订单预订, CsOrder.class, csOrder.getCsoId());
		}
		$.setLocal(ICommonDisposeService.DONOTSENDSMS, null);

		com.ccclubs.helper.TimeLineHelper.update(csOrder.getCsoId());

		return csOrderDao.getCsOrderById(csOrder.getCsoId());
	}
	
	public synchronized CsOrderCluster saveMealOrder(Long payMemberId
	        , Long useMemberId, Long carId, Long outlets_get_id,Long outlets_ret_id
	        , Date start, Long mealId, Long feeType, Long insureType, Double longPrice
	        , Double freeHours, Long editor, From from, String src) throws MessageException {

		SrvLock.lock(SrvLock.LockType.订单事务锁, "套餐添加");

		CsOrderLog csOrderLog = new CsOrderLog(// 添加订单日志
				"订单添加"// 操作项目 [非空]
				, 0l// 所属订单 [非空]
				, $.or(LoginHelper.getLoginId(), 0l)// 操作人 [非空]
				, LoginHelper.getLoginId() != null ? null : useMemberId, (short) from.ordinal(), src, $.getString("csOrder.csoRemark")// 备注信息
				, new Date()// 添加时间 [非空]
		).save();
		
		if (payMemberId == null)payMemberId = useMemberId;
		if (outlets_ret_id == null)outlets_ret_id = outlets_get_id;
		CsCar csoCar = csCarDao.getCsCarById(carId);
		//
		//判断车辆是否已被租用
		Long carCount=csOrderDao.getCsOrderCount($.add("csoCar", csoCar.getCscId()).add("definex", " cso_status in (0,1)"));
		if(carCount!=null&&carCount>0) {
			throw new MessageException(ErrorCode.ORDER_COUNT_LIMIT, "车辆已被租用，请更换车辆");
		}
		
		//
		//判断会员是否已存在订单（已预订、使用中）
		Long count=csOrderDao.getCsOrderCount($.add("csoUseMember", useMemberId).add("definex", " cso_status in (0,1)"));
		if(count!=null&&count>0) {
			throw new MessageException(ErrorCode.ORDER_COUNT_LIMIT, "每个会员仅允许租用一辆车");
		}
		//
		CsMember member = CsMember.get(payMemberId);
		CsItem item = CsItem.get(mealId);
		if(item == null)
			throw new MessageException(ErrorCode.PACKAGE_CANOT_BUY, "套餐不存在");
		
		if (member.getCsmVDrive().shortValue() != 1)
			throw new MessageException(ErrorCode.ORDER_MEMBERINFO_ERROR, "该会员资料未审核通过，不能下单");
		if (member.getCsmStatus().shortValue() != 1)
			throw new MessageException(ErrorCode.ORDER_MEMBER_DISABLE, "该会员帐号已被禁用，不能下单");
		if (member.get$csmEvcard() == null)
			throw new MessageException(ErrorCode.ORDER_NO_EV_CARD, "没有绑定EV卡，不能下单");

		//检查是否有未完成的退款
		if (commonMoneyService.isRefunding(payMemberId))
			throw new MessageException(ErrorCode.ORDER_NO_REFUND, "当前支付会员正在退款，不能下单");
		
		// 信用检测
		if (CsCreditBill.Get($.add(CsCreditBill.F.cscbMember, payMemberId).add(CsCreditBill.F.cscbStatus, 4)) != null) {
			throw new MessageException(ErrorCode.ORDER_CREDIT_BAD_PAY, "会员有信用帐单坏帐，不能租车");
		}
		
		if(start.before(new Date())){
			throw new MessageException(ErrorCode.ORDER_TIME_ERROR, "不能预订比当前时间早的订单");
		}
		
		// A借B还
//		if (outlets_get_id.longValue() != outlets_ret_id.longValue()) {
//			throw new MessageException(ErrorCode.ORDER_A_B_OUTLETS_ERROR, "套餐不支持A借B还");
//		}
		/*******2018-09-14套餐价格从计费规则中获取*******/
        String ruleName = item.getCsiFlag();
        String mealDescript = item.getCsiDepict();
        
        CsCar csCar = csCarDao.getCsCarById(carId);//车辆
        if(csCar ==  null) {
            throw new MessageException("辆车不存在", -520);
        }
        Long carModel = csCar.getCscModel();//车型
        CsOutlets csOutlets = CsOutlets.get(outlets_get_id);//取车网点
        if(csOutlets ==  null) {
            throw new MessageException("网点不存在", -520);
        }
        SrvHost srvHost = SrvHost.get(csOutlets.getCsoHost());//城市
        if(srvHost ==  null) {
            throw new MessageException("运营城市不存在", -520);
        }
        ICommonOrderService commonOrderService = $.GetSpringBean("commonOrderService");
        
        CsFeeTypeSet csFeeTypeSet = CsFeeTypeSet.Get($.add(CsFeeTypeSet.F.csftsHost, srvHost.getShId()).add(CsFeeTypeSet.F.csftsModel, carModel));
        Long userType = FeeTypeUitl.getUserType(feeType, csFeeTypeSet, null);
        CsProduct rent = commonOrderService.getProductByFlag(SYSTEM.RENT);
        
        Map<String, TimeSlot> slotMap = commonOrderService.getRules(csFeeTypeSet.getCsftsOutlets(), outlets_get_id, carModel, userType, rent.getCspId());
        
        TimeSlot timeSlot = slotMap.get(ruleName);
        if(timeSlot ==  null) {
            throw new MessageException("未配置套餐计费规则", -520);
        }
        
        double mealPrice = timeSlot.getPrice();
        String ruleDescript = timeSlot.toString();
        
        /*******2018-09-14套餐价格从计费规则中获取*******/
		
		
		MealExpress me  = MealHelper.parseExpress(mealDescript);
		Double defineMargin = me.getMargin();
		
		//检查余额是否足够
		Double canMoney = commonMoneyService.getUsableAmount(payMemberId);
		//canMoney = canMoney-member.getCsmCoupon();   //优惠券不可以用
		if(canMoney < (mealPrice + me.getMargin())){
			throw new MessageException(ErrorCode.ORDER_MONEY_LESS, "当前账户[余额+现金券]不足，不允许下单");
		}
		
		Double totalFee = mealPrice+defineMargin;
		Double payCoupon = 0d;
		Double payMoney = 0d;
		if(member.getCsmCoupon() >= totalFee){
			payCoupon = totalFee;
		}else{
			payCoupon = member.getCsmCoupon();
			payMoney  = totalFee - payCoupon;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		List<CsOrder> orderList = MealHelper.calcMealOrders(mealDescript, cal.getTime());
		start = orderList.get(0).getCsoStartTime();
		
		/**
		 * 订单簇冻结金额只包含（margin_need保证金）
		 */
		CsOrderCluster coc = new CsOrderCluster();
		coc.setCsocHost(member.getCsmHost());
		coc.setCsocPayMember(member.getCsmId());
		coc.setCsocOutType((short)1);				//套餐类型，CsItem
		coc.setCsocOutId(item.getCsiId());			//
		coc.setCsocMobile(member.getCsmMobile());
		coc.setCsocPrice(mealPrice);
		coc.setCsocMarginNeed(defineMargin);
		coc.setCsocTotalDuration($((orderList.get(orderList.size()-1).getCsoFinishTime().getTime()-start.getTime())*1d / SYSTEM.HOUR));
		coc.setCsocPayNeed(mealPrice);
		coc.setCsocPayReal(mealPrice);
		coc.setCsocPayRent(mealPrice);
		coc.setCsocPayMoney(payMoney);
		coc.setCsocPayCoupon(payCoupon);
		coc.setCsocPayCoin(0d);
		coc.setCsocSubOrders("");
        coc.setCsocPayDetails($.json($.add("express", mealDescript)
                .add(new DateUtil().dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"),
                        "套餐下单支付套餐费用" + mealPrice + ",冻结保证金" + defineMargin)
                .add("关联规则", ruleDescript)));
		coc.setCsocStatus((short)0);
		coc.setCsocStartTime(start);
		coc.setCsocFinishTime(orderList.get(orderList.size()-1).getCsoFinishTime());
		coc.setCsocAddTime(new Date());
		coc.setCsocUpdateTime(new Date());
		coc = coc.save();
		
		ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
		/***************************** 扣款 ********************************/
		String strDescript = "订单簇预定："+coc.getCsocId$()+",套餐ID"+item.getCsiId()+",套餐名称"+item.getCsiTitle()+",套餐金额"+mealPrice+",保证金"+defineMargin;
		if(payMoney >0){
			commonMoneyService.updateMoney(coc.getCsocHost(), member.getCsmId(), ICommonMoneyService.MoneyType.Money, -payMoney,
					SYSTEM.RecordType.订单消费, strDescript, coc.getCsocId(), coc.getCsocId(), CsOrderCluster.class);
		}
		if(payCoupon >0){
			commonMoneyService.updateMoney(coc.getCsocHost(), member.getCsmId(), ICommonMoneyService.MoneyType.Coupon, -payCoupon,
					SYSTEM.RecordType.订单消费, strDescript, coc.getCsocId(), coc.getCsocId(), CsOrderCluster.class);
		}
		/***************************** 扣款 ********************************/
		String sub_order_ids = "";
		for(int i=0; i<orderList.size(); i++){
			
			CsOrder o = orderList.get(i);
			start = o.getCsoStartTime();
			Date finish = o.getCsoFinishTime();
			
			CsOrder csOrder = new CsOrder();
			csOrder.setCsoFlag((short)3);			//订单标识设置成“订单簇”
			csOrder.setCsoPayMember(payMemberId);
			csOrder.setCsoUseMember(useMemberId);
			csOrder.setCsoCreditCard(null);
			csOrder.setCsoCar(carId);
			csOrder.setCsoStartTime(o.getCsoStartTime());
			csOrder.setCsoFinishTime(o.getCsoFinishTime());
			csOrder.setCsoInsureType(insureType);
			csOrder.setCsoFreehour(freeHours);
			csOrder.setCsoPayInsure(0d);
			csOrder.setCsoPayTimeout(0d);
			csOrder.setCsoArea((csoCar.get$cscOutlets()).getCsoArea());
			csOrder.setCsoRemark("使用套餐【"+item.getCsiTitle()+"】预定");
			csOrder.setCsoDeviceVesion(csoCar.getCscDeviceVesion());
			csOrder.setCsoHost(csoCar.getCscHost());
			csOrder.setCsoUseIndex(CsOrder.where().csoUseMember(useMemberId).csoStatus((short) 4).count().intValue());
			Short notRevenue = $.or(csOrder.get$csoPayMember().getCsmNotRevenue(), (short) 0);
			csOrder.setCsoIncome((short) (notRevenue.shortValue() == 0 ? 1 : 0));
			csOrder.setCsoOutlets($.or(outlets_get_id, csoCar.getCscOutlets()));// 取车网点
			csOrder.setCsoOutletsRet($.or(outlets_ret_id, csoCar.getCscOutlets()));// 还车网点
			
			/***************************** 校验 ********************************/
			// 分析取还车网点
			String schedulingError = SchedulingService.checkGetRetOutlets(carId, csOrder.getCsoOutlets(), csOrder.getCsoOutletsRet(), start, finish);
			if (!$.empty(schedulingError))
				throw new MessageException(ErrorCode.ORDER_A_B_OUTLETS_ERROR, schedulingError);
			
			// 分析时段是否有订单
			if (commonOrderService.isExistOrderByTime(carId, start, finish, null))
				throw new MessageException(ErrorCode.ORDER_CONFLICT, "当前订单时间已经被其它订单占用");
			/**************************** 校验 *********************************/
			
			csOrder.setCsoModel(csoCar.getCscModel());
			csOrder.setCsoMask(0l);
			csOrder.setCsoCarNumber((csOrder.get$csoCar()).getCscNumber());// 车牌号
			csOrder.setCsoMobile(csOrder.get$csoUseMember().getCsmMobile());// 手机号码
			csOrder.setCsoEvRfid(((csOrder.get$csoUseMember()).get$csmEvcard()).getCsecRfid());// ev卡号
			csOrder.setCsoProvid((csOrder.get$csoCar()).getCscProvid());// 所属商家
			csOrder.setCsoIsAdCar(csoCar.getCscIsAd());// 保存车辆是否宣传车
			csOrder.setCsoRebate(csoCar.getCscRebate());// 保存车辆的折扣状态

			// 设置默认值
			csOrder.setCsoFuel(0d);
			csOrder.setCsoElectric(0d);
			csOrder.setCsoPayReal(0d);

			csOrder.setCsoFrom((short) from.ordinal());
			csOrder.setCsoUpdateTime(new Date());
			csOrder.setCsoAddTime(new Date());
			csOrder.setCsoCode($.zerofill($.rand(999999), 6).replaceAll("0", "1"));
			csOrder.setCsoEditor(editor);
			csOrder.setCsoSrc(CsOrderCluster.class.getSimpleName()+"@"+coc.getCsocId());
			csOrder.setCsoAlarm((short) 0);
			csOrder.setCsoState((short) 0);
			csOrder.setCsoStatus((short) 0);

			// 订单序列号=会员ID+车辆ID+订单开始时间
			csOrder.setCsoSerial(csOrder.getCsoUseMember() + "-" + csOrder.getCsoCar() + "-" + csOrder.getCsoStartTime().getTime());
			csOrder.setCsoLongPrice(longPrice);
			
			CsOrder order = csOrderService.getCsOrder($.add(CsOrder.F.csoSerial, csOrder.getCsoSerial()));
			if(order!=null){
				if(order.getCsoStatus() == 0 || order.getCsoStatus() == 1 || order.getCsoStatus() == 5){
					Logger.getRootLogger().error("您已经预定了当前时间段该车辆，"+csOrder.getCsoSerial());
					throw new MessageException(ErrorCode.ORDER_CONFLICT, "您已经预定了当前时间段该车辆");
				}else{
					order.setCsoSerial($.md5(order.getCsoSerial()+order.getCsoStatus()));
					order.setCsoUpdateTime(new Date());
					order.update();
				}
			}
			

			// 获取小时数
			int allhours = (int) ((finish.getTime() - start.getTime()) / SYSTEM.HOUR);
			$.setLocal("defineactual", commonOrderService.getPredictKms(allhours) * 1);

			// 拆分订单
			OrderInfo orderinfo = commonOrderService.splitOrderDetails(payMemberId, carId, start, finish, null, freeHours, null, null, feeType, insureType, longPrice, null);
			
			/***************************** 更改订单金额 ********************************/
			orderinfo.setPrice(0d);
			orderinfo.setMargin(0d);				//保证金
			orderinfo.setFeeee(0d);					//里程费
			orderinfo.setUsecoin(0d);				//不能用红包
			
			/***************************** 更改订单金额 ********************************/
			csOrder.setCsoUseType((short) (csOrder.getCsoOutlets().longValue()==csOrder.getCsoOutletsRet().longValue() ? 0 : 1));														//原借原还
			csOrder.setCsoFeeType(orderinfo.feetype);												// 计费类型
			csOrder.setCsoPayNeed(orderinfo.price);													// 设置订单需付额
			csOrder.setCsoMarginNeed(orderinfo.margin);												// 设置订单需保证金额
			csOrder.setCsoPredict(orderinfo.feeee);													// 设置订单预计里程费
			csOrder.setCsoDuration(orderinfo.getTime().doubleValue() / SYSTEM.HOUR.doubleValue());	// 订单时长
			csOrder.setCsoPayCoin(orderinfo.usecoin);												// 设置红包支付
			csOrder = csOrderDao.saveCsOrder(csOrder);

			// 更新日志
			csOrderLog.csolOrder(csOrder.getCsoId()).update();

			// 插入调度记录数据
			SchedulingService.scheduling(carId, csOrder.getCsoOutlets(), csOrder.getCsoOutletsRet(), csOrder.getCsoStartTime(), csOrder.getCsoFinishTime(),
					com.ccclubs.service.common.impl.SchedulingService.DispatchType.定单调度, csOrder.getCsoId(), "订单自动调度");

			//this.saveOrderInfos(csOrder.getCsoId(), orderinfo);

			Boolean bUnSendSms = false;
			try {
				bUnSendSms = $.getBoolean("checkbox", false);
			} catch (Exception ex) {
				bUnSendSms = false;
			}

			// 发送短信
			if ($.getLocal(ICommonDisposeService.DONOTSENDSMS) == null && bUnSendSms == false) {
				UtilHelper.sendTemplateSMS(
						csOrder.getCsoHost(),
						"ADD_ORDER",
						csOrder.getCsoMobile(),
						"您的订单时间{start}到{finish}，取车点{outlets}，车牌号{number}，授权码{code}[车纷享]",
						SMSType.通知类短信,
						$.add("start", $.date(csOrder.getCsoStartTime(), "MM月dd日HH时mm分")).add("finish", $.date(csOrder.getCsoFinishTime(), "MM月dd日HH时mm分"))
								.add("outlets", (csOrder.get$csoOutlets()).getCsoName()).add("number", (csOrder.get$csoCar()).getCscNumber())
								.add("code", csOrder.getCsoCode()));
				// 发送微信消息
				com.ccclubs.helper.WeixinHelper.sendWxTemplateMessage(csOrder.getCsoUseMember(), WxTemplateMsgType.订单预订, CsOrder.class, csOrder.getCsoId());
			}
			$.setLocal(ICommonDisposeService.DONOTSENDSMS, null);

			com.ccclubs.helper.TimeLineHelper.update(csOrder.getCsoId());

			o.setCsoId(csOrder.getCsoId());
			sub_order_ids +=","+csOrder.getCsoId();
			csOrderDao.getCsOrderById(csOrder.getCsoId());
		}
		
		//更新宝贝已售数
		item.setCsiSale(item.getCsiSale() == null ? 0 : item.getCsiSale()+1); 
		item.update();
		
		coc.setCsocSubOrders(sub_order_ids);
		coc.update();

		return coc;
	}
	
	/**
	 * 判断子订单是否属于订单簇
	 * @param order
	 * @return
	 */
	public static boolean isOrderCluster(CsOrder order){
		if(order!=null){
			return order.getCsoFlag() != null && order.getCsoFlag().intValue() == 3 ;
		}
		return false;
	}

	/**
	 * 续订订单
	 * 
	 * @throws MessageException
	 * @param[非空] orderId
	 * @param[非空] finish
	 * @param[可空] freeHours
	 */
	public CsOrder executeReOrder(Long orderId, Date finish, Double freeHours, From from, String src) throws MessageException {

		SrvLock.lock(SrvLock.LockType.订单事务锁,
				"续订订单,订单时间[" + $.date(CsOrder.get(orderId).getCsoFinishTime(), "yyyy-MM-dd HH:mm:ss") + "]变更为[" + $.date(finish, "yyyy-MM-dd HH:mm:ss") + "]");

		try {
			org.springframework.transaction.interceptor.TransactionInterceptor transactionInterceptor = $.getBean("ccclubs_systemTransactionInterceptor");
			org.springframework.transaction.TransactionStatus currentTransactionStatus = transactionInterceptor.currentTransactionStatus();
			Field field = currentTransactionStatus.getClass().getDeclaredField("suspendedResources");
			field.setAccessible(true);
			Object holder = field.get(currentTransactionStatus);
			if (holder != null) {
				field = holder.getClass().getDeclaredField("name");
				field.setAccessible(true);
				String name = (String) field.get(holder);
			}
		} catch (Exception ex) {
		}

		new CsOrderLog(// 添加订单日志
				"续订订单,订单时间[" + $.date(CsOrder.get(orderId).getCsoFinishTime(), "yyyy-MM-dd HH:mm:ss") + "]变更为[" + $.date(finish, "yyyy-MM-dd HH:mm:ss") + "]"// 操作项目
																																								// [非空]
				, orderId// 所属订单 [非空]
				, $.or(LoginHelper.getLoginId(), 0l)// 操作人 [非空]
				, LoginHelper.getLoginId() != null ? null : CsOrder.get(orderId).getCsoUseMember(), (short) from.ordinal(), src,
				$.getString("csOrder.csoRemark")// 备注信息
				, new Date()// 添加时间 [非空]
		).save();

		CsOrder csOrder = csOrderDao.getCsOrderById(orderId);
		
		//定单簇单判断
		if(isOrderCluster(csOrder))
			throw new MessageException(ErrorCode.ORDER_FLAG_ERROR, "套餐订单不能续订");

		if (csOrder.getCsoStatus().shortValue() > 1 && csOrder.getCsoStatus().shortValue() != 5)
			throw new MessageException(ErrorCode.ORDER_STATUS_ERROR, "只有订单状态为已预订、正在执行、待处理的订单才能续订");

		if (csOrder.getCsoCreditCard() != null && LoginHelper.getLogin() == null) {
			Long renewcount = CsOrderLog.getCsOrderLogCount($.add(CsOrderLog.F.csolOrder, csOrder.getCsoId()).add("definex", "csol_title LIKE '%续订%'"));
			if (renewcount > 3l) {
				throw new MessageException(ErrorCode.ORDER_COUNT_LIMIT, "免押金订单最多只能续订三次");
			}
			Date oldFinishDate = csOrder.getCsoFinishTime();
			if ((finish.getTime() - oldFinishDate.getTime()) > (3 * SYSTEM.HOUR)) {
				throw new MessageException(ErrorCode.ORDER_TIME_LIMIT, "免押金订单续订时间不能超过3小时");
			}
		}

		finish = resetSecond(finish);

		if (commonMoneyService.isRefunding(csOrder.getCsoPayMember()))
			throw new MessageException(ErrorCode.ORDER_NO_REFUND, "当前支付会员正在退款，不能续订");

		if (!finish.after(csOrder.getCsoFinishTime()))
			throw new MessageException(ErrorCode.ORDER_TIME_ERROR, "续订结束时间必须在原订单结束时间之后");

		Date oldFinishTime = csOrder.getCsoFinishTime();

		csOrder.setCsoFinishTime(finish);
		csOrder.setCsoFreehour(freeHours);

		if (commonOrderService.isExistOrderByTime(csOrder.getCsoCar(), csOrder.getCsoStartTime(), finish, orderId))
			throw new MessageException(ErrorCode.ORDER_CONFLICT, "当前订单时间已经被其它订单占用");

		// 拆分订单
		OrderInfo orderinfo = commonOrderService.splitOrderDetails(csOrder.getCsoPayMember(), csOrder.getCsoCar(), csOrder.getCsoStartTime(),
				csOrder.getCsoFinishTime(), csOrder.getCsoRetTime(), csOrder.getCsoFreehour(), csOrder.getCsoElectric(), csOrder.getCsoFuel(),
				csOrder.getCsoFeeType(), csOrder.getCsoInsureType(), csOrder.getCsoLongPrice(), csOrder.getCsoId());

		// 设置订单时长
		csOrder.setCsoDuration(orderinfo.getTime().doubleValue() / SYSTEM.HOUR.doubleValue());

		// 检查余额是否够用
		Double memberMoney = csOrder.getCsoCreditCard() == null ? commonMoneyService.getUsableAmount(csOrder.getCsoPayMember()) : commonMoneyService
				.getUsableMoneyAndCouponAndCredit(csOrder.getCsoPayMember());
		// 当前查询到的余额+当前订单之前所占用的余额 比较 当前订单的费用
		if (LoginHelper.getLogin() == null
				&& (memberMoney + csOrder.getCsoPayNeed() + csOrder.getCsoPredict() + csOrder.getCsoMarginNeed()) < (orderinfo.getPrice() + orderinfo.feeee
						+ orderinfo.margin - orderinfo.usecoin))
			throw new MessageException(ErrorCode.ORDER_MONEY_LESS, "会员可用余额不足");
		
		Package2016GiftLimit.doCheckReOrderPayment(csOrder, orderinfo, memberMoney);

		// 设置订单需付额
		csOrder.setCsoPayNeed(orderinfo.getPrice());
		// 设置订单预计里程费
		csOrder.setCsoPredict(orderinfo.feeee);
		// 设置是否长单
		csOrder.setCsoLongOrder(orderinfo.longorder);
		// 设置红包支付
		csOrder.setCsoPayCoin(orderinfo.usecoin);

		csOrder.setCsoRemark("请输入备注信息");

		csOrderDao.updateCsOrder$NotNull(csOrder);

		this.saveOrderInfos(csOrder.getCsoId(), orderinfo);

		// 发送短信
		if ($.getLocal(ICommonDisposeService.DONOTSENDSMS) == null) {
			UtilHelper.sendTemplateSMS(
					csOrder.getCsoHost(),
					"REORDER_ORDER",
					csOrder.getCsoMobile(),
					"您的定单[{order}]续定成功，定单结束时间由{old}变更为{new}[车纷享]",
					SMSType.通知类短信,
					$.add("order", csOrder.getCsoId().toString()).add("old", $.date(oldFinishTime, "MM月dd日HH时mm分"))
							.add("new", $.date(csOrder.getCsoFinishTime(), "MM月dd日HH时mm分")));
			// 发送微信消息
			com.ccclubs.helper.WeixinHelper.sendWxTemplateMessage(csOrder.getCsoUseMember(), WxTemplateMsgType.订单续订, CsOrder.class, csOrder.getCsoId());
		}
		$.setLocal(ICommonDisposeService.DONOTSENDSMS, null);

		com.ccclubs.helper.TimeLineHelper.update(csOrder.getCsoId());

		return csOrderDao.getCsOrderById(csOrder.getCsoId());
	}
	
	public void executeCancelOrderCluster(Long orderId, String remark, From from, String src) throws MessageException{

		CsOrder order = csOrderDao.getCsOrderById(orderId);
		
		if(!isOrderCluster(order)){
			throw new MessageException(ErrorCode.ORDER_STATUS_ERROR, "定单簇无效");
		}
		
		CsOrderCluster coc = CsOrderCluster.Get($.add(CsOrderCluster.F.csocId, order.getCsoSrc().replace("CsOrderCluster@", "")));
		CsMember csMember = coc.get$csocPayMember();
		
		SrvLock.lock(SrvLock.LockType.订单事务锁, "取消订单簇" + coc.getCsocId());

		new CsOrderLog(// 添加订单日志
				"取消订单簇"// 操作项目 [非空]
				, coc.getCsocId()// 所属订单 [非空]
				, $.or(LoginHelper.getLoginId(), 0l)// 操作人 [非空]
				, LoginHelper.getLoginId() != null ? null : coc.getCsocPayMember(), (short) from.ordinal(), src, remark// 备注信息
				, new Date()// 添加时间 [非空]
		).save();
		
		if(coc !=null){
			String subOrders = coc.getCsocSubOrders();
			if(subOrders.startsWith(",")){
				subOrders = subOrders.substring(subOrders.indexOf(",")+1, subOrders.length());
			}else if(subOrders.endsWith(",")){
				subOrders = subOrders.substring(0, subOrders.length()-1);
			}
			List<CsOrder> orderList = CsOrder.getCsOrderList($.add("definex", " cso_id in ("+subOrders+")"), -1);
			for(CsOrder csOrder : orderList){
				//状态已预订
				if(csOrder.getCsoStatus().intValue() != 0){
					throw new MessageException(ErrorCode.ORDER_STATUS_ERROR, "已有子订单不是预定状态，不能完成取消");
				}
				
				if(csOrder.getCsoStartTime().before(new Date())){
					throw new MessageException(ErrorCode.ORDER_STATUS_ERROR, "已有子订单晚于订单开始时间，不能完成取消");
				}
			}
			
			for(CsOrder csOrder : orderList){
				csOrder.setCsoPayReal(0d);
				if (csOrder.getCsoPayReal() == null )
					csOrder.setCsoPayReal(csOrder.getCsoPayNeed());
				if (csOrder.getCsoElectric() == null)
					csOrder.setCsoElectric(0d);
				if (csOrder.getCsoFuel() == null)
					csOrder.setCsoFuel(0d);

				csOrder.setCsoPayMoney(0d);
				csOrder.setCsoPayCoupon(0d);
				csOrder.setCsoPayCoin(0d);

				// 设置订单冗余支付项
				csOrder.setCsoPayKilom(0d);
				csOrder.setCsoPayMileage(0d);
				csOrder.setCsoPayDriver(0d);
				csOrder.setCsoPayInsure(0d);
				csOrder.setCsoPayTimeout(0d);
				csOrder.setCsoPayRent(0d);
				csOrder.setCsoEndTime(new Date());

				csOrder.setCsoCancelFrom((short) from.ordinal());//设置订单取消来源
				csOrder.setCsoStatus((short) 3);		// 设置订单为已取消
				csOrder.setCsoRemark(remark);			// 备注信息
				csOrder.setCsoUpdateTime(new Date());	// 取消时间

				// 订单序列号=会员ID+车辆ID+订单开始时间+状态
				csOrder.csoSerial(csOrder.getCsoUseMember() + "-" + csOrder.getCsoCar() + "-" + csOrder.getCsoStartTime().getTime() + "-" + csOrder.getCsoStatus() + "-" + Calendar.getInstance().get(Calendar.MINUTE));
				csOrder.setCsoRemark("订单簇订单取消");
				csOrderDao.updateCsOrder$NotNull(csOrder);// 保存订单到数据库

				// 撤销定单的订单调度
				SchedulingService.cancelScheduling(csOrder.getCsoId());

				// 更新相关的企业订单
				CsUnitOrder unitOrder = CsUnitOrder.Get($.add(CsUnitOrder.F.csuoOrder, csOrder.getCsoId()));
				if (unitOrder != null) {
					CommonUnitService.getBean().executeComplete(unitOrder.getCsuoId());
				}

				com.ccclubs.helper.TimeLineHelper.update(csOrder.getCsoId());
			}
			
			//退款
			String sDescript = "订单簇取消，产生退款"+coc.getCsocPayReal();
			if(coc.getCsocPayMoney()!=null && coc.getCsocPayMoney() > 0){
				commonMoneyService.updateMoney(order.getCsoHost(), csMember.getCsmId(), ICommonMoneyService.MoneyType.Money, coc.getCsocPayMoney(),
						SYSTEM.RecordType.订单退款, sDescript, coc.getCsocId(), coc.getCsocId(), CsOrderCluster.class);
			}
			
			if(coc.getCsocPayMoney()!=null && coc.getCsocPayCoupon() > 0){
				commonMoneyService.updateMoney(order.getCsoHost(), csMember.getCsmId(), ICommonMoneyService.MoneyType.Coupon, coc.getCsocPayCoupon(),
						SYSTEM.RecordType.订单退款, sDescript, coc.getCsocId(), coc.getCsocId(), CsOrderCluster.class);
			}
			
			//订单簇状态修改
			coc.setCsocPayReal(0d);
			coc.setCsocStatus((short)3);
			coc.setCsocUpdateTime(new Date()); 
			coc.update();
			
			// 发送短信
			if ($.getLocal(ICommonDisposeService.DONOTSENDSMS) == null) {
				UtilHelper.sendTemplateSMS(
						order.getCsoHost(),
						"CANCEL_ORDER_CLUSTER",
						order.getCsoMobile(),
						"您的订单[{order}]于{time}取消成功，扣费{money}元[车纷享]",
						SMSType.通知类短信,
						$.add("order", order.getCsoId().toString()).add("time", $.date(new Date(), "MM月dd日HH时mm分"))
								.add("money", $(order.getCsoPayReal()).toString()));
				// 发送微信消息
				com.ccclubs.helper.WeixinHelper.sendWxTemplateMessage(order.getCsoUseMember(), WxTemplateMsgType.订单取消, CsOrderCluster.class, coc.getCsocId());
			}
			
		}else{
			throw new MessageException(ErrorCode.ORDER_STATUS_ERROR, "订单簇无效");
		}
	}

	/**
	 * 取消订单
	 * 
	 * @throws MessageException
	 * @param[非空] orderId
	 */
	public CsOrder executeCancelOrder(Long orderId, String remark, From from, String src) throws MessageException {

		SrvLock.lock(SrvLock.LockType.订单事务锁, "取消订单" + orderId);

		new CsOrderLog(// 添加订单日志
				"取消订单"// 操作项目 [非空]
				, orderId// 所属订单 [非空]
				, $.or(LoginHelper.getLoginId(), 0l)// 操作人 [非空]
				, LoginHelper.getLoginId() != null ? null : CsOrder.get(orderId).getCsoUseMember(), (short) from.ordinal(), src, remark// 备注信息
				, new Date()// 添加时间 [非空]
		).save();

		CsOrder csOrder = csOrderDao.getCsOrderById(orderId);
		
		//定单簇判断
		if(isOrderCluster(csOrder)){
			executeCancelOrderCluster(orderId, remark, from, src);
			return csOrderDao.getCsOrderById(orderId);
		}

		if (csOrder.getCsoStatus().shortValue() != 0) {
			throw new MessageException(ErrorCode.ORDER_CANOT_CANCEL, "只有已预订未使用的订单才能取消");
		}

		Date now = new Date();
		// 订单时长
		Double longer = new Double(csOrder.getCsoFinishTime().getTime() - csOrder.getCsoStartTime().getTime()) / new Double(1000 * 60 * 60);
		// 取消时间
		Double hour = new Double(csOrder.getCsoStartTime().getTime() - now.getTime()) / new Double(1000 * 60 * 60);
		Double scale = 1.0d;
		String sDescript = "";
		if (longer > 24) {
			if (hour > 6) {
				scale = 0d;
				sDescript = "大于24小时订单，取消时间大于6小时";
			} else if (hour < 0) {
				scale = 1.0d;
				sDescript = "大于24小时订单，超过预计取车时间";
			} else {
				scale = 0.1d;
				sDescript = "大于24小时订单，取消时间小于6小时";
			}
		} else {
			if (hour > 1) {
				scale = 0d;
				sDescript = "小于24小时订单，取消时间大于1小时";
			} else if (hour < 0) {
				scale = 1.0d;
				sDescript = "小于24小时订单，超过预计取车时间";
			} else {
				scale = 0.1d;
				sDescript = "小于24小时订单，取消时间小于1小时";
			}
		}

		// 拆分订单
		OrderInfo orderinfo = commonOrderService.splitOrderDetails(csOrder.getCsoPayMember(), csOrder.getCsoCar(), csOrder.getCsoStartTime(),
				csOrder.getCsoFinishTime(), csOrder.getCsoRetTime(), csOrder.getCsoFreehour(), csOrder.getCsoElectric(), csOrder.getCsoFuel(),
				csOrder.getCsoFeeType(), csOrder.getCsoInsureType(), csOrder.getCsoLongPrice(), csOrder.getCsoId());
		// 设置订单需付额
		csOrder.setCsoPayNeed(orderinfo.getPrice());

		// 应付额
		double payReal = orderinfo.getPrice() * scale;
		csOrder.setCsoPayReal(payReal);

		// 计算度设置订单的红包
		double payCoin = Math.min(payReal, orderinfo.usecoin);
		orderinfo.setUsecoin(payCoin);// 把实付红包改回去
		payReal = payReal - payCoin;
		csOrder.setCsoPayReal(payReal);// 实付额不应包含红包

		// 扣除会员帐户余额
		CsMember csMember = csMemberDao.getCsMemberById(csOrder.getCsoPayMember());
		Double payCoupon = $(Math.min(csMember.getCsmCoupon(), payReal));// 现金券支付(不能支付里程费)
		if (payCoupon < 0)
			payCoupon = 0d;
		payCoupon = Package2016GiftLimit.getCanUsePayCoupon(orderinfo, payCoupon);
		Double payMoney = $(payReal - payCoupon);// 余额支付数

		Double canMoney = commonMoneyService.getUsableAmount(csOrder.getCsoPayMember());
		CsCreditCard creditCard = csOrder.get$csoCreditCard();// 信用卡支付
		Boolean payByCreditCard = false;// 最终是否以信用卡支付

//		// 如果指定以信用卡支付，并且余额不够支付的情况下，以信用方式支付
//		if (creditCard != null && canMoney < payReal) {
//			payCoupon = 0d;
//			payMoney = payReal;
//			Long creditId = commonMoneyService.payCredit(csOrder.getCsoHost(), csMember.getCsmId(), csOrder.getCsoCreditCard(), payReal, "订单取消",
//					csOrder.getCsoId(), null, null);
//			csOrder.setCsoCreditBill(creditId);
//			payByCreditCard = true;
//		} else {
//			if (creditCard != null) {// 如果是信用订单，生成一条0月帐单
//				csOrder.setCsoCreditBill(commonMoneyService.payCredit(csOrder.getCsoHost(), csMember.getCsmId(), csOrder.getCsoCreditCard(), 0, "订单取消",
//						csOrder.getCsoId(), null, null));
//			}
//			if (payCoupon > 0) {// 现金券支付
//				commonMoneyService.updateMoney(csOrder.getCsoHost(), csMember.getCsmId(), ICommonMoneyService.MoneyType.Coupon, -payCoupon,
//						SYSTEM.RecordType.订单取消, sDescript, csOrder.getCsoId(), csOrder.getCsoId(), CsOrder.class);
//			}
//			if (payMoney > 0) {
//				commonMoneyService.updateMoney(csOrder.getCsoHost(), csMember.getCsmId(), ICommonMoneyService.MoneyType.Money, -payMoney,
//						SYSTEM.RecordType.订单取消, sDescript, csOrder.getCsoId(), csOrder.getCsoId(), CsOrder.class);
//			}
//			payByCreditCard = false;
//		}

		// if(csOrder.getCsoPayReal()==null || csOrder.getCsoPayReal()==0)
		if (csOrder.getCsoPayReal() == null )
			csOrder.setCsoPayReal(csOrder.getCsoPayNeed());
		if (csOrder.getCsoElectric() == null)
			csOrder.setCsoElectric(0d);
		if (csOrder.getCsoFuel() == null)
			csOrder.setCsoFuel(0d);

		csOrder.setCsoPayMoney(payMoney);
		csOrder.setCsoPayCoupon(payCoupon);
		csOrder.setCsoPayCoin(payCoin);

		// 设置订单冗余支付项
		csOrder.setCsoPayKilom(orderinfo.kilom);
		csOrder.setCsoPayMileage(orderinfo.mileage);
		csOrder.setCsoPayDriver(orderinfo.driver);
		csOrder.setCsoPayInsure(orderinfo.insure);
		csOrder.setCsoPayTimeout(orderinfo.timeout);
		csOrder.setCsoPayRent(orderinfo.rent);
		csOrder.setCsoEndTime(new Date());
		
		csOrder.setCsoCancelFrom((short) from.ordinal());//设置取消来源
		csOrder.setCsoStatus((short) 3);// 设置订单为已取消
		csOrder.setCsoRemark(remark);// 备注信息
		csOrder.setCsoUpdateTime(new Date());// 取消时间

		// 订单序列号=会员ID+车辆ID+订单开始时间+状态
		csOrder.csoSerial(csOrder.getCsoUseMember() + "-" + csOrder.getCsoCar() + "-" + csOrder.getCsoStartTime().getTime() + "-" + csOrder.getCsoStatus()
				+ "-" + Calendar.getInstance().get(Calendar.MINUTE));

		csOrder.setCsoRemark("请输入备注信息");

		csOrderDao.updateCsOrder$NotNull(csOrder);// 保存订单到数据库

		this.saveOrderInfos(csOrder.getCsoId(), orderinfo);

		// 发送短信
		if ($.getLocal(ICommonDisposeService.DONOTSENDSMS) == null) {
			UtilHelper.sendTemplateSMS(
					csOrder.getCsoHost(),
					payByCreditCard ? "CANCEL_ORDER_BY_CREDIT" : "CANCEL_ORDER",
					csOrder.getCsoMobile(),
					"您的订单[{order}]于{time}取消成功，扣费{money}元[车纷享]",
					SMSType.通知类短信,
					$.add("order", csOrder.getCsoId().toString()).add("time", $.date(new Date(), "MM月dd日HH时mm分"))
							.add("money", $(csOrder.getCsoPayReal()+csOrder.getCsoPayCoin()).toString()));
			// 发送微信消息
			com.ccclubs.helper.WeixinHelper.sendWxTemplateMessage(csOrder.getCsoUseMember(), WxTemplateMsgType.订单取消, CsOrder.class, csOrder.getCsoId());
		}
		$.setLocal(ICommonDisposeService.DONOTSENDSMS, null);

		// 发送信息到支付宝服务窗
		if (payByCreditCard) {
			ICsCreditBillService csCreditBillService = $.GetSpringBean("csCreditBillService");
			CsCreditBill creditBill = csCreditBillService.getCsCreditBill($.add("cscbOrder", csOrder.getCsoId()));
			if (creditBill != null) {
				// TODO 发送
				CsMember member = csOrder.get$csoUseMember();
				// String openid = member.getCsmOpenIds();
				// String alipay_user_id =
				// openid.substring(openid.indexOf("alipay:")+7,openid.indexOf("alipay:")+23);
				String alipay_user_id = member.getCsmAlipayFlag();
				if (!$.empty(alipay_user_id) && alipay_user_id.length() == 16) {
					WeixinHelper.sendCancelMsgFromUserId(alipay_user_id, csMember.getCsmName(), csOrder, alipay_user_id, creditBill.getCscbValue());
				}
			}
		}

		// 撤销定单的订单调度
		SchedulingService.cancelScheduling(csOrder.getCsoId());
		//SchedulingService.onlineCar(csOrder.getCsoCar(), (short) 1, "异借还车订单取消车辆自动上线", null);

		// 更新相关的企业订单
		CsUnitOrder unitOrder = CsUnitOrder.Get($.add(CsUnitOrder.F.csuoOrder, csOrder.getCsoId()));
		if (unitOrder != null) {
			CommonUnitService.getBean().executeComplete(unitOrder.getCsuoId());
		}

		com.ccclubs.helper.TimeLineHelper.update(csOrder.getCsoId());

		//修改车辆状态以及车辆网点地址 订单处于0,7 车辆网点修改为 订单的取车网点 其他订单状态 车辆的网点修改为还车网点 同时修改车辆状态为 上线
		if (csOrder.getCsoOutlets().longValue() != csOrder.getCsoOutletsRet().shortValue()) {
			CsCar csCar = new CsCar(csOrder.getCsoCar());
			csCar.setCscOutlets(csOrder.getCsoStatus()==0||csOrder.getCsoStatus() ==7 || csOrder.getCsoStatus() == 3 ? csOrder.getCsoOutlets(): csOrder.getCsoOutletsRet());
			csCar.update();
			
			SchedulingService.onlineCar(csOrder.getCsoCar(),(short)1,"异借还车订单取消车辆自动上线",null);
		}
		return csOrderDao.getCsOrderById(csOrder.getCsoId());
	}

	/**
	 * 结算订单
	 * 
	 * @param[非空] orderId
	 * @param[可空] retTime
	 * @param[可空] csoFuel
	 * @param[可空] csoElectric
	 */
	public CsOrder executeSettleOrder(Long orderId, Date retTime, Double csoFuel, Double csoElectric, From from, String src) throws MessageException {

		SrvLock.lock(SrvLock.LockType.订单事务锁, "结算订单" + orderId);

		new CsOrderLog(// 添加订单日志
				"结算订单"// 操作项目 [非空]
				, orderId// 所属订单 [非空]
				, $.or(LoginHelper.getLoginId(), 0l)// 操作人 [非空]
				, LoginHelper.getLoginId() != null ? null : CsOrder.get(orderId).getCsoUseMember(), (short) from.ordinal(), src,
				$.getString("csOrder.csoRemark")// 备注信息
				, new Date()// 添加时间 [非空]
		).save();

		CsOrder csOrder = csOrderDao.getCsOrderById(orderId);

		if (csOrder.getCsoStatus().shortValue() == 4)
			throw new MessageException(ErrorCode.ORDER_TIME_ERROR, 0, "订单不能重复结算");

		if (retTime != null)
			csOrder.setCsoRetTime(retTime);
		if (csoFuel != null)
			csOrder.setCsoFuel(csoFuel);
		if (csoElectric != null)
			csOrder.setCsoElectric(csoElectric);
		if (csOrder.getCsoElectric() == null)
			csOrder.setCsoElectric(0d);
		if (csOrder.getCsoFuel() == null)
			csOrder.setCsoFuel(0d);
		if (csOrder.getCsoTakeTime() == null)
			csOrder.setCsoTakeTime(csOrder.getCsoStartTime());

		if (csOrder.getCsoRetTime() == null)
			throw new MessageException(ErrorCode.ORDER_TIME_ERROR, 0, "该订单没有实际还车时间，不能结算");

		if ((csOrder.getCsoRetTime().getTime() - new Date().getTime()) > (SYSTEM.HOUR * 2))
			throw new MessageException(ErrorCode.ORDER_TIME_ERROR, 1, "还车时间不能比当前时间迟于一个小时");

		if (csOrder.getCsoRetTime().getTime() < csOrder.getCsoStartTime().getTime())
			throw new MessageException(ErrorCode.ORDER_TIME_ERROR, 2, "该订单没还车时间早于订单开始时间，不能结算");

		// 拆分订单
		OrderInfo orderinfo = commonOrderService.splitOrderDetails(csOrder.getCsoPayMember(), csOrder.getCsoCar(), csOrder.getCsoStartTime(),
				csOrder.getCsoFinishTime(), csOrder.getCsoRetTime(), csOrder.getCsoFreehour(), csOrder.getCsoElectric(), csOrder.getCsoFuel(),
				csOrder.getCsoFeeType(), csOrder.getCsoInsureType(), csOrder.getCsoLongPrice(), csOrder.getCsoId(), true);
		
		// 设置订单应付额，实付额
		csOrder.setCsoPayNeed(orderinfo.getPrice());
		// 设置订单预计里程费
		csOrder.setCsoPredict(orderinfo.feeee);

		// 设置订单时长
		csOrder.setCsoDuration(orderinfo.getTime().doubleValue() / SYSTEM.HOUR.doubleValue());

		// 应付额
		double payReal = orderinfo.getPrice();
		csOrder.setCsoPayReal(payReal);

		// 计算度设置订单的红包
		double payCoin = Math.min(payReal, orderinfo.usecoin);
		//订单簇，只能使用账户余额支付
		if(isOrderCluster(csOrder)){
			payCoin = 0d;
		}
		
		orderinfo.setUsecoin(payCoin);// 把实付红包改回去
		payReal = payReal - payCoin;
		csOrder.setCsoPayReal(payReal);// 实付额不应包含红包

		String strDescript = $.date(new Date(), "yyyy-MM-dd HH:mm:ss") + "结算订单,订单费用" + $(orderinfo.price) + "元,其中(";
		if (orderinfo.kilom > 0)
			strDescript += " 电里程费" + orderinfo.kilom + "元 ";
		if (orderinfo.mileage > 0)
			strDescript += " 油里程费" + orderinfo.mileage + "元 ";
		if (orderinfo.rent > 0)
			strDescript += " 租金费用" + orderinfo.rent + "元 ";
		if (orderinfo.insure > 0)
			strDescript += " 免责费用" + orderinfo.insure + "元 ";
		if (orderinfo.timeout > 0)
			strDescript += " 超时费用" + orderinfo.timeout + "元 ";
		if (orderinfo.driver > 0)
			strDescript += " 陪驾费用" + orderinfo.driver + "元 ";
		strDescript += ")";

		// 扣除会员帐户余额
		CsMember csMember = csMemberDao.getCsMemberById(csOrder.getCsoPayMember());
		Double payCoupon = $(Math.min(csMember.getCsmCoupon(), payReal));// 现金券支付(不能支付里程费)
		if (payCoupon < 0)
			payCoupon = 0d;
		
		Double payMoney = $(payReal - payCoupon);// 余额支付数

		Double canMoney = commonMoneyService.getUsableAmount(csOrder.getCsoPayMember());
		CsCreditCard creditCard = csOrder.get$csoCreditCard();// 信用卡支付
		Boolean payByCreditCard = false;// 最终是否以信用卡支付

		// 如果指定以信用卡支付，并且余额不够支付的情况下，以信用方式支付
		if (creditCard != null && canMoney < payReal && payReal > 0d) {
			payCoupon = 0d;
			payMoney = payReal;
			Long creditId = commonMoneyService.payCredit(csOrder.getCsoHost(), csMember.getCsmId(), csOrder.getCsoCreditCard(), payReal, "订单消费",
					csOrder.getCsoId(), null, null);
			csOrder.setCsoCreditBill(creditId);
			payByCreditCard = true;
		} else {
			if (creditCard != null) {// 如果是信用订单，生成一条0月帐单
				csOrder.setCsoCreditBill(commonMoneyService.payCredit(csOrder.getCsoHost(), csMember.getCsmId(), csOrder.getCsoCreditCard(), 0, "订单消费",
						csOrder.getCsoId(), null, null));
			}

			if (payCoupon > 0) {// 现金券支付
				commonMoneyService.updateMoney(csOrder.getCsoHost(), csMember.getCsmId(), ICommonMoneyService.MoneyType.Coupon, -payCoupon,
						SYSTEM.RecordType.订单消费, strDescript, csOrder.getCsoId(), csOrder.getCsoId(), CsOrder.class);
			}
			if (payMoney > 0) {
				commonMoneyService.updateMoney(csOrder.getCsoHost(), csMember.getCsmId(), ICommonMoneyService.MoneyType.Money, -payMoney,
						SYSTEM.RecordType.订单消费, strDescript, csOrder.getCsoId(), csOrder.getCsoId(), CsOrder.class);
			}
			payByCreditCard = false;
		}

		if (payReal > 0)
			commonMoneyService.updateIntegralByRule(csOrder.getCsoHost(), csMember.getCsmId(), csOrder.getCsoPayReal(), IntegralType.消费得积分,
					"车纷享会员每消费1元即1点积分。（消费指实际租金和实际里程的费用，不含超时费用）", csOrder.getCsoId());

		if (payReal > 0)
			CommonMoneyService.getBean().updateGrow(csMember.getCsmId(), GrowRecordType.订单消费, csOrder.getCsoId(), payReal);

		if (csOrder.get$csoModel().getCscmType().shortValue() == 1)
			CommonMoneyService.getBean().updateGrow(csMember.getCsmId(), GrowRecordType.电动车租用, csOrder.getCsoId());

		if (orderinfo.timeout > 0)
			CommonMoneyService.getBean().updateGrow(csMember.getCsmId(), GrowRecordType.还车超时, csOrder.getCsoId());

		csOrder.setCsoPayMoney(payMoney);
		csOrder.setCsoPayCoupon(payCoupon);
		csOrder.setCsoPayCoin(orderinfo.usecoin);

		// 设置订单冗余支付项
		csOrder.setCsoPayKilom(orderinfo.kilom);
		csOrder.setCsoPayMileage(orderinfo.mileage);
		csOrder.setCsoPayDriver(orderinfo.driver);
		csOrder.setCsoPayInsure(orderinfo.insure);
		csOrder.setCsoPayTimeout(orderinfo.timeout);
		csOrder.setCsoPayRent(orderinfo.rent);
		csOrder.setCsoEndTime(new Date());

		csOrder.setCsoStatus((short) 4);// 设置订单为已完成

		csOrder.setCsoRemark("请输入备注信息");

		if (LoginHelper.getLogin() != null)
			csOrder.setCsoEditor(LoginHelper.getLoginId());

		// 如果订单网点和取车网点不一样
		if (csOrder.getCsoOutlets().longValue() != csOrder.getCsoOutletsRet().shortValue()) {
			// 更新还车确认状态为未确认
			TbAbAffirm tbAbAffirm = TbAbAffirm.where().tbaaOrder(csOrder.getCsoId()).get();
			if (tbAbAffirm != null) {
				new TbAbAffirm(tbAbAffirm.getTbaaId()).tbaaRealTime(csOrder.getCsoRetTime()).tbaaStatus((short) 1).update();
			}
		}

		csOrderDao.updateCsOrder$NotNull(csOrder);// 保存订单到数据库

		// 保存订单信息
		this.saveOrderInfos(csOrder.getCsoId(), orderinfo);
		
		//订单簇 信息同步
		this.syncOrderCluster(csOrder);

		Boolean bUnSendSms = false;
		try {
			bUnSendSms = $.getBoolean("checkbox", false);
		} catch (Exception ex) {
			bUnSendSms = false;
		}

		// 更新相关的企业订单
		CsUnitOrder unitOrder = CsUnitOrder.Get($.add(CsUnitOrder.F.csuoOrder, csOrder.getCsoId()));
		boolean isUnitOrder = unitOrder!=null && unitOrder.getCsuoId() != null;
		
		// 发送短信
		if (!bUnSendSms && (csOrder.getCsoMask() & 2) == 0) {
			UtilHelper.sendTemplateSMS(
					csOrder.getCsoHost(),
					//payByCreditCard ? "SETTLE_ORDER_BY_CREDIT" : "SETTLE_ORDER",
					isUnitOrder ? "SETTLE_UNIT_ORDER" : "SETTLE_ORDER",	//公务订单短信和个人订单短信分开
					csOrder.getCsoMobile(),
					"您在{time}还车成功，定单[{order}]结算费用为{money}元,其中电里程费{kilom}元,油里程费{mileage}元,租金费用{rent}元,免责费用{insure}元,超时费用{timeout}元。[车纷享]",
					SMSType.通知类短信,
					$.add("time", $.date(csOrder.getCsoRetTime(), "MM月dd日HH时mm分")).add("order", csOrder.getCsoId().toString())
							.add("money", $(csOrder.getCsoPayReal()+csOrder.getCsoPayCoin()).toString()).add("kilom", $(orderinfo.kilom).toString())
							.add("mileage", $(orderinfo.mileage).toString()).add("course", $(orderinfo.mileage + orderinfo.kilom).toString())
							.add("rent", $(orderinfo.rent).toString()).add("insure", $(orderinfo.insure).toString())
							.add("timeout", $(orderinfo.timeout).toString()));
			// 发送微信消息
			com.ccclubs.helper.WeixinHelper.sendWxTemplateMessage(csOrder.getCsoUseMember(), WxTemplateMsgType.订单结算, CsOrder.class, csOrder.getCsoId());
			
			if(orderinfo.discountAmount >0 ){
				UtilHelper.sendTemplateSMS(
						csOrder.getCsoHost(),
						"SETTLE_ACTIVITY_ORDER" ,
						csOrder.getCsoMobile(),
						"尊敬的会员，因您的订单满足{promotion}，订单原费用为{payNeed}元，优惠后实际支付{money}元，感谢您的使用，如有问题，请联系客服400-666-9806",
						SMSType.通知类短信,
						$
						.add("payNeed", orderinfo.getPayNeed().toString())
						.add("promotion", orderinfo.promotionText)
						.add("money", $(csOrder.getCsoPayReal()+csOrder.getCsoPayCoin()).toString())
						);
			}
		}
		
		// 给后勤发送短信
		/*try{
			CsArgument csArgument = csArgumentService.getCsArgument($.add(CsArgument.F.csaFlag,ArgumentKey.MISSIONS_SMS_FLAG));

			if(csArgument != null && Boolean.parseBoolean(csArgument.getCsaFlag())){
				ISrvUserExpService srvUserExpService = $.GetSpringBean("srvUserExpService");
				List<SrvUserExp> srvUserExpList = srvUserExpService.getSrvUserExpList($.add("sueOutlets", csOrder.getCsoOutlets()), -1);
				if(!CollectionUtils.isEmpty(srvUserExpList)){
					for(SrvUserExp user : srvUserExpList){
						String mobile = user.get$sueUser().getSuMobile();
						UtilHelper.sendTemplateSMS(
								csOrder.getCsoHost(),
								"RETCAR_SRVUSER",
								mobile,
								"{carNo}车辆已在{retTime}还入{retOutletsName}网点，请前往检查车辆环境并清洁，同时保证车辆处于充电状态。若车辆出现破损，请拍照记录并联系客服反映情况。客服电话XXXX",
								SMSType.通知类短信,
								$.add("carNo", csOrder.get$csoCar().getCscCarNo())
								 .add("retTime", $.date(csOrder.getCsoRetTime(), "MM月dd日HH时mm分"))
								 .add("retOutletsName", csOrder.get$csoOutletsRet().getCsoName()));
					}
				}
			}
		}catch(Exception e){
			Logger.getRootLogger().error("给后勤发送短信异常:", e);
		}*/
		

		// 完成定单的订单调度
		SchedulingService.finishScheduling(csOrder.getCsoId());
		
		if (unitOrder != null) {
			CommonUnitService.getBean().executeComplete(unitOrder.getCsuoId());
		}

		com.ccclubs.helper.TimeLineHelper.update(csOrder.getCsoId());

		//修改车辆状态以及车辆网点地址 订单处于0,7 车辆网点修改为 订单的取车网点 其他订单状态 车辆的网点修改为还车网点 同时修改车辆状态为 上线
		if (csOrder.getCsoOutlets().longValue() != csOrder.getCsoOutletsRet().shortValue()) {
			CsCar csCar = new CsCar(csOrder.getCsoCar());
			csCar.setCscOutlets(csOrder.getCsoStatus()==0||csOrder.getCsoStatus() ==7 || csOrder.getCsoStatus() == 3? csOrder.getCsoOutlets(): csOrder.getCsoOutletsRet());
			csCar.update();
			
			SchedulingService.onlineCar(csOrder.getCsoCar(),(short)1,"异借还车订单结算车辆自动上线 ",null);
		}
		return csOrderDao.getCsOrderById(csOrder.getCsoId());
	}
	
	public void syncOrderCluster(CsOrder csOrder){
		//订单簇，保证金扣减，如果是子订单全部完成，则订单状态变成"完成"
		if(!isOrderCluster(csOrder)){
			return;
		}
		
		CsOrderCluster coc = CsOrderCluster.Get($.add(CsOrderCluster.F.csocId, csOrder.getCsoSrc().replace("CsOrderCluster@", "")));
		
		if(coc !=null){
			String subOrders = coc.getCsocSubOrders();
			if(subOrders.startsWith(",")){
				subOrders = subOrders.substring(subOrders.indexOf(",")+1, subOrders.length());
			}else if(subOrders.endsWith(",")){
				subOrders = subOrders.substring(0, subOrders.length()-1);
			}
			List<CsOrder> orderList = CsOrder.getCsOrderList($.add("definex", " cso_id in ("+subOrders+")"), -1);
			if(orderList!=null){
				int statusCount = 0; 
				
				//订单簇状态（0:已预定 1:使用中  3:已取消 4:已完成）
				for(CsOrder order : orderList){
					statusCount += order.getCsoStatus().intValue();
				}
				
				if(statusCount == orderList.size() * 4){ //全部完成
					coc.setCsocStatus((short)4);		 //完成
				}else{
					coc.setCsocStatus((short)1);		 //使用中
				}
				
				//每结算一笔子订单，从保证金里面减掉结算金额
				coc.setCsocMarginNeed(coc.getCsocMarginNeed() - csOrder.getCsoPayReal());
				coc.setCsocUpdateTime(new Date()); 
				coc.update();
			}
		}
	}

	/**
	 * 撤销订单
	 */
	public void executeUndoOrder(Long orderid, String csoRemark, From from, String src) {

		SrvLock.lock(SrvLock.LockType.订单事务锁, "撤销订单" + orderid);

		new CsOrderLog(// 添加订单日志
				"撤销订单"// 操作项目 [非空]
				, orderid// 所属订单 [非空]
				, LoginHelper.getLoginId()// 操作人 [非空]
				, LoginHelper.getLoginId() != null ? null : CsOrder.get(orderid).getCsoUseMember(), (short) from.ordinal(), src, csoRemark// 备注信息
				, new Date()// 添加时间 [非空]
		).save();

		CsOrder csOrder = CsOrder.get(orderid);
		
		//定单簇单判断
		if(isOrderCluster(csOrder))
			throw new MessageException(ErrorCode.ORDER_FLAG_ERROR, "定单簇订单不能撤销");
		
		// 订单的状态改为已撤销
		csOrderDao.updateCsOrder$NotNull(new CsOrder()
				.csoId(orderid)
				.csoStatus((short) 7)
				.csoState((short) 2)
				.csoSerial(
						csOrder.getCsoUseMember() + "-" + csOrder.getCsoCar() + "-" + csOrder.getCsoStartTime().getTime() + "-" + csOrder.getCsoStatus() + "-"
								+ Calendar.getInstance().get(Calendar.MINUTE))// 订单序列号=会员ID+车辆ID+订单开始时间+状态
				);

		// 回滚之前冻结的免费小时、优惠项
		this.rollbackFreeHour$Gift$Coin(orderid);
		// 删除之前保存的订单详情
		List<CsOrderDetail> details = csOrderDetailDao.getCsOrderDetailList($.add("csodOrder", orderid), -1);
		for (CsOrderDetail csOrderDetail : details)
			csOrderDetailDao.deleteCsOrderDetailById(csOrderDetail.getCsodId());

		// 撤销定单的订单调度
		SchedulingService.cancelScheduling(orderid);

		// 更新相关的企业订单
		CsUnitOrder unitOrder = CsUnitOrder.Get($.add(CsUnitOrder.F.csuoOrder, orderid));
		if (unitOrder != null) {
			CommonUnitService.getBean().executeComplete(unitOrder.getCsuoId());
		}
		//修改车辆状态 上线
		if (csOrder.getCsoOutlets().longValue() != csOrder.getCsoOutletsRet().shortValue()) {
			SchedulingService.onlineCar(csOrder.getCsoCar(),(short)1,"异借还车订单作废车辆自动上线",null);
		}
		com.ccclubs.helper.TimeLineHelper.update(csOrder.getCsoId());

	}

	/**
	 * 回滚订单
	 * 
	 * @param csoId
	 * @param csoRemark
	 */
	public void executeRollbackOrder(Long orderid, String csoRemark, From from, String src) {

		throw new RuntimeException("回滚未实现");

		/*
		 * String strError = canRollbackOrder(orderid); if(!$.empty(strError)){
		 * throw new MessageException(ErrorCode.ORDER_CANOT_ROLLBACK,strError);
		 * }
		 * 
		 * SrvLock.lock(SrvLock.LockType.订单事务锁,"回滚订单"+orderid); new
		 * CsOrderLog(//添加订单日志 "回滚订单"//操作项目 [非空] ,orderid//所属订单 [非空]
		 * ,LoginHelper.getLoginId()//操作人 [非空] ,csoRemark//备注信息 ,new
		 * Date()//添加时间 [非空] ).save();
		 * 
		 * 
		 * //成长记录
		 * CsGrowRecord.where().csgrRelate("CsOrder@"+orderid).get().delete();
		 * 
		 * //积分记录 CsIntegralRecord.where().csrRelate(orderid).get().delete();
		 * 
		 * //消费记录 CsRecord.where().csrOrder(orderid).get().delete();
		 * 
		 * //收支记录 CsOperRecord.where().csorOrder(orderid).get().delete();
		 * 
		 * //信用卡 CsCreditBill.where().cscbOrder(orderid).get().delete();
		 * 
		 * //会员余额
		 * 
		 * 
		 * //订单状态 new CsOrder(orderid).csoStatus((short)5).update();
		 */

	}

	/**
	 * 查询是否可以回滚订单
	 * 
	 * @param orderid
	 * @return
	 */
	public String canRollbackOrder(Long orderid) {

		return null;
	}

	/**
	 * 保存订单信息：优惠使用记录、
	 * 
	 * @param orderinfo
	 * @param b
	 */
	private void saveOrderInfos(Long orderid, OrderInfo orderinfo) {

		CsOrder order = csOrderDao.getCsOrderById(orderid);

		// 回滚之前冻结的免费小时、优惠项
		this.rollbackFreeHour$Gift$Coin(orderid);

		// 保存优惠项
		for (CsUseRecord userecord : orderinfo.getGifts()) {
			// 扣除优惠项
			csGiftDao.updateCsGiftByConfirm($.add("definex", "csg_remain=csg_remain-" + userecord.getCsurCount()),
					$.add("csgId", userecord.getCsurGift()).add("confirm", 1));
			userecord.setCsurHost(order.getCsoHost());
			userecord.setCsurOrder(orderid);
			csUseRecordDao.saveCsUseRecord(userecord);
		}

		// 保存免费小时使用记录，并扣除
		List<CsFreeHour> freehours = commonMoneyService.getCanUseFreeHourList(order.getCsoPayMember(), order.getCsoStartTime());
		Double payFreehour = orderinfo.getFreehour();
		for (CsFreeHour freehour : freehours) {
			if (payFreehour <= 0)
				break;
			Double count = Math.min(payFreehour, freehour.getCsfhRemain());
			if (count <= 0)
				continue;
			csFreeHourDao.updateCsFreeHourByConfirm($.add("definex", "csfh_remain=csfh_remain-" + count),
					$.add("csfhId", freehour.getCsfhId()).add("confirm", 1));
			csUseRecordDao.saveCsUseRecord(new CsUseRecord(order.getCsoHost(), "订单消费抵用", order.getCsoPayMember(), freehour.getCsfhId(), null, null, orderid,
					count, new Date()));
			payFreehour -= count;
		}

		// 只有订单状态是 已取消和已完成的才可以扣除红包
		if(order.getCsoStatus() == 3 || order.getCsoStatus() == 4 ){
			//保存红包的使用记录，并扣除
			List<CsCoin> coins = commonMoneyService.getCanUseCoinList(order.getCsoHost(), order.getCsoPayMember(), order.getCsoStartTime());
			Double payCoin = orderinfo.getUsecoin();
			for (CsCoin coin : coins) {
				if (payCoin <= 0)
					break;
				Double count = Math.min(payCoin, coin.getCscRemain());
				if (count <= 0)
					continue;
				ICsCoinDao csCoinDao = $.getBean("csCoinDao");
				csCoinDao.updateCsCoinByConfirm($.add("definex", "csc_remain=csc_remain-" + count), $.add("cscId", coin.getCscId()).add("confirm", 1));
				csUseRecordDao.saveCsUseRecord(new CsUseRecord(order.getCsoHost(), "订单消费抵用", order.getCsoPayMember(), null, coin.getCscId(), null, orderid, count,
						new Date()));
				payCoin -= count;
			}
		}
		// 删除之前保存的订单详情
		List<CsOrderDetail> details = csOrderDetailDao.getCsOrderDetailList($.add("csodOrder", orderid), -1);
		for (CsOrderDetail csOrderDetail : details)
			csOrderDetailDao.deleteCsOrderDetailById(csOrderDetail.getCsodId());
		// 保存当前订单详情
		for (CsOrderDetail csOrderDetail : orderinfo.getDetails()) {
			csOrderDetail.setCsodOrder(orderid);
			csOrderDetailDao.saveCsOrderDetail(csOrderDetail);
		}

	}

	/**
	 * 回滚订单的优惠项以及免费小时
	 * 
	 * @param orderid
	 */
	private void rollbackFreeHour$Gift$Coin(Long orderid) {
		List<CsUseRecord> records = csUseRecordDao.getCsUseRecordList($.add("csurOrder", orderid), -1);
		for (CsUseRecord record : records) {
			if (record.getCsurFreeHour() != null) {
				csFreeHourDao.updateCsFreeHourByConfirm($.add("definex", "csfh_remain=csfh_remain+" + record.getCsurCount()),
						$.add("csfhId", record.getCsurFreeHour()).add("confirm", 1));
				CsFreeHour freehour = CsFreeHour.get(record.getCsurFreeHour());
				if (freehour.getCsfhRemain() > freehour.getCsfhCount())
					throw new ErrorException("rollback-freehour-error", "回滚订单免费小时出错", $.json(freehour));
			} else if (record.getCsurGift() != null) {
				csGiftDao.updateCsGiftByConfirm($.add("definex", "csg_status=1,csg_remain=csg_remain+" + record.getCsurCount()),
						$.add("csgId", record.getCsurGift()).add("confirm", 1));
				CsGift gift = CsGift.get(record.getCsurGift());
				if (gift.getCsgRemain() > gift.getCsgCount())
					throw new ErrorException("rollback-error", "回滚订单的优惠项时出错", $.json(gift));
			} else if (record.getCsurCoin() != null) {
				ICsCoinDao csCoinDao = $.GetSpringBean("csCoinDao");
				csCoinDao.updateCsCoinByConfirm($.add("definex", "csc_status=1,csc_remain=csc_remain+" + record.getCsurCount()),
						$.add("cscId", record.getCsurCoin()).add("confirm", 1));
				CsCoin coin = CsCoin.get(record.getCsurCoin());
				if (coin.getCscRemain() > coin.getCscCount())
					throw new ErrorException("rollback-error", "回滚订单的纷享时出错", $.json(coin));
			}
			csUseRecordDao.deleteCsUseRecordById(record.getCsurId());
		}
	}

	/**
	 * 设置秒为0
	 * 
	 * @return
	 */
	public Date resetSecond(Date time) {
		return $.date($.date(time, "yyyy-MM-dd HH:mm:00"), "yyyy-MM-dd HH:mm:00");
	}

	public Double $(Double value) {
		return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public ICommonOrderService getCommonOrderService() {
		return commonOrderService;
	}

	public void setCommonOrderService(ICommonOrderService commonOrderService) {
		this.commonOrderService = commonOrderService;
	}

	public ICommonMoneyService getCommonMoneyService() {
		return commonMoneyService;
	}

	public void setCommonMoneyService(ICommonMoneyService commonMoneyService) {
		this.commonMoneyService = commonMoneyService;
	}

	public ICsOrderDao getCsOrderDao() {
		return csOrderDao;
	}

	public void setCsOrderDao(ICsOrderDao csOrderDao) {
		this.csOrderDao = csOrderDao;
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

	public ICsGiftDao getCsGiftDao() {
		return csGiftDao;
	}

	public void setCsGiftDao(ICsGiftDao csGiftDao) {
		this.csGiftDao = csGiftDao;
	}

	public ICsCarDao getCsCarDao() {
		return csCarDao;
	}

	public void setCsCarDao(ICsCarDao csCarDao) {
		this.csCarDao = csCarDao;
	}

	public ICsMemberDao getCsMemberDao() {
		return csMemberDao;
	}

	public void setCsMemberDao(ICsMemberDao csMemberDao) {
		this.csMemberDao = csMemberDao;
	}

	public ICsOrderDetailDao getCsOrderDetailDao() {
		return csOrderDetailDao;
	}

	public void setCsOrderDetailDao(ICsOrderDetailDao csOrderDetailDao) {
		this.csOrderDetailDao = csOrderDetailDao;
	}

	public ICsArgumentService getCsArgumentService() {
		return csArgumentService;
	}

	public void setCsArgumentService(ICsArgumentService csArgumentService) {
		this.csArgumentService = csArgumentService;
	}

	
	
}
