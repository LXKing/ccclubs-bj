<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/operate/marketlog.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/operate/marketlog.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csMarketLog.csmlId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csmlId,csmlHost,csmlTitle,csmlPlan,csmlProfile,csmlStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csmlId,csmlHost,csmlTitle,csmlPlan,csmlProfile,csmlStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csmlId,csmlHost,csmlTitle,csmlPlan,csmlProfile,csmlStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csmlId,csmlHost,csmlTitle,csmlPlan,csmlProfile,csmlStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"营销跟踪编辑":CTRL.title}</title>
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
		if($("#csmlProfile").size()>0){
			 //构造编辑页csMarketLog.csmlProfile为html编辑控件
			 KE.init({
				id : 'csmlProfile',
				width : '625px',
				height : '300px' , 
				imageUploadJson : '${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}?app=marketlog_edit&type=img${ lz:config("upload.url")==null ? "" : "&remote="}${ lz:config("upload.url")==null ? "" : basePath}${ lz:config("upload.url")==null ? "" : "callback.jsp"}'
			 });
			 KE.create("csmlProfile");
		 }
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#marketlogForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csmlId==true">
		${lz:set("haveEditable",true)}
		,"csMarketLog.csmlId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmlHost==true">
		${lz:set("haveEditable",true)}
		,"csMarketLog.csmlHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmlTitle==true">
		${lz:set("haveEditable",true)}
		,"csMarketLog.csmlTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "标题不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "标题最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmlPlan==true">
		${lz:set("haveEditable",true)}
		,"csMarketLog.csmlPlan":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmlProfile==true">
		${lz:set("haveEditable",true)}
		,"csMarketLog.csmlProfile":function(el){
			if($("#csmlProfile").get(0))
			 	KE.sync("csmlProfile");		 
			if(jQuery.trim(el.value)=="")
				return "描述不能为空";
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "描述最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmlUser==true">
		${lz:set("haveEditable",true)}
		,"csMarketLog.csmlUser":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmlUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csMarketLog.csmlUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmlAddTime==true">
		${lz:set("haveEditable",true)}
		,"csMarketLog.csmlAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmlStatus==true">
		${lz:set("haveEditable",true)}
		,"csMarketLog.csmlStatus":function(el){
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
	<form class="form editform" ref="CsMarketLog" id="marketlogForm" name="marketlogForm" action="${empty CTRL.action?"marketlog_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写营销跟踪相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csMarketLogToken}" name="csMarketLogToken" id="csMarketLogToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csMarketLog.csmlId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csmlId和after$csmlId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmlId==true">
	${before$csmlId}
	<dl class="csmlId ${CTRL.e.csmlId?"hidden":""}" major  ref="csmlId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csmlId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csMarketLog$csmlId!=null">${csMarketLog$csmlId}</s:if><s:else>
			<input type="hidden" value="${csMarketLog.csmlId}" name="csMarketLog.csmlId" id="csmlId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmlId">${csMarketLog.csmlId}</textarea>
		 		<span>
		 	
			 ${csMarketLog.csmlId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmlId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csmlHost和after$csmlHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmlHost==true">
	${before$csmlHost}
	<dl class="csmlHost " major  ref="csmlHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csmlHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csMarketLog$csmlHost!=null">${csMarketLog$csmlHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csmlHost" name="csMarketLog.csmlHost">
		 				<option selected value="${csMarketLog.csmlHost}">
		 					${get:SrvHost(csMarketLog.csmlHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmlHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csmlHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmlHost">${csMarketLog.csmlHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csMarketLog.csmlHost}',{ctrl:{editable:false,visible:true}})">
			 ${csMarketLog.csmlHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmlHost}
	</s:if>
	
	${lz:set("注释","*****************标题字段的输入框代码*****************")}
	${lz:set("注释","before$csmlTitle和after$csmlTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmlTitle==true">
	${before$csmlTitle}
	<dl class="csmlTitle " major  ref="csmlTitle" >
		<dt>标　　题:</dt>
		<s:if test="#request.CTRL.e.csmlTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMarketLog$csmlTitle!=null">${csMarketLog$csmlTitle}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMarketLog.csmlTitle" id="csmlTitle"  value="${csMarketLog.csmlTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入营销跟踪的标题</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****标题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmlTitle">${csMarketLog.csmlTitle}</textarea>
		 		<span>
		 	
			 ${csMarketLog.csmlTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmlTitle}
	</s:if>
	
	${lz:set("注释","*****************所属方案字段的输入框代码*****************")}
	${lz:set("注释","before$csmlPlan和after$csmlPlan变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmlPlan==true">
	${before$csmlPlan}
	<dl class="csmlPlan " major  ref="csmlPlan" >
		<dt>所属方案:</dt>
		<s:if test="#request.CTRL.e.csmlPlan==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csMarketLog$csmlPlan!=null">${csMarketLog$csmlPlan}</s:if><s:else>
		 			<input title="请输入营销方案方案主题进行查询" class="combox narrow" action="${basePath}${proname}/operate/marketplan_query.do?value={param}&csmpHost={csmlHost}" type="text" id="csmlPlan" name="csMarketLog.csmlPlan" text="${get:CsMarketPlan(csMarketLog.csmlPlan).csmpName}" value="${csMarketLog.csmlPlan}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmlPlan').val())==''){return;};window.href('${basePath}${proname}/operate/marketplan_details.do?key='+$('#csmlPlan').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属方案字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmlPlan">${csMarketLog.csmlPlan}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/operate/marketplan_details.do?key=${csMarketLog.csmlPlan}',{ctrl:{editable:false,visible:true}})">
			 ${csMarketLog.csmlPlan$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmlPlan}
	</s:if>
	
	${lz:set("注释","*****************描述字段的输入框代码*****************")}
	${lz:set("注释","before$csmlProfile和after$csmlProfile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmlProfile==true">
	${before$csmlProfile}
	<dl class="csmlProfile " major  ref="csmlProfile" style="width:98%;">
		<dt>描　　述:</dt>
		<s:if test="#request.CTRL.e.csmlProfile==true">
		${lz:set("haveEditable",true)}
		<dd input="html">
		<s:if test="#request.csMarketLog$csmlProfile!=null">${csMarketLog$csmlProfile}</s:if><s:else>
		    <textarea class="input wide"  id="csmlProfile" name="csMarketLog.csmlProfile" style="height:300px;visibility:hidden;">${csMarketLog.csmlProfile}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入营销跟踪的描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csMarketLog.csmlProfile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmlProfile}
	</s:if>
	
	${lz:set("注释","*****************执行人字段的输入框代码*****************")}
	${lz:set("注释","before$csmlUser和after$csmlUser变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmlUser==true">
	${before$csmlUser}
	<dl class="csmlUser " major  ref="csmlUser" >
		<dt>执&nbsp;&nbsp;行&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csmlUser==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csMarketLog$csmlUser!=null">${csMarketLog$csmlUser}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csmlUser" name="csMarketLog.csmlUser">
		 				<option selected value="${csMarketLog.csmlUser}">
		 					${get:SrvUser(csMarketLog.csmlUser).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmlUser').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csmlUser').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****执行人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmlUser">${csMarketLog.csmlUser}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csMarketLog.csmlUser}',{ctrl:{editable:false,visible:true}})">
			 ${csMarketLog.csmlUser$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmlUser}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csmlUpdateTime和after$csmlUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmlUpdateTime==true">
	${before$csmlUpdateTime}
	<dl class="csmlUpdateTime " major  ref="csmlUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csmlUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csMarketLog$csmlUpdateTime!=null">${csMarketLog$csmlUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csMarketLog.csmlUpdateTime" id="csmlUpdateTime"  value="<s:date name="csMarketLog.csmlUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择营销跟踪的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmlUpdateTime">${csMarketLog.csmlUpdateTime}</textarea>
		 		<span>
		 	
			 ${csMarketLog.csmlUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmlUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csmlAddTime和after$csmlAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmlAddTime==true">
	${before$csmlAddTime}
	<dl class="csmlAddTime " major  ref="csmlAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csmlAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csMarketLog$csmlAddTime!=null">${csMarketLog$csmlAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csMarketLog.csmlAddTime" id="csmlAddTime"  value="<s:date name="csMarketLog.csmlAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择营销跟踪的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmlAddTime">${csMarketLog.csmlAddTime}</textarea>
		 		<span>
		 	
			 ${csMarketLog.csmlAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmlAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csmlStatus和after$csmlStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmlStatus==true">
	${before$csmlStatus}
	<dl class="csmlStatus " major  ref="csmlStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csmlStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMarketLog$csmlStatus!=null">${csMarketLog$csmlStatus}</s:if><s:else>
		 	<select class="narrow" id="csmlStatus" name="csMarketLog.csmlStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csMarketLog.csmlStatus==0?"selected":""}>默认</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择营销跟踪的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmlStatus">${csMarketLog.csmlStatus}</textarea>
		 		<span>
		 	
			 ${csMarketLog.csmlStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmlStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csMarketLog.csmlId}" name="csMarketLog.csmlId" id="csmlId" />
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