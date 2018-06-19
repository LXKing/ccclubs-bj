<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>价格配置管理</title>
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
<script>
	$(function(){
		$(".pager li:contains('导出')").remove();
	});	
</script>
<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td>

<div class="title">价格配置管理</div>
	
</td></tr><tr><td>
  <%@include file="/admin/module/page.jsp"%>
  	
  			
	<div class="content">
	    <div class="toolbar">
	    	<!-- 
			<button type="button" class="del" onclick="DelSel();">
				删除
			</button>	
			 -->		
			<button type="button" class="add" onclick="AddFee();">
				添加
			</button>			
		</div>
		
		<table id="fee_table" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>			
			 <td  ${all?"width":"refer"}="200" title="名称">名称</td>			 
			 <td  ${all?"width":"refer"}="120" title="车型">车型</td>			 
			 <td  ${all?"width":"refer"}="120" title="天租价格">天租价格</td>
			 <td  ${all?"width":"refer"}="120" title="小时价格">小时价格</td>
			 <td  ${all?"width":"refer"}="120" title="里程价格">里程价格</td>			
			 <td width="130">
				<a href="javascript:AddFee()" style="text-decoration:underline">添加</a>
			 </td>
		  </tr>
		 </thead>	
		 <tbody>
		 	<s:iterator value="#request.page.result" id="item" status="i">
		  <tr id="${item.csufId}" >	  	
			<td ref="csufName">${item.csufName}</td>			
			<td ref="csufModel" class="" relate="${item.csufModel}">${get:CsCarModel(item.csufModel).cscmName}</td>
			<td ref="csufDayPrice" class="">${item.csufDayPrice}</td>			
			<td ref="csufHourPrice" class="">${item.csufHourPrice}</td>
			<td ref="csufHourPrice" class="">${item.csufMileage}</td>			
			<td>
				<a href="javascript:EditFee('${item.csufId}')">修改</a>
				<a href="javascript:DelFee('${item.csufId}','${item.csufName}')">删除</a>
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
	
	/**
	* 添加价格配置
	**/
	function AddFee(parent){		
		var url = "${basePath}unit/fee.edit.do";
		var params = {};
		href(url,params);
	}
	/**
	* 修改价格配置
	**/
	function EditFee(id){
		var url = "${basePath}unit/fee.edit.do";
		var params = {id:id};
		href(url,params);
	}
	/**
	* 删除价格配置
	**/
	function DelFee(id,flag)
	{
		var dialog=$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除价格配置["+flag+"]吗？</span>",
				title:"删除价格配置",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}unit/fee.del.do";
					var params = {id:id};
					$.submit(url,params);
				}
		 });	
	}	
</script>    

</body>
</html>