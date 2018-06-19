<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>专车司机选择</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
		
	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>
	
</head>  
<body>


<table border="0" cellspacing="0" cellpadding="0"><tr><td>


<div class="title">专车司机管理 - 专车司机选择</div>

<div class="line"></div>
<div class="content">
	<form class="query" id="queryForm" action="drivercar_select.do?csdc_driver_query=true&csscId=${csscId}&csscFinishTime=${csscFinishTime}" method="post">

			<dl>
				<dt>司机姓名:</dt>
				<dd>
		 			<input type="text" class="input" size="16"  maxlength="32" name="csdName"  value="${csdName}"/>
				</dd>
			</dl>
			
			<dl>
				<dt>手机号:</dt>
				<dd>
		 			<input type="text" class="input" size="16"  maxlength="32" name="csdMobile"  value="${csdMobile}"/>
				</dd>
			</dl>
			
			<dl>
				<dt>车牌号:</dt>
				<dd>
		 			<input type="text" class="input" size="16"  maxlength="1024" name="cscrCarNo"  value="${cscrCarNo}"/>
				</dd>
			</dl>	 
		 	
				
			<div class="line"></div>
			<div class="option">
				<table border="0" cellspacing="0" cellpadding="0">
				  <tr>
				    <td>
						<input type="hidden" name="all" value="${all}" />
						<button class="button" type="submit">&nbsp;&nbsp;查询&nbsp;&nbsp;</button>		
						<button class="button" onclick="clearForm()" type="button">&nbsp;&nbsp;重置&nbsp;&nbsp;</button>
					</td>
				  </tr>
				</table>
				<div class="clear"></div>
			</div>
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
	    
		<table s id="template_table" class="table" width="860" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
			 <td width="140">
			 	司机姓名
			 </td>
			 <td width="70">
			 	司机电话
			 </td>
			 <td>
				网点
			 </td>	 
			 <td>
				车牌名称
			 </td>	 
			 <td>
				状态
			 </td>	 
			 <td width="80">&nbsp;</td>
		  </tr>
		 </thead>	
		 <tbody>
		 	<s:iterator value="#request.page.result" id="item" status="i">
			  <tr id="${item.csdcId}" >
				<td ref="driverName" class=" node">
					${item.$csdcDriver.csdName}
				</td>
				<td ref="driverMobile" class=" node">
				 	${item.$csdcDriver.csdMobile}
				</td>
				<td ref="outletsName" class="">
				 	 ${item.$csdcOutlets.csoName}
				</td>
				<td ref="carNo" class="">
				 	 ${item.$csdcCar.cscrCarNo}
				</td>
				<td ref="state" class="">
					 ${item.csdcState$}
				</td>
				<td>
					<a href="javascript:selectTemplate('${item.csdcId}')">选择</a>
				</td>
			  </tr>
		 </s:iterator>
		 </tbody>			 
	  </table>
	  
  </div>
  <%@include file="/admin/module/page.jsp"%>
  
 </td></tr></table>

<script>
	
	showTips("${tips.value}");
	
	function selectTemplate(id){
		var name = $("#"+id+" td[ref=driverName]").text();
		var mobile = $("#"+id+" td[ref=driverMobile]").text();
		window.caller.$("#csscDriverPhone").val($.trim(name)+"("+$.trim(mobile)+")");
		window.caller.$("#csscDriver").val($.trim(id));
		$.closeModalDialog();
	}

</script>    

</body>
</html>
