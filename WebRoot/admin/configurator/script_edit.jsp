<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/configurator/script.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/configurator/script.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty srvActionScript.ssId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"ssId,ssName,ssModelClass,ssEvent,ssCode,ssStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"ssId,ssName,ssModelClass,ssEvent,ssCode,ssStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"ssId,ssName,ssModelClass,ssEvent,ssCode,ssStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"ssId,ssName,ssModelClass,ssEvent,ssCode,ssStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"后台事务脚本编辑":CTRL.title}</title>
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
	$("#scriptForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.ssId==true">
		${lz:set("haveEditable",true)}
		,"srvActionScript.ssId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.ssName==true">
		${lz:set("haveEditable",true)}
		,"srvActionScript.ssName":function(el){
			if(jQuery.trim(el.value)=="")
				return "脚本名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "脚本名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.ssModelClass==true">
		${lz:set("haveEditable",true)}
		,"srvActionScript.ssModelClass":function(el){
			if(jQuery.trim(el.value)=="")
				return "类名全称不能为空";
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "类名全称最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.ssEvent==true">
		${lz:set("haveEditable",true)}
		,"srvActionScript.ssEvent":function(el){
			if(jQuery.trim(el.value)=="")
				return "事件名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "事件名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.ssCode==true">
		${lz:set("haveEditable",true)}
		,"srvActionScript.ssCode":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "脚本代码最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.ssUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"srvActionScript.ssUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.ssAddTime==true">
		${lz:set("haveEditable",true)}
		,"srvActionScript.ssAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.ssStatus==true">
		${lz:set("haveEditable",true)}
		,"srvActionScript.ssStatus":function(el){
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
	<form class="form editform" ref="SrvActionScript" id="scriptForm" name="scriptForm" action="${empty CTRL.action?"script_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写后台事务脚本相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${srvActionScriptToken}" name="srvActionScriptToken" id="srvActionScriptToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty srvActionScript.ssId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$ssId和after$ssId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.ssId==true">
	${before$ssId}
	<dl class="ssId ${CTRL.e.ssId?"hidden":""}" major  ref="ssId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.ssId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.srvActionScript$ssId!=null">${srvActionScript$ssId}</s:if><s:else>
			<input type="hidden" value="${srvActionScript.ssId}" name="srvActionScript.ssId" id="ssId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="ssId">${srvActionScript.ssId}</textarea>
		 		<span>
		 	
			 ${srvActionScript.ssId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$ssId}
	</s:if>
	
	${lz:set("注释","*****************脚本名称字段的输入框代码*****************")}
	${lz:set("注释","before$ssName和after$ssName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.ssName==true">
	${before$ssName}
	<dl class="ssName " major  ref="ssName" >
		<dt>脚本名称:</dt>
		<s:if test="#request.CTRL.e.ssName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvActionScript$ssName!=null">${srvActionScript$ssName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="srvActionScript.ssName" id="ssName"  value="${srvActionScript.ssName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入后台事务脚本的脚本名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****脚本名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="ssName">${srvActionScript.ssName}</textarea>
		 		<span>
		 	
			 ${srvActionScript.ssName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$ssName}
	</s:if>
	
	${lz:set("注释","*****************类名全称字段的输入框代码*****************")}
	${lz:set("注释","before$ssModelClass和after$ssModelClass变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.ssModelClass==true">
	${before$ssModelClass}
	<dl class="ssModelClass " major  ref="ssModelClass" style="width:98%;">
		<dt>类名全称:</dt>
		<s:if test="#request.CTRL.e.ssModelClass==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvActionScript$ssModelClass!=null">${srvActionScript$ssModelClass}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="srvActionScript.ssModelClass" id="ssModelClass"  value="${srvActionScript.ssModelClass}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入后台事务脚本的类名全称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****类名全称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="ssModelClass">${srvActionScript.ssModelClass}</textarea>
		 		<span>
		 	
			 ${srvActionScript.ssModelClass$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$ssModelClass}
	</s:if>
	
	${lz:set("注释","*****************事件名称字段的输入框代码*****************")}
	${lz:set("注释","before$ssEvent和after$ssEvent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.ssEvent==true">
	${before$ssEvent}
	<dl class="ssEvent " major  ref="ssEvent" >
		<dt>事件名称:</dt>
		<s:if test="#request.CTRL.e.ssEvent==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvActionScript$ssEvent!=null">${srvActionScript$ssEvent}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="srvActionScript.ssEvent" id="ssEvent"  value="${srvActionScript.ssEvent}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入后台事务脚本的事件名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****事件名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="ssEvent">${srvActionScript.ssEvent}</textarea>
		 		<span>
		 	
			 ${srvActionScript.ssEvent$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$ssEvent}
	</s:if>
	
	${lz:set("注释","*****************脚本代码字段的输入框代码*****************")}
	${lz:set("注释","before$ssCode和after$ssCode变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.ssCode==true">
	${before$ssCode}
	<dl class="ssCode " minor  ref="ssCode" style="width:98%;">
		<dt>脚本代码:</dt>
		<s:if test="#request.CTRL.e.ssCode==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.srvActionScript$ssCode!=null">${srvActionScript$ssCode}</s:if><s:else>
		 	<textarea class="input wide"  id="ssCode" name="srvActionScript.ssCode" rows="5">${srvActionScript.ssCode}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>脚本代码为java代码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****脚本代码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${srvActionScript.ssCode$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$ssCode}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$ssUpdateTime和after$ssUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.ssUpdateTime==true">
	${before$ssUpdateTime}
	<dl class="ssUpdateTime " major  ref="ssUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.ssUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.srvActionScript$ssUpdateTime!=null">${srvActionScript$ssUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="srvActionScript.ssUpdateTime" id="ssUpdateTime"  value="<s:date name="srvActionScript.ssUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择后台事务脚本的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="ssUpdateTime">${srvActionScript.ssUpdateTime}</textarea>
		 		<span>
		 	
			 ${srvActionScript.ssUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$ssUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$ssAddTime和after$ssAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.ssAddTime==true">
	${before$ssAddTime}
	<dl class="ssAddTime " major  ref="ssAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.ssAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.srvActionScript$ssAddTime!=null">${srvActionScript$ssAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="srvActionScript.ssAddTime" id="ssAddTime"  value="<s:date name="srvActionScript.ssAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择后台事务脚本的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="ssAddTime">${srvActionScript.ssAddTime}</textarea>
		 		<span>
		 	
			 ${srvActionScript.ssAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$ssAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$ssStatus和after$ssStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.ssStatus==true">
	${before$ssStatus}
	<dl class="ssStatus " major  ref="ssStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.ssStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.srvActionScript$ssStatus!=null">${srvActionScript$ssStatus}</s:if><s:else>
		 	<select class="narrow" id="ssStatus" name="srvActionScript.ssStatus">
		 		<option value="">请选择</option>
				<option value="1" ${srvActionScript.ssStatus==1?"selected":""}>正常</option>
				<option value="0" ${srvActionScript.ssStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择后台事务脚本的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="ssStatus">${srvActionScript.ssStatus}</textarea>
		 		<span>
		 	
			 ${srvActionScript.ssStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$ssStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${srvActionScript.ssId}" name="srvActionScript.ssId" id="ssId" />
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