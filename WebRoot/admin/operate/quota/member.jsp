<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>会员指标</title>
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
	
	<form name="statsForm" id="statsForm" action="member.do" method="post">
	
		<table width="100%" id="yearmonth" border="0" border="0" cellpadding="0" cellspacing="0">
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
		</table>
		
		<input name="type" id="type" type="hidden" value="${type}" />
		<input name="view" id="view" type="hidden" value="${view}" />
	
		<button type="button" class="item ${type=="newly"?"focus":""}" ref="newly">		
			<img align="absmiddle" src="${basePath}admin/images/icons/Arrow up.png"/>
			新增会员分析		
		</button>
		
		<button type="button" class="item ${type=="charge"?"focus":""}" ref="charge">
			<img align="absmiddle" src="${basePath}admin/images/icons/20088256422923477806.png"/>
			新增充值分析
		</button>
		
		<button type="button" class="item ${type=="change"?"focus":""}" ref="change">
			<img align="absmiddle" src="${basePath}admin/images/icons/2008825642250778028.png"/>
			会员转化分析		
		</button>
		
		<button type="button" class="item ${type=="refund"?"focus":""}" ref="refund">
			<img align="absmiddle" src="${basePath}admin/images/icons/Wallet2.png"/>
			退款会员分析		
		</button>
		
		<button type="button" class="item ${type=="from"?"focus":""}" ref="from">
			<img align="absmiddle" src="${basePath}admin/images/icons/User.png"/>
			注册来源分析		
		</button>
		
		<button type="button" class="item ${type=="outlets"?"focus":""}" ref="outlets">
			<img align="absmiddle" src="${basePath}admin/images/icons/anchor.png"/>
			注册网点分析		
		</button>
		
	</form>
	
</div>


<table id="separator-panel" width="8" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr height="*">
		<td width="8" class="separator" align="center" valign="middle">&nbsp;</td>
	</tr>
</table>

<div id="statscontent">
	
	<s:if test="#request.type=='newly'">
		<!-- 新增会员数 -->
		<center class="plate">
			<h2>${sectioner}新增会员数分析</h2>
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
		<chart caption="会员新增指标" xAxisName="时间段" yAxisName="新增数" showValues="0" decimals="0" formatNumberScale="0" bgColor="f2f2f2">
			<set label="${sectioner.last}" value="${last[0]["count"]}" />
			<set label="${sectioner.prev}" value="${prev[0]["count"]}" />
			<set label="${sectioner}" value="${list[0]["count"]}" />
		</chart>
		</lz:set>
		$(function(){
			var chart = new FusionCharts("${basePath}/admin/js/FusionCharts/Charts/Column3D.swf?mm="+Math.random(),"newly", $("#chartdiv").width(), "400", "0", "0");
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
		<chart caption="注册会员增长" lineThickness="1" canvasBorderThickness="2" canvasBorderColor="#000000" showValues="0" formatNumberScale="0" anchorRadius="2" divLineAlpha="80" divLineColor="efefef" borderColor="efefef" divLineIsDashed="1" showAlternateHGridColor="1" alternateHGridAlpha="5" alternateHGridColor="CC3300" shadowAlpha="40" labelStep="2" numvdivlines="5" chartRightMargin="35" bgColor="f2f2f2" bgAngle="270" bgAlpha="100,100">
			<categories>
				<s:iterator  value="#request.list" id="item" status="i">
					<category label="${item.sectioner}"/>
				</s:iterator>
			</categories>
			<dataset seriesName="注册会员" color="1D8BD1" anchorBorderColor="1D8BD1" anchorBgColor="1D8BD1">
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
	</s:if>
	
	
	
	
	<s:if test="#request.type=='charge'">	
		<!-- 新增充值数 -->
		<center class="plate">
			<h2>${sectioner}新增充值数分析</h2>
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
		<chart caption="充值新增指标" xAxisName="时间段" yAxisName="新增数" showValues="0" decimals="0" formatNumberScale="0" bgColor="f2f2f2">
			<set label="${sectioner.last}" value="${last[0]["count"]}" />
			<set label="${sectioner.prev}" value="${prev[0]["count"]}" />
			<set label="${sectioner}" value="${list[0]["count"]}" />
		</chart>
		</lz:set>
		$(function(){
			var chart = new FusionCharts("${basePath}/admin/js/FusionCharts/Charts/Column3D.swf","charge", $("#chartdiv").width(), "400", "0", "0");
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
		<chart caption="新增会员充值" lineThickness="1" canvasBorderThickness="2" canvasBorderColor="#000000" showValues="0" formatNumberScale="0" anchorRadius="2" divLineAlpha="80" divLineColor="efefef" borderColor="efefef" divLineIsDashed="1" showAlternateHGridColor="1" alternateHGridAlpha="5" alternateHGridColor="CC3300" shadowAlpha="40" labelStep="2" numvdivlines="5" chartRightMargin="35" bgColor="f2f2f2" bgAngle="270" bgAlpha="100,100">
			<categories>
				<s:iterator  value="#request.list" id="item" status="i">
					<category label="${item.sectioner}"/>
				</s:iterator>
			</categories>
			<dataset seriesName="新增充值数" color="1D8BD1" anchorBorderColor="1D8BD1" anchorBgColor="1D8BD1">
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
	</s:if>
	
	
	
	
	
	<s:if test="#request.type=='refund'">	
		<!-- 会员退款数 -->
		<center class="plate">
			<h2>${sectioner}会员退款分析</h2>
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
		<chart caption="退款新增指标" xAxisName="时间段" yAxisName="新增数" showValues="0" decimals="0" formatNumberScale="0" bgColor="f2f2f2">
			<set label="${sectioner.last}" value="${last[0]["count"]}" />
			<set label="${sectioner.prev}" value="${prev[0]["count"]}" />
			<set label="${sectioner}" value="${list[0]["count"]}" />
		</chart>
		</lz:set>
		$(function(){
			var chart = new FusionCharts("${basePath}/admin/js/FusionCharts/Charts/Column3D.swf","charge", $("#chartdiv").width(), "400", "0", "0");
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
		<chart caption="会员新增退款" lineThickness="1" canvasBorderThickness="2" canvasBorderColor="#000000" showValues="0" formatNumberScale="0" anchorRadius="2" divLineAlpha="80" divLineColor="efefef" borderColor="efefef" divLineIsDashed="1" showAlternateHGridColor="1" alternateHGridAlpha="5" alternateHGridColor="CC3300" shadowAlpha="40" labelStep="2" numvdivlines="5" chartRightMargin="35" bgColor="f2f2f2" bgAngle="270" bgAlpha="100,100">
			<categories>
				<s:iterator  value="#request.list" id="item" status="i">
					<category label="${item.sectioner}"/>
				</s:iterator>
			</categories>
			<dataset seriesName="会员退款数" color="1D8BD1" anchorBorderColor="1D8BD1" anchorBgColor="1D8BD1">
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
	</s:if>
	
	
	
	
	
	<s:if test="#request.type=='change'">	
		<!-- 会员转化率 -->
		<center class="plate">
			<h2>${sectioner}会员转化率分析</h2>
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
		<chart caption="会员转化率分析" xAxisName="时间段" yAxisName="转化率" showValues="0" decimals="0" formatNumberScale="0" bgColor="f2f2f2">
			<set label="${sectioner.last}" value="${charge.last[0]["count"]}" />
			<set label="${sectioner.prev}" value="${charge.prev[0]["count"]}" />
			<set label="${sectioner}" value="${charge.list[0]["count"]}" />
		</chart>
		</lz:set>
		$(function(){
			var chart = new FusionCharts("${basePath}/admin/js/FusionCharts/Charts/Column3D.swf","charge", $("#chartdiv").width(), "400", "0", "0");
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
		<chart caption="会员转化率" lineThickness="1" canvasBorderThickness="2" canvasBorderColor="#000000" showValues="0" formatNumberScale="0" anchorRadius="2" divLineAlpha="80" divLineColor="efefef" borderColor="efefef" divLineIsDashed="1" showAlternateHGridColor="1" alternateHGridAlpha="5" alternateHGridColor="CC3300" shadowAlpha="40" labelStep="2" numvdivlines="5" chartRightMargin="35" bgColor="f2f2f2" bgAngle="270" bgAlpha="100,100">
			<categories>
				<s:iterator  value="#request.list" id="item" status="i">
					<category label="${item.sectioner}"/>
				</s:iterator>
			</categories>
			<dataset seriesName="转化率" color="1D8BD1" anchorBorderColor="1D8BD1" anchorBgColor="1D8BD1">
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
	</s:if>
		
	<s:if test="#request.type=='from'">
		<!-- 注册来源分析 -->
		<center class="plate">
			<h2>${sectioner}注册来源分析</h2>
		</center>
		<div id="chartdiv"></div>
		<script>
		<lz:set name="xml">
			<chart palette="4" decimals="0" enableSmartLabels="1" enableRotation="0" bgColor="f2f2f2" bgAlpha="40,100" bgRatio="0,100" bgAngle="360" showBorder="0" startingAngle="70">
			  <s:iterator value="#request.list" id="item">
			 	 <set label="${item.csm_from==0?"后台注册":""}
				${item.csm_from==1?"网站注册":""}
				${item.csm_from==2?"微信注册":""}
				${item.csm_from==3?"APP注册":""}
				${item.csm_from==4?"支付宝微生活":""}
				${empty item.csm_from?"未知来源":""}" value="${item.count}" /> 
			  </s:iterator>
			</chart>
		</lz:set>
		$(function(){
			var chart = new FusionCharts("${basePath}/admin/js/FusionCharts/Charts/Pie3D.swf","from", $("#chartdiv").width(), "400", "0", "0");
		   	chart.setTransparent(true);
		   	chart.setDataXML("${lz:js(xml)}");		   
		   	chart.render("chartdiv");
		});
		</script>
	</s:if>
	
	
	
	<s:if test="#request.type=='outlets'">
		<!-- 注册网点分析 -->
		<center class="plate">
			<h2>${sectioner}注册网点分析</h2>
		</center>
		<div id="chartdiv"></div>
		<script>
		<lz:set name="xml">
			<chart palette="4" decimals="0" enableSmartLabels="1" enableRotation="0" bgColor="f2f2f2" bgAlpha="40,100" bgRatio="0,100" bgAngle="360" showBorder="0" startingAngle="70">
			  <s:iterator value="#request.list" id="item">
			 	 <set label="${(get:CsOutlets(item.csmOutlets))==null?"未知网点":(get:CsOutlets(item.csmOutlets).csoName)}" value="${item.count}" /> 
			  </s:iterator>
			</chart>
		</lz:set>
		$(function(){
			var chart = new FusionCharts("${basePath}/admin/js/FusionCharts/Charts/Pie3D.swf","from", $("#chartdiv").width(), "400", "0", "0");
		   	chart.setTransparent(true);
		   	chart.setDataXML("${lz:js(xml)}");		   
		   	chart.render("chartdiv");
		});
		</script>
	</s:if>
	
	
		
</div>





</body>
</html>
