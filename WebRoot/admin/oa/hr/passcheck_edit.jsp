<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/oa/hr/passcheck.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/oa/hr/passcheck.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty tbPassCheck.tbpcId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"tbpcId,tbpcHost,tbpcTitle,tbpcInPos,tbpcInDistance,tbpcInTime,tbpcOutPos,tbpcOutDistance,tbpcOutTime,tbpcTimeLong,tbpcRemark,tbpcStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"tbpcId,tbpcHost,tbpcTitle,tbpcInPos,tbpcInDistance,tbpcInTime,tbpcOutPos,tbpcOutDistance,tbpcOutTime,tbpcTimeLong,tbpcRemark,tbpcStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"tbpcId,tbpcHost,tbpcTitle,tbpcInPos,tbpcInDistance,tbpcInTime,tbpcOutPos,tbpcOutDistance,tbpcOutTime,tbpcTimeLong,tbpcRemark,tbpcStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"tbpcId,tbpcHost,tbpcTitle,tbpcEmployee,tbpcInPos,tbpcInDistance,tbpcInTime,tbpcOutPos,tbpcOutDistance,tbpcOutTime,tbpcTimeLong,tbpcRemark,tbpcStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"上下班签到编辑":CTRL.title}</title>
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
	$("#passcheckForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.tbpcId==true">
		${lz:set("haveEditable",true)}
		,"tbPassCheck.tbpcId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpcHost==true">
		${lz:set("haveEditable",true)}
		,"tbPassCheck.tbpcHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpcTitle==true">
		${lz:set("haveEditable",true)}
		,"tbPassCheck.tbpcTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "签到描述不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "签到描述最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpcEmployee==true">
		${lz:set("haveEditable",true)}
		,"tbPassCheck.tbpcEmployee":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpcInPos==true">
		${lz:set("haveEditable",true)}
		,"tbPassCheck.tbpcInPos":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "签入位置最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpcInDistance==true">
		${lz:set("haveEditable",true)}
		,"tbPassCheck.tbpcInDistance":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpcInTime==true">
		${lz:set("haveEditable",true)}
		,"tbPassCheck.tbpcInTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpcOutPos==true">
		${lz:set("haveEditable",true)}
		,"tbPassCheck.tbpcOutPos":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "签出位置最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpcOutDistance==true">
		${lz:set("haveEditable",true)}
		,"tbPassCheck.tbpcOutDistance":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpcOutTime==true">
		${lz:set("haveEditable",true)}
		,"tbPassCheck.tbpcOutTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpcTimeLong==true">
		${lz:set("haveEditable",true)}
		,"tbPassCheck.tbpcTimeLong":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpcRemark==true">
		${lz:set("haveEditable",true)}
		,"tbPassCheck.tbpcRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbpcStatus==true">
		${lz:set("haveEditable",true)}
		,"tbPassCheck.tbpcStatus":function(el){
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
	<form class="form editform" ref="TbPassCheck" id="passcheckForm" name="passcheckForm" action="${empty CTRL.action?"passcheck_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写上下班签到相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${tbPassCheckToken}" name="tbPassCheckToken" id="tbPassCheckToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty tbPassCheck.tbpcId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$tbpcId和after$tbpcId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpcId==true">
	${before$tbpcId}
	<dl class="tbpcId ${CTRL.e.tbpcId?"hidden":""}" major  ref="tbpcId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.tbpcId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.tbPassCheck$tbpcId!=null">${tbPassCheck$tbpcId}</s:if><s:else>
			<input type="hidden" value="${tbPassCheck.tbpcId}" name="tbPassCheck.tbpcId" id="tbpcId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbpcId">${tbPassCheck.tbpcId}</textarea>
		 		<span>
		 	
			 ${tbPassCheck.tbpcId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpcId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$tbpcHost和after$tbpcHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpcHost==true">
	${before$tbpcHost}
	<dl class="tbpcHost " major  ref="tbpcHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.tbpcHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbPassCheck$tbpcHost!=null">${tbPassCheck$tbpcHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="tbpcHost" name="tbPassCheck.tbpcHost">
		 				<option selected value="${tbPassCheck.tbpcHost}">
		 					${get:SrvHost(tbPassCheck.tbpcHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbpcHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#tbpcHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbpcHost">${tbPassCheck.tbpcHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${tbPassCheck.tbpcHost}',{ctrl:{editable:false,visible:true}})">
			 ${tbPassCheck.tbpcHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpcHost}
	</s:if>
	
	${lz:set("注释","*****************签到描述字段的输入框代码*****************")}
	${lz:set("注释","before$tbpcTitle和after$tbpcTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpcTitle==true">
	${before$tbpcTitle}
	<dl class="tbpcTitle " major  ref="tbpcTitle" >
		<dt>签到描述:</dt>
		<s:if test="#request.CTRL.e.tbpcTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.tbPassCheck$tbpcTitle!=null">${tbPassCheck$tbpcTitle}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="tbPassCheck.tbpcTitle" id="tbpcTitle"  value="${tbPassCheck.tbpcTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入上下班签到的签到描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****签到描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbpcTitle">${tbPassCheck.tbpcTitle}</textarea>
		 		<span>
		 	
			 ${tbPassCheck.tbpcTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpcTitle}
	</s:if>
	
	${lz:set("注释","*****************员工字段的输入框代码*****************")}
	${lz:set("注释","before$tbpcEmployee和after$tbpcEmployee变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpcEmployee==true">
	${before$tbpcEmployee}
	<dl class="tbpcEmployee " major  ref="tbpcEmployee" >
		<dt>员　　工:</dt>
		<s:if test="#request.CTRL.e.tbpcEmployee==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.tbPassCheck$tbpcEmployee!=null">${tbPassCheck$tbpcEmployee}</s:if><s:else>
		 			<input title="请输入公司员工姓名进行查询" class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do?value={param}" type="text" id="tbpcEmployee" name="tbPassCheck.tbpcEmployee" text="${get:TbEmployee(tbPassCheck.tbpcEmployee).tbeName}" value="${tbPassCheck.tbpcEmployee}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbpcEmployee').val())==''){return;};window.href('${basePath}${proname}/oa/hr/employee_details.do?key='+$('#tbpcEmployee').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****员工字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbpcEmployee">${tbPassCheck.tbpcEmployee}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/oa/hr/employee_details.do?key=${tbPassCheck.tbpcEmployee}',{ctrl:{editable:false,visible:true}})">
			 ${tbPassCheck.tbpcEmployee$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpcEmployee}
	</s:if>
	
	${lz:set("注释","*****************签入位置字段的输入框代码*****************")}
	${lz:set("注释","before$tbpcInPos和after$tbpcInPos变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpcInPos==true">
	${before$tbpcInPos}
	<dl class="tbpcInPos " minor  ref="tbpcInPos" >
		<dt>签入位置:</dt>
		<s:if test="#request.CTRL.e.tbpcInPos==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.tbPassCheck$tbpcInPos!=null">${tbPassCheck$tbpcInPos}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="tbPassCheck.tbpcInPos" id="tbpcInPos"  value="${tbPassCheck.tbpcInPos}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入上下班签到的签入位置</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****签入位置字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbpcInPos">${tbPassCheck.tbpcInPos}</textarea>
		 		<span>
		 	
			 ${tbPassCheck.tbpcInPos$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpcInPos}
	</s:if>
	
	${lz:set("注释","*****************签入距离字段的输入框代码*****************")}
	${lz:set("注释","before$tbpcInDistance和after$tbpcInDistance变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpcInDistance==true">
	${before$tbpcInDistance}
	<dl class="tbpcInDistance " major  ref="tbpcInDistance" >
		<dt>签入距离:</dt>
		<s:if test="#request.CTRL.e.tbpcInDistance==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.tbPassCheck$tbpcInDistance!=null">${tbPassCheck$tbpcInDistance}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="tbPassCheck.tbpcInDistance" id="tbpcInDistance"  value="${tbPassCheck.tbpcInDistance}"/>
	 	 </s:else>
	 	 米
	 	 
	 	 <em>请输入上下班签到的签入距离</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****签入距离字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbpcInDistance">${tbPassCheck.tbpcInDistance}</textarea>
		 		<span>
		 	
			 ${tbPassCheck.tbpcInDistance$}
	 	 米 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpcInDistance}
	</s:if>
	
	${lz:set("注释","*****************签入时间字段的输入框代码*****************")}
	${lz:set("注释","before$tbpcInTime和after$tbpcInTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpcInTime==true">
	${before$tbpcInTime}
	<dl class="tbpcInTime " major  ref="tbpcInTime" >
		<dt>签入时间:</dt>
		<s:if test="#request.CTRL.e.tbpcInTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.tbPassCheck$tbpcInTime!=null">${tbPassCheck$tbpcInTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="tbPassCheck.tbpcInTime" id="tbpcInTime"  value="<s:date name="tbPassCheck.tbpcInTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择上下班签到的签入时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****签入时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbpcInTime">${tbPassCheck.tbpcInTime}</textarea>
		 		<span>
		 	
			 ${tbPassCheck.tbpcInTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpcInTime}
	</s:if>
	
	${lz:set("注释","*****************签出位置字段的输入框代码*****************")}
	${lz:set("注释","before$tbpcOutPos和after$tbpcOutPos变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpcOutPos==true">
	${before$tbpcOutPos}
	<dl class="tbpcOutPos " minor  ref="tbpcOutPos" >
		<dt>签出位置:</dt>
		<s:if test="#request.CTRL.e.tbpcOutPos==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.tbPassCheck$tbpcOutPos!=null">${tbPassCheck$tbpcOutPos}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="tbPassCheck.tbpcOutPos" id="tbpcOutPos"  value="${tbPassCheck.tbpcOutPos}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入上下班签到的签出位置</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****签出位置字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbpcOutPos">${tbPassCheck.tbpcOutPos}</textarea>
		 		<span>
		 	
			 ${tbPassCheck.tbpcOutPos$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpcOutPos}
	</s:if>
	
	${lz:set("注释","*****************签出距离字段的输入框代码*****************")}
	${lz:set("注释","before$tbpcOutDistance和after$tbpcOutDistance变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpcOutDistance==true">
	${before$tbpcOutDistance}
	<dl class="tbpcOutDistance " major  ref="tbpcOutDistance" >
		<dt>签出距离:</dt>
		<s:if test="#request.CTRL.e.tbpcOutDistance==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.tbPassCheck$tbpcOutDistance!=null">${tbPassCheck$tbpcOutDistance}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="tbPassCheck.tbpcOutDistance" id="tbpcOutDistance"  value="${tbPassCheck.tbpcOutDistance}"/>
	 	 </s:else>
	 	 米
	 	 
	 	 <em>请输入上下班签到的签出距离</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****签出距离字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbpcOutDistance">${tbPassCheck.tbpcOutDistance}</textarea>
		 		<span>
		 	
			 ${tbPassCheck.tbpcOutDistance$}
	 	 米 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpcOutDistance}
	</s:if>
	
	${lz:set("注释","*****************签出时间字段的输入框代码*****************")}
	${lz:set("注释","before$tbpcOutTime和after$tbpcOutTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpcOutTime==true">
	${before$tbpcOutTime}
	<dl class="tbpcOutTime " major  ref="tbpcOutTime" >
		<dt>签出时间:</dt>
		<s:if test="#request.CTRL.e.tbpcOutTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.tbPassCheck$tbpcOutTime!=null">${tbPassCheck$tbpcOutTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="tbPassCheck.tbpcOutTime" id="tbpcOutTime"  value="<s:date name="tbPassCheck.tbpcOutTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择上下班签到的签出时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****签出时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbpcOutTime">${tbPassCheck.tbpcOutTime}</textarea>
		 		<span>
		 	
			 ${tbPassCheck.tbpcOutTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpcOutTime}
	</s:if>
	
	${lz:set("注释","*****************作业时长字段的输入框代码*****************")}
	${lz:set("注释","before$tbpcTimeLong和after$tbpcTimeLong变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpcTimeLong==true">
	${before$tbpcTimeLong}
	<dl class="tbpcTimeLong " major  ref="tbpcTimeLong" >
		<dt>作业时长:</dt>
		<s:if test="#request.CTRL.e.tbpcTimeLong==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.tbPassCheck$tbpcTimeLong!=null">${tbPassCheck$tbpcTimeLong}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="tbPassCheck.tbpcTimeLong" id="tbpcTimeLong"  value="${tbPassCheck.tbpcTimeLong}"/>
	 	 </s:else>
	 	 分
	 	 
	 	 <em>请输入上下班签到的作业时长</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****作业时长字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbpcTimeLong">${tbPassCheck.tbpcTimeLong}</textarea>
		 		<span>
		 	
			 ${tbPassCheck.tbpcTimeLong$}
	 	 分 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpcTimeLong}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$tbpcRemark和after$tbpcRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpcRemark==true">
	${before$tbpcRemark}
	<dl class="tbpcRemark " minor  ref="tbpcRemark" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.tbpcRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.tbPassCheck$tbpcRemark!=null">${tbPassCheck$tbpcRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="tbpcRemark" name="tbPassCheck.tbpcRemark" rows="5">${tbPassCheck.tbpcRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入上下班签到的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbPassCheck.tbpcRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpcRemark}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$tbpcStatus和after$tbpcStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbpcStatus==true">
	${before$tbpcStatus}
	<dl class="tbpcStatus " major  ref="tbpcStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.tbpcStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.tbPassCheck$tbpcStatus!=null">${tbPassCheck$tbpcStatus}</s:if><s:else>
		 	<select class="narrow" id="tbpcStatus" name="tbPassCheck.tbpcStatus">
		 		<option value="">请选择</option>
				<option value="1" ${tbPassCheck.tbpcStatus==1?"selected":""}>正常</option>
				<option value="0" ${tbPassCheck.tbpcStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择上下班签到的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbpcStatus">${tbPassCheck.tbpcStatus}</textarea>
		 		<span>
		 	
			 ${tbPassCheck.tbpcStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbpcStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${tbPassCheck.tbpcId}" name="tbPassCheck.tbpcId" id="tbpcId" />
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