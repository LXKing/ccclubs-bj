<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/finance/invoice.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/finance/invoice.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csInvoice.csiId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csiId,csiHost,csiTitle,csiContent,csiType,csiTariffNo,csiMoney,csiMember,csiSendType,csiAddress,csiRemark,csiStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csiId,csiHost,csiTitle,csiContent,csiType,csiTariffNo,csiMoney,csiMember,csiSendType,csiAddress,csiRemark,csiStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csiId,csiHost,csiTitle,csiContent,csiType,csiTariffNo,csiMoney,csiMember,csiSendType,csiAddress,csiRemark,csiInvoiceTime,csiStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csiId,csiHost,csiTitle,csiContent,csiType,csiTariffNo,csiMoney,csiMember,csiSendType,csiAddress,csiRemark,csiInvoiceTime,csiStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"发票记录编辑":CTRL.title}</title>
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
<s:if test="#request.CTRL.title=='审核'">
	<s:if test="csInvoice.csiStatus!=0">
		${lz:set("message","只有状态为'待审核'的才能进行审核操作")}
		<lz:set name="jsonString">
			{
				title:"审核",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:if>
	<s:else>		
		<lz:set name="message">
			${lz:set("invoice",get:invoice(csInvoice.csiMember))}
			当前会员总保证金消费额${lz:digit(invoice.consume,2)}元，
			已开票${lz:digit(invoice.already,2)}元，
			剩余可开票额${lz:digit(invoice.viable,2)}元
			<br>(以前数据仅供参考，不包含之前未记录在案的开票额)
		</lz:set>
		<lz:set name="jsonString">
			{
				title:"审核",
				editable:false,
				visible:false,
				action:"invoice_check.do",
				fields:{
					csiTitle:{editable:false,visible:true}
					,csiContent:{editable:false,visible:true}
					,csiType:{editable:false,visible:true}
					,csiMember:{editable:false,visible:true}
					,csiSendType:{editable:false,visible:true}
					,csiAddress:{editable:false,visible:true}
					,csiRemark:{editable:true,visible:true}
				},
				submits:[
					{name:"审核通过",value="审核通过"},
					{name:"审核不通过",value="审核不通过"}
				]
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:else>
</s:if>
<s:elseif test="#request.CTRL.title=='开票'">
	<s:if test="csInvoice.csiStatus!=1">
		${lz:set("message","只有状态为'已审核待开票'的才能进行开票操作")}
		<lz:set name="jsonString">
			{
				title:"开票",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:if>
	<s:else>
		<lz:set name="message">
			${lz:set("invoice",get:invoice(csInvoice.csiMember))}
			当前会员总保证金消费额${lz:digit(invoice.consume,2)}元，
			已开票${lz:digit(invoice.already,2)}元，
			剩余可开票额${lz:digit(invoice.viable,2)}元
			<br>(以前数据仅供参考，不包含之前未记录在案的开票额)
		</lz:set>	
		<lz:set name="jsonString">
			{
				title:"开票",
				editable:false,
				visible:false,
				action:"invoice_invoice.do",
				fields:{
					csiTitle:{editable:false,visible:true}
					,csiContent:{editable:false,visible:true}
					,csiType:{editable:false,visible:true}
					,csiTariffNo:{editable:false,visible:true}
					,csiMoney:{editable:true,visible:true}
					,csiMember:{editable:false,visible:true}
					,csiSendType:{editable:false,visible:true}
					,csiAddress:{editable:false,visible:true}
					,csiRemark:{editable:true,visible:true}
				},
				submits:[
					{name:"完成开票",value="完成开票"}
				]
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
		<script>
		$(function(){
			$(".form").submit(function(){
				if($("#csiMoney").val()==""){
					$.toast("请输入本次开票的金额");
					return false;					
				}
				if(Number($("#csiMoney").val())>Number("${lz:digit(invoice.viable,2)}")){
					if(!confirm("输入的开票额超过程序计算的可开票额，确定一定这样做吗？"))
						return false;
				}
			});
		});
		</script>
	</s:else>
</s:elseif>

${lz:set("showIframe",(CTRL.title=='审核' || CTRL.title=='开票'))}
<s:if test="#request.showIframe==true">
${lz:set("frameid",lz:uuid())}
<lz:set name="recordHtml">
	<dl style="width:98%;">
		<dt>消费列表:</dt>
		<dd>		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="iframe_${frameid}_record" name="iframe_${frameid}_record" frameborder='0' 
			src="" url="${basePath}admin/finance/record.do?byInvoice=true&csrMember=${csInvoice.csiMember}&CsRecordType={csrtMoneyType:0,csrtCanInvoice:true}&csrAmountMax=0&fields=csrRecordType,csrAmount,csrAddTime&ctrl={mutual:'dialog',canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" width='620' height='0'></iframe>
		</dd>
	</dl>
	<dl style="width:98%;">
		<dt>已开票列表:</dt>
		<dd>		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="iframe_${frameid}_invoice" name="iframe_${frameid}_invoice" frameborder='0' 
			src="" url="${basePath}admin/finance/invoice.do?csiMember=${csInvoice.csiMember}&csiStatus=2&fields=csiTitle,csiMember,csiType,csiMoney,csiInvoiceTime&ctrl={mutual:'dialog',canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" width='620' height='0'></iframe>
		</dd>
	</dl>
</lz:set>
<script>
window.$on("readyStart",function(){
	$("${lz:js(recordHtml)}").insertBefore("dl[ref=csiTitle]");		
});
</script>
</s:if>
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
	$("#invoiceForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csiId==true">
		${lz:set("haveEditable",true)}
		,"csInvoice.csiId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiHost==true">
		${lz:set("haveEditable",true)}
		,"csInvoice.csiHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiTitle==true">
		${lz:set("haveEditable",true)}
		,"csInvoice.csiTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "抬头名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "抬头名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiContent==true">
		${lz:set("haveEditable",true)}
		,"csInvoice.csiContent":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择开票内容";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiType==true">
		${lz:set("haveEditable",true)}
		,"csInvoice.csiType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择抬头类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiTariffNo==true">
		${lz:set("haveEditable",true)}
		,"csInvoice.csiTariffNo":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "税号最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiMoney==true">
		${lz:set("haveEditable",true)}
		,"csInvoice.csiMoney":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "开票额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiMember==true">
		${lz:set("haveEditable",true)}
		,"csInvoice.csiMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiSendType==true">
		${lz:set("haveEditable",true)}
		,"csInvoice.csiSendType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择寄送方式";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiAddress==true">
		${lz:set("haveEditable",true)}
		,"csInvoice.csiAddress":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiChecker==true">
		${lz:set("haveEditable",true)}
		,"csInvoice.csiChecker":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiEditor==true">
		${lz:set("haveEditable",true)}
		,"csInvoice.csiEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiAdder==true">
		${lz:set("haveEditable",true)}
		,"csInvoice.csiAdder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiRemark==true">
		${lz:set("haveEditable",true)}
		,"csInvoice.csiRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiCheckTime==true">
		${lz:set("haveEditable",true)}
		,"csInvoice.csiCheckTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiInvoiceTime==true">
		${lz:set("haveEditable",true)}
		,"csInvoice.csiInvoiceTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csInvoice.csiUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiAddTime==true">
		${lz:set("haveEditable",true)}
		,"csInvoice.csiAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csiStatus==true">
		${lz:set("haveEditable",true)}
		,"csInvoice.csiStatus":function(el){
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
	<form class="form editform" ref="CsInvoice" id="invoiceForm" name="invoiceForm" action="${empty CTRL.action?"invoice_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写发票记录相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csInvoiceToken}" name="csInvoiceToken" id="csInvoiceToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csInvoice.csiId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csiId和after$csiId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiId==true">
	${before$csiId}
	<dl class="csiId ${CTRL.e.csiId?"hidden":""}" major  ref="csiId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csiId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csInvoice$csiId!=null">${csInvoice$csiId}</s:if><s:else>
			<input type="hidden" value="${csInvoice.csiId}" name="csInvoice.csiId" id="csiId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiId">${csInvoice.csiId}</textarea>
		 		<span>
		 	
			 ${csInvoice.csiId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csiHost和after$csiHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiHost==true">
	${before$csiHost}
	<dl class="csiHost " major  ref="csiHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csiHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csInvoice$csiHost!=null">${csInvoice$csiHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csiHost" name="csInvoice.csiHost">
		 				<option selected value="${csInvoice.csiHost}">
		 					${get:SrvHost(csInvoice.csiHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csiHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csiHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiHost">${csInvoice.csiHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csInvoice.csiHost}',{ctrl:{editable:false,visible:true}})">
			 ${csInvoice.csiHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiHost}
	</s:if>
	
	${lz:set("注释","*****************抬头名称字段的输入框代码*****************")}
	${lz:set("注释","before$csiTitle和after$csiTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiTitle==true">
	${before$csiTitle}
	<dl class="csiTitle " major  ref="csiTitle" >
		<dt>抬头名称:</dt>
		<s:if test="#request.CTRL.e.csiTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csInvoice$csiTitle!=null">${csInvoice$csiTitle}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csInvoice.csiTitle" id="csiTitle"  value="${csInvoice.csiTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入发票记录的抬头名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****抬头名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiTitle">${csInvoice.csiTitle}</textarea>
		 		<span>
		 	
			 ${csInvoice.csiTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiTitle}
	</s:if>
	
	${lz:set("注释","*****************开票内容字段的输入框代码*****************")}
	${lz:set("注释","before$csiContent和after$csiContent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiContent==true">
	${before$csiContent}
	<dl class="csiContent " major  ref="csiContent" >
		<dt>开票内容:</dt>
		<s:if test="#request.CTRL.e.csiContent==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csInvoice$csiContent!=null">${csInvoice$csiContent}</s:if><s:else>
		 	<select class="narrow" id="csiContent" name="csInvoice.csiContent">
		 		<option value="">请选择</option>
 					${lz:set("items", get:propertys("发票开票内容","invoice_content"))}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.spId}" ${csInvoice.csiContent==item.spId?"selected":""}>${item.spName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csiContent').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?key='+$('#csiContent').val(),{ctrl:{editable:false}})"></a>
	 			<a class="reladd" config="{
	 				action:'${basePath}${proname}/configurator/property_edit.do?rd=1&parent=${get:property("invoice_content").spId}'
	 				,callback:function(object){
	 					$('<option selected value='+object.spId+'>'+object.spName+'</option>').appendTo('#csiContent');
	 				}
	 			}" href="javascript:void(0);"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择发票记录的开票内容</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****开票内容字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiContent">${csInvoice.csiContent}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${csInvoice.csiContent}',{ctrl:{editable:false,visible:true}})">
			 ${csInvoice.csiContent$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiContent}
	</s:if>
	
	${lz:set("注释","*****************抬头类型字段的输入框代码*****************")}
	${lz:set("注释","before$csiType和after$csiType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiType==true">
	${before$csiType}
	<dl class="csiType " major  ref="csiType" >
		<dt>抬头类型:</dt>
		<s:if test="#request.CTRL.e.csiType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csInvoice$csiType!=null">${csInvoice$csiType}</s:if><s:else>
		 	<select class="narrow" id="csiType" name="csInvoice.csiType">
		 		<option value="">请选择</option>
				<option value="0" ${csInvoice.csiType==0?"selected":""}>个人</option>
				<option value="1" ${csInvoice.csiType==1?"selected":""}>公司</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择发票记录的抬头类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****抬头类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiType">${csInvoice.csiType}</textarea>
		 		<span>
		 	
			 ${csInvoice.csiType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiType}
	</s:if>
	
	${lz:set("注释","*****************税号字段的输入框代码*****************")}
	${lz:set("注释","before$csiTariffNo和after$csiTariffNo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiTariffNo==true">
	${before$csiTariffNo}
	<dl class="csiTariffNo " minor  ref="csiTariffNo" >
		<dt>税　　号:</dt>
		<s:if test="#request.CTRL.e.csiTariffNo==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csInvoice$csiTariffNo!=null">${csInvoice$csiTariffNo}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csInvoice.csiTariffNo" id="csiTariffNo"  value="${csInvoice.csiTariffNo}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入发票记录的税号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****税号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiTariffNo">${csInvoice.csiTariffNo}</textarea>
		 		<span>
		 	
			 ${csInvoice.csiTariffNo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiTariffNo}
	</s:if>
	
	${lz:set("注释","*****************开票额字段的输入框代码*****************")}
	${lz:set("注释","before$csiMoney和after$csiMoney变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiMoney==true">
	${before$csiMoney}
	<dl class="csiMoney " major  ref="csiMoney" >
		<dt>开&nbsp;&nbsp;票&nbsp;&nbsp;额:</dt>
		<s:if test="#request.CTRL.e.csiMoney==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csInvoice$csiMoney!=null">${csInvoice$csiMoney}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csInvoice.csiMoney" id="csiMoney"  value="${csInvoice.csiMoney}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入发票记录的开票额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****开票额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiMoney">${csInvoice.csiMoney}</textarea>
		 		<span>
		 	
			 ${csInvoice.csiMoney$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiMoney}
	</s:if>
	
	${lz:set("注释","*****************所属会员字段的输入框代码*****************")}
	${lz:set("注释","before$csiMember和after$csiMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiMember==true">
	${before$csiMember}
	<dl class="csiMember " major  ref="csiMember" >
		<dt>所属会员:</dt>
		<s:if test="#request.CTRL.e.csiMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csInvoice$csiMember!=null">${csInvoice$csiMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={csiHost}" type="text" id="csiMember" name="csInvoice.csiMember" text="${get:CsMember(csInvoice.csiMember).csmName}" value="${csInvoice.csiMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csiMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csiMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiMember">${csInvoice.csiMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csInvoice.csiMember}',{ctrl:{editable:false,visible:true}})">
			 ${csInvoice.csiMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiMember}
	</s:if>
	
	${lz:set("注释","*****************寄送方式字段的输入框代码*****************")}
	${lz:set("注释","before$csiSendType和after$csiSendType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiSendType==true">
	${before$csiSendType}
	<dl class="csiSendType " major  ref="csiSendType" >
		<dt>寄送方式:</dt>
		<s:if test="#request.CTRL.e.csiSendType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csInvoice$csiSendType!=null">${csInvoice$csiSendType}</s:if><s:else>
		 	<select class="narrow" id="csiSendType" name="csInvoice.csiSendType">
		 		<option value="">请选择</option>
				<option value="0" ${csInvoice.csiSendType==0?"selected":""}>快递</option>
				<option value="1" ${csInvoice.csiSendType==1?"selected":""}>上门取件</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择发票记录的寄送方式</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****寄送方式字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiSendType">${csInvoice.csiSendType}</textarea>
		 		<span>
		 	
			 ${csInvoice.csiSendType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiSendType}
	</s:if>
	
	${lz:set("注释","*****************寄送地址字段的输入框代码*****************")}
	${lz:set("注释","before$csiAddress和after$csiAddress变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiAddress==true">
	${before$csiAddress}
	<dl class="csiAddress " minor  ref="csiAddress" >
		<dt>寄送地址:</dt>
		<s:if test="#request.CTRL.e.csiAddress==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csInvoice$csiAddress!=null">${csInvoice$csiAddress}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/user/address_query.do?size=-1&csaMember={csiMember}" id="csiAddress" name="csInvoice.csiAddress">
		 				<option selected value="${csInvoice.csiAddress}">
		 					${get:CsAddress(csInvoice.csiAddress).csaAddress}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csiAddress').val())==''){return;};window.href('${basePath}${proname}/user/address_details.do?key='+$('#csiAddress').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****寄送地址字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiAddress">${csInvoice.csiAddress}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/address_details.do?key=${csInvoice.csiAddress}',{ctrl:{editable:false,visible:true}})">
			 ${csInvoice.csiAddress$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiAddress}
	</s:if>
	
	${lz:set("注释","*****************审核人字段的输入框代码*****************")}
	${lz:set("注释","before$csiChecker和after$csiChecker变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiChecker==true">
	${before$csiChecker}
	<dl class="csiChecker " minor  ref="csiChecker" >
		<dt>审&nbsp;&nbsp;核&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csiChecker==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csInvoice$csiChecker!=null">${csInvoice$csiChecker}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csiChecker" name="csInvoice.csiChecker">
		 				<option selected value="${csInvoice.csiChecker}">
		 					${get:SrvUser(csInvoice.csiChecker).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csiChecker').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csiChecker').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****审核人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiChecker">${csInvoice.csiChecker}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csInvoice.csiChecker}',{ctrl:{editable:false,visible:true}})">
			 ${csInvoice.csiChecker$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiChecker}
	</s:if>
	
	${lz:set("注释","*****************开票人字段的输入框代码*****************")}
	${lz:set("注释","before$csiEditor和after$csiEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiEditor==true">
	${before$csiEditor}
	<dl class="csiEditor " minor  ref="csiEditor" >
		<dt>开&nbsp;&nbsp;票&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csiEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csInvoice$csiEditor!=null">${csInvoice$csiEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csiEditor" name="csInvoice.csiEditor">
		 				<option selected value="${csInvoice.csiEditor}">
		 					${get:SrvUser(csInvoice.csiEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csiEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csiEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****开票人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiEditor">${csInvoice.csiEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csInvoice.csiEditor}',{ctrl:{editable:false,visible:true}})">
			 ${csInvoice.csiEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiEditor}
	</s:if>
	
	${lz:set("注释","*****************添加人字段的输入框代码*****************")}
	${lz:set("注释","before$csiAdder和after$csiAdder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiAdder==true">
	${before$csiAdder}
	<dl class="csiAdder " major  ref="csiAdder" >
		<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csiAdder==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csInvoice$csiAdder!=null">${csInvoice$csiAdder}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csiAdder" name="csInvoice.csiAdder">
		 				<option selected value="${csInvoice.csiAdder}">
		 					${get:SrvUser(csInvoice.csiAdder).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csiAdder').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csiAdder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiAdder">${csInvoice.csiAdder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csInvoice.csiAdder}',{ctrl:{editable:false,visible:true}})">
			 ${csInvoice.csiAdder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiAdder}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$csiRemark和after$csiRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiRemark==true">
	${before$csiRemark}
	<dl class="csiRemark " minor  ref="csiRemark" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.csiRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csInvoice$csiRemark!=null">${csInvoice$csiRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csiRemark" name="csInvoice.csiRemark" rows="5">${csInvoice.csiRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入发票记录的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csInvoice.csiRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiRemark}
	</s:if>
	
	${lz:set("注释","*****************审核时间字段的输入框代码*****************")}
	${lz:set("注释","before$csiCheckTime和after$csiCheckTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiCheckTime==true">
	${before$csiCheckTime}
	<dl class="csiCheckTime " major  ref="csiCheckTime" >
		<dt>审核时间:</dt>
		<s:if test="#request.CTRL.e.csiCheckTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csInvoice$csiCheckTime!=null">${csInvoice$csiCheckTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csInvoice.csiCheckTime" id="csiCheckTime"  value="<s:date name="csInvoice.csiCheckTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择发票记录的审核时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****审核时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiCheckTime">${csInvoice.csiCheckTime}</textarea>
		 		<span>
		 	
			 ${csInvoice.csiCheckTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiCheckTime}
	</s:if>
	
	${lz:set("注释","*****************开票时间字段的输入框代码*****************")}
	${lz:set("注释","before$csiInvoiceTime和after$csiInvoiceTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiInvoiceTime==true">
	${before$csiInvoiceTime}
	<dl class="csiInvoiceTime " major  ref="csiInvoiceTime" >
		<dt>开票时间:</dt>
		<s:if test="#request.CTRL.e.csiInvoiceTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csInvoice$csiInvoiceTime!=null">${csInvoice$csiInvoiceTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csInvoice.csiInvoiceTime" id="csiInvoiceTime"  value="<s:date name="csInvoice.csiInvoiceTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择发票记录的开票时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****开票时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiInvoiceTime">${csInvoice.csiInvoiceTime}</textarea>
		 		<span>
		 	
			 ${csInvoice.csiInvoiceTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiInvoiceTime}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csiUpdateTime和after$csiUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiUpdateTime==true">
	${before$csiUpdateTime}
	<dl class="csiUpdateTime " major  ref="csiUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csiUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csInvoice$csiUpdateTime!=null">${csInvoice$csiUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csInvoice.csiUpdateTime" id="csiUpdateTime"  value="<s:date name="csInvoice.csiUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择发票记录的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiUpdateTime">${csInvoice.csiUpdateTime}</textarea>
		 		<span>
		 	
			 ${csInvoice.csiUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csiAddTime和after$csiAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiAddTime==true">
	${before$csiAddTime}
	<dl class="csiAddTime " major  ref="csiAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csiAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csInvoice$csiAddTime!=null">${csInvoice$csiAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csInvoice.csiAddTime" id="csiAddTime"  value="<s:date name="csInvoice.csiAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择发票记录的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiAddTime">${csInvoice.csiAddTime}</textarea>
		 		<span>
		 	
			 ${csInvoice.csiAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csiStatus和after$csiStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csiStatus==true">
	${before$csiStatus}
	<dl class="csiStatus " major  ref="csiStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csiStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csInvoice$csiStatus!=null">${csInvoice$csiStatus}</s:if><s:else>
		 	<select class="narrow" id="csiStatus" name="csInvoice.csiStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csInvoice.csiStatus==0?"selected":""}>待审核</option>
				<option value="1" ${csInvoice.csiStatus==1?"selected":""}>已审核待开票</option>
				<option value="2" ${csInvoice.csiStatus==2?"selected":""}>已开票</option>
				<option value="3" ${csInvoice.csiStatus==3?"selected":""}>审核未通过</option>
				<option value="4" ${csInvoice.csiStatus==4?"selected":""}>取消</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择发票记录的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csiStatus">${csInvoice.csiStatus}</textarea>
		 		<span>
		 	
			 ${csInvoice.csiStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csiStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csInvoice.csiId}" name="csInvoice.csiId" id="csiId" />
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