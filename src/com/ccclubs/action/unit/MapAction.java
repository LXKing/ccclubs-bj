package com.ccclubs.action.unit;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ccclubs.action.admin.CacheThread;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.LoginHelper;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsElecHistory;
import com.ccclubs.model.CsGasStation;
import com.ccclubs.model.CsHistoryState;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsPowerPile;
import com.ccclubs.model.CsRemote;
import com.ccclubs.model.CsState;
import com.ccclubs.model.SrvHost;
import com.ccclubs.model.SrvProperty;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsElecHistoryService;
import com.ccclubs.service.admin.ICsHistoryStateService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsOutletsService;
import com.ccclubs.service.admin.ICsStateService;
import com.ccclubs.service.admin.ISrvPropertyService;
import com.ccclubs.service.admin.impl.CsElecHistoryService;
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
			
			$.setRequest("defaultHost", SrvHost.get(UnitLoginHelper.getLogin().getCsuuHost()));
			
			List<CsOutlets> outlets = null;
			
			if(UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiOutlets()!=null){
				outlets = csOutletsService.getCsOutletsList(
						$.add("csoStatus", 1)
						.add("desc", CsOutlets.C.csoCarS)
						.add(CsOutlets.F.csoId,UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiOutlets()),
					-1);
				List<CsCar> cars = csCarService.getCsCarList($.add(CsCar.F.cscOutlets, UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiOutlets()), 4096);
				
				$.SetRequest("outlets", outlets);
				$.SetRequest("cars", cars);
			}else{
				outlets = csOutletsService.getCsOutletsList(
						$.add("csoStatus", 1)
						.add("desc", CsOutlets.C.csoCarS)
						.add(CsOutlets.F.csoHost,UnitLoginHelper.getLogin().getCsuuHost()),
					-1);
				List<CsCar> cars = csCarService.getCsCarList($.add(CsCar.F.cscHost, UnitLoginHelper.getLogin().getCsuuHost()), 4096);
				
				$.SetRequest("outlets", outlets);
				$.SetRequest("cars", cars);
			}
			
			
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
			$.setRequest("defaultHost", SrvHost.get(UnitLoginHelper.getLogin().getCsuuHost()));
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "soso";
	}
	
	
	public String states(){
		try{
			String outlets = UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiOutlets();
			if(outlets!=null){
				List<CsState> list=CsState.where()
				.add("join", "LEFT JOIN (select * from cs_car where csc_outlets in ("+outlets+") ) a on css_number=a.csc_number")
				.add("definex", "a.csc_id is not null").list(-1);	
				return $.SendAjax(list, "UTF-8");
			}else{
				List<CsState> list=CsState.where().cssHost(UnitLoginHelper.getLogin().getCsuuHost()).list(-1);			
				return $.SendAjax(list, "UTF-8");
			}
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}	
	}
	public String info(){
		try{
			CsState csState = csStateService.getCsState($.add("cssNumber", $.getString("number")));
			$.SetRequest("csState", csState);
			CsCar csCar=csCarService.getCsCar($.add("cscNumber", $.getString("number")));
			$.SetRequest("csCar", csCar);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);			
		}	
		return "info";
	}
	
	public String remote(){
		try{
			Long id = $.getLong("id");
			Short type = $.getShort("type");
			if(type==null)
				return $.SendAjax($.add("success", false).add("message", "请选择远程控制类型"), "UTF-8");
			CsCar csCar = CsCar.get(id);
			if(csCar==null)
				return $.SendAjax($.add("success", false).add("message", "系统参数错误，请联系技术管理员"), "UTF-8");
			if(UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiOutlets().indexOf(csCar.getCscOutlets()+"")==-1)
				return $.SendAjax($.add("success", false).add("message", "对不起，您没有权限操作此功能"), "UTF-8");
			CsRemote csRemote = new CsRemote();
			csRemote.setCsrNumber(csCar.getCscNumber());
			csRemote.setCsrCar(csCar.getCscId());
			csRemote.setCsrHost(csCar.getCscHost());
			csRemote.setCsrEditor(0l);
			csRemote.setCsrAddTime(new Date());
			csRemote.setCsrState((short)2);
			csRemote.setCsrStatus((short)0);			
			csRemote.setCsrType((short)type);
			csRemote.setCsrRemark($.getString("remark"));
			csRemote=csRemote.save();
			
			com.ccclubs.service.common.impl.RemoteService.dealRemoteCommend(csRemote);

			String[] typeDesc = new String[]{"远程开门","远程关门","允许启动"};			
			return $.SendAjax($.add("success", true).add("message",csCar.getCscNumber()+typeDesc[type]+"执行成功"), "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax($.add("success", false).add("message", "系统繁忙，请联系管理员"), "UTF-8");
		}
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
