<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<title>${lz:config("project_name")}</title>
</head>

<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
<body id="head-panel" scroll="no" style="overflow:hidden;">
	
${lz:set("haveIcon",!(lz:empty(unit_login.$csuuHost.$shOper.csoIcon)))}
<s:if test="#request.haveIcon==true">
	<div id="logo" style="padding:0px;">
		<img height="50" src="${unit_login.$csuuHost.$shOper.csoIcon}"/>
	</div>
</s:if>
<s:else>
	<div id="logo">
		<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/admin/flash/swflash.cab#version=7,0,19,0" width="400" height="40">
		  <param name="movie" value="${basePath}admin/images/Arts.swf" />
		  <param name="quality" value="high" />
		  <param name="FlashVars" value="TEXT=公务用车服务平台&SIZE=32" />
		  <param name=wmode value=transparent />
		  <embed src="${basePath}admin/images/Arts.swf" width="400" height="40" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" wmode="transparent" flashvars="TEXT=企业用车服务平台&SIZE=32"></embed>
		</object>
	</div>
</s:else>
	<div id="panel">
		<div class="option">
			<a class="icon" href='login.logout.do'>
				<img src="${basePath}admin/images/icons/2008825642782877805.png" />
				<span>退出系统</span>
			</a>
			<a class="icon" onclick="top.themes()" href='javascript:void(0);'>
				<img src="${basePath}admin/images/icons/20088256423873477801.png" />
				<span>风格主题</span>
			</a>
			<a class="icon" href='javascript:password();'>
				<img src="${basePath}admin/images/icons/Keychain.png" />
				<span>修改密码</span>
			</a>
			<a class="icon" onClick="parent.href('welcome.do','系统首页')" href='javascript:void(0);'>
				<img src="${basePath}admin/images/icons/home.png" />
				<span>系统首页</span>
			</a>
		</div>
		<div class="info">
			<input id="suId" value="${unit_login.csuuId}" type="hidden" />
			欢迎您, ${unit_login.$csuuUnitInfo.csuiName}&nbsp;&nbsp;
			用户名：${unit_login.csuuUsername}&nbsp;&nbsp;
			<a onclick="password();" href="#">修改密码</a>		
		</div>
	</div>
	
	<script>
	<s:if test="#session.tips.notNull==true">
		top.showTips("${tips.value}");//错误提示
	</s:if>	
	$("#shortcut").pngFix();	
	
	function password(){
		top.$.showModalDialog({url:"password.do"},{},window);
	}
	
	</script>
		
</body>
</html>