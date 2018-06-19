<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/oa/model.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/oa/model.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty srvModel.smId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"smId,smName,smAction,smNamespace,smDisplay,smFunctions,smFields",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"smId,smName,smAction,smNamespace,smDisplay,smFunctions,smFields",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"smId,smName,smFunctions,smFields",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"smId,smName,smAction,smNamespace,smDisplay,smFunctions,smFields",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"表单设计编辑":CTRL.title}</title>
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
	<%@ taglib prefix="wf" uri="/workflow-tags" %>
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
<%@include file="model_editor.jsp"%>
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
	$("#modelForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.smId==true">
		${lz:set("haveEditable",true)}
		,"srvModel.smId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.smName==true">
		${lz:set("haveEditable",true)}
		,"srvModel.smName":function(el){
			if(jQuery.trim(el.value)=="")
				return "对象名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "对象名称最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("model_query.do",{exists:true,smName:el.value});
				if(exists && exists.length>0){
					if(${srvModel.smId==null} || exists[0].value!=$("#smId").val())
						return "对象名称已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.smAction==true">
		${lz:set("haveEditable",true)}
		,"srvModel.smAction":function(el){
			if(jQuery.trim(el.value)=="")
				return "英文名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "英文名称最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("model_query.do",{exists:true,smAction:el.value});
				if(exists && exists.length>0){
					if(${srvModel.smId==null} || exists[0].value!=$("#smId").val())
						return "英文名称已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.smNamespace==true">
		${lz:set("haveEditable",true)}
		,"srvModel.smNamespace":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "相对路径最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.smDisplay==true">
		${lz:set("haveEditable",true)}
		,"srvModel.smDisplay":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择结构类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.smFunctions==true">
		${lz:set("haveEditable",true)}
		,"srvModel.smFunctions":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.smFields==true">
		${lz:set("haveEditable",true)}
		,"srvModel.smFields":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "字段设计最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.smEditor==true">
		${lz:set("haveEditable",true)}
		,"srvModel.smEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.smUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"srvModel.smUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.smAddTime==true">
		${lz:set("haveEditable",true)}
		,"srvModel.smAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.smStatus==true">
		${lz:set("haveEditable",true)}
		,"srvModel.smStatus":function(el){
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
	<form class="form editform" ref="SrvModel" id="modelForm" name="modelForm" action="${empty CTRL.action?"model_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写表单设计相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${srvModelToken}" name="srvModelToken" id="srvModelToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty srvModel.smId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$smId和after$smId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.smId==true">
	${before$smId}
	<dl class="smId ${CTRL.e.smId?"hidden":""}" major  ref="smId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.smId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.srvModel$smId!=null">${srvModel$smId}</s:if><s:else>
			<input type="hidden" value="${srvModel.smId}" name="srvModel.smId" id="smId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="smId">${srvModel.smId}</textarea>
		 		<span>
		 	
			 ${srvModel.smId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$smId}
	</s:if>
	
	${lz:set("注释","*****************对象名称字段的输入框代码*****************")}
	${lz:set("注释","before$smName和after$smName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.smName==true">
	${before$smName}
	<dl class="smName " major  ref="smName" >
		<dt>对象名称:</dt>
		<s:if test="#request.CTRL.e.smName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvModel$smName!=null">${srvModel$smName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="srvModel.smName" id="smName"  value="${srvModel.smName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入表单设计的对象名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****对象名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="smName">${srvModel.smName}</textarea>
		 		<span>
		 	
			 ${srvModel.smName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$smName}
	</s:if>
	
	${lz:set("注释","*****************英文名称字段的输入框代码*****************")}
	${lz:set("注释","before$smAction和after$smAction变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.smAction==true">
	${before$smAction}
	<dl class="smAction " major  ref="smAction" >
		<dt>英文名称:</dt>
		<s:if test="#request.CTRL.e.smAction==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvModel$smAction!=null">${srvModel$smAction}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="srvModel.smAction" id="smAction"  value="${srvModel.smAction}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入表单设计的英文名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****英文名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="smAction">${srvModel.smAction}</textarea>
		 		<span>
		 	
			 ${srvModel.smAction$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$smAction}
	</s:if>
	
	${lz:set("注释","*****************相对路径字段的输入框代码*****************")}
	${lz:set("注释","before$smNamespace和after$smNamespace变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.smNamespace==true">
	${before$smNamespace}
	<dl class="smNamespace " major  ref="smNamespace" >
		<dt>相对路径:</dt>
		<s:if test="#request.CTRL.e.smNamespace==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvModel$smNamespace!=null">${srvModel$smNamespace}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="srvModel.smNamespace" id="smNamespace"  value="${srvModel.smNamespace}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入表单设计的相对路径</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****相对路径字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="smNamespace">${srvModel.smNamespace}</textarea>
		 		<span>
		 	
			 ${srvModel.smNamespace$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$smNamespace}
	</s:if>
	
	${lz:set("注释","*****************结构类型字段的输入框代码*****************")}
	${lz:set("注释","before$smDisplay和after$smDisplay变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.smDisplay==true">
	${before$smDisplay}
	<dl class="smDisplay " major  ref="smDisplay" >
		<dt>结构类型:</dt>
		<s:if test="#request.CTRL.e.smDisplay==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.srvModel$smDisplay!=null">${srvModel$smDisplay}</s:if><s:else>
		 	<select class="narrow" id="smDisplay" name="srvModel.smDisplay">
		 		<option value="">请选择</option>
				<option value="table" ${srvModel.smDisplay=='table'?"selected":""}>普通列表</option>
				<option value="tree" ${srvModel.smDisplay=='tree'?"selected":""}>层级树形</option>
				<option value="form" ${srvModel.smDisplay=='form'?"selected":""}>单条表单</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择表单设计的结构类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结构类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="smDisplay">${srvModel.smDisplay}</textarea>
		 		<span>
		 	
			 ${srvModel.smDisplay$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$smDisplay}
	</s:if>
	
	${lz:set("注释","*****************支持功能字段的输入框代码*****************")}
	${lz:set("注释","before$smFunctions和after$smFunctions变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.smFunctions==true">
	${before$smFunctions}
	<dl class="smFunctions " major  ref="smFunctions" style="width:98%;">
		<dt>支持功能:</dt>
		<s:if test="#request.CTRL.e.smFunctions==true">
		${lz:set("haveEditable",true)}
		<dd input="checkbox">
		<s:if test="#request.srvModel$smFunctions!=null">${srvModel$smFunctions}</s:if><s:else>
		 	<div class='wide input'>	
		 	&nbsp; 	
		 	<input type="hidden" value="#0#" name="srvModel.smFunctions" id="smFunctions"/>
			<label><input type="checkbox" ${lz:idin(srvModel.smFunctions,1) ? "checked='checked'" : ""} value="#1#" name="srvModel.smFunctions" id="smFunctions"/>查询&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:idin(srvModel.smFunctions,2) ? "checked='checked'" : ""} value="#2#" name="srvModel.smFunctions" id="smFunctions"/>添加&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:idin(srvModel.smFunctions,3) ? "checked='checked'" : ""} value="#3#" name="srvModel.smFunctions" id="smFunctions"/>修改&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:idin(srvModel.smFunctions,4) ? "checked='checked'" : ""} value="#4#" name="srvModel.smFunctions" id="smFunctions"/>删除&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:idin(srvModel.smFunctions,5) ? "checked='checked'" : ""} value="#5#" name="srvModel.smFunctions" id="smFunctions"/>导出&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:idin(srvModel.smFunctions,6) ? "checked='checked'" : ""} value="#6#" name="srvModel.smFunctions" id="smFunctions"/>报表&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:idin(srvModel.smFunctions,7) ? "checked='checked'" : ""} value="#7#" name="srvModel.smFunctions" id="smFunctions"/>工作流&nbsp;&nbsp;&nbsp;</label>
		 </div>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择表单设计的支持功能</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****支持功能字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="smFunctions">${srvModel.smFunctions}</textarea>
		 		<span>
		 	
			 ${srvModel.smFunctions$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$smFunctions}
	</s:if>
	
	${lz:set("注释","*****************字段设计字段的输入框代码*****************")}
	${lz:set("注释","before$smFields和after$smFields变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.smFields==true">
	${before$smFields}
	<dl class="smFields " minor  ref="smFields" style="width:98%;">
		<dt>字段设计:</dt>
		<s:if test="#request.CTRL.e.smFields==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.srvModel$smFields!=null">${srvModel$smFields}</s:if><s:else>
		 	<textarea class="input wide"  id="smFields" name="srvModel.smFields" rows="5">${srvModel.smFields}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入表单设计的字段设计</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****字段设计字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${srvModel.smFields$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$smFields}
	</s:if>
	
	${lz:set("注释","*****************设计人字段的输入框代码*****************")}
	${lz:set("注释","before$smEditor和after$smEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.smEditor==true">
	${before$smEditor}
	<dl class="smEditor " major  ref="smEditor" >
		<dt>设&nbsp;&nbsp;计&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.smEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.srvModel$smEditor!=null">${srvModel$smEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="smEditor" name="srvModel.smEditor">
		 				<option selected value="${srvModel.smEditor}">
		 					${get:SrvUser(srvModel.smEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#smEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#smEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****设计人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="smEditor">${srvModel.smEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${srvModel.smEditor}',{ctrl:{editable:false,visible:true}})">
			 ${srvModel.smEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$smEditor}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$smUpdateTime和after$smUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.smUpdateTime==true">
	${before$smUpdateTime}
	<dl class="smUpdateTime " major  ref="smUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.smUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.srvModel$smUpdateTime!=null">${srvModel$smUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="srvModel.smUpdateTime" id="smUpdateTime"  value="<s:date name="srvModel.smUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择表单设计的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="smUpdateTime">${srvModel.smUpdateTime}</textarea>
		 		<span>
		 	
			 ${srvModel.smUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$smUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$smAddTime和after$smAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.smAddTime==true">
	${before$smAddTime}
	<dl class="smAddTime " major  ref="smAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.smAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.srvModel$smAddTime!=null">${srvModel$smAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="srvModel.smAddTime" id="smAddTime"  value="<s:date name="srvModel.smAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择表单设计的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="smAddTime">${srvModel.smAddTime}</textarea>
		 		<span>
		 	
			 ${srvModel.smAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$smAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$smStatus和after$smStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.smStatus==true">
	${before$smStatus}
	<dl class="smStatus " major  ref="smStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.smStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.srvModel$smStatus!=null">${srvModel$smStatus}</s:if><s:else>
		 	<select class="narrow" id="smStatus" name="srvModel.smStatus">
		 		<option value="">请选择</option>
				<option value="0" ${srvModel.smStatus==0?"selected":""}>新增|测试</option>
				<option value="1" ${srvModel.smStatus==1?"selected":""}>上线使用</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择表单设计的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="smStatus">${srvModel.smStatus}</textarea>
		 		<span>
		 	
			 ${srvModel.smStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$smStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${srvModel.smId}" name="srvModel.smId" id="smId" />
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