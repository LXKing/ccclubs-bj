<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/configurator/oper.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/configurator/oper.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csOper.csoId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csoId,csoName,csoFullName,csoIcon,csoSign,csoPerson,csoMoney,csoAddress,csoContacts,csoMemo,csoStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csoId,csoName,csoFullName,csoIcon,csoSign,csoPerson,csoMoney,csoAddress,csoContacts,csoMemo,csoStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csoId,csoName,csoFullName,csoIcon,csoSign,csoPerson,csoMoney,csoAddress,csoContacts,csoMemo,csoStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csoId,csoName,csoFullName,csoIcon,csoSign,csoPerson,csoMoney,csoAddress,csoContacts,csoAdder,csoMemo,csoStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"运营商编辑":CTRL.title}</title>
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
	$("#operForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csoId==true">
		${lz:set("haveEditable",true)}
		,"csOper.csoId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoName==true">
		${lz:set("haveEditable",true)}
		,"csOper.csoName":function(el){
			if(jQuery.trim(el.value)=="")
				return "运营商名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "运营商名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoFullName==true">
		${lz:set("haveEditable",true)}
		,"csOper.csoFullName":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "公司全称最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoIcon==true">
		${lz:set("haveEditable",true)}
		,"csOper.csoIcon":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoSign==true">
		${lz:set("haveEditable",true)}
		,"csOper.csoSign":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "短信签名最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoPerson==true">
		${lz:set("haveEditable",true)}
		,"csOper.csoPerson":function(el){
			if(jQuery.trim(el.value)=="")
				return "负责人不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "负责人最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoMoney==true">
		${lz:set("haveEditable",true)}
		,"csOper.csoMoney":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "帐户资金输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoAddress==true">
		${lz:set("haveEditable",true)}
		,"csOper.csoAddress":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "详细地址最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoContacts==true">
		${lz:set("haveEditable",true)}
		,"csOper.csoContacts":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoAdder==true">
		${lz:set("haveEditable",true)}
		,"csOper.csoAdder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoRemark==true">
		${lz:set("haveEditable",true)}
		,"csOper.csoRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoMemo==true">
		${lz:set("haveEditable",true)}
		,"csOper.csoMemo":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "备忘信息最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoData==true">
		${lz:set("haveEditable",true)}
		,"csOper.csoData":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "程序数据最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csOper.csoUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoAddTime==true">
		${lz:set("haveEditable",true)}
		,"csOper.csoAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoStatus==true">
		${lz:set("haveEditable",true)}
		,"csOper.csoStatus":function(el){
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
	<form class="form editform" ref="CsOper" id="operForm" name="operForm" action="${empty CTRL.action?"oper_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写运营商相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csOperToken}" name="csOperToken" id="csOperToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csOper.csoId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csoId和after$csoId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoId==true">
	${before$csoId}
	<dl class="csoId ${CTRL.e.csoId?"hidden":""}" major  ref="csoId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csoId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csOper$csoId!=null">${csOper$csoId}</s:if><s:else>
			<input type="hidden" value="${csOper.csoId}" name="csOper.csoId" id="csoId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoId">${csOper.csoId}</textarea>
		 		<span>
		 	
			 ${csOper.csoId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoId}
	</s:if>
	
	${lz:set("注释","*****************运营商名称字段的输入框代码*****************")}
	${lz:set("注释","before$csoName和after$csoName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoName==true">
	${before$csoName}
	<dl class="csoName " major  ref="csoName" >
		<dt>运营商名称:</dt>
		<s:if test="#request.CTRL.e.csoName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOper$csoName!=null">${csOper$csoName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOper.csoName" id="csoName"  value="${csOper.csoName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入运营商的运营商名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****运营商名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoName">${csOper.csoName}</textarea>
		 		<span>
		 	
			 ${csOper.csoName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoName}
	</s:if>
	
	${lz:set("注释","*****************公司全称字段的输入框代码*****************")}
	${lz:set("注释","before$csoFullName和after$csoFullName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoFullName==true">
	${before$csoFullName}
	<dl class="csoFullName " major  ref="csoFullName" style="width:98%;">
		<dt>公司全称:</dt>
		<s:if test="#request.CTRL.e.csoFullName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOper$csoFullName!=null">${csOper$csoFullName}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csOper.csoFullName" id="csoFullName"  value="${csOper.csoFullName}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入运营商的公司全称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****公司全称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csoFullName">${csOper.csoFullName}</textarea>
		 		<span>
		 	
			 ${csOper.csoFullName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoFullName}
	</s:if>
	
	${lz:set("注释","*****************企业标识字段的输入框代码*****************")}
	${lz:set("注释","before$csoIcon和after$csoIcon变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoIcon==true">
	${before$csoIcon}
	<dl class="csoIcon " minor  ref="csoIcon" style="width:98%;">
		<dt>企业标识:</dt>
		<s:if test="#request.CTRL.e.csoIcon==true">
		${lz:set("haveEditable",true)}
		<dd input="image">
		<s:if test="#request.csOper$csoIcon!=null">${csOper$csoIcon}</s:if><s:else>
		    <input type="text" class="input" maxlength="128" size="32" name="csOper.csoIcon" id="csoIcon"  value="${csOper.csoIcon}"/>
			<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#csoIcon').val(url)}})" class="button">设置图片</button>
			<button type="button" onclick="$('#csoIcon').val('')" class="button">删除图片</button>
			<button type="button" onclick="$.thumb({url:$('#csoIcon').val()})" class="button">查看图片</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传运营商的企业标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****企业标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csoIcon">${csOper.csoIcon}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(csOper.csoIcon))}
		 	<s:if test="#request.isVacant==false">
		 	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="${csOper.csoIcon}"/>
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoIcon}
	</s:if>
	
	${lz:set("注释","*****************短信签名字段的输入框代码*****************")}
	${lz:set("注释","before$csoSign和after$csoSign变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoSign==true">
	${before$csoSign}
	<dl class="csoSign " minor  ref="csoSign" >
		<dt>短信签名:</dt>
		<s:if test="#request.CTRL.e.csoSign==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOper$csoSign!=null">${csOper$csoSign}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOper.csoSign" id="csoSign"  value="${csOper.csoSign}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入运营商的短信签名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****短信签名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoSign">${csOper.csoSign}</textarea>
		 		<span>
		 	
			 ${csOper.csoSign$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoSign}
	</s:if>
	
	${lz:set("注释","*****************负责人字段的输入框代码*****************")}
	${lz:set("注释","before$csoPerson和after$csoPerson变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoPerson==true">
	${before$csoPerson}
	<dl class="csoPerson " major  ref="csoPerson" >
		<dt>负&nbsp;&nbsp;责&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csoPerson==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOper$csoPerson!=null">${csOper$csoPerson}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOper.csoPerson" id="csoPerson"  value="${csOper.csoPerson}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入运营商的负责人</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****负责人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoPerson">${csOper.csoPerson}</textarea>
		 		<span>
		 	
			 ${csOper.csoPerson$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoPerson}
	</s:if>
	
	${lz:set("注释","*****************帐户资金字段的输入框代码*****************")}
	${lz:set("注释","before$csoMoney和after$csoMoney变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoMoney==true">
	${before$csoMoney}
	<dl class="csoMoney " major  ref="csoMoney" >
		<dt>帐户资金:</dt>
		<s:if test="#request.CTRL.e.csoMoney==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csOper$csoMoney!=null">${csOper$csoMoney}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOper.csoMoney" id="csoMoney"  value="${csOper.csoMoney}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入运营商的帐户资金</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****帐户资金字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoMoney">${csOper.csoMoney}</textarea>
		 		<span>
		 	
			 ${csOper.csoMoney$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoMoney}
	</s:if>
	
	${lz:set("注释","*****************详细地址字段的输入框代码*****************")}
	${lz:set("注释","before$csoAddress和after$csoAddress变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoAddress==true">
	${before$csoAddress}
	<dl class="csoAddress " major  ref="csoAddress" style="width:98%;">
		<dt>详细地址:</dt>
		<s:if test="#request.CTRL.e.csoAddress==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csOper$csoAddress!=null">${csOper$csoAddress}</s:if><s:else>
		 	<textarea class="input wide"  id="csoAddress" name="csOper.csoAddress" rows="5">${csOper.csoAddress}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入运营商的详细地址</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****详细地址字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csOper.csoAddress$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoAddress}
	</s:if>
	
	${lz:set("注释","*****************联系信息字段的输入框代码*****************")}
	${lz:set("注释","before$csoContacts和after$csoContacts变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoContacts==true">
	${before$csoContacts}
	<dl class="csoContacts " major  ref="csoContacts" style="width:98%;">
		<dt>联系信息:</dt>
		<s:if test="#request.CTRL.e.csoContacts==true">
		${lz:set("haveEditable",true)}
		<dd input="grid">
		<s:if test="#request.csOper$csoContacts!=null">${csOper$csoContacts}</s:if><s:else>
		    <textarea class="input wide grid" grid="[
		    	['姓名',100,'text','name',null]
		    	,['手机',100,'text','mobile',null]
		    	,['邮箱',100,'text','email',null]
		    	,['其它信息',null,'text','other',null]
		    ]" id="csoContacts" name="csOper.csoContacts">${csOper.csoContacts}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****联系信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csoContacts">${csOper.csoContacts}</textarea>
		 		<span>
		 	
			 ${csOper.csoContacts$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoContacts}
	</s:if>
	
	${lz:set("注释","*****************添加人字段的输入框代码*****************")}
	${lz:set("注释","before$csoAdder和after$csoAdder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoAdder==true">
	${before$csoAdder}
	<dl class="csoAdder " major  ref="csoAdder" >
		<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csoAdder==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOper$csoAdder!=null">${csOper$csoAdder}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csoAdder" name="csOper.csoAdder">
		 				<option selected value="${csOper.csoAdder}">
		 					${get:SrvUser(csOper.csoAdder).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csoAdder').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csoAdder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoAdder">${csOper.csoAdder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csOper.csoAdder}',{ctrl:{editable:false,visible:true}})">
			 ${csOper.csoAdder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoAdder}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$csoRemark和after$csoRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoRemark==true">
	${before$csoRemark}
	<dl class="csoRemark " major  ref="csoRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.csoRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csOper$csoRemark!=null">${csOper$csoRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csoRemark" name="csOper.csoRemark" rows="5">${csOper.csoRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入运营商的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csOper.csoRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoRemark}
	</s:if>
	
	${lz:set("注释","*****************备忘信息字段的输入框代码*****************")}
	${lz:set("注释","before$csoMemo和after$csoMemo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoMemo==true">
	${before$csoMemo}
	<dl class="csoMemo " minor  ref="csoMemo" style="width:98%;">
		<dt>备忘信息:</dt>
		<s:if test="#request.CTRL.e.csoMemo==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csOper$csoMemo!=null">${csOper$csoMemo}</s:if><s:else>
		 	<textarea class="input wide"  id="csoMemo" name="csOper.csoMemo" rows="5">${csOper.csoMemo}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入运营商的备忘信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备忘信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csOper.csoMemo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoMemo}
	</s:if>
	
	${lz:set("注释","*****************程序数据字段的输入框代码*****************")}
	${lz:set("注释","before$csoData和after$csoData变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoData==true">
	${before$csoData}
	<dl class="csoData " minor  ref="csoData" style="width:98%;">
		<dt>程序数据:</dt>
		<s:if test="#request.CTRL.e.csoData==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csOper$csoData!=null">${csOper$csoData}</s:if><s:else>
		 	<textarea class="input wide"  id="csoData" name="csOper.csoData" rows="5">${csOper.csoData}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入运营商的程序数据</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****程序数据字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csOper.csoData$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoData}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csoUpdateTime和after$csoUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoUpdateTime==true">
	${before$csoUpdateTime}
	<dl class="csoUpdateTime " major  ref="csoUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csoUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOper$csoUpdateTime!=null">${csOper$csoUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOper.csoUpdateTime" id="csoUpdateTime"  value="<s:date name="csOper.csoUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择运营商的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoUpdateTime">${csOper.csoUpdateTime}</textarea>
		 		<span>
		 	
			 ${csOper.csoUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csoAddTime和after$csoAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoAddTime==true">
	${before$csoAddTime}
	<dl class="csoAddTime " major  ref="csoAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csoAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOper$csoAddTime!=null">${csOper$csoAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOper.csoAddTime" id="csoAddTime"  value="<s:date name="csOper.csoAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择运营商的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoAddTime">${csOper.csoAddTime}</textarea>
		 		<span>
		 	
			 ${csOper.csoAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csoStatus和after$csoStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoStatus==true">
	${before$csoStatus}
	<dl class="csoStatus " major  ref="csoStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csoStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOper$csoStatus!=null">${csOper$csoStatus}</s:if><s:else>
		 	<select class="narrow" id="csoStatus" name="csOper.csoStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csOper.csoStatus==1?"selected":""}>正常</option>
				<option value="0" ${csOper.csoStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择运营商的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoStatus">${csOper.csoStatus}</textarea>
		 		<span>
		 	
			 ${csOper.csoStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csOper.csoId}" name="csOper.csoId" id="csoId" />
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
<script>
window["lzFlashUrl"]="${basePath}admin/flash/";
window["uploadUrl"]="${basePath}admin/upload/upload.jsp";
</script>
<!------------------------LAZY3Q_JSP_BOTTOM------------------------>


</body>
</html>