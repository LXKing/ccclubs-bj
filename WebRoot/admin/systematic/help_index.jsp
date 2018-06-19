<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/systematic/help.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty srvHelp.shId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"shId,shParent,shProfile,shEditor,shAddTime,shUpdateTime,shLevel",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"shId,shName,shParent,shProfile,shEditor,shAddTime,shUpdateTime,shLevel,shStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"shId,shProfile,shLevel",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"shId,shName,shProfile,shLevel,shStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>系统帮助管理</title>
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
	$(".table thead td[ref='shName']").width(160);
	$(".table thead td[ref='shLevel']").width(72);
	$(".table thead td[ref='shStatus']").width(100);
	
	var a = $("<a href=\"help_download.do\" target=\"_blank\" class=\"linker setting\"><img align=\"absmiddle\" src=\"${basePath}admin/images/icons/Arrow down.png\">数据导出</a>");
	a.insertAfter(".setting");
	
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
<div class="title">
	<a href="javascript:void(0);" onclick="parent.current.close();" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/Badge-multiply.png"/>
		关闭页面
	</a>
	<a href="javascript:void(0);" class="linker setting">
		<img align="absmiddle" src="${basePath}admin/images/icons/20088256421529677801.png"/>
		页面设置
	</a>
	<a href="?${lz:querys("UTF-8","xxxxx")}" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/2008825642250778028.png"/>
		重新加载
	</a>
	<s:if test="#request.shortcuts!=null || #request.haveQuerys==true">
		<a class="toggler" href="javascript:void(0);"><span class="${window.shortcuts!="hide"?"on":""}"></span></a>
	</s:if>
	<span class="caption">${empty title?"系统帮助管理":title}</span>
</div>


${before$form}

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
  			
	<div class="content">
		${lz:set("注释","*****************数据列表开始*****************")}
		<table id="help_table" ref="SrvHelp" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.shId || #request.defines['shId']!=null">
			 <td <s:if test="#request.defines['shId']>0">colspan="${defines["shId"]}" ${all?"width":"iwidth"}="${defines["shId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="0" ref="shId"   title="编号">
			 	<a class="${desc=="sh_id" ? "desc" : ""}${asc=="sh_id" ? "asc" : ""}" href="?${desc=="sh_id" ? "asc=sh_id" : ""}${(asc=="sh_id" || desc!="sh_id" )? "desc=sh_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","shId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.shName || #request.defines['shName']!=null">
			 <td <s:if test="#request.defines['shName']>0">colspan="${defines["shName"]}" ${all?"width":"iwidth"}="${defines["shName"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="1" ref="shName" flagKey  title="主题名称">
			 	<a class="${desc=="sh_name" ? "desc" : ""}${asc=="sh_name" ? "asc" : ""}" href="?${desc=="sh_name" ? "asc=sh_name" : ""}${(asc=="sh_name" || desc!="sh_name" )? "desc=sh_name" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	主题名称
			 	</a>
			 	${lz:set("checkeds[]","shName")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.shParent || #request.defines['shParent']!=null">
			 <td <s:if test="#request.defines['shParent']>0">colspan="${defines["shParent"]}" ${all?"width":"iwidth"}="${defines["shParent"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="shParent"   title="上级目录">
			 	<a class="${desc=="sh_parent" ? "desc" : ""}${asc=="sh_parent" ? "asc" : ""}" href="?${desc=="sh_parent" ? "asc=sh_parent" : ""}${(asc=="sh_parent" || desc!="sh_parent" )? "desc=sh_parent" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	上级目录
			 	</a>
			 	${lz:set("checkeds[]","shParent")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.shProfile || #request.defines['shProfile']!=null">
			 <td <s:if test="#request.defines['shProfile']>0">colspan="${defines["shProfile"]}" ${all?"width":"iwidth"}="${defines["shProfile"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="3" ref="shProfile"   title="帮助简述">
			 	<a class="${desc=="sh_profile" ? "desc" : ""}${asc=="sh_profile" ? "asc" : ""}" href="?${desc=="sh_profile" ? "asc=sh_profile" : ""}${(asc=="sh_profile" || desc!="sh_profile" )? "desc=sh_profile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	帮助简述
			 	</a>
			 	${lz:set("checkeds[]","shProfile")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.shEditor || #request.defines['shEditor']!=null">
			 <td <s:if test="#request.defines['shEditor']>0">colspan="${defines["shEditor"]}" ${all?"width":"iwidth"}="${defines["shEditor"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="shEditor"   title="修改人员">
			 	<a class="${desc=="sh_editor" ? "desc" : ""}${asc=="sh_editor" ? "asc" : ""}" href="?${desc=="sh_editor" ? "asc=sh_editor" : ""}${(asc=="sh_editor" || desc!="sh_editor" )? "desc=sh_editor" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改人员
			 	</a>
			 	${lz:set("checkeds[]","shEditor")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.shAddTime || #request.defines['shAddTime']!=null">
			 <td <s:if test="#request.defines['shAddTime']>0">colspan="${defines["shAddTime"]}" ${all?"width":"iwidth"}="${defines["shAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="5" ref="shAddTime"   title="添加时间">
			 	<a class="${desc=="sh_add_time" ? "desc" : ""}${asc=="sh_add_time" ? "asc" : ""}" href="?${desc=="sh_add_time" ? "asc=sh_add_time" : ""}${(asc=="sh_add_time" || desc!="sh_add_time" )? "desc=sh_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","shAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.shUpdateTime || #request.defines['shUpdateTime']!=null">
			 <td <s:if test="#request.defines['shUpdateTime']>0">colspan="${defines["shUpdateTime"]}" ${all?"width":"iwidth"}="${defines["shUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="6" ref="shUpdateTime"   title="修改时间">
			 	<a class="${desc=="sh_update_time" ? "desc" : ""}${asc=="sh_update_time" ? "asc" : ""}" href="?${desc=="sh_update_time" ? "asc=sh_update_time" : ""}${(asc=="sh_update_time" || desc!="sh_update_time" )? "desc=sh_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 	${lz:set("checkeds[]","shUpdateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.shLevel || #request.defines['shLevel']!=null">
			 <td <s:if test="#request.defines['shLevel']>0">colspan="${defines["shLevel"]}" ${all?"width":"iwidth"}="${defines["shLevel"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="7" ref="shLevel"   title="优先级">
			 	<a class="${desc=="sh_level" ? "desc" : ""}${asc=="sh_level" ? "asc" : ""}" href="?${desc=="sh_level" ? "asc=sh_level" : ""}${(asc=="sh_level" || desc!="sh_level" )? "desc=sh_level" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	优先级
			 	</a>
			 	${lz:set("checkeds[]","shLevel")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.shStatus || #request.defines['shStatus']!=null">
			 <td <s:if test="#request.defines['shStatus']>0">colspan="${defines["shStatus"]}" ${all?"width":"iwidth"}="${defines["shStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="shStatus"   title="状态">
			 	<a class="${desc=="sh_status" ? "desc" : ""}${asc=="sh_status" ? "asc" : ""}" href="?${desc=="sh_status" ? "asc=sh_status" : ""}${(asc=="sh_status" || desc!="sh_status" )? "desc=sh_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","shStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="9" class="options" ref="options">操作</td>
			<td rowspan="2" width="135" class="operation" tdid="10" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:AddHelp()" style="text-decoration:underline">添加</a>
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
		   <s:iterator value="#request.list" id="item" status="i">
		  <tr id="${item.shId}"  tree="${item.shParent}">
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.shId || #request.defines['shId']!=null">
				<s:if test="#request.defines['shId']>0">
					${lz:set("注释","****shId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.shId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atShId",lz:indexOf(fieldName,"shId")>-1)}
				  		<s:if test="#request.atShId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****shId字段的字串格式化输出****")}
					<td ref="shId" class="td ">
						 
						 	${lz:or(item$shId[i.count-1],lz:left(item.shId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.shName || #request.defines['shName']!=null">
				<s:if test="#request.defines['shName']>0">
					${lz:set("注释","****shName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.shName))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atShName",lz:indexOf(fieldName,"shName")>-1)}
				  		<s:if test="#request.atShName==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****shName字段的字串格式化输出****")}
					<td ref="shName" class="td  node">
						 
						 	${lz:or(item$shName[i.count-1],lz:left(item.shName$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.shParent || #request.defines['shParent']!=null">
				<s:if test="#request.defines['shParent']>0">
					${lz:set("注释","****shParent关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.shParent))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atShParent",lz:indexOf(fieldName,"shParent")>-1)}
				  		<s:if test="#request.atShParent==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****shParent字段的字串格式化输出****")}
					<td ref="shParent" class="td " relate="${item.shParent}">
						 
						 	<a <s:if test="#item.shParent!=null && #item.shParent!=''"> onclick="window.href('${basePath}${proname}/systematic/help_details.do?key=${item.shParent}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$shParent[i.count-1],lz:left(item.shParent$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.shProfile || #request.defines['shProfile']!=null">
				<s:if test="#request.defines['shProfile']>0">
					${lz:set("注释","****shProfile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.shProfile))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atShProfile",lz:indexOf(fieldName,"shProfile")>-1)}
				  		<s:if test="#request.atShProfile==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****shProfile字段的字串格式化输出****")}
					<td ref="shProfile" class="td ">
						 
						 	${lz:or(item$shProfile[i.count-1],lz:left(item.shProfile$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.shEditor || #request.defines['shEditor']!=null">
				<s:if test="#request.defines['shEditor']>0">
					${lz:set("注释","****shEditor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.shEditor))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atShEditor",lz:indexOf(fieldName,"shEditor")>-1)}
				  		<s:if test="#request.atShEditor==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****shEditor字段的字串格式化输出****")}
					<td ref="shEditor" class="td " relate="${item.shEditor}">
						 
						 	<a <s:if test="#item.shEditor!=null && #item.shEditor!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.shEditor}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$shEditor[i.count-1],lz:left(item.shEditor$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.shAddTime || #request.defines['shAddTime']!=null">
				<s:if test="#request.defines['shAddTime']>0">
					${lz:set("注释","****shAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.shAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atShAddTime",lz:indexOf(fieldName,"shAddTime")>-1)}
				  		<s:if test="#request.atShAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****shAddTime字段的字串格式化输出****")}
					<td ref="shAddTime" class="td ">
						 
						 	${lz:or(item$shAddTime[i.count-1],lz:left(item.shAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.shUpdateTime || #request.defines['shUpdateTime']!=null">
				<s:if test="#request.defines['shUpdateTime']>0">
					${lz:set("注释","****shUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.shUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atShUpdateTime",lz:indexOf(fieldName,"shUpdateTime")>-1)}
				  		<s:if test="#request.atShUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****shUpdateTime字段的字串格式化输出****")}
					<td ref="shUpdateTime" class="td ">
						 
						 	${lz:or(item$shUpdateTime[i.count-1],lz:left(item.shUpdateTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.shLevel || #request.defines['shLevel']!=null">
				<s:if test="#request.defines['shLevel']>0">
					${lz:set("注释","****shLevel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.shLevel))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atShLevel",lz:indexOf(fieldName,"shLevel")>-1)}
				  		<s:if test="#request.atShLevel==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****shLevel字段的字串格式化输出****")}
					<td ref="shLevel" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.shId}','level')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$shLevel[i.count-1],lz:left(item.shLevel$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.shStatus || #request.defines['shStatus']!=null">
				<s:if test="#request.defines['shStatus']>0">
					${lz:set("注释","****shStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.shStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atShStatus",lz:indexOf(fieldName,"shStatus")>-1)}
				  		<s:if test="#request.atShStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****shStatus字段的字串格式化输出****")}
					<td ref="shStatus" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.shId}','status')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$shStatus[i.count-1],lz:left(item.shStatus$,100))}
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
					<a href="javascript:HandleSel('${handle}','${item.shId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectHelp('${item.shId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canAdd==true">
				<a href="javascript:AddHelp('${item.shId}')">添加</a>
				</s:if>
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditHelp('${item.shId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelHelp('${item.shId}','${item.shName}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsHelp('${item.shId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.shId || #request.defines['shId']!=null">
				<s:if test="#request.defines['shId']>0">
					${lz:set("注释","****shId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atShId",lz:indexOf(fieldName,"shId")>-1)}				  		
				  		<s:if test="#request.atShId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">shId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.shName || #request.defines['shName']!=null">
				<s:if test="#request.defines['shName']>0">
					${lz:set("注释","****shName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atShName",lz:indexOf(fieldName,"shName")>-1)}				  		
				  		<s:if test="#request.atShName==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">shName[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.shParent || #request.defines['shParent']!=null">
				<s:if test="#request.defines['shParent']>0">
					${lz:set("注释","****shParent关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atShParent",lz:indexOf(fieldName,"shParent")>-1)}				  		
				  		<s:if test="#request.atShParent==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">shParent[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.shProfile || #request.defines['shProfile']!=null">
				<s:if test="#request.defines['shProfile']>0">
					${lz:set("注释","****shProfile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atShProfile",lz:indexOf(fieldName,"shProfile")>-1)}				  		
				  		<s:if test="#request.atShProfile==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">shProfile[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.shEditor || #request.defines['shEditor']!=null">
				<s:if test="#request.defines['shEditor']>0">
					${lz:set("注释","****shEditor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atShEditor",lz:indexOf(fieldName,"shEditor")>-1)}				  		
				  		<s:if test="#request.atShEditor==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">shEditor[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.shAddTime || #request.defines['shAddTime']!=null">
				<s:if test="#request.defines['shAddTime']>0">
					${lz:set("注释","****shAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atShAddTime",lz:indexOf(fieldName,"shAddTime")>-1)}				  		
				  		<s:if test="#request.atShAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">shAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.shUpdateTime || #request.defines['shUpdateTime']!=null">
				<s:if test="#request.defines['shUpdateTime']>0">
					${lz:set("注释","****shUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atShUpdateTime",lz:indexOf(fieldName,"shUpdateTime")>-1)}				  		
				  		<s:if test="#request.atShUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">shUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.shLevel || #request.defines['shLevel']!=null">
				<s:if test="#request.defines['shLevel']>0">
					${lz:set("注释","****shLevel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atShLevel",lz:indexOf(fieldName,"shLevel")>-1)}				  		
				  		<s:if test="#request.atShLevel==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">shLevel[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.shStatus || #request.defines['shStatus']!=null">
				<s:if test="#request.defines['shStatus']>0">
					${lz:set("注释","****shStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atShStatus",lz:indexOf(fieldName,"shStatus")>-1)}				  		
				  		<s:if test="#request.atShStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">shStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
  
 </td></tr></table>

<script>
$(function(){	
	$("#help_table").treegrid({expand:true});
});

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
	* 添加系统帮助
	**/
	function AddHelp(parent){		
		var url = "${basePath}${namespace}help_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加系统帮助'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择系统帮助
	**/
	function SelectHelp(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改系统帮助
	**/
	function EditHelp(id){
		var url = "${basePath}${namespace}help_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改系统帮助'}":lz:json(ctrl)}};
		href(url,params);
	}
	
	/**
	* 修改系统帮助
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}help_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新系统帮助",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["level"]={};
	fieldNames["status"]={};
	fieldNames["level"]["shLevel"]=true;
	fieldNames["status"]["shStatus"]=true;
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改系统帮助任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}help_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新系统帮助",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}help_edit.do";
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
			var url = "${basePath}${namespace}help_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除系统帮助
	**/
	function DelHelp(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除系统帮助["+flag+"]吗？</span>",
				title:"删除系统帮助",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}help_del.do";
					var params = {entrypoint:"${entrypoint}",id:id};
					$.submit(url,params);
				}
		 });	
	}
</s:if>
	/**
	* 显示系统帮助详情
	**/
	function DetailsHelp(id)
	{
		var url = "${basePath}${namespace}help_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"系统帮助详情",editable:false,visible:true}};
		href(url,params);
	}
</script>


<!------------------------LAZY3Q_JSP_BOTTOM------------------------>
<!------------------------LAZY3Q_JSP_BOTTOM------------------------>


</body>
</html>