<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/configurator/wx/wxmo.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/configurator/wx/wxmo.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csWxMo.cswmId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cswmId,cswmHost,cswmPlatform,cswmOpenid,cswmMember,cswmMsgType,cswmContentType,cswmContent,cswmMask,cswmStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cswmId,cswmHost,cswmPlatform,cswmOpenid,cswmMember,cswmMsgType,cswmContentType,cswmContent,cswmMask,cswmStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cswmId,cswmHost,cswmPlatform,cswmOpenid,cswmMember,cswmMsgType,cswmContentType,cswmContent,cswmMask,cswmStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cswmId,cswmHost,cswmPlatform,cswmOpenid,cswmMember,cswmMsgType,cswmContentType,cswmContent,cswmMask,cswmStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"微信上行编辑":CTRL.title}</title>
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
	$("#wxmoForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cswmId==true">
		${lz:set("haveEditable",true)}
		,"csWxMo.cswmId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmHost==true">
		${lz:set("haveEditable",true)}
		,"csWxMo.cswmHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmPlatform==true">
		${lz:set("haveEditable",true)}
		,"csWxMo.cswmPlatform":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择平台类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmOpenid==true">
		${lz:set("haveEditable",true)}
		,"csWxMo.cswmOpenid":function(el){
			if(jQuery.trim(el.value)=="")
				return "三方标识不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "三方标识最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmMember==true">
		${lz:set("haveEditable",true)}
		,"csWxMo.cswmMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmMsgType==true">
		${lz:set("haveEditable",true)}
		,"csWxMo.cswmMsgType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择消息类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmContentType==true">
		${lz:set("haveEditable",true)}
		,"csWxMo.cswmContentType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择内容类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmContent==true">
		${lz:set("haveEditable",true)}
		,"csWxMo.cswmContent":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "消息内容最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmAddTime==true">
		${lz:set("haveEditable",true)}
		,"csWxMo.cswmAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmMask==true">
		${lz:set("haveEditable",true)}
		,"csWxMo.cswmMask":function(el){
				var bitValue = 0;
				$("input[ref='cswmMask']").each(function(){
					if($(this).is(":checked"))
						bitValue|=$(this).val();
				});
				$("#cswmMask").val(bitValue);
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswmStatus==true">
		${lz:set("haveEditable",true)}
		,"csWxMo.cswmStatus":function(el){
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
	<form class="form editform" ref="CsWxMo" id="wxmoForm" name="wxmoForm" action="${empty CTRL.action?"wxmo_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写微信上行相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csWxMoToken}" name="csWxMoToken" id="csWxMoToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csWxMo.cswmId))}		
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
		<s:if test="#request.csWxMo$cswmId!=null">${csWxMo$cswmId}</s:if><s:else>
			<input type="hidden" value="${csWxMo.cswmId}" name="csWxMo.cswmId" id="cswmId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmId">${csWxMo.cswmId}</textarea>
		 		<span>
		 	
			 ${csWxMo.cswmId$}
	 	  
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
		<s:if test="#request.csWxMo$cswmHost!=null">${csWxMo$cswmHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cswmHost" name="csWxMo.cswmHost">
		 				<option selected value="${csWxMo.cswmHost}">
		 					${get:SrvHost(csWxMo.cswmHost).shName}
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
		 		<textarea class="" style="display:none;" id="cswmHost">${csWxMo.cswmHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csWxMo.cswmHost}',{ctrl:{editable:false,visible:true}})">
			 ${csWxMo.cswmHost$}</a>
	 	  
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
		<s:if test="#request.csWxMo$cswmPlatform!=null">${csWxMo$cswmPlatform}</s:if><s:else>
		 	<select class="narrow" id="cswmPlatform" name="csWxMo.cswmPlatform">
		 		<option value="">请选择</option>
				<option value="1" ${csWxMo.cswmPlatform==1?"selected":""}>微信</option>
				<option value="2" ${csWxMo.cswmPlatform==2?"selected":""}>支付宝</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择微信上行的平台类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****平台类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmPlatform">${csWxMo.cswmPlatform}</textarea>
		 		<span>
		 	
			 ${csWxMo.cswmPlatform$}
	 	  
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
	<dl class="cswmOpenid " major  ref="cswmOpenid" >
		<dt>三方标识:</dt>
		<s:if test="#request.CTRL.e.cswmOpenid==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csWxMo$cswmOpenid!=null">${csWxMo$cswmOpenid}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csWxMo.cswmOpenid" id="cswmOpenid"  value="${csWxMo.cswmOpenid}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入微信上行的三方标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****三方标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmOpenid">${csWxMo.cswmOpenid}</textarea>
		 		<span>
		 	
			 ${csWxMo.cswmOpenid$}
	 	  
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
		<s:if test="#request.csWxMo$cswmMember!=null">${csWxMo$cswmMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={cswmHost}" type="text" id="cswmMember" name="csWxMo.cswmMember" text="${get:CsMember(csWxMo.cswmMember).csmName}" value="${csWxMo.cswmMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cswmMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#cswmMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmMember">${csWxMo.cswmMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csWxMo.cswmMember}',{ctrl:{editable:false,visible:true}})">
			 ${csWxMo.cswmMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmMember}
	</s:if>
	
	${lz:set("注释","*****************消息类型字段的输入框代码*****************")}
	${lz:set("注释","before$cswmMsgType和after$cswmMsgType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmMsgType==true">
	${before$cswmMsgType}
	<dl class="cswmMsgType " major  ref="cswmMsgType" >
		<dt>消息类型:</dt>
		<s:if test="#request.CTRL.e.cswmMsgType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csWxMo$cswmMsgType!=null">${csWxMo$cswmMsgType}</s:if><s:else>
		 	<select class="narrow" id="cswmMsgType" name="csWxMo.cswmMsgType">
		 		<option value="">请选择</option>
				<option value="0" ${csWxMo.cswmMsgType==0?"selected":""}>普通消息</option>
				<option value="1" ${csWxMo.cswmMsgType==1?"selected":""}>事件消息</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择微信上行的消息类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****消息类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmMsgType">${csWxMo.cswmMsgType}</textarea>
		 		<span>
		 	
			 ${csWxMo.cswmMsgType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmMsgType}
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
		<s:if test="#request.csWxMo$cswmContentType!=null">${csWxMo$cswmContentType}</s:if><s:else>
		 	<select class="narrow" id="cswmContentType" name="csWxMo.cswmContentType">
		 		<option value="">请选择</option>
				<option value="0" ${csWxMo.cswmContentType==0?"selected":""}>文本</option>
				<option value="1" ${csWxMo.cswmContentType==1?"selected":""}>图片</option>
				<option value="2" ${csWxMo.cswmContentType==2?"selected":""}>图文</option>
				<option value="3" ${csWxMo.cswmContentType==3?"selected":""}>其它</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择微信上行的内容类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****内容类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmContentType">${csWxMo.cswmContentType}</textarea>
		 		<span>
		 	
			 ${csWxMo.cswmContentType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmContentType}
	</s:if>
	
	${lz:set("注释","*****************消息内容字段的输入框代码*****************")}
	${lz:set("注释","before$cswmContent和after$cswmContent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmContent==true">
	${before$cswmContent}
	<dl class="cswmContent " major  ref="cswmContent" style="width:98%;">
		<dt>消息内容:</dt>
		<s:if test="#request.CTRL.e.cswmContent==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csWxMo$cswmContent!=null">${csWxMo$cswmContent}</s:if><s:else>
		 	<textarea class="input wide"  id="cswmContent" name="csWxMo.cswmContent" rows="5">${csWxMo.cswmContent}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>直接保存xml或json</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****消息内容字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csWxMo.cswmContent$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmContent}
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
		<s:if test="#request.csWxMo$cswmAddTime!=null">${csWxMo$cswmAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csWxMo.cswmAddTime" id="cswmAddTime"  value="<s:date name="csWxMo.cswmAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择微信上行的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmAddTime">${csWxMo.cswmAddTime}</textarea>
		 		<span>
		 	
			 ${csWxMo.cswmAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmAddTime}
	</s:if>
	
	${lz:set("注释","*****************程序掩码字段的输入框代码*****************")}
	${lz:set("注释","before$cswmMask和after$cswmMask变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmMask==true">
	${before$cswmMask}
	<dl class="cswmMask " minor  ref="cswmMask" style="width:98%;">
		<dt>程序掩码:</dt>
		<s:if test="#request.CTRL.e.cswmMask==true">
		${lz:set("haveEditable",true)}
		<dd input="checkbox">
		<s:if test="#request.csWxMo$cswmMask!=null">${csWxMo$cswmMask}</s:if><s:else>
		 	<div class='wide input'>	
		 	&nbsp; 	
		 	<input type="hidden" value="#0#" name="csWxMo.cswmMask" id="cswmMask"/>
			<label><input type="checkbox" ${lz:bitin(csWxMo.cswmMask,0) ? "checked='checked'" : ""} value="0" ref="cswmMask"/>默认&nbsp;&nbsp;&nbsp;</label>
		 </div>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择微信上行的程序掩码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****程序掩码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cswmMask">${csWxMo.cswmMask}</textarea>
		 		<span>
		 	
			 ${csWxMo.cswmMask$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswmMask}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cswmStatus和after$cswmStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswmStatus==true">
	${before$cswmStatus}
	<dl class="cswmStatus " major  ref="cswmStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cswmStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csWxMo$cswmStatus!=null">${csWxMo$cswmStatus}</s:if><s:else>
		 	<select class="narrow" id="cswmStatus" name="csWxMo.cswmStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csWxMo.cswmStatus==1?"selected":""}>正常</option>
				<option value="0" ${csWxMo.cswmStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择微信上行的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswmStatus">${csWxMo.cswmStatus}</textarea>
		 		<span>
		 	
			 ${csWxMo.cswmStatus$}
	 	  
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
					<input type="hidden" value="${csWxMo.cswmId}" name="csWxMo.cswmId" id="cswmId" />
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