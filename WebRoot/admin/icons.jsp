<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.io.*" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+(request.getServerPort()==80?"":(":"+request.getServerPort()))+request.getContextPath()+"/";
%>
<div style="width:700px;margin:0px 20px;">
<%
String iconPath = pageContext.getServletContext().getRealPath("/") + "admin/images/icons/";
File file=new File(iconPath);
for(String filename:file.list()){%>
	<img class="icon" src="<%=basePath+"admin/images/icons/"+filename%>" width=48 height=48 style="margin:5px;cursor:pointer;"/>
<%}%>

<div style="clear:both;"></div>

</div>