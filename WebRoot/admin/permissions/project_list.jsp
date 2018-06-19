<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>项目管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
</head>  
<body>

${get:srvlimit("admin/permissions/project.do")}
<s:if test="#request.canView!=true">
	<div style="padding:80px 20px;width:500px;margin:0px auto;">
		<center class="message"><FONT color=red><H4>对不起，您没有权限查看当前页面</H4></FONT></center>
	</div>
</body>
</html>
<%if(true)return;%>
</s:if>

<div class="title">项目管理 - 项目列表</div>

<div class="line"></div>

<div  id="project_grid"  class="content"></div>

<script>
$.resize(function(){
	$("#project_grid").css("height",$(window).height()-65-10);
});
</script>
	
<script>
var addOption="";//添加项目链接:当如果该用户有添加项目的权限
<s:if test="#request.canAdd==true || #session.ccclubs_login.suId==0">
	addOption="[<a onclick=\"top.$.showModalDialog({title:'添加项目',url:'${basePath}admin/permissions/project_edit.do'},window)\" href=\"javascript:void(0)\">添加项目</a>]";
</s:if>
// 创建项目树形列表
var projectGrid=$("#project_grid").grid({
	column:[["编号",50],["项目名称",200],["项目地址",200],["优先级",80],["项目状态",120],[addOption,200]],
	nodeAt:1
});

//所有项目列表
var projects=[];
var option="";
<s:iterator value="#request.list" id="item" status="i">
option="";
<s:if test="#request.canAdd==true || #session.ccclubs_login.suId==0">
	option+="[<a onclick=\"top.$.showModalDialog({title:'添加项目',url:'${basePath}admin/permissions/project_edit.do?parent=${item.spId}'},window)\" href=\"javascript:void(0)\">添加</a>]";
</s:if>
<s:if test="#request.canEdit==true || #session.ccclubs_login.suId==0">
	option+="[<a onclick=\"top.$.showModalDialog({title:'修改项目',url:'${basePath}admin/permissions/project_edit.do?id=${item.spId}'},window)\" href=\"javascript:void(0)\">修改</a>]";
</s:if>
<s:if test="#request.canDel==true || #session.ccclubs_login.suId==0">	
	option+="[<a href=\"javascript:DelProject(${item.spId},'${item.spName}')\">删除</a>]";
</s:if>
	option+="[<a onclick=\"top.$.showModalDialog({title:'项目详情',url:'${basePath}admin/permissions/project_details.do?id=${item.spId}'}},window)\" href=\"javascript:void(0)\">详情</a>]";
projects[${item.spId}]=(projects[${item.spParentId}]||projectGrid).add(
	{data:["${item.spId}","${item.spName}","${item.spUrl}","${item.spLevel}","${item.spStatus==0?"无效":""}${item.spStatus==1?"有效":""}",option],expand:true}
)

</s:iterator>

<s:if test="#session.tips.notNull==true">
//错误提示
$.tips("${tips.value}");	
</s:if>

<s:if test="#request.canDel==true||#session.ccclubs_login.suId==0">
/**
* 删除项目
**/
function DelProject(id,flag)
{
	var dialog=$.dialog({
		html:"确定要删除项目["+flag+"]吗？",
		title:"删除项目",
		onOk:function(){
			window.location='project_del.do?id='+id;
		}
	 });
}
</s:if>

</script>    
</body>
</html>
