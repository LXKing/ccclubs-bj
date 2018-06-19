package com.ccclubs.action.weixin;

import com.ccclubs.action.web.BaseOrderInfo;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.SrvHost;
import com.ccclubs.service.admin.ISrvHostService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;

public class AjaxAction {
	/**
	 * 获得当前地域信息
	 * 
	 * @return
	 */
	public String getHost() {
		return $.SendAjax(SystemHelper.getSrvHost(), "UTF-8");
	}

	/**
	 * 根据地域id主动设置地域信息
	 */
	public String setHost() {
		try {
			Long hostId = $.getLong("host");
			ISrvHostService srvHostService = Lazy.GetSpringBean("srvHostService");
			SrvHost srvHost = srvHostService.getSrvHost($.add(SrvHost.F.shId, hostId));
			if (srvHost != null) {
				SystemHelper.setSrvHost(srvHost);
				// 重置预订信息里的，区域，网点，车型信息
				if (SystemHelper.getBaseOrderInf() != null) {
					BaseOrderInfo baseOrderInfo = SystemHelper.getBaseOrderInf();
					baseOrderInfo.setCsoArea(-1l);
					baseOrderInfo.setCsoOutlets(-1l);
					baseOrderInfo.setCsoModel(-1l);
					SystemHelper.setBaseOrderInfo(baseOrderInfo);
				}
				// BaseOrderInfo baseOrderInfo =
				return $.SendHtml("true", "UTF-8");
			} else {
				return $.SendHtml("false", "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return $.SendHtml("false", "UTF-8");
		}

	}
}
