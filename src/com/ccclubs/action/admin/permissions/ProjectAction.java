package com.ccclubs.action.admin.permissions;

import java.io.BufferedReader;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import com.lazy3q.web.util.Tree;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.$;

import org.apache.log4j.Logger;

import com.ccclubs.model.SrvProject;
import com.opensymphony.xwork2.ActionContext;
import com.ccclubs.service.admin.ISrvProjectService;

@SuppressWarnings({"unchecked","unused"})
public class ProjectAction
{
	ISrvProjectService srvProjectService ;
	
	SrvProject srvProject;
	
	/**
	 * 项目管理首页
	 * @return
	 */	
	public String execute()
	{	
		try{
			List<SrvProject> list=srvProjectService.getSrvProjectList();
			java.util.Collections.sort(list, new java.util.Comparator<SrvProject>(){
				public int compare(SrvProject o1,SrvProject o2){
					if(o1.getSpFamily().split(",").length<o2.getSpFamily().split(",").length)
						return 0;
					if(o1.getSpFamily().split(",").length==o2.getSpFamily().split(",").length && $.or(o1.getSpLevel(),0)>$.or(o2.getSpLevel(),0))
						return 0;
					return 1;
				}
			});
			$.SetRequest("list", list);
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "index";
	}
	
	/**
	 * 项目添加/编辑项目
	 * @return
	 */
	public String edit()
	{		
		try{	
			Map params = ActionContext.getContext().getParameters();
			Long id=$.getLong(params, "id");
			Long parent=$.getLong(params, "parent");
			if(id!=null)
			{
				srvProject=srvProjectService.getSrvProjectById(id);
				SrvProject parentSrvProject = srvProjectService.getSrvProjectById(srvProject.getSpParentId());
				$.SetRequest("parent", parentSrvProject);
			}
			else if(parent!=null)
			{
				SrvProject parentSrvProject=srvProjectService.getSrvProjectById(parent);
				$.SetRequest("parent", parentSrvProject);
			}		
			List<SrvProject> list=srvProjectService.getSrvProjectList();
			java.util.Collections.sort(list, new java.util.Comparator<SrvProject>(){
				public int compare(SrvProject o1,SrvProject o2){
					if(o1.getSpFamily().split(",").length<o2.getSpFamily().split(",").length)
						return 0;
					if(o1.getSpFamily().split(",").length==o2.getSpFamily().split(",").length && $.or(o1.getSpLevel(),0)>$.or(o2.getSpLevel(),0))
						return 0;
					return 1;
				}
			});
			$.SetRequest("list", list);
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "edit";
	}
	
	/**
	 * 保存项目
	 * @return
	 */
	public String save()
	{
		try{						
			srvProjectService.saveSrvProject(srvProject);			
			$.SetTips("保存项目成功");
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "common";
	}
	
	/**
	 * 修改项目
	 * @return
	 */
	public String update()
	{
		try{			
			srvProjectService.updateSrvProject(srvProject);
			$.SetTips("修改项目成功");
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "common";
	}
	
	/**
	 * 删除项目
	 * @return
	 */
	public String del()
	{
		try{
			Map params = ActionContext.getContext().getParameters();
			Long id=$.getLong(params, "id");
			srvProjectService.deleteSrvProjectById(id);
			$.SetTips("删除项目成功");
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "common";
	}
	
	/**
	 * 显示项目详情
	 * @return
	 */
	public String details()
	{
		try{
			Map params = ActionContext.getContext().getParameters();
			Long id=$.getLong(params,"id");
			srvProject=srvProjectService.getSrvProjectById(id);		
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
		}
		return "details";
	}
	
	/**
	 * 根据用项目名称查询项目
	 * @return
	 */
	public String query(){
		try{
			String strTerm=$.getString("value");
			String strAjax="";
			
			if(!$.empty(strTerm)){				
				List<SrvProject> list = new java.util.ArrayList();
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					list = srvProjectService.getSrvProjectList($.add("spName", strTerm.toLowerCase().replaceFirst("id:", "")), 10);
				else//按标识查询，模糊查询请带%
					list = srvProjectService.getSrvProjectList($.add("spId", "%"+strTerm+"%"), 10);
				for(SrvProject item:list){
					if(!strAjax.equals(""))
						strAjax+=",";
					strAjax+="{\"value\":\""+$.zerofill(item.getSpId().toString(), 6)+"\",\"text\":\""+$.js(item.getSpName())+"\"}";
				}
			}			
			return $.SendAjax("["+strAjax.replaceAll("\0", "")+"]", "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}		
	}
	
	/**
	 * ajax获取项目树形数据
	 * @return
	 */
	public String tree(){
		try{
			//获取要查询的数据的父节点，没有默认为0（顶层）
			Long parent = $.getLong("parent",0l);
			//根据父节点取数据列表
			List<SrvProject> list = srvProjectService.getSrvProjectList($.add("spParentId",parent),-1);
			
			/**
			* OK!取到数据拼成json串，[{value,text,more},...]
			* value:数据ID，text:数据标识,more是否还有子节点
			**/
			String strAjax="";
			for(SrvProject o:list){
				if(!strAjax.equals(""))
					strAjax+=",";
				strAjax+="{";
				strAjax+="\"value\":\""+o.getSpId()+"\"";
				strAjax+=",";
				strAjax+="\"text\":\""+$.js(o.getSpName())+"\"";
				strAjax+=",";
				strAjax+="\"more\":true";
				strAjax+="}";
			}
			//完了，$.SendAjax直接写json串到输出流
			return $.SendAjax("["+strAjax+"]", "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}
	}
	

	public ISrvProjectService getSrvProjectService()
	{
		return srvProjectService;
	}

	public void setSrvProjectService(ISrvProjectService srvProjectService)
	{
		this.srvProjectService = srvProjectService;
	}

	public SrvProject getSrvProject()
	{
		return srvProject;
	}

	public void setSrvProject(SrvProject srvProject)
	{
		this.srvProject = srvProject;
	}
	
}