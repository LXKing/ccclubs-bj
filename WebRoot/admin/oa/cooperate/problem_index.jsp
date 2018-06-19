<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/oa/cooperate/problem.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty tbProblem.tbpId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"tbpId,tbpHost,tbpProfile,tbpType,tbpOutlets,tbpSpecialData,tbpAdder,tbpAddTime,tbpStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"tbpId,tbpHost,tbpProfile,tbpType,tbpOutlets,tbpSpecialData,tbpAdder,tbpAddTime,tbpStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"tbpId,tbpHost,tbpProfile,tbpType,tbpOutlets,tbpSpecialData,tbpAdder,tbpAddTime,tbpStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"tbpId,tbpHost,tbpProfile,tbpType,tbpOutlets,tbpSpecialData,tbpAdder,tbpAddTime,tbpStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>问题故障报修管理</title>
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
	<span class="caption">${empty title?"问题故障报修管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="TbProblem" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="problem.query.jsp"%>

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
			<button type="button" class="add" onclick="AddProblem();">
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
			<dl ref="com.ccclubs.model.TbProblem" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.TbProblem" class="batch dropdowan">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/20088256421529677807.png"/>
					批量修改
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
					<s:if test="#request.defines==null && #request.CTRL.l.tbpStatus || #request.defines['tbpStatus']!=null">
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
		<table id="problem_table" ref="TbProblem" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpId || #request.defines['tbpId']!=null">
			 <td <s:if test="#request.defines['tbpId']>0">colspan="${defines["tbpId"]}" ${all?"width":"iwidth"}="${defines["tbpId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="tbpId"   title="编号">
			 	<a class="${desc=="tbp_id" ? "desc" : ""}${asc=="tbp_id" ? "asc" : ""}" href="?${desc=="tbp_id" ? "asc=tbp_id" : ""}${(asc=="tbp_id" || desc!="tbp_id" )? "desc=tbp_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","tbpId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpHost || #request.defines['tbpHost']!=null">
			 <td <s:if test="#request.defines['tbpHost']>0">colspan="${defines["tbpHost"]}" ${all?"width":"iwidth"}="${defines["tbpHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="tbpHost"   title="城市">
			 	<a class="${desc=="tbp_host" ? "desc" : ""}${asc=="tbp_host" ? "asc" : ""}" href="?${desc=="tbp_host" ? "asc=tbp_host" : ""}${(asc=="tbp_host" || desc!="tbp_host" )? "desc=tbp_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","tbpHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpProfile || #request.defines['tbpProfile']!=null">
			 <td <s:if test="#request.defines['tbpProfile']>0">colspan="${defines["tbpProfile"]}" ${all?"width":"iwidth"}="${defines["tbpProfile"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="3" ref="tbpProfile" flagKey  title="问题描述">
			 	<a class="${desc=="tbp_profile" ? "desc" : ""}${asc=="tbp_profile" ? "asc" : ""}" href="?${desc=="tbp_profile" ? "asc=tbp_profile" : ""}${(asc=="tbp_profile" || desc!="tbp_profile" )? "desc=tbp_profile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	问题描述
			 	</a>
			 	${lz:set("checkeds[]","tbpProfile")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpType || #request.defines['tbpType']!=null">
			 <td <s:if test="#request.defines['tbpType']>0">colspan="${defines["tbpType"]}" ${all?"width":"iwidth"}="${defines["tbpType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="tbpType"   title="问题类型">
			 	<a class="${desc=="tbp_type" ? "desc" : ""}${asc=="tbp_type" ? "asc" : ""}" href="?${desc=="tbp_type" ? "asc=tbp_type" : ""}${(asc=="tbp_type" || desc!="tbp_type" )? "desc=tbp_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	问题类型
			 	</a>
			 	${lz:set("checkeds[]","tbpType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpMember || #request.defines['tbpMember']!=null">
			 <td <s:if test="#request.defines['tbpMember']>0">colspan="${defines["tbpMember"]}" ${all?"width":"iwidth"}="${defines["tbpMember"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="tbpMember"   title="反馈会员">
			 	<a class="${desc=="tbp_member" ? "desc" : ""}${asc=="tbp_member" ? "asc" : ""}" href="?${desc=="tbp_member" ? "asc=tbp_member" : ""}${(asc=="tbp_member" || desc!="tbp_member" )? "desc=tbp_member" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	反馈会员
			 	</a>
			 	${lz:set("checkeds[]","tbpMember")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpOrder || #request.defines['tbpOrder']!=null">
			 <td <s:if test="#request.defines['tbpOrder']>0">colspan="${defines["tbpOrder"]}" ${all?"width":"iwidth"}="${defines["tbpOrder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="tbpOrder"   title="所属订单">
			 	<a class="${desc=="tbp_order" ? "desc" : ""}${asc=="tbp_order" ? "asc" : ""}" href="?${desc=="tbp_order" ? "asc=tbp_order" : ""}${(asc=="tbp_order" || desc!="tbp_order" )? "desc=tbp_order" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属订单
			 	</a>
			 	${lz:set("checkeds[]","tbpOrder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpRelate || #request.defines['tbpRelate']!=null">
			 <td <s:if test="#request.defines['tbpRelate']>0">colspan="${defines["tbpRelate"]}" ${all?"width":"iwidth"}="${defines["tbpRelate"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="7" ref="tbpRelate"   title="关联对象">
			 	<a class="${desc=="tbp_relate" ? "desc" : ""}${asc=="tbp_relate" ? "asc" : ""}" href="?${desc=="tbp_relate" ? "asc=tbp_relate" : ""}${(asc=="tbp_relate" || desc!="tbp_relate" )? "desc=tbp_relate" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	关联对象
			 	</a>
			 	${lz:set("checkeds[]","tbpRelate")}
			 </td>
			 </s:if>
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpOutlets || #request.defines['tbpOutlets']!=null">
			 <td <s:if test="#request.defines['tbpOutlets']>0">colspan="${defines["tbpOutlets"]}" ${all?"width":"iwidth"}="${defines["tbpOutlets"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="7" ref="tbpOutlets"   title="网点">
			 	<a class="${desc=="tbp_outlets" ? "desc" : ""}${asc=="tbp_outlets" ? "asc" : ""}" href="?${desc=="tbp_outlets" ? "asc=tbp_outlets" : ""}${(asc=="tbp_outlets" || desc!="tbp_outlets" )? "desc=tbp_outlets" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	网点
			 	</a>
			 	${lz:set("checkeds[]","tbpOutlets")}
			 </td>
			 </s:if>
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpSpecialData || #request.defines['tbpSpecialData']!=null">
			 <td <s:if test="#request.defines['tbpSpecialData']>0">colspan="${defines["tbpSpecialData"]}" ${all?"width":"iwidth"}="${defines["tbpSpecialData"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="7" ref="tbpSpecialData"   title="位置">
			 	<a class="${desc=="tbp_special_data" ? "desc" : ""}${asc=="tbp_special_data" ? "asc" : ""}" href="?${desc=="tbp_special_data" ? "asc=tbp_relate" : ""}${(asc=="tbp_special_data" || desc!="tbp_special_data" )? "desc=tbp_special_data" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	位置信息
			 	</a>
			 	${lz:set("checkeds[]","tbpSpecialData")}
			 </td>
			 </s:if>
			 
			 
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpAdder || #request.defines['tbpAdder']!=null">
			 <td <s:if test="#request.defines['tbpAdder']>0">colspan="${defines["tbpAdder"]}" ${all?"width":"iwidth"}="${defines["tbpAdder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="tbpAdder"   title="添加人员">
			 	<a class="${desc=="tbp_adder" ? "desc" : ""}${asc=="tbp_adder" ? "asc" : ""}" href="?${desc=="tbp_adder" ? "asc=tbp_adder" : ""}${(asc=="tbp_adder" || desc!="tbp_adder" )? "desc=tbp_adder" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加人员
			 	</a>
			 	${lz:set("checkeds[]","tbpAdder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpEditor || #request.defines['tbpEditor']!=null">
			 <td <s:if test="#request.defines['tbpEditor']>0">colspan="${defines["tbpEditor"]}" ${all?"width":"iwidth"}="${defines["tbpEditor"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="9" ref="tbpEditor"   title="修改人员">
			 	<a class="${desc=="tbp_editor" ? "desc" : ""}${asc=="tbp_editor" ? "asc" : ""}" href="?${desc=="tbp_editor" ? "asc=tbp_editor" : ""}${(asc=="tbp_editor" || desc!="tbp_editor" )? "desc=tbp_editor" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改人员
			 	</a>
			 	${lz:set("checkeds[]","tbpEditor")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpDealer || #request.defines['tbpDealer']!=null">
			 <td <s:if test="#request.defines['tbpDealer']>0">colspan="${defines["tbpDealer"]}" ${all?"width":"iwidth"}="${defines["tbpDealer"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="10" ref="tbpDealer"   title="任务指派">
			 	<a class="${desc=="tbp_dealer" ? "desc" : ""}${asc=="tbp_dealer" ? "asc" : ""}" href="?${desc=="tbp_dealer" ? "asc=tbp_dealer" : ""}${(asc=="tbp_dealer" || desc!="tbp_dealer" )? "desc=tbp_dealer" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	任务指派
			 	</a>
			 	${lz:set("checkeds[]","tbpDealer")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpResult || #request.defines['tbpResult']!=null">
			 <td <s:if test="#request.defines['tbpResult']>0">colspan="${defines["tbpResult"]}" ${all?"width":"iwidth"}="${defines["tbpResult"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="11" ref="tbpResult"   title="结案描述">
			 	<a class="${desc=="tbp_result" ? "desc" : ""}${asc=="tbp_result" ? "asc" : ""}" href="?${desc=="tbp_result" ? "asc=tbp_result" : ""}${(asc=="tbp_result" || desc!="tbp_result" )? "desc=tbp_result" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	结案描述
			 	</a>
			 	${lz:set("checkeds[]","tbpResult")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpRemark || #request.defines['tbpRemark']!=null">
			 <td <s:if test="#request.defines['tbpRemark']>0">colspan="${defines["tbpRemark"]}" ${all?"width":"iwidth"}="${defines["tbpRemark"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="12" ref="tbpRemark"   title="备注">
			 	<a class="${desc=="tbp_remark" ? "desc" : ""}${asc=="tbp_remark" ? "asc" : ""}" href="?${desc=="tbp_remark" ? "asc=tbp_remark" : ""}${(asc=="tbp_remark" || desc!="tbp_remark" )? "desc=tbp_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备注
			 	</a>
			 	${lz:set("checkeds[]","tbpRemark")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpMemo || #request.defines['tbpMemo']!=null">
			 <td <s:if test="#request.defines['tbpMemo']>0">colspan="${defines["tbpMemo"]}" ${all?"width":"iwidth"}="${defines["tbpMemo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="13" ref="tbpMemo"   title="备忘信息">
			 	<a class="${desc=="tbp_memo" ? "desc" : ""}${asc=="tbp_memo" ? "asc" : ""}" href="?${desc=="tbp_memo" ? "asc=tbp_memo" : ""}${(asc=="tbp_memo" || desc!="tbp_memo" )? "desc=tbp_memo" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备忘信息
			 	</a>
			 	${lz:set("checkeds[]","tbpMemo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpData || #request.defines['tbpData']!=null">
			 <td <s:if test="#request.defines['tbpData']>0">colspan="${defines["tbpData"]}" ${all?"width":"iwidth"}="${defines["tbpData"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="14" ref="tbpData"   title="程序数据">
			 	<a class="${desc=="tbp_data" ? "desc" : ""}${asc=="tbp_data" ? "asc" : ""}" href="?${desc=="tbp_data" ? "asc=tbp_data" : ""}${(asc=="tbp_data" || desc!="tbp_data" )? "desc=tbp_data" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	程序数据
			 	</a>
			 	${lz:set("checkeds[]","tbpData")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpUpdateTime || #request.defines['tbpUpdateTime']!=null">
			 <td <s:if test="#request.defines['tbpUpdateTime']>0">colspan="${defines["tbpUpdateTime"]}" ${all?"width":"iwidth"}="${defines["tbpUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="15" ref="tbpUpdateTime"   title="修改时间">
			 	<a class="${desc=="tbp_update_time" ? "desc" : ""}${asc=="tbp_update_time" ? "asc" : ""}" href="?${desc=="tbp_update_time" ? "asc=tbp_update_time" : ""}${(asc=="tbp_update_time" || desc!="tbp_update_time" )? "desc=tbp_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 	${lz:set("checkeds[]","tbpUpdateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpAddTime || #request.defines['tbpAddTime']!=null">
			 <td <s:if test="#request.defines['tbpAddTime']>0">colspan="${defines["tbpAddTime"]}" ${all?"width":"iwidth"}="${defines["tbpAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="16" ref="tbpAddTime"   title="添加时间">
			 	<a class="${desc=="tbp_add_time" ? "desc" : ""}${asc=="tbp_add_time" ? "asc" : ""}" href="?${desc=="tbp_add_time" ? "asc=tbp_add_time" : ""}${(asc=="tbp_add_time" || desc!="tbp_add_time" )? "desc=tbp_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","tbpAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpStatus || #request.defines['tbpStatus']!=null">
			 <td <s:if test="#request.defines['tbpStatus']>0">colspan="${defines["tbpStatus"]}" ${all?"width":"iwidth"}="${defines["tbpStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="17" ref="tbpStatus"   title="状态">
			 	<a class="${desc=="tbp_status" ? "desc" : ""}${asc=="tbp_status" ? "asc" : ""}" href="?${desc=="tbp_status" ? "asc=tbp_status" : ""}${(asc=="tbp_status" || desc!="tbp_status" )? "desc=tbp_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","tbpStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="18" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="19" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:AddProblem()" style="text-decoration:underline">添加</a>
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
		  <tr id="${item.tbpId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.tbpId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpId || #request.defines['tbpId']!=null">
				<s:if test="#request.defines['tbpId']>0">
					${lz:set("注释","****tbpId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpId",lz:indexOf(fieldName,"tbpId")>-1)}
				  		<s:if test="#request.atTbpId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpId字段的字串格式化输出****")}
					<td ref="tbpId" class="td ">
						 
						 	${lz:or(item$tbpId[i.count-1],lz:left(item.tbpId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpHost || #request.defines['tbpHost']!=null">
				<s:if test="#request.defines['tbpHost']>0">
					${lz:set("注释","****tbpHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpHost",lz:indexOf(fieldName,"tbpHost")>-1)}
				  		<s:if test="#request.atTbpHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpHost字段的字串格式化输出****")}
					<td ref="tbpHost" class="td " relate="${item.tbpHost}">
						 
						 	<a <s:if test="#item.tbpHost!=null && #item.tbpHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.tbpHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbpHost[i.count-1],lz:left(item.tbpHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpProfile || #request.defines['tbpProfile']!=null">
				<s:if test="#request.defines['tbpProfile']>0">
					${lz:set("注释","****tbpProfile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpProfile))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpProfile",lz:indexOf(fieldName,"tbpProfile")>-1)}
				  		<s:if test="#request.atTbpProfile==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpProfile字段的字串格式化输出****")}
					<td ref="tbpProfile" class="td  node">
						 
						 	${lz:or(item$tbpProfile[i.count-1],lz:left(item.tbpProfile$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpType || #request.defines['tbpType']!=null">
				<s:if test="#request.defines['tbpType']>0">
					${lz:set("注释","****tbpType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpType",lz:indexOf(fieldName,"tbpType")>-1)}
				  		<s:if test="#request.atTbpType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpType字段的字串格式化输出****")}
					<td ref="tbpType" class="td ">
						 
						 	${lz:or(item$tbpType[i.count-1],lz:left(item.tbpType$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpMember || #request.defines['tbpMember']!=null">
				<s:if test="#request.defines['tbpMember']>0">
					${lz:set("注释","****tbpMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpMember))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpMember",lz:indexOf(fieldName,"tbpMember")>-1)}
				  		<s:if test="#request.atTbpMember==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpMember字段的字串格式化输出****")}
					<td ref="tbpMember" class="td " relate="${item.tbpMember}">
						 
						 	<a <s:if test="#item.tbpMember!=null && #item.tbpMember!=''"> onclick="window.href('${basePath}${proname}/user/member_details.do?key=${item.tbpMember}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbpMember[i.count-1],lz:left(item.tbpMember$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpOrder || #request.defines['tbpOrder']!=null">
				<s:if test="#request.defines['tbpOrder']>0">
					${lz:set("注释","****tbpOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpOrder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpOrder",lz:indexOf(fieldName,"tbpOrder")>-1)}
				  		<s:if test="#request.atTbpOrder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpOrder字段的字串格式化输出****")}
					<td ref="tbpOrder" class="td " relate="${item.tbpOrder}">
						 
						 	<a <s:if test="#item.tbpOrder!=null && #item.tbpOrder!=''"> onclick="window.href('${basePath}${proname}/service/order_details.do?key=${item.tbpOrder}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbpOrder[i.count-1],lz:left(item.tbpOrder$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpRelate || #request.defines['tbpRelate']!=null">
				<s:if test="#request.defines['tbpRelate']>0">
					${lz:set("注释","****tbpRelate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpRelate))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpRelate",lz:indexOf(fieldName,"tbpRelate")>-1)}
				  		<s:if test="#request.atTbpRelate==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpRelate字段的字串格式化输出****")}
					<td ref="tbpRelate" class="td ">
						 
						 	${lz:or(item$tbpRelate[i.count-1],lz:left(item.tbpRelate$,100))}
					</td>
				</s:else>
			</s:if>
			
			<s:if test="#request.defines==null && #request.CTRL.l.tbpOutlets || #request.defines['tbpOutlets']!=null">
				<s:if test="#request.defines['tbpOutlets']>0">
					${lz:set("注释","****tbpOutlets关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpOutlets))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpOutlets",lz:indexOf(fieldName,"tbpOutlets")>-1)}
				  		<s:if test="#request.atTbpOutlets==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpOutlets字段的字串格式化输出****")}
					<td ref="tbpOutlets" class="td ">
						 
						 	${lz:or(item$tbpOutlets[i.count-1],lz:left(item.tbpOutlets$,100))}
					</td>
				</s:else>
			</s:if>
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpSpecialData || #request.defines['tbpSpecialData']!=null">
				<s:if test="#request.defines['tbpSpecialData']>0">
					${lz:set("注释","****tbpSpecialData关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpSpecialData))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpSpecialData",lz:indexOf(fieldName,"tbpSpecialData")>-1)}
				  		<s:if test="#request.atTbpSpecialData==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpSpecialData字段的字串格式化输出****")}
					<td ref="tbpOutlets" class="td ">
						 
						 	${lz:or(item$tbpSpecialData[i.count-1],lz:left(item.tbpSpecialData$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpAdder || #request.defines['tbpAdder']!=null">
				<s:if test="#request.defines['tbpAdder']>0">
					${lz:set("注释","****tbpAdder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpAdder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpAdder",lz:indexOf(fieldName,"tbpAdder")>-1)}
				  		<s:if test="#request.atTbpAdder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpAdder字段的字串格式化输出****")}
					<td ref="tbpAdder" class="td " relate="${item.tbpAdder}">
						 
						 	<a <s:if test="#item.tbpAdder!=null && #item.tbpAdder!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.tbpAdder}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbpAdder[i.count-1],lz:left(item.tbpAdder$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpEditor || #request.defines['tbpEditor']!=null">
				<s:if test="#request.defines['tbpEditor']>0">
					${lz:set("注释","****tbpEditor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpEditor))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpEditor",lz:indexOf(fieldName,"tbpEditor")>-1)}
				  		<s:if test="#request.atTbpEditor==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpEditor字段的字串格式化输出****")}
					<td ref="tbpEditor" class="td " relate="${item.tbpEditor}">
						 
						 	<a <s:if test="#item.tbpEditor!=null && #item.tbpEditor!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.tbpEditor}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbpEditor[i.count-1],lz:left(item.tbpEditor$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpDealer || #request.defines['tbpDealer']!=null">
				<s:if test="#request.defines['tbpDealer']>0">
					${lz:set("注释","****tbpDealer关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpDealer))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpDealer",lz:indexOf(fieldName,"tbpDealer")>-1)}
				  		<s:if test="#request.atTbpDealer==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpDealer字段的字串格式化输出****")}
					<td ref="tbpDealer" class="td " relate="${item.tbpDealer}">
						 
						 	<a <s:if test="#item.tbpDealer!=null && #item.tbpDealer!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.tbpDealer}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbpDealer[i.count-1],lz:left(item.tbpDealer$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpResult || #request.defines['tbpResult']!=null">
				<s:if test="#request.defines['tbpResult']>0">
					${lz:set("注释","****tbpResult关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpResult))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpResult",lz:indexOf(fieldName,"tbpResult")>-1)}
				  		<s:if test="#request.atTbpResult==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpResult字段的字串格式化输出****")}
					<td ref="tbpResult" class="td ">
						 
						 	${lz:or(item$tbpResult[i.count-1],lz:left(item.tbpResult$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpRemark || #request.defines['tbpRemark']!=null">
				<s:if test="#request.defines['tbpRemark']>0">
					${lz:set("注释","****tbpRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpRemark))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpRemark",lz:indexOf(fieldName,"tbpRemark")>-1)}
				  		<s:if test="#request.atTbpRemark==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpRemark字段的字串格式化输出****")}
					<td ref="tbpRemark" class="td ">
						 
						 	${lz:or(item$tbpRemark[i.count-1],lz:left(item.tbpRemark$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpMemo || #request.defines['tbpMemo']!=null">
				<s:if test="#request.defines['tbpMemo']>0">
					${lz:set("注释","****tbpMemo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpMemo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpMemo",lz:indexOf(fieldName,"tbpMemo")>-1)}
				  		<s:if test="#request.atTbpMemo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpMemo字段的字串格式化输出****")}
					<td ref="tbpMemo" class="td ">
						 
						 	${lz:or(item$tbpMemo[i.count-1],lz:left(item.tbpMemo$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpData || #request.defines['tbpData']!=null">
				<s:if test="#request.defines['tbpData']>0">
					${lz:set("注释","****tbpData关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpData))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpData",lz:indexOf(fieldName,"tbpData")>-1)}
				  		<s:if test="#request.atTbpData==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpData字段的字串格式化输出****")}
					<td ref="tbpData" class="td ">
						 
						 	${lz:or(item$tbpData[i.count-1],lz:left(item.tbpData$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpUpdateTime || #request.defines['tbpUpdateTime']!=null">
				<s:if test="#request.defines['tbpUpdateTime']>0">
					${lz:set("注释","****tbpUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpUpdateTime",lz:indexOf(fieldName,"tbpUpdateTime")>-1)}
				  		<s:if test="#request.atTbpUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpUpdateTime字段的字串格式化输出****")}
					<td ref="tbpUpdateTime" class="td ">
						 
						 	${lz:or(item$tbpUpdateTime[i.count-1],lz:left(item.tbpUpdateTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpAddTime || #request.defines['tbpAddTime']!=null">
				<s:if test="#request.defines['tbpAddTime']>0">
					${lz:set("注释","****tbpAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpAddTime",lz:indexOf(fieldName,"tbpAddTime")>-1)}
				  		<s:if test="#request.atTbpAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpAddTime字段的字串格式化输出****")}
					<td ref="tbpAddTime" class="td ">
						 
						 	${lz:or(item$tbpAddTime[i.count-1],lz:left(item.tbpAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpStatus || #request.defines['tbpStatus']!=null">
				<s:if test="#request.defines['tbpStatus']>0">
					${lz:set("注释","****tbpStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpStatus",lz:indexOf(fieldName,"tbpStatus")>-1)}
				  		<s:if test="#request.atTbpStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpStatus字段的字串格式化输出****")}
					<td ref="tbpStatus" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.tbpId}','status')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$tbpStatus[i.count-1],lz:left(item.tbpStatus$,100))}
					</td>
				</s:else>
			</s:if>
		   ${lz:set("注释","*****************数据列表数据部分结束*****************")}		  
		  
		   
		    ${lz:set("注释","****自定义的单行记录操作句柄[request中的handles(String[])变量]****")}
			<td class="options">
				  <s:if test="#request.alias==null">	
					<s:if test="#request.CTRL.canExp[0]==true">
					<a href="javascript:HandleSel('流转','${item.tbpId}')">流转</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[1]==true">
					<a href="javascript:HandleSel('完成','${item.tbpId}')">完成</a>
					</s:if>
				</s:if>
				${lz:set("items",lz:split(handles,","))}
				<s:iterator value="#request.items" id="handle" status="i">
					${lz:set("isMenu",(lz:indexOf(handle,"@")==0))}
					<s:if test="#request.isMenu!=true">
					<a href="javascript:HandleSel('${handle}','${item.tbpId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectProblem('${item.tbpId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditProblem('${item.tbpId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelProblem('${item.tbpId}','${item.tbpProfile}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsProblem('${item.tbpId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.tbpId || #request.defines['tbpId']!=null">
				<s:if test="#request.defines['tbpId']>0">
					${lz:set("注释","****tbpId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpId",lz:indexOf(fieldName,"tbpId")>-1)}				  		
				  		<s:if test="#request.atTbpId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpHost || #request.defines['tbpHost']!=null">
				<s:if test="#request.defines['tbpHost']>0">
					${lz:set("注释","****tbpHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpHost",lz:indexOf(fieldName,"tbpHost")>-1)}				  		
				  		<s:if test="#request.atTbpHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpProfile || #request.defines['tbpProfile']!=null">
				<s:if test="#request.defines['tbpProfile']>0">
					${lz:set("注释","****tbpProfile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpProfile",lz:indexOf(fieldName,"tbpProfile")>-1)}				  		
				  		<s:if test="#request.atTbpProfile==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpProfile[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpType || #request.defines['tbpType']!=null">
				<s:if test="#request.defines['tbpType']>0">
					${lz:set("注释","****tbpType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpType",lz:indexOf(fieldName,"tbpType")>-1)}				  		
				  		<s:if test="#request.atTbpType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpMember || #request.defines['tbpMember']!=null">
				<s:if test="#request.defines['tbpMember']>0">
					${lz:set("注释","****tbpMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpMember",lz:indexOf(fieldName,"tbpMember")>-1)}				  		
				  		<s:if test="#request.atTbpMember==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpMember[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpOrder || #request.defines['tbpOrder']!=null">
				<s:if test="#request.defines['tbpOrder']>0">
					${lz:set("注释","****tbpOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpOrder",lz:indexOf(fieldName,"tbpOrder")>-1)}				  		
				  		<s:if test="#request.atTbpOrder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpOrder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpRelate || #request.defines['tbpRelate']!=null">
				<s:if test="#request.defines['tbpRelate']>0">
					${lz:set("注释","****tbpRelate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpRelate",lz:indexOf(fieldName,"tbpRelate")>-1)}				  		
				  		<s:if test="#request.atTbpRelate==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpRelate[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpOutlets || #request.defines['tbpOutlets']!=null">
				<s:if test="#request.defines['tbpOutlets']>0">
					${lz:set("注释","****tbpOutlets关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpRelate",lz:indexOf(fieldName,"tbpOutlets")>-1)}				  		
				  		<s:if test="#request.atTbpOutlets==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpRelate[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpSpecialData || #request.defines['tbpSpecialData']!=null">
				<s:if test="#request.defines['tbpSpecialData']>0">
					${lz:set("注释","****tbpSpecialData关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpRelate",lz:indexOf(fieldName,"tbpOutlets")>-1)}				  		
				  		<s:if test="#request.atTbpSpecialData==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpRelate[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpAdder || #request.defines['tbpAdder']!=null">
				<s:if test="#request.defines['tbpAdder']>0">
					${lz:set("注释","****tbpAdder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpAdder",lz:indexOf(fieldName,"tbpAdder")>-1)}				  		
				  		<s:if test="#request.atTbpAdder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpAdder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpEditor || #request.defines['tbpEditor']!=null">
				<s:if test="#request.defines['tbpEditor']>0">
					${lz:set("注释","****tbpEditor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpEditor",lz:indexOf(fieldName,"tbpEditor")>-1)}				  		
				  		<s:if test="#request.atTbpEditor==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpEditor[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpDealer || #request.defines['tbpDealer']!=null">
				<s:if test="#request.defines['tbpDealer']>0">
					${lz:set("注释","****tbpDealer关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpDealer",lz:indexOf(fieldName,"tbpDealer")>-1)}				  		
				  		<s:if test="#request.atTbpDealer==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpDealer[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpResult || #request.defines['tbpResult']!=null">
				<s:if test="#request.defines['tbpResult']>0">
					${lz:set("注释","****tbpResult关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpResult",lz:indexOf(fieldName,"tbpResult")>-1)}				  		
				  		<s:if test="#request.atTbpResult==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpResult[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpRemark || #request.defines['tbpRemark']!=null">
				<s:if test="#request.defines['tbpRemark']>0">
					${lz:set("注释","****tbpRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpRemark",lz:indexOf(fieldName,"tbpRemark")>-1)}				  		
				  		<s:if test="#request.atTbpRemark==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpRemark[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpMemo || #request.defines['tbpMemo']!=null">
				<s:if test="#request.defines['tbpMemo']>0">
					${lz:set("注释","****tbpMemo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpMemo",lz:indexOf(fieldName,"tbpMemo")>-1)}				  		
				  		<s:if test="#request.atTbpMemo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpMemo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpData || #request.defines['tbpData']!=null">
				<s:if test="#request.defines['tbpData']>0">
					${lz:set("注释","****tbpData关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpData",lz:indexOf(fieldName,"tbpData")>-1)}				  		
				  		<s:if test="#request.atTbpData==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpData[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpUpdateTime || #request.defines['tbpUpdateTime']!=null">
				<s:if test="#request.defines['tbpUpdateTime']>0">
					${lz:set("注释","****tbpUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpUpdateTime",lz:indexOf(fieldName,"tbpUpdateTime")>-1)}				  		
				  		<s:if test="#request.atTbpUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpAddTime || #request.defines['tbpAddTime']!=null">
				<s:if test="#request.defines['tbpAddTime']>0">
					${lz:set("注释","****tbpAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpAddTime",lz:indexOf(fieldName,"tbpAddTime")>-1)}				  		
				  		<s:if test="#request.atTbpAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpStatus || #request.defines['tbpStatus']!=null">
				<s:if test="#request.defines['tbpStatus']>0">
					${lz:set("注释","****tbpStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpStatus",lz:indexOf(fieldName,"tbpStatus")>-1)}				  		
				  		<s:if test="#request.atTbpStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 添加问题故障报修
	**/
	function AddProblem(parent){		
		var url = "${basePath}${namespace}problem_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加问题故障报修'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择问题故障报修
	**/
	function SelectProblem(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改问题故障报修
	**/
	function EditProblem(id){
		var url = "${basePath}${namespace}problem_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改问题故障报修'}":lz:json(ctrl)}};
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
		EditProblem(checker.val());
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
				var url = "${basePath}${namespace}problem_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新问题故障报修",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}problem_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新问题故障报修",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改问题故障报修
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}problem_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新问题故障报修",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["status"]={};
	fieldNames["status"]["tbpStatus"]=true;
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改问题故障报修任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}problem_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新问题故障报修",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}problem_edit.do";
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
			var url = "${basePath}${namespace}problem_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除问题故障报修
	**/
	function DelProblem(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除问题故障报修["+flag+"]吗？</span>",
				title:"删除问题故障报修",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}problem_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选问题故障报修吗？</span>",
				title:"批量删除问题故障报修",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}problem_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示问题故障报修详情
	**/
	function DetailsProblem(id)
	{
		var url = "${basePath}${namespace}problem_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"问题故障报修详情",editable:false,visible:true}};
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
		DetailsProblem(checker.val());
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