<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/csm/callin.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/csm/callin.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csCallIn.csciId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csciId,csciHost,csciNumber,csciMember,csciName,csciHowLong,csciStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csciId,csciHost,csciNumber,csciMember,csciName,csciHowLong,csciStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csciId,csciHost,csciNumber,csciMember,csciName,csciHowLong,csciStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csciId,csciHost,csciNumber,csciMember,csciName,csciHowLong,csciStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"客户来电编辑":CTRL.title}</title>
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
	$("#callinForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csciId==true">
		${lz:set("haveEditable",true)}
		,"csCallIn.csciId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csciHost==true">
		${lz:set("haveEditable",true)}
		,"csCallIn.csciHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csciNumber==true">
		${lz:set("haveEditable",true)}
		,"csCallIn.csciNumber":function(el){
			if(jQuery.trim(el.value)=="")
				return "来电号码不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "来电号码最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csciMember==true">
		${lz:set("haveEditable",true)}
		,"csCallIn.csciMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csciName==true">
		${lz:set("haveEditable",true)}
		,"csCallIn.csciName":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "姓名最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csciAnswer==true">
		${lz:set("haveEditable",true)}
		,"csCallIn.csciAnswer":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csciHowLong==true">
		${lz:set("haveEditable",true)}
		,"csCallIn.csciHowLong":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csciUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csCallIn.csciUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csciAddTime==true">
		${lz:set("haveEditable",true)}
		,"csCallIn.csciAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "来电时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csciStatus==true">
		${lz:set("haveEditable",true)}
		,"csCallIn.csciStatus":function(el){
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
	<form class="form editform" ref="CsCallIn" id="callinForm" name="callinForm" action="${empty CTRL.action?"callin_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写客户来电相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csCallInToken}" name="csCallInToken" id="csCallInToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csCallIn.csciId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csciId和after$csciId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csciId==true">
	${before$csciId}
	<dl class="csciId ${CTRL.e.csciId?"hidden":""}" major  ref="csciId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csciId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csCallIn$csciId!=null">${csCallIn$csciId}</s:if><s:else>
			<input type="hidden" value="${csCallIn.csciId}" name="csCallIn.csciId" id="csciId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csciId">${csCallIn.csciId}</textarea>
		 		<span>
		 	
			 ${csCallIn.csciId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csciId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csciHost和after$csciHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csciHost==true">
	${before$csciHost}
	<dl class="csciHost " major  ref="csciHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csciHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCallIn$csciHost!=null">${csCallIn$csciHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csciHost" name="csCallIn.csciHost">
		 				<option selected value="${csCallIn.csciHost}">
		 					${get:SrvHost(csCallIn.csciHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csciHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csciHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csciHost">${csCallIn.csciHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csCallIn.csciHost}',{ctrl:{editable:false,visible:true}})">
			 ${csCallIn.csciHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csciHost}
	</s:if>
	
	${lz:set("注释","*****************来电号码字段的输入框代码*****************")}
	${lz:set("注释","before$csciNumber和after$csciNumber变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csciNumber==true">
	${before$csciNumber}
	<dl class="csciNumber " major  ref="csciNumber" >
		<dt>来电号码:</dt>
		<s:if test="#request.CTRL.e.csciNumber==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCallIn$csciNumber!=null">${csCallIn$csciNumber}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCallIn.csciNumber" id="csciNumber"  value="${csCallIn.csciNumber}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入客户来电的来电号码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****来电号码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csciNumber">${csCallIn.csciNumber}</textarea>
		 		<span>
		 	
			 ${csCallIn.csciNumber$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csciNumber}
	</s:if>
	
	${lz:set("注释","*****************来电会员字段的输入框代码*****************")}
	${lz:set("注释","before$csciMember和after$csciMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csciMember==true">
	${before$csciMember}
	<dl class="csciMember " major  ref="csciMember" >
		<dt>来电会员:</dt>
		<s:if test="#request.CTRL.e.csciMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csCallIn$csciMember!=null">${csCallIn$csciMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={csciHost}" type="text" id="csciMember" name="csCallIn.csciMember" text="${get:CsMember(csCallIn.csciMember).csmName}" value="${csCallIn.csciMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csciMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csciMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****来电会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csciMember">${csCallIn.csciMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csCallIn.csciMember}',{ctrl:{editable:false,visible:true}})">
			 ${csCallIn.csciMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csciMember}
	</s:if>
	
	${lz:set("注释","*****************姓名字段的输入框代码*****************")}
	${lz:set("注释","before$csciName和after$csciName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csciName==true">
	${before$csciName}
	<dl class="csciName " minor  ref="csciName" >
		<dt>姓　　名:</dt>
		<s:if test="#request.CTRL.e.csciName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCallIn$csciName!=null">${csCallIn$csciName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCallIn.csciName" id="csciName"  value="${csCallIn.csciName}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入客户来电的姓名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****姓名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csciName">${csCallIn.csciName}</textarea>
		 		<span>
		 	
			 ${csCallIn.csciName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csciName}
	</s:if>
	
	${lz:set("注释","*****************接听人字段的输入框代码*****************")}
	${lz:set("注释","before$csciAnswer和after$csciAnswer变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csciAnswer==true">
	${before$csciAnswer}
	<dl class="csciAnswer " major  ref="csciAnswer" >
		<dt>接&nbsp;&nbsp;听&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csciAnswer==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCallIn$csciAnswer!=null">${csCallIn$csciAnswer}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csciAnswer" name="csCallIn.csciAnswer">
		 				<option selected value="${csCallIn.csciAnswer}">
		 					${get:SrvUser(csCallIn.csciAnswer).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csciAnswer').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csciAnswer').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****接听人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csciAnswer">${csCallIn.csciAnswer}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csCallIn.csciAnswer}',{ctrl:{editable:false,visible:true}})">
			 ${csCallIn.csciAnswer$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csciAnswer}
	</s:if>
	
	${lz:set("注释","*****************通话时长字段的输入框代码*****************")}
	${lz:set("注释","before$csciHowLong和after$csciHowLong变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csciHowLong==true">
	${before$csciHowLong}
	<dl class="csciHowLong " major  ref="csciHowLong" >
		<dt>通话时长:</dt>
		<s:if test="#request.CTRL.e.csciHowLong==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csCallIn$csciHowLong!=null">${csCallIn$csciHowLong}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csCallIn.csciHowLong" id="csciHowLong"  value="${csCallIn.csciHowLong}"/>
	 	 </s:else>
	 	 毫秒
	 	 
	 	 <em>请输入客户来电的通话时长</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****通话时长字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csciHowLong">${csCallIn.csciHowLong}</textarea>
		 		<span>
		 	
			 ${csCallIn.csciHowLong$}
	 	 毫秒 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csciHowLong}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csciUpdateTime和after$csciUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csciUpdateTime==true">
	${before$csciUpdateTime}
	<dl class="csciUpdateTime " major  ref="csciUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csciUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCallIn$csciUpdateTime!=null">${csCallIn$csciUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCallIn.csciUpdateTime" id="csciUpdateTime"  value="<s:date name="csCallIn.csciUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户来电的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csciUpdateTime">${csCallIn.csciUpdateTime}</textarea>
		 		<span>
		 	
			 ${csCallIn.csciUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csciUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************来电时间字段的输入框代码*****************")}
	${lz:set("注释","before$csciAddTime和after$csciAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csciAddTime==true">
	${before$csciAddTime}
	<dl class="csciAddTime " major  ref="csciAddTime" >
		<dt>来电时间:</dt>
		<s:if test="#request.CTRL.e.csciAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCallIn$csciAddTime!=null">${csCallIn$csciAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCallIn.csciAddTime" id="csciAddTime"  value="<s:date name="csCallIn.csciAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户来电的来电时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****来电时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csciAddTime">${csCallIn.csciAddTime}</textarea>
		 		<span>
		 	
			 ${csCallIn.csciAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csciAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csciStatus和after$csciStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csciStatus==true">
	${before$csciStatus}
	<dl class="csciStatus " major  ref="csciStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csciStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCallIn$csciStatus!=null">${csCallIn$csciStatus}</s:if><s:else>
		 	<select class="narrow" id="csciStatus" name="csCallIn.csciStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csCallIn.csciStatus==1?"selected":""}>已接听</option>
				<option value="0" ${csCallIn.csciStatus==0?"selected":""}>未接听</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客户来电的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csciStatus">${csCallIn.csciStatus}</textarea>
		 		<span>
		 	
			 ${csCallIn.csciStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csciStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csCallIn.csciId}" name="csCallIn.csciId" id="csciId" />
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