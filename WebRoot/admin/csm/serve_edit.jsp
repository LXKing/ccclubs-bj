<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/csm/serve.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/csm/serve.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csServe.cssId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cssId,cssHost,cssMember,cssSort,cssType,cssDir,cssTitle,cssDesc,cssAlarmTime,cssStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cssId,cssHost,cssMember,cssSort,cssType,cssDir,cssTitle,cssDesc,cssAlarmTime,cssStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cssId,cssHost,cssMember,cssSort,cssType,cssDir,cssTitle,cssDesc,cssAlarmTime,cssStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cssId,cssHost,cssMember,cssSort,cssType,cssDir,cssTitle,cssDesc,cssAlarmTime,cssStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"客户服务编辑":CTRL.title}</title>
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
	$("#serveForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cssId==true">
		${lz:set("haveEditable",true)}
		,"csServe.cssId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssHost==true">
		${lz:set("haveEditable",true)}
		,"csServe.cssHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssMember==true">
		${lz:set("haveEditable",true)}
		,"csServe.cssMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssUser==true">
		${lz:set("haveEditable",true)}
		,"csServe.cssUser":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssSort==true">
		${lz:set("haveEditable",true)}
		,"csServe.cssSort":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择服务类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssType==true">
		${lz:set("haveEditable",true)}
		,"csServe.cssType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择服务方式";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssDir==true">
		${lz:set("haveEditable",true)}
		,"csServe.cssDir":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择联系方向";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssTitle==true">
		${lz:set("haveEditable",true)}
		,"csServe.cssTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "服务标题不能为空";
			if(el.value.length>64 && el.value.indexOf("[*]")==-1)
				return "服务标题最大长度为64个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssDesc==true">
		${lz:set("haveEditable",true)}
		,"csServe.cssDesc":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "服务描述最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csServe.cssUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssAddTime==true">
		${lz:set("haveEditable",true)}
		,"csServe.cssAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssAlarmTime==true">
		${lz:set("haveEditable",true)}
		,"csServe.cssAlarmTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssStatus==true">
		${lz:set("haveEditable",true)}
		,"csServe.cssStatus":function(el){
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
	<form class="form editform" ref="CsServe" id="serveForm" name="serveForm" action="${empty CTRL.action?"serve_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写客户服务相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csServeToken}" name="csServeToken" id="csServeToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csServe.cssId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cssId和after$cssId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssId==true">
	${before$cssId}
	<dl class="cssId ${CTRL.e.cssId?"hidden":""}" major  ref="cssId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cssId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csServe$cssId!=null">${csServe$cssId}</s:if><s:else>
			<input type="hidden" value="${csServe.cssId}" name="csServe.cssId" id="cssId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssId">${csServe.cssId}</textarea>
		 		<span>
		 	
			 ${csServe.cssId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cssHost和after$cssHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssHost==true">
	${before$cssHost}
	<dl class="cssHost " major  ref="cssHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cssHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csServe$cssHost!=null">${csServe$cssHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cssHost" name="csServe.cssHost">
		 				<option selected value="${csServe.cssHost}">
		 					${get:SrvHost(csServe.cssHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cssHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssHost">${csServe.cssHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csServe.cssHost}',{ctrl:{editable:false,visible:true}})">
			 ${csServe.cssHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssHost}
	</s:if>
	
	${lz:set("注释","*****************会员字段的输入框代码*****************")}
	${lz:set("注释","before$cssMember和after$cssMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssMember==true">
	${before$cssMember}
	<dl class="cssMember " major  ref="cssMember" >
		<dt>会　　员:</dt>
		<s:if test="#request.CTRL.e.cssMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csServe$cssMember!=null">${csServe$cssMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={cssHost}" type="text" id="cssMember" name="csServe.cssMember" text="${get:CsMember(csServe.cssMember).csmName}" value="${csServe.cssMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#cssMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssMember">${csServe.cssMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csServe.cssMember}',{ctrl:{editable:false,visible:true}})">
			 ${csServe.cssMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssMember}
	</s:if>
	
	${lz:set("注释","*****************处理人员字段的输入框代码*****************")}
	${lz:set("注释","before$cssUser和after$cssUser变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssUser==true">
	${before$cssUser}
	<dl class="cssUser " major  ref="cssUser" >
		<dt>处理人员:</dt>
		<s:if test="#request.CTRL.e.cssUser==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csServe$cssUser!=null">${csServe$cssUser}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="cssUser" name="csServe.cssUser">
		 				<option selected value="${csServe.cssUser}">
		 					${get:SrvUser(csServe.cssUser).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssUser').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#cssUser').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****处理人员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssUser">${csServe.cssUser}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csServe.cssUser}',{ctrl:{editable:false,visible:true}})">
			 ${csServe.cssUser$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssUser}
	</s:if>
	
	${lz:set("注释","*****************服务类型字段的输入框代码*****************")}
	${lz:set("注释","before$cssSort和after$cssSort变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssSort==true">
	${before$cssSort}
	<dl class="cssSort " major  ref="cssSort" >
		<dt>服务类型:</dt>
		<s:if test="#request.CTRL.e.cssSort==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csServe$cssSort!=null">${csServe$cssSort}</s:if><s:else>
		 	<select class="narrow" id="cssSort" name="csServe.cssSort">
		 		<option value="">请选择</option>
 					${lz:set("items", get:propertys("客户服务类型","serve_sort"))}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.spId}" ${csServe.cssSort==item.spId?"selected":""}>${item.spName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssSort').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?key='+$('#cssSort').val(),{ctrl:{editable:false}})"></a>
	 			<a class="reladd" config="{
	 				action:'${basePath}${proname}/configurator/property_edit.do?rd=1&parent=${get:property("serve_sort").spId}'
	 				,callback:function(object){
	 					$('<option selected value='+object.spId+'>'+object.spName+'</option>').appendTo('#cssSort');
	 				}
	 			}" href="javascript:void(0);"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户服务的服务类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****服务类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssSort">${csServe.cssSort}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${csServe.cssSort}',{ctrl:{editable:false,visible:true}})">
			 ${csServe.cssSort$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssSort}
	</s:if>
	
	${lz:set("注释","*****************服务方式字段的输入框代码*****************")}
	${lz:set("注释","before$cssType和after$cssType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssType==true">
	${before$cssType}
	<dl class="cssType " major  ref="cssType" >
		<dt>服务方式:</dt>
		<s:if test="#request.CTRL.e.cssType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csServe$cssType!=null">${csServe$cssType}</s:if><s:else>
		 	<select class="narrow" id="cssType" name="csServe.cssType">
		 		<option value="">请选择</option>
				<option value="0" ${csServe.cssType==0?"selected":""}>打电话</option>
				<option value="1" ${csServe.cssType==1?"selected":""}>发短信</option>
				<option value="2" ${csServe.cssType==2?"selected":""}>上门</option>
				<option value="3" ${csServe.cssType==3?"selected":""}>电子邮件</option>
				<option value="4" ${csServe.cssType==4?"selected":""}>信函</option>
				<option value="5" ${csServe.cssType==5?"selected":""}>送礼品</option>
				<option value="6" ${csServe.cssType==6?"selected":""}>其它</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户服务的服务方式</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****服务方式字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssType">${csServe.cssType}</textarea>
		 		<span>
		 	
			 ${csServe.cssType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssType}
	</s:if>
	
	${lz:set("注释","*****************联系方向字段的输入框代码*****************")}
	${lz:set("注释","before$cssDir和after$cssDir变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssDir==true">
	${before$cssDir}
	<dl class="cssDir " major  ref="cssDir" >
		<dt>联系方向:</dt>
		<s:if test="#request.CTRL.e.cssDir==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csServe$cssDir!=null">${csServe$cssDir}</s:if><s:else>
		 	<select class="narrow" id="cssDir" name="csServe.cssDir">
		 		<option value="">请选择</option>
				<option value="0" ${csServe.cssDir==0?"selected":""}>车纷享>客户</option>
				<option value="1" ${csServe.cssDir==1?"selected":""}>客户>车纷享</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户服务的联系方向</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****联系方向字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssDir">${csServe.cssDir}</textarea>
		 		<span>
		 	
			 ${csServe.cssDir$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssDir}
	</s:if>
	
	${lz:set("注释","*****************服务标题字段的输入框代码*****************")}
	${lz:set("注释","before$cssTitle和after$cssTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssTitle==true">
	${before$cssTitle}
	<dl class="cssTitle " major  ref="cssTitle" style="width:98%;">
		<dt>服务标题:</dt>
		<s:if test="#request.CTRL.e.cssTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csServe$cssTitle!=null">${csServe$cssTitle}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="64" name="csServe.cssTitle" id="cssTitle"  value="${csServe.cssTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入客户服务的服务标题</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****服务标题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cssTitle">${csServe.cssTitle}</textarea>
		 		<span>
		 	
			 ${csServe.cssTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssTitle}
	</s:if>
	
	${lz:set("注释","*****************服务描述字段的输入框代码*****************")}
	${lz:set("注释","before$cssDesc和after$cssDesc变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssDesc==true">
	${before$cssDesc}
	<dl class="cssDesc " minor  ref="cssDesc" style="width:98%;">
		<dt>服务描述:</dt>
		<s:if test="#request.CTRL.e.cssDesc==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csServe$cssDesc!=null">${csServe$cssDesc}</s:if><s:else>
		 	<textarea class="input wide"  id="cssDesc" name="csServe.cssDesc" rows="5">${csServe.cssDesc}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入客户服务的服务描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****服务描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csServe.cssDesc$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssDesc}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cssUpdateTime和after$cssUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssUpdateTime==true">
	${before$cssUpdateTime}
	<dl class="cssUpdateTime " major  ref="cssUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cssUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csServe$cssUpdateTime!=null">${csServe$cssUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csServe.cssUpdateTime" id="cssUpdateTime"  value="<s:date name="csServe.cssUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户服务的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssUpdateTime">${csServe.cssUpdateTime}</textarea>
		 		<span>
		 	
			 ${csServe.cssUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cssAddTime和after$cssAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssAddTime==true">
	${before$cssAddTime}
	<dl class="cssAddTime " major  ref="cssAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cssAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csServe$cssAddTime!=null">${csServe$cssAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csServe.cssAddTime" id="cssAddTime"  value="<s:date name="csServe.cssAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户服务的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssAddTime">${csServe.cssAddTime}</textarea>
		 		<span>
		 	
			 ${csServe.cssAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssAddTime}
	</s:if>
	
	${lz:set("注释","*****************提醒时间字段的输入框代码*****************")}
	${lz:set("注释","before$cssAlarmTime和after$cssAlarmTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssAlarmTime==true">
	${before$cssAlarmTime}
	<dl class="cssAlarmTime " minor  ref="cssAlarmTime" >
		<dt>提醒时间:</dt>
		<s:if test="#request.CTRL.e.cssAlarmTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csServe$cssAlarmTime!=null">${csServe$cssAlarmTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csServe.cssAlarmTime" id="cssAlarmTime"  value="<s:date name="csServe.cssAlarmTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择客户服务的提醒时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****提醒时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssAlarmTime">${csServe.cssAlarmTime}</textarea>
		 		<span>
		 	
			 ${csServe.cssAlarmTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssAlarmTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cssStatus和after$cssStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssStatus==true">
	${before$cssStatus}
	<dl class="cssStatus " major  ref="cssStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cssStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csServe$cssStatus!=null">${csServe$cssStatus}</s:if><s:else>
		 	<select class="narrow" id="cssStatus" name="csServe.cssStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csServe.cssStatus==0?"selected":""}>待跟踪</option>
				<option value="1" ${csServe.cssStatus==1?"selected":""}>已完成</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户服务的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssStatus">${csServe.cssStatus}</textarea>
		 		<span>
		 	
			 ${csServe.cssStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csServe.cssId}" name="csServe.cssId" id="cssId" />
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