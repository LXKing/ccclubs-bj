<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>用户资料修改</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	
	<!-- 感谢jquery提供的前端js库 -->
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	
	<!-- lazy3q提供的系统js -->
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" />	
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
</head>  
<body>
<script>
$(function(){	
	showTips("${tips.value}");
});
$(function(){
	$("#infoForm").form({
		"srvUser.suMobile":function(el){
			if(el.value.length>32)
				return "手机最大长度为32个字符";
			var mobile = $(el).val();
			var pattern = /^[1][3,4,5,8,7,9]\d{9}$/;	
			if(!pattern.test(mobile))
				return "手机号码格式输入错误，请输入正确的手机号码。";
		},
		"srvUser.suEmail":function(el){
			if(el.value.length>128)
				return "邮箱最大长度为128个字符";
			var email = $(el).val();
			var pattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;	
			if(!pattern.test(email))
				return "邮箱格式输入错误，请输入正确的邮箱。";
		}
	},function(){//表单提交自定义判断
		/******************************LAZY3Q_FORM_VALIDATE******************************/
		/******************************LAZY3Q_FORM_VALIDATE******************************/
	});		
});
</script>

<form class="form editform" id="infoForm" name="infoForm" action="user_info.do" method="post">

<div class="head" style="width:500px;"></div>
<div class="body" style="width:500px;">
	<div class="content">
	
		<div class="prompt" style="width:400px;">
			温馨提示：请仔细填写相关信息，<span class="extrude">"*" 为必填选项。</span>
		</div>
		
		<input type="hidden" value="${editorpoint}" id="editorpoint" name="editorpoint" />	
		<input type="hidden" value="${entrypoint}" id="entrypoint" name="entrypoint" />
		<input type="hidden" value="${complete}" name="complete" id="complete" />
		<input type="hidden" value="${edittype}" name="edittype" id="edittype" />
		
		<dl style="width:98%;">
			<dt>手机号码:</dt>
			<dd class="minor">
				 <input type="input" class="input" id="srvUser.suMobile" name="srvUser.suMobile" value="${srvUser.suMobile}"/>
			 	 <b>*</b>
			 	 <em>请输入您的手机号码</em>
			</dd>
		</dl>
			
		<dl style="width:98%;">
			<dt>邮箱地址:</dt>
			<dd class="minor">
				 <input type="input" class="input" id="srvUser.suEmail" name="srvUser.suEmail" value="${srvUser.suEmail}"/>
			 	 <b>*</b>
			 	 <em>请输入您的邮箱地址</em>
			</dd>
		</dl>
			
		<div class="line"></div>
		<center class="buttons">			
			<button class="button" type="submit" name="submiter" value="完成">
				&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
				完成&nbsp;&nbsp;</button>
			<button class="button" type="reset">
				&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778045.png"/>
				重置&nbsp;&nbsp;</button>
			<button class="button" onclick="cancel();" type="button">
				&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-multiply.png"/>
				取消&nbsp;&nbsp;</button>
		</center>
	</div>	
</div>
<div class="foot" style="width:500px;"></div>
	
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