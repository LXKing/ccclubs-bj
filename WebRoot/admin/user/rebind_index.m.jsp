<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>绑定会员卡</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	<meta name="viewport" content="width=device-width, initial-scale=1">	
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_mi")}?v=3"></script>
	<link href="${basePath}admin/css/lazy3q.mi.css?v=3" rel="stylesheet" type="text/css" /> 	
	
	<style>
	form .input,form button,form label,form select{display:block;margin:10px auto;width:100%;}	
	</style>
	
</head>
<body>
	
	
	<div id="page" class="page">
	
		<div class="header">
			<a class="left" style="margin-left:10px;"  href="${basePath}admin/index.do">返&nbsp;&nbsp;回</a>
			<span>绑定会员卡</span>
		</div>		
		<div data-role="content"  data-dom-cache="false">
			
			<form data-ajax="false" id="rebindForm" name="rebindForm" enctype="multipart/form-data" action="rebind_submit.do" method="post">
			
			<input type="hidden" value="${editorpoint}" id="editorpoint" name="editorpoint" />	
			<input type="hidden" value="${entrypoint}" id="entrypoint" name="entrypoint" />	
		
			<table width="90%" align="center" border="0" cellspacing="0" cellpadding="5">
			  <tr>
			    <td colspan="2">
			    	<label>请输入手机号码</label>
					<input class="input" name="csMember.csmMobile" id="csmMobile"  value="${csMember.csmMobile}" type="text" placeholder="请输入手机号码" />
				</td>				
			  </tr>
			  <tr>
			    <td colspan="2">
			    	<label>请输入卡号</label>
			    	<input class="input" placeholder="请输入卡号" type="text" id="cardno" name="cardno" value="${cardno}" />
			    </td>
			  </tr>
			  <tr>
			    <td colspan="2">
			    	<label>请读取识别码</label>
			    	<input class="input" placeholder="请读取识别码" notnull="false" type="password" id="rfid" name="rfid" value="${rfid}" />
			    </td>
			  </tr>
			  <tr>
			    <td colspan="2"><button type="button" onclick="$('#rebindForm').submit();" data-theme="e" data-corners="false">提交注册</button></td>
			  </tr>
			</table>
			</form>
			
		</div>
	</div>
	
	
	<script>
	$(function(){
		<s:if test="#session.tips.notNull==true">
		//错误提示
		alert("${tips.value}");
		</s:if>
	});
	
	
	$("#rebindForm").submit(function(){
		
		//非空验证
		{
			var bSuccess = true;
			$("#rebindForm input,#rebindForm select").each(function(){
				if($.trim($(this).val())=="" && $(this).attr("notnull")!="false" && $(this).attr("type")!="hidden"){
					alert($(this).attr("placeholder"));
					return bSuccess = false;
				}
			});
			if(!bSuccess)
				return false;
		}	
		//手机号码
		{
			var mobile = $("#csmMobile").val();
			var pattern = /^[1][3,4,5,8]\d{9}$/;	
			if(!pattern.test(mobile)){
				alert("手机号码格式输入错误，请输入正确的手机号码。");
				return false;
			}
		}		
		return true;
	});	
	</script>
</body>
</html>