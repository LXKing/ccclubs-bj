<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<title>${lz:config("project_name")}系统帮助</title> 
</head>
<frameset rows="*" cols="250,*" framespacing="0" frameborder="NO" border="0">
   <frame src="help.list.do" name="left" scrolling="Yes" frameborder="NO" >
   <!-- help.view.do -->
   <frame src="" name="right" frameborder="NO" scrolling="Auto" >
</frameset>
<noframes> 
<body>	
你的浏览器不支持框架集，无法显示帮助文档
</body>
</noframes>
</html>
