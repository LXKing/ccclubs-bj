package com.ccclubs.action.weixin.scripts;

import java.util.List;

import com.lazy3q.web.helper.$;
import com.ccclubs.action.weixin.IWXScript;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsWxContent;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.common.ICommonMoneyService;

public class MoneyWXScript implements IWXScript {

	public String getContent(String openId, String content) {		
		ICsMemberService csMemberService=$.getBean("csMemberService");
		ICommonMoneyService commonMoneyService = $.getBean("commonMoneyService");
		CsMember csMember = csMemberService.getCsMember($.add("definex", "(csm_weixin_flag = '" + openId + "'" + " or csm_alipay_flag = '" + openId + "' )"));
		if(csMember==null)
			return "对不起，您未绑定车纷享帐号。<a href='"+$.config("weixin.domain")+"weixin/index.html'>\"点此绑定帐号\"</a>";
		return "您的帐户当前可用余额为"+commonMoneyService.getUsableAmount(csMember.getCsmId())+"元";
	}

	public List<CsWxContent> getContents(String openId, String content) {
		return null;
	}

	public ReturnType getReturnType() {
		return ReturnType.TEXT;
	}

}
