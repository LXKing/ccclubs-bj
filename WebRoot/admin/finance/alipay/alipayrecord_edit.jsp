<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/finance/alipay/alipayrecord.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/finance/alipay/alipayrecord.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csAlipayRecord.csarId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csarHost,csarBankType",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csarHost,csarBankType",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csarId,csarHost,csarBankType",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csarId,csarHost,csarBankType",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"接口充值编辑":CTRL.title}</title>
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
	$("#alipayrecordForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csarId==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRecord.csarId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarHost==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRecord.csarHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarBankType==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRecord.csarBankType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择接口类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarTradeNo==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRecord.csarTradeNo":function(el){
			if(jQuery.trim(el.value)=="")
				return "交易号不能为空";
			if(el.value.length>64 && el.value.indexOf("[*]")==-1)
				return "交易号最大长度为64个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarMember==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRecord.csarMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarRealName==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRecord.csarRealName":function(el){
			if(jQuery.trim(el.value)=="")
				return "会员姓名不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "会员姓名最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarAmount==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRecord.csarAmount":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "充值金额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarBuyItem==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRecord.csarBuyItem":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarBuyCount==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRecord.csarBuyCount":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarRefund==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRecord.csarRefund":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "已退金额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarPayMethod==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRecord.csarPayMethod":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "支付接口最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarBlank==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRecord.csarBlank":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "银行名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarAlipayNo==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRecord.csarAlipayNo":function(el){
			if(el.value.length>64 && el.value.indexOf("[*]")==-1)
				return "支付宝交易号最大长度为64个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarBuyerAccount==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRecord.csarBuyerAccount":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "支付宝帐号最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarClientId==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRecord.csarClientId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarThirdBack==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRecord.csarThirdBack":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "三方回调最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRecord.csarUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarAddTime==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRecord.csarAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csarStatus==true">
		${lz:set("haveEditable",true)}
		,"csAlipayRecord.csarStatus":function(el){
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
	<form class="form editform" ref="CsAlipayRecord" id="alipayrecordForm" name="alipayrecordForm" action="${empty CTRL.action?"alipayrecord_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写接口充值相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csAlipayRecordToken}" name="csAlipayRecordToken" id="csAlipayRecordToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csAlipayRecord.csarId))}		
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
		<s:if test="#request.csAlipayRecord$csarId!=null">${csAlipayRecord$csarId}</s:if><s:else>
			<input type="hidden" value="${(csAlipayRecord.csarId)}" name="csAlipayRecord.csarId" id="csarId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarId">${csAlipayRecord.csarId}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayRecord.csarId$)}
	 	  
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
		<s:if test="#request.csAlipayRecord$csarHost!=null">${csAlipayRecord$csarHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csarHost" name="csAlipayRecord.csarHost">
		 				<option selected value="${(csAlipayRecord.csarHost)}">
		 					${get:SrvHost(csAlipayRecord.csarHost).shName}
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
		 		<textarea class="" style="display:none;" id="csarHost">${csAlipayRecord.csarHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${(csAlipayRecord.csarHost)}',{ctrl:{editable:false,visible:true}})">
			 ${lz:html(csAlipayRecord.csarHost$)}</a>
	 	  
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
		<s:if test="#request.csAlipayRecord$csarBankType!=null">${csAlipayRecord$csarBankType}</s:if><s:else>
		 	<select class="narrow" id="csarBankType" name="csAlipayRecord.csarBankType">
		 		<option value="">请选择</option>
				<option value="0" ${csAlipayRecord.csarBankType==0?"selected":""}>支付宝</option>
				<option value="10" ${csAlipayRecord.csarBankType==10?"selected":""}>招商银行</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择接口充值的接口类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****接口类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarBankType">${csAlipayRecord.csarBankType}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayRecord.csarBankType$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarBankType}
	</s:if>
	
	${lz:set("注释","*****************交易号字段的输入框代码*****************")}
	${lz:set("注释","before$csarTradeNo和after$csarTradeNo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarTradeNo==true">
	${before$csarTradeNo}
	<dl class="csarTradeNo " major  ref="csarTradeNo" style="width:98%;">
		<dt>交&nbsp;&nbsp;易&nbsp;&nbsp;号:</dt>
		<s:if test="#request.CTRL.e.csarTradeNo==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csAlipayRecord$csarTradeNo!=null">${csAlipayRecord$csarTradeNo}</s:if><s:else>
		 	<input type="text" class="input wide"   name="csAlipayRecord.csarTradeNo" id="csarTradeNo"  value="${(csAlipayRecord.csarTradeNo)}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入接口充值的交易号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****交易号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csarTradeNo">${csAlipayRecord.csarTradeNo}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayRecord.csarTradeNo$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarTradeNo}
	</s:if>
	
	${lz:set("注释","*****************充值会员字段的输入框代码*****************")}
	${lz:set("注释","before$csarMember和after$csarMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarMember==true">
	${before$csarMember}
	<dl class="csarMember " major  ref="csarMember" >
		<dt>充值会员:</dt>
		<s:if test="#request.CTRL.e.csarMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csAlipayRecord$csarMember!=null">${csAlipayRecord$csarMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csarHost}" type="text" id="csarMember" name="csAlipayRecord.csarMember" text="${get:CsMember(csAlipayRecord.csarMember).csmName}" value="${(csAlipayRecord.csarMember)}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csarMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csarMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****充值会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarMember">${csAlipayRecord.csarMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${(csAlipayRecord.csarMember)}',{ctrl:{editable:false,visible:true}})">
			 ${lz:html(csAlipayRecord.csarMember$)}</a>
	 	  
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
		<s:if test="#request.csAlipayRecord$csarRealName!=null">${csAlipayRecord$csarRealName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csAlipayRecord.csarRealName" id="csarRealName"  value="${(csAlipayRecord.csarRealName)}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入接口充值的会员姓名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****会员姓名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarRealName">${csAlipayRecord.csarRealName}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayRecord.csarRealName$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarRealName}
	</s:if>
	
	${lz:set("注释","*****************充值金额字段的输入框代码*****************")}
	${lz:set("注释","before$csarAmount和after$csarAmount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarAmount==true">
	${before$csarAmount}
	<dl class="csarAmount " major  ref="csarAmount" >
		<dt>充值金额:</dt>
		<s:if test="#request.CTRL.e.csarAmount==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csAlipayRecord$csarAmount!=null">${csAlipayRecord$csarAmount}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csAlipayRecord.csarAmount" id="csarAmount"  value="${(csAlipayRecord.csarAmount)}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入接口充值的充值金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****充值金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarAmount">${csAlipayRecord.csarAmount}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayRecord.csarAmount$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarAmount}
	</s:if>
	
	${lz:set("注释","*****************购买宝贝字段的输入框代码*****************")}
	${lz:set("注释","before$csarBuyItem和after$csarBuyItem变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarBuyItem==true">
	${before$csarBuyItem}
	<dl class="csarBuyItem " major  ref="csarBuyItem" >
		<dt>购买宝贝:</dt>
		<s:if test="#request.CTRL.e.csarBuyItem==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csAlipayRecord$csarBuyItem!=null">${csAlipayRecord$csarBuyItem}</s:if><s:else>
		 			<input title="请输入商品信息商品名称进行查询" class="combox narrow" action="${basePath}${proname}/fee/item_query.do?value={param}" type="text" id="csarBuyItem" name="csAlipayRecord.csarBuyItem" text="${get:CsItem(csAlipayRecord.csarBuyItem).csiTitle}" value="${(csAlipayRecord.csarBuyItem)}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csarBuyItem').val())==''){return;};window.href('${basePath}${proname}/fee/item_details.do?key='+$('#csarBuyItem').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em>标识充值完成后打算购买哪个宝贝</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****购买宝贝字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarBuyItem">${csAlipayRecord.csarBuyItem}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/fee/item_details.do?key=${(csAlipayRecord.csarBuyItem)}',{ctrl:{editable:false,visible:true}})">
			 ${lz:html(csAlipayRecord.csarBuyItem$)}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarBuyItem}
	</s:if>
	
	${lz:set("注释","*****************购买个数字段的输入框代码*****************")}
	${lz:set("注释","before$csarBuyCount和after$csarBuyCount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarBuyCount==true">
	${before$csarBuyCount}
	<dl class="csarBuyCount " major  ref="csarBuyCount" >
		<dt>购买个数:</dt>
		<s:if test="#request.CTRL.e.csarBuyCount==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csAlipayRecord$csarBuyCount!=null">${csAlipayRecord$csarBuyCount}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csAlipayRecord.csarBuyCount" id="csarBuyCount"  value="${(csAlipayRecord.csarBuyCount)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>标识充值完成后打算购买哪个宝贝的个数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****购买个数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarBuyCount">${csAlipayRecord.csarBuyCount}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayRecord.csarBuyCount$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarBuyCount}
	</s:if>
	
	${lz:set("注释","*****************已退金额字段的输入框代码*****************")}
	${lz:set("注释","before$csarRefund和after$csarRefund变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarRefund==true">
	${before$csarRefund}
	<dl class="csarRefund " major  ref="csarRefund" >
		<dt>已退金额:</dt>
		<s:if test="#request.CTRL.e.csarRefund==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csAlipayRecord$csarRefund!=null">${csAlipayRecord$csarRefund}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csAlipayRecord.csarRefund" id="csarRefund"  value="${(csAlipayRecord.csarRefund)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入接口充值的已退金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****已退金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarRefund">${csAlipayRecord.csarRefund}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayRecord.csarRefund$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarRefund}
	</s:if>
	
	${lz:set("注释","*****************支付接口字段的输入框代码*****************")}
	${lz:set("注释","before$csarPayMethod和after$csarPayMethod变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarPayMethod==true">
	${before$csarPayMethod}
	<dl class="csarPayMethod " major  ref="csarPayMethod" >
		<dt>支付接口:</dt>
		<s:if test="#request.CTRL.e.csarPayMethod==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csAlipayRecord$csarPayMethod!=null">${csAlipayRecord$csarPayMethod}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csAlipayRecord.csarPayMethod" id="csarPayMethod"  value="${(csAlipayRecord.csarPayMethod)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入接口充值的支付接口</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****支付接口字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarPayMethod">${csAlipayRecord.csarPayMethod}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayRecord.csarPayMethod$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarPayMethod}
	</s:if>
	
	${lz:set("注释","*****************银行名称字段的输入框代码*****************")}
	${lz:set("注释","before$csarBlank和after$csarBlank变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarBlank==true">
	${before$csarBlank}
	<dl class="csarBlank " major  ref="csarBlank" >
		<dt>银行名称:</dt>
		<s:if test="#request.CTRL.e.csarBlank==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csAlipayRecord$csarBlank!=null">${csAlipayRecord$csarBlank}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csAlipayRecord.csarBlank" id="csarBlank"  value="${(csAlipayRecord.csarBlank)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入接口充值的银行名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****银行名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarBlank">${csAlipayRecord.csarBlank}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayRecord.csarBlank$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarBlank}
	</s:if>
	
	${lz:set("注释","*****************支付宝交易号字段的输入框代码*****************")}
	${lz:set("注释","before$csarAlipayNo和after$csarAlipayNo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarAlipayNo==true">
	${before$csarAlipayNo}
	<dl class="csarAlipayNo " major  ref="csarAlipayNo" style="width:98%;">
		<dt>支付宝交易号:</dt>
		<s:if test="#request.CTRL.e.csarAlipayNo==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csAlipayRecord$csarAlipayNo!=null">${csAlipayRecord$csarAlipayNo}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="64" name="csAlipayRecord.csarAlipayNo" id="csarAlipayNo"  value="${(csAlipayRecord.csarAlipayNo)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入接口充值的支付宝交易号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****支付宝交易号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csarAlipayNo">${csAlipayRecord.csarAlipayNo}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayRecord.csarAlipayNo$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarAlipayNo}
	</s:if>
	
	${lz:set("注释","*****************支付宝帐号字段的输入框代码*****************")}
	${lz:set("注释","before$csarBuyerAccount和after$csarBuyerAccount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarBuyerAccount==true">
	${before$csarBuyerAccount}
	<dl class="csarBuyerAccount " major  ref="csarBuyerAccount" >
		<dt>支付宝帐号:</dt>
		<s:if test="#request.CTRL.e.csarBuyerAccount==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csAlipayRecord$csarBuyerAccount!=null">${csAlipayRecord$csarBuyerAccount}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csAlipayRecord.csarBuyerAccount" id="csarBuyerAccount"  value="${(csAlipayRecord.csarBuyerAccount)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入接口充值的支付宝帐号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****支付宝帐号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarBuyerAccount">${csAlipayRecord.csarBuyerAccount}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayRecord.csarBuyerAccount$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarBuyerAccount}
	</s:if>
	
	${lz:set("注释","*****************三方客户端ID字段的输入框代码*****************")}
	${lz:set("注释","before$csarClientId和after$csarClientId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarClientId==true">
	${before$csarClientId}
	<dl class="csarClientId " minor  ref="csarClientId" >
		<dt>三方客户端ID:</dt>
		<s:if test="#request.CTRL.e.csarClientId==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csAlipayRecord$csarClientId!=null">${csAlipayRecord$csarClientId}</s:if><s:else>
		 			<input title="请输入API客户端客户端ID进行查询" class="combox narrow" action="${basePath}${proname}/configurator/apiclient_query.do?value={param}" type="text" id="csarClientId" name="csAlipayRecord.csarClientId" text="${get:SrvApiClient(csAlipayRecord.csarClientId).sacClient}" value="${(csAlipayRecord.csarClientId)}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csarClientId').val())==''){return;};window.href('${basePath}${proname}/configurator/apiclient_details.do?key='+$('#csarClientId').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****三方客户端ID字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarClientId">${csAlipayRecord.csarClientId}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/apiclient_details.do?key=${(csAlipayRecord.csarClientId)}',{ctrl:{editable:false,visible:true}})">
			 ${lz:html(csAlipayRecord.csarClientId$)}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarClientId}
	</s:if>
	
	${lz:set("注释","*****************三方回调字段的输入框代码*****************")}
	${lz:set("注释","before$csarThirdBack和after$csarThirdBack变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csarThirdBack==true">
	${before$csarThirdBack}
	<dl class="csarThirdBack " minor  ref="csarThirdBack" style="width:98%;">
		<dt>三方回调:</dt>
		<s:if test="#request.CTRL.e.csarThirdBack==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csAlipayRecord$csarThirdBack!=null">${csAlipayRecord$csarThirdBack}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="256" name="csAlipayRecord.csarThirdBack" id="csarThirdBack"  value="${(csAlipayRecord.csarThirdBack)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入接口充值的三方回调</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****三方回调字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csarThirdBack">${csAlipayRecord.csarThirdBack}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayRecord.csarThirdBack$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csarThirdBack}
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
		<s:if test="#request.csAlipayRecord$csarUpdateTime!=null">${csAlipayRecord$csarUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csAlipayRecord.csarUpdateTime" id="csarUpdateTime"  value="<s:date name="csAlipayRecord.csarUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择接口充值的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarUpdateTime">${csAlipayRecord.csarUpdateTime}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayRecord.csarUpdateTime$)}
	 	  
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
		<s:if test="#request.csAlipayRecord$csarAddTime!=null">${csAlipayRecord$csarAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csAlipayRecord.csarAddTime" id="csarAddTime"  value="<s:date name="csAlipayRecord.csarAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择接口充值的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarAddTime">${csAlipayRecord.csarAddTime}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayRecord.csarAddTime$)}
	 	  
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
		<s:if test="#request.csAlipayRecord$csarStatus!=null">${csAlipayRecord$csarStatus}</s:if><s:else>
		 	<select class="narrow" id="csarStatus" name="csAlipayRecord.csarStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csAlipayRecord.csarStatus==0?"selected":""}>等待</option>
				<option value="1" ${csAlipayRecord.csarStatus==1?"selected":""}>成功</option>
				<option value="2" ${csAlipayRecord.csarStatus==2?"selected":""}>失败</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择接口充值的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csarStatus">${csAlipayRecord.csarStatus}</textarea>
		 		<span>
		 	
			 ${lz:html(csAlipayRecord.csarStatus$)}
	 	  
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
					<input type="hidden" value="${csAlipayRecord.csarId}" name="csAlipayRecord.csarId" id="csarId" />
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