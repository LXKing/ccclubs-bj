<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
${lz:set("page",todos)}
<s:if test="#request.page.total==0">
	<br/>
	<center>您当前没有待办事项</center>
</s:if>
<input type="hidden" value="${todoCount}"/>
<s:iterator value="#request.page.result" id="item" status="i">
	<div class="item${item.cstStatus==1?" flush":""}" id="todo-${item.cstId}" onclick="$.open('${basePath}admin/systematic/todo_edit.do?id=${item.cstId}',reloadTodo)">
		<span class="tit">${lz:left(item.cstTitle,16)}</span>
		<span class="cnt">${lz:left(item.cstContent,25)}${empty item.cstContent?"未添加待办事项详细内容":""}</span>
	</div>
</s:iterator>
<%@include file="page.jsp"%>