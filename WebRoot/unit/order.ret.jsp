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
		${lz:set("message","只有状态为已下单的订单才能进行还车操作")}
		<lz:set name="jsonString">
			{
				title:"远程还车",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("controller",lz:eval(jsonString))}
	</s:if>
	<s:elseif test="csUnitOrder.$csuoOrder.csoStatus!=1">
		${lz:set("message","只有系统订单状态为使用中的订单才能进行结算操作")}
		<lz:set name="jsonString">
			{
				title:"远程还车",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("controller",lz:eval(jsonString))}
	</s:elseif>
	<s:else>
		<lz:set name="jsonString">
			{
				title:"远程还车",
				editable:false,
				visible:false,
				action:"order.ret.do",
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
					//,csuoFreeHour:{editable:true,visible:true}
					//,csuoMileage:{editable:true,visible:true}
					//,csuoFreeKm:{editable:true,visible:true}
					//,csuoPayNeed:{editable:false,visible:true}
					//,csuoPayReal:{editable:true,visible:true}
					,csuoRemark:{editable:true,visible:true}
				},
				submits:[{name:"远程还车",value="远程还车"}]
			}
		</lz:set>
		${lz:set("controller",lz:eval(jsonString))}
	</s:else>
	
	<script>
	$(function(){
		$("#csuoMileage").val("${csUnitOrder.$csuoOrder.csoFuel}");
		$("#csuoRetTime").val("${lz:date(csUnitOrder.$csuoOrder.csoRetTime,"yyyy-MM-dd HH:mm:ss")}");
		$("#csuoRetTime").attr("onclick","").click(function(){
			WdatePicker(getRetDateParams());
		});	
		$("<b>*</b>").insertAfter("#csuoRetTime");
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
	</script>