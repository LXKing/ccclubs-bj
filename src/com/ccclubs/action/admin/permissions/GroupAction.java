package com.ccclubs.action.admin.permissions;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.$;

import org.apache.log4j.Logger;

import com.ccclubs.model.SrvGroup;
import com.ccclubs.model.SrvUser;
import com.opensymphony.xwork2.ActionContext;
import com.ccclubs.service.admin.ISrvGroupService;

@SuppressWarnings({"unchecked","unused"})
public class GroupAction
{
	ISrvGroupService srvGroupService ;
	
	SrvGroup srvGroup;
	
	/**
	 * 组管理首页
	 * @return
	 */	
	public String execute()
	{
		try{
			List list=srvGroupService.getSrvGroupList((SrvUser) $.getSession("ccclubs_login"));
			$.SetRequest("list", list);
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "index";
	}
	
	/**
	 * 组添加/编辑组
	 * @return
	 */
	public String edit()
	{
		try{
			Map params = ActionContext.getContext().getParameters();
			Long id=$.getLong(params,"id");
			if(id!=null)
				srvGroup=srvGroupService.getSrvGroupById(id);						
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "edit";
	}
	
	/**
	 * 保存组
	 * @return
	 */
	public String save()
	{
		try{
			srvGroup.setSgParentId((Long) $.getSession("ccclubs_login_id"));
			srvGroupService.saveSrvGroup(srvGroup);			
			$.SetTips("保存组成功");
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "common";
	}

	/**
	 * 修改组
	 * @return
	 */
	public String update()
	{
		try{
			SrvGroup oldSrvGroup = srvGroupService.getSrvGroupById(srvGroup.getSgId());
			if(oldSrvGroup.getSgParentId().longValue()!=(Long) $.getSession("ccclubs_login_id"))
			{
				$.SetTips("您没有权限修改该组");
				return "common";
			}
			srvGroup.setSgParentId((Long) $.getSession("ccclubs_login_id"));
			srvGroupService.updateSrvGroup(srvGroup);
			$.SetTips("修改组成功");
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "common";
	}
	
	/**
	 * 删除组
	 * @return
	 */
	public String del()
	{
		try{
			Map params = ActionContext.getContext().getParameters();
			Long id=$.getLong(params,"id");
			SrvGroup oldSrvGroup = srvGroupService.getSrvGroupById(id);
			if(oldSrvGroup.getSgParentId().longValue()!=(Long) $.getSession("ccclubs_login_id"))
			{
				$.SetTips("您没有权限删除该组");
				return "common";
			}
			srvGroupService.deleteSrvGroupById(id);
			$.SetTips("删除组成功");
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "common";
	}	
	
	/**
	 * 显示组详情
	 * @return
	 */
	public String details()
	{
		try{
			Map params = ActionContext.getContext().getParameters();
			Long id=$.getLong(params,"id");
			srvGroup=srvGroupService.getSrvGroupById(id);		
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "details";
	}
	
	/**
	 * 根据用用户组名称查询用户组
	 * @return
	 */
	public String query(){
		try{
			String strTerm=$.getString("value");
			String strAjax="";
			
			if(!$.empty(strTerm)){				
				List<SrvGroup> list = new java.util.ArrayList();
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					list = srvGroupService.getSrvGroupList($.add("sgName", strTerm.toLowerCase().replaceFirst("id:", "")), 10);
				else//按标识查询，模糊查询请带%
					list = srvGroupService.getSrvGroupList($.add("sgId", "%"+strTerm+"%"), 10);
				for(SrvGroup item:list){
					if(!strAjax.equals(""))
						strAjax+=",";
					strAjax+="{\"value\":\""+$.zerofill(item.getSgId().toString(), 6)+"\",\"text\":\""+$.js(item.getSgName())+"\"}";
				}
			}
			return $.SendAjax("["+strAjax.replaceAll("\0", "")+"]", "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}		
	}

	public ISrvGroupService getSrvGroupService()
	{
		return srvGroupService;
	}

	public void setSrvGroupService(ISrvGroupService srvGroupService)
	{
		this.srvGroupService = srvGroupService;
	}

	public SrvGroup getSrvGroup()
	{
		return srvGroup;
	}

	public void setSrvGroup(SrvGroup srvGroup)
	{
		this.srvGroup = srvGroup;
	}
	
}