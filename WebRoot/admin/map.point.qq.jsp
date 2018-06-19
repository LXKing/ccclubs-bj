<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!-- <script charset="utf-8" src="http://map.soso.com/api/v2/main.js"></script>-->
<script charset="utf-8" src="http://webapi.amap.com/maps?v=1.3&key=0a7423560970ad2922ad3d7506d2d6b2&plugin=AMap.Geocoder"></script>
<script>
$.extend({ 
	getMapPoint:function(callback,params){
		return $.getAMapPoint(callback,params,[${lz:or(get:SrvHost(get:host()).shLocation,"30.276611,120.161665")}]);
	},
	getAMapPoint:function(callback,params,defaultCenter){
		if(!defaultCenter)
			defaultCenter = [30.276611,120.161665];
		var pointer = null;
		var dialog = $("<div style='width:500px;height:300px;' id='container'></div>").dialog({
			title:"坐标拾取",
			static:true,
			modal:true,
			onOk:function(){
				if(!pointer){
					alert("请在地图上单击以创建坐标");
					return false;
				}
				callback(pointer.getLat(),pointer.getLng());
			}
		});
	 	var map = new AMap.Map(dialog.find('#container').get(0),{
	        center:[defaultCenter[1],defaultCenter[0]],//定位到杭州
	        zoom: 13
	    });
	    var marker = null;
	    if(params && $.trim(params.Longitude)!="" && $.trim(params.Latitude)!=""){
	    	pointer =new AMap.LngLat(params.Longitude,params.Latitude);
	    	marker = new AMap.Marker({
			    position:pointer,
			    map:map
			});	
			map.setCenter(pointer);
	    }
		AMap.event.addListener(
			map,'click',
			function(event) {
				pointer = event.lnglat;
				if(marker)
					marker.setPosition(event.lnglat);
				else
					marker = new AMap.Marker({
					    position:event.lnglat,
					    map:map
					});					
			}
		);
		var geocoder = new AMap.Geocoder();
		//地图自定义控件
		var mapControl=$("<div id='mapControl' class='state-header' style='margin:5px;'><div style='height:100%; line-height:32px; float:left; padding:0px 10px;font-weight:bold;'>地址搜索:</div><input style='margin:2px;width:250px;float:left;' class='input' type='text' id=''/><button type='button' style='background:url(/admin/images/icons/search.png) center no-repeat; border:0px;width:28px;height:28px;margin:5px 0px;cursor:pointer;'></button></div>");
		dialog.find('#container').after(mapControl);
		var button = mapControl.find("button");
		var input = mapControl.find("input");
		button.click(function(){
			var strIn=input.val();
			if($.trim(strIn)=="")
				return;
    		geocoder.getLocation(strIn, function(status, result) {
	         if (status === 'complete' && result.info === 'OK') {
		        //地理编码结果数组
		        var geocode = result.geocodes;
		        for (var i = 0; i < geocode.length; i++) {
		        	map.setCenter(geocode[i].location);
		            pointer = geocode[i].location;
					if(marker)
						marker.setPosition(pointer);
					else
					marker = new AMap.Marker({
					    position:pointer,
					    map:map
					});
		        	break;
			     }
		     }	
		});
	});
  }
});
</script>