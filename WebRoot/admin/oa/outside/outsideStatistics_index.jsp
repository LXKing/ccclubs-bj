<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/oa/outside/outsideStatistics.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csOutsideStatistics.csosId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csosId,csosHost,csosUser,csosOutlets,csosCar,csosProperty,csosOpenDoorTime,csosOpenDoorCount,csosCloseDoorTime,csosCloseDoorCount,csosFinishTime,csosWorkTime,csosStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csosId,csosUser,csosCar,csosArea,csosHost,csosOutlets,csosProperty,csosOpenDoorCount,csosCloseDoorCount,csosStartCount,csosWhistleCount,csosOpenDoorTime,csosCloseDoorTime,csosAddTime,csosUpdateTime,csosFinishTime,csosStatus,csosCarLocation,csosMemberLocation,csosDistance,csosWorkTime",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csosId,csosHost,csosUser,csosOutlets,csosCar,csosProperty,csosOpenDoorTime,csosOpenDoorCount,csosCloseDoorTime,csosCloseDoorCount,csosFinishTime,csosWorkTime,csosStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csosId,csosUser,csosCar,csosArea,csosHost,csosOutlets,csosProperty,csosOpenDoorCount,csosCloseDoorCount,csosStartCount,csosWhistleCount,csosOpenDoorTime,csosCloseDoorTime,csosAddTime,csosUpdateTime,csosFinishTime,csosStatus,csosCarLocation,csosMemberLocation,csosDistance,csosWorkTime",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>车辆整备管理</title>
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
<lz:JSEncode>
$(function(){
	
	$(".table tbody td[ref='csosStatus']:contains('未完成')").css("color","#ff0000");
	$(".table tbody td[ref='csosStatus']:contains('已完成')").css("color","#0000ff");
	
	//$(".table tbody td[ref='csoStartTime']").css("text-align","rtl");
	var states = $(".table tbody td[ref='csosState']:contains('已发送')");
	states.each(function(){
		$(this).css("color","#ff0000");
		var strOrderStart = $(this).siblings("td[ref='csosStartTime']").text();
		var orderStart = $.dateFormat($.trim(strOrderStart),"yyyy-MM-dd HH:mm:ss");
		if(orderStart.getTime()<new Date().getTime()){ 
			$(this).css("color","#0000ff").css("font-weight","bold").css("font-size","14px");
			$(this).html($.trim($(this).text())+"！！");
		}
	});
	
});
</lz:JSEncode>


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
	<s:if test="#request.alias==null">
		<s:if test="#request.CTRL.canStats==true">
	<a href="outsideStatistics_stats.do?${lz:querys("UTF-8","limitsid")}" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/Statistics.png"/>
		数据统计
	</a>
		</s:if>
	</s:if>
	<s:if test="#request.shortcuts!=null || #request.haveQuerys==true">
		<a class="toggler" href="javascript:void(0);"><span class="${window.shortcuts!="hide"?"on":""}"></span></a>
	</s:if>
	<span class="caption">${empty title?"车辆整备管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsOutsideStatistics" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="outsideStatistics.query.jsp"%>

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
			<dl ref="com.ccclubs.model.CsOutsideStatistics" class="display dropdowan" style="margin-left:30px;">
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
		<table id="outsideStatistics_table" ref="CsOutsideStatistics" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csosId || #request.defines['csosId']!=null">
			 <td <s:if test="#request.defines['csosId']>0">colspan="${defines["csosId"]}" ${all?"width":"iwidth"}="${defines["csosId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="csosId" flagKey  title="编号">
			 	<a class="${desc=="csos_id" ? "desc" : ""}${asc=="csos_id" ? "asc" : ""}" href="?${desc=="csos_id" ? "asc=csos_id" : ""}${(asc=="csos_id" || desc!="csos_id" )? "desc=csos_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","csosId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csosUser || #request.defines['csosUser']!=null">
				 <td <s:if test="#request.defines['csosUser']>0">colspan="${defines["csosUser"]}" ${all?"width":"iwidth"}="${defines["csosUser"]*100}" </s:if><s:else>rowspan="2"   ${all?"width=120":""} </s:else> tdid="2" ref="csosUser"   title="员工">
					<a class="${desc=="csos_user" ? "desc" : ""}${asc=="csos_user" ? "asc" : ""}" href="?${desc=="csos_user" ? "asc=csos_user" : ""}${(asc=="csos_user" || desc!="csos_user" )? "desc=csos_user" : ""}&${lz:queryss("UTF-8","desc","asc")}">
					员工
					</a>
					${lz:set("checkeds[]","csosUser")}
				 </td>
				 </s:if>


			 <s:if test="#request.defines==null && #request.CTRL.l.csosCar || #request.defines['csosCar']!=null">
				 <td <s:if test="#request.defines['csosCar']>0">colspan="${defines["csosCar"]}" ${all?"width":"iwidth"}="${defines["csosCar"]*100}" </s:if><s:else>rowspan="2"   ${all?"width=120":""} </s:else> tdid="3" ref="csosCar"   title="车牌号">
					<a class="${desc=="csos_car" ? "desc" : ""}${asc=="csos_car" ? "asc" : ""}" href="?${desc=="csos_car" ? "asc=csos_car" : ""}${(asc=="csos_car" || desc!="csos_car" )? "desc=csos_car" : ""}&${lz:queryss("UTF-8","desc","asc")}">
					车牌号
					</a>
					${lz:set("checkeds[]","csosCar")}
				 </td>
				 </s:if>


			 <s:if test="#request.defines==null && #request.CTRL.l.csosHost || #request.defines['csosHost']!=null">
			 <td <s:if test="#request.defines['csosHost']>0">colspan="${defines["csosHost"]}" ${all?"width":"iwidth"}="${defines["csosHost"]*100}" </s:if><s:else>rowspan="2"   ${all?"width=120":""} </s:else> tdid="4" ref="csosHost"   title="城市">
			 	<a class="${desc=="csos_host" ? "desc" : ""}${asc=="csos_host" ? "asc" : ""}" href="?${desc=="csos_host" ? "asc=csos_host" : ""}${(asc=="csos_host" || desc!="csos_host" )? "desc=csos_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","csosHost")}
			 </td>
			 </s:if>
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csosArea || #request.defines['csosArea']!=null">
				 <td <s:if test="#request.defines['csosArea']>0">colspan="${defines["csosArea"]}" ${all?"width":"iwidth"}="${defines["csosArea"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="csosArea"   title="整备项">
				 	<a class="${desc=="csos_area" ? "desc" : ""}${asc=="csos_area" ? "asc" : ""}" href="?${desc=="csos_area" ? "asc=csos_area" : ""}${(asc=="csos_area" || desc!="csos_area" )? "desc=csos_area" : ""}&${lz:queryss("UTF-8","desc","asc")}">
				 	所在区域
				 	</a>
				 	${lz:set("checkeds[]","csosArea")}
				 </td>
			 </s:if>
			 
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csosOutlets || #request.defines['csosOutlets']!=null">
			 <td <s:if test="#request.defines['csosOutlets']>0">colspan="${defines["csosOutlets"]}" ${all?"width":"iwidth"}="${defines["csosOutlets"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="csosOutlets"   title="网点">
			 	<a class="${desc=="csos_outlets" ? "desc" : ""}${asc=="csos_outlets" ? "asc" : ""}" href="?${desc=="csos_outlets" ? "asc=csos_outlets" : ""}${(asc=="csos_outlets" || desc!="csos_outlets" )? "desc=csos_outlets" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	网点
			 	</a>
			 	${lz:set("checkeds[]","csosOutlets")}
			 </td>
			 </s:if>
			 
			 
			  
			 
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csosProperty || #request.defines['csosProperty']!=null">
				 <td <s:if test="#request.defines['csosProperty']>=0">colspan="${defines["csosProperty"]}" ${all?"width":"iwidth"}="${defines["csosProperty"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="csosProperty"   title="整备项">
				 	<a class="${desc=="csos_property" ? "desc" : ""}${asc=="csos_property" ? "asc" : ""}" href="?${desc=="csos_property" ? "asc=csos_property" : ""}${(asc=="csos_property" || desc!="csos_property" )? "desc=csos_property" : ""}&${lz:queryss("UTF-8","desc","asc")}">
				 	整备项
				 	</a>
				 	${lz:set("checkeds[]","csosProperty")}
				 </td>
			 </s:if>
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csosOpenDoorCount || #request.defines['csosOpenDoorCount']!=null">
				 <td <s:if test="#request.defines['csosOpenDoorCount']>0">colspan="${defines["csosOpenDoorCount"]}" ${all?"width":"iwidth"}="${defines["csosOpenDoorCount"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="7" ref="csosOpenDoorCount"   title="开门次数">
				 	<a class="${desc=="csos_open_door_count" ? "desc" : ""}${asc=="csos_open_door_count" ? "asc" : ""}" href="?${desc=="csos_open_door_count" ? "asc=csos_open_door_count" : ""}${(asc=="csos_open_door_count" || desc!="csos_open_door_count" )? "desc=csos_open_door_count" : ""}&${lz:queryss("UTF-8","desc","asc")}">
				 开门次数
				 	</a>
				 	${lz:set("checkeds[]","csosOpenDoorCount")}
				 </td>
			 </s:if>
			 
			  
			 
			  <s:if test="#request.defines==null && #request.CTRL.l.csosCloseDoorCount || #request.defines['csosCloseDoorCount']!=null">
				 <td <s:if test="#request.defines['csosCloseDoorCount']>0">colspan="${defines["csosCloseDoorCount"]}" ${all?"width":"iwidth"}="${defines["csosCloseDoorCount"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="csosCloseDoorCount"   title="关门次数">
				 	<a class="${desc=="csos_close_door_count" ? "desc" : ""}${asc=="csos_close_door_count" ? "asc" : ""}" href="?${desc=="csos_close_door_count" ? "asc=csosCloseDoorCount" : ""}${(asc=="csos_close_door_count" || desc!="csos_close_door_count" )? "desc=csos_close_door_count" : ""}&${lz:queryss("UTF-8","desc","asc")}">
				 	关门次数
				 	</a>
				 	${lz:set("checkeds[]","csosCloseDoorCount")}
				 </td>
			 </s:if>
			 
			 
			  <s:if test="#request.defines==null && #request.CTRL.l.csosStartCount || #request.defines['csosStartCount']!=null">
				 <td <s:if test="#request.defines['csosStartCount']>0">colspan="${defines["csosStartCount"]}" ${all?"width":"iwidth"}="${defines["csosStartCount"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="9" ref="csosStartCount"   title="允许启动次数">
				 	<a class="${desc=="csos_start_count" ? "desc" : ""}${asc=="csos_start_count" ? "asc" : ""}" href="?${desc=="csos_start_count" ? "asc=csos_start_count" : ""}${(asc=="csos_start_count" || desc!="csos_start_count" )? "desc=csosStartCount" : ""}&${lz:queryss("UTF-8","desc","asc")}">
				 	启动次数
				 	</a>
				 	${lz:set("checkeds[]","csosStartCount")}
				 </td>
			 </s:if>
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csosWhistleCount || #request.defines['csosWhistleCount']!=null">
				 <td <s:if test="#request.defines['csosWhistleCount']>0">colspan="${defines["csosWhistleCount"]}" ${all?"width":"iwidth"}="${defines["csosWhistleCount"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="10" ref="csosWhistleCount"   title="鸣笛次数">
				 	<a class="${desc=="csos_whistle_count" ? "desc" : ""}${asc=="csos_whistle_count" ? "asc" : ""}" href="?${desc=="csos_whistle_count" ? "asc=csosWhistleCount" : ""}${(asc=="csos_whistle_count" || desc!="csosWhistleCount" )? "desc=csos_whistle_count" : ""}&${lz:queryss("UTF-8","desc","asc")}">
				 	鸣笛次数
				 	</a>
				 	${lz:set("checkeds[]","csosWhistleCount")}
				 </td>
			 </s:if>
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csosOpenDoorTime || #request.defines['csosOpenDoorTime']!=null">
				 <td <s:if test="#request.defines['csosOpenDoorTime']>0">colspan="${defines["csosOpenDoorTime"]}" ${all?"width":"iwidth"}="${defines["csosOpenDoorTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="11" ref="csosOpenDoorTime"   title="鸣笛次数">
				 	<a class="${desc=="csos_open_door_time" ? "desc" : ""}${asc=="csos_open_door_time" ? "asc" : ""}" href="?${desc=="csos_open_door_time" ? "asc=csos_open_door_time" : ""}${(asc=="csosOpenDoorTime" || desc!="csos_open_door_time" )? "desc=csosOpenDoorTime" : ""}&${lz:queryss("UTF-8","desc","asc")}">
				 	开门时间
				 	</a>
				 	${lz:set("checkeds[]","csosOpenDoorTime")}
				 </td>
			 </s:if>
			 
			 
			  <s:if test="#request.defines==null && #request.CTRL.l.csosCloseDoorTime || #request.defines['csosCloseDoorTime']!=null">
				 <td <s:if test="#request.defines['csosCloseDoorTime']>0">colspan="${defines["csosCloseDoorTime"]}" ${all?"width":"iwidth"}="${defines["csosCloseDoorTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="12" ref="csosCloseDoorTime"   title="关门时间">
				 	<a class="${desc=="csos_close_door_time" ? "desc" : ""}${asc=="csos_close_door_time" ? "asc" : ""}" href="?${desc=="csos_close_door_time" ? "asc=csos_close_door_time" : ""}${(asc=="csos_close_door_time" || desc!="csos_close_door_time" )? "desc=csos_close_door_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
				 	关门时间
				 	</a>
				 	${lz:set("checkeds[]","csosCloseDoorTime")}
				 </td>
			 </s:if>
			 
			 
			  <s:if test="#request.defines==null && #request.CTRL.l.csosAddTime || #request.defines['csosAddTime']!=null">
				 <td <s:if test="#request.defines['csosAddTime']>0">colspan="${defines["csosAddTime"]}" ${all?"width":"iwidth"}="${defines["csosAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="13" ref="csosAddTime"   title="添加时间">
				 	<a class="${desc=="csos_add_time" ? "desc" : ""}${asc=="csos_add_time" ? "asc" : ""}" href="?${desc=="csos_add_time" ? "asc=csos_add_time" : ""}${(asc=="csos_add_time" || desc!="csos_add_time" )? "desc=csos_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
				 	添加时间
				 	</a>
				 	${lz:set("checkeds[]","csosAddTime")}
				 </td>
			 </s:if>
			 
			 
			  <s:if test="#request.defines==null && #request.CTRL.l.csosUpdateTime || #request.defines['csosUpdateTime']!=null">
				 <td <s:if test="#request.defines['csosUpdateTime']>0">colspan="${defines["csosUpdateTime"]}" ${all?"width":"iwidth"}="${defines["csosUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="14" ref="csosUpdateTime"   title="更新时间">
				 	<a class="${desc=="csos_update_time" ? "desc" : ""}${asc=="csos_update_time" ? "asc" : ""}" href="?${desc=="csos_update_time" ? "asc=csos_update_time" : ""}${(asc=="csos_update_time" || desc!="csos_update_time" )? "desc=csos_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
				 	更新时间
				 	</a>
				 	${lz:set("checkeds[]","csosUpdateTime")}
				 </td>
			 </s:if>
			 
			  <s:if test="#request.defines==null && #request.CTRL.l.csosFinishTime || #request.defines['csosFinishTime']!=null">
				 <td <s:if test="#request.defines['csosFinishTime']>0">colspan="${defines["csosFinishTime"]}" ${all?"width":"iwidth"}="${defines["csosFinishTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="15" ref="csosFinishTime"   title="完成整备时间">
				 	<a class="${desc=="csos_finish_time" ? "desc" : ""}${asc=="csos_finish_time" ? "asc" : ""}" href="?${desc=="csos_finish_time" ? "asc=csos_finish_time" : ""}${(asc=="csos_finish_time" || desc!="csos_finish_time" )? "desc=csos_finish_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
				 	完成整备时间
				 	</a>
				 	${lz:set("checkeds[]","csosFinishTime")}
				 </td>
			 </s:if>
			 
			   <s:if test="#request.defines==null && #request.CTRL.l.csosStatus || #request.defines['csosStatus']!=null">
				 <td <s:if test="#request.defines['csosStatus']>0">colspan="${defines["csosStatus"]}" ${all?"width":"iwidth"}="${defines["csosStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="16" ref="csosStatus"   title="状态">
				 	<a class="${desc=="csos_status" ? "desc" : ""}${asc=="csos_status" ? "asc" : ""}" href="?${desc=="csos_status" ? "asc=csos_status" : ""}${(asc=="csos_status" || desc!="csos_status" )? "desc=csos_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
				 	状态
				 	</a>
				 	${lz:set("checkeds[]","csosStatus")}
				 </td>
			 </s:if>
			 
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csosCarLocation || #request.defines['csosCarLocation']!=null">
				 <td <s:if test="#request.defines['csosCarLocation']>0">colspan="${defines["csosCarLocation"]}" ${all?"width":"iwidth"}="${defines["csosCarLocation"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="17" ref="csosCarLocation"   title="车辆位置">
				 	<a class="${desc=="csos_car_location" ? "desc" : ""}${asc=="csos_car_location" ? "asc" : ""}" href="?${desc=="csos_car_location" ? "asc=csos_car_location" : ""}${(asc=="csos_car_location" || desc!="csos_car_location" )? "desc=csos_car_location" : ""}&${lz:queryss("UTF-8","desc","asc")}">
				 	车辆位置
				 	</a>
				 	${lz:set("checkeds[]","csosCarLocation")}
				 </td>
			 </s:if>
			 
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csosMemberLocation || #request.defines['csosMemberLocation']!=null">
				 <td <s:if test="#request.defines['csosMemberLocation']>0">colspan="${defines["csosMemberLocation"]}" ${all?"width":"iwidth"}="${defines["csosMemberLocation"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="18" ref="csosMemberLocation"   title="人员位置">
				 	<a class="${desc=="csos_member_location" ? "desc" : ""}${asc=="csos_member_location" ? "asc" : ""}" href="?${desc=="csos_member_location" ? "asc=csosMemberLocation" : ""}${(asc=="csosMemberLocation" || desc!="csosMemberLocation" )? "desc=csosMemberLocation" : ""}&${lz:queryss("UTF-8","desc","asc")}">
				 	人员位置
				 	</a>
				 	${lz:set("checkeds[]","csosMemberLocation")}
				 </td>
			 </s:if>
			 
			  <s:if test="#request.defines==null && #request.CTRL.l.csosDistance || #request.defines['csosDistance']!=null">
				 <td <s:if test="#request.defines['csosDistance']>0">colspan="${defines["csosDistance"]}" ${all?"width":"iwidth"}="${defines["csosDistance"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="19" ref="csosDistance"   title="人车距离">
				 	<a class="${desc=="csosDistance" ? "desc" : ""}${asc=="csosDistance" ? "asc" : ""}" href="?${desc=="csosDistance" ? "asc=csosDistance" : ""}${(asc=="csosDistance" || desc!="csosDistance" )? "desc=csosDistance" : ""}&${lz:queryss("UTF-8","desc","asc")}">
				 	人车距离
				 	</a>
				 	${lz:set("checkeds[]","csosDistance")}
				 </td>
			 </s:if>
			 
			 
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csosWorkTime || #request.defines['csosWorkTime']!=null">
				 <td <s:if test="#request.defines['csosWorkTime']>0">colspan="${defines["csosWorkTime"]}" ${all?"width":"iwidth"}="${defines["csosWorkTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="20" ref="csosWorkTime"   title="人车距离">
				 	<a class="${desc=="csosWorkTime" ? "desc" : ""}${asc=="csosWorkTime" ? "asc" : ""}" href="?${desc=="csosWorkTime" ? "asc=csosWorkTime" : ""}${(asc=="csosWorkTime" || desc!="csosWorkTime" )? "desc=csosWorkTime" : ""}&${lz:queryss("UTF-8","desc","asc")}">
				 	作业时长 (ms)
				 	</a>
				 	${lz:set("checkeds[]","csosWorkTime")}
				 </td>
			 </s:if>
			 

		 	${lz:set("注释","****数据列表列头最后两列****")}
			
			<td rowspan="1" width="105" class="operation" tdid="21" ref="operation">
					
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
		  <tr id="${item.csosId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csosId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csosId || #request.defines['csosId']!=null">
				<s:if test="#request.defines['csosId']>0">
					${lz:set("注释","****csosId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csosId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsosId",lz:indexOf(fieldName,"csosId")>-1)}
				  		<s:if test="#request.atCsosId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csosId字段的字串格式化输出****")}
					<td ref="csosId" class="td  node">
						 
						 	${lz:or(item$csosId[i.count-1],lz:left(item.csosId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			
			
			 
		 	<s:if test="#request.defines==null && #request.CTRL.l.csosUser || #request.defines['csosUser']!=null">
				<s:if test="#request.defines['csosUser']>0">
					${lz:set("注释","****csosUser关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csosUser))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsosUser",lz:indexOf(fieldName,"csosUser")>-1)}
				  		<s:if test="#request.atCsosUser==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csosUser字段的字串格式化输出****")}
					<td ref="csosUser" class="td " relate="${item.csosUser}">
						 
						 	<a <s:if test="#item.csosUser!=null && #item.csosUser!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.csosUser}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csosUser[i.count-1],lz:left(item.csosUser$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csosCar || #request.defines['csosCar']!=null">
				<s:if test="#request.defines['csosCar']>0">
					${lz:set("注释","****csosCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csosCar))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsosCar",lz:indexOf(fieldName,"csosCar")>-1)}
				  		<s:if test="#request.atCsosCar==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csosCar字段的字串格式化输出****")}
					<td ref="csosCar" class="td " relate="${item.csosCar}">
						 
						 	<a <s:if test="#item.csosCar!=null && #item.csosCar!=''"> onclick="window.href('${basePath}${proname}/object/car_details.do?key=${item.csosCar}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csosCar[i.count-1],lz:left(item.csosCar$,100))}</a>
					</td>
				</s:else>
			</s:if>
			
			<s:if test="#request.defines==null && #request.CTRL.l.csosHost || #request.defines['csosHost']!=null">
				<s:if test="#request.defines['csosHost']>0">
					${lz:set("注释","****csosHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csosHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsosHost",lz:indexOf(fieldName,"csosHost")>-1)}
				  		<s:if test="#request.atCsosHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csosHost字段的字串格式化输出****")}
					<td ref="csosHost" class="td " relate="${item.csosHost}">
						 
						 	<a <s:if test="#item.csosHost!=null && #item.csosHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.csosHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csosHost[i.count-1],lz:left(item.csosHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			
			
			
			<s:if test="#request.defines==null && #request.CTRL.l.csosArea || #request.defines['csosArea']!=null">
				<s:if test="#request.defines['csosArea']>0">
					${lz:set("注释","****csosArea关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csosArea))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsosArea",lz:indexOf(fieldName,"csosArea")>-1)}
				  		<s:if test="#request.atCsosArea==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csosArea字段的字串格式化输出****")}
					<td ref="csosArea" class="td " relate="${item.csosArea}">
						 
						 	<a <s:if test="#item.csosArea!=null && #item.csosArea!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.csosArea}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csosArea[i.count-1],lz:left(item.csosArea$,100))}</a>
					</td>
				</s:else>
			</s:if>
			
			
			<s:if test="#request.defines==null && #request.CTRL.l.csosOutlets || #request.defines['csosOutlets']!=null">
				<s:if test="#request.defines['csosOutlets']>0">
					${lz:set("注释","****csosOutlets关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csosOutlets))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsosOutlets",lz:indexOf(fieldName,"csosOutlets")>-1)}
				  		<s:if test="#request.atCsosOutlets==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csosOutlets字段的字串格式化输出****")}
					<td ref="csosOutlets" class="td " relate="${item.csosOutlets}">
						 
						 	<a <s:if test="#item.csosOutlets!=null && #item.csosOutlets!=''"> onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${item.csosOutlets}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csosOutlets[i.count-1],lz:left(item.csosOutlets$,100))}</a>
					</td>
				</s:else>
			</s:if>
			
			
			
			<s:if test="#request.defines==null && #request.CTRL.l.csosProperty || #request.defines['csosProperty']!=null">
				<s:if test="#request.defines['csosProperty']>0">
					${lz:set("注释","****csosProperty关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csosProperty))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsosProperty",lz:indexOf(fieldName,"csosProperty")>-1)}
				  		<s:if test="#request.atCsosProperty==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				 	
				<s:else>
					${lz:set("注释","****csosProperty字段的字串格式化输出****")}
					<td ref="csosProperty" class="td " relate="${item.csosProperty}">
					
						<s:if test="#item.csosProperty==0">其他</s:if>
						<s:else>
							<a <s:if test="#item.csosProperty!=null && #item.csosProperty!=''"> onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${item.csosProperty}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 		${lz:or(item$csosProperty[i.count-1],lz:left(item.csosProperty$,100))}</a>
						</s:else>
					</td>
				</s:else>
				
			</s:if>
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			<s:if test="#request.defines==null && #request.CTRL.l.csosOpenDoorCount || #request.defines['csosOpenDoorCount']!=null">
				<s:if test="#request.defines['csosOpenDoorCount']>0">
					${lz:set("注释","****csosOpenDoorCount关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csosOpenDoorCount))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsosOpenDoorCount",lz:indexOf(fieldName,"csosOpenDoorCount")>-1)}
				  		<s:if test="#request.atCsosOpenDoorCount==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csosOpenDoorCount字段的字串格式化输出****")}
					<td ref="csosOpenDoorCount" class="td " relate="${item.csosOpenDoorCount}">
						 
						 	<a <s:if test="#item.csosOpenDoorCount!=null && #item.csosOpenDoorCount!=''"> href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csosOpenDoorCount[i.count-1],lz:left(item.csosOpenDoorCount$,100))}</a>
					</td>
				</s:else>
			</s:if>
			
			<s:if test="#request.defines==null && #request.CTRL.l.csosCloseDoorCount || #request.defines['csosCloseDoorCount']!=null">
				<s:if test="#request.defines['csosCloseDoorCount']>0">
					${lz:set("注释","****csosCloseDoorCount关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csosCloseDoorCount))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsosCloseDoorCount",lz:indexOf(fieldName,"csosCloseDoorCount")>-1)}
				  		<s:if test="#request.atCsosCloseDoorCount==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csosCloseDoorCount字段的字串格式化输出****")}
					<td ref="csosCloseDoorCount" class="td " relate="${item.csosCloseDoorCount}">
						 
						 	<a <s:if test="#item.csosCloseDoorCount!=null && #item.csosCloseDoorCount!=''"> href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csosCloseDoorCount[i.count-1],lz:left(item.csosCloseDoorCount$,100))}</a>
					</td>
				</s:else>
			</s:if>
			
			<s:if test="#request.defines==null && #request.CTRL.l.csosStartCount || #request.defines['csosStartCount']!=null">
				<s:if test="#request.defines['csosStartCount']>0">
					${lz:set("注释","****csosStartCount关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csosStartCount))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsosStartCount",lz:indexOf(fieldName,"csosStartCount")>-1)}
				  		<s:if test="#request.atCsosStartCount==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csosStartCount字段的字串格式化输出****")}
					<td ref="csosStartCount" class="td " relate="${item.csosStartCount}">
						 
						 	<a <s:if test="#item.csosStartCount!=null && #item.csosStartCount!=''">  href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csosStartCount[i.count-1],lz:left(item.csosStartCount$,100))}</a>
					</td>
				</s:else>
			</s:if>
			
			
			
			
			<s:if test="#request.defines==null && #request.CTRL.l.csosWhistleCount || #request.defines['csosWhistleCount']!=null">
				<s:if test="#request.defines['csosWhistleCount']>0">
					${lz:set("注释","****csosWhistleCount关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csosWhistleCount))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsosWhistleCount",lz:indexOf(fieldName,"csosWhistleCount")>-1)}
				  		<s:if test="#request.atCsosWhistleCount==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csosWhistleCount字段的字串格式化输出****")}
					<td ref="csosWhistleCount" class="td " relate="${item.csosWhistleCount}">
						 
						 	<a <s:if test="#item.csosWhistleCount!=null && #item.csosWhistleCount!=''">  href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csosWhistleCount[i.count-1],lz:left(item.csosWhistleCount$,100))}</a>
					</td>
				</s:else>
			</s:if>
			
			<s:if test="#request.defines==null && #request.CTRL.l.csosOpenDoorTime || #request.defines['csosOpenDoorTime']!=null">
				<s:if test="#request.defines['csosOpenDoorTime']>0">
					${lz:set("注释","****csosOpenDoorTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csosOpenDoorTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsosOpenDoorTime",lz:indexOf(fieldName,"csosOpenDoorTime")>-1)}
				  		<s:if test="#request.atCsosOpenDoorTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csosOpenDoorTime字段的字串格式化输出****")}
					<td ref="csosOpenDoorTime" class="td " relate="${item.csosOpenDoorTime}">
						 
						 	<a <s:if test="#item.csosOpenDoorTime!=null && #item.csosOpenDoorTime!=''">  href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csosOpenDoorTime[i.count-1],lz:left(item.csosOpenDoorTime$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			 
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csosCloseDoorTime || #request.defines['csosCloseDoorTime']!=null">
				<s:if test="#request.defines['csosCloseDoorTime']>0">
					${lz:set("注释","****csosCloseDoorTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csosCloseDoorTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsosCloseDoorTime",lz:indexOf(fieldName,"csosCloseDoorTime")>-1)}
				  		<s:if test="#request.atCsosCloseDoorTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csosCloseDoorTime字段的字串格式化输出****")}
					<td ref="csosCloseDoorTime" class="td " relate="${item.csosCloseDoorTime}">
						 
						 	<a <s:if test="#item.csosCloseDoorTime!=null && #item.csosCloseDoorTime!=''">  href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csosCloseDoorTime[i.count-1],lz:left(item.csosCloseDoorTime$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			  <s:if test="#request.defines==null && #request.CTRL.l.csosAddTime || #request.defines['csosAddTime']!=null">
				<s:if test="#request.defines['csosAddTime']>0">
					${lz:set("注释","****csosAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csosAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsosAddTime",lz:indexOf(fieldName,"csosAddTime")>-1)}
				  		<s:if test="#request.atCsosAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csosOpenDoorTime字段的字串格式化输出****")}
					<td ref="csosAddTime" class="td " relate="${item.csosAddTime}">
						 
						 	<a <s:if test="#item.csosAddTime!=null && #item.csosAddTime!=''">  href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csosAddTime[i.count-1],lz:left(item.csosAddTime$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			  <s:if test="#request.defines==null && #request.CTRL.l.csosUpdateTime || #request.defines['csosUpdateTime']!=null">
				<s:if test="#request.defines['csosUpdateTime']>0">
					${lz:set("注释","****csosUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csosUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsosUpdateTime",lz:indexOf(fieldName,"csosUpdateTime")>-1)}
				  		<s:if test="#request.atCsosUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csosUpdateTime字段的字串格式化输出****")}
					<td ref="csosUpdateTime" class="td " relate="${item.csosUpdateTime}">
						 
						 	<a <s:if test="#item.csosUpdateTime!=null && #item.csosUpdateTime!=''">  href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csosUpdateTime[i.count-1],lz:left(item.csosUpdateTime$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csosFinishTime || #request.defines['csosFinishTime']!=null">
				<s:if test="#request.defines['csosFinishTime']>0">
					${lz:set("注释","****csosFinishTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csosFinishTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsosFinishTime",lz:indexOf(fieldName,"csosFinishTime")>-1)}
				  		<s:if test="#request.atCsosFinishTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csosFinishTime字段的字串格式化输出****")}
					<td ref="csosFinishTime" class="td " relate="${item.csosFinishTime}">
						 
						 	<a <s:if test="#item.csosFinishTime!=null && #item.csosFinishTime!=''">  href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csosFinishTime[i.count-1],lz:left(item.csosFinishTime$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			  
			 <s:if test="#request.defines==null && #request.CTRL.l.csosStatus || #request.defines['csosStatus']!=null">
				<s:if test="#request.defines['csosStatus']>0">
					${lz:set("注释","****csosStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csosStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsosStatus",lz:indexOf(fieldName,"csosStatus")>-1)}
				  		<s:if test="#request.atCsosStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csosStatus字段的字串格式化输出****")}
					<td ref="csosStatus" class="td " relate="${item.csosStatus}">
						 
						 	<a <s:if test="#item.csosStatus!=null && #item.csosStatus!=''">  href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csosStatus[i.count-1],lz:left(item.csosStatus$,100))}</a>
					</td>
				</s:else>
			</s:if>
			
			
			
			
			<s:if test="#request.defines==null && #request.CTRL.l.csosCarLocation || #request.defines['csosCarLocation']!=null">
				<s:if test="#request.defines['csosCarLocation']>0">
					${lz:set("注释","****csosCarLocation关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csosCarLocation))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsosCarLocation",lz:indexOf(fieldName,"csosCarLocation")>-1)}
				  		<s:if test="#request.atCsosCarLocation==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csosCarLocation字段的字串格式化输出****")}
					<td ref="csosCarLocation" class="td " relate="${item.csosCarLocation}">
						 
						 	<a <s:if test="#item.csosCarLocation!=null && #item.csosCarLocation!=''">  href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csosCarLocation[i.count-1],lz:left(item.csosCarLocation$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			 
			 
			 	
			<s:if test="#request.defines==null && #request.CTRL.l.csosMemberLocation || #request.defines['csosMemberLocation']!=null">
				<s:if test="#request.defines['csosMemberLocation']>0">
					${lz:set("注释","****csosMemberLocation关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csosMemberLocation))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsosMemberLocation",lz:indexOf(fieldName,"csosMemberLocation")>-1)}
				  		<s:if test="#request.atCsosMemberLocation==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csosMemberLocation字段的字串格式化输出****")}
					<td ref="csosMemberLocation" class="td " relate="${item.csosMemberLocation}">
						 
						 	<a <s:if test="#item.csosMemberLocation!=null && #item.csosMemberLocation!=''"> href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csosMemberLocation[i.count-1],lz:left(item.csosMemberLocation$,100))}</a>
					</td>
				</s:else>
			</s:if>
			
			
			
			
			
			<s:if test="#request.defines==null && #request.CTRL.l.csosDistance || #request.defines['csosDistance']!=null">
				<s:if test="#request.defines['csosDistance']>0">
					${lz:set("注释","****csosDistance关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csosDistance))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsosDistance",lz:indexOf(fieldName,"csosDistance")>-1)}
				  		<s:if test="#request.atCsosDistance==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csosDistance字段的字串格式化输出****")}
					<td ref="csosDistance" class="td " relate="${item.csosDistance}">
						 
						 	<a <s:if test="#item.csosDistance!=null && #item.csosDistance!=''">  href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csosDistance[i.count-1],lz:left(item.csosDistance$,100))}</a>
					</td>
				</s:else>
			</s:if>
			
			
			
			<s:if test="#request.defines==null && #request.CTRL.l.csosWorkTime || #request.defines['csosWorkTime']!=null">
				<s:if test="#request.defines['csosWorkTime']>0">
					${lz:set("注释","****csosWorkTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csosWorkTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsosWorkTime",lz:indexOf(fieldName,"csosWorkTime")>-1)}
				  		<s:if test="#request.atCsosWorkTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csosWorkTime字段的字串格式化输出****")}
					<td ref="csosWorkTime" class="td " relate="${item.csosWorkTime}">
						 
						 	<a <s:if test="#item.csosWorkTime!=null && #item.csosWorkTime!=''">    href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csosWorkTime[i.count-1],lz:left(item.csosWorkTime$,100))}</a>
					</td>
				</s:else>
			</s:if>
			
			
			
			
			 
		   ${lz:set("注释","*****************数据列表数据部分结束*****************")}		  
		  
		   
		    ${lz:set("注释","****自定义的单行记录操作句柄[request中的handles(String[])变量]****")}
			
			<td class="operation">
				<s:if test="#request.alias==null">
					<s:if test="#request.CTRL.canDel==true">
					<a href="javascript:DelOutsideStatistics('${item.csosId}','${item.csosId}')">删除</a></s:if>
				</s:if>
			
				<s:if test="#request.CTRL.canView==true">
				<a href="javascript:DetailsOutsideStatistics('${item.csosId}')">详情</a></s:if>
			</td>
		
		  </tr>
	
		  
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
<s:if test="#request.CTRL.canAdd==true">
	/**
	* 添加车辆违章
	**/
	function AddOutsideStatistics(parent){		
		var url = "${basePath}${namespace}outsideStatistics_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加车辆违章'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择车辆违章
	**/
	function SelectOutsideStatistics(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改车辆违章
	**/
	function EditOutsideStatistics(id){
		var url = "${basePath}${namespace}outsideStatistics_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改车辆违章'}":lz:json(ctrl)}};
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
		EditOutsideStatistics(checker.val());
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
				var url = "${basePath}${namespace}outsideStatistics_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新车辆整备管理",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}outsideStatistics_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新车辆整备管理",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改车辆违章
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}outsideStatistics_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新车辆整备管理",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改车辆违章任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}outsideStatistics_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新车辆违章",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}outsideStatistics_edit.do";
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
			var url = "${basePath}${namespace}outsideStatistics_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除车辆违章
	**/
	function DelOutsideStatistics(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除车辆整备数据["+flag+"]吗？</span>",
				title:"删除车辆整备数据",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}outsideStatistics_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选车辆整备数据吗？</span>",
				title:"批量删除车辆整备数据",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}outsideStatistics_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示车辆整备详情
	**/
	function DetailsOutsideStatistics(id)
	{
		var url = "${basePath}${namespace}outsideStatistics_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"车辆整备详情",editable:false,visible:true}};
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
		DetailsOutsideStatistics(checker.val());
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


<!-- LAZY3Q_JSP_BOTTOM -->
<script>

function redate(string){
	return $.formatDate("MM月dd日HH点mm分",$.dateFormat(string,"yyyy-MM-dd HH:mm:ss"));
}

</script>
<!-- LAZY3Q_JSP_BOTTOM -->


</body>
</html>