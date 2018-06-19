<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="get" uri="/get-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<title>${get:adminHost().shOper$}${lz:config("project_name")}</title>
</head>

<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
<!-- 用户自定义js(懒加载) -->
<script type="text/javascript" src="${basePath}admin/js/define.js"></script>
<body id="head-panel" scroll="no" style="overflow:hidden;">
	<div id="logo">
		<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/admin/flash/swflash.cab#version=7,0,19,0" width="${(lz:len(lz:config("project_name"))+lz:len(get:adminHost().shOper$))*32}" height="40">
			  <param name="movie" value="${basePath}admin/images/Arts.swf" />
			  <param name="quality" value="high" />
			  <param name="FlashVars" value="TEXT=${get:adminHost().shOper$}${lz:config("project_name")}&SIZE=32" />
			  <param name=wmode value=transparent />
			  <embed src="${basePath}admin/images/Arts.swf" width="${(lz:len(lz:config("project_name"))+lz:len(get:adminHost().shOper$))*32}" height="40" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" wmode="transparent" flashvars="TEXT=${get:adminHost().shOper$}${lz:config("project_name")}&SIZE=32"></embed>
			</object>
	</div>
	<div id="panel">
		<div class="info">&nbsp;</div>
		<div class="option">
			<a class="icon" href='login_logout.do'>
				<img src="${basePath}admin/images/icons/2008825642782877805.png" />
				<span>退出系统</span>
			</a>
			<a class="icon" onclick="top.$.showModalDialog({url:'permissions/password.do'},{},null);" href="#">
				<img src="${basePath}admin/images/icons/20081122201219142778044.png" />
				<span>修改密码</span>
			</a>
			<s:if test="#session.ccclubs_login.suId==0">
			<a class="icon" id="restart" onclick="top.restart();"  href="javascript:void(0);">
				<img src="${basePath}admin/images/icons/20088256421139077808.png" />
				<span>重启服务</span>
			</a>
			</s:if>
			<a class="icon" id="unlock" onclick="alert($.getObject('${basePath}/admin/message.kill.do?mm='+Math.random()).message);" href="javascript:void(0);">
				<img src="${basePath}admin/images/icons/key.png" />
				<span>系统加速</span>
			</a>
			<a class="icon" id="help" href="${basePath}admin/help.index.do" target="_blank">
				<img src="${basePath}admin/images/icons/20088256421631277802.png" />
				<span>系统帮助</span>
			</a>
			<a class="icon" id="helper" onclick="top.$.showModalDialog({url:'upgrade.log.do',title:'系统升级日志',width:800,height:$(top).height()*2/3},{},window);" href="javascript:void(0);">
				<img src="${basePath}admin/images/icons/Pencil.png" />
				<span>升级日志</span>
			</a>
			<a class="icon" onclick="top.$.showModalDialog({url:'shortcut.do'},{},window);" href='javascript:void(0);'>
				<img src="${basePath}admin/images/icons/2008825642250778031.png" />
				<span>添加图标</span>
			</a>
			<s:iterator value="#request.shortcuts" id="item" status="i">
				<s:if test="#item.target=='_parent'">
					<a uuid="${item.uuid}" class="icon shortcut" href="javascript:parent.href('${item.link}','${item.name}')">
				</s:if>
				<s:else>
					<a uuid="${item.uuid}" class="icon shortcut" href="${item.link}" target="${item.target}">
				</s:else>
				<img src="${item.icon}" />
				<span>${item.name}</span>
			</a>
			</s:iterator>
			<a class="icon" onclick="top.themes()" href='javascript:void(0);'>
				<img src="${basePath}admin/images/icons/20088256423873477801.png" />
				<span>风格主题</span>
			</a>
			<a class="icon" onClick="parent.href('welcome.do','欢迎页面')" href='javascript:void(0);'>
				<img src="${basePath}admin/images/icons/home.png" />
				<span>欢迎页面</span>
			</a>
		</div>
	</div>
	
	<script>
	<s:if test="#session.ccclubs_login.suId!=0">
	${lz:set("matches",lz:matches(get:SrvUser(ccclubs_login.suId).suPassword,"((?=.*\\d)(?=.*\\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{32}$"))}
	<s:if test="#request.matches==false">
	$(function(){
		var dialog = top.$.showModalDialog({url:'permissions/password.do'},{},null);
		dialog.find(".state-content .caption .icon").remove();
	});
	</s:if>
	</s:if>
	<s:if test="#session.tips.notNull==true">
		top.showTips("${tips.value}");//错误提示
	</s:if>	
	$("#shortcut").pngFix();
	$(function(){
		top.$($(".shortcut")).menu({
			"删除快捷方式":function(ui){
				var uuid=$(ui).attr("uuid");	
				top.$.dialog({
					title:"删除快捷方式",
					html:"<div style='white-space:nowrap;'>确定要删除快捷方式["+$(ui).find("span").html()+"]吗?</div>",
					onOk:function(){
						window.location="shortcut_delIcon.do?uuid="+uuid;											
					},
					modal:true
				});		
			}
		});
	});
	</script>
		
</body>
</html>
