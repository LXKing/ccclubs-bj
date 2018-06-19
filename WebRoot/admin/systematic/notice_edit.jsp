<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/systematic/notice.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
${lz:set("canEdit",true)}
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/systematic/notice.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csNotice.csnId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csnId,csnAlarmTime,csnRemark,csnStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csnId,csnContent,csnAlarmTime,csnUrl,csnRemark,csnStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"系统通知编辑":CTRL.title}</title>
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
	var csnUrl = $("#csnUrl").val();
	if($.trim(csnUrl)!=""){
		var a = $("<a></a>").html("点击访问操作链接>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>").attr("href",csnUrl);
		$("#csnUrl").next("span").html(a);
	}
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
	$("#noticeForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csnId==true">
		${lz:set("haveEditable",true)}
		,"csNotice.csnId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csnContent==true">
		${lz:set("haveEditable",true)}
		,"csNotice.csnContent":function(el){
			if(jQuery.trim(el.value)=="")
				return "通知内容不能为空";
			if(el.value.length>512 && el.value.indexOf("[*]")==-1)
				return "通知内容最大长度为512个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csnAlarmTime==true">
		${lz:set("haveEditable",true)}
		,"csNotice.csnAlarmTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csnUrl==true">
		${lz:set("haveEditable",true)}
		,"csNotice.csnUrl":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "处理地址最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csnFlag==true">
		${lz:set("haveEditable",true)}
		,"csNotice.csnFlag":function(el){
			if(el.value.length>64 && el.value.indexOf("[*]")==-1)
				return "标识最大长度为64个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csnSender==true">
		${lz:set("haveEditable",true)}
		,"csNotice.csnSender":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csnReceiver==true">
		${lz:set("haveEditable",true)}
		,"csNotice.csnReceiver":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csnRemark==true">
		${lz:set("haveEditable",true)}
		,"csNotice.csnRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "处理描述最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csnLog==true">
		${lz:set("haveEditable",true)}
		,"csNotice.csnLog":function(el){
			if(el.value.length>1024 && el.value.indexOf("[*]")==-1)
				return "处理日志最大长度为1024个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csnAddTime==true">
		${lz:set("haveEditable",true)}
		,"csNotice.csnAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csnDealTime==true">
		${lz:set("haveEditable",true)}
		,"csNotice.csnDealTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csnStatus==true">
		${lz:set("haveEditable",true)}
		,"csNotice.csnStatus":function(el){
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
	<form class="form editform" ref="CsNotice" id="noticeForm" name="noticeForm" action="${empty CTRL.action?"notice_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写系统通知相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csNoticeToken}" name="csNoticeToken" id="csNoticeToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csNotice.csnId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csnId和after$csnId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csnId==true">
	${before$csnId}
	<dl class="csnId ${CTRL.e.csnId?"hidden":""}" major  ref="csnId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csnId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csNotice$csnId!=null">${csNotice$csnId}</s:if><s:else>
			<input type="hidden" value="${csNotice.csnId}" name="csNotice.csnId" id="csnId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csnId">${csNotice.csnId}</textarea>
		 		<span>
		 	
			 ${csNotice.csnId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csnId}
	</s:if>
	
	${lz:set("注释","*****************通知内容字段的输入框代码*****************")}
	${lz:set("注释","before$csnContent和after$csnContent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csnContent==true">
	${before$csnContent}
	<dl class="csnContent " major  ref="csnContent" style="width:98%;">
		<dt>通知内容:</dt>
		<s:if test="#request.CTRL.e.csnContent==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csNotice$csnContent!=null">${csNotice$csnContent}</s:if><s:else>
		 	<textarea class="input wide"  id="csnContent" name="csNotice.csnContent" rows="5">${csNotice.csnContent}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入系统通知的通知内容</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****通知内容字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csNotice.csnContent$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csnContent}
	</s:if>
	
	${lz:set("注释","*****************提醒时间字段的输入框代码*****************")}
	${lz:set("注释","before$csnAlarmTime和after$csnAlarmTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csnAlarmTime==true">
	${before$csnAlarmTime}
	<dl class="csnAlarmTime " minor  ref="csnAlarmTime" >
		<dt>提醒时间:</dt>
		<s:if test="#request.CTRL.e.csnAlarmTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csNotice$csnAlarmTime!=null">${csNotice$csnAlarmTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csNotice.csnAlarmTime" id="csnAlarmTime"  value="<s:date name="csNotice.csnAlarmTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择系统通知的提醒时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****提醒时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csnAlarmTime">${csNotice.csnAlarmTime}</textarea>
		 		<span>
		 	
			 ${csNotice.csnAlarmTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csnAlarmTime}
	</s:if>
	
	${lz:set("注释","*****************处理地址字段的输入框代码*****************")}
	${lz:set("注释","before$csnUrl和after$csnUrl变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csnUrl==true">
	${before$csnUrl}
	<dl class="csnUrl " minor  ref="csnUrl" style="width:98%;">
		<dt>处理地址:</dt>
		<s:if test="#request.CTRL.e.csnUrl==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csNotice$csnUrl!=null">${csNotice$csnUrl}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csNotice.csnUrl" id="csnUrl"  value="${csNotice.csnUrl}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统通知的处理地址</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****处理地址字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csnUrl">${csNotice.csnUrl}</textarea>
		 		<span>
		 	
			 ${csNotice.csnUrl$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csnUrl}
	</s:if>
	
	${lz:set("注释","*****************标识字段的输入框代码*****************")}
	${lz:set("注释","before$csnFlag和after$csnFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csnFlag==true">
	${before$csnFlag}
	<dl class="csnFlag " minor  ref="csnFlag" style="width:98%;">
		<dt>标　　识:</dt>
		<s:if test="#request.CTRL.e.csnFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csNotice$csnFlag!=null">${csNotice$csnFlag}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="64" name="csNotice.csnFlag" id="csnFlag"  value="${csNotice.csnFlag}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统通知的标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csnFlag">${csNotice.csnFlag}</textarea>
		 		<span>
		 	
			 ${csNotice.csnFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csnFlag}
	</s:if>
	
	${lz:set("注释","*****************发送人字段的输入框代码*****************")}
	${lz:set("注释","before$csnSender和after$csnSender变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csnSender==true">
	${before$csnSender}
	<dl class="csnSender " major  ref="csnSender" >
		<dt>发&nbsp;&nbsp;送&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csnSender==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csNotice$csnSender!=null">${csNotice$csnSender}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csnSender" name="csNotice.csnSender">
		 				<option selected value="${csNotice.csnSender}">
		 					${get:SrvUser(csNotice.csnSender).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csnSender').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csnSender').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****发送人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csnSender">${csNotice.csnSender}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csNotice.csnSender}',{ctrl:{editable:false,visible:true}})">
			 ${csNotice.csnSender$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csnSender}
	</s:if>
	
	${lz:set("注释","*****************接收人字段的输入框代码*****************")}
	${lz:set("注释","before$csnReceiver和after$csnReceiver变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csnReceiver==true">
	${before$csnReceiver}
	<dl class="csnReceiver " major  ref="csnReceiver" >
		<dt>接&nbsp;&nbsp;收&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csnReceiver==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csNotice$csnReceiver!=null">${csNotice$csnReceiver}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csnReceiver" name="csNotice.csnReceiver">
		 				<option selected value="${csNotice.csnReceiver}">
		 					${get:SrvUser(csNotice.csnReceiver).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csnReceiver').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csnReceiver').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****接收人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csnReceiver">${csNotice.csnReceiver}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csNotice.csnReceiver}',{ctrl:{editable:false,visible:true}})">
			 ${csNotice.csnReceiver$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csnReceiver}
	</s:if>
	
	${lz:set("注释","*****************处理描述字段的输入框代码*****************")}
	${lz:set("注释","before$csnRemark和after$csnRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csnRemark==true">
	${before$csnRemark}
	<dl class="csnRemark " minor  ref="csnRemark" style="width:98%;">
		<dt>处理描述:</dt>
		<s:if test="#request.CTRL.e.csnRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csNotice$csnRemark!=null">${csNotice$csnRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csnRemark" name="csNotice.csnRemark" rows="5">${csNotice.csnRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统通知的处理描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****处理描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csNotice.csnRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csnRemark}
	</s:if>
	
	${lz:set("注释","*****************处理日志字段的输入框代码*****************")}
	${lz:set("注释","before$csnLog和after$csnLog变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csnLog==true">
	${before$csnLog}
	<dl class="csnLog " minor  ref="csnLog" style="width:98%;">
		<dt>处理日志:</dt>
		<s:if test="#request.CTRL.e.csnLog==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csNotice$csnLog!=null">${csNotice$csnLog}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="1024" name="csNotice.csnLog" id="csnLog"  value="${csNotice.csnLog}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统通知的处理日志</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****处理日志字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csnLog">${csNotice.csnLog}</textarea>
		 		<span>
		 	
			 ${csNotice.csnLog$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csnLog}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csnAddTime和after$csnAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csnAddTime==true">
	${before$csnAddTime}
	<dl class="csnAddTime " major  ref="csnAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csnAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csNotice$csnAddTime!=null">${csNotice$csnAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csNotice.csnAddTime" id="csnAddTime"  value="<s:date name="csNotice.csnAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统通知的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csnAddTime">${csNotice.csnAddTime}</textarea>
		 		<span>
		 	
			 ${csNotice.csnAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csnAddTime}
	</s:if>
	
	${lz:set("注释","*****************处理时间字段的输入框代码*****************")}
	${lz:set("注释","before$csnDealTime和after$csnDealTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csnDealTime==true">
	${before$csnDealTime}
	<dl class="csnDealTime " major  ref="csnDealTime" >
		<dt>处理时间:</dt>
		<s:if test="#request.CTRL.e.csnDealTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csNotice$csnDealTime!=null">${csNotice$csnDealTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csNotice.csnDealTime" id="csnDealTime"  value="<s:date name="csNotice.csnDealTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择系统通知的处理时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****处理时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csnDealTime">${csNotice.csnDealTime}</textarea>
		 		<span>
		 	
			 ${csNotice.csnDealTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csnDealTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csnStatus和after$csnStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csnStatus==true">
	${before$csnStatus}
	<dl class="csnStatus " major  ref="csnStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csnStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csNotice$csnStatus!=null">${csNotice$csnStatus}</s:if><s:else>
		 	<select class="narrow" id="csnStatus" name="csNotice.csnStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csNotice.csnStatus==0?"selected":""}>待处理</option>
				<option value="1" ${csNotice.csnStatus==1?"selected":""}>提醒报警</option>
				<option value="2" ${csNotice.csnStatus==2?"selected":""}>已处理</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统通知的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csnStatus">${csNotice.csnStatus}</textarea>
		 		<span>
		 	
			 ${csNotice.csnStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csnStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csNotice.csnId}" name="csNotice.csnId" id="csnId" />
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


<!-- LAZY3Q_JSP_BOTTOM -->
<script>
$(function(){
	var violats = $("dl span:contains('客服通知预警提醒')");
	violats.each(function(){
		var strTitle = $(this).text();
		var sid = strTitle.match(/违章(\d+)第\d次客服通知预警提醒/)[1];
		var a = $("<a href='javascript:void'></a>");
		a.click(function(){
			var url = "${basePath}admin/service/violat_edit.do";
			var params = {entrypoint:"${entrypoint}",id:sid,ctrl:{title:"客服通知"}};
			href(url,params); 
		});
		$(this).wrapInner(a);		
	});
	
	var cars = $("dl span:contains('险到期预警提醒')");
	cars.each(function(){
		var strTitle = $(this).text();
		var number = strTitle.match(/([a-zA-Z0-9]+)/)[1];
		var object = $.getObject("${basePath}admin/object/car_query.do",{
			cscNumber:number
		});
		var a = $("<a href='javascript:void'></a>");
		a.click(function(){
			var url = "${basePath}admin/object/car_details.do";
			var params = {entrypoint:"${entrypoint}",id:object[0].value,ctrl:{title:"车辆详情",editable:false,visible:true}};
			href(url,params); 
		});
		$(this).wrapInner(a);		
	});
	
	var outlets = $("dl span:contains('合约到期预警提醒')").add("dl span:contains('缴费预警提醒')");
	outlets.each(function(){
		var strTitle = $(this).text();
		var id = strTitle.match(/(\d+)/)[1];		
		var a = $("<a href='javascript:void'></a>");
		a.click(function(){
			var url = "${basePath}admin/object/outlets_details.do";
			var params = {entrypoint:"${entrypoint}",id:id,ctrl:{title:"网点详情",editable:false,visible:true}};
			href(url,params); 
		});
		$(this).wrapInner(a);		
	});
});
</script>
<!-- LAZY3Q_JSP_BOTTOM -->


</body>
</html>