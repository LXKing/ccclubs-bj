<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/sale/opport.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/sale/opport.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csSaleOpport.cssoId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cssoId,cssoHost,cssoTitle,cssoBattle,cssoEvent,cssoFrom,cssoCustom,cssoContact,cssoNumber,cssoRate,cssoTag,cssoProfile,cssoStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cssoId,cssoHost,cssoTitle,cssoBattle,cssoEvent,cssoFrom,cssoCustom,cssoContact,cssoNumber,cssoRate,cssoTag,cssoProfile,cssoStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cssoId,cssoHost,cssoTitle,cssoBattle,cssoEvent,cssoFrom,cssoCustom,cssoContact,cssoNumber,cssoRate,cssoTag,cssoProfile,cssoStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cssoId,cssoHost,cssoTitle,cssoBattle,cssoEvent,cssoFrom,cssoCustom,cssoContact,cssoNumber,cssoRate,cssoUser,cssoTag,cssoProfile,cssoStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"销售机会编辑":CTRL.title}</title>
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
	$("#opportForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cssoId==true">
		${lz:set("haveEditable",true)}
		,"csSaleOpport.cssoId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoHost==true">
		${lz:set("haveEditable",true)}
		,"csSaleOpport.cssoHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoTitle==true">
		${lz:set("haveEditable",true)}
		,"csSaleOpport.cssoTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "机会标题不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "机会标题最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoBattle==true">
		${lz:set("haveEditable",true)}
		,"csSaleOpport.cssoBattle":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoEvent==true">
		${lz:set("haveEditable",true)}
		,"csSaleOpport.cssoEvent":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoFrom==true">
		${lz:set("haveEditable",true)}
		,"csSaleOpport.cssoFrom":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择机会来源";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoCustom==true">
		${lz:set("haveEditable",true)}
		,"csSaleOpport.cssoCustom":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoContact==true">
		${lz:set("haveEditable",true)}
		,"csSaleOpport.cssoContact":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "客户姓名最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoNumber==true">
		${lz:set("haveEditable",true)}
		,"csSaleOpport.cssoNumber":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "联系电话最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoRate==true">
		${lz:set("haveEditable",true)}
		,"csSaleOpport.cssoRate":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择成功机率";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoOnwer==true">
		${lz:set("haveEditable",true)}
		,"csSaleOpport.cssoOnwer":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoUser==true">
		${lz:set("haveEditable",true)}
		,"csSaleOpport.cssoUser":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoTag==true">
		${lz:set("haveEditable",true)}
		,"csSaleOpport.cssoTag":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoProfile==true">
		${lz:set("haveEditable",true)}
		,"csSaleOpport.cssoProfile":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "机会描述最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csSaleOpport.cssoUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoAddTime==true">
		${lz:set("haveEditable",true)}
		,"csSaleOpport.cssoAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssoStatus==true">
		${lz:set("haveEditable",true)}
		,"csSaleOpport.cssoStatus":function(el){
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
	<form class="form editform" ref="CsSaleOpport" id="opportForm" name="opportForm" action="${empty CTRL.action?"opport_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写销售机会相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csSaleOpportToken}" name="csSaleOpportToken" id="csSaleOpportToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csSaleOpport.cssoId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cssoId和after$cssoId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoId==true">
	${before$cssoId}
	<dl class="cssoId ${CTRL.e.cssoId?"hidden":""}" major  ref="cssoId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cssoId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csSaleOpport$cssoId!=null">${csSaleOpport$cssoId}</s:if><s:else>
			<input type="hidden" value="${csSaleOpport.cssoId}" name="csSaleOpport.cssoId" id="cssoId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoId">${csSaleOpport.cssoId}</textarea>
		 		<span>
		 	
			 ${csSaleOpport.cssoId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cssoHost和after$cssoHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoHost==true">
	${before$cssoHost}
	<dl class="cssoHost " major  ref="cssoHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cssoHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csSaleOpport$cssoHost!=null">${csSaleOpport$cssoHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cssoHost" name="csSaleOpport.cssoHost">
		 				<option selected value="${csSaleOpport.cssoHost}">
		 					${get:SrvHost(csSaleOpport.cssoHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssoHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cssoHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoHost">${csSaleOpport.cssoHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csSaleOpport.cssoHost}',{ctrl:{editable:false,visible:true}})">
			 ${csSaleOpport.cssoHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoHost}
	</s:if>
	
	${lz:set("注释","*****************机会标题字段的输入框代码*****************")}
	${lz:set("注释","before$cssoTitle和after$cssoTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoTitle==true">
	${before$cssoTitle}
	<dl class="cssoTitle " major  ref="cssoTitle" >
		<dt>机会标题:</dt>
		<s:if test="#request.CTRL.e.cssoTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csSaleOpport$cssoTitle!=null">${csSaleOpport$cssoTitle}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csSaleOpport.cssoTitle" id="cssoTitle"  value="${csSaleOpport.cssoTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入销售机会的机会标题</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****机会标题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoTitle">${csSaleOpport.cssoTitle}</textarea>
		 		<span>
		 	
			 ${csSaleOpport.cssoTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoTitle}
	</s:if>
	
	${lz:set("注释","*****************归属战役字段的输入框代码*****************")}
	${lz:set("注释","before$cssoBattle和after$cssoBattle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoBattle==true">
	${before$cssoBattle}
	<dl class="cssoBattle " major  ref="cssoBattle" >
		<dt>归属战役:</dt>
		<s:if test="#request.CTRL.e.cssoBattle==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csSaleOpport$cssoBattle!=null">${csSaleOpport$cssoBattle}</s:if><s:else>
		 			<input title="请输入销售战役战役标题进行查询" class="combox narrow" action="${basePath}${proname}/sale/battle_query.do?value={param}&tbsbHost={cssoHost}" type="text" id="cssoBattle" name="csSaleOpport.cssoBattle" text="${get:TbSaleBattle(csSaleOpport.cssoBattle).tbsbTitle}" value="${csSaleOpport.cssoBattle}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssoBattle').val())==''){return;};window.href('${basePath}${proname}/sale/battle_details.do?key='+$('#cssoBattle').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****归属战役字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoBattle">${csSaleOpport.cssoBattle}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/sale/battle_details.do?key=${csSaleOpport.cssoBattle}',{ctrl:{editable:false,visible:true}})">
			 ${csSaleOpport.cssoBattle$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoBattle}
	</s:if>
	
	${lz:set("注释","*****************归属活动字段的输入框代码*****************")}
	${lz:set("注释","before$cssoEvent和after$cssoEvent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoEvent==true">
	${before$cssoEvent}
	<dl class="cssoEvent " major  ref="cssoEvent" >
		<dt>归属活动:</dt>
		<s:if test="#request.CTRL.e.cssoEvent==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csSaleOpport$cssoEvent!=null">${csSaleOpport$cssoEvent}</s:if><s:else>
		 			<input title="请输入销售活动活动主题进行查询" class="combox narrow" action="${basePath}${proname}/sale/event_query.do?value={param}&csseHost={cssoHost}" type="text" id="cssoEvent" name="csSaleOpport.cssoEvent" text="${get:CsSaleEvent(csSaleOpport.cssoEvent).csseSubject}" value="${csSaleOpport.cssoEvent}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssoEvent').val())==''){return;};window.href('${basePath}${proname}/sale/event_details.do?key='+$('#cssoEvent').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****归属活动字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoEvent">${csSaleOpport.cssoEvent}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/sale/event_details.do?key=${csSaleOpport.cssoEvent}',{ctrl:{editable:false,visible:true}})">
			 ${csSaleOpport.cssoEvent$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoEvent}
	</s:if>
	
	${lz:set("注释","*****************机会来源字段的输入框代码*****************")}
	${lz:set("注释","before$cssoFrom和after$cssoFrom变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoFrom==true">
	${before$cssoFrom}
	<dl class="cssoFrom " major  ref="cssoFrom" >
		<dt>机会来源:</dt>
		<s:if test="#request.CTRL.e.cssoFrom==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csSaleOpport$cssoFrom!=null">${csSaleOpport$cssoFrom}</s:if><s:else>
		 	<select class="narrow" id="cssoFrom" name="csSaleOpport.cssoFrom">
		 		<option value="">请选择</option>
 					${lz:set("items", get:propertys("销售机会来源","opport_from"))}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.spId}" ${csSaleOpport.cssoFrom==item.spId?"selected":""}>${item.spName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssoFrom').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?key='+$('#cssoFrom').val(),{ctrl:{editable:false}})"></a>
	 			<a class="reladd" config="{
	 				action:'${basePath}${proname}/configurator/property_edit.do?rd=1&parent=${get:property("opport_from").spId}'
	 				,callback:function(object){
	 					$('<option selected value='+object.spId+'>'+object.spName+'</option>').appendTo('#cssoFrom');
	 				}
	 			}" href="javascript:void(0);"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>比如：电话来访?独立开发?客户介绍?朋友介绍?媒体宣传?网络搜索?网络广告?报纸广告?代理商?会议促销?公开招标?客户主动上门</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****机会来源字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoFrom">${csSaleOpport.cssoFrom}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${csSaleOpport.cssoFrom}',{ctrl:{editable:false,visible:true}})">
			 ${csSaleOpport.cssoFrom$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoFrom}
	</s:if>
	
	${lz:set("注释","*****************客户资料字段的输入框代码*****************")}
	${lz:set("注释","before$cssoCustom和after$cssoCustom变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoCustom==true">
	${before$cssoCustom}
	<dl class="cssoCustom " major  ref="cssoCustom" >
		<dt>客户资料:</dt>
		<s:if test="#request.CTRL.e.cssoCustom==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csSaleOpport$cssoCustom!=null">${csSaleOpport$cssoCustom}</s:if><s:else>
		 			<input title="请输入客户资料客户名称进行查询" class="combox narrow" action="${basePath}${proname}/sale/custom_query.do?value={param}&tbscHost={cssoHost}" type="text" id="cssoCustom" name="csSaleOpport.cssoCustom" text="${get:TbSaleCustom(csSaleOpport.cssoCustom).tbscName}" value="${csSaleOpport.cssoCustom}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssoCustom').val())==''){return;};window.href('${basePath}${proname}/sale/custom_details.do?key='+$('#cssoCustom').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****客户资料字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoCustom">${csSaleOpport.cssoCustom}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/sale/custom_details.do?key=${csSaleOpport.cssoCustom}',{ctrl:{editable:false,visible:true}})">
			 ${csSaleOpport.cssoCustom$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoCustom}
	</s:if>
	
	${lz:set("注释","*****************客户姓名字段的输入框代码*****************")}
	${lz:set("注释","before$cssoContact和after$cssoContact变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoContact==true">
	${before$cssoContact}
	<dl class="cssoContact " major  ref="cssoContact" >
		<dt>客户姓名:</dt>
		<s:if test="#request.CTRL.e.cssoContact==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csSaleOpport$cssoContact!=null">${csSaleOpport$cssoContact}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csSaleOpport.cssoContact" id="cssoContact"  value="${csSaleOpport.cssoContact}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入销售机会的客户姓名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****客户姓名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoContact">${csSaleOpport.cssoContact}</textarea>
		 		<span>
		 	
			 ${csSaleOpport.cssoContact$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoContact}
	</s:if>
	
	${lz:set("注释","*****************联系电话字段的输入框代码*****************")}
	${lz:set("注释","before$cssoNumber和after$cssoNumber变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoNumber==true">
	${before$cssoNumber}
	<dl class="cssoNumber " major  ref="cssoNumber" >
		<dt>联系电话:</dt>
		<s:if test="#request.CTRL.e.cssoNumber==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csSaleOpport$cssoNumber!=null">${csSaleOpport$cssoNumber}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csSaleOpport.cssoNumber" id="cssoNumber"  value="${csSaleOpport.cssoNumber}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入销售机会的联系电话</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****联系电话字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoNumber">${csSaleOpport.cssoNumber}</textarea>
		 		<span>
		 	
			 ${csSaleOpport.cssoNumber$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoNumber}
	</s:if>
	
	${lz:set("注释","*****************成功机率字段的输入框代码*****************")}
	${lz:set("注释","before$cssoRate和after$cssoRate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoRate==true">
	${before$cssoRate}
	<dl class="cssoRate " major  ref="cssoRate" >
		<dt>成功机率:</dt>
		<s:if test="#request.CTRL.e.cssoRate==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csSaleOpport$cssoRate!=null">${csSaleOpport$cssoRate}</s:if><s:else>
		 	<select class="narrow" id="cssoRate" name="csSaleOpport.cssoRate">
		 		<option value="">请选择</option>
				<option value="0" ${csSaleOpport.cssoRate==0?"selected":""}>1%</option>
				<option value="1" ${csSaleOpport.cssoRate==1?"selected":""}>25%</option>
				<option value="2" ${csSaleOpport.cssoRate==2?"selected":""}>50%</option>
				<option value="3" ${csSaleOpport.cssoRate==3?"selected":""}>75%</option>
				<option value="4" ${csSaleOpport.cssoRate==4?"selected":""}>99%</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择销售机会的成功机率</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****成功机率字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoRate">${csSaleOpport.cssoRate}</textarea>
		 		<span>
		 	
			 ${csSaleOpport.cssoRate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoRate}
	</s:if>
	
	${lz:set("注释","*****************所有人字段的输入框代码*****************")}
	${lz:set("注释","before$cssoOnwer和after$cssoOnwer变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoOnwer==true">
	${before$cssoOnwer}
	<dl class="cssoOnwer " major  ref="cssoOnwer" >
		<dt>所&nbsp;&nbsp;有&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.cssoOnwer==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csSaleOpport$cssoOnwer!=null">${csSaleOpport$cssoOnwer}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cssoOnwer" name="csSaleOpport.cssoOnwer">
		 				<option selected value="${csSaleOpport.cssoOnwer}">
		 					${get:SrvUser(csSaleOpport.cssoOnwer).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssoOnwer').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cssoOnwer').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所有人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoOnwer">${csSaleOpport.cssoOnwer}</textarea>
		 		<span>
		 	
			 ${csSaleOpport.cssoOnwer$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoOnwer}
	</s:if>
	
	${lz:set("注释","*****************添加人字段的输入框代码*****************")}
	${lz:set("注释","before$cssoUser和after$cssoUser变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoUser==true">
	${before$cssoUser}
	<dl class="cssoUser " major  ref="cssoUser" >
		<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.cssoUser==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csSaleOpport$cssoUser!=null">${csSaleOpport$cssoUser}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cssoUser" name="csSaleOpport.cssoUser">
		 				<option selected value="${csSaleOpport.cssoUser}">
		 					${get:SrvUser(csSaleOpport.cssoUser).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssoUser').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cssoUser').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoUser">${csSaleOpport.cssoUser}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csSaleOpport.cssoUser}',{ctrl:{editable:false,visible:true}})">
			 ${csSaleOpport.cssoUser$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoUser}
	</s:if>
	
	${lz:set("注释","*****************跟单标签字段的输入框代码*****************")}
	${lz:set("注释","before$cssoTag和after$cssoTag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoTag==true">
	${before$cssoTag}
	<dl class="cssoTag " minor  ref="cssoTag" style="width:98%;">
		<dt>跟单标签:</dt>
		<s:if test="#request.CTRL.e.cssoTag==true">
		${lz:set("haveEditable",true)}
		<dd input="checkbox">
		<s:if test="#request.csSaleOpport$cssoTag!=null">${csSaleOpport$cssoTag}</s:if><s:else>
		 	<div class='wide input'>	
		 	&nbsp; 	
		 	<input type="hidden" value="#0#" name="csSaleOpport.cssoTag" id="cssoTag"/>
 					${lz:set("items", get:propertys("销售机会跟单标签","opport_tag"))}
					<s:iterator value="#request.items" id="item" status="i">
			<label><input type="checkbox" ${lz:idin(csSaleOpport.cssoTag,item.sp_id)?"checked='checked'":""} value="#${item.sp_id}#" name="csSaleOpport.cssoTag" id="cssoTag"/>
					${item.sp_name}&nbsp;&nbsp;&nbsp;</label>
					</s:iterator>
					${lz:set("items",null)}
		 </div>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择销售机会的跟单标签</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****跟单标签字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cssoTag">${csSaleOpport.cssoTag}</textarea>
		 		<span>
		 	
			 ${csSaleOpport.cssoTag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoTag}
	</s:if>
	
	${lz:set("注释","*****************机会描述字段的输入框代码*****************")}
	${lz:set("注释","before$cssoProfile和after$cssoProfile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoProfile==true">
	${before$cssoProfile}
	<dl class="cssoProfile " major  ref="cssoProfile" style="width:98%;">
		<dt>机会描述:</dt>
		<s:if test="#request.CTRL.e.cssoProfile==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csSaleOpport$cssoProfile!=null">${csSaleOpport$cssoProfile}</s:if><s:else>
		 	<textarea class="input wide"  id="cssoProfile" name="csSaleOpport.cssoProfile" rows="5">${csSaleOpport.cssoProfile}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入销售机会的机会描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****机会描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csSaleOpport.cssoProfile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoProfile}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cssoUpdateTime和after$cssoUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoUpdateTime==true">
	${before$cssoUpdateTime}
	<dl class="cssoUpdateTime " major  ref="cssoUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cssoUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csSaleOpport$cssoUpdateTime!=null">${csSaleOpport$cssoUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csSaleOpport.cssoUpdateTime" id="cssoUpdateTime"  value="<s:date name="csSaleOpport.cssoUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择销售机会的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoUpdateTime">${csSaleOpport.cssoUpdateTime}</textarea>
		 		<span>
		 	
			 ${csSaleOpport.cssoUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cssoAddTime和after$cssoAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoAddTime==true">
	${before$cssoAddTime}
	<dl class="cssoAddTime " major  ref="cssoAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cssoAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csSaleOpport$cssoAddTime!=null">${csSaleOpport$cssoAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csSaleOpport.cssoAddTime" id="cssoAddTime"  value="<s:date name="csSaleOpport.cssoAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择销售机会的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoAddTime">${csSaleOpport.cssoAddTime}</textarea>
		 		<span>
		 	
			 ${csSaleOpport.cssoAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cssoStatus和after$cssoStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssoStatus==true">
	${before$cssoStatus}
	<dl class="cssoStatus " major  ref="cssoStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cssoStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csSaleOpport$cssoStatus!=null">${csSaleOpport$cssoStatus}</s:if><s:else>
		 	<select class="narrow" id="cssoStatus" name="csSaleOpport.cssoStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csSaleOpport.cssoStatus==0?"selected":""}>跟踪</option>
				<option value="1" ${csSaleOpport.cssoStatus==1?"selected":""}>成功</option>
				<option value="2" ${csSaleOpport.cssoStatus==2?"selected":""}>搁置</option>
				<option value="3" ${csSaleOpport.cssoStatus==3?"selected":""}>失败</option>
				<option value="4" ${csSaleOpport.cssoStatus==4?"selected":""}>失效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择销售机会的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssoStatus">${csSaleOpport.cssoStatus}</textarea>
		 		<span>
		 	
			 ${csSaleOpport.cssoStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssoStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csSaleOpport.cssoId}" name="csSaleOpport.cssoId" id="cssoId" />
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