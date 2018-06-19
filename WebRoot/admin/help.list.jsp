<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
<title>${lz:config("project_name")}导航</title> 

<style>
html,body,td,div{font-size:12px;}
img {vertical-align:middle;} 
#id0{width:24px;height:24px;margin:0px 5px;}
</style>
	
<link href="${basePath}admin/js/dtree/dtree.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="${basePath}admin/js/dtree/dtree.js"></script>
	
</head>
<body>	
<script type="text/javascript">
	<!--
	var d = new dTree('d',"${basePath}admin/js/dtree/");
	d.config.folderLinks = false;
	d.icon.root = '${basePath}admin/images/icons/20088256421631277802.png';
	d.add(0,-1,'${lz:config("project_name")}帮助手册');
	<s:iterator  value="#request.list" id="item" status="i">
	d.add(${item.shId},${item.shParent},'${item.shName}','${basePath}admin/help.view.do?id=${item.shId}','${item.shProfile}','right');
	</s:iterator>
	document.write(d);
	//-->
</script>
</body>
</html>
