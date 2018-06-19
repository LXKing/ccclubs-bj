<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>

	<%
	com.ccclubs.model.CsElecHistory csState = (com.ccclubs.model.CsElecHistory)request.getAttribute("csehHistory");
	%>

	<div class="map-status-info">
		<ul>
			<li>
				<span class="zt" style="width:70px;text-align:left;">
					网点信息： 
				</span>
				${outlets.csoName}
				<br/>
				<span class="zt" style="width:70px;text-align:left;">
					网点地址：
				</span>
				${outlets.csoAddress}
				<br/>
				<span class="zt" style="width:70px;text-align:left;">
					停车地点：
				</span>
				${outlets.csoSpace}
				
				<s:if test="#request.piles!=null&&#request.piles.size>0">
					<br/>
					<span class="zt" style="width:90px;text-align:left;">
						充电桩信息：
					</span>
				</s:if>
			</li>
		</ul>
		
		<s:if test="#request.piles!=null&&#request.piles.size>0">
			<table align="center" border="0" cellspacing="1" cellpadding="0" style="margin:0px;">
				<tbody>
					<tr>
						<td width="6%">
							<strong>编号</strong>
						</td>
						<td width="16%">
							<strong>识别码</strong>
						</td>
						<td width="16%">
							<strong>电桩状态</strong>
						</td>
						<td width="16%">
							<strong>充电量</strong>
						</td>
						<td width="16%">
							<strong>充电时长</strong>
						</td>
						<td width="16%">
							<strong>上报时间</strong>
						</td>
					</tr>
					
					<s:iterator value="#request.piles" id="pile" status="i">
						<tr>
							<td>
								${pile.csppIndex}
							</td>
							<td>
								${pile.csppNo}
							</td>
							<td>
								<s:if test="#pile.values.csehHistory!=null">
									<s:if test="#pile.values.csehHistory.csehStatusCode==20050">
										<b style="color: #6CABFF;">充电中</b>
									</s:if>
									<s:elseif test="#pile.values.csehHistory.csehStatusCode==20000">
										<b style="color: #7AB900;">可充电</b>
									</s:elseif>
									<s:else>
										<b style="color: #FF2525;">不可用（<s:property value="#pile.values.csehHistory.csehStatusText" />）</b>
									</s:else>	
								</s:if>
							</td>
							<td>
								<s:if test="#pile.values.csehHistory!=null">${pile.values.csehHistory.csehEleAmount}</s:if>
							</td>
							<td>
								<s:if test="#pile.values.csehHistory!=null">${pile.values.csehHistory.csehTimeLong}</s:if>
							</td>
							<td>
								<s:if test="#pile.values.csehHistory!=null">${lz:date(pile.values.csehHistory.csehCollectTime, "yyyy-MM-dd HH:mm:ss")}</s:if>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		
<!-- 		<table align="center" border="0" cellspacing="1" cellpadding="0"> -->
<!-- 			<tbody> -->
<!-- 				<tr> -->
<!-- 					<td width="33%"> -->
<!-- 						<strong>电桩状态</strong> -->
<!-- 					</td> -->
<!-- 					<td width="34%"> -->
<!-- 						<strong>充电量（KWH）</strong> -->
<!-- 					</td> -->
<!-- 					<td width="33%"> -->
<!-- 						<strong>充电时长（秒）</strong> -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td> -->
<!-- 						${csehHistory.csehStatusText} -->
<!-- 					</td> -->
<!-- 					<td> -->
<!-- 						${csehHistory.csehEleAmount}度 -->
<!-- 					</td> -->
<!-- 					<td> -->
<!-- 						${csehHistory.csehTimeLong} -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 			</tbody> -->
<!-- 		</table> -->
		
<!-- 		<ul> -->
<!-- 			<span class="zt"> -->
<!-- 				采集时间： -->
<!-- 				</span> -->
<!-- 				${lz:date(csehHistory.csehCollectTime,"yyyy-MM-dd HH:mm:ss")} -->
<!-- 			</ -->
<!-- 		</ul> -->
	</div>