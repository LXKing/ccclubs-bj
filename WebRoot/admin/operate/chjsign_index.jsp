<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/operate/chjsign.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csChjSign.cscsId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"cscsId,cscsHost,cscsName,cscsMobile,cscsMember,cscsTakeTime,cscsRetTime,cscsModel,cscsOutlets,cscsLocked,cscsLockTime,cscsYear,cscsVisitChannel,cscsUpdateTime,cscsAddTime,cscsStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"cscsId,cscsHost,cscsName,cscsMobile,cscsMember,cscsTakeTime,cscsRetTime,cscsModel,cscsOutlets,cscsTarget,cscsLocked,cscsLockTime,cscsRemark,cscsYear,cscsVisitChannel,cscsUpdateTime,cscsAddTime,cscsStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"cscsHost,cscsMobile,cscsMember,cscsTakeTime,cscsRetTime,cscsModel,cscsOutlets,cscsLocked,cscsLockTime,cscsYear,cscsVisitChannel,cscsStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"cscsHost,cscsMobile,cscsMember,cscsTakeTime,cscsRetTime,cscsModel,cscsOutlets,cscsTarget,cscsLocked,cscsLockTime,cscsYear,cscsVisitChannel,cscsStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>春节套餐报名管理</title>
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
	<span class="caption">${empty title?"春节套餐报名管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsChjSign" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="chjsign.query.jsp"%>

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
			<button type="button" class="add" onclick="AddChjsign();">
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
			<dl ref="com.ccclubs.model.CsChjSign" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.CsChjSign" class="batch dropdowan">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/20088256421529677807.png"/>
					批量修改
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
					<s:if test="#request.defines==null && #request.CTRL.l.cscsStatus || #request.defines['cscsStatus']!=null">
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
		<table id="chjsign_table" ref="CsChjSign" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscsId || #request.defines['cscsId']!=null">
			 <td <s:if test="#request.defines['cscsId']>0">colspan="${defines["cscsId"]}" ${all?"width":"iwidth"}="${defines["cscsId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="cscsId"   title="编号">
			 	<a class="${desc=="cscs_id" ? "desc" : ""}${asc=="cscs_id" ? "asc" : ""}" href="?${desc=="cscs_id" ? "asc=cscs_id" : ""}${(asc=="cscs_id" || desc!="cscs_id" )? "desc=cscs_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","cscsId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscsHost || #request.defines['cscsHost']!=null">
			 <td <s:if test="#request.defines['cscsHost']>0">colspan="${defines["cscsHost"]}" ${all?"width":"iwidth"}="${defines["cscsHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="cscsHost"   title="城市">
			 	<a class="${desc=="cscs_host" ? "desc" : ""}${asc=="cscs_host" ? "asc" : ""}" href="?${desc=="cscs_host" ? "asc=cscs_host" : ""}${(asc=="cscs_host" || desc!="cscs_host" )? "desc=cscs_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","cscsHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscsName || #request.defines['cscsName']!=null">
			 <td <s:if test="#request.defines['cscsName']>0">colspan="${defines["cscsName"]}" ${all?"width":"iwidth"}="${defines["cscsName"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="3" ref="cscsName" flagKey  title="姓名">
			 	<a class="${desc=="cscs_name" ? "desc" : ""}${asc=="cscs_name" ? "asc" : ""}" href="?${desc=="cscs_name" ? "asc=cscs_name" : ""}${(asc=="cscs_name" || desc!="cscs_name" )? "desc=cscs_name" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	姓名
			 	</a>
			 	${lz:set("checkeds[]","cscsName")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscsMobile || #request.defines['cscsMobile']!=null">
			 <td <s:if test="#request.defines['cscsMobile']>0">colspan="${defines["cscsMobile"]}" ${all?"width":"iwidth"}="${defines["cscsMobile"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="4" ref="cscsMobile"   title="手机号码">
			 	<a class="${desc=="cscs_mobile" ? "desc" : ""}${asc=="cscs_mobile" ? "asc" : ""}" href="?${desc=="cscs_mobile" ? "asc=cscs_mobile" : ""}${(asc=="cscs_mobile" || desc!="cscs_mobile" )? "desc=cscs_mobile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	手机号码
			 	</a>
			 	${lz:set("checkeds[]","cscsMobile")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscsMember || #request.defines['cscsMember']!=null">
			 <td <s:if test="#request.defines['cscsMember']>0">colspan="${defines["cscsMember"]}" ${all?"width":"iwidth"}="${defines["cscsMember"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="cscsMember"   title="关联会员">
			 	<a class="${desc=="cscs_member" ? "desc" : ""}${asc=="cscs_member" ? "asc" : ""}" href="?${desc=="cscs_member" ? "asc=cscs_member" : ""}${(asc=="cscs_member" || desc!="cscs_member" )? "desc=cscs_member" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	关联会员
			 	</a>
			 	${lz:set("checkeds[]","cscsMember")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscsTakeTime || #request.defines['cscsTakeTime']!=null">
			 <td <s:if test="#request.defines['cscsTakeTime']>0">colspan="${defines["cscsTakeTime"]}" ${all?"width":"iwidth"}="${defines["cscsTakeTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="6" ref="cscsTakeTime"   title="取车时间">
			 	<a class="${desc=="cscs_take_time" ? "desc" : ""}${asc=="cscs_take_time" ? "asc" : ""}" href="?${desc=="cscs_take_time" ? "asc=cscs_take_time" : ""}${(asc=="cscs_take_time" || desc!="cscs_take_time" )? "desc=cscs_take_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	取车时间
			 	</a>
			 	${lz:set("checkeds[]","cscsTakeTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscsRetTime || #request.defines['cscsRetTime']!=null">
			 <td <s:if test="#request.defines['cscsRetTime']>0">colspan="${defines["cscsRetTime"]}" ${all?"width":"iwidth"}="${defines["cscsRetTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="7" ref="cscsRetTime"   title="还车时间">
			 	<a class="${desc=="cscs_ret_time" ? "desc" : ""}${asc=="cscs_ret_time" ? "asc" : ""}" href="?${desc=="cscs_ret_time" ? "asc=cscs_ret_time" : ""}${(asc=="cscs_ret_time" || desc!="cscs_ret_time" )? "desc=cscs_ret_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	还车时间
			 	</a>
			 	${lz:set("checkeds[]","cscsRetTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscsModel || #request.defines['cscsModel']!=null">
			 <td <s:if test="#request.defines['cscsModel']>0">colspan="${defines["cscsModel"]}" ${all?"width":"iwidth"}="${defines["cscsModel"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="cscsModel"   title="预定车型">
			 	<a class="${desc=="cscs_model" ? "desc" : ""}${asc=="cscs_model" ? "asc" : ""}" href="?${desc=="cscs_model" ? "asc=cscs_model" : ""}${(asc=="cscs_model" || desc!="cscs_model" )? "desc=cscs_model" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	预定车型
			 	</a>
			 	${lz:set("checkeds[]","cscsModel")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscsOutlets || #request.defines['cscsOutlets']!=null">
			 <td <s:if test="#request.defines['cscsOutlets']>0">colspan="${defines["cscsOutlets"]}" ${all?"width":"iwidth"}="${defines["cscsOutlets"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="9" ref="cscsOutlets"   title="预定网点">
			 	<a class="${desc=="cscs_outlets" ? "desc" : ""}${asc=="cscs_outlets" ? "asc" : ""}" href="?${desc=="cscs_outlets" ? "asc=cscs_outlets" : ""}${(asc=="cscs_outlets" || desc!="cscs_outlets" )? "desc=cscs_outlets" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	预定网点
			 	</a>
			 	${lz:set("checkeds[]","cscsOutlets")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscsTarget || #request.defines['cscsTarget']!=null">
			 <td <s:if test="#request.defines['cscsTarget']>0">colspan="${defines["cscsTarget"]}" ${all?"width":"iwidth"}="${defines["cscsTarget"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="10" ref="cscsTarget"   title="目的地">
			 	<a class="${desc=="cscs_target" ? "desc" : ""}${asc=="cscs_target" ? "asc" : ""}" href="?${desc=="cscs_target" ? "asc=cscs_target" : ""}${(asc=="cscs_target" || desc!="cscs_target" )? "desc=cscs_target" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	目的地
			 	</a>
			 	${lz:set("checkeds[]","cscsTarget")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscsLocked || #request.defines['cscsLocked']!=null">
			 <td <s:if test="#request.defines['cscsLocked']>0">colspan="${defines["cscsLocked"]}" ${all?"width":"iwidth"}="${defines["cscsLocked"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="11" ref="cscsLocked"   title="预定套餐">
			 	<a class="${desc=="cscs_locked" ? "desc" : ""}${asc=="cscs_locked" ? "asc" : ""}" href="?${desc=="cscs_locked" ? "asc=cscs_locked" : ""}${(asc=="cscs_locked" || desc!="cscs_locked" )? "desc=cscs_locked" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	预定套餐
			 	</a>
			 	${lz:set("checkeds[]","cscsLocked")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscsLockTime || #request.defines['cscsLockTime']!=null">
			 <td <s:if test="#request.defines['cscsLockTime']>0">colspan="${defines["cscsLockTime"]}" ${all?"width":"iwidth"}="${defines["cscsLockTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="12" ref="cscsLockTime"   title="预定时间">
			 	<a class="${desc=="cscs_lock_time" ? "desc" : ""}${asc=="cscs_lock_time" ? "asc" : ""}" href="?${desc=="cscs_lock_time" ? "asc=cscs_lock_time" : ""}${(asc=="cscs_lock_time" || desc!="cscs_lock_time" )? "desc=cscs_lock_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	预定时间
			 	</a>
			 	${lz:set("checkeds[]","cscsLockTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscsRemark || #request.defines['cscsRemark']!=null">
			 <td <s:if test="#request.defines['cscsRemark']>0">colspan="${defines["cscsRemark"]}" ${all?"width":"iwidth"}="${defines["cscsRemark"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="13" ref="cscsRemark"   title="备注">
			 	<a class="${desc=="cscs_remark" ? "desc" : ""}${asc=="cscs_remark" ? "asc" : ""}" href="?${desc=="cscs_remark" ? "asc=cscs_remark" : ""}${(asc=="cscs_remark" || desc!="cscs_remark" )? "desc=cscs_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备注
			 	</a>
			 	${lz:set("checkeds[]","cscsRemark")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscsYear || #request.defines['cscsYear']!=null">
			 <td <s:if test="#request.defines['cscsYear']>0">colspan="${defines["cscsYear"]}" ${all?"width":"iwidth"}="${defines["cscsYear"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="14" ref="cscsYear"   title="年份">
			 	<a class="${desc=="cscs_year" ? "desc" : ""}${asc=="cscs_year" ? "asc" : ""}" href="?${desc=="cscs_year" ? "asc=cscs_year" : ""}${(asc=="cscs_year" || desc!="cscs_year" )? "desc=cscs_year" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	年份
			 	</a>
			 	${lz:set("checkeds[]","cscsYear")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscsVisitChannel || #request.defines['cscsVisitChannel']!=null">
			 <td <s:if test="#request.defines['cscsVisitChannel']>0">colspan="${defines["cscsVisitChannel"]}" ${all?"width":"iwidth"}="${defines["cscsVisitChannel"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="15" ref="cscsVisitChannel"   title="渠道来源">
			 	<a class="${desc=="cscs_channel" ? "desc" : ""}${asc=="cscs_channel" ? "asc" : ""}" href="?${desc=="cscs_channel" ? "asc=cscs_channel" : ""}${(asc=="cscs_channel" || desc!="cscs_channel" )? "desc=cscs_channel" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	渠道来源
			 	</a>
			 	${lz:set("checkeds[]","cscsVisitChannel")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscsUpdateTime || #request.defines['cscsUpdateTime']!=null">
			 <td <s:if test="#request.defines['cscsUpdateTime']>0">colspan="${defines["cscsUpdateTime"]}" ${all?"width":"iwidth"}="${defines["cscsUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="16" ref="cscsUpdateTime"   title="修改时间">
			 	<a class="${desc=="cscs_update_time" ? "desc" : ""}${asc=="cscs_update_time" ? "asc" : ""}" href="?${desc=="cscs_update_time" ? "asc=cscs_update_time" : ""}${(asc=="cscs_update_time" || desc!="cscs_update_time" )? "desc=cscs_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 	${lz:set("checkeds[]","cscsUpdateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscsAddTime || #request.defines['cscsAddTime']!=null">
			 <td <s:if test="#request.defines['cscsAddTime']>0">colspan="${defines["cscsAddTime"]}" ${all?"width":"iwidth"}="${defines["cscsAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="17" ref="cscsAddTime"   title="添加时间">
			 	<a class="${desc=="cscs_add_time" ? "desc" : ""}${asc=="cscs_add_time" ? "asc" : ""}" href="?${desc=="cscs_add_time" ? "asc=cscs_add_time" : ""}${(asc=="cscs_add_time" || desc!="cscs_add_time" )? "desc=cscs_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","cscsAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscsStatus || #request.defines['cscsStatus']!=null">
			 <td <s:if test="#request.defines['cscsStatus']>0">colspan="${defines["cscsStatus"]}" ${all?"width":"iwidth"}="${defines["cscsStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="18" ref="cscsStatus"   title="状态">
			 	<a class="${desc=="cscs_status" ? "desc" : ""}${asc=="cscs_status" ? "asc" : ""}" href="?${desc=="cscs_status" ? "asc=cscs_status" : ""}${(asc=="cscs_status" || desc!="cscs_status" )? "desc=cscs_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","cscsStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="19" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="20" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:AddChjsign()" style="text-decoration:underline">添加</a>
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
		  <tr id="${item.cscsId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.cscsId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscsId || #request.defines['cscsId']!=null">
				<s:if test="#request.defines['cscsId']>0">
					${lz:set("注释","****cscsId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsId",lz:indexOf(fieldName,"cscsId")>-1)}
				  		<s:if test="#request.atCscsId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsId字段的字串格式化输出****")}
					<td ref="cscsId" class="td ">
						 
						 	${lz:or(item$cscsId[i.count-1],lz:left(item.cscsId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscsHost || #request.defines['cscsHost']!=null">
				<s:if test="#request.defines['cscsHost']>0">
					${lz:set("注释","****cscsHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsHost",lz:indexOf(fieldName,"cscsHost")>-1)}
				  		<s:if test="#request.atCscsHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsHost字段的字串格式化输出****")}
					<td ref="cscsHost" class="td " relate="${item.cscsHost}">
						 
						 	<a <s:if test="#item.cscsHost!=null && #item.cscsHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.cscsHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscsHost[i.count-1],lz:left(item.cscsHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscsName || #request.defines['cscsName']!=null">
				<s:if test="#request.defines['cscsName']>0">
					${lz:set("注释","****cscsName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsName))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsName",lz:indexOf(fieldName,"cscsName")>-1)}
				  		<s:if test="#request.atCscsName==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsName字段的字串格式化输出****")}
					<td ref="cscsName" class="td  node">
						 
						 	${lz:or(item$cscsName[i.count-1],lz:left(item.cscsName$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscsMobile || #request.defines['cscsMobile']!=null">
				<s:if test="#request.defines['cscsMobile']>0">
					${lz:set("注释","****cscsMobile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsMobile))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsMobile",lz:indexOf(fieldName,"cscsMobile")>-1)}
				  		<s:if test="#request.atCscsMobile==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsMobile字段的字串格式化输出****")}
					<td ref="cscsMobile" class="td ">
						 
						 	${lz:or(item$cscsMobile[i.count-1],lz:left(item.cscsMobile$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscsMember || #request.defines['cscsMember']!=null">
				<s:if test="#request.defines['cscsMember']>0">
					${lz:set("注释","****cscsMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsMember))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsMember",lz:indexOf(fieldName,"cscsMember")>-1)}
				  		<s:if test="#request.atCscsMember==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsMember字段的字串格式化输出****")}
					<td ref="cscsMember" class="td " relate="${item.cscsMember}">
						 
						 	<a <s:if test="#item.cscsMember!=null && #item.cscsMember!=''"> onclick="window.href('${basePath}${proname}/user/member_details.do?key=${item.cscsMember}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscsMember[i.count-1],lz:left(item.cscsMember$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscsTakeTime || #request.defines['cscsTakeTime']!=null">
				<s:if test="#request.defines['cscsTakeTime']>0">
					${lz:set("注释","****cscsTakeTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsTakeTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsTakeTime",lz:indexOf(fieldName,"cscsTakeTime")>-1)}
				  		<s:if test="#request.atCscsTakeTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsTakeTime字段的字串格式化输出****")}
					<td ref="cscsTakeTime" class="td ">
						 
						 	${lz:or(item$cscsTakeTime[i.count-1],lz:left(item.cscsTakeTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscsRetTime || #request.defines['cscsRetTime']!=null">
				<s:if test="#request.defines['cscsRetTime']>0">
					${lz:set("注释","****cscsRetTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsRetTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsRetTime",lz:indexOf(fieldName,"cscsRetTime")>-1)}
				  		<s:if test="#request.atCscsRetTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsRetTime字段的字串格式化输出****")}
					<td ref="cscsRetTime" class="td ">
						 
						 	${lz:or(item$cscsRetTime[i.count-1],lz:left(item.cscsRetTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscsModel || #request.defines['cscsModel']!=null">
				<s:if test="#request.defines['cscsModel']>0">
					${lz:set("注释","****cscsModel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsModel))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsModel",lz:indexOf(fieldName,"cscsModel")>-1)}
				  		<s:if test="#request.atCscsModel==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsModel字段的字串格式化输出****")}
					<td ref="cscsModel" class="td " relate="${item.cscsModel}">
						 
						 	<a <s:if test="#item.cscsModel!=null && #item.cscsModel!=''"> onclick="window.href('${basePath}${proname}/object/carmodel_details.do?key=${item.cscsModel}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscsModel[i.count-1],lz:left(item.cscsModel$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscsOutlets || #request.defines['cscsOutlets']!=null">
				<s:if test="#request.defines['cscsOutlets']>0">
					${lz:set("注释","****cscsOutlets关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsOutlets))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsOutlets",lz:indexOf(fieldName,"cscsOutlets")>-1)}
				  		<s:if test="#request.atCscsOutlets==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsOutlets字段的字串格式化输出****")}
					<td ref="cscsOutlets" class="td " relate="${item.cscsOutlets}">
						 
						 	<a <s:if test="#item.cscsOutlets!=null && #item.cscsOutlets!=''"> onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${item.cscsOutlets}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscsOutlets[i.count-1],lz:left(item.cscsOutlets$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscsTarget || #request.defines['cscsTarget']!=null">
				<s:if test="#request.defines['cscsTarget']>0">
					${lz:set("注释","****cscsTarget关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsTarget))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsTarget",lz:indexOf(fieldName,"cscsTarget")>-1)}
				  		<s:if test="#request.atCscsTarget==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsTarget字段的字串格式化输出****")}
					<td ref="cscsTarget" class="td ">
						 
						 	${lz:or(item$cscsTarget[i.count-1],lz:left(item.cscsTarget$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscsLocked || #request.defines['cscsLocked']!=null">
				<s:if test="#request.defines['cscsLocked']>0">
					${lz:set("注释","****cscsLocked关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsLocked))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsLocked",lz:indexOf(fieldName,"cscsLocked")>-1)}
				  		<s:if test="#request.atCscsLocked==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsLocked字段的字串格式化输出****")}
					<td ref="cscsLocked" class="td " relate="${item.cscsLocked}">
						 
						 	<a <s:if test="#item.cscsLocked!=null && #item.cscsLocked!=''"> onclick="window.href('${basePath}${proname}/fee/pack_details.do?key=${item.cscsLocked}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscsLocked[i.count-1],lz:left(item.cscsLocked$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscsLockTime || #request.defines['cscsLockTime']!=null">
				<s:if test="#request.defines['cscsLockTime']>0">
					${lz:set("注释","****cscsLockTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsLockTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsLockTime",lz:indexOf(fieldName,"cscsLockTime")>-1)}
				  		<s:if test="#request.atCscsLockTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsLockTime字段的字串格式化输出****")}
					<td ref="cscsLockTime" class="td ">
						 
						 	${lz:or(item$cscsLockTime[i.count-1],lz:left(item.cscsLockTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscsRemark || #request.defines['cscsRemark']!=null">
				<s:if test="#request.defines['cscsRemark']>0">
					${lz:set("注释","****cscsRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsRemark))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsRemark",lz:indexOf(fieldName,"cscsRemark")>-1)}
				  		<s:if test="#request.atCscsRemark==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsRemark字段的字串格式化输出****")}
					<td ref="cscsRemark" class="td ">
						 
						 	${lz:or(item$cscsRemark[i.count-1],lz:left(item.cscsRemark$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscsYear || #request.defines['cscsYear']!=null">
				<s:if test="#request.defines['cscsYear']>0">
					${lz:set("注释","****cscsYear关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsYear))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsYear",lz:indexOf(fieldName,"cscsYear")>-1)}
				  		<s:if test="#request.atCscsYear==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsYear字段的字串格式化输出****")}
					<td ref="cscsYear" class="td ">
						 
						 	${lz:or(item$cscsYear[i.count-1],lz:left(item.cscsYear$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscsVisitChannel || #request.defines['cscsVisitChannel']!=null">
				<s:if test="#request.defines['cscsVisitChannel']>0">
					${lz:set("注释","****cscsVisitChannel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsVisitChannel))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsVisitChannel",lz:indexOf(fieldName,"cscsVisitChannel")>-1)}
				  		<s:if test="#request.atCscsVisitChannel==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsVisitChannel字段的字串格式化输出****")}
					<td ref="cscsVisitChannel" class="td " relate="${item.cscsVisitChannel}">
						 
						 	<a <s:if test="#item.cscsVisitChannel!=null && #item.cscsVisitChannel!=''"> onclick="window.href('${basePath}${proname}/extension/channel_details.do?key=${item.cscsVisitChannel}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscsVisitChannel[i.count-1],lz:left(item.cscsVisitChannel$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscsUpdateTime || #request.defines['cscsUpdateTime']!=null">
				<s:if test="#request.defines['cscsUpdateTime']>0">
					${lz:set("注释","****cscsUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsUpdateTime",lz:indexOf(fieldName,"cscsUpdateTime")>-1)}
				  		<s:if test="#request.atCscsUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsUpdateTime字段的字串格式化输出****")}
					<td ref="cscsUpdateTime" class="td ">
						 
						 	${lz:or(item$cscsUpdateTime[i.count-1],lz:left(item.cscsUpdateTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscsAddTime || #request.defines['cscsAddTime']!=null">
				<s:if test="#request.defines['cscsAddTime']>0">
					${lz:set("注释","****cscsAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsAddTime",lz:indexOf(fieldName,"cscsAddTime")>-1)}
				  		<s:if test="#request.atCscsAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsAddTime字段的字串格式化输出****")}
					<td ref="cscsAddTime" class="td ">
						 
						 	${lz:or(item$cscsAddTime[i.count-1],lz:left(item.cscsAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscsStatus || #request.defines['cscsStatus']!=null">
				<s:if test="#request.defines['cscsStatus']>0">
					${lz:set("注释","****cscsStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscsStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscsStatus",lz:indexOf(fieldName,"cscsStatus")>-1)}
				  		<s:if test="#request.atCscsStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscsStatus字段的字串格式化输出****")}
					<td ref="cscsStatus" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.cscsId}','status')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$cscsStatus[i.count-1],lz:left(item.cscsStatus$,100))}
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
					<a href="javascript:HandleSel('${handle}','${item.cscsId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectChjsign('${item.cscsId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditChjsign('${item.cscsId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelChjsign('${item.cscsId}','${item.cscsName}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsChjsign('${item.cscsId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.cscsId || #request.defines['cscsId']!=null">
				<s:if test="#request.defines['cscsId']>0">
					${lz:set("注释","****cscsId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsId",lz:indexOf(fieldName,"cscsId")>-1)}				  		
				  		<s:if test="#request.atCscsId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscsHost || #request.defines['cscsHost']!=null">
				<s:if test="#request.defines['cscsHost']>0">
					${lz:set("注释","****cscsHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsHost",lz:indexOf(fieldName,"cscsHost")>-1)}				  		
				  		<s:if test="#request.atCscsHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscsName || #request.defines['cscsName']!=null">
				<s:if test="#request.defines['cscsName']>0">
					${lz:set("注释","****cscsName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsName",lz:indexOf(fieldName,"cscsName")>-1)}				  		
				  		<s:if test="#request.atCscsName==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsName[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscsMobile || #request.defines['cscsMobile']!=null">
				<s:if test="#request.defines['cscsMobile']>0">
					${lz:set("注释","****cscsMobile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsMobile",lz:indexOf(fieldName,"cscsMobile")>-1)}				  		
				  		<s:if test="#request.atCscsMobile==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsMobile[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscsMember || #request.defines['cscsMember']!=null">
				<s:if test="#request.defines['cscsMember']>0">
					${lz:set("注释","****cscsMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsMember",lz:indexOf(fieldName,"cscsMember")>-1)}				  		
				  		<s:if test="#request.atCscsMember==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsMember[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscsTakeTime || #request.defines['cscsTakeTime']!=null">
				<s:if test="#request.defines['cscsTakeTime']>0">
					${lz:set("注释","****cscsTakeTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsTakeTime",lz:indexOf(fieldName,"cscsTakeTime")>-1)}				  		
				  		<s:if test="#request.atCscsTakeTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsTakeTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscsRetTime || #request.defines['cscsRetTime']!=null">
				<s:if test="#request.defines['cscsRetTime']>0">
					${lz:set("注释","****cscsRetTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsRetTime",lz:indexOf(fieldName,"cscsRetTime")>-1)}				  		
				  		<s:if test="#request.atCscsRetTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsRetTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscsModel || #request.defines['cscsModel']!=null">
				<s:if test="#request.defines['cscsModel']>0">
					${lz:set("注释","****cscsModel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsModel",lz:indexOf(fieldName,"cscsModel")>-1)}				  		
				  		<s:if test="#request.atCscsModel==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsModel[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscsOutlets || #request.defines['cscsOutlets']!=null">
				<s:if test="#request.defines['cscsOutlets']>0">
					${lz:set("注释","****cscsOutlets关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsOutlets",lz:indexOf(fieldName,"cscsOutlets")>-1)}				  		
				  		<s:if test="#request.atCscsOutlets==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsOutlets[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscsTarget || #request.defines['cscsTarget']!=null">
				<s:if test="#request.defines['cscsTarget']>0">
					${lz:set("注释","****cscsTarget关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsTarget",lz:indexOf(fieldName,"cscsTarget")>-1)}				  		
				  		<s:if test="#request.atCscsTarget==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsTarget[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscsLocked || #request.defines['cscsLocked']!=null">
				<s:if test="#request.defines['cscsLocked']>0">
					${lz:set("注释","****cscsLocked关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsLocked",lz:indexOf(fieldName,"cscsLocked")>-1)}				  		
				  		<s:if test="#request.atCscsLocked==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsLocked[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscsLockTime || #request.defines['cscsLockTime']!=null">
				<s:if test="#request.defines['cscsLockTime']>0">
					${lz:set("注释","****cscsLockTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsLockTime",lz:indexOf(fieldName,"cscsLockTime")>-1)}				  		
				  		<s:if test="#request.atCscsLockTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsLockTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscsRemark || #request.defines['cscsRemark']!=null">
				<s:if test="#request.defines['cscsRemark']>0">
					${lz:set("注释","****cscsRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsRemark",lz:indexOf(fieldName,"cscsRemark")>-1)}				  		
				  		<s:if test="#request.atCscsRemark==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsRemark[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscsYear || #request.defines['cscsYear']!=null">
				<s:if test="#request.defines['cscsYear']>0">
					${lz:set("注释","****cscsYear关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsYear",lz:indexOf(fieldName,"cscsYear")>-1)}				  		
				  		<s:if test="#request.atCscsYear==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsYear[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscsVisitChannel || #request.defines['cscsVisitChannel']!=null">
				<s:if test="#request.defines['cscsVisitChannel']>0">
					${lz:set("注释","****cscsVisitChannel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsVisitChannel",lz:indexOf(fieldName,"cscsVisitChannel")>-1)}				  		
				  		<s:if test="#request.atCscsVisitChannel==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsVisitChannel[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscsUpdateTime || #request.defines['cscsUpdateTime']!=null">
				<s:if test="#request.defines['cscsUpdateTime']>0">
					${lz:set("注释","****cscsUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsUpdateTime",lz:indexOf(fieldName,"cscsUpdateTime")>-1)}				  		
				  		<s:if test="#request.atCscsUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscsAddTime || #request.defines['cscsAddTime']!=null">
				<s:if test="#request.defines['cscsAddTime']>0">
					${lz:set("注释","****cscsAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsAddTime",lz:indexOf(fieldName,"cscsAddTime")>-1)}				  		
				  		<s:if test="#request.atCscsAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscsStatus || #request.defines['cscsStatus']!=null">
				<s:if test="#request.defines['cscsStatus']>0">
					${lz:set("注释","****cscsStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscsStatus",lz:indexOf(fieldName,"cscsStatus")>-1)}				  		
				  		<s:if test="#request.atCscsStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscsStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 添加春节套餐报名
	**/
	function AddChjsign(parent){		
		var url = "${basePath}${namespace}chjsign_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加春节套餐报名'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择春节套餐报名
	**/
	function SelectChjsign(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改春节套餐报名
	**/
	function EditChjsign(id){
		var url = "${basePath}${namespace}chjsign_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改春节套餐报名'}":lz:json(ctrl)}};
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
		EditChjsign(checker.val());
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
				var url = "${basePath}${namespace}chjsign_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新春节套餐报名",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}chjsign_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新春节套餐报名",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改春节套餐报名
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}chjsign_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新春节套餐报名",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["status"]={};
	fieldNames["status"]["cscsStatus"]=true;
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改春节套餐报名任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}chjsign_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新春节套餐报名",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}chjsign_edit.do";
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
			var url = "${basePath}${namespace}chjsign_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除春节套餐报名
	**/
	function DelChjsign(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除春节套餐报名["+flag+"]吗？</span>",
				title:"删除春节套餐报名",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}chjsign_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选春节套餐报名吗？</span>",
				title:"批量删除春节套餐报名",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}chjsign_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示春节套餐报名详情
	**/
	function DetailsChjsign(id)
	{
		var url = "${basePath}${namespace}chjsign_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"春节套餐报名详情",editable:false,visible:true}};
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
		DetailsChjsign(checker.val());
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