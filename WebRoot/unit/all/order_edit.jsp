<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("projectpath","admin/service/order.do")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty controller.title?"订单编辑":controller.title}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	
	<!-- 感谢jquery提供的前端js库 -->
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	
	<!-- lazy3q提供的系统js -->
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" />	
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
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
	${lz:set("canAdd",true)}${lz:set("canEdit",true)}
	${lz:set("proname","unit")}
	<!------------------------LAZY3Q_JSP_HEAD------------------------>

</head>  
<body ${(controller.mutual=="page"||(controller.mutual!="dialog" && false))?"class='pagetype'":""}>


<!--LAZY3Q_JSP_BODY-->
<!--LAZY3Q_JSP_BODY-->

<script>

$(function(){	
	showTips("${tips.value}");
});

window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "admin/upload/upload.jsp" :  lz:config("upload.url")}";

$(function(){
		 
	${lz:set("isAddType",(lz:vacant(ids))&&(empty csOrder.csoId))}
	${lz:set("haveEditable",false)}
	$("#orderForm").form({
		"":function(){}
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoId',(false && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoId":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoHost',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoHost":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoPayMember',(true && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPayMember":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoUseMember',(true && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoUseMember":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoGroup',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoGroup":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoFeeType',(true && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoFeeType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择计费类型";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoArea',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoArea":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择网点区城市";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoOutlets',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoOutlets":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoOutletsRet',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoOutletsRet":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoModel',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoModel":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoCar',(true && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoCar":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoProvid',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoProvid":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoCarNumber',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoCarNumber":function(el){
			if(jQuery.trim(el.value)=="")
				return "车牌号码不能为空";
			if(el.value.length>32)
				return "车牌号码最大长度为32个字符";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoMobile',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoMobile":function(el){
			if(el.value.length>32)
				return "手机号码最大长度为32个字符";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoEvRfid',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoEvRfid":function(el){
			if(jQuery.trim(el.value)=="")
				return "EV序列号不能为空";
			if(el.value.length>32)
				return "EV序列号最大长度为32个字符";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoInsureType',(true && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoInsureType":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoIsAdCar',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoIsAdCar":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoRebate',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoRebate":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "折扣输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoFreehour',(true && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoFreehour":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "免费小时输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoStartTime',(true && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoStartTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "订单开始时间不能为空";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoFinishTime',(true && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoFinishTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "订单结束时间不能为空";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoLongOrder',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoLongOrder":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoLongPrice',(true && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoLongPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "长单价格输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoTakeTime',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoTakeTime":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoRetTime',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoRetTime":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoFuel',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoFuel":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "燃油里程输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoElectric',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoElectric":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "电里程输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoCreditCard',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoCreditCard":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoMarginNeed',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoMarginNeed":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "保证金额输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoPredict',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPredict":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "预计/里程费输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoPayNeed',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPayNeed":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "应付金额输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoPayReal',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPayReal":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "实付金额输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoPayMoney',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPayMoney":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "保证金支付输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoPayCoupon',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPayCoupon":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "现金券支付输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoPayKilom',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPayKilom":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "电里程费输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoPayMileage',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPayMileage":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "油里程费输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoPayRent',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPayRent":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "租金费用输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoPayInsure',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPayInsure":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "免责费用输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoPayTimeout',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPayTimeout":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "超时费用输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoPayDriver',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPayDriver":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "陪驾费用输入格式错误";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoFlag',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoFlag":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoFrom',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoFrom":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择订单来源";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoUpdateTime',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoAddTime',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoEndTime',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoEndTime":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoCode',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoCode":function(el){
			if(jQuery.trim(el.value)=="")
				return "授权码不能为空";
			if(el.value.length>32)
				return "授权码最大长度为32个字符";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoRemark',(false && isAddType)||(true && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoRemark":function(el){
			if(el.value.length>256)
				return "订单备注最大长度为256个字符";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoLog',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoLog":function(el){
			if(el.value.length>512)
				return "历史日志最大长度为512个字符";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoEditor',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoEditor":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoMask',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoMask":function(el){
				var bitValue = 0;
				$("input[ref='csoMask']").each(function(){
					if($(this).is(":checked"))
						bitValue|=$(this).val();
				});
				$("#csoMask").val(bitValue);
			if($("input[name='csOrder.csoMask']:checked").length==0)
				return "程序掩码至少选择一个";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoDeviceVesion',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoDeviceVesion":function(el){
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoState',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoState":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择数据状态";
		}
		</s:if>	
			 				
		${lz:set("canEditable",lz:editableEx(controller,'csoStatus',(false && isAddType)||(false && !isAddType)))}
		<s:if test="#request.canEditable==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择状态";
		}
		</s:if>	
	},function(){//表单提交自定义判断
		/******************************LAZY3Q_FORM_VALIDATE******************************/
		/******************************LAZY3Q_FORM_VALIDATE******************************/

	});		
});
</script>

<form class="form editform" ref="CsOrder" id="orderForm" name="orderForm" action="${empty controller.action?"order_save.do":controller.action}" method="post">

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
		<input type="hidden" value="${csOrderToken}" name="csOrderToken" id="csOrderToken" />
					
		${lz:set("isAddType",(lz:vacant(ids))&&(empty csOrder.csoId))}		
		${lz:set("haveEditable",false)}
		${lz:set("havePrimary",false)}
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoId',(false && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoId',(false && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && true)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csoId" >
			<dt>订单编号:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			${lz:set("havePrimary",true)}
			<dd input="hidden">
				<input type="hidden" value="${csOrder.csoId}" name="csOrder.csoId" id="csoId" />
		 	 
		 	 
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoId}" id="csoId" />
			 		<span>
			 	
				 ${csOrder.csoId$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoUseMember',(true && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoUseMember',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csoUseMember" >
			<dt>使用会员:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="query">
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csoHost}" type="text" id="csoUseMember" name="csOrder.csoUseMember" text="${get:CsMember(csOrder.csoUseMember).csmName}" value="${csOrder.csoUseMember}" />
			 			
		 	 <b>*</b>
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoUseMember}" id="csoUseMember" />
			 		<span>
			 			${csOrder.csoUseMember$}
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		
		${lz:set("canEditable",lz:editableEx(controller,'csoOutlets',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoOutlets',(false && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csoOutlets" >
			<dt>取车网点:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="combox">
			 			<select class="combox narrow" action="${basePath}${proname}/object/outlets_tree.do?parent={param}&csoHost={csoHost}" id="csoOutlets" name="csOrder.csoOutlets">
			 				<option selected value="${csOrder.csoOutlets}">
			 					${get:CsOutlets(csOrder.csoOutlets).csoName}
			 				</option>
			 			</select>
		 	 <b>*</b>
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoOutlets}" id="csoOutlets" />
			 		<span>${csOrder.csoOutlets$}</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoOutletsRet',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoOutletsRet',(false && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csoOutletsRet" >
			<dt>还车网点:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="combox">
			 			<select class="combox narrow" action="${basePath}${proname}/object/outlets_tree.do?parent={param}&csoHost={csoHost}" id="csoOutletsRet" name="csOrder.csoOutletsRet">
			 				<option selected value="${csOrder.csoOutletsRet}">
			 					${get:CsOutlets(csOrder.csoOutletsRet).csoName}
			 				</option>
			 			</select>
		 	 <b>*</b>
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoOutletsRet}" id="csoOutletsRet" />
			 		<span>${csOrder.csoOutletsRet$}</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoModel',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoModel',(false && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csoModel" >
			<dt>预订车型:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="combox">
			 			<select class="combox narrow" action="${basePath}${proname}/object/carmodel_query.do?size=-1" id="csoModel" name="csOrder.csoModel">
			 				<option selected value="${csOrder.csoModel}">
			 					${get:CsCarModel(csOrder.csoModel).cscmName}
			 				</option>
			 			</select>
		 	 <b>*</b>
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoModel}" id="csoModel" />
			 		<span>${csOrder.csoModel$}</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoCar',(true && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoCar',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csoCar" >
			<dt>预订车辆:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="query">
			 			<input title="请输入车辆车牌号进行查询" class="combox narrow" action="${basePath}${proname}/object/car_query.do?value={param}&cscOutlets={csoOutlets}&cscModel={csoModel}&cscHost={csoHost}" type="text" id="csoCar" name="csOrder.csoCar" text="${get:CsCar(csOrder.csoCar).cscNumber}" value="${csOrder.csoCar}" />
			 			
		 	 <b>*</b>
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoCar}" id="csoCar" />
			 		<span>${csOrder.csoCar$}</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		
		${lz:set("canEditable",lz:editableEx(controller,'csoCarNumber',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoCarNumber',(false && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csoCarNumber" >
			<dt>车牌号码:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="text">
			 	<input type="text" class="input narrow"  maxlength="32" name="csOrder.csoCarNumber" id="csoCarNumber"  value="${csOrder.csoCarNumber}"/>
		 	 
		 	 <b>*</b>
		 	 <em>请输入订单的车牌号码</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoCarNumber}" id="csoCarNumber" />
			 		<span>
			 	
				 ${csOrder.csoCarNumber$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoMobile',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoMobile',(false && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csoMobile" >
			<dt>手机号码:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="text">
			 	<input type="text" class="input narrow"  maxlength="32" name="csOrder.csoMobile" id="csoMobile"  value="${csOrder.csoMobile}"/>
		 	 
		 	 
		 	 <em>请输入订单的手机号码</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoMobile}" id="csoMobile" />
			 		<span>
			 	
				 ${csOrder.csoMobile$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		
		${lz:set("canEditable",lz:editableEx(controller,'csoInsureType',(true && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoInsureType',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csoInsureType" >
			<dt>保险类型:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="select">
			 	<select class="narrow" id="csoInsureType" name="csOrder.csoInsureType">
			 		<option value="">请选择</option>
					    ${lz:set("items", lz:query("ccclubs_system","select * from cs_product where csp_flag='INSURE' or csp_flag='SECURE'") )}
						<s:iterator value="#request.items" id="item" status="i">
						<option value="${item.cspId}" ${csOrder.csoInsureType==item.cspId?"selected":""}>${item.cspName}</option>
						</s:iterator>
						${lz:set("items",null)}
			 	</select>
			 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csoInsureType').val())==''){return;};window.href('${basePath}${proname}/fee/product_details.do?id='+$('#csoInsureType').val(),{controller:{editable:false}})"></a>
		 	 
		 	 
		 	 <em>请选择订单的保险类型</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoInsureType}" id="csoInsureType" />
			 		<span>
			 	
				 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/fee/product_details.do?id=${csOrder.csoInsureType}',{controller:{editable:false,visible:true}})">
				 ${csOrder.csoInsureType$}</a>
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoIsAdCar',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoIsAdCar',(false && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csoIsAdCar" >
			<dt>宣传车？:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="select">
			 	<select class="narrow" id="csoIsAdCar" name="csOrder.csoIsAdCar">
			 		<option value="">请选择</option>
					<option value="true" ${csOrder.csoIsAdCar==true?"selected":""}>是</option>
					<option value="false" ${csOrder.csoIsAdCar==false?"selected":""}>否</option>		 		
			 	</select>
		 	 
		 	 
		 	 <em>请选择订单的宣传车？</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoIsAdCar}" id="csoIsAdCar" />
			 		<span>
			 	
				 ${csOrder.csoIsAdCar$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoRebate',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoRebate',(false && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csoRebate" >
			<dt>折扣:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="menoy">
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoRebate" id="csoRebate"  value="${csOrder.csoRebate}"/>
		 	 
		 	 
		 	 <em>0到1,比如0.75表示七五折</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoRebate}" id="csoRebate" />
			 		<span>
			 	
				 ${csOrder.csoRebate$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoFreehour',(true && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoFreehour',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csoFreehour" >
			<dt>免费小时:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="menoy">
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoFreehour" id="csoFreehour"  value="${csOrder.csoFreehour}"/>
		 	 
		 	 
		 	 <em>请输入订单的免费小时</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoFreehour}" id="csoFreehour" />
			 		<span>
			 	
				 ${csOrder.csoFreehour$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoStartTime',(true && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoStartTime',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csoStartTime" >
			<dt>订单开始时间:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="datetime">
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOrder.csoStartTime" id="csoStartTime"  value="<s:date name="csOrder.csoStartTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 
		 	 <b>*</b>
		 	 <em>请选择订单的订单开始时间</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoStartTime}" id="csoStartTime" />
			 		<span>
			 	
				 ${csOrder.csoStartTime$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoFinishTime',(true && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoFinishTime',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csoFinishTime" >
			<dt>订单结束时间:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="datetime">
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOrder.csoFinishTime" id="csoFinishTime"  value="<s:date name="csOrder.csoFinishTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 
		 	 <b>*</b>
		 	 <em>请选择订单的订单结束时间</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoFinishTime}" id="csoFinishTime" />
			 		<span>
			 	
				 ${csOrder.csoFinishTime$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		
		${lz:set("canEditable",lz:editableEx(controller,'csoTakeTime',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoTakeTime',(false && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csoTakeTime" >
			<dt>实际取车时间:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="datetime">
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOrder.csoTakeTime" id="csoTakeTime"  value="<s:date name="csOrder.csoTakeTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 
		 	 
		 	 <em>请选择订单的实际取车时间</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoTakeTime}" id="csoTakeTime" />
			 		<span>
			 	
				 ${csOrder.csoTakeTime$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoRetTime',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoRetTime',(false && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csoRetTime" >
			<dt>实际还车时间:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="datetime">
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOrder.csoRetTime" id="csoRetTime"  value="<s:date name="csOrder.csoRetTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 
		 	 
		 	 <em>请选择订单的实际还车时间</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoRetTime}" id="csoRetTime" />
			 		<span>
			 	
				 ${csOrder.csoRetTime$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoFuel',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoFuel',(false && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csoFuel" >
			<dt>燃油里程:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="menoy">
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoFuel" id="csoFuel"  value="${csOrder.csoFuel}"/>
		 	 
		 	 <b>*</b>
		 	 <em>请输入订单的燃油里程</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoFuel}" id="csoFuel" />
			 		<span>
			 	
				 ${csOrder.csoFuel$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoElectric',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoElectric',(false && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csoElectric" >
			<dt>电里程:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="menoy">
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoElectric" id="csoElectric"  value="${csOrder.csoElectric}"/>
		 	 
		 	 <b>*</b>
		 	 <em>请输入订单的电里程</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoElectric}" id="csoElectric" />
			 		<span>
			 	
				 ${csOrder.csoElectric$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
			
		${lz:set("canEditable",lz:editableEx(controller,'csoCostDetails',(true && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoCostDetails',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csoCostDetails" style="width:98%;">
			<dt>订单明细:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="grid">
			  		<s:if test="csOrder.csoId!=null">
					<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csoCostDetails" name="csoCostDetails" frameborder='0' src="" url="${basePath}${proname}/service/orderdetail.do?canQuery=false&all=true&csodOrder=${csOrder.csoId}&controller={mutual:'dialog',queryable:false,fields:{csodOrder:{visible:true,editable:false,defaultValue:${csOrder.csoId}}}}&size=8" height='0'></iframe>
					<div class="iframe-list">
						<a class="button" style="float:right;" onclick="$('#csoCostDetails').attr('src',$('#csoCostDetails').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
						<div class="line" style="margin:0px;clear:none;width:450px;"></div>
					</div>
					</s:if>
					<s:else>
					<div class="state-input wide">
					完成或保存表单后即可编辑订单明细
					</div>
					</s:else>
		 	 
		 	 
		 	 <em></em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<s:if test="csOrder.csoId!=null">	  		
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csoCostDetails" name="csoCostDetails" frameborder='0' src="" url="${basePath}${proname}/service/orderdetail.do?canQuery=false&all=true&csodOrder=${csOrder.csoId}&controller={mutual:'dialog',canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
					<div class="iframe-list">
						<a class="button" style="float:right;" onclick="$('#csoCostDetails').attr('src',$('#csoCostDetails').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
						<div class="line" style="margin:0px;clear:none;width:450px;"></div>
					</div>
				</s:if>			
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoMarginNeed',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoMarginNeed',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csoMarginNeed" >
			<dt>保证金额:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="menoy">
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoMarginNeed" id="csoMarginNeed"  value="${csOrder.csoMarginNeed}"/>
		 	 
		 	 
		 	 <em>请输入订单的保证金额</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoMarginNeed}" id="csoMarginNeed" />
			 		<span>
			 	
				 ${csOrder.csoMarginNeed$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoPredict',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoPredict',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csoPredict" >
			<dt>预计/里程费:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="menoy">
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoPredict" id="csoPredict"  value="${csOrder.csoPredict}"/>
		 	 
		 	 
		 	 <em>请输入订单的预计/里程费</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoPredict}" id="csoPredict" />
			 		<span>
			 	
				 ${csOrder.csoPredict$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoPayNeed',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoPayNeed',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csoPayNeed" >
			<dt>应付金额:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="menoy">
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoPayNeed" id="csoPayNeed"  value="${csOrder.csoPayNeed}"/>
		 	 
		 	 
		 	 <em>应付为当前状态下的预计理论金额</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoPayNeed}" id="csoPayNeed" />
			 		<span>
			 	
				 ${csOrder.csoPayNeed$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoPayReal',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoPayReal',(true && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csoPayReal" >
			<dt>实付金额:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="menoy">
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoPayReal" id="csoPayReal"  value="${csOrder.csoPayReal}"/>
		 	 
		 	 
		 	 <em>请输入订单的实付金额</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoPayReal}" id="csoPayReal" />
			 		<span>
			 	
				 ${csOrder.csoPayReal$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoPayMoney',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoPayMoney',(false && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csoPayMoney" >
			<dt>保证金支付:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="menoy">
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoPayMoney" id="csoPayMoney"  value="${csOrder.csoPayMoney}"/>
		 	 
		 	 
		 	 <em>请输入订单的保证金支付</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoPayMoney}" id="csoPayMoney" />
			 		<span>
			 	
				 ${csOrder.csoPayMoney$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoPayCoupon',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoPayCoupon',(false && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csoPayCoupon" >
			<dt>现金券支付:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="menoy">
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoPayCoupon" id="csoPayCoupon"  value="${csOrder.csoPayCoupon}"/>
		 	 
		 	 
		 	 <em>请输入订单的现金券支付</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoPayCoupon}" id="csoPayCoupon" />
			 		<span>
			 	
				 ${csOrder.csoPayCoupon$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoPayKilom',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoPayKilom',(false && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csoPayKilom" >
			<dt>电里程费:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="menoy">
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoPayKilom" id="csoPayKilom"  value="${csOrder.csoPayKilom}"/>
		 	 
		 	 
		 	 <em>请输入订单的电里程费</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoPayKilom}" id="csoPayKilom" />
			 		<span>
			 	
				 ${csOrder.csoPayKilom$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoPayMileage',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoPayMileage',(false && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csoPayMileage" >
			<dt>油里程费:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="menoy">
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoPayMileage" id="csoPayMileage"  value="${csOrder.csoPayMileage}"/>
		 	 
		 	 
		 	 <em>请输入订单的油里程费</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoPayMileage}" id="csoPayMileage" />
			 		<span>
			 	
				 ${csOrder.csoPayMileage$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoPayRent',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoPayRent',(false && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csoPayRent" >
			<dt>租金费用:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="menoy">
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoPayRent" id="csoPayRent"  value="${csOrder.csoPayRent}"/>
		 	 
		 	 
		 	 <em>请输入订单的租金费用</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoPayRent}" id="csoPayRent" />
			 		<span>
			 	
				 ${csOrder.csoPayRent$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoPayInsure',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoPayInsure',(false && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csoPayInsure" >
			<dt>免责费用:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="menoy">
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoPayInsure" id="csoPayInsure"  value="${csOrder.csoPayInsure}"/>
		 	 
		 	 
		 	 <em>请输入订单的免责费用</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoPayInsure}" id="csoPayInsure" />
			 		<span>
			 	
				 ${csOrder.csoPayInsure$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoPayTimeout',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoPayTimeout',(false && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csoPayTimeout" >
			<dt>超时费用:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="menoy">
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoPayTimeout" id="csoPayTimeout"  value="${csOrder.csoPayTimeout}"/>
		 	 
		 	 
		 	 <em>请输入订单的超时费用</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoPayTimeout}" id="csoPayTimeout" />
			 		<span>
			 	
				 ${csOrder.csoPayTimeout$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoPayDriver',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoPayDriver',(false && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csoPayDriver" >
			<dt>陪驾费用:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="menoy">
			 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoPayDriver" id="csoPayDriver"  value="${csOrder.csoPayDriver}"/>
		 	 
		 	 
		 	 <em>请输入订单的陪驾费用</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoPayDriver}" id="csoPayDriver" />
			 		<span>
			 	
				 ${csOrder.csoPayDriver$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		
		${lz:set("canEditable",lz:editableEx(controller,'csoUpdateTime',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoUpdateTime',(false && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csoUpdateTime" >
			<dt>修改时间:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="datetime">
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOrder.csoUpdateTime" id="csoUpdateTime"  value="<s:date name="csOrder.csoUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 
		 	 <b>*</b>
		 	 <em>请选择订单的修改时间</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoUpdateTime}" id="csoUpdateTime" />
			 		<span>
			 	
				 ${csOrder.csoUpdateTime$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoAddTime',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoAddTime',(false && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csoAddTime" >
			<dt>添加时间:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="datetime">
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOrder.csoAddTime" id="csoAddTime"  value="<s:date name="csOrder.csoAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 
		 	 <b>*</b>
		 	 <em>请选择订单的添加时间</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoAddTime}" id="csoAddTime" />
			 		<span>
			 	
				 ${csOrder.csoAddTime$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoEndTime',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoEndTime',(false && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csoEndTime" >
			<dt>结束时间:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="datetime">
			 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOrder.csoEndTime" id="csoEndTime"  value="<s:date name="csOrder.csoEndTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		 	 
		 	 
		 	 <em>请选择订单的结束时间</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoEndTime}" id="csoEndTime" />
			 		<span>
			 	
				 ${csOrder.csoEndTime$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoCode',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoCode',(false && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csoCode" >
			<dt>授权码:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="text">
			 	<input type="text" class="input narrow"  maxlength="32" name="csOrder.csoCode" id="csoCode"  value="${csOrder.csoCode}"/>
		 	 
		 	 <b>*</b>
		 	 <em>请输入订单的授权码</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoCode}" id="csoCode" />
			 		<span>
			 	
				 ${csOrder.csoCode$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		
		${lz:set("canEditable",lz:editableEx(controller,'csoRemark',(false && isAddType)||(true && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoRemark',(false && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csoRemark" style="width:98%;">
			<dt>订单备注:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="textarea">
			 	<textarea class="input wide"  id="csoRemark" name="csOrder.csoRemark" rows="5">${csOrder.csoRemark}</textarea>
		 	 
		 	 
		 	 <em>请输入订单的订单备注</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input wide">
			 		<span>
			 	
				 ${csOrder.csoRemark$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoLog',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoLog',(false && isAddType)||(false && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="csoLog" style="width:98%;">
			<dt>历史日志:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="text">
			 	<input type="text" class="input wide"  maxlength="512" name="csOrder.csoLog" id="csoLog"  value="${csOrder.csoLog}"/>
		 	 
		 	 
		 	 <em>请输入订单的历史日志</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input wide">
			 		<input type="hidden" value="${csOrder.csoLog}" id="csoLog" />
			 		<span>
			 	
				 ${csOrder.csoLog$}
		
		
		 	  
		 	 		&nbsp;	
		 	 		</span>
		 	 	</div>
			</dd>
			</s:else>
		</dl>
		</s:if>
			
		
		${lz:set("canEditable",lz:editableEx(controller,'csoStatus',(false && isAddType)||(false && !isAddType)))}
		${lz:set("canVisible",lz:visibleEx(controller,'csoStatus',(false && isAddType)||(true && !isAddType)))}
		
		<s:if test="#request.canVisible==true">
		<dl class="${(canEditable && false)?"hidden":""} ${canEditable && true ? "" : " minor"}"  ref="csoStatus" >
			<dt>状态:</dt>
			<s:if test="#request.canEditable==true && (#request.canAdd || #request.canEdit)">
			${lz:set("haveEditable",true)}
			<dd input="select">
			 	<select class="narrow" id="csoStatus" name="csOrder.csoStatus">
			 		<option value="">请选择</option>
					<option value="0" ${csOrder.csoStatus==0?"selected":""}>已预定</option>
					<option value="1" ${csOrder.csoStatus==1?"selected":""}>正在执行</option>
					<option value="2" ${csOrder.csoStatus==2?"selected":""}>已还车</option>
					<option value="3" ${csOrder.csoStatus==3?"selected":""}>已取消</option>
					<option value="4" ${csOrder.csoStatus==4?"selected":""}>已完成</option>
					<option value="5" ${csOrder.csoStatus==5?"selected":""}>待处理</option>
					<option value="6" ${csOrder.csoStatus==6?"selected":""}>已退款</option>
					<option value="7" ${csOrder.csoStatus==7?"selected":""}>已撤销</option>
			 	</select>
		 	 
		 	 <b>*</b>
		 	 <em>请选择订单的状态</em>
			</dd>
			</s:if>
			<s:else>
			<dd>
			 	<div class="state-input narrow">
			 		<input type="hidden" value="${csOrder.csoStatus}" id="csoStatus" />
			 		<span>
			 	
				 ${csOrder.csoStatus$}
		
		
		 	  
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
			<input type="hidden" value="${csOrder.csoId}" name="csOrder.csoId" id="csoId" />
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