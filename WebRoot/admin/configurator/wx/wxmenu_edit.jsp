<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/configurator/wx/wxmenu.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/configurator/wx/wxmenu.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csWxMenu.cswmId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cswmId,cswmName,cswmParent,cswmType,cswmSupport,cswmUrlKey,cswmLevel,cswmStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cswmId,cswmName,cswmParent,cswmType,cswmSupport,cswmUrlKey,cswmLevel,cswmStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cswmId,cswmName,cswmParent,cswmType,cswmSupport,cswmUrlKey,cswmLevel,cswmStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cswmId,cswmName,cswmParent,cswmType,cswmSupport,cswmUrlKey,cswmLevel,cswmStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"微信菜单编辑":CTRL.title}</title>
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
	$("#wxmenuForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cswmId==true">
		${lz:set("haveEditable",true)}
		,"csWxMenu.cswmId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmName==true">
		${lz:set("haveEditable",true)}
		,"csWxMenu.cswmName":function(el){
			if(jQuery.trim(el.value)=="")
				return "菜单名称不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmParent==true">
		${lz:set("haveEditable",true)}
		,"csWxMenu.cswmParent":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmType==true">
		${lz:set("haveEditable",true)}
		,"csWxMenu.cswmType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择菜单类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmSupport==true">
		${lz:set("haveEditable",true)}
		,"csWxMenu.cswmSupport":function(el){
			if(!false && $("input[name='csWxMenu.cswmSupport']:checked").length==0)
				return "支持终端类型至少选择一个";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmUrlKey==true">
		${lz:set("haveEditable",true)}
		,"csWxMenu.cswmUrlKey":function(el){
			if(jQuery.trim(el.value)=="")
				return "地址或消息不能为空";
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "地址或消息最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmLevel==true">
		${lz:set("haveEditable",true)}
		,"csWxMenu.cswmLevel":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择优先级";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmTime==true">
		${lz:set("haveEditable",true)}
		,"csWxMenu.cswmTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "更改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmStatus==true">
		${lz:set("haveEditable",true)}
		,"csWxMenu.cswmStatus":function(el){
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
	<form class="form editform" ref="CsWxMenu" id="wxmenuForm" name="wxmenuForm" action="${empty CTRL.action?"wxmenu_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写微信菜单相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csWxMenuToken}" name="csWxMenuToken" id="csWxMenuToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csWxMenu.cswmId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cswmId和after$cswmId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmId==true">
	${before$cswmId}
	<dl class="cswmId ${CTRL.e.cswmId?"hidden":""}" major  ref="cswmId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cswmId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csWxMenu$cswmId!=null">${csWxMenu$cswmId}</s:if><s:else>
			<input type="hidden" value="${csWxMenu.cswmId}" name="csWxMenu.cswmId" id="cswmId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmId">${csWxMenu.cswmId}</textarea>
		 		<span>
		 	
			 ${csWxMenu.cswmId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmId}
	</s:if>
	
	${lz:set("注释","*****************菜单名称字段的输入框代码*****************")}
	${lz:set("注释","before$cswmName和after$cswmName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmName==true">
	${before$cswmName}
	<dl class="cswmName " major  ref="cswmName" >
		<dt>菜单名称:</dt>
		<s:if test="#request.CTRL.e.cswmName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csWxMenu$cswmName!=null">${csWxMenu$cswmName}</s:if><s:else>
		 	<input type="text" class="input narrow"   name="csWxMenu.cswmName" id="cswmName"  value="${csWxMenu.cswmName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入微信菜单的菜单名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****菜单名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmName">${csWxMenu.cswmName}</textarea>
		 		<span>
		 	
			 ${csWxMenu.cswmName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmName}
	</s:if>
	
	${lz:set("注释","*****************上级菜单字段的输入框代码*****************")}
	${lz:set("注释","before$cswmParent和after$cswmParent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmParent==true">
	${before$cswmParent}
	<dl class="cswmParent ${CTRL.e.cswmParent?"hidden":""}" major  ref="cswmParent" >
		<dt>上级菜单:</dt>
		<s:if test="#request.CTRL.e.cswmParent==true">
		${lz:set("haveEditable",true)}
		<dd input="hidden">
		<s:if test="#request.csWxMenu$cswmParent!=null">${csWxMenu$cswmParent}</s:if><s:else>
			<input type="hidden" name="csWxMenu.cswmParent" id="csWxMenu.cswmParent"  value="${parentCsWxMenu==null ? "0" : parentCsWxMenu.cswmId}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****上级菜单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmParent">${csWxMenu.cswmParent}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/wx/wxmenu_details.do?key=${csWxMenu.cswmParent}',{ctrl:{editable:false,visible:true}})">
			 ${csWxMenu.cswmParent$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmParent}
	</s:if>
	
	${lz:set("注释","*****************菜单类型字段的输入框代码*****************")}
	${lz:set("注释","before$cswmType和after$cswmType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmType==true">
	${before$cswmType}
	<dl class="cswmType " major  ref="cswmType" >
		<dt>菜单类型:</dt>
		<s:if test="#request.CTRL.e.cswmType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csWxMenu$cswmType!=null">${csWxMenu$cswmType}</s:if><s:else>
		 	<select class="narrow" id="cswmType" name="csWxMenu.cswmType">
		 		<option value="">请选择</option>
				<option value="0" ${csWxMenu.cswmType==0?"selected":""}>链接</option>
				<option value="1" ${csWxMenu.cswmType==1?"selected":""}>发消息</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择微信菜单的菜单类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****菜单类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmType">${csWxMenu.cswmType}</textarea>
		 		<span>
		 	
			 ${csWxMenu.cswmType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmType}
	</s:if>
	
	${lz:set("注释","*****************支持终端类型字段的输入框代码*****************")}
	${lz:set("注释","before$cswmSupport和after$cswmSupport变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmSupport==true">
	${before$cswmSupport}
	<dl class="cswmSupport " major  ref="cswmSupport" style="width:98%;">
		<dt>支持终端类型:</dt>
		<s:if test="#request.CTRL.e.cswmSupport==true">
		${lz:set("haveEditable",true)}
		<dd input="checkbox">
		<s:if test="#request.csWxMenu$cswmSupport!=null">${csWxMenu$cswmSupport}</s:if><s:else>
		 	<div class='wide input'>	
		 	&nbsp; 	
		 	<input type="hidden" value="#0#" name="csWxMenu.cswmSupport" id="cswmSupport"/>
			<label><input type="checkbox" ${lz:idin(csWxMenu.cswmSupport,0) ? "checked='checked'" : ""} value="#0#" name="csWxMenu.cswmSupport" id="cswmSupport"/>微信&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:idin(csWxMenu.cswmSupport,1) ? "checked='checked'" : ""} value="#1#" name="csWxMenu.cswmSupport" id="cswmSupport"/>支付宝&nbsp;&nbsp;&nbsp;</label>
		 </div>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择微信菜单的支持终端类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****支持终端类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cswmSupport">${csWxMenu.cswmSupport}</textarea>
		 		<span>
		 	
			 ${csWxMenu.cswmSupport$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmSupport}
	</s:if>
	
	${lz:set("注释","*****************地址或消息字段的输入框代码*****************")}
	${lz:set("注释","before$cswmUrlKey和after$cswmUrlKey变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmUrlKey==true">
	${before$cswmUrlKey}
	<dl class="cswmUrlKey " major  ref="cswmUrlKey" style="width:98%;">
		<dt>地址或消息:</dt>
		<s:if test="#request.CTRL.e.cswmUrlKey==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csWxMenu$cswmUrlKey!=null">${csWxMenu$cswmUrlKey}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csWxMenu.cswmUrlKey" id="cswmUrlKey"  value="${csWxMenu.cswmUrlKey}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入微信菜单的地址或消息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****地址或消息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cswmUrlKey">${csWxMenu.cswmUrlKey}</textarea>
		 		<span>
		 	
			 ${csWxMenu.cswmUrlKey$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmUrlKey}
	</s:if>
	
	${lz:set("注释","*****************优先级字段的输入框代码*****************")}
	${lz:set("注释","before$cswmLevel和after$cswmLevel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmLevel==true">
	${before$cswmLevel}
	<dl class="cswmLevel " major  ref="cswmLevel" >
		<dt>优&nbsp;&nbsp;先&nbsp;&nbsp;级:</dt>
		<s:if test="#request.CTRL.e.cswmLevel==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csWxMenu$cswmLevel!=null">${csWxMenu$cswmLevel}</s:if><s:else>
		 	<select class="narrow" id="cswmLevel" name="csWxMenu.cswmLevel">
		 		<option value="">请选择</option>
				<option value="0" ${csWxMenu.cswmLevel==0?"selected":""}>默认</option>
				<option value="1" ${csWxMenu.cswmLevel==1?"selected":""}>1</option>
				<option value="2" ${csWxMenu.cswmLevel==2?"selected":""}>2</option>
				<option value="3" ${csWxMenu.cswmLevel==3?"selected":""}>3</option>
				<option value="4" ${csWxMenu.cswmLevel==4?"selected":""}>4</option>
				<option value="5" ${csWxMenu.cswmLevel==5?"selected":""}>5</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择微信菜单的优先级</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****优先级字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmLevel">${csWxMenu.cswmLevel}</textarea>
		 		<span>
		 	
			 ${csWxMenu.cswmLevel$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmLevel}
	</s:if>
	
	${lz:set("注释","*****************更改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cswmTime和after$cswmTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmTime==true">
	${before$cswmTime}
	<dl class="cswmTime " major  ref="cswmTime" >
		<dt>更改时间:</dt>
		<s:if test="#request.CTRL.e.cswmTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csWxMenu$cswmTime!=null">${csWxMenu$cswmTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csWxMenu.cswmTime" id="cswmTime"  value="<s:date name="csWxMenu.cswmTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择微信菜单的更改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****更改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmTime">${csWxMenu.cswmTime}</textarea>
		 		<span>
		 	
			 ${csWxMenu.cswmTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cswmStatus和after$cswmStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmStatus==true">
	${before$cswmStatus}
	<dl class="cswmStatus " major  ref="cswmStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cswmStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csWxMenu$cswmStatus!=null">${csWxMenu$cswmStatus}</s:if><s:else>
		 	<select class="narrow" id="cswmStatus" name="csWxMenu.cswmStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csWxMenu.cswmStatus==1?"selected":""}>正常</option>
				<option value="0" ${csWxMenu.cswmStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择微信菜单的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmStatus">${csWxMenu.cswmStatus}</textarea>
		 		<span>
		 	
			 ${csWxMenu.cswmStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csWxMenu.cswmId}" name="csWxMenu.cswmId" id="cswmId" />
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