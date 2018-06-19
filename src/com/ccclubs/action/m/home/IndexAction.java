package com.ccclubs.action.m.home;

import com.ccclubs.action.m.WeixinHelper;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsViolat;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsViolatService;
import com.lazy3q.web.helper.$;

public class IndexAction {
	private ICsMemberService		csMemberService;
	private ICsOrderService			csOrderService;
	
	public String execute(){
		CsMember csMember = WeixinHelper.getCsMember();
		if (!WeixinHelper.isCorrectOpenId()) {
			// 微信openid不合规范
			return WeixinHelper.gotoLogin();
		}
		if (csMember == null || $.empty(WeixinHelper.getOpenId())) {
			return WeixinHelper.gotoLogin();
		}
		// 强制刷新当前登录信息
		WeixinHelper.refreshLoginSession();
		csMember = WeixinHelper.getCsMember();
		
		ICsViolatService voilatService = $.GetSpringBean("csViolatService");
		Long weizhang = voilatService.getCsViolatCount($.add(CsViolat.F.csvMember, csMember.getCsmId()).add("definex", "(csv_type=0 or csv_type=2) and (csv_status=0 or csv_status=1)"));
		
		$.SetRequest("csMember", csMember);
		$.SetRequest("weizhang", weizhang);
		return "index";
	}
	
	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}
	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}
	public ICsOrderService getCsOrderService() {
		return csOrderService;
	}
	public void setCsOrderService(ICsOrderService csOrderService) {
		this.csOrderService = csOrderService;
	}
}
