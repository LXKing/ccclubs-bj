<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
    <title>权限管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
</head> 
 
<body scroll="no" style="overflow:hidden;">

${get:srvlimit("admin/permissions/limit.do")}
<s:if test="#request.canView!=true">
	<div style="padding:80px 20px;width:500px;margin:0px auto;">
		<center class="message"><FONT color=red><H4>对不起，您没有权限查看当前页面</H4></FONT></center>
	</div>
</body>
</html>
<%if(true)return;%>
</s:if>

<div id="user_grid" style="left:0px; top:0px; width:30%; bottom:50px; position:absolute;overflow-y:scroll;">
	<table id="limit-table" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  	<tr>
		  		<td>名称</td>
		  		<td width="50">类型</td>
		  		<td width="70">操作</td>
		  	</tr>
		</thead>
		<tbody>
			<s:iterator value="#request.groups" id="item" status="i">
			<tr id="g${item.sgId}" tree="${item.sgParentId}">
				<td class="node">${item.sgName}</td>
		  		<td>组</td>
		  		<td>
		  			<s:if test="#request.canView==true&&#request.canAdd==true&&#request.canEdit==true&&#request.canDel==true || #session.ccclubs_login.suId==0">
		  				<a href='javascript:SetLimit(${item.sgId},0)'>设置权限</a>
		  			</s:if>
		  		</td>
		  	</tr>
		  	</s:iterator>
		  	<s:iterator value="#request.users" id="item" status="i">
			<tr id="${item.suId}" tree="g${item.suGroupId}">
				<td class="node">${item.suUsername}(${item.suRealName})</td>
		  		<td>用户</td>
		  		<td>
		  			<s:if test="#request.canView==true&&#request.canAdd==true&&#request.canEdit==true&&#request.canDel==true || #session.ccclubs_login.suId==0">
		  				<a href='javascript:SetLimit(${item.suId},1)'>设置权限</a>
		  			</s:if>
		  		</td>
		  	</tr>
		  	</s:iterator>
		</tbody>
	</table>
</div>

<div style="left:0px; height:50px; width:30%; bottom:0px; position:absolute;">
	<div style="margin:3px;">
		&nbsp;修改用户或组的权限时，将影响被修改权限的用户或组下级所有子节点的用户或组的权限。<br />
		&nbsp;当用户未设置权限时，当使用期所属组的权限。</div>
</div>

<div style=" width:70%; top:0px; bottom:0px; right:0px; position:absolute;">
	<iframe id="limit_frame" frameborder="0" allowtransparency="true" src="#" style="width:100%; height:100%;background-color: transparent; display:none;"></iframe>
</div> 

<script>
$(function(){
	$("#limit-table").treegrid({expand:true});
	$("#limit-table").find(".icon").click();
});
function SetLimit(id,type){
	$("#limit_frame").show();
	$("#limit_frame").get(0).src="limit_limit.do?actorid="+id+"&actortype="+type;
}
<s:if test="#session.tips.notNull==true">
//错误提示
$.tips("${tips.value}");	
</s:if>

$(function() {
	$.resize(function(){
		$("#user_grid").css("height",($(window).height()-50)+"px");
		$("#limit_frame").css("height",$(window).height()+"px");
		$("#user_grid").css("width",$(window).width()*3/10+"px");
		$("#limit_frame").css("width",$(window).width()*7/10+"px");
	});
});
</script>    
</body>
</html>
