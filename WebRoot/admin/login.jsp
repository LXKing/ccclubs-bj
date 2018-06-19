<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
    <title>${opener=='true'?"请关闭此页":"用户登录"} - ${lz:config('project_name')}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">

	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>

</head>

<style>
body,html{ margin:0px; padding:0px;height:100%; font-size:12px; border:0px;}
.full{position:absolute; width:100%; height:100%;}
</style>

<body id="loginpage">

<script>
<s:if test="#session.tips.notNull==true">
//当session里有错误提示时，显示错误提示
$.tips("${tips.value}");	
</s:if>
</script>

<!-- 
<img class="qr" src="${basePath}admin/images/ccclubs.apk.png" style="position:absolute;z-index:99999;top:0px;right:0px;"/> 
<img class="qr" src="${basePath}admin/images/ccclubs.app2.png" style="position:absolute;z-index:99999;bottom:0px;right:0px;"/> 
 -->
 
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center" valign="middle"> 
    
    	<s:if test="#request.opener=='true'">
		<span style='font-size:16px;'>系统已经以全屏方式在新的窗口进入系统，请关闭此页</span>
		</s:if>
		<s:else>
	
		<table id="login" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td colspan="2" class="head" align="left" valign="bottom">
				<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/admin/flash/swflash.cab#version=7,0,19,0" width="${lz:len(lz:config("project_name"))*32}" height="40">
					  <param name="movie" value="${basePath}admin/images/Arts.swf" />
					  <param name="quality" value="high" />
					  <param name="FlashVars" value="TEXT=${lz:config("project_name")}&SIZE=32" />
					  <param name=wmode value=transparent />
					  <embed src="${basePath}admin/images/Arts.swf" width="${lz:len(lz:config("project_name"))*32}" height="40" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" wmode="transparent" flashvars="TEXT=${lz:config("project_name")}&SIZE=32"></embed>
					</object>
			</td>
		  </tr>
		  <tr>
		  	<td height="*" width="40%" valign="middle" align="center">
				&nbsp;
			</td>
			<td class="body"  width="60%" align="center">
			<form id="loginForm" name="loginForm" onsubmit="return validation();" action="login_login.do" method="post">
				<table align="center" width="320" border="0" cellspacing="3" cellpadding="3">
				  <tr>
					<td width="100" align="right">登录帐号：</td>
					<td width="*" align="left">
						<input class="input" id="username" name="username" type="text" value="${lz:config("debug")=="true" ? lz:config("super_username") : "" }"/>					</td>
				  </tr>
				  <tr>
					<td align="right">登录密码：</td>
					<td align="left">
						<input class="input" id="password" name="password" type="password" value="${lz:config("debug")=="true" ? lz:config("super_password") : "" }"/>					</td>
				  </tr>
				  <tr>
					<td align="right">验&nbsp;证&nbsp; 码：</td>
					<td align="left">
						<input class="input" name="syscode" id="syscode" type="text" />
						<img src="${basePath}admin/images/syscode.jpg.jsp" title="看不清？点击换一张。" onclick="this.src='${basePath}admin/images/syscode.jpg.jsp?sd='+Math.random()" align="absmiddle"/>					</td>
				  </tr>
				  <tr>
					<td align="right">&nbsp;</td>
					<td align="left">
						<label>
						<input ${lz:or(theme,lz:or(cookie.theme.value,lz:config("theme")))=="window"?"checked":""} name="fullscreen" id="fullscreen" type="checkbox" />
						以全屏方式打开
						</label>
				  </tr>
				  <tr>
					<td colspan="2" align="center">
					  <input style="width:90px;" class="button" type="submit" id="submit" name="Submit" value="提交" />					</td>
				  </tr>
			  </table>
			</form>
			</td>		    
		  </tr>
		  <tr>
			<td colspan="2" align="center" valign="middle" class="foot">
				版权所有
				&nbsp;&nbsp;&nbsp;
				盗版必究			</td>
		  </tr>
		</table>
		
		</s:else>
			
	</td>
  </tr>
</table>	

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
	if($("#syscode").val()==""){
		$.tips("对不起，请输入验证码！");
		return false;
	}	
	return true;
}
if(parent!=this){
	window.parent.location.reload();   
}
</script>    
</body>

</html>