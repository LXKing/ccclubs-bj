<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>对账详情</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
		
	 
</head>  
<body>


<table border="0" cellspacing="0" cellpadding="0"><tr><td>


<div class="title">财务对账 - 对账详情</div>

<div class="line"></div>
<div class="content">
	<form class="query" id="queryForm" action="financial.details.do" method="post">

			 
		</form>
	</div>

<s:if test="#request.haveQuery==false">
<script>
$(function(){
	$(".query").parent().prevAll().remove();
	$(".query").parent().remove();
})
</script>
</s:if>
    

	
</td></tr><tr><td>
  <%@include file="/admin/module/page.jsp"%> 
	<div class="content"> 
		<table id="car_table" ref="Car" class="table" width="860" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	<td> 使用帐号</td> 
			<td>订单车辆 </td>
			<td>订单类型 </td> 
			<td>使用人</td> 
			<td >使用人手机</td> 
			<td >所属部门</td> 
			<td>订单开始时间</td> 
			<td >订单实际还车时间</td> 
			<td>实付金额(元) </td> 
			<td >订单里程 </td>
			<td >订单时长 </td>
			<td >操作 </td>        
		  </tr>
		 </thead>	
		 <tbody>
		 	<s:iterator value="#request.page.result" id="item" status="i">
			  <tr id="${item.csuoId}">
			  	<td class="node">
					${item.csuoUnitChild$}
				</td>
				<td   class=" node">
					${item.csuoCar$}
				</td>
				<td   class=" node">
					${item.csuoType$}
				</td> 
				<td   class=" node">
				 	${item.csuoUsername$}
				</td> 
				<td   class=" node">
				 	 ${item.csuoMobile$}
				</td>
				<td  class="node">
				 	${item.csuoUnitGroup$}
				</td>
				<td  class=" node">
				 	${item.csuoStartTime$}
				</td>
				<td  class=" node">
					 ${item.csuoRetTime$}
				</td>
				<td  class=" node">
					${item.csuoPayReal$}
				</td>
				<td  class=" node">
					${item.csuoMileage$}
				</td>
				 <td  class=" node">
					 ${item.$csuoOrder.csoDuration}
				</td>
				<td  class=" node">
				  <a href="javascript:Details('详情','${item.csuoId}')">详情</a>
				</td>
			  </tr>
		 </s:iterator>
		 </tbody>			 
	  </table>
	  
  </div>
  <%@include file="/admin/module/page.jsp"%> 
 </td></tr></table>     
<div class="line"></div>
		<center> 
			 
			<button class="button" onclick="cancel();" type="button">&nbsp;&nbsp;关闭&nbsp;&nbsp;</button>
		</center>
 <script>
	
	showTips("${tips.value}");
    function cancel(){
		if(${controller.mutual=="dialog"})
			$.closeModalDialog();
		else if(${controller.mutual=="page"})
			window.location="${lz:xeh(entrypoint)}";
		else if(true)
			$.closeModalDialog();
		else
			window.location="${lz:xeh(entrypoint)}";
	}
	function href(url,params){
		if(${controller.mutual=="dialog"})
			top.$.showModalDialog({url:url},params,window);
		else if(${controller.mutual=="page"})
			$.submit(url,params,"_self");
		else if(true)
			top.$.showModalDialog({url:url},params,window);
		else
			$.submit(url,params,"_self");	
	} 
	
	function Details(operate,id){
			var url = "${basePath}${namespace}order.details.do";
			var params = {entrypoint:"${entrypoint}",id:id,controller:{title:operate}};
			href(url,params);
	}
</script>
</body>
</html>
