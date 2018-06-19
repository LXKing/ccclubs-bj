<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>企业订单管理</title>
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
  
  <form ref="CsUnitOrder" class="query" id="queryForm" action="${basePath}${servletPath}" method="post">
		${lz:set("haveQuery",false)}
	
	 	
	<div class="title">
	<a href="javascript:void(0);" onclick="parent.current.close();" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/Badge-multiply.png"/>
		关闭页面
	</a>
	<a href="javascript:void(0);" class="linker imize ${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize"))?"minimize":"maximize"}">
		<img align="absmiddle" class="up" src="${basePath}admin/images/icons/Arrow up.png"/>
		<img align="absmiddle" class="down" src="${basePath}admin/images/icons/Arrow down.png"/>
		展开收起
	</a>
	<a href="javascript:void(0);" class="linker setting">
		<img align="absmiddle" src="${basePath}admin/images/icons/20088256421529677801.png"/>
		页面设置
	</a>
	<a href="?${lz:querys("UTF-8","xxxxx")}" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/2008825642250778028.png"/>
		重新加载
	</a>
	<s:if test="#request.CTRL.canExport==true">
	<a href="javascript:void(0);" onclick="exportXls();" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/20088256427828778018.png"/>
		数据导出
	</a>
	</s:if>
	<s:if test="#request.alias==null">
		<s:if test="#request.CTRL.canStats==true">
	<a href="order_stats.do?${lz:querys("UTF-8","limitsid")}" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/Statistics.png"/>
		数据统计
	</a>
		</s:if>
	</s:if>
	<s:if test="#request.shortcuts!=null || #request.haveQuerys==true">
		<a class="toggler" href="javascript:void(0);"><span class="${window.shortcuts!="hide"?"on":""}"></span></a>
	</s:if>
	<span class="caption">${empty title?"订单管理":title}</span>
	
</div>	
			 <!-- ----------------查询相关字段---------------- -->		
			 	${lz:set("haveQuery",true)}
				<dl>
					<dt>系统订单：</dt>
					<dd>

						<a href="javascript:void(0);" title="不包含" class="checkbox ${csuoOrderNot=="not"?"checked":""}" for="csuoOrderNot"></a>
						<input class="Not" title="不包含" ${csuoOrderNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuoOrderNot" value="csuoOrder"/>
			 			<input class="input" size="16" type="text" id="csuoOrder" name="csuoOrder" value="${csuoOrder}"/>
								
					 
					</dd>
				</dl>						

			
			
			<!-- ----------------查询相关字段---------------- -->
			 	
			 			 	
			 	
			 <!-- ----------------查询相关字段---------------- -->
	
			 	${lz:set("haveQuery",true)}
				<dl>
					<dt>使用帐号：</dt>
					<dd>
						<a href="javascript:void(0);" title="不包含" class="checkbox ${csuoUnitChildNot=="not"?"checked":""}" for="csuoUnitChildNot"></a>
						<input class="Not" title="不包含" ${csuoUnitChildNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuoUnitChildNot" value="csuoUnitChild"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/person/person_inquire.do?value={param}" size="16" type="text" id="csuoUnitChild" name="csuoUnitChild" text="${get:CsMember(csuoUnitChild).csmName}" value="${csuoUnitChild}"/>
					</dd>
				</dl>						
			
			
			<!-- ----------------查询相关字段---------------- -->
			 	
			 	
			 <!-- ----------------查询相关字段---------------- -->
				
			 	${lz:set("haveQuery",true)}
				<dl>
					<dt>订单车辆：</dt>
					<dd>
					
						<a href="javascript:void(0);" title="不包含" class="checkbox ${csuoCarNot=="not"?"checked":""}" for="csuoCarNot"></a>
						<input class="Not" title="不包含" ${csuoCarNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuoCarNot" value="csuoCar"/>
			 			<input title="请输入车辆车牌号进行查询" class="combox" action="${basePath}${proname}/object/car_query.do?value={param}" size="16" type="text" id="csuoCar" name="csuoCar" text="${get:CsCar(csuoCar).cscNumber}" value="${csuoCar}"/>
															
					 
					</dd>
				</dl>						
			
			
			<!-- ----------------查询相关字段---------------- -->			 	
			 	
			 	
			 <!-- ----------------查询相关字段---------------- -->
			 
			 	${lz:set("haveQuery",true)}
				<dl>
					<dt>使用人姓名：</dt>
					<dd>
					  	
						<a href="javascript:void(0);" title="不包含" class="checkbox ${csuoUsernameNot=="not"?"checked":""}" for="csuoUsernameNot"></a>
						<input class="Not" title="不包含" ${csuoUsernameNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuoUsernameNot" value="csuoUsername"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="csuoUsername" id="csuoUsername"  value="${csuoUsername}"/>
															
					 
					</dd>
				</dl>						
			
			
			<!-- ----------------查询相关字段---------------- -->
			 	
			 	
			 <!-- ----------------查询相关字段---------------- -->
			 
				<dl>
					<dt>使用人手机：</dt>
					<dd>
					  	
						<a href="javascript:void(0);" title="不包含" class="checkbox ${csuoMobileNot=="not"?"checked":""}" for="csuoMobileNot"></a>
						<input class="Not" title="不包含" ${csuoMobileNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuoMobileNot" value="csuoMobile"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="csuoMobile" id="csuoMobile"  value="${csuoMobile}"/>
				 
					</dd>
				</dl>						
			
			
			<!-- ----------------查询相关字段---------------- -->
			 	
			 	
			 <!-- ----------------查询相关字段---------------- -->
			 
				<dl>
					<dt>订单开始时间：</dt>
					<dd>
					  	
						<a href="javascript:void(0);" title="不包含" class="checkbox ${csuoStartTimeNot=="not"?"checked":""}" for="csuoStartTimeNot"></a>
						<input class="Not" title="不包含" ${csuoStartTimeNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuoStartTimeNot" value="csuoStartTime"/>
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="csuoStartTimeStart" id="csuoStartTimeStart"  value="<s:date name="#request.csuoStartTimeStart" format="yyyy-MM-dd HH:mm:ss"/>"/>
						起</dd></dl><dl class="query-item"><dt>订单开始时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="csuoStartTimeEnd" id="csuoStartTimeEnd"  value="<s:date name="#request.csuoStartTimeEnd" format="yyyy-MM-dd HH:mm:ss"/>"/>
						止
															
					 
					</dd>
				</dl>						
			
			
			<!-- ----------------查询相关字段---------------- -->
			 	
			 	
			 <!-- ----------------查询相关字段---------------- -->
			 
				<dl>
					<dt>订单结束时间：</dt>
					<dd>
					  	
						<a href="javascript:void(0);" title="不包含" class="checkbox ${csuoFinishTimeNot=="not"?"checked":""}" for="csuoFinishTimeNot"></a>
						<input class="Not" title="不包含" ${csuoFinishTimeNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuoFinishTimeNot" value="csuoFinishTime"/>
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="csuoFinishTimeStart" id="csuoFinishTimeStart"  value="<s:date name="#request.csuoFinishTimeStart" format="yyyy-MM-dd HH:mm:ss"/>"/>
						起</dd></dl><dl class="query-item"><dt>订单结束时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="csuoFinishTimeEnd" id="csuoFinishTimeEnd"  value="<s:date name="#request.csuoFinishTimeEnd" format="yyyy-MM-dd HH:mm:ss"/>"/>
						止
															
					 
					</dd>
				</dl>						
			
			
			<!-- ----------------查询相关字段---------------- -->
			 	
			 	
			 <!-- ----------------查询相关字段---------------- -->

				<dl>
					<dt>实际还车时间：</dt>
					<dd>
					  	
						<a href="javascript:void(0);" title="不包含" class="checkbox ${csuoRetTimeNot=="not"?"checked":""}" for="csuoRetTimeNot"></a>
						<input class="Not" title="不包含" ${csuoRetTimeNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuoRetTimeNot" value="csuoRetTime"/>
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="csuoRetTimeStart" id="csuoRetTimeStart"  value="<s:date name="#request.csuoRetTimeStart" format="yyyy-MM-dd HH:mm:ss"/>"/>
						起</dd></dl><dl class="query-item"><dt>实际还车时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="csuoRetTimeEnd" id="csuoRetTimeEnd"  value="<s:date name="#request.csuoRetTimeEnd" format="yyyy-MM-dd HH:mm:ss"/>"/>
						止
															
					 
					</dd>
				</dl>															 			
			
			
			
			<!-- ----------------查询相关字段---------------- -->
			 
				<dl>
					<dt>审核状态：</dt>
					<dd>
					  	
						<a href="javascript:void(0);" title="不包含" class="checkbox ${csuoStateNot=="not"?"checked":""}" for="csuoStateNot"></a>
						<input class="Not" title="不包含" ${csuoStateNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuoStateNot" value="csuoState"/>
			 		<select id="csuoState" name="csuoState" >
			 			<option value="">全部</option>
							<option value="0" ${csuoState==0?"selected":""}>无需审核</option>
							<option value="1" ${csuoState==1?"selected":""}>等待审核</option>
							<option value="2" ${csuoState==2?"selected":""}>审核已通过</option>
							<option value="3" ${csuoState==3?"selected":""}>审核不通过</option>
			 		</select>
															
					 
					</dd>
				</dl>	
			
			<!-- ----------------查询相关字段---------------- -->


		<div class="line"></div>
		<div class="option">
			<table border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <td>
					<input type="hidden" name="all" value="${all}" />
					<input type="hidden" name="fields" value="${fields}" />
					<button class="button" type="submit">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Zoom.png"/>
						查询&nbsp;&nbsp;</button>		
					<button class="button" onclick="clearForm()" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778045.png"/>
						重置&nbsp;&nbsp;</button>
					<s:if test="#request.alias=='join'">
					<button class="button onok" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
						确定&nbsp;&nbsp;</button>
					<button class="button oncancel" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-multiply.png"/>
						放弃&nbsp;&nbsp;</button>
					</s:if>
				</td>
			  </tr>
			</table>
			<div class="clear"></div>
		</div>
		
	</form>
	
	
	<!-- -------------------------页面展示区域-------------------------------   -->

    <div class="content">
	    <div class="toolbar">
			<button type="button" class="add" onclick="HandleSel('公务用车');" style="display:inline-block;margin-right:15px;">公务用车</button>
			
			<button type="button" class="all" onclick="$('.check').attr('checked',true)">
				全选
			</button>
			<button type="button" class="invert" onclick="$('.check').click();">
				反选
			</button>
		</div>
		<table id="order_table" ref="CsUnitOrder" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td width="40" tdid="0" align="center">
			 	选择
			 </td>
						 			 
			 
			 <td width=72 ${all?"width=120":""} tdid="1" ref="csuoId"   title="编号">
			 	<a class="${desc=="csuo_id" ? "desc" : ""}${asc=="csuo_id" ? "asc" : ""}" href="?${desc=="csuo_id" ? "asc=csuo_id" : ""}${(asc=="csuo_id" || desc!="csuo_id" )? "desc=csuo_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 </td>
			

			 <td  ${all?"width=120":""} tdid="3" ref="csuoOrder" flagKey  title="系统订单">
			 	<a class="${desc=="csuo_order" ? "desc" : ""}${asc=="csuo_order" ? "asc" : ""}" href="?${desc=="csuo_order" ? "asc=csuo_order" : ""}${(asc=="csuo_order" || desc!="csuo_order" )? "desc=csuo_order" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	系统订单
			 	</a>
			 </td>
		 
			 <td  ${all?"width=120":""} tdid="6" ref="csuoUnitChild"   title="使用帐号">
			 	<a class="${desc=="csuo_unit_child" ? "desc" : ""}${asc=="csuo_unit_child" ? "asc" : ""}" href="?${desc=="csuo_unit_child" ? "asc=csuo_unit_child" : ""}${(asc=="csuo_unit_child" || desc!="csuo_unit_child" )? "desc=csuo_unit_child" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	使用帐号
			 	</a>
			 </td>
						 
			 <td  ${all?"width=120":""} tdid="7" ref="csuoCar"   title="订单车辆">
			 	<a class="${desc=="csuo_car" ? "desc" : ""}${asc=="csuo_car" ? "asc" : ""}" href="?${desc=="csuo_car" ? "asc=csuo_car" : ""}${(asc=="csuo_car" || desc!="csuo_car" )? "desc=csuo_car" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单车辆
			 	</a>
			 </td>


			 <td  ${all?"width=120":""} tdid="8" ref="csuoType"   title="订单类型">
			 	<a class="${desc=="csuo_type" ? "desc" : ""}${asc=="csuo_type" ? "asc" : ""}" href="?${desc=="csuo_type" ? "asc=csuo_type" : ""}${(asc=="csuo_type" || desc!="csuo_type" )? "desc=csuo_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单类型
			 	</a>
			 </td>
						 
			 <td  ${all?"width=200":""} tdid="9" ref="csuoUsername"   title="使用人姓名">
			 	<a class="${desc=="csuo_username" ? "desc" : ""}${asc=="csuo_username" ? "asc" : ""}" href="?${desc=="csuo_username" ? "asc=csuo_username" : ""}${(asc=="csuo_username" || desc!="csuo_username" )? "desc=csuo_username" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	使用人
			 	</a>
			 </td>						 
						 
			 <td  ${all?"width=200":""} tdid="11" ref="csuoMobile"   title="使用人手机">
			 	<a class="${desc=="csuo_mobile" ? "desc" : ""}${asc=="csuo_mobile" ? "asc" : ""}" href="?${desc=="csuo_mobile" ? "asc=csuo_mobile" : ""}${(asc=="csuo_mobile" || desc!="csuo_mobile" )? "desc=csuo_mobile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	使用人手机
			 	</a>
			 </td>		
			 			 
			 <td  ${all?"width=200":""} tdid="11" ref="csuoUnitGroup"   title="所属部门">
			 	<a class="${desc=="csuo_unit_group" ? "desc" : ""}${asc=="csuo_unit_group" ? "asc" : ""}" href="?${desc=="csuo_unit_group" ? "asc=csuo_unit_group" : ""}${(asc=="csuo_unit_group" || desc!="csuo_unit_group" )? "desc=csuo_unit_group" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属部门
			 	</a>
			 </td>				 						 
						 
			 <td  ${all?"width=140":""} tdid="16" ref="csuoStartTime"   title="订单开始时间">
			 	<a class="${desc=="csuo_start_time" ? "desc" : ""}${asc=="csuo_start_time" ? "asc" : ""}" href="?${desc=="csuo_start_time" ? "asc=csuo_start_time" : ""}${(asc=="csuo_start_time" || desc!="csuo_start_time" )? "desc=csuo_start_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单开始时间
			 	</a>
			 </td>
						 
			 <td  ${all?"width=140":""} tdid="17" ref="csuoFinishTime"   title="订单结束时间">
			 	<a class="${desc=="csuo_finish_time" ? "desc" : ""}${asc=="csuo_finish_time" ? "asc" : ""}" href="?${desc=="csuo_finish_time" ? "asc=csuo_finish_time" : ""}${(asc=="csuo_finish_time" || desc!="csuo_finish_time" )? "desc=csuo_finish_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单结束时间
			 	</a>
			 </td>						 

			 <td  ${all?"width=120":""} tdid="25" ref="csuoPayReal"   title="实付金额(元)">
			 	<a class="${desc=="csuo_pay_real" ? "desc" : ""}${asc=="csuo_pay_real" ? "asc" : ""}" href="?${desc=="csuo_pay_real" ? "asc=csuo_pay_real" : ""}${(asc=="csuo_pay_real" || desc!="csuo_pay_real" )? "desc=csuo_pay_real" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	实付金额(元)
			 	</a>
			 </td>
						 
			 <td  ${all?"width=200":""} tdid="26" ref="csuoRemark"   title="订单备注">
			 	<a class="${desc=="csuo_remark" ? "desc" : ""}${asc=="csuo_remark" ? "asc" : ""}" href="?${desc=="csuo_remark" ? "asc=csuo_remark" : ""}${(asc=="csuo_remark" || desc!="csuo_remark" )? "desc=csuo_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单备注
			 	</a>
			 </td>
			 
			 <td  ${all?"width=120":""} tdid="27" ref="csuo_state"   title="审核状态">
			 	<a class="${desc=="csuo_state" ? "desc" : ""}${asc=="csuo_state" ? "asc" : ""}" href="?${desc=="csuo_state" ? "asc=csuo_state" : ""}${(asc=="csuo_state" || desc!="csuo_state" )? "desc=csuo_state" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	审核状态
			 	</a>
			 </td>
						 
			 <td  ${all?"width=120":""} tdid="27" ref="csoStatus"   title="状态">
			 	<a class="${desc=="cso_status" ? "desc" : ""}${asc=="cso_status" ? "asc" : ""}" href="?${desc=="cso_status" ? "asc=cso_status" : ""}${(asc=="cso_status" || desc!="cso_status" )? "desc=cso_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单状态
			 	</a>
			 </td>				 
		
			  <td width="60" tdid="28" class="options" ref="options">操作</td>
		  </tr>
		 </thead>	
		 <tbody>
		 	<s:iterator value="#request.page.result" id="item" status="i">
		  <tr id="${item.csuoId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csuoId}"/>
			</td>
		 
			<td ref="csuoId" class="">				 
				 ${item.csuoId$}
			</td>
			 	
			<td ref="csuoOrder" class="" relate="${item.csuoOrder}">
				 ${item.csuoOrder$}
			</td>

			<td ref="csuoUnitChild" class="" relate="${item.csuoUnitChild}">
				 ${item.csuoUnitChild$}
			</td>
			 	
			<td ref="csuoCar" class="" relate="${item.csuoCar}">
				 ${item.csuoCar$}
			</td>
			 	
			<td ref="csuoType" class="">
				 
				 ${item.csuoType$}
			</td>
			 	
			<td ref="csuoUsername" class="">
				 
				 ${item.csuoUsername$}
			</td>			
			 	
			<td ref="csuoMobile" class="">
				 
				 ${item.csuoMobile$}
			</td>
			
			<td ref="csuoUnitGroup" class="">
				 
				 ${item.csuoUnitGroup$}
			</td>			 				 	
			 	
			<td ref="csuoStartTime" class="">
				 
				 ${item.csuoStartTime$}
			</td>
			 	
			<td ref="csuoFinishTime" class="">
				 
				 ${item.csuoFinishTime$}
			</td>		 			 				
			 	
			<td ref="csuoPayReal" class="">
				 
				 ${item.$csuoOrder.csoPayReal$}
			</td>

			<td ref="csuoRemark" class="">
				 
				 ${item.csuoRemark$}
			</td>
			
			<td ref="csuoState" class="">
				 
				 ${item.csuoState$}
			</td>

			<td ref="csoStatus" class="">
				 
				 ${item.$csuoOrder.csoStatus$}
			</td>	
		 
			<td class="options">
				<a href="javascript:ReOrder('审核','${item.csuoId}')">审核</a>
				<a href="javascript:ReOrder('续订','${item.csuoId}')">续订</a>
				<a href="javascript:ReOrder('取消','${item.csuoId}')">取消</a>				
				<!-- <a href="javascript:Details('结算','${item.csuoId}')">结算</a> -->				
				<a href="javascript:Details('详情','${item.csuoId}')">详情</a>
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
	/*
	* 自定义操作
	*/
	function HandleSel(operate,id){
			var url = "${basePath}${namespace}order.add.do";
			var params = {entrypoint:"${entrypoint}",id:id,controller:{title:operate}};
			href(url,params);
	}
	
		function ReOrder(operate,id){
		/* if(window.getHandle && window.getHandle(operate,id)!=null)//本窗口的操作函数获取句柄
			window.getHandle(operate)(operate,id);
		else if(window.handles && window.handles[operate])//本窗口的操作函数句柄
			window.handles[operate](operate,id);
		else if(typeof(caller)!="undefined" && caller.handles && caller.handles[operate])//调用窗口的操作函数句柄
			caller.handles[operate](operate,id);
		else if(parent && parent.handles && parent.handles[operate])//父窗口的操作函数句柄
			parent.handles[operate](operate,id);
		else{ */
			var url = "${basePath}${namespace}order.reorder.do";
			var params = {entrypoint:"${entrypoint}",id:id,controller:{title:operate}};
			href(url,params);
		//}
	}
	
	function HandleSel(operate,id){
			var url = "${basePath}${namespace}order.add.do";
			var params = {entrypoint:"${entrypoint}",id:id,controller:{title:operate}};
			href(url,params);
	}
	
	function Details(operate,id){
			var url = "${basePath}${namespace}order.details.do";
			var params = {entrypoint:"${entrypoint}",id:id,controller:{title:operate}};
			href(url,params);
	}
</script>

</body>
</html>
