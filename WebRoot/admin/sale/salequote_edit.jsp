<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/sale/salequote.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/sale/salequote.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty tbSaleQuote.tbsqId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"tbsqId,tbsqHost,tbsqTitle,tbsqOpport,tbsqDetail,tbsqFile,tbsqStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"tbsqId,tbsqHost,tbsqTitle,tbsqOpport,tbsqDetail,tbsqFile,tbsqStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"tbsqId,tbsqHost,tbsqTitle,tbsqOpport,tbsqDetail,tbsqFile,tbsqStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"tbsqId,tbsqHost,tbsqTitle,tbsqOpport,tbsqDetail,tbsqFile,tbsqAdder,tbsqEditor,tbsqStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"跟单报价编辑":CTRL.title}</title>
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
	$("#salequoteForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.tbsqId==true">
		${lz:set("haveEditable",true)}
		,"tbSaleQuote.tbsqId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsqHost==true">
		${lz:set("haveEditable",true)}
		,"tbSaleQuote.tbsqHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsqTitle==true">
		${lz:set("haveEditable",true)}
		,"tbSaleQuote.tbsqTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "报价标题不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "报价标题最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsqOpport==true">
		${lz:set("haveEditable",true)}
		,"tbSaleQuote.tbsqOpport":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsqDetail==true">
		${lz:set("haveEditable",true)}
		,"tbSaleQuote.tbsqDetail":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsqFile==true">
		${lz:set("haveEditable",true)}
		,"tbSaleQuote.tbsqFile":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "报价附件最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsqOnwer==true">
		${lz:set("haveEditable",true)}
		,"tbSaleQuote.tbsqOnwer":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsqAdder==true">
		${lz:set("haveEditable",true)}
		,"tbSaleQuote.tbsqAdder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsqEditor==true">
		${lz:set("haveEditable",true)}
		,"tbSaleQuote.tbsqEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsqUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"tbSaleQuote.tbsqUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsqAddTime==true">
		${lz:set("haveEditable",true)}
		,"tbSaleQuote.tbsqAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsqStatus==true">
		${lz:set("haveEditable",true)}
		,"tbSaleQuote.tbsqStatus":function(el){
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
	<form class="form editform" ref="TbSaleQuote" id="salequoteForm" name="salequoteForm" action="${empty CTRL.action?"salequote_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写跟单报价相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${tbSaleQuoteToken}" name="tbSaleQuoteToken" id="tbSaleQuoteToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty tbSaleQuote.tbsqId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$tbsqId和after$tbsqId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsqId==true">
	${before$tbsqId}
	<dl class="tbsqId ${CTRL.e.tbsqId?"hidden":""}" major  ref="tbsqId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.tbsqId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.tbSaleQuote$tbsqId!=null">${tbSaleQuote$tbsqId}</s:if><s:else>
			<input type="hidden" value="${tbSaleQuote.tbsqId}" name="tbSaleQuote.tbsqId" id="tbsqId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsqId">${tbSaleQuote.tbsqId}</textarea>
		 		<span>
		 	
			 ${tbSaleQuote.tbsqId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsqId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$tbsqHost和after$tbsqHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsqHost==true">
	${before$tbsqHost}
	<dl class="tbsqHost " major  ref="tbsqHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.tbsqHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbSaleQuote$tbsqHost!=null">${tbSaleQuote$tbsqHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="tbsqHost" name="tbSaleQuote.tbsqHost">
		 				<option selected value="${tbSaleQuote.tbsqHost}">
		 					${get:SrvHost(tbSaleQuote.tbsqHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbsqHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#tbsqHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsqHost">${tbSaleQuote.tbsqHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${tbSaleQuote.tbsqHost}',{ctrl:{editable:false,visible:true}})">
			 ${tbSaleQuote.tbsqHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsqHost}
	</s:if>
	
	${lz:set("注释","*****************报价标题字段的输入框代码*****************")}
	${lz:set("注释","before$tbsqTitle和after$tbsqTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsqTitle==true">
	${before$tbsqTitle}
	<dl class="tbsqTitle " major  ref="tbsqTitle" >
		<dt>报价标题:</dt>
		<s:if test="#request.CTRL.e.tbsqTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.tbSaleQuote$tbsqTitle!=null">${tbSaleQuote$tbsqTitle}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="tbSaleQuote.tbsqTitle" id="tbsqTitle"  value="${tbSaleQuote.tbsqTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入跟单报价的报价标题</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****报价标题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsqTitle">${tbSaleQuote.tbsqTitle}</textarea>
		 		<span>
		 	
			 ${tbSaleQuote.tbsqTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsqTitle}
	</s:if>
	
	${lz:set("注释","*****************所属机会字段的输入框代码*****************")}
	${lz:set("注释","before$tbsqOpport和after$tbsqOpport变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsqOpport==true">
	${before$tbsqOpport}
	<dl class="tbsqOpport " major  ref="tbsqOpport" >
		<dt>所属机会:</dt>
		<s:if test="#request.CTRL.e.tbsqOpport==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.tbSaleQuote$tbsqOpport!=null">${tbSaleQuote$tbsqOpport}</s:if><s:else>
		 			<input title="请输入销售机会机会标题进行查询" class="combox narrow" action="${basePath}${proname}/sale/opport_query.do?value={param}&cssoHost={tbsqHost}" type="text" id="tbsqOpport" name="tbSaleQuote.tbsqOpport" text="${get:CsSaleOpport(tbSaleQuote.tbsqOpport).cssoTitle}" value="${tbSaleQuote.tbsqOpport}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbsqOpport').val())==''){return;};window.href('${basePath}${proname}/sale/opport_details.do?key='+$('#tbsqOpport').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属机会字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsqOpport">${tbSaleQuote.tbsqOpport}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/sale/opport_details.do?key=${tbSaleQuote.tbsqOpport}',{ctrl:{editable:false,visible:true}})">
			 ${tbSaleQuote.tbsqOpport$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsqOpport}
	</s:if>
	
	${lz:set("注释","*****************报价详情字段的输入框代码*****************")}
	${lz:set("注释","before$tbsqDetail和after$tbsqDetail变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsqDetail==true">
	${before$tbsqDetail}
	<dl class="tbsqDetail " major  ref="tbsqDetail" style="width:98%;">
		<dt>报价详情:</dt>
		<s:if test="#request.CTRL.e.tbsqDetail==true">
		${lz:set("haveEditable",true)}
		<dd input="grid">
		<s:if test="#request.tbSaleQuote$tbsqDetail!=null">${tbSaleQuote$tbsqDetail}</s:if><s:else>
		    <textarea class="input wide grid" grid="[
		    	['产品名称',null,'text','name',null]
		    	,['单位',null,'text','unit',null]
		    	,['价格',null,'money','price',null]
		    	,['数量',null,'number','count',null]
		    	,['金额',null,'money','money',null]
		    ]" id="tbsqDetail" name="tbSaleQuote.tbsqDetail">${tbSaleQuote.tbsqDetail}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****报价详情字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="tbsqDetail">${tbSaleQuote.tbsqDetail}</textarea>
		 		<span>
		 	
			 ${tbSaleQuote.tbsqDetail$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsqDetail}
	</s:if>
	
	${lz:set("注释","*****************报价附件字段的输入框代码*****************")}
	${lz:set("注释","before$tbsqFile和after$tbsqFile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsqFile==true">
	${before$tbsqFile}
	<dl class="tbsqFile " minor  ref="tbsqFile" style="width:98%;">
		<dt>报价附件:</dt>
		<s:if test="#request.CTRL.e.tbsqFile==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.tbSaleQuote$tbsqFile!=null">${tbSaleQuote$tbsqFile}</s:if><s:else>
		 	<textarea class="input wide"  id="tbsqFile" name="tbSaleQuote.tbsqFile" rows="5">${tbSaleQuote.tbsqFile}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入跟单报价的报价附件</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****报价附件字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbSaleQuote.tbsqFile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsqFile}
	</s:if>
	
	${lz:set("注释","*****************所有人字段的输入框代码*****************")}
	${lz:set("注释","before$tbsqOnwer和after$tbsqOnwer变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsqOnwer==true">
	${before$tbsqOnwer}
	<dl class="tbsqOnwer " major  ref="tbsqOnwer" >
		<dt>所&nbsp;&nbsp;有&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.tbsqOnwer==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbSaleQuote$tbsqOnwer!=null">${tbSaleQuote$tbsqOnwer}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="tbsqOnwer" name="tbSaleQuote.tbsqOnwer">
		 				<option selected value="${tbSaleQuote.tbsqOnwer}">
		 					${get:SrvUser(tbSaleQuote.tbsqOnwer).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbsqOnwer').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#tbsqOnwer').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所有人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsqOnwer">${tbSaleQuote.tbsqOnwer}</textarea>
		 		<span>
		 	
			 ${tbSaleQuote.tbsqOnwer$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsqOnwer}
	</s:if>
	
	${lz:set("注释","*****************添加人字段的输入框代码*****************")}
	${lz:set("注释","before$tbsqAdder和after$tbsqAdder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsqAdder==true">
	${before$tbsqAdder}
	<dl class="tbsqAdder " major  ref="tbsqAdder" >
		<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.tbsqAdder==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbSaleQuote$tbsqAdder!=null">${tbSaleQuote$tbsqAdder}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="tbsqAdder" name="tbSaleQuote.tbsqAdder">
		 				<option selected value="${tbSaleQuote.tbsqAdder}">
		 					${get:SrvUser(tbSaleQuote.tbsqAdder).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbsqAdder').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#tbsqAdder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsqAdder">${tbSaleQuote.tbsqAdder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${tbSaleQuote.tbsqAdder}',{ctrl:{editable:false,visible:true}})">
			 ${tbSaleQuote.tbsqAdder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsqAdder}
	</s:if>
	
	${lz:set("注释","*****************修改人字段的输入框代码*****************")}
	${lz:set("注释","before$tbsqEditor和after$tbsqEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsqEditor==true">
	${before$tbsqEditor}
	<dl class="tbsqEditor " major  ref="tbsqEditor" >
		<dt>修&nbsp;&nbsp;改&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.tbsqEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbSaleQuote$tbsqEditor!=null">${tbSaleQuote$tbsqEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="tbsqEditor" name="tbSaleQuote.tbsqEditor">
		 				<option selected value="${tbSaleQuote.tbsqEditor}">
		 					${get:SrvUser(tbSaleQuote.tbsqEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbsqEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#tbsqEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsqEditor">${tbSaleQuote.tbsqEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${tbSaleQuote.tbsqEditor}',{ctrl:{editable:false,visible:true}})">
			 ${tbSaleQuote.tbsqEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsqEditor}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$tbsqUpdateTime和after$tbsqUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsqUpdateTime==true">
	${before$tbsqUpdateTime}
	<dl class="tbsqUpdateTime " major  ref="tbsqUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.tbsqUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.tbSaleQuote$tbsqUpdateTime!=null">${tbSaleQuote$tbsqUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="tbSaleQuote.tbsqUpdateTime" id="tbsqUpdateTime"  value="<s:date name="tbSaleQuote.tbsqUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择跟单报价的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsqUpdateTime">${tbSaleQuote.tbsqUpdateTime}</textarea>
		 		<span>
		 	
			 ${tbSaleQuote.tbsqUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsqUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$tbsqAddTime和after$tbsqAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsqAddTime==true">
	${before$tbsqAddTime}
	<dl class="tbsqAddTime " major  ref="tbsqAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.tbsqAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.tbSaleQuote$tbsqAddTime!=null">${tbSaleQuote$tbsqAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="tbSaleQuote.tbsqAddTime" id="tbsqAddTime"  value="<s:date name="tbSaleQuote.tbsqAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择跟单报价的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsqAddTime">${tbSaleQuote.tbsqAddTime}</textarea>
		 		<span>
		 	
			 ${tbSaleQuote.tbsqAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsqAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$tbsqStatus和after$tbsqStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsqStatus==true">
	${before$tbsqStatus}
	<dl class="tbsqStatus " major  ref="tbsqStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.tbsqStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.tbSaleQuote$tbsqStatus!=null">${tbSaleQuote$tbsqStatus}</s:if><s:else>
		 	<select class="narrow" id="tbsqStatus" name="tbSaleQuote.tbsqStatus">
		 		<option value="">请选择</option>
				<option value="1" ${tbSaleQuote.tbsqStatus==1?"selected":""}>正常</option>
				<option value="0" ${tbSaleQuote.tbsqStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择跟单报价的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsqStatus">${tbSaleQuote.tbsqStatus}</textarea>
		 		<span>
		 	
			 ${tbSaleQuote.tbsqStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsqStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${tbSaleQuote.tbsqId}" name="tbSaleQuote.tbsqId" id="tbsqId" />
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