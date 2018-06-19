<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/service/longorder.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csLongOrder.csloId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csloId,csloHost,csloPackNo,csloMember,csloPackProfile,csloPayCycle,csloPactTime,csloStartTime,csloFinishTime,csloUpdateTime,csloAddTime,csloEditor,csloError,csloStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csloId,csloHost,csloPackNo,csloMember,csloPackProfile,csloPackImage,details,fees,csloCount,csloLeaseTerm,csloDeposit,csloPayCycle,csloNeedPay,csloRealPay,csloPactTime,csloStartTime,csloFinishTime,csloUpdateTime,csloAddTime,csloSerial,csloRemark,csloEditor,csloError,csloStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csloId,csloHost,csloPackNo,csloMember,csloPayCycle,csloPactTime,csloStartTime,csloFinishTime,csloError,csloStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csloId,csloHost,csloPackNo,csloMember,csloCount,csloLeaseTerm,csloPayCycle,csloPactTime,csloStartTime,csloFinishTime,csloError,csloStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>长租订单管理</title>
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
	<span class="caption">${empty title?"长租订单管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsLongOrder" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="longorder.query.jsp"%>

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
			<s:if test="#request.CTRL.canExp[0]==true">
			<a class="item" href="javascript:void(0);" onclick="HandleMenu('下单');">
				<img align="absmiddle" src="${basePath}admin//images/icons/200882564216312778012.png"/>下单
			</a>
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
			<dl ref="com.ccclubs.model.CsLongOrder" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.CsLongOrder" class="batch dropdowan">
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
		<table id="longorder_table" ref="CsLongOrder" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csloId || #request.defines['csloId']!=null">
			 <td <s:if test="#request.defines['csloId']>0">colspan="${defines["csloId"]}" ${all?"width":"iwidth"}="${defines["csloId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="csloId" flagKey  title="订单编号">
			 	<a class="${desc=="cslo_id" ? "desc" : ""}${asc=="cslo_id" ? "asc" : ""}" href="?${desc=="cslo_id" ? "asc=cslo_id" : ""}${(asc=="cslo_id" || desc!="cslo_id" )? "desc=cslo_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单编号
			 	</a>
			 	${lz:set("checkeds[]","csloId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csloHost || #request.defines['csloHost']!=null">
			 <td <s:if test="#request.defines['csloHost']>0">colspan="${defines["csloHost"]}" ${all?"width":"iwidth"}="${defines["csloHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="csloHost"   title="城市">
			 	<a class="${desc=="cslo_host" ? "desc" : ""}${asc=="cslo_host" ? "asc" : ""}" href="?${desc=="cslo_host" ? "asc=cslo_host" : ""}${(asc=="cslo_host" || desc!="cslo_host" )? "desc=cslo_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","csloHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csloPackNo || #request.defines['csloPackNo']!=null">
			 <td <s:if test="#request.defines['csloPackNo']>0">colspan="${defines["csloPackNo"]}" ${all?"width":"iwidth"}="${defines["csloPackNo"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="3" ref="csloPackNo"   title="合同号">
			 	<a class="${desc=="cslo_pack_no" ? "desc" : ""}${asc=="cslo_pack_no" ? "asc" : ""}" href="?${desc=="cslo_pack_no" ? "asc=cslo_pack_no" : ""}${(asc=="cslo_pack_no" || desc!="cslo_pack_no" )? "desc=cslo_pack_no" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	合同号
			 	</a>
			 	${lz:set("checkeds[]","csloPackNo")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csloMember || #request.defines['csloMember']!=null">
			 <td <s:if test="#request.defines['csloMember']>0">colspan="${defines["csloMember"]}" ${all?"width":"iwidth"}="${defines["csloMember"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="csloMember"   title="会员帐号">
			 	<a class="${desc=="cslo_member" ? "desc" : ""}${asc=="cslo_member" ? "asc" : ""}" href="?${desc=="cslo_member" ? "asc=cslo_member" : ""}${(asc=="cslo_member" || desc!="cslo_member" )? "desc=cslo_member" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	会员帐号
			 	</a>
			 	${lz:set("checkeds[]","csloMember")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csloPackProfile || #request.defines['csloPackProfile']!=null">
			 <td <s:if test="#request.defines['csloPackProfile']>0">colspan="${defines["csloPackProfile"]}" ${all?"width":"iwidth"}="${defines["csloPackProfile"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="5" ref="csloPackProfile"   title="合同备注">
			 	<a class="${desc=="cslo_pack_profile" ? "desc" : ""}${asc=="cslo_pack_profile" ? "asc" : ""}" href="?${desc=="cslo_pack_profile" ? "asc=cslo_pack_profile" : ""}${(asc=="cslo_pack_profile" || desc!="cslo_pack_profile" )? "desc=cslo_pack_profile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	合同备注
			 	</a>
			 	${lz:set("checkeds[]","csloPackProfile")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csloPackImage || #request.defines['csloPackImage']!=null">
			 <td <s:if test="#request.defines['csloPackImage']>0">colspan="${defines["csloPackImage"]}" ${all?"width":"iwidth"}="${defines["csloPackImage"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="6" ref="csloPackImage"   title="合同图片">
			 	<a class="${desc=="cslo_pack_image" ? "desc" : ""}${asc=="cslo_pack_image" ? "asc" : ""}" href="?${desc=="cslo_pack_image" ? "asc=cslo_pack_image" : ""}${(asc=="cslo_pack_image" || desc!="cslo_pack_image" )? "desc=cslo_pack_image" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	合同图片
			 	</a>
			 	${lz:set("checkeds[]","csloPackImage")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.details || #request.defines['details']!=null">
			 <td <s:if test="#request.defines['details']>0">colspan="${defines["details"]}" ${all?"width":"iwidth"}="${defines["details"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="7" ref="details"   title="车辆明细">
			 	<a class="${desc=="details" ? "desc" : ""}${asc=="details" ? "asc" : ""}" href="?${desc=="details" ? "asc=details" : ""}${(asc=="details" || desc!="details" )? "desc=details" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车辆明细
			 	</a>
			 	${lz:set("checkeds[]","details")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.fees || #request.defines['fees']!=null">
			 <td <s:if test="#request.defines['fees']>0">colspan="${defines["fees"]}" ${all?"width":"iwidth"}="${defines["fees"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="fees"   title="缴费明细">
			 	<a class="${desc=="fees" ? "desc" : ""}${asc=="fees" ? "asc" : ""}" href="?${desc=="fees" ? "asc=fees" : ""}${(asc=="fees" || desc!="fees" )? "desc=fees" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	缴费明细
			 	</a>
			 	${lz:set("checkeds[]","fees")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csloCount || #request.defines['csloCount']!=null">
			 <td <s:if test="#request.defines['csloCount']>0">colspan="${defines["csloCount"]}" ${all?"width":"iwidth"}="${defines["csloCount"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="9" ref="csloCount"   title="车辆数">
			 	<a class="${desc=="cslo_count" ? "desc" : ""}${asc=="cslo_count" ? "asc" : ""}" href="?${desc=="cslo_count" ? "asc=cslo_count" : ""}${(asc=="cslo_count" || desc!="cslo_count" )? "desc=cslo_count" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车辆数
			 	</a>
			 	${lz:set("checkeds[]","csloCount")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csloLeaseTerm || #request.defines['csloLeaseTerm']!=null">
			 <td <s:if test="#request.defines['csloLeaseTerm']>0">colspan="${defines["csloLeaseTerm"]}" ${all?"width":"iwidth"}="${defines["csloLeaseTerm"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="10" ref="csloLeaseTerm"   title="订单租期(个月)">
			 	<a class="${desc=="cslo_lease_term" ? "desc" : ""}${asc=="cslo_lease_term" ? "asc" : ""}" href="?${desc=="cslo_lease_term" ? "asc=cslo_lease_term" : ""}${(asc=="cslo_lease_term" || desc!="cslo_lease_term" )? "desc=cslo_lease_term" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单租期(个月)
			 	</a>
			 	${lz:set("checkeds[]","csloLeaseTerm")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csloDeposit || #request.defines['csloDeposit']!=null">
			 <td <s:if test="#request.defines['csloDeposit']>0">colspan="${defines["csloDeposit"]}" ${all?"width":"iwidth"}="${defines["csloDeposit"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="11" ref="csloDeposit"   title="押金合计">
			 	<a class="${desc=="cslo_deposit" ? "desc" : ""}${asc=="cslo_deposit" ? "asc" : ""}" href="?${desc=="cslo_deposit" ? "asc=cslo_deposit" : ""}${(asc=="cslo_deposit" || desc!="cslo_deposit" )? "desc=cslo_deposit" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	押金合计
			 	</a>
			 	${lz:set("checkeds[]","csloDeposit")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csloPayCycle || #request.defines['csloPayCycle']!=null">
			 <td <s:if test="#request.defines['csloPayCycle']>0">colspan="${defines["csloPayCycle"]}" ${all?"width":"iwidth"}="${defines["csloPayCycle"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="12" ref="csloPayCycle"   title="付款周期">
			 	<a class="${desc=="cslo_pay_cycle" ? "desc" : ""}${asc=="cslo_pay_cycle" ? "asc" : ""}" href="?${desc=="cslo_pay_cycle" ? "asc=cslo_pay_cycle" : ""}${(asc=="cslo_pay_cycle" || desc!="cslo_pay_cycle" )? "desc=cslo_pay_cycle" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	付款周期
			 	</a>
			 	${lz:set("checkeds[]","csloPayCycle")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csloNeedPay || #request.defines['csloNeedPay']!=null">
			 <td <s:if test="#request.defines['csloNeedPay']>0">colspan="${defines["csloNeedPay"]}" ${all?"width":"iwidth"}="${defines["csloNeedPay"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="13" ref="csloNeedPay"   title="应付金额">
			 	<a class="${desc=="cslo_need_pay" ? "desc" : ""}${asc=="cslo_need_pay" ? "asc" : ""}" href="?${desc=="cslo_need_pay" ? "asc=cslo_need_pay" : ""}${(asc=="cslo_need_pay" || desc!="cslo_need_pay" )? "desc=cslo_need_pay" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	应付金额
			 	</a>
			 	${lz:set("checkeds[]","csloNeedPay")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csloRealPay || #request.defines['csloRealPay']!=null">
			 <td <s:if test="#request.defines['csloRealPay']>0">colspan="${defines["csloRealPay"]}" ${all?"width":"iwidth"}="${defines["csloRealPay"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="14" ref="csloRealPay"   title="实付金额">
			 	<a class="${desc=="cslo_real_pay" ? "desc" : ""}${asc=="cslo_real_pay" ? "asc" : ""}" href="?${desc=="cslo_real_pay" ? "asc=cslo_real_pay" : ""}${(asc=="cslo_real_pay" || desc!="cslo_real_pay" )? "desc=cslo_real_pay" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	实付金额
			 	</a>
			 	${lz:set("checkeds[]","csloRealPay")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csloPactTime || #request.defines['csloPactTime']!=null">
			 <td <s:if test="#request.defines['csloPactTime']>0">colspan="${defines["csloPactTime"]}" ${all?"width":"iwidth"}="${defines["csloPactTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="15" ref="csloPactTime"   title="合同签订时间">
			 	<a class="${desc=="cslo_pact_time" ? "desc" : ""}${asc=="cslo_pact_time" ? "asc" : ""}" href="?${desc=="cslo_pact_time" ? "asc=cslo_pact_time" : ""}${(asc=="cslo_pact_time" || desc!="cslo_pact_time" )? "desc=cslo_pact_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	合同签订时间
			 	</a>
			 	${lz:set("checkeds[]","csloPactTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csloStartTime || #request.defines['csloStartTime']!=null">
			 <td <s:if test="#request.defines['csloStartTime']>0">colspan="${defines["csloStartTime"]}" ${all?"width":"iwidth"}="${defines["csloStartTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="16" ref="csloStartTime"   title="合同开始时间">
			 	<a class="${desc=="cslo_start_time" ? "desc" : ""}${asc=="cslo_start_time" ? "asc" : ""}" href="?${desc=="cslo_start_time" ? "asc=cslo_start_time" : ""}${(asc=="cslo_start_time" || desc!="cslo_start_time" )? "desc=cslo_start_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	合同开始时间
			 	</a>
			 	${lz:set("checkeds[]","csloStartTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csloFinishTime || #request.defines['csloFinishTime']!=null">
			 <td <s:if test="#request.defines['csloFinishTime']>0">colspan="${defines["csloFinishTime"]}" ${all?"width":"iwidth"}="${defines["csloFinishTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="17" ref="csloFinishTime"   title="合同结束时间">
			 	<a class="${desc=="cslo_finish_time" ? "desc" : ""}${asc=="cslo_finish_time" ? "asc" : ""}" href="?${desc=="cslo_finish_time" ? "asc=cslo_finish_time" : ""}${(asc=="cslo_finish_time" || desc!="cslo_finish_time" )? "desc=cslo_finish_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	合同结束时间
			 	</a>
			 	${lz:set("checkeds[]","csloFinishTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csloUpdateTime || #request.defines['csloUpdateTime']!=null">
			 <td <s:if test="#request.defines['csloUpdateTime']>0">colspan="${defines["csloUpdateTime"]}" ${all?"width":"iwidth"}="${defines["csloUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="18" ref="csloUpdateTime"   title="修改时间">
			 	<a class="${desc=="cslo_update_time" ? "desc" : ""}${asc=="cslo_update_time" ? "asc" : ""}" href="?${desc=="cslo_update_time" ? "asc=cslo_update_time" : ""}${(asc=="cslo_update_time" || desc!="cslo_update_time" )? "desc=cslo_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 	${lz:set("checkeds[]","csloUpdateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csloAddTime || #request.defines['csloAddTime']!=null">
			 <td <s:if test="#request.defines['csloAddTime']>0">colspan="${defines["csloAddTime"]}" ${all?"width":"iwidth"}="${defines["csloAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="19" ref="csloAddTime"   title="添加时间">
			 	<a class="${desc=="cslo_add_time" ? "desc" : ""}${asc=="cslo_add_time" ? "asc" : ""}" href="?${desc=="cslo_add_time" ? "asc=cslo_add_time" : ""}${(asc=="cslo_add_time" || desc!="cslo_add_time" )? "desc=cslo_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","csloAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csloSerial || #request.defines['csloSerial']!=null">
			 <td <s:if test="#request.defines['csloSerial']>0">colspan="${defines["csloSerial"]}" ${all?"width":"iwidth"}="${defines["csloSerial"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="20" ref="csloSerial"   title="序列号">
			 	<a class="${desc=="cslo_serial" ? "desc" : ""}${asc=="cslo_serial" ? "asc" : ""}" href="?${desc=="cslo_serial" ? "asc=cslo_serial" : ""}${(asc=="cslo_serial" || desc!="cslo_serial" )? "desc=cslo_serial" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	序列号
			 	</a>
			 	${lz:set("checkeds[]","csloSerial")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csloRemark || #request.defines['csloRemark']!=null">
			 <td <s:if test="#request.defines['csloRemark']>0">colspan="${defines["csloRemark"]}" ${all?"width":"iwidth"}="${defines["csloRemark"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="21" ref="csloRemark"   title="订单备注">
			 	<a class="${desc=="cslo_remark" ? "desc" : ""}${asc=="cslo_remark" ? "asc" : ""}" href="?${desc=="cslo_remark" ? "asc=cslo_remark" : ""}${(asc=="cslo_remark" || desc!="cslo_remark" )? "desc=cslo_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单备注
			 	</a>
			 	${lz:set("checkeds[]","csloRemark")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csloEditor || #request.defines['csloEditor']!=null">
			 <td <s:if test="#request.defines['csloEditor']>0">colspan="${defines["csloEditor"]}" ${all?"width":"iwidth"}="${defines["csloEditor"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="22" ref="csloEditor"   title="操作人">
			 	<a class="${desc=="cslo_editor" ? "desc" : ""}${asc=="cslo_editor" ? "asc" : ""}" href="?${desc=="cslo_editor" ? "asc=cslo_editor" : ""}${(asc=="cslo_editor" || desc!="cslo_editor" )? "desc=cslo_editor" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	操作人
			 	</a>
			 	${lz:set("checkeds[]","csloEditor")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csloError || #request.defines['csloError']!=null">
			 <td <s:if test="#request.defines['csloError']>0">colspan="${defines["csloError"]}" ${all?"width":"iwidth"}="${defines["csloError"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="23" ref="csloError"   title="报警信息">
			 	<a class="${desc=="cslo_error" ? "desc" : ""}${asc=="cslo_error" ? "asc" : ""}" href="?${desc=="cslo_error" ? "asc=cslo_error" : ""}${(asc=="cslo_error" || desc!="cslo_error" )? "desc=cslo_error" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	报警信息
			 	</a>
			 	${lz:set("checkeds[]","csloError")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csloStatus || #request.defines['csloStatus']!=null">
			 <td <s:if test="#request.defines['csloStatus']>0">colspan="${defines["csloStatus"]}" ${all?"width":"iwidth"}="${defines["csloStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="24" ref="csloStatus"   title="状态">
			 	<a class="${desc=="cslo_status" ? "desc" : ""}${asc=="cslo_status" ? "asc" : ""}" href="?${desc=="cslo_status" ? "asc=cslo_status" : ""}${(asc=="cslo_status" || desc!="cslo_status" )? "desc=cslo_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","csloStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="25" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="26" ref="operation">
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
		  <tr id="${item.csloId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csloId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csloId || #request.defines['csloId']!=null">
				<s:if test="#request.defines['csloId']>0">
					${lz:set("注释","****csloId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csloId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsloId",lz:indexOf(fieldName,"csloId")>-1)}
				  		<s:if test="#request.atCsloId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csloId字段的字串格式化输出****")}
					<td ref="csloId" class="td  node">
						 
						 	${lz:or(item$csloId[i.count-1],lz:left(item.csloId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csloHost || #request.defines['csloHost']!=null">
				<s:if test="#request.defines['csloHost']>0">
					${lz:set("注释","****csloHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csloHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsloHost",lz:indexOf(fieldName,"csloHost")>-1)}
				  		<s:if test="#request.atCsloHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csloHost字段的字串格式化输出****")}
					<td ref="csloHost" class="td " relate="${item.csloHost}">
						 
						 	<a <s:if test="#item.csloHost!=null && #item.csloHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.csloHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csloHost[i.count-1],lz:left(item.csloHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csloPackNo || #request.defines['csloPackNo']!=null">
				<s:if test="#request.defines['csloPackNo']>0">
					${lz:set("注释","****csloPackNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csloPackNo))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsloPackNo",lz:indexOf(fieldName,"csloPackNo")>-1)}
				  		<s:if test="#request.atCsloPackNo==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csloPackNo字段的字串格式化输出****")}
					<td ref="csloPackNo" class="td ">
						 
						 	${lz:or(item$csloPackNo[i.count-1],lz:left(item.csloPackNo$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csloMember || #request.defines['csloMember']!=null">
				<s:if test="#request.defines['csloMember']>0">
					${lz:set("注释","****csloMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csloMember))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsloMember",lz:indexOf(fieldName,"csloMember")>-1)}
				  		<s:if test="#request.atCsloMember==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csloMember字段的字串格式化输出****")}
					<td ref="csloMember" class="td " relate="${item.csloMember}">
						 
						 	<a <s:if test="#item.csloMember!=null && #item.csloMember!=''"> onclick="window.href('${basePath}${proname}/user/member_details.do?key=${item.csloMember}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csloMember[i.count-1],lz:left(item.csloMember$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csloPackProfile || #request.defines['csloPackProfile']!=null">
				<s:if test="#request.defines['csloPackProfile']>0">
					${lz:set("注释","****csloPackProfile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csloPackProfile))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsloPackProfile",lz:indexOf(fieldName,"csloPackProfile")>-1)}
				  		<s:if test="#request.atCsloPackProfile==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csloPackProfile字段的字串格式化输出****")}
					<td ref="csloPackProfile" class="td ">
						 
						 	${lz:or(item$csloPackProfile[i.count-1],lz:left(item.csloPackProfile$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csloPackImage || #request.defines['csloPackImage']!=null">
				<s:if test="#request.defines['csloPackImage']>0">
					${lz:set("注释","****csloPackImage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csloPackImage))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsloPackImage",lz:indexOf(fieldName,"csloPackImage")>-1)}
				  		<s:if test="#request.atCsloPackImage==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csloPackImage字段的字串格式化输出****")}
					<td ref="csloPackImage" class="td ">
						 
						 	${lz:or(item$csloPackImage[i.count-1],lz:left(item.csloPackImage$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.details || #request.defines['details']!=null">
				<s:if test="#request.defines['details']>0">
					${lz:set("注释","****details关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.details))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atDetails",lz:indexOf(fieldName,"details")>-1)}
				  		<s:if test="#request.atDetails==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****details字段的字串格式化输出****")}
					<td ref="details" class="td " relate="${item.details}">
						 
						 	<a <s:if test="#item.details!=null && #item.details!=''"> onclick="window.href('${basePath}${proname}/service/longorderdetail_details.do?key=${item.details}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$details[i.count-1],lz:left(item.details$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.fees || #request.defines['fees']!=null">
				<s:if test="#request.defines['fees']>0">
					${lz:set("注释","****fees关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.fees))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atFees",lz:indexOf(fieldName,"fees")>-1)}
				  		<s:if test="#request.atFees==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****fees字段的字串格式化输出****")}
					<td ref="fees" class="td " relate="${item.fees}">
						 
						 	<a <s:if test="#item.fees!=null && #item.fees!=''"> onclick="window.href('${basePath}${proname}/service/longorderfee_details.do?key=${item.fees}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$fees[i.count-1],lz:left(item.fees$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csloCount || #request.defines['csloCount']!=null">
				<s:if test="#request.defines['csloCount']>0">
					${lz:set("注释","****csloCount关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csloCount))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsloCount",lz:indexOf(fieldName,"csloCount")>-1)}
				  		<s:if test="#request.atCsloCount==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csloCount字段的字串格式化输出****")}
					<td ref="csloCount" class="td ">
						 
						 	${lz:or(item$csloCount[i.count-1],lz:left(item.csloCount$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csloLeaseTerm || #request.defines['csloLeaseTerm']!=null">
				<s:if test="#request.defines['csloLeaseTerm']>0">
					${lz:set("注释","****csloLeaseTerm关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csloLeaseTerm))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsloLeaseTerm",lz:indexOf(fieldName,"csloLeaseTerm")>-1)}
				  		<s:if test="#request.atCsloLeaseTerm==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csloLeaseTerm字段的字串格式化输出****")}
					<td ref="csloLeaseTerm" class="td ">
						 
						 	${lz:or(item$csloLeaseTerm[i.count-1],lz:left(item.csloLeaseTerm$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csloDeposit || #request.defines['csloDeposit']!=null">
				<s:if test="#request.defines['csloDeposit']>0">
					${lz:set("注释","****csloDeposit关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csloDeposit))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsloDeposit",lz:indexOf(fieldName,"csloDeposit")>-1)}
				  		<s:if test="#request.atCsloDeposit==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csloDeposit字段的字串格式化输出****")}
					<td ref="csloDeposit" class="td ">
						 
						 	${lz:or(item$csloDeposit[i.count-1],lz:left(item.csloDeposit$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csloPayCycle || #request.defines['csloPayCycle']!=null">
				<s:if test="#request.defines['csloPayCycle']>0">
					${lz:set("注释","****csloPayCycle关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csloPayCycle))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsloPayCycle",lz:indexOf(fieldName,"csloPayCycle")>-1)}
				  		<s:if test="#request.atCsloPayCycle==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csloPayCycle字段的字串格式化输出****")}
					<td ref="csloPayCycle" class="td ">
						 
						 	${lz:or(item$csloPayCycle[i.count-1],lz:left(item.csloPayCycle$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csloNeedPay || #request.defines['csloNeedPay']!=null">
				<s:if test="#request.defines['csloNeedPay']>0">
					${lz:set("注释","****csloNeedPay关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csloNeedPay))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsloNeedPay",lz:indexOf(fieldName,"csloNeedPay")>-1)}
				  		<s:if test="#request.atCsloNeedPay==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csloNeedPay字段的字串格式化输出****")}
					<td ref="csloNeedPay" class="td ">
						 
						 	${lz:or(item$csloNeedPay[i.count-1],lz:left(item.csloNeedPay$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csloRealPay || #request.defines['csloRealPay']!=null">
				<s:if test="#request.defines['csloRealPay']>0">
					${lz:set("注释","****csloRealPay关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csloRealPay))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsloRealPay",lz:indexOf(fieldName,"csloRealPay")>-1)}
				  		<s:if test="#request.atCsloRealPay==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csloRealPay字段的字串格式化输出****")}
					<td ref="csloRealPay" class="td ">
						 
						 	${lz:or(item$csloRealPay[i.count-1],lz:left(item.csloRealPay$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csloPactTime || #request.defines['csloPactTime']!=null">
				<s:if test="#request.defines['csloPactTime']>0">
					${lz:set("注释","****csloPactTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csloPactTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsloPactTime",lz:indexOf(fieldName,"csloPactTime")>-1)}
				  		<s:if test="#request.atCsloPactTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csloPactTime字段的字串格式化输出****")}
					<td ref="csloPactTime" class="td ">
						 
						 	${lz:or(item$csloPactTime[i.count-1],lz:left(item.csloPactTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csloStartTime || #request.defines['csloStartTime']!=null">
				<s:if test="#request.defines['csloStartTime']>0">
					${lz:set("注释","****csloStartTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csloStartTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsloStartTime",lz:indexOf(fieldName,"csloStartTime")>-1)}
				  		<s:if test="#request.atCsloStartTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csloStartTime字段的字串格式化输出****")}
					<td ref="csloStartTime" class="td ">
						 
						 	${lz:or(item$csloStartTime[i.count-1],lz:left(item.csloStartTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csloFinishTime || #request.defines['csloFinishTime']!=null">
				<s:if test="#request.defines['csloFinishTime']>0">
					${lz:set("注释","****csloFinishTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csloFinishTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsloFinishTime",lz:indexOf(fieldName,"csloFinishTime")>-1)}
				  		<s:if test="#request.atCsloFinishTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csloFinishTime字段的字串格式化输出****")}
					<td ref="csloFinishTime" class="td ">
						 
						 	${lz:or(item$csloFinishTime[i.count-1],lz:left(item.csloFinishTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csloUpdateTime || #request.defines['csloUpdateTime']!=null">
				<s:if test="#request.defines['csloUpdateTime']>0">
					${lz:set("注释","****csloUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csloUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsloUpdateTime",lz:indexOf(fieldName,"csloUpdateTime")>-1)}
				  		<s:if test="#request.atCsloUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csloUpdateTime字段的字串格式化输出****")}
					<td ref="csloUpdateTime" class="td ">
						 
						 	${lz:or(item$csloUpdateTime[i.count-1],lz:left(item.csloUpdateTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csloAddTime || #request.defines['csloAddTime']!=null">
				<s:if test="#request.defines['csloAddTime']>0">
					${lz:set("注释","****csloAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csloAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsloAddTime",lz:indexOf(fieldName,"csloAddTime")>-1)}
				  		<s:if test="#request.atCsloAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csloAddTime字段的字串格式化输出****")}
					<td ref="csloAddTime" class="td ">
						 
						 	${lz:or(item$csloAddTime[i.count-1],lz:left(item.csloAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csloSerial || #request.defines['csloSerial']!=null">
				<s:if test="#request.defines['csloSerial']>0">
					${lz:set("注释","****csloSerial关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csloSerial))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsloSerial",lz:indexOf(fieldName,"csloSerial")>-1)}
				  		<s:if test="#request.atCsloSerial==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csloSerial字段的字串格式化输出****")}
					<td ref="csloSerial" class="td ">
						 
						 	${lz:or(item$csloSerial[i.count-1],lz:left(item.csloSerial$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csloRemark || #request.defines['csloRemark']!=null">
				<s:if test="#request.defines['csloRemark']>0">
					${lz:set("注释","****csloRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csloRemark))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsloRemark",lz:indexOf(fieldName,"csloRemark")>-1)}
				  		<s:if test="#request.atCsloRemark==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csloRemark字段的字串格式化输出****")}
					<td ref="csloRemark" class="td ">
						 
						 	${lz:or(item$csloRemark[i.count-1],lz:left(item.csloRemark$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csloEditor || #request.defines['csloEditor']!=null">
				<s:if test="#request.defines['csloEditor']>0">
					${lz:set("注释","****csloEditor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csloEditor))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsloEditor",lz:indexOf(fieldName,"csloEditor")>-1)}
				  		<s:if test="#request.atCsloEditor==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csloEditor字段的字串格式化输出****")}
					<td ref="csloEditor" class="td " relate="${item.csloEditor}">
						 
						 	<a <s:if test="#item.csloEditor!=null && #item.csloEditor!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.csloEditor}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csloEditor[i.count-1],lz:left(item.csloEditor$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csloError || #request.defines['csloError']!=null">
				<s:if test="#request.defines['csloError']>0">
					${lz:set("注释","****csloError关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csloError))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsloError",lz:indexOf(fieldName,"csloError")>-1)}
				  		<s:if test="#request.atCsloError==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csloError字段的字串格式化输出****")}
					<td ref="csloError" class="td ">
						 
						 	${lz:or(item$csloError[i.count-1],lz:left(item.csloError$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csloStatus || #request.defines['csloStatus']!=null">
				<s:if test="#request.defines['csloStatus']>0">
					${lz:set("注释","****csloStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csloStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsloStatus",lz:indexOf(fieldName,"csloStatus")>-1)}
				  		<s:if test="#request.atCsloStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csloStatus字段的字串格式化输出****")}
					<td ref="csloStatus" class="td ">
						 
						 	${lz:or(item$csloStatus[i.count-1],lz:left(item.csloStatus$,100))}
					</td>
				</s:else>
			</s:if>
		   ${lz:set("注释","*****************数据列表数据部分结束*****************")}		  
		  
		   
		    ${lz:set("注释","****自定义的单行记录操作句柄[request中的handles(String[])变量]****")}
			<td class="options">
				  <s:if test="#request.alias==null">	
					<s:if test="#request.CTRL.canExp[1]==true">
					<a href="javascript:HandleSel('交付','${item.csloId}')">交付</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[2]==true">
					<a href="javascript:HandleSel('续租','${item.csloId}')">续租</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[3]==true">
					<a href="javascript:HandleSel('缴费','${item.csloId}')">缴费</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[4]==true">
					<a href="javascript:HandleSel('作废','${item.csloId}')">作废</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[5]==true">
					<a href="javascript:HandleSel('完成','${item.csloId}')">完成</a>
					</s:if>
				</s:if>
				${lz:set("items",lz:split(handles,","))}
				<s:iterator value="#request.items" id="handle" status="i">
					${lz:set("isMenu",(lz:indexOf(handle,"@")==0))}
					<s:if test="#request.isMenu!=true">
					<a href="javascript:HandleSel('${handle}','${item.csloId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectLongorder('${item.csloId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditLongorder('${item.csloId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelLongorder('${item.csloId}','${item.csloId}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsLongorder('${item.csloId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csloId || #request.defines['csloId']!=null">
				<s:if test="#request.defines['csloId']>0">
					${lz:set("注释","****csloId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsloId",lz:indexOf(fieldName,"csloId")>-1)}				  		
				  		<s:if test="#request.atCsloId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csloId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csloHost || #request.defines['csloHost']!=null">
				<s:if test="#request.defines['csloHost']>0">
					${lz:set("注释","****csloHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsloHost",lz:indexOf(fieldName,"csloHost")>-1)}				  		
				  		<s:if test="#request.atCsloHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csloHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csloPackNo || #request.defines['csloPackNo']!=null">
				<s:if test="#request.defines['csloPackNo']>0">
					${lz:set("注释","****csloPackNo关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsloPackNo",lz:indexOf(fieldName,"csloPackNo")>-1)}				  		
				  		<s:if test="#request.atCsloPackNo==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csloPackNo[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csloMember || #request.defines['csloMember']!=null">
				<s:if test="#request.defines['csloMember']>0">
					${lz:set("注释","****csloMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsloMember",lz:indexOf(fieldName,"csloMember")>-1)}				  		
				  		<s:if test="#request.atCsloMember==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csloMember[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csloPackProfile || #request.defines['csloPackProfile']!=null">
				<s:if test="#request.defines['csloPackProfile']>0">
					${lz:set("注释","****csloPackProfile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsloPackProfile",lz:indexOf(fieldName,"csloPackProfile")>-1)}				  		
				  		<s:if test="#request.atCsloPackProfile==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csloPackProfile[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csloPackImage || #request.defines['csloPackImage']!=null">
				<s:if test="#request.defines['csloPackImage']>0">
					${lz:set("注释","****csloPackImage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsloPackImage",lz:indexOf(fieldName,"csloPackImage")>-1)}				  		
				  		<s:if test="#request.atCsloPackImage==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csloPackImage[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.details || #request.defines['details']!=null">
				<s:if test="#request.defines['details']>0">
					${lz:set("注释","****details关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atDetails",lz:indexOf(fieldName,"details")>-1)}				  		
				  		<s:if test="#request.atDetails==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">details[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.fees || #request.defines['fees']!=null">
				<s:if test="#request.defines['fees']>0">
					${lz:set("注释","****fees关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atFees",lz:indexOf(fieldName,"fees")>-1)}				  		
				  		<s:if test="#request.atFees==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">fees[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csloCount || #request.defines['csloCount']!=null">
				<s:if test="#request.defines['csloCount']>0">
					${lz:set("注释","****csloCount关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsloCount",lz:indexOf(fieldName,"csloCount")>-1)}				  		
				  		<s:if test="#request.atCsloCount==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csloCount[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csloLeaseTerm || #request.defines['csloLeaseTerm']!=null">
				<s:if test="#request.defines['csloLeaseTerm']>0">
					${lz:set("注释","****csloLeaseTerm关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsloLeaseTerm",lz:indexOf(fieldName,"csloLeaseTerm")>-1)}				  		
				  		<s:if test="#request.atCsloLeaseTerm==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csloLeaseTerm[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csloDeposit || #request.defines['csloDeposit']!=null">
				<s:if test="#request.defines['csloDeposit']>0">
					${lz:set("注释","****csloDeposit关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsloDeposit",lz:indexOf(fieldName,"csloDeposit")>-1)}				  		
				  		<s:if test="#request.atCsloDeposit==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csloDeposit[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csloPayCycle || #request.defines['csloPayCycle']!=null">
				<s:if test="#request.defines['csloPayCycle']>0">
					${lz:set("注释","****csloPayCycle关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsloPayCycle",lz:indexOf(fieldName,"csloPayCycle")>-1)}				  		
				  		<s:if test="#request.atCsloPayCycle==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csloPayCycle[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csloNeedPay || #request.defines['csloNeedPay']!=null">
				<s:if test="#request.defines['csloNeedPay']>0">
					${lz:set("注释","****csloNeedPay关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsloNeedPay",lz:indexOf(fieldName,"csloNeedPay")>-1)}				  		
				  		<s:if test="#request.atCsloNeedPay==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csloNeedPay[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csloRealPay || #request.defines['csloRealPay']!=null">
				<s:if test="#request.defines['csloRealPay']>0">
					${lz:set("注释","****csloRealPay关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsloRealPay",lz:indexOf(fieldName,"csloRealPay")>-1)}				  		
				  		<s:if test="#request.atCsloRealPay==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csloRealPay[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csloPactTime || #request.defines['csloPactTime']!=null">
				<s:if test="#request.defines['csloPactTime']>0">
					${lz:set("注释","****csloPactTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsloPactTime",lz:indexOf(fieldName,"csloPactTime")>-1)}				  		
				  		<s:if test="#request.atCsloPactTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csloPactTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csloStartTime || #request.defines['csloStartTime']!=null">
				<s:if test="#request.defines['csloStartTime']>0">
					${lz:set("注释","****csloStartTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsloStartTime",lz:indexOf(fieldName,"csloStartTime")>-1)}				  		
				  		<s:if test="#request.atCsloStartTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csloStartTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csloFinishTime || #request.defines['csloFinishTime']!=null">
				<s:if test="#request.defines['csloFinishTime']>0">
					${lz:set("注释","****csloFinishTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsloFinishTime",lz:indexOf(fieldName,"csloFinishTime")>-1)}				  		
				  		<s:if test="#request.atCsloFinishTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csloFinishTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csloUpdateTime || #request.defines['csloUpdateTime']!=null">
				<s:if test="#request.defines['csloUpdateTime']>0">
					${lz:set("注释","****csloUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsloUpdateTime",lz:indexOf(fieldName,"csloUpdateTime")>-1)}				  		
				  		<s:if test="#request.atCsloUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csloUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csloAddTime || #request.defines['csloAddTime']!=null">
				<s:if test="#request.defines['csloAddTime']>0">
					${lz:set("注释","****csloAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsloAddTime",lz:indexOf(fieldName,"csloAddTime")>-1)}				  		
				  		<s:if test="#request.atCsloAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csloAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csloSerial || #request.defines['csloSerial']!=null">
				<s:if test="#request.defines['csloSerial']>0">
					${lz:set("注释","****csloSerial关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsloSerial",lz:indexOf(fieldName,"csloSerial")>-1)}				  		
				  		<s:if test="#request.atCsloSerial==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csloSerial[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csloRemark || #request.defines['csloRemark']!=null">
				<s:if test="#request.defines['csloRemark']>0">
					${lz:set("注释","****csloRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsloRemark",lz:indexOf(fieldName,"csloRemark")>-1)}				  		
				  		<s:if test="#request.atCsloRemark==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csloRemark[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csloEditor || #request.defines['csloEditor']!=null">
				<s:if test="#request.defines['csloEditor']>0">
					${lz:set("注释","****csloEditor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsloEditor",lz:indexOf(fieldName,"csloEditor")>-1)}				  		
				  		<s:if test="#request.atCsloEditor==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csloEditor[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csloError || #request.defines['csloError']!=null">
				<s:if test="#request.defines['csloError']>0">
					${lz:set("注释","****csloError关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsloError",lz:indexOf(fieldName,"csloError")>-1)}				  		
				  		<s:if test="#request.atCsloError==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csloError[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csloStatus || #request.defines['csloStatus']!=null">
				<s:if test="#request.defines['csloStatus']>0">
					${lz:set("注释","****csloStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsloStatus",lz:indexOf(fieldName,"csloStatus")>-1)}				  		
				  		<s:if test="#request.atCsloStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csloStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 选择长租订单
	**/
	function SelectLongorder(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改长租订单
	**/
	function EditLongorder(id){
		var url = "${basePath}${namespace}longorder_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改长租订单'}":lz:json(ctrl)}};
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
		EditLongorder(checker.val());
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
				var url = "${basePath}${namespace}longorder_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新长租订单",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}longorder_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新长租订单",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改长租订单
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}longorder_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新长租订单",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改长租订单任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}longorder_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新长租订单",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}longorder_edit.do";
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
			var url = "${basePath}${namespace}longorder_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除长租订单
	**/
	function DelLongorder(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除长租订单["+flag+"]吗？</span>",
				title:"删除长租订单",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}longorder_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选长租订单吗？</span>",
				title:"批量删除长租订单",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}longorder_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示长租订单详情
	**/
	function DetailsLongorder(id)
	{
		var url = "${basePath}${namespace}longorder_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"长租订单详情",editable:false,visible:true}};
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
		DetailsLongorder(checker.val());
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