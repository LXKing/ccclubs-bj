<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 

<style>
	.timeline{clear:both;width:700px; height:28px; margin:0px auto;margin-bottom:10px;background-position:10px center; background-repeat:no-repeat;}
</style>
<div id="compute" style="margin:0px auto;width:650px;display:none;">
	<div>(余额<span class="money"></span>+红包<span class="coin"></span>=总可用<span class="total"></span>) 不能小于 （租车保证金<span class="margin"></span>+租金<span class="rent"></span>+预计里程<span class="predict"></span>=总需<span class="need"></span>)</div>
</div>
<script>
/*******************车辆选择**********************/
$(function(){
	$("#compute").insertBefore(".line").show();
	$("#csoUseMember").parents(".combox").attr("action","${basePath}admin/service/order_user.do?value={param}&payer={csoPayMember}");
	$("<div class='timeline'></div>").hide().insertBefore("dl[ref=csoStartTime]");
	$("form dl[ref=csoCar] dd .combox .face").unbind("click").click(function(){
		href("${basePath}admin/object/car_select.do",{});
	});
	$("#queryForm input").change(function(){
		$("#queryForm").submit();
	});
	$("#queryForm input").keyup(function(){
		$("#queryForm").submit();
	});	
	$("<dl><dt></dt><dd><input type='checkbox' name='checkbox' id='checkbox' value='true'/>不发送取车短信</dd></dl>").insertBefore(".line");
});
/*******************车辆选择**********************/
</script>


<lz:set name="jsonString">
	{
		title:"添加订单",
		action:"order_add.do",
		submits:[{name:"完成",value="完成"}]
	}
</lz:set>
${lz:set("CTRL",lz:CTRL(jsonString))}

<script>
$(function(){
	$("dl[ref=defineMargin]").insertAfter("dl[ref=csoLongPrice]").hide();
	$("dl[ref=csoLongPrice]").hide();
	$("dl[ref=canFreehour]").insertAfter("dl[ref=csoPayReal]");
	$("dl[ref=canCoin]").insertAfter("dl[ref=csoPayReal]");
	$("dl[ref=canMoney]").insertAfter("dl[ref=csoPayReal]");
	$("#conflict").appendTo(".form");
	
	$("#csoPayMember,#csoUseMember,#csoCar,#csoFeeType,#csoInsureType,#csoStartTime,#csoFinishTime").change(onOrderChange);	
	$("#csoLongPrice,#csoFreehour,#defineMargin").blur(onOrderChange);
	$("#csoPayMember").change(function(){
		var member = $.getObject("${basePath}admin/user/member_query.do?object=true&csmId="+$(this).val())[0].object;
		var json = $.getObject("${basePath}admin/user/member_account.do?id="+$(this).val());
		$("#canMoney").next().html(json.money);
		$("#canMoney").val(json.money);
		$("#canFreehour").next().html(json.freehour);
		$("#canFreehour").val(json.freehour);
		
		$("#csoFeeType").val("${get:SrvHost(get:host()).shFeeType}");
		
		$("#compute .money").text(json.money);
		compute();
		
		if(member.csmVDrive==true && member.csmEvcard){
			$("#csoUseMember").val($(this).val());
			$("#csoUseMember").prev().html($(this).prev().html());
		}
		if(member.csmVReal!=1){
			$("#csoPayMember").say("支付会员["+$.trim($("#csoPayMember").prev().text())+"]没有实名认证，不能下单");
			$("#csoPayMember").val("");
			$("#csoPayMember").prev().html("请选择...");
			$("#csoUseMember").val("");
			$("#csoUseMember").prev().html("请选择...");
		}
		if(member.csmStatus!=1){
			$("#csoUseMember").say("使用会员["+$.trim($("#csoUseMember").prev().text())+"]已被禁用，不能下单");
			$("#csoUseMember").val("");
			$("#csoUseMember").prev().html("请选择...");
		}
	});
	$("#csoUseMember").change(function(){
		var member = $.getObject("${basePath}admin/user/member_query.do?object=true&csmId="+$(this).val())[0].object;
		if(member.csmVDrive!=1){
			$("#csoUseMember").say("使用会员["+$.trim($("#csoUseMember").prev().text())+"]驾驶认证审核未通过，不能下单");
			$("#csoUseMember").val("");
			$("#csoUseMember").prev().html("请选择...");
			return;
		}
		if(!member.csmEvcard){
			$("#csoUseMember").say("使用会员["+$.trim($("#csoUseMember").prev().text())+"]没有绑定EV卡，不能下单");
			$("#csoUseMember").val("");
			$("#csoUseMember").prev().html("请选择...");
			return;
		}
		if(member.csmStatus!=1){
			$("#csoUseMember").say("使用会员["+$.trim($("#csoUseMember").prev().text())+"]帐号已被禁用");
			$("#csoUseMember").val("");
			$("#csoUseMember").prev().html("请选择...");
			return;
		}
	});
	$("#csoCar").change(function(){
		$(".timeline").show().css("background-image","url(${basePath}admin/object/car_timeline.do?id="+$(this).val()+"&mm=${lz:uuid()})");
	});	
	$("#csoStartTime").attr("onclick","").click(function(){
		WdatePicker(getStartDateParams());
	});
	$("#csoFinishTime").attr("onclick","").click(function(){
		WdatePicker(getFinishDateParams());
	});	
	$("#orderForm").submit(function(event){
		var strError = "";
		if($("#conflict").val()=="true"){
			$.tips("当前订单所选时间与其它订单冲突，请重新选择时间");
			$.loading(false);
			return false;
		}
		var need = NUM($("#csoPayNeed").val())+NUM($("#csoMarginNeed").val())+NUM($("#csoPredict").val());		
		var total = NUM($("#canMoney").val())+NUM($("#canCoin").val());
		if(total<need)
			strError+=(strError==""?"":"\n")+"保证金+租金+预计里程小于可用余额+红包";
		if(strError!="" && !confirm(strError+"\n确定要下单吗？"))
			return false;
		return true;
	});
	if($("#csoPayMember").val()!=""){
		$("#csoPayMember").change();
	}
	if($("#csoUseMember").val()!=""){
		$("#csoUseMember").change();
	}
});
function NUM(text){
	if(text=="")return 0;
	return Number(text);
}
function M(className){
	var text = $.trim($("#compute "+className+":eq(0)").text());	
	if(text=="")return 0;
	return Number(text);
}
function compute(){	
	$("#compute .total").text($$(M(".money")+M(".coin")));
	$("#compute .need").text($$(M(".margin")+M(".rent")+M(".predict")));
}
function onSelectCar(id,name,outlets,outletsName){
	$("#csoOutlets").next("span").html(outletsName);
	$("#csoOutletsRet").setValue(outlets,outletsName);	
	$("#csoCar").setValue(id,name).change();
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
		disabledDates:['....-..-.. ..\:.[1-9]\:..']
	};
}
function getFinishDateParams(){
	var h1 = 1000*60*60;//1小时
	var dMax=1000*60*60*24*365;//365天
	var M10=1000*60*10;
	var nowTime = new Date(Math.floor(new Date().getTime()/M10)*M10+M10);//当前时间
	var minDate = empty($("#csoStartTime"))?nowTime:$.dateFormat($("#csoStartTime").val(),"yyyy-MM-dd HH:mm:ss");
	minDate = new Date(minDate.getTime()+h1);
	var maxDate = new Date(minDate.getTime()+dMax);
	return {
		el:"csoFinishTime",
		minDate:$.formatDate("yyyy-MM-dd HH:mm:ss",minDate),
		maxDate:$.formatDate("yyyy-MM-dd HH:mm:ss",maxDate),
		//alwaysUseStartDate:true,
		startDate:$.formatDate("yyyy-MM-dd HH:mm:00",minDate),
		dateFmt:'yyyy-MM-dd HH:mm:00',
		disabledDates:['....-..-.. ..\:.[1-9]\:..']
	};
}
function onOrderChange(){
	if(!empty($("#csoPayMember,#csoCar,#csoStartTime,#csoFinishTime"))){	
		//拆分订单获取相关信息
		var json = $.getObject("${basePath}admin/service/order_split.do",{
			"csOrder.csoPayMember":$("#csoPayMember").val(),
			"csOrder.csoCar":$("#csoCar").val(),
			"csOrder.csoOutletsRet":$("#csoOutletsRet").val(),
			"csOrder.csoStartTime":$("#csoStartTime").val(),
			"csOrder.csoFinishTime":$("#csoFinishTime").val(),
			"csOrder.csoLongPrice":$("#csoLongPrice").val(),
			"csOrder.csoFreehour":$("#csoFreehour").val(),
			"csOrder.csoFeeType":$("#csoFeeType").val(),
			"csOrder.csoInsureType":$("#csoInsureType").val(),
			"csOrder.csoMarginNeed":$("#csoMarginNeed").val(),
			"defineMargin":$("#defineMargin").val()
		},function(json){			
			if($("dl[ref=csoPayNeed] .state-input span").size()==0)
				$("<span></span>").appendTo("dl[ref=csoPayNeed] .state-input");
			$("form dl[ref=csoPayNeed] .state-input span").html(json.price);
			$("#csoPayNeed").val(json.price);
			
			$("#csoFreehour").val(json.freehour);
			
			$("#csoFeeType").val(json.feetype);
			
			$("#csoMarginNeed").next().html(json.margin);
			$("#csoMarginNeed").val(json.margin);
			
			$("#canCoin").next().html(json.cancoin);
			$("#canCoin").val(json.cancoin);
			
			$("#csoPayCoin").next().html(json.usecoin);
			$("#csoPayCoin").val(json.usecoin);
			
			$("#conflict").val(json.conflict);
			
			if($("dl[ref=csoPredict] .state-input span").size()==0)
				$("<span></span>").appendTo("dl[ref=csoPredict] .state-input");
			$("#csoPredict").next().html(json.predict);
			$("#csoPredict").val(json.predict);
			
			$("#csoFeeType").val(json.feetype);	
			
			if(json.longorder==true){
				$("dl[ref='csoLongPrice']").show();
				$("dl[ref='defineMargin']").show();				
			}
			
			$("#compute .margin").text(json.margin);
			$("#compute .rent").text(json.price);
			$("#compute .predict").text(json.predict);
			$("#compute .coin").text(json.cancoin);
			compute();
			
			if($.trim(json.error)!=""){
				Alert(json.error);
			}
			
		});
		
		//显示订单详情列表
		var html="<iframe class='state-input' onload=\"var d=$(this.contentWindow.document);var b=d.find('body');$(this).attr('height',d.height());if(d.height()>b.height())$(this).attr('height',d.height()+(d.height()-b.height()));\" id='iframe_${uuid}' name='iframe_${uuid}' frameborder='0' src='#' width='620' height='0'></iframe>";
		$(".form dl[ref=csoCostDetails] dd").html(html);
		var url = "${basePath}admin/service/orderdetail_dynamic.do";
		var params = getFormParams();
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
