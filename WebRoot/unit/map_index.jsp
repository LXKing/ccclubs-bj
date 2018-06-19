<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>网点地图监控</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
</head>  

<script type="text/javascript">
	window.basePath="${basePath}";
	window.contextPath="${basePath}${proname}/";
	window.apiUrl="${basePath}${proname}/api.do";
	window.handles={};
</script>

<style type="text/css">
body,html{overflow:hidden;}
#navigator{background:#ffffff;width:220px;float:left;height:100%;overflow:scroll;}
#mapframe{margin-left:200px;height:100%; overflow:hidden;}
#querypanel{position:fixed;width:200px;height:33px;top:0px;left:0px;z-index:9999;}
#querypanel .combox{margin:3px 2px 0px 5px;}
#querypanel .options{display:none;position:absolute;top:33px;left:0px;width:200px;box-shadow:0px 5px 5px #aaaaaa;padding-bottom:5px;}
#querypanel .options .line{height:10px;}
.table tbody td{font-size:12px;line-height:32px;}
.table tbody .focus td,.table tbody .odd,.table tbody .even{text-indent:1px;}
.table tbody td .ico{display:block;float:left;width:14px;height:14px;margin:10px 2px 0px 2px;background:center top no-repeat;background-repeat:no-repeat;}
.table tbody td .a{background-image:url(/admin/images/state/u_offline.gif);margin-left:40px;}
.table tbody td .b{background-image:url(/admin/images/state/signal_empty.gif);}
.table tbody td .c{background-image:url(/admin/images/state/power_empty.gif);}
.table tbody td .d{background-image:url(/admin/images/state/history.gif);}
.table tbody td .icon{cursor: pointer;display: inline-block;height: 22px;width: 22px;}
.table tbody td .link{font-size:14px;}
.table tbody td .o{color:#aaaaaa;}
</style>

<script>

window.counts={
	outlets:${lz:size(outlets)+0},
	cars:${lz:size(cars)+0},
	piles:${lz:size(piles)+0},
	gases:${lz:size(gases)+0}
}
var outlets=[];
<s:iterator value="#request.outlets" id="outlet" status="i">
outlets[${i.count-1}]={csoLatitude:${outlet.csoLatitude},csoLongitude:${outlet.csoLongitude},csoName:"${outlet.csoName}",csoId:"${outlet.csoId}"};
</s:iterator>

var cars={};
<s:iterator value="#request.cars" id="car" status="i">
cars["${car.cscNumber}"]={
	cscId:"${car.cscId}",
	cscNumber:"${car.cscNumber}",
	id:"${car.cscId}",
	number:"${car.cscNumber}",
	type:"${car.$cscModel.cscmType}",
	outlets:${car.cscOutlets},
	operate:${car.cscStatus==1},
	online:true,
	power:true,
	order:false,
	longorder:false,
	endurance:true,
	charge:true,
	show:true
};
</s:iterator>

var gases=[];
<s:iterator value="#request.gases" id="gas" status="i">
gases[${i.count-1}]={csgsName:"${gas.csgsName}",csgsLocation:"${gas.csgsLocation}"};
</s:iterator>

var piles=[], outletsPiles = {};
<s:iterator value="#request.piles" id="pile" status="i">
var record ={csppNo:"${pile.csppNo}", csppId:"${pile.csppId}",csppLocation:"${pile.csppLocation}", csppIndex:"${pile.csppIndex}"};
piles[${i.count-1}]= record;
if(outletsPiles["${pile.csppOutlets}"]){
	outletsPiles["${pile.csppOutlets}"].push(record);
}else{
	outletsPiles["${pile.csppOutlets}"] = [record];
}
</s:iterator>

function href(url,params){
	top.$.showModalDialog({url:url},params,window);
}

//刷新界面
function onRefresh(){
	var checkeds={};
	$("#querypanel .options input[type='checkbox']:checked").each(function(){
		checkeds[$(this).attr("name")]=$(this).val();
	});
	for(var o in cars){
		var bShow = true;
		if($("#outlets").val()!="" && cars[o].outlets!=$("#outlets").val()){
			bShow=false;
		}else if($("#keyword").val()!="" && "车辆搜索..."!=$("#keyword").val() && cars[o].number.toUpperCase().indexOf($("#keyword").val().toUpperCase())==-1){
			bShow=false;
		}
		var options = "operate,online,power,order,longorder,endurance,charge".split(",");
		for(var p in options){
			var name = options[p];
			if(checkeds[name] && checkeds[name]!=(""+cars[o][name]))
				bShow=false;
		}
		cars[o]["show"]=bShow;
		if(bShow)
			$("#"+o).show();
		else
			$("#"+o).hide();
	}
	$("tr[tree='0']").each(function(){
		if($(this).nextAll("tr[tree='"+$(this).attr("id")+"']:visible").size()==0)
			$(this).hide();
		else
			$(this).show();
	});
	window["sosomap"].onRefresh("showCars");
}


function update(number,bOrder,bOnline,bPower,bLongOrder,bEndurance,bCharge){

	cars[number]["online"]=bOnline;
	cars[number]["order"]=bOrder;
	cars[number]["power"]=bPower;
	cars[number]["longorder"]=bLongOrder;
	cars[number]["endurance"]=bEndurance;
	cars[number]["charge"]=bCharge;

	var tr = $("#"+number);
	tr.find(".a").css("background-image",bOrder?"url(/admin/images/state/u_online.gif)":"url(/admin/images/state/u_offline.gif)");
	tr.find(".b").css("background-image",bOnline?"url(/admin/images/state/signal_full.gif)":"url(/admin/images/state/signal_empty.gif)");
	tr.find(".c").css("background-image",bPower?"url(/admin/images/state/power_full.gif)":"url(/admin/images/state/power_empty.gif)");
}


function getOffLineRemark(carid){
	var csCarOn = CsCarOn.Get({cscoCar:carid});
	if(!csCarOn || $.trim(csCarOn.cscoRemark)==""){
		$(".car"+carid).say("该车辆可能未上线过或者未备注过下线原因",3000);
	}else{
		$(".car"+carid).say(csCarOn.cscoRemark,3000);
	}
}

</script>

<body  scroll="no">
	<div id="navigator">
		<div id="querypanel" class="state-header">
	 		<input type="input" style="width:180px;height:22px;float:left;margin:3px;" class="input" name="keyword" id="keyword" value="车辆搜索..."/>
	 		
	 		<div class="options state-content">
	 			<div class="line"></div>
	 			&nbsp;&nbsp;
	 			<label><input type="checkbox" name="operate" value="true"/>在运营的</label>
	 			&nbsp;&nbsp;
	 			<label><input type="checkbox" name="operate" value="false"/>非运营的</label>
		 		<div class="line"></div>
	 			&nbsp;&nbsp;
	 			<label><input type="checkbox" name="online" value="true"/>非掉线的</label>
	 			&nbsp;&nbsp;
	 			<label><input type="checkbox" name="online" value="false"/>已掉线的</label>
		 		<div class="line"></div>
		 		&nbsp;&nbsp;
	 			<label><input type="checkbox" name="power" value="false"/>低蓄电量</label>
	 			&nbsp;&nbsp;
	 			<label><input type="checkbox" name="power" value="true"/>非低蓄电量的</label>
		 		<div class="line"></div>
		 		&nbsp;&nbsp;
	 			<label><input type="checkbox" name="order" value="true"/>有订单的</label>
	 			&nbsp;&nbsp;
	 			<label><input type="checkbox" name="order" value="false"/>没订单的</label>
		 		<div class="line"></div>
		 		&nbsp;&nbsp;
	 			<label><input type="checkbox" name="longorder" value="true"/>有长租的</label>
	 			&nbsp;&nbsp;
	 			<label><input type="checkbox" name="longorder" value="false"/>非长租的</label>
		 		<div class="line"></div>
		 		&nbsp;&nbsp;
	 			<label><input type="checkbox" name="endurance" value="false"/>低续航的</label>
	 			&nbsp;&nbsp;
	 			<label><input type="checkbox" name="endurance" value="true"/>非低续航的</label>
		 		<div class="line"></div>
		 		&nbsp;&nbsp;
	 			<label><input type="checkbox" name="charge" value="true"/>在充电的</label>
	 			&nbsp;&nbsp;
	 			<label><input type="checkbox" name="charge" value="false"/>不在充电的</label>
	 		</div>
	 		
		</div>
		<table id="aoc_table" width="200" class="table" border="0" cellspacing="1" cellpadding="0">
			<thead>
			  	<tr>
			  		<td width="200" align="center"> 
			  			&nbsp;
			  		</td>
			  	</tr>
			</thead>
			<tbody>
				<s:iterator value="#request.outlets" id="outlet">
					<tr id="o${outlet.csoId}" expand="true" tree="0">
						<td class="node">
							<span class="icon tree-less" style="margin:7px 0px 0px 10px;display:block;float:left;"></span>
							${outlet.csoName}
						</td>
					</tr>
					<s:iterator value="#request.cars" id="car">
						<s:if test="#outlet.csoId==#car.cscOutlets">
						<tr class="carer" id="${car.cscNumber}" tree="o${car.cscOutlets}">
							<td class="node" title="${car.$cscOutlets.csoName$}">
								<em class='ico a'></em>
								<em class='ico b'></em>
								<em class='ico c'></em>
								<a class='link car${car.cscId}' onclick="sosomap.goMarker('${car.cscNumber}')" href="javascript:void(0);" title="${car.cscStatus==2?car.cscRemark:"在线运营"}"  style="${car.cscStatus==2?"text-decoration:line-through;":""}">${car.cscNumber}</a>
								<s:if test="#car.cscStatus==2">
								<a href="javascript:void(0);" title="点击显示下线原因" style="text-decoration:none;" onclick="getOffLineRemark(${car.cscId})">?</a>
								</s:if>
							</td>
						</tr>
						</s:if>
					</s:iterator>
				</s:iterator>
			</tbody>
		</table>
	</div>
	<div id="mapframe">
		<iframe  id="sosomap" src="map_soso.do" name="sosomap" frameborder="0" width="100%" height="100%"></iframe>
	</div>	
</body>


	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
			
	
	
	<script>
		$(function(){
			$("#querypanel").mouseenter(function(){
				$(this).find(".options").show();
			}).mouseleave(function(){
				$(this).find(".options").hide();
			});
			$("#outlets").change(onRefresh).siblings().click(function(){
				$("#querypanel").find(".options").hide();
			});
			$("#querypanel .options input[type='checkbox']").click(function(){
				$("#querypanel").find("input[name='"+$(this).attr("name")+"']").not($(this)).attr("checked",false);
				onRefresh();
			});
			$("#keyword").focus(function(){
				if($(this).val()=="车辆搜索..."){
					$(this).val("");
				}
				$("#querypanel").find(".options").hide();
			}).blur(function(){
				if($(this).val()==""){
					$(this).val("车辆搜索...");
				}
			}).keyup(onRefresh);
		});
		$(function(){
			$(".tree-less").toggle(function(){
				$("tr[tree="+$(this).parents("tr").attr("id")+"]").hide();
				$(this).removeClass("tree-less").addClass("tree-more");
			},function(){
				$("tr[tree="+$(this).parents("tr").attr("id")+"]").show();
				$(this).removeClass("tree-more").addClass("tree-less");
			});
		})
	</script>	

</html>
