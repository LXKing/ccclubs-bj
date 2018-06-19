<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
    <title>用户登录 - 公务用车服务平台</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">	

	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>

</head>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style>
*{ margin:0; padding:0; font-family: 'MicrosoftYaHei';}
.clear{ clear:both}
.top{ width:100%;}
.top img{ width:100%; position:absolute}
.top_p{ width:520px; height:120px; position:fixed; margin-left:27%; margin-top:30px;}<%-- width:520px; height:120px; position:fixed; margin-left:33%; margin-top:30px; --%>
.top_p img{width:120px; height:120px;}
.top_p p{ width:100%; float:left; font-size:30px; color:#FFF; margin:45px 130px 0; font-weight:bolder;letter-spacing:8px; }
.login{ width:500px; height:320px; background:#FFF; position:absolute;top:35%;left:30%;border-radius:5px;}
.copy{ position:absolute;bottom:1%;left:30%;width:520px; color:#919191; text-align:center;}
.copy p{color:#919191; font-size:14px;margin-bottom: 3%; font-weight:550;}
.input_div{ width:440px; height:40px; border: 1px solid #adadad; margin:0 auto; margin-top:30px;margin-left:30px; }
.input_code{ width:80px; height:40px; border: 0px solid #adadad; margin:0 auto; margin-top:-30px;margin-left:350px; }
.input_word{ width:330px; height:40px; border: 1px solid #adadad; margin:0 auto; margin-top:30px;margin-left:30px; }
.big_button{width:440px; height:40px; font-size:20px; border: 0px; color:#ffffff; background-color:#b13735; margin-top:30px;margin-left:30px;}
.input_div p{color:#919191; font-size:14px;margin-top: 2.5%; margin-left: 3%; font-weight:bolder;}
.input_div input{height:28px; width:350px;border-style:none;font-size:16px; outline-style:none; margin-top: -5%; margin-left: 14%;line-height:30px}
.input_word p{color:#919191; font-size:14px;margin-top: 3%; margin-left: 4%; font-weight:bolder;}
.input_word input{height:28px;width:200px;border-style:none;font-size:16px; outline-style:none; margin-top: -7%; margin-left: 23%;line-height:30px}
</style>
</head>

<body bgcolor="f0f0f0">

<script>
<s:if test="#session.tips.notNull==true">
//当session里有错误提示时，显示错误提示
$.tips("${tips.value}");	
</s:if>
</script>
<form id="loginForm" name="loginForm" onsubmit="return validation();" action="login.login.do" method="post">
<div class="top">
	<img src="${basePath}official/images/bg_01.png"  width="100%"/>
    <div class="top_p">
    	<%-- <img src="${basePath}official/images/logo_03.png"/> --%>
		<p>公务用车服务平台</p>
    </div>
</div>
<div class="clear login">
	<div class="input_div"><p>账号</p><input type="text" id="username" name="username" value="${lz:config("debug")=="true" ? "unit" : "" }"/></div>
  <div class="input_div"><p>密码</p><input type="password" id="password" name="password" value="${lz:config("debug")=="true" ? "123456" : "" }"/></div>
  <div class="input_word"><p>验证码</p><input type="text" name="syscode" id="syscode"/><div class="input_code" align="right"> <img src="${basePath}official/images/syscode.jpg.jsp" title="看不清？点击换一张。" onclick="this.src='${basePath}official/images/syscode.jpg.jsp?sd='+Math.random()" align="right"/></div>
 
</div>
	<input class="big_button"  type="submit" id="submit" name="Submit" value='登  录'></input>
</div>
<div class="copy">
	<p>版权所有&nbsp;盗版必究</p>
</div>

<script>
function validation()
{
	if($("#username").val()==""){
		$.tips("对不起，请输入用户名！");
		return false;
	}
	if($("#password").val()==""){
		$.tips("对不起，请输入密码！");
		return false;		
	}
	if($("#checkcode").val()==""){
		$.tips("对不起，请输入验证码！");
		return false;
	}
	return true;
}
</script>
	
<script>
if(window.top!=this){
	window.top.location.reload();   
}
</script>
</form>
</body>
</html>
