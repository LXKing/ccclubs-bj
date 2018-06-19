<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/user/message.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/user/message.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csMessage.csmId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csmId,csmTitle,csmContent,csmRelate,csmObject,csmAttach,csmReceiver,csmSender,csmRcvStatus,csmSndStatus,csmStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csmId,csmTitle,csmContent,csmRelate,csmObject,csmAttach,csmReceiver,csmSender,csmRcvStatus,csmSndStatus,csmStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csmId,csmTitle,csmContent,csmRelate,csmObject,csmAttach,csmReceiver,csmSender,csmRcvStatus,csmSndStatus,csmStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csmId,csmTitle,csmContent,csmRelate,csmObject,csmAttach,csmReceiver,csmSender,csmRcvTime,csmSndTime,csmRcvStatus,csmSndStatus,csmStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"会员消息编辑":CTRL.title}</title>
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
		if($("#csmContent").size()>0){
			 //构造编辑页csMessage.csmContent为html编辑控件
			 KE.init({
				id : 'csmContent',
				width : '625px',
				height : '300px' , 
				imageUploadJson : '${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}?app=message_edit&type=img${ lz:config("upload.url")==null ? "" : "&remote="}${ lz:config("upload.url")==null ? "" : basePath}${ lz:config("upload.url")==null ? "" : "callback.jsp"}'
			 });
			 KE.create("csmContent");
		 }
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#messageForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csmId==true">
		${lz:set("haveEditable",true)}
		,"csMessage.csmId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmTitle==true">
		${lz:set("haveEditable",true)}
		,"csMessage.csmTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "标题不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "标题最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmContent==true">
		${lz:set("haveEditable",true)}
		,"csMessage.csmContent":function(el){
			if($("#csmContent").get(0))
			 	KE.sync("csmContent");		 
			if(jQuery.trim(el.value)=="")
				return "消息内容不能为空";
			if(el.value.length>512 && el.value.indexOf("[*]")==-1)
				return "消息内容最大长度为512个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmRelate==true">
		${lz:set("haveEditable",true)}
		,"csMessage.csmRelate":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmObject==true">
		${lz:set("haveEditable",true)}
		,"csMessage.csmObject":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "关联类型最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmAttach==true">
		${lz:set("haveEditable",true)}
		,"csMessage.csmAttach":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmReceiver==true">
		${lz:set("haveEditable",true)}
		,"csMessage.csmReceiver":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmSender==true">
		${lz:set("haveEditable",true)}
		,"csMessage.csmSender":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmRcvTime==true">
		${lz:set("haveEditable",true)}
		,"csMessage.csmRcvTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "接收时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmSndTime==true">
		${lz:set("haveEditable",true)}
		,"csMessage.csmSndTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmRcvStatus==true">
		${lz:set("haveEditable",true)}
		,"csMessage.csmRcvStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择接收状态";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmSndStatus==true">
		${lz:set("haveEditable",true)}
		,"csMessage.csmSndStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择发送状态";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmStatus==true">
		${lz:set("haveEditable",true)}
		,"csMessage.csmStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择系统状态";
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
	<form class="form editform" ref="CsMessage" id="messageForm" name="messageForm" action="${empty CTRL.action?"message_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写会员消息相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csMessageToken}" name="csMessageToken" id="csMessageToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csMessage.csmId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csmId和after$csmId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmId==true">
	${before$csmId}
	<dl class="csmId ${CTRL.e.csmId?"hidden":""}" major  ref="csmId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csmId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csMessage$csmId!=null">${csMessage$csmId}</s:if><s:else>
			<input type="hidden" value="${csMessage.csmId}" name="csMessage.csmId" id="csmId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmId">${csMessage.csmId}</textarea>
		 		<span>
		 	
			 ${csMessage.csmId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmId}
	</s:if>
	
	${lz:set("注释","*****************标题字段的输入框代码*****************")}
	${lz:set("注释","before$csmTitle和after$csmTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmTitle==true">
	${before$csmTitle}
	<dl class="csmTitle " major  ref="csmTitle" >
		<dt>标　　题:</dt>
		<s:if test="#request.CTRL.e.csmTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMessage$csmTitle!=null">${csMessage$csmTitle}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMessage.csmTitle" id="csmTitle"  value="${csMessage.csmTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入会员消息的标题</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****标题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmTitle">${csMessage.csmTitle}</textarea>
		 		<span>
		 	
			 ${csMessage.csmTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmTitle}
	</s:if>
	
	${lz:set("注释","*****************消息内容字段的输入框代码*****************")}
	${lz:set("注释","before$csmContent和after$csmContent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmContent==true">
	${before$csmContent}
	<dl class="csmContent " major  ref="csmContent" style="width:98%;">
		<dt>消息内容:</dt>
		<s:if test="#request.CTRL.e.csmContent==true">
		${lz:set("haveEditable",true)}
		<dd input="html">
		<s:if test="#request.csMessage$csmContent!=null">${csMessage$csmContent}</s:if><s:else>
		    <textarea class="input wide"  id="csmContent" name="csMessage.csmContent" style="height:300px;visibility:hidden;">${csMessage.csmContent}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入会员消息的消息内容</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****消息内容字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csMessage.csmContent$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmContent}
	</s:if>
	
	${lz:set("注释","*****************关联对象字段的输入框代码*****************")}
	${lz:set("注释","before$csmRelate和after$csmRelate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmRelate==true">
	${before$csmRelate}
	<dl class="csmRelate " minor  ref="csmRelate" >
		<dt>关联对象:</dt>
		<s:if test="#request.CTRL.e.csmRelate==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csMessage$csmRelate!=null">${csMessage$csmRelate}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csMessage.csmRelate" id="csmRelate"  value="${csMessage.csmRelate}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员消息的关联对象</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联对象字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmRelate">${csMessage.csmRelate}</textarea>
		 		<span>
		 	
			 ${csMessage.csmRelate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmRelate}
	</s:if>
	
	${lz:set("注释","*****************关联类型字段的输入框代码*****************")}
	${lz:set("注释","before$csmObject和after$csmObject变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmObject==true">
	${before$csmObject}
	<dl class="csmObject " minor  ref="csmObject" >
		<dt>关联类型:</dt>
		<s:if test="#request.CTRL.e.csmObject==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMessage$csmObject!=null">${csMessage$csmObject}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMessage.csmObject" id="csmObject"  value="${csMessage.csmObject}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员消息的关联类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmObject">${csMessage.csmObject}</textarea>
		 		<span>
		 	
			 ${csMessage.csmObject$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmObject}
	</s:if>
	
	${lz:set("注释","*****************附件字段的输入框代码*****************")}
	${lz:set("注释","before$csmAttach和after$csmAttach变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmAttach==true">
	${before$csmAttach}
	<dl class="csmAttach " minor  ref="csmAttach" style="width:98%;">
		<dt>附　　件:</dt>
		<s:if test="#request.CTRL.e.csmAttach==true">
		${lz:set("haveEditable",true)}
		<dd input="file">
		<s:if test="#request.csMessage$csmAttach!=null">${csMessage$csmAttach}</s:if><s:else>
		 	<input type="text" class="input" maxlength="128" size="32" name="csMessage.csmAttach" id="csmAttach"  value="${csMessage.csmAttach}"/>
			<button type="button" onclick="$.upload({type:'file',callback:function(url){if(url)$('#csmAttach').val(url)}})" class="button">上传文件</button>
			<button type="button" onclick="$('#csmAttach').val('')" class="button">删除文件</button>
			<button type="button" onclick="window.location='${ lz:config("lazy3q.download")==null ? basePath : ""}${ lz:config("lazy3q.download")==null ? "download" :  lz:config("lazy3q.download")}?path='+$('#csmAttach').val()" class="button">下载文件</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传会员消息的附件</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****附件字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csmAttach">${csMessage.csmAttach}</textarea>
		 		<span>
		 	
			 ${csMessage.csmAttach$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmAttach}
	</s:if>
	
	${lz:set("注释","*****************接收人字段的输入框代码*****************")}
	${lz:set("注释","before$csmReceiver和after$csmReceiver变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmReceiver==true">
	${before$csmReceiver}
	<dl class="csmReceiver " major  ref="csmReceiver" >
		<dt>接&nbsp;&nbsp;收&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csmReceiver==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csMessage$csmReceiver!=null">${csMessage$csmReceiver}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}admin/user/member_inquire.do?value={param}" type="text" id="csmReceiver" name="csMessage.csmReceiver" text="${get:CsMember(csMessage.csmReceiver).csmName}" value="${csMessage.csmReceiver}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmReceiver').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csmReceiver').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****接收人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmReceiver">${csMessage.csmReceiver}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csMessage.csmReceiver}',{ctrl:{editable:false,visible:true}})">
			 ${csMessage.csmReceiver$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmReceiver}
	</s:if>
	
	${lz:set("注释","*****************发送人字段的输入框代码*****************")}
	${lz:set("注释","before$csmSender和after$csmSender变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmSender==true">
	${before$csmSender}
	<dl class="csmSender " major  ref="csmSender" >
		<dt>发&nbsp;&nbsp;送&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csmSender==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csMessage$csmSender!=null">${csMessage$csmSender}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}admin/user/member_inquire.do?value={param}" type="text" id="csmSender" name="csMessage.csmSender" text="${get:CsMember(csMessage.csmSender).csmName}" value="${csMessage.csmSender}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmSender').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csmSender').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****发送人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmSender">${csMessage.csmSender}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csMessage.csmSender}',{ctrl:{editable:false,visible:true}})">
			 ${csMessage.csmSender$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmSender}
	</s:if>
	
	${lz:set("注释","*****************接收时间字段的输入框代码*****************")}
	${lz:set("注释","before$csmRcvTime和after$csmRcvTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmRcvTime==true">
	${before$csmRcvTime}
	<dl class="csmRcvTime " major  ref="csmRcvTime" >
		<dt>接收时间:</dt>
		<s:if test="#request.CTRL.e.csmRcvTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csMessage$csmRcvTime!=null">${csMessage$csmRcvTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csMessage.csmRcvTime" id="csmRcvTime"  value="<s:date name="csMessage.csmRcvTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员消息的接收时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****接收时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmRcvTime">${csMessage.csmRcvTime}</textarea>
		 		<span>
		 	
			 ${csMessage.csmRcvTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmRcvTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csmSndTime和after$csmSndTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmSndTime==true">
	${before$csmSndTime}
	<dl class="csmSndTime " major  ref="csmSndTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csmSndTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csMessage$csmSndTime!=null">${csMessage$csmSndTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csMessage.csmSndTime" id="csmSndTime"  value="<s:date name="csMessage.csmSndTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员消息的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmSndTime">${csMessage.csmSndTime}</textarea>
		 		<span>
		 	
			 ${csMessage.csmSndTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmSndTime}
	</s:if>
	
	${lz:set("注释","*****************接收状态字段的输入框代码*****************")}
	${lz:set("注释","before$csmRcvStatus和after$csmRcvStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmRcvStatus==true">
	${before$csmRcvStatus}
	<dl class="csmRcvStatus " major  ref="csmRcvStatus" >
		<dt>接收状态:</dt>
		<s:if test="#request.CTRL.e.csmRcvStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMessage$csmRcvStatus!=null">${csMessage$csmRcvStatus}</s:if><s:else>
		 	<select class="narrow" id="csmRcvStatus" name="csMessage.csmRcvStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csMessage.csmRcvStatus==0?"selected":""}>未读</option>
				<option value="1" ${csMessage.csmRcvStatus==1?"selected":""}>已读</option>
				<option value="2" ${csMessage.csmRcvStatus==2?"selected":""}>已删</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员消息的接收状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****接收状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmRcvStatus">${csMessage.csmRcvStatus}</textarea>
		 		<span>
		 	
			 ${csMessage.csmRcvStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmRcvStatus}
	</s:if>
	
	${lz:set("注释","*****************发送状态字段的输入框代码*****************")}
	${lz:set("注释","before$csmSndStatus和after$csmSndStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmSndStatus==true">
	${before$csmSndStatus}
	<dl class="csmSndStatus " major  ref="csmSndStatus" >
		<dt>发送状态:</dt>
		<s:if test="#request.CTRL.e.csmSndStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMessage$csmSndStatus!=null">${csMessage$csmSndStatus}</s:if><s:else>
		 	<select class="narrow" id="csmSndStatus" name="csMessage.csmSndStatus">
		 		<option value="">请选择</option>
		 		 <option value="">无法自动构造</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员消息的发送状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****发送状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmSndStatus">${csMessage.csmSndStatus}</textarea>
		 		<span>
		 	
			 ${csMessage.csmSndStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmSndStatus}
	</s:if>
	
	${lz:set("注释","*****************系统状态字段的输入框代码*****************")}
	${lz:set("注释","before$csmStatus和after$csmStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmStatus==true">
	${before$csmStatus}
	<dl class="csmStatus " major  ref="csmStatus" >
		<dt>系统状态:</dt>
		<s:if test="#request.CTRL.e.csmStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMessage$csmStatus!=null">${csMessage$csmStatus}</s:if><s:else>
		 	<select class="narrow" id="csmStatus" name="csMessage.csmStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csMessage.csmStatus==1?"selected":""}>正常</option>
				<option value="0" ${csMessage.csmStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员消息的系统状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****系统状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmStatus">${csMessage.csmStatus}</textarea>
		 		<span>
		 	
			 ${csMessage.csmStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csMessage.csmId}" name="csMessage.csmId" id="csmId" />
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