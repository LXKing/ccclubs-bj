<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("projectpath","admin/systematic/eventheed.do")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>设置关注事件</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	
	<!-- 感谢jquery提供的前端js库 -->
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	
	<!-- lazy3q提供的系统js -->
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" />	
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<script type="text/javascript">
		window.basePath="${basePath}";
		window.contextPath="${basePath}${proname}/";
		window.apiUrl="${basePath}${proname}/api.do";
	</script>
		
</head>  
<body>

<!------------------------LAZY3Q_JSP_BODY------------------------>
<!------------------------LAZY3Q_JSP_BODY------------------------>

<script>
$(function(){	
	showTips("${tips.value}");
});
$(function(){
	<s:iterator value="#request.heeds" id="item" status="i">
	setHeed(${item.csehEventType},"${item.csehHeedType}");
	</s:iterator>
	$(".editform").submit(function(){
		
		//return false;
	});
});
function setHeed(type,heedType){
	if($.trim(heedType)=="")
		return;
	var arr = heedType.split(",");
	for(var o in arr){
		var heed = $.trim(arr[o].replace(/#/g,""));
		$("#th_"+type+"_"+heed).attr("checked",true);
	}
}
</script>
<style>
.table tbody td{text-indent:0px;}
</style>

<form class="form editform" ref="CsEventHeed" id="eventheedForm" name="eventheedForm" action="${basePath}admin/message.heed.do" method="post">

<div class="head"></div>
<div class="body" style="width:1060px;">
	<div class="content">	
	
		
		<input type="hidden" value="${editorpoint}" id="editorpoint" name="editorpoint" />	
		<input type="hidden" value="${entrypoint}" id="entrypoint" name="entrypoint" />
		<input type="hidden" value="${complete}" name="complete" id="complete" />	
		<input type="hidden" value="save" name="edittype" id="edittype" />
		
		
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tbody>
				<tr>
				  	<td width="33%">
						接收手机号码:
						<input type="input" class="input" id="srvUser.suMobile" name="srvUser.suMobile" value="${srvUser.suMobile}"/>
					</td>
				  	<td width="33%">
						接收邮箱地址:
						<input type="input" class="input" id="srvUser.suEmail" name="srvUser.suEmail" value="${srvUser.suEmail}"/>
					</td>
				  	<td width="33%">
						绑定微信帐号:发送(qr:登录帐号,密码)到车纷享服务窗<br/>例如(qr:zhangsan,123456)
					</td>
				</tr>
			</tbody>
		</table>
		
		<br/>
		
		<table class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
			<thead class="state-header">
				<tr>
				  <td>事件类型</td>
				  <td width="50">提醒</td>
				  <td width="50">通知</td>
				  <td width="50">邮件</td>
				  <td width="50">短信</td>
				  <td width="50">微信</td>
				  <td>事件类型</td>
				  <td width="50">提醒</td>
				  <td width="50">通知</td>
				  <td width="50">邮件</td>
				  <td width="50">短信</td>
				  <td width="50">微信</td>
				  <td>事件类型</td>
				  <td width="50">提醒</td>
				  <td width="50">通知</td>
				  <td width="50">邮件</td>
				  <td width="50">短信</td>
				  <td width="50">微信</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					${lz:set("status",0)}
					<s:iterator value="#request.types" id="item" status="i">
					${i.count%3==1?"</tr><tr>":""}
				  	<td align="right">
				  		${item.csetName}：<input type="hidden" name="types" id="types" value="${item.csetId}"/>
				  	</td>
				  	<td align="center">
						<input type="checkbox" name="type_${item.csetId}" id="th_${item.csetId}_0" value="#0#"/>
					</td>
				  	<td align="center">
						<input type="checkbox" name="type_${item.csetId}" id="th_${item.csetId}_1" value="#1#"/>
					</td>
					<td align="center">
						<input type="checkbox" name="type_${item.csetId}" id="th_${item.csetId}_3" value="#3#"/>
					</td>
				  	<td align="center">
						<input type="checkbox" name="type_${item.csetId}" id="th_${item.csetId}_2" value="#2#"/>
					</td>
					<td align="center">
						<input type="checkbox" name="type_${item.csetId}" id="th_${item.csetId}_4" value="#4#"/>
					</td>
					${lz:set("status",3-(i.count%3))}
				  	</s:iterator>
				  	<s:if test="#request.status<3">
				  	<s:bean name="org.apache.struts2.util.Counter" id="counter">
						 <s:param name="first" value="1"/>
						 <s:param name="last" value="#request.status"/>
						 <s:iterator>
							  <td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
						 </s:iterator>
				 	</s:bean>
				 	</s:if>
				</tr>
			</tbody>
		</table>
		
		
		<div class="line"></div>
		<center class="buttons">		
			<button class="button" type="submit" name="submiter" value="完成">
			&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
			完成&nbsp;&nbsp;</button>
			<button class="button" type="submit" name="submiter" value="保存">
			&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778030.png"/>
			保存&nbsp;&nbsp;</button>
			<button class="button" onclick="cancel();" type="button">
				&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-multiply.png"/>
				取消&nbsp;&nbsp;</button>
		</center>
		
		
	</div>	
</div>
<div class="foot"></div>
	
</form>

<script>
	
	function cancel(){
		$.closeModalDialog();
	}
	
	function href(url,params){
		top.$.showModalDialog({url:url},params,window);
	}
</script>
<!------------------------LAZY3Q_JSP_BOTTOM------------------------>
<!------------------------LAZY3Q_JSP_BOTTOM------------------------>
</body>
</html>