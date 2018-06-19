<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/oa/hr/passcheck.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty tbPassCheck.tbpcId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"tbpcId,tbpcHost,tbpcTitle,tbpcEmployee,tbpcOutTime,tbpcRemark,tbpcStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"tbpcId,tbpcHost,tbpcTitle,tbpcEmployee,tbpcInPos,tbpcInDistance,tbpcInTime,tbpcOutPos,tbpcOutDistance,tbpcOutTime,tbpcTimeLong,tbpcRemark,tbpcStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"tbpcId,tbpcTitle,tbpcEmployee,tbpcOutTime",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"tbpcId,tbpcTitle,tbpcEmployee,tbpcInDistance,tbpcInTime,tbpcOutDistance,tbpcOutTime,tbpcTimeLong",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>上下班签到管理</title>
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
	<span class="caption">${empty title?"上下班签到管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="TbPassCheck" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="passcheck.query.jsp"%>

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
			<dl ref="com.ccclubs.model.TbPassCheck" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.TbPassCheck" class="batch dropdowan">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/20088256421529677807.png"/>
					批量修改
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
					<s:if test="#request.defines==null && #request.CTRL.l.tbpcStatus || #request.defines['tbpcStatus']!=null">
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
		<table id="passcheck_table" ref="TbPassCheck" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpcId || #request.defines['tbpcId']!=null">
			 <td <s:if test="#request.defines['tbpcId']>0">colspan="${defines["tbpcId"]}" ${all?"width":"iwidth"}="${defines["tbpcId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="tbpcId"   title="编号">
			 	<a class="${desc=="tbpc_id" ? "desc" : ""}${asc=="tbpc_id" ? "asc" : ""}" href="?${desc=="tbpc_id" ? "asc=tbpc_id" : ""}${(asc=="tbpc_id" || desc!="tbpc_id" )? "desc=tbpc_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","tbpcId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpcHost || #request.defines['tbpcHost']!=null">
			 <td <s:if test="#request.defines['tbpcHost']>0">colspan="${defines["tbpcHost"]}" ${all?"width":"iwidth"}="${defines["tbpcHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="tbpcHost"   title="城市">
			 	<a class="${desc=="tbpc_host" ? "desc" : ""}${asc=="tbpc_host" ? "asc" : ""}" href="?${desc=="tbpc_host" ? "asc=tbpc_host" : ""}${(asc=="tbpc_host" || desc!="tbpc_host" )? "desc=tbpc_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","tbpcHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpcTitle || #request.defines['tbpcTitle']!=null">
			 <td <s:if test="#request.defines['tbpcTitle']>0">colspan="${defines["tbpcTitle"]}" ${all?"width":"iwidth"}="${defines["tbpcTitle"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="3" ref="tbpcTitle" flagKey  title="签到描述">
			 	<a class="${desc=="tbpc_title" ? "desc" : ""}${asc=="tbpc_title" ? "asc" : ""}" href="?${desc=="tbpc_title" ? "asc=tbpc_title" : ""}${(asc=="tbpc_title" || desc!="tbpc_title" )? "desc=tbpc_title" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	签到描述
			 	</a>
			 	${lz:set("checkeds[]","tbpcTitle")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpcEmployee || #request.defines['tbpcEmployee']!=null">
			 <td <s:if test="#request.defines['tbpcEmployee']>0">colspan="${defines["tbpcEmployee"]}" ${all?"width":"iwidth"}="${defines["tbpcEmployee"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="tbpcEmployee"   title="员工">
			 	<a class="${desc=="tbpc_employee" ? "desc" : ""}${asc=="tbpc_employee" ? "asc" : ""}" href="?${desc=="tbpc_employee" ? "asc=tbpc_employee" : ""}${(asc=="tbpc_employee" || desc!="tbpc_employee" )? "desc=tbpc_employee" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	员工
			 	</a>
			 	${lz:set("checkeds[]","tbpcEmployee")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpcInPos || #request.defines['tbpcInPos']!=null">
			 <td <s:if test="#request.defines['tbpcInPos']>0">colspan="${defines["tbpcInPos"]}" ${all?"width":"iwidth"}="${defines["tbpcInPos"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="5" ref="tbpcInPos"   title="签入位置">
			 	<a class="${desc=="tbpc_in_pos" ? "desc" : ""}${asc=="tbpc_in_pos" ? "asc" : ""}" href="?${desc=="tbpc_in_pos" ? "asc=tbpc_in_pos" : ""}${(asc=="tbpc_in_pos" || desc!="tbpc_in_pos" )? "desc=tbpc_in_pos" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	签入位置
			 	</a>
			 	${lz:set("checkeds[]","tbpcInPos")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpcInDistance || #request.defines['tbpcInDistance']!=null">
			 <td <s:if test="#request.defines['tbpcInDistance']>0">colspan="${defines["tbpcInDistance"]}" ${all?"width":"iwidth"}="${defines["tbpcInDistance"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="tbpcInDistance"   title="签入距离(米)">
			 	<a class="${desc=="tbpc_in_distance" ? "desc" : ""}${asc=="tbpc_in_distance" ? "asc" : ""}" href="?${desc=="tbpc_in_distance" ? "asc=tbpc_in_distance" : ""}${(asc=="tbpc_in_distance" || desc!="tbpc_in_distance" )? "desc=tbpc_in_distance" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	签入距离(米)
			 	</a>
			 	${lz:set("checkeds[]","tbpcInDistance")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpcInTime || #request.defines['tbpcInTime']!=null">
			 <td <s:if test="#request.defines['tbpcInTime']>0">colspan="${defines["tbpcInTime"]}" ${all?"width":"iwidth"}="${defines["tbpcInTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="7" ref="tbpcInTime"   title="签入时间">
			 	<a class="${desc=="tbpc_in_time" ? "desc" : ""}${asc=="tbpc_in_time" ? "asc" : ""}" href="?${desc=="tbpc_in_time" ? "asc=tbpc_in_time" : ""}${(asc=="tbpc_in_time" || desc!="tbpc_in_time" )? "desc=tbpc_in_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	签入时间
			 	</a>
			 	${lz:set("checkeds[]","tbpcInTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpcOutPos || #request.defines['tbpcOutPos']!=null">
			 <td <s:if test="#request.defines['tbpcOutPos']>0">colspan="${defines["tbpcOutPos"]}" ${all?"width":"iwidth"}="${defines["tbpcOutPos"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="8" ref="tbpcOutPos"   title="签出位置">
			 	<a class="${desc=="tbpc_out_pos" ? "desc" : ""}${asc=="tbpc_out_pos" ? "asc" : ""}" href="?${desc=="tbpc_out_pos" ? "asc=tbpc_out_pos" : ""}${(asc=="tbpc_out_pos" || desc!="tbpc_out_pos" )? "desc=tbpc_out_pos" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	签出位置
			 	</a>
			 	${lz:set("checkeds[]","tbpcOutPos")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpcOutDistance || #request.defines['tbpcOutDistance']!=null">
			 <td <s:if test="#request.defines['tbpcOutDistance']>0">colspan="${defines["tbpcOutDistance"]}" ${all?"width":"iwidth"}="${defines["tbpcOutDistance"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="9" ref="tbpcOutDistance"   title="签出距离(米)">
			 	<a class="${desc=="tbpc_out_distance" ? "desc" : ""}${asc=="tbpc_out_distance" ? "asc" : ""}" href="?${desc=="tbpc_out_distance" ? "asc=tbpc_out_distance" : ""}${(asc=="tbpc_out_distance" || desc!="tbpc_out_distance" )? "desc=tbpc_out_distance" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	签出距离(米)
			 	</a>
			 	${lz:set("checkeds[]","tbpcOutDistance")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpcOutTime || #request.defines['tbpcOutTime']!=null">
			 <td <s:if test="#request.defines['tbpcOutTime']>0">colspan="${defines["tbpcOutTime"]}" ${all?"width":"iwidth"}="${defines["tbpcOutTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="10" ref="tbpcOutTime"   title="签出时间">
			 	<a class="${desc=="tbpc_out_time" ? "desc" : ""}${asc=="tbpc_out_time" ? "asc" : ""}" href="?${desc=="tbpc_out_time" ? "asc=tbpc_out_time" : ""}${(asc=="tbpc_out_time" || desc!="tbpc_out_time" )? "desc=tbpc_out_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	签出时间
			 	</a>
			 	${lz:set("checkeds[]","tbpcOutTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpcTimeLong || #request.defines['tbpcTimeLong']!=null">
			 <td <s:if test="#request.defines['tbpcTimeLong']>0">colspan="${defines["tbpcTimeLong"]}" ${all?"width":"iwidth"}="${defines["tbpcTimeLong"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="11" ref="tbpcTimeLong"   title="作业时长(分)">
			 	<a class="${desc=="tbpc_time_long" ? "desc" : ""}${asc=="tbpc_time_long" ? "asc" : ""}" href="?${desc=="tbpc_time_long" ? "asc=tbpc_time_long" : ""}${(asc=="tbpc_time_long" || desc!="tbpc_time_long" )? "desc=tbpc_time_long" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	作业时长(分)
			 	</a>
			 	${lz:set("checkeds[]","tbpcTimeLong")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpcRemark || #request.defines['tbpcRemark']!=null">
			 <td <s:if test="#request.defines['tbpcRemark']>0">colspan="${defines["tbpcRemark"]}" ${all?"width":"iwidth"}="${defines["tbpcRemark"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="12" ref="tbpcRemark"   title="备注信息">
			 	<a class="${desc=="tbpc_remark" ? "desc" : ""}${asc=="tbpc_remark" ? "asc" : ""}" href="?${desc=="tbpc_remark" ? "asc=tbpc_remark" : ""}${(asc=="tbpc_remark" || desc!="tbpc_remark" )? "desc=tbpc_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备注信息
			 	</a>
			 	${lz:set("checkeds[]","tbpcRemark")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbpcStatus || #request.defines['tbpcStatus']!=null">
			 <td <s:if test="#request.defines['tbpcStatus']>0">colspan="${defines["tbpcStatus"]}" ${all?"width":"iwidth"}="${defines["tbpcStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="13" ref="tbpcStatus"   title="状态">
			 	<a class="${desc=="tbpc_status" ? "desc" : ""}${asc=="tbpc_status" ? "asc" : ""}" href="?${desc=="tbpc_status" ? "asc=tbpc_status" : ""}${(asc=="tbpc_status" || desc!="tbpc_status" )? "desc=tbpc_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","tbpcStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="14" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="15" ref="operation">
					&nbsp;
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
		  <tr id="${item.tbpcId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.tbpcId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcId || #request.defines['tbpcId']!=null">
				<s:if test="#request.defines['tbpcId']>0">
					${lz:set("注释","****tbpcId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpcId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpcId",lz:indexOf(fieldName,"tbpcId")>-1)}
				  		<s:if test="#request.atTbpcId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpcId字段的字串格式化输出****")}
					<td ref="tbpcId" class="td ">
						 
						 	${lz:or(item$tbpcId[i.count-1],lz:left(item.tbpcId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcHost || #request.defines['tbpcHost']!=null">
				<s:if test="#request.defines['tbpcHost']>0">
					${lz:set("注释","****tbpcHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpcHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpcHost",lz:indexOf(fieldName,"tbpcHost")>-1)}
				  		<s:if test="#request.atTbpcHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpcHost字段的字串格式化输出****")}
					<td ref="tbpcHost" class="td " relate="${item.tbpcHost}">
						 
						 	<a <s:if test="#item.tbpcHost!=null && #item.tbpcHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.tbpcHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbpcHost[i.count-1],lz:left(item.tbpcHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcTitle || #request.defines['tbpcTitle']!=null">
				<s:if test="#request.defines['tbpcTitle']>0">
					${lz:set("注释","****tbpcTitle关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpcTitle))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpcTitle",lz:indexOf(fieldName,"tbpcTitle")>-1)}
				  		<s:if test="#request.atTbpcTitle==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpcTitle字段的字串格式化输出****")}
					<td ref="tbpcTitle" class="td  node">
						 
						 	${lz:or(item$tbpcTitle[i.count-1],lz:left(item.tbpcTitle$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcEmployee || #request.defines['tbpcEmployee']!=null">
				<s:if test="#request.defines['tbpcEmployee']>0">
					${lz:set("注释","****tbpcEmployee关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpcEmployee))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpcEmployee",lz:indexOf(fieldName,"tbpcEmployee")>-1)}
				  		<s:if test="#request.atTbpcEmployee==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpcEmployee字段的字串格式化输出****")}
					<td ref="tbpcEmployee" class="td " relate="${item.tbpcEmployee}">
						 
						 	<a <s:if test="#item.tbpcEmployee!=null && #item.tbpcEmployee!=''"> onclick="window.href('${basePath}${proname}/oa/hr/employee_details.do?key=${item.tbpcEmployee}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbpcEmployee[i.count-1],lz:left(item.tbpcEmployee$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcInPos || #request.defines['tbpcInPos']!=null">
				<s:if test="#request.defines['tbpcInPos']>0">
					${lz:set("注释","****tbpcInPos关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpcInPos))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpcInPos",lz:indexOf(fieldName,"tbpcInPos")>-1)}
				  		<s:if test="#request.atTbpcInPos==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpcInPos字段的字串格式化输出****")}
					<td ref="tbpcInPos" class="td ">
						 
						 	${lz:or(item$tbpcInPos[i.count-1],lz:left(item.tbpcInPos$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcInDistance || #request.defines['tbpcInDistance']!=null">
				<s:if test="#request.defines['tbpcInDistance']>0">
					${lz:set("注释","****tbpcInDistance关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpcInDistance))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpcInDistance",lz:indexOf(fieldName,"tbpcInDistance")>-1)}
				  		<s:if test="#request.atTbpcInDistance==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpcInDistance字段的字串格式化输出****")}
					<td ref="tbpcInDistance" class="td ">
						 
						 	${lz:or(item$tbpcInDistance[i.count-1],lz:left(item.tbpcInDistance$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcInTime || #request.defines['tbpcInTime']!=null">
				<s:if test="#request.defines['tbpcInTime']>0">
					${lz:set("注释","****tbpcInTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpcInTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpcInTime",lz:indexOf(fieldName,"tbpcInTime")>-1)}
				  		<s:if test="#request.atTbpcInTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpcInTime字段的字串格式化输出****")}
					<td ref="tbpcInTime" class="td ">
						 
						 	${lz:or(item$tbpcInTime[i.count-1],lz:left(item.tbpcInTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcOutPos || #request.defines['tbpcOutPos']!=null">
				<s:if test="#request.defines['tbpcOutPos']>0">
					${lz:set("注释","****tbpcOutPos关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpcOutPos))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpcOutPos",lz:indexOf(fieldName,"tbpcOutPos")>-1)}
				  		<s:if test="#request.atTbpcOutPos==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpcOutPos字段的字串格式化输出****")}
					<td ref="tbpcOutPos" class="td ">
						 
						 	${lz:or(item$tbpcOutPos[i.count-1],lz:left(item.tbpcOutPos$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcOutDistance || #request.defines['tbpcOutDistance']!=null">
				<s:if test="#request.defines['tbpcOutDistance']>0">
					${lz:set("注释","****tbpcOutDistance关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpcOutDistance))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpcOutDistance",lz:indexOf(fieldName,"tbpcOutDistance")>-1)}
				  		<s:if test="#request.atTbpcOutDistance==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpcOutDistance字段的字串格式化输出****")}
					<td ref="tbpcOutDistance" class="td ">
						 
						 	${lz:or(item$tbpcOutDistance[i.count-1],lz:left(item.tbpcOutDistance$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcOutTime || #request.defines['tbpcOutTime']!=null">
				<s:if test="#request.defines['tbpcOutTime']>0">
					${lz:set("注释","****tbpcOutTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpcOutTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpcOutTime",lz:indexOf(fieldName,"tbpcOutTime")>-1)}
				  		<s:if test="#request.atTbpcOutTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpcOutTime字段的字串格式化输出****")}
					<td ref="tbpcOutTime" class="td ">
						 
						 	${lz:or(item$tbpcOutTime[i.count-1],lz:left(item.tbpcOutTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcTimeLong || #request.defines['tbpcTimeLong']!=null">
				<s:if test="#request.defines['tbpcTimeLong']>0">
					${lz:set("注释","****tbpcTimeLong关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpcTimeLong))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpcTimeLong",lz:indexOf(fieldName,"tbpcTimeLong")>-1)}
				  		<s:if test="#request.atTbpcTimeLong==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpcTimeLong字段的字串格式化输出****")}
					<td ref="tbpcTimeLong" class="td ">
						 
						 	${lz:or(item$tbpcTimeLong[i.count-1],lz:left(item.tbpcTimeLong$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcRemark || #request.defines['tbpcRemark']!=null">
				<s:if test="#request.defines['tbpcRemark']>0">
					${lz:set("注释","****tbpcRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpcRemark))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpcRemark",lz:indexOf(fieldName,"tbpcRemark")>-1)}
				  		<s:if test="#request.atTbpcRemark==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpcRemark字段的字串格式化输出****")}
					<td ref="tbpcRemark" class="td ">
						 
						 	${lz:or(item$tbpcRemark[i.count-1],lz:left(item.tbpcRemark$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcStatus || #request.defines['tbpcStatus']!=null">
				<s:if test="#request.defines['tbpcStatus']>0">
					${lz:set("注释","****tbpcStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbpcStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbpcStatus",lz:indexOf(fieldName,"tbpcStatus")>-1)}
				  		<s:if test="#request.atTbpcStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbpcStatus字段的字串格式化输出****")}
					<td ref="tbpcStatus" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.tbpcId}','status')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$tbpcStatus[i.count-1],lz:left(item.tbpcStatus$,100))}
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
					<a href="javascript:HandleSel('${handle}','${item.tbpcId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectPasscheck('${item.tbpcId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditPasscheck('${item.tbpcId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelPasscheck('${item.tbpcId}','${item.tbpcTitle}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsPasscheck('${item.tbpcId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcId || #request.defines['tbpcId']!=null">
				<s:if test="#request.defines['tbpcId']>0">
					${lz:set("注释","****tbpcId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpcId",lz:indexOf(fieldName,"tbpcId")>-1)}				  		
				  		<s:if test="#request.atTbpcId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpcId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcHost || #request.defines['tbpcHost']!=null">
				<s:if test="#request.defines['tbpcHost']>0">
					${lz:set("注释","****tbpcHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpcHost",lz:indexOf(fieldName,"tbpcHost")>-1)}				  		
				  		<s:if test="#request.atTbpcHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpcHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcTitle || #request.defines['tbpcTitle']!=null">
				<s:if test="#request.defines['tbpcTitle']>0">
					${lz:set("注释","****tbpcTitle关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpcTitle",lz:indexOf(fieldName,"tbpcTitle")>-1)}				  		
				  		<s:if test="#request.atTbpcTitle==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpcTitle[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcEmployee || #request.defines['tbpcEmployee']!=null">
				<s:if test="#request.defines['tbpcEmployee']>0">
					${lz:set("注释","****tbpcEmployee关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpcEmployee",lz:indexOf(fieldName,"tbpcEmployee")>-1)}				  		
				  		<s:if test="#request.atTbpcEmployee==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpcEmployee[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcInPos || #request.defines['tbpcInPos']!=null">
				<s:if test="#request.defines['tbpcInPos']>0">
					${lz:set("注释","****tbpcInPos关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpcInPos",lz:indexOf(fieldName,"tbpcInPos")>-1)}				  		
				  		<s:if test="#request.atTbpcInPos==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpcInPos[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcInDistance || #request.defines['tbpcInDistance']!=null">
				<s:if test="#request.defines['tbpcInDistance']>0">
					${lz:set("注释","****tbpcInDistance关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpcInDistance",lz:indexOf(fieldName,"tbpcInDistance")>-1)}				  		
				  		<s:if test="#request.atTbpcInDistance==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpcInDistance[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcInTime || #request.defines['tbpcInTime']!=null">
				<s:if test="#request.defines['tbpcInTime']>0">
					${lz:set("注释","****tbpcInTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpcInTime",lz:indexOf(fieldName,"tbpcInTime")>-1)}				  		
				  		<s:if test="#request.atTbpcInTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpcInTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcOutPos || #request.defines['tbpcOutPos']!=null">
				<s:if test="#request.defines['tbpcOutPos']>0">
					${lz:set("注释","****tbpcOutPos关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpcOutPos",lz:indexOf(fieldName,"tbpcOutPos")>-1)}				  		
				  		<s:if test="#request.atTbpcOutPos==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpcOutPos[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcOutDistance || #request.defines['tbpcOutDistance']!=null">
				<s:if test="#request.defines['tbpcOutDistance']>0">
					${lz:set("注释","****tbpcOutDistance关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpcOutDistance",lz:indexOf(fieldName,"tbpcOutDistance")>-1)}				  		
				  		<s:if test="#request.atTbpcOutDistance==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpcOutDistance[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcOutTime || #request.defines['tbpcOutTime']!=null">
				<s:if test="#request.defines['tbpcOutTime']>0">
					${lz:set("注释","****tbpcOutTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpcOutTime",lz:indexOf(fieldName,"tbpcOutTime")>-1)}				  		
				  		<s:if test="#request.atTbpcOutTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpcOutTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcTimeLong || #request.defines['tbpcTimeLong']!=null">
				<s:if test="#request.defines['tbpcTimeLong']>0">
					${lz:set("注释","****tbpcTimeLong关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpcTimeLong",lz:indexOf(fieldName,"tbpcTimeLong")>-1)}				  		
				  		<s:if test="#request.atTbpcTimeLong==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpcTimeLong[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcRemark || #request.defines['tbpcRemark']!=null">
				<s:if test="#request.defines['tbpcRemark']>0">
					${lz:set("注释","****tbpcRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpcRemark",lz:indexOf(fieldName,"tbpcRemark")>-1)}				  		
				  		<s:if test="#request.atTbpcRemark==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpcRemark[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbpcStatus || #request.defines['tbpcStatus']!=null">
				<s:if test="#request.defines['tbpcStatus']>0">
					${lz:set("注释","****tbpcStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbpcStatus",lz:indexOf(fieldName,"tbpcStatus")>-1)}				  		
				  		<s:if test="#request.atTbpcStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbpcStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
<s:if test="#request.alias=='select'">
	/**
	* 选择上下班签到
	**/
	function SelectPasscheck(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改上下班签到
	**/
	function EditPasscheck(id){
		var url = "${basePath}${namespace}passcheck_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改上下班签到'}":lz:json(ctrl)}};
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
		EditPasscheck(checker.val());
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
				var url = "${basePath}${namespace}passcheck_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新上下班签到",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}passcheck_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新上下班签到",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改上下班签到
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}passcheck_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新上下班签到",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["status"]={};
	fieldNames["status"]["tbpcStatus"]=true;
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改上下班签到任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}passcheck_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新上下班签到",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}passcheck_edit.do";
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
			var url = "${basePath}${namespace}passcheck_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除上下班签到
	**/
	function DelPasscheck(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除上下班签到["+flag+"]吗？</span>",
				title:"删除上下班签到",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}passcheck_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选上下班签到吗？</span>",
				title:"批量删除上下班签到",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}passcheck_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示上下班签到详情
	**/
	function DetailsPasscheck(id)
	{
		var url = "${basePath}${namespace}passcheck_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"上下班签到详情",editable:false,visible:true}};
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
		DetailsPasscheck(checker.val());
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