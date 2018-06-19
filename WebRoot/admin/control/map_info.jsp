<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>

	<%
	com.ccclubs.model.CsState csState = com.ccclubs.model.CsState.Get(com.lazy3q.web.helper.$.add("cssNumber",request.getAttribute("number")));
	com.ccclubs.model.CsState evState = com.ccclubs.model.CsState.Get(com.lazy3q.web.helper.$.add("cssNumber","EV"+request.getAttribute("number")));
	if(csState==null)csState=evState;
	if(evState==null)evState=csState;
	request.setAttribute("csState",csState);
	request.setAttribute("evState",evState); 	
	%>

	<div class="map-status-info">
		<ul>
			<li>
				<a style="float:right;text-decoration:none;" class="button" href="javascript:void(0);" onclick="window.open('map_history.do?number=${csState.cssNumber}')">
					历史轨迹
				</a>
				
				<span class="zt">
					车牌号码：
				</span>
				<a onclick="parent.href('${basePath}admin/object/car_details.do?id=${csCar.cscId}',{ctrl:{editable:false}})" href="javascript:void(0);">
				${csState.cssNumber}
				</a>
				${csCar.cscStatus==1?"在线运营":""}
				${csCar.cscStatus==2?"已下线":""}
				${csCar.cscStatus==3?"企业可租":""}
			</li>
			<li>
				<span class="zt">
					车型信息：
				</span>
				${csCar.$cscModel.cscmName}/
				${csCar.$cscModel.cscmType==0?"汽油车":"电动车"}/
				${csCar.$cscModel.cscmFile==0?"自动档":"手动档"}
			</li>
			<li>
				<div style="float:right;">
					车速：
					<span class="statustitle">
						${csState.cssSpeed}
					</span>
					- 转速：
					<span class="statustitle">
						${csState.cssMotor}
					</span>
				</div>
				<span class="zt">
					接收时间：
				</span>
				${lz:date(csState.cssAddTime,"yyyy-MM-dd HH:mm:ss")}
			</li>
			<!-- 
			<li>
				<span class="zt">
					车辆状态：
				</span>
				<span class="statustitle">
					${empty csState.cssRented ? "未知" : csState.cssRented}
				</span>
				-
				<span class="statustitle">
					${csState.cssWarn=="0"?"正常":"有报警"}
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
			 -->
		</ul>
		<s:if test="#request.csCar.$cscModel.cscmType==0">		
			<table align="center" border="0" cellspacing="1" cellpadding="0">
				<tbody>
					<tr>
						<td width="33%">
							<strong>总里程</strong>
						</td>
						<td width="34%">
							<strong>订单里程</strong>
						</td>
						<td width="33%">
							<strong>车速</strong>
						</td>
					</tr>
					<tr>
						<td>
							${csState.cssObdMile}
						</td>
						<td>
							${csState.cssMileage}
						</td>
						<td>
							${csState.cssSpeed}
						</td>
					</tr>
					<tr>
						<td>
							<strong>燃油量</strong>
						</td>
						<td>
							<strong>蓄电池电量</strong>
						</td>
						<td>
							<strong>转速</strong>
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
							${csState.cssMotor}
						</td>
					</tr>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<table align="center" border="0" cellspacing="1" cellpadding="0">
				<tbody>
					<tr>
						<td width="34%">
							<strong>车辆里程</strong>
						</td>
						<td width="33%">
							<strong>订单里程</strong>
						</td>
						<td width="33%">
							<strong>续航里程</strong>
						</td>
					</tr>
					<tr>
						<td>
							${csState.cssObdMile}
						</td>
						<td>
							${csState.cssMileage}
						</td>
						<td>
							${lz:or(evState.cssEndurance,"无数据")}
						</td>
					</tr>
					<tr>
						<td class="mileB">
							<strong>蓄电池电量</strong>
						</td>
						<td class="mileC">
							<strong>动力电池电量</strong>
						</td>
						<td>
							<strong>是否在充电</strong>
						</td>
					</tr>
					<tr>
						<td>
							${csState.cssPower}
						</td>
						<td>
							${lz:or(evState.cssEvBattery,"无数据")}
						</td>
						<td>
							${evState.cssCharging=="1"?"正在充电":"不在充电"}
						</td>
					</tr>
				</tbody>
			</table>
		
		</s:else>
		${lz:set("noOrder",lz:empty(csState.cssOrder))}
		<ul>
			<li>
				<span class="zt">
					当前定单：
				</span>
				<s:if test="#request.noOrder==false">
				<a title="${get:CsOrder(csState.cssOrder).csoRemark}" onclick="parent.href('${basePath}admin/service/order_details.do?id=${csState.cssOrder}',{ctrl:{editable:false}})" href="javascript:void(0);">
					${csState.cssOrder}
				</a>
				[${get:CsMember(get:CsOrder(csState.cssOrder).csoUseMember).csmName}]
				</s:if>
				<s:else>
					没有订单
				</s:else>
			</li>
		</ul>		
		<s:if test="#request.noOrder==false">
		<ul>
			<li>
				<span class="zt">
					定单时间：
				</span>
				${lz:date(get:CsOrder(csState.cssOrder).csoStartTime,"MM月dd日HH点mm分")}
				-
				${lz:date(get:CsOrder(csState.cssOrder).csoFinishTime,"MM月dd日HH点mm分")}
			</li>
		</ul>
		</s:if>
		<!-- 如果地图监控可以远程控制，则扩展权限的第一项必须有权限 -->
		<s:if test="#request.canExp[0]==true">
		<div id="options">
			<a onclick="remote('开门',${csCar.cscId})" href="javascript:void(0);">开门</a>
			<a onclick="remote('关门',${csCar.cscId})" href="javascript:void(0);">关门</a>
			<a onclick="remote('可启动',${csCar.cscId})" href="javascript:void(0);">可启动</a>
			<a onclick="remote('断油电',${csCar.cscId})" href="javascript:void(0);">断油电</a>
			<a onclick="remote('初始化',${csCar.cscId})" href="javascript:void(0);">初始化</a>
			<a onclick="remote('同步',${csCar.cscId})" href="javascript:void(0);">同步</a>
		</div>
		</s:if>
	</div>