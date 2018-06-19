<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/finance/operecord.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csOperRecord.csorId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csorId,csorHost,csorTitle,csorMoneyType,csorRecordType,csorIn,csorOut,csorRecord,csorMember,csorOrder,csorObject,csorDateTime,csorCashNo,csorAdder,csorStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csorId,csorHost,csorTitle,csorMoneyType,csorRecordType,csorIn,csorOut,csorRecord,csorMember,csorOrder,csorObject,csorDateTime,csorCashNo,csorAdder,csorStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csorId,csorHost,csorTitle,csorMoneyType,csorRecordType,csorIn,csorOut,csorOrder,csorObject,csorDateTime,csorCashNo",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csorId,csorHost,csorTitle,csorMoneyType,csorRecordType,csorIn,csorOut,csorOrder,csorObject,csorDateTime,csorCashNo",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>收支记录管理</title>
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
<s:iterator value="#request.page.result" id="item" status="i">
<lz:set name="item$csorCashNo[]">${item.csorCashNo==null?"<span style='color:#999999;'>&lt;未结账&gt;</span>":item.csorCashNo$}</lz:set>
<lz:set name="item$csorIn[]">${item.csorIn==null?"0.0":item.csorIn$}</lz:set>
<lz:set name="item$csorOut[]">${item.csorOut==null?"0.0":item.csorOut$}</lz:set>
</s:iterator>
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
	<s:if test="#request.alias==null">
		<s:if test="#request.CTRL.canStats==true">
	<a href="operecord_stats.do?${lz:querys("UTF-8","limitsid")}" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/Statistics.png"/>
		数据统计
	</a>
		</s:if>
	</s:if>
	<s:if test="#request.shortcuts!=null || #request.haveQuerys==true">
		<a class="toggler" href="javascript:void(0);"><span class="${window.shortcuts!="hide"?"on":""}"></span></a>
	</s:if>
	<span class="caption">${empty title?"收支记录管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsOperRecord" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="operecord.query.jsp"%>

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
			<dl ref="com.ccclubs.model.CsOperRecord" class="display dropdowan" style="margin-left:30px;">
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
		<table id="operecord_table" ref="CsOperRecord" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csorId || #request.defines['csorId']!=null">
			 <td <s:if test="#request.defines['csorId']>0">colspan="${defines["csorId"]}" ${all?"width":"iwidth"}="${defines["csorId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="csorId"   title="编号">
			 	<a class="${desc=="csor_id" ? "desc" : ""}${asc=="csor_id" ? "asc" : ""}" href="?${desc=="csor_id" ? "asc=csor_id" : ""}${(asc=="csor_id" || desc!="csor_id" )? "desc=csor_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","csorId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csorHost || #request.defines['csorHost']!=null">
			 <td <s:if test="#request.defines['csorHost']>0">colspan="${defines["csorHost"]}" ${all?"width":"iwidth"}="${defines["csorHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="csorHost"   title="城市">
			 	<a class="${desc=="csor_host" ? "desc" : ""}${asc=="csor_host" ? "asc" : ""}" href="?${desc=="csor_host" ? "asc=csor_host" : ""}${(asc=="csor_host" || desc!="csor_host" )? "desc=csor_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","csorHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csorTitle || #request.defines['csorTitle']!=null">
			 <td <s:if test="#request.defines['csorTitle']>0">colspan="${defines["csorTitle"]}" ${all?"width":"iwidth"}="${defines["csorTitle"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="3" ref="csorTitle" flagKey  title="摘要">
			 	<a class="${desc=="csor_title" ? "desc" : ""}${asc=="csor_title" ? "asc" : ""}" href="?${desc=="csor_title" ? "asc=csor_title" : ""}${(asc=="csor_title" || desc!="csor_title" )? "desc=csor_title" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	摘要
			 	</a>
			 	${lz:set("checkeds[]","csorTitle")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csorMoneyType || #request.defines['csorMoneyType']!=null">
			 <td <s:if test="#request.defines['csorMoneyType']>0">colspan="${defines["csorMoneyType"]}" ${all?"width":"iwidth"}="${defines["csorMoneyType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="csorMoneyType"   title="金额类型">
			 	<a class="${desc=="csor_money_type" ? "desc" : ""}${asc=="csor_money_type" ? "asc" : ""}" href="?${desc=="csor_money_type" ? "asc=csor_money_type" : ""}${(asc=="csor_money_type" || desc!="csor_money_type" )? "desc=csor_money_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	金额类型
			 	</a>
			 	${lz:set("checkeds[]","csorMoneyType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csorRecordType || #request.defines['csorRecordType']!=null">
			 <td <s:if test="#request.defines['csorRecordType']>0">colspan="${defines["csorRecordType"]}" ${all?"width":"iwidth"}="${defines["csorRecordType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="csorRecordType"   title="科目类型">
			 	<a class="${desc=="csor_record_type" ? "desc" : ""}${asc=="csor_record_type" ? "asc" : ""}" href="?${desc=="csor_record_type" ? "asc=csor_record_type" : ""}${(asc=="csor_record_type" || desc!="csor_record_type" )? "desc=csor_record_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	科目类型
			 	</a>
			 	${lz:set("checkeds[]","csorRecordType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csorIn || #request.defines['csorIn']!=null">
			 <td <s:if test="#request.defines['csorIn']>0">colspan="${defines["csorIn"]}" ${all?"width":"iwidth"}="${defines["csorIn"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="csorIn"   title="收入">
			 	<a class="${desc=="csor_in" ? "desc" : ""}${asc=="csor_in" ? "asc" : ""}" href="?${desc=="csor_in" ? "asc=csor_in" : ""}${(asc=="csor_in" || desc!="csor_in" )? "desc=csor_in" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	收入
			 	</a>
			 	${lz:set("checkeds[]","csorIn")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csorOut || #request.defines['csorOut']!=null">
			 <td <s:if test="#request.defines['csorOut']>0">colspan="${defines["csorOut"]}" ${all?"width":"iwidth"}="${defines["csorOut"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="7" ref="csorOut"   title="支出">
			 	<a class="${desc=="csor_out" ? "desc" : ""}${asc=="csor_out" ? "asc" : ""}" href="?${desc=="csor_out" ? "asc=csor_out" : ""}${(asc=="csor_out" || desc!="csor_out" )? "desc=csor_out" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	支出
			 	</a>
			 	${lz:set("checkeds[]","csorOut")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csorRecord || #request.defines['csorRecord']!=null">
			 <td <s:if test="#request.defines['csorRecord']>0">colspan="${defines["csorRecord"]}" ${all?"width":"iwidth"}="${defines["csorRecord"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="csorRecord"   title="关联消费记录">
			 	<a class="${desc=="csor_record" ? "desc" : ""}${asc=="csor_record" ? "asc" : ""}" href="?${desc=="csor_record" ? "asc=csor_record" : ""}${(asc=="csor_record" || desc!="csor_record" )? "desc=csor_record" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	关联消费记录
			 	</a>
			 	${lz:set("checkeds[]","csorRecord")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csorMember || #request.defines['csorMember']!=null">
			 <td <s:if test="#request.defines['csorMember']>0">colspan="${defines["csorMember"]}" ${all?"width":"iwidth"}="${defines["csorMember"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="9" ref="csorMember"   title="关联会员">
			 	<a class="${desc=="csor_member" ? "desc" : ""}${asc=="csor_member" ? "asc" : ""}" href="?${desc=="csor_member" ? "asc=csor_member" : ""}${(asc=="csor_member" || desc!="csor_member" )? "desc=csor_member" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	关联会员
			 	</a>
			 	${lz:set("checkeds[]","csorMember")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csorOrder || #request.defines['csorOrder']!=null">
			 <td <s:if test="#request.defines['csorOrder']>0">colspan="${defines["csorOrder"]}" ${all?"width":"iwidth"}="${defines["csorOrder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="10" ref="csorOrder"   title="关联订单">
			 	<a class="${desc=="csor_order" ? "desc" : ""}${asc=="csor_order" ? "asc" : ""}" href="?${desc=="csor_order" ? "asc=csor_order" : ""}${(asc=="csor_order" || desc!="csor_order" )? "desc=csor_order" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	关联订单
			 	</a>
			 	${lz:set("checkeds[]","csorOrder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csorObject || #request.defines['csorObject']!=null">
			 <td <s:if test="#request.defines['csorObject']>0">colspan="${defines["csorObject"]}" ${all?"width":"iwidth"}="${defines["csorObject"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="11" ref="csorObject"   title="其它关联">
			 	<a class="${desc=="csor_object" ? "desc" : ""}${asc=="csor_object" ? "asc" : ""}" href="?${desc=="csor_object" ? "asc=csor_object" : ""}${(asc=="csor_object" || desc!="csor_object" )? "desc=csor_object" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	其它关联
			 	</a>
			 	${lz:set("checkeds[]","csorObject")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csorDateTime || #request.defines['csorDateTime']!=null">
			 <td <s:if test="#request.defines['csorDateTime']>0">colspan="${defines["csorDateTime"]}" ${all?"width":"iwidth"}="${defines["csorDateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="12" ref="csorDateTime"   title="发生时间">
			 	<a class="${desc=="csor_date_time" ? "desc" : ""}${asc=="csor_date_time" ? "asc" : ""}" href="?${desc=="csor_date_time" ? "asc=csor_date_time" : ""}${(asc=="csor_date_time" || desc!="csor_date_time" )? "desc=csor_date_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	发生时间
			 	</a>
			 	${lz:set("checkeds[]","csorDateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csorCashNo || #request.defines['csorCashNo']!=null">
			 <td <s:if test="#request.defines['csorCashNo']>0">colspan="${defines["csorCashNo"]}" ${all?"width":"iwidth"}="${defines["csorCashNo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="13" ref="csorCashNo"   title="结账流水">
			 	<a class="${desc=="csor_cash_no" ? "desc" : ""}${asc=="csor_cash_no" ? "asc" : ""}" href="?${desc=="csor_cash_no" ? "asc=csor_cash_no" : ""}${(asc=="csor_cash_no" || desc!="csor_cash_no" )? "desc=csor_cash_no" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	结账流水
			 	</a>
			 	${lz:set("checkeds[]","csorCashNo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csorAdder || #request.defines['csorAdder']!=null">
			 <td <s:if test="#request.defines['csorAdder']>0">colspan="${defines["csorAdder"]}" ${all?"width":"iwidth"}="${defines["csorAdder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="14" ref="csorAdder"   title="添加人">
			 	<a class="${desc=="csor_adder" ? "desc" : ""}${asc=="csor_adder" ? "asc" : ""}" href="?${desc=="csor_adder" ? "asc=csor_adder" : ""}${(asc=="csor_adder" || desc!="csor_adder" )? "desc=csor_adder" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加人
			 	</a>
			 	${lz:set("checkeds[]","csorAdder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csorStatus || #request.defines['csorStatus']!=null">
			 <td <s:if test="#request.defines['csorStatus']>0">colspan="${defines["csorStatus"]}" ${all?"width":"iwidth"}="${defines["csorStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="15" ref="csorStatus"   title="状态">
			 	<a class="${desc=="csor_status" ? "desc" : ""}${asc=="csor_status" ? "asc" : ""}" href="?${desc=="csor_status" ? "asc=csor_status" : ""}${(asc=="csor_status" || desc!="csor_status" )? "desc=csor_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","csorStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="16" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="17" ref="operation">
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
		  <tr id="${item.csorId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csorId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csorId || #request.defines['csorId']!=null">
				<s:if test="#request.defines['csorId']>0">
					${lz:set("注释","****csorId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csorId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsorId",lz:indexOf(fieldName,"csorId")>-1)}
				  		<s:if test="#request.atCsorId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csorId字段的字串格式化输出****")}
					<td ref="csorId" class="td ">
						 
						 	${lz:or(item$csorId[i.count-1],lz:left(item.csorId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csorHost || #request.defines['csorHost']!=null">
				<s:if test="#request.defines['csorHost']>0">
					${lz:set("注释","****csorHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csorHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsorHost",lz:indexOf(fieldName,"csorHost")>-1)}
				  		<s:if test="#request.atCsorHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csorHost字段的字串格式化输出****")}
					<td ref="csorHost" class="td " relate="${item.csorHost}">
						 
						 	<a <s:if test="#item.csorHost!=null && #item.csorHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.csorHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csorHost[i.count-1],lz:left(item.csorHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csorTitle || #request.defines['csorTitle']!=null">
				<s:if test="#request.defines['csorTitle']>0">
					${lz:set("注释","****csorTitle关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csorTitle))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsorTitle",lz:indexOf(fieldName,"csorTitle")>-1)}
				  		<s:if test="#request.atCsorTitle==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csorTitle字段的字串格式化输出****")}
					<td ref="csorTitle" class="td  node">
						 
						 	${lz:or(item$csorTitle[i.count-1],lz:left(item.csorTitle$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csorMoneyType || #request.defines['csorMoneyType']!=null">
				<s:if test="#request.defines['csorMoneyType']>0">
					${lz:set("注释","****csorMoneyType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csorMoneyType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsorMoneyType",lz:indexOf(fieldName,"csorMoneyType")>-1)}
				  		<s:if test="#request.atCsorMoneyType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csorMoneyType字段的字串格式化输出****")}
					<td ref="csorMoneyType" class="td ">
						 
						 	${lz:or(item$csorMoneyType[i.count-1],lz:left(item.csorMoneyType$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csorRecordType || #request.defines['csorRecordType']!=null">
				<s:if test="#request.defines['csorRecordType']>0">
					${lz:set("注释","****csorRecordType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csorRecordType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsorRecordType",lz:indexOf(fieldName,"csorRecordType")>-1)}
				  		<s:if test="#request.atCsorRecordType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csorRecordType字段的字串格式化输出****")}
					<td ref="csorRecordType" class="td " relate="${item.csorRecordType}">
						 
						 	<a <s:if test="#item.csorRecordType!=null && #item.csorRecordType!=''"> onclick="window.href('${basePath}${proname}/configurator/recordsubject_details.do?key=${item.csorRecordType}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csorRecordType[i.count-1],lz:left(item.csorRecordType$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csorIn || #request.defines['csorIn']!=null">
				<s:if test="#request.defines['csorIn']>0">
					${lz:set("注释","****csorIn关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csorIn))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsorIn",lz:indexOf(fieldName,"csorIn")>-1)}
				  		<s:if test="#request.atCsorIn==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csorIn字段的字串格式化输出****")}
					<td ref="csorIn" class="td ">
						 
						 	${lz:or(item$csorIn[i.count-1],lz:left(item.csorIn$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csorOut || #request.defines['csorOut']!=null">
				<s:if test="#request.defines['csorOut']>0">
					${lz:set("注释","****csorOut关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csorOut))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsorOut",lz:indexOf(fieldName,"csorOut")>-1)}
				  		<s:if test="#request.atCsorOut==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csorOut字段的字串格式化输出****")}
					<td ref="csorOut" class="td ">
						 
						 	${lz:or(item$csorOut[i.count-1],lz:left(item.csorOut$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csorRecord || #request.defines['csorRecord']!=null">
				<s:if test="#request.defines['csorRecord']>0">
					${lz:set("注释","****csorRecord关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csorRecord))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsorRecord",lz:indexOf(fieldName,"csorRecord")>-1)}
				  		<s:if test="#request.atCsorRecord==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csorRecord字段的字串格式化输出****")}
					<td ref="csorRecord" class="td " relate="${item.csorRecord}">
						 
						 	<a <s:if test="#item.csorRecord!=null && #item.csorRecord!=''"> onclick="window.href('${basePath}${proname}/finance/record_details.do?key=${item.csorRecord}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csorRecord[i.count-1],lz:left(item.csorRecord$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csorMember || #request.defines['csorMember']!=null">
				<s:if test="#request.defines['csorMember']>0">
					${lz:set("注释","****csorMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csorMember))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsorMember",lz:indexOf(fieldName,"csorMember")>-1)}
				  		<s:if test="#request.atCsorMember==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csorMember字段的字串格式化输出****")}
					<td ref="csorMember" class="td " relate="${item.csorMember}">
						 
						 	<a <s:if test="#item.csorMember!=null && #item.csorMember!=''"> onclick="window.href('${basePath}${proname}/user/member_details.do?key=${item.csorMember}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csorMember[i.count-1],lz:left(item.csorMember$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csorOrder || #request.defines['csorOrder']!=null">
				<s:if test="#request.defines['csorOrder']>0">
					${lz:set("注释","****csorOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csorOrder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsorOrder",lz:indexOf(fieldName,"csorOrder")>-1)}
				  		<s:if test="#request.atCsorOrder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csorOrder字段的字串格式化输出****")}
					<td ref="csorOrder" class="td " relate="${item.csorOrder}">
						 
						 	<a <s:if test="#item.csorOrder!=null && #item.csorOrder!=''"> onclick="window.href('${basePath}${proname}/service/order_details.do?key=${item.csorOrder}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csorOrder[i.count-1],lz:left(item.csorOrder$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csorObject || #request.defines['csorObject']!=null">
				<s:if test="#request.defines['csorObject']>0">
					${lz:set("注释","****csorObject关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csorObject))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsorObject",lz:indexOf(fieldName,"csorObject")>-1)}
				  		<s:if test="#request.atCsorObject==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csorObject字段的字串格式化输出****")}
					<td ref="csorObject" class="td ">
						 
						 	${lz:or(item$csorObject[i.count-1],lz:left(item.csorObject$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csorDateTime || #request.defines['csorDateTime']!=null">
				<s:if test="#request.defines['csorDateTime']>0">
					${lz:set("注释","****csorDateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csorDateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsorDateTime",lz:indexOf(fieldName,"csorDateTime")>-1)}
				  		<s:if test="#request.atCsorDateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csorDateTime字段的字串格式化输出****")}
					<td ref="csorDateTime" class="td ">
						 
						 	${lz:or(item$csorDateTime[i.count-1],lz:left(item.csorDateTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csorCashNo || #request.defines['csorCashNo']!=null">
				<s:if test="#request.defines['csorCashNo']>0">
					${lz:set("注释","****csorCashNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csorCashNo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsorCashNo",lz:indexOf(fieldName,"csorCashNo")>-1)}
				  		<s:if test="#request.atCsorCashNo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csorCashNo字段的字串格式化输出****")}
					<td ref="csorCashNo" class="td " relate="${item.csorCashNo}">
						 
						 	<a <s:if test="#item.csorCashNo!=null && #item.csorCashNo!=''"> onclick="window.href('${basePath}${proname}/finance/cashrecord_details.do?key=${item.csorCashNo}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csorCashNo[i.count-1],lz:left(item.csorCashNo$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csorAdder || #request.defines['csorAdder']!=null">
				<s:if test="#request.defines['csorAdder']>0">
					${lz:set("注释","****csorAdder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csorAdder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsorAdder",lz:indexOf(fieldName,"csorAdder")>-1)}
				  		<s:if test="#request.atCsorAdder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csorAdder字段的字串格式化输出****")}
					<td ref="csorAdder" class="td " relate="${item.csorAdder}">
						 
						 	<a <s:if test="#item.csorAdder!=null && #item.csorAdder!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.csorAdder}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csorAdder[i.count-1],lz:left(item.csorAdder$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csorStatus || #request.defines['csorStatus']!=null">
				<s:if test="#request.defines['csorStatus']>0">
					${lz:set("注释","****csorStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csorStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsorStatus",lz:indexOf(fieldName,"csorStatus")>-1)}
				  		<s:if test="#request.atCsorStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csorStatus字段的字串格式化输出****")}
					<td ref="csorStatus" class="td ">
						 
						 	${lz:or(item$csorStatus[i.count-1],lz:left(item.csorStatus$,100))}
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
					<a href="javascript:HandleSel('${handle}','${item.csorId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectOperecord('${item.csorId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelOperecord('${item.csorId}','${item.csorTitle}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsOperecord('${item.csorId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csorId || #request.defines['csorId']!=null">
				<s:if test="#request.defines['csorId']>0">
					${lz:set("注释","****csorId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsorId",lz:indexOf(fieldName,"csorId")>-1)}				  		
				  		<s:if test="#request.atCsorId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csorId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csorHost || #request.defines['csorHost']!=null">
				<s:if test="#request.defines['csorHost']>0">
					${lz:set("注释","****csorHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsorHost",lz:indexOf(fieldName,"csorHost")>-1)}				  		
				  		<s:if test="#request.atCsorHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csorHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csorTitle || #request.defines['csorTitle']!=null">
				<s:if test="#request.defines['csorTitle']>0">
					${lz:set("注释","****csorTitle关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsorTitle",lz:indexOf(fieldName,"csorTitle")>-1)}				  		
				  		<s:if test="#request.atCsorTitle==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csorTitle[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csorMoneyType || #request.defines['csorMoneyType']!=null">
				<s:if test="#request.defines['csorMoneyType']>0">
					${lz:set("注释","****csorMoneyType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsorMoneyType",lz:indexOf(fieldName,"csorMoneyType")>-1)}				  		
				  		<s:if test="#request.atCsorMoneyType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csorMoneyType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csorRecordType || #request.defines['csorRecordType']!=null">
				<s:if test="#request.defines['csorRecordType']>0">
					${lz:set("注释","****csorRecordType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsorRecordType",lz:indexOf(fieldName,"csorRecordType")>-1)}				  		
				  		<s:if test="#request.atCsorRecordType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csorRecordType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csorIn || #request.defines['csorIn']!=null">
				<s:if test="#request.defines['csorIn']>0">
					${lz:set("注释","****csorIn关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsorIn",lz:indexOf(fieldName,"csorIn")>-1)}				  		
				  		<s:if test="#request.atCsorIn==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csorIn[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csorOut || #request.defines['csorOut']!=null">
				<s:if test="#request.defines['csorOut']>0">
					${lz:set("注释","****csorOut关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsorOut",lz:indexOf(fieldName,"csorOut")>-1)}				  		
				  		<s:if test="#request.atCsorOut==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csorOut[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csorRecord || #request.defines['csorRecord']!=null">
				<s:if test="#request.defines['csorRecord']>0">
					${lz:set("注释","****csorRecord关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsorRecord",lz:indexOf(fieldName,"csorRecord")>-1)}				  		
				  		<s:if test="#request.atCsorRecord==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csorRecord[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csorMember || #request.defines['csorMember']!=null">
				<s:if test="#request.defines['csorMember']>0">
					${lz:set("注释","****csorMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsorMember",lz:indexOf(fieldName,"csorMember")>-1)}				  		
				  		<s:if test="#request.atCsorMember==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csorMember[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csorOrder || #request.defines['csorOrder']!=null">
				<s:if test="#request.defines['csorOrder']>0">
					${lz:set("注释","****csorOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsorOrder",lz:indexOf(fieldName,"csorOrder")>-1)}				  		
				  		<s:if test="#request.atCsorOrder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csorOrder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csorObject || #request.defines['csorObject']!=null">
				<s:if test="#request.defines['csorObject']>0">
					${lz:set("注释","****csorObject关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsorObject",lz:indexOf(fieldName,"csorObject")>-1)}				  		
				  		<s:if test="#request.atCsorObject==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csorObject[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csorDateTime || #request.defines['csorDateTime']!=null">
				<s:if test="#request.defines['csorDateTime']>0">
					${lz:set("注释","****csorDateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsorDateTime",lz:indexOf(fieldName,"csorDateTime")>-1)}				  		
				  		<s:if test="#request.atCsorDateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csorDateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csorCashNo || #request.defines['csorCashNo']!=null">
				<s:if test="#request.defines['csorCashNo']>0">
					${lz:set("注释","****csorCashNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsorCashNo",lz:indexOf(fieldName,"csorCashNo")>-1)}				  		
				  		<s:if test="#request.atCsorCashNo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csorCashNo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csorAdder || #request.defines['csorAdder']!=null">
				<s:if test="#request.defines['csorAdder']>0">
					${lz:set("注释","****csorAdder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsorAdder",lz:indexOf(fieldName,"csorAdder")>-1)}				  		
				  		<s:if test="#request.atCsorAdder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csorAdder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csorStatus || #request.defines['csorStatus']!=null">
				<s:if test="#request.defines['csorStatus']>0">
					${lz:set("注释","****csorStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsorStatus",lz:indexOf(fieldName,"csorStatus")>-1)}				  		
				  		<s:if test="#request.atCsorStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csorStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 选择收支记录
	**/
	function SelectOperecord(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改收支记录任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}operecord_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新收支记录",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}operecord_edit.do";
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
			var url = "${basePath}${namespace}operecord_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除收支记录
	**/
	function DelOperecord(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除收支记录["+flag+"]吗？</span>",
				title:"删除收支记录",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}operecord_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选收支记录吗？</span>",
				title:"批量删除收支记录",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}operecord_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示收支记录详情
	**/
	function DetailsOperecord(id)
	{
		var url = "${basePath}${namespace}operecord_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"收支记录详情",editable:false,visible:true}};
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
		DetailsOperecord(checker.val());
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