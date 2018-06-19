<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/systematic/upgradelog.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/systematic/upgradelog.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csUpgradeLog.csulId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csulId,csulTitle,csulType,csulDescribe,csulOnTime,csulStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csulId,csulTitle,csulType,csulDescribe,csulOnTime,csulStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csulId,csulTitle,csulType,csulDescribe,csulOnTime,csulStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csulId,csulTitle,csulType,csulDescribe,csulOnTime,csulAdder,csulStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"升级日志编辑":CTRL.title}</title>
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
	$("#upgradelogForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csulId==true">
		${lz:set("haveEditable",true)}
		,"csUpgradeLog.csulId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csulTitle==true">
		${lz:set("haveEditable",true)}
		,"csUpgradeLog.csulTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "升级名称不能为空";
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "升级名称最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csulType==true">
		${lz:set("haveEditable",true)}
		,"csUpgradeLog.csulType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择升级类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csulDescribe==true">
		${lz:set("haveEditable",true)}
		,"csUpgradeLog.csulDescribe":function(el){
			if(el.value.length>1024 && el.value.indexOf("[*]")==-1)
				return "升级描述最大长度为1024个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csulOnTime==true">
		${lz:set("haveEditable",true)}
		,"csUpgradeLog.csulOnTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "上线日期不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csulAdder==true">
		${lz:set("haveEditable",true)}
		,"csUpgradeLog.csulAdder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csulUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csUpgradeLog.csulUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csulAddTime==true">
		${lz:set("haveEditable",true)}
		,"csUpgradeLog.csulAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csulStatus==true">
		${lz:set("haveEditable",true)}
		,"csUpgradeLog.csulStatus":function(el){
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
	<form class="form editform" ref="CsUpgradeLog" id="upgradelogForm" name="upgradelogForm" action="${empty CTRL.action?"upgradelog_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写升级日志相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csUpgradeLogToken}" name="csUpgradeLogToken" id="csUpgradeLogToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csUpgradeLog.csulId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csulId和after$csulId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csulId==true">
	${before$csulId}
	<dl class="csulId ${CTRL.e.csulId?"hidden":""}" major  ref="csulId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csulId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csUpgradeLog$csulId!=null">${csUpgradeLog$csulId}</s:if><s:else>
			<input type="hidden" value="${csUpgradeLog.csulId}" name="csUpgradeLog.csulId" id="csulId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csulId">${csUpgradeLog.csulId}</textarea>
		 		<span>
		 	
			 ${csUpgradeLog.csulId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csulId}
	</s:if>
	
	${lz:set("注释","*****************升级名称字段的输入框代码*****************")}
	${lz:set("注释","before$csulTitle和after$csulTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csulTitle==true">
	${before$csulTitle}
	<dl class="csulTitle " major  ref="csulTitle" style="width:98%;">
		<dt>升级名称:</dt>
		<s:if test="#request.CTRL.e.csulTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUpgradeLog$csulTitle!=null">${csUpgradeLog$csulTitle}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csUpgradeLog.csulTitle" id="csulTitle"  value="${csUpgradeLog.csulTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入升级日志的升级名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****升级名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csulTitle">${csUpgradeLog.csulTitle}</textarea>
		 		<span>
		 	
			 ${csUpgradeLog.csulTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csulTitle}
	</s:if>
	
	${lz:set("注释","*****************升级类型字段的输入框代码*****************")}
	${lz:set("注释","before$csulType和after$csulType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csulType==true">
	${before$csulType}
	<dl class="csulType " major  ref="csulType" >
		<dt>升级类型:</dt>
		<s:if test="#request.CTRL.e.csulType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csUpgradeLog$csulType!=null">${csUpgradeLog$csulType}</s:if><s:else>
		 	<select class="narrow" id="csulType" name="csUpgradeLog.csulType">
		 		<option value="">请选择</option>
				<option value="0" ${csUpgradeLog.csulType==0?"selected":""}>系统</option>
				<option value="1" ${csUpgradeLog.csulType==1?"selected":""}>后台</option>
				<option value="2" ${csUpgradeLog.csulType==2?"selected":""}>网站</option>
				<option value="3" ${csUpgradeLog.csulType==3?"selected":""}>微信</option>
				<option value="4" ${csUpgradeLog.csulType==4?"selected":""}>支付宝</option>
				<option value="5" ${csUpgradeLog.csulType==5?"selected":""}>APP</option>
				<option value="6" ${csUpgradeLog.csulType==6?"selected":""}>API</option>
				<option value="7" ${csUpgradeLog.csulType==7?"selected":""}>车机</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择升级日志的升级类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****升级类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csulType">${csUpgradeLog.csulType}</textarea>
		 		<span>
		 	
			 ${csUpgradeLog.csulType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csulType}
	</s:if>
	
	${lz:set("注释","*****************升级描述字段的输入框代码*****************")}
	${lz:set("注释","before$csulDescribe和after$csulDescribe变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csulDescribe==true">
	${before$csulDescribe}
	<dl class="csulDescribe " minor  ref="csulDescribe" style="width:98%;">
		<dt>升级描述:</dt>
		<s:if test="#request.CTRL.e.csulDescribe==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csUpgradeLog$csulDescribe!=null">${csUpgradeLog$csulDescribe}</s:if><s:else>
		 	<textarea class="input wide"  id="csulDescribe" name="csUpgradeLog.csulDescribe" rows="5">${csUpgradeLog.csulDescribe}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入升级日志的升级描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****升级描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csUpgradeLog.csulDescribe$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csulDescribe}
	</s:if>
	
	${lz:set("注释","*****************上线日期字段的输入框代码*****************")}
	${lz:set("注释","before$csulOnTime和after$csulOnTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csulOnTime==true">
	${before$csulOnTime}
	<dl class="csulOnTime " major  ref="csulOnTime" >
		<dt>上线日期:</dt>
		<s:if test="#request.CTRL.e.csulOnTime==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csUpgradeLog$csulOnTime!=null">${csUpgradeLog$csulOnTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csUpgradeLog.csulOnTime" id="csulOnTime"  value="<s:date name="csUpgradeLog.csulOnTime" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择升级日志的上线日期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****上线日期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csulOnTime">${csUpgradeLog.csulOnTime}</textarea>
		 		<span>
		 	
			 ${csUpgradeLog.csulOnTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csulOnTime}
	</s:if>
	
	${lz:set("注释","*****************添加人字段的输入框代码*****************")}
	${lz:set("注释","before$csulAdder和after$csulAdder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csulAdder==true">
	${before$csulAdder}
	<dl class="csulAdder " major  ref="csulAdder" >
		<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csulAdder==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csUpgradeLog$csulAdder!=null">${csUpgradeLog$csulAdder}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csulAdder" name="csUpgradeLog.csulAdder">
		 				<option selected value="${csUpgradeLog.csulAdder}">
		 					${get:SrvUser(csUpgradeLog.csulAdder).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csulAdder').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csulAdder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csulAdder">${csUpgradeLog.csulAdder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csUpgradeLog.csulAdder}',{ctrl:{editable:false,visible:true}})">
			 ${csUpgradeLog.csulAdder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csulAdder}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csulUpdateTime和after$csulUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csulUpdateTime==true">
	${before$csulUpdateTime}
	<dl class="csulUpdateTime " major  ref="csulUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csulUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUpgradeLog$csulUpdateTime!=null">${csUpgradeLog$csulUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUpgradeLog.csulUpdateTime" id="csulUpdateTime"  value="<s:date name="csUpgradeLog.csulUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择升级日志的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csulUpdateTime">${csUpgradeLog.csulUpdateTime}</textarea>
		 		<span>
		 	
			 ${csUpgradeLog.csulUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csulUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csulAddTime和after$csulAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csulAddTime==true">
	${before$csulAddTime}
	<dl class="csulAddTime " major  ref="csulAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csulAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUpgradeLog$csulAddTime!=null">${csUpgradeLog$csulAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUpgradeLog.csulAddTime" id="csulAddTime"  value="<s:date name="csUpgradeLog.csulAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择升级日志的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csulAddTime">${csUpgradeLog.csulAddTime}</textarea>
		 		<span>
		 	
			 ${csUpgradeLog.csulAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csulAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csulStatus和after$csulStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csulStatus==true">
	${before$csulStatus}
	<dl class="csulStatus " major  ref="csulStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csulStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csUpgradeLog$csulStatus!=null">${csUpgradeLog$csulStatus}</s:if><s:else>
		 	<select class="narrow" id="csulStatus" name="csUpgradeLog.csulStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csUpgradeLog.csulStatus==1?"selected":""}>正常</option>
				<option value="0" ${csUpgradeLog.csulStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择升级日志的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csulStatus">${csUpgradeLog.csulStatus}</textarea>
		 		<span>
		 	
			 ${csUpgradeLog.csulStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csulStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csUpgradeLog.csulId}" name="csUpgradeLog.csulId" id="csulId" />
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