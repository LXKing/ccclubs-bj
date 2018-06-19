<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/csm/care.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/csm/care.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csCare.cscId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cscId,cscHost,cscMember,cscSort,cscType,cscTitle,cscDesc,cscAlarmTime,cscStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscId,cscHost,cscMember,cscSort,cscType,cscTitle,cscDesc,cscAlarmTime,cscStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cscId,cscHost,cscMember,cscSort,cscType,cscTitle,cscDesc,cscAlarmTime,cscStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscId,cscHost,cscMember,cscSort,cscType,cscTitle,cscDesc,cscAlarmTime,cscStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"客户关怀编辑":CTRL.title}</title>
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
	$("#careForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cscId==true">
		${lz:set("haveEditable",true)}
		,"csCare.cscId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscHost==true">
		${lz:set("haveEditable",true)}
		,"csCare.cscHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscMember==true">
		${lz:set("haveEditable",true)}
		,"csCare.cscMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscUser==true">
		${lz:set("haveEditable",true)}
		,"csCare.cscUser":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscSort==true">
		${lz:set("haveEditable",true)}
		,"csCare.cscSort":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择关怀类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscType==true">
		${lz:set("haveEditable",true)}
		,"csCare.cscType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择关怀方式";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscTitle==true">
		${lz:set("haveEditable",true)}
		,"csCare.cscTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "关怀主题不能为空";
			if(el.value.length>64 && el.value.indexOf("[*]")==-1)
				return "关怀主题最大长度为64个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscDesc==true">
		${lz:set("haveEditable",true)}
		,"csCare.cscDesc":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "关怀描述最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csCare.cscUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscAddTime==true">
		${lz:set("haveEditable",true)}
		,"csCare.cscAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscAlarmTime==true">
		${lz:set("haveEditable",true)}
		,"csCare.cscAlarmTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscStatus==true">
		${lz:set("haveEditable",true)}
		,"csCare.cscStatus":function(el){
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
	<form class="form editform" ref="CsCare" id="careForm" name="careForm" action="${empty CTRL.action?"care_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写客户关怀相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csCareToken}" name="csCareToken" id="csCareToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csCare.cscId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cscId和after$cscId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscId==true">
	${before$cscId}
	<dl class="cscId ${CTRL.e.cscId?"hidden":""}" major  ref="cscId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cscId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csCare$cscId!=null">${csCare$cscId}</s:if><s:else>
			<input type="hidden" value="${csCare.cscId}" name="csCare.cscId" id="cscId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscId">${csCare.cscId}</textarea>
		 		<span>
		 	
			 ${csCare.cscId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cscHost和after$cscHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscHost==true">
	${before$cscHost}
	<dl class="cscHost " major  ref="cscHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cscHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCare$cscHost!=null">${csCare$cscHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cscHost" name="csCare.cscHost">
		 				<option selected value="${csCare.cscHost}">
		 					${get:SrvHost(csCare.cscHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cscHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscHost">${csCare.cscHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csCare.cscHost}',{ctrl:{editable:false,visible:true}})">
			 ${csCare.cscHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscHost}
	</s:if>
	
	${lz:set("注释","*****************关怀会员字段的输入框代码*****************")}
	${lz:set("注释","before$cscMember和after$cscMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscMember==true">
	${before$cscMember}
	<dl class="cscMember " major  ref="cscMember" >
		<dt>关怀会员:</dt>
		<s:if test="#request.CTRL.e.cscMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csCare$cscMember!=null">${csCare$cscMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={cscHost}" type="text" id="cscMember" name="csCare.cscMember" text="${get:CsMember(csCare.cscMember).csmName}" value="${csCare.cscMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#cscMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关怀会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscMember">${csCare.cscMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csCare.cscMember}',{ctrl:{editable:false,visible:true}})">
			 ${csCare.cscMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscMember}
	</s:if>
	
	${lz:set("注释","*****************执行人字段的输入框代码*****************")}
	${lz:set("注释","before$cscUser和after$cscUser变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscUser==true">
	${before$cscUser}
	<dl class="cscUser " major  ref="cscUser" >
		<dt>执&nbsp;&nbsp;行&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.cscUser==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCare$cscUser!=null">${csCare$cscUser}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cscUser" name="csCare.cscUser">
		 				<option selected value="${csCare.cscUser}">
		 					${get:SrvUser(csCare.cscUser).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscUser').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cscUser').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****执行人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscUser">${csCare.cscUser}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csCare.cscUser}',{ctrl:{editable:false,visible:true}})">
			 ${csCare.cscUser$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscUser}
	</s:if>
	
	${lz:set("注释","*****************关怀类型字段的输入框代码*****************")}
	${lz:set("注释","before$cscSort和after$cscSort变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscSort==true">
	${before$cscSort}
	<dl class="cscSort " major  ref="cscSort" >
		<dt>关怀类型:</dt>
		<s:if test="#request.CTRL.e.cscSort==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCare$cscSort!=null">${csCare$cscSort}</s:if><s:else>
		 	<select class="narrow" id="cscSort" name="csCare.cscSort">
		 		<option value="">请选择</option>
 					${lz:set("items", get:propertys("客户关怀类型","care_sort"))}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.spId}" ${csCare.cscSort==item.spId?"selected":""}>${item.spName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscSort').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?key='+$('#cscSort').val(),{ctrl:{editable:false}})"></a>
	 			<a class="reladd" config="{
	 				action:'${basePath}${proname}/configurator/property_edit.do?rd=1&parent=${get:property("care_sort").spId}'
	 				,callback:function(object){
	 					$('<option selected value='+object.spId+'>'+object.spName+'</option>').appendTo('#cscSort');
	 				}
	 			}" href="javascript:void(0);"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户关怀的关怀类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关怀类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscSort">${csCare.cscSort}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${csCare.cscSort}',{ctrl:{editable:false,visible:true}})">
			 ${csCare.cscSort$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscSort}
	</s:if>
	
	${lz:set("注释","*****************关怀方式字段的输入框代码*****************")}
	${lz:set("注释","before$cscType和after$cscType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscType==true">
	${before$cscType}
	<dl class="cscType " major  ref="cscType" >
		<dt>关怀方式:</dt>
		<s:if test="#request.CTRL.e.cscType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCare$cscType!=null">${csCare$cscType}</s:if><s:else>
		 	<select class="narrow" id="cscType" name="csCare.cscType">
		 		<option value="">请选择</option>
				<option value="0" ${csCare.cscType==0?"selected":""}>打电话</option>
				<option value="1" ${csCare.cscType==1?"selected":""}>发短信</option>
				<option value="2" ${csCare.cscType==2?"selected":""}>上门</option>
				<option value="3" ${csCare.cscType==3?"selected":""}>电子邮件</option>
				<option value="4" ${csCare.cscType==4?"selected":""}>信函</option>
				<option value="5" ${csCare.cscType==5?"selected":""}>送礼品</option>
				<option value="6" ${csCare.cscType==6?"selected":""}>其它</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户关怀的关怀方式</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关怀方式字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscType">${csCare.cscType}</textarea>
		 		<span>
		 	
			 ${csCare.cscType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscType}
	</s:if>
	
	${lz:set("注释","*****************关怀主题字段的输入框代码*****************")}
	${lz:set("注释","before$cscTitle和after$cscTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscTitle==true">
	${before$cscTitle}
	<dl class="cscTitle " major  ref="cscTitle" style="width:98%;">
		<dt>关怀主题:</dt>
		<s:if test="#request.CTRL.e.cscTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCare$cscTitle!=null">${csCare$cscTitle}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="64" name="csCare.cscTitle" id="cscTitle"  value="${csCare.cscTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入客户关怀的关怀主题</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关怀主题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscTitle">${csCare.cscTitle}</textarea>
		 		<span>
		 	
			 ${csCare.cscTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscTitle}
	</s:if>
	
	${lz:set("注释","*****************关怀描述字段的输入框代码*****************")}
	${lz:set("注释","before$cscDesc和after$cscDesc变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscDesc==true">
	${before$cscDesc}
	<dl class="cscDesc " minor  ref="cscDesc" style="width:98%;">
		<dt>关怀描述:</dt>
		<s:if test="#request.CTRL.e.cscDesc==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csCare$cscDesc!=null">${csCare$cscDesc}</s:if><s:else>
		 	<textarea class="input wide"  id="cscDesc" name="csCare.cscDesc" rows="5">${csCare.cscDesc}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入客户关怀的关怀描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关怀描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csCare.cscDesc$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscDesc}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscUpdateTime和after$cscUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscUpdateTime==true">
	${before$cscUpdateTime}
	<dl class="cscUpdateTime " major  ref="cscUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cscUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCare$cscUpdateTime!=null">${csCare$cscUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCare.cscUpdateTime" id="cscUpdateTime"  value="<s:date name="csCare.cscUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户关怀的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscUpdateTime">${csCare.cscUpdateTime}</textarea>
		 		<span>
		 	
			 ${csCare.cscUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscAddTime和after$cscAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscAddTime==true">
	${before$cscAddTime}
	<dl class="cscAddTime " major  ref="cscAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cscAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCare$cscAddTime!=null">${csCare$cscAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCare.cscAddTime" id="cscAddTime"  value="<s:date name="csCare.cscAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户关怀的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscAddTime">${csCare.cscAddTime}</textarea>
		 		<span>
		 	
			 ${csCare.cscAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscAddTime}
	</s:if>
	
	${lz:set("注释","*****************提醒时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscAlarmTime和after$cscAlarmTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscAlarmTime==true">
	${before$cscAlarmTime}
	<dl class="cscAlarmTime " minor  ref="cscAlarmTime" >
		<dt>提醒时间:</dt>
		<s:if test="#request.CTRL.e.cscAlarmTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCare$cscAlarmTime!=null">${csCare$cscAlarmTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCare.cscAlarmTime" id="cscAlarmTime"  value="<s:date name="csCare.cscAlarmTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择客户关怀的提醒时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****提醒时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscAlarmTime">${csCare.cscAlarmTime}</textarea>
		 		<span>
		 	
			 ${csCare.cscAlarmTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscAlarmTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cscStatus和after$cscStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscStatus==true">
	${before$cscStatus}
	<dl class="cscStatus " major  ref="cscStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cscStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCare$cscStatus!=null">${csCare$cscStatus}</s:if><s:else>
		 	<select class="narrow" id="cscStatus" name="csCare.cscStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csCare.cscStatus==0?"selected":""}>待跟踪</option>
				<option value="1" ${csCare.cscStatus==1?"selected":""}>已完成</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户关怀的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscStatus">${csCare.cscStatus}</textarea>
		 		<span>
		 	
			 ${csCare.cscStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csCare.cscId}" name="csCare.cscId" id="cscId" />
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