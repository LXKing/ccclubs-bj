<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@page import="com.ccclubs.model.CsMemberInfo"%>
<%@page import="com.ccclubs.model.CsMember"%>
<%@page import="com.lazy3q.web.helper.$"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>快递单打印</title>
<style type="text/css" media=print>
.noprint {
	display: none
}

.PageNext {
	PAGE-BREAK-AFTER: always
}
</style>
<style type="text/css">
.colorcss {
	background-color: #B8E3FA;
}
</style>
<script src="js/jquery-1.7.1.min.js"></script>
<SCRIPT language=javascript>
var HKEY_Root,HKEY_Path,HKEY_Key;
HKEY_Root="HKEY_CURRENT_USER";
HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";

function printsetup() {
	// 打印页面设置
	pageSetup_Null();
	wb.execwb(8, 1);
	pagesetup_default();
}
function printpreview() {
	// 打印页面预览
	pageSetup_Null();
	wb.execwb(7, 1);
	pagesetup_default();
}
//设置网页打印的页眉页脚为空
function pageSetup_Null(){
	try {
		var Wsh=new ActiveXObject("WScript.Shell");
		HKEY_Key="header";
		Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
		HKEY_Key="footer";
		Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
	}
	catch(e){
	}
}
//设置网页打印的页眉页脚为默认值s  
function pagesetup_default(){  
    try{  
        var RegWsh = new ActiveXObject("WScript.Shell");
        hkey_key="header";
        RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"&w&b页码，&p/&P");
        hkey_key="footer";
        RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"&u&b&d");
    }catch(e){}  
}

function printit() {
	//if (confirm('确定打印吗？')) {
		pageSetup_Null();
		wb.execwb(6, 6);
		pagesetup_default();
	//}
}
</SCRIPT>
</head>
<body>
	<%
		String strId = request.getParameter("id");
		Long id = Long.valueOf(strId);
		CsMemberInfo csMemberInfo = CsMemberInfo.Get($.add("csmiMemberId",id));
		if(csMemberInfo!=null){
			request.setAttribute("csMemberInfo",csMemberInfo);
			request.setAttribute("csMember",CsMember.get(csMemberInfo.getCsmiMemberId()));
		}
	%>
	<!--调用active-->
	<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height=0 id=wb name=wb width=3></OBJECT>
	<input class="noprint" type=button name=b utton value="打印" onclick="javascript:printit();">
	<input class="noprint" type=button name=button value="打印页面设置" onclick="javascript:printsetup();">
	<input class="noprint" type=button name=button_show value="打印预览" onclick="javascript:printpreview();">
	<input class="noprint" type=button name=b utton value="关闭" onclick="javascript:window.close();">
	<!--startprint-->
	<p class="colorcss" style="margin-left: 12cm; height:2cm;">&nbsp;</p>
	<p class="colorcss" style="margin-left: 12cm;">${csMemberInfo.csmiName}</p>
	<p class="colorcss" style="margin-left: 12cm;">${csMemberInfo.csmiAddress}</p>
	<p class="colorcss" style="margin-left: 12cm;">&nbsp;</p>
	<p class="colorcss" style="margin-left: 12cm;">${csMember.csmMobile}</p>
	<!---endprint-->
</body>
</html>