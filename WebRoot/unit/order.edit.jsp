<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("projectpath","admin/unit/order.do")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty controller.title?"企业订单编辑":controller.title}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	
	<!-- 感谢jquery提供的前端js库 -->
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	
	<!-- lazy3q提供的系统js -->
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" />	
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义css样式 -->
	<script type="text/javascript" src="${basePath}/admin/css/define.css"></script>
	
	<script type="text/javascript">
		${lz:set("proname","unit")}
		window.basePath="${basePath}";
		window.contextPath="${basePath}${proname}/";
		window.apiUrl="${basePath}${proname}/api.do";
	</script>
		
	<!-- 感谢kindeditor出品的在线HTML编辑控件 -->
	<script type="text/javascript" charset="utf-8" src="${basePath}admin/js/kindeditor/kindeditor.js"></script>
	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>
	
	<!------------------------LAZY3Q_JSP_HEAD------------------------>
	<!------------------------LAZY3Q_JSP_HEAD------------------------>

</head>  
<body ${(controller.mutual=="page"||(controller.mutual!="dialog" && false))?"class='pagetype'":""}>
<!------------------------LAZY3Q_JSP_BODY------------------------>
${lz:set("canAdd",true)}${lz:set("canEdit",true)}
<%@ include file="order.public.jsp"%>
<s:if test="#request.controller.title=='公务用车'||#request.controller.title=='紧急公务'">
	<%@ include file="order.add.jsp"%>
</s:if>
<s:elseif test="#request.controller.title=='内部用车'">
	<%@ include file="order.add.jsp"%>
</s:elseif>
<s:elseif test="#request.controller.title=='续订'">
	<%@ include file="order.reorder.jsp"%>
</s:elseif>
<s:elseif test="#request.controller.title=='取消'">
	<%@ include file="order.cancel.jsp"%>
</s:elseif>
<s:elseif test="#request.controller.title=='结算'">
	<%@ include file="order.settle.jsp"%>
</s:elseif>
<s:elseif test="#request.controller.title=='还车'">
	<%@ include file="order.ret.jsp"%>
</s:elseif>
<s:elseif test="#request.controller.title=='详情'">
	<%@ include file="order.detail.jsp"%>
</s:elseif>
<s:elseif test="#request.controller.title=='审核'">
	<%@ include file="order.check.jsp"%>
</s:elseif>
<!------------------------LAZY3Q_JSP_BODY------------------------>

<script>

$(function(){	
	showTips("${tips.value}");
});

window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "admin/upload/upload.jsp" :  lz:config("upload.url")}";

$(function(){
		 
	${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitOrder.csuoId))}
	${lz:set("haveEditable",false)}
	$("#orderForm").form({
		"":function(){}
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoId',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoId":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoHost',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoHost":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoOrder',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoOrder":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoUnitInfo',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoUnitInfo":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoUnitMember',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoUnitMember":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoUnitChild',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoUnitChild":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoCar',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoCar":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoType',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择订单类型";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoUsername',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoUsername":function(el){
			if(el.value.length>32)
				return "使用人姓名最大长度为32个字符";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoAlias',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoAlias":function(el){
			if(el.value.length>32)
				return "别名或标记最大长度为32个字符";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoMobile',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoMobile":function(el){
			if(el.value.length>32)
				return "使用人手机最大长度为32个字符";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoIdcard',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoIdcard":function(el){
			if(el.value.length>32)
				return "身份证号最大长度为32个字符";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoDayPrice',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoDayPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "天租价格输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoHourPrice',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoHourPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "小时价格输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoKmPrice',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoKmPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "里程价格输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoStartTime',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoStartTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "订单开始时间不能为空";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoFinishTime',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoFinishTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "订单结束时间不能为空";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoRetTime',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoRetTime":function(el){
		}
		</s:if>	
			
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoHour',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoHour":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "小时数输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoFreeHour',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoFreeHour":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoMileage',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoMileage":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "里程数输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoFreeKm',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoFreeKm":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "免费里程输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoPayNeed',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoPayNeed":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "应付金额输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoPayReal',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoPayReal":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "实付金额输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoRemark',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoRemark":function(el){
			if(el.value.length>256)
				return "订单备注最大长度为256个字符";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csuoStatus',(true && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csUnitOrder.csuoStatus":function(el){
		}
		</s:if>	
	},function(){//表单提交自定义判断
		/******************************LAZY3Q_FORM_VALIDATE******************************/
		/******************************LAZY3Q_FORM_VALIDATE******************************/

	});		
});
</script>

<form class="form editform" ref="CsUnitOrder" id="orderForm" name="orderForm" action="${empty controller.action?"order_save.do":controller.action}" method="post">

<div class="head"></div>
<div class="body">
	<div class="content">	
	
		<s:if test="#request.haveEditable==true">
		<div class="prompt">
			温馨提示：请仔细填写订单相关信息，<span class="extrude">"*" 为必填选项。</span>			
			<input type="checkbox" ${empty edittype?"checked='checked'":""} id="all"/>显示全部可编辑项
		</div>
		</s:if>
				
		${lz:set("empty",lz:vacant(message))}
		<s:if test="#request.empty!=true">
		<br/>
		<center>
			<font color="red"><h4>${message}</h4></font>
		</center>
		<br/>
		</s:if>
		
		<input type="hidden" value="${editorpoint}" id="editorpoint" name="editorpoint" />	
		<input type="hidden" value="${entrypoint}" id="entrypoint" name="entrypoint" />
		<input type="hidden" value="${complete}" name="complete" id="complete" />
		<input type="hidden" value="${ids}" name="ids" id="ids" />		
		<input type="hidden" value="${edittype}" name="edittype" id="edittype" />		
		<input type="hidden" value="${lz:html(lz:json(controller))}" id="controller" name="controller" />
		<input type="hidden" value="${csUnitOrderToken}" name="csUnitOrderToken" id="csUnitOrderToken" />
					
		${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitOrder.csuoId))}		
		${lz:set("haveEditable",false)}
		${lz:set("havePrimary",false)}
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csuoId',(true && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csuoId',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && true)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoId" >
			<dt>编号:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			${lz:set("havePrimary",true)}
			<dd input="hidden">
				<input type="hidden" value="${csUnitOrder.csuoId}" name="csUnitOrder.csuoId" id="csuoId" />
		 	 
		 	 
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitOrder.csuoId}" id="csuoId" />
			 		<span>
			 	
				 ${csUnitOrder.csuoId$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		
		${lz:set("canEditable",lz:editableEx(controller,'csuoOrder',(true && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csuoOrder',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoOrder" >
			<dt>系统订单:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="query">
			 			<input title="请输入订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={csuoHost}" type="text" id="csuoOrder" name="csUnitOrder.csuoOrder" text="${get:CsOrder(csUnitOrder.csuoOrder).csoId}" value="${csUnitOrder.csuoOrder}" />
			 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuoOrder').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?id='+$('#csuoOrder').val(),{controller:{editable:false}})"></a>
		 	 
		 	 <b>*</b>
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitOrder.csuoOrder}" id="csuoOrder" />
			 		<span>
			 	
				 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?id=${csUnitOrder.csuoOrder}',{controller:{editable:false,visible:true}})">
				 ${csUnitOrder.csuoOrder$}</a>
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
		
		
		
		${lz:set("canEditable",lz:editableEx(controller,'csuoType',(true && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csuoType',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoType" >
			<dt>订单类型:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="select">
			 	<select class="narrow" id="csuoType" name="csUnitOrder.csuoType">
			 		<option value="">请选择</option>
					<option value="1" ${csUnitOrder.csuoType==1?"selected":""}>一般公务</option>
					<option value="2" ${csUnitOrder.csuoType==2?"selected":""}>紧急公务</option>
			 	</select>
		 	 
		 	 <b>*</b>
		 	 <em>请选择企业订单的订单类型</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitOrder.csuoType}" id="csuoType" />
			 		<span>
			 	
				 ${csUnitOrder.csuoType$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
			
	
		${lz:set("canEditable",lz:editableEx(controller,'csuoProfile',(true && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csuoProfile',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoProfile" >
			<dt>订单摘要</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="select">
			 	<select class="narrow" id="csuoProfile" name="csUnitOrder.csuoProfile">
			 		<option value="">请选择</option>
					<option value="机要任务" ${csUnitOrder.csuoProfile=="机要任务"?"selected":""}>机要任务</option>
					<option value="应急勤务" ${csUnitOrder.csuoProfile=="应急勤务"?"selected":""}>应急勤务</option>
					<option value="文件交换" ${csUnitOrder.csuoProfile=="文件交换"?"selected":""}>文件交换</option>
					<option value="跨区交通" ${csUnitOrder.csuoProfile=="跨区交通"?"selected":""}>跨区交通</option>
			 	</select>
		 	 <b>*</b>
		 	 <em>请选择企业订单的订单摘要</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitOrder.csuoProfile}" id="csuoProfile" />
			 		<span>
						${csUnitOrder.csuoProfile$}
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csuoUnitChild',(true && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csuoUnitChild',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoUnitChild" >
			<dt>使用帐号:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="query">
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&pid={csuoUnitMember}" type="text" id="csuoUnitChild" name="csUnitOrder.csuoUnitChild" text="${get:CsMember(csUnitOrder.csuoUnitChild).csmName}" value="${csUnitOrder.csuoUnitChild}" />
			 <b>*</b>
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitOrder.csuoUnitChild}" id="csuoUnitChild" />
			 		<span>
			 	
				 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?id=${csUnitOrder.csuoUnitChild}',{controller:{editable:false,visible:true}})">
				 ${csUnitOrder.csuoUnitChild$}</a>
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csuoCar',(true && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csuoCar',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoCar">
			<dt>订单车辆:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="query">
			 			<input title="请输入车辆车牌号进行查询" class="combox narrow" action="${basePath}${proname}/unit/info_car.do?value={param}&unit={csuoUnitInfo}&cscHost={csuoHost}" type="text" id="csuoCar" name="csUnitOrder.csuoCar" text="${get:CsCar(csUnitOrder.csuoCar).cscNumber}" value="${csUnitOrder.csuoCar}" />
			 			
		 	 <b>*</b>
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitOrder.csuoCar}" id="csuoCar" />
			 		<span>
			 	
				 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/car_details.do?id=${csUnitOrder.csuoCar}',{controller:{editable:false,visible:true}})">
				 ${csUnitOrder.csuoCar$}</a>
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csuoUsername',(true && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csuoUsername',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csuoUsername" >
			<dt>使用人姓名:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="text">
			 	<input type="text" class="input narrow"  maxlength="32" name="csUnitOrder.csuoUsername" id="csuoUsername"  value="${csUnitOrder.csuoUsername}"/>
		 	 
		 	  <b>*</b>
		 	 <em>请输入企业订单的使用人姓名</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitOrder.csuoUsername}" id="csuoUsername" />
			 		<span>
			 	
				 ${csUnitOrder.csuoUsername$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csuoAlias',(true && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csuoAlias',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoAlias" >
			<dt>别名或标记:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="text">
			 	<input type="text" class="input narrow"  maxlength="32" name="csUnitOrder.csuoAlias" id="csuoAlias"  value="${csUnitOrder.csuoAlias}"/>
		 	 
		 	 
		 	 <em>请输入企业订单的别名或标记</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitOrder.csuoAlias}" id="csuoAlias" />
			 		<span>
			 	
				 ${csUnitOrder.csuoAlias$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csuoMobile',(true && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csuoMobile',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csuoMobile" >
			<dt>使用人手机:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="text">
			 	<input type="text" class="input narrow"  maxlength="32" name="csUnitOrder.csuoMobile" id="csuoMobile"  value="${csUnitOrder.csuoMobile}"/>
		 	 
		 	  <b>*</b>
		 	 <em>请输入企业订单的使用人手机</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitOrder.csuoMobile}" id="csuoMobile" />
			 		<span>
			 	
				 ${csUnitOrder.csuoMobile$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csuoIdcard',(true && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csuoIdcard',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csuoIdcard" >
			<dt>身份证号:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="text">
			 	<input type="text" class="input narrow"  maxlength="32" name="csUnitOrder.csuoIdcard" id="csuoIdcard"  value="${csUnitOrder.csuoIdcard}"/>
		 	 
		 	 
		 	 <em>请输入企业订单的身份证号</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitOrder.csuoIdcard}" id="csuoIdcard" />
			 		<span>
			 	
				 ${csUnitOrder.csuoIdcard$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csuoDayPrice',(true && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csuoDayPrice',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csuoDayPrice" >
			<dt>天租价格:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="menoy">
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitOrder.csuoDayPrice" id="csuoDayPrice"  value="${csUnitOrder.csuoDayPrice}"/>
		 	 元
		 	 
		 	 <em>请输入企业订单的天租价格</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitOrder.csuoDayPrice}" id="csuoDayPrice" />
			 		<span>
			 	
				 ${csUnitOrder.csuoDayPrice$}
		
		
		 	 元 
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csuoHourPrice',(true && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csuoHourPrice',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csuoHourPrice" >
			<dt>小时价格:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="menoy">
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitOrder.csuoHourPrice" id="csuoHourPrice"  value="${csUnitOrder.csuoHourPrice}"/>
		 	 元
		 	 
		 	 <em>请输入企业订单的小时价格</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitOrder.csuoHourPrice}" id="csuoHourPrice" />
			 		<span>
			 	
				 ${csUnitOrder.csuoHourPrice$}
		
		
		 	 元 
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csuoKmPrice',(true && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csuoKmPrice',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csuoKmPrice" >
			<dt>里程价格:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="menoy">
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitOrder.csuoKmPrice" id="csuoKmPrice"  value="${csUnitOrder.csuoKmPrice}"/>
		 	 
		 	 
		 	 <em>请输入企业订单的里程价格</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitOrder.csuoKmPrice}" id="csuoKmPrice" />
			 		<span>
			 	
				 ${csUnitOrder.csuoKmPrice$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csuoStartTime',(true && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csuoStartTime',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoStartTime" >
			<dt>订单开始时间:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="datetime">
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitOrder.csuoStartTime" id="csuoStartTime"  value="<s:date name="csUnitOrder.csuoStartTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 
		 	 <b>*</b>
		 	 <em>请选择企业订单的订单开始时间</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitOrder.csuoStartTime}" id="csuoStartTime" />
			 		<span>
			 	
				 ${csUnitOrder.csuoStartTime$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csuoFinishTime',(true && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csuoFinishTime',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoFinishTime" >
			<dt>订单结束时间:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="datetime">
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitOrder.csuoFinishTime" id="csuoFinishTime"  value="<s:date name="csUnitOrder.csuoFinishTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 
		 	 <b>*</b>
		 	 <em>请选择企业订单的订单结束时间</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitOrder.csuoFinishTime}" id="csuoFinishTime" />
			 		<span>
			 	
				 ${csUnitOrder.csuoFinishTime$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csuoRetTime',(true && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csuoRetTime',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoRetTime" >
			<dt>实际还车时间:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="datetime">
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitOrder.csuoRetTime" id="csuoRetTime"  value="<s:date name="csUnitOrder.csuoRetTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 
		 	 
		 	 <em>请选择企业订单的实际还车时间</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitOrder.csuoRetTime}" id="csuoRetTime" />
			 		<span>
			 	
				 ${csUnitOrder.csuoRetTime$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		
		${lz:set("canEditable",lz:editableEx(controller,'csuoFreeHour',(true && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csuoFreeHour',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csuoFreeHour" >
			<dt>免费小时:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="number">
					<input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input narrow"  maxlength="8" name="csUnitOrder.csuoFreeHour" id="csuoFreeHour"  value="${csUnitOrder.csuoFreeHour}"/>
		 	 
		 	 
		 	 <em>请输入企业订单的免费小时</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitOrder.csuoFreeHour}" id="csuoFreeHour" />
			 		<span>
			 	
				 ${csUnitOrder.csuoFreeHour$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csuoMileage',(true && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csuoMileage',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csuoMileage" >
			<dt>里程数:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="menoy">
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitOrder.csuoMileage" id="csuoMileage"  value="${csUnitOrder.csuoMileage}"/>
		 	 公里
		 	 
		 	 <em>请输入企业订单的里程数</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitOrder.csuoMileage}" id="csuoMileage" />
			 		<span>
			 	
				 ${csUnitOrder.csuoMileage$}
		
		
		 	 公里 
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csuoFreeKm',(true && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csuoFreeKm',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csuoFreeKm" >
			<dt>免费里程:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="menoy">
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitOrder.csuoFreeKm" id="csuoFreeKm"  value="${csUnitOrder.csuoFreeKm}"/>
		 	 
		 	 
		 	 <em>请输入企业订单的免费里程</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitOrder.csuoFreeKm}" id="csuoFreeKm" />
			 		<span>
			 	
				 ${csUnitOrder.csuoFreeKm$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csuoPayNeed',(true && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csuoPayNeed',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoPayNeed" >
			<dt>应付金额:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="menoy">
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitOrder.csuoPayNeed" id="csuoPayNeed"  value="${csUnitOrder.csuoPayNeed}"/>
		 	 元
		 	 
		 	 <em>请输入企业订单的应付金额</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitOrder.csuoPayNeed}" id="csuoPayNeed" />
			 		<span>
			 	
				 ${csUnitOrder.csuoPayNeed$}
		
		
		 	 元 
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csuoPayReal',(true && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csuoPayReal',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoPayReal" >
			<dt>实付金额:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="menoy">
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitOrder.csuoPayReal" id="csuoPayReal"  value="${csUnitOrder.csuoPayReal}"/>
		 	 元
		 	 
		 	 <em>请输入企业订单的实付金额</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitOrder.csuoPayReal}" id="csuoPayReal" />
			 		<span>
			 	
				 ${csUnitOrder.csuoPayReal$}
		
		
		 	 元 
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csuoRemark',(true && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csuoRemark',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csuoRemark" style="width:98%;">
			<dt>订单备注:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="textarea">
			 	<textarea class="input wide"  id="csuoRemark" name="csUnitOrder.csuoRemark" rows="5">${csUnitOrder.csuoRemark}</textarea>
		 	 
		 	 
		 	 <em>请输入企业订单的订单备注</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input wide">
			 		<span>
			 	
				 ${csUnitOrder.csuoRemark$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csuoStatus',(true && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csuoStatus',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoStatus" >
			<dt>状态:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="select">
			 	<select class="narrow" id="csuoStatus" name="csUnitOrder.csuoStatus">
			 		<option value="">请选择</option>
					<option value="0" ${csUnitOrder.csuoStatus==0?"selected":""}>已下单</option>
					<option value="1" ${csUnitOrder.csuoStatus==1?"selected":""}>已完成</option>
					<option value="2" ${csUnitOrder.csuoStatus==2?"selected":""}>已取消</option>
			 	</select>
		 	 
		 	 
		 	 <em>请选择企业订单的状态</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csUnitOrder.csuoStatus}" id="csuoStatus" />
			 		<span>
			 	
				 ${csUnitOrder.csuoStatus$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
	
		
		
		<div class="line"></div>
		<center class="buttons">			
			<s:if test="#request.havePrimary==false">
			<input type="hidden" value="${csUnitOrder.csuoId}" name="csUnitOrder.csuoId" id="csuoId" />
			</s:if>			
			<s:if test="#request.haveEditable==true">
				<s:if test="#request.controller.submits!=null">
					<s:iterator value="#request.controller.submits" id="submit" status="i">
			<button class="button" type="submit" name="submiter" value="${submit.value}">&nbsp;&nbsp;${submit.name}&nbsp;&nbsp;</button>
					</s:iterator>
				</s:if>
				<s:else>
			<s:if test="#request.complete!=null && #request.complete!=''">
				<!-- 如果调用时传入了complete完成句柄，那么只能提交到本页面，由action控制调用该句柄 -->
				<button class="button" type="submit" name="submiter" value="保存">
					&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778030.png"/>
					保存&nbsp;&nbsp;</button>
			</s:if>
			<s:else>
				<button class="button" type="submit" name="submiter" value="完成">
				&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
				完成&nbsp;&nbsp;</button>
				<button class="button" type="submit" name="submiter" value="保存">
					&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778030.png"/>
					保存&nbsp;&nbsp;</button>
			</s:else>
				</s:else>
			<button class="button" type="reset">
				&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778045.png"/>
				重置&nbsp;&nbsp;</button>
			</s:if>
			<s:else>
			<button class="button" onclick="cancel();" type="button">
				&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
				确定&nbsp;&nbsp;</button>
			</s:else>
			<button class="button" onclick="cancel();" type="button">
				&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-multiply.png"/>
				取消&nbsp;&nbsp;</button>
		</center>
		
		
	</div>	
</div>
<div class="foot"></div>
	
</form>

<script>
	
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
</script>


<!------------------------LAZY3Q_JSP_BOTTOM------------------------>
<!------------------------LAZY3Q_JSP_BOTTOM------------------------>


</body>
</html>