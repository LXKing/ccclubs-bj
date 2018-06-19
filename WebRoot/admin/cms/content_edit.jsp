<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/cms/content.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/cms/content.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csContent.cscId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cscId,cscTitle,cscParent,cscFlag,cscRecommend,cscThum,cscIntroduction,cscKeywords,cscImages,cscContent,cscFile,cscStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscId,cscTitle,cscParent,cscFlag,cscRecommend,cscThum,cscIntroduction,cscKeywords,cscImages,cscContent,cscFile,cscViewS,cscReplyS,cscCollectS,cscPraiseS,cscStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cscId,cscTitle,cscParent,cscFlag,cscRecommend,cscThum,cscIntroduction,cscKeywords,cscImages,cscContent,cscFile,cscStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscId,cscTitle,cscParent,cscFlag,cscRecommend,cscThum,cscIntroduction,cscKeywords,cscImages,cscContent,cscFile,cscAddTime,cscUpdateTime,cscViewS,cscReplyS,cscCollectS,cscPraiseS,cscStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"静态内容编辑":CTRL.title}</title>
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
		if($("#cscContent").size()>0){
			 //构造编辑页csContent.cscContent为html编辑控件
			 KE.init({
				id : 'cscContent',
				width : '625px',
				height : '300px' , 
				imageUploadJson : '${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}?app=content_edit&type=img${ lz:config("upload.url")==null ? "" : "&remote="}${ lz:config("upload.url")==null ? "" : basePath}${ lz:config("upload.url")==null ? "" : "callback.jsp"}'
			 });
			 KE.create("cscContent");
		 }
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#contentForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cscId==true">
		${lz:set("haveEditable",true)}
		,"csContent.cscId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscTitle==true">
		${lz:set("haveEditable",true)}
		,"csContent.cscTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "标题不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscParent==true">
		${lz:set("haveEditable",true)}
		,"csContent.cscParent":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscFlag==true">
		${lz:set("haveEditable",true)}
		,"csContent.cscFlag":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "系统标识最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscRecommend==true">
		${lz:set("haveEditable",true)}
		,"csContent.cscRecommend":function(el){
			if(jQuery.trim(el.value)=="")
				return "优先级不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscThum==true">
		${lz:set("haveEditable",true)}
		,"csContent.cscThum":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscIntroduction==true">
		${lz:set("haveEditable",true)}
		,"csContent.cscIntroduction":function(el){
			if(el.value.length>1024 && el.value.indexOf("[*]")==-1)
				return "简介最大长度为1024个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscKeywords==true">
		${lz:set("haveEditable",true)}
		,"csContent.cscKeywords":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "关键字最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscImages==true">
		${lz:set("haveEditable",true)}
		,"csContent.cscImages":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscContent==true">
		${lz:set("haveEditable",true)}
		,"csContent.cscContent":function(el){
			if($("#cscContent").get(0))
			 	KE.sync("cscContent");		 
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "内容最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscFile==true">
		${lz:set("haveEditable",true)}
		,"csContent.cscFile":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscAddTime==true">
		${lz:set("haveEditable",true)}
		,"csContent.cscAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csContent.cscUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "更新时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscViewS==true">
		${lz:set("haveEditable",true)}
		,"csContent.cscViewS":function(el){
			if(jQuery.trim(el.value)=="")
				return "浏览次数不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscReplyS==true">
		${lz:set("haveEditable",true)}
		,"csContent.cscReplyS":function(el){
			if(jQuery.trim(el.value)=="")
				return "评论次数不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscCollectS==true">
		${lz:set("haveEditable",true)}
		,"csContent.cscCollectS":function(el){
			if(jQuery.trim(el.value)=="")
				return "收藏次数不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscPraiseS==true">
		${lz:set("haveEditable",true)}
		,"csContent.cscPraiseS":function(el){
			if(jQuery.trim(el.value)=="")
				return "好评数不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscStatus==true">
		${lz:set("haveEditable",true)}
		,"csContent.cscStatus":function(el){
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
	<form class="form editform" ref="CsContent" id="contentForm" name="contentForm" action="${empty CTRL.action?"content_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写静态内容相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csContentToken}" name="csContentToken" id="csContentToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csContent.cscId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cscId和after$cscId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscId==true">
	${before$cscId}
	<dl class="cscId ${CTRL.e.cscId?"hidden":""}" major  ref="cscId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cscId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csContent$cscId!=null">${csContent$cscId}</s:if><s:else>
			<input type="hidden" value="${csContent.cscId}" name="csContent.cscId" id="cscId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscId">${csContent.cscId}</textarea>
		 		<span>
		 	
			 ${csContent.cscId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscId}
	</s:if>
	
	${lz:set("注释","*****************标题字段的输入框代码*****************")}
	${lz:set("注释","before$cscTitle和after$cscTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscTitle==true">
	${before$cscTitle}
	<dl class="cscTitle " major  ref="cscTitle" >
		<dt>标　　题:</dt>
		<s:if test="#request.CTRL.e.cscTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csContent$cscTitle!=null">${csContent$cscTitle}</s:if><s:else>
		 	<input type="text" class="input narrow"   name="csContent.cscTitle" id="cscTitle"  value="${csContent.cscTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>标题（32个字以内）</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****标题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscTitle">${csContent.cscTitle}</textarea>
		 		<span>
		 	
			 ${csContent.cscTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscTitle}
	</s:if>
	
	${lz:set("注释","*****************上级节点字段的输入框代码*****************")}
	${lz:set("注释","before$cscParent和after$cscParent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscParent==true">
	${before$cscParent}
	<dl class="cscParent ${CTRL.e.cscParent?"hidden":""}" major  ref="cscParent" >
		<dt>上级节点:</dt>
		<s:if test="#request.CTRL.e.cscParent==true">
		${lz:set("haveEditable",true)}
		<dd input="hidden">
		<s:if test="#request.csContent$cscParent!=null">${csContent$cscParent}</s:if><s:else>
			<input type="hidden" name="csContent.cscParent" id="csContent.cscParent"  value="${parentCsContent==null ? "0" : parentCsContent.cscId}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****上级节点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscParent">${csContent.cscParent}</textarea>
		 		<span>
		 	
			 ${csContent.cscParent$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscParent}
	</s:if>
	
	${lz:set("注释","*****************系统标识字段的输入框代码*****************")}
	${lz:set("注释","before$cscFlag和after$cscFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscFlag==true">
	${before$cscFlag}
	<dl class="cscFlag " major  ref="cscFlag" >
		<dt>系统标识:</dt>
		<s:if test="#request.CTRL.e.cscFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csContent$cscFlag!=null">${csContent$cscFlag}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csContent.cscFlag" id="cscFlag"  value="${csContent.cscFlag}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入静态内容的系统标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****系统标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscFlag">${csContent.cscFlag}</textarea>
		 		<span>
		 	
			 ${csContent.cscFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscFlag}
	</s:if>
	
	${lz:set("注释","*****************优先级字段的输入框代码*****************")}
	${lz:set("注释","before$cscRecommend和after$cscRecommend变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscRecommend==true">
	${before$cscRecommend}
	<dl class="cscRecommend " major  ref="cscRecommend" >
		<dt>优&nbsp;&nbsp;先&nbsp;&nbsp;级:</dt>
		<s:if test="#request.CTRL.e.cscRecommend==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csContent$cscRecommend!=null">${csContent$cscRecommend}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csContent.cscRecommend" id="cscRecommend"  value="${csContent.cscRecommend}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>数值越大，排序越靠前</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****优先级字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscRecommend">${csContent.cscRecommend}</textarea>
		 		<span>
		 	
			 ${csContent.cscRecommend$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscRecommend}
	</s:if>
	
	${lz:set("注释","*****************略缩图字段的输入框代码*****************")}
	${lz:set("注释","before$cscThum和after$cscThum变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscThum==true">
	${before$cscThum}
	<dl class="cscThum " minor  ref="cscThum" style="width:98%;">
		<dt>略&nbsp;&nbsp;缩&nbsp;&nbsp;图:</dt>
		<s:if test="#request.CTRL.e.cscThum==true">
		${lz:set("haveEditable",true)}
		<dd input="image">
		<s:if test="#request.csContent$cscThum!=null">${csContent$cscThum}</s:if><s:else>
		    <input type="text" class="input" maxlength="128" size="32" name="csContent.cscThum" id="cscThum"  value="${csContent.cscThum}"/>
			<button type="button" onclick="$.upload({type:'img',width:210,height:140,callback:function(url){if(url)$('#cscThum').val(url)}})" class="button">设置图片</button>
			<button type="button" onclick="$('#cscThum').val('')" class="button">删除图片</button>
			<button type="button" onclick="$.thumb({url:$('#cscThum').val()})" class="button">查看图片</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>在分类列表中显示的略缩图</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****略缩图字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscThum">${csContent.cscThum}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(csContent.cscThum))}
		 	<s:if test="#request.isVacant==false">
		 	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="${csContent.cscThum}"/>
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscThum}
	</s:if>
	
	${lz:set("注释","*****************简介字段的输入框代码*****************")}
	${lz:set("注释","before$cscIntroduction和after$cscIntroduction变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscIntroduction==true">
	${before$cscIntroduction}
	<dl class="cscIntroduction " minor  ref="cscIntroduction" style="width:98%;">
		<dt>简　　介:</dt>
		<s:if test="#request.CTRL.e.cscIntroduction==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csContent$cscIntroduction!=null">${csContent$cscIntroduction}</s:if><s:else>
		 	<textarea class="input wide"  id="cscIntroduction" name="csContent.cscIntroduction" rows="5">${csContent.cscIntroduction}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入静态内容的简介</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****简介字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csContent.cscIntroduction$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscIntroduction}
	</s:if>
	
	${lz:set("注释","*****************关键字字段的输入框代码*****************")}
	${lz:set("注释","before$cscKeywords和after$cscKeywords变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscKeywords==true">
	${before$cscKeywords}
	<dl class="cscKeywords " minor  ref="cscKeywords" style="width:98%;">
		<dt>关&nbsp;&nbsp;键&nbsp;&nbsp;字:</dt>
		<s:if test="#request.CTRL.e.cscKeywords==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csContent$cscKeywords!=null">${csContent$cscKeywords}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csContent.cscKeywords" id="cscKeywords"  value="${csContent.cscKeywords}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>百度搜索此关键字，多个关键字之前逗号分隔</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关键字字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscKeywords">${csContent.cscKeywords}</textarea>
		 		<span>
		 	
			 ${csContent.cscKeywords$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscKeywords}
	</s:if>
	
	${lz:set("注释","*****************预览图字段的输入框代码*****************")}
	${lz:set("注释","before$cscImages和after$cscImages变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscImages==true">
	${before$cscImages}
	<dl class="cscImages " minor  ref="cscImages" style="width:98%;">
		<dt>预&nbsp;&nbsp;览&nbsp;&nbsp;图:</dt>
		<s:if test="#request.CTRL.e.cscImages==true">
		${lz:set("haveEditable",true)}
		<dd input="imagelist">
		<s:if test="#request.csContent$cscImages!=null">${csContent$cscImages}</s:if><s:else>
		    <div id="cscImages" class="wide input imagelist">
			<s:generator val="csContent.cscImages" separator=",">
				<s:iterator id="item">
					${lz:set("isVacant",lz:vacant(item))}
		 			<s:if test="#request.isVacant==false">
					<div><a href="${item}" target="_blank"><img src="${item}"/><input type="hidden" name="csContent.cscImages" value="${item}"/></a><span onclick="$(this).parent().remove();">x</span></div>
					</s:if>
				</s:iterator>
			</s:generator>
			</div>
			<button type="button" onclick="$.upload({type:'img',count:10,callback:function(url){if(url)$('#cscImages').append('<div><a href=&quot;'+url+'&quot; target=&quot;_blank&quot;><img src=&quot;'+url+'&quot;/><input type=&quot;hidden&quot; name=&quot;csContent.cscImages&quot; value=&quot;'+url+'&quot;/></a><span onclick=&quot;$(this).parent().remove();&quot;>x</span></div>');}})" class="button">添加</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>在详情显示的时候显示的一些效果图</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****预览图字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscImages">${csContent.cscImages}</textarea>
		 		<span>
		 	 <s:generator val="csContent.cscImages" separator=",">
		 	 	<s:iterator id="item">
					${lz:set("isVacant",lz:vacant(item))}
		 			<s:if test="#request.isVacant==false">
					<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="<s:property/>"/>
					</s:if>
				</s:iterator>
			 </s:generator>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscImages}
	</s:if>
	
	${lz:set("注释","*****************内容字段的输入框代码*****************")}
	${lz:set("注释","before$cscContent和after$cscContent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscContent==true">
	${before$cscContent}
	<dl class="cscContent " minor  ref="cscContent" style="width:98%;">
		<dt>内　　容:</dt>
		<s:if test="#request.CTRL.e.cscContent==true">
		${lz:set("haveEditable",true)}
		<dd input="html">
		<s:if test="#request.csContent$cscContent!=null">${csContent$cscContent}</s:if><s:else>
		    <textarea class="input wide"  id="cscContent" name="csContent.cscContent" style="height:300px;visibility:hidden;">${csContent.cscContent}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入静态内容的内容</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****内容字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csContent.cscContent$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscContent}
	</s:if>
	
	${lz:set("注释","*****************文件字段的输入框代码*****************")}
	${lz:set("注释","before$cscFile和after$cscFile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscFile==true">
	${before$cscFile}
	<dl class="cscFile " minor  ref="cscFile" style="width:98%;">
		<dt>文　　件:</dt>
		<s:if test="#request.CTRL.e.cscFile==true">
		${lz:set("haveEditable",true)}
		<dd input="file">
		<s:if test="#request.csContent$cscFile!=null">${csContent$cscFile}</s:if><s:else>
		 	<input type="text" class="input" maxlength="128" size="32" name="csContent.cscFile" id="cscFile"  value="${csContent.cscFile}"/>
			<button type="button" onclick="$.upload({type:'file',callback:function(url){if(url)$('#cscFile').val(url)}})" class="button">上传文件</button>
			<button type="button" onclick="$('#cscFile').val('')" class="button">删除文件</button>
			<button type="button" onclick="window.location='${ lz:config("lazy3q.download")==null ? basePath : ""}${ lz:config("lazy3q.download")==null ? "download" :  lz:config("lazy3q.download")}?path='+$('#cscFile').val()" class="button">下载文件</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>下载的文件，多个文件请压缩</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****文件字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscFile">${csContent.cscFile}</textarea>
		 		<span>
		 	
			 ${csContent.cscFile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscFile}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscAddTime和after$cscAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscAddTime==true">
	${before$cscAddTime}
	<dl class="cscAddTime " major  ref="cscAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cscAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csContent$cscAddTime!=null">${csContent$cscAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csContent.cscAddTime" id="cscAddTime"  value="<s:date name="csContent.cscAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择静态内容的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscAddTime">${csContent.cscAddTime}</textarea>
		 		<span>
		 	
			 ${csContent.cscAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscAddTime}
	</s:if>
	
	${lz:set("注释","*****************更新时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscUpdateTime和after$cscUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscUpdateTime==true">
	${before$cscUpdateTime}
	<dl class="cscUpdateTime " major  ref="cscUpdateTime" >
		<dt>更新时间:</dt>
		<s:if test="#request.CTRL.e.cscUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csContent$cscUpdateTime!=null">${csContent$cscUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csContent.cscUpdateTime" id="cscUpdateTime"  value="<s:date name="csContent.cscUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择静态内容的更新时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****更新时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscUpdateTime">${csContent.cscUpdateTime}</textarea>
		 		<span>
		 	
			 ${csContent.cscUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************浏览次数字段的输入框代码*****************")}
	${lz:set("注释","before$cscViewS和after$cscViewS变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscViewS==true">
	${before$cscViewS}
	<dl class="cscViewS " major  ref="cscViewS" >
		<dt>浏览次数:</dt>
		<s:if test="#request.CTRL.e.cscViewS==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csContent$cscViewS!=null">${csContent$cscViewS}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csContent.cscViewS" id="cscViewS"  value="${csContent.cscViewS}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入静态内容的浏览次数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****浏览次数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscViewS">${csContent.cscViewS}</textarea>
		 		<span>
		 	
			 ${csContent.cscViewS$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscViewS}
	</s:if>
	
	${lz:set("注释","*****************评论次数字段的输入框代码*****************")}
	${lz:set("注释","before$cscReplyS和after$cscReplyS变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscReplyS==true">
	${before$cscReplyS}
	<dl class="cscReplyS " major  ref="cscReplyS" >
		<dt>评论次数:</dt>
		<s:if test="#request.CTRL.e.cscReplyS==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csContent$cscReplyS!=null">${csContent$cscReplyS}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csContent.cscReplyS" id="cscReplyS"  value="${csContent.cscReplyS}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入静态内容的评论次数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****评论次数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscReplyS">${csContent.cscReplyS}</textarea>
		 		<span>
		 	
			 ${csContent.cscReplyS$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscReplyS}
	</s:if>
	
	${lz:set("注释","*****************收藏次数字段的输入框代码*****************")}
	${lz:set("注释","before$cscCollectS和after$cscCollectS变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscCollectS==true">
	${before$cscCollectS}
	<dl class="cscCollectS " major  ref="cscCollectS" >
		<dt>收藏次数:</dt>
		<s:if test="#request.CTRL.e.cscCollectS==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csContent$cscCollectS!=null">${csContent$cscCollectS}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csContent.cscCollectS" id="cscCollectS"  value="${csContent.cscCollectS}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入静态内容的收藏次数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****收藏次数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscCollectS">${csContent.cscCollectS}</textarea>
		 		<span>
		 	
			 ${csContent.cscCollectS$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscCollectS}
	</s:if>
	
	${lz:set("注释","*****************好评数字段的输入框代码*****************")}
	${lz:set("注释","before$cscPraiseS和after$cscPraiseS变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscPraiseS==true">
	${before$cscPraiseS}
	<dl class="cscPraiseS " major  ref="cscPraiseS" >
		<dt>好&nbsp;&nbsp;评&nbsp;&nbsp;数:</dt>
		<s:if test="#request.CTRL.e.cscPraiseS==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csContent$cscPraiseS!=null">${csContent$cscPraiseS}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csContent.cscPraiseS" id="cscPraiseS"  value="${csContent.cscPraiseS}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入静态内容的好评数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****好评数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscPraiseS">${csContent.cscPraiseS}</textarea>
		 		<span>
		 	
			 ${csContent.cscPraiseS$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscPraiseS}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cscStatus和after$cscStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscStatus==true">
	${before$cscStatus}
	<dl class="cscStatus " major  ref="cscStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cscStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csContent$cscStatus!=null">${csContent$cscStatus}</s:if><s:else>
		 	<select class="narrow" id="cscStatus" name="csContent.cscStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csContent.cscStatus==1?"selected":""}>正常</option>
				<option value="0" ${csContent.cscStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择静态内容的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscStatus">${csContent.cscStatus}</textarea>
		 		<span>
		 	
			 ${csContent.cscStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csContent.cscId}" name="csContent.cscId" id="cscId" />
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