<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/configurator/query.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
${lz:set("canAdd",true)}
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/configurator/query.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty srvQuery.sqId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"sqId,sqName,sqIcon,sqPath,sqParams",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"sqId,sqName,sqIcon,sqPath,sqParams",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"sqId,sqName,sqIcon,sqPath,sqParams,sqEditor,sqStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"sqId,sqName,sqIcon,sqPath,sqParams,sqEditor,sqStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"表单查询编辑":CTRL.title}</title>
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
	$("#queryForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.sqId==true">
		${lz:set("haveEditable",true)}
		,"srvQuery.sqId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.sqName==true">
		${lz:set("haveEditable",true)}
		,"srvQuery.sqName":function(el){
			if(jQuery.trim(el.value)=="")
				return "查询名称不能为空";
			if(el.value.length>10 && el.value.indexOf("[*]")==-1)
				return "查询名称最大长度为10个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.sqIcon==true">
		${lz:set("haveEditable",true)}
		,"srvQuery.sqIcon":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.sqPath==true">
		${lz:set("haveEditable",true)}
		,"srvQuery.sqPath":function(el){
			if(jQuery.trim(el.value)=="")
				return "项目路径不能为空";
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "项目路径最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.sqParams==true">
		${lz:set("haveEditable",true)}
		,"srvQuery.sqParams":function(el){
			if(jQuery.trim(el.value)=="")
				return "查询参数不能为空";
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "查询参数最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.sqEditor==true">
		${lz:set("haveEditable",true)}
		,"srvQuery.sqEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.sqUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"srvQuery.sqUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.sqAddTime==true">
		${lz:set("haveEditable",true)}
		,"srvQuery.sqAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.sqStatus==true">
		${lz:set("haveEditable",true)}
		,"srvQuery.sqStatus":function(el){
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
	<form class="form editform" ref="SrvQuery" id="queryForm" name="queryForm" action="${empty CTRL.action?"query_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写表单查询相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${srvQueryToken}" name="srvQueryToken" id="srvQueryToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty srvQuery.sqId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$sqId和after$sqId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.sqId==true">
	${before$sqId}
	<dl class="sqId ${CTRL.e.sqId?"hidden":""}" major  ref="sqId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.sqId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.srvQuery$sqId!=null">${srvQuery$sqId}</s:if><s:else>
			<input type="hidden" value="${srvQuery.sqId}" name="srvQuery.sqId" id="sqId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="sqId">${srvQuery.sqId}</textarea>
		 		<span>
		 	
			 ${srvQuery.sqId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$sqId}
	</s:if>
	
	${lz:set("注释","*****************查询名称字段的输入框代码*****************")}
	${lz:set("注释","before$sqName和after$sqName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.sqName==true">
	${before$sqName}
	<dl class="sqName " major  ref="sqName" >
		<dt>查询名称:</dt>
		<s:if test="#request.CTRL.e.sqName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvQuery$sqName!=null">${srvQuery$sqName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="10" name="srvQuery.sqName" id="sqName"  value="${srvQuery.sqName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入表单查询的查询名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****查询名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="sqName">${srvQuery.sqName}</textarea>
		 		<span>
		 	
			 ${srvQuery.sqName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$sqName}
	</s:if>
	
	${lz:set("注释","*****************图标字段的输入框代码*****************")}
	${lz:set("注释","before$sqIcon和after$sqIcon变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.sqIcon==true">
	${before$sqIcon}
	<dl class="sqIcon " major  ref="sqIcon" style="width:98%;">
		<dt>图　　标:</dt>
		<s:if test="#request.CTRL.e.sqIcon==true">
		${lz:set("haveEditable",true)}
		<dd input="icon">
		<s:if test="#request.srvQuery$sqIcon!=null">${srvQuery$sqIcon}</s:if><s:else>
		 	<img class="sqIcon" src="" style="width:48px; height:48px; float:left; margin-right:10px;"/>
			<input type="hidden" name="srvQuery.sqIcon" id="sqIcon"  value="${srvQuery.sqIcon}"/>
			<button class="button" type="button" onclick="top.$.sysicon({jsp:'${basePath}admin/icons.jsp',callback:function(path){$('#sqIcon').val(path);$('.sqIcon').attr('src',path)}});">系统图标</button>
			&nbsp;&nbsp;
			<button class="button" type="button" onclick="$.upload({type:'img',callback:function(path){$('#sqIcon').val(path);$('.sqIcon').attr('src',path)})">上传图标</button>
			&nbsp;&nbsp;
			<button class="button" type="button" onclick="$('#sqIcon').val('');$('.sqIcon').attr('src','')">删除图标</button>
			<br />
			上传图标时建议图标为透明png图片，48*48像素
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****图标字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="sqIcon">${srvQuery.sqIcon}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(srvQuery.sqIcon))}
		 	<s:if test="#request.isVacant==false">
		 	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="${srvQuery.sqIcon}"/>
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$sqIcon}
	</s:if>
	
	${lz:set("注释","*****************项目路径字段的输入框代码*****************")}
	${lz:set("注释","before$sqPath和after$sqPath变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.sqPath==true">
	${before$sqPath}
	<dl class="sqPath " major  ref="sqPath" style="width:98%;">
		<dt>项目路径:</dt>
		<s:if test="#request.CTRL.e.sqPath==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvQuery$sqPath!=null">${srvQuery$sqPath}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="srvQuery.sqPath" id="sqPath"  value="${srvQuery.sqPath}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入表单查询的项目路径</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****项目路径字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="sqPath">${srvQuery.sqPath}</textarea>
		 		<span>
		 	
			 ${srvQuery.sqPath$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$sqPath}
	</s:if>
	
	${lz:set("注释","*****************查询参数字段的输入框代码*****************")}
	${lz:set("注释","before$sqParams和after$sqParams变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.sqParams==true">
	${before$sqParams}
	<dl class="sqParams " major  ref="sqParams" style="width:98%;">
		<dt>查询参数:</dt>
		<s:if test="#request.CTRL.e.sqParams==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.srvQuery$sqParams!=null">${srvQuery$sqParams}</s:if><s:else>
		 	<textarea class="input wide"  id="sqParams" name="srvQuery.sqParams" rows="5">${srvQuery.sqParams}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入表单查询的查询参数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****查询参数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${srvQuery.sqParams$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$sqParams}
	</s:if>
	
	${lz:set("注释","*****************添加人字段的输入框代码*****************")}
	${lz:set("注释","before$sqEditor和after$sqEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.sqEditor==true">
	${before$sqEditor}
	<dl class="sqEditor " major  ref="sqEditor" >
		<dt>添&nbsp;&nbsp;加&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.sqEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.srvQuery$sqEditor!=null">${srvQuery$sqEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="sqEditor" name="srvQuery.sqEditor">
		 				<option selected value="${srvQuery.sqEditor}">
		 					${get:SrvUser(srvQuery.sqEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#sqEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#sqEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="sqEditor">${srvQuery.sqEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${srvQuery.sqEditor}',{ctrl:{editable:false,visible:true}})">
			 ${srvQuery.sqEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$sqEditor}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$sqUpdateTime和after$sqUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.sqUpdateTime==true">
	${before$sqUpdateTime}
	<dl class="sqUpdateTime " major  ref="sqUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.sqUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.srvQuery$sqUpdateTime!=null">${srvQuery$sqUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="srvQuery.sqUpdateTime" id="sqUpdateTime"  value="<s:date name="srvQuery.sqUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择表单查询的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="sqUpdateTime">${srvQuery.sqUpdateTime}</textarea>
		 		<span>
		 	
			 ${srvQuery.sqUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$sqUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$sqAddTime和after$sqAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.sqAddTime==true">
	${before$sqAddTime}
	<dl class="sqAddTime " major  ref="sqAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.sqAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.srvQuery$sqAddTime!=null">${srvQuery$sqAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="srvQuery.sqAddTime" id="sqAddTime"  value="<s:date name="srvQuery.sqAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择表单查询的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="sqAddTime">${srvQuery.sqAddTime}</textarea>
		 		<span>
		 	
			 ${srvQuery.sqAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$sqAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$sqStatus和after$sqStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.sqStatus==true">
	${before$sqStatus}
	<dl class="sqStatus " major  ref="sqStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.sqStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.srvQuery$sqStatus!=null">${srvQuery$sqStatus}</s:if><s:else>
		 	<select class="narrow" id="sqStatus" name="srvQuery.sqStatus">
		 		<option value="">请选择</option>
				<option value="1" ${srvQuery.sqStatus==1?"selected":""}>正常</option>
				<option value="0" ${srvQuery.sqStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择表单查询的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="sqStatus">${srvQuery.sqStatus}</textarea>
		 		<span>
		 	
			 ${srvQuery.sqStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$sqStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${srvQuery.sqId}" name="srvQuery.sqId" id="sqId" />
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