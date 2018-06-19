<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
		${lz:set("proname","unit")}
		window.basePath="${basePath}";
		window.contextPath="${basePath}${proname}/";
		window.apiUrl="${basePath}${proname}/api.do";
		window.handles={};
	</script>
		
	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>
	
	<!------------------------LAZY3Q_JSP_HEAD------------------------>
	<!------------------------LAZY3Q_JSP_HEAD------------------------>	
	
</head>  
<!------------------------LAZY3Q_JSP_BODY------------------------>
<script>
$(function(){
	$(".pager li:contains('导出')").remove();
	$(".table thead td[title='应付金额']").attr("width",85);
	$(".table thead td[title='实付金额']").attr("width",85);
	$(".table thead td[title='开始时间']").attr("width",140);
	$(".table thead td[title='结束时间']").attr("width",140);
	$(".table thead td[title='状态']").attr("width",120);
	$(".table tbody td[ref='csoStatus']:contains('已下单')").css("color","#ff0000");
	$(".table tbody td[ref='csoStatus']:contains('已完成')").css("color","#0000ff");
	$(".table tbody td[ref='csoStatus']:contains('已取消')").css("color","#FF00ff");	
});
</script>
<!------------------------LAZY3Q_JSP_BODY------------------------>

<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td>
<div class="title">
	<a href="javascript:void(0);" class="setting imize"></a>
	<a ref=".plate" href="javascript:void(0);" class="maximize imize"></a>
	<a ref=".plate" href="javascript:void(0);" class="minimize imize"></a>
	${empty title?"企业订单管理":title}	
</div>
<div class="plate" style="${hideQuery?"display:none;":""}">
	<form ref="CsUnitOrder" class="query" id="queryForm" action="${basePath}${servletPath}" method="post">
				<dl>
					<dt>订单号：</dt>
					<dd>
						<a href="javascript:void(0);" title="不包含" class="checkbox ${csuoOrderNot=="not"?"checked":""}" for="csuoOrderNot"></a>
						<input class="Not" title="不包含" ${csuoOrderNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuoOrderNot" value="csuoOrder"/>
			 			<input class="input" size="16" type="text" id="csuoOrder" name="csuoOrder" value="${csuoOrder}"/>
					</dd>
				</dl>
			
			
				<dl>
					<dt>使用帐号：</dt>
					<dd>
						<a href="javascript:void(0);" title="不包含" class="checkbox ${csuoUnitChildNot=="not"?"checked":""}" for="csuoUnitChildNot"></a>
						<input class="Not" title="不包含" ${csuoUnitChildNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuoUnitChildNot" value="csuoUnitChild"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_inquire.do?value={param}" size="16" type="text" id="csuoUnitChild" name="csuoUnitChild" text="${get:CsMember(csuoUnitChild).csmName}" value="${csuoUnitChild}"/>
					</dd>
				</dl>						
			
				<dl>
					<dt>订单车辆：</dt>
					<dd>
						<a href="javascript:void(0);" title="不包含" class="checkbox ${csuoNumberNot=="not"?"checked":""}" for="csuoNumberNot"></a>
						<input class="Not" title="不包含" ${csuoNumberNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuoNumberNot" value="csuoNumber"/>
			 			<input class="input" size="16" type="text" id="csuoNumber" name="csuoNumber" value="${csuoNumber}"/>
					</dd>
				</dl>						
				
				<s:if test="#session.unit_login.$csuuUnitInfo.csuiType!=0">
				<dl>
					<dt>订单类型：</dt>
					<dd>
						<a href="javascript:void(0);" title="不包含" class="checkbox ${csuoTypeNot=="not"?"checked":""}" for="csuoTypeNot"></a>
						<input class="Not" title="不包含" ${csuoTypeNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuoTypeNot" value="csuoType"/>
				 		<select id="csuoType" name="csuoType" >
				 			<option value="">全部</option>
				 			<s:if test="#session.unit_login.$csuuUnitInfo.csuiType==1">
							<option value="0" ${csuoType==0?"selected":""}>代理订单</option>
							</s:if>
							<option value="1" ${csuoType==1?"selected":""}>${unit_login.$csuuUnitInfo.csuiType<2?"内部用车":"公务用车"}</option>
							<s:if test="#session.unit_login.$csuuUnitInfo.csuiType==2">
							<option value="2" ${csuoType==2?"selected":""}>紧急公务</option>
							</s:if>
				 		</select>
					</dd>
				</dl>
				</s:if>
				
				<dl>
					<dt>使用人姓名：</dt>
					<dd>
					  	
						<a href="javascript:void(0);" title="不包含" class="checkbox ${csuoUsernameNot=="not"?"checked":""}" for="csuoUsernameNot"></a>
						<input class="Not" title="不包含" ${csuoUsernameNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuoUsernameNot" value="csuoUsername"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="csuoUsername" id="csuoUsername"  value="${csuoUsername}"/>
															
					 
					</dd>
				</dl>						
			
				<dl>
					<dt>使用人手机：</dt>
					<dd>
						<a href="javascript:void(0);" title="不包含" class="checkbox ${csuoMobileNot=="not"?"checked":""}" for="csuoMobileNot"></a>
						<input class="Not" title="不包含" ${csuoMobileNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuoMobileNot" value="csuoMobile"/>
			 			<input type="text" class="input" size="16"  maxlength="32" name="csuoMobile" id="csuoMobile"  value="${csuoMobile}"/>
					</dd>
				</dl>						
				
				<dl>
					<dt>订单开始时间：</dt>
					<dd>
						<input class="YesNot" type="hidden" name="csuoStartTimeYesNot" id="csuoStartTimeYesNot" value="${csuoStartTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="csuoStartTime" name="csuoStartTimeExp" id="csuoStartTimeExp" start="${lz:date(csuoStartTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(csuoStartTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${csuoStartTimeExp}">
					</dd>
				</dl>						
				
				
				<dl>
					<dt>订单结束时间：</dt>
					<dd>
						<input class="YesNot" type="hidden" name="csuoFinishTimeYesNot" id="csuoFinishTimeYesNot" value="${csuoFinishTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="csuoFinishTime" name="csuoFinishTimeExp" id="csuoFinishTimeExp" start="${lz:date(csuoFinishTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(csuoFinishTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${csuoFinishTimeExp}">
					</dd>
				</dl>			


				<dl>
					<dt>实际还车时间：</dt>
					<dd>
						<input class="YesNot" type="hidden" name="csuoRetTimeYesNot" id="csuoRetTimeYesNot" value="${csuoRetTimeYesNot}"/>
			 			<input type="datetime" class="input" ref="csuoRetTime" name="csuoRetTimeExp" id="csuoRetTimeExp" start="${lz:date(csuoRetTimeStart,"yyyy-MM-dd HH:mm:ss")}" end="${lz:date(csuoRetTimeEnd,"yyyy-MM-dd HH:mm:ss")}" exp="${csuoRetTimeExp}">
					</dd>
				</dl>						
				
				<dl>
					<dt>订单备注：</dt>
					<dd>
						<a href="javascript:void(0);" title="不包含" class="checkbox ${csuoRemarkNot=="not"?"checked":""}" for="csuoRemarkNot"></a>
						<input class="Not" title="不包含" ${csuoRemarkNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuoRemarkNot" value="csuoRemark"/>
			 			<input type="text" class="input" size="16"  maxlength="256" name="csuoRemark" id="csuoRemark"  value="${csuoRemark}"/>
					</dd>
				</dl>		
				
				<s:if test="#session.unit_login.$csuuUnitInfo.csuiType==2">
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
				</s:if>				
				
				
				<dl>
					<dt>状态：</dt>
					<dd>
						<a href="javascript:void(0);" title="不包含" class="checkbox ${csuoStatusNot=="not"?"checked":""}" for="csuoStatusNot"></a>
						<input class="Not" title="不包含" ${csuoStatusNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csuoStatusNot" value="csuoStatus"/>
				 		<select id="csuoStatus" name="csuoStatus" >
				 			<option value="">全部</option>
								<option value="0" ${csuoStatus==0?"selected":""}>已下单</option>
								<option value="1" ${csuoStatus==1?"selected":""}>已完成</option>
								<option value="2" ${csuoStatus==2?"selected":""}>已取消</option>
				 		</select>
					</dd>
				</dl>	
				
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
</div>


	
</td></tr><tr><td>
	<%@include file="/admin/module/page.jsp"%>
  			
	<div class="content">
	    <div class="toolbar">
			<button type="button" class="add" onclick="HandleSel('公务用车');" style="display:inline-block;margin-right:15px;">${unit_login.$csuuUnitInfo.csuiType<2?"内部用车":"公务用车"}</button>
			<s:if test="#session.unit_login.$csuuUnitInfo.csuiType==true">
			<button type="button" class="add" onclick="HandleSel('代理订单');" style="display:inline-block;">代理订单</button>
			</s:if>
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
		  	
			 <td  ${all?"width=120":""} tdid="3" ref="csuoOrder" flagKey  title="系统订单">
			 	<a class="${desc=="csuo_order" ? "desc" : ""}${asc=="csuo_order" ? "asc" : ""}" href="?${desc=="csuo_order" ? "asc=csuo_order" : ""}${(asc=="csuo_order" || desc!="csuo_order" )? "desc=csuo_order" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单号
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
			 	使用人姓名
			 	</a>
			 </td>
			 
			 <td  ${all?"width=200":""} tdid="11" ref="csuoMobile"   title="使用人手机">
			 	<a class="${desc=="csuo_mobile" ? "desc" : ""}${asc=="csuo_mobile" ? "asc" : ""}" href="?${desc=="csuo_mobile" ? "asc=csuo_mobile" : ""}${(asc=="csuo_mobile" || desc!="csuo_mobile" )? "desc=csuo_mobile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	使用人手机
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
			 
			 <td  ${all?"width=140":""} tdid="18" ref="csuoRetTime"   title="实际还车时间">
			 	<a class="${desc=="csuo_ret_time" ? "desc" : ""}${asc=="csuo_ret_time" ? "asc" : ""}" href="?${desc=="csuo_ret_time" ? "asc=csuo_ret_time" : ""}${(asc=="csuo_ret_time" || desc!="csuo_ret_time" )? "desc=csuo_ret_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	实际还车时间
			 	</a>
			 </td>
			 
			 <td width="72" title="授权码">
			 	授权码
			  </td>
			  
			  <s:if test="#session.unit_login.$csuuUnitInfo.csuiType==2">
			  <td  ${all?"width=140":""} tdid="18" ref="csuoState"   title="审核状态">
			 	<a class="${desc=="csuo_state" ? "desc" : ""}${asc=="csuo_state" ? "asc" : ""}" href="?${desc=="csuo_state" ? "asc=csuo_state" : ""}${(asc=="csuo_state" || desc!="csuo_state" )? "desc=csuo_state" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	审核状态
			 	</a>
			 </td>
			  </s:if>
			
			 <td  ${all?"width=120":""} tdid="27" ref="csuoStatus"   title="订单状态">
			 	<a class="${desc=="csuo_status" ? "desc" : ""}${asc=="csuo_status" ? "asc" : ""}" href="?${desc=="csuo_status" ? "asc=csuo_status" : ""}${(asc=="csuo_status" || desc!="csuo_status" )? "desc=csuo_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单状态
			 	</a>
			 </td>
			 
			  <td width="60" tdid="28" class="options" ref="options">操作</td>
		  </tr>
		 </thead>	
		 <tbody>
		 	<s:iterator value="#request.page.result" id="item" status="i">
		  <tr id="${item.csuoId}" >
		  	
			<td ref="csuoOrder" class=" node" relate="${item.csuoOrder}">
				<s:if test="#item.csuoOrder>0">
			 	[系统]${item.csuoOrder}
			 	</s:if>
			 	<s:if test="#item.csuoSpecial>0">
			 	[专车]${item.csuoSpecial}
			 	</s:if>
			</td>
			
			<td ref="csuoUnitChild" class="" relate="${item.csuoUnitChild}">
				 ${item.csuoUnitChild$}
			</td>
			
			<td ref="csuoCar" class="" relate="${item.csuoCar}">
				${item.csuoNumber$}
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
			
			<td ref="csuoStartTime" class="">
				 
				 ${item.csuoStartTime$}
			</td>
			
			<td ref="csuoFinishTime" class="">
				 
				 ${item.csuoFinishTime$}
			</td>
			
			<td ref="csuoRetTime" class="">
				 
				 ${lz:or(item.csuoRetTime$,"&lt;未还车&gt;")}
			</td>
			
			<td>
				${item.$csuoOrder.csoCode$}
			</td>
			
			<s:if test="#session.unit_login.$csuuUnitInfo.csuiType==2">
			<td ref="csuoState" class="">
				 ${item.csuoState$}
			</td>
			</s:if>
			
			<td ref="csuoStatus" class="">
				 ${item.$csuoOrder.csoStatus$}${item.$csuoSpecial.csscStatus$}
			</td>
			
			<td class="options">
				<s:if test="#item.csuoOrder>0 && #item.$csuoOrder.csoStatus<2">
					<a href="javascript:HandleSel('续订','${item.csuoId}')">续订</a>
				</s:if>
				<s:if test="#item.csuoOrder>0 && #item.$csuoOrder.csoStatus==0">
					<a href="javascript:HandleSel('取消','${item.csuoId}')">取消</a>
				</s:if>
				<s:if test="#item.csuoOrder>0 && #item.$csuoOrder.csoStatus==1">
					<a href="javascript:HandleSel('还车','${item.csuoId}')">还车</a>
				</s:if>
				<s:if test="#item.csuoType==0 && #item.csuoStatus==0">
					<a href="javascript:HandleSel('结算','${item.csuoId}')">结算</a>
				</s:if>
				<s:if test="#session.unit_login.$csuuUnitInfo.csuiType==2">
					<a href="javascript:HandleSel('审核','${item.csuoId}')">审核</a>
				</s:if>
				<a href="javascript:HandleSel('详情','${item.csuoId}')">详情</a>
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
		
		if(window.getHandle && window.getHandle(operate,id)!=null)//本窗口的操作函数获取句柄
			window.getHandle(operate)(operate,id);
		else if(window.handles && window.handles[operate])//本窗口的操作函数句柄
			window.handles[operate](operate,id);
		else if(typeof(caller)!="undefined" && caller.handles && caller.handles[operate])//调用窗口的操作函数句柄
			caller.handles[operate](operate,id);
		else if(parent && parent.handles && parent.handles[operate])//父窗口的操作函数句柄
			parent.handles[operate](operate,id);
		else{
			var url = "${basePath}${namespace}order.edit.do";
			var params = {entrypoint:"${entrypoint}",id:id,controller:{title:operate}};
			href(url,params);
		}
	}
</script>


<!------------------------LAZY3Q_JSP_BOTTOM------------------------>
<!------------------------LAZY3Q_JSP_BOTTOM------------------------>


</body>
</html>