<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/unit/user.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/unit/user.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitUser.csuuId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csuuId,csuuHost,csuuUsername,csuuUnitInfo,csuuIsRoot,csuuLimit,csuuRealname,csuuMobile,csuuStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csuuId,csuuHost,csuuUsername,csuuUnitInfo,csuuIsRoot,csuuLimit,csuuRealname,csuuMobile,csuuStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csuuId,csuuHost,csuuUnitInfo,csuuIsRoot,csuuLimit,csuuRealname,csuuMobile,csuuStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csuuId,csuuHost,csuuUsername,csuuUnitInfo,csuuIsRoot,csuuLimit,csuuRealname,csuuMobile,csuuStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"企业车管编辑":CTRL.title}</title>
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
	$("#userForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csuuId==true">
		${lz:set("haveEditable",true)}
		,"csUnitUser.csuuId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuuHost==true">
		${lz:set("haveEditable",true)}
		,"csUnitUser.csuuHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuuUsername==true">
		${lz:set("haveEditable",true)}
		,"csUnitUser.csuuUsername":function(el){
			if(jQuery.trim(el.value)=="")
				return "登录帐号不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "登录帐号最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("user_query.do",{exists:true,csuuUsername:el.value});
				if(exists && exists.length>0){
					if(${csUnitUser.csuuId==null} || exists[0].value!=$("#csuuId").val())
						return "登录帐号已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuuPassword==true">
		${lz:set("haveEditable",true)}
		,"csUnitUser.csuuPassword":function(el){
			if(jQuery.trim(el.value)=="")
				return "登录密码不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "登录密码最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuuUnitInfo==true">
		${lz:set("haveEditable",true)}
		,"csUnitUser.csuuUnitInfo":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuuIsRoot==true">
		${lz:set("haveEditable",true)}
		,"csUnitUser.csuuIsRoot":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuuLimit==true">
		${lz:set("haveEditable",true)}
		,"csUnitUser.csuuLimit":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuuRealname==true">
		${lz:set("haveEditable",true)}
		,"csUnitUser.csuuRealname":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "真实姓名最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuuMobile==true">
		${lz:set("haveEditable",true)}
		,"csUnitUser.csuuMobile":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "联系电话最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuuAddTime==true">
		${lz:set("haveEditable",true)}
		,"csUnitUser.csuuAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuuLastIp==true">
		${lz:set("haveEditable",true)}
		,"csUnitUser.csuuLastIp":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "最后登录IP最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuuLastTime==true">
		${lz:set("haveEditable",true)}
		,"csUnitUser.csuuLastTime":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "最后登录时间最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuuStatus==true">
		${lz:set("haveEditable",true)}
		,"csUnitUser.csuuStatus":function(el){
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
	<form class="form editform" ref="CsUnitUser" id="userForm" name="userForm" action="${empty CTRL.action?"user_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写企业车管相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csUnitUserToken}" name="csUnitUserToken" id="csUnitUserToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitUser.csuuId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csuuId和after$csuuId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuuId==true">
	${before$csuuId}
	<dl class="csuuId ${CTRL.e.csuuId?"hidden":""}" major  ref="csuuId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csuuId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csUnitUser$csuuId!=null">${csUnitUser$csuuId}</s:if><s:else>
			<input type="hidden" value="${csUnitUser.csuuId}" name="csUnitUser.csuuId" id="csuuId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuuId">${csUnitUser.csuuId}</textarea>
		 		<span>
		 	
			 ${csUnitUser.csuuId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuuId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csuuHost和after$csuuHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuuHost==true">
	${before$csuuHost}
	<dl class="csuuHost " major  ref="csuuHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csuuHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csUnitUser$csuuHost!=null">${csUnitUser$csuuHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csuuHost" name="csUnitUser.csuuHost">
		 				<option selected value="${csUnitUser.csuuHost}">
		 					${get:SrvHost(csUnitUser.csuuHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuuHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csuuHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuuHost">${csUnitUser.csuuHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csUnitUser.csuuHost}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitUser.csuuHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuuHost}
	</s:if>
	
	${lz:set("注释","*****************登录帐号字段的输入框代码*****************")}
	${lz:set("注释","before$csuuUsername和after$csuuUsername变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuuUsername==true">
	${before$csuuUsername}
	<dl class="csuuUsername " major  ref="csuuUsername" >
		<dt>登录帐号:</dt>
		<s:if test="#request.CTRL.e.csuuUsername==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitUser$csuuUsername!=null">${csUnitUser$csuuUsername}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitUser.csuuUsername" id="csuuUsername"  value="${csUnitUser.csuuUsername}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入企业车管的登录帐号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****登录帐号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuuUsername">${csUnitUser.csuuUsername}</textarea>
		 		<span>
		 	
			 ${csUnitUser.csuuUsername$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuuUsername}
	</s:if>
	
	${lz:set("注释","*****************登录密码字段的输入框代码*****************")}
	${lz:set("注释","before$csuuPassword和after$csuuPassword变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuuPassword==true">
	${before$csuuPassword}
	<dl class="csuuPassword " major  ref="csuuPassword" >
		<dt>登录密码:</dt>
		<s:if test="#request.CTRL.e.csuuPassword==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitUser$csuuPassword!=null">${csUnitUser$csuuPassword}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitUser.csuuPassword" id="csuuPassword"  value="${csUnitUser.csuuPassword}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>默认密码为用户名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****登录密码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuuPassword">${csUnitUser.csuuPassword}</textarea>
		 		<span>
		 	
			 ${csUnitUser.csuuPassword$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuuPassword}
	</s:if>
	
	${lz:set("注释","*****************所属单位字段的输入框代码*****************")}
	${lz:set("注释","before$csuuUnitInfo和after$csuuUnitInfo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuuUnitInfo==true">
	${before$csuuUnitInfo}
	<dl class="csuuUnitInfo " major  ref="csuuUnitInfo" >
		<dt>所属单位:</dt>
		<s:if test="#request.CTRL.e.csuuUnitInfo==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitUser$csuuUnitInfo!=null">${csUnitUser$csuuUnitInfo}</s:if><s:else>
		 			<input title="请输入企业信息名称进行查询" class="combox narrow" action="${basePath}${proname}/unit/info_query.do?value={param}&csuiHost={csuuHost}" type="text" id="csuuUnitInfo" name="csUnitUser.csuuUnitInfo" text="${get:CsUnitInfo(csUnitUser.csuuUnitInfo).csuiName}" value="${csUnitUser.csuuUnitInfo}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csuuUnitInfo').val())==''){return;};window.href('${basePath}${proname}/unit/info_details.do?key='+$('#csuuUnitInfo').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属单位字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuuUnitInfo">${csUnitUser.csuuUnitInfo}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/unit/info_details.do?key=${csUnitUser.csuuUnitInfo}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitUser.csuuUnitInfo$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuuUnitInfo}
	</s:if>
	
	${lz:set("注释","*****************系统管理员字段的输入框代码*****************")}
	${lz:set("注释","before$csuuIsRoot和after$csuuIsRoot变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuuIsRoot==true">
	${before$csuuIsRoot}
	<dl class="csuuIsRoot " major  ref="csuuIsRoot" >
		<dt>系统管理员:</dt>
		<s:if test="#request.CTRL.e.csuuIsRoot==true">
		${lz:set("haveEditable",true)}
		<dd input="radio">
		<s:if test="#request.csUnitUser$csuuIsRoot!=null">${csUnitUser$csuuIsRoot}</s:if><s:else>
		 	<div class='narrow input'>
		 	&nbsp;
	 	    <label><input type="radio" value="true" name="csUnitUser.csuuIsRoot" id="csuuIsRoot" ${csUnitUser.csuuIsRoot==true?"checked":""}/>是&nbsp;&nbsp;&nbsp;</label>
			<label><input type="radio" value="false" name="csUnitUser.csuuIsRoot" id="csuuIsRoot" ${csUnitUser==null || csUnitUser.csuuIsRoot==null || csUnitUser.csuuIsRoot==false?"checked":""}/>否</label>
		 	</div>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择企业车管的系统管理员</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****系统管理员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuuIsRoot">${csUnitUser.csuuIsRoot}</textarea>
		 		<span>
		 	
			 ${csUnitUser.csuuIsRoot$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuuIsRoot}
	</s:if>
	
	${lz:set("注释","*****************权限信息字段的输入框代码*****************")}
	${lz:set("注释","before$csuuLimit和after$csuuLimit变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuuLimit==true">
	${before$csuuLimit}
	<dl class="csuuLimit " major  ref="csuuLimit" style="width:98%;">
		<dt>权限信息:</dt>
		<s:if test="#request.CTRL.e.csuuLimit==true">
		${lz:set("haveEditable",true)}
		<dd input="checkbox">
		<s:if test="#request.csUnitUser$csuuLimit!=null">${csUnitUser$csuuLimit}</s:if><s:else>
		 	<div class='wide input'>	
		 	&nbsp; 	
		 	<input type="hidden" value="#0#" name="csUnitUser.csuuLimit" id="csuuLimit"/>
			<label><input type="checkbox" ${lz:idin(csUnitUser.csuuLimit,0) ? "checked='checked'" : ""} value="#0#" name="csUnitUser.csuuLimit" id="csuuLimit"/>订单管理&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:idin(csUnitUser.csuuLimit,1) ? "checked='checked'" : ""} value="#1#" name="csUnitUser.csuuLimit" id="csuuLimit"/>车辆管理&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:idin(csUnitUser.csuuLimit,2) ? "checked='checked'" : ""} value="#2#" name="csUnitUser.csuuLimit" id="csuuLimit"/>车辆监控&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:idin(csUnitUser.csuuLimit,3) ? "checked='checked'" : ""} value="#3#" name="csUnitUser.csuuLimit" id="csuuLimit"/>权限管理&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:idin(csUnitUser.csuuLimit,4) ? "checked='checked'" : ""} value="#4#" name="csUnitUser.csuuLimit" id="csuuLimit"/>价格管理&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:idin(csUnitUser.csuuLimit,5) ? "checked='checked'" : ""} value="#5#" name="csUnitUser.csuuLimit" id="csuuLimit"/>部门分组&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:idin(csUnitUser.csuuLimit,6) ? "checked='checked'" : ""} value="#6#" name="csUnitUser.csuuLimit" id="csuuLimit"/>用车人员&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:idin(csUnitUser.csuuLimit,7) ? "checked='checked'" : ""} value="#7#" name="csUnitUser.csuuLimit" id="csuuLimit"/>对账单&nbsp;&nbsp;&nbsp;</label>
		 </div>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择企业车管的权限信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****权限信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csuuLimit">${csUnitUser.csuuLimit}</textarea>
		 		<span>
		 	
			 ${csUnitUser.csuuLimit$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuuLimit}
	</s:if>
	
	${lz:set("注释","*****************真实姓名字段的输入框代码*****************")}
	${lz:set("注释","before$csuuRealname和after$csuuRealname变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuuRealname==true">
	${before$csuuRealname}
	<dl class="csuuRealname " major  ref="csuuRealname" >
		<dt>真实姓名:</dt>
		<s:if test="#request.CTRL.e.csuuRealname==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitUser$csuuRealname!=null">${csUnitUser$csuuRealname}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitUser.csuuRealname" id="csuuRealname"  value="${csUnitUser.csuuRealname}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业车管的真实姓名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****真实姓名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuuRealname">${csUnitUser.csuuRealname}</textarea>
		 		<span>
		 	
			 ${csUnitUser.csuuRealname$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuuRealname}
	</s:if>
	
	${lz:set("注释","*****************联系电话字段的输入框代码*****************")}
	${lz:set("注释","before$csuuMobile和after$csuuMobile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuuMobile==true">
	${before$csuuMobile}
	<dl class="csuuMobile " major  ref="csuuMobile" >
		<dt>联系电话:</dt>
		<s:if test="#request.CTRL.e.csuuMobile==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitUser$csuuMobile!=null">${csUnitUser$csuuMobile}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitUser.csuuMobile" id="csuuMobile"  value="${csUnitUser.csuuMobile}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业车管的联系电话</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****联系电话字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuuMobile">${csUnitUser.csuuMobile}</textarea>
		 		<span>
		 	
			 ${csUnitUser.csuuMobile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuuMobile}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csuuAddTime和after$csuuAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuuAddTime==true">
	${before$csuuAddTime}
	<dl class="csuuAddTime " major  ref="csuuAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csuuAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitUser$csuuAddTime!=null">${csUnitUser$csuuAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitUser.csuuAddTime" id="csuuAddTime"  value="<s:date name="csUnitUser.csuuAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择企业车管的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuuAddTime">${csUnitUser.csuuAddTime}</textarea>
		 		<span>
		 	
			 ${csUnitUser.csuuAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuuAddTime}
	</s:if>
	
	${lz:set("注释","*****************最后登录IP字段的输入框代码*****************")}
	${lz:set("注释","before$csuuLastIp和after$csuuLastIp变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuuLastIp==true">
	${before$csuuLastIp}
	<dl class="csuuLastIp " minor  ref="csuuLastIp" >
		<dt>最后登录IP:</dt>
		<s:if test="#request.CTRL.e.csuuLastIp==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitUser$csuuLastIp!=null">${csUnitUser$csuuLastIp}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitUser.csuuLastIp" id="csuuLastIp"  value="${csUnitUser.csuuLastIp}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业车管的最后登录IP</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****最后登录IP字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuuLastIp">${csUnitUser.csuuLastIp}</textarea>
		 		<span>
		 	
			 ${csUnitUser.csuuLastIp$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuuLastIp}
	</s:if>
	
	${lz:set("注释","*****************最后登录时间字段的输入框代码*****************")}
	${lz:set("注释","before$csuuLastTime和after$csuuLastTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuuLastTime==true">
	${before$csuuLastTime}
	<dl class="csuuLastTime " minor  ref="csuuLastTime" >
		<dt>最后登录时间:</dt>
		<s:if test="#request.CTRL.e.csuuLastTime==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitUser$csuuLastTime!=null">${csUnitUser$csuuLastTime}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitUser.csuuLastTime" id="csuuLastTime"  value="${csUnitUser.csuuLastTime}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入企业车管的最后登录时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****最后登录时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuuLastTime">${csUnitUser.csuuLastTime}</textarea>
		 		<span>
		 	
			 ${csUnitUser.csuuLastTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuuLastTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csuuStatus和after$csuuStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuuStatus==true">
	${before$csuuStatus}
	<dl class="csuuStatus " major  ref="csuuStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csuuStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csUnitUser$csuuStatus!=null">${csUnitUser$csuuStatus}</s:if><s:else>
		 	<select class="narrow" id="csuuStatus" name="csUnitUser.csuuStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csUnitUser.csuuStatus==1?"selected":""}>正常</option>
				<option value="0" ${csUnitUser.csuuStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择企业车管的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuuStatus">${csUnitUser.csuuStatus}</textarea>
		 		<span>
		 	
			 ${csUnitUser.csuuStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuuStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csUnitUser.csuuId}" name="csUnitUser.csuuId" id="csuuId" />
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