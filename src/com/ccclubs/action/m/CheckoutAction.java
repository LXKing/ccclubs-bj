package com.ccclubs.action.m;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.ccclubs.action.m.util.MOrderUtil;
import com.ccclubs.action.m.util.OrderTime;
import com.ccclubs.action.m.util.ResponseEnvelope;
import com.ccclubs.action.web.BaseOrderInfo;
import com.ccclubs.config.Constant;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.config.SYSTEM.RuleName;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsCreditCard;
import com.ccclubs.model.CsFeeTypeSet;
import com.ccclubs.model.CsGoods;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsPrice;
import com.ccclubs.model.CsProduct;
import com.ccclubs.model.Restriction;
import com.ccclubs.service.admin.ICsCarModelService;
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
import com.ccclubs.service.common.impl.OrderInfo;
import com.ccclubs.util.DateUtil;
import com.lazy3q.web.helper.$;

public class CheckoutAction {
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
	
	public String execute() {
		try {
			CsMember csMember = WeixinHelper.getCsMember();
			if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
				// 微信openid不合规范
				return WeixinHelper.gotoLogin();
			}
			
			double freehour = 0;
			if (csMember != null) {
				freehour = commonMoneyService.getCanUseFreeHour(csMember.getCsmId(), new java.util.Date());
			}
			// 设置定单基本信息
			Long csoCar = $.getLong("carid", -1l);
			// 获取保险的产品
			// 保险类型，默认按小时购买
			CsProduct csProductHour = csProductService.getCsProduct($.add(CsProduct.F.cspFlag, Constant.SECURE));
			CsProduct csProductMile = csProductService.getCsProduct($.add(CsProduct.F.cspFlag, Constant.INSURE));

			Long insureType = $.getLong("insureType", -1l);
			if (insureType.equals(-1l)) {
				// 保险类型，默认按小时购买
				insureType = csProductHour.getCspId();
			}
			
			CsCar csCar = csCarService.getCsCarById(csoCar);
			if (csCar == null || csCar.getCscStatus() == (short) 0){
				// 需要选择一辆车
				ServletActionContext.getResponse().sendRedirect($.GetRequest("basePath") + "m/index.html");
				return null;
			}
			
			BaseOrderInfo baseOrderInfo = new BaseOrderInfo();
			if (SystemHelper.isBaseOrderInfo()) {
				baseOrderInfo = SystemHelper.setDefaultOrderTime(baseOrderInfo);
			}
			
			// 车辆价格
			List<CsPrice> listPrice = new ArrayList<CsPrice>();
			Long outlets = csCar.getCscOutlets();
			Long feeType = CsFeeTypeSet.Get(
				$.add(CsFeeTypeSet.F.csftsHost, csCar.getCscHost())
				.add(CsFeeTypeSet.F.csftsModel, csCar.getCscModel())
			).getCsftsDefault();
			// 价格列表
			listPrice = csPriceService.getCsPriceList($.add(CsPrice.F.cspUserType, feeType).add(CsPrice.F.cspOutets, outlets).add(CsPrice.F.cspModel, csCar.getCscModel()).add("desc", CsPrice.C.cspGoods), -1);
			// 如果没有，则以默认网点价格作为参考
			CsFeeTypeSet feeTypeSet = CsFeeTypeSet.Get($.add(CsFeeTypeSet.F.csftsModel, csCar.getCscModel()).add(CsFeeTypeSet.F.csftsHost, CsOutlets.get(csCar.getCscOutlets()).getCsoHost()));
			if (listPrice.isEmpty() && feeTypeSet != null && feeTypeSet.getCsftsOutlets() != null) {
				listPrice = csPriceService.getCsPriceList($.add(CsPrice.F.cspUserType, feeTypeSet.getCsftsDefault()).add(CsPrice.F.cspOutets, feeTypeSet.getCsftsOutlets()).add(CsPrice.F.cspModel, csCar.getCscModel()).add("desc", CsPrice.C.cspGoods), -1);
				feeType = feeTypeSet.getCsftsDefault();
			}
			
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
			
			//异地还车
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(CsOutlets.F.csoHost, SystemHelper.getSrvHost().getShId());
			params.put(CsOutlets.F.csoStatus, "1");
			params.put(CsOutlets.F.csoCanOrder, "1");
			params.put(CsOutlets.F.csoCanB, "1");
			// 可用车位数大于0
			params.put("definex", CsOutlets.C.csoId + " <> " + csCar.getCscOutlets() + " AND (" + CsOutlets.C.csoPositionS + " - " + CsOutlets.C.csoCarS + " > 0)");
			// 还车网点列表
			List<CsOutlets> huanche_outlets = csOutletsService.getCsOutletsList(params, -1);
			ICsOutletsService outletsService = $.GetSpringBean("csOutletsService");
			CsOutlets csOutlets = outletsService.getCsOutletsById(outlets);
			if(csOutlets.getCsoCanA()){
				$.SetRequest("aret", "A");
			}
			
			DateUtil du = new DateUtil();
			MOrderUtil morder = new MOrderUtil();
			ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MINUTE, 0);
			String dateCondition = du.dateToString(calendar.getTime(), "yyyy-MM-dd");
			
			List<String> listday = new ArrayList<String>();
			try{
				for(int i=0;i<7;i++){
					String is_full = "usable";
					int restriction = -1;
					if(i!=0){
						calendar.add(Calendar.DAY_OF_MONTH, 1);
					}
					int month = calendar.get(Calendar.MONTH) + 1;
					int day = calendar.get(Calendar.DAY_OF_MONTH);
					Date start = calendar.getTime();
					String dayofweek = morder.getWeekOfDate(start);
					String dayview = month + "月" + day + "日  " + dayofweek;
					dateCondition = SystemHelper.formatDate(start, "yyyy-MM-dd");
					List<CsOrder> orders = csOrderService.getCsOrderList($.add(CsOrder.F.csoCar, csoCar).add("definex", CsOrder.C.csoStatus + " not in (3,7) and cso_start_time < '" +
							dateCondition + " 23:59' and if(ISNULL(cso_ret_time), cso_finish_time  > '" + dateCondition + "' , cso_ret_time >'" + dateCondition + "')"), -1);
					if(orders.size()!=0){
						boolean s = morder.isInDates(du.StringtoDate(dateCondition+" 00:00:00", null), orders.get(0).getCsoStartTime(), orders.get(0).getCsoFinishTime());
						boolean s_end = morder.isInDates(du.StringtoDate(dateCondition+" 23:59:59", null), orders.get(0).getCsoStartTime(), orders.get(0).getCsoFinishTime());
						//全天订单已满
						if(s&&s_end){
							is_full = "full";
						}
					}
					listday.add(dayview);
					List<OrderTime> listtime = morder.usableTime(orders, dateCondition);
					
					//限行时间
					Date tempDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateCondition);
					Date tempDateEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateCondition + " 23:59");
					ICommonOrderService commonOrderService = $.GetSpringBean("commonOrderService");
					List<Restriction> restrictions = commonOrderService.getRestrictions(csoCar, tempDate, tempDateEnd);
					if (restrictions.size() > 0) {
						restriction = 1;
					}
					
					$.SetRequest("restriction_"+i, restriction);
					$.SetRequest("listtime_"+i, listtime);
					$.SetRequest("full_"+i, is_full);
				}
			}catch(ParseException e){
				e.printStackTrace();
			}
			$.SetRequest("listday", listday);
			$.SetRequest("carid", csoCar);
			
			$.SetRequest("freehour", freehour);
			$.SetRequest("csProductHour", csProductHour);
			$.SetRequest("csProductMile", csProductMile);
			$.SetRequest("insureType", insureType);
			$.SetRequest("listPrice", listPrice);
			$.SetRequest("baseOrderInfo", baseOrderInfo);
			$.SetRequest("csoCar", csCar);
			$.SetRequest("limit", limit);
			$.SetRequest("currentOutlet", csOutlets.getCsoName$());
			$.SetRequest("huanche_outlets", huanche_outlets);
			$.SetRequest("startTime", $.getString("startTime"));
			$.SetRequest("finishTime", $.getString("finishTime"));
		}catch(Exception e){
			e.printStackTrace();
			ResponseEnvelope<String> re = new ResponseEnvelope<String>();
			re.setState(false);
			re.setMessage("系统错误，请稍后再试");
			return $.SendAjax(re, "utf-8");
		}
		return "checkout";
	}
	
	/**
	 * 获得订单的大概费用
	 * @return
	 */
	public String confirm(){
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
			ICsCarModelService modelService = $.GetSpringBean("csCarModelService");
			String carType = modelService.getCsCarModelById(car.getCscModel()).getCscmType$();
			if (carType.equals("电动车")) {
				long tempTime = csoStart.getTime() - System.currentTimeMillis();
				double hours = (double) tempTime / 1000 / 3600;
				if (hours > 2) {
					re.setState(false);
					re.setMessage("亲，电动汽车最多支持提前2小时预订");
					return $.SendAjax(re, "utf-8");
				}
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

			String yidiwd_value = $.getString("yidiwd_value");
			String yidiwd_text = "no";
			if(!yidiwd_value.equals("-1")){
				yidiwd_text = java.net.URLDecoder.decode($.getString("yidiwd_text"), "utf-8");
			}
			
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
			// 获取小时数
			int allhours = (int) ((csoStart.getTime() - csoFinish.getTime()) / SYSTEM.HOUR);
			// 拆分订单
			OrderInfo orderinfo = commonOrderService.splitOrderDetails(csMember.getCsmId(), car.getCscId(), csoStart, csoFinish, null, 
					freeHours, null, commonOrderService.getPredictKms(allhours) * 1.0, null, csoAvoid, null, null);
			Double allPayment = orderinfo.getPrice() + orderinfo.getMargin() + orderinfo.getFeeee();
			
			// 是否使用芝麻信用
			if($.getBoolean("credit")){
				//是否在风险名单
				ICsCreditCardService cardservice = $.GetSpringBean("csCreditCardService");
				CsCreditCard card = cardservice.getCsCreditCard($.add(CsCreditCard.F.csccMember, csMember.getCsmId()));
				boolean isRisk = WeixinHelper.getAlipayRisk(card);
				if(isRisk){
					re.setState(false);
					re.setMessage("您的个人信息在芝麻信用风险名单中，暂不能下单");
					return $.SendAjax(re, "utf-8");
				}
				// 判断信用额度是否够下单
				ICommonMoneyService commonMoneyService = $.GetSpringBean("commonMoneyService");
				if ((orderinfo.getPrice() + orderinfo.getMargin() + orderinfo.getFeeee()) > (commonMoneyService.getUsableCredit(csMember.getCsmId()))) {
					re.setState(false);
					re.setMessage("亲，您的信用余额已不够下单");
					return $.SendAjax(re, "utf-8");
				}
				if ((csoFinish.getTime() - csoStart.getTime()) > (1 * SYSTEM.DAY)){
					re.setState(false);
					re.setMessage("免押金订单最多只能下一天哦");
					return $.SendAjax(re, "utf-8");
				}
			}
			
			//订单确认页面所需参数
			$.setSession("carmodel", car.get$cscModel().getCscmName());
			$.setSession("carnumber", car.getCscCarNo());
			$.setSession("getcaroutlet", car.get$cscOutlets().getCsoName());
			
			if(yidiwd_text.equals("no")){
				$.setSession("returncaroutletid", "null");
				$.setSession("returncaroutlet", "null");
			}else{
				$.setSession("returncaroutletid", yidiwd_value);
				$.setSession("returncaroutlet", yidiwd_text);
			}
			$.setSession("starttime", $.getString("csoStart"));
			$.setSession("finishtime", $.getString("csoFinish"));
			$.setSession("rentpay", orderinfo.getPrice());//总费用
			$.setSession("rentmargin", orderinfo.getMargin());
			$.setSession("rentmile", orderinfo.getFeeee());
			$.setSession("rentPayment", orderinfo.getRent());//租金
			$.setSession("insurancePayment", orderinfo.getInsure());//千元免赔
			$.setSession("credit", $.getBoolean("credit"));
			if (allPayment <= 2000 && $.getBoolean("credit")) {
				$.setSession("usecreditpay", $.digit(allPayment, 2));
				$.setSession("freeze", "0");
			} else if (allPayment > 2000 && $.getBoolean("credit")) {
				$.setSession("usecreditpay", "2000");
				$.setSession("freeze", $.digit(allPayment - 2000, 2));
			} else {
				$.setSession("usecreditpay", "0");
				$.setSession("freeze", $.digit(allPayment, 2));
			}
			$.setSession("carid", csoCar);
			$.setSession("csoAvoid", csoAvoid);
			$.setSession("freeHours", freeHours);
			$.setSession("yidiwd_value", yidiwd_value);
		}catch(Exception e){
			e.printStackTrace();
			re.setState(false);
			re.setMessage("系统错误，请稍后再试");
			return $.SendAjax(re, "utf-8");
		}
		re.setState(true);
		return $.SendAjax(re, "utf-8");
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
