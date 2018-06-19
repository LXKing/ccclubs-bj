<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/object/rcgcard.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/object/rcgcard.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csRechargeCard.csrcId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csrcId,csrcHost,csrcNumber,csrcPassword,csrcType,csrcDenomination,csrcMethod,csrcValidity,csrcValidityUtil,csrcUtil",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csrcId,csrcHost,csrcNumber,csrcPassword,csrcType,csrcDenomination,csrcMethod,csrcValidity,csrcValidityUtil,csrcUtil",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csrcId,csrcHost,csrcNumber,csrcPassword,csrcType,csrcDenomination,csrcMethod,csrcValidity,csrcValidityUtil,csrcUtil,csrcMember,csrcStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csrcId,csrcHost,csrcNumber,csrcPassword,csrcType,csrcDenomination,csrcMethod,csrcValidity,csrcValidityUtil,csrcUtil,csrcMember,csrcStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"充值卡编辑":CTRL.title}</title>
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
	$("#rcgcardForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csrcId==true">
		${lz:set("haveEditable",true)}
		,"csRechargeCard.csrcId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrcHost==true">
		${lz:set("haveEditable",true)}
		,"csRechargeCard.csrcHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrcNumber==true">
		${lz:set("haveEditable",true)}
		,"csRechargeCard.csrcNumber":function(el){
			if(jQuery.trim(el.value)=="")
				return "卡号不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "卡号最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("rcgcard_query.do",{exists:true,csrcNumber:el.value});
				if(exists && exists.length>0){
					if(${csRechargeCard.csrcId==null} || exists[0].value!=$("#csrcId").val())
						return "卡号已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrcPassword==true">
		${lz:set("haveEditable",true)}
		,"csRechargeCard.csrcPassword":function(el){
			if(jQuery.trim(el.value)=="")
				return "密码不能为空";
			if(el.value.length>16 && el.value.indexOf("[*]")==-1)
				return "密码最大长度为16个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrcType==true">
		${lz:set("haveEditable",true)}
		,"csRechargeCard.csrcType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrcDenomination==true">
		${lz:set("haveEditable",true)}
		,"csRechargeCard.csrcDenomination":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "面额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrcMethod==true">
		${lz:set("haveEditable",true)}
		,"csRechargeCard.csrcMethod":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择分类";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrcValidity==true">
		${lz:set("haveEditable",true)}
		,"csRechargeCard.csrcValidity":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择内容有效期";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrcValidityUtil==true">
		${lz:set("haveEditable",true)}
		,"csRechargeCard.csrcValidityUtil":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrcUtil==true">
		${lz:set("haveEditable",true)}
		,"csRechargeCard.csrcUtil":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrcMember==true">
		${lz:set("haveEditable",true)}
		,"csRechargeCard.csrcMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrcUseTime==true">
		${lz:set("haveEditable",true)}
		,"csRechargeCard.csrcUseTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrcAddTime==true">
		${lz:set("haveEditable",true)}
		,"csRechargeCard.csrcAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrcStatus==true">
		${lz:set("haveEditable",true)}
		,"csRechargeCard.csrcStatus":function(el){
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
	<form class="form editform" ref="CsRechargeCard" id="rcgcardForm" name="rcgcardForm" action="${empty CTRL.action?"rcgcard_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写充值卡相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csRechargeCardToken}" name="csRechargeCardToken" id="csRechargeCardToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csRechargeCard.csrcId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csrcId和after$csrcId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrcId==true">
	${before$csrcId}
	<dl class="csrcId ${CTRL.e.csrcId?"hidden":""}" major  ref="csrcId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csrcId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csRechargeCard$csrcId!=null">${csRechargeCard$csrcId}</s:if><s:else>
			<input type="hidden" value="${csRechargeCard.csrcId}" name="csRechargeCard.csrcId" id="csrcId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrcId">${csRechargeCard.csrcId}</textarea>
		 		<span>
		 	
			 ${csRechargeCard.csrcId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrcId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csrcHost和after$csrcHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrcHost==true">
	${before$csrcHost}
	<dl class="csrcHost " major  ref="csrcHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csrcHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csRechargeCard$csrcHost!=null">${csRechargeCard$csrcHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csrcHost" name="csRechargeCard.csrcHost">
		 				<option selected value="${csRechargeCard.csrcHost}">
		 					${get:SrvHost(csRechargeCard.csrcHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrcHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csrcHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrcHost">${csRechargeCard.csrcHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csRechargeCard.csrcHost}',{ctrl:{editable:false,visible:true}})">
			 ${csRechargeCard.csrcHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrcHost}
	</s:if>
	
	${lz:set("注释","*****************卡号字段的输入框代码*****************")}
	${lz:set("注释","before$csrcNumber和after$csrcNumber变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrcNumber==true">
	${before$csrcNumber}
	<dl class="csrcNumber " major  ref="csrcNumber" >
		<dt>卡　　号:</dt>
		<s:if test="#request.CTRL.e.csrcNumber==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csRechargeCard$csrcNumber!=null">${csRechargeCard$csrcNumber}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csRechargeCard.csrcNumber" id="csrcNumber"  value="${csRechargeCard.csrcNumber}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入充值卡的卡号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****卡号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrcNumber">${csRechargeCard.csrcNumber}</textarea>
		 		<span>
		 	
			 ${csRechargeCard.csrcNumber$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrcNumber}
	</s:if>
	
	${lz:set("注释","*****************密码字段的输入框代码*****************")}
	${lz:set("注释","before$csrcPassword和after$csrcPassword变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrcPassword==true">
	${before$csrcPassword}
	<dl class="csrcPassword " major  ref="csrcPassword" >
		<dt>密　　码:</dt>
		<s:if test="#request.CTRL.e.csrcPassword==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csRechargeCard$csrcPassword!=null">${csRechargeCard$csrcPassword}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="16" name="csRechargeCard.csrcPassword" id="csrcPassword"  value="${csRechargeCard.csrcPassword}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入充值卡的密码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****密码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrcPassword">${csRechargeCard.csrcPassword}</textarea>
		 		<span>
		 	
			 ${csRechargeCard.csrcPassword$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrcPassword}
	</s:if>
	
	${lz:set("注释","*****************类型字段的输入框代码*****************")}
	${lz:set("注释","before$csrcType和after$csrcType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrcType==true">
	${before$csrcType}
	<dl class="csrcType " major  ref="csrcType" >
		<dt>类　　型:</dt>
		<s:if test="#request.CTRL.e.csrcType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRechargeCard$csrcType!=null">${csRechargeCard$csrcType}</s:if><s:else>
		 	<select class="narrow" id="csrcType" name="csRechargeCard.csrcType">
		 		<option value="">请选择</option>
				<option value="0" ${csRechargeCard.csrcType==0?"selected":""}>现金券</option>
				<option value="1" ${csRechargeCard.csrcType==1?"selected":""}>免费小时</option>
				<option value="2" ${csRechargeCard.csrcType==2?"selected":""}>红包</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择充值卡的类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrcType">${csRechargeCard.csrcType}</textarea>
		 		<span>
		 	
			 ${csRechargeCard.csrcType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrcType}
	</s:if>
	
	${lz:set("注释","*****************面额字段的输入框代码*****************")}
	${lz:set("注释","before$csrcDenomination和after$csrcDenomination变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrcDenomination==true">
	${before$csrcDenomination}
	<dl class="csrcDenomination " major  ref="csrcDenomination" >
		<dt>面　　额:</dt>
		<s:if test="#request.CTRL.e.csrcDenomination==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csRechargeCard$csrcDenomination!=null">${csRechargeCard$csrcDenomination}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csRechargeCard.csrcDenomination" id="csrcDenomination"  value="${csRechargeCard.csrcDenomination}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入充值卡的面额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****面额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrcDenomination">${csRechargeCard.csrcDenomination}</textarea>
		 		<span>
		 	
			 ${csRechargeCard.csrcDenomination$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrcDenomination}
	</s:if>
	
	${lz:set("注释","*****************分类字段的输入框代码*****************")}
	${lz:set("注释","before$csrcMethod和after$csrcMethod变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrcMethod==true">
	${before$csrcMethod}
	<dl class="csrcMethod " major  ref="csrcMethod" >
		<dt>分　　类:</dt>
		<s:if test="#request.CTRL.e.csrcMethod==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRechargeCard$csrcMethod!=null">${csRechargeCard$csrcMethod}</s:if><s:else>
		 	<select class="narrow" id="csrcMethod" name="csRechargeCard.csrcMethod">
		 		<option value="">请选择</option>
 					${lz:set("items", get:propertys("充值卡分类","rcgcard_method"))}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.spId}" ${csRechargeCard.csrcMethod==item.spId?"selected":""}>${item.spName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrcMethod').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?key='+$('#csrcMethod').val(),{ctrl:{editable:false}})"></a>
	 			<a class="reladd" config="{
	 				action:'${basePath}${proname}/configurator/property_edit.do?rd=1&parent=${get:property("rcgcard_method").spId}'
	 				,callback:function(object){
	 					$('<option selected value='+object.spId+'>'+object.spName+'</option>').appendTo('#csrcMethod');
	 				}
	 			}" href="javascript:void(0);"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择充值卡的分类</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****分类字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrcMethod">${csRechargeCard.csrcMethod}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${csRechargeCard.csrcMethod}',{ctrl:{editable:false,visible:true}})">
			 ${csRechargeCard.csrcMethod$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrcMethod}
	</s:if>
	
	${lz:set("注释","*****************内容有效期字段的输入框代码*****************")}
	${lz:set("注释","before$csrcValidity和after$csrcValidity变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrcValidity==true">
	${before$csrcValidity}
	<dl class="csrcValidity " major  ref="csrcValidity" >
		<dt>内容有效期:</dt>
		<s:if test="#request.CTRL.e.csrcValidity==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRechargeCard$csrcValidity!=null">${csRechargeCard$csrcValidity}</s:if><s:else>
		 	<select class="narrow" id="csrcValidity" name="csRechargeCard.csrcValidity">
		 		<option value="">请选择</option>
				<option value="1" ${csRechargeCard.csrcValidity==1?"selected":""}>1个月</option>
				<option value="2" ${csRechargeCard.csrcValidity==2?"selected":""}>2个月</option>
				<option value="3" ${csRechargeCard.csrcValidity==3?"selected":""}>3个月</option>
				<option value="6" ${csRechargeCard.csrcValidity==6?"selected":""}>半年</option>
				<option value="12" ${csRechargeCard.csrcValidity==12?"selected":""}>1年</option>
				<option value="0" ${csRechargeCard.csrcValidity==0?"selected":""}>自定义</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>指明该充值卡对应的内容的有效期时长</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****内容有效期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrcValidity">${csRechargeCard.csrcValidity}</textarea>
		 		<span>
		 	
			 ${csRechargeCard.csrcValidity$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrcValidity}
	</s:if>
	
	${lz:set("注释","*****************内容有效至字段的输入框代码*****************")}
	${lz:set("注释","before$csrcValidityUtil和after$csrcValidityUtil变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrcValidityUtil==true">
	${before$csrcValidityUtil}
	<dl class="csrcValidityUtil " major  ref="csrcValidityUtil" >
		<dt>内容有效至:</dt>
		<s:if test="#request.CTRL.e.csrcValidityUtil==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csRechargeCard$csrcValidityUtil!=null">${csRechargeCard$csrcValidityUtil}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csRechargeCard.csrcValidityUtil" id="csrcValidityUtil"  value="<s:date name="csRechargeCard.csrcValidityUtil" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择充值卡的内容有效至</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****内容有效至字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrcValidityUtil">${csRechargeCard.csrcValidityUtil}</textarea>
		 		<span>
		 	
			 ${csRechargeCard.csrcValidityUtil$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrcValidityUtil}
	</s:if>
	
	${lz:set("注释","*****************充值有效至字段的输入框代码*****************")}
	${lz:set("注释","before$csrcUtil和after$csrcUtil变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrcUtil==true">
	${before$csrcUtil}
	<dl class="csrcUtil " major  ref="csrcUtil" >
		<dt>充值有效至:</dt>
		<s:if test="#request.CTRL.e.csrcUtil==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csRechargeCard$csrcUtil!=null">${csRechargeCard$csrcUtil}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csRechargeCard.csrcUtil" id="csrcUtil"  value="<s:date name="csRechargeCard.csrcUtil" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>指明该充值卡的有效时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****充值有效至字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrcUtil">${csRechargeCard.csrcUtil}</textarea>
		 		<span>
		 	
			 ${csRechargeCard.csrcUtil$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrcUtil}
	</s:if>
	
	${lz:set("注释","*****************使用会员字段的输入框代码*****************")}
	${lz:set("注释","before$csrcMember和after$csrcMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrcMember==true">
	${before$csrcMember}
	<dl class="csrcMember " major  ref="csrcMember" >
		<dt>使用会员:</dt>
		<s:if test="#request.CTRL.e.csrcMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csRechargeCard$csrcMember!=null">${csRechargeCard$csrcMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={csrcHost}" type="text" id="csrcMember" name="csRechargeCard.csrcMember" text="${get:CsMember(csRechargeCard.csrcMember).csmName}" value="${csRechargeCard.csrcMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrcMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csrcMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrcMember">${csRechargeCard.csrcMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csRechargeCard.csrcMember}',{ctrl:{editable:false,visible:true}})">
			 ${csRechargeCard.csrcMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrcMember}
	</s:if>
	
	${lz:set("注释","*****************使用时间字段的输入框代码*****************")}
	${lz:set("注释","before$csrcUseTime和after$csrcUseTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrcUseTime==true">
	${before$csrcUseTime}
	<dl class="csrcUseTime " major  ref="csrcUseTime" >
		<dt>使用时间:</dt>
		<s:if test="#request.CTRL.e.csrcUseTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csRechargeCard$csrcUseTime!=null">${csRechargeCard$csrcUseTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csRechargeCard.csrcUseTime" id="csrcUseTime"  value="<s:date name="csRechargeCard.csrcUseTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择充值卡的使用时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****使用时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrcUseTime">${csRechargeCard.csrcUseTime}</textarea>
		 		<span>
		 	
			 ${csRechargeCard.csrcUseTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrcUseTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csrcAddTime和after$csrcAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrcAddTime==true">
	${before$csrcAddTime}
	<dl class="csrcAddTime " major  ref="csrcAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csrcAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csRechargeCard$csrcAddTime!=null">${csRechargeCard$csrcAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csRechargeCard.csrcAddTime" id="csrcAddTime"  value="<s:date name="csRechargeCard.csrcAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择充值卡的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrcAddTime">${csRechargeCard.csrcAddTime}</textarea>
		 		<span>
		 	
			 ${csRechargeCard.csrcAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrcAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csrcStatus和after$csrcStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrcStatus==true">
	${before$csrcStatus}
	<dl class="csrcStatus " major  ref="csrcStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csrcStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRechargeCard$csrcStatus!=null">${csRechargeCard$csrcStatus}</s:if><s:else>
		 	<select class="narrow" id="csrcStatus" name="csRechargeCard.csrcStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csRechargeCard.csrcStatus==1?"selected":""}>库存</option>
				<option value="2" ${csRechargeCard.csrcStatus==2?"selected":""}>出库(发出)</option>
				<option value="3" ${csRechargeCard.csrcStatus==3?"selected":""}>已使用</option>
				<option value="0" ${csRechargeCard.csrcStatus==0?"selected":""}>无效卡</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择充值卡的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrcStatus">${csRechargeCard.csrcStatus}</textarea>
		 		<span>
		 	
			 ${csRechargeCard.csrcStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrcStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csRechargeCard.csrcId}" name="csRechargeCard.csrcId" id="csrcId" />
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