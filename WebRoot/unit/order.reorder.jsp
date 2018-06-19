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
<!--
$(function(){
	$("#compute").insertBefore("#orderForm .line");
});
//-->
</script>

<s:if test="csUnitOrder.$csuoOrder.csoStatus>1">
	${lz:set("message","只有在系统订单状态为已预订和正在执行的订单才能续订")}
	<lz:set name="jsonString">
		{
			title:"续订",
			editable:false,
			visible:false
		}
	</lz:set>
	${lz:set("controller",lz:eval(jsonString))}
</s:if>
<s:else>
	<lz:set name="jsonString">
		{
			title:"续订",
			editable:false,
			visible:false,
			action:"order.reorder.do",
			fields:{
				csuoId:{editable:false,visible:false}
				,csuoUnitChild:{editable:false,visible:true}
				,csuoCar:{editable:false,visible:true}
				,csuoUsername:{editable:false,visible:true}
				,csuoMobile:{editable:false,visible:true}
				<s:if test="csUnitOrder.csuoType==0">
				,csuoAlias:{editable:false,visible:true}
				,csuoIdcard:{editable:false,visible:true}
				</s:if>				
				,csuoStartTime:{editable:false,visible:true}
				,csuoFinishTime:{editable:true,visible:true}
				,csuoPayNeed:{editable:false,visible:true}
			},
			submits:[{name:"完成",value="完成"}]
		}
	</lz:set>
	${lz:set("controller",lz:eval(jsonString))}
	<script>
	$(function(){		
		$("#csuoFinishTime").attr("onclick","").click(function(){
			WdatePicker(getFinishDateParams());
		});
		$("#csuoFinishTime").change(onOrderChange);	
		$("#csuoFreeHour").blur(onOrderChange);
	});
	function getFinishDateParams(){
		var h1 = 1000*60*60;//1小时
		var m10 = 1000*60*10;//1小时
		var d30=1000*60*60*24*7;//7天
		var orderDate = $.dateFormat("<s:date name="csUnitOrder.csuoFinishTime" format="yyyy-MM-dd HH:mm:ss"/>","yyyy-MM-dd HH:mm:ss");
		var minDate = new Date(orderDate.getTime()+m10);//最小10分钟
		var startDate = new Date(orderDate.getTime()+h1);//默认1小时
		var maxDate = new Date(minDate.getTime()+d30);
		return {
			el:"csuoFinishTime",
			minDate:$.formatDate("yyyy-MM-dd HH:mm:ss",minDate),
			maxDate:$.formatDate("yyyy-MM-dd HH:mm:ss",maxDate),
			alwaysUseStartDate:true,
			startDate:$.formatDate("yyyy-MM-dd HH:mm:00",startDate),
			dateFmt:'yyyy-MM-dd HH:mm:00',
			disabledDates:['....-..-.. ..\:.[1-9]\:..']
		};
	}
	function onOrderChange(){
		if(empty($("#csoFinishTime")))
			return;	
		//到服务器验证帐户余额是否足够，订单时间是否有冲突
		var json = $.getObject("${basePath}unit/order.verify.do",{
			"csUnitOrder.csuoId":$("#csuoId").val(),
			"csUnitOrder.csuoFinishTime":$("#csuoFinishTime").val(),
			"csUnitOrder.csuoFreeHour":$("#csuoFreeHour").val()
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
			
			<s:if test="csUnitOrder.csuoType==0">
			{
				var strInfo = "天租价格({dayPrice})*天数({day})+小时价格({hourPrice})*小时数({hour})=应付额({total})";
				strInfo = strInfo.replace("{dayPrice}",json.info.dayPrice);
				strInfo = strInfo.replace("{day}",json.info.day);
				strInfo = strInfo.replace("{hourPrice}",json.info.hourPrice);
				strInfo = strInfo.replace("{hour}",json.info.hour);
				strInfo = strInfo.replace("{total}",json.info.total);
				$("#compute .proxy").html(strInfo);
			}
			{
				$("#csuoDay").val(json.info.day);
				$("form dl[ref=csuoDay] .state-input span").html(json.info.day+"天");
				$("#csuoHour").val(json.info.hour);
				$("form dl[ref=csuoHour] .state-input span").html(json.info.hour+"小时");
				$("#csuoPayNeed").val(json.info.total);
				$("form dl[ref=csuoPayNeed] .state-input span").html(json.info.total+"元");
			}
			</s:if>
		}
		
	}
	</script>
</s:else>