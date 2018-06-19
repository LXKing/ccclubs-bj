package com.ccclubs.action.weixin.scripts;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lazy3q.web.helper.$;
import com.ccclubs.action.weixin.IWXScript;
import com.ccclubs.action.weixin.WeixinHelper;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsWxContent;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.common.From;
import com.ccclubs.service.common.ICommonDisposeService;

public class ReOrderWXScript implements IWXScript {

	public String getContent(String openId, String content) {		
		ICsMemberService csMemberService=$.getBean("csMemberService");
		ICommonDisposeService commonDisposeService = $.getBean("commonDisposeService");
		ICsOrderService csOrderService = $.getBean("csOrderService");
		CsMember csMember = csMemberService.getCsMember($.add("definex", "(csm_weixin_flag = '" + openId + "'" + " or csm_alipay_flag = '" + openId + "' )"));
		if(csMember==null)
			return "对不起，您未绑定车纷享帐号。<a href='"+$.config("weixin.domain")+"weixin/login.html'>\"点此绑定帐号\"</a>";
		CsOrder csOrder = csOrderService.getCsOrder($.add("csoUseMember", csMember.getCsmId()).add("definex", "cso_status=0 and cso_status=1"));
		if(csOrder==null)
			return "对不起，您当前没有可续订的订单";
		Date oldFinishTime = csOrder.getCsoFinishTime();
		
		Integer minute = 0;
		Matcher match = Pattern.compile("/([\\d]*)").matcher(content);		
		if(match.find())
			minute = Integer.valueOf(match.group(1));
		else
			return "未输入要续订的分钟数";
		
		Date newFinishTime = new Date(csOrder.getCsoFinishTime().getTime()+(minute*60*1000));
		
		try{
			commonDisposeService.executeReOrder(csOrder.getCsoId(), newFinishTime , null,WeixinHelper.switchOrderPlatform(openId),null);
		}catch(com.ccclubs.exception.MessageException oe){
			return oe.getMessage();	
		}
		
		return "你的订单["+csOrder.getCsoId()+"]已续订成功，时间由"+$.date(oldFinishTime, "MM月dd日HH时mm分")+"变更为"+$.date(newFinishTime, "MM月dd日HH时mm分");
	}

	public List<CsWxContent> getContents(String openId, String content) {
		return null;
	}

	public ReturnType getReturnType() {
		return ReturnType.TEXT;
	}

}
