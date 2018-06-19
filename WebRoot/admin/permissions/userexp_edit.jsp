<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/permissions/userexp.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/permissions/userexp.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty srvUserExp.sueId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"sueId,sueUser,sueWxOpenid,sueIsQrOp,sueIsServicer,sueChatNick,sueChatHeader,sueChatSign",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"sueId,sueUser,sueWxOpenid,sueIsQrOp,sueIsServicer,sueChatNick,sueChatHeader,sueChatSign",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"sueId,sueWxOpenid,sueIsQrOp,sueIsServicer,sueChatNick,sueChatHeader,sueChatSign",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"sueId,sueUser,sueWxOpenid,sueIsQrOp,sueIsServicer,sueChatNick,sueChatHeader,sueChatSign",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"用户扩展信息编辑":CTRL.title}</title>
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
	$("#userexpForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.sueId==true">
		${lz:set("haveEditable",true)}
		,"srvUserExp.sueId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.sueUser==true">
		${lz:set("haveEditable",true)}
		,"srvUserExp.sueUser":function(el){
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("userexp_query.do",{exists:true,sueUser:el.value});
				if(exists && exists.length>0){
					if(${srvUserExp.sueId==null} || exists[0].value!=$("#sueId").val())
						return "所属用户已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.sueWxOpenid==true">
		${lz:set("haveEditable",true)}
		,"srvUserExp.sueWxOpenid":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "微信标识最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.sueIsQrOp==true">
		${lz:set("haveEditable",true)}
		,"srvUserExp.sueIsQrOp":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.sueIsServicer==true">
		${lz:set("haveEditable",true)}
		,"srvUserExp.sueIsServicer":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.sueChatNick==true">
		${lz:set("haveEditable",true)}
		,"srvUserExp.sueChatNick":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "客服昵称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.sueChatHeader==true">
		${lz:set("haveEditable",true)}
		,"srvUserExp.sueChatHeader":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.sueChatSign==true">
		${lz:set("haveEditable",true)}
		,"srvUserExp.sueChatSign":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "客服签名最大长度为128个字符";
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
	<form class="form editform" ref="SrvUserExp" id="userexpForm" name="userexpForm" action="${empty CTRL.action?"userexp_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写用户扩展信息相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${srvUserExpToken}" name="srvUserExpToken" id="srvUserExpToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty srvUserExp.sueId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$sueId和after$sueId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.sueId==true">
	${before$sueId}
	<dl class="sueId ${CTRL.e.sueId?"hidden":""}" major  ref="sueId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.sueId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.srvUserExp$sueId!=null">${srvUserExp$sueId}</s:if><s:else>
			<input type="hidden" value="${srvUserExp.sueId}" name="srvUserExp.sueId" id="sueId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="sueId">${srvUserExp.sueId}</textarea>
		 		<span>
		 	
			 ${srvUserExp.sueId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$sueId}
	</s:if>
	
	${lz:set("注释","*****************所属用户字段的输入框代码*****************")}
	${lz:set("注释","before$sueUser和after$sueUser变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.sueUser==true">
	${before$sueUser}
	<dl class="sueUser " major  ref="sueUser" >
		<dt>所属用户:</dt>
		<s:if test="#request.CTRL.e.sueUser==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.srvUserExp$sueUser!=null">${srvUserExp$sueUser}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="sueUser" name="srvUserExp.sueUser">
		 				<option selected value="${srvUserExp.sueUser}">
		 					${get:SrvUser(srvUserExp.sueUser).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#sueUser').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#sueUser').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属用户字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="sueUser">${srvUserExp.sueUser}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${srvUserExp.sueUser}',{ctrl:{editable:false,visible:true}})">
			 ${srvUserExp.sueUser$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$sueUser}
	</s:if>
	
	${lz:set("注释","*****************微信标识字段的输入框代码*****************")}
	${lz:set("注释","before$sueWxOpenid和after$sueWxOpenid变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.sueWxOpenid==true">
	${before$sueWxOpenid}
	<dl class="sueWxOpenid " major  ref="sueWxOpenid" >
		<dt>微信标识:</dt>
		<s:if test="#request.CTRL.e.sueWxOpenid==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvUserExp$sueWxOpenid!=null">${srvUserExp$sueWxOpenid}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="srvUserExp.sueWxOpenid" id="sueWxOpenid"  value="${srvUserExp.sueWxOpenid}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入用户扩展信息的微信标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****微信标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="sueWxOpenid">${srvUserExp.sueWxOpenid}</textarea>
		 		<span>
		 	
			 ${srvUserExp.sueWxOpenid$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$sueWxOpenid}
	</s:if>
	
	${lz:set("注释","*****************外勤作业字段的输入框代码*****************")}
	${lz:set("注释","before$sueIsQrOp和after$sueIsQrOp变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.sueIsQrOp==true">
	${before$sueIsQrOp}
	<dl class="sueIsQrOp " major  ref="sueIsQrOp" >
		<dt>外勤作业:</dt>
		<s:if test="#request.CTRL.e.sueIsQrOp==true">
		${lz:set("haveEditable",true)}
		<dd input="radio">
		<s:if test="#request.srvUserExp$sueIsQrOp!=null">${srvUserExp$sueIsQrOp}</s:if><s:else>
		 	<div class='narrow input'>
		 	&nbsp;
	 	    <label><input type="radio" value="true" name="srvUserExp.sueIsQrOp" id="sueIsQrOp" ${srvUserExp.sueIsQrOp==true?"checked":""}/>是&nbsp;&nbsp;&nbsp;</label>
			<label><input type="radio" value="false" name="srvUserExp.sueIsQrOp" id="sueIsQrOp" ${srvUserExp==null || srvUserExp.sueIsQrOp==null || srvUserExp.sueIsQrOp==false?"checked":""}/>否</label>
		 	</div>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择用户扩展信息的外勤作业</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****外勤作业字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="sueIsQrOp">${srvUserExp.sueIsQrOp}</textarea>
		 		<span>
		 	
			 ${srvUserExp.sueIsQrOp$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$sueIsQrOp}
	</s:if>
	
	${lz:set("注释","*****************是否客服字段的输入框代码*****************")}
	${lz:set("注释","before$sueIsServicer和after$sueIsServicer变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.sueIsServicer==true">
	${before$sueIsServicer}
	<dl class="sueIsServicer " major  ref="sueIsServicer" >
		<dt>是否客服:</dt>
		<s:if test="#request.CTRL.e.sueIsServicer==true">
		${lz:set("haveEditable",true)}
		<dd input="radio">
		<s:if test="#request.srvUserExp$sueIsServicer!=null">${srvUserExp$sueIsServicer}</s:if><s:else>
		 	<div class='narrow input'>
		 	&nbsp;
	 	    <label><input type="radio" value="true" name="srvUserExp.sueIsServicer" id="sueIsServicer" ${srvUserExp.sueIsServicer==true?"checked":""}/>是&nbsp;&nbsp;&nbsp;</label>
			<label><input type="radio" value="false" name="srvUserExp.sueIsServicer" id="sueIsServicer" ${srvUserExp==null || srvUserExp.sueIsServicer==null || srvUserExp.sueIsServicer==false?"checked":""}/>否</label>
		 	</div>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择用户扩展信息的是否客服</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****是否客服字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="sueIsServicer">${srvUserExp.sueIsServicer}</textarea>
		 		<span>
		 	
			 ${srvUserExp.sueIsServicer$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$sueIsServicer}
	</s:if>
	
	${lz:set("注释","*****************客服昵称字段的输入框代码*****************")}
	${lz:set("注释","before$sueChatNick和after$sueChatNick变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.sueChatNick==true">
	${before$sueChatNick}
	<dl class="sueChatNick " major  ref="sueChatNick" >
		<dt>客服昵称:</dt>
		<s:if test="#request.CTRL.e.sueChatNick==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvUserExp$sueChatNick!=null">${srvUserExp$sueChatNick}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="srvUserExp.sueChatNick" id="sueChatNick"  value="${srvUserExp.sueChatNick}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入用户扩展信息的客服昵称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****客服昵称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="sueChatNick">${srvUserExp.sueChatNick}</textarea>
		 		<span>
		 	
			 ${srvUserExp.sueChatNick$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$sueChatNick}
	</s:if>
	
	${lz:set("注释","*****************客服头像字段的输入框代码*****************")}
	${lz:set("注释","before$sueChatHeader和after$sueChatHeader变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.sueChatHeader==true">
	${before$sueChatHeader}
	<dl class="sueChatHeader " minor  ref="sueChatHeader" style="width:98%;">
		<dt>客服头像:</dt>
		<s:if test="#request.CTRL.e.sueChatHeader==true">
		${lz:set("haveEditable",true)}
		<dd input="image">
		<s:if test="#request.srvUserExp$sueChatHeader!=null">${srvUserExp$sueChatHeader}</s:if><s:else>
		    <input type="text" class="input" maxlength="128" size="32" name="srvUserExp.sueChatHeader" id="sueChatHeader"  value="${srvUserExp.sueChatHeader}"/>
			<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#sueChatHeader').val(url)}})" class="button">设置图片</button>
			<button type="button" onclick="$('#sueChatHeader').val('')" class="button">删除图片</button>
			<button type="button" onclick="$.thumb({url:$('#sueChatHeader').val()})" class="button">查看图片</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传用户扩展信息的客服头像</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****客服头像字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="sueChatHeader">${srvUserExp.sueChatHeader}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(srvUserExp.sueChatHeader))}
		 	<s:if test="#request.isVacant==false">
		 	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="${srvUserExp.sueChatHeader}"/>
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$sueChatHeader}
	</s:if>
	
	${lz:set("注释","*****************客服签名字段的输入框代码*****************")}
	${lz:set("注释","before$sueChatSign和after$sueChatSign变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.sueChatSign==true">
	${before$sueChatSign}
	<dl class="sueChatSign " minor  ref="sueChatSign" style="width:98%;">
		<dt>客服签名:</dt>
		<s:if test="#request.CTRL.e.sueChatSign==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.srvUserExp$sueChatSign!=null">${srvUserExp$sueChatSign}</s:if><s:else>
		 	<textarea class="input wide"  id="sueChatSign" name="srvUserExp.sueChatSign" rows="5">${srvUserExp.sueChatSign}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入用户扩展信息的客服签名</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****客服签名字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${srvUserExp.sueChatSign$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$sueChatSign}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${srvUserExp.sueId}" name="srvUserExp.sueId" id="sueId" />
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