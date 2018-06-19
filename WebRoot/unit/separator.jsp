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
<table id="separator-panel" width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr height="32"><td class="state-title">&nbsp;</td></tr>
	<tr height="*">
		<td class="separator" align="center" valign="middle">
			<input id="separator-button" class="show" name="" type="button" />
		</td>
	</tr>
</table>

<script>
	$("#separator-button").click(function(){
		if($("#separator-button").attr("class")=="show"){
			$("#separator-button").attr("class","hide");
			window.parent.toggle(false);			
		}else{
			$("#separator-button").attr("class","show");
			window.parent.toggle(true);			
		}		
	});
</script>

</body>
</html>