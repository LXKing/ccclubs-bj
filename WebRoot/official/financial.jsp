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
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">


<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
${lz:set("window",lz:window())}
<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
	<script type="text/javascript">
		${lz:set("proname","official")}
		window.basePath="${basePath}";
		window.contextPath="${basePath}${proname}/";
		window.apiUrl="${basePath}${proname}/api.do";
		window.handles={};
	</script>
	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>



<div class="title">对账单</div>
<div class="plate">
	<form class="query" id="queryForm" action="financial.do" method="post">
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
				   <select id="csubGroup" name="csubGroup" >
			 			<option value="">全部</option>			 					 					 		
			 			<s:iterator value="#request.csUnitGroups" id="item" status="csUnitGroup">
			 				<option value="${item.csugId}" ${item.csugId==csubGroup?"selected":""}>${item.csugName}</option>
						</s:iterator>
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
	<table id="order_table" class="table"  width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>	
		 	 <td  width="10%">
			 	账单月份
			 </td>		 			 
			 <td   width="15%">
			 	部门
			 </td>
			 <td   width="15%">
			 	订单总计
			 </td>
			 <td   width="15%">
			 	费用总计
			 </td>
			 <td   width="15%">
			 	里程总计
			 </td>
			 <td   width="15%">
			 	时长总计
			 </td> 
			  <td>
			 	操作
			 </td>
		  </tr> 
		 </thead>	
		 <tbody>
		 	<s:iterator value="#request.page.result" id="bill" status="i">
		 		<tr>
		 			<td colspan="7" height="5" style="height:5px;line-height:0px;"></td>
		 		</tr>
					  <tr>
					  		 <td>
							 	${bill.csubMonth}
							 </td>
							 <td>
							 	${bill.csubGroup$}
							 </td>
							 <td>
							 	${bill.csubOrderS }
							 </td>
							 <td>
							 	${bill.csubFeeOrder }
							 </td>
							 <td>
							 	${bill.csubMileage }
							 </td>
							 <td>
							 	${bill.csubDuration }
							 </td> 
							 <td>
							 <input type="hidden" value="${bill.csubId }" ></input>	
							 	<a href="javascript:details('${bill.csubId }')">查看详情  </a>
							 	
							 </td>
					  </tr>
			</s:iterator>
		 </tbody>			 
	  </table>
</div>
<%@include file="/admin/module/page.jsp"%>
<script>
	function href(url,params){	
 	   top.$.showModalDialog({url:url},params,window);
	}
	function details(id){		
	    var url = "${basePath}official/financial.details.do";
		var params = {entrypoint:"${entrypoint}",id:id,controller:${controller==null?"{title:'查看账单详细'}":lz:json(controller)}};
		href(url,params); 
	}

</script>
</body>

</html>