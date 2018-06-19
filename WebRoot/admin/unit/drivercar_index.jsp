<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/unit/drivercar.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty CsDriverCar.csdcId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csdcId,csdcHost,csdcOutlets,csdcDriver,csdcCar,csdcState,csdcStatus,csdcAddTime,csdcUpdateTime",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csdcId,csdcHost,csdcOutlets,csdcDriver,csdcCar,csdcState,csdcStatus,csdcAddTime,csdcUpdateTime",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csdcId,csdcHost,csdcOutlets,csdcDriver,csdcCar,csdcState,csdcStatus,csdcAddTime,csdcUpdateTime,csdName,csdMobile,cscrCarNo",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csdcId,csdcHost,csdcOutlets,csdcDriver,csdcCar,csdcState,csdcStatus,csdcAddTime,csdcUpdateTime,csdName,csdMobile,cscrCarNo",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>司机专车管理</title>
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
		<span class="caption">${empty title?"司机专车管理":title}</span>
	</div>
</s:if>

${before$form}
<s:if test="#request.CTRL.canQuery==true">
	<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
		<form ref="CsDriverCar" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
			${lz:set("haveQuery",false)}
			
			${lz:set("注释","****导入查询表单各字段****")}
			<%@include file="drivercar.query.jsp"%>
	
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
			<button type="button" class="add" onclick="Adddrivercar();">
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
			<dl ref="com.ccclubs.model.CsDriverCar" class="display dropdowan" style="margin-left:30px;">
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
		<table id="drivercar_table" ref="CsDriverCar" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csdcId || #request.defines['csdcId']!=null">
			 <td <s:if test="#request.defines['csdcId']>0">colspan="${defines["csdcId"]}" ${all?"width":"iwidth"}="${defines["csdcId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=72":""} </s:else> tdid="1" ref="csdcId"   title="编号">
			 	<a class="${desc=="csdc_id" ? "desc" : ""}${asc=="csdc_id" ? "asc" : ""}" href="?${desc=="csdc_id" ? "asc=csdc_id" : ""}${(asc=="csdc_id" || desc!="csdc_id" )? "desc=csdc_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","csdcId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csdcHost || #request.defines['csdcHost']!=null">
			 <td <s:if test="#request.defines['csdcHost']>0">colspan="${defines["csdcHost"]}" ${all?"width":"iwidth"}="${defines["csdcHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=100":""} </s:else> tdid="2" ref="csdcHost"   title="城市">
			 	<a class="${desc=="csdc_host" ? "desc" : ""}${asc=="csdc_host" ? "asc" : ""}" href="?${desc=="csdc_host" ? "asc=csdc_host" : ""}${(asc=="csdc_host" || desc!="csdc_host" )? "desc=csdc_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","csdcHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csdcOutlets || #request.defines['csdcOutlets']!=null">
			 <td <s:if test="#request.defines['csdcOutlets']>0">colspan="${defines["csdcOutlets"]}" ${all?"width":"iwidth"}="${defines["csdcOutlets"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=100":""} </s:else> tdid="9" ref="csdcOutlets"   title="指定网点">
			 	<a class="${desc=="csdc_outlets" ? "desc" : ""}${asc=="csdc_outlets" ? "asc" : ""}" href="?${desc=="csdc_outlets" ? "asc=csdc_outlets" : ""}${(asc=="csdc_outlets" || desc!="csdc_outlets" )? "desc=csdc_outlets" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	指定网点
			 	</a>
			 	${lz:set("checkeds[]","csdcOutlets")}
			 </td>
			 </s:if>
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csdcDriver || #request.defines['csdcDriver']!=null">
			 <td <s:if test="#request.defines['csdcDriver']>0">colspan="${defines["csdcDriver"]}" ${all?"width":"iwidth"}="${defines["csdcDriver"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="csdcDriver" flagKey  title="姓名">
			 	<a class="${desc=="csdc_driver" ? "desc" : ""}${asc=="csdc_driver" ? "asc" : ""}" href="?${desc=="csdc_driver" ? "asc=csdc_driver" : ""}${(asc=="csdc_driver" || desc!="csdc_driver" )? "desc=csdc_driver" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	司机姓名
			 	</a>
			 	${lz:set("checkeds[]","csdcDriver")}
			 </td>
			 </s:if>
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csdcDriver || #request.defines['csdcDriver']!=null">
			 <td <s:if test="#request.defines['csdcDriver']>0">colspan="${defines["csdcDriver"]}" ${all?"width":"iwidth"}="${defines["csdcDriver"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="csdcDriver" flagKey  title="姓名">
			 	<a class="${desc=="csdc_driver" ? "desc" : ""}${asc=="csdc_driver" ? "asc" : ""}" href="?${desc=="csdc_driver" ? "asc=csdc_driver" : ""}${(asc=="csdc_driver" || desc!="csdc_driver" )? "desc=csdc_driver" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	司机手机号
			 	</a>
			 	${lz:set("checkeds[]","csdcDriver")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csdcCar || #request.defines['csdcCar']!=null">
			 <td <s:if test="#request.defines['csdcCar']>0">colspan="${defines["csdcCar"]}" ${all?"width":"iwidth"}="${defines["csdcCar"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=60":""} </s:else> tdid="5" ref="csdcCar"   title="性别">
			 	<a class="${desc=="csdc_car" ? "desc" : ""}${asc=="csdc_car" ? "asc" : ""}" href="?${desc=="csdc_car" ? "asc=csdc_car" : ""}${(asc=="csdc_car" || desc!="csdc_car" )? "desc=csdc_car" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车牌号
			 	</a>
			 	${lz:set("checkeds[]","csdcCar")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csdcState || #request.defines['csdcState']!=null">
			 <td <s:if test="#request.defines['csdcState']>0">colspan="${defines["csdcState"]}" ${all?"width":"iwidth"}="${defines["csdcState"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="csdcState"   title="手机号码">
			 	<a class="${desc=="csdc_state" ? "desc" : ""}${asc=="csdc_state" ? "asc" : ""}" href="?${desc=="csdc_state" ? "asc=csdc_state" : ""}${(asc=="csdc_state" || desc!="csdc_state" )? "desc=csdc_state" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	业务状态
			 	</a>
			 	${lz:set("checkeds[]","csdcState")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csdcStatus || #request.defines['csdcStatus']!=null">
			 <td <s:if test="#request.defines['csdcStatus']>0">colspan="${defines["csdcStatus"]}" ${all?"width":"iwidth"}="${defines["csdcStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="7" ref="csdcStatus"   title="司机照片">
			 	<a class="${desc=="csdc_status" ? "desc" : ""}${asc=="csdc_status" ? "asc" : ""}" href="?${desc=="csdc_status" ? "asc=csdc_status" : ""}${(asc=="csdc_status" || desc!="csdc_status" )? "desc=csdc_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	数据状态
			 	</a>
			 	${lz:set("checkeds[]","csdcStatus")}
			 </td>
			 </s:if>
			
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csdcAddTime || #request.defines['csdcAddTime']!=null">
			 <td <s:if test="#request.defines['csdcAddTime']>0">colspan="${defines["csdcAddTime"]}" ${all?"width":"iwidth"}="${defines["csdcAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="10" ref="csdcAddTime"   title="创建时间">
			 	<a class="${desc=="csdc_add_time" ? "desc" : ""}${asc=="csdc_add_time" ? "asc" : ""}" href="?${desc=="csdc_add_time" ? "asc=csdc_add_time" : ""}${(asc=="csdc_add_time" || desc!="csdc_add_time" )? "desc=csdc_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	创建时间
			 	</a>
			 	${lz:set("checkeds[]","csdcAddTime")}
			 </td>
			 </s:if>
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csdcUpdateTime || #request.defines['csdcUpdateTime']!=null">
			 <td <s:if test="#request.defines['csdcUpdateTime']>0">colspan="${defines["csdcUpdateTime"]}" ${all?"width":"iwidth"}="${defines["csdcUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="10" ref="csdcUpdateTime"   title="修改时间">
			 	<a class="${desc=="csdc_update_time" ? "desc" : ""}${asc=="csdc_update_time" ? "asc" : ""}" href="?${desc=="csdc_update_time" ? "asc=csdc_update_time" : ""}${(asc=="csdc_update_time" || desc!="csdc_update_time" )? "desc=csdc_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 	${lz:set("checkeds[]","csdcUpdateTime")}
			 </td>
			 </s:if>
			 
		 
	    	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="11" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="12" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:Adddrivercar()" style="text-decoration:underline">添加</a>
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
		  <tr id="${item.csdcId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csdcId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csdcId || #request.defines['csdcId']!=null">
				<s:if test="#request.defines['csdcId']>0">
					${lz:set("注释","****csdcId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csdcId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atcsdcId",lz:indexOf(fieldName,"csdcId")>-1)}
				  		<s:if test="#request.atcsdcId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csdcId字段的字串格式化输出****")}
					<td ref="csdcId" class="td ">
						 
						 	${lz:or(item$csdcId[i.count-1],lz:left(item.csdcId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csdcHost || #request.defines['csdcHost']!=null">
				<s:if test="#request.defines['csdcHost']>0">
					${lz:set("注释","****csdcHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csdcHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atcsdcHost",lz:indexOf(fieldName,"csdcHost")>-1)}
				  		<s:if test="#request.atcsdcHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csdcHost字段的字串格式化输出****")}
					<td ref="csdcHost" class="td " relate="${item.csdcHost}">
						 
						 	<a <s:if test="#item.csdcHost!=null && #item.csdcHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.csdcHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csdcHost[i.count-1],lz:left(item.csdcHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csdcOutlets || #request.defines['csdcOutlets']!=null">
				<s:if test="#request.defines['csdcOutlets']>0">
					${lz:set("注释","****csdcOutlets关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csdcOutlets))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atcsdcOutlets",lz:indexOf(fieldName,"csdcOutlets")>-1)}
				  		<s:if test="#request.atcsdcOutlets==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csdcOutlets字段的字串格式化输出****")}
					<td ref="csdcOutlets" class="td " relate="${item.csdcOutlets}">
						 
						 	<a <s:if test="#item.csdcOutlets!=null && #item.csdcOutlets!=''"> onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${item.csdcOutlets}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${item.csdcOutlets$}</a>
						 	
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csdcDriver || #request.defines['csdcDriver']!=null">
				<s:if test="#request.defines['csdcDriver']>0">
					${lz:set("注释","****csdcDriver关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csdcDriver))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atcsdcDriver",lz:indexOf(fieldName,"csdcDriver")>-1)}
				  		<s:if test="#request.atcsdcDriver==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csdcDriver字段的字串格式化输出****")}
					<td ref="csdcDriver" class="td  node">
							<a <s:if test="#item.csdcDriver!=null && #item.csdcDriver!=''"> onclick="window.href('${basePath}${proname}/unit/driver_details.do?id=${item.csdcDriver}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
					
						 	${lz:or(item$csdcDriver[i.count-1],lz:left(item.$csdcDriver.csdName$,100))}</a>
					</td>
				</s:else>
			</s:if>
			
			<s:if test="#request.defines==null && #request.CTRL.l.csdcDriver || #request.defines['csdcDriver']!=null">
				<s:if test="#request.defines['csdcDriver']>0">
					${lz:set("注释","****csdcDriver关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csdcDriver))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atcsdcDriver",lz:indexOf(fieldName,"csdcDriver")>-1)}
				  		<s:if test="#request.atcsdcDriver==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csdcDriver字段的字串格式化输出****")}
					<td ref="csdcDriver" class="td  node">
							<a <s:if test="#item.csdcDriver!=null && #item.csdcDriver!=''"> onclick="window.href('${basePath}${proname}/unit/driver_details.do?id=${item.csdcDriver}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
					
						 	${lz:or(item$csdcDriver[i.count-1],lz:left(item.$csdcDriver.csdMobile$,100))}</a>
					</td>
				</s:else>
			</s:if>
			
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csdcCar || #request.defines['csdcCar']!=null">
				<s:if test="#request.defines['csdcCar']>0">
					${lz:set("注释","****csdcCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csdcCar))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atcsdcCar",lz:indexOf(fieldName,"csdcCar")>-1)}
				  		<s:if test="#request.atcsdcCar==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csdcCar字段的字串格式化输出****")}
					<td ref="csdcCar" class="td " relate="${item.csdcCar}">
						 	<a <s:if test="#item.csdcCar!=null && #item.csdcCar!=''"> onclick="window.href('${basePath}${proname}/unit/carres_details.do?key=${item.csdcCar}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 
						 	${lz:or(item$csdcCar[i.count-1],lz:left(item.$csdcCar.cscrCarNo$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csdcState || #request.defines['csdcState']!=null">
				<s:if test="#request.defines['csdcState']>0">
					${lz:set("注释","****csdcState关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csdcState))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atcsdcState",lz:indexOf(fieldName,"csdcState")>-1)}
				  		<s:if test="#request.atcsdcState==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csdcState字段的字串格式化输出****")}
					<td ref="csdcState" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csdcId}','state')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csdcState[i.count-1],lz:left(item.csdcState$,100))}
					</td>
				</s:else>
			</s:if>
			
			<s:if test="#request.defines==null && #request.CTRL.l.csdcStatus || #request.defines['csdcStatus']!=null">
				<s:if test="#request.defines['csdcStatus']>0">
					${lz:set("注释","****csdcStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csdcStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atcsdcStatus",lz:indexOf(fieldName,"csdcStatus")>-1)}
				  		<s:if test="#request.atcsdcStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csdcStatus字段的字串格式化输出****")}
					<td ref="csdcStatus" class="td ">
						 	${lz:or(item$csdcStatus[i.count-1],lz:left(item.csdcStatus$,100))}
					</td>
				</s:else>
			</s:if>
			
			<s:if test="#request.defines==null && #request.CTRL.l.csdcAddTime || #request.defines['csdcAddTime']!=null">
				<s:if test="#request.defines['csdcAddTime']>0">
					${lz:set("注释","****csdcAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csdcAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atcsdcAddTime",lz:indexOf(fieldName,"csdcAddTime")>-1)}
				  		<s:if test="#request.atcsdcAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csdcAddTime字段的字串格式化输出****")}
					<td ref="csdcAddTime" class="td ">
						 	${lz:or(item$csdcAddTime[i.count-1],lz:left(item.csdcAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			
			<s:if test="#request.defines==null && #request.CTRL.l.csdcUpdateTime || #request.defines['csdcUpdateTime']!=null">
				<s:if test="#request.defines['csdcUpdateTime']>0">
					${lz:set("注释","****csdcAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csdcUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atcsdcAddTime",lz:indexOf(fieldName,"csdcUpdateTime")>-1)}
				  		<s:if test="#request.atcsdcUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csdcUpdateTime字段的字串格式化输出****")}
					<td ref="csdcUpdateTime" class="td ">
						 	${lz:or(item$csdcUpdateTime[i.count-1],lz:left(item.csdcUpdateTime$,100))}
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
					<a href="javascript:HandleSel('${handle}','${item.csdcId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:Selectdrivercar('${item.csdcId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:Editdrivercar('${item.csdcId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:Deldrivercar('${item.csdcId}','${item.csdcDriver}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:Detailsdrivercar('${item.csdcId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csdcId || #request.defines['csdcId']!=null">
				<s:if test="#request.defines['csdcId']>0">
					${lz:set("注释","****csdcId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atcsdcId",lz:indexOf(fieldName,"csdcId")>-1)}				  		
				  		<s:if test="#request.atcsdcId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csdcId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csdcHost || #request.defines['csdcHost']!=null">
				<s:if test="#request.defines['csdcHost']>0">
					${lz:set("注释","****csdcHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atcsdcHost",lz:indexOf(fieldName,"csdcHost")>-1)}				  		
				  		<s:if test="#request.atcsdcHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csdcHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csdcOutlets || #request.defines['csdcOutlets']!=null">
				<s:if test="#request.defines['csdcOutlets']>0">
					${lz:set("注释","****csdcOutlets关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atcsdcOutlets",lz:indexOf(fieldName,"csdcOutlets")>-1)}				  		
				  		<s:if test="#request.atcsdcOutlets==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csdcOutlets[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			 
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csdcDriver || #request.defines['csdcDriver']!=null">
				<s:if test="#request.defines['csdcDriver']>0">
					${lz:set("注释","****csdcDriver关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atcsdcDriver",lz:indexOf(fieldName,"csdcDriver")>-1)}				  		
				  		<s:if test="#request.atcsdcDriver==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csdcDriver[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			 <s:if test="#request.defines==null && #request.CTRL.l.csdcDriver || #request.defines['csdcDriver']!=null">
				<s:if test="#request.defines['csdcDriver']>0">
					${lz:set("注释","****csdcDriver关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atcsdcDriver",lz:indexOf(fieldName,"csdcDriver")>-1)}				  		
				  		<s:if test="#request.atcsdcDriver==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csdcDriver[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csdcCar || #request.defines['csdcCar']!=null">
				<s:if test="#request.defines['csdcCar']>0">
					${lz:set("注释","****csdcCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atcsdcCar",lz:indexOf(fieldName,"csdcCar")>-1)}				  		
				  		<s:if test="#request.atcsdcCar==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csdcCar[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			
			
			
			
			<s:if test="#request.defines==null && #request.CTRL.l.csdcState || #request.defines['csdcState']!=null">
				<s:if test="#request.defines['csdcState']>0">
					${lz:set("注释","****csdcState关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atcsdcState",lz:indexOf(fieldName,"csdcState")>-1)}				  		
				  		<s:if test="#request.atcsdcState==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csdcState[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csdcStatus || #request.defines['csdcStatus']!=null">
				<s:if test="#request.defines['csdcStatus']>0">
					${lz:set("注释","****csdcStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atcsdcStatus",lz:indexOf(fieldName,"csdcStatus")>-1)}				  		
				  		<s:if test="#request.atcsdcStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csdcStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csdcAddTime || #request.defines['csdcAddTime']!=null">
				<s:if test="#request.defines['csdcAddTime']>0">
					${lz:set("注释","****csdcAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atcsdcAddTime",lz:indexOf(fieldName,"csdcAddTime")>-1)}				  		
				  		<s:if test="#request.atcsdcAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csdcAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csdcUpdateTime || #request.defines['csdcUpdateTime']!=null">
				<s:if test="#request.defines['csdcUpdateTime']>0">
					${lz:set("注释","****csdcUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atcsdcUpdateTime",lz:indexOf(fieldName,"csdcUpdateTime")>-1)}				  		
				  		<s:if test="#request.atcsdcUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csdcUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
  
 </td></tr></table><script>

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
	* 添加司机人员
	**/
	function Adddrivercar(parent){		
		var url = "${basePath}${namespace}drivercar_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加司机专车'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择司机人员
	**/
	function Selectdrivercar(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改司机人员
	**/
	function Editdrivercar(id){
		var url = "${basePath}${namespace}drivercar_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改司机人员'}":lz:json(ctrl)}};
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
		Editdrivercar(checker.val());
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
				alert(fieldNames);
				var url = "${basePath}${namespace}drivercar_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新司机人员",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}drivercar_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新用车人员",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改用车人员
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}drivercar_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新用车人员",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["state"]={};
	fieldNames["state"]["csdcState"]=true;
	
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改用车人员任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}drivercar_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新用车人员",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}drivercar_edit.do";
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
			var url = "${basePath}${namespace}drivercar_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除用车人员
	**/
	function Deldrivercar(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除司机人员["+flag+"]吗？</span>",
				title:"删除用车人员",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}drivercar_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选用车人员吗？</span>",
				title:"批量删除用车人员",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}drivercar_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示用车人员详情
	**/
	function Detailsdrivercar(id)
	{
		var url = "${basePath}${namespace}drivercar_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"司机人员详情",editable:false,visible:true}};
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
		Detailsdrivercar(checker.val());
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
