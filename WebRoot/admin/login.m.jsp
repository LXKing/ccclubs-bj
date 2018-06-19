<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
    <title>用户登录 - ${lz:config('project_name')}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_mi")}?v=2"></script>
	<link href="${basePath}admin/css/lazy3q.mi.css?v=2" rel="stylesheet" type="text/css" /> 
</head>

<body>

	<div class="page">
	
		<div class="header">
			<span>车纷享系统</span>
		</div>
		
		<div class="content">
						
			<br/><br/>
			
			<form id="loginForm" name="loginForm" action="login_login.do" method="post">
				
				<label for="username">用户名</label>
				<input class="input" type="text" id="username" name="username" autocomplete="off" placeholder="请输入用户名" />
	
				<label for="password">密码</label>
				<input class="input" type="password" id="password" name="password" autocomplete="off" placeholder="请输入登录密码"/>
	
				<label for="syscode">
					验证码
					<img src="${basePath}admin/images/syscode.jpg.jsp" title="看不清？点击换一张。" onclick="this.src='${basePath}admin/images/syscode.jpg.jsp?sd='+Math.random()" align="absmiddle"/>
				</label>
				<input class="input" type="text" id="syscode" name="syscode"  placeholder="请输入验证码"/>
				
				<br/>
				
				<button data-corners="false" type="submit" data-theme="e">登录</button>
											
			</form>
		
		</div>
		
	</div>
	
	<script>
	<s:if test="#session.tips.notNull==true">
	//错误提示
	alert("${tips.value}");
	</s:if>
	
	$(function(){
		if(android.cookie){
			$("#username").val(android.cookie("username"));
			$("#password").val(android.cookie("password"));
			$("#loginForm").submit(function(){
				android.setCookie("username",$("#username").val());
				android.setCookie("password",$("#password").val());
			});
		}		
	});
	
	</script>
        
</body>
</html>