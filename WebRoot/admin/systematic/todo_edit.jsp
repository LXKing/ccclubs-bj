<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/systematic/todo.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
${lz:set("canEdit",true)}
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/systematic/todo.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csTodo.cstId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cstId,cstTitle,cstAlarmTime,cstContent",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cstId,cstTitle,cstAlarmTime,cstContent",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cstId,cstTitle,cstAlarmTime,cstContent,cstStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cstId,cstTitle,cstAlarmTime,cstContent,cstStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"待办事项编辑":CTRL.title}</title>
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
	$("#todoForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cstId==true">
		${lz:set("haveEditable",true)}
		,"csTodo.cstId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstTitle==true">
		${lz:set("haveEditable",true)}
		,"csTodo.cstTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "待办事标题不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "待办事标题最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstAlarmTime==true">
		${lz:set("haveEditable",true)}
		,"csTodo.cstAlarmTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstUser==true">
		${lz:set("haveEditable",true)}
		,"csTodo.cstUser":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstContent==true">
		${lz:set("haveEditable",true)}
		,"csTodo.cstContent":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "待办事内容最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csTodo.cstUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstAddTime==true">
		${lz:set("haveEditable",true)}
		,"csTodo.cstAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cstStatus==true">
		${lz:set("haveEditable",true)}
		,"csTodo.cstStatus":function(el){
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
	<form class="form editform" ref="CsTodo" id="todoForm" name="todoForm" action="${empty CTRL.action?"todo_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写待办事项相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csTodoToken}" name="csTodoToken" id="csTodoToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csTodo.cstId))}		
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
		<s:if test="#request.csTodo$cstId!=null">${csTodo$cstId}</s:if><s:else>
			<input type="hidden" value="${csTodo.cstId}" name="csTodo.cstId" id="cstId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstId">${csTodo.cstId}</textarea>
		 		<span>
		 	
			 ${csTodo.cstId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstId}
	</s:if>
	
	${lz:set("注释","*****************待办事标题字段的输入框代码*****************")}
	${lz:set("注释","before$cstTitle和after$cstTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstTitle==true">
	${before$cstTitle}
	<dl class="cstTitle " major  ref="cstTitle" >
		<dt>待办事标题:</dt>
		<s:if test="#request.CTRL.e.cstTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csTodo$cstTitle!=null">${csTodo$cstTitle}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csTodo.cstTitle" id="cstTitle"  value="${csTodo.cstTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入待办事项的待办事标题</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****待办事标题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstTitle">${csTodo.cstTitle}</textarea>
		 		<span>
		 	
			 ${csTodo.cstTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstTitle}
	</s:if>
	
	${lz:set("注释","*****************设定提醒时间字段的输入框代码*****************")}
	${lz:set("注释","before$cstAlarmTime和after$cstAlarmTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstAlarmTime==true">
	${before$cstAlarmTime}
	<dl class="cstAlarmTime " major  ref="cstAlarmTime" >
		<dt>设定提醒时间:</dt>
		<s:if test="#request.CTRL.e.cstAlarmTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csTodo$cstAlarmTime!=null">${csTodo$cstAlarmTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csTodo.cstAlarmTime" id="cstAlarmTime"  value="<s:date name="csTodo.cstAlarmTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择待办事项的设定提醒时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****设定提醒时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstAlarmTime">${csTodo.cstAlarmTime}</textarea>
		 		<span>
		 	
			 ${csTodo.cstAlarmTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstAlarmTime}
	</s:if>
	
	${lz:set("注释","*****************所有人字段的输入框代码*****************")}
	${lz:set("注释","before$cstUser和after$cstUser变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstUser==true">
	${before$cstUser}
	<dl class="cstUser " major  ref="cstUser" >
		<dt>所&nbsp;&nbsp;有&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.cstUser==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csTodo$cstUser!=null">${csTodo$cstUser}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cstUser" name="csTodo.cstUser">
		 				<option selected value="${csTodo.cstUser}">
		 					${get:SrvUser(csTodo.cstUser).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cstUser').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cstUser').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所有人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstUser">${csTodo.cstUser}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csTodo.cstUser}',{ctrl:{editable:false,visible:true}})">
			 ${csTodo.cstUser$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstUser}
	</s:if>
	
	${lz:set("注释","*****************待办事内容字段的输入框代码*****************")}
	${lz:set("注释","before$cstContent和after$cstContent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstContent==true">
	${before$cstContent}
	<dl class="cstContent " major  ref="cstContent" style="width:98%;">
		<dt>待办事内容:</dt>
		<s:if test="#request.CTRL.e.cstContent==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csTodo$cstContent!=null">${csTodo$cstContent}</s:if><s:else>
		 	<textarea class="input wide"  id="cstContent" name="csTodo.cstContent" rows="5">${csTodo.cstContent}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入待办事项的待办事内容</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****待办事内容字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csTodo.cstContent$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstContent}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cstUpdateTime和after$cstUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstUpdateTime==true">
	${before$cstUpdateTime}
	<dl class="cstUpdateTime " major  ref="cstUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cstUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csTodo$cstUpdateTime!=null">${csTodo$cstUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csTodo.cstUpdateTime" id="cstUpdateTime"  value="<s:date name="csTodo.cstUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择待办事项的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstUpdateTime">${csTodo.cstUpdateTime}</textarea>
		 		<span>
		 	
			 ${csTodo.cstUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cstAddTime和after$cstAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cstAddTime==true">
	${before$cstAddTime}
	<dl class="cstAddTime " major  ref="cstAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cstAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csTodo$cstAddTime!=null">${csTodo$cstAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csTodo.cstAddTime" id="cstAddTime"  value="<s:date name="csTodo.cstAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择待办事项的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstAddTime">${csTodo.cstAddTime}</textarea>
		 		<span>
		 	
			 ${csTodo.cstAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cstAddTime}
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
		<s:if test="#request.csTodo$cstStatus!=null">${csTodo$cstStatus}</s:if><s:else>
		 	<select class="narrow" id="cstStatus" name="csTodo.cstStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csTodo.cstStatus==0?"selected":""}>待处理</option>
				<option value="1" ${csTodo.cstStatus==1?"selected":""}>提醒报警</option>
				<option value="2" ${csTodo.cstStatus==2?"selected":""}>已完成</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择待办事项的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cstStatus">${csTodo.cstStatus}</textarea>
		 		<span>
		 	
			 ${csTodo.cstStatus$}
	 	  
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
					<input type="hidden" value="${csTodo.cstId}" name="csTodo.cstId" id="cstId" />
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