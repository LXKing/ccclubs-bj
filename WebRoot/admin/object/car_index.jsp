<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/object/car.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csCar.cscId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"cscId,cscHost,cscNumber,cscCarNo,cscModel,cscOutlets,cscProvid,cscRebate,cscIsAd,cscMobile,cscOilCard,cscVin,cscEngineNo,cscBuyDate,cscBargainNo,cscCertific,cscFactoryNo,cscColor,cscAnnualPre,cscAnnual,cscAnnualNext,cscTiUnit,cscTiNo,cscBiUnit,cscBiType,cscEditor,cscUpdateTime,cscAddTime,cscRemark,cscMemo,cscOnPsd,cscSetup,cscDeviceVesion,cscMqttFlag,cscMask,cscStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"cscId,cscHost,cscNumber,cscCarNo,cscModel,cscOutlets,cscProvid,cscRebate,cscIsAd,cscMobile,cscPassword,cscOilCard,cscVin,cscEngineNo,cscBuyDate,cscBargainNo,cscTaxPrice,cscBuyPrice,cscCertific,cscFactoryNo,cscColor,cscPreview,cscImages,cscMaintKm,cscCheckIn,cscAnnualPre,cscAnnual,cscAnnualInfo,cscAnnualNext,cscTiDate,cscTiUnit,cscTiNo,cscBiDate,cscBiUnit,cscBiNo,cscBiType,cscBiLimit,cscBiCompany,cscEditor,cscUpdateTime,cscAddTime,cscRemark,cscMemo,cscOnPsd,cscSetup,cscDeviceVesion,cscMqttFlag,cscMask,cscStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"cscId,cscHost,cscNumber,cscModel,cscOutlets,cscVin,cscEngineNo,cscAnnualNext,cscTiUnit,cscRemark,cscSetup,cscStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"cscId,cscHost,cscNumber,cscModel,cscOutlets,cscVin,cscEngineNo,cscAnnualNext,cscTiUnit,cscBiCompany,cscRemark,cscSetup,cscStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>车辆管理</title>
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


<!-- LAZY3Q_JSP_BODY -->
<script>
$(function(){
	var button = $('<button type="button" class="edit">车辆信息同步</button>');
	button.appendTo(".toolbar");
	button.click(function(){
		var json = $.getObject("${basePath}admin/object/car_sync.do");
		if(json.success)
			showTips("同步成功");
		else
			showTips("同步失败");
	});
});
</script>
<!-- LAZY3Q_JSP_BODY -->


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
	<span class="caption">${empty title?"车辆管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsCar" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="car.query.jsp"%>

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
			<s:if test="#request.CTRL.canAdd==true">
			<button type="button" class="add" onclick="AddCar();">
				添加
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
			<dl ref="com.ccclubs.model.CsCar" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.CsCar" class="batch dropdowan">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/20088256421529677807.png"/>
					批量修改
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
					<s:if test="#request.defines==null && #request.CTRL.l.cscProvid || #request.defines['cscProvid']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('provid')">供应商</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.cscRebate || #request.defines['cscRebate']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('rebate')">折扣</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.cscIsAd || #request.defines['cscIsAd']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('isad')">广告车？</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.cscMemo || #request.defines['cscMemo']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('memo')">备忘信息</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.cscSetup || #request.defines['cscSetup']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('setup')">车机安装</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.cscDeviceVesion || #request.defines['cscDeviceVesion']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('vesion')">下位机版本</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.cscMqttFlag || #request.defines['cscMqttFlag']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('mqtt')">服务器标识</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.cscStatus || #request.defines['cscStatus']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('status')">状态</a>
					</s:if>
					</td>  <td class="r"></td></tr><tr>  <td class="bl"></td>  <td class="b"></td>  <td class="br"></td></tr>
					</tbody></table
				</dd>
			</dl>
			</s:if>
			
			${after$toolbar}
			
		</div>
		${lz:set("注释","*****************数据列表开始*****************")}
		<table id="car_table" ref="CsCar" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscId || #request.defines['cscId']!=null">
			 <td <s:if test="#request.defines['cscId']>0">colspan="${defines["cscId"]}" ${all?"width":"iwidth"}="${defines["cscId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="cscId"   title="编号">
			 	<a class="${desc=="csc_id" ? "desc" : ""}${asc=="csc_id" ? "asc" : ""}" href="?${desc=="csc_id" ? "asc=csc_id" : ""}${(asc=="csc_id" || desc!="csc_id" )? "desc=csc_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","cscId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscHost || #request.defines['cscHost']!=null">
			 <td <s:if test="#request.defines['cscHost']>0">colspan="${defines["cscHost"]}" ${all?"width":"iwidth"}="${defines["cscHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="cscHost"   title="城市">
			 	<a class="${desc=="csc_host" ? "desc" : ""}${asc=="csc_host" ? "asc" : ""}" href="?${desc=="csc_host" ? "asc=csc_host" : ""}${(asc=="csc_host" || desc!="csc_host" )? "desc=csc_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","cscHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscNumber || #request.defines['cscNumber']!=null">
			 <td <s:if test="#request.defines['cscNumber']>0">colspan="${defines["cscNumber"]}" ${all?"width":"iwidth"}="${defines["cscNumber"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="3" ref="cscNumber" flagKey  title="车牌号">
			 	<a class="${desc=="csc_number" ? "desc" : ""}${asc=="csc_number" ? "asc" : ""}" href="?${desc=="csc_number" ? "asc=csc_number" : ""}${(asc=="csc_number" || desc!="csc_number" )? "desc=csc_number" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车牌号
			 	</a>
			 	${lz:set("checkeds[]","cscNumber")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscCarNo || #request.defines['cscCarNo']!=null">
			 <td <s:if test="#request.defines['cscCarNo']>0">colspan="${defines["cscCarNo"]}" ${all?"width":"iwidth"}="${defines["cscCarNo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="4" ref="cscCarNo"   title="车牌名称">
			 	<a class="${desc=="csc_car_no" ? "desc" : ""}${asc=="csc_car_no" ? "asc" : ""}" href="?${desc=="csc_car_no" ? "asc=csc_car_no" : ""}${(asc=="csc_car_no" || desc!="csc_car_no" )? "desc=csc_car_no" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车牌名称
			 	</a>
			 	${lz:set("checkeds[]","cscCarNo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscModel || #request.defines['cscModel']!=null">
			 <td <s:if test="#request.defines['cscModel']>0">colspan="${defines["cscModel"]}" ${all?"width":"iwidth"}="${defines["cscModel"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="cscModel"   title="车型">
			 	<a class="${desc=="csc_model" ? "desc" : ""}${asc=="csc_model" ? "asc" : ""}" href="?${desc=="csc_model" ? "asc=csc_model" : ""}${(asc=="csc_model" || desc!="csc_model" )? "desc=csc_model" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车型
			 	</a>
			 	${lz:set("checkeds[]","cscModel")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscOutlets || #request.defines['cscOutlets']!=null">
			 <td <s:if test="#request.defines['cscOutlets']>0">colspan="${defines["cscOutlets"]}" ${all?"width":"iwidth"}="${defines["cscOutlets"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="cscOutlets"   title="网点">
			 	<a class="${desc=="csc_outlets" ? "desc" : ""}${asc=="csc_outlets" ? "asc" : ""}" href="?${desc=="csc_outlets" ? "asc=csc_outlets" : ""}${(asc=="csc_outlets" || desc!="csc_outlets" )? "desc=csc_outlets" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	网点
			 	</a>
			 	${lz:set("checkeds[]","cscOutlets")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscProvid || #request.defines['cscProvid']!=null">
			 <td <s:if test="#request.defines['cscProvid']>0">colspan="${defines["cscProvid"]}" ${all?"width":"iwidth"}="${defines["cscProvid"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="7" ref="cscProvid"   title="供应商">
			 	<a class="${desc=="csc_provid" ? "desc" : ""}${asc=="csc_provid" ? "asc" : ""}" href="?${desc=="csc_provid" ? "asc=csc_provid" : ""}${(asc=="csc_provid" || desc!="csc_provid" )? "desc=csc_provid" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	供应商
			 	</a>
			 	${lz:set("checkeds[]","cscProvid")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscRebate || #request.defines['cscRebate']!=null">
			 <td <s:if test="#request.defines['cscRebate']>0">colspan="${defines["cscRebate"]}" ${all?"width":"iwidth"}="${defines["cscRebate"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="cscRebate"   title="折扣">
			 	<a class="${desc=="csc_rebate" ? "desc" : ""}${asc=="csc_rebate" ? "asc" : ""}" href="?${desc=="csc_rebate" ? "asc=csc_rebate" : ""}${(asc=="csc_rebate" || desc!="csc_rebate" )? "desc=csc_rebate" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	折扣
			 	</a>
			 	${lz:set("checkeds[]","cscRebate")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscIsAd || #request.defines['cscIsAd']!=null">
			 <td <s:if test="#request.defines['cscIsAd']>0">colspan="${defines["cscIsAd"]}" ${all?"width":"iwidth"}="${defines["cscIsAd"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="9" ref="cscIsAd"   title="广告车？">
			 	<a class="${desc=="csc_is_ad" ? "desc" : ""}${asc=="csc_is_ad" ? "asc" : ""}" href="?${desc=="csc_is_ad" ? "asc=csc_is_ad" : ""}${(asc=="csc_is_ad" || desc!="csc_is_ad" )? "desc=csc_is_ad" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	广告车？
			 	</a>
			 	${lz:set("checkeds[]","cscIsAd")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscMobile || #request.defines['cscMobile']!=null">
			 <td <s:if test="#request.defines['cscMobile']>0">colspan="${defines["cscMobile"]}" ${all?"width":"iwidth"}="${defines["cscMobile"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="10" ref="cscMobile"   title="手机号码">
			 	<a class="${desc=="csc_mobile" ? "desc" : ""}${asc=="csc_mobile" ? "asc" : ""}" href="?${desc=="csc_mobile" ? "asc=csc_mobile" : ""}${(asc=="csc_mobile" || desc!="csc_mobile" )? "desc=csc_mobile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	手机号码
			 	</a>
			 	${lz:set("checkeds[]","cscMobile")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscPassword || #request.defines['cscPassword']!=null">
			 <td <s:if test="#request.defines['cscPassword']>0">colspan="${defines["cscPassword"]}" ${all?"width":"iwidth"}="${defines["cscPassword"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="11" ref="cscPassword"   title="服务密码">
			 	<a class="${desc=="csc_password" ? "desc" : ""}${asc=="csc_password" ? "asc" : ""}" href="?${desc=="csc_password" ? "asc=csc_password" : ""}${(asc=="csc_password" || desc!="csc_password" )? "desc=csc_password" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	服务密码
			 	</a>
			 	${lz:set("checkeds[]","cscPassword")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscOilCard || #request.defines['cscOilCard']!=null">
			 <td <s:if test="#request.defines['cscOilCard']>0">colspan="${defines["cscOilCard"]}" ${all?"width":"iwidth"}="${defines["cscOilCard"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="12" ref="cscOilCard"   title="加油卡号">
			 	<a class="${desc=="csc_oil_card" ? "desc" : ""}${asc=="csc_oil_card" ? "asc" : ""}" href="?${desc=="csc_oil_card" ? "asc=csc_oil_card" : ""}${(asc=="csc_oil_card" || desc!="csc_oil_card" )? "desc=csc_oil_card" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	加油卡号
			 	</a>
			 	${lz:set("checkeds[]","cscOilCard")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscVin || #request.defines['cscVin']!=null">
			 <td <s:if test="#request.defines['cscVin']>0">colspan="${defines["cscVin"]}" ${all?"width":"iwidth"}="${defines["cscVin"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="13" ref="cscVin"   title="车架号">
			 	<a class="${desc=="csc_vin" ? "desc" : ""}${asc=="csc_vin" ? "asc" : ""}" href="?${desc=="csc_vin" ? "asc=csc_vin" : ""}${(asc=="csc_vin" || desc!="csc_vin" )? "desc=csc_vin" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车架号
			 	</a>
			 	${lz:set("checkeds[]","cscVin")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscEngineNo || #request.defines['cscEngineNo']!=null">
			 <td <s:if test="#request.defines['cscEngineNo']>0">colspan="${defines["cscEngineNo"]}" ${all?"width":"iwidth"}="${defines["cscEngineNo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="14" ref="cscEngineNo"   title="发动机号">
			 	<a class="${desc=="csc_engine_no" ? "desc" : ""}${asc=="csc_engine_no" ? "asc" : ""}" href="?${desc=="csc_engine_no" ? "asc=csc_engine_no" : ""}${(asc=="csc_engine_no" || desc!="csc_engine_no" )? "desc=csc_engine_no" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	发动机号
			 	</a>
			 	${lz:set("checkeds[]","cscEngineNo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscBuyDate || #request.defines['cscBuyDate']!=null">
			 <td <s:if test="#request.defines['cscBuyDate']>0">colspan="${defines["cscBuyDate"]}" ${all?"width":"iwidth"}="${defines["cscBuyDate"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="15" ref="cscBuyDate"   title="购车日">
			 	<a class="${desc=="csc_buy_date" ? "desc" : ""}${asc=="csc_buy_date" ? "asc" : ""}" href="?${desc=="csc_buy_date" ? "asc=csc_buy_date" : ""}${(asc=="csc_buy_date" || desc!="csc_buy_date" )? "desc=csc_buy_date" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	购车日
			 	</a>
			 	${lz:set("checkeds[]","cscBuyDate")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscBargainNo || #request.defines['cscBargainNo']!=null">
			 <td <s:if test="#request.defines['cscBargainNo']>0">colspan="${defines["cscBargainNo"]}" ${all?"width":"iwidth"}="${defines["cscBargainNo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="16" ref="cscBargainNo"   title="合同号">
			 	<a class="${desc=="csc_bargain_no" ? "desc" : ""}${asc=="csc_bargain_no" ? "asc" : ""}" href="?${desc=="csc_bargain_no" ? "asc=csc_bargain_no" : ""}${(asc=="csc_bargain_no" || desc!="csc_bargain_no" )? "desc=csc_bargain_no" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	合同号
			 	</a>
			 	${lz:set("checkeds[]","cscBargainNo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscTaxPrice || #request.defines['cscTaxPrice']!=null">
			 <td <s:if test="#request.defines['cscTaxPrice']>0">colspan="${defines["cscTaxPrice"]}" ${all?"width":"iwidth"}="${defines["cscTaxPrice"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="17" ref="cscTaxPrice"   title="购置税">
			 	<a class="${desc=="csc_tax_price" ? "desc" : ""}${asc=="csc_tax_price" ? "asc" : ""}" href="?${desc=="csc_tax_price" ? "asc=csc_tax_price" : ""}${(asc=="csc_tax_price" || desc!="csc_tax_price" )? "desc=csc_tax_price" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	购置税
			 	</a>
			 	${lz:set("checkeds[]","cscTaxPrice")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscBuyPrice || #request.defines['cscBuyPrice']!=null">
			 <td <s:if test="#request.defines['cscBuyPrice']>0">colspan="${defines["cscBuyPrice"]}" ${all?"width":"iwidth"}="${defines["cscBuyPrice"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="18" ref="cscBuyPrice"   title="车价">
			 	<a class="${desc=="csc_buy_price" ? "desc" : ""}${asc=="csc_buy_price" ? "asc" : ""}" href="?${desc=="csc_buy_price" ? "asc=csc_buy_price" : ""}${(asc=="csc_buy_price" || desc!="csc_buy_price" )? "desc=csc_buy_price" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车价
			 	</a>
			 	${lz:set("checkeds[]","cscBuyPrice")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscCertific || #request.defines['cscCertific']!=null">
			 <td <s:if test="#request.defines['cscCertific']>0">colspan="${defines["cscCertific"]}" ${all?"width":"iwidth"}="${defines["cscCertific"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="19" ref="cscCertific"   title="合格证号">
			 	<a class="${desc=="csc_certific" ? "desc" : ""}${asc=="csc_certific" ? "asc" : ""}" href="?${desc=="csc_certific" ? "asc=csc_certific" : ""}${(asc=="csc_certific" || desc!="csc_certific" )? "desc=csc_certific" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	合格证号
			 	</a>
			 	${lz:set("checkeds[]","cscCertific")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscFactoryNo || #request.defines['cscFactoryNo']!=null">
			 <td <s:if test="#request.defines['cscFactoryNo']>0">colspan="${defines["cscFactoryNo"]}" ${all?"width":"iwidth"}="${defines["cscFactoryNo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="20" ref="cscFactoryNo"   title="厂牌型号">
			 	<a class="${desc=="csc_factory_no" ? "desc" : ""}${asc=="csc_factory_no" ? "asc" : ""}" href="?${desc=="csc_factory_no" ? "asc=csc_factory_no" : ""}${(asc=="csc_factory_no" || desc!="csc_factory_no" )? "desc=csc_factory_no" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	厂牌型号
			 	</a>
			 	${lz:set("checkeds[]","cscFactoryNo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscColor || #request.defines['cscColor']!=null">
			 <td <s:if test="#request.defines['cscColor']>0">colspan="${defines["cscColor"]}" ${all?"width":"iwidth"}="${defines["cscColor"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="21" ref="cscColor"   title="颜色">
			 	<a class="${desc=="csc_color" ? "desc" : ""}${asc=="csc_color" ? "asc" : ""}" href="?${desc=="csc_color" ? "asc=csc_color" : ""}${(asc=="csc_color" || desc!="csc_color" )? "desc=csc_color" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	颜色
			 	</a>
			 	${lz:set("checkeds[]","cscColor")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscPreview || #request.defines['cscPreview']!=null">
			 <td <s:if test="#request.defines['cscPreview']>0">colspan="${defines["cscPreview"]}" ${all?"width":"iwidth"}="${defines["cscPreview"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="22" ref="cscPreview"   title="车辆主图">
			 	<a class="${desc=="csc_preview" ? "desc" : ""}${asc=="csc_preview" ? "asc" : ""}" href="?${desc=="csc_preview" ? "asc=csc_preview" : ""}${(asc=="csc_preview" || desc!="csc_preview" )? "desc=csc_preview" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车辆主图
			 	</a>
			 	${lz:set("checkeds[]","cscPreview")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscImages || #request.defines['cscImages']!=null">
			 <td <s:if test="#request.defines['cscImages']>0">colspan="${defines["cscImages"]}" ${all?"width":"iwidth"}="${defines["cscImages"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="23" ref="cscImages"   title="照片列表">
			 	<a class="${desc=="csc_images" ? "desc" : ""}${asc=="csc_images" ? "asc" : ""}" href="?${desc=="csc_images" ? "asc=csc_images" : ""}${(asc=="csc_images" || desc!="csc_images" )? "desc=csc_images" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	照片列表
			 	</a>
			 	${lz:set("checkeds[]","cscImages")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscMaintKm || #request.defines['cscMaintKm']!=null">
			 <td <s:if test="#request.defines['cscMaintKm']>0">colspan="${defines["cscMaintKm"]}" ${all?"width":"iwidth"}="${defines["cscMaintKm"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="24" ref="cscMaintKm"   title="保养公里">
			 	<a class="${desc=="csc_maint_km" ? "desc" : ""}${asc=="csc_maint_km" ? "asc" : ""}" href="?${desc=="csc_maint_km" ? "asc=csc_maint_km" : ""}${(asc=="csc_maint_km" || desc!="csc_maint_km" )? "desc=csc_maint_km" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	保养公里
			 	</a>
			 	${lz:set("checkeds[]","cscMaintKm")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscCheckIn || #request.defines['cscCheckIn']!=null">
			 <td <s:if test="#request.defines['cscCheckIn']>0">colspan="${defines["cscCheckIn"]}" ${all?"width":"iwidth"}="${defines["cscCheckIn"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="25" ref="cscCheckIn"   title="初次登记">
			 	<a class="${desc=="csc_check_in" ? "desc" : ""}${asc=="csc_check_in" ? "asc" : ""}" href="?${desc=="csc_check_in" ? "asc=csc_check_in" : ""}${(asc=="csc_check_in" || desc!="csc_check_in" )? "desc=csc_check_in" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	初次登记
			 	</a>
			 	${lz:set("checkeds[]","cscCheckIn")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscAnnualPre || #request.defines['cscAnnualPre']!=null">
			 <td <s:if test="#request.defines['cscAnnualPre']>0">colspan="${defines["cscAnnualPre"]}" ${all?"width":"iwidth"}="${defines["cscAnnualPre"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="26" ref="cscAnnualPre"   title="上次年检日期">
			 	<a class="${desc=="csc_annual_pre" ? "desc" : ""}${asc=="csc_annual_pre" ? "asc" : ""}" href="?${desc=="csc_annual_pre" ? "asc=csc_annual_pre" : ""}${(asc=="csc_annual_pre" || desc!="csc_annual_pre" )? "desc=csc_annual_pre" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	上次年检日期
			 	</a>
			 	${lz:set("checkeds[]","cscAnnualPre")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscAnnual || #request.defines['cscAnnual']!=null">
			 <td <s:if test="#request.defines['cscAnnual']>0">colspan="${defines["cscAnnual"]}" ${all?"width":"iwidth"}="${defines["cscAnnual"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="27" ref="cscAnnual"   title="实际年检日期">
			 	<a class="${desc=="csc_annual" ? "desc" : ""}${asc=="csc_annual" ? "asc" : ""}" href="?${desc=="csc_annual" ? "asc=csc_annual" : ""}${(asc=="csc_annual" || desc!="csc_annual" )? "desc=csc_annual" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	实际年检日期
			 	</a>
			 	${lz:set("checkeds[]","cscAnnual")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscAnnualInfo || #request.defines['cscAnnualInfo']!=null">
			 <td <s:if test="#request.defines['cscAnnualInfo']>0">colspan="${defines["cscAnnualInfo"]}" ${all?"width":"iwidth"}="${defines["cscAnnualInfo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="28" ref="cscAnnualInfo"   title="具体年检信息">
			 	<a class="${desc=="csc_annual_info" ? "desc" : ""}${asc=="csc_annual_info" ? "asc" : ""}" href="?${desc=="csc_annual_info" ? "asc=csc_annual_info" : ""}${(asc=="csc_annual_info" || desc!="csc_annual_info" )? "desc=csc_annual_info" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	具体年检信息
			 	</a>
			 	${lz:set("checkeds[]","cscAnnualInfo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscAnnualNext || #request.defines['cscAnnualNext']!=null">
			 <td <s:if test="#request.defines['cscAnnualNext']>0">colspan="${defines["cscAnnualNext"]}" ${all?"width":"iwidth"}="${defines["cscAnnualNext"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="29" ref="cscAnnualNext"   title="预计下次年检">
			 	<a class="${desc=="csc_annual_next" ? "desc" : ""}${asc=="csc_annual_next" ? "asc" : ""}" href="?${desc=="csc_annual_next" ? "asc=csc_annual_next" : ""}${(asc=="csc_annual_next" || desc!="csc_annual_next" )? "desc=csc_annual_next" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	预计下次年检
			 	</a>
			 	${lz:set("checkeds[]","cscAnnualNext")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscTiDate || #request.defines['cscTiDate']!=null">
			 <td <s:if test="#request.defines['cscTiDate']>0">colspan="${defines["cscTiDate"]}" ${all?"width":"iwidth"}="${defines["cscTiDate"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="30" ref="cscTiDate"   title="交强险投保">
			 	<a class="${desc=="csc_ti_date" ? "desc" : ""}${asc=="csc_ti_date" ? "asc" : ""}" href="?${desc=="csc_ti_date" ? "asc=csc_ti_date" : ""}${(asc=="csc_ti_date" || desc!="csc_ti_date" )? "desc=csc_ti_date" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	交强险投保
			 	</a>
			 	${lz:set("checkeds[]","cscTiDate")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscTiUnit || #request.defines['cscTiUnit']!=null">
			 <td <s:if test="#request.defines['cscTiUnit']>0">colspan="${defines["cscTiUnit"]}" ${all?"width":"iwidth"}="${defines["cscTiUnit"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="31" ref="cscTiUnit"   title="交强险到期">
			 	<a class="${desc=="csc_ti_unit" ? "desc" : ""}${asc=="csc_ti_unit" ? "asc" : ""}" href="?${desc=="csc_ti_unit" ? "asc=csc_ti_unit" : ""}${(asc=="csc_ti_unit" || desc!="csc_ti_unit" )? "desc=csc_ti_unit" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	交强险到期
			 	</a>
			 	${lz:set("checkeds[]","cscTiUnit")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscTiNo || #request.defines['cscTiNo']!=null">
			 <td <s:if test="#request.defines['cscTiNo']>0">colspan="${defines["cscTiNo"]}" ${all?"width":"iwidth"}="${defines["cscTiNo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="32" ref="cscTiNo"   title="交强险保单">
			 	<a class="${desc=="csc_ti_no" ? "desc" : ""}${asc=="csc_ti_no" ? "asc" : ""}" href="?${desc=="csc_ti_no" ? "asc=csc_ti_no" : ""}${(asc=="csc_ti_no" || desc!="csc_ti_no" )? "desc=csc_ti_no" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	交强险保单
			 	</a>
			 	${lz:set("checkeds[]","cscTiNo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscBiDate || #request.defines['cscBiDate']!=null">
			 <td <s:if test="#request.defines['cscBiDate']>0">colspan="${defines["cscBiDate"]}" ${all?"width":"iwidth"}="${defines["cscBiDate"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="33" ref="cscBiDate"   title="商业险投保">
			 	<a class="${desc=="csc_bi_date" ? "desc" : ""}${asc=="csc_bi_date" ? "asc" : ""}" href="?${desc=="csc_bi_date" ? "asc=csc_bi_date" : ""}${(asc=="csc_bi_date" || desc!="csc_bi_date" )? "desc=csc_bi_date" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	商业险投保
			 	</a>
			 	${lz:set("checkeds[]","cscBiDate")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscBiUnit || #request.defines['cscBiUnit']!=null">
			 <td <s:if test="#request.defines['cscBiUnit']>0">colspan="${defines["cscBiUnit"]}" ${all?"width":"iwidth"}="${defines["cscBiUnit"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="34" ref="cscBiUnit"   title="商业险到期">
			 	<a class="${desc=="csc_bi_unit" ? "desc" : ""}${asc=="csc_bi_unit" ? "asc" : ""}" href="?${desc=="csc_bi_unit" ? "asc=csc_bi_unit" : ""}${(asc=="csc_bi_unit" || desc!="csc_bi_unit" )? "desc=csc_bi_unit" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	商业险到期
			 	</a>
			 	${lz:set("checkeds[]","cscBiUnit")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscBiNo || #request.defines['cscBiNo']!=null">
			 <td <s:if test="#request.defines['cscBiNo']>0">colspan="${defines["cscBiNo"]}" ${all?"width":"iwidth"}="${defines["cscBiNo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="35" ref="cscBiNo"   title="商业险保单">
			 	<a class="${desc=="csc_bi_no" ? "desc" : ""}${asc=="csc_bi_no" ? "asc" : ""}" href="?${desc=="csc_bi_no" ? "asc=csc_bi_no" : ""}${(asc=="csc_bi_no" || desc!="csc_bi_no" )? "desc=csc_bi_no" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	商业险保单
			 	</a>
			 	${lz:set("checkeds[]","cscBiNo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscBiType || #request.defines['cscBiType']!=null">
			 <td <s:if test="#request.defines['cscBiType']>0">colspan="${defines["cscBiType"]}" ${all?"width":"iwidth"}="${defines["cscBiType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="36" ref="cscBiType"   title="商业险类型">
			 	<a class="${desc=="csc_bi_type" ? "desc" : ""}${asc=="csc_bi_type" ? "asc" : ""}" href="?${desc=="csc_bi_type" ? "asc=csc_bi_type" : ""}${(asc=="csc_bi_type" || desc!="csc_bi_type" )? "desc=csc_bi_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	商业险类型
			 	</a>
			 	${lz:set("checkeds[]","cscBiType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscBiLimit || #request.defines['cscBiLimit']!=null">
			 <td <s:if test="#request.defines['cscBiLimit']>0">colspan="${defines["cscBiLimit"]}" ${all?"width":"iwidth"}="${defines["cscBiLimit"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="37" ref="cscBiLimit"   title="商业险金额">
			 	<a class="${desc=="csc_bi_limit" ? "desc" : ""}${asc=="csc_bi_limit" ? "asc" : ""}" href="?${desc=="csc_bi_limit" ? "asc=csc_bi_limit" : ""}${(asc=="csc_bi_limit" || desc!="csc_bi_limit" )? "desc=csc_bi_limit" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	商业险金额
			 	</a>
			 	${lz:set("checkeds[]","cscBiLimit")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscBiCompany || #request.defines['cscBiCompany']!=null">
			 <td <s:if test="#request.defines['cscBiCompany']>0">colspan="${defines["cscBiCompany"]}" ${all?"width":"iwidth"}="${defines["cscBiCompany"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="38" ref="cscBiCompany"   title="保险公司">
			 	<a class="${desc=="csc_bi_company" ? "desc" : ""}${asc=="csc_bi_company" ? "asc" : ""}" href="?${desc=="csc_bi_company" ? "asc=csc_bi_company" : ""}${(asc=="csc_bi_company" || desc!="csc_bi_company" )? "desc=csc_bi_company" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	保险公司
			 	</a>
			 	${lz:set("checkeds[]","cscBiCompany")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscEditor || #request.defines['cscEditor']!=null">
			 <td <s:if test="#request.defines['cscEditor']>0">colspan="${defines["cscEditor"]}" ${all?"width":"iwidth"}="${defines["cscEditor"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="39" ref="cscEditor"   title="修改人">
			 	<a class="${desc=="csc_editor" ? "desc" : ""}${asc=="csc_editor" ? "asc" : ""}" href="?${desc=="csc_editor" ? "asc=csc_editor" : ""}${(asc=="csc_editor" || desc!="csc_editor" )? "desc=csc_editor" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改人
			 	</a>
			 	${lz:set("checkeds[]","cscEditor")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscUpdateTime || #request.defines['cscUpdateTime']!=null">
			 <td <s:if test="#request.defines['cscUpdateTime']>0">colspan="${defines["cscUpdateTime"]}" ${all?"width":"iwidth"}="${defines["cscUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="40" ref="cscUpdateTime"   title="修改时间">
			 	<a class="${desc=="csc_update_time" ? "desc" : ""}${asc=="csc_update_time" ? "asc" : ""}" href="?${desc=="csc_update_time" ? "asc=csc_update_time" : ""}${(asc=="csc_update_time" || desc!="csc_update_time" )? "desc=csc_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 	${lz:set("checkeds[]","cscUpdateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscAddTime || #request.defines['cscAddTime']!=null">
			 <td <s:if test="#request.defines['cscAddTime']>0">colspan="${defines["cscAddTime"]}" ${all?"width":"iwidth"}="${defines["cscAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="41" ref="cscAddTime"   title="添加时间">
			 	<a class="${desc=="csc_add_time" ? "desc" : ""}${asc=="csc_add_time" ? "asc" : ""}" href="?${desc=="csc_add_time" ? "asc=csc_add_time" : ""}${(asc=="csc_add_time" || desc!="csc_add_time" )? "desc=csc_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","cscAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscRemark || #request.defines['cscRemark']!=null">
			 <td <s:if test="#request.defines['cscRemark']>0">colspan="${defines["cscRemark"]}" ${all?"width":"iwidth"}="${defines["cscRemark"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="42" ref="cscRemark"   title="备注">
			 	<a class="${desc=="csc_remark" ? "desc" : ""}${asc=="csc_remark" ? "asc" : ""}" href="?${desc=="csc_remark" ? "asc=csc_remark" : ""}${(asc=="csc_remark" || desc!="csc_remark" )? "desc=csc_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备注
			 	</a>
			 	${lz:set("checkeds[]","cscRemark")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscMemo || #request.defines['cscMemo']!=null">
			 <td <s:if test="#request.defines['cscMemo']>0">colspan="${defines["cscMemo"]}" ${all?"width":"iwidth"}="${defines["cscMemo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="43" ref="cscMemo"   title="备忘信息">
			 	<a class="${desc=="csc_memo" ? "desc" : ""}${asc=="csc_memo" ? "asc" : ""}" href="?${desc=="csc_memo" ? "asc=csc_memo" : ""}${(asc=="csc_memo" || desc!="csc_memo" )? "desc=csc_memo" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备忘信息
			 	</a>
			 	${lz:set("checkeds[]","cscMemo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscOnPsd || #request.defines['cscOnPsd']!=null">
			 <td <s:if test="#request.defines['cscOnPsd']>0">colspan="${defines["cscOnPsd"]}" ${all?"width":"iwidth"}="${defines["cscOnPsd"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="44" ref="cscOnPsd"   title="上线密码">
			 	<a class="${desc=="csc_on_psd" ? "desc" : ""}${asc=="csc_on_psd" ? "asc" : ""}" href="?${desc=="csc_on_psd" ? "asc=csc_on_psd" : ""}${(asc=="csc_on_psd" || desc!="csc_on_psd" )? "desc=csc_on_psd" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	上线密码
			 	</a>
			 	${lz:set("checkeds[]","cscOnPsd")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscSetup || #request.defines['cscSetup']!=null">
			 <td <s:if test="#request.defines['cscSetup']>0">colspan="${defines["cscSetup"]}" ${all?"width":"iwidth"}="${defines["cscSetup"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="45" ref="cscSetup"   title="车机安装">
			 	<a class="${desc=="csc_setup" ? "desc" : ""}${asc=="csc_setup" ? "asc" : ""}" href="?${desc=="csc_setup" ? "asc=csc_setup" : ""}${(asc=="csc_setup" || desc!="csc_setup" )? "desc=csc_setup" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车机安装
			 	</a>
			 	${lz:set("checkeds[]","cscSetup")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscDeviceVesion || #request.defines['cscDeviceVesion']!=null">
			 <td <s:if test="#request.defines['cscDeviceVesion']>0">colspan="${defines["cscDeviceVesion"]}" ${all?"width":"iwidth"}="${defines["cscDeviceVesion"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="46" ref="cscDeviceVesion"   title="下位机版本">
			 	<a class="${desc=="csc_device_vesion" ? "desc" : ""}${asc=="csc_device_vesion" ? "asc" : ""}" href="?${desc=="csc_device_vesion" ? "asc=csc_device_vesion" : ""}${(asc=="csc_device_vesion" || desc!="csc_device_vesion" )? "desc=csc_device_vesion" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	下位机版本
			 	</a>
			 	${lz:set("checkeds[]","cscDeviceVesion")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscMqttFlag || #request.defines['cscMqttFlag']!=null">
			 <td <s:if test="#request.defines['cscMqttFlag']>0">colspan="${defines["cscMqttFlag"]}" ${all?"width":"iwidth"}="${defines["cscMqttFlag"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="47" ref="cscMqttFlag"   title="服务器标识">
			 	<a class="${desc=="csc_mqtt_flag" ? "desc" : ""}${asc=="csc_mqtt_flag" ? "asc" : ""}" href="?${desc=="csc_mqtt_flag" ? "asc=csc_mqtt_flag" : ""}${(asc=="csc_mqtt_flag" || desc!="csc_mqtt_flag" )? "desc=csc_mqtt_flag" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	服务器标识
			 	</a>
			 	${lz:set("checkeds[]","cscMqttFlag")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscMask || #request.defines['cscMask']!=null">
			 <td <s:if test="#request.defines['cscMask']>0">colspan="${defines["cscMask"]}" ${all?"width":"iwidth"}="${defines["cscMask"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="48" ref="cscMask"   title="程序掩码">
			 	<a class="${desc=="csc_mask" ? "desc" : ""}${asc=="csc_mask" ? "asc" : ""}" href="?${desc=="csc_mask" ? "asc=csc_mask" : ""}${(asc=="csc_mask" || desc!="csc_mask" )? "desc=csc_mask" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	程序掩码
			 	</a>
			 	${lz:set("checkeds[]","cscMask")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscStatus || #request.defines['cscStatus']!=null">
			 <td <s:if test="#request.defines['cscStatus']>0">colspan="${defines["cscStatus"]}" ${all?"width":"iwidth"}="${defines["cscStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="49" ref="cscStatus"   title="状态">
			 	<a class="${desc=="csc_status" ? "desc" : ""}${asc=="csc_status" ? "asc" : ""}" href="?${desc=="csc_status" ? "asc=csc_status" : ""}${(asc=="csc_status" || desc!="csc_status" )? "desc=csc_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","cscStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="50" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="51" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:AddCar()" style="text-decoration:underline">添加</a>
				</s:if>
				<s:else>&nbsp;</s:else>
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
		  <tr id="${item.cscId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.cscId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscId || #request.defines['cscId']!=null">
				<s:if test="#request.defines['cscId']>0">
					${lz:set("注释","****cscId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscId",lz:indexOf(fieldName,"cscId")>-1)}
				  		<s:if test="#request.atCscId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscId字段的字串格式化输出****")}
					<td ref="cscId" class="td ">
						 
						 	${lz:or(item$cscId[i.count-1],lz:left(item.cscId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscHost || #request.defines['cscHost']!=null">
				<s:if test="#request.defines['cscHost']>0">
					${lz:set("注释","****cscHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscHost",lz:indexOf(fieldName,"cscHost")>-1)}
				  		<s:if test="#request.atCscHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscHost字段的字串格式化输出****")}
					<td ref="cscHost" class="td " relate="${item.cscHost}">
						 
						 	<a <s:if test="#item.cscHost!=null && #item.cscHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.cscHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscHost[i.count-1],lz:left(item.cscHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscNumber || #request.defines['cscNumber']!=null">
				<s:if test="#request.defines['cscNumber']>0">
					${lz:set("注释","****cscNumber关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscNumber))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscNumber",lz:indexOf(fieldName,"cscNumber")>-1)}
				  		<s:if test="#request.atCscNumber==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscNumber字段的字串格式化输出****")}
					<td ref="cscNumber" class="td  node">
						 
						 	${lz:or(item$cscNumber[i.count-1],lz:left(item.cscNumber$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscCarNo || #request.defines['cscCarNo']!=null">
				<s:if test="#request.defines['cscCarNo']>0">
					${lz:set("注释","****cscCarNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscCarNo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscCarNo",lz:indexOf(fieldName,"cscCarNo")>-1)}
				  		<s:if test="#request.atCscCarNo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscCarNo字段的字串格式化输出****")}
					<td ref="cscCarNo" class="td ">
						 
						 	${lz:or(item$cscCarNo[i.count-1],lz:left(item.cscCarNo$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscModel || #request.defines['cscModel']!=null">
				<s:if test="#request.defines['cscModel']>0">
					${lz:set("注释","****cscModel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscModel))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscModel",lz:indexOf(fieldName,"cscModel")>-1)}
				  		<s:if test="#request.atCscModel==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscModel字段的字串格式化输出****")}
					<td ref="cscModel" class="td " relate="${item.cscModel}">
						 
						 	<a <s:if test="#item.cscModel!=null && #item.cscModel!=''"> onclick="window.href('${basePath}${proname}/object/carmodel_details.do?key=${item.cscModel}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscModel[i.count-1],lz:left(item.cscModel$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscOutlets || #request.defines['cscOutlets']!=null">
				<s:if test="#request.defines['cscOutlets']>0">
					${lz:set("注释","****cscOutlets关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscOutlets))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscOutlets",lz:indexOf(fieldName,"cscOutlets")>-1)}
				  		<s:if test="#request.atCscOutlets==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscOutlets字段的字串格式化输出****")}
					<td ref="cscOutlets" class="td " relate="${item.cscOutlets}">
						 
						 	<a <s:if test="#item.cscOutlets!=null && #item.cscOutlets!=''"> onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${item.cscOutlets}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscOutlets[i.count-1],lz:left(item.cscOutlets$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscProvid || #request.defines['cscProvid']!=null">
				<s:if test="#request.defines['cscProvid']>0">
					${lz:set("注释","****cscProvid关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscProvid))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscProvid",lz:indexOf(fieldName,"cscProvid")>-1)}
				  		<s:if test="#request.atCscProvid==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscProvid字段的字串格式化输出****")}
					<td ref="cscProvid" class="td " relate="${item.cscProvid}">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.cscId}','provid')"></a>
							 </s:if>
					     </s:if>
						 
						 	<a <s:if test="#item.cscProvid!=null && #item.cscProvid!=''"> onclick="window.href('${basePath}${proname}/configurator/provid_details.do?key=${item.cscProvid}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscProvid[i.count-1],lz:left(item.cscProvid$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscRebate || #request.defines['cscRebate']!=null">
				<s:if test="#request.defines['cscRebate']>0">
					${lz:set("注释","****cscRebate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscRebate))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscRebate",lz:indexOf(fieldName,"cscRebate")>-1)}
				  		<s:if test="#request.atCscRebate==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscRebate字段的字串格式化输出****")}
					<td ref="cscRebate" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.cscId}','rebate')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$cscRebate[i.count-1],lz:left(item.cscRebate$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscIsAd || #request.defines['cscIsAd']!=null">
				<s:if test="#request.defines['cscIsAd']>0">
					${lz:set("注释","****cscIsAd关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscIsAd))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscIsAd",lz:indexOf(fieldName,"cscIsAd")>-1)}
				  		<s:if test="#request.atCscIsAd==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscIsAd字段的字串格式化输出****")}
					<td ref="cscIsAd" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.cscId}','isad')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$cscIsAd[i.count-1],lz:left(item.cscIsAd$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscMobile || #request.defines['cscMobile']!=null">
				<s:if test="#request.defines['cscMobile']>0">
					${lz:set("注释","****cscMobile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscMobile))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscMobile",lz:indexOf(fieldName,"cscMobile")>-1)}
				  		<s:if test="#request.atCscMobile==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscMobile字段的字串格式化输出****")}
					<td ref="cscMobile" class="td ">
						 
						 	${lz:or(item$cscMobile[i.count-1],lz:left(item.cscMobile$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscPassword || #request.defines['cscPassword']!=null">
				<s:if test="#request.defines['cscPassword']>0">
					${lz:set("注释","****cscPassword关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscPassword))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscPassword",lz:indexOf(fieldName,"cscPassword")>-1)}
				  		<s:if test="#request.atCscPassword==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscPassword字段的字串格式化输出****")}
					<td ref="cscPassword" class="td ">
						 
						 	${lz:or(item$cscPassword[i.count-1],lz:left(item.cscPassword$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscOilCard || #request.defines['cscOilCard']!=null">
				<s:if test="#request.defines['cscOilCard']>0">
					${lz:set("注释","****cscOilCard关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscOilCard))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscOilCard",lz:indexOf(fieldName,"cscOilCard")>-1)}
				  		<s:if test="#request.atCscOilCard==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscOilCard字段的字串格式化输出****")}
					<td ref="cscOilCard" class="td ">
						 
						 	${lz:or(item$cscOilCard[i.count-1],lz:left(item.cscOilCard$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscVin || #request.defines['cscVin']!=null">
				<s:if test="#request.defines['cscVin']>0">
					${lz:set("注释","****cscVin关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscVin))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscVin",lz:indexOf(fieldName,"cscVin")>-1)}
				  		<s:if test="#request.atCscVin==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscVin字段的字串格式化输出****")}
					<td ref="cscVin" class="td ">
						 
						 	${lz:or(item$cscVin[i.count-1],lz:left(item.cscVin$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscEngineNo || #request.defines['cscEngineNo']!=null">
				<s:if test="#request.defines['cscEngineNo']>0">
					${lz:set("注释","****cscEngineNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscEngineNo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscEngineNo",lz:indexOf(fieldName,"cscEngineNo")>-1)}
				  		<s:if test="#request.atCscEngineNo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscEngineNo字段的字串格式化输出****")}
					<td ref="cscEngineNo" class="td ">
						 
						 	${lz:or(item$cscEngineNo[i.count-1],lz:left(item.cscEngineNo$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscBuyDate || #request.defines['cscBuyDate']!=null">
				<s:if test="#request.defines['cscBuyDate']>0">
					${lz:set("注释","****cscBuyDate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscBuyDate))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscBuyDate",lz:indexOf(fieldName,"cscBuyDate")>-1)}
				  		<s:if test="#request.atCscBuyDate==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscBuyDate字段的字串格式化输出****")}
					<td ref="cscBuyDate" class="td ">
						 
						 	${lz:or(item$cscBuyDate[i.count-1],lz:left(item.cscBuyDate$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscBargainNo || #request.defines['cscBargainNo']!=null">
				<s:if test="#request.defines['cscBargainNo']>0">
					${lz:set("注释","****cscBargainNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscBargainNo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscBargainNo",lz:indexOf(fieldName,"cscBargainNo")>-1)}
				  		<s:if test="#request.atCscBargainNo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscBargainNo字段的字串格式化输出****")}
					<td ref="cscBargainNo" class="td ">
						 
						 	${lz:or(item$cscBargainNo[i.count-1],lz:left(item.cscBargainNo$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscTaxPrice || #request.defines['cscTaxPrice']!=null">
				<s:if test="#request.defines['cscTaxPrice']>0">
					${lz:set("注释","****cscTaxPrice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscTaxPrice))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscTaxPrice",lz:indexOf(fieldName,"cscTaxPrice")>-1)}
				  		<s:if test="#request.atCscTaxPrice==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscTaxPrice字段的字串格式化输出****")}
					<td ref="cscTaxPrice" class="td ">
						 
						 	${lz:or(item$cscTaxPrice[i.count-1],lz:left(item.cscTaxPrice$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscBuyPrice || #request.defines['cscBuyPrice']!=null">
				<s:if test="#request.defines['cscBuyPrice']>0">
					${lz:set("注释","****cscBuyPrice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscBuyPrice))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscBuyPrice",lz:indexOf(fieldName,"cscBuyPrice")>-1)}
				  		<s:if test="#request.atCscBuyPrice==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscBuyPrice字段的字串格式化输出****")}
					<td ref="cscBuyPrice" class="td ">
						 
						 	${lz:or(item$cscBuyPrice[i.count-1],lz:left(item.cscBuyPrice$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscCertific || #request.defines['cscCertific']!=null">
				<s:if test="#request.defines['cscCertific']>0">
					${lz:set("注释","****cscCertific关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscCertific))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscCertific",lz:indexOf(fieldName,"cscCertific")>-1)}
				  		<s:if test="#request.atCscCertific==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscCertific字段的字串格式化输出****")}
					<td ref="cscCertific" class="td ">
						 
						 	${lz:or(item$cscCertific[i.count-1],lz:left(item.cscCertific$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscFactoryNo || #request.defines['cscFactoryNo']!=null">
				<s:if test="#request.defines['cscFactoryNo']>0">
					${lz:set("注释","****cscFactoryNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscFactoryNo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscFactoryNo",lz:indexOf(fieldName,"cscFactoryNo")>-1)}
				  		<s:if test="#request.atCscFactoryNo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscFactoryNo字段的字串格式化输出****")}
					<td ref="cscFactoryNo" class="td ">
						 
						 	${lz:or(item$cscFactoryNo[i.count-1],lz:left(item.cscFactoryNo$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscColor || #request.defines['cscColor']!=null">
				<s:if test="#request.defines['cscColor']>0">
					${lz:set("注释","****cscColor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscColor))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscColor",lz:indexOf(fieldName,"cscColor")>-1)}
				  		<s:if test="#request.atCscColor==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscColor字段的字串格式化输出****")}
					<td ref="cscColor" class="td ">
						 
						 	${lz:or(item$cscColor[i.count-1],lz:left(item.cscColor$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscPreview || #request.defines['cscPreview']!=null">
				<s:if test="#request.defines['cscPreview']>0">
					${lz:set("注释","****cscPreview关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscPreview))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscPreview",lz:indexOf(fieldName,"cscPreview")>-1)}
				  		<s:if test="#request.atCscPreview==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscPreview字段的字串格式化输出****")}
					<td ref="cscPreview" class="td ">
						 
						 	${lz:or(item$cscPreview[i.count-1],lz:left(item.cscPreview$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscImages || #request.defines['cscImages']!=null">
				<s:if test="#request.defines['cscImages']>0">
					${lz:set("注释","****cscImages关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscImages))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscImages",lz:indexOf(fieldName,"cscImages")>-1)}
				  		<s:if test="#request.atCscImages==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscImages字段的字串格式化输出****")}
					<td ref="cscImages" class="td ">
						 
						 	${lz:or(item$cscImages[i.count-1],lz:left(item.cscImages$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscMaintKm || #request.defines['cscMaintKm']!=null">
				<s:if test="#request.defines['cscMaintKm']>0">
					${lz:set("注释","****cscMaintKm关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscMaintKm))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscMaintKm",lz:indexOf(fieldName,"cscMaintKm")>-1)}
				  		<s:if test="#request.atCscMaintKm==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscMaintKm字段的字串格式化输出****")}
					<td ref="cscMaintKm" class="td ">
						 
						 	${lz:or(item$cscMaintKm[i.count-1],lz:left(item.cscMaintKm$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscCheckIn || #request.defines['cscCheckIn']!=null">
				<s:if test="#request.defines['cscCheckIn']>0">
					${lz:set("注释","****cscCheckIn关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscCheckIn))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscCheckIn",lz:indexOf(fieldName,"cscCheckIn")>-1)}
				  		<s:if test="#request.atCscCheckIn==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscCheckIn字段的字串格式化输出****")}
					<td ref="cscCheckIn" class="td ">
						 
						 	${lz:or(item$cscCheckIn[i.count-1],lz:left(item.cscCheckIn$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscAnnualPre || #request.defines['cscAnnualPre']!=null">
				<s:if test="#request.defines['cscAnnualPre']>0">
					${lz:set("注释","****cscAnnualPre关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscAnnualPre))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscAnnualPre",lz:indexOf(fieldName,"cscAnnualPre")>-1)}
				  		<s:if test="#request.atCscAnnualPre==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscAnnualPre字段的字串格式化输出****")}
					<td ref="cscAnnualPre" class="td ">
						 
						 	${lz:or(item$cscAnnualPre[i.count-1],lz:left(item.cscAnnualPre$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscAnnual || #request.defines['cscAnnual']!=null">
				<s:if test="#request.defines['cscAnnual']>0">
					${lz:set("注释","****cscAnnual关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscAnnual))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscAnnual",lz:indexOf(fieldName,"cscAnnual")>-1)}
				  		<s:if test="#request.atCscAnnual==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscAnnual字段的字串格式化输出****")}
					<td ref="cscAnnual" class="td ">
						 
						 	${lz:or(item$cscAnnual[i.count-1],lz:left(item.cscAnnual$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscAnnualInfo || #request.defines['cscAnnualInfo']!=null">
				<s:if test="#request.defines['cscAnnualInfo']>0">
					${lz:set("注释","****cscAnnualInfo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscAnnualInfo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscAnnualInfo",lz:indexOf(fieldName,"cscAnnualInfo")>-1)}
				  		<s:if test="#request.atCscAnnualInfo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscAnnualInfo字段的字串格式化输出****")}
					<td ref="cscAnnualInfo" class="td ">
						 
						 	${lz:or(item$cscAnnualInfo[i.count-1],lz:left(item.cscAnnualInfo$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscAnnualNext || #request.defines['cscAnnualNext']!=null">
				<s:if test="#request.defines['cscAnnualNext']>0">
					${lz:set("注释","****cscAnnualNext关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscAnnualNext))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscAnnualNext",lz:indexOf(fieldName,"cscAnnualNext")>-1)}
				  		<s:if test="#request.atCscAnnualNext==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscAnnualNext字段的字串格式化输出****")}
					<td ref="cscAnnualNext" class="td ">
						 
						 	${lz:or(item$cscAnnualNext[i.count-1],lz:left(item.cscAnnualNext$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscTiDate || #request.defines['cscTiDate']!=null">
				<s:if test="#request.defines['cscTiDate']>0">
					${lz:set("注释","****cscTiDate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscTiDate))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscTiDate",lz:indexOf(fieldName,"cscTiDate")>-1)}
				  		<s:if test="#request.atCscTiDate==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscTiDate字段的字串格式化输出****")}
					<td ref="cscTiDate" class="td ">
						 
						 	${lz:or(item$cscTiDate[i.count-1],lz:left(item.cscTiDate$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscTiUnit || #request.defines['cscTiUnit']!=null">
				<s:if test="#request.defines['cscTiUnit']>0">
					${lz:set("注释","****cscTiUnit关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscTiUnit))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscTiUnit",lz:indexOf(fieldName,"cscTiUnit")>-1)}
				  		<s:if test="#request.atCscTiUnit==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscTiUnit字段的字串格式化输出****")}
					<td ref="cscTiUnit" class="td ">
						 
						 	${lz:or(item$cscTiUnit[i.count-1],lz:left(item.cscTiUnit$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscTiNo || #request.defines['cscTiNo']!=null">
				<s:if test="#request.defines['cscTiNo']>0">
					${lz:set("注释","****cscTiNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscTiNo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscTiNo",lz:indexOf(fieldName,"cscTiNo")>-1)}
				  		<s:if test="#request.atCscTiNo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscTiNo字段的字串格式化输出****")}
					<td ref="cscTiNo" class="td ">
						 
						 	${lz:or(item$cscTiNo[i.count-1],lz:left(item.cscTiNo$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscBiDate || #request.defines['cscBiDate']!=null">
				<s:if test="#request.defines['cscBiDate']>0">
					${lz:set("注释","****cscBiDate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscBiDate))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscBiDate",lz:indexOf(fieldName,"cscBiDate")>-1)}
				  		<s:if test="#request.atCscBiDate==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscBiDate字段的字串格式化输出****")}
					<td ref="cscBiDate" class="td ">
						 
						 	${lz:or(item$cscBiDate[i.count-1],lz:left(item.cscBiDate$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscBiUnit || #request.defines['cscBiUnit']!=null">
				<s:if test="#request.defines['cscBiUnit']>0">
					${lz:set("注释","****cscBiUnit关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscBiUnit))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscBiUnit",lz:indexOf(fieldName,"cscBiUnit")>-1)}
				  		<s:if test="#request.atCscBiUnit==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscBiUnit字段的字串格式化输出****")}
					<td ref="cscBiUnit" class="td ">
						 
						 	${lz:or(item$cscBiUnit[i.count-1],lz:left(item.cscBiUnit$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscBiNo || #request.defines['cscBiNo']!=null">
				<s:if test="#request.defines['cscBiNo']>0">
					${lz:set("注释","****cscBiNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscBiNo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscBiNo",lz:indexOf(fieldName,"cscBiNo")>-1)}
				  		<s:if test="#request.atCscBiNo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscBiNo字段的字串格式化输出****")}
					<td ref="cscBiNo" class="td ">
						 
						 	${lz:or(item$cscBiNo[i.count-1],lz:left(item.cscBiNo$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscBiType || #request.defines['cscBiType']!=null">
				<s:if test="#request.defines['cscBiType']>0">
					${lz:set("注释","****cscBiType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscBiType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscBiType",lz:indexOf(fieldName,"cscBiType")>-1)}
				  		<s:if test="#request.atCscBiType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscBiType字段的字串格式化输出****")}
					<td ref="cscBiType" class="td ">
						 
						 	${lz:or(item$cscBiType[i.count-1],lz:left(item.cscBiType$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscBiLimit || #request.defines['cscBiLimit']!=null">
				<s:if test="#request.defines['cscBiLimit']>0">
					${lz:set("注释","****cscBiLimit关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscBiLimit))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscBiLimit",lz:indexOf(fieldName,"cscBiLimit")>-1)}
				  		<s:if test="#request.atCscBiLimit==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscBiLimit字段的字串格式化输出****")}
					<td ref="cscBiLimit" class="td ">
						 
						 	${lz:or(item$cscBiLimit[i.count-1],lz:left(item.cscBiLimit$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscBiCompany || #request.defines['cscBiCompany']!=null">
				<s:if test="#request.defines['cscBiCompany']>0">
					${lz:set("注释","****cscBiCompany关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscBiCompany))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscBiCompany",lz:indexOf(fieldName,"cscBiCompany")>-1)}
				  		<s:if test="#request.atCscBiCompany==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscBiCompany字段的字串格式化输出****")}
					<td ref="cscBiCompany" class="td ">
						 
						 	${lz:or(item$cscBiCompany[i.count-1],lz:left(item.cscBiCompany$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscEditor || #request.defines['cscEditor']!=null">
				<s:if test="#request.defines['cscEditor']>0">
					${lz:set("注释","****cscEditor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscEditor))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscEditor",lz:indexOf(fieldName,"cscEditor")>-1)}
				  		<s:if test="#request.atCscEditor==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscEditor字段的字串格式化输出****")}
					<td ref="cscEditor" class="td " relate="${item.cscEditor}">
						 
						 	<a <s:if test="#item.cscEditor!=null && #item.cscEditor!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.cscEditor}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscEditor[i.count-1],lz:left(item.cscEditor$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscUpdateTime || #request.defines['cscUpdateTime']!=null">
				<s:if test="#request.defines['cscUpdateTime']>0">
					${lz:set("注释","****cscUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscUpdateTime",lz:indexOf(fieldName,"cscUpdateTime")>-1)}
				  		<s:if test="#request.atCscUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscUpdateTime字段的字串格式化输出****")}
					<td ref="cscUpdateTime" class="td ">
						 
						 	${lz:or(item$cscUpdateTime[i.count-1],lz:left(item.cscUpdateTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscAddTime || #request.defines['cscAddTime']!=null">
				<s:if test="#request.defines['cscAddTime']>0">
					${lz:set("注释","****cscAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscAddTime",lz:indexOf(fieldName,"cscAddTime")>-1)}
				  		<s:if test="#request.atCscAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscAddTime字段的字串格式化输出****")}
					<td ref="cscAddTime" class="td ">
						 
						 	${lz:or(item$cscAddTime[i.count-1],lz:left(item.cscAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscRemark || #request.defines['cscRemark']!=null">
				<s:if test="#request.defines['cscRemark']>0">
					${lz:set("注释","****cscRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscRemark))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscRemark",lz:indexOf(fieldName,"cscRemark")>-1)}
				  		<s:if test="#request.atCscRemark==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscRemark字段的字串格式化输出****")}
					<td ref="cscRemark" class="td ">
						 
						 	${lz:or(item$cscRemark[i.count-1],lz:left(item.cscRemark$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscMemo || #request.defines['cscMemo']!=null">
				<s:if test="#request.defines['cscMemo']>0">
					${lz:set("注释","****cscMemo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscMemo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscMemo",lz:indexOf(fieldName,"cscMemo")>-1)}
				  		<s:if test="#request.atCscMemo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscMemo字段的字串格式化输出****")}
					<td ref="cscMemo" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.cscId}','memo')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$cscMemo[i.count-1],lz:left(item.cscMemo$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscOnPsd || #request.defines['cscOnPsd']!=null">
				<s:if test="#request.defines['cscOnPsd']>0">
					${lz:set("注释","****cscOnPsd关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscOnPsd))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscOnPsd",lz:indexOf(fieldName,"cscOnPsd")>-1)}
				  		<s:if test="#request.atCscOnPsd==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscOnPsd字段的字串格式化输出****")}
					<td ref="cscOnPsd" class="td ">
						 
						 	${lz:or(item$cscOnPsd[i.count-1],lz:left(item.cscOnPsd$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscSetup || #request.defines['cscSetup']!=null">
				<s:if test="#request.defines['cscSetup']>0">
					${lz:set("注释","****cscSetup关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscSetup))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscSetup",lz:indexOf(fieldName,"cscSetup")>-1)}
				  		<s:if test="#request.atCscSetup==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscSetup字段的字串格式化输出****")}
					<td ref="cscSetup" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.cscId}','setup')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$cscSetup[i.count-1],lz:left(item.cscSetup$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscDeviceVesion || #request.defines['cscDeviceVesion']!=null">
				<s:if test="#request.defines['cscDeviceVesion']>0">
					${lz:set("注释","****cscDeviceVesion关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscDeviceVesion))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscDeviceVesion",lz:indexOf(fieldName,"cscDeviceVesion")>-1)}
				  		<s:if test="#request.atCscDeviceVesion==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscDeviceVesion字段的字串格式化输出****")}
					<td ref="cscDeviceVesion" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.cscId}','vesion')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$cscDeviceVesion[i.count-1],lz:left(item.cscDeviceVesion$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscMqttFlag || #request.defines['cscMqttFlag']!=null">
				<s:if test="#request.defines['cscMqttFlag']>0">
					${lz:set("注释","****cscMqttFlag关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscMqttFlag))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscMqttFlag",lz:indexOf(fieldName,"cscMqttFlag")>-1)}
				  		<s:if test="#request.atCscMqttFlag==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscMqttFlag字段的字串格式化输出****")}
					<td ref="cscMqttFlag" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.cscId}','mqtt')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$cscMqttFlag[i.count-1],lz:left(item.cscMqttFlag$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscMask || #request.defines['cscMask']!=null">
				<s:if test="#request.defines['cscMask']>0">
					${lz:set("注释","****cscMask关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscMask))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscMask",lz:indexOf(fieldName,"cscMask")>-1)}
				  		<s:if test="#request.atCscMask==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscMask字段的字串格式化输出****")}
					<td ref="cscMask" class="td ">
						 
						 	${lz:or(item$cscMask[i.count-1],lz:left(item.cscMask$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscStatus || #request.defines['cscStatus']!=null">
				<s:if test="#request.defines['cscStatus']>0">
					${lz:set("注释","****cscStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscStatus",lz:indexOf(fieldName,"cscStatus")>-1)}
				  		<s:if test="#request.atCscStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscStatus字段的字串格式化输出****")}
					<td ref="cscStatus" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.cscId}','status')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$cscStatus[i.count-1],lz:left(item.cscStatus$,100))}
					</td>
				</s:else>
			</s:if>
		   ${lz:set("注释","*****************数据列表数据部分结束*****************")}		  
		  
		   
		    ${lz:set("注释","****自定义的单行记录操作句柄[request中的handles(String[])变量]****")}
			<td class="options">
				  <s:if test="#request.alias==null">	
					<s:if test="#request.CTRL.canExp[0]==true">
					<a href="javascript:HandleSel('远程控制','${item.cscId}')">远程控制</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[1]==true">
					<a href="javascript:HandleSel('车辆调度','${item.cscId}')">车辆调度</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[2]==true">
					<a href="javascript:HandleSel('车辆年检','${item.cscId}')">车辆年检</a>
					</s:if>
				</s:if>
				${lz:set("items",lz:split(handles,","))}
				<s:iterator value="#request.items" id="handle" status="i">
					${lz:set("isMenu",(lz:indexOf(handle,"@")==0))}
					<s:if test="#request.isMenu!=true">
					<a href="javascript:HandleSel('${handle}','${item.cscId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectCar('${item.cscId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditCar('${item.cscId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelCar('${item.cscId}','${item.cscNumber}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsCar('${item.cscId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.cscId || #request.defines['cscId']!=null">
				<s:if test="#request.defines['cscId']>0">
					${lz:set("注释","****cscId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscId",lz:indexOf(fieldName,"cscId")>-1)}				  		
				  		<s:if test="#request.atCscId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscHost || #request.defines['cscHost']!=null">
				<s:if test="#request.defines['cscHost']>0">
					${lz:set("注释","****cscHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscHost",lz:indexOf(fieldName,"cscHost")>-1)}				  		
				  		<s:if test="#request.atCscHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscNumber || #request.defines['cscNumber']!=null">
				<s:if test="#request.defines['cscNumber']>0">
					${lz:set("注释","****cscNumber关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscNumber",lz:indexOf(fieldName,"cscNumber")>-1)}				  		
				  		<s:if test="#request.atCscNumber==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscNumber[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscCarNo || #request.defines['cscCarNo']!=null">
				<s:if test="#request.defines['cscCarNo']>0">
					${lz:set("注释","****cscCarNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscCarNo",lz:indexOf(fieldName,"cscCarNo")>-1)}				  		
				  		<s:if test="#request.atCscCarNo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscCarNo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscModel || #request.defines['cscModel']!=null">
				<s:if test="#request.defines['cscModel']>0">
					${lz:set("注释","****cscModel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscModel",lz:indexOf(fieldName,"cscModel")>-1)}				  		
				  		<s:if test="#request.atCscModel==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscModel[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscOutlets || #request.defines['cscOutlets']!=null">
				<s:if test="#request.defines['cscOutlets']>0">
					${lz:set("注释","****cscOutlets关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscOutlets",lz:indexOf(fieldName,"cscOutlets")>-1)}				  		
				  		<s:if test="#request.atCscOutlets==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscOutlets[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscProvid || #request.defines['cscProvid']!=null">
				<s:if test="#request.defines['cscProvid']>0">
					${lz:set("注释","****cscProvid关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscProvid",lz:indexOf(fieldName,"cscProvid")>-1)}				  		
				  		<s:if test="#request.atCscProvid==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscProvid[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscRebate || #request.defines['cscRebate']!=null">
				<s:if test="#request.defines['cscRebate']>0">
					${lz:set("注释","****cscRebate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscRebate",lz:indexOf(fieldName,"cscRebate")>-1)}				  		
				  		<s:if test="#request.atCscRebate==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscRebate[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscIsAd || #request.defines['cscIsAd']!=null">
				<s:if test="#request.defines['cscIsAd']>0">
					${lz:set("注释","****cscIsAd关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscIsAd",lz:indexOf(fieldName,"cscIsAd")>-1)}				  		
				  		<s:if test="#request.atCscIsAd==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscIsAd[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscMobile || #request.defines['cscMobile']!=null">
				<s:if test="#request.defines['cscMobile']>0">
					${lz:set("注释","****cscMobile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscMobile",lz:indexOf(fieldName,"cscMobile")>-1)}				  		
				  		<s:if test="#request.atCscMobile==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscMobile[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscPassword || #request.defines['cscPassword']!=null">
				<s:if test="#request.defines['cscPassword']>0">
					${lz:set("注释","****cscPassword关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscPassword",lz:indexOf(fieldName,"cscPassword")>-1)}				  		
				  		<s:if test="#request.atCscPassword==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscPassword[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscOilCard || #request.defines['cscOilCard']!=null">
				<s:if test="#request.defines['cscOilCard']>0">
					${lz:set("注释","****cscOilCard关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscOilCard",lz:indexOf(fieldName,"cscOilCard")>-1)}				  		
				  		<s:if test="#request.atCscOilCard==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscOilCard[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscVin || #request.defines['cscVin']!=null">
				<s:if test="#request.defines['cscVin']>0">
					${lz:set("注释","****cscVin关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscVin",lz:indexOf(fieldName,"cscVin")>-1)}				  		
				  		<s:if test="#request.atCscVin==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscVin[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscEngineNo || #request.defines['cscEngineNo']!=null">
				<s:if test="#request.defines['cscEngineNo']>0">
					${lz:set("注释","****cscEngineNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscEngineNo",lz:indexOf(fieldName,"cscEngineNo")>-1)}				  		
				  		<s:if test="#request.atCscEngineNo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscEngineNo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscBuyDate || #request.defines['cscBuyDate']!=null">
				<s:if test="#request.defines['cscBuyDate']>0">
					${lz:set("注释","****cscBuyDate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscBuyDate",lz:indexOf(fieldName,"cscBuyDate")>-1)}				  		
				  		<s:if test="#request.atCscBuyDate==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscBuyDate[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscBargainNo || #request.defines['cscBargainNo']!=null">
				<s:if test="#request.defines['cscBargainNo']>0">
					${lz:set("注释","****cscBargainNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscBargainNo",lz:indexOf(fieldName,"cscBargainNo")>-1)}				  		
				  		<s:if test="#request.atCscBargainNo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscBargainNo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscTaxPrice || #request.defines['cscTaxPrice']!=null">
				<s:if test="#request.defines['cscTaxPrice']>0">
					${lz:set("注释","****cscTaxPrice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscTaxPrice",lz:indexOf(fieldName,"cscTaxPrice")>-1)}				  		
				  		<s:if test="#request.atCscTaxPrice==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscTaxPrice[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscBuyPrice || #request.defines['cscBuyPrice']!=null">
				<s:if test="#request.defines['cscBuyPrice']>0">
					${lz:set("注释","****cscBuyPrice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscBuyPrice",lz:indexOf(fieldName,"cscBuyPrice")>-1)}				  		
				  		<s:if test="#request.atCscBuyPrice==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscBuyPrice[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscCertific || #request.defines['cscCertific']!=null">
				<s:if test="#request.defines['cscCertific']>0">
					${lz:set("注释","****cscCertific关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscCertific",lz:indexOf(fieldName,"cscCertific")>-1)}				  		
				  		<s:if test="#request.atCscCertific==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscCertific[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscFactoryNo || #request.defines['cscFactoryNo']!=null">
				<s:if test="#request.defines['cscFactoryNo']>0">
					${lz:set("注释","****cscFactoryNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscFactoryNo",lz:indexOf(fieldName,"cscFactoryNo")>-1)}				  		
				  		<s:if test="#request.atCscFactoryNo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscFactoryNo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscColor || #request.defines['cscColor']!=null">
				<s:if test="#request.defines['cscColor']>0">
					${lz:set("注释","****cscColor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscColor",lz:indexOf(fieldName,"cscColor")>-1)}				  		
				  		<s:if test="#request.atCscColor==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscColor[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscPreview || #request.defines['cscPreview']!=null">
				<s:if test="#request.defines['cscPreview']>0">
					${lz:set("注释","****cscPreview关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscPreview",lz:indexOf(fieldName,"cscPreview")>-1)}				  		
				  		<s:if test="#request.atCscPreview==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscPreview[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscImages || #request.defines['cscImages']!=null">
				<s:if test="#request.defines['cscImages']>0">
					${lz:set("注释","****cscImages关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscImages",lz:indexOf(fieldName,"cscImages")>-1)}				  		
				  		<s:if test="#request.atCscImages==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscImages[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscMaintKm || #request.defines['cscMaintKm']!=null">
				<s:if test="#request.defines['cscMaintKm']>0">
					${lz:set("注释","****cscMaintKm关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscMaintKm",lz:indexOf(fieldName,"cscMaintKm")>-1)}				  		
				  		<s:if test="#request.atCscMaintKm==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscMaintKm[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscCheckIn || #request.defines['cscCheckIn']!=null">
				<s:if test="#request.defines['cscCheckIn']>0">
					${lz:set("注释","****cscCheckIn关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscCheckIn",lz:indexOf(fieldName,"cscCheckIn")>-1)}				  		
				  		<s:if test="#request.atCscCheckIn==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscCheckIn[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscAnnualPre || #request.defines['cscAnnualPre']!=null">
				<s:if test="#request.defines['cscAnnualPre']>0">
					${lz:set("注释","****cscAnnualPre关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscAnnualPre",lz:indexOf(fieldName,"cscAnnualPre")>-1)}				  		
				  		<s:if test="#request.atCscAnnualPre==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscAnnualPre[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscAnnual || #request.defines['cscAnnual']!=null">
				<s:if test="#request.defines['cscAnnual']>0">
					${lz:set("注释","****cscAnnual关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscAnnual",lz:indexOf(fieldName,"cscAnnual")>-1)}				  		
				  		<s:if test="#request.atCscAnnual==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscAnnual[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscAnnualInfo || #request.defines['cscAnnualInfo']!=null">
				<s:if test="#request.defines['cscAnnualInfo']>0">
					${lz:set("注释","****cscAnnualInfo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscAnnualInfo",lz:indexOf(fieldName,"cscAnnualInfo")>-1)}				  		
				  		<s:if test="#request.atCscAnnualInfo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscAnnualInfo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscAnnualNext || #request.defines['cscAnnualNext']!=null">
				<s:if test="#request.defines['cscAnnualNext']>0">
					${lz:set("注释","****cscAnnualNext关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscAnnualNext",lz:indexOf(fieldName,"cscAnnualNext")>-1)}				  		
				  		<s:if test="#request.atCscAnnualNext==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscAnnualNext[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscTiDate || #request.defines['cscTiDate']!=null">
				<s:if test="#request.defines['cscTiDate']>0">
					${lz:set("注释","****cscTiDate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscTiDate",lz:indexOf(fieldName,"cscTiDate")>-1)}				  		
				  		<s:if test="#request.atCscTiDate==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscTiDate[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscTiUnit || #request.defines['cscTiUnit']!=null">
				<s:if test="#request.defines['cscTiUnit']>0">
					${lz:set("注释","****cscTiUnit关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscTiUnit",lz:indexOf(fieldName,"cscTiUnit")>-1)}				  		
				  		<s:if test="#request.atCscTiUnit==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscTiUnit[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscTiNo || #request.defines['cscTiNo']!=null">
				<s:if test="#request.defines['cscTiNo']>0">
					${lz:set("注释","****cscTiNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscTiNo",lz:indexOf(fieldName,"cscTiNo")>-1)}				  		
				  		<s:if test="#request.atCscTiNo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscTiNo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscBiDate || #request.defines['cscBiDate']!=null">
				<s:if test="#request.defines['cscBiDate']>0">
					${lz:set("注释","****cscBiDate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscBiDate",lz:indexOf(fieldName,"cscBiDate")>-1)}				  		
				  		<s:if test="#request.atCscBiDate==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscBiDate[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscBiUnit || #request.defines['cscBiUnit']!=null">
				<s:if test="#request.defines['cscBiUnit']>0">
					${lz:set("注释","****cscBiUnit关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscBiUnit",lz:indexOf(fieldName,"cscBiUnit")>-1)}				  		
				  		<s:if test="#request.atCscBiUnit==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscBiUnit[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscBiNo || #request.defines['cscBiNo']!=null">
				<s:if test="#request.defines['cscBiNo']>0">
					${lz:set("注释","****cscBiNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscBiNo",lz:indexOf(fieldName,"cscBiNo")>-1)}				  		
				  		<s:if test="#request.atCscBiNo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscBiNo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscBiType || #request.defines['cscBiType']!=null">
				<s:if test="#request.defines['cscBiType']>0">
					${lz:set("注释","****cscBiType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscBiType",lz:indexOf(fieldName,"cscBiType")>-1)}				  		
				  		<s:if test="#request.atCscBiType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscBiType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscBiLimit || #request.defines['cscBiLimit']!=null">
				<s:if test="#request.defines['cscBiLimit']>0">
					${lz:set("注释","****cscBiLimit关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscBiLimit",lz:indexOf(fieldName,"cscBiLimit")>-1)}				  		
				  		<s:if test="#request.atCscBiLimit==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscBiLimit[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscBiCompany || #request.defines['cscBiCompany']!=null">
				<s:if test="#request.defines['cscBiCompany']>0">
					${lz:set("注释","****cscBiCompany关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscBiCompany",lz:indexOf(fieldName,"cscBiCompany")>-1)}				  		
				  		<s:if test="#request.atCscBiCompany==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscBiCompany[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscEditor || #request.defines['cscEditor']!=null">
				<s:if test="#request.defines['cscEditor']>0">
					${lz:set("注释","****cscEditor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscEditor",lz:indexOf(fieldName,"cscEditor")>-1)}				  		
				  		<s:if test="#request.atCscEditor==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscEditor[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscUpdateTime || #request.defines['cscUpdateTime']!=null">
				<s:if test="#request.defines['cscUpdateTime']>0">
					${lz:set("注释","****cscUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscUpdateTime",lz:indexOf(fieldName,"cscUpdateTime")>-1)}				  		
				  		<s:if test="#request.atCscUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscAddTime || #request.defines['cscAddTime']!=null">
				<s:if test="#request.defines['cscAddTime']>0">
					${lz:set("注释","****cscAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscAddTime",lz:indexOf(fieldName,"cscAddTime")>-1)}				  		
				  		<s:if test="#request.atCscAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscRemark || #request.defines['cscRemark']!=null">
				<s:if test="#request.defines['cscRemark']>0">
					${lz:set("注释","****cscRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscRemark",lz:indexOf(fieldName,"cscRemark")>-1)}				  		
				  		<s:if test="#request.atCscRemark==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscRemark[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscMemo || #request.defines['cscMemo']!=null">
				<s:if test="#request.defines['cscMemo']>0">
					${lz:set("注释","****cscMemo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscMemo",lz:indexOf(fieldName,"cscMemo")>-1)}				  		
				  		<s:if test="#request.atCscMemo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscMemo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscOnPsd || #request.defines['cscOnPsd']!=null">
				<s:if test="#request.defines['cscOnPsd']>0">
					${lz:set("注释","****cscOnPsd关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscOnPsd",lz:indexOf(fieldName,"cscOnPsd")>-1)}				  		
				  		<s:if test="#request.atCscOnPsd==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscOnPsd[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscSetup || #request.defines['cscSetup']!=null">
				<s:if test="#request.defines['cscSetup']>0">
					${lz:set("注释","****cscSetup关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscSetup",lz:indexOf(fieldName,"cscSetup")>-1)}				  		
				  		<s:if test="#request.atCscSetup==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscSetup[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscDeviceVesion || #request.defines['cscDeviceVesion']!=null">
				<s:if test="#request.defines['cscDeviceVesion']>0">
					${lz:set("注释","****cscDeviceVesion关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscDeviceVesion",lz:indexOf(fieldName,"cscDeviceVesion")>-1)}				  		
				  		<s:if test="#request.atCscDeviceVesion==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscDeviceVesion[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscMqttFlag || #request.defines['cscMqttFlag']!=null">
				<s:if test="#request.defines['cscMqttFlag']>0">
					${lz:set("注释","****cscMqttFlag关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscMqttFlag",lz:indexOf(fieldName,"cscMqttFlag")>-1)}				  		
				  		<s:if test="#request.atCscMqttFlag==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscMqttFlag[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscMask || #request.defines['cscMask']!=null">
				<s:if test="#request.defines['cscMask']>0">
					${lz:set("注释","****cscMask关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscMask",lz:indexOf(fieldName,"cscMask")>-1)}				  		
				  		<s:if test="#request.atCscMask==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscMask[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscStatus || #request.defines['cscStatus']!=null">
				<s:if test="#request.defines['cscStatus']>0">
					${lz:set("注释","****cscStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscStatus",lz:indexOf(fieldName,"cscStatus")>-1)}				  		
				  		<s:if test="#request.atCscStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
<s:if test="#request.CTRL.canAdd==true">
	/**
	* 添加车辆
	**/
	function AddCar(parent){		
		var url = "${basePath}${namespace}car_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加车辆'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择车辆
	**/
	function SelectCar(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改车辆
	**/
	function EditCar(id){
		var url = "${basePath}${namespace}car_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改车辆'}":lz:json(ctrl)}};
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
		EditCar(checker.val());
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
				var url = "${basePath}${namespace}car_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新车辆",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}car_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新车辆",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改车辆
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}car_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新车辆",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["provid"]={};
	fieldNames["rebate"]={};
	fieldNames["isad"]={};
	fieldNames["memo"]={};
	fieldNames["setup"]={};
	fieldNames["vesion"]={};
	fieldNames["mqtt"]={};
	fieldNames["status"]={};
	fieldNames["provid"]["cscProvid"]=true;
	fieldNames["rebate"]["cscRebate"]=true;
	fieldNames["isad"]["cscIsAd"]=true;
	fieldNames["memo"]["cscMemo"]=true;
	fieldNames["setup"]["cscSetup"]=true;
	fieldNames["vesion"]["cscDeviceVesion"]=true;
	fieldNames["mqtt"]["cscMqttFlag"]=true;
	fieldNames["status"]["cscStatus"]=true;
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改车辆任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}car_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新车辆",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}car_edit.do";
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
			var url = "${basePath}${namespace}car_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除车辆
	**/
	function DelCar(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除车辆["+flag+"]吗？</span>",
				title:"删除车辆",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}car_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选车辆吗？</span>",
				title:"批量删除车辆",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}car_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示车辆详情
	**/
	function DetailsCar(id)
	{
		var url = "${basePath}${namespace}car_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"车辆详情",editable:false,visible:true}};
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
		DetailsCar(checker.val());
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


<!--LAZY3Q_JSP_BOTTOM-->
<script>
function getHandle(operate,id){
	var mapping={"远程控制":remote};
	return mapping[operate];
}
//远程控制
function remote(operate,id){
	var url = "${basePath}admin/control/remote_edit.do";
	var params = {
		entrypoint:"${entrypoint}",
		"csRemote.csrCar":id
	};
	href(url,params);
}
</script>
<!--LAZY3Q_JSP_BOTTOM-->


</body>
</html>