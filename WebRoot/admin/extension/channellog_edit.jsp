<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/extension/channellog.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/extension/channellog.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csChannelLog.csclId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csclId,csclHost,csclTitle,csclPlan,csclProfile,csclStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csclId,csclHost,csclTitle,csclPlan,csclProfile,csclStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csclId,csclHost,csclTitle,csclPlan,csclProfile,csclStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csclId,csclHost,csclTitle,csclPlan,csclProfile,csclStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"渠道跟踪维护编辑":CTRL.title}</title>
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
		if($("#csclProfile").size()>0){
			 //构造编辑页csChannelLog.csclProfile为html编辑控件
			 KE.init({
				id : 'csclProfile',
				width : '625px',
				height : '300px' , 
				imageUploadJson : '${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}?app=channellog_edit&type=img${ lz:config("upload.url")==null ? "" : "&remote="}${ lz:config("upload.url")==null ? "" : basePath}${ lz:config("upload.url")==null ? "" : "callback.jsp"}'
			 });
			 KE.create("csclProfile");
		 }
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#channellogForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csclId==true">
		${lz:set("haveEditable",true)}
		,"csChannelLog.csclId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csclHost==true">
		${lz:set("haveEditable",true)}
		,"csChannelLog.csclHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csclTitle==true">
		${lz:set("haveEditable",true)}
		,"csChannelLog.csclTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "标题不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "标题最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csclPlan==true">
		${lz:set("haveEditable",true)}
		,"csChannelLog.csclPlan":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csclProfile==true">
		${lz:set("haveEditable",true)}
		,"csChannelLog.csclProfile":function(el){
			if($("#csclProfile").get(0))
			 	KE.sync("csclProfile");		 
			if(jQuery.trim(el.value)=="")
				return "描述不能为空";
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "描述最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csclUser==true">
		${lz:set("haveEditable",true)}
		,"csChannelLog.csclUser":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csclUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csChannelLog.csclUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csclAddTime==true">
		${lz:set("haveEditable",true)}
		,"csChannelLog.csclAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csclStatus==true">
		${lz:set("haveEditable",true)}
		,"csChannelLog.csclStatus":function(el){
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
	<form class="form editform" ref="CsChannelLog" id="channellogForm" name="channellogForm" action="${empty CTRL.action?"channellog_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写渠道跟踪维护相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csChannelLogToken}" name="csChannelLogToken" id="csChannelLogToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csChannelLog.csclId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csclId和after$csclId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csclId==true">
	${before$csclId}
	<dl class="csclId ${CTRL.e.csclId?"hidden":""}" major  ref="csclId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csclId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csChannelLog$csclId!=null">${csChannelLog$csclId}</s:if><s:else>
			<input type="hidden" value="${csChannelLog.csclId}" name="csChannelLog.csclId" id="csclId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csclId">${csChannelLog.csclId}</textarea>
		 		<span>
		 	
			 ${csChannelLog.csclId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csclId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csclHost和after$csclHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csclHost==true">
	${before$csclHost}
	<dl class="csclHost " major  ref="csclHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csclHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csChannelLog$csclHost!=null">${csChannelLog$csclHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csclHost" name="csChannelLog.csclHost">
		 				<option selected value="${csChannelLog.csclHost}">
		 					${get:SrvHost(csChannelLog.csclHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csclHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csclHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csclHost">${csChannelLog.csclHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csChannelLog.csclHost}',{ctrl:{editable:false,visible:true}})">
			 ${csChannelLog.csclHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csclHost}
	</s:if>
	
	${lz:set("注释","*****************标题字段的输入框代码*****************")}
	${lz:set("注释","before$csclTitle和after$csclTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csclTitle==true">
	${before$csclTitle}
	<dl class="csclTitle " major  ref="csclTitle" >
		<dt>标　　题:</dt>
		<s:if test="#request.CTRL.e.csclTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csChannelLog$csclTitle!=null">${csChannelLog$csclTitle}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csChannelLog.csclTitle" id="csclTitle"  value="${csChannelLog.csclTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入渠道跟踪维护的标题</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****标题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csclTitle">${csChannelLog.csclTitle}</textarea>
		 		<span>
		 	
			 ${csChannelLog.csclTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csclTitle}
	</s:if>
	
	${lz:set("注释","*****************所属渠道字段的输入框代码*****************")}
	${lz:set("注释","before$csclPlan和after$csclPlan变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csclPlan==true">
	${before$csclPlan}
	<dl class="csclPlan " major  ref="csclPlan" >
		<dt>所属渠道:</dt>
		<s:if test="#request.CTRL.e.csclPlan==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csChannelLog$csclPlan!=null">${csChannelLog$csclPlan}</s:if><s:else>
		 			<input title="请输入访问渠道渠道名称进行查询" class="combox narrow" action="${basePath}${proname}/extension/channel_query.do?value={param}&cscHost={csclHost}" type="text" id="csclPlan" name="csChannelLog.csclPlan" text="${get:CsChannel(csChannelLog.csclPlan).cscName}" value="${csChannelLog.csclPlan}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csclPlan').val())==''){return;};window.href('${basePath}${proname}/extension/channel_details.do?key='+$('#csclPlan').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属渠道字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csclPlan">${csChannelLog.csclPlan}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/extension/channel_details.do?key=${csChannelLog.csclPlan}',{ctrl:{editable:false,visible:true}})">
			 ${csChannelLog.csclPlan$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csclPlan}
	</s:if>
	
	${lz:set("注释","*****************描述字段的输入框代码*****************")}
	${lz:set("注释","before$csclProfile和after$csclProfile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csclProfile==true">
	${before$csclProfile}
	<dl class="csclProfile " major  ref="csclProfile" style="width:98%;">
		<dt>描　　述:</dt>
		<s:if test="#request.CTRL.e.csclProfile==true">
		${lz:set("haveEditable",true)}
		<dd input="html">
		<s:if test="#request.csChannelLog$csclProfile!=null">${csChannelLog$csclProfile}</s:if><s:else>
		    <textarea class="input wide"  id="csclProfile" name="csChannelLog.csclProfile" style="height:300px;visibility:hidden;">${csChannelLog.csclProfile}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入渠道跟踪维护的描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csChannelLog.csclProfile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csclProfile}
	</s:if>
	
	${lz:set("注释","*****************执行人字段的输入框代码*****************")}
	${lz:set("注释","before$csclUser和after$csclUser变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csclUser==true">
	${before$csclUser}
	<dl class="csclUser " major  ref="csclUser" >
		<dt>执&nbsp;&nbsp;行&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csclUser==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csChannelLog$csclUser!=null">${csChannelLog$csclUser}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csclUser" name="csChannelLog.csclUser">
		 				<option selected value="${csChannelLog.csclUser}">
		 					${get:SrvUser(csChannelLog.csclUser).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csclUser').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csclUser').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****执行人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csclUser">${csChannelLog.csclUser}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csChannelLog.csclUser}',{ctrl:{editable:false,visible:true}})">
			 ${csChannelLog.csclUser$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csclUser}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csclUpdateTime和after$csclUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csclUpdateTime==true">
	${before$csclUpdateTime}
	<dl class="csclUpdateTime " major  ref="csclUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csclUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csChannelLog$csclUpdateTime!=null">${csChannelLog$csclUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csChannelLog.csclUpdateTime" id="csclUpdateTime"  value="<s:date name="csChannelLog.csclUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择渠道跟踪维护的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csclUpdateTime">${csChannelLog.csclUpdateTime}</textarea>
		 		<span>
		 	
			 ${csChannelLog.csclUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csclUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csclAddTime和after$csclAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csclAddTime==true">
	${before$csclAddTime}
	<dl class="csclAddTime " major  ref="csclAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csclAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csChannelLog$csclAddTime!=null">${csChannelLog$csclAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csChannelLog.csclAddTime" id="csclAddTime"  value="<s:date name="csChannelLog.csclAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择渠道跟踪维护的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csclAddTime">${csChannelLog.csclAddTime}</textarea>
		 		<span>
		 	
			 ${csChannelLog.csclAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csclAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csclStatus和after$csclStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csclStatus==true">
	${before$csclStatus}
	<dl class="csclStatus " major  ref="csclStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csclStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csChannelLog$csclStatus!=null">${csChannelLog$csclStatus}</s:if><s:else>
		 	<select class="narrow" id="csclStatus" name="csChannelLog.csclStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csChannelLog.csclStatus==0?"selected":""}>默认</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择渠道跟踪维护的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csclStatus">${csChannelLog.csclStatus}</textarea>
		 		<span>
		 	
			 ${csChannelLog.csclStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csclStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csChannelLog.csclId}" name="csChannelLog.csclId" id="csclId" />
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