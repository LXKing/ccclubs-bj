package com.ccclubs.action.m.home;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ccclubs.action.m.WeixinHelper;
import com.ccclubs.action.m.util.MOrderUtil;
import com.ccclubs.action.m.util.OrderTime;
import com.ccclubs.action.m.util.ResponseEnvelope;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsCreditBill;
import com.ccclubs.model.CsCreditCard;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.Restriction;
import com.ccclubs.service.admin.ICsCreditBillService;
import com.ccclubs.service.admin.ICsCreditCardService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.common.ICommonDisposeService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonOrderService;
import com.ccclubs.service.common.impl.OrderInfo;
import com.ccclubs.util.DateUtil;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;

public class OrderAction {
	private ICsMemberService		csMemberService;
	private ICsOrderService			csOrderService;
	private ICommonDisposeService	commonDisposeService;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (!WeixinHelper.isCorrectOpenId()) {
			// 微信openid不合规范
			return WeixinHelper.gotoLogin();
		}
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		
		Page<CsOrder> page = csOrderService.getCsOrderPage($.getInteger("page", 0), 5, $.add("csoPayMember", csMember.getCsmId()).add("desc", "cso_add_time"));
		List<CsOrder> list = page.getResult();
		// 付款状态
		for (CsOrder csOrder : list) {
			ICsCreditBillService creditBillService = $.GetSpringBean("csCreditBillService");
			CsCreditBill bill = creditBillService.getCsCreditBill($.add("cscbOrder", csOrder.getCsoId()));
			if (bill != null) {
				csOrder.setValues("csoCreditCardStatus", bill.getCscbStatus().toString());
			} else {
				// 若账单为空，则表示该订单通过余额直接扣款结算
				csOrder.setValues("csoCreditCardStatus", "1");
			}
		}
		page.setResult(list);

		$.SetRequest("page", page);
		return "order";
	}
	
	public String usecar(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (!WeixinHelper.isCorrectOpenId()) {
			return WeixinHelper.gotoLogin();
		}
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		
		List<CsOrder> list = csOrderService.getCsOrderList($.add("csoPayMember", csMember.getCsmId()).add("definex", "(cso_status=0 or cso_status=1)").add("desc", "cso_add_time"), -1);
		if(list==null){
			return $.Redirect($.GetRequest("basePath")+"m/index.html");
		}else if(list.isEmpty()){
			return $.Redirect($.GetRequest("basePath")+"m/index.html");
		}else if(list.size()==1){
			return $.Redirect($.GetRequest("basePath")+"m/home/order_detail.html?orderid="+list.get(0).getCsoId());
		}
		$.SetRequest("list", list);
		return "usecar";
	}
	
	public String detail(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (!WeixinHelper.isCorrectOpenId()) {
			// 微信openid不合规范
			return WeixinHelper.gotoLogin();
		}
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		
		long order = $.getLong("orderid", 0l);
		CsOrder csOrder = csOrderService.getCsOrderById(order);
		// 不存在定单
		if (csOrder == null){
			return $.SendHtml("false", "UTF-8");
		}
		// 支付会员对不上
		if (!csOrder.getCsoPayMember().equals(csMember.getCsmId())){
			return $.SendHtml("false", "UTF-8");
		}
		
		ICsCreditBillService creditBillService = $.GetSpringBean("csCreditBillService");
		CsCreditBill bill = creditBillService.getCsCreditBill($.add("cscbOrder", csOrder.getCsoId()));
		if (bill != null) {
			String payStatus = bill.getCscbStatus().toString();
			$.SetRequest("payStatus", payStatus);
			$.SetRequest("mustPay", bill.getCscbValue());
		} else {
			$.SetRequest("payStatus", "1");
		}
		$.SetRequest("csOrder", csOrder);
		return "detail";
	}
	
	public String cancelconfirm(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		try{
			Long orderid = $.getLong("orderid");
			CsOrder csOrder = csOrderService.getCsOrderById(orderid);

			Date now = new Date();
			// 订单时长
			Double longer = new Double(csOrder.getCsoFinishTime().getTime() - csOrder.getCsoStartTime().getTime()) / new Double(1000 * 60 * 60);
			// 取消时间
			Double hour = new Double(csOrder.getCsoStartTime().getTime() - now.getTime()) / new Double(1000 * 60 * 60);
			Double scale = 1.0d;
			if (longer > 24) {
				if (hour > 8) {
					scale = 0.1d;
				} else {
					scale = 1.0d;
				}
			} else {
				if (hour > 1) {
					scale = 0.1d;
				} else {
					scale = 1.0d;
				}
			}
			ICommonOrderService commonOrderService = $.getBean("commonOrderService");
			// 拆分订单
			OrderInfo orderinfo = commonOrderService.splitOrderDetails(csOrder.getCsoPayMember(), csOrder.getCsoCar(), csOrder.getCsoStartTime(), csOrder.getCsoFinishTime(), csOrder.getCsoRetTime(), csOrder.getCsoFreehour(), csOrder.getCsoElectric(), csOrder.getCsoFuel(), csOrder.getCsoFeeType(),
					csOrder.getCsoInsureType(), csOrder.getCsoLongPrice(), csOrder.getCsoId());
			// 设置订单需付额
			csOrder.setCsoPayNeed(orderinfo.getPrice());
			csOrder.setCsoPayReal(orderinfo.getPrice() * scale);
			double payReal = orderinfo.getPrice() * scale;
			re.setState(true);
			re.setMessage("取消本订单将扣除" + $.digit(payReal, 2) + "元，确定取消订单？");
			return $.SendAjax(re, "utf-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			re.setState(false);
			re.setMessage("系统错误，请稍后再试");
			return $.SendAjax(re, "utf-8");
		}
	}
	
	public String cancel(){
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
			
			Long orderid = $.getLong("orderid");
			CsOrder order = csOrderService.getCsOrderById(orderid);
			// 验证当前订单是不是当前用户的
			if (order == null || !order.getCsoPayMember().equals(csMember.getCsmId())) {
				re.setState(false);
				re.setMessage("亲，只有本人才能取消定单哦～");
				return $.SendAjax(re, "utf-8");
			}
			if (order.getCsoStatus() != 0){
				re.setState(false);
				re.setMessage("亲，只有已预订状态下的订单才能取消哦～");
				return $.SendAjax(re, "utf-8");
			}
			commonDisposeService.executeCancelOrder(orderid, "会员自主取消订单",WeixinHelper.switchOrderPlatform(WeixinHelper.getOpenId()),null);
			
			re.setState(true);
			re.setMessage("取消订单成功");
			return $.SendAjax(re, "utf-8");
		} catch (com.ccclubs.exception.MessageException oe) {
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
	
	public String reorder(){
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
			
			long order = $.getLong("orderid", 0l);
			CsOrder csOrder = csOrderService.getCsOrderById(order);
			// 不存在定单
			if (csOrder == null){
				return $.SendHtml("false", "UTF-8");
			}
			// 支付会员对不上
			if (!csOrder.getCsoPayMember().equals(csMember.getCsmId())){
				return $.SendHtml("false", "UTF-8");
			}
			$.SetRequest("csOrder", csOrder);
			
			// 默认续订1小时，输出新的续订时间
			Calendar cal = Calendar.getInstance();
			cal.setTime(csOrder.getCsoFinishTime());
			cal.add(Calendar.HOUR_OF_DAY, 1);
			String newfinish = new DateUtil().dateToString(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
			$.SetRequest("newFinish", newfinish);
			
			DateUtil du = new DateUtil();
			MOrderUtil morder = new MOrderUtil();
			ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MINUTE, 0);
			String dateCondition = du.dateToString(calendar.getTime(), "yyyy-MM-dd");
			String carid = csOrder.getCsoCar()+"";
			List<String> listday = new ArrayList<String>();
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
				List<CsOrder> orders = csOrderService.getCsOrderList($.add(CsOrder.F.csoCar, carid).add("definex", CsOrder.C.csoStatus + " not in (3,7) and cso_start_time < '" +
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
				List<Restriction> restrictions = commonOrderService.getRestrictions(Long.parseLong(carid), tempDate, tempDateEnd);
				if (restrictions.size() > 0) {
					restriction = 1;
				}
				
				$.SetRequest("restriction_"+i, restriction);
				$.SetRequest("listtime_"+i, listtime);
				$.SetRequest("full_"+i, is_full);
			}
			$.SetRequest("listday", listday);
		}catch(Exception e){
			e.printStackTrace();
			re.setState(false);
			re.setMessage("系统错误，请稍后再试");
			return $.SendAjax(re, "utf-8");
		}
		return "reorder";
	}
	
	public String reordersubmit(){
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
			
			Long order = $.getLong("orderid", 0l);
			Date newFinishDate = $.getDate("newFinishDate");
			CsOrder csOrder = csOrderService.getCsOrderById(order);
			// 不存在定单
			if (csOrder == null){
				re.setState(false);
				re.setMessage("订单不存在，请稍后再试");
				return $.SendAjax(re, "utf-8");
			}
			// 支付会员对不上
			if (!csOrder.getCsoPayMember().equals(csMember.getCsmId())){
				re.setState(false);
				re.setMessage("亲，只有本人才能续订订单哦～");
				return $.SendAjax(re, "utf-8");
			}
			if (newFinishDate == null){
				re.setState(false);
				re.setMessage("还车时间有误，请重新选择");
				return $.SendAjax(re, "utf-8");
			}
			
			Double freehour = csOrder.getCsoFreehour();
			CsOrder reOrder = commonDisposeService.executeReOrder(order, newFinishDate, freehour,WeixinHelper.switchOrderPlatform(WeixinHelper.getOpenId()),null);
			if (csOrder.getCsoCreditCard$() != null) { // 判断信用额度是否够下单
				ICommonMoneyService commonMoneyService = $.GetSpringBean("commonMoneyService");
				if ((reOrder.getCsoPayNeed() - csOrder.getCsoPayNeed()) > commonMoneyService.getUsableMoneyAndCouponAndCredit(csMember.getCsmId())) {
					re.setState(false);
					re.setMessage("您的信用余额不足");
					return $.SendAjax(re, "utf-8");
				}
				Long creditId = csOrder.getCsoCreditCard();
				ICsCreditCardService creditcardService = $.GetSpringBean("csCreditCardService");
				CsCreditCard creditcard = creditcardService.getCsCreditCardById(creditId);
				if (creditcard.getCsccStatus$().equals("2")) {
					re.setState(false);
					re.setMessage("您的信用余额不足");
					return $.SendAjax(re, "utf-8");
				}
			}
			
			re.setState(true);
			re.setMessage("订单续订成功");
			return $.SendAjax(re, "utf-8");
		} catch (com.ccclubs.exception.MessageException oe) {
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
	
	public String operate(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}

		// 订单前10分钟才可以取车，return "orderdetail"
		CsOrder csOrder = csOrderService.getCsOrderById(Long.parseLong($.getString("orderid","0")));
		// 页面没传id过来
		if (csOrder == null){
			List<CsOrder> listorder = csOrderService.getCsOrderList($.add("definex", "(cso_status=0 or cso_status=1)").add(CsOrder.F.csoPayMember, csMember.getCsmId()), -1);
			if(listorder.isEmpty()){
				return $.Redirect($.GetRequest("basePath")+"m/index.html");
			}else if(listorder.size()==1){
				csOrder = listorder.get(0);
			}else if(listorder.size()>1){
				return "order";
			}
		}
//		return $.SendHtml("false", "UTF-8");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(csOrder.getCsoStartTime());
		calendar.add(Calendar.MINUTE, -10);
		long temp = calendar.getTimeInMillis() - new Date().getTime();
		ICsCreditBillService creditBillService = $.GetSpringBean("csCreditBillService");
		CsCreditBill bill = creditBillService.getCsCreditBill($.add("cscbOrder", csOrder.getCsoId()));
		if (bill != null) {
			String payStatus = bill.getCscbStatus().toString();
			$.SetRequest("payStatus", payStatus);
			$.SetRequest("mustPay", bill.getCscbValue());
		} else {
			$.SetRequest("payStatus", "1");
		}
		$.SetRequest("csOrder", csOrder);

		// 电动车和凯越才可远程开关门-杭州，宁波
		CsCar car = csOrder.get$csoCar();
		$.SetRequest("carNum", car.getCscCarNo());// car.getCscNumber$().substring(3)
		$.SetRequest("carType", car.get$cscModel().getCscmName());// car.getCscNumber$().substring(3)
		if (car.getCscHost() == 1) {// 杭州
			if(car.getCscModel()==1008l || car.getCscModel()==1006l || car.getCscModel()==1022l){
				if (temp > 0) {// 判断订单开始时间
					// 支付会员对不上
					if (!csOrder.getCsoUseMember().equals(csMember.getCsmId())) {
						return "order";
					}
					$.SetRequest("msg", "您的订单尚未开始，在取车时间前10分钟开始就能操控小纷咯，享受开关门的乐趣。");
					return "operate";
				} else if (!(csOrder.getCsoStatus().equals(new Short("0")) || csOrder.getCsoStatus().equals(new Short("1")))) {// System.currentTimeMillis()>csOrder.getCsoFinishTime().getTime()
					// 订单状态不为0和1
					return $.Redirect($.GetRequest("basePath")+"m/home/order_detail.html?orderid="+$.getString("orderid","0"));
				} else {
					return "operate";
				}
			}else{
				return $.Redirect($.GetRequest("basePath")+"m/home/order_detail.html?orderid="+$.getString("orderid","0"));
			}
		} else if(car.getCscHost() == 11l){//宁波
			if (temp > 0) {// 判断订单开始时间
				// 支付会员对不上
				if (!csOrder.getCsoUseMember().equals(csMember.getCsmId())) {
					return "order";
				}
				$.SetRequest("msg", "您的订单尚未开始，在取车时间前10分钟开始就能操控小纷咯，享受开关门的乐趣。");
				return "operate";
			} else if (!(csOrder.getCsoStatus().equals(new Short("0")) || csOrder.getCsoStatus().equals(new Short("1")))) {// System.currentTimeMillis()>csOrder.getCsoFinishTime().getTime()
				// 订单状态不为0和1
				return $.Redirect($.GetRequest("basePath")+"m/home/order_detail.html?orderid="+$.getString("orderid","0"));
			} else {
				return "operate";
			}
		}else {// 汽油车
			return $.Redirect($.GetRequest("basePath")+"m/home/order_detail.html?orderid="+$.getString("orderid","0"));
		}
	}
	
	public String remoteCon(){
		try {
			CsMember csMember = WeixinHelper.getCsMember();
			if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
				// openid不存在，跳转到403页面
				return $.SendHtml("101", "UTF-8");
			}

			CsOrder csOrder = csOrderService.getCsOrderById(Long.parseLong($.getString("orderId")));
			// 不存在定单
			if (csOrder == null) {
				return $.SendHtml("101", "UTF-8");
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(csOrder.getCsoStartTime());
			calendar.add(Calendar.MINUTE, -10);
			long temp = calendar.getTimeInMillis() - new Date().getTime();
			if (temp > 0) {// 判断订单开始时间
				return $.SendHtml("444", "UTF-8");
			}

			String controllType = $.getString("type");
			if (controllType.equals("1")) {
				if (csOrder.getCsoStatus().equals(new Short("0"))) {// 已预订状态-远程取车
					WeixinHelper.remoteController(csOrder.getCsoCar(), "6", csMember.getCsmId());
					return $.SendHtml("111", "UTF-8");
				} else if (csOrder.getCsoStatus().equals(new Short("1"))) {// 使用中状态-远程开门
					WeixinHelper.remoteController(csOrder.getCsoCar(), "0", csMember.getCsmId());
					return $.SendHtml("111", "UTF-8");
				} else {
					return $.SendHtml("9999", "UTF-8");
				}
			} else if (controllType.equals("2")) {// 关门
				if (csOrder.getCsoStatus().equals(new Short("0")) || csOrder.getCsoStatus().equals(new Short("1"))) {
					WeixinHelper.remoteController(csOrder.getCsoCar(), "1", csMember.getCsmId());
					return $.SendHtml("111", "UTF-8");
				} else {
					return $.SendHtml("9999", "UTF-8");
				}
			} else if (controllType.equals("3")) {// 鸣笛
				if (csOrder.getCsoStatus().equals(new Short("0")) || csOrder.getCsoStatus().equals(new Short("1"))) {
					WeixinHelper.remoteController(csOrder.getCsoCar(), "17", csMember.getCsmId());
					return $.SendHtml("111", "UTF-8");
				} else {
					return $.SendHtml("9999", "UTF-8");
				}
			} else if (controllType.equals("4")) {// 远程还车
				if (csOrder.getCsoStatus().equals(new Short("0")) || csOrder.getCsoStatus().equals(new Short("1"))) {
					WeixinHelper.remoteController(csOrder.getCsoCar(), "7", csMember.getCsmId());
					return $.SendHtml("111", "UTF-8");
				} else {
					return $.SendHtml("9999", "UTF-8");
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return $.SendHtml("101", "UTF-8");
		}
	}
	
	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}
	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}
	public ICsOrderService getCsOrderService() {
		return csOrderService;
	}
	public void setCsOrderService(ICsOrderService csOrderService) {
		this.csOrderService = csOrderService;
	}
	public ICommonDisposeService getCommonDisposeService() {
		return commonDisposeService;
	}
	public void setCommonDisposeService(ICommonDisposeService commonDisposeService) {
		this.commonDisposeService = commonDisposeService;
	}
}
