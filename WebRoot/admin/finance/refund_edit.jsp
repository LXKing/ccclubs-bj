<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/finance/refund.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/finance/refund.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csRefund.csrId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csrHost,csrMember,csrAccountNo,csrAccountName,csrMobile,refunds,csrRemark",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csrHost,csrMember,csrAccountNo,csrAccountName,csrMobile,refunds,csrRemark",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csrHost,csrAmount,csrReturnType,csrAccountNo,csrAccountName,csrMobile,refunds,csrCheckTime,csrBackTime,csrEndTime,csrRemark",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csrHost,csrMember,csrAmount,csrReturnType,csrAccountNo,csrAccountName,csrMobile,refunds,csrCheckTime,csrBackTime,csrEndTime,csrRemark",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"会员退款编辑":CTRL.title}</title>
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
<s:if test="#request.CTRL.title=='审核'">
	<%@ include file="refund_check.jsp"%>
</s:if>
<s:elseif test="#request.CTRL.title=='退款'">
	<%@ include file="refund_back.jsp"%>
</s:elseif>
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
	$("#refundForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csrId==true">
		${lz:set("haveEditable",true)}
		,"csRefund.csrId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrHost==true">
		${lz:set("haveEditable",true)}
		,"csRefund.csrHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrMember==true">
		${lz:set("haveEditable",true)}
		,"csRefund.csrMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrAmount==true">
		${lz:set("haveEditable",true)}
		,"csRefund.csrAmount":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "已退金额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrReturnType==true">
		${lz:set("haveEditable",true)}
		,"csRefund.csrReturnType":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrAccountNo==true">
		${lz:set("haveEditable",true)}
		,"csRefund.csrAccountNo":function(el){
			if(jQuery.trim(el.value)=="")
				return "收款帐号不能为空";
			if(el.value.length>64 && el.value.indexOf("[*]")==-1)
				return "收款帐号最大长度为64个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrAccountName==true">
		${lz:set("haveEditable",true)}
		,"csRefund.csrAccountName":function(el){
			if(jQuery.trim(el.value)=="")
				return "收款姓名不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "收款姓名最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrMobile==true">
		${lz:set("haveEditable",true)}
		,"csRefund.csrMobile":function(el){
			if(jQuery.trim(el.value)=="")
				return "手机号码不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "手机号码最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrChecker==true">
		${lz:set("haveEditable",true)}
		,"csRefund.csrChecker":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrBacker==true">
		${lz:set("haveEditable",true)}
		,"csRefund.csrBacker":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrMemo==true">
		${lz:set("haveEditable",true)}
		,"csRefund.csrMemo":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "退款备录最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrAddTime==true">
		${lz:set("haveEditable",true)}
		,"csRefund.csrAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrCheckTime==true">
		${lz:set("haveEditable",true)}
		,"csRefund.csrCheckTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "审核时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrBackTime==true">
		${lz:set("haveEditable",true)}
		,"csRefund.csrBackTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "退款时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrEndTime==true">
		${lz:set("haveEditable",true)}
		,"csRefund.csrEndTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "完成时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrRemark==true">
		${lz:set("haveEditable",true)}
		,"csRefund.csrRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrStatus==true">
		${lz:set("haveEditable",true)}
		,"csRefund.csrStatus":function(el){
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
	<form class="form editform" ref="CsRefund" id="refundForm" name="refundForm" action="${empty CTRL.action?"refund_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写会员退款相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csRefundToken}" name="csRefundToken" id="csRefundToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csRefund.csrId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csrId和after$csrId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrId==true">
	${before$csrId}
	<dl class="csrId ${CTRL.e.csrId?"hidden":""}" major  ref="csrId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csrId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csRefund$csrId!=null">${csRefund$csrId}</s:if><s:else>
			<input type="hidden" value="${(csRefund.csrId)}" name="csRefund.csrId" id="csrId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrId">${csRefund.csrId}</textarea>
		 		<span>
		 	
			 ${lz:html(csRefund.csrId$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrId}
	</s:if>
	
	${lz:set("注释","*****************运营地区字段的输入框代码*****************")}
	${lz:set("注释","before$csrHost和after$csrHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrHost==true">
	${before$csrHost}
	<dl class="csrHost " major  ref="csrHost" >
		<dt>运营地区:</dt>
		<s:if test="#request.CTRL.e.csrHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csRefund$csrHost!=null">${csRefund$csrHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csrHost" name="csRefund.csrHost">
		 				<option selected value="${(csRefund.csrHost)}">
		 					${get:SrvHost(csRefund.csrHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csrHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****运营地区字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrHost">${csRefund.csrHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${(csRefund.csrHost)}',{ctrl:{editable:false,visible:true}})">
			 ${lz:html(csRefund.csrHost$)}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrHost}
	</s:if>
	
	${lz:set("注释","*****************退款会员字段的输入框代码*****************")}
	${lz:set("注释","before$csrMember和after$csrMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrMember==true">
	${before$csrMember}
	<dl class="csrMember " major  ref="csrMember" >
		<dt>退款会员:</dt>
		<s:if test="#request.CTRL.e.csrMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csRefund$csrMember!=null">${csRefund$csrMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csrHost}" type="text" id="csrMember" name="csRefund.csrMember" text="${get:CsMember(csRefund.csrMember).csmName}" value="${(csRefund.csrMember)}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csrMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****退款会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrMember">${csRefund.csrMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${(csRefund.csrMember)}',{ctrl:{editable:false,visible:true}})">
			 ${lz:html(csRefund.csrMember$)}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrMember}
	</s:if>
	
	${lz:set("注释","*****************已退金额字段的输入框代码*****************")}
	${lz:set("注释","before$csrAmount和after$csrAmount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrAmount==true">
	${before$csrAmount}
	<dl class="csrAmount " major  ref="csrAmount" >
		<dt>已退金额:</dt>
		<s:if test="#request.CTRL.e.csrAmount==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csRefund$csrAmount!=null">${csRefund$csrAmount}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csRefund.csrAmount" id="csrAmount"  value="${(csRefund.csrAmount)}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入会员退款的已退金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****已退金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrAmount">${csRefund.csrAmount}</textarea>
		 		<span>
		 	
			 ${lz:html(csRefund.csrAmount$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrAmount}
	</s:if>
	
	${lz:set("注释","*****************退款方式字段的输入框代码*****************")}
	${lz:set("注释","before$csrReturnType和after$csrReturnType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrReturnType==true">
	${before$csrReturnType}
	<dl class="csrReturnType " major  ref="csrReturnType" >
		<dt>退款方式:</dt>
		<s:if test="#request.CTRL.e.csrReturnType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRefund$csrReturnType!=null">${csRefund$csrReturnType}</s:if><s:else>
		 	<select class="narrow" id="csrReturnType" name="csRefund.csrReturnType">
		 		<option value="">请选择</option>
				<option value="0" ${csRefund.csrReturnType==0?"selected":""}>接口退款</option>
				<option value="1" ${csRefund.csrReturnType==1?"selected":""}>支付宝转帐</option>
				<option value="2" ${csRefund.csrReturnType==2?"selected":""}>银行卡转帐</option>
				<option value="3" ${csRefund.csrReturnType==3?"selected":""}>邮局汇款</option>
				<option value="4" ${csRefund.csrReturnType==4?"selected":""}>上门取现</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择会员退款的退款方式</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****退款方式字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrReturnType">${csRefund.csrReturnType}</textarea>
		 		<span>
		 	
			 ${lz:html(csRefund.csrReturnType$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrReturnType}
	</s:if>
	
	${lz:set("注释","*****************收款帐号字段的输入框代码*****************")}
	${lz:set("注释","before$csrAccountNo和after$csrAccountNo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrAccountNo==true">
	${before$csrAccountNo}
	<dl class="csrAccountNo " major  ref="csrAccountNo" style="width:98%;">
		<dt>收款帐号:</dt>
		<s:if test="#request.CTRL.e.csrAccountNo==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csRefund$csrAccountNo!=null">${csRefund$csrAccountNo}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="64" name="csRefund.csrAccountNo" id="csrAccountNo"  value="${(csRefund.csrAccountNo)}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入会员退款的收款帐号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****收款帐号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csrAccountNo">${csRefund.csrAccountNo}</textarea>
		 		<span>
		 	
			 ${lz:html(csRefund.csrAccountNo$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrAccountNo}
	</s:if>
	
	${lz:set("注释","*****************收款姓名字段的输入框代码*****************")}
	${lz:set("注释","before$csrAccountName和after$csrAccountName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrAccountName==true">
	${before$csrAccountName}
	<dl class="csrAccountName " major  ref="csrAccountName" >
		<dt>收款姓名:</dt>
		<s:if test="#request.CTRL.e.csrAccountName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csRefund$csrAccountName!=null">${csRefund$csrAccountName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csRefund.csrAccountName" id="csrAccountName"  value="${(csRefund.csrAccountName)}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入会员退款的收款姓名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****收款姓名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrAccountName">${csRefund.csrAccountName}</textarea>
		 		<span>
		 	
			 ${lz:html(csRefund.csrAccountName$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrAccountName}
	</s:if>
	
	${lz:set("注释","*****************手机号码字段的输入框代码*****************")}
	${lz:set("注释","before$csrMobile和after$csrMobile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrMobile==true">
	${before$csrMobile}
	<dl class="csrMobile " major  ref="csrMobile" >
		<dt>手机号码:</dt>
		<s:if test="#request.CTRL.e.csrMobile==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csRefund$csrMobile!=null">${csRefund$csrMobile}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csRefund.csrMobile" id="csrMobile"  value="${(csRefund.csrMobile)}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入会员退款的手机号码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****手机号码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrMobile">${csRefund.csrMobile}</textarea>
		 		<span>
		 	
			 ${lz:html(csRefund.csrMobile$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrMobile}
	</s:if>
	
	${lz:set("注释","*****************退款明细字段的输入框代码*****************")}
	${lz:set("注释","before$refunds和after$refunds变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.refunds==true">
	${before$refunds}
	<dl class="refunds " major  ref="refunds" style="width:98%;">
		<dt>退款明细:</dt>
		<s:if test="#request.CTRL.e.refunds==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csRefund$refunds!=null">${csRefund$refunds}</s:if><s:else>
		  		<s:if test="csRefund.csrId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="refunds" name="refunds" frameborder='0' src="" url="${basePath}${proname}/finance/alipay/alipayrefund.do?inFrame=true&canQuery=false&fields=csarBankType,csarTradeNo,csarAmount,csarStatus&csarRefund=${csRefund.csrId}&ctrl={queryable:false,fields:{csarRefund:{visible:true,editable:false,defaultValue:${csRefund.csrId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#refunds').attr('src',$('#refunds').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑退款明细
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****退款明细字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csRefund.csrId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="refunds" name="refunds" frameborder='0' src="" url="${basePath}${proname}/finance/alipay/alipayrefund.do?inFrame=true&canQuery=false&fields=csarBankType,csarTradeNo,csarAmount,csarStatus&csarRefund=${csRefund.csrId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#refunds').attr('src',$('#refunds').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$refunds}
	</s:if>
	
	${lz:set("注释","*****************审核人员字段的输入框代码*****************")}
	${lz:set("注释","before$csrChecker和after$csrChecker变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrChecker==true">
	${before$csrChecker}
	<dl class="csrChecker " minor  ref="csrChecker" >
		<dt>审核人员:</dt>
		<s:if test="#request.CTRL.e.csrChecker==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csRefund$csrChecker!=null">${csRefund$csrChecker}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csrChecker" name="csRefund.csrChecker">
		 				<option selected value="${(csRefund.csrChecker)}">
		 					${get:SrvUser(csRefund.csrChecker).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrChecker').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csrChecker').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****审核人员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrChecker">${csRefund.csrChecker}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${(csRefund.csrChecker)}',{ctrl:{editable:false,visible:true}})">
			 ${lz:html(csRefund.csrChecker$)}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrChecker}
	</s:if>
	
	${lz:set("注释","*****************退款人员字段的输入框代码*****************")}
	${lz:set("注释","before$csrBacker和after$csrBacker变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrBacker==true">
	${before$csrBacker}
	<dl class="csrBacker " minor  ref="csrBacker" >
		<dt>退款人员:</dt>
		<s:if test="#request.CTRL.e.csrBacker==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csRefund$csrBacker!=null">${csRefund$csrBacker}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csrBacker" name="csRefund.csrBacker">
		 				<option selected value="${(csRefund.csrBacker)}">
		 					${get:SrvUser(csRefund.csrBacker).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrBacker').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csrBacker').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****退款人员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrBacker">${csRefund.csrBacker}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${(csRefund.csrBacker)}',{ctrl:{editable:false,visible:true}})">
			 ${lz:html(csRefund.csrBacker$)}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrBacker}
	</s:if>
	
	${lz:set("注释","*****************退款备录字段的输入框代码*****************")}
	${lz:set("注释","before$csrMemo和after$csrMemo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrMemo==true">
	${before$csrMemo}
	<dl class="csrMemo " minor  ref="csrMemo" style="width:98%;">
		<dt>退款备录:</dt>
		<s:if test="#request.CTRL.e.csrMemo==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csRefund$csrMemo!=null">${csRefund$csrMemo}</s:if><s:else>
		 	<textarea class="input wide"  id="csrMemo" name="csRefund.csrMemo" rows="5">${(csRefund.csrMemo)}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员退款的退款备录</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****退款备录字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${lz:html(csRefund.csrMemo$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrMemo}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csrAddTime和after$csrAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrAddTime==true">
	${before$csrAddTime}
	<dl class="csrAddTime " major  ref="csrAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csrAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csRefund$csrAddTime!=null">${csRefund$csrAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csRefund.csrAddTime" id="csrAddTime"  value="<s:date name="csRefund.csrAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员退款的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrAddTime">${csRefund.csrAddTime}</textarea>
		 		<span>
		 	
			 ${lz:html(csRefund.csrAddTime$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrAddTime}
	</s:if>
	
	${lz:set("注释","*****************审核时间字段的输入框代码*****************")}
	${lz:set("注释","before$csrCheckTime和after$csrCheckTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrCheckTime==true">
	${before$csrCheckTime}
	<dl class="csrCheckTime " major  ref="csrCheckTime" >
		<dt>审核时间:</dt>
		<s:if test="#request.CTRL.e.csrCheckTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csRefund$csrCheckTime!=null">${csRefund$csrCheckTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csRefund.csrCheckTime" id="csrCheckTime"  value="<s:date name="csRefund.csrCheckTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>没有审核时间则设置为预计时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****审核时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrCheckTime">${csRefund.csrCheckTime}</textarea>
		 		<span>
		 	
			 ${lz:html(csRefund.csrCheckTime$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrCheckTime}
	</s:if>
	
	${lz:set("注释","*****************退款时间字段的输入框代码*****************")}
	${lz:set("注释","before$csrBackTime和after$csrBackTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrBackTime==true">
	${before$csrBackTime}
	<dl class="csrBackTime " major  ref="csrBackTime" >
		<dt>退款时间:</dt>
		<s:if test="#request.CTRL.e.csrBackTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csRefund$csrBackTime!=null">${csRefund$csrBackTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csRefund.csrBackTime" id="csrBackTime"  value="<s:date name="csRefund.csrBackTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>没有退款时间则设置为预计时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****退款时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrBackTime">${csRefund.csrBackTime}</textarea>
		 		<span>
		 	
			 ${lz:html(csRefund.csrBackTime$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrBackTime}
	</s:if>
	
	${lz:set("注释","*****************完成时间字段的输入框代码*****************")}
	${lz:set("注释","before$csrEndTime和after$csrEndTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrEndTime==true">
	${before$csrEndTime}
	<dl class="csrEndTime " major  ref="csrEndTime" >
		<dt>完成时间:</dt>
		<s:if test="#request.CTRL.e.csrEndTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csRefund$csrEndTime!=null">${csRefund$csrEndTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csRefund.csrEndTime" id="csrEndTime"  value="<s:date name="csRefund.csrEndTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>没有完成时间则设置为预计时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****完成时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrEndTime">${csRefund.csrEndTime}</textarea>
		 		<span>
		 	
			 ${lz:html(csRefund.csrEndTime$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrEndTime}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$csrRemark和after$csrRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrRemark==true">
	${before$csrRemark}
	<dl class="csrRemark " minor  ref="csrRemark" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.csrRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csRefund$csrRemark!=null">${csRefund$csrRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csrRemark" name="csRefund.csrRemark" rows="5">${(csRefund.csrRemark)}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员退款的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${lz:html(csRefund.csrRemark$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrRemark}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csrStatus和after$csrStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrStatus==true">
	${before$csrStatus}
	<dl class="csrStatus " major  ref="csrStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csrStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRefund$csrStatus!=null">${csRefund$csrStatus}</s:if><s:else>
		 	<select class="narrow" id="csrStatus" name="csRefund.csrStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csRefund.csrStatus==0?"selected":""}>等待审核</option>
				<option value="1" ${csRefund.csrStatus==1?"selected":""}>等待退款</option>
				<option value="2" ${csRefund.csrStatus==2?"selected":""}>退款完成</option>
				<option value="3" ${csRefund.csrStatus==3?"selected":""}>退款失败</option>
				<option value="4" ${csRefund.csrStatus==4?"selected":""}>退款取消</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员退款的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrStatus">${csRefund.csrStatus}</textarea>
		 		<span>
		 	
			 ${lz:html(csRefund.csrStatus$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csRefund.csrId}" name="csRefund.csrId" id="csrId" />
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