<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/finance/alipay/alipayrefund.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/finance/alipay/alipayrefund.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csAlipayRefund.csarId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csarHost,csarBankType,csarMember,csarRealName,csarAmount,csarRefund,csarRecord,csarAlipayNo",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csarId,csarHost,csarBankType,csarTradeNo,csarMember,csarRealName,csarAmount,csarRefund,csarRecord,csarAlipayNo,csarStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csarHost,csarBankType,csarMember,csarRealName,csarAmount,csarRefund,csarRecord,csarAlipayNo",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csarId,csarHost,csarBankType,csarTradeNo,csarMember,csarRealName,csarAmount,csarRefund,csarRecord,csarAlipayNo,csarStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"接口退款编辑":CTRL.title}</title>
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
	$("#alipayrefundForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csarId==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRefund.csarId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarHost==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRefund.csarHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarBankType==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRefund.csarBankType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择接口类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarTradeNo==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRefund.csarTradeNo":function(el){
			if(jQuery.trim(el.value)=="")
				return "退款批次号不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "退款批次号最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarMember==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRefund.csarMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarRealName==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRefund.csarRealName":function(el){
			if(jQuery.trim(el.value)=="")
				return "会员姓名不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "会员姓名最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarAmount==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRefund.csarAmount":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "退款金额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarRefund==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRefund.csarRefund":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarRecord==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRefund.csarRecord":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarAlipayNo==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRefund.csarAlipayNo":function(el){
			if(jQuery.trim(el.value)=="")
				return "备注信息不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRefund.csarUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarAddTime==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRefund.csarAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarStatus==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRefund.csarStatus":function(el){
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
	<form class="form editform" ref="CsAlipayRefund" id="alipayrefundForm" name="alipayrefundForm" action="${empty CTRL.action?"alipayrefund_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写接口退款相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csAlipayRefundToken}" name="csAlipayRefundToken" id="csAlipayRefundToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csAlipayRefund.csarId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csarId和after$csarId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarId==true">
	${before$csarId}
	<dl class="csarId ${CTRL.e.csarId?"hidden":""}" major  ref="csarId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csarId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csAlipayRefund$csarId!=null">${csAlipayRefund$csarId}</s:if><s:else>
			<input type="hidden" value="${(csAlipayRefund.csarId)}" name="csAlipayRefund.csarId" id="csarId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarId">${csAlipayRefund.csarId}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayRefund.csarId$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarId}
	</s:if>
	
	${lz:set("注释","*****************运营地区字段的输入框代码*****************")}
	${lz:set("注释","before$csarHost和after$csarHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarHost==true">
	${before$csarHost}
	<dl class="csarHost " major  ref="csarHost" >
		<dt>运营地区:</dt>
		<s:if test="#request.CTRL.e.csarHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csAlipayRefund$csarHost!=null">${csAlipayRefund$csarHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csarHost" name="csAlipayRefund.csarHost">
		 				<option selected value="${(csAlipayRefund.csarHost)}">
		 					${get:SrvHost(csAlipayRefund.csarHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csarHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csarHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****运营地区字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarHost">${csAlipayRefund.csarHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${(csAlipayRefund.csarHost)}',{ctrl:{editable:false,visible:true}})">
			 ${lz:html(csAlipayRefund.csarHost$)}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarHost}
	</s:if>
	
	${lz:set("注释","*****************接口类型字段的输入框代码*****************")}
	${lz:set("注释","before$csarBankType和after$csarBankType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarBankType==true">
	${before$csarBankType}
	<dl class="csarBankType " major  ref="csarBankType" >
		<dt>接口类型:</dt>
		<s:if test="#request.CTRL.e.csarBankType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csAlipayRefund$csarBankType!=null">${csAlipayRefund$csarBankType}</s:if><s:else>
		 	<select class="narrow" id="csarBankType" name="csAlipayRefund.csarBankType">
		 		<option value="">请选择</option>
				<option value="0" ${csAlipayRefund.csarBankType==0?"selected":""}>支付宝</option>
				<option value="10" ${csAlipayRefund.csarBankType==10?"selected":""}>招商银行</option>
				<option value="21" ${csAlipayRefund.csarBankType==21?"selected":""}>支付宝转帐</option>
				<option value="22" ${csAlipayRefund.csarBankType==22?"selected":""}>银行卡转帐</option>
				<option value="23" ${csAlipayRefund.csarBankType==23?"selected":""}>邮局汇款</option>
				<option value="24" ${csAlipayRefund.csarBankType==24?"selected":""}>现金退款</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择接口退款的接口类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****接口类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarBankType">${csAlipayRefund.csarBankType}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayRefund.csarBankType$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarBankType}
	</s:if>
	
	${lz:set("注释","*****************退款批次号字段的输入框代码*****************")}
	${lz:set("注释","before$csarTradeNo和after$csarTradeNo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarTradeNo==true">
	${before$csarTradeNo}
	<dl class="csarTradeNo " major  ref="csarTradeNo" >
		<dt>退款批次号:</dt>
		<s:if test="#request.CTRL.e.csarTradeNo==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csAlipayRefund$csarTradeNo!=null">${csAlipayRefund$csarTradeNo}</s:if><s:else>
		 	<input type="text" class="input narrow"   name="csAlipayRefund.csarTradeNo" id="csarTradeNo"  value="${(csAlipayRefund.csarTradeNo)}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入接口退款的退款批次号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****退款批次号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarTradeNo">${csAlipayRefund.csarTradeNo}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayRefund.csarTradeNo$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarTradeNo}
	</s:if>
	
	${lz:set("注释","*****************退款会员字段的输入框代码*****************")}
	${lz:set("注释","before$csarMember和after$csarMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarMember==true">
	${before$csarMember}
	<dl class="csarMember " major  ref="csarMember" >
		<dt>退款会员:</dt>
		<s:if test="#request.CTRL.e.csarMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csAlipayRefund$csarMember!=null">${csAlipayRefund$csarMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csarHost}" type="text" id="csarMember" name="csAlipayRefund.csarMember" text="${get:CsMember(csAlipayRefund.csarMember).csmName}" value="${(csAlipayRefund.csarMember)}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csarMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csarMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****退款会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarMember">${csAlipayRefund.csarMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${(csAlipayRefund.csarMember)}',{ctrl:{editable:false,visible:true}})">
			 ${lz:html(csAlipayRefund.csarMember$)}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarMember}
	</s:if>
	
	${lz:set("注释","*****************会员姓名字段的输入框代码*****************")}
	${lz:set("注释","before$csarRealName和after$csarRealName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarRealName==true">
	${before$csarRealName}
	<dl class="csarRealName " major  ref="csarRealName" >
		<dt>会员姓名:</dt>
		<s:if test="#request.CTRL.e.csarRealName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csAlipayRefund$csarRealName!=null">${csAlipayRefund$csarRealName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csAlipayRefund.csarRealName" id="csarRealName"  value="${(csAlipayRefund.csarRealName)}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入接口退款的会员姓名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****会员姓名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarRealName">${csAlipayRefund.csarRealName}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayRefund.csarRealName$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarRealName}
	</s:if>
	
	${lz:set("注释","*****************退款金额字段的输入框代码*****************")}
	${lz:set("注释","before$csarAmount和after$csarAmount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarAmount==true">
	${before$csarAmount}
	<dl class="csarAmount " major  ref="csarAmount" >
		<dt>退款金额:</dt>
		<s:if test="#request.CTRL.e.csarAmount==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csAlipayRefund$csarAmount!=null">${csAlipayRefund$csarAmount}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csAlipayRefund.csarAmount" id="csarAmount"  value="${(csAlipayRefund.csarAmount)}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入接口退款的退款金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****退款金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarAmount">${csAlipayRefund.csarAmount}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayRefund.csarAmount$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarAmount}
	</s:if>
	
	${lz:set("注释","*****************关联退款申请字段的输入框代码*****************")}
	${lz:set("注释","before$csarRefund和after$csarRefund变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarRefund==true">
	${before$csarRefund}
	<dl class="csarRefund " major  ref="csarRefund" >
		<dt>关联退款申请:</dt>
		<s:if test="#request.CTRL.e.csarRefund==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csAlipayRefund$csarRefund!=null">${csAlipayRefund$csarRefund}</s:if><s:else>
		 			<input title="请输入会员退款编号进行查询" class="combox narrow" action="${basePath}${proname}/finance/refund_query.do?value={param}&csrHost={csarHost}" type="text" id="csarRefund" name="csAlipayRefund.csarRefund" text="${get:CsRefund(csAlipayRefund.csarRefund).csrId}" value="${(csAlipayRefund.csarRefund)}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csarRefund').val())==''){return;};window.href('${basePath}${proname}/finance/refund_details.do?key='+$('#csarRefund').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联退款申请字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarRefund">${csAlipayRefund.csarRefund}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/finance/refund_details.do?key=${(csAlipayRefund.csarRefund)}',{ctrl:{editable:false,visible:true}})">
			 ${lz:html(csAlipayRefund.csarRefund$)}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarRefund}
	</s:if>
	
	${lz:set("注释","*****************原接口充值字段的输入框代码*****************")}
	${lz:set("注释","before$csarRecord和after$csarRecord变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarRecord==true">
	${before$csarRecord}
	<dl class="csarRecord " major  ref="csarRecord" >
		<dt>原接口充值:</dt>
		<s:if test="#request.CTRL.e.csarRecord==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csAlipayRefund$csarRecord!=null">${csAlipayRefund$csarRecord}</s:if><s:else>
		 			<input title="请输入接口充值交易号进行查询" class="combox narrow" action="${basePath}${proname}/finance/alipay/alipayrecord_query.do?value={param}&csarHost={csarHost}" type="text" id="csarRecord" name="csAlipayRefund.csarRecord" text="${get:CsAlipayRecord(csAlipayRefund.csarRecord).csarTradeNo}" value="${(csAlipayRefund.csarRecord)}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csarRecord').val())==''){return;};window.href('${basePath}${proname}/finance/alipay/alipayrecord_details.do?key='+$('#csarRecord').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****原接口充值字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarRecord">${csAlipayRefund.csarRecord}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/finance/alipay/alipayrecord_details.do?key=${(csAlipayRefund.csarRecord)}',{ctrl:{editable:false,visible:true}})">
			 ${lz:html(csAlipayRefund.csarRecord$)}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarRecord}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$csarAlipayNo和after$csarAlipayNo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarAlipayNo==true">
	${before$csarAlipayNo}
	<dl class="csarAlipayNo " major  ref="csarAlipayNo" >
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.csarAlipayNo==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csAlipayRefund$csarAlipayNo!=null">${csAlipayRefund$csarAlipayNo}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csAlipayRefund.csarAlipayNo" id="csarAlipayNo"  value="${(csAlipayRefund.csarAlipayNo)}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入接口退款的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarAlipayNo">${csAlipayRefund.csarAlipayNo}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayRefund.csarAlipayNo$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarAlipayNo}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csarUpdateTime和after$csarUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarUpdateTime==true">
	${before$csarUpdateTime}
	<dl class="csarUpdateTime " major  ref="csarUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csarUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csAlipayRefund$csarUpdateTime!=null">${csAlipayRefund$csarUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csAlipayRefund.csarUpdateTime" id="csarUpdateTime"  value="<s:date name="csAlipayRefund.csarUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择接口退款的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarUpdateTime">${csAlipayRefund.csarUpdateTime}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayRefund.csarUpdateTime$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csarAddTime和after$csarAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarAddTime==true">
	${before$csarAddTime}
	<dl class="csarAddTime " major  ref="csarAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csarAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csAlipayRefund$csarAddTime!=null">${csAlipayRefund$csarAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csAlipayRefund.csarAddTime" id="csarAddTime"  value="<s:date name="csAlipayRefund.csarAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择接口退款的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarAddTime">${csAlipayRefund.csarAddTime}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayRefund.csarAddTime$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csarStatus和after$csarStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarStatus==true">
	${before$csarStatus}
	<dl class="csarStatus " major  ref="csarStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csarStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csAlipayRefund$csarStatus!=null">${csAlipayRefund$csarStatus}</s:if><s:else>
		 	<select class="narrow" id="csarStatus" name="csAlipayRefund.csarStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csAlipayRefund.csarStatus==0?"selected":""}>等待</option>
				<option value="1" ${csAlipayRefund.csarStatus==1?"selected":""}>成功</option>
				<option value="2" ${csAlipayRefund.csarStatus==2?"selected":""}>失败</option>
				<option value="3" ${csAlipayRefund.csarStatus==3?"selected":""}>取消</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择接口退款的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarStatus">${csAlipayRefund.csarStatus}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayRefund.csarStatus$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csAlipayRefund.csarId}" name="csAlipayRefund.csarId" id="csarId" />
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