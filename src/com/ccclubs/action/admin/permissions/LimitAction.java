package com.ccclubs.action.admin.permissions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.$;


import com.ccclubs.model.SrvGroup;
import com.ccclubs.model.SrvLimited;
import com.ccclubs.model.SrvProject;
import com.ccclubs.model.SrvUser;
import com.opensymphony.xwork2.ActionContext;
import com.ccclubs.service.admin.ISrvGroupService;
import com.ccclubs.service.admin.ISrvLimitedService;
import com.ccclubs.service.admin.ISrvProjectService;
import com.ccclubs.service.admin.ISrvUserService;

@SuppressWarnings({"unchecked","unused"})
public class LimitAction
{
	ISrvLimitedService srvLimitedService;
	ISrvUserService srvUserService;
	ISrvGroupService srvGroupService;
	ISrvProjectService srvProjectService;
	
	/**
	 * 权限管理首页
	 * @return
	 */	
	public String execute()
	{
		try{
			$.SetRequest("groups", srvGroupService.getSrvUserTreeChild((SrvUser) $.getSession("ccclubs_login")));
			$.SetRequest("users", srvUserService.getSrvUserOnwerTree((SrvUser) $.getSession("ccclubs_login")));
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "index";
	}
	/**
	 * 加载权限
	 * @return
	 */
	public String limit()
	{
		try{
			Map params = ActionContext.getContext().getParameters();
			Integer actortype=$.getInteger(params, "actortype");			
			Long actorid=$.getLong(params, "actorid");			
			$.SetRequest("actortype", actortype);
			$.SetRequest("actorid", actorid);
			
			
			//获取该对象父级秘支持的权限
			SrvGroup srvGroup=null;
			SrvUser srvUser=null;			
			List<SrvGroup> _groups=null;
			List<SrvUser> _users=null;			
			List<SrvLimited> _limits=null;//权限设置者的权限
			List _projects=null;//权限设置者可见的项目
			List<SrvLimited> _limiteds=null;//权限视被设置者的权限	
			
			
			SrvUser parent;
			if(actortype==1)
			{
				srvUser=srvUserService.getSrvUserById(actorid);
				parent=srvUserService.getSrvUserById(srvUser.getSuParentId());
				
			}
			else
			{
				srvGroup=srvGroupService.getSrvGroupById(actorid);
				parent=srvUserService.getSrvUserById(srvGroup.getSgParentId());
			}
			if(parent!=null)
				_limits=srvLimitedService.getUserLimits(parent,true);
			else
				$.setRequest("EXTEND_BY_SUPER", true);
			$.SetRequest("limits", _limits);
			
			//获取该对象所支持的项目列表	
			if(parent!=null)
			{
				String strIds="";
				for(SrvLimited o:_limits)
				{
					if(!strIds.equals(""))
						strIds+=",";
					strIds+=o.getSlProjectId();
				}
				_projects=srvProjectService.getSrvProjectListByIds(strIds);	
			}
			else//如果父类为空，说明直属超级用户
			{
				_projects=srvProjectService.getSrvProjectList();
			}
			java.util.Collections.sort(_projects, new java.util.Comparator<SrvProject>(){
				public int compare(SrvProject o1,SrvProject o2){
					if(o1.getSpFamily().split(",").length<o2.getSpFamily().split(",").length)
						return 0;
					if(o1.getSpFamily().split(",").length==o2.getSpFamily().split(",").length && $.or(o1.getSpLevel(),0)>$.or(o2.getSpLevel(),0))
						return 0;
					return 1;
				}
			});
			$.SetRequest("projects", _projects);
			
			//获取该对象的实际权限
			if(actortype==1)
			{
				_limiteds=srvLimitedService.getUserLimits(srvUser,false);
				if(_limiteds.isEmpty())
					$.SetRequest("USER_GROUP_LIMIT", true);
				$.SetRequest("target", srvUser);
			}else{
				_limiteds=srvLimitedService.getGroupLimits(srvGroup);
				$.SetRequest("target", srvGroup);
			}
			$.SetRequest("limiteds", _limiteds);
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "limit";
	}
	
	
	/**
	 * ajax获取权限
	 * @return
	 */
	public String ajax(){
		try{
			Map params = ActionContext.getContext().getParameters();
			Long actorid = $.getLong("actorid");
			Integer actortype=actorid>0?1:0;
			actorid = Math.abs(actorid);
			//获取该对象父级秘支持的权限
			SrvGroup srvGroup=null;
			SrvUser srvUser=null;
			List<SrvLimited> _limiteds=null;//权限视被设置者的权限
			SrvUser parent;
			if(actortype==1){
				srvUser=srvUserService.getSrvUserById(actorid);
				parent=srvUserService.getSrvUserById(srvUser.getSuParentId());
			}else{
				srvGroup=srvGroupService.getSrvGroupById(actorid);
				parent=srvUserService.getSrvUserById(srvGroup.getSgParentId());
			}
			//获取该对象的实际权限
			if(actortype==1){
				_limiteds=srvLimitedService.getUserLimits(srvUser,false);
			}else{
				_limiteds=srvLimitedService.getGroupLimits(srvGroup);
			}
			return $.SendAjax($.add("success", true).add("limits", _limiteds), $.UTF8);
		}catch(Exception ex){
			ex.printStackTrace();
			return $.SendAjax($.add("success", false).add("message","系统错误,请稍候再试"), $.UTF8);
		}
	}
	
	
	
	/**
	 * 修改权限
	 * @return
	 */
	public String update()
	{
		try{
			Map params = ActionContext.getContext().getParameters();
			Integer actortype=$.getInteger(params, "actortype");			
			Long actorid=$.getLong(params, "actorid");			
			$.SetRequest("actortype", actortype);
			$.SetRequest("actorid", actorid);
			String strLimitList=$.getString(params, "limit_list");
			if(strLimitList==null || strLimitList.equals(""))
			{
				$.SetTips("修改权限失败:无项目可设置。");	
				return "common";
			}
			String[] aLister=strLimitList.split(",");
			if(aLister==null || aLister.length==0)
			{
				$.SetTips("修改权限失败:无项目可设置。");	
				return "common";
			}
			List<SrvLimited> limits=new ArrayList();
			for(int i=0;i<aLister.length;i++)
			{
				SrvLimited limit=new SrvLimited();
				limit.setSlActorId(actorid);
				limit.setSlActorType(actortype);
				
				String strItemer=aLister[i];
				String[] aItemer=strItemer.split("\\|");
				
				Long lLimitId=Long.parseLong(aItemer[0]);
				Long lProjectId=Long.parseLong(aItemer[1]);
				Integer lLimitValue=Integer.parseInt(aItemer[2]);
				
				if(lLimitId.longValue()!=0)
					limit.setSlId(lLimitId);
				limit.setSlProjectId(lProjectId);
				limit.setSlLimit(lLimitValue);
				
				limits.add(limit);				
			}
			
			srvLimitedService.updateLimited(actorid,actortype,limits);
			$.SetTips("修改权限成功");
			
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");		
		}
		
		return "common";
	}
	
	public ISrvLimitedService getSrvLimitedService()
	{
		return srvLimitedService;
	}
	public void setSrvLimitedService(ISrvLimitedService srvLimitedService)
	{
		this.srvLimitedService = srvLimitedService;
	}
	public ISrvUserService getSrvUserService()
	{
		return srvUserService;
	}
	public void setSrvUserService(ISrvUserService srvUserService)
	{
		this.srvUserService = srvUserService;
	}
	public ISrvGroupService getSrvGroupService()
	{
		return srvGroupService;
	}
	public void setSrvGroupService(ISrvGroupService srvGroupService)
	{
		this.srvGroupService = srvGroupService;
	}
	public ISrvProjectService getSrvProjectService()
	{
		return srvProjectService;
	}
	public void setSrvProjectService(ISrvProjectService srvProjectService)
	{
		this.srvProjectService = srvProjectService;
	}
	
}