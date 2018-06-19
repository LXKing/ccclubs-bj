<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/extension/detail.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/extension/detail.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csVisitDetail.csvdId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csvdId,csvdHost,csvdPlatform,csvdChannel,csvdPlan,csvdActivity,csvdPv,csvdUv,csvdIp,csvdStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csvdId,csvdHost,csvdPlatform,csvdChannel,csvdPlan,csvdActivity,csvdPv,csvdUv,csvdIp,csvdStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csvdId,csvdHost,csvdPlatform,csvdChannel,csvdPlan,csvdActivity,csvdPv,csvdUv,csvdIp,csvdStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csvdId,csvdHost,csvdPlatform,csvdChannel,csvdPlan,csvdActivity,csvdPv,csvdUv,csvdIp,csvdStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"访问详情编辑":CTRL.title}</title>
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
	$("#detailForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csvdId==true">
		${lz:set("haveEditable",true)}
		,"csVisitDetail.csvdId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvdHost==true">
		${lz:set("haveEditable",true)}
		,"csVisitDetail.csvdHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvdPlatform==true">
		${lz:set("haveEditable",true)}
		,"csVisitDetail.csvdPlatform":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "所属平台最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvdChannel==true">
		${lz:set("haveEditable",true)}
		,"csVisitDetail.csvdChannel":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvdPlan==true">
		${lz:set("haveEditable",true)}
		,"csVisitDetail.csvdPlan":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvdActivity==true">
		${lz:set("haveEditable",true)}
		,"csVisitDetail.csvdActivity":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvdPv==true">
		${lz:set("haveEditable",true)}
		,"csVisitDetail.csvdPv":function(el){
			if(jQuery.trim(el.value)=="")
				return "PV不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvdUv==true">
		${lz:set("haveEditable",true)}
		,"csVisitDetail.csvdUv":function(el){
			if(jQuery.trim(el.value)=="")
				return "UV不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvdIp==true">
		${lz:set("haveEditable",true)}
		,"csVisitDetail.csvdIp":function(el){
			if(jQuery.trim(el.value)=="")
				return "IP不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvdAddTime==true">
		${lz:set("haveEditable",true)}
		,"csVisitDetail.csvdAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "访问时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csvdStatus==true">
		${lz:set("haveEditable",true)}
		,"csVisitDetail.csvdStatus":function(el){
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
<s:else>
	<form class="form editform" ref="CsVisitDetail" id="detailForm" name="detailForm" action="${empty CTRL.action?"detail_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写访问详情相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csVisitDetailToken}" name="csVisitDetailToken" id="csVisitDetailToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csVisitDetail.csvdId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csvdId和after$csvdId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvdId==true">
	${before$csvdId}
	<dl class="csvdId ${CTRL.e.csvdId?"hidden":""}" major  ref="csvdId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csvdId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csVisitDetail$csvdId!=null">${csVisitDetail$csvdId}</s:if><s:else>
			<input type="hidden" value="${csVisitDetail.csvdId}" name="csVisitDetail.csvdId" id="csvdId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvdId">${csVisitDetail.csvdId}</textarea>
		 		<span>
		 	
			 ${csVisitDetail.csvdId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvdId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csvdHost和after$csvdHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvdHost==true">
	${before$csvdHost}
	<dl class="csvdHost " major  ref="csvdHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csvdHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csVisitDetail$csvdHost!=null">${csVisitDetail$csvdHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csvdHost" name="csVisitDetail.csvdHost">
		 				<option selected value="${csVisitDetail.csvdHost}">
		 					${get:SrvHost(csVisitDetail.csvdHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csvdHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csvdHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvdHost">${csVisitDetail.csvdHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csVisitDetail.csvdHost}',{ctrl:{editable:false,visible:true}})">
			 ${csVisitDetail.csvdHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvdHost}
	</s:if>
	
	${lz:set("注释","*****************所属平台字段的输入框代码*****************")}
	${lz:set("注释","before$csvdPlatform和after$csvdPlatform变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvdPlatform==true">
	${before$csvdPlatform}
	<dl class="csvdPlatform " major  ref="csvdPlatform" >
		<dt>所属平台:</dt>
		<s:if test="#request.CTRL.e.csvdPlatform==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csVisitDetail$csvdPlatform!=null">${csVisitDetail$csvdPlatform}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csVisitDetail.csvdPlatform" id="csvdPlatform"  value="${csVisitDetail.csvdPlatform}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入访问详情的所属平台</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属平台字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvdPlatform">${csVisitDetail.csvdPlatform}</textarea>
		 		<span>
		 	
			 ${csVisitDetail.csvdPlatform$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvdPlatform}
	</s:if>
	
	${lz:set("注释","*****************所属渠道字段的输入框代码*****************")}
	${lz:set("注释","before$csvdChannel和after$csvdChannel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvdChannel==true">
	${before$csvdChannel}
	<dl class="csvdChannel " major  ref="csvdChannel" >
		<dt>所属渠道:</dt>
		<s:if test="#request.CTRL.e.csvdChannel==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csVisitDetail$csvdChannel!=null">${csVisitDetail$csvdChannel}</s:if><s:else>
		 			<input title="请输入访问渠道渠道名称进行查询" class="combox narrow" action="${basePath}${proname}/extension/channel_query.do?value={param}&cscHost={csvdHost}" type="text" id="csvdChannel" name="csVisitDetail.csvdChannel" text="${get:CsChannel(csVisitDetail.csvdChannel).cscName}" value="${csVisitDetail.csvdChannel}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csvdChannel').val())==''){return;};window.href('${basePath}${proname}/extension/channel_details.do?key='+$('#csvdChannel').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属渠道字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvdChannel">${csVisitDetail.csvdChannel}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/extension/channel_details.do?key=${csVisitDetail.csvdChannel}',{ctrl:{editable:false,visible:true}})">
			 ${csVisitDetail.csvdChannel$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvdChannel}
	</s:if>
	
	${lz:set("注释","*****************所属策划字段的输入框代码*****************")}
	${lz:set("注释","before$csvdPlan和after$csvdPlan变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvdPlan==true">
	${before$csvdPlan}
	<dl class="csvdPlan " major  ref="csvdPlan" >
		<dt>所属策划:</dt>
		<s:if test="#request.CTRL.e.csvdPlan==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csVisitDetail$csvdPlan!=null">${csVisitDetail$csvdPlan}</s:if><s:else>
		 			<input title="请输入营销方案方案主题进行查询" class="combox narrow" action="${basePath}${proname}/operate/marketplan_query.do?value={param}&csmpHost={csvdHost}" type="text" id="csvdPlan" name="csVisitDetail.csvdPlan" text="${get:CsMarketPlan(csVisitDetail.csvdPlan).csmpName}" value="${csVisitDetail.csvdPlan}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csvdPlan').val())==''){return;};window.href('${basePath}${proname}/operate/marketplan_details.do?key='+$('#csvdPlan').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属策划字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvdPlan">${csVisitDetail.csvdPlan}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/operate/marketplan_details.do?key=${csVisitDetail.csvdPlan}',{ctrl:{editable:false,visible:true}})">
			 ${csVisitDetail.csvdPlan$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvdPlan}
	</s:if>
	
	${lz:set("注释","*****************所属活动字段的输入框代码*****************")}
	${lz:set("注释","before$csvdActivity和after$csvdActivity变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvdActivity==true">
	${before$csvdActivity}
	<dl class="csvdActivity " major  ref="csvdActivity" >
		<dt>所属活动:</dt>
		<s:if test="#request.CTRL.e.csvdActivity==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csVisitDetail$csvdActivity!=null">${csVisitDetail$csvdActivity}</s:if><s:else>
		 			<input title="请输入营销活动活动主题进行查询" class="combox narrow" action="${basePath}${proname}/operate/activitys/activity_query.do?value={param}&csaHost={csvdHost}" type="text" id="csvdActivity" name="csVisitDetail.csvdActivity" text="${get:CsActivity(csVisitDetail.csvdActivity).csaTitle}" value="${csVisitDetail.csvdActivity}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csvdActivity').val())==''){return;};window.href('${basePath}${proname}/operate/activitys/activity_details.do?key='+$('#csvdActivity').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属活动字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvdActivity">${csVisitDetail.csvdActivity}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/operate/activitys/activity_details.do?key=${csVisitDetail.csvdActivity}',{ctrl:{editable:false,visible:true}})">
			 ${csVisitDetail.csvdActivity$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvdActivity}
	</s:if>
	
	${lz:set("注释","*****************PV字段的输入框代码*****************")}
	${lz:set("注释","before$csvdPv和after$csvdPv变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvdPv==true">
	${before$csvdPv}
	<dl class="csvdPv " major  ref="csvdPv" >
		<dt>P　　V:</dt>
		<s:if test="#request.CTRL.e.csvdPv==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csVisitDetail$csvdPv!=null">${csVisitDetail$csvdPv}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csVisitDetail.csvdPv" id="csvdPv"  value="${csVisitDetail.csvdPv}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入访问详情的PV</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****PV字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvdPv">${csVisitDetail.csvdPv}</textarea>
		 		<span>
		 	
			 ${csVisitDetail.csvdPv$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvdPv}
	</s:if>
	
	${lz:set("注释","*****************UV字段的输入框代码*****************")}
	${lz:set("注释","before$csvdUv和after$csvdUv变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvdUv==true">
	${before$csvdUv}
	<dl class="csvdUv " major  ref="csvdUv" >
		<dt>U　　V:</dt>
		<s:if test="#request.CTRL.e.csvdUv==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csVisitDetail$csvdUv!=null">${csVisitDetail$csvdUv}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csVisitDetail.csvdUv" id="csvdUv"  value="${csVisitDetail.csvdUv}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入访问详情的UV</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****UV字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvdUv">${csVisitDetail.csvdUv}</textarea>
		 		<span>
		 	
			 ${csVisitDetail.csvdUv$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvdUv}
	</s:if>
	
	${lz:set("注释","*****************IP字段的输入框代码*****************")}
	${lz:set("注释","before$csvdIp和after$csvdIp变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvdIp==true">
	${before$csvdIp}
	<dl class="csvdIp " major  ref="csvdIp" >
		<dt>I　　P:</dt>
		<s:if test="#request.CTRL.e.csvdIp==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csVisitDetail$csvdIp!=null">${csVisitDetail$csvdIp}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csVisitDetail.csvdIp" id="csvdIp"  value="${csVisitDetail.csvdIp}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入访问详情的IP</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****IP字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvdIp">${csVisitDetail.csvdIp}</textarea>
		 		<span>
		 	
			 ${csVisitDetail.csvdIp$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvdIp}
	</s:if>
	
	${lz:set("注释","*****************访问时间字段的输入框代码*****************")}
	${lz:set("注释","before$csvdAddTime和after$csvdAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvdAddTime==true">
	${before$csvdAddTime}
	<dl class="csvdAddTime " major  ref="csvdAddTime" >
		<dt>访问时间:</dt>
		<s:if test="#request.CTRL.e.csvdAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csVisitDetail$csvdAddTime!=null">${csVisitDetail$csvdAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csVisitDetail.csvdAddTime" id="csvdAddTime"  value="<s:date name="csVisitDetail.csvdAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择访问详情的访问时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****访问时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvdAddTime">${csVisitDetail.csvdAddTime}</textarea>
		 		<span>
		 	
			 ${csVisitDetail.csvdAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvdAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csvdStatus和after$csvdStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csvdStatus==true">
	${before$csvdStatus}
	<dl class="csvdStatus " major  ref="csvdStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csvdStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csVisitDetail$csvdStatus!=null">${csVisitDetail$csvdStatus}</s:if><s:else>
		 	<select class="narrow" id="csvdStatus" name="csVisitDetail.csvdStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csVisitDetail.csvdStatus==1?"selected":""}>正常</option>
				<option value="0" ${csVisitDetail.csvdStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择访问详情的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csvdStatus">${csVisitDetail.csvdStatus}</textarea>
		 		<span>
		 	
			 ${csVisitDetail.csvdStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csvdStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csVisitDetail.csvdId}" name="csVisitDetail.csvdId" id="csvdId" />
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