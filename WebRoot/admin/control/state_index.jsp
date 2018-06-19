<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/control/state.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csState.cssId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"cssId,cssHost,cssNumber,cssCar,cssAddTime,cssOrder",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"cssId,cssHost,cssNumber,cssCar,cssAddTime,cssCurrentTime,cssRented,cssWarn,cssRfid,cssRfidDte,cssObdMile,cssEngineT,cssMileage,cssSpeed,cssMotor,cssOil,cssPower,cssEvBattery,cssCharging,cssFuelMileage,cssElectricMileage,cssEndurance,cssTemperature,cssCsq,cssPowerConsumption,cssLongitude,cssLatitude,cssOrder",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"cssHost,cssNumber,cssAddTime,cssOrder",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"cssHost,cssNumber,cssAddTime,cssRented,cssRfid,cssMileage,cssSpeed,cssMotor,cssOil,cssPower,cssLongitude,cssLatitude,cssOrder",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>车辆当前状态管理</title>
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


<!--LAZY3Q_JSP_BODY-->
<script>
$(function(){
	$(".table thead td[title='添加时间']").attr("width",140);
});
</script>
<!--LAZY3Q_JSP_BODY-->


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
	<span class="caption">${empty title?"车辆当前状态管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsState" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="state.query.jsp"%>

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
			<s:if test="#request.CTRL.canDel==true">
			<button type="button" class="del" onclick="DelSel();">
				删除
			</button>
			</s:if>
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
			<dl ref="com.ccclubs.model.CsState" class="display dropdowan" style="margin-left:30px;">
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
			
			${after$toolbar}
			
		</div>
		${lz:set("注释","*****************数据列表开始*****************")}
		<table id="state_table" ref="CsState" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssId || #request.defines['cssId']!=null">
			 <td <s:if test="#request.defines['cssId']>0">colspan="${defines["cssId"]}" ${all?"width":"iwidth"}="${defines["cssId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="cssId"   title="编号">
			 	<a class="${desc=="css_id" ? "desc" : ""}${asc=="css_id" ? "asc" : ""}" href="?${desc=="css_id" ? "asc=css_id" : ""}${(asc=="css_id" || desc!="css_id" )? "desc=css_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","cssId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssHost || #request.defines['cssHost']!=null">
			 <td <s:if test="#request.defines['cssHost']>0">colspan="${defines["cssHost"]}" ${all?"width":"iwidth"}="${defines["cssHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="cssHost"   title="城市">
			 	<a class="${desc=="css_host" ? "desc" : ""}${asc=="css_host" ? "asc" : ""}" href="?${desc=="css_host" ? "asc=css_host" : ""}${(asc=="css_host" || desc!="css_host" )? "desc=css_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","cssHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssNumber || #request.defines['cssNumber']!=null">
			 <td <s:if test="#request.defines['cssNumber']>0">colspan="${defines["cssNumber"]}" ${all?"width":"iwidth"}="${defines["cssNumber"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="3" ref="cssNumber" flagKey  title="车牌号">
			 	<a class="${desc=="css_number" ? "desc" : ""}${asc=="css_number" ? "asc" : ""}" href="?${desc=="css_number" ? "asc=css_number" : ""}${(asc=="css_number" || desc!="css_number" )? "desc=css_number" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车牌号
			 	</a>
			 	${lz:set("checkeds[]","cssNumber")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssCar || #request.defines['cssCar']!=null">
			 <td <s:if test="#request.defines['cssCar']>0">colspan="${defines["cssCar"]}" ${all?"width":"iwidth"}="${defines["cssCar"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="cssCar"   title="关联车辆">
			 	<a class="${desc=="css_car" ? "desc" : ""}${asc=="css_car" ? "asc" : ""}" href="?${desc=="css_car" ? "asc=css_car" : ""}${(asc=="css_car" || desc!="css_car" )? "desc=css_car" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	关联车辆
			 	</a>
			 	${lz:set("checkeds[]","cssCar")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssAddTime || #request.defines['cssAddTime']!=null">
			 <td <s:if test="#request.defines['cssAddTime']>0">colspan="${defines["cssAddTime"]}" ${all?"width":"iwidth"}="${defines["cssAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="5" ref="cssAddTime"   title="添加时间">
			 	<a class="${desc=="css_add_time" ? "desc" : ""}${asc=="css_add_time" ? "asc" : ""}" href="?${desc=="css_add_time" ? "asc=css_add_time" : ""}${(asc=="css_add_time" || desc!="css_add_time" )? "desc=css_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","cssAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssCurrentTime || #request.defines['cssCurrentTime']!=null">
			 <td <s:if test="#request.defines['cssCurrentTime']>0">colspan="${defines["cssCurrentTime"]}" ${all?"width":"iwidth"}="${defines["cssCurrentTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="6" ref="cssCurrentTime"   title="下位机时间">
			 	<a class="${desc=="css_current_time" ? "desc" : ""}${asc=="css_current_time" ? "asc" : ""}" href="?${desc=="css_current_time" ? "asc=css_current_time" : ""}${(asc=="css_current_time" || desc!="css_current_time" )? "desc=css_current_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	下位机时间
			 	</a>
			 	${lz:set("checkeds[]","cssCurrentTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssRented || #request.defines['cssRented']!=null">
			 <td <s:if test="#request.defines['cssRented']>0">colspan="${defines["cssRented"]}" ${all?"width":"iwidth"}="${defines["cssRented"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="7" ref="cssRented"   title="租赁状态">
			 	<a class="${desc=="css_rented" ? "desc" : ""}${asc=="css_rented" ? "asc" : ""}" href="?${desc=="css_rented" ? "asc=css_rented" : ""}${(asc=="css_rented" || desc!="css_rented" )? "desc=css_rented" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	租赁状态
			 	</a>
			 	${lz:set("checkeds[]","cssRented")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssWarn || #request.defines['cssWarn']!=null">
			 <td <s:if test="#request.defines['cssWarn']>0">colspan="${defines["cssWarn"]}" ${all?"width":"iwidth"}="${defines["cssWarn"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="8" ref="cssWarn"   title="有报警">
			 	<a class="${desc=="css_warn" ? "desc" : ""}${asc=="css_warn" ? "asc" : ""}" href="?${desc=="css_warn" ? "asc=css_warn" : ""}${(asc=="css_warn" || desc!="css_warn" )? "desc=css_warn" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	有报警
			 	</a>
			 	${lz:set("checkeds[]","cssWarn")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssRfid || #request.defines['cssRfid']!=null">
			 <td <s:if test="#request.defines['cssRfid']>0">colspan="${defines["cssRfid"]}" ${all?"width":"iwidth"}="${defines["cssRfid"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="9" ref="cssRfid"   title="RFID卡号">
			 	<a class="${desc=="css_rfid" ? "desc" : ""}${asc=="css_rfid" ? "asc" : ""}" href="?${desc=="css_rfid" ? "asc=css_rfid" : ""}${(asc=="css_rfid" || desc!="css_rfid" )? "desc=css_rfid" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	RFID卡号
			 	</a>
			 	${lz:set("checkeds[]","cssRfid")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssRfidDte || #request.defines['cssRfidDte']!=null">
			 <td <s:if test="#request.defines['cssRfidDte']>0">colspan="${defines["cssRfidDte"]}" ${all?"width":"iwidth"}="${defines["cssRfidDte"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="10" ref="cssRfidDte"   title="用户RFID">
			 	<a class="${desc=="css_rfid_dte" ? "desc" : ""}${asc=="css_rfid_dte" ? "asc" : ""}" href="?${desc=="css_rfid_dte" ? "asc=css_rfid_dte" : ""}${(asc=="css_rfid_dte" || desc!="css_rfid_dte" )? "desc=css_rfid_dte" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	用户RFID
			 	</a>
			 	${lz:set("checkeds[]","cssRfidDte")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssObdMile || #request.defines['cssObdMile']!=null">
			 <td <s:if test="#request.defines['cssObdMile']>0">colspan="${defines["cssObdMile"]}" ${all?"width":"iwidth"}="${defines["cssObdMile"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="11" ref="cssObdMile"   title="OBD里程">
			 	<a class="${desc=="css_obd_mile" ? "desc" : ""}${asc=="css_obd_mile" ? "asc" : ""}" href="?${desc=="css_obd_mile" ? "asc=css_obd_mile" : ""}${(asc=="css_obd_mile" || desc!="css_obd_mile" )? "desc=css_obd_mile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	OBD里程
			 	</a>
			 	${lz:set("checkeds[]","cssObdMile")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssEngineT || #request.defines['cssEngineT']!=null">
			 <td <s:if test="#request.defines['cssEngineT']>0">colspan="${defines["cssEngineT"]}" ${all?"width":"iwidth"}="${defines["cssEngineT"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="12" ref="cssEngineT"   title="发动机温度">
			 	<a class="${desc=="css_engine_t" ? "desc" : ""}${asc=="css_engine_t" ? "asc" : ""}" href="?${desc=="css_engine_t" ? "asc=css_engine_t" : ""}${(asc=="css_engine_t" || desc!="css_engine_t" )? "desc=css_engine_t" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	发动机温度
			 	</a>
			 	${lz:set("checkeds[]","cssEngineT")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssMileage || #request.defines['cssMileage']!=null">
			 <td <s:if test="#request.defines['cssMileage']>0">colspan="${defines["cssMileage"]}" ${all?"width":"iwidth"}="${defines["cssMileage"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="13" ref="cssMileage"   title="总里程">
			 	<a class="${desc=="css_mileage" ? "desc" : ""}${asc=="css_mileage" ? "asc" : ""}" href="?${desc=="css_mileage" ? "asc=css_mileage" : ""}${(asc=="css_mileage" || desc!="css_mileage" )? "desc=css_mileage" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	总里程
			 	</a>
			 	${lz:set("checkeds[]","cssMileage")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssSpeed || #request.defines['cssSpeed']!=null">
			 <td <s:if test="#request.defines['cssSpeed']>0">colspan="${defines["cssSpeed"]}" ${all?"width":"iwidth"}="${defines["cssSpeed"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="14" ref="cssSpeed"   title="车速">
			 	<a class="${desc=="css_speed" ? "desc" : ""}${asc=="css_speed" ? "asc" : ""}" href="?${desc=="css_speed" ? "asc=css_speed" : ""}${(asc=="css_speed" || desc!="css_speed" )? "desc=css_speed" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车速
			 	</a>
			 	${lz:set("checkeds[]","cssSpeed")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssMotor || #request.defines['cssMotor']!=null">
			 <td <s:if test="#request.defines['cssMotor']>0">colspan="${defines["cssMotor"]}" ${all?"width":"iwidth"}="${defines["cssMotor"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="15" ref="cssMotor"   title="转速">
			 	<a class="${desc=="css_motor" ? "desc" : ""}${asc=="css_motor" ? "asc" : ""}" href="?${desc=="css_motor" ? "asc=css_motor" : ""}${(asc=="css_motor" || desc!="css_motor" )? "desc=css_motor" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	转速
			 	</a>
			 	${lz:set("checkeds[]","cssMotor")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssOil || #request.defines['cssOil']!=null">
			 <td <s:if test="#request.defines['cssOil']>0">colspan="${defines["cssOil"]}" ${all?"width":"iwidth"}="${defines["cssOil"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="16" ref="cssOil"   title="燃油量">
			 	<a class="${desc=="css_oil" ? "desc" : ""}${asc=="css_oil" ? "asc" : ""}" href="?${desc=="css_oil" ? "asc=css_oil" : ""}${(asc=="css_oil" || desc!="css_oil" )? "desc=css_oil" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	燃油量
			 	</a>
			 	${lz:set("checkeds[]","cssOil")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssPower || #request.defines['cssPower']!=null">
			 <td <s:if test="#request.defines['cssPower']>0">colspan="${defines["cssPower"]}" ${all?"width":"iwidth"}="${defines["cssPower"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="17" ref="cssPower"   title="蓄电量">
			 	<a class="${desc=="css_power" ? "desc" : ""}${asc=="css_power" ? "asc" : ""}" href="?${desc=="css_power" ? "asc=css_power" : ""}${(asc=="css_power" || desc!="css_power" )? "desc=css_power" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	蓄电量
			 	</a>
			 	${lz:set("checkeds[]","cssPower")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssEvBattery || #request.defines['cssEvBattery']!=null">
			 <td <s:if test="#request.defines['cssEvBattery']>0">colspan="${defines["cssEvBattery"]}" ${all?"width":"iwidth"}="${defines["cssEvBattery"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="18" ref="cssEvBattery"   title="动力电池电量">
			 	<a class="${desc=="css_ev_battery" ? "desc" : ""}${asc=="css_ev_battery" ? "asc" : ""}" href="?${desc=="css_ev_battery" ? "asc=css_ev_battery" : ""}${(asc=="css_ev_battery" || desc!="css_ev_battery" )? "desc=css_ev_battery" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	动力电池电量
			 	</a>
			 	${lz:set("checkeds[]","cssEvBattery")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssCharging || #request.defines['cssCharging']!=null">
			 <td <s:if test="#request.defines['cssCharging']>0">colspan="${defines["cssCharging"]}" ${all?"width":"iwidth"}="${defines["cssCharging"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="19" ref="cssCharging"   title="在充电">
			 	<a class="${desc=="css_charging" ? "desc" : ""}${asc=="css_charging" ? "asc" : ""}" href="?${desc=="css_charging" ? "asc=css_charging" : ""}${(asc=="css_charging" || desc!="css_charging" )? "desc=css_charging" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	在充电
			 	</a>
			 	${lz:set("checkeds[]","cssCharging")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssFuelMileage || #request.defines['cssFuelMileage']!=null">
			 <td <s:if test="#request.defines['cssFuelMileage']>0">colspan="${defines["cssFuelMileage"]}" ${all?"width":"iwidth"}="${defines["cssFuelMileage"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="20" ref="cssFuelMileage"   title="油里程">
			 	<a class="${desc=="css_fuel_mileage" ? "desc" : ""}${asc=="css_fuel_mileage" ? "asc" : ""}" href="?${desc=="css_fuel_mileage" ? "asc=css_fuel_mileage" : ""}${(asc=="css_fuel_mileage" || desc!="css_fuel_mileage" )? "desc=css_fuel_mileage" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	油里程
			 	</a>
			 	${lz:set("checkeds[]","cssFuelMileage")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssElectricMileage || #request.defines['cssElectricMileage']!=null">
			 <td <s:if test="#request.defines['cssElectricMileage']>0">colspan="${defines["cssElectricMileage"]}" ${all?"width":"iwidth"}="${defines["cssElectricMileage"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="21" ref="cssElectricMileage"   title="电量程">
			 	<a class="${desc=="css_electric_mileage" ? "desc" : ""}${asc=="css_electric_mileage" ? "asc" : ""}" href="?${desc=="css_electric_mileage" ? "asc=css_electric_mileage" : ""}${(asc=="css_electric_mileage" || desc!="css_electric_mileage" )? "desc=css_electric_mileage" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	电量程
			 	</a>
			 	${lz:set("checkeds[]","cssElectricMileage")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssEndurance || #request.defines['cssEndurance']!=null">
			 <td <s:if test="#request.defines['cssEndurance']>0">colspan="${defines["cssEndurance"]}" ${all?"width":"iwidth"}="${defines["cssEndurance"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="22" ref="cssEndurance"   title="续航里程">
			 	<a class="${desc=="css_endurance" ? "desc" : ""}${asc=="css_endurance" ? "asc" : ""}" href="?${desc=="css_endurance" ? "asc=css_endurance" : ""}${(asc=="css_endurance" || desc!="css_endurance" )? "desc=css_endurance" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	续航里程
			 	</a>
			 	${lz:set("checkeds[]","cssEndurance")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssTemperature || #request.defines['cssTemperature']!=null">
			 <td <s:if test="#request.defines['cssTemperature']>0">colspan="${defines["cssTemperature"]}" ${all?"width":"iwidth"}="${defines["cssTemperature"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="23" ref="cssTemperature"   title="温度">
			 	<a class="${desc=="css_temperature" ? "desc" : ""}${asc=="css_temperature" ? "asc" : ""}" href="?${desc=="css_temperature" ? "asc=css_temperature" : ""}${(asc=="css_temperature" || desc!="css_temperature" )? "desc=css_temperature" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	温度
			 	</a>
			 	${lz:set("checkeds[]","cssTemperature")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssCsq || #request.defines['cssCsq']!=null">
			 <td <s:if test="#request.defines['cssCsq']>0">colspan="${defines["cssCsq"]}" ${all?"width":"iwidth"}="${defines["cssCsq"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="24" ref="cssCsq"   title="信号强度">
			 	<a class="${desc=="css_csq" ? "desc" : ""}${asc=="css_csq" ? "asc" : ""}" href="?${desc=="css_csq" ? "asc=css_csq" : ""}${(asc=="css_csq" || desc!="css_csq" )? "desc=css_csq" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	信号强度
			 	</a>
			 	${lz:set("checkeds[]","cssCsq")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssPowerConsumption || #request.defines['cssPowerConsumption']!=null">
			 <td <s:if test="#request.defines['cssPowerConsumption']>0">colspan="${defines["cssPowerConsumption"]}" ${all?"width":"iwidth"}="${defines["cssPowerConsumption"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="25" ref="cssPowerConsumption"   title="功耗">
			 	<a class="${desc=="css_power_consumption" ? "desc" : ""}${asc=="css_power_consumption" ? "asc" : ""}" href="?${desc=="css_power_consumption" ? "asc=css_power_consumption" : ""}${(asc=="css_power_consumption" || desc!="css_power_consumption" )? "desc=css_power_consumption" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	功耗
			 	</a>
			 	${lz:set("checkeds[]","cssPowerConsumption")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssLongitude || #request.defines['cssLongitude']!=null">
			 <td <s:if test="#request.defines['cssLongitude']>0">colspan="${defines["cssLongitude"]}" ${all?"width":"iwidth"}="${defines["cssLongitude"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="26" ref="cssLongitude"   title="经度">
			 	<a class="${desc=="css_longitude" ? "desc" : ""}${asc=="css_longitude" ? "asc" : ""}" href="?${desc=="css_longitude" ? "asc=css_longitude" : ""}${(asc=="css_longitude" || desc!="css_longitude" )? "desc=css_longitude" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	经度
			 	</a>
			 	${lz:set("checkeds[]","cssLongitude")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssLatitude || #request.defines['cssLatitude']!=null">
			 <td <s:if test="#request.defines['cssLatitude']>0">colspan="${defines["cssLatitude"]}" ${all?"width":"iwidth"}="${defines["cssLatitude"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="27" ref="cssLatitude"   title="纬度">
			 	<a class="${desc=="css_latitude" ? "desc" : ""}${asc=="css_latitude" ? "asc" : ""}" href="?${desc=="css_latitude" ? "asc=css_latitude" : ""}${(asc=="css_latitude" || desc!="css_latitude" )? "desc=css_latitude" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	纬度
			 	</a>
			 	${lz:set("checkeds[]","cssLatitude")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cssOrder || #request.defines['cssOrder']!=null">
			 <td <s:if test="#request.defines['cssOrder']>0">colspan="${defines["cssOrder"]}" ${all?"width":"iwidth"}="${defines["cssOrder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="28" ref="cssOrder"   title="当前订单">
			 	<a class="${desc=="css_order" ? "desc" : ""}${asc=="css_order" ? "asc" : ""}" href="?${desc=="css_order" ? "asc=css_order" : ""}${(asc=="css_order" || desc!="css_order" )? "desc=css_order" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	当前订单
			 	</a>
			 	${lz:set("checkeds[]","cssOrder")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="29" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="30" ref="operation">
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
		  <tr id="${item.cssId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.cssId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssId || #request.defines['cssId']!=null">
				<s:if test="#request.defines['cssId']>0">
					${lz:set("注释","****cssId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssId",lz:indexOf(fieldName,"cssId")>-1)}
				  		<s:if test="#request.atCssId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssId字段的字串格式化输出****")}
					<td ref="cssId" class="td ">
						 
						 	${lz:or(item$cssId[i.count-1],lz:left(item.cssId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssHost || #request.defines['cssHost']!=null">
				<s:if test="#request.defines['cssHost']>0">
					${lz:set("注释","****cssHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssHost",lz:indexOf(fieldName,"cssHost")>-1)}
				  		<s:if test="#request.atCssHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssHost字段的字串格式化输出****")}
					<td ref="cssHost" class="td " relate="${item.cssHost}">
						 
						 	<a <s:if test="#item.cssHost!=null && #item.cssHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.cssHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cssHost[i.count-1],lz:left(item.cssHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssNumber || #request.defines['cssNumber']!=null">
				<s:if test="#request.defines['cssNumber']>0">
					${lz:set("注释","****cssNumber关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssNumber))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssNumber",lz:indexOf(fieldName,"cssNumber")>-1)}
				  		<s:if test="#request.atCssNumber==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssNumber字段的字串格式化输出****")}
					<td ref="cssNumber" class="td  node">
						 
						 	${lz:or(item$cssNumber[i.count-1],lz:left(item.cssNumber$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssCar || #request.defines['cssCar']!=null">
				<s:if test="#request.defines['cssCar']>0">
					${lz:set("注释","****cssCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssCar))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssCar",lz:indexOf(fieldName,"cssCar")>-1)}
				  		<s:if test="#request.atCssCar==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssCar字段的字串格式化输出****")}
					<td ref="cssCar" class="td " relate="${item.cssCar}">
						 
						 	<a <s:if test="#item.cssCar!=null && #item.cssCar!=''"> onclick="window.href('${basePath}${proname}/object/car_details.do?key=${item.cssCar}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cssCar[i.count-1],lz:left(item.cssCar$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssAddTime || #request.defines['cssAddTime']!=null">
				<s:if test="#request.defines['cssAddTime']>0">
					${lz:set("注释","****cssAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssAddTime",lz:indexOf(fieldName,"cssAddTime")>-1)}
				  		<s:if test="#request.atCssAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssAddTime字段的字串格式化输出****")}
					<td ref="cssAddTime" class="td ">
						 
						 	${lz:or(item$cssAddTime[i.count-1],lz:left(item.cssAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssCurrentTime || #request.defines['cssCurrentTime']!=null">
				<s:if test="#request.defines['cssCurrentTime']>0">
					${lz:set("注释","****cssCurrentTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssCurrentTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssCurrentTime",lz:indexOf(fieldName,"cssCurrentTime")>-1)}
				  		<s:if test="#request.atCssCurrentTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssCurrentTime字段的字串格式化输出****")}
					<td ref="cssCurrentTime" class="td ">
						 
						 	${lz:or(item$cssCurrentTime[i.count-1],lz:left(item.cssCurrentTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssRented || #request.defines['cssRented']!=null">
				<s:if test="#request.defines['cssRented']>0">
					${lz:set("注释","****cssRented关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssRented))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssRented",lz:indexOf(fieldName,"cssRented")>-1)}
				  		<s:if test="#request.atCssRented==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssRented字段的字串格式化输出****")}
					<td ref="cssRented" class="td ">
						 
						 	${lz:or(item$cssRented[i.count-1],lz:left(item.cssRented$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssWarn || #request.defines['cssWarn']!=null">
				<s:if test="#request.defines['cssWarn']>0">
					${lz:set("注释","****cssWarn关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssWarn))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssWarn",lz:indexOf(fieldName,"cssWarn")>-1)}
				  		<s:if test="#request.atCssWarn==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssWarn字段的字串格式化输出****")}
					<td ref="cssWarn" class="td ">
						 
						 	${lz:or(item$cssWarn[i.count-1],lz:left(item.cssWarn$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssRfid || #request.defines['cssRfid']!=null">
				<s:if test="#request.defines['cssRfid']>0">
					${lz:set("注释","****cssRfid关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssRfid))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssRfid",lz:indexOf(fieldName,"cssRfid")>-1)}
				  		<s:if test="#request.atCssRfid==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssRfid字段的字串格式化输出****")}
					<td ref="cssRfid" class="td ">
						 
						 	${lz:or(item$cssRfid[i.count-1],lz:left(item.cssRfid$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssRfidDte || #request.defines['cssRfidDte']!=null">
				<s:if test="#request.defines['cssRfidDte']>0">
					${lz:set("注释","****cssRfidDte关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssRfidDte))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssRfidDte",lz:indexOf(fieldName,"cssRfidDte")>-1)}
				  		<s:if test="#request.atCssRfidDte==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssRfidDte字段的字串格式化输出****")}
					<td ref="cssRfidDte" class="td ">
						 
						 	${lz:or(item$cssRfidDte[i.count-1],lz:left(item.cssRfidDte$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssObdMile || #request.defines['cssObdMile']!=null">
				<s:if test="#request.defines['cssObdMile']>0">
					${lz:set("注释","****cssObdMile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssObdMile))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssObdMile",lz:indexOf(fieldName,"cssObdMile")>-1)}
				  		<s:if test="#request.atCssObdMile==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssObdMile字段的字串格式化输出****")}
					<td ref="cssObdMile" class="td ">
						 
						 	${lz:or(item$cssObdMile[i.count-1],lz:left(item.cssObdMile$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssEngineT || #request.defines['cssEngineT']!=null">
				<s:if test="#request.defines['cssEngineT']>0">
					${lz:set("注释","****cssEngineT关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssEngineT))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssEngineT",lz:indexOf(fieldName,"cssEngineT")>-1)}
				  		<s:if test="#request.atCssEngineT==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssEngineT字段的字串格式化输出****")}
					<td ref="cssEngineT" class="td ">
						 
						 	${lz:or(item$cssEngineT[i.count-1],lz:left(item.cssEngineT$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssMileage || #request.defines['cssMileage']!=null">
				<s:if test="#request.defines['cssMileage']>0">
					${lz:set("注释","****cssMileage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssMileage))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssMileage",lz:indexOf(fieldName,"cssMileage")>-1)}
				  		<s:if test="#request.atCssMileage==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssMileage字段的字串格式化输出****")}
					<td ref="cssMileage" class="td ">
						 
						 	${lz:or(item$cssMileage[i.count-1],lz:left(item.cssMileage$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssSpeed || #request.defines['cssSpeed']!=null">
				<s:if test="#request.defines['cssSpeed']>0">
					${lz:set("注释","****cssSpeed关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssSpeed))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssSpeed",lz:indexOf(fieldName,"cssSpeed")>-1)}
				  		<s:if test="#request.atCssSpeed==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssSpeed字段的字串格式化输出****")}
					<td ref="cssSpeed" class="td ">
						 
						 	${lz:or(item$cssSpeed[i.count-1],lz:left(item.cssSpeed$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssMotor || #request.defines['cssMotor']!=null">
				<s:if test="#request.defines['cssMotor']>0">
					${lz:set("注释","****cssMotor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssMotor))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssMotor",lz:indexOf(fieldName,"cssMotor")>-1)}
				  		<s:if test="#request.atCssMotor==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssMotor字段的字串格式化输出****")}
					<td ref="cssMotor" class="td ">
						 
						 	${lz:or(item$cssMotor[i.count-1],lz:left(item.cssMotor$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssOil || #request.defines['cssOil']!=null">
				<s:if test="#request.defines['cssOil']>0">
					${lz:set("注释","****cssOil关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssOil))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssOil",lz:indexOf(fieldName,"cssOil")>-1)}
				  		<s:if test="#request.atCssOil==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssOil字段的字串格式化输出****")}
					<td ref="cssOil" class="td ">
						 
						 	${lz:or(item$cssOil[i.count-1],lz:left(item.cssOil$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssPower || #request.defines['cssPower']!=null">
				<s:if test="#request.defines['cssPower']>0">
					${lz:set("注释","****cssPower关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssPower))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssPower",lz:indexOf(fieldName,"cssPower")>-1)}
				  		<s:if test="#request.atCssPower==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssPower字段的字串格式化输出****")}
					<td ref="cssPower" class="td ">
						 
						 	${lz:or(item$cssPower[i.count-1],lz:left(item.cssPower$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssEvBattery || #request.defines['cssEvBattery']!=null">
				<s:if test="#request.defines['cssEvBattery']>0">
					${lz:set("注释","****cssEvBattery关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssEvBattery))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssEvBattery",lz:indexOf(fieldName,"cssEvBattery")>-1)}
				  		<s:if test="#request.atCssEvBattery==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssEvBattery字段的字串格式化输出****")}
					<td ref="cssEvBattery" class="td ">
						 
						 	${lz:or(item$cssEvBattery[i.count-1],lz:left(item.cssEvBattery$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssCharging || #request.defines['cssCharging']!=null">
				<s:if test="#request.defines['cssCharging']>0">
					${lz:set("注释","****cssCharging关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssCharging))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssCharging",lz:indexOf(fieldName,"cssCharging")>-1)}
				  		<s:if test="#request.atCssCharging==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssCharging字段的字串格式化输出****")}
					<td ref="cssCharging" class="td ">
						 
						 	${lz:or(item$cssCharging[i.count-1],lz:left(item.cssCharging$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssFuelMileage || #request.defines['cssFuelMileage']!=null">
				<s:if test="#request.defines['cssFuelMileage']>0">
					${lz:set("注释","****cssFuelMileage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssFuelMileage))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssFuelMileage",lz:indexOf(fieldName,"cssFuelMileage")>-1)}
				  		<s:if test="#request.atCssFuelMileage==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssFuelMileage字段的字串格式化输出****")}
					<td ref="cssFuelMileage" class="td ">
						 
						 	${lz:or(item$cssFuelMileage[i.count-1],lz:left(item.cssFuelMileage$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssElectricMileage || #request.defines['cssElectricMileage']!=null">
				<s:if test="#request.defines['cssElectricMileage']>0">
					${lz:set("注释","****cssElectricMileage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssElectricMileage))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssElectricMileage",lz:indexOf(fieldName,"cssElectricMileage")>-1)}
				  		<s:if test="#request.atCssElectricMileage==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssElectricMileage字段的字串格式化输出****")}
					<td ref="cssElectricMileage" class="td ">
						 
						 	${lz:or(item$cssElectricMileage[i.count-1],lz:left(item.cssElectricMileage$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssEndurance || #request.defines['cssEndurance']!=null">
				<s:if test="#request.defines['cssEndurance']>0">
					${lz:set("注释","****cssEndurance关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssEndurance))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssEndurance",lz:indexOf(fieldName,"cssEndurance")>-1)}
				  		<s:if test="#request.atCssEndurance==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssEndurance字段的字串格式化输出****")}
					<td ref="cssEndurance" class="td ">
						 
						 	${lz:or(item$cssEndurance[i.count-1],lz:left(item.cssEndurance$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssTemperature || #request.defines['cssTemperature']!=null">
				<s:if test="#request.defines['cssTemperature']>0">
					${lz:set("注释","****cssTemperature关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssTemperature))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssTemperature",lz:indexOf(fieldName,"cssTemperature")>-1)}
				  		<s:if test="#request.atCssTemperature==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssTemperature字段的字串格式化输出****")}
					<td ref="cssTemperature" class="td ">
						 
						 	${lz:or(item$cssTemperature[i.count-1],lz:left(item.cssTemperature$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssCsq || #request.defines['cssCsq']!=null">
				<s:if test="#request.defines['cssCsq']>0">
					${lz:set("注释","****cssCsq关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssCsq))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssCsq",lz:indexOf(fieldName,"cssCsq")>-1)}
				  		<s:if test="#request.atCssCsq==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssCsq字段的字串格式化输出****")}
					<td ref="cssCsq" class="td ">
						 
						 	${lz:or(item$cssCsq[i.count-1],lz:left(item.cssCsq$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssPowerConsumption || #request.defines['cssPowerConsumption']!=null">
				<s:if test="#request.defines['cssPowerConsumption']>0">
					${lz:set("注释","****cssPowerConsumption关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssPowerConsumption))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssPowerConsumption",lz:indexOf(fieldName,"cssPowerConsumption")>-1)}
				  		<s:if test="#request.atCssPowerConsumption==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssPowerConsumption字段的字串格式化输出****")}
					<td ref="cssPowerConsumption" class="td ">
						 
						 	${lz:or(item$cssPowerConsumption[i.count-1],lz:left(item.cssPowerConsumption$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssLongitude || #request.defines['cssLongitude']!=null">
				<s:if test="#request.defines['cssLongitude']>0">
					${lz:set("注释","****cssLongitude关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssLongitude))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssLongitude",lz:indexOf(fieldName,"cssLongitude")>-1)}
				  		<s:if test="#request.atCssLongitude==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssLongitude字段的字串格式化输出****")}
					<td ref="cssLongitude" class="td ">
						 
						 	${lz:or(item$cssLongitude[i.count-1],lz:left(item.cssLongitude$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssLatitude || #request.defines['cssLatitude']!=null">
				<s:if test="#request.defines['cssLatitude']>0">
					${lz:set("注释","****cssLatitude关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssLatitude))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssLatitude",lz:indexOf(fieldName,"cssLatitude")>-1)}
				  		<s:if test="#request.atCssLatitude==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssLatitude字段的字串格式化输出****")}
					<td ref="cssLatitude" class="td ">
						 
						 	${lz:or(item$cssLatitude[i.count-1],lz:left(item.cssLatitude$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cssOrder || #request.defines['cssOrder']!=null">
				<s:if test="#request.defines['cssOrder']>0">
					${lz:set("注释","****cssOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cssOrder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCssOrder",lz:indexOf(fieldName,"cssOrder")>-1)}
				  		<s:if test="#request.atCssOrder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cssOrder字段的字串格式化输出****")}
					<td ref="cssOrder" class="td " relate="${item.cssOrder}">
						 
						 	<a <s:if test="#item.cssOrder!=null && #item.cssOrder!=''"> onclick="window.href('${basePath}${proname}/service/order_details.do?key=${item.cssOrder}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cssOrder[i.count-1],lz:left(item.cssOrder$,100))}</a>
					</td>
				</s:else>
			</s:if>
		   ${lz:set("注释","*****************数据列表数据部分结束*****************")}		  
		  
		   
		    ${lz:set("注释","****自定义的单行记录操作句柄[request中的handles(String[])变量]****")}
			<td class="options">
				${lz:set("items",lz:split(handles,","))}
				<s:iterator value="#request.items" id="handle" status="i">
					${lz:set("isMenu",(lz:indexOf(handle,"@")==0))}
					<s:if test="#request.isMenu!=true">
					<a href="javascript:HandleSel('${handle}','${item.cssId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectState('${item.cssId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelState('${item.cssId}','${item.cssNumber}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsState('${item.cssId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.cssId || #request.defines['cssId']!=null">
				<s:if test="#request.defines['cssId']>0">
					${lz:set("注释","****cssId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssId",lz:indexOf(fieldName,"cssId")>-1)}				  		
				  		<s:if test="#request.atCssId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssHost || #request.defines['cssHost']!=null">
				<s:if test="#request.defines['cssHost']>0">
					${lz:set("注释","****cssHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssHost",lz:indexOf(fieldName,"cssHost")>-1)}				  		
				  		<s:if test="#request.atCssHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssNumber || #request.defines['cssNumber']!=null">
				<s:if test="#request.defines['cssNumber']>0">
					${lz:set("注释","****cssNumber关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssNumber",lz:indexOf(fieldName,"cssNumber")>-1)}				  		
				  		<s:if test="#request.atCssNumber==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssNumber[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssCar || #request.defines['cssCar']!=null">
				<s:if test="#request.defines['cssCar']>0">
					${lz:set("注释","****cssCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssCar",lz:indexOf(fieldName,"cssCar")>-1)}				  		
				  		<s:if test="#request.atCssCar==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssCar[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssAddTime || #request.defines['cssAddTime']!=null">
				<s:if test="#request.defines['cssAddTime']>0">
					${lz:set("注释","****cssAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssAddTime",lz:indexOf(fieldName,"cssAddTime")>-1)}				  		
				  		<s:if test="#request.atCssAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssCurrentTime || #request.defines['cssCurrentTime']!=null">
				<s:if test="#request.defines['cssCurrentTime']>0">
					${lz:set("注释","****cssCurrentTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssCurrentTime",lz:indexOf(fieldName,"cssCurrentTime")>-1)}				  		
				  		<s:if test="#request.atCssCurrentTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssCurrentTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssRented || #request.defines['cssRented']!=null">
				<s:if test="#request.defines['cssRented']>0">
					${lz:set("注释","****cssRented关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssRented",lz:indexOf(fieldName,"cssRented")>-1)}				  		
				  		<s:if test="#request.atCssRented==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssRented[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssWarn || #request.defines['cssWarn']!=null">
				<s:if test="#request.defines['cssWarn']>0">
					${lz:set("注释","****cssWarn关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssWarn",lz:indexOf(fieldName,"cssWarn")>-1)}				  		
				  		<s:if test="#request.atCssWarn==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssWarn[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssRfid || #request.defines['cssRfid']!=null">
				<s:if test="#request.defines['cssRfid']>0">
					${lz:set("注释","****cssRfid关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssRfid",lz:indexOf(fieldName,"cssRfid")>-1)}				  		
				  		<s:if test="#request.atCssRfid==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssRfid[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssRfidDte || #request.defines['cssRfidDte']!=null">
				<s:if test="#request.defines['cssRfidDte']>0">
					${lz:set("注释","****cssRfidDte关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssRfidDte",lz:indexOf(fieldName,"cssRfidDte")>-1)}				  		
				  		<s:if test="#request.atCssRfidDte==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssRfidDte[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssObdMile || #request.defines['cssObdMile']!=null">
				<s:if test="#request.defines['cssObdMile']>0">
					${lz:set("注释","****cssObdMile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssObdMile",lz:indexOf(fieldName,"cssObdMile")>-1)}				  		
				  		<s:if test="#request.atCssObdMile==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssObdMile[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssEngineT || #request.defines['cssEngineT']!=null">
				<s:if test="#request.defines['cssEngineT']>0">
					${lz:set("注释","****cssEngineT关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssEngineT",lz:indexOf(fieldName,"cssEngineT")>-1)}				  		
				  		<s:if test="#request.atCssEngineT==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssEngineT[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssMileage || #request.defines['cssMileage']!=null">
				<s:if test="#request.defines['cssMileage']>0">
					${lz:set("注释","****cssMileage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssMileage",lz:indexOf(fieldName,"cssMileage")>-1)}				  		
				  		<s:if test="#request.atCssMileage==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssMileage[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssSpeed || #request.defines['cssSpeed']!=null">
				<s:if test="#request.defines['cssSpeed']>0">
					${lz:set("注释","****cssSpeed关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssSpeed",lz:indexOf(fieldName,"cssSpeed")>-1)}				  		
				  		<s:if test="#request.atCssSpeed==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssSpeed[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssMotor || #request.defines['cssMotor']!=null">
				<s:if test="#request.defines['cssMotor']>0">
					${lz:set("注释","****cssMotor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssMotor",lz:indexOf(fieldName,"cssMotor")>-1)}				  		
				  		<s:if test="#request.atCssMotor==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssMotor[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssOil || #request.defines['cssOil']!=null">
				<s:if test="#request.defines['cssOil']>0">
					${lz:set("注释","****cssOil关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssOil",lz:indexOf(fieldName,"cssOil")>-1)}				  		
				  		<s:if test="#request.atCssOil==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssOil[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssPower || #request.defines['cssPower']!=null">
				<s:if test="#request.defines['cssPower']>0">
					${lz:set("注释","****cssPower关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssPower",lz:indexOf(fieldName,"cssPower")>-1)}				  		
				  		<s:if test="#request.atCssPower==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssPower[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssEvBattery || #request.defines['cssEvBattery']!=null">
				<s:if test="#request.defines['cssEvBattery']>0">
					${lz:set("注释","****cssEvBattery关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssEvBattery",lz:indexOf(fieldName,"cssEvBattery")>-1)}				  		
				  		<s:if test="#request.atCssEvBattery==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssEvBattery[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssCharging || #request.defines['cssCharging']!=null">
				<s:if test="#request.defines['cssCharging']>0">
					${lz:set("注释","****cssCharging关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssCharging",lz:indexOf(fieldName,"cssCharging")>-1)}				  		
				  		<s:if test="#request.atCssCharging==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssCharging[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssFuelMileage || #request.defines['cssFuelMileage']!=null">
				<s:if test="#request.defines['cssFuelMileage']>0">
					${lz:set("注释","****cssFuelMileage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssFuelMileage",lz:indexOf(fieldName,"cssFuelMileage")>-1)}				  		
				  		<s:if test="#request.atCssFuelMileage==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssFuelMileage[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssElectricMileage || #request.defines['cssElectricMileage']!=null">
				<s:if test="#request.defines['cssElectricMileage']>0">
					${lz:set("注释","****cssElectricMileage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssElectricMileage",lz:indexOf(fieldName,"cssElectricMileage")>-1)}				  		
				  		<s:if test="#request.atCssElectricMileage==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssElectricMileage[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssEndurance || #request.defines['cssEndurance']!=null">
				<s:if test="#request.defines['cssEndurance']>0">
					${lz:set("注释","****cssEndurance关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssEndurance",lz:indexOf(fieldName,"cssEndurance")>-1)}				  		
				  		<s:if test="#request.atCssEndurance==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssEndurance[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssTemperature || #request.defines['cssTemperature']!=null">
				<s:if test="#request.defines['cssTemperature']>0">
					${lz:set("注释","****cssTemperature关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssTemperature",lz:indexOf(fieldName,"cssTemperature")>-1)}				  		
				  		<s:if test="#request.atCssTemperature==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssTemperature[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssCsq || #request.defines['cssCsq']!=null">
				<s:if test="#request.defines['cssCsq']>0">
					${lz:set("注释","****cssCsq关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssCsq",lz:indexOf(fieldName,"cssCsq")>-1)}				  		
				  		<s:if test="#request.atCssCsq==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssCsq[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssPowerConsumption || #request.defines['cssPowerConsumption']!=null">
				<s:if test="#request.defines['cssPowerConsumption']>0">
					${lz:set("注释","****cssPowerConsumption关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssPowerConsumption",lz:indexOf(fieldName,"cssPowerConsumption")>-1)}				  		
				  		<s:if test="#request.atCssPowerConsumption==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssPowerConsumption[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssLongitude || #request.defines['cssLongitude']!=null">
				<s:if test="#request.defines['cssLongitude']>0">
					${lz:set("注释","****cssLongitude关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssLongitude",lz:indexOf(fieldName,"cssLongitude")>-1)}				  		
				  		<s:if test="#request.atCssLongitude==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssLongitude[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssLatitude || #request.defines['cssLatitude']!=null">
				<s:if test="#request.defines['cssLatitude']>0">
					${lz:set("注释","****cssLatitude关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssLatitude",lz:indexOf(fieldName,"cssLatitude")>-1)}				  		
				  		<s:if test="#request.atCssLatitude==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssLatitude[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cssOrder || #request.defines['cssOrder']!=null">
				<s:if test="#request.defines['cssOrder']>0">
					${lz:set("注释","****cssOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCssOrder",lz:indexOf(fieldName,"cssOrder")>-1)}				  		
				  		<s:if test="#request.atCssOrder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cssOrder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 选择车辆当前状态
	**/
	function SelectState(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改车辆当前状态任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}state_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新车辆当前状态",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}state_edit.do";
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
			var url = "${basePath}${namespace}state_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除车辆当前状态
	**/
	function DelState(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除车辆当前状态["+flag+"]吗？</span>",
				title:"删除车辆当前状态",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}state_del.do";
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
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除所选车辆当前状态吗？</span>",
				title:"批量删除车辆当前状态",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}state_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示车辆当前状态详情
	**/
	function DetailsState(id)
	{
		var url = "${basePath}${namespace}state_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"车辆当前状态详情",editable:false,visible:true}};
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
		DetailsState(checker.val());
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