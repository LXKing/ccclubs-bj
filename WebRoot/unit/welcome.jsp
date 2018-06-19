<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	<title>${lz:config("project_name")}</title> 
</head>

<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>

<style>
.plate{box-shadow:none;border:0px;}
.process{height:180px;width:890px;margin:0px auto;position:relative;}
.process .node{position:absolute;background:url(images/plate.png) center;border:1px solid #efefef;line-height:25px;width:220px;height:100px;}
.process .node .icon{width:40px;height:40px;margin:20px 10px;float:left;}
.process .node .cnt{margin-left:60px;}
.process .node .tile{font-size:16px;display:block;margin-top:10px;}
.process .node .links a{color:#999999;}
.process .dir{position:absolute;}
.plate{background:url(images/plate.png) center;}
.table thead td,.table tbody td,.toolbar{height:34px;line-height:34px;}
</style>


<body>

<div class="title">
	系统首页
</div>

<div class="plate">

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td width="100" align="center" valign="middle">
			<img src="${basePath}admin/images/icons/20088256427828778017.png" />
		</td>
	    <td width="250" align="left" valign="middle">
			<h2>欢迎您，${unit_login.$csuuUnitInfo.csuiName}</h2>
		</td>
		<td width="150" align="left" valign="middle">
			<h4>登录帐号：${unit_login.csuuUsername}</h4>
		</td>
		<td width="*" align="left" valign="middle">
			<h4>本月订单数：${orderCount}个</h4>
		</td>
	  </tr>
	</table>
</div>


<script>
$(function(){
	$(".links .link").click(function(){
		parent.href($(this).attr("href"),$(this).html());
		return false;
	});
});
showTips("${tips.value}");
function href(url,params){
	if(${controller.mutual=="dialog"})
		top.$.showModalDialog({url:url},params,window);
	else if(${controller.mutual=="page"})
		$.submit(url,params,"_self");
	else if(true)
		top.$.showModalDialog({url:url},params,window);
	else
		$.submit(url,params,"_self");	
}
function addAllOrder(){
	var url = "${basePath}unit/order.edit.do";
	var params = {entrypoint:"${entrypoint}",controller:{title:"内部用车"}};
	href(url,params);
}

function addProxyOrder(){
	var url = "${basePath}unit/order.edit.do";
	var params = {entrypoint:"${entrypoint}",controller:{title:"代理订单"}};
	href(url,params);
}
</script>

<!-- 企业流程 -->
<div class="process">
	
	<div class="node" style="top:50px;left:0px;">
		<img class="icon" src="${basePath}admin/images/icons/20088256423826577806.png"/>
		<div class="cnt">
			<span class="tile">企业(租车)管理</span>		
			<div class="links">
				${lz:set("limit",lz:idin(unit_login.csuuLimit,1))}
	    		<s:if test="#request.limit==true && #session.unit_login.csuuIsRoot==true">
					<a class="link" href="user.do">权限管理</a>
				</s:if>
				${lz:set("limit",lz:idin(unit_login.csuuLimit,2))}
	    		<s:if test="#request.limit==true">
					<a class="link" href="account.do">用车帐号</a>
				</s:if>
				<br/>
				${lz:set("limit",lz:idin(unit_login.csuuLimit,3))}
	    		<s:if test="#request.limit==true">
					<a class="link" href="car.do">车辆管理</a>
				</s:if>
				<s:if test="#session.unit_login.$csuuUnitInfo.csuiType==true">
					${lz:set("limit",lz:idin(unit_login.csuuLimit,7))}
		    		<s:if test="#request.limit==true">
						<a class="link" href="airport.do">机场服务</a>
					</s:if>
				</s:if>
			</div>
		</div>
	</div>
	
	<img class="dir" src="images/c.png" style="top:65px;left:248px;"/>
	
	<div class="node" style="top:50px;left:350px;">
		<img class="icon" src="${basePath}admin/images/icons/20088256422923477809.png"/>
		<div class="cnt">
			<span class="tile">订单管理</span>		
			<div class="links">
				${lz:set("limit",lz:idin(unit_login.csuuLimit,0))}
	    		<s:if test="#request.limit==true">
					<a class="link" href="order.do">全部订单</a>
				</s:if>
				<a onclick="addAllOrder()" href="javascript:void(0);">内部用车</a>
				</br>
				<s:if test="#session.unit_login.$csuuUnitInfo.csuiType==true">
				${lz:set("limit",lz:idin(unit_login.csuuLimit,1))}
	    		<s:if test="#request.limit==true">
					<a class="link" href="fee.do">价格配置</a>
				</s:if>
				</s:if>
				<s:if test="#session.unit_login.$csuuUnitInfo.csuiType==true">
					<a onclick="addProxyOrder()" href="javascript:void(0);">代理下单</a>
				</s:if>
			</div>
		</div>
	</div>
	
	
	<img class="dir" src="images/c.png" style="top:65px;left:600px;"/>

	<div class="node" style="top:50px;left:700px;">
		<img class="icon" src="${basePath}admin/images/icons/20088256422923477806.png"/>
		<div class="cnt">
			<span class="tile">财务对账</span>		
			<div class="links">
				${lz:set("limit",lz:idin(unit_login.csuuLimit,8))}
	    		<s:if test="#request.limit==true">
					<a class="link" href="bill.do">对账报表</a>
				</s:if>
				${lz:set("limit",lz:idin(unit_login.csuuLimit,4))}
	    		<s:if test="#request.limit==true">
					<a class="link" href="pack.do">企业套餐</a>
				</s:if>
				<br/>
				${lz:set("limit",lz:idin(unit_login.csuuLimit,6))}
	    		<s:if test="#request.limit==true">
					<a class="link" href="record.do">套餐使用记录</a>
				</s:if>
			</div>
		</div>
	</div>


</div>


<div class="content">
	<div class="toolbar">&nbsp;&nbsp;&nbsp;当前订单</div>
	<table id="order_table" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
			 			 
			 <td  ${all?"width":"refer"}="120" title="系统订单">
			 	<a class="${desc=="csuo_order" ? "desc" : ""}${asc=="csuo_order" ? "asc" : ""}" href="?${desc=="csuo_order" ? "asc=csuo_order" : ""}${(asc=="csuo_order" || desc!="csuo_order" )? "desc=csuo_order" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单号
			 	</a>
			 </td>
			 
			 
			 <td  ${all?"width":"refer"}="120" title="订单类型">
			 	<a class="${desc=="csuo_type" ? "desc" : ""}${asc=="csuo_type" ? "asc" : ""}" href="?${desc=="csuo_type" ? "asc=csuo_type" : ""}${(asc=="csuo_type" || desc!="csuo_type" )? "desc=csuo_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单类型
			 	</a>
			 </td>
			 
			 
			 <td  ${all?"width":"refer"}="120" title="子帐号">
			 	子帐号
			 </td>
			 
			
			 <td  ${all?"width":"refer"}="120" title="订单车辆">
			 	订单车辆
			 </td>
			
			 
			 <td  ${all?"width":"refer"}="200" title="使用人姓名">
			 	<a class="${desc=="csuo_username" ? "desc" : ""}${asc=="csuo_username" ? "asc" : ""}" href="?${desc=="csuo_username" ? "asc=csuo_username" : ""}${(asc=="csuo_username" || desc!="csuo_username" )? "desc=csuo_username" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	使用人姓名
			 	</a>
			 </td>
			
			 			 
			 <td  ${all?"width":"refer"}="200" title="使用人手机">
			 	<a class="${desc=="csuo_mobile" ? "desc" : ""}${asc=="csuo_mobile" ? "asc" : ""}" href="?${desc=="csuo_mobile" ? "asc=csuo_mobile" : ""}${(asc=="csuo_mobile" || desc!="csuo_mobile" )? "desc=csuo_mobile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	使用人手机
			 	</a>
			 </td>

			 <s:if test="#request.all==true">			
			 <td  ${all?"width":"refer"}="200" title="身份证号">
			 	<a class="${desc=="csuo_idcard" ? "desc" : ""}${asc=="csuo_idcard" ? "asc" : ""}" href="?${desc=="csuo_idcard" ? "asc=csuo_idcard" : ""}${(asc=="csuo_idcard" || desc!="csuo_idcard" )? "desc=csuo_idcard" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	身份证号
			 	</a>
			 </td>
			 </s:if>
			 <s:if test="#request.all==true">			 
			 <td  ${all?"width":"refer"}="120" title="天租价格">
			 	<a class="${desc=="csuo_day_price" ? "desc" : ""}${asc=="csuo_day_price" ? "asc" : ""}" href="?${desc=="csuo_day_price" ? "asc=csuo_day_price" : ""}${(asc=="csuo_day_price" || desc!="csuo_day_price" )? "desc=csuo_day_price" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	天租价格
			 	</a>
			 </td>
			 </s:if>
			 <s:if test="#request.all==true">			
			 <td  ${all?"width":"refer"}="120" title="小时价格">
			 	<a class="${desc=="csuo_hour_price" ? "desc" : ""}${asc=="csuo_hour_price" ? "asc" : ""}" href="?${desc=="csuo_hour_price" ? "asc=csuo_hour_price" : ""}${(asc=="csuo_hour_price" || desc!="csuo_hour_price" )? "desc=csuo_hour_price" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	小时价格
			 	</a>
			 </td>
			 </s:if>
			 
			 <td  ${all?"width":"refer"}="140" title="开始时间">
			 	<a class="${desc=="csuo_start_time" ? "desc" : ""}${asc=="csuo_start_time" ? "asc" : ""}" href="?${desc=="csuo_start_time" ? "asc=csuo_start_time" : ""}${(asc=="csuo_start_time" || desc!="csuo_start_time" )? "desc=csuo_start_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	开始时间
			 	</a>
			 </td>			 
			 			 
			 <td  ${all?"width":"refer"}="140" title="结束时间">
			 	<a class="${desc=="csuo_finish_time" ? "desc" : ""}${asc=="csuo_finish_time" ? "asc" : ""}" href="?${desc=="csuo_finish_time" ? "asc=csuo_finish_time" : ""}${(asc=="csuo_finish_time" || desc!="csuo_finish_time" )? "desc=csuo_finish_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	结束时间
			 	</a>
			 </td>
			 
			 <s:if test="#request.all==true">			 
			 <td  ${all?"width":"refer"}="140" title="还车时间">
			 	<a class="${desc=="csuo_ret_time" ? "desc" : ""}${asc=="csuo_ret_time" ? "asc" : ""}" href="?${desc=="csuo_ret_time" ? "asc=csuo_ret_time" : ""}${(asc=="csuo_ret_time" || desc!="csuo_ret_time" )? "desc=csuo_ret_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	还车时间
			 	</a>
			 </td>
			 </s:if>
				
			 <s:if test="#session.unit_login.$csuuUnitInfo.csuiType==true">	 
			 <td  ${all?"width":"refer"}="120" title="应付金额">
			 	<a class="${desc=="csuo_pay_need" ? "desc" : ""}${asc=="csuo_pay_need" ? "asc" : ""}" href="?${desc=="csuo_pay_need" ? "asc=csuo_pay_need" : ""}${(asc=="csuo_pay_need" || desc!="csuo_pay_need" )? "desc=csuo_pay_need" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	应付金额
			 	</a>
			 </td>
			 </s:if>
			 
			 <s:if test="#session.unit_login.$csuuUnitInfo.csuiType==true">
			 <td  ${all?"width":"refer"}="120" title="实付金额">
			 	<a class="${desc=="csuo_pay_real" ? "desc" : ""}${asc=="csuo_pay_real" ? "asc" : ""}" href="?${desc=="csuo_pay_real" ? "asc=csuo_pay_real" : ""}${(asc=="csuo_pay_real" || desc!="csuo_pay_real" )? "desc=csuo_pay_real" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	实付金额
			 	</a>
			 </td>
			 </s:if>
			 
			 <s:if test="#request.all==true">			 
			 <td  ${all?"width":"refer"}="140" title="修改时间">
			 	<a class="${desc=="csuo_update_time" ? "desc" : ""}${asc=="csuo_update_time" ? "asc" : ""}" href="?${desc=="csuo_update_time" ? "asc=csuo_update_time" : ""}${(asc=="csuo_update_time" || desc!="csuo_update_time" )? "desc=csuo_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 </td>
			 </s:if>
			 <s:if test="#request.all==true">			 
			 <td  ${all?"width":"refer"}="140" title="添加时间">
			 	<a class="${desc=="csuo_add_time" ? "desc" : ""}${asc=="csuo_add_time" ? "asc" : ""}" href="?${desc=="csuo_add_time" ? "asc=csuo_add_time" : ""}${(asc=="csuo_add_time" || desc!="csuo_add_time" )? "desc=csuo_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 </td>
			 </s:if>
			 			 
			 <td  ${all?"width":"refer"}="120" title="状态">
			 	<a class="${desc=="csuo_status" ? "desc" : ""}${asc=="csuo_status" ? "asc" : ""}" href="?${desc=="csuo_status" ? "asc=csuo_status" : ""}${(asc=="csuo_status" || desc!="csuo_status" )? "desc=csuo_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单状态
			 	</a>
			 	(系统状态)
			 </td>
			 
		  </tr>
		 </thead>	
		 <tbody>
		 	<s:iterator value="#request.orders" id="item" status="i">
		  <tr id="${item.csuoId}" >
						
			<td ref="csuoOrder" class=" node" relate="${item.csuoOrder}">
				<s:if test="#item.csuoOrder>0">
			 	[系统]${item.csuoOrder}
			 	</s:if>
			 	<s:if test="#item.csuoSpecial>0">
			 	[专车]${item.csuoSpecial}
			 	</s:if>
			</td>
			
			
			<td ref="csuoType" class="">
					 ${item.csuoType==0?"客户订单":""}
					 ${item.csuoType==1?"内部用车":""}
			</td>
							
			
			<td ref="csuoUnitChild" class="" relate="${item.csuoUnitChild}">
				${get:CsMember(item.csuoUnitChild).csmName}
			</td>
			
			
			<td ref="csuoCar" class="" relate="${item.csuoCar}">
				${item.csuoNumber$}
			</td>
			
			
			<td ref="csuoUsername" class="">
				${item.csuoUsername}
			</td>
			
			
					
			<td ref="csuoMobile" class="">
				${item.csuoMobile}
			</td>
			
			<s:if test="#request.all==true">			
			<td ref="csuoIdcard" class="">
				${item.csuoIdcard}
			</td>
			</s:if>
			<s:if test="#request.all==true">			
			<td ref="csuoDayPrice" class="">
			 	${item.csuoDayPrice}
			</td>
			</s:if>
			<s:if test="#request.all==true">			
			<td ref="csuoHourPrice" class="">
			 	${item.csuoHourPrice}
			</td>
			</s:if>
			
			
			<td ref="csuoStartTime" class="">
				 <s:date name="#item.csuoStartTime" format="yyyy-MM-dd HH:mm:ss"/>
			</td>			
						
			<td ref="csuoFinishTime" class="">
				 <s:date name="#item.csuoFinishTime" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
			
			<s:if test="#request.all==true">			
			<td ref="csuoRetTime" class="">
				 <s:date name="#item.csuoRetTime" format="yyyy-MM-dd HH:mm:ss"/>
			</td>			
			</s:if>
			
			<s:if test="#session.unit_login.$csuuUnitInfo.csuiType==true">
			<td ref="csuoPayNeed" class="">
			 	${item.csuoPayNeed}
			</td>
			</s:if>
			
			<s:if test="#session.unit_login.$csuuUnitInfo.csuiType==true">		
			<td ref="csuoPayReal" class="">
			 	${item.csuoPayReal}
			</td>
			</s:if>
			
			<s:if test="#request.all==true">			
			<td ref="csuoUpdateTime" class="">
				<s:date name="#item.csuoUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
			</s:if>
			<s:if test="#request.all==true">			
			<td ref="csuoAddTime" class="">
				<s:date name="#item.csuoAddTime" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
			</s:if>
						
			<td ref="csuoStatus" class="">
				 ${item.$csuoOrder.csoStatus$}${item.$csuoSpecial.csscStatus$}
			</td>
			
		  </tr>
		 </s:iterator>
		 </tbody>			 
	  </table>
</div>

<br/>

</body>
</html>