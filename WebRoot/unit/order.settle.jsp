<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 

	<div id="compute" style="clear:both;font-size:16px;margin:0px auto;width:550px;display:none;">
		请仔细填写企业订单相关信息，"*" 为必填选项。
	</div>
	<script>
	<!--
	$(function(){
		$("#compute").insertBefore("#orderForm .line");
	});
	//-->
	</script>

	<s:if test="csUnitOrder.csuoStatus!=0">
		${lz:set("message","只有状态为已下单的订单才能进行结算操作")}
		<lz:set name="jsonString">
			{
				title:"结算订单",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("controller",lz:eval(jsonString))}
	</s:if>
	<s:elseif test="csUnitOrder.$csuoOrder.csoStatus!=4">
		${lz:set("message","只有系统订单状态为已完成的订单才能进行结算操作")}
		<lz:set name="jsonString">
			{
				title:"结算订单",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("controller",lz:eval(jsonString))}
	</s:elseif>
	<s:else>
		<lz:set name="jsonString">
			{
				title:"结算订单",
				editable:false,
				visible:false,
				action:"order.settle.do",
				fields:{
					csuoId:{editable:false,visible:false}
					,csuoUnitChild:{editable:false,visible:true}
					,csuoCar:{editable:false,visible:true}
					,csuoUsername:{editable:false,visible:true}
					,csuoMobile:{editable:false,visible:true}
					,csuoAlias:{editable:false,visible:true}
					,csuoIdcard:{editable:false,visible:true}
					//,csuoDayPrice:{editable:false,visible:true}
					//,csuoHourPrice:{editable:false,visible:true}	
					//,csuoKmPrice:{editable:false,visible:true}				
					,csuoStartTime:{editable:false,visible:true}
					,csuoFinishTime:{editable:false,visible:true}
					,csuoRetTime:{editable:true,visible:true}
					//,csuoDay:{editable:false,visible:true}
					//,csuoHour:{editable:false,visible:true}
					,csuoFreeHour:{editable:true,visible:true}
					,csuoMileage:{editable:true,visible:true}
					,csuoFreeKm:{editable:true,visible:true}
					,csuoPayNeed:{editable:false,visible:true}
					,csuoPayReal:{editable:true,visible:true}
					,csuoRemark:{editable:true,visible:true}
				},
				submits:[{name:"结算订单",value="结算订单"}]
			}
		</lz:set>
		${lz:set("controller",lz:eval(jsonString))}
	</s:else>
	
	<script>
	$(function(){	
		$("#csuoRetTime").change(onOrderChange);
		$("#csuoMileage,#csuoFreeHour,#csuoFreeKm").blur(onOrderChange);	
		$("#csuoMileage").val("${csUnitOrder.$csuoOrder.csoFuel}");
		$("#csuoRetTime").val("${lz:date(csUnitOrder.$csuoOrder.csoRetTime,"yyyy-MM-dd HH:mm:ss")}");
		$("#csuoRetTime").attr("onclick","").click(function(){
			WdatePicker(getRetDateParams());
		});	
		$("<b>*</b>").insertAfter("#csuoRetTime");
		$("<dl><dt></dt><dd><input type='checkbox' name='checkbox' id='checkbox' value='true'/>不发送结算短信</dd></dl>").insertAfter("dl[ref=csuoRemark]");
	});
	function getRetDateParams(){
		var h1 = 1000*60*60;//1小时
		var d30=1000*60*60*24*7;//30天
		var minDate = $.dateFormat("<s:date name="csUnitOrder.csuoStartTime" format="yyyy-MM-dd HH:mm:ss"/>","yyyy-MM-dd HH:mm:ss");
		minDate = new Date(minDate.getTime()+h1);
		var maxDate = new Date(minDate.getTime()+d30);
		return {
			el:"csuoRetTime",
			minDate:$.formatDate("yyyy-MM-dd HH:mm:ss",minDate),
			maxDate:$.formatDate("yyyy-MM-dd HH:mm:ss",maxDate),
			alwaysUseStartDate:true,
			dateFmt:'yyyy-MM-dd HH:mm:00'
			//,disabledDates:['....-..-.. ..\:.[1-9]\:..']
		};
	}
	function onOrderChange(){
		if(empty($("#csuoRetTime,#csuoMileage")))
			return;		
		//到服务器验证帐户余额是否足够，订单时间是否有冲突
		var json = $.getObject("${basePath}unit/order.verify.do",{
			"csUnitOrder.csuoId":$("#csuoId").val(),
			"csUnitOrder.csuoRetTime":$("#csuoRetTime").val(),
			"csUnitOrder.csuoMileage":$("#csuoMileage").val(),
			"csUnitOrder.csuoFreeKm":$("#csuoFreeKm").val(),
			"csUnitOrder.csuoFreeHour":$("#csuoFreeHour").val()
		});
		$("#compute").show();
		if(!json.success){
			$("#compute").html("<font color='red'>"+json.message+"</font>");
		}else{
			var strInfo = "天租价格({dayPrice})*天数({day})+小时价格({hourPrice})*小时数({hour})+里程价格({mileage})*里程数({km})=应付额({total})";
			strInfo = strInfo.replace("{dayPrice}",json.dayPrice);
			strInfo = strInfo.replace("{day}",json.day);
			strInfo = strInfo.replace("{hourPrice}",json.hourPrice);
			strInfo = strInfo.replace("{hour}",json.hour);
			strInfo = strInfo.replace("{total}",json.total);
			strInfo = strInfo.replace("{mileage}",json.mileage);
			strInfo = strInfo.replace("{km}",json.km);
			$("#compute").html(strInfo);
			
			$("#csuoDay").val(json.day);
			$("form dl[ref=csuoDay] .state-input span").html(json.day+"天");
			$("#csuoHour").val(json.hour);
			$("form dl[ref=csuoHour] .state-input span").html(json.hour+"小时");
					
			$("#csuoPayReal").val(json.total);	
			$("#csuoPayNeed").val(json.total);
			$("form dl[ref=csuoPayNeed] .state-input span").html(json.total+"元");			
		}		
	}
	</script>