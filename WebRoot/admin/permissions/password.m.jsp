<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
    <title>修改密码 - ${lz:config('project_name')}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">	
	<meta name="viewport" content="width=device-width, initial-scale=1">	
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_mi")}"></script>
	<link href="${basePath}admin/css/lazy3q.mi.css" rel="stylesheet" type="text/css" /> 
</head>

<body>

	<div class="page">
	
		<div class="header">
			<span>修改密码</span>
			<a style="margin-left:10px;" class="left" href="${basePath}admin/index.do">返回首页</a>
		</div>
		
		
		<div class="content">
			
			<br/><br/><br/>
			
			<form data-ajax="false" id="passwordForm" name="passwordForm" action="${basePath}admin/permissions/password_update_password.do" method="post">

				<label for="old">旧密码</label>
				<input class="input" type="text" id="old" name="old" placeholder="请输入旧密码" />
	
				<label for="new">新密码</label>
				<input class="input" type="password" id="new" name="new" placeholder="请输入新的登录密码"/>
	
				<label for="agin">确认密码</label>
				<input class="input" type="password" id="agin" name="agin" placeholder="请再次输入新的登录密码"/>
				
				<br/>
				
				<button data-corners="false" type="submit" data-theme="e">提交修改</button>
			
			</form>
		
		</div>
		
	</div>
	
	<script>
	<s:if test="#session.tips.notNull==true">
	//错误提示
	alert("${tips.value}");	
	</s:if>
	</script>
	
</body>
</html>