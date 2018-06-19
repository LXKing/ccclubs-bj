<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/unit/mt.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/unit/mt.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitMt.csumId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csumId,csumHost,csumInfo,csumPerson,csumTitle,csumContent,csumType,csumData,csumReadTime,csumStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csumId,csumHost,csumInfo,csumPerson,csumTitle,csumContent,csumType,csumData,csumReadTime,csumStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csumId,csumHost,csumInfo,csumPerson,csumTitle,csumContent,csumType,csumData,csumReadTime,csumStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csumId,csumHost,csumInfo,csumPerson,csumTitle,csumContent,csumType,csumData,csumReadTime,csumStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"推送消息编辑":CTRL.title}</title>
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
	$("#mtForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csumId==true">
		${lz:set("haveEditable",true)}
		,"csUnitMt.csumId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csumHost==true">
		${lz:set("haveEditable",true)}
		,"csUnitMt.csumHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csumInfo==true">
		${lz:set("haveEditable",true)}
		,"csUnitMt.csumInfo":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csumPerson==true">
		${lz:set("haveEditable",true)}
		,"csUnitMt.csumPerson":function(el){
			if(jQuery.trim(el.value)=="")
				return "所属人员不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csumTitle==true">
		${lz:set("haveEditable",true)}
		,"csUnitMt.csumTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "消息标题不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "消息标题最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csumContent==true">
		${lz:set("haveEditable",true)}
		,"csUnitMt.csumContent":function(el){
			if(jQuery.trim(el.value)=="")
				return "消息内容不能为空";
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "消息内容最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csumType==true">
		${lz:set("haveEditable",true)}
		,"csUnitMt.csumType":function(el){
			if(jQuery.trim(el.value)=="")
				return "消息类型不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csumData==true">
		${lz:set("haveEditable",true)}
		,"csUnitMt.csumData":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csumReadTime==true">
		${lz:set("haveEditable",true)}
		,"csUnitMt.csumReadTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csumUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csUnitMt.csumUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csumAddTime==true">
		${lz:set("haveEditable",true)}
		,"csUnitMt.csumAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csumStatus==true">
		${lz:set("haveEditable",true)}
		,"csUnitMt.csumStatus":function(el){
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
	<form class="form editform" ref="CsUnitMt" id="mtForm" name="mtForm" action="${empty CTRL.action?"mt_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写推送消息相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csUnitMtToken}" name="csUnitMtToken" id="csUnitMtToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitMt.csumId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csumId和after$csumId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csumId==true">
	${before$csumId}
	<dl class="csumId ${CTRL.e.csumId?"hidden":""}" major  ref="csumId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csumId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csUnitMt$csumId!=null">${csUnitMt$csumId}</s:if><s:else>
			<input type="hidden" value="${csUnitMt.csumId}" name="csUnitMt.csumId" id="csumId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csumId">${csUnitMt.csumId}</textarea>
		 		<span>
		 	
			 ${csUnitMt.csumId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csumId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csumHost和after$csumHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csumHost==true">
	${before$csumHost}
	<dl class="csumHost " major  ref="csumHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csumHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csUnitMt$csumHost!=null">${csUnitMt$csumHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csumHost" name="csUnitMt.csumHost">
		 				<option selected value="${csUnitMt.csumHost}">
		 					${get:SrvHost(csUnitMt.csumHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csumHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csumHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csumHost">${csUnitMt.csumHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csUnitMt.csumHost}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitMt.csumHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csumHost}
	</s:if>
	
	${lz:set("注释","*****************所属企业字段的输入框代码*****************")}
	${lz:set("注释","before$csumInfo和after$csumInfo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csumInfo==true">
	${before$csumInfo}
	<dl class="csumInfo " major  ref="csumInfo" >
		<dt>所属企业:</dt>
		<s:if test="#request.CTRL.e.csumInfo==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitMt$csumInfo!=null">${csUnitMt$csumInfo}</s:if><s:else>
		 			<input title="请输入企业信息名称进行查询" class="combox narrow" action="${basePath}${proname}/unit/info_query.do?value={param}&csuiHost={csumHost}" type="text" id="csumInfo" name="csUnitMt.csumInfo" text="${get:CsUnitInfo(csUnitMt.csumInfo).csuiName}" value="${csUnitMt.csumInfo}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csumInfo').val())==''){return;};window.href('${basePath}${proname}/unit/info_details.do?key='+$('#csumInfo').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属企业字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csumInfo">${csUnitMt.csumInfo}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/unit/info_details.do?key=${csUnitMt.csumInfo}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitMt.csumInfo$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csumInfo}
	</s:if>
	
	${lz:set("注释","*****************所属人员字段的输入框代码*****************")}
	${lz:set("注释","before$csumPerson和after$csumPerson变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csumPerson==true">
	${before$csumPerson}
	<dl class="csumPerson " major  ref="csumPerson" >
		<dt>所属人员:</dt>
		<s:if test="#request.CTRL.e.csumPerson==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csUnitMt$csumPerson!=null">${csUnitMt$csumPerson}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitMt.csumPerson" id="csumPerson"  value="${csUnitMt.csumPerson}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入推送消息的所属人员</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属人员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csumPerson">${csUnitMt.csumPerson}</textarea>
		 		<span>
		 	
			 ${csUnitMt.csumPerson$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csumPerson}
	</s:if>
	
	${lz:set("注释","*****************消息标题字段的输入框代码*****************")}
	${lz:set("注释","before$csumTitle和after$csumTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csumTitle==true">
	${before$csumTitle}
	<dl class="csumTitle " major  ref="csumTitle" >
		<dt>消息标题:</dt>
		<s:if test="#request.CTRL.e.csumTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitMt$csumTitle!=null">${csUnitMt$csumTitle}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitMt.csumTitle" id="csumTitle"  value="${csUnitMt.csumTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入推送消息的消息标题</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****消息标题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csumTitle">${csUnitMt.csumTitle}</textarea>
		 		<span>
		 	
			 ${csUnitMt.csumTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csumTitle}
	</s:if>
	
	${lz:set("注释","*****************消息内容字段的输入框代码*****************")}
	${lz:set("注释","before$csumContent和after$csumContent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csumContent==true">
	${before$csumContent}
	<dl class="csumContent " major  ref="csumContent" style="width:98%;">
		<dt>消息内容:</dt>
		<s:if test="#request.CTRL.e.csumContent==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csUnitMt$csumContent!=null">${csUnitMt$csumContent}</s:if><s:else>
		 	<textarea class="input wide"  id="csumContent" name="csUnitMt.csumContent" rows="5">${csUnitMt.csumContent}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入推送消息的消息内容</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****消息内容字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csUnitMt.csumContent$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csumContent}
	</s:if>
	
	${lz:set("注释","*****************消息类型字段的输入框代码*****************")}
	${lz:set("注释","before$csumType和after$csumType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csumType==true">
	${before$csumType}
	<dl class="csumType " major  ref="csumType" >
		<dt>消息类型:</dt>
		<s:if test="#request.CTRL.e.csumType==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csUnitMt$csumType!=null">${csUnitMt$csumType}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csUnitMt.csumType" id="csumType"  value="${csUnitMt.csumType}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入推送消息的消息类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****消息类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csumType">${csUnitMt.csumType}</textarea>
		 		<span>
		 	
			 ${csUnitMt.csumType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csumType}
	</s:if>
	
	${lz:set("注释","*****************关联数据字段的输入框代码*****************")}
	${lz:set("注释","before$csumData和after$csumData变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csumData==true">
	${before$csumData}
	<dl class="csumData " major  ref="csumData" >
		<dt>关联数据:</dt>
		<s:if test="#request.CTRL.e.csumData==true">
		${lz:set("haveEditable",true)}
		<dd input="generic">
		<s:if test="#request.csUnitMt$csumData!=null">${csUnitMt$csumData}</s:if><s:else>
		 	<input title="请输入动态查询" class="combox narrow" generic="csumDataGeneric" size="16" type="text" id="csumData" name="csUnitMt.csumData" text="${csUnitMt.csumData$}" value="${csUnitMt.csumData}"/>
		 	${lz:set("models",get:models("CsUnitOrder"))}
		 	<select id="csumDataGeneric" style="display:none;">
		 		<option value="">--选择模块--</option>
		 		<s:iterator value="#request.models" id="item" status="i">
		 		<option value="${item.name}:${basePath}admin/${item.namespace}_query.do?value={param}">${item.caption}</option>
		 		</s:iterator>
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="lookup('#${NAME}')"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联数据字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csumData">${csUnitMt.csumData}</textarea>
		 		<span>
		 	
			 ${csUnitMt.csumData$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csumData}
	</s:if>
	
	${lz:set("注释","*****************读取时间字段的输入框代码*****************")}
	${lz:set("注释","before$csumReadTime和after$csumReadTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csumReadTime==true">
	${before$csumReadTime}
	<dl class="csumReadTime " major  ref="csumReadTime" >
		<dt>读取时间:</dt>
		<s:if test="#request.CTRL.e.csumReadTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitMt$csumReadTime!=null">${csUnitMt$csumReadTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitMt.csumReadTime" id="csumReadTime"  value="<s:date name="csUnitMt.csumReadTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择推送消息的读取时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****读取时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csumReadTime">${csUnitMt.csumReadTime}</textarea>
		 		<span>
		 	
			 ${csUnitMt.csumReadTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csumReadTime}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csumUpdateTime和after$csumUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csumUpdateTime==true">
	${before$csumUpdateTime}
	<dl class="csumUpdateTime " major  ref="csumUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csumUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitMt$csumUpdateTime!=null">${csUnitMt$csumUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitMt.csumUpdateTime" id="csumUpdateTime"  value="<s:date name="csUnitMt.csumUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择推送消息的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csumUpdateTime">${csUnitMt.csumUpdateTime}</textarea>
		 		<span>
		 	
			 ${csUnitMt.csumUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csumUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csumAddTime和after$csumAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csumAddTime==true">
	${before$csumAddTime}
	<dl class="csumAddTime " major  ref="csumAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csumAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitMt$csumAddTime!=null">${csUnitMt$csumAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitMt.csumAddTime" id="csumAddTime"  value="<s:date name="csUnitMt.csumAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择推送消息的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csumAddTime">${csUnitMt.csumAddTime}</textarea>
		 		<span>
		 	
			 ${csUnitMt.csumAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csumAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csumStatus和after$csumStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csumStatus==true">
	${before$csumStatus}
	<dl class="csumStatus " major  ref="csumStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csumStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csUnitMt$csumStatus!=null">${csUnitMt$csumStatus}</s:if><s:else>
		 	<select class="narrow" id="csumStatus" name="csUnitMt.csumStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csUnitMt.csumStatus==1?"selected":""}>已读</option>
				<option value="0" ${csUnitMt.csumStatus==0?"selected":""}>未读</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择推送消息的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csumStatus">${csUnitMt.csumStatus}</textarea>
		 		<span>
		 	
			 ${csUnitMt.csumStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csumStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csUnitMt.csumId}" name="csUnitMt.csumId" id="csumId" />
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