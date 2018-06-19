<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/systematic/log.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/systematic/log.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csLog.cslId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cslId,cslHost,cslName,cslModel,cslType,cslUser,cslClass,cslRelate,cslDetail",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cslId,cslHost,cslName,cslModel,cslType,cslUser,cslClass,cslRelate,cslDetail",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cslId,cslHost,cslName,cslModel,cslType,cslUser,cslClass,cslRelate,cslDetail",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cslId,cslHost,cslName,cslModel,cslType,cslUser,cslClass,cslRelate,cslDetail,cslAddTime",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"系统日志编辑":CTRL.title}</title>
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
	$("#logForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cslId==true">
		${lz:set("haveEditable",true)}
		,"csLog.cslId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslHost==true">
		${lz:set("haveEditable",true)}
		,"csLog.cslHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslName==true">
		${lz:set("haveEditable",true)}
		,"csLog.cslName":function(el){
			if(jQuery.trim(el.value)=="")
				return "操作名称不能为空";
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "操作名称最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslModel==true">
		${lz:set("haveEditable",true)}
		,"csLog.cslModel":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslType==true">
		${lz:set("haveEditable",true)}
		,"csLog.cslType":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslUser==true">
		${lz:set("haveEditable",true)}
		,"csLog.cslUser":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslClass==true">
		${lz:set("haveEditable",true)}
		,"csLog.cslClass":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "模块类名最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslRelate==true">
		${lz:set("haveEditable",true)}
		,"csLog.cslRelate":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslDetail==true">
		${lz:set("haveEditable",true)}
		,"csLog.cslDetail":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "数据状态最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslAddTime==true">
		${lz:set("haveEditable",true)}
		,"csLog.cslAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "操作时间不能为空";
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
	<form class="form editform" ref="CsLog" id="logForm" name="logForm" action="${empty CTRL.action?"log_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写系统日志相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csLogToken}" name="csLogToken" id="csLogToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csLog.cslId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cslId和after$cslId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslId==true">
	${before$cslId}
	<dl class="cslId ${CTRL.e.cslId?"hidden":""}" major  ref="cslId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cslId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csLog$cslId!=null">${csLog$cslId}</s:if><s:else>
			<input type="hidden" value="${csLog.cslId}" name="csLog.cslId" id="cslId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslId">${csLog.cslId}</textarea>
		 		<span>
		 	
			 ${csLog.cslId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslId}
	</s:if>
	
	${lz:set("注释","*****************域字段的输入框代码*****************")}
	${lz:set("注释","before$cslHost和after$cslHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslHost==true">
	${before$cslHost}
	<dl class="cslHost " major  ref="cslHost" >
		<dt>域:</dt>
		<s:if test="#request.CTRL.e.cslHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csLog$cslHost!=null">${csLog$cslHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cslHost" name="csLog.cslHost">
		 				<option selected value="${csLog.cslHost}">
		 					${get:SrvHost(csLog.cslHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cslHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cslHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****域字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslHost">${csLog.cslHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csLog.cslHost}',{ctrl:{editable:false,visible:true}})">
			 ${csLog.cslHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslHost}
	</s:if>
	
	${lz:set("注释","*****************操作名称字段的输入框代码*****************")}
	${lz:set("注释","before$cslName和after$cslName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslName==true">
	${before$cslName}
	<dl class="cslName " major  ref="cslName" style="width:98%;">
		<dt>操作名称:</dt>
		<s:if test="#request.CTRL.e.cslName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csLog$cslName!=null">${csLog$cslName}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csLog.cslName" id="cslName"  value="${csLog.cslName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入系统日志的操作名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****操作名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cslName">${csLog.cslName}</textarea>
		 		<span>
		 	
			 ${csLog.cslName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslName}
	</s:if>
	
	${lz:set("注释","*****************操作模块字段的输入框代码*****************")}
	${lz:set("注释","before$cslModel和after$cslModel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslModel==true">
	${before$cslModel}
	<dl class="cslModel " major  ref="cslModel" >
		<dt>操作模块:</dt>
		<s:if test="#request.CTRL.e.cslModel==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csLog$cslModel!=null">${csLog$cslModel}</s:if><s:else>
		 	<select class="narrow" id="cslModel" name="csLog.cslModel">
		 		<option value="">请选择</option>
 					${lz:set("items", get:propertys("系统数据模型类","model_class"))}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.spId}" ${csLog.cslModel==item.spId?"selected":""}>${item.spName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cslModel').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?key='+$('#cslModel').val(),{ctrl:{editable:false}})"></a>
	 			<a class="reladd" config="{
	 				action:'${basePath}${proname}/configurator/property_edit.do?rd=1&parent=${get:property("model_class").spId}'
	 				,callback:function(object){
	 					$('<option selected value='+object.spId+'>'+object.spName+'</option>').appendTo('#cslModel');
	 				}
	 			}" href="javascript:void(0);"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择系统日志的操作模块</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****操作模块字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslModel">${csLog.cslModel}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${csLog.cslModel}',{ctrl:{editable:false,visible:true}})">
			 ${csLog.cslModel$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslModel}
	</s:if>
	
	${lz:set("注释","*****************操作类型字段的输入框代码*****************")}
	${lz:set("注释","before$cslType和after$cslType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslType==true">
	${before$cslType}
	<dl class="cslType " major  ref="cslType" >
		<dt>操作类型:</dt>
		<s:if test="#request.CTRL.e.cslType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csLog$cslType!=null">${csLog$cslType}</s:if><s:else>
		 	<select class="narrow" id="cslType" name="csLog.cslType">
		 		<option value="">请选择</option>
 					${lz:set("items", get:propertys("日志操作类型","log_type"))}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.spId}" ${csLog.cslType==item.spId?"selected":""}>${item.spName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cslType').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?key='+$('#cslType').val(),{ctrl:{editable:false}})"></a>
	 			<a class="reladd" config="{
	 				action:'${basePath}${proname}/configurator/property_edit.do?rd=1&parent=${get:property("log_type").spId}'
	 				,callback:function(object){
	 					$('<option selected value='+object.spId+'>'+object.spName+'</option>').appendTo('#cslType');
	 				}
	 			}" href="javascript:void(0);"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择系统日志的操作类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****操作类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslType">${csLog.cslType}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${csLog.cslType}',{ctrl:{editable:false,visible:true}})">
			 ${csLog.cslType$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslType}
	</s:if>
	
	${lz:set("注释","*****************操作人字段的输入框代码*****************")}
	${lz:set("注释","before$cslUser和after$cslUser变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslUser==true">
	${before$cslUser}
	<dl class="cslUser " major  ref="cslUser" >
		<dt>操&nbsp;&nbsp;作&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.cslUser==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csLog$cslUser!=null">${csLog$cslUser}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cslUser" name="csLog.cslUser">
		 				<option selected value="${csLog.cslUser}">
		 					${get:SrvUser(csLog.cslUser).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cslUser').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cslUser').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****操作人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslUser">${csLog.cslUser}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csLog.cslUser}',{ctrl:{editable:false,visible:true}})">
			 ${csLog.cslUser$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslUser}
	</s:if>
	
	${lz:set("注释","*****************模块类名字段的输入框代码*****************")}
	${lz:set("注释","before$cslClass和after$cslClass变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslClass==true">
	${before$cslClass}
	<dl class="cslClass " minor  ref="cslClass" >
		<dt>模块类名:</dt>
		<s:if test="#request.CTRL.e.cslClass==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csLog$cslClass!=null">${csLog$cslClass}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csLog.cslClass" id="cslClass"  value="${csLog.cslClass}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统日志的模块类名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****模块类名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslClass">${csLog.cslClass}</textarea>
		 		<span>
		 	
			 ${csLog.cslClass$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslClass}
	</s:if>
	
	${lz:set("注释","*****************数据ID字段的输入框代码*****************")}
	${lz:set("注释","before$cslRelate和after$cslRelate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslRelate==true">
	${before$cslRelate}
	<dl class="cslRelate " minor  ref="cslRelate" >
		<dt>数据ID:</dt>
		<s:if test="#request.CTRL.e.cslRelate==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csLog$cslRelate!=null">${csLog$cslRelate}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLog.cslRelate" id="cslRelate"  value="${csLog.cslRelate}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统日志的数据ID</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****数据ID字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslRelate">${csLog.cslRelate}</textarea>
		 		<span>
		 	
			 ${csLog.cslRelate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslRelate}
	</s:if>
	
	${lz:set("注释","*****************数据状态字段的输入框代码*****************")}
	${lz:set("注释","before$cslDetail和after$cslDetail变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslDetail==true">
	${before$cslDetail}
	<dl class="cslDetail " major  ref="cslDetail" style="width:98%;">
		<dt>数据状态:</dt>
		<s:if test="#request.CTRL.e.cslDetail==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csLog$cslDetail!=null">${csLog$cslDetail}</s:if><s:else>
		 	<textarea class="input wide"  id="cslDetail" name="csLog.cslDetail" rows="5">${csLog.cslDetail}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统日志的数据状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****数据状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csLog.cslDetail$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslDetail}
	</s:if>
	
	${lz:set("注释","*****************操作时间字段的输入框代码*****************")}
	${lz:set("注释","before$cslAddTime和after$cslAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslAddTime==true">
	${before$cslAddTime}
	<dl class="cslAddTime " major  ref="cslAddTime" >
		<dt>操作时间:</dt>
		<s:if test="#request.CTRL.e.cslAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csLog$cslAddTime!=null">${csLog$cslAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csLog.cslAddTime" id="cslAddTime"  value="<s:date name="csLog.cslAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统日志的操作时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****操作时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslAddTime">${csLog.cslAddTime}</textarea>
		 		<span>
		 	
			 ${csLog.cslAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslAddTime}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csLog.cslId}" name="csLog.cslId" id="cslId" />
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