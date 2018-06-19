<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/sale/survey.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/sale/survey.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csSaleSurvey.csssId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csssId,csssHost,csssSubject,csssEvent,csssType,csssDetail,csssStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csssId,csssHost,csssSubject,csssEvent,csssType,csssDetail,csssStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csssId,csssHost,csssSubject,csssEvent,csssType,csssDetail,csssStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csssId,csssHost,csssSubject,csssEvent,csssType,csssDetail,csssUser,csssStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"市场调查编辑":CTRL.title}</title>
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
		if($("#csssDetail").size()>0){
			 //构造编辑页csSaleSurvey.csssDetail为html编辑控件
			 KE.init({
				id : 'csssDetail',
				width : '625px',
				height : '300px' , 
				imageUploadJson : '${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}?app=survey_edit&type=img${ lz:config("upload.url")==null ? "" : "&remote="}${ lz:config("upload.url")==null ? "" : basePath}${ lz:config("upload.url")==null ? "" : "callback.jsp"}'
			 });
			 KE.create("csssDetail");
		 }
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#surveyForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csssId==true">
		${lz:set("haveEditable",true)}
		,"csSaleSurvey.csssId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csssHost==true">
		${lz:set("haveEditable",true)}
		,"csSaleSurvey.csssHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csssSubject==true">
		${lz:set("haveEditable",true)}
		,"csSaleSurvey.csssSubject":function(el){
			if(jQuery.trim(el.value)=="")
				return "调查主题不能为空";
			if(el.value.length>64 && el.value.indexOf("[*]")==-1)
				return "调查主题最大长度为64个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csssEvent==true">
		${lz:set("haveEditable",true)}
		,"csSaleSurvey.csssEvent":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csssType==true">
		${lz:set("haveEditable",true)}
		,"csSaleSurvey.csssType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择调查类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csssDetail==true">
		${lz:set("haveEditable",true)}
		,"csSaleSurvey.csssDetail":function(el){
			if($("#csssDetail").get(0))
			 	KE.sync("csssDetail");		 
			if(jQuery.trim(el.value)=="")
				return "调查结论不能为空";
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "调查结论最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csssUser==true">
		${lz:set("haveEditable",true)}
		,"csSaleSurvey.csssUser":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csssUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csSaleSurvey.csssUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csssAddTime==true">
		${lz:set("haveEditable",true)}
		,"csSaleSurvey.csssAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csssStatus==true">
		${lz:set("haveEditable",true)}
		,"csSaleSurvey.csssStatus":function(el){
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
	<form class="form editform" ref="CsSaleSurvey" id="surveyForm" name="surveyForm" action="${empty CTRL.action?"survey_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写市场调查相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csSaleSurveyToken}" name="csSaleSurveyToken" id="csSaleSurveyToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csSaleSurvey.csssId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csssId和after$csssId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csssId==true">
	${before$csssId}
	<dl class="csssId ${CTRL.e.csssId?"hidden":""}" major  ref="csssId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csssId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csSaleSurvey$csssId!=null">${csSaleSurvey$csssId}</s:if><s:else>
			<input type="hidden" value="${csSaleSurvey.csssId}" name="csSaleSurvey.csssId" id="csssId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csssId">${csSaleSurvey.csssId}</textarea>
		 		<span>
		 	
			 ${csSaleSurvey.csssId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csssId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csssHost和after$csssHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csssHost==true">
	${before$csssHost}
	<dl class="csssHost " major  ref="csssHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csssHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csSaleSurvey$csssHost!=null">${csSaleSurvey$csssHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csssHost" name="csSaleSurvey.csssHost">
		 				<option selected value="${csSaleSurvey.csssHost}">
		 					${get:SrvHost(csSaleSurvey.csssHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csssHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csssHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csssHost">${csSaleSurvey.csssHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csSaleSurvey.csssHost}',{ctrl:{editable:false,visible:true}})">
			 ${csSaleSurvey.csssHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csssHost}
	</s:if>
	
	${lz:set("注释","*****************调查主题字段的输入框代码*****************")}
	${lz:set("注释","before$csssSubject和after$csssSubject变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csssSubject==true">
	${before$csssSubject}
	<dl class="csssSubject " major  ref="csssSubject" style="width:98%;">
		<dt>调查主题:</dt>
		<s:if test="#request.CTRL.e.csssSubject==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csSaleSurvey$csssSubject!=null">${csSaleSurvey$csssSubject}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="64" name="csSaleSurvey.csssSubject" id="csssSubject"  value="${csSaleSurvey.csssSubject}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入市场调查的调查主题</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****调查主题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csssSubject">${csSaleSurvey.csssSubject}</textarea>
		 		<span>
		 	
			 ${csSaleSurvey.csssSubject$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csssSubject}
	</s:if>
	
	${lz:set("注释","*****************所属活动字段的输入框代码*****************")}
	${lz:set("注释","before$csssEvent和after$csssEvent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csssEvent==true">
	${before$csssEvent}
	<dl class="csssEvent " major  ref="csssEvent" >
		<dt>所属活动:</dt>
		<s:if test="#request.CTRL.e.csssEvent==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csSaleSurvey$csssEvent!=null">${csSaleSurvey$csssEvent}</s:if><s:else>
		 			<input title="请输入销售活动活动主题进行查询" class="combox narrow" action="${basePath}${proname}/sale/event_query.do?value={param}&csseHost={csssHost}" type="text" id="csssEvent" name="csSaleSurvey.csssEvent" text="${get:CsSaleEvent(csSaleSurvey.csssEvent).csseSubject}" value="${csSaleSurvey.csssEvent}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csssEvent').val())==''){return;};window.href('${basePath}${proname}/sale/event_details.do?key='+$('#csssEvent').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属活动字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csssEvent">${csSaleSurvey.csssEvent}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/sale/event_details.do?key=${csSaleSurvey.csssEvent}',{ctrl:{editable:false,visible:true}})">
			 ${csSaleSurvey.csssEvent$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csssEvent}
	</s:if>
	
	${lz:set("注释","*****************调查类型字段的输入框代码*****************")}
	${lz:set("注释","before$csssType和after$csssType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csssType==true">
	${before$csssType}
	<dl class="csssType " major  ref="csssType" >
		<dt>调查类型:</dt>
		<s:if test="#request.CTRL.e.csssType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csSaleSurvey$csssType!=null">${csSaleSurvey$csssType}</s:if><s:else>
		 	<select class="narrow" id="csssType" name="csSaleSurvey.csssType">
		 		<option value="">请选择</option>
 					${lz:set("items", get:propertys("市场调查类型","survey_type"))}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.spId}" ${csSaleSurvey.csssType==item.spId?"selected":""}>${item.spName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csssType').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?key='+$('#csssType').val(),{ctrl:{editable:false}})"></a>
	 			<a class="reladd" config="{
	 				action:'${basePath}${proname}/configurator/property_edit.do?rd=1&parent=${get:property("survey_type").spId}'
	 				,callback:function(object){
	 					$('<option selected value='+object.spId+'>'+object.spName+'</option>').appendTo('#csssType');
	 				}
	 			}" href="javascript:void(0);"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>比如：综合性调查 区域品牌 竞争 价格 认知满意度 负面调查 行业调查 宏观政策 容量</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****调查类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csssType">${csSaleSurvey.csssType}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${csSaleSurvey.csssType}',{ctrl:{editable:false,visible:true}})">
			 ${csSaleSurvey.csssType$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csssType}
	</s:if>
	
	${lz:set("注释","*****************调查结论字段的输入框代码*****************")}
	${lz:set("注释","before$csssDetail和after$csssDetail变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csssDetail==true">
	${before$csssDetail}
	<dl class="csssDetail " major  ref="csssDetail" style="width:98%;">
		<dt>调查结论:</dt>
		<s:if test="#request.CTRL.e.csssDetail==true">
		${lz:set("haveEditable",true)}
		<dd input="html">
		<s:if test="#request.csSaleSurvey$csssDetail!=null">${csSaleSurvey$csssDetail}</s:if><s:else>
		    <textarea class="input wide"  id="csssDetail" name="csSaleSurvey.csssDetail" style="height:300px;visibility:hidden;">${csSaleSurvey.csssDetail}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入市场调查的调查结论</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****调查结论字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csSaleSurvey.csssDetail$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csssDetail}
	</s:if>
	
	${lz:set("注释","*****************添加人字段的输入框代码*****************")}
	${lz:set("注释","before$csssUser和after$csssUser变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csssUser==true">
	${before$csssUser}
	<dl class="csssUser " major  ref="csssUser" >
		<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csssUser==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csSaleSurvey$csssUser!=null">${csSaleSurvey$csssUser}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csssUser" name="csSaleSurvey.csssUser">
		 				<option selected value="${csSaleSurvey.csssUser}">
		 					${get:SrvUser(csSaleSurvey.csssUser).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csssUser').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csssUser').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csssUser">${csSaleSurvey.csssUser}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csSaleSurvey.csssUser}',{ctrl:{editable:false,visible:true}})">
			 ${csSaleSurvey.csssUser$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csssUser}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csssUpdateTime和after$csssUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csssUpdateTime==true">
	${before$csssUpdateTime}
	<dl class="csssUpdateTime " major  ref="csssUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csssUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csSaleSurvey$csssUpdateTime!=null">${csSaleSurvey$csssUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csSaleSurvey.csssUpdateTime" id="csssUpdateTime"  value="<s:date name="csSaleSurvey.csssUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择市场调查的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csssUpdateTime">${csSaleSurvey.csssUpdateTime}</textarea>
		 		<span>
		 	
			 ${csSaleSurvey.csssUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csssUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csssAddTime和after$csssAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csssAddTime==true">
	${before$csssAddTime}
	<dl class="csssAddTime " major  ref="csssAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csssAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csSaleSurvey$csssAddTime!=null">${csSaleSurvey$csssAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csSaleSurvey.csssAddTime" id="csssAddTime"  value="<s:date name="csSaleSurvey.csssAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择市场调查的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csssAddTime">${csSaleSurvey.csssAddTime}</textarea>
		 		<span>
		 	
			 ${csSaleSurvey.csssAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csssAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csssStatus和after$csssStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csssStatus==true">
	${before$csssStatus}
	<dl class="csssStatus " major  ref="csssStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csssStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csSaleSurvey$csssStatus!=null">${csSaleSurvey$csssStatus}</s:if><s:else>
		 	<select class="narrow" id="csssStatus" name="csSaleSurvey.csssStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csSaleSurvey.csssStatus==1?"selected":""}>正常</option>
				<option value="0" ${csSaleSurvey.csssStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择市场调查的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csssStatus">${csSaleSurvey.csssStatus}</textarea>
		 		<span>
		 	
			 ${csSaleSurvey.csssStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csssStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csSaleSurvey.csssId}" name="csSaleSurvey.csssId" id="csssId" />
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