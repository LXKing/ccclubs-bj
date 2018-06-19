<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
    <title>组管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
</head>  
<body>

${get:srvlimit("admin/permissions/group.do")}
<s:if test="#request.canView!=true">
	<div style="padding:80px 20px;width:500px;margin:0px auto;">
		<center class="message"><FONT color=red><H4>对不起，您没有权限查看当前页面</H4></FONT></center>
	</div>
</body>
</html>
<%if(true)return;%>
</s:if>

<div class="title">用户组管理 - 组列表</div>

<div class="line"></div>

<div class="content">
	<table class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
			<td width="50">编号</td>
			<td>组名称</td>
			<td>组状态</td>
			<td>&nbsp;
				<s:if test="#request.canAdd==true||#session.ccclubs_login.suId==0">
				<a onclick="top.$.showModalDialog({title:'添加组',url:'${basePath}admin/permissions/group_edit.do'},window)" href="javascript:void(0)">添加组</a></s:if>
			</td>
		  </tr>
		 </thead>	
		 <tbody>
		 <s:iterator value="#request.list" id="item" status="i">
		  <tr>
			<td>&nbsp;${item.sgId}</td>
			<td>&nbsp;${item.sgName}</td>
			<td>&nbsp;${item.sgStatus==0?"无效":""}${item.sgStatus==1?"有效":""}</td>
			<td>
				<s:if test="#request.canEdit==true||#session.ccclubs_login.suId==0">
				<a onclick="top.$.showModalDialog({title:'修改组',url:'${basePath}admin/permissions/group_edit.do?id=${item.sgId}'},window)" href="javascript:void(0)">修改</a></s:if>
				<s:if test="#request.canDel==true||#session.ccclubs_login.suId==0">
				<a href="javascript:DelGroup(${item.sgId},'${item.sgName}')">删除</a></s:if>
				<a onclick="top.$.showModalDialog({title:'组详情',url:'${basePath}admin/permissions/group_details.do?id=${item.sgId}'},window)" href="javascript:void(0)">详情</a>
			</td>
		  </tr>
		 </s:iterator>
	  </tbody>			 
	</table>
<script>
<s:if test="#session.tips.notNull==true">
//错误提示
$.tips("${tips.value}");	
</s:if>

<s:if test="#request.canDel==true||#session.ccclubs_login.suId==0">
/**
* 删除组
**/
function DelGroup(id,flag)
{
	var dialog=$.dialog({
			html:"确定要删除组["+flag+"]吗？",
			title:"删除组",
			onOk:function(){
				window.location='group_del.do?id='+id;
			}
	 });	
}
</s:if>

</script>    
</body>
</html>
