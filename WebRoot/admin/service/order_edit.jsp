<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/service/order.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/service/order.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csOrder.csoId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csoPayMember,csoUseMember,csoFeeType,csoOutletsRet,csoCar,csoStartTime,csoFinishTime,csoLongPrice,csoInsureType,csoFreehour,csoCostDetails",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csoPayMember,csoUseMember,csoFeeType,csoOutlets,csoOutletsRet,csoCar,csoStartTime,csoFinishTime,csoLongPrice,csoInsureType,csoFreehour,csoCostDetails,csoMarginNeed,csoPredict,csoPayNeed,csoPayReal,csoPayCoin",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csoId,csoRemark",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csoId,csoPayMember,csoUseMember,csoFeeType,csoCar,csoStartTime,csoFinishTime,csoInsureType,csoFreehour,csoCostDetails,csoMarginNeed,csoPredict,csoPayNeed,csoPayReal,csoPayCoin,csoRemark,csoStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"系统订单编辑":CTRL.title}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	<meta name="hashcode" content="${window.hashcode}">
	
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
		window.basePath="${basePath}";
		window.hashcode = "${window.hashcode}";
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
<body>

${lz:set("注释","****非法权限时的友情提醒****")}
<s:if test="#request.canView!=true && #request.canAdd!=true && #request.canEdit!=true">
	<div style="padding:80px 20px;width:500px;margin:0px auto;">
		<center class="message"><FONT color=red><H4>${lz:config("nolimit.message")}</H4></FONT></center>
	</div>
</body>
</html>
<%if(true)return;%>
</s:if>



<!--LAZY3Q_JSP_BODY-->
<s:if test="#request.CTRL.title=='添加系统订单'">
	<%@ include file="order_public.jsp"%>
	<%@ include file="order_add.jsp"%>
</s:if>
<s:elseif test="#request.CTRL.title=='续订'">
	<%@ include file="order_public.jsp"%>
	<%@ include file="order_reorder.jsp"%>
</s:elseif>
<s:elseif test="#request.CTRL.title=='取消订单'">
	<%@ include file="order_public.jsp"%>
	<%@ include file="order_cancel.jsp"%>
</s:elseif>
<s:elseif test="#request.CTRL.title=='待处理'">
	<%@ include file="order_public.jsp"%>
	<%@ include file="order_pend.jsp"%>
</s:elseif>
<s:elseif test="#request.CTRL.title=='结算'">
	<%@ include file="order_public.jsp"%>
	<%@ include file="order_settle.jsp"%>
</s:elseif>
<s:elseif test="#request.CTRL.title=='重发'">
	<%@ include file="order_public.jsp"%>
	<%@ include file="order_reset.jsp"%>
</s:elseif>
<s:elseif test="#request.CTRL.title=='作废'">
	<%@ include file="order_public.jsp"%>
	<%@ include file="order_undo.jsp"%>
</s:elseif>
<s:elseif test="#request.CTRL.title=='回滚'">
	<%@ include file="order_public.jsp"%>
	<%@ include file="order_rollback.jsp"%>
</s:elseif>

<s:elseif test="#request.CTRL.title=='更改网点'">
	<%@ include file="order_public.jsp"%>
	<%@ include file="order_outlets.jsp"%>
</s:elseif>


<script type="text/javascript">
<!--
$(function(){
	$("dl[ref='csoCar']").insertBefore("dl[ref='csoOutlets']");
	$("#csoOutletsRet").parents(".combox").attr("action","${basePath}admin/object/outlets_ab.do?parent={param}&host={csoHost}&csoHost={csoHost}&car={csoCar}");
});
//-->
</script>

<!--LAZY3Q_JSP_BODY-->



<script>
$(function(){	
	showTips("${tips.value}");
});
window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}";


$(function(){
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#orderForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csoId==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoHost==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoPayMember==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPayMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoUseMember==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoUseMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoFeeType==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoFeeType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择计费类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoArea==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoArea":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择网点区域";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoOutlets==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoOutlets":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoOutletsRet==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoOutletsRet":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoModel==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoModel":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoCar==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoCar":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoProvid==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoProvid":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoCarNumber==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoCarNumber":function(el){
			if(jQuery.trim(el.value)=="")
				return "车牌号码不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "车牌号码最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoMobile==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoMobile":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "手机号码最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoEvRfid==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoEvRfid":function(el){
			if(jQuery.trim(el.value)=="")
				return "EV序列号不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "EV序列号最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoIsAdCar==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoIsAdCar":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoStartTime==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoStartTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "订单开始时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoFinishTime==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoFinishTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "订单结束时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoLongOrder==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoLongOrder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoLongPrice==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoLongPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "长单价格输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoUseType==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoUseType":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoTakeTime==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoTakeTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoRetTime==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoRetTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoDuration==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoDuration":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "订单时长输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoFuel==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoFuel":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "燃油里程输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoElectric==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoElectric":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "电里程输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoCreditCard==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoCreditCard":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoCreditBill==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoCreditBill":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoInsureType==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoInsureType":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoFreehour==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoFreehour":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "免费小时输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoRebate==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoRebate":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "折扣输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoMarginNeed==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoMarginNeed":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "押金/保证金输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoPredict==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPredict":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "预计/里程费输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoPayNeed==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPayNeed":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "应付金额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoPayReal==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPayReal":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "实付金额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoPayMoney==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPayMoney":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "保证金支付输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoPayCoupon==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPayCoupon":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "现金券支付输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoPayCoin==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPayCoin":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "红包支付输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoPayKilom==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPayKilom":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "电里程费输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoPayMileage==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPayMileage":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "油里程费输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoPayRent==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPayRent":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "租金费用输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoPayInsure==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPayInsure":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "免责费用输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoPayTimeout==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPayTimeout":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "超时费用输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoPayDriver==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoPayDriver":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "陪驾费用输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoIncome==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoIncome":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoUseIndex==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoUseIndex":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoFlag==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoFlag":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoFrom==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoFrom":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择订单来源";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoSrc==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoSrc":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoAddTime==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoEndTime==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoEndTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoSerial==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoSerial":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "序列号最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoCode==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoCode":function(el){
			if(jQuery.trim(el.value)=="")
				return "授权码不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "授权码最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoRemark==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "订单备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoLog==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoLog":function(el){
			if(el.value.length>512 && el.value.indexOf("[*]")==-1)
				return "历史日志最大长度为512个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoEditor==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoMask==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoMask":function(el){
				var bitValue = 0;
				$("input[ref='csoMask']").each(function(){
					if($(this).is(":checked"))
						bitValue|=$(this).val();
				});
				$("#csoMask").val(bitValue);
			if(!true && $("input[name='csOrder.csoMask']:checked").length==0)
				return "程序掩码至少选择一个";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoDeviceVesion==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoDeviceVesion":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoAlarm==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoAlarm":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoState==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoState":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择数据状态";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoStatus==true">
		${lz:set("haveEditable",true)}
		,"csOrder.csoStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择状态";
		}
		</s:if>	
	},function(){
		${lz:set("注释","****表单提交自定义判断，阻止提交返回提示字符串即可****")}
		/******************************LAZY3Q_FORM_VALIDATE******************************/
		/******************************LAZY3Q_FORM_VALIDATE******************************/

	});		
});
</script>

<s:if test="false"></s:if>
<s:elseif test="false"></s:elseif>
<s:else>
	<form class="form editform" ref="CsOrder" id="orderForm" name="orderForm" action="${empty CTRL.action?"order_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写系统订单相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${method}" name="method" id="method" />
				<input type="hidden" value="${lz:html(ctrl)}" id="ctrl" name="ctrl" />
				<input type="hidden" value="${csOrderToken}" name="csOrderToken" id="csOrderToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csOrder.csoId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************订单编号字段的输入框代码*****************")}
	${lz:set("注释","before$csoId和after$csoId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoId==true">
	${before$csoId}
	<dl class="csoId ${CTRL.e.csoId?"hidden":""}" major  ref="csoId" >
		<dt>订单编号:</dt>
		<s:if test="#request.CTRL.e.csoId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csOrder$csoId!=null">${csOrder$csoId}</s:if><s:else>
			<input type="hidden" value="${csOrder.csoId}" name="csOrder.csoId" id="csoId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoId">${csOrder.csoId}</textarea>
		 		<span>
		 	
			 ${csOrder.csoId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csoHost和after$csoHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoHost==true">
	${before$csoHost}
	<dl class="csoHost " major  ref="csoHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csoHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOrder$csoHost!=null">${csOrder$csoHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csoHost" name="csOrder.csoHost">
		 				<option selected value="${csOrder.csoHost}">
		 					${get:SrvHost(csOrder.csoHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csoHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csoHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoHost">${csOrder.csoHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csOrder.csoHost}',{ctrl:{editable:false,visible:true}})">
			 ${csOrder.csoHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoHost}
	</s:if>
	
	${lz:set("注释","*****************支付会员字段的输入框代码*****************")}
	${lz:set("注释","before$csoPayMember和after$csoPayMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoPayMember==true">
	${before$csoPayMember}
	<dl class="csoPayMember " major  ref="csoPayMember" >
		<dt>支付会员:</dt>
		<s:if test="#request.CTRL.e.csoPayMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOrder$csoPayMember!=null">${csOrder$csoPayMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csoHost}" type="text" id="csoPayMember" name="csOrder.csoPayMember" text="${get:CsMember(csOrder.csoPayMember).csmName}" value="${csOrder.csoPayMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csoPayMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csoPayMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****支付会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoPayMember">${csOrder.csoPayMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csOrder.csoPayMember}',{ctrl:{editable:false,visible:true}})">
			 ${csOrder.csoPayMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoPayMember}
	</s:if>
	
	${lz:set("注释","*****************使用会员字段的输入框代码*****************")}
	${lz:set("注释","before$csoUseMember和after$csoUseMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoUseMember==true">
	${before$csoUseMember}
	<dl class="csoUseMember " major  ref="csoUseMember" >
		<dt>使用会员:</dt>
		<s:if test="#request.CTRL.e.csoUseMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOrder$csoUseMember!=null">${csOrder$csoUseMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csoHost}" type="text" id="csoUseMember" name="csOrder.csoUseMember" text="${get:CsMember(csOrder.csoUseMember).csmName}" value="${csOrder.csoUseMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csoUseMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csoUseMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoUseMember">${csOrder.csoUseMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csOrder.csoUseMember}',{ctrl:{editable:false,visible:true}})">
			 ${csOrder.csoUseMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoUseMember}
	</s:if>
	
	${lz:set("注释","*****************操作记录项字段的输入框代码*****************")}
	${lz:set("注释","before$logs和after$logs变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.logs==true">
	${before$logs}
	<dl class="logs " minor  ref="logs" style="width:98%;">
		<dt>操作记录项:</dt>
		<s:if test="#request.CTRL.e.logs==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csOrder$logs!=null">${csOrder$logs}</s:if><s:else>
		  		<s:if test="csOrder.csoId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="logs" name="logs" frameborder='0' src="" url="${basePath}${proname}/service/orderlog.do?inFrame=true&canQuery=false&fields=csolTitle,csolEditor,csolRemark,csolAddTime&csolOrder=${csOrder.csoId}&ctrl={queryable:false,fields:{csolOrder:{visible:true,editable:false,defaultValue:${csOrder.csoId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#logs').attr('src',$('#logs').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑操作记录项
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****操作记录项字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csOrder.csoId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="logs" name="logs" frameborder='0' src="" url="${basePath}${proname}/service/orderlog.do?inFrame=true&canQuery=false&fields=csolTitle,csolEditor,csolRemark,csolAddTime&csolOrder=${csOrder.csoId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#logs').attr('src',$('#logs').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$logs}
	</s:if>
	
	${lz:set("注释","*****************计费类型字段的输入框代码*****************")}
	${lz:set("注释","before$csoFeeType和after$csoFeeType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoFeeType==true">
	${before$csoFeeType}
	<dl class="csoFeeType " major  ref="csoFeeType" >
		<dt>计费类型:</dt>
		<s:if test="#request.CTRL.e.csoFeeType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOrder$csoFeeType!=null">${csOrder$csoFeeType}</s:if><s:else>
		 	<select class="narrow" id="csoFeeType" name="csOrder.csoFeeType">
		 		<option value="">请选择</option>
 					${lz:set("items", lz:query("ccclubs_system","select * from cs_user_type where 1=1 ") )}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.csutId}" ${csOrder.csoFeeType==item.csutId?"selected":""}>${item.csutName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csoFeeType').val())==''){return;};window.href('${basePath}${proname}/user/type_details.do?key='+$('#csoFeeType').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统订单的计费类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****计费类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoFeeType">${csOrder.csoFeeType}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/type_details.do?key=${csOrder.csoFeeType}',{ctrl:{editable:false,visible:true}})">
			 ${csOrder.csoFeeType$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoFeeType}
	</s:if>
	
	${lz:set("注释","*****************网点区域字段的输入框代码*****************")}
	${lz:set("注释","before$csoArea和after$csoArea变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoArea==true">
	${before$csoArea}
	<dl class="csoArea " major  ref="csoArea" >
		<dt>网点区域:</dt>
		<s:if test="#request.CTRL.e.csoArea==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOrder$csoArea!=null">${csOrder$csoArea}</s:if><s:else>
		 	<select class="narrow" id="csoArea" name="csOrder.csoArea">
		 		<option value="">请选择</option>
 					${lz:set("items", lz:query$$("ccclubs_system","select * from cs_area where 1=1  and (csa_host in (?) or ? is null ) ",ccclubs_login.suHost,ccclubs_login.suHost) )}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.csaId}" ${csOrder.csoArea==item.csaId?"selected":""}>${item.csaName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csoArea').val())==''){return;};window.href('${basePath}${proname}/configurator/area_details.do?key='+$('#csoArea').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统订单的网点区域</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****网点区域字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoArea">${csOrder.csoArea}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/area_details.do?key=${csOrder.csoArea}',{ctrl:{editable:false,visible:true}})">
			 ${csOrder.csoArea$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoArea}
	</s:if>
	
	${lz:set("注释","*****************取车网点字段的输入框代码*****************")}
	${lz:set("注释","before$csoOutlets和after$csoOutlets变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoOutlets==true">
	${before$csoOutlets}
	<dl class="csoOutlets " major  ref="csoOutlets" >
		<dt>取车网点:</dt>
		<s:if test="#request.CTRL.e.csoOutlets==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOrder$csoOutlets!=null">${csOrder$csoOutlets}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/object/outlets_tree.do?parent={param}&host={csoHost}&csoHost={csoHost}" id="csoOutlets" name="csOrder.csoOutlets">
		 				<option selected value="${csOrder.csoOutlets}">
		 					${get:CsOutlets(csOrder.csoOutlets).csoName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csoOutlets').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?key='+$('#csoOutlets').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****取车网点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoOutlets">${csOrder.csoOutlets}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${csOrder.csoOutlets}',{ctrl:{editable:false,visible:true}})">
			 ${csOrder.csoOutlets$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoOutlets}
	</s:if>
	
	${lz:set("注释","*****************还车网点字段的输入框代码*****************")}
	${lz:set("注释","before$csoOutletsRet和after$csoOutletsRet变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoOutletsRet==true">
	${before$csoOutletsRet}
	<dl class="csoOutletsRet " major  ref="csoOutletsRet" >
		<dt>还车网点:</dt>
		<s:if test="#request.CTRL.e.csoOutletsRet==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOrder$csoOutletsRet!=null">${csOrder$csoOutletsRet}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/object/outlets_tree.do?parent={param}&host={csoHost}&csoHost={csoHost}" id="csoOutletsRet" name="csOrder.csoOutletsRet">
		 				<option selected value="${csOrder.csoOutletsRet}">
		 					${get:CsOutlets(csOrder.csoOutletsRet).csoName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csoOutletsRet').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?key='+$('#csoOutletsRet').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****还车网点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoOutletsRet">${csOrder.csoOutletsRet}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${csOrder.csoOutletsRet}',{ctrl:{editable:false,visible:true}})">
			 ${csOrder.csoOutletsRet$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoOutletsRet}
	</s:if>
	
	${lz:set("注释","*****************预订车型字段的输入框代码*****************")}
	${lz:set("注释","before$csoModel和after$csoModel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoModel==true">
	${before$csoModel}
	<dl class="csoModel " major  ref="csoModel" >
		<dt>预订车型:</dt>
		<s:if test="#request.CTRL.e.csoModel==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOrder$csoModel!=null">${csOrder$csoModel}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/object/carmodel_query.do?size=-1" id="csoModel" name="csOrder.csoModel">
		 				<option selected value="${csOrder.csoModel}">
		 					${get:CsCarModel(csOrder.csoModel).cscmName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csoModel').val())==''){return;};window.href('${basePath}${proname}/object/carmodel_details.do?key='+$('#csoModel').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****预订车型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoModel">${csOrder.csoModel}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/carmodel_details.do?key=${csOrder.csoModel}',{ctrl:{editable:false,visible:true}})">
			 ${csOrder.csoModel$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoModel}
	</s:if>
	
	${lz:set("注释","*****************预订车辆字段的输入框代码*****************")}
	${lz:set("注释","before$csoCar和after$csoCar变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoCar==true">
	${before$csoCar}
	<dl class="csoCar " major  ref="csoCar" >
		<dt>预订车辆:</dt>
		<s:if test="#request.CTRL.e.csoCar==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOrder$csoCar!=null">${csOrder$csoCar}</s:if><s:else>
		 			<input title="请输入车辆车牌号进行查询" class="combox narrow" action="${basePath}${proname}/object/car_query.do?value={param}&cscOutlets={csoOutlets}&cscModel={csoModel}&cscHost={csoHost}" type="text" id="csoCar" name="csOrder.csoCar" text="${get:CsCar(csOrder.csoCar).cscNumber}" value="${csOrder.csoCar}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csoCar').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?key='+$('#csoCar').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****预订车辆字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoCar">${csOrder.csoCar}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/car_details.do?key=${csOrder.csoCar}',{ctrl:{editable:false,visible:true}})">
			 ${csOrder.csoCar$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoCar}
	</s:if>
	
	${lz:set("注释","*****************车辆商家字段的输入框代码*****************")}
	${lz:set("注释","before$csoProvid和after$csoProvid变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoProvid==true">
	${before$csoProvid}
	<dl class="csoProvid " major  ref="csoProvid" >
		<dt>车辆商家:</dt>
		<s:if test="#request.CTRL.e.csoProvid==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOrder$csoProvid!=null">${csOrder$csoProvid}</s:if><s:else>
		 			<input title="请输入供应商名称进行查询" class="combox narrow" action="${basePath}${proname}/configurator/provid_query.do?value={param}" type="text" id="csoProvid" name="csOrder.csoProvid" text="${get:CsProvid(csOrder.csoProvid).cspName}" value="${csOrder.csoProvid}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csoProvid').val())==''){return;};window.href('${basePath}${proname}/configurator/provid_details.do?key='+$('#csoProvid').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车辆商家字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoProvid">${csOrder.csoProvid}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/provid_details.do?key=${csOrder.csoProvid}',{ctrl:{editable:false,visible:true}})">
			 ${csOrder.csoProvid$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoProvid}
	</s:if>
	
	${lz:set("注释","*****************车牌号码字段的输入框代码*****************")}
	${lz:set("注释","before$csoCarNumber和after$csoCarNumber变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoCarNumber==true">
	${before$csoCarNumber}
	<dl class="csoCarNumber " major  ref="csoCarNumber" >
		<dt>车牌号码:</dt>
		<s:if test="#request.CTRL.e.csoCarNumber==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOrder$csoCarNumber!=null">${csOrder$csoCarNumber}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOrder.csoCarNumber" id="csoCarNumber"  value="${csOrder.csoCarNumber}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入系统订单的车牌号码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车牌号码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoCarNumber">${csOrder.csoCarNumber}</textarea>
		 		<span>
		 	
			 ${csOrder.csoCarNumber$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoCarNumber}
	</s:if>
	
	${lz:set("注释","*****************手机号码字段的输入框代码*****************")}
	${lz:set("注释","before$csoMobile和after$csoMobile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoMobile==true">
	${before$csoMobile}
	<dl class="csoMobile " minor  ref="csoMobile" >
		<dt>手机号码:</dt>
		<s:if test="#request.CTRL.e.csoMobile==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOrder$csoMobile!=null">${csOrder$csoMobile}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOrder.csoMobile" id="csoMobile"  value="${csOrder.csoMobile}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统订单的手机号码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****手机号码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoMobile">${csOrder.csoMobile}</textarea>
		 		<span>
		 	
			 ${csOrder.csoMobile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoMobile}
	</s:if>
	
	${lz:set("注释","*****************EV序列号字段的输入框代码*****************")}
	${lz:set("注释","before$csoEvRfid和after$csoEvRfid变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoEvRfid==true">
	${before$csoEvRfid}
	<dl class="csoEvRfid " major  ref="csoEvRfid" >
		<dt>EV序列号:</dt>
		<s:if test="#request.CTRL.e.csoEvRfid==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOrder$csoEvRfid!=null">${csOrder$csoEvRfid}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOrder.csoEvRfid" id="csoEvRfid"  value="${csOrder.csoEvRfid}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入系统订单的EV序列号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****EV序列号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoEvRfid">${csOrder.csoEvRfid}</textarea>
		 		<span>
		 	
			 ${csOrder.csoEvRfid$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoEvRfid}
	</s:if>
	
	${lz:set("注释","*****************宣传车？字段的输入框代码*****************")}
	${lz:set("注释","before$csoIsAdCar和after$csoIsAdCar变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoIsAdCar==true">
	${before$csoIsAdCar}
	<dl class="csoIsAdCar " minor  ref="csoIsAdCar" >
		<dt>宣传车？:</dt>
		<s:if test="#request.CTRL.e.csoIsAdCar==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOrder$csoIsAdCar!=null">${csOrder$csoIsAdCar}</s:if><s:else>
		 	<select class="narrow" id="csoIsAdCar" name="csOrder.csoIsAdCar">
		 		<option value="">请选择</option>
				<option value="true" ${csOrder.csoIsAdCar==true?"selected":""}>是</option>
				<option value="false" ${csOrder.csoIsAdCar==false?"selected":""}>否</option>		 		
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择系统订单的宣传车？</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****宣传车？字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoIsAdCar">${csOrder.csoIsAdCar}</textarea>
		 		<span>
		 	
			 ${csOrder.csoIsAdCar$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoIsAdCar}
	</s:if>
	
	${lz:set("注释","*****************订单开始时间字段的输入框代码*****************")}
	${lz:set("注释","before$csoStartTime和after$csoStartTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoStartTime==true">
	${before$csoStartTime}
	<dl class="csoStartTime " major  ref="csoStartTime" >
		<dt>订单开始时间:</dt>
		<s:if test="#request.CTRL.e.csoStartTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOrder$csoStartTime!=null">${csOrder$csoStartTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOrder.csoStartTime" id="csoStartTime"  value="<s:date name="csOrder.csoStartTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统订单的订单开始时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单开始时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoStartTime">${csOrder.csoStartTime}</textarea>
		 		<span>
		 	
			 ${csOrder.csoStartTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoStartTime}
	</s:if>
	
	${lz:set("注释","*****************订单结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$csoFinishTime和after$csoFinishTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoFinishTime==true">
	${before$csoFinishTime}
	<dl class="csoFinishTime " major  ref="csoFinishTime" >
		<dt>订单结束时间:</dt>
		<s:if test="#request.CTRL.e.csoFinishTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOrder$csoFinishTime!=null">${csOrder$csoFinishTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOrder.csoFinishTime" id="csoFinishTime"  value="<s:date name="csOrder.csoFinishTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统订单的订单结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoFinishTime">${csOrder.csoFinishTime}</textarea>
		 		<span>
		 	
			 ${csOrder.csoFinishTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoFinishTime}
	</s:if>
	
	${lz:set("注释","*****************是否长单字段的输入框代码*****************")}
	${lz:set("注释","before$csoLongOrder和after$csoLongOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoLongOrder==true">
	${before$csoLongOrder}
	<dl class="csoLongOrder " minor  ref="csoLongOrder" >
		<dt>是否长单:</dt>
		<s:if test="#request.CTRL.e.csoLongOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOrder$csoLongOrder!=null">${csOrder$csoLongOrder}</s:if><s:else>
		 	<select class="narrow" id="csoLongOrder" name="csOrder.csoLongOrder">
		 		<option value="">请选择</option>
				<option value="true" ${csOrder.csoLongOrder==true?"selected":""}>是</option>
				<option value="false" ${csOrder.csoLongOrder==false?"selected":""}>否</option>		 		
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择系统订单的是否长单</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****是否长单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoLongOrder">${csOrder.csoLongOrder}</textarea>
		 		<span>
		 	
			 ${csOrder.csoLongOrder$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoLongOrder}
	</s:if>
	
	${lz:set("注释","*****************长单价格字段的输入框代码*****************")}
	${lz:set("注释","before$csoLongPrice和after$csoLongPrice变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoLongPrice==true">
	${before$csoLongPrice}
	<dl class="csoLongPrice " minor  ref="csoLongPrice" >
		<dt>长单价格:</dt>
		<s:if test="#request.CTRL.e.csoLongPrice==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrder$csoLongPrice!=null">${csOrder$csoLongPrice}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoLongPrice" id="csoLongPrice"  value="${csOrder.csoLongPrice}"/>
	 	 </s:else>
	 	 元
	 	 
	 	 <em>元/月</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****长单价格字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoLongPrice">${csOrder.csoLongPrice}</textarea>
		 		<span>
		 	
			 ${csOrder.csoLongPrice$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoLongPrice}
	</s:if>
	
	${lz:set("注释","*****************用车方式字段的输入框代码*****************")}
	${lz:set("注释","before$csoUseType和after$csoUseType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoUseType==true">
	${before$csoUseType}
	<dl class="csoUseType " major  ref="csoUseType" >
		<dt>用车方式:</dt>
		<s:if test="#request.CTRL.e.csoUseType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOrder$csoUseType!=null">${csOrder$csoUseType}</s:if><s:else>
		 	<select class="narrow" id="csoUseType" name="csOrder.csoUseType">
		 		<option value="">请选择</option>
				<option value="0" ${csOrder.csoUseType==0?"selected":""}>原借原还</option>
				<option value="1" ${csOrder.csoUseType==1?"selected":""}>A借B还</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择系统订单的用车方式</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****用车方式字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoUseType">${csOrder.csoUseType}</textarea>
		 		<span>
		 	
			 ${csOrder.csoUseType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoUseType}
	</s:if>
	
	${lz:set("注释","*****************实际取车时间字段的输入框代码*****************")}
	${lz:set("注释","before$csoTakeTime和after$csoTakeTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoTakeTime==true">
	${before$csoTakeTime}
	<dl class="csoTakeTime " minor  ref="csoTakeTime" >
		<dt>实际取车时间:</dt>
		<s:if test="#request.CTRL.e.csoTakeTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOrder$csoTakeTime!=null">${csOrder$csoTakeTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOrder.csoTakeTime" id="csoTakeTime"  value="<s:date name="csOrder.csoTakeTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择系统订单的实际取车时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实际取车时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoTakeTime">${csOrder.csoTakeTime}</textarea>
		 		<span>
		 	
			 ${csOrder.csoTakeTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoTakeTime}
	</s:if>
	
	${lz:set("注释","*****************实际还车时间字段的输入框代码*****************")}
	${lz:set("注释","before$csoRetTime和after$csoRetTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoRetTime==true">
	${before$csoRetTime}
	<dl class="csoRetTime " minor  ref="csoRetTime" >
		<dt>实际还车时间:</dt>
		<s:if test="#request.CTRL.e.csoRetTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOrder$csoRetTime!=null">${csOrder$csoRetTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOrder.csoRetTime" id="csoRetTime"  value="<s:date name="csOrder.csoRetTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择系统订单的实际还车时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实际还车时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoRetTime">${csOrder.csoRetTime}</textarea>
		 		<span>
		 	
			 ${csOrder.csoRetTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoRetTime}
	</s:if>
	
	${lz:set("注释","*****************订单时长字段的输入框代码*****************")}
	${lz:set("注释","before$csoDuration和after$csoDuration变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoDuration==true">
	${before$csoDuration}
	<dl class="csoDuration " major  ref="csoDuration" >
		<dt>订单时长:</dt>
		<s:if test="#request.CTRL.e.csoDuration==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrder$csoDuration!=null">${csOrder$csoDuration}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoDuration" id="csoDuration"  value="${csOrder.csoDuration}"/>
	 	 </s:else>
	 	 小时
	 	 <b>*</b>
	 	 <em>请输入系统订单的订单时长</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单时长字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoDuration">${csOrder.csoDuration}</textarea>
		 		<span>
		 	
			 ${csOrder.csoDuration$}
	 	 小时 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoDuration}
	</s:if>
	
	${lz:set("注释","*****************燃油里程字段的输入框代码*****************")}
	${lz:set("注释","before$csoFuel和after$csoFuel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoFuel==true">
	${before$csoFuel}
	<dl class="csoFuel " major  ref="csoFuel" >
		<dt>燃油里程:</dt>
		<s:if test="#request.CTRL.e.csoFuel==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrder$csoFuel!=null">${csOrder$csoFuel}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoFuel" id="csoFuel"  value="${csOrder.csoFuel}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入系统订单的燃油里程</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****燃油里程字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoFuel">${csOrder.csoFuel}</textarea>
		 		<span>
		 	
			 ${csOrder.csoFuel$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoFuel}
	</s:if>
	
	${lz:set("注释","*****************电里程字段的输入框代码*****************")}
	${lz:set("注释","before$csoElectric和after$csoElectric变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoElectric==true">
	${before$csoElectric}
	<dl class="csoElectric " major  ref="csoElectric" >
		<dt>电&nbsp;&nbsp;里&nbsp;&nbsp;程:</dt>
		<s:if test="#request.CTRL.e.csoElectric==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrder$csoElectric!=null">${csOrder$csoElectric}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoElectric" id="csoElectric"  value="${csOrder.csoElectric}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入系统订单的电里程</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****电里程字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoElectric">${csOrder.csoElectric}</textarea>
		 		<span>
		 	
			 ${csOrder.csoElectric$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoElectric}
	</s:if>
	
	${lz:set("注释","*****************使用信用卡字段的输入框代码*****************")}
	${lz:set("注释","before$csoCreditCard和after$csoCreditCard变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoCreditCard==true">
	${before$csoCreditCard}
	<dl class="csoCreditCard " minor  ref="csoCreditCard" >
		<dt>使用信用卡:</dt>
		<s:if test="#request.CTRL.e.csoCreditCard==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOrder$csoCreditCard!=null">${csOrder$csoCreditCard}</s:if><s:else>
		 			<input title="请输入信用卡信用卡号进行查询" class="combox narrow" action="${basePath}${proname}/user/credit/card_query.do?value={param}&csccMember={csoPayMember}" type="text" id="csoCreditCard" name="csOrder.csoCreditCard" text="${get:CsCreditCard(csOrder.csoCreditCard).csccNo}" value="${csOrder.csoCreditCard}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csoCreditCard').val())==''){return;};window.href('${basePath}${proname}/user/credit/card_details.do?key='+$('#csoCreditCard').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用信用卡字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoCreditCard">${csOrder.csoCreditCard}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/credit/card_details.do?key=${csOrder.csoCreditCard}',{ctrl:{editable:false,visible:true}})">
			 ${csOrder.csoCreditCard$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoCreditCard}
	</s:if>
	
	${lz:set("注释","*****************信用卡帐单字段的输入框代码*****************")}
	${lz:set("注释","before$csoCreditBill和after$csoCreditBill变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoCreditBill==true">
	${before$csoCreditBill}
	<dl class="csoCreditBill " major  ref="csoCreditBill" >
		<dt>信用卡帐单:</dt>
		<s:if test="#request.CTRL.e.csoCreditBill==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOrder$csoCreditBill!=null">${csOrder$csoCreditBill}</s:if><s:else>
		 			<input title="请输入信用账单编号进行查询" class="combox narrow" action="${basePath}${proname}/finance/credit/bill_query.do?value={param}&cscbMember={csoPayMember}&cscbHost={csoHost}" type="text" id="csoCreditBill" name="csOrder.csoCreditBill" text="${get:CsCreditBill(csOrder.csoCreditBill).cscbId}" value="${csOrder.csoCreditBill}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csoCreditBill').val())==''){return;};window.href('${basePath}${proname}/finance/credit/bill_details.do?key='+$('#csoCreditBill').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****信用卡帐单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoCreditBill">${csOrder.csoCreditBill}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/finance/credit/bill_details.do?key=${csOrder.csoCreditBill}',{ctrl:{editable:false,visible:true}})">
			 ${csOrder.csoCreditBill$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoCreditBill}
	</s:if>
	
	${lz:set("注释","*****************保险类型字段的输入框代码*****************")}
	${lz:set("注释","before$csoInsureType和after$csoInsureType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoInsureType==true">
	${before$csoInsureType}
	<dl class="csoInsureType " minor  ref="csoInsureType" >
		<dt>保险类型:</dt>
		<s:if test="#request.CTRL.e.csoInsureType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOrder$csoInsureType!=null">${csOrder$csoInsureType}</s:if><s:else>
		 	<select class="narrow" id="csoInsureType" name="csOrder.csoInsureType">
		 		<option value="">请选择</option>
				    ${lz:set("items", lz:query("ccclubs_system","select * from cs_product where csp_flag='INSURE' or csp_flag='SECURE'") )}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.cspId}" ${csOrder.csoInsureType==item.cspId?"selected":""}>${item.cspName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csoInsureType').val())==''){return;};window.href('${basePath}${proname}/fee/product_details.do?key='+$('#csoInsureType').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择系统订单的保险类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****保险类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoInsureType">${csOrder.csoInsureType}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/fee/product_details.do?key=${csOrder.csoInsureType}',{ctrl:{editable:false,visible:true}})">
			 ${csOrder.csoInsureType$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoInsureType}
	</s:if>
	
	${lz:set("注释","*****************免费小时字段的输入框代码*****************")}
	${lz:set("注释","before$csoFreehour和after$csoFreehour变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoFreehour==true">
	${before$csoFreehour}
	<dl class="csoFreehour " minor  ref="csoFreehour" >
		<dt>免费小时:</dt>
		<s:if test="#request.CTRL.e.csoFreehour==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrder$csoFreehour!=null">${csOrder$csoFreehour}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoFreehour" id="csoFreehour"  value="${csOrder.csoFreehour}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统订单的免费小时</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****免费小时字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoFreehour">${csOrder.csoFreehour}</textarea>
		 		<span>
		 	
			 ${csOrder.csoFreehour$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoFreehour}
	</s:if>
	
	${lz:set("注释","*****************订单明细字段的输入框代码*****************")}
	${lz:set("注释","before$csoCostDetails和after$csoCostDetails变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoCostDetails==true">
	${before$csoCostDetails}
	<dl class="csoCostDetails " minor  ref="csoCostDetails" style="width:98%;">
		<dt>订单明细:</dt>
		<s:if test="#request.CTRL.e.csoCostDetails==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csOrder$csoCostDetails!=null">${csOrder$csoCostDetails}</s:if><s:else>
		  		<s:if test="csOrder.csoId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csoCostDetails" name="csoCostDetails" frameborder='0' src="" url="${basePath}${proname}/service/orderdetail.do?inFrame=true&canQuery=false&all=true&csodOrder=${csOrder.csoId}&ctrl={queryable:false,fields:{csodOrder:{visible:true,editable:false,defaultValue:${csOrder.csoId}}}}&size=8" height='0'></iframe>
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
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单明细字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csOrder.csoId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csoCostDetails" name="csoCostDetails" frameborder='0' src="" url="${basePath}${proname}/service/orderdetail.do?inFrame=true&canQuery=false&all=true&csodOrder=${csOrder.csoId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csoCostDetails').attr('src',$('#csoCostDetails').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$csoCostDetails}
	</s:if>
	
	${lz:set("注释","*****************免费优惠项字段的输入框代码*****************")}
	${lz:set("注释","before$gifts和after$gifts变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.gifts==true">
	${before$gifts}
	<dl class="gifts " minor  ref="gifts" style="width:98%;">
		<dt>红包使用记录:</dt>
		<s:if test="#request.CTRL.e.gifts==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csOrder$gifts!=null">${csOrder$gifts}</s:if><s:else>
		  		<s:if test="csOrder.csoId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="gifts" name="gifts" frameborder='0' src="" url="${basePath}${proname}/user/userecord.do?inFrame=true&canQuery=false&fields=csurCoin,csurCount&csurOrder=${csOrder.csoId}&ctrl={queryable:false,fields:{csurOrder:{visible:true,editable:false,defaultValue:${csOrder.csoId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#gifts').attr('src',$('#gifts').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑红包使用记录
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****免费优惠项字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csOrder.csoId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="gifts" name="gifts" frameborder='0' src="" url="${basePath}${proname}/user/userecord.do?inFrame=true&canQuery=false&fields=csurCoin,csurCount&csurOrder=${csOrder.csoId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#gifts').attr('src',$('#gifts').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$gifts}
	</s:if>
	
	${lz:set("注释","*****************消费记录项字段的输入框代码*****************")}
	${lz:set("注释","before$records和after$records变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.records==true">
	${before$records}
	<dl class="records " minor  ref="records" style="width:98%;">
		<dt>消费记录项:</dt>
		<s:if test="#request.CTRL.e.records==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csOrder$records!=null">${csOrder$records}</s:if><s:else>
		  		<s:if test="csOrder.csoId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="records" name="records" frameborder='0' src="" url="${basePath}${proname}/finance/record.do?inFrame=true&canQuery=false&fields=csrRemark,csrMoneyType,csrRecordSubject,csrRecordType,csrAmount,csrAddTime,csrOrder&csrOrder=${csOrder.csoId}&ctrl={queryable:false,fields:{csrOrder:{visible:true,editable:false,defaultValue:${csOrder.csoId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#records').attr('src',$('#records').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑消费记录项
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****消费记录项字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csOrder.csoId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="records" name="records" frameborder='0' src="" url="${basePath}${proname}/finance/record.do?inFrame=true&canQuery=false&fields=csrRemark,csrMoneyType,csrRecordSubject,csrRecordType,csrAmount,csrAddTime,csrOrder&csrOrder=${csOrder.csoId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#records').attr('src',$('#records').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$records}
	</s:if>
	
	${lz:set("注释","*****************相关违章字段的输入框代码*****************")}
	${lz:set("注释","before$violats和after$violats变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.violats==true">
	${before$violats}
	<dl class="violats " minor  ref="violats" style="width:98%;">
		<dt>相关违章:</dt>
		<s:if test="#request.CTRL.e.violats==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csOrder$violats!=null">${csOrder$violats}</s:if><s:else>
		  		<s:if test="csOrder.csoId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="violats" name="violats" frameborder='0' src="" url="${basePath}${proname}/service/violat.do?inFrame=true&canQuery=false&fields=csvCar,csvHappenTime,csvDecipt,csvStatus&csvOrder=${csOrder.csoId}&ctrl={queryable:false,fields:{csvOrder:{visible:true,editable:false,defaultValue:${csOrder.csoId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#violats').attr('src',$('#violats').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑相关违章
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****相关违章字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csOrder.csoId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="violats" name="violats" frameborder='0' src="" url="${basePath}${proname}/service/violat.do?inFrame=true&canQuery=false&fields=csvCar,csvHappenTime,csvDecipt,csvStatus&csvOrder=${csOrder.csoId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#violats').attr('src',$('#violats').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$violats}
	</s:if>
	
	${lz:set("注释","*****************相关事故字段的输入框代码*****************")}
	${lz:set("注释","before$troubles和after$troubles变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.troubles==true">
	${before$troubles}
	<dl class="troubles " minor  ref="troubles" style="width:98%;">
		<dt>相关事故:</dt>
		<s:if test="#request.CTRL.e.troubles==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csOrder$troubles!=null">${csOrder$troubles}</s:if><s:else>
		  		<s:if test="csOrder.csoId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="troubles" name="troubles" frameborder='0' src="" url="${basePath}${proname}/service/trouble.do?inFrame=true&canQuery=false&fields=cstCar,cstType,cstHappenTime,cstStatus&cstOrder=${csOrder.csoId}&ctrl={queryable:false,fields:{cstOrder:{visible:true,editable:false,defaultValue:${csOrder.csoId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#troubles').attr('src',$('#troubles').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑相关事故
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****相关事故字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csOrder.csoId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="troubles" name="troubles" frameborder='0' src="" url="${basePath}${proname}/service/trouble.do?inFrame=true&canQuery=false&fields=cstCar,cstType,cstHappenTime,cstStatus&cstOrder=${csOrder.csoId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#troubles').attr('src',$('#troubles').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$troubles}
	</s:if>
	
	${lz:set("注释","*****************收入记录字段的输入框代码*****************")}
	${lz:set("注释","before$operrecords和after$operrecords变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.operrecords==true">
	${before$operrecords}
	<dl class="operrecords " minor  ref="operrecords" style="width:98%;">
		<dt>收入记录:</dt>
		<s:if test="#request.CTRL.e.operrecords==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csOrder$operrecords!=null">${csOrder$operrecords}</s:if><s:else>
		  		<s:if test="csOrder.csoId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="operrecords" name="operrecords" frameborder='0' src="" url="${basePath}${proname}/finance/operecord.do?inFrame=true&canQuery=false&fields=csorHost,csorTitle,csorMoneyType,csorRecordType,csorIn,csorOut,csorDateTime,csorAdder&csorOrder=${csOrder.csoId}&ctrl={queryable:false,fields:{csorOrder:{visible:true,editable:false,defaultValue:${csOrder.csoId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#operrecords').attr('src',$('#operrecords').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑收入记录
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****收入记录字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csOrder.csoId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="operrecords" name="operrecords" frameborder='0' src="" url="${basePath}${proname}/finance/operecord.do?inFrame=true&canQuery=false&fields=csorHost,csorTitle,csorMoneyType,csorRecordType,csorIn,csorOut,csorDateTime,csorAdder&csorOrder=${csOrder.csoId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#operrecords').attr('src',$('#operrecords').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$operrecords}
	</s:if>
	
	${lz:set("注释","*****************折扣字段的输入框代码*****************")}
	${lz:set("注释","before$csoRebate和after$csoRebate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoRebate==true">
	${before$csoRebate}
	<dl class="csoRebate " minor  ref="csoRebate" >
		<dt>折　　扣:</dt>
		<s:if test="#request.CTRL.e.csoRebate==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrder$csoRebate!=null">${csOrder$csoRebate}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoRebate" id="csoRebate"  value="${csOrder.csoRebate}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>0到1,比如0.75表示七五折</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****折扣字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoRebate">${csOrder.csoRebate}</textarea>
		 		<span>
		 	
			 ${csOrder.csoRebate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoRebate}
	</s:if>
	
	${lz:set("注释","*****************押金/保证金字段的输入框代码*****************")}
	${lz:set("注释","before$csoMarginNeed和after$csoMarginNeed变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoMarginNeed==true">
	${before$csoMarginNeed}
	<dl class="csoMarginNeed " minor  ref="csoMarginNeed" >
		<dt>押金/保证金:</dt>
		<s:if test="#request.CTRL.e.csoMarginNeed==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrder$csoMarginNeed!=null">${csOrder$csoMarginNeed}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoMarginNeed" id="csoMarginNeed"  value="${csOrder.csoMarginNeed}"/>
	 	 </s:else>
	 	 元
	 	 
	 	 <em>请输入系统订单的押金/保证金</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****押金/保证金字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoMarginNeed">${csOrder.csoMarginNeed}</textarea>
		 		<span>
		 	
			 ${csOrder.csoMarginNeed$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoMarginNeed}
	</s:if>
	
	${lz:set("注释","*****************预计/里程费字段的输入框代码*****************")}
	${lz:set("注释","before$csoPredict和after$csoPredict变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoPredict==true">
	${before$csoPredict}
	<dl class="csoPredict " minor  ref="csoPredict" >
		<dt>预计/里程费:</dt>
		<s:if test="#request.CTRL.e.csoPredict==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrder$csoPredict!=null">${csOrder$csoPredict}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoPredict" id="csoPredict"  value="${csOrder.csoPredict}"/>
	 	 </s:else>
	 	 元
	 	 
	 	 <em>请输入系统订单的预计/里程费</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****预计/里程费字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoPredict">${csOrder.csoPredict}</textarea>
		 		<span>
		 	
			 ${csOrder.csoPredict$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoPredict}
	</s:if>
	
	${lz:set("注释","*****************应付金额字段的输入框代码*****************")}
	${lz:set("注释","before$csoPayNeed和after$csoPayNeed变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoPayNeed==true">
	${before$csoPayNeed}
	<dl class="csoPayNeed " minor  ref="csoPayNeed" >
		<dt>应付金额:</dt>
		<s:if test="#request.CTRL.e.csoPayNeed==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrder$csoPayNeed!=null">${csOrder$csoPayNeed}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoPayNeed" id="csoPayNeed"  value="${csOrder.csoPayNeed}"/>
	 	 </s:else>
	 	 元
	 	 
	 	 <em>应付为当前状态下的预计理论金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****应付金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoPayNeed">${csOrder.csoPayNeed}</textarea>
		 		<span>
		 	
			 ${csOrder.csoPayNeed$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoPayNeed}
	</s:if>
	
	${lz:set("注释","*****************实付金额字段的输入框代码*****************")}
	${lz:set("注释","before$csoPayReal和after$csoPayReal变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoPayReal==true">
	${before$csoPayReal}
	<dl class="csoPayReal " minor  ref="csoPayReal" >
		<dt>实付金额:</dt>
		<s:if test="#request.CTRL.e.csoPayReal==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrder$csoPayReal!=null">${csOrder$csoPayReal}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoPayReal" id="csoPayReal"  value="${csOrder.csoPayReal}"/>
	 	 </s:else>
	 	 元
	 	 
	 	 <em>请输入系统订单的实付金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实付金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoPayReal">${csOrder.csoPayReal}</textarea>
		 		<span>
		 	
			 ${csOrder.csoPayReal$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoPayReal}
	</s:if>
	
	${lz:set("注释","*****************保证金支付字段的输入框代码*****************")}
	${lz:set("注释","before$csoPayMoney和after$csoPayMoney变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoPayMoney==true">
	${before$csoPayMoney}
	<dl class="csoPayMoney " minor  ref="csoPayMoney" >
		<dt>保证金支付:</dt>
		<s:if test="#request.CTRL.e.csoPayMoney==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrder$csoPayMoney!=null">${csOrder$csoPayMoney}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoPayMoney" id="csoPayMoney"  value="${csOrder.csoPayMoney}"/>
	 	 </s:else>
	 	 元
	 	 
	 	 <em>请输入系统订单的保证金支付</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****保证金支付字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoPayMoney">${csOrder.csoPayMoney}</textarea>
		 		<span>
		 	
			 ${csOrder.csoPayMoney$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoPayMoney}
	</s:if>
	
	${lz:set("注释","*****************现金券支付字段的输入框代码*****************")}
	${lz:set("注释","before$csoPayCoupon和after$csoPayCoupon变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoPayCoupon==true">
	${before$csoPayCoupon}
	<dl class="csoPayCoupon " minor  ref="csoPayCoupon" >
		<dt>现金券支付:</dt>
		<s:if test="#request.CTRL.e.csoPayCoupon==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrder$csoPayCoupon!=null">${csOrder$csoPayCoupon}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoPayCoupon" id="csoPayCoupon"  value="${csOrder.csoPayCoupon}"/>
	 	 </s:else>
	 	 元
	 	 
	 	 <em>请输入系统订单的现金券支付</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****现金券支付字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoPayCoupon">${csOrder.csoPayCoupon}</textarea>
		 		<span>
		 	
			 ${csOrder.csoPayCoupon$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoPayCoupon}
	</s:if>
	
	${lz:set("注释","*****************红包支付字段的输入框代码*****************")}
	${lz:set("注释","before$csoPayCoin和after$csoPayCoin变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoPayCoin==true">
	${before$csoPayCoin}
	<dl class="csoPayCoin " minor  ref="csoPayCoin" >
		<dt>红包支付:</dt>
		<s:if test="#request.CTRL.e.csoPayCoin==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrder$csoPayCoin!=null">${csOrder$csoPayCoin}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoPayCoin" id="csoPayCoin"  value="${csOrder.csoPayCoin}"/>
	 	 </s:else>
	 	 元
	 	 
	 	 <em>请输入系统订单的红包支付</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****红包支付字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoPayCoin">${csOrder.csoPayCoin}</textarea>
		 		<span>
		 	
			 ${csOrder.csoPayCoin$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoPayCoin}
	</s:if>
	
	${lz:set("注释","*****************电里程费字段的输入框代码*****************")}
	${lz:set("注释","before$csoPayKilom和after$csoPayKilom变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoPayKilom==true">
	${before$csoPayKilom}
	<dl class="csoPayKilom " minor  ref="csoPayKilom" >
		<dt>电里程费:</dt>
		<s:if test="#request.CTRL.e.csoPayKilom==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrder$csoPayKilom!=null">${csOrder$csoPayKilom}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoPayKilom" id="csoPayKilom"  value="${csOrder.csoPayKilom}"/>
	 	 </s:else>
	 	 元
	 	 
	 	 <em>请输入系统订单的电里程费</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****电里程费字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoPayKilom">${csOrder.csoPayKilom}</textarea>
		 		<span>
		 	
			 ${csOrder.csoPayKilom$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoPayKilom}
	</s:if>
	
	${lz:set("注释","*****************油里程费字段的输入框代码*****************")}
	${lz:set("注释","before$csoPayMileage和after$csoPayMileage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoPayMileage==true">
	${before$csoPayMileage}
	<dl class="csoPayMileage " minor  ref="csoPayMileage" >
		<dt>油里程费:</dt>
		<s:if test="#request.CTRL.e.csoPayMileage==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrder$csoPayMileage!=null">${csOrder$csoPayMileage}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoPayMileage" id="csoPayMileage"  value="${csOrder.csoPayMileage}"/>
	 	 </s:else>
	 	 元
	 	 
	 	 <em>请输入系统订单的油里程费</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****油里程费字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoPayMileage">${csOrder.csoPayMileage}</textarea>
		 		<span>
		 	
			 ${csOrder.csoPayMileage$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoPayMileage}
	</s:if>
	
	${lz:set("注释","*****************租金费用字段的输入框代码*****************")}
	${lz:set("注释","before$csoPayRent和after$csoPayRent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoPayRent==true">
	${before$csoPayRent}
	<dl class="csoPayRent " minor  ref="csoPayRent" >
		<dt>租金费用:</dt>
		<s:if test="#request.CTRL.e.csoPayRent==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrder$csoPayRent!=null">${csOrder$csoPayRent}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoPayRent" id="csoPayRent"  value="${csOrder.csoPayRent}"/>
	 	 </s:else>
	 	 元
	 	 
	 	 <em>请输入系统订单的租金费用</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****租金费用字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoPayRent">${csOrder.csoPayRent}</textarea>
		 		<span>
		 	
			 ${csOrder.csoPayRent$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoPayRent}
	</s:if>
	
	${lz:set("注释","*****************免责费用字段的输入框代码*****************")}
	${lz:set("注释","before$csoPayInsure和after$csoPayInsure变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoPayInsure==true">
	${before$csoPayInsure}
	<dl class="csoPayInsure " minor  ref="csoPayInsure" >
		<dt>免责费用:</dt>
		<s:if test="#request.CTRL.e.csoPayInsure==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrder$csoPayInsure!=null">${csOrder$csoPayInsure}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoPayInsure" id="csoPayInsure"  value="${csOrder.csoPayInsure}"/>
	 	 </s:else>
	 	 元
	 	 
	 	 <em>请输入系统订单的免责费用</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****免责费用字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoPayInsure">${csOrder.csoPayInsure}</textarea>
		 		<span>
		 	
			 ${csOrder.csoPayInsure$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoPayInsure}
	</s:if>
	
	${lz:set("注释","*****************超时费用字段的输入框代码*****************")}
	${lz:set("注释","before$csoPayTimeout和after$csoPayTimeout变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoPayTimeout==true">
	${before$csoPayTimeout}
	<dl class="csoPayTimeout " minor  ref="csoPayTimeout" >
		<dt>超时费用:</dt>
		<s:if test="#request.CTRL.e.csoPayTimeout==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrder$csoPayTimeout!=null">${csOrder$csoPayTimeout}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoPayTimeout" id="csoPayTimeout"  value="${csOrder.csoPayTimeout}"/>
	 	 </s:else>
	 	 元
	 	 
	 	 <em>请输入系统订单的超时费用</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****超时费用字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoPayTimeout">${csOrder.csoPayTimeout}</textarea>
		 		<span>
		 	
			 ${csOrder.csoPayTimeout$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoPayTimeout}
	</s:if>
	
	${lz:set("注释","*****************陪驾费用字段的输入框代码*****************")}
	${lz:set("注释","before$csoPayDriver和after$csoPayDriver变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoPayDriver==true">
	${before$csoPayDriver}
	<dl class="csoPayDriver " minor  ref="csoPayDriver" >
		<dt>陪驾费用:</dt>
		<s:if test="#request.CTRL.e.csoPayDriver==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOrder$csoPayDriver!=null">${csOrder$csoPayDriver}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoPayDriver" id="csoPayDriver"  value="${csOrder.csoPayDriver}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统订单的陪驾费用</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****陪驾费用字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoPayDriver">${csOrder.csoPayDriver}</textarea>
		 		<span>
		 	
			 ${csOrder.csoPayDriver$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoPayDriver}
	</s:if>
	
	${lz:set("注释","*****************计入营收字段的输入框代码*****************")}
	${lz:set("注释","before$csoIncome和after$csoIncome变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoIncome==true">
	${before$csoIncome}
	<dl class="csoIncome " minor  ref="csoIncome" >
		<dt>计入营收:</dt>
		<s:if test="#request.CTRL.e.csoIncome==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOrder$csoIncome!=null">${csOrder$csoIncome}</s:if><s:else>
		 	<select class="narrow" id="csoIncome" name="csOrder.csoIncome">
		 		<option value="">请选择</option>
				<option value="0" ${csOrder.csoIncome==0?"selected":""}>不计营收</option>
				<option value="1" ${csOrder.csoIncome==1?"selected":""}>计入营收</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择系统订单的计入营收</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****计入营收字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoIncome">${csOrder.csoIncome}</textarea>
		 		<span>
		 	
			 ${csOrder.csoIncome$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoIncome}
	</s:if>
	
	${lz:set("注释","*****************订单次数字段的输入框代码*****************")}
	${lz:set("注释","before$csoUseIndex和after$csoUseIndex变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoUseIndex==true">
	${before$csoUseIndex}
	<dl class="csoUseIndex " minor  ref="csoUseIndex" >
		<dt>订单次数:</dt>
		<s:if test="#request.CTRL.e.csoUseIndex==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csOrder$csoUseIndex!=null">${csOrder$csoUseIndex}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOrder.csoUseIndex" id="csoUseIndex"  value="${csOrder.csoUseIndex}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>标明该订单是会员第几次用车</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单次数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoUseIndex">${csOrder.csoUseIndex}</textarea>
		 		<span>
		 	
			 ${csOrder.csoUseIndex$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoUseIndex}
	</s:if>
	
	${lz:set("注释","*****************类型标识字段的输入框代码*****************")}
	${lz:set("注释","before$csoFlag和after$csoFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoFlag==true">
	${before$csoFlag}
	<dl class="csoFlag " minor  ref="csoFlag" >
		<dt>类型标识:</dt>
		<s:if test="#request.CTRL.e.csoFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOrder$csoFlag!=null">${csOrder$csoFlag}</s:if><s:else>
		 	<select class="narrow" id="csoFlag" name="csOrder.csoFlag">
		 		<option value="">请选择</option>
				<option value="0" ${csOrder.csoFlag==0?"selected":""}>默认</option>
				<option value="1" ${csOrder.csoFlag==1?"selected":""}>2014春节订单</option>
				<option value="2" ${csOrder.csoFlag==2?"selected":""}>2015春节订单</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择系统订单的类型标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****类型标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoFlag">${csOrder.csoFlag}</textarea>
		 		<span>
		 	
			 ${csOrder.csoFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoFlag}
	</s:if>
	
	${lz:set("注释","*****************订单来源字段的输入框代码*****************")}
	${lz:set("注释","before$csoFrom和after$csoFrom变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoFrom==true">
	${before$csoFrom}
	<dl class="csoFrom " major  ref="csoFrom" >
		<dt>订单来源:</dt>
		<s:if test="#request.CTRL.e.csoFrom==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOrder$csoFrom!=null">${csOrder$csoFrom}</s:if><s:else>
		 	<select class="narrow" id="csoFrom" name="csOrder.csoFrom">
		 		<option value="">请选择</option>
				<option value="0" ${csOrder.csoFrom==0?"selected":""}>网站预订</option>
				<option value="1" ${csOrder.csoFrom==1?"selected":""}>后台预订</option>
				<option value="2" ${csOrder.csoFrom==2?"selected":""}>微信预订</option>
				<option value="3" ${csOrder.csoFrom==3?"selected":""}>App预订</option>
				<option value="4" ${csOrder.csoFrom==4?"selected":""}>企业预订</option>
				<option value="5" ${csOrder.csoFrom==5?"selected":""}>支付宝预订</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统订单的订单来源</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单来源字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoFrom">${csOrder.csoFrom}</textarea>
		 		<span>
		 	
			 ${csOrder.csoFrom$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoFrom}
	</s:if>
	
	${lz:set("注释","*****************来源信息字段的输入框代码*****************")}
	${lz:set("注释","before$csoSrc和after$csoSrc变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoSrc==true">
	${before$csoSrc}
	<dl class="csoSrc " minor  ref="csoSrc" >
		<dt>来源信息:</dt>
		<s:if test="#request.CTRL.e.csoSrc==true">
		${lz:set("haveEditable",true)}
		<dd input="generic">
		<s:if test="#request.csOrder$csoSrc!=null">${csOrder$csoSrc}</s:if><s:else>
		 	<input title="请输入动态查询" class="combox narrow" generic="csoSrcGeneric" size="16" type="text" id="csoSrc" name="csOrder.csoSrc" text="${csOrder.csoSrc$}" value="${csOrder.csoSrc}"/>
		 	${lz:set("models",get:models(""))}
		 	<select id="csoSrcGeneric" style="display:none;">
		 		<option value="">--选择模块--</option>
		 		<s:iterator value="#request.models" id="item" status="i">
		 		<option value="${item.name}:${basePath}admin/${item.namespace}_query.do?value={param}">${item.caption}</option>
		 		</s:iterator>
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="lookup('#${NAME}')"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****来源信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoSrc">${csOrder.csoSrc}</textarea>
		 		<span>
		 	
			 ${csOrder.csoSrc$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoSrc}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csoUpdateTime和after$csoUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoUpdateTime==true">
	${before$csoUpdateTime}
	<dl class="csoUpdateTime " major  ref="csoUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csoUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOrder$csoUpdateTime!=null">${csOrder$csoUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOrder.csoUpdateTime" id="csoUpdateTime"  value="<s:date name="csOrder.csoUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统订单的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoUpdateTime">${csOrder.csoUpdateTime}</textarea>
		 		<span>
		 	
			 ${csOrder.csoUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csoAddTime和after$csoAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoAddTime==true">
	${before$csoAddTime}
	<dl class="csoAddTime " major  ref="csoAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csoAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOrder$csoAddTime!=null">${csOrder$csoAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOrder.csoAddTime" id="csoAddTime"  value="<s:date name="csOrder.csoAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统订单的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoAddTime">${csOrder.csoAddTime}</textarea>
		 		<span>
		 	
			 ${csOrder.csoAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoAddTime}
	</s:if>
	
	${lz:set("注释","*****************结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$csoEndTime和after$csoEndTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoEndTime==true">
	${before$csoEndTime}
	<dl class="csoEndTime " minor  ref="csoEndTime" >
		<dt>结束时间:</dt>
		<s:if test="#request.CTRL.e.csoEndTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOrder$csoEndTime!=null">${csOrder$csoEndTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOrder.csoEndTime" id="csoEndTime"  value="<s:date name="csOrder.csoEndTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择系统订单的结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoEndTime">${csOrder.csoEndTime}</textarea>
		 		<span>
		 	
			 ${csOrder.csoEndTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoEndTime}
	</s:if>
	
	${lz:set("注释","*****************序列号字段的输入框代码*****************")}
	${lz:set("注释","before$csoSerial和after$csoSerial变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoSerial==true">
	${before$csoSerial}
	<dl class="csoSerial " minor  ref="csoSerial" >
		<dt>序&nbsp;&nbsp;列&nbsp;&nbsp;号:</dt>
		<s:if test="#request.CTRL.e.csoSerial==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOrder$csoSerial!=null">${csOrder$csoSerial}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOrder.csoSerial" id="csoSerial"  value="${csOrder.csoSerial}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统订单的序列号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****序列号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoSerial">${csOrder.csoSerial}</textarea>
		 		<span>
		 	
			 ${csOrder.csoSerial$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoSerial}
	</s:if>
	
	${lz:set("注释","*****************授权码字段的输入框代码*****************")}
	${lz:set("注释","before$csoCode和after$csoCode变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoCode==true">
	${before$csoCode}
	<dl class="csoCode " major  ref="csoCode" >
		<dt>授&nbsp;&nbsp;权&nbsp;&nbsp;码:</dt>
		<s:if test="#request.CTRL.e.csoCode==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOrder$csoCode!=null">${csOrder$csoCode}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOrder.csoCode" id="csoCode"  value="${csOrder.csoCode}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入系统订单的授权码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****授权码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoCode">${csOrder.csoCode}</textarea>
		 		<span>
		 	
			 ${csOrder.csoCode$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoCode}
	</s:if>
	
	${lz:set("注释","*****************订单备注字段的输入框代码*****************")}
	${lz:set("注释","before$csoRemark和after$csoRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoRemark==true">
	${before$csoRemark}
	<dl class="csoRemark " minor  ref="csoRemark" style="width:98%;">
		<dt>订单备注:</dt>
		<s:if test="#request.CTRL.e.csoRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csOrder$csoRemark!=null">${csOrder$csoRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csoRemark" name="csOrder.csoRemark" rows="5">${csOrder.csoRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统订单的订单备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单备注字段非编辑模式或只读时的显示****")}
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
	${after$csoRemark}
	</s:if>
	
	${lz:set("注释","*****************历史日志字段的输入框代码*****************")}
	${lz:set("注释","before$csoLog和after$csoLog变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoLog==true">
	${before$csoLog}
	<dl class="csoLog " minor  ref="csoLog" style="width:98%;">
		<dt>历史日志:</dt>
		<s:if test="#request.CTRL.e.csoLog==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOrder$csoLog!=null">${csOrder$csoLog}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="512" name="csOrder.csoLog" id="csoLog"  value="${csOrder.csoLog}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统订单的历史日志</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****历史日志字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csoLog">${csOrder.csoLog}</textarea>
		 		<span>
		 	
			 ${csOrder.csoLog$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoLog}
	</s:if>
	
	${lz:set("注释","*****************操作人字段的输入框代码*****************")}
	${lz:set("注释","before$csoEditor和after$csoEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoEditor==true">
	${before$csoEditor}
	<dl class="csoEditor " major  ref="csoEditor" >
		<dt>操&nbsp;&nbsp;作&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csoEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOrder$csoEditor!=null">${csOrder$csoEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csoEditor" name="csOrder.csoEditor">
		 				<option selected value="${csOrder.csoEditor}">
		 					${get:SrvUser(csOrder.csoEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csoEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csoEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****操作人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoEditor">${csOrder.csoEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csOrder.csoEditor}',{ctrl:{editable:false,visible:true}})">
			 ${csOrder.csoEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoEditor}
	</s:if>
	
	${lz:set("注释","*****************程序掩码字段的输入框代码*****************")}
	${lz:set("注释","before$csoMask和after$csoMask变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoMask==true">
	${before$csoMask}
	<dl class="csoMask " major  ref="csoMask" style="width:98%;">
		<dt>程序掩码:</dt>
		<s:if test="#request.CTRL.e.csoMask==true">
		${lz:set("haveEditable",true)}
		<dd input="checkbox">
		<s:if test="#request.csOrder$csoMask!=null">${csOrder$csoMask}</s:if><s:else>
		 	<div class='wide input'>	
		 	&nbsp; 	
		 	<input type="hidden" value="#0#" name="csOrder.csoMask" id="csoMask"/>
			<label><input type="checkbox" ${lz:bitin(csOrder.csoMask,1) ? "checked='checked'" : ""} value="1" ref="csoMask"/>催还车短信已发&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:bitin(csOrder.csoMask,2) ? "checked='checked'" : ""} value="2" ref="csoMask"/>设置不发还车短信&nbsp;&nbsp;&nbsp;</label>
		 </div>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统订单的程序掩码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****程序掩码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csoMask">${csOrder.csoMask}</textarea>
		 		<span>
		 	
			 ${csOrder.csoMask$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoMask}
	</s:if>
	
	${lz:set("注释","*****************下位机版本字段的输入框代码*****************")}
	${lz:set("注释","before$csoDeviceVesion和after$csoDeviceVesion变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoDeviceVesion==true">
	${before$csoDeviceVesion}
	<dl class="csoDeviceVesion " minor  ref="csoDeviceVesion" >
		<dt>下位机版本:</dt>
		<s:if test="#request.CTRL.e.csoDeviceVesion==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOrder$csoDeviceVesion!=null">${csOrder$csoDeviceVesion}</s:if><s:else>
		 	<select class="narrow" id="csoDeviceVesion" name="csOrder.csoDeviceVesion">
		 		<option value="">请选择</option>
				<option value="0" ${csOrder.csoDeviceVesion==0?"selected":""}>默认</option>
				<option value="1" ${csOrder.csoDeviceVesion==1?"selected":""}>测试</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择系统订单的下位机版本</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****下位机版本字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoDeviceVesion">${csOrder.csoDeviceVesion}</textarea>
		 		<span>
		 	
			 ${csOrder.csoDeviceVesion$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoDeviceVesion}
	</s:if>
	
	${lz:set("注释","*****************报警字段的输入框代码*****************")}
	${lz:set("注释","before$csoAlarm和after$csoAlarm变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoAlarm==true">
	${before$csoAlarm}
	<dl class="csoAlarm " minor  ref="csoAlarm" >
		<dt>报　　警:</dt>
		<s:if test="#request.CTRL.e.csoAlarm==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOrder$csoAlarm!=null">${csOrder$csoAlarm}</s:if><s:else>
		 	<select class="narrow" id="csoAlarm" name="csOrder.csoAlarm">
		 		<option value="">请选择</option>
				<option value="0" ${csOrder.csoAlarm==0?"selected":""}>默认</option>
				<option value="1" ${csOrder.csoAlarm==1?"selected":""}>超距</option>
				<option value="2" ${csOrder.csoAlarm==2?"selected":""}>超时</option>
				<option value="3" ${csOrder.csoAlarm==3?"selected":""}>断线</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择系统订单的报警</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****报警字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoAlarm">${csOrder.csoAlarm}</textarea>
		 		<span>
		 	
			 ${csOrder.csoAlarm$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoAlarm}
	</s:if>
	
	${lz:set("注释","*****************数据状态字段的输入框代码*****************")}
	${lz:set("注释","before$csoState和after$csoState变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoState==true">
	${before$csoState}
	<dl class="csoState " major  ref="csoState" >
		<dt>数据状态:</dt>
		<s:if test="#request.CTRL.e.csoState==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOrder$csoState!=null">${csOrder$csoState}</s:if><s:else>
		 	<select class="narrow" id="csoState" name="csOrder.csoState">
		 		<option value="">请选择</option>
				<option value="0" ${csOrder.csoState==0?"selected":""}>未发送</option>
				<option value="1" ${csOrder.csoState==1?"selected":""}>已发送</option>
				<option value="2" ${csOrder.csoState==2?"selected":""}>已收到</option>
				<option value="3" ${csOrder.csoState==3?"selected":""}>不发送</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统订单的数据状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****数据状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoState">${csOrder.csoState}</textarea>
		 		<span>
		 	
			 ${csOrder.csoState$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoState}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csoStatus和after$csoStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoStatus==true">
	${before$csoStatus}
	<dl class="csoStatus " major  ref="csoStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csoStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOrder$csoStatus!=null">${csOrder$csoStatus}</s:if><s:else>
		 	<select class="narrow" id="csoStatus" name="csOrder.csoStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csOrder.csoStatus==0?"selected":""}>已预定</option>
				<option value="1" ${csOrder.csoStatus==1?"selected":""}>使用中</option>
				<option value="2" ${csOrder.csoStatus==2?"selected":""}>已还车</option>
				<option value="3" ${csOrder.csoStatus==3?"selected":""}>已取消</option>
				<option value="4" ${csOrder.csoStatus==4?"selected":""}>已完成</option>
				<option value="5" ${csOrder.csoStatus==5?"selected":""}>待处理</option>
				<option value="6" ${csOrder.csoStatus==6?"selected":""}>已退款</option>
				<option value="7" ${csOrder.csoStatus==7?"selected":""}>已作废</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统订单的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoStatus">${csOrder.csoStatus}</textarea>
		 		<span>
		 	
			 ${csOrder.csoStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csOrder.csoId}" name="csOrder.csoId" id="csoId" />
					</s:if>			
					<s:if test="#request.haveEditable==true">
						${lz:set("submitsCount",lz:size(CTRL.submits))}
						<s:if test="#request.submitsCount>0">
							<s:iterator value="#request.CTRL.submits" id="submit" status="i">
					<button class="button" type="submit" name="submiter" value="${submit.value}">&nbsp;&nbsp;${submit.name}&nbsp;&nbsp;</button>
							</s:iterator>
						</s:if>
						<s:else>
					<s:if test="#request.complete!=null && #request.complete!=''">
						${lz:set("注释","****如果调用时传入了complete完成句柄，那么只能提交到本页面，由action控制调用该句柄****")}
						<button title="保存数据,不关闭窗口,刷新原列表" class="button" type="submit" name="submiter" value="保存">
							&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778030.png"/>
							保存&nbsp;&nbsp;</button>
					</s:if>
					<s:else>
						<button title="保存数据,关闭窗口,刷新原列表" class="button" type="submit" name="submiter" value="完成">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
						完成&nbsp;&nbsp;</button>
						<button title="保存数据,不关闭窗口,刷新原列表" class="button" type="submit" name="submiter" value="保存">
							&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778030.png"/>
							保存&nbsp;&nbsp;</button>
					</s:else>
						</s:else>
					<button class="button" type="reset" value="重置">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778045.png"/>
						重置&nbsp;&nbsp;</button>
					</s:if>
					<s:else>
					<button title="不保存数据,关闭窗口,不刷新原列表" class="button" onclick="cancel();" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
						确定&nbsp;&nbsp;</button>
					</s:else>
					<button title="不保存数据,关闭窗口,不刷新原列表" class="button" onclick="cancel();" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-multiply.png"/>
						取消&nbsp;&nbsp;</button>
				</center>
			</div>	
		</div>
		<div class="foot"></div>		
	</form>
</s:else>

<script>
	function cancel(){
		$.closeModalDialog();
	}
	function href(url,params){
		top.$.showModalDialog({url:url},params,window);
	}
</script>


<!------------------------LAZY3Q_JSP_BOTTOM------------------------>
<!------------------------LAZY3Q_JSP_BOTTOM------------------------>


</body>
</html>