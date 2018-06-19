<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/oa/hr/employee.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/oa/hr/employee.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty tbEmployee.tbeId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"tbeId,tbeName,tbeDepart,tbeWeixinFlag,tbeMemo,tbeStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"tbeId,tbeName,tbeDepart,tbeWeixinFlag,tbeMemo,tbeStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"tbeId,tbeName,tbeDepart,tbeWeixinFlag,tbeMemo,tbeStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"tbeId,tbeName,tbeDepart,tbeWeixinFlag,tbeEditor,tbeMemo,tbeStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"公司员工编辑":CTRL.title}</title>
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
	$("#employeeForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.tbeId==true">
		${lz:set("haveEditable",true)}
		,"tbEmployee.tbeId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbeName==true">
		${lz:set("haveEditable",true)}
		,"tbEmployee.tbeName":function(el){
			if(jQuery.trim(el.value)=="")
				return "姓名不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "姓名最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbeDepart==true">
		${lz:set("haveEditable",true)}
		,"tbEmployee.tbeDepart":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbeWeixinFlag==true">
		${lz:set("haveEditable",true)}
		,"tbEmployee.tbeWeixinFlag":function(el){
			if(el.value.length>50 && el.value.indexOf("[*]")==-1)
				return "微信标识最大长度为50个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbeEditor==true">
		${lz:set("haveEditable",true)}
		,"tbEmployee.tbeEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbeRemark==true">
		${lz:set("haveEditable",true)}
		,"tbEmployee.tbeRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbeMemo==true">
		${lz:set("haveEditable",true)}
		,"tbEmployee.tbeMemo":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "备忘信息最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbeData==true">
		${lz:set("haveEditable",true)}
		,"tbEmployee.tbeData":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "程序数据最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbeUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"tbEmployee.tbeUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbeAddTime==true">
		${lz:set("haveEditable",true)}
		,"tbEmployee.tbeAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbeStatus==true">
		${lz:set("haveEditable",true)}
		,"tbEmployee.tbeStatus":function(el){
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
	<form class="form editform" ref="TbEmployee" id="employeeForm" name="employeeForm" action="${empty CTRL.action?"employee_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写公司员工相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${tbEmployeeToken}" name="tbEmployeeToken" id="tbEmployeeToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty tbEmployee.tbeId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$tbeId和after$tbeId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbeId==true">
	${before$tbeId}
	<dl class="tbeId ${CTRL.e.tbeId?"hidden":""}" major  ref="tbeId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.tbeId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.tbEmployee$tbeId!=null">${tbEmployee$tbeId}</s:if><s:else>
			<input type="hidden" value="${tbEmployee.tbeId}" name="tbEmployee.tbeId" id="tbeId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbeId">${tbEmployee.tbeId}</textarea>
		 		<span>
		 	
			 ${tbEmployee.tbeId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbeId}
	</s:if>
	
	${lz:set("注释","*****************姓名字段的输入框代码*****************")}
	${lz:set("注释","before$tbeName和after$tbeName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbeName==true">
	${before$tbeName}
	<dl class="tbeName " major  ref="tbeName" >
		<dt>姓　　名:</dt>
		<s:if test="#request.CTRL.e.tbeName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.tbEmployee$tbeName!=null">${tbEmployee$tbeName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="tbEmployee.tbeName" id="tbeName"  value="${tbEmployee.tbeName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入公司员工的姓名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****姓名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbeName">${tbEmployee.tbeName}</textarea>
		 		<span>
		 	
			 ${tbEmployee.tbeName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbeName}
	</s:if>
	
	${lz:set("注释","*****************部门字段的输入框代码*****************")}
	${lz:set("注释","before$tbeDepart和after$tbeDepart变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbeDepart==true">
	${before$tbeDepart}
	<dl class="tbeDepart " major  ref="tbeDepart" >
		<dt>部　　门:</dt>
		<s:if test="#request.CTRL.e.tbeDepart==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.tbEmployee$tbeDepart!=null">${tbEmployee$tbeDepart}</s:if><s:else>
		 			<input title="请输入公司部门部门名称进行查询" class="combox narrow" action="${basePath}${proname}/oa/hr/department_query.do?value={param}" type="text" id="tbeDepart" name="tbEmployee.tbeDepart" text="${get:TbDepartment(tbEmployee.tbeDepart).tbdName}" value="${tbEmployee.tbeDepart}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbeDepart').val())==''){return;};window.href('${basePath}${proname}/oa/hr/department_details.do?key='+$('#tbeDepart').val(),{ctrl:{editable:false}})"></a>
		 			<a class="reladd" config="{
		 				action:'${basePath}${proname}/oa/hr/department_edit.do?rd=1'
		 				,callback:function(object){			 					
		 					var input = $('#tbeDepart');
		 					input.val(object.tbdId);
		 					input.prev().html(object.tbdName);
		 				}
		 			}" href="javascript:void(0);"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****部门字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbeDepart">${tbEmployee.tbeDepart}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/oa/hr/department_details.do?key=${tbEmployee.tbeDepart}',{ctrl:{editable:false,visible:true}})">
			 ${tbEmployee.tbeDepart$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbeDepart}
	</s:if>
	
	${lz:set("注释","*****************微信标识字段的输入框代码*****************")}
	${lz:set("注释","before$tbeWeixinFlag和after$tbeWeixinFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbeWeixinFlag==true">
	${before$tbeWeixinFlag}
	<dl class="tbeWeixinFlag " minor  ref="tbeWeixinFlag" style="width:98%;">
		<dt>微信标识:</dt>
		<s:if test="#request.CTRL.e.tbeWeixinFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.tbEmployee$tbeWeixinFlag!=null">${tbEmployee$tbeWeixinFlag}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="50" name="tbEmployee.tbeWeixinFlag" id="tbeWeixinFlag"  value="${tbEmployee.tbeWeixinFlag}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入公司员工的微信标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****微信标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="tbeWeixinFlag">${tbEmployee.tbeWeixinFlag}</textarea>
		 		<span>
		 	
			 ${tbEmployee.tbeWeixinFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbeWeixinFlag}
	</s:if>
	
	${lz:set("注释","*****************修改人字段的输入框代码*****************")}
	${lz:set("注释","before$tbeEditor和after$tbeEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbeEditor==true">
	${before$tbeEditor}
	<dl class="tbeEditor " major  ref="tbeEditor" >
		<dt>修&nbsp;&nbsp;改&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.tbeEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbEmployee$tbeEditor!=null">${tbEmployee$tbeEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="tbeEditor" name="tbEmployee.tbeEditor">
		 				<option selected value="${tbEmployee.tbeEditor}">
		 					${get:SrvUser(tbEmployee.tbeEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbeEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#tbeEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbeEditor">${tbEmployee.tbeEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${tbEmployee.tbeEditor}',{ctrl:{editable:false,visible:true}})">
			 ${tbEmployee.tbeEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbeEditor}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$tbeRemark和after$tbeRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbeRemark==true">
	${before$tbeRemark}
	<dl class="tbeRemark " major  ref="tbeRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.tbeRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.tbEmployee$tbeRemark!=null">${tbEmployee$tbeRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="tbeRemark" name="tbEmployee.tbeRemark" rows="5">${tbEmployee.tbeRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入公司员工的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbEmployee.tbeRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbeRemark}
	</s:if>
	
	${lz:set("注释","*****************备忘信息字段的输入框代码*****************")}
	${lz:set("注释","before$tbeMemo和after$tbeMemo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbeMemo==true">
	${before$tbeMemo}
	<dl class="tbeMemo " minor  ref="tbeMemo" style="width:98%;">
		<dt>备忘信息:</dt>
		<s:if test="#request.CTRL.e.tbeMemo==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.tbEmployee$tbeMemo!=null">${tbEmployee$tbeMemo}</s:if><s:else>
		 	<textarea class="input wide"  id="tbeMemo" name="tbEmployee.tbeMemo" rows="5">${tbEmployee.tbeMemo}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入公司员工的备忘信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备忘信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbEmployee.tbeMemo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbeMemo}
	</s:if>
	
	${lz:set("注释","*****************程序数据字段的输入框代码*****************")}
	${lz:set("注释","before$tbeData和after$tbeData变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbeData==true">
	${before$tbeData}
	<dl class="tbeData " minor  ref="tbeData" style="width:98%;">
		<dt>程序数据:</dt>
		<s:if test="#request.CTRL.e.tbeData==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.tbEmployee$tbeData!=null">${tbEmployee$tbeData}</s:if><s:else>
		 	<textarea class="input wide"  id="tbeData" name="tbEmployee.tbeData" rows="5">${tbEmployee.tbeData}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入公司员工的程序数据</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****程序数据字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbEmployee.tbeData$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbeData}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$tbeUpdateTime和after$tbeUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbeUpdateTime==true">
	${before$tbeUpdateTime}
	<dl class="tbeUpdateTime " major  ref="tbeUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.tbeUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.tbEmployee$tbeUpdateTime!=null">${tbEmployee$tbeUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="tbEmployee.tbeUpdateTime" id="tbeUpdateTime"  value="<s:date name="tbEmployee.tbeUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择公司员工的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbeUpdateTime">${tbEmployee.tbeUpdateTime}</textarea>
		 		<span>
		 	
			 ${tbEmployee.tbeUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbeUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$tbeAddTime和after$tbeAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbeAddTime==true">
	${before$tbeAddTime}
	<dl class="tbeAddTime " major  ref="tbeAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.tbeAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.tbEmployee$tbeAddTime!=null">${tbEmployee$tbeAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="tbEmployee.tbeAddTime" id="tbeAddTime"  value="<s:date name="tbEmployee.tbeAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择公司员工的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbeAddTime">${tbEmployee.tbeAddTime}</textarea>
		 		<span>
		 	
			 ${tbEmployee.tbeAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbeAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$tbeStatus和after$tbeStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbeStatus==true">
	${before$tbeStatus}
	<dl class="tbeStatus " major  ref="tbeStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.tbeStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.tbEmployee$tbeStatus!=null">${tbEmployee$tbeStatus}</s:if><s:else>
		 	<select class="narrow" id="tbeStatus" name="tbEmployee.tbeStatus">
		 		<option value="">请选择</option>
				<option value="0" ${tbEmployee.tbeStatus==0?"selected":""}>待审核</option>
				<option value="1" ${tbEmployee.tbeStatus==1?"selected":""}>正常</option>
				<option value="2" ${tbEmployee.tbeStatus==2?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择公司员工的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbeStatus">${tbEmployee.tbeStatus}</textarea>
		 		<span>
		 	
			 ${tbEmployee.tbeStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbeStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${tbEmployee.tbeId}" name="tbEmployee.tbeId" id="tbeId" />
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