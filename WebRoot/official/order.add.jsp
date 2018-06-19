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
		href("${basePath}official/car.do",{select:true});
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

<form class="form editform" ref="CsUnitOrder" id="orderForm" name="orderForm" action="order.save.do" method="post">

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
					
		${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitOrder.csuoId))}		
		${lz:set("haveEditable",false)}
		${lz:set("havePrimary",false)}			 	
		
		
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoType">
			<dt>用车类型:</dt>
			<dd input="select">			 	
		 	 	<select class="narrow" id="csuoType" name="csUnitOrder.csuoType">
			 		<option value="">请选择</option>
					<option value="1" ${csUnitOrder.csuoType=="1"?"selected":""}>一般公务</option>
					<option value="2" ${csUnitOrder.csuoType=="2"?"selected":""}>紧急公务</option>
			 	</select>
		 	 
		 	 <em>请选择公务用车用途</em>
			</dd>
		</dl>
			
																			
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoUsername">
			<dt>用车人员:</dt>
			<dd input="query">			 				 	
		 		<!-- <input title="使用人" class="combox narrow" type="text" id="csuoUsername" name="csUnitOrder.csuoUsername" text="${get:CsCar(csOrder.csoCar).cscNumber}" value="${csUnitOrder.csuoCar}" /> 					 			
		 	 	<input title="请输入真实姓名进行查询" class="combox narrow" action="http://localhost:8080/official/person_query.do?value={param}&csmpHost={csuoHost}" type="text" id="csuoUsername" name="csUnitOrder.csuoUsername" text="${get:CsUnitPerson(csUnitOrder.csuoUsername).csupName}" value="${csUnitOrder.csuoUsername}" /> -->	
		 		
		 	 	<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/person/person_inquire.do?value={param}" type="text" id="csupId" name="csUnitPerson.csupId" text="${get:CsUnitPerson(csUnitOrder.csuoUsername).csupName}" value="${csUnitPerson.csupId}" />
		 	 <b>*</b>
		 	 <em>请输入用车人员</em>
			</dd>
		</dl>
		<!-- 
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csupGroup">
			<dt>订单车辆:</dt>
					
				<select class="narrow" id="csupGroup" name="csUnitPerson.csupGroup">
					<option value="">请选择</option>			 					 					 		
			 		<s:iterator value="#request.csUnitGroups" id="item" status="csUnitGroup">
			 		<option value="${item.csugId}">${item.csugName}</option>
					</s:iterator>
					
			 	</select>
		 	 <b>*</b>
		 	 <em>请选择所属部门信息</em>
		</dl>
			 -->
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoCar">
			<dt>订单车辆:</dt>
			<dd input="query">			 				 	
		 		<input title="请输入车辆车牌号进行查询" class="combox narrow" action="${basePath}official/car.do?value={param}&unit={csuoUnitInfo}&cscHost={csuoHost}" type="text" id="csuoCar" name="csUnitOrder.csuoCar" text="${get:CsCar(csUnitOrder.csuoCar).cscNumber}" value="${csUnitOrder.csuoCar}" />	 					 			
		 	 	
		 	 <b>*</b>
		 	 <em></em>
			</dd>
		</dl>					
			
			
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csuoMobile" >
			<dt>使用人手机:</dt>
			<dd input="text">
			 	<input type="text" class="input narrow"  maxlength="32" name="csUnitOrder.csuoMobile" id="csuoMobile"  value="${csUnitOrder.csuoMobile}"/>
		 	 
		 	  <b>*</b>
		 	 <em>请输入企业订单的使用人手机</em>
			</dd>
		</dl>		
			
	
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoStartTime" >
			<dt>订单开始时间:</dt>
			<dd input="datetime">
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitOrder.csuoStartTime" id="csuoStartTime"  value="<s:date name="csUnitOrder.csuoStartTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 
		 	 <b>*</b>
		 	 <em>请选择企业订单的订单开始时间</em>
			</dd>
		</dl>
		
		

		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoFinishTime" >
			<dt>订单结束时间:</dt>
			<dd input="datetime">
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitOrder.csuoFinishTime" id="csuoFinishTime"  value="<s:date name="csUnitOrder.csuoFinishTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 
		 	 <b>*</b>
		 	 <em>请选择企业订单的订单结束时间</em>
			</dd>			
		</dl>
		
		
		<!-- 
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoFinishTime" >
			<dt>订单续订结束时间:</dt>
			<dd input="datetime">
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitOrder.csuoFinishTime" id="csuoFinishTime"  value="<s:date name="csUnitOrder.csuoFinishTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 
		 	 <b>*</b>
		 	 <em>请选择企业订单的续订结束时间</em>
			</dd>			
		</dl>					
	 	-->
		
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csuoProfile">
			<dt>公务用车用途:</dt>
		 	 <dd input="select">			 	
		 	 	<select class="narrow" id="csuoProfile" name="csUnitOrder.csuoProfile">
			 		<option value="">请选择</option>
					<option value="机要任务" ${csUnitOrder.csuoProfile=="机要任务"?"selected":""}>机要任务</option>
					<option value="应急勤务" ${csUnitOrder.csuoProfile=="应急勤务"?"selected":""}>应急勤务</option>
					<option value="文件交换" ${csUnitOrder.csuoProfile=="文件交换"?"selected":""}>文件交换</option>
					<option value="跨区交通" ${csUnitOrder.csuoProfile=="跨区交通"?"selected":""}>跨区交通</option>
			 	</select>
		 	 
		 	 <em>请选择公务用车用途</em>
			</dd>
		</dl>
		
			
	
		<%-- <dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csuoStatus" >
			<dt>状态:</dt>
			<dd input="select">
			 	<select class="narrow" id="csuoStatus" name="csUnitOrder.csuoStatus">
			 		<option value="">请选择</option>
					<option value="0" ${csUnitOrder.csuoStatus==0?"selected":""}>已下单</option>
					<option value="1" ${csUnitOrder.csuoStatus==1?"selected":""}>已完成</option>
					<option value="2" ${csUnitOrder.csuoStatus==2?"selected":""}>已取消</option>
			 	</select>
	 		 <em>请选择企业订单的状态</em>
			</dd>
		</dl> --%>
		
		
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csoRemark" style="width:98%;">
			<dt>备注:</dt>
		 	 <dd input="textarea">			 	
		 	 	<textarea class="input wide"  id="csoRemark" name="csOrder.csoRemark" rows="5">${csOrder.csoRemark}</textarea> 
			</dd>
		</dl>

	
		
		
		<div class="line"></div>
		<center class="buttons">			
			<s:if test="#request.havePrimary==false">
			<input type="hidden" value="${csUnitOrder.csuoId}" name="csUnitOrder.csuoId" id="csuoId" />
			</s:if>			
			<button class="button" type="submit" name="submiter" value="完成">
				&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
				完成&nbsp;&nbsp;</button>
			<button class="button" type="submit" name="submiter" value="保存">
					&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778030.png"/>
					保存&nbsp;&nbsp;</button>
			<button class="button" type="reset">
				&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778045.png"/>
				重置&nbsp;&nbsp;</button>			
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
	
	/*function href(url,params){
		if(${controller.mutual=="dialog"})
			top.$.showModalDialog({url:url},params,window);
		else if(${controller.mutual=="page"})
			$.submit(url,params,"_self");
		else if(true)
			top.$.showModalDialog({url:url},params,window);
		else
			$.submit(url,params,"_self");	
	}*/
	
	function href(url,params){
		top.$.showModalDialog({url:url},params,window);
	}
</script>


<!------------------------LAZY3Q_JSP_BOTTOM------------------------>
<!------------------------LAZY3Q_JSP_BOTTOM------------------------>


</body>
</html>