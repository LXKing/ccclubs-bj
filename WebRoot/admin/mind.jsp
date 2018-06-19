<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="get" uri="/get-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<title>${project.spName}功能导航</title>
</head>

<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>

<style>
<!--
.mindtab{height:36px;display:block;width:100%;overflow:hidden;}
.mindtab,.mindtab li{list-style:none;margin:0px;padding:0px;}
.mindtab li{cursor:pointer;height:36px;line-height:36px;font-size:14px;float:left;margin:0px 5px;padding:0px 10px;text-align:center;}
.mindtab a{text-decoration:none;}
.mindtab li .clicked{color:#000000;font-weight:bold;}
-->
</style>

<body scroll="no" style="overflow:hidden;">
	${get:srvlimit(project.spUrl)}
	${lz:set("limitexps",(lz:split(project.spLimitExp,",")))}
	<ul class="mindtab">
		<s:iterator  value="#request.limitexps" id="item" status="i">
		${lz:set("can",(CTRL.canExp[i.count-1] || ccclubs_login_id==0))}
		<s:if test="#request.can==true">
			${lz:set("clicked",(i.count-1)==(lz:or(index,0)))}
			<li class="${clicked?"clicked":""}">
				<a href="mind.do?id=${project.spId}&index=${i.count-1}">
				<s:if test="#request.clicked==true">
					[${item}]
				</s:if>
				<s:else>${item}</s:else>
				</a>
			</li>
		</s:if>
		</s:iterator>
	</ul>
	
	<div class="line" style="margin:0px;height:3px;"></div>
	
	<div mind="{background:true,bordWidth:1,bgColor:0xf2f2f2,txtColor:0x016b96}" style="position:absolute;left:0px;top:40px;right:0px;bottom:0px;">
		${lz:replace(lz:replace(lz:split(project.spMind,"\\[#\\]")[lz:or(index,0)],"\\$?\\{basePath\\}",basePath),"\\$?\\{proname\\}","admin/")}</div>
	
</body>

<script>
function onPaintClick(link){
	if($.trim(link)!=""){
		top.link(link);
	}
}
</script>

</html>
