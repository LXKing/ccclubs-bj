<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/service/ordercluster.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csOrderCluster.csocId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csocId,csocHost,csocSubOrders,csocPayMember,csocMobile,csocPrice,csocMarginNeed,csocTotalDuration,csocPayNeed,csocPayReal,csocPayRent,csocPayInsure,csocPayTimeout,csocPayDetails,csocStartTime,csocFinishTime,csocStatus,csocAddTime,csocUpdateTime",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csocId,csocHost,csocSubOrders,csocPayMember,csocMobile,csocPrice,csocMarginNeed,csocTotalDuration,csocPayNeed,csocPayReal,csocPayRent,csocPayInsure,csocPayTimeout,csocPayDetails,csocStartTime,csocFinishTime,csocStatus,csocAddTime,csocUpdateTime",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csocId,csocHost,csocSubOrders,csocPayMember,csocMobile,csocPrice,csocMarginNeed,csocTotalDuration,csocPayNeed,csocPayReal,csocPayRent,csocPayInsure,csocPayTimeout,csocPayDetails,csocStartTime,csocFinishTime,csocStatus,csocAddTime,csocUpdateTime",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csocId,csocHost,csocSubOrders,csocPayMember,csocMobile,csocPrice,csocMarginNeed,csocTotalDuration,csocPayNeed,csocPayReal,csocPayRent,csocPayInsure,csocPayTimeout,csocPayDetails,csocStartTime,csocFinishTime,csocStatus,csocAddTime,csocUpdateTime",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>订单簇管理</title>
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


<!--LAZY3Q_JSP_BODY-->



<!--LAZY3Q_JSP_BODY-->


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
	<span class="caption">${empty title?"订单簇管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsOrderCluster" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="ordercluster.query.jsp"%>

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
			<!-- 
			<button type="button" class="add" onclick="AddOrder();">
				添加
			</button>		 
			-->					
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
			<dl ref="com.ccclubs.model.CsOrderCluster" class="display dropdowan" style="margin-left:30px;">
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
		<table id="ordercluster_table" ref="CsOrderCluster" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csocId || #request.defines['csocId']!=null">
			 <td <s:if test="#request.defines['csocId']>0">colspan="${defines["csocId"]}" ${all?"width":"iwidth"}="${defines["csocId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="csocId" flagKey  title="订单编号">
			 	<a class="${desc=="csoc_id" ? "desc" : ""}${asc=="csoc_id" ? "asc" : ""}" href="?${desc=="csoc_id" ? "asc=csoc_id" : ""}${(asc=="csoc_id" || desc!="csoc_id" )? "desc=csoc_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单编号
			 	</a>
			 	${lz:set("checkeds[]","csocId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csocHost || #request.defines['csocHost']!=null">
			 <td <s:if test="#request.defines['csocHost']>0">colspan="${defines["csocHost"]}" ${all?"width":"iwidth"}="${defines["csocHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="csocHost"   title="城市">
			 	<a class="${desc=="csoc_host" ? "desc" : ""}${asc=="csoc_host" ? "asc" : ""}" href="?${desc=="csoc_host" ? "asc=csoc_host" : ""}${(asc=="csoc_host" || desc!="csoc_host" )? "desc=csoc_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","csocHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csocSubOrders || #request.defines['csocSubOrders']!=null">
			 <td <s:if test="#request.defines['csocSubOrders']>0">colspan="${defines["csocSubOrders"]}" ${all?"width":"iwidth"}="${defines["csocSubOrders"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="3" ref="csocSubOrders"   title="子订单集">
			 	<a class="${desc=="csoc_sub_orders" ? "desc" : ""}${asc=="csoc_sub_orders" ? "asc" : ""}" href="?${desc=="csoc_sub_orders" ? "asc=csoc_sub_orders" : ""}${(asc=="csoc_sub_orders" || desc!="csoc_sub_orders" )? "desc=csoc_sub_orders" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	子订单集
			 	</a>
			 	${lz:set("checkeds[]","csocSubOrders")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csocPayMember || #request.defines['csocPayMember']!=null">
			 <td <s:if test="#request.defines['csocPayMember']>0">colspan="${defines["csocPayMember"]}" ${all?"width":"iwidth"}="${defines["csocPayMember"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="csocPayMember"   title="支付会员">
			 	<a class="${desc=="csoc_pay_member" ? "desc" : ""}${asc=="csoc_pay_member" ? "asc" : ""}" href="?${desc=="csoc_pay_member" ? "asc=csoc_pay_member" : ""}${(asc=="csoc_pay_member" || desc!="csoc_pay_member" )? "desc=csoc_pay_member" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	支付会员
			 	</a>
			 	${lz:set("checkeds[]","csocPayMember")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csocMobile || #request.defines['csocMobile']!=null">
			 <td <s:if test="#request.defines['csocMobile']>0">colspan="${defines["csocMobile"]}" ${all?"width":"iwidth"}="${defines["csocMobile"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="csocMobile"   title="手机号码">
			 	<a class="${desc=="csoc_mobile" ? "desc" : ""}${asc=="csoc_mobile" ? "asc" : ""}" href="?${desc=="csoc_mobile" ? "asc=csoc_mobile" : ""}${(asc=="csoc_mobile" || desc!="csoc_mobile" )? "desc=csoc_mobile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	手机号码
			 	</a>
			 	${lz:set("checkeds[]","csocMobile")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csocPrice || #request.defines['csocPrice']!=null">
			 <td <s:if test="#request.defines['csocPrice']>0">colspan="${defines["csocPrice"]}" ${all?"width":"iwidth"}="${defines["csocPrice"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="csocPrice"   title="订单总价">
			 	<a class="${desc=="csoc_price" ? "desc" : ""}${asc=="csoc_price" ? "asc" : ""}" href="?${desc=="csoc_price" ? "asc=csoc_price" : ""}${(asc=="csoc_price" || desc!="csoc_price" )? "desc=csoc_price" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单总价
			 	</a>
			 	${lz:set("checkeds[]","csocPrice")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csocMarginNeed || #request.defines['csocMarginNeed']!=null">
			 <td <s:if test="#request.defines['csocMarginNeed']>0">colspan="${defines["csocMarginNeed"]}" ${all?"width":"iwidth"}="${defines["csocMarginNeed"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="7" ref="csocMarginNeed"   title="押金/保证金">
			 	<a class="${desc=="csoc_margin_need" ? "desc" : ""}${asc=="csoc_margin_need" ? "asc" : ""}" href="?${desc=="csoc_margin_need" ? "asc=csoc_margin_need" : ""}${(asc=="csoc_margin_need" || desc!="csoc_margin_need" )? "desc=csoc_margin_need" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	押金/保证金
			 	</a>
			 	${lz:set("checkeds[]","csocMarginNeed")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csocTotalDuration || #request.defines['csocTotalDuration']!=null">
			 <td <s:if test="#request.defines['csocTotalDuration']>0">colspan="${defines["csocTotalDuration"]}" ${all?"width":"iwidth"}="${defines["csocTotalDuration"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="csocTotalDuration"   title="订单总时长">
			 	<a class="${desc=="csoc_total_duration" ? "desc" : ""}${asc=="csoc_total_duration" ? "asc" : ""}" href="?${desc=="csoc_total_duration" ? "asc=csoc_total_duration" : ""}${(asc=="csoc_total_duration" || desc!="csoc_total_duration" )? "desc=csoc_total_duration" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单总时长
			 	</a>
			 	${lz:set("checkeds[]","csocTotalDuration")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csocPayNeed || #request.defines['csocPayNeed']!=null">
			 <td <s:if test="#request.defines['csocPayNeed']>0">colspan="${defines["csocPayNeed"]}" ${all?"width":"iwidth"}="${defines["csocPayNeed"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="9" ref="csocPayNeed"   title="应付金额">
			 	<a class="${desc=="csoc_pay_need" ? "desc" : ""}${asc=="csoc_pay_need" ? "asc" : ""}" href="?${desc=="csoc_pay_need" ? "asc=csoc_pay_need" : ""}${(asc=="csoc_pay_need" || desc!="csoc_pay_need" )? "desc=csoc_pay_need" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	应付金额
			 	</a>
			 	${lz:set("checkeds[]","csocPayNeed")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csocPayReal || #request.defines['csocPayReal']!=null">
			 <td <s:if test="#request.defines['csocPayReal']>0">colspan="${defines["csocPayReal"]}" ${all?"width":"iwidth"}="${defines["csocPayReal"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="10" ref="csocPayReal"   title="实付金额">
			 	<a class="${desc=="csoc_pay_real" ? "desc" : ""}${asc=="csoc_pay_real" ? "asc" : ""}" href="?${desc=="csoc_pay_real" ? "asc=csoc_pay_real" : ""}${(asc=="csoc_pay_real" || desc!="csoc_pay_real" )? "desc=csoc_pay_real" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	实付金额
			 	</a>
			 	${lz:set("checkeds[]","csocPayReal")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csocPayRent || #request.defines['csocPayRent']!=null">
			 <td <s:if test="#request.defines['csocPayRent']>0">colspan="${defines["csocPayRent"]}" ${all?"width":"iwidth"}="${defines["csocPayRent"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="11" ref="csocPayRent"   title="租金费用">
			 	<a class="${desc=="csoc_pay_rent" ? "desc" : ""}${asc=="csoc_pay_rent" ? "asc" : ""}" href="?${desc=="csoc_pay_rent" ? "asc=csoc_pay_rent" : ""}${(asc=="csoc_pay_rent" || desc!="csoc_pay_rent" )? "desc=csoc_pay_rent" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	租金费用
			 	</a>
			 	${lz:set("checkeds[]","csocPayRent")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csocPayInsure || #request.defines['csocPayInsure']!=null">
			 <td <s:if test="#request.defines['csocPayInsure']>0">colspan="${defines["csocPayInsure"]}" ${all?"width":"iwidth"}="${defines["csocPayInsure"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="12" ref="csocPayInsure"   title="免责费用">
			 	<a class="${desc=="csoc_pay_insure" ? "desc" : ""}${asc=="csoc_pay_insure" ? "asc" : ""}" href="?${desc=="csoc_pay_insure" ? "asc=csoc_pay_insure" : ""}${(asc=="csoc_pay_insure" || desc!="csoc_pay_insure" )? "desc=csoc_pay_insure" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	免责费用
			 	</a>
			 	${lz:set("checkeds[]","csocPayInsure")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csocPayTimeout || #request.defines['csocPayTimeout']!=null">
			 <td <s:if test="#request.defines['csocPayTimeout']>0">colspan="${defines["csocPayTimeout"]}" ${all?"width":"iwidth"}="${defines["csocPayTimeout"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="13" ref="csocPayTimeout"   title="超时费用">
			 	<a class="${desc=="csoc_pay_timeout" ? "desc" : ""}${asc=="csoc_pay_timeout" ? "asc" : ""}" href="?${desc=="csoc_pay_timeout" ? "asc=csoc_pay_timeout" : ""}${(asc=="csoc_pay_timeout" || desc!="csoc_pay_timeout" )? "desc=csoc_pay_timeout" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	超时费用
			 	</a>
			 	${lz:set("checkeds[]","csocPayTimeout")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csocPayDetails || #request.defines['csocPayDetails']!=null">
			 <td <s:if test="#request.defines['csocPayDetails']>0">colspan="${defines["csocPayDetails"]}" ${all?"width":"iwidth"}="${defines["csocPayDetails"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="14" ref="csocPayDetails"   title="支付明细">
			 	<a class="${desc=="csoc_pay_details" ? "desc" : ""}${asc=="csoc_pay_details" ? "asc" : ""}" href="?${desc=="csoc_pay_details" ? "asc=csoc_pay_details" : ""}${(asc=="csoc_pay_details" || desc!="csoc_pay_details" )? "desc=csoc_pay_details" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	支付明细
			 	</a>
			 	${lz:set("checkeds[]","csocPayDetails")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csocStartTime || #request.defines['csocStartTime']!=null">
			 <td <s:if test="#request.defines['csocStartTime']>0">colspan="${defines["csocStartTime"]}" ${all?"width":"iwidth"}="${defines["csocStartTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="15" ref="csocStartTime"   title="订单开始时间">
			 	<a class="${desc=="csoc_start_time" ? "desc" : ""}${asc=="csoc_start_time" ? "asc" : ""}" href="?${desc=="csoc_start_time" ? "asc=csoc_start_time" : ""}${(asc=="csoc_start_time" || desc!="csoc_start_time" )? "desc=csoc_start_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单开始时间
			 	</a>
			 	${lz:set("checkeds[]","csocStartTime")}
			 </td>
			 </s:if>
			 <s:if test="#request.defines==null && #request.CTRL.l.csocFinishTime || #request.defines['csocFinishTime']!=null">
			 <td <s:if test="#request.defines['csocFinishTime']>0">colspan="${defines["csocFinishTime"]}" ${all?"width":"iwidth"}="${defines["csocFinishTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="17" ref="csocFinishTime"   title="订单结束时间">
			 	<a class="${desc=="csoc_finish_time" ? "desc" : ""}${asc=="csoc_finish_time" ? "asc" : ""}" href="?${desc=="csoc_finish_time" ? "asc=csoc_finish_time" : ""}${(asc=="csoc_finish_time" || desc!="csoc_finish_time" )? "desc=csoc_finish_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单结束时间
			 	</a>
			 	${lz:set("checkeds[]","csocFinishTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csocStatus || #request.defines['csocStatus']!=null">
			 <td <s:if test="#request.defines['csocStatus']>0">colspan="${defines["csocStatus"]}" ${all?"width":"iwidth"}="${defines["csocStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="18" ref="csocStatus"   title="是否长单">
			 	<a class="${desc=="csoc_status" ? "desc" : ""}${asc=="csoc_status" ? "asc" : ""}" href="?${desc=="csoc_status" ? "asc=csoc_status" : ""}${(asc=="csoc_status" || desc!="csoc_status" )? "desc=csoc_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	数据状态
			 	</a>
			 	${lz:set("checkeds[]","csocStatus")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csocAddTime || #request.defines['csocAddTime']!=null">
			 <td <s:if test="#request.defines['csocAddTime']>0">colspan="${defines["csocAddTime"]}" ${all?"width":"iwidth"}="${defines["csocAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="19" ref="csocAddTime"   title="创建时间">
			 	<a class="${desc=="csoc_add_time" ? "desc" : ""}${asc=="csoc_add_time" ? "asc" : ""}" href="?${desc=="csoc_add_time" ? "asc=csoc_add_time" : ""}${(asc=="csoc_add_time" || desc!="csoc_add_time" )? "desc=csoc_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	创建时间
			 	</a>
			 	${lz:set("checkeds[]","csocAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csocUpdateTime || #request.defines['csocUpdateTime']!=null">
			 <td <s:if test="#request.defines['csocUpdateTime']>0">colspan="${defines["csocUpdateTime"]}" ${all?"width":"iwidth"}="${defines["csocUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="20" ref="csocUpdateTime"   title="修改时间">
			 	<a class="${desc=="csoc_update_time" ? "desc" : ""}${asc=="csoc_update_time" ? "asc" : ""}" href="?${desc=="csoc_update_time" ? "asc=csoc_update_time" : ""}${(asc=="csoc_update_time" || desc!="csoc_update_time" )? "desc=csoc_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 	${lz:set("checkeds[]","csocUpdateTime")}
			 </td>
			 </s:if>
				
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			
			<td rowspan="2" width="105" class="operation" tdid="68" ref="operation">
				操作
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
		  <tr id="${item.csocId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csocId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csocId || #request.defines['csocId']!=null">
				<s:if test="#request.defines['csocId']>0">
					${lz:set("注释","****csoId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csocId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoId",lz:indexOf(fieldName,"csocId")>-1)}
				  		<s:if test="#request.atCsoId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoId字段的字串格式化输出****")}
					<td ref="csocId" class="td  node">
						 
						 	${lz:or(item$csocId[i.count-1],lz:left(item.csocId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csocHost || #request.defines['csocHost']!=null">
				<s:if test="#request.defines['csocHost']>0">
					${lz:set("注释","****csoHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csocHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoHost",lz:indexOf(fieldName,"csocHost")>-1)}
				  		<s:if test="#request.atCsoHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoHost字段的字串格式化输出****")}
					<td ref="csocHost" class="td " relate="${item.csocHost}">
						 
						 	<a <s:if test="#item.csocHost!=null && #item.csocHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.csocHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csocHost[i.count-1],lz:left(item.csocHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csocSubOrders || #request.defines['csocSubOrders']!=null">
				<s:if test="#request.defines['csocSubOrders']>0">
					${lz:set("注释","****csoPayMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csocSubOrders))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoPayMember",lz:indexOf(fieldName,"csocSubOrders")>-1)}
				  		<s:if test="#request.atCsoPayMember==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csocSubOrders字段的字串格式化输出****")}
					<td ref="csocSubOrders" class="td " relate="${item.csocSubOrders}">
						 
						 	<a >
						 	${lz:or(item$csocSubOrders[i.count-1],lz:left(item.csocSubOrders$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csocPayMember || #request.defines['csocPayMember']!=null">
				<s:if test="#request.defines['csocPayMember']>0">
					${lz:set("注释","****csoUseMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csocPayMember))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoUseMember",lz:indexOf(fieldName,"csocPayMember")>-1)}
				  		<s:if test="#request.atCsoUseMember==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoUseMember字段的字串格式化输出****")}
					<td ref="csocPayMember" class="td " relate="${item.csocPayMember}">
						 
						 	<a <s:if test="#item.csocPayMember!=null && #item.csocPayMember!=''"> onclick="window.href('${basePath}${proname}/user/member_details.do?key=${item.csocPayMember}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csocPayMember[i.count-1],lz:left(item.csocPayMember$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csocMobile || #request.defines['csocMobile']!=null">
				<s:if test="#request.defines['csocMobile']>0">
					${lz:set("注释","****csoFeeType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csocMobile))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoFeeType",lz:indexOf(fieldName,"csocMobile")>-1)}
				  		<s:if test="#request.atCsoFeeType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoFeeType字段的字串格式化输出****")}
					<td ref="csocMobile" class="td " relate="${item.csocMobile}">
						 
						 	${lz:or(item$csocMobile[i.count-1],lz:left(item.csocMobile$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csocPrice || #request.defines['csocPrice']!=null">
				<s:if test="#request.defines['csocPrice']>0">
					${lz:set("注释","****csoArea关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csocPrice))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoArea",lz:indexOf(fieldName,"csocPrice")>-1)}
				  		<s:if test="#request.atCsoArea==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoArea字段的字串格式化输出****")}
					<td ref="csocPrice" class="td " relate="${item.csocPrice}">
						 
						 	${lz:or(item$csocPrice[i.count-1],lz:left(item.csocPrice$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csocMarginNeed || #request.defines['csocMarginNeed']!=null">
				<s:if test="#request.defines['csocMarginNeed']>0">
					${lz:set("注释","****csoOutlets关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csocMarginNeed))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoOutlets",lz:indexOf(fieldName,"csocMarginNeed")>-1)}
				  		<s:if test="#request.atCsoOutlets==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoOutlets字段的字串格式化输出****")}
					<td ref="csocMarginNeed" class="td " relate="${item.csocMarginNeed}">
						 
						 	${lz:or(item$csocMarginNeed[i.count-1],lz:left(item.csocMarginNeed$,100))}
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csocTotalDuration || #request.defines['csocTotalDuration']!=null">
				<s:if test="#request.defines['csocTotalDuration']>0">
					${lz:set("注释","****csoOutletsRet关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csocTotalDuration))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoOutletsRet",lz:indexOf(fieldName,"csocTotalDuration")>-1)}
				  		<s:if test="#request.atCsoOutletsRet==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoOutletsRet字段的字串格式化输出****")}
					<td ref="csocTotalDuration" class="td " relate="${item.csocTotalDuration}">
						 
						 	${lz:or(item$csocTotalDuration[i.count-1],lz:left(item.csocTotalDuration$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csocPayNeed || #request.defines['csocPayNeed']!=null">
				<s:if test="#request.defines['csocPayNeed']>0">
					${lz:set("注释","****csoModel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csocPayNeed))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoModel",lz:indexOf(fieldName,"csocPayNeed")>-1)}
				  		<s:if test="#request.atCsoModel==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoModel字段的字串格式化输出****")}
					<td ref="csocPayNeed" class="td " relate="${item.csocPayNeed}">
						 
						 	${lz:or(item$csocPayNeed[i.count-1],lz:left(item.csocPayNeed$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csocPayReal || #request.defines['csocPayReal']!=null">
				<s:if test="#request.defines['csocPayReal']>0">
					${lz:set("注释","****csoCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csocPayReal))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoCar",lz:indexOf(fieldName,"csocPayReal")>-1)}
				  		<s:if test="#request.atCsoCar==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoCar字段的字串格式化输出****")}
					<td ref="csocPayReal" class="td " relate="${item.csocPayReal}">
						 
						 	${lz:or(item$csocPayReal[i.count-1],lz:left(item.csocPayReal$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csocPayRent || #request.defines['csocPayRent']!=null">
				<s:if test="#request.defines['csocPayRent']>0">
					${lz:set("注释","****csoProvid关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csocPayRent))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoProvid",lz:indexOf(fieldName,"csocPayRent")>-1)}
				  		<s:if test="#request.atCsoProvid==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoProvid字段的字串格式化输出****")}
					<td ref="csocPayRent" class="td " relate="${item.csocPayRent}">
						 
						 	${lz:or(item$csocPayRent[i.count-1],lz:left(item.csocPayRent$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csocPayInsure || #request.defines['csocPayInsure']!=null">
				<s:if test="#request.defines['csocPayInsure']>0">
					${lz:set("注释","****csoCarNumber关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csocPayInsure))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoCarNumber",lz:indexOf(fieldName,"csocPayInsure")>-1)}
				  		<s:if test="#request.atCsoCarNumber==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoCarNumber字段的字串格式化输出****")}
					<td ref="csocPayInsure" class="td ">
						 
						 	${lz:or(item$csocPayInsure[i.count-1],lz:left(item.csocPayInsure$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csocPayTimeout || #request.defines['csocPayTimeout']!=null">
				<s:if test="#request.defines['csocPayTimeout']>0">
					${lz:set("注释","****csoMobile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csocPayTimeout))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoMobile",lz:indexOf(fieldName,"csocPayTimeout")>-1)}
				  		<s:if test="#request.atCsoMobile==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoMobile字段的字串格式化输出****")}
					<td ref="csocPayTimeout" class="td ">
						 
						 	${lz:or(item$csocPayTimeout[i.count-1],lz:left(item.csocPayTimeout$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csocPayDetails || #request.defines['csocPayDetails']!=null">
				<s:if test="#request.defines['csocPayDetails']>0">
					${lz:set("注释","****csoEvRfid关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csocPayDetails))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoEvRfid",lz:indexOf(fieldName,"csocPayDetails")>-1)}
				  		<s:if test="#request.atCsoEvRfid==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoEvRfid字段的字串格式化输出****")}
					<td ref="csocPayDetails" class="td ">
						 
						 	${lz:or(item$csocPayDetails[i.count-1],lz:left(item.csocPayDetails$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csocStartTime || #request.defines['csocStartTime']!=null">
				<s:if test="#request.defines['csocStartTime']>0">
					${lz:set("注释","****csoIsAdCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csocStartTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoIsAdCar",lz:indexOf(fieldName,"csocStartTime")>-1)}
				  		<s:if test="#request.atCsoIsAdCar==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoIsAdCar字段的字串格式化输出****")}
					<td ref="csocStartTime" class="td ">
						 
						 	${lz:or(item$csocStartTime[i.count-1],lz:left(item.csocStartTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoStartTime || #request.defines['csoStartTime']!=null">
				<s:if test="#request.defines['csoStartTime']>0">
					${lz:set("注释","****csoStartTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoStartTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoStartTime",lz:indexOf(fieldName,"csoStartTime")>-1)}
				  		<s:if test="#request.atCsoStartTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoStartTime字段的字串格式化输出****")}
					<td ref="csoStartTime" class="td ">
						 
						 	${lz:or(item$csoStartTime[i.count-1],lz:left(item.csoStartTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csocFinishTime || #request.defines['csocFinishTime']!=null">
				<s:if test="#request.defines['csocFinishTime']>0">
					${lz:set("注释","****csocFinishTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csocFinishTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atcsocFinishTime",lz:indexOf(fieldName,"csocFinishTime")>-1)}
				  		<s:if test="#request.atcsocFinishTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csocFinishTime字段的字串格式化输出****")}
					<td ref="csocFinishTime" class="td ">
						 
						 	${lz:or(item$csocFinishTime[i.count-1],lz:left(item.csocFinishTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csocStatus || #request.defines['csocStatus']!=null">
				<s:if test="#request.defines['csocStatus']>0">
					${lz:set("注释","****csoLongOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csocStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoLongOrder",lz:indexOf(fieldName,"csocStatus")>-1)}
				  		<s:if test="#request.atCsoLongOrder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoLongOrder字段的字串格式化输出****")}
					<td ref="csocStatus" class="td ">
						 
						 	${lz:or(item$csocStatus[i.count-1],lz:left(item.csocStatus$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csocAddTime || #request.defines['csocAddTime']!=null">
				<s:if test="#request.defines['csocAddTime']>0">
					${lz:set("注释","****csoLongPrice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csocAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoLongPrice",lz:indexOf(fieldName,"csocAddTime")>-1)}
				  		<s:if test="#request.atCsoLongPrice==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoLongPrice字段的字串格式化输出****")}
					<td ref="csocAddTime" class="td ">
						 
						 	${lz:or(item$csocAddTime[i.count-1],lz:left(item.csocAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csocUpdateTime || #request.defines['csocUpdateTime']!=null">
				<s:if test="#request.defines['csocUpdateTime']>0">
					${lz:set("注释","****csoUseType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csocUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoUseType",lz:indexOf(fieldName,"csocUpdateTime")>-1)}
				  		<s:if test="#request.atCsoUseType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoUseType字段的字串格式化输出****")}
					<td ref="csocUpdateTime" class="td ">
						 
						 	${lz:or(item$csocUpdateTime[i.count-1],lz:left(item.csocUpdateTime$,100))}
					</td>
				</s:else>
			</s:if>
		   ${lz:set("注释","*****************数据列表数据部分结束*****************")}		  
		  
		   
		    ${lz:set("注释","****自定义的单行记录操作句柄[request中的handles(String[])变量]****")}
			
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectOrder('${item.csocId}')">选择</a>
			</s:if>
		
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsOrder('${item.csocId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csocId || #request.defines['csocId']!=null">
				<s:if test="#request.defines['csocId']>0">
					${lz:set("注释","****csoId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoId",lz:indexOf(fieldName,"csocId")>-1)}				  		
				  		<s:if test="#request.atCsocId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csocId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csocHost || #request.defines['csocHost']!=null">
				<s:if test="#request.defines['csocHost']>0">
					${lz:set("注释","****csoHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoHost",lz:indexOf(fieldName,"csocHost")>-1)}				  		
				  		<s:if test="#request.atCsocHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csocHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csocSubOrders || #request.defines['csocSubOrders']!=null">
				<s:if test="#request.defines['csocSubOrders']>0">
					${lz:set("注释","****csoPayMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoPayMember",lz:indexOf(fieldName,"csocSubOrders")>-1)}				  		
				  		<s:if test="#request.atCsocSubOrders==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csocSubOrders[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csocPayMember || #request.defines['csocPayMember']!=null">
				<s:if test="#request.defines['csocPayMember']>0">
					${lz:set("注释","****csoUseMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoUseMember",lz:indexOf(fieldName,"csocPayMember")>-1)}				  		
				  		<s:if test="#request.atCsocPayMember==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csocPayMember[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csocMobile || #request.defines['csocMobile']!=null">
				<s:if test="#request.defines['csocMobile']>0">
					${lz:set("注释","****csoFeeType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoFeeType",lz:indexOf(fieldName,"csocMobile")>-1)}				  		
				  		<s:if test="#request.atCsocMobile==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csocMobile[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csocPrice || #request.defines['csocPrice']!=null">
				<s:if test="#request.defines['csocPrice']>0">
					${lz:set("注释","****csoArea关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoArea",lz:indexOf(fieldName,"csocPrice")>-1)}				  		
				  		<s:if test="#request.atCsocPrice==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csocPrice[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csocMarginNeed || #request.defines['csocMarginNeed']!=null">
				<s:if test="#request.defines['csocMarginNeed']>0">
					${lz:set("注释","****csoOutlets关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoOutlets",lz:indexOf(fieldName,"csocMarginNeed")>-1)}				  		
				  		<s:if test="#request.atCsocMarginNeed==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csocMarginNeed[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csocTotalDuration || #request.defines['csocTotalDuration']!=null">
				<s:if test="#request.defines['csocTotalDuration']>0">
					${lz:set("注释","****csoOutletsRet关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoOutletsRet",lz:indexOf(fieldName,"csocTotalDuration")>-1)}				  		
				  		<s:if test="#request.atCsocTotalDuration==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csocTotalDuration[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csocPayNeed || #request.defines['csocPayNeed']!=null">
				<s:if test="#request.defines['csocPayNeed']>0">
					${lz:set("注释","****csoModel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoModel",lz:indexOf(fieldName,"csocPayNeed")>-1)}				  		
				  		<s:if test="#request.atCsocPayNeed==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csocPayNeed[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csocPayReal || #request.defines['csocPayReal']!=null">
				<s:if test="#request.defines['csocPayReal']>0">
					${lz:set("注释","****csoCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoCar",lz:indexOf(fieldName,"csocPayReal")>-1)}				  		
				  		<s:if test="#request.atCsocPayReal==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csocPayReal[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csocPayRent || #request.defines['csocPayRent']!=null">
				<s:if test="#request.defines['csocPayRent']>0">
					${lz:set("注释","****csoProvid关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoProvid",lz:indexOf(fieldName,"csocPayRent")>-1)}				  		
				  		<s:if test="#request.atCsocPayRent==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csocPayRent[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csocPayInsure || #request.defines['csocPayInsure']!=null">
				<s:if test="#request.defines['csocPayInsure']>0">
					${lz:set("注释","****csoCarNumber关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoCarNumber",lz:indexOf(fieldName,"csocPayInsure")>-1)}				  		
				  		<s:if test="#request.atCsocPayInsure==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csocPayInsure[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csocPayTimeout || #request.defines['csocPayTimeout']!=null">
				<s:if test="#request.defines['csocPayTimeout']>0">
					${lz:set("注释","****csoMobile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoMobile",lz:indexOf(fieldName,"csocPayTimeout")>-1)}				  		
				  		<s:if test="#request.atCsocPayTimeout==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csocPayTimeout[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csocPayDetails || #request.defines['csocPayDetails']!=null">
				<s:if test="#request.defines['csocPayDetails']>0">
					${lz:set("注释","****csoEvRfid关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoEvRfid",lz:indexOf(fieldName,"csocPayDetails")>-1)}				  		
				  		<s:if test="#request.atCsocPayDetails==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csocPayDetails[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csocStartTime || #request.defines['csocStartTime']!=null">
				<s:if test="#request.defines['csocStartTime']>0">
					${lz:set("注释","****csoIsAdCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoIsAdCar",lz:indexOf(fieldName,"csocStartTime")>-1)}				  		
				  		<s:if test="#request.atCsocStartTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csocStartTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoStartTime || #request.defines['csoStartTime']!=null">
				<s:if test="#request.defines['csoStartTime']>0">
					${lz:set("注释","****csoStartTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoStartTime",lz:indexOf(fieldName,"csoStartTime")>-1)}				  		
				  		<s:if test="#request.atCsoStartTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoStartTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csocFinishTime || #request.defines['csocFinishTime']!=null">
				<s:if test="#request.defines['csocFinishTime']>0">
					${lz:set("注释","****csocFinishTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atcsocFinishTime",lz:indexOf(fieldName,"csocFinishTime")>-1)}				  		
				  		<s:if test="#request.atcsocFinishTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csocFinishTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csocStatus || #request.defines['csocStatus']!=null">
				<s:if test="#request.defines['csocStatus']>0">
					${lz:set("注释","****csoLongOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoLongOrder",lz:indexOf(fieldName,"csocStatus")>-1)}				  		
				  		<s:if test="#request.atCsocStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csocStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csocAddTime || #request.defines['csocAddTime']!=null">
				<s:if test="#request.defines['csocAddTime']>0">
					${lz:set("注释","****csoLongPrice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoLongPrice",lz:indexOf(fieldName,"csocAddTime")>-1)}				  		
				  		<s:if test="#request.atCsocAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csocAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csocUpdateTime || #request.defines['csocUpdateTime']!=null">
				<s:if test="#request.defines['csocUpdateTime']>0">
					${lz:set("注释","****csoUseType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoUseType",lz:indexOf(fieldName,"csocUpdateTime")>-1)}				  		
				  		<s:if test="#request.atCsocUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csocUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 选择系统订单
	**/
	function SelectOrderCluster(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改系统订单任意字段
		/* $(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}ordercluster_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新订单簇",visible:false,editable:false,fields:{}}};
			params.ctrl["fields"][$(this).attr("ref")]={visible:true,editable:true};
			href(url,params);
		}); */
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
			var url = "${basePath}${namespace}ordercluster_edit.do";
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
			var url = "${basePath}${namespace}ordercluster_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}

	/**
	* 显示系统订单详情
	**/
	function DetailsOrder(id)
	{
		var url = "${basePath}${namespace}ordercluster_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"系统订单详情",editable:false,visible:true}};
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
		DetailsOrder(checker.val());
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


</body>
</html>