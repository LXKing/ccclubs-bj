<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/configurator/wx/wxmt.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/configurator/wx/wxmt.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csWxMt.cswmId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cswmId,cswmHost,cswmPlatform,cswmOpenid,cswmMember,cswmMsgFlag,cswmContentType,cswmRelate,cswmContent,cswmMo,cswmMask,cswmSuccess,cswmStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cswmId,cswmHost,cswmPlatform,cswmOpenid,cswmMember,cswmMsgFlag,cswmContentType,cswmRelate,cswmContent,cswmMo,cswmMask,cswmSuccess,cswmStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cswmId,cswmHost,cswmPlatform,cswmOpenid,cswmMember,cswmMsgFlag,cswmContentType,cswmRelate,cswmContent,cswmMo,cswmMask,cswmSuccess,cswmStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cswmId,cswmHost,cswmPlatform,cswmOpenid,cswmMember,cswmMsgFlag,cswmContentType,cswmRelate,cswmContent,cswmMo,cswmMask,cswmSuccess,cswmStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"微信下行编辑":CTRL.title}</title>
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
	$("#wxmtForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cswmId==true">
		${lz:set("haveEditable",true)}
		,"csWxMt.cswmId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmHost==true">
		${lz:set("haveEditable",true)}
		,"csWxMt.cswmHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmPlatform==true">
		${lz:set("haveEditable",true)}
		,"csWxMt.cswmPlatform":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择平台类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmOpenid==true">
		${lz:set("haveEditable",true)}
		,"csWxMt.cswmOpenid":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "三方标识最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmMember==true">
		${lz:set("haveEditable",true)}
		,"csWxMt.cswmMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmMsgFlag==true">
		${lz:set("haveEditable",true)}
		,"csWxMt.cswmMsgFlag":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "分类标识最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmContentType==true">
		${lz:set("haveEditable",true)}
		,"csWxMt.cswmContentType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择内容类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmRelate==true">
		${lz:set("haveEditable",true)}
		,"csWxMt.cswmRelate":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmContent==true">
		${lz:set("haveEditable",true)}
		,"csWxMt.cswmContent":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "消息内容最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmMo==true">
		${lz:set("haveEditable",true)}
		,"csWxMt.cswmMo":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmSendTime==true">
		${lz:set("haveEditable",true)}
		,"csWxMt.cswmSendTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmMask==true">
		${lz:set("haveEditable",true)}
		,"csWxMt.cswmMask":function(el){
				var bitValue = 0;
				$("input[ref='cswmMask']").each(function(){
					if($(this).is(":checked"))
						bitValue|=$(this).val();
				});
				$("#cswmMask").val(bitValue);
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmSuccess==true">
		${lz:set("haveEditable",true)}
		,"csWxMt.cswmSuccess":function(el){
				var bitValue = 0;
				$("input[ref='cswmSuccess']").each(function(){
					if($(this).is(":checked"))
						bitValue|=$(this).val();
				});
				$("#cswmSuccess").val(bitValue);
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmAddTime==true">
		${lz:set("haveEditable",true)}
		,"csWxMt.cswmAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmStatus==true">
		${lz:set("haveEditable",true)}
		,"csWxMt.cswmStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择数据状态";
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
	<form class="form editform" ref="CsWxMt" id="wxmtForm" name="wxmtForm" action="${empty CTRL.action?"wxmt_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写微信下行相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csWxMtToken}" name="csWxMtToken" id="csWxMtToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csWxMt.cswmId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cswmId和after$cswmId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmId==true">
	${before$cswmId}
	<dl class="cswmId ${CTRL.e.cswmId?"hidden":""}" major  ref="cswmId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cswmId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csWxMt$cswmId!=null">${csWxMt$cswmId}</s:if><s:else>
			<input type="hidden" value="${csWxMt.cswmId}" name="csWxMt.cswmId" id="cswmId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmId">${csWxMt.cswmId}</textarea>
		 		<span>
		 	
			 ${csWxMt.cswmId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cswmHost和after$cswmHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmHost==true">
	${before$cswmHost}
	<dl class="cswmHost " major  ref="cswmHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cswmHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csWxMt$cswmHost!=null">${csWxMt$cswmHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cswmHost" name="csWxMt.cswmHost">
		 				<option selected value="${csWxMt.cswmHost}">
		 					${get:SrvHost(csWxMt.cswmHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cswmHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cswmHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmHost">${csWxMt.cswmHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csWxMt.cswmHost}',{ctrl:{editable:false,visible:true}})">
			 ${csWxMt.cswmHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmHost}
	</s:if>
	
	${lz:set("注释","*****************平台类型字段的输入框代码*****************")}
	${lz:set("注释","before$cswmPlatform和after$cswmPlatform变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmPlatform==true">
	${before$cswmPlatform}
	<dl class="cswmPlatform " major  ref="cswmPlatform" >
		<dt>平台类型:</dt>
		<s:if test="#request.CTRL.e.cswmPlatform==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csWxMt$cswmPlatform!=null">${csWxMt$cswmPlatform}</s:if><s:else>
		 	<select class="narrow" id="cswmPlatform" name="csWxMt.cswmPlatform">
		 		<option value="">请选择</option>
				<option value="0" ${csWxMt.cswmPlatform==0?"selected":""}>全部</option>
				<option value="1" ${csWxMt.cswmPlatform==1?"selected":""}>微信</option>
				<option value="2" ${csWxMt.cswmPlatform==2?"selected":""}>支付宝</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择微信下行的平台类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****平台类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmPlatform">${csWxMt.cswmPlatform}</textarea>
		 		<span>
		 	
			 ${csWxMt.cswmPlatform$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmPlatform}
	</s:if>
	
	${lz:set("注释","*****************三方标识字段的输入框代码*****************")}
	${lz:set("注释","before$cswmOpenid和after$cswmOpenid变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmOpenid==true">
	${before$cswmOpenid}
	<dl class="cswmOpenid " minor  ref="cswmOpenid" >
		<dt>三方标识:</dt>
		<s:if test="#request.CTRL.e.cswmOpenid==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csWxMt$cswmOpenid!=null">${csWxMt$cswmOpenid}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csWxMt.cswmOpenid" id="cswmOpenid"  value="${csWxMt.cswmOpenid}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入微信下行的三方标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****三方标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmOpenid">${csWxMt.cswmOpenid}</textarea>
		 		<span>
		 	
			 ${csWxMt.cswmOpenid$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmOpenid}
	</s:if>
	
	${lz:set("注释","*****************关联会员字段的输入框代码*****************")}
	${lz:set("注释","before$cswmMember和after$cswmMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmMember==true">
	${before$cswmMember}
	<dl class="cswmMember " major  ref="cswmMember" >
		<dt>关联会员:</dt>
		<s:if test="#request.CTRL.e.cswmMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csWxMt$cswmMember!=null">${csWxMt$cswmMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={cswmHost}" type="text" id="cswmMember" name="csWxMt.cswmMember" text="${get:CsMember(csWxMt.cswmMember).csmName}" value="${csWxMt.cswmMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cswmMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#cswmMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmMember">${csWxMt.cswmMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csWxMt.cswmMember}',{ctrl:{editable:false,visible:true}})">
			 ${csWxMt.cswmMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmMember}
	</s:if>
	
	${lz:set("注释","*****************分类标识字段的输入框代码*****************")}
	${lz:set("注释","before$cswmMsgFlag和after$cswmMsgFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmMsgFlag==true">
	${before$cswmMsgFlag}
	<dl class="cswmMsgFlag " major  ref="cswmMsgFlag" >
		<dt>分类标识:</dt>
		<s:if test="#request.CTRL.e.cswmMsgFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csWxMt$cswmMsgFlag!=null">${csWxMt$cswmMsgFlag}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csWxMt.cswmMsgFlag" id="cswmMsgFlag"  value="${csWxMt.cswmMsgFlag}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入微信下行的分类标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****分类标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmMsgFlag">${csWxMt.cswmMsgFlag}</textarea>
		 		<span>
		 	
			 ${csWxMt.cswmMsgFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmMsgFlag}
	</s:if>
	
	${lz:set("注释","*****************内容类型字段的输入框代码*****************")}
	${lz:set("注释","before$cswmContentType和after$cswmContentType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmContentType==true">
	${before$cswmContentType}
	<dl class="cswmContentType " major  ref="cswmContentType" >
		<dt>内容类型:</dt>
		<s:if test="#request.CTRL.e.cswmContentType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csWxMt$cswmContentType!=null">${csWxMt$cswmContentType}</s:if><s:else>
		 	<select class="narrow" id="cswmContentType" name="csWxMt.cswmContentType">
		 		<option value="">请选择</option>
				<option value="0" ${csWxMt.cswmContentType==0?"selected":""}>文本</option>
				<option value="1" ${csWxMt.cswmContentType==1?"selected":""}>图片</option>
				<option value="2" ${csWxMt.cswmContentType==2?"selected":""}>图文</option>
				<option value="3" ${csWxMt.cswmContentType==3?"selected":""}>模板</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择微信下行的内容类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****内容类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmContentType">${csWxMt.cswmContentType}</textarea>
		 		<span>
		 	
			 ${csWxMt.cswmContentType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmContentType}
	</s:if>
	
	${lz:set("注释","*****************关联对象字段的输入框代码*****************")}
	${lz:set("注释","before$cswmRelate和after$cswmRelate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmRelate==true">
	${before$cswmRelate}
	<dl class="cswmRelate " major  ref="cswmRelate" >
		<dt>关联对象:</dt>
		<s:if test="#request.CTRL.e.cswmRelate==true">
		${lz:set("haveEditable",true)}
		<dd input="generic">
		<s:if test="#request.csWxMt$cswmRelate!=null">${csWxMt$cswmRelate}</s:if><s:else>
		 	<input title="请输入动态查询" class="combox narrow" generic="cswmRelateGeneric" size="16" type="text" id="cswmRelate" name="csWxMt.cswmRelate" text="${csWxMt.cswmRelate$}" value="${csWxMt.cswmRelate}"/>
		 	${lz:set("models",get:models("CsTrouble,CsViolat,CsOrder"))}
		 	<select id="cswmRelateGeneric" style="display:none;">
		 		<option value="">--选择模块--</option>
		 		<s:iterator value="#request.models" id="item" status="i">
		 		<option value="${item.name}:${basePath}admin/${item.namespace}_query.do?value={param}">${item.caption}</option>
		 		</s:iterator>
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="lookup('#${NAME}')"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联对象字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmRelate">${csWxMt.cswmRelate}</textarea>
		 		<span>
		 	
			 ${csWxMt.cswmRelate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmRelate}
	</s:if>
	
	${lz:set("注释","*****************消息内容字段的输入框代码*****************")}
	${lz:set("注释","before$cswmContent和after$cswmContent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmContent==true">
	${before$cswmContent}
	<dl class="cswmContent " minor  ref="cswmContent" style="width:98%;">
		<dt>消息内容:</dt>
		<s:if test="#request.CTRL.e.cswmContent==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csWxMt$cswmContent!=null">${csWxMt$cswmContent}</s:if><s:else>
		 	<textarea class="input wide"  id="cswmContent" name="csWxMt.cswmContent" rows="5">${csWxMt.cswmContent}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>直接保存xml或json</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****消息内容字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csWxMt.cswmContent$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmContent}
	</s:if>
	
	${lz:set("注释","*****************关联上行字段的输入框代码*****************")}
	${lz:set("注释","before$cswmMo和after$cswmMo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmMo==true">
	${before$cswmMo}
	<dl class="cswmMo " minor  ref="cswmMo" >
		<dt>关联上行:</dt>
		<s:if test="#request.CTRL.e.cswmMo==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csWxMt$cswmMo!=null">${csWxMt$cswmMo}</s:if><s:else>
		 			<input title="请输入微信上行编号进行查询" class="combox narrow" action="${basePath}${proname}/configurator/wx/wxmo_query.do?value={param}&cswmHost={cswmHost}" type="text" id="cswmMo" name="csWxMt.cswmMo" text="${get:CsWxMo(csWxMt.cswmMo).cswmId}" value="${csWxMt.cswmMo}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cswmMo').val())==''){return;};window.href('${basePath}${proname}/configurator/wx/wxmo_details.do?key='+$('#cswmMo').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联上行字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmMo">${csWxMt.cswmMo}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/wx/wxmo_details.do?key=${csWxMt.cswmMo}',{ctrl:{editable:false,visible:true}})">
			 ${csWxMt.cswmMo$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmMo}
	</s:if>
	
	${lz:set("注释","*****************发送时间字段的输入框代码*****************")}
	${lz:set("注释","before$cswmSendTime和after$cswmSendTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmSendTime==true">
	${before$cswmSendTime}
	<dl class="cswmSendTime " minor  ref="cswmSendTime" >
		<dt>发送时间:</dt>
		<s:if test="#request.CTRL.e.cswmSendTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csWxMt$cswmSendTime!=null">${csWxMt$cswmSendTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csWxMt.cswmSendTime" id="cswmSendTime"  value="<s:date name="csWxMt.cswmSendTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择微信下行的发送时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****发送时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmSendTime">${csWxMt.cswmSendTime}</textarea>
		 		<span>
		 	
			 ${csWxMt.cswmSendTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmSendTime}
	</s:if>
	
	${lz:set("注释","*****************发送状态字段的输入框代码*****************")}
	${lz:set("注释","before$cswmMask和after$cswmMask变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmMask==true">
	${before$cswmMask}
	<dl class="cswmMask " major  ref="cswmMask" style="width:98%;">
		<dt>发送状态:</dt>
		<s:if test="#request.CTRL.e.cswmMask==true">
		${lz:set("haveEditable",true)}
		<dd input="checkbox">
		<s:if test="#request.csWxMt$cswmMask!=null">${csWxMt$cswmMask}</s:if><s:else>
		 	<div class='wide input'>	
		 	&nbsp; 	
		 	<input type="hidden" value="#0#" name="csWxMt.cswmMask" id="cswmMask"/>
			<label><input type="checkbox" ${lz:bitin(csWxMt.cswmMask,1) ? "checked='checked'" : ""} value="1" ref="cswmMask"/>微信已发&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:bitin(csWxMt.cswmMask,2) ? "checked='checked'" : ""} value="2" ref="cswmMask"/>支付宝已发&nbsp;&nbsp;&nbsp;</label>
		 </div>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择微信下行的发送状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****发送状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cswmMask">${csWxMt.cswmMask}</textarea>
		 		<span>
		 	
			 ${csWxMt.cswmMask$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmMask}
	</s:if>
	
	${lz:set("注释","*****************成功状态字段的输入框代码*****************")}
	${lz:set("注释","before$cswmSuccess和after$cswmSuccess变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmSuccess==true">
	${before$cswmSuccess}
	<dl class="cswmSuccess " major  ref="cswmSuccess" style="width:98%;">
		<dt>成功状态:</dt>
		<s:if test="#request.CTRL.e.cswmSuccess==true">
		${lz:set("haveEditable",true)}
		<dd input="checkbox">
		<s:if test="#request.csWxMt$cswmSuccess!=null">${csWxMt$cswmSuccess}</s:if><s:else>
		 	<div class='wide input'>	
		 	&nbsp; 	
		 	<input type="hidden" value="#0#" name="csWxMt.cswmSuccess" id="cswmSuccess"/>
			<label><input type="checkbox" ${lz:bitin(csWxMt.cswmSuccess,1) ? "checked='checked'" : ""} value="1" ref="cswmSuccess"/>微信成功&nbsp;&nbsp;&nbsp;</label>
			<label><input type="checkbox" ${lz:bitin(csWxMt.cswmSuccess,2) ? "checked='checked'" : ""} value="2" ref="cswmSuccess"/>支付宝成功&nbsp;&nbsp;&nbsp;</label>
		 </div>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择微信下行的成功状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****成功状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cswmSuccess">${csWxMt.cswmSuccess}</textarea>
		 		<span>
		 	
			 ${csWxMt.cswmSuccess$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmSuccess}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cswmAddTime和after$cswmAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmAddTime==true">
	${before$cswmAddTime}
	<dl class="cswmAddTime " major  ref="cswmAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cswmAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csWxMt$cswmAddTime!=null">${csWxMt$cswmAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csWxMt.cswmAddTime" id="cswmAddTime"  value="<s:date name="csWxMt.cswmAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择微信下行的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmAddTime">${csWxMt.cswmAddTime}</textarea>
		 		<span>
		 	
			 ${csWxMt.cswmAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmAddTime}
	</s:if>
	
	${lz:set("注释","*****************数据状态字段的输入框代码*****************")}
	${lz:set("注释","before$cswmStatus和after$cswmStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmStatus==true">
	${before$cswmStatus}
	<dl class="cswmStatus " major  ref="cswmStatus" >
		<dt>数据状态:</dt>
		<s:if test="#request.CTRL.e.cswmStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csWxMt$cswmStatus!=null">${csWxMt$cswmStatus}</s:if><s:else>
		 	<select class="narrow" id="cswmStatus" name="csWxMt.cswmStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csWxMt.cswmStatus==0?"selected":""}>默认</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择微信下行的数据状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****数据状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmStatus">${csWxMt.cswmStatus}</textarea>
		 		<span>
		 	
			 ${csWxMt.cswmStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csWxMt.cswmId}" name="csWxMt.cswmId" id="cswmId" />
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