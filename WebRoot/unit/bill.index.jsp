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

	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>

<body>



<div class="title">对账单</div>
<div class="plate">
	<form class="query" id="queryForm" action="bill.do" method="post">
	${lz:set("haveQuery",false)}
		 
		 	${lz:set("queryable",lz:queryable(controller,'csubMonth'))}
		 	<s:if test="#request.queryable!=false">
		 	${lz:set("haveQuery",true)}
			<dl>
				<dt>帐单月份:</dt>
				<dd>					
		 			<input type="text" class="input" onClick="WdatePicker({dateFmt:'yyyy-MM'})" size="16"  maxlength="32" name="csubMonth" id="csubMonth"  value="${csubMonth}"/>
				</dd>
			</dl>
			</s:if>
		
		 	${lz:set("queryable",lz:queryable(controller,'csubStatus'))}
		 	<s:if test="#request.queryable!=false">
		 	${lz:set("haveQuery",true)}
			<dl>
				<dt>状态:</dt>
				<dd>					
		 			<select id="csubStatus" name="csubStatus" style="width:100px;">
		 			<option value="">全部</option>
						<option value="1" ${csubStatus==1?"selected":""}>未结算</option>
						<option value="2" ${csubStatus==2?"selected":""}>已结算</option>
		 		</select>
				</dd>
			</dl>
			</s:if>
		
			<div class="line"></div>
			<div class="option">
				<table border="0" cellspacing="0" cellpadding="0">
				  <tr>
				    <td>
						<input type="hidden" name="all" value="${all}" />
						<button class="button" type="submit">&nbsp;&nbsp;查询&nbsp;&nbsp;</button>		
						<button class="button" onclick="clearForm()" type="button">&nbsp;&nbsp;重置&nbsp;&nbsp;</button>
					</td>
				  </tr>
				</table>
				<div class="clear"></div>
			</div>
		</form>
	</div>



<div class="content">
	<table id="order_table" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>	
		 	 <td>
			 	账单月份
			 </td>		 			 
			 <td>
			 	车型 
			 </td>
			 <td>
			 	使用时间
			 </td>
			 <td>
			 	套餐时间
			 </td>
			 <td>
			 	超出时间
			 </td>
			 <td>
			 	超出租金
			 </td>
			 <td>
			 	里程数
			 </td>
			 <td>
			 	里程费
			 </td>			 
			 <td>
			 	免责费
			 </td>
			 <td>
			 	接送机次
			 </td>
			 <td>
			 	接送机费
			 </td>
			 <td>
			 	应付额
			 </td>
			 <td>
			 	已付额
			 </td>
			 <td>
			 	状态
			 </td>
		  </tr>
		 </thead>	
		 <tbody>
		 	<s:iterator value="#request.page.result" id="bill" status="i">
		 		<tr>
		 			<td colspan="14" height="5" style="height:5px;line-height:0px;"></td>
		 		</tr>
		 		${lz:set("detailCount",lz:size(bill.details))}
		 		<s:iterator value="#bill.details" id="detail" status="j">
					  <tr>
					  	  <s:if test="#j.index==0">
					  		 <td rowspan="${detailCount}">
							 	${bill.csubMonth}
							 </td>
						  </s:if>		 			 
							 <td>
							 	${detail.$csubdModel.cscmName}
							 </td>
							 <td>
							 	${detail.csubdUseHour}小时
							 </td>
							 <td>
							 	${detail.csubdPkgHour}小时
							 </td>
							 <td>
							 	${detail.csubdTimeOut}小时
							 </td>
							 <td>
							 	${detail.csubdRentOut}元
							 </td>							 
							 <td>
							 	${detail.csubdKm}公里
							 </td>
							 <td>
							 	${detail.csubdFeeKm}元
							 </td>
							 <td>
							 	${detail.csubdFeeSecure}元
							 </td>
						 <s:if test="#j.index==0">
						 	 <td rowspan="${detailCount}">
							 	${bill.csubAirS}
							 </td>
							 <td rowspan="${detailCount}">
							 	${bill.csubFeeAir}
							 </td>
							 <td rowspan="${detailCount}">
							 	${bill.csubNeedPay}
							 </td>
							 <td rowspan="${detailCount}">
							 	${bill.csubRealPay}
							 </td>
							 <td rowspan="${detailCount}">
							 	${bill.csubStatus==0 ? "未审核":""}
							 	${bill.csubStatus==1 ? "未结算":""}
							 	${bill.csubStatus==2 ? "已结算":""}							 	
							 </td>
						 </s:if>
					  </tr>
				</s:iterator>
			</s:iterator>
		 </tbody>			 
	  </table>
</div>
<%@include file="/admin/module/page.jsp"%>

</body>
</html>