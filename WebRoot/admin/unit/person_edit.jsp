<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/unit/person.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/unit/person.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitPerson.csupId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csupId,csupHost,csupInfo,csupName,csupGroup,csupMember",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csupId,csupHost,csupInfo,csupName,csupGroup,csupMember",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csupId,csupHost,csupInfo,csupName,csupGroup,csupMember,csupMemo,csupStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csupId,csupHost,csupInfo,csupName,csupGroup,csupMember,csupMemo,csupStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"用车人员编辑":CTRL.title}</title>
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
	$("#personForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csupId==true">
		${lz:set("haveEditable",true)}
		,"csUnitPerson.csupId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csupHost==true">
		${lz:set("haveEditable",true)}
		,"csUnitPerson.csupHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csupInfo==true">
		${lz:set("haveEditable",true)}
		,"csUnitPerson.csupInfo":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csupName==true">
		${lz:set("haveEditable",true)}
		,"csUnitPerson.csupName":function(el){
			if(jQuery.trim(el.value)=="")
				return "姓名不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "姓名最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csupGroup==true">
		${lz:set("haveEditable",true)}
		,"csUnitPerson.csupGroup":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csupMember==true">
		${lz:set("haveEditable",true)}
		,"csUnitPerson.csupMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csupRemark==true">
		${lz:set("haveEditable",true)}
		,"csUnitPerson.csupRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csupMemo==true">
		${lz:set("haveEditable",true)}
		,"csUnitPerson.csupMemo":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "备忘信息最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csupUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csUnitPerson.csupUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csupAddTime==true">
		${lz:set("haveEditable",true)}
		,"csUnitPerson.csupAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csupStatus==true">
		${lz:set("haveEditable",true)}
		,"csUnitPerson.csupStatus":function(el){
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
	<form class="form editform" ref="CsUnitPerson" id="personForm" name="personForm" action="${empty CTRL.action?"person_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写用车人员相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csUnitPersonToken}" name="csUnitPersonToken" id="csUnitPersonToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitPerson.csupId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csupId和after$csupId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csupId==true">
	${before$csupId}
	<dl class="csupId ${CTRL.e.csupId?"hidden":""}" major  ref="csupId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csupId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csUnitPerson$csupId!=null">${csUnitPerson$csupId}</s:if><s:else>
			<input type="hidden" value="${csUnitPerson.csupId}" name="csUnitPerson.csupId" id="csupId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csupId">${csUnitPerson.csupId}</textarea>
		 		<span>
		 	
			 ${csUnitPerson.csupId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csupId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csupHost和after$csupHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csupHost==true">
	${before$csupHost}
	<dl class="csupHost " major  ref="csupHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csupHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csUnitPerson$csupHost!=null">${csUnitPerson$csupHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csupHost" name="csUnitPerson.csupHost">
		 				<option selected value="${csUnitPerson.csupHost}">
		 					${get:SrvHost(csUnitPerson.csupHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csupHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csupHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csupHost">${csUnitPerson.csupHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csUnitPerson.csupHost}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitPerson.csupHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csupHost}
	</s:if>
	
	${lz:set("注释","*****************所属单位字段的输入框代码*****************")}
	${lz:set("注释","before$csupInfo和after$csupInfo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csupInfo==true">
	${before$csupInfo}
	<dl class="csupInfo " major  ref="csupInfo" >
		<dt>所属单位:</dt>
		<s:if test="#request.CTRL.e.csupInfo==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitPerson$csupInfo!=null">${csUnitPerson$csupInfo}</s:if><s:else>
		 			<input title="请输入企业信息名称进行查询" class="combox narrow" action="${basePath}${proname}/unit/info_query.do?value={param}&csuiHost={csupHost}" type="text" id="csupInfo" name="csUnitPerson.csupInfo" text="${get:CsUnitInfo(csUnitPerson.csupInfo).csuiName}" value="${csUnitPerson.csupInfo}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csupInfo').val())==''){return;};window.href('${basePath}${proname}/unit/info_details.do?key='+$('#csupInfo').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属单位字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csupInfo">${csUnitPerson.csupInfo}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/unit/info_details.do?key=${csUnitPerson.csupInfo}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitPerson.csupInfo$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csupInfo}
	</s:if>
	
	${lz:set("注释","*****************姓名字段的输入框代码*****************")}
	${lz:set("注释","before$csupName和after$csupName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csupName==true">
	${before$csupName}
	<dl class="csupName " major  ref="csupName" >
		<dt>姓　　名:</dt>
		<s:if test="#request.CTRL.e.csupName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitPerson$csupName!=null">${csUnitPerson$csupName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitPerson.csupName" id="csupName"  value="${csUnitPerson.csupName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入用车人员的姓名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****姓名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csupName">${csUnitPerson.csupName}</textarea>
		 		<span>
		 	
			 ${csUnitPerson.csupName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csupName}
	</s:if>
	
	${lz:set("注释","*****************所属分组字段的输入框代码*****************")}
	${lz:set("注释","before$csupGroup和after$csupGroup变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csupGroup==true">
	${before$csupGroup}
	<dl class="csupGroup " major  ref="csupGroup" >
		<dt>所属分组:</dt>
		<s:if test="#request.CTRL.e.csupGroup==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitPerson$csupGroup!=null">${csUnitPerson$csupGroup}</s:if><s:else>
		 			<input title="请输入分组信息组名称进行查询" class="combox narrow" action="${basePath}${proname}/unit/group_query.do?value={param}&csugHost={csupHost}&csugInfo={csupInfo}" type="text" id="csupGroup" name="csUnitPerson.csupGroup" text="${get:CsUnitGroup(csUnitPerson.csupGroup).csugName}" value="${csUnitPerson.csupGroup}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csupGroup').val())==''){return;};window.href('${basePath}${proname}/unit/group_details.do?key='+$('#csupGroup').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属分组字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csupGroup">${csUnitPerson.csupGroup}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/unit/group_details.do?key=${csUnitPerson.csupGroup}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitPerson.csupGroup$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csupGroup}
	</s:if>
	
	${lz:set("注释","*****************帐号信息字段的输入框代码*****************")}
	${lz:set("注释","before$csupMember和after$csupMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csupMember==true">
	${before$csupMember}
	<dl class="csupMember " major  ref="csupMember" >
		<dt>帐号信息:</dt>
		<s:if test="#request.CTRL.e.csupMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitPerson$csupMember!=null">${csUnitPerson$csupMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={csupHost}" type="text" id="csupMember" name="csUnitPerson.csupMember" text="${get:CsMember(csUnitPerson.csupMember).csmName}" value="${csUnitPerson.csupMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csupMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csupMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****帐号信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csupMember">${csUnitPerson.csupMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csUnitPerson.csupMember}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitPerson.csupMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csupMember}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$csupRemark和after$csupRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csupRemark==true">
	${before$csupRemark}
	<dl class="csupRemark " minor  ref="csupRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.csupRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csUnitPerson$csupRemark!=null">${csUnitPerson$csupRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csupRemark" name="csUnitPerson.csupRemark" rows="5">${csUnitPerson.csupRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入用车人员的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csUnitPerson.csupRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csupRemark}
	</s:if>
	
	${lz:set("注释","*****************备忘信息字段的输入框代码*****************")}
	${lz:set("注释","before$csupMemo和after$csupMemo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csupMemo==true">
	${before$csupMemo}
	<dl class="csupMemo " minor  ref="csupMemo" style="width:98%;">
		<dt>备忘信息:</dt>
		<s:if test="#request.CTRL.e.csupMemo==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csUnitPerson$csupMemo!=null">${csUnitPerson$csupMemo}</s:if><s:else>
		 	<textarea class="input wide"  id="csupMemo" name="csUnitPerson.csupMemo" rows="5">${csUnitPerson.csupMemo}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入用车人员的备忘信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备忘信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csUnitPerson.csupMemo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csupMemo}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csupUpdateTime和after$csupUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csupUpdateTime==true">
	${before$csupUpdateTime}
	<dl class="csupUpdateTime " major  ref="csupUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csupUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitPerson$csupUpdateTime!=null">${csUnitPerson$csupUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitPerson.csupUpdateTime" id="csupUpdateTime"  value="<s:date name="csUnitPerson.csupUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择用车人员的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csupUpdateTime">${csUnitPerson.csupUpdateTime}</textarea>
		 		<span>
		 	
			 ${csUnitPerson.csupUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csupUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csupAddTime和after$csupAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csupAddTime==true">
	${before$csupAddTime}
	<dl class="csupAddTime " major  ref="csupAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csupAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitPerson$csupAddTime!=null">${csUnitPerson$csupAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitPerson.csupAddTime" id="csupAddTime"  value="<s:date name="csUnitPerson.csupAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择用车人员的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csupAddTime">${csUnitPerson.csupAddTime}</textarea>
		 		<span>
		 	
			 ${csUnitPerson.csupAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csupAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csupStatus和after$csupStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csupStatus==true">
	${before$csupStatus}
	<dl class="csupStatus " major  ref="csupStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csupStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csUnitPerson$csupStatus!=null">${csUnitPerson$csupStatus}</s:if><s:else>
		 	<select class="narrow" id="csupStatus" name="csUnitPerson.csupStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csUnitPerson.csupStatus==1?"selected":""}>正常</option>
				<option value="0" ${csUnitPerson.csupStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择用车人员的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csupStatus">${csUnitPerson.csupStatus}</textarea>
		 		<span>
		 	
			 ${csUnitPerson.csupStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csupStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csUnitPerson.csupId}" name="csUnitPerson.csupId" id="csupId" />
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