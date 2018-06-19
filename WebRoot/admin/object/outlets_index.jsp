<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/object/outlets.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csOutlets.csoId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csoId,csoHost,csoName,csoArea,csoAddress,csoSpace,csoUntil,csoExpire,csoPileTime,csoCanA,csoCanB,csoCanReg,csoCanOrder,csoOnlineTime,csoUpdateTime,csoAddTime,csoStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csoId,csoHost,csoName,csoArea,csoType,csoLongitude,csoLatitude,csoAddress,csoSpace,csoDoorTime,csoCost,csoPerson,csoContact,csoUntil,csoExpire,csoPileTime,csoCarS,csoPositionS,csoImage,csoCanA,csoCanB,csoCanReg,csoCanOrder,csoPriority,csoVoteS,csoProfile,csoOnlineTime,csoUpdateTime,csoAddTime,csoStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csoId,csoHost,csoName,csoArea,csoUntil,csoExpire,csoCanA,csoCanB,csoCanReg,csoCanOrder,csoOnlineTime,csoStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csoId,csoHost,csoName,csoArea,csoType,csoUntil,csoExpire,csoCarS,csoPositionS,csoCanA,csoCanB,csoCanReg,csoCanOrder,csoProfile,csoOnlineTime,csoStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>网点管理</title>
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
	<span class="caption">${empty title?"网点管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsOutlets" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="outlets.query.jsp"%>

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
			<button type="button" class="add" onclick="AddOutlets();">
				添加
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
			<dl ref="com.ccclubs.model.CsOutlets" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.CsOutlets" class="batch dropdowan">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/20088256421529677807.png"/>
					批量修改
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
					<s:if test="#request.defines==null && #request.CTRL.l.csoCanA || #request.defines['csoCanA']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('canAB')">AB借出</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.csoCanB || #request.defines['csoCanB']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('canAB')">AB还入</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.csoCanReg || #request.defines['csoCanReg']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('canReg')">能注册</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.csoCanOrder || #request.defines['csoCanOrder']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('canOrder')">能预订</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.csoStatus || #request.defines['csoStatus']!=null">
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
		<table id="outlets_table" ref="CsOutlets" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoId || #request.defines['csoId']!=null">
			 <td <s:if test="#request.defines['csoId']>0">colspan="${defines["csoId"]}" ${all?"width":"iwidth"}="${defines["csoId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="csoId"   title="编号">
			 	<a class="${desc=="cso_id" ? "desc" : ""}${asc=="cso_id" ? "asc" : ""}" href="?${desc=="cso_id" ? "asc=cso_id" : ""}${(asc=="cso_id" || desc!="cso_id" )? "desc=cso_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","csoId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoHost || #request.defines['csoHost']!=null">
			 <td <s:if test="#request.defines['csoHost']>0">colspan="${defines["csoHost"]}" ${all?"width":"iwidth"}="${defines["csoHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="csoHost"   title="城市">
			 	<a class="${desc=="cso_host" ? "desc" : ""}${asc=="cso_host" ? "asc" : ""}" href="?${desc=="cso_host" ? "asc=cso_host" : ""}${(asc=="cso_host" || desc!="cso_host" )? "desc=cso_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","csoHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoName || #request.defines['csoName']!=null">
			 <td <s:if test="#request.defines['csoName']>0">colspan="${defines["csoName"]}" ${all?"width":"iwidth"}="${defines["csoName"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="3" ref="csoName" flagKey  title="名称">
			 	<a class="${desc=="cso_name" ? "desc" : ""}${asc=="cso_name" ? "asc" : ""}" href="?${desc=="cso_name" ? "asc=cso_name" : ""}${(asc=="cso_name" || desc!="cso_name" )? "desc=cso_name" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	名称
			 	</a>
			 	${lz:set("checkeds[]","csoName")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoArea || #request.defines['csoArea']!=null">
			 <td <s:if test="#request.defines['csoArea']>0">colspan="${defines["csoArea"]}" ${all?"width":"iwidth"}="${defines["csoArea"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="csoArea"   title="所在区域">
			 	<a class="${desc=="cso_area" ? "desc" : ""}${asc=="cso_area" ? "asc" : ""}" href="?${desc=="cso_area" ? "asc=cso_area" : ""}${(asc=="cso_area" || desc!="cso_area" )? "desc=cso_area" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所在区域
			 	</a>
			 	${lz:set("checkeds[]","csoArea")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoType || #request.defines['csoType']!=null">
			 <td <s:if test="#request.defines['csoType']>0">colspan="${defines["csoType"]}" ${all?"width":"iwidth"}="${defines["csoType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="csoType"   title="网点类型">
			 	<a class="${desc=="cso_type" ? "desc" : ""}${asc=="cso_type" ? "asc" : ""}" href="?${desc=="cso_type" ? "asc=cso_type" : ""}${(asc=="cso_type" || desc!="cso_type" )? "desc=cso_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	网点类型
			 	</a>
			 	${lz:set("checkeds[]","csoType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoLongitude || #request.defines['csoLongitude']!=null">
			 <td <s:if test="#request.defines['csoLongitude']>0">colspan="${defines["csoLongitude"]}" ${all?"width":"iwidth"}="${defines["csoLongitude"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="csoLongitude"   title="经度">
			 	<a class="${desc=="cso_longitude" ? "desc" : ""}${asc=="cso_longitude" ? "asc" : ""}" href="?${desc=="cso_longitude" ? "asc=cso_longitude" : ""}${(asc=="cso_longitude" || desc!="cso_longitude" )? "desc=cso_longitude" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	经度
			 	</a>
			 	${lz:set("checkeds[]","csoLongitude")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoLatitude || #request.defines['csoLatitude']!=null">
			 <td <s:if test="#request.defines['csoLatitude']>0">colspan="${defines["csoLatitude"]}" ${all?"width":"iwidth"}="${defines["csoLatitude"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="7" ref="csoLatitude"   title="纬度">
			 	<a class="${desc=="cso_latitude" ? "desc" : ""}${asc=="cso_latitude" ? "asc" : ""}" href="?${desc=="cso_latitude" ? "asc=cso_latitude" : ""}${(asc=="cso_latitude" || desc!="cso_latitude" )? "desc=cso_latitude" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	纬度
			 	</a>
			 	${lz:set("checkeds[]","csoLatitude")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoAddress || #request.defines['csoAddress']!=null">
			 <td <s:if test="#request.defines['csoAddress']>0">colspan="${defines["csoAddress"]}" ${all?"width":"iwidth"}="${defines["csoAddress"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="8" ref="csoAddress"   title="地址">
			 	<a class="${desc=="cso_address" ? "desc" : ""}${asc=="cso_address" ? "asc" : ""}" href="?${desc=="cso_address" ? "asc=cso_address" : ""}${(asc=="cso_address" || desc!="cso_address" )? "desc=cso_address" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	地址
			 	</a>
			 	${lz:set("checkeds[]","csoAddress")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoSpace || #request.defines['csoSpace']!=null">
			 <td <s:if test="#request.defines['csoSpace']>0">colspan="${defines["csoSpace"]}" ${all?"width":"iwidth"}="${defines["csoSpace"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="9" ref="csoSpace"   title="停车地点">
			 	<a class="${desc=="cso_space" ? "desc" : ""}${asc=="cso_space" ? "asc" : ""}" href="?${desc=="cso_space" ? "asc=cso_space" : ""}${(asc=="cso_space" || desc!="cso_space" )? "desc=cso_space" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	停车地点
			 	</a>
			 	${lz:set("checkeds[]","csoSpace")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoDoorTime || #request.defines['csoDoorTime']!=null">
			 <td <s:if test="#request.defines['csoDoorTime']>0">colspan="${defines["csoDoorTime"]}" ${all?"width":"iwidth"}="${defines["csoDoorTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="10" ref="csoDoorTime"   title="门禁时间">
			 	<a class="${desc=="cso_door_time" ? "desc" : ""}${asc=="cso_door_time" ? "asc" : ""}" href="?${desc=="cso_door_time" ? "asc=cso_door_time" : ""}${(asc=="cso_door_time" || desc!="cso_door_time" )? "desc=cso_door_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	门禁时间
			 	</a>
			 	${lz:set("checkeds[]","csoDoorTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoCost || #request.defines['csoCost']!=null">
			 <td <s:if test="#request.defines['csoCost']>0">colspan="${defines["csoCost"]}" ${all?"width":"iwidth"}="${defines["csoCost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="11" ref="csoCost"   title="停车费(元每月)">
			 	<a class="${desc=="cso_cost" ? "desc" : ""}${asc=="cso_cost" ? "asc" : ""}" href="?${desc=="cso_cost" ? "asc=cso_cost" : ""}${(asc=="cso_cost" || desc!="cso_cost" )? "desc=cso_cost" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	停车费(元每月)
			 	</a>
			 	${lz:set("checkeds[]","csoCost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoPerson || #request.defines['csoPerson']!=null">
			 <td <s:if test="#request.defines['csoPerson']>0">colspan="${defines["csoPerson"]}" ${all?"width":"iwidth"}="${defines["csoPerson"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="12" ref="csoPerson"   title="联系人">
			 	<a class="${desc=="cso_person" ? "desc" : ""}${asc=="cso_person" ? "asc" : ""}" href="?${desc=="cso_person" ? "asc=cso_person" : ""}${(asc=="cso_person" || desc!="cso_person" )? "desc=cso_person" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	联系人
			 	</a>
			 	${lz:set("checkeds[]","csoPerson")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoContact || #request.defines['csoContact']!=null">
			 <td <s:if test="#request.defines['csoContact']>0">colspan="${defines["csoContact"]}" ${all?"width":"iwidth"}="${defines["csoContact"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="13" ref="csoContact"   title="联系电话">
			 	<a class="${desc=="cso_contact" ? "desc" : ""}${asc=="cso_contact" ? "asc" : ""}" href="?${desc=="cso_contact" ? "asc=cso_contact" : ""}${(asc=="cso_contact" || desc!="cso_contact" )? "desc=cso_contact" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	联系电话
			 	</a>
			 	${lz:set("checkeds[]","csoContact")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoUntil || #request.defines['csoUntil']!=null">
			 <td <s:if test="#request.defines['csoUntil']>0">colspan="${defines["csoUntil"]}" ${all?"width":"iwidth"}="${defines["csoUntil"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="14" ref="csoUntil"   title="缴费到期">
			 	<a class="${desc=="cso_until" ? "desc" : ""}${asc=="cso_until" ? "asc" : ""}" href="?${desc=="cso_until" ? "asc=cso_until" : ""}${(asc=="cso_until" || desc!="cso_until" )? "desc=cso_until" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	缴费到期
			 	</a>
			 	${lz:set("checkeds[]","csoUntil")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoExpire || #request.defines['csoExpire']!=null">
			 <td <s:if test="#request.defines['csoExpire']>0">colspan="${defines["csoExpire"]}" ${all?"width":"iwidth"}="${defines["csoExpire"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="15" ref="csoExpire"   title="合约到期">
			 	<a class="${desc=="cso_expire" ? "desc" : ""}${asc=="cso_expire" ? "asc" : ""}" href="?${desc=="cso_expire" ? "asc=cso_expire" : ""}${(asc=="cso_expire" || desc!="cso_expire" )? "desc=cso_expire" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	合约到期
			 	</a>
			 	${lz:set("checkeds[]","csoExpire")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoPileTime || #request.defines['csoPileTime']!=null">
			 <td <s:if test="#request.defines['csoPileTime']>0">colspan="${defines["csoPileTime"]}" ${all?"width":"iwidth"}="${defines["csoPileTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="16" ref="csoPileTime"   title="电桩建立时间">
			 	<a class="${desc=="cso_pile_time" ? "desc" : ""}${asc=="cso_pile_time" ? "asc" : ""}" href="?${desc=="cso_pile_time" ? "asc=cso_pile_time" : ""}${(asc=="cso_pile_time" || desc!="cso_pile_time" )? "desc=cso_pile_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	电桩建立时间
			 	</a>
			 	${lz:set("checkeds[]","csoPileTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoCarS || #request.defines['csoCarS']!=null">
			 <td <s:if test="#request.defines['csoCarS']>0">colspan="${defines["csoCarS"]}" ${all?"width":"iwidth"}="${defines["csoCarS"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="17" ref="csoCarS"   title="已有车辆数">
			 	<a class="${desc=="cso_car_s" ? "desc" : ""}${asc=="cso_car_s" ? "asc" : ""}" href="?${desc=="cso_car_s" ? "asc=cso_car_s" : ""}${(asc=="cso_car_s" || desc!="cso_car_s" )? "desc=cso_car_s" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	已有车辆数
			 	</a>
			 	${lz:set("checkeds[]","csoCarS")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoPositionS || #request.defines['csoPositionS']!=null">
			 <td <s:if test="#request.defines['csoPositionS']>0">colspan="${defines["csoPositionS"]}" ${all?"width":"iwidth"}="${defines["csoPositionS"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="18" ref="csoPositionS"   title="车位数">
			 	<a class="${desc=="cso_position_s" ? "desc" : ""}${asc=="cso_position_s" ? "asc" : ""}" href="?${desc=="cso_position_s" ? "asc=cso_position_s" : ""}${(asc=="cso_position_s" || desc!="cso_position_s" )? "desc=cso_position_s" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车位数
			 	</a>
			 	${lz:set("checkeds[]","csoPositionS")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoImage || #request.defines['csoImage']!=null">
			 <td <s:if test="#request.defines['csoImage']>0">colspan="${defines["csoImage"]}" ${all?"width":"iwidth"}="${defines["csoImage"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="19" ref="csoImage"   title="网点图片">
			 	<a class="${desc=="cso_image" ? "desc" : ""}${asc=="cso_image" ? "asc" : ""}" href="?${desc=="cso_image" ? "asc=cso_image" : ""}${(asc=="cso_image" || desc!="cso_image" )? "desc=cso_image" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	网点图片
			 	</a>
			 	${lz:set("checkeds[]","csoImage")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoCanA || #request.defines['csoCanA']!=null">
			 <td <s:if test="#request.defines['csoCanA']>0">colspan="${defines["csoCanA"]}" ${all?"width":"iwidth"}="${defines["csoCanA"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="20" ref="csoCanA"   title="AB借出">
			 	<a class="${desc=="cso_can_a" ? "desc" : ""}${asc=="cso_can_a" ? "asc" : ""}" href="?${desc=="cso_can_a" ? "asc=cso_can_a" : ""}${(asc=="cso_can_a" || desc!="cso_can_a" )? "desc=cso_can_a" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	AB借出
			 	</a>
			 	${lz:set("checkeds[]","csoCanA")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoCanB || #request.defines['csoCanB']!=null">
			 <td <s:if test="#request.defines['csoCanB']>0">colspan="${defines["csoCanB"]}" ${all?"width":"iwidth"}="${defines["csoCanB"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="21" ref="csoCanB"   title="AB还入">
			 	<a class="${desc=="cso_can_b" ? "desc" : ""}${asc=="cso_can_b" ? "asc" : ""}" href="?${desc=="cso_can_b" ? "asc=cso_can_b" : ""}${(asc=="cso_can_b" || desc!="cso_can_b" )? "desc=cso_can_b" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	AB还入
			 	</a>
			 	${lz:set("checkeds[]","csoCanB")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoCanReg || #request.defines['csoCanReg']!=null">
			 <td <s:if test="#request.defines['csoCanReg']>0">colspan="${defines["csoCanReg"]}" ${all?"width":"iwidth"}="${defines["csoCanReg"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="22" ref="csoCanReg"   title="能注册">
			 	<a class="${desc=="cso_can_reg" ? "desc" : ""}${asc=="cso_can_reg" ? "asc" : ""}" href="?${desc=="cso_can_reg" ? "asc=cso_can_reg" : ""}${(asc=="cso_can_reg" || desc!="cso_can_reg" )? "desc=cso_can_reg" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	能注册
			 	</a>
			 	${lz:set("checkeds[]","csoCanReg")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoCanOrder || #request.defines['csoCanOrder']!=null">
			 <td <s:if test="#request.defines['csoCanOrder']>0">colspan="${defines["csoCanOrder"]}" ${all?"width":"iwidth"}="${defines["csoCanOrder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="23" ref="csoCanOrder"   title="能预订">
			 	<a class="${desc=="cso_can_order" ? "desc" : ""}${asc=="cso_can_order" ? "asc" : ""}" href="?${desc=="cso_can_order" ? "asc=cso_can_order" : ""}${(asc=="cso_can_order" || desc!="cso_can_order" )? "desc=cso_can_order" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	能预订
			 	</a>
			 	${lz:set("checkeds[]","csoCanOrder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoPriority || #request.defines['csoPriority']!=null">
			 <td <s:if test="#request.defines['csoPriority']>0">colspan="${defines["csoPriority"]}" ${all?"width":"iwidth"}="${defines["csoPriority"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="24" ref="csoPriority"   title="优先级">
			 	<a class="${desc=="cso_priority" ? "desc" : ""}${asc=="cso_priority" ? "asc" : ""}" href="?${desc=="cso_priority" ? "asc=cso_priority" : ""}${(asc=="cso_priority" || desc!="cso_priority" )? "desc=cso_priority" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	优先级
			 	</a>
			 	${lz:set("checkeds[]","csoPriority")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoVoteS || #request.defines['csoVoteS']!=null">
			 <td <s:if test="#request.defines['csoVoteS']>0">colspan="${defines["csoVoteS"]}" ${all?"width":"iwidth"}="${defines["csoVoteS"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="25" ref="csoVoteS"   title="人气">
			 	<a class="${desc=="cso_vote_s" ? "desc" : ""}${asc=="cso_vote_s" ? "asc" : ""}" href="?${desc=="cso_vote_s" ? "asc=cso_vote_s" : ""}${(asc=="cso_vote_s" || desc!="cso_vote_s" )? "desc=cso_vote_s" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	人气
			 	</a>
			 	${lz:set("checkeds[]","csoVoteS")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoProfile || #request.defines['csoProfile']!=null">
			 <td <s:if test="#request.defines['csoProfile']>0">colspan="${defines["csoProfile"]}" ${all?"width":"iwidth"}="${defines["csoProfile"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="26" ref="csoProfile"   title="描述">
			 	<a class="${desc=="cso_profile" ? "desc" : ""}${asc=="cso_profile" ? "asc" : ""}" href="?${desc=="cso_profile" ? "asc=cso_profile" : ""}${(asc=="cso_profile" || desc!="cso_profile" )? "desc=cso_profile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	描述
			 	</a>
			 	${lz:set("checkeds[]","csoProfile")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoOnlineTime || #request.defines['csoOnlineTime']!=null">
			 <td <s:if test="#request.defines['csoOnlineTime']>0">colspan="${defines["csoOnlineTime"]}" ${all?"width":"iwidth"}="${defines["csoOnlineTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="27" ref="csoOnlineTime"   title="上线时间">
			 	<a class="${desc=="cso_online_time" ? "desc" : ""}${asc=="cso_online_time" ? "asc" : ""}" href="?${desc=="cso_online_time" ? "asc=cso_online_time" : ""}${(asc=="cso_online_time" || desc!="cso_online_time" )? "desc=cso_online_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	上线时间
			 	</a>
			 	${lz:set("checkeds[]","csoOnlineTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoUpdateTime || #request.defines['csoUpdateTime']!=null">
			 <td <s:if test="#request.defines['csoUpdateTime']>0">colspan="${defines["csoUpdateTime"]}" ${all?"width":"iwidth"}="${defines["csoUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="28" ref="csoUpdateTime"   title="修改时间">
			 	<a class="${desc=="cso_update_time" ? "desc" : ""}${asc=="cso_update_time" ? "asc" : ""}" href="?${desc=="cso_update_time" ? "asc=cso_update_time" : ""}${(asc=="cso_update_time" || desc!="cso_update_time" )? "desc=cso_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 	${lz:set("checkeds[]","csoUpdateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoAddTime || #request.defines['csoAddTime']!=null">
			 <td <s:if test="#request.defines['csoAddTime']>0">colspan="${defines["csoAddTime"]}" ${all?"width":"iwidth"}="${defines["csoAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="29" ref="csoAddTime"   title="添加时间">
			 	<a class="${desc=="cso_add_time" ? "desc" : ""}${asc=="cso_add_time" ? "asc" : ""}" href="?${desc=="cso_add_time" ? "asc=cso_add_time" : ""}${(asc=="cso_add_time" || desc!="cso_add_time" )? "desc=cso_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","csoAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoStatus || #request.defines['csoStatus']!=null">
			 <td <s:if test="#request.defines['csoStatus']>0">colspan="${defines["csoStatus"]}" ${all?"width":"iwidth"}="${defines["csoStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="30" ref="csoStatus"   title="状态">
			 	<a class="${desc=="cso_status" ? "desc" : ""}${asc=="cso_status" ? "asc" : ""}" href="?${desc=="cso_status" ? "asc=cso_status" : ""}${(asc=="cso_status" || desc!="cso_status" )? "desc=cso_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","csoStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="31" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="32" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:AddOutlets()" style="text-decoration:underline">添加</a>
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
		  <tr id="${item.csoId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csoId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoId || #request.defines['csoId']!=null">
				<s:if test="#request.defines['csoId']>0">
					${lz:set("注释","****csoId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoId",lz:indexOf(fieldName,"csoId")>-1)}
				  		<s:if test="#request.atCsoId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoId字段的字串格式化输出****")}
					<td ref="csoId" class="td ">
						 
						 	${lz:or(item$csoId[i.count-1],lz:left(item.csoId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoHost || #request.defines['csoHost']!=null">
				<s:if test="#request.defines['csoHost']>0">
					${lz:set("注释","****csoHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoHost",lz:indexOf(fieldName,"csoHost")>-1)}
				  		<s:if test="#request.atCsoHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoHost字段的字串格式化输出****")}
					<td ref="csoHost" class="td " relate="${item.csoHost}">
						 
						 	<a <s:if test="#item.csoHost!=null && #item.csoHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.csoHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csoHost[i.count-1],lz:left(item.csoHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoName || #request.defines['csoName']!=null">
				<s:if test="#request.defines['csoName']>0">
					${lz:set("注释","****csoName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoName))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoName",lz:indexOf(fieldName,"csoName")>-1)}
				  		<s:if test="#request.atCsoName==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoName字段的字串格式化输出****")}
					<td ref="csoName" class="td  node">
						 
						 	${lz:or(item$csoName[i.count-1],lz:left(item.csoName$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoArea || #request.defines['csoArea']!=null">
				<s:if test="#request.defines['csoArea']>0">
					${lz:set("注释","****csoArea关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoArea))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoArea",lz:indexOf(fieldName,"csoArea")>-1)}
				  		<s:if test="#request.atCsoArea==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoArea字段的字串格式化输出****")}
					<td ref="csoArea" class="td " relate="${item.csoArea}">
						 
						 	<a <s:if test="#item.csoArea!=null && #item.csoArea!=''"> onclick="window.href('${basePath}${proname}/configurator/area_details.do?key=${item.csoArea}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csoArea[i.count-1],lz:left(item.csoArea$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoType || #request.defines['csoType']!=null">
				<s:if test="#request.defines['csoType']>0">
					${lz:set("注释","****csoType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoType",lz:indexOf(fieldName,"csoType")>-1)}
				  		<s:if test="#request.atCsoType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoType字段的字串格式化输出****")}
					<td ref="csoType" class="td ">
						 
						 	${lz:or(item$csoType[i.count-1],lz:left(item.csoType$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoLongitude || #request.defines['csoLongitude']!=null">
				<s:if test="#request.defines['csoLongitude']>0">
					${lz:set("注释","****csoLongitude关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoLongitude))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoLongitude",lz:indexOf(fieldName,"csoLongitude")>-1)}
				  		<s:if test="#request.atCsoLongitude==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoLongitude字段的字串格式化输出****")}
					<td ref="csoLongitude" class="td ">
						 
						 	${lz:or(item$csoLongitude[i.count-1],lz:left(item.csoLongitude$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoLatitude || #request.defines['csoLatitude']!=null">
				<s:if test="#request.defines['csoLatitude']>0">
					${lz:set("注释","****csoLatitude关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoLatitude))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoLatitude",lz:indexOf(fieldName,"csoLatitude")>-1)}
				  		<s:if test="#request.atCsoLatitude==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoLatitude字段的字串格式化输出****")}
					<td ref="csoLatitude" class="td ">
						 
						 	${lz:or(item$csoLatitude[i.count-1],lz:left(item.csoLatitude$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoAddress || #request.defines['csoAddress']!=null">
				<s:if test="#request.defines['csoAddress']>0">
					${lz:set("注释","****csoAddress关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoAddress))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoAddress",lz:indexOf(fieldName,"csoAddress")>-1)}
				  		<s:if test="#request.atCsoAddress==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoAddress字段的字串格式化输出****")}
					<td ref="csoAddress" class="td ">
						 
						 	${lz:or(item$csoAddress[i.count-1],lz:left(item.csoAddress$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoSpace || #request.defines['csoSpace']!=null">
				<s:if test="#request.defines['csoSpace']>0">
					${lz:set("注释","****csoSpace关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoSpace))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoSpace",lz:indexOf(fieldName,"csoSpace")>-1)}
				  		<s:if test="#request.atCsoSpace==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoSpace字段的字串格式化输出****")}
					<td ref="csoSpace" class="td ">
						 
						 	${lz:or(item$csoSpace[i.count-1],lz:left(item.csoSpace$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoDoorTime || #request.defines['csoDoorTime']!=null">
				<s:if test="#request.defines['csoDoorTime']>0">
					${lz:set("注释","****csoDoorTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoDoorTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoDoorTime",lz:indexOf(fieldName,"csoDoorTime")>-1)}
				  		<s:if test="#request.atCsoDoorTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoDoorTime字段的字串格式化输出****")}
					<td ref="csoDoorTime" class="td ">
						 
						 	${lz:or(item$csoDoorTime[i.count-1],lz:left(item.csoDoorTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoCost || #request.defines['csoCost']!=null">
				<s:if test="#request.defines['csoCost']>0">
					${lz:set("注释","****csoCost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoCost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoCost",lz:indexOf(fieldName,"csoCost")>-1)}
				  		<s:if test="#request.atCsoCost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoCost字段的字串格式化输出****")}
					<td ref="csoCost" class="td ">
						 
						 	${lz:or(item$csoCost[i.count-1],lz:left(item.csoCost$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoPerson || #request.defines['csoPerson']!=null">
				<s:if test="#request.defines['csoPerson']>0">
					${lz:set("注释","****csoPerson关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoPerson))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoPerson",lz:indexOf(fieldName,"csoPerson")>-1)}
				  		<s:if test="#request.atCsoPerson==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoPerson字段的字串格式化输出****")}
					<td ref="csoPerson" class="td ">
						 
						 	${lz:or(item$csoPerson[i.count-1],lz:left(item.csoPerson$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoContact || #request.defines['csoContact']!=null">
				<s:if test="#request.defines['csoContact']>0">
					${lz:set("注释","****csoContact关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoContact))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoContact",lz:indexOf(fieldName,"csoContact")>-1)}
				  		<s:if test="#request.atCsoContact==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoContact字段的字串格式化输出****")}
					<td ref="csoContact" class="td ">
						 
						 	${lz:or(item$csoContact[i.count-1],lz:left(item.csoContact$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoUntil || #request.defines['csoUntil']!=null">
				<s:if test="#request.defines['csoUntil']>0">
					${lz:set("注释","****csoUntil关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoUntil))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoUntil",lz:indexOf(fieldName,"csoUntil")>-1)}
				  		<s:if test="#request.atCsoUntil==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoUntil字段的字串格式化输出****")}
					<td ref="csoUntil" class="td ">
						 
						 	${lz:or(item$csoUntil[i.count-1],lz:left(item.csoUntil$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoExpire || #request.defines['csoExpire']!=null">
				<s:if test="#request.defines['csoExpire']>0">
					${lz:set("注释","****csoExpire关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoExpire))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoExpire",lz:indexOf(fieldName,"csoExpire")>-1)}
				  		<s:if test="#request.atCsoExpire==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoExpire字段的字串格式化输出****")}
					<td ref="csoExpire" class="td ">
						 
						 	${lz:or(item$csoExpire[i.count-1],lz:left(item.csoExpire$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoPileTime || #request.defines['csoPileTime']!=null">
				<s:if test="#request.defines['csoPileTime']>0">
					${lz:set("注释","****csoPileTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoPileTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoPileTime",lz:indexOf(fieldName,"csoPileTime")>-1)}
				  		<s:if test="#request.atCsoPileTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoPileTime字段的字串格式化输出****")}
					<td ref="csoPileTime" class="td ">
						 
						 	${lz:or(item$csoPileTime[i.count-1],lz:left(item.csoPileTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoCarS || #request.defines['csoCarS']!=null">
				<s:if test="#request.defines['csoCarS']>0">
					${lz:set("注释","****csoCarS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoCarS))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoCarS",lz:indexOf(fieldName,"csoCarS")>-1)}
				  		<s:if test="#request.atCsoCarS==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoCarS字段的字串格式化输出****")}
					<td ref="csoCarS" class="td ">
						 
						 	${lz:or(item$csoCarS[i.count-1],lz:left(item.csoCarS$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoPositionS || #request.defines['csoPositionS']!=null">
				<s:if test="#request.defines['csoPositionS']>0">
					${lz:set("注释","****csoPositionS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoPositionS))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoPositionS",lz:indexOf(fieldName,"csoPositionS")>-1)}
				  		<s:if test="#request.atCsoPositionS==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoPositionS字段的字串格式化输出****")}
					<td ref="csoPositionS" class="td ">
						 
						 	${lz:or(item$csoPositionS[i.count-1],lz:left(item.csoPositionS$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoImage || #request.defines['csoImage']!=null">
				<s:if test="#request.defines['csoImage']>0">
					${lz:set("注释","****csoImage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoImage))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoImage",lz:indexOf(fieldName,"csoImage")>-1)}
				  		<s:if test="#request.atCsoImage==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoImage字段的字串格式化输出****")}
					<td ref="csoImage" class="td ">
						 
						 	${lz:or(item$csoImage[i.count-1],lz:left(item.csoImage$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoCanA || #request.defines['csoCanA']!=null">
				<s:if test="#request.defines['csoCanA']>0">
					${lz:set("注释","****csoCanA关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoCanA))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoCanA",lz:indexOf(fieldName,"csoCanA")>-1)}
				  		<s:if test="#request.atCsoCanA==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoCanA字段的字串格式化输出****")}
					<td ref="csoCanA" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csoId}','canAB')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csoCanA[i.count-1],lz:left(item.csoCanA$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoCanB || #request.defines['csoCanB']!=null">
				<s:if test="#request.defines['csoCanB']>0">
					${lz:set("注释","****csoCanB关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoCanB))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoCanB",lz:indexOf(fieldName,"csoCanB")>-1)}
				  		<s:if test="#request.atCsoCanB==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoCanB字段的字串格式化输出****")}
					<td ref="csoCanB" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csoId}','canAB')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csoCanB[i.count-1],lz:left(item.csoCanB$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoCanReg || #request.defines['csoCanReg']!=null">
				<s:if test="#request.defines['csoCanReg']>0">
					${lz:set("注释","****csoCanReg关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoCanReg))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoCanReg",lz:indexOf(fieldName,"csoCanReg")>-1)}
				  		<s:if test="#request.atCsoCanReg==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoCanReg字段的字串格式化输出****")}
					<td ref="csoCanReg" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csoId}','canReg')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csoCanReg[i.count-1],lz:left(item.csoCanReg$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoCanOrder || #request.defines['csoCanOrder']!=null">
				<s:if test="#request.defines['csoCanOrder']>0">
					${lz:set("注释","****csoCanOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoCanOrder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoCanOrder",lz:indexOf(fieldName,"csoCanOrder")>-1)}
				  		<s:if test="#request.atCsoCanOrder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoCanOrder字段的字串格式化输出****")}
					<td ref="csoCanOrder" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csoId}','canOrder')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csoCanOrder[i.count-1],lz:left(item.csoCanOrder$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoPriority || #request.defines['csoPriority']!=null">
				<s:if test="#request.defines['csoPriority']>0">
					${lz:set("注释","****csoPriority关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoPriority))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoPriority",lz:indexOf(fieldName,"csoPriority")>-1)}
				  		<s:if test="#request.atCsoPriority==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoPriority字段的字串格式化输出****")}
					<td ref="csoPriority" class="td ">
						 
						 	${lz:or(item$csoPriority[i.count-1],lz:left(item.csoPriority$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoVoteS || #request.defines['csoVoteS']!=null">
				<s:if test="#request.defines['csoVoteS']>0">
					${lz:set("注释","****csoVoteS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoVoteS))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoVoteS",lz:indexOf(fieldName,"csoVoteS")>-1)}
				  		<s:if test="#request.atCsoVoteS==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoVoteS字段的字串格式化输出****")}
					<td ref="csoVoteS" class="td ">
						 
						 	${lz:or(item$csoVoteS[i.count-1],lz:left(item.csoVoteS$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoProfile || #request.defines['csoProfile']!=null">
				<s:if test="#request.defines['csoProfile']>0">
					${lz:set("注释","****csoProfile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoProfile))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoProfile",lz:indexOf(fieldName,"csoProfile")>-1)}
				  		<s:if test="#request.atCsoProfile==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoProfile字段的字串格式化输出****")}
					<td ref="csoProfile" class="td ">
						 
						 	${lz:or(item$csoProfile[i.count-1],lz:left(item.csoProfile$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoOnlineTime || #request.defines['csoOnlineTime']!=null">
				<s:if test="#request.defines['csoOnlineTime']>0">
					${lz:set("注释","****csoOnlineTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoOnlineTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoOnlineTime",lz:indexOf(fieldName,"csoOnlineTime")>-1)}
				  		<s:if test="#request.atCsoOnlineTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoOnlineTime字段的字串格式化输出****")}
					<td ref="csoOnlineTime" class="td ">
						 
						 	${lz:or(item$csoOnlineTime[i.count-1],lz:left(item.csoOnlineTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoUpdateTime || #request.defines['csoUpdateTime']!=null">
				<s:if test="#request.defines['csoUpdateTime']>0">
					${lz:set("注释","****csoUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoUpdateTime",lz:indexOf(fieldName,"csoUpdateTime")>-1)}
				  		<s:if test="#request.atCsoUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoUpdateTime字段的字串格式化输出****")}
					<td ref="csoUpdateTime" class="td ">
						 
						 	${lz:or(item$csoUpdateTime[i.count-1],lz:left(item.csoUpdateTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoAddTime || #request.defines['csoAddTime']!=null">
				<s:if test="#request.defines['csoAddTime']>0">
					${lz:set("注释","****csoAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoAddTime",lz:indexOf(fieldName,"csoAddTime")>-1)}
				  		<s:if test="#request.atCsoAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoAddTime字段的字串格式化输出****")}
					<td ref="csoAddTime" class="td ">
						 
						 	${lz:or(item$csoAddTime[i.count-1],lz:left(item.csoAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoStatus || #request.defines['csoStatus']!=null">
				<s:if test="#request.defines['csoStatus']>0">
					${lz:set("注释","****csoStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoStatus",lz:indexOf(fieldName,"csoStatus")>-1)}
				  		<s:if test="#request.atCsoStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoStatus字段的字串格式化输出****")}
					<td ref="csoStatus" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csoId}','status')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csoStatus[i.count-1],lz:left(item.csoStatus$,100))}
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
					<a href="javascript:HandleSel('${handle}','${item.csoId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectOutlets('${item.csoId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditOutlets('${item.csoId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelOutlets('${item.csoId}','${item.csoName}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsOutlets('${item.csoId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csoId || #request.defines['csoId']!=null">
				<s:if test="#request.defines['csoId']>0">
					${lz:set("注释","****csoId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoId",lz:indexOf(fieldName,"csoId")>-1)}				  		
				  		<s:if test="#request.atCsoId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoHost || #request.defines['csoHost']!=null">
				<s:if test="#request.defines['csoHost']>0">
					${lz:set("注释","****csoHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoHost",lz:indexOf(fieldName,"csoHost")>-1)}				  		
				  		<s:if test="#request.atCsoHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoName || #request.defines['csoName']!=null">
				<s:if test="#request.defines['csoName']>0">
					${lz:set("注释","****csoName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoName",lz:indexOf(fieldName,"csoName")>-1)}				  		
				  		<s:if test="#request.atCsoName==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoName[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoArea || #request.defines['csoArea']!=null">
				<s:if test="#request.defines['csoArea']>0">
					${lz:set("注释","****csoArea关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoArea",lz:indexOf(fieldName,"csoArea")>-1)}				  		
				  		<s:if test="#request.atCsoArea==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoArea[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoType || #request.defines['csoType']!=null">
				<s:if test="#request.defines['csoType']>0">
					${lz:set("注释","****csoType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoType",lz:indexOf(fieldName,"csoType")>-1)}				  		
				  		<s:if test="#request.atCsoType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoLongitude || #request.defines['csoLongitude']!=null">
				<s:if test="#request.defines['csoLongitude']>0">
					${lz:set("注释","****csoLongitude关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoLongitude",lz:indexOf(fieldName,"csoLongitude")>-1)}				  		
				  		<s:if test="#request.atCsoLongitude==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoLongitude[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoLatitude || #request.defines['csoLatitude']!=null">
				<s:if test="#request.defines['csoLatitude']>0">
					${lz:set("注释","****csoLatitude关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoLatitude",lz:indexOf(fieldName,"csoLatitude")>-1)}				  		
				  		<s:if test="#request.atCsoLatitude==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoLatitude[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoAddress || #request.defines['csoAddress']!=null">
				<s:if test="#request.defines['csoAddress']>0">
					${lz:set("注释","****csoAddress关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoAddress",lz:indexOf(fieldName,"csoAddress")>-1)}				  		
				  		<s:if test="#request.atCsoAddress==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoAddress[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoSpace || #request.defines['csoSpace']!=null">
				<s:if test="#request.defines['csoSpace']>0">
					${lz:set("注释","****csoSpace关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoSpace",lz:indexOf(fieldName,"csoSpace")>-1)}				  		
				  		<s:if test="#request.atCsoSpace==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoSpace[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoDoorTime || #request.defines['csoDoorTime']!=null">
				<s:if test="#request.defines['csoDoorTime']>0">
					${lz:set("注释","****csoDoorTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoDoorTime",lz:indexOf(fieldName,"csoDoorTime")>-1)}				  		
				  		<s:if test="#request.atCsoDoorTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoDoorTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoCost || #request.defines['csoCost']!=null">
				<s:if test="#request.defines['csoCost']>0">
					${lz:set("注释","****csoCost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoCost",lz:indexOf(fieldName,"csoCost")>-1)}				  		
				  		<s:if test="#request.atCsoCost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoCost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoPerson || #request.defines['csoPerson']!=null">
				<s:if test="#request.defines['csoPerson']>0">
					${lz:set("注释","****csoPerson关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoPerson",lz:indexOf(fieldName,"csoPerson")>-1)}				  		
				  		<s:if test="#request.atCsoPerson==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoPerson[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoContact || #request.defines['csoContact']!=null">
				<s:if test="#request.defines['csoContact']>0">
					${lz:set("注释","****csoContact关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoContact",lz:indexOf(fieldName,"csoContact")>-1)}				  		
				  		<s:if test="#request.atCsoContact==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoContact[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoUntil || #request.defines['csoUntil']!=null">
				<s:if test="#request.defines['csoUntil']>0">
					${lz:set("注释","****csoUntil关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoUntil",lz:indexOf(fieldName,"csoUntil")>-1)}				  		
				  		<s:if test="#request.atCsoUntil==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoUntil[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoExpire || #request.defines['csoExpire']!=null">
				<s:if test="#request.defines['csoExpire']>0">
					${lz:set("注释","****csoExpire关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoExpire",lz:indexOf(fieldName,"csoExpire")>-1)}				  		
				  		<s:if test="#request.atCsoExpire==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoExpire[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoPileTime || #request.defines['csoPileTime']!=null">
				<s:if test="#request.defines['csoPileTime']>0">
					${lz:set("注释","****csoPileTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoPileTime",lz:indexOf(fieldName,"csoPileTime")>-1)}				  		
				  		<s:if test="#request.atCsoPileTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoPileTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoCarS || #request.defines['csoCarS']!=null">
				<s:if test="#request.defines['csoCarS']>0">
					${lz:set("注释","****csoCarS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoCarS",lz:indexOf(fieldName,"csoCarS")>-1)}				  		
				  		<s:if test="#request.atCsoCarS==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoCarS[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoPositionS || #request.defines['csoPositionS']!=null">
				<s:if test="#request.defines['csoPositionS']>0">
					${lz:set("注释","****csoPositionS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoPositionS",lz:indexOf(fieldName,"csoPositionS")>-1)}				  		
				  		<s:if test="#request.atCsoPositionS==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoPositionS[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoImage || #request.defines['csoImage']!=null">
				<s:if test="#request.defines['csoImage']>0">
					${lz:set("注释","****csoImage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoImage",lz:indexOf(fieldName,"csoImage")>-1)}				  		
				  		<s:if test="#request.atCsoImage==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoImage[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoCanA || #request.defines['csoCanA']!=null">
				<s:if test="#request.defines['csoCanA']>0">
					${lz:set("注释","****csoCanA关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoCanA",lz:indexOf(fieldName,"csoCanA")>-1)}				  		
				  		<s:if test="#request.atCsoCanA==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoCanA[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoCanB || #request.defines['csoCanB']!=null">
				<s:if test="#request.defines['csoCanB']>0">
					${lz:set("注释","****csoCanB关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoCanB",lz:indexOf(fieldName,"csoCanB")>-1)}				  		
				  		<s:if test="#request.atCsoCanB==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoCanB[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoCanReg || #request.defines['csoCanReg']!=null">
				<s:if test="#request.defines['csoCanReg']>0">
					${lz:set("注释","****csoCanReg关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoCanReg",lz:indexOf(fieldName,"csoCanReg")>-1)}				  		
				  		<s:if test="#request.atCsoCanReg==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoCanReg[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoCanOrder || #request.defines['csoCanOrder']!=null">
				<s:if test="#request.defines['csoCanOrder']>0">
					${lz:set("注释","****csoCanOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoCanOrder",lz:indexOf(fieldName,"csoCanOrder")>-1)}				  		
				  		<s:if test="#request.atCsoCanOrder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoCanOrder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoPriority || #request.defines['csoPriority']!=null">
				<s:if test="#request.defines['csoPriority']>0">
					${lz:set("注释","****csoPriority关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoPriority",lz:indexOf(fieldName,"csoPriority")>-1)}				  		
				  		<s:if test="#request.atCsoPriority==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoPriority[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoVoteS || #request.defines['csoVoteS']!=null">
				<s:if test="#request.defines['csoVoteS']>0">
					${lz:set("注释","****csoVoteS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoVoteS",lz:indexOf(fieldName,"csoVoteS")>-1)}				  		
				  		<s:if test="#request.atCsoVoteS==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoVoteS[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoProfile || #request.defines['csoProfile']!=null">
				<s:if test="#request.defines['csoProfile']>0">
					${lz:set("注释","****csoProfile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoProfile",lz:indexOf(fieldName,"csoProfile")>-1)}				  		
				  		<s:if test="#request.atCsoProfile==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoProfile[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoOnlineTime || #request.defines['csoOnlineTime']!=null">
				<s:if test="#request.defines['csoOnlineTime']>0">
					${lz:set("注释","****csoOnlineTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoOnlineTime",lz:indexOf(fieldName,"csoOnlineTime")>-1)}				  		
				  		<s:if test="#request.atCsoOnlineTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoOnlineTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoUpdateTime || #request.defines['csoUpdateTime']!=null">
				<s:if test="#request.defines['csoUpdateTime']>0">
					${lz:set("注释","****csoUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoUpdateTime",lz:indexOf(fieldName,"csoUpdateTime")>-1)}				  		
				  		<s:if test="#request.atCsoUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoAddTime || #request.defines['csoAddTime']!=null">
				<s:if test="#request.defines['csoAddTime']>0">
					${lz:set("注释","****csoAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoAddTime",lz:indexOf(fieldName,"csoAddTime")>-1)}				  		
				  		<s:if test="#request.atCsoAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoStatus || #request.defines['csoStatus']!=null">
				<s:if test="#request.defines['csoStatus']>0">
					${lz:set("注释","****csoStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoStatus",lz:indexOf(fieldName,"csoStatus")>-1)}				  		
				  		<s:if test="#request.atCsoStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 添加网点
	**/
	function AddOutlets(parent){		
		var url = "${basePath}${namespace}outlets_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加网点'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择网点
	**/
	function SelectOutlets(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改网点
	**/
	function EditOutlets(id){
		var url = "${basePath}${namespace}outlets_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改网点'}":lz:json(ctrl)}};
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
		EditOutlets(checker.val());
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
				var url = "${basePath}${namespace}outlets_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新网点",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}outlets_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新网点",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改网点
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}outlets_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新网点",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["canAB"]={};
	fieldNames["canAB"]={};
	fieldNames["canReg"]={};
	fieldNames["canOrder"]={};
	fieldNames["status"]={};
	fieldNames["canAB"]["csoCanA"]=true;
	fieldNames["canAB"]["csoCanB"]=true;
	fieldNames["canReg"]["csoCanReg"]=true;
	fieldNames["canOrder"]["csoCanOrder"]=true;
	fieldNames["status"]["csoStatus"]=true;
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改网点任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}outlets_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新网点",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}outlets_edit.do";
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
			var url = "${basePath}${namespace}outlets_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除网点
	**/
	function DelOutlets(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除网点["+flag+"]吗？</span>",
				title:"删除网点",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}outlets_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选网点吗？</span>",
				title:"批量删除网点",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}outlets_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示网点详情
	**/
	function DetailsOutlets(id)
	{
		var url = "${basePath}${namespace}outlets_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"网点详情",editable:false,visible:true}};
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
		DetailsOutlets(checker.val());
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