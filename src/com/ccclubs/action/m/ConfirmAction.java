package com.ccclubs.action.m;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ccclubs.action.m.home.AccountAction;
import com.ccclubs.action.m.util.ResponseEnvelope;
import com.ccclubs.action.web.BaseOrderInfo;
import com.ccclubs.action.weixin.RechargeHelper;
import com.ccclubs.action.weixin.alipay.util.AlipayNotify;
import com.ccclubs.config.Constant;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.DateHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsAlipayRecord;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsCreditCard;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.service.admin.ICsAlipayRecordService;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsCreditCardService;
import com.ccclubs.service.admin.ICsGoodsService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsOutletsService;
import com.ccclubs.service.admin.ICsPriceService;
import com.ccclubs.service.admin.ICsProductService;
import com.ccclubs.service.admin.ICsUserTypeService;
import com.ccclubs.service.admin.ISrvPropertyService;
import com.ccclubs.service.common.ICommonDisposeService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonOrderService;
import com.lazy3q.web.helper.$;

public class ConfirmAction {
	private ISrvPropertyService		srvPropertyService;
	private ICsOutletsService		csOutletsService;
	private ICsCarService			csCarService;
	private ICsMemberService		csMemberService;
	private ICsGoodsService			csGoodsService;
	private ICsPriceService			csPriceService;
	private ICsUserTypeService		csUserTypeService;
	private ICommonOrderService		commonOrderService;
	private ICommonDisposeService	commonDisposeService;
	private ICsOrderService			csOrderService;
	private ICommonMoneyService		commonMoneyService;
	private ICsProductService		csProductService;
	private ICsCreditCardService	csCreditCardService;
	
	public String execute(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (!WeixinHelper.isCorrectOpenId()) {
			// 微信openid不合规范
			return WeixinHelper.gotoLogin();
		}
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		String carmodel = $.getSession("carmodel");
		String carnumber = $.getSession("carnumber");
		String getcaroutlet = $.getSession("getcaroutlet");
		String returncaroutletid = $.getSession("returncaroutletid");
		String returncaroutlet = $.getSession("returncaroutlet");
		String starttime = $.getSession("starttime");
		String finishtime = $.getSession("finishtime");
		Double rentpay = $.getSession("rentpay");
		Double rentmargin = $.getSession("rentmargin");
		Double rentmile = $.getSession("rentmile");
		Double rentPayment = $.getSession("rentPayment");
		Double insurancePayment = $.getSession("insurancePayment");
		boolean credit = $.getSession("credit");
		String usecreditpay = $.getSession("usecreditpay");
		String freeze = $.getSession("freeze");
		Double usable = commonMoneyService.getUsableAmount(csMember.getCsmId()) + commonMoneyService.getCoin(csMember.getCsmHost(), csMember.getCsmId(), new Date());
		if(carmodel==null){
			return $.Redirect("index.html");
		}
		Long carid = $.getSession("carid");
		Long csoAvoid = $.getSession("csoAvoid");
		Double freeHours = $.getSession("freeHours");
		String yidiwd_value = $.getSession("yidiwd_value");
		
		$.SetRequest("carmodel", carmodel);
		$.SetRequest("carnumber", carnumber);
		$.SetRequest("getcaroutlet", getcaroutlet);
		$.SetRequest("returncaroutletid", returncaroutletid);
		$.SetRequest("returncaroutlet", returncaroutlet);
		$.SetRequest("starttime", starttime);
		$.SetRequest("finishtime", finishtime);
		$.SetRequest("rentpay", rentpay);
		$.SetRequest("rentmargin", rentmargin);
		$.SetRequest("rentmile", rentmile);
		$.SetRequest("rentPayment", rentPayment);
		$.SetRequest("insurancePayment", insurancePayment);
		$.SetRequest("credit", credit);
		$.SetRequest("usecreditpay", usecreditpay);
		$.SetRequest("freeze", freeze);
		$.SetRequest("usable", usable);
		if(usable<(rentpay+rentmargin+rentmile)){
			$.SetRequest("needpay", (rentpay+rentmargin+rentmile)-usable);
		}else{
			$.SetRequest("needpay", 0);
		}
		$.SetRequest("carid", carid);
		$.SetRequest("csoAvoid", csoAvoid);
		$.SetRequest("freeHours", freeHours);
		$.SetRequest("yidiwd_value", yidiwd_value);
		
		CsCar ccar = csCarService.getCsCarById(carid);
		CsOutlets csoutlet = csOutletsService.getCsOutletsById(ccar.getCscOutlets());
		$.SetRequest("csoutletLat", csoutlet.getCsoLatitude());
		$.SetRequest("csoutletLon", csoutlet.getCsoLongitude());
		return "confirm";
	}
	
	public String submit(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		try{
			CsMember csMember = WeixinHelper.getCsMember();
			if (!WeixinHelper.isCorrectOpenId()) {
				// 微信openid不合规范
				return WeixinHelper.gotoLogin();
			}
			if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
				return WeixinHelper.gotoLogin();
			}
			
			Long csoCar = $.getLong("csoCar", -1l);
			Long csoAvoid = $.getLong("csoAvoid");
			// 开始时间，结束时间
			Date csoStart = $.getDate("csoStart", null);
			Date csoFinish = $.getDate("csoFinish", null);
			double freeHours = $.getDouble("freeHours", 0d);
			// 区域，网点，车型
			if (csoStart == null){
				// 开始时间没有
				re.setState(false);
				re.setMessage("请设置取车时间");
				return $.SendAjax(re, "utf-8");
			}
			if (csoFinish == null){
				// 结束时间没有
				re.setState(false);
				re.setMessage("请设置还车时间");
				return $.SendAjax(re, "utf-8");
			}
			if ((csoStart.getTime() - new Date().getTime()) < (-2 * SYSTEM.HOUR)){
				// 开始时间不能早于当前时间两小时
				re.setState(false);
				re.setMessage("取车时间不能早于当前时间两小时");
				return $.SendAjax(re, "utf-8");
			}
			if (csoStart.after(csoFinish)){
				// 开始时间不能大于结束时间
				re.setState(false);
				re.setMessage("取车时间不能晚于还车时间");
				return $.SendAjax(re, "utf-8");
			}
			if ((csoFinish.getTime() - csoStart.getTime()) < (SYSTEM.HOUR)){
				// 租车时间最低1个小时起租
				re.setState(false);
				re.setMessage("最低1个小时起租");
				return $.SendAjax(re, "utf-8");
			}
			if (!SystemHelper.isCorrectOrderDate(csoStart) || !SystemHelper.isCorrectOrderDate(csoFinish)){
				// 租车时间最低1个小时起租
				re.setState(false);
				re.setMessage("取、还车时间应该是20分钟的倍数");
				return $.SendAjax(re, "utf-8");
			}
			if ((csoFinish.getTime() - csoStart.getTime()) > (Constant.ORDER_DAYS_DUR * SYSTEM.DAY)){
				// 系统只提供30天长度的预订
				re.setState(false);
				re.setMessage("网站只提供最长15天的订单");
				return $.SendAjax(re, "utf-8");
			}
			ICsCarService carService = $.GetSpringBean("csCarService");
			CsCar car = carService.getCsCarById(csoCar);
			if (car == null){
				// 需要选择一辆车
				re.setState(false);
				re.setMessage("请先选择一辆车，再预订");
				return $.SendAjax(re, "utf-8");
			}
			if (car.getCscStatus() == (short) 0){
				// 需要选择一辆车
				re.setState(false);
				re.setMessage("请先选择一辆车，再预订");
				return $.SendAjax(re, "utf-8");
			}
			// 检查该车辆，该时间段已经被预订
			if (commonOrderService.isExistOrderByTime(csoCar, csoStart, csoFinish, null)){
				re.setState(false);
				re.setMessage("亲，下手不够快哟～，你选择的时间已经被人预订啦。换个时间吧");
				return $.SendAjax(re, "utf-8");
			}
			
			BaseOrderInfo baseOrderInfo = new BaseOrderInfo();
			if (SystemHelper.isBaseOrderInfo()){
				baseOrderInfo = SystemHelper.getBaseOrderInf();
			}
			if (!baseOrderInfo.getCsoIsSpring()) {
				if ((csoStart.getTime() - new Date().getTime()) > (Constant.ORDER_DAYS_EARLY * SYSTEM.DAY)){
					// 系统只提供7天以内的预订
					re.setState(false);
					re.setMessage("网站提只提供7天以内的车辆预订");
					return $.SendAjax(re, "utf-8");
				}
			}
			Double usable = commonMoneyService.getUsableAmount(csMember.getCsmId()) + commonMoneyService.getCoin(csMember.getCsmHost(), csMember.getCsmId(), new Date());
			if (usable<0){
				re.setState(false);
				re.setMessage("您的账户存在问题，请联系客服");
				return $.SendAjax(re, "utf-8");
			}
			baseOrderInfo.setCsoFinish(csoFinish);
			baseOrderInfo.setCsoStart(csoStart);
			baseOrderInfo.setCsoCar(csoCar);
			baseOrderInfo.setCsoInsure(csoAvoid);
			if(!$.getString("yidiwd_value").equals("-1")){
				baseOrderInfo.setCsoOutletsRet(Long.parseLong($.getString("yidiwd_value")));
			}else{
				baseOrderInfo.setCsoOutletsRet(null);
			}
			// 设置定单基本信息 Session
			SystemHelper.setBaseOrderInfo(baseOrderInfo);
			
			/** ********支付人********* */
			// 驾驶认证没有审核通过
			if (csMember.getCsmVDrive() != null && csMember.getCsmVDrive() != (short) 1 && csMember.getCsmEvcard() != null) {
				if (csMember.getCsmVDrive() == (short) 2) {
					// 驾驶认证已经上传，请耐心等待
					re.setState(false);
					re.setMessage("你的驾驶证正在审核中...");
					return $.SendAjax(re, "utf-8");
				} else {
					// 驾驶认证没有上传
					re.setState(false);
					re.setMessage("你还没有上传驾驶证照片，你可以去 账户 里上传");
					return $.SendAjax(re, "utf-8");
				}
			}
			/** ********驾驶人********* */
			CsMember useMember = csMember;
			CsOrder csOrder = new CsOrder();
			ICsOrderService csOrderService = $.getBean("csOrderService");
			// 芝麻信用
			csCreditCardService = $.GetSpringBean("csCreditCardService");
			CsCreditCard card = csCreditCardService.getCsCreditCard($.add("csccMember", csMember.getCsmId()));
			Long credit_card_id = null;
			boolean is_credit = $.getBoolean("credit");
			if (is_credit && card != null) {
				credit_card_id = card.getCsccId();
				// 信用额度
				ICommonMoneyService moneyService = $.GetSpringBean("commonMoneyService");
				Double allCanUseMoney = moneyService.getUsableMoneyAndCouponAndCredit(csMember.getCsmId());
				if (allCanUseMoney <= 0) {
					re.setState(false);
					re.setMessage("信用余额不足");
					return $.SendAjax(re, "utf-8");
				}
			}
			
			// 平时订单处理
			if (!baseOrderInfo.getCsoIsSpring()) {
				if (credit_card_id != null) {
					csOrder = commonDisposeService.executeSaveOrder(csMember.getCsmId(), useMember.getCsmId(), credit_card_id, baseOrderInfo.getCsoCar(),
							car.getCscOutlets(), baseOrderInfo.getCsoOutletsRet(),
							baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish(), null, baseOrderInfo.getCsoInsure(), null, freeHours, null, 0l,
							WeixinHelper.switchOrderPlatform(WeixinHelper.getOpenId()),null);
				} else {
					csOrder = commonDisposeService.executeSaveOrder(csMember.getCsmId(), useMember.getCsmId(), baseOrderInfo.getCsoCar(),
							car.getCscOutlets(), baseOrderInfo.getCsoOutletsRet(),
							baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish(), null, baseOrderInfo.getCsoInsure(), null, freeHours, null, 0l,
							WeixinHelper.switchOrderPlatform(WeixinHelper.getOpenId()),null);
				}
			} else {
				// 春节订单处理，保证金为1500元
				if (credit_card_id != null) {
					csOrder = commonDisposeService.executeSaveOrder(csMember.getCsmId(), useMember.getCsmId(), credit_card_id, baseOrderInfo.getCsoCar(),
							car.getCscOutlets(), baseOrderInfo.getCsoOutletsRet(),
							baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish(), null, baseOrderInfo.getCsoInsure(), null, freeHours, 1500.0, 0l,
							WeixinHelper.switchOrderPlatform(WeixinHelper.getOpenId()),null);
				} else {
					csOrder = commonDisposeService.executeSaveOrder(csMember.getCsmId(), useMember.getCsmId(), baseOrderInfo.getCsoCar(),
							car.getCscOutlets(), baseOrderInfo.getCsoOutletsRet(),
							baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish(), null, baseOrderInfo.getCsoInsure(), null, freeHours, 1500.0, 0l,
							WeixinHelper.switchOrderPlatform(WeixinHelper.getOpenId()),null);
				}
				// 设置订单flag为春节租车
				csOrder.setCsoFlag((short) 1);
				csOrder.setCsoUpdateTime(new Date());
				csOrderService.updateCsOrder$NotNull(csOrder);
			}
			// 清除当前定单信息
			$.setSession(Constant.BASE_ORDER_INFO, null);
			// 更新默认取车点
			if (csMember.getCsmOutlets() == null) {
				CsMember newMember = new CsMember();
				newMember.setCsmOutlets(csOrder.getCsoOutlets());
				newMember.setCsmId(csMember.getCsmId());
				csMemberService.updateCsMember$NotNull(newMember);
			}
			
			$.SetRequest("csOrder", csOrder);
			re.setState(true);
			re.setMessage(String.valueOf(csOrder.getCsoId()));
			return $.SendAjax(re, "utf-8");
		} catch (com.ccclubs.exception.MessageException oe){
			oe.printStackTrace();
			re.setState(false);
			re.setMessage(oe.getMessage());
			return $.SendAjax(re, "utf-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			re.setState(false);
			re.setMessage("系统错误，请稍后再试");
			return $.SendAjax(re, "utf-8");
		}
	}
	
	public String alipay(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (!WeixinHelper.isCorrectOpenId()) {
			return WeixinHelper.gotoLogin();
		}
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}

		Double amount = $.getDouble("alipayneedpay");
		RechargeHelper.payFromAlipayToConfirm(csMember, amount, 0l, 0);
		return null;
	}
	@SuppressWarnings("rawtypes")
	public String callback() {
		try {
			HttpServletRequest request = $.getServletRequest();
			String orderSTT = request.getParameter("orderSTT");
			if ($.empty(orderSTT)) {
				// 获取支付宝POST过来反馈信息
				Map<String, String> params = new HashMap<String, String>();
				Map requestParams = request.getParameterMap();
				for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
					String name = (String) iter.next();
					String[] values = (String[]) requestParams.get(name);
					String valueStr = "";
					for (int i = 0; i < values.length; i++) {
						valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
					}
					// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
					// valueStr = new String(valueStr.getBytes("ISO-8859-1"),
					// "gbk");
					params.put(name, valueStr);
				}

				Logger.getLogger("alipay").info($.getQueryString("UTF-8"));

				// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
				// 商户订单号
				String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

				// 支付宝交易号

				String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

				// 交易状态
				String result = new String(request.getParameter("result").getBytes("ISO-8859-1"), "UTF-8");

				// log
				String classPath = AccountAction.class.getResource("/").toURI().getPath() + "rechargeTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + "_alipay.txt";
				WeixinHelper.writeAppendToFile(classPath, DateHelper.formatDate(new Date(), "HH:mm:ss") + " - " + out_trade_no + " - " + trade_no + " - notice", "UTF-8");

				// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
				// 计算得出通知验证结果
				boolean verify_result = AlipayNotify.verifyReturn(params);

				if (verify_result) {// 验证成功
					// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
					if (result.equals("success")) {
						// 判断该笔订单是否在商户网站中已经做过处理
						// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						// 如果有做过处理，不执行商户的业务程序
						/** **************本地业务处理******************* */
						// 该页面可做页面美工编辑
						$.SetRequest("success", true);
						ICsAlipayRecordService csAlipayRecordService = $.GetSpringBean("csAlipayRecordService");
						CsAlipayRecord csAlipayRecord = csAlipayRecordService.getCsAlipayRecord($.add("csarTradeNo", out_trade_no));
						$.SetRequest("from", csAlipayRecord.getCsarBlank());
					}
					// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				} else {
					// 该页面可做页面美工编辑
					$.SetRequest("success", false);// out.println("验证失败");
				}
			} else {
				// merID|orderDate|orderTime|orderId|currencyType|amount|remark|orderSTT|tranDate|tranTime|pyFlownNo
				// ---------------------------------杭州银行返回
				String hzbankOpenId = $.getString("openId");
				Logger.getLogger("hzbank").info($.getQueryString("UTF-8"));
				if (orderSTT.equals("1")) {
					$.SetRequest("success", true);
					$.SetRequest("from", hzbankOpenId);
				} else {
					$.SetRequest("success", false);
					$.SetRequest("from", hzbankOpenId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetRequest("success", false);
		}
		return "confirm";
	}
	
	public ISrvPropertyService getSrvPropertyService() {
		return srvPropertyService;
	}
	public void setSrvPropertyService(ISrvPropertyService srvPropertyService) {
		this.srvPropertyService = srvPropertyService;
	}
	public ICsOutletsService getCsOutletsService() {
		return csOutletsService;
	}
	public void setCsOutletsService(ICsOutletsService csOutletsService) {
		this.csOutletsService = csOutletsService;
	}
	public ICsCarService getCsCarService() {
		return csCarService;
	}
	public void setCsCarService(ICsCarService csCarService) {
		this.csCarService = csCarService;
	}
	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}
	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}
	public ICsGoodsService getCsGoodsService() {
		return csGoodsService;
	}
	public void setCsGoodsService(ICsGoodsService csGoodsService) {
		this.csGoodsService = csGoodsService;
	}
	public ICsPriceService getCsPriceService() {
		return csPriceService;
	}
	public void setCsPriceService(ICsPriceService csPriceService) {
		this.csPriceService = csPriceService;
	}
	public ICsUserTypeService getCsUserTypeService() {
		return csUserTypeService;
	}
	public void setCsUserTypeService(ICsUserTypeService csUserTypeService) {
		this.csUserTypeService = csUserTypeService;
	}
	public ICommonOrderService getCommonOrderService() {
		return commonOrderService;
	}
	public void setCommonOrderService(ICommonOrderService commonOrderService) {
		this.commonOrderService = commonOrderService;
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
	public ICsCreditCardService getCsCreditCardService() {
		return csCreditCardService;
	}
	public void setCsCreditCardService(ICsCreditCardService csCreditCardService) {
		this.csCreditCardService = csCreditCardService;
	}
}
