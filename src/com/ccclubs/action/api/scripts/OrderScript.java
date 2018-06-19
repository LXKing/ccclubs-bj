package com.ccclubs.action.api.scripts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ccclubs.action.api.model.Result;
import com.ccclubs.action.web.BaseOrderInfo;
import com.ccclubs.config.Constant;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.DateHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsCarModel;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberShip;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOrderDetail;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsProduct;
import com.ccclubs.model.CsUseRecord;
import com.ccclubs.model.Restriction;
import com.ccclubs.model.SrvApiClient;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsMemberShipService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsOutletsService;
import com.ccclubs.service.admin.ICsProductService;
import com.ccclubs.service.common.From;
import com.ccclubs.service.common.ICommonDisposeService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonOrderService;
import com.ccclubs.service.common.impl.OrderInfo;
import com.lazy3q.web.helper.$;

public class OrderScript extends BaseScript{
	
	/**
	 * 一次性加载车辆时间轴信息
	 * @return
	 */
	public static Map<String, Object> getCarStatusTimeLine(String carIds, String start, String finish){
		if(carIds == null || carIds.trim().equals(""))return null;
		if(start == null || start.trim().equals(""))return null;
		if(finish == null || finish.trim().equals(""))return null;
		
		SimpleDateFormat formatter = new SimpleDateFormat(NORMAL_DATE_FORMAT);
		Map<String, Object> datamap = new HashMap<String, Object>();
	    Date start_date = null;
	    Date finish_date = null;
		try {
			start_date = formatter.parse(start);
			finish_date = formatter.parse(finish);
		} catch (ParseException e1) {
			e1.printStackTrace();
			return datamap;
		}
		
		//超出一个月的时间，不返回
		if((finish_date.getTime()-start_date.getTime())>SYSTEM.MONTH){
			return datamap;
		}
		
		ICommonOrderService	commonOrderService = $.GetSpringBean("commonOrderService");
		ICsOrderService	csOrderService = $.GetSpringBean("csOrderService");
		try {
			
			String[] carIdsList = carIds.split(",");
			
			
			for(int j = 0; j<carIdsList.length; j++){
				
				Map<String /*carId*/, HashMap<String, Object>/*car timebar data */> timeBarDataMap = new HashMap<String, HashMap<String /*key*/, Object>>();
				List<CsOrder> orders = csOrderService.getCsOrderList($.add("definex", CsOrder.C.csoCar + " ='"+carIdsList[j]+"' and " + CsOrder.C.csoStatus + " not in (3,7) and cso_start_time < '" + SystemHelper.formatDate(finish_date, "yyyy-MM-dd HH:mm:ss") + "' and cso_finish_time >  '" + SystemHelper.formatDate(start_date, "yyyy-MM-dd HH:mm:ss") + "'"), -1);
				List<Restriction> restrictions = commonOrderService.getRestrictions(Long.valueOf(carIdsList[j]), start_date, finish_date);
				
				Map<String, List<Restriction>> carTimeBarMap = new HashMap<String, List<Restriction>>();
				if(orders!=null){
					List<Restriction> orderTimes = new ArrayList<Restriction>();
					for(CsOrder co : orders){
						Restriction rest = new Restriction(co.getCsoStartTime(), co.getCsoFinishTime());
						orderTimes.add(rest);
					}
					carTimeBarMap.put("orderTimeLine", orderTimes);
					carTimeBarMap.put("limitTimeLine", restrictions);
				}
				
				datamap.put(carIdsList[j], carTimeBarMap);
			}
			
			return datamap;
		} catch (Exception e) {
			e.printStackTrace(); 
			Logger.getRootLogger().error(e.getMessage(),e);
		}
		
		return datamap;
	}
	
	/**
	 * 订单预定检查
	 * @carId 车辆ID
	 * @start 开始时间
	 * @finish 结束时间
	 * @insureType 保险类型
	 * @freehour 免费小时
	 * @return
	 */
	public static Result checkOrder(Long carId, String start, String finish, Long insureType, Double freehour, Long retOutlets, Long detailType){
		Result rs = new Result();
		if(start == null || start.trim().equals(""))return null;
		if(finish == null || finish.trim().equals(""))return null;
		
		SimpleDateFormat formatter = new SimpleDateFormat(NORMAL_DATE_FORMAT);
	    Date start_date = null;
	    Date finish_date = null;
		try {
			start_date = formatter.parse(start);
			finish_date = formatter.parse(finish);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		ICsCarService	csCarService = $.GetSpringBean("csCarService");
		ICommonOrderService commonOrderService = $.GetSpringBean("commonOrderService");
		ICsOutletsService	csOutletsService = $.GetSpringBean("csOutletsService");
		
		try{
			// 区域，网点，车型
			if (start_date == null)
				// 开始时间没有
				return new Result(false, 101);
			if (finish_date == null)
				// 结束时间没有
				return new Result(false, 102);
			if ((start_date.getTime() - new Date().getTime()) < (-2 * SYSTEM.HOUR))
				// 开始时间不能早于当前时间两小时
				return new Result(false, 103);
			if (start_date.after(finish_date))
				// 开始时间不能大于结束时间
				return new Result(false, 104);
			if ((finish_date.getTime() - start_date.getTime()) < (SYSTEM.HOUR))
				// 租车时间最低1个小时起租
				return new Result(false, 105);
			if (!SystemHelper.isCorrectOrderDate(start_date) || !SystemHelper.isCorrectOrderDate(finish_date))
				// 开始时间、结束时间，不符合定单时间要求，应该是20分钟的整数倍
				return new Result(false, 106);
			if ((finish_date.getTime() - start_date.getTime()) > (Constant.ORDER_DAYS_DUR * SYSTEM.DAY))
				// 系统只提供30天长度的预订
				return new Result(false, 108);
			CsCar car = csCarService.getCsCarById(carId);
			if (car == null)
				// 需要选择一辆车
				return new Result(false, 109);
			if (car.getCscStatus() == (short) 0)
				// 选择的车辆已下线
				return new Result(false, 110);
			// 检查该车辆，该时间段已经被预订
			if (commonOrderService.isExistOrderByTime(carId, start_date, finish_date, null))
				return new Result(false, 111);
			
			//电动车型的开始预定时间判断
			if(car.get$cscModel().getCscmType().intValue() == 1){
				//当前车型只提供一小时以内的车辆预定
				if(start_date.getTime() - Calendar.getInstance().getTime().getTime() > SYSTEM.HOUR * 2)
					return new Result(false, 112);
			}
			
			//还入网点不能为空
			if(retOutlets == null){
				return new Result(false, 114);
			}
			
			CsOutlets csOutletsRet = csOutletsService.getCsOutletsById(retOutlets);
			if(csOutletsRet == null){
				return new Result(false, 114);
			}else{
				//还车网点的可用车位数不足
				if(csOutletsRet.getCsoPositionS()==null || csOutletsRet.getCsoPositionS()<=0){
					return new Result(false, 115);
				}
			}
			
			// 设置定单基本信息
			BaseOrderInfo baseOrderInfo = new BaseOrderInfo();
			if (SystemHelper.isBaseOrderInfo())
				baseOrderInfo = SystemHelper.getBaseOrderInf();
	
			if (!baseOrderInfo.getCsoIsSpring()) {
				if ((start_date.getTime() - new Date().getTime()) > (Constant.ORDER_DAYS_EARLY * SYSTEM.DAY))
					// 系统只提供7天以内的预订
					return new Result(false, 107);
			}else{
				//2015年使用春节套餐租车最短要租满10天
				if ((finish_date.getTime() - start_date.getTime()) < (10 * SYSTEM.DAY))
					return new Result(false, 113);
			}
			
			baseOrderInfo.setCsoFinish(finish_date);
			baseOrderInfo.setCsoStart(start_date);
			baseOrderInfo.setCsoCar(carId);
			baseOrderInfo.setCsoOutlets(car.getCscOutlets());
			baseOrderInfo.setCsoOutletsRet(csOutletsRet.getCsoId());
			baseOrderInfo.setCsoOutletsNameRet(csOutletsRet.getCsoName());
			// 设置定单基本信息 Session
			SystemHelper.setBaseOrderInfo(baseOrderInfo);
			// 设置定单基本信息 Session 成功，跳转到 预订页面
			
			Map<String, Object> values = null;
			if(detailType == 2){
				values = detailV2(insureType, freehour);
			}else{
				values = detail(insureType, freehour);
			}
			
			rs.setData(values);
			
			return rs.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return new Result(false, 9999);
		} catch (Error e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return new Result(false, 9999);
		}
	}
	
	/**
	 * 订单详情
	 * @return
	 */
	public static Map<String, Object> detail(Long insureType, Double freehour){
		Map<String, Object> values = new HashMap<String, Object>();
		
		ICsCarService	csCarService = $.GetSpringBean("csCarService");
		ICsProductService	csProductService= $.GetSpringBean("csProductService");
		ICommonOrderService		commonOrderService = $.GetSpringBean("commonOrderService");
		ICommonMoneyService		commonMoneyService = $.GetSpringBean("commonMoneyService");
		ICsMemberService		csMemberService = $.GetSpringBean("csMemberService");
		
		Long userId = getOauth();
		
		BaseOrderInfo baseOrderInfo = SystemHelper.getBaseOrderInf();
		if (insureType!=null && insureType.equals(-1l)) {
			// 保险类型，默认按小时购买
			CsProduct csProduct = csProductService.getCsProduct($.add(CsProduct.F.cspFlag, Constant.SECURE));
			insureType = csProduct.getCspId();
		}
		
		CsCar csoCar = csCarService.getCsCarById(baseOrderInfo.getCsoCar());
		OrderInfo orderInfo ;
		// 获取小时数
		int allhours = (int) ((baseOrderInfo.getCsoFinish().getTime() - baseOrderInfo.getCsoStart().getTime()) / SYSTEM.HOUR);
		
		// 汽油车或电动车
		if (csoCar.get$cscModel().getCscmType() == (short) 0) {
			orderInfo = commonOrderService.splitOrderDetails(userId, baseOrderInfo.getCsoCar(), baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish(), null, freehour, null, commonOrderService.getPredictKms(allhours) * 1.0, null, insureType, null, null);
		} else {
			orderInfo = commonOrderService.splitOrderDetails(userId, baseOrderInfo.getCsoCar(), baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish(), null, freehour, commonOrderService.getPredictKms(allhours) * 1.0, null, null, insureType, null, null);
		}
		Double memberhours = commonMoneyService.getCanUseFreeHour(userId, new Date());
		
		// 最多可用免费小时
		values.put("maxhours", Math.min(memberhours, orderInfo.getOnehours()));
		// 帐户可用免费小时
		values.put("memberhours", memberhours);
		CsMember member = csMemberService.getCsMemberById(userId);
		if(member == null){
			return values;
		}
		
		values.put("member", member);
		values.put("money", commonMoneyService.getUsableAmount(member.getCsmId()));	// 会员可用余额
		values.put("price", orderInfo.getPrice());  //总消费金额
		values.put("margin", orderInfo.getMargin()); //保证金
		
		List<CsOrderDetail> detailList = orderInfo.getDetails();
		List dataList = new ArrayList();
		if(detailList!=null){
			for(CsOrderDetail detail : detailList){
				Map<String,Object> orderItem = new HashMap<String, Object>();
				orderItem.put("csgName", detail.get$csodGoods().getCsgName());
				orderItem.put("csodPrice", detail.getCsodPrice());
				orderItem.put("csodCount", detail.getCsodCount());
				orderItem.put("csodStart", detail.getCsodStart());
				orderItem.put("csodEnd", detail.getCsodEnd());
				orderItem.put("csodTotalReal", detail.getCsodTotalReal());
				dataList.add(orderItem);
			}
		}
		values.put("details", dataList);
		
		freehour = orderInfo.getFreehour();
		// 设置免费小时，保险产品类型
		baseOrderInfo.setCsoFreeHours(freehour);
		baseOrderInfo.setCsoInsure(insureType);
		SystemHelper.setBaseOrderInfo(baseOrderInfo);
		
		return values;
	}
	/**
	 * 订单详情，归纳订单产品
	 * @return
	 */
	public static Map<String, Object> detailV2(Long insureType, Double freehour){
		Map<String, Object> values = new HashMap<String, Object>();
		
		ICsCarService	csCarService = $.GetSpringBean("csCarService");
		ICsProductService	csProductService= $.GetSpringBean("csProductService");
		ICommonOrderService		commonOrderService = $.GetSpringBean("commonOrderService");
		ICommonMoneyService		commonMoneyService = $.GetSpringBean("commonMoneyService");
		ICsMemberService		csMemberService = $.GetSpringBean("csMemberService");
		
		Long userId = getOauth();
		
		BaseOrderInfo baseOrderInfo = SystemHelper.getBaseOrderInf();
		if (insureType!=null && insureType.equals(-1l)) {
			// 保险类型，默认按小时购买
			CsProduct csProduct = csProductService.getCsProduct($.add(CsProduct.F.cspFlag, Constant.SECURE));
			insureType = csProduct.getCspId();
		}
		
		CsCar csoCar = csCarService.getCsCarById(baseOrderInfo.getCsoCar());
		OrderInfo orderInfo ;
		// 获取小时数
		int allhours = (int) ((baseOrderInfo.getCsoFinish().getTime() - baseOrderInfo.getCsoStart().getTime()) / SYSTEM.HOUR);
		
		// 汽油车或电动车
		CsCarModel model = csoCar.get$cscModel();
		if (model.getCscmType() == (short) 0) {
			orderInfo = commonOrderService.splitOrderDetails(userId, baseOrderInfo.getCsoCar(), baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish(), null, freehour, null, commonOrderService.getPredictKms(allhours) * 1.0, null, insureType, null, null);
		} else {
			orderInfo = commonOrderService.splitOrderDetails(userId, baseOrderInfo.getCsoCar(), baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish(), null, freehour, commonOrderService.getPredictKms(allhours) * 1.0, null, null, insureType, null, null);
		}
		Double memberhours = commonMoneyService.getCanUseFreeHour(userId, new Date());
		
		// 最多可用免费小时
		values.put("maxhours", Math.min(memberhours, orderInfo.getOnehours()));
		// 帐户可用免费小时
		values.put("memberhours", memberhours);
		CsMember member = csMemberService.getCsMemberById(userId);
		if(member == null){
			return values;
		}
		
		values.put("member", member);
		values.put("money", commonMoneyService.getUsableAmount(member.getCsmId()));	// 会员可用余额
		values.put("price", orderInfo.getPrice());  //总消费金额
		values.put("margin", orderInfo.getMargin()); //保证金
		
		List<CsOrderDetail> detailList = orderInfo.getDetails();
		if(detailList!=null){
			Map<String, Double> pricesItem = new HashMap<String, Double>();
			for(CsOrderDetail detail : detailList){
				CsProduct prod = detail.get$csodProduct();
				Double price = pricesItem.get(prod.getCspFlag());
				if(price == null){
					pricesItem.put(prod.getCspFlag(), detail.getCsodTotalReal());
				}else{
					pricesItem.put(prod.getCspFlag(), detail.getCsodTotalReal() + price);
				}
			}
			values.put("rent", pricesItem.get(SYSTEM.RENT));
			if (model.getCscmType() == (short) 0) {
				values.put("mileageFee", pricesItem.get(SYSTEM.MILEAGE));
			}else if(model.getCscmType() == (short) 1){
				values.put("mileageFee", pricesItem.get(SYSTEM.KILOM));
			}
			values.put("hasInsureMargin", pricesItem.get(SYSTEM.MARGIN));
			values.put("noInsureMargin", pricesItem.get(SYSTEM.MARGING));
			values.put("insure", pricesItem.get(SYSTEM.INSURE));
			values.put("secure", pricesItem.get(SYSTEM.SECURE));
			values.put("allhours", allhours);
			values.put("computekilom", commonOrderService.getPredictKms(allhours));
		}
		
		freehour = orderInfo.getFreehour();
		// 设置免费小时，保险产品类型
		baseOrderInfo.setCsoFreeHours(freehour);
		baseOrderInfo.setCsoInsure(insureType);
		SystemHelper.setBaseOrderInfo(baseOrderInfo);
		
		return values;
	}
	
	/**
	 * 获取保险类型
	 * @return
	 */
	public static List<CsProduct> getInsureTypes(){
		ICsProductService	csProductService = $.GetSpringBean("csProductService");
		List<CsProduct> insureTypes = csProductService.getCsProductList($.add("definex", CsProduct.C.cspFlag + " = '" + Constant.SECURE + "' || " + CsProduct.C.cspFlag + " = '" + Constant.INSURE + "'"), -1);
		return insureTypes;
	}
	
	/**
	 * 获取用户可用免费小时
	 * @param memeberId
	 * @return
	 */
	public static Double getUserCanFreeHour(){
		ICommonMoneyService	commonMoneyService = $.GetSpringBean("commonMoneyService");
		Double memberhours = commonMoneyService.getCanUseFreeHour(getOauth(), new Date());
		return memberhours;
	}
	
	/**
	 * 订单提交
	 * @return
	 */
	public static Result commitOrder(Long insureType, Double freeHours, Long paid, String remark){
		ICsMemberService	csMemberService = $.GetSpringBean("csMemberService");
		ICsMemberShipService 	csMemberShipService = $.GetSpringBean("csMemberShipService");
		ICommonDisposeService	commonDisposeService = $.GetSpringBean("commonDisposeService");
		ICsOrderService csOrderService = $.getBean("csOrderService");
		
		Result rs = new Result();
		try {
			BaseOrderInfo baseOrderInfo = new BaseOrderInfo();
			//用户没有登录
			if (getOauth() == null)
				return new Result(false, 201);
			
			// Session 里没有基本订单信息
			if (!SystemHelper.isBaseOrderInfo() || SystemHelper.getBaseOrderInf().getCsoCar().equals(0l)) {
				return new Result(false, 202);
			} else {
				baseOrderInfo = SystemHelper.getBaseOrderInf();
			}
			/** ********驾驶人********* */
			CsMember useMember = csMemberService.getCsMemberById(getOauth());
			// 驾驶认证没有审核通过
			if (useMember.getCsmVDrive() != null && useMember.getCsmVDrive() != (short) 1 && useMember.getCsmEvcard() != null) {
				if (useMember.getCsmVDrive() == (short) 2) {
					// 驾驶认证已经上传，请耐心等待
					return new Result(false, 205);
				} else {
					// 驾驶认证没有上传
					return new Result(false, 203);
				}
			}
			
			/** ********支付人********* */
			Long payMemberId = null;
			if(paid !=null && paid > 0L){
				CsMemberShip memberShip = csMemberShipService.getCsMemberShipById(paid);
				if(memberShip == null || memberShip.getCsmsTargeter() == null
						|| memberShip.getCsmsTargeter().longValue()!=useMember.getCsmId().longValue() || memberShip.getCsmsStatus() !=(short)1){
					//选择的代付会员参数不正确
					return new Result(false, 209);
				}
				payMemberId = memberShip.getCsmsPayer();
			}else{
				payMemberId = useMember.getCsmId();
			}
			
			Result checkResult = checkOrderInfo(baseOrderInfo);
			if(!checkResult.getResult()){
				return checkResult;
			}
			
			CsOrder csOrder = new CsOrder();
			// 平时订单处理
			if (!baseOrderInfo.getCsoIsSpring()) {
				csOrder = commonDisposeService.executeSaveOrder(payMemberId, useMember.getCsmId(), baseOrderInfo.getCsoCar(), baseOrderInfo.getCsoOutlets(), baseOrderInfo.getCsoOutletsRet(), baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish(), null, baseOrderInfo.getCsoInsure(), null, freeHours, null, 0l,From.API,$.getLocal("client")==null?null:("SrvApiClient@"+((SrvApiClient)$.getLocal("client")).getSacId()));
			} else {
				// 春节订单处理，保证金为1500元
				csOrder = commonDisposeService.executeSaveOrder(payMemberId, useMember.getCsmId(), baseOrderInfo.getCsoCar(), baseOrderInfo.getCsoOutlets(), baseOrderInfo.getCsoOutletsRet(), baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish(), null, baseOrderInfo.getCsoInsure(), null, freeHours, 1500.0, 0l,From.API,$.getLocal("client")==null?null:("SrvApiClient@"+((SrvApiClient)$.getLocal("client")).getSacId()));
				// 设置订单flag为春节租车
				csOrder.setCsoFlag((short) 1);
				csOrder.setCsoUpdateTime(new Date());
				csOrderService.updateCsOrder$NotNull(csOrder);
			}

			if(StringUtils.isNotEmpty(remark)){
				new CsOrder().csoId(csOrder.getCsoId()).csoRemark(remark).update();
			}
			$.SetRequest("csOrder", csOrder);
			
			// 清除当前定单信息
			$.setSession(Constant.BASE_ORDER_INFO, null);
			// 更新默认取车点
			if (useMember.getCsmOutlets() == null) {
				CsMember newMember = new CsMember();
				newMember.setCsmOutlets(csOrder.getCsoOutlets());
				newMember.setCsmId(useMember.getCsmId());
				csMemberService.updateCsMember$NotNull(newMember);
			}
			
			return rs.setData(csOrder.getCsoId()).setResult(true);	//返回订单号
		} catch (com.ccclubs.exception.MessageException oe) {
			oe.printStackTrace();
			Logger.getRootLogger().error(oe);
			// $.SetTips(oe.getMessage());
			if (oe.getMessage().equals("会员可用余额不足")) {
				return new Result(false, 204);
			} else if (oe.getMessage().equals("当前支付会员正在退款，不能下单")) {
				return new Result(false, 206);
			} else if (oe.getMessage().equals("没有绑定EV卡，不能下单")) {
				return new Result(false, 207);
			} else if (oe.getMessage().equals("当前订单时间已经被其它订单占用")) {
				return new Result(false, 208);
			} else if(oe.getMessage().equals("该会员资料未审核通过，不能下单")) {
				return new Result(false, 205);
			} else if(oe.getMessage().equals("该会员帐号已被禁用，不能下单")) {
				return new Result(false, 205);
			} else if(oe.getMessage().equals("会员已欠费，不能下单")) {
				return new Result(false, 209);
			} else if(oe.getMessage().equals("会员有信用帐单坏帐，不能租车")) {
				return new Result(false, 210);
			}else {
				return new Result(false, 9999);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return new Result(false, 9999);
		} catch (Error e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return new Result(false, 9999);
		}
	}
	
	/**
	 * 取消订单
	 * 
	 * @return
	 */
	public static Result cancel(Long orderId) {
		
		Result rs = new Result();
		try {
			
			ICsOrderService csOrderService = $.getBean("csOrderService");
			ICommonDisposeService	commonDisposeService = $.GetSpringBean("commonDisposeService");
			
			if(orderId == null){
				return new Result(false, 100);
			}
			
			//用户没有登录
			if (getOauth() == null)
				return new Result(false, 101);
			
			CsOrder order = csOrderService.getCsOrderById(orderId);
			// 验证当前订单是不是当前用户的
			if (order == null || !order.getCsoPayMember().equals(getOauth())) {
				return new Result(false, 102);
			}
			
			//订单状态不正确
			if (order.getCsoStatus() != 0)
				return new Result(false, 103);
			
			commonDisposeService.executeCancelOrder(orderId, "会员自主取消订单",From.API,$.getLocal("client")==null?null:("SrvApiClient@"+((SrvApiClient)$.getLocal("client")).getSacId()));
			rs.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return new Result(false, 9999);
		} catch (Error e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return new Result(false, 9999);
		}
		
		return rs;
	}
	
	/**
	 * 验证定单信息
	 * 
	 * @return
	 */
	private static Result checkOrderInfo(BaseOrderInfo baseOrderInfo) {
		ICsOutletsService	csOutletsService = $.GetSpringBean("csOutletsService");
		Result rs = new Result();
		// 开始时间，结束时间
		Date csoStart = baseOrderInfo.getCsoStart();
		Date csoFinish = baseOrderInfo.getCsoFinish();
		// 车辆
		Long csoCar = baseOrderInfo.getCsoCar();
		if (csoStart == null)
			// 开始时间没有
			return new Result(false, 101); 
		if (csoFinish == null)
			// 结束时间没有
			return new Result(false, 102); 
		if ((csoStart.getTime() - new Date().getTime()) < (-2 * SYSTEM.HOUR))
			// 开始时间不能早于当前时间两小时
			return new Result(false, 103); 
		if (csoStart.after(csoFinish))
			// 开始时间不能大于结束时间
			return new Result(false, 104); 
		if ((csoFinish.getTime() - csoStart.getTime()) < (SYSTEM.HOUR))
			// 租车时间最低1个小时起租
			return new Result(false, 105); 
		if (!SystemHelper.isCorrectOrderDate(csoStart) || !SystemHelper.isCorrectOrderDate(csoFinish))
			// 开始时间、结束时间，不符合定单时间要求，应该是20分钟的整数倍
			return new Result(false, 106); 
		// 节假日租车时间特殊化未处理!!
		if (!baseOrderInfo.getCsoIsSpring()) {
			if ((csoStart.getTime() - new Date().getTime()) > (Constant.ORDER_DAYS_EARLY * SYSTEM.DAY))
				// 系统只提供7天以内的预订
				return new Result(false, 107); 
		} else {
			// 春节租车需要包含 1月30日17点到2月7号9点
			Date startDate = DateHelper.getDate(2015, 2, 17, 17, 0, 0);
			Date endDate = DateHelper.getDate(2015, 2, 25, 8, 40, 0);
			if (csoStart.after(startDate)) {
				// 亲，春节租车，需要选择在2月17日17点前提车
				return new Result(false, 110); 
			}
			if (endDate.after(csoFinish)) {
				// 亲，春节租车，需要选择在2月25日9点后还车
				return new Result(false, 111);
			}
			
			//2015年使用春节套餐租车最短要租满10天
			if ((csoFinish.getTime() - csoStart.getTime()) < (10 * SYSTEM.DAY))
				return new Result(false, 112);
			
		}
		if ((csoFinish.getTime() - csoStart.getTime()) > (Constant.ORDER_DAYS_DUR * SYSTEM.DAY))
			// 系统只提供15天长度的预订
			return new Result(false, 108);
		if (csoCar.equals(0l))
			// 需要选择一辆车
			return new Result(false, 109);
		
		CsOutlets csOutletsRet = csOutletsService.getCsOutletsById(baseOrderInfo.getCsoOutletsRet());
		if(csOutletsRet == null){
			return new Result(false, 114);
		}else{
			//还车网点的可用车位数不足
			if(csOutletsRet.getCsoPositionS()==null || csOutletsRet.getCsoPositionS()<=0){
				return new Result(false, 115);
			}
		}
		
		return rs.setResult(true);
	}
	
	/**
	 * 解析订单详情列表
	 * @param order
	 * @return
	 */
	public static List<Map<String, Object>> parseOrderDetails(CsOrder order){
		List<Map<String, Object>> retlist = new ArrayList<Map<String, Object>>();
		if(order!=null){
			List<CsOrderDetail> detailList = order.getCsoCostDetails();
			if(detailList!=null){
				for(CsOrderDetail detail : detailList){
					Map<String, Object> orderItem = new HashMap<String, Object>();
					
					//产品名称
					orderItem.put("csgName", detail.get$csodGoods().getCsgName());
					
					//产品开始时间
					orderItem.put("csodStart", detail.getCsodStart());
					
					//产品结束时间
					orderItem.put("csodEnd", detail.getCsodEnd());
					
					//折扣
					orderItem.put("csodRebate", detail.getCsodRebate());
					
					//单价
					orderItem.put("csodPrice", detail.getCsodPrice());
					
					//实际单价
					orderItem.put("csodRealPrice", detail.getCsodPrice() * detail.getCsodRebate());
					
					//数量
					if(detail.get$csodGoods().getCsgName().contains("公里") || detail.get$csodGoods().getCsgName().contains("里程")){
						orderItem.put("amountType", 2); //按小时
						orderItem.put("amount", detail.getCsodCount());
					}else{
						orderItem.put("amountType", 1); //按里程
						orderItem.put("amount", detail.getCsodEnd().getTime() - detail.getCsodStart().getTime());
					}
					
					//描述
					orderItem.put("csodRemark", detail.getCsodRemark());
					
					String mealText = "";
					if(order.getGifts()!=null){
						for(CsUseRecord  ogf : order.getGifts()){
							mealText += "#使用套餐优惠"+ogf.getCsurCount()+"个";
						}
					}
					
					//套餐描述
					orderItem.put("mealText", mealText);
					
					//实际使用优惠数量
					orderItem.put("csodCountReal", detail.getCsodCountReal());
					
					//实际总价
					orderItem.put("csodTotalReal", detail.getCsodTotalReal());
					retlist.add(orderItem);
				}
			}
		}
		return retlist;
	}
	
	
}