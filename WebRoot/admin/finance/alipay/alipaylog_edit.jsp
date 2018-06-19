<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/finance/alipay/alipaylog.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/finance/alipay/alipaylog.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csAlipayLog.csalId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csalId,csalBankType,csalRecord,csalRefund,csalOther,csalModelClass,csalType,csalLog,csalStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csalId,csalBankType,csalRecord,csalRefund,csalOther,csalModelClass,csalType,csalLog,csalStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csalId,csalBankType,csalRecord,csalRefund,csalOther,csalModelClass,csalType,csalLog,csalStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csalId,csalBankType,csalRecord,csalRefund,csalOther,csalModelClass,csalType,csalLog,csalStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"接口日志编辑":CTRL.title}</title>
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
		if($("#csalLog").size()>0){
			 //构造编辑页csAlipayLog.csalLog为html编辑控件
			 KE.init({
				id : 'csalLog',
				width : '625px',
				height : '300px' , 
				imageUploadJson : '${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}?app=alipaylog_edit&type=img${ lz:config("upload.url")==null ? "" : "&remote="}${ lz:config("upload.url")==null ? "" : basePath}${ lz:config("upload.url")==null ? "" : "callback.jsp"}'
			 });
			 KE.create("csalLog");
		 }
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#alipaylogForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csalId==true">
		${lz:set("haveEditable",true)}
		,"csAlipayLog.csalId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csalBankType==true">
		${lz:set("haveEditable",true)}
		,"csAlipayLog.csalBankType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择接口类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csalRecord==true">
		${lz:set("haveEditable",true)}
		,"csAlipayLog.csalRecord":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csalRefund==true">
		${lz:set("haveEditable",true)}
		,"csAlipayLog.csalRefund":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csalOther==true">
		${lz:set("haveEditable",true)}
		,"csAlipayLog.csalOther":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csalModelClass==true">
		${lz:set("haveEditable",true)}
		,"csAlipayLog.csalModelClass":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "模型类名最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csalType==true">
		${lz:set("haveEditable",true)}
		,"csAlipayLog.csalType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csalLog==true">
		${lz:set("haveEditable",true)}
		,"csAlipayLog.csalLog":function(el){
			if($("#csalLog").get(0))
			 	KE.sync("csalLog");		 
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "日志内容最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csalAddTime==true">
		${lz:set("haveEditable",true)}
		,"csAlipayLog.csalAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csalStatus==true">
		${lz:set("haveEditable",true)}
		,"csAlipayLog.csalStatus":function(el){
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
	<form class="form editform" ref="CsAlipayLog" id="alipaylogForm" name="alipaylogForm" action="${empty CTRL.action?"alipaylog_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写接口日志相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csAlipayLogToken}" name="csAlipayLogToken" id="csAlipayLogToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csAlipayLog.csalId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csalId和after$csalId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csalId==true">
	${before$csalId}
	<dl class="csalId ${CTRL.e.csalId?"hidden":""}" major  ref="csalId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csalId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csAlipayLog$csalId!=null">${csAlipayLog$csalId}</s:if><s:else>
			<input type="hidden" value="${(csAlipayLog.csalId)}" name="csAlipayLog.csalId" id="csalId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csalId">${csAlipayLog.csalId}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayLog.csalId$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csalId}
	</s:if>
	
	${lz:set("注释","*****************接口类型字段的输入框代码*****************")}
	${lz:set("注释","before$csalBankType和after$csalBankType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csalBankType==true">
	${before$csalBankType}
	<dl class="csalBankType " major  ref="csalBankType" >
		<dt>接口类型:</dt>
		<s:if test="#request.CTRL.e.csalBankType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csAlipayLog$csalBankType!=null">${csAlipayLog$csalBankType}</s:if><s:else>
		 	<select class="narrow" id="csalBankType" name="csAlipayLog.csalBankType">
		 		<option value="">请选择</option>
				<option value="0" ${csAlipayLog.csalBankType==0?"selected":""}>支付宝</option>
				<option value="10" ${csAlipayLog.csalBankType==10?"selected":""}>招商银行</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择接口日志的接口类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****接口类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csalBankType">${csAlipayLog.csalBankType}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayLog.csalBankType$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csalBankType}
	</s:if>
	
	${lz:set("注释","*****************关联充值字段的输入框代码*****************")}
	${lz:set("注释","before$csalRecord和after$csalRecord变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csalRecord==true">
	${before$csalRecord}
	<dl class="csalRecord " major  ref="csalRecord" >
		<dt>关联充值:</dt>
		<s:if test="#request.CTRL.e.csalRecord==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csAlipayLog$csalRecord!=null">${csAlipayLog$csalRecord}</s:if><s:else>
		 			<input title="请输入接口充值交易号进行查询" class="combox narrow" action="${basePath}${proname}/finance/alipay/alipayrecord_query.do?value={param}" type="text" id="csalRecord" name="csAlipayLog.csalRecord" text="${get:CsAlipayRecord(csAlipayLog.csalRecord).csarTradeNo}" value="${(csAlipayLog.csalRecord)}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csalRecord').val())==''){return;};window.href('${basePath}${proname}/finance/alipay/alipayrecord_details.do?key='+$('#csalRecord').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联充值字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csalRecord">${csAlipayLog.csalRecord}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/finance/alipay/alipayrecord_details.do?key=${(csAlipayLog.csalRecord)}',{ctrl:{editable:false,visible:true}})">
			 ${lz:html(csAlipayLog.csalRecord$)}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csalRecord}
	</s:if>
	
	${lz:set("注释","*****************关联退款字段的输入框代码*****************")}
	${lz:set("注释","before$csalRefund和after$csalRefund变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csalRefund==true">
	${before$csalRefund}
	<dl class="csalRefund " major  ref="csalRefund" >
		<dt>关联退款:</dt>
		<s:if test="#request.CTRL.e.csalRefund==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csAlipayLog$csalRefund!=null">${csAlipayLog$csalRefund}</s:if><s:else>
		 			<input title="请输入接口退款退款批次号进行查询" class="combox narrow" action="${basePath}${proname}/finance/alipay/alipayrefund_query.do?value={param}" type="text" id="csalRefund" name="csAlipayLog.csalRefund" text="${get:CsAlipayRefund(csAlipayLog.csalRefund).csarTradeNo}" value="${(csAlipayLog.csalRefund)}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csalRefund').val())==''){return;};window.href('${basePath}${proname}/finance/alipay/alipayrefund_details.do?key='+$('#csalRefund').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联退款字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csalRefund">${csAlipayLog.csalRefund}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/finance/alipay/alipayrefund_details.do?key=${(csAlipayLog.csalRefund)}',{ctrl:{editable:false,visible:true}})">
			 ${lz:html(csAlipayLog.csalRefund$)}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csalRefund}
	</s:if>
	
	${lz:set("注释","*****************关联其它字段的输入框代码*****************")}
	${lz:set("注释","before$csalOther和after$csalOther变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csalOther==true">
	${before$csalOther}
	<dl class="csalOther " major  ref="csalOther" >
		<dt>关联其它:</dt>
		<s:if test="#request.CTRL.e.csalOther==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csAlipayLog$csalOther!=null">${csAlipayLog$csalOther}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csAlipayLog.csalOther" id="csalOther"  value="${(csAlipayLog.csalOther)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>比如订单号、会员ID</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联其它字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csalOther">${csAlipayLog.csalOther}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayLog.csalOther$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csalOther}
	</s:if>
	
	${lz:set("注释","*****************模型类名字段的输入框代码*****************")}
	${lz:set("注释","before$csalModelClass和after$csalModelClass变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csalModelClass==true">
	${before$csalModelClass}
	<dl class="csalModelClass " major  ref="csalModelClass" >
		<dt>模型类名:</dt>
		<s:if test="#request.CTRL.e.csalModelClass==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csAlipayLog$csalModelClass!=null">${csAlipayLog$csalModelClass}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csAlipayLog.csalModelClass" id="csalModelClass"  value="${(csAlipayLog.csalModelClass)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>比如CsOrder、CsMember</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****模型类名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csalModelClass">${csAlipayLog.csalModelClass}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayLog.csalModelClass$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csalModelClass}
	</s:if>
	
	${lz:set("注释","*****************类型字段的输入框代码*****************")}
	${lz:set("注释","before$csalType和after$csalType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csalType==true">
	${before$csalType}
	<dl class="csalType " major  ref="csalType" >
		<dt>类　　型:</dt>
		<s:if test="#request.CTRL.e.csalType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csAlipayLog$csalType!=null">${csAlipayLog$csalType}</s:if><s:else>
		 	<select class="narrow" id="csalType" name="csAlipayLog.csalType">
		 		<option value="">请选择</option>
				<option value="0" ${csAlipayLog.csalType==0?"selected":""}>进站</option>
				<option value="1" ${csAlipayLog.csalType==1?"selected":""}>出站</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择接口日志的类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csalType">${csAlipayLog.csalType}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayLog.csalType$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csalType}
	</s:if>
	
	${lz:set("注释","*****************日志内容字段的输入框代码*****************")}
	${lz:set("注释","before$csalLog和after$csalLog变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csalLog==true">
	${before$csalLog}
	<dl class="csalLog " major  ref="csalLog" style="width:98%;">
		<dt>日志内容:</dt>
		<s:if test="#request.CTRL.e.csalLog==true">
		${lz:set("haveEditable",true)}
		<dd input="html">
		<s:if test="#request.csAlipayLog$csalLog!=null">${csAlipayLog$csalLog}</s:if><s:else>
		    <textarea class="input wide"  id="csalLog" name="csAlipayLog.csalLog" style="height:300px;visibility:hidden;">${csAlipayLog.csalLog}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入接口日志的日志内容</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****日志内容字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${lz:html(csAlipayLog.csalLog$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csalLog}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csalAddTime和after$csalAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csalAddTime==true">
	${before$csalAddTime}
	<dl class="csalAddTime " major  ref="csalAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csalAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csAlipayLog$csalAddTime!=null">${csAlipayLog$csalAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csAlipayLog.csalAddTime" id="csalAddTime"  value="<s:date name="csAlipayLog.csalAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择接口日志的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csalAddTime">${csAlipayLog.csalAddTime}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayLog.csalAddTime$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csalAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csalStatus和after$csalStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csalStatus==true">
	${before$csalStatus}
	<dl class="csalStatus " major  ref="csalStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csalStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csAlipayLog$csalStatus!=null">${csAlipayLog$csalStatus}</s:if><s:else>
		 	<select class="narrow" id="csalStatus" name="csAlipayLog.csalStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csAlipayLog.csalStatus==1?"selected":""}>正常</option>
				<option value="0" ${csAlipayLog.csalStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择接口日志的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csalStatus">${csAlipayLog.csalStatus}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayLog.csalStatus$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csalStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csAlipayLog.csalId}" name="csAlipayLog.csalId" id="csalId" />
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