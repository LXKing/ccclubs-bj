<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/oa/outside/outsideqr.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csOutsideQr.csoqId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csoqId,csoqHost,csoqAdder,csoqCar,csoqType,csoqAddTime,csoqEndTime,csoqRemark,csoqStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csoqId,csoqHost,csoqAdder,csoqCar,csoqType,csoqUserPosStart,csoqCarPosStart,csoqDistanceStart,csoqUsePosEnd,csoqCarPosEnd,csoqDistanceEnd,csoqAddTime,csoqEndTime,csoqTimeLong,csoqRemark,csoqStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csoqId,csoqHost,csoqAdder,csoqCar,csoqType,csoqAddTime,csoqEndTime,csoqStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csoqId,csoqHost,csoqAdder,csoqCar,csoqType,csoqDistanceStart,csoqDistanceEnd,csoqAddTime,csoqEndTime,csoqTimeLong,csoqStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>巡检作业管理</title>
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
	<span class="caption">${empty title?"巡检作业管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsOutsideQr" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="outsideqr.query.jsp"%>

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
			<dl ref="com.ccclubs.model.CsOutsideQr" class="display dropdowan" style="margin-left:30px;">
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
		<table id="outsideqr_table" ref="CsOutsideQr" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoqId || #request.defines['csoqId']!=null">
			 <td <s:if test="#request.defines['csoqId']>0">colspan="${defines["csoqId"]}" ${all?"width":"iwidth"}="${defines["csoqId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="csoqId" flagKey  title="编号">
			 	<a class="${desc=="csoq_id" ? "desc" : ""}${asc=="csoq_id" ? "asc" : ""}" href="?${desc=="csoq_id" ? "asc=csoq_id" : ""}${(asc=="csoq_id" || desc!="csoq_id" )? "desc=csoq_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","csoqId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoqHost || #request.defines['csoqHost']!=null">
			 <td <s:if test="#request.defines['csoqHost']>0">colspan="${defines["csoqHost"]}" ${all?"width":"iwidth"}="${defines["csoqHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="csoqHost"   title="城市">
			 	<a class="${desc=="csoq_host" ? "desc" : ""}${asc=="csoq_host" ? "asc" : ""}" href="?${desc=="csoq_host" ? "asc=csoq_host" : ""}${(asc=="csoq_host" || desc!="csoq_host" )? "desc=csoq_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","csoqHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoqAdder || #request.defines['csoqAdder']!=null">
			 <td <s:if test="#request.defines['csoqAdder']>0">colspan="${defines["csoqAdder"]}" ${all?"width":"iwidth"}="${defines["csoqAdder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="3" ref="csoqAdder"   title="人员">
			 	<a class="${desc=="csoq_adder" ? "desc" : ""}${asc=="csoq_adder" ? "asc" : ""}" href="?${desc=="csoq_adder" ? "asc=csoq_adder" : ""}${(asc=="csoq_adder" || desc!="csoq_adder" )? "desc=csoq_adder" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	人员
			 	</a>
			 	${lz:set("checkeds[]","csoqAdder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoqCar || #request.defines['csoqCar']!=null">
			 <td <s:if test="#request.defines['csoqCar']>0">colspan="${defines["csoqCar"]}" ${all?"width":"iwidth"}="${defines["csoqCar"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="csoqCar"   title="车辆">
			 	<a class="${desc=="csoq_car" ? "desc" : ""}${asc=="csoq_car" ? "asc" : ""}" href="?${desc=="csoq_car" ? "asc=csoq_car" : ""}${(asc=="csoq_car" || desc!="csoq_car" )? "desc=csoq_car" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车辆
			 	</a>
			 	${lz:set("checkeds[]","csoqCar")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoqType || #request.defines['csoqType']!=null">
			 <td <s:if test="#request.defines['csoqType']>0">colspan="${defines["csoqType"]}" ${all?"width":"iwidth"}="${defines["csoqType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="csoqType"   title="作业类型">
			 	<a class="${desc=="csoq_type" ? "desc" : ""}${asc=="csoq_type" ? "asc" : ""}" href="?${desc=="csoq_type" ? "asc=csoq_type" : ""}${(asc=="csoq_type" || desc!="csoq_type" )? "desc=csoq_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	作业类型
			 	</a>
			 	${lz:set("checkeds[]","csoqType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoqUserPosStart || #request.defines['csoqUserPosStart']!=null">
			 <td <s:if test="#request.defines['csoqUserPosStart']>0">colspan="${defines["csoqUserPosStart"]}" ${all?"width":"iwidth"}="${defines["csoqUserPosStart"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="6" ref="csoqUserPosStart"   title="人员位置(开始)">
			 	<a class="${desc=="csoq_user_pos_start" ? "desc" : ""}${asc=="csoq_user_pos_start" ? "asc" : ""}" href="?${desc=="csoq_user_pos_start" ? "asc=csoq_user_pos_start" : ""}${(asc=="csoq_user_pos_start" || desc!="csoq_user_pos_start" )? "desc=csoq_user_pos_start" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	人员位置(开始)
			 	</a>
			 	${lz:set("checkeds[]","csoqUserPosStart")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoqCarPosStart || #request.defines['csoqCarPosStart']!=null">
			 <td <s:if test="#request.defines['csoqCarPosStart']>0">colspan="${defines["csoqCarPosStart"]}" ${all?"width":"iwidth"}="${defines["csoqCarPosStart"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="7" ref="csoqCarPosStart"   title="车辆位置(开始)">
			 	<a class="${desc=="csoq_car_pos_start" ? "desc" : ""}${asc=="csoq_car_pos_start" ? "asc" : ""}" href="?${desc=="csoq_car_pos_start" ? "asc=csoq_car_pos_start" : ""}${(asc=="csoq_car_pos_start" || desc!="csoq_car_pos_start" )? "desc=csoq_car_pos_start" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车辆位置(开始)
			 	</a>
			 	${lz:set("checkeds[]","csoqCarPosStart")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoqDistanceStart || #request.defines['csoqDistanceStart']!=null">
			 <td <s:if test="#request.defines['csoqDistanceStart']>0">colspan="${defines["csoqDistanceStart"]}" ${all?"width":"iwidth"}="${defines["csoqDistanceStart"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="csoqDistanceStart"   title="人车距离(米/开始)">
			 	<a class="${desc=="csoq_distance_start" ? "desc" : ""}${asc=="csoq_distance_start" ? "asc" : ""}" href="?${desc=="csoq_distance_start" ? "asc=csoq_distance_start" : ""}${(asc=="csoq_distance_start" || desc!="csoq_distance_start" )? "desc=csoq_distance_start" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	人车距离(米/开始)
			 	</a>
			 	${lz:set("checkeds[]","csoqDistanceStart")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoqUsePosEnd || #request.defines['csoqUsePosEnd']!=null">
			 <td <s:if test="#request.defines['csoqUsePosEnd']>0">colspan="${defines["csoqUsePosEnd"]}" ${all?"width":"iwidth"}="${defines["csoqUsePosEnd"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="9" ref="csoqUsePosEnd"   title="人员位置(完成)">
			 	<a class="${desc=="csoq_use_pos_end" ? "desc" : ""}${asc=="csoq_use_pos_end" ? "asc" : ""}" href="?${desc=="csoq_use_pos_end" ? "asc=csoq_use_pos_end" : ""}${(asc=="csoq_use_pos_end" || desc!="csoq_use_pos_end" )? "desc=csoq_use_pos_end" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	人员位置(完成)
			 	</a>
			 	${lz:set("checkeds[]","csoqUsePosEnd")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoqCarPosEnd || #request.defines['csoqCarPosEnd']!=null">
			 <td <s:if test="#request.defines['csoqCarPosEnd']>0">colspan="${defines["csoqCarPosEnd"]}" ${all?"width":"iwidth"}="${defines["csoqCarPosEnd"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="10" ref="csoqCarPosEnd"   title="车辆位置(完成)">
			 	<a class="${desc=="csoq_car_pos_end" ? "desc" : ""}${asc=="csoq_car_pos_end" ? "asc" : ""}" href="?${desc=="csoq_car_pos_end" ? "asc=csoq_car_pos_end" : ""}${(asc=="csoq_car_pos_end" || desc!="csoq_car_pos_end" )? "desc=csoq_car_pos_end" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车辆位置(完成)
			 	</a>
			 	${lz:set("checkeds[]","csoqCarPosEnd")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoqDistanceEnd || #request.defines['csoqDistanceEnd']!=null">
			 <td <s:if test="#request.defines['csoqDistanceEnd']>0">colspan="${defines["csoqDistanceEnd"]}" ${all?"width":"iwidth"}="${defines["csoqDistanceEnd"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="11" ref="csoqDistanceEnd"   title="人车距离(米/完成)">
			 	<a class="${desc=="csoq_distance_end" ? "desc" : ""}${asc=="csoq_distance_end" ? "asc" : ""}" href="?${desc=="csoq_distance_end" ? "asc=csoq_distance_end" : ""}${(asc=="csoq_distance_end" || desc!="csoq_distance_end" )? "desc=csoq_distance_end" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	人车距离(米/完成)
			 	</a>
			 	${lz:set("checkeds[]","csoqDistanceEnd")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoqAddTime || #request.defines['csoqAddTime']!=null">
			 <td <s:if test="#request.defines['csoqAddTime']>0">colspan="${defines["csoqAddTime"]}" ${all?"width":"iwidth"}="${defines["csoqAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="12" ref="csoqAddTime"   title="开始时间">
			 	<a class="${desc=="csoq_add_time" ? "desc" : ""}${asc=="csoq_add_time" ? "asc" : ""}" href="?${desc=="csoq_add_time" ? "asc=csoq_add_time" : ""}${(asc=="csoq_add_time" || desc!="csoq_add_time" )? "desc=csoq_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	开始时间
			 	</a>
			 	${lz:set("checkeds[]","csoqAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoqEndTime || #request.defines['csoqEndTime']!=null">
			 <td <s:if test="#request.defines['csoqEndTime']>0">colspan="${defines["csoqEndTime"]}" ${all?"width":"iwidth"}="${defines["csoqEndTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="13" ref="csoqEndTime"   title="结束时间">
			 	<a class="${desc=="csoq_end_time" ? "desc" : ""}${asc=="csoq_end_time" ? "asc" : ""}" href="?${desc=="csoq_end_time" ? "asc=csoq_end_time" : ""}${(asc=="csoq_end_time" || desc!="csoq_end_time" )? "desc=csoq_end_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	结束时间
			 	</a>
			 	${lz:set("checkeds[]","csoqEndTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoqTimeLong || #request.defines['csoqTimeLong']!=null">
			 <td <s:if test="#request.defines['csoqTimeLong']>0">colspan="${defines["csoqTimeLong"]}" ${all?"width":"iwidth"}="${defines["csoqTimeLong"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="14" ref="csoqTimeLong"   title="作业时长(分钟)">
			 	<a class="${desc=="csoq_time_long" ? "desc" : ""}${asc=="csoq_time_long" ? "asc" : ""}" href="?${desc=="csoq_time_long" ? "asc=csoq_time_long" : ""}${(asc=="csoq_time_long" || desc!="csoq_time_long" )? "desc=csoq_time_long" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	作业时长(分钟)
			 	</a>
			 	${lz:set("checkeds[]","csoqTimeLong")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoqRemark || #request.defines['csoqRemark']!=null">
			 <td <s:if test="#request.defines['csoqRemark']>0">colspan="${defines["csoqRemark"]}" ${all?"width":"iwidth"}="${defines["csoqRemark"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="15" ref="csoqRemark"   title="备注信息">
			 	<a class="${desc=="csoq_remark" ? "desc" : ""}${asc=="csoq_remark" ? "asc" : ""}" href="?${desc=="csoq_remark" ? "asc=csoq_remark" : ""}${(asc=="csoq_remark" || desc!="csoq_remark" )? "desc=csoq_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备注信息
			 	</a>
			 	${lz:set("checkeds[]","csoqRemark")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoqStatus || #request.defines['csoqStatus']!=null">
			 <td <s:if test="#request.defines['csoqStatus']>0">colspan="${defines["csoqStatus"]}" ${all?"width":"iwidth"}="${defines["csoqStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="16" ref="csoqStatus"   title="状态">
			 	<a class="${desc=="csoq_status" ? "desc" : ""}${asc=="csoq_status" ? "asc" : ""}" href="?${desc=="csoq_status" ? "asc=csoq_status" : ""}${(asc=="csoq_status" || desc!="csoq_status" )? "desc=csoq_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","csoqStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="17" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="18" ref="operation">
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
		  <tr id="${item.csoqId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csoqId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoqId || #request.defines['csoqId']!=null">
				<s:if test="#request.defines['csoqId']>0">
					${lz:set("注释","****csoqId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoqId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoqId",lz:indexOf(fieldName,"csoqId")>-1)}
				  		<s:if test="#request.atCsoqId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoqId字段的字串格式化输出****")}
					<td ref="csoqId" class="td  node">
						 
						 	${lz:or(item$csoqId[i.count-1],lz:left(item.csoqId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoqHost || #request.defines['csoqHost']!=null">
				<s:if test="#request.defines['csoqHost']>0">
					${lz:set("注释","****csoqHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoqHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoqHost",lz:indexOf(fieldName,"csoqHost")>-1)}
				  		<s:if test="#request.atCsoqHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoqHost字段的字串格式化输出****")}
					<td ref="csoqHost" class="td " relate="${item.csoqHost}">
						 
						 	<a <s:if test="#item.csoqHost!=null && #item.csoqHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.csoqHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csoqHost[i.count-1],lz:left(item.csoqHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoqAdder || #request.defines['csoqAdder']!=null">
				<s:if test="#request.defines['csoqAdder']>0">
					${lz:set("注释","****csoqAdder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoqAdder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoqAdder",lz:indexOf(fieldName,"csoqAdder")>-1)}
				  		<s:if test="#request.atCsoqAdder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoqAdder字段的字串格式化输出****")}
					<td ref="csoqAdder" class="td " relate="${item.csoqAdder}">
						 
						 	<a <s:if test="#item.csoqAdder!=null && #item.csoqAdder!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.csoqAdder}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csoqAdder[i.count-1],lz:left(item.csoqAdder$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoqCar || #request.defines['csoqCar']!=null">
				<s:if test="#request.defines['csoqCar']>0">
					${lz:set("注释","****csoqCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoqCar))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoqCar",lz:indexOf(fieldName,"csoqCar")>-1)}
				  		<s:if test="#request.atCsoqCar==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoqCar字段的字串格式化输出****")}
					<td ref="csoqCar" class="td " relate="${item.csoqCar}">
						 
						 	<a <s:if test="#item.csoqCar!=null && #item.csoqCar!=''"> onclick="window.href('${basePath}${proname}/object/car_details.do?key=${item.csoqCar}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csoqCar[i.count-1],lz:left(item.csoqCar$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoqType || #request.defines['csoqType']!=null">
				<s:if test="#request.defines['csoqType']>0">
					${lz:set("注释","****csoqType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoqType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoqType",lz:indexOf(fieldName,"csoqType")>-1)}
				  		<s:if test="#request.atCsoqType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoqType字段的字串格式化输出****")}
					<td ref="csoqType" class="td " relate="${item.csoqType}">
						 
						 	<a <s:if test="#item.csoqType!=null && #item.csoqType!=''"> onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${item.csoqType}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csoqType[i.count-1],lz:left(item.csoqType$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoqUserPosStart || #request.defines['csoqUserPosStart']!=null">
				<s:if test="#request.defines['csoqUserPosStart']>0">
					${lz:set("注释","****csoqUserPosStart关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoqUserPosStart))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoqUserPosStart",lz:indexOf(fieldName,"csoqUserPosStart")>-1)}
				  		<s:if test="#request.atCsoqUserPosStart==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoqUserPosStart字段的字串格式化输出****")}
					<td ref="csoqUserPosStart" class="td ">
						 
						 	${lz:or(item$csoqUserPosStart[i.count-1],lz:left(item.csoqUserPosStart$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoqCarPosStart || #request.defines['csoqCarPosStart']!=null">
				<s:if test="#request.defines['csoqCarPosStart']>0">
					${lz:set("注释","****csoqCarPosStart关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoqCarPosStart))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoqCarPosStart",lz:indexOf(fieldName,"csoqCarPosStart")>-1)}
				  		<s:if test="#request.atCsoqCarPosStart==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoqCarPosStart字段的字串格式化输出****")}
					<td ref="csoqCarPosStart" class="td ">
						 
						 	${lz:or(item$csoqCarPosStart[i.count-1],lz:left(item.csoqCarPosStart$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoqDistanceStart || #request.defines['csoqDistanceStart']!=null">
				<s:if test="#request.defines['csoqDistanceStart']>0">
					${lz:set("注释","****csoqDistanceStart关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoqDistanceStart))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoqDistanceStart",lz:indexOf(fieldName,"csoqDistanceStart")>-1)}
				  		<s:if test="#request.atCsoqDistanceStart==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoqDistanceStart字段的字串格式化输出****")}
					<td ref="csoqDistanceStart" class="td ">
						 
						 	${lz:or(item$csoqDistanceStart[i.count-1],lz:left(item.csoqDistanceStart$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoqUsePosEnd || #request.defines['csoqUsePosEnd']!=null">
				<s:if test="#request.defines['csoqUsePosEnd']>0">
					${lz:set("注释","****csoqUsePosEnd关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoqUsePosEnd))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoqUsePosEnd",lz:indexOf(fieldName,"csoqUsePosEnd")>-1)}
				  		<s:if test="#request.atCsoqUsePosEnd==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoqUsePosEnd字段的字串格式化输出****")}
					<td ref="csoqUsePosEnd" class="td ">
						 
						 	${lz:or(item$csoqUsePosEnd[i.count-1],lz:left(item.csoqUsePosEnd$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoqCarPosEnd || #request.defines['csoqCarPosEnd']!=null">
				<s:if test="#request.defines['csoqCarPosEnd']>0">
					${lz:set("注释","****csoqCarPosEnd关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoqCarPosEnd))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoqCarPosEnd",lz:indexOf(fieldName,"csoqCarPosEnd")>-1)}
				  		<s:if test="#request.atCsoqCarPosEnd==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoqCarPosEnd字段的字串格式化输出****")}
					<td ref="csoqCarPosEnd" class="td ">
						 
						 	${lz:or(item$csoqCarPosEnd[i.count-1],lz:left(item.csoqCarPosEnd$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoqDistanceEnd || #request.defines['csoqDistanceEnd']!=null">
				<s:if test="#request.defines['csoqDistanceEnd']>0">
					${lz:set("注释","****csoqDistanceEnd关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoqDistanceEnd))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoqDistanceEnd",lz:indexOf(fieldName,"csoqDistanceEnd")>-1)}
				  		<s:if test="#request.atCsoqDistanceEnd==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoqDistanceEnd字段的字串格式化输出****")}
					<td ref="csoqDistanceEnd" class="td ">
						 
						 	${lz:or(item$csoqDistanceEnd[i.count-1],lz:left(item.csoqDistanceEnd$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoqAddTime || #request.defines['csoqAddTime']!=null">
				<s:if test="#request.defines['csoqAddTime']>0">
					${lz:set("注释","****csoqAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoqAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoqAddTime",lz:indexOf(fieldName,"csoqAddTime")>-1)}
				  		<s:if test="#request.atCsoqAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoqAddTime字段的字串格式化输出****")}
					<td ref="csoqAddTime" class="td ">
						 
						 	${lz:or(item$csoqAddTime[i.count-1],lz:left(item.csoqAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoqEndTime || #request.defines['csoqEndTime']!=null">
				<s:if test="#request.defines['csoqEndTime']>0">
					${lz:set("注释","****csoqEndTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoqEndTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoqEndTime",lz:indexOf(fieldName,"csoqEndTime")>-1)}
				  		<s:if test="#request.atCsoqEndTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoqEndTime字段的字串格式化输出****")}
					<td ref="csoqEndTime" class="td ">
						 
						 	${lz:or(item$csoqEndTime[i.count-1],lz:left(item.csoqEndTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoqTimeLong || #request.defines['csoqTimeLong']!=null">
				<s:if test="#request.defines['csoqTimeLong']>0">
					${lz:set("注释","****csoqTimeLong关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoqTimeLong))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoqTimeLong",lz:indexOf(fieldName,"csoqTimeLong")>-1)}
				  		<s:if test="#request.atCsoqTimeLong==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoqTimeLong字段的字串格式化输出****")}
					<td ref="csoqTimeLong" class="td ">
						 
						 	${lz:or(item$csoqTimeLong[i.count-1],lz:left(item.csoqTimeLong$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoqRemark || #request.defines['csoqRemark']!=null">
				<s:if test="#request.defines['csoqRemark']>0">
					${lz:set("注释","****csoqRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoqRemark))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoqRemark",lz:indexOf(fieldName,"csoqRemark")>-1)}
				  		<s:if test="#request.atCsoqRemark==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoqRemark字段的字串格式化输出****")}
					<td ref="csoqRemark" class="td ">
						 
						 	${lz:or(item$csoqRemark[i.count-1],lz:left(item.csoqRemark$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoqStatus || #request.defines['csoqStatus']!=null">
				<s:if test="#request.defines['csoqStatus']>0">
					${lz:set("注释","****csoqStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoqStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoqStatus",lz:indexOf(fieldName,"csoqStatus")>-1)}
				  		<s:if test="#request.atCsoqStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoqStatus字段的字串格式化输出****")}
					<td ref="csoqStatus" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csoqId}','status')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csoqStatus[i.count-1],lz:left(item.csoqStatus$,100))}
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
					<a href="javascript:HandleSel('${handle}','${item.csoqId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectOutsideqr('${item.csoqId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelOutsideqr('${item.csoqId}','${item.csoqId}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsOutsideqr('${item.csoqId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csoqId || #request.defines['csoqId']!=null">
				<s:if test="#request.defines['csoqId']>0">
					${lz:set("注释","****csoqId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoqId",lz:indexOf(fieldName,"csoqId")>-1)}				  		
				  		<s:if test="#request.atCsoqId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoqId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoqHost || #request.defines['csoqHost']!=null">
				<s:if test="#request.defines['csoqHost']>0">
					${lz:set("注释","****csoqHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoqHost",lz:indexOf(fieldName,"csoqHost")>-1)}				  		
				  		<s:if test="#request.atCsoqHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoqHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoqAdder || #request.defines['csoqAdder']!=null">
				<s:if test="#request.defines['csoqAdder']>0">
					${lz:set("注释","****csoqAdder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoqAdder",lz:indexOf(fieldName,"csoqAdder")>-1)}				  		
				  		<s:if test="#request.atCsoqAdder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoqAdder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoqCar || #request.defines['csoqCar']!=null">
				<s:if test="#request.defines['csoqCar']>0">
					${lz:set("注释","****csoqCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoqCar",lz:indexOf(fieldName,"csoqCar")>-1)}				  		
				  		<s:if test="#request.atCsoqCar==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoqCar[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoqType || #request.defines['csoqType']!=null">
				<s:if test="#request.defines['csoqType']>0">
					${lz:set("注释","****csoqType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoqType",lz:indexOf(fieldName,"csoqType")>-1)}				  		
				  		<s:if test="#request.atCsoqType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoqType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoqUserPosStart || #request.defines['csoqUserPosStart']!=null">
				<s:if test="#request.defines['csoqUserPosStart']>0">
					${lz:set("注释","****csoqUserPosStart关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoqUserPosStart",lz:indexOf(fieldName,"csoqUserPosStart")>-1)}				  		
				  		<s:if test="#request.atCsoqUserPosStart==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoqUserPosStart[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoqCarPosStart || #request.defines['csoqCarPosStart']!=null">
				<s:if test="#request.defines['csoqCarPosStart']>0">
					${lz:set("注释","****csoqCarPosStart关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoqCarPosStart",lz:indexOf(fieldName,"csoqCarPosStart")>-1)}				  		
				  		<s:if test="#request.atCsoqCarPosStart==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoqCarPosStart[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoqDistanceStart || #request.defines['csoqDistanceStart']!=null">
				<s:if test="#request.defines['csoqDistanceStart']>0">
					${lz:set("注释","****csoqDistanceStart关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoqDistanceStart",lz:indexOf(fieldName,"csoqDistanceStart")>-1)}				  		
				  		<s:if test="#request.atCsoqDistanceStart==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoqDistanceStart[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoqUsePosEnd || #request.defines['csoqUsePosEnd']!=null">
				<s:if test="#request.defines['csoqUsePosEnd']>0">
					${lz:set("注释","****csoqUsePosEnd关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoqUsePosEnd",lz:indexOf(fieldName,"csoqUsePosEnd")>-1)}				  		
				  		<s:if test="#request.atCsoqUsePosEnd==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoqUsePosEnd[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoqCarPosEnd || #request.defines['csoqCarPosEnd']!=null">
				<s:if test="#request.defines['csoqCarPosEnd']>0">
					${lz:set("注释","****csoqCarPosEnd关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoqCarPosEnd",lz:indexOf(fieldName,"csoqCarPosEnd")>-1)}				  		
				  		<s:if test="#request.atCsoqCarPosEnd==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoqCarPosEnd[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoqDistanceEnd || #request.defines['csoqDistanceEnd']!=null">
				<s:if test="#request.defines['csoqDistanceEnd']>0">
					${lz:set("注释","****csoqDistanceEnd关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoqDistanceEnd",lz:indexOf(fieldName,"csoqDistanceEnd")>-1)}				  		
				  		<s:if test="#request.atCsoqDistanceEnd==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoqDistanceEnd[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoqAddTime || #request.defines['csoqAddTime']!=null">
				<s:if test="#request.defines['csoqAddTime']>0">
					${lz:set("注释","****csoqAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoqAddTime",lz:indexOf(fieldName,"csoqAddTime")>-1)}				  		
				  		<s:if test="#request.atCsoqAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoqAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoqEndTime || #request.defines['csoqEndTime']!=null">
				<s:if test="#request.defines['csoqEndTime']>0">
					${lz:set("注释","****csoqEndTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoqEndTime",lz:indexOf(fieldName,"csoqEndTime")>-1)}				  		
				  		<s:if test="#request.atCsoqEndTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoqEndTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoqTimeLong || #request.defines['csoqTimeLong']!=null">
				<s:if test="#request.defines['csoqTimeLong']>0">
					${lz:set("注释","****csoqTimeLong关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoqTimeLong",lz:indexOf(fieldName,"csoqTimeLong")>-1)}				  		
				  		<s:if test="#request.atCsoqTimeLong==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoqTimeLong[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoqRemark || #request.defines['csoqRemark']!=null">
				<s:if test="#request.defines['csoqRemark']>0">
					${lz:set("注释","****csoqRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoqRemark",lz:indexOf(fieldName,"csoqRemark")>-1)}				  		
				  		<s:if test="#request.atCsoqRemark==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoqRemark[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoqStatus || #request.defines['csoqStatus']!=null">
				<s:if test="#request.defines['csoqStatus']>0">
					${lz:set("注释","****csoqStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoqStatus",lz:indexOf(fieldName,"csoqStatus")>-1)}				  		
				  		<s:if test="#request.atCsoqStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoqStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 选择巡检作业
	**/
	function SelectOutsideqr(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改巡检作业任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}outsideqr_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新巡检作业",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}outsideqr_edit.do";
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
			var url = "${basePath}${namespace}outsideqr_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除巡检作业
	**/
	function DelOutsideqr(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除巡检作业["+flag+"]吗？</span>",
				title:"删除巡检作业",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}outsideqr_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选巡检作业吗？</span>",
				title:"批量删除巡检作业",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}outsideqr_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示巡检作业详情
	**/
	function DetailsOutsideqr(id)
	{
		var url = "${basePath}${namespace}outsideqr_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"巡检作业详情",editable:false,visible:true}};
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
		DetailsOutsideqr(checker.val());
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