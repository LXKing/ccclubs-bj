<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
    <title>用户管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
</head>  
<body>

${get:srvlimit("admin/permissions/user.do")}
<s:if test="#request.canView!=true">
	<div style="padding:80px 20px;width:500px;margin:0px auto;">
		<center class="message"><FONT color=red><H4>对不起，您没有权限查看当前页面</H4></FONT></center>
	</div>
</body>
</html>
<%if(true)return;%>
</s:if>

<div class="title">用户管理 - 用户列表</div>

<div class="line"></div>

<div  id="user_grid"  class="content" style="height:100%;"></div>

<script>
$.resize(function(){
	$("#user_grid").css("height",$(window).height()-65-10);
});
</script>

<script>

var addOption="";//添加用户链接:当如果该用户有添加用户的权限
<s:if test="#request.canAdd==true || #session.ccclubs_login.suId==0">
	addOption="[<a onclick=\"top.$.showModalDialog({title:'添加用户',url:'${basePath}admin/permissions/user_edit.do'},window)\" href=\"javascript:void(0)\">添加用户</a>]";
</s:if>

// 创建用户树形列表
var userGrid=$("#user_grid").grid({
	column:[["编号",50],["帐号",180],["姓名",100],["手机号码",120],["所属区域",180],["所属组",100],["状态",50],[addOption,200]],
	nodeAt:1
});

//当前登录用户下面的用户列表
var users=[];
var option="";
<s:iterator value="#request.users" id="item" status="i">
option="";
<s:if test="#request.canEdit==true || #session.ccclubs_login.suId==0 && #item.suParentId==#session.ccclubs_login.suId">
	option+="[<a onclick=\"top.$.showModalDialog({title:'修改用户',url:'${basePath}admin/permissions/user_edit.do?id=${item.suId}'},window)\" href=\"javascript:void(0)\">修改</a>]";
</s:if>
<s:if test="#request.canDel==true || #session.ccclubs_login.suId==0 && #item.suParentId==#session.ccclubs_login.suId">
	option+="[<a href=\"javascript:DelUser(${item.suId},'${item.suUsername}')\">删除</a>]";
</s:if>
<s:if test="#request.canEdit==true || #session.ccclubs_login.suId==0 && #item.suParentId==#session.ccclubs_login.suId">	
	option+="[<a href=\"user_reset.do?id=${item.suId}\">重置密码</a>]";
</s:if>
	option+="[<a onclick=\"top.$.showModalDialog({title:'用户详情',url:'${basePath}admin/permissions/user_details.do?id=${item.suId}'},window)\" href=\"javascript:void(0)\">详情</a>]";
users[${item.suId}]=(users[${item.suParentId}]||userGrid).add(
	{data:["${item.suId}","${item.suUsername}","${item.suRealName}","${item.suMobile}","${item.suHost$}","${lz:get('ccclubs_system','srv_group',item.suGroupId).sg_name}","${item.suStatus==0?"无效":""}${item.suStatus==1?"有效":""}",option],expand:false}
)
//当不是当前登录用户的直接用户时，显示为斜体，并且只有查看详情的权限
<s:if test="#item.suParentId!=#session.ccclubs_login.suId">	
	users[${item.suId}].css("font-style","italic");
</s:if>
</s:iterator>

<s:if test="#session.tips.notNull==true">
//当session里有错误提示时，显示错误提示
$.tips("${tips.value}");	
</s:if>

<s:if test="#request.canDel==true||#session.ccclubs_login.suId==0">
/**
* 删除用户
**/
function DelUser(id,flag)
{
	var dialog=$.dialog({
			html:"确定要删除用户["+flag+"]吗？",
			title:"删除用户",
			onOk:function(){
				window.location='user_del.do?id='+id;
			}
	 });	
}
</s:if>  

</script>

</body>
</html>
