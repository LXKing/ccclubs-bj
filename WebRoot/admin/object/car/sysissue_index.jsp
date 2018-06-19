<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/object/car/sysissue.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>
${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty carSysIssue.cscsiId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"cscsiId,cscsiHost,cscsiCar,cscsiParts,cscsiType,cscsiAddTime,cscsiUpdateTime",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"cscsiId,cscsiHost,cscsiCar,cscsiPartsStr,cscsiContent,cscsiKeywords,cscsiType,cscsiSrvuser,cscsiAddTime,cscsiUpdateTime",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"cscsiId,cscsiHost,cscsiCar,cscsiType,cscsiParts,cscsiAddTime,cscsiUpdateTime",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"cscsiId,cscsiHost,cscsiCar,cscsiPartsStr,cscsiContent,cscsiKeywords,cscsiType,cscsiSrvuser,cscsiAddTime,cscsiUpdateTime",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>车况报告</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="JUST">
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
	<span class="caption">${empty title?"车况报告":title}</span>
</div>
  	</s:if>
${before$form}

<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsCarSysIssue" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
			${lz:set("haveQuery",false)}
			
			${lz:set("注释","****导入查询表单各字段****")}
			<%@include file="sysissue.query.jsp"%>
	
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

</td>
</tr>
<tr>
	<td>
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
			<button type="button" class="add" onclick="AddCsCarSysIssue();">
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
			<dl ref="com.ccclubs.model.CsCarSysIssue" class="display dropdowan" style="margin-left:30px;">
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
		<table id="sysissue_table" ref="CsCarSysIssue" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
		  		<s:if test="#request.defines==null && #request.CTRL.l.cscsiId || #request.defines['cscsiId']!=null">
			 <td <s:if test="#request.defines['cscsiId']>0">colspan="${defines["cscsiId"]}" ${all?"width":"iwidth"}="${defines["cscsiId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="cscsiId"   title="编号">
			 	<a class="${desc=="cscsi_Id" ? "desc" : ""}${asc=="cscsi_Id" ? "asc" : ""}" href="?${desc=="cscsi_Id" ? "asc=cscsi_Id" : ""}${(asc=="cscsi_Id" || desc!="cscsi_Id" )? "desc=cscsi_Id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","cscsiId")}
			 </td>
		  </s:if> 		<s:if test="#request.defines==null && #request.CTRL.l.cscsiHost || #request.defines['cscsiHost']!=null">
			 <td <s:if test="#request.defines['cscsiHost']>0">colspan="${defines["cscsiHost"]}" ${all?"width":"iwidth"}="${defines["cscsiHost"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="2" ref="cscsiHost"   title="城市">
			 	<a class="${desc=="cscsi_host" ? "desc" : ""}${asc=="cscsi_host" ? "asc" : ""}" href="?${desc=="cscsi_host" ? "asc=cscsi_host" : ""}${(asc=="cscsi_host" || desc!="cscsi_host" )? "desc=cscsi_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","cscsiHost")}
			 </td>
		  </s:if> 		<s:if test="#request.defines==null && #request.CTRL.l.cscsiCar || #request.defines['cscsiCar']!=null">
			 <td <s:if test="#request.defines['cscsiCar']>0">colspan="${defines["cscsiCar"]}" ${all?"width":"iwidth"}="${defines["cscsiCar"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="3" ref="cscsiCar"   title="车辆ID">
			 	<a class="${desc=="cscsi_car" ? "desc" : ""}${asc=="cscsi_car" ? "asc" : ""}" href="?${desc=="cscsi_car" ? "asc=cscsi_car" : ""}${(asc=="cscsi_car" || desc!="cscsi_car" )? "desc=cscsi_car" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车辆名称
			 	</a>
			 	${lz:set("checkeds[]","cscsiCar")}
			 </td>
		  </s:if> 		<s:if test="#request.defines==null && #request.CTRL.l.cscsiParts || #request.defines['cscsiParts']!=null">
			 <td <s:if test="#request.defines['cscsiParts']>0">colspan="${defines["cscsiParts"]}" ${all?"width":"iwidth"}="${defines["cscsiParts"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="4" ref="cscsiParts"   title="车部件ID">
			 	<a class="${desc=="cscsi_parts" ? "desc" : ""}${asc=="cscsi_parts" ? "asc" : ""}" href="?${desc=="cscsi_parts" ? "asc=cscsi_parts" : ""}${(asc=="cscsi_parts" || desc!="cscsi_parts" )? "desc=cscsi_parts" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车部件ID
			 	</a>
			 	${lz:set("checkeds[]","cscsiParts")}
			 </td>
		  </s:if> 		<s:if test="#request.defines==null && #request.CTRL.l.cscsiPartsStr || #request.defines['cscsiPartsStr']!=null">
			 <td <s:if test="#request.defines['cscsiPartsStr']>0">colspan="${defines["cscsiPartsStr"]}" ${all?"width":"iwidth"}="${defines["cscsiPartsStr"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="5" ref="cscsiPartsStr"   title="数据状态">
			 	<a class="${desc=="cscsi_parts_str" ? "desc" : ""}${asc=="cscsi_parts_str" ? "asc" : ""}" href="?${desc=="cscsi_parts_str" ? "asc=cscsi_parts_str" : ""}${(asc=="cscsi_parts_str" || desc!="cscsi_parts_str" )? "desc=cscsi_parts_str" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车辆部位
			 	</a>
			 	${lz:set("checkeds[]","cscsiPartsStr")}
			 </td>
		  </s:if> 		<s:if test="#request.defines==null && #request.CTRL.l.cscsiContent || #request.defines['cscsiContent']!=null">
			 <td <s:if test="#request.defines['cscsiContent']>0">colspan="${defines["cscsiContent"]}" ${all?"width":"iwidth"}="${defines["cscsiContent"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="6" ref="cscsiContent"   title="内容">
			 	<a class="${desc=="cscsi_content" ? "desc" : ""}${asc=="cscsi_content" ? "asc" : ""}" href="?${desc=="cscsi_content" ? "asc=cscsi_content" : ""}${(asc=="cscsi_content" || desc!="cscsi_content" )? "desc=cscsi_content" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	内容
			 	</a>
			 	${lz:set("checkeds[]","cscsiContent")}
			 </td>
		  </s:if> 		<s:if test="#request.defines==null && #request.CTRL.l.cscsiKeywords || #request.defines['cscsiKeywords']!=null">
			 <td <s:if test="#request.defines['cscsiKeywords']>0">colspan="${defines["cscsiKeywords"]}" ${all?"width":"iwidth"}="${defines["cscsiKeywords"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="7" ref="cscsiKeywords"   title="关键字">
			 	<a class="${desc=="cscsi_keywords" ? "desc" : ""}${asc=="cscsi_keywords" ? "asc" : ""}" href="?${desc=="cscsi_keywords" ? "asc=cscsi_keywords" : ""}${(asc=="cscsi_keywords" || desc!="cscsi_keywords" )? "desc=cscsi_keywords" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车况标签
			 	</a>
			 	${lz:set("checkeds[]","cscsiKeywords")}
			 </td>
		  </s:if> 		<s:if test="#request.defines==null && #request.CTRL.l.cscsiImage || #request.defines['cscsiImage']!=null">
			 <td <s:if test="#request.defines['cscsiImage']>0">colspan="${defines["cscsiImage"]}" ${all?"width":"iwidth"}="${defines["cscsiImage"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="8" ref="cscsiImage"   title="图片">
			 	<a class="${desc=="cscsi_image" ? "desc" : ""}${asc=="cscsi_image" ? "asc" : ""}" href="?${desc=="cscsi_image" ? "asc=cscsi_image" : ""}${(asc=="cscsi_image" || desc!="cscsi_image" )? "desc=cscsi_image" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	图片
			 	</a>
			 	${lz:set("checkeds[]","cscsiImage")}
			 </td>
		  </s:if> 		<s:if test="#request.defines==null && #request.CTRL.l.cscsiType || #request.defines['cscsiType']!=null">
			 <td <s:if test="#request.defines['cscsiType']>0">colspan="${defines["cscsiType"]}" ${all?"width":"iwidth"}="${defines["cscsiType"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="9" ref="cscsiType"   title="图片">
			 	<a class="${desc=="cscsi_type" ? "desc" : ""}${asc=="cscsi_type" ? "asc" : ""}" href="?${desc=="cscsi_type" ? "asc=cscsi_type" : ""}${(asc=="cscsi_type" || desc!="cscsi_type" )? "desc=cscsi_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	类别
			 	</a>
			 	${lz:set("checkeds[]","cscsiType")}
			 </td>
		  </s:if> 		<s:if test="#request.defines==null && #request.CTRL.l.cscsiSrvuser || #request.defines['cscsiSrvuser']!=null">
			 <td <s:if test="#request.defines['cscsiSrvuser']>0">colspan="${defines["cscsiSrvuser"]}" ${all?"width":"iwidth"}="${defines["cscsiSrvuser"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="10" ref="cscsiSrvuser"   title="操作人">
			 	<a class="${desc=="cscsi_srvuser" ? "desc" : ""}${asc=="cscsi_srvuser" ? "asc" : ""}" href="?${desc=="cscsi_srvuser" ? "asc=cscsi_srvuser" : ""}${(asc=="cscsi_srvuser" || desc!="cscsi_srvuser" )? "desc=cscsi_srvuser" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	操作人
			 	</a>
			 	${lz:set("checkeds[]","cscsiSrvuser")}
			 </td>
		  </s:if> 		<s:if test="#request.defines==null && #request.CTRL.l.cscsiAddTime || #request.defines['cscsiAddTime']!=null">
			 <td <s:if test="#request.defines['cscsiAddTime']>0">colspan="${defines["cscsiAddTime"]}" ${all?"width":"iwidth"}="${defines["cscsiAddTime"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="11" ref="cscsiAddTime"   title="添加时间">
			 	<a class="${desc=="cscsi_add_time" ? "desc" : ""}${asc=="cscsi_add_time" ? "asc" : ""}" href="?${desc=="cscsi_add_time" ? "asc=cscsi_add_time" : ""}${(asc=="cscsi_add_time" || desc!="cscsi_add_time" )? "desc=cscsi_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","cscsiAddTime")}
			 </td>
		  </s:if> 		<s:if test="#request.defines==null && #request.CTRL.l.cscsiUpdateTime || #request.defines['cscsiUpdateTime']!=null">
			 <td <s:if test="#request.defines['cscsiUpdateTime']>0">colspan="${defines["cscsiUpdateTime"]}" ${all?"width":"iwidth"}="${defines["cscsiUpdateTime"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="12" ref="cscsiUpdateTime"   title="修改时间">
			 	<a class="${desc=="cscsi_update_time" ? "desc" : ""}${asc=="cscsi_update_time" ? "asc" : ""}" href="?${desc=="cscsi_update_time" ? "asc=cscsi_update_time" : ""}${(asc=="cscsi_update_time" || desc!="cscsi_update_time" )? "desc=cscsi_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 	${lz:set("checkeds[]","cscsiUpdateTime")}
			 </td>
		  </s:if> 		<s:if test="#request.defines==null && #request.CTRL.l.cscsiStatus || #request.defines['cscsiStatus']!=null">
			 <td <s:if test="#request.defines['cscsiStatus']>0">colspan="${defines["cscsiStatus"]}" ${all?"width":"iwidth"}="${defines["cscsiStatus"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="13" ref="cscsiStatus"   title="数据状态">
			 	<a class="${desc=="cscsi_status" ? "desc" : ""}${asc=="cscsi_status" ? "asc" : ""}" href="?${desc=="cscsi_status" ? "asc=cscsi_status" : ""}${(asc=="cscsi_status" || desc!="cscsi_status" )? "desc=cscsi_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	数据状态
			 	</a>
			 	${lz:set("checkeds[]","cscsiStatus")}
			 </td>
		  </s:if> 		
		  <%-- 
		  <s:if test="#request.defines==null && #request.CTRL.l.cscsiRemark || #request.defines['cscsiRemark']!=null">
			 <td <s:if test="#request.defines['cscsiRemark']>0">colspan="${defines["cscsiRemark"]}" ${all?"width":"iwidth"}="${defines["cscsiRemark"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="14" ref="cscsiRemark"   title="备注">
			 	<a class="${desc=="cscsi_remark" ? "desc" : ""}${asc=="cscsi_remark" ? "asc" : ""}" href="?${desc=="cscsi_remark" ? "asc=cscsi_remark" : ""}${(asc=="cscsi_remark" || desc!="cscsi_remark" )? "desc=cscsi_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备注
			 	</a>
			 	${lz:set("checkeds[]","cscsiRemark")}
			 </td>
		  </s:if> 
		  --%> 
		  ${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="8" class="options" ref="options">操作</td>
			<td rowspan="2" width="72" class="operation" tdid="9" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:AddCsCarSysIssue()" style="text-decoration:underline">添加</a>
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
		  <tr id="${item.cscsiId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.cscsiId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
						<s:if test="#request.defines==null && #request.CTRL.l.cscsiId || #request.defines['cscsiId']!=null">
				<s:if test="#request.defines['cscsiId']>0">
					${lz:set("注释","****cscsiId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsiId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsiId",lz:indexOf(fieldName,"cscsiId")>-1)}
				  		<s:if test="#request.atCscsiId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsiId字段的字串格式化输出****")}
					<td ref="cscsiId" class="td ">
						 
						 	${lz:or(item$cscsiId[i.count-1],lz:left(item.cscsiId$,100))}
					</td>
				</s:else>
			</s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.cscsiHost || #request.defines['cscsiHost']!=null">
				<s:if test="#request.defines['cscsiHost']>0">
					${lz:set("注释","****cscsiHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsiHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsiHost",lz:indexOf(fieldName,"cscsiHost")>-1)}
				  		<s:if test="#request.atCscsiHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsiHost字段的字串格式化输出****")}
					<td ref="cscsiHost" class="td ">
						 <a <s:if test="#item.cscsiHost!=null && #item.cscsiHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.cscsiHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscsiHost[i.count-1],lz:left(item.cscsiHost$,100))}
						 </a>
					</td>
				</s:else>
			</s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.cscsiCar || #request.defines['cscsiCar']!=null">
				<s:if test="#request.defines['cscsiCar']>0">
					${lz:set("注释","****cscsiCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsiCar))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsiCar",lz:indexOf(fieldName,"cscsiCar")>-1)}
				  		<s:if test="#request.atCscsiCar==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsiCar字段的字串格式化输出****")}
					<td ref="cscsiCar" class="td ">
						 <a <s:if test="#item.cscsiCar!=null && #item.cscsiCar!=''"> onclick="window.href('${basePath}${proname}/object/car_details.do?key=${item.cscsiCar}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscsiCar[i.count-1],lz:left(item.cscsiCar$,100))}
						 </a>
					</td>
				</s:else>
			</s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.cscsiParts || #request.defines['cscsiParts']!=null">
				<s:if test="#request.defines['cscsiParts']>0">
					${lz:set("注释","****cscsiParts关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsiParts))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsiParts",lz:indexOf(fieldName,"cscsiParts")>-1)}
				  		<s:if test="#request.atCscsiParts==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsiParts字段的字串格式化输出****")}
					<td ref="cscsiParts" class="td ">
						 
						 	${lz:or(item$cscsiParts[i.count-1],lz:left(item.cscsiParts$,100))}
					</td>
				</s:else>
			</s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.cscsiPartsStr || #request.defines['cscsiPartsStr']!=null">
				<s:if test="#request.defines['cscsiPartsStr']>0">
					${lz:set("注释","****cscsiPartsStr关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsiPartsStr))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsiPartsStr",lz:indexOf(fieldName,"cscsiPartsStr")>-1)}
				  		<s:if test="#request.atCscsiPartsStr==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsiPartsStr字段的字串格式化输出****")}
					<td ref="cscsiPartsStr" class="td ">
						 
						 	${lz:or(item$cscsiPartsStr[i.count-1],lz:left(item.cscsiPartsStr$,100))}
					</td>
				</s:else>
			</s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.cscsiContent || #request.defines['cscsiContent']!=null">
				<s:if test="#request.defines['cscsiContent']>0">
					${lz:set("注释","****cscsiContent关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsiContent))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsiContent",lz:indexOf(fieldName,"cscsiContent")>-1)}
				  		<s:if test="#request.atCscsiContent==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsiContent字段的字串格式化输出****")}
					<td ref="cscsiContent" class="td ">
						 
						 	${lz:or(item$cscsiContent[i.count-1],lz:left(item.cscsiContent$,100))}
					</td>
				</s:else>
			</s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.cscsiKeywords || #request.defines['cscsiKeywords']!=null">
				<s:if test="#request.defines['cscsiKeywords']>0">
					${lz:set("注释","****cscsiKeywords关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsiKeywords))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsiKeywords",lz:indexOf(fieldName,"cscsiKeywords")>-1)}
				  		<s:if test="#request.atCscsiKeywords==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsiKeywords字段的字串格式化输出****")}
					<td ref="cscsiKeywords" class="td ">
						 
						 	${lz:or(item$cscsiKeywords[i.count-1],lz:left(item.cscsiKeywords$,100))}
					</td>
				</s:else>
			</s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.cscsiImage || #request.defines['cscsiImage']!=null">
				<s:if test="#request.defines['cscsiImage']>0">
					${lz:set("注释","****cscsiImage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsiImage))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsiImage",lz:indexOf(fieldName,"cscsiImage")>-1)}
				  		<s:if test="#request.atCscsiImage==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsiImage字段的字串格式化输出****")}
					<td ref="cscsiImage" class="td ">
						 
						 	${lz:or(item$cscsiImage[i.count-1],lz:left(item.cscsiImage$,100))}
					</td>
				</s:else>
			</s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.cscsiType || #request.defines['cscsiType']!=null">
				<s:if test="#request.defines['cscsiType']>0">
					${lz:set("注释","****cscsiType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsiType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsiType",lz:indexOf(fieldName,"cscsiType")>-1)}
				  		<s:if test="#request.atCscsiType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsiType字段的字串格式化输出****")}
					<td ref="cscsiType" class="td ">
						 
						 	${lz:or(item$cscsiType[i.count-1],lz:left(item.cscsiType$,100))}
					</td>
				</s:else>
			</s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.cscsiSrvuser || #request.defines['cscsiSrvuser']!=null">
				<s:if test="#request.defines['cscsiSrvuser']>0">
					${lz:set("注释","****cscsiSrvuser关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsiSrvuser))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsiSrvuser",lz:indexOf(fieldName,"cscsiSrvuser")>-1)}
				  		<s:if test="#request.atCscsiSrvuser==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsiSrvuser字段的字串格式化输出****")}
					<td ref="cscsiSrvuser" class="td ">
						 
						 	${lz:or(item$cscsiSrvuser[i.count-1],lz:left(item.cscsiSrvuser$,100))}
					</td>
				</s:else>
			</s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.cscsiAddTime || #request.defines['cscsiAddTime']!=null">
				<s:if test="#request.defines['cscsiAddTime']>0">
					${lz:set("注释","****cscsiAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsiAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsiAddTime",lz:indexOf(fieldName,"cscsiAddTime")>-1)}
				  		<s:if test="#request.atCscsiAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsiAddTime字段的字串格式化输出****")}
					<td ref="cscsiAddTime" class="td ">
						 
						 	${lz:or(item$cscsiAddTime[i.count-1],lz:left(item.cscsiAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.cscsiUpdateTime || #request.defines['cscsiUpdateTime']!=null">
				<s:if test="#request.defines['cscsiUpdateTime']>0">
					${lz:set("注释","****cscsiUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsiUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsiUpdateTime",lz:indexOf(fieldName,"cscsiUpdateTime")>-1)}
				  		<s:if test="#request.atCscsiUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsiUpdateTime字段的字串格式化输出****")}
					<td ref="cscsiUpdateTime" class="td ">
						 
						 	${lz:or(item$cscsiUpdateTime[i.count-1],lz:left(item.cscsiUpdateTime$,100))}
					</td>
				</s:else>
			</s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.cscsiStatus || #request.defines['cscsiStatus']!=null">
				<s:if test="#request.defines['cscsiStatus']>0">
					${lz:set("注释","****cscsiStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsiStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsiStatus",lz:indexOf(fieldName,"cscsiStatus")>-1)}
				  		<s:if test="#request.atCscsiStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsiStatus字段的字串格式化输出****")}
					<td ref="cscsiStatus" class="td ">
						 
						 	${lz:or(item$cscsiStatus[i.count-1],lz:left(item.cscsiStatus$,100))}
					</td>
				</s:else>
			</s:if>
			<%-- 
			<s:if test="#request.defines==null && #request.CTRL.l.cscsiRemark || #request.defines['cscsiRemark']!=null">
				<s:if test="#request.defines['cscsiRemark']>0">
					${lz:set("注释","****cscsiRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsiRemark))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsiRemark",lz:indexOf(fieldName,"cscsiRemark")>-1)}
				  		<s:if test="#request.atCscsiRemark==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsiRemark字段的字串格式化输出****")}
					<td ref="cscsiRemark" class="td ">
						 
						 	${lz:or(item$cscsiRemark[i.count-1],lz:left(item.cscsiRemark$,100))}
					</td>
				</s:else>
			</s:if> 
			--%>

			 
			
			
		   ${lz:set("注释","*****************数据列表数据部分结束*****************")}		  
		  
		   
		    ${lz:set("注释","****自定义的单行记录操作句柄[request中的handles(String[])变量]****")}
			<td class="options">
				${lz:set("items",lz:split(handles,","))}
				<s:iterator value="#request.items" id="handle" status="i">
					${lz:set("isMenu",(lz:indexOf(handle,"@")==0))}
					<s:if test="#request.isMenu!=true">
					<a href="javascript:HandleSel('${handle}','${item.cscsiId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectCsCarSysIssue('${item.cscsiId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditCsCarSysIssue('${item.cscsiId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelCsCarSysIssue('${item.cscsiId}','${item.cscsiKeywords}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsCsCarSysIssue('${item.cscsiId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
		 	 
		 	 			<s:if test="#request.defines==null && #request.CTRL.l.cscsiId || #request.defines['cscsiId']!=null">
				<s:if test="#request.defines['cscsiId']>0">
					${lz:set("注释","****cscsiId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsiId",lz:indexOf(fieldName,"cscsiId")>-1)}				  		
				  		<s:if test="#request.atCscsiId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsiId[${k.count}]${lz:split(fieldName,"[]")[1]}</lz:set>
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
			 </s:if>			<s:if test="#request.defines==null && #request.CTRL.l.cscsiHost || #request.defines['cscsiHost']!=null">
				<s:if test="#request.defines['cscsiHost']>0">
					${lz:set("注释","****cscsiHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsiHost",lz:indexOf(fieldName,"cscsiHost")>-1)}				  		
				  		<s:if test="#request.atCscsiHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsiHost[${k.count}]${lz:split(fieldName,"[]")[1]}</lz:set>
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
			 </s:if>			<s:if test="#request.defines==null && #request.CTRL.l.cscsiCar || #request.defines['cscsiCar']!=null">
				<s:if test="#request.defines['cscsiCar']>0">
					${lz:set("注释","****cscsiCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsiCar",lz:indexOf(fieldName,"cscsiCar")>-1)}				  		
				  		<s:if test="#request.atCscsiCar==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsiCar[${k.count}]${lz:split(fieldName,"[]")[1]}</lz:set>
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
			 </s:if>			<s:if test="#request.defines==null && #request.CTRL.l.cscsiParts || #request.defines['cscsiParts']!=null">
				<s:if test="#request.defines['cscsiParts']>0">
					${lz:set("注释","****cscsiParts关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsiParts",lz:indexOf(fieldName,"cscsiParts")>-1)}				  		
				  		<s:if test="#request.atCscsiParts==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsiParts[${k.count}]${lz:split(fieldName,"[]")[1]}</lz:set>
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
			 </s:if>			<s:if test="#request.defines==null && #request.CTRL.l.cscsiPartsStr || #request.defines['cscsiPartsStr']!=null">
				<s:if test="#request.defines['cscsiPartsStr']>0">
					${lz:set("注释","****cscsiPartsStr关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsiPartsStr",lz:indexOf(fieldName,"cscsiPartsStr")>-1)}				  		
				  		<s:if test="#request.atCscsiPartsStr==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsiPartsStr[${k.count}]${lz:split(fieldName,"[]")[1]}</lz:set>
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
			 </s:if>			<s:if test="#request.defines==null && #request.CTRL.l.cscsiContent || #request.defines['cscsiContent']!=null">
				<s:if test="#request.defines['cscsiContent']>0">
					${lz:set("注释","****cscsiContent关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsiContent",lz:indexOf(fieldName,"cscsiContent")>-1)}				  		
				  		<s:if test="#request.atCscsiContent==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsiContent[${k.count}]${lz:split(fieldName,"[]")[1]}</lz:set>
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
			 </s:if>			<s:if test="#request.defines==null && #request.CTRL.l.cscsiKeywords || #request.defines['cscsiKeywords']!=null">
				<s:if test="#request.defines['cscsiKeywords']>0">
					${lz:set("注释","****cscsiKeywords关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsiKeywords",lz:indexOf(fieldName,"cscsiKeywords")>-1)}				  		
				  		<s:if test="#request.atCscsiKeywords==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsiKeywords[${k.count}]${lz:split(fieldName,"[]")[1]}</lz:set>
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
			 </s:if>			<s:if test="#request.defines==null && #request.CTRL.l.cscsiImage || #request.defines['cscsiImage']!=null">
				<s:if test="#request.defines['cscsiImage']>0">
					${lz:set("注释","****cscsiImage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsiImage",lz:indexOf(fieldName,"cscsiImage")>-1)}				  		
				  		<s:if test="#request.atCscsiImage==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsiImage[${k.count}]${lz:split(fieldName,"[]")[1]}</lz:set>
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
			 </s:if>			<s:if test="#request.defines==null && #request.CTRL.l.cscsiType || #request.defines['cscsiType']!=null">
				<s:if test="#request.defines['cscsiType']>0">
					${lz:set("注释","****cscsiType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsiType",lz:indexOf(fieldName,"cscsiType")>-1)}				  		
				  		<s:if test="#request.atCscsiType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsiType[${k.count}]${lz:split(fieldName,"[]")[1]}</lz:set>
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
			 </s:if>			<s:if test="#request.defines==null && #request.CTRL.l.cscsiSrvuser || #request.defines['cscsiSrvuser']!=null">
				<s:if test="#request.defines['cscsiSrvuser']>0">
					${lz:set("注释","****cscsiSrvuser关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsiSrvuser",lz:indexOf(fieldName,"cscsiSrvuser")>-1)}				  		
				  		<s:if test="#request.atCscsiSrvuser==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsiSrvuser[${k.count}]${lz:split(fieldName,"[]")[1]}</lz:set>
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
			 </s:if>			<s:if test="#request.defines==null && #request.CTRL.l.cscsiAddTime || #request.defines['cscsiAddTime']!=null">
				<s:if test="#request.defines['cscsiAddTime']>0">
					${lz:set("注释","****cscsiAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsiAddTime",lz:indexOf(fieldName,"cscsiAddTime")>-1)}				  		
				  		<s:if test="#request.atCscsiAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsiAddTime[${k.count}]${lz:split(fieldName,"[]")[1]}</lz:set>
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
			 </s:if>			<s:if test="#request.defines==null && #request.CTRL.l.cscsiUpdateTime || #request.defines['cscsiUpdateTime']!=null">
				<s:if test="#request.defines['cscsiUpdateTime']>0">
					${lz:set("注释","****cscsiUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsiUpdateTime",lz:indexOf(fieldName,"cscsiUpdateTime")>-1)}				  		
				  		<s:if test="#request.atCscsiUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsiUpdateTime[${k.count}]${lz:split(fieldName,"[]")[1]}</lz:set>
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
			 </s:if>			<s:if test="#request.defines==null && #request.CTRL.l.cscsiStatus || #request.defines['cscsiStatus']!=null">
				<s:if test="#request.defines['cscsiStatus']>0">
					${lz:set("注释","****cscsiStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsiStatus",lz:indexOf(fieldName,"cscsiStatus")>-1)}				  		
				  		<s:if test="#request.atCscsiStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsiStatus[${k.count}]${lz:split(fieldName,"[]")[1]}</lz:set>
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
			 <%-- 
			 <s:if test="#request.defines==null && #request.CTRL.l.cscsiRemark || #request.defines['cscsiRemark']!=null">
				<s:if test="#request.defines['cscsiRemark']>0">
					${lz:set("注释","****cscsiRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsiRemark",lz:indexOf(fieldName,"cscsiRemark")>-1)}				  		
				  		<s:if test="#request.atCscsiRemark==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsiRemark[${k.count}]${lz:split(fieldName,"[]")[1]}</lz:set>
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
			 --%>
			
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
	* 添加车况
	**/
	function AddCsCarSysIssue(parent){		
		var url = "${basePath}${namespace}sysissue_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加车况'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择车况
	**/
	function SelectCsCarSysIssue(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>

<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改车况
	**/
	function EditCsCarSysIssue(id){
		var url = "${basePath}${namespace}sysissue_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改车况'}":lz:json(ctrl)}};
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
		EditCsCarSysIssue(checker.val());
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
				var url = "${basePath}${namespace}sysissue_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新车况",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}sysissue_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新车况",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改会员卡
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}sysissue_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新车况",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["status"]={};
	fieldNames["status"]["cscsiStatus"]=true;
	
</s:if>
<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改车况任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}sysissue_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新车况",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}sysissue_edit.do";
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
			var url = "${basePath}${namespace}sysissue_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除车况
	**/
	function DelCsCarSysIssue(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除车况["+flag+"]吗？</span>",
				title:"删除车况",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}sysissue_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选车况吗？</span>",
				title:"批量删除车况",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}sysissue_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
/**
	* 显示车况详情
	**/
	function DetailsCsCarSysIssue(id)
	{
		var url = "${basePath}${namespace}sysissue_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"车况详情",editable:false,visible:true}};
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
		DetailsCsCarSysIssue(checker.val());
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
		  


