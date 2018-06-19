package com.ccclubs.action.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import com.alipay.util.httpClient.HttpProtocolHandler;
import com.alipay.util.httpClient.HttpRequest;
import com.alipay.util.httpClient.HttpResponse;
import com.alipay.util.httpClient.HttpResultType;
import com.ccclubs.action.m.WeixinHelper;
import com.ccclubs.action.m.home.AccountAction;
import com.ccclubs.action.weixin.alipay.config.AlipayConfig;
import com.ccclubs.action.weixin.alipay.util.AlipayNotify;
import com.ccclubs.config.CommonMessage;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.config.SYSTEM.RecordType;
import com.ccclubs.helper.DateHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsAlipayRecord;
import com.ccclubs.service.admin.ICsAlipayRecordService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonPayService;
import com.ccclubs.service.common.ICommonMoneyService.MoneyType;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.LZ;


public class AliPayCallbackAction {
	
	/**
	 * 由app端请求过来的回调
	 * @return
	 */
	public String callback(){
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
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
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ICommonPayService commonPayService = $.GetSpringBean("commonPayService");
		boolean result = commonPayService.executeSynAppAlipayCallback(params, null, false);
		
		try{
			if(result){
				out.println("success");	//请不要修改或删除
				out.flush();
				out.close();
			}else{
				out.println("fail");
				out.flush();
				out.close();
			}
		}catch(Exception e){
			
		}
		
		return "index";
	}
	
	/**
	 * 来自支付宝同步回调地址
	 * @return
	 */
	public String sync_redirect_web(){
		try {
			HttpServletRequest request = $.getServletRequest();
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
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

			// 支付人支付宝帐号
			String buyer_email = new String(request.getParameter("buyer_email").getBytes("ISO-8859-1"), "UTF-8");

			// 获取总金额
			String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8");

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

			// 计算得出通知验证结果
			boolean verify_result = com.alipay.util.AlipayNotify.verify(params);

			if (verify_result) {// 验证成功
				// ////////////////////////////////////////////////////////////////////////////////////////
				// 请在这里加上商户的业务逻辑程序代码

				// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序
					/** **************本地业务处理******************* */
					String url = updatePaySuccess(out_trade_no, trade_no, buyer_email);
					/** **************本地业务处理******************* */
					
					$.SendHtml("<script>window.location.href='"+url+"';</script>", "utf-8");
				}

				// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				// ////////////////////////////////////////////////////////////////////////////////////////
			} else {
				// 该页面可做页面美工编辑
				$.SendHtml("验证失败", "utf-8");// out.println("验证失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SendHtml("验证失败", "utf-8");
		}
		return "callback";
	}

	/**
	 * 来自支付宝异步通知地址
	 * @return
	 */
	public String async_notice_web(){
		try {
			HttpServletRequest request = $.getServletRequest();
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

			Logger.getLogger("alipay").debug($.getQueryString("UTF-8"));

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

			// 支付人支付宝帐号
			String buyer_email = new String(request.getParameter("buyer_email").getBytes("ISO-8859-1"), "UTF-8");

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			if (com.alipay.util.AlipayNotify.verify(params)) {// 验证成功
				// ////////////////////////////////////////////////////////////////////////////////////////
				// 请在这里加上商户的业务逻辑程序代码

				// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				if (trade_status.equals("TRADE_FINISHED")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序
					/** **************暂时不处理此情况******************* */
					// 注意：
					// 该种交易状态只在两种情况下出现
					// 1、开通了普通即时到账，买家付款成功后。
					// 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
				} else if (trade_status.equals("TRADE_SUCCESS")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序
					/** **************本地业务处理******************* */
					updatePaySuccess(out_trade_no, trade_no, buyer_email);
					/** **************本地业务处理******************* */
					// 注意：
					// 该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
				}

				// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				$.SendHtml("验证成功", "utf-8");
				// ////////////////////////////////////////////////////////////////////////////////////////
			} else {// 验证失败
				$.SendHtml("验证失败", "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendHtml("fail", SYSTEM.UTF8);
		}
		return null;
	}
	
	/**
	 * 来自支付宝同步回调地址
	 * @return
	 */
	public String sync_redirect_mobileweb(){
		try{
			HttpServletRequest request = $.getServletRequest();
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
	
			// 支付人支付宝帐号
			String buyer_email = "";
	
			// log
			String classPath = AccountAction.class.getResource("/").toURI().getPath() + "rechargeTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + "_alipay.txt";
			WeixinHelper.writeAppendToFile(classPath, DateHelper.formatDate(new Date(), "HH:mm:ss") + " - " + out_trade_no + " - " + trade_no + " - notice", "UTF-8");
	
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
	
			// 计算得出通知验证结果
			boolean verify_result = AlipayNotify.verifyReturn(params);
	
			if (verify_result) {// 验证成功
				// ////////////////////////////////////////////////////////////////////////////////////////
				// 请在这里加上商户的业务逻辑程序代码
	
				// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				if (result.equals("success")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序
					/** **************本地业务处理******************* */
					 String url = updatePaySuccess(out_trade_no, trade_no, buyer_email);
					/** **************本地业务处理******************* */
					// 该页面可做页面美工编辑
					 $.SendHtml("<script>window.location.href='"+url+"';</script>", "utf-8");
				//	CsAlipayRecord csAlipayRecord = csAlipayRecordService.getCsAlipayRecord($.add("csarTradeNo", out_trade_no));
				//	$.SetRequest("from", csAlipayRecord.getCsarBlank());
				}
				// $.SetRequest("amount", total_fee);
				// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				// ////////////////////////////////////////////////////////////////////////////////////////
			} else {
				// 该页面可做页面美工编辑
				$.SendHtml("验证失败", "utf-8");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 来自支付宝异步通知地址
	 * @return
	 */
	public String async_notice_mobileweb(){
		try{
			String classPath = AccountAction.class.getResource("/").toURI().getPath() + "rechargeTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + "_alipay.txt";
			HttpServletRequest request = $.getServletRequest();
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
	
			Logger.getLogger("alipay").debug($.getQueryString("UTF-8"));
	
			// 解密（如果是RSA签名需要解密，如果是MD5签名则下面一行清注释掉）
			// Map<String,String> decrypt_params =
			// AlipayNotify.decrypt(params);
			// XML解析notify_data数据
			Document doc_notify_data = DocumentHelper.parseText(params.get("notify_data"));
	
			// 商户订单号
			String out_trade_no = doc_notify_data.selectSingleNode("//notify/out_trade_no").getText();
	
			// 支付宝交易号
			String trade_no = doc_notify_data.selectSingleNode("//notify/trade_no").getText();
	
			// 交易状态
			String trade_status = doc_notify_data.selectSingleNode("//notify/trade_status").getText();
	
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
	
			// 支付人支付宝帐号
			String buyer_email = doc_notify_data.selectSingleNode("//notify/buyer_email").getText();
	
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			if (AlipayNotify.verifyNotify(params)) {// 验证成功
				// ////////////////////////////////////////////////////////////////////////////////////////
				// 请在这里加上商户的业务逻辑程序代码
	
				// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序
					updatePaySuccess(out_trade_no, trade_no, buyer_email);
					// 注意：
					// 该种交易状态只在两种情况下出现
					// 1、开通了普通即时到账，买家付款成功后。
					// 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
				}
				// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				WeixinHelper.writeAppendToFile(classPath, DateHelper.formatDate(new Date(), "HH:mm:ss") + " - " + out_trade_no + " - " + trade_no + " - notice -success", "UTF-8");
				return $.SendHtml("success", SYSTEM.UTF8); // 请不要修改或删除
				// ////////////////////////////////////////////////////////////////////////////////////////
			} else {// 验证失败
				return $.SendHtml("fail", SYSTEM.UTF8);
			}
		}catch(Exception e){
			
		}
		return null;
	}
	
	/**
	 * 更新支付结果成功
	 */
	String updatePaySuccess(final String out_trade_no, final String ali_trade_no, final String buyer_email) {
		final ICsAlipayRecordService	csAlipayRecordService = $.GetSpringBean("csAlipayRecordService");
		final ICommonMoneyService		commonMoneyService = $.GetSpringBean("commonMoneyService");
		String url = csAlipayRecordService.executeTransaction(new Function() {
			@Override
			public <T> T execute(Object... arg0) {
				CsAlipayRecord csAlipayRecord = csAlipayRecordService.getCsAlipayRecord($.add("csarTradeNo", out_trade_no));
				if (csAlipayRecord == null) {
					Logger.getRootLogger().error("支付宝充值返回[" + out_trade_no + "][" + ali_trade_no + "]错误 - 无法找到相关的充值记录 ");
					return null;
				} else if (csAlipayRecord.getCsarStatus().shortValue() == 0) {
					Logger.getRootLogger().info("支付宝充值返回[" + out_trade_no + "][" + ali_trade_no + "]");
					// 修改充值记录为已成功
					csAlipayRecordService.updateCsAlipayRecord$NotNull(new CsAlipayRecord().csarAlipayNo(ali_trade_no)// 支付宝交易号
							.csarStatus((short) 1)// 状态为成功
							.csarBuyerAccount(buyer_email)// 支付宝帐号
							.csarId(csAlipayRecord.getCsarId())// 原充值记录ID
							.csarUpdateTime(new Date())// 更新时间为当前时间
							);
					// 修改会员余额帐户
					String remark = "";
					remark = CommonMessage.MSG0309.replace("{money}", LZ.digit(csAlipayRecord.getCsarAmount(), 2));
					commonMoneyService.updateMoney(csAlipayRecord.getCsarMember(), MoneyType.Money, csAlipayRecord.getCsarAmount(), RecordType.支付宝充值, remark, null, csAlipayRecord.getCsarId(), CsAlipayRecord.class);

//					try{
//				        HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();
//				        HttpRequest request = new HttpRequest(HttpResultType.BYTES);
//				        //设置编码集
//				        request.setCharset(AlipayConfig.input_charset);
//				        request.setUrl(csAlipayRecord.getCsarThirdBack()+"?result=true");
//				        HttpResponse response = httpProtocolHandler.execute(request, "", "");
//				        if (response == null) {
//				            return null;
//				        }
//					}catch(Exception e){
//						e.printStackTrace();
//					}
					
					return null;
				} else {// 放弃处理
					Logger.getRootLogger().error("支付宝充值返回[" + out_trade_no + "][" + ali_trade_no + "]放弃处理 - " + $.json(csAlipayRecord));
					return null;
				}
			}
		});
		return url;
	}

	
	/**
	 * 来自支付宝操作中断返回地址
	 * @return
	 */
	public String failure(){
		
		return null;
	}
}
