<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>订单明细管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
	<!-- 用户自定义css样式 -->
	<script type="text/javascript" src="${basePath}/admin/css/define.css"></script>
	
	<script type="text/javascript">
		window.basePath="${basePath}";
		window.contextPath="${basePath}unit/";
		window.apiUrl="${basePath}unit/api.do";
		window.handles={};
	</script>
		
	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>
	
	<!------------------------LAZY3Q_JSP_HEAD------------------------>
	<!------------------------LAZY3Q_JSP_HEAD------------------------>	
	
</head>  
<body>


<!------------------------LAZY3Q_JSP_BODY------------------------>
<!------------------------LAZY3Q_JSP_BODY------------------------>

<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td>
	${lz:set("permission",lz:permission(controller,'canQuery'))}
	<s:if test="#request.canQuery==true && #request.permission==true">
  <%@include file="/admin/module/page.jsp"%>
  	</s:if>
  	
  			
	<div class="content">
		<table id="orderdetail_table" ref="CsOrderDetail" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td width="40" tdid="0" align="center">
			 	选择
			 </td>
		     ${lz:set("canListable",lz:listableEx(controller,'csodId',(true||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td width=72 ${all?"width=120":""} tdid="1" ref="csodId" flagKey  title="编号">
			 	<a class="${desc=="csod_id" ? "desc" : ""}${asc=="csod_id" ? "asc" : ""}" href="?${desc=="csod_id" ? "asc=csod_id" : ""}${(asc=="csod_id" || desc!="csod_id" )? "desc=csod_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 </td>
			 </s:if>
										 
			 ${lz:set("canListable",lz:listableEx(controller,'csodOrder',(true||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="3" ref="csodOrder"   title="所属定单">
			 	<a class="${desc=="csod_order" ? "desc" : ""}${asc=="csod_order" ? "asc" : ""}" href="?${desc=="csod_order" ? "asc=csod_order" : ""}${(asc=="csod_order" || desc!="csod_order" )? "desc=csod_order" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属定单
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csodProduct',(true||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="4" ref="csodProduct"   title="所属产品">
			 	<a class="${desc=="csod_product" ? "desc" : ""}${asc=="csod_product" ? "asc" : ""}" href="?${desc=="csod_product" ? "asc=csod_product" : ""}${(asc=="csod_product" || desc!="csod_product" )? "desc=csod_product" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属产品
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csodGoods',(true||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="5" ref="csodGoods"   title="所属商品">
			 	<a class="${desc=="csod_goods" ? "desc" : ""}${asc=="csod_goods" ? "asc" : ""}" href="?${desc=="csod_goods" ? "asc=csod_goods" : ""}${(asc=="csod_goods" || desc!="csod_goods" )? "desc=csod_goods" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属商品
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csodPricer',(true||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="6" ref="csodPricer"   title="所属价格">
			 	<a class="${desc=="csod_pricer" ? "desc" : ""}${asc=="csod_pricer" ? "asc" : ""}" href="?${desc=="csod_pricer" ? "asc=csod_pricer" : ""}${(asc=="csod_pricer" || desc!="csod_pricer" )? "desc=csod_pricer" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属价格
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csodOutlets',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="7" ref="csodOutlets"   title="所属网点">
			 	<a class="${desc=="csod_outlets" ? "desc" : ""}${asc=="csod_outlets" ? "asc" : ""}" href="?${desc=="csod_outlets" ? "asc=csod_outlets" : ""}${(asc=="csod_outlets" || desc!="csod_outlets" )? "desc=csod_outlets" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属网点
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csodModel',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="8" ref="csodModel"   title="所属车型">
			 	<a class="${desc=="csod_model" ? "desc" : ""}${asc=="csod_model" ? "asc" : ""}" href="?${desc=="csod_model" ? "asc=csod_model" : ""}${(asc=="csod_model" || desc!="csod_model" )? "desc=csod_model" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属车型
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csodCar',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="9" ref="csodCar"   title="所属车辆">
			 	<a class="${desc=="csod_car" ? "desc" : ""}${asc=="csod_car" ? "asc" : ""}" href="?${desc=="csod_car" ? "asc=csod_car" : ""}${(asc=="csod_car" || desc!="csod_car" )? "desc=csod_car" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属车辆
			 	</a>
			 </td>
			 </s:if>
					
			 ${lz:set("canListable",lz:listableEx(controller,'csodStart',(true||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=140":""} tdid="11" ref="csodStart"   title="开始时间">
			 	<a class="${desc=="csod_start" ? "desc" : ""}${asc=="csod_start" ? "asc" : ""}" href="?${desc=="csod_start" ? "asc=csod_start" : ""}${(asc=="csod_start" || desc!="csod_start" )? "desc=csod_start" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	开始时间
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csodEnd',(true||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=140":""} tdid="12" ref="csodEnd"   title="结束时间">
			 	<a class="${desc=="csod_end" ? "desc" : ""}${asc=="csod_end" ? "asc" : ""}" href="?${desc=="csod_end" ? "asc=csod_end" : ""}${(asc=="csod_end" || desc!="csod_end" )? "desc=csod_end" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	结束时间
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csodRule',(true||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="13" ref="csodRule"   title="单位">
			 	<a class="${desc=="csod_rule" ? "desc" : ""}${asc=="csod_rule" ? "asc" : ""}" href="?${desc=="csod_rule" ? "asc=csod_rule" : ""}${(asc=="csod_rule" || desc!="csod_rule" )? "desc=csod_rule" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	单位
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csodCount',(true||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="14" ref="csodCount"   title="理论数量">
			 	<a class="${desc=="csod_count" ? "desc" : ""}${asc=="csod_count" ? "asc" : ""}" href="?${desc=="csod_count" ? "asc=csod_count" : ""}${(asc=="csod_count" || desc!="csod_count" )? "desc=csod_count" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	理论数量
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csodCountReal',(true||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="15" ref="csodCountReal"   title="实际数量">
			 	<a class="${desc=="csod_count_real" ? "desc" : ""}${asc=="csod_count_real" ? "asc" : ""}" href="?${desc=="csod_count_real" ? "asc=csod_count_real" : ""}${(asc=="csod_count_real" || desc!="csod_count_real" )? "desc=csod_count_real" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	实际数量
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csodPrice',(true||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="16" ref="csodPrice"   title="理论单价">
			 	<a class="${desc=="csod_price" ? "desc" : ""}${asc=="csod_price" ? "asc" : ""}" href="?${desc=="csod_price" ? "asc=csod_price" : ""}${(asc=="csod_price" || desc!="csod_price" )? "desc=csod_price" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	理论单价
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csodPriceReal',(true||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="17" ref="csodPriceReal"   title="实际单价">
			 	<a class="${desc=="csod_price_real" ? "desc" : ""}${asc=="csod_price_real" ? "asc" : ""}" href="?${desc=="csod_price_real" ? "asc=csod_price_real" : ""}${(asc=="csod_price_real" || desc!="csod_price_real" )? "desc=csod_price_real" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	实际单价
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csodTotal',(true||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="18" ref="csodTotal"   title="理论金额">
			 	<a class="${desc=="csod_total" ? "desc" : ""}${asc=="csod_total" ? "asc" : ""}" href="?${desc=="csod_total" ? "asc=csod_total" : ""}${(asc=="csod_total" || desc!="csod_total" )? "desc=csod_total" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	理论金额
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csodRebate',(true||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="19" ref="csodRebate"   title="折扣">
			 	<a class="${desc=="csod_rebate" ? "desc" : ""}${asc=="csod_rebate" ? "asc" : ""}" href="?${desc=="csod_rebate" ? "asc=csod_rebate" : ""}${(asc=="csod_rebate" || desc!="csod_rebate" )? "desc=csod_rebate" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	折扣
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csodTotalReal',(true||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="20" ref="csodTotalReal"   title="实际金额">
			 	<a class="${desc=="csod_total_real" ? "desc" : ""}${asc=="csod_total_real" ? "asc" : ""}" href="?${desc=="csod_total_real" ? "asc=csod_total_real" : ""}${(asc=="csod_total_real" || desc!="csod_total_real" )? "desc=csod_total_real" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	实际金额
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csodRemark',(true||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=200":""} tdid="21" ref="csodRemark"   title="备注">
			 	<a class="${desc=="csod_remark" ? "desc" : ""}${asc=="csod_remark" ? "asc" : ""}" href="?${desc=="csod_remark" ? "asc=csod_remark" : ""}${(asc=="csod_remark" || desc!="csod_remark" )? "desc=csod_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备注
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csodAddTime',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=140":""} tdid="22" ref="csodAddTime"   title="添加时间">
			 	<a class="${desc=="csod_add_time" ? "desc" : ""}${asc=="csod_add_time" ? "asc" : ""}" href="?${desc=="csod_add_time" ? "asc=csod_add_time" : ""}${(asc=="csod_add_time" || desc!="csod_add_time" )? "desc=csod_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 </td>
			 </s:if>
		  </tr>
		 </thead>	
		 <tbody>
		 	<s:iterator value="#request.page.result" id="item" status="i">
		  <tr id="${item.csodId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csodId}"/>
			</td>
		   	
			${lz:set("canListable",lz:listableEx(controller,'csodId',(true||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csodId" class=" node">
				 
				 ${item.csodId$}
			</td>
			</s:if>
			 	
			 	
			${lz:set("canListable",lz:listableEx(controller,'csodOrder',(true||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csodOrder" class="" relate="${item.csodOrder}">
				 
				 ${item.csodOrder$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csodProduct',(true||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csodProduct" class="" relate="${item.csodProduct}">
				 
				 ${item.csodProduct$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csodGoods',(true||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csodGoods" class="" relate="${item.csodGoods}">
				 
				 ${item.csodGoods$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csodPricer',(true||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csodPricer" class="" relate="${item.csodPricer}">
				 
				 ${item.csodPricer$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csodOutlets',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csodOutlets" class="" relate="${item.csodOutlets}">
				 
				 ${item.csodOutlets$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csodModel',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csodModel" class="" relate="${item.csodModel}">
				 
				 ${item.csodModel$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csodCar',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csodCar" class="" relate="${item.csodCar}">
				 
				 ${item.csodCar$}
			</td>
			</s:if>
			 	
			 	
			${lz:set("canListable",lz:listableEx(controller,'csodStart',(true||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csodStart" class="">
				 
				 ${item.csodStart$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csodEnd',(true||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csodEnd" class="">
				 
				 ${item.csodEnd$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csodRule',(true||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csodRule" class="" relate="${item.csodRule}">
				 
				 ${item.csodRule$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csodCount',(true||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csodCount" class="">
				 
				 ${item.csodCount$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csodCountReal',(true||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csodCountReal" class="">
				 
				 ${item.csodCountReal$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csodPrice',(true||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csodPrice" class="">
				 
				 ${item.csodPrice$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csodPriceReal',(true||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csodPriceReal" class="">
				 
				 ${item.csodPriceReal$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csodTotal',(true||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csodTotal" class="">
				 
				 ${item.csodTotal$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csodRebate',(true||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csodRebate" class="">
				 
				 ${item.csodRebate$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csodTotalReal',(true||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csodTotalReal" class="">
				 
				 ${item.csodTotalReal$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csodRemark',(true||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csodRemark" class="">
				 
				 ${item.csodRemark$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csodAddTime',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csodAddTime" class="">
				 
				 ${item.csodAddTime$}
			</td>
			</s:if>
		  </tr>
		 </s:iterator>
		 </tbody>			 
	  </table>
	  
  </div>
  <%@include file="/admin/module/page.jsp"%>
  
 </td></tr></table>

<script>

	<s:if test="#request.alias=='join'">
	window.objects = ${objects==null ? null : (lz:json(objects))};
	</s:if>	
	window.entrypoint="${entrypoint}";
	
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

	/*
	* 自定义操作
	*/
	function HandleSel(operate,id){
		if(!id){
			var checker=$(".check:checked");
			if(checker.length!=1){
				$.tips("请选择一个需要操作的记录.");
				return;
			}
			id = checker.val();
		}
		
		if(window.getHandle && window.getHandle(operate,id)!=null)//本窗口的操作函数获取句柄
			window.getHandle(operate)(operate,id);
		else if(window.handles && window.handles[operate])//本窗口的操作函数句柄
			window.handles[operate](operate,id);
		else if(typeof(caller)!="undefined" && caller.handles && caller.handles[operate])//调用窗口的操作函数句柄
			caller.handles[operate](operate,id);
		else if(parent && parent.handles && parent.handles[operate])//父窗口的操作函数句柄
			parent.handles[operate](operate,id);
		else{
			var url = "${basePath}${namespace}orderdetail_edit.do";
			var params = {entrypoint:"${entrypoint}",id:id,controller:{title:operate}};
			href(url,params);
		}
	}

</script>


<!------------------------LAZY3Q_JSP_BOTTOM------------------------>
<!------------------------LAZY3Q_JSP_BOTTOM------------------------>


</body>
</html>