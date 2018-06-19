<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/oa/workflow.do")}
<!--LAZY3Q_JSP_TOP-->
<%@ include file="workflow_editor.jsp"%>
<%if(true)return;%>
<!--LAZY3Q_JSP_TOP-->

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/oa/workflow.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty srvWorkflow.swId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"swId,swName,swProfile,swPaint,swConfig,swStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"swId,swName,swProfile,swPaint,swConfig,swStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"swId,swName,swProfile,swPaint,swConfig,swStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"swId,swName,swProfile,swPaint,swConfig,swEditor,swUpdateTime,swAddTime,swStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"工作流编辑":CTRL.title}</title>
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
	$("#workflowForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.swId==true">
		${lz:set("haveEditable",true)}
		,"srvWorkflow.swId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.swName==true">
		${lz:set("haveEditable",true)}
		,"srvWorkflow.swName":function(el){
			if(jQuery.trim(el.value)=="")
				return "名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.swProfile==true">
		${lz:set("haveEditable",true)}
		,"srvWorkflow.swProfile":function(el){
			if(el.value.length>512 && el.value.indexOf("[*]")==-1)
				return "工作流描述最大长度为512个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.swPaint==true">
		${lz:set("haveEditable",true)}
		,"srvWorkflow.swPaint":function(el){
			if(jQuery.trim(el.value)=="")
				return "流程图设计不能为空";
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "流程图设计最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.swConfig==true">
		${lz:set("haveEditable",true)}
		,"srvWorkflow.swConfig":function(el){
			if(jQuery.trim(el.value)=="")
				return "流程配置不能为空";
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "流程配置最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.swEditor==true">
		${lz:set("haveEditable",true)}
		,"srvWorkflow.swEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.swUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"srvWorkflow.swUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.swAddTime==true">
		${lz:set("haveEditable",true)}
		,"srvWorkflow.swAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.swStatus==true">
		${lz:set("haveEditable",true)}
		,"srvWorkflow.swStatus":function(el){
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
	<form class="form editform" ref="SrvWorkflow" id="workflowForm" name="workflowForm" action="${empty CTRL.action?"workflow_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写工作流相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${srvWorkflowToken}" name="srvWorkflowToken" id="srvWorkflowToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty srvWorkflow.swId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$swId和after$swId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.swId==true">
	${before$swId}
	<dl class="swId ${CTRL.e.swId?"hidden":""}" major  ref="swId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.swId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.srvWorkflow$swId!=null">${srvWorkflow$swId}</s:if><s:else>
			<input type="hidden" value="${srvWorkflow.swId}" name="srvWorkflow.swId" id="swId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="swId">${srvWorkflow.swId}</textarea>
		 		<span>
		 	
			 ${srvWorkflow.swId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$swId}
	</s:if>
	
	${lz:set("注释","*****************名称字段的输入框代码*****************")}
	${lz:set("注释","before$swName和after$swName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.swName==true">
	${before$swName}
	<dl class="swName " major  ref="swName" >
		<dt>名　　称:</dt>
		<s:if test="#request.CTRL.e.swName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvWorkflow$swName!=null">${srvWorkflow$swName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="srvWorkflow.swName" id="swName"  value="${srvWorkflow.swName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入工作流的名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="swName">${srvWorkflow.swName}</textarea>
		 		<span>
		 	
			 ${srvWorkflow.swName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$swName}
	</s:if>
	
	${lz:set("注释","*****************工作流描述字段的输入框代码*****************")}
	${lz:set("注释","before$swProfile和after$swProfile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.swProfile==true">
	${before$swProfile}
	<dl class="swProfile " minor  ref="swProfile" style="width:98%;">
		<dt>工作流描述:</dt>
		<s:if test="#request.CTRL.e.swProfile==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.srvWorkflow$swProfile!=null">${srvWorkflow$swProfile}</s:if><s:else>
		 	<textarea class="input wide"  id="swProfile" name="srvWorkflow.swProfile" rows="5">${srvWorkflow.swProfile}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入工作流的工作流描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****工作流描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${srvWorkflow.swProfile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$swProfile}
	</s:if>
	
	${lz:set("注释","*****************流程图设计字段的输入框代码*****************")}
	${lz:set("注释","before$swPaint和after$swPaint变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.swPaint==true">
	${before$swPaint}
	<dl class="swPaint " major  ref="swPaint" style="width:98%;">
		<dt>流程图设计:</dt>
		<s:if test="#request.CTRL.e.swPaint==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.srvWorkflow$swPaint!=null">${srvWorkflow$swPaint}</s:if><s:else>
		 	<textarea class="input wide"  id="swPaint" name="srvWorkflow.swPaint" rows="5">${srvWorkflow.swPaint}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入工作流的流程图设计</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****流程图设计字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${srvWorkflow.swPaint$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$swPaint}
	</s:if>
	
	${lz:set("注释","*****************流程配置字段的输入框代码*****************")}
	${lz:set("注释","before$swConfig和after$swConfig变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.swConfig==true">
	${before$swConfig}
	<dl class="swConfig " major  ref="swConfig" style="width:98%;">
		<dt>流程配置:</dt>
		<s:if test="#request.CTRL.e.swConfig==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.srvWorkflow$swConfig!=null">${srvWorkflow$swConfig}</s:if><s:else>
		 	<textarea class="input wide"  id="swConfig" name="srvWorkflow.swConfig" rows="5">${srvWorkflow.swConfig}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入工作流的流程配置</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****流程配置字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${srvWorkflow.swConfig$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$swConfig}
	</s:if>
	
	${lz:set("注释","*****************设计人字段的输入框代码*****************")}
	${lz:set("注释","before$swEditor和after$swEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.swEditor==true">
	${before$swEditor}
	<dl class="swEditor " major  ref="swEditor" >
		<dt>设&nbsp;&nbsp;计&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.swEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.srvWorkflow$swEditor!=null">${srvWorkflow$swEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="swEditor" name="srvWorkflow.swEditor">
		 				<option selected value="${srvWorkflow.swEditor}">
		 					${get:SrvUser(srvWorkflow.swEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#swEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#swEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****设计人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="swEditor">${srvWorkflow.swEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${srvWorkflow.swEditor}',{ctrl:{editable:false,visible:true}})">
			 ${srvWorkflow.swEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$swEditor}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$swUpdateTime和after$swUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.swUpdateTime==true">
	${before$swUpdateTime}
	<dl class="swUpdateTime " major  ref="swUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.swUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.srvWorkflow$swUpdateTime!=null">${srvWorkflow$swUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="srvWorkflow.swUpdateTime" id="swUpdateTime"  value="<s:date name="srvWorkflow.swUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择工作流的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="swUpdateTime">${srvWorkflow.swUpdateTime}</textarea>
		 		<span>
		 	
			 ${srvWorkflow.swUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$swUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$swAddTime和after$swAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.swAddTime==true">
	${before$swAddTime}
	<dl class="swAddTime " major  ref="swAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.swAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.srvWorkflow$swAddTime!=null">${srvWorkflow$swAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="srvWorkflow.swAddTime" id="swAddTime"  value="<s:date name="srvWorkflow.swAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择工作流的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="swAddTime">${srvWorkflow.swAddTime}</textarea>
		 		<span>
		 	
			 ${srvWorkflow.swAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$swAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$swStatus和after$swStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.swStatus==true">
	${before$swStatus}
	<dl class="swStatus " major  ref="swStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.swStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.srvWorkflow$swStatus!=null">${srvWorkflow$swStatus}</s:if><s:else>
		 	<select class="narrow" id="swStatus" name="srvWorkflow.swStatus">
		 		<option value="">请选择</option>
				<option value="1" ${srvWorkflow.swStatus==1?"selected":""}>正常</option>
				<option value="0" ${srvWorkflow.swStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择工作流的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="swStatus">${srvWorkflow.swStatus}</textarea>
		 		<span>
		 	
			 ${srvWorkflow.swStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$swStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${srvWorkflow.swId}" name="srvWorkflow.swId" id="swId" />
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