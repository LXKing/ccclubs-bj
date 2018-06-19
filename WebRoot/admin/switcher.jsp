<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<title>${lz:config("project_name")}</title> 
</head>

<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>

<body>
<table id="switcher-panel" width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr height="*">
		<td class="switcher" align="center" valign="middle"><input id="switcher-button" class="show" name="" type="button" /></td>
	</tr>
</table>

<script>
	$("#switcher-button").click(function(){
		if($("#switcher-button").attr("class")=="show"){
			$("#switcher-button").attr("class","hide");
			window.parent.change(false);			
		}else{
			$("#switcher-button").attr("class","show");
			window.parent.change(true);			
		}		
	});
</script>

</body>
</html>
