<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/object/app/update.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/object/app/update.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csUpdate.csuId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csuId,csuName,csuVersion,csuType,csuFile,csuDescript,csuStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csuId,csuName,csuVersion,csuType,csuFile,csuDescript,csuStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csuId,csuName,csuVersion,csuType,csuFile,csuDescript,csuStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csuId,csuName,csuVersion,csuType,csuFile,csuDescript,csuUpdateTime,csuAddTime,csuStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"App更新编辑":CTRL.title}</title>
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
	$("#updateForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csuId==true">
		${lz:set("haveEditable",true)}
		,"csUpdate.csuId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuName==true">
		${lz:set("haveEditable",true)}
		,"csUpdate.csuName":function(el){
			if(jQuery.trim(el.value)=="")
				return "名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuVersion==true">
		${lz:set("haveEditable",true)}
		,"csUpdate.csuVersion":function(el){
			if(jQuery.trim(el.value)=="")
				return "版本号不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "版本号最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuType==true">
		${lz:set("haveEditable",true)}
		,"csUpdate.csuType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuFile==true">
		${lz:set("haveEditable",true)}
		,"csUpdate.csuFile":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuDescript==true">
		${lz:set("haveEditable",true)}
		,"csUpdate.csuDescript":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "描述最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csUpdate.csuUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuAddTime==true">
		${lz:set("haveEditable",true)}
		,"csUpdate.csuAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csuStatus==true">
		${lz:set("haveEditable",true)}
		,"csUpdate.csuStatus":function(el){
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
	<form class="form editform" ref="CsUpdate" id="updateForm" name="updateForm" action="${empty CTRL.action?"update_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写App更新相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csUpdateToken}" name="csUpdateToken" id="csUpdateToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csUpdate.csuId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csuId和after$csuId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuId==true">
	${before$csuId}
	<dl class="csuId ${CTRL.e.csuId?"hidden":""}" major  ref="csuId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csuId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csUpdate$csuId!=null">${csUpdate$csuId}</s:if><s:else>
			<input type="hidden" value="${csUpdate.csuId}" name="csUpdate.csuId" id="csuId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuId">${csUpdate.csuId}</textarea>
		 		<span>
		 	
			 ${csUpdate.csuId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuId}
	</s:if>
	
	${lz:set("注释","*****************名称字段的输入框代码*****************")}
	${lz:set("注释","before$csuName和after$csuName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuName==true">
	${before$csuName}
	<dl class="csuName " major  ref="csuName" >
		<dt>名　　称:</dt>
		<s:if test="#request.CTRL.e.csuName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUpdate$csuName!=null">${csUpdate$csuName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUpdate.csuName" id="csuName"  value="${csUpdate.csuName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入App更新的名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuName">${csUpdate.csuName}</textarea>
		 		<span>
		 	
			 ${csUpdate.csuName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuName}
	</s:if>
	
	${lz:set("注释","*****************版本号字段的输入框代码*****************")}
	${lz:set("注释","before$csuVersion和after$csuVersion变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuVersion==true">
	${before$csuVersion}
	<dl class="csuVersion " major  ref="csuVersion" >
		<dt>版&nbsp;&nbsp;本&nbsp;&nbsp;号:</dt>
		<s:if test="#request.CTRL.e.csuVersion==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csUpdate$csuVersion!=null">${csUpdate$csuVersion}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csUpdate.csuVersion" id="csuVersion"  value="${csUpdate.csuVersion}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入App更新的版本号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****版本号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuVersion">${csUpdate.csuVersion}</textarea>
		 		<span>
		 	
			 ${csUpdate.csuVersion$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuVersion}
	</s:if>
	
	${lz:set("注释","*****************类型字段的输入框代码*****************")}
	${lz:set("注释","before$csuType和after$csuType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuType==true">
	${before$csuType}
	<dl class="csuType " major  ref="csuType" >
		<dt>类　　型:</dt>
		<s:if test="#request.CTRL.e.csuType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csUpdate$csuType!=null">${csUpdate$csuType}</s:if><s:else>
		 	<select class="narrow" id="csuType" name="csUpdate.csuType">
		 		<option value="">请选择</option>
				<option value="0" ${csUpdate.csuType==0?"selected":""}>android程序</option>
				<option value="1" ${csUpdate.csuType==1?"selected":""}>ios程序</option>
				<option value="2" ${csUpdate.csuType==1?"selected":""}>后勤app</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择App更新的类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuType">${csUpdate.csuType}</textarea>
		 		<span>
		 	
			 ${csUpdate.csuType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuType}
	</s:if>
	
	${lz:set("注释","*****************更新文件字段的输入框代码*****************")}
	${lz:set("注释","before$csuFile和after$csuFile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuFile==true">
	${before$csuFile}
	<dl class="csuFile " minor  ref="csuFile" style="width:98%;">
		<dt>更新文件:</dt>
		<s:if test="#request.CTRL.e.csuFile==true">
		${lz:set("haveEditable",true)}
		<dd input="file">
		<s:if test="#request.csUpdate$csuFile!=null">${csUpdate$csuFile}</s:if><s:else>
		 	<input type="text" class="input" maxlength="128" size="32" name="csUpdate.csuFile" id="csuFile"  value="${csUpdate.csuFile}"/>
			<button type="button" onclick="$.upload({type:'file',callback:function(url){if(url)$('#csuFile').val(url)}})" class="button">上传文件</button>
			<button type="button" onclick="$('#csuFile').val('')" class="button">删除文件</button>
			<button type="button" onclick="window.location='${ lz:config("lazy3q.download")==null ? basePath : ""}${ lz:config("lazy3q.download")==null ? "download" :  lz:config("lazy3q.download")}?path='+$('#csuFile').val()" class="button">下载文件</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传App更新的更新文件</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****更新文件字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csuFile">${csUpdate.csuFile}</textarea>
		 		<span>
		 	
			 ${csUpdate.csuFile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuFile}
	</s:if>
	
	${lz:set("注释","*****************描述字段的输入框代码*****************")}
	${lz:set("注释","before$csuDescript和after$csuDescript变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuDescript==true">
	${before$csuDescript}
	<dl class="csuDescript " minor  ref="csuDescript" style="width:98%;">
		<dt>描　　述:</dt>
		<s:if test="#request.CTRL.e.csuDescript==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csUpdate$csuDescript!=null">${csUpdate$csuDescript}</s:if><s:else>
		 	<textarea class="input wide"  id="csuDescript" name="csUpdate.csuDescript" rows="5">${csUpdate.csuDescript}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入App更新的描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csUpdate.csuDescript$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuDescript}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csuUpdateTime和after$csuUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuUpdateTime==true">
	${before$csuUpdateTime}
	<dl class="csuUpdateTime " major  ref="csuUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csuUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUpdate$csuUpdateTime!=null">${csUpdate$csuUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUpdate.csuUpdateTime" id="csuUpdateTime"  value="<s:date name="csUpdate.csuUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择App更新的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuUpdateTime">${csUpdate.csuUpdateTime}</textarea>
		 		<span>
		 	
			 ${csUpdate.csuUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csuAddTime和after$csuAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuAddTime==true">
	${before$csuAddTime}
	<dl class="csuAddTime " major  ref="csuAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csuAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csUpdate$csuAddTime!=null">${csUpdate$csuAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csUpdate.csuAddTime" id="csuAddTime"  value="<s:date name="csUpdate.csuAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择App更新的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuAddTime">${csUpdate.csuAddTime}</textarea>
		 		<span>
		 	
			 ${csUpdate.csuAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csuStatus和after$csuStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csuStatus==true">
	${before$csuStatus}
	<dl class="csuStatus " major  ref="csuStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csuStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csUpdate$csuStatus!=null">${csUpdate$csuStatus}</s:if><s:else>
		 	<select class="narrow" id="csuStatus" name="csUpdate.csuStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csUpdate.csuStatus==1?"selected":""}>正常</option>
				<option value="0" ${csUpdate.csuStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择App更新的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csuStatus">${csUpdate.csuStatus}</textarea>
		 		<span>
		 	
			 ${csUpdate.csuStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csuStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csUpdate.csuId}" name="csUpdate.csuId" id="csuId" />
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