<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>运营分析</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<link href="${basePath}admin/css/stats.css?mm=${lz:uuid()}" rel="stylesheet" type="text/css" /> 
	
	<script type="text/javascript" src="${basePath}admin/js/FusionCharts/JSClass/FusionCharts.js"></script>
	
</head>  
<body scroll="no">
<script>
$(function(){

	showTips("${tips.value}");

	$(".item").click(function(){
		$("#type").val($(this).attr("ref"));
		$("#statsForm").submit();
	});
	
	$("input[name='view']").click(function(){
		window.location="?${lz:querys("UTF-8","view")}&view="+$(this).val();
	});
	
	$("#yearmonth select").change(function(){
		$("#statsForm").submit();
	});
	
});
</script>

<div id="navigator" >
	
	<form name="statsForm" id="statsForm" action="order.do" method="post">
	
		<table width="100%" id="yearmonth" border="0" border="0" cellpadding="5" cellspacing="0">
			<tr>
				<td colspan="3">
					<select style="width:100%" id="host" name="host">
						<option ${empty host?"selected":""} value="">全部城市</option>
						${lz:set("hosts",get:hosts())}
						<s:iterator value="#request.hosts" id="item" status="i">
							<option ${host==item.shId?"selected":""} value="${item.shId}">${item.shName}</option>
						</s:iterator>		 				
		 			</select>
				</td>
			</tr>
			<tr>
				<td width="33%">
					<select name="year" id="year" style="width:100%;">
						<option value="2012" ${(year==2012)?"selected":""}>2012年</option>
						<option value="2013" ${(year==2013)?"selected":""} >2013年</option>
						<option value="2014" ${(year==2014||year==null)?"selected":""}>2014年</option>
						<option value="2015" ${(year==2015||year==null)?"selected":""}>2015年</option>
					</select>
				</td>
				<td width="33%">
					<select name="month" id="month" style="width:100%;">
					<s:bean name="org.apache.struts2.util.Counter" id="counter">
						 <s:param name="first" value="0"/>
						 <s:param name="last" value="11" />
							<s:iterator>
								<option value="${counter.current}" ${(counter.current)==month?"selected":""}>${counter.current}月</option>
							</s:iterator>
					</s:bean>						
					</select>
				</td>
				<td width="34%">
					<select name="week" id="week" style="width:100%;">
						<option value="">全月</option>
						<option value="1" ${(week==1)?"selected":""} >第一周</option>
						<option value="2" ${(week==2)?"selected":""} >第二周</option>
						<option value="3" ${(week==3)?"selected":""} >第三周</option>
						<option value="4" ${(week==4)?"selected":""} >第四周</option>
						<option value="5" ${(week==5)?"selected":""} >第五周</option>	
						<option value="6" ${(week==6)?"selected":""} >第六周</option>										
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="checkbox" name="all" ${all==true?"checked='checked'":""} value="true" />
					计算全部状态订单
				</td>
			</tr>
		</table>
		
		<input name="type" id="type" type="hidden" value="${type}" />
		<input name="view" id="view" type="hidden" value="${view}" />
	
		<button type="button" class="item ${type=="ordermoney"?"focus":""}" ref="ordermoney">		
			<img align="absmiddle" src="${basePath}admin/images/icons/20088256422923477806.png"/>
			订单金额增长
		</button>
	
		<button type="button" class="item ${type=="ordertime"?"focus":""}" ref="ordertime">		
			<img align="absmiddle" src="${basePath}admin/images/icons/20088256422923477803.png"/>
			订单时间增长
		</button>
	
		<button type="button" class="item ${type=="ordercount"?"focus":""}" ref="ordercount">		
			<img align="absmiddle" src="${basePath}admin/images/icons/20088256422923477809.png"/>
			订单数量增长
		</button>	
		
		<!--
		<button type="button" class="item ${type=="average"?"focus":""}" ref="average">
			<img align="absmiddle" src="${basePath}admin/images/icons/200882564216312778012.png"/>
			平均消费增长	
		</button>
		-->
		
		<button type="button" class="item ${type=="compose"?"focus":""}" ref="compose">
			<img align="absmiddle" src="${basePath}admin/images/icons/Statistics PieChart.png"/>
			收入构成分析		
		</button>		
		
		<button type="button" class="item ${type=="area"?"focus":""}" ref="area">
			<img align="absmiddle" src="${basePath}admin/images/icons/Map.png"/>
			订单区域分析	
		</button>
		
		<button type="button" class="item ${type=="outlets"?"focus":""}" ref="outlets">
			<img align="absmiddle" src="${basePath}admin/images/icons/anchor.png"/>
			订单网点分析		
		</button>
		
		<button type="button" class="item ${type=="car"?"focus":""}" ref="car">
			<img align="absmiddle" src="${basePath}admin/images/icons/200882564229234778010.png"/>
			订单车辆分析	
		</button>
		
		<button type="button" class="item ${type=="model"?"focus":""}" ref="model">
			<img align="absmiddle" src="${basePath}admin/images/icons/2008825642250778029.png"/>
			车型订单分析	
		</button>			
		
		<button type="button" class="item ${type=="from"?"focus":""}" ref="from">
			<img align="absmiddle" src="${basePath}admin/images/icons/20088256422978177802.png"/>
			订单来源分析		
		</button>		
		
	</form>
	
</div>


<table id="separator-panel" width="8" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr height="*">
		<td width="8" class="separator" align="center" valign="middle">&nbsp;</td>
	</tr>
</table>

<div id="statscontent">
	
	
	
	<s:if test="#request.type=='ordermoney'">
		<center class="plate">			
			<h2>${sectioner}订单金额增长</h2>
		</center>
		<center>
			<input type="radio" name="view" ${(empty view || view=="column")?"checked='checked'":""} value="column" />
			同比环比柱状图
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="view" ${view=="line"?"checked='checked'":""} value="line" />
			${sectioner.type=="WEEK"?"12周":"12个月"}增长曲线图
		</center>
		<div id="chartdiv"></div>
		<s:if test="#request.view=='column'">
		<script>
		<lz:set name="xml">
		<chart caption="订单金额增长" xAxisName="时间段" yAxisName="订单金额(元)" showValues="0" decimals="0" formatNumberScale="0" bgColor="f2f2f2">
			<set label="${sectioner.last}" value="${last[0]["money"]}" />
			<set label="${sectioner.prev}" value="${prev[0]["money"]}" />
			<set label="${sectioner}" value="${list[0]["money"]}" />
		</chart>
		</lz:set>
		$(function(){
			var chart = new FusionCharts("${basePath}/admin/js/FusionCharts/Charts/Column3D.swf","chartdiv", $("#chartdiv").width(), "400", "0", "0");
		   	chart.setTransparent(true);
		   	chart.setDataXML("${lz:js(xml)}");		   
		   	chart.render("chartdiv");
		});
		</script>
		<center>
			<s:if test="#request.last[0]['money']>0">
			<button type="button" class="display">
				同比增长：${lz:digit((list[0]["money"]-last[0]["money"])/last[0]["money"]*100,2)}%
			</button>
			</s:if>
			<s:if test="#request.prev[0]['money']>0">
			<button type="button" class="display">
				环比增长:${lz:digit((list[0]["money"]-prev[0]["money"])/prev[0]["money"]*100,2)}%
			</button>
			</s:if>
		</center>
		</s:if>
		<s:if test="#request.view=='line'">
		<script>
		<lz:set name="xml">
		<chart caption="订单金额增长" lineThickness="1" canvasBorderThickness="2" canvasBorderColor="#000000" showValues="0" formatNumberScale="0" anchorRadius="2" divLineAlpha="80" divLineColor="efefef" borderColor="efefef" divLineIsDashed="1" showAlternateHGridColor="1" alternateHGridAlpha="5" alternateHGridColor="CC3300" shadowAlpha="40" labelStep="2" numvdivlines="5" chartRightMargin="35" bgColor="f2f2f2" bgAngle="270" bgAlpha="100,100">
			<categories>
				<s:iterator  value="#request.list" id="item" status="i">
					<category label="${item.sectioner}"/>
				</s:iterator>
			</categories>
			<dataset seriesName="订单金额" color="1D8BD1" anchorBorderColor="1D8BD1" anchorBgColor="1D8BD1">
				<s:iterator  value="#request.list" id="item" status="i">
					<set name="${item.sectioner}" value="${item.money}"/>
				</s:iterator>
			</dataset>
		</chart>		
		</lz:set>	
		$(function(){
			var chart = new FusionCharts("${basePath}/admin/js/FusionCharts/Charts/MSLine.swf","chartdiv", $("#chartdiv").width()-40, "400", "0", "0");
		   	chart.setTransparent(true);
		   	chart.setDataXML("${lz:js(xml)}");
		   	chart.render("chartdiv");
		});
		</script>
		</s:if>
	</s:if>
	<s:elseif test="#request.type=='ordertime'">
		<center class="plate">			
			<h2>${sectioner}订单时间增长</h2>
		</center>
		<center>
			<input type="radio" name="view" ${(empty view || view=="column")?"checked='checked'":""} value="column" />
			同比环比柱状图
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="view" ${view=="line"?"checked='checked'":""} value="line" />
			${sectioner.type=="WEEK"?"12周":"12个月"}增长曲线图
		</center>
		<div id="chartdiv"></div>
		<s:if test="#request.view=='column'">
		<script>
		<lz:set name="xml">
		<chart caption="订单时间增长" xAxisName="时间段" yAxisName="订单时间(小时)" showValues="0" decimals="0" formatNumberScale="0" bgColor="f2f2f2">
			<set label="${sectioner.last}" value="${last[0]["time"]/3600}" />
			<set label="${sectioner.prev}" value="${prev[0]["time"]/3600}" />
			<set label="${sectioner}" value="${list[0]["time"]/3600}" />
		</chart>
		</lz:set>
		$(function(){
			var chart = new FusionCharts("${basePath}/admin/js/FusionCharts/Charts/Column3D.swf","chartdiv", $("#chartdiv").width(), "400", "0", "0");
		   	chart.setTransparent(true);
		   	chart.setDataXML("${lz:js(xml)}");		   
		   	chart.render("chartdiv");
		});
		</script>
		<center>
			<s:if test="#request.last[0]['time']>0">
			<button type="button" class="display">
				同比增长：${((list[0]["time"]-last[0]["time"])/last[0]["time"]*100)}%
			</button>
			</s:if>
			<s:if test="#request.prev[0]['time']>0">
			<button type="button" class="display">
				环比增长:${((list[0]["time"]-prev[0]["time"])/prev[0]["time"]*100)}%
			</button>
			</s:if>
		</center>
		</s:if>
		<s:if test="#request.view=='line'">
		<script>
		<lz:set name="xml">
		<chart caption="订单时间增长" lineThickness="1" canvasBorderThickness="2" canvasBorderColor="#000000" showValues="0" formatNumberScale="0" anchorRadius="2" divLineAlpha="80" divLineColor="efefef" borderColor="efefef" divLineIsDashed="1" showAlternateHGridColor="1" alternateHGridAlpha="5" alternateHGridColor="CC3300" shadowAlpha="40" labelStep="2" numvdivlines="5" chartRightMargin="35" bgColor="f2f2f2" bgAngle="270" bgAlpha="100,100">
			<categories>
				<s:iterator  value="#request.list" id="item" status="i">
					<category label="${item.sectioner}"/>
				</s:iterator>
			</categories>
			<dataset seriesName="订单时间" color="1D8BD1" anchorBorderColor="1D8BD1" anchorBgColor="1D8BD1">
				<s:iterator  value="#request.list" id="item" status="i">
					<set name="${item.sectioner}" value="${item.time/3600}"/>
				</s:iterator>
			</dataset>
		</chart>		
		</lz:set>	
		$(function(){
			var chart = new FusionCharts("${basePath}/admin/js/FusionCharts/Charts/MSLine.swf","chartdiv", $("#chartdiv").width()-40, "400", "0", "0");
		   	chart.setTransparent(true);
		   	chart.setDataXML("${lz:js(xml)}");
		   	chart.render("chartdiv");
		});
		</script>
		</s:if>
	</s:elseif>
	<s:elseif test="#request.type=='ordercount'">
		<center class="plate">			
			<h2>${sectioner}订单数增长</h2>
		</center>
		<center>
			<input type="radio" name="view" ${(empty view || view=="column")?"checked='checked'":""} value="column" />
			同比环比柱状图
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="view" ${view=="line"?"checked='checked'":""} value="line" />
			${sectioner.type=="WEEK"?"12周":"12个月"}增长曲线图
		</center>
		<div id="chartdiv"></div>
		<s:if test="#request.view=='column'">
		<script>
		<lz:set name="xml">
		<chart caption="订单数增长" xAxisName="时间段" yAxisName="订单数" showValues="0" decimals="0" formatNumberScale="0" bgColor="f2f2f2">
			<set label="${sectioner.last}" value="${last[0]["count"]}" />
			<set label="${sectioner.prev}" value="${prev[0]["count"]}" />
			<set label="${sectioner}" value="${list[0]["count"]}" />
		</chart>
		</lz:set>
		$(function(){
			var chart = new FusionCharts("${basePath}/admin/js/FusionCharts/Charts/Column3D.swf","chartdiv", $("#chartdiv").width(), "400", "0", "0");
		   	chart.setTransparent(true);
		   	chart.setDataXML("${lz:js(xml)}");		   
		   	chart.render("chartdiv");
		});
		</script>
		<center>
			<s:if test="#request.last[0]['count']>0">
			<button type="button" class="display">
				同比增长：${lz:digit((list[0]["count"]-last[0]["count"])/last[0]["count"]*100,2)}%
			</button>
			</s:if>
			<s:if test="#request.prev[0]['count']>0">
			<button type="button" class="display">
				环比增长:${lz:digit((list[0]["count"]-prev[0]["count"])/prev[0]["count"]*100,2)}%
			</button>
			</s:if>
		</center>
		</s:if>
		<s:if test="#request.view=='line'">
		<script>
		<lz:set name="xml">
		<chart caption="订单数增长" lineThickness="1" canvasBorderThickness="2" canvasBorderColor="#000000" showValues="0" formatNumberScale="0" anchorRadius="2" divLineAlpha="80" divLineColor="efefef" borderColor="efefef" divLineIsDashed="1" showAlternateHGridColor="1" alternateHGridAlpha="5" alternateHGridColor="CC3300" shadowAlpha="40" labelStep="2" numvdivlines="5" chartRightMargin="35" bgColor="f2f2f2" bgAngle="270" bgAlpha="100,100">
			<categories>
				<s:iterator  value="#request.list" id="item" status="i">
					<category label="${item.sectioner}"/>
				</s:iterator>
			</categories>
			<dataset seriesName="订单数" color="1D8BD1" anchorBorderColor="1D8BD1" anchorBgColor="1D8BD1">
				<s:iterator  value="#request.list" id="item" status="i">
					<set name="${item.sectioner}" value="${item.count}"/>
				</s:iterator>
			</dataset>
		</chart>		
		</lz:set>	
		$(function(){
			var chart = new FusionCharts("${basePath}/admin/js/FusionCharts/Charts/MSLine.swf","chartdiv", $("#chartdiv").width()-40, "400", "0", "0");
		   	chart.setTransparent(true);
		   	chart.setDataXML("${lz:js(xml)}");
		   	chart.render("chartdiv");
		});
		</script>
		</s:if>
	</s:elseif>
	<s:elseif test="#request.type=='average'">
		<center class="plate">			
			<h2>${sectioner}平均消费增长</h2>
		</center>
		<center>
			<input type="radio" name="view" ${(empty view || view=="column")?"checked='checked'":""} value="column" />
			同比环比柱状图
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="view" ${view=="line"?"checked='checked'":""} value="line" />
			${sectioner.type=="WEEK"?"12周":"12个月"}增长曲线图
		</center>
		<div id="chartdiv"></div>
		<s:if test="#request.view=='column'">
		<script>
		<lz:set name="xml">
		<chart caption="平均消费增长" xAxisName="时间段" yAxisName="平均消费额" showValues="0" decimals="0" formatNumberScale="0" bgColor="f2f2f2">
			<set label="${sectioner.last}" value="${last[0]["value"]}" />
			<set label="${sectioner.prev}" value="${prev[0]["value"]}" />
			<set label="${sectioner}" value="${list[0]["value"]}" />
		</chart>
		</lz:set>
		$(function(){
			var chart = new FusionCharts("${basePath}/admin/js/FusionCharts/Charts/Column3D.swf","chartdiv", $("#chartdiv").width(), "400", "0", "0");
		   	chart.setTransparent(true);
		   	chart.setDataXML("${lz:js(xml)}");		   
		   	chart.render("chartdiv");
		});
		</script>
		<center>
			<s:if test="#request.last[0]['value']>0">
			<button type="button" class="display">
				同比增长：${lz:digit((list[0]["value"]-last[0]["value"])/last[0]["value"]*100,2)}%
			</button>
			</s:if>
			<s:if test="#request.prev[0]['value']>0">
			<button type="button" class="display">
				环比增长:${lz:digit((list[0]["value"]-prev[0]["value"])/prev[0]["value"]*100,2)}%
			</button>
			</s:if>
		</center>
		</s:if>
		<s:if test="#request.view=='line'">
		<script>
		<lz:set name="xml">
		<chart caption="平均消费增长" lineThickness="1" canvasBorderThickness="2" canvasBorderColor="#000000" showValues="0" formatNumberScale="0" anchorRadius="2" divLineAlpha="80" divLineColor="efefef" borderColor="efefef" divLineIsDashed="1" showAlternateHGridColor="1" alternateHGridAlpha="5" alternateHGridColor="CC3300" shadowAlpha="40" labelStep="2" numvdivlines="5" chartRightMargin="35" bgColor="f2f2f2" bgAngle="270" bgAlpha="100,100">
			<categories>
				<s:iterator  value="#request.list" id="item" status="i">
					<category label="${item.sectioner}"/>
				</s:iterator>
			</categories>
			<dataset seriesName="平均消费额" color="1D8BD1" anchorBorderColor="1D8BD1" anchorBgColor="1D8BD1">
				<s:iterator  value="#request.list" id="item" status="i">
					<set name="${item.sectioner}" value="${item.value}"/>
				</s:iterator>
			</dataset>
		</chart>		
		</lz:set>	
		$(function(){
			var chart = new FusionCharts("${basePath}/admin/js/FusionCharts/Charts/MSLine.swf","chartdiv", $("#chartdiv").width()-40, "400", "0", "0");
		   	chart.setTransparent(true);
		   	chart.setDataXML("${lz:js(xml)}");
		   	chart.render("chartdiv");
		});
		</script>
		</s:if>
	</s:elseif>
	<s:elseif test="#request.type=='compose'">
		<center class="plate">			
			<h2>${sectioner}收入构成(${lz:digit(compose["pay"],2)}元)分析</h2>
		</center>
		<div id="chartdiv"></div>
		<script>
		<lz:set name="listxml">
			<chart palette="4" decimals="0" enableSmartLabels="1" enableRotation="0" bgColor="f2f2f2" bgAlpha="40,100" bgRatio="0,100" bgAngle="360" showBorder="0" startingAngle="70">
			  ${lz:set("counter",0)}
			  <s:iterator value="#request.fields" id="item">
			  	 <set label="${item}${lz:digit(compose[item],2)}元" value="${compose[item]}" />
			  	 ${lz:set("counter",counter+compose[item])}
			  </s:iterator>
			  <set label="其它${lz:digit(compose["pay"]-counter,2)}元" value="${compose["pay"]-counter}" />
			</chart>
		</lz:set>
		$(function(){
			var chart = new FusionCharts("${basePath}/admin/js/FusionCharts/Charts/Pie3D.swf","chartdiv", $("#chartdiv").width(), "400", "0", "0");
		   	chart.setTransparent(true);
		   	chart.setDataXML("${lz:js(listxml)}");		   
		   	chart.render("chartdiv");
		});
		</script>		
	</s:elseif>
	<s:else>
	
		
		${lz:set("count",0)}<s:iterator value="#request.list" id="item">${lz:set("count",count+item.count)}</s:iterator>
		${lz:set("money",0)}<s:iterator value="#request.list" id="item">${lz:set("money",money+item.money)}</s:iterator>
		${lz:set("time",0)}<s:iterator value="#request.list" id="item">${lz:set("time",time+item.time)}</s:iterator>
		<center class="plate">			
			<h2>${sectioner}${caption}分析</h2>
		</center>
		<center>
			<button onclick="window.location='?${lz:querys("UTF-8","kind")}&kind=count'" type="button" class="display ${(empty kind || kind=="count")?"":"disable"}">
				订单个数${count}个
			</button>
			<button onclick="window.location='?${lz:querys("UTF-8","kind")}&kind=money'" type="button" class="display ${(kind=="money")?"":"disable"}">
				订单金额${lz:digit(money,2)}元
			</button>
			<button onclick="window.location='?${lz:querys("UTF-8","kind")}&kind=time'" type="button" class="display ${(kind=="time")?"":"disable"}">
				订单时间${time/3600}小时
			</button>
		</center>
		
		
		<s:if test="#request.kind==null||#request.kind=='count'">
		<div id="chartcount"></div>
		<script>
		<lz:set name="listxml">
			<chart palette="4" decimals="0" enableSmartLabels="1" enableRotation="0" bgColor="f2f2f2" bgAlpha="40,100" bgRatio="0,100" bgAngle="360" showBorder="0" startingAngle="70">
			  <s:iterator value="#request.list" id="item">
			  	 ${lz:set("value",item.count)}
			  	 ${lz:set("unit","个")}
			  	 <%@include file="typename.jsp"%>
			  </s:iterator>
			</chart>
		</lz:set>
		$(function(){
			var chart = new FusionCharts("${basePath}/admin/js/FusionCharts/Charts/Pie3D.swf","chartcount", $("#chartcount").width(), "400", "0", "0");
		   	chart.setTransparent(true);
		   	chart.setDataXML("${lz:js(listxml)}");		   
		   	chart.render("chartcount");
		});
		</script>
		</s:if>
				
		
		<s:if test="#request.kind=='money'">
		<div id="chartmoney"></div>
		<script>
		<lz:set name="listxml">
			<chart palette="4" decimals="0" enableSmartLabels="1" enableRotation="0" bgColor="f2f2f2" bgAlpha="40,100" bgRatio="0,100" bgAngle="360" showBorder="0" startingAngle="70">
			  <s:iterator value="#request.list" id="item">
			  	 ${lz:set("value",item.money)}
			  	 ${lz:set("unit","元")}
			  	 <%@include file="typename.jsp"%>
			  </s:iterator>
			</chart>
		</lz:set>
		$(function(){
			var chart = new FusionCharts("${basePath}/admin/js/FusionCharts/Charts/Pie3D.swf","chartmoney", $("#chartmoney").width(), "400", "0", "0");
		   	chart.setTransparent(true);
		   	chart.setDataXML("${lz:js(listxml)}");		   
		   	chart.render("chartmoney");
		});
		</script>
		</s:if>
		
		
		<s:if test="#request.kind=='time'">
		<div id="charttime"></div>
		<script>
		<lz:set name="listxml">
			<chart palette="4" decimals="0" enableSmartLabels="1" enableRotation="0" bgColor="f2f2f2" bgAlpha="40,100" bgRatio="0,100" bgAngle="360" showBorder="0" startingAngle="70">
			  <s:iterator value="#request.list" id="item">
			  	 ${lz:set("value",item.time/3600)}
			  	 ${lz:set("unit","小时")}
			  	 <%@include file="typename.jsp"%>
			  </s:iterator>
			</chart>
		</lz:set>
		$(function(){
			var chart = new FusionCharts("${basePath}/admin/js/FusionCharts/Charts/Pie3D.swf","charttime", $("#charttime").width(), "400", "0", "0");
		   	chart.setTransparent(true);
		   	chart.setDataXML("${lz:js(listxml)}");		   
		   	chart.render("charttime");
		});
		</script>
		</s:if>
		
	
	</s:else>
	
		
</div>


</body>
</html>
