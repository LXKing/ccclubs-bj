<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/fee/packinfo.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csPackInfo.cspiId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"cspiId,cspiPack,cspiGoods,cspiOutlets,cspiModel,cspiType,cspiScript,cspiAddTime",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"cspiId,cspiName,cspiPack,cspiGoods,cspiOutlets,cspiModel,cspiType,cspiValue,cspiScript,cspiAddTime",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"cspiId,cspiPack,cspiGoods,cspiOutlets,cspiModel,cspiType,cspiAddTime",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"cspiId,cspiName,cspiPack,cspiGoods,cspiOutlets,cspiModel,cspiType,cspiValue,cspiAddTime",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>套餐内容管理</title>
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
	<span class="caption">${empty title?"套餐内容管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsPackInfo" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="packinfo.query.jsp"%>

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
			<button type="button" class="add" onclick="AddPackinfo();">
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
			<dl ref="com.ccclubs.model.CsPackInfo" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.CsPackInfo" class="batch dropdowan">
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
		<table id="packinfo_table" ref="CsPackInfo" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cspiId || #request.defines['cspiId']!=null">
			 <td <s:if test="#request.defines['cspiId']>0">colspan="${defines["cspiId"]}" ${all?"width":"iwidth"}="${defines["cspiId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="cspiId"   title="编号">
			 	<a class="${desc=="cspi_id" ? "desc" : ""}${asc=="cspi_id" ? "asc" : ""}" href="?${desc=="cspi_id" ? "asc=cspi_id" : ""}${(asc=="cspi_id" || desc!="cspi_id" )? "desc=cspi_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","cspiId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cspiName || #request.defines['cspiName']!=null">
			 <td <s:if test="#request.defines['cspiName']>0">colspan="${defines["cspiName"]}" ${all?"width":"iwidth"}="${defines["cspiName"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="2" ref="cspiName" flagKey  title="名称">
			 	<a class="${desc=="cspi_name" ? "desc" : ""}${asc=="cspi_name" ? "asc" : ""}" href="?${desc=="cspi_name" ? "asc=cspi_name" : ""}${(asc=="cspi_name" || desc!="cspi_name" )? "desc=cspi_name" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	名称
			 	</a>
			 	${lz:set("checkeds[]","cspiName")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cspiPack || #request.defines['cspiPack']!=null">
			 <td <s:if test="#request.defines['cspiPack']>0">colspan="${defines["cspiPack"]}" ${all?"width":"iwidth"}="${defines["cspiPack"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="3" ref="cspiPack"   title="所属套餐">
			 	<a class="${desc=="cspi_pack" ? "desc" : ""}${asc=="cspi_pack" ? "asc" : ""}" href="?${desc=="cspi_pack" ? "asc=cspi_pack" : ""}${(asc=="cspi_pack" || desc!="cspi_pack" )? "desc=cspi_pack" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属套餐
			 	</a>
			 	${lz:set("checkeds[]","cspiPack")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cspiGoods || #request.defines['cspiGoods']!=null">
			 <td <s:if test="#request.defines['cspiGoods']>0">colspan="${defines["cspiGoods"]}" ${all?"width":"iwidth"}="${defines["cspiGoods"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="cspiGoods"   title="所属商品">
			 	<a class="${desc=="cspi_goods" ? "desc" : ""}${asc=="cspi_goods" ? "asc" : ""}" href="?${desc=="cspi_goods" ? "asc=cspi_goods" : ""}${(asc=="cspi_goods" || desc!="cspi_goods" )? "desc=cspi_goods" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属商品
			 	</a>
			 	${lz:set("checkeds[]","cspiGoods")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cspiOutlets || #request.defines['cspiOutlets']!=null">
			 <td <s:if test="#request.defines['cspiOutlets']>0">colspan="${defines["cspiOutlets"]}" ${all?"width":"iwidth"}="${defines["cspiOutlets"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="cspiOutlets"   title="所属网点">
			 	<a class="${desc=="cspi_outlets" ? "desc" : ""}${asc=="cspi_outlets" ? "asc" : ""}" href="?${desc=="cspi_outlets" ? "asc=cspi_outlets" : ""}${(asc=="cspi_outlets" || desc!="cspi_outlets" )? "desc=cspi_outlets" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属网点
			 	</a>
			 	${lz:set("checkeds[]","cspiOutlets")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cspiModel || #request.defines['cspiModel']!=null">
			 <td <s:if test="#request.defines['cspiModel']>0">colspan="${defines["cspiModel"]}" ${all?"width":"iwidth"}="${defines["cspiModel"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="6" ref="cspiModel"   title="所属车型">
			 	<a class="${desc=="cspi_model" ? "desc" : ""}${asc=="cspi_model" ? "asc" : ""}" href="?${desc=="cspi_model" ? "asc=cspi_model" : ""}${(asc=="cspi_model" || desc!="cspi_model" )? "desc=cspi_model" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属车型
			 	</a>
			 	${lz:set("checkeds[]","cspiModel")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cspiType || #request.defines['cspiType']!=null">
			 <td <s:if test="#request.defines['cspiType']>0">colspan="${defines["cspiType"]}" ${all?"width":"iwidth"}="${defines["cspiType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="7" ref="cspiType"   title="类型">
			 	<a class="${desc=="cspi_type" ? "desc" : ""}${asc=="cspi_type" ? "asc" : ""}" href="?${desc=="cspi_type" ? "asc=cspi_type" : ""}${(asc=="cspi_type" || desc!="cspi_type" )? "desc=cspi_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	类型
			 	</a>
			 	${lz:set("checkeds[]","cspiType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cspiValue || #request.defines['cspiValue']!=null">
			 <td <s:if test="#request.defines['cspiValue']>0">colspan="${defines["cspiValue"]}" ${all?"width":"iwidth"}="${defines["cspiValue"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="cspiValue"   title="值">
			 	<a class="${desc=="cspi_value" ? "desc" : ""}${asc=="cspi_value" ? "asc" : ""}" href="?${desc=="cspi_value" ? "asc=cspi_value" : ""}${(asc=="cspi_value" || desc!="cspi_value" )? "desc=cspi_value" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	值
			 	</a>
			 	${lz:set("checkeds[]","cspiValue")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cspiScript || #request.defines['cspiScript']!=null">
			 <td <s:if test="#request.defines['cspiScript']>0">colspan="${defines["cspiScript"]}" ${all?"width":"iwidth"}="${defines["cspiScript"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="9" ref="cspiScript"   title="函数脚本">
			 	<a class="${desc=="cspi_script" ? "desc" : ""}${asc=="cspi_script" ? "asc" : ""}" href="?${desc=="cspi_script" ? "asc=cspi_script" : ""}${(asc=="cspi_script" || desc!="cspi_script" )? "desc=cspi_script" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	函数脚本
			 	</a>
			 	${lz:set("checkeds[]","cspiScript")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cspiAddTime || #request.defines['cspiAddTime']!=null">
			 <td <s:if test="#request.defines['cspiAddTime']>0">colspan="${defines["cspiAddTime"]}" ${all?"width":"iwidth"}="${defines["cspiAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="10" ref="cspiAddTime"   title="添加时间">
			 	<a class="${desc=="cspi_add_time" ? "desc" : ""}${asc=="cspi_add_time" ? "asc" : ""}" href="?${desc=="cspi_add_time" ? "asc=cspi_add_time" : ""}${(asc=="cspi_add_time" || desc!="cspi_add_time" )? "desc=cspi_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","cspiAddTime")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="11" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="12" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:AddPackinfo()" style="text-decoration:underline">添加</a>
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
		  <tr id="${item.cspiId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.cspiId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cspiId || #request.defines['cspiId']!=null">
				<s:if test="#request.defines['cspiId']>0">
					${lz:set("注释","****cspiId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cspiId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCspiId",lz:indexOf(fieldName,"cspiId")>-1)}
				  		<s:if test="#request.atCspiId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cspiId字段的字串格式化输出****")}
					<td ref="cspiId" class="td ">
						 
						 	${lz:or(item$cspiId[i.count-1],lz:left(item.cspiId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cspiName || #request.defines['cspiName']!=null">
				<s:if test="#request.defines['cspiName']>0">
					${lz:set("注释","****cspiName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cspiName))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCspiName",lz:indexOf(fieldName,"cspiName")>-1)}
				  		<s:if test="#request.atCspiName==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cspiName字段的字串格式化输出****")}
					<td ref="cspiName" class="td  node">
						 
						 	${lz:or(item$cspiName[i.count-1],lz:left(item.cspiName$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cspiPack || #request.defines['cspiPack']!=null">
				<s:if test="#request.defines['cspiPack']>0">
					${lz:set("注释","****cspiPack关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cspiPack))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCspiPack",lz:indexOf(fieldName,"cspiPack")>-1)}
				  		<s:if test="#request.atCspiPack==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cspiPack字段的字串格式化输出****")}
					<td ref="cspiPack" class="td " relate="${item.cspiPack}">
						 
						 	<a <s:if test="#item.cspiPack!=null && #item.cspiPack!=''"> onclick="window.href('${basePath}${proname}/fee/pack_details.do?key=${item.cspiPack}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cspiPack[i.count-1],lz:left(item.cspiPack$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cspiGoods || #request.defines['cspiGoods']!=null">
				<s:if test="#request.defines['cspiGoods']>0">
					${lz:set("注释","****cspiGoods关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cspiGoods))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCspiGoods",lz:indexOf(fieldName,"cspiGoods")>-1)}
				  		<s:if test="#request.atCspiGoods==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cspiGoods字段的字串格式化输出****")}
					<td ref="cspiGoods" class="td " relate="${item.cspiGoods}">
						 
						 	<a <s:if test="#item.cspiGoods!=null && #item.cspiGoods!=''"> onclick="window.href('${basePath}${proname}/fee/goods_details.do?key=${item.cspiGoods}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cspiGoods[i.count-1],lz:left(item.cspiGoods$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cspiOutlets || #request.defines['cspiOutlets']!=null">
				<s:if test="#request.defines['cspiOutlets']>0">
					${lz:set("注释","****cspiOutlets关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cspiOutlets))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCspiOutlets",lz:indexOf(fieldName,"cspiOutlets")>-1)}
				  		<s:if test="#request.atCspiOutlets==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cspiOutlets字段的字串格式化输出****")}
					<td ref="cspiOutlets" class="td " relate="${item.cspiOutlets}">
						 
						 	<a <s:if test="#item.cspiOutlets!=null && #item.cspiOutlets!=''"> onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${item.cspiOutlets}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cspiOutlets[i.count-1],lz:left(item.cspiOutlets$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cspiModel || #request.defines['cspiModel']!=null">
				<s:if test="#request.defines['cspiModel']>0">
					${lz:set("注释","****cspiModel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cspiModel))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCspiModel",lz:indexOf(fieldName,"cspiModel")>-1)}
				  		<s:if test="#request.atCspiModel==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cspiModel字段的字串格式化输出****")}
					<td ref="cspiModel" class="td " relate="${item.cspiModel}">
						 
						 	${lz:or(item$cspiModel[i.count-1],lz:left(item.cspiModel$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cspiType || #request.defines['cspiType']!=null">
				<s:if test="#request.defines['cspiType']>0">
					${lz:set("注释","****cspiType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cspiType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCspiType",lz:indexOf(fieldName,"cspiType")>-1)}
				  		<s:if test="#request.atCspiType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cspiType字段的字串格式化输出****")}
					<td ref="cspiType" class="td ">
						 
						 	${lz:or(item$cspiType[i.count-1],lz:left(item.cspiType$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cspiValue || #request.defines['cspiValue']!=null">
				<s:if test="#request.defines['cspiValue']>0">
					${lz:set("注释","****cspiValue关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cspiValue))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCspiValue",lz:indexOf(fieldName,"cspiValue")>-1)}
				  		<s:if test="#request.atCspiValue==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cspiValue字段的字串格式化输出****")}
					<td ref="cspiValue" class="td ">
						 
						 	${lz:or(item$cspiValue[i.count-1],lz:left(item.cspiValue$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cspiScript || #request.defines['cspiScript']!=null">
				<s:if test="#request.defines['cspiScript']>0">
					${lz:set("注释","****cspiScript关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cspiScript))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCspiScript",lz:indexOf(fieldName,"cspiScript")>-1)}
				  		<s:if test="#request.atCspiScript==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cspiScript字段的字串格式化输出****")}
					<td ref="cspiScript" class="td ">
						 
						 	${lz:or(item$cspiScript[i.count-1],lz:left(item.cspiScript$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cspiAddTime || #request.defines['cspiAddTime']!=null">
				<s:if test="#request.defines['cspiAddTime']>0">
					${lz:set("注释","****cspiAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cspiAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCspiAddTime",lz:indexOf(fieldName,"cspiAddTime")>-1)}
				  		<s:if test="#request.atCspiAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cspiAddTime字段的字串格式化输出****")}
					<td ref="cspiAddTime" class="td ">
						 
						 	${lz:or(item$cspiAddTime[i.count-1],lz:left(item.cspiAddTime$,100))}
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
					<a href="javascript:HandleSel('${handle}','${item.cspiId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectPackinfo('${item.cspiId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditPackinfo('${item.cspiId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelPackinfo('${item.cspiId}','${item.cspiName}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsPackinfo('${item.cspiId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.cspiId || #request.defines['cspiId']!=null">
				<s:if test="#request.defines['cspiId']>0">
					${lz:set("注释","****cspiId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCspiId",lz:indexOf(fieldName,"cspiId")>-1)}				  		
				  		<s:if test="#request.atCspiId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cspiId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cspiName || #request.defines['cspiName']!=null">
				<s:if test="#request.defines['cspiName']>0">
					${lz:set("注释","****cspiName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCspiName",lz:indexOf(fieldName,"cspiName")>-1)}				  		
				  		<s:if test="#request.atCspiName==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cspiName[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cspiPack || #request.defines['cspiPack']!=null">
				<s:if test="#request.defines['cspiPack']>0">
					${lz:set("注释","****cspiPack关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCspiPack",lz:indexOf(fieldName,"cspiPack")>-1)}				  		
				  		<s:if test="#request.atCspiPack==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cspiPack[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cspiGoods || #request.defines['cspiGoods']!=null">
				<s:if test="#request.defines['cspiGoods']>0">
					${lz:set("注释","****cspiGoods关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCspiGoods",lz:indexOf(fieldName,"cspiGoods")>-1)}				  		
				  		<s:if test="#request.atCspiGoods==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cspiGoods[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cspiOutlets || #request.defines['cspiOutlets']!=null">
				<s:if test="#request.defines['cspiOutlets']>0">
					${lz:set("注释","****cspiOutlets关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCspiOutlets",lz:indexOf(fieldName,"cspiOutlets")>-1)}				  		
				  		<s:if test="#request.atCspiOutlets==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cspiOutlets[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cspiModel || #request.defines['cspiModel']!=null">
				<s:if test="#request.defines['cspiModel']>0">
					${lz:set("注释","****cspiModel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCspiModel",lz:indexOf(fieldName,"cspiModel")>-1)}				  		
				  		<s:if test="#request.atCspiModel==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cspiModel[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cspiType || #request.defines['cspiType']!=null">
				<s:if test="#request.defines['cspiType']>0">
					${lz:set("注释","****cspiType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCspiType",lz:indexOf(fieldName,"cspiType")>-1)}				  		
				  		<s:if test="#request.atCspiType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cspiType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cspiValue || #request.defines['cspiValue']!=null">
				<s:if test="#request.defines['cspiValue']>0">
					${lz:set("注释","****cspiValue关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCspiValue",lz:indexOf(fieldName,"cspiValue")>-1)}				  		
				  		<s:if test="#request.atCspiValue==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cspiValue[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cspiScript || #request.defines['cspiScript']!=null">
				<s:if test="#request.defines['cspiScript']>0">
					${lz:set("注释","****cspiScript关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCspiScript",lz:indexOf(fieldName,"cspiScript")>-1)}				  		
				  		<s:if test="#request.atCspiScript==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cspiScript[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cspiAddTime || #request.defines['cspiAddTime']!=null">
				<s:if test="#request.defines['cspiAddTime']>0">
					${lz:set("注释","****cspiAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCspiAddTime",lz:indexOf(fieldName,"cspiAddTime")>-1)}				  		
				  		<s:if test="#request.atCspiAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cspiAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 添加套餐内容
	**/
	function AddPackinfo(parent){		
		var url = "${basePath}${namespace}packinfo_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加套餐内容'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择套餐内容
	**/
	function SelectPackinfo(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改套餐内容
	**/
	function EditPackinfo(id){
		var url = "${basePath}${namespace}packinfo_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改套餐内容'}":lz:json(ctrl)}};
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
		EditPackinfo(checker.val());
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
				var url = "${basePath}${namespace}packinfo_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新套餐内容",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}packinfo_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新套餐内容",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改套餐内容
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}packinfo_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新套餐内容",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改套餐内容任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}packinfo_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新套餐内容",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}packinfo_edit.do";
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
			var url = "${basePath}${namespace}packinfo_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除套餐内容
	**/
	function DelPackinfo(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除套餐内容["+flag+"]吗？</span>",
				title:"删除套餐内容",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}packinfo_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选套餐内容吗？</span>",
				title:"批量删除套餐内容",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}packinfo_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示套餐内容详情
	**/
	function DetailsPackinfo(id)
	{
		var url = "${basePath}${namespace}packinfo_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"套餐内容详情",editable:false,visible:true}};
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
		DetailsPackinfo(checker.val());
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