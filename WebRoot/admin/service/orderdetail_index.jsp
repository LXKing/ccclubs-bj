<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/service/orderdetail.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
${lz:set("canView",true)}
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csOrderDetail.csodId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csodId,csodHost,csodOrder,csodProduct,csodGoods,csodPricer,csodOutlets,csodModel,csodCar,csodProvid,csodRule,csodRemark,csodAddTime",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csodId,csodHost,csodOrder,csodProduct,csodGoods,csodPricer,csodOutlets,csodModel,csodCar,csodProvid,csodStart,csodEnd,csodRule,csodCount,csodCountReal,csodPrice,csodPriceReal,csodTotal,csodRebate,csodTotalReal,csodRemark,csodAddTime",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csodId,csodHost,csodOrder,csodProduct,csodGoods,csodPricer,csodRule,csodRemark",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csodId,csodHost,csodOrder,csodProduct,csodGoods,csodPricer,csodStart,csodEnd,csodRule,csodCount,csodCountReal,csodPrice,csodPriceReal,csodTotal,csodRebate,csodTotalReal,csodRemark",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>订单明细管理</title>
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
	<span class="caption">${empty title?"订单明细管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsOrderDetail" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="orderdetail.query.jsp"%>

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
			<button type="button" class="add" onclick="AddOrderdetail();">
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
			<dl ref="com.ccclubs.model.CsOrderDetail" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.CsOrderDetail" class="batch dropdowan">
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
		<table id="orderdetail_table" ref="CsOrderDetail" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csodId || #request.defines['csodId']!=null">
			 <td <s:if test="#request.defines['csodId']>0">colspan="${defines["csodId"]}" ${all?"width":"iwidth"}="${defines["csodId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="csodId" flagKey  title="编号">
			 	<a class="${desc=="csod_id" ? "desc" : ""}${asc=="csod_id" ? "asc" : ""}" href="?${desc=="csod_id" ? "asc=csod_id" : ""}${(asc=="csod_id" || desc!="csod_id" )? "desc=csod_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","csodId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csodHost || #request.defines['csodHost']!=null">
			 <td <s:if test="#request.defines['csodHost']>0">colspan="${defines["csodHost"]}" ${all?"width":"iwidth"}="${defines["csodHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="csodHost"   title="城市">
			 	<a class="${desc=="csod_host" ? "desc" : ""}${asc=="csod_host" ? "asc" : ""}" href="?${desc=="csod_host" ? "asc=csod_host" : ""}${(asc=="csod_host" || desc!="csod_host" )? "desc=csod_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","csodHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csodOrder || #request.defines['csodOrder']!=null">
			 <td <s:if test="#request.defines['csodOrder']>0">colspan="${defines["csodOrder"]}" ${all?"width":"iwidth"}="${defines["csodOrder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="3" ref="csodOrder"   title="所属定单">
			 	<a class="${desc=="csod_order" ? "desc" : ""}${asc=="csod_order" ? "asc" : ""}" href="?${desc=="csod_order" ? "asc=csod_order" : ""}${(asc=="csod_order" || desc!="csod_order" )? "desc=csod_order" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属定单
			 	</a>
			 	${lz:set("checkeds[]","csodOrder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csodProduct || #request.defines['csodProduct']!=null">
			 <td <s:if test="#request.defines['csodProduct']>0">colspan="${defines["csodProduct"]}" ${all?"width":"iwidth"}="${defines["csodProduct"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="csodProduct"   title="所属产品">
			 	<a class="${desc=="csod_product" ? "desc" : ""}${asc=="csod_product" ? "asc" : ""}" href="?${desc=="csod_product" ? "asc=csod_product" : ""}${(asc=="csod_product" || desc!="csod_product" )? "desc=csod_product" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属产品
			 	</a>
			 	${lz:set("checkeds[]","csodProduct")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csodGoods || #request.defines['csodGoods']!=null">
			 <td <s:if test="#request.defines['csodGoods']>0">colspan="${defines["csodGoods"]}" ${all?"width":"iwidth"}="${defines["csodGoods"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="csodGoods"   title="所属商品">
			 	<a class="${desc=="csod_goods" ? "desc" : ""}${asc=="csod_goods" ? "asc" : ""}" href="?${desc=="csod_goods" ? "asc=csod_goods" : ""}${(asc=="csod_goods" || desc!="csod_goods" )? "desc=csod_goods" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属商品
			 	</a>
			 	${lz:set("checkeds[]","csodGoods")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csodPricer || #request.defines['csodPricer']!=null">
			 <td <s:if test="#request.defines['csodPricer']>0">colspan="${defines["csodPricer"]}" ${all?"width":"iwidth"}="${defines["csodPricer"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="csodPricer"   title="所属价格">
			 	<a class="${desc=="csod_pricer" ? "desc" : ""}${asc=="csod_pricer" ? "asc" : ""}" href="?${desc=="csod_pricer" ? "asc=csod_pricer" : ""}${(asc=="csod_pricer" || desc!="csod_pricer" )? "desc=csod_pricer" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属价格
			 	</a>
			 	${lz:set("checkeds[]","csodPricer")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csodOutlets || #request.defines['csodOutlets']!=null">
			 <td <s:if test="#request.defines['csodOutlets']>0">colspan="${defines["csodOutlets"]}" ${all?"width":"iwidth"}="${defines["csodOutlets"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="7" ref="csodOutlets"   title="所属网点">
			 	<a class="${desc=="csod_outlets" ? "desc" : ""}${asc=="csod_outlets" ? "asc" : ""}" href="?${desc=="csod_outlets" ? "asc=csod_outlets" : ""}${(asc=="csod_outlets" || desc!="csod_outlets" )? "desc=csod_outlets" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属网点
			 	</a>
			 	${lz:set("checkeds[]","csodOutlets")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csodModel || #request.defines['csodModel']!=null">
			 <td <s:if test="#request.defines['csodModel']>0">colspan="${defines["csodModel"]}" ${all?"width":"iwidth"}="${defines["csodModel"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="csodModel"   title="所属车型">
			 	<a class="${desc=="csod_model" ? "desc" : ""}${asc=="csod_model" ? "asc" : ""}" href="?${desc=="csod_model" ? "asc=csod_model" : ""}${(asc=="csod_model" || desc!="csod_model" )? "desc=csod_model" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属车型
			 	</a>
			 	${lz:set("checkeds[]","csodModel")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csodCar || #request.defines['csodCar']!=null">
			 <td <s:if test="#request.defines['csodCar']>0">colspan="${defines["csodCar"]}" ${all?"width":"iwidth"}="${defines["csodCar"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="9" ref="csodCar"   title="所属车辆">
			 	<a class="${desc=="csod_car" ? "desc" : ""}${asc=="csod_car" ? "asc" : ""}" href="?${desc=="csod_car" ? "asc=csod_car" : ""}${(asc=="csod_car" || desc!="csod_car" )? "desc=csod_car" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属车辆
			 	</a>
			 	${lz:set("checkeds[]","csodCar")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csodProvid || #request.defines['csodProvid']!=null">
			 <td <s:if test="#request.defines['csodProvid']>0">colspan="${defines["csodProvid"]}" ${all?"width":"iwidth"}="${defines["csodProvid"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="10" ref="csodProvid"   title="所属商家">
			 	<a class="${desc=="csod_provid" ? "desc" : ""}${asc=="csod_provid" ? "asc" : ""}" href="?${desc=="csod_provid" ? "asc=csod_provid" : ""}${(asc=="csod_provid" || desc!="csod_provid" )? "desc=csod_provid" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属商家
			 	</a>
			 	${lz:set("checkeds[]","csodProvid")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csodStart || #request.defines['csodStart']!=null">
			 <td <s:if test="#request.defines['csodStart']>0">colspan="${defines["csodStart"]}" ${all?"width":"iwidth"}="${defines["csodStart"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="11" ref="csodStart"   title="开始时间">
			 	<a class="${desc=="csod_start" ? "desc" : ""}${asc=="csod_start" ? "asc" : ""}" href="?${desc=="csod_start" ? "asc=csod_start" : ""}${(asc=="csod_start" || desc!="csod_start" )? "desc=csod_start" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	开始时间
			 	</a>
			 	${lz:set("checkeds[]","csodStart")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csodEnd || #request.defines['csodEnd']!=null">
			 <td <s:if test="#request.defines['csodEnd']>0">colspan="${defines["csodEnd"]}" ${all?"width":"iwidth"}="${defines["csodEnd"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="12" ref="csodEnd"   title="结束时间">
			 	<a class="${desc=="csod_end" ? "desc" : ""}${asc=="csod_end" ? "asc" : ""}" href="?${desc=="csod_end" ? "asc=csod_end" : ""}${(asc=="csod_end" || desc!="csod_end" )? "desc=csod_end" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	结束时间
			 	</a>
			 	${lz:set("checkeds[]","csodEnd")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csodRule || #request.defines['csodRule']!=null">
			 <td <s:if test="#request.defines['csodRule']>0">colspan="${defines["csodRule"]}" ${all?"width":"iwidth"}="${defines["csodRule"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="13" ref="csodRule"   title="单位">
			 	<a class="${desc=="csod_rule" ? "desc" : ""}${asc=="csod_rule" ? "asc" : ""}" href="?${desc=="csod_rule" ? "asc=csod_rule" : ""}${(asc=="csod_rule" || desc!="csod_rule" )? "desc=csod_rule" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	单位
			 	</a>
			 	${lz:set("checkeds[]","csodRule")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csodCount || #request.defines['csodCount']!=null">
			 <td <s:if test="#request.defines['csodCount']>0">colspan="${defines["csodCount"]}" ${all?"width":"iwidth"}="${defines["csodCount"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="14" ref="csodCount"   title="理论数量">
			 	<a class="${desc=="csod_count" ? "desc" : ""}${asc=="csod_count" ? "asc" : ""}" href="?${desc=="csod_count" ? "asc=csod_count" : ""}${(asc=="csod_count" || desc!="csod_count" )? "desc=csod_count" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	理论数量
			 	</a>
			 	${lz:set("checkeds[]","csodCount")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csodCountReal || #request.defines['csodCountReal']!=null">
			 <td <s:if test="#request.defines['csodCountReal']>0">colspan="${defines["csodCountReal"]}" ${all?"width":"iwidth"}="${defines["csodCountReal"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="15" ref="csodCountReal"   title="实际数量">
			 	<a class="${desc=="csod_count_real" ? "desc" : ""}${asc=="csod_count_real" ? "asc" : ""}" href="?${desc=="csod_count_real" ? "asc=csod_count_real" : ""}${(asc=="csod_count_real" || desc!="csod_count_real" )? "desc=csod_count_real" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	实际数量
			 	</a>
			 	${lz:set("checkeds[]","csodCountReal")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csodPrice || #request.defines['csodPrice']!=null">
			 <td <s:if test="#request.defines['csodPrice']>0">colspan="${defines["csodPrice"]}" ${all?"width":"iwidth"}="${defines["csodPrice"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="16" ref="csodPrice"   title="理论单价">
			 	<a class="${desc=="csod_price" ? "desc" : ""}${asc=="csod_price" ? "asc" : ""}" href="?${desc=="csod_price" ? "asc=csod_price" : ""}${(asc=="csod_price" || desc!="csod_price" )? "desc=csod_price" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	理论单价
			 	</a>
			 	${lz:set("checkeds[]","csodPrice")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csodPriceReal || #request.defines['csodPriceReal']!=null">
			 <td <s:if test="#request.defines['csodPriceReal']>0">colspan="${defines["csodPriceReal"]}" ${all?"width":"iwidth"}="${defines["csodPriceReal"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="17" ref="csodPriceReal"   title="实际单价">
			 	<a class="${desc=="csod_price_real" ? "desc" : ""}${asc=="csod_price_real" ? "asc" : ""}" href="?${desc=="csod_price_real" ? "asc=csod_price_real" : ""}${(asc=="csod_price_real" || desc!="csod_price_real" )? "desc=csod_price_real" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	实际单价
			 	</a>
			 	${lz:set("checkeds[]","csodPriceReal")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csodTotal || #request.defines['csodTotal']!=null">
			 <td <s:if test="#request.defines['csodTotal']>0">colspan="${defines["csodTotal"]}" ${all?"width":"iwidth"}="${defines["csodTotal"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="18" ref="csodTotal"   title="理论金额">
			 	<a class="${desc=="csod_total" ? "desc" : ""}${asc=="csod_total" ? "asc" : ""}" href="?${desc=="csod_total" ? "asc=csod_total" : ""}${(asc=="csod_total" || desc!="csod_total" )? "desc=csod_total" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	理论金额
			 	</a>
			 	${lz:set("checkeds[]","csodTotal")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csodRebate || #request.defines['csodRebate']!=null">
			 <td <s:if test="#request.defines['csodRebate']>0">colspan="${defines["csodRebate"]}" ${all?"width":"iwidth"}="${defines["csodRebate"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="19" ref="csodRebate"   title="折扣">
			 	<a class="${desc=="csod_rebate" ? "desc" : ""}${asc=="csod_rebate" ? "asc" : ""}" href="?${desc=="csod_rebate" ? "asc=csod_rebate" : ""}${(asc=="csod_rebate" || desc!="csod_rebate" )? "desc=csod_rebate" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	折扣
			 	</a>
			 	${lz:set("checkeds[]","csodRebate")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csodTotalReal || #request.defines['csodTotalReal']!=null">
			 <td <s:if test="#request.defines['csodTotalReal']>0">colspan="${defines["csodTotalReal"]}" ${all?"width":"iwidth"}="${defines["csodTotalReal"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="20" ref="csodTotalReal"   title="实际金额">
			 	<a class="${desc=="csod_total_real" ? "desc" : ""}${asc=="csod_total_real" ? "asc" : ""}" href="?${desc=="csod_total_real" ? "asc=csod_total_real" : ""}${(asc=="csod_total_real" || desc!="csod_total_real" )? "desc=csod_total_real" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	实际金额
			 	</a>
			 	${lz:set("checkeds[]","csodTotalReal")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csodRemark || #request.defines['csodRemark']!=null">
			 <td <s:if test="#request.defines['csodRemark']>0">colspan="${defines["csodRemark"]}" ${all?"width":"iwidth"}="${defines["csodRemark"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="21" ref="csodRemark"   title="备注">
			 	<a class="${desc=="csod_remark" ? "desc" : ""}${asc=="csod_remark" ? "asc" : ""}" href="?${desc=="csod_remark" ? "asc=csod_remark" : ""}${(asc=="csod_remark" || desc!="csod_remark" )? "desc=csod_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	备注
			 	</a>
			 	${lz:set("checkeds[]","csodRemark")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csodAddTime || #request.defines['csodAddTime']!=null">
			 <td <s:if test="#request.defines['csodAddTime']>0">colspan="${defines["csodAddTime"]}" ${all?"width":"iwidth"}="${defines["csodAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="22" ref="csodAddTime"   title="添加时间">
			 	<a class="${desc=="csod_add_time" ? "desc" : ""}${asc=="csod_add_time" ? "asc" : ""}" href="?${desc=="csod_add_time" ? "asc=csod_add_time" : ""}${(asc=="csod_add_time" || desc!="csod_add_time" )? "desc=csod_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","csodAddTime")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="23" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="24" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:AddOrderdetail()" style="text-decoration:underline">添加</a>
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
		  <tr id="${item.csodId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csodId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csodId || #request.defines['csodId']!=null">
				<s:if test="#request.defines['csodId']>0">
					${lz:set("注释","****csodId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csodId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsodId",lz:indexOf(fieldName,"csodId")>-1)}
				  		<s:if test="#request.atCsodId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csodId字段的字串格式化输出****")}
					<td ref="csodId" class="td  node">
						 
						 	${lz:or(item$csodId[i.count-1],lz:left(item.csodId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csodHost || #request.defines['csodHost']!=null">
				<s:if test="#request.defines['csodHost']>0">
					${lz:set("注释","****csodHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csodHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsodHost",lz:indexOf(fieldName,"csodHost")>-1)}
				  		<s:if test="#request.atCsodHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csodHost字段的字串格式化输出****")}
					<td ref="csodHost" class="td " relate="${item.csodHost}">
						 
						 	<a <s:if test="#item.csodHost!=null && #item.csodHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.csodHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csodHost[i.count-1],lz:left(item.csodHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csodOrder || #request.defines['csodOrder']!=null">
				<s:if test="#request.defines['csodOrder']>0">
					${lz:set("注释","****csodOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csodOrder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsodOrder",lz:indexOf(fieldName,"csodOrder")>-1)}
				  		<s:if test="#request.atCsodOrder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csodOrder字段的字串格式化输出****")}
					<td ref="csodOrder" class="td " relate="${item.csodOrder}">
						 
						 	<a <s:if test="#item.csodOrder!=null && #item.csodOrder!=''"> onclick="window.href('${basePath}${proname}/service/order_details.do?key=${item.csodOrder}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csodOrder[i.count-1],lz:left(item.csodOrder$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csodProduct || #request.defines['csodProduct']!=null">
				<s:if test="#request.defines['csodProduct']>0">
					${lz:set("注释","****csodProduct关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csodProduct))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsodProduct",lz:indexOf(fieldName,"csodProduct")>-1)}
				  		<s:if test="#request.atCsodProduct==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csodProduct字段的字串格式化输出****")}
					<td ref="csodProduct" class="td " relate="${item.csodProduct}">
						 
						 	<a <s:if test="#item.csodProduct!=null && #item.csodProduct!=''"> onclick="window.href('${basePath}${proname}/fee/product_details.do?key=${item.csodProduct}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csodProduct[i.count-1],lz:left(item.csodProduct$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csodGoods || #request.defines['csodGoods']!=null">
				<s:if test="#request.defines['csodGoods']>0">
					${lz:set("注释","****csodGoods关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csodGoods))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsodGoods",lz:indexOf(fieldName,"csodGoods")>-1)}
				  		<s:if test="#request.atCsodGoods==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csodGoods字段的字串格式化输出****")}
					<td ref="csodGoods" class="td " relate="${item.csodGoods}">
						 
						 	<a <s:if test="#item.csodGoods!=null && #item.csodGoods!=''"> onclick="window.href('${basePath}${proname}/fee/goods_details.do?key=${item.csodGoods}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csodGoods[i.count-1],lz:left(item.csodGoods$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csodPricer || #request.defines['csodPricer']!=null">
				<s:if test="#request.defines['csodPricer']>0">
					${lz:set("注释","****csodPricer关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csodPricer))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsodPricer",lz:indexOf(fieldName,"csodPricer")>-1)}
				  		<s:if test="#request.atCsodPricer==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csodPricer字段的字串格式化输出****")}
					<td ref="csodPricer" class="td " relate="${item.csodPricer}">
						 
						 	<a <s:if test="#item.csodPricer!=null && #item.csodPricer!=''"> onclick="window.href('${basePath}${proname}/fee/price_details.do?key=${item.csodPricer}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csodPricer[i.count-1],lz:left(item.csodPricer$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csodOutlets || #request.defines['csodOutlets']!=null">
				<s:if test="#request.defines['csodOutlets']>0">
					${lz:set("注释","****csodOutlets关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csodOutlets))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsodOutlets",lz:indexOf(fieldName,"csodOutlets")>-1)}
				  		<s:if test="#request.atCsodOutlets==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csodOutlets字段的字串格式化输出****")}
					<td ref="csodOutlets" class="td " relate="${item.csodOutlets}">
						 
						 	<a <s:if test="#item.csodOutlets!=null && #item.csodOutlets!=''"> onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${item.csodOutlets}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csodOutlets[i.count-1],lz:left(item.csodOutlets$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csodModel || #request.defines['csodModel']!=null">
				<s:if test="#request.defines['csodModel']>0">
					${lz:set("注释","****csodModel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csodModel))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsodModel",lz:indexOf(fieldName,"csodModel")>-1)}
				  		<s:if test="#request.atCsodModel==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csodModel字段的字串格式化输出****")}
					<td ref="csodModel" class="td " relate="${item.csodModel}">
						 
						 	<a <s:if test="#item.csodModel!=null && #item.csodModel!=''"> onclick="window.href('${basePath}${proname}/object/carmodel_details.do?key=${item.csodModel}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csodModel[i.count-1],lz:left(item.csodModel$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csodCar || #request.defines['csodCar']!=null">
				<s:if test="#request.defines['csodCar']>0">
					${lz:set("注释","****csodCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csodCar))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsodCar",lz:indexOf(fieldName,"csodCar")>-1)}
				  		<s:if test="#request.atCsodCar==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csodCar字段的字串格式化输出****")}
					<td ref="csodCar" class="td " relate="${item.csodCar}">
						 
						 	<a <s:if test="#item.csodCar!=null && #item.csodCar!=''"> onclick="window.href('${basePath}${proname}/object/car_details.do?key=${item.csodCar}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csodCar[i.count-1],lz:left(item.csodCar$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csodProvid || #request.defines['csodProvid']!=null">
				<s:if test="#request.defines['csodProvid']>0">
					${lz:set("注释","****csodProvid关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csodProvid))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsodProvid",lz:indexOf(fieldName,"csodProvid")>-1)}
				  		<s:if test="#request.atCsodProvid==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csodProvid字段的字串格式化输出****")}
					<td ref="csodProvid" class="td " relate="${item.csodProvid}">
						 
						 	<a <s:if test="#item.csodProvid!=null && #item.csodProvid!=''"> onclick="window.href('${basePath}${proname}/configurator/provid_details.do?key=${item.csodProvid}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csodProvid[i.count-1],lz:left(item.csodProvid$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csodStart || #request.defines['csodStart']!=null">
				<s:if test="#request.defines['csodStart']>0">
					${lz:set("注释","****csodStart关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csodStart))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsodStart",lz:indexOf(fieldName,"csodStart")>-1)}
				  		<s:if test="#request.atCsodStart==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csodStart字段的字串格式化输出****")}
					<td ref="csodStart" class="td ">
						 
						 	${lz:or(item$csodStart[i.count-1],lz:left(item.csodStart$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csodEnd || #request.defines['csodEnd']!=null">
				<s:if test="#request.defines['csodEnd']>0">
					${lz:set("注释","****csodEnd关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csodEnd))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsodEnd",lz:indexOf(fieldName,"csodEnd")>-1)}
				  		<s:if test="#request.atCsodEnd==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csodEnd字段的字串格式化输出****")}
					<td ref="csodEnd" class="td ">
						 
						 	${lz:or(item$csodEnd[i.count-1],lz:left(item.csodEnd$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csodRule || #request.defines['csodRule']!=null">
				<s:if test="#request.defines['csodRule']>0">
					${lz:set("注释","****csodRule关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csodRule))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsodRule",lz:indexOf(fieldName,"csodRule")>-1)}
				  		<s:if test="#request.atCsodRule==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csodRule字段的字串格式化输出****")}
					<td ref="csodRule" class="td " relate="${item.csodRule}">
						 
						 	<a <s:if test="#item.csodRule!=null && #item.csodRule!=''"> onclick="window.href('${basePath}${proname}/fee/rule_details.do?key=${item.csodRule}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csodRule[i.count-1],lz:left(item.csodRule$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csodCount || #request.defines['csodCount']!=null">
				<s:if test="#request.defines['csodCount']>0">
					${lz:set("注释","****csodCount关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csodCount))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsodCount",lz:indexOf(fieldName,"csodCount")>-1)}
				  		<s:if test="#request.atCsodCount==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csodCount字段的字串格式化输出****")}
					<td ref="csodCount" class="td ">
						 
						 	${lz:or(item$csodCount[i.count-1],lz:left(item.csodCount$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csodCountReal || #request.defines['csodCountReal']!=null">
				<s:if test="#request.defines['csodCountReal']>0">
					${lz:set("注释","****csodCountReal关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csodCountReal))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsodCountReal",lz:indexOf(fieldName,"csodCountReal")>-1)}
				  		<s:if test="#request.atCsodCountReal==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csodCountReal字段的字串格式化输出****")}
					<td ref="csodCountReal" class="td ">
						 
						 	${lz:or(item$csodCountReal[i.count-1],lz:left(item.csodCountReal$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csodPrice || #request.defines['csodPrice']!=null">
				<s:if test="#request.defines['csodPrice']>0">
					${lz:set("注释","****csodPrice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csodPrice))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsodPrice",lz:indexOf(fieldName,"csodPrice")>-1)}
				  		<s:if test="#request.atCsodPrice==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csodPrice字段的字串格式化输出****")}
					<td ref="csodPrice" class="td ">
						 
						 	${lz:or(item$csodPrice[i.count-1],lz:left(item.csodPrice$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csodPriceReal || #request.defines['csodPriceReal']!=null">
				<s:if test="#request.defines['csodPriceReal']>0">
					${lz:set("注释","****csodPriceReal关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csodPriceReal))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsodPriceReal",lz:indexOf(fieldName,"csodPriceReal")>-1)}
				  		<s:if test="#request.atCsodPriceReal==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csodPriceReal字段的字串格式化输出****")}
					<td ref="csodPriceReal" class="td ">
						 
						 	${lz:or(item$csodPriceReal[i.count-1],lz:left(item.csodPriceReal$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csodTotal || #request.defines['csodTotal']!=null">
				<s:if test="#request.defines['csodTotal']>0">
					${lz:set("注释","****csodTotal关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csodTotal))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsodTotal",lz:indexOf(fieldName,"csodTotal")>-1)}
				  		<s:if test="#request.atCsodTotal==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csodTotal字段的字串格式化输出****")}
					<td ref="csodTotal" class="td ">
						 
						 	${lz:or(item$csodTotal[i.count-1],lz:left(item.csodTotal$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csodRebate || #request.defines['csodRebate']!=null">
				<s:if test="#request.defines['csodRebate']>0">
					${lz:set("注释","****csodRebate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csodRebate))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsodRebate",lz:indexOf(fieldName,"csodRebate")>-1)}
				  		<s:if test="#request.atCsodRebate==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csodRebate字段的字串格式化输出****")}
					<td ref="csodRebate" class="td ">
						 
						 	${lz:or(item$csodRebate[i.count-1],lz:left(item.csodRebate$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csodTotalReal || #request.defines['csodTotalReal']!=null">
				<s:if test="#request.defines['csodTotalReal']>0">
					${lz:set("注释","****csodTotalReal关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csodTotalReal))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsodTotalReal",lz:indexOf(fieldName,"csodTotalReal")>-1)}
				  		<s:if test="#request.atCsodTotalReal==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csodTotalReal字段的字串格式化输出****")}
					<td ref="csodTotalReal" class="td ">
						 
						 	${lz:or(item$csodTotalReal[i.count-1],lz:left(item.csodTotalReal$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csodRemark || #request.defines['csodRemark']!=null">
				<s:if test="#request.defines['csodRemark']>0">
					${lz:set("注释","****csodRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csodRemark))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsodRemark",lz:indexOf(fieldName,"csodRemark")>-1)}
				  		<s:if test="#request.atCsodRemark==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csodRemark字段的字串格式化输出****")}
					<td ref="csodRemark" class="td ">
						 
						 	${lz:or(item$csodRemark[i.count-1],lz:left(item.csodRemark$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csodAddTime || #request.defines['csodAddTime']!=null">
				<s:if test="#request.defines['csodAddTime']>0">
					${lz:set("注释","****csodAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csodAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsodAddTime",lz:indexOf(fieldName,"csodAddTime")>-1)}
				  		<s:if test="#request.atCsodAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csodAddTime字段的字串格式化输出****")}
					<td ref="csodAddTime" class="td ">
						 
						 	${lz:or(item$csodAddTime[i.count-1],lz:left(item.csodAddTime$,100))}
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
					<a href="javascript:HandleSel('${handle}','${item.csodId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectOrderdetail('${item.csodId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditOrderdetail('${item.csodId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelOrderdetail('${item.csodId}','${item.csodId}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsOrderdetail('${item.csodId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csodId || #request.defines['csodId']!=null">
				<s:if test="#request.defines['csodId']>0">
					${lz:set("注释","****csodId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsodId",lz:indexOf(fieldName,"csodId")>-1)}				  		
				  		<s:if test="#request.atCsodId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csodId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csodHost || #request.defines['csodHost']!=null">
				<s:if test="#request.defines['csodHost']>0">
					${lz:set("注释","****csodHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsodHost",lz:indexOf(fieldName,"csodHost")>-1)}				  		
				  		<s:if test="#request.atCsodHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csodHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csodOrder || #request.defines['csodOrder']!=null">
				<s:if test="#request.defines['csodOrder']>0">
					${lz:set("注释","****csodOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsodOrder",lz:indexOf(fieldName,"csodOrder")>-1)}				  		
				  		<s:if test="#request.atCsodOrder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csodOrder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csodProduct || #request.defines['csodProduct']!=null">
				<s:if test="#request.defines['csodProduct']>0">
					${lz:set("注释","****csodProduct关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsodProduct",lz:indexOf(fieldName,"csodProduct")>-1)}				  		
				  		<s:if test="#request.atCsodProduct==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csodProduct[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csodGoods || #request.defines['csodGoods']!=null">
				<s:if test="#request.defines['csodGoods']>0">
					${lz:set("注释","****csodGoods关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsodGoods",lz:indexOf(fieldName,"csodGoods")>-1)}				  		
				  		<s:if test="#request.atCsodGoods==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csodGoods[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csodPricer || #request.defines['csodPricer']!=null">
				<s:if test="#request.defines['csodPricer']>0">
					${lz:set("注释","****csodPricer关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsodPricer",lz:indexOf(fieldName,"csodPricer")>-1)}				  		
				  		<s:if test="#request.atCsodPricer==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csodPricer[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csodOutlets || #request.defines['csodOutlets']!=null">
				<s:if test="#request.defines['csodOutlets']>0">
					${lz:set("注释","****csodOutlets关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsodOutlets",lz:indexOf(fieldName,"csodOutlets")>-1)}				  		
				  		<s:if test="#request.atCsodOutlets==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csodOutlets[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csodModel || #request.defines['csodModel']!=null">
				<s:if test="#request.defines['csodModel']>0">
					${lz:set("注释","****csodModel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsodModel",lz:indexOf(fieldName,"csodModel")>-1)}				  		
				  		<s:if test="#request.atCsodModel==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csodModel[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csodCar || #request.defines['csodCar']!=null">
				<s:if test="#request.defines['csodCar']>0">
					${lz:set("注释","****csodCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsodCar",lz:indexOf(fieldName,"csodCar")>-1)}				  		
				  		<s:if test="#request.atCsodCar==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csodCar[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csodProvid || #request.defines['csodProvid']!=null">
				<s:if test="#request.defines['csodProvid']>0">
					${lz:set("注释","****csodProvid关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsodProvid",lz:indexOf(fieldName,"csodProvid")>-1)}				  		
				  		<s:if test="#request.atCsodProvid==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csodProvid[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csodStart || #request.defines['csodStart']!=null">
				<s:if test="#request.defines['csodStart']>0">
					${lz:set("注释","****csodStart关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsodStart",lz:indexOf(fieldName,"csodStart")>-1)}				  		
				  		<s:if test="#request.atCsodStart==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csodStart[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csodEnd || #request.defines['csodEnd']!=null">
				<s:if test="#request.defines['csodEnd']>0">
					${lz:set("注释","****csodEnd关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsodEnd",lz:indexOf(fieldName,"csodEnd")>-1)}				  		
				  		<s:if test="#request.atCsodEnd==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csodEnd[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csodRule || #request.defines['csodRule']!=null">
				<s:if test="#request.defines['csodRule']>0">
					${lz:set("注释","****csodRule关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsodRule",lz:indexOf(fieldName,"csodRule")>-1)}				  		
				  		<s:if test="#request.atCsodRule==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csodRule[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csodCount || #request.defines['csodCount']!=null">
				<s:if test="#request.defines['csodCount']>0">
					${lz:set("注释","****csodCount关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsodCount",lz:indexOf(fieldName,"csodCount")>-1)}				  		
				  		<s:if test="#request.atCsodCount==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csodCount[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csodCountReal || #request.defines['csodCountReal']!=null">
				<s:if test="#request.defines['csodCountReal']>0">
					${lz:set("注释","****csodCountReal关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsodCountReal",lz:indexOf(fieldName,"csodCountReal")>-1)}				  		
				  		<s:if test="#request.atCsodCountReal==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csodCountReal[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csodPrice || #request.defines['csodPrice']!=null">
				<s:if test="#request.defines['csodPrice']>0">
					${lz:set("注释","****csodPrice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsodPrice",lz:indexOf(fieldName,"csodPrice")>-1)}				  		
				  		<s:if test="#request.atCsodPrice==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csodPrice[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csodPriceReal || #request.defines['csodPriceReal']!=null">
				<s:if test="#request.defines['csodPriceReal']>0">
					${lz:set("注释","****csodPriceReal关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsodPriceReal",lz:indexOf(fieldName,"csodPriceReal")>-1)}				  		
				  		<s:if test="#request.atCsodPriceReal==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csodPriceReal[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csodTotal || #request.defines['csodTotal']!=null">
				<s:if test="#request.defines['csodTotal']>0">
					${lz:set("注释","****csodTotal关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsodTotal",lz:indexOf(fieldName,"csodTotal")>-1)}				  		
				  		<s:if test="#request.atCsodTotal==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csodTotal[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csodRebate || #request.defines['csodRebate']!=null">
				<s:if test="#request.defines['csodRebate']>0">
					${lz:set("注释","****csodRebate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsodRebate",lz:indexOf(fieldName,"csodRebate")>-1)}				  		
				  		<s:if test="#request.atCsodRebate==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csodRebate[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csodTotalReal || #request.defines['csodTotalReal']!=null">
				<s:if test="#request.defines['csodTotalReal']>0">
					${lz:set("注释","****csodTotalReal关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsodTotalReal",lz:indexOf(fieldName,"csodTotalReal")>-1)}				  		
				  		<s:if test="#request.atCsodTotalReal==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csodTotalReal[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csodRemark || #request.defines['csodRemark']!=null">
				<s:if test="#request.defines['csodRemark']>0">
					${lz:set("注释","****csodRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsodRemark",lz:indexOf(fieldName,"csodRemark")>-1)}				  		
				  		<s:if test="#request.atCsodRemark==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csodRemark[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csodAddTime || #request.defines['csodAddTime']!=null">
				<s:if test="#request.defines['csodAddTime']>0">
					${lz:set("注释","****csodAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsodAddTime",lz:indexOf(fieldName,"csodAddTime")>-1)}				  		
				  		<s:if test="#request.atCsodAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csodAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 添加订单明细
	**/
	function AddOrderdetail(parent){		
		var url = "${basePath}${namespace}orderdetail_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加订单明细'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择订单明细
	**/
	function SelectOrderdetail(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改订单明细
	**/
	function EditOrderdetail(id){
		var url = "${basePath}${namespace}orderdetail_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改订单明细'}":lz:json(ctrl)}};
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
		EditOrderdetail(checker.val());
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
				var url = "${basePath}${namespace}orderdetail_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新订单明细",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}orderdetail_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新订单明细",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改订单明细
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}orderdetail_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新订单明细",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改订单明细任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}orderdetail_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新订单明细",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}orderdetail_edit.do";
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
			var url = "${basePath}${namespace}orderdetail_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除订单明细
	**/
	function DelOrderdetail(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除订单明细["+flag+"]吗？</span>",
				title:"删除订单明细",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}orderdetail_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选订单明细吗？</span>",
				title:"批量删除订单明细",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}orderdetail_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示订单明细详情
	**/
	function DetailsOrderdetail(id)
	{
		var url = "${basePath}${namespace}orderdetail_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"订单明细详情",editable:false,visible:true}};
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
		DetailsOrderdetail(checker.val());
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