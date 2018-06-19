package com.ccclubs.action.weixin;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.ccclubs.action.weixin.scripts.CacheSyncWXScript;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.DateHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.redis.DBIndex;
import com.ccclubs.helper.redis.DefaultJRedisClient;
import com.ccclubs.model.CsMember;
import com.ccclubs.service.admin.ICsMemberService;
import com.lazy3q.web.helper.$;

public class LoginAction {

	ICsMemberService	csMemberService;
	
	final DefaultJRedisClient<String, Object> jr = new DefaultJRedisClient<String, Object>(DBIndex.APP_WEIXIN);

	/**
	 * 默认登录页面，如果已存在微信openid，直接跳转账户页面
	 * 
	 * @return
	 */
	public String execute() {
		try {
			if (!WeixinHelper.isCorrectOpenId()) {
				return WeixinHelper.gotoLogin();
			}
			if (WeixinHelper.getCsMember() != null) {
				return "success";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "login";
	}

	/**
	 * 提交微信登录，并更新微信对应的openids
	 * <p>
	 * tips：注意微信openid去重，只包含一个微信openid
	 * </p>
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	public String submit() {
		try {
			String from = WeixinHelper.getOpenId();
			// $.SetRequest("from", from);
			String strUsername = $.getString("username");
			String strPassword = $.getString("password");
			// 记录一些东西
			String classPath = LoginAction.class.getResource("/").toURI().getPath() + "weixinTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + "_wxLogin.txt";
			WeixinHelper.writeAppendToFile(classPath, DateHelper.formatDate(new Date(), "HH:mm:ss") + " - " + from, "UTF-8");
			if (!WeixinHelper.isCorrectOpenId()) {
				// 你还没有输入帐号
				return $.SendHtml("107", "UTF-8");
			}
			if (SystemHelper.isNullOrEmpty(strUsername)) {
				// 你还没有输入帐号
				return $.SendHtml("102", "UTF-8");
			}
			if (SystemHelper.isNullOrEmpty(strPassword)) {
				// 你还没有输入密码！
				return $.SendHtml("103", "UTF-8");
			}
			CsMember user = null;

			if (SystemHelper.isEmail(strUsername))// 邮箱方式登录
				user = csMemberService.getCsMember($.Map("csmEmail", strUsername));
			else if (SystemHelper.isMobile(strUsername))// 手机方式登录
				user = csMemberService.getCsMember($.Map("csmMobile", strUsername));
			else
				// 帐号方式登录
				user = csMemberService.getCsMember($.Map("csmUsername", strUsername));

			if (user == null) {
				// 你输入的帐号不正确，请重新输入
				return $.SendHtml("105", "UTF-8");
			}
			if (user.getCsmStatus() != 1) {
				// 该帐户已经失效，请联系车纷享
				return $.SendHtml("106", "UTF-8");
			}

			if (!$.md5(strPassword).equals(user.getCsmPassword().toUpperCase())) {
				// 你输入的密码不正确，请重新输入。
				return $.SendHtml("105", "UTF-8");
			}

			HttpServletRequest request = ServletActionContext.getRequest();
			String csmLastIp = user.getCsmLastIp();
			java.util.Date csmLastTime = user.getCsmLastTime();

			CsMember updateMember = new CsMember().csmId(user.getCsmId()).csmLastIp(request.getRemoteAddr()).csmLastTime(new java.util.Date());
			// 更新微信，支付宝注册来源
			switch (WeixinHelper.switchOrderPlatform(WeixinHelper.getOpenId()).ordinal()) {
				case 2:
					updateMember.csmWeixinFlag(from);
					user.csmWeixinFlag(from);
					break;
				case 5:
					updateMember.csmAlipayFlag(from);
					user.csmAlipayFlag(from);
					break;
			}

			updateMember.update();

			user.setCsmLastIp(request.getRemoteAddr());
			user.setCsmLastTime(new java.util.Date());
			WeixinHelper.writeAppendToFile(classPath, DateHelper.formatDate(new Date(), "HH:mm:ss") + " - " + from, "UTF-8");

			// 设置登录 session
			$.setSession(SYSTEM.ccclubs_web_login, user);
			$.setSession(SYSTEM.ccclubs_web_login_id, user.getCsmId());
			// 设置 cache
			try {
				jr.set(from, user.getCsmId().toString());
			} catch (Exception e) {
				e.printStackTrace();
				// 记录一些东西
				String path = WeixinHelper.class.getResource("/").toURI().getPath() + "redisTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + "_wx.txt";
				WeixinHelper.writeAppendToFile(path, DateHelper.formatDate(new Date(), "HH:mm:ss") + " - " + e.getMessage(), "UTF-8");

				// 再尝试一遍
				jr.set(from, user.getCsmId().toString());
			}
			// 更新当前用户的登录信息
			WeixinHelper.updateUserLogin(user);

			return $.SendHtml("100", "UTF-8");
		} catch (Exception ex) {
			return $.SendHtml("9999", "UTF-8");
		}

	}

	/**
	 * 注销，解除微信openid和车纷享账户的关联
	 * 
	 * @return
	 */
	public String logout() {
		try {
			CsMember csMember = WeixinHelper.getCsMember();
			if (csMember == null || $.empty(WeixinHelper.getOpenId()))
				return $.SendHtml("101", "UTF-8");

			String from = WeixinHelper.getOpenId();
			CsMember updateMember = new CsMember().csmId(csMember.getCsmId());
			if ($.empty(from)) {
				// 什么也不做
			} else if (from.length() == 28) {
				updateMember.csmWeixinFlag("");
				csMember.csmWeixinFlag("");
			} else if (from.length() == 16) {
				updateMember.csmAlipayFlag("");
				csMember.csmAlipayFlag("");
			} else {
				// 什么也不做
			}
			updateMember.update();
			$.setSession(SYSTEM.ccclubs_web_login, null);
			$.setSession(SYSTEM.ccclubs_web_login_id, null);
			// 删除 cache
			try {
				jr.del(from);
			} catch (Exception e) {
				e.printStackTrace();
				// 记录一些东西
				String path = WeixinHelper.class.getResource("/").toURI().getPath() + "redisTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + "_wx.txt";
				WeixinHelper.writeAppendToFile(path, DateHelper.formatDate(new Date(), "HH:mm:ss") + " - " + e.getMessage(), "UTF-8");

				// 再尝试一遍
				jr.del(from);
			}

			return $.SendHtml("100", "UTF-8");
		} catch (Exception ex) {
			return $.SendHtml("9999", "UTF-8");
		}
	}

	public String loadsession() {
		String ret = new CacheSyncWXScript().getContent(null, null);
		$.SendHtml(ret, "UTF-8");
		return null;
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}

}
