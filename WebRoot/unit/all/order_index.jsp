<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>订单管理</title>
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
	${lz:set("namespace","unit/all/")}
	<lz:set name="jsonString">
		{
			fields:{
				csoHost:{listable:false,queryable:false,visible:false}
				,csoFeeType:{listable:false,queryable:false,visible:false}
				,csoProvid:{listable:false,queryable:false,visible:false}
				,csoCreditCard:{listable:false,queryable:false,visible:false}
				,csoFlag:{listable:false,queryable:false,visible:false}
				,csoEditor:{listable:false,queryable:false,visible:false}
				,csoMask:{listable:false,queryable:false,visible:false}
				,csoDeviceVesion:{listable:false,queryable:false,visible:false}
				,csoState:{listable:false,queryable:false,visible:false}
			}
		}
	</lz:set>
	${lz:set("controller",lz:eval(jsonString))}
	${lz:set("proname","unit")}
	<!------------------------LAZY3Q_JSP_HEAD------------------------>
	
</head>  
<body>



<!--LAZY3Q_JSP_BODY-->
<script>
$(function(){
	$(".table thead td[title='订单编号']").attr("width",100);
	$(".table thead td[title='预订车辆']").attr("width",85);
	$(".table thead td[title='订单开始时间']").attr("width",120);
	$(".table thead td[title='订单结束时间']").attr("width",120);
	$(".table tbody td[ref='csoStatus']:contains('已预定')").css("color","#ff0000");
	$(".table tbody td[ref='csoStatus']:contains('正在执行')").css("color","#0000ff");
	$(".table tbody td[ref='csoStatus']:contains('待处理')").css("color","#FF00ff");
	var states = $(".table tbody td[ref='csoState']:contains('已发送')");
	states.each(function(){
		$(this).css("color","#ff0000");
		var strOrderStart = $(this).siblings("td[ref='csoStartTime']").text();
		var orderStart = $.dateFormat($.trim(strOrderStart),"yyyy-MM-dd HH:mm:ss");
		if(orderStart.getTime()<new Date().getTime()){ 
			$(this).css("color","#0000ff").css("font-weight","bold").css("font-size","14px");
			$(this).html($.trim($(this).text())+"！！");
		}
	});
	
	var states = $(".table tbody td[ref='csoState']:contains('已发送')");
	states.each(function(){
		$(this).css("color","#ff0000");
		var strOrderStart = $(this).siblings("td[ref='csoStartTime']").text();
		var orderStart = $.dateFormat($.trim(strOrderStart),"yyyy-MM-dd HH:mm:ss");
		if(orderStart.getTime()<new Date().getTime()){ 
			$(this).css("color","#0000ff").css("font-weight","bold").css("font-size","14px");
			$(this).html($.trim($(this).text())+"！！");
		}
	});	
});
</script>
<!--LAZY3Q_JSP_BODY-->

<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td>
	${lz:set("permission",lz:permission(controller,'canQuery'))}
	<s:if test="#request.canQuery==true && #request.permission==true">
<div class="title">
	<a href="javascript:void(0);" class="setting imize"></a>
	<a ref=".plate" href="javascript:void(0);" class="maximize imize"></a>
	<a ref=".plate" href="javascript:void(0);" class="minimize imize"></a>
	${empty title?"订单管理":title}	
</div>
  	</s:if>
${lz:set("permission",lz:permission(controller,'canQuery'))}
<s:if test="#request.canQuery==true && #request.permission==true">
<div class="plate" style="${hideQuery?"display:none;":""}">
	<form ref="CsOrder" class="query" id="queryForm" action="${basePath}${servletPath}" method="post">
		${lz:set("haveQuery",false)}
			 	
			 	
			 <!-- ----------------查询相关字段---------------- -->
			 ${lz:set("canQueryable",lz:queryableEx(controller,'csoId',(defines.csoId==true)||(defines==null && true||(true && all))))}
			 <s:if test="#request.canQueryable!=false">
			 	${lz:set("haveQuery",true)}
				<dl>
					<dt>订单编号：</dt>
					<dd>
					  	
						<a href="javascript:void(0);" title="不包含" class="checkbox ${csoIdNot=="not"?"checked":""}" for="csoIdNot"></a>
						<input class="Not" title="不包含" ${csoIdNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csoIdNot" value="csoId"/>
			  	    <input onkeyup="this.value=this.value.replace(/[^\d]/g,'')" type="text" class="input"  maxlength="12" size="16" name="csoId" id="csoId"  value="${csoId}"/>
															
					 
					</dd>
				</dl>						
				
			</s:if>
			
			
			<!-- ----------------查询相关字段---------------- -->
			 	
			 	
			 	
			 <!-- ----------------查询相关字段---------------- -->
			 ${lz:set("canQueryable",lz:queryableEx(controller,'csoUseMember',(defines.csoUseMember==true)||(defines==null && true||(true && all))))}
			 <s:if test="#request.canQueryable!=false">
			 	${lz:set("haveQuery",true)}
				<dl>
					<dt>使用会员：</dt>
					<dd>

						<a href="javascript:void(0);" title="不包含" class="checkbox ${csoUseMemberNot=="not"?"checked":""}" for="csoUseMemberNot"></a>
						<input class="Not" title="不包含" ${csoUseMemberNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csoUseMemberNot" value="csoUseMember"/>
			 			<input title="请输入会员帐号真实姓名进行查询" class="combox" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csoHost}" size="16" type="text" id="csoUseMember" name="csoUseMember" text="${get:CsMember(csoUseMember).csmName}" value="${csoUseMember}"/>
			 			
					</dd>
				</dl>						
				
			</s:if>
			
			
			<!-- ----------------查询相关字段---------------- -->
			 	
			 	
			 	
			 	
			 <!-- ----------------查询相关字段---------------- -->
			 ${lz:set("canQueryable",lz:queryableEx(controller,'csoOutlets',(defines.csoOutlets==true)||(defines==null && true||(true && all))))}
			 <s:if test="#request.canQueryable!=false">
			 	${lz:set("haveQuery",true)}
				<dl>
					<dt>取车网点：</dt>
					<dd>

						<a href="javascript:void(0);" title="不包含" class="checkbox ${csoOutletsNot=="not"?"checked":""}" for="csoOutletsNot"></a>
						<input class="Not" title="不包含" ${csoOutletsNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csoOutletsNot" value="csoOutlets"/>
			 			<select class="combox"  action="${basePath}${proname}/object/outlets_tree.do?parent={param}&csoHost={csoHost}" id="csoOutlets" name="csoOutlets">
				 				<option selected value="${csoOutlets}">
				 					${get:CsOutlets(csoOutlets).csoName}
				 				</option>
				 		</select>
				 		
					</dd>
				</dl>						
				
			</s:if>
			
			
			<!-- ----------------查询相关字段---------------- -->
			 	
			 	
			 <!-- ----------------查询相关字段---------------- -->
			 ${lz:set("canQueryable",lz:queryableEx(controller,'csoOutletsRet',(defines.csoOutletsRet==true)||(defines==null && false||(true && all))))}
			 <s:if test="#request.canQueryable!=false">
			 	${lz:set("haveQuery",true)}
				<dl>
					<dt>还车网点：</dt>
					<dd>

						<a href="javascript:void(0);" title="不包含" class="checkbox ${csoOutletsRetNot=="not"?"checked":""}" for="csoOutletsRetNot"></a>
						<input class="Not" title="不包含" ${csoOutletsRetNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csoOutletsRetNot" value="csoOutletsRet"/>
			 			<select class="combox"  action="${basePath}${proname}/object/outlets_tree.do?parent={param}&csoHost={csoHost}" id="csoOutletsRet" name="csoOutletsRet">
				 				<option selected value="${csoOutletsRet}">
				 					${get:CsOutlets(csoOutletsRet).csoName}
				 				</option>
				 		</select>
				 		
					</dd>
				</dl>						
				
			</s:if>
			
			
			<!-- ----------------查询相关字段---------------- -->
			 	
			 	
			 <!-- ----------------查询相关字段---------------- -->
			 ${lz:set("canQueryable",lz:queryableEx(controller,'csoModel',(defines.csoModel==true)||(defines==null && false||(true && all))))}
			 <s:if test="#request.canQueryable!=false">
			 	${lz:set("haveQuery",true)}
				<dl>
					<dt>预订车型：</dt>
					<dd>

						<a href="javascript:void(0);" title="不包含" class="checkbox ${csoModelNot=="not"?"checked":""}" for="csoModelNot"></a>
						<input class="Not" title="不包含" ${csoModelNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csoModelNot" value="csoModel"/>
			 			<select class="combox"  action="${basePath}${proname}/object/carmodel_query.do?size=-1" id="csoModel" name="csoModel">
				 				<option selected value="${csoModel}">
				 					${get:CsCarModel(csoModel).cscmName}
				 				</option>
				 		</select>
				 		
					</dd>
				</dl>						
				
			</s:if>
			
			
			<!-- ----------------查询相关字段---------------- -->
			 	
			 	
			 <!-- ----------------查询相关字段---------------- -->
			 ${lz:set("canQueryable",lz:queryableEx(controller,'csoCar',(defines.csoCar==true)||(defines==null && true||(true && all))))}
			 <s:if test="#request.canQueryable!=false">
			 	${lz:set("haveQuery",true)}
				<dl>
					<dt>预订车辆：</dt>
					<dd>

						<a href="javascript:void(0);" title="不包含" class="checkbox ${csoCarNot=="not"?"checked":""}" for="csoCarNot"></a>
						<input class="Not" title="不包含" ${csoCarNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csoCarNot" value="csoCar"/>
			 			<input title="请输入车辆车牌号进行查询" class="combox" action="${basePath}${proname}/object/car_query.do?value={param}&cscOutlets={csoOutlets}&cscModel={csoModel}&cscHost={csoHost}" size="16" type="text" id="csoCar" name="csoCar" text="${get:CsCar(csoCar).cscNumber}" value="${csoCar}"/>
			 			
					</dd>
				</dl>						
				
			</s:if>
			
			
			<!-- ----------------查询相关字段---------------- -->
			 	
			 	
			 	
			 <!-- ----------------查询相关字段---------------- -->
			 ${lz:set("canQueryable",lz:queryableEx(controller,'csoInsureType',(defines.csoInsureType==true)||(defines==null && false||(true && all))))}
			 <s:if test="#request.canQueryable!=false">
			 	${lz:set("haveQuery",true)}
				<dl>
					<dt>保险类型：</dt>
					<dd>

						<a href="javascript:void(0);" title="不包含" class="checkbox ${csoInsureTypeNot=="not"?"checked":""}" for="csoInsureTypeNot"></a>
						<input class="Not" title="不包含" ${csoInsureTypeNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csoInsureTypeNot" value="csoInsureType"/>
			 		<select id="csoInsureType" name="csoInsureType" >
			 			<option value="">全部</option>
							    ${lz:set("items", lz:query("ccclubs_system","select * from cs_product where csp_flag='INSURE' or csp_flag='SECURE'") )}
								<s:iterator value="#request.items" id="item" status="i">
								<option value="${item.cspId}" ${csoInsureType==item.cspId?"selected":""}>${item.cspName}</option>
								</s:iterator>
								${lz:set("items",null)}
			 		</select>
			 		
					</dd>
				</dl>						
				
			</s:if>
			
			
			<!-- ----------------查询相关字段---------------- -->
			 	
			 	
			 <!-- ----------------查询相关字段---------------- -->
			 ${lz:set("canQueryable",lz:queryableEx(controller,'csoStartTime',(defines.csoStartTime==true)||(defines==null && true||(true && all))))}
			 <s:if test="#request.canQueryable!=false">
			 	${lz:set("haveQuery",true)}
				<dl>
					<dt>订单开始时间：</dt>
					<dd>
					  	
						<a href="javascript:void(0);" title="不包含" class="checkbox ${csoStartTimeNot=="not"?"checked":""}" for="csoStartTimeNot"></a>
						<input class="Not" title="不包含" ${csoStartTimeNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csoStartTimeNot" value="csoStartTime"/>
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="csoStartTimeStart" id="csoStartTimeStart"  value="<s:date name="#request.csoStartTimeStart" format="yyyy-MM-dd HH:mm:ss"/>"/>
						起</dd></dl><dl class="query-item"><dt>订单开始时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="csoStartTimeEnd" id="csoStartTimeEnd"  value="<s:date name="#request.csoStartTimeEnd" format="yyyy-MM-dd HH:mm:ss"/>"/>
						止
															
					 
					</dd>
				</dl>						
				
			</s:if>
			
			
			<!-- ----------------查询相关字段---------------- -->
			 	
			 	
			 <!-- ----------------查询相关字段---------------- -->
			 ${lz:set("canQueryable",lz:queryableEx(controller,'csoFinishTime',(defines.csoFinishTime==true)||(defines==null && true||(true && all))))}
			 <s:if test="#request.canQueryable!=false">
			 	${lz:set("haveQuery",true)}
				<dl>
					<dt>订单结束时间：</dt>
					<dd>
					  	
						<a href="javascript:void(0);" title="不包含" class="checkbox ${csoFinishTimeNot=="not"?"checked":""}" for="csoFinishTimeNot"></a>
						<input class="Not" title="不包含" ${csoFinishTimeNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csoFinishTimeNot" value="csoFinishTime"/>
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="csoFinishTimeStart" id="csoFinishTimeStart"  value="<s:date name="#request.csoFinishTimeStart" format="yyyy-MM-dd HH:mm:ss"/>"/>
						起</dd></dl><dl class="query-item"><dt>订单结束时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="csoFinishTimeEnd" id="csoFinishTimeEnd"  value="<s:date name="#request.csoFinishTimeEnd" format="yyyy-MM-dd HH:mm:ss"/>"/>
						止
															
					 
					</dd>
				</dl>						
				
			</s:if>
			
			
			<!-- ----------------查询相关字段---------------- -->
			 	
			 	
			 	
			 	
			 <!-- ----------------查询相关字段---------------- -->
			 ${lz:set("canQueryable",lz:queryableEx(controller,'csoRetTime',(defines.csoRetTime==true)||(defines==null && false||(true && all))))}
			 <s:if test="#request.canQueryable!=false">
			 	${lz:set("haveQuery",true)}
				<dl>
					<dt>实际还车时间：</dt>
					<dd>
					  	
						<a href="javascript:void(0);" title="不包含" class="checkbox ${csoRetTimeNot=="not"?"checked":""}" for="csoRetTimeNot"></a>
						<input class="Not" title="不包含" ${csoRetTimeNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csoRetTimeNot" value="csoRetTime"/>
			 			<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="csoRetTimeStart" id="csoRetTimeStart"  value="<s:date name="#request.csoRetTimeStart" format="yyyy-MM-dd HH:mm:ss"/>"/>
						起</dd></dl><dl class="query-item"><dt>实际还车时间：</dt><dd>
						<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input" size="14" maxlength="19" name="csoRetTimeEnd" id="csoRetTimeEnd"  value="<s:date name="#request.csoRetTimeEnd" format="yyyy-MM-dd HH:mm:ss"/>"/>
						止
															
					 
					</dd>
				</dl>						
				
			</s:if>
			
			
			<!-- ----------------查询相关字段---------------- -->
			 	
			 	
			 	
			 <!-- ----------------查询相关字段---------------- -->
			 ${lz:set("canQueryable",lz:queryableEx(controller,'csoStatus',(defines.csoStatus==true)||(defines==null && true||(true && all))))}
			 <s:if test="#request.canQueryable!=false">
			 	${lz:set("haveQuery",true)}
				<dl>
					<dt>状态：</dt>
					<dd>
					  	
						<a href="javascript:void(0);" title="不包含" class="checkbox ${csoStatusNot=="not"?"checked":""}" for="csoStatusNot"></a>
						<input class="Not" title="不包含" ${csoStatusNot=="not"?"checked='checked'":""} type="checkbox" name="Not" id="csoStatusNot" value="csoStatus"/>
			 		<select id="csoStatus" name="csoStatus" >
			 			<option value="">全部</option>
							<option value="0" ${csoStatus==0?"selected":""}>已预定</option>
							<option value="1" ${csoStatus==1?"selected":""}>正在执行</option>
							<option value="2" ${csoStatus==2?"selected":""}>已还车</option>
							<option value="3" ${csoStatus==3?"selected":""}>已取消</option>
							<option value="4" ${csoStatus==4?"selected":""}>已完成</option>
							<option value="5" ${csoStatus==5?"selected":""}>待处理</option>
							<option value="6" ${csoStatus==6?"selected":""}>已退款</option>
							<option value="7" ${csoStatus==7?"selected":""}>已撤销</option>
			 		</select>
															
					 
					</dd>
				</dl>						
				
			</s:if>
			
			
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
</div>
</s:if>

<s:if test="#request.haveQuery==false">
<script>
$(function(){
	$(".query").parent().prevAll().remove();
	$(".query").parent().remove();
})
</script>
</s:if>
    

	
</td></tr><tr><td>
	${lz:set("permission",lz:permission(controller,'canQuery'))}
	<s:if test="#request.canQuery==true && #request.permission==true">
  <%@include file="/admin/module/page.jsp"%>
  	</s:if>
  	
  			
	<div class="content">
	    <div class="toolbar">
	    	<lz:set name="">
		    ${lz:set("permission",lz:permission(controller,'canAdd'))}
			<s:if test="#request.canAdd==true && #request.permission==true">
			<button type="button" class="add" onclick="AddOrder();">
				添加
			</button>							
			</s:if>
			</lz:set>
			<button type="button" class="all" onclick="$('.check').attr('checked',true)">
				全选
			</button>
			<button type="button" class="invert" onclick="$('.check').click();">
				反选
			</button>
			<dl ref="com.ccclubs.model.CsOrder" class="display">
				<dt>
					${all==null?"主要内容":""}
					${all=="true"?"全部内容":""}
				</dt>
				<dd>
					<a all="" href="?all=&${lz:queryss("UTF-8","all","fields")}">主要内容</a>
					<a all="true" href="?all=true&${lz:queryss("UTF-8","all","fields")}">全部内容</a>
				</dd>
			</dl>
		</div>
		<table id="order_table" ref="CsOrder" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td width="40" tdid="0" align="center">
			 	选择
			 </td>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoId',(true||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td width=72 ${all?"width=120":""} tdid="1" ref="csoId" flagKey  title="订单编号">
			 	<a class="${desc=="cso_id" ? "desc" : ""}${asc=="cso_id" ? "asc" : ""}" href="?${desc=="cso_id" ? "asc=cso_id" : ""}${(asc=="cso_id" || desc!="cso_id" )? "desc=cso_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单编号
			 	</a>
			 </td>
			 </s:if>
				 
			 ${lz:set("canListable",lz:listableEx(controller,'csoUseMember',(true||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="4" ref="csoUseMember"   title="使用会员">
			 	<a class="${desc=="cso_use_member" ? "desc" : ""}${asc=="cso_use_member" ? "asc" : ""}" href="?${desc=="cso_use_member" ? "asc=cso_use_member" : ""}${(asc=="cso_use_member" || desc!="cso_use_member" )? "desc=cso_use_member" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	使用会员
			 	</a>
			 </td>
			 </s:if>
				
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoOutlets',(true||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="8" ref="csoOutlets"   title="取车网点">
			 	<a class="${desc=="cso_outlets" ? "desc" : ""}${asc=="cso_outlets" ? "asc" : ""}" href="?${desc=="cso_outlets" ? "asc=cso_outlets" : ""}${(asc=="cso_outlets" || desc!="cso_outlets" )? "desc=cso_outlets" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	取车网点
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoOutletsRet',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="9" ref="csoOutletsRet"   title="还车网点">
			 	<a class="${desc=="cso_outlets_ret" ? "desc" : ""}${asc=="cso_outlets_ret" ? "asc" : ""}" href="?${desc=="cso_outlets_ret" ? "asc=cso_outlets_ret" : ""}${(asc=="cso_outlets_ret" || desc!="cso_outlets_ret" )? "desc=cso_outlets_ret" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	还车网点
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoModel',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="10" ref="csoModel"   title="预订车型">
			 	<a class="${desc=="cso_model" ? "desc" : ""}${asc=="cso_model" ? "asc" : ""}" href="?${desc=="cso_model" ? "asc=cso_model" : ""}${(asc=="cso_model" || desc!="cso_model" )? "desc=cso_model" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	预订车型
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoCar',(true||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="11" ref="csoCar"   title="预订车辆">
			 	<a class="${desc=="cso_car" ? "desc" : ""}${asc=="cso_car" ? "asc" : ""}" href="?${desc=="cso_car" ? "asc=cso_car" : ""}${(asc=="cso_car" || desc!="cso_car" )? "desc=cso_car" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	预订车辆
			 	</a>
			 </td>
			 </s:if>
					 
			 ${lz:set("canListable",lz:listableEx(controller,'csoCarNumber',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=200":""} tdid="13" ref="csoCarNumber"   title="车牌号码">
			 	<a class="${desc=="cso_car_number" ? "desc" : ""}${asc=="cso_car_number" ? "asc" : ""}" href="?${desc=="cso_car_number" ? "asc=cso_car_number" : ""}${(asc=="cso_car_number" || desc!="cso_car_number" )? "desc=cso_car_number" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车牌号码
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoMobile',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=200":""} tdid="14" ref="csoMobile"   title="手机号码">
			 	<a class="${desc=="cso_mobile" ? "desc" : ""}${asc=="cso_mobile" ? "asc" : ""}" href="?${desc=="cso_mobile" ? "asc=cso_mobile" : ""}${(asc=="cso_mobile" || desc!="cso_mobile" )? "desc=cso_mobile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	手机号码
			 	</a>
			 </td>
			 </s:if>
					
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoInsureType',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="16" ref="csoInsureType"   title="保险类型">
			 	<a class="${desc=="cso_insure_type" ? "desc" : ""}${asc=="cso_insure_type" ? "asc" : ""}" href="?${desc=="cso_insure_type" ? "asc=cso_insure_type" : ""}${(asc=="cso_insure_type" || desc!="cso_insure_type" )? "desc=cso_insure_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	保险类型
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoIsAdCar',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="17" ref="csoIsAdCar"   title="宣传车？">
			 	<a class="${desc=="cso_is_ad_car" ? "desc" : ""}${asc=="cso_is_ad_car" ? "asc" : ""}" href="?${desc=="cso_is_ad_car" ? "asc=cso_is_ad_car" : ""}${(asc=="cso_is_ad_car" || desc!="cso_is_ad_car" )? "desc=cso_is_ad_car" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	宣传车？
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoRebate',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="18" ref="csoRebate"   title="折扣">
			 	<a class="${desc=="cso_rebate" ? "desc" : ""}${asc=="cso_rebate" ? "asc" : ""}" href="?${desc=="cso_rebate" ? "asc=cso_rebate" : ""}${(asc=="cso_rebate" || desc!="cso_rebate" )? "desc=cso_rebate" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	折扣
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoFreehour',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="19" ref="csoFreehour"   title="免费小时">
			 	<a class="${desc=="cso_freehour" ? "desc" : ""}${asc=="cso_freehour" ? "asc" : ""}" href="?${desc=="cso_freehour" ? "asc=cso_freehour" : ""}${(asc=="cso_freehour" || desc!="cso_freehour" )? "desc=cso_freehour" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	免费小时
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoStartTime',(true||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=140":""} tdid="20" ref="csoStartTime"   title="订单开始时间">
			 	<a class="${desc=="cso_start_time" ? "desc" : ""}${asc=="cso_start_time" ? "asc" : ""}" href="?${desc=="cso_start_time" ? "asc=cso_start_time" : ""}${(asc=="cso_start_time" || desc!="cso_start_time" )? "desc=cso_start_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单开始时间
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoFinishTime',(true||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=140":""} tdid="21" ref="csoFinishTime"   title="订单结束时间">
			 	<a class="${desc=="cso_finish_time" ? "desc" : ""}${asc=="cso_finish_time" ? "asc" : ""}" href="?${desc=="cso_finish_time" ? "asc=cso_finish_time" : ""}${(asc=="cso_finish_time" || desc!="cso_finish_time" )? "desc=cso_finish_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单结束时间
			 	</a>
			 </td>
			 </s:if>
					
			 ${lz:set("canListable",lz:listableEx(controller,'csoTakeTime',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=140":""} tdid="24" ref="csoTakeTime"   title="实际取车时间">
			 	<a class="${desc=="cso_take_time" ? "desc" : ""}${asc=="cso_take_time" ? "asc" : ""}" href="?${desc=="cso_take_time" ? "asc=cso_take_time" : ""}${(asc=="cso_take_time" || desc!="cso_take_time" )? "desc=cso_take_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	实际取车时间
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoRetTime',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=140":""} tdid="25" ref="csoRetTime"   title="实际还车时间">
			 	<a class="${desc=="cso_ret_time" ? "desc" : ""}${asc=="cso_ret_time" ? "asc" : ""}" href="?${desc=="cso_ret_time" ? "asc=cso_ret_time" : ""}${(asc=="cso_ret_time" || desc!="cso_ret_time" )? "desc=cso_ret_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	实际还车时间
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoFuel',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="26" ref="csoFuel"   title="燃油里程">
			 	<a class="${desc=="cso_fuel" ? "desc" : ""}${asc=="cso_fuel" ? "asc" : ""}" href="?${desc=="cso_fuel" ? "asc=cso_fuel" : ""}${(asc=="cso_fuel" || desc!="cso_fuel" )? "desc=cso_fuel" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	燃油里程
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoElectric',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="27" ref="csoElectric"   title="电里程">
			 	<a class="${desc=="cso_electric" ? "desc" : ""}${asc=="cso_electric" ? "asc" : ""}" href="?${desc=="cso_electric" ? "asc=cso_electric" : ""}${(asc=="cso_electric" || desc!="cso_electric" )? "desc=cso_electric" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	电里程
			 	</a>
			 </td>
			 </s:if>
					
			 ${lz:set("canListable",lz:listableEx(controller,'csoMarginNeed',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="29" ref="csoMarginNeed"   title="保证金额">
			 	<a class="${desc=="cso_margin_need" ? "desc" : ""}${asc=="cso_margin_need" ? "asc" : ""}" href="?${desc=="cso_margin_need" ? "asc=cso_margin_need" : ""}${(asc=="cso_margin_need" || desc!="cso_margin_need" )? "desc=cso_margin_need" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	保证金额
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoPredict',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="30" ref="csoPredict"   title="预计/里程费">
			 	<a class="${desc=="cso_predict" ? "desc" : ""}${asc=="cso_predict" ? "asc" : ""}" href="?${desc=="cso_predict" ? "asc=cso_predict" : ""}${(asc=="cso_predict" || desc!="cso_predict" )? "desc=cso_predict" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	预计/里程费
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoPayNeed',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="31" ref="csoPayNeed"   title="应付金额">
			 	<a class="${desc=="cso_pay_need" ? "desc" : ""}${asc=="cso_pay_need" ? "asc" : ""}" href="?${desc=="cso_pay_need" ? "asc=cso_pay_need" : ""}${(asc=="cso_pay_need" || desc!="cso_pay_need" )? "desc=cso_pay_need" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	应付金额
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoPayReal',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="32" ref="csoPayReal"   title="实付金额">
			 	<a class="${desc=="cso_pay_real" ? "desc" : ""}${asc=="cso_pay_real" ? "asc" : ""}" href="?${desc=="cso_pay_real" ? "asc=cso_pay_real" : ""}${(asc=="cso_pay_real" || desc!="cso_pay_real" )? "desc=cso_pay_real" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	实付金额
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoPayMoney',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="33" ref="csoPayMoney"   title="保证金支付">
			 	<a class="${desc=="cso_pay_money" ? "desc" : ""}${asc=="cso_pay_money" ? "asc" : ""}" href="?${desc=="cso_pay_money" ? "asc=cso_pay_money" : ""}${(asc=="cso_pay_money" || desc!="cso_pay_money" )? "desc=cso_pay_money" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	保证金支付
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoPayCoupon',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="34" ref="csoPayCoupon"   title="现金券支付">
			 	<a class="${desc=="cso_pay_coupon" ? "desc" : ""}${asc=="cso_pay_coupon" ? "asc" : ""}" href="?${desc=="cso_pay_coupon" ? "asc=cso_pay_coupon" : ""}${(asc=="cso_pay_coupon" || desc!="cso_pay_coupon" )? "desc=cso_pay_coupon" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	现金券支付
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoPayKilom',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="35" ref="csoPayKilom"   title="电里程费">
			 	<a class="${desc=="cso_pay_kilom" ? "desc" : ""}${asc=="cso_pay_kilom" ? "asc" : ""}" href="?${desc=="cso_pay_kilom" ? "asc=cso_pay_kilom" : ""}${(asc=="cso_pay_kilom" || desc!="cso_pay_kilom" )? "desc=cso_pay_kilom" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	电里程费
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoPayMileage',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="36" ref="csoPayMileage"   title="油里程费">
			 	<a class="${desc=="cso_pay_mileage" ? "desc" : ""}${asc=="cso_pay_mileage" ? "asc" : ""}" href="?${desc=="cso_pay_mileage" ? "asc=cso_pay_mileage" : ""}${(asc=="cso_pay_mileage" || desc!="cso_pay_mileage" )? "desc=cso_pay_mileage" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	油里程费
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoPayRent',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="37" ref="csoPayRent"   title="租金费用">
			 	<a class="${desc=="cso_pay_rent" ? "desc" : ""}${asc=="cso_pay_rent" ? "asc" : ""}" href="?${desc=="cso_pay_rent" ? "asc=cso_pay_rent" : ""}${(asc=="cso_pay_rent" || desc!="cso_pay_rent" )? "desc=cso_pay_rent" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	租金费用
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoPayInsure',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="38" ref="csoPayInsure"   title="免责费用">
			 	<a class="${desc=="cso_pay_insure" ? "desc" : ""}${asc=="cso_pay_insure" ? "asc" : ""}" href="?${desc=="cso_pay_insure" ? "asc=cso_pay_insure" : ""}${(asc=="cso_pay_insure" || desc!="cso_pay_insure" )? "desc=cso_pay_insure" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	免责费用
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoPayTimeout',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="39" ref="csoPayTimeout"   title="超时费用">
			 	<a class="${desc=="cso_pay_timeout" ? "desc" : ""}${asc=="cso_pay_timeout" ? "asc" : ""}" href="?${desc=="cso_pay_timeout" ? "asc=cso_pay_timeout" : ""}${(asc=="cso_pay_timeout" || desc!="cso_pay_timeout" )? "desc=cso_pay_timeout" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	超时费用
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoPayDriver',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="40" ref="csoPayDriver"   title="陪驾费用">
			 	<a class="${desc=="cso_pay_driver" ? "desc" : ""}${asc=="cso_pay_driver" ? "asc" : ""}" href="?${desc=="cso_pay_driver" ? "asc=cso_pay_driver" : ""}${(asc=="cso_pay_driver" || desc!="cso_pay_driver" )? "desc=cso_pay_driver" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	陪驾费用
			 	</a>
			 </td>
			 </s:if>
					
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoEndTime',(false||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=140":""} tdid="45" ref="csoEndTime"   title="结束时间">
			 	<a class="${desc=="cso_end_time" ? "desc" : ""}${asc=="cso_end_time" ? "asc" : ""}" href="?${desc=="cso_end_time" ? "asc=cso_end_time" : ""}${(asc=="cso_end_time" || desc!="cso_end_time" )? "desc=cso_end_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	结束时间
			 	</a>
			 </td>
			 </s:if>
						 
			 ${lz:set("canListable",lz:listableEx(controller,'csoCode',(true||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=200":""} tdid="46" ref="csoCode"   title="授权码">
			 	<a class="${desc=="cso_code" ? "desc" : ""}${asc=="cso_code" ? "asc" : ""}" href="?${desc=="cso_code" ? "asc=cso_code" : ""}${(asc=="cso_code" || desc!="cso_code" )? "desc=cso_code" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	授权码
			 	</a>
			 </td>
			 </s:if>
					
			 ${lz:set("canListable",lz:listableEx(controller,'csoStatus',(true||(true && all))))}
			 <s:if test="#request.canListable!=false">
			 <td  ${all?"width=120":""} tdid="52" ref="csoStatus"   title="状态">
			 	<a class="${desc=="cso_status" ? "desc" : ""}${asc=="cso_status" ? "asc" : ""}" href="?${desc=="cso_status" ? "asc=cso_status" : ""}${(asc=="cso_status" || desc!="cso_status" )? "desc=cso_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 </td>
			 </s:if>
			 <lz:set name="">
			 <td width="60" tdid="53" class="options" ref="options">操作</td>
			 </lz:set>
			<td width="105" class="operation" tdid="54" ref="operation">
				<lz:set name="">
				${lz:set("permission",lz:permission(controller,'canAdd'))}
				<s:if test="#request.canAdd==true && #request.permission==true">
					<a href="javascript:AddOrder()" style="text-decoration:underline">添加</a>
				</s:if>
				<s:else>&nbsp;</s:else>
				</lz:set>
			</td>
		  </tr>
		 </thead>	
		 <tbody>
	   <s:iterator value="#request.page.result" id="item" status="i">
		  <tr id="${item.csoId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csoId}"/>
			</td>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoId',(true||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoId" class=" node">
				 
				 ${item.csoId$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoUseMember',(true||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoUseMember" class="" relate="${item.csoUseMember}">
				 
				 ${item.csoUseMember$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoOutlets',(true||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoOutlets" class="" relate="${item.csoOutlets}">
				 
				${item.csoOutlets$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoOutletsRet',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoOutletsRet" class="" relate="${item.csoOutletsRet}">
				 ${item.csoOutletsRet$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoModel',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoModel" class="" relate="${item.csoModel}">
				 ${item.csoModel$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoCar',(true||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoCar" class="" relate="${item.csoCar}">
				 ${item.csoCar$}
			</td>
			</s:if>
			 	
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoCarNumber',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoCarNumber" class="">
				 ${item.csoCarNumber$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoMobile',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoMobile" class="">
				 ${item.csoMobile$}
			</td>
			</s:if>
			 	
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoInsureType',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoInsureType" class="" relate="${item.csoInsureType}">
				 ${item.csoInsureType$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoIsAdCar',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoIsAdCar" class="">
				 ${item.csoIsAdCar$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoRebate',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoRebate" class="">
				 ${item.csoRebate$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoFreehour',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoFreehour" class="">
				${item.csoFreehour$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoStartTime',(true||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoStartTime" class="">
				 ${item.csoStartTime$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoFinishTime',(true||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoFinishTime" class="">
				 ${item.csoFinishTime$}
			</td>
			</s:if>
			 	
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoTakeTime',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoTakeTime" class="">
				 ${item.csoTakeTime$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoRetTime',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoRetTime" class="">
				 ${item.csoRetTime$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoFuel',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoFuel" class="">
				 ${item.csoFuel$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoElectric',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoElectric" class="">
				 ${item.csoElectric$}
			</td>
			</s:if>
			 
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoMarginNeed',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoMarginNeed" class="">
				 ${item.csoMarginNeed$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoPredict',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoPredict" class="">
				 ${item.csoPredict$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoPayNeed',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoPayNeed" class="">
				 ${item.csoPayNeed$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoPayReal',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoPayReal" class="">
				 ${item.csoPayReal$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoPayMoney',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoPayMoney" class="">
				 ${item.csoPayMoney$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoPayCoupon',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoPayCoupon" class="">
				 ${item.csoPayCoupon$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoPayKilom',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoPayKilom" class="">
				 ${item.csoPayKilom$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoPayMileage',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoPayMileage" class="">
				 ${item.csoPayMileage$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoPayRent',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoPayRent" class="">
				 ${item.csoPayRent$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoPayInsure',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoPayInsure" class="">
				 ${item.csoPayInsure$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoPayTimeout',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoPayTimeout" class="">
				 ${item.csoPayTimeout$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoPayDriver',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoPayDriver" class="">
				 ${item.csoPayDriver$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoEndTime',(false||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoEndTime" class="">
				 ${item.csoEndTime$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoCode',(true||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoCode" class="">
				 ${item.csoCode$}
			</td>
			</s:if>
			 	
			${lz:set("canListable",lz:listableEx(controller,'csoStatus',(true||(true && all))))}
			<s:if test="#request.canListable!=false">
			<td ref="csoStatus" class="">
				 ${item.csoStatus$}
			</td>
			</s:if>
		  	
		  	<lz:set name="">
			<td class="options">			
				${lz:set("permission",lz:permission(controller,'canExp'))}
				<s:if test="#request.canExp[1]==true && #request.permission[1]==true">
				<a href="javascript:HandleSel('续订','${item.csoId}')">续订</a>
				</s:if>
				${lz:set("permission",lz:permission(controller,'canExp'))}
				<s:if test="#request.canExp[2]==true && #request.permission[2]==true">
				<a href="javascript:HandleSel('取消订单','${item.csoId}')">取消订单</a>
				</s:if>
			</td>
			</lz:set>
			
			<td class="operation" style="text-align:center;text-indent:0px;">
				${lz:set("permission",lz:permission(controller,'canView'))}
				<s:if test="#request.canView==true && #request.permission==true">
				<a href="javascript:DetailsOrder('${item.csoId}')">详情</a></s:if>
			</td>
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
	
<lz:set name="">
${lz:set("permission",lz:permission(controller,'canAdd'))}
<s:if test="#request.canAdd==true && #request.permission==true">
	/**
	* 添加订单
	**/
	function AddOrder(parent){		
		var url = "${basePath}${namespace}order_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),controller:{title:'添加订单'}};
		href(url,params);
	}
</s:if>
</lz:set>

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
			var url = "${basePath}${namespace}order_edit.do";
			var params = {entrypoint:"${entrypoint}",id:id,controller:{title:operate}};
			href(url,params);
		}
	}

	/**
	* 显示订单详情
	**/
	function DetailsOrder(id)
	{
		var url = "${basePath}${namespace}order_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,controller:{title:"订单详情",editable:false,visible:true}};
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
		DetailsOrder(checker.val());
	}
</script>

</body>
</html>