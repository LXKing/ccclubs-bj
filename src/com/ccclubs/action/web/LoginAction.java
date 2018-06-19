package com.ccclubs.action.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ccclubs.config.Constant;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.LoggerHelper;
import com.ccclubs.helper.RSAUtil;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsMember;
import com.ccclubs.service.admin.ICsMemberService;
import com.lazy3q.web.helper.$;
import com.opensymphony.xwork2.ActionContext;

public class LoginAction {
	/**
	 * 来路页面
	 */
	private String from;

	ICsMemberService csMemberService;

	/**
	 * 登录默认入口
	 */
	public String execute() {
		if (from == null) {
			HttpServletRequest request = ServletActionContext.getRequest();
			String strDirect = request.getHeader("Referer");// 源地址
			if (strDirect != null)
				from = $.hex(strDirect);
		}
		return "login";
	}

	/**
	 * 统一的异步登录
	 * 
	 * @return
	 */
	public String ajaxlogin() {
		try {
			Map<String, Object> params = ActionContext.getContext().getParameters();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			
			String strUsername = $.getString("username", "");
			String strPass = $.getString("password", "");
			
			String strRequestSyscode=$.getString("syscode");
			String strSessionSyscode = (String) session.getAttribute("syscode");
			if (strRequestSyscode == null || !strRequestSyscode.equals(strSessionSyscode)){
				return $.SendHtml("101", "UTF-8");
			}
			if (SystemHelper.isNullOrEmpty(strUsername)) {
				// 你还没有输入帐号
				return $.SendHtml("102", "UTF-8");
			}
			if (SystemHelper.isNullOrEmpty(strPass)) {
				// 你还没有输入密码！
				return $.SendHtml("103", "UTF-8");
			}
			String strPassword = RSAUtil.decrypt(strPass);
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
				return $.SendHtml("107", "UTF-8");
			}

			if (!$.md5(strPassword).equals(user.getCsmPassword().toUpperCase())) {
				// 你输入的密码不正确，请重新输入。
				return $.SendHtml("106", "UTF-8");
			}
			// 记录 Cookie
			if ($.getBoolean("remMe", true) == true) {
				$.setCookie("/", "username", strUsername);
			}

			String csmLastIp = user.getCsmLastIp();
			java.util.Date csmLastTime = user.getCsmLastTime();
			// 更新最后登录IP，最后登录时间，登录次数 +1
			if(user.getCsmLoginS()==null)
				csMemberService.updateCsMember$NotNull(new CsMember().csmLastIp(request.getRemoteAddr()).csmLastTime(new java.util.Date()).csmLoginS( 1).csmId(user.getCsmId()));
			else {
				csMemberService.updateCsMember$NotNull(new CsMember().csmLastIp(request.getRemoteAddr()).csmLastTime(new java.util.Date()).csmLoginS(user.getCsmLoginS() + 1).csmId(user.getCsmId()));
			}
			
			user.setCsmLastIp(csmLastIp);
			user.setCsmLastTime(csmLastTime);
			System.out.println("#######" + $.getSession("super_user") + "#######");
			$.setSession(SYSTEM.ccclubs_web_login, user);
			$.setSession(SYSTEM.ccclubs_web_login_id, user.getCsmId());
			System.out.println("#######" + $.getSession("super_user") + "#######");

			// 登录成功
			return $.SendHtml("100", "UTF-8");
			// }
		} catch (Exception ex) {
			ex.printStackTrace();
			// 系统错误,请稍候再试。
			return $.SendHtml("9999", "UTF-8");
		}
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 */
	public String logout() {
		try {
			// 清除当前登录信息
			$.setSession(SYSTEM.ccclubs_web_login, null);
			$.setSession(SYSTEM.ccclubs_web_login_id, null);
			// 清除当前定单信息
			$.setSession(Constant.BASE_ORDER_INFO, null);
		} catch (Exception ex) {
			ex.printStackTrace();
			$.SetTips("系统错误[" + this.getClass().getName().hashCode() + "],请稍候再试");
		}
		return "loginout";
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}
