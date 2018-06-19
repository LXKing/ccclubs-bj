<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("page",notices)}
<s:if test="#request.page.total==0">
	<br/>
	<center>当前没有消息通知</center>
</s:if>
<input type="hidden" value="${noticeCount}"/>
<s:iterator value="#request.page.result" id="item" status="i">
	<div class="item${item.csnStatus==1?" flush":""}" id="notice-${item.csnId}">
		<a class='tit' href='javascript:void(0)' onclick="$.open('/admin/systematic/notice_edit.do?id=${item.csnId}',reloadNotice)">
			${lz:left(item.csnContent,50)}</a>
	</div>
</s:iterator>
<%@include file="page.jsp"%>