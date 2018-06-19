<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>短信模版选择</title>
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


<div class="title">短信模版管理 - 短信模版选择</div>

<div class="line"></div>
<div class="content">
	<form class="query" id="queryForm" action="template_select.do" method="post">


			<dl>
				<dt>短信类型:</dt>
				<dd>
		 		<select id="csstType" name="csstType" style="width:100px;">
		 			<option value="">全部</option>
						    ${lz:set("items", lz:query$("ccclubs_system","select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)" ,'SMS_TEMP_TYPE') )}
							<s:iterator value="#request.items" id="item" status="i">
							<option value="${item.sp_id}" ${csstType==item.sp_id?"selected":""}>${item.sp_name}</option>
							</s:iterator>
							${lz:set("items",null)}
		 		</select>
		 		<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csstType').val())==''){return;};window.href('${basePath}admin/configurator/property_details.do?id='+$('#csstType').val(),{ctrl:{editable:false}})"></a>
				</dd>
			</dl>
	
			<dl>
				<dt>模版名称:</dt>
				<dd>
		 			<input type="text" class="input" size="16"  maxlength="32" name="csstName" id="csstName"  value="${csstName}"/>
				</dd>
			</dl>
			
			<dl>
				<dt>模版内容:</dt>
				<dd>
		 			<input type="text" class="input" size="16"  maxlength="1024" name="csstContent" id="csstContent"  value="${csstContent}"/>
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
			 	<a class="${desc=="csst_name" ? "desc" : ""}${asc=="csst_name" ? "asc" : ""}" href="?${desc=="csst_name" ? "asc=csst_name" : ""}${(asc=="csst_name" || desc!="csst_name" )? "desc=csst_name" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	模版名称
			 	</a>
			 </td>
			 <td width="70">
			 	<a class="${desc=="csst_type" ? "desc" : ""}${asc=="csst_type" ? "asc" : ""}" href="?${desc=="csst_type" ? "asc=csst_type" : ""}${(asc=="csst_type" || desc!="csst_type" )? "desc=csst_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	短信类型
			 	</a>
			 </td>
			 <td>
				短信内容
			 </td>	 
			 <td width="80">&nbsp;</td>
		  </tr>
		 </thead>	
		 <tbody>
		 	<s:iterator value="#request.page.result" id="item" status="i">
		  <tr id="${item.csstId}" >
			<td ref="csstName" class=" node">
					 ${item.csstName}
			</td>
			<td ref="csstType" class="" value="${item.csstType}">
			 	 ${item.csstType==1?"代码类短信":""}
				 ${item.csstType==2?"通知类短信":""}
				 ${item.csstType==3?"提示类短信":""}
				 ${item.csstType==4?"广告类短信":""}
			</td>
			<td ref="csstContent" class="">
				 ${item.csstContent}
			</td>
			<td>
				<a href="javascript:selectTemplate('${item.csstId}')">选择</a>
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
		var content = $("#"+id+" td[ref=csstContent]").text();
		window.caller.$("#cssmContent").val($.trim(content));
		var type = $("#"+id+" td[ref=csstType]").attr("value");
		window.caller.$("#cssmType").val(type);
		$.closeModalDialog();
	}

</script>    

</body>
</html>
