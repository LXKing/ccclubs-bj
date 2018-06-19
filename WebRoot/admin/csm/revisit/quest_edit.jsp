<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/csm/revisit/quest.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/csm/revisit/quest.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csQuestTemplate.csqtId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csqtId,csqtName,csqtType,csqtContent,csqtFlag,csqtStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csqtId,csqtName,csqtType,csqtContent,csqtFlag,csqtStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csqtId,csqtName,csqtType,csqtContent,csqtFlag,csqtStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csqtId,csqtName,csqtType,csqtContent,csqtFlag,csqtStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"问卷模板编辑":CTRL.title}</title>
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
	<script type="text/javascript" src="${basePath}admin/js/jquery-ui-1.10.2.custom.min.js"></script>
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
.template{min-height:300px;}
.template dl{border:1px solid #dddddd;margin:5px;padding:10px;padding-top:15px;width:770px;}
.template .del{position:relative;}
.template label{margin:0px 10px;padding-bottom:10px;display:block;}
.btn{margin:5px;}
.closer{position:absolute;width:15px;height:15px;line-height:15px;cursor:pointer;top:0px;right:-10px;text-align:center;color:red;}
</style>
<script>
//,date:"日期",time:"时间",datetime:"日期时间",money:"小数",number:"整数",text:"单行文本",textarea:"多行文本"
var types={radio:"单选",checkbox:"多选",select:"下拉框"};
$(function(){
	var dl = $("#csqtContent").parents("dl");
	dl.css("min-height","auto");
	$("<div class='line'></div>").insertAfter(dl);
	$("<div class='template'></div>").insertAfter(dl);
	$("<div class='line'></div>").insertAfter(dl);
	dl.find("em").remove();
	///////////////////////添加组件按钮//////////////////////
	for(var o in types)
		$("<button type='button' class='button typebtn' style='padding:0px 2px;'></button>").insertBefore($("#csqtContent")).html("添加"+types[o]).attr("inputType",o);
	dl.find(".typebtn").click(function(){		
		var dialog = top.$.showModalDialog({
			title:"选择问题",
			url:"${basePath}admin/crm/revisit/answer.select.do",
			width:880,
			height:1024
		},{
			handles:"选择此问题（答案）"
		},window);
		var inputType = $(this).attr("inputType");
		window.handles=window.handles||[];
		window.handles["选择此问题（答案）"]=function(operate,id){
			var csQuestAnswer = $.api("com.ccclubs.model.CsQuestAnswer","get")(id);
			var questId = csQuestAnswer.csqaParent!=0 ? csQuestAnswer.csqaParent : csQuestAnswer.csqaId;
			if($("[name="+questId+"]").size()>1){
				alert("该问题在当前模板中已存在");
				return;
			}
			addItem(inputType,questId);			
			dialog.close();
		}
	});
	///////////////////////设置问题可换位置//////////////////////
	$(".template").sortable();
	///////////////////////删除组件//////////////////////
	window.closer = $("<div onclick=\"$(this).parent('.del').remove()\" class='closer'>X</div>");
	window.onClose=function(){
		window.closer.appendTo($(this));
	};
	//////////////////////////////////////////////////
	$("#csqtContent").next("em").css("width","auto");
	dl.find("dt").css("height","auto").css("min-height","0px");
	var value = $("#csqtContent").remove().val();
	var array = value.split(",");
	for(var o in array){
		if($.trim(array[o])!="")
			addItem($.trim(array[o].split(":")[1]),$.trim(array[o].split(":")[0]));
	}
});
function addItem(type,id){
	var csQuestAnswer = $.api("com.ccclubs.model.CsQuestAnswer","get")(id);
	var answers = $.api("com.ccclubs.model.CsQuestAnswer","getCsQuestAnswerList")({
		csqaParent:csQuestAnswer.csqaId
	},-1);
	var dl = $("<dl class='del'><dt>"+csQuestAnswer.csqaTitle+":</dt><dd></dd></dl>").appendTo(".template");
	$("<input type='hidden' id=\"csqtContent\" name=\"csQuestTemplate.csqtContent\" value='"+csQuestAnswer.csqaId+":"+type+"'/>").appendTo(dl);	
	if(type=="radio"||type=="checkbox"){
		for(var o in answers){
			$("<label><input name='"+csQuestAnswer.csqaId+"' value='"+answers[o].csqaId+"' type='"+type+"'/>"+answers[o].csqaTitle+"</label>").prependTo(dl.find("dd"));
		}
	}else if(type=="select"){
		var select = $("<label><select name='"+csQuestAnswer.csqaId+"' class='narrow'></select></label>").prependTo(dl.find("dd")).find("select");
		$("<option>请选择</option>").appendTo(select);
		for(var o in answers){
			$("<option value='"+answers[o].csqaId+"'>"+answers[o].csqaTitle+"</option>").appendTo(select);
		}
	}
	dl.mouseenter(window.onClose);//安装删除组件
}
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
	$("#questForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csqtId==true">
		${lz:set("haveEditable",true)}
		,"csQuestTemplate.csqtId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csqtName==true">
		${lz:set("haveEditable",true)}
		,"csQuestTemplate.csqtName":function(el){
			if(jQuery.trim(el.value)=="")
				return "模板名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "模板名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csqtType==true">
		${lz:set("haveEditable",true)}
		,"csQuestTemplate.csqtType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csqtContent==true">
		${lz:set("haveEditable",true)}
		,"csQuestTemplate.csqtContent":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "模板设计最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csqtFlag==true">
		${lz:set("haveEditable",true)}
		,"csQuestTemplate.csqtFlag":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "标识最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csqtEditor==true">
		${lz:set("haveEditable",true)}
		,"csQuestTemplate.csqtEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csqtUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csQuestTemplate.csqtUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csqtAddTime==true">
		${lz:set("haveEditable",true)}
		,"csQuestTemplate.csqtAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csqtStatus==true">
		${lz:set("haveEditable",true)}
		,"csQuestTemplate.csqtStatus":function(el){
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
	<form class="form editform" ref="CsQuestTemplate" id="questForm" name="questForm" action="${empty CTRL.action?"quest_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写问卷模板相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csQuestTemplateToken}" name="csQuestTemplateToken" id="csQuestTemplateToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csQuestTemplate.csqtId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csqtId和after$csqtId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csqtId==true">
	${before$csqtId}
	<dl class="csqtId ${CTRL.e.csqtId?"hidden":""}" major  ref="csqtId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csqtId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csQuestTemplate$csqtId!=null">${csQuestTemplate$csqtId}</s:if><s:else>
			<input type="hidden" value="${csQuestTemplate.csqtId}" name="csQuestTemplate.csqtId" id="csqtId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csqtId">${csQuestTemplate.csqtId}</textarea>
		 		<span>
		 	
			 ${csQuestTemplate.csqtId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csqtId}
	</s:if>
	
	${lz:set("注释","*****************模板名称字段的输入框代码*****************")}
	${lz:set("注释","before$csqtName和after$csqtName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csqtName==true">
	${before$csqtName}
	<dl class="csqtName " major  ref="csqtName" >
		<dt>模板名称:</dt>
		<s:if test="#request.CTRL.e.csqtName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csQuestTemplate$csqtName!=null">${csQuestTemplate$csqtName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csQuestTemplate.csqtName" id="csqtName"  value="${csQuestTemplate.csqtName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入问卷模板的模板名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****模板名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csqtName">${csQuestTemplate.csqtName}</textarea>
		 		<span>
		 	
			 ${csQuestTemplate.csqtName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csqtName}
	</s:if>
	
	${lz:set("注释","*****************类型字段的输入框代码*****************")}
	${lz:set("注释","before$csqtType和after$csqtType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csqtType==true">
	${before$csqtType}
	<dl class="csqtType " major  ref="csqtType" >
		<dt>类　　型:</dt>
		<s:if test="#request.CTRL.e.csqtType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csQuestTemplate$csqtType!=null">${csQuestTemplate$csqtType}</s:if><s:else>
		 	<select class="narrow" id="csqtType" name="csQuestTemplate.csqtType">
		 		<option value="">请选择</option>
				<option value="0" ${csQuestTemplate.csqtType==0?"selected":""}>调查报告表</option>
				<option value="1" ${csQuestTemplate.csqtType==1?"selected":""}>客户回访表</option>
				<option value="2" ${csQuestTemplate.csqtType==2?"selected":""}>投诉反馈表</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择问卷模板的类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csqtType">${csQuestTemplate.csqtType}</textarea>
		 		<span>
		 	
			 ${csQuestTemplate.csqtType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csqtType}
	</s:if>
	
	${lz:set("注释","*****************模板设计字段的输入框代码*****************")}
	${lz:set("注释","before$csqtContent和after$csqtContent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csqtContent==true">
	${before$csqtContent}
	<dl class="csqtContent " minor  ref="csqtContent" style="width:98%;">
		<dt>模板设计:</dt>
		<s:if test="#request.CTRL.e.csqtContent==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csQuestTemplate$csqtContent!=null">${csQuestTemplate$csqtContent}</s:if><s:else>
		 	<textarea class="input wide"  id="csqtContent" name="csQuestTemplate.csqtContent" rows="5">${csQuestTemplate.csqtContent}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入问卷模板的模板设计</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****模板设计字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csQuestTemplate.csqtContent$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csqtContent}
	</s:if>
	
	${lz:set("注释","*****************标识字段的输入框代码*****************")}
	${lz:set("注释","before$csqtFlag和after$csqtFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csqtFlag==true">
	${before$csqtFlag}
	<dl class="csqtFlag " major  ref="csqtFlag" >
		<dt>标　　识:</dt>
		<s:if test="#request.CTRL.e.csqtFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csQuestTemplate$csqtFlag!=null">${csQuestTemplate$csqtFlag}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csQuestTemplate.csqtFlag" id="csqtFlag"  value="${csQuestTemplate.csqtFlag}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入问卷模板的标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csqtFlag">${csQuestTemplate.csqtFlag}</textarea>
		 		<span>
		 	
			 ${csQuestTemplate.csqtFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csqtFlag}
	</s:if>
	
	${lz:set("注释","*****************修改人字段的输入框代码*****************")}
	${lz:set("注释","before$csqtEditor和after$csqtEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csqtEditor==true">
	${before$csqtEditor}
	<dl class="csqtEditor " major  ref="csqtEditor" >
		<dt>修&nbsp;&nbsp;改&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csqtEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csQuestTemplate$csqtEditor!=null">${csQuestTemplate$csqtEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csqtEditor" name="csQuestTemplate.csqtEditor">
		 				<option selected value="${csQuestTemplate.csqtEditor}">
		 					${get:SrvUser(csQuestTemplate.csqtEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csqtEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csqtEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csqtEditor">${csQuestTemplate.csqtEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csQuestTemplate.csqtEditor}',{ctrl:{editable:false,visible:true}})">
			 ${csQuestTemplate.csqtEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csqtEditor}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csqtUpdateTime和after$csqtUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csqtUpdateTime==true">
	${before$csqtUpdateTime}
	<dl class="csqtUpdateTime " major  ref="csqtUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csqtUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csQuestTemplate$csqtUpdateTime!=null">${csQuestTemplate$csqtUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csQuestTemplate.csqtUpdateTime" id="csqtUpdateTime"  value="<s:date name="csQuestTemplate.csqtUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择问卷模板的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csqtUpdateTime">${csQuestTemplate.csqtUpdateTime}</textarea>
		 		<span>
		 	
			 ${csQuestTemplate.csqtUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csqtUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csqtAddTime和after$csqtAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csqtAddTime==true">
	${before$csqtAddTime}
	<dl class="csqtAddTime " major  ref="csqtAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csqtAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csQuestTemplate$csqtAddTime!=null">${csQuestTemplate$csqtAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csQuestTemplate.csqtAddTime" id="csqtAddTime"  value="<s:date name="csQuestTemplate.csqtAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择问卷模板的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csqtAddTime">${csQuestTemplate.csqtAddTime}</textarea>
		 		<span>
		 	
			 ${csQuestTemplate.csqtAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csqtAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csqtStatus和after$csqtStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csqtStatus==true">
	${before$csqtStatus}
	<dl class="csqtStatus " major  ref="csqtStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csqtStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csQuestTemplate$csqtStatus!=null">${csQuestTemplate$csqtStatus}</s:if><s:else>
		 	<select class="narrow" id="csqtStatus" name="csQuestTemplate.csqtStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csQuestTemplate.csqtStatus==1?"selected":""}>正常</option>
				<option value="0" ${csQuestTemplate.csqtStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择问卷模板的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csqtStatus">${csQuestTemplate.csqtStatus}</textarea>
		 		<span>
		 	
			 ${csQuestTemplate.csqtStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csqtStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csQuestTemplate.csqtId}" name="csQuestTemplate.csqtId" id="csqtId" />
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