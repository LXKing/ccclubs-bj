<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/service/trouble.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/service/trouble.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csTrouble.cstId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cstId,cstHost,cstOrder,cstMember,cstCar,cstType,cstDamage,cstDecipt,cstImages,cstHappenTime,cstRemark",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cstId,cstHost,cstOrder,cstMember,cstCar,cstType,cstDamage,cstDecipt,cstImages,cstHappenTime,cstRemark",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cstId,cstHost,cstDamage,cstRepair,cstInvoice,cstInsureFee,cstThirdLoss,cstTrailFee,cstRepayDate,cstRepayType,cstThirdDesc,cstCompensate,cstCompDate,cstRepairPay,cstAgio,cstDecipt,cstImages,cstNotice,cstDealFlag,cstRemark",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cstId,cstHost,cstOrder,cstMember,cstCar,cstType,cstDamage,cstRepairAdr,cstRepairPart,cstRepair,cstInvoice,cstInsureFee,cstMoney,cstThirdLoss,cstTrailFee,cstRepayDate,cstRepayType,cstThirdDesc,cstCompensate,cstCompDate,cstRepairPay,cstAgio,cstDecipt,cstImages,cstNotice,cstDealFlag,cstRemark",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"车辆事故编辑":CTRL.title}</title>
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



<!--LAZY3Q_JSP_BODY-->
<s:if test="#request.CTRL.title=='定损'">
	<%@ include file="trouble_pricing.jsp"%>
</s:if>
<s:if test="#request.CTRL.title=='协商'">
	<%@ include file="trouble_consult.jsp"%>
</s:if>
<s:elseif test="#request.CTRL.title=='维修'">
	<%@ include file="trouble_repair.jsp"%>
</s:elseif>
<s:elseif test="#request.CTRL.title=='扣款'">
	<%@ include file="trouble_deduct.jsp"%>
</s:elseif>
<s:elseif test="#request.CTRL.title=='结案'">
	<%@ include file="trouble_settle.jsp"%>
</s:elseif>
<s:else>
	<script>
		$(function(){
			$("#cstOrder").change(function(){
				$.getObject("${basePath}admin/service/order_info.do",{id:$(this).val()},function(obj){
					$("#cstMember").val(obj.member.id);
					$("#cstMember").prev().html(obj.member.name);					
					$("#cstCar").val(obj.car.id);
					$("#cstCar").prev().html(obj.car.name);
				});
			});			
		});
	</script>
</s:else>
<!--LAZY3Q_JSP_BODY-->



<script>
$(function(){	
	showTips("${tips.value}");
});
window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}";


$(function(){
		if($("#cstDecipt").size()>0){
			 //构造编辑页csTrouble.cstDecipt为html编辑控件
			 KE.init({
				id : 'cstDecipt',
				width : '625px',
				height : '300px' , 
				imageUploadJson : '${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}?app=trouble_edit&type=img${ lz:config("upload.url")==null ? "" : "&remote="}${ lz:config("upload.url")==null ? "" : basePath}${ lz:config("upload.url")==null ? "" : "callback.jsp"}'
			 });
			 KE.create("cstDecipt");
		 }
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#troubleForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cstId==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstHost==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstTitle==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "事故名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "事故名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstOrder==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstOrder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstMember==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstCar==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstCar":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstType==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择事故类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstDamage==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstDamage":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "车损情况最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstRepairAdr==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstRepairAdr":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstRepairPart==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstRepairPart":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "维修部位最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstRepair==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstRepair":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstInvoice==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstInvoice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "开票金额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstInsureFee==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstInsureFee":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "保险定损输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstMoney==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstMoney":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "协商扣款输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstThirdLoss==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstThirdLoss":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "三者损失输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstTrailFee==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstTrailFee":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "拖车费用输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstRepayDate==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstRepayDate":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstRepayType==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstRepayType":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstThirdDesc==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstThirdDesc":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "三者垫付备注最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstCompensate==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstCompensate":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "赔付到账输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstCompDate==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstCompDate":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstRepairPay==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstRepairPay":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "维修费用输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstAgio==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstAgio":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "合计差价输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstDecipt==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstDecipt":function(el){
			if($("#cstDecipt").get(0))
			 	KE.sync("cstDecipt");		 
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "事故描述最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstImages==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstImages":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstHappenTime==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstHappenTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "发生时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstNotice==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstNotice":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择客服通知";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstDealFlag==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstDealFlag":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstAccepter==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstAccepter":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstCaser==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstCaser":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstFinishTime==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstFinishTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstRemark==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstUpdateTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstAddTime==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstStatus==true">
		${lz:set("haveEditable",true)}
		,"csTrouble.cstStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择处理状态";
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
<s:else>
	<form class="form editform" ref="CsTrouble" id="troubleForm" name="troubleForm" action="${empty CTRL.action?"trouble_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写车辆事故相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csTroubleToken}" name="csTroubleToken" id="csTroubleToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csTrouble.cstId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cstId和after$cstId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstId==true">
	${before$cstId}
	<dl class="cstId ${CTRL.e.cstId?"hidden":""}" major  ref="cstId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cstId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csTrouble$cstId!=null">${csTrouble$cstId}</s:if><s:else>
			<input type="hidden" value="${csTrouble.cstId}" name="csTrouble.cstId" id="cstId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstId">${csTrouble.cstId}</textarea>
		 		<span>
		 	
			 ${csTrouble.cstId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cstHost和after$cstHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstHost==true">
	${before$cstHost}
	<dl class="cstHost " major  ref="cstHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cstHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csTrouble$cstHost!=null">${csTrouble$cstHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cstHost" name="csTrouble.cstHost">
		 				<option selected value="${csTrouble.cstHost}">
		 					${get:SrvHost(csTrouble.cstHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cstHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cstHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstHost">${csTrouble.cstHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csTrouble.cstHost}',{ctrl:{editable:false,visible:true}})">
			 ${csTrouble.cstHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstHost}
	</s:if>
	
	${lz:set("注释","*****************事故名称字段的输入框代码*****************")}
	${lz:set("注释","before$cstTitle和after$cstTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstTitle==true">
	${before$cstTitle}
	<dl class="cstTitle " major  ref="cstTitle" >
		<dt>事故名称:</dt>
		<s:if test="#request.CTRL.e.cstTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csTrouble$cstTitle!=null">${csTrouble$cstTitle}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csTrouble.cstTitle" id="cstTitle"  value="${csTrouble.cstTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入车辆事故的事故名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****事故名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstTitle">${csTrouble.cstTitle}</textarea>
		 		<span>
		 	
			 ${csTrouble.cstTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstTitle}
	</s:if>
	
	${lz:set("注释","*****************事故订单字段的输入框代码*****************")}
	${lz:set("注释","before$cstOrder和after$cstOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstOrder==true">
	${before$cstOrder}
	<dl class="cstOrder " major  ref="cstOrder" >
		<dt>事故订单:</dt>
		<s:if test="#request.CTRL.e.cstOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csTrouble$cstOrder!=null">${csTrouble$cstOrder}</s:if><s:else>
		 			<input title="请输入系统订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={cstHost}" type="text" id="cstOrder" name="csTrouble.cstOrder" text="${get:CsOrder(csTrouble.cstOrder).csoId}" value="${csTrouble.cstOrder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cstOrder').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?key='+$('#cstOrder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****事故订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstOrder">${csTrouble.cstOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?key=${csTrouble.cstOrder}',{ctrl:{editable:false,visible:true}})">
			 ${csTrouble.cstOrder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstOrder}
	</s:if>
	
	${lz:set("注释","*****************事故会员字段的输入框代码*****************")}
	${lz:set("注释","before$cstMember和after$cstMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstMember==true">
	${before$cstMember}
	<dl class="cstMember " major  ref="cstMember" >
		<dt>事故会员:</dt>
		<s:if test="#request.CTRL.e.cstMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csTrouble$cstMember!=null">${csTrouble$cstMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={cstHost}" type="text" id="cstMember" name="csTrouble.cstMember" text="${get:CsMember(csTrouble.cstMember).csmName}" value="${csTrouble.cstMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cstMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#cstMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****事故会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstMember">${csTrouble.cstMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csTrouble.cstMember}',{ctrl:{editable:false,visible:true}})">
			 ${csTrouble.cstMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstMember}
	</s:if>
	
	${lz:set("注释","*****************事故车辆字段的输入框代码*****************")}
	${lz:set("注释","before$cstCar和after$cstCar变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstCar==true">
	${before$cstCar}
	<dl class="cstCar " major  ref="cstCar" >
		<dt>事故车辆:</dt>
		<s:if test="#request.CTRL.e.cstCar==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csTrouble$cstCar!=null">${csTrouble$cstCar}</s:if><s:else>
		 			<input title="请输入车辆车牌号进行查询" class="combox narrow" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={cstHost}" type="text" id="cstCar" name="csTrouble.cstCar" text="${get:CsCar(csTrouble.cstCar).cscNumber}" value="${csTrouble.cstCar}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cstCar').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?key='+$('#cstCar').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****事故车辆字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstCar">${csTrouble.cstCar}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/car_details.do?key=${csTrouble.cstCar}',{ctrl:{editable:false,visible:true}})">
			 ${csTrouble.cstCar$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstCar}
	</s:if>
	
	${lz:set("注释","*****************事故类型字段的输入框代码*****************")}
	${lz:set("注释","before$cstType和after$cstType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstType==true">
	${before$cstType}
	<dl class="cstType " major  ref="cstType" >
		<dt>事故类型:</dt>
		<s:if test="#request.CTRL.e.cstType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csTrouble$cstType!=null">${csTrouble$cstType}</s:if><s:else>
		 	<select class="narrow" id="cstType" name="csTrouble.cstType">
		 		<option value="">请选择</option>
				<option value="0" ${csTrouble.cstType==0?"selected":""}>简易事故</option>
				<option value="1" ${csTrouble.cstType==1?"selected":""}>单方事故</option>
				<option value="2" ${csTrouble.cstType==2?"selected":""}>我方全责</option>
				<option value="3" ${csTrouble.cstType==3?"selected":""}>对方全责</option>
				<option value="4" ${csTrouble.cstType==4?"selected":""}>同等责任</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆事故的事故类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****事故类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstType">${csTrouble.cstType}</textarea>
		 		<span>
		 	
			 ${csTrouble.cstType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstType}
	</s:if>
	
	${lz:set("注释","*****************车损情况字段的输入框代码*****************")}
	${lz:set("注释","before$cstDamage和after$cstDamage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstDamage==true">
	${before$cstDamage}
	<dl class="cstDamage " minor  ref="cstDamage" style="width:98%;">
		<dt>车损情况:</dt>
		<s:if test="#request.CTRL.e.cstDamage==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csTrouble$cstDamage!=null">${csTrouble$cstDamage}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csTrouble.cstDamage" id="cstDamage"  value="${csTrouble.cstDamage}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆事故的车损情况</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车损情况字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cstDamage">${csTrouble.cstDamage}</textarea>
		 		<span>
		 	
			 ${csTrouble.cstDamage$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstDamage}
	</s:if>
	
	${lz:set("注释","*****************送修地点字段的输入框代码*****************")}
	${lz:set("注释","before$cstRepairAdr和after$cstRepairAdr变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstRepairAdr==true">
	${before$cstRepairAdr}
	<dl class="cstRepairAdr " minor  ref="cstRepairAdr" >
		<dt>送修地点:</dt>
		<s:if test="#request.CTRL.e.cstRepairAdr==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csTrouble$cstRepairAdr!=null">${csTrouble$cstRepairAdr}</s:if><s:else>
		 	<select class="narrow" id="cstRepairAdr" name="csTrouble.cstRepairAdr">
		 		<option value="">请选择</option>
 					${lz:set("items", lz:query$$("ccclubs_system","select * from cs_repair_adr where 1=1  and (csra_host in (?) or ? is null ) ",ccclubs_login.suHost,ccclubs_login.suHost) )}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.csraId}" ${csTrouble.cstRepairAdr==item.csraId?"selected":""}>${item.csraName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cstRepairAdr').val())==''){return;};window.href('${basePath}${proname}/service/repairadr_details.do?key='+$('#cstRepairAdr').val(),{ctrl:{editable:false}})"></a>
	 			<a class="reladd" config="{
	 				action:'${basePath}${proname}/service/repairadr_edit.do?rd=1'
	 				,callback:function(object){
	 					$('<option selected value='+object.csraId+'>'+object.csraName+'</option>').appendTo('#cstRepairAdr');
	 				}
	 			}" href="javascript:void(0);"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆事故的送修地点</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****送修地点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstRepairAdr">${csTrouble.cstRepairAdr}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/repairadr_details.do?key=${csTrouble.cstRepairAdr}',{ctrl:{editable:false,visible:true}})">
			 ${csTrouble.cstRepairAdr$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstRepairAdr}
	</s:if>
	
	${lz:set("注释","*****************维修部位字段的输入框代码*****************")}
	${lz:set("注释","before$cstRepairPart和after$cstRepairPart变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstRepairPart==true">
	${before$cstRepairPart}
	<dl class="cstRepairPart " minor  ref="cstRepairPart" style="width:98%;">
		<dt>维修部位:</dt>
		<s:if test="#request.CTRL.e.cstRepairPart==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csTrouble$cstRepairPart!=null">${csTrouble$cstRepairPart}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csTrouble.cstRepairPart" id="cstRepairPart"  value="${csTrouble.cstRepairPart}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆事故的维修部位</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****维修部位字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cstRepairPart">${csTrouble.cstRepairPart}</textarea>
		 		<span>
		 	
			 ${csTrouble.cstRepairPart$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstRepairPart}
	</s:if>
	
	${lz:set("注释","*****************维修详细信息字段的输入框代码*****************")}
	${lz:set("注释","before$cstRepair和after$cstRepair变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstRepair==true">
	${before$cstRepair}
	<dl class="cstRepair " minor  ref="cstRepair" >
		<dt>维修详细信息:</dt>
		<s:if test="#request.CTRL.e.cstRepair==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csTrouble$cstRepair!=null">${csTrouble$cstRepair}</s:if><s:else>
		 			<input title="请输入车辆维修报修描述进行查询" class="combox narrow" action="${basePath}${proname}/service/repair_query.do?value={param}&csrHost={cstHost}" type="text" id="cstRepair" name="csTrouble.cstRepair" text="${get:CsRepair(csTrouble.cstRepair).csrProfile}" value="${csTrouble.cstRepair}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cstRepair').val())==''){return;};window.href('${basePath}${proname}/service/repair_details.do?key='+$('#cstRepair').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****维修详细信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstRepair">${csTrouble.cstRepair}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/repair_details.do?key=${csTrouble.cstRepair}',{ctrl:{editable:false,visible:true}})">
			 ${csTrouble.cstRepair$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstRepair}
	</s:if>
	
	${lz:set("注释","*****************开票金额字段的输入框代码*****************")}
	${lz:set("注释","before$cstInvoice和after$cstInvoice变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstInvoice==true">
	${before$cstInvoice}
	<dl class="cstInvoice " minor  ref="cstInvoice" >
		<dt>开票金额:</dt>
		<s:if test="#request.CTRL.e.cstInvoice==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csTrouble$cstInvoice!=null">${csTrouble$cstInvoice}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csTrouble.cstInvoice" id="cstInvoice"  value="${csTrouble.cstInvoice}"/>
	 	 </s:else>
	 	 元
	 	 
	 	 <em>请输入车辆事故的开票金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****开票金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstInvoice">${csTrouble.cstInvoice}</textarea>
		 		<span>
		 	
			 ${csTrouble.cstInvoice$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstInvoice}
	</s:if>
	
	${lz:set("注释","*****************保险定损字段的输入框代码*****************")}
	${lz:set("注释","before$cstInsureFee和after$cstInsureFee变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstInsureFee==true">
	${before$cstInsureFee}
	<dl class="cstInsureFee " major  ref="cstInsureFee" >
		<dt>保险定损:</dt>
		<s:if test="#request.CTRL.e.cstInsureFee==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csTrouble$cstInsureFee!=null">${csTrouble$cstInsureFee}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csTrouble.cstInsureFee" id="cstInsureFee"  value="${csTrouble.cstInsureFee}"/>
	 	 </s:else>
	 	 元
	 	 
	 	 <em>请输入车辆事故的保险定损</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****保险定损字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstInsureFee">${csTrouble.cstInsureFee}</textarea>
		 		<span>
		 	
			 ${csTrouble.cstInsureFee$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstInsureFee}
	</s:if>
	
	${lz:set("注释","*****************协商扣款字段的输入框代码*****************")}
	${lz:set("注释","before$cstMoney和after$cstMoney变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstMoney==true">
	${before$cstMoney}
	<dl class="cstMoney " major  ref="cstMoney" >
		<dt>协商扣款:</dt>
		<s:if test="#request.CTRL.e.cstMoney==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csTrouble$cstMoney!=null">${csTrouble$cstMoney}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csTrouble.cstMoney" id="cstMoney"  value="${csTrouble.cstMoney}"/>
	 	 </s:else>
	 	 元
	 	 <b>*</b>
	 	 <em>将从会员保证金里扣除的钱</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****协商扣款字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstMoney">${csTrouble.cstMoney}</textarea>
		 		<span>
		 	
			 ${csTrouble.cstMoney$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstMoney}
	</s:if>
	
	${lz:set("注释","*****************三者损失字段的输入框代码*****************")}
	${lz:set("注释","before$cstThirdLoss和after$cstThirdLoss变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstThirdLoss==true">
	${before$cstThirdLoss}
	<dl class="cstThirdLoss " major  ref="cstThirdLoss" >
		<dt>三者损失:</dt>
		<s:if test="#request.CTRL.e.cstThirdLoss==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csTrouble$cstThirdLoss!=null">${csTrouble$cstThirdLoss}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csTrouble.cstThirdLoss" id="cstThirdLoss"  value="${csTrouble.cstThirdLoss}"/>
	 	 </s:else>
	 	 元
	 	 
	 	 <em>请输入车辆事故的三者损失</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****三者损失字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstThirdLoss">${csTrouble.cstThirdLoss}</textarea>
		 		<span>
		 	
			 ${csTrouble.cstThirdLoss$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstThirdLoss}
	</s:if>
	
	${lz:set("注释","*****************拖车费用字段的输入框代码*****************")}
	${lz:set("注释","before$cstTrailFee和after$cstTrailFee变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstTrailFee==true">
	${before$cstTrailFee}
	<dl class="cstTrailFee " minor  ref="cstTrailFee" >
		<dt>拖车费用:</dt>
		<s:if test="#request.CTRL.e.cstTrailFee==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csTrouble$cstTrailFee!=null">${csTrouble$cstTrailFee}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csTrouble.cstTrailFee" id="cstTrailFee"  value="${csTrouble.cstTrailFee}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆事故的拖车费用</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****拖车费用字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstTrailFee">${csTrouble.cstTrailFee}</textarea>
		 		<span>
		 	
			 ${csTrouble.cstTrailFee$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstTrailFee}
	</s:if>
	
	${lz:set("注释","*****************返还垫付时间字段的输入框代码*****************")}
	${lz:set("注释","before$cstRepayDate和after$cstRepayDate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstRepayDate==true">
	${before$cstRepayDate}
	<dl class="cstRepayDate " minor  ref="cstRepayDate" >
		<dt>返还垫付时间:</dt>
		<s:if test="#request.CTRL.e.cstRepayDate==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csTrouble$cstRepayDate!=null">${csTrouble$cstRepayDate}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csTrouble.cstRepayDate" id="cstRepayDate"  value="<s:date name="csTrouble.cstRepayDate" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>返还给垫付人垫付款的时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****返还垫付时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstRepayDate">${csTrouble.cstRepayDate}</textarea>
		 		<span>
		 	
			 ${csTrouble.cstRepayDate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstRepayDate}
	</s:if>
	
	${lz:set("注释","*****************返还垫付方式字段的输入框代码*****************")}
	${lz:set("注释","before$cstRepayType和after$cstRepayType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstRepayType==true">
	${before$cstRepayType}
	<dl class="cstRepayType " minor  ref="cstRepayType" >
		<dt>返还垫付方式:</dt>
		<s:if test="#request.CTRL.e.cstRepayType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csTrouble$cstRepayType!=null">${csTrouble$cstRepayType}</s:if><s:else>
		 	<select class="narrow" id="cstRepayType" name="csTrouble.cstRepayType">
		 		<option value="">请选择</option>
				<option value="0" ${csTrouble.cstRepayType==0?"selected":""}>银行转帐</option>
				<option value="1" ${csTrouble.cstRepayType==1?"selected":""}>返还保证金</option>
				<option value="2" ${csTrouble.cstRepayType==2?"selected":""}>返还现金</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆事故的返还垫付方式</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****返还垫付方式字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstRepayType">${csTrouble.cstRepayType}</textarea>
		 		<span>
		 	
			 ${csTrouble.cstRepayType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstRepayType}
	</s:if>
	
	${lz:set("注释","*****************三者垫付备注字段的输入框代码*****************")}
	${lz:set("注释","before$cstThirdDesc和after$cstThirdDesc变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstThirdDesc==true">
	${before$cstThirdDesc}
	<dl class="cstThirdDesc " minor  ref="cstThirdDesc" style="width:98%;">
		<dt>三者垫付备注:</dt>
		<s:if test="#request.CTRL.e.cstThirdDesc==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csTrouble$cstThirdDesc!=null">${csTrouble$cstThirdDesc}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csTrouble.cstThirdDesc" id="cstThirdDesc"  value="${csTrouble.cstThirdDesc}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>三者损失、垫付等其它备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****三者垫付备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cstThirdDesc">${csTrouble.cstThirdDesc}</textarea>
		 		<span>
		 	
			 ${csTrouble.cstThirdDesc$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstThirdDesc}
	</s:if>
	
	${lz:set("注释","*****************赔付到账字段的输入框代码*****************")}
	${lz:set("注释","before$cstCompensate和after$cstCompensate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstCompensate==true">
	${before$cstCompensate}
	<dl class="cstCompensate " minor  ref="cstCompensate" >
		<dt>赔付到账:</dt>
		<s:if test="#request.CTRL.e.cstCompensate==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csTrouble$cstCompensate!=null">${csTrouble$cstCompensate}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csTrouble.cstCompensate" id="cstCompensate"  value="${csTrouble.cstCompensate}"/>
	 	 </s:else>
	 	 元
	 	 
	 	 <em>请输入车辆事故的赔付到账</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****赔付到账字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstCompensate">${csTrouble.cstCompensate}</textarea>
		 		<span>
		 	
			 ${csTrouble.cstCompensate$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstCompensate}
	</s:if>
	
	${lz:set("注释","*****************赔付到账时间字段的输入框代码*****************")}
	${lz:set("注释","before$cstCompDate和after$cstCompDate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstCompDate==true">
	${before$cstCompDate}
	<dl class="cstCompDate " minor  ref="cstCompDate" >
		<dt>赔付到账时间:</dt>
		<s:if test="#request.CTRL.e.cstCompDate==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csTrouble$cstCompDate!=null">${csTrouble$cstCompDate}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csTrouble.cstCompDate" id="cstCompDate"  value="<s:date name="csTrouble.cstCompDate" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆事故的赔付到账时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****赔付到账时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstCompDate">${csTrouble.cstCompDate}</textarea>
		 		<span>
		 	
			 ${csTrouble.cstCompDate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstCompDate}
	</s:if>
	
	${lz:set("注释","*****************维修费用字段的输入框代码*****************")}
	${lz:set("注释","before$cstRepairPay和after$cstRepairPay变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstRepairPay==true">
	${before$cstRepairPay}
	<dl class="cstRepairPay " minor  ref="cstRepairPay" >
		<dt>维修费用:</dt>
		<s:if test="#request.CTRL.e.cstRepairPay==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csTrouble$cstRepairPay!=null">${csTrouble$cstRepairPay}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csTrouble.cstRepairPay" id="cstRepairPay"  value="${csTrouble.cstRepairPay}"/>
	 	 </s:else>
	 	 元
	 	 
	 	 <em>请输入车辆事故的维修费用</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****维修费用字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstRepairPay">${csTrouble.cstRepairPay}</textarea>
		 		<span>
		 	
			 ${csTrouble.cstRepairPay$}
	 	 元 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstRepairPay}
	</s:if>
	
	${lz:set("注释","*****************合计差价字段的输入框代码*****************")}
	${lz:set("注释","before$cstAgio和after$cstAgio变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstAgio==true">
	${before$cstAgio}
	<dl class="cstAgio " major  ref="cstAgio" >
		<dt>合计差价:</dt>
		<s:if test="#request.CTRL.e.cstAgio==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csTrouble$cstAgio!=null">${csTrouble$cstAgio}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csTrouble.cstAgio" id="cstAgio"  value="${csTrouble.cstAgio}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入车辆事故的合计差价</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合计差价字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstAgio">${csTrouble.cstAgio}</textarea>
		 		<span>
		 	
			 ${csTrouble.cstAgio$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstAgio}
	</s:if>
	
	${lz:set("注释","*****************事故描述字段的输入框代码*****************")}
	${lz:set("注释","before$cstDecipt和after$cstDecipt变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstDecipt==true">
	${before$cstDecipt}
	<dl class="cstDecipt " minor  ref="cstDecipt" style="width:98%;">
		<dt>事故描述:</dt>
		<s:if test="#request.CTRL.e.cstDecipt==true">
		${lz:set("haveEditable",true)}
		<dd input="html">
		<s:if test="#request.csTrouble$cstDecipt!=null">${csTrouble$cstDecipt}</s:if><s:else>
		    <textarea class="input wide"  id="cstDecipt" name="csTrouble.cstDecipt" style="height:300px;visibility:hidden;">${csTrouble.cstDecipt}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆事故的事故描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****事故描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csTrouble.cstDecipt$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstDecipt}
	</s:if>
	
	${lz:set("注释","*****************图片列表字段的输入框代码*****************")}
	${lz:set("注释","before$cstImages和after$cstImages变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstImages==true">
	${before$cstImages}
	<dl class="cstImages " minor  ref="cstImages" style="width:98%;">
		<dt>图片列表:</dt>
		<s:if test="#request.CTRL.e.cstImages==true">
		${lz:set("haveEditable",true)}
		<dd input="imagelist">
		<s:if test="#request.csTrouble$cstImages!=null">${csTrouble$cstImages}</s:if><s:else>
		    <div id="cstImages" class="wide input imagelist">
			<s:generator val="csTrouble.cstImages" separator=",">
				<s:iterator id="item">
					${lz:set("isVacant",lz:vacant(item))}
		 			<s:if test="#request.isVacant==false">
					<div><a href="${item}" target="_blank"><img src="${item}"/><input type="hidden" name="csTrouble.cstImages" value="${item}"/></a><span onclick="$(this).parent().remove();">x</span></div>
					</s:if>
				</s:iterator>
			</s:generator>
			</div>
			<button type="button" onclick="$.upload({type:'img',count:10,callback:function(url){if(url)$('#cstImages').append('<div><a href=&quot;'+url+'&quot; target=&quot;_blank&quot;><img src=&quot;'+url+'&quot;/><input type=&quot;hidden&quot; name=&quot;csTrouble.cstImages&quot; value=&quot;'+url+'&quot;/></a><span onclick=&quot;$(this).parent().remove();&quot;>x</span></div>');}})" class="button">添加</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传车辆事故的图片列表</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****图片列表字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cstImages">${csTrouble.cstImages}</textarea>
		 		<span>
		 	 <s:generator val="csTrouble.cstImages" separator=",">
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
	${after$cstImages}
	</s:if>
	
	${lz:set("注释","*****************发生时间字段的输入框代码*****************")}
	${lz:set("注释","before$cstHappenTime和after$cstHappenTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstHappenTime==true">
	${before$cstHappenTime}
	<dl class="cstHappenTime " major  ref="cstHappenTime" >
		<dt>发生时间:</dt>
		<s:if test="#request.CTRL.e.cstHappenTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csTrouble$cstHappenTime!=null">${csTrouble$cstHappenTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csTrouble.cstHappenTime" id="cstHappenTime"  value="<s:date name="csTrouble.cstHappenTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆事故的发生时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****发生时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstHappenTime">${csTrouble.cstHappenTime}</textarea>
		 		<span>
		 	
			 ${csTrouble.cstHappenTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstHappenTime}
	</s:if>
	
	${lz:set("注释","*****************客服通知字段的输入框代码*****************")}
	${lz:set("注释","before$cstNotice和after$cstNotice变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstNotice==true">
	${before$cstNotice}
	<dl class="cstNotice " major  ref="cstNotice" >
		<dt>客服通知:</dt>
		<s:if test="#request.CTRL.e.cstNotice==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csTrouble$cstNotice!=null">${csTrouble$cstNotice}</s:if><s:else>
		 	<select class="narrow" id="cstNotice" name="csTrouble.cstNotice">
		 		<option value="">请选择</option>
				<option value="0" ${csTrouble.cstNotice==0?"selected":""}>未通知</option>
				<option value="1" ${csTrouble.cstNotice==1?"selected":""}>已通知</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆事故的客服通知</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****客服通知字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstNotice">${csTrouble.cstNotice}</textarea>
		 		<span>
		 	
			 ${csTrouble.cstNotice$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstNotice}
	</s:if>
	
	${lz:set("注释","*****************受理标记字段的输入框代码*****************")}
	${lz:set("注释","before$cstDealFlag和after$cstDealFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstDealFlag==true">
	${before$cstDealFlag}
	<dl class="cstDealFlag " major  ref="cstDealFlag" >
		<dt>受理标记:</dt>
		<s:if test="#request.CTRL.e.cstDealFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csTrouble$cstDealFlag!=null">${csTrouble$cstDealFlag}</s:if><s:else>
		 	<select class="narrow" id="cstDealFlag" name="csTrouble.cstDealFlag">
		 		<option value="">请选择</option>
				<option value="0" ${csTrouble.cstDealFlag==0?"selected":""}>默认</option>
				<option value="1" ${csTrouble.cstDealFlag==1?"selected":""}>配合</option>
				<option value="2" ${csTrouble.cstDealFlag==2?"selected":""}>不配合</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆事故的受理标记</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****受理标记字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstDealFlag">${csTrouble.cstDealFlag}</textarea>
		 		<span>
		 	
			 ${csTrouble.cstDealFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstDealFlag}
	</s:if>
	
	${lz:set("注释","*****************受理人员字段的输入框代码*****************")}
	${lz:set("注释","before$cstAccepter和after$cstAccepter变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstAccepter==true">
	${before$cstAccepter}
	<dl class="cstAccepter " major  ref="cstAccepter" >
		<dt>受理人员:</dt>
		<s:if test="#request.CTRL.e.cstAccepter==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csTrouble$cstAccepter!=null">${csTrouble$cstAccepter}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cstAccepter" name="csTrouble.cstAccepter">
		 				<option selected value="${csTrouble.cstAccepter}">
		 					${get:SrvUser(csTrouble.cstAccepter).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cstAccepter').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cstAccepter').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****受理人员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstAccepter">${csTrouble.cstAccepter}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csTrouble.cstAccepter}',{ctrl:{editable:false,visible:true}})">
			 ${csTrouble.cstAccepter$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstAccepter}
	</s:if>
	
	${lz:set("注释","*****************结案人员字段的输入框代码*****************")}
	${lz:set("注释","before$cstCaser和after$cstCaser变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstCaser==true">
	${before$cstCaser}
	<dl class="cstCaser " minor  ref="cstCaser" >
		<dt>结案人员:</dt>
		<s:if test="#request.CTRL.e.cstCaser==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csTrouble$cstCaser!=null">${csTrouble$cstCaser}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cstCaser" name="csTrouble.cstCaser">
		 				<option selected value="${csTrouble.cstCaser}">
		 					${get:SrvUser(csTrouble.cstCaser).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cstCaser').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cstCaser').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结案人员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstCaser">${csTrouble.cstCaser}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csTrouble.cstCaser}',{ctrl:{editable:false,visible:true}})">
			 ${csTrouble.cstCaser$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstCaser}
	</s:if>
	
	${lz:set("注释","*****************结案时间字段的输入框代码*****************")}
	${lz:set("注释","before$cstFinishTime和after$cstFinishTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstFinishTime==true">
	${before$cstFinishTime}
	<dl class="cstFinishTime " minor  ref="cstFinishTime" >
		<dt>结案时间:</dt>
		<s:if test="#request.CTRL.e.cstFinishTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csTrouble$cstFinishTime!=null">${csTrouble$cstFinishTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csTrouble.cstFinishTime" id="cstFinishTime"  value="<s:date name="csTrouble.cstFinishTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆事故的结案时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结案时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstFinishTime">${csTrouble.cstFinishTime}</textarea>
		 		<span>
		 	
			 ${csTrouble.cstFinishTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstFinishTime}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$cstRemark和after$cstRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstRemark==true">
	${before$cstRemark}
	<dl class="cstRemark " minor  ref="cstRemark" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.cstRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csTrouble$cstRemark!=null">${csTrouble$cstRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="cstRemark" name="csTrouble.cstRemark" rows="5">${csTrouble.cstRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆事故的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csTrouble.cstRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstRemark}
	</s:if>
	
	${lz:set("注释","*****************更新时间字段的输入框代码*****************")}
	${lz:set("注释","before$cstUpdateTime和after$cstUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstUpdateTime==true">
	${before$cstUpdateTime}
	<dl class="cstUpdateTime " minor  ref="cstUpdateTime" >
		<dt>更新时间:</dt>
		<s:if test="#request.CTRL.e.cstUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csTrouble$cstUpdateTime!=null">${csTrouble$cstUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csTrouble.cstUpdateTime" id="cstUpdateTime"  value="<s:date name="csTrouble.cstUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆事故的更新时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****更新时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstUpdateTime">${csTrouble.cstUpdateTime}</textarea>
		 		<span>
		 	
			 ${csTrouble.cstUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cstAddTime和after$cstAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstAddTime==true">
	${before$cstAddTime}
	<dl class="cstAddTime " major  ref="cstAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cstAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csTrouble$cstAddTime!=null">${csTrouble$cstAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csTrouble.cstAddTime" id="cstAddTime"  value="<s:date name="csTrouble.cstAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆事故的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstAddTime">${csTrouble.cstAddTime}</textarea>
		 		<span>
		 	
			 ${csTrouble.cstAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstAddTime}
	</s:if>
	
	${lz:set("注释","*****************处理状态字段的输入框代码*****************")}
	${lz:set("注释","before$cstStatus和after$cstStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstStatus==true">
	${before$cstStatus}
	<dl class="cstStatus " major  ref="cstStatus" >
		<dt>处理状态:</dt>
		<s:if test="#request.CTRL.e.cstStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csTrouble$cstStatus!=null">${csTrouble$cstStatus}</s:if><s:else>
		 	<select class="narrow" id="cstStatus" name="csTrouble.cstStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csTrouble.cstStatus==0?"selected":""}>已录入待定损</option>
				<option value="1" ${csTrouble.cstStatus==1?"selected":""}>已定损待协商</option>
				<option value="2" ${csTrouble.cstStatus==2?"selected":""}>已协商待维修</option>
				<option value="3" ${csTrouble.cstStatus==3?"selected":""}>正在维修</option>
				<option value="4" ${csTrouble.cstStatus==4?"selected":""}>已维修待扣款</option>
				<option value="5" ${csTrouble.cstStatus==5?"selected":""}>已扣款待结案</option>
				<option value="6" ${csTrouble.cstStatus==6?"selected":""}>已结案</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆事故的处理状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****处理状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstStatus">${csTrouble.cstStatus}</textarea>
		 		<span>
		 	
			 ${csTrouble.cstStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csTrouble.cstId}" name="csTrouble.cstId" id="cstId" />
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