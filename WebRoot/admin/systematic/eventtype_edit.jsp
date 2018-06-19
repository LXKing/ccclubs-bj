<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/systematic/eventtype.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/systematic/eventtype.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csEventType.csetId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csetId,csetName,csetLevel,csetProject,csetAction,csetInterval,csetStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csetId,csetName,csetLevel,csetProject,csetAction,csetInterval,csetStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csetId,csetName,csetLevel,csetProject,csetAction,csetInterval,csetStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csetId,csetName,csetLevel,csetProject,csetAction,csetInterval,csetStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"系统事件类型编辑":CTRL.title}</title>
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
	$("#eventtypeForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csetId==true">
		${lz:set("haveEditable",true)}
		,"csEventType.csetId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csetName==true">
		${lz:set("haveEditable",true)}
		,"csEventType.csetName":function(el){
			if(jQuery.trim(el.value)=="")
				return "事件类型名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "事件类型名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csetLevel==true">
		${lz:set("haveEditable",true)}
		,"csEventType.csetLevel":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择事件级别";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csetProject==true">
		${lz:set("haveEditable",true)}
		,"csEventType.csetProject":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csetAction==true">
		${lz:set("haveEditable",true)}
		,"csEventType.csetAction":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "处理地址最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csetInterval==true">
		${lz:set("haveEditable",true)}
		,"csEventType.csetInterval":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csetUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csEventType.csetUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csetAddTime==true">
		${lz:set("haveEditable",true)}
		,"csEventType.csetAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csetStatus==true">
		${lz:set("haveEditable",true)}
		,"csEventType.csetStatus":function(el){
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
	<form class="form editform" ref="CsEventType" id="eventtypeForm" name="eventtypeForm" action="${empty CTRL.action?"eventtype_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写系统事件类型相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csEventTypeToken}" name="csEventTypeToken" id="csEventTypeToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csEventType.csetId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csetId和after$csetId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csetId==true">
	${before$csetId}
	<dl class="csetId ${CTRL.e.csetId?"hidden":""}" major  ref="csetId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csetId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csEventType$csetId!=null">${csEventType$csetId}</s:if><s:else>
			<input type="hidden" value="${csEventType.csetId}" name="csEventType.csetId" id="csetId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csetId">${csEventType.csetId}</textarea>
		 		<span>
		 	
			 ${csEventType.csetId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csetId}
	</s:if>
	
	${lz:set("注释","*****************事件类型名称字段的输入框代码*****************")}
	${lz:set("注释","before$csetName和after$csetName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csetName==true">
	${before$csetName}
	<dl class="csetName " major  ref="csetName" >
		<dt>事件类型名称:</dt>
		<s:if test="#request.CTRL.e.csetName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csEventType$csetName!=null">${csEventType$csetName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csEventType.csetName" id="csetName"  value="${csEventType.csetName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入系统事件类型的事件类型名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****事件类型名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csetName">${csEventType.csetName}</textarea>
		 		<span>
		 	
			 ${csEventType.csetName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csetName}
	</s:if>
	
	${lz:set("注释","*****************事件级别字段的输入框代码*****************")}
	${lz:set("注释","before$csetLevel和after$csetLevel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csetLevel==true">
	${before$csetLevel}
	<dl class="csetLevel " major  ref="csetLevel" >
		<dt>事件级别:</dt>
		<s:if test="#request.CTRL.e.csetLevel==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csEventType$csetLevel!=null">${csEventType$csetLevel}</s:if><s:else>
		 	<select class="narrow" id="csetLevel" name="csEventType.csetLevel">
		 		<option value="">请选择</option>
				<option value="0" ${csEventType.csetLevel==0?"selected":""}>普通级别</option>
				<option value="1" ${csEventType.csetLevel==1?"selected":""}>全员提示</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统事件类型的事件级别</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****事件级别字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csetLevel">${csEventType.csetLevel}</textarea>
		 		<span>
		 	
			 ${csEventType.csetLevel$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csetLevel}
	</s:if>
	
	${lz:set("注释","*****************关联项目字段的输入框代码*****************")}
	${lz:set("注释","before$csetProject和after$csetProject变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csetProject==true">
	${before$csetProject}
	<dl class="csetProject " major  ref="csetProject" >
		<dt>关联项目:</dt>
		<s:if test="#request.CTRL.e.csetProject==true">
		${lz:set("haveEditable",true)}
		<dd input="tree">
		<s:if test="#request.csEventType$csetProject!=null">${csEventType$csetProject}</s:if><s:else>
		  			<select class="combox narrow"  id="csetProject" name="csEventType.csetProject" action="${basePath}${proname}/permissions/project_tree.do?parent={param}">
						<option value="${csEventType.csetProject}">
								${get:SrvProject(csEventType.csetProject).spName}
						</option>
					</select>			  		
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择系统事件类型的关联项目</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联项目字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csetProject">${csEventType.csetProject}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/project_details.do?key=${csEventType.csetProject}',{ctrl:{editable:false,visible:true}})">
			 ${csEventType.csetProject$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csetProject}
	</s:if>
	
	${lz:set("注释","*****************处理地址字段的输入框代码*****************")}
	${lz:set("注释","before$csetAction和after$csetAction变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csetAction==true">
	${before$csetAction}
	<dl class="csetAction " major  ref="csetAction" style="width:98%;">
		<dt>处理地址:</dt>
		<s:if test="#request.CTRL.e.csetAction==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csEventType$csetAction!=null">${csEventType$csetAction}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csEventType.csetAction" id="csetAction"  value="${csEventType.csetAction}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统事件类型的处理地址</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****处理地址字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csetAction">${csEventType.csetAction}</textarea>
		 		<span>
		 	
			 ${csEventType.csetAction$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csetAction}
	</s:if>
	
	${lz:set("注释","*****************时间间隔字段的输入框代码*****************")}
	${lz:set("注释","before$csetInterval和after$csetInterval变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csetInterval==true">
	${before$csetInterval}
	<dl class="csetInterval " major  ref="csetInterval" >
		<dt>时间间隔:</dt>
		<s:if test="#request.CTRL.e.csetInterval==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csEventType$csetInterval!=null">${csEventType$csetInterval}</s:if><s:else>
		 	<select class="narrow" id="csetInterval" name="csEventType.csetInterval">
		 		<option value="">请选择</option>
				<option value="0" ${csEventType.csetInterval==0?"selected":""}>不限制</option>
				<option value="60" ${csEventType.csetInterval==60?"selected":""}>一分钟</option>
				<option value="600" ${csEventType.csetInterval==600?"selected":""}>十分钟</option>
				<option value="1800" ${csEventType.csetInterval==1800?"selected":""}>半小时</option>
				<option value="3600" ${csEventType.csetInterval==3600?"selected":""}>一小时</option>
				<option value="7200" ${csEventType.csetInterval==7200?"selected":""}>两小时</option>
				<option value="43200" ${csEventType.csetInterval==43200?"selected":""}>半天</option>
				<option value="86400" ${csEventType.csetInterval==86400?"selected":""}>一天</option>
				<option value="2592000" ${csEventType.csetInterval==2592000?"selected":""}>一个月</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择系统事件类型的时间间隔</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****时间间隔字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csetInterval">${csEventType.csetInterval}</textarea>
		 		<span>
		 	
			 ${csEventType.csetInterval$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csetInterval}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csetUpdateTime和after$csetUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csetUpdateTime==true">
	${before$csetUpdateTime}
	<dl class="csetUpdateTime " major  ref="csetUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csetUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csEventType$csetUpdateTime!=null">${csEventType$csetUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csEventType.csetUpdateTime" id="csetUpdateTime"  value="<s:date name="csEventType.csetUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统事件类型的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csetUpdateTime">${csEventType.csetUpdateTime}</textarea>
		 		<span>
		 	
			 ${csEventType.csetUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csetUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csetAddTime和after$csetAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csetAddTime==true">
	${before$csetAddTime}
	<dl class="csetAddTime " major  ref="csetAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csetAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csEventType$csetAddTime!=null">${csEventType$csetAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csEventType.csetAddTime" id="csetAddTime"  value="<s:date name="csEventType.csetAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统事件类型的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csetAddTime">${csEventType.csetAddTime}</textarea>
		 		<span>
		 	
			 ${csEventType.csetAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csetAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csetStatus和after$csetStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csetStatus==true">
	${before$csetStatus}
	<dl class="csetStatus " major  ref="csetStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csetStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csEventType$csetStatus!=null">${csEventType$csetStatus}</s:if><s:else>
		 	<select class="narrow" id="csetStatus" name="csEventType.csetStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csEventType.csetStatus==1?"selected":""}>启用</option>
				<option value="0" ${csEventType.csetStatus==0?"selected":""}>禁用</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统事件类型的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csetStatus">${csEventType.csetStatus}</textarea>
		 		<span>
		 	
			 ${csEventType.csetStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csetStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csEventType.csetId}" name="csEventType.csetId" id="csetId" />
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