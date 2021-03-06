<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/systematic/smsmt.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
${lz:set("canEdit",true)}
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/systematic/smsmt.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csSmsMt.cssmId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cssmId,cssmHost,cssmType,cssmMobile,cssmContent",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cssmId,cssmHost,cssmType,cssmMobile,cssmContent",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cssmId,cssmHost,cssmType,cssmMobile,cssmContent,cssmRemark",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cssmId,cssmHost,cssmType,cssmMobile,cssmContent,cssmEditor,cssmChannel,cssmUpdateTime,cssmAddTime,cssmRemark,cssmStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"短信发送编辑":CTRL.title}</title>
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



<!--LAZY3Q_JSP_BODY-->
<script>
$(function(){
	var dl=$("<dl><dt></dt><dd></dd></dl>").insertAfter("dl[ref=cssmMobile]");
	var button = $("<button class='button' type='button'>选择模板短信</button>").appendTo(dl.find("dd"));
	button.click(function(){
		href("${basePath}admin/configurator/template_select.do",{});
	});
});
</script>
<!--LAZY3Q_JSP_BODY-->



<script>
$(function(){	
	showTips("${tips.value}");
});
window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}";


$(function(){
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#smsmtForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cssmId==true">
		${lz:set("haveEditable",true)}
		,"csSmsMt.cssmId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssmHost==true">
		${lz:set("haveEditable",true)}
		,"csSmsMt.cssmHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssmType==true">
		${lz:set("haveEditable",true)}
		,"csSmsMt.cssmType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择短信类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssmMobile==true">
		${lz:set("haveEditable",true)}
		,"csSmsMt.cssmMobile":function(el){
			if(jQuery.trim(el.value)=="")
				return "接收号码不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "接收号码最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssmContent==true">
		${lz:set("haveEditable",true)}
		,"csSmsMt.cssmContent":function(el){
			if(jQuery.trim(el.value)=="")
				return "短信内容不能为空";
			if(el.value.length>1024 && el.value.indexOf("[*]")==-1)
				return "短信内容最大长度为1024个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssmEditor==true">
		${lz:set("haveEditable",true)}
		,"csSmsMt.cssmEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssmChannel==true">
		${lz:set("haveEditable",true)}
		,"csSmsMt.cssmChannel":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssmUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csSmsMt.cssmUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssmAddTime==true">
		${lz:set("haveEditable",true)}
		,"csSmsMt.cssmAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssmRemark==true">
		${lz:set("haveEditable",true)}
		,"csSmsMt.cssmRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssmStatus==true">
		${lz:set("haveEditable",true)}
		,"csSmsMt.cssmStatus":function(el){
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
	<form class="form editform" ref="CsSmsMt" id="smsmtForm" name="smsmtForm" action="${empty CTRL.action?"smsmt_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写短信发送相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csSmsMtToken}" name="csSmsMtToken" id="csSmsMtToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csSmsMt.cssmId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cssmId和after$cssmId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssmId==true">
	${before$cssmId}
	<dl class="cssmId ${CTRL.e.cssmId?"hidden":""}" major  ref="cssmId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cssmId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csSmsMt$cssmId!=null">${csSmsMt$cssmId}</s:if><s:else>
			<input type="hidden" value="${csSmsMt.cssmId}" name="csSmsMt.cssmId" id="cssmId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssmId">${csSmsMt.cssmId}</textarea>
		 		<span>
		 	
			 ${csSmsMt.cssmId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssmId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cssmHost和after$cssmHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssmHost==true">
	${before$cssmHost}
	<dl class="cssmHost " major  ref="cssmHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cssmHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csSmsMt$cssmHost!=null">${csSmsMt$cssmHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cssmHost" name="csSmsMt.cssmHost">
		 				<option selected value="${csSmsMt.cssmHost}">
		 					${get:SrvHost(csSmsMt.cssmHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssmHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cssmHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssmHost">${csSmsMt.cssmHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csSmsMt.cssmHost}',{ctrl:{editable:false,visible:true}})">
			 ${csSmsMt.cssmHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssmHost}
	</s:if>
	
	${lz:set("注释","*****************短信类型字段的输入框代码*****************")}
	${lz:set("注释","before$cssmType和after$cssmType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssmType==true">
	${before$cssmType}
	<dl class="cssmType " major  ref="cssmType" >
		<dt>短信类型:</dt>
		<s:if test="#request.CTRL.e.cssmType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csSmsMt$cssmType!=null">${csSmsMt$cssmType}</s:if><s:else>
		 	<select class="narrow" id="cssmType" name="csSmsMt.cssmType">
		 		<option value="">请选择</option>
				<option value="1" ${csSmsMt.cssmType==1?"selected":""}>代码类短信</option>
				<option value="2" ${csSmsMt.cssmType==2?"selected":""}>通知类短信</option>
				<option value="3" ${csSmsMt.cssmType==3?"selected":""}>提示类短信</option>
				<option value="4" ${csSmsMt.cssmType==4?"selected":""}>广告类短信</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em><strong style='color:blue;'>《《 慎重 》》选择“代码类短信”！！</strong></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****短信类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssmType">${csSmsMt.cssmType}</textarea>
		 		<span>
		 	
			 ${csSmsMt.cssmType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssmType}
	</s:if>
	
	${lz:set("注释","*****************接收号码字段的输入框代码*****************")}
	${lz:set("注释","before$cssmMobile和after$cssmMobile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssmMobile==true">
	${before$cssmMobile}
	<dl class="cssmMobile " major  ref="cssmMobile" >
		<dt>接收号码:</dt>
		<s:if test="#request.CTRL.e.cssmMobile==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csSmsMt$cssmMobile!=null">${csSmsMt$cssmMobile}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csSmsMt.cssmMobile" id="cssmMobile"  value="${csSmsMt.cssmMobile}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入短信发送的接收号码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****接收号码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssmMobile">${csSmsMt.cssmMobile}</textarea>
		 		<span>
		 	
			 ${csSmsMt.cssmMobile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssmMobile}
	</s:if>
	
	${lz:set("注释","*****************短信内容字段的输入框代码*****************")}
	${lz:set("注释","before$cssmContent和after$cssmContent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssmContent==true">
	${before$cssmContent}
	<dl class="cssmContent " major  ref="cssmContent" style="width:98%;">
		<dt>短信内容:</dt>
		<s:if test="#request.CTRL.e.cssmContent==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csSmsMt$cssmContent!=null">${csSmsMt$cssmContent}</s:if><s:else>
		 	<textarea class="input wide"  id="cssmContent" name="csSmsMt.cssmContent" rows="5">${csSmsMt.cssmContent}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入短信发送的短信内容</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****短信内容字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csSmsMt.cssmContent$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssmContent}
	</s:if>
	
	${lz:set("注释","*****************操作人字段的输入框代码*****************")}
	${lz:set("注释","before$cssmEditor和after$cssmEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssmEditor==true">
	${before$cssmEditor}
	<dl class="cssmEditor " major  ref="cssmEditor" >
		<dt>操&nbsp;&nbsp;作&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.cssmEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csSmsMt$cssmEditor!=null">${csSmsMt$cssmEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cssmEditor" name="csSmsMt.cssmEditor">
		 				<option selected value="${csSmsMt.cssmEditor}">
		 					${get:SrvUser(csSmsMt.cssmEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssmEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cssmEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****操作人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssmEditor">${csSmsMt.cssmEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csSmsMt.cssmEditor}',{ctrl:{editable:false,visible:true}})">
			 ${csSmsMt.cssmEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssmEditor}
	</s:if>
	
	${lz:set("注释","*****************发送通道字段的输入框代码*****************")}
	${lz:set("注释","before$cssmChannel和after$cssmChannel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssmChannel==true">
	${before$cssmChannel}
	<dl class="cssmChannel " major  ref="cssmChannel" >
		<dt>发送通道:</dt>
		<s:if test="#request.CTRL.e.cssmChannel==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csSmsMt$cssmChannel!=null">${csSmsMt$cssmChannel}</s:if><s:else>
		 	<select class="narrow" id="cssmChannel" name="csSmsMt.cssmChannel">
		 		<option value="">请选择</option>
				    ${lz:set("items", lz:query$("ccclubs_system","select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)" ,'SMS_CHANNEL') )}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.spId}" ${csSmsMt.cssmChannel==item.spId?"selected":""}>${item.spName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssmChannel').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?key='+$('#cssmChannel').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择短信发送的发送通道</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****发送通道字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssmChannel">${csSmsMt.cssmChannel}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${csSmsMt.cssmChannel}',{ctrl:{editable:false,visible:true}})">
			 ${csSmsMt.cssmChannel$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssmChannel}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cssmUpdateTime和after$cssmUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssmUpdateTime==true">
	${before$cssmUpdateTime}
	<dl class="cssmUpdateTime " major  ref="cssmUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cssmUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csSmsMt$cssmUpdateTime!=null">${csSmsMt$cssmUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csSmsMt.cssmUpdateTime" id="cssmUpdateTime"  value="<s:date name="csSmsMt.cssmUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择短信发送的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssmUpdateTime">${csSmsMt.cssmUpdateTime}</textarea>
		 		<span>
		 	
			 ${csSmsMt.cssmUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssmUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cssmAddTime和after$cssmAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssmAddTime==true">
	${before$cssmAddTime}
	<dl class="cssmAddTime " major  ref="cssmAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cssmAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csSmsMt$cssmAddTime!=null">${csSmsMt$cssmAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csSmsMt.cssmAddTime" id="cssmAddTime"  value="<s:date name="csSmsMt.cssmAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择短信发送的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssmAddTime">${csSmsMt.cssmAddTime}</textarea>
		 		<span>
		 	
			 ${csSmsMt.cssmAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssmAddTime}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$cssmRemark和after$cssmRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssmRemark==true">
	${before$cssmRemark}
	<dl class="cssmRemark " minor  ref="cssmRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.cssmRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csSmsMt$cssmRemark!=null">${csSmsMt$cssmRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="cssmRemark" name="csSmsMt.cssmRemark" rows="5">${csSmsMt.cssmRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入短信发送的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csSmsMt.cssmRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssmRemark}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cssmStatus和after$cssmStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssmStatus==true">
	${before$cssmStatus}
	<dl class="cssmStatus " major  ref="cssmStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cssmStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csSmsMt$cssmStatus!=null">${csSmsMt$cssmStatus}</s:if><s:else>
		 	<select class="narrow" id="cssmStatus" name="csSmsMt.cssmStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csSmsMt.cssmStatus==1?"selected":""}>待发送</option>
				<option value="2" ${csSmsMt.cssmStatus==2?"selected":""}>已发送</option>
				<option value="3" ${csSmsMt.cssmStatus==3?"selected":""}>发送失败</option>
				<option value="0" ${csSmsMt.cssmStatus==0?"selected":""}>停止发送</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择短信发送的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssmStatus">${csSmsMt.cssmStatus}</textarea>
		 		<span>
		 	
			 ${csSmsMt.cssmStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssmStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csSmsMt.cssmId}" name="csSmsMt.cssmId" id="cssmId" />
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