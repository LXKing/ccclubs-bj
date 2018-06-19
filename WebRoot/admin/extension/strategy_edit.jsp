<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/extension/strategy.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/extension/strategy.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csStrategy.cssId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cssId,cssTitle,cssContent,cssMind,cssStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cssId,cssTitle,cssContent,cssMind,cssStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cssId,cssTitle,cssContent,cssMind,cssStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cssId,cssTitle,cssContent,cssMind,cssAdder,cssEditor,cssStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"品牌战略编辑":CTRL.title}</title>
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
		if($("#cssContent").size()>0){
			 //构造编辑页csStrategy.cssContent为html编辑控件
			 KE.init({
				id : 'cssContent',
				width : '625px',
				height : '300px' , 
				imageUploadJson : '${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}?app=strategy_edit&type=img${ lz:config("upload.url")==null ? "" : "&remote="}${ lz:config("upload.url")==null ? "" : basePath}${ lz:config("upload.url")==null ? "" : "callback.jsp"}'
			 });
			 KE.create("cssContent");
		 }
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#strategyForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cssId==true">
		${lz:set("haveEditable",true)}
		,"csStrategy.cssId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssTitle==true">
		${lz:set("haveEditable",true)}
		,"csStrategy.cssTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "战略标题不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "战略标题最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssContent==true">
		${lz:set("haveEditable",true)}
		,"csStrategy.cssContent":function(el){
			if($("#cssContent").get(0))
			 	KE.sync("cssContent");		 
			if(jQuery.trim(el.value)=="")
				return "战略详情不能为空";
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "战略详情最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssMind==true">
		${lz:set("haveEditable",true)}
		,"csStrategy.cssMind":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "构想图最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssAdder==true">
		${lz:set("haveEditable",true)}
		,"csStrategy.cssAdder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssEditor==true">
		${lz:set("haveEditable",true)}
		,"csStrategy.cssEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csStrategy.cssUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssAddTime==true">
		${lz:set("haveEditable",true)}
		,"csStrategy.cssAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssStatus==true">
		${lz:set("haveEditable",true)}
		,"csStrategy.cssStatus":function(el){
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
	<form class="form editform" ref="CsStrategy" id="strategyForm" name="strategyForm" action="${empty CTRL.action?"strategy_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写品牌战略相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csStrategyToken}" name="csStrategyToken" id="csStrategyToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csStrategy.cssId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cssId和after$cssId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssId==true">
	${before$cssId}
	<dl class="cssId ${CTRL.e.cssId?"hidden":""}" major  ref="cssId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cssId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csStrategy$cssId!=null">${csStrategy$cssId}</s:if><s:else>
			<input type="hidden" value="${csStrategy.cssId}" name="csStrategy.cssId" id="cssId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssId">${csStrategy.cssId}</textarea>
		 		<span>
		 	
			 ${csStrategy.cssId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssId}
	</s:if>
	
	${lz:set("注释","*****************战略标题字段的输入框代码*****************")}
	${lz:set("注释","before$cssTitle和after$cssTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssTitle==true">
	${before$cssTitle}
	<dl class="cssTitle " major  ref="cssTitle" >
		<dt>战略标题:</dt>
		<s:if test="#request.CTRL.e.cssTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csStrategy$cssTitle!=null">${csStrategy$cssTitle}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csStrategy.cssTitle" id="cssTitle"  value="${csStrategy.cssTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入品牌战略的战略标题</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****战略标题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssTitle">${csStrategy.cssTitle}</textarea>
		 		<span>
		 	
			 ${csStrategy.cssTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssTitle}
	</s:if>
	
	${lz:set("注释","*****************战略详情字段的输入框代码*****************")}
	${lz:set("注释","before$cssContent和after$cssContent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssContent==true">
	${before$cssContent}
	<dl class="cssContent " major  ref="cssContent" style="width:98%;">
		<dt>战略详情:</dt>
		<s:if test="#request.CTRL.e.cssContent==true">
		${lz:set("haveEditable",true)}
		<dd input="html">
		<s:if test="#request.csStrategy$cssContent!=null">${csStrategy$cssContent}</s:if><s:else>
		    <textarea class="input wide"  id="cssContent" name="csStrategy.cssContent" style="height:300px;visibility:hidden;">${csStrategy.cssContent}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入品牌战略的战略详情</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****战略详情字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csStrategy.cssContent$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssContent}
	</s:if>
	
	${lz:set("注释","*****************构想图字段的输入框代码*****************")}
	${lz:set("注释","before$cssMind和after$cssMind变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssMind==true">
	${before$cssMind}
	<dl class="cssMind " minor  ref="cssMind" style="width:98%;">
		<dt>构&nbsp;&nbsp;想&nbsp;&nbsp;图:</dt>
		<s:if test="#request.CTRL.e.cssMind==true">
		${lz:set("haveEditable",true)}
		<dd input="mind">
		<s:if test="#request.csStrategy$cssMind!=null">${csStrategy$cssMind}</s:if><s:else>
		    <textarea class="input wide mind" mind="" id="cssMind" name="csStrategy.cssMind">${csStrategy.cssMind}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****构想图字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cssMind">${csStrategy.cssMind}</textarea>
		 		<span>
		 	<textarea class="hidden mind" mind="" id="cssMind">${csStrategy.cssMind}</textarea>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssMind}
	</s:if>
	
	${lz:set("注释","*****************添加人字段的输入框代码*****************")}
	${lz:set("注释","before$cssAdder和after$cssAdder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssAdder==true">
	${before$cssAdder}
	<dl class="cssAdder " major  ref="cssAdder" >
		<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.cssAdder==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csStrategy$cssAdder!=null">${csStrategy$cssAdder}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cssAdder" name="csStrategy.cssAdder">
		 				<option selected value="${csStrategy.cssAdder}">
		 					${get:SrvUser(csStrategy.cssAdder).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssAdder').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cssAdder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssAdder">${csStrategy.cssAdder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csStrategy.cssAdder}',{ctrl:{editable:false,visible:true}})">
			 ${csStrategy.cssAdder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssAdder}
	</s:if>
	
	${lz:set("注释","*****************修改人字段的输入框代码*****************")}
	${lz:set("注释","before$cssEditor和after$cssEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssEditor==true">
	${before$cssEditor}
	<dl class="cssEditor " major  ref="cssEditor" >
		<dt>修&nbsp;&nbsp;改&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.cssEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csStrategy$cssEditor!=null">${csStrategy$cssEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cssEditor" name="csStrategy.cssEditor">
		 				<option selected value="${csStrategy.cssEditor}">
		 					${get:SrvUser(csStrategy.cssEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cssEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssEditor">${csStrategy.cssEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csStrategy.cssEditor}',{ctrl:{editable:false,visible:true}})">
			 ${csStrategy.cssEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssEditor}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cssUpdateTime和after$cssUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssUpdateTime==true">
	${before$cssUpdateTime}
	<dl class="cssUpdateTime " major  ref="cssUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cssUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csStrategy$cssUpdateTime!=null">${csStrategy$cssUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csStrategy.cssUpdateTime" id="cssUpdateTime"  value="<s:date name="csStrategy.cssUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择品牌战略的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssUpdateTime">${csStrategy.cssUpdateTime}</textarea>
		 		<span>
		 	
			 ${csStrategy.cssUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cssAddTime和after$cssAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssAddTime==true">
	${before$cssAddTime}
	<dl class="cssAddTime " major  ref="cssAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cssAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csStrategy$cssAddTime!=null">${csStrategy$cssAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csStrategy.cssAddTime" id="cssAddTime"  value="<s:date name="csStrategy.cssAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择品牌战略的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssAddTime">${csStrategy.cssAddTime}</textarea>
		 		<span>
		 	
			 ${csStrategy.cssAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cssStatus和after$cssStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssStatus==true">
	${before$cssStatus}
	<dl class="cssStatus " major  ref="cssStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cssStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csStrategy$cssStatus!=null">${csStrategy$cssStatus}</s:if><s:else>
		 	<select class="narrow" id="cssStatus" name="csStrategy.cssStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csStrategy.cssStatus==1?"selected":""}>正常</option>
				<option value="0" ${csStrategy.cssStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择品牌战略的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssStatus">${csStrategy.cssStatus}</textarea>
		 		<span>
		 	
			 ${csStrategy.cssStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csStrategy.cssId}" name="csStrategy.cssId" id="cssId" />
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