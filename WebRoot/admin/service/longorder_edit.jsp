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
${lz:set("projectpath","admin/service/longorder.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csLongOrder.csloId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csloHost,csloPackNo,csloMember,csloPackProfile,csloPackImage,csloDeposit,csloPayCycle,csloPactTime,csloStartTime,csloFinishTime",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csloHost,csloPackNo,csloMember,csloPackProfile,csloPackImage,csloDeposit,csloPayCycle,csloPactTime,csloStartTime,csloFinishTime",
	</s:if>
	<s:elseif test="#request.CTRL.title=='下单'">
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csloHost,csloMember,csloPackNo,csloPactTime,csloPackProfile,csloPackImage,cids,csloDeposit,csloPayCycle,csloStartTime,csloFinishTime,nothing",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csloHost,csloMember,csloPackNo,csloPactTime,csloPackProfile,csloPackImage,cids,csloDeposit,csloPayCycle,csloStartTime,csloFinishTime,nothing",
	</s:elseif>
	<s:elseif test="#request.CTRL.title=='交付'">
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"infos,csloRemark,nothing",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csloMember,details,fees,csloLeaseTerm,csloDeposit,csloPactTime,csloStartTime,csloFinishTime,infos,csloRemark,nothing",
	</s:elseif>
	<s:elseif test="#request.CTRL.title=='续租'">
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"to,csloRemark,nothing",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csloMember,details,fees,csloLeaseTerm,csloDeposit,csloPactTime,csloStartTime,csloFinishTime,to,csloRemark,nothing",
	</s:elseif>
	<s:elseif test="#request.CTRL.title=='缴费'">
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"feeTitle,ammount,moneyType,type,remark,nothing",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csloMember,details,fees,csloLeaseTerm,csloPayCycle,feeTitle,ammount,moneyType,type,remark,nothing",
	</s:elseif>
	<s:elseif test="#request.CTRL.title=='作废'">
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csloRemark,nothing",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csloMember,details,fees,csloLeaseTerm,csloDeposit,csloPactTime,csloStartTime,csloFinishTime,csloRemark,nothing",
	</s:elseif>
	<s:elseif test="#request.CTRL.title=='完成'">
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"infos,csloRemark,nothing",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csloMember,details,fees,csloLeaseTerm,csloDeposit,csloPactTime,csloStartTime,csloFinishTime,infos,csloRemark,nothing",
	</s:elseif>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csloId,csloPackNo,csloPackProfile,csloPackImage,details,fees,csloPayCycle,csloRemark",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csloId,csloHost,csloPackNo,csloMember,csloPackProfile,csloPackImage,details,fees,csloCount,csloLeaseTerm,csloDeposit,csloPayCycle,csloPactTime,csloStartTime,csloFinishTime,csloRemark,csloStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"长租订单编辑":CTRL.title}</title>
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
<s:if test="#request.CTRL.title=='下单'">
	
</s:if>
<s:if test="#request.CTRL.title=='续租'">
	<s:if test="csLongOrder.csloStatus>1">
		${lz:set("message","只有已预订和使用中的订单才能续租")}
		<lz:set name="CTRL" type="json">{
			title:"不能续租",
			editable:false,
			visible:false
		}</lz:set>
	</s:if>
	<s:else>
		<script>
		$(function(){
			$("#to").attr("onClick","").click(function(){		
				WdatePicker(getFinishDateParams());
			});
		});
		function getFinishDateParams(){
			var d1=1000*60*60*24*1;//1天
			var d10=1000*60*60*24*10;//10天
			var orderDate = $.dateFormat("<s:date name="csLongOrder.csloFinishTime" format="yyyy-MM-dd HH:mm:ss"/>","yyyy-MM-dd HH:mm:ss");
			var minDate = new Date(orderDate.getTime()+d1);//最少续订1天
			var startDate = new Date(orderDate.getTime()+d10);//默认续订10天
			return {
				el:"to",
				minDate:$.formatDate("yyyy-MM-dd",minDate),
				alwaysUseStartDate:true,
				startDate:$.formatDate("yyyy-MM-dd",startDate),
				dateFmt:'yyyy-MM-dd'
			};
		}
		</script>
	</s:else>
</s:if>
<s:if test="#request.CTRL.title=='缴费'">
	<s:if test="csLongOrder.csloStatus>1 && csLongOrder.csloStatus!=5">
		${lz:set("message","只有已预订、使用中或待处理的订单才能缴费")}
		<lz:set name="CTRL" type="json">{
			title:"不能缴费",
			editable:false,
			visible:false
		}</lz:set>
	</s:if>
</s:if>
<s:if test="#request.CTRL.title=='作废'">
	<s:if test="csLongOrder.csloStatus>1 && csLongOrder.csloStatus!=5">
		${lz:set("message","只有已预订、使用中或待处理的订单才能作废")}
		<lz:set name="CTRL" type="json">{
			title:"不能作废",
			editable:false,
			visible:false
		}</lz:set>
	</s:if>
</s:if>
<s:if test="#request.CTRL.title=='交付'">
	<s:if test="csLongOrder.csloStatus!=0">
		${lz:set("message","只有已预订的订单才能交付")}
		<lz:set name="CTRL" type="json">{
			title:"不能交付",
			editable:false,
			visible:false
		}</lz:set>
	</s:if>
	<s:else>
		<get:CsLongOrderDetail name="infos" size="-1">{
			cslodLongOrder:${csLongOrder.csloId}
		}</get:CsLongOrderDetail>
		<lz:set name="infos">
		[<s:iterator value="#request.infos" id="item" status="i">${i.count>1?",":""}{
			id:${item.cslodId},car:"${item.cslodCar$}",status:"${item.cslodStatus$}",setDate:"${item.cslodSetTime$}",setKilo:"${item.cslodSetKilo$}",retKilo:"${item.cslodRetKilo$}",retDate:"${item.cslodRetTime$}"
		}</s:iterator>]
		</lz:set>
		<script>
		$(function(){
			$(".infos .table .end").remove();
		})
		</script>
	</s:else>
</s:if>
<s:if test="#request.CTRL.title=='完成'">
	<s:if test="csLongOrder.csloStatus!=1 && csLongOrder.csloStatus!=5">
		${lz:set("message","只有使用中或待处理的订单才能完成")}
		<lz:set name="CTRL" type="json">{
			title:"不能完成",
			editable:false,
			visible:false
		}</lz:set>
	</s:if>
	<s:else>
		<get:CsLongOrderDetail name="infos" size="-1">{
			cslodLongOrder:${csLongOrder.csloId}
		}</get:CsLongOrderDetail>
		<lz:set name="infos">
		[<s:iterator value="#request.infos" id="item" status="i">${i.count>1?",":""}{
			id:${item.cslodId},car:"${item.cslodCar$}",status:"${item.cslodStatus$}",setDate:"${item.cslodSetTime$}",setKilo:"${item.cslodSetKilo$}",retKilo:"${item.cslodRetKilo$}",retDate:"${item.cslodRetTime$}"
		}</s:iterator>]
		</lz:set>
		<script>
		$(function(){
			$(".infos .table .end").remove();
		})
		</script>
	</s:else>
</s:if>
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
	$("#longorderForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csloId==true">
		${lz:set("haveEditable",true)}
		,"csLongOrder.csloId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csloHost==true">
		${lz:set("haveEditable",true)}
		,"csLongOrder.csloHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csloPackNo==true">
		${lz:set("haveEditable",true)}
		,"csLongOrder.csloPackNo":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "合同号最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csloMember==true">
		${lz:set("haveEditable",true)}
		,"csLongOrder.csloMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csloPackProfile==true">
		${lz:set("haveEditable",true)}
		,"csLongOrder.csloPackProfile":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "合同备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csloPackImage==true">
		${lz:set("haveEditable",true)}
		,"csLongOrder.csloPackImage":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csloCount==true">
		${lz:set("haveEditable",true)}
		,"csLongOrder.csloCount":function(el){
			if(jQuery.trim(el.value)=="")
				return "车辆数不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csloLeaseTerm==true">
		${lz:set("haveEditable",true)}
		,"csLongOrder.csloLeaseTerm":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "订单租期输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csloDeposit==true">
		${lz:set("haveEditable",true)}
		,"csLongOrder.csloDeposit":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "押金合计输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csloPayCycle==true">
		${lz:set("haveEditable",true)}
		,"csLongOrder.csloPayCycle":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择付款周期";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csloNeedPay==true">
		${lz:set("haveEditable",true)}
		,"csLongOrder.csloNeedPay":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "应付金额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csloRealPay==true">
		${lz:set("haveEditable",true)}
		,"csLongOrder.csloRealPay":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "实付金额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csloPactTime==true">
		${lz:set("haveEditable",true)}
		,"csLongOrder.csloPactTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "合同签订时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csloStartTime==true">
		${lz:set("haveEditable",true)}
		,"csLongOrder.csloStartTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "合同开始时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csloFinishTime==true">
		${lz:set("haveEditable",true)}
		,"csLongOrder.csloFinishTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "合同结束时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csloUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csLongOrder.csloUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csloAddTime==true">
		${lz:set("haveEditable",true)}
		,"csLongOrder.csloAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csloSerial==true">
		${lz:set("haveEditable",true)}
		,"csLongOrder.csloSerial":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "序列号最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csloRemark==true">
		${lz:set("haveEditable",true)}
		,"csLongOrder.csloRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "订单备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csloEditor==true">
		${lz:set("haveEditable",true)}
		,"csLongOrder.csloEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csloError==true">
		${lz:set("haveEditable",true)}
		,"csLongOrder.csloError":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "报警信息最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csloStatus==true">
		${lz:set("haveEditable",true)}
		,"csLongOrder.csloStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择状态";
		}
		</s:if>	
	},function(){
		${lz:set("注释","****表单提交自定义判断，阻止提交返回提示字符串即可****")}
		/******************************LAZY3Q_FORM_VALIDATE******************************/
		/******************************LAZY3Q_FORM_VALIDATE******************************/

	});		
});
</script>

<s:if test="false"></s:if>
<s:elseif test="false"></s:elseif>
<s:elseif test="#request.CTRL.title=='下单'">
	<form class="form editform" ref="CsLongOrder" id="longorderForm" name="longorderForm" action="${empty CTRL.action?"longorder_add.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<div class="prompt">
					温馨提示：请仔细填写长租订单相关信息，<span class="extrude">"*" 为必填选项。</span>
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
				<input type="hidden" value="${csLongOrderToken}" name="csLongOrderToken" id="csLongOrderToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csloHost和after$csloHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloHost}
	<dl class="csloHost " major  ref="csloHost" >
		<dt>城　　市:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csLongOrder$csloHost!=null">${csLongOrder$csloHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csloHost" name="csLongOrder.csloHost">
		 				<option selected value="${csLongOrder.csloHost}">
		 					${get:SrvHost(csLongOrder.csloHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csloHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csloHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloHost">${csLongOrder.csloHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csLongOrder.csloHost}',{ctrl:{editable:false,visible:true}})">
			 ${csLongOrder.csloHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloHost}
	
	
	${lz:set("注释","*****************会员帐号字段的输入框代码*****************")}
	${lz:set("注释","before$csloMember和after$csloMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloMember}
	<dl class="csloMember " major  ref="csloMember" >
		<dt>会员帐号:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csLongOrder$csloMember!=null">${csLongOrder$csloMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csloHost}" type="text" id="csloMember" name="csLongOrder.csloMember" text="${get:CsMember(csLongOrder.csloMember).csmName}" value="${csLongOrder.csloMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csloMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csloMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****会员帐号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloMember">${csLongOrder.csloMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csLongOrder.csloMember}',{ctrl:{editable:false,visible:true}})">
			 ${csLongOrder.csloMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloMember}
	
	
	${lz:set("注释","*****************合同号字段的输入框代码*****************")}
	${lz:set("注释","before$csloPackNo和after$csloPackNo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloPackNo}
	<dl class="csloPackNo " major  ref="csloPackNo" >
		<dt>合&nbsp;&nbsp;同&nbsp;&nbsp;号:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csLongOrder$csloPackNo!=null">${csLongOrder$csloPackNo}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csLongOrder.csloPackNo" id="csloPackNo"  value="${csLongOrder.csloPackNo}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长租订单的合同号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloPackNo">${csLongOrder.csloPackNo}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloPackNo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloPackNo}
	
	
	${lz:set("注释","*****************合同签订时间字段的输入框代码*****************")}
	${lz:set("注释","before$csloPactTime和after$csloPactTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloPactTime}
	<dl class="csloPactTime " major  ref="csloPactTime" >
		<dt>合同签订时间:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csLongOrder$csloPactTime!=null">${csLongOrder$csloPactTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csLongOrder.csloPactTime" id="csloPactTime"  value="<s:date name="csLongOrder.csloPactTime" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的合同签订时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同签订时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloPactTime">${csLongOrder.csloPactTime}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloPactTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloPactTime}
	
	
	${lz:set("注释","*****************合同备注字段的输入框代码*****************")}
	${lz:set("注释","before$csloPackProfile和after$csloPackProfile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloPackProfile}
	<dl class="csloPackProfile " minor  ref="csloPackProfile" style="width:98%;">
		<dt>合同备注:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csLongOrder$csloPackProfile!=null">${csLongOrder$csloPackProfile}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="256" name="csLongOrder.csloPackProfile" id="csloPackProfile"  value="${csLongOrder.csloPackProfile}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入长租订单的合同备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csloPackProfile">${csLongOrder.csloPackProfile}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloPackProfile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloPackProfile}
	
	
	${lz:set("注释","*****************合同图片字段的输入框代码*****************")}
	${lz:set("注释","before$csloPackImage和after$csloPackImage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloPackImage}
	<dl class="csloPackImage " minor  ref="csloPackImage" style="width:98%;">
		<dt>合同图片:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="imagelist">
		<s:if test="#request.csLongOrder$csloPackImage!=null">${csLongOrder$csloPackImage}</s:if><s:else>
		    <div id="csloPackImage" class="wide input imagelist">
			<s:generator val="csLongOrder.csloPackImage" separator=",">
				<s:iterator id="item">
					${lz:set("isVacant",lz:vacant(item))}
		 			<s:if test="#request.isVacant==false">
					<div><a href="${item}" target="_blank"><img src="${item}"/><input type="hidden" name="csLongOrder.csloPackImage" value="${item}"/></a><span onclick="$(this).parent().remove();">x</span></div>
					</s:if>
				</s:iterator>
			</s:generator>
			</div>
			<button type="button" onclick="$.upload({type:'img',count:10,callback:function(url){if(url)$('#csloPackImage').append('<div><a href=&quot;'+url+'&quot; target=&quot;_blank&quot;><img src=&quot;'+url+'&quot;/><input type=&quot;hidden&quot; name=&quot;csLongOrder.csloPackImage&quot; value=&quot;'+url+'&quot;/></a><span onclick=&quot;$(this).parent().remove();&quot;>x</span></div>');}})" class="button">添加</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传长租订单的合同图片</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同图片字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csloPackImage">${csLongOrder.csloPackImage}</textarea>
		 		<span>
		 	 <s:generator val="csLongOrder.csloPackImage" separator=",">
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
	${after$csloPackImage}
	
	
	${lz:set("注释","*****************车辆列表字段的输入框代码*****************")}
	${lz:set("注释","before$cids和after$cids变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cids}
	<dl class="cids " major  ref="cids" style="width:98%;">
		<dt>车辆列表:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="grid">
		<s:if test="#request.csLongOrder$cids!=null">${csLongOrder$cids}</s:if><s:else>
		    <textarea class="input wide grid" grid="[
		    	['预订车辆',140,'query','car','${basePath}${proname}/object/car_query.do?value={param}&cscHost={csloHost}']
		    	,['车辆租金',88,'menoy','rent',null]
		    	,['备注信息',null,'text','remark',null]
		    ]" id="cids" name="cids">${cids}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车辆列表字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cids">${cids}</textarea>
		 		<span>
		 	
			 ${cids$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cids}
	
	
	${lz:set("注释","*****************押金合计字段的输入框代码*****************")}
	${lz:set("注释","before$csloDeposit和after$csloDeposit变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloDeposit}
	<dl class="csloDeposit " major  ref="csloDeposit" >
		<dt>押金合计:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csLongOrder$csloDeposit!=null">${csLongOrder$csloDeposit}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLongOrder.csloDeposit" id="csloDeposit"  value="${csLongOrder.csloDeposit}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长租订单的押金合计</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****押金合计字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloDeposit">${csLongOrder.csloDeposit}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloDeposit$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloDeposit}
	
	
	${lz:set("注释","*****************付款周期字段的输入框代码*****************")}
	${lz:set("注释","before$csloPayCycle和after$csloPayCycle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloPayCycle}
	<dl class="csloPayCycle " major  ref="csloPayCycle" >
		<dt>付款周期:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csLongOrder$csloPayCycle!=null">${csLongOrder$csloPayCycle}</s:if><s:else>
		 	<select class="narrow" id="csloPayCycle" name="csLongOrder.csloPayCycle">
		 		<option value="">请选择</option>
				<option value="0" ${csLongOrder.csloPayCycle==0?"selected":""}>一次付清</option>
				<option value="1" ${csLongOrder.csloPayCycle==1?"selected":""}>按月付</option>
				<option value="2" ${csLongOrder.csloPayCycle==2?"selected":""}>按季付</option>
				<option value="3" ${csLongOrder.csloPayCycle==3?"selected":""}>半年付</option>
				<option value="4" ${csLongOrder.csloPayCycle==4?"selected":""}>按年付</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的付款周期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****付款周期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloPayCycle">${csLongOrder.csloPayCycle}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloPayCycle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloPayCycle}
	
	
	${lz:set("注释","*****************合同开始时间字段的输入框代码*****************")}
	${lz:set("注释","before$csloStartTime和after$csloStartTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloStartTime}
	<dl class="csloStartTime " major  ref="csloStartTime" >
		<dt>合同开始时间:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csLongOrder$csloStartTime!=null">${csLongOrder$csloStartTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csLongOrder.csloStartTime" id="csloStartTime"  value="<s:date name="csLongOrder.csloStartTime" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的合同开始时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同开始时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloStartTime">${csLongOrder.csloStartTime}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloStartTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloStartTime}
	
	
	${lz:set("注释","*****************合同结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$csloFinishTime和after$csloFinishTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloFinishTime}
	<dl class="csloFinishTime " major  ref="csloFinishTime" >
		<dt>合同结束时间:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csLongOrder$csloFinishTime!=null">${csLongOrder$csloFinishTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csLongOrder.csloFinishTime" id="csloFinishTime"  value="<s:date name="csLongOrder.csloFinishTime" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的合同结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloFinishTime">${csLongOrder.csloFinishTime}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloFinishTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloFinishTime}
	
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<input type="hidden" value="${csLongOrder.csloId}" name="csLongOrder.csloId" id="csloId" />
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
<s:elseif test="#request.CTRL.title=='交付'">
	<form class="form editform" ref="CsLongOrder" id="longorderForm" name="longorderForm" action="${empty CTRL.action?"longorder_deliver.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<div class="prompt">
					温馨提示：请仔细填写长租订单相关信息，<span class="extrude">"*" 为必填选项。</span>
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
				<input type="hidden" value="${csLongOrderToken}" name="csLongOrderToken" id="csLongOrderToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************会员帐号字段的输入框代码*****************")}
	${lz:set("注释","before$csloMember和after$csloMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloMember}
	<dl class="csloMember " major  ref="csloMember" >
		<dt>会员帐号:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csLongOrder$csloMember!=null">${csLongOrder$csloMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csloHost}" type="text" id="csloMember" name="csLongOrder.csloMember" text="${get:CsMember(csLongOrder.csloMember).csmName}" value="${csLongOrder.csloMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csloMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csloMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****会员帐号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloMember">${csLongOrder.csloMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csLongOrder.csloMember}',{ctrl:{editable:false,visible:true}})">
			 ${csLongOrder.csloMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloMember}
	
	
	${lz:set("注释","*****************车辆明细字段的输入框代码*****************")}
	${lz:set("注释","before$details和after$details变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$details}
	<dl class="details " minor  ref="details" style="width:98%;">
		<dt>车辆明细:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csLongOrder$details!=null">${csLongOrder$details}</s:if><s:else>
		  		<s:if test="csLongOrder.csloId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="details" name="details" frameborder='0' src="" url="${basePath}${proname}/service/longorderdetail.do?inFrame=true&canQuery=false&fields=cslodCar,cslodPrice,cslodSetTime,cslodRetTime,cslodStatus&cslodLongOrder=${csLongOrder.csloId}&ctrl={queryable:false,fields:{cslodLongOrder:{visible:true,editable:false,defaultValue:${csLongOrder.csloId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#details').attr('src',$('#details').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑车辆明细
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车辆明细字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csLongOrder.csloId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="details" name="details" frameborder='0' src="" url="${basePath}${proname}/service/longorderdetail.do?inFrame=true&canQuery=false&fields=cslodCar,cslodPrice,cslodSetTime,cslodRetTime,cslodStatus&cslodLongOrder=${csLongOrder.csloId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#details').attr('src',$('#details').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$details}
	
	
	${lz:set("注释","*****************缴费明细字段的输入框代码*****************")}
	${lz:set("注释","before$fees和after$fees变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$fees}
	<dl class="fees " minor  ref="fees" style="width:98%;">
		<dt>缴费明细:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csLongOrder$fees!=null">${csLongOrder$fees}</s:if><s:else>
		  		<s:if test="csLongOrder.csloId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="fees" name="fees" frameborder='0' src="" url="${basePath}${proname}/service/longorderfee.do?inFrame=true&canQuery=false&fields=cslofName,cslofType,cslofAmmount,cslofState,cslofStatus&cslofLongorder=${csLongOrder.csloId}&ctrl={queryable:false,fields:{cslofLongorder:{visible:true,editable:false,defaultValue:${csLongOrder.csloId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#fees').attr('src',$('#fees').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑缴费明细
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****缴费明细字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csLongOrder.csloId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="fees" name="fees" frameborder='0' src="" url="${basePath}${proname}/service/longorderfee.do?inFrame=true&canQuery=false&fields=cslofName,cslofType,cslofAmmount,cslofState,cslofStatus&cslofLongorder=${csLongOrder.csloId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#fees').attr('src',$('#fees').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$fees}
	
	
	${lz:set("注释","*****************订单租期字段的输入框代码*****************")}
	${lz:set("注释","before$csloLeaseTerm和after$csloLeaseTerm变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloLeaseTerm}
	<dl class="csloLeaseTerm " major  ref="csloLeaseTerm" >
		<dt>订单租期:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csLongOrder$csloLeaseTerm!=null">${csLongOrder$csloLeaseTerm}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLongOrder.csloLeaseTerm" id="csloLeaseTerm"  value="${csLongOrder.csloLeaseTerm}"/>
	 	 </s:else>
	 	 个月
	 	 <b>*</b>
	 	 <em>请输入长租订单的订单租期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单租期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloLeaseTerm">${csLongOrder.csloLeaseTerm}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloLeaseTerm$}
	 	 个月 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloLeaseTerm}
	
	
	${lz:set("注释","*****************押金合计字段的输入框代码*****************")}
	${lz:set("注释","before$csloDeposit和after$csloDeposit变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloDeposit}
	<dl class="csloDeposit " major  ref="csloDeposit" >
		<dt>押金合计:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csLongOrder$csloDeposit!=null">${csLongOrder$csloDeposit}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLongOrder.csloDeposit" id="csloDeposit"  value="${csLongOrder.csloDeposit}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长租订单的押金合计</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****押金合计字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloDeposit">${csLongOrder.csloDeposit}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloDeposit$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloDeposit}
	
	
	${lz:set("注释","*****************合同签订时间字段的输入框代码*****************")}
	${lz:set("注释","before$csloPactTime和after$csloPactTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloPactTime}
	<dl class="csloPactTime " major  ref="csloPactTime" >
		<dt>合同签订时间:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csLongOrder$csloPactTime!=null">${csLongOrder$csloPactTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csLongOrder.csloPactTime" id="csloPactTime"  value="<s:date name="csLongOrder.csloPactTime" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的合同签订时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同签订时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloPactTime">${csLongOrder.csloPactTime}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloPactTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloPactTime}
	
	
	${lz:set("注释","*****************合同开始时间字段的输入框代码*****************")}
	${lz:set("注释","before$csloStartTime和after$csloStartTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloStartTime}
	<dl class="csloStartTime " major  ref="csloStartTime" >
		<dt>合同开始时间:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csLongOrder$csloStartTime!=null">${csLongOrder$csloStartTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csLongOrder.csloStartTime" id="csloStartTime"  value="<s:date name="csLongOrder.csloStartTime" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的合同开始时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同开始时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloStartTime">${csLongOrder.csloStartTime}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloStartTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloStartTime}
	
	
	${lz:set("注释","*****************合同结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$csloFinishTime和after$csloFinishTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloFinishTime}
	<dl class="csloFinishTime " major  ref="csloFinishTime" >
		<dt>合同结束时间:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csLongOrder$csloFinishTime!=null">${csLongOrder$csloFinishTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csLongOrder.csloFinishTime" id="csloFinishTime"  value="<s:date name="csLongOrder.csloFinishTime" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的合同结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloFinishTime">${csLongOrder.csloFinishTime}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloFinishTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloFinishTime}
	
	
	${lz:set("注释","*****************车辆列表字段的输入框代码*****************")}
	${lz:set("注释","before$infos和after$infos变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$infos}
	<dl class="infos " major  ref="infos" style="width:98%;">
		<dt>车辆列表:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="grid">
		<s:if test="#request.csLongOrder$infos!=null">${csLongOrder$infos}</s:if><s:else>
		    <textarea class="input wide grid" grid="[
		    	['编号',null,'hidden','id',null]
		    	,['车牌号码',null,'label','car',null]
		    	,['交付里程',null,'number','setKilo',null]
		    	,['交付时间',null,'date','setDate',null]
		    ]" id="infos" name="infos">${infos}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车辆列表字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="infos">${infos}</textarea>
		 		<span>
		 	
			 ${infos$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$infos}
	
	
	${lz:set("注释","*****************订单备注字段的输入框代码*****************")}
	${lz:set("注释","before$csloRemark和after$csloRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloRemark}
	<dl class="csloRemark " major  ref="csloRemark" style="width:98%;">
		<dt>订单备注:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csLongOrder$csloRemark!=null">${csLongOrder$csloRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csloRemark" name="csLongOrder.csloRemark" rows="5">${csLongOrder.csloRemark}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长租订单的订单备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csLongOrder.csloRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloRemark}
	
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<input type="hidden" value="${csLongOrder.csloId}" name="csLongOrder.csloId" id="csloId" />
						<button class="button" type="submit" name="submiter" value="确定交付">
						&nbsp;&nbsp;确定交付&nbsp;&nbsp;</button>
					<button title="不保存数据,关闭窗口,不刷新原列表" class="button" onclick="cancel();" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-multiply.png"/>
						取消&nbsp;&nbsp;</button>
				</center>
			</div>	
		</div>
		<div class="foot"></div>		
	</form>
</s:elseif>
<s:elseif test="#request.CTRL.title=='续租'">
	<form class="form editform" ref="CsLongOrder" id="longorderForm" name="longorderForm" action="${empty CTRL.action?"longorder_reorder.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<div class="prompt">
					温馨提示：请仔细填写长租订单相关信息，<span class="extrude">"*" 为必填选项。</span>
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
				<input type="hidden" value="${csLongOrderToken}" name="csLongOrderToken" id="csLongOrderToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************会员帐号字段的输入框代码*****************")}
	${lz:set("注释","before$csloMember和after$csloMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloMember}
	<dl class="csloMember " major  ref="csloMember" >
		<dt>会员帐号:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csLongOrder$csloMember!=null">${csLongOrder$csloMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csloHost}" type="text" id="csloMember" name="csLongOrder.csloMember" text="${get:CsMember(csLongOrder.csloMember).csmName}" value="${csLongOrder.csloMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csloMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csloMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****会员帐号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloMember">${csLongOrder.csloMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csLongOrder.csloMember}',{ctrl:{editable:false,visible:true}})">
			 ${csLongOrder.csloMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloMember}
	
	
	${lz:set("注释","*****************车辆明细字段的输入框代码*****************")}
	${lz:set("注释","before$details和after$details变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$details}
	<dl class="details " minor  ref="details" style="width:98%;">
		<dt>车辆明细:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csLongOrder$details!=null">${csLongOrder$details}</s:if><s:else>
		  		<s:if test="csLongOrder.csloId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="details" name="details" frameborder='0' src="" url="${basePath}${proname}/service/longorderdetail.do?inFrame=true&canQuery=false&fields=cslodCar,cslodPrice,cslodSetTime,cslodRetTime,cslodStatus&cslodLongOrder=${csLongOrder.csloId}&ctrl={queryable:false,fields:{cslodLongOrder:{visible:true,editable:false,defaultValue:${csLongOrder.csloId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#details').attr('src',$('#details').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑车辆明细
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车辆明细字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csLongOrder.csloId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="details" name="details" frameborder='0' src="" url="${basePath}${proname}/service/longorderdetail.do?inFrame=true&canQuery=false&fields=cslodCar,cslodPrice,cslodSetTime,cslodRetTime,cslodStatus&cslodLongOrder=${csLongOrder.csloId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#details').attr('src',$('#details').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$details}
	
	
	${lz:set("注释","*****************缴费明细字段的输入框代码*****************")}
	${lz:set("注释","before$fees和after$fees变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$fees}
	<dl class="fees " minor  ref="fees" style="width:98%;">
		<dt>缴费明细:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csLongOrder$fees!=null">${csLongOrder$fees}</s:if><s:else>
		  		<s:if test="csLongOrder.csloId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="fees" name="fees" frameborder='0' src="" url="${basePath}${proname}/service/longorderfee.do?inFrame=true&canQuery=false&fields=cslofName,cslofType,cslofAmmount,cslofState,cslofStatus&cslofLongorder=${csLongOrder.csloId}&ctrl={queryable:false,fields:{cslofLongorder:{visible:true,editable:false,defaultValue:${csLongOrder.csloId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#fees').attr('src',$('#fees').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑缴费明细
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****缴费明细字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csLongOrder.csloId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="fees" name="fees" frameborder='0' src="" url="${basePath}${proname}/service/longorderfee.do?inFrame=true&canQuery=false&fields=cslofName,cslofType,cslofAmmount,cslofState,cslofStatus&cslofLongorder=${csLongOrder.csloId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#fees').attr('src',$('#fees').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$fees}
	
	
	${lz:set("注释","*****************订单租期字段的输入框代码*****************")}
	${lz:set("注释","before$csloLeaseTerm和after$csloLeaseTerm变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloLeaseTerm}
	<dl class="csloLeaseTerm " major  ref="csloLeaseTerm" >
		<dt>订单租期:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csLongOrder$csloLeaseTerm!=null">${csLongOrder$csloLeaseTerm}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLongOrder.csloLeaseTerm" id="csloLeaseTerm"  value="${csLongOrder.csloLeaseTerm}"/>
	 	 </s:else>
	 	 个月
	 	 <b>*</b>
	 	 <em>请输入长租订单的订单租期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单租期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloLeaseTerm">${csLongOrder.csloLeaseTerm}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloLeaseTerm$}
	 	 个月 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloLeaseTerm}
	
	
	${lz:set("注释","*****************押金合计字段的输入框代码*****************")}
	${lz:set("注释","before$csloDeposit和after$csloDeposit变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloDeposit}
	<dl class="csloDeposit " major  ref="csloDeposit" >
		<dt>押金合计:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csLongOrder$csloDeposit!=null">${csLongOrder$csloDeposit}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLongOrder.csloDeposit" id="csloDeposit"  value="${csLongOrder.csloDeposit}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长租订单的押金合计</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****押金合计字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloDeposit">${csLongOrder.csloDeposit}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloDeposit$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloDeposit}
	
	
	${lz:set("注释","*****************合同签订时间字段的输入框代码*****************")}
	${lz:set("注释","before$csloPactTime和after$csloPactTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloPactTime}
	<dl class="csloPactTime " major  ref="csloPactTime" >
		<dt>合同签订时间:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csLongOrder$csloPactTime!=null">${csLongOrder$csloPactTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csLongOrder.csloPactTime" id="csloPactTime"  value="<s:date name="csLongOrder.csloPactTime" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的合同签订时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同签订时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloPactTime">${csLongOrder.csloPactTime}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloPactTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloPactTime}
	
	
	${lz:set("注释","*****************合同开始时间字段的输入框代码*****************")}
	${lz:set("注释","before$csloStartTime和after$csloStartTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloStartTime}
	<dl class="csloStartTime " major  ref="csloStartTime" >
		<dt>合同开始时间:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csLongOrder$csloStartTime!=null">${csLongOrder$csloStartTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csLongOrder.csloStartTime" id="csloStartTime"  value="<s:date name="csLongOrder.csloStartTime" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的合同开始时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同开始时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloStartTime">${csLongOrder.csloStartTime}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloStartTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloStartTime}
	
	
	${lz:set("注释","*****************合同结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$csloFinishTime和after$csloFinishTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloFinishTime}
	<dl class="csloFinishTime " major  ref="csloFinishTime" >
		<dt>合同结束时间:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csLongOrder$csloFinishTime!=null">${csLongOrder$csloFinishTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csLongOrder.csloFinishTime" id="csloFinishTime"  value="<s:date name="csLongOrder.csloFinishTime" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的合同结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloFinishTime">${csLongOrder.csloFinishTime}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloFinishTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloFinishTime}
	
	
	${lz:set("注释","*****************续租时间字段的输入框代码*****************")}
	${lz:set("注释","before$to和after$to变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$to}
	<dl class="to " major  ref="to" >
		<dt>续租时间:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csLongOrder$to!=null">${csLongOrder$to}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="to" id="to"  value="<s:date name="to" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的续租时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****续租时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="to">${to}</textarea>
		 		<span>
		 	
			 ${to$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$to}
	
	
	${lz:set("注释","*****************订单备注字段的输入框代码*****************")}
	${lz:set("注释","before$csloRemark和after$csloRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloRemark}
	<dl class="csloRemark " major  ref="csloRemark" style="width:98%;">
		<dt>订单备注:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csLongOrder$csloRemark!=null">${csLongOrder$csloRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csloRemark" name="csLongOrder.csloRemark" rows="5">${csLongOrder.csloRemark}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长租订单的订单备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csLongOrder.csloRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloRemark}
	
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<input type="hidden" value="${csLongOrder.csloId}" name="csLongOrder.csloId" id="csloId" />
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
<s:elseif test="#request.CTRL.title=='缴费'">
	<form class="form editform" ref="CsLongOrder" id="longorderForm" name="longorderForm" action="${empty CTRL.action?"longorder_pay.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<div class="prompt">
					温馨提示：请仔细填写长租订单相关信息，<span class="extrude">"*" 为必填选项。</span>
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
				<input type="hidden" value="${csLongOrderToken}" name="csLongOrderToken" id="csLongOrderToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************会员帐号字段的输入框代码*****************")}
	${lz:set("注释","before$csloMember和after$csloMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloMember}
	<dl class="csloMember " major  ref="csloMember" >
		<dt>会员帐号:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csLongOrder$csloMember!=null">${csLongOrder$csloMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csloHost}" type="text" id="csloMember" name="csLongOrder.csloMember" text="${get:CsMember(csLongOrder.csloMember).csmName}" value="${csLongOrder.csloMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csloMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csloMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****会员帐号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloMember">${csLongOrder.csloMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csLongOrder.csloMember}',{ctrl:{editable:false,visible:true}})">
			 ${csLongOrder.csloMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloMember}
	
	
	${lz:set("注释","*****************车辆明细字段的输入框代码*****************")}
	${lz:set("注释","before$details和after$details变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$details}
	<dl class="details " minor  ref="details" style="width:98%;">
		<dt>车辆明细:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csLongOrder$details!=null">${csLongOrder$details}</s:if><s:else>
		  		<s:if test="csLongOrder.csloId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="details" name="details" frameborder='0' src="" url="${basePath}${proname}/service/longorderdetail.do?inFrame=true&canQuery=false&fields=cslodCar,cslodPrice,cslodSetTime,cslodRetTime,cslodStatus&cslodLongOrder=${csLongOrder.csloId}&ctrl={queryable:false,fields:{cslodLongOrder:{visible:true,editable:false,defaultValue:${csLongOrder.csloId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#details').attr('src',$('#details').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑车辆明细
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车辆明细字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csLongOrder.csloId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="details" name="details" frameborder='0' src="" url="${basePath}${proname}/service/longorderdetail.do?inFrame=true&canQuery=false&fields=cslodCar,cslodPrice,cslodSetTime,cslodRetTime,cslodStatus&cslodLongOrder=${csLongOrder.csloId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#details').attr('src',$('#details').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$details}
	
	
	${lz:set("注释","*****************缴费明细字段的输入框代码*****************")}
	${lz:set("注释","before$fees和after$fees变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$fees}
	<dl class="fees " minor  ref="fees" style="width:98%;">
		<dt>缴费明细:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csLongOrder$fees!=null">${csLongOrder$fees}</s:if><s:else>
		  		<s:if test="csLongOrder.csloId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="fees" name="fees" frameborder='0' src="" url="${basePath}${proname}/service/longorderfee.do?inFrame=true&canQuery=false&fields=cslofName,cslofType,cslofAmmount,cslofState,cslofStatus&cslofLongorder=${csLongOrder.csloId}&ctrl={queryable:false,fields:{cslofLongorder:{visible:true,editable:false,defaultValue:${csLongOrder.csloId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#fees').attr('src',$('#fees').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑缴费明细
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****缴费明细字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csLongOrder.csloId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="fees" name="fees" frameborder='0' src="" url="${basePath}${proname}/service/longorderfee.do?inFrame=true&canQuery=false&fields=cslofName,cslofType,cslofAmmount,cslofState,cslofStatus&cslofLongorder=${csLongOrder.csloId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#fees').attr('src',$('#fees').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$fees}
	
	
	${lz:set("注释","*****************订单租期字段的输入框代码*****************")}
	${lz:set("注释","before$csloLeaseTerm和after$csloLeaseTerm变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloLeaseTerm}
	<dl class="csloLeaseTerm " major  ref="csloLeaseTerm" >
		<dt>订单租期:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csLongOrder$csloLeaseTerm!=null">${csLongOrder$csloLeaseTerm}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLongOrder.csloLeaseTerm" id="csloLeaseTerm"  value="${csLongOrder.csloLeaseTerm}"/>
	 	 </s:else>
	 	 个月
	 	 <b>*</b>
	 	 <em>请输入长租订单的订单租期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单租期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloLeaseTerm">${csLongOrder.csloLeaseTerm}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloLeaseTerm$}
	 	 个月 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloLeaseTerm}
	
	
	${lz:set("注释","*****************付款周期字段的输入框代码*****************")}
	${lz:set("注释","before$csloPayCycle和after$csloPayCycle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloPayCycle}
	<dl class="csloPayCycle " major  ref="csloPayCycle" >
		<dt>付款周期:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csLongOrder$csloPayCycle!=null">${csLongOrder$csloPayCycle}</s:if><s:else>
		 	<select class="narrow" id="csloPayCycle" name="csLongOrder.csloPayCycle">
		 		<option value="">请选择</option>
		 		 <option value="">无法自动构造</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的付款周期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****付款周期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloPayCycle">${csLongOrder.csloPayCycle}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloPayCycle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloPayCycle}
	
	
	${lz:set("注释","*****************缴费标题字段的输入框代码*****************")}
	${lz:set("注释","before$feeTitle和after$feeTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$feeTitle}
	<dl class="feeTitle " major  ref="feeTitle" >
		<dt>缴费标题:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csLongOrder$feeTitle!=null">${csLongOrder$feeTitle}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="" name="feeTitle" id="feeTitle"  value="${feeTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长租订单的缴费标题</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****缴费标题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="feeTitle">${feeTitle}</textarea>
		 		<span>
		 	
			 ${feeTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$feeTitle}
	
	
	${lz:set("注释","*****************缴费金额字段的输入框代码*****************")}
	${lz:set("注释","before$ammount和after$ammount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$ammount}
	<dl class="ammount " major  ref="ammount" >
		<dt>缴费金额:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csLongOrder$ammount!=null">${csLongOrder$ammount}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="ammount" id="ammount"  value="${ammount}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长租订单的缴费金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****缴费金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="ammount">${ammount}</textarea>
		 		<span>
		 	
			 ${ammount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$ammount}
	
	
	${lz:set("注释","*****************金额类型字段的输入框代码*****************")}
	${lz:set("注释","before$moneyType和after$moneyType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$moneyType}
	<dl class="moneyType " major  ref="moneyType" >
		<dt>金额类型:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csLongOrder$moneyType!=null">${csLongOrder$moneyType}</s:if><s:else>
		 	<select class="narrow" id="moneyType" name="moneyType">
		 		<option value="">请选择</option>
				<option value="0" ${moneyType==0?"selected":""}>押金</option>
				<option value="1" ${moneyType==1?"selected":""}>租金</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的金额类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****金额类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="moneyType">${moneyType}</textarea>
		 		<span>
		 	
			 ${moneyType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$moneyType}
	
	
	${lz:set("注释","*****************缴费方式字段的输入框代码*****************")}
	${lz:set("注释","before$type和after$type变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$type}
	<dl class="type " major  ref="type" >
		<dt>缴费方式:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csLongOrder$type!=null">${csLongOrder$type}</s:if><s:else>
		 	<select class="narrow" id="type" name="type">
		 		<option value="">请选择</option>
				<option value="0" ${type==0?"selected":""}>现金</option>
				<option value="1" ${type==1?"selected":""}>转帐</option>
				<option value="2" ${type==2?"selected":""}>余额</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的缴费方式</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****缴费方式字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="type">${type}</textarea>
		 		<span>
		 	
			 ${type$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$type}
	
	
	${lz:set("注释","*****************缴费备注字段的输入框代码*****************")}
	${lz:set("注释","before$remark和after$remark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$remark}
	<dl class="remark " major  ref="remark" style="width:98%;">
		<dt>缴费备注:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csLongOrder$remark!=null">${csLongOrder$remark}</s:if><s:else>
		 	<textarea class="input wide"  id="remark" name="remark" rows="5">${remark}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长租订单的缴费备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****缴费备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${remark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$remark}
	
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<input type="hidden" value="${csLongOrder.csloId}" name="csLongOrder.csloId" id="csloId" />
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
<s:elseif test="#request.CTRL.title=='作废'">
	<form class="form editform" ref="CsLongOrder" id="longorderForm" name="longorderForm" action="${empty CTRL.action?"longorder_undo.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<div class="prompt">
					温馨提示：请仔细填写长租订单相关信息，<span class="extrude">"*" 为必填选项。</span>
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
				<input type="hidden" value="${csLongOrderToken}" name="csLongOrderToken" id="csLongOrderToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************会员帐号字段的输入框代码*****************")}
	${lz:set("注释","before$csloMember和after$csloMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloMember}
	<dl class="csloMember " major  ref="csloMember" >
		<dt>会员帐号:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csLongOrder$csloMember!=null">${csLongOrder$csloMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csloHost}" type="text" id="csloMember" name="csLongOrder.csloMember" text="${get:CsMember(csLongOrder.csloMember).csmName}" value="${csLongOrder.csloMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csloMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csloMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****会员帐号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloMember">${csLongOrder.csloMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csLongOrder.csloMember}',{ctrl:{editable:false,visible:true}})">
			 ${csLongOrder.csloMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloMember}
	
	
	${lz:set("注释","*****************车辆明细字段的输入框代码*****************")}
	${lz:set("注释","before$details和after$details变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$details}
	<dl class="details " minor  ref="details" style="width:98%;">
		<dt>车辆明细:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csLongOrder$details!=null">${csLongOrder$details}</s:if><s:else>
		  		<s:if test="csLongOrder.csloId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="details" name="details" frameborder='0' src="" url="${basePath}${proname}/service/longorderdetail.do?inFrame=true&canQuery=false&fields=cslodCar,cslodPrice,cslodSetTime,cslodRetTime,cslodStatus&cslodLongOrder=${csLongOrder.csloId}&ctrl={queryable:false,fields:{cslodLongOrder:{visible:true,editable:false,defaultValue:${csLongOrder.csloId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#details').attr('src',$('#details').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑车辆明细
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车辆明细字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csLongOrder.csloId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="details" name="details" frameborder='0' src="" url="${basePath}${proname}/service/longorderdetail.do?inFrame=true&canQuery=false&fields=cslodCar,cslodPrice,cslodSetTime,cslodRetTime,cslodStatus&cslodLongOrder=${csLongOrder.csloId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#details').attr('src',$('#details').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$details}
	
	
	${lz:set("注释","*****************缴费明细字段的输入框代码*****************")}
	${lz:set("注释","before$fees和after$fees变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$fees}
	<dl class="fees " minor  ref="fees" style="width:98%;">
		<dt>缴费明细:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csLongOrder$fees!=null">${csLongOrder$fees}</s:if><s:else>
		  		<s:if test="csLongOrder.csloId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="fees" name="fees" frameborder='0' src="" url="${basePath}${proname}/service/longorderfee.do?inFrame=true&canQuery=false&fields=cslofName,cslofType,cslofAmmount,cslofState,cslofStatus&cslofLongorder=${csLongOrder.csloId}&ctrl={queryable:false,fields:{cslofLongorder:{visible:true,editable:false,defaultValue:${csLongOrder.csloId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#fees').attr('src',$('#fees').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑缴费明细
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****缴费明细字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csLongOrder.csloId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="fees" name="fees" frameborder='0' src="" url="${basePath}${proname}/service/longorderfee.do?inFrame=true&canQuery=false&fields=cslofName,cslofType,cslofAmmount,cslofState,cslofStatus&cslofLongorder=${csLongOrder.csloId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#fees').attr('src',$('#fees').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$fees}
	
	
	${lz:set("注释","*****************订单租期字段的输入框代码*****************")}
	${lz:set("注释","before$csloLeaseTerm和after$csloLeaseTerm变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloLeaseTerm}
	<dl class="csloLeaseTerm " major  ref="csloLeaseTerm" >
		<dt>订单租期:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csLongOrder$csloLeaseTerm!=null">${csLongOrder$csloLeaseTerm}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLongOrder.csloLeaseTerm" id="csloLeaseTerm"  value="${csLongOrder.csloLeaseTerm}"/>
	 	 </s:else>
	 	 个月
	 	 <b>*</b>
	 	 <em>请输入长租订单的订单租期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单租期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloLeaseTerm">${csLongOrder.csloLeaseTerm}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloLeaseTerm$}
	 	 个月 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloLeaseTerm}
	
	
	${lz:set("注释","*****************押金合计字段的输入框代码*****************")}
	${lz:set("注释","before$csloDeposit和after$csloDeposit变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloDeposit}
	<dl class="csloDeposit " major  ref="csloDeposit" >
		<dt>押金合计:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csLongOrder$csloDeposit!=null">${csLongOrder$csloDeposit}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLongOrder.csloDeposit" id="csloDeposit"  value="${csLongOrder.csloDeposit}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长租订单的押金合计</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****押金合计字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloDeposit">${csLongOrder.csloDeposit}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloDeposit$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloDeposit}
	
	
	${lz:set("注释","*****************合同签订时间字段的输入框代码*****************")}
	${lz:set("注释","before$csloPactTime和after$csloPactTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloPactTime}
	<dl class="csloPactTime " major  ref="csloPactTime" >
		<dt>合同签订时间:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csLongOrder$csloPactTime!=null">${csLongOrder$csloPactTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csLongOrder.csloPactTime" id="csloPactTime"  value="<s:date name="csLongOrder.csloPactTime" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的合同签订时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同签订时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloPactTime">${csLongOrder.csloPactTime}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloPactTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloPactTime}
	
	
	${lz:set("注释","*****************合同开始时间字段的输入框代码*****************")}
	${lz:set("注释","before$csloStartTime和after$csloStartTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloStartTime}
	<dl class="csloStartTime " major  ref="csloStartTime" >
		<dt>合同开始时间:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csLongOrder$csloStartTime!=null">${csLongOrder$csloStartTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csLongOrder.csloStartTime" id="csloStartTime"  value="<s:date name="csLongOrder.csloStartTime" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的合同开始时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同开始时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloStartTime">${csLongOrder.csloStartTime}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloStartTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloStartTime}
	
	
	${lz:set("注释","*****************合同结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$csloFinishTime和after$csloFinishTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloFinishTime}
	<dl class="csloFinishTime " major  ref="csloFinishTime" >
		<dt>合同结束时间:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csLongOrder$csloFinishTime!=null">${csLongOrder$csloFinishTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csLongOrder.csloFinishTime" id="csloFinishTime"  value="<s:date name="csLongOrder.csloFinishTime" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的合同结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloFinishTime">${csLongOrder.csloFinishTime}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloFinishTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloFinishTime}
	
	
	${lz:set("注释","*****************订单备注字段的输入框代码*****************")}
	${lz:set("注释","before$csloRemark和after$csloRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloRemark}
	<dl class="csloRemark " major  ref="csloRemark" style="width:98%;">
		<dt>订单备注:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csLongOrder$csloRemark!=null">${csLongOrder$csloRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csloRemark" name="csLongOrder.csloRemark" rows="5">${csLongOrder.csloRemark}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长租订单的订单备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csLongOrder.csloRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloRemark}
	
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<input type="hidden" value="${csLongOrder.csloId}" name="csLongOrder.csloId" id="csloId" />
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
<s:elseif test="#request.CTRL.title=='完成'">
	<form class="form editform" ref="CsLongOrder" id="longorderForm" name="longorderForm" action="${empty CTRL.action?"longorder_complete.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<div class="prompt">
					温馨提示：请仔细填写长租订单相关信息，<span class="extrude">"*" 为必填选项。</span>
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
				<input type="hidden" value="${csLongOrderToken}" name="csLongOrderToken" id="csLongOrderToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************会员帐号字段的输入框代码*****************")}
	${lz:set("注释","before$csloMember和after$csloMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloMember}
	<dl class="csloMember " major  ref="csloMember" >
		<dt>会员帐号:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csLongOrder$csloMember!=null">${csLongOrder$csloMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csloHost}" type="text" id="csloMember" name="csLongOrder.csloMember" text="${get:CsMember(csLongOrder.csloMember).csmName}" value="${csLongOrder.csloMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csloMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csloMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****会员帐号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloMember">${csLongOrder.csloMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csLongOrder.csloMember}',{ctrl:{editable:false,visible:true}})">
			 ${csLongOrder.csloMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloMember}
	
	
	${lz:set("注释","*****************车辆明细字段的输入框代码*****************")}
	${lz:set("注释","before$details和after$details变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$details}
	<dl class="details " minor  ref="details" style="width:98%;">
		<dt>车辆明细:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csLongOrder$details!=null">${csLongOrder$details}</s:if><s:else>
		  		<s:if test="csLongOrder.csloId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="details" name="details" frameborder='0' src="" url="${basePath}${proname}/service/longorderdetail.do?inFrame=true&canQuery=false&fields=cslodCar,cslodPrice,cslodSetTime,cslodRetTime,cslodStatus&cslodLongOrder=${csLongOrder.csloId}&ctrl={queryable:false,fields:{cslodLongOrder:{visible:true,editable:false,defaultValue:${csLongOrder.csloId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#details').attr('src',$('#details').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑车辆明细
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车辆明细字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csLongOrder.csloId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="details" name="details" frameborder='0' src="" url="${basePath}${proname}/service/longorderdetail.do?inFrame=true&canQuery=false&fields=cslodCar,cslodPrice,cslodSetTime,cslodRetTime,cslodStatus&cslodLongOrder=${csLongOrder.csloId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#details').attr('src',$('#details').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$details}
	
	
	${lz:set("注释","*****************缴费明细字段的输入框代码*****************")}
	${lz:set("注释","before$fees和after$fees变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$fees}
	<dl class="fees " minor  ref="fees" style="width:98%;">
		<dt>缴费明细:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csLongOrder$fees!=null">${csLongOrder$fees}</s:if><s:else>
		  		<s:if test="csLongOrder.csloId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="fees" name="fees" frameborder='0' src="" url="${basePath}${proname}/service/longorderfee.do?inFrame=true&canQuery=false&fields=cslofName,cslofType,cslofAmmount,cslofState,cslofStatus&cslofLongorder=${csLongOrder.csloId}&ctrl={queryable:false,fields:{cslofLongorder:{visible:true,editable:false,defaultValue:${csLongOrder.csloId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#fees').attr('src',$('#fees').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑缴费明细
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****缴费明细字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csLongOrder.csloId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="fees" name="fees" frameborder='0' src="" url="${basePath}${proname}/service/longorderfee.do?inFrame=true&canQuery=false&fields=cslofName,cslofType,cslofAmmount,cslofState,cslofStatus&cslofLongorder=${csLongOrder.csloId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#fees').attr('src',$('#fees').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$fees}
	
	
	${lz:set("注释","*****************订单租期字段的输入框代码*****************")}
	${lz:set("注释","before$csloLeaseTerm和after$csloLeaseTerm变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloLeaseTerm}
	<dl class="csloLeaseTerm " major  ref="csloLeaseTerm" >
		<dt>订单租期:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csLongOrder$csloLeaseTerm!=null">${csLongOrder$csloLeaseTerm}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLongOrder.csloLeaseTerm" id="csloLeaseTerm"  value="${csLongOrder.csloLeaseTerm}"/>
	 	 </s:else>
	 	 个月
	 	 <b>*</b>
	 	 <em>请输入长租订单的订单租期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单租期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloLeaseTerm">${csLongOrder.csloLeaseTerm}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloLeaseTerm$}
	 	 个月 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloLeaseTerm}
	
	
	${lz:set("注释","*****************押金合计字段的输入框代码*****************")}
	${lz:set("注释","before$csloDeposit和after$csloDeposit变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloDeposit}
	<dl class="csloDeposit " major  ref="csloDeposit" >
		<dt>押金合计:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csLongOrder$csloDeposit!=null">${csLongOrder$csloDeposit}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLongOrder.csloDeposit" id="csloDeposit"  value="${csLongOrder.csloDeposit}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长租订单的押金合计</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****押金合计字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloDeposit">${csLongOrder.csloDeposit}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloDeposit$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloDeposit}
	
	
	${lz:set("注释","*****************合同签订时间字段的输入框代码*****************")}
	${lz:set("注释","before$csloPactTime和after$csloPactTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloPactTime}
	<dl class="csloPactTime " major  ref="csloPactTime" >
		<dt>合同签订时间:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csLongOrder$csloPactTime!=null">${csLongOrder$csloPactTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csLongOrder.csloPactTime" id="csloPactTime"  value="<s:date name="csLongOrder.csloPactTime" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的合同签订时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同签订时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloPactTime">${csLongOrder.csloPactTime}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloPactTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloPactTime}
	
	
	${lz:set("注释","*****************合同开始时间字段的输入框代码*****************")}
	${lz:set("注释","before$csloStartTime和after$csloStartTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloStartTime}
	<dl class="csloStartTime " major  ref="csloStartTime" >
		<dt>合同开始时间:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csLongOrder$csloStartTime!=null">${csLongOrder$csloStartTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csLongOrder.csloStartTime" id="csloStartTime"  value="<s:date name="csLongOrder.csloStartTime" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的合同开始时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同开始时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloStartTime">${csLongOrder.csloStartTime}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloStartTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloStartTime}
	
	
	${lz:set("注释","*****************合同结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$csloFinishTime和after$csloFinishTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloFinishTime}
	<dl class="csloFinishTime " major  ref="csloFinishTime" >
		<dt>合同结束时间:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csLongOrder$csloFinishTime!=null">${csLongOrder$csloFinishTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csLongOrder.csloFinishTime" id="csloFinishTime"  value="<s:date name="csLongOrder.csloFinishTime" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的合同结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloFinishTime">${csLongOrder.csloFinishTime}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloFinishTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloFinishTime}
	
	
	${lz:set("注释","*****************还车时间字段的输入框代码*****************")}
	${lz:set("注释","before$infos和after$infos变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$infos}
	<dl class="infos " major  ref="infos" style="width:98%;">
		<dt>还车时间:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="grid">
		<s:if test="#request.csLongOrder$infos!=null">${csLongOrder$infos}</s:if><s:else>
		    <textarea class="input wide grid" grid="[
		    	['编号',null,'hidden','id',null]
		    	,['车牌号码',120,'label','car',null]
		    	,['交车里程',null,'label','setKilo',null]
		    	,['交车时间',null,'label','setDate',null]
		    	,['还车里程',null,'number','retKilo',null]
		    	,['还车时间',null,'date','retDate',null]
		    	,['状态',null,'label','status',null]
		    ]" id="infos" name="infos">${infos}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****还车时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="infos">${infos}</textarea>
		 		<span>
		 	
			 ${infos$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$infos}
	
	
	${lz:set("注释","*****************订单备注字段的输入框代码*****************")}
	${lz:set("注释","before$csloRemark和after$csloRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$csloRemark}
	<dl class="csloRemark " major  ref="csloRemark" style="width:98%;">
		<dt>订单备注:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csLongOrder$csloRemark!=null">${csLongOrder$csloRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csloRemark" name="csLongOrder.csloRemark" rows="5">${csLongOrder.csloRemark}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长租订单的订单备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csLongOrder.csloRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloRemark}
	
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<input type="hidden" value="${csLongOrder.csloId}" name="csLongOrder.csloId" id="csloId" />
						<button class="button" type="submit" name="submiter" value="订单完成">
						&nbsp;&nbsp;订单完成&nbsp;&nbsp;</button>
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
	<form class="form editform" ref="CsLongOrder" id="longorderForm" name="longorderForm" action="${empty CTRL.action?"longorder_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写长租订单相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csLongOrderToken}" name="csLongOrderToken" id="csLongOrderToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csLongOrder.csloId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************订单编号字段的输入框代码*****************")}
	${lz:set("注释","before$csloId和after$csloId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csloId==true">
	${before$csloId}
	<dl class="csloId ${CTRL.e.csloId?"hidden":""}" major  ref="csloId" >
		<dt>订单编号:</dt>
		<s:if test="#request.CTRL.e.csloId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csLongOrder$csloId!=null">${csLongOrder$csloId}</s:if><s:else>
			<input type="hidden" value="${csLongOrder.csloId}" name="csLongOrder.csloId" id="csloId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloId">${csLongOrder.csloId}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csloHost和after$csloHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csloHost==true">
	${before$csloHost}
	<dl class="csloHost " major  ref="csloHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csloHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csLongOrder$csloHost!=null">${csLongOrder$csloHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csloHost" name="csLongOrder.csloHost">
		 				<option selected value="${csLongOrder.csloHost}">
		 					${get:SrvHost(csLongOrder.csloHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csloHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csloHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloHost">${csLongOrder.csloHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csLongOrder.csloHost}',{ctrl:{editable:false,visible:true}})">
			 ${csLongOrder.csloHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloHost}
	</s:if>
	
	${lz:set("注释","*****************合同号字段的输入框代码*****************")}
	${lz:set("注释","before$csloPackNo和after$csloPackNo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csloPackNo==true">
	${before$csloPackNo}
	<dl class="csloPackNo " major  ref="csloPackNo" >
		<dt>合&nbsp;&nbsp;同&nbsp;&nbsp;号:</dt>
		<s:if test="#request.CTRL.e.csloPackNo==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csLongOrder$csloPackNo!=null">${csLongOrder$csloPackNo}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csLongOrder.csloPackNo" id="csloPackNo"  value="${csLongOrder.csloPackNo}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入长租订单的合同号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloPackNo">${csLongOrder.csloPackNo}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloPackNo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloPackNo}
	</s:if>
	
	${lz:set("注释","*****************会员帐号字段的输入框代码*****************")}
	${lz:set("注释","before$csloMember和after$csloMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csloMember==true">
	${before$csloMember}
	<dl class="csloMember " major  ref="csloMember" >
		<dt>会员帐号:</dt>
		<s:if test="#request.CTRL.e.csloMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csLongOrder$csloMember!=null">${csLongOrder$csloMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csloHost}" type="text" id="csloMember" name="csLongOrder.csloMember" text="${get:CsMember(csLongOrder.csloMember).csmName}" value="${csLongOrder.csloMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csloMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csloMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****会员帐号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloMember">${csLongOrder.csloMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csLongOrder.csloMember}',{ctrl:{editable:false,visible:true}})">
			 ${csLongOrder.csloMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloMember}
	</s:if>
	
	${lz:set("注释","*****************合同备注字段的输入框代码*****************")}
	${lz:set("注释","before$csloPackProfile和after$csloPackProfile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csloPackProfile==true">
	${before$csloPackProfile}
	<dl class="csloPackProfile " minor  ref="csloPackProfile" style="width:98%;">
		<dt>合同备注:</dt>
		<s:if test="#request.CTRL.e.csloPackProfile==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csLongOrder$csloPackProfile!=null">${csLongOrder$csloPackProfile}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="256" name="csLongOrder.csloPackProfile" id="csloPackProfile"  value="${csLongOrder.csloPackProfile}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入长租订单的合同备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csloPackProfile">${csLongOrder.csloPackProfile}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloPackProfile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloPackProfile}
	</s:if>
	
	${lz:set("注释","*****************合同图片字段的输入框代码*****************")}
	${lz:set("注释","before$csloPackImage和after$csloPackImage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csloPackImage==true">
	${before$csloPackImage}
	<dl class="csloPackImage " minor  ref="csloPackImage" style="width:98%;">
		<dt>合同图片:</dt>
		<s:if test="#request.CTRL.e.csloPackImage==true">
		${lz:set("haveEditable",true)}
		<dd input="imagelist">
		<s:if test="#request.csLongOrder$csloPackImage!=null">${csLongOrder$csloPackImage}</s:if><s:else>
		    <div id="csloPackImage" class="wide input imagelist">
			<s:generator val="csLongOrder.csloPackImage" separator=",">
				<s:iterator id="item">
					${lz:set("isVacant",lz:vacant(item))}
		 			<s:if test="#request.isVacant==false">
					<div><a href="${item}" target="_blank"><img src="${item}"/><input type="hidden" name="csLongOrder.csloPackImage" value="${item}"/></a><span onclick="$(this).parent().remove();">x</span></div>
					</s:if>
				</s:iterator>
			</s:generator>
			</div>
			<button type="button" onclick="$.upload({type:'img',count:10,callback:function(url){if(url)$('#csloPackImage').append('<div><a href=&quot;'+url+'&quot; target=&quot;_blank&quot;><img src=&quot;'+url+'&quot;/><input type=&quot;hidden&quot; name=&quot;csLongOrder.csloPackImage&quot; value=&quot;'+url+'&quot;/></a><span onclick=&quot;$(this).parent().remove();&quot;>x</span></div>');}})" class="button">添加</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传长租订单的合同图片</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同图片字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csloPackImage">${csLongOrder.csloPackImage}</textarea>
		 		<span>
		 	 <s:generator val="csLongOrder.csloPackImage" separator=",">
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
	${after$csloPackImage}
	</s:if>
	
	${lz:set("注释","*****************车辆明细字段的输入框代码*****************")}
	${lz:set("注释","before$details和after$details变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.details==true">
	${before$details}
	<dl class="details " minor  ref="details" style="width:98%;">
		<dt>车辆明细:</dt>
		<s:if test="#request.CTRL.e.details==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csLongOrder$details!=null">${csLongOrder$details}</s:if><s:else>
		  		<s:if test="csLongOrder.csloId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="details" name="details" frameborder='0' src="" url="${basePath}${proname}/service/longorderdetail.do?inFrame=true&canQuery=false&fields=cslodCar,cslodPrice,cslodSetTime,cslodRetTime,cslodStatus&cslodLongOrder=${csLongOrder.csloId}&ctrl={queryable:false,fields:{cslodLongOrder:{visible:true,editable:false,defaultValue:${csLongOrder.csloId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#details').attr('src',$('#details').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑车辆明细
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车辆明细字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csLongOrder.csloId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="details" name="details" frameborder='0' src="" url="${basePath}${proname}/service/longorderdetail.do?inFrame=true&canQuery=false&fields=cslodCar,cslodPrice,cslodSetTime,cslodRetTime,cslodStatus&cslodLongOrder=${csLongOrder.csloId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#details').attr('src',$('#details').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$details}
	</s:if>
	
	${lz:set("注释","*****************缴费明细字段的输入框代码*****************")}
	${lz:set("注释","before$fees和after$fees变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.fees==true">
	${before$fees}
	<dl class="fees " minor  ref="fees" style="width:98%;">
		<dt>缴费明细:</dt>
		<s:if test="#request.CTRL.e.fees==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csLongOrder$fees!=null">${csLongOrder$fees}</s:if><s:else>
		  		<s:if test="csLongOrder.csloId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="fees" name="fees" frameborder='0' src="" url="${basePath}${proname}/service/longorderfee.do?inFrame=true&canQuery=false&fields=cslofName,cslofType,cslofAmmount,cslofState,cslofStatus&cslofLongorder=${csLongOrder.csloId}&ctrl={queryable:false,fields:{cslofLongorder:{visible:true,editable:false,defaultValue:${csLongOrder.csloId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#fees').attr('src',$('#fees').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑缴费明细
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****缴费明细字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csLongOrder.csloId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="fees" name="fees" frameborder='0' src="" url="${basePath}${proname}/service/longorderfee.do?inFrame=true&canQuery=false&fields=cslofName,cslofType,cslofAmmount,cslofState,cslofStatus&cslofLongorder=${csLongOrder.csloId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#fees').attr('src',$('#fees').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$fees}
	</s:if>
	
	${lz:set("注释","*****************车辆数字段的输入框代码*****************")}
	${lz:set("注释","before$csloCount和after$csloCount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csloCount==true">
	${before$csloCount}
	<dl class="csloCount " major  ref="csloCount" >
		<dt>车&nbsp;&nbsp;辆&nbsp;&nbsp;数:</dt>
		<s:if test="#request.CTRL.e.csloCount==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csLongOrder$csloCount!=null">${csLongOrder$csloCount}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLongOrder.csloCount" id="csloCount"  value="${csLongOrder.csloCount}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长租订单的车辆数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车辆数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloCount">${csLongOrder.csloCount}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloCount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloCount}
	</s:if>
	
	${lz:set("注释","*****************订单租期字段的输入框代码*****************")}
	${lz:set("注释","before$csloLeaseTerm和after$csloLeaseTerm变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csloLeaseTerm==true">
	${before$csloLeaseTerm}
	<dl class="csloLeaseTerm " major  ref="csloLeaseTerm" >
		<dt>订单租期:</dt>
		<s:if test="#request.CTRL.e.csloLeaseTerm==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csLongOrder$csloLeaseTerm!=null">${csLongOrder$csloLeaseTerm}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLongOrder.csloLeaseTerm" id="csloLeaseTerm"  value="${csLongOrder.csloLeaseTerm}"/>
	 	 </s:else>
	 	 个月
	 	 <b>*</b>
	 	 <em>请输入长租订单的订单租期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单租期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloLeaseTerm">${csLongOrder.csloLeaseTerm}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloLeaseTerm$}
	 	 个月 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloLeaseTerm}
	</s:if>
	
	${lz:set("注释","*****************押金合计字段的输入框代码*****************")}
	${lz:set("注释","before$csloDeposit和after$csloDeposit变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csloDeposit==true">
	${before$csloDeposit}
	<dl class="csloDeposit " major  ref="csloDeposit" >
		<dt>押金合计:</dt>
		<s:if test="#request.CTRL.e.csloDeposit==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csLongOrder$csloDeposit!=null">${csLongOrder$csloDeposit}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLongOrder.csloDeposit" id="csloDeposit"  value="${csLongOrder.csloDeposit}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长租订单的押金合计</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****押金合计字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloDeposit">${csLongOrder.csloDeposit}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloDeposit$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloDeposit}
	</s:if>
	
	${lz:set("注释","*****************付款周期字段的输入框代码*****************")}
	${lz:set("注释","before$csloPayCycle和after$csloPayCycle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csloPayCycle==true">
	${before$csloPayCycle}
	<dl class="csloPayCycle " major  ref="csloPayCycle" >
		<dt>付款周期:</dt>
		<s:if test="#request.CTRL.e.csloPayCycle==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csLongOrder$csloPayCycle!=null">${csLongOrder$csloPayCycle}</s:if><s:else>
		 	<select class="narrow" id="csloPayCycle" name="csLongOrder.csloPayCycle">
		 		<option value="">请选择</option>
				<option value="0" ${csLongOrder.csloPayCycle==0?"selected":""}>一次付清</option>
				<option value="1" ${csLongOrder.csloPayCycle==1?"selected":""}>按月付</option>
				<option value="2" ${csLongOrder.csloPayCycle==2?"selected":""}>按季付</option>
				<option value="3" ${csLongOrder.csloPayCycle==3?"selected":""}>半年付</option>
				<option value="4" ${csLongOrder.csloPayCycle==4?"selected":""}>按年付</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的付款周期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****付款周期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloPayCycle">${csLongOrder.csloPayCycle}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloPayCycle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloPayCycle}
	</s:if>
	
	${lz:set("注释","*****************应付金额字段的输入框代码*****************")}
	${lz:set("注释","before$csloNeedPay和after$csloNeedPay变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csloNeedPay==true">
	${before$csloNeedPay}
	<dl class="csloNeedPay " major  ref="csloNeedPay" >
		<dt>应付金额:</dt>
		<s:if test="#request.CTRL.e.csloNeedPay==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csLongOrder$csloNeedPay!=null">${csLongOrder$csloNeedPay}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLongOrder.csloNeedPay" id="csloNeedPay"  value="${csLongOrder.csloNeedPay}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长租订单的应付金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****应付金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloNeedPay">${csLongOrder.csloNeedPay}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloNeedPay$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloNeedPay}
	</s:if>
	
	${lz:set("注释","*****************实付金额字段的输入框代码*****************")}
	${lz:set("注释","before$csloRealPay和after$csloRealPay变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csloRealPay==true">
	${before$csloRealPay}
	<dl class="csloRealPay " major  ref="csloRealPay" >
		<dt>实付金额:</dt>
		<s:if test="#request.CTRL.e.csloRealPay==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csLongOrder$csloRealPay!=null">${csLongOrder$csloRealPay}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLongOrder.csloRealPay" id="csloRealPay"  value="${csLongOrder.csloRealPay}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长租订单的实付金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实付金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloRealPay">${csLongOrder.csloRealPay}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloRealPay$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloRealPay}
	</s:if>
	
	${lz:set("注释","*****************合同签订时间字段的输入框代码*****************")}
	${lz:set("注释","before$csloPactTime和after$csloPactTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csloPactTime==true">
	${before$csloPactTime}
	<dl class="csloPactTime " major  ref="csloPactTime" >
		<dt>合同签订时间:</dt>
		<s:if test="#request.CTRL.e.csloPactTime==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csLongOrder$csloPactTime!=null">${csLongOrder$csloPactTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csLongOrder.csloPactTime" id="csloPactTime"  value="<s:date name="csLongOrder.csloPactTime" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的合同签订时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同签订时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloPactTime">${csLongOrder.csloPactTime}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloPactTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloPactTime}
	</s:if>
	
	${lz:set("注释","*****************合同开始时间字段的输入框代码*****************")}
	${lz:set("注释","before$csloStartTime和after$csloStartTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csloStartTime==true">
	${before$csloStartTime}
	<dl class="csloStartTime " major  ref="csloStartTime" >
		<dt>合同开始时间:</dt>
		<s:if test="#request.CTRL.e.csloStartTime==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csLongOrder$csloStartTime!=null">${csLongOrder$csloStartTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csLongOrder.csloStartTime" id="csloStartTime"  value="<s:date name="csLongOrder.csloStartTime" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的合同开始时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同开始时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloStartTime">${csLongOrder.csloStartTime}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloStartTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloStartTime}
	</s:if>
	
	${lz:set("注释","*****************合同结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$csloFinishTime和after$csloFinishTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csloFinishTime==true">
	${before$csloFinishTime}
	<dl class="csloFinishTime " major  ref="csloFinishTime" >
		<dt>合同结束时间:</dt>
		<s:if test="#request.CTRL.e.csloFinishTime==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csLongOrder$csloFinishTime!=null">${csLongOrder$csloFinishTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csLongOrder.csloFinishTime" id="csloFinishTime"  value="<s:date name="csLongOrder.csloFinishTime" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的合同结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloFinishTime">${csLongOrder.csloFinishTime}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloFinishTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloFinishTime}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csloUpdateTime和after$csloUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csloUpdateTime==true">
	${before$csloUpdateTime}
	<dl class="csloUpdateTime " major  ref="csloUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csloUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csLongOrder$csloUpdateTime!=null">${csLongOrder$csloUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csLongOrder.csloUpdateTime" id="csloUpdateTime"  value="<s:date name="csLongOrder.csloUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloUpdateTime">${csLongOrder.csloUpdateTime}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csloAddTime和after$csloAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csloAddTime==true">
	${before$csloAddTime}
	<dl class="csloAddTime " major  ref="csloAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csloAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csLongOrder$csloAddTime!=null">${csLongOrder$csloAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csLongOrder.csloAddTime" id="csloAddTime"  value="<s:date name="csLongOrder.csloAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloAddTime">${csLongOrder.csloAddTime}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloAddTime}
	</s:if>
	
	${lz:set("注释","*****************序列号字段的输入框代码*****************")}
	${lz:set("注释","before$csloSerial和after$csloSerial变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csloSerial==true">
	${before$csloSerial}
	<dl class="csloSerial " minor  ref="csloSerial" >
		<dt>序&nbsp;&nbsp;列&nbsp;&nbsp;号:</dt>
		<s:if test="#request.CTRL.e.csloSerial==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csLongOrder$csloSerial!=null">${csLongOrder$csloSerial}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csLongOrder.csloSerial" id="csloSerial"  value="${csLongOrder.csloSerial}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入长租订单的序列号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****序列号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloSerial">${csLongOrder.csloSerial}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloSerial$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloSerial}
	</s:if>
	
	${lz:set("注释","*****************订单备注字段的输入框代码*****************")}
	${lz:set("注释","before$csloRemark和after$csloRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csloRemark==true">
	${before$csloRemark}
	<dl class="csloRemark " minor  ref="csloRemark" style="width:98%;">
		<dt>订单备注:</dt>
		<s:if test="#request.CTRL.e.csloRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csLongOrder$csloRemark!=null">${csLongOrder$csloRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csloRemark" name="csLongOrder.csloRemark" rows="5">${csLongOrder.csloRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入长租订单的订单备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csLongOrder.csloRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloRemark}
	</s:if>
	
	${lz:set("注释","*****************操作人字段的输入框代码*****************")}
	${lz:set("注释","before$csloEditor和after$csloEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csloEditor==true">
	${before$csloEditor}
	<dl class="csloEditor " major  ref="csloEditor" >
		<dt>操&nbsp;&nbsp;作&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csloEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csLongOrder$csloEditor!=null">${csLongOrder$csloEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csloEditor" name="csLongOrder.csloEditor">
		 				<option selected value="${csLongOrder.csloEditor}">
		 					${get:SrvUser(csLongOrder.csloEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csloEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csloEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****操作人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloEditor">${csLongOrder.csloEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csLongOrder.csloEditor}',{ctrl:{editable:false,visible:true}})">
			 ${csLongOrder.csloEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloEditor}
	</s:if>
	
	${lz:set("注释","*****************报警信息字段的输入框代码*****************")}
	${lz:set("注释","before$csloError和after$csloError变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csloError==true">
	${before$csloError}
	<dl class="csloError " major  ref="csloError" >
		<dt>报警信息:</dt>
		<s:if test="#request.CTRL.e.csloError==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csLongOrder$csloError!=null">${csLongOrder$csloError}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csLongOrder.csloError" id="csloError"  value="${csLongOrder.csloError}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入长租订单的报警信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****报警信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloError">${csLongOrder.csloError}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloError$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloError}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csloStatus和after$csloStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csloStatus==true">
	${before$csloStatus}
	<dl class="csloStatus " major  ref="csloStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csloStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csLongOrder$csloStatus!=null">${csLongOrder$csloStatus}</s:if><s:else>
		 	<select class="narrow" id="csloStatus" name="csLongOrder.csloStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csLongOrder.csloStatus==0?"selected":""}>已预定</option>
				<option value="1" ${csLongOrder.csloStatus==1?"selected":""}>使用中</option>
				<option value="2" ${csLongOrder.csloStatus==2?"selected":""}>已还车</option>
				<option value="3" ${csLongOrder.csloStatus==3?"selected":""}>已取消</option>
				<option value="4" ${csLongOrder.csloStatus==4?"selected":""}>已完成</option>
				<option value="5" ${csLongOrder.csloStatus==5?"selected":""}>待处理</option>
				<option value="6" ${csLongOrder.csloStatus==6?"selected":""}>已退款</option>
				<option value="7" ${csLongOrder.csloStatus==7?"selected":""}>已作废</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长租订单的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csloStatus">${csLongOrder.csloStatus}</textarea>
		 		<span>
		 	
			 ${csLongOrder.csloStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csloStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csLongOrder.csloId}" name="csLongOrder.csloId" id="csloId" />
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