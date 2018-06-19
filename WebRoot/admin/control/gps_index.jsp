<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<s:bean id="lz" name="com.lazy3q.web.helper.$"/> 
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>网点地图监控</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	

	
</head>  

<script>
var outlets=[];
<s:iterator value="#request.outlets" id="outlet" status="i">
outlets[${i.count-1}]={csoLatitude:${outlet.csoLatitude},csoLongitude:${outlet.csoLongitude},csoName:"${outlet.csoName}"};
</s:iterator>

var cars=[];
<s:iterator value="#request.cars" id="car" status="i">
cars[${i.count-1}]={cscId:"${car.cscId}",cscNumber:"${car.cscNumber}"};
</s:iterator>

function href(url,params){
	top.$.showModalDialog({url:url},params,window);
}

function search(strIn){
	if(!strIn){
		$("#navigator tr[tree]").find(".link").css("color","").css("font-weight","");
	}else{
		var trs = $("#navigator tr:contains('"+strIn+"')");
		trs.find(".link").css("color","red").css("font-weight","bold");
		var top = $("#navigator").scrollTop()+$(trs.get(0)).offset().top-100;
		if(trs.size()>0)
			$("#navigator").scrollTop(top);
	}
}

function update(number,bOrder,bOnline,bPower){
	var tr = $("#"+number);
	tr.find(".a").css("background",bOrder?"url(/admin/images/state/u_online.gif)":"url(/admin/images/state/u_offline.gif)");
	tr.find(".b").css("background",bOnline?"url(/admin/images/state/signal_full.gif)":"url(/admin/images/state/signal_empty.gif)");
	tr.find(".c").css("background",bPower?"url(/admin/images/state/power_full.gif)":"url(/admin/images/state/power_empty.gif)");
}

function getLoction(){
	var strLoaction = "${get:SrvHost(csProvid.cspHost).shLocation}";
	if($.trim(strLoaction)=="")
		strLoaction="30.276611,120.161665";
	return strLoaction.split(",");
}

</script>

<body  scroll="no">
	<div id="navigator">
		<table id="aoc_table" class="table" border="0" cellspacing="1" cellpadding="0">
			<thead class="state-header">
			  	<tr>
			  		<td width="200" align="center"> 
			  			所有车辆 
			  		</td>
			  	</tr>
			</thead>
			<tbody>
				<s:iterator value="#request.cars" id="car">
					<tr class="carer" id="${car.cscNumber}" tree="o${car.cscOutlets}">
						<td class="node">
							<em class='ico a'></em>
							<em class='ico b'></em>
							<em class='ico c'></em>
							<a class='link' title="${car.$cscModel.cscmName}(${car.cscStatus==2?car.cscRemark:"在线运营"})" onclick="sosomap.goMarker('${car.cscNumber}')" href="javascript:void(0);" style="${car.cscStatus==2?"color:#ff0000;text-decoration:line-through;":""}">
								${car.cscCarNo}
							</a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
	<div id="mapframe">
		<iframe  id="sosomap" src="gps_soso.do?number=${number}" name="sosomap" frameborder="0" width="100%" height="100%"></iframe>
	</div>	
</body>


	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
			
	<style type="text/css">
	body,html{overflow:hidden;}
	#navigator{background:#ffffff;width:220px;float:left;height:100%;overflow:scroll;}
	#mapframe{margin-left:200px;height:100%; overflow:hidden;}	
	.table tbody td .ico{display:inline-block;width:14px;height:14px;background:center top no-repeat;margin:2px;}
	.table tbody td .a{background-image:url(/admin/images/state/u_offline.gif);width:14px;}
	.table tbody td .b{background-image:url(/admin/images/state/signal_empty.gif);}
	.table tbody td .c{background-image:url(/admin/images/state/power_empty.gif);}
	.table tbody td .d{background-image:url(/admin/images/state/history.gif);}
	.table tbody td .icon{cursor: pointer;display: inline-block;height: 22px;width: 22px;}
	</style>	
	
	<script>
	showTips("${tips.value}");
	</script>

</html>