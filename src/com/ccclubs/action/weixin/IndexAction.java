package com.ccclubs.action.weixin;

import com.lazy3q.web.helper.$;

public class IndexAction {
	/**
	 * 首页
	 * 
	 * @return
	 */
	public String execute() {
		// 合作来源 FM93，HZBANK
		// String flag = $.getString("cooperation", "");orLK0jrMvofWKa1D3V-CBrIXjzxM
//		WeixinHelper.setOpenId("2088002140198780");
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
}
