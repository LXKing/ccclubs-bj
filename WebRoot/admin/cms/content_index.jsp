<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/cms/content.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csContent.cscId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"cscId",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"cscId,cscTitle,cscParent,cscFlag,cscRecommend,cscThum,cscKeywords,cscImages,cscFile,cscAddTime,cscUpdateTime,cscViewS,cscReplyS,cscCollectS,cscPraiseS,cscStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"cscId",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"cscId,cscTitle,cscParent,cscFlag,cscRecommend,cscAddTime,cscStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>静态内容管理</title>
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
	<span class="caption">${empty title?"静态内容管理":title}</span>
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
		<table id="content_table" ref="CsContent" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscId || #request.defines['cscId']!=null">
			 <td <s:if test="#request.defines['cscId']>0">colspan="${defines["cscId"]}" ${all?"width":"iwidth"}="${defines["cscId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="0" ref="cscId"   title="编号">
			 	<a class="${desc=="csc_id" ? "desc" : ""}${asc=="csc_id" ? "asc" : ""}" href="?${desc=="csc_id" ? "asc=csc_id" : ""}${(asc=="csc_id" || desc!="csc_id" )? "desc=csc_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","cscId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscTitle || #request.defines['cscTitle']!=null">
			 <td <s:if test="#request.defines['cscTitle']>0">colspan="${defines["cscTitle"]}" ${all?"width":"iwidth"}="${defines["cscTitle"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="1" ref="cscTitle" flagKey  title="标题">
			 	<a class="${desc=="csc_title" ? "desc" : ""}${asc=="csc_title" ? "asc" : ""}" href="?${desc=="csc_title" ? "asc=csc_title" : ""}${(asc=="csc_title" || desc!="csc_title" )? "desc=csc_title" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	标题
			 	</a>
			 	${lz:set("checkeds[]","cscTitle")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscParent || #request.defines['cscParent']!=null">
			 <td <s:if test="#request.defines['cscParent']>0">colspan="${defines["cscParent"]}" ${all?"width":"iwidth"}="${defines["cscParent"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="cscParent"   title="上级节点">
			 	<a class="${desc=="csc_parent" ? "desc" : ""}${asc=="csc_parent" ? "asc" : ""}" href="?${desc=="csc_parent" ? "asc=csc_parent" : ""}${(asc=="csc_parent" || desc!="csc_parent" )? "desc=csc_parent" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	上级节点
			 	</a>
			 	${lz:set("checkeds[]","cscParent")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscFlag || #request.defines['cscFlag']!=null">
			 <td <s:if test="#request.defines['cscFlag']>0">colspan="${defines["cscFlag"]}" ${all?"width":"iwidth"}="${defines["cscFlag"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="3" ref="cscFlag"   title="系统标识">
			 	<a class="${desc=="csc_flag" ? "desc" : ""}${asc=="csc_flag" ? "asc" : ""}" href="?${desc=="csc_flag" ? "asc=csc_flag" : ""}${(asc=="csc_flag" || desc!="csc_flag" )? "desc=csc_flag" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	系统标识
			 	</a>
			 	${lz:set("checkeds[]","cscFlag")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscRecommend || #request.defines['cscRecommend']!=null">
			 <td <s:if test="#request.defines['cscRecommend']>0">colspan="${defines["cscRecommend"]}" ${all?"width":"iwidth"}="${defines["cscRecommend"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="cscRecommend"   title="优先级">
			 	<a class="${desc=="csc_order" ? "desc" : ""}${asc=="csc_order" ? "asc" : ""}" href="?${desc=="csc_order" ? "asc=csc_order" : ""}${(asc=="csc_order" || desc!="csc_order" )? "desc=csc_order" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	优先级
			 	</a>
			 	${lz:set("checkeds[]","cscRecommend")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscThum || #request.defines['cscThum']!=null">
			 <td <s:if test="#request.defines['cscThum']>0">colspan="${defines["cscThum"]}" ${all?"width":"iwidth"}="${defines["cscThum"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="5" ref="cscThum"   title="略缩图">
			 	<a class="${desc=="csc_thum" ? "desc" : ""}${asc=="csc_thum" ? "asc" : ""}" href="?${desc=="csc_thum" ? "asc=csc_thum" : ""}${(asc=="csc_thum" || desc!="csc_thum" )? "desc=csc_thum" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	略缩图
			 	</a>
			 	${lz:set("checkeds[]","cscThum")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscKeywords || #request.defines['cscKeywords']!=null">
			 <td <s:if test="#request.defines['cscKeywords']>0">colspan="${defines["cscKeywords"]}" ${all?"width":"iwidth"}="${defines["cscKeywords"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="6" ref="cscKeywords"   title="关键字">
			 	<a class="${desc=="csc_keywords" ? "desc" : ""}${asc=="csc_keywords" ? "asc" : ""}" href="?${desc=="csc_keywords" ? "asc=csc_keywords" : ""}${(asc=="csc_keywords" || desc!="csc_keywords" )? "desc=csc_keywords" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	关键字
			 	</a>
			 	${lz:set("checkeds[]","cscKeywords")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscImages || #request.defines['cscImages']!=null">
			 <td <s:if test="#request.defines['cscImages']>0">colspan="${defines["cscImages"]}" ${all?"width":"iwidth"}="${defines["cscImages"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="7" ref="cscImages"   title="预览图">
			 	<a class="${desc=="csc_images" ? "desc" : ""}${asc=="csc_images" ? "asc" : ""}" href="?${desc=="csc_images" ? "asc=csc_images" : ""}${(asc=="csc_images" || desc!="csc_images" )? "desc=csc_images" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	预览图
			 	</a>
			 	${lz:set("checkeds[]","cscImages")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscFile || #request.defines['cscFile']!=null">
			 <td <s:if test="#request.defines['cscFile']>0">colspan="${defines["cscFile"]}" ${all?"width":"iwidth"}="${defines["cscFile"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="8" ref="cscFile"   title="文件">
			 	<a class="${desc=="csc_file" ? "desc" : ""}${asc=="csc_file" ? "asc" : ""}" href="?${desc=="csc_file" ? "asc=csc_file" : ""}${(asc=="csc_file" || desc!="csc_file" )? "desc=csc_file" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	文件
			 	</a>
			 	${lz:set("checkeds[]","cscFile")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscAddTime || #request.defines['cscAddTime']!=null">
			 <td <s:if test="#request.defines['cscAddTime']>0">colspan="${defines["cscAddTime"]}" ${all?"width":"iwidth"}="${defines["cscAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="9" ref="cscAddTime"   title="添加时间">
			 	<a class="${desc=="csc_add_time" ? "desc" : ""}${asc=="csc_add_time" ? "asc" : ""}" href="?${desc=="csc_add_time" ? "asc=csc_add_time" : ""}${(asc=="csc_add_time" || desc!="csc_add_time" )? "desc=csc_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","cscAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscUpdateTime || #request.defines['cscUpdateTime']!=null">
			 <td <s:if test="#request.defines['cscUpdateTime']>0">colspan="${defines["cscUpdateTime"]}" ${all?"width":"iwidth"}="${defines["cscUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="10" ref="cscUpdateTime"   title="更新时间">
			 	<a class="${desc=="csc_update_time" ? "desc" : ""}${asc=="csc_update_time" ? "asc" : ""}" href="?${desc=="csc_update_time" ? "asc=csc_update_time" : ""}${(asc=="csc_update_time" || desc!="csc_update_time" )? "desc=csc_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	更新时间
			 	</a>
			 	${lz:set("checkeds[]","cscUpdateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscViewS || #request.defines['cscViewS']!=null">
			 <td <s:if test="#request.defines['cscViewS']>0">colspan="${defines["cscViewS"]}" ${all?"width":"iwidth"}="${defines["cscViewS"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="11" ref="cscViewS"   title="浏览次数">
			 	<a class="${desc=="csc_view_s" ? "desc" : ""}${asc=="csc_view_s" ? "asc" : ""}" href="?${desc=="csc_view_s" ? "asc=csc_view_s" : ""}${(asc=="csc_view_s" || desc!="csc_view_s" )? "desc=csc_view_s" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	浏览次数
			 	</a>
			 	${lz:set("checkeds[]","cscViewS")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscReplyS || #request.defines['cscReplyS']!=null">
			 <td <s:if test="#request.defines['cscReplyS']>0">colspan="${defines["cscReplyS"]}" ${all?"width":"iwidth"}="${defines["cscReplyS"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="12" ref="cscReplyS"   title="评论次数">
			 	<a class="${desc=="csc_reply_s" ? "desc" : ""}${asc=="csc_reply_s" ? "asc" : ""}" href="?${desc=="csc_reply_s" ? "asc=csc_reply_s" : ""}${(asc=="csc_reply_s" || desc!="csc_reply_s" )? "desc=csc_reply_s" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	评论次数
			 	</a>
			 	${lz:set("checkeds[]","cscReplyS")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscCollectS || #request.defines['cscCollectS']!=null">
			 <td <s:if test="#request.defines['cscCollectS']>0">colspan="${defines["cscCollectS"]}" ${all?"width":"iwidth"}="${defines["cscCollectS"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="13" ref="cscCollectS"   title="收藏次数">
			 	<a class="${desc=="csc_collect_s" ? "desc" : ""}${asc=="csc_collect_s" ? "asc" : ""}" href="?${desc=="csc_collect_s" ? "asc=csc_collect_s" : ""}${(asc=="csc_collect_s" || desc!="csc_collect_s" )? "desc=csc_collect_s" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	收藏次数
			 	</a>
			 	${lz:set("checkeds[]","cscCollectS")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscPraiseS || #request.defines['cscPraiseS']!=null">
			 <td <s:if test="#request.defines['cscPraiseS']>0">colspan="${defines["cscPraiseS"]}" ${all?"width":"iwidth"}="${defines["cscPraiseS"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="14" ref="cscPraiseS"   title="好评数">
			 	<a class="${desc=="csc_praise_s" ? "desc" : ""}${asc=="csc_praise_s" ? "asc" : ""}" href="?${desc=="csc_praise_s" ? "asc=csc_praise_s" : ""}${(asc=="csc_praise_s" || desc!="csc_praise_s" )? "desc=csc_praise_s" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	好评数
			 	</a>
			 	${lz:set("checkeds[]","cscPraiseS")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscStatus || #request.defines['cscStatus']!=null">
			 <td <s:if test="#request.defines['cscStatus']>0">colspan="${defines["cscStatus"]}" ${all?"width":"iwidth"}="${defines["cscStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="15" ref="cscStatus"   title="状态">
			 	<a class="${desc=="csc_status" ? "desc" : ""}${asc=="csc_status" ? "asc" : ""}" href="?${desc=="csc_status" ? "asc=csc_status" : ""}${(asc=="csc_status" || desc!="csc_status" )? "desc=csc_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","cscStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="16" class="options" ref="options">操作</td>
			<td rowspan="2" width="135" class="operation" tdid="17" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:AddContent()" style="text-decoration:underline">添加</a>
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
		  <tr id="${item.cscId}"  tree="${item.cscParent}">
		  
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
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscTitle || #request.defines['cscTitle']!=null">
				<s:if test="#request.defines['cscTitle']>0">
					${lz:set("注释","****cscTitle关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscTitle))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscTitle",lz:indexOf(fieldName,"cscTitle")>-1)}
				  		<s:if test="#request.atCscTitle==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscTitle字段的字串格式化输出****")}
					<td ref="cscTitle" class="td  node">
						 
						 	${lz:or(item$cscTitle[i.count-1],lz:left(item.cscTitle$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscParent || #request.defines['cscParent']!=null">
				<s:if test="#request.defines['cscParent']>0">
					${lz:set("注释","****cscParent关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscParent))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscParent",lz:indexOf(fieldName,"cscParent")>-1)}
				  		<s:if test="#request.atCscParent==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscParent字段的字串格式化输出****")}
					<td ref="cscParent" class="td ">
						 
						 	${lz:or(item$cscParent[i.count-1],lz:left(item.cscParent$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscFlag || #request.defines['cscFlag']!=null">
				<s:if test="#request.defines['cscFlag']>0">
					${lz:set("注释","****cscFlag关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscFlag))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscFlag",lz:indexOf(fieldName,"cscFlag")>-1)}
				  		<s:if test="#request.atCscFlag==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscFlag字段的字串格式化输出****")}
					<td ref="cscFlag" class="td ">
						 
						 	${lz:or(item$cscFlag[i.count-1],lz:left(item.cscFlag$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscRecommend || #request.defines['cscRecommend']!=null">
				<s:if test="#request.defines['cscRecommend']>0">
					${lz:set("注释","****cscRecommend关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscRecommend))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscRecommend",lz:indexOf(fieldName,"cscRecommend")>-1)}
				  		<s:if test="#request.atCscRecommend==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscRecommend字段的字串格式化输出****")}
					<td ref="cscRecommend" class="td ">
						 
						 	${lz:or(item$cscRecommend[i.count-1],lz:left(item.cscRecommend$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscThum || #request.defines['cscThum']!=null">
				<s:if test="#request.defines['cscThum']>0">
					${lz:set("注释","****cscThum关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscThum))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscThum",lz:indexOf(fieldName,"cscThum")>-1)}
				  		<s:if test="#request.atCscThum==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscThum字段的字串格式化输出****")}
					<td ref="cscThum" class="td ">
						 
						 	${lz:or(item$cscThum[i.count-1],lz:left(item.cscThum$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscKeywords || #request.defines['cscKeywords']!=null">
				<s:if test="#request.defines['cscKeywords']>0">
					${lz:set("注释","****cscKeywords关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscKeywords))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscKeywords",lz:indexOf(fieldName,"cscKeywords")>-1)}
				  		<s:if test="#request.atCscKeywords==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscKeywords字段的字串格式化输出****")}
					<td ref="cscKeywords" class="td ">
						 
						 	${lz:or(item$cscKeywords[i.count-1],lz:left(item.cscKeywords$,100))}
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
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscFile || #request.defines['cscFile']!=null">
				<s:if test="#request.defines['cscFile']>0">
					${lz:set("注释","****cscFile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscFile))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscFile",lz:indexOf(fieldName,"cscFile")>-1)}
				  		<s:if test="#request.atCscFile==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscFile字段的字串格式化输出****")}
					<td ref="cscFile" class="td ">
						 
						 	${lz:or(item$cscFile[i.count-1],lz:left(item.cscFile$,100))}
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
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscViewS || #request.defines['cscViewS']!=null">
				<s:if test="#request.defines['cscViewS']>0">
					${lz:set("注释","****cscViewS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscViewS))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscViewS",lz:indexOf(fieldName,"cscViewS")>-1)}
				  		<s:if test="#request.atCscViewS==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscViewS字段的字串格式化输出****")}
					<td ref="cscViewS" class="td ">
						 
						 	${lz:or(item$cscViewS[i.count-1],lz:left(item.cscViewS$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscReplyS || #request.defines['cscReplyS']!=null">
				<s:if test="#request.defines['cscReplyS']>0">
					${lz:set("注释","****cscReplyS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscReplyS))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscReplyS",lz:indexOf(fieldName,"cscReplyS")>-1)}
				  		<s:if test="#request.atCscReplyS==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscReplyS字段的字串格式化输出****")}
					<td ref="cscReplyS" class="td ">
						 
						 	${lz:or(item$cscReplyS[i.count-1],lz:left(item.cscReplyS$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscCollectS || #request.defines['cscCollectS']!=null">
				<s:if test="#request.defines['cscCollectS']>0">
					${lz:set("注释","****cscCollectS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscCollectS))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscCollectS",lz:indexOf(fieldName,"cscCollectS")>-1)}
				  		<s:if test="#request.atCscCollectS==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscCollectS字段的字串格式化输出****")}
					<td ref="cscCollectS" class="td ">
						 
						 	${lz:or(item$cscCollectS[i.count-1],lz:left(item.cscCollectS$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscPraiseS || #request.defines['cscPraiseS']!=null">
				<s:if test="#request.defines['cscPraiseS']>0">
					${lz:set("注释","****cscPraiseS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscPraiseS))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscPraiseS",lz:indexOf(fieldName,"cscPraiseS")>-1)}
				  		<s:if test="#request.atCscPraiseS==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscPraiseS字段的字串格式化输出****")}
					<td ref="cscPraiseS" class="td ">
						 
						 	${lz:or(item$cscPraiseS[i.count-1],lz:left(item.cscPraiseS$,100))}
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
				<a href="javascript:SelectContent('${item.cscId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canAdd==true">
				<a href="javascript:AddContent('${item.cscId}')">添加</a>
				</s:if>
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditContent('${item.cscId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelContent('${item.cscId}','${item.cscTitle}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsContent('${item.cscId}')">详情</a></s:if>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscTitle || #request.defines['cscTitle']!=null">
				<s:if test="#request.defines['cscTitle']>0">
					${lz:set("注释","****cscTitle关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscTitle",lz:indexOf(fieldName,"cscTitle")>-1)}				  		
				  		<s:if test="#request.atCscTitle==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscTitle[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscParent || #request.defines['cscParent']!=null">
				<s:if test="#request.defines['cscParent']>0">
					${lz:set("注释","****cscParent关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscParent",lz:indexOf(fieldName,"cscParent")>-1)}				  		
				  		<s:if test="#request.atCscParent==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscParent[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscFlag || #request.defines['cscFlag']!=null">
				<s:if test="#request.defines['cscFlag']>0">
					${lz:set("注释","****cscFlag关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscFlag",lz:indexOf(fieldName,"cscFlag")>-1)}				  		
				  		<s:if test="#request.atCscFlag==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscFlag[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscRecommend || #request.defines['cscRecommend']!=null">
				<s:if test="#request.defines['cscRecommend']>0">
					${lz:set("注释","****cscRecommend关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscRecommend",lz:indexOf(fieldName,"cscRecommend")>-1)}				  		
				  		<s:if test="#request.atCscRecommend==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscRecommend[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscThum || #request.defines['cscThum']!=null">
				<s:if test="#request.defines['cscThum']>0">
					${lz:set("注释","****cscThum关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscThum",lz:indexOf(fieldName,"cscThum")>-1)}				  		
				  		<s:if test="#request.atCscThum==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscThum[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscKeywords || #request.defines['cscKeywords']!=null">
				<s:if test="#request.defines['cscKeywords']>0">
					${lz:set("注释","****cscKeywords关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscKeywords",lz:indexOf(fieldName,"cscKeywords")>-1)}				  		
				  		<s:if test="#request.atCscKeywords==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscKeywords[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscFile || #request.defines['cscFile']!=null">
				<s:if test="#request.defines['cscFile']>0">
					${lz:set("注释","****cscFile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscFile",lz:indexOf(fieldName,"cscFile")>-1)}				  		
				  		<s:if test="#request.atCscFile==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscFile[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscViewS || #request.defines['cscViewS']!=null">
				<s:if test="#request.defines['cscViewS']>0">
					${lz:set("注释","****cscViewS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscViewS",lz:indexOf(fieldName,"cscViewS")>-1)}				  		
				  		<s:if test="#request.atCscViewS==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscViewS[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscReplyS || #request.defines['cscReplyS']!=null">
				<s:if test="#request.defines['cscReplyS']>0">
					${lz:set("注释","****cscReplyS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscReplyS",lz:indexOf(fieldName,"cscReplyS")>-1)}				  		
				  		<s:if test="#request.atCscReplyS==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscReplyS[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscCollectS || #request.defines['cscCollectS']!=null">
				<s:if test="#request.defines['cscCollectS']>0">
					${lz:set("注释","****cscCollectS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscCollectS",lz:indexOf(fieldName,"cscCollectS")>-1)}				  		
				  		<s:if test="#request.atCscCollectS==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscCollectS[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscPraiseS || #request.defines['cscPraiseS']!=null">
				<s:if test="#request.defines['cscPraiseS']>0">
					${lz:set("注释","****cscPraiseS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscPraiseS",lz:indexOf(fieldName,"cscPraiseS")>-1)}				  		
				  		<s:if test="#request.atCscPraiseS==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscPraiseS[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
  
 </td></tr></table>

<script>
$(function(){	
	$("#content_table").treegrid({expand:true});
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
	* 添加静态内容
	**/
	function AddContent(parent){		
		var url = "${basePath}${namespace}content_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加静态内容'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择静态内容
	**/
	function SelectContent(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改静态内容
	**/
	function EditContent(id){
		var url = "${basePath}${namespace}content_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改静态内容'}":lz:json(ctrl)}};
		href(url,params);
	}
	
	/**
	* 修改静态内容
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}content_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新静态内容",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["status"]={};
	fieldNames["status"]["cscStatus"]=true;
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改静态内容任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}content_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新静态内容",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}content_edit.do";
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
			var url = "${basePath}${namespace}content_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除静态内容
	**/
	function DelContent(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除静态内容["+flag+"]吗？</span>",
				title:"删除静态内容",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}content_del.do";
					var params = {entrypoint:"${entrypoint}",id:id};
					$.submit(url,params);
				}
		 });	
	}
</s:if>
	/**
	* 显示静态内容详情
	**/
	function DetailsContent(id)
	{
		var url = "${basePath}${namespace}content_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"静态内容详情",editable:false,visible:true}};
		href(url,params);
	}
</script>


<!------------------------LAZY3Q_JSP_BOTTOM------------------------>
<!------------------------LAZY3Q_JSP_BOTTOM------------------------>


</body>
</html>