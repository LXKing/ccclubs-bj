package com.ccclubs.action.m;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.SrvHost;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;

public class IndexAction {
	/**
	 * 首页
	 * 
	 * @return
	 */
	public String execute() {
		// 合作来源 FM93，HZBANK
//		String flag = $.getString("cooperation", "");
//		WeixinHelper.setOpenId("2088002140798780");
//		ICsMemberService memberservice = $.GetSpringBean("csMemberService");
//		CsMember csMember = memberservice.getCsMemberById(10007133l);
		String flag_wx = $.getString("flag", "");
		String flag = $.getString("radioid", "");
		if ($.empty(flag)) {
			flag = $.getString("cooperation", "");
		}
		
		if (!$.empty(flag)) {
			$.setSession("cooperation", flag.toUpperCase());
		}
		if (!$.empty(flag_wx)) {
			$.setSession("cooperation", flag_wx.toUpperCase());
		}
		
		return "index";
	}
	
	/**
	 * 首页-获取初始化城市
	 */
	public String getSrvHost(){
		SrvHost srvHost = SystemHelper.getSrvHost();
		return Lazy.SendAjax(srvHost, SYSTEM.UTF8);
	}
}
