<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>网点地图监控</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
		
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script charset="utf-8" src="http://map.soso.com/api/v2/main.js"></script>
	
	<style type="text/css">
	#allmap{width:100%;height:100%;}
	#searcher{}
	#searcher dt{float:left;width:40px;}
	#searcher dd{padding-top:5px;}
	.map-status-info{width:400px;font-size:14px;margin:10px;}
	.map-status-info ul,.map-status-info li{list-style:none;margin:0px;padding:0px;line-height:25px;}
	.map-status-info ul{margin:10px 0;}
	.map-status-info ul .zt{display:block;width:100px;float:left;text-align:right;font-weight:bold;font-size:14px;}
	.map-status-info table{width:90%;margin:5px;text-align:center;background:#dddddd;}
	.map-status-info table td{background:#ffffff;padding:5px;}
	#options{text-align:center;}
	#options a{margin:5px;color:#000000;}
	#mapControl{margin:5px;width:220px;}
	#mapControl div{ height:100%; line-height:32px; float:left; padding:0px 10px;font-weight:bold;}
	#mapControl input{margin:2px;width:100px;float:left;}
	#mapControl button{background:url(/admin/images/icons/search.png) center no-repeat; border:0px;width:28px;height:28px;margin:5px 0px;cursor:pointer;}
	#elementTypes{width:230px;}
	#elementTypes .c{position:relative;border:0px;}
	#elementTypes .state-content{z-index:1;position:absolute;left:0px;top:0px;width:100%;height:100%;filter:alpha(opacity=85);opacity:0.85;}
	#elementTypes #layer{z-index:2;position:relative;padding:10px 0px;}
	#elementTypes td{font-size:14px;line-height:32px;}
	#elementTypes td .line{height:5px;background:none;border-bottom:1px solid #dddddd;}
	#elementTypes label{cursor:pointer;}
	#elementTypes label input{margin:0px 5px;}
	</style>
	
</head>  
<body>	
	<div id="allmap"></div>	
</body>

<script type="text/javascript">

var shows={
	showOutlets:true,
	showCars:true,
	showPiles:false,
	showGas:false
}

//地图对象
var map = new soso.maps.Map(document.getElementById('allmap'),{
	        center: new soso.maps.LatLng(${lz:or(defaultHost.shLocation,"30.276611,120.161665")}),//没有配置位置就定位到杭州
	        zoom: 11,
	        scrollWheel: true	        
	    });
	    
	    
new soso.maps.ScaleControl({
         align: soso.maps.ALIGN.BOTTOM_LEFT,
         margin: new soso.maps.Size(30, 15),
         map: map
     });

new soso.maps.NavigationControl({
            align: soso.maps.ALIGN.TOP_RIGHT,
            margin: new soso.maps.Size(5, 15),
            map: map
        });

	    
	    
//信息窗口
var infoWin = new soso.maps.InfoWindow({map: map});
function onMarker(who){	
	var marker = this.info ? this : who;
	infoWin.setPosition(marker.getPosition());
    $.ajax({
    	url:"map_info.do",
    	data:{number:marker.info.number},
    	type:"post",
    	cache:false,
    	success:function(data){
    		infoWin.open();    		
    		infoWin.setContent(data);
    	}
    });
}
function goMarker(number){
	onMarker(cars[number]);
}


<lz:set name="elementTypes">
<table id='elementTypes' class='shadow' border='0' cellspacing='0' cellpadding='0'><tr>  <td class='tl'></td>  <td class='t'></td>  <td class='tr'></td></tr><tr>  <td class='l'></td>  <td class='c'>
<div class="state-content"></div>
<div id="layer" style="margin:0px 12px;">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td width="16" align="center"><img width="16" src="${basePath}admin/images/state/marker.png?v=1"/></td>
	    <td width="*"><label style="color:#50b020;"><input id="showCars" ref="cars" type="checkbox" value="true"/>车辆信息</label></td>
	    <td width="40" align="center" style="color:#50b020;">{cars}</td>
	  </tr>
	  <tr>
	    <td colspan="3"><div class="line"></div></td>
	  </tr>
	  <tr>
	    <td width="16" align="center"><img width="25" src="${basePath}admin/images/state/pin-blue.png?v=2"/></td>
	    <td><label style="color:#0001c1;"><input id="showOutlets" ref="outlets" type="checkbox" value="true"/>网点信息</label></td>
	    <td align="center" style="color:#0001c1;">{outlets}</td>
	  </tr>
	   <!--
	  <tr>
	    <td colspan="3"><div class="line"></div></td>
	  </tr>
	  <tr>
	    <td width="16" align="center"><img width="25" src="${basePath}admin/images/state/gas.png?v=1"/></td>
	    <td><label style="color:#ee0000;"><input id="showGas" ref="gases" type="checkbox" value="true"/>加油站信息</label></td>
	    <td align="center" style="color:#ee0000;">{gases}</td>
	  </tr>
	  <tr>
	    <td colspan="3"><div class="line"></div></td>
	  </tr>
	  <tr>
	    <td width="16" align="center"><img width="18" src="${basePath}admin/images/state/pile.png?v=1"/></td>
	    <td><label style="color:#ffc000;"><input id="showPiles" ref="piles" type="checkbox" value="true"/>充电桩信息</label></td>
	    <td align="center" style="color:#ffc000;">{piles}</td>
	  </tr>
	  -->
	</table>
</div>
</td>  <td class='r'></td></tr><tr>  <td class='bl'></td>  <td class='b'></td>  <td class='br'></td></tr></table>
</lz:set>
var elementHtml = "${lz:js(elementTypes)}"
	.replace("{outlets}",parent.counts.outlets)
	.replace("{cars}",parent.counts.cars)
	.replace("{piles}",parent.counts.piles)
	.replace("{gases}",parent.counts.gases);
var elementTypes=$(elementHtml);
new soso.maps.Control({
    content:elementTypes.get(0),
    align: soso.maps.ALIGN.TOP_LEFT,
    map: map
});
$(function(){
	for(var type in shows){
		elementTypes.find("#"+type).attr("checked",shows[type]).click(function(){
			onRefresh($(this).attr("id"));
		});
	}
});
function onRefresh(type){
	setTimeout(function(){
		var pThis = elementTypes.find("#"+type);
		var bShow = shows[pThis.attr("id")]=pThis.is(":checked");
		var markers=window[pThis.attr("ref")];
		for(var o in markers){
			if(pThis.attr("ref")=="cars"){
				markers[o].setVisible(parent.cars[markers[o].info.number].show && bShow);
			}else{
				markers[o].setVisible(bShow);
			}
		}
	},100);
}


//获取图标
function getIcon(bOrder,bOnlie,bPower,bCharging,bBattery){
	if(!window.icons)
		window.icons=[];
	var sIcon = (bOrder?"1":"0")+"-"+(bOnlie?"1":"0")+"-"+(bPower?"1":"0")+"-"+(bCharging?"1":"0")+"-"+(bBattery?"1":"0");
	if(window.icons[sIcon])
		return window.icons[sIcon];
	var anchor = new soso.maps.Point(12, 32);
    var size = new soso.maps.Size(36, 39);
    var origin = new soso.maps.Point(0, 0);    
	var icon = window.icons[sIcon] = new soso.maps.MarkerImage("/admin/images/state/v2/marker-"+sIcon+".png?v=1", size, origin, anchor);
	return icon;	
}
function getShadow(bOrder,bOnlie,bPower,bCharging,bBattery){
	if(!window.shadows)
		window.shadows=[];
	var sShadow = (bOrder?"1":"0")+"-"+(bOnlie?"1":"0")+"-"+(bPower?"1":"0")+"-"+(bCharging?"1":"0")+"-"+(bBattery?"1":"0");
	if(window.shadows[sShadow])
		return window.shadows[sShadow];
	var anchor = new soso.maps.Point(0, 32);
    var size = new soso.maps.Size(36, 39);
    var origin = new soso.maps.Point(36, 0);    
	var shadow = window.shadows[sShadow] = new soso.maps.MarkerImage("/admin/images/state/v2/marker-"+sShadow+".png?v=1", size, origin, anchor);
	return shadow;	
}
var outletIcon=new soso.maps.MarkerImage(
   	"/admin/images/state/outlet-red.png", 
	new soso.maps.Size(32, 32), 
	new soso.maps.Point(0, 0), 
	new soso.maps.Point(12, 30)
);
var outletAndPowerIcon = new soso.maps.MarkerImage(
   	"/admin/images/state/pin-blue.png?v=2", 
	new soso.maps.Size(32, 32), 
	new soso.maps.Point(0, 0), 
	new soso.maps.Point(12, 30)
); 
var gasIcon=new soso.maps.MarkerImage(
   	"/admin/images/state/gas.png", 
	new soso.maps.Size(32, 32), 
	new soso.maps.Point(0, 0), 
	new soso.maps.Point(12, 30)
);
var pileIcon=new soso.maps.MarkerImage(
   	"/admin/images/state/pile.png", 
	new soso.maps.Size(32, 32), 
	new soso.maps.Point(0, 0), 
	new soso.maps.Point(12, 30)
);




//远程控制
function remote(operate,id){
	var controls={"开门":0,"关门":1};	
	var input = $("<input type='text' class='input' size='32' id='remark'/>");
	top.$(input).dialog({
		title:"请输入远程[<strong>"+operate+"</strong>]备注",
		modal:true,
		onOk:function(){
			if($.trim(input.val())==""){
				$.tips("请输入远程[<strong>"+operate+"</strong>]备注");
				return false;
			}
			$.ajax({
				url:"map_remote.do",
				cache:false,
				type:"post",
				data:{
					"id":id,
					"type":controls[operate],
					"remark":input.val()
				},
				dataType:"json",
				success:function(data){	
					Alert(data.message);			
				},
				error:function(){
				}
			});
		}
	});
	input.select();
}


var states = {};
function update(){
	$.getObject("map_states.do",{},function(data){
		states = {};
		for(var o in data)
			states[data[o].cssNumber]=data[o];		
		for(var o in data)
			delete data[o];
		data.length=0;
		
		for(var num in states){
			var csState = states[num];
			var number = "";
			if(csState.cssNumber.indexOf("EV")==0)
				number = csState.cssNumber.substring(2);
			else
				number = csState.cssNumber;
			
			var state = states[number];
			var evstate = states["EV"+number];
			if(!state)state=evstate;
			if(!evstate)evstate=state;			
			
			//var evstate = evs[state.cssNumber]||state;			
			if(!cars[number])continue;
			cars[number].setPosition(new soso.maps.LatLng(parseFloat(state.cssLatitude)-0.0023,parseFloat(state.cssLongitude)+0.0044));
			if(state.cssHost==2)
				cars[number].setPosition(new soso.maps.LatLng(parseFloat(state.cssLatitude)+0.0014,parseFloat(state.cssLongitude)+0.0058));
			var bOrder = ($.trim(state.cssOrder)!="" && $.trim(state.cssOrder)!="0");//是否有单
			var bOnline = (new Date().getTime()-state.cssAddTime)<(20*60*1000);//是否在线
			var bPower = state.cssPower>115;//蓄电电量是否充足
			var bCharging = $.trim(evstate.cssCharging)=="1";//是否正在充电
			var bBattery = cars[number].info.type==0 || $.trim(evstate.cssEvBattery)=="" || (parseInt(evstate.cssEvBattery)>30);//动力电量是否充足
			var bLongOrder = state.values.longOrder;
			cars[number].setIcon(getIcon(bOrder,bOnline,bPower,bCharging,bBattery));
			cars[number].setShadow(getShadow(bOrder,bOnline,bPower,bCharging,bBattery));
			cars[number].setTitle(number+(bOnline?"":" [掉线]")+(bPower?"":" [电量不足]"));	
			parent.update(number,bOrder,bOnline,bPower,bLongOrder,bBattery,bCharging);
		}
		
		
		
		/*states = data;
		var evs={};
		for(var o in states){
			var state = states[o];
			if(state.cssNumber.indexOf("EV")==0){
				evs[state.cssNumber.substring(2)]=state;
			}
		}
		for(var o in states){
			var state = states[o];
			var evstate = evs[state.cssNumber]||state;			
			if(!cars[state.cssNumber])continue;
			cars[state.cssNumber].setPosition(new soso.maps.LatLng(parseFloat(state.cssLatitude)-0.0023,parseFloat(state.cssLongitude)+0.0044));
			if(state.cssHost==2)
				cars[state.cssNumber].setPosition(new soso.maps.LatLng(parseFloat(state.cssLatitude)+0.0014,parseFloat(state.cssLongitude)+0.0058));
			var bOrder = ($.trim(state.cssOrder)!="" && $.trim(state.cssOrder)!="0");//是否有单
			var bOnline = (new Date().getTime()-state.cssAddTime)<(20*60*1000);//是否在线
			var bPower = state.cssPower>115;//蓄电电量是否充足
			var bCharging = $.trim(evstate.cssCharging)=="1";//是否正在充电
			var bBattery = cars[state.cssNumber].info.type==0 || $.trim(evstate.cssEvBattery)=="" || (parseInt(evstate.cssEvBattery)>30);//动力电量是否充足
			var bLongOrder = state.values.longOrder;
			cars[state.cssNumber].setIcon(getIcon(bOrder,bOnline,bPower,bCharging,bBattery));
			cars[state.cssNumber].setShadow(getShadow(bOrder,bOnline,bPower,bCharging,bBattery));
			cars[state.cssNumber].setTitle(state.cssNumber+(bOnline?"":" [掉线]")+(bPower?"":" [电量不足]"));	
			parent.update(state.cssNumber,bOrder,bOnline,bPower,bLongOrder,bBattery,bCharging);
		}*/
	});
}

</script>


<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 

<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>


<script>
var cars=[];
var outlets=[];
var piles=[];
var gases=[];
$(function(){
	setTimeout(init,1000);
});

function init(){
	//创建网点
	for(var o in parent.outlets){
		var outlet=parent.outlets[o];
		var piles = parent.outletsPiles[outlet.csoId]; 
		var m = outlets[outlets.length]=new soso.maps.Marker({
		    position:new soso.maps.LatLng(outlet.csoLatitude,outlet.csoLongitude),
		    map:map,
		    cursor:"pointer",
		    title:outlet.csoName,
		    icon:(piles && piles.length>0 ? outletAndPowerIcon : outletIcon)
		});	
		m.setVisible(shows["showOutlets"]);
		m.info = {outletsId:outlet.csoId};
// 		if(piles){
// 			var pileIds = "";
// 			for(var i=0;i<piles.length;i++){
// 				pileIds += (i==0?"":",")+piles[i].csppNo;
// 			}
// 		}

		soso.maps.event.addListener(m,"click", function(who){
				var marker = this.info ? this : who;
				infoWin.setPosition(marker.getPosition());
				
			    $.ajax({
			    	url:"map_powerInfo.do",
			    	data:{outletsId:marker.info.outletsId},
			    	type:"post",
			    	cache:false,
			    	success:function(data){
			    		infoWin.open();    		
			    		infoWin.setContent(data);
			    	}
			    });
		});
		
	}
		
	//创建加油站图标
	for(var o in parent.gases){
		var gas=parent.gases[o];
		var m = gases[gases.length]=new soso.maps.Marker({
		    position:new soso.maps.LatLng(gas.csgsLocation.split(",")[0],gas.csgsLocation.split(",")[1]),
		    map:map,
		    cursor:"pointer",
		    title:gas.csgsName,
		    icon:gasIcon
		});
		m.setVisible(shows["showGas"]);
	}
	
	
	//创建加油站图标
// 	for(var o in parent.piles){
// 		var pile=parent.piles[o];
// 		var m = piles[piles.length]=new soso.maps.Marker({
// 		    position:new soso.maps.LatLng(pile.csppLocation.split(",")[0],pile.csppLocation.split(",")[1]),
// 		    map:map,
// 		    cursor:"pointer",
// 		    title:pile.csppNo,
// 		    icon:pileIcon
// 		});
// 		m.setVisible(shows["showPiles"]);
// 		m.info={no:pile.csppNo, id:pile.csppId};
// 		soso.maps.event.addListener(m,"click", function(who){
// 				var marker = this.info ? this : who;
// 				infoWin.setPosition(marker.getPosition());
// 			    $.ajax({
// 			    	url:"map_powerInfo.do",
// 			    	data:{csppNo:marker.info.no, csppId:marker.info.id},
// 			    	type:"post",
// 			    	cache:false,
// 			    	success:function(data){
// 			    		infoWin.open();    		
// 			    		infoWin.setContent(data);
// 			    	}
// 			    });
// 		});
// 	}
	
	//创建车辆标记
	for(var o in parent.cars){
		var car=parent.cars[o];
		var marker = cars[car.cscId]=cars[car.cscNumber]=new soso.maps.Marker({
		    position:new soso.maps.LatLng(30.276611,120.161665),//定位到杭州
		    map:map,
		    cursor:"pointer",
		    icon:getIcon(false,false,false,false,false),
		    shadow:getShadow(false,false,false,false,false),
		    title:car.cscNumber
		});
		marker.setDraggable(true);
		marker.info={id:car.cscId,number:car.cscNumber,type:car.type};
		soso.maps.event.addListener(marker,"click",onMarker)
	}
	update();
	setInterval(update,30*1000);//定时器
}

</script>


</html>
