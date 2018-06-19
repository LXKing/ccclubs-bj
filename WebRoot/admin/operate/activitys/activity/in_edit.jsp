<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/operate/activitys/activity/in.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/operate/activitys/activity/in.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csActivityIn.csaiId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csaiId,csaiHost,csaiActivity,csaiMember,csaiRemark,csaiData,csaiState,csaiStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csaiId,csaiHost,csaiActivity,csaiMember,csaiRemark,csaiData,csaiState,csaiStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csaiId,csaiHost,csaiActivity,csaiMember,csaiRemark,csaiData,csaiState,csaiStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csaiId,csaiHost,csaiActivity,csaiMember,csaiRemark,csaiData,csaiState,csaiStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"活动参与编辑":CTRL.title}</title>
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
	$("#inForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csaiId==true">
		${lz:set("haveEditable",true)}
		,"csActivityIn.csaiId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaiHost==true">
		${lz:set("haveEditable",true)}
		,"csActivityIn.csaiHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaiActivity==true">
		${lz:set("haveEditable",true)}
		,"csActivityIn.csaiActivity":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaiMember==true">
		${lz:set("haveEditable",true)}
		,"csActivityIn.csaiMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaiUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csActivityIn.csaiUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaiAddTime==true">
		${lz:set("haveEditable",true)}
		,"csActivityIn.csaiAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaiRemark==true">
		${lz:set("haveEditable",true)}
		,"csActivityIn.csaiRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaiData==true">
		${lz:set("haveEditable",true)}
		,"csActivityIn.csaiData":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "JSON数据最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaiState==true">
		${lz:set("haveEditable",true)}
		,"csActivityIn.csaiState":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "状态码最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaiStatus==true">
		${lz:set("haveEditable",true)}
		,"csActivityIn.csaiStatus":function(el){
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
	<form class="form editform" ref="CsActivityIn" id="inForm" name="inForm" action="${empty CTRL.action?"in_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写活动参与相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csActivityInToken}" name="csActivityInToken" id="csActivityInToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csActivityIn.csaiId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csaiId和after$csaiId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaiId==true">
	${before$csaiId}
	<dl class="csaiId ${CTRL.e.csaiId?"hidden":""}" major  ref="csaiId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csaiId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csActivityIn$csaiId!=null">${csActivityIn$csaiId}</s:if><s:else>
			<input type="hidden" value="${csActivityIn.csaiId}" name="csActivityIn.csaiId" id="csaiId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaiId">${csActivityIn.csaiId}</textarea>
		 		<span>
		 	
			 ${csActivityIn.csaiId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaiId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csaiHost和after$csaiHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaiHost==true">
	${before$csaiHost}
	<dl class="csaiHost " major  ref="csaiHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csaiHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csActivityIn$csaiHost!=null">${csActivityIn$csaiHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csaiHost" name="csActivityIn.csaiHost">
		 				<option selected value="${csActivityIn.csaiHost}">
		 					${get:SrvHost(csActivityIn.csaiHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csaiHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csaiHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaiHost">${csActivityIn.csaiHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csActivityIn.csaiHost}',{ctrl:{editable:false,visible:true}})">
			 ${csActivityIn.csaiHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaiHost}
	</s:if>
	
	${lz:set("注释","*****************所属活动字段的输入框代码*****************")}
	${lz:set("注释","before$csaiActivity和after$csaiActivity变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaiActivity==true">
	${before$csaiActivity}
	<dl class="csaiActivity " major  ref="csaiActivity" >
		<dt>所属活动:</dt>
		<s:if test="#request.CTRL.e.csaiActivity==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csActivityIn$csaiActivity!=null">${csActivityIn$csaiActivity}</s:if><s:else>
		 			<input title="请输入营销活动活动主题进行查询" class="combox narrow" action="${basePath}${proname}/operate/activitys/activity_query.do?value={param}&csaHost={csaiHost}" type="text" id="csaiActivity" name="csActivityIn.csaiActivity" text="${get:CsActivity(csActivityIn.csaiActivity).csaTitle}" value="${csActivityIn.csaiActivity}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csaiActivity').val())==''){return;};window.href('${basePath}${proname}/operate/activitys/activity_details.do?key='+$('#csaiActivity').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属活动字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaiActivity">${csActivityIn.csaiActivity}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/operate/activitys/activity_details.do?key=${csActivityIn.csaiActivity}',{ctrl:{editable:false,visible:true}})">
			 ${csActivityIn.csaiActivity$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaiActivity}
	</s:if>
	
	${lz:set("注释","*****************参与人字段的输入框代码*****************")}
	${lz:set("注释","before$csaiMember和after$csaiMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaiMember==true">
	${before$csaiMember}
	<dl class="csaiMember " major  ref="csaiMember" >
		<dt>参&nbsp;&nbsp;与&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csaiMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csActivityIn$csaiMember!=null">${csActivityIn$csaiMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={csaiHost}" type="text" id="csaiMember" name="csActivityIn.csaiMember" text="${get:CsMember(csActivityIn.csaiMember).csmName}" value="${csActivityIn.csaiMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csaiMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csaiMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****参与人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaiMember">${csActivityIn.csaiMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csActivityIn.csaiMember}',{ctrl:{editable:false,visible:true}})">
			 ${csActivityIn.csaiMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaiMember}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csaiUpdateTime和after$csaiUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaiUpdateTime==true">
	${before$csaiUpdateTime}
	<dl class="csaiUpdateTime " major  ref="csaiUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csaiUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csActivityIn$csaiUpdateTime!=null">${csActivityIn$csaiUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csActivityIn.csaiUpdateTime" id="csaiUpdateTime"  value="<s:date name="csActivityIn.csaiUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择活动参与的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaiUpdateTime">${csActivityIn.csaiUpdateTime}</textarea>
		 		<span>
		 	
			 ${csActivityIn.csaiUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaiUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csaiAddTime和after$csaiAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaiAddTime==true">
	${before$csaiAddTime}
	<dl class="csaiAddTime " major  ref="csaiAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csaiAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csActivityIn$csaiAddTime!=null">${csActivityIn$csaiAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csActivityIn.csaiAddTime" id="csaiAddTime"  value="<s:date name="csActivityIn.csaiAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择活动参与的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaiAddTime">${csActivityIn.csaiAddTime}</textarea>
		 		<span>
		 	
			 ${csActivityIn.csaiAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaiAddTime}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$csaiRemark和after$csaiRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaiRemark==true">
	${before$csaiRemark}
	<dl class="csaiRemark " major  ref="csaiRemark" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.csaiRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csActivityIn$csaiRemark!=null">${csActivityIn$csaiRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csaiRemark" name="csActivityIn.csaiRemark" rows="5">${csActivityIn.csaiRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入活动参与的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csActivityIn.csaiRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaiRemark}
	</s:if>
	
	${lz:set("注释","*****************JSON数据字段的输入框代码*****************")}
	${lz:set("注释","before$csaiData和after$csaiData变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaiData==true">
	${before$csaiData}
	<dl class="csaiData " minor  ref="csaiData" style="width:98%;">
		<dt>JSON数据:</dt>
		<s:if test="#request.CTRL.e.csaiData==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csActivityIn$csaiData!=null">${csActivityIn$csaiData}</s:if><s:else>
		 	<textarea class="input wide"  id="csaiData" name="csActivityIn.csaiData" rows="5">${csActivityIn.csaiData}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>每个活动根据自己情况自定义数据存储</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****JSON数据字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csActivityIn.csaiData$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaiData}
	</s:if>
	
	${lz:set("注释","*****************状态码字段的输入框代码*****************")}
	${lz:set("注释","before$csaiState和after$csaiState变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaiState==true">
	${before$csaiState}
	<dl class="csaiState " major  ref="csaiState" >
		<dt>状&nbsp;&nbsp;态&nbsp;&nbsp;码:</dt>
		<s:if test="#request.CTRL.e.csaiState==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csActivityIn$csaiState!=null">${csActivityIn$csaiState}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csActivityIn.csaiState" id="csaiState"  value="${csActivityIn.csaiState}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>每个活动根据自己情况自定义状态码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaiState">${csActivityIn.csaiState}</textarea>
		 		<span>
		 	
			 ${csActivityIn.csaiState$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaiState}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csaiStatus和after$csaiStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaiStatus==true">
	${before$csaiStatus}
	<dl class="csaiStatus " major  ref="csaiStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csaiStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csActivityIn$csaiStatus!=null">${csActivityIn$csaiStatus}</s:if><s:else>
		 	<select class="narrow" id="csaiStatus" name="csActivityIn.csaiStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csActivityIn.csaiStatus==1?"selected":""}>正常</option>
				<option value="0" ${csActivityIn.csaiStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择活动参与的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaiStatus">${csActivityIn.csaiStatus}</textarea>
		 		<span>
		 	
			 ${csActivityIn.csaiStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaiStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csActivityIn.csaiId}" name="csActivityIn.csaiId" id="csaiId" />
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