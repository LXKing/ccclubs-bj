<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/systematic/eventheed.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/systematic/eventheed.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csEventHeed.csehId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csehId,csehEventType,csehHeedType",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csehId,csehEventType,csehHeedType",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csehId,csehEventType,csehHeedUser,csehHeedType,csehStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csehId,csehEventType,csehHeedUser,csehHeedType,csehStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"事件关注编辑":CTRL.title}</title>
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
	$("#eventheedForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csehId==true">
		${lz:set("haveEditable",true)}
		,"csEventHeed.csehId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csehEventType==true">
		${lz:set("haveEditable",true)}
		,"csEventHeed.csehEventType":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csehHeedUser==true">
		${lz:set("haveEditable",true)}
		,"csEventHeed.csehHeedUser":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csehHeedType==true">
		${lz:set("haveEditable",true)}
		,"csEventHeed.csehHeedType":function(el){
			if(!false && $("input[name='csEventHeed.csehHeedType']:checked").length==0)
				return "处理方式至少选择一个";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csehUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csEventHeed.csehUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csehAddTime==true">
		${lz:set("haveEditable",true)}
		,"csEventHeed.csehAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csehStatus==true">
		${lz:set("haveEditable",true)}
		,"csEventHeed.csehStatus":function(el){
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
	<form class="form editform" ref="CsEventHeed" id="eventheedForm" name="eventheedForm" action="${empty CTRL.action?"eventheed_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写事件关注相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csEventHeedToken}" name="csEventHeedToken" id="csEventHeedToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csEventHeed.csehId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csehId和after$csehId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csehId==true">
	${before$csehId}
	<dl class="csehId ${CTRL.e.csehId?"hidden":""}" major  ref="csehId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csehId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csEventHeed$csehId!=null">${csEventHeed$csehId}</s:if><s:else>
			<input type="hidden" value="${csEventHeed.csehId}" name="csEventHeed.csehId" id="csehId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csehId">${csEventHeed.csehId}</textarea>
		 		<span>
		 	
			 ${csEventHeed.csehId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csehId}
	</s:if>
	
	${lz:set("注释","*****************事件类型字段的输入框代码*****************")}
	${lz:set("注释","before$csehEventType和after$csehEventType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csehEventType==true">
	${before$csehEventType}
	<dl class="csehEventType " major  ref="csehEventType" >
		<dt>事件类型:</dt>
		<s:if test="#request.CTRL.e.csehEventType==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csEventHeed$csehEventType!=null">${csEventHeed$csehEventType}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/systematic/eventtype_query.do?size=-1" id="csehEventType" name="csEventHeed.csehEventType">
		 				<option selected value="${csEventHeed.csehEventType}">
		 					${get:CsEventType(csEventHeed.csehEventType).csetName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csehEventType').val())==''){return;};window.href('${basePath}${proname}/systematic/eventtype_details.do?key='+$('#csehEventType').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****事件类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csehEventType">${csEventHeed.csehEventType}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/systematic/eventtype_details.do?key=${csEventHeed.csehEventType}',{ctrl:{editable:false,visible:true}})">
			 ${csEventHeed.csehEventType$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csehEventType}
	</s:if>
	
	${lz:set("注释","*****************关注人字段的输入框代码*****************")}
	${lz:set("注释","before$csehHeedUser和after$csehHeedUser变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csehHeedUser==true">
	${before$csehHeedUser}
	<dl class="csehHeedUser " major  ref="csehHeedUser" >
		<dt>关&nbsp;&nbsp;注&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csehHeedUser==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csEventHeed$csehHeedUser!=null">${csEventHeed$csehHeedUser}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csehHeedUser" name="csEventHeed.csehHeedUser">
		 				<option selected value="${csEventHeed.csehHeedUser}">
		 					${get:SrvUser(csEventHeed.csehHeedUser).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csehHeedUser').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csehHeedUser').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关注人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csehHeedUser">${csEventHeed.csehHeedUser}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csEventHeed.csehHeedUser}',{ctrl:{editable:false,visible:true}})">
			 ${csEventHeed.csehHeedUser$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csehHeedUser}
	</s:if>
	
	${lz:set("注释","*****************处理方式字段的输入框代码*****************")}
	${lz:set("注释","before$csehHeedType和after$csehHeedType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csehHeedType==true">
	${before$csehHeedType}
	<dl class="csehHeedType " major  ref="csehHeedType" style="width:98%;">
		<dt>处理方式:</dt>
		<s:if test="#request.CTRL.e.csehHeedType==true">
		${lz:set("haveEditable",true)}
		<dd input="checkbox">
		<s:if test="#request.csEventHeed$csehHeedType!=null">${csEventHeed$csehHeedType}</s:if><s:else>
		 	<div class='wide input'>	
		 	&nbsp; 	
		 	<input type="hidden" value="#0#" name="csEventHeed.csehHeedType" id="csehHeedType"/>
			<label><input type="checkbox" ${lz:idin(csEventHeed.csehHeedType,0) ? "checked='checked'" : ""} value="#0#" name="csEventHeed.csehHeedType" id="csehHeedType"/>消息提示&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:idin(csEventHeed.csehHeedType,1) ? "checked='checked'" : ""} value="#1#" name="csEventHeed.csehHeedType" id="csehHeedType"/>系统通知&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:idin(csEventHeed.csehHeedType,3) ? "checked='checked'" : ""} value="#3#" name="csEventHeed.csehHeedType" id="csehHeedType"/>邮件通知&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:idin(csEventHeed.csehHeedType,2) ? "checked='checked'" : ""} value="#2#" name="csEventHeed.csehHeedType" id="csehHeedType"/>短信通知&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:idin(csEventHeed.csehHeedType,4) ? "checked='checked'" : ""} value="#4#" name="csEventHeed.csehHeedType" id="csehHeedType"/>微信通知&nbsp;&nbsp;&nbsp;</label>
		 </div>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择事件关注的处理方式</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****处理方式字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csehHeedType">${csEventHeed.csehHeedType}</textarea>
		 		<span>
		 	
			 ${csEventHeed.csehHeedType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csehHeedType}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csehUpdateTime和after$csehUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csehUpdateTime==true">
	${before$csehUpdateTime}
	<dl class="csehUpdateTime " major  ref="csehUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csehUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csEventHeed$csehUpdateTime!=null">${csEventHeed$csehUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csEventHeed.csehUpdateTime" id="csehUpdateTime"  value="<s:date name="csEventHeed.csehUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择事件关注的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csehUpdateTime">${csEventHeed.csehUpdateTime}</textarea>
		 		<span>
		 	
			 ${csEventHeed.csehUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csehUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csehAddTime和after$csehAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csehAddTime==true">
	${before$csehAddTime}
	<dl class="csehAddTime " major  ref="csehAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csehAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csEventHeed$csehAddTime!=null">${csEventHeed$csehAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csEventHeed.csehAddTime" id="csehAddTime"  value="<s:date name="csEventHeed.csehAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择事件关注的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csehAddTime">${csEventHeed.csehAddTime}</textarea>
		 		<span>
		 	
			 ${csEventHeed.csehAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csehAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csehStatus和after$csehStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csehStatus==true">
	${before$csehStatus}
	<dl class="csehStatus " major  ref="csehStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csehStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csEventHeed$csehStatus!=null">${csEventHeed$csehStatus}</s:if><s:else>
		 	<select class="narrow" id="csehStatus" name="csEventHeed.csehStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csEventHeed.csehStatus==1?"selected":""}>正常</option>
				<option value="0" ${csEventHeed.csehStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择事件关注的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csehStatus">${csEventHeed.csehStatus}</textarea>
		 		<span>
		 	
			 ${csEventHeed.csehStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csehStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csEventHeed.csehId}" name="csEventHeed.csehId" id="csehId" />
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