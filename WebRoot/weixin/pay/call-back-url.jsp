<%@page import="com.ccclubs.service.common.ICommonMoneyService"%>
<%
/* *
 功能：支付宝页面跳转同步通知页面
 版本：3.2
 日期：2011-03-17
 说明：
 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 //***********页面功能说明***********
 该页面可在本机电脑测试
 可放入HTML等美化页面的代码、商户业务逻辑程序代码
 TRADE_FINISHED(表示交易已经成功结束，并不能再对该交易做后续操作);
 TRADE_SUCCESS(表示交易已经成功结束，可以对该交易做后续操作，如：分润、退款等);
 //********************************
 * */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.ccclubs.action.weixin.alipay.util.*"%>
<%@ page import="com.ccclubs.action.weixin.alipay.config.*"%>
<%@ page import="com.lazy3q.util.Function"%>
<%@ page import="com.lazy3q.web.helper.$"%>
<%@ page import="com.ccclubs.service.admin.*"%>
<%@ page import="com.ccclubs.model.*"%>
<%@ page import="com.ccclubs.service.common.*"%>
<%@ page import="com.ccclubs.config.SYSTEM"%>
<html>
  <head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<title>支付宝页面跳转同步通知页面</title>
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="pay.css">
  </head>
  <body>
<%
	//获取支付宝GET过来反馈信息
	Map<String,String> params = new HashMap<String,String>();
	Map requestParams = request.getParameterMap();
	for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
		String name = (String) iter.next();
		String[] values = (String[]) requestParams.get(name);
		String valueStr = "";
		for (int i = 0; i < values.length; i++) {
			valueStr = (i == values.length - 1) ? valueStr + values[i]
					: valueStr + values[i] + ",";
		}
		//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
		valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
		params.put(name, valueStr);
	}

	//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
	//商户订单号
	String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

	//支付宝交易号	String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

	//交易状态
	String result = new String(request.getParameter("result").getBytes("ISO-8859-1"),"UTF-8");

	//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
	
	//计算得出通知验证结果
	boolean verify_result = AlipayNotify.verifyReturn(params);
	
	if(verify_result){//验证成功
		////////////////////////////////2015-04-25刘代进改///////////////////////////////
		CsOrder csOrder = CsOrder.get(Long.parseLong(out_trade_no));
		String strDescript = $.date(new Date(), "yyyy-MM-dd HH:mm:ss")+"结算订单,订单费用"+csOrder.getCsoPayReal()+"元";
		ICommonMoneyService moneyService = $.GetSpringBean("commonMoneyService");
		moneyService.setCreditBillPayed(csOrder.getCsoCreditBill(),trade_no,strDescript,false);
		///////////////////////////////////////////////////////////////////////////////
		//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
		/*ICsCreditBillService creditBillService = $.GetSpringBean("csCreditBillService");
		CsCreditBill creditBill = creditBillService.getCsCreditBill($.add("cscbOrder", out_trade_no));
		String paystatus = creditBill.getCscbStatus$();*/
		
		//已还款就不接收支付宝多余的异步返回
		/*if(!creditBill.getCscbStatus$().equals("1")){
			creditBill.setCscbPayment(creditBill.getCscbValue());
			creditBill.setCscbStatus(new Short("1"));
			creditBill.setCscbUpdateTime(new Date());
			creditBillService.updateCsCreditBill(creditBill);
			
			ICsOrderService orderService = $.GetSpringBean("csOrderService");
			CsOrder csOrder = orderService.getCsOrderById(Long.parseLong(out_trade_no));
			if(creditBill.getCscbTitle().equals("订单取消")){
				csOrder.setCsoStatus(new Short("3"));
			}else if(creditBill.getCscbTitle().equals("订单消费")){
				csOrder.setCsoStatus(new Short("4"));
			}
			orderService.updateCsOrder(csOrder);
			Long memberId = csOrder.getCsoPayMember();
			ICsMemberService memberService = $.GetSpringBean("csMemberService");
			CsMember csMember = memberService.getCsMemberById(memberId);
			String from = csMember.getCsmAlipayFlag();
			*/
			//更新保证金
			/*String strDescript = $.date(new Date(), "yyyy-MM-dd HH:mm:ss")+"结算订单,订单费用"+csOrder.getCsoPayReal()+"元";
			ICommonMoneyService moneyService = $.GetSpringBean("commonMoneyService");
			moneyService.updateMoney(csOrder.getCsoHost(),csMember.getCsmId(),ICommonMoneyService.MoneyType.Money,csOrder.getCsoPayReal(),SYSTEM.RecordType.芝麻信用还款,strDescript,csOrder.getCsoId(),null,null);
			moneyService.updateMoney(csOrder.getCsoHost(),csMember.getCsmId(),ICommonMoneyService.MoneyType.Money,-csOrder.getCsoPayReal(),SYSTEM.RecordType.订单消费,strDescript,csOrder.getCsoId(),null,null);*/
			
			//请在这里加上商户的业务逻辑程序代码
			String html = "<div class=\"bigmain\"><div class=\"buytitle\"><div class=\"content\"><div class=\"goback\"><a href=\"../../m/index.html"+
				"\">返回</a></div><div class=\"showtitle\">付款</div></div></div></div><div class=\"netbox\"><ul><li class=\"first\">"+
				"<img src=\"../images/pay-suc.png\">&nbsp;&nbsp;付款成功，感谢您的支持，您可以继续使用租车服务了。</li><li><img src=\"../images/tel.png\">&nbsp;&nbsp;如有疑问请致电客服：0571-88190338</li>"+
				"</ul></div><br><div class=\"okbuybtn\"><a href=\"../../m/index.html"+
				"\">返回首页</a></div><div class=\"okbuybtn\"><a href=\"../../m/index.html\">立即租车</a></div>";
			
			//该页面可做页面美工编辑
			out.println(html);
		}
	}else{
		//该页面可做页面美工编辑
		ICsOrderService orderService = $.GetSpringBean("csOrderService");
		Long memberId = orderService.getCsOrderById(Long.parseLong(out_trade_no)).getCsoPayMember();
		ICsMemberService memberService = $.GetSpringBean("csMemberService");
		String from = csMember.getCsmAlipayFlag();
		
		String html_error = "<div class=\"bigmain\"><div class=\"buytitle\"><div class=\"content\"><div class=\"goback\"><a href=\"../../m/index.html\">返回</a></div><div class=\"showtitle\">付款</div></div></div></div>"+
		"<div class=\"netbox\"><ul><li class=\"first\">&nbsp;&nbsp;付款失败，请确认你的支付环境正常。</li><li><img src=\"../images/tel.png\">&nbsp;&nbsp;如有疑问请致电客服：0571-88190338</li></ul></div>"+
		"<br><div class=\"okbuybtn\"><a href=\"../../m/index.html\">返回首页</a></div><div class=\"okbuybtn\"><a href=\"../../m/index.html\">立即租车</a></div>";
		out.println(html_error);
	}
%>
  </body>
</html>