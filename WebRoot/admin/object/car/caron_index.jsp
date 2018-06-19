<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/object/car/caron.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
${lz:set("canView",true)}
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csCarOn.cscoId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"cscoId,cscoHost,cscoCar,cscoOnUser,cscoOffUser,cscoOnTime,cscoOffTime,cscoLong,cscoRemark,cscoStatus,cscoOnRemark,cscoProperty",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"cscoId,cscoHost,cscoCar,cscoOnUser,cscoOffUser,cscoOnTime,cscoOffTime,cscoLong,cscoRemark,cscoStatus,cscoOnRemark,cscoProperty",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"cscoHost,cscoCar,cscoOnUser,cscoOffUser,cscoOnTime,cscoOffTime,cscoLong,cscoRemark,cscoOnRemark,cscoProperty",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"cscoHost,cscoCar,cscoOnUser,cscoOffUser,cscoOnTime,cscoOffTime,cscoLong,cscoRemark,cscoOnRemark,cscoProperty",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>车辆上线记录管理</title>
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
	<span class="caption">${empty title?"车辆上线记录管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsCarOn" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="caron.query.jsp"%>

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
			<dl ref="com.ccclubs.model.CsCarOn" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.CsCarOn" class="batch dropdowan">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/20088256421529677807.png"/>
					批量修改
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
					<s:if test="#request.defines==null && #request.CTRL.l.cscoStatus || #request.defines['cscoStatus']!=null">
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
		<table id="caron_table" ref="CsCarOn" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscoId || #request.defines['cscoId']!=null">
			 <td <s:if test="#request.defines['cscoId']>0">colspan="${defines["cscoId"]}" ${all?"width":"iwidth"}="${defines["cscoId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="cscoId"   title="编号">
			 	<a class="${desc=="csco_id" ? "desc" : ""}${asc=="csco_id" ? "asc" : ""}" href="?${desc=="csco_id" ? "asc=csco_id" : ""}${(asc=="csco_id" || desc!="csco_id" )? "desc=csco_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","cscoId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscoHost || #request.defines['cscoHost']!=null">
			 <td <s:if test="#request.defines['cscoHost']>0">colspan="${defines["cscoHost"]}" ${all?"width":"iwidth"}="${defines["cscoHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="cscoHost"   title="城市">
			 	<a class="${desc=="csco_host" ? "desc" : ""}${asc=="csco_host" ? "asc" : ""}" href="?${desc=="csco_host" ? "asc=csco_host" : ""}${(asc=="csco_host" || desc!="csco_host" )? "desc=csco_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","cscoHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscoCar || #request.defines['cscoCar']!=null">
			 <td <s:if test="#request.defines['cscoCar']>0">colspan="${defines["cscoCar"]}" ${all?"width":"iwidth"}="${defines["cscoCar"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="3" ref="cscoCar" flagKey  title="所属车辆">
			 	<a class="${desc=="csco_car" ? "desc" : ""}${asc=="csco_car" ? "asc" : ""}" href="?${desc=="csco_car" ? "asc=csco_car" : ""}${(asc=="csco_car" || desc!="csco_car" )? "desc=csco_car" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属车辆
			 	</a>
			 	${lz:set("checkeds[]","cscoCar")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscoOnUser || #request.defines['cscoOnUser']!=null">
			 <td <s:if test="#request.defines['cscoOnUser']>0">colspan="${defines["cscoOnUser"]}" ${all?"width":"iwidth"}="${defines["cscoOnUser"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="cscoOnUser"   title="上线操作人">
			 	<a class="${desc=="csco_on_user" ? "desc" : ""}${asc=="csco_on_user" ? "asc" : ""}" href="?${desc=="csco_on_user" ? "asc=csco_on_user" : ""}${(asc=="csco_on_user" || desc!="csco_on_user" )? "desc=csco_on_user" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	上线操作人
			 	</a>
			 	${lz:set("checkeds[]","cscoOnUser")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscoOffUser || #request.defines['cscoOffUser']!=null">
			 <td <s:if test="#request.defines['cscoOffUser']>0">colspan="${defines["cscoOffUser"]}" ${all?"width":"iwidth"}="${defines["cscoOffUser"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="cscoOffUser"   title="下线操作人">
			 	<a class="${desc=="csco_off_user" ? "desc" : ""}${asc=="csco_off_user" ? "asc" : ""}" href="?${desc=="csco_off_user" ? "asc=csco_off_user" : ""}${(asc=="csco_off_user" || desc!="csco_off_user" )? "desc=csco_off_user" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	下线操作人
			 	</a>
			 	${lz:set("checkeds[]","cscoOffUser")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscoOnTime || #request.defines['cscoOnTime']!=null">
			 <td <s:if test="#request.defines['cscoOnTime']>0">colspan="${defines["cscoOnTime"]}" ${all?"width":"iwidth"}="${defines["cscoOnTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="6" ref="cscoOnTime"   title="上线时间">
			 	<a class="${desc=="csco_on_time" ? "desc" : ""}${asc=="csco_on_time" ? "asc" : ""}" href="?${desc=="csco_on_time" ? "asc=csco_on_time" : ""}${(asc=="csco_on_time" || desc!="csco_on_time" )? "desc=csco_on_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	上线时间
			 	</a>
			 	${lz:set("checkeds[]","cscoOnTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscoOffTime || #request.defines['cscoOffTime']!=null">
			 <td <s:if test="#request.defines['cscoOffTime']>0">colspan="${defines["cscoOffTime"]}" ${all?"width":"iwidth"}="${defines["cscoOffTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="7" ref="cscoOffTime"   title="下线时间">
			 	<a class="${desc=="csco_off_time" ? "desc" : ""}${asc=="csco_off_time" ? "asc" : ""}" href="?${desc=="csco_off_time" ? "asc=csco_off_time" : ""}${(asc=="csco_off_time" || desc!="csco_off_time" )? "desc=csco_off_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	下线时间
			 	</a>
			 	${lz:set("checkeds[]","cscoOffTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscoLong || #request.defines['cscoLong']!=null">
			 <td <s:if test="#request.defines['cscoLong']>0">colspan="${defines["cscoLong"]}" ${all?"width":"iwidth"}="${defines["cscoLong"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="cscoLong"   title="运营时长(分钟)">
			 	<a class="${desc=="csco_long" ? "desc" : ""}${asc=="csco_long" ? "asc" : ""}" href="?${desc=="csco_long" ? "asc=csco_long" : ""}${(asc=="csco_long" || desc!="csco_long" )? "desc=csco_long" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	运营时长(分钟)
			 	</a>
			 	${lz:set("checkeds[]","cscoLong")}
			 </td>
			 </s:if>
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.cscoOnRemark || #request.defines['cscoOnRemark']!=null">
			 <td <s:if test="#request.defines['cscoOnRemark']>0">colspan="${defines["cscoOnRemark"]}" ${all?"width":"iwidth"}="${defines["cscoOnRemark"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="9" ref="cscoOnRemark"   title="上线备注">
			 	<a class="${desc=="csco_on_remark" ? "desc" : ""}${asc=="csco_on_remark" ? "asc" : ""}" href="?${desc=="csco_on_remark" ? "asc=csco_on_remark" : ""}${(asc=="csco_on_remark" || desc!="csco_on_remark" )? "desc=csco_on_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	上线备注
			 	</a>
			 	${lz:set("checkeds[]","cscoOnRemark")}
			 </td>
			 </s:if>
			 	
			 <s:if test="#request.defines==null && #request.CTRL.l.cscoRemark || #request.defines['cscoRemark']!=null">
			 <td <s:if test="#request.defines['cscoRemark']>0">colspan="${defines["cscoRemark"]}" ${all?"width":"iwidth"}="${defines["cscoRemark"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="9" ref="cscoRemark"   title="下线备注">
			 	<a class="${desc=="csco_remark" ? "desc" : ""}${asc=="csco_remark" ? "asc" : ""}" href="?${desc=="csco_remark" ? "asc=csco_remark" : ""}${(asc=="csco_remark" || desc!="csco_remark" )? "desc=csco_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	下线备注
			 	</a>
			 	${lz:set("checkeds[]","cscoRemark")}
			 </td>
			 </s:if>
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.cscoProperty || #request.defines['cscoProperty']!=null">
			 <td <s:if test="#request.defines['cscoProperty']>0">colspan="${defines["cscoProperty"]}" ${all?"width":"iwidth"}="${defines["cscoProperty"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="9" ref="cscoProperty"   title="下线标签">
			 	<a class="${desc=="csco_property" ? "desc" : ""}${asc=="csco_property" ? "asc" : ""}" href="?${desc=="csco_property" ? "asc=csco_property" : ""}${(asc=="csco_property" || desc!="csco_property" )? "desc=csco_property" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	下线标签
			 	</a>
			 	${lz:set("checkeds[]","cscoProperty")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.cscoStatus || #request.defines['cscoStatus']!=null">
			 <td <s:if test="#request.defines['cscoStatus']>0">colspan="${defines["cscoStatus"]}" ${all?"width":"iwidth"}="${defines["cscoStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="10" ref="cscoStatus"   title="状态">
			 	<a class="${desc=="csco_status" ? "desc" : ""}${asc=="csco_status" ? "asc" : ""}" href="?${desc=="csco_status" ? "asc=csco_status" : ""}${(asc=="csco_status" || desc!="csco_status" )? "desc=csco_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","cscoStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="11" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="12" ref="operation">
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
		  <tr id="${item.cscoId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.cscoId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscoId || #request.defines['cscoId']!=null">
				<s:if test="#request.defines['cscoId']>0">
					${lz:set("注释","****cscoId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscoId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscoId",lz:indexOf(fieldName,"cscoId")>-1)}
				  		<s:if test="#request.atCscoId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscoId字段的字串格式化输出****")}
					<td ref="cscoId" class="td ">
						 
						 	${lz:or(item$cscoId[i.count-1],lz:left(item.cscoId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscoHost || #request.defines['cscoHost']!=null">
				<s:if test="#request.defines['cscoHost']>0">
					${lz:set("注释","****cscoHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscoHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscoHost",lz:indexOf(fieldName,"cscoHost")>-1)}
				  		<s:if test="#request.atCscoHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscoHost字段的字串格式化输出****")}
					<td ref="cscoHost" class="td " relate="${item.cscoHost}">
						 
						 	<a <s:if test="#item.cscoHost!=null && #item.cscoHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.cscoHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscoHost[i.count-1],lz:left(item.cscoHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscoCar || #request.defines['cscoCar']!=null">
				<s:if test="#request.defines['cscoCar']>0">
					${lz:set("注释","****cscoCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscoCar))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscoCar",lz:indexOf(fieldName,"cscoCar")>-1)}
				  		<s:if test="#request.atCscoCar==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscoCar字段的字串格式化输出****")}
					<td ref="cscoCar" class="td  node" relate="${item.cscoCar}">
						 
						 	<a <s:if test="#item.cscoCar!=null && #item.cscoCar!=''"> onclick="window.href('${basePath}${proname}/object/car_details.do?key=${item.cscoCar}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscoCar[i.count-1],lz:left(item.cscoCar$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscoOnUser || #request.defines['cscoOnUser']!=null">
				<s:if test="#request.defines['cscoOnUser']>0">
					${lz:set("注释","****cscoOnUser关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscoOnUser))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscoOnUser",lz:indexOf(fieldName,"cscoOnUser")>-1)}
				  		<s:if test="#request.atCscoOnUser==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscoOnUser字段的字串格式化输出****")}
					<td ref="cscoOnUser" class="td " relate="${item.cscoOnUser}">
						 
						 	<a <s:if test="#item.cscoOnUser!=null && #item.cscoOnUser!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.cscoOnUser}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscoOnUser[i.count-1],lz:left(item.cscoOnUser$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscoOffUser || #request.defines['cscoOffUser']!=null">
				<s:if test="#request.defines['cscoOffUser']>0">
					${lz:set("注释","****cscoOffUser关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscoOffUser))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscoOffUser",lz:indexOf(fieldName,"cscoOffUser")>-1)}
				  		<s:if test="#request.atCscoOffUser==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscoOffUser字段的字串格式化输出****")}
					<td ref="cscoOffUser" class="td " relate="${item.cscoOffUser}">
						 
						 	<a <s:if test="#item.cscoOffUser!=null && #item.cscoOffUser!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.cscoOffUser}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$cscoOffUser[i.count-1],lz:left(item.cscoOffUser$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscoOnTime || #request.defines['cscoOnTime']!=null">
				<s:if test="#request.defines['cscoOnTime']>0">
					${lz:set("注释","****cscoOnTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscoOnTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscoOnTime",lz:indexOf(fieldName,"cscoOnTime")>-1)}
				  		<s:if test="#request.atCscoOnTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscoOnTime字段的字串格式化输出****")}
					<td ref="cscoOnTime" class="td ">
						 
						 	${lz:or(item$cscoOnTime[i.count-1],lz:left(item.cscoOnTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscoOffTime || #request.defines['cscoOffTime']!=null">
				<s:if test="#request.defines['cscoOffTime']>0">
					${lz:set("注释","****cscoOffTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscoOffTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscoOffTime",lz:indexOf(fieldName,"cscoOffTime")>-1)}
				  		<s:if test="#request.atCscoOffTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscoOffTime字段的字串格式化输出****")}
					<td ref="cscoOffTime" class="td ">
						 
						 	${lz:or(item$cscoOffTime[i.count-1],lz:left(item.cscoOffTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscoLong || #request.defines['cscoLong']!=null">
				<s:if test="#request.defines['cscoLong']>0">
					${lz:set("注释","****cscoLong关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscoLong))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscoLong",lz:indexOf(fieldName,"cscoLong")>-1)}
				  		<s:if test="#request.atCscoLong==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscoLong字段的字串格式化输出****")}
					<td ref="cscoLong" class="td ">
						 
						 	${lz:or(item$cscoLong[i.count-1],lz:left(item.cscoLong$,100))}
					</td>
				</s:else>
			</s:if>
			
			<s:if test="#request.defines==null && #request.CTRL.l.cscoOnRemark || #request.defines['cscoOnRemark']!=null">
				<s:if test="#request.defines['cscoOnRemark']>0">
					${lz:set("注释","****cscoOnRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscoOnRemark))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscoRemark",lz:indexOf(fieldName,"cscoOnRemark")>-1)}
				  		<s:if test="#request.atCscoOnRemark==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscoOnRemark字段的字串格式化输出****")}
					<td ref="cscoOnRemark" class="td ">
						 
						 	${lz:or(item$cscoOnRemark[i.count-1],lz:left(item.cscoOnRemark$,100))}
					</td>
				</s:else>
			</s:if>
			
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscoRemark || #request.defines['cscoRemark']!=null">
				<s:if test="#request.defines['cscoRemark']>0">
					${lz:set("注释","****cscoRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscoRemark))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscoRemark",lz:indexOf(fieldName,"cscoRemark")>-1)}
				  		<s:if test="#request.atCscoRemark==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscoRemark字段的字串格式化输出****")}
					<td ref="cscoRemark" class="td ">
						 
						 	${lz:or(item$cscoRemark[i.count-1],lz:left(item.cscoRemark$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscoProperty || #request.defines['cscoProperty']!=null">
				<s:if test="#request.defines['cscoProperty']>0">
					${lz:set("注释","****cscoProperty关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscoProperty))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscoProperty",lz:indexOf(fieldName,"cscoProperty")>-1)}
				  		<s:if test="#request.atCscoProperty==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscoProperty字段的字串格式化输出****")}
					<td ref="cscoProperty" class="td ">
						 
						 ${lz:or(item$cscoProperty[i.count-1],lz:left(item.cscoProperty$,100))}
					</td>
				</s:else>
			</s:if> 
			 
			 
			 
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscoStatus || #request.defines['cscoStatus']!=null">
				<s:if test="#request.defines['cscoStatus']>0">
					${lz:set("注释","****cscoStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.cscoStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCscoStatus",lz:indexOf(fieldName,"cscoStatus")>-1)}
				  		<s:if test="#request.atCscoStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****cscoStatus字段的字串格式化输出****")}
					<td ref="cscoStatus" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.cscoId}','status')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$cscoStatus[i.count-1],lz:left(item.cscoStatus$,100))}
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
					<a href="javascript:HandleSel('${handle}','${item.cscoId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectCaron('${item.cscoId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditCaron('${item.cscoId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelCaron('${item.cscoId}','${item.cscoCar}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsCaron('${item.cscoId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.cscoId || #request.defines['cscoId']!=null">
				<s:if test="#request.defines['cscoId']>0">
					${lz:set("注释","****cscoId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscoId",lz:indexOf(fieldName,"cscoId")>-1)}				  		
				  		<s:if test="#request.atCscoId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscoId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscoHost || #request.defines['cscoHost']!=null">
				<s:if test="#request.defines['cscoHost']>0">
					${lz:set("注释","****cscoHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscoHost",lz:indexOf(fieldName,"cscoHost")>-1)}				  		
				  		<s:if test="#request.atCscoHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscoHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscoCar || #request.defines['cscoCar']!=null">
				<s:if test="#request.defines['cscoCar']>0">
					${lz:set("注释","****cscoCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscoCar",lz:indexOf(fieldName,"cscoCar")>-1)}				  		
				  		<s:if test="#request.atCscoCar==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscoCar[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscoOnUser || #request.defines['cscoOnUser']!=null">
				<s:if test="#request.defines['cscoOnUser']>0">
					${lz:set("注释","****cscoOnUser关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscoOnUser",lz:indexOf(fieldName,"cscoOnUser")>-1)}				  		
				  		<s:if test="#request.atCscoOnUser==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscoOnUser[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscoOffUser || #request.defines['cscoOffUser']!=null">
				<s:if test="#request.defines['cscoOffUser']>0">
					${lz:set("注释","****cscoOffUser关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscoOffUser",lz:indexOf(fieldName,"cscoOffUser")>-1)}				  		
				  		<s:if test="#request.atCscoOffUser==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscoOffUser[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscoOnTime || #request.defines['cscoOnTime']!=null">
				<s:if test="#request.defines['cscoOnTime']>0">
					${lz:set("注释","****cscoOnTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscoOnTime",lz:indexOf(fieldName,"cscoOnTime")>-1)}				  		
				  		<s:if test="#request.atCscoOnTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscoOnTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscoOffTime || #request.defines['cscoOffTime']!=null">
				<s:if test="#request.defines['cscoOffTime']>0">
					${lz:set("注释","****cscoOffTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscoOffTime",lz:indexOf(fieldName,"cscoOffTime")>-1)}				  		
				  		<s:if test="#request.atCscoOffTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscoOffTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscoLong || #request.defines['cscoLong']!=null">
				<s:if test="#request.defines['cscoLong']>0">
					${lz:set("注释","****cscoLong关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscoLong",lz:indexOf(fieldName,"cscoLong")>-1)}				  		
				  		<s:if test="#request.atCscoLong==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscoLong[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.cscoOnRemark || #request.defines['cscoOnRemark']!=null">
				<s:if test="#request.defines['cscoOnRemark']>0">
					${lz:set("注释","****cscoOnRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscoOnRemark",lz:indexOf(fieldName,"cscoOnRemark")>-1)}				  		
				  		<s:if test="#request.atCscoOnRemark==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscoOnRemark[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.cscoRemark || #request.defines['cscoRemark']!=null">
				<s:if test="#request.defines['cscoRemark']>0">
					${lz:set("注释","****cscoRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscoRemark",lz:indexOf(fieldName,"cscoRemark")>-1)}				  		
				  		<s:if test="#request.atCscoRemark==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscoRemark[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			
			<s:if test="#request.defines==null && #request.CTRL.l.cscoProperty || #request.defines['cscoProperty']!=null">
				<s:if test="#request.defines['cscoProperty']>0">
					${lz:set("注释","****cscoProperty关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscoProperty",lz:indexOf(fieldName,"cscoProperty")>-1)}				  		
				  		<s:if test="#request.atCscoProperty==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscoProperty[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			
			 
			<s:if test="#request.defines==null && #request.CTRL.l.cscoStatus || #request.defines['cscoStatus']!=null">
				<s:if test="#request.defines['cscoStatus']>0">
					${lz:set("注释","****cscoStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCscoStatus",lz:indexOf(fieldName,"cscoStatus")>-1)}				  		
				  		<s:if test="#request.atCscoStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">cscoStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 选择车辆上线记录
	**/
	function SelectCaron(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改车辆上线记录
	**/
	function EditCaron(id){
		var url = "${basePath}${namespace}caron_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改车辆上线记录'}":lz:json(ctrl)}};
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
		EditCaron(checker.val());
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
				var url = "${basePath}${namespace}caron_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新车辆上线记录",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}caron_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新车辆上线记录",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改车辆上线记录
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}caron_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新车辆上线记录",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["status"]={};
	fieldNames["status"]["cscoStatus"]=true;
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改车辆上线记录任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}caron_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新车辆上线记录",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}caron_edit.do";
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
			var url = "${basePath}${namespace}caron_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除车辆上线记录
	**/
	function DelCaron(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除车辆上线记录["+flag+"]吗？</span>",
				title:"删除车辆上线记录",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}caron_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选车辆上线记录吗？</span>",
				title:"批量删除车辆上线记录",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}caron_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示车辆上线记录详情
	**/
	function DetailsCaron(id)
	{
		var url = "${basePath}${namespace}caron_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"车辆上线记录详情",editable:false,visible:true}};
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
		DetailsCaron(checker.val());
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