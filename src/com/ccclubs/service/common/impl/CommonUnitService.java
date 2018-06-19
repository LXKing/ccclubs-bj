package com.ccclubs.service.common.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.ccclubs.action.app.official.util.ThirdPartyApiHelper;
import com.ccclubs.action.unit.UnitLoginHelper;
import com.ccclubs.config.ArgumentKey;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.config.SYSTEM.GrowRecordType;
import com.ccclubs.exception.MessageException;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.UnitHelper;
import com.ccclubs.helper.UnitHelper.UnitOrderInfo;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.model.CsArgument;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsDriverCar;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsSpecialCar;
import com.ccclubs.model.CsUnitInfo;
import com.ccclubs.model.CsUnitOrder;
import com.ccclubs.model.CsUnitPerson;
import com.ccclubs.service.admin.ICsArgumentService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsUnitOrderService;
import com.ccclubs.service.common.From;
import com.ccclubs.service.common.ICommonDisposeService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonOrderService;
import com.ccclubs.service.common.ICommonUnitService;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.lazy3q.web.helper.$;

public class CommonUnitService implements ICommonUnitService {
	
	ICommonOrderService commonOrderService;
	ICommonMoneyService commonMoneyService;
	ICommonDisposeService commonDisposeService;
	ICsUnitOrderService csUnitOrderService;
	ICsOrderService csOrderService;
	ICsArgumentService csArgumentService;
	
	public static ICommonUnitService getBean(){
		return $.getBean("commonUnitService");
	}
	
	
	/**
	 * 专车下单
	 * @param personId 人员ID
	 * @param profile 订单摘要
	 * @param start 开始时间
	 * @param finish 结束时间
	 * @param type 类型：1:一般公务 2:紧急公务
	 * @param startPoint 起点位置
	 * @param finishPoint 终点位置
	 * @param startAddr 起点地址
	 * @param finishAddr 终点地址
	 * @param clientMessage 客户留言
	 * @return
	 * @throws MessageException
	 */
	public CsUnitOrder executeAddSpecialOrder(
			Long personId,String profile,
			Date start,Date finish,Short type,
			String startPoint,String finishPoint,
			String startAddr,String finishAddr,
			String clientMessage,
			Long carLevel
		){
		return executeAddSpecialOrder(personId, profile, start, finish, type, startPoint, finishPoint, startAddr, finishAddr, clientMessage, carLevel, null, null);
		
	};

	@Override
	public CsUnitOrder executeAddSpecialOrder(Long personId, String profile,
			Date start, Date finish, Short type, String startPoint,
			String finishPoint, String startAddr, String finishAddr,
			String clientMessage, Long carLevel, String mobile, Long third)
			throws MessageException {
		CsUnitPerson csUnitPerson = CsUnitPerson.get(personId);
		CsUnitInfo unitInfo = csUnitPerson.get$csupInfo();
		CsMember userMember = csUnitPerson.get$csupMember();
		Long payMember = UnitHelper.getPayIdByUseId(unitInfo.getCsuiId(), userMember.getCsmId());
		
		CsSpecialCar csSpecialCar = new CsSpecialCar(
			csUnitPerson.getCsupHost()//城市 [非空]
		 	,unitInfo.getCsuiId()//企业信息 [非空]
		 	,csUnitPerson.getCsupId()//用车人员 [非空]
		 	,0l//企业订单 [非空]
		 	,"匿名"//乘客姓名
		 	,StringUtils.isEmpty(mobile) ? userMember.getCsmMobile() : mobile//乘客电话
		 	,1//乘车人数 [非空]
		 	,carLevel//预定车型
		 	,null//车牌号码
		 	,null//司机姓名
		 	,null//司机电话
		 	,null//司机照片
		 	,null//车辆颜色
		 	,null//车型信息
		 	,clientMessage//客户留言
		 	,startPoint//起点位置 [非空]
		 	,startAddr//起点地址
		 	,start//开始时间 [非空]
		 	,finishPoint//终点位置 [非空]
		 	,finishAddr//终点地址
		 	,finish//结束时间
		 	,0l//订单时长
		 	,0l//实际时长
		 	,0//公里数
		 	,(short)0//用车评分 [非空]
		 	,null//用车评价
		 	,third//三方名称
		 	,null//三方订单号
		 	,0d//三方费用
		 	,null//三方费用信息
		 	,0d//三方其它费用
		 	,null//三方其它费用描述
		 	,new Date()//修改时间 [非空]
		 	,new Date()//添加时间 [非空]
		 	,null//响应状态
		 	,(short)0//状态 [非空]
		 	,(short)0//是否拼车
		 	,null
		).save();
		
		//根据专车订单的奇偶随机判单csSpecialCar.getCsscId()%2+
		//long third = 1L;
		//if(ApiBase.useYidao){
		//	third = csSpecialCar.getCsscId()%2+1;
		//}
		//new CsSpecialCar(csSpecialCar.getCsscId()).csscThird(third).update();
		
		CsUnitOrder csUnitOrder = new CsUnitOrder(
			unitInfo.getCsuiHost()//城市 [非空]
		 	,0l//系统订单 [非空]
		 	,csSpecialCar.getCsscId()
		 	,unitInfo.getCsuiId()//所属单位 [非空]
		 	,csUnitPerson==null?null:csUnitPerson.getCsupGroup()//所属分组
		 	,payMember//支付帐号 [非空]
		 	,userMember.getCsmId()//使用帐号 [非空]
		 	,null
		 	,null//车牌号码
		 	,type//订单类型 [非空]
		 	,personId//用车人员
		 	,csUnitPerson.getCsupName()//使用人姓名
		 	,null//别名或标记
		 	,userMember.getCsmMobile()//使用人手机
		 	,profile
		 	,userMember.get$csmInfo().getCsmiCertifyNum()//身份证号
		 	,start//订单开始时间 [非空]
		 	,finish//订单结束时间 [非空]
		 	,null//实际还车时间
		 	,null//免费小时(代理)
		 	,null//免费里程(代理)
		 	,null//天租价格(代理)
		 	,null//小时价格(代理)
		 	,null//里程价格(代理)
		 	,null//应付金额(代理)
		 	,null//实付金额(代理)
		 	,null//订单备注
		 	,new Date()//添加时间
		 	,new Date()//修改时间
		 	,0d//里程数		 	
		 	,0d//总费用
		 	,(short)0
		 	,null
		 	,null//结账单
		 	,SystemHelper.getAplication()
		 	,null//审核时间
		 	,null//审核人
		 	,(short)1//审核状态
		 ).save();
	
		 //把企业订单号返写到专车订单中
		 new CsSpecialCar(csSpecialCar.getCsscId()).csscUnitOrder(csUnitOrder.getCsuoId()).update();
	
		return csUnitOrder;
	}


	/**
	 * 公务用车下单
	 * @param personId 人员ID
	 * @param profile 用车用途(可以为空)
	 * @param carId 车辆ID
	 * @param start 开始时间
	 * @param finish 结束时间
	 * @param type 类型：1:一般公务 2:紧急公务
	 * @return
	 * @throws MessageException
	 */
	@Override
	public CsUnitOrder executeAddOfficialOrder(Long personId,String profile, Long carId,Date start, Date finish,Short type) throws MessageException {
		CsUnitPerson csUnitPerson = CsUnitPerson.get(personId);
		CsMember userMember = csUnitPerson.get$csupMember();
		return this.executeAddUnitOrder(csUnitPerson.getCsupInfo(),personId, userMember.getCsmId(), userMember.getCsmName(), userMember.getCsmMobile(),profile,userMember.get$csmInfo().getCsmiCertifyNum(), carId, start, finish, type, null, null, null, null, null);
	}

	/**
	 * 添加企业订单
	 * @param unid 企业ID
	 * @param groupId 企业组ID
	 * @param memberId 用车帐号ID
	 * @param name 用车人姓名
	 * @param mobile 用车人手机
	 * @param profile 用车用途
	 * @param idcard 身份证号
	 * @param carId 车辆ID
	 * @param start 开始时间
	 * @param finish 结束时间
	 * @param type 订单类型
	 * @param dayPrice 天租价格
	 * @param hourPrice 小时价格
	 * @param kmPrice 里程价格
	 * @return
	 * @throws MessageException
	 */
	@Override
	public CsUnitOrder executeAddUnitOrder(
			Long unitId,Long personId,
			Long memberId,String name, String mobile,
			String profile,String idcard, 
			Long carId, Date start, Date finish,
			Short type,Double dayPrice,Double hourPrice,Double kmPrice, Long takeOutlets, Long retOutlets
		)throws MessageException {
		
		CsUnitInfo unitInfo = CsUnitInfo.get(unitId);
		Long payMember = UnitHelper.getPayIdByUseId(unitId, memberId);
		CsUnitPerson csUnitPerson = CsUnitPerson.where().csupInfo(unitId).csupMember(memberId).get();
		CsCar csCar = CsCar.get(carId);
		
		$.setLocal(ICommonDisposeService.DONOTSENDSMS, true);
//		CsOrder csOrder = commonDisposeService.executeSaveOrder(
//				payMember, 
//				memberId, 
//				mobile,
//				carId, 
//				start, 
//				finish,	
//				unitInfo.getCsuiFeeType(),
//				null,//默认按里程免责 
//				null,
//				null,
//				null,
//				0l,
//				From.企业,
//				UnitLoginHelper.getLoginAt()
//		);
		
		CsOrder csOrder =  commonDisposeService.executeSaveOrder(
    		   payMember, memberId, null, carId, 
    		   takeOutlets, retOutlets, start, finish, unitInfo.getCsuiFeeType(), null, 
    		   null, null, null, 0l, From.企业, UnitLoginHelper.getLoginAt());
		//设置该订单不发送还车短信
		new CsOrder().csoId(csOrder.getCsoId()).csoMask(csOrder.getCsoMask()|2).update();
		//公务订单时，订单数据状态设置为不发送
		if(unitInfo.getCsuiType().shortValue()==2){
			new CsOrder().csoId(csOrder.getCsoId()).csoState((short)3).update();
		}
		
		//公务订单时，人员信息以使用帐号信息为准
		if(unitInfo.getCsuiType().shortValue()==2){
			CsMember useMember = CsMember.get(memberId);
			name = useMember.getCsmName();
			mobile = useMember.getCsmMobile();			
		}		
		
		CsUnitOrder csUnitOrder = new CsUnitOrder(
			unitInfo.getCsuiHost()//城市 [非空]
		 	,csOrder.getCsoId()//系统订单 [非空]
		 	,0l
		 	,unitId//所属单位 [非空]
		 	,csUnitPerson==null?null:csUnitPerson.getCsupGroup()//所属分组
		 	,payMember//支付帐号 [非空]
		 	,memberId//使用帐号 [非空]
		 	,carId
		 	,CsCar.get(carId).getCscCarNo()
		 	,type//订单类型 [非空]
		 	,personId//用车人员
		 	,name//使用人姓名
		 	,null//别名或标记
		 	,mobile//使用人手机
		 	,profile
		 	,idcard//身份证号
		 	,csOrder.getCsoStartTime()//订单开始时间 [非空]
		 	,csOrder.getCsoFinishTime()//订单结束时间 [非空]
		 	,null//实际还车时间
		 	,$.getInteger("csuoFreeHour")//免费小时(代理)
		 	,$.getDouble("csuoFreeKm")//免费里程(代理)
		 	,dayPrice//天租价格(代理)
		 	,hourPrice//小时价格(代理)
		 	,kmPrice//里程价格(代理)
		 	,0d//应付金额(代理)
		 	,0d//实付金额(代理)
		 	,null//订单备注
		 	,new Date()//添加时间
		 	,new Date()//修改时间
		 	,0d//里程数
		 	,0d//总费用
		 	,(short)0
		 	,null
		 	,null//结账单
		 	,SystemHelper.getAplication()
		 	,null//审核时间
		 	,null//审核人
		 	,unitInfo.getCsuiType().shortValue()==2?(short)1:(short)2//审核状态  
		 );
		//official后台添加人员选择订单是否需要审批权限
		CsArgument argument = csArgumentService.getCsArgument($.add(CsArgument.F.csaFlag,ArgumentKey.ORDER_APPROVER_POWER));
		if(argument != null && Boolean.parseBoolean(argument.getCsaFlag())){
			if(csUnitPerson.getCsupFlag() != null && csUnitPerson.getCsupFlag().indexOf("3")!=-1){
				csUnitOrder.setCsuoState((short)2);
				new com.ccclubs.model.CsOrder(csUnitOrder.getCsuoOrder()).csoState((short)0).update();
			}
		}
		
		UnitOrderInfo info = UnitHelper.getUnitOrderInfo(
				unitId,
				carId,
				csUnitOrder.getCsuoStartTime(),
				csUnitOrder.getCsuoFinishTime(),
				csUnitOrder.getCsuoRetTime(),
				csUnitOrder.getCsuoFreeHour(),
				null,
				csUnitOrder.getCsuoFreeKm()
			);
		csUnitOrder.setCsuoPayNeed((info.getTotal() == null || info.getTotal().intValue() == 0) ? csOrder.getCsoPayNeed() : info.getTotal());
		csUnitOrder.setCsuoDayPrice(info.getDayPrice());
		csUnitOrder.setCsuoHourPrice(info.getHourPrice());
		csUnitOrder.setCsuoKmPrice(info.getMileage());
		
		CsUnitOrder unitOrder = csUnitOrderService.saveCsUnitOrder(csUnitOrder);
								
		//发送下单成功短信
		if(!$.empty(csUnitOrder.getCsuoMobile()) && unitInfo.getCsuiType().shortValue()!=2){
			UtilHelper.sendTemplateSMS(unitInfo.getCsuiHost(),"ADD_UNIT_ORDER",
				csUnitOrder.getCsuoMobile(), 
				"您的订单[{order}]下单成功,取车时间[{time}],车牌号[{number}],取车点[{outlets}],授权码{code},祝您用车愉快", 
				SMSType.通知类短信,
				$.add("time", $.date(csUnitOrder.getCsuoStartTime(), "MM月dd日HH时mm分"))
				.add("order", csUnitOrder.getCsuoOrder().toString())
				.add("number", csCar.getCscNumber())
				.add("code", csUnitOrder.get$csuoOrder().getCsoCode())
				.add("outlets", csCar.get$cscOutlets().getCsoAddress().toString())
			);
		}
		
		return unitOrder;
	}
	
	
	
	/**
	 * 续订企业订单
	 * @param unitId
	 * @param unitOrderId
	 * @param newFinishTime
	 * @return
	 * @throws MessageException
	 */
	@Override
	public CsUnitOrder executeReUnitOrder(Long unitId, Long unitOrderId,Date newFinishTime) throws MessageException {
		
		//获取当前企业订单
		CsUnitOrder csUnitOrder = CsUnitOrder.get(unitOrderId);
		
		csUnitOrder.setCsuoFinishTime(newFinishTime);
		
		$.setLocal(ICommonDisposeService.DONOTSENDSMS, true);
		CsOrder csOrder = commonDisposeService.executeReOrder(csUnitOrder.getCsuoOrder(), csUnitOrder.getCsuoFinishTime(), null,From.企业,UnitLoginHelper.getLoginAt());
					
		UnitOrderInfo info = UnitHelper.getUnitOrderInfo(
				unitId,
				csUnitOrder.get$csuoOrder().getCsoCar(),
				csUnitOrder.getCsuoStartTime(),
				csUnitOrder.getCsuoFinishTime(),
				csUnitOrder.getCsuoRetTime(),
				csUnitOrder.getCsuoFreeHour(),
				null,
				csUnitOrder.getCsuoFreeKm()
			);
		
		new CsUnitOrder(unitOrderId)
		.csuoFinishTime(csUnitOrder.getCsuoFinishTime())
		.csuoFreeHour(csUnitOrder.getCsuoFreeHour())
		.csuoPayNeed((info.getTotal() == null || info.getTotal().intValue() == 0) ? csOrder.getCsoPayNeed() : info.getTotal())
		.update();
		
		return CsUnitOrder.get(unitOrderId);
	}
	
	
	
	/**
	 * 取消企业订单
	 * @param csuoUnitInfo
	 * @param csuoId
	 */
	@Override
	public void executeCancelUnitOrder(Long unitInfoId, Long unitOrderId,String remark) {
		//获取当前企业订单
		CsUnitOrder csUnitOrder = CsUnitOrder.get(unitOrderId);
		if(csUnitOrder.getCsuoOrder()>0){
			$.setLocal(ICommonDisposeService.DONOTSENDSMS, true);
			commonDisposeService.executeCancelOrder(csUnitOrder.getCsuoOrder(), remark, From.企业, UnitLoginHelper.getLoginAt());
			
			//unitOrder这里是审核通过的，state状态不变
		}else if(csUnitOrder.getCsuoSpecial()>0){
			ThirdPartyApiHelper.cancelOrder(csUnitOrder.getCsuoSpecial());
			
			//未审批的状态下，状态置为取消
			if(csUnitOrder.getCsuoState().intValue()!=2){
				CsUnitOrder.where().csuoId(unitOrderId).set().csuoState(4).update();
			}
		}
	}

	
	/**
	 * 订单审核，返回错误信息，没有错误返回null
	 */
	@Override
	public String executeCheckUnitOrder(Boolean bOk, Long csuoId,Long unitinfo, String remark) {
		CsUnitOrder csUnitOrder = CsUnitOrder.get(csuoId);
		if(csUnitOrder.getCsuoState().shortValue()!=1)
			return "订单不在审核状态，不能审核";
		if(!bOk){//审核不通过
			if(csUnitOrder.getCsuoOrder()>0){
				//修改车辆状态 上线
				if (csUnitOrder.get$csuoOrder().getCsoOutlets().longValue() != csUnitOrder.get$csuoOrder().getCsoOutletsRet().shortValue()) {
					SchedulingService.onlineCar(csUnitOrder.getCsuoCar(),(short)1,"异借还车订单审核不通过车辆自动上线 订单号:"+csUnitOrder.get$csuoOrder().getCsoId$(),null);
				}
				commonDisposeService.executeUndoOrder(csUnitOrder.getCsuoOrder(), remark, From.企业, UnitLoginHelper.getLoginAt());
			}else if(csUnitOrder.getCsuoSpecial()>0){
				CsSpecialCar.where().csscId(csUnitOrder.getCsuoSpecial()).set().csscStatus(6).update();
			}
			CsUnitOrder.where().csuoId(csuoId).set().csuoState(3).update();
		}else{//审核通过
			if(csUnitOrder.getCsuoOrder()>0){
				new com.ccclubs.model.CsOrder(csUnitOrder.getCsuoOrder()).csoState((short)0).update();
			}else if(csUnitOrder.getCsuoSpecial()>0){
				CsSpecialCar.where().csscId(csUnitOrder.getCsuoSpecial()).set().csscStatus(1).update();
				//ThirdPartyApiHelper.createOrder(csUnitOrder.getCsuoMobile(), csUnitOrder.getCsuoSpecial());				
			}
			CsUnitOrder.where().csuoId(csuoId).set().csuoState(2).update();
		}
		return null;
	}

	
	
	@Override
	public void executeComplete(Long id) {
		CsUnitOrder csUnitOrder = CsUnitOrder.get(id);
		if(csUnitOrder==null)return;
		
		//系统订单
		if(csUnitOrder.getCsuoOrder()>0){
			CsOrder csOrder = csUnitOrder.get$csuoOrder();
			CsUnitOrder C_U_O = new CsUnitOrder(id);
			C_U_O.csuoMileage(csOrder.getCsoFuel()+csOrder.getCsoElectric());
			C_U_O.csuoSettlement($.or(csOrder.getCsoPayReal(),0d));
			C_U_O.csuoSettleState((short)1);
			C_U_O.csuoSettleTime(new Date());
			C_U_O.setCsuoPayNeed(csOrder.getCsoPayNeed());
			C_U_O.setCsuoPayReal(csOrder.getCsoPayReal());
			C_U_O.setCsuoFinishTime(csOrder.getCsoFinishTime());
			C_U_O.csuoRetTime(csOrder.getCsoRetTime());
			C_U_O.csuoRetTime(csOrder.getCsoRetTime());
			C_U_O.update();
		}
		
		
		//专车订单
		if(csUnitOrder.getCsuoSpecial()>0){
			CsSpecialCar csSpecialCar = csUnitOrder.get$csuoSpecial();
			CsUnitOrder C_U_O = new CsUnitOrder(id);
			C_U_O.csuoFinishTime(csSpecialCar.getCsscFinishTime());
			C_U_O.csuoRetTime(csSpecialCar.getCsscFinishTime());
			C_U_O.csuoMileage(csSpecialCar.getCsscKilo().doubleValue());
			C_U_O.csuoSettlement(csSpecialCar.getCsscThirdFee()+csSpecialCar.getCsscThirdOtherFee());
			C_U_O.csuoSettleState((short)1);
			C_U_O.csuoSettleTime(new Date());
			C_U_O.update();
		}
		
		/***************************专车订单扣除其支付帐号余额*****************************/
		if(csUnitOrder.getCsuoSpecial()>0){
			CsUnitOrder C_U_O = CsUnitOrder.get(id);
			commonMoneyService.updateMoney(
					C_U_O.getCsuoHost(),
					C_U_O.getCsuoUnitMember(), 
					ICommonMoneyService.MoneyType.Money, 
					-C_U_O.getCsuoSettlement(), 
					SYSTEM.RecordType.专车订单, 
					"专车订单消费",
					null, 
					id,
					CsUnitOrder.class
				);
		}
		/***************************专车订单扣除其支付帐号余额*****************************/
		
	}

	@Override
	public CsUnitOrder executeSettleRentOrder(Long csuoId, Long useMemberId, Long payMemberId, Date finishTime,
			Double mileage, String remark) throws MessageException {
		
		//if(payMemberId == null)payMemberId = useMemberId;
		payMemberId = useMemberId; //默认不允许代付
		
		// ===================== 校验 =====================
		if(csuoId == null || useMemberId == null || mileage == null || finishTime == null){
			throw new MessageException("参数错误，不能完成结算！", 1000);
		}
		
		CsUnitOrder unitOrder = CsUnitOrder.get(csuoId);
		if(unitOrder == null) {
			throw new MessageException("该订单不存在，不能完成结算！", 1001);
		}
		
		CsMember member = CsMember.get(useMemberId);
		if(member == null || member.getCsmStatus().intValue() != 1){
			throw new MessageException("该会员不可用，不能完成结算！", 1002);
		}
		
		if(unitOrder.getCsuoState().intValue() != 2 || $.or(unitOrder.getCsuoSettleState(), 0).intValue() !=0){
			throw new MessageException("该订单状态不正确，不能完成结算！", 1003);
		}
		
		if(finishTime.getTime() < unitOrder.getCsuoStartTime().getTime()){
			throw new MessageException("该订单没还车时间早于订单开始时间，不能完成结算！", 1004);
		}
		
		if(!unitOrder.get$csuoPerson().getCsupMember().equals(useMemberId)){
			throw new MessageException("该订单所属人不符，不能完成结算！", 1004);
		}
		
		// ===================== 扣款 =====================
		Double canMoney = commonMoneyService.getUsableAmount(payMemberId);  //用户可用余额
		com.ccclubs.service.common.impl.UnitOrderInfo orderinfo = execulateCalcRentOrder(csuoId, finishTime, mileage, false);
		Double payReal = orderinfo.getPayReal();   //实际支付
		Double payNeed = orderinfo.getPayNeed();   //理论支付价格
		
		Double payCoupon = 0d;  //现金券支付金额
		Double payMoney = 0d;   //余额支付金额
		
		//如果可用余额小于需支付费用并且实际支付大于0
		if(canMoney < payReal) {
			CsSpecialCar specialOrder = unitOrder.get$csuoSpecial();
			specialOrder.setCsscUpdateTime(new Date());
			specialOrder.setCsscStatus((short) 7); //待处理
			specialOrder.update();
			
			throw new MessageException("账户可用支付余额不足，未能完成结算！", 1005);
		}
		
		//优先优惠券支付
		payCoupon = Math.min(member.getCsmCoupon(), payReal);	// 现金券支付(不能支付里程费)
		if (payCoupon < 0)
			payCoupon = 0d;
		
		//剩下的用余额支付
		payMoney = payReal - payCoupon;// 余额支付数
		
		if(payMoney <=0 && payCoupon <=0){
			throw new MessageException("价格计算异常，不能完成结算！", 1006);
		}
		
		String strDescript = $.date(new Date(), "yyyy-MM-dd HH:mm:ss") + "结算订单，订单费用" + orderinfo.payReal + "元，其中(";
		if(orderinfo.hasPromotion())
			strDescript += "优惠["+orderinfo.getPromotion()+"]减免" + (orderinfo.payNeed - orderinfo.payReal) +"元";
		if (orderinfo.mileageFee > 0)
			strDescript += "，里程费" + orderinfo.mileageFee + "元 ";
		if (orderinfo.timeLongFee > 0)
			strDescript += "，时长费" + orderinfo.timeLongFee + "元 ";
		strDescript += ")";
		
		if (payCoupon > 0) {// 现金券支付
			commonMoneyService.updateMoney(unitOrder.getCsuoHost(), member.getCsmId(), ICommonMoneyService.MoneyType.Coupon, -payCoupon,
					SYSTEM.RecordType.订单消费, strDescript, unitOrder.getCsuoId(), unitOrder.getCsuoId(), CsOrder.class);
		}
		if (payMoney > 0) {
			commonMoneyService.updateMoney(unitOrder.getCsuoHost(), member.getCsmId(), ICommonMoneyService.MoneyType.Money, -payMoney,
					SYSTEM.RecordType.订单消费, strDescript, unitOrder.getCsuoId(), unitOrder.getCsuoId(), CsOrder.class);
		}
		
		CommonMoneyService.getBean().updateGrow(useMemberId, GrowRecordType.订单消费, unitOrder.getCsuoId(), payReal);
		
		// ===================== 订单更新 =====================
		unitOrder.setCsuoSettleState((short)1);	//已结算
		unitOrder.setCsuoState((short)4);		//订单结束
		unitOrder.setCsuoSettleTime(new Date());
		unitOrder.setCsuoMileage(mileage);
		unitOrder.setCsuoKmPrice(orderinfo.mileageFee); 
		unitOrder.setCsuoPayNeed(payNeed);
		unitOrder.setCsuoPayReal(payReal);
		unitOrder.setCsuoFinishTime(finishTime);
		unitOrder.setCsuoRemark(remark);
		unitOrder.setCsuoUpdateTime(new Date());
		unitOrder.update();
		
		CsSpecialCar specialOrder = unitOrder.get$csuoSpecial();
		specialOrder.setCsscRealDur(orderinfo.timeMinute);
		specialOrder.setCsscKilo(mileage.intValue());
		specialOrder.setCsscFinishTime(finishTime);
		specialOrder.setCsscUpdateTime(new Date());
		specialOrder.setCsscStatus((short) 4); //已完成
		specialOrder.update();

		//司机状态释放
		if(specialOrder.getCsscDrivercar()!=null){
			CsDriverCar driverCar = new CsDriverCar(specialOrder.getCsscDrivercar());
			driverCar.setCsdcState((short)0); //相关司机状态改为“空闲”
			driverCar.update();
		}
		
		UtilHelper.sendTemplateSMS(
				unitOrder.getCsuoHost(),
				//payByCreditCard ? "SETTLE_ORDER_BY_CREDIT" : "SETTLE_ORDER",
				"SETTLE_UNIT_ORDER",	//公务订单短信和个人订单短信分开
				member.getCsmMobile(),
				"您的约租车订单号为{order}于{time}已结算，行驶里程{mileage}，订单时长{duration}分钟，总计费用为{money}",
				SMSType.通知类短信,
					   $.add("time", $.date(finishTime, "MM月dd日HH时mm分"))
						.add("order", unitOrder.getCsuoId().toString())
						.add("money", unitOrder.getCsuoPayReal().toString())
						.add("mileage", mileage.toString())
						.add("duration", orderinfo.timeMinute.toString()));
		
		return null;
	}

	@Override
	public CsUnitOrder executeCancelRentOrder(Long csuoId, Long useMemberId, String remark)
			throws MessageException {
		
		Date finishTime = new Date();

		// ===================== 校验 =====================
		if(csuoId == null || useMemberId == null){
			throw new MessageException("参数错误，不能完成订单取消！", 1000);
		}
		
		CsUnitOrder unitOrder = CsUnitOrder.get(csuoId);
		if(unitOrder == null) {
			throw new MessageException("该订单不存在，不能完成订单取消！", 1001);
		}
		
		CsMember member = CsMember.get(useMemberId);
		if(member == null || member.getCsmStatus().intValue() != 1){
			throw new MessageException("该会员不可用，不能完成订单取消！", 1002);
		}
		
		if(unitOrder.getCsuoState().intValue() ==4 || $.or(unitOrder.getCsuoSettleState(), 0).intValue() !=0){
			throw new MessageException("该订单状态不正确，不能完成订单取消！", 1003);
		}
		
		//if(finishTime.getTime() < unitOrder.getCsuoStartTime().getTime()){
		//	throw new MessageException("该订单还车时间早于订单开始时间，不能完成取消！", 1004);
		//}
		
		if(!unitOrder.get$csuoPerson().getCsupMember().equals(useMemberId)){
			throw new MessageException("该订单所属人不符，不能完成取消！", 1004);
		}
		
		// ===================== 扣款 =====================
		Double canMoney = commonMoneyService.getUsableAmount(useMemberId);  //用户可用余额
		
		com.ccclubs.service.common.impl.UnitOrderInfo unitOrderInfo = execulateCalcRentOrder(csuoId, finishTime, null, true);
		Double payReal = unitOrderInfo.getPayReal();	//实际支付价格
		Double payNeed = unitOrderInfo.getPayNeed();	//理论支付价格
		Double payCoupon = 0d;
		Double payMoney  = 0d;			
		
		//如果产生费用
		if(payReal >0){
			payReal = new BigDecimal(payReal).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			
			//如果可用余额小于需支付费用并且实际支付大于0
			if(canMoney < payReal) {
				throw new MessageException("账户可用支付余额不足，不能完成取消结算！", 1005);
			}
			
			//优先优惠券支付
			payCoupon = Math.min(member.getCsmCoupon(), payReal);	// 现金券支付(不能支付里程费)
			if (payCoupon < 0)
				payCoupon = 0d;
			
			//剩下的用余额支付
			payMoney = payReal - payCoupon;// 余额支付数
			
			if(payMoney <=0 && payCoupon <=0){
				throw new MessageException("价格计算异常，不能完成结算！", 1006);
			}
			
			String strDescript = $.date(new Date(), "yyyy-MM-dd HH:mm:ss") + "取消结算订单,订单费用" + payReal + "元,其中(";
			if (payReal > 0)
				strDescript += " 超时费用" + payReal + "元 ";
			strDescript += ")";
			
			if (payCoupon > 0) {// 现金券支付
				commonMoneyService.updateMoney(unitOrder.getCsuoHost(), member.getCsmId(), ICommonMoneyService.MoneyType.Coupon, -payCoupon,
						SYSTEM.RecordType.订单消费, strDescript, unitOrder.getCsuoId(), unitOrder.getCsuoId(), CsOrder.class);
			}
			if (payMoney > 0) {
				commonMoneyService.updateMoney(unitOrder.getCsuoHost(), member.getCsmId(), ICommonMoneyService.MoneyType.Money, -payMoney,
						SYSTEM.RecordType.订单消费, strDescript, unitOrder.getCsuoId(), unitOrder.getCsuoId(), CsOrder.class);
			}
			
			CommonMoneyService.getBean().updateGrow(useMemberId, GrowRecordType.订单消费, unitOrder.getCsuoId(), payReal);
		}
		
		// ===================== 订单更新 =====================
		unitOrder.setCsuoSettleState((short)1);	//已结算
		unitOrder.setCsuoState((short)4);		//订单结束
		unitOrder.setCsuoSettleTime(new Date());
		unitOrder.setCsuoMileage(0d);
		unitOrder.setCsuoKmPrice(0d); 
		unitOrder.setCsuoPayNeed(payNeed);
		unitOrder.setCsuoPayReal(payReal);
		unitOrder.setCsuoFinishTime(finishTime);
		unitOrder.setCsuoRemark(StringUtils.isEmpty(remark) ? "用户发起订单取消":remark);
		unitOrder.setCsuoUpdateTime(new Date());
		unitOrder.update();
		
		CsSpecialCar specialOrder = unitOrder.get$csuoSpecial();
		specialOrder.setCsscRealDur(0L);
		specialOrder.setCsscKilo(0);
		specialOrder.setCsscFinishTime(finishTime);
		specialOrder.setCsscUpdateTime(new Date());
		specialOrder.setCsscStatus((short) 5); //已取消
		specialOrder.update();
		
		//========================其它事物处理======================
		
		//司机状态释放
		if(specialOrder.getCsscDrivercar()!=null){
			CsDriverCar driverCar = new CsDriverCar(specialOrder.getCsscDrivercar());
			driverCar.setCsdcState((short)0); //相关司机状态改为“空闲”
			driverCar.update();
		}
		
		UtilHelper.sendTemplateSMS(
				unitOrder.getCsuoHost(),
				//payByCreditCard ? "SETTLE_ORDER_BY_CREDIT" : "SETTLE_ORDER",
				"SETTLE_UNIT_ORDER",	//公务订单短信和个人订单短信分开
				member.getCsmMobile(),
				"您的约租车订单号为{order}于{time}已取消，总计费用为{money}",
				SMSType.通知类短信,
					   $.add("time", $.date(finishTime, "MM月dd日HH时mm分"))
						.add("order", unitOrder.getCsuoId().toString())
						.add("money", unitOrder.getCsuoPayReal().toString()));
		
		return null;
	}
	

	@Override
	public com.ccclubs.service.common.impl.UnitOrderInfo execulateCalcRentOrder(Long csuoId,
			Date finishTime, Double mileage, boolean isCancel) throws MessageException {
		if(mileage == null) mileage = 0d;
		if(finishTime == null) finishTime = new Date();
		
		CsUnitOrder unitOrder = CsUnitOrder.get(csuoId);
		if(unitOrder == null){
			throw new MessageException("订单号不存在", 1000);
		}
		
		CsSpecialCar special = unitOrder.get$csuoSpecial();
		
//		如果没有司机
//		if(special.getCsscStatus().intValue() !=3 && special.getCsscDrivercar() == null || special.getCsscDrivercar() <= 0L){
//			return new com.ccclubs.service.common.impl.UnitOrderInfo();
//		}
		
		if(isCancel){		// =========================== 取消结算 =================================
			com.ccclubs.service.common.impl.UnitOrderInfo orderInfo = new com.ccclubs.service.common.impl.UnitOrderInfo();
			Long durationMillisecond = unitOrder.getCsuoStartTime().getTime() - new Date().getTime();
			long durationMinute = durationMillisecond / SYSTEM.MINUTE.longValue();
			
			//已到达出发时间取消，收取预估费用100%
			Double payNeed = special.getCsscEstimateFee();
			Double payReal = 0d;
			if(durationMinute <= 0){
				payReal = payNeed;
			}else if(durationMinute >0 && durationMinute<=30){  //出发前半小时以内，收取预估费用的20%
				payReal = payNeed * 0.2;
			}else if(durationMinute > 30){ 						//出发前半小时以上，收取预估费用的0%
				payReal = payNeed * 0;
			}
			
			orderInfo.setPayNeed(payReal);
			orderInfo.setPayReal(payReal);
			return orderInfo;
		}else{			// =========================== 正常结算 ==================================
			com.ccclubs.service.common.impl.UnitOrderInfo orderInfo = new com.ccclubs.service.common.impl.UnitOrderInfo(unitOrder.getCsuoStartTime(), finishTime);
			orderInfo.setMileageFee(mileage * ICommonUnitService.mileagePrice);
			orderInfo.setTimeLongFee( 0d * orderInfo.getTimeMinute().doubleValue());
			orderInfo.setPayNeed(orderInfo.timeLongFee + orderInfo.mileageFee);
			orderInfo.setPayReal(orderInfo.getPayNeed());
			
			//如果选择是拼车的话半价
			if(special.getCsscIsCarpool().intValue() == 1){     		
				orderInfo.setPromotion("拼车半价");
				orderInfo.setPayReal(orderInfo.getPayNeed() * 0.5);
			}
			return orderInfo;
		}
		
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

	public ICommonDisposeService getCommonDisposeService() {
		return commonDisposeService;
	}

	public void setCommonDisposeService(ICommonDisposeService commonDisposeService) {
		this.commonDisposeService = commonDisposeService;
	}

	public ICsUnitOrderService getCsUnitOrderService() {
		return csUnitOrderService;
	}

	public void setCsUnitOrderService(ICsUnitOrderService csUnitOrderService) {
		this.csUnitOrderService = csUnitOrderService;
	}

	public ICsOrderService getCsOrderService() {
		return csOrderService;
	}

	public void setCsOrderService(ICsOrderService csOrderService) {
		this.csOrderService = csOrderService;
	}


	public ICsArgumentService getCsArgumentService() {
		return csArgumentService;
	}


	public void setCsArgumentService(ICsArgumentService csArgumentService) {
		this.csArgumentService = csArgumentService;
	}

}
