<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/configurator/config.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/configurator/config.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csConfig.cscId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cscId,cscLimitPower,cscFlags,cscAddress,cscSvrNumber,cscSrvQqGroup,cscBanners",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscId,cscHost,cscLimitPower,cscFlags,cscAddress,cscSvrNumber,cscSrvQqGroup,cscBanners",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cscId,cscLimitPower,cscFlags,cscAddress,cscSvrNumber,cscSrvQqGroup,cscBanners",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscId,cscHost,cscLimitPower,cscFlags,cscAddress,cscSvrNumber,cscSrvQqGroup,cscBanners",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"系统配置(城市)编辑":CTRL.title}</title>
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
<style>
select.wide{display:none;}
</style>
<script>
</script>
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
	$("#configForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cscId==true">
		${lz:set("haveEditable",true)}
		,"csConfig.cscId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscHost==true">
		${lz:set("haveEditable",true)}
		,"csConfig.cscHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscLimitPower==true">
		${lz:set("haveEditable",true)}
		,"csConfig.cscLimitPower":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscFlags==true">
		${lz:set("haveEditable",true)}
		,"csConfig.cscFlags":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscAddress==true">
		${lz:set("haveEditable",true)}
		,"csConfig.cscAddress":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "联系地址最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscSvrNumber==true">
		${lz:set("haveEditable",true)}
		,"csConfig.cscSvrNumber":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "客服电话最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscSrvQqGroup==true">
		${lz:set("haveEditable",true)}
		,"csConfig.cscSrvQqGroup":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "QQ群号最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscBanners==true">
		${lz:set("haveEditable",true)}
		,"csConfig.cscBanners":function(el){
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
	<form class="form editform" ref="CsConfig" id="configForm" name="configForm" action="${empty CTRL.action?"config_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写系统配置(城市)相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csConfigToken}" name="csConfigToken" id="csConfigToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csConfig.cscId))}		
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
		<s:if test="#request.csConfig$cscId!=null">${csConfig$cscId}</s:if><s:else>
			<input type="hidden" value="${csConfig.cscId}" name="csConfig.cscId" id="cscId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscId">${csConfig.cscId}</textarea>
		 		<span>
		 	
			 ${csConfig.cscId$}
	 	  
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
		<s:if test="#request.csConfig$cscHost!=null">${csConfig$cscHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cscHost" name="csConfig.cscHost">
		 				<option selected value="${csConfig.cscHost}">
		 					${get:SrvHost(csConfig.cscHost).shName}
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
		 		<textarea class="" style="display:none;" id="cscHost">${csConfig.cscHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csConfig.cscHost}',{ctrl:{editable:false,visible:true}})">
			 ${csConfig.cscHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscHost}
	</s:if>
	
	${lz:set("注释","*****************电量阀值字段的输入框代码*****************")}
	${lz:set("注释","before$cscLimitPower和after$cscLimitPower变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscLimitPower==true">
	${before$cscLimitPower}
	<dl class="cscLimitPower " major  ref="cscLimitPower" >
		<dt>电量阀值:</dt>
		<s:if test="#request.CTRL.e.cscLimitPower==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csConfig$cscLimitPower!=null">${csConfig$cscLimitPower}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csConfig.cscLimitPower" id="cscLimitPower"  value="${csConfig.cscLimitPower}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>当车辆电量超过此值时发短信给维护人员</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****电量阀值字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscLimitPower">${csConfig.cscLimitPower}</textarea>
		 		<span>
		 	
			 ${csConfig.cscLimitPower$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscLimitPower}
	</s:if>
	
	${lz:set("注释","*****************可选标记字段的输入框代码*****************")}
	${lz:set("注释","before$cscFlags和after$cscFlags变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscFlags==true">
	${before$cscFlags}
	<dl class="cscFlags " major  ref="cscFlags" style="width:98%;">
		<dt>可选标记:</dt>
		<s:if test="#request.CTRL.e.cscFlags==true">
		${lz:set("haveEditable",true)}
		<dd input="checkbox">
		<s:if test="#request.csConfig$cscFlags!=null">${csConfig$cscFlags}</s:if><s:else>
		 	<div class='wide input'>	
		 	&nbsp; 	
		 	<input type="hidden" value="#0#" name="csConfig.cscFlags" id="cscFlags"/>
			<label><input type="checkbox" ${lz:idin(csConfig.cscFlags,0) ? "checked='checked'" : ""} value="#0#" name="csConfig.cscFlags" id="cscFlags"/>默认&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:idin(csConfig.cscFlags,1) ? "checked='checked'" : ""} value="#1#" name="csConfig.cscFlags" id="cscFlags"/>叫车&nbsp;&nbsp;&nbsp;</label>
		 </div>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择系统配置(城市)的可选标记</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****可选标记字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscFlags">${csConfig.cscFlags}</textarea>
		 		<span>
		 	
			 ${csConfig.cscFlags$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscFlags}
	</s:if>
	
	${lz:set("注释","*****************联系地址字段的输入框代码*****************")}
	${lz:set("注释","before$cscAddress和after$cscAddress变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscAddress==true">
	${before$cscAddress}
	<dl class="cscAddress " minor  ref="cscAddress" style="width:98%;">
		<dt>联系地址:</dt>
		<s:if test="#request.CTRL.e.cscAddress==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csConfig$cscAddress!=null">${csConfig$cscAddress}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="256" name="csConfig.cscAddress" id="cscAddress"  value="${csConfig.cscAddress}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统配置(城市)的联系地址</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****联系地址字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscAddress">${csConfig.cscAddress}</textarea>
		 		<span>
		 	
			 ${csConfig.cscAddress$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscAddress}
	</s:if>
	
	${lz:set("注释","*****************客服电话字段的输入框代码*****************")}
	${lz:set("注释","before$cscSvrNumber和after$cscSvrNumber变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscSvrNumber==true">
	${before$cscSvrNumber}
	<dl class="cscSvrNumber " major  ref="cscSvrNumber" >
		<dt>客服电话:</dt>
		<s:if test="#request.CTRL.e.cscSvrNumber==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csConfig$cscSvrNumber!=null">${csConfig$cscSvrNumber}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csConfig.cscSvrNumber" id="cscSvrNumber"  value="${csConfig.cscSvrNumber}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统配置(城市)的客服电话</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****客服电话字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscSvrNumber">${csConfig.cscSvrNumber}</textarea>
		 		<span>
		 	
			 ${csConfig.cscSvrNumber$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscSvrNumber}
	</s:if>
	
	${lz:set("注释","*****************QQ群号字段的输入框代码*****************")}
	${lz:set("注释","before$cscSrvQqGroup和after$cscSrvQqGroup变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscSrvQqGroup==true">
	${before$cscSrvQqGroup}
	<dl class="cscSrvQqGroup " major  ref="cscSrvQqGroup" >
		<dt>QQ群号:</dt>
		<s:if test="#request.CTRL.e.cscSrvQqGroup==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csConfig$cscSrvQqGroup!=null">${csConfig$cscSrvQqGroup}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csConfig.cscSrvQqGroup" id="cscSrvQqGroup"  value="${csConfig.cscSrvQqGroup}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统配置(城市)的QQ群号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****QQ群号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscSrvQqGroup">${csConfig.cscSrvQqGroup}</textarea>
		 		<span>
		 	
			 ${csConfig.cscSrvQqGroup$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscSrvQqGroup}
	</s:if>
	
	${lz:set("注释","*****************首页海报字段的输入框代码*****************")}
	${lz:set("注释","before$cscBanners和after$cscBanners变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscBanners==true">
	${before$cscBanners}
	<dl class="cscBanners " minor  ref="cscBanners" style="width:98%;">
		<dt>首页海报:</dt>
		<s:if test="#request.CTRL.e.cscBanners==true">
		${lz:set("haveEditable",true)}
		<dd input="grid">
		<s:if test="#request.csConfig$cscBanners!=null">${csConfig$cscBanners}</s:if><s:else>
		    <textarea class="input wide grid" grid="[
		    	['海报图',null,'image','image',null]
		    	,['链接地址',null,'text','link',null]
		    ]" id="cscBanners" name="csConfig.cscBanners">${csConfig.cscBanners}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****首页海报字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscBanners">${csConfig.cscBanners}</textarea>
		 		<span>
		 	
			 ${csConfig.cscBanners$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscBanners}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csConfig.cscId}" name="csConfig.cscId" id="cscId" />
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