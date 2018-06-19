package com.ccclubs.action.web;

import java.util.Date;
import com.ccclubs.config.Constant;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.RSAUtil;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsMember;
import com.ccclubs.service.admin.ICsMemberService;
import com.lazy3q.web.helper.$;

public class ForgotAction {
	ICsMemberService csMemberService;

	/**
	 * 忘记密码 页面
	 * 
	 * @return
	 */
	public String execute() {
		// 为了让页面能够调用短信接口，在session放入最后发短信标识(当前时间减去一分钟)
		$.setSession(SYSTEM.REGIST_VAILD_LAST, new Date(new Date().getTime() - SYSTEM.MINUTE));
		return "forgot";
	}

	public String step2() {
		// 验证当前手机号
		if ($.getSession(Constant.VERIFY_MOBILE) == null) {
			return "forgot-step2";
		}
		return "step2";
	}

	public String success() {
		// 重置密码成功
		return "success";
	}

	/**
	 * 验证手机号，验证码，校验码
	 * 
	 * @return
	 */
	public String verify() {
		try {
			String csmMobile = $.getString("txtMobile", "");
			String strValidCode = $.getString("txtCode", "");
			String strValidMobCode = $.getString("txtMobCode", "");
			if ($.empty(csmMobile)) {
				// $.SetTips("未输入手机号码，不能修改密码");
				return $.SendHtml("101", "UTF-8");
			}
			if ($.empty(strValidCode)) {
				// $.SetTips("未输入验证码，不能修改密码");
				return $.SendHtml("103", "UTF-8");
			}
			if ($.empty(strValidMobCode)) {
				// $.SetTips("未输入短信校验码，不能修改密码");
				return $.SendHtml("104", "UTF-8");
			}
			if (!SystemHelper.isMobile(csmMobile)) {
				// $.SetTips("手机号码格式错误");
				return $.SendHtml("105", "UTF-8");
			}
			if (!$.equals(strValidCode.toLowerCase(), ((String) $.getSession(Constant.VERIFY_CODE)).toLowerCase())) {
				// $.SetTips("注册码输入错误");
				return $.SendHtml("109", "UTF-8");
			}
			if (!$.equals(strValidMobCode, (String) $.getSession(SYSTEM.REGIST_VAILD_CODE))) {
				// $.SetTips("短信校验码输入错误");
				return $.SendHtml("110", "UTF-8");
			}
			CsMember existMember = csMemberService.getCsMember($.add(CsMember.F.csmMobile, csmMobile));
			if (existMember == null)
				// $.SetTips("手机号不存在，不能修改密码");
				return $.SendHtml("111", "UTF-8");
			// ----------------------------------------------------------验证通过，进入到修改密码页面
			$.setSession(Constant.VERIFY_MOBILE, csmMobile);
			// 清空验证码session
			$.setSession(Constant.VERIFY_CODE, null);
			// $.SetTips("-验证通过，进入到修改密码页面");
			return $.SendHtml("100", "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			// $.SetTips("系统错误,请稍候再试");
			return $.SendHtml("9999", "UTF-8");
		}
	}

	/**
	 * 重置密码
	 * 
	 * @return
	 */
	public String resetPwd() {
		try {
			// 验证当前手机号
			if ($.getSession(Constant.VERIFY_MOBILE) == null) {
				// $.SetTips("当前手机号不存在，转到第一步");
				return $.SendHtml("-1", "UTF-8");
			}
			String csmPassword = RSAUtil.decrypt($.getString("txtPassWord", ""));
			String rePwd = RSAUtil.decrypt($.getString("txtRePassWord", ""));
			if ($.empty(csmPassword)) {
				// $.SetTips("未输入密码，不能注册");
				return $.SendHtml("102", "UTF-8");
			}
			if (csmPassword.length() < 6) {
				// $.SetTips("密码设置有误，请输入6到20位有效密码");
				return $.SendHtml("106", "UTF-8");
			}
			if (!$.equals(csmPassword, rePwd)) {
				// $.SetTips("两次密码输入不一致");
				return $.SendHtml("107", "UTF-8");
			}
			// $.md5(csmPassword)
			// 当前重置密码用户
			CsMember csMember = csMemberService.getCsMember($.add(CsMember.F.csmMobile, $.getSession(Constant.VERIFY_MOBILE)));
			if (csMember == null)
				// $.SetTips("手机号不存在，不能修改密码");
				return $.SendHtml("111", "UTF-8");
			// ----------------------------------------------------------验证通过，重置密码
			csMemberService.updateCsMember$NotNull(new CsMember().csmPassword($.md5(csmPassword)).csmId(csMember.getCsmId()));
			// $.SetTips("重置密码成功，进入到成功页面");
			// 清空当前手机号session
			$.setSession(Constant.VERIFY_MOBILE, null);

			return $.SendHtml("100", "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			// $.SetTips("系统错误,请稍候再试");
			return $.SendHtml("9999", "UTF-8");
		}
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}
}
