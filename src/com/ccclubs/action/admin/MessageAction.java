package com.ccclubs.action.admin;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.LoginHelper;
import com.ccclubs.helper.EventHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsEventHeed;
import com.ccclubs.model.CsEventType;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsNotice;
import com.ccclubs.model.CsTodo;
import com.ccclubs.model.SrvHost;
import com.ccclubs.model.SrvLimited;
import com.ccclubs.model.SrvProject;
import com.ccclubs.model.SrvUser;
import com.ccclubs.service.admin.ISrvLimitedService;
import com.ccclubs.service.admin.ISrvProjectService;
import com.ccclubs.service.admin.ISrvUserService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;

public class MessageAction {
	
	public String execute(){		
		if(LoginHelper.getLogin().getSuHost()==null){
			$.setRequest("hosts", SrvHost.getSrvHostList($.add(SrvHost.F.shStatus, 1), -1));
			$.setRequest("defaultHost", SrvHost.get(SYSTEM.getDefaultHost()));
		}
		return "box";
	}	
	
	/**
	 * 获取我的通知
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String notice(){
		try{
			Map params = $.add(CsNotice.F.csnReceiver, LoginHelper.getLoginId());
			params.put(CsNotice.F.csnStatus+"Not", "not");
			params.put(CsNotice.F.csnStatus, 2);
			Page<CsNotice> notices = CsNotice.page($.getInteger("page",0), 5, params);
			$.setRequest("notices", notices);
			$.setRequest("noticeCount", CsNotice.count($.add(CsNotice.F.csnReceiver, LoginHelper.getLoginId()).add(CsNotice.F.csnStatus, 1)));
		}catch(Exception ex){
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
		}
		return "notice";
	}
	/**
	 * 获取待办事列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String todo(){
		try{
			Map params = $.add(CsTodo.F.cstUser, LoginHelper.getLoginId());
			params.put(CsTodo.F.cstStatus+"Not", "not");
			params.put(CsTodo.F.cstStatus, 2);
			Page<CsTodo> todos = CsTodo.page($.getInteger("page",0), 5, params);
			$.setRequest("todos", todos);
			$.setRequest("todoCount", CsTodo.count($.add(CsTodo.F.cstUser, LoginHelper.getLoginId()).add(CsTodo.F.cstStatus, 1)));
		}catch(Exception ex){
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
		}
		return "todo";
	}
	/**
	 * 关注事件
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String heed(){
		try{
			SrvUser updateSrvUser = SrvUser.get((Long) $.getSession("ccclubs_login_id"));
			if($.equals($.getString("edittype"),"save")){
				if(updateSrvUser!=null && updateSrvUser.getSuId().longValue()>0){
					updateSrvUser.setSuMobile($.getString("srvUser.suMobile"));
					updateSrvUser.setSuEmail($.getString("srvUser.suEmail"));
					ISrvUserService srvUserService = $.getBean("srvUserService");
					srvUserService.updateSrvUser(updateSrvUser);
				}
				
				List<CsEventHeed> heeds = CsEventHeed.list($.add(CsEventHeed.F.csehHeedUser, LoginHelper.getLoginId()), -1);
				Map<Long,CsEventHeed> mapping = new HashMap();
				for(CsEventHeed csEventHeed:heeds){
					mapping.put(csEventHeed.getCsehEventType(), csEventHeed);
				}
				String[] types = $.getArray("types",new String[]{});
				for(String type:types){
					Long lType = Long.parseLong(type);
					if(mapping.get(lType)==null){
						 new CsEventHeed(
							lType//事件类型 [非空]
						 	,LoginHelper.getLoginId()//关注人 [非空]
						 	,$.getArrayToString("type_"+lType,",")//处理方式 [非空]
						 	,new Date()//修改时间 [非空]
						 	,new Date()//添加时间 [非空]
						 	,(short)1//状态 [非空]
						 ).save();
					}else{
						String strTypes=$.getArrayToString("type_"+lType,",");
						if($.empty(strTypes))
							mapping.get(lType).delete();
						else
							mapping.get(lType).csehHeedType(strTypes).update();					
					}
				}			
				$.SetTips("关注设置成功！");
			}
			$.setRequest("srvUser", updateSrvUser);
			List<CsEventType> eventTypes = new ArrayList();
			if((Long)$.getSession("ccclubs_login_id")==0){
				eventTypes = CsEventType.list($.add(CsEventType.F.csetStatus, 1), -1);
			}else{
				ISrvLimitedService srvLimitedService = $.getBean("srvLimitedService");
				ISrvProjectService srvProjectService = $.getBean("srvProjectService");
				List<SrvLimited> limits=srvLimitedService.getUserLimits((SrvUser) $.getSession("ccclubs_login"),true);//获取用户权限
				String strIds="";
				for(SrvLimited o:limits){
					if(!strIds.equals(""))
						strIds+=",";
					strIds+=o.getSlProjectId();
				}
				List<SrvProject> projects = srvProjectService.getSrvProjectListByIds(strIds);
				String proIds="";
				for(SrvProject p:projects){
					if(!proIds.equals(""))
						proIds+=",";
					proIds+=p.getSpId();
				}
				String definex = " ("+CsEventType.C.csetProject+" is null";
				if(!$.empty(proIds)){
					definex+=" or "+CsEventType.C.csetProject+" in ("+proIds+")";
				}
				definex+=")";
				eventTypes = CsEventType.list(
					$.add(CsEventType.F.csetStatus, 1)
					.add("definex",definex)
				, -1);
			}
			List<CsEventHeed> heeds = CsEventHeed.list($.add(CsEventHeed.F.csehHeedUser, LoginHelper.getLoginId()), -1);
			$.setRequest("types", eventTypes);
			$.setRequest("heeds", heeds);
		}catch(Exception ex){
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
		}
		return "heed";
	}
	
	
	/**
	 * 远程控制脚本已发送
	 * @return
	 */
	public String run(){
		try{
			if(LoginHelper.getLoginId().longValue()!=0l)
				return $.SendAjax($.add("success", false).add("message", "无权操作此项"), "UTF-8");
			String strRun = $.trim($.getString("run"));
			String className = strRun.substring(0, strRun.lastIndexOf("."));
			String methodName = strRun.substring(strRun.lastIndexOf(".")+1).replace("()", "").replace(";", "");
			Class<?> cls = null;
			Method method = null;
			try{cls=Class.forName(className);}catch(Exception ex){}
			if(cls==null)
				return $.SendAjax($.add("success", false).add("message", "找不到类"+className), "UTF-8");
			try{method = cls.getMethod(methodName);}catch(Exception ex){}
			if(method==null)
				return $.SendAjax($.add("success", false).add("message", "找不到类"+className+"的函数"+methodName), "UTF-8");			
			final Method finalMethod = method;
			new Thread(){
				@Override
				public void run() {					
					try {
						finalMethod.invoke(null);
					} catch (Exception ex) {						
						ex.printStackTrace();
					}			
					super.run();
				}
			}.start();
			
			return $.SendAjax($.add("success", false).add("message", "远程执行已运行"), "UTF-8");
		}catch(Exception ex){
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return $.SendAjax($.add("success", false).add("message", SYSTEM.ERROR_TIPS), "UTF-8");
		}
	}
	
	/**
	 * 数据库解锁
	 * @return
	 */
	public String kill(){
		try{
			return $.SendAjax($.add("success", false).add("message", SystemHelper.killLocks(LoginHelper.getLoginId().longValue()==0l)), "UTF-8");
		}catch(Exception ex){
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return $.SendAjax($.add("success", false).add("message", SYSTEM.ERROR_TIPS), "UTF-8");
		}
	}
	
	/**
	 * 显示审核会员消息框
	 * @return
	 */
	public String member(){
		Long id = $.getLong("id");
		CsMember csMember = CsMember.get(id);
		$.SetRequest("csMember",csMember);
		Date updateTime = csMember.get$csmInfo().getCsmiUpdateTime();
		String timeDesc = SystemHelper.getTimeDesc(updateTime);
		$.SetRequest("timeDesc", timeDesc);
		return "member";
	}
	
	public String tips(){
		return "tips";
	}
}
