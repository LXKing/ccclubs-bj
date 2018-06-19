<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/configurator/template.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/configurator/template.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csSmsTemplate.csstId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csstId,csstHost,csstName,csstContent,csstType,csstFlag,csstStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csstId,csstHost,csstName,csstContent,csstType,csstFlag,csstStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csstId,csstHost,csstName,csstContent,csstType,csstFlag,csstStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csstId,csstHost,csstName,csstContent,csstType,csstFlag,csstEditor,csstUpdateTime,csstAddTime,csstStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"短信模版编辑":CTRL.title}</title>
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
<script>
$(function(){
	$("<option value=\"0\" ${csSmsTemplate.csstHost==0?"selected":""}>默认(通用)</option>").insertAfter("#csstHost option:first");
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
	$("#templateForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csstId==true">
		${lz:set("haveEditable",true)}
		,"csSmsTemplate.csstId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csstHost==true">
		${lz:set("haveEditable",true)}
		,"csSmsTemplate.csstHost":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择城市";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csstName==true">
		${lz:set("haveEditable",true)}
		,"csSmsTemplate.csstName":function(el){
			if(jQuery.trim(el.value)=="")
				return "模版名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "模版名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csstContent==true">
		${lz:set("haveEditable",true)}
		,"csSmsTemplate.csstContent":function(el){
			if(jQuery.trim(el.value)=="")
				return "模版内容不能为空";
			if(el.value.length>1024 && el.value.indexOf("[*]")==-1)
				return "模版内容最大长度为1024个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csstType==true">
		${lz:set("haveEditable",true)}
		,"csSmsTemplate.csstType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择短信类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csstFlag==true">
		${lz:set("haveEditable",true)}
		,"csSmsTemplate.csstFlag":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "模版标识最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csstEditor==true">
		${lz:set("haveEditable",true)}
		,"csSmsTemplate.csstEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csstUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csSmsTemplate.csstUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csstAddTime==true">
		${lz:set("haveEditable",true)}
		,"csSmsTemplate.csstAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csstStatus==true">
		${lz:set("haveEditable",true)}
		,"csSmsTemplate.csstStatus":function(el){
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
	<form class="form editform" ref="CsSmsTemplate" id="templateForm" name="templateForm" action="${empty CTRL.action?"template_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写短信模版相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csSmsTemplateToken}" name="csSmsTemplateToken" id="csSmsTemplateToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csSmsTemplate.csstId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csstId和after$csstId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csstId==true">
	${before$csstId}
	<dl class="csstId ${CTRL.e.csstId?"hidden":""}" major  ref="csstId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csstId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csSmsTemplate$csstId!=null">${csSmsTemplate$csstId}</s:if><s:else>
			<input type="hidden" value="${csSmsTemplate.csstId}" name="csSmsTemplate.csstId" id="csstId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csstId">${csSmsTemplate.csstId}</textarea>
		 		<span>
		 	
			 ${csSmsTemplate.csstId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csstId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csstHost和after$csstHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csstHost==true">
	${before$csstHost}
	<dl class="csstHost " major  ref="csstHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csstHost==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csSmsTemplate$csstHost!=null">${csSmsTemplate$csstHost}</s:if><s:else>
		 	<select class="narrow" id="csstHost" name="csSmsTemplate.csstHost">
		 		<option value="">请选择</option>
 					${lz:set("items", lz:query("ccclubs_system","select * from srv_host where 1=1 ") )}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.shId}" ${csSmsTemplate.csstHost==item.shId?"selected":""}>${item.shName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csstHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csstHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择短信模版的城市</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csstHost">${csSmsTemplate.csstHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csSmsTemplate.csstHost}',{ctrl:{editable:false,visible:true}})">
			 ${csSmsTemplate.csstHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csstHost}
	</s:if>
	
	${lz:set("注释","*****************模版名称字段的输入框代码*****************")}
	${lz:set("注释","before$csstName和after$csstName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csstName==true">
	${before$csstName}
	<dl class="csstName " major  ref="csstName" >
		<dt>模版名称:</dt>
		<s:if test="#request.CTRL.e.csstName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csSmsTemplate$csstName!=null">${csSmsTemplate$csstName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csSmsTemplate.csstName" id="csstName"  value="${csSmsTemplate.csstName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入短信模版的模版名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****模版名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csstName">${csSmsTemplate.csstName}</textarea>
		 		<span>
		 	
			 ${csSmsTemplate.csstName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csstName}
	</s:if>
	
	${lz:set("注释","*****************模版内容字段的输入框代码*****************")}
	${lz:set("注释","before$csstContent和after$csstContent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csstContent==true">
	${before$csstContent}
	<dl class="csstContent " major  ref="csstContent" style="width:98%;">
		<dt>模版内容:</dt>
		<s:if test="#request.CTRL.e.csstContent==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csSmsTemplate$csstContent!=null">${csSmsTemplate$csstContent}</s:if><s:else>
		 	<textarea class="input wide"  id="csstContent" name="csSmsTemplate.csstContent" rows="5">${csSmsTemplate.csstContent}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入短信模版的模版内容</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****模版内容字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csSmsTemplate.csstContent$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csstContent}
	</s:if>
	
	${lz:set("注释","*****************短信类型字段的输入框代码*****************")}
	${lz:set("注释","before$csstType和after$csstType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csstType==true">
	${before$csstType}
	<dl class="csstType " major  ref="csstType" >
		<dt>短信类型:</dt>
		<s:if test="#request.CTRL.e.csstType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csSmsTemplate$csstType!=null">${csSmsTemplate$csstType}</s:if><s:else>
		 	<select class="narrow" id="csstType" name="csSmsTemplate.csstType">
		 		<option value="">请选择</option>
				<option value="1" ${csSmsTemplate.csstType==1?"selected":""}>代码类短信</option>
				<option value="2" ${csSmsTemplate.csstType==2?"selected":""}>通知类短信</option>
				<option value="3" ${csSmsTemplate.csstType==3?"selected":""}>提示类短信</option>
				<option value="4" ${csSmsTemplate.csstType==4?"selected":""}>广告类短信</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择短信模版的短信类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****短信类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csstType">${csSmsTemplate.csstType}</textarea>
		 		<span>
		 	
			 ${csSmsTemplate.csstType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csstType}
	</s:if>
	
	${lz:set("注释","*****************模版标识字段的输入框代码*****************")}
	${lz:set("注释","before$csstFlag和after$csstFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csstFlag==true">
	${before$csstFlag}
	<dl class="csstFlag " major  ref="csstFlag" >
		<dt>模版标识:</dt>
		<s:if test="#request.CTRL.e.csstFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csSmsTemplate$csstFlag!=null">${csSmsTemplate$csstFlag}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csSmsTemplate.csstFlag" id="csstFlag"  value="${csSmsTemplate.csstFlag}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入短信模版的模版标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****模版标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csstFlag">${csSmsTemplate.csstFlag}</textarea>
		 		<span>
		 	
			 ${csSmsTemplate.csstFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csstFlag}
	</s:if>
	
	${lz:set("注释","*****************添加人字段的输入框代码*****************")}
	${lz:set("注释","before$csstEditor和after$csstEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csstEditor==true">
	${before$csstEditor}
	<dl class="csstEditor " minor  ref="csstEditor" >
		<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csstEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csSmsTemplate$csstEditor!=null">${csSmsTemplate$csstEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csstEditor" name="csSmsTemplate.csstEditor">
		 				<option selected value="${csSmsTemplate.csstEditor}">
		 					${get:SrvUser(csSmsTemplate.csstEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csstEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csstEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csstEditor">${csSmsTemplate.csstEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csSmsTemplate.csstEditor}',{ctrl:{editable:false,visible:true}})">
			 ${csSmsTemplate.csstEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csstEditor}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csstUpdateTime和after$csstUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csstUpdateTime==true">
	${before$csstUpdateTime}
	<dl class="csstUpdateTime " major  ref="csstUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csstUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csSmsTemplate$csstUpdateTime!=null">${csSmsTemplate$csstUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csSmsTemplate.csstUpdateTime" id="csstUpdateTime"  value="<s:date name="csSmsTemplate.csstUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择短信模版的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csstUpdateTime">${csSmsTemplate.csstUpdateTime}</textarea>
		 		<span>
		 	
			 ${csSmsTemplate.csstUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csstUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csstAddTime和after$csstAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csstAddTime==true">
	${before$csstAddTime}
	<dl class="csstAddTime " major  ref="csstAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csstAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csSmsTemplate$csstAddTime!=null">${csSmsTemplate$csstAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csSmsTemplate.csstAddTime" id="csstAddTime"  value="<s:date name="csSmsTemplate.csstAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择短信模版的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csstAddTime">${csSmsTemplate.csstAddTime}</textarea>
		 		<span>
		 	
			 ${csSmsTemplate.csstAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csstAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csstStatus和after$csstStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csstStatus==true">
	${before$csstStatus}
	<dl class="csstStatus " major  ref="csstStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csstStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csSmsTemplate$csstStatus!=null">${csSmsTemplate$csstStatus}</s:if><s:else>
		 	<select class="narrow" id="csstStatus" name="csSmsTemplate.csstStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csSmsTemplate.csstStatus==1?"selected":""}>正常</option>
				<option value="0" ${csSmsTemplate.csstStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择短信模版的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csstStatus">${csSmsTemplate.csstStatus}</textarea>
		 		<span>
		 	
			 ${csSmsTemplate.csstStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csstStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csSmsTemplate.csstId}" name="csSmsTemplate.csstId" id="csstId" />
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