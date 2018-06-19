<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 

	<s:if test="csOrder.csoStatus!=5">
		${lz:set("message","只有待处理的订单才能进行结算操作")}
		<lz:set name="jsonString">
			{
				title:"结算订单",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:if>
	<s:else>
		<lz:set name="jsonString">
			{
				title:"结算订单",
				editable:false,
				visible:false,
				action:"order_settle.do",
				fields:{
					csoId:{editable:false,visible:true}
					,csoPayMember:{editable:false,visible:true}
					,csoUseMember:{editable:false,visible:true}
					,csoCar:{editable:false,visible:true}
					,csoFeeType:{editable:false,visible:true}
					,csoStartTime:{editable:false,visible:true}
					,csoFinishTime:{editable:false,visible:true}
					,csoTakeTime:{editable:false,visible:true}
					,csoRetTime:{editable:true,visible:true}
					,csoFuel:{editable:true,visible:true}
					,csoElectric:{editable:true,visible:true}				
					,csoCostDetails:{editable:false,visible:true}				
					,csoPayNeed:{editable:false,visible:true}
					,csoPayReal:{editable:false,visible:true}
					,csoRemark:{editable:true,visible:true}
					,csoPayCoin:{editable:false,visible:true}
				},
				submits:[{name:"结算订单",value="结算订单"}]
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:else>
	
	<script>
	$(function(){	
		$("#csoRetTime").change(onOrderChange);	
		$("#csoFuel,#csoElectric").blur(onOrderChange);
		$("dl[ref=canCoin]").insertAfter("dl[ref=csoPayReal]");
		$("#csoRetTime").attr("onclick","").click(function(){
			WdatePicker(getRetDateParams());
		});	
		$("<b>*</b>").insertAfter("#csoRetTime");	
		$("<dl><dt></dt><dd><input type='checkbox' name='checkbox' id='checkbox' value='true'/>不发送结算短信</dd></dl>").insertAfter("dl[ref=csoRemark]");
	});
	function getRetDateParams(){
		var h1 = 1000*60*60;//1小时
		var d30=1000*60*60*24*30;//30天
		var minDate = $.dateFormat("<s:date name="csOrder.csoStartTime" format="yyyy-MM-dd HH:mm:ss"/>","yyyy-MM-dd HH:mm:ss");
		minDate = new Date(minDate.getTime());
		var maxDate = new Date(minDate.getTime()+d30);
		return {
			el:"csoRetTime",
			minDate:$.formatDate("yyyy-MM-dd HH:mm:ss",minDate),
			//maxDate:$.formatDate("yyyy-MM-dd HH:mm:ss",maxDate),
			//alwaysUseStartDate:true,
			dateFmt:'yyyy-MM-dd HH:mm:00'
			//,disabledDates:['....-..-.. ..\:.[1-9]\:..']
		};
	}
	function onOrderChange(){
		if(!empty($("#csoRetTime,#csoFuel,#csoElectric,#csoPayReal"))){
			var html="<iframe class='state-input' onload=\"var d=$(this.contentWindow.document);var b=d.find('body');$(this).attr('height',d.height());if(d.height()>b.height())$(this).attr('height',d.height()+(d.height()-b.height()));\" id='iframe_${uuid}' name='iframe_${uuid}' frameborder='0' src='#' width='620' height='0'></iframe>";
			$(".form dl[ref=csoCostDetails] dd").html(html);
			var url = "${basePath}admin/service/orderdetail_dynamic.do";
			var params = getFormParams();
			params.all=true;
			params.ctrl={
				mutual:'dialog',
				queryable:false,
				editable:false,
				canAdd:false,
				canEdit:false,
				canDel:false,
				canView:false,
				fields:{
					csodPricer:{listable:false},
					csodAddTime:{listable:false},
					csodOrder:{listable:false},
					csodId:{listable:false}
				}
			};			
			$.submit(url,params,$(".form iframe").attr("name"));
			$(".form iframe").load(function(){
				var frame = $(this).get(0);
				if($(frame.contentWindow.document).size()){
					var framedocument = $(frame.contentWindow.document);
					var tds = framedocument.find("#orderdetail_table tbody td[ref=csodTotalReal]");
					var totalPrice = 0;
					$.each(tds,function(i,o){
						totalPrice+=Number($(o).text());
					});
					totalPrice=totalPrice.toFixed(2);
					$("form dl[ref=csoPayNeed] .state-input").html(totalPrice+"元");
					$("#csoPayReal").val(totalPrice);
				}				
			});
		}	
	}
	</script>