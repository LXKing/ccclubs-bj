package com.ccclubs.action.admin.csm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.model.CsComplain;
import com.ccclubs.model.SrvProperty;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ISrvPropertyService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.node.Export;
import com.lazy3q.web.util.Page;
import com.opensymphony.xwork2.ActionContext;

public class MemberClassifyAction {	
	public String execute(){		
		try{
			java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\.([\\S\\s]+)\\.").matcher($.getServletRequest().getServletPath());
			String alias=(String) (matcher.find()?$.setRequest("alias",matcher.group(1)):null);//别名入口		
			ISrvPropertyService srvPropertyService = $.getBean("srvPropertyService");
			SrvProperty root = srvPropertyService.getSrvProperty($.add(SrvProperty.F.spFlag, "MEMBER_TAG"));
			if(root!=null)
				$.setRequest("propertys", srvPropertyService.getSrvPropertyList($.add(SrvProperty.F.spParent,root.getSpId()),-1));
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "index";
	}
}
