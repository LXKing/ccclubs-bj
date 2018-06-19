<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 

<s:if test="csOrder.csoStatus>1 && csOrder.csoStatus!=5">
	${lz:set("message","只有已预订和正在执行的订单才能续订")}
	<lz:set name="jsonString">
		{
			title:"续订",
			editable:false,
			visible:false
		}
	</lz:set>
	${lz:set("CTRL",lz:CTRL(jsonString))}
</s:if>
<s:else>
	<lz:set name="jsonString">
		{
			title:"续订",
			editable:false,
			visible:false,
			action:"order_reorder.do",
			fields:{
				csoId:{editable:false,visible:true}
				,csoPayMember:{editable:false,visible:true}
				,csoUseMember:{editable:false,visible:true}
				,csoCar:{editable:false,visible:true}
				,csoFeeType:{editable:false,visible:true}
				,csoInsureType:{editable:false,visible:true}
				,csoStartTime:{editable:false,visible:true}
				,csoFinishTime:{editable:true,visible:true}
				,csoCostDetails:{editable:false,visible:true}
				,csoPayNeed:{editable:false,visible:true}
				,csoPayReal:{editable:false,visible:true}
				,csoMarginNeed:{editable:false,visible:true}
				,csoRemark:{editable:true,visible:true}
				,csoFreehour:{editable:true,visible:true}
				,csoPayCoin:{editable:false,visible:true}
				,csoPredict:{editable:false,visible:true}
			}
		}
	</lz:set>
	${lz:set("CTRL",lz:CTRL(jsonString))}
	<style>
	.timeline{clear:both;width:700px; height:28px; margin:0px auto;margin-bottom:10px;background-position:10px center; background-repeat:no-repeat;}
	</style>
	<script>
	$(function(){
		$("dl[ref=canMoney]").insertAfter("dl[ref=csoPayReal]");
		$("dl[ref=canFreehour]").insertAfter("dl[ref=csoPayReal]");
		$("dl[ref=canFreehour]").insertAfter("dl[ref=csoPayReal]");
		$("dl[ref=canCoin]").insertAfter("dl[ref=csoPayReal]");
		$("dl[ref=canMoney]").insertAfter("dl[ref=csoPayReal]");
		$("#conflict").appendTo(".form");
		
		$("#csoRemark").val("");
				
		var json = $.getObject("${basePath}admin/user/member_account.do?id=${csOrder.csoPayMember}&order=${csOrder.csoId}");
		var money = (json.money+${csOrder.csoPayNeed}+${csOrder.csoMarginNeed}+${csOrder.csoPredict}-${csOrder.csoPayCoin}).toFixed(2);
		$("#canMoney").next().html(money);
		$("#canMoney").val(money);
		
		$("#canFreehour").next().html(json.freehour+${csOrder.csoFreehour>0?csOrder.csoFreehour:0});
		$("#canFreehour").val(json.freehour+${csOrder.csoFreehour>0?csOrder.csoFreehour:0});
		
		$("#canCoin").next().html(json.coin+${csOrder.csoPayCoin>0?csOrder.csoPayCoin:0});
		$("#canCoin").val(json.coin+${csOrder.csoPayCoin>0?csOrder.csoPayCoin:0});
				
		$("<div class='timeline'></div>").insertBefore("dl[ref=csoStartTime]");
		$(".timeline").css("background-image","url(${basePath}admin/object/car_timeline.do?id=${csOrder.csoCar}&mm=${lz:uuid()})");
		$("#csoFinishTime").change(onOrderChange);
		$("#csoFreehour").blur(onOrderChange);	
		$("#csoFinishTime").attr("onclick","").click(function(){
			WdatePicker(getFinishDateParams());
		});
		$("#orderForm").submit(function(event){
			if($("#conflict").val()=="true"){
				$.tips("当前订单所选时间与其它订单冲突，请重新选择时间");
				return false;
			}
		});
	});
	function getFinishDateParams(){
		var h1 = 1000*60*60;//1小时
		var m10 = 1000*60*10;//1小时
		var d30=1000*60*60*24*30;//30天
		var orderDate = $.dateFormat("<s:date name="csOrder.csoFinishTime" format="yyyy-MM-dd HH:mm:ss"/>","yyyy-MM-dd HH:mm:ss");
		var minDate = new Date(orderDate.getTime()+m10);//最小10分钟
		var startDate = new Date(orderDate.getTime()+h1);//默认1小时
		var maxDate = new Date(minDate.getTime()+d30);
		return {
			el:"csoFinishTime",
			minDate:$.formatDate("yyyy-MM-dd HH:mm:ss",minDate),
			maxDate:$.formatDate("yyyy-MM-dd HH:mm:ss",maxDate),
			alwaysUseStartDate:true,
			startDate:$.formatDate("yyyy-MM-dd HH:mm:00",startDate),
			dateFmt:'yyyy-MM-dd HH:mm:00',
			disabledDates:['....-..-.. ..\:.[1-9]\:..']
		};
	}
	function onOrderChange(){
		if(!empty($("#csoFinishTime"))){
		
			//拆分订单获取相关信息
			var json = $.getObject("${basePath}admin/service/order_split.do",{
				"csOrder.csoId":$("#csoId").val(),
				"csOrder.csoFinishTime":$("#csoFinishTime").val(),
				"csOrder.csoFreehour":$("#csoFreehour").val()			
			},function(json){
				if($("dl[ref=csoPayNeed] .state-input span").size()==0)
					$("<span></span>").appendTo("dl[ref=csoPayNeed] .state-input");
				$("form dl[ref=csoPayNeed] .state-input span").html(json.price);
				$("#csoPayNeed").val(json.price);
				
				$("#csoFreehour").val(json.freehour);
				
				$("#canCoin").next().html(json.cancoin);
				$("#canCoin").val(json.cancoin);
				
				$("#csoPayCoin").next().html(json.usecoin);
				$("#csoPayCoin").val(json.usecoin);
				
				$("#conflict").val(json.conflict);
				
				if($("dl[ref=csoPredict] .state-input span").size()==0)
					$("<span></span>").appendTo("dl[ref=csoPredict] .state-input");
				$("#csoPredict").next().html(json.predict);
				$("#csoPredict").val(json.predict);
			});
		
			var html="<iframe class='state-input' onload=\"var d=$(this.contentWindow.document);var b=d.find('body');$(this).attr('height',d.height());if(d.height()>b.height())$(this).attr('height',d.height()+(d.height()-b.height()));\" id='iframe_${uuid}' name='iframe_${uuid}' frameborder='0' width='620' height='0'></iframe>";
			$(".form dl[ref=csoCostDetails] dd").html(html);
			var url = "${basePath}admin/service/orderdetail_dynamic.do";
			var params = getFormParams();
			params.dynamicSplit=true;
			params.all=true;
			params.canQuery=false;
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
					csodId:{listable:false},
					csodOutlets:{listable:false},
					csodModel:{listable:false},
					csodCar:{listable:false}
				}
			};
			$.submit(url,params,$(".form iframe").attr("name"));
		}	
	}
	</script>
</s:else>