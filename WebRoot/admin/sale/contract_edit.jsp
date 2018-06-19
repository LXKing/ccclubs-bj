<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/sale/contract.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/sale/contract.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty tbSaleContract.tbscId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"tbscId,tbscHost,tbscName,tbscNo,tbscBattle,tbscEvent,tbscOpport,tbscQuote,tbscGroup,tbscCustom,tbscSigner,tbscSignDate,tbscBegin,tbscEnd,tbscMoney,tbscProfit,tbscFile,tbscMemo,tbscStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"tbscId,tbscHost,tbscName,tbscNo,tbscBattle,tbscEvent,tbscOpport,tbscQuote,tbscGroup,tbscCustom,tbscSigner,tbscSignDate,tbscBegin,tbscEnd,tbscMoney,tbscProfit,tbscFile,tbscMemo,tbscStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"tbscId,tbscHost,tbscName,tbscNo,tbscBattle,tbscEvent,tbscOpport,tbscQuote,tbscGroup,tbscCustom,tbscSigner,tbscSignDate,tbscBegin,tbscEnd,tbscMoney,tbscProfit,tbscFile,tbscMemo,tbscStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"tbscId,tbscHost,tbscName,tbscNo,tbscBattle,tbscEvent,tbscOpport,tbscQuote,tbscGroup,tbscCustom,tbscSigner,tbscSignDate,tbscBegin,tbscEnd,tbscMoney,tbscProfit,tbscFile,tbscAdder,tbscEditor,tbscMemo,tbscStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"签约合同编辑":CTRL.title}</title>
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
	$("#contractForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.tbscId==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscHost==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscName==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscName":function(el){
			if(jQuery.trim(el.value)=="")
				return "合同名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "合同名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscNo==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscNo":function(el){
			if(jQuery.trim(el.value)=="")
				return "合同编号不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "合同编号最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscBattle==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscBattle":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscEvent==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscEvent":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscOpport==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscOpport":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscQuote==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscQuote":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscGroup==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscGroup":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscCustom==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscCustom":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscSigner==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscSigner":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscSignDate==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscSignDate":function(el){
			if(jQuery.trim(el.value)=="")
				return "签约日期不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscBegin==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscBegin":function(el){
			if(jQuery.trim(el.value)=="")
				return "合同开始不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscEnd==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscEnd":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscMoney==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscMoney":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "签约金额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscProfit==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscProfit":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "毛估利润输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscFile==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscFile":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "资料文件最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscOnwer==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscOnwer":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscAdder==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscAdder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscEditor==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscRemark==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscMemo==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscMemo":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "备忘信息最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscData==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscData":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "程序数据最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscAddTime==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbscStatus==true">
		${lz:set("haveEditable",true)}
		,"tbSaleContract.tbscStatus":function(el){
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
	<form class="form editform" ref="TbSaleContract" id="contractForm" name="contractForm" action="${empty CTRL.action?"contract_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写签约合同相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${tbSaleContractToken}" name="tbSaleContractToken" id="tbSaleContractToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty tbSaleContract.tbscId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$tbscId和after$tbscId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscId==true">
	${before$tbscId}
	<dl class="tbscId ${CTRL.e.tbscId?"hidden":""}" major  ref="tbscId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.tbscId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.tbSaleContract$tbscId!=null">${tbSaleContract$tbscId}</s:if><s:else>
			<input type="hidden" value="${tbSaleContract.tbscId}" name="tbSaleContract.tbscId" id="tbscId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscId">${tbSaleContract.tbscId}</textarea>
		 		<span>
		 	
			 ${tbSaleContract.tbscId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$tbscHost和after$tbscHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscHost==true">
	${before$tbscHost}
	<dl class="tbscHost " major  ref="tbscHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.tbscHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbSaleContract$tbscHost!=null">${tbSaleContract$tbscHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="tbscHost" name="tbSaleContract.tbscHost">
		 				<option selected value="${tbSaleContract.tbscHost}">
		 					${get:SrvHost(tbSaleContract.tbscHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbscHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#tbscHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscHost">${tbSaleContract.tbscHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${tbSaleContract.tbscHost}',{ctrl:{editable:false,visible:true}})">
			 ${tbSaleContract.tbscHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscHost}
	</s:if>
	
	${lz:set("注释","*****************合同名称字段的输入框代码*****************")}
	${lz:set("注释","before$tbscName和after$tbscName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscName==true">
	${before$tbscName}
	<dl class="tbscName " major  ref="tbscName" >
		<dt>合同名称:</dt>
		<s:if test="#request.CTRL.e.tbscName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.tbSaleContract$tbscName!=null">${tbSaleContract$tbscName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="tbSaleContract.tbscName" id="tbscName"  value="${tbSaleContract.tbscName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入签约合同的合同名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscName">${tbSaleContract.tbscName}</textarea>
		 		<span>
		 	
			 ${tbSaleContract.tbscName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscName}
	</s:if>
	
	${lz:set("注释","*****************合同编号字段的输入框代码*****************")}
	${lz:set("注释","before$tbscNo和after$tbscNo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscNo==true">
	${before$tbscNo}
	<dl class="tbscNo " major  ref="tbscNo" >
		<dt>合同编号:</dt>
		<s:if test="#request.CTRL.e.tbscNo==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.tbSaleContract$tbscNo!=null">${tbSaleContract$tbscNo}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="tbSaleContract.tbscNo" id="tbscNo"  value="${tbSaleContract.tbscNo}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入签约合同的合同编号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscNo">${tbSaleContract.tbscNo}</textarea>
		 		<span>
		 	
			 ${tbSaleContract.tbscNo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscNo}
	</s:if>
	
	${lz:set("注释","*****************所属战役字段的输入框代码*****************")}
	${lz:set("注释","before$tbscBattle和after$tbscBattle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscBattle==true">
	${before$tbscBattle}
	<dl class="tbscBattle " major  ref="tbscBattle" >
		<dt>所属战役:</dt>
		<s:if test="#request.CTRL.e.tbscBattle==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.tbSaleContract$tbscBattle!=null">${tbSaleContract$tbscBattle}</s:if><s:else>
		 			非关联对象，不可设置为query类型
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属战役字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscBattle">${tbSaleContract.tbscBattle}</textarea>
		 		<span>
		 	
			 ${tbSaleContract.tbscBattle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscBattle}
	</s:if>
	
	${lz:set("注释","*****************所属活动字段的输入框代码*****************")}
	${lz:set("注释","before$tbscEvent和after$tbscEvent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscEvent==true">
	${before$tbscEvent}
	<dl class="tbscEvent " major  ref="tbscEvent" >
		<dt>所属活动:</dt>
		<s:if test="#request.CTRL.e.tbscEvent==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.tbSaleContract$tbscEvent!=null">${tbSaleContract$tbscEvent}</s:if><s:else>
		 			<input title="请输入销售活动活动主题进行查询" class="combox narrow" action="${basePath}${proname}/sale/event_query.do?value={param}&csseHost={tbscHost}" type="text" id="tbscEvent" name="tbSaleContract.tbscEvent" text="${get:CsSaleEvent(tbSaleContract.tbscEvent).csseSubject}" value="${tbSaleContract.tbscEvent}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbscEvent').val())==''){return;};window.href('${basePath}${proname}/sale/event_details.do?key='+$('#tbscEvent').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属活动字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscEvent">${tbSaleContract.tbscEvent}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/sale/event_details.do?key=${tbSaleContract.tbscEvent}',{ctrl:{editable:false,visible:true}})">
			 ${tbSaleContract.tbscEvent$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscEvent}
	</s:if>
	
	${lz:set("注释","*****************所属机会字段的输入框代码*****************")}
	${lz:set("注释","before$tbscOpport和after$tbscOpport变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscOpport==true">
	${before$tbscOpport}
	<dl class="tbscOpport " major  ref="tbscOpport" >
		<dt>所属机会:</dt>
		<s:if test="#request.CTRL.e.tbscOpport==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.tbSaleContract$tbscOpport!=null">${tbSaleContract$tbscOpport}</s:if><s:else>
		 			<input title="请输入销售机会机会标题进行查询" class="combox narrow" action="${basePath}${proname}/sale/opport_query.do?value={param}&cssoHost={tbscHost}" type="text" id="tbscOpport" name="tbSaleContract.tbscOpport" text="${get:CsSaleOpport(tbSaleContract.tbscOpport).cssoTitle}" value="${tbSaleContract.tbscOpport}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbscOpport').val())==''){return;};window.href('${basePath}${proname}/sale/opport_details.do?key='+$('#tbscOpport').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属机会字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscOpport">${tbSaleContract.tbscOpport}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/sale/opport_details.do?key=${tbSaleContract.tbscOpport}',{ctrl:{editable:false,visible:true}})">
			 ${tbSaleContract.tbscOpport$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscOpport}
	</s:if>
	
	${lz:set("注释","*****************关联报价字段的输入框代码*****************")}
	${lz:set("注释","before$tbscQuote和after$tbscQuote变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscQuote==true">
	${before$tbscQuote}
	<dl class="tbscQuote " major  ref="tbscQuote" >
		<dt>关联报价:</dt>
		<s:if test="#request.CTRL.e.tbscQuote==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.tbSaleContract$tbscQuote!=null">${tbSaleContract$tbscQuote}</s:if><s:else>
		 			<input title="请输入跟单报价报价标题进行查询" class="combox narrow" action="${basePath}${proname}/sale/salequote_query.do?value={param}&tbsqHost={tbscHost}" type="text" id="tbscQuote" name="tbSaleContract.tbscQuote" text="${get:TbSaleQuote(tbSaleContract.tbscQuote).tbsqTitle}" value="${tbSaleContract.tbscQuote}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbscQuote').val())==''){return;};window.href('${basePath}${proname}/sale/salequote_details.do?key='+$('#tbscQuote').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联报价字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscQuote">${tbSaleContract.tbscQuote}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/sale/salequote_details.do?key=${tbSaleContract.tbscQuote}',{ctrl:{editable:false,visible:true}})">
			 ${tbSaleContract.tbscQuote$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscQuote}
	</s:if>
	
	${lz:set("注释","*****************签约组织字段的输入框代码*****************")}
	${lz:set("注释","before$tbscGroup和after$tbscGroup变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscGroup==true">
	${before$tbscGroup}
	<dl class="tbscGroup " major  ref="tbscGroup" >
		<dt>签约组织:</dt>
		<s:if test="#request.CTRL.e.tbscGroup==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.tbSaleContract$tbscGroup!=null">${tbSaleContract$tbscGroup}</s:if><s:else>
		 			<input title="请输入客户组织组织名称进行查询" class="combox narrow" action="${basePath}${proname}/sale/customgroup_query.do?value={param}&tbcgHost={tbscHost}" type="text" id="tbscGroup" name="tbSaleContract.tbscGroup" text="${get:TbCustomGroup(tbSaleContract.tbscGroup).tbcgName}" value="${tbSaleContract.tbscGroup}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbscGroup').val())==''){return;};window.href('${basePath}${proname}/sale/customgroup_details.do?key='+$('#tbscGroup').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****签约组织字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscGroup">${tbSaleContract.tbscGroup}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/sale/customgroup_details.do?key=${tbSaleContract.tbscGroup}',{ctrl:{editable:false,visible:true}})">
			 ${tbSaleContract.tbscGroup$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscGroup}
	</s:if>
	
	${lz:set("注释","*****************签约客户字段的输入框代码*****************")}
	${lz:set("注释","before$tbscCustom和after$tbscCustom变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscCustom==true">
	${before$tbscCustom}
	<dl class="tbscCustom " major  ref="tbscCustom" >
		<dt>签约客户:</dt>
		<s:if test="#request.CTRL.e.tbscCustom==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.tbSaleContract$tbscCustom!=null">${tbSaleContract$tbscCustom}</s:if><s:else>
		 			<input title="请输入客户资料客户名称进行查询" class="combox narrow" action="${basePath}${proname}/sale/custom_query.do?value={param}&tbscHost={tbscHost}" type="text" id="tbscCustom" name="tbSaleContract.tbscCustom" text="${get:TbSaleCustom(tbSaleContract.tbscCustom).tbscName}" value="${tbSaleContract.tbscCustom}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbscCustom').val())==''){return;};window.href('${basePath}${proname}/sale/custom_details.do?key='+$('#tbscCustom').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****签约客户字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscCustom">${tbSaleContract.tbscCustom}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/sale/custom_details.do?key=${tbSaleContract.tbscCustom}',{ctrl:{editable:false,visible:true}})">
			 ${tbSaleContract.tbscCustom$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscCustom}
	</s:if>
	
	${lz:set("注释","*****************我方人员字段的输入框代码*****************")}
	${lz:set("注释","before$tbscSigner和after$tbscSigner变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscSigner==true">
	${before$tbscSigner}
	<dl class="tbscSigner " major  ref="tbscSigner" >
		<dt>我方人员:</dt>
		<s:if test="#request.CTRL.e.tbscSigner==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbSaleContract$tbscSigner!=null">${tbSaleContract$tbscSigner}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="tbscSigner" name="tbSaleContract.tbscSigner">
		 				<option selected value="${tbSaleContract.tbscSigner}">
		 					${get:SrvUser(tbSaleContract.tbscSigner).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbscSigner').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#tbscSigner').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****我方人员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscSigner">${tbSaleContract.tbscSigner}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${tbSaleContract.tbscSigner}',{ctrl:{editable:false,visible:true}})">
			 ${tbSaleContract.tbscSigner$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscSigner}
	</s:if>
	
	${lz:set("注释","*****************签约日期字段的输入框代码*****************")}
	${lz:set("注释","before$tbscSignDate和after$tbscSignDate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscSignDate==true">
	${before$tbscSignDate}
	<dl class="tbscSignDate " major  ref="tbscSignDate" >
		<dt>签约日期:</dt>
		<s:if test="#request.CTRL.e.tbscSignDate==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.tbSaleContract$tbscSignDate!=null">${tbSaleContract$tbscSignDate}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="tbSaleContract.tbscSignDate" id="tbscSignDate"  value="<s:date name="tbSaleContract.tbscSignDate" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择签约合同的签约日期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****签约日期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscSignDate">${tbSaleContract.tbscSignDate}</textarea>
		 		<span>
		 	
			 ${tbSaleContract.tbscSignDate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscSignDate}
	</s:if>
	
	${lz:set("注释","*****************合同开始字段的输入框代码*****************")}
	${lz:set("注释","before$tbscBegin和after$tbscBegin变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscBegin==true">
	${before$tbscBegin}
	<dl class="tbscBegin " major  ref="tbscBegin" >
		<dt>合同开始:</dt>
		<s:if test="#request.CTRL.e.tbscBegin==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.tbSaleContract$tbscBegin!=null">${tbSaleContract$tbscBegin}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="tbSaleContract.tbscBegin" id="tbscBegin"  value="<s:date name="tbSaleContract.tbscBegin" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择签约合同的合同开始</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同开始字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscBegin">${tbSaleContract.tbscBegin}</textarea>
		 		<span>
		 	
			 ${tbSaleContract.tbscBegin$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscBegin}
	</s:if>
	
	${lz:set("注释","*****************合同结束字段的输入框代码*****************")}
	${lz:set("注释","before$tbscEnd和after$tbscEnd变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscEnd==true">
	${before$tbscEnd}
	<dl class="tbscEnd " minor  ref="tbscEnd" >
		<dt>合同结束:</dt>
		<s:if test="#request.CTRL.e.tbscEnd==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.tbSaleContract$tbscEnd!=null">${tbSaleContract$tbscEnd}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="tbSaleContract.tbscEnd" id="tbscEnd"  value="<s:date name="tbSaleContract.tbscEnd" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择签约合同的合同结束</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****合同结束字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscEnd">${tbSaleContract.tbscEnd}</textarea>
		 		<span>
		 	
			 ${tbSaleContract.tbscEnd$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscEnd}
	</s:if>
	
	${lz:set("注释","*****************签约金额字段的输入框代码*****************")}
	${lz:set("注释","before$tbscMoney和after$tbscMoney变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscMoney==true">
	${before$tbscMoney}
	<dl class="tbscMoney " major  ref="tbscMoney" >
		<dt>签约金额:</dt>
		<s:if test="#request.CTRL.e.tbscMoney==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.tbSaleContract$tbscMoney!=null">${tbSaleContract$tbscMoney}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="tbSaleContract.tbscMoney" id="tbscMoney"  value="${tbSaleContract.tbscMoney}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入签约合同的签约金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****签约金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscMoney">${tbSaleContract.tbscMoney}</textarea>
		 		<span>
		 	
			 ${tbSaleContract.tbscMoney$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscMoney}
	</s:if>
	
	${lz:set("注释","*****************毛估利润字段的输入框代码*****************")}
	${lz:set("注释","before$tbscProfit和after$tbscProfit变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscProfit==true">
	${before$tbscProfit}
	<dl class="tbscProfit " major  ref="tbscProfit" >
		<dt>毛估利润:</dt>
		<s:if test="#request.CTRL.e.tbscProfit==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.tbSaleContract$tbscProfit!=null">${tbSaleContract$tbscProfit}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="tbSaleContract.tbscProfit" id="tbscProfit"  value="${tbSaleContract.tbscProfit}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入签约合同的毛估利润</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****毛估利润字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscProfit">${tbSaleContract.tbscProfit}</textarea>
		 		<span>
		 	
			 ${tbSaleContract.tbscProfit$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscProfit}
	</s:if>
	
	${lz:set("注释","*****************资料文件字段的输入框代码*****************")}
	${lz:set("注释","before$tbscFile和after$tbscFile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscFile==true">
	${before$tbscFile}
	<dl class="tbscFile " major  ref="tbscFile" style="width:98%;">
		<dt>资料文件:</dt>
		<s:if test="#request.CTRL.e.tbscFile==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.tbSaleContract$tbscFile!=null">${tbSaleContract$tbscFile}</s:if><s:else>
		 	<textarea class="input wide"  id="tbscFile" name="tbSaleContract.tbscFile" rows="5">${tbSaleContract.tbscFile}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入签约合同的资料文件</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****资料文件字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbSaleContract.tbscFile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscFile}
	</s:if>
	
	${lz:set("注释","*****************所有人字段的输入框代码*****************")}
	${lz:set("注释","before$tbscOnwer和after$tbscOnwer变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscOnwer==true">
	${before$tbscOnwer}
	<dl class="tbscOnwer " major  ref="tbscOnwer" >
		<dt>所&nbsp;&nbsp;有&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.tbscOnwer==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbSaleContract$tbscOnwer!=null">${tbSaleContract$tbscOnwer}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="tbscOnwer" name="tbSaleContract.tbscOnwer">
		 				<option selected value="${tbSaleContract.tbscOnwer}">
		 					${get:SrvUser(tbSaleContract.tbscOnwer).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbscOnwer').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#tbscOnwer').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所有人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscOnwer">${tbSaleContract.tbscOnwer}</textarea>
		 		<span>
		 	
			 ${tbSaleContract.tbscOnwer$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscOnwer}
	</s:if>
	
	${lz:set("注释","*****************添加人字段的输入框代码*****************")}
	${lz:set("注释","before$tbscAdder和after$tbscAdder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscAdder==true">
	${before$tbscAdder}
	<dl class="tbscAdder " major  ref="tbscAdder" >
		<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.tbscAdder==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbSaleContract$tbscAdder!=null">${tbSaleContract$tbscAdder}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="tbscAdder" name="tbSaleContract.tbscAdder">
		 				<option selected value="${tbSaleContract.tbscAdder}">
		 					${get:SrvUser(tbSaleContract.tbscAdder).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbscAdder').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#tbscAdder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscAdder">${tbSaleContract.tbscAdder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${tbSaleContract.tbscAdder}',{ctrl:{editable:false,visible:true}})">
			 ${tbSaleContract.tbscAdder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscAdder}
	</s:if>
	
	${lz:set("注释","*****************修改人字段的输入框代码*****************")}
	${lz:set("注释","before$tbscEditor和after$tbscEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscEditor==true">
	${before$tbscEditor}
	<dl class="tbscEditor " major  ref="tbscEditor" >
		<dt>修&nbsp;&nbsp;改&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.tbscEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbSaleContract$tbscEditor!=null">${tbSaleContract$tbscEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="tbscEditor" name="tbSaleContract.tbscEditor">
		 				<option selected value="${tbSaleContract.tbscEditor}">
		 					${get:SrvUser(tbSaleContract.tbscEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbscEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#tbscEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscEditor">${tbSaleContract.tbscEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${tbSaleContract.tbscEditor}',{ctrl:{editable:false,visible:true}})">
			 ${tbSaleContract.tbscEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscEditor}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$tbscRemark和after$tbscRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscRemark==true">
	${before$tbscRemark}
	<dl class="tbscRemark " major  ref="tbscRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.tbscRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.tbSaleContract$tbscRemark!=null">${tbSaleContract$tbscRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="tbscRemark" name="tbSaleContract.tbscRemark" rows="5">${tbSaleContract.tbscRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入签约合同的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbSaleContract.tbscRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscRemark}
	</s:if>
	
	${lz:set("注释","*****************备忘信息字段的输入框代码*****************")}
	${lz:set("注释","before$tbscMemo和after$tbscMemo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscMemo==true">
	${before$tbscMemo}
	<dl class="tbscMemo " minor  ref="tbscMemo" style="width:98%;">
		<dt>备忘信息:</dt>
		<s:if test="#request.CTRL.e.tbscMemo==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.tbSaleContract$tbscMemo!=null">${tbSaleContract$tbscMemo}</s:if><s:else>
		 	<textarea class="input wide"  id="tbscMemo" name="tbSaleContract.tbscMemo" rows="5">${tbSaleContract.tbscMemo}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入签约合同的备忘信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备忘信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbSaleContract.tbscMemo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscMemo}
	</s:if>
	
	${lz:set("注释","*****************程序数据字段的输入框代码*****************")}
	${lz:set("注释","before$tbscData和after$tbscData变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscData==true">
	${before$tbscData}
	<dl class="tbscData " minor  ref="tbscData" style="width:98%;">
		<dt>程序数据:</dt>
		<s:if test="#request.CTRL.e.tbscData==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.tbSaleContract$tbscData!=null">${tbSaleContract$tbscData}</s:if><s:else>
		 	<textarea class="input wide"  id="tbscData" name="tbSaleContract.tbscData" rows="5">${tbSaleContract.tbscData}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入签约合同的程序数据</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****程序数据字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbSaleContract.tbscData$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscData}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$tbscUpdateTime和after$tbscUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscUpdateTime==true">
	${before$tbscUpdateTime}
	<dl class="tbscUpdateTime " major  ref="tbscUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.tbscUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.tbSaleContract$tbscUpdateTime!=null">${tbSaleContract$tbscUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="tbSaleContract.tbscUpdateTime" id="tbscUpdateTime"  value="<s:date name="tbSaleContract.tbscUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择签约合同的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscUpdateTime">${tbSaleContract.tbscUpdateTime}</textarea>
		 		<span>
		 	
			 ${tbSaleContract.tbscUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$tbscAddTime和after$tbscAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscAddTime==true">
	${before$tbscAddTime}
	<dl class="tbscAddTime " major  ref="tbscAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.tbscAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.tbSaleContract$tbscAddTime!=null">${tbSaleContract$tbscAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="tbSaleContract.tbscAddTime" id="tbscAddTime"  value="<s:date name="tbSaleContract.tbscAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择签约合同的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscAddTime">${tbSaleContract.tbscAddTime}</textarea>
		 		<span>
		 	
			 ${tbSaleContract.tbscAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$tbscStatus和after$tbscStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbscStatus==true">
	${before$tbscStatus}
	<dl class="tbscStatus " major  ref="tbscStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.tbscStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.tbSaleContract$tbscStatus!=null">${tbSaleContract$tbscStatus}</s:if><s:else>
		 	<select class="narrow" id="tbscStatus" name="tbSaleContract.tbscStatus">
		 		<option value="">请选择</option>
				<option value="1" ${tbSaleContract.tbscStatus==1?"selected":""}>正常</option>
				<option value="0" ${tbSaleContract.tbscStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择签约合同的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbscStatus">${tbSaleContract.tbscStatus}</textarea>
		 		<span>
		 	
			 ${tbSaleContract.tbscStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbscStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${tbSaleContract.tbscId}" name="tbSaleContract.tbscId" id="tbscId" />
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