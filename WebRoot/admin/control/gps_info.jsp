<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>

	<%
	com.ccclubs.model.CsState csState = (com.ccclubs.model.CsState)request.getAttribute("csState");
	if(csState!=null){
		com.ccclubs.model.CsState evState = com.ccclubs.model.CsState.Get(com.lazy3q.web.helper.$.add("cssNumber","EV"+csState.getCssNumber()));
		if(evState!=null){
			request.setAttribute("csState",evState);
			request.setAttribute("evState",evState);
		}
	} 
	java.util.List datas = com.ccclubs.model.CsHistoryState.list(
		com.lazy3q.web.helper.$
		.add("cshsNumber",csState.getCssNumber())
		.add("desc","cshs_add_time")
		.add("cshsMoDataNot","not"), 
		10
	);
	request.setAttribute("datas",datas);
	request.setAttribute("dataCount",datas.size());
	%>

	<div class="map-status-info"> 
		<ul>
			<li>
				<s:if test="#request.dataCount>0">
				<textarea id="data" style="display:none;">
					<table style="margin:0px 20px;" id="order_table" ref="CsOrder" width="800" class="table" border="0" cellspacing="1" cellpadding="0">
						<thead class="state-header">
						  <tr>
							 <td width="80" align="center">
								车牌号码
							 </td>
							 <td width="150" align="center">
								添加时间
							 </td>
							 <td width="*" align="center">
								接口数据
							 </td>
						  <tr>
						</thead>
						<tbody>
							<s:iterator value="#request.datas" id="data" status="i">
						   <tr>
							 <td width="160" align="center">
								${data.cshsNumber}
							 </td>
							 <td width="160" align="center">
								${lz:date(data.cshsAddTime,"yyyy-MM-dd HH:mm:ss")}
							 </td>
							 <td onclick="alert($(this).html())" width="300" align="center">
								${data.cshsMoData}
							 </td>
						  <tr>
							</s:iterator>
						</tbody>
					</table>
				</textarea>
				<a style="float:right;text-decoration:none;" class="button" href="javascript:void(0);" onclick="top.Alert($('#data').val())">
					接口数据
				</a>
				</s:if>
			
				<span class="zt">
					车牌号：
				</span>
				${csState.cssNumber}
			</li>
			<li>
				<span class="zt">
					车型：
				</span>
				${csCar.$cscModel.cscmName}/
				${csCar.$cscModel.cscmType==0?"汽油车":"电动车"}/
				${csCar.$cscModel.cscmFile==0?"自动档":"手动档"}
			</li>
			<li>
				<span class="zt">
					接收时间：
				</span>
				${lz:date(csState.cssAddTime,"yyyy-MM-dd HH:mm:ss")}
			</li>
			<li>
				<span class="zt">
					车辆状态：
				</span>
				<span class="statustitle">
					${empty csState.cssRented ? "未知" : csState.cssRented}
				</span>
				-
				<span class="statustitle">
					${csState.cssWarn=="0"?"有报警":"正常"}
				</span>
				-
				<span class="statustitle">
					${csState.cssCharging=="0"?"在充电":"不在充电"}
				</span>
				车速：
				<span class="statustitle">
					${csState.cssSpeed}
				</span>
				- 转速：
				<span class="statustitle">
					${csState.cssMotor}
				</span>
			</li>
		</ul>
		<table align="center" border="0" cellspacing="1" cellpadding="0">
			<tbody>
				<tr>
					<td class="mileA">
						油里程
					</td>
					<td class="mileB">
						电里程
					</td>
					<td class="mileC">
						总里程
					</td>
				</tr>
				<tr>
					<td>
						${csState.cssFuelMileage}
					</td>
					<td>
						${csState.cssElectricMileage}
					</td>
					<td>
						${csState.cssMileage}
					</td>
				</tr>
				<tr>
					<td class="mileA">
						燃油量
					</td>
					<td class="mileB">
						蓄电池电量
					</td>
					<td class="mileC">
						动力电池电量
					</td>
				</tr>
				<tr>
					<td>
						${csState.cssOil}
					</td>
					<td>
						${csState.cssPower}
					</td>
					<td>
						${csState.cssEvBattery}
						${lz:set("haveEndurance",(evState.cssEndurance>0)) }
						<s:if test="#request.haveEndurance==true">
						可续航(${evState.cssEndurance})
						</s:if>
					</td>
				</tr>
			</tbody>
		</table>
		
	</div>