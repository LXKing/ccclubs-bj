package com.ccclubs.helper;


import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.ccclubs.model.*;
import com.ccclubs.service.admin.ISrvUserService;
import com.lazy3q.web.helper.$;

/**
* 根据ID取对象标签助手类
**/
public class GetTagHelper{
	
	/**根据ID取用户**/
	public static com.ccclubs.model.SrvUser GetSrvUser(java.lang.Long id){
		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+id);
		if(srvUser!=null)
			return srvUser;
		com.ccclubs.service.admin.ISrvUserService srvUserService = $.GetSpringBean("srvUserService");		
		srvUser = srvUserService.getSrvUserById(id);
		$.SetRequest("SrvUser$"+id, srvUser);
		return srvUser;
	}
	
	public static com.ccclubs.model.SrvProject GetSrvProject(java.lang.Long id){
		com.ccclubs.model.SrvProject srvProject = (com.ccclubs.model.SrvProject) $.GetRequest("SrvProject$"+id);
		if(srvProject!=null)
			return srvProject;
		com.ccclubs.service.admin.ISrvProjectService srvProjectService = $.GetSpringBean("srvProjectService");		
		srvProject = srvProjectService.getSrvProjectById(id);
		$.SetRequest("SrvProject$"+id, srvProject);
		return srvProject;
	}
	
	
	
}
