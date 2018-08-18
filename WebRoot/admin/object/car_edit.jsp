<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/object/car.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/object/car.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csCar.cscId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cscId,cscHost,cscNumber,cscCarNo,cscModel,cscOutlets,cscProvid,cscRebate,cscIsAd,cscMobile,cscPassword,cscOilCard,cscVin,cscEngineNo,cscBuyDate,cscBargainNo,cscTaxPrice,cscBuyPrice,cscCertific,cscFactoryNo,cscColor,cscPreview,cscImages,cscMaintKm,cscCheckIn,cscAnnualPre,cscAnnual,cscAnnualInfo,cscAnnualNext,cscTiDate,cscTiUnit,cscTiNo,cscBiDate,cscBiUnit,cscBiNo,cscBiType,cscBiLimit,cscBiCompany,cscMemo,cscSetup,cscDeviceVesion,cscMqttFlag,cscTerNo,cscBindPlatform",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscId,cscHost,cscNumber,cscCarNo,cscModel,cscOutlets,cscProvid,cscRebate,cscIsAd,cscMobile,cscPassword,cscOilCard,cscVin,cscEngineNo,cscBuyDate,cscBargainNo,cscTaxPrice,cscBuyPrice,cscCertific,cscFactoryNo,cscColor,cscPreview,cscImages,cscMaintKm,cscCheckIn,cscAnnualPre,cscAnnual,cscAnnualInfo,cscAnnualNext,cscTiDate,cscTiUnit,cscTiNo,cscBiDate,cscBiUnit,cscBiNo,cscBiType,cscBiLimit,cscBiCompany,cscMemo,cscSetup,cscDeviceVesion,cscMqttFlag,cscTerNo,cscBindPlatform",
	</s:if>
	<s:elseif test="#request.CTRL.title=='车辆年检'">
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cscAnnualPre,cscAnnual,cscAnnualNext,cscAnnualInfo,nothing",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscNumber,cscAnnualPre,cscAnnual,cscAnnualNext,cscAnnualInfo,nothing",
	</s:elseif>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cscId,cscHost,cscNumber,cscCarNo,cscModel,orders,violats,troubles,repairs,maintains,onlines,cscProvid,cscRebate,cscIsAd,cscMobile,cscPassword,cscOilCard,cscVin,cscEngineNo,cscBuyDate,cscBargainNo,cscTaxPrice,cscBuyPrice,cscCertific,cscFactoryNo,cscColor,cscPreview,cscImages,cscMaintKm,cscCheckIn,cscAnnualPre,cscAnnual,cscAnnualInfo,cscAnnualNext,cscTiDate,cscTiUnit,cscTiNo,cscBiDate,cscBiUnit,cscBiNo,cscBiType,cscBiLimit,cscBiCompany,cscMemo,cscSetup,cscDeviceVesion,cscMqttFlag,cscTerNo,cscBindPlatform",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscId,cscHost,cscNumber,cscCarNo,cscModel,cscOutlets,orders,violats,troubles,repairs,maintains,onlines,cscProvid,cscRebate,cscIsAd,cscMobile,cscPassword,cscOilCard,cscVin,cscEngineNo,cscBuyDate,cscBargainNo,cscTaxPrice,cscBuyPrice,cscCertific,cscFactoryNo,cscColor,cscPreview,cscImages,cscMaintKm,cscCheckIn,cscAnnualPre,cscAnnual,cscAnnualInfo,cscAnnualNext,cscTiDate,cscTiUnit,cscTiNo,cscBiDate,cscBiUnit,cscBiNo,cscBiType,cscBiLimit,cscBiCompany,cscEditor,cscUpdateTime,cscAddTime,cscRemark,cscMemo,cscSetup,cscDeviceVesion,cscMqttFlag,cscTerNo,cscBindPlatform",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"车辆编辑":CTRL.title}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="author" content="飞啊飘啊">
	<meta name="hashcode" content="${window.hashcode}">
	
	<!-- 感谢jquery提供的前端js库 -->
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("jauery_js")}"></script>
	
	<!-- lazy3q提供的系统js -->
	<link href="${basePath}admin/css/${lz:or(style,lz:or(cookie.style.value,lz:config("style")))}" rel="stylesheet" type="text/css" />	
	<script type="text/javascript" src="${basePath}admin/js/${lz:config("lazy3q_ui")}"></script>
	
	<!-- 用户自定义js(懒加载) -->
	<script type="text/javascript" src="${basePath}/admin/js/define.js"></script>
	<!-- 用户自定义css样式 -->
	<script type="text/javascript" src="${basePath}/admin/css/define.css"></script>
	
	<script type="text/javascript">
		window.basePath="${basePath}";
		window.hashcode = "${window.hashcode}";
		window.contextPath="${basePath}${proname}/";
		window.apiUrl="${basePath}${proname}/api.do";
	</script>
		
	<!-- 感谢kindeditor出品的在线HTML编辑控件 -->
	<script type="text/javascript" charset="utf-8" src="${basePath}admin/js/kindeditor/kindeditor.js"></script>
	<!-- 感谢My97DatePicker出品的时间控件 -->
	<script language="javascript" type="text/javascript" src="${basePath}admin/js/My97DatePicker/WdatePicker.js"></script>
	
	<!------------------------LAZY3Q_JSP_HEAD------------------------>
	<!------------------------LAZY3Q_JSP_HEAD------------------------>

</head>  
<body>

${lz:set("注释","****非法权限时的友情提醒****")}
<s:if test="#request.canView!=true && #request.canAdd!=true && #request.canEdit!=true">
	<div style="padding:80px 20px;width:500px;margin:0px auto;">
		<center class="message"><FONT color=red><H4>${lz:config("nolimit.message")}</H4></FONT></center>
	</div>
</body>
</html>
<%if(true)return;%>
</s:if>



<!------------------------LAZY3Q_JSP_BODY------------------------>
<s:if test="csCar.cscId>0">
<style>
.timeline{height:40px;width:702px;overflow:hidden;position:relative;margin:20px auto;padding-top:10px;}
.timeline .lines{position:relative;background:#ededed;border-color:#ccc #fff #fff #ccc;border-width:1px 1px 1px 1px;border-style:solid;position:relative;height:10px;}
.timeline .order,.timeline .restr{position:absolute;top:0px;height:10px;border-radius:20px;}
.timeline .order{background:url(${basePath}admin/images/timeline.png) 0px -1px;z-index:1;cursor:pointer;}
.timeline .restr{background:url(${basePath}admin/images/timeline.png) 0px 11px;z-index:2;}
.timeline .days{position:relative;position:relative;}
.timeline .day{position:absolute;top:2px;height:15px;text-indent:0px;pading:0px;line-height:15px;text-align:center;color:#666666;border-right:1px solid #000000;}
.colspan td{}
</style>
<lz:set name="before$cscOutlets">
<div class="timeline" >
	<div class="lines">
		<s:iterator value="csCar.values.orders" id="order" status="i">
			${lz:set("left",lz:digit((order.csoStartTime.time-begin.time)*100/howlong,2))}
			${lz:set("right",lz:digit((order.csoFinishTime.time-begin.time)*100/howlong,2))}
			<div onclick="window.href('${basePath}admin/service/order_details.do?id=${order.csoId}',{ctrl:{editable:false,visible:true}})" style="left:${left}%;width:${lz:digit(right-left,2)}%;" title="${lz:date(order.csoStartTime,"MM-dd HH:mm")} 至 ${lz:date(order.csoFinishTime,"MM-dd HH:mm")} 有单,点击查看订单" class="order">
			</div>
		</s:iterator>
		<s:iterator value="csCar.values.restrs" id="restr" status="i">
			${lz:set("left",lz:digit((restr.start.time-begin.time)*100/howlong,2))}
			${lz:set("right",lz:digit((restr.finish.time-begin.time)*100/howlong,2))}
			<div style="left:${left}%;width:${lz:digit(right-left,2)}%;" title="${lz:date(restr.start,"MM-dd HH:mm")} 至 ${lz:date(restr.finish,"MM-dd HH:mm")} 限行" class="restr">
			</div>
		</s:iterator>
	</div>
	<div class="days">
		<s:iterator value="#request.dates" id="date" status="i">
			${lz:set("left",lz:digit((date.start.time-begin.time)*100/howlong,2))}
			${lz:set("right",lz:digit((date.finish.time-begin.time)*100/howlong,2))}
			<div style="left:${left}%;width:${lz:digit(right-left,2)}%;" title="${lz:date(date.start,"MM月dd日 E")}" class="day">
				<s:if test="#request.day>20">${lz:date(date.start,"MM.dd")}</s:if>
				<s:elseif test="#request.day>10">${lz:replace(lz:date(date.start,"MM.dd E"),"星期","")}</s:elseif>
				<s:else>${lz:date(date.start,"MM月dd日 E")}</s:else>
			</div>
		</s:iterator>
	</div>
</div>
</lz:set>
</s:if>
<s:if test="#request.method=='status'">
	<lz:set name="jsonString">
		{
			title:"车辆上下线",
			action:"car_status.do",
			editable:false,
			visible:false,
			fields:{
				cscOnPsd:{editable:true,visible:true}
				,cscRemark:{editable:true,visible:true}
				,cscStatus:{editable:true,visible:true}
			}
		}
	</lz:set>
	${lz:set("CTRL",lz:CTRL(jsonString))}
	<script>
	$(function(){
		$("#cscOnPsd").val("");
		$("#cscRemark").val("");
		$("<b>*</b>").insertAfter("#cscRemark");
		var propertyStr = "<dl class=\"cscoProperty \" major  ref=\"cscoProperty\" >"
		+"<dt>下线标签:</dt>"
		+"<dd input=\"select\">"
		 	+"<select class=\"narrow\" id=\"cscoProperty\" name=\"property\">"
		 		+"<option value=\"\">请选择</option>"
 					+"${lz:set('items', lz:query('ccclubs_system','SELECT * from srv_property p where EXISTS (SELECT sp_flag from srv_property where p.sp_parent = sp_id and sp_flag = \'CAR_OFF_LINE_LABEL\' and sp_parent = 0 )') )}"
					+"<s:iterator value='#request.items' id='item' status='i'>"
					+"<option value='${item.spId}'>${item.spName}</option>"
					+"</s:iterator>"
					+"${lz:set('items',null)}"
		 	+"</select>"
	 	 +"<b>*</b>"
	 	 +"<em>请选择下线标签</em>"
		+"</dd>"
		+"</dl>";
		if($("#cscStatus").val()==2){
			$("dl[ref='cscStatus']").after(propertyStr);
		}	
		$("#cscStatus").change(function(){
			if($("#cscStatus").val()==2){
				$("dl[ref='cscStatus']").after(propertyStr);
			}else{
				if($("dl[ref='cscoProperty']").length>0){
					$("dl[ref='cscoProperty']").remove();
				}
			}
			
		});
	});
	</script>
</s:if>
<s:if test="#request.CTRL.title=='车辆调度'">
	<lz:set name="jsonString">
		{
			title:"车辆调度",
			action:"car_dispatch.do",
			editable:false,
			visible:false,
			fields:{
				cscNumber:{editable:false,visible:true}				
				,cscOutlets:{editable:true,visible:true}
				,cscHost:{editable:true,visible:true}
				,cscRemark:{editable:true,visible:true}
			}
		}
	</lz:set>
	${lz:set("CTRL",lz:CTRL(jsonString))}
	<lz:set name="after$cscHost">
		<dl><dt></dt><dd></dd></dl>
	</lz:set>
	<script>
	$(function(){
		$("#cscRemark").val("");
		$("<b>*</b>").insertAfter("#cscRemark");
	});
	</script>
</s:if>

<lz:set name="logHtml">
	<dl class="${(canEditable && false)?"hidden":""} ${canEditable && false ? "" : " minor"}"  ref="orders" style="width:98%;">
		<dt>操作日志:</dt>
		<dd>
		 	<s:if test="csCar.cscId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="logs" name="logs" frameborder='0' src="" url="${basePath}admin/systematic/log.do?canQuery=false&fields=cslName,cslUser,cslModel,cslType,cslDetail,cslAddTime&cslRelate=${csCar.cscId}&cslClass=CsCar&ctrl={mutual:'dialog',canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#logs').attr('src',$('#logs').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>操作日志</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>
		</dd>
	</dl>
</lz:set>
<script>
window.$on("readyStart",function(){
	$("${lz:js(logHtml)}").insertAfter("dl[ref='maintains']");
});
$(function(){
	var checkForm = function(){
		if($.trim($("#cscTiDate").val())!="" && $.trim($("#cscTiUnit").val())!="" ){
			if($.date($("#cscTiDate").val()).getTime()>$.date($("#cscTiUnit").val()).getTime()){
				$("#cscTiUnit").say("交强险到期时间不能比投保时间早");
				window.location.href="#cscCheckIn";
				$.tips("交强险到期时间不能比投保时间早");
				return false;
			}
		}
		if($.trim($("#cscBiDate").val())!="" && $.trim($("#cscBiUnit").val())!="" ){
			if($.date($("#cscBiDate").val()).getTime()>$.date($("#cscBiUnit").val()).getTime()){
				$("#cscBiUnit").say("商业险到期时间不能比投保时间早");				
				window.location.href="#cscCheckIn";
				$.tips("商业险到期时间不能比投保时间早");
				return false;
			}
		}	
	};
	$("#cscTiDate").change(function(){
		if($.trim($("#cscTiDate").val())!="" && $.trim($("#cscTiUnit").val())=="" ){
			$("#cscTiUnit").val($.date(new Date($.date($("#cscTiDate").val()).getTime()+(1000*60*60*24*365)),"yyyy-MM-dd"));
		}
	});
	$("#cscBiDate").change(function(){
		if($.trim($("#cscBiDate").val())!="" && $.trim($("#cscBiUnit").val())=="" ){
			$("#cscBiUnit").val($.date(new Date($.date($("#cscBiDate").val()).getTime()+(1000*60*60*24*365)),"yyyy-MM-dd"));
		}
	});
	$("#cscTiDate,#cscTiUnit,#cscBiDate,#cscBiUnit").change(checkForm);
	$("#carForm").submit(checkForm);
});
</script>
<!------------------------LAZY3Q_JSP_BODY------------------------>



<script>
$(function(){	
	showTips("${tips.value}");
});
window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}";


$(function(){
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#carForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cscId==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscHost==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscNumber==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscNumber":function(el){
			if(jQuery.trim(el.value)=="")
				return "车牌号不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "车牌号最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("car_query.do",{exists:true,cscNumber:el.value});
				if(exists && exists.length>0){
					if(${csCar.cscId==null} || exists[0].value!=$("#cscId").val())
						return "车牌号已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscCarNo==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscCarNo":function(el){
			if(jQuery.trim(el.value)=="")
				return "车牌名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "车牌名称最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("car_query.do",{exists:true,cscCarNo:el.value});
				if(exists && exists.length>0){
					if(${csCar.cscId==null} || exists[0].value!=$("#cscId").val())
						return "车牌名称已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscModel==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscModel":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscOutlets==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscOutlets":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscProvid==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscProvid":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择供应商";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscRebate==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscRebate":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "折扣输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscIsAd==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscIsAd":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择广告车？";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscLongitude==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscLongitude":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "经度输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscLatitude==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscLatitude":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "纬度输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscMobile==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscMobile":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "手机号码最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("car_query.do",{exists:true,cscMobile:el.value});
				if(exists && exists.length>0){
					if(${csCar.cscId==null} || exists[0].value!=$("#cscId").val())
						return "手机号码已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscPassword==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscPassword":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "服务密码最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscOilCard==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscOilCard":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "加油卡号最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("car_query.do",{exists:true,cscOilCard:el.value});
				if(exists && exists.length>0){
					if(${csCar.cscId==null} || exists[0].value!=$("#cscId").val())
						return "加油卡号已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscVin==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscVin":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "车架号最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("car_query.do",{exists:true,cscVin:el.value});
				if(exists && exists.length>0){
					if(${csCar.cscId==null} || exists[0].value!=$("#cscId").val())
						return "车架号已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscEngineNo==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscEngineNo":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "发动机号最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("car_query.do",{exists:true,cscEngineNo:el.value});
				if(exists && exists.length>0){
					if(${csCar.cscId==null} || exists[0].value!=$("#cscId").val())
						return "发动机号已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscBuyDate==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscBuyDate":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscBargainNo==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscBargainNo":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "合同号最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscTaxPrice==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscTaxPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "购置税输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscBuyPrice==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscBuyPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "车价输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscCertific==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscCertific":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "合格证号最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscFactoryNo==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscFactoryNo":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "厂牌型号最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscColor==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscColor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscPreview==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscPreview":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscImages==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscImages":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscMaintKm==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscMaintKm":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscCheckIn==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscCheckIn":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscAnnualPre==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscAnnualPre":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscAnnual==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscAnnual":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscAnnualInfo==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscAnnualInfo":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "具体年检信息最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscAnnualNext==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscAnnualNext":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscTiDate==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscTiDate":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscTiUnit==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscTiUnit":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscTiNo==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscTiNo":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "交强险保单最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("car_query.do",{exists:true,cscTiNo:el.value});
				if(exists && exists.length>0){
					if(${csCar.cscId==null} || exists[0].value!=$("#cscId").val())
						return "交强险保单已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscBiDate==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscBiDate":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscBiUnit==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscBiUnit":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscBiNo==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscBiNo":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "商业险保单最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("car_query.do",{exists:true,cscBiNo:el.value});
				if(exists && exists.length>0){
					if(${csCar.cscId==null} || exists[0].value!=$("#cscId").val())
						return "商业险保单已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscBiType==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscBiType":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "商业险类型最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscBiLimit==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscBiLimit":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "商业险金额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscBiCompany==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscBiCompany":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "保险公司最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscEditor==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscAddTime==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscRemark==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscMemo==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscMemo":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscOnPsd==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscOnPsd":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "上线密码最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscSetup==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscSetup":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscDeviceVesion==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscDeviceVesion":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscMqttFlag==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscMqttFlag":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "服务器标识最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscMask==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscMask":function(el){
				var bitValue = 0;
				$("input[ref='cscMask']").each(function(){
					if($(this).is(":checked"))
						bitValue|=$(this).val();
				});
				$("#cscMask").val(bitValue);
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscStatus==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择状态";
		}
		</s:if>	
		/* <s:if test="#request.CTRL.e.cscTerNo==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscTerNo":function(el){
			if(jQuery.trim(el.value)=="")
				return "请填写终端序列号";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscBindPlatform==true">
		${lz:set("haveEditable",true)}
		,"csCar.cscBindPlatform":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择车辆挂载平台";
		}
		</s:if>	 */
	},function(){
		${lz:set("注释","****表单提交自定义判断，阻止提交返回提示字符串即可****")}
		/******************************LAZY3Q_FORM_VALIDATE******************************/
		/******************************LAZY3Q_FORM_VALIDATE******************************/

	});		
});
</script>

<s:if test="false"></s:if>
<s:elseif test="false"></s:elseif>
<s:elseif test="#request.CTRL.title=='车辆年检'">
	<form class="form editform" ref="CsCar" id="carForm" name="carForm" action="${empty CTRL.action?"car_annual.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<div class="prompt">
					温馨提示：请仔细填写车辆相关信息，<span class="extrude">"*" 为必填选项。</span>
				</div>
				${lz:set("empty",lz:vacant(message))}
				<s:if test="#request.empty!=true">
				<br/>
				<center>
					<font color="red"><h4>${message}</h4></font>
				</center>
				<br/>
				</s:if>
				<input type="hidden" value="${editorpoint}" id="editorpoint" name="editorpoint" />	
				<input type="hidden" value="${entrypoint}" id="entrypoint" name="entrypoint" />
				<input type="hidden" value="${complete}" name="complete" id="complete" />
				<input type="hidden" value="${ids}" name="ids" id="ids" />		
				<input type="hidden" value="${edittype}" name="edittype" id="edittype" />
				<input type="hidden" value="${method}" name="method" id="method" />
				<input type="hidden" value="${lz:html(ctrl)}" id="ctrl" name="ctrl" />
				<input type="hidden" value="${csCarToken}" name="csCarToken" id="csCarToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************车牌号字段的输入框代码*****************")}
	${lz:set("注释","before$cscNumber和after$cscNumber变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscNumber}
	<dl class="cscNumber " major  ref="cscNumber" >
		<dt>车&nbsp;&nbsp;牌&nbsp;&nbsp;号:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCar$cscNumber!=null">${csCar$cscNumber}</s:if><s:else>
		 	<input type="text" class="input narrow" name="csCar.cscNumber" id="cscNumber"  value="${csCar.cscNumber}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆的车牌号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车牌号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscNumber">${csCar.cscNumber}</textarea>
		 		<span>
		 	
			 ${csCar.cscNumber$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscNumber}
	
	
	${lz:set("注释","*****************上次年检日期字段的输入框代码*****************")}
	${lz:set("注释","before$cscAnnualPre和after$cscAnnualPre变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscAnnualPre}
	<dl class="cscAnnualPre " minor  ref="cscAnnualPre" >
		<dt>上次年检日期:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csCar$cscAnnualPre!=null">${csCar$cscAnnualPre}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csCar.cscAnnualPre" id="cscAnnualPre"  value="<s:date name="csCar.cscAnnualPre" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆的上次年检日期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****上次年检日期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscAnnualPre">${csCar.cscAnnualPre}</textarea>
		 		<span>
		 	
			 ${csCar.cscAnnualPre$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscAnnualPre}
	
	
	${lz:set("注释","*****************实际年检日期字段的输入框代码*****************")}
	${lz:set("注释","before$cscAnnual和after$cscAnnual变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscAnnual}
	<dl class="cscAnnual " major  ref="cscAnnual" >
		<dt>实际年检日期:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csCar$cscAnnual!=null">${csCar$cscAnnual}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csCar.cscAnnual" id="cscAnnual"  value="<s:date name="csCar.cscAnnual" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆的实际年检日期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实际年检日期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscAnnual">${csCar.cscAnnual}</textarea>
		 		<span>
		 	
			 ${csCar.cscAnnual$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscAnnual}
	
	
	${lz:set("注释","*****************预计下次年检字段的输入框代码*****************")}
	${lz:set("注释","before$cscAnnualNext和after$cscAnnualNext变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscAnnualNext}
	<dl class="cscAnnualNext " major  ref="cscAnnualNext" >
		<dt>预计下次年检:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csCar$cscAnnualNext!=null">${csCar$cscAnnualNext}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csCar.cscAnnualNext" id="cscAnnualNext"  value="<s:date name="csCar.cscAnnualNext" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆的预计下次年检</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****预计下次年检字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscAnnualNext">${csCar.cscAnnualNext}</textarea>
		 		<span>
		 	
			 ${csCar.cscAnnualNext$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscAnnualNext}
	
	
	${lz:set("注释","*****************具体年检信息字段的输入框代码*****************")}
	${lz:set("注释","before$cscAnnualInfo和after$cscAnnualInfo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscAnnualInfo}
	<dl class="cscAnnualInfo " major  ref="cscAnnualInfo" style="width:98%;">
		<dt>具体年检信息:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csCar$cscAnnualInfo!=null">${csCar$cscAnnualInfo}</s:if><s:else>
		 	<textarea class="input wide"  id="cscAnnualInfo" name="csCar.cscAnnualInfo" rows="5">${csCar.cscAnnualInfo}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入车辆的具体年检信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****具体年检信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csCar.cscAnnualInfo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscAnnualInfo}
	
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<input type="hidden" value="${csCar.cscId}" name="csCar.cscId" id="cscId" />
					<button title="保存数据,关闭窗口,刷新原列表" class="button" type="submit" name="submiter" value="完成">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
						完成&nbsp;&nbsp;</button>
					<button title="不保存数据,关闭窗口,不刷新原列表" class="button" onclick="cancel();" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-multiply.png"/>
						取消&nbsp;&nbsp;</button>
				</center>
			</div>	
		</div>
		<div class="foot"></div>		
	</form>
</s:elseif>
<s:else>
	<form class="form editform" ref="CsCar" id="carForm" name="carForm" action="${empty CTRL.action?"car_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写车辆相关信息，<span class="extrude">"*" 为必填选项。</span>			
					<input type="checkbox" ${empty edittype?"checked='checked'":""} id="all"/>显示全部可编辑项
				</div>
				</s:if>
				${lz:set("empty",lz:vacant(message))}
				<s:if test="#request.empty!=true">
				<br/>
				<center>
					<font color="red"><h4>${message}</h4></font>
				</center>
				<br/>
				</s:if>
				<input type="hidden" value="${editorpoint}" id="editorpoint" name="editorpoint" />	
				<input type="hidden" value="${entrypoint}" id="entrypoint" name="entrypoint" />
				<input type="hidden" value="${complete}" name="complete" id="complete" />
				<input type="hidden" value="${ids}" name="ids" id="ids" />		
				<input type="hidden" value="${edittype}" name="edittype" id="edittype" />
				<input type="hidden" value="${method}" name="method" id="method" />
				<input type="hidden" value="${lz:html(ctrl)}" id="ctrl" name="ctrl" />
				<input type="hidden" value="${csCarToken}" name="csCarToken" id="csCarToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csCar.cscId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cscId和after$cscId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscId==true">
	${before$cscId}
	<dl class="cscId ${CTRL.e.cscId?"hidden":""}" major  ref="cscId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cscId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csCar$cscId!=null">${csCar$cscId}</s:if><s:else>
			<input type="hidden" value="${csCar.cscId}" name="csCar.cscId" id="cscId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscId">${csCar.cscId}</textarea>
		 		<span>
		 	
			 ${csCar.cscId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cscHost和after$cscHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscHost==true">
	${before$cscHost}
	<dl class="cscHost " major  ref="cscHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cscHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCar$cscHost!=null">${csCar$cscHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cscHost" name="csCar.cscHost">
		 				<option selected value="${csCar.cscHost}">
		 					${get:SrvHost(csCar.cscHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cscHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscHost">${csCar.cscHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csCar.cscHost}',{ctrl:{editable:false,visible:true}})">
			 ${csCar.cscHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscHost}
	</s:if>
	
	${lz:set("注释","*****************车牌号字段的输入框代码*****************")}
	${lz:set("注释","before$cscNumber和after$cscNumber变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscNumber==true">
	${before$cscNumber}
	<dl class="cscNumber " major  ref="cscNumber" >
		<dt>车&nbsp;&nbsp;牌&nbsp;&nbsp;号:</dt>
		<s:if test="#request.CTRL.e.cscNumber==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCar$cscNumber!=null">${csCar$cscNumber}</s:if><s:else>
		 	<input type="text" class="input narrow"  name="csCar.cscNumber" id="cscNumber"  value="${csCar.cscNumber}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入车辆的车牌号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车牌号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscNumber">${csCar.cscNumber}</textarea>
		 		<span>
		 	
			 ${csCar.cscNumber$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscNumber}
	</s:if>
	
	${lz:set("注释","*****************车牌名称字段的输入框代码*****************")}
	${lz:set("注释","before$cscCarNo和after$cscCarNo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscCarNo==true">
	${before$cscCarNo}
	<dl class="cscCarNo " major  ref="cscCarNo" >
		<dt>车牌名称:</dt>
		<s:if test="#request.CTRL.e.cscCarNo==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCar$cscCarNo!=null">${csCar$cscCarNo}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCar.cscCarNo" id="cscCarNo"  value="${csCar.cscCarNo}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入车辆的车牌名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车牌名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscCarNo">${csCar.cscCarNo}</textarea>
		 		<span>
		 	
			 ${csCar.cscCarNo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscCarNo}
	</s:if>
	
	${lz:set("注释","*****************车型字段的输入框代码*****************")}
	${lz:set("注释","before$cscModel和after$cscModel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscModel==true">
	${before$cscModel}
	<dl class="cscModel " major  ref="cscModel" >
		<dt>车　　型:</dt>
		<s:if test="#request.CTRL.e.cscModel==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCar$cscModel!=null">${csCar$cscModel}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/object/carmodel_query.do?size=-1" id="cscModel" name="csCar.cscModel">
		 				<option selected value="${csCar.cscModel}">
		 					${get:CsCarModel(csCar.cscModel).cscmName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscModel').val())==''){return;};window.href('${basePath}${proname}/object/carmodel_details.do?key='+$('#cscModel').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscModel">${csCar.cscModel}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/carmodel_details.do?key=${csCar.cscModel}',{ctrl:{editable:false,visible:true}})">
			 ${csCar.cscModel$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscModel}
	</s:if>
	
	${lz:set("注释","*****************网点字段的输入框代码*****************")}
	${lz:set("注释","before$cscOutlets和after$cscOutlets变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscOutlets==true">
	${before$cscOutlets}
	<dl class="cscOutlets " major  ref="cscOutlets" >
		<dt>网　　点:</dt>
		<s:if test="#request.CTRL.e.cscOutlets==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCar$cscOutlets!=null">${csCar$cscOutlets}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/object/outlets_tree.do?parent={param}&host={cscHost}&csoHost={cscHost}" id="cscOutlets" name="csCar.cscOutlets">
		 				<option selected value="${csCar.cscOutlets}">
		 					${get:CsOutlets(csCar.cscOutlets).csoName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscOutlets').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?key='+$('#cscOutlets').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****网点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscOutlets">${csCar.cscOutlets}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${csCar.cscOutlets}',{ctrl:{editable:false,visible:true}})">
			 ${csCar.cscOutlets$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscOutlets}
	</s:if>
	
	${lz:set("注释","*****************订单记录字段的输入框代码*****************")}
	${lz:set("注释","before$orders和after$orders变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.orders==true">
	${before$orders}
	<dl class="orders " minor  ref="orders" style="width:98%;">
		<dt>订单记录:</dt>
		<s:if test="#request.CTRL.e.orders==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csCar$orders!=null">${csCar$orders}</s:if><s:else>
		  		<s:if test="csCar.cscId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="orders" name="orders" frameborder='0' src="" url="${basePath}${proname}/service/order.do?inFrame=true&canQuery=false&all=true&csoCar=${csCar.cscId}&ctrl={queryable:false,fields:{csoCar:{visible:true,editable:false,defaultValue:${csCar.cscId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#orders').attr('src',$('#orders').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑订单记录
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单记录字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csCar.cscId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="orders" name="orders" frameborder='0' src="" url="${basePath}${proname}/service/order.do?inFrame=true&canQuery=false&all=true&csoCar=${csCar.cscId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#orders').attr('src',$('#orders').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$orders}
	</s:if>
	
	${lz:set("注释","*****************违章记录字段的输入框代码*****************")}
	${lz:set("注释","before$violats和after$violats变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.violats==true">
	${before$violats}
	<dl class="violats " minor  ref="violats" style="width:98%;">
		<dt>违章记录:</dt>
		<s:if test="#request.CTRL.e.violats==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csCar$violats!=null">${csCar$violats}</s:if><s:else>
		  		<s:if test="csCar.cscId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="violats" name="violats" frameborder='0' src="" url="${basePath}${proname}/service/violat.do?inFrame=true&canQuery=false&all=true&csvCar=${csCar.cscId}&ctrl={queryable:false,fields:{csvCar:{visible:true,editable:false,defaultValue:${csCar.cscId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#violats').attr('src',$('#violats').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑违章记录
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****违章记录字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csCar.cscId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="violats" name="violats" frameborder='0' src="" url="${basePath}${proname}/service/violat.do?inFrame=true&canQuery=false&all=true&csvCar=${csCar.cscId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#violats').attr('src',$('#violats').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$violats}
	</s:if>
	
	${lz:set("注释","*****************事故记录字段的输入框代码*****************")}
	${lz:set("注释","before$troubles和after$troubles变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.troubles==true">
	${before$troubles}
	<dl class="troubles " minor  ref="troubles" style="width:98%;">
		<dt>事故记录:</dt>
		<s:if test="#request.CTRL.e.troubles==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csCar$troubles!=null">${csCar$troubles}</s:if><s:else>
		  		<s:if test="csCar.cscId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="troubles" name="troubles" frameborder='0' src="" url="${basePath}${proname}/service/trouble.do?inFrame=true&canQuery=false&all=true&cstCar=${csCar.cscId}&ctrl={queryable:false,fields:{cstCar:{visible:true,editable:false,defaultValue:${csCar.cscId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#troubles').attr('src',$('#troubles').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑事故记录
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****事故记录字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csCar.cscId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="troubles" name="troubles" frameborder='0' src="" url="${basePath}${proname}/service/trouble.do?inFrame=true&canQuery=false&all=true&cstCar=${csCar.cscId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#troubles').attr('src',$('#troubles').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$troubles}
	</s:if>
	
	${lz:set("注释","*****************维修记录字段的输入框代码*****************")}
	${lz:set("注释","before$repairs和after$repairs变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.repairs==true">
	${before$repairs}
	<dl class="repairs " minor  ref="repairs" style="width:98%;">
		<dt>维修记录:</dt>
		<s:if test="#request.CTRL.e.repairs==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csCar$repairs!=null">${csCar$repairs}</s:if><s:else>
		  		<s:if test="csCar.cscId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="repairs" name="repairs" frameborder='0' src="" url="${basePath}${proname}/service/repair.do?inFrame=true&canQuery=false&all=true&csrCar=${csCar.cscId}&ctrl={queryable:false,fields:{csrCar:{visible:true,editable:false,defaultValue:${csCar.cscId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#repairs').attr('src',$('#repairs').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑维修记录
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****维修记录字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csCar.cscId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="repairs" name="repairs" frameborder='0' src="" url="${basePath}${proname}/service/repair.do?inFrame=true&canQuery=false&all=true&csrCar=${csCar.cscId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#repairs').attr('src',$('#repairs').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$repairs}
	</s:if>
	
	${lz:set("注释","*****************保养记录字段的输入框代码*****************")}
	${lz:set("注释","before$maintains和after$maintains变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.maintains==true">
	${before$maintains}
	<dl class="maintains " minor  ref="maintains" style="width:98%;">
		<dt>保养记录:</dt>
		<s:if test="#request.CTRL.e.maintains==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csCar$maintains!=null">${csCar$maintains}</s:if><s:else>
		  		<s:if test="csCar.cscId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="maintains" name="maintains" frameborder='0' src="" url="${basePath}${proname}/service/maintain.do?inFrame=true&canQuery=false&all=true&csmNumber=${csCar.cscId}&ctrl={queryable:false,fields:{csmNumber:{visible:true,editable:false,defaultValue:${csCar.cscId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#maintains').attr('src',$('#maintains').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑保养记录
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****保养记录字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csCar.cscId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="maintains" name="maintains" frameborder='0' src="" url="${basePath}${proname}/service/maintain.do?inFrame=true&canQuery=false&all=true&csmNumber=${csCar.cscId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#maintains').attr('src',$('#maintains').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$maintains}
	</s:if>
	
	${lz:set("注释","*****************上下线记录字段的输入框代码*****************")}
	${lz:set("注释","before$onlines和after$onlines变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.onlines==true">
	${before$onlines}
	<dl class="onlines " minor  ref="onlines" style="width:98%;">
		<dt>上下线记录:</dt>
		<s:if test="#request.CTRL.e.onlines==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csCar$onlines!=null">${csCar$onlines}</s:if><s:else>
		  		<s:if test="csCar.cscId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="onlines" name="onlines" frameborder='0' src="" url="${basePath}${proname}/object/car/caron.do?inFrame=true&canQuery=false&all=true&cscoCar=${csCar.cscId}&ctrl={queryable:false,fields:{cscoCar:{visible:true,editable:false,defaultValue:${csCar.cscId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#onlines').attr('src',$('#onlines').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑上下线记录
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****上下线记录字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csCar.cscId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="onlines" name="onlines" frameborder='0' src="" url="${basePath}${proname}/object/car/caron.do?inFrame=true&canQuery=false&all=true&cscoCar=${csCar.cscId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#onlines').attr('src',$('#onlines').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$onlines}
	</s:if>
	
	${lz:set("注释","*****************供应商字段的输入框代码*****************")}
	${lz:set("注释","before$cscProvid和after$cscProvid变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscProvid==true">
	${before$cscProvid}
	<dl class="cscProvid " major  ref="cscProvid" >
		<dt>供&nbsp;&nbsp;应&nbsp;&nbsp;商:</dt>
		<s:if test="#request.CTRL.e.cscProvid==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCar$cscProvid!=null">${csCar$cscProvid}</s:if><s:else>
		 	<select class="narrow" id="cscProvid" name="csCar.cscProvid">
		 		<option value="">请选择</option>
 					${lz:set("items", lz:query("ccclubs_system","select * from cs_provid where 1=1 ") )}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.cspId}" ${csCar.cscProvid==item.cspId?"selected":""}>${item.cspName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscProvid').val())==''){return;};window.href('${basePath}${proname}/configurator/provid_details.do?key='+$('#cscProvid').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆的供应商</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****供应商字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscProvid">${csCar.cscProvid}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/provid_details.do?key=${csCar.cscProvid}',{ctrl:{editable:false,visible:true}})">
			 ${csCar.cscProvid$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscProvid}
	</s:if>
	
	${lz:set("注释","*****************折扣字段的输入框代码*****************")}
	${lz:set("注释","before$cscRebate和after$cscRebate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscRebate==true">
	${before$cscRebate}
	<dl class="cscRebate " major  ref="cscRebate" >
		<dt>折　　扣:</dt>
		<s:if test="#request.CTRL.e.cscRebate==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csCar$cscRebate!=null">${csCar$cscRebate}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csCar.cscRebate" id="cscRebate"  value="${csCar.cscRebate}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>0到1,比如0.75表示七五折</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****折扣字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscRebate">${csCar.cscRebate}</textarea>
		 		<span>
		 	
			 ${csCar.cscRebate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscRebate}
	</s:if>
	
	${lz:set("注释","*****************广告车？字段的输入框代码*****************")}
	${lz:set("注释","before$cscIsAd和after$cscIsAd变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscIsAd==true">
	${before$cscIsAd}
	<dl class="cscIsAd " major  ref="cscIsAd" >
		<dt>广告车？:</dt>
		<s:if test="#request.CTRL.e.cscIsAd==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCar$cscIsAd!=null">${csCar$cscIsAd}</s:if><s:else>
		 	<select class="narrow" id="cscIsAd" name="csCar.cscIsAd">
		 		<option value="">请选择</option>
				<option value="true" ${csCar.cscIsAd==true?"selected":""}>是</option>
				<option value="false" ${csCar.cscIsAd==false?"selected":""}>否</option>		 		
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆的广告车？</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****广告车？字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscIsAd">${csCar.cscIsAd}</textarea>
		 		<span>
		 	
			 ${csCar.cscIsAd$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscIsAd}
	</s:if>
	
	${lz:set("注释","*****************经度字段的输入框代码*****************")}
	${lz:set("注释","before$cscLongitude和after$cscLongitude变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscLongitude==true">
	${before$cscLongitude}
	<dl class="cscLongitude " minor  ref="cscLongitude" >
		<dt>经　　度:</dt>
		<s:if test="#request.CTRL.e.cscLongitude==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csCar$cscLongitude!=null">${csCar$cscLongitude}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csCar.cscLongitude" id="cscLongitude"  value="${csCar.cscLongitude}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆的经度</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****经度字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscLongitude">${csCar.cscLongitude}</textarea>
		 		<span>
		 	
			 ${csCar.cscLongitude$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscLongitude}
	</s:if>
	
	${lz:set("注释","*****************纬度字段的输入框代码*****************")}
	${lz:set("注释","before$cscLatitude和after$cscLatitude变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscLatitude==true">
	${before$cscLatitude}
	<dl class="cscLatitude " minor  ref="cscLatitude" >
		<dt>纬　　度:</dt>
		<s:if test="#request.CTRL.e.cscLatitude==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csCar$cscLatitude!=null">${csCar$cscLatitude}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csCar.cscLatitude" id="cscLatitude"  value="${csCar.cscLatitude}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆的纬度</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****纬度字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscLatitude">${csCar.cscLatitude}</textarea>
		 		<span>
		 	
			 ${csCar.cscLatitude$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscLatitude}
	</s:if>
	
	${lz:set("注释","*****************手机号码字段的输入框代码*****************")}
	${lz:set("注释","before$cscMobile和after$cscMobile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscMobile==true">
	${before$cscMobile}
	<dl class="cscMobile " minor  ref="cscMobile" >
		<dt>手机号码:</dt>
		<s:if test="#request.CTRL.e.cscMobile==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCar$cscMobile!=null">${csCar$cscMobile}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCar.cscMobile" id="cscMobile"  value="${csCar.cscMobile}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆的手机号码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****手机号码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscMobile">${csCar.cscMobile}</textarea>
		 		<span>
		 	
			 ${csCar.cscMobile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscMobile}
	</s:if>
	
	${lz:set("注释","*****************服务密码字段的输入框代码*****************")}
	${lz:set("注释","before$cscPassword和after$cscPassword变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscPassword==true">
	${before$cscPassword}
	<dl class="cscPassword " minor  ref="cscPassword" >
		<dt>服务密码:</dt>
		<s:if test="#request.CTRL.e.cscPassword==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCar$cscPassword!=null">${csCar$cscPassword}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCar.cscPassword" id="cscPassword"  value="${csCar.cscPassword}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆的服务密码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****服务密码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscPassword">${csCar.cscPassword}</textarea>
		 		<span>
		 	
			 ${csCar.cscPassword$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscPassword}
	</s:if>
	
	${lz:set("注释","*****************加油卡号字段的输入框代码*****************")}
	${lz:set("注释","before$cscOilCard和after$cscOilCard变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscOilCard==true">
	${before$cscOilCard}
	<dl class="cscOilCard " minor  ref="cscOilCard" >
		<dt>加油卡号:</dt>
		<s:if test="#request.CTRL.e.cscOilCard==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCar$cscOilCard!=null">${csCar$cscOilCard}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCar.cscOilCard" id="cscOilCard"  value="${csCar.cscOilCard}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆的加油卡号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****加油卡号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscOilCard">${csCar.cscOilCard}</textarea>
		 		<span>
		 	
			 ${csCar.cscOilCard$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscOilCard}
	</s:if>
	
	${lz:set("注释","*****************车架号字段的输入框代码*****************")}
	${lz:set("注释","before$cscVin和after$cscVin变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscVin==true">
	${before$cscVin}
	<dl class="cscVin " major  ref="cscVin" >
		<dt>车&nbsp;&nbsp;架&nbsp;&nbsp;号:</dt>
		<s:if test="#request.CTRL.e.cscVin==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCar$cscVin!=null">${csCar$cscVin}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCar.cscVin" id="cscVin"  value="${csCar.cscVin}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆的车架号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车架号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscVin">${csCar.cscVin}</textarea>
		 		<span>
		 	
			 ${csCar.cscVin$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscVin}
	</s:if>
	
	${lz:set("注释","*****************发动机号字段的输入框代码*****************")}
	${lz:set("注释","before$cscEngineNo和after$cscEngineNo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscEngineNo==true">
	${before$cscEngineNo}
	<dl class="cscEngineNo " major  ref="cscEngineNo" >
		<dt>发动机号:</dt>
		<s:if test="#request.CTRL.e.cscEngineNo==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCar$cscEngineNo!=null">${csCar$cscEngineNo}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCar.cscEngineNo" id="cscEngineNo"  value="${csCar.cscEngineNo}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆的发动机号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****发动机号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscEngineNo">${csCar.cscEngineNo}</textarea>
		 		<span>
		 	
			 ${csCar.cscEngineNo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscEngineNo}
	</s:if>
	
	${lz:set("注释","*****************购车日字段的输入框代码*****************")}
	${lz:set("注释","before$cscBuyDate和after$cscBuyDate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscBuyDate==true">
	${before$cscBuyDate}
	<dl class="cscBuyDate " minor  ref="cscBuyDate" >
		<dt>购&nbsp;&nbsp;车&nbsp;&nbsp;日:</dt>
		<s:if test="#request.CTRL.e.cscBuyDate==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csCar$cscBuyDate!=null">${csCar$cscBuyDate}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csCar.cscBuyDate" id="cscBuyDate"  value="<s:date name="csCar.cscBuyDate" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆的购车日</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****购车日字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscBuyDate">${csCar.cscBuyDate}</textarea>
		 		<span>
		 	
			 ${csCar.cscBuyDate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscBuyDate}
	</s:if>
	
	${lz:set("注释","*****************合同号字段的输入框代码*****************")}
	${lz:set("注释","before$cscBargainNo和after$cscBargainNo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscBargainNo==true">
	${before$cscBargainNo}
	<dl class="cscBargainNo " minor  ref="cscBargainNo" >
		<dt>合&nbsp;&nbsp;同&nbsp;&nbsp;号:</dt>
		<s:if test="#request.CTRL.e.cscBargainNo==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCar$cscBargainNo!=null">${csCar$cscBargainNo}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCar.cscBargainNo" id="cscBargainNo"  value="${csCar.cscBargainNo}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆的合同号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscBargainNo">${csCar.cscBargainNo}</textarea>
		 		<span>
		 	
			 ${csCar.cscBargainNo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscBargainNo}
	</s:if>
	
	${lz:set("注释","*****************购置税字段的输入框代码*****************")}
	${lz:set("注释","before$cscTaxPrice和after$cscTaxPrice变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscTaxPrice==true">
	${before$cscTaxPrice}
	<dl class="cscTaxPrice " minor  ref="cscTaxPrice" >
		<dt>购&nbsp;&nbsp;置&nbsp;&nbsp;税:</dt>
		<s:if test="#request.CTRL.e.cscTaxPrice==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csCar$cscTaxPrice!=null">${csCar$cscTaxPrice}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csCar.cscTaxPrice" id="cscTaxPrice"  value="${csCar.cscTaxPrice}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆的购置税</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****购置税字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscTaxPrice">${csCar.cscTaxPrice}</textarea>
		 		<span>
		 	
			 ${csCar.cscTaxPrice$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscTaxPrice}
	</s:if>
	
	${lz:set("注释","*****************车价字段的输入框代码*****************")}
	${lz:set("注释","before$cscBuyPrice和after$cscBuyPrice变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscBuyPrice==true">
	${before$cscBuyPrice}
	<dl class="cscBuyPrice " minor  ref="cscBuyPrice" >
		<dt>车　　价:</dt>
		<s:if test="#request.CTRL.e.cscBuyPrice==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csCar$cscBuyPrice!=null">${csCar$cscBuyPrice}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csCar.cscBuyPrice" id="cscBuyPrice"  value="${csCar.cscBuyPrice}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆的车价</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车价字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscBuyPrice">${csCar.cscBuyPrice}</textarea>
		 		<span>
		 	
			 ${csCar.cscBuyPrice$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscBuyPrice}
	</s:if>
	
	${lz:set("注释","*****************合格证号字段的输入框代码*****************")}
	${lz:set("注释","before$cscCertific和after$cscCertific变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscCertific==true">
	${before$cscCertific}
	<dl class="cscCertific " minor  ref="cscCertific" >
		<dt>合格证号:</dt>
		<s:if test="#request.CTRL.e.cscCertific==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCar$cscCertific!=null">${csCar$cscCertific}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCar.cscCertific" id="cscCertific"  value="${csCar.cscCertific}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆的合格证号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合格证号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscCertific">${csCar.cscCertific}</textarea>
		 		<span>
		 	
			 ${csCar.cscCertific$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscCertific}
	</s:if>
	
	${lz:set("注释","*****************厂牌型号字段的输入框代码*****************")}
	${lz:set("注释","before$cscFactoryNo和after$cscFactoryNo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscFactoryNo==true">
	${before$cscFactoryNo}
	<dl class="cscFactoryNo " minor  ref="cscFactoryNo" >
		<dt>厂牌型号:</dt>
		<s:if test="#request.CTRL.e.cscFactoryNo==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCar$cscFactoryNo!=null">${csCar$cscFactoryNo}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCar.cscFactoryNo" id="cscFactoryNo"  value="${csCar.cscFactoryNo}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆的厂牌型号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****厂牌型号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscFactoryNo">${csCar.cscFactoryNo}</textarea>
		 		<span>
		 	
			 ${csCar.cscFactoryNo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscFactoryNo}
	</s:if>
	
	${lz:set("注释","*****************颜色字段的输入框代码*****************")}
	${lz:set("注释","before$cscColor和after$cscColor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscColor==true">
	${before$cscColor}
	<dl class="cscColor " minor  ref="cscColor" >
		<dt>颜　　色:</dt>
		<s:if test="#request.CTRL.e.cscColor==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCar$cscColor!=null">${csCar$cscColor}</s:if><s:else>
		 	<select class="narrow" id="cscColor" name="csCar.cscColor">
		 		<option value="">请选择</option>
				<option value="0" ${csCar.cscColor==0?"selected":""}>黑色</option>
				<option value="1" ${csCar.cscColor==1?"selected":""}>白色</option>
				<option value="2" ${csCar.cscColor==2?"selected":""}>红色</option>
				<option value="3" ${csCar.cscColor==3?"selected":""}>绿色</option>
				<option value="4" ${csCar.cscColor==4?"selected":""}>黄色</option>
				<option value="5" ${csCar.cscColor==5?"selected":""}>橙色</option>
				<option value="6" ${csCar.cscColor==6?"selected":""}>银色</option>
				<option value="7" ${csCar.cscColor==7?"selected":""}>蓝色</option>
				<option value="8" ${csCar.cscColor==8?"selected":""}>深蓝</option>
				<option value="9" ${csCar.cscColor==9?"selected":""}>深灰</option>
				<option value="10" ${csCar.cscColor==10?"selected":""}>深红</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆的颜色</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****颜色字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscColor">${csCar.cscColor}</textarea>
		 		<span>
		 	
			 ${csCar.cscColor$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscColor}
	</s:if>
	
	${lz:set("注释","*****************车辆主图字段的输入框代码*****************")}
	${lz:set("注释","before$cscPreview和after$cscPreview变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscPreview==true">
	${before$cscPreview}
	<dl class="cscPreview " minor  ref="cscPreview" style="width:98%;">
		<dt>车辆主图:</dt>
		<s:if test="#request.CTRL.e.cscPreview==true">
		${lz:set("haveEditable",true)}
		<dd input="image">
		<s:if test="#request.csCar$cscPreview!=null">${csCar$cscPreview}</s:if><s:else>
		    <input type="text" class="input" maxlength="128" size="32" name="csCar.cscPreview" id="cscPreview"  value="${csCar.cscPreview}"/>
			<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#cscPreview').val(url)}})" class="button">设置图片</button>
			<button type="button" onclick="$('#cscPreview').val('')" class="button">删除图片</button>
			<button type="button" onclick="$.thumb({url:$('#cscPreview').val()})" class="button">查看图片</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>图片建议尺寸500*500像素（比例）</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车辆主图字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscPreview">${csCar.cscPreview}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(csCar.cscPreview))}
		 	<s:if test="#request.isVacant==false">
		 	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="${csCar.cscPreview}"/>
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscPreview}
	</s:if>
	
	${lz:set("注释","*****************照片列表字段的输入框代码*****************")}
	${lz:set("注释","before$cscImages和after$cscImages变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscImages==true">
	${before$cscImages}
	<dl class="cscImages " minor  ref="cscImages" style="width:98%;">
		<dt>照片列表:</dt>
		<s:if test="#request.CTRL.e.cscImages==true">
		${lz:set("haveEditable",true)}
		<dd input="imagelist">
		<s:if test="#request.csCar$cscImages!=null">${csCar$cscImages}</s:if><s:else>
		    <div id="cscImages" class="wide input imagelist">
			<s:generator val="csCar.cscImages" separator=",">
				<s:iterator id="item">
					${lz:set("isVacant",lz:vacant(item))}
		 			<s:if test="#request.isVacant==false">
					<div><a href="${item}" target="_blank"><img src="${item}"/><input type="hidden" name="csCar.cscImages" value="${item}"/></a><span onclick="$(this).parent().remove();">x</span></div>
					</s:if>
				</s:iterator>
			</s:generator>
			</div>
			<button type="button" onclick="$.upload({type:'img',count:10,callback:function(url){if(url)$('#cscImages').append('<div><a href=&quot;'+url+'&quot; target=&quot;_blank&quot;><img src=&quot;'+url+'&quot;/><input type=&quot;hidden&quot; name=&quot;csCar.cscImages&quot; value=&quot;'+url+'&quot;/></a><span onclick=&quot;$(this).parent().remove();&quot;>x</span></div>');}})" class="button">添加</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传车辆的照片列表</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****照片列表字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscImages">${csCar.cscImages}</textarea>
		 		<span>
		 	 <s:generator val="csCar.cscImages" separator=",">
		 	 	<s:iterator id="item">
					${lz:set("isVacant",lz:vacant(item))}
		 			<s:if test="#request.isVacant==false">
					<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="<s:property/>"/>
					</s:if>
				</s:iterator>
			 </s:generator>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscImages}
	</s:if>
	
	${lz:set("注释","*****************保养公里字段的输入框代码*****************")}
	${lz:set("注释","before$cscMaintKm和after$cscMaintKm变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscMaintKm==true">
	${before$cscMaintKm}
	<dl class="cscMaintKm " minor  ref="cscMaintKm" >
		<dt>保养公里:</dt>
		<s:if test="#request.CTRL.e.cscMaintKm==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csCar$cscMaintKm!=null">${csCar$cscMaintKm}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csCar.cscMaintKm" id="cscMaintKm"  value="${csCar.cscMaintKm}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆的保养公里</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****保养公里字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscMaintKm">${csCar.cscMaintKm}</textarea>
		 		<span>
		 	
			 ${csCar.cscMaintKm$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscMaintKm}
	</s:if>
	
	${lz:set("注释","*****************初次登记字段的输入框代码*****************")}
	${lz:set("注释","before$cscCheckIn和after$cscCheckIn变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscCheckIn==true">
	${before$cscCheckIn}
	<dl class="cscCheckIn " minor  ref="cscCheckIn" >
		<dt>初次登记:</dt>
		<s:if test="#request.CTRL.e.cscCheckIn==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csCar$cscCheckIn!=null">${csCar$cscCheckIn}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csCar.cscCheckIn" id="cscCheckIn"  value="<s:date name="csCar.cscCheckIn" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆的初次登记</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****初次登记字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscCheckIn">${csCar.cscCheckIn}</textarea>
		 		<span>
		 	
			 ${csCar.cscCheckIn$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscCheckIn}
	</s:if>
	
	${lz:set("注释","*****************上次年检日期字段的输入框代码*****************")}
	${lz:set("注释","before$cscAnnualPre和after$cscAnnualPre变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscAnnualPre==true">
	${before$cscAnnualPre}
	<dl class="cscAnnualPre " minor  ref="cscAnnualPre" >
		<dt>上次年检日期:</dt>
		<s:if test="#request.CTRL.e.cscAnnualPre==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csCar$cscAnnualPre!=null">${csCar$cscAnnualPre}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csCar.cscAnnualPre" id="cscAnnualPre"  value="<s:date name="csCar.cscAnnualPre" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆的上次年检日期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****上次年检日期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscAnnualPre">${csCar.cscAnnualPre}</textarea>
		 		<span>
		 	
			 ${csCar.cscAnnualPre$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscAnnualPre}
	</s:if>
	
	${lz:set("注释","*****************实际年检日期字段的输入框代码*****************")}
	${lz:set("注释","before$cscAnnual和after$cscAnnual变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscAnnual==true">
	${before$cscAnnual}
	<dl class="cscAnnual " minor  ref="cscAnnual" >
		<dt>实际年检日期:</dt>
		<s:if test="#request.CTRL.e.cscAnnual==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csCar$cscAnnual!=null">${csCar$cscAnnual}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csCar.cscAnnual" id="cscAnnual"  value="<s:date name="csCar.cscAnnual" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆的实际年检日期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实际年检日期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscAnnual">${csCar.cscAnnual}</textarea>
		 		<span>
		 	
			 ${csCar.cscAnnual$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscAnnual}
	</s:if>
	
	${lz:set("注释","*****************具体年检信息字段的输入框代码*****************")}
	${lz:set("注释","before$cscAnnualInfo和after$cscAnnualInfo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscAnnualInfo==true">
	${before$cscAnnualInfo}
	<dl class="cscAnnualInfo " minor  ref="cscAnnualInfo" style="width:98%;">
		<dt>具体年检信息:</dt>
		<s:if test="#request.CTRL.e.cscAnnualInfo==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csCar$cscAnnualInfo!=null">${csCar$cscAnnualInfo}</s:if><s:else>
		 	<textarea class="input wide"  id="cscAnnualInfo" name="csCar.cscAnnualInfo" rows="5">${csCar.cscAnnualInfo}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆的具体年检信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****具体年检信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csCar.cscAnnualInfo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscAnnualInfo}
	</s:if>
	
	${lz:set("注释","*****************预计下次年检字段的输入框代码*****************")}
	${lz:set("注释","before$cscAnnualNext和after$cscAnnualNext变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscAnnualNext==true">
	${before$cscAnnualNext}
	<dl class="cscAnnualNext " major  ref="cscAnnualNext" >
		<dt>预计下次年检:</dt>
		<s:if test="#request.CTRL.e.cscAnnualNext==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csCar$cscAnnualNext!=null">${csCar$cscAnnualNext}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csCar.cscAnnualNext" id="cscAnnualNext"  value="<s:date name="csCar.cscAnnualNext" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆的预计下次年检</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****预计下次年检字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscAnnualNext">${csCar.cscAnnualNext}</textarea>
		 		<span>
		 	
			 ${csCar.cscAnnualNext$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscAnnualNext}
	</s:if>
	
	${lz:set("注释","*****************交强险投保字段的输入框代码*****************")}
	${lz:set("注释","before$cscTiDate和after$cscTiDate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscTiDate==true">
	${before$cscTiDate}
	<dl class="cscTiDate " minor  ref="cscTiDate" >
		<dt>交强险投保:</dt>
		<s:if test="#request.CTRL.e.cscTiDate==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csCar$cscTiDate!=null">${csCar$cscTiDate}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csCar.cscTiDate" id="cscTiDate"  value="<s:date name="csCar.cscTiDate" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆的交强险投保</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****交强险投保字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscTiDate">${csCar.cscTiDate}</textarea>
		 		<span>
		 	
			 ${csCar.cscTiDate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscTiDate}
	</s:if>
	
	${lz:set("注释","*****************交强险到期字段的输入框代码*****************")}
	${lz:set("注释","before$cscTiUnit和after$cscTiUnit变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscTiUnit==true">
	${before$cscTiUnit}
	<dl class="cscTiUnit " major  ref="cscTiUnit" >
		<dt>交强险到期:</dt>
		<s:if test="#request.CTRL.e.cscTiUnit==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csCar$cscTiUnit!=null">${csCar$cscTiUnit}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csCar.cscTiUnit" id="cscTiUnit"  value="<s:date name="csCar.cscTiUnit" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆的交强险到期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****交强险到期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscTiUnit">${csCar.cscTiUnit}</textarea>
		 		<span>
		 	
			 ${csCar.cscTiUnit$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscTiUnit}
	</s:if>
	
	${lz:set("注释","*****************交强险保单字段的输入框代码*****************")}
	${lz:set("注释","before$cscTiNo和after$cscTiNo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscTiNo==true">
	${before$cscTiNo}
	<dl class="cscTiNo " minor  ref="cscTiNo" >
		<dt>交强险保单:</dt>
		<s:if test="#request.CTRL.e.cscTiNo==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCar$cscTiNo!=null">${csCar$cscTiNo}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCar.cscTiNo" id="cscTiNo"  value="${csCar.cscTiNo}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆的交强险保单</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****交强险保单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscTiNo">${csCar.cscTiNo}</textarea>
		 		<span>
		 	
			 ${csCar.cscTiNo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscTiNo}
	</s:if>
	
	${lz:set("注释","*****************商业险投保字段的输入框代码*****************")}
	${lz:set("注释","before$cscBiDate和after$cscBiDate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscBiDate==true">
	${before$cscBiDate}
	<dl class="cscBiDate " minor  ref="cscBiDate" >
		<dt>商业险投保:</dt>
		<s:if test="#request.CTRL.e.cscBiDate==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csCar$cscBiDate!=null">${csCar$cscBiDate}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csCar.cscBiDate" id="cscBiDate"  value="<s:date name="csCar.cscBiDate" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆的商业险投保</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****商业险投保字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscBiDate">${csCar.cscBiDate}</textarea>
		 		<span>
		 	
			 ${csCar.cscBiDate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscBiDate}
	</s:if>
	
	${lz:set("注释","*****************商业险到期字段的输入框代码*****************")}
	${lz:set("注释","before$cscBiUnit和after$cscBiUnit变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscBiUnit==true">
	${before$cscBiUnit}
	<dl class="cscBiUnit " minor  ref="cscBiUnit" >
		<dt>商业险到期:</dt>
		<s:if test="#request.CTRL.e.cscBiUnit==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csCar$cscBiUnit!=null">${csCar$cscBiUnit}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csCar.cscBiUnit" id="cscBiUnit"  value="<s:date name="csCar.cscBiUnit" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆的商业险到期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****商业险到期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscBiUnit">${csCar.cscBiUnit}</textarea>
		 		<span>
		 	
			 ${csCar.cscBiUnit$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscBiUnit}
	</s:if>
	
	${lz:set("注释","*****************商业险保单字段的输入框代码*****************")}
	${lz:set("注释","before$cscBiNo和after$cscBiNo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscBiNo==true">
	${before$cscBiNo}
	<dl class="cscBiNo " minor  ref="cscBiNo" >
		<dt>商业险保单:</dt>
		<s:if test="#request.CTRL.e.cscBiNo==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCar$cscBiNo!=null">${csCar$cscBiNo}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCar.cscBiNo" id="cscBiNo"  value="${csCar.cscBiNo}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆的商业险保单</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****商业险保单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscBiNo">${csCar.cscBiNo}</textarea>
		 		<span>
		 	
			 ${csCar.cscBiNo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscBiNo}
	</s:if>
	
	${lz:set("注释","*****************商业险类型字段的输入框代码*****************")}
	${lz:set("注释","before$cscBiType和after$cscBiType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscBiType==true">
	${before$cscBiType}
	<dl class="cscBiType " minor  ref="cscBiType" style="width:98%;">
		<dt>商业险类型:</dt>
		<s:if test="#request.CTRL.e.cscBiType==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCar$cscBiType!=null">${csCar$cscBiType}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csCar.cscBiType" id="cscBiType"  value="${csCar.cscBiType}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆的商业险类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****商业险类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscBiType">${csCar.cscBiType}</textarea>
		 		<span>
		 	
			 ${csCar.cscBiType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscBiType}
	</s:if>
	
	${lz:set("注释","*****************商业险金额字段的输入框代码*****************")}
	${lz:set("注释","before$cscBiLimit和after$cscBiLimit变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscBiLimit==true">
	${before$cscBiLimit}
	<dl class="cscBiLimit " minor  ref="cscBiLimit" >
		<dt>商业险金额:</dt>
		<s:if test="#request.CTRL.e.cscBiLimit==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csCar$cscBiLimit!=null">${csCar$cscBiLimit}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csCar.cscBiLimit" id="cscBiLimit"  value="${csCar.cscBiLimit}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆的商业险金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****商业险金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscBiLimit">${csCar.cscBiLimit}</textarea>
		 		<span>
		 	
			 ${csCar.cscBiLimit$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscBiLimit}
	</s:if>
	
	${lz:set("注释","*****************保险公司字段的输入框代码*****************")}
	${lz:set("注释","before$cscBiCompany和after$cscBiCompany变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscBiCompany==true">
	${before$cscBiCompany}
	<dl class="cscBiCompany " major  ref="cscBiCompany" >
		<dt>保险公司:</dt>
		<s:if test="#request.CTRL.e.cscBiCompany==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCar$cscBiCompany!=null">${csCar$cscBiCompany}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCar.cscBiCompany" id="cscBiCompany"  value="${csCar.cscBiCompany}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆的保险公司</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****保险公司字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscBiCompany">${csCar.cscBiCompany}</textarea>
		 		<span>
		 	
			 ${csCar.cscBiCompany$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscBiCompany}
	</s:if>
	
	${lz:set("注释","*****************修改人字段的输入框代码*****************")}
	${lz:set("注释","before$cscEditor和after$cscEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscEditor==true">
	${before$cscEditor}
	<dl class="cscEditor " minor  ref="cscEditor" >
		<dt>修&nbsp;&nbsp;改&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.cscEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCar$cscEditor!=null">${csCar$cscEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cscEditor" name="csCar.cscEditor">
		 				<option selected value="${csCar.cscEditor}">
		 					${get:SrvUser(csCar.cscEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cscEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscEditor">${csCar.cscEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csCar.cscEditor}',{ctrl:{editable:false,visible:true}})">
			 ${csCar.cscEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscEditor}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscUpdateTime和after$cscUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscUpdateTime==true">
	${before$cscUpdateTime}
	<dl class="cscUpdateTime " major  ref="cscUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cscUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCar$cscUpdateTime!=null">${csCar$cscUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCar.cscUpdateTime" id="cscUpdateTime"  value="<s:date name="csCar.cscUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscUpdateTime">${csCar.cscUpdateTime}</textarea>
		 		<span>
		 	
			 ${csCar.cscUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscAddTime和after$cscAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscAddTime==true">
	${before$cscAddTime}
	<dl class="cscAddTime " major  ref="cscAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cscAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCar$cscAddTime!=null">${csCar$cscAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCar.cscAddTime" id="cscAddTime"  value="<s:date name="csCar.cscAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscAddTime">${csCar.cscAddTime}</textarea>
		 		<span>
		 	
			 ${csCar.cscAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscAddTime}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$cscRemark和after$cscRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscRemark==true">
	${before$cscRemark}
	<dl class="cscRemark " major  ref="cscRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.cscRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csCar$cscRemark!=null">${csCar$cscRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="cscRemark" name="csCar.cscRemark" rows="5">${csCar.cscRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csCar.cscRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscRemark}
	</s:if>
	
	${lz:set("注释","*****************备忘信息字段的输入框代码*****************")}
	${lz:set("注释","before$cscMemo和after$cscMemo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscMemo==true">
	${before$cscMemo}
	<dl class="cscMemo " minor  ref="cscMemo" style="width:98%;">
		<dt>备忘信息:</dt>
		<s:if test="#request.CTRL.e.cscMemo==true">
		${lz:set("haveEditable",true)}
		<dd input="grid">
		<s:if test="#request.csCar$cscMemo!=null">${csCar$cscMemo}</s:if><s:else>
		    <textarea class="input wide grid" grid="[
		    	['备忘内容',null,'textarea','content',null]
		    	,['签名',100,'text','sign',null]
		    ]" id="cscMemo" name="csCar.cscMemo">${csCar.cscMemo}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备忘信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscMemo">${csCar.cscMemo}</textarea>
		 		<span>
		 	
			 ${csCar.cscMemo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscMemo}
	</s:if>
	
	${lz:set("注释","*****************上线密码字段的输入框代码*****************")}
	${lz:set("注释","before$cscOnPsd和after$cscOnPsd变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscOnPsd==true">
	${before$cscOnPsd}
	<dl class="cscOnPsd " minor  ref="cscOnPsd" >
		<dt>上线密码:</dt>
		<s:if test="#request.CTRL.e.cscOnPsd==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCar$cscOnPsd!=null">${csCar$cscOnPsd}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCar.cscOnPsd" id="cscOnPsd"  value="${csCar.cscOnPsd}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆的上线密码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****上线密码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscOnPsd">${csCar.cscOnPsd}</textarea>
		 		<span>
		 	
			 ${csCar.cscOnPsd$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscOnPsd}
	</s:if>
	
	${lz:set("注释","*****************车机安装字段的输入框代码*****************")}
	${lz:set("注释","before$cscSetup和after$cscSetup变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscSetup==true">
	${before$cscSetup}
	<dl class="cscSetup " major  ref="cscSetup" >
		<dt>车机安装:</dt>
		<s:if test="#request.CTRL.e.cscSetup==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCar$cscSetup!=null">${csCar$cscSetup}</s:if><s:else>
		 	<select class="narrow" id="cscSetup" name="csCar.cscSetup">
		 		<option value="">请选择</option>
				<option value="0" ${csCar.cscSetup==0?"selected":""}>未安装</option>
				<option value="1" ${csCar.cscSetup==1?"selected":""}>正在安装</option>
				<option value="2" ${csCar.cscSetup==2?"selected":""}>已安装</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆的车机安装</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车机安装字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscSetup">${csCar.cscSetup}</textarea>
		 		<span>
		 	
			 ${csCar.cscSetup$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscSetup}
	</s:if>
	
	${lz:set("注释","*****************下位机版本字段的输入框代码*****************")}
	${lz:set("注释","before$cscDeviceVesion和after$cscDeviceVesion变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscDeviceVesion==true">
	${before$cscDeviceVesion}
	<dl class="cscDeviceVesion " minor  ref="cscDeviceVesion" >
		<dt>下位机版本:</dt>
		<s:if test="#request.CTRL.e.cscDeviceVesion==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCar$cscDeviceVesion!=null">${csCar$cscDeviceVesion}</s:if><s:else>
		 	<select class="narrow" id="cscDeviceVesion" name="csCar.cscDeviceVesion">
		 		<option value="">请选择</option>
				<option value="0" ${csCar.cscDeviceVesion==0?"selected":""}>1.0版</option>
				<option value="1" ${csCar.cscDeviceVesion==1?"selected":""}>2.0版</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆的下位机版本</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****下位机版本字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscDeviceVesion">${csCar.cscDeviceVesion}</textarea>
		 		<span>
		 	
			 ${csCar.cscDeviceVesion$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscDeviceVesion}
	</s:if>
	
	${lz:set("注释","*****************服务器标识字段的输入框代码*****************")}
	${lz:set("注释","before$cscMqttFlag和after$cscMqttFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscMqttFlag==true">
	${before$cscMqttFlag}
	<dl class="cscMqttFlag " minor  ref="cscMqttFlag" >
		<dt>服务器标识:</dt>
		<s:if test="#request.CTRL.e.cscMqttFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCar$cscMqttFlag!=null">${csCar$cscMqttFlag}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCar.cscMqttFlag" id="cscMqttFlag"  value="${csCar.cscMqttFlag}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆的服务器标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****服务器标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscMqttFlag">${csCar.cscMqttFlag}</textarea>
		 		<span>
		 	
			 ${csCar.cscMqttFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscMqttFlag}
	</s:if>
	
	${lz:set("注释","*****************程序掩码字段的输入框代码*****************")}
	${lz:set("注释","before$cscMask和after$cscMask变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscMask==true">
	${before$cscMask}
	<dl class="cscMask " minor  ref="cscMask" style="width:98%;">
		<dt>程序掩码:</dt>
		<s:if test="#request.CTRL.e.cscMask==true">
		${lz:set("haveEditable",true)}
		<dd input="checkbox">
		<s:if test="#request.csCar$cscMask!=null">${csCar$cscMask}</s:if><s:else>
		 	<div class='wide input'>	
		 	&nbsp; 	
		 	<input type="hidden" value="#0#" name="csCar.cscMask" id="cscMask"/>
			<label><input type="checkbox" ${lz:bitin(csCar.cscMask,1) ? "checked='checked'" : ""} value="1" ref="cscMask"/>假定条件&nbsp;&nbsp;&nbsp;</label>
		 </div>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆的程序掩码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****程序掩码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscMask">${csCar.cscMask}</textarea>
		 		<span>
		 	
			 ${csCar.cscMask$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscMask}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cscStatus和after$cscStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscStatus==true">
	${before$cscStatus}
	<dl class="cscStatus " major  ref="cscStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cscStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCar$cscStatus!=null">${csCar$cscStatus}</s:if><s:else>
		 	<select class="narrow" id="cscStatus" name="csCar.cscStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csCar.cscStatus==1?"selected":""}>上线</option>
				<option value="2" ${csCar.cscStatus==2?"selected":""}>下线</option>
				<option value="3" ${csCar.cscStatus==3?"selected":""}>企业可租</option>
				<option value="0" ${csCar.cscStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscStatus">${csCar.cscStatus}</textarea>
		 		<span>
		 	
			 ${csCar.cscStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscStatus}
	</s:if>
	
	${lz:set("注释","*****************终端序列号字段的输入框代码*****************")}
	${lz:set("注释","before$cscTerNo和after$cscTerNo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscTerNo==true">
	${before$cscTerNo}
	<dl class="cscTerNo " major  ref="cscTerNo" >
		<dt>终端序列号:</dt>
		<s:if test="#request.CTRL.e.cscTerNo==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCar$cscTerNo!=null">${csCar$cscTerNo}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCar.cscTerNo" id="cscTerNo"  value="${csCar.cscTerNo}"/>
	 	 </s:else>
	 	 <em>请输入要绑定的终端序列号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****终端序列号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscTerNo">${csCar.cscTerNo}</textarea>
		 		<span>
		 	
			 ${csCar.cscTerNo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscTerNo}
	</s:if>
	
	${lz:set("注释","*****************车辆所挂载平台字段的输入框代码*****************")}
	${lz:set("注释","before$cscBindPlatform和after$cscBindPlatform变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscBindPlatform==true">
	${before$cscBindPlatform}
	<dl class="cscBindPlatform " major  ref="cscBindPlatform" >
		<dt>车辆所挂载平台:</dt>
		<s:if test="#request.CTRL.e.cscBindPlatform==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCar$cscBindPlatform!=null">${csCar$cscBindPlatform}</s:if><s:else>
		 	<select class="narrow" id="cscBindPlatform" name="csCar.cscBindPlatform">
		 		<option value="">请选择</option>
				<option value="0" ${csCar.cscBindPlatform==0?"selected":""}>北京出行平台</option>
				<option value="1" ${csCar.cscBindPlatform==1?"selected":""}>车机中心平台</option>
		 	</select>
	 	 </s:else>
	 	 <em>请选择车辆所挂载平台</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车辆所挂载平台字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscBindPlatform">${csCar.cscBindPlatform}</textarea>
		 		<span>
		 	
			 ${csCar.cscBindPlatform$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscBindPlatform}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csCar.cscId}" name="csCar.cscId" id="cscId" />
					</s:if>			
					<s:if test="#request.haveEditable==true">
						${lz:set("submitsCount",lz:size(CTRL.submits))}
						<s:if test="#request.submitsCount>0">
							<s:iterator value="#request.CTRL.submits" id="submit" status="i">
					<button class="button" type="submit" name="submiter" value="${submit.value}">&nbsp;&nbsp;${submit.name}&nbsp;&nbsp;</button>
							</s:iterator>
						</s:if>
						<s:else>
					<s:if test="#request.complete!=null && #request.complete!=''">
						${lz:set("注释","****如果调用时传入了complete完成句柄，那么只能提交到本页面，由action控制调用该句柄****")}
						<button title="保存数据,不关闭窗口,刷新原列表" class="button" type="submit" name="submiter" value="保存">
							&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778030.png"/>
							保存&nbsp;&nbsp;</button>
					</s:if>
					<s:else>
						<button title="保存数据,关闭窗口,刷新原列表" class="button" type="submit" name="submiter" value="完成">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
						完成&nbsp;&nbsp;</button>
						<button title="保存数据,不关闭窗口,刷新原列表" class="button" type="submit" name="submiter" value="保存">
							&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778030.png"/>
							保存&nbsp;&nbsp;</button>
					</s:else>
						</s:else>
					<button class="button" type="reset" value="重置">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778045.png"/>
						重置&nbsp;&nbsp;</button>
					</s:if>
					<s:else>
					<button title="不保存数据,关闭窗口,不刷新原列表" class="button" onclick="cancel();" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
						确定&nbsp;&nbsp;</button>
					</s:else>
					<button title="不保存数据,关闭窗口,不刷新原列表" class="button" onclick="cancel();" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-multiply.png"/>
						取消&nbsp;&nbsp;</button>
				</center>
			</div>	
		</div>
		<div class="foot"></div>		
	</form>
</s:else>

<script>
	function cancel(){
		$.closeModalDialog();
	}
	function href(url,params){
		top.$.showModalDialog({url:url},params,window);
	}
</script>


<!------------------------LAZY3Q_JSP_BOTTOM------------------------>
<!------------------------LAZY3Q_JSP_BOTTOM------------------------>


</body>
</html>