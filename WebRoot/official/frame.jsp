<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<title>${lz:config("project_name")}</title> 
</head>

<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>

<script>
function toggle(bShow){
	if(!bShow)	
		$("#bodys").attr("cols","0,9,*");	
	else
		$("#bodys").attr("cols","200,9,*");
}
function change(bShow){
	if(!bShow)	
		$("#frameset").attr("rows","0,9,*");	
	else
		$("#frameset").attr("rows","50,9,*");
}

function link(flag,params){
	left.link(flag,params);
}

function href(url,title){
	right.href(url,title);
}
</script>

<frameset id="frameset" framespacing="0" frameborder="0" rows="50,9,*">
	<frame id="header" name="header" noresize="noresize" frameborder="0" src="index.header.do"/>
	<frame id="switcher" noresize="noresize" scrolling="no" frameborder="0" src="index.switcher.do"/>
	<frameset id="bodys" framespacing="0" frameborder="0" cols="200,9,*">
		<frame id="left" name="left" noresize="noresize" frameborder="0" src="index.left.do"/>
		<frame id="separator" noresize="noresize" scrolling="no" frameborder="0" src="index.separator.do"/>
		<frame id="right" name="right" noresize="noresize" frameborder="0" src="index.right.do"/>
	</frameset>
</frameset>
<noframes>
	对不起，您的浏览器不支持框集，无法显示网页
</noframes>
</html>