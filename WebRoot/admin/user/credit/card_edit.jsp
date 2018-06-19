<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/user/credit/card.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/user/credit/card.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csCreditCard.csccId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csccId,csccNo,csccMember,csccImage,csccStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csccId,csccNo,csccMember,csccImage,csccStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csccId,csccNo,csccMember,csccImage,csccStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csccId,csccNo,csccMember,csccImage,csccStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"信用卡编辑":CTRL.title}</title>
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
	$("#cardForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csccId==true">
		${lz:set("haveEditable",true)}
		,"csCreditCard.csccId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csccNo==true">
		${lz:set("haveEditable",true)}
		,"csCreditCard.csccNo":function(el){
			if(jQuery.trim(el.value)=="")
				return "信用卡号不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "信用卡号最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("card_query.do",{exists:true,csccNo:el.value});
				if(exists && exists.length>0){
					if(${csCreditCard.csccId==null} || exists[0].value!=$("#csccId").val())
						return "信用卡号已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csccMember==true">
		${lz:set("haveEditable",true)}
		,"csCreditCard.csccMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csccLimit==true">
		${lz:set("haveEditable",true)}
		,"csCreditCard.csccLimit":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "信用额度输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csccScope==true">
		${lz:set("haveEditable",true)}
		,"csCreditCard.csccScope":function(el){
			if(jQuery.trim(el.value)=="")
				return "信用分值不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csccType==true">
		${lz:set("haveEditable",true)}
		,"csCreditCard.csccType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择信用卡类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csccRemark==true">
		${lz:set("haveEditable",true)}
		,"csCreditCard.csccRemark":function(el){
			if(el.value.length>500 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为500个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csccData==true">
		${lz:set("haveEditable",true)}
		,"csCreditCard.csccData":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "特殊数据最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csccMask==true">
		${lz:set("haveEditable",true)}
		,"csCreditCard.csccMask":function(el){
				var bitValue = 0;
				$("input[ref='csccMask']").each(function(){
					if($(this).is(":checked"))
						bitValue|=$(this).val();
				});
				$("#csccMask").val(bitValue);
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csccUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csCreditCard.csccUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csccAddTime==true">
		${lz:set("haveEditable",true)}
		,"csCreditCard.csccAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csccStatus==true">
		${lz:set("haveEditable",true)}
		,"csCreditCard.csccStatus":function(el){
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
	<form class="form editform" ref="CsCreditCard" id="cardForm" name="cardForm" action="${empty CTRL.action?"card_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写信用卡相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csCreditCardToken}" name="csCreditCardToken" id="csCreditCardToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csCreditCard.csccId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csccId和after$csccId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csccId==true">
	${before$csccId}
	<dl class="csccId ${CTRL.e.csccId?"hidden":""}" major  ref="csccId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csccId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csCreditCard$csccId!=null">${csCreditCard$csccId}</s:if><s:else>
			<input type="hidden" value="${csCreditCard.csccId}" name="csCreditCard.csccId" id="csccId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csccId">${csCreditCard.csccId}</textarea>
		 		<span>
		 	
			 ${csCreditCard.csccId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csccId}
	</s:if>
	
	${lz:set("注释","*****************信用卡号字段的输入框代码*****************")}
	${lz:set("注释","before$csccNo和after$csccNo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csccNo==true">
	${before$csccNo}
	<dl class="csccNo " major  ref="csccNo" >
		<dt>信用卡号:</dt>
		<s:if test="#request.CTRL.e.csccNo==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCreditCard$csccNo!=null">${csCreditCard$csccNo}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCreditCard.csccNo" id="csccNo"  value="${csCreditCard.csccNo}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入信用卡的信用卡号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****信用卡号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csccNo">${csCreditCard.csccNo}</textarea>
		 		<span>
		 	
			 ${csCreditCard.csccNo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csccNo}
	</s:if>
	
	${lz:set("注释","*****************所属会员字段的输入框代码*****************")}
	${lz:set("注释","before$csccMember和after$csccMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csccMember==true">
	${before$csccMember}
	<dl class="csccMember " major  ref="csccMember" >
		<dt>所属会员:</dt>
		<s:if test="#request.CTRL.e.csccMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csCreditCard$csccMember!=null">${csCreditCard$csccMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}" type="text" id="csccMember" name="csCreditCard.csccMember" text="${get:CsMember(csCreditCard.csccMember).csmName}" value="${csCreditCard.csccMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csccMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csccMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csccMember">${csCreditCard.csccMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csCreditCard.csccMember}',{ctrl:{editable:false,visible:true}})">
			 ${csCreditCard.csccMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csccMember}
	</s:if>
	
	${lz:set("注释","*****************信用额度字段的输入框代码*****************")}
	${lz:set("注释","before$csccLimit和after$csccLimit变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csccLimit==true">
	${before$csccLimit}
	<dl class="csccLimit " major  ref="csccLimit" >
		<dt>信用额度:</dt>
		<s:if test="#request.CTRL.e.csccLimit==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csCreditCard$csccLimit!=null">${csCreditCard$csccLimit}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csCreditCard.csccLimit" id="csccLimit"  value="${csCreditCard.csccLimit}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入信用卡的信用额度</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****信用额度字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csccLimit">${csCreditCard.csccLimit}</textarea>
		 		<span>
		 	
			 ${csCreditCard.csccLimit$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csccLimit}
	</s:if>
	
	${lz:set("注释","*****************信用分值字段的输入框代码*****************")}
	${lz:set("注释","before$csccScope和after$csccScope变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csccScope==true">
	${before$csccScope}
	<dl class="csccScope " major  ref="csccScope" >
		<dt>信用分值:</dt>
		<s:if test="#request.CTRL.e.csccScope==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csCreditCard$csccScope!=null">${csCreditCard$csccScope}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csCreditCard.csccScope" id="csccScope"  value="${csCreditCard.csccScope}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入信用卡的信用分值</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****信用分值字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csccScope">${csCreditCard.csccScope}</textarea>
		 		<span>
		 	
			 ${csCreditCard.csccScope$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csccScope}
	</s:if>
	
	${lz:set("注释","*****************信用卡类型字段的输入框代码*****************")}
	${lz:set("注释","before$csccType和after$csccType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csccType==true">
	${before$csccType}
	<dl class="csccType " major  ref="csccType" >
		<dt>信用卡类型:</dt>
		<s:if test="#request.CTRL.e.csccType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCreditCard$csccType!=null">${csCreditCard$csccType}</s:if><s:else>
		 	<select class="narrow" id="csccType" name="csCreditCard.csccType">
		 		<option value="">请选择</option>
				<option value="1" ${csCreditCard.csccType==1?"selected":""}>车纷享信用卡</option>
				<option value="3" ${csCreditCard.csccType==3?"selected":""}>支付宝信用卡</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择信用卡的信用卡类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****信用卡类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csccType">${csCreditCard.csccType}</textarea>
		 		<span>
		 	
			 ${csCreditCard.csccType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csccType}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$csccRemark和after$csccRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csccRemark==true">
	${before$csccRemark}
	<dl class="csccRemark " minor  ref="csccRemark" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.csccRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csCreditCard$csccRemark!=null">${csCreditCard$csccRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csccRemark" name="csCreditCard.csccRemark" rows="5">${csCreditCard.csccRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入信用卡的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csCreditCard.csccRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csccRemark}
	</s:if>
	
	${lz:set("注释","*****************特殊数据字段的输入框代码*****************")}
	${lz:set("注释","before$csccData和after$csccData变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csccData==true">
	${before$csccData}
	<dl class="csccData " minor  ref="csccData" style="width:98%;">
		<dt>特殊数据:</dt>
		<s:if test="#request.CTRL.e.csccData==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csCreditCard$csccData!=null">${csCreditCard$csccData}</s:if><s:else>
		 	<textarea class="input wide"  id="csccData" name="csCreditCard.csccData" rows="5">${csCreditCard.csccData}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入信用卡的特殊数据</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****特殊数据字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csCreditCard.csccData$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csccData}
	</s:if>
	
	${lz:set("注释","*****************掩码标识字段的输入框代码*****************")}
	${lz:set("注释","before$csccMask和after$csccMask变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csccMask==true">
	${before$csccMask}
	<dl class="csccMask " major  ref="csccMask" style="width:98%;">
		<dt>掩码标识:</dt>
		<s:if test="#request.CTRL.e.csccMask==true">
		${lz:set("haveEditable",true)}
		<dd input="checkbox">
		<s:if test="#request.csCreditCard$csccMask!=null">${csCreditCard$csccMask}</s:if><s:else>
		 	<div class='wide input'>	
		 	&nbsp; 	
		 	<input type="hidden" value="#0#" name="csCreditCard.csccMask" id="csccMask"/>
			<label><input type="checkbox" ${lz:bitin(csCreditCard.csccMask,1) ? "checked='checked'" : ""} value="1" ref="csccMask"/>已回流支付宝&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:bitin(csCreditCard.csccMask,2) ? "checked='checked'" : ""} value="2" ref="csccMask"/>支付宝风险名单用户&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:bitin(csCreditCard.csccMask,3) ? "checked='checked'" : ""} value="3" ref="csccMask"/>保留&nbsp;&nbsp;&nbsp;</label>
		 </div>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择信用卡的掩码标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****掩码标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csccMask">${csCreditCard.csccMask}</textarea>
		 		<span>
		 	
			 ${csCreditCard.csccMask$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csccMask}
	</s:if>
	
	${lz:set("注释","*****************信用卡图片字段的输入框代码*****************")}
	${lz:set("注释","before$csccImage和after$csccImage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csccImage==true">
	${before$csccImage}
	<dl class="csccImage " minor  ref="csccImage" style="width:98%;">
		<dt>信用卡图片:</dt>
		<s:if test="#request.CTRL.e.csccImage==true">
		${lz:set("haveEditable",true)}
		<dd input="image">
		<s:if test="#request.csCreditCard$csccImage!=null">${csCreditCard$csccImage}</s:if><s:else>
		    <input type="text" class="input" maxlength="128" size="32" name="csCreditCard.csccImage" id="csccImage"  value="${csCreditCard.csccImage}"/>
			<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#csccImage').val(url)}})" class="button">设置图片</button>
			<button type="button" onclick="$('#csccImage').val('')" class="button">删除图片</button>
			<button type="button" onclick="$.thumb({url:$('#csccImage').val()})" class="button">查看图片</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传会员信息的证件图片</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****证件图片字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csccImage">${csCreditCard.csccImage}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(csCreditCard.csccImage))}
		 	<s:if test="#request.isVacant==false">
		 	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="${csCreditCard.csccImage}"/>
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csccImage}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csccUpdateTime和after$csccUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csccUpdateTime==true">
	${before$csccUpdateTime}
	<dl class="csccUpdateTime " major  ref="csccUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csccUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCreditCard$csccUpdateTime!=null">${csCreditCard$csccUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCreditCard.csccUpdateTime" id="csccUpdateTime"  value="<s:date name="csCreditCard.csccUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择信用卡的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csccUpdateTime">${csCreditCard.csccUpdateTime}</textarea>
		 		<span>
		 	
			 ${csCreditCard.csccUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csccUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csccAddTime和after$csccAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csccAddTime==true">
	${before$csccAddTime}
	<dl class="csccAddTime " major  ref="csccAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csccAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCreditCard$csccAddTime!=null">${csCreditCard$csccAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCreditCard.csccAddTime" id="csccAddTime"  value="<s:date name="csCreditCard.csccAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择信用卡的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csccAddTime">${csCreditCard.csccAddTime}</textarea>
		 		<span>
		 	
			 ${csCreditCard.csccAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csccAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csccStatus和after$csccStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csccStatus==true">
	${before$csccStatus}
	<dl class="csccStatus " major  ref="csccStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csccStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCreditCard$csccStatus!=null">${csCreditCard$csccStatus}</s:if><s:else>
		 	<select class="narrow" id="csccStatus" name="csCreditCard.csccStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csCreditCard.csccStatus==0?"selected":""}>未认证</option>
				<option value="1" ${csCreditCard.csccStatus==1?"selected":""}>已认证</option>
				<option value="2" ${csCreditCard.csccStatus==2?"selected":""}>等待认证</option>
				<option value="3" ${csCreditCard.csccStatus==3?"selected":""}>认证失败</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择信用卡的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csccStatus">${csCreditCard.csccStatus}</textarea>
		 		<span>
		 	
			 ${csCreditCard.csccStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csccStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csCreditCard.csccId}" name="csCreditCard.csccId" id="csccId" />
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