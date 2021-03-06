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
	<meta name="author" content="sunny_tam">
	<!-- 感谢jquery提供的前端js库 -->
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	
	<!-- lazy3q提供的系统js -->
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" />	
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义css样式 -->
	<script type="text/javascript" src="${basePath}/admin/css/define.css"></script>
	
	<script type="text/javascript">
		${lz:set("proname","official")}
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
<!------------------------LAZY3Q_JSP_BODY------------------------>




<script>
$(function(){
	$("<div class='timeline'></div>").hide().insertAfter("dl[ref=csuoCar]");
	$("form dl[ref=csuoCar] dd .combox .face").unbind("click").click(function(){
		href("${basePath}unit/car.do",{select:true});
	});
});
function onSelectCar(id,name){
	$("form dl[ref=csuoCar] dd .combox .face span").html(name);	
	$("#csuoCar").val(id).change();
}
</script>


<script>

$(function(){	
	showTips("${tips.value}");
});

$(function(){
		 
	$("#orderForm").form({
		"":function(){}
			 				
		
		,"csUnitOrder.csuoId":function(el){
		}			 				

		,"csUnitOrder.csuoHost":function(el){
		}
			 				
		
		,"csUnitOrder.csuoOrder":function(el){
		}
			 				
		,"csUnitOrder.csuoUnitInfo":function(el){
		}
	
		,"csUnitOrder.csuoUnitMember":function(el){
		}
			 			
		,"csUnitOrder.csuoUnitChild":function(el){
		}
			 				
		,"csUnitOrder.csuoCar":function(el){
		}
			 				
			 				
		,"csUnitOrder.csuoUsername":function(el){
			if(el.value.length>32)
				return "使用人姓名最大长度为32个字符";
		}			 				
			 				
		,"csUnitOrder.csuoMobile":function(el){
			if(el.value.length>32)
				return "使用人手机最大长度为32个字符";
		}			 				
			 					
/*			 				
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
		</s:if>	*/			 				
		,"csUnitOrder.csuoStartTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "订单开始时间不能为空";
		}
			 				
		,"csUnitOrder.csuoFinishTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "订单时长不能为空";
		}			 							 				
			 						
			 				
		,"csUnitOrder.csuoRemark":function(el){
			if(el.value.length>256)
				return "订单备注最大长度为256个字符";
		}
			 				
		,"csUnitOrder.csuoStatus":function(el){
		}
	},function(){//表单提交自定义判断
		/******************************LAZY3Q_FORM_VALIDATE******************************/
		/******************************LAZY3Q_FORM_VALIDATE******************************/

	});		
});
</script>

<form class="form editform" ref="CsUnitOrder" id="orderForm" name="orderForm" action="order.settlement.do" method="post">

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
		<input type="hidden" value="${id}" name="id" id="id" />		
		<input type="hidden" value="${edittype}" name="edittype" id="edittype" />		
		<input type="hidden" value="${lz:html(lz:json(controller))}" id="controller" name="controller" />
		<input type="hidden" value="${csUnitOrderToken}" name="csUnitOrderToken" id="csUnitOrderToken" />
		<input type="hidden" value="${canVisible}" name="canVisible" id="canVisible"/>			
		${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitOrder.csuoId))}		
		${lz:set("haveEditable",false)}
		${lz:set("havePrimary",false)}
		
		<s:if test="#request.canVisible==4">
		<dl class="${(canEditable && true)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoId" >
			<dt>编号:</dt>
			<dd input="hidden">
				<input type="hidden" value="${csUnitOrder.csuoId}" name="csUnitOrder.csuoId" id="csuoId" />	 	 
		 	 <em></em>
			</dd>
		</dl>
		</s:if>				 	
			
																			
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoUsername">
			<dt>用车人员:</dt>
			<dd>
			<div class="state-input narrow">
			 		<input type="hidden" readOnly="true" value="${csUnitOrder.csuoUsername}" id="csuoUsername" />
			 		<span>
				${csUnitOrder.csuoUsername}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
		 	 <dd>
		</dl>
		
		
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoCar">
			<dt>订单车辆:</dt>
			<dd>			
			<div class="state-input narrow">
			 		<input type="hidden" readOnly="true" value="${csUnitOrder.csuoCar}" id="csuoCar" />
			 		<span>
				${csUnitOrder.$csuoCar.cscNumber$}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
		 	 </dd>
		</dl>					
			
			
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csuoMobile" >
			<dt>使用人手机:</dt>
			<dd>
			<div class="state-input narrow">
			 		<input type="hidden" readOnly="true" value="${csUnitOrder.csuoMobile}" id="csuoMobile" />
			 		<span>
				${csUnitOrder.csuoMobile}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
		 	 </dd>
		</dl>		
			
	
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoStartTime" >
			<dt>订单开始时间:</dt>
			<dd>
			<div class="state-input narrow">
			 		<input type="hidden" readOnly="true" value="${csUnitOrder.csuoStartTime}" id="csuoStartTime" />
			 		<span>
				${csUnitOrder.csuoStartTime$}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
		 	 </dd>
		</dl>
		
		

		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoFinishTime" >
			<dt>订单结束时间:</dt>
			<dd>
			<div class="state-input narrow">
			 		<input type="hidden" readOnly="true" value="${csUnitOrder.csuoFinishTime}" id="csuoOldFinishTime" />
			 		<span>
				${csUnitOrder.csuoFinishTime$}
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
		 	 </dd>		
		</dl>

		

		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="hour" >
			<dt>订单时长:</dt>
			<dd>
			<div class="state-input narrow">
				<input type="hidden" readOnly="true" value="${info.hour}" id="hour" />
			 	<span>
			 	<s:if test="#request.day>0">${ day}天</s:if><s:if test="#request.hour>0">${ hour}小时</s:if>
			 	</span>		 	
			</div>
			</dd>			
		</dl>

		

		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoDayPrice" >
			<dt>天租价格</dt>
			<dd>
			 	<div class="state-input narrow">
				<input type="hidden" readOnly="true" value="${csUnitOrder.csuoDayPrice}" id="csuoDayPrice" />
			 	<span>
			 	${csUnitOrder.csuoDayPrice$}元/每天
			 	</span>		 	
			</div>	 	
			</dd>			
		</dl>
		
		
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoHourPrice" >
			<dt>小时租价格</dt>
			<dd>
			 	<div class="state-input narrow">
				<input type="hidden" readOnly="true" value="${csUnitOrder.csuoHourPrice}" id="csuoHourPrice" />
			 	<span>
			 	${csUnitOrder.csuoHourPrice$}元/每小时
			 	</span>		 	
			</div>	 	
			</dd>			
		</dl>
		
		
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoMileage" >
			<dt>总里程（公里）</dt>
			<dd>
			 	<div class="state-input narrow">
				<input type="hidden" readOnly="true" value="${csUnitOrder.csuoMileage}" id="csuoMileage" />
			 	<span>
			 		${csUnitOrder.csuoMileage$}
			 	</span>		 	
			</div>	 	
			</dd>			
		</dl>
		
		
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoKmPrice" >
			<dt>油里程价格</dt>
			<dd>
			 	<div class="state-input narrow">
				<input type="hidden" readOnly="true" value="${csUnitOrder.csuoKmPrice}" id="csuoKmPrice" />
			 	<span>
			 	${csUnitOrder.csuoKmPrice$}元/每公里
			 	</span>		 	
			</div>	 	
			</dd>			
		</dl>

		
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="total" >
			<dt>订单总价（元）</dt>
			<dd>
			 	<div class="state-input narrow">
				<input type="hidden" readOnly="true" value="${info.total}" id="total" />
			 	<span>
			 		${total}
			 	</span>		 	
			</div>	 	
			</dd>			
		</dl>
					
		
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoState" >
			<dt>审核状态:</dt>
			<dd>
			<div class="state-input narrow">
			 		<input type="hidden" readOnly="true" value="${csUnitOrder.csuoState}" id="csuoState" />
			 		<span>
					${csUnitOrder.csuoState==0?"无需审核":""}	
					${csUnitOrder.csuoState==1?"等待审核":""}
				 	${csUnitOrder.csuoState==2?"审核已通过":""}	
				 	${csUnitOrder.csuoState==3?"审核不通过":""}		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	</div>
		 </dd>
		</dl>		
		
		
		
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csuoProfile" style="width:98%;">
			<dt>公务车用途:</dt>
			<dd>
			<input type="hidden" readOnly="true" value="${csUnitOrder.csuoProfile}" id="csuoProfile" />
			 		<span>
			 		${csUnitOrder.csuoProfile}	
					
			 		</span>
			 </dd>
		</dl>
		
		
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csuoRemark" style="width:98%;">
			<dt>订单备注:</dt>
			<dd input="textarea">
			 	<textarea class="input wide"  id="csuoRemark" name="csUnitOrder.csuoRemark" rows="5">${csUnitOrder.csuoRemark}</textarea>		 	 
		 	 <em>请输入企业订单的订单备注</em>
			</dd>
		</dl>
		
		

	
		
		
		<div class="line"></div>
		<s:if test="#request.canVisible==1">
		<center class="buttons">	
			<s:if test="#request.havePrimary==false">
			<input type="hidden" value="${csUnitOrder.csuoId}" name="csUnitOrder.csuoId" id="csuoId" />
			</s:if>			
			<button class="button" type="submit" name="submiter" value="确定">
				&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
				结算&nbsp;&nbsp;</button>	
			<button class="button" onclick="cancel();" type="button">
				&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-multiply.png"/>
				取消&nbsp;&nbsp;</button>
		</center>
		</s:if>
		<s:elseif test="#request.canVisible==2">
		<center class="buttons">	
			<s:if test="#request.havePrimary==false">
			<input type="hidden" value="${csUnitOrder.csuoId}" name="csUnitOrder.csuoId" id="csuoId" />
			</s:if>			
			<button class="button" onclick="cancel();" type="button" value="确定">
				&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
				确定&nbsp;&nbsp;</button>	
			<button class="button" onclick="cancel();" type="button">
				&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-multiply.png"/>
				取消&nbsp;&nbsp;</button>
		</center>
		</s:elseif>
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
		top.$.showModalDialog({url:url},params,window);
	}
</script>

</body>
</html>