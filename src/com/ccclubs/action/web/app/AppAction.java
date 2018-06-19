package com.ccclubs.action.web.app;
import com.ccclubs.model.CsUpdate;
import com.ccclubs.service.admin.ICsUpdateService;
import com.lazy3q.web.helper.$;

public class AppAction {
	/**
	 * app下载页面
	 * 
	 * @return
	 */
	public String execute() {
		ICsUpdateService csUpdateService=$.getBean("csUpdateService");
		CsUpdate csUpdateAndroid = csUpdateService.getCsUpdate($.add("csuType", 0));
		$.SetRequest("csUpdateAndroid", csUpdateAndroid);
		CsUpdate csUpdateIos= csUpdateService.getCsUpdate($.add("csuType", 1));
		$.SetRequest("csUpdateIos", csUpdateIos);
		return "index";
	}
}
