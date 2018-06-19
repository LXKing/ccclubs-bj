<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/configurator/recordsubject.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/configurator/recordsubject.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csRecordSubject.csrsId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csrsIsOrder",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csrsIsOrder",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csrsIsArtif,csrsIsOrder,csrsIsInvoice",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csrsId,csrsName,csrsIsIncome,csrsIsOperIn,csrsIsOperOut,csrsIsUserIn,csrsIsUserOut,csrsIsArtif,csrsIsOrder,csrsIsInvoice",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"消费科目编辑":CTRL.title}</title>
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
	$("#recordsubjectForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csrsId==true">
		${lz:set("haveEditable",true)}
		,"csRecordSubject.csrsId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrsName==true">
		${lz:set("haveEditable",true)}
		,"csRecordSubject.csrsName":function(el){
			if(jQuery.trim(el.value)=="")
				return "科目名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "科目名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrsIsIncome==true">
		${lz:set("haveEditable",true)}
		,"csRecordSubject.csrsIsIncome":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择系统收入项";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrsIsOperIn==true">
		${lz:set("haveEditable",true)}
		,"csRecordSubject.csrsIsOperIn":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择商家收入项";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrsIsOperOut==true">
		${lz:set("haveEditable",true)}
		,"csRecordSubject.csrsIsOperOut":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择商家支出项";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrsIsUserIn==true">
		${lz:set("haveEditable",true)}
		,"csRecordSubject.csrsIsUserIn":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择会员收入项";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrsIsUserOut==true">
		${lz:set("haveEditable",true)}
		,"csRecordSubject.csrsIsUserOut":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择会员支出项";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrsIsArtif==true">
		${lz:set("haveEditable",true)}
		,"csRecordSubject.csrsIsArtif":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择人工操作项";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrsIsOrder==true">
		${lz:set("haveEditable",true)}
		,"csRecordSubject.csrsIsOrder":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择订单相关项";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrsIsInvoice==true">
		${lz:set("haveEditable",true)}
		,"csRecordSubject.csrsIsInvoice":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择是否开票项";
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
	<form class="form editform" ref="CsRecordSubject" id="recordsubjectForm" name="recordsubjectForm" action="${empty CTRL.action?"recordsubject_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写消费科目相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csRecordSubjectToken}" name="csRecordSubjectToken" id="csRecordSubjectToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csRecordSubject.csrsId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csrsId和after$csrsId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrsId==true">
	${before$csrsId}
	<dl class="csrsId ${CTRL.e.csrsId?"hidden":""}" major  ref="csrsId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csrsId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csRecordSubject$csrsId!=null">${csRecordSubject$csrsId}</s:if><s:else>
			<input type="hidden" value="${csRecordSubject.csrsId}" name="csRecordSubject.csrsId" id="csrsId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrsId">${csRecordSubject.csrsId}</textarea>
		 		<span>
		 	
			 ${csRecordSubject.csrsId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrsId}
	</s:if>
	
	${lz:set("注释","*****************科目名称字段的输入框代码*****************")}
	${lz:set("注释","before$csrsName和after$csrsName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrsName==true">
	${before$csrsName}
	<dl class="csrsName " major  ref="csrsName" >
		<dt>科目名称:</dt>
		<s:if test="#request.CTRL.e.csrsName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csRecordSubject$csrsName!=null">${csRecordSubject$csrsName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csRecordSubject.csrsName" id="csrsName"  value="${csRecordSubject.csrsName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入消费科目的科目名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****科目名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrsName">${csRecordSubject.csrsName}</textarea>
		 		<span>
		 	
			 ${csRecordSubject.csrsName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrsName}
	</s:if>
	
	${lz:set("注释","*****************系统收入项字段的输入框代码*****************")}
	${lz:set("注释","before$csrsIsIncome和after$csrsIsIncome变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrsIsIncome==true">
	${before$csrsIsIncome}
	<dl class="csrsIsIncome " major  ref="csrsIsIncome" >
		<dt>系统收入项:</dt>
		<s:if test="#request.CTRL.e.csrsIsIncome==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRecordSubject$csrsIsIncome!=null">${csRecordSubject$csrsIsIncome}</s:if><s:else>
		 	<select class="narrow" id="csrsIsIncome" name="csRecordSubject.csrsIsIncome">
		 		<option value="">请选择</option>
				<option value="true" ${csRecordSubject.csrsIsIncome==true?"selected":""}>是</option>
				<option value="false" ${csRecordSubject.csrsIsIncome==false?"selected":""}>否</option>		 		
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择消费科目的系统收入项</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****系统收入项字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrsIsIncome">${csRecordSubject.csrsIsIncome}</textarea>
		 		<span>
		 	
			 ${csRecordSubject.csrsIsIncome$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrsIsIncome}
	</s:if>
	
	${lz:set("注释","*****************商家收入项字段的输入框代码*****************")}
	${lz:set("注释","before$csrsIsOperIn和after$csrsIsOperIn变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrsIsOperIn==true">
	${before$csrsIsOperIn}
	<dl class="csrsIsOperIn " major  ref="csrsIsOperIn" >
		<dt>商家收入项:</dt>
		<s:if test="#request.CTRL.e.csrsIsOperIn==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRecordSubject$csrsIsOperIn!=null">${csRecordSubject$csrsIsOperIn}</s:if><s:else>
		 	<select class="narrow" id="csrsIsOperIn" name="csRecordSubject.csrsIsOperIn">
		 		<option value="">请选择</option>
				<option value="true" ${csRecordSubject.csrsIsOperIn==true?"selected":""}>是</option>
				<option value="false" ${csRecordSubject.csrsIsOperIn==false?"selected":""}>否</option>		 		
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择消费科目的商家收入项</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****商家收入项字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrsIsOperIn">${csRecordSubject.csrsIsOperIn}</textarea>
		 		<span>
		 	
			 ${csRecordSubject.csrsIsOperIn$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrsIsOperIn}
	</s:if>
	
	${lz:set("注释","*****************商家支出项字段的输入框代码*****************")}
	${lz:set("注释","before$csrsIsOperOut和after$csrsIsOperOut变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrsIsOperOut==true">
	${before$csrsIsOperOut}
	<dl class="csrsIsOperOut " major  ref="csrsIsOperOut" >
		<dt>商家支出项:</dt>
		<s:if test="#request.CTRL.e.csrsIsOperOut==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRecordSubject$csrsIsOperOut!=null">${csRecordSubject$csrsIsOperOut}</s:if><s:else>
		 	<select class="narrow" id="csrsIsOperOut" name="csRecordSubject.csrsIsOperOut">
		 		<option value="">请选择</option>
				<option value="true" ${csRecordSubject.csrsIsOperOut==true?"selected":""}>是</option>
				<option value="false" ${csRecordSubject.csrsIsOperOut==false?"selected":""}>否</option>		 		
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择消费科目的商家支出项</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****商家支出项字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrsIsOperOut">${csRecordSubject.csrsIsOperOut}</textarea>
		 		<span>
		 	
			 ${csRecordSubject.csrsIsOperOut$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrsIsOperOut}
	</s:if>
	
	${lz:set("注释","*****************会员收入项字段的输入框代码*****************")}
	${lz:set("注释","before$csrsIsUserIn和after$csrsIsUserIn变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrsIsUserIn==true">
	${before$csrsIsUserIn}
	<dl class="csrsIsUserIn " major  ref="csrsIsUserIn" >
		<dt>会员收入项:</dt>
		<s:if test="#request.CTRL.e.csrsIsUserIn==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRecordSubject$csrsIsUserIn!=null">${csRecordSubject$csrsIsUserIn}</s:if><s:else>
		 	<select class="narrow" id="csrsIsUserIn" name="csRecordSubject.csrsIsUserIn">
		 		<option value="">请选择</option>
				<option value="true" ${csRecordSubject.csrsIsUserIn==true?"selected":""}>是</option>
				<option value="false" ${csRecordSubject.csrsIsUserIn==false?"selected":""}>否</option>		 		
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择消费科目的会员收入项</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****会员收入项字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrsIsUserIn">${csRecordSubject.csrsIsUserIn}</textarea>
		 		<span>
		 	
			 ${csRecordSubject.csrsIsUserIn$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrsIsUserIn}
	</s:if>
	
	${lz:set("注释","*****************会员支出项字段的输入框代码*****************")}
	${lz:set("注释","before$csrsIsUserOut和after$csrsIsUserOut变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrsIsUserOut==true">
	${before$csrsIsUserOut}
	<dl class="csrsIsUserOut " major  ref="csrsIsUserOut" >
		<dt>会员支出项:</dt>
		<s:if test="#request.CTRL.e.csrsIsUserOut==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRecordSubject$csrsIsUserOut!=null">${csRecordSubject$csrsIsUserOut}</s:if><s:else>
		 	<select class="narrow" id="csrsIsUserOut" name="csRecordSubject.csrsIsUserOut">
		 		<option value="">请选择</option>
				<option value="true" ${csRecordSubject.csrsIsUserOut==true?"selected":""}>是</option>
				<option value="false" ${csRecordSubject.csrsIsUserOut==false?"selected":""}>否</option>		 		
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择消费科目的会员支出项</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****会员支出项字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrsIsUserOut">${csRecordSubject.csrsIsUserOut}</textarea>
		 		<span>
		 	
			 ${csRecordSubject.csrsIsUserOut$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrsIsUserOut}
	</s:if>
	
	${lz:set("注释","*****************人工操作项字段的输入框代码*****************")}
	${lz:set("注释","before$csrsIsArtif和after$csrsIsArtif变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrsIsArtif==true">
	${before$csrsIsArtif}
	<dl class="csrsIsArtif " major  ref="csrsIsArtif" >
		<dt>人工操作项:</dt>
		<s:if test="#request.CTRL.e.csrsIsArtif==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRecordSubject$csrsIsArtif!=null">${csRecordSubject$csrsIsArtif}</s:if><s:else>
		 	<select class="narrow" id="csrsIsArtif" name="csRecordSubject.csrsIsArtif">
		 		<option value="">请选择</option>
				<option value="true" ${csRecordSubject.csrsIsArtif==true?"selected":""}>是</option>
				<option value="false" ${csRecordSubject.csrsIsArtif==false?"selected":""}>否</option>		 		
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择消费科目的人工操作项</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****人工操作项字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrsIsArtif">${csRecordSubject.csrsIsArtif}</textarea>
		 		<span>
		 	
			 ${csRecordSubject.csrsIsArtif$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrsIsArtif}
	</s:if>
	
	${lz:set("注释","*****************订单相关项字段的输入框代码*****************")}
	${lz:set("注释","before$csrsIsOrder和after$csrsIsOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrsIsOrder==true">
	${before$csrsIsOrder}
	<dl class="csrsIsOrder " major  ref="csrsIsOrder" >
		<dt>订单相关项:</dt>
		<s:if test="#request.CTRL.e.csrsIsOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRecordSubject$csrsIsOrder!=null">${csRecordSubject$csrsIsOrder}</s:if><s:else>
		 	<select class="narrow" id="csrsIsOrder" name="csRecordSubject.csrsIsOrder">
		 		<option value="">请选择</option>
				<option value="true" ${csRecordSubject.csrsIsOrder==true?"selected":""}>是</option>
				<option value="false" ${csRecordSubject.csrsIsOrder==false?"selected":""}>否</option>		 		
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择消费科目的订单相关项</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单相关项字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrsIsOrder">${csRecordSubject.csrsIsOrder}</textarea>
		 		<span>
		 	
			 ${csRecordSubject.csrsIsOrder$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrsIsOrder}
	</s:if>
	
	${lz:set("注释","*****************是否开票项字段的输入框代码*****************")}
	${lz:set("注释","before$csrsIsInvoice和after$csrsIsInvoice变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrsIsInvoice==true">
	${before$csrsIsInvoice}
	<dl class="csrsIsInvoice " major  ref="csrsIsInvoice" >
		<dt>是否开票项:</dt>
		<s:if test="#request.CTRL.e.csrsIsInvoice==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRecordSubject$csrsIsInvoice!=null">${csRecordSubject$csrsIsInvoice}</s:if><s:else>
		 	<select class="narrow" id="csrsIsInvoice" name="csRecordSubject.csrsIsInvoice">
		 		<option value="">请选择</option>
				<option value="true" ${csRecordSubject.csrsIsInvoice==true?"selected":""}>是</option>
				<option value="false" ${csRecordSubject.csrsIsInvoice==false?"selected":""}>否</option>		 		
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择消费科目的是否开票项</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****是否开票项字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrsIsInvoice">${csRecordSubject.csrsIsInvoice}</textarea>
		 		<span>
		 	
			 ${csRecordSubject.csrsIsInvoice$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrsIsInvoice}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csRecordSubject.csrsId}" name="csRecordSubject.csrsId" id="csrsId" />
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