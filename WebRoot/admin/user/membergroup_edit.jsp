<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/user/membergroup.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/user/membergroup.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csMemberGroup.csmgId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csmgId,csmgHost,csmgName,csmgPerson,csmgNumber,csmgFlag,csmgRebate,csmgRemark",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csmgId,csmgHost,csmgName,csmgPerson,csmgNumber,csmgFlag,csmgRebate,csmgRemark",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csmgId,csmgHost,csmgName,csmgPerson,csmgNumber,csmgFlag,csmgRebate,csmgRemark",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csmgId,csmgHost,csmgName,csmgPerson,csmgNumber,csmgFlag,csmgRebate,csmgRemark",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"会员组织编辑":CTRL.title}</title>
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
	$("#membergroupForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csmgId==true">
		${lz:set("haveEditable",true)}
		,"csMemberGroup.csmgId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmgHost==true">
		${lz:set("haveEditable",true)}
		,"csMemberGroup.csmgHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmgName==true">
		${lz:set("haveEditable",true)}
		,"csMemberGroup.csmgName":function(el){
			if(jQuery.trim(el.value)=="")
				return "组织名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "组织名称最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("membergroup_query.do",{exists:true,csmgName:el.value});
				if(exists && exists.length>0){
					if(${csMemberGroup.csmgId==null} || exists[0].value!=$("#csmgId").val())
						return "组织名称已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmgPerson==true">
		${lz:set("haveEditable",true)}
		,"csMemberGroup.csmgPerson":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "负责人最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmgNumber==true">
		${lz:set("haveEditable",true)}
		,"csMemberGroup.csmgNumber":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "联系电话最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmgFlag==true">
		${lz:set("haveEditable",true)}
		,"csMemberGroup.csmgFlag":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "组织标识最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmgRebate==true">
		${lz:set("haveEditable",true)}
		,"csMemberGroup.csmgRebate":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "组织折扣输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmgRemark==true">
		${lz:set("haveEditable",true)}
		,"csMemberGroup.csmgRemark":function(el){
			if(el.value.length>512 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为512个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmgAddTime==true">
		${lz:set("haveEditable",true)}
		,"csMemberGroup.csmgAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmgStatus==true">
		${lz:set("haveEditable",true)}
		,"csMemberGroup.csmgStatus":function(el){
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
	<form class="form editform" ref="CsMemberGroup" id="membergroupForm" name="membergroupForm" action="${empty CTRL.action?"membergroup_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写会员组织相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csMemberGroupToken}" name="csMemberGroupToken" id="csMemberGroupToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csMemberGroup.csmgId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csmgId和after$csmgId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmgId==true">
	${before$csmgId}
	<dl class="csmgId ${CTRL.e.csmgId?"hidden":""}" major  ref="csmgId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csmgId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csMemberGroup$csmgId!=null">${csMemberGroup$csmgId}</s:if><s:else>
			<input type="hidden" value="${csMemberGroup.csmgId}" name="csMemberGroup.csmgId" id="csmgId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmgId">${csMemberGroup.csmgId}</textarea>
		 		<span>
		 	
			 ${csMemberGroup.csmgId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmgId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csmgHost和after$csmgHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmgHost==true">
	${before$csmgHost}
	<dl class="csmgHost " major  ref="csmgHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csmgHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csMemberGroup$csmgHost!=null">${csMemberGroup$csmgHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csmgHost" name="csMemberGroup.csmgHost">
		 				<option selected value="${csMemberGroup.csmgHost}">
		 					${get:SrvHost(csMemberGroup.csmgHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmgHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csmgHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmgHost">${csMemberGroup.csmgHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csMemberGroup.csmgHost}',{ctrl:{editable:false,visible:true}})">
			 ${csMemberGroup.csmgHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmgHost}
	</s:if>
	
	${lz:set("注释","*****************组织名称字段的输入框代码*****************")}
	${lz:set("注释","before$csmgName和after$csmgName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmgName==true">
	${before$csmgName}
	<dl class="csmgName " major  ref="csmgName" >
		<dt>组织名称:</dt>
		<s:if test="#request.CTRL.e.csmgName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMemberGroup$csmgName!=null">${csMemberGroup$csmgName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMemberGroup.csmgName" id="csmgName"  value="${csMemberGroup.csmgName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入会员组织的组织名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****组织名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmgName">${csMemberGroup.csmgName}</textarea>
		 		<span>
		 	
			 ${csMemberGroup.csmgName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmgName}
	</s:if>
	
	${lz:set("注释","*****************负责人字段的输入框代码*****************")}
	${lz:set("注释","before$csmgPerson和after$csmgPerson变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmgPerson==true">
	${before$csmgPerson}
	<dl class="csmgPerson " major  ref="csmgPerson" >
		<dt>负&nbsp;&nbsp;责&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csmgPerson==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMemberGroup$csmgPerson!=null">${csMemberGroup$csmgPerson}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMemberGroup.csmgPerson" id="csmgPerson"  value="${csMemberGroup.csmgPerson}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员组织的负责人</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****负责人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmgPerson">${csMemberGroup.csmgPerson}</textarea>
		 		<span>
		 	
			 ${csMemberGroup.csmgPerson$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmgPerson}
	</s:if>
	
	${lz:set("注释","*****************联系电话字段的输入框代码*****************")}
	${lz:set("注释","before$csmgNumber和after$csmgNumber变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmgNumber==true">
	${before$csmgNumber}
	<dl class="csmgNumber " major  ref="csmgNumber" >
		<dt>联系电话:</dt>
		<s:if test="#request.CTRL.e.csmgNumber==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMemberGroup$csmgNumber!=null">${csMemberGroup$csmgNumber}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMemberGroup.csmgNumber" id="csmgNumber"  value="${csMemberGroup.csmgNumber}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员组织的联系电话</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****联系电话字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmgNumber">${csMemberGroup.csmgNumber}</textarea>
		 		<span>
		 	
			 ${csMemberGroup.csmgNumber$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmgNumber}
	</s:if>
	
	${lz:set("注释","*****************组织标识字段的输入框代码*****************")}
	${lz:set("注释","before$csmgFlag和after$csmgFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmgFlag==true">
	${before$csmgFlag}
	<dl class="csmgFlag " minor  ref="csmgFlag" >
		<dt>组织标识:</dt>
		<s:if test="#request.CTRL.e.csmgFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMemberGroup$csmgFlag!=null">${csMemberGroup$csmgFlag}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMemberGroup.csmgFlag" id="csmgFlag"  value="${csMemberGroup.csmgFlag}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员组织的组织标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****组织标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmgFlag">${csMemberGroup.csmgFlag}</textarea>
		 		<span>
		 	
			 ${csMemberGroup.csmgFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmgFlag}
	</s:if>
	
	${lz:set("注释","*****************组织折扣字段的输入框代码*****************")}
	${lz:set("注释","before$csmgRebate和after$csmgRebate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmgRebate==true">
	${before$csmgRebate}
	<dl class="csmgRebate " major  ref="csmgRebate" >
		<dt>组织折扣:</dt>
		<s:if test="#request.CTRL.e.csmgRebate==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csMemberGroup$csmgRebate!=null">${csMemberGroup$csmgRebate}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csMemberGroup.csmgRebate" id="csmgRebate"  value="${csMemberGroup.csmgRebate}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>0到1之间的小数，其它无效</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****组织折扣字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmgRebate">${csMemberGroup.csmgRebate}</textarea>
		 		<span>
		 	
			 ${csMemberGroup.csmgRebate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmgRebate}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$csmgRemark和after$csmgRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmgRemark==true">
	${before$csmgRemark}
	<dl class="csmgRemark " minor  ref="csmgRemark" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.csmgRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csMemberGroup$csmgRemark!=null">${csMemberGroup$csmgRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csmgRemark" name="csMemberGroup.csmgRemark" rows="5">${csMemberGroup.csmgRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员组织的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csMemberGroup.csmgRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmgRemark}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csmgAddTime和after$csmgAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmgAddTime==true">
	${before$csmgAddTime}
	<dl class="csmgAddTime " major  ref="csmgAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csmgAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csMemberGroup$csmgAddTime!=null">${csMemberGroup$csmgAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csMemberGroup.csmgAddTime" id="csmgAddTime"  value="<s:date name="csMemberGroup.csmgAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员组织的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmgAddTime">${csMemberGroup.csmgAddTime}</textarea>
		 		<span>
		 	
			 ${csMemberGroup.csmgAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmgAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csmgStatus和after$csmgStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmgStatus==true">
	${before$csmgStatus}
	<dl class="csmgStatus " major  ref="csmgStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csmgStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMemberGroup$csmgStatus!=null">${csMemberGroup$csmgStatus}</s:if><s:else>
		 	<select class="narrow" id="csmgStatus" name="csMemberGroup.csmgStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csMemberGroup.csmgStatus==1?"selected":""}>正常</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员组织的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmgStatus">${csMemberGroup.csmgStatus}</textarea>
		 		<span>
		 	
			 ${csMemberGroup.csmgStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmgStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csMemberGroup.csmgId}" name="csMemberGroup.csmgId" id="csmgId" />
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