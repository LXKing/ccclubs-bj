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
	#mapControl{margin:5px;}
	#mapControl div{ height:100%; line-height:32px; float:left; padding:0px 10px;font-weight:bold;}
	#mapControl input{margin:2px;width:100px;}
	#mapControl button{background:url(/admin/images/icons/search.png) center no-repeat; border:0px;width:28px;28px;margin:5px 0px;cursor:pointer;}
	</style>
	
</head>  
<body>	
	<div id="allmap"></div>	
</body>

<script type="text/javascript">

//地图对象
var map = new soso.maps.Map(document.getElementById('allmap'),{
	        center: new soso.maps.LatLng(parent.getLoction()[0],parent.getLoction()[1]),//定位到杭州
	        zoom: 10,
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
    	url:"${basePath}admin/control/gps_info.do",
    	data:{number:marker.info.number,cshsNumber:marker.info.number},
    	type:"post",
    	cache:false,
    	success:function(data){
    		infoWin.open();    		
    		infoWin.setContent(data);
    	}
    });
}
function goMarker(number){
	onMarker(markers[number]);
}


//地图自定义控件
var mapControl=$("<div id='mapControl' class='state-header'><div>车辆搜索:</div><input class='input' type='text' id=''/><button type='button'></button></div>");
new soso.maps.Control({
    content:mapControl.get(0),
    align: soso.maps.ALIGN.TOP_LEFT,
    map: map
});



//获取图标
function getIcon(bOrder,bOnlie,bPower){
	if(!window.icons)
		window.icons=[];
	var sIcon = (bOrder?"1":"0")+"-"+(bOnlie?"1":"0")+"-"+(bPower?"1":"0");
	if(window.icons[sIcon])
		return window.icons[sIcon];
	var anchor = new soso.maps.Point(12, 32);
    var size = new soso.maps.Size(36, 39);
    var origin = new soso.maps.Point(0, 0);    
	var icon = window.icons[sIcon] = new soso.maps.MarkerImage("/admin/images/state/marker-"+sIcon+".png?v=1", size, origin, anchor);
	return icon;	
}
function getShadow(bOrder,bOnlie,bPower){
	if(!window.shadows)
		window.shadows=[];
	var sShadow = (bOrder?"1":"0")+"-"+(bOnlie?"1":"0")+"-"+(bPower?"1":"0");
	if(window.shadows[sShadow])
		return window.shadows[sShadow];
	var anchor = new soso.maps.Point(0, 32);
    var size = new soso.maps.Size(36, 39);
    var origin = new soso.maps.Point(36, 0);    
	var shadow = window.shadows[sShadow] = new soso.maps.MarkerImage("/admin/images/state/marker-"+sShadow+".png?v=1", size, origin, anchor);
	return shadow;	
}

var states = null;
function update(callback){
	$.getObject("${basePath}admin/control/gps_states.do",{
		online:"${online}",
		gbs_key:"${key}"
	},function(data){
		states = data;
		//取出所有以EV开头的当前状态数据
		var evdatas={};
		for(var o in states){
			var state = states[o];
			if(state.cssNumber.indexOf("EV")!=0)continue;
			evdatas[state.cssNumber]=state;
		}
		//再把EV的数据覆盖回去 
		for(var o in states){
			var state = states[o];
			if(state.cssNumber.indexOf("EV")==0)continue;
			if(evdatas["EV"+state.cssNumber])
				states[o] = evdatas["EV"+state.cssNumber];
		}
		for(var o in states){
			var state = states[o];
			if(!markers[state.cssNumber])continue;
			markers[state.cssNumber].setPosition(new soso.maps.LatLng(parseFloat(state.cssLatitude)-0.0023,parseFloat(state.cssLongitude)+0.0044));
			if(state.cssHost==2)
				markers[state.cssNumber].setPosition(new soso.maps.LatLng(parseFloat(state.cssLatitude)+0.0014,parseFloat(state.cssLongitude)+0.0058));
			var bOrder = ($.trim(state.cssOrder)!="" && $.trim(state.cssOrder)!="0");
			var bOnline = (new Date().getTime()-new Date(state.cssAddTime).getTime())<(10*60*1000);
			var bPower = state.cssPower>115;
			markers[state.cssNumber].setIcon(getIcon(bOrder,bOnline,bPower));
			markers[state.cssNumber].setShadow(getShadow(bOrder,bOnline,bPower));
			markers[state.cssNumber].setTitle(state.cssNumber+(bOnline?"":" [掉线]")+(bPower?"":" [电量不足]"));	
			parent.update(state.cssNumber,bOrder,bOnline,bPower);
		}
		if(callback)
			callback();
	});
}

</script>


<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>


<script>
var markers=[];

$(function(){
	
	setTimeout(init,1000);
	
	var input = mapControl.find("input");
	input.keyup(function(){		
		parent.search();	
		var strIn=$(this).val().toUpperCase();
		if($.trim(strIn)=="")
			return;
		parent.search(strIn);		
	});
});

function init(){	
	//创建车辆标记
	for(var o in parent.cars){
		var car=parent.cars[o];
		var marker = markers[car.cscId]=markers[car.cscNumber]=new soso.maps.Marker({
		    position:new soso.maps.LatLng(parent.getLoction()[0],parent.getLoction()[1]),//定位到杭州
		    map:map,
		    cursor:"pointer",
		    icon:getIcon(false,false,false),
		    shadow:getShadow(false,false,false),
		    title:car.cscNumber
		});
		marker.info={id:car.cscId,number:car.cscNumber};
		soso.maps.event.addListener(marker,"click",onMarker)
	}
	update(function(){
		if("${number}"!="")
			goMarker("${number}");	
	});
	setInterval(update,30*1000);//定时器	
}
</script>


</html>