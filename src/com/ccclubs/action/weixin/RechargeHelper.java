package com.ccclubs.action.weixin;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ccclubs.action.weixin.alipay.config.AlipayConfig;
import com.ccclubs.action.weixin.alipay.util.AlipaySubmit;
import com.ccclubs.action.weixin.hzbank.HZBANKSetting;
import com.ccclubs.action.weixin.hzbank.NetSignServer;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsAlipayRecord;
import com.ccclubs.model.CsMember;
import com.ccclubs.service.admin.ICsAlipayRecordService;
import com.lazy3q.web.helper.$;

public class RechargeHelper {

	/**
	 * 通过支付宝充值
	 * @param csMember 会员
	 * @param amount 金额
	 * @param csiId 购买的对应宝贝id
	 * @param num 购买的对应宝贝数量
	 * @return
	 */
	public static String payFromAlipay(CsMember csMember, Double amount, Long csiId, int num) {
		try {

			// 支付宝网关地址
			String ALIPAY_GATEWAY_NEW = "http://wappaygw.alipay.com/service/rest.htm?";

			// //////////////////////////////////调用授权接口alipay.wap.trade.create.direct获取授权码token//////////////////////////////////////

			// 返回格式
			String format = "xml";
			// 必填，不需要修改

			// 返回格式
			String v = "2.0";
			// 必填，不需要修改

			// 请求号
			String req_id = $.date(new Date(), "yyyyMMddHHmmssSSS");

			// 必填，须保证每次请求都是唯一

			// req_data详细信息

			// 服务器异步通知页面路径
			String notify_url = $.GetRequest("basePath") + "m/home/account_notice.html";
			// 需http://格式的完整路径，不能加?id=123这类自定义参数

			// 页面跳转同步通知页面路径
			String call_back_url = $.GetRequest("basePath") + "m/home/account_callback.html";
			// 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

			// 操作中断返回地址
			String merchant_url = $.GetRequest("basePath") + "weixin/failure.html";
			// 用户付款中途退出返回商户的地址。需http://格式的完整路径，不允许加?id=123这类自定义参数

			// 卖家支付宝帐户
			String seller_email = AlipayConfig.seller_email;
			// 必填

			// 商户订单号
			String out_trade_no = $.date(new Date(), "yyyyMMddHHmmssSSS") + csMember.getCsmId();
			// 商户网站订单系统中唯一订单号，必填

			// 订单名称
			String subject = "车纷享自助租车";
			// 必填

			// 付款金额
			String total_fee = amount.toString();
			// 必填

			// 请求业务参数详细
			String req_dataToken = "<direct_trade_create_req><notify_url>" + notify_url + "</notify_url><call_back_url>" + call_back_url + "</call_back_url><seller_account_name>" + seller_email + "</seller_account_name><out_trade_no>" + out_trade_no + "</out_trade_no><subject>" + subject
					+ "</subject><total_fee>" + total_fee + "</total_fee><merchant_url>" + merchant_url + "</merchant_url></direct_trade_create_req>";
			// 必填

			// ////////////////////////////////////////////////////////////////////////////////

			// 把请求参数打包成数组
			Map<String, String> sParaTempToken = new HashMap<String, String>();
			sParaTempToken.put("service", "alipay.wap.trade.create.direct");
			sParaTempToken.put("partner", AlipayConfig.partner);
			sParaTempToken.put("_input_charset", AlipayConfig.input_charset);
			sParaTempToken.put("sec_id", AlipayConfig.sign_type);
			sParaTempToken.put("format", format);
			sParaTempToken.put("v", v);
			sParaTempToken.put("req_id", req_id);
			sParaTempToken.put("req_data", req_dataToken);

			// /////////////////////////////////////保存充值记录到数据库///////////////////////////////////////////
			CsAlipayRecord csAlipayRecord = new CsAlipayRecord();
			csAlipayRecord.setNotNull(SystemHelper.getSrvHost().getShId(),(short)0, out_trade_no, csMember.getCsmId(), csMember.getCsmName(), amount, new Date(), new Date(), (short) 0);
			csAlipayRecord.csarRefund(0d).csarBlank(WeixinHelper.getOpenId()).csarPayMethod("wap01");
			// ------ 是否是购买现金券
			if (csiId != null && csiId != 0l && num != 0) {
				csAlipayRecord.csarBuyItem(csiId);
				csAlipayRecord.csarBuyCount(num);
			}

			ICsAlipayRecordService csAlipayRecordService = $.getBean("csAlipayRecordService");

			csAlipayRecordService.saveCsAlipayRecord(csAlipayRecord);
			// sParaTempToken =
			// AlipaySubmit.buildRequestPara(sParaTempToken);
			// 建立请求
			String sHtmlTextToken = AlipaySubmit.buildRequest(ALIPAY_GATEWAY_NEW, "", "", sParaTempToken);
			// URLDECODE返回的信息
			sHtmlTextToken = URLDecoder.decode(sHtmlTextToken, AlipayConfig.input_charset);
			// 获取token
			String request_token = AlipaySubmit.getRequestToken(sHtmlTextToken);
			// out.println(request_token);

			// //////////////////////////////////根据授权码token调用交易接口alipay.wap.auth.authAndExecute//////////////////////////////////////

			// 业务详细
			String req_data = "<auth_and_execute_req><request_token>" + request_token + "</request_token></auth_and_execute_req>";
			// 必填

			// 把请求参数打包成数组
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", "alipay.wap.auth.authAndExecute");
			sParaTemp.put("partner", AlipayConfig.partner);
			sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("sec_id", AlipayConfig.sign_type);
			sParaTemp.put("format", format);
			sParaTemp.put("v", v);
			sParaTemp.put("req_data", req_data);

			// 建立请求
			String sHtmlText = AlipaySubmit.buildRequest(ALIPAY_GATEWAY_NEW, sParaTemp, "get", "确认");

			return $.SendHtml(sHtmlText, SYSTEM.UTF8);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 通过杭州银行充值
	 * 
	 * @param csMember
	 * @return
	 */
	public static String payFromHzbank(CsMember csMember, Double money, String certNo) {
		try {

			HZBANKSetting hzset = new HZBANKSetting();

			String hzbankDir = $.config("hzbank_dir");
			int ret = hzset.initialize(true, hzbankDir + "/log/", hzbankDir + "/HZBANKCert/" + $.config("mer_id") + ".pfx", $.config("mer_password"), hzbankDir + "/HZBANKCert/Operation_CA2.cer", $.config("hzbank_dn")); // 该代码只需调用一次

			if (ret != 0) { // 初始化失败
				System.out.print("初始化失败");
				return null;
			}

			String merID; // 商户编码
			String orderId; // 订单编号
			String amount; // 订单金额
			String currencyType; // 订单币种
			String jumpSeconds; // 自动跳转时间
			String pageURI; // 页面URI
			String selfURI; // 自动URI
			String sourceMsg;
			// String certNo = "330184198609194112";
			// merID = BEASetting.MerchantID;
			Date now = new Date();
			merID = $.config("mer_id");
			orderId = SystemHelper.formatDate(now, "yyyyMMddHHmmssSSS") + $.zerofill($.rand(99), 2);
			amount = $.digit(money, 2);
			currencyType = "00";
			jumpSeconds = "3";
			// selfURI = $.config("weixin.domain") +
			// "weixin/account_notice.html";
			selfURI = $.config("weixin.domain") + "/weixin/account_notice.html";
			pageURI = $.config("weixin.domain") + "/weixin/account_callback.html";
			// 组织签名原文
			java.lang.StringBuffer sourBuffer = new java.lang.StringBuffer();
			// merID|orderNo|currencyType|transferSum|orderIndate|pageURL|selfURL|jumpSeconds|certNo
			sourBuffer.append(merID);
			sourBuffer.append("|");
			sourBuffer.append(orderId);
			sourBuffer.append("|");
			sourBuffer.append(currencyType);
			sourBuffer.append("|");
			sourBuffer.append(amount);
			sourBuffer.append("|");
			sourBuffer.append($.date(now, "yyyy-MM-dd HH:mm:ss"));
			sourBuffer.append("|");
			sourBuffer.append(pageURI);
			sourBuffer.append("|");
			sourBuffer.append(selfURI);
			sourBuffer.append("|");
			sourBuffer.append(jumpSeconds);
			sourBuffer.append("|");
			sourBuffer.append(certNo);

			sourceMsg = sourBuffer.toString();

			NetSignServer nss = new NetSignServer();

			String merchantDN = HZBANKSetting.MerchantCertDN;

			nss.NSSetPlainText(sourceMsg.getBytes("UTF-8"));

			byte bSignMsg[] = nss.NSDetachedSign(merchantDN);

			if (nss.getLastErrnum() < 0) {
				$.trace("ERROR:商户端签名失败");
				return null;
			}

			String signMsg = new String(bSignMsg, "GBK");

			// /////////////////////////////////////保存充值记录到数据库///////////////////////////////////////////
			CsAlipayRecord csAlipayRecord = new CsAlipayRecord();
			csAlipayRecord.setNotNull(SystemHelper.getSrvHost().getShId(), (short)0,orderId, csMember.getCsmId(), csMember.getCsmName(), $.getDouble("alimoney"), new Date(), new Date(), (short) 0);
			csAlipayRecord.csarRefund(0d).csarBlank($.getString("from")).csarPayMethod("HZBANK");
			ICsAlipayRecordService csAlipayRecordService = $.getBean("csAlipayRecordService");
			csAlipayRecordService.saveCsAlipayRecord(csAlipayRecord);

			// 把请求参数打包成数组
			Map<String, String> sParaTemp = new HashMap<String, String>();
			// sParaTemp.put("dse_operationName", "PY8000SrvOp");
			// merID|orderNo|currencyType|transferSum|orderIndate|pageURL|selfURL|jumpSeconds|certNo
			// + sourceMsg + openId
			sParaTemp.put("merID", merID);
			sParaTemp.put("orderNo", orderId);
			sParaTemp.put("currencyType", currencyType);
			sParaTemp.put("transferSum", amount);
			sParaTemp.put("orderIndate", $.date(now, "yyyy-MM-dd HH:mm:ss"));
			sParaTemp.put("pageURI", pageURI);
			sParaTemp.put("selfURI", selfURI);
			sParaTemp.put("jumpSeconds", jumpSeconds);
			sParaTemp.put("certNo", certNo);
			sParaTemp.put("sourceMsg", signMsg);
			sParaTemp.put("openId", $.getString("from"));// "o_oFvuKz9eOEZJPtxOOWogqMHe88"

			// 建立请求
			String sHtmlText = SystemHelper.buildRequest("https://weixin.hzbank.com.cn/wap/carPay.jsp", sParaTemp, "post", "确认");

			return $.SendHtml(sHtmlText, SYSTEM.UTF8);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public static String payFromAlipayToConfirm(CsMember csMember, Double amount, Long csiId, int num) {
		try {

			// 支付宝网关地址
			String ALIPAY_GATEWAY_NEW = "http://wappaygw.alipay.com/service/rest.htm?";

			// //////////////////////////////////调用授权接口alipay.wap.trade.create.direct获取授权码token//////////////////////////////////////

			// 返回格式
			String format = "xml";
			// 必填，不需要修改

			// 返回格式
			String v = "2.0";
			// 必填，不需要修改

			// 请求号
			String req_id = $.date(new Date(), "yyyyMMddHHmmssSSS");
			// 必填，须保证每次请求都是唯一
			
			// req_data详细信息
			// 服务器异步通知页面路径
			String notify_url = $.GetRequest("basePath") + "m/home/account_notice.html";
			// 需http://格式的完整路径，不能加?id=123这类自定义参数

			// 页面跳转同步通知页面路径
			String call_back_url = $.GetRequest("basePath") + "m/confirm_callback.html";
			// 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

			// 操作中断返回地址
			String merchant_url = $.GetRequest("basePath") + "weixin/failure.html";
			// 用户付款中途退出返回商户的地址。需http://格式的完整路径，不允许加?id=123这类自定义参数

			// 卖家支付宝帐户
			String seller_email = AlipayConfig.seller_email;
			// 必填

			// 商户订单号
			String out_trade_no = $.date(new Date(), "yyyyMMddHHmmssSSS") + csMember.getCsmId();
			// 商户网站订单系统中唯一订单号，必填

			// 订单名称
			String subject = "车纷享自助租车";
			// 必填

			// 付款金额
			String total_fee = amount.toString();
			// 必填

			// 请求业务参数详细
			String req_dataToken = "<direct_trade_create_req><notify_url>" + notify_url + "</notify_url><call_back_url>" + call_back_url + "</call_back_url><seller_account_name>" + seller_email + "</seller_account_name><out_trade_no>" + out_trade_no + "</out_trade_no><subject>" + subject
					+ "</subject><total_fee>" + total_fee + "</total_fee><merchant_url>" + merchant_url + "</merchant_url></direct_trade_create_req>";
			// 必填

			// ////////////////////////////////////////////////////////////////////////////////

			// 把请求参数打包成数组
			Map<String, String> sParaTempToken = new HashMap<String, String>();
			sParaTempToken.put("service", "alipay.wap.trade.create.direct");
			sParaTempToken.put("partner", AlipayConfig.partner);
			sParaTempToken.put("_input_charset", AlipayConfig.input_charset);
			sParaTempToken.put("sec_id", AlipayConfig.sign_type);
			sParaTempToken.put("format", format);
			sParaTempToken.put("v", v);
			sParaTempToken.put("req_id", req_id);
			sParaTempToken.put("req_data", req_dataToken);

			// /////////////////////////////////////保存充值记录到数据库///////////////////////////////////////////
			CsAlipayRecord csAlipayRecord = new CsAlipayRecord();
			csAlipayRecord.setNotNull(SystemHelper.getSrvHost().getShId(),(short)0, out_trade_no, csMember.getCsmId(), csMember.getCsmName(), amount, new Date(), new Date(), (short) 0);
			csAlipayRecord.csarRefund(0d).csarBlank(WeixinHelper.getOpenId()).csarPayMethod("wap01");
			// ------ 是否是购买现金券
			if (csiId != null && csiId != 0l && num != 0) {
				csAlipayRecord.csarBuyItem(csiId);
				csAlipayRecord.csarBuyCount(num);
			}

			ICsAlipayRecordService csAlipayRecordService = $.getBean("csAlipayRecordService");

			csAlipayRecordService.saveCsAlipayRecord(csAlipayRecord);
			// 建立请求
			String sHtmlTextToken = AlipaySubmit.buildRequest(ALIPAY_GATEWAY_NEW, "", "", sParaTempToken);
			// URLDECODE返回的信息
			sHtmlTextToken = URLDecoder.decode(sHtmlTextToken, AlipayConfig.input_charset);
			// 获取token
			String request_token = AlipaySubmit.getRequestToken(sHtmlTextToken);
			// out.println(request_token);

			// //////////////////////////////////根据授权码token调用交易接口alipay.wap.auth.authAndExecute//////////////////////////////////////

			// 业务详细
			String req_data = "<auth_and_execute_req><request_token>" + request_token + "</request_token></auth_and_execute_req>";
			// 必填

			// 把请求参数打包成数组
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", "alipay.wap.auth.authAndExecute");
			sParaTemp.put("partner", AlipayConfig.partner);
			sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("sec_id", AlipayConfig.sign_type);
			sParaTemp.put("format", format);
			sParaTemp.put("v", v);
			sParaTemp.put("req_data", req_data);

			// 建立请求
			String sHtmlText = AlipaySubmit.buildRequest(ALIPAY_GATEWAY_NEW, sParaTemp, "get", "确认");

			return $.SendHtml(sHtmlText, SYSTEM.UTF8);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
