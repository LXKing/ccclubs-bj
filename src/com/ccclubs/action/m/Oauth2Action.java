package com.ccclubs.action.m;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOrder;
import com.ccclubs.service.admin.ICsOrderService;
import com.lazy3q.web.helper.$;

public class Oauth2Action {
	/**
	 * 微信网页授权入口
	 * 
	 * @return
	 */
	public String execute() {
		try {

			String fetchURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WeixinTokenHelper.WEIXIN_APP_ID + "&response_type=code&scope=snsapi_base&redirect_uri=";
			// String fetchURL =
			// "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx475a5cf195336fde&response_type=code&scope=snsapi_base&redirect_uri=";

			String baseURL = $.GetRequest("basePath");

			String flag = $.getString("flag", "");
			String redirect = $.getString("redirect", "");
			// 请求微信授权地址，传递 appid，response_type，scope，redirect_uri 等参数
			return $.Redirect(fetchURL + java.net.URLEncoder.encode(baseURL + "weixin/oauth2_weixin.html?flag=" + flag + "&redirect=" + redirect, "UTF-8") + "&state=STATE#wechat_redirect");
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendHtml("error", "UTF-8");
		}
	}

	@SuppressWarnings("rawtypes")
	public String weixin() {
		try {
			String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WeixinTokenHelper.WEIXIN_APP_ID + "&secret=" + WeixinTokenHelper.WEIXIN_APP_SECRET + "&grant_type=authorization_code&code=";
			// String url =
			// "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx475a5cf195336fde&secret=e0a37638ff8d8f49442d3bac1a5567c9&grant_type=authorization_code&code=";

			String code = $.getString("code");
			String flag = $.getString("flag", "");
			String redirect = $.getString("redirect", "");
			// 通过code，换取用户openid，传递 appid，secret，grant_type，code 等参数
			String strAccessToken = $.http(url + code);
			Map mapAccessToken = $.eval(strAccessToken);
			String openid = (String) mapAccessToken.get("openid");
			$.trace("poenid:" + openid + "-------------------------------------");
			// 设置openid session
			WeixinHelper.setOpenId(openid);
			if ($.empty(redirect)) {
				return $.Redirect($.GetRequest("basePath") + "m/index.html?flag=" + flag);
			} else {
				// fix bug 早期是 index
				if (redirect.equals("index")) {
					return $.Redirect($.GetRequest("basePath") + "m/index.html?flag=" + flag);
				} else {
					if (redirect.indexOf("?") > -1) {
						return $.Redirect($.GetRequest("basePath") + redirect + "&flag=" + flag);
					} else {
						return $.Redirect($.GetRequest("basePath") + redirect + "?flag=" + flag);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendHtml("error", "UTF-8");
		}
	}

	public String alipay() {
		String alipay_user_id = "";
		try {
			String redirect = $.getString("redirect", "");

			String auth_code = $.getString("auth_code", "");
			HashMap<String, String> map = WeixinHelper.getAlipayAuthUserInfo(auth_code);
			alipay_user_id = map.get("alipay_user_id");
			// 设置openid session
			WeixinHelper.setOpenId(alipay_user_id);
			$.setSession(SYSTEM.ccclubs_alipay_cert, map.get("cert_no"));
			$.setSession(SYSTEM.ccclubs_alipay_addr, map.get("address"));
			$.setSession(SYSTEM.ccclubs_alipay_name, map.get("real_name"));
			if ($.empty(redirect)) {
				return $.Redirect($.GetRequest("basePath") + "m/index.html");
			} else {
				if (redirect.equals("index")) {
					return $.Redirect($.GetRequest("basePath") + "m/index.html");
				} else {
					if (redirect.indexOf("?") > -1) {
						return $.Redirect($.GetRequest("basePath") + redirect);
					} else {
						return $.Redirect($.GetRequest("basePath") + redirect);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.Redirect($.GetRequest("basePath") + "weixin/403.html");
		}
	}

	public String zhimaRedirect() {
		// weixin.ccclubs.com/weixin/oauth2_zhimaRedirect.html?state=2088002361614263&redirect=account_credit&result=T
		// localhost:9998/weixin/oauth2_zhimaRedirect.html?state=first&redirect=account_credit&from=2088002361614263&result=T
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		String result = $.getString("result", "");
		String redirect = $.getString("redirect", "");

		if (result.equals("F")) {
			// 授权失败，跳转失败页面
			return $.Redirect($.GetRequest("basePath") + "weixin/403.html");
		}
		if (redirect.equals("account_credit")) {// 我的账户
			return $.Redirect($.GetRequest("basePath") + "m/home/" + redirect + ".html?state=" + $.getString("state", ""));
		}
		return $.Redirect($.GetRequest("basePath") + "m/index.html");
	}

	public String chuxing() {
		// 是否登录
		CsMember csMember = WeixinHelper.getCsMember();
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}

		// 是否认证过
		if (!csMember.getCsmVDrive().toString().equals("1")) {
			return $.Redirect($.GetRequest("basePath") + "m/register_identity.html");
		}
		// 是否授权过
		boolean auth = WeixinHelper.isZhimaAuthToken(csMember);
		if (!auth) {
			String url = WeixinHelper.getWindowAlipayZhimaAuthUrl(csMember, "first&redirect=account_credit");
			return $.Redirect(url);
		}
		// 订单列表（已预订0和使用中1）
		ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
		List<CsOrder> orderlist = csOrderService.getCsOrderList($.add(CsOrder.F.csoPayMember, csMember.getCsmId()).add("definex", " (cso_status='0' OR cso_status='1') "), -1);
		if (orderlist != null && !orderlist.isEmpty()) {
			if (orderlist.size() == 1) {
				// 开关门页面
				return $.Redirect($.GetRequest("basePath") + "m/home/order_operate.html?orderid=" + orderlist.get(0).getCsoId());
				// return $.Redirect($.GetRequest("basePath") +
				// "weixin/account_orderdetail.html?from=" + from + "&order=" +
				// orderlist.get(0).getCsoId());
			} else if (orderlist.size() > 1) {
				// 订单列表页面
				return $.Redirect($.GetRequest("basePath") + "m/home/order.html");
			} else {
				return $.Redirect($.GetRequest("basePath") + "m/index.html");
			}
		} else {
			return $.Redirect($.GetRequest("basePath") + "m/index.html");
		}
	}
}
