package com.ccclubs.action.weixin.scripts;

import java.util.Date;
import java.util.List;

import com.lazy3q.web.helper.$;
import com.ccclubs.action.weixin.IWXScript;
import com.ccclubs.action.weixin.WeixinHelper;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsWxContent;
import com.ccclubs.model.SrvUser;
import com.ccclubs.model.SrvUserExp;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ISrvUserExpService;
import com.ccclubs.service.admin.ISrvUserService;
import com.ccclubs.service.common.ICommonMoneyService;

/**
 * 外勤用户绑定微信openid
 * 
 * @author qs小刚
 * 
 */
public class QrSignWXScript implements IWXScript {

	public String getContent(String openId, String content) {
		try {
			// 扩展用户信息
			ISrvUserExpService srvUserExpService = $.getBean("srvUserExpService");
			SrvUserExp srvUserExp = srvUserExpService.getSrvUserExp($.add(SrvUserExp.F.sueWxOpenid, openId));
			if (srvUserExp != null) {
				return "您已绑定外勤帐号。";
			}

			// content 是以 'qr:' 开头
			if (!content.startsWith("qr:") || content.length() < 5 || !content.contains(",")) {
				return "格式错误。qr:用户名,密码";
			}

			String[] accountArr = content.split(",");

			SrvUser user = null;
			ISrvUserService srvUserService = $.getBean("srvUserService");
			// 帐号方式登录
			user = srvUserService.getSrvUserByUsername(accountArr[0]);
			if (user == null) {
				return "管理帐号用户名不对。";
			}

			if (user.getSuPassword() == accountArr[1] || user.getSuPassword() == $.md5(accountArr[1])) {
				SrvUserExp newSrvUserExp = new SrvUserExp();
				newSrvUserExp.setSueWxOpenid(openId);
				newSrvUserExp.setSueUser(user.getSuId());
				newSrvUserExp.save();

				return "绑定成功！";
			} else {
				return "管理帐号用户名或密码不对。";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "系统错误";
		}
	}

	public List<CsWxContent> getContents(String openId, String content) {
		return null;
	}

	public ReturnType getReturnType() {
		return ReturnType.TEXT;
	}

}
