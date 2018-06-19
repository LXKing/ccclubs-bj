<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/control/history.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csHistoryState.cshsId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"cshsId,cshsHost,cshsNumber,cshsAddTime,cshsMileage,cshsOrder,cshsMoData",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"cshsId,cshsHost,cshsNumber,cshsAddTime,cshsCurrentTime,cshsRented,cshsWarn,cshsRfid,cshsRfidDte,cshsObdMile,cshsEngineT,cshsMileage,cshsSpeed,cshsMotor,cshsOil,cshsPower,cshsEvBattery,cshsCharging,cshsFuelMileage,cshsElectricMileage,cshsEndurance,cshsTemperature,cshsCsq,cshsPowerConsumption,cshsLongitude,cshsLatitude,cshsOrder,cshsMoData",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"cshsHost,cshsNumber,cshsAddTime,cshsMileage,cshsOrder",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"cshsHost,cshsNumber,cshsAddTime,cshsRented,cshsRfid,cshsMileage,cshsSpeed,cshsMotor,cshsOil,cshsPower,cshsLongitude,cshsLatitude,cshsOrder",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>车辆历史状态管理</title>
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
<script type="text/javascript">
<!--
$(function(){
	if(${empty cshsNumber})
		$("#cshsNumber").prev("span").tooltip("请在这里选择一个车牌进行查询");
});

//-->
</script>
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
	<span class="caption">${empty title?"车辆历史状态管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsHistoryState" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="history.query.jsp"%>

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
			<dl ref="com.ccclubs.model.CsHistoryState" class="display dropdowan" style="margin-left:30px;">
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
		<table id="history_table" ref="CsHistoryState" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsId || #request.defines['cshsId']!=null">
			 <td <s:if test="#request.defines['cshsId']>0">colspan="${defines["cshsId"]}" ${all?"width":"iwidth"}="${defines["cshsId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="cshsId"   title="编号">
			 	<a class="${desc=="cshs_id" ? "desc" : ""}${asc=="cshs_id" ? "asc" : ""}" href="?${desc=="cshs_id" ? "asc=cshs_id" : ""}${(asc=="cshs_id" || desc!="cshs_id" )? "desc=cshs_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","cshsId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsHost || #request.defines['cshsHost']!=null">
			 <td <s:if test="#request.defines['cshsHost']>0">colspan="${defines["cshsHost"]}" ${all?"width":"iwidth"}="${defines["cshsHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="cshsHost"   title="城市">
			 	<a class="${desc=="cshs_host" ? "desc" : ""}${asc=="cshs_host" ? "asc" : ""}" href="?${desc=="cshs_host" ? "asc=cshs_host" : ""}${(asc=="cshs_host" || desc!="cshs_host" )? "desc=cshs_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","cshsHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsNumber || #request.defines['cshsNumber']!=null">
			 <td <s:if test="#request.defines['cshsNumber']>0">colspan="${defines["cshsNumber"]}" ${all?"width":"iwidth"}="${defines["cshsNumber"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="3" ref="cshsNumber" flagKey  title="车牌号">
			 	<a class="${desc=="cshs_number" ? "desc" : ""}${asc=="cshs_number" ? "asc" : ""}" href="?${desc=="cshs_number" ? "asc=cshs_number" : ""}${(asc=="cshs_number" || desc!="cshs_number" )? "desc=cshs_number" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车牌号
			 	</a>
			 	${lz:set("checkeds[]","cshsNumber")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsAddTime || #request.defines['cshsAddTime']!=null">
			 <td <s:if test="#request.defines['cshsAddTime']>0">colspan="${defines["cshsAddTime"]}" ${all?"width":"iwidth"}="${defines["cshsAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="4" ref="cshsAddTime"   title="添加时间">
			 	<a class="${desc=="cshs_add_time" ? "desc" : ""}${asc=="cshs_add_time" ? "asc" : ""}" href="?${desc=="cshs_add_time" ? "asc=cshs_add_time" : ""}${(asc=="cshs_add_time" || desc!="cshs_add_time" )? "desc=cshs_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","cshsAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsCurrentTime || #request.defines['cshsCurrentTime']!=null">
			 <td <s:if test="#request.defines['cshsCurrentTime']>0">colspan="${defines["cshsCurrentTime"]}" ${all?"width":"iwidth"}="${defines["cshsCurrentTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="5" ref="cshsCurrentTime"   title="下位机时间">
			 	<a class="${desc=="cshs_current_time" ? "desc" : ""}${asc=="cshs_current_time" ? "asc" : ""}" href="?${desc=="cshs_current_time" ? "asc=cshs_current_time" : ""}${(asc=="cshs_current_time" || desc!="cshs_current_time" )? "desc=cshs_current_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	下位机时间
			 	</a>
			 	${lz:set("checkeds[]","cshsCurrentTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsRented || #request.defines['cshsRented']!=null">
			 <td <s:if test="#request.defines['cshsRented']>0">colspan="${defines["cshsRented"]}" ${all?"width":"iwidth"}="${defines["cshsRented"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="cshsRented"   title="租赁状态">
			 	<a class="${desc=="cshs_rented" ? "desc" : ""}${asc=="cshs_rented" ? "asc" : ""}" href="?${desc=="cshs_rented" ? "asc=cshs_rented" : ""}${(asc=="cshs_rented" || desc!="cshs_rented" )? "desc=cshs_rented" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	租赁状态
			 	</a>
			 	${lz:set("checkeds[]","cshsRented")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsWarn || #request.defines['cshsWarn']!=null">
			 <td <s:if test="#request.defines['cshsWarn']>0">colspan="${defines["cshsWarn"]}" ${all?"width":"iwidth"}="${defines["cshsWarn"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="7" ref="cshsWarn"   title="有报警">
			 	<a class="${desc=="cshs_warn" ? "desc" : ""}${asc=="cshs_warn" ? "asc" : ""}" href="?${desc=="cshs_warn" ? "asc=cshs_warn" : ""}${(asc=="cshs_warn" || desc!="cshs_warn" )? "desc=cshs_warn" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	有报警
			 	</a>
			 	${lz:set("checkeds[]","cshsWarn")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsRfid || #request.defines['cshsRfid']!=null">
			 <td <s:if test="#request.defines['cshsRfid']>0">colspan="${defines["cshsRfid"]}" ${all?"width":"iwidth"}="${defines["cshsRfid"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="8" ref="cshsRfid"   title="RFID卡号">
			 	<a class="${desc=="cshs_rfid" ? "desc" : ""}${asc=="cshs_rfid" ? "asc" : ""}" href="?${desc=="cshs_rfid" ? "asc=cshs_rfid" : ""}${(asc=="cshs_rfid" || desc!="cshs_rfid" )? "desc=cshs_rfid" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	RFID卡号
			 	</a>
			 	${lz:set("checkeds[]","cshsRfid")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsRfidDte || #request.defines['cshsRfidDte']!=null">
			 <td <s:if test="#request.defines['cshsRfidDte']>0">colspan="${defines["cshsRfidDte"]}" ${all?"width":"iwidth"}="${defines["cshsRfidDte"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="9" ref="cshsRfidDte"   title="用户RFID">
			 	<a class="${desc=="cshs_rfid_dte" ? "desc" : ""}${asc=="cshs_rfid_dte" ? "asc" : ""}" href="?${desc=="cshs_rfid_dte" ? "asc=cshs_rfid_dte" : ""}${(asc=="cshs_rfid_dte" || desc!="cshs_rfid_dte" )? "desc=cshs_rfid_dte" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	用户RFID
			 	</a>
			 	${lz:set("checkeds[]","cshsRfidDte")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsObdMile || #request.defines['cshsObdMile']!=null">
			 <td <s:if test="#request.defines['cshsObdMile']>0">colspan="${defines["cshsObdMile"]}" ${all?"width":"iwidth"}="${defines["cshsObdMile"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="10" ref="cshsObdMile"   title="OBD里程">
			 	<a class="${desc=="cshs_obd_mile" ? "desc" : ""}${asc=="cshs_obd_mile" ? "asc" : ""}" href="?${desc=="cshs_obd_mile" ? "asc=cshs_obd_mile" : ""}${(asc=="cshs_obd_mile" || desc!="cshs_obd_mile" )? "desc=cshs_obd_mile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	OBD里程
			 	</a>
			 	${lz:set("checkeds[]","cshsObdMile")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsEngineT || #request.defines['cshsEngineT']!=null">
			 <td <s:if test="#request.defines['cshsEngineT']>0">colspan="${defines["cshsEngineT"]}" ${all?"width":"iwidth"}="${defines["cshsEngineT"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="11" ref="cshsEngineT"   title="发动机温度">
			 	<a class="${desc=="cshs_engine_t" ? "desc" : ""}${asc=="cshs_engine_t" ? "asc" : ""}" href="?${desc=="cshs_engine_t" ? "asc=cshs_engine_t" : ""}${(asc=="cshs_engine_t" || desc!="cshs_engine_t" )? "desc=cshs_engine_t" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	发动机温度
			 	</a>
			 	${lz:set("checkeds[]","cshsEngineT")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsMileage || #request.defines['cshsMileage']!=null">
			 <td <s:if test="#request.defines['cshsMileage']>0">colspan="${defines["cshsMileage"]}" ${all?"width":"iwidth"}="${defines["cshsMileage"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="12" ref="cshsMileage"   title="总里程">
			 	<a class="${desc=="cshs_mileage" ? "desc" : ""}${asc=="cshs_mileage" ? "asc" : ""}" href="?${desc=="cshs_mileage" ? "asc=cshs_mileage" : ""}${(asc=="cshs_mileage" || desc!="cshs_mileage" )? "desc=cshs_mileage" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	总里程
			 	</a>
			 	${lz:set("checkeds[]","cshsMileage")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsSpeed || #request.defines['cshsSpeed']!=null">
			 <td <s:if test="#request.defines['cshsSpeed']>0">colspan="${defines["cshsSpeed"]}" ${all?"width":"iwidth"}="${defines["cshsSpeed"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="13" ref="cshsSpeed"   title="车速">
			 	<a class="${desc=="cshs_speed" ? "desc" : ""}${asc=="cshs_speed" ? "asc" : ""}" href="?${desc=="cshs_speed" ? "asc=cshs_speed" : ""}${(asc=="cshs_speed" || desc!="cshs_speed" )? "desc=cshs_speed" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车速
			 	</a>
			 	${lz:set("checkeds[]","cshsSpeed")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsMotor || #request.defines['cshsMotor']!=null">
			 <td <s:if test="#request.defines['cshsMotor']>0">colspan="${defines["cshsMotor"]}" ${all?"width":"iwidth"}="${defines["cshsMotor"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="14" ref="cshsMotor"   title="转速">
			 	<a class="${desc=="cshs_motor" ? "desc" : ""}${asc=="cshs_motor" ? "asc" : ""}" href="?${desc=="cshs_motor" ? "asc=cshs_motor" : ""}${(asc=="cshs_motor" || desc!="cshs_motor" )? "desc=cshs_motor" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	转速
			 	</a>
			 	${lz:set("checkeds[]","cshsMotor")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsOil || #request.defines['cshsOil']!=null">
			 <td <s:if test="#request.defines['cshsOil']>0">colspan="${defines["cshsOil"]}" ${all?"width":"iwidth"}="${defines["cshsOil"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="15" ref="cshsOil"   title="燃油量">
			 	<a class="${desc=="cshs_oil" ? "desc" : ""}${asc=="cshs_oil" ? "asc" : ""}" href="?${desc=="cshs_oil" ? "asc=cshs_oil" : ""}${(asc=="cshs_oil" || desc!="cshs_oil" )? "desc=cshs_oil" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	燃油量
			 	</a>
			 	${lz:set("checkeds[]","cshsOil")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsPower || #request.defines['cshsPower']!=null">
			 <td <s:if test="#request.defines['cshsPower']>0">colspan="${defines["cshsPower"]}" ${all?"width":"iwidth"}="${defines["cshsPower"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="16" ref="cshsPower"   title="蓄电量">
			 	<a class="${desc=="cshs_power" ? "desc" : ""}${asc=="cshs_power" ? "asc" : ""}" href="?${desc=="cshs_power" ? "asc=cshs_power" : ""}${(asc=="cshs_power" || desc!="cshs_power" )? "desc=cshs_power" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	蓄电量
			 	</a>
			 	${lz:set("checkeds[]","cshsPower")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsEvBattery || #request.defines['cshsEvBattery']!=null">
			 <td <s:if test="#request.defines['cshsEvBattery']>0">colspan="${defines["cshsEvBattery"]}" ${all?"width":"iwidth"}="${defines["cshsEvBattery"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="17" ref="cshsEvBattery"   title="动力电池电量">
			 	<a class="${desc=="cshs_ev_battery" ? "desc" : ""}${asc=="cshs_ev_battery" ? "asc" : ""}" href="?${desc=="cshs_ev_battery" ? "asc=cshs_ev_battery" : ""}${(asc=="cshs_ev_battery" || desc!="cshs_ev_battery" )? "desc=cshs_ev_battery" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	动力电池电量
			 	</a>
			 	${lz:set("checkeds[]","cshsEvBattery")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsCharging || #request.defines['cshsCharging']!=null">
			 <td <s:if test="#request.defines['cshsCharging']>0">colspan="${defines["cshsCharging"]}" ${all?"width":"iwidth"}="${defines["cshsCharging"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="18" ref="cshsCharging"   title="在充电">
			 	<a class="${desc=="cshs_charging" ? "desc" : ""}${asc=="cshs_charging" ? "asc" : ""}" href="?${desc=="cshs_charging" ? "asc=cshs_charging" : ""}${(asc=="cshs_charging" || desc!="cshs_charging" )? "desc=cshs_charging" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	在充电
			 	</a>
			 	${lz:set("checkeds[]","cshsCharging")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsFuelMileage || #request.defines['cshsFuelMileage']!=null">
			 <td <s:if test="#request.defines['cshsFuelMileage']>0">colspan="${defines["cshsFuelMileage"]}" ${all?"width":"iwidth"}="${defines["cshsFuelMileage"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="19" ref="cshsFuelMileage"   title="油里程">
			 	<a class="${desc=="cshs_fuel_mileage" ? "desc" : ""}${asc=="cshs_fuel_mileage" ? "asc" : ""}" href="?${desc=="cshs_fuel_mileage" ? "asc=cshs_fuel_mileage" : ""}${(asc=="cshs_fuel_mileage" || desc!="cshs_fuel_mileage" )? "desc=cshs_fuel_mileage" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	油里程
			 	</a>
			 	${lz:set("checkeds[]","cshsFuelMileage")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsElectricMileage || #request.defines['cshsElectricMileage']!=null">
			 <td <s:if test="#request.defines['cshsElectricMileage']>0">colspan="${defines["cshsElectricMileage"]}" ${all?"width":"iwidth"}="${defines["cshsElectricMileage"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="20" ref="cshsElectricMileage"   title="电量程">
			 	<a class="${desc=="cshs_electric_mileage" ? "desc" : ""}${asc=="cshs_electric_mileage" ? "asc" : ""}" href="?${desc=="cshs_electric_mileage" ? "asc=cshs_electric_mileage" : ""}${(asc=="cshs_electric_mileage" || desc!="cshs_electric_mileage" )? "desc=cshs_electric_mileage" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	电量程
			 	</a>
			 	${lz:set("checkeds[]","cshsElectricMileage")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsEndurance || #request.defines['cshsEndurance']!=null">
			 <td <s:if test="#request.defines['cshsEndurance']>0">colspan="${defines["cshsEndurance"]}" ${all?"width":"iwidth"}="${defines["cshsEndurance"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="21" ref="cshsEndurance"   title="续航里程">
			 	<a class="${desc=="cshs_endurance" ? "desc" : ""}${asc=="cshs_endurance" ? "asc" : ""}" href="?${desc=="cshs_endurance" ? "asc=cshs_endurance" : ""}${(asc=="cshs_endurance" || desc!="cshs_endurance" )? "desc=cshs_endurance" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	续航里程
			 	</a>
			 	${lz:set("checkeds[]","cshsEndurance")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsTemperature || #request.defines['cshsTemperature']!=null">
			 <td <s:if test="#request.defines['cshsTemperature']>0">colspan="${defines["cshsTemperature"]}" ${all?"width":"iwidth"}="${defines["cshsTemperature"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="22" ref="cshsTemperature"   title="温度">
			 	<a class="${desc=="cshs_temperature" ? "desc" : ""}${asc=="cshs_temperature" ? "asc" : ""}" href="?${desc=="cshs_temperature" ? "asc=cshs_temperature" : ""}${(asc=="cshs_temperature" || desc!="cshs_temperature" )? "desc=cshs_temperature" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	温度
			 	</a>
			 	${lz:set("checkeds[]","cshsTemperature")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsCsq || #request.defines['cshsCsq']!=null">
			 <td <s:if test="#request.defines['cshsCsq']>0">colspan="${defines["cshsCsq"]}" ${all?"width":"iwidth"}="${defines["cshsCsq"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="23" ref="cshsCsq"   title="信号强度">
			 	<a class="${desc=="cshs_csq" ? "desc" : ""}${asc=="cshs_csq" ? "asc" : ""}" href="?${desc=="cshs_csq" ? "asc=cshs_csq" : ""}${(asc=="cshs_csq" || desc!="cshs_csq" )? "desc=cshs_csq" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	信号强度
			 	</a>
			 	${lz:set("checkeds[]","cshsCsq")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsPowerConsumption || #request.defines['cshsPowerConsumption']!=null">
			 <td <s:if test="#request.defines['cshsPowerConsumption']>0">colspan="${defines["cshsPowerConsumption"]}" ${all?"width":"iwidth"}="${defines["cshsPowerConsumption"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="24" ref="cshsPowerConsumption"   title="功耗">
			 	<a class="${desc=="cshs_power_consumption" ? "desc" : ""}${asc=="cshs_power_consumption" ? "asc" : ""}" href="?${desc=="cshs_power_consumption" ? "asc=cshs_power_consumption" : ""}${(asc=="cshs_power_consumption" || desc!="cshs_power_consumption" )? "desc=cshs_power_consumption" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	功耗
			 	</a>
			 	${lz:set("checkeds[]","cshsPowerConsumption")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsLongitude || #request.defines['cshsLongitude']!=null">
			 <td <s:if test="#request.defines['cshsLongitude']>0">colspan="${defines["cshsLongitude"]}" ${all?"width":"iwidth"}="${defines["cshsLongitude"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="25" ref="cshsLongitude"   title="经度">
			 	<a class="${desc=="cshs_longitude" ? "desc" : ""}${asc=="cshs_longitude" ? "asc" : ""}" href="?${desc=="cshs_longitude" ? "asc=cshs_longitude" : ""}${(asc=="cshs_longitude" || desc!="cshs_longitude" )? "desc=cshs_longitude" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	经度
			 	</a>
			 	${lz:set("checkeds[]","cshsLongitude")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsLatitude || #request.defines['cshsLatitude']!=null">
			 <td <s:if test="#request.defines['cshsLatitude']>0">colspan="${defines["cshsLatitude"]}" ${all?"width":"iwidth"}="${defines["cshsLatitude"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="26" ref="cshsLatitude"   title="纬度">
			 	<a class="${desc=="cshs_latitude" ? "desc" : ""}${asc=="cshs_latitude" ? "asc" : ""}" href="?${desc=="cshs_latitude" ? "asc=cshs_latitude" : ""}${(asc=="cshs_latitude" || desc!="cshs_latitude" )? "desc=cshs_latitude" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	纬度
			 	</a>
			 	${lz:set("checkeds[]","cshsLatitude")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsOrder || #request.defines['cshsOrder']!=null">
			 <td <s:if test="#request.defines['cshsOrder']>0">colspan="${defines["cshsOrder"]}" ${all?"width":"iwidth"}="${defines["cshsOrder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="27" ref="cshsOrder"   title="当前订单">
			 	<a class="${desc=="cshs_order" ? "desc" : ""}${asc=="cshs_order" ? "asc" : ""}" href="?${desc=="cshs_order" ? "asc=cshs_order" : ""}${(asc=="cshs_order" || desc!="cshs_order" )? "desc=cshs_order" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	当前订单
			 	</a>
			 	${lz:set("checkeds[]","cshsOrder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cshsMoData || #request.defines['cshsMoData']!=null">
			 <td <s:if test="#request.defines['cshsMoData']>0">colspan="${defines["cshsMoData"]}" ${all?"width":"iwidth"}="${defines["cshsMoData"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="28" ref="cshsMoData"   title="接口数据">
			 	<a class="${desc=="cshs_mo_data" ? "desc" : ""}${asc=="cshs_mo_data" ? "asc" : ""}" href="?${desc=="cshs_mo_data" ? "asc=cshs_mo_data" : ""}${(asc=="cshs_mo_data" || desc!="cshs_mo_data" )? "desc=cshs_mo_data" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	接口数据
			 	</a>
			 	${lz:set("checkeds[]","cshsMoData")}
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
		  <tr id="${item.cshsId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.cshsId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsId || #request.defines['cshsId']!=null">
				<s:if test="#request.defines['cshsId']>0">
					${lz:set("注释","****cshsId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsId",lz:indexOf(fieldName,"cshsId")>-1)}
				  		<s:if test="#request.atCshsId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsId字段的字串格式化输出****")}
					<td ref="cshsId" class="td ">
						 
						 	${lz:or(item$cshsId[i.count-1],lz:left(item.cshsId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsHost || #request.defines['cshsHost']!=null">
				<s:if test="#request.defines['cshsHost']>0">
					${lz:set("注释","****cshsHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsHost",lz:indexOf(fieldName,"cshsHost")>-1)}
				  		<s:if test="#request.atCshsHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsHost字段的字串格式化输出****")}
					<td ref="cshsHost" class="td " relate="${item.cshsHost}">
						 
						 	<a <s:if test="#item.cshsHost!=null && #item.cshsHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.cshsHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cshsHost[i.count-1],lz:left(item.cshsHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsNumber || #request.defines['cshsNumber']!=null">
				<s:if test="#request.defines['cshsNumber']>0">
					${lz:set("注释","****cshsNumber关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsNumber))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsNumber",lz:indexOf(fieldName,"cshsNumber")>-1)}
				  		<s:if test="#request.atCshsNumber==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsNumber字段的字串格式化输出****")}
					<td ref="cshsNumber" class="td  node">
						 
						 	${lz:or(item$cshsNumber[i.count-1],lz:left(item.cshsNumber$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsAddTime || #request.defines['cshsAddTime']!=null">
				<s:if test="#request.defines['cshsAddTime']>0">
					${lz:set("注释","****cshsAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsAddTime",lz:indexOf(fieldName,"cshsAddTime")>-1)}
				  		<s:if test="#request.atCshsAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsAddTime字段的字串格式化输出****")}
					<td ref="cshsAddTime" class="td ">
						 
						 	${lz:or(item$cshsAddTime[i.count-1],lz:left(item.cshsAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsCurrentTime || #request.defines['cshsCurrentTime']!=null">
				<s:if test="#request.defines['cshsCurrentTime']>0">
					${lz:set("注释","****cshsCurrentTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsCurrentTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsCurrentTime",lz:indexOf(fieldName,"cshsCurrentTime")>-1)}
				  		<s:if test="#request.atCshsCurrentTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsCurrentTime字段的字串格式化输出****")}
					<td ref="cshsCurrentTime" class="td ">
						 
						 	${lz:or(item$cshsCurrentTime[i.count-1],lz:left(item.cshsCurrentTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsRented || #request.defines['cshsRented']!=null">
				<s:if test="#request.defines['cshsRented']>0">
					${lz:set("注释","****cshsRented关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsRented))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsRented",lz:indexOf(fieldName,"cshsRented")>-1)}
				  		<s:if test="#request.atCshsRented==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsRented字段的字串格式化输出****")}
					<td ref="cshsRented" class="td ">
						 
						 	${lz:or(item$cshsRented[i.count-1],lz:left(item.cshsRented$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsWarn || #request.defines['cshsWarn']!=null">
				<s:if test="#request.defines['cshsWarn']>0">
					${lz:set("注释","****cshsWarn关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsWarn))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsWarn",lz:indexOf(fieldName,"cshsWarn")>-1)}
				  		<s:if test="#request.atCshsWarn==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsWarn字段的字串格式化输出****")}
					<td ref="cshsWarn" class="td ">
						 
						 	${lz:or(item$cshsWarn[i.count-1],lz:left(item.cshsWarn$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsRfid || #request.defines['cshsRfid']!=null">
				<s:if test="#request.defines['cshsRfid']>0">
					${lz:set("注释","****cshsRfid关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsRfid))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsRfid",lz:indexOf(fieldName,"cshsRfid")>-1)}
				  		<s:if test="#request.atCshsRfid==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsRfid字段的字串格式化输出****")}
					<td ref="cshsRfid" class="td ">
						 
						 	${lz:or(item$cshsRfid[i.count-1],lz:left(item.cshsRfid$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsRfidDte || #request.defines['cshsRfidDte']!=null">
				<s:if test="#request.defines['cshsRfidDte']>0">
					${lz:set("注释","****cshsRfidDte关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsRfidDte))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsRfidDte",lz:indexOf(fieldName,"cshsRfidDte")>-1)}
				  		<s:if test="#request.atCshsRfidDte==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsRfidDte字段的字串格式化输出****")}
					<td ref="cshsRfidDte" class="td ">
						 
						 	${lz:or(item$cshsRfidDte[i.count-1],lz:left(item.cshsRfidDte$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsObdMile || #request.defines['cshsObdMile']!=null">
				<s:if test="#request.defines['cshsObdMile']>0">
					${lz:set("注释","****cshsObdMile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsObdMile))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsObdMile",lz:indexOf(fieldName,"cshsObdMile")>-1)}
				  		<s:if test="#request.atCshsObdMile==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsObdMile字段的字串格式化输出****")}
					<td ref="cshsObdMile" class="td ">
						 
						 	${lz:or(item$cshsObdMile[i.count-1],lz:left(item.cshsObdMile$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsEngineT || #request.defines['cshsEngineT']!=null">
				<s:if test="#request.defines['cshsEngineT']>0">
					${lz:set("注释","****cshsEngineT关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsEngineT))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsEngineT",lz:indexOf(fieldName,"cshsEngineT")>-1)}
				  		<s:if test="#request.atCshsEngineT==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsEngineT字段的字串格式化输出****")}
					<td ref="cshsEngineT" class="td ">
						 
						 	${lz:or(item$cshsEngineT[i.count-1],lz:left(item.cshsEngineT$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsMileage || #request.defines['cshsMileage']!=null">
				<s:if test="#request.defines['cshsMileage']>0">
					${lz:set("注释","****cshsMileage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsMileage))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsMileage",lz:indexOf(fieldName,"cshsMileage")>-1)}
				  		<s:if test="#request.atCshsMileage==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsMileage字段的字串格式化输出****")}
					<td ref="cshsMileage" class="td ">
						 
						 	${lz:or(item$cshsMileage[i.count-1],lz:left(item.cshsMileage$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsSpeed || #request.defines['cshsSpeed']!=null">
				<s:if test="#request.defines['cshsSpeed']>0">
					${lz:set("注释","****cshsSpeed关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsSpeed))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsSpeed",lz:indexOf(fieldName,"cshsSpeed")>-1)}
				  		<s:if test="#request.atCshsSpeed==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsSpeed字段的字串格式化输出****")}
					<td ref="cshsSpeed" class="td ">
						 
						 	${lz:or(item$cshsSpeed[i.count-1],lz:left(item.cshsSpeed$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsMotor || #request.defines['cshsMotor']!=null">
				<s:if test="#request.defines['cshsMotor']>0">
					${lz:set("注释","****cshsMotor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsMotor))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsMotor",lz:indexOf(fieldName,"cshsMotor")>-1)}
				  		<s:if test="#request.atCshsMotor==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsMotor字段的字串格式化输出****")}
					<td ref="cshsMotor" class="td ">
						 
						 	${lz:or(item$cshsMotor[i.count-1],lz:left(item.cshsMotor$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsOil || #request.defines['cshsOil']!=null">
				<s:if test="#request.defines['cshsOil']>0">
					${lz:set("注释","****cshsOil关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsOil))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsOil",lz:indexOf(fieldName,"cshsOil")>-1)}
				  		<s:if test="#request.atCshsOil==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsOil字段的字串格式化输出****")}
					<td ref="cshsOil" class="td ">
						 
						 	${lz:or(item$cshsOil[i.count-1],lz:left(item.cshsOil$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsPower || #request.defines['cshsPower']!=null">
				<s:if test="#request.defines['cshsPower']>0">
					${lz:set("注释","****cshsPower关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsPower))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsPower",lz:indexOf(fieldName,"cshsPower")>-1)}
				  		<s:if test="#request.atCshsPower==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsPower字段的字串格式化输出****")}
					<td ref="cshsPower" class="td ">
						 
						 	${lz:or(item$cshsPower[i.count-1],lz:left(item.cshsPower$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsEvBattery || #request.defines['cshsEvBattery']!=null">
				<s:if test="#request.defines['cshsEvBattery']>0">
					${lz:set("注释","****cshsEvBattery关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsEvBattery))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsEvBattery",lz:indexOf(fieldName,"cshsEvBattery")>-1)}
				  		<s:if test="#request.atCshsEvBattery==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsEvBattery字段的字串格式化输出****")}
					<td ref="cshsEvBattery" class="td ">
						 
						 	${lz:or(item$cshsEvBattery[i.count-1],lz:left(item.cshsEvBattery$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsCharging || #request.defines['cshsCharging']!=null">
				<s:if test="#request.defines['cshsCharging']>0">
					${lz:set("注释","****cshsCharging关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsCharging))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsCharging",lz:indexOf(fieldName,"cshsCharging")>-1)}
				  		<s:if test="#request.atCshsCharging==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsCharging字段的字串格式化输出****")}
					<td ref="cshsCharging" class="td ">
						 
						 	${lz:or(item$cshsCharging[i.count-1],lz:left(item.cshsCharging$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsFuelMileage || #request.defines['cshsFuelMileage']!=null">
				<s:if test="#request.defines['cshsFuelMileage']>0">
					${lz:set("注释","****cshsFuelMileage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsFuelMileage))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsFuelMileage",lz:indexOf(fieldName,"cshsFuelMileage")>-1)}
				  		<s:if test="#request.atCshsFuelMileage==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsFuelMileage字段的字串格式化输出****")}
					<td ref="cshsFuelMileage" class="td ">
						 
						 	${lz:or(item$cshsFuelMileage[i.count-1],lz:left(item.cshsFuelMileage$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsElectricMileage || #request.defines['cshsElectricMileage']!=null">
				<s:if test="#request.defines['cshsElectricMileage']>0">
					${lz:set("注释","****cshsElectricMileage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsElectricMileage))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsElectricMileage",lz:indexOf(fieldName,"cshsElectricMileage")>-1)}
				  		<s:if test="#request.atCshsElectricMileage==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsElectricMileage字段的字串格式化输出****")}
					<td ref="cshsElectricMileage" class="td ">
						 
						 	${lz:or(item$cshsElectricMileage[i.count-1],lz:left(item.cshsElectricMileage$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsEndurance || #request.defines['cshsEndurance']!=null">
				<s:if test="#request.defines['cshsEndurance']>0">
					${lz:set("注释","****cshsEndurance关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsEndurance))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsEndurance",lz:indexOf(fieldName,"cshsEndurance")>-1)}
				  		<s:if test="#request.atCshsEndurance==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsEndurance字段的字串格式化输出****")}
					<td ref="cshsEndurance" class="td ">
						 
						 	${lz:or(item$cshsEndurance[i.count-1],lz:left(item.cshsEndurance$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsTemperature || #request.defines['cshsTemperature']!=null">
				<s:if test="#request.defines['cshsTemperature']>0">
					${lz:set("注释","****cshsTemperature关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsTemperature))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsTemperature",lz:indexOf(fieldName,"cshsTemperature")>-1)}
				  		<s:if test="#request.atCshsTemperature==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsTemperature字段的字串格式化输出****")}
					<td ref="cshsTemperature" class="td ">
						 
						 	${lz:or(item$cshsTemperature[i.count-1],lz:left(item.cshsTemperature$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsCsq || #request.defines['cshsCsq']!=null">
				<s:if test="#request.defines['cshsCsq']>0">
					${lz:set("注释","****cshsCsq关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsCsq))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsCsq",lz:indexOf(fieldName,"cshsCsq")>-1)}
				  		<s:if test="#request.atCshsCsq==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsCsq字段的字串格式化输出****")}
					<td ref="cshsCsq" class="td ">
						 
						 	${lz:or(item$cshsCsq[i.count-1],lz:left(item.cshsCsq$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsPowerConsumption || #request.defines['cshsPowerConsumption']!=null">
				<s:if test="#request.defines['cshsPowerConsumption']>0">
					${lz:set("注释","****cshsPowerConsumption关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsPowerConsumption))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsPowerConsumption",lz:indexOf(fieldName,"cshsPowerConsumption")>-1)}
				  		<s:if test="#request.atCshsPowerConsumption==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsPowerConsumption字段的字串格式化输出****")}
					<td ref="cshsPowerConsumption" class="td ">
						 
						 	${lz:or(item$cshsPowerConsumption[i.count-1],lz:left(item.cshsPowerConsumption$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsLongitude || #request.defines['cshsLongitude']!=null">
				<s:if test="#request.defines['cshsLongitude']>0">
					${lz:set("注释","****cshsLongitude关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsLongitude))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsLongitude",lz:indexOf(fieldName,"cshsLongitude")>-1)}
				  		<s:if test="#request.atCshsLongitude==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsLongitude字段的字串格式化输出****")}
					<td ref="cshsLongitude" class="td ">
						 
						 	${lz:or(item$cshsLongitude[i.count-1],lz:left(item.cshsLongitude$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsLatitude || #request.defines['cshsLatitude']!=null">
				<s:if test="#request.defines['cshsLatitude']>0">
					${lz:set("注释","****cshsLatitude关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsLatitude))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsLatitude",lz:indexOf(fieldName,"cshsLatitude")>-1)}
				  		<s:if test="#request.atCshsLatitude==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsLatitude字段的字串格式化输出****")}
					<td ref="cshsLatitude" class="td ">
						 
						 	${lz:or(item$cshsLatitude[i.count-1],lz:left(item.cshsLatitude$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsOrder || #request.defines['cshsOrder']!=null">
				<s:if test="#request.defines['cshsOrder']>0">
					${lz:set("注释","****cshsOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsOrder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsOrder",lz:indexOf(fieldName,"cshsOrder")>-1)}
				  		<s:if test="#request.atCshsOrder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsOrder字段的字串格式化输出****")}
					<td ref="cshsOrder" class="td " relate="${item.cshsOrder}">
						 
						 	<a <s:if test="#item.cshsOrder!=null && #item.cshsOrder!=''"> onclick="window.href('${basePath}${proname}/service/order_details.do?key=${item.cshsOrder}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cshsOrder[i.count-1],lz:left(item.cshsOrder$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cshsMoData || #request.defines['cshsMoData']!=null">
				<s:if test="#request.defines['cshsMoData']>0">
					${lz:set("注释","****cshsMoData关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cshsMoData))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCshsMoData",lz:indexOf(fieldName,"cshsMoData")>-1)}
				  		<s:if test="#request.atCshsMoData==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cshsMoData字段的字串格式化输出****")}
					<td ref="cshsMoData" class="td ">
						 
						 	${lz:or(item$cshsMoData[i.count-1],lz:left(item.cshsMoData$,100))}
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
					<a href="javascript:HandleSel('${handle}','${item.cshsId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectHistory('${item.cshsId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelHistory('${item.cshsId}','${item.cshsNumber}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsHistory('${item.cshsId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.cshsId || #request.defines['cshsId']!=null">
				<s:if test="#request.defines['cshsId']>0">
					${lz:set("注释","****cshsId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsId",lz:indexOf(fieldName,"cshsId")>-1)}				  		
				  		<s:if test="#request.atCshsId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsHost || #request.defines['cshsHost']!=null">
				<s:if test="#request.defines['cshsHost']>0">
					${lz:set("注释","****cshsHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsHost",lz:indexOf(fieldName,"cshsHost")>-1)}				  		
				  		<s:if test="#request.atCshsHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsNumber || #request.defines['cshsNumber']!=null">
				<s:if test="#request.defines['cshsNumber']>0">
					${lz:set("注释","****cshsNumber关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsNumber",lz:indexOf(fieldName,"cshsNumber")>-1)}				  		
				  		<s:if test="#request.atCshsNumber==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsNumber[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsAddTime || #request.defines['cshsAddTime']!=null">
				<s:if test="#request.defines['cshsAddTime']>0">
					${lz:set("注释","****cshsAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsAddTime",lz:indexOf(fieldName,"cshsAddTime")>-1)}				  		
				  		<s:if test="#request.atCshsAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsCurrentTime || #request.defines['cshsCurrentTime']!=null">
				<s:if test="#request.defines['cshsCurrentTime']>0">
					${lz:set("注释","****cshsCurrentTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsCurrentTime",lz:indexOf(fieldName,"cshsCurrentTime")>-1)}				  		
				  		<s:if test="#request.atCshsCurrentTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsCurrentTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsRented || #request.defines['cshsRented']!=null">
				<s:if test="#request.defines['cshsRented']>0">
					${lz:set("注释","****cshsRented关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsRented",lz:indexOf(fieldName,"cshsRented")>-1)}				  		
				  		<s:if test="#request.atCshsRented==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsRented[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsWarn || #request.defines['cshsWarn']!=null">
				<s:if test="#request.defines['cshsWarn']>0">
					${lz:set("注释","****cshsWarn关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsWarn",lz:indexOf(fieldName,"cshsWarn")>-1)}				  		
				  		<s:if test="#request.atCshsWarn==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsWarn[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsRfid || #request.defines['cshsRfid']!=null">
				<s:if test="#request.defines['cshsRfid']>0">
					${lz:set("注释","****cshsRfid关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsRfid",lz:indexOf(fieldName,"cshsRfid")>-1)}				  		
				  		<s:if test="#request.atCshsRfid==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsRfid[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsRfidDte || #request.defines['cshsRfidDte']!=null">
				<s:if test="#request.defines['cshsRfidDte']>0">
					${lz:set("注释","****cshsRfidDte关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsRfidDte",lz:indexOf(fieldName,"cshsRfidDte")>-1)}				  		
				  		<s:if test="#request.atCshsRfidDte==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsRfidDte[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsObdMile || #request.defines['cshsObdMile']!=null">
				<s:if test="#request.defines['cshsObdMile']>0">
					${lz:set("注释","****cshsObdMile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsObdMile",lz:indexOf(fieldName,"cshsObdMile")>-1)}				  		
				  		<s:if test="#request.atCshsObdMile==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsObdMile[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsEngineT || #request.defines['cshsEngineT']!=null">
				<s:if test="#request.defines['cshsEngineT']>0">
					${lz:set("注释","****cshsEngineT关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsEngineT",lz:indexOf(fieldName,"cshsEngineT")>-1)}				  		
				  		<s:if test="#request.atCshsEngineT==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsEngineT[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsMileage || #request.defines['cshsMileage']!=null">
				<s:if test="#request.defines['cshsMileage']>0">
					${lz:set("注释","****cshsMileage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsMileage",lz:indexOf(fieldName,"cshsMileage")>-1)}				  		
				  		<s:if test="#request.atCshsMileage==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsMileage[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsSpeed || #request.defines['cshsSpeed']!=null">
				<s:if test="#request.defines['cshsSpeed']>0">
					${lz:set("注释","****cshsSpeed关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsSpeed",lz:indexOf(fieldName,"cshsSpeed")>-1)}				  		
				  		<s:if test="#request.atCshsSpeed==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsSpeed[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsMotor || #request.defines['cshsMotor']!=null">
				<s:if test="#request.defines['cshsMotor']>0">
					${lz:set("注释","****cshsMotor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsMotor",lz:indexOf(fieldName,"cshsMotor")>-1)}				  		
				  		<s:if test="#request.atCshsMotor==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsMotor[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsOil || #request.defines['cshsOil']!=null">
				<s:if test="#request.defines['cshsOil']>0">
					${lz:set("注释","****cshsOil关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsOil",lz:indexOf(fieldName,"cshsOil")>-1)}				  		
				  		<s:if test="#request.atCshsOil==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsOil[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsPower || #request.defines['cshsPower']!=null">
				<s:if test="#request.defines['cshsPower']>0">
					${lz:set("注释","****cshsPower关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsPower",lz:indexOf(fieldName,"cshsPower")>-1)}				  		
				  		<s:if test="#request.atCshsPower==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsPower[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsEvBattery || #request.defines['cshsEvBattery']!=null">
				<s:if test="#request.defines['cshsEvBattery']>0">
					${lz:set("注释","****cshsEvBattery关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsEvBattery",lz:indexOf(fieldName,"cshsEvBattery")>-1)}				  		
				  		<s:if test="#request.atCshsEvBattery==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsEvBattery[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsCharging || #request.defines['cshsCharging']!=null">
				<s:if test="#request.defines['cshsCharging']>0">
					${lz:set("注释","****cshsCharging关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsCharging",lz:indexOf(fieldName,"cshsCharging")>-1)}				  		
				  		<s:if test="#request.atCshsCharging==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsCharging[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsFuelMileage || #request.defines['cshsFuelMileage']!=null">
				<s:if test="#request.defines['cshsFuelMileage']>0">
					${lz:set("注释","****cshsFuelMileage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsFuelMileage",lz:indexOf(fieldName,"cshsFuelMileage")>-1)}				  		
				  		<s:if test="#request.atCshsFuelMileage==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsFuelMileage[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsElectricMileage || #request.defines['cshsElectricMileage']!=null">
				<s:if test="#request.defines['cshsElectricMileage']>0">
					${lz:set("注释","****cshsElectricMileage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsElectricMileage",lz:indexOf(fieldName,"cshsElectricMileage")>-1)}				  		
				  		<s:if test="#request.atCshsElectricMileage==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsElectricMileage[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsEndurance || #request.defines['cshsEndurance']!=null">
				<s:if test="#request.defines['cshsEndurance']>0">
					${lz:set("注释","****cshsEndurance关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsEndurance",lz:indexOf(fieldName,"cshsEndurance")>-1)}				  		
				  		<s:if test="#request.atCshsEndurance==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsEndurance[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsTemperature || #request.defines['cshsTemperature']!=null">
				<s:if test="#request.defines['cshsTemperature']>0">
					${lz:set("注释","****cshsTemperature关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsTemperature",lz:indexOf(fieldName,"cshsTemperature")>-1)}				  		
				  		<s:if test="#request.atCshsTemperature==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsTemperature[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsCsq || #request.defines['cshsCsq']!=null">
				<s:if test="#request.defines['cshsCsq']>0">
					${lz:set("注释","****cshsCsq关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsCsq",lz:indexOf(fieldName,"cshsCsq")>-1)}				  		
				  		<s:if test="#request.atCshsCsq==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsCsq[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsPowerConsumption || #request.defines['cshsPowerConsumption']!=null">
				<s:if test="#request.defines['cshsPowerConsumption']>0">
					${lz:set("注释","****cshsPowerConsumption关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsPowerConsumption",lz:indexOf(fieldName,"cshsPowerConsumption")>-1)}				  		
				  		<s:if test="#request.atCshsPowerConsumption==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsPowerConsumption[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsLongitude || #request.defines['cshsLongitude']!=null">
				<s:if test="#request.defines['cshsLongitude']>0">
					${lz:set("注释","****cshsLongitude关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsLongitude",lz:indexOf(fieldName,"cshsLongitude")>-1)}				  		
				  		<s:if test="#request.atCshsLongitude==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsLongitude[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsLatitude || #request.defines['cshsLatitude']!=null">
				<s:if test="#request.defines['cshsLatitude']>0">
					${lz:set("注释","****cshsLatitude关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsLatitude",lz:indexOf(fieldName,"cshsLatitude")>-1)}				  		
				  		<s:if test="#request.atCshsLatitude==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsLatitude[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsOrder || #request.defines['cshsOrder']!=null">
				<s:if test="#request.defines['cshsOrder']>0">
					${lz:set("注释","****cshsOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsOrder",lz:indexOf(fieldName,"cshsOrder")>-1)}				  		
				  		<s:if test="#request.atCshsOrder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsOrder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cshsMoData || #request.defines['cshsMoData']!=null">
				<s:if test="#request.defines['cshsMoData']>0">
					${lz:set("注释","****cshsMoData关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCshsMoData",lz:indexOf(fieldName,"cshsMoData")>-1)}				  		
				  		<s:if test="#request.atCshsMoData==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cshsMoData[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 选择车辆历史状态
	**/
	function SelectHistory(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改车辆历史状态任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}history_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新车辆历史状态",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}history_edit.do";
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
			var url = "${basePath}${namespace}history_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除车辆历史状态
	**/
	function DelHistory(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除车辆历史状态["+flag+"]吗？</span>",
				title:"删除车辆历史状态",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}history_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选车辆历史状态吗？</span>",
				title:"批量删除车辆历史状态",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}history_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示车辆历史状态详情
	**/
	function DetailsHistory(id)
	{
		var url = "${basePath}${namespace}history_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"车辆历史状态详情",editable:false,visible:true}};
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
		DetailsHistory(checker.val());
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
<script>
/**
* 显示车辆历史状态详情
**/
function DetailsHistory(id)
{
	var url = "${basePath}${namespace}history_details.do";
	var params = {entrypoint:"${entrypoint}",id:id,cshsNumber:"${cshsNumber}",ctrl:{title:"车辆历史状态详情",editable:false,visible:true}};
	href(url,params);
}
</script>
<!------------------------LAZY3Q_JSP_BOTTOM------------------------>


</body>
</html>