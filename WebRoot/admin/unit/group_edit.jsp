<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/unit/group.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/unit/group.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitGroup.csugId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csugId,csugHost,csugInfo,csugName",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csugId,csugHost,csugInfo,csugName",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csugId,csugHost,csugInfo,csugName,csugPerson,csugMemo",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csugId,csugHost,csugInfo,csugName,csugPerson,csugMemo",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"分组信息编辑":CTRL.title}</title>
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
	$("#groupForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csugId==true">
		${lz:set("haveEditable",true)}
		,"csUnitGroup.csugId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csugHost==true">
		${lz:set("haveEditable",true)}
		,"csUnitGroup.csugHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csugInfo==true">
		${lz:set("haveEditable",true)}
		,"csUnitGroup.csugInfo":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csugName==true">
		${lz:set("haveEditable",true)}
		,"csUnitGroup.csugName":function(el){
			if(jQuery.trim(el.value)=="")
				return "组名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "组名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csugPerson==true">
		${lz:set("haveEditable",true)}
		,"csUnitGroup.csugPerson":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csugRemark==true">
		${lz:set("haveEditable",true)}
		,"csUnitGroup.csugRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csugMemo==true">
		${lz:set("haveEditable",true)}
		,"csUnitGroup.csugMemo":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "备忘信息最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csugData==true">
		${lz:set("haveEditable",true)}
		,"csUnitGroup.csugData":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "程序数据最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csugUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csUnitGroup.csugUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csugAddTime==true">
		${lz:set("haveEditable",true)}
		,"csUnitGroup.csugAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csugStatus==true">
		${lz:set("haveEditable",true)}
		,"csUnitGroup.csugStatus":function(el){
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
	<form class="form editform" ref="CsUnitGroup" id="groupForm" name="groupForm" action="${empty CTRL.action?"group_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写分组信息相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csUnitGroupToken}" name="csUnitGroupToken" id="csUnitGroupToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csUnitGroup.csugId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csugId和after$csugId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csugId==true">
	${before$csugId}
	<dl class="csugId ${CTRL.e.csugId?"hidden":""}" major  ref="csugId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csugId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csUnitGroup$csugId!=null">${csUnitGroup$csugId}</s:if><s:else>
			<input type="hidden" value="${csUnitGroup.csugId}" name="csUnitGroup.csugId" id="csugId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csugId">${csUnitGroup.csugId}</textarea>
		 		<span>
		 	
			 ${csUnitGroup.csugId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csugId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csugHost和after$csugHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csugHost==true">
	${before$csugHost}
	<dl class="csugHost " major  ref="csugHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csugHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csUnitGroup$csugHost!=null">${csUnitGroup$csugHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csugHost" name="csUnitGroup.csugHost">
		 				<option selected value="${csUnitGroup.csugHost}">
		 					${get:SrvHost(csUnitGroup.csugHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csugHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csugHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csugHost">${csUnitGroup.csugHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csUnitGroup.csugHost}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitGroup.csugHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csugHost}
	</s:if>
	
	${lz:set("注释","*****************所属单位字段的输入框代码*****************")}
	${lz:set("注释","before$csugInfo和after$csugInfo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csugInfo==true">
	${before$csugInfo}
	<dl class="csugInfo " major  ref="csugInfo" >
		<dt>所属单位:</dt>
		<s:if test="#request.CTRL.e.csugInfo==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csUnitGroup$csugInfo!=null">${csUnitGroup$csugInfo}</s:if><s:else>
		 			<input title="请输入企业信息名称进行查询" class="combox narrow" action="${basePath}${proname}/unit/info_query.do?value={param}&csuiHost={csugHost}" type="text" id="csugInfo" name="csUnitGroup.csugInfo" text="${get:CsUnitInfo(csUnitGroup.csugInfo).csuiName}" value="${csUnitGroup.csugInfo}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csugInfo').val())==''){return;};window.href('${basePath}${proname}/unit/info_details.do?key='+$('#csugInfo').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属单位字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csugInfo">${csUnitGroup.csugInfo}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/unit/info_details.do?key=${csUnitGroup.csugInfo}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitGroup.csugInfo$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csugInfo}
	</s:if>
	
	${lz:set("注释","*****************组名称字段的输入框代码*****************")}
	${lz:set("注释","before$csugName和after$csugName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csugName==true">
	${before$csugName}
	<dl class="csugName " major  ref="csugName" >
		<dt>组&nbsp;&nbsp;名&nbsp;&nbsp;称:</dt>
		<s:if test="#request.CTRL.e.csugName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUnitGroup$csugName!=null">${csUnitGroup$csugName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUnitGroup.csugName" id="csugName"  value="${csUnitGroup.csugName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入分组信息的组名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****组名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csugName">${csUnitGroup.csugName}</textarea>
		 		<span>
		 	
			 ${csUnitGroup.csugName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csugName}
	</s:if>
	
	${lz:set("注释","*****************审核人员字段的输入框代码*****************")}
	${lz:set("注释","before$csugPerson和after$csugPerson变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csugPerson==true">
	${before$csugPerson}
	<dl class="csugPerson " major  ref="csugPerson" >
		<dt>审核人员:</dt>
		<s:if test="#request.CTRL.e.csugPerson==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csUnitGroup$csugPerson!=null">${csUnitGroup$csugPerson}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/unit/person_query.do?size=-1&csupGroup={csugId}&csupHost={csugHost}" id="csugPerson" name="csUnitGroup.csugPerson">
		 				<option selected value="${csUnitGroup.csugPerson}">
		 					${get:CsUnitPerson(csUnitGroup.csugPerson).csupName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csugPerson').val())==''){return;};window.href('${basePath}${proname}/unit/person_details.do?key='+$('#csugPerson').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****审核人员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csugPerson">${csUnitGroup.csugPerson}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/unit/person_details.do?key=${csUnitGroup.csugPerson}',{ctrl:{editable:false,visible:true}})">
			 ${csUnitGroup.csugPerson$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csugPerson}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$csugRemark和after$csugRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csugRemark==true">
	${before$csugRemark}
	<dl class="csugRemark " major  ref="csugRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.csugRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csUnitGroup$csugRemark!=null">${csUnitGroup$csugRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csugRemark" name="csUnitGroup.csugRemark" rows="5">${csUnitGroup.csugRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入分组信息的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csUnitGroup.csugRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csugRemark}
	</s:if>
	
	${lz:set("注释","*****************备忘信息字段的输入框代码*****************")}
	${lz:set("注释","before$csugMemo和after$csugMemo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csugMemo==true">
	${before$csugMemo}
	<dl class="csugMemo " minor  ref="csugMemo" style="width:98%;">
		<dt>备忘信息:</dt>
		<s:if test="#request.CTRL.e.csugMemo==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csUnitGroup$csugMemo!=null">${csUnitGroup$csugMemo}</s:if><s:else>
		 	<textarea class="input wide"  id="csugMemo" name="csUnitGroup.csugMemo" rows="5">${csUnitGroup.csugMemo}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入分组信息的备忘信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备忘信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csUnitGroup.csugMemo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csugMemo}
	</s:if>
	
	${lz:set("注释","*****************程序数据字段的输入框代码*****************")}
	${lz:set("注释","before$csugData和after$csugData变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csugData==true">
	${before$csugData}
	<dl class="csugData " minor  ref="csugData" style="width:98%;">
		<dt>程序数据:</dt>
		<s:if test="#request.CTRL.e.csugData==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csUnitGroup$csugData!=null">${csUnitGroup$csugData}</s:if><s:else>
		 	<textarea class="input wide"  id="csugData" name="csUnitGroup.csugData" rows="5">${csUnitGroup.csugData}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入分组信息的程序数据</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****程序数据字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csUnitGroup.csugData$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csugData}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csugUpdateTime和after$csugUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csugUpdateTime==true">
	${before$csugUpdateTime}
	<dl class="csugUpdateTime " major  ref="csugUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csugUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitGroup$csugUpdateTime!=null">${csUnitGroup$csugUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitGroup.csugUpdateTime" id="csugUpdateTime"  value="<s:date name="csUnitGroup.csugUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择分组信息的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csugUpdateTime">${csUnitGroup.csugUpdateTime}</textarea>
		 		<span>
		 	
			 ${csUnitGroup.csugUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csugUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csugAddTime和after$csugAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csugAddTime==true">
	${before$csugAddTime}
	<dl class="csugAddTime " major  ref="csugAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csugAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUnitGroup$csugAddTime!=null">${csUnitGroup$csugAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUnitGroup.csugAddTime" id="csugAddTime"  value="<s:date name="csUnitGroup.csugAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择分组信息的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csugAddTime">${csUnitGroup.csugAddTime}</textarea>
		 		<span>
		 	
			 ${csUnitGroup.csugAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csugAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csugStatus和after$csugStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csugStatus==true">
	${before$csugStatus}
	<dl class="csugStatus " major  ref="csugStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csugStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csUnitGroup$csugStatus!=null">${csUnitGroup$csugStatus}</s:if><s:else>
		 	<select class="narrow" id="csugStatus" name="csUnitGroup.csugStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csUnitGroup.csugStatus==1?"selected":""}>正常</option>
				<option value="0" ${csUnitGroup.csugStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择分组信息的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csugStatus">${csUnitGroup.csugStatus}</textarea>
		 		<span>
		 	
			 ${csUnitGroup.csugStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csugStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csUnitGroup.csugId}" name="csUnitGroup.csugId" id="csugId" />
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