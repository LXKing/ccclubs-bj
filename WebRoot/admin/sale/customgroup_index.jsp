<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/sale/customgroup.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty tbCustomGroup.tbcgId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"tbcgId,tbcgHost,tbcgBattle,tbcgEvent,tbcgName,tbcgTrade,tbcgService,tbcgScale,tbcgAddress,tbcgConcact,tbcgNumber,tbcgDetail,tbcgOnwer,tbcgAdder,tbcgRemark,tbcgMemo,tbcgData,tbcgUpdateTime,tbcgAddTime,tbcgStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"tbcgId,tbcgHost,tbcgBattle,tbcgEvent,tbcgName,tbcgFullName,tbcgType,tbcgTrade,tbcgService,tbcgScale,tbcgAddress,tbcgConcact,tbcgNumber,tbcgDetail,tbcgOnwer,tbcgAdder,tbcgRemark,tbcgMemo,tbcgData,tbcgUpdateTime,tbcgAddTime,tbcgStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"tbcgId,tbcgHost,tbcgBattle,tbcgEvent,tbcgName,tbcgTrade,tbcgConcact,tbcgNumber,tbcgOnwer,tbcgRemark,tbcgUpdateTime,tbcgAddTime,tbcgStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"tbcgId,tbcgHost,tbcgBattle,tbcgEvent,tbcgName,tbcgFullName,tbcgType,tbcgTrade,tbcgConcact,tbcgNumber,tbcgOnwer,tbcgRemark,tbcgUpdateTime,tbcgAddTime,tbcgStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>客户组织管理</title>
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
	<span class="caption">${empty title?"客户组织管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="TbCustomGroup" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="customgroup.query.jsp"%>

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
			<button type="button" class="add" onclick="AddCustomgroup();">
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
			<dl ref="com.ccclubs.model.TbCustomGroup" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.TbCustomGroup" class="batch dropdowan">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/20088256421529677807.png"/>
					批量修改
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
					<s:if test="#request.defines==null && #request.CTRL.l.tbcgStatus || #request.defines['tbcgStatus']!=null">
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
		<table id="customgroup_table" ref="TbCustomGroup" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbcgId || #request.defines['tbcgId']!=null">
			 <td <s:if test="#request.defines['tbcgId']>0">colspan="${defines["tbcgId"]}" ${all?"width":"iwidth"}="${defines["tbcgId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="tbcgId"   title="编号">
			 	<a class="${desc=="tbcg_id" ? "desc" : ""}${asc=="tbcg_id" ? "asc" : ""}" href="?${desc=="tbcg_id" ? "asc=tbcg_id" : ""}${(asc=="tbcg_id" || desc!="tbcg_id" )? "desc=tbcg_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","tbcgId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbcgHost || #request.defines['tbcgHost']!=null">
			 <td <s:if test="#request.defines['tbcgHost']>0">colspan="${defines["tbcgHost"]}" ${all?"width":"iwidth"}="${defines["tbcgHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="tbcgHost"   title="城市">
			 	<a class="${desc=="tbcg_host" ? "desc" : ""}${asc=="tbcg_host" ? "asc" : ""}" href="?${desc=="tbcg_host" ? "asc=tbcg_host" : ""}${(asc=="tbcg_host" || desc!="tbcg_host" )? "desc=tbcg_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","tbcgHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbcgBattle || #request.defines['tbcgBattle']!=null">
			 <td <s:if test="#request.defines['tbcgBattle']>0">colspan="${defines["tbcgBattle"]}" ${all?"width":"iwidth"}="${defines["tbcgBattle"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="3" ref="tbcgBattle"   title="归属战役">
			 	<a class="${desc=="tbcg_battle" ? "desc" : ""}${asc=="tbcg_battle" ? "asc" : ""}" href="?${desc=="tbcg_battle" ? "asc=tbcg_battle" : ""}${(asc=="tbcg_battle" || desc!="tbcg_battle" )? "desc=tbcg_battle" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	归属战役
			 	</a>
			 	${lz:set("checkeds[]","tbcgBattle")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbcgEvent || #request.defines['tbcgEvent']!=null">
			 <td <s:if test="#request.defines['tbcgEvent']>0">colspan="${defines["tbcgEvent"]}" ${all?"width":"iwidth"}="${defines["tbcgEvent"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="tbcgEvent"   title="归属活动">
			 	<a class="${desc=="tbcg_event" ? "desc" : ""}${asc=="tbcg_event" ? "asc" : ""}" href="?${desc=="tbcg_event" ? "asc=tbcg_event" : ""}${(asc=="tbcg_event" || desc!="tbcg_event" )? "desc=tbcg_event" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	归属活动
			 	</a>
			 	${lz:set("checkeds[]","tbcgEvent")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbcgName || #request.defines['tbcgName']!=null">
			 <td <s:if test="#request.defines['tbcgName']>0">colspan="${defines["tbcgName"]}" ${all?"width":"iwidth"}="${defines["tbcgName"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="5" ref="tbcgName" flagKey  title="组织名称">
			 	<a class="${desc=="tbcg_name" ? "desc" : ""}${asc=="tbcg_name" ? "asc" : ""}" href="?${desc=="tbcg_name" ? "asc=tbcg_name" : ""}${(asc=="tbcg_name" || desc!="tbcg_name" )? "desc=tbcg_name" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	组织名称
			 	</a>
			 	${lz:set("checkeds[]","tbcgName")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbcgFullName || #request.defines['tbcgFullName']!=null">
			 <td <s:if test="#request.defines['tbcgFullName']>0">colspan="${defines["tbcgFullName"]}" ${all?"width":"iwidth"}="${defines["tbcgFullName"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="6" ref="tbcgFullName"   title="组织全称">
			 	<a class="${desc=="tbcg_full_name" ? "desc" : ""}${asc=="tbcg_full_name" ? "asc" : ""}" href="?${desc=="tbcg_full_name" ? "asc=tbcg_full_name" : ""}${(asc=="tbcg_full_name" || desc!="tbcg_full_name" )? "desc=tbcg_full_name" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	组织全称
			 	</a>
			 	${lz:set("checkeds[]","tbcgFullName")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbcgType || #request.defines['tbcgType']!=null">
			 <td <s:if test="#request.defines['tbcgType']>0">colspan="${defines["tbcgType"]}" ${all?"width":"iwidth"}="${defines["tbcgType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="7" ref="tbcgType"   title="组织类型">
			 	<a class="${desc=="tbcg_type" ? "desc" : ""}${asc=="tbcg_type" ? "asc" : ""}" href="?${desc=="tbcg_type" ? "asc=tbcg_type" : ""}${(asc=="tbcg_type" || desc!="tbcg_type" )? "desc=tbcg_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	组织类型
			 	</a>
			 	${lz:set("checkeds[]","tbcgType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbcgTrade || #request.defines['tbcgTrade']!=null">
			 <td <s:if test="#request.defines['tbcgTrade']>0">colspan="${defines["tbcgTrade"]}" ${all?"width":"iwidth"}="${defines["tbcgTrade"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="tbcgTrade"   title="行业">
			 	<a class="${desc=="tbcg_trade" ? "desc" : ""}${asc=="tbcg_trade" ? "asc" : ""}" href="?${desc=="tbcg_trade" ? "asc=tbcg_trade" : ""}${(asc=="tbcg_trade" || desc!="tbcg_trade" )? "desc=tbcg_trade" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	行业
			 	</a>
			 	${lz:set("checkeds[]","tbcgTrade")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbcgService || #request.defines['tbcgService']!=null">
			 <td <s:if test="#request.defines['tbcgService']>0">colspan="${defines["tbcgService"]}" ${all?"width":"iwidth"}="${defines["tbcgService"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="9" ref="tbcgService"   title="主营业务">
			 	<a class="${desc=="tbcg_service" ? "desc" : ""}${asc=="tbcg_service" ? "asc" : ""}" href="?${desc=="tbcg_service" ? "asc=tbcg_service" : ""}${(asc=="tbcg_service" || desc!="tbcg_service" )? "desc=tbcg_service" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	主营业务
			 	</a>
			 	${lz:set("checkeds[]","tbcgService")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbcgScale || #request.defines['tbcgScale']!=null">
			 <td <s:if test="#request.defines['tbcgScale']>0">colspan="${defines["tbcgScale"]}" ${all?"width":"iwidth"}="${defines["tbcgScale"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="10" ref="tbcgScale"   title="人员规模">
			 	<a class="${desc=="tbcg_scale" ? "desc" : ""}${asc=="tbcg_scale" ? "asc" : ""}" href="?${desc=="tbcg_scale" ? "asc=tbcg_scale" : ""}${(asc=="tbcg_scale" || desc!="tbcg_scale" )? "desc=tbcg_scale" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	人员规模
			 	</a>
			 	${lz:set("checkeds[]","tbcgScale")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbcgAddress || #request.defines['tbcgAddress']!=null">
			 <td <s:if test="#request.defines['tbcgAddress']>0">colspan="${defines["tbcgAddress"]}" ${all?"width":"iwidth"}="${defines["tbcgAddress"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="11" ref="tbcgAddress"   title="详细地址">
			 	<a class="${desc=="tbcg_address" ? "desc" : ""}${asc=="tbcg_address" ? "asc" : ""}" href="?${desc=="tbcg_address" ? "asc=tbcg_address" : ""}${(asc=="tbcg_address" || desc!="tbcg_address" )? "desc=tbcg_address" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	详细地址
			 	</a>
			 	${lz:set("checkeds[]","tbcgAddress")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbcgConcact || #request.defines['tbcgConcact']!=null">
			 <td <s:if test="#request.defines['tbcgConcact']>0">colspan="${defines["tbcgConcact"]}" ${all?"width":"iwidth"}="${defines["tbcgConcact"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="12" ref="tbcgConcact"   title="联系人">
			 	<a class="${desc=="tbcg_concact" ? "desc" : ""}${asc=="tbcg_concact" ? "asc" : ""}" href="?${desc=="tbcg_concact" ? "asc=tbcg_concact" : ""}${(asc=="tbcg_concact" || desc!="tbcg_concact" )? "desc=tbcg_concact" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	联系人
			 	</a>
			 	${lz:set("checkeds[]","tbcgConcact")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbcgNumber || #request.defines['tbcgNumber']!=null">
			 <td <s:if test="#request.defines['tbcgNumber']>0">colspan="${defines["tbcgNumber"]}" ${all?"width":"iwidth"}="${defines["tbcgNumber"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="13" ref="tbcgNumber"   title="联系电话">
			 	<a class="${desc=="tbcg_number" ? "desc" : ""}${asc=="tbcg_number" ? "asc" : ""}" href="?${desc=="tbcg_number" ? "asc=tbcg_number" : ""}${(asc=="tbcg_number" || desc!="tbcg_number" )? "desc=tbcg_number" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	联系电话
			 	</a>
			 	${lz:set("checkeds[]","tbcgNumber")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbcgDetail || #request.defines['tbcgDetail']!=null">
			 <td <s:if test="#request.defines['tbcgDetail']>0">colspan="${defines["tbcgDetail"]}" ${all?"width":"iwidth"}="${defines["tbcgDetail"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="14" ref="tbcgDetail"   title="备注信息">
			 	<a class="${desc=="tbcg_detail" ? "desc" : ""}${asc=="tbcg_detail" ? "asc" : ""}" href="?${desc=="tbcg_detail" ? "asc=tbcg_detail" : ""}${(asc=="tbcg_detail" || desc!="tbcg_detail" )? "desc=tbcg_detail" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备注信息
			 	</a>
			 	${lz:set("checkeds[]","tbcgDetail")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbcgOnwer || #request.defines['tbcgOnwer']!=null">
			 <td <s:if test="#request.defines['tbcgOnwer']>0">colspan="${defines["tbcgOnwer"]}" ${all?"width":"iwidth"}="${defines["tbcgOnwer"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="15" ref="tbcgOnwer"   title="所有人">
			 	<a class="${desc=="tbcg_onwer" ? "desc" : ""}${asc=="tbcg_onwer" ? "asc" : ""}" href="?${desc=="tbcg_onwer" ? "asc=tbcg_onwer" : ""}${(asc=="tbcg_onwer" || desc!="tbcg_onwer" )? "desc=tbcg_onwer" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所有人
			 	</a>
			 	${lz:set("checkeds[]","tbcgOnwer")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbcgAdder || #request.defines['tbcgAdder']!=null">
			 <td <s:if test="#request.defines['tbcgAdder']>0">colspan="${defines["tbcgAdder"]}" ${all?"width":"iwidth"}="${defines["tbcgAdder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="16" ref="tbcgAdder"   title="添加人">
			 	<a class="${desc=="tbcg_adder" ? "desc" : ""}${asc=="tbcg_adder" ? "asc" : ""}" href="?${desc=="tbcg_adder" ? "asc=tbcg_adder" : ""}${(asc=="tbcg_adder" || desc!="tbcg_adder" )? "desc=tbcg_adder" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加人
			 	</a>
			 	${lz:set("checkeds[]","tbcgAdder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbcgRemark || #request.defines['tbcgRemark']!=null">
			 <td <s:if test="#request.defines['tbcgRemark']>0">colspan="${defines["tbcgRemark"]}" ${all?"width":"iwidth"}="${defines["tbcgRemark"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="17" ref="tbcgRemark"   title="备注">
			 	<a class="${desc=="tbcg_remark" ? "desc" : ""}${asc=="tbcg_remark" ? "asc" : ""}" href="?${desc=="tbcg_remark" ? "asc=tbcg_remark" : ""}${(asc=="tbcg_remark" || desc!="tbcg_remark" )? "desc=tbcg_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备注
			 	</a>
			 	${lz:set("checkeds[]","tbcgRemark")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbcgMemo || #request.defines['tbcgMemo']!=null">
			 <td <s:if test="#request.defines['tbcgMemo']>0">colspan="${defines["tbcgMemo"]}" ${all?"width":"iwidth"}="${defines["tbcgMemo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="18" ref="tbcgMemo"   title="备忘信息">
			 	<a class="${desc=="tbcg_memo" ? "desc" : ""}${asc=="tbcg_memo" ? "asc" : ""}" href="?${desc=="tbcg_memo" ? "asc=tbcg_memo" : ""}${(asc=="tbcg_memo" || desc!="tbcg_memo" )? "desc=tbcg_memo" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备忘信息
			 	</a>
			 	${lz:set("checkeds[]","tbcgMemo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbcgData || #request.defines['tbcgData']!=null">
			 <td <s:if test="#request.defines['tbcgData']>0">colspan="${defines["tbcgData"]}" ${all?"width":"iwidth"}="${defines["tbcgData"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="19" ref="tbcgData"   title="程序数据">
			 	<a class="${desc=="tbcg_data" ? "desc" : ""}${asc=="tbcg_data" ? "asc" : ""}" href="?${desc=="tbcg_data" ? "asc=tbcg_data" : ""}${(asc=="tbcg_data" || desc!="tbcg_data" )? "desc=tbcg_data" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	程序数据
			 	</a>
			 	${lz:set("checkeds[]","tbcgData")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbcgUpdateTime || #request.defines['tbcgUpdateTime']!=null">
			 <td <s:if test="#request.defines['tbcgUpdateTime']>0">colspan="${defines["tbcgUpdateTime"]}" ${all?"width":"iwidth"}="${defines["tbcgUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="20" ref="tbcgUpdateTime"   title="修改时间">
			 	<a class="${desc=="tbcg_update_time" ? "desc" : ""}${asc=="tbcg_update_time" ? "asc" : ""}" href="?${desc=="tbcg_update_time" ? "asc=tbcg_update_time" : ""}${(asc=="tbcg_update_time" || desc!="tbcg_update_time" )? "desc=tbcg_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 	${lz:set("checkeds[]","tbcgUpdateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbcgAddTime || #request.defines['tbcgAddTime']!=null">
			 <td <s:if test="#request.defines['tbcgAddTime']>0">colspan="${defines["tbcgAddTime"]}" ${all?"width":"iwidth"}="${defines["tbcgAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="21" ref="tbcgAddTime"   title="添加时间">
			 	<a class="${desc=="tbcg_add_time" ? "desc" : ""}${asc=="tbcg_add_time" ? "asc" : ""}" href="?${desc=="tbcg_add_time" ? "asc=tbcg_add_time" : ""}${(asc=="tbcg_add_time" || desc!="tbcg_add_time" )? "desc=tbcg_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","tbcgAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbcgStatus || #request.defines['tbcgStatus']!=null">
			 <td <s:if test="#request.defines['tbcgStatus']>0">colspan="${defines["tbcgStatus"]}" ${all?"width":"iwidth"}="${defines["tbcgStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="22" ref="tbcgStatus"   title="状态">
			 	<a class="${desc=="tbcg_status" ? "desc" : ""}${asc=="tbcg_status" ? "asc" : ""}" href="?${desc=="tbcg_status" ? "asc=tbcg_status" : ""}${(asc=="tbcg_status" || desc!="tbcg_status" )? "desc=tbcg_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","tbcgStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="23" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="24" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:AddCustomgroup()" style="text-decoration:underline">添加</a>
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
		  <tr id="${item.tbcgId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.tbcgId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgId || #request.defines['tbcgId']!=null">
				<s:if test="#request.defines['tbcgId']>0">
					${lz:set("注释","****tbcgId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbcgId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbcgId",lz:indexOf(fieldName,"tbcgId")>-1)}
				  		<s:if test="#request.atTbcgId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbcgId字段的字串格式化输出****")}
					<td ref="tbcgId" class="td ">
						 
						 	${lz:or(item$tbcgId[i.count-1],lz:left(item.tbcgId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgHost || #request.defines['tbcgHost']!=null">
				<s:if test="#request.defines['tbcgHost']>0">
					${lz:set("注释","****tbcgHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbcgHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbcgHost",lz:indexOf(fieldName,"tbcgHost")>-1)}
				  		<s:if test="#request.atTbcgHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbcgHost字段的字串格式化输出****")}
					<td ref="tbcgHost" class="td " relate="${item.tbcgHost}">
						 
						 	<a <s:if test="#item.tbcgHost!=null && #item.tbcgHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.tbcgHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbcgHost[i.count-1],lz:left(item.tbcgHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgBattle || #request.defines['tbcgBattle']!=null">
				<s:if test="#request.defines['tbcgBattle']>0">
					${lz:set("注释","****tbcgBattle关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbcgBattle))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbcgBattle",lz:indexOf(fieldName,"tbcgBattle")>-1)}
				  		<s:if test="#request.atTbcgBattle==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbcgBattle字段的字串格式化输出****")}
					<td ref="tbcgBattle" class="td " relate="${item.tbcgBattle}">
						 
						 	<a <s:if test="#item.tbcgBattle!=null && #item.tbcgBattle!=''"> onclick="window.href('${basePath}${proname}/sale/battle_details.do?key=${item.tbcgBattle}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbcgBattle[i.count-1],lz:left(item.tbcgBattle$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgEvent || #request.defines['tbcgEvent']!=null">
				<s:if test="#request.defines['tbcgEvent']>0">
					${lz:set("注释","****tbcgEvent关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbcgEvent))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbcgEvent",lz:indexOf(fieldName,"tbcgEvent")>-1)}
				  		<s:if test="#request.atTbcgEvent==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbcgEvent字段的字串格式化输出****")}
					<td ref="tbcgEvent" class="td " relate="${item.tbcgEvent}">
						 
						 	<a <s:if test="#item.tbcgEvent!=null && #item.tbcgEvent!=''"> onclick="window.href('${basePath}${proname}/sale/event_details.do?key=${item.tbcgEvent}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbcgEvent[i.count-1],lz:left(item.tbcgEvent$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgName || #request.defines['tbcgName']!=null">
				<s:if test="#request.defines['tbcgName']>0">
					${lz:set("注释","****tbcgName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbcgName))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbcgName",lz:indexOf(fieldName,"tbcgName")>-1)}
				  		<s:if test="#request.atTbcgName==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbcgName字段的字串格式化输出****")}
					<td ref="tbcgName" class="td  node">
						 
						 	${lz:or(item$tbcgName[i.count-1],lz:left(item.tbcgName$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgFullName || #request.defines['tbcgFullName']!=null">
				<s:if test="#request.defines['tbcgFullName']>0">
					${lz:set("注释","****tbcgFullName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbcgFullName))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbcgFullName",lz:indexOf(fieldName,"tbcgFullName")>-1)}
				  		<s:if test="#request.atTbcgFullName==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbcgFullName字段的字串格式化输出****")}
					<td ref="tbcgFullName" class="td ">
						 
						 	${lz:or(item$tbcgFullName[i.count-1],lz:left(item.tbcgFullName$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgType || #request.defines['tbcgType']!=null">
				<s:if test="#request.defines['tbcgType']>0">
					${lz:set("注释","****tbcgType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbcgType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbcgType",lz:indexOf(fieldName,"tbcgType")>-1)}
				  		<s:if test="#request.atTbcgType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbcgType字段的字串格式化输出****")}
					<td ref="tbcgType" class="td ">
						 
						 	${lz:or(item$tbcgType[i.count-1],lz:left(item.tbcgType$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgTrade || #request.defines['tbcgTrade']!=null">
				<s:if test="#request.defines['tbcgTrade']>0">
					${lz:set("注释","****tbcgTrade关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbcgTrade))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbcgTrade",lz:indexOf(fieldName,"tbcgTrade")>-1)}
				  		<s:if test="#request.atTbcgTrade==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbcgTrade字段的字串格式化输出****")}
					<td ref="tbcgTrade" class="td " relate="${item.tbcgTrade}">
						 
						 	<a <s:if test="#item.tbcgTrade!=null && #item.tbcgTrade!=''"> onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${item.tbcgTrade}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbcgTrade[i.count-1],lz:left(item.tbcgTrade$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgService || #request.defines['tbcgService']!=null">
				<s:if test="#request.defines['tbcgService']>0">
					${lz:set("注释","****tbcgService关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbcgService))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbcgService",lz:indexOf(fieldName,"tbcgService")>-1)}
				  		<s:if test="#request.atTbcgService==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbcgService字段的字串格式化输出****")}
					<td ref="tbcgService" class="td ">
						 
						 	${lz:or(item$tbcgService[i.count-1],lz:left(item.tbcgService$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgScale || #request.defines['tbcgScale']!=null">
				<s:if test="#request.defines['tbcgScale']>0">
					${lz:set("注释","****tbcgScale关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbcgScale))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbcgScale",lz:indexOf(fieldName,"tbcgScale")>-1)}
				  		<s:if test="#request.atTbcgScale==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbcgScale字段的字串格式化输出****")}
					<td ref="tbcgScale" class="td ">
						 
						 	${lz:or(item$tbcgScale[i.count-1],lz:left(item.tbcgScale$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgAddress || #request.defines['tbcgAddress']!=null">
				<s:if test="#request.defines['tbcgAddress']>0">
					${lz:set("注释","****tbcgAddress关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbcgAddress))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbcgAddress",lz:indexOf(fieldName,"tbcgAddress")>-1)}
				  		<s:if test="#request.atTbcgAddress==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbcgAddress字段的字串格式化输出****")}
					<td ref="tbcgAddress" class="td ">
						 
						 	${lz:or(item$tbcgAddress[i.count-1],lz:left(item.tbcgAddress$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgConcact || #request.defines['tbcgConcact']!=null">
				<s:if test="#request.defines['tbcgConcact']>0">
					${lz:set("注释","****tbcgConcact关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbcgConcact))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbcgConcact",lz:indexOf(fieldName,"tbcgConcact")>-1)}
				  		<s:if test="#request.atTbcgConcact==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbcgConcact字段的字串格式化输出****")}
					<td ref="tbcgConcact" class="td ">
						 
						 	${lz:or(item$tbcgConcact[i.count-1],lz:left(item.tbcgConcact$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgNumber || #request.defines['tbcgNumber']!=null">
				<s:if test="#request.defines['tbcgNumber']>0">
					${lz:set("注释","****tbcgNumber关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbcgNumber))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbcgNumber",lz:indexOf(fieldName,"tbcgNumber")>-1)}
				  		<s:if test="#request.atTbcgNumber==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbcgNumber字段的字串格式化输出****")}
					<td ref="tbcgNumber" class="td ">
						 
						 	${lz:or(item$tbcgNumber[i.count-1],lz:left(item.tbcgNumber$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgDetail || #request.defines['tbcgDetail']!=null">
				<s:if test="#request.defines['tbcgDetail']>0">
					${lz:set("注释","****tbcgDetail关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbcgDetail))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbcgDetail",lz:indexOf(fieldName,"tbcgDetail")>-1)}
				  		<s:if test="#request.atTbcgDetail==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbcgDetail字段的字串格式化输出****")}
					<td ref="tbcgDetail" class="td ">
						 
						 	${lz:or(item$tbcgDetail[i.count-1],lz:left(item.tbcgDetail$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgOnwer || #request.defines['tbcgOnwer']!=null">
				<s:if test="#request.defines['tbcgOnwer']>0">
					${lz:set("注释","****tbcgOnwer关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbcgOnwer))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbcgOnwer",lz:indexOf(fieldName,"tbcgOnwer")>-1)}
				  		<s:if test="#request.atTbcgOnwer==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbcgOnwer字段的字串格式化输出****")}
					<td ref="tbcgOnwer" class="td " relate="${item.tbcgOnwer}">
						 
						 	${lz:or(item$tbcgOnwer[i.count-1],lz:left(item.tbcgOnwer$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgAdder || #request.defines['tbcgAdder']!=null">
				<s:if test="#request.defines['tbcgAdder']>0">
					${lz:set("注释","****tbcgAdder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbcgAdder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbcgAdder",lz:indexOf(fieldName,"tbcgAdder")>-1)}
				  		<s:if test="#request.atTbcgAdder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbcgAdder字段的字串格式化输出****")}
					<td ref="tbcgAdder" class="td " relate="${item.tbcgAdder}">
						 
						 	<a <s:if test="#item.tbcgAdder!=null && #item.tbcgAdder!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.tbcgAdder}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbcgAdder[i.count-1],lz:left(item.tbcgAdder$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgRemark || #request.defines['tbcgRemark']!=null">
				<s:if test="#request.defines['tbcgRemark']>0">
					${lz:set("注释","****tbcgRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbcgRemark))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbcgRemark",lz:indexOf(fieldName,"tbcgRemark")>-1)}
				  		<s:if test="#request.atTbcgRemark==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbcgRemark字段的字串格式化输出****")}
					<td ref="tbcgRemark" class="td ">
						 
						 	${lz:or(item$tbcgRemark[i.count-1],lz:left(item.tbcgRemark$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgMemo || #request.defines['tbcgMemo']!=null">
				<s:if test="#request.defines['tbcgMemo']>0">
					${lz:set("注释","****tbcgMemo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbcgMemo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbcgMemo",lz:indexOf(fieldName,"tbcgMemo")>-1)}
				  		<s:if test="#request.atTbcgMemo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbcgMemo字段的字串格式化输出****")}
					<td ref="tbcgMemo" class="td ">
						 
						 	${lz:or(item$tbcgMemo[i.count-1],lz:left(item.tbcgMemo$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgData || #request.defines['tbcgData']!=null">
				<s:if test="#request.defines['tbcgData']>0">
					${lz:set("注释","****tbcgData关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbcgData))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbcgData",lz:indexOf(fieldName,"tbcgData")>-1)}
				  		<s:if test="#request.atTbcgData==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbcgData字段的字串格式化输出****")}
					<td ref="tbcgData" class="td ">
						 
						 	${lz:or(item$tbcgData[i.count-1],lz:left(item.tbcgData$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgUpdateTime || #request.defines['tbcgUpdateTime']!=null">
				<s:if test="#request.defines['tbcgUpdateTime']>0">
					${lz:set("注释","****tbcgUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbcgUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbcgUpdateTime",lz:indexOf(fieldName,"tbcgUpdateTime")>-1)}
				  		<s:if test="#request.atTbcgUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbcgUpdateTime字段的字串格式化输出****")}
					<td ref="tbcgUpdateTime" class="td ">
						 
						 	${lz:or(item$tbcgUpdateTime[i.count-1],lz:left(item.tbcgUpdateTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgAddTime || #request.defines['tbcgAddTime']!=null">
				<s:if test="#request.defines['tbcgAddTime']>0">
					${lz:set("注释","****tbcgAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbcgAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbcgAddTime",lz:indexOf(fieldName,"tbcgAddTime")>-1)}
				  		<s:if test="#request.atTbcgAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbcgAddTime字段的字串格式化输出****")}
					<td ref="tbcgAddTime" class="td ">
						 
						 	${lz:or(item$tbcgAddTime[i.count-1],lz:left(item.tbcgAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgStatus || #request.defines['tbcgStatus']!=null">
				<s:if test="#request.defines['tbcgStatus']>0">
					${lz:set("注释","****tbcgStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbcgStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbcgStatus",lz:indexOf(fieldName,"tbcgStatus")>-1)}
				  		<s:if test="#request.atTbcgStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbcgStatus字段的字串格式化输出****")}
					<td ref="tbcgStatus" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.tbcgId}','status')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$tbcgStatus[i.count-1],lz:left(item.tbcgStatus$,100))}
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
					<a href="javascript:HandleSel('${handle}','${item.tbcgId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectCustomgroup('${item.tbcgId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditCustomgroup('${item.tbcgId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelCustomgroup('${item.tbcgId}','${item.tbcgName}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsCustomgroup('${item.tbcgId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgId || #request.defines['tbcgId']!=null">
				<s:if test="#request.defines['tbcgId']>0">
					${lz:set("注释","****tbcgId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbcgId",lz:indexOf(fieldName,"tbcgId")>-1)}				  		
				  		<s:if test="#request.atTbcgId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbcgId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgHost || #request.defines['tbcgHost']!=null">
				<s:if test="#request.defines['tbcgHost']>0">
					${lz:set("注释","****tbcgHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbcgHost",lz:indexOf(fieldName,"tbcgHost")>-1)}				  		
				  		<s:if test="#request.atTbcgHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbcgHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgBattle || #request.defines['tbcgBattle']!=null">
				<s:if test="#request.defines['tbcgBattle']>0">
					${lz:set("注释","****tbcgBattle关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbcgBattle",lz:indexOf(fieldName,"tbcgBattle")>-1)}				  		
				  		<s:if test="#request.atTbcgBattle==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbcgBattle[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgEvent || #request.defines['tbcgEvent']!=null">
				<s:if test="#request.defines['tbcgEvent']>0">
					${lz:set("注释","****tbcgEvent关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbcgEvent",lz:indexOf(fieldName,"tbcgEvent")>-1)}				  		
				  		<s:if test="#request.atTbcgEvent==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbcgEvent[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgName || #request.defines['tbcgName']!=null">
				<s:if test="#request.defines['tbcgName']>0">
					${lz:set("注释","****tbcgName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbcgName",lz:indexOf(fieldName,"tbcgName")>-1)}				  		
				  		<s:if test="#request.atTbcgName==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbcgName[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgFullName || #request.defines['tbcgFullName']!=null">
				<s:if test="#request.defines['tbcgFullName']>0">
					${lz:set("注释","****tbcgFullName关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbcgFullName",lz:indexOf(fieldName,"tbcgFullName")>-1)}				  		
				  		<s:if test="#request.atTbcgFullName==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbcgFullName[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgType || #request.defines['tbcgType']!=null">
				<s:if test="#request.defines['tbcgType']>0">
					${lz:set("注释","****tbcgType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbcgType",lz:indexOf(fieldName,"tbcgType")>-1)}				  		
				  		<s:if test="#request.atTbcgType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbcgType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgTrade || #request.defines['tbcgTrade']!=null">
				<s:if test="#request.defines['tbcgTrade']>0">
					${lz:set("注释","****tbcgTrade关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbcgTrade",lz:indexOf(fieldName,"tbcgTrade")>-1)}				  		
				  		<s:if test="#request.atTbcgTrade==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbcgTrade[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgService || #request.defines['tbcgService']!=null">
				<s:if test="#request.defines['tbcgService']>0">
					${lz:set("注释","****tbcgService关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbcgService",lz:indexOf(fieldName,"tbcgService")>-1)}				  		
				  		<s:if test="#request.atTbcgService==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbcgService[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgScale || #request.defines['tbcgScale']!=null">
				<s:if test="#request.defines['tbcgScale']>0">
					${lz:set("注释","****tbcgScale关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbcgScale",lz:indexOf(fieldName,"tbcgScale")>-1)}				  		
				  		<s:if test="#request.atTbcgScale==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbcgScale[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgAddress || #request.defines['tbcgAddress']!=null">
				<s:if test="#request.defines['tbcgAddress']>0">
					${lz:set("注释","****tbcgAddress关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbcgAddress",lz:indexOf(fieldName,"tbcgAddress")>-1)}				  		
				  		<s:if test="#request.atTbcgAddress==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbcgAddress[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgConcact || #request.defines['tbcgConcact']!=null">
				<s:if test="#request.defines['tbcgConcact']>0">
					${lz:set("注释","****tbcgConcact关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbcgConcact",lz:indexOf(fieldName,"tbcgConcact")>-1)}				  		
				  		<s:if test="#request.atTbcgConcact==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbcgConcact[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgNumber || #request.defines['tbcgNumber']!=null">
				<s:if test="#request.defines['tbcgNumber']>0">
					${lz:set("注释","****tbcgNumber关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbcgNumber",lz:indexOf(fieldName,"tbcgNumber")>-1)}				  		
				  		<s:if test="#request.atTbcgNumber==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbcgNumber[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgDetail || #request.defines['tbcgDetail']!=null">
				<s:if test="#request.defines['tbcgDetail']>0">
					${lz:set("注释","****tbcgDetail关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbcgDetail",lz:indexOf(fieldName,"tbcgDetail")>-1)}				  		
				  		<s:if test="#request.atTbcgDetail==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbcgDetail[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgOnwer || #request.defines['tbcgOnwer']!=null">
				<s:if test="#request.defines['tbcgOnwer']>0">
					${lz:set("注释","****tbcgOnwer关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbcgOnwer",lz:indexOf(fieldName,"tbcgOnwer")>-1)}				  		
				  		<s:if test="#request.atTbcgOnwer==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbcgOnwer[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgAdder || #request.defines['tbcgAdder']!=null">
				<s:if test="#request.defines['tbcgAdder']>0">
					${lz:set("注释","****tbcgAdder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbcgAdder",lz:indexOf(fieldName,"tbcgAdder")>-1)}				  		
				  		<s:if test="#request.atTbcgAdder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbcgAdder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgRemark || #request.defines['tbcgRemark']!=null">
				<s:if test="#request.defines['tbcgRemark']>0">
					${lz:set("注释","****tbcgRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbcgRemark",lz:indexOf(fieldName,"tbcgRemark")>-1)}				  		
				  		<s:if test="#request.atTbcgRemark==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbcgRemark[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgMemo || #request.defines['tbcgMemo']!=null">
				<s:if test="#request.defines['tbcgMemo']>0">
					${lz:set("注释","****tbcgMemo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbcgMemo",lz:indexOf(fieldName,"tbcgMemo")>-1)}				  		
				  		<s:if test="#request.atTbcgMemo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbcgMemo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgData || #request.defines['tbcgData']!=null">
				<s:if test="#request.defines['tbcgData']>0">
					${lz:set("注释","****tbcgData关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbcgData",lz:indexOf(fieldName,"tbcgData")>-1)}				  		
				  		<s:if test="#request.atTbcgData==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbcgData[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgUpdateTime || #request.defines['tbcgUpdateTime']!=null">
				<s:if test="#request.defines['tbcgUpdateTime']>0">
					${lz:set("注释","****tbcgUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbcgUpdateTime",lz:indexOf(fieldName,"tbcgUpdateTime")>-1)}				  		
				  		<s:if test="#request.atTbcgUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbcgUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgAddTime || #request.defines['tbcgAddTime']!=null">
				<s:if test="#request.defines['tbcgAddTime']>0">
					${lz:set("注释","****tbcgAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbcgAddTime",lz:indexOf(fieldName,"tbcgAddTime")>-1)}				  		
				  		<s:if test="#request.atTbcgAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbcgAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbcgStatus || #request.defines['tbcgStatus']!=null">
				<s:if test="#request.defines['tbcgStatus']>0">
					${lz:set("注释","****tbcgStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbcgStatus",lz:indexOf(fieldName,"tbcgStatus")>-1)}				  		
				  		<s:if test="#request.atTbcgStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbcgStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 添加客户组织
	**/
	function AddCustomgroup(parent){		
		var url = "${basePath}${namespace}customgroup_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加客户组织'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择客户组织
	**/
	function SelectCustomgroup(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改客户组织
	**/
	function EditCustomgroup(id){
		var url = "${basePath}${namespace}customgroup_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改客户组织'}":lz:json(ctrl)}};
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
		EditCustomgroup(checker.val());
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
				var url = "${basePath}${namespace}customgroup_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新客户组织",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}customgroup_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新客户组织",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改客户组织
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}customgroup_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新客户组织",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["status"]={};
	fieldNames["status"]["tbcgStatus"]=true;
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改客户组织任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}customgroup_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新客户组织",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}customgroup_edit.do";
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
			var url = "${basePath}${namespace}customgroup_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除客户组织
	**/
	function DelCustomgroup(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除客户组织["+flag+"]吗？</span>",
				title:"删除客户组织",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}customgroup_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选客户组织吗？</span>",
				title:"批量删除客户组织",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}customgroup_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示客户组织详情
	**/
	function DetailsCustomgroup(id)
	{
		var url = "${basePath}${namespace}customgroup_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"客户组织详情",editable:false,visible:true}};
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
		DetailsCustomgroup(checker.val());
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