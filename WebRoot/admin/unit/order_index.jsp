<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/unit/order.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitOrder.csuoId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csuoId,csuoHost,csuoOrder,csuoSpecial,csuoUnitInfo,csuoUnitGroup,csuoUnitMember,csuoUnitChild,csuoCar,csuoNumber,csuoType,csuoPerson,csuoUsername,csuoMobile,csuoProfile,csuoStartTime,csuoFinishTime,csuoRetTime,csuoRemark,csuoAddTime,csuoUpdateTime,csuoSettleState,csuoSettleTime,csuoStatement,csuoFrom,csuoCheckTime,csuoChecker,csuoState",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csuoId,csuoHost,csuoOrder,csuoSpecial,csuoUnitInfo,csuoUnitGroup,csuoUnitMember,csuoUnitChild,csuoCar,csuoNumber,csuoType,csuoPerson,csuoUsername,csuoAlias,csuoMobile,csuoProfile,csuoIdcard,csuoStartTime,csuoFinishTime,csuoRetTime,csuoFreeHour,csuoFreeKm,csuoDayPrice,csuoHourPrice,csuoKmPrice,csuoPayNeed,csuoPayReal,csuoRemark,csuoAddTime,csuoUpdateTime,csuoMileage,csuoSettlement,csuoSettleState,csuoSettleTime,csuoStatement,csuoFrom,csuoCheckTime,csuoChecker,csuoState",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csuoId,csuoHost,csuoOrder,csuoSpecial,csuoUnitInfo,csuoUnitGroup,csuoNumber,csuoType,csuoUsername,csuoStartTime,csuoFinishTime,csuoState",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csuoId,csuoHost,csuoOrder,csuoSpecial,csuoUnitInfo,csuoUnitGroup,csuoNumber,csuoType,csuoUsername,csuoStartTime,csuoFinishTime,csuoState",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>企业订单管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	<meta name="hashcode" content="${window.hashcode}">
	
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
	<!-- 用户自定义css样式 -->
	<script type="text/javascript" src="${basePath}/admin/css/define.css"></script>
	
	<script type="text/javascript">
		window.basePath="${basePath}";
		window.contextPath="${basePath}${proname}/";
		window.hashcode = "${window.hashcode}";
		window.apiUrl="${basePath}${proname}/api.do";
		window.handles={};
	</script>
		
	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>
	
	<!------------------------LAZY3Q_JSP_HEAD------------------------>
	<!------------------------LAZY3Q_JSP_HEAD------------------------>	
	
</head>  
<body>

${lz:set("注释","****非法权限时的友情提醒****")}
<s:if test="#request.canView!=true">
	<div style="padding:80px 20px;width:500px;margin:0px auto;">
		<center class="message"><FONT color=red><H4>${lz:config("nolimit.message")}</H4></FONT></center>
	</div>
</body>
</html>
<%if(true)return;%>
</s:if>


<!------------------------LAZY3Q_JSP_BODY------------------------>
<!------------------------LAZY3Q_JSP_BODY------------------------>


${lz:set("querys",get:querys(servletPath))}
${lz:set("haveQuerys",lz:size(querys)>0)}

<div class="shortcuts state-content" style="display:${inFrame==null && (window.shortcuts!="hide" && (shortcuts!=null || haveQuerys))?"block":"none"};">
	<div class="shadow"></div>
	<div class="holder">
		<a class="item ${(T+Q+0)==0?"focus":""}" href="${location}">		
			<img align="absmiddle" src="${basePath}admin/images/icons/20088256421631277806.png"/>
			默认数据列表
		</a>
		
		<s:iterator value="#request.querys" id="item" status="i">
		<a class="item ${Q==item.sqId?"focus":""}" onclick="$.submit('${item.sqPath}?Q=${item.sqId}',eval('('+$(this).find('textarea').val()+')'))" href="javascript:void(0);">
			<span ref="${item.sqId}">X</span>
			<img align="absmiddle" src="${item.sqIcon}"/>${item.sqName}
			<textarea style="display:none;">${item.sqParams}</textarea>
		</a>
		</s:iterator>
		
		
		${shortcuts}
	</div>
</div>

<table class="${inFrame==null && (window.shortcuts!="hide" && (shortcuts!=null || haveQuerys))?"margin-by-shortcut":""}" border="0" cellspacing="0" cellpadding="0"><tr><td>

${before$title}
	<s:if test="#request.CTRL.canQuery==true">
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
	<s:if test="#request.shortcuts!=null || #request.haveQuerys==true">
		<a class="toggler" href="javascript:void(0);"><span class="${window.shortcuts!="hide"?"on":""}"></span></a>
	</s:if>
	<span class="caption">${empty title?"企业订单管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsUnitOrder" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="order.query.jsp"%>

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
					<button class="button" onclick="saveForm()" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778030.png"/>
						保存&nbsp;&nbsp;</button>
					<s:if test="#request.alias=='join' || #request.alias=='select'">
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
	$(".pager:eq(0)").remove();
})
</script>
</s:if>
    
${after$form}
	
</td></tr><tr><td>
	<s:if test="#request.CTRL.canQuery==true">
  <%@include file="/admin/module/page.jsp"%>
  	</s:if>
  			
	<div class="content">
	    <div class="toolbar">
	    
	      ${before$toolbar}
	    	
	      <s:if test="#request.alias==null">
		  </s:if>
		  <s:if test="#request.alias==null">	
		  </s:if>
	    	
	      ${lz:set("items",lz:split(handles,","))}
		  <s:iterator value="#request.items" id="handle" status="i">
			${lz:set("isMenu",(lz:indexOf(handle,"@")==0))}
			<s:if test="#request.isMenu==true">
			<button type="button" class="edit" onclick="HandleMenu('${handle}');">
				${handle}
			</button>
			</s:if>
		  </s:iterator>
	    
		  
			<button type="button" class="all" onclick="$('.check').attr('checked',true)">
				全选
			</button>
			<button type="button" class="invert" onclick="$('.check').click();">
				反选
			</button>
			<dl ref="com.ccclubs.model.CsUnitOrder" class="display dropdowan" style="margin-left:30px;">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/display.png"/>
					${((all=="false" || all==null) && defines==null ) ? "主要内容":""}
					${(all=="true" && defines==null) ? "全部内容":""}
					${(defines!=null) ? "自定义显示":""}
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
						<a all="false" href="${basePath}${lz:replace(servletPath,"^/","")}?all=false&${lz:queryss("UTF-8","all","fields")}">主要内容</a>
						<a all="true" href="${basePath}${lz:replace(servletPath,"^/","")}?all=true&${lz:queryss("UTF-8","all","fields")}">全部内容</a>
						<a all="" href="${basePath}${lz:replace(servletPath,"^/","")}?${lz:queryss("UTF-8","all","fields")}">自定义显示</a>
					</td>  <td class="r"></td></tr><tr>  <td class="bl"></td>  <td class="b"></td>  <td class="br"></td></tr>
					</tbody></table>
				</dd>
			</dl>
			<s:if test="#request.CTRL.canEdit==true">
			<dl ref="com.ccclubs.model.CsUnitOrder" class="batch dropdowan">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/20088256421529677807.png"/>
					批量修改
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
					</td>  <td class="r"></td></tr><tr>  <td class="bl"></td>  <td class="b"></td>  <td class="br"></td></tr>
					</tbody></table
				</dd>
			</dl>
			</s:if>
			
			${after$toolbar}
			
		</div>
		${lz:set("注释","*****************数据列表开始*****************")}
		<table id="order_table" ref="CsUnitOrder" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoId || #request.defines['csuoId']!=null">
			 <td <s:if test="#request.defines['csuoId']>0">colspan="${defines["csuoId"]}" ${all?"width":"iwidth"}="${defines["csuoId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="csuoId"   title="编号">
			 	<a class="${desc=="csuo_id" ? "desc" : ""}${asc=="csuo_id" ? "asc" : ""}" href="?${desc=="csuo_id" ? "asc=csuo_id" : ""}${(asc=="csuo_id" || desc!="csuo_id" )? "desc=csuo_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","csuoId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoHost || #request.defines['csuoHost']!=null">
			 <td <s:if test="#request.defines['csuoHost']>0">colspan="${defines["csuoHost"]}" ${all?"width":"iwidth"}="${defines["csuoHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="csuoHost"   title="城市">
			 	<a class="${desc=="csuo_host" ? "desc" : ""}${asc=="csuo_host" ? "asc" : ""}" href="?${desc=="csuo_host" ? "asc=csuo_host" : ""}${(asc=="csuo_host" || desc!="csuo_host" )? "desc=csuo_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","csuoHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoOrder || #request.defines['csuoOrder']!=null">
			 <td <s:if test="#request.defines['csuoOrder']>0">colspan="${defines["csuoOrder"]}" ${all?"width":"iwidth"}="${defines["csuoOrder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="3" ref="csuoOrder" flagKey  title="系统订单">
			 	<a class="${desc=="csuo_order" ? "desc" : ""}${asc=="csuo_order" ? "asc" : ""}" href="?${desc=="csuo_order" ? "asc=csuo_order" : ""}${(asc=="csuo_order" || desc!="csuo_order" )? "desc=csuo_order" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	系统订单
			 	</a>
			 	${lz:set("checkeds[]","csuoOrder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoSpecial || #request.defines['csuoSpecial']!=null">
			 <td <s:if test="#request.defines['csuoSpecial']>0">colspan="${defines["csuoSpecial"]}" ${all?"width":"iwidth"}="${defines["csuoSpecial"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="csuoSpecial"   title="专车订单">
			 	<a class="${desc=="csuo_special" ? "desc" : ""}${asc=="csuo_special" ? "asc" : ""}" href="?${desc=="csuo_special" ? "asc=csuo_special" : ""}${(asc=="csuo_special" || desc!="csuo_special" )? "desc=csuo_special" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	专车订单
			 	</a>
			 	${lz:set("checkeds[]","csuoSpecial")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoUnitInfo || #request.defines['csuoUnitInfo']!=null">
			 <td <s:if test="#request.defines['csuoUnitInfo']>0">colspan="${defines["csuoUnitInfo"]}" ${all?"width":"iwidth"}="${defines["csuoUnitInfo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="csuoUnitInfo"   title="所属单位">
			 	<a class="${desc=="csuo_unit_info" ? "desc" : ""}${asc=="csuo_unit_info" ? "asc" : ""}" href="?${desc=="csuo_unit_info" ? "asc=csuo_unit_info" : ""}${(asc=="csuo_unit_info" || desc!="csuo_unit_info" )? "desc=csuo_unit_info" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属单位
			 	</a>
			 	${lz:set("checkeds[]","csuoUnitInfo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoUnitGroup || #request.defines['csuoUnitGroup']!=null">
			 <td <s:if test="#request.defines['csuoUnitGroup']>0">colspan="${defines["csuoUnitGroup"]}" ${all?"width":"iwidth"}="${defines["csuoUnitGroup"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="csuoUnitGroup"   title="所属分组">
			 	<a class="${desc=="csuo_unit_group" ? "desc" : ""}${asc=="csuo_unit_group" ? "asc" : ""}" href="?${desc=="csuo_unit_group" ? "asc=csuo_unit_group" : ""}${(asc=="csuo_unit_group" || desc!="csuo_unit_group" )? "desc=csuo_unit_group" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属分组
			 	</a>
			 	${lz:set("checkeds[]","csuoUnitGroup")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoUnitMember || #request.defines['csuoUnitMember']!=null">
			 <td <s:if test="#request.defines['csuoUnitMember']>0">colspan="${defines["csuoUnitMember"]}" ${all?"width":"iwidth"}="${defines["csuoUnitMember"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="7" ref="csuoUnitMember"   title="支付帐号">
			 	<a class="${desc=="csuo_unit_member" ? "desc" : ""}${asc=="csuo_unit_member" ? "asc" : ""}" href="?${desc=="csuo_unit_member" ? "asc=csuo_unit_member" : ""}${(asc=="csuo_unit_member" || desc!="csuo_unit_member" )? "desc=csuo_unit_member" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	支付帐号
			 	</a>
			 	${lz:set("checkeds[]","csuoUnitMember")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoUnitChild || #request.defines['csuoUnitChild']!=null">
			 <td <s:if test="#request.defines['csuoUnitChild']>0">colspan="${defines["csuoUnitChild"]}" ${all?"width":"iwidth"}="${defines["csuoUnitChild"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="csuoUnitChild"   title="使用帐号">
			 	<a class="${desc=="csuo_unit_child" ? "desc" : ""}${asc=="csuo_unit_child" ? "asc" : ""}" href="?${desc=="csuo_unit_child" ? "asc=csuo_unit_child" : ""}${(asc=="csuo_unit_child" || desc!="csuo_unit_child" )? "desc=csuo_unit_child" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	使用帐号
			 	</a>
			 	${lz:set("checkeds[]","csuoUnitChild")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoCar || #request.defines['csuoCar']!=null">
			 <td <s:if test="#request.defines['csuoCar']>0">colspan="${defines["csuoCar"]}" ${all?"width":"iwidth"}="${defines["csuoCar"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="9" ref="csuoCar"   title="预订车辆">
			 	<a class="${desc=="csuo_car" ? "desc" : ""}${asc=="csuo_car" ? "asc" : ""}" href="?${desc=="csuo_car" ? "asc=csuo_car" : ""}${(asc=="csuo_car" || desc!="csuo_car" )? "desc=csuo_car" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	预订车辆
			 	</a>
			 	${lz:set("checkeds[]","csuoCar")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoNumber || #request.defines['csuoNumber']!=null">
			 <td <s:if test="#request.defines['csuoNumber']>0">colspan="${defines["csuoNumber"]}" ${all?"width":"iwidth"}="${defines["csuoNumber"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="10" ref="csuoNumber"   title="车牌号码">
			 	<a class="${desc=="csuo_number" ? "desc" : ""}${asc=="csuo_number" ? "asc" : ""}" href="?${desc=="csuo_number" ? "asc=csuo_number" : ""}${(asc=="csuo_number" || desc!="csuo_number" )? "desc=csuo_number" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车牌号码
			 	</a>
			 	${lz:set("checkeds[]","csuoNumber")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoType || #request.defines['csuoType']!=null">
			 <td <s:if test="#request.defines['csuoType']>0">colspan="${defines["csuoType"]}" ${all?"width":"iwidth"}="${defines["csuoType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="11" ref="csuoType"   title="订单类型">
			 	<a class="${desc=="csuo_type" ? "desc" : ""}${asc=="csuo_type" ? "asc" : ""}" href="?${desc=="csuo_type" ? "asc=csuo_type" : ""}${(asc=="csuo_type" || desc!="csuo_type" )? "desc=csuo_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单类型
			 	</a>
			 	${lz:set("checkeds[]","csuoType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoPerson || #request.defines['csuoPerson']!=null">
			 <td <s:if test="#request.defines['csuoPerson']>0">colspan="${defines["csuoPerson"]}" ${all?"width":"iwidth"}="${defines["csuoPerson"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="12" ref="csuoPerson"   title="用车人员">
			 	<a class="${desc=="csuo_person" ? "desc" : ""}${asc=="csuo_person" ? "asc" : ""}" href="?${desc=="csuo_person" ? "asc=csuo_person" : ""}${(asc=="csuo_person" || desc!="csuo_person" )? "desc=csuo_person" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	用车人员
			 	</a>
			 	${lz:set("checkeds[]","csuoPerson")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoUsername || #request.defines['csuoUsername']!=null">
			 <td <s:if test="#request.defines['csuoUsername']>0">colspan="${defines["csuoUsername"]}" ${all?"width":"iwidth"}="${defines["csuoUsername"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="13" ref="csuoUsername"   title="使用人姓名">
			 	<a class="${desc=="csuo_username" ? "desc" : ""}${asc=="csuo_username" ? "asc" : ""}" href="?${desc=="csuo_username" ? "asc=csuo_username" : ""}${(asc=="csuo_username" || desc!="csuo_username" )? "desc=csuo_username" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	使用人姓名
			 	</a>
			 	${lz:set("checkeds[]","csuoUsername")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoAlias || #request.defines['csuoAlias']!=null">
			 <td <s:if test="#request.defines['csuoAlias']>0">colspan="${defines["csuoAlias"]}" ${all?"width":"iwidth"}="${defines["csuoAlias"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="14" ref="csuoAlias"   title="别名或标记">
			 	<a class="${desc=="csuo_alias" ? "desc" : ""}${asc=="csuo_alias" ? "asc" : ""}" href="?${desc=="csuo_alias" ? "asc=csuo_alias" : ""}${(asc=="csuo_alias" || desc!="csuo_alias" )? "desc=csuo_alias" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	别名或标记
			 	</a>
			 	${lz:set("checkeds[]","csuoAlias")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoMobile || #request.defines['csuoMobile']!=null">
			 <td <s:if test="#request.defines['csuoMobile']>0">colspan="${defines["csuoMobile"]}" ${all?"width":"iwidth"}="${defines["csuoMobile"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="15" ref="csuoMobile"   title="使用人手机">
			 	<a class="${desc=="csuo_mobile" ? "desc" : ""}${asc=="csuo_mobile" ? "asc" : ""}" href="?${desc=="csuo_mobile" ? "asc=csuo_mobile" : ""}${(asc=="csuo_mobile" || desc!="csuo_mobile" )? "desc=csuo_mobile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	使用人手机
			 	</a>
			 	${lz:set("checkeds[]","csuoMobile")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoProfile || #request.defines['csuoProfile']!=null">
			 <td <s:if test="#request.defines['csuoProfile']>0">colspan="${defines["csuoProfile"]}" ${all?"width":"iwidth"}="${defines["csuoProfile"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="16" ref="csuoProfile"   title="订单摘要">
			 	<a class="${desc=="csuo_profile" ? "desc" : ""}${asc=="csuo_profile" ? "asc" : ""}" href="?${desc=="csuo_profile" ? "asc=csuo_profile" : ""}${(asc=="csuo_profile" || desc!="csuo_profile" )? "desc=csuo_profile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单摘要
			 	</a>
			 	${lz:set("checkeds[]","csuoProfile")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoIdcard || #request.defines['csuoIdcard']!=null">
			 <td <s:if test="#request.defines['csuoIdcard']>0">colspan="${defines["csuoIdcard"]}" ${all?"width":"iwidth"}="${defines["csuoIdcard"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="17" ref="csuoIdcard"   title="身份证号">
			 	<a class="${desc=="csuo_idcard" ? "desc" : ""}${asc=="csuo_idcard" ? "asc" : ""}" href="?${desc=="csuo_idcard" ? "asc=csuo_idcard" : ""}${(asc=="csuo_idcard" || desc!="csuo_idcard" )? "desc=csuo_idcard" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	身份证号
			 	</a>
			 	${lz:set("checkeds[]","csuoIdcard")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoStartTime || #request.defines['csuoStartTime']!=null">
			 <td <s:if test="#request.defines['csuoStartTime']>0">colspan="${defines["csuoStartTime"]}" ${all?"width":"iwidth"}="${defines["csuoStartTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="18" ref="csuoStartTime"   title="订单开始时间">
			 	<a class="${desc=="csuo_start_time" ? "desc" : ""}${asc=="csuo_start_time" ? "asc" : ""}" href="?${desc=="csuo_start_time" ? "asc=csuo_start_time" : ""}${(asc=="csuo_start_time" || desc!="csuo_start_time" )? "desc=csuo_start_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单开始时间
			 	</a>
			 	${lz:set("checkeds[]","csuoStartTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoFinishTime || #request.defines['csuoFinishTime']!=null">
			 <td <s:if test="#request.defines['csuoFinishTime']>0">colspan="${defines["csuoFinishTime"]}" ${all?"width":"iwidth"}="${defines["csuoFinishTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="19" ref="csuoFinishTime"   title="订单结束时间">
			 	<a class="${desc=="csuo_finish_time" ? "desc" : ""}${asc=="csuo_finish_time" ? "asc" : ""}" href="?${desc=="csuo_finish_time" ? "asc=csuo_finish_time" : ""}${(asc=="csuo_finish_time" || desc!="csuo_finish_time" )? "desc=csuo_finish_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单结束时间
			 	</a>
			 	${lz:set("checkeds[]","csuoFinishTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoRetTime || #request.defines['csuoRetTime']!=null">
			 <td <s:if test="#request.defines['csuoRetTime']>0">colspan="${defines["csuoRetTime"]}" ${all?"width":"iwidth"}="${defines["csuoRetTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="20" ref="csuoRetTime"   title="实际还车时间">
			 	<a class="${desc=="csuo_ret_time" ? "desc" : ""}${asc=="csuo_ret_time" ? "asc" : ""}" href="?${desc=="csuo_ret_time" ? "asc=csuo_ret_time" : ""}${(asc=="csuo_ret_time" || desc!="csuo_ret_time" )? "desc=csuo_ret_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	实际还车时间
			 	</a>
			 	${lz:set("checkeds[]","csuoRetTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoFreeHour || #request.defines['csuoFreeHour']!=null">
			 <td <s:if test="#request.defines['csuoFreeHour']>0">colspan="${defines["csuoFreeHour"]}" ${all?"width":"iwidth"}="${defines["csuoFreeHour"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="21" ref="csuoFreeHour"   title="免费小时(代理)">
			 	<a class="${desc=="csuo_free_hour" ? "desc" : ""}${asc=="csuo_free_hour" ? "asc" : ""}" href="?${desc=="csuo_free_hour" ? "asc=csuo_free_hour" : ""}${(asc=="csuo_free_hour" || desc!="csuo_free_hour" )? "desc=csuo_free_hour" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	免费小时(代理)
			 	</a>
			 	${lz:set("checkeds[]","csuoFreeHour")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoFreeKm || #request.defines['csuoFreeKm']!=null">
			 <td <s:if test="#request.defines['csuoFreeKm']>0">colspan="${defines["csuoFreeKm"]}" ${all?"width":"iwidth"}="${defines["csuoFreeKm"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="22" ref="csuoFreeKm"   title="免费里程(代理)">
			 	<a class="${desc=="csuo_free_km" ? "desc" : ""}${asc=="csuo_free_km" ? "asc" : ""}" href="?${desc=="csuo_free_km" ? "asc=csuo_free_km" : ""}${(asc=="csuo_free_km" || desc!="csuo_free_km" )? "desc=csuo_free_km" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	免费里程(代理)
			 	</a>
			 	${lz:set("checkeds[]","csuoFreeKm")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoDayPrice || #request.defines['csuoDayPrice']!=null">
			 <td <s:if test="#request.defines['csuoDayPrice']>0">colspan="${defines["csuoDayPrice"]}" ${all?"width":"iwidth"}="${defines["csuoDayPrice"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="23" ref="csuoDayPrice"   title="天租价格(代理)(元)">
			 	<a class="${desc=="csuo_day_price" ? "desc" : ""}${asc=="csuo_day_price" ? "asc" : ""}" href="?${desc=="csuo_day_price" ? "asc=csuo_day_price" : ""}${(asc=="csuo_day_price" || desc!="csuo_day_price" )? "desc=csuo_day_price" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	天租价格(代理)(元)
			 	</a>
			 	${lz:set("checkeds[]","csuoDayPrice")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoHourPrice || #request.defines['csuoHourPrice']!=null">
			 <td <s:if test="#request.defines['csuoHourPrice']>0">colspan="${defines["csuoHourPrice"]}" ${all?"width":"iwidth"}="${defines["csuoHourPrice"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="24" ref="csuoHourPrice"   title="小时价格(代理)(元)">
			 	<a class="${desc=="csuo_hour_price" ? "desc" : ""}${asc=="csuo_hour_price" ? "asc" : ""}" href="?${desc=="csuo_hour_price" ? "asc=csuo_hour_price" : ""}${(asc=="csuo_hour_price" || desc!="csuo_hour_price" )? "desc=csuo_hour_price" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	小时价格(代理)(元)
			 	</a>
			 	${lz:set("checkeds[]","csuoHourPrice")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoKmPrice || #request.defines['csuoKmPrice']!=null">
			 <td <s:if test="#request.defines['csuoKmPrice']>0">colspan="${defines["csuoKmPrice"]}" ${all?"width":"iwidth"}="${defines["csuoKmPrice"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="25" ref="csuoKmPrice"   title="里程价格(代理)">
			 	<a class="${desc=="csuo_km_price" ? "desc" : ""}${asc=="csuo_km_price" ? "asc" : ""}" href="?${desc=="csuo_km_price" ? "asc=csuo_km_price" : ""}${(asc=="csuo_km_price" || desc!="csuo_km_price" )? "desc=csuo_km_price" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	里程价格(代理)
			 	</a>
			 	${lz:set("checkeds[]","csuoKmPrice")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoPayNeed || #request.defines['csuoPayNeed']!=null">
			 <td <s:if test="#request.defines['csuoPayNeed']>0">colspan="${defines["csuoPayNeed"]}" ${all?"width":"iwidth"}="${defines["csuoPayNeed"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="26" ref="csuoPayNeed"   title="应付金额(代理)(元)">
			 	<a class="${desc=="csuo_pay_need" ? "desc" : ""}${asc=="csuo_pay_need" ? "asc" : ""}" href="?${desc=="csuo_pay_need" ? "asc=csuo_pay_need" : ""}${(asc=="csuo_pay_need" || desc!="csuo_pay_need" )? "desc=csuo_pay_need" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	应付金额(代理)(元)
			 	</a>
			 	${lz:set("checkeds[]","csuoPayNeed")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoPayReal || #request.defines['csuoPayReal']!=null">
			 <td <s:if test="#request.defines['csuoPayReal']>0">colspan="${defines["csuoPayReal"]}" ${all?"width":"iwidth"}="${defines["csuoPayReal"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="27" ref="csuoPayReal"   title="实付金额(代理)(元)">
			 	<a class="${desc=="csuo_pay_real" ? "desc" : ""}${asc=="csuo_pay_real" ? "asc" : ""}" href="?${desc=="csuo_pay_real" ? "asc=csuo_pay_real" : ""}${(asc=="csuo_pay_real" || desc!="csuo_pay_real" )? "desc=csuo_pay_real" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	实付金额(代理)(元)
			 	</a>
			 	${lz:set("checkeds[]","csuoPayReal")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoRemark || #request.defines['csuoRemark']!=null">
			 <td <s:if test="#request.defines['csuoRemark']>0">colspan="${defines["csuoRemark"]}" ${all?"width":"iwidth"}="${defines["csuoRemark"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="28" ref="csuoRemark"   title="订单备注">
			 	<a class="${desc=="csuo_remark" ? "desc" : ""}${asc=="csuo_remark" ? "asc" : ""}" href="?${desc=="csuo_remark" ? "asc=csuo_remark" : ""}${(asc=="csuo_remark" || desc!="csuo_remark" )? "desc=csuo_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单备注
			 	</a>
			 	${lz:set("checkeds[]","csuoRemark")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoAddTime || #request.defines['csuoAddTime']!=null">
			 <td <s:if test="#request.defines['csuoAddTime']>0">colspan="${defines["csuoAddTime"]}" ${all?"width":"iwidth"}="${defines["csuoAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="29" ref="csuoAddTime"   title="添加时间">
			 	<a class="${desc=="csuo_add_time" ? "desc" : ""}${asc=="csuo_add_time" ? "asc" : ""}" href="?${desc=="csuo_add_time" ? "asc=csuo_add_time" : ""}${(asc=="csuo_add_time" || desc!="csuo_add_time" )? "desc=csuo_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","csuoAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoUpdateTime || #request.defines['csuoUpdateTime']!=null">
			 <td <s:if test="#request.defines['csuoUpdateTime']>0">colspan="${defines["csuoUpdateTime"]}" ${all?"width":"iwidth"}="${defines["csuoUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="30" ref="csuoUpdateTime"   title="修改时间">
			 	<a class="${desc=="csuo_update_time" ? "desc" : ""}${asc=="csuo_update_time" ? "asc" : ""}" href="?${desc=="csuo_update_time" ? "asc=csuo_update_time" : ""}${(asc=="csuo_update_time" || desc!="csuo_update_time" )? "desc=csuo_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 	${lz:set("checkeds[]","csuoUpdateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoMileage || #request.defines['csuoMileage']!=null">
			 <td <s:if test="#request.defines['csuoMileage']>0">colspan="${defines["csuoMileage"]}" ${all?"width":"iwidth"}="${defines["csuoMileage"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="31" ref="csuoMileage"   title="里程数(公里)">
			 	<a class="${desc=="csuo_mileage" ? "desc" : ""}${asc=="csuo_mileage" ? "asc" : ""}" href="?${desc=="csuo_mileage" ? "asc=csuo_mileage" : ""}${(asc=="csuo_mileage" || desc!="csuo_mileage" )? "desc=csuo_mileage" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	里程数(公里)
			 	</a>
			 	${lz:set("checkeds[]","csuoMileage")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoSettlement || #request.defines['csuoSettlement']!=null">
			 <td <s:if test="#request.defines['csuoSettlement']>0">colspan="${defines["csuoSettlement"]}" ${all?"width":"iwidth"}="${defines["csuoSettlement"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="32" ref="csuoSettlement"   title="结算费用">
			 	<a class="${desc=="csuo_settlement" ? "desc" : ""}${asc=="csuo_settlement" ? "asc" : ""}" href="?${desc=="csuo_settlement" ? "asc=csuo_settlement" : ""}${(asc=="csuo_settlement" || desc!="csuo_settlement" )? "desc=csuo_settlement" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	结算费用
			 	</a>
			 	${lz:set("checkeds[]","csuoSettlement")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoSettleState || #request.defines['csuoSettleState']!=null">
			 <td <s:if test="#request.defines['csuoSettleState']>0">colspan="${defines["csuoSettleState"]}" ${all?"width":"iwidth"}="${defines["csuoSettleState"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="33" ref="csuoSettleState"   title="结算状态">
			 	<a class="${desc=="csuo_settle_state" ? "desc" : ""}${asc=="csuo_settle_state" ? "asc" : ""}" href="?${desc=="csuo_settle_state" ? "asc=csuo_settle_state" : ""}${(asc=="csuo_settle_state" || desc!="csuo_settle_state" )? "desc=csuo_settle_state" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	结算状态
			 	</a>
			 	${lz:set("checkeds[]","csuoSettleState")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoSettleTime || #request.defines['csuoSettleTime']!=null">
			 <td <s:if test="#request.defines['csuoSettleTime']>0">colspan="${defines["csuoSettleTime"]}" ${all?"width":"iwidth"}="${defines["csuoSettleTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="34" ref="csuoSettleTime"   title="结算时间">
			 	<a class="${desc=="csuo_settle_time" ? "desc" : ""}${asc=="csuo_settle_time" ? "asc" : ""}" href="?${desc=="csuo_settle_time" ? "asc=csuo_settle_time" : ""}${(asc=="csuo_settle_time" || desc!="csuo_settle_time" )? "desc=csuo_settle_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	结算时间
			 	</a>
			 	${lz:set("checkeds[]","csuoSettleTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoStatement || #request.defines['csuoStatement']!=null">
			 <td <s:if test="#request.defines['csuoStatement']>0">colspan="${defines["csuoStatement"]}" ${all?"width":"iwidth"}="${defines["csuoStatement"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="35" ref="csuoStatement"   title="结账单">
			 	<a class="${desc=="csuo_statement" ? "desc" : ""}${asc=="csuo_statement" ? "asc" : ""}" href="?${desc=="csuo_statement" ? "asc=csuo_statement" : ""}${(asc=="csuo_statement" || desc!="csuo_statement" )? "desc=csuo_statement" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	结账单
			 	</a>
			 	${lz:set("checkeds[]","csuoStatement")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoFrom || #request.defines['csuoFrom']!=null">
			 <td <s:if test="#request.defines['csuoFrom']>0">colspan="${defines["csuoFrom"]}" ${all?"width":"iwidth"}="${defines["csuoFrom"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="36" ref="csuoFrom"   title="订单来源">
			 	<a class="${desc=="csuo_from" ? "desc" : ""}${asc=="csuo_from" ? "asc" : ""}" href="?${desc=="csuo_from" ? "asc=csuo_from" : ""}${(asc=="csuo_from" || desc!="csuo_from" )? "desc=csuo_from" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单来源
			 	</a>
			 	${lz:set("checkeds[]","csuoFrom")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoCheckTime || #request.defines['csuoCheckTime']!=null">
			 <td <s:if test="#request.defines['csuoCheckTime']>0">colspan="${defines["csuoCheckTime"]}" ${all?"width":"iwidth"}="${defines["csuoCheckTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="37" ref="csuoCheckTime"   title="审核时间">
			 	<a class="${desc=="csuo_check_time" ? "desc" : ""}${asc=="csuo_check_time" ? "asc" : ""}" href="?${desc=="csuo_check_time" ? "asc=csuo_check_time" : ""}${(asc=="csuo_check_time" || desc!="csuo_check_time" )? "desc=csuo_check_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	审核时间
			 	</a>
			 	${lz:set("checkeds[]","csuoCheckTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoChecker || #request.defines['csuoChecker']!=null">
			 <td <s:if test="#request.defines['csuoChecker']>0">colspan="${defines["csuoChecker"]}" ${all?"width":"iwidth"}="${defines["csuoChecker"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="38" ref="csuoChecker"   title="审核人">
			 	<a class="${desc=="csuo_checker" ? "desc" : ""}${asc=="csuo_checker" ? "asc" : ""}" href="?${desc=="csuo_checker" ? "asc=csuo_checker" : ""}${(asc=="csuo_checker" || desc!="csuo_checker" )? "desc=csuo_checker" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	审核人
			 	</a>
			 	${lz:set("checkeds[]","csuoChecker")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csuoState || #request.defines['csuoState']!=null">
			 <td <s:if test="#request.defines['csuoState']>0">colspan="${defines["csuoState"]}" ${all?"width":"iwidth"}="${defines["csuoState"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="39" ref="csuoState"   title="状态">
			 	<a class="${desc=="csuo_state" ? "desc" : ""}${asc=="csuo_state" ? "asc" : ""}" href="?${desc=="csuo_state" ? "asc=csuo_state" : ""}${(asc=="csuo_state" || desc!="csuo_state" )? "desc=csuo_state" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","csuoState")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="40" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="41" ref="operation">
					&nbsp;
			</td>
		  </tr>
		  <s:if test="#request.childes!=null">
		  ${lz:set("注释","****级联字段:当某字段关联另外一个表时，并且用户勾选了要显示另外的字段****")}
		  <tr>
		  	<s:iterator value="#request.childes" id="fieldName" status="i">
		 	<td tdid="${fieldName}" ref="${fieldName}">
		 		${lz:set("field",lz:field(modelClass,fieldName))}
		 		<!-- <a class="${desc==field.notes.column.value ? "desc" : ""}${asc==field.notes.column.value ? "asc" : ""}" href="?${desc==field.notes.column.value ? (lz:join("asc=",field.notes.column.value)) : ""}${(asc==field.notes.column.value || desc!=field.notes.column.value )? (lz:join("desc=",field.notes.column.value)) : ""}&${lz:queryss("UTF-8","desc","asc")}"> -->
		 		${field.notes.caption.value}
		 		<!-- </a> -->
		 	</td>
		 	</s:iterator>
		 	${lz:set("checkeds[]",fieldName)}
		  </tr>
		  </s:if>
		 </thead>	
		 <tbody>
		 	<s:iterator value="#request.page.result" id="item" status="i">
		  <tr id="${item.csuoId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csuoId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoId || #request.defines['csuoId']!=null">
				<s:if test="#request.defines['csuoId']>0">
					${lz:set("注释","****csuoId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoId",lz:indexOf(fieldName,"csuoId")>-1)}
				  		<s:if test="#request.atCsuoId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoId字段的字串格式化输出****")}
					<td ref="csuoId" class="td ">
						 
						 	${lz:or(item$csuoId[i.count-1],lz:left(item.csuoId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoHost || #request.defines['csuoHost']!=null">
				<s:if test="#request.defines['csuoHost']>0">
					${lz:set("注释","****csuoHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoHost",lz:indexOf(fieldName,"csuoHost")>-1)}
				  		<s:if test="#request.atCsuoHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoHost字段的字串格式化输出****")}
					<td ref="csuoHost" class="td " relate="${item.csuoHost}">
						 
						 	<a <s:if test="#item.csuoHost!=null && #item.csuoHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.csuoHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csuoHost[i.count-1],lz:left(item.csuoHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoOrder || #request.defines['csuoOrder']!=null">
				<s:if test="#request.defines['csuoOrder']>0">
					${lz:set("注释","****csuoOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoOrder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoOrder",lz:indexOf(fieldName,"csuoOrder")>-1)}
				  		<s:if test="#request.atCsuoOrder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoOrder字段的字串格式化输出****")}
					<td ref="csuoOrder" class="td  node" relate="${item.csuoOrder}">
						 
						 	<a <s:if test="#item.csuoOrder!=null && #item.csuoOrder!=''"> onclick="window.href('${basePath}${proname}/service/order_details.do?key=${item.csuoOrder}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csuoOrder[i.count-1],lz:left(item.csuoOrder$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoSpecial || #request.defines['csuoSpecial']!=null">
				<s:if test="#request.defines['csuoSpecial']>0">
					${lz:set("注释","****csuoSpecial关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoSpecial))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoSpecial",lz:indexOf(fieldName,"csuoSpecial")>-1)}
				  		<s:if test="#request.atCsuoSpecial==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoSpecial字段的字串格式化输出****")}
					<td ref="csuoSpecial" class="td " relate="${item.csuoSpecial}">
						 
						 	<a <s:if test="#item.csuoSpecial!=null && #item.csuoSpecial!=''"> onclick="window.href('${basePath}${proname}/unit/specialcar_details.do?key=${item.csuoSpecial}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csuoSpecial[i.count-1],lz:left(item.csuoSpecial$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoUnitInfo || #request.defines['csuoUnitInfo']!=null">
				<s:if test="#request.defines['csuoUnitInfo']>0">
					${lz:set("注释","****csuoUnitInfo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoUnitInfo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoUnitInfo",lz:indexOf(fieldName,"csuoUnitInfo")>-1)}
				  		<s:if test="#request.atCsuoUnitInfo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoUnitInfo字段的字串格式化输出****")}
					<td ref="csuoUnitInfo" class="td " relate="${item.csuoUnitInfo}">
						 
						 	<a <s:if test="#item.csuoUnitInfo!=null && #item.csuoUnitInfo!=''"> onclick="window.href('${basePath}${proname}/unit/info_details.do?key=${item.csuoUnitInfo}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csuoUnitInfo[i.count-1],lz:left(item.csuoUnitInfo$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoUnitGroup || #request.defines['csuoUnitGroup']!=null">
				<s:if test="#request.defines['csuoUnitGroup']>0">
					${lz:set("注释","****csuoUnitGroup关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoUnitGroup))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoUnitGroup",lz:indexOf(fieldName,"csuoUnitGroup")>-1)}
				  		<s:if test="#request.atCsuoUnitGroup==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoUnitGroup字段的字串格式化输出****")}
					<td ref="csuoUnitGroup" class="td " relate="${item.csuoUnitGroup}">
						 
						 	<a <s:if test="#item.csuoUnitGroup!=null && #item.csuoUnitGroup!=''"> onclick="window.href('${basePath}${proname}/unit/group_details.do?key=${item.csuoUnitGroup}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csuoUnitGroup[i.count-1],lz:left(item.csuoUnitGroup$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoUnitMember || #request.defines['csuoUnitMember']!=null">
				<s:if test="#request.defines['csuoUnitMember']>0">
					${lz:set("注释","****csuoUnitMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoUnitMember))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoUnitMember",lz:indexOf(fieldName,"csuoUnitMember")>-1)}
				  		<s:if test="#request.atCsuoUnitMember==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoUnitMember字段的字串格式化输出****")}
					<td ref="csuoUnitMember" class="td " relate="${item.csuoUnitMember}">
						 
						 	<a <s:if test="#item.csuoUnitMember!=null && #item.csuoUnitMember!=''"> onclick="window.href('${basePath}${proname}/user/member_details.do?key=${item.csuoUnitMember}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csuoUnitMember[i.count-1],lz:left(item.csuoUnitMember$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoUnitChild || #request.defines['csuoUnitChild']!=null">
				<s:if test="#request.defines['csuoUnitChild']>0">
					${lz:set("注释","****csuoUnitChild关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoUnitChild))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoUnitChild",lz:indexOf(fieldName,"csuoUnitChild")>-1)}
				  		<s:if test="#request.atCsuoUnitChild==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoUnitChild字段的字串格式化输出****")}
					<td ref="csuoUnitChild" class="td " relate="${item.csuoUnitChild}">
						 
						 	<a <s:if test="#item.csuoUnitChild!=null && #item.csuoUnitChild!=''"> onclick="window.href('${basePath}${proname}/user/member_details.do?key=${item.csuoUnitChild}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csuoUnitChild[i.count-1],lz:left(item.csuoUnitChild$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoCar || #request.defines['csuoCar']!=null">
				<s:if test="#request.defines['csuoCar']>0">
					${lz:set("注释","****csuoCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoCar))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoCar",lz:indexOf(fieldName,"csuoCar")>-1)}
				  		<s:if test="#request.atCsuoCar==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoCar字段的字串格式化输出****")}
					<td ref="csuoCar" class="td " relate="${item.csuoCar}">
						 
						 	<a <s:if test="#item.csuoCar!=null && #item.csuoCar!=''"> onclick="window.href('${basePath}${proname}/object/car_details.do?key=${item.csuoCar}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csuoCar[i.count-1],lz:left(item.csuoCar$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoNumber || #request.defines['csuoNumber']!=null">
				<s:if test="#request.defines['csuoNumber']>0">
					${lz:set("注释","****csuoNumber关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoNumber))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoNumber",lz:indexOf(fieldName,"csuoNumber")>-1)}
				  		<s:if test="#request.atCsuoNumber==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoNumber字段的字串格式化输出****")}
					<td ref="csuoNumber" class="td ">
						 
						 	${lz:or(item$csuoNumber[i.count-1],lz:left(item.csuoNumber$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoType || #request.defines['csuoType']!=null">
				<s:if test="#request.defines['csuoType']>0">
					${lz:set("注释","****csuoType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoType",lz:indexOf(fieldName,"csuoType")>-1)}
				  		<s:if test="#request.atCsuoType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoType字段的字串格式化输出****")}
					<td ref="csuoType" class="td ">
						 
						 	${lz:or(item$csuoType[i.count-1],lz:left(item.csuoType$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoPerson || #request.defines['csuoPerson']!=null">
				<s:if test="#request.defines['csuoPerson']>0">
					${lz:set("注释","****csuoPerson关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoPerson))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoPerson",lz:indexOf(fieldName,"csuoPerson")>-1)}
				  		<s:if test="#request.atCsuoPerson==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoPerson字段的字串格式化输出****")}
					<td ref="csuoPerson" class="td " relate="${item.csuoPerson}">
						 
						 	<a <s:if test="#item.csuoPerson!=null && #item.csuoPerson!=''"> onclick="window.href('${basePath}${proname}/unit/person_details.do?key=${item.csuoPerson}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csuoPerson[i.count-1],lz:left(item.csuoPerson$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoUsername || #request.defines['csuoUsername']!=null">
				<s:if test="#request.defines['csuoUsername']>0">
					${lz:set("注释","****csuoUsername关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoUsername))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoUsername",lz:indexOf(fieldName,"csuoUsername")>-1)}
				  		<s:if test="#request.atCsuoUsername==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoUsername字段的字串格式化输出****")}
					<td ref="csuoUsername" class="td ">
						 
						 	${lz:or(item$csuoUsername[i.count-1],lz:left(item.csuoUsername$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoAlias || #request.defines['csuoAlias']!=null">
				<s:if test="#request.defines['csuoAlias']>0">
					${lz:set("注释","****csuoAlias关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoAlias))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoAlias",lz:indexOf(fieldName,"csuoAlias")>-1)}
				  		<s:if test="#request.atCsuoAlias==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoAlias字段的字串格式化输出****")}
					<td ref="csuoAlias" class="td ">
						 
						 	${lz:or(item$csuoAlias[i.count-1],lz:left(item.csuoAlias$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoMobile || #request.defines['csuoMobile']!=null">
				<s:if test="#request.defines['csuoMobile']>0">
					${lz:set("注释","****csuoMobile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoMobile))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoMobile",lz:indexOf(fieldName,"csuoMobile")>-1)}
				  		<s:if test="#request.atCsuoMobile==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoMobile字段的字串格式化输出****")}
					<td ref="csuoMobile" class="td ">
						 
						 	${lz:or(item$csuoMobile[i.count-1],lz:left(item.csuoMobile$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoProfile || #request.defines['csuoProfile']!=null">
				<s:if test="#request.defines['csuoProfile']>0">
					${lz:set("注释","****csuoProfile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoProfile))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoProfile",lz:indexOf(fieldName,"csuoProfile")>-1)}
				  		<s:if test="#request.atCsuoProfile==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoProfile字段的字串格式化输出****")}
					<td ref="csuoProfile" class="td ">
						 
						 	${lz:or(item$csuoProfile[i.count-1],lz:left(item.csuoProfile$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoIdcard || #request.defines['csuoIdcard']!=null">
				<s:if test="#request.defines['csuoIdcard']>0">
					${lz:set("注释","****csuoIdcard关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoIdcard))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoIdcard",lz:indexOf(fieldName,"csuoIdcard")>-1)}
				  		<s:if test="#request.atCsuoIdcard==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoIdcard字段的字串格式化输出****")}
					<td ref="csuoIdcard" class="td ">
						 
						 	${lz:or(item$csuoIdcard[i.count-1],lz:left(item.csuoIdcard$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoStartTime || #request.defines['csuoStartTime']!=null">
				<s:if test="#request.defines['csuoStartTime']>0">
					${lz:set("注释","****csuoStartTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoStartTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoStartTime",lz:indexOf(fieldName,"csuoStartTime")>-1)}
				  		<s:if test="#request.atCsuoStartTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoStartTime字段的字串格式化输出****")}
					<td ref="csuoStartTime" class="td ">
						 
						 	${lz:or(item$csuoStartTime[i.count-1],lz:left(item.csuoStartTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoFinishTime || #request.defines['csuoFinishTime']!=null">
				<s:if test="#request.defines['csuoFinishTime']>0">
					${lz:set("注释","****csuoFinishTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoFinishTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoFinishTime",lz:indexOf(fieldName,"csuoFinishTime")>-1)}
				  		<s:if test="#request.atCsuoFinishTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoFinishTime字段的字串格式化输出****")}
					<td ref="csuoFinishTime" class="td ">
						 
						 	${lz:or(item$csuoFinishTime[i.count-1],lz:left(item.csuoFinishTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoRetTime || #request.defines['csuoRetTime']!=null">
				<s:if test="#request.defines['csuoRetTime']>0">
					${lz:set("注释","****csuoRetTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoRetTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoRetTime",lz:indexOf(fieldName,"csuoRetTime")>-1)}
				  		<s:if test="#request.atCsuoRetTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoRetTime字段的字串格式化输出****")}
					<td ref="csuoRetTime" class="td ">
						 
						 	${lz:or(item$csuoRetTime[i.count-1],lz:left(item.csuoRetTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoFreeHour || #request.defines['csuoFreeHour']!=null">
				<s:if test="#request.defines['csuoFreeHour']>0">
					${lz:set("注释","****csuoFreeHour关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoFreeHour))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoFreeHour",lz:indexOf(fieldName,"csuoFreeHour")>-1)}
				  		<s:if test="#request.atCsuoFreeHour==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoFreeHour字段的字串格式化输出****")}
					<td ref="csuoFreeHour" class="td ">
						 
						 	${lz:or(item$csuoFreeHour[i.count-1],lz:left(item.csuoFreeHour$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoFreeKm || #request.defines['csuoFreeKm']!=null">
				<s:if test="#request.defines['csuoFreeKm']>0">
					${lz:set("注释","****csuoFreeKm关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoFreeKm))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoFreeKm",lz:indexOf(fieldName,"csuoFreeKm")>-1)}
				  		<s:if test="#request.atCsuoFreeKm==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoFreeKm字段的字串格式化输出****")}
					<td ref="csuoFreeKm" class="td ">
						 
						 	${lz:or(item$csuoFreeKm[i.count-1],lz:left(item.csuoFreeKm$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoDayPrice || #request.defines['csuoDayPrice']!=null">
				<s:if test="#request.defines['csuoDayPrice']>0">
					${lz:set("注释","****csuoDayPrice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoDayPrice))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoDayPrice",lz:indexOf(fieldName,"csuoDayPrice")>-1)}
				  		<s:if test="#request.atCsuoDayPrice==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoDayPrice字段的字串格式化输出****")}
					<td ref="csuoDayPrice" class="td ">
						 
						 	${lz:or(item$csuoDayPrice[i.count-1],lz:left(item.csuoDayPrice$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoHourPrice || #request.defines['csuoHourPrice']!=null">
				<s:if test="#request.defines['csuoHourPrice']>0">
					${lz:set("注释","****csuoHourPrice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoHourPrice))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoHourPrice",lz:indexOf(fieldName,"csuoHourPrice")>-1)}
				  		<s:if test="#request.atCsuoHourPrice==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoHourPrice字段的字串格式化输出****")}
					<td ref="csuoHourPrice" class="td ">
						 
						 	${lz:or(item$csuoHourPrice[i.count-1],lz:left(item.csuoHourPrice$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoKmPrice || #request.defines['csuoKmPrice']!=null">
				<s:if test="#request.defines['csuoKmPrice']>0">
					${lz:set("注释","****csuoKmPrice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoKmPrice))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoKmPrice",lz:indexOf(fieldName,"csuoKmPrice")>-1)}
				  		<s:if test="#request.atCsuoKmPrice==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoKmPrice字段的字串格式化输出****")}
					<td ref="csuoKmPrice" class="td ">
						 
						 	${lz:or(item$csuoKmPrice[i.count-1],lz:left(item.csuoKmPrice$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoPayNeed || #request.defines['csuoPayNeed']!=null">
				<s:if test="#request.defines['csuoPayNeed']>0">
					${lz:set("注释","****csuoPayNeed关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoPayNeed))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoPayNeed",lz:indexOf(fieldName,"csuoPayNeed")>-1)}
				  		<s:if test="#request.atCsuoPayNeed==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoPayNeed字段的字串格式化输出****")}
					<td ref="csuoPayNeed" class="td ">
						 
						 	${lz:or(item$csuoPayNeed[i.count-1],lz:left(item.csuoPayNeed$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoPayReal || #request.defines['csuoPayReal']!=null">
				<s:if test="#request.defines['csuoPayReal']>0">
					${lz:set("注释","****csuoPayReal关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoPayReal))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoPayReal",lz:indexOf(fieldName,"csuoPayReal")>-1)}
				  		<s:if test="#request.atCsuoPayReal==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoPayReal字段的字串格式化输出****")}
					<td ref="csuoPayReal" class="td ">
						 
						 	${lz:or(item$csuoPayReal[i.count-1],lz:left(item.csuoPayReal$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoRemark || #request.defines['csuoRemark']!=null">
				<s:if test="#request.defines['csuoRemark']>0">
					${lz:set("注释","****csuoRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoRemark))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoRemark",lz:indexOf(fieldName,"csuoRemark")>-1)}
				  		<s:if test="#request.atCsuoRemark==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoRemark字段的字串格式化输出****")}
					<td ref="csuoRemark" class="td ">
						 
						 	${lz:or(item$csuoRemark[i.count-1],lz:left(item.csuoRemark$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoAddTime || #request.defines['csuoAddTime']!=null">
				<s:if test="#request.defines['csuoAddTime']>0">
					${lz:set("注释","****csuoAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoAddTime",lz:indexOf(fieldName,"csuoAddTime")>-1)}
				  		<s:if test="#request.atCsuoAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoAddTime字段的字串格式化输出****")}
					<td ref="csuoAddTime" class="td ">
						 
						 	${lz:or(item$csuoAddTime[i.count-1],lz:left(item.csuoAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoUpdateTime || #request.defines['csuoUpdateTime']!=null">
				<s:if test="#request.defines['csuoUpdateTime']>0">
					${lz:set("注释","****csuoUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoUpdateTime",lz:indexOf(fieldName,"csuoUpdateTime")>-1)}
				  		<s:if test="#request.atCsuoUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoUpdateTime字段的字串格式化输出****")}
					<td ref="csuoUpdateTime" class="td ">
						 
						 	${lz:or(item$csuoUpdateTime[i.count-1],lz:left(item.csuoUpdateTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoMileage || #request.defines['csuoMileage']!=null">
				<s:if test="#request.defines['csuoMileage']>0">
					${lz:set("注释","****csuoMileage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoMileage))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoMileage",lz:indexOf(fieldName,"csuoMileage")>-1)}
				  		<s:if test="#request.atCsuoMileage==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoMileage字段的字串格式化输出****")}
					<td ref="csuoMileage" class="td ">
						 
						 	${lz:or(item$csuoMileage[i.count-1],lz:left(item.csuoMileage$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoSettlement || #request.defines['csuoSettlement']!=null">
				<s:if test="#request.defines['csuoSettlement']>0">
					${lz:set("注释","****csuoSettlement关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoSettlement))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoSettlement",lz:indexOf(fieldName,"csuoSettlement")>-1)}
				  		<s:if test="#request.atCsuoSettlement==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoSettlement字段的字串格式化输出****")}
					<td ref="csuoSettlement" class="td ">
						 
						 	${lz:or(item$csuoSettlement[i.count-1],lz:left(item.csuoSettlement$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoSettleState || #request.defines['csuoSettleState']!=null">
				<s:if test="#request.defines['csuoSettleState']>0">
					${lz:set("注释","****csuoSettleState关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoSettleState))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoSettleState",lz:indexOf(fieldName,"csuoSettleState")>-1)}
				  		<s:if test="#request.atCsuoSettleState==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoSettleState字段的字串格式化输出****")}
					<td ref="csuoSettleState" class="td ">
						 
						 	${lz:or(item$csuoSettleState[i.count-1],lz:left(item.csuoSettleState$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoSettleTime || #request.defines['csuoSettleTime']!=null">
				<s:if test="#request.defines['csuoSettleTime']>0">
					${lz:set("注释","****csuoSettleTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoSettleTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoSettleTime",lz:indexOf(fieldName,"csuoSettleTime")>-1)}
				  		<s:if test="#request.atCsuoSettleTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoSettleTime字段的字串格式化输出****")}
					<td ref="csuoSettleTime" class="td ">
						 
						 	${lz:or(item$csuoSettleTime[i.count-1],lz:left(item.csuoSettleTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoStatement || #request.defines['csuoStatement']!=null">
				<s:if test="#request.defines['csuoStatement']>0">
					${lz:set("注释","****csuoStatement关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoStatement))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoStatement",lz:indexOf(fieldName,"csuoStatement")>-1)}
				  		<s:if test="#request.atCsuoStatement==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoStatement字段的字串格式化输出****")}
					<td ref="csuoStatement" class="td " relate="${item.csuoStatement}">
						 
						 	<a <s:if test="#item.csuoStatement!=null && #item.csuoStatement!=''"> onclick="window.href('${basePath}${proname}/unit/bill_details.do?key=${item.csuoStatement}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csuoStatement[i.count-1],lz:left(item.csuoStatement$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoFrom || #request.defines['csuoFrom']!=null">
				<s:if test="#request.defines['csuoFrom']>0">
					${lz:set("注释","****csuoFrom关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoFrom))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoFrom",lz:indexOf(fieldName,"csuoFrom")>-1)}
				  		<s:if test="#request.atCsuoFrom==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoFrom字段的字串格式化输出****")}
					<td ref="csuoFrom" class="td ">
						 
						 	${lz:or(item$csuoFrom[i.count-1],lz:left(item.csuoFrom$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoCheckTime || #request.defines['csuoCheckTime']!=null">
				<s:if test="#request.defines['csuoCheckTime']>0">
					${lz:set("注释","****csuoCheckTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoCheckTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoCheckTime",lz:indexOf(fieldName,"csuoCheckTime")>-1)}
				  		<s:if test="#request.atCsuoCheckTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoCheckTime字段的字串格式化输出****")}
					<td ref="csuoCheckTime" class="td ">
						 
						 	${lz:or(item$csuoCheckTime[i.count-1],lz:left(item.csuoCheckTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoChecker || #request.defines['csuoChecker']!=null">
				<s:if test="#request.defines['csuoChecker']>0">
					${lz:set("注释","****csuoChecker关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoChecker))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoChecker",lz:indexOf(fieldName,"csuoChecker")>-1)}
				  		<s:if test="#request.atCsuoChecker==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoChecker字段的字串格式化输出****")}
					<td ref="csuoChecker" class="td ">
						 
						 	${lz:or(item$csuoChecker[i.count-1],lz:left(item.csuoChecker$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csuoState || #request.defines['csuoState']!=null">
				<s:if test="#request.defines['csuoState']>0">
					${lz:set("注释","****csuoState关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csuoState))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsuoState",lz:indexOf(fieldName,"csuoState")>-1)}
				  		<s:if test="#request.atCsuoState==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csuoState字段的字串格式化输出****")}
					<td ref="csuoState" class="td ">
						 
						 	${lz:or(item$csuoState[i.count-1],lz:left(item.csuoState$,100))}
					</td>
				</s:else>
			</s:if>
		   ${lz:set("注释","*****************数据列表数据部分结束*****************")}		  
		  
		   
		    ${lz:set("注释","****自定义的单行记录操作句柄[request中的handles(String[])变量]****")}
			<td class="options">
				  <s:if test="#request.alias==null">	
					<s:if test="#request.CTRL.canExp[0]==true">
					<a href="javascript:HandleSel('续订','${item.csuoId}')">续订</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[1]==true">
					<a href="javascript:HandleSel('取消','${item.csuoId}')">取消</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[2]==true">
					<a href="javascript:HandleSel('审核','${item.csuoId}')">审核</a>
					</s:if>
				</s:if>
				${lz:set("items",lz:split(handles,","))}
				<s:iterator value="#request.items" id="handle" status="i">
					${lz:set("isMenu",(lz:indexOf(handle,"@")==0))}
					<s:if test="#request.isMenu!=true">
					<a href="javascript:HandleSel('${handle}','${item.csuoId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectOrder('${item.csuoId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditOrder('${item.csuoId}')">修改</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsOrder('${item.csuoId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoId || #request.defines['csuoId']!=null">
				<s:if test="#request.defines['csuoId']>0">
					${lz:set("注释","****csuoId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoId",lz:indexOf(fieldName,"csuoId")>-1)}				  		
				  		<s:if test="#request.atCsuoId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoHost || #request.defines['csuoHost']!=null">
				<s:if test="#request.defines['csuoHost']>0">
					${lz:set("注释","****csuoHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoHost",lz:indexOf(fieldName,"csuoHost")>-1)}				  		
				  		<s:if test="#request.atCsuoHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoOrder || #request.defines['csuoOrder']!=null">
				<s:if test="#request.defines['csuoOrder']>0">
					${lz:set("注释","****csuoOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoOrder",lz:indexOf(fieldName,"csuoOrder")>-1)}				  		
				  		<s:if test="#request.atCsuoOrder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoOrder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoSpecial || #request.defines['csuoSpecial']!=null">
				<s:if test="#request.defines['csuoSpecial']>0">
					${lz:set("注释","****csuoSpecial关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoSpecial",lz:indexOf(fieldName,"csuoSpecial")>-1)}				  		
				  		<s:if test="#request.atCsuoSpecial==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoSpecial[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoUnitInfo || #request.defines['csuoUnitInfo']!=null">
				<s:if test="#request.defines['csuoUnitInfo']>0">
					${lz:set("注释","****csuoUnitInfo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoUnitInfo",lz:indexOf(fieldName,"csuoUnitInfo")>-1)}				  		
				  		<s:if test="#request.atCsuoUnitInfo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoUnitInfo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoUnitGroup || #request.defines['csuoUnitGroup']!=null">
				<s:if test="#request.defines['csuoUnitGroup']>0">
					${lz:set("注释","****csuoUnitGroup关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoUnitGroup",lz:indexOf(fieldName,"csuoUnitGroup")>-1)}				  		
				  		<s:if test="#request.atCsuoUnitGroup==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoUnitGroup[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoUnitMember || #request.defines['csuoUnitMember']!=null">
				<s:if test="#request.defines['csuoUnitMember']>0">
					${lz:set("注释","****csuoUnitMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoUnitMember",lz:indexOf(fieldName,"csuoUnitMember")>-1)}				  		
				  		<s:if test="#request.atCsuoUnitMember==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoUnitMember[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoUnitChild || #request.defines['csuoUnitChild']!=null">
				<s:if test="#request.defines['csuoUnitChild']>0">
					${lz:set("注释","****csuoUnitChild关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoUnitChild",lz:indexOf(fieldName,"csuoUnitChild")>-1)}				  		
				  		<s:if test="#request.atCsuoUnitChild==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoUnitChild[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoCar || #request.defines['csuoCar']!=null">
				<s:if test="#request.defines['csuoCar']>0">
					${lz:set("注释","****csuoCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoCar",lz:indexOf(fieldName,"csuoCar")>-1)}				  		
				  		<s:if test="#request.atCsuoCar==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoCar[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoNumber || #request.defines['csuoNumber']!=null">
				<s:if test="#request.defines['csuoNumber']>0">
					${lz:set("注释","****csuoNumber关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoNumber",lz:indexOf(fieldName,"csuoNumber")>-1)}				  		
				  		<s:if test="#request.atCsuoNumber==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoNumber[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoType || #request.defines['csuoType']!=null">
				<s:if test="#request.defines['csuoType']>0">
					${lz:set("注释","****csuoType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoType",lz:indexOf(fieldName,"csuoType")>-1)}				  		
				  		<s:if test="#request.atCsuoType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoPerson || #request.defines['csuoPerson']!=null">
				<s:if test="#request.defines['csuoPerson']>0">
					${lz:set("注释","****csuoPerson关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoPerson",lz:indexOf(fieldName,"csuoPerson")>-1)}				  		
				  		<s:if test="#request.atCsuoPerson==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoPerson[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoUsername || #request.defines['csuoUsername']!=null">
				<s:if test="#request.defines['csuoUsername']>0">
					${lz:set("注释","****csuoUsername关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoUsername",lz:indexOf(fieldName,"csuoUsername")>-1)}				  		
				  		<s:if test="#request.atCsuoUsername==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoUsername[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoAlias || #request.defines['csuoAlias']!=null">
				<s:if test="#request.defines['csuoAlias']>0">
					${lz:set("注释","****csuoAlias关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoAlias",lz:indexOf(fieldName,"csuoAlias")>-1)}				  		
				  		<s:if test="#request.atCsuoAlias==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoAlias[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoMobile || #request.defines['csuoMobile']!=null">
				<s:if test="#request.defines['csuoMobile']>0">
					${lz:set("注释","****csuoMobile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoMobile",lz:indexOf(fieldName,"csuoMobile")>-1)}				  		
				  		<s:if test="#request.atCsuoMobile==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoMobile[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoProfile || #request.defines['csuoProfile']!=null">
				<s:if test="#request.defines['csuoProfile']>0">
					${lz:set("注释","****csuoProfile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoProfile",lz:indexOf(fieldName,"csuoProfile")>-1)}				  		
				  		<s:if test="#request.atCsuoProfile==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoProfile[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoIdcard || #request.defines['csuoIdcard']!=null">
				<s:if test="#request.defines['csuoIdcard']>0">
					${lz:set("注释","****csuoIdcard关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoIdcard",lz:indexOf(fieldName,"csuoIdcard")>-1)}				  		
				  		<s:if test="#request.atCsuoIdcard==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoIdcard[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoStartTime || #request.defines['csuoStartTime']!=null">
				<s:if test="#request.defines['csuoStartTime']>0">
					${lz:set("注释","****csuoStartTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoStartTime",lz:indexOf(fieldName,"csuoStartTime")>-1)}				  		
				  		<s:if test="#request.atCsuoStartTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoStartTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoFinishTime || #request.defines['csuoFinishTime']!=null">
				<s:if test="#request.defines['csuoFinishTime']>0">
					${lz:set("注释","****csuoFinishTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoFinishTime",lz:indexOf(fieldName,"csuoFinishTime")>-1)}				  		
				  		<s:if test="#request.atCsuoFinishTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoFinishTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoRetTime || #request.defines['csuoRetTime']!=null">
				<s:if test="#request.defines['csuoRetTime']>0">
					${lz:set("注释","****csuoRetTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoRetTime",lz:indexOf(fieldName,"csuoRetTime")>-1)}				  		
				  		<s:if test="#request.atCsuoRetTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoRetTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoFreeHour || #request.defines['csuoFreeHour']!=null">
				<s:if test="#request.defines['csuoFreeHour']>0">
					${lz:set("注释","****csuoFreeHour关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoFreeHour",lz:indexOf(fieldName,"csuoFreeHour")>-1)}				  		
				  		<s:if test="#request.atCsuoFreeHour==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoFreeHour[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoFreeKm || #request.defines['csuoFreeKm']!=null">
				<s:if test="#request.defines['csuoFreeKm']>0">
					${lz:set("注释","****csuoFreeKm关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoFreeKm",lz:indexOf(fieldName,"csuoFreeKm")>-1)}				  		
				  		<s:if test="#request.atCsuoFreeKm==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoFreeKm[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoDayPrice || #request.defines['csuoDayPrice']!=null">
				<s:if test="#request.defines['csuoDayPrice']>0">
					${lz:set("注释","****csuoDayPrice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoDayPrice",lz:indexOf(fieldName,"csuoDayPrice")>-1)}				  		
				  		<s:if test="#request.atCsuoDayPrice==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoDayPrice[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoHourPrice || #request.defines['csuoHourPrice']!=null">
				<s:if test="#request.defines['csuoHourPrice']>0">
					${lz:set("注释","****csuoHourPrice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoHourPrice",lz:indexOf(fieldName,"csuoHourPrice")>-1)}				  		
				  		<s:if test="#request.atCsuoHourPrice==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoHourPrice[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoKmPrice || #request.defines['csuoKmPrice']!=null">
				<s:if test="#request.defines['csuoKmPrice']>0">
					${lz:set("注释","****csuoKmPrice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoKmPrice",lz:indexOf(fieldName,"csuoKmPrice")>-1)}				  		
				  		<s:if test="#request.atCsuoKmPrice==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoKmPrice[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoPayNeed || #request.defines['csuoPayNeed']!=null">
				<s:if test="#request.defines['csuoPayNeed']>0">
					${lz:set("注释","****csuoPayNeed关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoPayNeed",lz:indexOf(fieldName,"csuoPayNeed")>-1)}				  		
				  		<s:if test="#request.atCsuoPayNeed==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoPayNeed[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoPayReal || #request.defines['csuoPayReal']!=null">
				<s:if test="#request.defines['csuoPayReal']>0">
					${lz:set("注释","****csuoPayReal关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoPayReal",lz:indexOf(fieldName,"csuoPayReal")>-1)}				  		
				  		<s:if test="#request.atCsuoPayReal==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoPayReal[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoRemark || #request.defines['csuoRemark']!=null">
				<s:if test="#request.defines['csuoRemark']>0">
					${lz:set("注释","****csuoRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoRemark",lz:indexOf(fieldName,"csuoRemark")>-1)}				  		
				  		<s:if test="#request.atCsuoRemark==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoRemark[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoAddTime || #request.defines['csuoAddTime']!=null">
				<s:if test="#request.defines['csuoAddTime']>0">
					${lz:set("注释","****csuoAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoAddTime",lz:indexOf(fieldName,"csuoAddTime")>-1)}				  		
				  		<s:if test="#request.atCsuoAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoUpdateTime || #request.defines['csuoUpdateTime']!=null">
				<s:if test="#request.defines['csuoUpdateTime']>0">
					${lz:set("注释","****csuoUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoUpdateTime",lz:indexOf(fieldName,"csuoUpdateTime")>-1)}				  		
				  		<s:if test="#request.atCsuoUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoMileage || #request.defines['csuoMileage']!=null">
				<s:if test="#request.defines['csuoMileage']>0">
					${lz:set("注释","****csuoMileage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoMileage",lz:indexOf(fieldName,"csuoMileage")>-1)}				  		
				  		<s:if test="#request.atCsuoMileage==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoMileage[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoSettlement || #request.defines['csuoSettlement']!=null">
				<s:if test="#request.defines['csuoSettlement']>0">
					${lz:set("注释","****csuoSettlement关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoSettlement",lz:indexOf(fieldName,"csuoSettlement")>-1)}				  		
				  		<s:if test="#request.atCsuoSettlement==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoSettlement[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoSettleState || #request.defines['csuoSettleState']!=null">
				<s:if test="#request.defines['csuoSettleState']>0">
					${lz:set("注释","****csuoSettleState关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoSettleState",lz:indexOf(fieldName,"csuoSettleState")>-1)}				  		
				  		<s:if test="#request.atCsuoSettleState==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoSettleState[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoSettleTime || #request.defines['csuoSettleTime']!=null">
				<s:if test="#request.defines['csuoSettleTime']>0">
					${lz:set("注释","****csuoSettleTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoSettleTime",lz:indexOf(fieldName,"csuoSettleTime")>-1)}				  		
				  		<s:if test="#request.atCsuoSettleTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoSettleTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoStatement || #request.defines['csuoStatement']!=null">
				<s:if test="#request.defines['csuoStatement']>0">
					${lz:set("注释","****csuoStatement关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoStatement",lz:indexOf(fieldName,"csuoStatement")>-1)}				  		
				  		<s:if test="#request.atCsuoStatement==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoStatement[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoFrom || #request.defines['csuoFrom']!=null">
				<s:if test="#request.defines['csuoFrom']>0">
					${lz:set("注释","****csuoFrom关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoFrom",lz:indexOf(fieldName,"csuoFrom")>-1)}				  		
				  		<s:if test="#request.atCsuoFrom==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoFrom[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoCheckTime || #request.defines['csuoCheckTime']!=null">
				<s:if test="#request.defines['csuoCheckTime']>0">
					${lz:set("注释","****csuoCheckTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoCheckTime",lz:indexOf(fieldName,"csuoCheckTime")>-1)}				  		
				  		<s:if test="#request.atCsuoCheckTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoCheckTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoChecker || #request.defines['csuoChecker']!=null">
				<s:if test="#request.defines['csuoChecker']>0">
					${lz:set("注释","****csuoChecker关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoChecker",lz:indexOf(fieldName,"csuoChecker")>-1)}				  		
				  		<s:if test="#request.atCsuoChecker==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoChecker[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csuoState || #request.defines['csuoState']!=null">
				<s:if test="#request.defines['csuoState']>0">
					${lz:set("注释","****csuoState关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsuoState",lz:indexOf(fieldName,"csuoState")>-1)}				  		
				  		<s:if test="#request.atCsuoState==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csuoState[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
				 				<td class="onemore">${lz:left(lz:el(item,arrFieldName),100)}</td>
				 			</s:if>
				 			<s:else>
								<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
							</s:else>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					<s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
				</s:else>
			 </s:if>
			 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
		  	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
		 </tr>
			 </s:iterator>
	 	 </s:bean>
		 </s:if>
		  
		  
		 </s:iterator>
		 </tbody>			 
	  </table>
	  
	  ${lz:set("注释","*****************数据列表结束*****************")}
	  
  </div>
  <%@include file="/admin/module/page.jsp"%>
  
 </td></tr></table>

<script>

	<s:if test="#request.alias=='join'">
	window.objects = ${objects==null ? null : (lz:json(objects))};
	</s:if>	
	window.entrypoint="${entrypoint}";
	
	showTips("${tips.value}");
	
	<s:if test="#request.onload!=null">
	$(function(){
		try{
			if("${lz:js(onload)}".indexOf("(")>-1){
				eval("${lz:js(onload)}");
			}else{
				window["${onload}"]();
			}
		}catch(e){}
	});
	</s:if>
	

	function href(url,params){
		top.$.showModalDialog({url:url},params,window);
	} 
	
	function saveForm(){
		var params = $("#queryForm").params();
		var paramCount = 0;
		for(var o in params){
			if($.trim(params[o])=="")
				delete params[o];
			else if($("#queryForm *[name='"+o+"']").attr("type")=="hidden"){
			
			}else
				paramCount++;
		}
		if(paramCount==0){
			top.Alert("没有任何查询条件，不能保存");
			return;
		}
		var dialog = top.$.open("${basePath}admin/configurator/query_edit.do",{
			"srvQuery.sqPath":"${servletPath}",
			"srvQuery.sqParams":JSON.stringify(params)
		},function(object){
			if(typeof(object)!="object"){
				try{
					dialog.find("iframe").get(0).contentWindow.$("#sqPath").parents("dl").hide();
					dialog.find("iframe").get(0).contentWindow.$("#sqParams").parents("dl").hide();
				}catch(e){}
			}else if(object && object.sqId){
				top.Alert("快速查询链接保存成功");
				$.cache("shortcuts","show");
				$.submit("${location}?Q="+object.sqId,params);
			}
		});		
	}
	$(function(){
		$(".shortcuts .item span").click(function(){
			var id = $(this).attr("ref");
			var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除该快速查询链接吗？</span>",
				title:"删除快速查询链接",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}admin/configurator/query_del.do";
					var params = {entrypoint:"${entrypoint}",id:id};
					$.submit(url,params);					
				}
			});
			return false;	
		});
	});
<s:if test="#request.alias=='select'">
	/**
	* 选择企业订单
	**/
	function SelectOrder(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改企业订单
	**/
	function EditOrder(id){
		var url = "${basePath}${namespace}order_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改企业订单'}":lz:json(ctrl)}};
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
		EditOrder(checker.val());
	}
	
	/**
	* 批量修改
	**/
	function UpdateSel(flag)
	{
		var checker=$(".check:checked");
		if(checker.length==0){
			<s:if test="#session.ccclubs_login.suId==0">
			$.alert("确定要对当前查询的${objects.count}条记录进行批量修改吗?",function(){
				var url = "${basePath}${namespace}order_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新企业订单",visible:false,editable:false,fields:{}}};
				for(var o in fieldNames[flag])
					params.ctrl["fields"][o]={visible:true,editable:true};
				href(url,params);
			});
			</s:if>
			<s:else>
			top.$.alert("您未选择要修改的记录");
			</s:else>
		}else{
			var ids = "";
			checker.each(function(i){
				if(ids!="")
					ids+=",";
				ids+=$(this).val();
			});
			var url = "${basePath}${namespace}order_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新企业订单",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改企业订单
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}order_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新企业订单",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改企业订单任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}order_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新企业订单",visible:false,editable:false,fields:{}}};
			params.ctrl["fields"][$(this).attr("ref")]={visible:true,editable:true};
			href(url,params);
		});
	});
	</s:if>

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
			var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:operate}};
			href(url,params);
		}
	}
	/*
	* 自定义菜单
	*/
	function HandleMenu(operate){
		if(window.getHandle && window.getHandle(operate)!=null)//本窗口的操作函数获取句柄
			window.getHandle(operate)(operate);
		else if(window.handles && window.handles[operate])//本窗口的操作函数句柄
			window.handles[operate](operate);
		else if(typeof(caller)!="undefined" && caller.handles && caller.handles[operate])//调用窗口的操作函数句柄
			caller.handles[operate](operate);
		else if(parent && parent.handles && parent.handles[operate])//父窗口的操作函数句柄
			parent.handles[operate](operate);
		else{
			var url = "${basePath}${namespace}order_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
	/**
	* 显示企业订单详情
	**/
	function DetailsOrder(id)
	{
		var url = "${basePath}${namespace}order_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"企业订单详情",editable:false,visible:true}};
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
	/*
	* 数据导出
	*/
	function exportXls(){
		if(${page.count>=0 ? page.count : 0}==0){
			showTips("当前没有可导出数据");
			return;
		}
		top.$.showModalDialog({
			url:"${basePath}admin/export.do"
		},{
			className:"${page.result[0].class.name}",
			count:${page.count+0},
			exporturl:"${basePath}${servletPath}?export=true&page={page}&${lz:querys("UTF-8","page")}",
			checkeds:"${checkeds}"
		},window);	
	}
</script>


<!------------------------LAZY3Q_JSP_BOTTOM------------------------>
<!------------------------LAZY3Q_JSP_BOTTOM------------------------>


</body>
</html>