<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>企业机场管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
		
	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>
	
</head>  
<body>

<script>
	$(function(){
		$(".pager li:contains('导出')").remove();
	});	
</script>

${lz:set("canAdd",true)}

<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td>


<div class="title">企业机场管理</div>

<div class="line"></div>
<div class="content">
	<form class="query" id="queryForm" action="airport.do" method="post">
	${lz:set("haveQuery",false)}
		 
		 			 
		 	${lz:set("queryable",lz:queryable(controller,'csuaType'))}
		 	<s:if test="#request.queryable!=false">
		 	${lz:set("haveQuery",true)}
			<dl>
				<dt>类型:</dt>
				<dd>
					<input class="Not" title="不包含" ${csuaTypeNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuaTypeNot" value="csuaType"/>
		 		<select id="csuaType" name="csuaType" style="width:100px;">
		 			<option value="">全部</option>
						<option value="0" ${csuaType==0?"selected":""}>接机</option>
						<option value="1" ${csuaType==1?"selected":""}>送机</option>
		 		</select>
				</dd>
			</dl>
			</s:if>
		
		 <s:if test="#request.all==true">
		 	${lz:set("queryable",lz:queryable(controller,'csuaFlight'))}
		 	<s:if test="#request.queryable!=false">
		 	${lz:set("haveQuery",true)}
			<dl>
				<dt>航班号:</dt>
				<dd>
					<input class="Not" title="不包含" ${csuaFlightNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuaFlightNot" value="csuaFlight"/>
		 			<input type="text" class="input" size="16"  maxlength="32" name="csuaFlight" id="csuaFlight"  value="${csuaFlight}"/>
				</dd>
			</dl>
			</s:if>
		</s:if>
		 
		 	${lz:set("queryable",lz:queryable(controller,'csuaTime'))}
		 	<s:if test="#request.queryable!=false">
		 	${lz:set("haveQuery",true)}
			<dl>
				<dt>时间:</dt>
				<dd>
					<input class="Not" title="不包含" ${csuaTimeNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuaTimeNot" value="csuaTime"/>
		 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="csuaTimeStart" id="csuaTimeStart"  value="<s:date name="#request.csuaTimeStart" format="yyyy-MM-dd HH:mm:ss"/>"/>
					起</dd></dl><dl class="query-item"><dt>时间:</dt><dd>
					<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="csuaTimeEnd" id="csuaTimeEnd"  value="<s:date name="#request.csuaTimeEnd" format="yyyy-MM-dd HH:mm:ss"/>"/>
					止
				</dd>
			</dl>
			</s:if>
		
		 
		 	${lz:set("queryable",lz:queryable(controller,'csuaContact'))}
		 	<s:if test="#request.queryable!=false">
		 	${lz:set("haveQuery",true)}
			<dl>
				<dt>联系人:</dt>
				<dd>
					<input class="Not" title="不包含" ${csuaContactNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuaContactNot" value="csuaContact"/>
		 			<input type="text" class="input" size="16"  maxlength="32" name="csuaContact" id="csuaContact"  value="${csuaContact}"/>
				</dd>
			</dl>
			</s:if>
		
		 
		 	${lz:set("queryable",lz:queryable(controller,'csuaMobile'))}
		 	<s:if test="#request.queryable!=false">
		 	${lz:set("haveQuery",true)}
			<dl>
				<dt>联系电话:</dt>
				<dd>
					<input class="Not" title="不包含" ${csuaMobileNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuaMobileNot" value="csuaMobile"/>
		 			<input type="text" class="input" size="16"  maxlength="32" name="csuaMobile" id="csuaMobile"  value="${csuaMobile}"/>
				</dd>
			</dl>
			</s:if>
		
		 <s:if test="#request.all==true">
		 	${lz:set("queryable",lz:queryable(controller,'csuaUpdateTime'))}
		 	<s:if test="#request.queryable!=false">
		 	${lz:set("haveQuery",true)}
			<dl>
				<dt>修改时间:</dt>
				<dd>
					<input class="Not" title="不包含" ${csuaUpdateTimeNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuaUpdateTimeNot" value="csuaUpdateTime"/>
		 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="csuaUpdateTimeStart" id="csuaUpdateTimeStart"  value="<s:date name="#request.csuaUpdateTimeStart" format="yyyy-MM-dd HH:mm:ss"/>"/>
					起</dd></dl><dl class="query-item"><dt>修改时间:</dt><dd>
					<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="csuaUpdateTimeEnd" id="csuaUpdateTimeEnd"  value="<s:date name="#request.csuaUpdateTimeEnd" format="yyyy-MM-dd HH:mm:ss"/>"/>
					止
				</dd>
			</dl>
			</s:if>
		</s:if>
		 <s:if test="#request.all==true">
		 	${lz:set("queryable",lz:queryable(controller,'csuaAddTime'))}
		 	<s:if test="#request.queryable!=false">
		 	${lz:set("haveQuery",true)}
			<dl>
				<dt>添加时间:</dt>
				<dd>
					<input class="Not" title="不包含" ${csuaAddTimeNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuaAddTimeNot" value="csuaAddTime"/>
		 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="csuaAddTimeStart" id="csuaAddTimeStart"  value="<s:date name="#request.csuaAddTimeStart" format="yyyy-MM-dd HH:mm:ss"/>"/>
					起</dd></dl><dl class="query-item"><dt>添加时间:</dt><dd>
					<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="csuaAddTimeEnd" id="csuaAddTimeEnd"  value="<s:date name="#request.csuaAddTimeEnd" format="yyyy-MM-dd HH:mm:ss"/>"/>
					止
				</dd>
			</dl>
			</s:if>
		</s:if>
		 
		 	${lz:set("queryable",lz:queryable(controller,'csuaStatus'))}
		 	<s:if test="#request.queryable!=false">
		 	${lz:set("haveQuery",true)}
			<dl>
				<dt>状态:</dt>
				<dd>
					<input class="Not" title="不包含" ${csuaStatusNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuaStatusNot" value="csuaStatus"/>
		 		<select id="csuaStatus" name="csuaStatus" style="width:100px;">
		 			<option value="">全部</option>
						<option value="0" ${csuaStatus==0?"selected":""}>未处理</option>
						<option value="1" ${csuaStatus==1?"selected":""}>已处理</option>
						<option value="2" ${csuaStatus==2?"selected":""}>审核失败</option>
						<option value="3" ${csuaStatus==3?"selected":""}>已取消</option>
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

<s:if test="#request.haveQuery==false">
<script>
$(function(){
	$(".query").parent().prevAll().remove();
	$(".query").parent().remove();
})
</script>
</s:if>
    

	
</td></tr><tr><td>
  <%@include file="/admin/module/page.jsp"%>
  	
  			
	<div class="content">
	    <div class="toolbar">
			${lz:set("permission",lz:permission(controller,'canDel'))}
			<s:if test="#request.canDel==true && #request.permission==true">
			<button type="button" class="del" onclick="DelSel();">
				删除
			</button>
			</s:if>
			${lz:set("permission",lz:permission(controller,'canView'))}
			<s:if test="#request.canView==true && #request.permission==true">
			<!--<button type="button" class="detail" onclick="DetailsSel();">
				详情
			</button>-->
			</s:if>
			${lz:set("permission",lz:permission(controller,'canEdit'))}
			<s:if test="#request.canEdit==true && #request.permission==true">
						
			<button type="button" class="edit" onclick="UpdateSel('status')">状态
			</button>
						
			<!--<button type="button" class="edit" onclick="EditSel();">
				修改
			</button>-->
			</s:if>
		    ${lz:set("permission",lz:permission(controller,'canAdd'))}
			<s:if test="#request.canAdd==true && #request.permission==true">
			<button type="button" class="add" onclick="AddAirport();">
				添加
			</button>							
			</s:if>
			<button type="button" class="all" onclick="$('.check').attr('checked',true)">
				全选
			</button>
			<button type="button" class="invert" onclick="$('.check').click();">
				反选
			</button>
			<a href="?${all==true?"":"all=true&"}${lz:querys("UTF-8","all")}"  class="more">
				${all==true?"主要内容":"全部内容"}
			</a>		
		</div>
		
		<table id="airport_table" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td width="40" align="center">
			 	选择
			 </td>
			 
			 ${lz:set("listable",lz:listable(controller,'csuaId'))}
			 <s:if test="#request.listable!=false">
			 <td width=72 ${all?"width":"refer"}="120" title="编号">
			 	<a class="${desc=="csua_id" ? "desc" : ""}${asc=="csua_id" ? "asc" : ""}" href="?${desc=="csua_id" ? "asc=csua_id" : ""}${(asc=="csua_id" || desc!="csua_id" )? "desc=csua_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 </td>
			 </s:if>
			 			 
			 
			 ${lz:set("listable",lz:listable(controller,'csuaType'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="120" title="类型">
			 	<a class="${desc=="csua_type" ? "desc" : ""}${asc=="csua_type" ? "asc" : ""}" href="?${desc=="csua_type" ? "asc=csua_type" : ""}${(asc=="csua_type" || desc!="csua_type" )? "desc=csua_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	类型
			 	</a>
			 </td>
			 </s:if>
			 
			 <s:if test="#request.all==true">
			 ${lz:set("listable",lz:listable(controller,'csuaFlight'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="200" title="航班号">
			 	<a class="${desc=="csua_flight" ? "desc" : ""}${asc=="csua_flight" ? "asc" : ""}" href="?${desc=="csua_flight" ? "asc=csua_flight" : ""}${(asc=="csua_flight" || desc!="csua_flight" )? "desc=csua_flight" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	航班号
			 	</a>
			 </td>
			 </s:if>
			 </s:if>
			 
			 ${lz:set("listable",lz:listable(controller,'csuaTime'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="140" title="时间">
			 	<a class="${desc=="csua_time" ? "desc" : ""}${asc=="csua_time" ? "asc" : ""}" href="?${desc=="csua_time" ? "asc=csua_time" : ""}${(asc=="csua_time" || desc!="csua_time" )? "desc=csua_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	时间
			 	</a>
			 </td>
			 </s:if>
			 
			 
			 ${lz:set("listable",lz:listable(controller,'csuaContact'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="200" title="联系人">
			 	<a class="${desc=="csua_contact" ? "desc" : ""}${asc=="csua_contact" ? "asc" : ""}" href="?${desc=="csua_contact" ? "asc=csua_contact" : ""}${(asc=="csua_contact" || desc!="csua_contact" )? "desc=csua_contact" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	联系人
			 	</a>
			 </td>
			 </s:if>
			 
			 
			 ${lz:set("listable",lz:listable(controller,'csuaMobile'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="200" title="联系电话">
			 	<a class="${desc=="csua_mobile" ? "desc" : ""}${asc=="csua_mobile" ? "asc" : ""}" href="?${desc=="csua_mobile" ? "asc=csua_mobile" : ""}${(asc=="csua_mobile" || desc!="csua_mobile" )? "desc=csua_mobile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	联系电话
			 	</a>
			 </td>
			 </s:if>
			 
			 
			 ${lz:set("listable",lz:listable(controller,'csuaCount'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="120" title="人数">
			 	<a class="${desc=="csua_count" ? "desc" : ""}${asc=="csua_count" ? "asc" : ""}" href="?${desc=="csua_count" ? "asc=csua_count" : ""}${(asc=="csua_count" || desc!="csua_count" )? "desc=csua_count" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	人数
			 	</a>
			 </td>
			 </s:if>
			 
			 <s:if test="#request.all==true">
			 ${lz:set("listable",lz:listable(controller,'csuaUpdateTime'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="140" title="修改时间">
			 	<a class="${desc=="csua_update_time" ? "desc" : ""}${asc=="csua_update_time" ? "asc" : ""}" href="?${desc=="csua_update_time" ? "asc=csua_update_time" : ""}${(asc=="csua_update_time" || desc!="csua_update_time" )? "desc=csua_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 </td>
			 </s:if>
			 </s:if>
			 <s:if test="#request.all==true">
			 ${lz:set("listable",lz:listable(controller,'csuaAddTime'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="140" title="添加时间">
			 	<a class="${desc=="csua_add_time" ? "desc" : ""}${asc=="csua_add_time" ? "asc" : ""}" href="?${desc=="csua_add_time" ? "asc=csua_add_time" : ""}${(asc=="csua_add_time" || desc!="csua_add_time" )? "desc=csua_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 </td>
			 </s:if>
			 </s:if>
			 <s:if test="#request.all==true">
			 ${lz:set("listable",lz:listable(controller,'csuaRemark'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="200" title="备注">
			 	<a class="${desc=="csua_remark" ? "desc" : ""}${asc=="csua_remark" ? "asc" : ""}" href="?${desc=="csua_remark" ? "asc=csua_remark" : ""}${(asc=="csua_remark" || desc!="csua_remark" )? "desc=csua_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备注
			 	</a>
			 </td>
			 </s:if>
			 </s:if>
			 
			 ${lz:set("listable",lz:listable(controller,'csuaStatus'))}
			 <s:if test="#request.listable!=false">
			 <td  ${all?"width":"refer"}="120" title="状态">
			 	<a class="${desc=="csua_status" ? "desc" : ""}${asc=="csua_status" ? "asc" : ""}" href="?${desc=="csua_status" ? "asc=csua_status" : ""}${(asc=="csua_status" || desc!="csua_status" )? "desc=csua_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 </td>
			 </s:if>
			 
			<td width="130">&nbsp;
				${lz:set("permission",lz:permission(controller,'canAdd'))}
				<s:if test="#request.canAdd==true && #request.permission==true">
					<a href="javascript:AddAirport()" style="text-decoration:underline">添加</a>
				</s:if>
			</td>
		  </tr>
		 </thead>	
		 <tbody>
		 	<s:iterator value="#request.page.result" id="item" status="i">
		  <tr id="${item.csuaId}" >
		  	<td align="center">
				<input class="check" type="checkbox" value="${item.csuaId}"/>
			</td>
			
			${lz:set("listable",lz:listable(controller,'csuaId'))}
			<s:if test="#request.listable!=false">
			<td ref="csuaId" class=" node">
					 ${lz:zerofill(item.csuaId,6)}
			</td>
			</s:if>
						
			
			${lz:set("listable",lz:listable(controller,'csuaType'))}
			<s:if test="#request.listable!=false">
			<td ref="csuaType" class="">
					 ${item.csuaType==0?"接机":""}
					 ${item.csuaType==1?"送机":""}
			</td>
			</s:if>
			
			<s:if test="#request.all==true">
			${lz:set("listable",lz:listable(controller,'csuaFlight'))}
			<s:if test="#request.listable!=false">
			<td ref="csuaFlight" class="">
					 ${item.csuaFlight}
			</td>
			</s:if>
			</s:if>
			
			${lz:set("listable",lz:listable(controller,'csuaTime'))}
			<s:if test="#request.listable!=false">
			<td ref="csuaTime" class="">
				 	 <s:date name="#item.csuaTime" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
			</s:if>
			
			
			${lz:set("listable",lz:listable(controller,'csuaContact'))}
			<s:if test="#request.listable!=false">
			<td ref="csuaContact" class="">
					 ${item.csuaContact}
			</td>
			</s:if>
			
			
			${lz:set("listable",lz:listable(controller,'csuaMobile'))}
			<s:if test="#request.listable!=false">
			<td ref="csuaMobile" class="">
					 ${item.csuaMobile}
			</td>
			</s:if>
			
			
			${lz:set("listable",lz:listable(controller,'csuaCount'))}
			<s:if test="#request.listable!=false">
			<td ref="csuaCount" class="">
				 	 ${item.csuaCount}
			</td>
			</s:if>
			
			<s:if test="#request.all==true">
			${lz:set("listable",lz:listable(controller,'csuaUpdateTime'))}
			<s:if test="#request.listable!=false">
			<td ref="csuaUpdateTime" class="">
				 	 <s:date name="#item.csuaUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
			</s:if>
			</s:if>
			<s:if test="#request.all==true">
			${lz:set("listable",lz:listable(controller,'csuaAddTime'))}
			<s:if test="#request.listable!=false">
			<td ref="csuaAddTime" class="">
				 	 <s:date name="#item.csuaAddTime" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
			</s:if>
			</s:if>
			<s:if test="#request.all==true">
			${lz:set("listable",lz:listable(controller,'csuaRemark'))}
			<s:if test="#request.listable!=false">
			<td ref="csuaRemark" class="">
					 ${item.csuaRemark}
			</td>
			</s:if>
			</s:if>
			
			${lz:set("listable",lz:listable(controller,'csuaStatus'))}
			<s:if test="#request.listable!=false">
			<td ref="csuaStatus" class="">
				 	${lz:set("permission",lz:permission(controller,'canEdit'))}
				 <s:if test="#request.canEdit==true && #request.permission==true">
				 	<a class="modify" href="javascript:Update('${item.csuaId}','status')"></a>
				 </s:if>
					 ${item.csuaStatus==0?"未处理":""}
					 ${item.csuaStatus==1?"已处理":""}
					 ${item.csuaStatus==2?"审核失败":""}
					 ${item.csuaStatus==3?"已取消":""}
			</td>
			</s:if>
			
			<td>
				<s:if test="#item.csuaStatus==0">
					<a href="javascript:cancelAirport('${item.csuaId}')">取消</a>
				</s:if>
				${lz:set("permission",lz:permission(controller,'canEdit'))}
				<s:if test="#request.canEdit==true && #request.permission==true">
				<a href="javascript:EditAirport('${item.csuaId}')">修改</a></s:if>
				${lz:set("permission",lz:permission(controller,'canDel'))}
				<s:if test="#request.canDel==true && #request.permission==true">
				<a href="javascript:DelAirport('${item.csuaId}','${item.csuaId}')">删除</a></s:if>
				${lz:set("permission",lz:permission(controller,'canView'))}
				<s:if test="#request.canView==true && #request.permission==true">
				<a href="javascript:DetailsAirport('${item.csuaId}')">详情</a></s:if>
			</td>
		  </tr>
		 </s:iterator>
		 </tbody>			 
	  </table>
	  
  </div>
  <%@include file="/admin/module/page.jsp"%>
  
 </td></tr></table>

<script>
	
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
	
	function cancelAirport(id){
		if(confirm("确定要取消该接送机服务吗？")){
			$.submit("airport.save.do",{
				"csUnitAirport.csuaStatus":3,
				"csUnitAirport.csuaId":id,
				"entrypoint":"${entrypoint}",
				controller:{
					fields:{
						csuaStatus:{visible:true,editable:true}
					}
				}
			});
		}
	}
	
${lz:set("permission",lz:permission(controller,'canAdd'))}
<s:if test="#request.canAdd==true && #request.permission==true">
	/**
	* 添加企业机场
	**/
	function AddAirport(parent){		
		var url = "${basePath}unit/airport.edit.do";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),controller:${controller==null?"{title:'添加企业机场'}":lz:json(controller)}};
		href(url,params);
	}
</s:if>
${lz:set("permission",lz:permission(controller,'canEdit'))}
<s:if test="#request.canEdit==true && #request.permission==true">
	/**
	* 修改企业机场
	**/
	function EditAirport(id){
		var url = "${basePath}unit/airport.edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,controller:${controller==null?"{title:'修改企业机场'}":lz:json(controller)}};
		href(url,params);
	}
	/*
	* 修改所选
	*/
	function EditSel(){
		var checker=$(".check:checked");
		if(checker.length!=1)
		{
			$.tips("请选择一个需要修改的记录.");
			return;
		}
		EditAirport(checker.val());
	}
	
	/**
	* 批量修改
	**/
	function UpdateSel(flag)
	{
		var checker=$(".check:checked");
		if(checker.length==0){
			$.tips("您未选中任何记录，请至少选择一项.");
			return;
		}
		var ids = "";
		checker.each(function(i){
			if(ids!="")
				ids+=",";
			ids+=$(this).val();
		});
		var url = "${basePath}unit/airport.edit.do";
		var params = {entrypoint:"${entrypoint}",ids:ids,controller:{title:"批量更新企业机场",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.controller["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	/**
	* 修改企业机场
	**/
	function Update(id,flag)
	{
		var url = "${basePath}unit/airport.edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,controller:{title:"更新企业机场",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.controller["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["status"]={};
	fieldNames["status"]["csuaStatus"]=true;

</s:if>
${lz:set("permission",lz:permission(controller,'canDel'))}
<s:if test="#request.canDel==true && #request.permission==true">
	/**
	* 删除企业机场
	**/
	function DelAirport(id,flag)
	{
		var dialog=$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除企业机场["+flag+"]吗？</span>",
				title:"删除企业机场",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}unit/airport.del.do";
					var params = {entrypoint:"${entrypoint}",id:id};
					$.submit(url,params);
				}
		 });	
	}
	/*
	* 删除所选记录详情
	*/
	function DelSel(){
		var checker=$(".check:checked");
		if(checker.length==0)
		{
			$.tips("您未选中任何记录，请至少选择一项.");
			return;
		}
		var dialog=$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除所选企业机场吗？</span>",
				title:"批量删除企业机场",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}unit/airport.del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示企业机场详情
	**/
	function DetailsAirport(id)
	{
		var url = "${basePath}unit/airport.details.do";
		var params = {entrypoint:"${entrypoint}",id:id,controller:{title:"企业机场详情",editable:false,visible:true}};
		href(url,params);
	}
	/*
	* 显示所选记录详情
	*/
	function DetailsSel(){
		var checker=$(".check:checked");
		if(checker.length!=1)
		{
			$.tips("请选择一个需要显示详情的记录.");
			return;
		}
		DetailsAirport(checker.val());
	}
</script>    

</body>
</html>