<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/service/longorderfee.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/service/longorderfee.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csLongOrderFee.cslofId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cslofId,cslofHost,cslofLongorder,cslofName,cslofType,cslofPayType,cslofAmmount,cslofRemark,cslofStateInfo,cslofState,cslofStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cslofId,cslofHost,cslofLongorder,cslofName,cslofType,cslofPayType,cslofAmmount,cslofRemark,cslofStateInfo,cslofState,cslofStatus",
	</s:if>
	<s:elseif test="#request.CTRL.title=='财务确认'">
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cslofStateInfo,nothing",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cslofHost,cslofLongorder,cslofName,cslofType,cslofPayType,cslofAmmount,cslofRemark,cslofStateInfo,nothing",
	</s:elseif>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cslofId,cslofHost,cslofLongorder,cslofName,cslofType,cslofPayType,cslofAmmount,cslofRemark,cslofStateInfo,cslofState,cslofStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cslofId,cslofHost,cslofLongorder,cslofName,cslofType,cslofPayType,cslofAmmount,cslofRemark,cslofStateInfo,cslofState,cslofStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"长单缴费编辑":CTRL.title}</title>
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
	$("#longorderfeeForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cslofId==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderFee.cslofId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslofHost==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderFee.cslofHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslofLongorder==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderFee.cslofLongorder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslofName==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderFee.cslofName":function(el){
			if(jQuery.trim(el.value)=="")
				return "项目名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "项目名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslofType==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderFee.cslofType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择费用类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslofPayType==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderFee.cslofPayType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择交费方式";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslofAmmount==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderFee.cslofAmmount":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "费用金额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslofRemark==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderFee.cslofRemark":function(el){
			if(jQuery.trim(el.value)=="")
				return "备注信息不能为空";
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslofUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderFee.cslofUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslofAddTime==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderFee.cslofAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslofStateInfo==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderFee.cslofStateInfo":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "入帐信息最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslofState==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderFee.cslofState":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择财务确认";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cslofStatus==true">
		${lz:set("haveEditable",true)}
		,"csLongOrderFee.cslofStatus":function(el){
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
<s:elseif test="#request.CTRL.title=='财务确认'">
	<form class="form editform" ref="CsLongOrderFee" id="longorderfeeForm" name="longorderfeeForm" action="${empty CTRL.action?"longorderfee_confirm.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<div class="prompt">
					温馨提示：请仔细填写长单缴费相关信息，<span class="extrude">"*" 为必填选项。</span>
				</div>
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
				<input type="hidden" value="${csLongOrderFeeToken}" name="csLongOrderFeeToken" id="csLongOrderFeeToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cslofHost和after$cslofHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cslofHost}
	<dl class="cslofHost " major  ref="cslofHost" >
		<dt>城　　市:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csLongOrderFee$cslofHost!=null">${csLongOrderFee$cslofHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cslofHost" name="csLongOrderFee.cslofHost">
		 				<option selected value="${csLongOrderFee.cslofHost}">
		 					${get:SrvHost(csLongOrderFee.cslofHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cslofHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cslofHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslofHost">${csLongOrderFee.cslofHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csLongOrderFee.cslofHost}',{ctrl:{editable:false,visible:true}})">
			 ${csLongOrderFee.cslofHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslofHost}
	
	
	${lz:set("注释","*****************所属长租字段的输入框代码*****************")}
	${lz:set("注释","before$cslofLongorder和after$cslofLongorder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cslofLongorder}
	<dl class="cslofLongorder " major  ref="cslofLongorder" >
		<dt>所属长租:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csLongOrderFee$cslofLongorder!=null">${csLongOrderFee$cslofLongorder}</s:if><s:else>
		 			<input title="请输入长租订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/longorder_query.do?value={param}&csloHost={cslofHost}" type="text" id="cslofLongorder" name="csLongOrderFee.cslofLongorder" text="${get:CsLongOrder(csLongOrderFee.cslofLongorder).csloId}" value="${csLongOrderFee.cslofLongorder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cslofLongorder').val())==''){return;};window.href('${basePath}${proname}/service/longorder_details.do?key='+$('#cslofLongorder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属长租字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslofLongorder">${csLongOrderFee.cslofLongorder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/longorder_details.do?key=${csLongOrderFee.cslofLongorder}',{ctrl:{editable:false,visible:true}})">
			 ${csLongOrderFee.cslofLongorder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslofLongorder}
	
	
	${lz:set("注释","*****************项目名称字段的输入框代码*****************")}
	${lz:set("注释","before$cslofName和after$cslofName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cslofName}
	<dl class="cslofName " major  ref="cslofName" >
		<dt>项目名称:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csLongOrderFee$cslofName!=null">${csLongOrderFee$cslofName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csLongOrderFee.cslofName" id="cslofName"  value="${csLongOrderFee.cslofName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长单缴费的项目名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****项目名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslofName">${csLongOrderFee.cslofName}</textarea>
		 		<span>
		 	
			 ${csLongOrderFee.cslofName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslofName}
	
	
	${lz:set("注释","*****************费用类型字段的输入框代码*****************")}
	${lz:set("注释","before$cslofType和after$cslofType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cslofType}
	<dl class="cslofType " major  ref="cslofType" >
		<dt>费用类型:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csLongOrderFee$cslofType!=null">${csLongOrderFee$cslofType}</s:if><s:else>
		 	<select class="narrow" id="cslofType" name="csLongOrderFee.cslofType">
		 		<option value="">请选择</option>
		 		 <option value="">无法自动构造</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长单缴费的费用类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****费用类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslofType">${csLongOrderFee.cslofType}</textarea>
		 		<span>
		 	
			 ${csLongOrderFee.cslofType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslofType}
	
	
	${lz:set("注释","*****************交费方式字段的输入框代码*****************")}
	${lz:set("注释","before$cslofPayType和after$cslofPayType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cslofPayType}
	<dl class="cslofPayType " major  ref="cslofPayType" >
		<dt>交费方式:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csLongOrderFee$cslofPayType!=null">${csLongOrderFee$cslofPayType}</s:if><s:else>
		 	<select class="narrow" id="cslofPayType" name="csLongOrderFee.cslofPayType">
		 		<option value="">请选择</option>
		 		 <option value="">无法自动构造</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长单缴费的交费方式</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****交费方式字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslofPayType">${csLongOrderFee.cslofPayType}</textarea>
		 		<span>
		 	
			 ${csLongOrderFee.cslofPayType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslofPayType}
	
	
	${lz:set("注释","*****************费用金额字段的输入框代码*****************")}
	${lz:set("注释","before$cslofAmmount和after$cslofAmmount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cslofAmmount}
	<dl class="cslofAmmount " major  ref="cslofAmmount" >
		<dt>费用金额:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csLongOrderFee$cslofAmmount!=null">${csLongOrderFee$cslofAmmount}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLongOrderFee.cslofAmmount" id="cslofAmmount"  value="${csLongOrderFee.cslofAmmount}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长单缴费的费用金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****费用金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslofAmmount">${csLongOrderFee.cslofAmmount}</textarea>
		 		<span>
		 	
			 ${csLongOrderFee.cslofAmmount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslofAmmount}
	
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$cslofRemark和after$cslofRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cslofRemark}
	<dl class="cslofRemark " major  ref="cslofRemark" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csLongOrderFee$cslofRemark!=null">${csLongOrderFee$cslofRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="cslofRemark" name="csLongOrderFee.cslofRemark" rows="5">${csLongOrderFee.cslofRemark}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长单缴费的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csLongOrderFee.cslofRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslofRemark}
	
	
	${lz:set("注释","*****************入帐信息字段的输入框代码*****************")}
	${lz:set("注释","before$cslofStateInfo和after$cslofStateInfo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cslofStateInfo}
	<dl class="cslofStateInfo " major  ref="cslofStateInfo" style="width:98%;">
		<dt>入帐信息:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csLongOrderFee$cslofStateInfo!=null">${csLongOrderFee$cslofStateInfo}</s:if><s:else>
		 	<textarea class="input wide"  id="cslofStateInfo" name="csLongOrderFee.cslofStateInfo" rows="5">${csLongOrderFee.cslofStateInfo}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长单缴费的入帐信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****入帐信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csLongOrderFee.cslofStateInfo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslofStateInfo}
	
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<input type="hidden" value="${csLongOrderFee.cslofId}" name="csLongOrderFee.cslofId" id="cslofId" />
					<button title="保存数据,关闭窗口,刷新原列表" class="button" type="submit" name="submiter" value="完成">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
						完成&nbsp;&nbsp;</button>
					<button title="不保存数据,关闭窗口,不刷新原列表" class="button" onclick="cancel();" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-multiply.png"/>
						取消&nbsp;&nbsp;</button>
				</center>
			</div>	
		</div>
		<div class="foot"></div>		
	</form>
</s:elseif>
<s:else>
	<form class="form editform" ref="CsLongOrderFee" id="longorderfeeForm" name="longorderfeeForm" action="${empty CTRL.action?"longorderfee_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写长单缴费相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csLongOrderFeeToken}" name="csLongOrderFeeToken" id="csLongOrderFeeToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csLongOrderFee.cslofId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cslofId和after$cslofId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslofId==true">
	${before$cslofId}
	<dl class="cslofId ${CTRL.e.cslofId?"hidden":""}" major  ref="cslofId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cslofId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csLongOrderFee$cslofId!=null">${csLongOrderFee$cslofId}</s:if><s:else>
			<input type="hidden" value="${csLongOrderFee.cslofId}" name="csLongOrderFee.cslofId" id="cslofId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslofId">${csLongOrderFee.cslofId}</textarea>
		 		<span>
		 	
			 ${csLongOrderFee.cslofId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslofId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cslofHost和after$cslofHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslofHost==true">
	${before$cslofHost}
	<dl class="cslofHost " major  ref="cslofHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cslofHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csLongOrderFee$cslofHost!=null">${csLongOrderFee$cslofHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cslofHost" name="csLongOrderFee.cslofHost">
		 				<option selected value="${csLongOrderFee.cslofHost}">
		 					${get:SrvHost(csLongOrderFee.cslofHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cslofHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cslofHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslofHost">${csLongOrderFee.cslofHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csLongOrderFee.cslofHost}',{ctrl:{editable:false,visible:true}})">
			 ${csLongOrderFee.cslofHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslofHost}
	</s:if>
	
	${lz:set("注释","*****************所属长租字段的输入框代码*****************")}
	${lz:set("注释","before$cslofLongorder和after$cslofLongorder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslofLongorder==true">
	${before$cslofLongorder}
	<dl class="cslofLongorder " major  ref="cslofLongorder" >
		<dt>所属长租:</dt>
		<s:if test="#request.CTRL.e.cslofLongorder==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csLongOrderFee$cslofLongorder!=null">${csLongOrderFee$cslofLongorder}</s:if><s:else>
		 			<input title="请输入长租订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/longorder_query.do?value={param}&csloHost={cslofHost}" type="text" id="cslofLongorder" name="csLongOrderFee.cslofLongorder" text="${get:CsLongOrder(csLongOrderFee.cslofLongorder).csloId}" value="${csLongOrderFee.cslofLongorder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cslofLongorder').val())==''){return;};window.href('${basePath}${proname}/service/longorder_details.do?key='+$('#cslofLongorder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属长租字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslofLongorder">${csLongOrderFee.cslofLongorder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/longorder_details.do?key=${csLongOrderFee.cslofLongorder}',{ctrl:{editable:false,visible:true}})">
			 ${csLongOrderFee.cslofLongorder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslofLongorder}
	</s:if>
	
	${lz:set("注释","*****************项目名称字段的输入框代码*****************")}
	${lz:set("注释","before$cslofName和after$cslofName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslofName==true">
	${before$cslofName}
	<dl class="cslofName " major  ref="cslofName" >
		<dt>项目名称:</dt>
		<s:if test="#request.CTRL.e.cslofName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csLongOrderFee$cslofName!=null">${csLongOrderFee$cslofName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csLongOrderFee.cslofName" id="cslofName"  value="${csLongOrderFee.cslofName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长单缴费的项目名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****项目名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslofName">${csLongOrderFee.cslofName}</textarea>
		 		<span>
		 	
			 ${csLongOrderFee.cslofName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslofName}
	</s:if>
	
	${lz:set("注释","*****************费用类型字段的输入框代码*****************")}
	${lz:set("注释","before$cslofType和after$cslofType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslofType==true">
	${before$cslofType}
	<dl class="cslofType " major  ref="cslofType" >
		<dt>费用类型:</dt>
		<s:if test="#request.CTRL.e.cslofType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csLongOrderFee$cslofType!=null">${csLongOrderFee$cslofType}</s:if><s:else>
		 	<select class="narrow" id="cslofType" name="csLongOrderFee.cslofType">
		 		<option value="">请选择</option>
				<option value="0" ${csLongOrderFee.cslofType==0?"selected":""}>押金</option>
				<option value="1" ${csLongOrderFee.cslofType==1?"selected":""}>租金</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长单缴费的费用类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****费用类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslofType">${csLongOrderFee.cslofType}</textarea>
		 		<span>
		 	
			 ${csLongOrderFee.cslofType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslofType}
	</s:if>
	
	${lz:set("注释","*****************交费方式字段的输入框代码*****************")}
	${lz:set("注释","before$cslofPayType和after$cslofPayType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslofPayType==true">
	${before$cslofPayType}
	<dl class="cslofPayType " major  ref="cslofPayType" >
		<dt>交费方式:</dt>
		<s:if test="#request.CTRL.e.cslofPayType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csLongOrderFee$cslofPayType!=null">${csLongOrderFee$cslofPayType}</s:if><s:else>
		 	<select class="narrow" id="cslofPayType" name="csLongOrderFee.cslofPayType">
		 		<option value="">请选择</option>
				<option value="0" ${csLongOrderFee.cslofPayType==0?"selected":""}>现金支付</option>
				<option value="1" ${csLongOrderFee.cslofPayType==1?"selected":""}>转帐支付</option>
				<option value="2" ${csLongOrderFee.cslofPayType==2?"selected":""}>余额支付</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长单缴费的交费方式</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****交费方式字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslofPayType">${csLongOrderFee.cslofPayType}</textarea>
		 		<span>
		 	
			 ${csLongOrderFee.cslofPayType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslofPayType}
	</s:if>
	
	${lz:set("注释","*****************费用金额字段的输入框代码*****************")}
	${lz:set("注释","before$cslofAmmount和after$cslofAmmount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslofAmmount==true">
	${before$cslofAmmount}
	<dl class="cslofAmmount " major  ref="cslofAmmount" >
		<dt>费用金额:</dt>
		<s:if test="#request.CTRL.e.cslofAmmount==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csLongOrderFee$cslofAmmount!=null">${csLongOrderFee$cslofAmmount}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csLongOrderFee.cslofAmmount" id="cslofAmmount"  value="${csLongOrderFee.cslofAmmount}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长单缴费的费用金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****费用金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslofAmmount">${csLongOrderFee.cslofAmmount}</textarea>
		 		<span>
		 	
			 ${csLongOrderFee.cslofAmmount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslofAmmount}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$cslofRemark和after$cslofRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslofRemark==true">
	${before$cslofRemark}
	<dl class="cslofRemark " major  ref="cslofRemark" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.cslofRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csLongOrderFee$cslofRemark!=null">${csLongOrderFee$cslofRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="cslofRemark" name="csLongOrderFee.cslofRemark" rows="5">${csLongOrderFee.cslofRemark}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入长单缴费的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csLongOrderFee.cslofRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslofRemark}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cslofUpdateTime和after$cslofUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslofUpdateTime==true">
	${before$cslofUpdateTime}
	<dl class="cslofUpdateTime " major  ref="cslofUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cslofUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csLongOrderFee$cslofUpdateTime!=null">${csLongOrderFee$cslofUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csLongOrderFee.cslofUpdateTime" id="cslofUpdateTime"  value="<s:date name="csLongOrderFee.cslofUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长单缴费的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslofUpdateTime">${csLongOrderFee.cslofUpdateTime}</textarea>
		 		<span>
		 	
			 ${csLongOrderFee.cslofUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslofUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cslofAddTime和after$cslofAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslofAddTime==true">
	${before$cslofAddTime}
	<dl class="cslofAddTime " major  ref="cslofAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cslofAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csLongOrderFee$cslofAddTime!=null">${csLongOrderFee$cslofAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csLongOrderFee.cslofAddTime" id="cslofAddTime"  value="<s:date name="csLongOrderFee.cslofAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长单缴费的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslofAddTime">${csLongOrderFee.cslofAddTime}</textarea>
		 		<span>
		 	
			 ${csLongOrderFee.cslofAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslofAddTime}
	</s:if>
	
	${lz:set("注释","*****************入帐信息字段的输入框代码*****************")}
	${lz:set("注释","before$cslofStateInfo和after$cslofStateInfo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslofStateInfo==true">
	${before$cslofStateInfo}
	<dl class="cslofStateInfo " minor  ref="cslofStateInfo" style="width:98%;">
		<dt>入帐信息:</dt>
		<s:if test="#request.CTRL.e.cslofStateInfo==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csLongOrderFee$cslofStateInfo!=null">${csLongOrderFee$cslofStateInfo}</s:if><s:else>
		 	<textarea class="input wide"  id="cslofStateInfo" name="csLongOrderFee.cslofStateInfo" rows="5">${csLongOrderFee.cslofStateInfo}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入长单缴费的入帐信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****入帐信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csLongOrderFee.cslofStateInfo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslofStateInfo}
	</s:if>
	
	${lz:set("注释","*****************财务确认字段的输入框代码*****************")}
	${lz:set("注释","before$cslofState和after$cslofState变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslofState==true">
	${before$cslofState}
	<dl class="cslofState " major  ref="cslofState" >
		<dt>财务确认:</dt>
		<s:if test="#request.CTRL.e.cslofState==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csLongOrderFee$cslofState!=null">${csLongOrderFee$cslofState}</s:if><s:else>
		 	<select class="narrow" id="cslofState" name="csLongOrderFee.cslofState">
		 		<option value="">请选择</option>
				<option value="0" ${csLongOrderFee.cslofState==0?"selected":""}>待确认</option>
				<option value="1" ${csLongOrderFee.cslofState==1?"selected":""}>已确认</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长单缴费的财务确认</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****财务确认字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslofState">${csLongOrderFee.cslofState}</textarea>
		 		<span>
		 	
			 ${csLongOrderFee.cslofState$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslofState}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cslofStatus和after$cslofStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cslofStatus==true">
	${before$cslofStatus}
	<dl class="cslofStatus " major  ref="cslofStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cslofStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csLongOrderFee$cslofStatus!=null">${csLongOrderFee$cslofStatus}</s:if><s:else>
		 	<select class="narrow" id="cslofStatus" name="csLongOrderFee.cslofStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csLongOrderFee.cslofStatus==0?"selected":""}>已交款</option>
				<option value="1" ${csLongOrderFee.cslofStatus==1?"selected":""}>已退款</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择长单缴费的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cslofStatus">${csLongOrderFee.cslofStatus}</textarea>
		 		<span>
		 	
			 ${csLongOrderFee.cslofStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cslofStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csLongOrderFee.cslofId}" name="csLongOrderFee.cslofId" id="cslofId" />
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