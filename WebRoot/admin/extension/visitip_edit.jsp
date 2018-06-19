<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/extension/visitip.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/extension/visitip.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csVisitIp.csviId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csviId,csviHost,csviAddress,csviChannel,csviPlan,csviActivity,csviUrlFrom,csviUrlTo",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csviId,csviHost,csviAddress,csviChannel,csviPlan,csviActivity,csviUrlFrom,csviUrlTo",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csviId,csviHost,csviAddress,csviChannel,csviPlan,csviActivity,csviUrlFrom,csviUrlTo",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csviId,csviHost,csviAddress,csviChannel,csviPlan,csviActivity,csviUrlFrom,csviUrlTo",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"独立IP访问编辑":CTRL.title}</title>
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
	$("#visitipForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csviId==true">
		${lz:set("haveEditable",true)}
		,"csVisitIp.csviId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csviHost==true">
		${lz:set("haveEditable",true)}
		,"csVisitIp.csviHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csviAddress==true">
		${lz:set("haveEditable",true)}
		,"csVisitIp.csviAddress":function(el){
			if(jQuery.trim(el.value)=="")
				return "IP地址不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "IP地址最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csviChannel==true">
		${lz:set("haveEditable",true)}
		,"csVisitIp.csviChannel":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csviPlan==true">
		${lz:set("haveEditable",true)}
		,"csVisitIp.csviPlan":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csviActivity==true">
		${lz:set("haveEditable",true)}
		,"csVisitIp.csviActivity":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csviUrlFrom==true">
		${lz:set("haveEditable",true)}
		,"csVisitIp.csviUrlFrom":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "源地址最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csviUrlTo==true">
		${lz:set("haveEditable",true)}
		,"csVisitIp.csviUrlTo":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "目标地址最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csviAddTime==true">
		${lz:set("haveEditable",true)}
		,"csVisitIp.csviAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "访问时间不能为空";
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
	<form class="form editform" ref="CsVisitIp" id="visitipForm" name="visitipForm" action="${empty CTRL.action?"visitip_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写独立IP访问相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csVisitIpToken}" name="csVisitIpToken" id="csVisitIpToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csVisitIp.csviId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csviId和after$csviId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csviId==true">
	${before$csviId}
	<dl class="csviId ${CTRL.e.csviId?"hidden":""}" major  ref="csviId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csviId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csVisitIp$csviId!=null">${csVisitIp$csviId}</s:if><s:else>
			<input type="hidden" value="${csVisitIp.csviId}" name="csVisitIp.csviId" id="csviId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csviId">${csVisitIp.csviId}</textarea>
		 		<span>
		 	
			 ${csVisitIp.csviId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csviId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csviHost和after$csviHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csviHost==true">
	${before$csviHost}
	<dl class="csviHost " major  ref="csviHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csviHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csVisitIp$csviHost!=null">${csVisitIp$csviHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csviHost" name="csVisitIp.csviHost">
		 				<option selected value="${csVisitIp.csviHost}">
		 					${get:SrvHost(csVisitIp.csviHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csviHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csviHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csviHost">${csVisitIp.csviHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csVisitIp.csviHost}',{ctrl:{editable:false,visible:true}})">
			 ${csVisitIp.csviHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csviHost}
	</s:if>
	
	${lz:set("注释","*****************IP地址字段的输入框代码*****************")}
	${lz:set("注释","before$csviAddress和after$csviAddress变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csviAddress==true">
	${before$csviAddress}
	<dl class="csviAddress " major  ref="csviAddress" >
		<dt>IP地址:</dt>
		<s:if test="#request.CTRL.e.csviAddress==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csVisitIp$csviAddress!=null">${csVisitIp$csviAddress}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csVisitIp.csviAddress" id="csviAddress"  value="${csVisitIp.csviAddress}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入独立IP访问的IP地址</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****IP地址字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csviAddress">${csVisitIp.csviAddress}</textarea>
		 		<span>
		 	
			 ${csVisitIp.csviAddress$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csviAddress}
	</s:if>
	
	${lz:set("注释","*****************所属渠道字段的输入框代码*****************")}
	${lz:set("注释","before$csviChannel和after$csviChannel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csviChannel==true">
	${before$csviChannel}
	<dl class="csviChannel " major  ref="csviChannel" >
		<dt>所属渠道:</dt>
		<s:if test="#request.CTRL.e.csviChannel==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csVisitIp$csviChannel!=null">${csVisitIp$csviChannel}</s:if><s:else>
		 			<input title="请输入访问渠道渠道名称进行查询" class="combox narrow" action="${basePath}${proname}/extension/channel_query.do?value={param}&cscHost={csviHost}" type="text" id="csviChannel" name="csVisitIp.csviChannel" text="${get:CsChannel(csVisitIp.csviChannel).cscName}" value="${csVisitIp.csviChannel}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csviChannel').val())==''){return;};window.href('${basePath}${proname}/extension/channel_details.do?key='+$('#csviChannel').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属渠道字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csviChannel">${csVisitIp.csviChannel}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/extension/channel_details.do?key=${csVisitIp.csviChannel}',{ctrl:{editable:false,visible:true}})">
			 ${csVisitIp.csviChannel$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csviChannel}
	</s:if>
	
	${lz:set("注释","*****************所属策划字段的输入框代码*****************")}
	${lz:set("注释","before$csviPlan和after$csviPlan变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csviPlan==true">
	${before$csviPlan}
	<dl class="csviPlan " major  ref="csviPlan" >
		<dt>所属策划:</dt>
		<s:if test="#request.CTRL.e.csviPlan==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csVisitIp$csviPlan!=null">${csVisitIp$csviPlan}</s:if><s:else>
		 			<input title="请输入营销方案方案主题进行查询" class="combox narrow" action="${basePath}${proname}/operate/marketplan_query.do?value={param}&csmpHost={csviHost}" type="text" id="csviPlan" name="csVisitIp.csviPlan" text="${get:CsMarketPlan(csVisitIp.csviPlan).csmpName}" value="${csVisitIp.csviPlan}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csviPlan').val())==''){return;};window.href('${basePath}${proname}/operate/marketplan_details.do?key='+$('#csviPlan').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属策划字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csviPlan">${csVisitIp.csviPlan}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/operate/marketplan_details.do?key=${csVisitIp.csviPlan}',{ctrl:{editable:false,visible:true}})">
			 ${csVisitIp.csviPlan$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csviPlan}
	</s:if>
	
	${lz:set("注释","*****************所属活动字段的输入框代码*****************")}
	${lz:set("注释","before$csviActivity和after$csviActivity变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csviActivity==true">
	${before$csviActivity}
	<dl class="csviActivity " major  ref="csviActivity" >
		<dt>所属活动:</dt>
		<s:if test="#request.CTRL.e.csviActivity==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csVisitIp$csviActivity!=null">${csVisitIp$csviActivity}</s:if><s:else>
		 			<input title="请输入营销活动活动主题进行查询" class="combox narrow" action="${basePath}${proname}/operate/activitys/activity_query.do?value={param}&csaHost={csviHost}" type="text" id="csviActivity" name="csVisitIp.csviActivity" text="${get:CsActivity(csVisitIp.csviActivity).csaTitle}" value="${csVisitIp.csviActivity}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csviActivity').val())==''){return;};window.href('${basePath}${proname}/operate/activitys/activity_details.do?key='+$('#csviActivity').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属活动字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csviActivity">${csVisitIp.csviActivity}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/operate/activitys/activity_details.do?key=${csVisitIp.csviActivity}',{ctrl:{editable:false,visible:true}})">
			 ${csVisitIp.csviActivity$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csviActivity}
	</s:if>
	
	${lz:set("注释","*****************源地址字段的输入框代码*****************")}
	${lz:set("注释","before$csviUrlFrom和after$csviUrlFrom变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csviUrlFrom==true">
	${before$csviUrlFrom}
	<dl class="csviUrlFrom " major  ref="csviUrlFrom" style="width:98%;">
		<dt>源&nbsp;&nbsp;地&nbsp;&nbsp;址:</dt>
		<s:if test="#request.CTRL.e.csviUrlFrom==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csVisitIp$csviUrlFrom!=null">${csVisitIp$csviUrlFrom}</s:if><s:else>
		 	<textarea class="input wide"  id="csviUrlFrom" name="csVisitIp.csviUrlFrom" rows="5">${csVisitIp.csviUrlFrom}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入独立IP访问的源地址</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****源地址字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csVisitIp.csviUrlFrom$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csviUrlFrom}
	</s:if>
	
	${lz:set("注释","*****************目标地址字段的输入框代码*****************")}
	${lz:set("注释","before$csviUrlTo和after$csviUrlTo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csviUrlTo==true">
	${before$csviUrlTo}
	<dl class="csviUrlTo " major  ref="csviUrlTo" style="width:98%;">
		<dt>目标地址:</dt>
		<s:if test="#request.CTRL.e.csviUrlTo==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csVisitIp$csviUrlTo!=null">${csVisitIp$csviUrlTo}</s:if><s:else>
		 	<textarea class="input wide"  id="csviUrlTo" name="csVisitIp.csviUrlTo" rows="5">${csVisitIp.csviUrlTo}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入独立IP访问的目标地址</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****目标地址字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csVisitIp.csviUrlTo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csviUrlTo}
	</s:if>
	
	${lz:set("注释","*****************访问时间字段的输入框代码*****************")}
	${lz:set("注释","before$csviAddTime和after$csviAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csviAddTime==true">
	${before$csviAddTime}
	<dl class="csviAddTime " major  ref="csviAddTime" >
		<dt>访问时间:</dt>
		<s:if test="#request.CTRL.e.csviAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csVisitIp$csviAddTime!=null">${csVisitIp$csviAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csVisitIp.csviAddTime" id="csviAddTime"  value="<s:date name="csVisitIp.csviAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择独立IP访问的访问时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****访问时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csviAddTime">${csVisitIp.csviAddTime}</textarea>
		 		<span>
		 	
			 ${csVisitIp.csviAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csviAddTime}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csVisitIp.csviId}" name="csVisitIp.csviId" id="csviId" />
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