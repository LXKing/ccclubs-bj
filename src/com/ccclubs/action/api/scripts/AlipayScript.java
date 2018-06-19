package com.ccclubs.action.api.scripts;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ccclubs.action.api.model.Result;
import com.ccclubs.action.weixin.WeixinHelper;
import com.ccclubs.action.weixin.alipay.config.AlipayConfig;
import com.ccclubs.action.weixin.alipay.util.AlipaySubmit;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsAlipayRecord;
import com.ccclubs.model.CsMember;
import com.ccclubs.service.admin.ICsAlipayRecordService;
import com.ccclubs.service.common.ICommonPayService;
import com.lazy3q.web.helper.$;

/**
 * 支付接口脚本
 * 
 * @author 
 *
 */
public class AlipayScript extends BaseScript{
	
	/**
	 * 获取支付url
	 */
	public static Result genPayUrl(Double amount){
		Result rs = new Result();
		
		//非法请求
		if(getOauth() == null){
			return new Result(false, 100); 
		}
		
		//参数异常
		if(amount == null){
			return new Result(false, 101);
		}
		
		ICommonPayService commonPayService = $.GetSpringBean("commonPayService");
		String appid = $.getSession("appid");
		String noticeUrl = "http://"+ getServerUrl() +"/api/alipay/callback.html";
		String result = null;
		try {
			result = commonPayService.executeGetAppAlipayString(getOauth(), amount, noticeUrl, appid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		rs.setData(result).setResult(true);
		return rs;
	}
	
	/**
	 * 执行支付（适用于web网站/web微网站）调用支付，返回支付宝HTML
	 * @param amount
	 * @param notifyUrl
	 * @param sourceType(1网站， 2微网站)
	 * @return
	 */
	public static Result payFromMobile(Double amount, String notifyUrl){
		Result rs = new Result();
		
		//非法请求
		if(getOauth() == null){
			return new Result(false, 100); 
		}
		
		//参数异常
		if(amount == null){
			return new Result(false, 101);
		}
		
		// 支付宝网关地址
		String ALIPAY_GATEWAY_NEW = "http://wappaygw.alipay.com/service/rest.htm?";
		
		CsMember member = CsMember.get(getOauth());
		if (member.get$csmInfo() == null || $.empty(member.get$csmInfo().getCsmiName())) {
			return new Result(false, 102);
		}

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
		String notify_url = "http://"+ getServerUrl() + "/api/alipay/async_notice_mobileweb.html";
		// 需http://格式的完整路径，不能加?id=123这类自定义参数

		// 页面跳转同步通知页面路径
		String call_back_url = "http://"+ getServerUrl() + "/api/alipay/sync_redirect_mobileweb.html";
		// 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

		// 操作中断返回地址
		String merchant_url = "http://"+ getServerUrl() + "/api/alipay/failure.html";
		// 用户付款中途退出返回商户的地址。需http://格式的完整路径，不允许加?id=123这类自定义参数

		// 卖家支付宝帐户
		String seller_email = AlipayConfig.seller_email;
		// 必填

		// 商户订单号
		String out_trade_no = $.date(new Date(), "yyyyMMddHHmmssSSS") + member.getCsmId();
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

		
		try {
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
			csAlipayRecord.setNotNull(SystemHelper.getSrvHost().getShId(), (short)0,out_trade_no, member.getCsmId(), member.getCsmName(), amount, new Date(), new Date(), (short) 0);
			csAlipayRecord.csarRefund(0d).csarBlank(WeixinHelper.getOpenId()).csarPayMethod("wap01").csarThirdBack(notifyUrl);
			// ------ 是否是购买现金券
			//if (csiId != null && csiId != 0l && num != 0) {
				csAlipayRecord.csarBuyItem(0L);
				csAlipayRecord.csarBuyCount(0);
			//}	
	
			ICsAlipayRecordService csAlipayRecordService = $.getBean("csAlipayRecordService");
	
			csAlipayRecordService.saveCsAlipayRecord(csAlipayRecord);
			// sParaTempToken =
			// AlipaySubmit.buildRequestPara(sParaTempToken);
			// 建立请求
			String sHtmlTextToken;
			
				sHtmlTextToken = AlipaySubmit.buildRequest(ALIPAY_GATEWAY_NEW, "", "", sParaTempToken);
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
			rs.setResult(true);
			rs.setData(sHtmlText);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 从网站过来的支付
	 * @param notifyUrl
	 * @return
	 */
	public static Result payFromWeb(Double amount, String notifyUrl){
		Result rs = new Result();
		
		//非法请求
		if(getOauth() == null){
			return new Result(false, 100); 
		}
		
		//参数异常
		if(amount == null){
			return new Result(false, 101);
		}
		
		//个人资料待完善
		CsMember member = CsMember.get(getOauth());
		if (member.get$csmInfo() == null || $.empty(member.get$csmInfo().getCsmiName())) {
			return new Result(false, 102);
		}
		
		ICsAlipayRecordService	csAlipayRecordService = $.GetSpringBean("csAlipayRecordService");

		// 支付类型
		String payment_type = "1";
		
		// 必填，不能修改
		// 服务器异步通知页面路径
		String notify_url = "http://"+ getServerUrl() + "/api/alipay/async_notice_web.html";
		// 需http://格式的完整路径，不能加?id=123这类自定义参数

		// 页面跳转同步通知页面路径
		String return_url = "http://"+ getServerUrl() + "/api/alipay/sync_redirect_web.html";
		// 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

		// 卖家支付宝帐户
		String seller_email = AlipayConfig.seller_email;
		// 必填

		// 商户订单号
		String out_trade_no = $.date(new Date(), "yyyyMMddHHmmssSSS") + member.getCsmId();
		// 商户网站订单系统中唯一订单号，必填

		// 订单名称
		String subject = "车纷享自助租车";
		// 必填

		// 付款金额
		String total_fee = amount.toString();
		// 必填

		// 订单描述
		String body = "车纷享自助租车，方便、快捷";

		// 默认支付方式
		String paymethod = "directPay";
		// 必填
		// 默认网银
		String defaultbank = "";
		// 必填，银行简码请参考接口技术文档

		// 商品展示地址
		String show_url = $.config("domain");
		// 需以http://开头的完整路径，例如：http://www.xxx.com/myorder.html

		// 防钓鱼时间戳
		String anti_phishing_key = "";
		// 若要使用请调用类文件submit中的query_timestamp函数

		// 客户端的IP地址
		String exter_invoke_ip = "";
		// 非局域网的外网IP地址，如：221.0.0.1

		// /////////////////////////////////////保存充值记录到数据库///////////////////////////////////////////
		CsAlipayRecord csAlipayRecord = new CsAlipayRecord();
		csAlipayRecord.setNotNull(SystemHelper.getSrvHost().getShId(), (short)0,out_trade_no, member.getCsmId(), member.get$csmInfo().getCsmiName(), amount, new Date(), new Date(), (short) 0);
		csAlipayRecord.csarRefund(0d).csarBlank(defaultbank).csarPayMethod(paymethod).csarThirdBack(notifyUrl);
		csAlipayRecordService.saveCsAlipayRecord(csAlipayRecord);
		// ////////////////////////////////////////////////////////////////////////////////

		// 把请求参数打包成数组,
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("seller_email", seller_email);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		sParaTemp.put("paymethod", paymethod);
		sParaTemp.put("defaultbank", defaultbank);
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);

		// 建立请求
		String sHtmlText = com.alipay.util.AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
		rs.setResult(true);
		rs.setData(sHtmlText);
		return rs;
	}
	
	/**
	 * 支付回调
	 */
	public static Result payCallback(String resultInfo){
		Result rs = new Result();
		
		//非法请求
		if(getOauth() == null){
			return new Result(false, 100); 
		}
		
		//参数异常
		if(resultInfo == null){
			return new Result(false, 101);
		}
		
		ICommonPayService commonPayService = $.GetSpringBean("commonPayService");
//		Map<String, String> paramMap = URLRequest(resultInfo);
		int iSignContentEnd = resultInfo.indexOf ("&sign_type=");
        String signContent = resultInfo.substring (0, iSignContentEnd);  
		Map<String, String> paramMap = parseUrl(resultInfo);
		
		boolean result = commonPayService.executeSynAppAlipayCallback(paramMap, signContent, true);
		rs.setData(result).setResult(true);
		
		return rs;
	}
	
	public static Map<String, String> parseUrl(String url){
		Map<String, String> result = new HashMap<String, String>();
		if(url==null)return result;
		String[] paramArr = url.split("[&]");
		for(String kvstr : paramArr){
			for(int i=0; i<kvstr.length(); i++){
				if(kvstr.charAt(i) == '=' && (i+1!=kvstr.length()) && kvstr.charAt(i+1) == '"' 
						&& kvstr.charAt(kvstr.length()-1) == '"' && i+2!=kvstr.length()){
					result.put(kvstr.substring(0, i), kvstr.substring(i+2, kvstr.length() - 1));
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 获取API服务器地址
	 * @return
	 */
	public static String getServerUrl(){
		String api = $.config("api.host");
		if(StringUtils.isEmpty(api))return "api.ccclubs.com";
		return api;
	}
	
}
