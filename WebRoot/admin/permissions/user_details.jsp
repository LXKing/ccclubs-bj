<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
    <title>用户详情</title>
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

<form class="form editform">
<div class="head"></div>
<div class="body">
	<div class="content">	
		<dl>
			<dt>用户编号:</dt>
			<dd>${srvUser.suId}</dd>
		</dl>
		<dl>
			<dt>用户名:</dt>
			<dd>${srvUser.suUsername}</dd>
		</dl>
		<dl>
			<dt>真实姓名:</dt>
			<dd>${srvUser.suRealName}</dd>
		</dl>
		<s:if test="#session.ccclubs_login.suId==0">
		  	<s:if test="srvUser==null">
		  <dl>
		    <dt>所属区域：</dt>
		    <dd>
				${lz:get('ccclubs_system','srv_host',srvUser.suHost).sh_name}
			</dd>
		  </dl>
		  	</s:if>
		  </s:if>
		 <s:if test="srvUserExp != null">
		  <dl>
		    <dt>网点：</dt>
		    <dd>
				${srvUserExp.sueOutlets$}
			</dd>
		  </dl>
		 </s:if>
		  
		  
		<dl>
			<dt>所属组:</dt>
			<dd>${lz:get('ccclubs_system','srv_group',srvUser.suGroupId).sg_name}</dd>
		</dl>
		<dl>
			<dt>添加时间:</dt>
			<dd><s:date name="srvUser.suAddTime" format="yyyy-MM-dd HH:mm:ss"/></dd>
		</dl>
		<dl>
			<dt style="width:88px;">最后登录时间:</dt>
			<dd><s:date name="srvUser.suLastTime" format="yyyy-MM-dd HH:mm:ss"/></dd>
		</dl>
		<dl>
			<dt  style="width:88px;">最后登录IP:</dt>
			<dd>${srvUser.suLastIp}</dd>
		</dl>
		<dl>
			<dt>用户状态:</dt>
			<dd>${srvUser.suStatus==0?"无效":""}${srvUser.suStatus==1?"有效":""}</dd>
		</dl>
		<dl style="width:100%;">
			<dt>子级组:</dt>
			<dd>
				${lz:set("items",lz:query$('ccclubs_system','select * from srv_group where sg_parent_id=? order by sg_id desc',srvUser.suId))}
				<s:iterator value="#request.items" id="item" status="i">
				&nbsp;[${item.sg_name}]&nbsp;
				</s:iterator>
			</dd>
		</dl>
		
		<div class="line"></div>
		<center class="buttons">
			<button class="button" onclick="cancel();" type="button">&nbsp;&nbsp;确定&nbsp;&nbsp;</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="button" onclick="cancel();" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
		</center>
	</div>
</div>
<div class="foot"></div>

</form>

<script>
	
	function cancel(){
		$.closeModalDialog();
	}
	
</script>

</body>
</html>