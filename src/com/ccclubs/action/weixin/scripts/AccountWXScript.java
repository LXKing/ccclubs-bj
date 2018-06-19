package com.ccclubs.action.weixin.scripts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lazy3q.web.helper.$;
import com.ccclubs.action.weixin.IWXScript;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsWxContent;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.common.ICommonMoneyService;

/**
 * 获取帐户信息
 * 
 * @author Administrator
 * 
 */
public class AccountWXScript implements IWXScript {

	public String getContent(String openId, String content) {
		ICsMemberService csMemberService = $.getBean("csMemberService");
		ICommonMoneyService commonMoneyService = $.getBean("commonMoneyService");
		CsMember csMember = csMemberService.getCsMember($.add("definex", "(csm_weixin_flag = '" + openId + "'" + " or csm_alipay_flag = '" + openId + "' )"));
		if (csMember == null)
			return " 对不起，您未绑定车纷享帐号。<a href='" + $.config("weixin.domain") +  "weixin/login.html'>\" 点此绑定帐号\"</a>";
		return "您的帐户当前免费小时为" + commonMoneyService.getCanUseFreeHour(csMember.getCsmId(), new Date()) + "小时";
	}

	public List<CsWxContent> getContents(String openId, String content) {
		List<CsWxContent> contentList = new ArrayList<CsWxContent>();
		CsWxContent csWxContent = new CsWxContent();
		csWxContent.setCswcTitle("我的帐户信息"); 
		ICsMemberService csMemberService = $.getBean("csMemberService");
		CsMember csMember = csMemberService.getCsMember($.add("definex", "( binary csm_weixin_flag like '%" + openId + "%'" + " or binary csm_alipay_flag like '%" + openId + "%' )"));
		if (csMember == null) {
			csWxContent.setCswcContnet("\n对不起，你还没有绑定车纷享帐号。\n\n如果你已经有车纷享帐号了，点全文马上绑定。\n\n点击“会员注册”加入车纷享！\n\n若有疑问或需要帮助，点击“帮助信息”。");
			csWxContent.setCswcLink($.config("weixin.domain") + "weixin/login.html");
		} else {
			csWxContent.setCswcContnet(getDescription(csMember));
			csWxContent.setCswcLink($.config("weixin.domain") + "weixin/account.html");
		}
		contentList.add(csWxContent);
		return contentList;
	}

	public ReturnType getReturnType() {
		return ReturnType.NEWS;
	}

	public String getDescription(CsMember csMember) {
		ICommonMoneyService commonMoneyService = $.getBean("commonMoneyService");
		String description = "\n尊敬的 {code00} ：\n\n充值帐户：{code01} 元\n现金券    ：{code02} 元\n积分        ：{code03} 积分\n免费小时：{code04} 小时\n\n★当前绑定帐户为 {code05} ，如需解除绑定，点击全文。★\n\n若有疑问或需要帮助，点击“帮助信息”。";
		if ($.empty(csMember.getCsmName())) {
			description = description.replace("{code00}", SystemHelper.mobileConvert2Star(csMember.getCsmMobile()));
		} else {
			description = description.replace("{code00}", csMember.getCsmName());
		}
		return description.replace("{code01}", $.digit(csMember.getCsmMoney(), 2)).replace("{code05}", SystemHelper.mobileConvert2Star(csMember.getCsmMobile())).replace("{code02}", $.digit(csMember.getCsmCoupon(), 2))
				.replace("{code03}", String.valueOf(SystemHelper.toInt(csMember.getCsmIntegral()))).replace("{code04}",
						String.valueOf(commonMoneyService.getCanUseFreeHour(csMember.getCsmId(), new Date())));
	}
}
