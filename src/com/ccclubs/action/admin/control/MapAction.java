package com.ccclubs.action.admin.control;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ccclubs.action.admin.CacheThread;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsElecHistory;
import com.ccclubs.model.CsGasStation;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsPowerPile;
import com.ccclubs.model.CsState;
import com.ccclubs.model.SrvHost;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsElecHistoryService;
import com.ccclubs.service.admin.ICsHistoryStateService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsOutletsService;
import com.ccclubs.service.admin.ICsStateService;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Ctrl;

public class MapAction {

	ICsOutletsService csOutletsService;
	ICsCarService csCarService;
	ICsStateService csStateService;	
	ICsHistoryStateService csHistoryStateService;
	ICsElecHistoryService csElecHistoryService;
	ICsOrderService csOrderService;
	
	public String execute(){
		
		try{
			Date start = new Date();
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//查询条件传递到转跳
			$.SetRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			
			$.setRequest("defaultHost", SrvHost.get(SYSTEM.getDefaultHost()));
			
			Long count = csOutletsService.getCsOutletsCount($.add("csoStatus", 1).add("desc", CsOutlets.C.csoCarS).add(CsOutlets.F.csoHost,SYSTEM.getDefaultHost() ));
			
			List<CsOutlets> outlets = csOutletsService.getCsOutletsList(
					$.add("csoStatus", 1).add("desc", CsOutlets.C.csoCarS).add(CsOutlets.F.csoHost,SYSTEM.getDefaultHost() ),
					count.intValue());
			List<CsCar> cars = csCarService.getCsCarList($.add(CsCar.F.cscHost, SYSTEM.getDefaultHost()).add("definex", "csc_status>0"), 4096);
			List<CsGasStation> gases = CsGasStation.getCsGasStationList($.add(CsGasStation.F.csgsStatus, 1).add(CsGasStation.F.csgsHost, SYSTEM.getDefaultHost()), 1024);
			List<CsPowerPile> piles = CsPowerPile.getCsPowerPileList($.add(CsPowerPile.F.csppHost, SYSTEM.getDefaultHost()), 1024);
			
			$.SetRequest("outlets", outlets);
			$.SetRequest("cars", cars);
			$.SetRequest("gases", gases);
			$.SetRequest("piles", piles);
			
			$.trace("查询耗时",new Date().getTime()-start.getTime());
			
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "index";
	}
	
	public String soso(){
		try{
			$.setRequest("defaultHost", SrvHost.get(SYSTEM.getDefaultHost()));
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "soso";
	}
	
	public String gaode(){
		try{
			$.setRequest("defaultHost", SrvHost.get(SYSTEM.getDefaultHost()));
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "gaode";
	}
	
	public String states(){
		try{
			String json = CacheThread.getCacheThread(SYSTEM.getDefaultHost()).getSatateJsonData();	
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain");
			response.setCharacterEncoding("utf-8"); 
//		    OutputStream out = response.getOutputStream();
//		    out.write(json.getBytes("utf-8"));
//		    out.flush();
//		    out.close();
			
			PrintWriter printWriter = response.getWriter();
			printWriter.write(json);
			printWriter.flush();
			printWriter.close();
			return null;
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}	
	}
	
	public String info(){
		try{			
			CsState csState = CsState.where().cssNumber($.getString("number")).cssHost(SYSTEM.getDefaultHost()).get();
			$.SetRequest("csState", csState);
			CsCar csCar = CsCar.where().cscNumber($.getString("number")).add("definex", "csc_status>0").cscHost(SYSTEM.getDefaultHost()).get();
			$.SetRequest("csCar", csCar);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);			
		}	
		return "info";
	}
	
	/**
	 * 电桩信息
	 * @return
	 */
	public String powerInfo(){
		try{
			Long outletsId = $.getLong("outletsId");
			if(outletsId !=null){
				List<CsPowerPile> piles = CsPowerPile.getCsPowerPileList($.add(CsPowerPile.F.csppOutlets, outletsId), 1024);
				if(piles!=null){
					for(CsPowerPile pile : piles){
						CsElecHistory csehHistory = csElecHistoryService.getCsElecHistory($.add(CsElecHistory.F.csehDid, pile.getCsppNo())
								.add(CsElecHistory.F.csehPowerPile, pile.getCsppId())
								.add("desc", "cseh_add_time")
						);
						pile.setValues("csehHistory", csehHistory);
					}
					$.setRequest("piles", piles);
				}
				
				CsOutlets outlets = csOutletsService.getCsOutletsById(outletsId);
				$.setRequest("outlets", outlets);
			}
			
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);			
		}	
		return "power_info";
	}
	
	/**
	 * 车辆历史轨迹
	 * @return
	 */
	public String history(){
		try{			
			String number = $.getString("number");
			
			CsCar csCar = csCarService.getCsCar($.add("cscNumber", number).add("definex", "csc_status>0"));
			$.SetRequest("csCar", csCar);
						
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -30);
			List<CsOrder> orders = csOrderService.getCsOrderList(
				$.add(CsOrder.F.csoCar, csCar.getCscId())
				.add("csoAddTimeStart", calendar.getTime())
				.add(CsOrder.F.csoHost, SYSTEM.getDefaultHost())
			,-1);
			$.SetRequest("orders", orders);
			
			Long orderid = $.getLong("order",0l);			
			$.SetRequest("orderid", orderid);
			if(!orderid.equals(0l)){
				Lazy3qDaoSupport dao = $.getDao("ccclubs_system_history");
				List<Map> historys = dao.executeQuery("select cshs_add_time,cshs_latitude,cshs_longitude,cshs_oil, cshs_speed , cshs_mileage, cshs_power from cs_history_state_"+number.toLowerCase()+" where cshs_order=?  order by cshs_id asc", orderid);
				$.SetRequest("historys", historys);
			}
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);			
		}	
		return "history";
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
	public ICsStateService getCsStateService() {
		return csStateService;
	}


	public void setCsStateService(ICsStateService csStateService) {
		this.csStateService = csStateService;
	}

	public ICsHistoryStateService getCsHistoryStateService() {
		return csHistoryStateService;
	}

	public void setCsHistoryStateService(
			ICsHistoryStateService csHistoryStateService) {
		this.csHistoryStateService = csHistoryStateService;
	}

	public ICsOrderService getCsOrderService() {
		return csOrderService;
	}

	public void setCsOrderService(ICsOrderService csOrderService) {
		this.csOrderService = csOrderService;
	}

	public ICsElecHistoryService getCsElecHistoryService() {
		return csElecHistoryService;
	}

	public void setCsElecHistoryService(ICsElecHistoryService csElecHistoryService) {
		this.csElecHistoryService = csElecHistoryService;
	}
	
}
