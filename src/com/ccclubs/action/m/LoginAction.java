package com.ccclubs.action.m;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.ccclubs.action.m.util.ResponseEnvelope;
import com.ccclubs.action.weixin.WeixinHelper;
import com.ccclubs.action.weixin.scripts.CacheSyncWXScript;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.DateHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.redis.DBIndex;
import com.ccclubs.helper.redis.DefaultJRedisClient;
import com.ccclubs.model.CsMember;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.common.From;
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
	@SuppressWarnings({ "unused" })
	public String submit() {
		try {
			String from = WeixinHelper.getOpenId();
			// $.SetRequest("from", from);
			String strUsername = $.getString("username");
			String strPassword = $.getString("password");
			// 记录一些东西
			String classPath = LoginAction.class.getResource("/").toURI().getPath() + "weixinTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + "_wxLogin.txt";
			WeixinHelper.writeAppendToFile(classPath, DateHelper.formatDate(new Date(), "HH:mm:ss") + " - " + from, "UTF-8");
			if (SystemHelper.isNullOrEmpty(strUsername)) {
				$.SetTips("你还没有输入帐号");
				return "login";
			}
			if (SystemHelper.isNullOrEmpty(strPassword)) {
				$.SetTips("你还没有输入密码！");
				return "login";
			}
			CsMember user = null;

			if (SystemHelper.isEmail(strUsername))// 邮箱方式登录
				user = csMemberService.getCsMember($.Map("csmEmail", strUsername));
			else if (SystemHelper.isMobile(strUsername))// 手机方式登录
				user = csMemberService.getCsMember($.Map("csmMobile", strUsername));
			else// 帐号方式登录
				user = csMemberService.getCsMember($.Map("csmUsername", strUsername));

			if (user == null) {
				$.SetTips("你输入的帐号不正确，请重新输入");
				return "login";
			}
			if (user.getCsmStatus() != 1) {
				$.SetTips("该帐户已经失效，请联系车纷享");
				return "login";
			}

			if (!$.md5(strPassword).equals(user.getCsmPassword().toUpperCase())) {
				$.SetTips("你输入的密码不正确，请重新输入。");
				return "login";
			}

			HttpServletRequest request = ServletActionContext.getRequest();
			String csmLastIp = user.getCsmLastIp();
			java.util.Date csmLastTime = user.getCsmLastTime();

			CsMember updateMember = new CsMember().csmId(user.getCsmId()).csmLastIp(request.getRemoteAddr()).csmLastTime(new java.util.Date());
			// 更新微信，支付宝注册来源
			if(WeixinHelper.switchOrderPlatform(WeixinHelper.getOpenId()).equals(From.微信)){
				updateMember.csmWeixinFlag(from);
				user.csmWeixinFlag(from);
			}else if(WeixinHelper.switchOrderPlatform(WeixinHelper.getOpenId()).equals(From.支付宝)){
				updateMember.csmAlipayFlag(from);
				user.csmAlipayFlag(from);
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
			}
			// 更新当前用户的登录信息
			WeixinHelper.updateUserLogin(user);
			
			if(!$.empty($.getString("redirect")))
				return $.Redirect($.getString("redirect"));
			
			return "success";
		} catch (Exception ex) {
			ex.printStackTrace();
			$.SetTips("系统繁忙，请稍候再试");
			return "login";
		}

	}

	/**
	 * 注销，解除微信openid和车纷享账户的关联
	 * 
	 * @return
	 */
	public String logout() {
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		try {
			CsMember csMember = WeixinHelper.getCsMember();
			if (csMember == null || $.empty(WeixinHelper.getOpenId())){
				re.setState(false);
				re.setMessage("未获取到登录账户，请稍后再试");
				return $.SendAjax(re, "utf-8");
			}

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

			re.setState(true);
			re.setMessage("操作成功");
			return $.SendAjax(re, "utf-8");
		} catch (Exception ex) {
			re.setState(false);
			re.setMessage("系统错误，请稍后再试");
			return $.SendAjax(re, "utf-8");
		}
	}

	public String loadsession() {
		String ret = new CacheSyncWXScript().getContent(null, null);
		$.SendHtml(ret, "UTF-8");
		return null;
	}
	
	public String changePsw(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		try {
			String csmMobile = $.getString("txtMobile", "");
			String strValidMobCode = $.getString("txtCode", "");
			String csmPassword = $.getString("txtPassWord", "");
			if ($.empty(csmMobile)) {
				re.setState(false);
				re.setMessage("未输入手机号码");
				return $.SendAjax(re, "utf-8");
			}
			if ($.empty(strValidMobCode)) {
				re.setState(false);
				re.setMessage("未输入短信校验码");
				return $.SendAjax(re, "utf-8");
			}
			if (!SystemHelper.isMobile(csmMobile)) {
				re.setState(false);
				re.setMessage("手机号码格式错误");
				return $.SendAjax(re, "utf-8");
			}
			if (!$.equals(strValidMobCode, (String) $.getSession(SYSTEM.REGIST_VAILD_CODE))) {
				re.setState(false);
				re.setMessage("短信校验码输入错误");
				return $.SendAjax(re, "utf-8");
			}
			CsMember existMember = csMemberService.getCsMember($.add(CsMember.F.csmMobile, csmMobile));
			if (existMember == null) {
				re.setState(false);
				re.setMessage("该手机号尚未注册");
				return $.SendAjax(re, "utf-8");
			}
			if (csmPassword.length() < 6) {
				re.setState(false);
				re.setMessage("密码设置有误，请输入6到20位有效密码");
				return $.SendAjax(re, "utf-8");
			}
			// ----------------------------------------------------------验证通过，重置密码
			csMemberService.updateCsMember$NotNull(new CsMember().csmPassword($.md5(csmPassword)).csmId(existMember.getCsmId()));
			re.setState(true);
			re.setMessage("重置密码成功");
			return $.SendAjax(re, "utf-8");
		} catch (Exception e) {
			re.setState(false);
			re.setMessage("系统错误，请稍后再试");
			return $.SendAjax(re, "utf-8");
		}
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}

}
