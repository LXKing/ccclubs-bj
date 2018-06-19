<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>公务车选择</title>
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


<div class="title">专车管理 - 外部车辆选择</div>

<div class="line"></div>
<div class="content">
	<form class="query" id="queryForm" action="carres_select.do" method="post">

			<dl>
				<dt>车牌号:</dt>
				<dd>
		 			<input type="text" class="input" size="16"  maxlength="32" name="cscCarNo" id="cscCarNo" value="${cscCarNo}"/>
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
	    
		<table id="car_table" ref="Car" class="table" width="860" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td width="140">
			 	车牌号
			 </td>
			 <td width="140">
			 	车牌名称
			 </td>
			 <td width="70">
			 	车型名称
			 </td>
			 <td width="70">
			 	网点
			 </td>
			 	 
			 <td>
				停放位置
			 </td>
			 <td>
				车辆颜色
			 </td>	 
			 <td>
				状态
			 </td>	 
			 <td width="80">&nbsp;</td>
		  </tr>
		 </thead>	
		 <tbody>
		 	<s:iterator value="#request.page.result" id="item" status="i">
			  <tr id="${item.cscId}" >
			  	<td ref="cscNumber" class=" node">
					${item.cscNumber}
				</td>
				<td ref="cscCarNo" class=" node">
					${item.cscCarNo}
				</td>
				<td ref="cscmName" class=" node">
				 	${item.$cscModel.cscmName}
				</td>
				
				<td ref="csoName" class=" node">
				 	 ${item.cscOutlets$}
				</td>
				<td ref="csoAddress" class="node">
				 	 ${item.$cscOutlets.csoAddress}
				</td>
				<td ref="cscColor" class=" node">
				 	 ${item.cscColor$}
				</td>
				<td ref="state" class=" node">
					 ${item.cscStatus$}
				</td>
				<td>
					<a href="javascript:selectTemplate('${item.cscId}','${item.cscModel}')">选择</a>
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
	
	function selectTemplate(id,cscModel){
		var cscCarNo = $("#"+id+" td[ref=cscCarNo]").text();
		window.caller.$("#cscrCar").val($.trim(id));
		window.caller.$("#cscrCarNo").val($.trim(cscCarNo));
		window.caller.$("#cscrCarmodel").parent().find("span").text($.trim($("#"+id+" td[ref=cscmName]").text()));
		window.caller.$("#cscrCarmodel").val(cscModel);
		window.caller.$("#cscrLocation").val($.trim($("#"+id+" td[ref=csoAddress]").text()));
		window.caller.$("#cscrColor").val($.trim($("#"+id+" td[ref=cscColor]").text()));				
		window.caller.$("#cscrSource").val(1); 
		$.closeModalDialog();
	}

</script>    

</body>
</html>
