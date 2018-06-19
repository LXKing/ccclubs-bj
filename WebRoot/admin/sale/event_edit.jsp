<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/sale/event.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/sale/event.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csSaleEvent.csseId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csseId,csseHost,csseSubject,csseBattle,csseType,cssePerson,cssePostion,csseAddress,csseDesc,csseStart,csseFinish,csseRemark,csseStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csseId,csseHost,csseSubject,csseBattle,csseType,cssePerson,cssePostion,csseAddress,csseDesc,csseStart,csseFinish,csseRemark,csseStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csseId,csseHost,csseSubject,csseBattle,csseType,cssePerson,cssePostion,csseAddress,csseDesc,csseStart,csseFinish,csseRemark,csseStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csseId,csseHost,csseSubject,csseBattle,csseType,cssePerson,cssePostion,csseAddress,csseDesc,csseStart,csseFinish,csseRemark,csseStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"销售活动编辑":CTRL.title}</title>
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
	$("#eventForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csseId==true">
		${lz:set("haveEditable",true)}
		,"csSaleEvent.csseId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csseHost==true">
		${lz:set("haveEditable",true)}
		,"csSaleEvent.csseHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csseSubject==true">
		${lz:set("haveEditable",true)}
		,"csSaleEvent.csseSubject":function(el){
			if(jQuery.trim(el.value)=="")
				return "活动主题不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "活动主题最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csseBattle==true">
		${lz:set("haveEditable",true)}
		,"csSaleEvent.csseBattle":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csseType==true">
		${lz:set("haveEditable",true)}
		,"csSaleEvent.csseType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择活动类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssePerson==true">
		${lz:set("haveEditable",true)}
		,"csSaleEvent.cssePerson":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssePostion==true">
		${lz:set("haveEditable",true)}
		,"csSaleEvent.cssePostion":function(el){
			if(jQuery.trim(el.value)=="")
				return "位置不能为空";
			if(el.value.length>100 && el.value.indexOf("[*]")==-1)
				return "位置最大长度为100个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csseAddress==true">
		${lz:set("haveEditable",true)}
		,"csSaleEvent.csseAddress":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "详细地点最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csseDesc==true">
		${lz:set("haveEditable",true)}
		,"csSaleEvent.csseDesc":function(el){
			if(jQuery.trim(el.value)=="")
				return "活动说明不能为空";
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "活动说明最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csseStart==true">
		${lz:set("haveEditable",true)}
		,"csSaleEvent.csseStart":function(el){
			if(jQuery.trim(el.value)=="")
				return "开始时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csseFinish==true">
		${lz:set("haveEditable",true)}
		,"csSaleEvent.csseFinish":function(el){
			if(jQuery.trim(el.value)=="")
				return "结束时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csseUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csSaleEvent.csseUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csseAddTime==true">
		${lz:set("haveEditable",true)}
		,"csSaleEvent.csseAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csseRemark==true">
		${lz:set("haveEditable",true)}
		,"csSaleEvent.csseRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csseStatus==true">
		${lz:set("haveEditable",true)}
		,"csSaleEvent.csseStatus":function(el){
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
	<form class="form editform" ref="CsSaleEvent" id="eventForm" name="eventForm" action="${empty CTRL.action?"event_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写销售活动相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csSaleEventToken}" name="csSaleEventToken" id="csSaleEventToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csSaleEvent.csseId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csseId和after$csseId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csseId==true">
	${before$csseId}
	<dl class="csseId ${CTRL.e.csseId?"hidden":""}" major  ref="csseId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csseId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csSaleEvent$csseId!=null">${csSaleEvent$csseId}</s:if><s:else>
			<input type="hidden" value="${csSaleEvent.csseId}" name="csSaleEvent.csseId" id="csseId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csseId">${csSaleEvent.csseId}</textarea>
		 		<span>
		 	
			 ${csSaleEvent.csseId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csseId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csseHost和after$csseHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csseHost==true">
	${before$csseHost}
	<dl class="csseHost " major  ref="csseHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csseHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csSaleEvent$csseHost!=null">${csSaleEvent$csseHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csseHost" name="csSaleEvent.csseHost">
		 				<option selected value="${csSaleEvent.csseHost}">
		 					${get:SrvHost(csSaleEvent.csseHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csseHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csseHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csseHost">${csSaleEvent.csseHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csSaleEvent.csseHost}',{ctrl:{editable:false,visible:true}})">
			 ${csSaleEvent.csseHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csseHost}
	</s:if>
	
	${lz:set("注释","*****************活动主题字段的输入框代码*****************")}
	${lz:set("注释","before$csseSubject和after$csseSubject变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csseSubject==true">
	${before$csseSubject}
	<dl class="csseSubject " major  ref="csseSubject" >
		<dt>活动主题:</dt>
		<s:if test="#request.CTRL.e.csseSubject==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csSaleEvent$csseSubject!=null">${csSaleEvent$csseSubject}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csSaleEvent.csseSubject" id="csseSubject"  value="${csSaleEvent.csseSubject}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入销售活动的活动主题</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****活动主题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csseSubject">${csSaleEvent.csseSubject}</textarea>
		 		<span>
		 	
			 ${csSaleEvent.csseSubject$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csseSubject}
	</s:if>
	
	${lz:set("注释","*****************归属战役字段的输入框代码*****************")}
	${lz:set("注释","before$csseBattle和after$csseBattle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csseBattle==true">
	${before$csseBattle}
	<dl class="csseBattle " major  ref="csseBattle" >
		<dt>归属战役:</dt>
		<s:if test="#request.CTRL.e.csseBattle==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csSaleEvent$csseBattle!=null">${csSaleEvent$csseBattle}</s:if><s:else>
		 			<input title="请输入销售战役战役标题进行查询" class="combox narrow" action="${basePath}${proname}/sale/battle_query.do?value={param}&tbsbHost={csseHost}" type="text" id="csseBattle" name="csSaleEvent.csseBattle" text="${get:TbSaleBattle(csSaleEvent.csseBattle).tbsbTitle}" value="${csSaleEvent.csseBattle}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csseBattle').val())==''){return;};window.href('${basePath}${proname}/sale/battle_details.do?key='+$('#csseBattle').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****归属战役字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csseBattle">${csSaleEvent.csseBattle}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/sale/battle_details.do?key=${csSaleEvent.csseBattle}',{ctrl:{editable:false,visible:true}})">
			 ${csSaleEvent.csseBattle$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csseBattle}
	</s:if>
	
	${lz:set("注释","*****************活动类型字段的输入框代码*****************")}
	${lz:set("注释","before$csseType和after$csseType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csseType==true">
	${before$csseType}
	<dl class="csseType " major  ref="csseType" >
		<dt>活动类型:</dt>
		<s:if test="#request.CTRL.e.csseType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csSaleEvent$csseType!=null">${csSaleEvent$csseType}</s:if><s:else>
		 	<select class="narrow" id="csseType" name="csSaleEvent.csseType">
		 		<option value="">请选择</option>
 					${lz:set("items", get:propertys("销售活动类型","sale_event_type"))}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.spId}" ${csSaleEvent.csseType==item.spId?"selected":""}>${item.spName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csseType').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?key='+$('#csseType').val(),{ctrl:{editable:false}})"></a>
	 			<a class="reladd" config="{
	 				action:'${basePath}${proname}/configurator/property_edit.do?rd=1&parent=${get:property("sale_event_type").spId}'
	 				,callback:function(object){
	 					$('<option selected value='+object.spId+'>'+object.spName+'</option>').appendTo('#csseType');
	 				}
	 			}" href="javascript:void(0);"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>比如：促销活动 展架直销 会议培训 展会 研讨会 网络推广</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****活动类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csseType">${csSaleEvent.csseType}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${csSaleEvent.csseType}',{ctrl:{editable:false,visible:true}})">
			 ${csSaleEvent.csseType$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csseType}
	</s:if>
	
	${lz:set("注释","*****************负责人字段的输入框代码*****************")}
	${lz:set("注释","before$cssePerson和after$cssePerson变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssePerson==true">
	${before$cssePerson}
	<dl class="cssePerson " major  ref="cssePerson" >
		<dt>负&nbsp;&nbsp;责&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.cssePerson==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csSaleEvent$cssePerson!=null">${csSaleEvent$cssePerson}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cssePerson" name="csSaleEvent.cssePerson">
		 				<option selected value="${csSaleEvent.cssePerson}">
		 					${get:SrvUser(csSaleEvent.cssePerson).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssePerson').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cssePerson').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****负责人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssePerson">${csSaleEvent.cssePerson}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csSaleEvent.cssePerson}',{ctrl:{editable:false,visible:true}})">
			 ${csSaleEvent.cssePerson$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssePerson}
	</s:if>
	
	${lz:set("注释","*****************位置字段的输入框代码*****************")}
	${lz:set("注释","before$cssePostion和after$cssePostion变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssePostion==true">
	${before$cssePostion}
	<dl class="cssePostion " major  ref="cssePostion" style="width:98%;">
		<dt>位　　置:</dt>
		<s:if test="#request.CTRL.e.cssePostion==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csSaleEvent$cssePostion!=null">${csSaleEvent$cssePostion}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="100" name="csSaleEvent.cssePostion" id="cssePostion"  value="${csSaleEvent.cssePostion}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入销售活动的位置</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****位置字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cssePostion">${csSaleEvent.cssePostion}</textarea>
		 		<span>
		 	
			 ${csSaleEvent.cssePostion$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssePostion}
	</s:if>
	
	${lz:set("注释","*****************详细地点字段的输入框代码*****************")}
	${lz:set("注释","before$csseAddress和after$csseAddress变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csseAddress==true">
	${before$csseAddress}
	<dl class="csseAddress " major  ref="csseAddress" style="width:98%;">
		<dt>详细地点:</dt>
		<s:if test="#request.CTRL.e.csseAddress==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csSaleEvent$csseAddress!=null">${csSaleEvent$csseAddress}</s:if><s:else>
		 	<textarea class="input wide"  id="csseAddress" name="csSaleEvent.csseAddress" rows="5">${csSaleEvent.csseAddress}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入销售活动的详细地点</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****详细地点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csSaleEvent.csseAddress$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csseAddress}
	</s:if>
	
	${lz:set("注释","*****************活动说明字段的输入框代码*****************")}
	${lz:set("注释","before$csseDesc和after$csseDesc变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csseDesc==true">
	${before$csseDesc}
	<dl class="csseDesc " major  ref="csseDesc" style="width:98%;">
		<dt>活动说明:</dt>
		<s:if test="#request.CTRL.e.csseDesc==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csSaleEvent$csseDesc!=null">${csSaleEvent$csseDesc}</s:if><s:else>
		 	<textarea class="input wide"  id="csseDesc" name="csSaleEvent.csseDesc" rows="5">${csSaleEvent.csseDesc}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入销售活动的活动说明</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****活动说明字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csSaleEvent.csseDesc$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csseDesc}
	</s:if>
	
	${lz:set("注释","*****************开始时间字段的输入框代码*****************")}
	${lz:set("注释","before$csseStart和after$csseStart变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csseStart==true">
	${before$csseStart}
	<dl class="csseStart " major  ref="csseStart" >
		<dt>开始时间:</dt>
		<s:if test="#request.CTRL.e.csseStart==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csSaleEvent$csseStart!=null">${csSaleEvent$csseStart}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csSaleEvent.csseStart" id="csseStart"  value="<s:date name="csSaleEvent.csseStart" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择销售活动的开始时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****开始时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csseStart">${csSaleEvent.csseStart}</textarea>
		 		<span>
		 	
			 ${csSaleEvent.csseStart$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csseStart}
	</s:if>
	
	${lz:set("注释","*****************结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$csseFinish和after$csseFinish变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csseFinish==true">
	${before$csseFinish}
	<dl class="csseFinish " major  ref="csseFinish" >
		<dt>结束时间:</dt>
		<s:if test="#request.CTRL.e.csseFinish==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csSaleEvent$csseFinish!=null">${csSaleEvent$csseFinish}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csSaleEvent.csseFinish" id="csseFinish"  value="<s:date name="csSaleEvent.csseFinish" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择销售活动的结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csseFinish">${csSaleEvent.csseFinish}</textarea>
		 		<span>
		 	
			 ${csSaleEvent.csseFinish$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csseFinish}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csseUpdateTime和after$csseUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csseUpdateTime==true">
	${before$csseUpdateTime}
	<dl class="csseUpdateTime " major  ref="csseUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csseUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csSaleEvent$csseUpdateTime!=null">${csSaleEvent$csseUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csSaleEvent.csseUpdateTime" id="csseUpdateTime"  value="<s:date name="csSaleEvent.csseUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择销售活动的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csseUpdateTime">${csSaleEvent.csseUpdateTime}</textarea>
		 		<span>
		 	
			 ${csSaleEvent.csseUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csseUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csseAddTime和after$csseAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csseAddTime==true">
	${before$csseAddTime}
	<dl class="csseAddTime " major  ref="csseAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csseAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csSaleEvent$csseAddTime!=null">${csSaleEvent$csseAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csSaleEvent.csseAddTime" id="csseAddTime"  value="<s:date name="csSaleEvent.csseAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择销售活动的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csseAddTime">${csSaleEvent.csseAddTime}</textarea>
		 		<span>
		 	
			 ${csSaleEvent.csseAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csseAddTime}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$csseRemark和after$csseRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csseRemark==true">
	${before$csseRemark}
	<dl class="csseRemark " major  ref="csseRemark" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.csseRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csSaleEvent$csseRemark!=null">${csSaleEvent$csseRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csseRemark" name="csSaleEvent.csseRemark" rows="5">${csSaleEvent.csseRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入销售活动的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csSaleEvent.csseRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csseRemark}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csseStatus和after$csseStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csseStatus==true">
	${before$csseStatus}
	<dl class="csseStatus " major  ref="csseStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csseStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csSaleEvent$csseStatus!=null">${csSaleEvent$csseStatus}</s:if><s:else>
		 	<select class="narrow" id="csseStatus" name="csSaleEvent.csseStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csSaleEvent.csseStatus==0?"selected":""}>筹备中</option>
				<option value="1" ${csSaleEvent.csseStatus==1?"selected":""}>进行中</option>
				<option value="2" ${csSaleEvent.csseStatus==2?"selected":""}>已完成</option>
				<option value="3" ${csSaleEvent.csseStatus==3?"selected":""}>已取消</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择销售活动的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csseStatus">${csSaleEvent.csseStatus}</textarea>
		 		<span>
		 	
			 ${csSaleEvent.csseStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csseStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csSaleEvent.csseId}" name="csSaleEvent.csseId" id="csseId" />
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