<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/configurator/task.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/configurator/task.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csTask.cstId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cstId,cstName,cstFlag,cstUserIds,cstSql,cstTips,cstUrl,cstStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cstId,cstName,cstFlag,cstUserIds,cstSql,cstTips,cstUrl,cstStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cstId,cstName,cstFlag,cstUserIds,cstSql,cstTips,cstUrl,cstStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cstId,cstName,cstFlag,cstUserIds,cstSql,cstTips,cstUrl,cstStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"数据任务编辑":CTRL.title}</title>
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
	$("#taskForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cstId==true">
		${lz:set("haveEditable",true)}
		,"csTask.cstId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstName==true">
		${lz:set("haveEditable",true)}
		,"csTask.cstName":function(el){
			if(jQuery.trim(el.value)=="")
				return "任务名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "任务名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstFlag==true">
		${lz:set("haveEditable",true)}
		,"csTask.cstFlag":function(el){
			if(jQuery.trim(el.value)=="")
				return "任务标识不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "任务标识最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("task_query.do",{exists:true,cstFlag:el.value});
				if(exists && exists.length>0){
					if(${csTask.cstId==null} || exists[0].value!=$("#cstId").val())
						return "任务标识已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstUserIds==true">
		${lz:set("haveEditable",true)}
		,"csTask.cstUserIds":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstSql==true">
		${lz:set("haveEditable",true)}
		,"csTask.cstSql":function(el){
			if(jQuery.trim(el.value)=="")
				return "SQL语句不能为空";
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "SQL语句最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstTips==true">
		${lz:set("haveEditable",true)}
		,"csTask.cstTips":function(el){
			if(jQuery.trim(el.value)=="")
				return "消息提醒不能为空";
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "消息提醒最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstUrl==true">
		${lz:set("haveEditable",true)}
		,"csTask.cstUrl":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "处理地址最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstEditTime==true">
		${lz:set("haveEditable",true)}
		,"csTask.cstEditTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "编辑时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstStatus==true">
		${lz:set("haveEditable",true)}
		,"csTask.cstStatus":function(el){
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
	<form class="form editform" ref="CsTask" id="taskForm" name="taskForm" action="${empty CTRL.action?"task_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写数据任务相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csTaskToken}" name="csTaskToken" id="csTaskToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csTask.cstId))}		
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
		<s:if test="#request.csTask$cstId!=null">${csTask$cstId}</s:if><s:else>
			<input type="hidden" value="${csTask.cstId}" name="csTask.cstId" id="cstId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstId">${csTask.cstId}</textarea>
		 		<span>
		 	
			 ${csTask.cstId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstId}
	</s:if>
	
	${lz:set("注释","*****************任务名称字段的输入框代码*****************")}
	${lz:set("注释","before$cstName和after$cstName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstName==true">
	${before$cstName}
	<dl class="cstName " major  ref="cstName" >
		<dt>任务名称:</dt>
		<s:if test="#request.CTRL.e.cstName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csTask$cstName!=null">${csTask$cstName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csTask.cstName" id="cstName"  value="${csTask.cstName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入数据任务的任务名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****任务名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstName">${csTask.cstName}</textarea>
		 		<span>
		 	
			 ${csTask.cstName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstName}
	</s:if>
	
	${lz:set("注释","*****************任务标识字段的输入框代码*****************")}
	${lz:set("注释","before$cstFlag和after$cstFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstFlag==true">
	${before$cstFlag}
	<dl class="cstFlag " major  ref="cstFlag" >
		<dt>任务标识:</dt>
		<s:if test="#request.CTRL.e.cstFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csTask$cstFlag!=null">${csTask$cstFlag}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csTask.cstFlag" id="cstFlag"  value="${csTask.cstFlag}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入数据任务的任务标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****任务标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstFlag">${csTask.cstFlag}</textarea>
		 		<span>
		 	
			 ${csTask.cstFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstFlag}
	</s:if>
	
	${lz:set("注释","*****************处理人员字段的输入框代码*****************")}
	${lz:set("注释","before$cstUserIds和after$cstUserIds变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstUserIds==true">
	${before$cstUserIds}
	<dl class="cstUserIds " minor  ref="cstUserIds" >
		<dt>处理人员:</dt>
		<s:if test="#request.CTRL.e.cstUserIds==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csTask$cstUserIds!=null">${csTask$cstUserIds}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cstUserIds" name="csTask.cstUserIds">
		 				<option selected value="${csTask.cstUserIds}">
		 					${get:SrvUser(csTask.cstUserIds).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cstUserIds').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cstUserIds').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****处理人员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstUserIds">${csTask.cstUserIds}</textarea>
		 		<span>
		 	
			 ${csTask.cstUserIds$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstUserIds}
	</s:if>
	
	${lz:set("注释","*****************SQL语句字段的输入框代码*****************")}
	${lz:set("注释","before$cstSql和after$cstSql变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstSql==true">
	${before$cstSql}
	<dl class="cstSql " major  ref="cstSql" style="width:98%;">
		<dt>SQL语句:</dt>
		<s:if test="#request.CTRL.e.cstSql==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csTask$cstSql!=null">${csTask$cstSql}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csTask.cstSql" id="cstSql"  value="${csTask.cstSql}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入数据任务的SQL语句</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****SQL语句字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cstSql">${csTask.cstSql}</textarea>
		 		<span>
		 	
			 ${csTask.cstSql$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstSql}
	</s:if>
	
	${lz:set("注释","*****************消息提醒字段的输入框代码*****************")}
	${lz:set("注释","before$cstTips和after$cstTips变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstTips==true">
	${before$cstTips}
	<dl class="cstTips " major  ref="cstTips" style="width:98%;">
		<dt>消息提醒:</dt>
		<s:if test="#request.CTRL.e.cstTips==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csTask$cstTips!=null">${csTask$cstTips}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="256" name="csTask.cstTips" id="cstTips"  value="${csTask.cstTips}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>消息提醒格式{sql字段}</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****消息提醒字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cstTips">${csTask.cstTips}</textarea>
		 		<span>
		 	
			 ${csTask.cstTips$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstTips}
	</s:if>
	
	${lz:set("注释","*****************处理地址字段的输入框代码*****************")}
	${lz:set("注释","before$cstUrl和after$cstUrl变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstUrl==true">
	${before$cstUrl}
	<dl class="cstUrl " minor  ref="cstUrl" style="width:98%;">
		<dt>处理地址:</dt>
		<s:if test="#request.CTRL.e.cstUrl==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csTask$cstUrl!=null">${csTask$cstUrl}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csTask.cstUrl" id="cstUrl"  value="${csTask.cstUrl}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入数据任务的处理地址</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****处理地址字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cstUrl">${csTask.cstUrl}</textarea>
		 		<span>
		 	
			 ${csTask.cstUrl$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstUrl}
	</s:if>
	
	${lz:set("注释","*****************编辑时间字段的输入框代码*****************")}
	${lz:set("注释","before$cstEditTime和after$cstEditTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstEditTime==true">
	${before$cstEditTime}
	<dl class="cstEditTime " major  ref="cstEditTime" >
		<dt>编辑时间:</dt>
		<s:if test="#request.CTRL.e.cstEditTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csTask$cstEditTime!=null">${csTask$cstEditTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csTask.cstEditTime" id="cstEditTime"  value="<s:date name="csTask.cstEditTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择数据任务的编辑时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编辑时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstEditTime">${csTask.cstEditTime}</textarea>
		 		<span>
		 	
			 ${csTask.cstEditTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstEditTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cstStatus和after$cstStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstStatus==true">
	${before$cstStatus}
	<dl class="cstStatus " major  ref="cstStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cstStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csTask$cstStatus!=null">${csTask$cstStatus}</s:if><s:else>
		 	<select class="narrow" id="cstStatus" name="csTask.cstStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csTask.cstStatus==1?"selected":""}>正常</option>
				<option value="0" ${csTask.cstStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择数据任务的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstStatus">${csTask.cstStatus}</textarea>
		 		<span>
		 	
			 ${csTask.cstStatus$}
	 	  
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
					<input type="hidden" value="${csTask.cstId}" name="csTask.cstId" id="cstId" />
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