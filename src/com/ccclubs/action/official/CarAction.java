package com.ccclubs.action.official;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ccclubs.action.unit.UnitLoginHelper;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.ActionHelper;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsRemote;
import com.ccclubs.model.CsUnitInfo;
import com.ccclubs.model.Restriction;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsRemoteService;
import com.ccclubs.service.common.ICommonOrderService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;

public class CarAction {
	public ICsCarService csCarService;
	public CsCar csCar;
	
	
	public String execute(){
		try{
			$.setRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//查询条件传递到转跳
			Map<String,Object> params = ActionHelper.getQueryFormParams(CsCar.class);//把表单参数转成查询需要的参数格式
			CsUnitInfo unitInfo = UnitLoginHelper.getLogin().get$csuuUnitInfo();
			
			String outletsDefSQL = "";
			if(unitInfo.getCsuiOutlets()!=null){
//				params.put(CsCar.F.cscOutlets, unitInfo.getCsuiOutlets());
				outletsDefSQL = " and csc_outlets in ("+unitInfo.getCsuiOutlets()+")";
			}else{
				params.put(CsCar.F.cscHost, unitInfo.getCsuiHost());
			}
			params.put("definex", "(csc_status=1 or csc_status=2 )"+outletsDefSQL+"");
			Page<CsCar> page = csCarService.getCsCarPage($.getInteger("page",0), 10, params);
			$.SetRequest("cars", page.getResult());
			$.SetRequest("page", page);
			
			
			java.util.Calendar calender = java.util.Calendar.getInstance();
			calender.set(java.util.Calendar.HOUR_OF_DAY, 0);
			calender.set(java.util.Calendar.MINUTE, 0);
			calender.set(java.util.Calendar.SECOND, 0);
			Date begin = $.getDate("begin",calender.getTime());
			$.setRequest("begin", begin);
			Date end = $.getDate("end");
			if(end==null || end.before(begin)){
				if(end!=null)
					$.SetTips("结束时间不能在开始时间之前,自动设置为开始时间向后5天");
				calender.setTime(begin);
				calender.add(java.util.Calendar.DATE, 5);
				end = calender.getTime();
			}
			if((end.getTime()-begin.getTime())>SYSTEM.MONTH){
				calender.setTime(begin);
				calender.add(java.util.Calendar.DATE, 30);
				end = calender.getTime();
				$.SetTips("时间跨度不能超过一个月,已经自动退化为一个月");
			}
			$.setRequest("end", end);
			int day = (int)((end.getTime()-begin.getTime())/SYSTEM.DAY)+1;
			$.setRequest("day",day);
			
			ICommonOrderService commonOrderService = $.GetSpringBean("commonOrderService");
			List<CsCar> result = page.getResult();
			String ids = "0";
			for(CsCar csCar:result){
				if(!$.empty(ids))
					ids+=",";
				ids+=csCar.getCscId();
			}
			List<CsOrder> allorders = CsOrder.list($.add("csoFinishTimeStart",begin).add("definex", "cso_car in ("+ids+") and cso_status!=3 and cso_status!=7"), -1);
			Map<Long,List> mapping = new HashMap();
			for(CsOrder csOrder:allorders){
				Long carid = csOrder.getCsoCar();
				List list = mapping.get(carid);
				if(list==null){
					list = new ArrayList();
					mapping.put(carid, list);
				}
				list.add(csOrder);
			}
			for(CsCar csCar:result){
				List<CsOrder> orders = mapping.get(csCar.getCscId());
				List<Restriction> restrs = commonOrderService.getRestrictions(csCar.getCscId(),day);
				csCar.setValues("orders", orders);
				csCar.setValues("restrs", restrs);
			}
			
			List<Map> dates=new java.util.ArrayList();
			for(int i=0;i<day;i++){
				dates.add(
					$.add("start", new Date(begin.getTime()+(i*SYSTEM.DAY))).add("finish", new Date(begin.getTime()+(i*SYSTEM.DAY)+SYSTEM.DAY))
				);
			}
			$.setRequest("dates", dates);
			
			$.setRequest("howlong",end.getTime()-begin.getTime()+SYSTEM.DAY);
			
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙，请稍候再试");
		}
		return "index";
	}
	
	/**
	 * 远程控制
	 * 
	 * @return
	 */
	public String remote(){
		try{
		    Map<String,Object> params = ActionHelper.getQueryFormParams(CsCar.class);
			Long id = $.getLong("id");
			Short type = $.getShort("type");
			CsUnitInfo unitInfo = UnitLoginHelper.getLogin().get$csuuUnitInfo();
			if(type==null || type>1)
				return $.SendAjax($.add("message", "远程只能操作开门或者关门"), "UTF-8");
			params.put(CsCar.F.cscId, $.getString("id"));
			if(unitInfo.getCsuiOutlets()!=null) {
			    params.put("definex"," csc_outlets in ("+unitInfo.getCsuiOutlets()+")");
			}else {
			    return $.SendAjax($.add("message", "远程操作失败，此车辆网点异常"), "UTF-8");
            }
			
			
			//CsCar csCar = csCarService.getCsCarById(id);
			CsCar csCar = csCarService.getCsCar(params);
			if (csCar==null) {
			    return $.SendAjax($.add("message", "远程操作失败，无此车辆或无权限"), "UTF-8");
            }
			ICsRemoteService csRemoteService = $.getBean("csRemoteService");
			
			CsRemote csRemote = new CsRemote();
			csRemote.setCsrNumber(csCar.getCscNumber());
			csRemote.setCsrCar(csCar.getCscId());
			csRemote.setCsrEditor(0l);
			csRemote.setCsrAddTime(new Date());
			csRemote.setCsrState((short)2);
			csRemote.setCsrStatus((short)0);			
			csRemote.setCsrType((short)type);
			csRemote.setCsrRemark(UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiName()+"]["+UnitLoginHelper.getLogin().getCsuuId()+":"+UnitLoginHelper.getLogin().getCsuuUsername()+"]执行微信远程操作");	
			csRemote.setCsrHost(UnitLoginHelper.getLogin().getCsuuHost());
			csRemote=csRemoteService.saveCsRemote(csRemote);
			com.ccclubs.service.common.impl.RemoteService.dealRemoteCommend(csRemote);
			String[] typeDesc = new String[]{"远程开门","远程关门"};				
			return $.SendAjax($.add("message", "远程操作["+typeDesc[type]+"]成功"), "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax($.add("message", "系统繁忙，请稍候再试"), "UTF-8");
		}
	}
	


	public ICsCarService getCsCarService() {
		return csCarService;
	}


	public void setCsCarService(ICsCarService csCarService) {
		this.csCarService = csCarService;
	}


	public CsCar getCsCar() {
		return csCar;
	}


	public void setCsCar(CsCar csCar) {
		this.csCar = csCar;
	}
	
	
	
}
