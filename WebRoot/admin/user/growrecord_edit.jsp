<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/user/growrecord.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/user/growrecord.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csGrowRecord.csgrId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csgrId,csgrHost,csgrMember,csgrDesc,csgrType,csgrAmount,csgrRelate",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csgrId,csgrHost,csgrMember,csgrDesc,csgrType,csgrAmount,csgrRelate",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csgrId,csgrHost,csgrMember,csgrDesc,csgrType,csgrAmount,csgrRelate,csgrStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csgrId,csgrHost,csgrMember,csgrDesc,csgrType,csgrAmount,csgrRelate,csgrStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"成长记录编辑":CTRL.title}</title>
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
<s:if test="#request.edittype=='save'">
<lz:set name="before$csgrType">
	<dl class="" major  ref="csgrType" >
		<dt>成长值类型:</dt>
		<dd>
		 	<select class="combox narrow" action="${basePath}${proname}/configurator/growtype_query.do?size=-1" id="growType" name="growType">
 				<option selected value=""></option>
 			</select>
 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#growType').val())==''){return;};window.href('${basePath}${proname}/configurator/growtype_details.do?csgtIsArtif=true&key='+$('#growType').val(),{ctrl:{editable:false}})"></a>
			<a class="reladd" config="{
			 				action:'${basePath}${proname}/configurator/growtype_edit.do?rd=1'
			 				,callback:function(object){
			 					$('#growType').setValue(object.csgtId,object.csgtName);			 					
			 				}
			 			}" href="javascript:void(0);"></a>
		</dd>
	</dl>
</lz:set>
</s:if>
<script type="text/javascript">
<!--
$(function(){
	$("#csgrType,#csgrAmount").attr("readonly",true).click(function(){
		$('#growType').say("请选择成长值类型");
	})
	$('#growType').change(function(){
		if($(this).val()!=""){
			var csGrowType = CsGrowType.get($(this).val());
			$("#csgrType").val(csGrowType.csgtName);
			$("#csgrAmount").val(csGrowType.csgtValue);
		}
	});
});
//-->
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
	$("#growrecordForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csgrId==true">
		${lz:set("haveEditable",true)}
		,"csGrowRecord.csgrId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgrHost==true">
		${lz:set("haveEditable",true)}
		,"csGrowRecord.csgrHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgrMember==true">
		${lz:set("haveEditable",true)}
		,"csGrowRecord.csgrMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgrDesc==true">
		${lz:set("haveEditable",true)}
		,"csGrowRecord.csgrDesc":function(el){
			if(jQuery.trim(el.value)=="")
				return "描述不能为空";
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "描述最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgrType==true">
		${lz:set("haveEditable",true)}
		,"csGrowRecord.csgrType":function(el){
			if(jQuery.trim(el.value)=="")
				return "类型不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "类型最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgrAmount==true">
		${lz:set("haveEditable",true)}
		,"csGrowRecord.csgrAmount":function(el){
			if(jQuery.trim(el.value)=="")
				return "变化值不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgrRelate==true">
		${lz:set("haveEditable",true)}
		,"csGrowRecord.csgrRelate":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgrUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csGrowRecord.csgrUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgrAddTime==true">
		${lz:set("haveEditable",true)}
		,"csGrowRecord.csgrAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgrStatus==true">
		${lz:set("haveEditable",true)}
		,"csGrowRecord.csgrStatus":function(el){
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
	<form class="form editform" ref="CsGrowRecord" id="growrecordForm" name="growrecordForm" action="${empty CTRL.action?"growrecord_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写成长记录相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csGrowRecordToken}" name="csGrowRecordToken" id="csGrowRecordToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csGrowRecord.csgrId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csgrId和after$csgrId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgrId==true">
	${before$csgrId}
	<dl class="csgrId ${CTRL.e.csgrId?"hidden":""}" major  ref="csgrId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csgrId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csGrowRecord$csgrId!=null">${csGrowRecord$csgrId}</s:if><s:else>
			<input type="hidden" value="${csGrowRecord.csgrId}" name="csGrowRecord.csgrId" id="csgrId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgrId">${csGrowRecord.csgrId}</textarea>
		 		<span>
		 	
			 ${csGrowRecord.csgrId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgrId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csgrHost和after$csgrHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgrHost==true">
	${before$csgrHost}
	<dl class="csgrHost " major  ref="csgrHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csgrHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csGrowRecord$csgrHost!=null">${csGrowRecord$csgrHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csgrHost" name="csGrowRecord.csgrHost">
		 				<option selected value="${csGrowRecord.csgrHost}">
		 					${get:SrvHost(csGrowRecord.csgrHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csgrHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csgrHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgrHost">${csGrowRecord.csgrHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csGrowRecord.csgrHost}',{ctrl:{editable:false,visible:true}})">
			 ${csGrowRecord.csgrHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgrHost}
	</s:if>
	
	${lz:set("注释","*****************会员字段的输入框代码*****************")}
	${lz:set("注释","before$csgrMember和after$csgrMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgrMember==true">
	${before$csgrMember}
	<dl class="csgrMember " major  ref="csgrMember" >
		<dt>会　　员:</dt>
		<s:if test="#request.CTRL.e.csgrMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csGrowRecord$csgrMember!=null">${csGrowRecord$csgrMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={csgrHost}" type="text" id="csgrMember" name="csGrowRecord.csgrMember" text="${get:CsMember(csGrowRecord.csgrMember).csmName}" value="${csGrowRecord.csgrMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csgrMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csgrMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgrMember">${csGrowRecord.csgrMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csGrowRecord.csgrMember}',{ctrl:{editable:false,visible:true}})">
			 ${csGrowRecord.csgrMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgrMember}
	</s:if>
	
	${lz:set("注释","*****************描述字段的输入框代码*****************")}
	${lz:set("注释","before$csgrDesc和after$csgrDesc变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgrDesc==true">
	${before$csgrDesc}
	<dl class="csgrDesc " major  ref="csgrDesc" style="width:98%;">
		<dt>描　　述:</dt>
		<s:if test="#request.CTRL.e.csgrDesc==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csGrowRecord$csgrDesc!=null">${csGrowRecord$csgrDesc}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="256" name="csGrowRecord.csgrDesc" id="csgrDesc"  value="${csGrowRecord.csgrDesc}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入成长记录的描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csgrDesc">${csGrowRecord.csgrDesc}</textarea>
		 		<span>
		 	
			 ${csGrowRecord.csgrDesc$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgrDesc}
	</s:if>
	
	${lz:set("注释","*****************类型字段的输入框代码*****************")}
	${lz:set("注释","before$csgrType和after$csgrType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgrType==true">
	${before$csgrType}
	<dl class="csgrType " major  ref="csgrType" >
		<dt>类　　型:</dt>
		<s:if test="#request.CTRL.e.csgrType==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csGrowRecord$csgrType!=null">${csGrowRecord$csgrType}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csGrowRecord.csgrType" id="csgrType"  value="${csGrowRecord.csgrType}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入成长记录的类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgrType">${csGrowRecord.csgrType}</textarea>
		 		<span>
		 	
			 ${csGrowRecord.csgrType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgrType}
	</s:if>
	
	${lz:set("注释","*****************变化值字段的输入框代码*****************")}
	${lz:set("注释","before$csgrAmount和after$csgrAmount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgrAmount==true">
	${before$csgrAmount}
	<dl class="csgrAmount " major  ref="csgrAmount" >
		<dt>变&nbsp;&nbsp;化&nbsp;&nbsp;值:</dt>
		<s:if test="#request.CTRL.e.csgrAmount==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csGrowRecord$csgrAmount!=null">${csGrowRecord$csgrAmount}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csGrowRecord.csgrAmount" id="csgrAmount"  value="${csGrowRecord.csgrAmount}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入成长记录的变化值</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****变化值字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgrAmount">${csGrowRecord.csgrAmount}</textarea>
		 		<span>
		 	
			 ${csGrowRecord.csgrAmount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgrAmount}
	</s:if>
	
	${lz:set("注释","*****************关联数据字段的输入框代码*****************")}
	${lz:set("注释","before$csgrRelate和after$csgrRelate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgrRelate==true">
	${before$csgrRelate}
	<dl class="csgrRelate " major  ref="csgrRelate" >
		<dt>关联数据:</dt>
		<s:if test="#request.CTRL.e.csgrRelate==true">
		${lz:set("haveEditable",true)}
		<dd input="generic">
		<s:if test="#request.csGrowRecord$csgrRelate!=null">${csGrowRecord$csgrRelate}</s:if><s:else>
		 	<input title="请输入动态查询" class="combox narrow" generic="csgrRelateGeneric" size="16" type="text" id="csgrRelate" name="csGrowRecord.csgrRelate" text="${csGrowRecord.csgrRelate$}" value="${csGrowRecord.csgrRelate}"/>
		 	${lz:set("models",get:models("CsTrouble,CsViolat,CsOrder"))}
		 	<select id="csgrRelateGeneric" style="display:none;">
		 		<option value="">--选择模块--</option>
		 		<s:iterator value="#request.models" id="item" status="i">
		 		<option value="${item.name}:${basePath}admin/${item.namespace}_query.do?value={param}">${item.caption}</option>
		 		</s:iterator>
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="lookup('#${NAME}')"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em>数据格式：对象类名@对象ID</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联数据字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgrRelate">${csGrowRecord.csgrRelate}</textarea>
		 		<span>
		 	
			 ${csGrowRecord.csgrRelate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgrRelate}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csgrUpdateTime和after$csgrUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgrUpdateTime==true">
	${before$csgrUpdateTime}
	<dl class="csgrUpdateTime " major  ref="csgrUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csgrUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csGrowRecord$csgrUpdateTime!=null">${csGrowRecord$csgrUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csGrowRecord.csgrUpdateTime" id="csgrUpdateTime"  value="<s:date name="csGrowRecord.csgrUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择成长记录的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgrUpdateTime">${csGrowRecord.csgrUpdateTime}</textarea>
		 		<span>
		 	
			 ${csGrowRecord.csgrUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgrUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csgrAddTime和after$csgrAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgrAddTime==true">
	${before$csgrAddTime}
	<dl class="csgrAddTime " major  ref="csgrAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csgrAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csGrowRecord$csgrAddTime!=null">${csGrowRecord$csgrAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csGrowRecord.csgrAddTime" id="csgrAddTime"  value="<s:date name="csGrowRecord.csgrAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择成长记录的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgrAddTime">${csGrowRecord.csgrAddTime}</textarea>
		 		<span>
		 	
			 ${csGrowRecord.csgrAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgrAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csgrStatus和after$csgrStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgrStatus==true">
	${before$csgrStatus}
	<dl class="csgrStatus " major  ref="csgrStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csgrStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csGrowRecord$csgrStatus!=null">${csGrowRecord$csgrStatus}</s:if><s:else>
		 	<select class="narrow" id="csgrStatus" name="csGrowRecord.csgrStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csGrowRecord.csgrStatus==1?"selected":""}>正常</option>
				<option value="0" ${csGrowRecord.csgrStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择成长记录的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgrStatus">${csGrowRecord.csgrStatus}</textarea>
		 		<span>
		 	
			 ${csGrowRecord.csgrStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgrStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csGrowRecord.csgrId}" name="csGrowRecord.csgrId" id="csgrId" />
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