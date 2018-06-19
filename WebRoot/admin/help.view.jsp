<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
<title>${srvHelp.shName}</title> 

<style>
html,body,td,div{font-size:13px;font-family:"微软雅黑";}
div,li,ul,dl,dt,dd,td{line-height:32px;}
img {vertical-align:middle;max-width:90%;margin:20px 10px;box-shadow: 0px 0px 10px #eeeeee;border:5px solid #ffffff;}
.title{height:32px;line-height:32px;margin:10px 0px;text-indent:3px;font-size:16px;font-weight:bold;border-bottom:1px solid #bbbbbb;}
.content{margin:10px;}
</style>

</head>
<body>	

<div class="title">
<span style="float:right;font-size:12px;font-weight:normal;">修改时间：[${srvHelp.shUpdateTime$}]</span>
${srvHelp.shParent$} > ${srvHelp.shName}
</div>
<br/>
<div class="content">
${srvHelp.shText}
</div>

</body>
</html>
