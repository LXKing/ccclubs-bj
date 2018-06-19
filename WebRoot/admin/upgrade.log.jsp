<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="get" uri="/get-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<title>系统升级日志</title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
</head>

<style>
</style>

<body>

<table border="0" cellspacing="0" cellpadding="20"><tr><td>
	
	
	<table class="table" width="100%" border="0" cellspacing="1" cellpadding="5">
		<s:iterator value="#request.page.result" id="item" status="i">
		<tr>
			<td width="155" align="right" valign="top">
				${last==item.csulOnTime?"":item.csulOnTime$}
				&nbsp;
				
			</td>
			<td width="*" style="white-space:normal;">
				<strong>【${item.csulType$}】${item.csulTitle}
				${lz:set("emptyDesc",(empty item.csulDescribe))}
				</strong>
				<s:if test="#request.emptyDesc==false">
				<div style='text-indent:0px;margin-left:10px;'>${lz:html(item.csulDescribe)}</div>
				</s:if>
			</td>
		</tr>
		${lz:set("last",item.csulOnTime)}
		</s:iterator>
	</table>
	
	
   <%@include file="/admin/module/page.jsp"%>
  
 </td></tr></table>

</body>
</html>
