<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<script charset="utf-8" src="http://map.soso.com/api/v2/main.js"></script>
<script>
$.extend({
	getMapPoint:function(callback,params){
		return $.getSoSoMapPoint(callback,params,[${lz:or(get:SrvHost(get:host()).shLocation,"30.276611,120.161665")}]);
	},
	getSoSoMapPoint:function(callback,params,defaultCenter){
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
	 	var map = new soso.maps.Map(dialog.find('#container').get(0),{
	        center: new soso.maps.LatLng(defaultCenter[0],defaultCenter[1]),//定位到杭州
	        zoom: 13
	    });
	    var marker = null;
	    if(params && $.trim(params.Longitude)!="" && $.trim(params.Latitude)!=""){
	    	pointer = new soso.maps.LatLng(params.Latitude,params.Longitude);
	    	marker = new soso.maps.Marker({
			    position:pointer,
			    map:map
			});	
			map.setCenter(pointer);
	    }
		soso.maps.event.addListener(
			map,'click',
			function(event) {
				pointer = new soso.maps.LatLng(event.latLng.getLat(), event.latLng.getLng());
				if(marker)
					marker.setPosition(pointer);
				else
					marker = new soso.maps.Marker({
					    position:pointer,
					    map:map
					});					
			}
		);
		var geocoder = new soso.maps.Geocoder({
	        complete : function(result){
	            map.setCenter(result.detail.location);
	            pointer = result.detail.location;
				if(marker)
					marker.setPosition(pointer);
				else
					marker = new soso.maps.Marker({
					    position:pointer,
					    map:map
					});
	        }
	    });
		//地图自定义控件
		var mapControl=$("<div id='mapControl' class='state-header' style='margin:5px;'><div style='height:100%; line-height:32px; float:left; padding:0px 10px;font-weight:bold;'>地址搜索:</div><input style='margin:2px;width:100px;float:left;' class='input' type='text' id=''/><button type='button' style='background:url(/admin/images/icons/search.png) center no-repeat; border:0px;width:28px;height:28px;margin:5px 0px;cursor:pointer;'></button></div>");
		new soso.maps.Control({
		    content:mapControl.get(0),
		    align: soso.maps.ALIGN.BOTTOM_RIGHT,
		    map: map
		});
		var button = mapControl.find("button");
		var input = mapControl.find("input");
		button.click(function(){
			var strIn=input.val();
			if($.trim(strIn)=="")
				return;
    		geocoder.getLocation(strIn);	
		});
	}
});
</script>