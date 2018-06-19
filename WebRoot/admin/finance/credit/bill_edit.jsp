<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/finance/credit/bill.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/finance/credit/bill.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csCreditBill.cscbId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cscbId,cscbHost,cscbTitle,cscbMember,cscbCreditCard,cscbOrder,cscbViolat,cscbTrouble,cscbValue,cscbPayment,cscbRepayTime,cscbRemark,cscbProcess",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscbId,cscbHost,cscbTitle,cscbMember,cscbCreditCard,cscbOrder,cscbViolat,cscbTrouble,cscbValue,cscbPayment,cscbRepayTime,cscbRemark,cscbProcess",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cscbId,cscbHost,cscbTitle,cscbMember,cscbCreditCard,cscbOrder,cscbViolat,cscbTrouble,cscbValue,cscbPayment,cscbRepayTime,cscbRemark,cscbProcess,cscbPaySerial",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscbId,cscbHost,cscbTitle,cscbMember,cscbCreditCard,cscbOrder,cscbViolat,cscbTrouble,cscbValue,cscbPayment,cscbRepayTime,cscbRemark,cscbProcess,cscbPaySerial",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"信用账单编辑":CTRL.title}</title>
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
<s:if test="#request.CTRL.title=='帐户代付'">
	${lz:set("amount",(get:amount(csCreditBill.cscbMember)))}
	<s:if test="csCreditBill.cscbStatus!=0">
		${lz:set("message","只有待还款的帐单才能帐户代付")}
		<lz:set name="jsonString">
			{title:"帐户代付",editable:false,	visible:false}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:if>
	<s:elseif test="#request.amount < csCreditBill.cscbValue">
		${lz:set("message","会员帐户余额不足，不能代付")}
		<lz:set name="jsonString">
			{title:"无法帐户代付",editable:false,	visible:false}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:elseif>
	<s:else>
		${lz:set("message","此操作将会直接从会员帐户余额里扣除同等金额")}
		<lz:set name="jsonString">
			{
				title:"帐户代付",
				editable:false,
				action:"bill_repay.do",	
				fields:{
					cscbState:{visible:false},
					cscbProcess:{visible:false},
					cscbCreditCard:{visible:false},
					cscbPaySerial:{visible:false},
					cscbRemark:{editable:true,visible:true}
				},
				submits:[
					{name:"帐户代付",value="帐户代付"}
				]
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:else>
	<script>
	$(function(){
		$("#cscbRemark").val("");
		$("<b>*</b>").insertAfter("#cscbRemark");
	});
	</script>
</s:if>
<s:if test="#request.CTRL.title=='撤销帐单'">
	<s:if test="csCreditBill.cscbStatus!=0">
		${lz:set("message","只有待还款的帐单才能撤销帐单")}
		<lz:set name="jsonString">
			{
				title:"撤销帐单",
				editable:false,				
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:if>
	<s:else>
		${lz:set("message","确定该帐单要撤销吗，想好了你再做哦")}
		<lz:set name="jsonString">
			{
				title:"撤销帐单",
				editable:false,
				action:"bill_cancel.do",
				fields:{
					cscbState:{visible:false},
					cscbProcess:{visible:false},
					cscbCreditCard:{visible:false},
					cscbPaySerial:{visible:false},
					cscbRemark:{editable:true,visible:true}
				},
				submits:[
					{name:"撤销帐单",value="撤销帐单"}
				]
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:else>
	<script>
	$(function(){
		$("#cscbRemark").val("");
		$("<b>*</b>").insertAfter("#cscbRemark");
	});
	</script>
</s:if>
<s:if test="#request.CTRL.title=='坏帐处理'">
	<s:if test="csCreditBill.cscbStatus!=0">
		${lz:set("message","只有待还款的帐单才能坏帐处理")}
		<lz:set name="jsonString">
			{
				title:"坏帐处理",
				editable:false,				
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:if>
	<s:else>
		${lz:set("message","确定该帐单已经追讨不回了吗？(ㄒoㄒ)...")}
		<lz:set name="jsonString">
			{
				title:"坏帐处理",
				editable:false,
				action:"bill_bad.do",
				fields:{
					cscbState:{visible:false},
					cscbProcess:{visible:false},
					cscbCreditCard:{visible:false},
					cscbPaySerial:{visible:false},
					cscbRemark:{editable:true,visible:true}
				},
				submits:[
					{name:"坏帐处理",value="坏帐处理"}
				]
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:else>
	<script>
	$(function(){
		$("#cscbRemark").val("");
		$("<b>*</b>").insertAfter("#cscbRemark");
	});
	</script>
</s:if>
<s:if test="#request.CTRL.title=='已付处理'">
	<s:if test="csCreditBill.cscbStatus!=0">
		${lz:set("message","只有待还款的帐单才能已付处理")}
		<lz:set name="jsonString">
			{
				title:"已付处理",
				editable:false,				
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:if>
	<s:else>
		${lz:set("message","已付处理表示会员已经支付了，但是未同步到系统")}
		<lz:set name="jsonString">
			{
				title:"已付处理",
				editable:false,
				action:"bill_payed.do",
				fields:{
					cscbState:{visible:false},
					cscbProcess:{visible:false},
					cscbCreditCard:{visible:false},
					cscbPaySerial:{editable:true,visible:true},
					cscbRemark:{editable:true,visible:true}
				},
				submits:[
					{name:"已付处理",value="已付处理"}
				]
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:else>
	<script>
	$(function(){
		$("#cscbRemark").val("");
		$("<b>*</b>").insertAfter("#cscbRemark");
		$("<b>*</b>").insertAfter("#cscbPaySerial");
	});
	</script>
</s:if>



<s:if test="#request.CTRL.title=='批量处理已付'">
	<lz:set name="jsonString">
		{
			title:"批量处理已付",
			action:"bill_batch.do",
			editable:false,
			visible:false,
			fields:{
				cscbRemark:{editable:true,visible:true}
			}
		}
	</lz:set>
	${lz:set("CTRL",lz:CTRL(jsonString))}
	<lz:set name="before$buttons">
		<center>
			2003格式excel:
		 	<input type="file" name="batch" id="batch" /></br>
	 	 	<b>*</b>
	 		<em>第一列为交易号,第二列订单号,第三列为发生金额</em>
		</center>
		<br/><br/>
	</lz:set>
	<script>
	$(function(){
		$(".form").attr("enctype","multipart/form-data");
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
	$("#billForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cscbId==true">
		${lz:set("haveEditable",true)}
		,"csCreditBill.cscbId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscbHost==true">
		${lz:set("haveEditable",true)}
		,"csCreditBill.cscbHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscbTitle==true">
		${lz:set("haveEditable",true)}
		,"csCreditBill.cscbTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "账单描述不能为空";
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "账单描述最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscbMember==true">
		${lz:set("haveEditable",true)}
		,"csCreditBill.cscbMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscbCreditCard==true">
		${lz:set("haveEditable",true)}
		,"csCreditBill.cscbCreditCard":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscbOrder==true">
		${lz:set("haveEditable",true)}
		,"csCreditBill.cscbOrder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscbViolat==true">
		${lz:set("haveEditable",true)}
		,"csCreditBill.cscbViolat":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscbTrouble==true">
		${lz:set("haveEditable",true)}
		,"csCreditBill.cscbTrouble":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscbValue==true">
		${lz:set("haveEditable",true)}
		,"csCreditBill.cscbValue":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "总欠账额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscbPayment==true">
		${lz:set("haveEditable",true)}
		,"csCreditBill.cscbPayment":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "已付额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscbRepayTime==true">
		${lz:set("haveEditable",true)}
		,"csCreditBill.cscbRepayTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscbUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csCreditBill.cscbUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscbAddTime==true">
		${lz:set("haveEditable",true)}
		,"csCreditBill.cscbAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscbEditor==true">
		${lz:set("haveEditable",true)}
		,"csCreditBill.cscbEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscbRemark==true">
		${lz:set("haveEditable",true)}
		,"csCreditBill.cscbRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscbProcess==true">
		${lz:set("haveEditable",true)}
		,"csCreditBill.cscbProcess":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscbPaySerial==true">
		${lz:set("haveEditable",true)}
		,"csCreditBill.cscbPaySerial":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "还款交易号最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscbState==true">
		${lz:set("haveEditable",true)}
		,"csCreditBill.cscbState":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscbStatus==true">
		${lz:set("haveEditable",true)}
		,"csCreditBill.cscbStatus":function(el){
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
	<form class="form editform" ref="CsCreditBill" id="billForm" name="billForm" action="${empty CTRL.action?"bill_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写信用账单相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csCreditBillToken}" name="csCreditBillToken" id="csCreditBillToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csCreditBill.cscbId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cscbId和after$cscbId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscbId==true">
	${before$cscbId}
	<dl class="cscbId ${CTRL.e.cscbId?"hidden":""}" major  ref="cscbId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cscbId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csCreditBill$cscbId!=null">${csCreditBill$cscbId}</s:if><s:else>
			<input type="hidden" value="${csCreditBill.cscbId}" name="csCreditBill.cscbId" id="cscbId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscbId">${csCreditBill.cscbId}</textarea>
		 		<span>
		 	
			 ${csCreditBill.cscbId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscbId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cscbHost和after$cscbHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscbHost==true">
	${before$cscbHost}
	<dl class="cscbHost " major  ref="cscbHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cscbHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCreditBill$cscbHost!=null">${csCreditBill$cscbHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cscbHost" name="csCreditBill.cscbHost">
		 				<option selected value="${csCreditBill.cscbHost}">
		 					${get:SrvHost(csCreditBill.cscbHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscbHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cscbHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscbHost">${csCreditBill.cscbHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csCreditBill.cscbHost}',{ctrl:{editable:false,visible:true}})">
			 ${csCreditBill.cscbHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscbHost}
	</s:if>
	
	${lz:set("注释","*****************账单描述字段的输入框代码*****************")}
	${lz:set("注释","before$cscbTitle和after$cscbTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscbTitle==true">
	${before$cscbTitle}
	<dl class="cscbTitle " major  ref="cscbTitle" style="width:98%;">
		<dt>账单描述:</dt>
		<s:if test="#request.CTRL.e.cscbTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCreditBill$cscbTitle!=null">${csCreditBill$cscbTitle}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csCreditBill.cscbTitle" id="cscbTitle"  value="${csCreditBill.cscbTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入信用账单的账单描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****账单描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscbTitle">${csCreditBill.cscbTitle}</textarea>
		 		<span>
		 	
			 ${csCreditBill.cscbTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscbTitle}
	</s:if>
	
	${lz:set("注释","*****************所属会员字段的输入框代码*****************")}
	${lz:set("注释","before$cscbMember和after$cscbMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscbMember==true">
	${before$cscbMember}
	<dl class="cscbMember " major  ref="cscbMember" >
		<dt>所属会员:</dt>
		<s:if test="#request.CTRL.e.cscbMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csCreditBill$cscbMember!=null">${csCreditBill$cscbMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={cscbHost}" type="text" id="cscbMember" name="csCreditBill.cscbMember" text="${get:CsMember(csCreditBill.cscbMember).csmName}" value="${csCreditBill.cscbMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscbMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#cscbMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscbMember">${csCreditBill.cscbMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csCreditBill.cscbMember}',{ctrl:{editable:false,visible:true}})">
			 ${csCreditBill.cscbMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscbMember}
	</s:if>
	
	${lz:set("注释","*****************所属信用卡字段的输入框代码*****************")}
	${lz:set("注释","before$cscbCreditCard和after$cscbCreditCard变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscbCreditCard==true">
	${before$cscbCreditCard}
	<dl class="cscbCreditCard " major  ref="cscbCreditCard" >
		<dt>所属信用卡:</dt>
		<s:if test="#request.CTRL.e.cscbCreditCard==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csCreditBill$cscbCreditCard!=null">${csCreditBill$cscbCreditCard}</s:if><s:else>
		 			<input title="请输入信用卡信用卡号进行查询" class="combox narrow" action="${basePath}${proname}/user/credit/card_query.do?value={param}" type="text" id="cscbCreditCard" name="csCreditBill.cscbCreditCard" text="${get:CsCreditCard(csCreditBill.cscbCreditCard).csccNo}" value="${csCreditBill.cscbCreditCard}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscbCreditCard').val())==''){return;};window.href('${basePath}${proname}/user/credit/card_details.do?key='+$('#cscbCreditCard').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属信用卡字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscbCreditCard">${csCreditBill.cscbCreditCard}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/credit/card_details.do?key=${csCreditBill.cscbCreditCard}',{ctrl:{editable:false,visible:true}})">
			 ${csCreditBill.cscbCreditCard$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscbCreditCard}
	</s:if>
	
	${lz:set("注释","*****************关联订单字段的输入框代码*****************")}
	${lz:set("注释","before$cscbOrder和after$cscbOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscbOrder==true">
	${before$cscbOrder}
	<dl class="cscbOrder " major  ref="cscbOrder" >
		<dt>关联订单:</dt>
		<s:if test="#request.CTRL.e.cscbOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csCreditBill$cscbOrder!=null">${csCreditBill$cscbOrder}</s:if><s:else>
		 			<input title="请输入系统订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={cscbHost}" type="text" id="cscbOrder" name="csCreditBill.cscbOrder" text="${get:CsOrder(csCreditBill.cscbOrder).csoId}" value="${csCreditBill.cscbOrder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscbOrder').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?key='+$('#cscbOrder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscbOrder">${csCreditBill.cscbOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?key=${csCreditBill.cscbOrder}',{ctrl:{editable:false,visible:true}})">
			 ${csCreditBill.cscbOrder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscbOrder}
	</s:if>
	
	${lz:set("注释","*****************关联违章字段的输入框代码*****************")}
	${lz:set("注释","before$cscbViolat和after$cscbViolat变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscbViolat==true">
	${before$cscbViolat}
	<dl class="cscbViolat " minor  ref="cscbViolat" >
		<dt>关联违章:</dt>
		<s:if test="#request.CTRL.e.cscbViolat==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csCreditBill$cscbViolat!=null">${csCreditBill$cscbViolat}</s:if><s:else>
		 			<input title="请输入车辆违章编号进行查询" class="combox narrow" action="${basePath}${proname}/service/violat_query.do?value={param}&csvHost={cscbHost}" type="text" id="cscbViolat" name="csCreditBill.cscbViolat" text="${get:CsViolat(csCreditBill.cscbViolat).csvId}" value="${csCreditBill.cscbViolat}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscbViolat').val())==''){return;};window.href('${basePath}${proname}/service/violat_details.do?key='+$('#cscbViolat').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联违章字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscbViolat">${csCreditBill.cscbViolat}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/violat_details.do?key=${csCreditBill.cscbViolat}',{ctrl:{editable:false,visible:true}})">
			 ${csCreditBill.cscbViolat$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscbViolat}
	</s:if>
	
	${lz:set("注释","*****************关联事故字段的输入框代码*****************")}
	${lz:set("注释","before$cscbTrouble和after$cscbTrouble变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscbTrouble==true">
	${before$cscbTrouble}
	<dl class="cscbTrouble " minor  ref="cscbTrouble" >
		<dt>关联事故:</dt>
		<s:if test="#request.CTRL.e.cscbTrouble==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csCreditBill$cscbTrouble!=null">${csCreditBill$cscbTrouble}</s:if><s:else>
		 			<input title="请输入车辆事故编号进行查询" class="combox narrow" action="${basePath}${proname}/service/trouble_query.do?value={param}&cstHost={cscbHost}" type="text" id="cscbTrouble" name="csCreditBill.cscbTrouble" text="${get:CsTrouble(csCreditBill.cscbTrouble).cstId}" value="${csCreditBill.cscbTrouble}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscbTrouble').val())==''){return;};window.href('${basePath}${proname}/service/trouble_details.do?key='+$('#cscbTrouble').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联事故字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscbTrouble">${csCreditBill.cscbTrouble}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/trouble_details.do?key=${csCreditBill.cscbTrouble}',{ctrl:{editable:false,visible:true}})">
			 ${csCreditBill.cscbTrouble$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscbTrouble}
	</s:if>
	
	${lz:set("注释","*****************总欠账额字段的输入框代码*****************")}
	${lz:set("注释","before$cscbValue和after$cscbValue变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscbValue==true">
	${before$cscbValue}
	<dl class="cscbValue " major  ref="cscbValue" >
		<dt>总欠账额:</dt>
		<s:if test="#request.CTRL.e.cscbValue==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csCreditBill$cscbValue!=null">${csCreditBill$cscbValue}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csCreditBill.cscbValue" id="cscbValue"  value="${csCreditBill.cscbValue}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入信用账单的总欠账额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****总欠账额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscbValue">${csCreditBill.cscbValue}</textarea>
		 		<span>
		 	
			 ${csCreditBill.cscbValue$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscbValue}
	</s:if>
	
	${lz:set("注释","*****************已付额字段的输入框代码*****************")}
	${lz:set("注释","before$cscbPayment和after$cscbPayment变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscbPayment==true">
	${before$cscbPayment}
	<dl class="cscbPayment " major  ref="cscbPayment" >
		<dt>已&nbsp;&nbsp;付&nbsp;&nbsp;额:</dt>
		<s:if test="#request.CTRL.e.cscbPayment==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csCreditBill$cscbPayment!=null">${csCreditBill$cscbPayment}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csCreditBill.cscbPayment" id="cscbPayment"  value="${csCreditBill.cscbPayment}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入信用账单的已付额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****已付额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscbPayment">${csCreditBill.cscbPayment}</textarea>
		 		<span>
		 	
			 ${csCreditBill.cscbPayment$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscbPayment}
	</s:if>
	
	${lz:set("注释","*****************还款时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscbRepayTime和after$cscbRepayTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscbRepayTime==true">
	${before$cscbRepayTime}
	<dl class="cscbRepayTime " major  ref="cscbRepayTime" >
		<dt>还款时间:</dt>
		<s:if test="#request.CTRL.e.cscbRepayTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCreditBill$cscbRepayTime!=null">${csCreditBill$cscbRepayTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCreditBill.cscbRepayTime" id="cscbRepayTime"  value="<s:date name="csCreditBill.cscbRepayTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择信用账单的还款时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****还款时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscbRepayTime">${csCreditBill.cscbRepayTime}</textarea>
		 		<span>
		 	
			 ${csCreditBill.cscbRepayTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscbRepayTime}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscbUpdateTime和after$cscbUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscbUpdateTime==true">
	${before$cscbUpdateTime}
	<dl class="cscbUpdateTime " major  ref="cscbUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cscbUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCreditBill$cscbUpdateTime!=null">${csCreditBill$cscbUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCreditBill.cscbUpdateTime" id="cscbUpdateTime"  value="<s:date name="csCreditBill.cscbUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择信用账单的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscbUpdateTime">${csCreditBill.cscbUpdateTime}</textarea>
		 		<span>
		 	
			 ${csCreditBill.cscbUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscbUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscbAddTime和after$cscbAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscbAddTime==true">
	${before$cscbAddTime}
	<dl class="cscbAddTime " major  ref="cscbAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cscbAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCreditBill$cscbAddTime!=null">${csCreditBill$cscbAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCreditBill.cscbAddTime" id="cscbAddTime"  value="<s:date name="csCreditBill.cscbAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择信用账单的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscbAddTime">${csCreditBill.cscbAddTime}</textarea>
		 		<span>
		 	
			 ${csCreditBill.cscbAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscbAddTime}
	</s:if>
	
	${lz:set("注释","*****************操作人员字段的输入框代码*****************")}
	${lz:set("注释","before$cscbEditor和after$cscbEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscbEditor==true">
	${before$cscbEditor}
	<dl class="cscbEditor " major  ref="cscbEditor" >
		<dt>操作人员:</dt>
		<s:if test="#request.CTRL.e.cscbEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCreditBill$cscbEditor!=null">${csCreditBill$cscbEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cscbEditor" name="csCreditBill.cscbEditor">
		 				<option selected value="${csCreditBill.cscbEditor}">
		 					${get:SrvUser(csCreditBill.cscbEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscbEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cscbEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****操作人员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscbEditor">${csCreditBill.cscbEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csCreditBill.cscbEditor}',{ctrl:{editable:false,visible:true}})">
			 ${csCreditBill.cscbEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscbEditor}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$cscbRemark和after$cscbRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscbRemark==true">
	${before$cscbRemark}
	<dl class="cscbRemark " major  ref="cscbRemark" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.cscbRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csCreditBill$cscbRemark!=null">${csCreditBill$cscbRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="cscbRemark" name="csCreditBill.cscbRemark" rows="5">${csCreditBill.cscbRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入信用账单的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csCreditBill.cscbRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscbRemark}
	</s:if>
	
	${lz:set("注释","*****************跟踪信息字段的输入框代码*****************")}
	${lz:set("注释","before$cscbProcess和after$cscbProcess变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscbProcess==true">
	${before$cscbProcess}
	<dl class="cscbProcess " major  ref="cscbProcess" style="width:98%;">
		<dt>跟踪信息:</dt>
		<s:if test="#request.CTRL.e.cscbProcess==true">
		${lz:set("haveEditable",true)}
		<dd input="checkbox">
		<s:if test="#request.csCreditBill$cscbProcess!=null">${csCreditBill$cscbProcess}</s:if><s:else>
		 	<div class='wide input'>	
		 	&nbsp; 	
		 	<input type="hidden" value="#0#" name="csCreditBill.cscbProcess" id="cscbProcess"/>
			<label><input type="checkbox" ${lz:idin(csCreditBill.cscbProcess,1) ? "checked='checked'" : ""} value="#1#" name="csCreditBill.cscbProcess" id="cscbProcess"/>催缴短信已发&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:idin(csCreditBill.cscbProcess,2) ? "checked='checked'" : ""} value="#2#" name="csCreditBill.cscbProcess" id="cscbProcess"/>催缴电话已打&nbsp;&nbsp;&nbsp;</label>
		 </div>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择信用账单的跟踪信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****跟踪信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscbProcess">${csCreditBill.cscbProcess}</textarea>
		 		<span>
		 	
			 ${csCreditBill.cscbProcess$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscbProcess}
	</s:if>
	
	${lz:set("注释","*****************还款交易号字段的输入框代码*****************")}
	${lz:set("注释","before$cscbPaySerial和after$cscbPaySerial变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscbPaySerial==true">
	${before$cscbPaySerial}
	<dl class="cscbPaySerial " minor  ref="cscbPaySerial" >
		<dt>还款交易号:</dt>
		<s:if test="#request.CTRL.e.cscbPaySerial==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCreditBill$cscbPaySerial!=null">${csCreditBill$cscbPaySerial}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCreditBill.cscbPaySerial" id="cscbPaySerial"  value="${csCreditBill.cscbPaySerial}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>三方状态同步返回的交易号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****还款交易号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscbPaySerial">${csCreditBill.cscbPaySerial}</textarea>
		 		<span>
		 	
			 ${csCreditBill.cscbPaySerial$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscbPaySerial}
	</s:if>
	
	${lz:set("注释","*****************同步状态字段的输入框代码*****************")}
	${lz:set("注释","before$cscbState和after$cscbState变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscbState==true">
	${before$cscbState}
	<dl class="cscbState " minor  ref="cscbState" >
		<dt>同步状态:</dt>
		<s:if test="#request.CTRL.e.cscbState==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCreditBill$cscbState!=null">${csCreditBill$cscbState}</s:if><s:else>
		 	<select class="narrow" id="cscbState" name="csCreditBill.cscbState">
		 		<option value="">请选择</option>
				<option value="0" ${csCreditBill.cscbState==0?"selected":""}>未同步</option>
				<option value="1" ${csCreditBill.cscbState==1?"selected":""}>已同步</option>
				<option value="2" ${csCreditBill.cscbState==2?"selected":""}>不同步</option>
				<option value="3" ${csCreditBill.cscbState==3?"selected":""}>待撤单</option>
				<option value="4" ${csCreditBill.cscbState==4?"selected":""}>已撤单</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>描述此帐单是否已同步到第三方</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****同步状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscbState">${csCreditBill.cscbState}</textarea>
		 		<span>
		 	
			 ${csCreditBill.cscbState$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscbState}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cscbStatus和after$cscbStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscbStatus==true">
	${before$cscbStatus}
	<dl class="cscbStatus " major  ref="cscbStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cscbStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCreditBill$cscbStatus!=null">${csCreditBill$cscbStatus}</s:if><s:else>
		 	<select class="narrow" id="cscbStatus" name="csCreditBill.cscbStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csCreditBill.cscbStatus==0?"selected":""}>待还款</option>
				<option value="1" ${csCreditBill.cscbStatus==1?"selected":""}>已还款</option>
				<option value="2" ${csCreditBill.cscbStatus==2?"selected":""}>已关闭</option>
				<option value="3" ${csCreditBill.cscbStatus==3?"selected":""}>已代付</option>
				<option value="4" ${csCreditBill.cscbStatus==4?"selected":""}>已坏帐</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择信用账单的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscbStatus">${csCreditBill.cscbStatus}</textarea>
		 		<span>
		 	
			 ${csCreditBill.cscbStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscbStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csCreditBill.cscbId}" name="csCreditBill.cscbId" id="cscbId" />
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