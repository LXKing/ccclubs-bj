<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<lz:set name="chartXML">
<chart  lineThickness="1" canvasBorderThickness="2" canvasBorderColor="#000000" showValues="0" formatNumberScale="0" anchorRadius="2" divLineAlpha="80" divLineColor="efefef" borderColor="efefef" divLineIsDashed="1" showAlternateHGridColor="1" alternateHGridAlpha="5" alternateHGridColor="CC3300" shadowAlpha="40" labelStep="2" numvdivlines="5" chartRightMargin="35" bgColor="f2f2f2" bgAngle="270" bgAlpha="100,100">
	<categories>
		<s:iterator  value="#request.statistics.datenames" id="datename" status="i">
			<category label="${lz:date(datename,"yy年MM月")}"/>
		</s:iterator>
	</categories>
	<dataset seriesName="注册会员数" color="1D8BD1">
		<s:iterator  value="#request.statistics.regists" id="item" status="i">
			<set name="${lz:date(item.date,"yy年MM月")}" value="${item.value}"/>
		</s:iterator>
	</dataset>
	<dataset seriesName="注册会员数" color="1D8BD1" renderAs="Line">
		<s:iterator  value="#request.statistics.regists" id="item" status="i">
			<set name="${lz:date(item.date,"yy年MM月")}" value="${item.value}"/>
		</s:iterator>
	</dataset>
	<dataset seriesName="会员下单数" color="8BBA00">
		<s:iterator  value="#request.statistics.orders" id="item" status="i">
			<set name="${lz:date(item.date,"yy年MM月")}" value="${item.value}"/>
		</s:iterator>
	</dataset>
	<dataset seriesName="会员下单数" color="8BBA00" renderAs="Line">
		<s:iterator  value="#request.statistics.orders" id="item" status="i">
			<set name="${lz:date(item.date,"yy年MM月")}" value="${item.value}"/>
		</s:iterator>
	</dataset>
</chart>
</lz:set>

<script>
var chartXML="${lz:js(chartXML)}";
</script>