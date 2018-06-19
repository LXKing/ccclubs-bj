<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/unit/bill.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitBill.csubId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csubId,csubHost,csubMonth,csubUnit,csubEditor,csubUpdateTime,csubAddTime,csubStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csubId,csubHost,csubMonth,csubUnit,csubAirS,csubOrderS,csubSpecialS,csubFeeAir,csubFeeOrder,csubFeeSpecial,csubNeedPay,csubRealPay,csubIncome,csubPayout,csubEditor,csubUpdateTime,csubAddTime,csubSummary,csubStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csubId,csubHost,csubMonth,csubUnit,csubStatus",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csubId,csubHost,csubMonth,csubUnit,csubAirS,csubOrderS,csubSpecialS,csubFeeAir,csubFeeOrder,csubFeeSpecial,csubNeedPay,csubRealPay,csubIncome,csubPayout,csubSummary,csubStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>企业对账管理</title>
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
	<span class="caption">${empty title?"企业对账管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsUnitBill" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="bill.query.jsp"%>

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
			<dl ref="com.ccclubs.model.CsUnitBill" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.CsUnitBill" class="batch dropdowan">
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
		<table id="bill_table" ref="CsUnitBill" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csubId || #request.defines['csubId']!=null">
			 <td <s:if test="#request.defines['csubId']>0">colspan="${defines["csubId"]}" ${all?"width":"iwidth"}="${defines["csubId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="csubId"   title="编号">
			 	<a class="${desc=="csub_id" ? "desc" : ""}${asc=="csub_id" ? "asc" : ""}" href="?${desc=="csub_id" ? "asc=csub_id" : ""}${(asc=="csub_id" || desc!="csub_id" )? "desc=csub_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	编号
			 	</a>
			 	${lz:set("checkeds[]","csubId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csubHost || #request.defines['csubHost']!=null">
			 <td <s:if test="#request.defines['csubHost']>0">colspan="${defines["csubHost"]}" ${all?"width":"iwidth"}="${defines["csubHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="csubHost"   title="运营地区">
			 	<a class="${desc=="csub_host" ? "desc" : ""}${asc=="csub_host" ? "asc" : ""}" href="?${desc=="csub_host" ? "asc=csub_host" : ""}${(asc=="csub_host" || desc!="csub_host" )? "desc=csub_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	运营地区
			 	</a>
			 	${lz:set("checkeds[]","csubHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csubMonth || #request.defines['csubMonth']!=null">
			 <td <s:if test="#request.defines['csubMonth']>0">colspan="${defines["csubMonth"]}" ${all?"width":"iwidth"}="${defines["csubMonth"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="3" ref="csubMonth" flagKey  title="帐单月份">
			 	<a class="${desc=="csub_month" ? "desc" : ""}${asc=="csub_month" ? "asc" : ""}" href="?${desc=="csub_month" ? "asc=csub_month" : ""}${(asc=="csub_month" || desc!="csub_month" )? "desc=csub_month" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	帐单月份
			 	</a>
			 	${lz:set("checkeds[]","csubMonth")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csubUnit || #request.defines['csubUnit']!=null">
			 <td <s:if test="#request.defines['csubUnit']>0">colspan="${defines["csubUnit"]}" ${all?"width":"iwidth"}="${defines["csubUnit"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="csubUnit"   title="所属单位">
			 	<a class="${desc=="csub_unit" ? "desc" : ""}${asc=="csub_unit" ? "asc" : ""}" href="?${desc=="csub_unit" ? "asc=csub_unit" : ""}${(asc=="csub_unit" || desc!="csub_unit" )? "desc=csub_unit" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	所属单位
			 	</a>
			 	${lz:set("checkeds[]","csubUnit")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csubOrderS || #request.defines['csubOrderS']!=null">
			 <td <s:if test="#request.defines['csubOrderS']>0">colspan="${defines["csubOrderS"]}" ${all?"width":"iwidth"}="${defines["csubOrderS"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="7" ref="csubOrderS"   title="订单个数">
			 	<a class="${desc=="csub_order_s" ? "desc" : ""}${asc=="csub_order_s" ? "asc" : ""}" href="?${desc=="csub_order_s" ? "asc=csub_order_s" : ""}${(asc=="csub_order_s" || desc!="csub_order_s" )? "desc=csub_order_s" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单个数
			 	</a>
			 	${lz:set("checkeds[]","csubOrderS")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csubSpecialS || #request.defines['csubSpecialS']!=null">
			 <td <s:if test="#request.defines['csubSpecialS']>0">colspan="${defines["csubSpecialS"]}" ${all?"width":"iwidth"}="${defines["csubSpecialS"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="csubSpecialS"   title="专车个数">
			 	<a class="${desc=="csub_special_s" ? "desc" : ""}${asc=="csub_special_s" ? "asc" : ""}" href="?${desc=="csub_special_s" ? "asc=csub_special_s" : ""}${(asc=="csub_special_s" || desc!="csub_special_s" )? "desc=csub_special_s" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	专车个数
			 	</a>
			 	${lz:set("checkeds[]","csubSpecialS")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csubFeeOrder || #request.defines['csubFeeOrder']!=null">
			 <td <s:if test="#request.defines['csubFeeOrder']>0">colspan="${defines["csubFeeOrder"]}" ${all?"width":"iwidth"}="${defines["csubFeeOrder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="10" ref="csubFeeOrder"   title="订单费用">
			 	<a class="${desc=="csub_fee_order" ? "desc" : ""}${asc=="csub_fee_order" ? "asc" : ""}" href="?${desc=="csub_fee_order" ? "asc=csub_fee_order" : ""}${(asc=="csub_fee_order" || desc!="csub_fee_order" )? "desc=csub_fee_order" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单费用
			 	</a>
			 	${lz:set("checkeds[]","csubFeeOrder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csubFeeSpecial || #request.defines['csubFeeSpecial']!=null">
			 <td <s:if test="#request.defines['csubFeeSpecial']>0">colspan="${defines["csubFeeSpecial"]}" ${all?"width":"iwidth"}="${defines["csubFeeSpecial"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="11" ref="csubFeeSpecial"   title="专车费用">
			 	<a class="${desc=="csub_fee_special" ? "desc" : ""}${asc=="csub_fee_special" ? "asc" : ""}" href="?${desc=="csub_fee_special" ? "asc=csub_fee_special" : ""}${(asc=="csub_fee_special" || desc!="csub_fee_special" )? "desc=csub_fee_special" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	专车费用
			 	</a>
			 	${lz:set("checkeds[]","csubFeeSpecial")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csubNeedPay || #request.defines['csubNeedPay']!=null">
			 <td <s:if test="#request.defines['csubNeedPay']>0">colspan="${defines["csubNeedPay"]}" ${all?"width":"iwidth"}="${defines["csubNeedPay"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="12" ref="csubNeedPay"   title="应付金额">
			 	<a class="${desc=="csub_need_pay" ? "desc" : ""}${asc=="csub_need_pay" ? "asc" : ""}" href="?${desc=="csub_need_pay" ? "asc=csub_need_pay" : ""}${(asc=="csub_need_pay" || desc!="csub_need_pay" )? "desc=csub_need_pay" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	应付金额
			 	</a>
			 	${lz:set("checkeds[]","csubNeedPay")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csubRealPay || #request.defines['csubRealPay']!=null">
			 <td <s:if test="#request.defines['csubRealPay']>0">colspan="${defines["csubRealPay"]}" ${all?"width":"iwidth"}="${defines["csubRealPay"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="13" ref="csubRealPay"   title="已付金额">
			 	<a class="${desc=="csub_real_pay" ? "desc" : ""}${asc=="csub_real_pay" ? "asc" : ""}" href="?${desc=="csub_real_pay" ? "asc=csub_real_pay" : ""}${(asc=="csub_real_pay" || desc!="csub_real_pay" )? "desc=csub_real_pay" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	已付金额
			 	</a>
			 	${lz:set("checkeds[]","csubRealPay")}
			 </td>
			 </s:if>
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csubIncome || #request.defines['csubIncome']!=null">
			 <td <s:if test="#request.defines['csubIncome']>0">colspan="${defines["csubIncome"]}" ${all?"width":"iwidth"}="${defines["csubIncome"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="13" ref="csubIncome"   title="收入金额">
			 	<a class="${desc=="csub_income" ? "desc" : ""}${asc=="csub_income" ? "asc" : ""}" href="?${desc=="csub_income" ? "asc=csub_income" : ""}${(asc=="csub_income" || desc!="csub_income" )? "desc=csub_income" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	收入金额
			 	</a>
			 	${lz:set("checkeds[]","csubIncome")}
			 </td>
			 </s:if>
			 
			 <s:if test="#request.defines==null && #request.CTRL.l.csubPayout || #request.defines['csubPayout']!=null">
			 <td <s:if test="#request.defines['csubPayout']>0">colspan="${defines["csubPayout"]}" ${all?"width":"iwidth"}="${defines["csubPayout"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="13" ref="csubPayout"   title="支出金额">
			 	<a class="${desc=="csub_payout" ? "desc" : ""}${asc=="csub_payout" ? "asc" : ""}" href="?${desc=="csub_payout" ? "asc=csub_payout" : ""}${(asc=="csub_payout" || desc!="csub_payout" )? "desc=csub_payout" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	支出金额
			 	</a>
			 	${lz:set("checkeds[]","csubPayout")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csubEditor || #request.defines['csubEditor']!=null">
			 <td <s:if test="#request.defines['csubEditor']>0">colspan="${defines["csubEditor"]}" ${all?"width":"iwidth"}="${defines["csubEditor"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="14" ref="csubEditor"   title="修改人">
			 	<a class="${desc=="csub_editor" ? "desc" : ""}${asc=="csub_editor" ? "asc" : ""}" href="?${desc=="csub_editor" ? "asc=csub_editor" : ""}${(asc=="csub_editor" || desc!="csub_editor" )? "desc=csub_editor" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改人
			 	</a>
			 	${lz:set("checkeds[]","csubEditor")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csubUpdateTime || #request.defines['csubUpdateTime']!=null">
			 <td <s:if test="#request.defines['csubUpdateTime']>0">colspan="${defines["csubUpdateTime"]}" ${all?"width":"iwidth"}="${defines["csubUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="15" ref="csubUpdateTime"   title="修改时间">
			 	<a class="${desc=="csub_update_time" ? "desc" : ""}${asc=="csub_update_time" ? "asc" : ""}" href="?${desc=="csub_update_time" ? "asc=csub_update_time" : ""}${(asc=="csub_update_time" || desc!="csub_update_time" )? "desc=csub_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 	${lz:set("checkeds[]","csubUpdateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csubAddTime || #request.defines['csubAddTime']!=null">
			 <td <s:if test="#request.defines['csubAddTime']>0">colspan="${defines["csubAddTime"]}" ${all?"width":"iwidth"}="${defines["csubAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="16" ref="csubAddTime"   title="添加时间">
			 	<a class="${desc=="csub_add_time" ? "desc" : ""}${asc=="csub_add_time" ? "asc" : ""}" href="?${desc=="csub_add_time" ? "asc=csub_add_time" : ""}${(asc=="csub_add_time" || desc!="csub_add_time" )? "desc=csub_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","csubAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csubSummary || #request.defines['csubSummary']!=null">
			 <td <s:if test="#request.defines['csubSummary']>0">colspan="${defines["csubSummary"]}" ${all?"width":"iwidth"}="${defines["csubSummary"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="17" ref="csubSummary"   title="财务备注">
			 	<a class="${desc=="csub_summary" ? "desc" : ""}${asc=="csub_summary" ? "asc" : ""}" href="?${desc=="csub_summary" ? "asc=csub_summary" : ""}${(asc=="csub_summary" || desc!="csub_summary" )? "desc=csub_summary" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	财务备注
			 	</a>
			 	${lz:set("checkeds[]","csubSummary")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csubStatus || #request.defines['csubStatus']!=null">
			 <td <s:if test="#request.defines['csubStatus']>0">colspan="${defines["csubStatus"]}" ${all?"width":"iwidth"}="${defines["csubStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="18" ref="csubStatus"   title="状态">
			 	<a class="${desc=="csub_status" ? "desc" : ""}${asc=="csub_status" ? "asc" : ""}" href="?${desc=="csub_status" ? "asc=csub_status" : ""}${(asc=="csub_status" || desc!="csub_status" )? "desc=csub_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","csubStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="19" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="20" ref="operation">
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
		  <tr id="${item.csubId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csubId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csubId || #request.defines['csubId']!=null">
				<s:if test="#request.defines['csubId']>0">
					${lz:set("注释","****csubId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csubId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsubId",lz:indexOf(fieldName,"csubId")>-1)}
				  		<s:if test="#request.atCsubId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csubId字段的字串格式化输出****")}
					<td ref="csubId" class="td ">
						 
						 	${lz:or(item$csubId[i.count-1],(lz:left(item.csubId$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csubHost || #request.defines['csubHost']!=null">
				<s:if test="#request.defines['csubHost']>0">
					${lz:set("注释","****csubHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csubHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsubHost",lz:indexOf(fieldName,"csubHost")>-1)}
				  		<s:if test="#request.atCsubHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csubHost字段的字串格式化输出****")}
					<td ref="csubHost" class="td " relate="${item.csubHost}">
						 
						 	<a <s:if test="#item.csubHost!=null && #item.csubHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.csubHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csubHost[i.count-1],(lz:left(item.csubHost$,100)))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csubMonth || #request.defines['csubMonth']!=null">
				<s:if test="#request.defines['csubMonth']>0">
					${lz:set("注释","****csubMonth关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csubMonth))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsubMonth",lz:indexOf(fieldName,"csubMonth")>-1)}
				  		<s:if test="#request.atCsubMonth==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csubMonth字段的字串格式化输出****")}
					<td ref="csubMonth" class="td  node">
						 
						 	${lz:or(item$csubMonth[i.count-1],(lz:left(item.csubMonth$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csubUnit || #request.defines['csubUnit']!=null">
				<s:if test="#request.defines['csubUnit']>0">
					${lz:set("注释","****csubUnit关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csubUnit))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsubUnit",lz:indexOf(fieldName,"csubUnit")>-1)}
				  		<s:if test="#request.atCsubUnit==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csubUnit字段的字串格式化输出****")}
					<td ref="csubUnit" class="td " relate="${item.csubUnit}">
						 
						 	<a <s:if test="#item.csubUnit!=null && #item.csubUnit!=''"> onclick="window.href('${basePath}${proname}/unit/info_details.do?key=${item.csubUnit}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csubUnit[i.count-1],(lz:left(item.csubUnit$,100)))}</a>
					</td>
				</s:else>
			</s:if>
			 
<!-- 			<s:if test="#request.defines==null && #request.CTRL.l.csubAirport || #request.defines['csubAirport']!=null"> -->
<!-- 				<s:if test="#request.defines['csubAirport']>0"> -->
<!-- 					${lz:set("注释","****csubAirport关联表的子级字段：如果用户勾选了要显示的话****")} -->
<!-- 				  	<s:iterator value="#request.childes" id="fieldName" status="j"> -->
<!-- 				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)} -->
<!-- 				 		<s:if test="#request.isList==true"> -->
<!-- 				 			${lz:set("sizeList",lz:size(item.csubAirport))} -->
<!-- 				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)} -->
<!-- 				 		</s:if> -->
<!-- 				  		${lz:set("atCsubAirport",lz:indexOf(fieldName,"csubAirport")>-1)} -->
<!-- 				  		<s:if test="#request.atCsubAirport==true"> -->
<!-- 				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td> -->
<!-- 				 		</s:if> -->
<!-- 				 	</s:iterator> -->
<!-- 				</s:if> -->
<!-- 				<s:else> -->
<!-- 					${lz:set("注释","****csubAirport字段的字串格式化输出****")} -->
<!-- 					<td ref="csubAirport" class="td " relate="${item.csubAirport}"> -->
						 
<!-- 						 	<a <s:if test="#item.csubAirport!=null && #item.csubAirport!=''"> onclick="window.href('${basePath}${proname}/unit/airport_details.do?key=${item.csubAirport}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>> -->
<!-- 						 	${lz:or(item$csubAirport[i.count-1],(lz:left(item.csubAirport$,100)))}</a> -->
<!-- 					</td> -->
<!-- 				</s:else> -->
<!-- 			</s:if> -->
			 
<!-- 			<s:if test="#request.defines==null && #request.CTRL.l.csubAirS || #request.defines['csubAirS']!=null"> -->
<!-- 				<s:if test="#request.defines['csubAirS']>0"> -->
<!-- 					${lz:set("注释","****csubAirS关联表的子级字段：如果用户勾选了要显示的话****")} -->
<!-- 				  	<s:iterator value="#request.childes" id="fieldName" status="j"> -->
<!-- 				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)} -->
<!-- 				 		<s:if test="#request.isList==true"> -->
<!-- 				 			${lz:set("sizeList",lz:size(item.csubAirS))} -->
<!-- 				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)} -->
<!-- 				 		</s:if> -->
<!-- 				  		${lz:set("atCsubAirS",lz:indexOf(fieldName,"csubAirS")>-1)} -->
<!-- 				  		<s:if test="#request.atCsubAirS==true"> -->
<!-- 				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td> -->
<!-- 				 		</s:if> -->
<!-- 				 	</s:iterator> -->
<!-- 				</s:if> -->
<!-- 				<s:else> -->
<!-- 					${lz:set("注释","****csubAirS字段的字串格式化输出****")} -->
<!-- 					<td ref="csubAirS" class="td "> -->
						 
<!-- 						 	${lz:or(item$csubAirS[i.count-1],(lz:left(item.csubAirS$,100)))} -->
<!-- 					</td> -->
<!-- 				</s:else> -->
<!-- 			</s:if> -->
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csubOrderS || #request.defines['csubOrderS']!=null">
				<s:if test="#request.defines['csubOrderS']>0">
					${lz:set("注释","****csubOrderS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csubOrderS))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsubOrderS",lz:indexOf(fieldName,"csubOrderS")>-1)}
				  		<s:if test="#request.atCsubOrderS==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csubOrderS字段的字串格式化输出****")}
					<td ref="csubOrderS" class="td ">
						 
						 	${lz:or(item$csubOrderS[i.count-1],(lz:left(item.csubOrderS$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csubSpecialS || #request.defines['csubSpecialS']!=null">
				<s:if test="#request.defines['csubSpecialS']>0">
					${lz:set("注释","****csubSpecialS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csubSpecialS))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsubSpecialS",lz:indexOf(fieldName,"csubSpecialS")>-1)}
				  		<s:if test="#request.atCsubSpecialS==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csubSpecialS字段的字串格式化输出****")}
					<td ref="csubSpecialS" class="td ">
						 
						 	${lz:or(item$csubSpecialS[i.count-1],(lz:left(item.csubSpecialS$,100)))}
					</td>
				</s:else>
			</s:if>
			 
<!-- 			<s:if test="#request.defines==null && #request.CTRL.l.csubFeeAir || #request.defines['csubFeeAir']!=null"> -->
<!-- 				<s:if test="#request.defines['csubFeeAir']>0"> -->
<!-- 					${lz:set("注释","****csubFeeAir关联表的子级字段：如果用户勾选了要显示的话****")} -->
<!-- 				  	<s:iterator value="#request.childes" id="fieldName" status="j"> -->
<!-- 				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)} -->
<!-- 				 		<s:if test="#request.isList==true"> -->
<!-- 				 			${lz:set("sizeList",lz:size(item.csubFeeAir))} -->
<!-- 				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)} -->
<!-- 				 		</s:if> -->
<!-- 				  		${lz:set("atCsubFeeAir",lz:indexOf(fieldName,"csubFeeAir")>-1)} -->
<!-- 				  		<s:if test="#request.atCsubFeeAir==true"> -->
<!-- 				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td> -->
<!-- 				 		</s:if> -->
<!-- 				 	</s:iterator> -->
<!-- 				</s:if> -->
<!-- 				<s:else> -->
<!-- 					${lz:set("注释","****csubFeeAir字段的字串格式化输出****")} -->
<!-- 					<td ref="csubFeeAir" class="td "> -->
						 
<!-- 						 	${lz:or(item$csubFeeAir[i.count-1],(lz:left(item.csubFeeAir$,100)))} -->
<!-- 					</td> -->
<!-- 				</s:else> -->
<!-- 			</s:if> -->
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csubFeeOrder || #request.defines['csubFeeOrder']!=null">
				<s:if test="#request.defines['csubFeeOrder']>0">
					${lz:set("注释","****csubFeeOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csubFeeOrder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsubFeeOrder",lz:indexOf(fieldName,"csubFeeOrder")>-1)}
				  		<s:if test="#request.atCsubFeeOrder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csubFeeOrder字段的字串格式化输出****")}
					<td ref="csubFeeOrder" class="td ">
						 
						 	${lz:or(item$csubFeeOrder[i.count-1],(lz:left(item.csubFeeOrder$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csubFeeSpecial || #request.defines['csubFeeSpecial']!=null">
				<s:if test="#request.defines['csubFeeSpecial']>0">
					${lz:set("注释","****csubFeeSpecial关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csubFeeSpecial))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsubFeeSpecial",lz:indexOf(fieldName,"csubFeeSpecial")>-1)}
				  		<s:if test="#request.atCsubFeeSpecial==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csubFeeSpecial字段的字串格式化输出****")}
					<td ref="csubFeeSpecial" class="td ">
						 
						 	${lz:or(item$csubFeeSpecial[i.count-1],(lz:left(item.csubFeeSpecial$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csubNeedPay || #request.defines['csubNeedPay']!=null">
				<s:if test="#request.defines['csubNeedPay']>0">
					${lz:set("注释","****csubNeedPay关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csubNeedPay))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsubNeedPay",lz:indexOf(fieldName,"csubNeedPay")>-1)}
				  		<s:if test="#request.atCsubNeedPay==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csubNeedPay字段的字串格式化输出****")}
					<td ref="csubNeedPay" class="td ">
						 
						 	${lz:or(item$csubNeedPay[i.count-1],(lz:left(item.csubNeedPay$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csubRealPay || #request.defines['csubRealPay']!=null">
				<s:if test="#request.defines['csubRealPay']>0">
					${lz:set("注释","****csubRealPay关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csubRealPay))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsubRealPay",lz:indexOf(fieldName,"csubRealPay")>-1)}
				  		<s:if test="#request.atCsubRealPay==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csubRealPay字段的字串格式化输出****")}
					<td ref="csubRealPay" class="td ">
						 
						 	${lz:or(item$csubRealPay[i.count-1],(lz:left(item.csubRealPay$,100)))}
					</td>
				</s:else>
			</s:if>
			
			<s:if test="#request.defines==null && #request.CTRL.l.csubIncome || #request.defines['csubIncome']!=null">
				<s:if test="#request.defines['csubIncome']>0">
					${lz:set("注释","****csubIncome关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csubIncome))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsubIncome",lz:indexOf(fieldName,"csubIncome")>-1)}
				  		<s:if test="#request.atCsubIncome==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csubRealPay字段的字串格式化输出****")}
					<td ref="csubIncome" class="td ">
						 	${lz:or(item$csubIncome[i.count-1],(lz:left(item.csubIncome$,100)))}
					</td>
				</s:else>
			</s:if>
			
			<s:if test="#request.defines==null && #request.CTRL.l.csubPayout || #request.defines['csubPayout']!=null">
				<s:if test="#request.defines['csubPayout']>0">
					${lz:set("注释","****csubPayout关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csubPayout))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsubPayout",lz:indexOf(fieldName,"csubPayout")>-1)}
				  		<s:if test="#request.atCsubPayout==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csubPayout字段的字串格式化输出****")}
					<td ref="csubPayout" class="td ">
						 	${lz:or(item$csubPayout[i.count-1],(lz:left(item.csubPayout$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csubEditor || #request.defines['csubEditor']!=null">
				<s:if test="#request.defines['csubEditor']>0">
					${lz:set("注释","****csubEditor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csubEditor))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsubEditor",lz:indexOf(fieldName,"csubEditor")>-1)}
				  		<s:if test="#request.atCsubEditor==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csubEditor字段的字串格式化输出****")}
					<td ref="csubEditor" class="td " relate="${item.csubEditor}">
						 
						 	<a <s:if test="#item.csubEditor!=null && #item.csubEditor!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.csubEditor}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csubEditor[i.count-1],(lz:left(item.csubEditor$,100)))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csubUpdateTime || #request.defines['csubUpdateTime']!=null">
				<s:if test="#request.defines['csubUpdateTime']>0">
					${lz:set("注释","****csubUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csubUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsubUpdateTime",lz:indexOf(fieldName,"csubUpdateTime")>-1)}
				  		<s:if test="#request.atCsubUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csubUpdateTime字段的字串格式化输出****")}
					<td ref="csubUpdateTime" class="td ">
						 
						 	${lz:or(item$csubUpdateTime[i.count-1],(lz:left(item.csubUpdateTime$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csubAddTime || #request.defines['csubAddTime']!=null">
				<s:if test="#request.defines['csubAddTime']>0">
					${lz:set("注释","****csubAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csubAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsubAddTime",lz:indexOf(fieldName,"csubAddTime")>-1)}
				  		<s:if test="#request.atCsubAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csubAddTime字段的字串格式化输出****")}
					<td ref="csubAddTime" class="td ">
						 
						 	${lz:or(item$csubAddTime[i.count-1],(lz:left(item.csubAddTime$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csubSummary || #request.defines['csubSummary']!=null">
				<s:if test="#request.defines['csubSummary']>0">
					${lz:set("注释","****csubSummary关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csubSummary))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsubSummary",lz:indexOf(fieldName,"csubSummary")>-1)}
				  		<s:if test="#request.atCsubSummary==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csubSummary字段的字串格式化输出****")}
					<td ref="csubSummary" class="td ">
						 
						 	${lz:or(item$csubSummary[i.count-1],(lz:left(item.csubSummary$,100)))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csubStatus || #request.defines['csubStatus']!=null">
				<s:if test="#request.defines['csubStatus']>0">
					${lz:set("注释","****csubStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csubStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsubStatus",lz:indexOf(fieldName,"csubStatus")>-1)}
				  		<s:if test="#request.atCsubStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csubStatus字段的字串格式化输出****")}
					<td ref="csubStatus" class="td ">
						 
						 	${lz:or(item$csubStatus[i.count-1],(lz:left(item.csubStatus$,100)))}
					</td>
				</s:else>
			</s:if>
		   ${lz:set("注释","*****************数据列表数据部分结束*****************")}		  
		  
		   
		    ${lz:set("注释","****自定义的单行记录操作句柄[request中的handles(String[])变量]****")}
			<td class="options">
				  <s:if test="#request.alias==null">	
					<s:if test="#request.CTRL.canExp[0]==true">
					<a href="javascript:HandleSel('审核','${item.csubId}')">审核</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[1]==true">
					<a href="javascript:HandleSel('结算','${item.csubId}')">结算</a>
					</s:if>
				</s:if>
				${lz:set("items",lz:split(handles,","))}
				<s:iterator value="#request.items" id="handle" status="i">
					${lz:set("isMenu",(lz:indexOf(handle,"@")==0))}
					<s:if test="#request.isMenu!=true">
					<a href="javascript:HandleSel('${handle}','${item.csubId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectBill('${item.csubId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditBill('${item.csubId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelBill('${item.csubId}','${item.csubMonth}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsBill('${item.csubId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csubId || #request.defines['csubId']!=null">
				<s:if test="#request.defines['csubId']>0">
					${lz:set("注释","****csubId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsubId",lz:indexOf(fieldName,"csubId")>-1)}				  		
				  		<s:if test="#request.atCsubId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csubId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csubHost || #request.defines['csubHost']!=null">
				<s:if test="#request.defines['csubHost']>0">
					${lz:set("注释","****csubHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsubHost",lz:indexOf(fieldName,"csubHost")>-1)}				  		
				  		<s:if test="#request.atCsubHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csubHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csubMonth || #request.defines['csubMonth']!=null">
				<s:if test="#request.defines['csubMonth']>0">
					${lz:set("注释","****csubMonth关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsubMonth",lz:indexOf(fieldName,"csubMonth")>-1)}				  		
				  		<s:if test="#request.atCsubMonth==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csubMonth[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csubUnit || #request.defines['csubUnit']!=null">
				<s:if test="#request.defines['csubUnit']>0">
					${lz:set("注释","****csubUnit关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsubUnit",lz:indexOf(fieldName,"csubUnit")>-1)}				  		
				  		<s:if test="#request.atCsubUnit==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csubUnit[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csubAirport || #request.defines['csubAirport']!=null">
				<s:if test="#request.defines['csubAirport']>0">
					${lz:set("注释","****csubAirport关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsubAirport",lz:indexOf(fieldName,"csubAirport")>-1)}				  		
				  		<s:if test="#request.atCsubAirport==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csubAirport[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csubAirS || #request.defines['csubAirS']!=null">
				<s:if test="#request.defines['csubAirS']>0">
					${lz:set("注释","****csubAirS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsubAirS",lz:indexOf(fieldName,"csubAirS")>-1)}				  		
				  		<s:if test="#request.atCsubAirS==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csubAirS[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csubOrderS || #request.defines['csubOrderS']!=null">
				<s:if test="#request.defines['csubOrderS']>0">
					${lz:set("注释","****csubOrderS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsubOrderS",lz:indexOf(fieldName,"csubOrderS")>-1)}				  		
				  		<s:if test="#request.atCsubOrderS==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csubOrderS[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csubSpecialS || #request.defines['csubSpecialS']!=null">
				<s:if test="#request.defines['csubSpecialS']>0">
					${lz:set("注释","****csubSpecialS关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsubSpecialS",lz:indexOf(fieldName,"csubSpecialS")>-1)}				  		
				  		<s:if test="#request.atCsubSpecialS==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csubSpecialS[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csubFeeAir || #request.defines['csubFeeAir']!=null">
				<s:if test="#request.defines['csubFeeAir']>0">
					${lz:set("注释","****csubFeeAir关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsubFeeAir",lz:indexOf(fieldName,"csubFeeAir")>-1)}				  		
				  		<s:if test="#request.atCsubFeeAir==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csubFeeAir[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csubFeeOrder || #request.defines['csubFeeOrder']!=null">
				<s:if test="#request.defines['csubFeeOrder']>0">
					${lz:set("注释","****csubFeeOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsubFeeOrder",lz:indexOf(fieldName,"csubFeeOrder")>-1)}				  		
				  		<s:if test="#request.atCsubFeeOrder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csubFeeOrder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csubFeeSpecial || #request.defines['csubFeeSpecial']!=null">
				<s:if test="#request.defines['csubFeeSpecial']>0">
					${lz:set("注释","****csubFeeSpecial关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsubFeeSpecial",lz:indexOf(fieldName,"csubFeeSpecial")>-1)}				  		
				  		<s:if test="#request.atCsubFeeSpecial==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csubFeeSpecial[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csubNeedPay || #request.defines['csubNeedPay']!=null">
				<s:if test="#request.defines['csubNeedPay']>0">
					${lz:set("注释","****csubNeedPay关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsubNeedPay",lz:indexOf(fieldName,"csubNeedPay")>-1)}				  		
				  		<s:if test="#request.atCsubNeedPay==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csubNeedPay[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csubRealPay || #request.defines['csubRealPay']!=null">
				<s:if test="#request.defines['csubRealPay']>0">
					${lz:set("注释","****csubRealPay关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsubRealPay",lz:indexOf(fieldName,"csubRealPay")>-1)}				  		
				  		<s:if test="#request.atCsubRealPay==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csubRealPay[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csubEditor || #request.defines['csubEditor']!=null">
				<s:if test="#request.defines['csubEditor']>0">
					${lz:set("注释","****csubEditor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsubEditor",lz:indexOf(fieldName,"csubEditor")>-1)}				  		
				  		<s:if test="#request.atCsubEditor==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csubEditor[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csubUpdateTime || #request.defines['csubUpdateTime']!=null">
				<s:if test="#request.defines['csubUpdateTime']>0">
					${lz:set("注释","****csubUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsubUpdateTime",lz:indexOf(fieldName,"csubUpdateTime")>-1)}				  		
				  		<s:if test="#request.atCsubUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csubUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csubAddTime || #request.defines['csubAddTime']!=null">
				<s:if test="#request.defines['csubAddTime']>0">
					${lz:set("注释","****csubAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsubAddTime",lz:indexOf(fieldName,"csubAddTime")>-1)}				  		
				  		<s:if test="#request.atCsubAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csubAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csubSummary || #request.defines['csubSummary']!=null">
				<s:if test="#request.defines['csubSummary']>0">
					${lz:set("注释","****csubSummary关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsubSummary",lz:indexOf(fieldName,"csubSummary")>-1)}				  		
				  		<s:if test="#request.atCsubSummary==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csubSummary[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csubStatus || #request.defines['csubStatus']!=null">
				<s:if test="#request.defines['csubStatus']>0">
					${lz:set("注释","****csubStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsubStatus",lz:indexOf(fieldName,"csubStatus")>-1)}				  		
				  		<s:if test="#request.atCsubStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csubStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 选择企业对账
	**/
	function SelectBill(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改企业对账
	**/
	function EditBill(id){
		var url = "${basePath}${namespace}bill_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改企业对账'}":lz:json(ctrl)}};
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
		EditBill(checker.val());
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
				var url = "${basePath}${namespace}bill_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新企业对账",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}bill_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新企业对账",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改企业对账
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}bill_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新企业对账",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改企业对账任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}bill_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新企业对账",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}bill_edit.do";
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
			var url = "${basePath}${namespace}bill_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除企业对账
	**/
	function DelBill(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除企业对账["+flag+"]吗？</span>",
				title:"删除企业对账",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}bill_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选企业对账吗？</span>",
				title:"批量删除企业对账",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}bill_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示企业对账详情
	**/
	function DetailsBill(id)
	{
		var url = "${basePath}${namespace}bill_details.do";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"企业对账详情",editable:false,visible:true}};
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
		DetailsBill(checker.val());
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