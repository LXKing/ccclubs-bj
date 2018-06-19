<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<title>订单计算器</title>
</head>

<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>

	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>

<style>
#calculator .button{width:80px;height:80px;background-image:none;}
#calculator .lcd{background:#eeFFff;border:inset;position:relative;}
#calculator{margin:10px;}
#calculator .lcd .result{font-size:16px; font-weight:bold;padding:5px 5px;}
#calculator .lcd .item{width:50%;text-align:center;white-space:nowrap;}
#calculator .lcd .item span{}
</style>


<body scroll="no" style="overflow:hidden;">
		
	<table id="calculator" border="0" cellspacing="15" cellpadding="5">
	  <tr>
	    <td colspan="3">
			<div class="lcd">
				<table width="100%" border="0" cellspacing="0" cellpadding="5">
					<tr>
						<td class="item"><span tips="未选择网点车辆">未选择网点车辆</span><input id='csoCar' name='csOrder.csoCar' type='hidden'/></td>
						<td></td>
						<td class="item"><span tips="未选择保险类型">未选择保险类型</span><input id='csoInsureType' name='csOrder.csoInsureType' type='hidden'/></td>
					</tr>
					<tr>
						<td title="{保证金}+[租金]+(预计里程费)=最低余额" colspan="3" class="result" align="right">
							0.0
						</td>
					</tr>
					<tr>
						<td class="item"><span tips="未设置开始时间">未设置开始时间</span><input id='csoStartTime' name='csOrder.csoStartTime' type='hidden'/></td>
						<td>至</td>
						<td class="item"><span tips="未设置结束时间">未设置结束时间</span><input id='csoFinishTime' name='csOrder.csoFinishTime' type='hidden'/></td>
					</tr>
				</table>
			</div>
		</td>
	  </tr>
	  <tr>
	  	<td><button class="button" onclick="selectCar()" type="button">选择<br />车辆</button></td>
	    <td><button class="button" onclick="WdatePicker(getStartDateParams())" type="button">开始<br />时间</button></td>
	    <td><button class="button" onclick="WdatePicker(getFinishDateParams())" type="button">结束<br />时间</button></td>
	  </tr>
	  <tr>
	  	<td><button class="button" onclick="selectInsureType()" type="button">保险<br />类型</button></td>
	    <td><button class="button" onclick="resetAll();" type="button">清空</button></td>
	    <td><button class="button" onclick="$.closeModalDialog();" type="button">退出</button></td>
	  </tr>
	</table>
	
	<div class="hidden">
		<center class="InsureType">
			${lz:set("items", lz:query("ccclubs_system","select * from cs_product where csp_flag='INSURE' or csp_flag='SECURE'") )}
			<s:iterator value="#request.items" id="item" status="i">
				<button class="button" type="button" value="${item.csp_id}" style="width:200px;margin:10px;">${item.csp_name}</button><br/>
			</s:iterator>
				<button class="button" type="button" value=""  style="width:200px;margin:10px;">不选择保险</button>
		</center>
	</div>
	
<script>

showTips("${tips.value}");

function href(url,params){
	top.$.showModalDialog({url:url},params,window);
}


function getStartDateParams(){
	var M10=1000*60*10;
	var d7=1000*60*60*24*7;//7天
	var nowTime = new Date(Math.floor(new Date().getTime()/M10)*M10+M10);
	var minDate = nowTime;
	var maxDate = new Date(minDate.getTime()+d7);
	return {
		el:"csoStartTime",
		//minDate:$.formatDate("yyyy-MM-dd HH:mm:ss",minDate),
		//maxDate:$.formatDate("yyyy-MM-dd HH:mm:ss",maxDate),
		//alwaysUseStartDate:true,
		startDate:$.formatDate("yyyy-MM-dd HH:mm:00",nowTime),
		dateFmt:'yyyy-MM-dd HH:mm:00',
		disabledDates:['....-..-.. ..\:.[1-9]\:..'],
		onpicked:pickedStart
	};
}
function getFinishDateParams(){
	var h1 = 1000*60*60;//1小时
	var d30=1000*60*60*24*30;//30天
	var minDate = $.dateFormat($("#csoStartTime").val(),"yyyy-MM-dd HH:mm:ss");
	minDate = new Date(minDate.getTime()+h1);
	var maxDate = new Date(minDate.getTime()+d30);
	return {
		el:"csoFinishTime",
		minDate:$.formatDate("yyyy-MM-dd HH:mm:ss",minDate),
		maxDate:$.formatDate("yyyy-MM-dd HH:mm:ss",maxDate),
		//alwaysUseStartDate:true,
		dateFmt:'yyyy-MM-dd HH:mm:00',
		disabledDates:['....-..-.. ..\:.[1-9]\:..'],
		onpicked:pickedFinish
	};
}


function selectCar(){
	href("${basePath}admin/object/car_select.do",{});
}

function onSelectCar(id,name){
	$("#csoCar").val(id).prev("span").html(name);
	onOrderChange();
}

function pickedStart(){
	$("#csoStartTime").prev("span").html($("#csoStartTime").val());
	onOrderChange();
}

function pickedFinish(){
	$("#csoFinishTime").prev("span").html($("#csoFinishTime").val());
	onOrderChange();
}

function selectInsureType(){
	var InsureType = $(".InsureType").clone();
	InsureType.find("button").click(function(){
		$("#csoInsureType").val($(this).val()).prev("span").html($(this).html());
		top.$(InsureType).closeDialog();
		onOrderChange();
	});
	top.$(InsureType).dialog({title:"选择保险类型",modal:true,buttons:[]});
}

function resetAll(){
	$("#calculator .lcd .item").each(function(){
		$(this).find("input").val("");
		var span = $(this).find("span");
		span.html(span.attr("tips"));
		$(".result").html("0.0");
	});
}

function empty(inputs){
	var bEmpty = false;
	$.each(inputs,function(i,o){
		if($.trim($(o).val())==""){
			bEmpty=true;
		}
	});
	return bEmpty;
}

function onOrderChange(){
	if(!empty($("#csoCar,#csoStartTime,#csoFinishTime"))){
		//拆分订单获取相关信息
		var json = $.getObject("${basePath}admin/service/order_split.do",{
			"csOrder.csoCar":$("#csoCar").val(),
			"csOrder.csoStartTime":$("#csoStartTime").val(),
			"csOrder.csoFinishTime":$("#csoFinishTime").val(),
			"csOrder.csoInsureType":$("#csoInsureType").val()			
		},function(json){			
			var desc="{"+json.margin+"}+["+json.price+"]+("+json.predict+")="+$_$$(json.margin+json.price+json.predict);
			$(".result").html(desc);
		});		
	}	
}
function $_$$(v){
	return Number(v).toFixed(2);
}
</script>
	
		
</body>
</html>
