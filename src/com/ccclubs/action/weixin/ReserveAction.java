package com.ccclubs.action.weixin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ccclubs.action.web.BaseOrderInfo;
import com.ccclubs.config.Constant;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.config.SYSTEM.RuleName;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsArea;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsCarModel;
import com.ccclubs.model.CsCreditBill;
import com.ccclubs.model.CsCreditCard;
import com.ccclubs.model.CsFeeTypeSet;
import com.ccclubs.model.CsGoods;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsPrice;
import com.ccclubs.model.CsProduct;
import com.ccclubs.model.Restriction;
import com.ccclubs.model.SrvHost;
import com.ccclubs.service.admin.ICsAreaService;
import com.ccclubs.service.admin.ICsCarModelService;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsCreditBillService;
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
import com.ccclubs.service.common.impl.OrderInfo;
import com.ccclubs.util.DateUtil;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;
import com.lazy3q.web.util.Page;

public class ReserveAction {
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
	// ---------------车辆id
	Long							car;
	// ---------------最近 amount 天的车辆使用情况
	String							date;

	/**
	 * 订车
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String execute() {
		BaseOrderInfo baseOrderInfo = new BaseOrderInfo();

		if (!SystemHelper.isBaseOrderInfo()) {
			baseOrderInfo = SystemHelper.setDefaultOrderTime(baseOrderInfo);

			// CsMember csMember = WeixinHelper.getCsMember();
			// if (csMember != null) {
			// // 默认网点不为空
			// CsOutlets csOutlets = csMember.get$csmOutlets();
			// if (csOutlets != null) {
			// baseOrderInfo.setCsoArea(csOutlets.getCsoArea());
			// baseOrderInfo.setCsoOutlets(csOutlets.getCsoId());
			// baseOrderInfo.setCsoLatitude(csOutlets.getCsoLatitude());
			// baseOrderInfo.setCsoLongitude(csOutlets.getCsoLongitude());
			// } else {
			// // 默认取车网点，这里默认选择 西湖区，银马公寓
			// baseOrderInfo.setCsoArea(5l);
			// baseOrderInfo.setCsoOutlets(1065l);
			// }
			// }
			// 设置定单基本信息 Session
			SystemHelper.setBaseOrderInfo(baseOrderInfo);
		} else {
			baseOrderInfo = SystemHelper.getBaseOrderInf();
			if (baseOrderInfo.getCsoIsSpring()) {
				baseOrderInfo = SystemHelper.setDefaultOrderTime(baseOrderInfo);
				baseOrderInfo.setCsoIsSpring(false);
			}
			// baseOrderInfo = SystemHelper.setDefaultOrderTime(baseOrderInfo);
		}

		// 最近 7天
		List<Date> dates = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		dates.add(calendar.getTime());
		for (int i = 0; i < 6; i++) {
			calendar.add(Calendar.DATE, 1);
			dates.add(calendar.getTime());
		}
		$.SetRequest("dates", dates);
		// 当前选中的日期的 index
		$.SetRequest("index", $.getInteger("index", 0));
		// 获取页面条件
		String date = $.getString("csoDate", "");
		if ($.empty(date)) {
			date = $.date(new Date(), "yyyy-MM-dd");
		}
		Long csoHost = $.getLong("csoHost", Long.parseLong($.config("web.host")));
		if (csoHost.equals(baseOrderInfo.getCsoHost())) {
			// 取车区域
			Long csoArea = $.getLong("csoArea", 0l);
			if (!csoArea.equals(0l)) {
				baseOrderInfo.setCsoArea(csoArea);
				ICsAreaService csAreaService = $.GetSpringBean("csAreaService");
				CsArea csoAreaModel = csAreaService.getCsAreaById(csoArea);
				if (csoAreaModel != null) {
					baseOrderInfo.setCsoAreaName(csoAreaModel.getCsaName());
				}
			}

			// 取车网点
			Long csoOutlets = $.getLong("csoOutlets", 0l);
			if (!csoOutlets.equals(0l))
				baseOrderInfo.setCsoOutlets(csoOutlets);
			// 设置所选网点
			CsOutlets csOutlets = csOutletsService.getCsOutletsById(csoOutlets);
			$.SetRequest("csOutlets", csOutlets);
			// 设置车型
			Long csoModel = $.getLong("csoModel", 0l);
			if (!csoModel.equals(0l))
				baseOrderInfo.setCsoModel(csoModel);
		}
		// 设置宣传车
		baseOrderInfo.setCsoIsAdCar($.getBoolean("csoIsAdCar", false));
		// 设置是否日期参与筛选条件
		Boolean csoIsDate = $.getBoolean("csoIsDate", false);
		baseOrderInfo.setCsoIsDate(csoIsDate);
		// 设置查看多长时间内的车辆租赁情况
		int csoAmount = $.getInteger("csoAmount", 2);
		if (csoAmount != 2 && csoAmount != 3 && csoAmount != 7) {
			csoAmount = 2;
		}
		baseOrderInfo.setCsoAmount(csoAmount);
		$.SetRequest("baseOrderInfo", baseOrderInfo);
		// TODO：如果没有结果，返回的是？
		Map<String, Object> params = getCarParams(baseOrderInfo);
		Page<CsCar> page = csCarService.getCsCarPage($.getInteger("page", 0), 5, params);
		List<CsCar> list = page.getResult();
		List<CsCar> list_x = new ArrayList<CsCar>();
		for(CsCar cscar : list){
			Long carid = cscar.getCscId();
			String today = new DateUtil().dateToString(new Date(), "yyyy-MM-dd");
			String start = today + " 00:00:00";
			String finish = today + " 23:59:59";
			boolean xianxing = commonOrderService.isExistRestrictionByTime(carid, new DateUtil().StringtoDate(start, null), new DateUtil().StringtoDate(finish, null));
			if(xianxing){
				cscar.setValues("xianxing", "1");
			}
			list_x.add(cscar);
		}
		page.setResult(list_x);
		$.SetRequest("page", page);

		$.SetRequest("from", $.getString("from"));
		$.SetRequest("date", date);
		// 设置可选区域
		ICsAreaService csAreaService = Lazy.GetSpringBean("csAreaService");
		List<CsArea> areas = csAreaService.getCsAreaList($.add(CsArea.F.csaHost, SystemHelper.getSrvHost().getShId()).add("desc", CsArea.C.csaLevel), -1);
		Lazy.SetRequest("areas", areas);

		ICsCarModelService csCarModelService = $.GetSpringBean("csCarModelService");
		List<CsCarModel> carModels = SystemHelper.getHostModels(SystemHelper.getSrvHost().getShId());//csCarModelService.getCsCarModelList($.add("definex", CsCarModel.C.cscmHosts + " like '%#" + SystemHelper.getSrvHost().getShId() + "#%'").add(CsCarModel.F.cscmStatus, 1).add("desc", CsCarModel.C.cscmType), -1);
		
		$.SetRequest("carModels", carModels);
		return "reserve";
	}

	/**
	 * 更多车辆信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@Deprecated
	public String more() {
		// 设置定单基本信息
		try {
			BaseOrderInfo baseOrderInfo = new BaseOrderInfo();
			if (SystemHelper.isBaseOrderInfo())
				baseOrderInfo = SystemHelper.getBaseOrderInf();

			Map<String, Object> params = getCarParams(baseOrderInfo);
			Page<CsCar> page = csCarService.getCsCarPage($.getInteger("page", 0), 5, params);
			List<CsCar> list = page.getResult();
			List<CsCar> list_x = new ArrayList<CsCar>();
			for(CsCar cscar : list){
				Long carid = cscar.getCscId();
				String today = new DateUtil().dateToString(new Date(), "yyyy-MM-dd");
				String start = today + " 00:00:00";
				String finish = today + " 23:59:59";
				boolean xianxing = commonOrderService.isExistRestrictionByTime(carid, new DateUtil().StringtoDate(start, null), new DateUtil().StringtoDate(finish, null));
				if(xianxing){
					cscar.setValues("xianxing", "1");
				}
				list_x.add(cscar);
			}
			page.setResult(list_x);
			$.SetRequest("page", page);
			$.SetRequest("date", $.getString("date"));
			return "more";
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 订单结算信息
	 * 
	 * @return
	 * @throws IOException
	 */
	public String checkout() throws IOException {
		try {
			// 当前选中的日期的 index
			$.SetRequest("index", $.getInteger("index", 0));
			// 设置定单基本信息
			CsMember csMember = WeixinHelper.getCsMember();
			if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
				// 微信openid不合规范
				// return $.SendHtml("209", SYSTEM.UTF8);
				return WeixinHelper.gotoLogin();
			}

			double freehour = 0;
			if (csMember != null) {
				freehour = commonMoneyService.getCanUseFreeHour(csMember.getCsmId(), new java.util.Date());
			}
			$.SetRequest("freehour", freehour);
			// 设置定单基本信息
			Long csoCar = $.getLong("car", -1l);

			// 获取保险的产品
			// 保险类型，默认按小时购买
			CsProduct csProductHour = csProductService.getCsProduct($.add(CsProduct.F.cspFlag, Constant.SECURE));
			CsProduct csProductMile = csProductService.getCsProduct($.add(CsProduct.F.cspFlag, Constant.INSURE));
			$.SetRequest("csProductHour", csProductHour);
			$.SetRequest("csProductMile", csProductMile);
			Long insureType = $.getLong("insureType", -1l);
			if (insureType.equals(-1l)) {
				// 保险类型，默认按小时购买
				// CsProduct csProduct =
				// csProductService.getCsProduct($.add(CsProduct.F.cspFlag,
				// Constant.SECURE));
				insureType = csProductHour.getCspId();
			}
			$.SetRequest("insureType", insureType);

			// 最近 7天
			List<Date> dates = new ArrayList<Date>();
			Calendar calendar = Calendar.getInstance();
			dates.add(calendar.getTime());
			for (int i = 0; i < 6; i++) {
				calendar.add(Calendar.DATE, 1);
				dates.add(calendar.getTime());
			}
			$.SetRequest("dates", dates);
			// 获取页面条件
			String date = $.getString("csoDate", $.date(new Date(), "yyyy-MM-dd"));
			$.SetRequest("date", date);
			CsCar csCar = csCarService.getCsCarById(csoCar);
			if (csCar == null || csCar.getCscStatus() == (short) 0)
			// 需要选择一辆车
			{
				ServletActionContext.getResponse().sendRedirect($.GetRequest("basePath") + "weixin/reserve.html");
				return null;
			}

			BaseOrderInfo baseOrderInfo = new BaseOrderInfo();
			if (SystemHelper.isBaseOrderInfo()) {
				baseOrderInfo = SystemHelper.setDefaultOrderTime(baseOrderInfo);
			}

			// 车辆价格
			List<CsPrice> listPrice = new ArrayList<CsPrice>();
			Long outlets = csCar.getCscOutlets();
			Long feeType = 3L;// 默认为默认计费类型
			
			feeType = CsFeeTypeSet.Get(
					$.add(CsFeeTypeSet.F.csftsHost, csCar.getCscHost())
					.add(CsFeeTypeSet.F.csftsModel, csCar.getCscMobile$())
				).getCsftsDefault();
			
			if (csCar.getCscHost() == 2) {
				feeType = 7L;// 北京的计费类型为7
			}
			// 价格列表
			listPrice = csPriceService.getCsPriceList($.add(CsPrice.F.cspUserType, feeType).add(CsPrice.F.cspOutets, outlets).add(CsPrice.F.cspModel, csCar.getCscModel()).add("desc", CsPrice.C.cspGoods), -1);

			// 如果没有，则以默认网点价格作为参考
			CsFeeTypeSet feeTypeSet = CsFeeTypeSet.Get($.add(CsFeeTypeSet.F.csftsModel, csCar.getCscModel()).add(CsFeeTypeSet.F.csftsHost, CsOutlets.get(csCar.getCscOutlets()).getCsoHost()));
			if (listPrice.isEmpty() && feeTypeSet != null && feeTypeSet.getCsftsOutlets() != null) {
				listPrice = csPriceService.getCsPriceList($.add(CsPrice.F.cspUserType, feeTypeSet.getCsftsDefault()).add(CsPrice.F.cspOutets, feeTypeSet.getCsftsOutlets()).add(CsPrice.F.cspModel, csCar.getCscModel()).add("desc", CsPrice.C.cspGoods), -1);
				feeType = feeTypeSet.getCsftsDefault();
			}
			$.SetRequest("listPrice", listPrice);

			CsGoods kmGoods = commonOrderService.getGoodsByFlag(csCar.get$cscModel().getCscmType().shortValue() == 0 ? SYSTEM.MILEAGE : SYSTEM.KILOM, RuleName.每公里, feeType);
			if (kmGoods != null) {// 有可能有些车辆没有关于里程的商品
				Map<String, Object> elecParams = new HashMap<String, Object>();
				elecParams.put(CsPrice.F.cspOutets, csCar.getCscOutlets());
				elecParams.put(CsPrice.F.cspUserType, feeType);
				elecParams.put(CsPrice.F.cspModel, csCar.getCscModel());
				elecParams.put(CsPrice.F.cspGoods, kmGoods.getCsgId());
				CsPrice kmPrice = csPriceService.getCsPrice(elecParams);

				if (kmPrice == null && feeTypeSet != null) {
					elecParams.put(CsPrice.F.cspOutets, feeTypeSet.getCsftsOutlets());
					elecParams.put(CsPrice.F.cspUserType, feeType);
					kmPrice = csPriceService.getCsPrice(elecParams);
				}
				$.SetRequest("oilPrice", kmPrice);
				$.SetRequest("elecPrice", kmPrice);
			} else {
				$.SetRequest("oilPrice", null);
				$.SetRequest("elecPrice", null);
			}

			$.SetRequest("baseOrderInfo", baseOrderInfo);
			$.SetRequest("csoCar", csCar);

			// 信用卡，免押金余额
			Double limit = 0d;
			ICommonMoneyService moneyService = $.GetSpringBean("commonMoneyService");
			Double fund = moneyService.getUsableCredit(csMember.getCsmId());
			ICsCreditCardService cardService = $.GetSpringBean("csCreditCardService");
			CsCreditCard card = cardService.getCsCreditCard($.add("csccMember", csMember.getCsmId()));
			if (card == null) {
				limit = 0d;
			} else {
				if (card.getCsccStatus().toString().equals("1")) {
					limit = fund;
				}
			}
			$.SetRequest("limit", limit);
			$.SetRequest("testUser", true);
			
			//限行
			String today = new DateUtil().dateToString(new Date(), "yyyy-MM-dd");
			String start = today + " 00:00:00";
			String finish = today + " 23:59:59";
			boolean xianxing = commonOrderService.isExistRestrictionByTime(csCar.getCscId(), new DateUtil().StringtoDate(start, null), new DateUtil().StringtoDate(finish, null));
			if(xianxing){
				$.SetRequest("xianxing", "1");
			}
			
			//异地还车
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(CsOutlets.F.csoHost, SystemHelper.getSrvHost().getShId());
			params.put(CsOutlets.F.csoStatus, "1");
			params.put(CsOutlets.F.csoCanOrder, "1");
			params.put(CsOutlets.F.csoCanB, "1");
			// 可用车位数大于0
			params.put("definex", CsOutlets.C.csoId + " <> " + csCar.getCscOutlets() + " AND (" + CsOutlets.C.csoPositionS + " - " + CsOutlets.C.csoCarS + " > 0)");
			List<CsOutlets> huanche_outlets = csOutletsService.getCsOutletsList(params, -1);
			// 还车网点列表
			$.SetRequest("huanche_outlets", huanche_outlets);
			ICsOutletsService outletsService = $.GetSpringBean("csOutletsService");
			CsOutlets csOutlets = outletsService.getCsOutletsById(outlets);
			if(csOutlets.getCsoCanA()){
				$.SetRequest("aret", "A");
			}
			$.SetRequest("currentOutlet", csOutlets.getCsoName$());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
		}
		return "checkout";
	}

	/**
	 * 获得订单的大概费用
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	public String confirm() {
		try {
			// 设置定单基本信息
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
			// 区域，网点，车型
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

			if ((csoFinish.getTime() - csoStart.getTime()) > (Constant.ORDER_DAYS_DUR * SYSTEM.DAY))
				// 系统只提供30天长度的预订
				return $.SendHtml("108", SYSTEM.UTF8);

			ICsCarService carService = $.GetSpringBean("csCarService");
			CsCar car = carService.getCsCarById(csoCar);
			ICsCarModelService modelService = $.GetSpringBean("csCarModelService");
			String carType = modelService.getCsCarModelById(car.getCscModel()).getCscmType$();
			if (carType.equals("电动车")) {
				long tempTime = csoStart.getTime() - System.currentTimeMillis();
				double hours = (double) tempTime / 1000 / 3600;
				if (hours > 2) {
					return $.SendHtml("404", SYSTEM.UTF8);
				}
			}

			if (car == null)
				// 需要选择一辆车
				return $.SendHtml("109", SYSTEM.UTF8);
			if (car.getCscStatus() == (short) 0)
				// 需要选择一辆车
				return $.SendHtml("109", SYSTEM.UTF8);
			// 检查该车辆，该时间段已经被预订
			if (commonOrderService.isExistOrderByTime(csoCar, csoStart, csoFinish, null))
				return $.SendHtml("111", SYSTEM.UTF8);

			double freeHours = $.getDouble("freeHours", 0d);
			/** ********支付人********* */
			// 驾驶认证没有审核通过
			if (csMember.getCsmVDrive() != null && csMember.getCsmVDrive() != (short) 1 && csMember.getCsmEvcard() != null) {
				if (csMember.getCsmVDrive() == (short) 2) {
					// 驾驶认证已经上传，请耐心等待
					return $.SendHtml("205", SYSTEM.UTF8);
				} else {
					// 驾驶认证没有上传
					return $.SendHtml("203", SYSTEM.UTF8);
				}
			}
			/** ********驾驶人********* */
			// CsMember useMember = csMember;
			// 拆分订单
			OrderInfo orderinfo = commonOrderService.splitOrderDetails(csMember.getCsmId(), car.getCscId(), csoStart, csoFinish, null, freeHours, null, null, null, csoAvoid, null, null);

			String yidiwd_value = $.getString("yidiwd_value");
			String yidiwd_text = "no";
			if(!yidiwd_value.equals("-1")){
				yidiwd_text = java.net.URLDecoder.decode($.getString("yidiwd_text"), "utf-8");
			}
			
			// 是否使用芝麻信用
			ICsCreditCardService creditCardService = $.GetSpringBean("csCreditCardService");
			CsCreditCard creditCard = creditCardService.getCsCreditCard($.add("csccMember", csMember.getCsmId()));
			Double limit = 0d;
			if (creditCard != null) {
				limit = creditCard.getCsccLimit();
			}
			if ($.getString("credit").equals("checked")) {
				// 判断信用额度是否够下单
				ICommonMoneyService commonMoneyService = $.GetSpringBean("commonMoneyService");
				if ((orderinfo.getPrice() + orderinfo.getMargin() + orderinfo.getFeeee()) > (commonMoneyService.getUsableCredit(csMember.getCsmId()))) {
					return $.SendHtml("444", SYSTEM.UTF8);
				}
				if ((csoFinish.getTime() - csoStart.getTime()) > (1 * SYSTEM.DAY)){
					return $.SendHtml("445", SYSTEM.UTF8);
				}
			}
			String html = getCreditConfirmHtml(orderinfo,limit,car,csoStart,csoFinish,$.getString("credit"),yidiwd_text);
			
			return $.SendHtml(html,SYSTEM.UTF8);
		} catch (Exception ex) {
			ex.printStackTrace();
			// $.SetTips(SYSTEM.ERROR_TIPS);
			return $.SendHtml("false", SYSTEM.UTF8);
		}
	}

	private String getCreditConfirmHtml(OrderInfo orderinfo, Double limit, CsCar car, Date csoStart, Date csoFinish, String is_credit, String yidiwd_text) {
		Double allPayment = orderinfo.getPrice() + orderinfo.getMargin() + orderinfo.getFeeee();
		String html = "<div class=\"bigmain\"><div class=\"buytitle\"><div class=\"content\"><div class=\"showtitle\">订单确认</div></div></div><div style=\"padding:2px 0;\">"
				+ "<div style=\"margin:0 0 8px 1%;\">###credit<br/><br/><span style=\"color:#00a3da;font-size:20px;\">订单信息</span></div><div class=\"confirmbox\"><ul>"
				+ "<li style=\"height:50px; line-height:25px; border-top: none;\"><b>###carType，###outlet</b><span style=\"float:right;\">###startTime</span><br>" + "<span style=\"float:right;\">###finishTime</span></li>"
				+ "###yidi"
				+ "<li style=\"height:25px; line-height:25px; border-top: none;\"><b>租金</b><span style=\"float:right;\">###rentPayment元</span></li>"
				+ "<li style=\"height:25px; line-height:25px; border-top: none;\"><b>租金保证金</b><span style=\"float:right;\">###rentMargin元</span></li>"
				+ "<li style=\"height:25px; line-height:25px; border-top: none;\"><b>里程费[预估]</b><span style=\"float:right;\">###rentMile元</span>###useCredit</li></ul></div>" + "<div style=\"padding-top:5px;padding-left:1%;\">###freeze</div>"
				+ "<div style=\"padding-left:1%;padding-top:5px;clear:both;\">注：订单结束时将根据实际消费产生的费用结算</div></div></div>";
		//限行
		boolean xianxing = commonOrderService.isExistRestrictionByTime(car.getCscId(), csoStart, csoFinish);
		if(xianxing){
			Long host = car.getCscHost();
			String xianxingUrl = "";
			if(host==1){
				xianxingUrl = "http://zjnews.zjol.com.cn/system/2014/05/04/020003783.shtml";
			}else if(host==2){
				xianxingUrl = "http://www.bjjtgl.gov.cn/zhuanti/10weihao/index.html";
			}
			html = html + "<div style=\"padding-left:1%;padding-top:5px;clear:both;\">您选择的车辆在订单时间内有可能会受到<span style=\"color: red;\">错峰限行</span>的影响。查看<a href=\"" + xianxingUrl +
					"\" style=\"color: blue;\">错峰限行时间</a>和<a href=\"" + xianxingUrl +
					"\" style=\"color: blue;\">区域</a>（仅供参考）</div>";
		}
		if(yidiwd_text.equals("no")){
			html = html.replace("###yidi", "");
		}else{
			html = html.replace("###yidi", "<li style=\"height:25px; line-height:25px; border-top: none;\"><b>异地还车网点</b><span style=\"float:right;\">" + yidiwd_text + "</span></li>");
		}
		html = html.replace("###carType", car.getCscModel$());
		html = html.replace("###outlet", car.getCscOutlets$());
		html = html.replace("###startTime", new DateUtil().dateToString(csoStart, "yyyy-MM-dd HH:mm"));
		html = html.replace("###finishTime", new DateUtil().dateToString(csoFinish, "yyyy-MM-dd HH:mm"));
		html = html.replace("###rentPayment", orderinfo.getPrice().toString());
		html = html.replace("###rentMargin", orderinfo.getMargin().toString());
		html = html.replace("###rentMile", orderinfo.getFeeee().toString());
		if (!(limit == 0)) {
			if (is_credit.equals("checked")) {
				html = html.replace("###credit", "<span><input type=\"checkbox\" id=\"credit_use\" name=\"credit_use\" checked disabled=\"disabled\">已使用免押金额度下单</span>");
			} else {
				html = html.replace("###credit", "");
			}
			if (allPayment <= 2000 && is_credit.equals("checked")) {
				html = html.replace("###useCredit", "<li><b>使用免押金额</b><span>" + $.digit(allPayment, 2) + "元</span></li>");
				html = html.replace("###freeze", "<span style=\"float:left\">车纷享账户冻结余额：</span><span style=\"float:right;padding-right:1%;\">0元</span>");
			} else if (allPayment > 2000 && is_credit.equals("checked")) {
				html = html.replace("###useCredit", "<li><b>使用免押金额</b><span>2000元</span></li>");
				html = html.replace("###freeze", "<span style=\"float:left\">车纷享账户冻结余额：</span><span style=\"float:right;padding-right:1%;\" >" + $.digit(allPayment - 2000, 2) + "元</span>");
			} else {
				html = html.replace("###useCredit", "");
				html = html.replace("###freeze", "<span style=\"float:left\">车纷享账户冻结余额：</span><span style=\"float:right;padding-right:1%;\" >" + $.digit(allPayment, 2) + "元</span>");
			}
		} else {
			html = html.replace("###credit", "");
			html = html.replace("###useCredit", "");
			html = html.replace("###freeze", "<span style=\"float:left\">车纷享账户冻结余额：</span><span style=\"float:right;padding-right:1%;\" >" + $.digit(allPayment, 2) + "元</span>");
		}
		return html;
	}

	/**
	 * 定单提交
	 * 
	 * @return
	 */
	public String submit() {
		try {
			// 设置定单基本信息
			CsMember csMember = WeixinHelper.getCsMember();

			if (!WeixinHelper.isCorrectOpenId()) {
				// 微信openid不合规范
				// return $.SendHtml("209", SYSTEM.UTF8);
				return WeixinHelper.gotoLogin();
			}
			if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
				// return $.SendHtml("201", SYSTEM.UTF8);
				return WeixinHelper.gotoLogin();
			}

			Long csoCar = $.getLong("csoCar", -1l);
			Long csoAvoid = $.getLong("csoAvoid");

			// 开始时间，结束时间
			Date csoStart = $.getDate("csoStart", null);
			Date csoFinish = $.getDate("csoFinish", null);
			// 区域，网点，车型
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

			if ((csoFinish.getTime() - csoStart.getTime()) > (Constant.ORDER_DAYS_DUR * SYSTEM.DAY))
				// 系统只提供30天长度的预订
				return $.SendHtml("108", SYSTEM.UTF8);

			CsCar car = csCarService.getCsCarById(csoCar);
			if (car == null)
				// 需要选择一辆车
				return $.SendHtml("109", SYSTEM.UTF8);
			if (car.getCscStatus() == (short) 0)
				// 需要选择一辆车
				return $.SendHtml("109", SYSTEM.UTF8);
			// 检查该车辆，该时间段已经被预订
			if (commonOrderService.isExistOrderByTime(csoCar, csoStart, csoFinish, null))
				return $.SendHtml("111", SYSTEM.UTF8);

			BaseOrderInfo baseOrderInfo = new BaseOrderInfo();
			if (SystemHelper.isBaseOrderInfo())
				baseOrderInfo = SystemHelper.getBaseOrderInf();

			if (!baseOrderInfo.getCsoIsSpring()) {
				if ((csoStart.getTime() - new Date().getTime()) > (Constant.ORDER_DAYS_EARLY * SYSTEM.DAY))
					// 系统只提供7天以内的预订
					return $.SendHtml("107", SYSTEM.UTF8);
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

			double freeHours = $.getDouble("freeHours", 0d);
			/** ********支付人********* */
			// 驾驶认证没有审核通过
			if (csMember.getCsmVDrive() != null && csMember.getCsmVDrive() != (short) 1 && csMember.getCsmEvcard() != null) {
				if (csMember.getCsmVDrive() == (short) 2) {
					// 驾驶认证已经上传，请耐心等待
					return $.SendHtml("205", SYSTEM.UTF8);
				} else {
					// 驾驶认证没有上传
					return $.SendHtml("203", SYSTEM.UTF8);
				}
			}
			/** ********驾驶人********* */
			CsMember useMember = csMember;
			// checkOrderInfo(baseOrderInfo);
			CsOrder csOrder = new CsOrder();

			ICsOrderService csOrderService = $.getBean("csOrderService");

			// 芝麻信用
			csCreditCardService = $.GetSpringBean("csCreditCardService");
			CsCreditCard card = csCreditCardService.getCsCreditCard($.add("csccMember", csMember.getCsmId()));
			Long credit_card_id = null;
			String is_credit = $.getString("credit");
			if (is_credit.equals("checked") && card != null) {
				credit_card_id = card.getCsccId();
				// 信用额度
				ICommonMoneyService moneyService = $.GetSpringBean("commonMoneyService");
				Double allCanUseMoney = moneyService.getUsableMoneyAndCouponAndCredit(csMember.getCsmId());
				if (allCanUseMoney <= 0) {
					return $.SendHtml("222", SYSTEM.UTF8);
				}
			}

			// 平时订单处理
			if (!baseOrderInfo.getCsoIsSpring()) {
				if (credit_card_id != null) {
//					csOrder = commonDisposeService.executeSaveOrder(csMember.getCsmId(), useMember.getCsmId(), credit_card_id, baseOrderInfo.getCsoCar(), baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish(), csMember.getCsmType(), baseOrderInfo.getCsoInsure(), null, freeHours, null, 0l,
//							WeixinHelper.switchOrderPlatform(WeixinHelper.getOpenId()));
					csOrder = commonDisposeService.executeSaveOrder(csMember.getCsmId(), useMember.getCsmId(), credit_card_id, baseOrderInfo.getCsoCar(),
							car.getCscOutlets(), baseOrderInfo.getCsoOutletsRet(),
							baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish(), null, baseOrderInfo.getCsoInsure(), null, freeHours, null, 0l,
							WeixinHelper.switchOrderPlatform(WeixinHelper.getOpenId()),null);
				} else {
//					csOrder = commonDisposeService.executeSaveOrder(csMember.getCsmId(), useMember.getCsmId(), baseOrderInfo.getCsoCar(), baseOrderInfo.getCsoStart(), baseOrderInfo.getCsoFinish(), csMember.getCsmType(), baseOrderInfo.getCsoInsure(), null, freeHours, null, 0l, WeixinHelper
//							.switchOrderPlatform(WeixinHelper.getOpenId()));
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

			$.SetRequest("csOrder", csOrder);
			// 清除当前定单信息
			$.setSession(Constant.BASE_ORDER_INFO, null);
			// 更新默认取车点
			if (csMember.getCsmOutlets() == null) {
				CsMember newMember = new CsMember();
				newMember.setCsmOutlets(csOrder.getCsoOutlets());
				newMember.setCsmId(csMember.getCsmId());
				csMemberService.updateCsMember$NotNull(newMember);
			}
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

	public String getOrderId() {
		try {
			// 设置定单基本信息
			CsMember csMember = WeixinHelper.getCsMember();
			if (!WeixinHelper.isCorrectOpenId()) {
				// 微信openid不合规范
				return WeixinHelper.gotoLogin();
			}
			if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
				// return $.SendHtml("201", SYSTEM.UTF8);
				return WeixinHelper.gotoLogin();
			}

			ICsCreditBillService billService = $.GetSpringBean("csCreditBillService");
			CsCreditBill creditBill = billService.getCsCreditBill($.add("cscbMember", csMember.getCsmId()).add("cscbStatus", "0"));
			return $.SendHtml(creditBill.getCscbOrder$(), SYSTEM.UTF8);
		} catch (Exception ex) {
			ex.printStackTrace();
			return $.SendHtml("false", SYSTEM.UTF8);
		}
	}

	/**
	 * 车辆租赁情况
	 * 
	 * @return
	 */
	public String timeline() {
		// try {
		// Long id = $.getLong("id", -1l);
		// Date date = $.getDate("date", null);
		// Integer count = $.getInteger("count", 7);
		// List<CsOrder> orders = csOrderService.getCsOrderList($.add("csoCar",
		// id).add("definex", "cso_status!=3 and cso_status!=7"), -1);
		// $.SetRequest("image", TimeLineHelper.getTimeLineImage(date, count,
		// orders, commonOrderService.getRestrictions(id, 10)));
		// } catch (Exception e) {
		// e.printStackTrace();
		// Logger.getRootLogger().error(e.getMessage(),e);
		// return $.SendAjax("[]", "UTF-8");
		// }
		try {
			if (car != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MINUTE, 0);
				Date start = calendar.getTime();
				// 
				String dateCondition = SystemHelper.formatDate(start, "yyyy-MM-dd");
				if (!$.empty(date))
					dateCondition = date;
				List<CsOrder> orders = csOrderService.getCsOrderList($.add(CsOrder.F.csoCar, car).add("definex", CsOrder.C.csoStatus + " not in (3,7) and cso_start_time < '" + dateCondition + " 23:59' and if(ISNULL(cso_ret_time), cso_finish_time  > '" + dateCondition + "' , cso_ret_time >'"
						+ dateCondition + "')"), -1);
				// $.SetRequest("image", TimeLineHelper.getTimeLineImage(date,
				// count, orders, commonOrderService.getRestrictions(id, 10)));
				$.SetRequest("orders", orders);
				int restriction = -1;

				Date tempDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateCondition);
				Date tempDateEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateCondition + " 23:59");
				// dateList.add(tempDate);
				// if (commonOrderService.isExistRestrictionByTime(car,
				// tempDate, tempDateEnd))
				// restriction = 1;
				List<Restriction> restrictions = commonOrderService.getRestrictions(car, tempDate, tempDateEnd);
				if (restrictions.size() > 0) {
					restriction = 1;
				}
				$.SetRequest("dateCondition", dateCondition);
				$.SetRequest("restriction", restriction);
			}
			return "timeline";
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendHtml("{\"error\": \"car must be not null !\"}", "UTF-8");
		}
	}

	private Map<String, Object> getCarParams(BaseOrderInfo baseOrderInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		// 设置地域信息
		params.put(CsCar.F.cscHost, SystemHelper.getSrvHost().getShId());
		params.put(CsCar.F.cscStatus, 1);
		if (baseOrderInfo == null)
			return params;
		// 限定网点
		if (!baseOrderInfo.getCsoOutlets().equals(-1l)) {
			// 指定网点
			params.put(CsCar.F.cscOutlets, baseOrderInfo.getCsoOutlets());
			// 限定时间
			if (baseOrderInfo.getCsoIsDate()) {
				params.put("definex", " csc_id not in ( select cso_car from cs_order where cso_id not in (0,1) and  cso_start_time < '" + SystemHelper.formatDate(baseOrderInfo.getCsoFinish()) + "' and cso_finish_time >  '" + SystemHelper.formatDate(baseOrderInfo.getCsoStart())
						+ "' group by cso_car )");
			}
		} else {
			// 指定区域，不指定网点
			if (!baseOrderInfo.getCsoArea().equals(-1l)) {
				// 自定义
				String ids = "";
				List<CsOutlets> listOutlets = csOutletsService.getCsOutletsList($.add(CsOutlets.F.csoArea, baseOrderInfo.getCsoArea()), -1);
				if (listOutlets.size() > 0) {
					for (CsOutlets car : listOutlets) {
						ids = ids + car.getCsoId() + ",";
					}
					ids = ids.substring(0, ids.length() - 1);
					if (baseOrderInfo.getCsoIsDate()) {
						params.put("definex", CsCar.C.cscOutlets + " in ( " + ids + " )" + " and csc_id not in ( select cso_car from cs_order where cso_id not in (0,1) and  cso_start_time < '" + SystemHelper.formatDate(baseOrderInfo.getCsoFinish()) + "' and cso_finish_time >  '"
								+ SystemHelper.formatDate(baseOrderInfo.getCsoStart()) + "' group by cso_car )");
					} else {
						params.put("definex", CsCar.C.cscOutlets + " in ( " + ids + " )");
					}
				} else {
					if (baseOrderInfo.getCsoIsDate()) {
						params.put("definex", CsCar.C.cscOutlets + " in ( 0 )  " + " and  csc_id not in ( select cso_car from cs_order where cso_id not in (0,1) and  cso_start_time < '" + SystemHelper.formatDate(baseOrderInfo.getCsoFinish()) + "' and cso_finish_time >  '"
								+ SystemHelper.formatDate(baseOrderInfo.getCsoStart()) + "' group by cso_car )");
					} else {
						params.put("definex", CsCar.C.cscOutlets + " in ( 0 )  ");
					}
				}
			} else {
				// 指定网点
				// params.put(CsCar.F.cscOutlets,
				// baseOrderInfo.getCsoOutlets());
				// 限定时间
				if (baseOrderInfo.getCsoIsDate()) {
					params.put("definex", " csc_id not in ( select cso_car from cs_order where cso_id not in (0,1) and  cso_start_time < '" + SystemHelper.formatDate(baseOrderInfo.getCsoFinish()) + "' and cso_finish_time >  '" + SystemHelper.formatDate(baseOrderInfo.getCsoStart())
							+ "' group by cso_car )");
				}
			}
		}
		// 限定车型
		if (!baseOrderInfo.getCsoModel().equals(-1l)) {
			params.put(CsCar.F.cscModel, baseOrderInfo.getCsoModel());
		}
		// 限定广告车
		if (baseOrderInfo.getCsoIsAdCar()) {
			params.put(CsCar.F.cscIsAd, 1);
		}
		// 限定排序
		if (baseOrderInfo.getCsoLatitude().equals(0d) || baseOrderInfo.getCsoLongitude().equals(0d)) {
			SrvHost srvHost = SystemHelper.getSrvHost();
			String[] point = srvHost.getShLocation().split(",");
			baseOrderInfo.setCsoLatitude(Double.valueOf(point[0]));
			baseOrderInfo.setCsoLongitude(Double.valueOf(point[1]));
		}
		params.put("asc", "(pow(" + CsCar.C.cscLongitude + " -  " + baseOrderInfo.getCsoLongitude() + ",2) + pow(" + CsCar.C.cscLatitude + " - " + baseOrderInfo.getCsoLatitude() + ",2))");

		return params;
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

	public Long getCar() {
		return car;
	}

	public void setCar(Long car) {
		this.car = car;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
