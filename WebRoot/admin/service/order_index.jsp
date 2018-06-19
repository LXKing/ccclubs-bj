<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/service/order.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csOrder.csoId))}
<lz:DefaultCtrl>{
	<s:if test="#request.all==true">
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可查询")}
	queryables:"csoId,csoHost,csoPayMember,csoUseMember,csoFeeType,csoArea,csoOutlets,csoOutletsRet,csoModel,csoCar,csoProvid,csoMobile,csoStartTime,csoFinishTime,csoLongOrder,csoUseType,csoRetTime,csoDuration,csoCreditCard,csoCreditBill,csoInsureType,csoIncome,csoUseIndex,csoFlag,csoFrom,csoSrc,csoUpdateTime,csoAddTime,csoEditor,csoDeviceVesion,csoAlarm,csoState,csoStatus",
	${lz:set("注释","当用户选择显示全部字段时，哪些字段可显示在表格中")}
	listables:"csoId,csoHost,csoPayMember,csoUseMember,csoFeeType,csoArea,csoOutlets,csoOutletsRet,csoModel,csoCar,csoProvid,csoCarNumber,csoMobile,csoEvRfid,csoIsAdCar,csoStartTime,csoFinishTime,csoLongOrder,csoLongPrice,csoUseType,csoTakeTime,csoRetTime,csoDuration,csoFuel,csoElectric,csoCreditCard,csoCreditBill,csoInsureType,csoFreehour,csoCostDetails,gifts,records,violats,troubles,operrecords,csoRebate,csoMarginNeed,csoPredict,csoPayNeed,csoPayReal,csoPayMoney,csoPayCoupon,csoPayCoin,csoPayKilom,csoPayMileage,csoPayRent,csoPayInsure,csoPayTimeout,csoPayDriver,csoIncome,csoUseIndex,csoFlag,csoFrom,csoSrc,csoUpdateTime,csoAddTime,csoEndTime,csoSerial,csoCode,csoRemark,csoEditor,csoMask,csoDeviceVesion,csoAlarm,csoState,csoStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可查询")}
	queryables:"csoId,csoHost,csoUseMember,csoCar,csoStartTime,csoFinishTime,csoUseType,csoCreditBill,csoState,csoStatus,csoFlag",
	${lz:set("注释","当用户选择显示部分字段时，哪些字段可显示在表格中")}
	listables:"csoId,csoHost,csoUseMember,csoCar,csoStartTime,csoFinishTime,csoUseType,csoCreditBill,csoCode,csoState,csoStatus,csoFlag",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>系统订单管理</title>
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
<script>
<lz:JSEncode>
$(function(){
	$(".table thead td[title='预订车辆']").attr("width",85);
	$(".table thead td[title='订单开始时间']").attr("width",120);
	$(".table thead td[title='订单结束时间']").attr("width",120);
	$(".table thead td[title='信用卡帐单']").attr("width",90);
	$(".table thead td[title='城市']").attr("width",60);
	$(".table tbody td[ref='csoStatus']:contains('已预定')").css("color","#ff0000");
	$(".table tbody td[ref='csoStatus']:contains('使用中')").css("color","#0000ff");
	$(".table tbody td[ref='csoStatus']:contains('待处理')").css("color","#FF00ff");
	$(".table tbody td[ref='csoStatus']:contains('已作废')").css("text-decoration","line-through");
	$(".table tbody td[ref='csoStatus']:contains('未付款')").css("color","#00dddd");
	//$(".table tbody td[ref='csoStartTime']").css("text-align","rtl");
	var states = $(".table tbody td[ref='csoState']:contains('已发送')");
	states.each(function(){
		$(this).css("color","#ff0000");
		var strOrderStart = $(this).siblings("td[ref='csoStartTime']").text();
		var orderStart = $.dateFormat($.trim(strOrderStart),"yyyy-MM-dd HH:mm:ss");
		if(orderStart.getTime()<new Date().getTime()){ 
			$(this).css("color","#0000ff").css("font-weight","bold").css("font-size","14px");
			$(this).html($.trim($(this).text())+"！！");
		}
	});
	
	var states = $(".table tbody td[ref='csoState']:contains('已发送')");
	states.each(function(){
		$(this).css("color","#ff0000");
		var strOrderStart = $(this).siblings("td[ref='csoStartTime']").text();
		var orderStart = $.dateFormat($.trim(strOrderStart),"yyyy-MM-dd HH:mm:ss");
		if(orderStart.getTime()<new Date().getTime()){ 
			$(this).css("color","#0000ff").css("font-weight","bold").css("font-size","14px");
			$(this).html($.trim($(this).text())+"！！");
		}
	});	
});
</lz:JSEncode>
<s:if test="#session.ccclubs_login.suId==0">
//批量发短信
$(function(){
	var button = $("<a onclick=\"rollbackOrder()\" href=\"javascript:void(0)\" class=\"btn\">订单回滚</a>");
	button.appendTo(".toolbar");
});
function rollbackOrder(){
	$.getInput({callback:function(orderid){
		var array = orderid.split(",");
		var strHtml = "";
		for(var o in array)
			strHtml+=($.api("com.ccclubs.helper.NewYearRollback","rollback")(array[o]))+"<br/>";
		Alert(strHtml);
	}});
}
</s:if>

</script>

<s:iterator value="#request.page.result" id="item" status="i">
<lz:set name="item$csoCreditBill[]">
	${item.csoCreditCard==null?"<span style='color:#aaaaaa;'>[非信用订单]</span>":(item.csoCreditBill==null?(item.csoStatus==4?"<span style='color:#999999;'>(不生成账单)</span>":"<span style='color:#666666;'>(待生成账单)</span>"):item.csoCreditBill$)}
</lz:set>
<lz:set name="item$csoUseMember[]">
	<span title="${item.csoUseIndex==0?"首次用车":""}" style='${item.csoUseIndex==0?"color:red;font-weight:bold;":""}'>${item.csoUseMember$}</span>	
</lz:set>
</s:iterator>



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
	<s:if test="#request.alias==null">
		<s:if test="#request.CTRL.canStats==true">
	<a href="order_stats.do?${lz:querys("UTF-8","limitsid")}" class="linker">
		<img align="absmiddle" src="${basePath}admin/images/icons/Statistics.png"/>
		数据统计
	</a>
		</s:if>
	</s:if>
	<s:if test="#request.shortcuts!=null || #request.haveQuerys==true">
		<a class="toggler" href="javascript:void(0);"><span class="${window.shortcuts!="hide"?"on":""}"></span></a>
	</s:if>
	<span class="caption">${empty title?"系统订单管理":title}</span>
</div>
  	</s:if>


${before$form}
<s:if test="#request.CTRL.canQuery==true">
<div class="plate" style="${((hideQuery==true && window.imize!="maximize")||(hideQuery!=true && window.imize=="minimize")) ? "display:none;":""}">
	<form ref="CsOrder" class="query" id="queryForm" action="${basePath}${lz:replace(servletPath,"^/","")}?${lz:querys("UTF-8","page")}" method="post">
		${lz:set("haveQuery",false)}
		
		${lz:set("注释","****导入查询表单各字段****")}
		<%@include file="order.query.jsp"%>

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
			<button type="button" class="add" onclick="AddOrder();">
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
			<dl ref="com.ccclubs.model.CsOrder" class="display dropdowan" style="margin-left:30px;">
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
			<dl ref="com.ccclubs.model.CsOrder" class="batch dropdowan">
				<dt>
					<img align="absmiddle" width="20" src="${basePath}admin/images/icons/20088256421529677807.png"/>
					批量修改
				</dt>
				<dd>
					<table class="shadow" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>  <td class="l"></td>  <td class="c">
					<s:if test="#request.defines==null && #request.CTRL.l.csoLongOrder || #request.defines['csoLongOrder']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('longOrder')">是否长单</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.csoFlag || #request.defines['csoFlag']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('flag')">类型标识</a>
					</s:if>
					<s:if test="#request.defines==null && #request.CTRL.l.csoDeviceVesion || #request.defines['csoDeviceVesion']!=null">
						<a href="javascript:void(0);" onclick="UpdateSel('vesion')">下位机版本</a>
					</s:if>
					</td>  <td class="r"></td></tr><tr>  <td class="bl"></td>  <td class="b"></td>  <td class="br"></td></tr>
					</tbody></table
				</dd>
			</dl>
			</s:if>
			
			${after$toolbar}
			
		</div>
		${lz:set("注释","*****************数据列表开始*****************")}
		<table id="order_table" ref="CsOrder" class="table" width="100%" border="0" cellspacing="1" cellpadding="0">
		<thead class="state-header">
		  <tr>
		  	 <td rowspan="2" width="40" tdid="0" align="center">
			 	选择
			 </td>
		  	
		  ${lz:set("注释","****数据列表列头开始****")}
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoId || #request.defines['csoId']!=null">
			 <td <s:if test="#request.defines['csoId']>0">colspan="${defines["csoId"]}" ${all?"width":"iwidth"}="${defines["csoId"]*100}" </s:if><s:else>rowspan="2" width=72 ${all?"width=120":""} </s:else> tdid="1" ref="csoId" flagKey  title="订单编号">
			 	<a class="${desc=="cso_id" ? "desc" : ""}${asc=="cso_id" ? "asc" : ""}" href="?${desc=="cso_id" ? "asc=cso_id" : ""}${(asc=="cso_id" || desc!="cso_id" )? "desc=cso_id" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单编号
			 	</a>
			 	${lz:set("checkeds[]","csoId")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoHost || #request.defines['csoHost']!=null">
			 <td <s:if test="#request.defines['csoHost']>0">colspan="${defines["csoHost"]}" ${all?"width":"iwidth"}="${defines["csoHost"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="2" ref="csoHost"   title="城市">
			 	<a class="${desc=="cso_host" ? "desc" : ""}${asc=="cso_host" ? "asc" : ""}" href="?${desc=="cso_host" ? "asc=cso_host" : ""}${(asc=="cso_host" || desc!="cso_host" )? "desc=cso_host" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	城市
			 	</a>
			 	${lz:set("checkeds[]","csoHost")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoPayMember || #request.defines['csoPayMember']!=null">
			 <td <s:if test="#request.defines['csoPayMember']>0">colspan="${defines["csoPayMember"]}" ${all?"width":"iwidth"}="${defines["csoPayMember"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="3" ref="csoPayMember"   title="支付会员">
			 	<a class="${desc=="cso_pay_member" ? "desc" : ""}${asc=="cso_pay_member" ? "asc" : ""}" href="?${desc=="cso_pay_member" ? "asc=cso_pay_member" : ""}${(asc=="cso_pay_member" || desc!="cso_pay_member" )? "desc=cso_pay_member" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	支付会员
			 	</a>
			 	${lz:set("checkeds[]","csoPayMember")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoUseMember || #request.defines['csoUseMember']!=null">
			 <td <s:if test="#request.defines['csoUseMember']>0">colspan="${defines["csoUseMember"]}" ${all?"width":"iwidth"}="${defines["csoUseMember"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="4" ref="csoUseMember"   title="使用会员">
			 	<a class="${desc=="cso_use_member" ? "desc" : ""}${asc=="cso_use_member" ? "asc" : ""}" href="?${desc=="cso_use_member" ? "asc=cso_use_member" : ""}${(asc=="cso_use_member" || desc!="cso_use_member" )? "desc=cso_use_member" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	使用会员
			 	</a>
			 	${lz:set("checkeds[]","csoUseMember")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoFeeType || #request.defines['csoFeeType']!=null">
			 <td <s:if test="#request.defines['csoFeeType']>0">colspan="${defines["csoFeeType"]}" ${all?"width":"iwidth"}="${defines["csoFeeType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="5" ref="csoFeeType"   title="计费类型">
			 	<a class="${desc=="cso_fee_type" ? "desc" : ""}${asc=="cso_fee_type" ? "asc" : ""}" href="?${desc=="cso_fee_type" ? "asc=cso_fee_type" : ""}${(asc=="cso_fee_type" || desc!="cso_fee_type" )? "desc=cso_fee_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	计费类型
			 	</a>
			 	${lz:set("checkeds[]","csoFeeType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoArea || #request.defines['csoArea']!=null">
			 <td <s:if test="#request.defines['csoArea']>0">colspan="${defines["csoArea"]}" ${all?"width":"iwidth"}="${defines["csoArea"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="6" ref="csoArea"   title="网点区域">
			 	<a class="${desc=="cso_area" ? "desc" : ""}${asc=="cso_area" ? "asc" : ""}" href="?${desc=="cso_area" ? "asc=cso_area" : ""}${(asc=="cso_area" || desc!="cso_area" )? "desc=cso_area" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	网点区域
			 	</a>
			 	${lz:set("checkeds[]","csoArea")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoOutlets || #request.defines['csoOutlets']!=null">
			 <td <s:if test="#request.defines['csoOutlets']>0">colspan="${defines["csoOutlets"]}" ${all?"width":"iwidth"}="${defines["csoOutlets"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="7" ref="csoOutlets"   title="取车网点">
			 	<a class="${desc=="cso_outlets" ? "desc" : ""}${asc=="cso_outlets" ? "asc" : ""}" href="?${desc=="cso_outlets" ? "asc=cso_outlets" : ""}${(asc=="cso_outlets" || desc!="cso_outlets" )? "desc=cso_outlets" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	取车网点
			 	</a>
			 	${lz:set("checkeds[]","csoOutlets")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoOutletsRet || #request.defines['csoOutletsRet']!=null">
			 <td <s:if test="#request.defines['csoOutletsRet']>0">colspan="${defines["csoOutletsRet"]}" ${all?"width":"iwidth"}="${defines["csoOutletsRet"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="8" ref="csoOutletsRet"   title="还车网点">
			 	<a class="${desc=="cso_outlets_ret" ? "desc" : ""}${asc=="cso_outlets_ret" ? "asc" : ""}" href="?${desc=="cso_outlets_ret" ? "asc=cso_outlets_ret" : ""}${(asc=="cso_outlets_ret" || desc!="cso_outlets_ret" )? "desc=cso_outlets_ret" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	还车网点
			 	</a>
			 	${lz:set("checkeds[]","csoOutletsRet")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoModel || #request.defines['csoModel']!=null">
			 <td <s:if test="#request.defines['csoModel']>0">colspan="${defines["csoModel"]}" ${all?"width":"iwidth"}="${defines["csoModel"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="9" ref="csoModel"   title="预订车型">
			 	<a class="${desc=="cso_model" ? "desc" : ""}${asc=="cso_model" ? "asc" : ""}" href="?${desc=="cso_model" ? "asc=cso_model" : ""}${(asc=="cso_model" || desc!="cso_model" )? "desc=cso_model" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	预订车型
			 	</a>
			 	${lz:set("checkeds[]","csoModel")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoCar || #request.defines['csoCar']!=null">
			 <td <s:if test="#request.defines['csoCar']>0">colspan="${defines["csoCar"]}" ${all?"width":"iwidth"}="${defines["csoCar"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="10" ref="csoCar"   title="预订车辆">
			 	<a class="${desc=="cso_car" ? "desc" : ""}${asc=="cso_car" ? "asc" : ""}" href="?${desc=="cso_car" ? "asc=cso_car" : ""}${(asc=="cso_car" || desc!="cso_car" )? "desc=cso_car" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	预订车辆
			 	</a>
			 	${lz:set("checkeds[]","csoCar")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoProvid || #request.defines['csoProvid']!=null">
			 <td <s:if test="#request.defines['csoProvid']>0">colspan="${defines["csoProvid"]}" ${all?"width":"iwidth"}="${defines["csoProvid"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="11" ref="csoProvid"   title="车辆商家">
			 	<a class="${desc=="cso_provid" ? "desc" : ""}${asc=="cso_provid" ? "asc" : ""}" href="?${desc=="cso_provid" ? "asc=cso_provid" : ""}${(asc=="cso_provid" || desc!="cso_provid" )? "desc=cso_provid" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车辆商家
			 	</a>
			 	${lz:set("checkeds[]","csoProvid")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoCarNumber || #request.defines['csoCarNumber']!=null">
			 <td <s:if test="#request.defines['csoCarNumber']>0">colspan="${defines["csoCarNumber"]}" ${all?"width":"iwidth"}="${defines["csoCarNumber"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="12" ref="csoCarNumber"   title="车牌号码">
			 	<a class="${desc=="cso_car_number" ? "desc" : ""}${asc=="cso_car_number" ? "asc" : ""}" href="?${desc=="cso_car_number" ? "asc=cso_car_number" : ""}${(asc=="cso_car_number" || desc!="cso_car_number" )? "desc=cso_car_number" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	车牌号码
			 	</a>
			 	${lz:set("checkeds[]","csoCarNumber")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoMobile || #request.defines['csoMobile']!=null">
			 <td <s:if test="#request.defines['csoMobile']>0">colspan="${defines["csoMobile"]}" ${all?"width":"iwidth"}="${defines["csoMobile"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="13" ref="csoMobile"   title="手机号码">
			 	<a class="${desc=="cso_mobile" ? "desc" : ""}${asc=="cso_mobile" ? "asc" : ""}" href="?${desc=="cso_mobile" ? "asc=cso_mobile" : ""}${(asc=="cso_mobile" || desc!="cso_mobile" )? "desc=cso_mobile" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	手机号码
			 	</a>
			 	${lz:set("checkeds[]","csoMobile")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoEvRfid || #request.defines['csoEvRfid']!=null">
			 <td <s:if test="#request.defines['csoEvRfid']>0">colspan="${defines["csoEvRfid"]}" ${all?"width":"iwidth"}="${defines["csoEvRfid"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="14" ref="csoEvRfid"   title="EV序列号">
			 	<a class="${desc=="cso_ev_rfid" ? "desc" : ""}${asc=="cso_ev_rfid" ? "asc" : ""}" href="?${desc=="cso_ev_rfid" ? "asc=cso_ev_rfid" : ""}${(asc=="cso_ev_rfid" || desc!="cso_ev_rfid" )? "desc=cso_ev_rfid" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	EV序列号
			 	</a>
			 	${lz:set("checkeds[]","csoEvRfid")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoIsAdCar || #request.defines['csoIsAdCar']!=null">
			 <td <s:if test="#request.defines['csoIsAdCar']>0">colspan="${defines["csoIsAdCar"]}" ${all?"width":"iwidth"}="${defines["csoIsAdCar"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="15" ref="csoIsAdCar"   title="宣传车？">
			 	<a class="${desc=="cso_is_ad_car" ? "desc" : ""}${asc=="cso_is_ad_car" ? "asc" : ""}" href="?${desc=="cso_is_ad_car" ? "asc=cso_is_ad_car" : ""}${(asc=="cso_is_ad_car" || desc!="cso_is_ad_car" )? "desc=cso_is_ad_car" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	宣传车？
			 	</a>
			 	${lz:set("checkeds[]","csoIsAdCar")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoStartTime || #request.defines['csoStartTime']!=null">
			 <td <s:if test="#request.defines['csoStartTime']>0">colspan="${defines["csoStartTime"]}" ${all?"width":"iwidth"}="${defines["csoStartTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="16" ref="csoStartTime"   title="订单开始时间">
			 	<a class="${desc=="cso_start_time" ? "desc" : ""}${asc=="cso_start_time" ? "asc" : ""}" href="?${desc=="cso_start_time" ? "asc=cso_start_time" : ""}${(asc=="cso_start_time" || desc!="cso_start_time" )? "desc=cso_start_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单开始时间
			 	</a>
			 	${lz:set("checkeds[]","csoStartTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoFinishTime || #request.defines['csoFinishTime']!=null">
			 <td <s:if test="#request.defines['csoFinishTime']>0">colspan="${defines["csoFinishTime"]}" ${all?"width":"iwidth"}="${defines["csoFinishTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="17" ref="csoFinishTime"   title="订单结束时间">
			 	<a class="${desc=="cso_finish_time" ? "desc" : ""}${asc=="cso_finish_time" ? "asc" : ""}" href="?${desc=="cso_finish_time" ? "asc=cso_finish_time" : ""}${(asc=="cso_finish_time" || desc!="cso_finish_time" )? "desc=cso_finish_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单结束时间
			 	</a>
			 	${lz:set("checkeds[]","csoFinishTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoLongOrder || #request.defines['csoLongOrder']!=null">
			 <td <s:if test="#request.defines['csoLongOrder']>0">colspan="${defines["csoLongOrder"]}" ${all?"width":"iwidth"}="${defines["csoLongOrder"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="18" ref="csoLongOrder"   title="是否长单">
			 	<a class="${desc=="cso_long_order" ? "desc" : ""}${asc=="cso_long_order" ? "asc" : ""}" href="?${desc=="cso_long_order" ? "asc=cso_long_order" : ""}${(asc=="cso_long_order" || desc!="cso_long_order" )? "desc=cso_long_order" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	是否长单
			 	</a>
			 	${lz:set("checkeds[]","csoLongOrder")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoLongPrice || #request.defines['csoLongPrice']!=null">
			 <td <s:if test="#request.defines['csoLongPrice']>0">colspan="${defines["csoLongPrice"]}" ${all?"width":"iwidth"}="${defines["csoLongPrice"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="19" ref="csoLongPrice"   title="长单价格(元)">
			 	<a class="${desc=="cso_long_price" ? "desc" : ""}${asc=="cso_long_price" ? "asc" : ""}" href="?${desc=="cso_long_price" ? "asc=cso_long_price" : ""}${(asc=="cso_long_price" || desc!="cso_long_price" )? "desc=cso_long_price" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	长单价格(元)
			 	</a>
			 	${lz:set("checkeds[]","csoLongPrice")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoUseType || #request.defines['csoUseType']!=null">
			 <td <s:if test="#request.defines['csoUseType']>0">colspan="${defines["csoUseType"]}" ${all?"width":"iwidth"}="${defines["csoUseType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="20" ref="csoUseType"   title="用车方式">
			 	<a class="${desc=="cso_use_type" ? "desc" : ""}${asc=="cso_use_type" ? "asc" : ""}" href="?${desc=="cso_use_type" ? "asc=cso_use_type" : ""}${(asc=="cso_use_type" || desc!="cso_use_type" )? "desc=cso_use_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	用车方式
			 	</a>
			 	${lz:set("checkeds[]","csoUseType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoTakeTime || #request.defines['csoTakeTime']!=null">
			 <td <s:if test="#request.defines['csoTakeTime']>0">colspan="${defines["csoTakeTime"]}" ${all?"width":"iwidth"}="${defines["csoTakeTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="21" ref="csoTakeTime"   title="实际取车时间">
			 	<a class="${desc=="cso_take_time" ? "desc" : ""}${asc=="cso_take_time" ? "asc" : ""}" href="?${desc=="cso_take_time" ? "asc=cso_take_time" : ""}${(asc=="cso_take_time" || desc!="cso_take_time" )? "desc=cso_take_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	实际取车时间
			 	</a>
			 	${lz:set("checkeds[]","csoTakeTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoRetTime || #request.defines['csoRetTime']!=null">
			 <td <s:if test="#request.defines['csoRetTime']>0">colspan="${defines["csoRetTime"]}" ${all?"width":"iwidth"}="${defines["csoRetTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="22" ref="csoRetTime"   title="实际还车时间">
			 	<a class="${desc=="cso_ret_time" ? "desc" : ""}${asc=="cso_ret_time" ? "asc" : ""}" href="?${desc=="cso_ret_time" ? "asc=cso_ret_time" : ""}${(asc=="cso_ret_time" || desc!="cso_ret_time" )? "desc=cso_ret_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	实际还车时间
			 	</a>
			 	${lz:set("checkeds[]","csoRetTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoDuration || #request.defines['csoDuration']!=null">
			 <td <s:if test="#request.defines['csoDuration']>0">colspan="${defines["csoDuration"]}" ${all?"width":"iwidth"}="${defines["csoDuration"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="23" ref="csoDuration"   title="订单时长(小时)">
			 	<a class="${desc=="cso_duration" ? "desc" : ""}${asc=="cso_duration" ? "asc" : ""}" href="?${desc=="cso_duration" ? "asc=cso_duration" : ""}${(asc=="cso_duration" || desc!="cso_duration" )? "desc=cso_duration" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单时长(小时)
			 	</a>
			 	${lz:set("checkeds[]","csoDuration")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoFuel || #request.defines['csoFuel']!=null">
			 <td <s:if test="#request.defines['csoFuel']>0">colspan="${defines["csoFuel"]}" ${all?"width":"iwidth"}="${defines["csoFuel"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="24" ref="csoFuel"   title="燃油里程">
			 	<a class="${desc=="cso_fuel" ? "desc" : ""}${asc=="cso_fuel" ? "asc" : ""}" href="?${desc=="cso_fuel" ? "asc=cso_fuel" : ""}${(asc=="cso_fuel" || desc!="cso_fuel" )? "desc=cso_fuel" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	燃油里程
			 	</a>
			 	${lz:set("checkeds[]","csoFuel")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoElectric || #request.defines['csoElectric']!=null">
			 <td <s:if test="#request.defines['csoElectric']>0">colspan="${defines["csoElectric"]}" ${all?"width":"iwidth"}="${defines["csoElectric"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="25" ref="csoElectric"   title="电里程">
			 	<a class="${desc=="cso_electric" ? "desc" : ""}${asc=="cso_electric" ? "asc" : ""}" href="?${desc=="cso_electric" ? "asc=cso_electric" : ""}${(asc=="cso_electric" || desc!="cso_electric" )? "desc=cso_electric" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	电里程
			 	</a>
			 	${lz:set("checkeds[]","csoElectric")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoCreditCard || #request.defines['csoCreditCard']!=null">
			 <td <s:if test="#request.defines['csoCreditCard']>0">colspan="${defines["csoCreditCard"]}" ${all?"width":"iwidth"}="${defines["csoCreditCard"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="26" ref="csoCreditCard"   title="使用信用卡">
			 	<a class="${desc=="cso_credit_card" ? "desc" : ""}${asc=="cso_credit_card" ? "asc" : ""}" href="?${desc=="cso_credit_card" ? "asc=cso_credit_card" : ""}${(asc=="cso_credit_card" || desc!="cso_credit_card" )? "desc=cso_credit_card" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	使用信用卡
			 	</a>
			 	${lz:set("checkeds[]","csoCreditCard")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoCreditBill || #request.defines['csoCreditBill']!=null">
			 <td <s:if test="#request.defines['csoCreditBill']>0">colspan="${defines["csoCreditBill"]}" ${all?"width":"iwidth"}="${defines["csoCreditBill"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="27" ref="csoCreditBill"   title="信用卡帐单">
			 	<a class="${desc=="cso_credit_bill" ? "desc" : ""}${asc=="cso_credit_bill" ? "asc" : ""}" href="?${desc=="cso_credit_bill" ? "asc=cso_credit_bill" : ""}${(asc=="cso_credit_bill" || desc!="cso_credit_bill" )? "desc=cso_credit_bill" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	信用卡帐单
			 	</a>
			 	${lz:set("checkeds[]","csoCreditBill")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoInsureType || #request.defines['csoInsureType']!=null">
			 <td <s:if test="#request.defines['csoInsureType']>0">colspan="${defines["csoInsureType"]}" ${all?"width":"iwidth"}="${defines["csoInsureType"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="28" ref="csoInsureType"   title="保险类型">
			 	<a class="${desc=="cso_insure_type" ? "desc" : ""}${asc=="cso_insure_type" ? "asc" : ""}" href="?${desc=="cso_insure_type" ? "asc=cso_insure_type" : ""}${(asc=="cso_insure_type" || desc!="cso_insure_type" )? "desc=cso_insure_type" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	保险类型
			 	</a>
			 	${lz:set("checkeds[]","csoInsureType")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoFreehour || #request.defines['csoFreehour']!=null">
			 <td <s:if test="#request.defines['csoFreehour']>0">colspan="${defines["csoFreehour"]}" ${all?"width":"iwidth"}="${defines["csoFreehour"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="29" ref="csoFreehour"   title="免费小时">
			 	<a class="${desc=="cso_freehour" ? "desc" : ""}${asc=="cso_freehour" ? "asc" : ""}" href="?${desc=="cso_freehour" ? "asc=cso_freehour" : ""}${(asc=="cso_freehour" || desc!="cso_freehour" )? "desc=cso_freehour" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	免费小时
			 	</a>
			 	${lz:set("checkeds[]","csoFreehour")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoCostDetails || #request.defines['csoCostDetails']!=null">
			 <td <s:if test="#request.defines['csoCostDetails']>0">colspan="${defines["csoCostDetails"]}" ${all?"width":"iwidth"}="${defines["csoCostDetails"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="30" ref="csoCostDetails"   title="订单明细">
			 	<a class="${desc=="cso_cost_details" ? "desc" : ""}${asc=="cso_cost_details" ? "asc" : ""}" href="?${desc=="cso_cost_details" ? "asc=cso_cost_details" : ""}${(asc=="cso_cost_details" || desc!="cso_cost_details" )? "desc=cso_cost_details" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单明细
			 	</a>
			 	${lz:set("checkeds[]","csoCostDetails")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.gifts || #request.defines['gifts']!=null">
			 <td <s:if test="#request.defines['gifts']>0">colspan="${defines["gifts"]}" ${all?"width":"iwidth"}="${defines["gifts"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="31" ref="gifts"   title="免费优惠项">
			 	<a class="${desc=="gifts" ? "desc" : ""}${asc=="gifts" ? "asc" : ""}" href="?${desc=="gifts" ? "asc=gifts" : ""}${(asc=="gifts" || desc!="gifts" )? "desc=gifts" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	免费优惠项
			 	</a>
			 	${lz:set("checkeds[]","gifts")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.records || #request.defines['records']!=null">
			 <td <s:if test="#request.defines['records']>0">colspan="${defines["records"]}" ${all?"width":"iwidth"}="${defines["records"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="32" ref="records"   title="消费记录项">
			 	<a class="${desc=="records" ? "desc" : ""}${asc=="records" ? "asc" : ""}" href="?${desc=="records" ? "asc=records" : ""}${(asc=="records" || desc!="records" )? "desc=records" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	消费记录项
			 	</a>
			 	${lz:set("checkeds[]","records")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.violats || #request.defines['violats']!=null">
			 <td <s:if test="#request.defines['violats']>0">colspan="${defines["violats"]}" ${all?"width":"iwidth"}="${defines["violats"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="33" ref="violats"   title="相关违章">
			 	<a class="${desc=="violats" ? "desc" : ""}${asc=="violats" ? "asc" : ""}" href="?${desc=="violats" ? "asc=violats" : ""}${(asc=="violats" || desc!="violats" )? "desc=violats" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	相关违章
			 	</a>
			 	${lz:set("checkeds[]","violats")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.troubles || #request.defines['troubles']!=null">
			 <td <s:if test="#request.defines['troubles']>0">colspan="${defines["troubles"]}" ${all?"width":"iwidth"}="${defines["troubles"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="34" ref="troubles"   title="相关事故">
			 	<a class="${desc=="troubles" ? "desc" : ""}${asc=="troubles" ? "asc" : ""}" href="?${desc=="troubles" ? "asc=troubles" : ""}${(asc=="troubles" || desc!="troubles" )? "desc=troubles" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	相关事故
			 	</a>
			 	${lz:set("checkeds[]","troubles")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.operrecords || #request.defines['operrecords']!=null">
			 <td <s:if test="#request.defines['operrecords']>0">colspan="${defines["operrecords"]}" ${all?"width":"iwidth"}="${defines["operrecords"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="35" ref="operrecords"   title="收入记录">
			 	<a class="${desc=="operrecords" ? "desc" : ""}${asc=="operrecords" ? "asc" : ""}" href="?${desc=="operrecords" ? "asc=operrecords" : ""}${(asc=="operrecords" || desc!="operrecords" )? "desc=operrecords" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	收入记录
			 	</a>
			 	${lz:set("checkeds[]","operrecords")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoRebate || #request.defines['csoRebate']!=null">
			 <td <s:if test="#request.defines['csoRebate']>0">colspan="${defines["csoRebate"]}" ${all?"width":"iwidth"}="${defines["csoRebate"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="36" ref="csoRebate"   title="折扣">
			 	<a class="${desc=="cso_rebate" ? "desc" : ""}${asc=="cso_rebate" ? "asc" : ""}" href="?${desc=="cso_rebate" ? "asc=cso_rebate" : ""}${(asc=="cso_rebate" || desc!="cso_rebate" )? "desc=cso_rebate" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	折扣
			 	</a>
			 	${lz:set("checkeds[]","csoRebate")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoMarginNeed || #request.defines['csoMarginNeed']!=null">
			 <td <s:if test="#request.defines['csoMarginNeed']>0">colspan="${defines["csoMarginNeed"]}" ${all?"width":"iwidth"}="${defines["csoMarginNeed"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="37" ref="csoMarginNeed"   title="押金/保证金(元)">
			 	<a class="${desc=="cso_margin_need" ? "desc" : ""}${asc=="cso_margin_need" ? "asc" : ""}" href="?${desc=="cso_margin_need" ? "asc=cso_margin_need" : ""}${(asc=="cso_margin_need" || desc!="cso_margin_need" )? "desc=cso_margin_need" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	押金/保证金(元)
			 	</a>
			 	${lz:set("checkeds[]","csoMarginNeed")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoPredict || #request.defines['csoPredict']!=null">
			 <td <s:if test="#request.defines['csoPredict']>0">colspan="${defines["csoPredict"]}" ${all?"width":"iwidth"}="${defines["csoPredict"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="38" ref="csoPredict"   title="预计/里程费(元)">
			 	<a class="${desc=="cso_predict" ? "desc" : ""}${asc=="cso_predict" ? "asc" : ""}" href="?${desc=="cso_predict" ? "asc=cso_predict" : ""}${(asc=="cso_predict" || desc!="cso_predict" )? "desc=cso_predict" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	预计/里程费(元)
			 	</a>
			 	${lz:set("checkeds[]","csoPredict")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoPayNeed || #request.defines['csoPayNeed']!=null">
			 <td <s:if test="#request.defines['csoPayNeed']>0">colspan="${defines["csoPayNeed"]}" ${all?"width":"iwidth"}="${defines["csoPayNeed"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="39" ref="csoPayNeed"   title="应付金额(元)">
			 	<a class="${desc=="cso_pay_need" ? "desc" : ""}${asc=="cso_pay_need" ? "asc" : ""}" href="?${desc=="cso_pay_need" ? "asc=cso_pay_need" : ""}${(asc=="cso_pay_need" || desc!="cso_pay_need" )? "desc=cso_pay_need" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	应付金额(元)
			 	</a>
			 	${lz:set("checkeds[]","csoPayNeed")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoPayReal || #request.defines['csoPayReal']!=null">
			 <td <s:if test="#request.defines['csoPayReal']>0">colspan="${defines["csoPayReal"]}" ${all?"width":"iwidth"}="${defines["csoPayReal"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="40" ref="csoPayReal"   title="实付金额(元)">
			 	<a class="${desc=="cso_pay_real" ? "desc" : ""}${asc=="cso_pay_real" ? "asc" : ""}" href="?${desc=="cso_pay_real" ? "asc=cso_pay_real" : ""}${(asc=="cso_pay_real" || desc!="cso_pay_real" )? "desc=cso_pay_real" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	实付金额(元)
			 	</a>
			 	${lz:set("checkeds[]","csoPayReal")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoPayMoney || #request.defines['csoPayMoney']!=null">
			 <td <s:if test="#request.defines['csoPayMoney']>0">colspan="${defines["csoPayMoney"]}" ${all?"width":"iwidth"}="${defines["csoPayMoney"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="41" ref="csoPayMoney"   title="保证金支付(元)">
			 	<a class="${desc=="cso_pay_money" ? "desc" : ""}${asc=="cso_pay_money" ? "asc" : ""}" href="?${desc=="cso_pay_money" ? "asc=cso_pay_money" : ""}${(asc=="cso_pay_money" || desc!="cso_pay_money" )? "desc=cso_pay_money" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	保证金支付(元)
			 	</a>
			 	${lz:set("checkeds[]","csoPayMoney")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoPayCoupon || #request.defines['csoPayCoupon']!=null">
			 <td <s:if test="#request.defines['csoPayCoupon']>0">colspan="${defines["csoPayCoupon"]}" ${all?"width":"iwidth"}="${defines["csoPayCoupon"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="42" ref="csoPayCoupon"   title="现金券支付(元)">
			 	<a class="${desc=="cso_pay_coupon" ? "desc" : ""}${asc=="cso_pay_coupon" ? "asc" : ""}" href="?${desc=="cso_pay_coupon" ? "asc=cso_pay_coupon" : ""}${(asc=="cso_pay_coupon" || desc!="cso_pay_coupon" )? "desc=cso_pay_coupon" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	现金券支付(元)
			 	</a>
			 	${lz:set("checkeds[]","csoPayCoupon")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoPayCoin || #request.defines['csoPayCoin']!=null">
			 <td <s:if test="#request.defines['csoPayCoin']>0">colspan="${defines["csoPayCoin"]}" ${all?"width":"iwidth"}="${defines["csoPayCoin"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="43" ref="csoPayCoin"   title="红包支付(元)">
			 	<a class="${desc=="cso_pay_coin" ? "desc" : ""}${asc=="cso_pay_coin" ? "asc" : ""}" href="?${desc=="cso_pay_coin" ? "asc=cso_pay_coin" : ""}${(asc=="cso_pay_coin" || desc!="cso_pay_coin" )? "desc=cso_pay_coin" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	红包支付(元)
			 	</a>
			 	${lz:set("checkeds[]","csoPayCoin")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoPayKilom || #request.defines['csoPayKilom']!=null">
			 <td <s:if test="#request.defines['csoPayKilom']>0">colspan="${defines["csoPayKilom"]}" ${all?"width":"iwidth"}="${defines["csoPayKilom"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="44" ref="csoPayKilom"   title="电里程费(元)">
			 	<a class="${desc=="cso_pay_kilom" ? "desc" : ""}${asc=="cso_pay_kilom" ? "asc" : ""}" href="?${desc=="cso_pay_kilom" ? "asc=cso_pay_kilom" : ""}${(asc=="cso_pay_kilom" || desc!="cso_pay_kilom" )? "desc=cso_pay_kilom" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	电里程费(元)
			 	</a>
			 	${lz:set("checkeds[]","csoPayKilom")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoPayMileage || #request.defines['csoPayMileage']!=null">
			 <td <s:if test="#request.defines['csoPayMileage']>0">colspan="${defines["csoPayMileage"]}" ${all?"width":"iwidth"}="${defines["csoPayMileage"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="45" ref="csoPayMileage"   title="油里程费(元)">
			 	<a class="${desc=="cso_pay_mileage" ? "desc" : ""}${asc=="cso_pay_mileage" ? "asc" : ""}" href="?${desc=="cso_pay_mileage" ? "asc=cso_pay_mileage" : ""}${(asc=="cso_pay_mileage" || desc!="cso_pay_mileage" )? "desc=cso_pay_mileage" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	油里程费(元)
			 	</a>
			 	${lz:set("checkeds[]","csoPayMileage")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoPayRent || #request.defines['csoPayRent']!=null">
			 <td <s:if test="#request.defines['csoPayRent']>0">colspan="${defines["csoPayRent"]}" ${all?"width":"iwidth"}="${defines["csoPayRent"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="46" ref="csoPayRent"   title="租金费用(元)">
			 	<a class="${desc=="cso_pay_rent" ? "desc" : ""}${asc=="cso_pay_rent" ? "asc" : ""}" href="?${desc=="cso_pay_rent" ? "asc=cso_pay_rent" : ""}${(asc=="cso_pay_rent" || desc!="cso_pay_rent" )? "desc=cso_pay_rent" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	租金费用(元)
			 	</a>
			 	${lz:set("checkeds[]","csoPayRent")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoPayInsure || #request.defines['csoPayInsure']!=null">
			 <td <s:if test="#request.defines['csoPayInsure']>0">colspan="${defines["csoPayInsure"]}" ${all?"width":"iwidth"}="${defines["csoPayInsure"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="47" ref="csoPayInsure"   title="免责费用(元)">
			 	<a class="${desc=="cso_pay_insure" ? "desc" : ""}${asc=="cso_pay_insure" ? "asc" : ""}" href="?${desc=="cso_pay_insure" ? "asc=cso_pay_insure" : ""}${(asc=="cso_pay_insure" || desc!="cso_pay_insure" )? "desc=cso_pay_insure" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	免责费用(元)
			 	</a>
			 	${lz:set("checkeds[]","csoPayInsure")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoPayTimeout || #request.defines['csoPayTimeout']!=null">
			 <td <s:if test="#request.defines['csoPayTimeout']>0">colspan="${defines["csoPayTimeout"]}" ${all?"width":"iwidth"}="${defines["csoPayTimeout"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="48" ref="csoPayTimeout"   title="超时费用(元)">
			 	<a class="${desc=="cso_pay_timeout" ? "desc" : ""}${asc=="cso_pay_timeout" ? "asc" : ""}" href="?${desc=="cso_pay_timeout" ? "asc=cso_pay_timeout" : ""}${(asc=="cso_pay_timeout" || desc!="cso_pay_timeout" )? "desc=cso_pay_timeout" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	超时费用(元)
			 	</a>
			 	${lz:set("checkeds[]","csoPayTimeout")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoPayDriver || #request.defines['csoPayDriver']!=null">
			 <td <s:if test="#request.defines['csoPayDriver']>0">colspan="${defines["csoPayDriver"]}" ${all?"width":"iwidth"}="${defines["csoPayDriver"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="49" ref="csoPayDriver"   title="陪驾费用">
			 	<a class="${desc=="cso_pay_driver" ? "desc" : ""}${asc=="cso_pay_driver" ? "asc" : ""}" href="?${desc=="cso_pay_driver" ? "asc=cso_pay_driver" : ""}${(asc=="cso_pay_driver" || desc!="cso_pay_driver" )? "desc=cso_pay_driver" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	陪驾费用
			 	</a>
			 	${lz:set("checkeds[]","csoPayDriver")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoIncome || #request.defines['csoIncome']!=null">
			 <td <s:if test="#request.defines['csoIncome']>0">colspan="${defines["csoIncome"]}" ${all?"width":"iwidth"}="${defines["csoIncome"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="50" ref="csoIncome"   title="计入营收">
			 	<a class="${desc=="cso_income" ? "desc" : ""}${asc=="cso_income" ? "asc" : ""}" href="?${desc=="cso_income" ? "asc=cso_income" : ""}${(asc=="cso_income" || desc!="cso_income" )? "desc=cso_income" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	计入营收
			 	</a>
			 	${lz:set("checkeds[]","csoIncome")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoUseIndex || #request.defines['csoUseIndex']!=null">
			 <td <s:if test="#request.defines['csoUseIndex']>0">colspan="${defines["csoUseIndex"]}" ${all?"width":"iwidth"}="${defines["csoUseIndex"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="51" ref="csoUseIndex"   title="订单次数">
			 	<a class="${desc=="cso_use_index" ? "desc" : ""}${asc=="cso_use_index" ? "asc" : ""}" href="?${desc=="cso_use_index" ? "asc=cso_use_index" : ""}${(asc=="cso_use_index" || desc!="cso_use_index" )? "desc=cso_use_index" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单次数
			 	</a>
			 	${lz:set("checkeds[]","csoUseIndex")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoFlag || #request.defines['csoFlag']!=null">
			 <td <s:if test="#request.defines['csoFlag']>0">colspan="${defines["csoFlag"]}" ${all?"width":"iwidth"}="${defines["csoFlag"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="52" ref="csoFlag"   title="类型标识">
			 	<a class="${desc=="cso_flag" ? "desc" : ""}${asc=="cso_flag" ? "asc" : ""}" href="?${desc=="cso_flag" ? "asc=cso_flag" : ""}${(asc=="cso_flag" || desc!="cso_flag" )? "desc=cso_flag" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	类型标识
			 	</a>
			 	${lz:set("checkeds[]","csoFlag")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoFrom || #request.defines['csoFrom']!=null">
			 <td <s:if test="#request.defines['csoFrom']>0">colspan="${defines["csoFrom"]}" ${all?"width":"iwidth"}="${defines["csoFrom"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="53" ref="csoFrom"   title="订单来源">
			 	<a class="${desc=="cso_from" ? "desc" : ""}${asc=="cso_from" ? "asc" : ""}" href="?${desc=="cso_from" ? "asc=cso_from" : ""}${(asc=="cso_from" || desc!="cso_from" )? "desc=cso_from" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单来源
			 	</a>
			 	${lz:set("checkeds[]","csoFrom")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoSrc || #request.defines['csoSrc']!=null">
			 <td <s:if test="#request.defines['csoSrc']>0">colspan="${defines["csoSrc"]}" ${all?"width":"iwidth"}="${defines["csoSrc"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="54" ref="csoSrc"   title="来源信息">
			 	<a class="${desc=="cso_src" ? "desc" : ""}${asc=="cso_src" ? "asc" : ""}" href="?${desc=="cso_src" ? "asc=cso_src" : ""}${(asc=="cso_src" || desc!="cso_src" )? "desc=cso_src" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	来源信息
			 	</a>
			 	${lz:set("checkeds[]","csoSrc")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoUpdateTime || #request.defines['csoUpdateTime']!=null">
			 <td <s:if test="#request.defines['csoUpdateTime']>0">colspan="${defines["csoUpdateTime"]}" ${all?"width":"iwidth"}="${defines["csoUpdateTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="55" ref="csoUpdateTime"   title="修改时间">
			 	<a class="${desc=="cso_update_time" ? "desc" : ""}${asc=="cso_update_time" ? "asc" : ""}" href="?${desc=="cso_update_time" ? "asc=cso_update_time" : ""}${(asc=="cso_update_time" || desc!="cso_update_time" )? "desc=cso_update_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	修改时间
			 	</a>
			 	${lz:set("checkeds[]","csoUpdateTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoAddTime || #request.defines['csoAddTime']!=null">
			 <td <s:if test="#request.defines['csoAddTime']>0">colspan="${defines["csoAddTime"]}" ${all?"width":"iwidth"}="${defines["csoAddTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="56" ref="csoAddTime"   title="添加时间">
			 	<a class="${desc=="cso_add_time" ? "desc" : ""}${asc=="cso_add_time" ? "asc" : ""}" href="?${desc=="cso_add_time" ? "asc=cso_add_time" : ""}${(asc=="cso_add_time" || desc!="cso_add_time" )? "desc=cso_add_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	添加时间
			 	</a>
			 	${lz:set("checkeds[]","csoAddTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoEndTime || #request.defines['csoEndTime']!=null">
			 <td <s:if test="#request.defines['csoEndTime']>0">colspan="${defines["csoEndTime"]}" ${all?"width":"iwidth"}="${defines["csoEndTime"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=140":""} </s:else> tdid="57" ref="csoEndTime"   title="结束时间">
			 	<a class="${desc=="cso_end_time" ? "desc" : ""}${asc=="cso_end_time" ? "asc" : ""}" href="?${desc=="cso_end_time" ? "asc=cso_end_time" : ""}${(asc=="cso_end_time" || desc!="cso_end_time" )? "desc=cso_end_time" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	结束时间
			 	</a>
			 	${lz:set("checkeds[]","csoEndTime")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoSerial || #request.defines['csoSerial']!=null">
			 <td <s:if test="#request.defines['csoSerial']>0">colspan="${defines["csoSerial"]}" ${all?"width":"iwidth"}="${defines["csoSerial"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="58" ref="csoSerial"   title="序列号">
			 	<a class="${desc=="cso_serial" ? "desc" : ""}${asc=="cso_serial" ? "asc" : ""}" href="?${desc=="cso_serial" ? "asc=cso_serial" : ""}${(asc=="cso_serial" || desc!="cso_serial" )? "desc=cso_serial" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	序列号
			 	</a>
			 	${lz:set("checkeds[]","csoSerial")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoCode || #request.defines['csoCode']!=null">
			 <td <s:if test="#request.defines['csoCode']>0">colspan="${defines["csoCode"]}" ${all?"width":"iwidth"}="${defines["csoCode"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="59" ref="csoCode"   title="授权码">
			 	<a class="${desc=="cso_code" ? "desc" : ""}${asc=="cso_code" ? "asc" : ""}" href="?${desc=="cso_code" ? "asc=cso_code" : ""}${(asc=="cso_code" || desc!="cso_code" )? "desc=cso_code" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	授权码
			 	</a>
			 	${lz:set("checkeds[]","csoCode")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoRemark || #request.defines['csoRemark']!=null">
			 <td <s:if test="#request.defines['csoRemark']>0">colspan="${defines["csoRemark"]}" ${all?"width":"iwidth"}="${defines["csoRemark"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=200":""} </s:else> tdid="60" ref="csoRemark"   title="订单备注">
			 	<a class="${desc=="cso_remark" ? "desc" : ""}${asc=="cso_remark" ? "asc" : ""}" href="?${desc=="cso_remark" ? "asc=cso_remark" : ""}${(asc=="cso_remark" || desc!="cso_remark" )? "desc=cso_remark" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	订单备注
			 	</a>
			 	${lz:set("checkeds[]","csoRemark")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoEditor || #request.defines['csoEditor']!=null">
			 <td <s:if test="#request.defines['csoEditor']>0">colspan="${defines["csoEditor"]}" ${all?"width":"iwidth"}="${defines["csoEditor"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="61" ref="csoEditor"   title="操作人">
			 	<a class="${desc=="cso_editor" ? "desc" : ""}${asc=="cso_editor" ? "asc" : ""}" href="?${desc=="cso_editor" ? "asc=cso_editor" : ""}${(asc=="cso_editor" || desc!="cso_editor" )? "desc=cso_editor" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	操作人
			 	</a>
			 	${lz:set("checkeds[]","csoEditor")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoMask || #request.defines['csoMask']!=null">
			 <td <s:if test="#request.defines['csoMask']>0">colspan="${defines["csoMask"]}" ${all?"width":"iwidth"}="${defines["csoMask"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="62" ref="csoMask"   title="程序掩码">
			 	<a class="${desc=="cso_mask" ? "desc" : ""}${asc=="cso_mask" ? "asc" : ""}" href="?${desc=="cso_mask" ? "asc=cso_mask" : ""}${(asc=="cso_mask" || desc!="cso_mask" )? "desc=cso_mask" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	程序掩码
			 	</a>
			 	${lz:set("checkeds[]","csoMask")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoDeviceVesion || #request.defines['csoDeviceVesion']!=null">
			 <td <s:if test="#request.defines['csoDeviceVesion']>0">colspan="${defines["csoDeviceVesion"]}" ${all?"width":"iwidth"}="${defines["csoDeviceVesion"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="63" ref="csoDeviceVesion"   title="下位机版本">
			 	<a class="${desc=="cso_device_vesion" ? "desc" : ""}${asc=="cso_device_vesion" ? "asc" : ""}" href="?${desc=="cso_device_vesion" ? "asc=cso_device_vesion" : ""}${(asc=="cso_device_vesion" || desc!="cso_device_vesion" )? "desc=cso_device_vesion" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	下位机版本
			 	</a>
			 	${lz:set("checkeds[]","csoDeviceVesion")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoAlarm || #request.defines['csoAlarm']!=null">
			 <td <s:if test="#request.defines['csoAlarm']>0">colspan="${defines["csoAlarm"]}" ${all?"width":"iwidth"}="${defines["csoAlarm"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="64" ref="csoAlarm"   title="报警">
			 	<a class="${desc=="cso_alarm" ? "desc" : ""}${asc=="cso_alarm" ? "asc" : ""}" href="?${desc=="cso_alarm" ? "asc=cso_alarm" : ""}${(asc=="cso_alarm" || desc!="cso_alarm" )? "desc=cso_alarm" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	报警
			 	</a>
			 	${lz:set("checkeds[]","csoAlarm")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoState || #request.defines['csoState']!=null">
			 <td <s:if test="#request.defines['csoState']>0">colspan="${defines["csoState"]}" ${all?"width":"iwidth"}="${defines["csoState"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="65" ref="csoState"   title="数据状态">
			 	<a class="${desc=="cso_state" ? "desc" : ""}${asc=="cso_state" ? "asc" : ""}" href="?${desc=="cso_state" ? "asc=cso_state" : ""}${(asc=="cso_state" || desc!="cso_state" )? "desc=cso_state" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	数据状态
			 	</a>
			 	${lz:set("checkeds[]","csoState")}
			 </td>
			 </s:if>
				
			 <s:if test="#request.defines==null && #request.CTRL.l.csoStatus || #request.defines['csoStatus']!=null">
			 <td <s:if test="#request.defines['csoStatus']>0">colspan="${defines["csoStatus"]}" ${all?"width":"iwidth"}="${defines["csoStatus"]*100}" </s:if><s:else>rowspan="2"  ${all?"width=120":""} </s:else> tdid="66" ref="csoStatus"   title="状态">
			 	<a class="${desc=="cso_status" ? "desc" : ""}${asc=="cso_status" ? "asc" : ""}" href="?${desc=="cso_status" ? "asc=cso_status" : ""}${(asc=="cso_status" || desc!="cso_status" )? "desc=cso_status" : ""}&${lz:queryss("UTF-8","desc","asc")}">
			 	状态
			 	</a>
			 	${lz:set("checkeds[]","csoStatus")}
			 </td>
			 </s:if>
		 
		 	${lz:set("注释","****数据列表列头最后两列****")}
			<td rowspan="2" width="60" tdid="67" class="options" ref="options">操作</td>
			<td rowspan="2" width="105" class="operation" tdid="68" ref="operation">
				<s:if test="#request.CTRL.canAdd==true">
					<a href="javascript:AddOrder()" style="text-decoration:underline">添加</a>
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
		  <tr id="${item.csoId}" >
		  	<td class="td-checkbox" align="center">
				<input class="check" type="checkbox" value="${item.csoId}"/>
			</td>
		  
		  	${lz:set("rowspan",0)}
		  
		    ${lz:set("注释","*****************数据列表数据部分开始*****************")}
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoId || #request.defines['csoId']!=null">
				<s:if test="#request.defines['csoId']>0">
					${lz:set("注释","****csoId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoId))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoId",lz:indexOf(fieldName,"csoId")>-1)}
				  		<s:if test="#request.atCsoId==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoId字段的字串格式化输出****")}
					<td ref="csoId" class="td  node">
						 
						 	${lz:or(item$csoId[i.count-1],lz:left(item.csoId$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoHost || #request.defines['csoHost']!=null">
				<s:if test="#request.defines['csoHost']>0">
					${lz:set("注释","****csoHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoHost))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoHost",lz:indexOf(fieldName,"csoHost")>-1)}
				  		<s:if test="#request.atCsoHost==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoHost字段的字串格式化输出****")}
					<td ref="csoHost" class="td " relate="${item.csoHost}">
						 
						 	<a <s:if test="#item.csoHost!=null && #item.csoHost!=''"> onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${item.csoHost}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csoHost[i.count-1],lz:left(item.csoHost$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoPayMember || #request.defines['csoPayMember']!=null">
				<s:if test="#request.defines['csoPayMember']>0">
					${lz:set("注释","****csoPayMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoPayMember))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoPayMember",lz:indexOf(fieldName,"csoPayMember")>-1)}
				  		<s:if test="#request.atCsoPayMember==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoPayMember字段的字串格式化输出****")}
					<td ref="csoPayMember" class="td " relate="${item.csoPayMember}">
						 
						 	<a <s:if test="#item.csoPayMember!=null && #item.csoPayMember!=''"> onclick="window.href('${basePath}${proname}/user/member_details.do?key=${item.csoPayMember}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csoPayMember[i.count-1],lz:left(item.csoPayMember$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoUseMember || #request.defines['csoUseMember']!=null">
				<s:if test="#request.defines['csoUseMember']>0">
					${lz:set("注释","****csoUseMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoUseMember))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoUseMember",lz:indexOf(fieldName,"csoUseMember")>-1)}
				  		<s:if test="#request.atCsoUseMember==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoUseMember字段的字串格式化输出****")}
					<td ref="csoUseMember" class="td " relate="${item.csoUseMember}">
						 
						 	<a <s:if test="#item.csoUseMember!=null && #item.csoUseMember!=''"> onclick="window.href('${basePath}${proname}/user/member_details.do?key=${item.csoUseMember}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csoUseMember[i.count-1],lz:left(item.csoUseMember$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoFeeType || #request.defines['csoFeeType']!=null">
				<s:if test="#request.defines['csoFeeType']>0">
					${lz:set("注释","****csoFeeType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoFeeType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoFeeType",lz:indexOf(fieldName,"csoFeeType")>-1)}
				  		<s:if test="#request.atCsoFeeType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoFeeType字段的字串格式化输出****")}
					<td ref="csoFeeType" class="td " relate="${item.csoFeeType}">
						 
						 	<a <s:if test="#item.csoFeeType!=null && #item.csoFeeType!=''"> onclick="window.href('${basePath}${proname}/user/type_details.do?key=${item.csoFeeType}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csoFeeType[i.count-1],lz:left(item.csoFeeType$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoArea || #request.defines['csoArea']!=null">
				<s:if test="#request.defines['csoArea']>0">
					${lz:set("注释","****csoArea关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoArea))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoArea",lz:indexOf(fieldName,"csoArea")>-1)}
				  		<s:if test="#request.atCsoArea==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoArea字段的字串格式化输出****")}
					<td ref="csoArea" class="td " relate="${item.csoArea}">
						 
						 	<a <s:if test="#item.csoArea!=null && #item.csoArea!=''"> onclick="window.href('${basePath}${proname}/configurator/area_details.do?key=${item.csoArea}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csoArea[i.count-1],lz:left(item.csoArea$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoOutlets || #request.defines['csoOutlets']!=null">
				<s:if test="#request.defines['csoOutlets']>0">
					${lz:set("注释","****csoOutlets关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoOutlets))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoOutlets",lz:indexOf(fieldName,"csoOutlets")>-1)}
				  		<s:if test="#request.atCsoOutlets==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoOutlets字段的字串格式化输出****")}
					<td ref="csoOutlets" class="td " relate="${item.csoOutlets}">
						 
						 	<a <s:if test="#item.csoOutlets!=null && #item.csoOutlets!=''"> onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${item.csoOutlets}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csoOutlets[i.count-1],lz:left(item.csoOutlets$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoOutletsRet || #request.defines['csoOutletsRet']!=null">
				<s:if test="#request.defines['csoOutletsRet']>0">
					${lz:set("注释","****csoOutletsRet关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoOutletsRet))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoOutletsRet",lz:indexOf(fieldName,"csoOutletsRet")>-1)}
				  		<s:if test="#request.atCsoOutletsRet==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoOutletsRet字段的字串格式化输出****")}
					<td ref="csoOutletsRet" class="td " relate="${item.csoOutletsRet}">
						 
						 	<a <s:if test="#item.csoOutletsRet!=null && #item.csoOutletsRet!=''"> onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${item.csoOutletsRet}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csoOutletsRet[i.count-1],lz:left(item.csoOutletsRet$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoModel || #request.defines['csoModel']!=null">
				<s:if test="#request.defines['csoModel']>0">
					${lz:set("注释","****csoModel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoModel))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoModel",lz:indexOf(fieldName,"csoModel")>-1)}
				  		<s:if test="#request.atCsoModel==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoModel字段的字串格式化输出****")}
					<td ref="csoModel" class="td " relate="${item.csoModel}">
						 
						 	<a <s:if test="#item.csoModel!=null && #item.csoModel!=''"> onclick="window.href('${basePath}${proname}/object/carmodel_details.do?key=${item.csoModel}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csoModel[i.count-1],lz:left(item.csoModel$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoCar || #request.defines['csoCar']!=null">
				<s:if test="#request.defines['csoCar']>0">
					${lz:set("注释","****csoCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoCar))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoCar",lz:indexOf(fieldName,"csoCar")>-1)}
				  		<s:if test="#request.atCsoCar==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoCar字段的字串格式化输出****")}
					<td ref="csoCar" class="td " relate="${item.csoCar}">
						 
						 	<a <s:if test="#item.csoCar!=null && #item.csoCar!=''"> onclick="window.href('${basePath}${proname}/object/car_details.do?key=${item.csoCar}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csoCar[i.count-1],lz:left(item.csoCar$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoProvid || #request.defines['csoProvid']!=null">
				<s:if test="#request.defines['csoProvid']>0">
					${lz:set("注释","****csoProvid关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoProvid))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoProvid",lz:indexOf(fieldName,"csoProvid")>-1)}
				  		<s:if test="#request.atCsoProvid==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoProvid字段的字串格式化输出****")}
					<td ref="csoProvid" class="td " relate="${item.csoProvid}">
						 
						 	<a <s:if test="#item.csoProvid!=null && #item.csoProvid!=''"> onclick="window.href('${basePath}${proname}/configurator/provid_details.do?key=${item.csoProvid}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csoProvid[i.count-1],lz:left(item.csoProvid$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoCarNumber || #request.defines['csoCarNumber']!=null">
				<s:if test="#request.defines['csoCarNumber']>0">
					${lz:set("注释","****csoCarNumber关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoCarNumber))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoCarNumber",lz:indexOf(fieldName,"csoCarNumber")>-1)}
				  		<s:if test="#request.atCsoCarNumber==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoCarNumber字段的字串格式化输出****")}
					<td ref="csoCarNumber" class="td ">
						 
						 	${lz:or(item$csoCarNumber[i.count-1],lz:left(item.csoCarNumber$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoMobile || #request.defines['csoMobile']!=null">
				<s:if test="#request.defines['csoMobile']>0">
					${lz:set("注释","****csoMobile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoMobile))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoMobile",lz:indexOf(fieldName,"csoMobile")>-1)}
				  		<s:if test="#request.atCsoMobile==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoMobile字段的字串格式化输出****")}
					<td ref="csoMobile" class="td ">
						 
						 	${lz:or(item$csoMobile[i.count-1],lz:left(item.csoMobile$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoEvRfid || #request.defines['csoEvRfid']!=null">
				<s:if test="#request.defines['csoEvRfid']>0">
					${lz:set("注释","****csoEvRfid关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoEvRfid))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoEvRfid",lz:indexOf(fieldName,"csoEvRfid")>-1)}
				  		<s:if test="#request.atCsoEvRfid==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoEvRfid字段的字串格式化输出****")}
					<td ref="csoEvRfid" class="td ">
						 
						 	${lz:or(item$csoEvRfid[i.count-1],lz:left(item.csoEvRfid$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoIsAdCar || #request.defines['csoIsAdCar']!=null">
				<s:if test="#request.defines['csoIsAdCar']>0">
					${lz:set("注释","****csoIsAdCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoIsAdCar))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoIsAdCar",lz:indexOf(fieldName,"csoIsAdCar")>-1)}
				  		<s:if test="#request.atCsoIsAdCar==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoIsAdCar字段的字串格式化输出****")}
					<td ref="csoIsAdCar" class="td ">
						 
						 	${lz:or(item$csoIsAdCar[i.count-1],lz:left(item.csoIsAdCar$,100))}
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
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoFinishTime || #request.defines['csoFinishTime']!=null">
				<s:if test="#request.defines['csoFinishTime']>0">
					${lz:set("注释","****csoFinishTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoFinishTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoFinishTime",lz:indexOf(fieldName,"csoFinishTime")>-1)}
				  		<s:if test="#request.atCsoFinishTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoFinishTime字段的字串格式化输出****")}
					<td ref="csoFinishTime" class="td ">
						 
						 	${lz:or(item$csoFinishTime[i.count-1],lz:left(item.csoFinishTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoLongOrder || #request.defines['csoLongOrder']!=null">
				<s:if test="#request.defines['csoLongOrder']>0">
					${lz:set("注释","****csoLongOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoLongOrder))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoLongOrder",lz:indexOf(fieldName,"csoLongOrder")>-1)}
				  		<s:if test="#request.atCsoLongOrder==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoLongOrder字段的字串格式化输出****")}
					<td ref="csoLongOrder" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csoId}','longOrder')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csoLongOrder[i.count-1],lz:left(item.csoLongOrder$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoLongPrice || #request.defines['csoLongPrice']!=null">
				<s:if test="#request.defines['csoLongPrice']>0">
					${lz:set("注释","****csoLongPrice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoLongPrice))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoLongPrice",lz:indexOf(fieldName,"csoLongPrice")>-1)}
				  		<s:if test="#request.atCsoLongPrice==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoLongPrice字段的字串格式化输出****")}
					<td ref="csoLongPrice" class="td ">
						 
						 	${lz:or(item$csoLongPrice[i.count-1],lz:left(item.csoLongPrice$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoUseType || #request.defines['csoUseType']!=null">
				<s:if test="#request.defines['csoUseType']>0">
					${lz:set("注释","****csoUseType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoUseType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoUseType",lz:indexOf(fieldName,"csoUseType")>-1)}
				  		<s:if test="#request.atCsoUseType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoUseType字段的字串格式化输出****")}
					<td ref="csoUseType" class="td ">
						 
						 	${lz:or(item$csoUseType[i.count-1],lz:left(item.csoUseType$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoTakeTime || #request.defines['csoTakeTime']!=null">
				<s:if test="#request.defines['csoTakeTime']>0">
					${lz:set("注释","****csoTakeTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoTakeTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoTakeTime",lz:indexOf(fieldName,"csoTakeTime")>-1)}
				  		<s:if test="#request.atCsoTakeTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoTakeTime字段的字串格式化输出****")}
					<td ref="csoTakeTime" class="td ">
						 
						 	${lz:or(item$csoTakeTime[i.count-1],lz:left(item.csoTakeTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoRetTime || #request.defines['csoRetTime']!=null">
				<s:if test="#request.defines['csoRetTime']>0">
					${lz:set("注释","****csoRetTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoRetTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoRetTime",lz:indexOf(fieldName,"csoRetTime")>-1)}
				  		<s:if test="#request.atCsoRetTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoRetTime字段的字串格式化输出****")}
					<td ref="csoRetTime" class="td ">
						 
						 	${lz:or(item$csoRetTime[i.count-1],lz:left(item.csoRetTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoDuration || #request.defines['csoDuration']!=null">
				<s:if test="#request.defines['csoDuration']>0">
					${lz:set("注释","****csoDuration关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoDuration))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoDuration",lz:indexOf(fieldName,"csoDuration")>-1)}
				  		<s:if test="#request.atCsoDuration==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoDuration字段的字串格式化输出****")}
					<td ref="csoDuration" class="td ">
						 
						 	${lz:or(item$csoDuration[i.count-1],lz:left(item.csoDuration$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoFuel || #request.defines['csoFuel']!=null">
				<s:if test="#request.defines['csoFuel']>0">
					${lz:set("注释","****csoFuel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoFuel))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoFuel",lz:indexOf(fieldName,"csoFuel")>-1)}
				  		<s:if test="#request.atCsoFuel==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoFuel字段的字串格式化输出****")}
					<td ref="csoFuel" class="td ">
						 
						 	${lz:or(item$csoFuel[i.count-1],lz:left(item.csoFuel$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoElectric || #request.defines['csoElectric']!=null">
				<s:if test="#request.defines['csoElectric']>0">
					${lz:set("注释","****csoElectric关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoElectric))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoElectric",lz:indexOf(fieldName,"csoElectric")>-1)}
				  		<s:if test="#request.atCsoElectric==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoElectric字段的字串格式化输出****")}
					<td ref="csoElectric" class="td ">
						 
						 	${lz:or(item$csoElectric[i.count-1],lz:left(item.csoElectric$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoCreditCard || #request.defines['csoCreditCard']!=null">
				<s:if test="#request.defines['csoCreditCard']>0">
					${lz:set("注释","****csoCreditCard关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoCreditCard))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoCreditCard",lz:indexOf(fieldName,"csoCreditCard")>-1)}
				  		<s:if test="#request.atCsoCreditCard==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoCreditCard字段的字串格式化输出****")}
					<td ref="csoCreditCard" class="td " relate="${item.csoCreditCard}">
						 
						 	<a <s:if test="#item.csoCreditCard!=null && #item.csoCreditCard!=''"> onclick="window.href('${basePath}${proname}/user/credit/card_details.do?key=${item.csoCreditCard}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csoCreditCard[i.count-1],lz:left(item.csoCreditCard$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoCreditBill || #request.defines['csoCreditBill']!=null">
				<s:if test="#request.defines['csoCreditBill']>0">
					${lz:set("注释","****csoCreditBill关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoCreditBill))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoCreditBill",lz:indexOf(fieldName,"csoCreditBill")>-1)}
				  		<s:if test="#request.atCsoCreditBill==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoCreditBill字段的字串格式化输出****")}
					<td ref="csoCreditBill" class="td " relate="${item.csoCreditBill}">
						 
						 	<a <s:if test="#item.csoCreditBill!=null && #item.csoCreditBill!=''"> onclick="window.href('${basePath}${proname}/finance/credit/bill_details.do?key=${item.csoCreditBill}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csoCreditBill[i.count-1],lz:left(item.csoCreditBill$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoInsureType || #request.defines['csoInsureType']!=null">
				<s:if test="#request.defines['csoInsureType']>0">
					${lz:set("注释","****csoInsureType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoInsureType))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoInsureType",lz:indexOf(fieldName,"csoInsureType")>-1)}
				  		<s:if test="#request.atCsoInsureType==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoInsureType字段的字串格式化输出****")}
					<td ref="csoInsureType" class="td " relate="${item.csoInsureType}">
						 
						 	<a <s:if test="#item.csoInsureType!=null && #item.csoInsureType!=''"> onclick="window.href('${basePath}${proname}/fee/product_details.do?key=${item.csoInsureType}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csoInsureType[i.count-1],lz:left(item.csoInsureType$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoFreehour || #request.defines['csoFreehour']!=null">
				<s:if test="#request.defines['csoFreehour']>0">
					${lz:set("注释","****csoFreehour关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoFreehour))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoFreehour",lz:indexOf(fieldName,"csoFreehour")>-1)}
				  		<s:if test="#request.atCsoFreehour==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoFreehour字段的字串格式化输出****")}
					<td ref="csoFreehour" class="td ">
						 
						 	${lz:or(item$csoFreehour[i.count-1],lz:left(item.csoFreehour$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoCostDetails || #request.defines['csoCostDetails']!=null">
				<s:if test="#request.defines['csoCostDetails']>0">
					${lz:set("注释","****csoCostDetails关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoCostDetails))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoCostDetails",lz:indexOf(fieldName,"csoCostDetails")>-1)}
				  		<s:if test="#request.atCsoCostDetails==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoCostDetails字段的字串格式化输出****")}
					<td ref="csoCostDetails" class="td " relate="${item.csoCostDetails}">
						 
						 	<a <s:if test="#item.csoCostDetails!=null && #item.csoCostDetails!=''"> onclick="window.href('${basePath}${proname}/service/orderdetail_details.do?key=${item.csoCostDetails}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csoCostDetails[i.count-1],lz:left(item.csoCostDetails$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.gifts || #request.defines['gifts']!=null">
				<s:if test="#request.defines['gifts']>0">
					${lz:set("注释","****gifts关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.gifts))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atGifts",lz:indexOf(fieldName,"gifts")>-1)}
				  		<s:if test="#request.atGifts==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****gifts字段的字串格式化输出****")}
					<td ref="gifts" class="td " relate="${item.gifts}">
						 
						 	<a <s:if test="#item.gifts!=null && #item.gifts!=''"> onclick="window.href('${basePath}${proname}/user/userecord_details.do?key=${item.gifts}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$gifts[i.count-1],lz:left(item.gifts$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.records || #request.defines['records']!=null">
				<s:if test="#request.defines['records']>0">
					${lz:set("注释","****records关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.records))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atRecords",lz:indexOf(fieldName,"records")>-1)}
				  		<s:if test="#request.atRecords==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****records字段的字串格式化输出****")}
					<td ref="records" class="td " relate="${item.records}">
						 
						 	<a <s:if test="#item.records!=null && #item.records!=''"> onclick="window.href('${basePath}${proname}/finance/record_details.do?key=${item.records}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$records[i.count-1],lz:left(item.records$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.violats || #request.defines['violats']!=null">
				<s:if test="#request.defines['violats']>0">
					${lz:set("注释","****violats关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.violats))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atViolats",lz:indexOf(fieldName,"violats")>-1)}
				  		<s:if test="#request.atViolats==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****violats字段的字串格式化输出****")}
					<td ref="violats" class="td " relate="${item.violats}">
						 
						 	<a <s:if test="#item.violats!=null && #item.violats!=''"> onclick="window.href('${basePath}${proname}/service/violat_details.do?key=${item.violats}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$violats[i.count-1],lz:left(item.violats$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.troubles || #request.defines['troubles']!=null">
				<s:if test="#request.defines['troubles']>0">
					${lz:set("注释","****troubles关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.troubles))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atTroubles",lz:indexOf(fieldName,"troubles")>-1)}
				  		<s:if test="#request.atTroubles==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****troubles字段的字串格式化输出****")}
					<td ref="troubles" class="td " relate="${item.troubles}">
						 
						 	<a <s:if test="#item.troubles!=null && #item.troubles!=''"> onclick="window.href('${basePath}${proname}/service/trouble_details.do?key=${item.troubles}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$troubles[i.count-1],lz:left(item.troubles$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.operrecords || #request.defines['operrecords']!=null">
				<s:if test="#request.defines['operrecords']>0">
					${lz:set("注释","****operrecords关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.operrecords))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atOperrecords",lz:indexOf(fieldName,"operrecords")>-1)}
				  		<s:if test="#request.atOperrecords==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****operrecords字段的字串格式化输出****")}
					<td ref="operrecords" class="td " relate="${item.operrecords}">
						 
						 	<a <s:if test="#item.operrecords!=null && #item.operrecords!=''"> onclick="window.href('${basePath}${proname}/finance/operecord_details.do?key=${item.operrecords}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$operrecords[i.count-1],lz:left(item.operrecords$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoRebate || #request.defines['csoRebate']!=null">
				<s:if test="#request.defines['csoRebate']>0">
					${lz:set("注释","****csoRebate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoRebate))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoRebate",lz:indexOf(fieldName,"csoRebate")>-1)}
				  		<s:if test="#request.atCsoRebate==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoRebate字段的字串格式化输出****")}
					<td ref="csoRebate" class="td ">
						 
						 	${lz:or(item$csoRebate[i.count-1],lz:left(item.csoRebate$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoMarginNeed || #request.defines['csoMarginNeed']!=null">
				<s:if test="#request.defines['csoMarginNeed']>0">
					${lz:set("注释","****csoMarginNeed关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoMarginNeed))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoMarginNeed",lz:indexOf(fieldName,"csoMarginNeed")>-1)}
				  		<s:if test="#request.atCsoMarginNeed==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoMarginNeed字段的字串格式化输出****")}
					<td ref="csoMarginNeed" class="td ">
						 
						 	${lz:or(item$csoMarginNeed[i.count-1],lz:left(item.csoMarginNeed$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoPredict || #request.defines['csoPredict']!=null">
				<s:if test="#request.defines['csoPredict']>0">
					${lz:set("注释","****csoPredict关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoPredict))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoPredict",lz:indexOf(fieldName,"csoPredict")>-1)}
				  		<s:if test="#request.atCsoPredict==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoPredict字段的字串格式化输出****")}
					<td ref="csoPredict" class="td ">
						 
						 	${lz:or(item$csoPredict[i.count-1],lz:left(item.csoPredict$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoPayNeed || #request.defines['csoPayNeed']!=null">
				<s:if test="#request.defines['csoPayNeed']>0">
					${lz:set("注释","****csoPayNeed关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoPayNeed))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoPayNeed",lz:indexOf(fieldName,"csoPayNeed")>-1)}
				  		<s:if test="#request.atCsoPayNeed==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoPayNeed字段的字串格式化输出****")}
					<td ref="csoPayNeed" class="td ">
						 
						 	${lz:or(item$csoPayNeed[i.count-1],lz:left(item.csoPayNeed$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoPayReal || #request.defines['csoPayReal']!=null">
				<s:if test="#request.defines['csoPayReal']>0">
					${lz:set("注释","****csoPayReal关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoPayReal))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoPayReal",lz:indexOf(fieldName,"csoPayReal")>-1)}
				  		<s:if test="#request.atCsoPayReal==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoPayReal字段的字串格式化输出****")}
					<td ref="csoPayReal" class="td ">
						 
						 	${lz:or(item$csoPayReal[i.count-1],lz:left(item.csoPayReal$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoPayMoney || #request.defines['csoPayMoney']!=null">
				<s:if test="#request.defines['csoPayMoney']>0">
					${lz:set("注释","****csoPayMoney关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoPayMoney))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoPayMoney",lz:indexOf(fieldName,"csoPayMoney")>-1)}
				  		<s:if test="#request.atCsoPayMoney==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoPayMoney字段的字串格式化输出****")}
					<td ref="csoPayMoney" class="td ">
						 
						 	${lz:or(item$csoPayMoney[i.count-1],lz:left(item.csoPayMoney$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoPayCoupon || #request.defines['csoPayCoupon']!=null">
				<s:if test="#request.defines['csoPayCoupon']>0">
					${lz:set("注释","****csoPayCoupon关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoPayCoupon))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoPayCoupon",lz:indexOf(fieldName,"csoPayCoupon")>-1)}
				  		<s:if test="#request.atCsoPayCoupon==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoPayCoupon字段的字串格式化输出****")}
					<td ref="csoPayCoupon" class="td ">
						 
						 	${lz:or(item$csoPayCoupon[i.count-1],lz:left(item.csoPayCoupon$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoPayCoin || #request.defines['csoPayCoin']!=null">
				<s:if test="#request.defines['csoPayCoin']>0">
					${lz:set("注释","****csoPayCoin关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoPayCoin))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoPayCoin",lz:indexOf(fieldName,"csoPayCoin")>-1)}
				  		<s:if test="#request.atCsoPayCoin==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoPayCoin字段的字串格式化输出****")}
					<td ref="csoPayCoin" class="td ">
						 
						 	${lz:or(item$csoPayCoin[i.count-1],lz:left(item.csoPayCoin$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoPayKilom || #request.defines['csoPayKilom']!=null">
				<s:if test="#request.defines['csoPayKilom']>0">
					${lz:set("注释","****csoPayKilom关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoPayKilom))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoPayKilom",lz:indexOf(fieldName,"csoPayKilom")>-1)}
				  		<s:if test="#request.atCsoPayKilom==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoPayKilom字段的字串格式化输出****")}
					<td ref="csoPayKilom" class="td ">
						 
						 	${lz:or(item$csoPayKilom[i.count-1],lz:left(item.csoPayKilom$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoPayMileage || #request.defines['csoPayMileage']!=null">
				<s:if test="#request.defines['csoPayMileage']>0">
					${lz:set("注释","****csoPayMileage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoPayMileage))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoPayMileage",lz:indexOf(fieldName,"csoPayMileage")>-1)}
				  		<s:if test="#request.atCsoPayMileage==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoPayMileage字段的字串格式化输出****")}
					<td ref="csoPayMileage" class="td ">
						 
						 	${lz:or(item$csoPayMileage[i.count-1],lz:left(item.csoPayMileage$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoPayRent || #request.defines['csoPayRent']!=null">
				<s:if test="#request.defines['csoPayRent']>0">
					${lz:set("注释","****csoPayRent关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoPayRent))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoPayRent",lz:indexOf(fieldName,"csoPayRent")>-1)}
				  		<s:if test="#request.atCsoPayRent==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoPayRent字段的字串格式化输出****")}
					<td ref="csoPayRent" class="td ">
						 
						 	${lz:or(item$csoPayRent[i.count-1],lz:left(item.csoPayRent$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoPayInsure || #request.defines['csoPayInsure']!=null">
				<s:if test="#request.defines['csoPayInsure']>0">
					${lz:set("注释","****csoPayInsure关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoPayInsure))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoPayInsure",lz:indexOf(fieldName,"csoPayInsure")>-1)}
				  		<s:if test="#request.atCsoPayInsure==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoPayInsure字段的字串格式化输出****")}
					<td ref="csoPayInsure" class="td ">
						 
						 	${lz:or(item$csoPayInsure[i.count-1],lz:left(item.csoPayInsure$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoPayTimeout || #request.defines['csoPayTimeout']!=null">
				<s:if test="#request.defines['csoPayTimeout']>0">
					${lz:set("注释","****csoPayTimeout关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoPayTimeout))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoPayTimeout",lz:indexOf(fieldName,"csoPayTimeout")>-1)}
				  		<s:if test="#request.atCsoPayTimeout==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoPayTimeout字段的字串格式化输出****")}
					<td ref="csoPayTimeout" class="td ">
						 
						 	${lz:or(item$csoPayTimeout[i.count-1],lz:left(item.csoPayTimeout$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoPayDriver || #request.defines['csoPayDriver']!=null">
				<s:if test="#request.defines['csoPayDriver']>0">
					${lz:set("注释","****csoPayDriver关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoPayDriver))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoPayDriver",lz:indexOf(fieldName,"csoPayDriver")>-1)}
				  		<s:if test="#request.atCsoPayDriver==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoPayDriver字段的字串格式化输出****")}
					<td ref="csoPayDriver" class="td ">
						 
						 	${lz:or(item$csoPayDriver[i.count-1],lz:left(item.csoPayDriver$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoIncome || #request.defines['csoIncome']!=null">
				<s:if test="#request.defines['csoIncome']>0">
					${lz:set("注释","****csoIncome关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoIncome))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoIncome",lz:indexOf(fieldName,"csoIncome")>-1)}
				  		<s:if test="#request.atCsoIncome==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoIncome字段的字串格式化输出****")}
					<td ref="csoIncome" class="td ">
						 
						 	${lz:or(item$csoIncome[i.count-1],lz:left(item.csoIncome$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoUseIndex || #request.defines['csoUseIndex']!=null">
				<s:if test="#request.defines['csoUseIndex']>0">
					${lz:set("注释","****csoUseIndex关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoUseIndex))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoUseIndex",lz:indexOf(fieldName,"csoUseIndex")>-1)}
				  		<s:if test="#request.atCsoUseIndex==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoUseIndex字段的字串格式化输出****")}
					<td ref="csoUseIndex" class="td ">
						 
						 	${lz:or(item$csoUseIndex[i.count-1],lz:left(item.csoUseIndex$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoFlag || #request.defines['csoFlag']!=null">
				<s:if test="#request.defines['csoFlag']>0">
					${lz:set("注释","****csoFlag关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoFlag))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoFlag",lz:indexOf(fieldName,"csoFlag")>-1)}
				  		<s:if test="#request.atCsoFlag==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoFlag字段的字串格式化输出****")}
					<td ref="csoFlag" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csoId}','flag')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csoFlag[i.count-1],lz:left(item.csoFlag$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoFrom || #request.defines['csoFrom']!=null">
				<s:if test="#request.defines['csoFrom']>0">
					${lz:set("注释","****csoFrom关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoFrom))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoFrom",lz:indexOf(fieldName,"csoFrom")>-1)}
				  		<s:if test="#request.atCsoFrom==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoFrom字段的字串格式化输出****")}
					<td ref="csoFrom" class="td ">
						 
						 	${lz:or(item$csoFrom[i.count-1],lz:left(item.csoFrom$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoSrc || #request.defines['csoSrc']!=null">
				<s:if test="#request.defines['csoSrc']>0">
					${lz:set("注释","****csoSrc关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoSrc))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoSrc",lz:indexOf(fieldName,"csoSrc")>-1)}
				  		<s:if test="#request.atCsoSrc==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoSrc字段的字串格式化输出****")}
					<td ref="csoSrc" class="td ">
						 
						 	${lz:or(item$csoSrc[i.count-1],lz:left(item.csoSrc$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoUpdateTime || #request.defines['csoUpdateTime']!=null">
				<s:if test="#request.defines['csoUpdateTime']>0">
					${lz:set("注释","****csoUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoUpdateTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoUpdateTime",lz:indexOf(fieldName,"csoUpdateTime")>-1)}
				  		<s:if test="#request.atCsoUpdateTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoUpdateTime字段的字串格式化输出****")}
					<td ref="csoUpdateTime" class="td ">
						 
						 	${lz:or(item$csoUpdateTime[i.count-1],lz:left(item.csoUpdateTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoAddTime || #request.defines['csoAddTime']!=null">
				<s:if test="#request.defines['csoAddTime']>0">
					${lz:set("注释","****csoAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoAddTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoAddTime",lz:indexOf(fieldName,"csoAddTime")>-1)}
				  		<s:if test="#request.atCsoAddTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoAddTime字段的字串格式化输出****")}
					<td ref="csoAddTime" class="td ">
						 
						 	${lz:or(item$csoAddTime[i.count-1],lz:left(item.csoAddTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoEndTime || #request.defines['csoEndTime']!=null">
				<s:if test="#request.defines['csoEndTime']>0">
					${lz:set("注释","****csoEndTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoEndTime))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoEndTime",lz:indexOf(fieldName,"csoEndTime")>-1)}
				  		<s:if test="#request.atCsoEndTime==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoEndTime字段的字串格式化输出****")}
					<td ref="csoEndTime" class="td ">
						 
						 	${lz:or(item$csoEndTime[i.count-1],lz:left(item.csoEndTime$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoSerial || #request.defines['csoSerial']!=null">
				<s:if test="#request.defines['csoSerial']>0">
					${lz:set("注释","****csoSerial关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoSerial))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoSerial",lz:indexOf(fieldName,"csoSerial")>-1)}
				  		<s:if test="#request.atCsoSerial==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoSerial字段的字串格式化输出****")}
					<td ref="csoSerial" class="td ">
						 
						 	${lz:or(item$csoSerial[i.count-1],lz:left(item.csoSerial$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoCode || #request.defines['csoCode']!=null">
				<s:if test="#request.defines['csoCode']>0">
					${lz:set("注释","****csoCode关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoCode))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoCode",lz:indexOf(fieldName,"csoCode")>-1)}
				  		<s:if test="#request.atCsoCode==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoCode字段的字串格式化输出****")}
					<td ref="csoCode" class="td ">
						 
						 	${lz:or(item$csoCode[i.count-1],lz:left(item.csoCode$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoRemark || #request.defines['csoRemark']!=null">
				<s:if test="#request.defines['csoRemark']>0">
					${lz:set("注释","****csoRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoRemark))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoRemark",lz:indexOf(fieldName,"csoRemark")>-1)}
				  		<s:if test="#request.atCsoRemark==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoRemark字段的字串格式化输出****")}
					<td ref="csoRemark" class="td ">
						 
						 	${lz:or(item$csoRemark[i.count-1],lz:left(item.csoRemark$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoEditor || #request.defines['csoEditor']!=null">
				<s:if test="#request.defines['csoEditor']>0">
					${lz:set("注释","****csoEditor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoEditor))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoEditor",lz:indexOf(fieldName,"csoEditor")>-1)}
				  		<s:if test="#request.atCsoEditor==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoEditor字段的字串格式化输出****")}
					<td ref="csoEditor" class="td " relate="${item.csoEditor}">
						 
						 	<a <s:if test="#item.csoEditor!=null && #item.csoEditor!=''"> onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${item.csoEditor}',{ctrl:{editable:false,visible:true}})" href="javascript:void(0);"</s:if>>
						 	${lz:or(item$csoEditor[i.count-1],lz:left(item.csoEditor$,100))}</a>
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoMask || #request.defines['csoMask']!=null">
				<s:if test="#request.defines['csoMask']>0">
					${lz:set("注释","****csoMask关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoMask))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoMask",lz:indexOf(fieldName,"csoMask")>-1)}
				  		<s:if test="#request.atCsoMask==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoMask字段的字串格式化输出****")}
					<td ref="csoMask" class="td ">
						 
						 	${lz:or(item$csoMask[i.count-1],lz:left(item.csoMask$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoDeviceVesion || #request.defines['csoDeviceVesion']!=null">
				<s:if test="#request.defines['csoDeviceVesion']>0">
					${lz:set("注释","****csoDeviceVesion关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoDeviceVesion))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoDeviceVesion",lz:indexOf(fieldName,"csoDeviceVesion")>-1)}
				  		<s:if test="#request.atCsoDeviceVesion==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoDeviceVesion字段的字串格式化输出****")}
					<td ref="csoDeviceVesion" class="td ">
						 <s:if test="#request.alias==null">
							 <s:if test="#request.CTRL.canEdit==true">
							 	<a class="modify" href="javascript:Update('${item.csoId}','vesion')"></a>
							 </s:if>
					     </s:if>
						 
						 	${lz:or(item$csoDeviceVesion[i.count-1],lz:left(item.csoDeviceVesion$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoAlarm || #request.defines['csoAlarm']!=null">
				<s:if test="#request.defines['csoAlarm']>0">
					${lz:set("注释","****csoAlarm关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoAlarm))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoAlarm",lz:indexOf(fieldName,"csoAlarm")>-1)}
				  		<s:if test="#request.atCsoAlarm==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoAlarm字段的字串格式化输出****")}
					<td ref="csoAlarm" class="td ">
						 
						 	${lz:or(item$csoAlarm[i.count-1],lz:left(item.csoAlarm$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoState || #request.defines['csoState']!=null">
				<s:if test="#request.defines['csoState']>0">
					${lz:set("注释","****csoState关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoState))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoState",lz:indexOf(fieldName,"csoState")>-1)}
				  		<s:if test="#request.atCsoState==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoState字段的字串格式化输出****")}
					<td ref="csoState" class="td ">
						 
						 	${lz:or(item$csoState[i.count-1],lz:left(item.csoState$,100))}
					</td>
				</s:else>
			</s:if>
			 
			<s:if test="#request.defines==null && #request.CTRL.l.csoStatus || #request.defines['csoStatus']!=null">
				<s:if test="#request.defines['csoStatus']>0">
					${lz:set("注释","****csoStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				 		<s:if test="#request.isList==true">
				 			${lz:set("sizeList",lz:size(item.csoStatus))}
				 			${lz:set("rowspan",rowspan>sizeList?rowspan:sizeList)}
				 		</s:if>
				  		${lz:set("atCsoStatus",lz:indexOf(fieldName,"csoStatus")>-1)}
				  		<s:if test="#request.atCsoStatus==true">
				 			<td ${isList?"class='onemore'":""}>${lz:left(lz:el(item,fieldName),100)}</td>
				 		</s:if>
				 	</s:iterator>
				</s:if>
				<s:else>
					${lz:set("注释","****csoStatus字段的字串格式化输出****")}
					<td ref="csoStatus" class="td ">
						 
						 	${lz:or(item$csoStatus[i.count-1],lz:left(item.csoStatus$,100))}
					</td>
				</s:else>
			</s:if>
		   ${lz:set("注释","*****************数据列表数据部分结束*****************")}		  
		  
		   
		    ${lz:set("注释","****自定义的单行记录操作句柄[request中的handles(String[])变量]****")}
			<td class="options">
				  <s:if test="#request.alias==null">	
					<s:if test="#request.CTRL.canExp[0]==true">
					<a href="javascript:HandleSel('发短信','${item.csoId}')">发短信</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[1]==true && #item.csoFlag != 3">
					<a href="javascript:HandleSel('续订','${item.csoId}')">续订</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[2]==true && #item.csoFlag != 3">
					<a href="javascript:HandleSel('取消订单','${item.csoId}')">取消订单</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[3]==true">
					<a href="javascript:HandleSel('待处理','${item.csoId}')">待处理</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[4]==true">
					<a href="javascript:HandleSel('结算','${item.csoId}')">结算</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[5]==true">
					<a href="javascript:HandleSel('重发','${item.csoId}')">重发</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[6]==true && #item.csoFlag != 3">
					<a href="javascript:HandleSel('作废','${item.csoId}')">作废</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[7]==true && #item.csoFlag != 3">
					<a href="javascript:HandleSel('退款','${item.csoId}')">退款</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[8]==true">
					<a href="javascript:HandleSel('扣款','${item.csoId}')">扣款</a>
					</s:if>
					<s:if test="#request.CTRL.canExp[9]==true && #item.csoFlag != 3">
					<a href="javascript:HandleSel('回滚','${item.csoId}')">回滚</a>
					</s:if>
					
					<%-- 
					<s:if test="#request.CTRL.canExp[10]==true && (#item.csoOutlets != #item.csoOutletsRet) && (#item.csoStatus == 1 || #item.csoStatus == 0)">
					<a href="javascript:HandleSel('更改网点','${item.csoId}')">更改网点</a>
					</s:if> 
					--%>
				</s:if>
				${lz:set("items",lz:split(handles,","))}
				<s:iterator value="#request.items" id="handle" status="i">
					${lz:set("isMenu",(lz:indexOf(handle,"@")==0))}
					<s:if test="#request.isMenu!=true">
					<a href="javascript:HandleSel('${handle}','${item.csoId}')">${handle}</a>
					</s:if>
				</s:iterator>
			</td>
			
			<td class="operation">
			<s:if test="#request.alias=='select'">
				<a href="javascript:SelectOrder('${item.csoId}')">选择</a>
			</s:if>
			<s:if test="#request.alias==null">
				<s:if test="#request.CTRL.canEdit==true">
				<a href="javascript:EditOrder('${item.csoId}')">修改</a></s:if>
				<s:if test="#request.CTRL.canDel==true">
				<a href="javascript:DelOrder('${item.csoId}','${item.csoId}')">删除</a></s:if>
			</s:if>
			<s:if test="#request.CTRL.canView==true">
			<a href="javascript:DetailsOrder('${item.csoId}')">详情</a></s:if>
			</td>
		  </tr>
		  
		  
		 
		 <s:if test="#request.rowspan>1">
		 <s:bean name="org.apache.struts2.util.Counter" id="counter">
			 <s:param name="first" value="1"/>
			 <s:param name="last" value="#request.rowspan-1"/>
			 <s:iterator status="k">
		 <tr>
		 	 <s:if test="#k.count==1"><td class="blank" rowspan="${rowspan-1}">&nbsp;</td></s:if>
			<s:if test="#request.defines==null && #request.CTRL.l.csoId || #request.defines['csoId']!=null">
				<s:if test="#request.defines['csoId']>0">
					${lz:set("注释","****csoId关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoId",lz:indexOf(fieldName,"csoId")>-1)}				  		
				  		<s:if test="#request.atCsoId==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoId[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoHost || #request.defines['csoHost']!=null">
				<s:if test="#request.defines['csoHost']>0">
					${lz:set("注释","****csoHost关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoHost",lz:indexOf(fieldName,"csoHost")>-1)}				  		
				  		<s:if test="#request.atCsoHost==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoHost[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoPayMember || #request.defines['csoPayMember']!=null">
				<s:if test="#request.defines['csoPayMember']>0">
					${lz:set("注释","****csoPayMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoPayMember",lz:indexOf(fieldName,"csoPayMember")>-1)}				  		
				  		<s:if test="#request.atCsoPayMember==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoPayMember[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoUseMember || #request.defines['csoUseMember']!=null">
				<s:if test="#request.defines['csoUseMember']>0">
					${lz:set("注释","****csoUseMember关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoUseMember",lz:indexOf(fieldName,"csoUseMember")>-1)}				  		
				  		<s:if test="#request.atCsoUseMember==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoUseMember[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoFeeType || #request.defines['csoFeeType']!=null">
				<s:if test="#request.defines['csoFeeType']>0">
					${lz:set("注释","****csoFeeType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoFeeType",lz:indexOf(fieldName,"csoFeeType")>-1)}				  		
				  		<s:if test="#request.atCsoFeeType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoFeeType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoArea || #request.defines['csoArea']!=null">
				<s:if test="#request.defines['csoArea']>0">
					${lz:set("注释","****csoArea关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoArea",lz:indexOf(fieldName,"csoArea")>-1)}				  		
				  		<s:if test="#request.atCsoArea==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoArea[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoOutlets || #request.defines['csoOutlets']!=null">
				<s:if test="#request.defines['csoOutlets']>0">
					${lz:set("注释","****csoOutlets关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoOutlets",lz:indexOf(fieldName,"csoOutlets")>-1)}				  		
				  		<s:if test="#request.atCsoOutlets==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoOutlets[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoOutletsRet || #request.defines['csoOutletsRet']!=null">
				<s:if test="#request.defines['csoOutletsRet']>0">
					${lz:set("注释","****csoOutletsRet关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoOutletsRet",lz:indexOf(fieldName,"csoOutletsRet")>-1)}				  		
				  		<s:if test="#request.atCsoOutletsRet==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoOutletsRet[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoModel || #request.defines['csoModel']!=null">
				<s:if test="#request.defines['csoModel']>0">
					${lz:set("注释","****csoModel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoModel",lz:indexOf(fieldName,"csoModel")>-1)}				  		
				  		<s:if test="#request.atCsoModel==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoModel[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoCar || #request.defines['csoCar']!=null">
				<s:if test="#request.defines['csoCar']>0">
					${lz:set("注释","****csoCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoCar",lz:indexOf(fieldName,"csoCar")>-1)}				  		
				  		<s:if test="#request.atCsoCar==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoCar[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoProvid || #request.defines['csoProvid']!=null">
				<s:if test="#request.defines['csoProvid']>0">
					${lz:set("注释","****csoProvid关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoProvid",lz:indexOf(fieldName,"csoProvid")>-1)}				  		
				  		<s:if test="#request.atCsoProvid==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoProvid[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoCarNumber || #request.defines['csoCarNumber']!=null">
				<s:if test="#request.defines['csoCarNumber']>0">
					${lz:set("注释","****csoCarNumber关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoCarNumber",lz:indexOf(fieldName,"csoCarNumber")>-1)}				  		
				  		<s:if test="#request.atCsoCarNumber==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoCarNumber[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoMobile || #request.defines['csoMobile']!=null">
				<s:if test="#request.defines['csoMobile']>0">
					${lz:set("注释","****csoMobile关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoMobile",lz:indexOf(fieldName,"csoMobile")>-1)}				  		
				  		<s:if test="#request.atCsoMobile==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoMobile[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoEvRfid || #request.defines['csoEvRfid']!=null">
				<s:if test="#request.defines['csoEvRfid']>0">
					${lz:set("注释","****csoEvRfid关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoEvRfid",lz:indexOf(fieldName,"csoEvRfid")>-1)}				  		
				  		<s:if test="#request.atCsoEvRfid==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoEvRfid[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoIsAdCar || #request.defines['csoIsAdCar']!=null">
				<s:if test="#request.defines['csoIsAdCar']>0">
					${lz:set("注释","****csoIsAdCar关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoIsAdCar",lz:indexOf(fieldName,"csoIsAdCar")>-1)}				  		
				  		<s:if test="#request.atCsoIsAdCar==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoIsAdCar[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoFinishTime || #request.defines['csoFinishTime']!=null">
				<s:if test="#request.defines['csoFinishTime']>0">
					${lz:set("注释","****csoFinishTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoFinishTime",lz:indexOf(fieldName,"csoFinishTime")>-1)}				  		
				  		<s:if test="#request.atCsoFinishTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoFinishTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoLongOrder || #request.defines['csoLongOrder']!=null">
				<s:if test="#request.defines['csoLongOrder']>0">
					${lz:set("注释","****csoLongOrder关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoLongOrder",lz:indexOf(fieldName,"csoLongOrder")>-1)}				  		
				  		<s:if test="#request.atCsoLongOrder==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoLongOrder[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoLongPrice || #request.defines['csoLongPrice']!=null">
				<s:if test="#request.defines['csoLongPrice']>0">
					${lz:set("注释","****csoLongPrice关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoLongPrice",lz:indexOf(fieldName,"csoLongPrice")>-1)}				  		
				  		<s:if test="#request.atCsoLongPrice==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoLongPrice[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoUseType || #request.defines['csoUseType']!=null">
				<s:if test="#request.defines['csoUseType']>0">
					${lz:set("注释","****csoUseType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoUseType",lz:indexOf(fieldName,"csoUseType")>-1)}				  		
				  		<s:if test="#request.atCsoUseType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoUseType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoTakeTime || #request.defines['csoTakeTime']!=null">
				<s:if test="#request.defines['csoTakeTime']>0">
					${lz:set("注释","****csoTakeTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoTakeTime",lz:indexOf(fieldName,"csoTakeTime")>-1)}				  		
				  		<s:if test="#request.atCsoTakeTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoTakeTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoRetTime || #request.defines['csoRetTime']!=null">
				<s:if test="#request.defines['csoRetTime']>0">
					${lz:set("注释","****csoRetTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoRetTime",lz:indexOf(fieldName,"csoRetTime")>-1)}				  		
				  		<s:if test="#request.atCsoRetTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoRetTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoDuration || #request.defines['csoDuration']!=null">
				<s:if test="#request.defines['csoDuration']>0">
					${lz:set("注释","****csoDuration关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoDuration",lz:indexOf(fieldName,"csoDuration")>-1)}				  		
				  		<s:if test="#request.atCsoDuration==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoDuration[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoFuel || #request.defines['csoFuel']!=null">
				<s:if test="#request.defines['csoFuel']>0">
					${lz:set("注释","****csoFuel关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoFuel",lz:indexOf(fieldName,"csoFuel")>-1)}				  		
				  		<s:if test="#request.atCsoFuel==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoFuel[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoElectric || #request.defines['csoElectric']!=null">
				<s:if test="#request.defines['csoElectric']>0">
					${lz:set("注释","****csoElectric关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoElectric",lz:indexOf(fieldName,"csoElectric")>-1)}				  		
				  		<s:if test="#request.atCsoElectric==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoElectric[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoCreditCard || #request.defines['csoCreditCard']!=null">
				<s:if test="#request.defines['csoCreditCard']>0">
					${lz:set("注释","****csoCreditCard关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoCreditCard",lz:indexOf(fieldName,"csoCreditCard")>-1)}				  		
				  		<s:if test="#request.atCsoCreditCard==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoCreditCard[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoCreditBill || #request.defines['csoCreditBill']!=null">
				<s:if test="#request.defines['csoCreditBill']>0">
					${lz:set("注释","****csoCreditBill关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoCreditBill",lz:indexOf(fieldName,"csoCreditBill")>-1)}				  		
				  		<s:if test="#request.atCsoCreditBill==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoCreditBill[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoInsureType || #request.defines['csoInsureType']!=null">
				<s:if test="#request.defines['csoInsureType']>0">
					${lz:set("注释","****csoInsureType关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoInsureType",lz:indexOf(fieldName,"csoInsureType")>-1)}				  		
				  		<s:if test="#request.atCsoInsureType==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoInsureType[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoFreehour || #request.defines['csoFreehour']!=null">
				<s:if test="#request.defines['csoFreehour']>0">
					${lz:set("注释","****csoFreehour关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoFreehour",lz:indexOf(fieldName,"csoFreehour")>-1)}				  		
				  		<s:if test="#request.atCsoFreehour==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoFreehour[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoCostDetails || #request.defines['csoCostDetails']!=null">
				<s:if test="#request.defines['csoCostDetails']>0">
					${lz:set("注释","****csoCostDetails关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoCostDetails",lz:indexOf(fieldName,"csoCostDetails")>-1)}				  		
				  		<s:if test="#request.atCsoCostDetails==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoCostDetails[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.gifts || #request.defines['gifts']!=null">
				<s:if test="#request.defines['gifts']>0">
					${lz:set("注释","****gifts关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atGifts",lz:indexOf(fieldName,"gifts")>-1)}				  		
				  		<s:if test="#request.atGifts==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">gifts[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.records || #request.defines['records']!=null">
				<s:if test="#request.defines['records']>0">
					${lz:set("注释","****records关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atRecords",lz:indexOf(fieldName,"records")>-1)}				  		
				  		<s:if test="#request.atRecords==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">records[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.violats || #request.defines['violats']!=null">
				<s:if test="#request.defines['violats']>0">
					${lz:set("注释","****violats关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atViolats",lz:indexOf(fieldName,"violats")>-1)}				  		
				  		<s:if test="#request.atViolats==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">violats[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.troubles || #request.defines['troubles']!=null">
				<s:if test="#request.defines['troubles']>0">
					${lz:set("注释","****troubles关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atTroubles",lz:indexOf(fieldName,"troubles")>-1)}				  		
				  		<s:if test="#request.atTroubles==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">troubles[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.operrecords || #request.defines['operrecords']!=null">
				<s:if test="#request.defines['operrecords']>0">
					${lz:set("注释","****operrecords关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atOperrecords",lz:indexOf(fieldName,"operrecords")>-1)}				  		
				  		<s:if test="#request.atOperrecords==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">operrecords[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoRebate || #request.defines['csoRebate']!=null">
				<s:if test="#request.defines['csoRebate']>0">
					${lz:set("注释","****csoRebate关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoRebate",lz:indexOf(fieldName,"csoRebate")>-1)}				  		
				  		<s:if test="#request.atCsoRebate==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoRebate[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoMarginNeed || #request.defines['csoMarginNeed']!=null">
				<s:if test="#request.defines['csoMarginNeed']>0">
					${lz:set("注释","****csoMarginNeed关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoMarginNeed",lz:indexOf(fieldName,"csoMarginNeed")>-1)}				  		
				  		<s:if test="#request.atCsoMarginNeed==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoMarginNeed[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoPredict || #request.defines['csoPredict']!=null">
				<s:if test="#request.defines['csoPredict']>0">
					${lz:set("注释","****csoPredict关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoPredict",lz:indexOf(fieldName,"csoPredict")>-1)}				  		
				  		<s:if test="#request.atCsoPredict==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoPredict[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoPayNeed || #request.defines['csoPayNeed']!=null">
				<s:if test="#request.defines['csoPayNeed']>0">
					${lz:set("注释","****csoPayNeed关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoPayNeed",lz:indexOf(fieldName,"csoPayNeed")>-1)}				  		
				  		<s:if test="#request.atCsoPayNeed==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoPayNeed[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoPayReal || #request.defines['csoPayReal']!=null">
				<s:if test="#request.defines['csoPayReal']>0">
					${lz:set("注释","****csoPayReal关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoPayReal",lz:indexOf(fieldName,"csoPayReal")>-1)}				  		
				  		<s:if test="#request.atCsoPayReal==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoPayReal[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoPayMoney || #request.defines['csoPayMoney']!=null">
				<s:if test="#request.defines['csoPayMoney']>0">
					${lz:set("注释","****csoPayMoney关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoPayMoney",lz:indexOf(fieldName,"csoPayMoney")>-1)}				  		
				  		<s:if test="#request.atCsoPayMoney==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoPayMoney[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoPayCoupon || #request.defines['csoPayCoupon']!=null">
				<s:if test="#request.defines['csoPayCoupon']>0">
					${lz:set("注释","****csoPayCoupon关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoPayCoupon",lz:indexOf(fieldName,"csoPayCoupon")>-1)}				  		
				  		<s:if test="#request.atCsoPayCoupon==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoPayCoupon[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoPayCoin || #request.defines['csoPayCoin']!=null">
				<s:if test="#request.defines['csoPayCoin']>0">
					${lz:set("注释","****csoPayCoin关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoPayCoin",lz:indexOf(fieldName,"csoPayCoin")>-1)}				  		
				  		<s:if test="#request.atCsoPayCoin==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoPayCoin[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoPayKilom || #request.defines['csoPayKilom']!=null">
				<s:if test="#request.defines['csoPayKilom']>0">
					${lz:set("注释","****csoPayKilom关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoPayKilom",lz:indexOf(fieldName,"csoPayKilom")>-1)}				  		
				  		<s:if test="#request.atCsoPayKilom==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoPayKilom[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoPayMileage || #request.defines['csoPayMileage']!=null">
				<s:if test="#request.defines['csoPayMileage']>0">
					${lz:set("注释","****csoPayMileage关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoPayMileage",lz:indexOf(fieldName,"csoPayMileage")>-1)}				  		
				  		<s:if test="#request.atCsoPayMileage==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoPayMileage[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoPayRent || #request.defines['csoPayRent']!=null">
				<s:if test="#request.defines['csoPayRent']>0">
					${lz:set("注释","****csoPayRent关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoPayRent",lz:indexOf(fieldName,"csoPayRent")>-1)}				  		
				  		<s:if test="#request.atCsoPayRent==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoPayRent[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoPayInsure || #request.defines['csoPayInsure']!=null">
				<s:if test="#request.defines['csoPayInsure']>0">
					${lz:set("注释","****csoPayInsure关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoPayInsure",lz:indexOf(fieldName,"csoPayInsure")>-1)}				  		
				  		<s:if test="#request.atCsoPayInsure==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoPayInsure[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoPayTimeout || #request.defines['csoPayTimeout']!=null">
				<s:if test="#request.defines['csoPayTimeout']>0">
					${lz:set("注释","****csoPayTimeout关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoPayTimeout",lz:indexOf(fieldName,"csoPayTimeout")>-1)}				  		
				  		<s:if test="#request.atCsoPayTimeout==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoPayTimeout[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoPayDriver || #request.defines['csoPayDriver']!=null">
				<s:if test="#request.defines['csoPayDriver']>0">
					${lz:set("注释","****csoPayDriver关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoPayDriver",lz:indexOf(fieldName,"csoPayDriver")>-1)}				  		
				  		<s:if test="#request.atCsoPayDriver==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoPayDriver[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoIncome || #request.defines['csoIncome']!=null">
				<s:if test="#request.defines['csoIncome']>0">
					${lz:set("注释","****csoIncome关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoIncome",lz:indexOf(fieldName,"csoIncome")>-1)}				  		
				  		<s:if test="#request.atCsoIncome==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoIncome[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoUseIndex || #request.defines['csoUseIndex']!=null">
				<s:if test="#request.defines['csoUseIndex']>0">
					${lz:set("注释","****csoUseIndex关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoUseIndex",lz:indexOf(fieldName,"csoUseIndex")>-1)}				  		
				  		<s:if test="#request.atCsoUseIndex==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoUseIndex[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoFlag || #request.defines['csoFlag']!=null">
				<s:if test="#request.defines['csoFlag']>0">
					${lz:set("注释","****csoFlag关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoFlag",lz:indexOf(fieldName,"csoFlag")>-1)}				  		
				  		<s:if test="#request.atCsoFlag==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoFlag[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoFrom || #request.defines['csoFrom']!=null">
				<s:if test="#request.defines['csoFrom']>0">
					${lz:set("注释","****csoFrom关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoFrom",lz:indexOf(fieldName,"csoFrom")>-1)}				  		
				  		<s:if test="#request.atCsoFrom==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoFrom[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoSrc || #request.defines['csoSrc']!=null">
				<s:if test="#request.defines['csoSrc']>0">
					${lz:set("注释","****csoSrc关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoSrc",lz:indexOf(fieldName,"csoSrc")>-1)}				  		
				  		<s:if test="#request.atCsoSrc==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoSrc[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoUpdateTime || #request.defines['csoUpdateTime']!=null">
				<s:if test="#request.defines['csoUpdateTime']>0">
					${lz:set("注释","****csoUpdateTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoUpdateTime",lz:indexOf(fieldName,"csoUpdateTime")>-1)}				  		
				  		<s:if test="#request.atCsoUpdateTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoUpdateTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoAddTime || #request.defines['csoAddTime']!=null">
				<s:if test="#request.defines['csoAddTime']>0">
					${lz:set("注释","****csoAddTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoAddTime",lz:indexOf(fieldName,"csoAddTime")>-1)}				  		
				  		<s:if test="#request.atCsoAddTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoAddTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoEndTime || #request.defines['csoEndTime']!=null">
				<s:if test="#request.defines['csoEndTime']>0">
					${lz:set("注释","****csoEndTime关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoEndTime",lz:indexOf(fieldName,"csoEndTime")>-1)}				  		
				  		<s:if test="#request.atCsoEndTime==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoEndTime[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoSerial || #request.defines['csoSerial']!=null">
				<s:if test="#request.defines['csoSerial']>0">
					${lz:set("注释","****csoSerial关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoSerial",lz:indexOf(fieldName,"csoSerial")>-1)}				  		
				  		<s:if test="#request.atCsoSerial==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoSerial[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoCode || #request.defines['csoCode']!=null">
				<s:if test="#request.defines['csoCode']>0">
					${lz:set("注释","****csoCode关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoCode",lz:indexOf(fieldName,"csoCode")>-1)}				  		
				  		<s:if test="#request.atCsoCode==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoCode[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoRemark || #request.defines['csoRemark']!=null">
				<s:if test="#request.defines['csoRemark']>0">
					${lz:set("注释","****csoRemark关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoRemark",lz:indexOf(fieldName,"csoRemark")>-1)}				  		
				  		<s:if test="#request.atCsoRemark==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoRemark[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoEditor || #request.defines['csoEditor']!=null">
				<s:if test="#request.defines['csoEditor']>0">
					${lz:set("注释","****csoEditor关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoEditor",lz:indexOf(fieldName,"csoEditor")>-1)}				  		
				  		<s:if test="#request.atCsoEditor==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoEditor[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoMask || #request.defines['csoMask']!=null">
				<s:if test="#request.defines['csoMask']>0">
					${lz:set("注释","****csoMask关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoMask",lz:indexOf(fieldName,"csoMask")>-1)}				  		
				  		<s:if test="#request.atCsoMask==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoMask[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoDeviceVesion || #request.defines['csoDeviceVesion']!=null">
				<s:if test="#request.defines['csoDeviceVesion']>0">
					${lz:set("注释","****csoDeviceVesion关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoDeviceVesion",lz:indexOf(fieldName,"csoDeviceVesion")>-1)}				  		
				  		<s:if test="#request.atCsoDeviceVesion==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoDeviceVesion[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoAlarm || #request.defines['csoAlarm']!=null">
				<s:if test="#request.defines['csoAlarm']>0">
					${lz:set("注释","****csoAlarm关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoAlarm",lz:indexOf(fieldName,"csoAlarm")>-1)}				  		
				  		<s:if test="#request.atCsoAlarm==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoAlarm[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoState || #request.defines['csoState']!=null">
				<s:if test="#request.defines['csoState']>0">
					${lz:set("注释","****csoState关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoState",lz:indexOf(fieldName,"csoState")>-1)}				  		
				  		<s:if test="#request.atCsoState==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoState[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
			<s:if test="#request.defines==null && #request.CTRL.l.csoStatus || #request.defines['csoStatus']!=null">
				<s:if test="#request.defines['csoStatus']>0">
					${lz:set("注释","****csoStatus关联表的子级字段：如果用户勾选了要显示的话****")}
				  	<s:iterator value="#request.childes" id="fieldName" status="j">
				  		${lz:set("atCsoStatus",lz:indexOf(fieldName,"csoStatus")>-1)}				  		
				  		<s:if test="#request.atCsoStatus==true">
				  			${lz:set("isList",lz:indexOf(fieldName,"[]")>-1)}
				  			<s:if test="#request.isList==true">
				  				<lz:set name="arrFieldName">csoStatus[${k.count}]${lz:split(fieldName,"\\[\\]")[1]}</lz:set>
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
	* 添加系统订单
	**/
	function AddOrder(parent){		
		var url = "${basePath}${namespace}order_edit.do?edittype=save";
		var params = {entrypoint:"${entrypoint}",parent:(parent?parent:""),ctrl:${ctrl==null?"{title:'添加系统订单'}":lz:json(ctrl)}};
		href(url,params);
	}
</s:if>
<s:if test="#request.alias=='select'">
	/**
	* 选择系统订单
	**/
	function SelectOrder(id){		
		var completeFunc = top["complete_"+window.name];
		if(completeFunc)
			completeFunc(id);
	}
</s:if>
<s:if test="#request.CTRL.canEdit==true">
	/**
	* 修改系统订单
	**/
	function EditOrder(id){
		var url = "${basePath}${namespace}order_edit.do?edittype=update";
		var params = {entrypoint:"${entrypoint}",id:id,ctrl:${ctrl==null?"{title:'修改系统订单'}":lz:json(ctrl)}};
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
		EditOrder(checker.val());
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
				var url = "${basePath}${namespace}order_edit.do";
				var params = {entrypoint:"${entrypoint}",method:flag,PARAMS:${lz:json(PARAMS)},ctrl:{title:"批量更新系统订单",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}order_edit.do";
			var params = {entrypoint:"${entrypoint}",method:flag,ids:ids,ctrl:{title:"批量更新系统订单",visible:false,editable:false,fields:{}}};
			for(var o in fieldNames[flag])
				params.ctrl["fields"][o]={visible:true,editable:true};
			href(url,params);
		}
	}
	
	/**
	* 修改系统订单
	**/
	function Update(id,flag)
	{
		var url = "${basePath}${namespace}order_edit.do";
		var params = {entrypoint:"${entrypoint}",id:id,method:flag,ctrl:{title:"更新系统订单",visible:false,editable:false,fields:{}}};
		for(var o in fieldNames[flag])
			params.ctrl["fields"][o]={visible:true,editable:true};
		href(url,params);
	}
	
	var fieldNames={};
	fieldNames["longOrder"]={};
	fieldNames["flag"]={};
	fieldNames["vesion"]={};
	fieldNames["longOrder"]["csoLongOrder"]=true;
	fieldNames["flag"]["csoFlag"]=true;
	fieldNames["vesion"]["csoDeviceVesion"]=true;
	
</s:if>

	<s:if test="#session.ccclubs_login.suId==0">
	$(function(){
		//修改系统订单任意字段
		$(".table tbody td.td").dblclick(function(){
			var url = "${basePath}${namespace}order_edit.do";
			var params = {entrypoint:"${entrypoint}",id:$(this).parents("tr:eq(0)").attr("id"),method:"any",ctrl:{title:"更新系统订单",visible:false,editable:false,fields:{}}};
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
			var url = "${basePath}${namespace}order_edit.do";
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
			var url = "${basePath}${namespace}order_edit.do";
			var params = {entrypoint:"${entrypoint}",ctrl:{title:operate}};
			href(url,params);
		}
	}
<s:if test="#request.CTRL.canDel==true">
	/**
	* 删除系统订单
	**/
	function DelOrder(id,flag)
	{
		var dialog=top.$.dialog({
				html:"<span style='white-space:nowrap;'>确定要删除系统订单["+flag+"]吗？</span>",
				title:"删除系统订单",
				modal: true,			
				onOk:function(){
					dialog.closeDialog();
					var url = "${basePath}${namespace}order_del.do";
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
				html:"<span style='white-space:nowrap;'>确定要删除所选系统订单吗？</span>",
				title:"批量删除系统订单",
				modal: true,			
				onOk:function(){
					var ids = "";
					checker.each(function(i){
						if(ids!="")
							ids+=",";
						ids+=$(this).val();
					});
					dialog.closeDialog();
					var url = "${basePath}${namespace}order_del.do";
					var params = {entrypoint:"${entrypoint}",ids:ids};
					$.submit(url,params);	
				}
		});
	}
</s:if>
	/**
	* 显示系统订单详情
	**/
	function DetailsOrder(id)
	{
		var url = "${basePath}${namespace}order_details.do";
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


<!-- LAZY3Q_JSP_BOTTOM -->
<script>
function getHandle(operate,id){
	if(operate=="发短信")
		return sendSms;
	if(operate=="退款")
		return refund;
	if(operate=="扣款")
		return deduct;
}
function refund(operate,id){
	var order = CsOrder.get(id);
	if(order.csoStatus!=4 && order.csoStatus!=3){
		Alert("只有已完成或已取消的订单才能退款");
	}else{
		top.$.open("${basePath}admin/finance/recharge_edit.do",{
			"csArtifRecord.csarHost":order.csoHost,
			"csArtifRecord.csarTitle":"",
			"csArtifRecord.csarMember":order.csoPayMember,
			"csArtifRecord.csarMoneyType":0,
			"csArtifRecord.csarRecordSubject":CsRecordSubject.Get({csrsName:"订单退款"}).csrsId,
			"csArtifRecord.csarMember":order.csoPayMember,
			"csArtifRecord.csarOrder":order.csoId
		});
	}
}
function deduct(operate,id){
	var order = CsOrder.get(id);
	if(order.csoStatus!=4 && order.csoStatus!=3){
		Alert("只有已完成或已取消的订单才能扣款");
	}else{
		top.$.open("${basePath}admin/finance/recharge_edit.do",{
			"csArtifRecord.csarHost":order.csoHost,
			"csArtifRecord.csarTitle":"",
			"csArtifRecord.csarMember":order.csoPayMember,
			"csArtifRecord.csarMoneyType":0,
			"csArtifRecord.csarRecordSubject":CsRecordSubject.Get({csrsName:"订单补扣"}).csrsId,
			"csArtifRecord.csarMember":order.csoPayMember,
			"csArtifRecord.csarOrder":order.csoId
		});
	}
}
function redate(string){
	return $.formatDate("MM月dd日HH点mm分",$.dateFormat(string,"yyyy-MM-dd HH:mm:ss"));
}
function sendSms(operate,id){
	var memberid=$("#"+id+" td[ref=csoUseMember]").attr("relate");
	var member=$.getObject("${basePath}admin/user/member_query.do",{object:true,csmId:memberid})[0].object;	
	var strStatus = $.trim($("#"+id+" td[ref=csoStatus]").text());
	var smsContent = "";
	if(strStatus=="已完成"){
		var smsTemplate = $.getObject("${basePath}admin/configurator/template_query.do",{object:true,csstFlag:"SETTLE_ORDER"})[0].object;
		var order = $.getObject("${basePath}admin/service/order_query.do",{object:true,csoId:id})[0].object;
		smsContent = smsTemplate.csstContent;
		smsContent=smsContent.replace("{time}",$.formatDate("MM月dd日HH点mm分",new Date(order.csoRetTime)));
		smsContent=smsContent.replace("{rent}",order.csoPayRent);
		smsContent=smsContent.replace("{mileage}",order.csoPayMileage);
		smsContent=smsContent.replace("{insure}",order.csoPayInsure);
		smsContent=smsContent.replace("{timeout}",order.csoPayTimeout);
		smsContent=smsContent.replace("{course}",order.csoPayMileage+order.csoPayKilom);
	}else{
		var smsTemplate = $.getObject("${basePath}admin/configurator/template_query.do",{object:true,csstFlag:"ADD_ORDER"})[0].object;
		smsContent = smsTemplate.csstContent;
		smsContent=smsContent.replace("{start}",redate($.trim($("#"+id+" td[ref=csoStartTime]").text())));
		smsContent=smsContent.replace("{finish}",redate($.trim($("#"+id+" td[ref=csoFinishTime]").text())));
		smsContent=smsContent.replace("{outlets}",$.trim($("#"+id+" td[ref=csoOutlets]").text()));
		smsContent=smsContent.replace("{number}",$.trim($("#"+id+" td[ref=csoCar]").text()));
		smsContent=smsContent.replace("{code}",$.trim($("#"+id+" td[ref=csoCode]").text()));
	}
	
	var params = {
		entrypoint:"${entrypoint}",
		ctrl:{
			fields:{
				cssmMobile:{
					defaultValue:member.csmMobile
				},
				cssmContent:{
					defaultValue:smsContent
				}
			}
		}
	};
	href("${basePath}admin/systematic/smsmt_edit.do",params);
}
</script>
<!-- LAZY3Q_JSP_BOTTOM -->


</body>
</html>