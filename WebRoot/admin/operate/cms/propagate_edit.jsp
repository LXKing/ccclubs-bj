<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/operate/cms/propagate.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/operate/cms/propagate.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csPropagate.cspId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cspId,cspTitle,cspType,cspRecommend,cspFlag,cspThum,cspContentType,cspContent,cspHtml,cspRedirect,cspStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cspId,cspTitle,cspType,cspRecommend,cspFlag,cspThum,cspContentType,cspContent,cspHtml,cspRedirect,cspViewS,cspReplyS,cspCollectS,cspPraiseS,cspStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cspId,cspTitle,cspType,cspRecommend,cspFlag,cspThum,cspContentType,cspContent,cspHtml,cspRedirect,cspStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cspId,cspTitle,cspType,cspRecommend,cspFlag,cspThum,cspContentType,cspContent,cspHtml,cspRedirect,cspAddTime,cspUpdateTime,cspViewS,cspReplyS,cspCollectS,cspPraiseS,cspStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"传播内容编辑":CTRL.title}</title>
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
		if($("#cspContent").size()>0){
			 //构造编辑页csPropagate.cspContent为html编辑控件
			 KE.init({
				id : 'cspContent',
				width : '625px',
				height : '300px' , 
				imageUploadJson : '${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}?app=propagate_edit&type=img${ lz:config("upload.url")==null ? "" : "&remote="}${ lz:config("upload.url")==null ? "" : basePath}${ lz:config("upload.url")==null ? "" : "callback.jsp"}'
			 });
			 KE.create("cspContent");
		 }
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#propagateForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cspId==true">
		${lz:set("haveEditable",true)}
		,"csPropagate.cspId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspTitle==true">
		${lz:set("haveEditable",true)}
		,"csPropagate.cspTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "标题不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "标题最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspType==true">
		${lz:set("haveEditable",true)}
		,"csPropagate.cspType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择平台类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspRecommend==true">
		${lz:set("haveEditable",true)}
		,"csPropagate.cspRecommend":function(el){
			if(jQuery.trim(el.value)=="")
				return "优先级不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspFlag==true">
		${lz:set("haveEditable",true)}
		,"csPropagate.cspFlag":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "程序标识最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspThum==true">
		${lz:set("haveEditable",true)}
		,"csPropagate.cspThum":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspContentType==true">
		${lz:set("haveEditable",true)}
		,"csPropagate.cspContentType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择内容类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspContent==true">
		${lz:set("haveEditable",true)}
		,"csPropagate.cspContent":function(el){
			if($("#cspContent").get(0))
			 	KE.sync("cspContent");		 
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "编辑内容最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspHtml==true">
		${lz:set("haveEditable",true)}
		,"csPropagate.cspHtml":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "HTML代码最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspRedirect==true">
		${lz:set("haveEditable",true)}
		,"csPropagate.cspRedirect":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "转到地址最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspAddTime==true">
		${lz:set("haveEditable",true)}
		,"csPropagate.cspAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csPropagate.cspUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "更新时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspViewS==true">
		${lz:set("haveEditable",true)}
		,"csPropagate.cspViewS":function(el){
			if(jQuery.trim(el.value)=="")
				return "浏览次数不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspReplyS==true">
		${lz:set("haveEditable",true)}
		,"csPropagate.cspReplyS":function(el){
			if(jQuery.trim(el.value)=="")
				return "评论次数不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspCollectS==true">
		${lz:set("haveEditable",true)}
		,"csPropagate.cspCollectS":function(el){
			if(jQuery.trim(el.value)=="")
				return "收藏次数不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspPraiseS==true">
		${lz:set("haveEditable",true)}
		,"csPropagate.cspPraiseS":function(el){
			if(jQuery.trim(el.value)=="")
				return "好评数不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspStatus==true">
		${lz:set("haveEditable",true)}
		,"csPropagate.cspStatus":function(el){
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
	<form class="form editform" ref="CsPropagate" id="propagateForm" name="propagateForm" action="${empty CTRL.action?"propagate_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写传播内容相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csPropagateToken}" name="csPropagateToken" id="csPropagateToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csPropagate.cspId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cspId和after$cspId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspId==true">
	${before$cspId}
	<dl class="cspId ${CTRL.e.cspId?"hidden":""}" major  ref="cspId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cspId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csPropagate$cspId!=null">${csPropagate$cspId}</s:if><s:else>
			<input type="hidden" value="${csPropagate.cspId}" name="csPropagate.cspId" id="cspId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspId">${csPropagate.cspId}</textarea>
		 		<span>
		 	
			 ${csPropagate.cspId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspId}
	</s:if>
	
	${lz:set("注释","*****************标题字段的输入框代码*****************")}
	${lz:set("注释","before$cspTitle和after$cspTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspTitle==true">
	${before$cspTitle}
	<dl class="cspTitle " major  ref="cspTitle" >
		<dt>标　　题:</dt>
		<s:if test="#request.CTRL.e.cspTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csPropagate$cspTitle!=null">${csPropagate$cspTitle}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csPropagate.cspTitle" id="cspTitle"  value="${csPropagate.cspTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>标题（32个字以内）</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****标题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspTitle">${csPropagate.cspTitle}</textarea>
		 		<span>
		 	
			 ${csPropagate.cspTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspTitle}
	</s:if>
	
	${lz:set("注释","*****************平台类型字段的输入框代码*****************")}
	${lz:set("注释","before$cspType和after$cspType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspType==true">
	${before$cspType}
	<dl class="cspType " major  ref="cspType" >
		<dt>平台类型:</dt>
		<s:if test="#request.CTRL.e.cspType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csPropagate$cspType!=null">${csPropagate$cspType}</s:if><s:else>
		 	<select class="narrow" id="cspType" name="csPropagate.cspType">
		 		<option value="">请选择</option>
				<option value="0" ${csPropagate.cspType==0?"selected":""}>PC端</option>
				<option value="1" ${csPropagate.cspType==1?"selected":""}>移动端</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择传播内容的平台类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****平台类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspType">${csPropagate.cspType}</textarea>
		 		<span>
		 	
			 ${csPropagate.cspType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspType}
	</s:if>
	
	${lz:set("注释","*****************优先级字段的输入框代码*****************")}
	${lz:set("注释","before$cspRecommend和after$cspRecommend变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspRecommend==true">
	${before$cspRecommend}
	<dl class="cspRecommend " major  ref="cspRecommend" >
		<dt>优&nbsp;&nbsp;先&nbsp;&nbsp;级:</dt>
		<s:if test="#request.CTRL.e.cspRecommend==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csPropagate$cspRecommend!=null">${csPropagate$cspRecommend}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csPropagate.cspRecommend" id="cspRecommend"  value="${csPropagate.cspRecommend}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>数值越大，排序越靠前</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****优先级字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspRecommend">${csPropagate.cspRecommend}</textarea>
		 		<span>
		 	
			 ${csPropagate.cspRecommend$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspRecommend}
	</s:if>
	
	${lz:set("注释","*****************程序标识字段的输入框代码*****************")}
	${lz:set("注释","before$cspFlag和after$cspFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspFlag==true">
	${before$cspFlag}
	<dl class="cspFlag " major  ref="cspFlag" >
		<dt>程序标识:</dt>
		<s:if test="#request.CTRL.e.cspFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csPropagate$cspFlag!=null">${csPropagate$cspFlag}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csPropagate.cspFlag" id="cspFlag"  value="${csPropagate.cspFlag}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入传播内容的程序标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****程序标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspFlag">${csPropagate.cspFlag}</textarea>
		 		<span>
		 	
			 ${csPropagate.cspFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspFlag}
	</s:if>
	
	${lz:set("注释","*****************略缩图字段的输入框代码*****************")}
	${lz:set("注释","before$cspThum和after$cspThum变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspThum==true">
	${before$cspThum}
	<dl class="cspThum " major  ref="cspThum" style="width:98%;">
		<dt>略&nbsp;&nbsp;缩&nbsp;&nbsp;图:</dt>
		<s:if test="#request.CTRL.e.cspThum==true">
		${lz:set("haveEditable",true)}
		<dd input="image">
		<s:if test="#request.csPropagate$cspThum!=null">${csPropagate$cspThum}</s:if><s:else>
		    <input type="text" class="input" maxlength="128" size="32" name="csPropagate.cspThum" id="cspThum"  value="${csPropagate.cspThum}"/>
			<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#cspThum').val(url)}})" class="button">设置图片</button>
			<button type="button" onclick="$('#cspThum').val('')" class="button">删除图片</button>
			<button type="button" onclick="$.thumb({url:$('#cspThum').val()})" class="button">查看图片</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>在分类列表中显示的略缩图</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****略缩图字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cspThum">${csPropagate.cspThum}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(csPropagate.cspThum))}
		 	<s:if test="#request.isVacant==false">
		 	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="${csPropagate.cspThum}"/>
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspThum}
	</s:if>
	
	${lz:set("注释","*****************内容类型字段的输入框代码*****************")}
	${lz:set("注释","before$cspContentType和after$cspContentType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspContentType==true">
	${before$cspContentType}
	<dl class="cspContentType " major  ref="cspContentType" >
		<dt>内容类型:</dt>
		<s:if test="#request.CTRL.e.cspContentType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csPropagate$cspContentType!=null">${csPropagate$cspContentType}</s:if><s:else>
		 	<select class="narrow" id="cspContentType" name="csPropagate.cspContentType">
		 		<option value="">请选择</option>
				<option value="0" ${csPropagate.cspContentType==0?"selected":""}>编辑内容</option>
				<option value="1" ${csPropagate.cspContentType==1?"selected":""}>html代码</option>
				<option value="2" ${csPropagate.cspContentType==2?"selected":""}>转跳地址</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择传播内容的内容类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****内容类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspContentType">${csPropagate.cspContentType}</textarea>
		 		<span>
		 	
			 ${csPropagate.cspContentType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspContentType}
	</s:if>
	
	${lz:set("注释","*****************编辑内容字段的输入框代码*****************")}
	${lz:set("注释","before$cspContent和after$cspContent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspContent==true">
	${before$cspContent}
	<dl class="cspContent " minor  ref="cspContent" style="width:98%;">
		<dt>编辑内容:</dt>
		<s:if test="#request.CTRL.e.cspContent==true">
		${lz:set("haveEditable",true)}
		<dd input="html">
		<s:if test="#request.csPropagate$cspContent!=null">${csPropagate$cspContent}</s:if><s:else>
		    <textarea class="input wide"  id="cspContent" name="csPropagate.cspContent" style="height:300px;visibility:hidden;">${csPropagate.cspContent}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入传播内容的编辑内容</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编辑内容字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csPropagate.cspContent$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspContent}
	</s:if>
	
	${lz:set("注释","*****************HTML代码字段的输入框代码*****************")}
	${lz:set("注释","before$cspHtml和after$cspHtml变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspHtml==true">
	${before$cspHtml}
	<dl class="cspHtml " minor  ref="cspHtml" style="width:98%;">
		<dt>HTML代码:</dt>
		<s:if test="#request.CTRL.e.cspHtml==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csPropagate$cspHtml!=null">${csPropagate$cspHtml}</s:if><s:else>
		 	<textarea class="input wide"  id="cspHtml" name="csPropagate.cspHtml" rows="5">${csPropagate.cspHtml}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入传播内容的HTML代码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****HTML代码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csPropagate.cspHtml$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspHtml}
	</s:if>
	
	${lz:set("注释","*****************转到地址字段的输入框代码*****************")}
	${lz:set("注释","before$cspRedirect和after$cspRedirect变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspRedirect==true">
	${before$cspRedirect}
	<dl class="cspRedirect " minor  ref="cspRedirect" style="width:98%;">
		<dt>转到地址:</dt>
		<s:if test="#request.CTRL.e.cspRedirect==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csPropagate$cspRedirect!=null">${csPropagate$cspRedirect}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csPropagate.cspRedirect" id="cspRedirect"  value="${csPropagate.cspRedirect}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>转跳到哪个地址</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****转到地址字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cspRedirect">${csPropagate.cspRedirect}</textarea>
		 		<span>
		 	
			 ${csPropagate.cspRedirect$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspRedirect}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cspAddTime和after$cspAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspAddTime==true">
	${before$cspAddTime}
	<dl class="cspAddTime " major  ref="cspAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cspAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csPropagate$cspAddTime!=null">${csPropagate$cspAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csPropagate.cspAddTime" id="cspAddTime"  value="<s:date name="csPropagate.cspAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择传播内容的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspAddTime">${csPropagate.cspAddTime}</textarea>
		 		<span>
		 	
			 ${csPropagate.cspAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspAddTime}
	</s:if>
	
	${lz:set("注释","*****************更新时间字段的输入框代码*****************")}
	${lz:set("注释","before$cspUpdateTime和after$cspUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspUpdateTime==true">
	${before$cspUpdateTime}
	<dl class="cspUpdateTime " major  ref="cspUpdateTime" >
		<dt>更新时间:</dt>
		<s:if test="#request.CTRL.e.cspUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csPropagate$cspUpdateTime!=null">${csPropagate$cspUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csPropagate.cspUpdateTime" id="cspUpdateTime"  value="<s:date name="csPropagate.cspUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择传播内容的更新时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****更新时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspUpdateTime">${csPropagate.cspUpdateTime}</textarea>
		 		<span>
		 	
			 ${csPropagate.cspUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************浏览次数字段的输入框代码*****************")}
	${lz:set("注释","before$cspViewS和after$cspViewS变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspViewS==true">
	${before$cspViewS}
	<dl class="cspViewS " major  ref="cspViewS" >
		<dt>浏览次数:</dt>
		<s:if test="#request.CTRL.e.cspViewS==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csPropagate$cspViewS!=null">${csPropagate$cspViewS}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csPropagate.cspViewS" id="cspViewS"  value="${csPropagate.cspViewS}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入传播内容的浏览次数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****浏览次数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspViewS">${csPropagate.cspViewS}</textarea>
		 		<span>
		 	
			 ${csPropagate.cspViewS$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspViewS}
	</s:if>
	
	${lz:set("注释","*****************评论次数字段的输入框代码*****************")}
	${lz:set("注释","before$cspReplyS和after$cspReplyS变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspReplyS==true">
	${before$cspReplyS}
	<dl class="cspReplyS " major  ref="cspReplyS" >
		<dt>评论次数:</dt>
		<s:if test="#request.CTRL.e.cspReplyS==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csPropagate$cspReplyS!=null">${csPropagate$cspReplyS}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csPropagate.cspReplyS" id="cspReplyS"  value="${csPropagate.cspReplyS}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入传播内容的评论次数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****评论次数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspReplyS">${csPropagate.cspReplyS}</textarea>
		 		<span>
		 	
			 ${csPropagate.cspReplyS$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspReplyS}
	</s:if>
	
	${lz:set("注释","*****************收藏次数字段的输入框代码*****************")}
	${lz:set("注释","before$cspCollectS和after$cspCollectS变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspCollectS==true">
	${before$cspCollectS}
	<dl class="cspCollectS " major  ref="cspCollectS" >
		<dt>收藏次数:</dt>
		<s:if test="#request.CTRL.e.cspCollectS==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csPropagate$cspCollectS!=null">${csPropagate$cspCollectS}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csPropagate.cspCollectS" id="cspCollectS"  value="${csPropagate.cspCollectS}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入传播内容的收藏次数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****收藏次数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspCollectS">${csPropagate.cspCollectS}</textarea>
		 		<span>
		 	
			 ${csPropagate.cspCollectS$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspCollectS}
	</s:if>
	
	${lz:set("注释","*****************好评数字段的输入框代码*****************")}
	${lz:set("注释","before$cspPraiseS和after$cspPraiseS变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspPraiseS==true">
	${before$cspPraiseS}
	<dl class="cspPraiseS " major  ref="cspPraiseS" >
		<dt>好&nbsp;&nbsp;评&nbsp;&nbsp;数:</dt>
		<s:if test="#request.CTRL.e.cspPraiseS==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csPropagate$cspPraiseS!=null">${csPropagate$cspPraiseS}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csPropagate.cspPraiseS" id="cspPraiseS"  value="${csPropagate.cspPraiseS}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入传播内容的好评数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****好评数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspPraiseS">${csPropagate.cspPraiseS}</textarea>
		 		<span>
		 	
			 ${csPropagate.cspPraiseS$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspPraiseS}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cspStatus和after$cspStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspStatus==true">
	${before$cspStatus}
	<dl class="cspStatus " major  ref="cspStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cspStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csPropagate$cspStatus!=null">${csPropagate$cspStatus}</s:if><s:else>
		 	<select class="narrow" id="cspStatus" name="csPropagate.cspStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csPropagate.cspStatus==1?"selected":""}>正常</option>
				<option value="0" ${csPropagate.cspStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择传播内容的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspStatus">${csPropagate.cspStatus}</textarea>
		 		<span>
		 	
			 ${csPropagate.cspStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csPropagate.cspId}" name="csPropagate.cspId" id="cspId" />
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