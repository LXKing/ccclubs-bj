<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/finance/record.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/finance/record.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csRecord.csrId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csrRecordSubject,csrOrder,csrRelate,csrObject",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csrId,csrHost,csrRemark,csrMember,csrMoneyType,csrRecordSubject,csrRecordType,csrAmount,csrRemain,csrAddTime,csrOrder,csrRelate,csrObject",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"消费记录编辑":CTRL.title}</title>
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
	$("#recordForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csrId==true">
		${lz:set("haveEditable",true)}
		,"csRecord.csrId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrHost==true">
		${lz:set("haveEditable",true)}
		,"csRecord.csrHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrRemark==true">
		${lz:set("haveEditable",true)}
		,"csRecord.csrRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "摘要最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrMember==true">
		${lz:set("haveEditable",true)}
		,"csRecord.csrMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrMoneyType==true">
		${lz:set("haveEditable",true)}
		,"csRecord.csrMoneyType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择金额类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrRecordSubject==true">
		${lz:set("haveEditable",true)}
		,"csRecord.csrRecordSubject":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrRecordType==true">
		${lz:set("haveEditable",true)}
		,"csRecord.csrRecordType":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrAmount==true">
		${lz:set("haveEditable",true)}
		,"csRecord.csrAmount":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "数额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrRemain==true">
		${lz:set("haveEditable",true)}
		,"csRecord.csrRemain":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "余额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrAddTime==true">
		${lz:set("haveEditable",true)}
		,"csRecord.csrAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrOrder==true">
		${lz:set("haveEditable",true)}
		,"csRecord.csrOrder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrRelate==true">
		${lz:set("haveEditable",true)}
		,"csRecord.csrRelate":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrObject==true">
		${lz:set("haveEditable",true)}
		,"csRecord.csrObject":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrEditor==true">
		${lz:set("haveEditable",true)}
		,"csRecord.csrEditor":function(el){
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
	<form class="form editform" ref="CsRecord" id="recordForm" name="recordForm" action="${empty CTRL.action?"record_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写消费记录相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csRecordToken}" name="csRecordToken" id="csRecordToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csRecord.csrId))}		
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
		<s:if test="#request.csRecord$csrId!=null">${csRecord$csrId}</s:if><s:else>
			<input type="hidden" value="${csRecord.csrId}" name="csRecord.csrId" id="csrId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrId">${csRecord.csrId}</textarea>
		 		<span>
		 	
			 ${csRecord.csrId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csrHost和after$csrHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrHost==true">
	${before$csrHost}
	<dl class="csrHost " major  ref="csrHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csrHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csRecord$csrHost!=null">${csRecord$csrHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csrHost" name="csRecord.csrHost">
		 				<option selected value="${csRecord.csrHost}">
		 					${get:SrvHost(csRecord.csrHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csrHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrHost">${csRecord.csrHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csRecord.csrHost}',{ctrl:{editable:false,visible:true}})">
			 ${csRecord.csrHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrHost}
	</s:if>
	
	${lz:set("注释","*****************摘要字段的输入框代码*****************")}
	${lz:set("注释","before$csrRemark和after$csrRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrRemark==true">
	${before$csrRemark}
	<dl class="csrRemark " major  ref="csrRemark" style="width:98%;">
		<dt>摘　　要:</dt>
		<s:if test="#request.CTRL.e.csrRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csRecord$csrRemark!=null">${csRecord$csrRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csrRemark" name="csRecord.csrRemark" rows="5">${csRecord.csrRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入消费记录的摘要</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****摘要字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csRecord.csrRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrRemark}
	</s:if>
	
	${lz:set("注释","*****************消费会员字段的输入框代码*****************")}
	${lz:set("注释","before$csrMember和after$csrMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrMember==true">
	${before$csrMember}
	<dl class="csrMember " major  ref="csrMember" >
		<dt>消费会员:</dt>
		<s:if test="#request.CTRL.e.csrMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csRecord$csrMember!=null">${csRecord$csrMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csrHost}" type="text" id="csrMember" name="csRecord.csrMember" text="${get:CsMember(csRecord.csrMember).csmName}" value="${csRecord.csrMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csrMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****消费会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrMember">${csRecord.csrMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csRecord.csrMember}',{ctrl:{editable:false,visible:true}})">
			 ${csRecord.csrMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrMember}
	</s:if>
	
	${lz:set("注释","*****************金额类型字段的输入框代码*****************")}
	${lz:set("注释","before$csrMoneyType和after$csrMoneyType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrMoneyType==true">
	${before$csrMoneyType}
	<dl class="csrMoneyType " major  ref="csrMoneyType" >
		<dt>金额类型:</dt>
		<s:if test="#request.CTRL.e.csrMoneyType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRecord$csrMoneyType!=null">${csRecord$csrMoneyType}</s:if><s:else>
		 	<select class="narrow" id="csrMoneyType" name="csRecord.csrMoneyType">
		 		<option value="">请选择</option>
				<option value="0" ${csRecord.csrMoneyType==0?"selected":""}>保证金</option>
				<option value="1" ${csRecord.csrMoneyType==1?"selected":""}>现金券</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择消费记录的金额类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****金额类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrMoneyType">${csRecord.csrMoneyType}</textarea>
		 		<span>
		 	
			 ${csRecord.csrMoneyType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrMoneyType}
	</s:if>
	
	${lz:set("注释","*****************科目类型字段的输入框代码*****************")}
	${lz:set("注释","before$csrRecordSubject和after$csrRecordSubject变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrRecordSubject==true">
	${before$csrRecordSubject}
	<dl class="csrRecordSubject " major  ref="csrRecordSubject" >
		<dt>科目类型:</dt>
		<s:if test="#request.CTRL.e.csrRecordSubject==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csRecord$csrRecordSubject!=null">${csRecord$csrRecordSubject}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/configurator/recordsubject_query.do?size=-1" id="csrRecordSubject" name="csRecord.csrRecordSubject">
		 				<option selected value="${csRecord.csrRecordSubject}">
		 					${get:CsRecordSubject(csRecord.csrRecordSubject).csrsName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrRecordSubject').val())==''){return;};window.href('${basePath}${proname}/configurator/recordsubject_details.do?key='+$('#csrRecordSubject').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****科目类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrRecordSubject">${csRecord.csrRecordSubject}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/recordsubject_details.do?key=${csRecord.csrRecordSubject}',{ctrl:{editable:false,visible:true}})">
			 ${csRecord.csrRecordSubject$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrRecordSubject}
	</s:if>
	
	${lz:set("注释","*****************消费类型字段的输入框代码*****************")}
	${lz:set("注释","before$csrRecordType和after$csrRecordType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrRecordType==true">
	${before$csrRecordType}
	<dl class="csrRecordType " major  ref="csrRecordType" >
		<dt>消费类型:</dt>
		<s:if test="#request.CTRL.e.csrRecordType==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csRecord$csrRecordType!=null">${csRecord$csrRecordType}</s:if><s:else>
		 			<input title="请输入消费类型类型名称进行查询" class="combox narrow" action="${basePath}${proname}/configurator/recordtype_query.do?value={param}" type="text" id="csrRecordType" name="csRecord.csrRecordType" text="${get:CsRecordType(csRecord.csrRecordType).csrtTypeName}" value="${csRecord.csrRecordType}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrRecordType').val())==''){return;};window.href('${basePath}${proname}/configurator/recordtype_details.do?key='+$('#csrRecordType').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****消费类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrRecordType">${csRecord.csrRecordType}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/recordtype_details.do?key=${csRecord.csrRecordType}',{ctrl:{editable:false,visible:true}})">
			 ${csRecord.csrRecordType$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrRecordType}
	</s:if>
	
	${lz:set("注释","*****************数额字段的输入框代码*****************")}
	${lz:set("注释","before$csrAmount和after$csrAmount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrAmount==true">
	${before$csrAmount}
	<dl class="csrAmount " major  ref="csrAmount" >
		<dt>数　　额:</dt>
		<s:if test="#request.CTRL.e.csrAmount==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csRecord$csrAmount!=null">${csRecord$csrAmount}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csRecord.csrAmount" id="csrAmount"  value="${csRecord.csrAmount}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入消费记录的数额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****数额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrAmount">${csRecord.csrAmount}</textarea>
		 		<span>
		 	
			 ${csRecord.csrAmount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrAmount}
	</s:if>
	
	${lz:set("注释","*****************余额字段的输入框代码*****************")}
	${lz:set("注释","before$csrRemain和after$csrRemain变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrRemain==true">
	${before$csrRemain}
	<dl class="csrRemain " major  ref="csrRemain" >
		<dt>余　　额:</dt>
		<s:if test="#request.CTRL.e.csrRemain==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csRecord$csrRemain!=null">${csRecord$csrRemain}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csRecord.csrRemain" id="csrRemain"  value="${csRecord.csrRemain}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>帐户余额或现金券余额或积分余量</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****余额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrRemain">${csRecord.csrRemain}</textarea>
		 		<span>
		 	
			 ${csRecord.csrRemain$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrRemain}
	</s:if>
	
	${lz:set("注释","*****************时间字段的输入框代码*****************")}
	${lz:set("注释","before$csrAddTime和after$csrAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrAddTime==true">
	${before$csrAddTime}
	<dl class="csrAddTime " major  ref="csrAddTime" >
		<dt>时　　间:</dt>
		<s:if test="#request.CTRL.e.csrAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csRecord$csrAddTime!=null">${csRecord$csrAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csRecord.csrAddTime" id="csrAddTime"  value="<s:date name="csRecord.csrAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择消费记录的时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrAddTime">${csRecord.csrAddTime}</textarea>
		 		<span>
		 	
			 ${csRecord.csrAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrAddTime}
	</s:if>
	
	${lz:set("注释","*****************关联订单字段的输入框代码*****************")}
	${lz:set("注释","before$csrOrder和after$csrOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrOrder==true">
	${before$csrOrder}
	<dl class="csrOrder " minor  ref="csrOrder" >
		<dt>关联订单:</dt>
		<s:if test="#request.CTRL.e.csrOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csRecord$csrOrder!=null">${csRecord$csrOrder}</s:if><s:else>
		 			<input title="请输入系统订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={csrHost}" type="text" id="csrOrder" name="csRecord.csrOrder" text="${get:CsOrder(csRecord.csrOrder).csoId}" value="${csRecord.csrOrder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrOrder').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?key='+$('#csrOrder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrOrder">${csRecord.csrOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?key=${csRecord.csrOrder}',{ctrl:{editable:false,visible:true}})">
			 ${csRecord.csrOrder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrOrder}
	</s:if>
	
	${lz:set("注释","*****************关联编号字段的输入框代码*****************")}
	${lz:set("注释","before$csrRelate和after$csrRelate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrRelate==true">
	${before$csrRelate}
	<dl class="csrRelate " minor  ref="csrRelate" >
		<dt>关联编号:</dt>
		<s:if test="#request.CTRL.e.csrRelate==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csRecord$csrRelate!=null">${csRecord$csrRelate}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csRecord.csrRelate" id="csrRelate"  value="${csRecord.csrRelate}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>支付宝充值？等</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrRelate">${csRecord.csrRelate}</textarea>
		 		<span>
		 	
			 ${csRecord.csrRelate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrRelate}
	</s:if>
	
	${lz:set("注释","*****************其它关联字段的输入框代码*****************")}
	${lz:set("注释","before$csrObject和after$csrObject变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrObject==true">
	${before$csrObject}
	<dl class="csrObject " minor  ref="csrObject" >
		<dt>其它关联:</dt>
		<s:if test="#request.CTRL.e.csrObject==true">
		${lz:set("haveEditable",true)}
		<dd input="generic">
		<s:if test="#request.csRecord$csrObject!=null">${csRecord$csrObject}</s:if><s:else>
		 	<input title="请输入动态查询" class="combox narrow" generic="csrObjectGeneric" size="16" type="text" id="csrObject" name="csRecord.csrObject" text="${csRecord.csrObject$}" value="${csRecord.csrObject}"/>
		 	${lz:set("models",get:models(""))}
		 	<select id="csrObjectGeneric" style="display:none;">
		 		<option value="">--选择模块--</option>
		 		<s:iterator value="#request.models" id="item" status="i">
		 		<option value="${item.name}:${basePath}admin/${item.namespace}_query.do?value={param}">${item.caption}</option>
		 		</s:iterator>
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="lookup('#${NAME}')"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****其它关联字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrObject">${csRecord.csrObject}</textarea>
		 		<span>
		 	
			 ${csRecord.csrObject$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrObject}
	</s:if>
	
	${lz:set("注释","*****************操作人字段的输入框代码*****************")}
	${lz:set("注释","before$csrEditor和after$csrEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrEditor==true">
	${before$csrEditor}
	<dl class="csrEditor " major  ref="csrEditor" >
		<dt>操&nbsp;&nbsp;作&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csrEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csRecord$csrEditor!=null">${csRecord$csrEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csrEditor" name="csRecord.csrEditor">
		 				<option selected value="${csRecord.csrEditor}">
		 					${get:SrvUser(csRecord.csrEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csrEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csrEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****操作人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrEditor">${csRecord.csrEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csRecord.csrEditor}',{ctrl:{editable:false,visible:true}})">
			 ${csRecord.csrEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrEditor}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csRecord.csrId}" name="csRecord.csrId" id="csrId" />
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