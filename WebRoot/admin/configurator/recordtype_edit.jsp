<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/configurator/recordtype.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/configurator/recordtype.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csRecordType.csrtId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csrtId,csrtTypeName,csrtMoneyType,csrtIsIncome,csrtCanInvoice,csrtSubject,csrtStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csrtId,csrtTypeName,csrtMoneyType,csrtIsIncome,csrtCanInvoice,csrtSubject,csrtStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csrtId,csrtIsIncome,csrtCanInvoice,csrtSubject,csrtStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csrtId,csrtTypeName,csrtMoneyType,csrtIsIncome,csrtCanInvoice,csrtSubject,csrtStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"消费类型编辑":CTRL.title}</title>
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
	$("#recordtypeForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csrtId==true">
		${lz:set("haveEditable",true)}
		,"csRecordType.csrtId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrtTypeName==true">
		${lz:set("haveEditable",true)}
		,"csRecordType.csrtTypeName":function(el){
			if(jQuery.trim(el.value)=="")
				return "类型名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "类型名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrtMoneyType==true">
		${lz:set("haveEditable",true)}
		,"csRecordType.csrtMoneyType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择券额类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrtIsIncome==true">
		${lz:set("haveEditable",true)}
		,"csRecordType.csrtIsIncome":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择是否收入项";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrtCanInvoice==true">
		${lz:set("haveEditable",true)}
		,"csRecordType.csrtCanInvoice":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择是否开票项";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrtSubject==true">
		${lz:set("haveEditable",true)}
		,"csRecordType.csrtSubject":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrtStatus==true">
		${lz:set("haveEditable",true)}
		,"csRecordType.csrtStatus":function(el){
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
	<form class="form editform" ref="CsRecordType" id="recordtypeForm" name="recordtypeForm" action="${empty CTRL.action?"recordtype_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写消费类型相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csRecordTypeToken}" name="csRecordTypeToken" id="csRecordTypeToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csRecordType.csrtId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csrtId和after$csrtId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrtId==true">
	${before$csrtId}
	<dl class="csrtId ${CTRL.e.csrtId?"hidden":""}" major  ref="csrtId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csrtId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csRecordType$csrtId!=null">${csRecordType$csrtId}</s:if><s:else>
			<input type="hidden" value="${csRecordType.csrtId}" name="csRecordType.csrtId" id="csrtId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrtId">${csRecordType.csrtId}</textarea>
		 		<span>
		 	
			 ${csRecordType.csrtId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrtId}
	</s:if>
	
	${lz:set("注释","*****************类型名称字段的输入框代码*****************")}
	${lz:set("注释","before$csrtTypeName和after$csrtTypeName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrtTypeName==true">
	${before$csrtTypeName}
	<dl class="csrtTypeName " major  ref="csrtTypeName" >
		<dt>类型名称:</dt>
		<s:if test="#request.CTRL.e.csrtTypeName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csRecordType$csrtTypeName!=null">${csRecordType$csrtTypeName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csRecordType.csrtTypeName" id="csrtTypeName"  value="${csRecordType.csrtTypeName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入消费类型的类型名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****类型名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrtTypeName">${csRecordType.csrtTypeName}</textarea>
		 		<span>
		 	
			 ${csRecordType.csrtTypeName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrtTypeName}
	</s:if>
	
	${lz:set("注释","*****************券额类型字段的输入框代码*****************")}
	${lz:set("注释","before$csrtMoneyType和after$csrtMoneyType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrtMoneyType==true">
	${before$csrtMoneyType}
	<dl class="csrtMoneyType " major  ref="csrtMoneyType" >
		<dt>券额类型:</dt>
		<s:if test="#request.CTRL.e.csrtMoneyType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRecordType$csrtMoneyType!=null">${csRecordType$csrtMoneyType}</s:if><s:else>
		 	<select class="narrow" id="csrtMoneyType" name="csRecordType.csrtMoneyType">
		 		<option value="">请选择</option>
				<option value="0" ${csRecordType.csrtMoneyType==0?"selected":""}>保证金</option>
				<option value="1" ${csRecordType.csrtMoneyType==1?"selected":""}>现金券</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择消费类型的券额类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****券额类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrtMoneyType">${csRecordType.csrtMoneyType}</textarea>
		 		<span>
		 	
			 ${csRecordType.csrtMoneyType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrtMoneyType}
	</s:if>
	
	${lz:set("注释","*****************是否收入项字段的输入框代码*****************")}
	${lz:set("注释","before$csrtIsIncome和after$csrtIsIncome变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrtIsIncome==true">
	${before$csrtIsIncome}
	<dl class="csrtIsIncome " major  ref="csrtIsIncome" >
		<dt>是否收入项:</dt>
		<s:if test="#request.CTRL.e.csrtIsIncome==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRecordType$csrtIsIncome!=null">${csRecordType$csrtIsIncome}</s:if><s:else>
		 	<select class="narrow" id="csrtIsIncome" name="csRecordType.csrtIsIncome">
		 		<option value="">请选择</option>
				<option value="true" ${csRecordType.csrtIsIncome==true?"selected":""}>是</option>
				<option value="false" ${csRecordType.csrtIsIncome==false?"selected":""}>否</option>		 		
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择消费类型的是否收入项</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****是否收入项字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrtIsIncome">${csRecordType.csrtIsIncome}</textarea>
		 		<span>
		 	
			 ${csRecordType.csrtIsIncome$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrtIsIncome}
	</s:if>
	
	${lz:set("注释","*****************是否开票项字段的输入框代码*****************")}
	${lz:set("注释","before$csrtCanInvoice和after$csrtCanInvoice变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrtCanInvoice==true">
	${before$csrtCanInvoice}
	<dl class="csrtCanInvoice " major  ref="csrtCanInvoice" >
		<dt>是否开票项:</dt>
		<s:if test="#request.CTRL.e.csrtCanInvoice==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRecordType$csrtCanInvoice!=null">${csRecordType$csrtCanInvoice}</s:if><s:else>
		 	<select class="narrow" id="csrtCanInvoice" name="csRecordType.csrtCanInvoice">
		 		<option value="">请选择</option>
				<option value="true" ${csRecordType.csrtCanInvoice==true?"selected":""}>是</option>
				<option value="false" ${csRecordType.csrtCanInvoice==false?"selected":""}>否</option>		 		
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择消费类型的是否开票项</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****是否开票项字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrtCanInvoice">${csRecordType.csrtCanInvoice}</textarea>
		 		<span>
		 	
			 ${csRecordType.csrtCanInvoice$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrtCanInvoice}
	</s:if>
	
	${lz:set("注释","*****************对应科目字段的输入框代码*****************")}
	${lz:set("注释","before$csrtSubject和after$csrtSubject变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrtSubject==true">
	${before$csrtSubject}
	<dl class="csrtSubject " major  ref="csrtSubject" >
		<dt>对应科目:</dt>
		<s:if test="#request.CTRL.e.csrtSubject==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csRecordType$csrtSubject!=null">${csRecordType$csrtSubject}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/configurator/recordsubject_query.do?size=-1" id="csrtSubject" name="csRecordType.csrtSubject">
		 				<option selected value="${csRecordType.csrtSubject}">
		 					${get:CsRecordSubject(csRecordType.csrtSubject).csrsName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrtSubject').val())==''){return;};window.href('${basePath}${proname}/configurator/recordsubject_details.do?key='+$('#csrtSubject').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****对应科目字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrtSubject">${csRecordType.csrtSubject}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/recordsubject_details.do?key=${csRecordType.csrtSubject}',{ctrl:{editable:false,visible:true}})">
			 ${csRecordType.csrtSubject$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrtSubject}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csrtStatus和after$csrtStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrtStatus==true">
	${before$csrtStatus}
	<dl class="csrtStatus " major  ref="csrtStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csrtStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRecordType$csrtStatus!=null">${csRecordType$csrtStatus}</s:if><s:else>
		 	<select class="narrow" id="csrtStatus" name="csRecordType.csrtStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csRecordType.csrtStatus==1?"selected":""}>正常</option>
				<option value="0" ${csRecordType.csrtStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择消费类型的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrtStatus">${csRecordType.csrtStatus}</textarea>
		 		<span>
		 	
			 ${csRecordType.csrtStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrtStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csRecordType.csrtId}" name="csRecordType.csrtId" id="csrtId" />
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