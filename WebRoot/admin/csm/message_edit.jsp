<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/csm/message.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/csm/message.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csChatMessage.cscmId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cscmId,cscmHost,cscmContent,cscmType,cscmFrom,cscmTarget,cscmState,cscmStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscmId,cscmHost,cscmContent,cscmType,cscmFrom,cscmTarget,cscmState,cscmStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cscmId,cscmHost,cscmContent,cscmType,cscmFrom,cscmTarget,cscmState,cscmStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscmId,cscmHost,cscmContent,cscmType,cscmFrom,cscmTarget,cscmState,cscmStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"客服消息编辑":CTRL.title}</title>
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
	$("#messageForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cscmId==true">
		${lz:set("haveEditable",true)}
		,"csChatMessage.cscmId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscmHost==true">
		${lz:set("haveEditable",true)}
		,"csChatMessage.cscmHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscmContent==true">
		${lz:set("haveEditable",true)}
		,"csChatMessage.cscmContent":function(el){
			if(jQuery.trim(el.value)=="")
				return "消息内容不能为空";
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "消息内容最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscmType==true">
		${lz:set("haveEditable",true)}
		,"csChatMessage.cscmType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择内容类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscmFrom==true">
		${lz:set("haveEditable",true)}
		,"csChatMessage.cscmFrom":function(el){
			if(jQuery.trim(el.value)=="")
				return "发送来源不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "发送来源最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscmTarget==true">
		${lz:set("haveEditable",true)}
		,"csChatMessage.cscmTarget":function(el){
			if(jQuery.trim(el.value)=="")
				return "接收目标不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "接收目标最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscmUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csChatMessage.cscmUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscmAddTime==true">
		${lz:set("haveEditable",true)}
		,"csChatMessage.cscmAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscmState==true">
		${lz:set("haveEditable",true)}
		,"csChatMessage.cscmState":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择接收状态";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscmStatus==true">
		${lz:set("haveEditable",true)}
		,"csChatMessage.cscmStatus":function(el){
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
	<form class="form editform" ref="CsChatMessage" id="messageForm" name="messageForm" action="${empty CTRL.action?"message_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写客服消息相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csChatMessageToken}" name="csChatMessageToken" id="csChatMessageToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csChatMessage.cscmId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cscmId和after$cscmId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmId==true">
	${before$cscmId}
	<dl class="cscmId ${CTRL.e.cscmId?"hidden":""}" major  ref="cscmId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cscmId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csChatMessage$cscmId!=null">${csChatMessage$cscmId}</s:if><s:else>
			<input type="hidden" value="${csChatMessage.cscmId}" name="csChatMessage.cscmId" id="cscmId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscmId">${csChatMessage.cscmId}</textarea>
		 		<span>
		 	
			 ${csChatMessage.cscmId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cscmHost和after$cscmHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmHost==true">
	${before$cscmHost}
	<dl class="cscmHost " major  ref="cscmHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cscmHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csChatMessage$cscmHost!=null">${csChatMessage$cscmHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cscmHost" name="csChatMessage.cscmHost">
		 				<option selected value="${csChatMessage.cscmHost}">
		 					${get:SrvHost(csChatMessage.cscmHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscmHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cscmHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscmHost">${csChatMessage.cscmHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csChatMessage.cscmHost}',{ctrl:{editable:false,visible:true}})">
			 ${csChatMessage.cscmHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmHost}
	</s:if>
	
	${lz:set("注释","*****************消息内容字段的输入框代码*****************")}
	${lz:set("注释","before$cscmContent和after$cscmContent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmContent==true">
	${before$cscmContent}
	<dl class="cscmContent " major  ref="cscmContent" style="width:98%;">
		<dt>消息内容:</dt>
		<s:if test="#request.CTRL.e.cscmContent==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csChatMessage$cscmContent!=null">${csChatMessage$cscmContent}</s:if><s:else>
		 	<textarea class="input wide"  id="cscmContent" name="csChatMessage.cscmContent" rows="5">${csChatMessage.cscmContent}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入客服消息的消息内容</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****消息内容字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csChatMessage.cscmContent$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmContent}
	</s:if>
	
	${lz:set("注释","*****************内容类型字段的输入框代码*****************")}
	${lz:set("注释","before$cscmType和after$cscmType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmType==true">
	${before$cscmType}
	<dl class="cscmType " major  ref="cscmType" >
		<dt>内容类型:</dt>
		<s:if test="#request.CTRL.e.cscmType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csChatMessage$cscmType!=null">${csChatMessage$cscmType}</s:if><s:else>
		 	<select class="narrow" id="cscmType" name="csChatMessage.cscmType">
		 		<option value="">请选择</option>
				<option value="text" ${csChatMessage.cscmType==text?"selected":""}>文本</option>
				<option value="image" ${csChatMessage.cscmType==image?"selected":""}>图片</option>
				<option value="news" ${csChatMessage.cscmType==news?"selected":""}>图文</option>
				<option value="html" ${csChatMessage.cscmType==html?"selected":""}>特殊</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客服消息的内容类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****内容类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscmType">${csChatMessage.cscmType}</textarea>
		 		<span>
		 	
			 ${csChatMessage.cscmType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmType}
	</s:if>
	
	${lz:set("注释","*****************发送来源字段的输入框代码*****************")}
	${lz:set("注释","before$cscmFrom和after$cscmFrom变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmFrom==true">
	${before$cscmFrom}
	<dl class="cscmFrom " major  ref="cscmFrom" >
		<dt>发送来源:</dt>
		<s:if test="#request.CTRL.e.cscmFrom==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csChatMessage$cscmFrom!=null">${csChatMessage$cscmFrom}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csChatMessage.cscmFrom" id="cscmFrom"  value="${csChatMessage.cscmFrom}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入客服消息的发送来源</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****发送来源字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscmFrom">${csChatMessage.cscmFrom}</textarea>
		 		<span>
		 	
			 ${csChatMessage.cscmFrom$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmFrom}
	</s:if>
	
	${lz:set("注释","*****************接收目标字段的输入框代码*****************")}
	${lz:set("注释","before$cscmTarget和after$cscmTarget变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmTarget==true">
	${before$cscmTarget}
	<dl class="cscmTarget " major  ref="cscmTarget" >
		<dt>接收目标:</dt>
		<s:if test="#request.CTRL.e.cscmTarget==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csChatMessage$cscmTarget!=null">${csChatMessage$cscmTarget}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csChatMessage.cscmTarget" id="cscmTarget"  value="${csChatMessage.cscmTarget}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入客服消息的接收目标</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****接收目标字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscmTarget">${csChatMessage.cscmTarget}</textarea>
		 		<span>
		 	
			 ${csChatMessage.cscmTarget$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmTarget}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscmUpdateTime和after$cscmUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmUpdateTime==true">
	${before$cscmUpdateTime}
	<dl class="cscmUpdateTime " major  ref="cscmUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cscmUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csChatMessage$cscmUpdateTime!=null">${csChatMessage$cscmUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csChatMessage.cscmUpdateTime" id="cscmUpdateTime"  value="<s:date name="csChatMessage.cscmUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客服消息的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscmUpdateTime">${csChatMessage.cscmUpdateTime}</textarea>
		 		<span>
		 	
			 ${csChatMessage.cscmUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscmAddTime和after$cscmAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmAddTime==true">
	${before$cscmAddTime}
	<dl class="cscmAddTime " major  ref="cscmAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cscmAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csChatMessage$cscmAddTime!=null">${csChatMessage$cscmAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csChatMessage.cscmAddTime" id="cscmAddTime"  value="<s:date name="csChatMessage.cscmAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客服消息的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscmAddTime">${csChatMessage.cscmAddTime}</textarea>
		 		<span>
		 	
			 ${csChatMessage.cscmAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmAddTime}
	</s:if>
	
	${lz:set("注释","*****************接收状态字段的输入框代码*****************")}
	${lz:set("注释","before$cscmState和after$cscmState变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmState==true">
	${before$cscmState}
	<dl class="cscmState " major  ref="cscmState" >
		<dt>接收状态:</dt>
		<s:if test="#request.CTRL.e.cscmState==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csChatMessage$cscmState!=null">${csChatMessage$cscmState}</s:if><s:else>
		 	<select class="narrow" id="cscmState" name="csChatMessage.cscmState">
		 		<option value="">请选择</option>
				<option value="0" ${csChatMessage.cscmState==0?"selected":""}>未读</option>
				<option value="1" ${csChatMessage.cscmState==1?"selected":""}>已读</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客服消息的接收状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****接收状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscmState">${csChatMessage.cscmState}</textarea>
		 		<span>
		 	
			 ${csChatMessage.cscmState$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmState}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cscmStatus和after$cscmStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmStatus==true">
	${before$cscmStatus}
	<dl class="cscmStatus " major  ref="cscmStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cscmStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csChatMessage$cscmStatus!=null">${csChatMessage$cscmStatus}</s:if><s:else>
		 	<select class="narrow" id="cscmStatus" name="csChatMessage.cscmStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csChatMessage.cscmStatus==1?"selected":""}>默认</option>
				<option value="0" ${csChatMessage.cscmStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择客服消息的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscmStatus">${csChatMessage.cscmStatus}</textarea>
		 		<span>
		 	
			 ${csChatMessage.cscmStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csChatMessage.cscmId}" name="csChatMessage.cscmId" id="cscmId" />
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