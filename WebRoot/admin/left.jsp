<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<title>${lz:config("project_name")}</title> 
</head>

<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
<!-- 用户自定义js(懒加载) -->
<script type="text/javascript" src="${basePath}admin/js/define.js"></script>
<body scroll="no" style="overflow:hidden;">
<table id="navbar" width="100%" height="32" border="0" cellspacing="0" cellpadding="0">
  <tr height="32">
    <td>
		<table id="navigate" width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		  <tr>
			<td width="120" class="state-focus" align="center" style="height:28px;">
				菜单导航
			</td>
			<td width="32" class="c">&nbsp;</td>
			<td width="*" class="state-title" style="height:28px;">&nbsp;</td>
		  </tr>
	    </table>
	</td>
  </tr>
</table>
<table class="tabcnt" flag="默认菜单" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr height="*">
    <td id="accordion" class="accordion">
		<s:iterator  value="#request.projects" id="project1" status="i">
		  <s:if test="#project1.spParentId==0">
		  	${lz:set("canMind",(lz:size(project1.spMind)>32) && (ccclubs_login.suId==0 || limits[project1.spId]>63))}
		  	<a class="item" group="${project1.spGroup}" <s:if test="#request.canMind==true">href="javascript:parent.href('${basePath}admin/mind.do?id=${project1.spId}','系统导航')"</s:if>>${project1.spName}</a>
			<div id="nav_${project1.spId}" class="panel" group="${project1.spGroup}"></div>			
		  </s:if>
		</s:iterator>
		<s:if test="#session.ccclubs_login.suId==0">
    		<div class="item state-active" group="系统配置">超级权限</div>			
			<div id="nav_super" class="panel" group="系统配置"></div>
		</s:if>
	</td>
  </tr>
</table>
<script>
$(function(){
	var groups = {};
	var bHaveGroup = false;
	$(".accordion .item").each(function(){
		if($.trim($(this).attr("group"))!=""){
			groups[$.trim($(this).attr("group"))]=true;
			bHaveGroup=true;
		}
	});
	if(bHaveGroup){
		$("<div class=\"navitab\" id=\"navitab\"><ul><li class=\"clicked\" ref=\"默认菜单\">默<br/>认<br/>菜<br/>单</li></ul></div><div class=\"navicnt\"></div>").insertAfter("#navbar");
		var defaultTable = $(".tabcnt").appendTo(".navicnt");
		for(var o in groups){
			var li=$("<li></li>").attr("ref",o).html(o.replace(/(.)/g,"$1<br/>")).appendTo("#navitab ul");
			var table=$("<table class=\"tabcnt\" flag=\""+o+"\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr height=\"*\"><td id=\"accordion\" class=\"accordion\"></td></tr></table>");
			table.appendTo(".navicnt").hide();
			defaultTable.find("[group='"+o+"']").appendTo(table.find(".accordion"));
		}
		$("<div class=\"shdw\"></div>").prependTo("#navigate .state-focus");
	}
	$(".navitab li").click(function(){
		$(".clicked").removeClass("clicked");
		$(this).addClass("clicked");
		$(".navicnt table").hide();
		$(".navicnt table[flag='"+$(this).attr("ref")+"']").show();
	});
});
</script>


<script>

var onResize=function(){
	var hItem=33;
	$(".accordion").each(function(){
		var iCount=$(this).find(".item").size();
		$(this).find(".panel").css("height",$(window).height()-hItem*iCount-33);
	});
}

/*实现抽屉盒功能*/
$(function(){	

	<s:if test="#session.ccclubs_login.suId==0">
	//如果是超级用户（登录用户的ID为0），则添加几个超级权限导航菜单，当数据库清空时，也能登录到系统
	$("#nav_super").tree({text:"项目管理",href:"javascript:parent.href('${basePath}admin/permissions/project.do','项目管理','images/icons/20088256422681277805.png')",expand:false,icon:"images/icons/20088256422681277805.png"});
	$("#nav_super").tree({text:"城市管理",href:"javascript:parent.href('${basePath}admin/permissions/host.do','城市管理','images/icons/20088256422681277807.png')",expand:false,icon:"images/icons/20088256422681277807.png"});
	$("#nav_super").tree({text:"用户管理",href:"javascript:parent.href('${basePath}admin/permissions/user.do','用户管理','images/icons/20088256427828778017.png')",expand:false,icon:"images/icons/20088256427828778017.png"});
	$("#nav_super").tree({text:"分组管理",href:"javascript:parent.href('${basePath}admin/permissions/group.do','分组管理','images/icons/Folder user.png')",expand:false,icon:"images/icons/Folder user.png"});
	$("#nav_super").tree({text:"权限管理",href:"javascript:parent.href('${basePath}admin/permissions/limit.do','权限管理','images/icons/2008825642250778046.png')",expand:false,icon:"images/icons/2008825642250778046.png"});
	</s:if>
	
	var projects=[];
	//取顶级项目
	<s:iterator  value="#request.projects" id="project" status="i">
	  	<s:if test="#project.spParentId==0">
  	projects[${project.spId}]=projects["${project.spName}"]=$("#nav_${project.spId}");
	  	</s:if>
	</s:iterator>
	//创建用户可操作的项目菜单	
	<s:iterator  value="#request.projects" id="project" status="i">
		${lz:set("canView",ccclubs_login.suId==0 || (lz:bit(limits[project.spId],"&",1)==1))}
		<s:if test="#project.spParentId!=0 && #project.spStatus==1 && #request.canView==true">
	if(projects[${project.spParentId}])
		projects[${project.spId}]=projects["${project.spParentId}"]["${project.spName}"]=projects[${project.spParentId}].tree({text:"${project.spName}",href:"javascript:parent.href('${lz:indexOf(project.spUrl,'#')==0?"":basePath}${project.spUrl}${lz:indexOf(project.spUrl,'?') > -1 ? "&":"?"}limitsid=${lz:limitid(project.spUrl,limits[project.spId])}','${project.spName}','${empty project.spLogo?"images/icons/2008825642250778016.png":project.spLogo}')",expand:true,url:"admin/${project.spUrl}",icon:"${empty project.spLogo?"images/icons/2008825642250778016.png":project.spLogo}"});
		</s:if>
	</s:iterator>
	
	//根据路径描述获取父节点
	var getProject=function(sPath){		
		var array = sPath.split(",");
		var lastProject=null;
		for(var o in array){
			var name = array[o];
			if(!lastProject){
				if(!(lastProject=projects[name]))
					lastProject=projects[name]=$("<div class='item'>"+array[o]+"</div><div class='panel'></div>").appendTo(".accordion").eq(1);
			}else{
				if(!lastProject[name]){
					lastProject = lastProject[name]=lastProject.tree({text:array[o],href:"#",expand:true,url:"#"});
				}else
					lastProject=lastProject[name];
			}				
		}
		return lastProject;
	}
	
	var tempProject = null;	
	//创建插件可菜单
	<s:iterator value="#request.plugins" id="plugin" status="i">
		${lz:set("links",lz:request(lz:join(plugin.name,"_links")))}
		<s:iterator value="#request.links" id="link" status="i">
		getProject("${link.path}")["${link.name}"]=getProject("${link.path}").tree({text:"${link.name}",href:"javascript:parent.href('${basePath}${link.url}','${link.name}')",expand:true,url:"${link.url}"});
		</s:iterator>
		${lz:set("links",null)}
	</s:iterator>	
		
	
	$(".accordion .item").click(function(){
		var accordion=$(this).parents(".accordion");
		accordion.find(".panel").hide();
		$(this).next().show();
		accordion.find(".item").attr("class","item state-header");
		$(this).attr("class","item state-active");  
	});	
	onResize();	
	$(window).resize(onResize);
	$(document).resize(onResize);
	
	$(".accordion").each(function(){
		$(this).find(".item:eq(0)").click();
	});	
});

function link(flag,params){
	var link=$(".accordion a[href*='"+flag+"']").attr("href");
	eval(link);
}

</script>

</body>
</html>
