package com.ccclubs.action.m;

import java.util.Map;

import org.apache.log4j.Logger;

import com.lazy3q.web.helper.$;

public class OauthAction {
	/**
	 * 微信网页授权入口
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	public String execute() {
		try {
			
			String fetchURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf7d08a80787fa584&response_type=code&scope=snsapi_base&redirect_uri=";

			String baseURL = $.GetRequest("basePath");

			String flag = $.getString("flag", "");
			// 请求微信授权地址，传递 appid，response_type，scope，redirect_uri 等参数
			return $.SendHtml("error", "UTF-8");
			//return $.Redirect(fetchURL + java.net.URLEncoder.encode(baseURL + "weixin/oauth_weixin.html?flag=" + flag, "UTF-8") + "&state=STATE#wechat_redirect");
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendHtml("error", "UTF-8");
		}
	}

	@SuppressWarnings("rawtypes")
	public String weixin() {
		try {
			String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxf7d08a80787fa584&secret=6402e052c715f37b34fc7f1c6ab3d938&grant_type=authorization_code&code=";

			String code = $.getString("code");
			String flag = $.getString("flag", "");
			// 通过code，换取用户openid，传递 appid，secret，grant_type，code 等参数
			String strAccessToken = $.http(url + code);
			$.trace("mapAccessToken：" + strAccessToken);
			Map mapAccessToken = $.eval(strAccessToken);
			String openid = (String) mapAccessToken.get("openid");
			return $.Redirect($.GetRequest("basePath") + "/weixin/account_register.html?from=" + openid + "&flag=" + flag);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendHtml("error", "UTF-8");
		}
	}
}
