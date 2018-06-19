<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
    <title>修改密码</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
</head>  
<body>

<form class="form" id="passwordForm" name="passwordForm" action="password.submit.do" method="post">
<div class="head"></div>
<div class="body">
	<div class="content">
	
		  <div class="prompt">
				温馨提示：请输入旧密码和新密码，确认密码必须和新密码输入一致
		  </div>
		  
		  <br/><br/>

		  
		  <dl>
			<dt>旧密码：</dt>
			<dd>
				<input class="input" name="old" type="password" id="old" value="" maxlength="16" size="16"/>
				<b>*</b>
			</dd>
		  </dl>
		  
		  <div class="line"></div>
		  
		  <dl>
			<dt>新密码：</dt>
			<dd>
				<input class="input" name="new" type="password" id="new" value="" maxlength="16" size="16"/>
				<b>*</b>
			</dd>
		  </dl>
		  <dl>
			<dt>确认新密码：</dt>
			<dd>
				<input class="input" name="agin" type="password" id="agin" value="" maxlength="16" size="16"/>
				<b>*</b>
			</dd>
		  </dl>
		  
		  <div class="line"></div>
		
		  <center>
			  <button class="button" type="submit">完成</button>		
			  <button onclick="$.closeModalDialog();" class="button" type="button">取消</button>
		  </center>
		  
	  </div>	
</div>
<div class="foot"></div>
</form>
	
<script>
$(function(){
	<s:if test="#session.tips.notNull==true">
	var strTips = "${tips.value}";
	top.showTips(strTips);	
	if(strTips.indexOf("密码修改成功")>-1)
		$.closeModalDialog();
	</s:if>
});

$("#passwordForm").form({
	"agin":function(el){
		if(el.value!=$("#new").val())
			return "确认密码和新密码不一致";
	}
});
</script>
	
</body>
</html>