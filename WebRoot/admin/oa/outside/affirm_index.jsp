<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/oa/outside/affirm.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty tbAbAffirm.tbaaId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"tbaaId,tbaaHost,tbaaCar,tbaaOrder,tbaaOut,tbaaRet,tbaaRealRet,tbaaTime,tbaaRealTime,tbaaConfirm,tbaaAdder,tbaaEditor,tbaaConfirmTime,tbaaUpdateTime,tbaaAddTime,tbaaRemark,tbaaStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"tbaaId,tbaaHost,tbaaCar,tbaaOrder,tbaaOut,tbaaRet,tbaaRealRet,tbaaTime,tbaaRealTime,tbaaConfirm,tbaaAdder,tbaaEditor,tbaaConfirmTime,tbaaUpdateTime,tbaaAddTime,tbaaRemark,tbaaStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"tbaaHost,tbaaCar,tbaaOrder,tbaaOut,tbaaRet,tbaaRealRet,tbaaTime,tbaaRealTime,tbaaConfirm,tbaaConfirmTime,tbaaStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"tbaaHost,tbaaCar,tbaaOrder,tbaaOut,tbaaRet,tbaaRealRet,tbaaTime,tbaaRealTime,tbaaConfirm,tbaaConfirmTime,tbaaStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>异借确认管理</title>
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
	<span class="caption">${empty title?"异借确认管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="TbAbAffirm" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="affirm.query.jsp"%>

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
			<dl ref="com.ccclubs.model.TbAbAffirm" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.TbAbAffirm" class="batch dropdowan">
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
		<table id="affirm_table" ref="TbAbAffirm" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbaaId || #request.defines['tbaaId']!=null">
			 <td <s:if test="#request.defines['tbaaId']>0">colspan="${defines["tbaaId"]}" ${all?"width":"iwidth"}="${defines["tbaaId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="tbaaId" flagKey  title="编号">
			 	<a class="${desc=="tbaa_id" ? "desc" : ""}${asc=="tbaa_id" ? "asc" : ""}" href="?${desc=="tbaa_id" ? "asc=tbaa_id" : ""}${(asc=="tbaa_id" || desc!="tbaa_id" )? "desc=tbaa_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","tbaaId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbaaHost || #request.defines['tbaaHost']!=null">
			 <td <s:if test="#request.defines['tbaaHost']>0">colspan="${defines["tbaaHost"]}" ${all?"width":"iwidth"}="${defines["tbaaHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="tbaaHost"   title="城市">
			 	<a class="${desc=="tbaa_host" ? "desc" : ""}${asc=="tbaa_host" ? "asc" : ""}" href="?${desc=="tbaa_host" ? "asc=tbaa_host" : ""}${(asc=="tbaa_host" || desc!="tbaa_host" )? "desc=tbaa_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","tbaaHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbaaCar || #request.defines['tbaaCar']!=null">
			 <td <s:if test="#request.defines['tbaaCar']>0">colspan="${defines["tbaaCar"]}" ${all?"width":"iwidth"}="${defines["tbaaCar"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="3" ref="tbaaCar"   title="所属车辆">
			 	<a class="${desc=="tbaa_car" ? "desc" : ""}${asc=="tbaa_car" ? "asc" : ""}" href="?${desc=="tbaa_car" ? "asc=tbaa_car" : ""}${(asc=="tbaa_car" || desc!="tbaa_car" )? "desc=tbaa_car" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属车辆
			 	</a>
			 	${lz:set("checkeds[]","tbaaCar")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbaaOrder || #request.defines['tbaaOrder']!=null">
			 <td <s:if test="#request.defines['tbaaOrder']>0">colspan="${defines["tbaaOrder"]}" ${all?"width":"iwidth"}="${defines["tbaaOrder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="tbaaOrder"   title="所属订单">
			 	<a class="${desc=="tbaa_order" ? "desc" : ""}${asc=="tbaa_order" ? "asc" : ""}" href="?${desc=="tbaa_order" ? "asc=tbaa_order" : ""}${(asc=="tbaa_order" || desc!="tbaa_order" )? "desc=tbaa_order" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属订单
			 	</a>
			 	${lz:set("checkeds[]","tbaaOrder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbaaOut || #request.defines['tbaaOut']!=null">
			 <td <s:if test="#request.defines['tbaaOut']>0">colspan="${defines["tbaaOut"]}" ${all?"width":"iwidth"}="${defines["tbaaOut"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="tbaaOut"   title="借出网点">
			 	<a class="${desc=="tbaa_out" ? "desc" : ""}${asc=="tbaa_out" ? "asc" : ""}" href="?${desc=="tbaa_out" ? "asc=tbaa_out" : ""}${(asc=="tbaa_out" || desc!="tbaa_out" )? "desc=tbaa_out" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	借出网点
			 	</a>
			 	${lz:set("checkeds[]","tbaaOut")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbaaRet || #request.defines['tbaaRet']!=null">
			 <td <s:if test="#request.defines['tbaaRet']>0">colspan="${defines["tbaaRet"]}" ${all?"width":"iwidth"}="${defines["tbaaRet"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="tbaaRet"   title="预计还入网点">
			 	<a class="${desc=="tbaa_ret" ? "desc" : ""}${asc=="tbaa_ret" ? "asc" : ""}" href="?${desc=="tbaa_ret" ? "asc=tbaa_ret" : ""}${(asc=="tbaa_ret" || desc!="tbaa_ret" )? "desc=tbaa_ret" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	预计还入网点
			 	</a>
			 	${lz:set("checkeds[]","tbaaRet")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbaaRealRet || #request.defines['tbaaRealRet']!=null">
			 <td <s:if test="#request.defines['tbaaRealRet']>0">colspan="${defines["tbaaRealRet"]}" ${all?"width":"iwidth"}="${defines["tbaaRealRet"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="7" ref="tbaaRealRet"   title="实际还入网点">
			 	<a class="${desc=="tbaa_real_ret" ? "desc" : ""}${asc=="tbaa_real_ret" ? "asc" : ""}" href="?${desc=="tbaa_real_ret" ? "asc=tbaa_real_ret" : ""}${(asc=="tbaa_real_ret" || desc!="tbaa_real_ret" )? "desc=tbaa_real_ret" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	实际还入网点
			 	</a>
			 	${lz:set("checkeds[]","tbaaRealRet")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbaaTime || #request.defines['tbaaTime']!=null">
			 <td <s:if test="#request.defines['tbaaTime']>0">colspan="${defines["tbaaTime"]}" ${all?"width":"iwidth"}="${defines["tbaaTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="8" ref="tbaaTime"   title="预计还入时间">
			 	<a class="${desc=="tbaa_time" ? "desc" : ""}${asc=="tbaa_time" ? "asc" : ""}" href="?${desc=="tbaa_time" ? "asc=tbaa_time" : ""}${(asc=="tbaa_time" || desc!="tbaa_time" )? "desc=tbaa_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	预计还入时间
			 	</a>
			 	${lz:set("checkeds[]","tbaaTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbaaRealTime || #request.defines['tbaaRealTime']!=null">
			 <td <s:if test="#request.defines['tbaaRealTime']>0">colspan="${defines["tbaaRealTime"]}" ${all?"width":"iwidth"}="${defines["tbaaRealTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="9" ref="tbaaRealTime"   title="实际还入时间">
			 	<a class="${desc=="tbaa_real_time" ? "desc" : ""}${asc=="tbaa_real_time" ? "asc" : ""}" href="?${desc=="tbaa_real_time" ? "asc=tbaa_real_time" : ""}${(asc=="tbaa_real_time" || desc!="tbaa_real_time" )? "desc=tbaa_real_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	实际还入时间
			 	</a>
			 	${lz:set("checkeds[]","tbaaRealTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbaaConfirm || #request.defines['tbaaConfirm']!=null">
			 <td <s:if test="#request.defines['tbaaConfirm']>0">colspan="${defines["tbaaConfirm"]}" ${all?"width":"iwidth"}="${defines["tbaaConfirm"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="10" ref="tbaaConfirm"   title="确认人">
			 	<a class="${desc=="tbaa_confirm" ? "desc" : ""}${asc=="tbaa_confirm" ? "asc" : ""}" href="?${desc=="tbaa_confirm" ? "asc=tbaa_confirm" : ""}${(asc=="tbaa_confirm" || desc!="tbaa_confirm" )? "desc=tbaa_confirm" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	确认人
			 	</a>
			 	${lz:set("checkeds[]","tbaaConfirm")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbaaAdder || #request.defines['tbaaAdder']!=null">
			 <td <s:if test="#request.defines['tbaaAdder']>0">colspan="${defines["tbaaAdder"]}" ${all?"width":"iwidth"}="${defines["tbaaAdder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="11" ref="tbaaAdder"   title="添加人">
			 	<a class="${desc=="tbaa_adder" ? "desc" : ""}${asc=="tbaa_adder" ? "asc" : ""}" href="?${desc=="tbaa_adder" ? "asc=tbaa_adder" : ""}${(asc=="tbaa_adder" || desc!="tbaa_adder" )? "desc=tbaa_adder" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加人
			 	</a>
			 	${lz:set("checkeds[]","tbaaAdder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbaaEditor || #request.defines['tbaaEditor']!=null">
			 <td <s:if test="#request.defines['tbaaEditor']>0">colspan="${defines["tbaaEditor"]}" ${all?"width":"iwidth"}="${defines["tbaaEditor"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="12" ref="tbaaEditor"   title="修改人">
			 	<a class="${desc=="tbaa_editor" ? "desc" : ""}${asc=="tbaa_editor" ? "asc" : ""}" href="?${desc=="tbaa_editor" ? "asc=tbaa_editor" : ""}${(asc=="tbaa_editor" || desc!="tbaa_editor" )? "desc=tbaa_editor" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改人
			 	</a>
			 	${lz:set("checkeds[]","tbaaEditor")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbaaConfirmTime || #request.defines['tbaaConfirmTime']!=null">
			 <td <s:if test="#request.defines['tbaaConfirmTime']>0">colspan="${defines["tbaaConfirmTime"]}" ${all?"width":"iwidth"}="${defines["tbaaConfirmTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="13" ref="tbaaConfirmTime"   title="确认时间">
			 	<a class="${desc=="tbaa_confirm_time" ? "desc" : ""}${asc=="tbaa_confirm_time" ? "asc" : ""}" href="?${desc=="tbaa_confirm_time" ? "asc=tbaa_confirm_time" : ""}${(asc=="tbaa_confirm_time" || desc!="tbaa_confirm_time" )? "desc=tbaa_confirm_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	确认时间
			 	</a>
			 	${lz:set("checkeds[]","tbaaConfirmTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbaaUpdateTime || #request.defines['tbaaUpdateTime']!=null">
			 <td <s:if test="#request.defines['tbaaUpdateTime']>0">colspan="${defines["tbaaUpdateTime"]}" ${all?"width":"iwidth"}="${defines["tbaaUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="14" ref="tbaaUpdateTime"   title="修改时间">
			 	<a class="${desc=="tbaa_update_time" ? "desc" : ""}${asc=="tbaa_update_time" ? "asc" : ""}" href="?${desc=="tbaa_update_time" ? "asc=tbaa_update_time" : ""}${(asc=="tbaa_update_time" || desc!="tbaa_update_time" )? "desc=tbaa_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 	${lz:set("checkeds[]","tbaaUpdateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbaaAddTime || #request.defines['tbaaAddTime']!=null">
			 <td <s:if test="#request.defines['tbaaAddTime']>0">colspan="${defines["tbaaAddTime"]}" ${all?"width":"iwidth"}="${defines["tbaaAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="15" ref="tbaaAddTime"   title="添加时间">
			 	<a class="${desc=="tbaa_add_time" ? "desc" : ""}${asc=="tbaa_add_time" ? "asc" : ""}" href="?${desc=="tbaa_add_time" ? "asc=tbaa_add_time" : ""}${(asc=="tbaa_add_time" || desc!="tbaa_add_time" )? "desc=tbaa_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","tbaaAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbaaRemark || #request.defines['tbaaRemark']!=null">
			 <td <s:if test="#request.defines['tbaaRemark']>0">colspan="${defines["tbaaRemark"]}" ${all?"width":"iwidth"}="${defines["tbaaRemark"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="16" ref="tbaaRemark"   title="备注">
			 	<a class="${desc=="tbaa_remark" ? "desc" : ""}${asc=="tbaa_remark" ? "asc" : ""}" href="?${desc=="tbaa_remark" ? "asc=tbaa_remark" : ""}${(asc=="tbaa_remark" || desc!="tbaa_remark" )? "desc=tbaa_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备注
			 	</a>
			 	${lz:set("checkeds[]","tbaaRemark")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.tbaaStatus || #request.defines['tbaaStatus']!=null">
			 <td <s:if test="#request.defines['tbaaStatus']>0">colspan="${defines["tbaaStatus"]}" ${all?"width":"iwidth"}="${defines["tbaaStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="17" ref="tbaaStatus"   title="状态">
			 	<a class="${desc=="tbaa_status" ? "desc" : ""}${asc=="tbaa_status" ? "asc" : ""}" href="?${desc=="tbaa_status" ? "asc=tbaa_status" : ""}${(asc=="tbaa_status" || desc!="tbaa_status" )? "desc=tbaa_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","tbaaStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="18" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="19" ref="operation">
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
		  <tr id="${item.tbaaId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.tbaaId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaId || #request.defines['tbaaId']!=null">
				<s:if test="#request.defines['tbaaId']>0">
					${lz:set("注释","****tbaaId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbaaId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbaaId",lz:indexOf(fieldName,"tbaaId")>-1)}
				  		<s:if test="#request.atTbaaId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbaaId字段的字串格式化输出****")}
					<td ref="tbaaId" class="td  node">
						 
						 	${lz:or(item$tbaaId[i.count-1],lz:left(item.tbaaId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaHost || #request.defines['tbaaHost']!=null">
				<s:if test="#request.defines['tbaaHost']>0">
					${lz:set("注释","****tbaaHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbaaHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbaaHost",lz:indexOf(fieldName,"tbaaHost")>-1)}
				  		<s:if test="#request.atTbaaHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbaaHost字段的字串格式化输出****")}
					<td ref="tbaaHost" class="td " relate="${item.tbaaHost}">
						 
						 	<a <s:if test="#item.tbaaHost!=null && #item.tbaaHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.tbaaHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbaaHost[i.count-1],lz:left(item.tbaaHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaCar || #request.defines['tbaaCar']!=null">
				<s:if test="#request.defines['tbaaCar']>0">
					${lz:set("注释","****tbaaCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbaaCar))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbaaCar",lz:indexOf(fieldName,"tbaaCar")>-1)}
				  		<s:if test="#request.atTbaaCar==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbaaCar字段的字串格式化输出****")}
					<td ref="tbaaCar" class="td " relate="${item.tbaaCar}">
						 
						 	<a <s:if test="#item.tbaaCar!=null && #item.tbaaCar!=''"> onclick="window.href('${basePath}${proname}/object/car_details.do?key=${item.tbaaCar}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbaaCar[i.count-1],lz:left(item.tbaaCar$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaOrder || #request.defines['tbaaOrder']!=null">
				<s:if test="#request.defines['tbaaOrder']>0">
					${lz:set("注释","****tbaaOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbaaOrder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbaaOrder",lz:indexOf(fieldName,"tbaaOrder")>-1)}
				  		<s:if test="#request.atTbaaOrder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbaaOrder字段的字串格式化输出****")}
					<td ref="tbaaOrder" class="td " relate="${item.tbaaOrder}">
						 
						 	<a <s:if test="#item.tbaaOrder!=null && #item.tbaaOrder!=''"> onclick="window.href('${basePath}${proname}/service/order_details.do?key=${item.tbaaOrder}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbaaOrder[i.count-1],lz:left(item.tbaaOrder$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaOut || #request.defines['tbaaOut']!=null">
				<s:if test="#request.defines['tbaaOut']>0">
					${lz:set("注释","****tbaaOut关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbaaOut))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbaaOut",lz:indexOf(fieldName,"tbaaOut")>-1)}
				  		<s:if test="#request.atTbaaOut==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbaaOut字段的字串格式化输出****")}
					<td ref="tbaaOut" class="td " relate="${item.tbaaOut}">
						 
						 	<a <s:if test="#item.tbaaOut!=null && #item.tbaaOut!=''"> onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${item.tbaaOut}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbaaOut[i.count-1],lz:left(item.tbaaOut$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaRet || #request.defines['tbaaRet']!=null">
				<s:if test="#request.defines['tbaaRet']>0">
					${lz:set("注释","****tbaaRet关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbaaRet))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbaaRet",lz:indexOf(fieldName,"tbaaRet")>-1)}
				  		<s:if test="#request.atTbaaRet==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbaaRet字段的字串格式化输出****")}
					<td ref="tbaaRet" class="td " relate="${item.tbaaRet}">
						 
						 	<a <s:if test="#item.tbaaRet!=null && #item.tbaaRet!=''"> onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${item.tbaaRet}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbaaRet[i.count-1],lz:left(item.tbaaRet$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaRealRet || #request.defines['tbaaRealRet']!=null">
				<s:if test="#request.defines['tbaaRealRet']>0">
					${lz:set("注释","****tbaaRealRet关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbaaRealRet))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbaaRealRet",lz:indexOf(fieldName,"tbaaRealRet")>-1)}
				  		<s:if test="#request.atTbaaRealRet==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbaaRealRet字段的字串格式化输出****")}
					<td ref="tbaaRealRet" class="td " relate="${item.tbaaRealRet}">
						 
						 	<a <s:if test="#item.tbaaRealRet!=null && #item.tbaaRealRet!=''"> onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${item.tbaaRealRet}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbaaRealRet[i.count-1],lz:left(item.tbaaRealRet$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaTime || #request.defines['tbaaTime']!=null">
				<s:if test="#request.defines['tbaaTime']>0">
					${lz:set("注释","****tbaaTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbaaTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbaaTime",lz:indexOf(fieldName,"tbaaTime")>-1)}
				  		<s:if test="#request.atTbaaTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbaaTime字段的字串格式化输出****")}
					<td ref="tbaaTime" class="td ">
						 
						 	${lz:or(item$tbaaTime[i.count-1],lz:left(item.tbaaTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaRealTime || #request.defines['tbaaRealTime']!=null">
				<s:if test="#request.defines['tbaaRealTime']>0">
					${lz:set("注释","****tbaaRealTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbaaRealTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbaaRealTime",lz:indexOf(fieldName,"tbaaRealTime")>-1)}
				  		<s:if test="#request.atTbaaRealTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbaaRealTime字段的字串格式化输出****")}
					<td ref="tbaaRealTime" class="td ">
						 
						 	${lz:or(item$tbaaRealTime[i.count-1],lz:left(item.tbaaRealTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaConfirm || #request.defines['tbaaConfirm']!=null">
				<s:if test="#request.defines['tbaaConfirm']>0">
					${lz:set("注释","****tbaaConfirm关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbaaConfirm))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbaaConfirm",lz:indexOf(fieldName,"tbaaConfirm")>-1)}
				  		<s:if test="#request.atTbaaConfirm==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbaaConfirm字段的字串格式化输出****")}
					<td ref="tbaaConfirm" class="td " relate="${item.tbaaConfirm}">
						 
						 	<a <s:if test="#item.tbaaConfirm!=null && #item.tbaaConfirm!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.tbaaConfirm}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbaaConfirm[i.count-1],lz:left(item.tbaaConfirm$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaAdder || #request.defines['tbaaAdder']!=null">
				<s:if test="#request.defines['tbaaAdder']>0">
					${lz:set("注释","****tbaaAdder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbaaAdder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbaaAdder",lz:indexOf(fieldName,"tbaaAdder")>-1)}
				  		<s:if test="#request.atTbaaAdder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbaaAdder字段的字串格式化输出****")}
					<td ref="tbaaAdder" class="td " relate="${item.tbaaAdder}">
						 
						 	<a <s:if test="#item.tbaaAdder!=null && #item.tbaaAdder!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.tbaaAdder}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbaaAdder[i.count-1],lz:left(item.tbaaAdder$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaEditor || #request.defines['tbaaEditor']!=null">
				<s:if test="#request.defines['tbaaEditor']>0">
					${lz:set("注释","****tbaaEditor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbaaEditor))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbaaEditor",lz:indexOf(fieldName,"tbaaEditor")>-1)}
				  		<s:if test="#request.atTbaaEditor==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbaaEditor字段的字串格式化输出****")}
					<td ref="tbaaEditor" class="td " relate="${item.tbaaEditor}">
						 
						 	<a <s:if test="#item.tbaaEditor!=null && #item.tbaaEditor!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.tbaaEditor}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$tbaaEditor[i.count-1],lz:left(item.tbaaEditor$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaConfirmTime || #request.defines['tbaaConfirmTime']!=null">
				<s:if test="#request.defines['tbaaConfirmTime']>0">
					${lz:set("注释","****tbaaConfirmTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbaaConfirmTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbaaConfirmTime",lz:indexOf(fieldName,"tbaaConfirmTime")>-1)}
				  		<s:if test="#request.atTbaaConfirmTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbaaConfirmTime字段的字串格式化输出****")}
					<td ref="tbaaConfirmTime" class="td ">
						 
						 	${lz:or(item$tbaaConfirmTime[i.count-1],lz:left(item.tbaaConfirmTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaUpdateTime || #request.defines['tbaaUpdateTime']!=null">
				<s:if test="#request.defines['tbaaUpdateTime']>0">
					${lz:set("注释","****tbaaUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbaaUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbaaUpdateTime",lz:indexOf(fieldName,"tbaaUpdateTime")>-1)}
				  		<s:if test="#request.atTbaaUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbaaUpdateTime字段的字串格式化输出****")}
					<td ref="tbaaUpdateTime" class="td ">
						 
						 	${lz:or(item$tbaaUpdateTime[i.count-1],lz:left(item.tbaaUpdateTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaAddTime || #request.defines['tbaaAddTime']!=null">
				<s:if test="#request.defines['tbaaAddTime']>0">
					${lz:set("注释","****tbaaAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbaaAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbaaAddTime",lz:indexOf(fieldName,"tbaaAddTime")>-1)}
				  		<s:if test="#request.atTbaaAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbaaAddTime字段的字串格式化输出****")}
					<td ref="tbaaAddTime" class="td ">
						 
						 	${lz:or(item$tbaaAddTime[i.count-1],lz:left(item.tbaaAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaRemark || #request.defines['tbaaRemark']!=null">
				<s:if test="#request.defines['tbaaRemark']>0">
					${lz:set("注释","****tbaaRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbaaRemark))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbaaRemark",lz:indexOf(fieldName,"tbaaRemark")>-1)}
				  		<s:if test="#request.atTbaaRemark==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbaaRemark字段的字串格式化输出****")}
					<td ref="tbaaRemark" class="td ">
						 
						 	${lz:or(item$tbaaRemark[i.count-1],lz:left(item.tbaaRemark$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaStatus || #request.defines['tbaaStatus']!=null">
				<s:if test="#request.defines['tbaaStatus']>0">
					${lz:set("注释","****tbaaStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.tbaaStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTbaaStatus",lz:indexOf(fieldName,"tbaaStatus")>-1)}
				  		<s:if test="#request.atTbaaStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****tbaaStatus字段的字串格式化输出****")}
					<td ref="tbaaStatus" class="td ">
						 
						 	${lz:or(item$tbaaStatus[i.count-1],lz:left(item.tbaaStatus$,100))}
					</td>
				</s:else>
			</s:if>
		   ${lz:set("注释","*****************数据列表数据部分结束*****************")}		  
		  
		   
		    ${lz:set("注释","****自定义的单行记录操作句柄[request中的handles(String[])变量]****")}
			<td class="options">
				  <s:if test="#request.alias==null">	
					<s:if test="#request.CTRL.canExp[0]==true">
					<a href="javascript:HandleSel('确认','${item.tbaaId}')">确认</a>
					</s:if>
				</s:if>
				${lz:set("items",lz:split(handles,","))}
				<s:iterator value="#request.items" id="handle" status="i">
					${lz:set("isMenu",(lz:indexOf(handle,"@")==0))}
					<s:if test="#request.isMenu!=true">
					<a href="javascript:HandleSel('${handle}','${item.tbaaId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectAffirm('${item.tbaaId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditAffirm('${item.tbaaId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelAffirm('${item.tbaaId}','${item.tbaaId}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsAffirm('${item.tbaaId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaId || #request.defines['tbaaId']!=null">
				<s:if test="#request.defines['tbaaId']>0">
					${lz:set("注释","****tbaaId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbaaId",lz:indexOf(fieldName,"tbaaId")>-1)}				  		
				  		<s:if test="#request.atTbaaId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbaaId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaHost || #request.defines['tbaaHost']!=null">
				<s:if test="#request.defines['tbaaHost']>0">
					${lz:set("注释","****tbaaHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbaaHost",lz:indexOf(fieldName,"tbaaHost")>-1)}				  		
				  		<s:if test="#request.atTbaaHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbaaHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaCar || #request.defines['tbaaCar']!=null">
				<s:if test="#request.defines['tbaaCar']>0">
					${lz:set("注释","****tbaaCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbaaCar",lz:indexOf(fieldName,"tbaaCar")>-1)}				  		
				  		<s:if test="#request.atTbaaCar==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbaaCar[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaOrder || #request.defines['tbaaOrder']!=null">
				<s:if test="#request.defines['tbaaOrder']>0">
					${lz:set("注释","****tbaaOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbaaOrder",lz:indexOf(fieldName,"tbaaOrder")>-1)}				  		
				  		<s:if test="#request.atTbaaOrder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbaaOrder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaOut || #request.defines['tbaaOut']!=null">
				<s:if test="#request.defines['tbaaOut']>0">
					${lz:set("注释","****tbaaOut关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbaaOut",lz:indexOf(fieldName,"tbaaOut")>-1)}				  		
				  		<s:if test="#request.atTbaaOut==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbaaOut[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaRet || #request.defines['tbaaRet']!=null">
				<s:if test="#request.defines['tbaaRet']>0">
					${lz:set("注释","****tbaaRet关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbaaRet",lz:indexOf(fieldName,"tbaaRet")>-1)}				  		
				  		<s:if test="#request.atTbaaRet==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbaaRet[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaRealRet || #request.defines['tbaaRealRet']!=null">
				<s:if test="#request.defines['tbaaRealRet']>0">
					${lz:set("注释","****tbaaRealRet关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbaaRealRet",lz:indexOf(fieldName,"tbaaRealRet")>-1)}				  		
				  		<s:if test="#request.atTbaaRealRet==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbaaRealRet[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaTime || #request.defines['tbaaTime']!=null">
				<s:if test="#request.defines['tbaaTime']>0">
					${lz:set("注释","****tbaaTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbaaTime",lz:indexOf(fieldName,"tbaaTime")>-1)}				  		
				  		<s:if test="#request.atTbaaTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbaaTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaRealTime || #request.defines['tbaaRealTime']!=null">
				<s:if test="#request.defines['tbaaRealTime']>0">
					${lz:set("注释","****tbaaRealTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbaaRealTime",lz:indexOf(fieldName,"tbaaRealTime")>-1)}				  		
				  		<s:if test="#request.atTbaaRealTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbaaRealTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaConfirm || #request.defines['tbaaConfirm']!=null">
				<s:if test="#request.defines['tbaaConfirm']>0">
					${lz:set("注释","****tbaaConfirm关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbaaConfirm",lz:indexOf(fieldName,"tbaaConfirm")>-1)}				  		
				  		<s:if test="#request.atTbaaConfirm==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbaaConfirm[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaAdder || #request.defines['tbaaAdder']!=null">
				<s:if test="#request.defines['tbaaAdder']>0">
					${lz:set("注释","****tbaaAdder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbaaAdder",lz:indexOf(fieldName,"tbaaAdder")>-1)}				  		
				  		<s:if test="#request.atTbaaAdder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbaaAdder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaEditor || #request.defines['tbaaEditor']!=null">
				<s:if test="#request.defines['tbaaEditor']>0">
					${lz:set("注释","****tbaaEditor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbaaEditor",lz:indexOf(fieldName,"tbaaEditor")>-1)}				  		
				  		<s:if test="#request.atTbaaEditor==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbaaEditor[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaConfirmTime || #request.defines['tbaaConfirmTime']!=null">
				<s:if test="#request.defines['tbaaConfirmTime']>0">
					${lz:set("注释","****tbaaConfirmTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbaaConfirmTime",lz:indexOf(fieldName,"tbaaConfirmTime")>-1)}				  		
				  		<s:if test="#request.atTbaaConfirmTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbaaConfirmTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaUpdateTime || #request.defines['tbaaUpdateTime']!=null">
				<s:if test="#request.defines['tbaaUpdateTime']>0">
					${lz:set("注释","****tbaaUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbaaUpdateTime",lz:indexOf(fieldName,"tbaaUpdateTime")>-1)}				  		
				  		<s:if test="#request.atTbaaUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbaaUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaAddTime || #request.defines['tbaaAddTime']!=null">
				<s:if test="#request.defines['tbaaAddTime']>0">
					${lz:set("注释","****tbaaAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbaaAddTime",lz:indexOf(fieldName,"tbaaAddTime")>-1)}				  		
				  		<s:if test="#request.atTbaaAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbaaAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaRemark || #request.defines['tbaaRemark']!=null">
				<s:if test="#request.defines['tbaaRemark']>0">
					${lz:set("注释","****tbaaRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbaaRemark",lz:indexOf(fieldName,"tbaaRemark")>-1)}				  		
				  		<s:if test="#request.atTbaaRemark==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbaaRemark[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.tbaaStatus || #request.defines['tbaaStatus']!=null">
				<s:if test="#request.defines['tbaaStatus']>0">
					${lz:set("注释","****tbaaStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTbaaStatus",lz:indexOf(fieldName,"tbaaStatus")>-1)}				  		
				  		<s:if test="#request.atTbaaStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">tbaaStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 选择异借确认
	**/
	function SelectAffirm(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改异借确认
	**/
	function EditAffirm(id){
		var url = "${basePath}${namespace}affirm_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改异借确认'}":lz:json(ctrl)}};
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
		EditAffirm(checker.val());
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
				var url = "${basePath}${namespace}affirm_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新异借确认",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}affirm_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新异借确认",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改异借确认
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}affirm_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新异借确认",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改异借确认任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}affirm_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新异借确认",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}affirm_edit.do";
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
			var url = "${basePath}${namespace}affirm_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除异借确认
	**/
	function DelAffirm(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除异借确认["+flag+"]吗？</span>",
				title:"删除异借确认",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}affirm_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选异借确认吗？</span>",
				title:"批量删除异借确认",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}affirm_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示异借确认详情
	**/
	function DetailsAffirm(id)
	{
		var url = "${basePath}${namespace}affirm_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"异借确认详情",editable:false,visible:true}};
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
		DetailsAffirm(checker.val());
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