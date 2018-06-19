package com.ccclubs.action.admin.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.model.CsArea;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.SrvHost;
import com.ccclubs.service.admin.ICsOutletsService;
import com.ccclubs.service.admin.ISrvHostService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Ctrl;

@SuppressWarnings({ "unused", "rawtypes" })
public class NewMapAction {

	ICsOutletsService csOutletsService;

	public String execute() {
		try {
			final Ctrl CTRL = $.setRequest("CTRL", $.CTRL($.getString("ctrl")));// 可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			List<SrvHost> hostList = srvHostService.getSrvHostList(
					new HashMap<String, Object>(), -1);

			List<Map> map = csOutletsService
					.getCsOutletsEvalList(
							CsArea.C.csaName + " , " + CsOutlets.C.csoArea
									+ " , " + CsOutlets.C.csoLatitude + " , "
									+ CsOutlets.C.csoLongitude + ",count(1)",
							$.add(CsOutlets.C.csoHost,
									SrvHost.get(SYSTEM.getDefaultHost())
											.getShId())
									.add("definex",
											"1 =1 group by "
													+ CsOutlets.C.csoArea)
									.add("join",
											" LEFT JOIN cs_area on cs_area.csa_id = cso_area"));
			$.setRequest("hostList", hostList);
			$.setRequest("defaultHost", SrvHost.get(SYSTEM.getDefaultHost()));
			$.setRequest("areaList", map);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(), e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "index";
	}

	public String getOutlesList() {

		try {
			final Ctrl CTRL = $.setRequest("CTRL", $.CTRL($.getString("ctrl")));// 可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			List<CsOutlets> outletList = csOutletsService.getCsOutletsList(
					$.add(CsOutlets.C.csoHost,
							SrvHost.get(SYSTEM.getDefaultHost()).getShId())
							.add(CsOutlets.C.csoArea, $.getString("areaId")),
					null);
			return $.SendAjax(outletList, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(), e);
			return $.SendAjax("[]", "UTF-8");
		}
	}

	ISrvHostService srvHostService;

	public ISrvHostService getSrvHostService() {
		return srvHostService;
	}

	public void setSrvHostService(ISrvHostService srvHostService) {
		this.srvHostService = srvHostService;
	}

	public ICsOutletsService getCsOutletsService() {
		return csOutletsService;
	}

	public void setCsOutletsService(ICsOutletsService csOutletsService) {
		this.csOutletsService = csOutletsService;
	}
}
