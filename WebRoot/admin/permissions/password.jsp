<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="get" uri="/get-tags" %>

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

<form class="form" id="passwordForm" name="passwordForm" action="password_update_password.do" method="post">
<div class="head" style="width:500px;"></div>
<div class="body" style="width:500px;">
	<div class="content">
	
		  
		  ${lz:set("matches",lz:matches(get:SrvUser(ccclubs_login.suId).suPassword,"((?=.*\\d)(?=.*\\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{32}$"))}
		  <s:if test="#request.matches==false">
			<center>
				<font color="red"><h4>您的密码即将过期，请即时修改</h4></font>
			</center>
	      </s:if>
	      <s:else>
	      	<div class="prompt" style="width:400px;">
	      		温馨提示：请输入旧密码和新密码以及确认密码
	      	</div>
	      </s:else>

		  <dl style="width:98%;">
			<dt>输入旧密码：</dt>
			<dd>
				<input class="input" name="old" type="password" id="old" value="" maxlength="16" size="16"/>
				<b>*</b>
				 <em>请输入您的旧密码</em>
			</dd>
		  </dl>
		  <dl style="width:98%;">
			<dt>输入新密码：</dt>
			<dd>
				<input class="input" name="now" type="password" id="now" value="" maxlength="16" size="16"/>
				<b>*</b>
				<em>请输入您的新密码,8到16位字符，不能为纯数字或纯字母</em>
			</dd>
		  </dl>
		  <dl style="width:98%;">
			<dt>确认新密码：</dt>
			<dd>
				<input class="input" name="agin" type="password" id="agin" value="" maxlength="16" size="16"/>
				<b>*</b>
				 <em>请输入确认新密码</em>
			</dd>
		  </dl>
		  
		  <div class="line"></div>
		
		  <center>
			  <button class="button" type="submit">提交</button>		
			  <button class="button" type="reset">重置</button>
		  </center>
		  
	  </div>	
</div>
<div class="foot" style="width:500px;"></div>
</form>
	
<script>
<s:if test="#session.tips.notNull==true">
$(function(){
	${lz:set("tipsValue",tips.value)}
	top.$.tips("${tipsValue}");
	<s:if test="#request.tipsValue=='密码修改成功'">
	$.closeModalDialog();
	</s:if>
});
</s:if>

$("#passwordForm").form({
	"now":function(el){
		if(!/((?=.*\\d)(?=.*\\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{8,16}$/g.test(el.value))
			return "8到16位字符，不能为纯数字或纯字母";
	},
	"agin":function(el){
		if(el.value!=$("#now").val())
			return "确认密码和新密码不一致";
	}
});
</script>
	
</body>
</html>
