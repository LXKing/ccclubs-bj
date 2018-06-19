<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/finance/operecord.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/finance/operecord.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csOperRecord.csorId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csorId,csorHost,csorTitle,csorMoneyType,csorRecordType,csorIn,csorOut,csorRecord,csorMember,csorOrder,csorObject,csorDateTime,csorCashNo,csorAdder,csorStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csorId,csorHost,csorTitle,csorMoneyType,csorRecordType,csorIn,csorOut,csorRecord,csorMember,csorOrder,csorObject,csorDateTime,csorCashNo,csorAdder,csorStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csorId,csorHost,csorTitle,csorMoneyType,csorRecordType,csorIn,csorOut,csorRecord,csorMember,csorOrder,csorObject,csorDateTime,csorCashNo,csorAdder,csorStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csorId,csorHost,csorTitle,csorMoneyType,csorRecordType,csorIn,csorOut,csorRecord,csorMember,csorOrder,csorObject,csorDateTime,csorCashNo,csorAdder,csorStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"收支记录编辑":CTRL.title}</title>
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
	$("#operecordForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csorId==true">
		${lz:set("haveEditable",true)}
		,"csOperRecord.csorId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csorHost==true">
		${lz:set("haveEditable",true)}
		,"csOperRecord.csorHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csorTitle==true">
		${lz:set("haveEditable",true)}
		,"csOperRecord.csorTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "摘要不能为空";
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "摘要最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csorMoneyType==true">
		${lz:set("haveEditable",true)}
		,"csOperRecord.csorMoneyType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择金额类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csorRecordType==true">
		${lz:set("haveEditable",true)}
		,"csOperRecord.csorRecordType":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csorIn==true">
		${lz:set("haveEditable",true)}
		,"csOperRecord.csorIn":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "收入输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csorOut==true">
		${lz:set("haveEditable",true)}
		,"csOperRecord.csorOut":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "支出输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csorRecord==true">
		${lz:set("haveEditable",true)}
		,"csOperRecord.csorRecord":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csorMember==true">
		${lz:set("haveEditable",true)}
		,"csOperRecord.csorMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csorOrder==true">
		${lz:set("haveEditable",true)}
		,"csOperRecord.csorOrder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csorObject==true">
		${lz:set("haveEditable",true)}
		,"csOperRecord.csorObject":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csorDateTime==true">
		${lz:set("haveEditable",true)}
		,"csOperRecord.csorDateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "发生时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csorCashNo==true">
		${lz:set("haveEditable",true)}
		,"csOperRecord.csorCashNo":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csorAdder==true">
		${lz:set("haveEditable",true)}
		,"csOperRecord.csorAdder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csorStatus==true">
		${lz:set("haveEditable",true)}
		,"csOperRecord.csorStatus":function(el){
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
	<form class="form editform" ref="CsOperRecord" id="operecordForm" name="operecordForm" action="${empty CTRL.action?"operecord_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写收支记录相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csOperRecordToken}" name="csOperRecordToken" id="csOperRecordToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csOperRecord.csorId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csorId和after$csorId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csorId==true">
	${before$csorId}
	<dl class="csorId ${CTRL.e.csorId?"hidden":""}" major  ref="csorId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csorId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csOperRecord$csorId!=null">${csOperRecord$csorId}</s:if><s:else>
			<input type="hidden" value="${csOperRecord.csorId}" name="csOperRecord.csorId" id="csorId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csorId">${csOperRecord.csorId}</textarea>
		 		<span>
		 	
			 ${csOperRecord.csorId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csorId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csorHost和after$csorHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csorHost==true">
	${before$csorHost}
	<dl class="csorHost " major  ref="csorHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csorHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOperRecord$csorHost!=null">${csOperRecord$csorHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csorHost" name="csOperRecord.csorHost">
		 				<option selected value="${csOperRecord.csorHost}">
		 					${get:SrvHost(csOperRecord.csorHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csorHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csorHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csorHost">${csOperRecord.csorHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csOperRecord.csorHost}',{ctrl:{editable:false,visible:true}})">
			 ${csOperRecord.csorHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csorHost}
	</s:if>
	
	${lz:set("注释","*****************摘要字段的输入框代码*****************")}
	${lz:set("注释","before$csorTitle和after$csorTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csorTitle==true">
	${before$csorTitle}
	<dl class="csorTitle " major  ref="csorTitle" style="width:98%;">
		<dt>摘　　要:</dt>
		<s:if test="#request.CTRL.e.csorTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csOperRecord$csorTitle!=null">${csOperRecord$csorTitle}</s:if><s:else>
		 	<textarea class="input wide"  id="csorTitle" name="csOperRecord.csorTitle" rows="5">${csOperRecord.csorTitle}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入收支记录的摘要</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****摘要字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csOperRecord.csorTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csorTitle}
	</s:if>
	
	${lz:set("注释","*****************金额类型字段的输入框代码*****************")}
	${lz:set("注释","before$csorMoneyType和after$csorMoneyType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csorMoneyType==true">
	${before$csorMoneyType}
	<dl class="csorMoneyType " major  ref="csorMoneyType" >
		<dt>金额类型:</dt>
		<s:if test="#request.CTRL.e.csorMoneyType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOperRecord$csorMoneyType!=null">${csOperRecord$csorMoneyType}</s:if><s:else>
		 	<select class="narrow" id="csorMoneyType" name="csOperRecord.csorMoneyType">
		 		<option value="">请选择</option>
				<option value="0" ${csOperRecord.csorMoneyType==0?"selected":""}>保证金</option>
				<option value="1" ${csOperRecord.csorMoneyType==1?"selected":""}>优惠券</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择收支记录的金额类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****金额类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csorMoneyType">${csOperRecord.csorMoneyType}</textarea>
		 		<span>
		 	
			 ${csOperRecord.csorMoneyType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csorMoneyType}
	</s:if>
	
	${lz:set("注释","*****************科目类型字段的输入框代码*****************")}
	${lz:set("注释","before$csorRecordType和after$csorRecordType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csorRecordType==true">
	${before$csorRecordType}
	<dl class="csorRecordType " major  ref="csorRecordType" >
		<dt>科目类型:</dt>
		<s:if test="#request.CTRL.e.csorRecordType==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOperRecord$csorRecordType!=null">${csOperRecord$csorRecordType}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/configurator/recordsubject_query.do?size=-1" id="csorRecordType" name="csOperRecord.csorRecordType">
		 				<option selected value="${csOperRecord.csorRecordType}">
		 					${get:CsRecordSubject(csOperRecord.csorRecordType).csrsName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csorRecordType').val())==''){return;};window.href('${basePath}${proname}/configurator/recordsubject_details.do?key='+$('#csorRecordType').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****科目类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csorRecordType">${csOperRecord.csorRecordType}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/recordsubject_details.do?key=${csOperRecord.csorRecordType}',{ctrl:{editable:false,visible:true}})">
			 ${csOperRecord.csorRecordType$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csorRecordType}
	</s:if>
	
	${lz:set("注释","*****************收入字段的输入框代码*****************")}
	${lz:set("注释","before$csorIn和after$csorIn变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csorIn==true">
	${before$csorIn}
	<dl class="csorIn " major  ref="csorIn" >
		<dt>收　　入:</dt>
		<s:if test="#request.CTRL.e.csorIn==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOperRecord$csorIn!=null">${csOperRecord$csorIn}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOperRecord.csorIn" id="csorIn"  value="${csOperRecord.csorIn}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入收支记录的收入</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****收入字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csorIn">${csOperRecord.csorIn}</textarea>
		 		<span>
		 	
			 ${csOperRecord.csorIn$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csorIn}
	</s:if>
	
	${lz:set("注释","*****************支出字段的输入框代码*****************")}
	${lz:set("注释","before$csorOut和after$csorOut变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csorOut==true">
	${before$csorOut}
	<dl class="csorOut " major  ref="csorOut" >
		<dt>支　　出:</dt>
		<s:if test="#request.CTRL.e.csorOut==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOperRecord$csorOut!=null">${csOperRecord$csorOut}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOperRecord.csorOut" id="csorOut"  value="${csOperRecord.csorOut}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入收支记录的支出</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****支出字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csorOut">${csOperRecord.csorOut}</textarea>
		 		<span>
		 	
			 ${csOperRecord.csorOut$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csorOut}
	</s:if>
	
	${lz:set("注释","*****************关联消费记录字段的输入框代码*****************")}
	${lz:set("注释","before$csorRecord和after$csorRecord变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csorRecord==true">
	${before$csorRecord}
	<dl class="csorRecord " major  ref="csorRecord" >
		<dt>关联消费记录:</dt>
		<s:if test="#request.CTRL.e.csorRecord==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOperRecord$csorRecord!=null">${csOperRecord$csorRecord}</s:if><s:else>
		 			<input title="请输入消费记录编号进行查询" class="combox narrow" action="${basePath}${proname}/finance/record_query.do?value={param}&csrHost={csorHost}" type="text" id="csorRecord" name="csOperRecord.csorRecord" text="${get:CsRecord(csOperRecord.csorRecord).csrId}" value="${csOperRecord.csorRecord}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csorRecord').val())==''){return;};window.href('${basePath}${proname}/finance/record_details.do?key='+$('#csorRecord').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联消费记录字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csorRecord">${csOperRecord.csorRecord}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/finance/record_details.do?key=${csOperRecord.csorRecord}',{ctrl:{editable:false,visible:true}})">
			 ${csOperRecord.csorRecord$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csorRecord}
	</s:if>
	
	${lz:set("注释","*****************关联会员字段的输入框代码*****************")}
	${lz:set("注释","before$csorMember和after$csorMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csorMember==true">
	${before$csorMember}
	<dl class="csorMember " minor  ref="csorMember" >
		<dt>关联会员:</dt>
		<s:if test="#request.CTRL.e.csorMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOperRecord$csorMember!=null">${csOperRecord$csorMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={csorHost}" type="text" id="csorMember" name="csOperRecord.csorMember" text="${get:CsMember(csOperRecord.csorMember).csmName}" value="${csOperRecord.csorMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csorMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csorMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csorMember">${csOperRecord.csorMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csOperRecord.csorMember}',{ctrl:{editable:false,visible:true}})">
			 ${csOperRecord.csorMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csorMember}
	</s:if>
	
	${lz:set("注释","*****************关联订单字段的输入框代码*****************")}
	${lz:set("注释","before$csorOrder和after$csorOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csorOrder==true">
	${before$csorOrder}
	<dl class="csorOrder " major  ref="csorOrder" >
		<dt>关联订单:</dt>
		<s:if test="#request.CTRL.e.csorOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOperRecord$csorOrder!=null">${csOperRecord$csorOrder}</s:if><s:else>
		 			<input title="请输入系统订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={csorHost}" type="text" id="csorOrder" name="csOperRecord.csorOrder" text="${get:CsOrder(csOperRecord.csorOrder).csoId}" value="${csOperRecord.csorOrder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csorOrder').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?key='+$('#csorOrder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csorOrder">${csOperRecord.csorOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?key=${csOperRecord.csorOrder}',{ctrl:{editable:false,visible:true}})">
			 ${csOperRecord.csorOrder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csorOrder}
	</s:if>
	
	${lz:set("注释","*****************其它关联字段的输入框代码*****************")}
	${lz:set("注释","before$csorObject和after$csorObject变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csorObject==true">
	${before$csorObject}
	<dl class="csorObject " major  ref="csorObject" >
		<dt>其它关联:</dt>
		<s:if test="#request.CTRL.e.csorObject==true">
		${lz:set("haveEditable",true)}
		<dd input="generic">
		<s:if test="#request.csOperRecord$csorObject!=null">${csOperRecord$csorObject}</s:if><s:else>
		 	<input title="请输入动态查询" class="combox narrow" generic="csorObjectGeneric" size="16" type="text" id="csorObject" name="csOperRecord.csorObject" text="${csOperRecord.csorObject$}" value="${csOperRecord.csorObject}"/>
		 	${lz:set("models",get:models("CsTrouble,CsViolat,CsRepair,CsMaintain"))}
		 	<select id="csorObjectGeneric" style="display:none;">
		 		<option value="">--选择模块--</option>
		 		<s:iterator value="#request.models" id="item" status="i">
		 		<option value="${item.name}:${basePath}admin/${item.namespace}_query.do?value={param}">${item.caption}</option>
		 		</s:iterator>
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="lookup('#${NAME}')"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em>类型@id</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****其它关联字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csorObject">${csOperRecord.csorObject}</textarea>
		 		<span>
		 	
			 ${csOperRecord.csorObject$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csorObject}
	</s:if>
	
	${lz:set("注释","*****************发生时间字段的输入框代码*****************")}
	${lz:set("注释","before$csorDateTime和after$csorDateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csorDateTime==true">
	${before$csorDateTime}
	<dl class="csorDateTime " major  ref="csorDateTime" >
		<dt>发生时间:</dt>
		<s:if test="#request.CTRL.e.csorDateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOperRecord$csorDateTime!=null">${csOperRecord$csorDateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOperRecord.csorDateTime" id="csorDateTime"  value="<s:date name="csOperRecord.csorDateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择收支记录的发生时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****发生时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csorDateTime">${csOperRecord.csorDateTime}</textarea>
		 		<span>
		 	
			 ${csOperRecord.csorDateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csorDateTime}
	</s:if>
	
	${lz:set("注释","*****************结账流水字段的输入框代码*****************")}
	${lz:set("注释","before$csorCashNo和after$csorCashNo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csorCashNo==true">
	${before$csorCashNo}
	<dl class="csorCashNo " major  ref="csorCashNo" >
		<dt>结账流水:</dt>
		<s:if test="#request.CTRL.e.csorCashNo==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOperRecord$csorCashNo!=null">${csOperRecord$csorCashNo}</s:if><s:else>
		 			<input title="请输入提现记录流水号进行查询" class="combox narrow" action="${basePath}${proname}/finance/cashrecord_query.do?value={param}&cscrHost={csorHost}" type="text" id="csorCashNo" name="csOperRecord.csorCashNo" text="${get:CsCashRecord(csOperRecord.csorCashNo).cscrTradeNo}" value="${csOperRecord.csorCashNo}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csorCashNo').val())==''){return;};window.href('${basePath}${proname}/finance/cashrecord_details.do?key='+$('#csorCashNo').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结账流水字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csorCashNo">${csOperRecord.csorCashNo}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/finance/cashrecord_details.do?key=${csOperRecord.csorCashNo}',{ctrl:{editable:false,visible:true}})">
			 ${csOperRecord.csorCashNo$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csorCashNo}
	</s:if>
	
	${lz:set("注释","*****************添加人字段的输入框代码*****************")}
	${lz:set("注释","before$csorAdder和after$csorAdder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csorAdder==true">
	${before$csorAdder}
	<dl class="csorAdder " major  ref="csorAdder" >
		<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csorAdder==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOperRecord$csorAdder!=null">${csOperRecord$csorAdder}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csorAdder" name="csOperRecord.csorAdder">
		 				<option selected value="${csOperRecord.csorAdder}">
		 					${get:SrvUser(csOperRecord.csorAdder).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csorAdder').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csorAdder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csorAdder">${csOperRecord.csorAdder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csOperRecord.csorAdder}',{ctrl:{editable:false,visible:true}})">
			 ${csOperRecord.csorAdder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csorAdder}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csorStatus和after$csorStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csorStatus==true">
	${before$csorStatus}
	<dl class="csorStatus " major  ref="csorStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csorStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOperRecord$csorStatus!=null">${csOperRecord$csorStatus}</s:if><s:else>
		 	<select class="narrow" id="csorStatus" name="csOperRecord.csorStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csOperRecord.csorStatus==1?"selected":""}>正常</option>
				<option value="2" ${csOperRecord.csorStatus==2?"selected":""}>争议</option>
				<option value="0" ${csOperRecord.csorStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择收支记录的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csorStatus">${csOperRecord.csorStatus}</textarea>
		 		<span>
		 	
			 ${csOperRecord.csorStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csorStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csOperRecord.csorId}" name="csOperRecord.csorId" id="csorId" />
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