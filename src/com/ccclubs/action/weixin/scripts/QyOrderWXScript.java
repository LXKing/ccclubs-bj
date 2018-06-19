package com.ccclubs.action.weixin.scripts;

import java.util.List;

import com.lazy3q.web.helper.$;
import com.ccclubs.action.weixin.IWXScript;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsWxContent;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsOrderService;

public class QyOrderWXScript implements IWXScript {

	public String getContent(String openId, String content) {		
		ICsMemberService csMemberService=$.getBean("csMemberService");
		ICsOrderService csOrderService = $.getBean("csOrderService");
		CsMember csMember = csMemberService.getCsMember($.add("definex", "(csm_weixin_flag = '" + openId + "'" + " or csm_alipay_flag = '" + openId + "' )"));
		if(csMember==null)
			return "对不起，您未绑定车纷享帐号。<a href='"+$.config("weixin.domain")+"weixin/login.html'>\"点此绑定帐号\"</a>";
		CsOrder csOrder = csOrderService.getCsOrder($.add("csoUseMember", csMember.getCsmId()));
		if(csOrder==null)
			return "对不起，查询不到相关的订单信息";		
		//浙A·
		String strTips = "您最新订单"+csOrder.getCsoId()+"\n";	
		if(csOrder.getCsoStatus().shortValue()==0){
			strTips = "您当前订单"+csOrder.getCsoId()+"\n";
			strTips+="状态:已预定\n";
			strTips+="车牌号:"+csOrder.get$csoCar().getCscCarNo()+"\n";
			strTips+="授权码:"+csOrder.getCsoCode()+"\n";
		}else if(csOrder.getCsoStatus().shortValue()==1){	
			strTips = "您当前订单"+csOrder.getCsoId()+"\n";
			strTips+="状态:正在执行\n";
			strTips+="车牌号:"+csOrder.get$csoCar().getCscCarNo()+"\n";
		}else if(csOrder.getCsoStatus().shortValue()==2||csOrder.getCsoStatus().shortValue()==5){		
			strTips+="状态:正在结算\n";
			if(csOrder.getCsoRetTime()!=null)
				strTips+="还车时间:"+$.date(csOrder.getCsoRetTime(), "MM月dd日HH点mm分")+"\n";
			strTips+="车牌号:"+csOrder.get$csoCar().getCscCarNo()+"\n";
		}else if(csOrder.getCsoStatus().shortValue()==3){		
			strTips+="状态:已取消\n";
			strTips+="车牌号:"+csOrder.get$csoCar().getCscCarNo()+"\n";
			strTips+="结算金额:"+csOrder.getCsoPayReal()+"\n";
		}else if(csOrder.getCsoStatus().shortValue()==4){		
			strTips+="状态:已完成\n";
			strTips+="车牌号:"+csOrder.get$csoCar().getCscCarNo()+"\n";
			strTips+="结算金额:"+csOrder.getCsoPayReal()+"\n";
		}else if(csOrder.getCsoStatus().shortValue()==7||csOrder.getCsoStatus().shortValue()==6){		
			strTips+="状态:已撤销\n";
			strTips+="车牌号:"+csOrder.get$csoCar().getCscCarNo()+"\n";
		}
		strTips+="<a href='"+$.config("weixin.domain")+"weixin/account_order.html'>\"了解订单详情\"</a>";
		
		return strTips;		
	}

	public List<CsWxContent> getContents(String openId, String content) {
		return null;
	}

	public ReturnType getReturnType() {
		return ReturnType.TEXT;
	}

}
