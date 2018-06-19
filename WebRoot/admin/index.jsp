<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="get" uri="/get-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<title>${get:adminHost().shOper$}${lz:config("project_name")}</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="basePath" content="${basePath}">
	<meta name="contextPath" content="${basePath}admin/">
	<script>window.contextPath="${basePath}admin/";</script>
</head>
<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
<!-- 感谢jquery框架 -->
<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
<!-- 用户自定义js(懒加载) -->
<script type="text/javascript" src="${basePath}admin/js/define.js"></script>
<link href="${basePath}admin/messages/box/box.css" rel="stylesheet" type="text/css" />
<!-- 感谢My97DatePicker时间控件 -->
<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>
<body scroll="no" style="overflow:hidden;" onselectstart="return false" refer="${ccclubs_login.suId}">
	<iframe id="indexframe" name="indexframe" frameborder="0" width="100%" height="100%" src="frame.do?mm=${lz:uuid()}"></iframe>
</body>
<script>
function link(flag,params){
	indexframe.link(flag,params);
}
</script>
<script type="text/javascript" src="${basePath}admin/js/imqtt.io.js"></script>
<%@ include file="plugin.event.jsp"%> 
</html>
