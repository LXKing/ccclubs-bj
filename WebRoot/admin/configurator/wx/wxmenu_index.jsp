<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/configurator/wx/wxmenu.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csWxMenu.cswmId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"cswmId,cswmName,cswmSupport,cswmLevel,cswmTime,cswmStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"cswmId,cswmName,cswmParent,cswmType,cswmSupport,cswmUrlKey,cswmLevel,cswmTime,cswmStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"cswmId,cswmName,cswmSupport,cswmLevel,cswmTime,cswmStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"cswmId,cswmName,cswmParent,cswmType,cswmSupport,cswmUrlKey,cswmLevel,cswmTime,cswmStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>微信菜单管理</title>
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
	$("<button class='button' style='float:right;margin:10px;'>同步到微信</button>").insertBefore("#wxmenu_table").click(function(){
		$.getObject("${lz:config("weixin.domain")}weixin/interface-synchro.htm?callback=?",{},function(json){
			showTips(json.message);
		});
	});
	$("<span style='float:right;margin:10px;'>每天最多只能同步到微信100次，想好了你再做</span>").insertBefore("#wxmenu_table");
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
	<span class="caption">${empty title?"微信菜单管理":title}</span>
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
		<table id="wxmenu_table" ref="CsWxMenu" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cswmId || #request.defines['cswmId']!=null">
			 <td <s:if test="#request.defines['cswmId']>0">colspan="${defines["cswmId"]}" ${all?"width":"iwidth"}="${defines["cswmId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="0" ref="cswmId"   title="编号">
			 	<a class="${desc=="cswm_id" ? "desc" : ""}${asc=="cswm_id" ? "asc" : ""}" href="?${desc=="cswm_id" ? "asc=cswm_id" : ""}${(asc=="cswm_id" || desc!="cswm_id" )? "desc=cswm_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","cswmId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cswmName || #request.defines['cswmName']!=null">
			 <td <s:if test="#request.defines['cswmName']>0">colspan="${defines["cswmName"]}" ${all?"width":"iwidth"}="${defines["cswmName"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="1" ref="cswmName" flagKey  title="菜单名称">
			 	<a class="${desc=="cswm_name" ? "desc" : ""}${asc=="cswm_name" ? "asc" : ""}" href="?${desc=="cswm_name" ? "asc=cswm_name" : ""}${(asc=="cswm_name" || desc!="cswm_name" )? "desc=cswm_name" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	菜单名称
			 	</a>
			 	${lz:set("checkeds[]","cswmName")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cswmParent || #request.defines['cswmParent']!=null">
			 <td <s:if test="#request.defines['cswmParent']>0">colspan="${defines["cswmParent"]}" ${all?"width":"iwidth"}="${defines["cswmParent"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="cswmParent"   title="上级菜单">
			 	<a class="${desc=="cswm_parent" ? "desc" : ""}${asc=="cswm_parent" ? "asc" : ""}" href="?${desc=="cswm_parent" ? "asc=cswm_parent" : ""}${(asc=="cswm_parent" || desc!="cswm_parent" )? "desc=cswm_parent" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	上级菜单
			 	</a>
			 	${lz:set("checkeds[]","cswmParent")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cswmType || #request.defines['cswmType']!=null">
			 <td <s:if test="#request.defines['cswmType']>0">colspan="${defines["cswmType"]}" ${all?"width":"iwidth"}="${defines["cswmType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="3" ref="cswmType"   title="菜单类型">
			 	<a class="${desc=="cswm_type" ? "desc" : ""}${asc=="cswm_type" ? "asc" : ""}" href="?${desc=="cswm_type" ? "asc=cswm_type" : ""}${(asc=="cswm_type" || desc!="cswm_type" )? "desc=cswm_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	菜单类型
			 	</a>
			 	${lz:set("checkeds[]","cswmType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cswmSupport || #request.defines['cswmSupport']!=null">
			 <td <s:if test="#request.defines['cswmSupport']>0">colspan="${defines["cswmSupport"]}" ${all?"width":"iwidth"}="${defines["cswmSupport"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="4" ref="cswmSupport"   title="支持终端类型">
			 	<a class="${desc=="cswm_support" ? "desc" : ""}${asc=="cswm_support" ? "asc" : ""}" href="?${desc=="cswm_support" ? "asc=cswm_support" : ""}${(asc=="cswm_support" || desc!="cswm_support" )? "desc=cswm_support" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	支持终端类型
			 	</a>
			 	${lz:set("checkeds[]","cswmSupport")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cswmUrlKey || #request.defines['cswmUrlKey']!=null">
			 <td <s:if test="#request.defines['cswmUrlKey']>0">colspan="${defines["cswmUrlKey"]}" ${all?"width":"iwidth"}="${defines["cswmUrlKey"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="5" ref="cswmUrlKey"   title="地址或消息">
			 	<a class="${desc=="cswm_url_key" ? "desc" : ""}${asc=="cswm_url_key" ? "asc" : ""}" href="?${desc=="cswm_url_key" ? "asc=cswm_url_key" : ""}${(asc=="cswm_url_key" || desc!="cswm_url_key" )? "desc=cswm_url_key" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	地址或消息
			 	</a>
			 	${lz:set("checkeds[]","cswmUrlKey")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cswmLevel || #request.defines['cswmLevel']!=null">
			 <td <s:if test="#request.defines['cswmLevel']>0">colspan="${defines["cswmLevel"]}" ${all?"width":"iwidth"}="${defines["cswmLevel"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="cswmLevel"   title="优先级">
			 	<a class="${desc=="cswm_level" ? "desc" : ""}${asc=="cswm_level" ? "asc" : ""}" href="?${desc=="cswm_level" ? "asc=cswm_level" : ""}${(asc=="cswm_level" || desc!="cswm_level" )? "desc=cswm_level" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	优先级
			 	</a>
			 	${lz:set("checkeds[]","cswmLevel")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cswmTime || #request.defines['cswmTime']!=null">
			 <td <s:if test="#request.defines['cswmTime']>0">colspan="${defines["cswmTime"]}" ${all?"width":"iwidth"}="${defines["cswmTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="7" ref="cswmTime"   title="更改时间">
			 	<a class="${desc=="cswm_time" ? "desc" : ""}${asc=="cswm_time" ? "asc" : ""}" href="?${desc=="cswm_time" ? "asc=cswm_time" : ""}${(asc=="cswm_time" || desc!="cswm_time" )? "desc=cswm_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	更改时间
			 	</a>
			 	${lz:set("checkeds[]","cswmTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cswmStatus || #request.defines['cswmStatus']!=null">
			 <td <s:if test="#request.defines['cswmStatus']>0">colspan="${defines["cswmStatus"]}" ${all?"width":"iwidth"}="${defines["cswmStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="cswmStatus"   title="状态">
			 	<a class="${desc=="cswm_status" ? "desc" : ""}${asc=="cswm_status" ? "asc" : ""}" href="?${desc=="cswm_status" ? "asc=cswm_status" : ""}${(asc=="cswm_status" || desc!="cswm_status" )? "desc=cswm_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","cswmStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="9" class="options" ref="options">操作</td>
			<td rowspan="2" width="135" class="operation" tdid="10" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:AddWxmenu()" style="text-decoration:underline">添加</a>
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
		  <tr id="${item.cswmId}"  tree="${item.cswmParent}">
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cswmId || #request.defines['cswmId']!=null">
				<s:if test="#request.defines['cswmId']>0">
					${lz:set("注释","****cswmId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cswmId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCswmId",lz:indexOf(fieldName,"cswmId")>-1)}
				  		<s:if test="#request.atCswmId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cswmId字段的字串格式化输出****")}
					<td ref="cswmId" class="td ">
						 
						 	${lz:or(item$cswmId[i.count-1],lz:left(item.cswmId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cswmName || #request.defines['cswmName']!=null">
				<s:if test="#request.defines['cswmName']>0">
					${lz:set("注释","****cswmName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cswmName))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCswmName",lz:indexOf(fieldName,"cswmName")>-1)}
				  		<s:if test="#request.atCswmName==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cswmName字段的字串格式化输出****")}
					<td ref="cswmName" class="td  node">
						 
						 	${lz:or(item$cswmName[i.count-1],lz:left(item.cswmName$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cswmParent || #request.defines['cswmParent']!=null">
				<s:if test="#request.defines['cswmParent']>0">
					${lz:set("注释","****cswmParent关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cswmParent))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCswmParent",lz:indexOf(fieldName,"cswmParent")>-1)}
				  		<s:if test="#request.atCswmParent==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cswmParent字段的字串格式化输出****")}
					<td ref="cswmParent" class="td " relate="${item.cswmParent}">
						 
						 	<a <s:if test="#item.cswmParent!=null && #item.cswmParent!=''"> onclick="window.href('${basePath}${proname}/configurator/wx/wxmenu_details.do?key=${item.cswmParent}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cswmParent[i.count-1],lz:left(item.cswmParent$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cswmType || #request.defines['cswmType']!=null">
				<s:if test="#request.defines['cswmType']>0">
					${lz:set("注释","****cswmType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cswmType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCswmType",lz:indexOf(fieldName,"cswmType")>-1)}
				  		<s:if test="#request.atCswmType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cswmType字段的字串格式化输出****")}
					<td ref="cswmType" class="td ">
						 
						 	${lz:or(item$cswmType[i.count-1],lz:left(item.cswmType$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cswmSupport || #request.defines['cswmSupport']!=null">
				<s:if test="#request.defines['cswmSupport']>0">
					${lz:set("注释","****cswmSupport关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cswmSupport))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCswmSupport",lz:indexOf(fieldName,"cswmSupport")>-1)}
				  		<s:if test="#request.atCswmSupport==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cswmSupport字段的字串格式化输出****")}
					<td ref="cswmSupport" class="td ">
						 
						 	${lz:or(item$cswmSupport[i.count-1],lz:left(item.cswmSupport$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cswmUrlKey || #request.defines['cswmUrlKey']!=null">
				<s:if test="#request.defines['cswmUrlKey']>0">
					${lz:set("注释","****cswmUrlKey关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cswmUrlKey))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCswmUrlKey",lz:indexOf(fieldName,"cswmUrlKey")>-1)}
				  		<s:if test="#request.atCswmUrlKey==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cswmUrlKey字段的字串格式化输出****")}
					<td ref="cswmUrlKey" class="td ">
						 
						 	${lz:or(item$cswmUrlKey[i.count-1],lz:left(item.cswmUrlKey$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cswmLevel || #request.defines['cswmLevel']!=null">
				<s:if test="#request.defines['cswmLevel']>0">
					${lz:set("注释","****cswmLevel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cswmLevel))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCswmLevel",lz:indexOf(fieldName,"cswmLevel")>-1)}
				  		<s:if test="#request.atCswmLevel==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cswmLevel字段的字串格式化输出****")}
					<td ref="cswmLevel" class="td ">
						 
						 	${lz:or(item$cswmLevel[i.count-1],lz:left(item.cswmLevel$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cswmTime || #request.defines['cswmTime']!=null">
				<s:if test="#request.defines['cswmTime']>0">
					${lz:set("注释","****cswmTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cswmTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCswmTime",lz:indexOf(fieldName,"cswmTime")>-1)}
				  		<s:if test="#request.atCswmTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cswmTime字段的字串格式化输出****")}
					<td ref="cswmTime" class="td ">
						 
						 	${lz:or(item$cswmTime[i.count-1],lz:left(item.cswmTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cswmStatus || #request.defines['cswmStatus']!=null">
				<s:if test="#request.defines['cswmStatus']>0">
					${lz:set("注释","****cswmStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cswmStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCswmStatus",lz:indexOf(fieldName,"cswmStatus")>-1)}
				  		<s:if test="#request.atCswmStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cswmStatus字段的字串格式化输出****")}
					<td ref="cswmStatus" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.cswmId}','status')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$cswmStatus[i.count-1],lz:left(item.cswmStatus$,100))}
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
					<a href="javascript:HandleSel('${handle}','${item.cswmId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectWxmenu('${item.cswmId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canAdd==true">
				<a href="javascript:AddWxmenu('${item.cswmId}')">添加</a>
				</s:if>
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditWxmenu('${item.cswmId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelWxmenu('${item.cswmId}','${item.cswmName}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsWxmenu('${item.cswmId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.cswmId || #request.defines['cswmId']!=null">
				<s:if test="#request.defines['cswmId']>0">
					${lz:set("注释","****cswmId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCswmId",lz:indexOf(fieldName,"cswmId")>-1)}				  		
				  		<s:if test="#request.atCswmId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cswmId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cswmName || #request.defines['cswmName']!=null">
				<s:if test="#request.defines['cswmName']>0">
					${lz:set("注释","****cswmName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCswmName",lz:indexOf(fieldName,"cswmName")>-1)}				  		
				  		<s:if test="#request.atCswmName==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cswmName[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cswmParent || #request.defines['cswmParent']!=null">
				<s:if test="#request.defines['cswmParent']>0">
					${lz:set("注释","****cswmParent关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCswmParent",lz:indexOf(fieldName,"cswmParent")>-1)}				  		
				  		<s:if test="#request.atCswmParent==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cswmParent[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cswmType || #request.defines['cswmType']!=null">
				<s:if test="#request.defines['cswmType']>0">
					${lz:set("注释","****cswmType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCswmType",lz:indexOf(fieldName,"cswmType")>-1)}				  		
				  		<s:if test="#request.atCswmType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cswmType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cswmSupport || #request.defines['cswmSupport']!=null">
				<s:if test="#request.defines['cswmSupport']>0">
					${lz:set("注释","****cswmSupport关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCswmSupport",lz:indexOf(fieldName,"cswmSupport")>-1)}				  		
				  		<s:if test="#request.atCswmSupport==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cswmSupport[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cswmUrlKey || #request.defines['cswmUrlKey']!=null">
				<s:if test="#request.defines['cswmUrlKey']>0">
					${lz:set("注释","****cswmUrlKey关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCswmUrlKey",lz:indexOf(fieldName,"cswmUrlKey")>-1)}				  		
				  		<s:if test="#request.atCswmUrlKey==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cswmUrlKey[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cswmLevel || #request.defines['cswmLevel']!=null">
				<s:if test="#request.defines['cswmLevel']>0">
					${lz:set("注释","****cswmLevel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCswmLevel",lz:indexOf(fieldName,"cswmLevel")>-1)}				  		
				  		<s:if test="#request.atCswmLevel==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cswmLevel[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cswmTime || #request.defines['cswmTime']!=null">
				<s:if test="#request.defines['cswmTime']>0">
					${lz:set("注释","****cswmTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCswmTime",lz:indexOf(fieldName,"cswmTime")>-1)}				  		
				  		<s:if test="#request.atCswmTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cswmTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cswmStatus || #request.defines['cswmStatus']!=null">
				<s:if test="#request.defines['cswmStatus']>0">
					${lz:set("注释","****cswmStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCswmStatus",lz:indexOf(fieldName,"cswmStatus")>-1)}				  		
				  		<s:if test="#request.atCswmStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cswmStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	$("#wxmenu_table").treegrid({expand:true});
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
	* 添加微信菜单
	**/
	function AddWxmenu(parent){		
		var url = "${basePath}${namespace}wxmenu_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加微信菜单'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择微信菜单
	**/
	function SelectWxmenu(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改微信菜单
	**/
	function EditWxmenu(id){
		var url = "${basePath}${namespace}wxmenu_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改微信菜单'}":lz:json(ctrl)}};
		href(url,params);
	}
	
	/**
	* 修改微信菜单
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}wxmenu_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新微信菜单",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["status"]={};
	fieldNames["status"]["cswmStatus"]=true;
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改微信菜单任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}wxmenu_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新微信菜单",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}wxmenu_edit.do";
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
			var url = "${basePath}${namespace}wxmenu_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除微信菜单
	**/
	function DelWxmenu(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除微信菜单["+flag+"]吗？</span>",
				title:"删除微信菜单",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}wxmenu_del.do";
					var params = {entrypoint:"${entrypoint}",id:id};
					$.submit(url,params);
				}
		 });	
	}
</s:if>
	/**
	* 显示微信菜单详情
	**/
	function DetailsWxmenu(id)
	{
		var url = "${basePath}${namespace}wxmenu_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"微信菜单详情",editable:false,visible:true}};
		href(url,params);
	}
</script>


<!------------------------LAZY3Q_JSP_BOTTOM------------------------>
<!------------------------LAZY3Q_JSP_BOTTOM------------------------>


</body>
</html>