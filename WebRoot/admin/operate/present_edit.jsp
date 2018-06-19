<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/operate/present.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/operate/present.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csPresent.cspId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cspId,cspHost,cspName,cspType,cspImage,cspImages,cspProfile,cspCount,cspStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cspId,cspHost,cspName,cspType,cspImage,cspImages,cspProfile,cspCount,cspStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cspId,cspHost,cspName,cspType,cspImage,cspImages,cspProfile,cspCount,cspStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cspId,cspHost,cspName,cspType,cspImage,cspImages,cspProfile,cspCount,cspStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"礼品赠品编辑":CTRL.title}</title>
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
		if($("#cspProfile").size()>0){
			 //构造编辑页csPresent.cspProfile为html编辑控件
			 KE.init({
				id : 'cspProfile',
				width : '625px',
				height : '300px' , 
				imageUploadJson : '${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}?app=present_edit&type=img${ lz:config("upload.url")==null ? "" : "&remote="}${ lz:config("upload.url")==null ? "" : basePath}${ lz:config("upload.url")==null ? "" : "callback.jsp"}'
			 });
			 KE.create("cspProfile");
		 }
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#presentForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cspId==true">
		${lz:set("haveEditable",true)}
		,"csPresent.cspId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspHost==true">
		${lz:set("haveEditable",true)}
		,"csPresent.cspHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspName==true">
		${lz:set("haveEditable",true)}
		,"csPresent.cspName":function(el){
			if(jQuery.trim(el.value)=="")
				return "礼品名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "礼品名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspType==true">
		${lz:set("haveEditable",true)}
		,"csPresent.cspType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择礼品分类";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspImage==true">
		${lz:set("haveEditable",true)}
		,"csPresent.cspImage":function(el){
			if(jQuery.trim(el.value)=="")
				return "主图不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspImages==true">
		${lz:set("haveEditable",true)}
		,"csPresent.cspImages":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspProfile==true">
		${lz:set("haveEditable",true)}
		,"csPresent.cspProfile":function(el){
			if($("#cspProfile").get(0))
			 	KE.sync("cspProfile");		 
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "详细信息最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspCount==true">
		${lz:set("haveEditable",true)}
		,"csPresent.cspCount":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csPresent.cspUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspAddTime==true">
		${lz:set("haveEditable",true)}
		,"csPresent.cspAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspStatus==true">
		${lz:set("haveEditable",true)}
		,"csPresent.cspStatus":function(el){
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
	<form class="form editform" ref="CsPresent" id="presentForm" name="presentForm" action="${empty CTRL.action?"present_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写礼品赠品相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csPresentToken}" name="csPresentToken" id="csPresentToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csPresent.cspId))}		
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
		<s:if test="#request.csPresent$cspId!=null">${csPresent$cspId}</s:if><s:else>
			<input type="hidden" value="${csPresent.cspId}" name="csPresent.cspId" id="cspId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspId">${csPresent.cspId}</textarea>
		 		<span>
		 	
			 ${csPresent.cspId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cspHost和after$cspHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspHost==true">
	${before$cspHost}
	<dl class="cspHost " major  ref="cspHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cspHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csPresent$cspHost!=null">${csPresent$cspHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cspHost" name="csPresent.cspHost">
		 				<option selected value="${csPresent.cspHost}">
		 					${get:SrvHost(csPresent.cspHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cspHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cspHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspHost">${csPresent.cspHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csPresent.cspHost}',{ctrl:{editable:false,visible:true}})">
			 ${csPresent.cspHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspHost}
	</s:if>
	
	${lz:set("注释","*****************礼品名称字段的输入框代码*****************")}
	${lz:set("注释","before$cspName和after$cspName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspName==true">
	${before$cspName}
	<dl class="cspName " major  ref="cspName" >
		<dt>礼品名称:</dt>
		<s:if test="#request.CTRL.e.cspName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csPresent$cspName!=null">${csPresent$cspName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csPresent.cspName" id="cspName"  value="${csPresent.cspName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入礼品赠品的礼品名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****礼品名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspName">${csPresent.cspName}</textarea>
		 		<span>
		 	
			 ${csPresent.cspName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspName}
	</s:if>
	
	${lz:set("注释","*****************礼品分类字段的输入框代码*****************")}
	${lz:set("注释","before$cspType和after$cspType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspType==true">
	${before$cspType}
	<dl class="cspType " major  ref="cspType" >
		<dt>礼品分类:</dt>
		<s:if test="#request.CTRL.e.cspType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csPresent$cspType!=null">${csPresent$cspType}</s:if><s:else>
		 	<select class="narrow" id="cspType" name="csPresent.cspType">
		 		<option value="">请选择</option>
 					${lz:set("items", get:propertys("礼品赠品分类","present_type"))}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.spId}" ${csPresent.cspType==item.spId?"selected":""}>${item.spName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cspType').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?key='+$('#cspType').val(),{ctrl:{editable:false}})"></a>
	 			<a class="reladd" config="{
	 				action:'${basePath}${proname}/configurator/property_edit.do?rd=1&parent=${get:property("present_type").spId}'
	 				,callback:function(object){
	 					$('<option selected value='+object.spId+'>'+object.spName+'</option>').appendTo('#cspType');
	 				}
	 			}" href="javascript:void(0);"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择礼品赠品的礼品分类</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****礼品分类字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspType">${csPresent.cspType}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${csPresent.cspType}',{ctrl:{editable:false,visible:true}})">
			 ${csPresent.cspType$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspType}
	</s:if>
	
	${lz:set("注释","*****************主图字段的输入框代码*****************")}
	${lz:set("注释","before$cspImage和after$cspImage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspImage==true">
	${before$cspImage}
	<dl class="cspImage " major  ref="cspImage" style="width:98%;">
		<dt>主　　图:</dt>
		<s:if test="#request.CTRL.e.cspImage==true">
		${lz:set("haveEditable",true)}
		<dd input="image">
		<s:if test="#request.csPresent$cspImage!=null">${csPresent$cspImage}</s:if><s:else>
		    <input type="text" class="input" maxlength="128" size="32" name="csPresent.cspImage" id="cspImage"  value="${csPresent.cspImage}"/>
			<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#cspImage').val(url)}})" class="button">设置图片</button>
			<button type="button" onclick="$('#cspImage').val('')" class="button">删除图片</button>
			<button type="button" onclick="$.thumb({url:$('#cspImage').val()})" class="button">查看图片</button>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请上传礼品赠品的主图</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****主图字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cspImage">${csPresent.cspImage}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(csPresent.cspImage))}
		 	<s:if test="#request.isVacant==false">
		 	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="${csPresent.cspImage}"/>
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspImage}
	</s:if>
	
	${lz:set("注释","*****************图列表字段的输入框代码*****************")}
	${lz:set("注释","before$cspImages和after$cspImages变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspImages==true">
	${before$cspImages}
	<dl class="cspImages " minor  ref="cspImages" style="width:98%;">
		<dt>图&nbsp;&nbsp;列&nbsp;&nbsp;表:</dt>
		<s:if test="#request.CTRL.e.cspImages==true">
		${lz:set("haveEditable",true)}
		<dd input="imagelist">
		<s:if test="#request.csPresent$cspImages!=null">${csPresent$cspImages}</s:if><s:else>
		    <div id="cspImages" class="wide input imagelist">
			<s:generator val="csPresent.cspImages" separator=",">
				<s:iterator id="item">
					${lz:set("isVacant",lz:vacant(item))}
		 			<s:if test="#request.isVacant==false">
					<div><a href="${item}" target="_blank"><img src="${item}"/><input type="hidden" name="csPresent.cspImages" value="${item}"/></a><span onclick="$(this).parent().remove();">x</span></div>
					</s:if>
				</s:iterator>
			</s:generator>
			</div>
			<button type="button" onclick="$.upload({type:'img',count:10,callback:function(url){if(url)$('#cspImages').append('<div><a href=&quot;'+url+'&quot; target=&quot;_blank&quot;><img src=&quot;'+url+'&quot;/><input type=&quot;hidden&quot; name=&quot;csPresent.cspImages&quot; value=&quot;'+url+'&quot;/></a><span onclick=&quot;$(this).parent().remove();&quot;>x</span></div>');}})" class="button">添加</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传礼品赠品的图列表</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****图列表字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cspImages">${csPresent.cspImages}</textarea>
		 		<span>
		 	 <s:generator val="csPresent.cspImages" separator=",">
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
	${after$cspImages}
	</s:if>
	
	${lz:set("注释","*****************详细信息字段的输入框代码*****************")}
	${lz:set("注释","before$cspProfile和after$cspProfile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspProfile==true">
	${before$cspProfile}
	<dl class="cspProfile " minor  ref="cspProfile" style="width:98%;">
		<dt>详细信息:</dt>
		<s:if test="#request.CTRL.e.cspProfile==true">
		${lz:set("haveEditable",true)}
		<dd input="html">
		<s:if test="#request.csPresent$cspProfile!=null">${csPresent$cspProfile}</s:if><s:else>
		    <textarea class="input wide"  id="cspProfile" name="csPresent.cspProfile" style="height:300px;visibility:hidden;">${csPresent.cspProfile}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入礼品赠品的详细信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****详细信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csPresent.cspProfile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspProfile}
	</s:if>
	
	${lz:set("注释","*****************库存数字段的输入框代码*****************")}
	${lz:set("注释","before$cspCount和after$cspCount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspCount==true">
	${before$cspCount}
	<dl class="cspCount " major  ref="cspCount" >
		<dt>库&nbsp;&nbsp;存&nbsp;&nbsp;数:</dt>
		<s:if test="#request.CTRL.e.cspCount==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csPresent$cspCount!=null">${csPresent$cspCount}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csPresent.cspCount" id="cspCount"  value="${csPresent.cspCount}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入礼品赠品的库存数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****库存数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspCount">${csPresent.cspCount}</textarea>
		 		<span>
		 	
			 ${csPresent.cspCount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspCount}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cspUpdateTime和after$cspUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspUpdateTime==true">
	${before$cspUpdateTime}
	<dl class="cspUpdateTime " major  ref="cspUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cspUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csPresent$cspUpdateTime!=null">${csPresent$cspUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csPresent.cspUpdateTime" id="cspUpdateTime"  value="<s:date name="csPresent.cspUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择礼品赠品的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspUpdateTime">${csPresent.cspUpdateTime}</textarea>
		 		<span>
		 	
			 ${csPresent.cspUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspUpdateTime}
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
		<s:if test="#request.csPresent$cspAddTime!=null">${csPresent$cspAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csPresent.cspAddTime" id="cspAddTime"  value="<s:date name="csPresent.cspAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择礼品赠品的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspAddTime">${csPresent.cspAddTime}</textarea>
		 		<span>
		 	
			 ${csPresent.cspAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspAddTime}
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
		<s:if test="#request.csPresent$cspStatus!=null">${csPresent$cspStatus}</s:if><s:else>
		 	<select class="narrow" id="cspStatus" name="csPresent.cspStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csPresent.cspStatus==1?"selected":""}>正常</option>
				<option value="0" ${csPresent.cspStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择礼品赠品的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspStatus">${csPresent.cspStatus}</textarea>
		 		<span>
		 	
			 ${csPresent.cspStatus$}
	 	  
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
					<input type="hidden" value="${csPresent.cspId}" name="csPresent.cspId" id="cspId" />
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