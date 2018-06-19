<%@ page language="java" contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.io.*" %>
<%@page import="com.lazy3q.web.helper.$"%>
<%
	//kindeditor图片上传之回调
	String strJson=request.getParameter("json");
	if(strJson==null || strJson.trim().equals(""))
	{
		out.println("{error:1,message:\"图片服务器之回传参数错误\"}");
		return;
	}
	out.println($.xeh(strJson));
%>