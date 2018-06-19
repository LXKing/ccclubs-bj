<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/user/address.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/user/address.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csAddress.csaId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csaId,csaAddress,csaName,csaDefault,csaMember,csaPostCode,csaContact,csaRemark,csaStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csaId,csaAddress,csaName,csaDefault,csaMember,csaPostCode,csaContact,csaRemark,csaStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csaId,csaAddress,csaName,csaDefault,csaMember,csaPostCode,csaContact,csaRemark,csaStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csaId,csaAddress,csaName,csaDefault,csaMember,csaPostCode,csaContact,csaRemark,csaStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"会员地址编辑":CTRL.title}</title>
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
	$("#addressForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csaId==true">
		${lz:set("haveEditable",true)}
		,"csAddress.csaId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaAddress==true">
		${lz:set("haveEditable",true)}
		,"csAddress.csaAddress":function(el){
			if(jQuery.trim(el.value)=="")
				return "详细地址不能为空";
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "详细地址最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaName==true">
		${lz:set("haveEditable",true)}
		,"csAddress.csaName":function(el){
			if(jQuery.trim(el.value)=="")
				return "收件人姓名不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "收件人姓名最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaDefault==true">
		${lz:set("haveEditable",true)}
		,"csAddress.csaDefault":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择是否默认";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaMember==true">
		${lz:set("haveEditable",true)}
		,"csAddress.csaMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaPostCode==true">
		${lz:set("haveEditable",true)}
		,"csAddress.csaPostCode":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "邮编最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaContact==true">
		${lz:set("haveEditable",true)}
		,"csAddress.csaContact":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "电话最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaRemark==true">
		${lz:set("haveEditable",true)}
		,"csAddress.csaRemark":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csAddress.csaUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaAddTime==true">
		${lz:set("haveEditable",true)}
		,"csAddress.csaAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaStatus==true">
		${lz:set("haveEditable",true)}
		,"csAddress.csaStatus":function(el){
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
	<form class="form editform" ref="CsAddress" id="addressForm" name="addressForm" action="${empty CTRL.action?"address_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写会员地址相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csAddressToken}" name="csAddressToken" id="csAddressToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csAddress.csaId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csaId和after$csaId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaId==true">
	${before$csaId}
	<dl class="csaId ${CTRL.e.csaId?"hidden":""}" major  ref="csaId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csaId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csAddress$csaId!=null">${csAddress$csaId}</s:if><s:else>
			<input type="hidden" value="${csAddress.csaId}" name="csAddress.csaId" id="csaId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaId">${csAddress.csaId}</textarea>
		 		<span>
		 	
			 ${csAddress.csaId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaId}
	</s:if>
	
	${lz:set("注释","*****************详细地址字段的输入框代码*****************")}
	${lz:set("注释","before$csaAddress和after$csaAddress变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaAddress==true">
	${before$csaAddress}
	<dl class="csaAddress " major  ref="csaAddress" style="width:98%;">
		<dt>详细地址:</dt>
		<s:if test="#request.CTRL.e.csaAddress==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csAddress$csaAddress!=null">${csAddress$csaAddress}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csAddress.csaAddress" id="csaAddress"  value="${csAddress.csaAddress}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入会员地址的详细地址</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****详细地址字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csaAddress">${csAddress.csaAddress}</textarea>
		 		<span>
		 	
			 ${csAddress.csaAddress$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaAddress}
	</s:if>
	
	${lz:set("注释","*****************收件人姓名字段的输入框代码*****************")}
	${lz:set("注释","before$csaName和after$csaName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaName==true">
	${before$csaName}
	<dl class="csaName " major  ref="csaName" >
		<dt>收件人姓名:</dt>
		<s:if test="#request.CTRL.e.csaName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csAddress$csaName!=null">${csAddress$csaName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csAddress.csaName" id="csaName"  value="${csAddress.csaName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入会员地址的收件人姓名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****收件人姓名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaName">${csAddress.csaName}</textarea>
		 		<span>
		 	
			 ${csAddress.csaName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaName}
	</s:if>
	
	${lz:set("注释","*****************是否默认字段的输入框代码*****************")}
	${lz:set("注释","before$csaDefault和after$csaDefault变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaDefault==true">
	${before$csaDefault}
	<dl class="csaDefault " major  ref="csaDefault" >
		<dt>是否默认:</dt>
		<s:if test="#request.CTRL.e.csaDefault==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csAddress$csaDefault!=null">${csAddress$csaDefault}</s:if><s:else>
		 	<select class="narrow" id="csaDefault" name="csAddress.csaDefault">
		 		<option value="">请选择</option>
				<option value="true" ${csAddress.csaDefault==true?"selected":""}>是</option>
				<option value="false" ${csAddress.csaDefault==false?"selected":""}>否</option>		 		
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员地址的是否默认</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****是否默认字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaDefault">${csAddress.csaDefault}</textarea>
		 		<span>
		 	
			 ${csAddress.csaDefault$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaDefault}
	</s:if>
	
	${lz:set("注释","*****************所属会员字段的输入框代码*****************")}
	${lz:set("注释","before$csaMember和after$csaMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaMember==true">
	${before$csaMember}
	<dl class="csaMember " major  ref="csaMember" >
		<dt>所属会员:</dt>
		<s:if test="#request.CTRL.e.csaMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csAddress$csaMember!=null">${csAddress$csaMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}" type="text" id="csaMember" name="csAddress.csaMember" text="${get:CsMember(csAddress.csaMember).csmName}" value="${csAddress.csaMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csaMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csaMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaMember">${csAddress.csaMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csAddress.csaMember}',{ctrl:{editable:false,visible:true}})">
			 ${csAddress.csaMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaMember}
	</s:if>
	
	${lz:set("注释","*****************邮编字段的输入框代码*****************")}
	${lz:set("注释","before$csaPostCode和after$csaPostCode变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaPostCode==true">
	${before$csaPostCode}
	<dl class="csaPostCode " major  ref="csaPostCode" >
		<dt>邮　　编:</dt>
		<s:if test="#request.CTRL.e.csaPostCode==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csAddress$csaPostCode!=null">${csAddress$csaPostCode}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csAddress.csaPostCode" id="csaPostCode"  value="${csAddress.csaPostCode}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员地址的邮编</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****邮编字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaPostCode">${csAddress.csaPostCode}</textarea>
		 		<span>
		 	
			 ${csAddress.csaPostCode$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaPostCode}
	</s:if>
	
	${lz:set("注释","*****************电话字段的输入框代码*****************")}
	${lz:set("注释","before$csaContact和after$csaContact变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaContact==true">
	${before$csaContact}
	<dl class="csaContact " major  ref="csaContact" >
		<dt>电　　话:</dt>
		<s:if test="#request.CTRL.e.csaContact==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csAddress$csaContact!=null">${csAddress$csaContact}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csAddress.csaContact" id="csaContact"  value="${csAddress.csaContact}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员地址的电话</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****电话字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaContact">${csAddress.csaContact}</textarea>
		 		<span>
		 	
			 ${csAddress.csaContact$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaContact}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$csaRemark和after$csaRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaRemark==true">
	${before$csaRemark}
	<dl class="csaRemark " minor  ref="csaRemark" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.csaRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csAddress$csaRemark!=null">${csAddress$csaRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csaRemark" name="csAddress.csaRemark" rows="5">${csAddress.csaRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员地址的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csAddress.csaRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaRemark}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csaUpdateTime和after$csaUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaUpdateTime==true">
	${before$csaUpdateTime}
	<dl class="csaUpdateTime " major  ref="csaUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csaUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csAddress$csaUpdateTime!=null">${csAddress$csaUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csAddress.csaUpdateTime" id="csaUpdateTime"  value="<s:date name="csAddress.csaUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员地址的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaUpdateTime">${csAddress.csaUpdateTime}</textarea>
		 		<span>
		 	
			 ${csAddress.csaUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csaAddTime和after$csaAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaAddTime==true">
	${before$csaAddTime}
	<dl class="csaAddTime " major  ref="csaAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csaAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csAddress$csaAddTime!=null">${csAddress$csaAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csAddress.csaAddTime" id="csaAddTime"  value="<s:date name="csAddress.csaAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员地址的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaAddTime">${csAddress.csaAddTime}</textarea>
		 		<span>
		 	
			 ${csAddress.csaAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csaStatus和after$csaStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaStatus==true">
	${before$csaStatus}
	<dl class="csaStatus " major  ref="csaStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csaStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csAddress$csaStatus!=null">${csAddress$csaStatus}</s:if><s:else>
		 	<select class="narrow" id="csaStatus" name="csAddress.csaStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csAddress.csaStatus==1?"selected":""}>正常</option>
				<option value="0" ${csAddress.csaStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员地址的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaStatus">${csAddress.csaStatus}</textarea>
		 		<span>
		 	
			 ${csAddress.csaStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csAddress.csaId}" name="csAddress.csaId" id="csaId" />
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