package com.ccclubs.action.admin.control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ccclubs.action.admin.CacheThread;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsHistoryState;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsProvid;
import com.ccclubs.model.CsState;
import com.ccclubs.model.SrvProperty;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsHistoryStateService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsOutletsService;
import com.ccclubs.service.admin.ICsProvidService;
import com.ccclubs.service.admin.ICsStateService;
import com.ccclubs.service.admin.ISrvPropertyService;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Ctrl;

/**
 * 车主车辆定位
 * @author uiu
 */
public class GpsAction {

	ICsOutletsService csOutletsService;
	ICsCarService csCarService;
	ICsStateService csStateService;
	
	public String execute(){
		
		try{
			Date start = new Date();
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//查询条件传递到转跳
			$.SetRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			
			String key = $.getString("key");
			if($.empty(key)){
				$.SetTips("未带参数key");
				return "index";
			}
			CsProvid csProvid = CsProvid.getCsProvid($.add(CsProvid.F.cspFlag, key));
			$.setRequest("csProvid", csProvid);
			if(csProvid==null){
				$.SetTips("参数key错误，请联系管理员");
				return "index";
			}
			$.setSession("gbs_key", key);
			
			List<CsCar> cars = csCarService.getCsCarList(
				$.add("cscProvid", csProvid.getCspId())
				.add("cscStatus", $.getBoolean("online",false)?1:null)
			,65535);
			$.SetRequest("cars", cars);
			$.trace("查询耗时",new Date().getTime()-start.getTime());
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "index";
	}
	
	public String soso(){
		return "soso";
	}
	
	
	public String states(){
		try{
			String key = $.getSession("gbs_key");
			if($.empty(key))return $.SendAjax("[]", "UTF-8");				
			CsProvid csProvid = CsProvid.getCsProvid($.add(CsProvid.F.cspFlag, key));
			if(csProvid==null)return $.SendAjax("[]", "UTF-8");	
			List<CsCar> cars = csCarService.getCsCarList(
					$.add("cscProvid", csProvid.getCspId())
					.add("cscStatus", $.getBoolean("online",false)?1:null)
				, 65535);
			Map<Long,Boolean> mapping=new HashMap();
			for(CsCar csCar:cars){
				mapping.put(csCar.getCscId(), true);
			}
			List<CsState> states = CsState.where().list(65535);
			List<CsState> list=new ArrayList();
			for(CsState csState:states){
				if(mapping.get(csState.getCssCar())!=null){
					list.add(csState);
				}
			}
			return $.SendAjax(list, "UTF-8");
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

}
