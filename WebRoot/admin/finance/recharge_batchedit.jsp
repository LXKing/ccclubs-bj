<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/finance/recharge.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
${lz:set("canAdd",true)}
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/finance/recharge.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csArtifRecord.csarId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csarId,csarHost,csarTitle,csarMember,csarCount,csarMoneyType,csarRecordSubject,csarOrder,csarRelate",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csarId,csarHost,csarTitle,csarMember,csarCount,csarMoneyType,csarRecordSubject,csarOrder,csarRelate",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csarId,csarHost,csarTitle,csarMember,csarCount,csarMoneyType,csarRecordSubject,csarOrder,csarRelate,csarReceived,csarVoucher",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csarId,csarHost,csarTitle,csarMember,csarCount,csarMoneyType,csarRecordSubject,csarOrder,csarRelate,csarReceived,csarVoucher",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"充值扣款编辑":CTRL.title}</title>
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
<script type="text/javascript">
<!--
$(function(){
	$("#csarCount").change(function(){
		$(this).val($(this).val().replace("-",""));
	});
	$("#csarOrder").change(function(){
		var order=CsOrder.get($(this).val());
		$("#csarMember").setValue(order.csoPayMember,CsMember.getKeyValue(order.csoPayMember));
	});
	$("#csarRelate").change(function(){
		if($(this).val()=="")return;
		var arr=$(this).val().split("@");
		var json=window[arr[0]].get(arr[1]);
		//获取会员相关的字段
		var memberField=null;
		for(var o in json){
			if(o.indexOf("Member")>-1){
				memberField=o;
				break;
			}
		}
		if(memberField)
			$("#csarMember").setValue(json[memberField],CsMember.getKeyValue(json[memberField]));
	});
	$("#csarRecordSubject").change(function(){
		if(!$.empty($(this).val())){
			var csRecordSubject = CsRecordSubject.get($(this).val());
			$("#csarRecordSubject").say((csRecordSubject.csrsIsUserIn?"会员帐户增加项":"")+(csRecordSubject.csrsIsUserOut?"会员帐户扣除项":""),"right");
		}
	});
});
//-->
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
	$("#rechargeForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csarId==true">
		${lz:set("haveEditable",true)}
		,"csArtifRecord.csarId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarHost==true">
		${lz:set("haveEditable",true)}
		,"csArtifRecord.csarHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarTitle==true">
		${lz:set("haveEditable",true)}
		,"csArtifRecord.csarTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "摘要不能为空";
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "摘要最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarMember==true">
		${lz:set("haveEditable",true)}
		,"csArtifRecord.csarMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarCount==true">
		${lz:set("haveEditable",true)}
		,"csArtifRecord.csarCount":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "金额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarMoneyType==true">
		${lz:set("haveEditable",true)}
		,"csArtifRecord.csarMoneyType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择金额类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarRecordSubject==true">
		${lz:set("haveEditable",true)}
		,"csArtifRecord.csarRecordSubject":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarOrder==true">
		${lz:set("haveEditable",true)}
		,"csArtifRecord.csarOrder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarRelate==true">
		${lz:set("haveEditable",true)}
		,"csArtifRecord.csarRelate":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarReceived==true">
		${lz:set("haveEditable",true)}
		,"csArtifRecord.csarReceived":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarVoucher==true">
		${lz:set("haveEditable",true)}
		,"csArtifRecord.csarVoucher":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "现金入帐凭据最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("recharge_query.do",{exists:true,csarVoucher:el.value});
				if(exists && exists.length>0){
					if(${csArtifRecord.csarId==null} || exists[0].value!=$("#csarId").val())
						return "现金入帐凭据已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarAdder==true">
		${lz:set("haveEditable",true)}
		,"csArtifRecord.csarAdder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarEditor==true">
		${lz:set("haveEditable",true)}
		,"csArtifRecord.csarEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarConfirmer==true">
		${lz:set("haveEditable",true)}
		,"csArtifRecord.csarConfirmer":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csArtifRecord.csarUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarAddTime==true">
		${lz:set("haveEditable",true)}
		,"csArtifRecord.csarAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarConfirmTime==true">
		${lz:set("haveEditable",true)}
		,"csArtifRecord.csarConfirmTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarRemark==true">
		${lz:set("haveEditable",true)}
		,"csArtifRecord.csarRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarStatus==true">
		${lz:set("haveEditable",true)}
		,"csArtifRecord.csarStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择状态";
		}
		</s:if>	
	},function(){
		${lz:set("注释","****表单提交自定义判断，阻止提交返回提示字符串即可****")}
		/******************************LAZY3Q_FORM_VALIDATE******************************/
		var recordType = $("#csarRecordSubject").val();
		if(recordType!=""){
			var csRecordSubject = CsRecordSubject.get(recordType);
			if(csRecordSubject.csrsIsOrder && $("#csarOrder").val()==""){
				$("#csarOrder").say("请输入关联订单");
				return "请输入关联订单";
			}
		}
		/******************************LAZY3Q_FORM_VALIDATE******************************/

	});		
});
</script>

<s:if test="false"></s:if>
<s:elseif test="false"></s:elseif>
<s:else>
	<form class="form editform" ref="CsArtifRecord" id="rechargeForm" name="rechargeForm" action="${empty CTRL.action?"recharge_batchcheck.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写充值扣款相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csArtifRecordToken}" name="csArtifRecordToken" id="csArtifRecordToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csArtifRecord.csarId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csarId和after$csarId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarId==true">
	${before$csarId}
	<dl class="csarId ${CTRL.e.csarId?"hidden":""}" major  ref="csarId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csarId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csArtifRecord$csarId!=null">${csArtifRecord$csarId}</s:if><s:else>
			<input type="hidden" value="${csArtifRecord.csarId}" name="csArtifRecord.csarId" id="csarId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarId">${csArtifRecord.csarId}</textarea>
		 		<span>
		 	
			 ${csArtifRecord.csarId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csarHost和after$csarHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarHost==true">
	${before$csarHost}
	<dl class="csarHost " major  ref="csarHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csarHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csArtifRecord$csarHost!=null">${csArtifRecord$csarHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csarHost" name="csArtifRecord.csarHost">
		 				<option selected value="${csArtifRecord.csarHost}">
		 					${get:SrvHost(csArtifRecord.csarHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csarHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csarHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarHost">${csArtifRecord.csarHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csArtifRecord.csarHost}',{ctrl:{editable:false,visible:true}})">
			 ${csArtifRecord.csarHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarHost}
	</s:if>
	
	${lz:set("注释","*****************摘要字段的输入框代码*****************")}
	${lz:set("注释","before$csarTitle和after$csarTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarTitle==true">
	${before$csarTitle}
	<dl class="csarTitle " major  ref="csarTitle" style="width:98%;">
		<dt>摘　　要:</dt>
		<s:if test="#request.CTRL.e.csarTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csArtifRecord$csarTitle!=null">${csArtifRecord$csarTitle}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="256" name="csArtifRecord.csarTitle" id="csarTitle"  value="${csArtifRecord.csarTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入充值扣款的摘要</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****摘要字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csarTitle">${csArtifRecord.csarTitle}</textarea>
		 		<span>
		 	
			 ${csArtifRecord.csarTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarTitle}
	</s:if>
	
	${lz:set("注释","*****************会员字段的输入框代码*****************")}
	${lz:set("注释","before$csarMember和after$csarMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarMember==true">
	${before$csarMember}
	<dl class="csarMember " major  ref="csarMember" >
		<dt>会　　员:</dt>
		<s:if test="#request.CTRL.e.csarMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csArtifRecord$csarMember!=null">${csArtifRecord$csarMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={csarHost}" type="text" id="csarMember" name="csArtifRecord.csarMember" text="${get:CsMember(csArtifRecord.csarMember).csmName}" value="${csArtifRecord.csarMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csarMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csarMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarMember">${csArtifRecord.csarMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csArtifRecord.csarMember}',{ctrl:{editable:false,visible:true}})">
			 ${csArtifRecord.csarMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarMember}
	</s:if>
	
	${lz:set("注释","*****************金额字段的输入框代码*****************")}
	${lz:set("注释","before$csarCount和after$csarCount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarCount==true">
	${before$csarCount}
	<dl class="csarCount " major  ref="csarCount" >
		<dt>金　　额:</dt>
		<s:if test="#request.CTRL.e.csarCount==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csArtifRecord$csarCount!=null">${csArtifRecord$csarCount}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csArtifRecord.csarCount" id="csarCount"  value="${csArtifRecord.csarCount}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入充值扣款的金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarCount">${csArtifRecord.csarCount}</textarea>
		 		<span>
		 	
			 ${csArtifRecord.csarCount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarCount}
	</s:if>
	
	${lz:set("注释","*****************金额类型字段的输入框代码*****************")}
	${lz:set("注释","before$csarMoneyType和after$csarMoneyType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarMoneyType==true">
	${before$csarMoneyType}
	<dl class="csarMoneyType " major  ref="csarMoneyType" >
		<dt>金额类型:</dt>
		<s:if test="#request.CTRL.e.csarMoneyType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csArtifRecord$csarMoneyType!=null">${csArtifRecord$csarMoneyType}</s:if><s:else>
		 	<select class="narrow" id="csarMoneyType" name="csArtifRecord.csarMoneyType">
		 		<option value="">请选择</option>
				<option value="0" ${csArtifRecord.csarMoneyType==0?"selected":""}>保证金</option>
				<option value="1" ${csArtifRecord.csarMoneyType==1?"selected":""}>现金券</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择充值扣款的金额类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****金额类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarMoneyType">${csArtifRecord.csarMoneyType}</textarea>
		 		<span>
		 	
			 ${csArtifRecord.csarMoneyType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarMoneyType}
	</s:if>
	
	${lz:set("注释","*****************类型科目字段的输入框代码*****************")}
	${lz:set("注释","before$csarRecordSubject和after$csarRecordSubject变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarRecordSubject==true">
	${before$csarRecordSubject}
	<dl class="csarRecordSubject " major  ref="csarRecordSubject" >
		<dt>类型科目:</dt>
		<s:if test="#request.CTRL.e.csarRecordSubject==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csArtifRecord$csarRecordSubject!=null">${csArtifRecord$csarRecordSubject}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/configurator/recordsubject_query.do?size=-1&csrsIsArtif=true" id="csarRecordSubject" name="csArtifRecord.csarRecordSubject">
		 				<option selected value="${csArtifRecord.csarRecordSubject}">
		 					${get:CsRecordSubject(csArtifRecord.csarRecordSubject).csrsName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csarRecordSubject').val())==''){return;};window.href('${basePath}${proname}/configurator/recordsubject_details.do?key='+$('#csarRecordSubject').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****类型科目字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarRecordSubject">${csArtifRecord.csarRecordSubject}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/recordsubject_details.do?key=${csArtifRecord.csarRecordSubject}',{ctrl:{editable:false,visible:true}})">
			 ${csArtifRecord.csarRecordSubject$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarRecordSubject}
	</s:if>
	
	${lz:set("注释","*****************关联订单字段的输入框代码*****************")}
	${lz:set("注释","before$csarOrder和after$csarOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarOrder==true">
	${before$csarOrder}
	<dl class="csarOrder " major  ref="csarOrder" >
		<dt>关联订单:</dt>
		<s:if test="#request.CTRL.e.csarOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csArtifRecord$csarOrder!=null">${csArtifRecord$csarOrder}</s:if><s:else>
		 			<input title="请输入系统订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={csarHost}" type="text" id="csarOrder" name="csArtifRecord.csarOrder" text="${get:CsOrder(csArtifRecord.csarOrder).csoId}" value="${csArtifRecord.csarOrder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csarOrder').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?key='+$('#csarOrder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarOrder">${csArtifRecord.csarOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?key=${csArtifRecord.csarOrder}',{ctrl:{editable:false,visible:true}})">
			 ${csArtifRecord.csarOrder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarOrder}
	</s:if>
	
	${lz:set("注释","*****************其它关联字段的输入框代码*****************")}
	${lz:set("注释","before$csarRelate和after$csarRelate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarRelate==true">
	${before$csarRelate}
	<dl class="csarRelate " major  ref="csarRelate" >
		<dt>其它关联:</dt>
		<s:if test="#request.CTRL.e.csarRelate==true">
		${lz:set("haveEditable",true)}
		<dd input="generic">
		<s:if test="#request.csArtifRecord$csarRelate!=null">${csArtifRecord$csarRelate}</s:if><s:else>
		 	<input title="请输入动态查询" class="combox narrow" generic="csarRelateGeneric" size="16" type="text" id="csarRelate" name="csArtifRecord.csarRelate" text="${csArtifRecord.csarRelate$}" value="${csArtifRecord.csarRelate}"/>
		 	${lz:set("models",get:models("CsRefund,CsAlipayRecord,CsCreditBill,CsTrouble,CsViolat"))}
		 	<select id="csarRelateGeneric" style="display:none;">
		 		<option value="">--选择模块--</option>
		 		<s:iterator value="#request.models" id="item" status="i">
		 		<option value="${item.name}:${basePath}admin/${item.namespace}_query.do?value={param}">${item.caption}</option>
		 		</s:iterator>
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="lookup('#${NAME}')"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****其它关联字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarRelate">${csArtifRecord.csarRelate}</textarea>
		 		<span>
		 	
			 ${csArtifRecord.csarRelate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarRelate}
	</s:if>
	
	${lz:set("注释","*****************现金已收讫字段的输入框代码*****************")}
	${lz:set("注释","before$csarReceived和after$csarReceived变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarReceived==true">
	${before$csarReceived}
	<dl class="csarReceived " minor  ref="csarReceived" >
		<dt>现金已收讫:</dt>
		<s:if test="#request.CTRL.e.csarReceived==true">
		${lz:set("haveEditable",true)}
		<dd input="radio">
		<s:if test="#request.csArtifRecord$csarReceived!=null">${csArtifRecord$csarReceived}</s:if><s:else>
		 	<div class='narrow input'>
		 	&nbsp;
	 	    <label><input type="radio" value="true" name="csArtifRecord.csarReceived" id="csarReceived" ${csArtifRecord.csarReceived==true?"checked":""}/>是&nbsp;&nbsp;&nbsp;</label>
			<label><input type="radio" value="false" name="csArtifRecord.csarReceived" id="csarReceived" ${csArtifRecord==null || csArtifRecord.csarReceived==null || csArtifRecord.csarReceived==false?"checked":""}/>否</label>
		 	</div>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择充值扣款的现金已收讫</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****现金已收讫字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarReceived">${csArtifRecord.csarReceived}</textarea>
		 		<span>
		 	
			 ${csArtifRecord.csarReceived$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarReceived}
	</s:if>
	
	${lz:set("注释","*****************现金入帐凭据字段的输入框代码*****************")}
	${lz:set("注释","before$csarVoucher和after$csarVoucher变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarVoucher==true">
	${before$csarVoucher}
	<dl class="csarVoucher " minor  ref="csarVoucher" >
		<dt>现金入帐凭据:</dt>
		<s:if test="#request.CTRL.e.csarVoucher==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csArtifRecord$csarVoucher!=null">${csArtifRecord$csarVoucher}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csArtifRecord.csarVoucher" id="csarVoucher"  value="${csArtifRecord.csarVoucher}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入充值扣款的现金入帐凭据</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****现金入帐凭据字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarVoucher">${csArtifRecord.csarVoucher}</textarea>
		 		<span>
		 	
			 ${csArtifRecord.csarVoucher$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarVoucher}
	</s:if>
	
	${lz:set("注释","*****************添加人字段的输入框代码*****************")}
	${lz:set("注释","before$csarAdder和after$csarAdder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarAdder==true">
	${before$csarAdder}
	<dl class="csarAdder " major  ref="csarAdder" >
		<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csarAdder==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csArtifRecord$csarAdder!=null">${csArtifRecord$csarAdder}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csarAdder" name="csArtifRecord.csarAdder">
		 				<option selected value="${csArtifRecord.csarAdder}">
		 					${get:SrvUser(csArtifRecord.csarAdder).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csarAdder').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csarAdder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarAdder">${csArtifRecord.csarAdder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csArtifRecord.csarAdder}',{ctrl:{editable:false,visible:true}})">
			 ${csArtifRecord.csarAdder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarAdder}
	</s:if>
	
	${lz:set("注释","*****************修改人字段的输入框代码*****************")}
	${lz:set("注释","before$csarEditor和after$csarEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarEditor==true">
	${before$csarEditor}
	<dl class="csarEditor " major  ref="csarEditor" >
		<dt>修&nbsp;&nbsp;改&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csarEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csArtifRecord$csarEditor!=null">${csArtifRecord$csarEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csarEditor" name="csArtifRecord.csarEditor">
		 				<option selected value="${csArtifRecord.csarEditor}">
		 					${get:SrvUser(csArtifRecord.csarEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csarEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csarEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarEditor">${csArtifRecord.csarEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csArtifRecord.csarEditor}',{ctrl:{editable:false,visible:true}})">
			 ${csArtifRecord.csarEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarEditor}
	</s:if>
	
	${lz:set("注释","*****************财务审核人字段的输入框代码*****************")}
	${lz:set("注释","before$csarConfirmer和after$csarConfirmer变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarConfirmer==true">
	${before$csarConfirmer}
	<dl class="csarConfirmer " minor  ref="csarConfirmer" >
		<dt>财务审核人:</dt>
		<s:if test="#request.CTRL.e.csarConfirmer==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csArtifRecord$csarConfirmer!=null">${csArtifRecord$csarConfirmer}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csArtifRecord.csarConfirmer" id="csarConfirmer"  value="${csArtifRecord.csarConfirmer}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入充值扣款的财务审核人</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****财务审核人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarConfirmer">${csArtifRecord.csarConfirmer}</textarea>
		 		<span>
		 	
			 ${csArtifRecord.csarConfirmer$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarConfirmer}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csarUpdateTime和after$csarUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarUpdateTime==true">
	${before$csarUpdateTime}
	<dl class="csarUpdateTime " major  ref="csarUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csarUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csArtifRecord$csarUpdateTime!=null">${csArtifRecord$csarUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csArtifRecord.csarUpdateTime" id="csarUpdateTime"  value="<s:date name="csArtifRecord.csarUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择充值扣款的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarUpdateTime">${csArtifRecord.csarUpdateTime}</textarea>
		 		<span>
		 	
			 ${csArtifRecord.csarUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csarAddTime和after$csarAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarAddTime==true">
	${before$csarAddTime}
	<dl class="csarAddTime " major  ref="csarAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csarAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csArtifRecord$csarAddTime!=null">${csArtifRecord$csarAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csArtifRecord.csarAddTime" id="csarAddTime"  value="<s:date name="csArtifRecord.csarAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择充值扣款的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarAddTime">${csArtifRecord.csarAddTime}</textarea>
		 		<span>
		 	
			 ${csArtifRecord.csarAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarAddTime}
	</s:if>
	
	${lz:set("注释","*****************审核时间字段的输入框代码*****************")}
	${lz:set("注释","before$csarConfirmTime和after$csarConfirmTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarConfirmTime==true">
	${before$csarConfirmTime}
	<dl class="csarConfirmTime " minor  ref="csarConfirmTime" >
		<dt>审核时间:</dt>
		<s:if test="#request.CTRL.e.csarConfirmTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csArtifRecord$csarConfirmTime!=null">${csArtifRecord$csarConfirmTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csArtifRecord.csarConfirmTime" id="csarConfirmTime"  value="<s:date name="csArtifRecord.csarConfirmTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择充值扣款的审核时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****审核时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarConfirmTime">${csArtifRecord.csarConfirmTime}</textarea>
		 		<span>
		 	
			 ${csArtifRecord.csarConfirmTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarConfirmTime}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$csarRemark和after$csarRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarRemark==true">
	${before$csarRemark}
	<dl class="csarRemark " minor  ref="csarRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.csarRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csArtifRecord$csarRemark!=null">${csArtifRecord$csarRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csarRemark" name="csArtifRecord.csarRemark" rows="5">${csArtifRecord.csarRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入充值扣款的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csArtifRecord.csarRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarRemark}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csarStatus和after$csarStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarStatus==true">
	${before$csarStatus}
	<dl class="csarStatus " major  ref="csarStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csarStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csArtifRecord$csarStatus!=null">${csArtifRecord$csarStatus}</s:if><s:else>
		 	<select class="narrow" id="csarStatus" name="csArtifRecord.csarStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csArtifRecord.csarStatus==1?"selected":""}>已审核</option>
				<option value="2" ${csArtifRecord.csarStatus==2?"selected":""}>审核未通过</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择充值扣款的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarStatus">${csArtifRecord.csarStatus}</textarea>
		 		<span>
		 	
			 ${csArtifRecord.csarStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csArtifRecord.csarId}" name="csArtifRecord.csarId" id="csarId" />
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