<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/finance/invoice.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csInvoice.csiId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csiId,csiHost,csiTitle,csiContent,csiMember,csiSendType,csiAddress,csiChecker,csiEditor,csiAdder,csiCheckTime,csiInvoiceTime,csiUpdateTime,csiAddTime,csiStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csiId,csiHost,csiTitle,csiContent,csiType,csiTariffNo,csiMoney,csiMember,csiSendType,csiAddress,csiChecker,csiEditor,csiAdder,csiRemark,csiCheckTime,csiInvoiceTime,csiUpdateTime,csiAddTime,csiStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csiHost,csiTitle,csiContent,csiMember,csiSendType,csiAdder,csiCheckTime,csiInvoiceTime,csiStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csiHost,csiTitle,csiContent,csiType,csiMoney,csiMember,csiSendType,csiAdder,csiCheckTime,csiInvoiceTime,csiStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>发票记录管理</title>
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
	<span class="caption">${empty title?"发票记录管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsInvoice" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="invoice.query.jsp"%>

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
			<button type="button" class="add" onclick="AddInvoice();">
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
			<dl ref="com.ccclubs.model.CsInvoice" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.CsInvoice" class="batch dropdowan">
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
		<table id="invoice_table" ref="CsInvoice" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiId || #request.defines['csiId']!=null">
			 <td <s:if test="#request.defines['csiId']>0">colspan="${defines["csiId"]}" ${all?"width":"iwidth"}="${defines["csiId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="csiId"   title="编号">
			 	<a class="${desc=="csi_id" ? "desc" : ""}${asc=="csi_id" ? "asc" : ""}" href="?${desc=="csi_id" ? "asc=csi_id" : ""}${(asc=="csi_id" || desc!="csi_id" )? "desc=csi_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","csiId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiHost || #request.defines['csiHost']!=null">
			 <td <s:if test="#request.defines['csiHost']>0">colspan="${defines["csiHost"]}" ${all?"width":"iwidth"}="${defines["csiHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="csiHost"   title="城市">
			 	<a class="${desc=="csi_host" ? "desc" : ""}${asc=="csi_host" ? "asc" : ""}" href="?${desc=="csi_host" ? "asc=csi_host" : ""}${(asc=="csi_host" || desc!="csi_host" )? "desc=csi_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","csiHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiTitle || #request.defines['csiTitle']!=null">
			 <td <s:if test="#request.defines['csiTitle']>0">colspan="${defines["csiTitle"]}" ${all?"width":"iwidth"}="${defines["csiTitle"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="3" ref="csiTitle" flagKey  title="抬头名称">
			 	<a class="${desc=="csi_title" ? "desc" : ""}${asc=="csi_title" ? "asc" : ""}" href="?${desc=="csi_title" ? "asc=csi_title" : ""}${(asc=="csi_title" || desc!="csi_title" )? "desc=csi_title" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	抬头名称
			 	</a>
			 	${lz:set("checkeds[]","csiTitle")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiContent || #request.defines['csiContent']!=null">
			 <td <s:if test="#request.defines['csiContent']>0">colspan="${defines["csiContent"]}" ${all?"width":"iwidth"}="${defines["csiContent"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="csiContent"   title="开票内容">
			 	<a class="${desc=="csi_content" ? "desc" : ""}${asc=="csi_content" ? "asc" : ""}" href="?${desc=="csi_content" ? "asc=csi_content" : ""}${(asc=="csi_content" || desc!="csi_content" )? "desc=csi_content" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	开票内容
			 	</a>
			 	${lz:set("checkeds[]","csiContent")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiType || #request.defines['csiType']!=null">
			 <td <s:if test="#request.defines['csiType']>0">colspan="${defines["csiType"]}" ${all?"width":"iwidth"}="${defines["csiType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="csiType"   title="抬头类型">
			 	<a class="${desc=="csi_type" ? "desc" : ""}${asc=="csi_type" ? "asc" : ""}" href="?${desc=="csi_type" ? "asc=csi_type" : ""}${(asc=="csi_type" || desc!="csi_type" )? "desc=csi_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	抬头类型
			 	</a>
			 	${lz:set("checkeds[]","csiType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiTariffNo || #request.defines['csiTariffNo']!=null">
			 <td <s:if test="#request.defines['csiTariffNo']>0">colspan="${defines["csiTariffNo"]}" ${all?"width":"iwidth"}="${defines["csiTariffNo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="6" ref="csiTariffNo"   title="税号">
			 	<a class="${desc=="csi_tariff_no" ? "desc" : ""}${asc=="csi_tariff_no" ? "asc" : ""}" href="?${desc=="csi_tariff_no" ? "asc=csi_tariff_no" : ""}${(asc=="csi_tariff_no" || desc!="csi_tariff_no" )? "desc=csi_tariff_no" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	税号
			 	</a>
			 	${lz:set("checkeds[]","csiTariffNo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiMoney || #request.defines['csiMoney']!=null">
			 <td <s:if test="#request.defines['csiMoney']>0">colspan="${defines["csiMoney"]}" ${all?"width":"iwidth"}="${defines["csiMoney"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="7" ref="csiMoney"   title="开票额">
			 	<a class="${desc=="csi_money" ? "desc" : ""}${asc=="csi_money" ? "asc" : ""}" href="?${desc=="csi_money" ? "asc=csi_money" : ""}${(asc=="csi_money" || desc!="csi_money" )? "desc=csi_money" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	开票额
			 	</a>
			 	${lz:set("checkeds[]","csiMoney")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiMember || #request.defines['csiMember']!=null">
			 <td <s:if test="#request.defines['csiMember']>0">colspan="${defines["csiMember"]}" ${all?"width":"iwidth"}="${defines["csiMember"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="csiMember"   title="所属会员">
			 	<a class="${desc=="csi_member" ? "desc" : ""}${asc=="csi_member" ? "asc" : ""}" href="?${desc=="csi_member" ? "asc=csi_member" : ""}${(asc=="csi_member" || desc!="csi_member" )? "desc=csi_member" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属会员
			 	</a>
			 	${lz:set("checkeds[]","csiMember")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiSendType || #request.defines['csiSendType']!=null">
			 <td <s:if test="#request.defines['csiSendType']>0">colspan="${defines["csiSendType"]}" ${all?"width":"iwidth"}="${defines["csiSendType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="9" ref="csiSendType"   title="寄送方式">
			 	<a class="${desc=="csi_send_type" ? "desc" : ""}${asc=="csi_send_type" ? "asc" : ""}" href="?${desc=="csi_send_type" ? "asc=csi_send_type" : ""}${(asc=="csi_send_type" || desc!="csi_send_type" )? "desc=csi_send_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	寄送方式
			 	</a>
			 	${lz:set("checkeds[]","csiSendType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiAddress || #request.defines['csiAddress']!=null">
			 <td <s:if test="#request.defines['csiAddress']>0">colspan="${defines["csiAddress"]}" ${all?"width":"iwidth"}="${defines["csiAddress"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="10" ref="csiAddress"   title="寄送地址">
			 	<a class="${desc=="csi_address" ? "desc" : ""}${asc=="csi_address" ? "asc" : ""}" href="?${desc=="csi_address" ? "asc=csi_address" : ""}${(asc=="csi_address" || desc!="csi_address" )? "desc=csi_address" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	寄送地址
			 	</a>
			 	${lz:set("checkeds[]","csiAddress")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiChecker || #request.defines['csiChecker']!=null">
			 <td <s:if test="#request.defines['csiChecker']>0">colspan="${defines["csiChecker"]}" ${all?"width":"iwidth"}="${defines["csiChecker"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="11" ref="csiChecker"   title="审核人">
			 	<a class="${desc=="csi_checker" ? "desc" : ""}${asc=="csi_checker" ? "asc" : ""}" href="?${desc=="csi_checker" ? "asc=csi_checker" : ""}${(asc=="csi_checker" || desc!="csi_checker" )? "desc=csi_checker" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	审核人
			 	</a>
			 	${lz:set("checkeds[]","csiChecker")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiEditor || #request.defines['csiEditor']!=null">
			 <td <s:if test="#request.defines['csiEditor']>0">colspan="${defines["csiEditor"]}" ${all?"width":"iwidth"}="${defines["csiEditor"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="12" ref="csiEditor"   title="开票人">
			 	<a class="${desc=="csi_editor" ? "desc" : ""}${asc=="csi_editor" ? "asc" : ""}" href="?${desc=="csi_editor" ? "asc=csi_editor" : ""}${(asc=="csi_editor" || desc!="csi_editor" )? "desc=csi_editor" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	开票人
			 	</a>
			 	${lz:set("checkeds[]","csiEditor")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiAdder || #request.defines['csiAdder']!=null">
			 <td <s:if test="#request.defines['csiAdder']>0">colspan="${defines["csiAdder"]}" ${all?"width":"iwidth"}="${defines["csiAdder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="13" ref="csiAdder"   title="添加人">
			 	<a class="${desc=="csi_adder" ? "desc" : ""}${asc=="csi_adder" ? "asc" : ""}" href="?${desc=="csi_adder" ? "asc=csi_adder" : ""}${(asc=="csi_adder" || desc!="csi_adder" )? "desc=csi_adder" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加人
			 	</a>
			 	${lz:set("checkeds[]","csiAdder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiRemark || #request.defines['csiRemark']!=null">
			 <td <s:if test="#request.defines['csiRemark']>0">colspan="${defines["csiRemark"]}" ${all?"width":"iwidth"}="${defines["csiRemark"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="14" ref="csiRemark"   title="备注信息">
			 	<a class="${desc=="csi_remark" ? "desc" : ""}${asc=="csi_remark" ? "asc" : ""}" href="?${desc=="csi_remark" ? "asc=csi_remark" : ""}${(asc=="csi_remark" || desc!="csi_remark" )? "desc=csi_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备注信息
			 	</a>
			 	${lz:set("checkeds[]","csiRemark")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiCheckTime || #request.defines['csiCheckTime']!=null">
			 <td <s:if test="#request.defines['csiCheckTime']>0">colspan="${defines["csiCheckTime"]}" ${all?"width":"iwidth"}="${defines["csiCheckTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="15" ref="csiCheckTime"   title="审核时间">
			 	<a class="${desc=="csi_check_time" ? "desc" : ""}${asc=="csi_check_time" ? "asc" : ""}" href="?${desc=="csi_check_time" ? "asc=csi_check_time" : ""}${(asc=="csi_check_time" || desc!="csi_check_time" )? "desc=csi_check_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	审核时间
			 	</a>
			 	${lz:set("checkeds[]","csiCheckTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiInvoiceTime || #request.defines['csiInvoiceTime']!=null">
			 <td <s:if test="#request.defines['csiInvoiceTime']>0">colspan="${defines["csiInvoiceTime"]}" ${all?"width":"iwidth"}="${defines["csiInvoiceTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="16" ref="csiInvoiceTime"   title="开票时间">
			 	<a class="${desc=="csi_invoice_time" ? "desc" : ""}${asc=="csi_invoice_time" ? "asc" : ""}" href="?${desc=="csi_invoice_time" ? "asc=csi_invoice_time" : ""}${(asc=="csi_invoice_time" || desc!="csi_invoice_time" )? "desc=csi_invoice_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	开票时间
			 	</a>
			 	${lz:set("checkeds[]","csiInvoiceTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiUpdateTime || #request.defines['csiUpdateTime']!=null">
			 <td <s:if test="#request.defines['csiUpdateTime']>0">colspan="${defines["csiUpdateTime"]}" ${all?"width":"iwidth"}="${defines["csiUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="17" ref="csiUpdateTime"   title="修改时间">
			 	<a class="${desc=="csi_update_time" ? "desc" : ""}${asc=="csi_update_time" ? "asc" : ""}" href="?${desc=="csi_update_time" ? "asc=csi_update_time" : ""}${(asc=="csi_update_time" || desc!="csi_update_time" )? "desc=csi_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 	${lz:set("checkeds[]","csiUpdateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiAddTime || #request.defines['csiAddTime']!=null">
			 <td <s:if test="#request.defines['csiAddTime']>0">colspan="${defines["csiAddTime"]}" ${all?"width":"iwidth"}="${defines["csiAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="18" ref="csiAddTime"   title="添加时间">
			 	<a class="${desc=="csi_add_time" ? "desc" : ""}${asc=="csi_add_time" ? "asc" : ""}" href="?${desc=="csi_add_time" ? "asc=csi_add_time" : ""}${(asc=="csi_add_time" || desc!="csi_add_time" )? "desc=csi_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","csiAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csiStatus || #request.defines['csiStatus']!=null">
			 <td <s:if test="#request.defines['csiStatus']>0">colspan="${defines["csiStatus"]}" ${all?"width":"iwidth"}="${defines["csiStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="19" ref="csiStatus"   title="状态">
			 	<a class="${desc=="csi_status" ? "desc" : ""}${asc=="csi_status" ? "asc" : ""}" href="?${desc=="csi_status" ? "asc=csi_status" : ""}${(asc=="csi_status" || desc!="csi_status" )? "desc=csi_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","csiStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="20" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="21" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:AddInvoice()" style="text-decoration:underline">添加</a>
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
		  <tr id="${item.csiId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csiId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiId || #request.defines['csiId']!=null">
				<s:if test="#request.defines['csiId']>0">
					${lz:set("注释","****csiId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiId",lz:indexOf(fieldName,"csiId")>-1)}
				  		<s:if test="#request.atCsiId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiId字段的字串格式化输出****")}
					<td ref="csiId" class="td ">
						 
						 	${lz:or(item$csiId[i.count-1],lz:left(item.csiId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiHost || #request.defines['csiHost']!=null">
				<s:if test="#request.defines['csiHost']>0">
					${lz:set("注释","****csiHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiHost",lz:indexOf(fieldName,"csiHost")>-1)}
				  		<s:if test="#request.atCsiHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiHost字段的字串格式化输出****")}
					<td ref="csiHost" class="td " relate="${item.csiHost}">
						 
						 	<a <s:if test="#item.csiHost!=null && #item.csiHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.csiHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csiHost[i.count-1],lz:left(item.csiHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiTitle || #request.defines['csiTitle']!=null">
				<s:if test="#request.defines['csiTitle']>0">
					${lz:set("注释","****csiTitle关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiTitle))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiTitle",lz:indexOf(fieldName,"csiTitle")>-1)}
				  		<s:if test="#request.atCsiTitle==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiTitle字段的字串格式化输出****")}
					<td ref="csiTitle" class="td  node">
						 
						 	${lz:or(item$csiTitle[i.count-1],lz:left(item.csiTitle$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiContent || #request.defines['csiContent']!=null">
				<s:if test="#request.defines['csiContent']>0">
					${lz:set("注释","****csiContent关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiContent))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiContent",lz:indexOf(fieldName,"csiContent")>-1)}
				  		<s:if test="#request.atCsiContent==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiContent字段的字串格式化输出****")}
					<td ref="csiContent" class="td " relate="${item.csiContent}">
						 
						 	<a <s:if test="#item.csiContent!=null && #item.csiContent!=''"> onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${item.csiContent}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csiContent[i.count-1],lz:left(item.csiContent$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiType || #request.defines['csiType']!=null">
				<s:if test="#request.defines['csiType']>0">
					${lz:set("注释","****csiType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiType",lz:indexOf(fieldName,"csiType")>-1)}
				  		<s:if test="#request.atCsiType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiType字段的字串格式化输出****")}
					<td ref="csiType" class="td ">
						 
						 	${lz:or(item$csiType[i.count-1],lz:left(item.csiType$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiTariffNo || #request.defines['csiTariffNo']!=null">
				<s:if test="#request.defines['csiTariffNo']>0">
					${lz:set("注释","****csiTariffNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiTariffNo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiTariffNo",lz:indexOf(fieldName,"csiTariffNo")>-1)}
				  		<s:if test="#request.atCsiTariffNo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiTariffNo字段的字串格式化输出****")}
					<td ref="csiTariffNo" class="td ">
						 
						 	${lz:or(item$csiTariffNo[i.count-1],lz:left(item.csiTariffNo$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiMoney || #request.defines['csiMoney']!=null">
				<s:if test="#request.defines['csiMoney']>0">
					${lz:set("注释","****csiMoney关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiMoney))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiMoney",lz:indexOf(fieldName,"csiMoney")>-1)}
				  		<s:if test="#request.atCsiMoney==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiMoney字段的字串格式化输出****")}
					<td ref="csiMoney" class="td ">
						 
						 	${lz:or(item$csiMoney[i.count-1],lz:left(item.csiMoney$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiMember || #request.defines['csiMember']!=null">
				<s:if test="#request.defines['csiMember']>0">
					${lz:set("注释","****csiMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiMember))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiMember",lz:indexOf(fieldName,"csiMember")>-1)}
				  		<s:if test="#request.atCsiMember==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiMember字段的字串格式化输出****")}
					<td ref="csiMember" class="td " relate="${item.csiMember}">
						 
						 	<a <s:if test="#item.csiMember!=null && #item.csiMember!=''"> onclick="window.href('${basePath}${proname}/user/member_details.do?key=${item.csiMember}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csiMember[i.count-1],lz:left(item.csiMember$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiSendType || #request.defines['csiSendType']!=null">
				<s:if test="#request.defines['csiSendType']>0">
					${lz:set("注释","****csiSendType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiSendType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiSendType",lz:indexOf(fieldName,"csiSendType")>-1)}
				  		<s:if test="#request.atCsiSendType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiSendType字段的字串格式化输出****")}
					<td ref="csiSendType" class="td ">
						 
						 	${lz:or(item$csiSendType[i.count-1],lz:left(item.csiSendType$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiAddress || #request.defines['csiAddress']!=null">
				<s:if test="#request.defines['csiAddress']>0">
					${lz:set("注释","****csiAddress关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiAddress))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiAddress",lz:indexOf(fieldName,"csiAddress")>-1)}
				  		<s:if test="#request.atCsiAddress==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiAddress字段的字串格式化输出****")}
					<td ref="csiAddress" class="td " relate="${item.csiAddress}">
						 
						 	<a <s:if test="#item.csiAddress!=null && #item.csiAddress!=''"> onclick="window.href('${basePath}${proname}/user/address_details.do?key=${item.csiAddress}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csiAddress[i.count-1],lz:left(item.csiAddress$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiChecker || #request.defines['csiChecker']!=null">
				<s:if test="#request.defines['csiChecker']>0">
					${lz:set("注释","****csiChecker关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiChecker))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiChecker",lz:indexOf(fieldName,"csiChecker")>-1)}
				  		<s:if test="#request.atCsiChecker==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiChecker字段的字串格式化输出****")}
					<td ref="csiChecker" class="td " relate="${item.csiChecker}">
						 
						 	<a <s:if test="#item.csiChecker!=null && #item.csiChecker!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.csiChecker}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csiChecker[i.count-1],lz:left(item.csiChecker$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiEditor || #request.defines['csiEditor']!=null">
				<s:if test="#request.defines['csiEditor']>0">
					${lz:set("注释","****csiEditor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiEditor))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiEditor",lz:indexOf(fieldName,"csiEditor")>-1)}
				  		<s:if test="#request.atCsiEditor==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiEditor字段的字串格式化输出****")}
					<td ref="csiEditor" class="td " relate="${item.csiEditor}">
						 
						 	<a <s:if test="#item.csiEditor!=null && #item.csiEditor!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.csiEditor}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csiEditor[i.count-1],lz:left(item.csiEditor$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiAdder || #request.defines['csiAdder']!=null">
				<s:if test="#request.defines['csiAdder']>0">
					${lz:set("注释","****csiAdder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiAdder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiAdder",lz:indexOf(fieldName,"csiAdder")>-1)}
				  		<s:if test="#request.atCsiAdder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiAdder字段的字串格式化输出****")}
					<td ref="csiAdder" class="td " relate="${item.csiAdder}">
						 
						 	<a <s:if test="#item.csiAdder!=null && #item.csiAdder!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.csiAdder}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csiAdder[i.count-1],lz:left(item.csiAdder$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiRemark || #request.defines['csiRemark']!=null">
				<s:if test="#request.defines['csiRemark']>0">
					${lz:set("注释","****csiRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiRemark))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiRemark",lz:indexOf(fieldName,"csiRemark")>-1)}
				  		<s:if test="#request.atCsiRemark==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiRemark字段的字串格式化输出****")}
					<td ref="csiRemark" class="td ">
						 
						 	${lz:or(item$csiRemark[i.count-1],lz:left(item.csiRemark$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiCheckTime || #request.defines['csiCheckTime']!=null">
				<s:if test="#request.defines['csiCheckTime']>0">
					${lz:set("注释","****csiCheckTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiCheckTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiCheckTime",lz:indexOf(fieldName,"csiCheckTime")>-1)}
				  		<s:if test="#request.atCsiCheckTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiCheckTime字段的字串格式化输出****")}
					<td ref="csiCheckTime" class="td ">
						 
						 	${lz:or(item$csiCheckTime[i.count-1],lz:left(item.csiCheckTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiInvoiceTime || #request.defines['csiInvoiceTime']!=null">
				<s:if test="#request.defines['csiInvoiceTime']>0">
					${lz:set("注释","****csiInvoiceTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiInvoiceTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiInvoiceTime",lz:indexOf(fieldName,"csiInvoiceTime")>-1)}
				  		<s:if test="#request.atCsiInvoiceTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiInvoiceTime字段的字串格式化输出****")}
					<td ref="csiInvoiceTime" class="td ">
						 
						 	${lz:or(item$csiInvoiceTime[i.count-1],lz:left(item.csiInvoiceTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiUpdateTime || #request.defines['csiUpdateTime']!=null">
				<s:if test="#request.defines['csiUpdateTime']>0">
					${lz:set("注释","****csiUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiUpdateTime",lz:indexOf(fieldName,"csiUpdateTime")>-1)}
				  		<s:if test="#request.atCsiUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiUpdateTime字段的字串格式化输出****")}
					<td ref="csiUpdateTime" class="td ">
						 
						 	${lz:or(item$csiUpdateTime[i.count-1],lz:left(item.csiUpdateTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiAddTime || #request.defines['csiAddTime']!=null">
				<s:if test="#request.defines['csiAddTime']>0">
					${lz:set("注释","****csiAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiAddTime",lz:indexOf(fieldName,"csiAddTime")>-1)}
				  		<s:if test="#request.atCsiAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiAddTime字段的字串格式化输出****")}
					<td ref="csiAddTime" class="td ">
						 
						 	${lz:or(item$csiAddTime[i.count-1],lz:left(item.csiAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csiStatus || #request.defines['csiStatus']!=null">
				<s:if test="#request.defines['csiStatus']>0">
					${lz:set("注释","****csiStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csiStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsiStatus",lz:indexOf(fieldName,"csiStatus")>-1)}
				  		<s:if test="#request.atCsiStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csiStatus字段的字串格式化输出****")}
					<td ref="csiStatus" class="td ">
						 
						 	${lz:or(item$csiStatus[i.count-1],lz:left(item.csiStatus$,100))}
					</td>
				</s:else>
			</s:if>
		   ${lz:set("注释","*****************数据列表数据部分结束*****************")}		  
		  
		   
		    ${lz:set("注释","****自定义的单行记录操作句柄[request中的handles(String[])变量]****")}
			<td class="options">
				  <s:if test="#request.alias==null">	
					<s:if test="#request.CTRL.canExp[0]==true">
					<a href="javascript:HandleSel('审核','${item.csiId}')">审核</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[1]==true">
					<a href="javascript:HandleSel('开票','${item.csiId}')">开票</a>
					</s:if>
				</s:if>
				${lz:set("items",lz:split(handles,","))}
				<s:iterator value="#request.items" id="handle" status="i">
					${lz:set("isMenu",(lz:indexOf(handle,"@")==0))}
					<s:if test="#request.isMenu!=true">
					<a href="javascript:HandleSel('${handle}','${item.csiId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectInvoice('${item.csiId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditInvoice('${item.csiId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelInvoice('${item.csiId}','${item.csiTitle}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsInvoice('${item.csiId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csiId || #request.defines['csiId']!=null">
				<s:if test="#request.defines['csiId']>0">
					${lz:set("注释","****csiId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiId",lz:indexOf(fieldName,"csiId")>-1)}				  		
				  		<s:if test="#request.atCsiId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiHost || #request.defines['csiHost']!=null">
				<s:if test="#request.defines['csiHost']>0">
					${lz:set("注释","****csiHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiHost",lz:indexOf(fieldName,"csiHost")>-1)}				  		
				  		<s:if test="#request.atCsiHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiTitle || #request.defines['csiTitle']!=null">
				<s:if test="#request.defines['csiTitle']>0">
					${lz:set("注释","****csiTitle关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiTitle",lz:indexOf(fieldName,"csiTitle")>-1)}				  		
				  		<s:if test="#request.atCsiTitle==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiTitle[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiContent || #request.defines['csiContent']!=null">
				<s:if test="#request.defines['csiContent']>0">
					${lz:set("注释","****csiContent关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiContent",lz:indexOf(fieldName,"csiContent")>-1)}				  		
				  		<s:if test="#request.atCsiContent==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiContent[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiType || #request.defines['csiType']!=null">
				<s:if test="#request.defines['csiType']>0">
					${lz:set("注释","****csiType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiType",lz:indexOf(fieldName,"csiType")>-1)}				  		
				  		<s:if test="#request.atCsiType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiTariffNo || #request.defines['csiTariffNo']!=null">
				<s:if test="#request.defines['csiTariffNo']>0">
					${lz:set("注释","****csiTariffNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiTariffNo",lz:indexOf(fieldName,"csiTariffNo")>-1)}				  		
				  		<s:if test="#request.atCsiTariffNo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiTariffNo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiMoney || #request.defines['csiMoney']!=null">
				<s:if test="#request.defines['csiMoney']>0">
					${lz:set("注释","****csiMoney关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiMoney",lz:indexOf(fieldName,"csiMoney")>-1)}				  		
				  		<s:if test="#request.atCsiMoney==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiMoney[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiMember || #request.defines['csiMember']!=null">
				<s:if test="#request.defines['csiMember']>0">
					${lz:set("注释","****csiMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiMember",lz:indexOf(fieldName,"csiMember")>-1)}				  		
				  		<s:if test="#request.atCsiMember==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiMember[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiSendType || #request.defines['csiSendType']!=null">
				<s:if test="#request.defines['csiSendType']>0">
					${lz:set("注释","****csiSendType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiSendType",lz:indexOf(fieldName,"csiSendType")>-1)}				  		
				  		<s:if test="#request.atCsiSendType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiSendType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiAddress || #request.defines['csiAddress']!=null">
				<s:if test="#request.defines['csiAddress']>0">
					${lz:set("注释","****csiAddress关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiAddress",lz:indexOf(fieldName,"csiAddress")>-1)}				  		
				  		<s:if test="#request.atCsiAddress==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiAddress[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiChecker || #request.defines['csiChecker']!=null">
				<s:if test="#request.defines['csiChecker']>0">
					${lz:set("注释","****csiChecker关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiChecker",lz:indexOf(fieldName,"csiChecker")>-1)}				  		
				  		<s:if test="#request.atCsiChecker==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiChecker[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiEditor || #request.defines['csiEditor']!=null">
				<s:if test="#request.defines['csiEditor']>0">
					${lz:set("注释","****csiEditor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiEditor",lz:indexOf(fieldName,"csiEditor")>-1)}				  		
				  		<s:if test="#request.atCsiEditor==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiEditor[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiAdder || #request.defines['csiAdder']!=null">
				<s:if test="#request.defines['csiAdder']>0">
					${lz:set("注释","****csiAdder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiAdder",lz:indexOf(fieldName,"csiAdder")>-1)}				  		
				  		<s:if test="#request.atCsiAdder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiAdder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiRemark || #request.defines['csiRemark']!=null">
				<s:if test="#request.defines['csiRemark']>0">
					${lz:set("注释","****csiRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiRemark",lz:indexOf(fieldName,"csiRemark")>-1)}				  		
				  		<s:if test="#request.atCsiRemark==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiRemark[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiCheckTime || #request.defines['csiCheckTime']!=null">
				<s:if test="#request.defines['csiCheckTime']>0">
					${lz:set("注释","****csiCheckTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiCheckTime",lz:indexOf(fieldName,"csiCheckTime")>-1)}				  		
				  		<s:if test="#request.atCsiCheckTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiCheckTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiInvoiceTime || #request.defines['csiInvoiceTime']!=null">
				<s:if test="#request.defines['csiInvoiceTime']>0">
					${lz:set("注释","****csiInvoiceTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiInvoiceTime",lz:indexOf(fieldName,"csiInvoiceTime")>-1)}				  		
				  		<s:if test="#request.atCsiInvoiceTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiInvoiceTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiUpdateTime || #request.defines['csiUpdateTime']!=null">
				<s:if test="#request.defines['csiUpdateTime']>0">
					${lz:set("注释","****csiUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiUpdateTime",lz:indexOf(fieldName,"csiUpdateTime")>-1)}				  		
				  		<s:if test="#request.atCsiUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiAddTime || #request.defines['csiAddTime']!=null">
				<s:if test="#request.defines['csiAddTime']>0">
					${lz:set("注释","****csiAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiAddTime",lz:indexOf(fieldName,"csiAddTime")>-1)}				  		
				  		<s:if test="#request.atCsiAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csiStatus || #request.defines['csiStatus']!=null">
				<s:if test="#request.defines['csiStatus']>0">
					${lz:set("注释","****csiStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsiStatus",lz:indexOf(fieldName,"csiStatus")>-1)}				  		
				  		<s:if test="#request.atCsiStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csiStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 添加发票记录
	**/
	function AddInvoice(parent){		
		var url = "${basePath}${namespace}invoice_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加发票记录'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择发票记录
	**/
	function SelectInvoice(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改发票记录
	**/
	function EditInvoice(id){
		var url = "${basePath}${namespace}invoice_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改发票记录'}":lz:json(ctrl)}};
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
		EditInvoice(checker.val());
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
				var url = "${basePath}${namespace}invoice_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新发票记录",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}invoice_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新发票记录",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改发票记录
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}invoice_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新发票记录",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改发票记录任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}invoice_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新发票记录",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}invoice_edit.do";
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
			var url = "${basePath}${namespace}invoice_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除发票记录
	**/
	function DelInvoice(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除发票记录["+flag+"]吗？</span>",
				title:"删除发票记录",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}invoice_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选发票记录吗？</span>",
				title:"批量删除发票记录",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}invoice_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示发票记录详情
	**/
	function DetailsInvoice(id)
	{
		var url = "${basePath}${namespace}invoice_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"发票记录详情",editable:false,visible:true}};
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
		DetailsInvoice(checker.val());
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