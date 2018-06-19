package com.ccclubs.action.weixin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsArea;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsOutletsService;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

public class ParkinglotAction {

	private ICsOutletsService	csOutletsService;
	private ICsCarService		csCarService;

	/**
	 * 网点 - 区域列表
	 * 
	 * @return
	 */
	public String execute() {
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
		List<Map> outlets = dao.executeQuery("select cso_area, count(*) as cso_count  from cs_outlets where cso_status = 1 and cso_can_order = 1 and " + CsOutlets.C.csoHost + " = " + SystemHelper.getSrvHost().getShId() + " group by cso_area order by cso_area");
		$.SetRequest("outlets", outlets);
		return "parkinglot";
	}

	/**
	 * 网点 - 某个区域的网点列表
	 * 
	 * @return
	 */
	public String itemlist() {
		Map<String, Object> params = new HashMap<String, Object>();
		Long area = $.getLong("area", -1l);
		params.put(CsOutlets.F.csoHost, SystemHelper.getSrvHost().getShId());
		params.put(CsOutlets.F.csoCanOrder, 1);
		params.put(CsOutlets.F.csoArea, area);
		// 按照能预订的网点排前面
		params.put("desc", CsOutlets.C.csoPriority + " desc, " + CsOutlets.C.csoAddTime);
		List<CsOutlets> outlets = csOutletsService.getCsOutletsList(params, -1);
		$.SetRequest("outlets", outlets);
		// 取出第一个网点，主要是显示网点的区域名字
		CsArea srvProperty = null;
		if (outlets.size() > 0) {
			srvProperty = outlets.get(0).get$csoArea();
		}
		$.SetRequest("srvProperty", srvProperty);
		return "itemlist";
	}

	/**
	 * 网点详情，车辆信息
	 * 
	 * @return
	 */
	public String itemdetail() {
		// 获得网点
		Long outletId = $.getLong("outlet", -1l);
		CsOutlets csOutlets = csOutletsService.getCsOutletsById(outletId);
		// 获得网点下的上线车辆列表
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(CsCar.F.cscOutlets, outletId);
		params.put(CsCar.F.cscStatus, 1);
		List<CsCar> csCarList = csCarService.getCsCarList(params, -1);

		String date = $.date(new Date(), "yyyy-MM-dd");
		$.SetRequest("csOutlets", csOutlets);
		$.SetRequest("csCarList", csCarList);
		$.SetRequest("date", date);
		return "itemdetail";
	}

	/**
	 * 网点地图
	 * 
	 * @return
	 */
	public String map() {
		$.SetRequest("car", $.getString("car"));
		// 获得网点
		Long outletId = $.getLong("outlet", -1l);
		CsOutlets csOutlets = csOutletsService.getCsOutletsById(outletId);
		$.SetRequest("csOutlets", csOutlets);
		return "map";
	}

	/**
	 * 网点地图 - 所有网点
	 * 
	 * @return
	 */
	public String mapall() {
		$.setRequest("point", SystemHelper.getSrvHost().getShLocation());
		// 获得网点
		List<CsOutlets> listCsList = csOutletsService.getCsOutletsList($.add(CsOutlets.F.csoCanOrder, 1), -1);
		$.SetRequest("listCsList", listCsList);
		return "mapall";
	}

	/**
	 * 网点地图 - 某个区域
	 * 
	 * @return
	 */
	public String maparea() {

		$.setRequest("point", SystemHelper.getSrvHost().getShLocation());
		Map<String, Object> params = new HashMap<String, Object>();
		int area = $.getInteger("area", -1);
		params.put(CsOutlets.F.csoArea, area);
		params.put(CsOutlets.F.csoCanOrder, 1);
		// 按照能预订的网点排前面
		params.put("desc", CsOutlets.C.csoPriority + " desc, " + CsOutlets.C.csoAddTime);
		List<CsOutlets> listCsList = csOutletsService.getCsOutletsList(params, -1);
		$.SetRequest("listCsList", listCsList);
		// 取出第一个网点，主要是显示网点的区域名字
		CsArea srvProperty = null;
		if (listCsList.size() > 0) {
			srvProperty = listCsList.get(0).get$csoArea();
		}
		$.SetRequest("srvProperty", srvProperty);

		return "maparea";
	}

	public ICsOutletsService getCsOutletsService() {
		return csOutletsService;
	}

	public void setCsOutletsService(ICsOutletsService csOutletsService) {
		this.csOutletsService = csOutletsService;
	}

	public ICsCarService getCsCarService() {
		return csCarService;
	}

	public void setCsCarService(ICsCarService csCarService) {
		this.csCarService = csCarService;
	}
}
