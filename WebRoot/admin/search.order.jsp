<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<s:if test="#order!=null">
${get:srvlimit("admin/service/order.do")}
<s:if test="#request.canView==true">
	${lz:set("haveData",true)}
	<div class="item">
		<dl class="opts">
			<dt>操作 ▼<dt><dd class="optionMenu">
			<s:if test="#request.canExp[0]==true">
			<a href="javascript:HandleSel('发短信','${order.csoId}','${basePath}admin/service/order_edit.do')">发短信</a>
			</s:if>
			<s:if test="#request.canExp[1]==true">
			<a href="javascript:HandleSel('续订','${order.csoId}','${basePath}admin/service/order_edit.do')">续订</a>
			</s:if>
			<s:if test="#request.canExp[2]==true">
			<a href="javascript:HandleSel('取消订单','${order.csoId}','${basePath}admin/service/order_edit.do')">取消订单</a>
			</s:if>
			<s:if test="#request.canExp[3]==true">
			<a href="javascript:HandleSel('待处理','${order.csoId}','${basePath}admin/service/order_edit.do')">待处理</a>
			</s:if>
			<s:if test="#request.canExp[4]==true">
			<a href="javascript:HandleSel('结算','${order.csoId}','${basePath}admin/service/order_edit.do')">结算</a>
			</s:if>
			<s:if test="#request.canExp[5]==true">
			<a href="javascript:HandleSel('重发','${order.csoId}','${basePath}admin/service/order_edit.do')">重发</a>
			</s:if>
			<s:if test="#request.canExp[6]==true">
			<a href="javascript:HandleSel('撤销','${order.csoId}','${basePath}admin/service/order_edit.do')">撤销</a>
			</s:if>
			</dd>
		</dl>
		<img class="icon" align="absmiddle" src="${basePath}admin/images/icons/20088256422923477803.png"/>
		<a class="detail" href="javascript:void(0);" onclick="$.showModalDialog({url:'${basePath}admin/service/order_details.do?id=${order.csoId}'},{ctrl:{editable:false,editable:false,visible:true}})">
		订单：${lz:keyword(order.csoId,keyword,11)}(${lz:keyword(order.csoStatus$,keyword,11)})</a>
	</div>
</s:if>
</s:if>