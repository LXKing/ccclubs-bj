package com.ccclubs.action.weixin;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.lazy3q.web.helper.$;

/**
 * 适用于微信公众平台，缓存 token，ticket
 * 
 * @author qsxiaogang
 * 
 */
public final class WeixinTokenHelper {
	// ccclubs 微信公众号 appid 和 密钥
	/**
	 * 
	 * <li>测试微信公众号：gh_b0f83c822d8c</li>
	 * <li>测试微信 appid ：wx475a5cf195336fde</li>
	 * <li>测试微信 appsecret ：e0a37638ff8d8f49442d3bac1a5567c9</li>
	 * 
	 */
	public static String	WEIXIN_APP_ID				= "wxf7d08a80787fa584";																	// "";
	public static String	WEIXIN_APP_SECRET			= "6402e052c715f37b34fc7f1c6ab3d938";														// "";

	/**
	 * 微信下载媒体资源URL
	 */
	public static String	WEIXIN_DOWNLOAD_MEDIA_URL	= "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token={token}&media_id={media}";
	/**
	 * 微信发送模板消息URL
	 */
	public static String	WEIXIN_TEMPLATE_URL			= "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token={token}";
	
	
	private static String	token		= null;
	private static String	ticket		= null;			// js ticket
	private static Date		lastDate	= null;			// 最后获取时间
	private static long		duration	= 7000l * 1000l;	// 缓存时间，单位：毫秒

	/**
	 * 获取微信token，每天限制为10W次
	 * 
	 * @return
	 */
	public static String getToken() {
		httpToken();
		return token;
	}

	/**
	 * 获取微信jsticket
	 * 
	 * @return
	 */
	public static String getTicket() {
		httpToken();
		return ticket;
	}

	private static void httpToken() {
		try {
			if (lastDate != null) {
				// 超过最长的缓存时间
				if (System.currentTimeMillis() - lastDate.getTime() >= duration) {
					// 重新获取
					setToken();
				}
			} else {
				// 首次获取
				setToken();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 设置token,ticket,lastDate
	 */
	private static void setToken() {
		try {
			// 获取token ccclubs 微信
			String strAccessToken = $.http("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + WEIXIN_APP_ID + "&secret=" + WEIXIN_APP_SECRET);
			// 测试微信
			// String strAccessToken =
			// $.http("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx475a5cf195336fde&secret=e0a37638ff8d8f49442d3bac1a5567c9");
			Map mapAccessToken = $.eval(strAccessToken);
			token = mapAccessToken.get("access_token").toString();
			$.trace(mapAccessToken + "--------------------------access_token");
			// 获取ticket
			String strTicket = $.http("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + token + "&type=jsapi");
			Map mapTicket = $.eval(strTicket);
			ticket = mapTicket.get("ticket").toString();
			$.trace(mapTicket + "--------------------------ticket");
			// 设置获取时间
			lastDate = new Date();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 依据 当前url 获取调用微信JSAPI的config
	 * 
	 * @param url
	 * @return
	 */
	public static Map<String, String> getSign(String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";

		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + getTicket() + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
		System.out.println(string1);

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		ret.put("url", url);
		ret.put("jsapi_ticket", getTicket());
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}

	/**
	 * 字节转换成十六进制
	 * 
	 * @param hash
	 * @return
	 */
	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	/**
	 * 获得随机字符串
	 * 
	 * @return
	 */
	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 获得时间戳
	 * 
	 * @return
	 */
	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	/**
	 * 获得当前完整的url地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getCompleteUrl(HttpServletRequest request) {
		if (request == null) {
			return "";
		}
		// request.getRequestURL();
		String parmater = request.getQueryString();
		if ($.empty(parmater)) {
			return request.getRequestURL().toString();
		}
		return request.getRequestURL().toString() + "?" + parmater;
	}
}
