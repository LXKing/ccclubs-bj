package com.ccclubs.action.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ccclubs.action.unit.UnitLoginHelper;
import com.ccclubs.config.Constant;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.DateHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsGoods;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberShip;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsProduct;
import com.ccclubs.model.CsUserType;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsGoodsService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsMemberShipService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsProductService;
import com.ccclubs.service.admin.ICsUserTypeService;
import com.ccclubs.service.common.From;
import com.ccclubs.service.common.ICommonDisposeService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonOrderService;
import com.ccclubs.service.common.impl.OrderInfo;
import com.lazy3q.web.helper.$;

public class CheckoutAction {
	ICsCarService			csCarService;
	ICommonOrderService		commonOrderService;
	ICsUserTypeService		csUserTypeService;
	ICsMemberService		csMemberService;
	ICommonMoneyService		commonMoneyService;
	ICsProductService		csProductService;
	ICsGoodsService			csGoodsService;
	ICommonDisposeService	commonDisposeService;
	ICsOrderService			csOrderService;
	ICsMemberShipService 	csMemberShipService;

	/**
	 * 定单结算页面
	 * 
	 * @return
	 */
	public String execute() {
		BaseOrderInfo baseOrderInfo = new BaseOrderInfo();
		// Session 里没有基本订单信息
		if (!SystemHelper.isBaseOrderInfo() || SystemHelper.getBaseOrderInf().getCsoCar().equals(0l)) {
			return "failure";
		}
		baseOrderInfo = SystemHelper.getBaseOrderInf();
		$.SetRequest("baseOrderInfo", baseOrderInfo);

		CsCar csoCar = csCarService.getCsCarById(baseOrderInfo.getCsoCar());
		$.SetRequest("csoCar", csoCar);
		$.SetRequest("isRestriction", commonOrderService.isExistRestrictionByTime(baseOrderInfo.getCsoCar(), baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish()));
		
		return "checkout";
	}

	/**
	 * 预定时，时间产品拆分
	 * 
	 * @return
	 */
	public String detail() {
		try {
			// 获取保险的产品
			$.setRequest("isLogin", SystemHelper.isLogin()? 1 : 0);
			List<CsProduct> insureTypes = csProductService.getCsProductList($.add("definex", CsProduct.C.cspFlag + " = '" + Constant.SECURE + "' || " + CsProduct.C.cspFlag + " = '" + Constant.INSURE + "'"), -1);

			$.SetRequest("insureTypes", insureTypes);

			Long insureType = $.getLong("insureType", -1l);
			if (insureType.equals(-1l)) {
				// 保险类型，默认按小时购买
				CsProduct csProduct = csProductService.getCsProduct($.add(CsProduct.F.cspFlag, Constant.SECURE));
				insureType = csProduct.getCspId();
			}
			$.SetRequest("insureType", insureType);

			BaseOrderInfo baseOrderInfo = new BaseOrderInfo();
			// Session 里没有基本订单信息
			if (!SystemHelper.isBaseOrderInfo() || SystemHelper.getBaseOrderInf().getCsoCar().equals(0l)) {
				return $.SendHtml("false", SYSTEM.UTF8);
			} else {
				baseOrderInfo = SystemHelper.getBaseOrderInf();
			}

			CsCar csoCar = csCarService.getCsCarById(baseOrderInfo.getCsoCar());
			Double freehour = $.getDouble("freehour", 0d);
			OrderInfo orderInfo;
			// 获取小时数
			int allhours = (int) ((baseOrderInfo.getCsoFinish().getTime() - baseOrderInfo.getCsoStart().getTime()) / SYSTEM.HOUR);
			// 传入预计里程数，计算预计里程费用，计算最低帐户余额
			if (SystemHelper.isLogin()) {
				// 汽油车或电动车
				if (csoCar.get$cscModel().getCscmType() == (short) 0) {
					orderInfo = commonOrderService.splitOrderDetails(SystemHelper.getLoginId(), baseOrderInfo.getCsoCar(), baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish(), null, freehour, null, commonOrderService.getPredictKms(allhours) * 1.0, null, insureType, null, null);
				} else {
					orderInfo = commonOrderService.splitOrderDetails(SystemHelper.getLoginId(), baseOrderInfo.getCsoCar(), baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish(), null, freehour, commonOrderService.getPredictKms(allhours) * 1.0, null, null, insureType, null, null);
				}
				//orderInfo = commonOrderService.splitOrderDetails(SystemHelper.getLoginId(), baseOrderInfo.getCsoCar(), baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish(), null, freehour, null, commonOrderService.getPredictKms(allhours) * 1.0, null, insureType, null, null);
				Double memberhours = commonMoneyService.getCanUseFreeHour(SystemHelper.getLoginId(), new Date());
				// 最多可用免费小时
				$.SetRequest("maxhours", Math.min(memberhours, orderInfo.getOnehours()));
				// 帐户可用免费小时
				$.SetRequest("memberhours", memberhours);
				CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
				$.SetRequest("member", member);
				// 会员可用余额
				$.SetRequest("money", commonMoneyService.getUsableAmount(member.getCsmId()));
			} else {
				// TODO：当前定单本身的零散小时数与当前用户无关，commonMoneyService.splitOrderDetails
				// 需要改进
				// 传入预计里程，计算订单总费用
				// 汽油车或电动车
				if (csoCar.get$cscModel().getCscmType() == (short) 0) {
					orderInfo = commonOrderService.splitOrderDetails(SystemHelper.getLoginId(), baseOrderInfo.getCsoCar(), baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish(), null, freehour, null, commonOrderService.getPredictKms(allhours) * 1.0, null, insureType, null, null);
				} else {
					orderInfo = commonOrderService.splitOrderDetails(SystemHelper.getLoginId(), baseOrderInfo.getCsoCar(), baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish(), null, freehour, commonOrderService.getPredictKms(allhours) * 1.0, null, null, insureType, null, null);
				}
				$.SetRequest("maxhours", orderInfo.getOnehours());
			}
			freehour = orderInfo.getFreehour();
			// 设置免费小时，保险产品类型
			baseOrderInfo.setCsoFreeHours(freehour);
			baseOrderInfo.setCsoInsure(insureType);
			SystemHelper.setBaseOrderInfo(baseOrderInfo);
			// 获取是否使用免费小时标识
			Boolean isfreehour = $.getBoolean("isfreehour", false);

			$.SetRequest("isfreehour", isfreehour);
			$.SetRequest("freehour", freehour);
			$.SetRequest("orderInfo", orderInfo);
			$.SetRequest("baseOrderInfo", baseOrderInfo);

			$.SetRequest("csoCar", csoCar);
			
			$.SetRequest("isRestriction", commonOrderService.isExistRestrictionByTime(baseOrderInfo.getCsoCar(), baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish()));
			
//			Map<String, Object> params = new HashMap<String, Object>();
//			params.put("csmsTargeter", SystemHelper.getLoginId()); //使用账户
//			params.put("csmsStatus", 1);  //审核通过的状态
//			List<CsMemberShip> dataList = csMemberShipService.getCsMemberShipList(params, 100);
//			CsMemberShip self = new CsMemberShip();
//			self.setCsmsPayer(SystemHelper.getLoginId());
//			$.setRequest("selfName", self.getCsmsPayer$());
//			$.SetRequest("paidList", dataList);
		} catch (Exception ex) {
			ex.printStackTrace();
			// TODO：打印错误信息，网点车辆价格没有找到，请尝试选择其他区域网点
			return $.SendHtml("false", SYSTEM.UTF8);
		}
		return "detail";
	}

	/**
	 * 定单提交
	 * 
	 * @return
	 */
	public String submit() {
		try {
			BaseOrderInfo baseOrderInfo = new BaseOrderInfo();
			if (!SystemHelper.isLogin())
				return $.SendHtml("201", SYSTEM.UTF8);
			// Session 里没有基本订单信息
			if (!SystemHelper.isBaseOrderInfo() || SystemHelper.getBaseOrderInf().getCsoCar().equals(0l)) {
				return $.SendHtml("202", SYSTEM.UTF8);
			} else {
				baseOrderInfo = SystemHelper.getBaseOrderInf();
			}
			double freeHours = $.getDouble("freeHours", 0d);
			/** ********驾驶人********* */
			CsMember useMember = csMemberService.getCsMemberById(SystemHelper.getLoginId());
			// 驾驶认证没有审核通过
			if (useMember.getCsmVDrive() != null && useMember.getCsmVDrive() != (short) 1 && useMember.getCsmEvcard() != null) {
				if (useMember.getCsmVDrive() == (short) 2) {
					// 驾驶认证已经上传，请耐心等待
					return $.SendHtml("205", SYSTEM.UTF8);
				} else {
					// 驾驶认证没有上传
					return $.SendHtml("203", SYSTEM.UTF8);
				}
			}
			
			/** ********支付人********* */
			Long payMemberId = null;
			Long paid = $.getLong("paid");
			String remark = $.getString("remark");
			if(paid !=null && paid > 0L){
				CsMemberShip memberShip = csMemberShipService.getCsMemberShipById(paid);
				if(memberShip == null || memberShip.getCsmsTargeter() == null
						|| memberShip.getCsmsTargeter().longValue()!=useMember.getCsmId().longValue() || memberShip.getCsmsStatus() !=(short)1){
					return $.SendHtml("209", SYSTEM.UTF8);	//选择的代付会员参数不正确
				}
				payMemberId = memberShip.getCsmsPayer();
			}else{
				payMemberId = useMember.getCsmId();
			}
			
			String str = checkOrderInfo(baseOrderInfo);
			if(str == null || !str.equals("true"))return $.SendHtml("", "UTF-8");
			
			CsCar csoCar = csCarService.getCsCarById(baseOrderInfo.getCsoCar());
			if (csoCar==null) {
				return $.SendHtml("210", "UTF-8");
			}
			
			CsOrder csOrder = new CsOrder();
			// 平时订单处理
			if (!baseOrderInfo.getCsoIsSpring()) {
				csOrder = commonDisposeService.executeSaveOrder(payMemberId, useMember.getCsmId(), baseOrderInfo.getCsoCar(), csoCar.getCscOutlets(), baseOrderInfo.getCsoOutletsRet(), baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish(), null, baseOrderInfo.getCsoInsure(), null, freeHours, null, 0l,From.网站,null);
				//csOrder = commonDisposeService.executeSaveOrder(payMemberId, useMember.getCsmId(), baseOrderInfo.getCsoCar(), baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish(), null, baseOrderInfo.getCsoInsure(), null, freeHours, null, 0l, (short) 0);
			} else {
				// 春节订单处理，保证金为1500元
				csOrder = commonDisposeService.executeSaveOrder(payMemberId, useMember.getCsmId(), baseOrderInfo.getCsoCar(), csoCar.getCscOutlets(), baseOrderInfo.getCsoOutletsRet(), baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish(), null, baseOrderInfo.getCsoInsure(), null, freeHours, 1500.0, 0l,From.网站,null);
				//csOrder = commonDisposeService.executeSaveOrder(payMemberId, useMember.getCsmId(), baseOrderInfo.getCsoCar(), baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish(), null, baseOrderInfo.getCsoInsure(), null, freeHours, 1500.0, 0l, (short) 0);
				ICsOrderService csOrderService = $.getBean("csOrderService");
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
			// TODO：设置车辆人气，以便后面排序
			// 打印定单号
			return $.SendHtml(String.valueOf(csOrder.getCsoId()), SYSTEM.UTF8);
		} catch (com.ccclubs.exception.MessageException oe) {
			// $.SetTips(oe.getMessage());
			if (oe.getMessage().equals("会员可用余额不足")) {
				return $.SendHtml("204", SYSTEM.UTF8);
			} else if (oe.getMessage().equals("当前支付会员正在退款，不能下单")) {
				return $.SendHtml("206", SYSTEM.UTF8);
			} else if (oe.getMessage().equals("没有绑定EV卡，不能下单")) {
				return $.SendHtml("207", SYSTEM.UTF8);
			} else if (oe.getMessage().equals("当前订单时间已经被其它订单占用")) {
				return $.SendHtml("208", SYSTEM.UTF8);
			} else {
				return $.SendHtml(oe.getMessage(), SYSTEM.UTF8);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			// $.SetTips(SYSTEM.ERROR_TIPS);
			return $.SendHtml("false", SYSTEM.UTF8);
		}
	}

	/**
	 * 定单提交成功，根据定单号，显示授权码等信息
	 * 
	 * @return
	 */
	public String success() {
		try {
			if (!SystemHelper.isLogin())
				return SystemHelper.gotoLogin();
			Long order = $.getLong("order", 0l);
			CsOrder csOrder = csOrderService.getCsOrderById(order);
			// 不存在定单
			if (csOrder == null)
				return $.SendHtml("false", "UTF-8");
			// 订单使用会员和登陆账户对不上
			if (!csOrder.getCsoUseMember().equals(SystemHelper.getLoginId()))
				return $.SendHtml("false", "UTF-8");
			$.SetRequest("csOrder", csOrder);
			CsCar car = csCarService.getCsCarById(csOrder.getCsoCar());
			$.setRequest("car", car);
			
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			return $.SendHtml("false", "UTF-8");
		}
	}

	/**
	 * 提交续订
	 * 
	 * @return
	 */
	public String renewSubmit() {
		try {
			if (!SystemHelper.isLogin())
				return $.SendHtml("101", "UTF-8");
			Long order = $.getLong("order", 0l);
			Date newFinishDate = $.getDate("newFinishDate");
			Double freehour = $.getDouble("freehour", 0d);

			CsOrder csOrder = csOrderService.getCsOrderById(order);
			// 不存在定单
			if (csOrder == null)
				return $.SendHtml("false", "UTF-8");
			// 支付会员对不上
			if (!csOrder.getCsoPayMember().equals(SystemHelper.getLoginId()))
				return $.SendHtml("false", "UTF-8");
			if (newFinishDate == null)
				// 新的还车时间不能为空
				return $.SendHtml("102", "UTF-8");
			freehour = csOrder.getCsoFreehour();
			commonDisposeService.executeReOrder(order, newFinishDate, freehour,From.网站,null);

			return $.SendHtml("100", SYSTEM.UTF8);
		} catch (com.ccclubs.exception.MessageException oe) {
			// $.SetTips(oe.getMessage());
			return $.SendHtml(oe.getMessage(), SYSTEM.UTF8);
		} catch (Exception ex) {
			ex.printStackTrace();
			// $.SetTips(SYSTEM.ERROR_TIPS);
			return $.SendHtml("9999", SYSTEM.UTF8);
		}
	}

	/**
	 * 取消订单
	 * 
	 * @return
	 */
	public String cancel() {
		try {
			if (!SystemHelper.isLogin())
				return $.SendHtml("101", "UTF-8");
			Long orderid = $.getLong("orderid");
			CsOrder order = csOrderService.getCsOrderById(orderid);
			// 验证当前订单是不是当前用户的
			if (order == null || !order.getCsoPayMember().equals(SystemHelper.getLoginId())) {
				return $.SendHtml("102", "UTF-8");
			}
			if (order.getCsoStatus() != 0)
				return $.SendHtml("103", "UTF-8");
			commonDisposeService.executeCancelOrder(orderid, "会员自主取消订单",From.网站,null);
			// $.SetTips("订单取消成功!");
			return $.SendHtml("100", SYSTEM.UTF8);
		} catch (com.ccclubs.exception.MessageException oe) {
			// $.SetTips(oe.getMessage());
			return $.SendHtml(oe.getMessage(), "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			// $.SetTips(SYSTEM.ERROR_TIPS);
			return $.SendHtml("9999", "UTF-8");
		}
	}

	/**
	 * 验证定单信息
	 * 
	 * @return
	 */
	private String checkOrderInfo(BaseOrderInfo baseOrderInfo) {
		// 开始时间，结束时间
		Date csoStart = baseOrderInfo.getCsoStart();
		Date csoFinish = baseOrderInfo.getCsoFinish();
		// 车辆
		Long csoCar = baseOrderInfo.getCsoCar();
		if (csoStart == null)
			// 开始时间没有
			return $.SendHtml("101", SYSTEM.UTF8);
		if (csoFinish == null)
			// 结束时间没有
			return $.SendHtml("102", SYSTEM.UTF8);
		if ((csoStart.getTime() - new Date().getTime()) < (-2 * SYSTEM.HOUR))
			// 开始时间不能早于当前时间两小时
			return $.SendHtml("103", SYSTEM.UTF8);
		if (csoStart.after(csoFinish))
			// 开始时间不能大于结束时间
			return $.SendHtml("104", SYSTEM.UTF8);
		if ((csoFinish.getTime() - csoStart.getTime()) < (SYSTEM.HOUR))
			// 租车时间最低1个小时起租
			return $.SendHtml("105", SYSTEM.UTF8);
		if (!SystemHelper.isCorrectOrderDate(csoStart) || !SystemHelper.isCorrectOrderDate(csoFinish))
			// 开始时间、结束时间，不符合定单时间要求，应该是20分钟的整数倍
			return $.SendHtml("106", SYSTEM.UTF8);
		// 节假日租车时间特殊化未处理!!
		if (!baseOrderInfo.getCsoIsSpring()) {
			if ((csoStart.getTime() - new Date().getTime()) > (Constant.ORDER_DAYS_EARLY * SYSTEM.DAY))
				// 系统只提供7天以内的预订
				return $.SendHtml("107", SYSTEM.UTF8);
		} else {
			// 春节租车需要包含 1月30日17点到2月7号9点
			Date startDate = DateHelper.getDate(2015, 2, 17, 17, 0, 0);
			Date endDate = DateHelper.getDate(2015, 2, 25, 8, 40, 0);
			if (csoStart.after(startDate)) {
				// 亲，春节租车，需要选择在2月17日17点前提车
				return $.SendHtml("110", SYSTEM.UTF8);
			}
			if (endDate.after(csoFinish)) {
				// 亲，春节租车，需要选择在2月25日9点后还车
				return $.SendHtml("111", SYSTEM.UTF8);
			}
			
			//2015年使用春节套餐租车最短要租满10天
			if ((csoFinish.getTime() - csoStart.getTime()) < (10 * SYSTEM.DAY))
				return $.SendHtml("112", SYSTEM.UTF8);
			
		}
		if ((csoFinish.getTime() - csoStart.getTime()) > (Constant.ORDER_DAYS_DUR * SYSTEM.DAY))
			// 系统只提供15天长度的预订
			return $.SendHtml("108", SYSTEM.UTF8);
		if (csoCar.equals(0l))
			// 需要选择一辆车
			return $.SendHtml("109", SYSTEM.UTF8);
		return "true";
	}

	public ICsCarService getCsCarService() {
		return csCarService;
	}

	public void setCsCarService(ICsCarService csCarService) {
		this.csCarService = csCarService;
	}

	public ICommonOrderService getCommonOrderService() {
		return commonOrderService;
	}

	public void setCommonOrderService(ICommonOrderService commonOrderService) {
		this.commonOrderService = commonOrderService;
	}

	public ICsUserTypeService getCsUserTypeService() {
		return csUserTypeService;
	}

	public void setCsUserTypeService(ICsUserTypeService csUserTypeService) {
		this.csUserTypeService = csUserTypeService;
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}

	public ICommonMoneyService getCommonMoneyService() {
		return commonMoneyService;
	}

	public void setCommonMoneyService(ICommonMoneyService commonMoneyService) {
		this.commonMoneyService = commonMoneyService;
	}

	public ICsProductService getCsProductService() {
		return csProductService;
	}

	public void setCsProductService(ICsProductService csProductService) {
		this.csProductService = csProductService;
	}

	public ICsGoodsService getCsGoodsService() {
		return csGoodsService;
	}

	public void setCsGoodsService(ICsGoodsService csGoodsService) {
		this.csGoodsService = csGoodsService;
	}

	public ICommonDisposeService getCommonDisposeService() {
		return commonDisposeService;
	}

	public void setCommonDisposeService(ICommonDisposeService commonDisposeService) {
		this.commonDisposeService = commonDisposeService;
	}

	public ICsOrderService getCsOrderService() {
		return csOrderService;
	}

	public void setCsOrderService(ICsOrderService csOrderService) {
		this.csOrderService = csOrderService;
	}

	public ICsMemberShipService getCsMemberShipService() {
		return csMemberShipService;
	}

	public void setCsMemberShipService(ICsMemberShipService csMemberShipService) {
		this.csMemberShipService = csMemberShipService;
	}

}
