<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<s:bean id="lz" name="com.lazy3q.web.helper.$" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />
<title>网点地图监控</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta name="author" content="飞啊飘啊">
<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
<script charset="utf-8"
	src="http://map.qq.com/api/js?v=2.exp&key=MXSBZ-6GG3I-SKMGK-5HFGF-B3VIK-IVFCJ&libraries=convertor"></script>
<style type="text/css">
html,body {
	width: 100%;
	height: 100%;
}

* {
	margin: 0px;
	padding: 0px;
}

#allmap {
	min-width: 100%;
	min-height: 100%;
	position: absolute;
}

#layer {
	position: absolute;
	right: 10px;
	top: 10px;
	z-index: 10;
	background: #fff;
	margin-right: 12%;
	width: 1000px;
}

#car_choose {
	display: none;
	position: absolute;
	right: 10px;
	top: 10px;
	z-index: 10;
	background: #fff;
	margin-right: 46%;
	margin-top: 25px;
	position: absolute;
}

#car_choose ul {
	margin-left: 40px;
	width: 220px;
}

#car_choose ul li {
	width: 100px;
	float: left;
	display: block;
}

#city_choose {
	width: 144px;
	position: absolute;
	right: 10px;
	top: 10px;
	z-index: 10;
	background: #fff;
	margin-top: 25px;
	position: absolute;
	margin-right: 58%;
}

#city_choose ul {
	text-align:center;
	width: 100%;
}

#city_choose ul li {
	width: 100%;
	float: left;
	display: block;
}
</style>
</head>
<body onload="window.init()">

	<div id="layer">
		<div>
			<table width="100%" border="1" cellspacing="0" cellpadding="0">
				<tr>
					<td width="16" align="center" id="choose_city">选择城市</td>
					<td width="16" align="center" id="choose_car">车辆选择</td>
					<td width="16" align="center" id="actual">实时监控</td>
					<td width="16" align="center">远程控制</td>
					<td width="16" align="center">电子围栏</td>
					<td width="16" align="center">统计报表</td>
					<td width="16" align="center">热力图</td>
				</tr>
			</table>
		</div>
	</div>
	<div id="car_choose">
		<ul>
			<li><input type="checkbox" value="1" name="online" />已上线</li>
			<li><input type="checkbox" value="0" name="unline" /> 已下线</li>
			<li><input type="checkbox" value="0" name="unline" />在线</li>
			<li><input type="checkbox" value="0" name="unline" />掉线</li>
			<li><input type="checkbox" value="0" name="unline" />储蓄电量</li>
			<li><input type="checkbox" value="0" name="unline" />非低蓄电量</li>
			<li><input type="checkbox" value="0" name="unline" />有订单</li>
			<li><input type="checkbox" value="0" name="unline" />没订单</li>
			<li><input type="checkbox" value="0" name="unline" />有长租</li>
			<li><input type="checkbox" value="0" name="unline" />没长租</li>
			<li><input type="checkbox" value="0" name="unline" />低续航</li>
			<li><input type="checkbox" value="0" name="unline" />非低续航</li>
			<li><input type="checkbox" value="0" name="unline" />在充电的</li>
			<li><input type="checkbox" value="0" name="unline" />不在充电的</li>
			<li><input type="checkbox" value="0" name="unline" />在运营</li>
			<li><input type="checkbox" value="0" name="unline" />非运营</li>
			<li><input type="button" value="确定"
				onclick="closeDiv('car_choose')" /></li>
			<li><input type="button" value="取消"
				onclick="closeDiv('car_choose')" /></li>
		</ul>
	</div>

	<div id="city_choose">
		<ul>
			<s:iterator value="#request.hostList" var="host">
				<li><span><s:property value="#host.shName" /> </span>
				</li>
			</s:iterator>
		</ul>
	</div>

	<div id="allmap"></div>

</body>
<script type="text/javascript" src="${basePath}admin/js/qqmap.js"></script>
<script>
	$(function(){
		$("#choose_car").on( "click", function() {
			  $("#car_choose").show();
		});
	});
	
	function closeDiv(id){
		$("#car_choose").hide();
	}

	window.init = function() {
		
		
		var latLng  = new qq.maps.LatLng(${lz:or(defaultHost.shLocation,"30.276611,120.161665")});
		var Qmap = new EVnetMap("allmap", 14, MapType.QQ, latLng.getLat(), latLng.getLng(),
				true);
		<s:iterator value="#request.areaList" var="area">
			Qmap.createOverlay(Qmap,<s:property value="#area.cso_latitude" />,<s:property value="#area.cso_longitude" />,<s:property value="#area.cso_area" />,"<s:property value="#area.csa_name" /><s:property value="#area.eval" />个");
		</s:iterator>
	};
</script>





</html>
