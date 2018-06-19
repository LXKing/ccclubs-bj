<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/systematic/help.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/systematic/help.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty srvHelp.shId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"shId,shName,shParent,shProfile,shText,shLevel,shStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"shId,shName,shParent,shProfile,shText,shLevel,shStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"shId,shName,shParent,shProfile,shText,shLevel,shStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"shId,shName,shParent,shProfile,shText,shLevel,shStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"系统帮助编辑":CTRL.title}</title>
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
	<script type="text/javascript" src="${basePath}admin/js/jquery-ui-1.10.2.custom.min.js"></script>
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
	$(".imagelist").sortable();
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
		if($("#shText").size()>0){
			 //构造编辑页srvHelp.shText为html编辑控件
			 KE.init({
				id : 'shText',
				width : '625px',
				height : '300px' , 
				imageUploadJson : '${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}?app=help_edit&type=img${ lz:config("upload.url")==null ? "" : "&remote="}${ lz:config("upload.url")==null ? "" : basePath}${ lz:config("upload.url")==null ? "" : "callback.jsp"}'
			 });
			 KE.create("shText");
		 }
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#helpForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.shId==true">
		${lz:set("haveEditable",true)}
		,"srvHelp.shId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.shName==true">
		${lz:set("haveEditable",true)}
		,"srvHelp.shName":function(el){
			if(jQuery.trim(el.value)=="")
				return "主题名称不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.shParent==true">
		${lz:set("haveEditable",true)}
		,"srvHelp.shParent":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.shProfile==true">
		${lz:set("haveEditable",true)}
		,"srvHelp.shProfile":function(el){
			if(jQuery.trim(el.value)=="")
				return "帮助简述不能为空";
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "帮助简述最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.shText==true">
		${lz:set("haveEditable",true)}
		,"srvHelp.shText":function(el){
			if($("#shText").get(0))
			 	KE.sync("shText");		 
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "详细说明最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.shMind==true">
		${lz:set("haveEditable",true)}
		,"srvHelp.shMind":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.shImages==true">
		${lz:set("haveEditable",true)}
		,"srvHelp.shImages":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.shEditor==true">
		${lz:set("haveEditable",true)}
		,"srvHelp.shEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.shAddTime==true">
		${lz:set("haveEditable",true)}
		,"srvHelp.shAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.shUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"srvHelp.shUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.shLevel==true">
		${lz:set("haveEditable",true)}
		,"srvHelp.shLevel":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.shStatus==true">
		${lz:set("haveEditable",true)}
		,"srvHelp.shStatus":function(el){
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
	<form class="form editform" ref="SrvHelp" id="helpForm" name="helpForm" action="${empty CTRL.action?"help_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写系统帮助相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${srvHelpToken}" name="srvHelpToken" id="srvHelpToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty srvHelp.shId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$shId和after$shId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.shId==true">
	${before$shId}
	<dl class="shId ${CTRL.e.shId?"hidden":""}" major  ref="shId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.shId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.srvHelp$shId!=null">${srvHelp$shId}</s:if><s:else>
			<input type="hidden" value="${srvHelp.shId}" name="srvHelp.shId" id="shId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="shId">${srvHelp.shId}</textarea>
		 		<span>
		 	
			 ${srvHelp.shId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$shId}
	</s:if>
	
	${lz:set("注释","*****************主题名称字段的输入框代码*****************")}
	${lz:set("注释","before$shName和after$shName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.shName==true">
	${before$shName}
	<dl class="shName " major  ref="shName" >
		<dt>主题名称:</dt>
		<s:if test="#request.CTRL.e.shName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvHelp$shName!=null">${srvHelp$shName}</s:if><s:else>
		 	<input type="text" class="input narrow"   name="srvHelp.shName" id="shName"  value="${srvHelp.shName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>建议8个字以内的名称，建议无标点符号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****主题名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="shName">${srvHelp.shName}</textarea>
		 		<span>
		 	
			 ${srvHelp.shName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$shName}
	</s:if>
	
	${lz:set("注释","*****************上级目录字段的输入框代码*****************")}
	${lz:set("注释","before$shParent和after$shParent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.shParent==true">
	${before$shParent}
	<dl class="shParent " major  ref="shParent" >
		<dt>上级目录:</dt>
		<s:if test="#request.CTRL.e.shParent==true">
		${lz:set("haveEditable",true)}
		<dd input="label">
		<s:if test="#request.srvHelp$shParent!=null">${srvHelp$shParent}</s:if><s:else>
			<input type="hidden" name="srvHelp.shParent" id="srvHelp.shParent"  value="${parentSrvHelp==null ? "0" : parentSrvHelp.shId}"/>
			${parentSrvHelp==null ? "顶级节点" : parentSrvHelp.shName$}
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****上级目录字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="shParent">${srvHelp.shParent}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/systematic/help_details.do?key=${srvHelp.shParent}',{ctrl:{editable:false,visible:true}})">
			 ${srvHelp.shParent$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$shParent}
	</s:if>
	
	${lz:set("注释","*****************帮助简述字段的输入框代码*****************")}
	${lz:set("注释","before$shProfile和after$shProfile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.shProfile==true">
	${before$shProfile}
	<dl class="shProfile " major  ref="shProfile" style="width:98%;">
		<dt>帮助简述:</dt>
		<s:if test="#request.CTRL.e.shProfile==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvHelp$shProfile!=null">${srvHelp$shProfile}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="srvHelp.shProfile" id="shProfile"  value="${srvHelp.shProfile}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>一句话描述一下该帮助功能帮助</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****帮助简述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="shProfile">${srvHelp.shProfile}</textarea>
		 		<span>
		 	
			 ${srvHelp.shProfile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$shProfile}
	</s:if>
	
	${lz:set("注释","*****************详细说明字段的输入框代码*****************")}
	${lz:set("注释","before$shText和after$shText变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.shText==true">
	${before$shText}
	<dl class="shText " minor  ref="shText" style="width:98%;">
		<dt>详细说明:</dt>
		<s:if test="#request.CTRL.e.shText==true">
		${lz:set("haveEditable",true)}
		<dd input="html">
		<s:if test="#request.srvHelp$shText!=null">${srvHelp$shText}</s:if><s:else>
		    <textarea class="input wide"  id="shText" name="srvHelp.shText" style="height:300px;visibility:hidden;">${srvHelp.shText}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统帮助的详细说明</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****详细说明字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${srvHelp.shText$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$shText}
	</s:if>
	
	${lz:set("注释","*****************流程导向字段的输入框代码*****************")}
	${lz:set("注释","before$shMind和after$shMind变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.shMind==true">
	${before$shMind}
	<dl class="shMind ${CTRL.e.shMind?"hidden":""}" minor  ref="shMind" >
		<dt>流程导向:</dt>
		<s:if test="#request.CTRL.e.shMind==true">
		${lz:set("haveEditable",true)}
		<dd input="hidden">
		<s:if test="#request.srvHelp$shMind!=null">${srvHelp$shMind}</s:if><s:else>
			<input type="hidden" value="${srvHelp.shMind}" name="srvHelp.shMind" id="shMind" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****流程导向字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="shMind">${srvHelp.shMind}</textarea>
		 		<span>
		 	
			 ${srvHelp.shMind$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$shMind}
	</s:if>
	
	${lz:set("注释","*****************操作截图字段的输入框代码*****************")}
	${lz:set("注释","before$shImages和after$shImages变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.shImages==true">
	${before$shImages}
	<dl class="shImages ${CTRL.e.shImages?"hidden":""}" minor  ref="shImages" >
		<dt>操作截图:</dt>
		<s:if test="#request.CTRL.e.shImages==true">
		${lz:set("haveEditable",true)}
		<dd input="hidden">
		<s:if test="#request.srvHelp$shImages!=null">${srvHelp$shImages}</s:if><s:else>
			<input type="hidden" value="${srvHelp.shImages}" name="srvHelp.shImages" id="shImages" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****操作截图字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="shImages">${srvHelp.shImages}</textarea>
		 		<span>
		 	
			 ${srvHelp.shImages$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$shImages}
	</s:if>
	
	${lz:set("注释","*****************修改人员字段的输入框代码*****************")}
	${lz:set("注释","before$shEditor和after$shEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.shEditor==true">
	${before$shEditor}
	<dl class="shEditor " major  ref="shEditor" >
		<dt>修改人员:</dt>
		<s:if test="#request.CTRL.e.shEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.srvHelp$shEditor!=null">${srvHelp$shEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="shEditor" name="srvHelp.shEditor">
		 				<option selected value="${srvHelp.shEditor}">
		 					${get:SrvUser(srvHelp.shEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#shEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#shEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改人员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="shEditor">${srvHelp.shEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${srvHelp.shEditor}',{ctrl:{editable:false,visible:true}})">
			 ${srvHelp.shEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$shEditor}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$shAddTime和after$shAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.shAddTime==true">
	${before$shAddTime}
	<dl class="shAddTime " major  ref="shAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.shAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.srvHelp$shAddTime!=null">${srvHelp$shAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="srvHelp.shAddTime" id="shAddTime"  value="<s:date name="srvHelp.shAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统帮助的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="shAddTime">${srvHelp.shAddTime}</textarea>
		 		<span>
		 	
			 ${srvHelp.shAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$shAddTime}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$shUpdateTime和after$shUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.shUpdateTime==true">
	${before$shUpdateTime}
	<dl class="shUpdateTime " major  ref="shUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.shUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.srvHelp$shUpdateTime!=null">${srvHelp$shUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="srvHelp.shUpdateTime" id="shUpdateTime"  value="<s:date name="srvHelp.shUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统帮助的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="shUpdateTime">${srvHelp.shUpdateTime}</textarea>
		 		<span>
		 	
			 ${srvHelp.shUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$shUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************优先级字段的输入框代码*****************")}
	${lz:set("注释","before$shLevel和after$shLevel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.shLevel==true">
	${before$shLevel}
	<dl class="shLevel " major  ref="shLevel" >
		<dt>优&nbsp;&nbsp;先&nbsp;&nbsp;级:</dt>
		<s:if test="#request.CTRL.e.shLevel==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.srvHelp$shLevel!=null">${srvHelp$shLevel}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="srvHelp.shLevel" id="shLevel"  value="${srvHelp.shLevel}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入一个100以内的数描述其先后顺序</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****优先级字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="shLevel">${srvHelp.shLevel}</textarea>
		 		<span>
		 	
			 ${srvHelp.shLevel$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$shLevel}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$shStatus和after$shStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.shStatus==true">
	${before$shStatus}
	<dl class="shStatus " major  ref="shStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.shStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.srvHelp$shStatus!=null">${srvHelp$shStatus}</s:if><s:else>
		 	<select class="narrow" id="shStatus" name="srvHelp.shStatus">
		 		<option value="">请选择</option>
				<option value="1" ${srvHelp.shStatus==1?"selected":""}>有效</option>
				<option value="0" ${srvHelp.shStatus==0?"selected":""}>内容</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统帮助的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="shStatus">${srvHelp.shStatus}</textarea>
		 		<span>
		 	
			 ${srvHelp.shStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$shStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${srvHelp.shId}" name="srvHelp.shId" id="shId" />
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