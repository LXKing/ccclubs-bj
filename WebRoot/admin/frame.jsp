<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<title>${lz:config("project_name")}</title> 
</head>

<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>

<script>
function toggle(bShow){
	var cols = $("#bodys").attr("cols");
	if(((bShow+"")=="false") || cols=="210,9,*"){
		$("#bodys").attr("cols","0,9,*");
		return false;
	}else{
		$("#bodys").attr("cols","210,9,*");
		return true;
	}
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

function href(url,title,icon){
	right.href(url,title,icon);
}
</script>

<frameset id="frameset" framespacing="0" frameborder="0" rows="50,9,*">
	<frame id="header" name="header" noresize="noresize" frameborder="0" src="header.do?mm=${lz:uuid()}"/>
	<frame id="switcher" noresize="noresize" scrolling="no" frameborder="0" src="switcher.do?mm=${lz:uuid()}"/>
	<frameset id="bodys" framespacing="0" frameborder="0" cols="210,9,*">
		<frame id="left" name="left" noresize="noresize" frameborder="0" src="left.do?mm=${lz:uuid()}"/>
		<frame id="separator" noresize="noresize" scrolling="no" frameborder="0" src="separator.do?mm=${lz:uuid()}"/>
		<frame id="right" name="right" noresize="noresize" frameborder="0" src="right.do?mm=${lz:uuid()}"/>
	</frameset>
</frameset>
<noframes>
	对不起，您的浏览器不支持框集，无法显示网页
</noframes>
</html>
