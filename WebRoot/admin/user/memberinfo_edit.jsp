<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/user/memberinfo.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/user/memberinfo.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csMemberInfo.csmiId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csmiId,csmiHost,csmiName,csmiMemberId,csmiCertifyType,csmiCertifyNum,csmiCertifyImage,csmiDriverNum,csmiDriverImage,csmiSex,csmiStatus,csmiOnCertifyImage,csmiProofOfEmployment",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csmiId,csmiHost,csmiName,csmiMemberId,csmiCertifyType,csmiCertifyNum,csmiCertifyImage,csmiDriverNum,csmiDriverImage,csmiSex,csmiStatus,csmiOnCertifyImage,csmiProofOfEmployment",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csmiId,csmiHost,csmiName,csmiMemberId,csmiCertifyType,csmiCertifyNum,csmiCertifyImage,csmiDriverNum,csmiDriverImage,csmiSex,csmiStatus,csmiOnCertifyImage,csmiProofOfEmployment",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csmiId,csmiHost,csmiName,csmiMemberId,csmiCertifyType,csmiCertifyNum,csmiCertifyImage,csmiDriverNum,csmiDriverImage,csmiSex,csmiUpdateTime,csmiAddTime,csmiStatus,csmiOnCertifyImage,csmiProofOfEmployment",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"会员信息编辑":CTRL.title}</title>
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
	$("#memberinfoForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csmiId==true">
		${lz:set("haveEditable",true)}
		,"csMemberInfo.csmiId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiHost==true">
		${lz:set("haveEditable",true)}
		,"csMemberInfo.csmiHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiName==true">
		${lz:set("haveEditable",true)}
		,"csMemberInfo.csmiName":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "真实姓名最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiMemberId==true">
		${lz:set("haveEditable",true)}
		,"csMemberInfo.csmiMemberId":function(el){
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("memberinfo_query.do",{exists:true,csmiMemberId:el.value});
				if(exists && exists.length>0){
					if(${csMemberInfo.csmiId==null} || exists[0].value!=$("#csmiId").val())
						return "会员帐号已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiCertifyType==true">
		${lz:set("haveEditable",true)}
		,"csMemberInfo.csmiCertifyType":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiCertifyNum==true">
		${lz:set("haveEditable",true)}
		,"csMemberInfo.csmiCertifyNum":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "证件号码最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiCertifyImage==true">
		${lz:set("haveEditable",true)}
		,"csMemberInfo.csmiCertifyImage":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiDriverNum==true">
		${lz:set("haveEditable",true)}
		,"csMemberInfo.csmiDriverNum":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "驾证号码最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiDriverImage==true">
		${lz:set("haveEditable",true)}
		,"csMemberInfo.csmiDriverImage":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiSex==true">
		${lz:set("haveEditable",true)}
		,"csMemberInfo.csmiSex":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiBirthday==true">
		${lz:set("haveEditable",true)}
		,"csMemberInfo.csmiBirthday":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiQq==true">
		${lz:set("haveEditable",true)}
		,"csMemberInfo.csmiQq":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "QQ最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiNation==true">
		${lz:set("haveEditable",true)}
		,"csMemberInfo.csmiNation":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "民族最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiAddress==true">
		${lz:set("haveEditable",true)}
		,"csMemberInfo.csmiAddress":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "邮寄地址最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiCompany==true">
		${lz:set("haveEditable",true)}
		,"csMemberInfo.csmiCompany":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "就职公司最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiPlace==true">
		${lz:set("haveEditable",true)}
		,"csMemberInfo.csmiPlace":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiPerson==true">
		${lz:set("haveEditable",true)}
		,"csMemberInfo.csmiPerson":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "紧急联系人最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiContact==true">
		${lz:set("haveEditable",true)}
		,"csMemberInfo.csmiContact":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "紧急联系号码最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiRelation==true">
		${lz:set("haveEditable",true)}
		,"csMemberInfo.csmiRelation":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "联系人关系最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csMemberInfo.csmiUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiAddTime==true">
		${lz:set("haveEditable",true)}
		,"csMemberInfo.csmiAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiStatus==true">
		${lz:set("haveEditable",true)}
		,"csMemberInfo.csmiStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择状态";
		}
		</s:if>	
		
		<s:if test="#request.CTRL.e.csmiOnCertifyImage==true">
		${lz:set("haveEditable",true)}
		,"csMemberInfo.csmiOnCertifyImage":function(el){
		}
		</s:if>
		<s:if test="#request.CTRL.e.csmiProofOfEmployment==true">
		${lz:set("haveEditable",true)}
		,"csMemberInfo.csmiProofOfEmployment":function(el){
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
	<form class="form editform" ref="CsMemberInfo" id="memberinfoForm" name="memberinfoForm" action="${empty CTRL.action?"memberinfo_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写会员信息相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csMemberInfoToken}" name="csMemberInfoToken" id="csMemberInfoToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csMemberInfo.csmiId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csmiId和after$csmiId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiId==true">
	${before$csmiId}
	<dl class="csmiId ${CTRL.e.csmiId?"hidden":""}" major  ref="csmiId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csmiId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csMemberInfo$csmiId!=null">${csMemberInfo$csmiId}</s:if><s:else>
			<input type="hidden" value="${csMemberInfo.csmiId}" name="csMemberInfo.csmiId" id="csmiId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiId">${csMemberInfo.csmiId}</textarea>
		 		<span>
		 	
			 ${csMemberInfo.csmiId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csmiHost和after$csmiHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiHost==true">
	${before$csmiHost}
	<dl class="csmiHost " major  ref="csmiHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csmiHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csMemberInfo$csmiHost!=null">${csMemberInfo$csmiHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csmiHost" name="csMemberInfo.csmiHost">
		 				<option selected value="${csMemberInfo.csmiHost}">
		 					${get:SrvHost(csMemberInfo.csmiHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmiHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csmiHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiHost">${csMemberInfo.csmiHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csMemberInfo.csmiHost}',{ctrl:{editable:false,visible:true}})">
			 ${csMemberInfo.csmiHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiHost}
	</s:if>
	
	${lz:set("注释","*****************真实姓名字段的输入框代码*****************")}
	${lz:set("注释","before$csmiName和after$csmiName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiName==true">
	${before$csmiName}
	<dl class="csmiName " major  ref="csmiName" >
		<dt>真实姓名:</dt>
		<s:if test="#request.CTRL.e.csmiName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMemberInfo$csmiName!=null">${csMemberInfo$csmiName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMemberInfo.csmiName" id="csmiName"  value="${csMemberInfo.csmiName}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员信息的真实姓名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****真实姓名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiName">${csMemberInfo.csmiName}</textarea>
		 		<span>
		 	
			 ${csMemberInfo.csmiName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiName}
	</s:if>
	
	${lz:set("注释","*****************会员帐号字段的输入框代码*****************")}
	${lz:set("注释","before$csmiMemberId和after$csmiMemberId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiMemberId==true">
	${before$csmiMemberId}
	<dl class="csmiMemberId " major  ref="csmiMemberId" >
		<dt>会员帐号:</dt>
		<s:if test="#request.CTRL.e.csmiMemberId==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csMemberInfo$csmiMemberId!=null">${csMemberInfo$csmiMemberId}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={csmiHost}" type="text" id="csmiMemberId" name="csMemberInfo.csmiMemberId" text="${get:CsMember(csMemberInfo.csmiMemberId).csmName}" value="${csMemberInfo.csmiMemberId}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmiMemberId').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csmiMemberId').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****会员帐号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiMemberId">${csMemberInfo.csmiMemberId}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csMemberInfo.csmiMemberId}',{ctrl:{editable:false,visible:true}})">
			 ${csMemberInfo.csmiMemberId$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiMemberId}
	</s:if>
	
	${lz:set("注释","*****************证件类型字段的输入框代码*****************")}
	${lz:set("注释","before$csmiCertifyType和after$csmiCertifyType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiCertifyType==true">
	${before$csmiCertifyType}
	<dl class="csmiCertifyType " major  ref="csmiCertifyType" >
		<dt>证件类型:</dt>
		<s:if test="#request.CTRL.e.csmiCertifyType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMemberInfo$csmiCertifyType!=null">${csMemberInfo$csmiCertifyType}</s:if><s:else>
		 	<select class="narrow" id="csmiCertifyType" name="csMemberInfo.csmiCertifyType">
		 		<option value="">请选择</option>
				<option value="1" ${csMemberInfo.csmiCertifyType==1?"selected":""}>身份证</option>
				<option value="2" ${csMemberInfo.csmiCertifyType==2?"selected":""}>护照</option>
				<option value="3" ${csMemberInfo.csmiCertifyType==3?"selected":""}>军官证</option>
				<option value="4" ${csMemberInfo.csmiCertifyType==4?"selected":""}>台胞证</option>
				<option value="5" ${csMemberInfo.csmiCertifyType==5?"selected":""}>港澳通行证</option>
				<option value="6" ${csMemberInfo.csmiCertifyType==6?"selected":""}>营业执照</option>
				<option value="7" ${csMemberInfo.csmiCertifyType==7?"selected":""}>公务员证</option>
				<option value="9" ${csMemberInfo.csmiCertifyType==9?"selected":""}>其它</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择会员信息的证件类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****证件类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiCertifyType">${csMemberInfo.csmiCertifyType}</textarea>
		 		<span>
		 	
			 ${csMemberInfo.csmiCertifyType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiCertifyType}
	</s:if>
	
	${lz:set("注释","*****************证件号码字段的输入框代码*****************")}
	${lz:set("注释","before$csmiCertifyNum和after$csmiCertifyNum变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiCertifyNum==true">
	${before$csmiCertifyNum}
	<dl class="csmiCertifyNum " minor  ref="csmiCertifyNum" >
		<dt>证件号码:</dt>
		<s:if test="#request.CTRL.e.csmiCertifyNum==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMemberInfo$csmiCertifyNum!=null">${csMemberInfo$csmiCertifyNum}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMemberInfo.csmiCertifyNum" id="csmiCertifyNum"  value="${csMemberInfo.csmiCertifyNum}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员信息的证件号码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****证件号码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiCertifyNum">${csMemberInfo.csmiCertifyNum}</textarea>
		 		<span>
		 	
			 ${csMemberInfo.csmiCertifyNum$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiCertifyNum}
	</s:if>
	
	
	
	
	${lz:set("注释","*****************证件图片字段的输入框代码*****************")}
	${lz:set("注释","before$csmiOnCertifyImage和after$csmiOnCertifyImage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiOnCertifyImage==true">
	${before$csmiOnCertifyImage}
	<dl class="csmiOnCertifyImage " minor  ref="csmiOnCertifyImage" style="width:98%;">
		<dt>证件正面:</dt>
		<s:if test="#request.CTRL.e.csmiOnCertifyImage==true">
		${lz:set("haveEditable",true)}
		<dd input="image">
		<s:if test="#request.csMemberInfo$csmiOnCertifyImage!=null">${csMemberInfo$csmiOnCertifyImage}</s:if><s:else>
		    <input type="text" class="input" maxlength="128" size="32" name="csMemberInfo.csmiOnCertifyImage" id="csmiOnCertifyImage"  value="${csMemberInfo.csmiOnCertifyImage}"/>
			<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#csmiOnCertifyImage').val(url)}})" class="button">设置图片</button>
			<button type="button" onclick="$('#csmiOnCertifyImage').val('')" class="button">删除图片</button>
			<button type="button" onclick="$.thumb({url:$('#csmiOnCertifyImage').val()})" class="button">查看图片</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传会员信息的证件图片</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****证件图片字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csmiOnCertifyImage">${csMemberInfo.csmiOnCertifyImage}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(csMemberInfo.csmiOnCertifyImage))}
		 	<s:if test="#request.isVacant==false">
		 	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="${csMemberInfo.csmiOnCertifyImage}"/>
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiOnCertifyImage}
	</s:if>
	
	
	${lz:set("注释","*****************证件图片字段的输入框代码*****************")}
	${lz:set("注释","before$csmiCertifyImage和after$csmiCertifyImage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiCertifyImage==true">
	${before$csmiCertifyImage}
	<dl class="csmiCertifyImage " minor  ref="csmiCertifyImage" style="width:98%;">
		<dt>证件反面:</dt>
		<s:if test="#request.CTRL.e.csmiCertifyImage==true">
		${lz:set("haveEditable",true)}
		<dd input="image">
		<s:if test="#request.csMemberInfo$csmiCertifyImage!=null">${csMemberInfo$csmiCertifyImage}</s:if><s:else>
		    <input type="text" class="input" maxlength="128" size="32" name="csMemberInfo.csmiCertifyImage" id="csmiCertifyImage"  value="${csMemberInfo.csmiCertifyImage}"/>
			<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#csmiCertifyImage').val(url)}})" class="button">设置图片</button>
			<button type="button" onclick="$('#csmiCertifyImage').val('')" class="button">删除图片</button>
			<button type="button" onclick="$.thumb({url:$('#csmiCertifyImage').val()})" class="button">查看图片</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传会员信息的证件图片</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****证件图片字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csmiCertifyImage">${csMemberInfo.csmiCertifyImage}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(csMemberInfo.csmiCertifyImage))}
		 	<s:if test="#request.isVacant==false">
		 	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="${csMemberInfo.csmiCertifyImage}"/>
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiCertifyImage}
	</s:if>
	
	
	
	
	
	
	${lz:set("注释","*****************驾证号码字段的输入框代码*****************")}
	${lz:set("注释","before$csmiDriverNum和after$csmiDriverNum变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiDriverNum==true">
	${before$csmiDriverNum}
	<dl class="csmiDriverNum " minor  ref="csmiDriverNum" >
		<dt>驾证号码:</dt>
		<s:if test="#request.CTRL.e.csmiDriverNum==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMemberInfo$csmiDriverNum!=null">${csMemberInfo$csmiDriverNum}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMemberInfo.csmiDriverNum" id="csmiDriverNum"  value="${csMemberInfo.csmiDriverNum}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员信息的驾证号码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****驾证号码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiDriverNum">${csMemberInfo.csmiDriverNum}</textarea>
		 		<span>
		 	
			 ${csMemberInfo.csmiDriverNum$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiDriverNum}
	</s:if>
	
	${lz:set("注释","*****************驾证图片字段的输入框代码*****************")}
	${lz:set("注释","before$csmiDriverImage和after$csmiDriverImage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiDriverImage==true">
	${before$csmiDriverImage}
	<dl class="csmiDriverImage " minor  ref="csmiDriverImage" style="width:98%;">
		<dt>驾证图片:</dt>
		<s:if test="#request.CTRL.e.csmiDriverImage==true">
		${lz:set("haveEditable",true)}
		<dd input="image">
		<s:if test="#request.csMemberInfo$csmiDriverImage!=null">${csMemberInfo$csmiDriverImage}</s:if><s:else>
		    <input type="text" class="input" maxlength="128" size="32" name="csMemberInfo.csmiDriverImage" id="csmiDriverImage"  value="${csMemberInfo.csmiDriverImage}"/>
			<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#csmiDriverImage').val(url)}})" class="button">设置图片</button>
			<button type="button" onclick="$('#csmiDriverImage').val('')" class="button">删除图片</button>
			<button type="button" onclick="$.thumb({url:$('#csmiDriverImage').val()})" class="button">查看图片</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传会员信息的驾证图片</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****驾证图片字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csmiDriverImage">${csMemberInfo.csmiDriverImage}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(csMemberInfo.csmiDriverImage))}
		 	<s:if test="#request.isVacant==false">
		 	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="${csMemberInfo.csmiDriverImage}"/>
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiDriverImage}
	</s:if>
	
	${lz:set("注释","*****************工作证图片字段的输入框代码*****************")}
	${lz:set("注释","before$csmiProofOfEmployment和after$csmiProofOfEmployment变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiProofOfEmployment==true">
	${before$csmiProofOfEmployment}
	<dl class="csmiDriverImage " minor  ref="csmiProofOfEmployment" style="width:98%;">
		<dt>工作证图片:</dt>
		<s:if test="#request.CTRL.e.csmiProofOfEmployment==true">
		${lz:set("haveEditable",true)}
		<dd input="image">
		<s:if test="#request.csMemberInfo$csmiProofOfEmployment!=null">${csMemberInfo$csmiProofOfEmployment}</s:if><s:else>
		    <input type="text" class="input" maxlength="128" size="32" name="csMemberInfo.csmiProofOfEmployment" id="csmiProofOfEmployment"  value="${csMemberInfo.csmiProofOfEmployment}"/>
			<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#csmiProofOfEmployment').val(url)}})" class="button">设置图片</button>
			<button type="button" onclick="$('#csmiProofOfEmployment').val('')" class="button">删除图片</button>
			<button type="button" onclick="$.thumb({url:$('#csmiProofOfEmployment').val()})" class="button">查看图片</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传会员信息的工作证图片</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****工作证图片字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csmiProofOfEmployment">${csMemberInfo.csmiProofOfEmployment}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(csMemberInfo.csmiProofOfEmployment))}
		 	<s:if test="#request.isVacant==false">
		 	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="${csMemberInfo.csmiProofOfEmployment}"/>
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiProofOfEmployment}
	</s:if>
	
	${lz:set("注释","*****************性别字段的输入框代码*****************")}
	${lz:set("注释","before$csmiSex和after$csmiSex变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiSex==true">
	${before$csmiSex}
	<dl class="csmiSex " major  ref="csmiSex" >
		<dt>性　　别:</dt>
		<s:if test="#request.CTRL.e.csmiSex==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMemberInfo$csmiSex!=null">${csMemberInfo$csmiSex}</s:if><s:else>
		 	<select class="narrow" id="csmiSex" name="csMemberInfo.csmiSex">
		 		<option value="">请选择</option>
				<option value="0" ${csMemberInfo.csmiSex==0?"selected":""}>女</option>
				<option value="1" ${csMemberInfo.csmiSex==1?"selected":""}>男</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择会员信息的性别</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****性别字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiSex">${csMemberInfo.csmiSex}</textarea>
		 		<span>
		 	
			 ${csMemberInfo.csmiSex$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiSex}
	</s:if>
	<!--
	${lz:set("注释","*****************生日字段的输入框代码*****************")}
	${lz:set("注释","before$csmiBirthday和after$csmiBirthday变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiBirthday==true">
	${before$csmiBirthday}
	<dl class="csmiBirthday " minor  ref="csmiBirthday" >
		<dt>生　　日:</dt>
		<s:if test="#request.CTRL.e.csmiBirthday==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csMemberInfo$csmiBirthday!=null">${csMemberInfo$csmiBirthday}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csMemberInfo.csmiBirthday" id="csmiBirthday"  value="<s:date name="csMemberInfo.csmiBirthday" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择会员信息的生日</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****生日字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiBirthday">${csMemberInfo.csmiBirthday}</textarea>
		 		<span>
		 	
			 ${csMemberInfo.csmiBirthday$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiBirthday}
	</s:if>
	
	${lz:set("注释","*****************QQ字段的输入框代码*****************")}
	${lz:set("注释","before$csmiQq和after$csmiQq变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiQq==true">
	${before$csmiQq}
	<dl class="csmiQq " major  ref="csmiQq" >
		<dt>Q　　Q:</dt>
		<s:if test="#request.CTRL.e.csmiQq==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMemberInfo$csmiQq!=null">${csMemberInfo$csmiQq}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMemberInfo.csmiQq" id="csmiQq"  value="${csMemberInfo.csmiQq}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员信息的QQ</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****QQ字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiQq">${csMemberInfo.csmiQq}</textarea>
		 		<span>
		 	
			 ${csMemberInfo.csmiQq$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiQq}
	</s:if>
	
	${lz:set("注释","*****************民族字段的输入框代码*****************")}
	${lz:set("注释","before$csmiNation和after$csmiNation变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiNation==true">
	${before$csmiNation}
	<dl class="csmiNation " minor  ref="csmiNation" >
		<dt>民　　族:</dt>
		<s:if test="#request.CTRL.e.csmiNation==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMemberInfo$csmiNation!=null">${csMemberInfo$csmiNation}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMemberInfo.csmiNation" id="csmiNation"  value="${csMemberInfo.csmiNation}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员信息的民族</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****民族字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiNation">${csMemberInfo.csmiNation}</textarea>
		 		<span>
		 	
			 ${csMemberInfo.csmiNation$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiNation}
	</s:if>
	
	${lz:set("注释","*****************邮寄地址字段的输入框代码*****************")}
	${lz:set("注释","before$csmiAddress和after$csmiAddress变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiAddress==true">
	${before$csmiAddress}
	<dl class="csmiAddress " minor  ref="csmiAddress" style="width:98%;">
		<dt>邮寄地址:</dt>
		<s:if test="#request.CTRL.e.csmiAddress==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMemberInfo$csmiAddress!=null">${csMemberInfo$csmiAddress}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csMemberInfo.csmiAddress" id="csmiAddress"  value="${csMemberInfo.csmiAddress}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员信息的邮寄地址</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****邮寄地址字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csmiAddress">${csMemberInfo.csmiAddress}</textarea>
		 		<span>
		 	
			 ${csMemberInfo.csmiAddress$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiAddress}
	</s:if>
	
	${lz:set("注释","*****************就职公司字段的输入框代码*****************")}
	${lz:set("注释","before$csmiCompany和after$csmiCompany变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiCompany==true">
	${before$csmiCompany}
	<dl class="csmiCompany " minor  ref="csmiCompany" style="width:98%;">
		<dt>就职公司:</dt>
		<s:if test="#request.CTRL.e.csmiCompany==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMemberInfo$csmiCompany!=null">${csMemberInfo$csmiCompany}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csMemberInfo.csmiCompany" id="csmiCompany"  value="${csMemberInfo.csmiCompany}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员信息的就职公司</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****就职公司字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csmiCompany">${csMemberInfo.csmiCompany}</textarea>
		 		<span>
		 	
			 ${csMemberInfo.csmiCompany$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiCompany}
	</s:if>
	
	${lz:set("注释","*****************所在地字段的输入框代码*****************")}
	${lz:set("注释","before$csmiPlace和after$csmiPlace变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiPlace==true">
	${before$csmiPlace}
	<dl class="csmiPlace " minor  ref="csmiPlace" >
		<dt>所&nbsp;&nbsp;在&nbsp;&nbsp;地:</dt>
		<s:if test="#request.CTRL.e.csmiPlace==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csMemberInfo$csmiPlace!=null">${csMemberInfo$csmiPlace}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csMemberInfo.csmiPlace" id="csmiPlace"  value="${csMemberInfo.csmiPlace}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员信息的所在地</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所在地字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiPlace">${csMemberInfo.csmiPlace}</textarea>
		 		<span>
		 	
			 ${csMemberInfo.csmiPlace$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiPlace}
	</s:if>
	
	${lz:set("注释","*****************紧急联系人字段的输入框代码*****************")}
	${lz:set("注释","before$csmiPerson和after$csmiPerson变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiPerson==true">
	${before$csmiPerson}
	<dl class="csmiPerson " minor  ref="csmiPerson" >
		<dt>紧急联系人:</dt>
		<s:if test="#request.CTRL.e.csmiPerson==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMemberInfo$csmiPerson!=null">${csMemberInfo$csmiPerson}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMemberInfo.csmiPerson" id="csmiPerson"  value="${csMemberInfo.csmiPerson}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员信息的紧急联系人</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****紧急联系人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiPerson">${csMemberInfo.csmiPerson}</textarea>
		 		<span>
		 	
			 ${csMemberInfo.csmiPerson$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiPerson}
	</s:if>
	
	${lz:set("注释","*****************紧急联系号码字段的输入框代码*****************")}
	${lz:set("注释","before$csmiContact和after$csmiContact变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiContact==true">
	${before$csmiContact}
	<dl class="csmiContact " minor  ref="csmiContact" >
		<dt>紧急联系号码:</dt>
		<s:if test="#request.CTRL.e.csmiContact==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMemberInfo$csmiContact!=null">${csMemberInfo$csmiContact}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMemberInfo.csmiContact" id="csmiContact"  value="${csMemberInfo.csmiContact}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员信息的紧急联系号码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****紧急联系号码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiContact">${csMemberInfo.csmiContact}</textarea>
		 		<span>
		 	
			 ${csMemberInfo.csmiContact$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiContact}
	</s:if>
	
	${lz:set("注释","*****************联系人关系字段的输入框代码*****************")}
	${lz:set("注释","before$csmiRelation和after$csmiRelation变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiRelation==true">
	${before$csmiRelation}
	<dl class="csmiRelation " minor  ref="csmiRelation" >
		<dt>联系人关系:</dt>
		<s:if test="#request.CTRL.e.csmiRelation==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMemberInfo$csmiRelation!=null">${csMemberInfo$csmiRelation}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMemberInfo.csmiRelation" id="csmiRelation"  value="${csMemberInfo.csmiRelation}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入会员信息的联系人关系</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****联系人关系字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiRelation">${csMemberInfo.csmiRelation}</textarea>
		 		<span>
		 	
			 ${csMemberInfo.csmiRelation$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiRelation}
	</s:if>
	-->
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csmiUpdateTime和after$csmiUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiUpdateTime==true">
	${before$csmiUpdateTime}
	<dl class="csmiUpdateTime " major  ref="csmiUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csmiUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csMemberInfo$csmiUpdateTime!=null">${csMemberInfo$csmiUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csMemberInfo.csmiUpdateTime" id="csmiUpdateTime"  value="<s:date name="csMemberInfo.csmiUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员信息的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiUpdateTime">${csMemberInfo.csmiUpdateTime}</textarea>
		 		<span>
		 	
			 ${csMemberInfo.csmiUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csmiAddTime和after$csmiAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiAddTime==true">
	${before$csmiAddTime}
	<dl class="csmiAddTime " major  ref="csmiAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csmiAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csMemberInfo$csmiAddTime!=null">${csMemberInfo$csmiAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csMemberInfo.csmiAddTime" id="csmiAddTime"  value="<s:date name="csMemberInfo.csmiAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员信息的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiAddTime">${csMemberInfo.csmiAddTime}</textarea>
		 		<span>
		 	
			 ${csMemberInfo.csmiAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csmiStatus和after$csmiStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiStatus==true">
	${before$csmiStatus}
	<dl class="csmiStatus " major  ref="csmiStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csmiStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMemberInfo$csmiStatus!=null">${csMemberInfo$csmiStatus}</s:if><s:else>
		 	<select class="narrow" id="csmiStatus" name="csMemberInfo.csmiStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csMemberInfo.csmiStatus==1?"selected":""}>默认</option>
				<option value="2" ${csMemberInfo.csmiStatus==2?"selected":""}>保留</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择会员信息的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiStatus">${csMemberInfo.csmiStatus}</textarea>
		 		<span>
		 	
			 ${csMemberInfo.csmiStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csMemberInfo.csmiId}" name="csMemberInfo.csmiId" id="csmiId" />
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