<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/configurator/wx/wxcontent.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/configurator/wx/wxcontent.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csWxContent.cswcId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cswcId,cswcTitle,cswcContnet,cswcThumb,cswcImage,cswcLink,cswcOrder",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cswcId,cswcTitle,cswcContnet,cswcThumb,cswcImage,cswcLink,cswcOrder",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cswcId,cswcTitle,cswcContnet,cswcThumb,cswcImage,cswcLink,cswcOrder",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cswcId,cswcTitle,cswcContnet,cswcThumb,cswcImage,cswcLink,cswcOrder",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"微信素材编辑":CTRL.title}</title>
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
	$("#wxcontentForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cswcId==true">
		${lz:set("haveEditable",true)}
		,"csWxContent.cswcId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswcTitle==true">
		${lz:set("haveEditable",true)}
		,"csWxContent.cswcTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "标题不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "标题最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswcContnet==true">
		${lz:set("haveEditable",true)}
		,"csWxContent.cswcContnet":function(el){
			if(el.value.length>1024 && el.value.indexOf("[*]")==-1)
				return "内容最大长度为1024个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswcThumb==true">
		${lz:set("haveEditable",true)}
		,"csWxContent.cswcThumb":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswcImage==true">
		${lz:set("haveEditable",true)}
		,"csWxContent.cswcImage":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswcLink==true">
		${lz:set("haveEditable",true)}
		,"csWxContent.cswcLink":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "链接地址最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswcOrder==true">
		${lz:set("haveEditable",true)}
		,"csWxContent.cswcOrder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswcAddTime==true">
		${lz:set("haveEditable",true)}
		,"csWxContent.cswcAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
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
	<form class="form editform" ref="CsWxContent" id="wxcontentForm" name="wxcontentForm" action="${empty CTRL.action?"wxcontent_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写微信素材相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csWxContentToken}" name="csWxContentToken" id="csWxContentToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csWxContent.cswcId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cswcId和after$cswcId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswcId==true">
	${before$cswcId}
	<dl class="cswcId ${CTRL.e.cswcId?"hidden":""}" major  ref="cswcId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cswcId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csWxContent$cswcId!=null">${csWxContent$cswcId}</s:if><s:else>
			<input type="hidden" value="${csWxContent.cswcId}" name="csWxContent.cswcId" id="cswcId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswcId">${csWxContent.cswcId}</textarea>
		 		<span>
		 	
			 ${csWxContent.cswcId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswcId}
	</s:if>
	
	${lz:set("注释","*****************标题字段的输入框代码*****************")}
	${lz:set("注释","before$cswcTitle和after$cswcTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswcTitle==true">
	${before$cswcTitle}
	<dl class="cswcTitle " major  ref="cswcTitle" >
		<dt>标　　题:</dt>
		<s:if test="#request.CTRL.e.cswcTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csWxContent$cswcTitle!=null">${csWxContent$cswcTitle}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csWxContent.cswcTitle" id="cswcTitle"  value="${csWxContent.cswcTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入微信素材的标题</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****标题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswcTitle">${csWxContent.cswcTitle}</textarea>
		 		<span>
		 	
			 ${csWxContent.cswcTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswcTitle}
	</s:if>
	
	${lz:set("注释","*****************内容字段的输入框代码*****************")}
	${lz:set("注释","before$cswcContnet和after$cswcContnet变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswcContnet==true">
	${before$cswcContnet}
	<dl class="cswcContnet " major  ref="cswcContnet" style="width:98%;">
		<dt>内　　容:</dt>
		<s:if test="#request.CTRL.e.cswcContnet==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csWxContent$cswcContnet!=null">${csWxContent$cswcContnet}</s:if><s:else>
		 	<textarea class="input wide"  id="cswcContnet" name="csWxContent.cswcContnet" rows="5">${csWxContent.cswcContnet}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入微信素材的内容</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****内容字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csWxContent.cswcContnet$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswcContnet}
	</s:if>
	
	${lz:set("注释","*****************缩略图字段的输入框代码*****************")}
	${lz:set("注释","before$cswcThumb和after$cswcThumb变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswcThumb==true">
	${before$cswcThumb}
	<dl class="cswcThumb " minor  ref="cswcThumb" style="width:98%;">
		<dt>缩&nbsp;&nbsp;略&nbsp;&nbsp;图:</dt>
		<s:if test="#request.CTRL.e.cswcThumb==true">
		${lz:set("haveEditable",true)}
		<dd input="image">
		<s:if test="#request.csWxContent$cswcThumb!=null">${csWxContent$cswcThumb}</s:if><s:else>
		    <input type="text" class="input" maxlength="128" size="32" name="csWxContent.cswcThumb" id="cswcThumb"  value="${csWxContent.cswcThumb}"/>
			<button type="button" onclick="$.upload({type:'img',width:80,height:80,callback:function(url){if(url)$('#cswcThumb').val(url)}})" class="button">设置图片</button>
			<button type="button" onclick="$('#cswcThumb').val('')" class="button">删除图片</button>
			<button type="button" onclick="$.thumb({url:$('#cswcThumb').val()})" class="button">查看图片</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传微信素材的缩略图</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****缩略图字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cswcThumb">${csWxContent.cswcThumb}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(csWxContent.cswcThumb))}
		 	<s:if test="#request.isVacant==false">
		 	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="${csWxContent.cswcThumb}"/>
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswcThumb}
	</s:if>
	
	${lz:set("注释","*****************图片字段的输入框代码*****************")}
	${lz:set("注释","before$cswcImage和after$cswcImage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswcImage==true">
	${before$cswcImage}
	<dl class="cswcImage " minor  ref="cswcImage" style="width:98%;">
		<dt>图　　片:</dt>
		<s:if test="#request.CTRL.e.cswcImage==true">
		${lz:set("haveEditable",true)}
		<dd input="image">
		<s:if test="#request.csWxContent$cswcImage!=null">${csWxContent$cswcImage}</s:if><s:else>
		    <input type="text" class="input" maxlength="128" size="32" name="csWxContent.cswcImage" id="cswcImage"  value="${csWxContent.cswcImage}"/>
			<button type="button" onclick="$.upload({type:'img',width:640,height:320,callback:function(url){if(url)$('#cswcImage').val(url)}})" class="button">设置图片</button>
			<button type="button" onclick="$('#cswcImage').val('')" class="button">删除图片</button>
			<button type="button" onclick="$.thumb({url:$('#cswcImage').val()})" class="button">查看图片</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传微信素材的图片</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****图片字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cswcImage">${csWxContent.cswcImage}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(csWxContent.cswcImage))}
		 	<s:if test="#request.isVacant==false">
		 	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="${csWxContent.cswcImage}"/>
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswcImage}
	</s:if>
	
	${lz:set("注释","*****************链接地址字段的输入框代码*****************")}
	${lz:set("注释","before$cswcLink和after$cswcLink变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswcLink==true">
	${before$cswcLink}
	<dl class="cswcLink " major  ref="cswcLink" style="width:98%;">
		<dt>链接地址:</dt>
		<s:if test="#request.CTRL.e.cswcLink==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csWxContent$cswcLink!=null">${csWxContent$cswcLink}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="256" name="csWxContent.cswcLink" id="cswcLink"  value="${csWxContent.cswcLink}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入微信素材的链接地址</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****链接地址字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cswcLink">${csWxContent.cswcLink}</textarea>
		 		<span>
		 	
			 ${csWxContent.cswcLink$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswcLink}
	</s:if>
	
	${lz:set("注释","*****************所属指令字段的输入框代码*****************")}
	${lz:set("注释","before$cswcOrder和after$cswcOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswcOrder==true">
	${before$cswcOrder}
	<dl class="cswcOrder " major  ref="cswcOrder" >
		<dt>所属指令:</dt>
		<s:if test="#request.CTRL.e.cswcOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csWxContent$cswcOrder!=null">${csWxContent$cswcOrder}</s:if><s:else>
		 			<input title="请输入微信指令指令名称进行查询" class="combox narrow" action="${basePath}${proname}/configurator/wx/wxorder_query.do?value={param}" type="text" id="cswcOrder" name="csWxContent.cswcOrder" text="${get:CsWxOrder(csWxContent.cswcOrder).cswoName}" value="${csWxContent.cswcOrder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cswcOrder').val())==''){return;};window.href('${basePath}${proname}/configurator/wx/wxorder_details.do?key='+$('#cswcOrder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属指令字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswcOrder">${csWxContent.cswcOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/wx/wxorder_details.do?key=${csWxContent.cswcOrder}',{ctrl:{editable:false,visible:true}})">
			 ${csWxContent.cswcOrder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswcOrder}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cswcAddTime和after$cswcAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswcAddTime==true">
	${before$cswcAddTime}
	<dl class="cswcAddTime " major  ref="cswcAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cswcAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csWxContent$cswcAddTime!=null">${csWxContent$cswcAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csWxContent.cswcAddTime" id="cswcAddTime"  value="<s:date name="csWxContent.cswcAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择微信素材的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswcAddTime">${csWxContent.cswcAddTime}</textarea>
		 		<span>
		 	
			 ${csWxContent.cswcAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswcAddTime}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csWxContent.cswcId}" name="csWxContent.cswcId" id="cswcId" />
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