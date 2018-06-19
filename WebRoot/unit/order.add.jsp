<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 


<style>
	.timeline{clear:both;width:700px; height:28px; margin:0px auto;margin-bottom:10px;background-position:10px center; background-repeat:no-repeat;}
	#compute div{color:red;font-size:16px;font-weight:bold;text-align:center;}
</style>

<div id="compute" style="clear:both;font-size:16px;margin:0px auto;width:720px;">
	<div class="message"></div>
	<div class="proxy"></div>
	<div class="actual"></div>
</div>
<script>
$(function(){
	$("#compute").insertBefore("#orderForm .line");
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



<s:if test="#request.controller.title=='代理订单'">
	<lz:set name="jsonString">
		{
			title:"代理订单",
			editable:false,
			visible:false,
			action:"order.add.do",
			fields:{
				csuoId:{editable:false,visible:false}		
				,csuoCar:{editable:true,visible:true}
				,csuoUsername:{editable:true,visible:true}
				,csuoAlias:{editable:true,visible:true}
				,csuoMobile:{editable:true,visible:true}
				,csuoUnitMember:{editable:true,visible:true}
				,csuoUnitChild:{editable:true,visible:true}
				,csuoIdcard:{editable:true,visible:true}
				,csuoDayPrice:{editable:false,visible:false}
				,csuoHourPrice:{editable:false,visible:false}					
				,csuoStartTime:{editable:true,visible:true}
				,csuoFinishTime:{editable:true,visible:true}
				,csuoDay:{editable:false,visible:false}
				,csuoHour:{editable:false,visible:false}			
				,csuoFreeHour:{editable:true,visible:true}
				,csuoFreeKm:{editable:true,visible:true}
				,csuoPayNeed:{editable:false,visible:true}
			},
			submits:[{name:"完成",value="完成"}]
		}
	</lz:set>
</s:if>
<s:elseif test="#request.controller.title=='公务用车'">
	<lz:set name="jsonString">
		{
			title:"公务用车",
			editable:false,
			visible:false,
			action:"order.add.do",
			fields:{
				csuoId:{editable:false,visible:false}
				<s:if test="#session.unit_login.$csuuUnitInfo.csuiType==2">
				,csuoType:{editable:true,visible:true}
				,csuoProfile:{editable:true,visible:true}
				</s:if>
				,csuoUnitMember:{editable:true,visible:true}
				,csuoUnitChild:{editable:true,visible:true}
				<s:if test="#session.unit_login.$csuuUnitInfo.csuiType!=2">
				,csuoUsername:{editable:true,visible:true}
				,csuoMobile:{editable:true,visible:true}
				</s:if>
				,csuoCar:{editable:true,visible:true}
				,csuoDayPrice:{editable:false,visible:false}
				,csuoHourPrice:{editable:false,visible:false}					
				,csuoStartTime:{editable:true,visible:true}
				,csuoFinishTime:{editable:true,visible:true}
				,csuoDay:{editable:false,visible:false}
				,csuoHour:{editable:false,visible:false}
				,csuoRemark:{editable:true,visible:true}
			},
			submits:[{name:"完成",value="完成"}]
		}
	</lz:set>
</s:elseif>


${lz:set("controller",lz:eval(jsonString))}

<script>
$(function(){
	if("${controller.title}"=='代理订单'){
		$("<input type='hidden' value='0' name='csUnitOrder.csuoType' id='csuoType'/>").appendTo(".form");
	}
	<s:if test="#session.unit_login.$csuuUnitInfo.csuiType!=2">
	if("${controller.title}"=='公务用车'){
		$("<input type='hidden' value='1' name='csUnitOrder.csuoType' id='csuoType'/>").appendTo(".form");
	}
	</s:if>
	$("#csuoUnitChild").change(function(){
		var member = $.getObject("${basePath}unit/user/member_info.do?object=true&id="+$(this).val())[0].object;
		if(member && member.csmVDrive!=true){
			$.tips("该用户没有驾驶认证，不能下订单");
			$("#csuoUnitChild").val("");
			$("#csuoUnitChild").prev().html("请选择...");
		}
		//$("#csuoMobile").val(member.csmMobile);
		//$("#csuoUsername").val(member.csmName);
	});
	$("#csuoCar").change(function(){
		<s:if test="#request.controller.title=='代理订单'">
		$("#csuoDayPrice").val("");$("#csuoHourPrice").val("");
		var json = $.getObject("${basePath}unit/order.price.do",{car:$(this).val()});
		if(json.success){
			if((json.dayPrice+json.hourPrice)>0){
				$("#csuoDayPrice").val(json.dayPrice);
				$("form dl[ref=csuoDayPrice] .state-input span").html(json.dayPrice+"元");
				$("#csuoHourPrice").val(json.hourPrice);
				$("form dl[ref=csuoHourPrice] .state-input span").html(json.hourPrice+"元");
			}else{
				$("#csuoCar").prev("span").html("请选择...");
				$("#csuoCar").val("");
				$.tips("对不起，所选车型价格配置不正确（天租价格和小时价格不能同时为0）");
			}
		}else{
			$("#csuoCar").prev("span").html("请选择...");
			$("#csuoCar").val("");
			$.tips(json.message);
		}
		</s:if>
	});
	$("#csuoCar").change(function(){
		$(".timeline").show().css("background-image","url(${basePath}unit/car.timeline.do?id="+$(this).val()+"&mm=${lz:uuid()})");
	});	
	$("#csuoStartTime").attr("onclick","").click(function(){
		WdatePicker(getStartDateParams());
	});
	$("#csuoFinishTime").attr("onclick","").click(function(){
		WdatePicker(getFinishDateParams());
	});	
	
	$("#csuoUnitChild,#csuoCar,#csuoStartTime,#csuoFinishTime").change(onOrderChange);
	$("#csuoFreeHour").blur(onOrderChange);
	
	$("#orderForm").submit(function(event){		
		return true;
	});
	
	$("<dl style='width:98%;'><dt style='height:22px;'></dt><dd><input type='checkbox' name='checkbox' id='checkbox' value='true'/>不发送取车短信</dd></dl>").insertBefore("#compute");
});
function getStartDateParams(){
	var M10=1000*60*10;
	var d7=1000*60*60*24*7;//7天
	var nowTime = new Date(Math.floor(new Date().getTime()/M10)*M10+M10);
	var minDate = nowTime;
	var maxDate = new Date(minDate.getTime()+d7);
	return {
		el:"csuoStartTime",
		minDate:$.formatDate("yyyy-MM-dd HH:mm:ss",minDate),
		maxDate:$.formatDate("yyyy-MM-dd HH:mm:ss",maxDate),
		alwaysUseStartDate:true,
		startDate:$.formatDate("yyyy-MM-dd HH:mm:00",nowTime),
		dateFmt:'yyyy-MM-dd HH:mm:00',
		disabledDates:['....-..-.. ..\:.[1-9]\:..']
	};
}
function getFinishDateParams(){
	var h1 = 1000*60*60;//1小时
	var dMax=1000*60*60*24*7;//7天
	var M10=1000*60*10;
	var nowTime = new Date(Math.floor(new Date().getTime()/M10)*M10+M10);//当前时间
	var minDate = empty($("#csuoStartTime"))?nowTime:$.dateFormat($("#csuoStartTime").val(),"yyyy-MM-dd HH:mm:ss");
	minDate = new Date(minDate.getTime()+h1);
	var maxDate = new Date(minDate.getTime()+dMax);
	return {
		el:"csuoFinishTime",
		minDate:$.formatDate("yyyy-MM-dd HH:mm:ss",minDate),
		maxDate:$.formatDate("yyyy-MM-dd HH:mm:ss",maxDate),
		alwaysUseStartDate:true,
		startDate:$.formatDate("yyyy-MM-dd HH:mm:00",minDate),
		dateFmt:'yyyy-MM-dd HH:mm:00',
		disabledDates:['....-..-.. ..\:.[1-9]\:..']
	};
}
function onOrderChange(){
	//如果车辆、开始时间、结束时间都不为空
	if(empty($("#csuoUnitChild,#csuoCar,#csuoStartTime,#csuoFinishTime")))
		return;
			
	//到服务器验证帐户余额是否足够，订单时间是否有冲突
	var json = $.getObject("${basePath}unit/order.verify.do",{
		"csUnitOrder.csuoUnitChild":$("#csuoUnitChild").val()
		,"csUnitOrder.csuoCar":$("#csuoCar").val()
		,"csUnitOrder.csuoStartTime":$("#csuoStartTime").val()
		,"csUnitOrder.csuoFinishTime":$("#csuoFinishTime").val()
		,"csUnitOrder.csuoFreeHour":$("#csuoFreeHour").val()
		,"csUnitOrder.csuoType":$("#csuoType").val()
	});
	if(!json.success){
		$("#compute .message").html(json.message);
	}else{
		$("#compute div").html("");
		
		var strData = "租车保证金{margin}+租金{rent}+预计里程{predic}=共{need}  (余额{canMoney})";
		strData = strData.replace("{margin}",json.data.margin);
		strData = strData.replace("{rent}",json.data.price);
		strData = strData.replace("{predic}",json.data.feeee);	
		strData = strData.replace("{need}",Number(json.data.margin+json.data.price+json.data.feeee).toFixed(2));	
		strData = strData.replace("{canMoney}",json.canMoney);
		$("#compute .actual").html(strData);
		
		if($("#csuoType").val()=="0"){
			var strInfo = "天租价格({dayPrice})*天数({day})+小时价格({hourPrice})*小时数({hour})=应付额({total})";
			strInfo = strInfo.replace("{dayPrice}",json.info.dayPrice);
			strInfo = strInfo.replace("{day}",json.info.day);
			strInfo = strInfo.replace("{hourPrice}",json.info.hourPrice);
			strInfo = strInfo.replace("{hour}",json.info.hour);
			strInfo = strInfo.replace("{total}",json.info.total);
			$("#compute .proxy").html(strInfo);
		}
		if($("#csuoType").val()=="0"){
			$("#csuoDay").val(json.info.day);
			$("form dl[ref=csuoDay] .state-input span").html(json.info.day+"天");
			$("#csuoHour").val(json.info.hour);
			$("form dl[ref=csuoHour] .state-input span").html(json.info.hour+"小时");
			$("#csuoPayNeed").val(json.info.total);
			$("form dl[ref=csuoPayNeed] .state-input span").html(json.info.total+"元");
		}
		
		if($.trim(json.error)!=""){
			Alert(json.error);
		}
			
	}
}
</script>
