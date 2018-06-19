<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/systematic/bug.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/systematic/bug.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csBug.csbId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csbId,csbTitle,csbDescript,csbImages,csbExpectTime",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csbId,csbTitle,csbDescript,csbImages,csbExpectTime",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csbId,csbTitle,csbDescript,csbImages,csbExpectTime,csbRemark",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csbId,csbTitle,csbDescript,csbImages,csbExpectTime,csbRemark",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"系统BUG编辑":CTRL.title}</title>
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



<!--LAZY3Q_JSP_BODY-->
<s:if test="#request.CTRL.title=='处理'">
	<lz:set name="jsonString">
		{
			title:"问题处理",
			action:"bug_dispose.do",
			editable:true,
			visible:true,
			fields:{
				csbId:{editable:true,visible:true},
				csbUpdateTime:{visible:false},
				csbTecher:{visible:false},
				csbAddTime:{editable:false,visible:true},
				csbExpectTime:{editable:false,visible:true},
			},
			submits:[{name:"提交处理",value:"提交处理"}]
		}
	</lz:set>
	${lz:set("CTRL",lz:CTRL(jsonString))}
</s:if>
<!--LAZY3Q_JSP_BODY-->



<script>
$(function(){	
	showTips("${tips.value}");
});
window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}";


$(function(){
		if($("#csbDescript").size()>0){
			 //构造编辑页csBug.csbDescript为html编辑控件
			 KE.init({
				id : 'csbDescript',
				width : '625px',
				height : '300px' , 
				imageUploadJson : '${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}?app=bug_edit&type=img${ lz:config("upload.url")==null ? "" : "&remote="}${ lz:config("upload.url")==null ? "" : basePath}${ lz:config("upload.url")==null ? "" : "callback.jsp"}'
			 });
			 KE.create("csbDescript");
		 }
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#bugForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csbId==true">
		${lz:set("haveEditable",true)}
		,"csBug.csbId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csbTitle==true">
		${lz:set("haveEditable",true)}
		,"csBug.csbTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "BUG标题不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "BUG标题最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csbDescript==true">
		${lz:set("haveEditable",true)}
		,"csBug.csbDescript":function(el){
			if($("#csbDescript").get(0))
			 	KE.sync("csbDescript");		 
			if(el.value.length>1024 && el.value.indexOf("[*]")==-1)
				return "BUG描述最大长度为1024个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csbImages==true">
		${lz:set("haveEditable",true)}
		,"csBug.csbImages":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csbLevel==true">
		${lz:set("haveEditable",true)}
		,"csBug.csbLevel":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择BUG级别";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csbTester==true">
		${lz:set("haveEditable",true)}
		,"csBug.csbTester":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csbTecher==true">
		${lz:set("haveEditable",true)}
		,"csBug.csbTecher":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csbExpectTime==true">
		${lz:set("haveEditable",true)}
		,"csBug.csbExpectTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csbUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csBug.csbUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csbAddTime==true">
		${lz:set("haveEditable",true)}
		,"csBug.csbAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csbRemark==true">
		${lz:set("haveEditable",true)}
		,"csBug.csbRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csbStatus==true">
		${lz:set("haveEditable",true)}
		,"csBug.csbStatus":function(el){
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
	<form class="form editform" ref="CsBug" id="bugForm" name="bugForm" action="${empty CTRL.action?"bug_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写系统BUG相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csBugToken}" name="csBugToken" id="csBugToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csBug.csbId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csbId和after$csbId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csbId==true">
	${before$csbId}
	<dl class="csbId ${CTRL.e.csbId?"hidden":""}" major  ref="csbId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csbId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csBug$csbId!=null">${csBug$csbId}</s:if><s:else>
			<input type="hidden" value="${csBug.csbId}" name="csBug.csbId" id="csbId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csbId">${csBug.csbId}</textarea>
		 		<span>
		 	
			 ${csBug.csbId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csbId}
	</s:if>
	
	${lz:set("注释","*****************BUG标题字段的输入框代码*****************")}
	${lz:set("注释","before$csbTitle和after$csbTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csbTitle==true">
	${before$csbTitle}
	<dl class="csbTitle " major  ref="csbTitle" >
		<dt>BUG标题:</dt>
		<s:if test="#request.CTRL.e.csbTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csBug$csbTitle!=null">${csBug$csbTitle}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csBug.csbTitle" id="csbTitle"  value="${csBug.csbTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入系统BUG的BUG标题</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****BUG标题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csbTitle">${csBug.csbTitle}</textarea>
		 		<span>
		 	
			 ${csBug.csbTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csbTitle}
	</s:if>
	
	${lz:set("注释","*****************BUG描述字段的输入框代码*****************")}
	${lz:set("注释","before$csbDescript和after$csbDescript变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csbDescript==true">
	${before$csbDescript}
	<dl class="csbDescript " major  ref="csbDescript" style="width:98%;">
		<dt>BUG描述:</dt>
		<s:if test="#request.CTRL.e.csbDescript==true">
		${lz:set("haveEditable",true)}
		<dd input="html">
		<s:if test="#request.csBug$csbDescript!=null">${csBug$csbDescript}</s:if><s:else>
		    <textarea class="input wide"  id="csbDescript" name="csBug.csbDescript" style="height:300px;visibility:hidden;">${csBug.csbDescript}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统BUG的BUG描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****BUG描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csBug.csbDescript$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csbDescript}
	</s:if>
	
	${lz:set("注释","*****************BUG截图字段的输入框代码*****************")}
	${lz:set("注释","before$csbImages和after$csbImages变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csbImages==true">
	${before$csbImages}
	<dl class="csbImages " minor  ref="csbImages" style="width:98%;">
		<dt>BUG截图:</dt>
		<s:if test="#request.CTRL.e.csbImages==true">
		${lz:set("haveEditable",true)}
		<dd input="imagelist">
		<s:if test="#request.csBug$csbImages!=null">${csBug$csbImages}</s:if><s:else>
		    <div id="csbImages" class="wide input imagelist">
			<s:generator val="csBug.csbImages" separator=",">
				<s:iterator id="item">
					${lz:set("isVacant",lz:vacant(item))}
		 			<s:if test="#request.isVacant==false">
					<div><a href="${item}" target="_blank"><img src="${item}"/><input type="hidden" name="csBug.csbImages" value="${item}"/></a><span onclick="$(this).parent().remove();">x</span></div>
					</s:if>
				</s:iterator>
			</s:generator>
			</div>
			<button type="button" onclick="$.upload({type:'img',count:10,callback:function(url){if(url)$('#csbImages').append('<div><a href=&quot;'+url+'&quot; target=&quot;_blank&quot;><img src=&quot;'+url+'&quot;/><input type=&quot;hidden&quot; name=&quot;csBug.csbImages&quot; value=&quot;'+url+'&quot;/></a><span onclick=&quot;$(this).parent().remove();&quot;>x</span></div>');}})" class="button">添加</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传系统BUG的BUG截图</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****BUG截图字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csbImages">${csBug.csbImages}</textarea>
		 		<span>
		 	 <s:generator val="csBug.csbImages" separator=",">
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
	${after$csbImages}
	</s:if>
	
	${lz:set("注释","*****************BUG级别字段的输入框代码*****************")}
	${lz:set("注释","before$csbLevel和after$csbLevel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csbLevel==true">
	${before$csbLevel}
	<dl class="csbLevel " major  ref="csbLevel" >
		<dt>BUG级别:</dt>
		<s:if test="#request.CTRL.e.csbLevel==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csBug$csbLevel!=null">${csBug$csbLevel}</s:if><s:else>
		 	<select class="narrow" id="csbLevel" name="csBug.csbLevel">
		 		<option value="">请选择</option>
				<option value="0" ${csBug.csbLevel==0?"selected":""}>未设置</option>
				<option value="1" ${csBug.csbLevel==1?"selected":""}>一级</option>
				<option value="2" ${csBug.csbLevel==2?"selected":""}>二级</option>
				<option value="3" ${csBug.csbLevel==3?"selected":""}>三级</option>
				<option value="4" ${csBug.csbLevel==4?"selected":""}>四级</option>
				<option value="5" ${csBug.csbLevel==5?"selected":""}>五级</option>
				<option value="6" ${csBug.csbLevel==6?"selected":""}>六级</option>
				<option value="7" ${csBug.csbLevel==7?"selected":""}>七级</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>级别越大，问题越大</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****BUG级别字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csbLevel">${csBug.csbLevel}</textarea>
		 		<span>
		 	
			 ${csBug.csbLevel$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csbLevel}
	</s:if>
	
	${lz:set("注释","*****************提交人字段的输入框代码*****************")}
	${lz:set("注释","before$csbTester和after$csbTester变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csbTester==true">
	${before$csbTester}
	<dl class="csbTester " major  ref="csbTester" >
		<dt>提&nbsp;&nbsp;交&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csbTester==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csBug$csbTester!=null">${csBug$csbTester}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csbTester" name="csBug.csbTester">
		 				<option selected value="${csBug.csbTester}">
		 					${get:SrvUser(csBug.csbTester).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csbTester').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csbTester').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****提交人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csbTester">${csBug.csbTester}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csBug.csbTester}',{ctrl:{editable:false,visible:true}})">
			 ${csBug.csbTester$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csbTester}
	</s:if>
	
	${lz:set("注释","*****************处理人字段的输入框代码*****************")}
	${lz:set("注释","before$csbTecher和after$csbTecher变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csbTecher==true">
	${before$csbTecher}
	<dl class="csbTecher " minor  ref="csbTecher" >
		<dt>处&nbsp;&nbsp;理&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csbTecher==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csBug$csbTecher!=null">${csBug$csbTecher}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csbTecher" name="csBug.csbTecher">
		 				<option selected value="${csBug.csbTecher}">
		 					${get:SrvUser(csBug.csbTecher).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csbTecher').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csbTecher').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****处理人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csbTecher">${csBug.csbTecher}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csBug.csbTecher}',{ctrl:{editable:false,visible:true}})">
			 ${csBug.csbTecher$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csbTecher}
	</s:if>
	
	${lz:set("注释","*****************期望时间字段的输入框代码*****************")}
	${lz:set("注释","before$csbExpectTime和after$csbExpectTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csbExpectTime==true">
	${before$csbExpectTime}
	<dl class="csbExpectTime " minor  ref="csbExpectTime" >
		<dt>期望时间:</dt>
		<s:if test="#request.CTRL.e.csbExpectTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csBug$csbExpectTime!=null">${csBug$csbExpectTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csBug.csbExpectTime" id="csbExpectTime"  value="<s:date name="csBug.csbExpectTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>期望问题解决的时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****期望时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csbExpectTime">${csBug.csbExpectTime}</textarea>
		 		<span>
		 	
			 ${csBug.csbExpectTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csbExpectTime}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csbUpdateTime和after$csbUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csbUpdateTime==true">
	${before$csbUpdateTime}
	<dl class="csbUpdateTime " major  ref="csbUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csbUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csBug$csbUpdateTime!=null">${csBug$csbUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csBug.csbUpdateTime" id="csbUpdateTime"  value="<s:date name="csBug.csbUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统BUG的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csbUpdateTime">${csBug.csbUpdateTime}</textarea>
		 		<span>
		 	
			 ${csBug.csbUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csbUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csbAddTime和after$csbAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csbAddTime==true">
	${before$csbAddTime}
	<dl class="csbAddTime " major  ref="csbAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csbAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csBug$csbAddTime!=null">${csBug$csbAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csBug.csbAddTime" id="csbAddTime"  value="<s:date name="csBug.csbAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统BUG的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csbAddTime">${csBug.csbAddTime}</textarea>
		 		<span>
		 	
			 ${csBug.csbAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csbAddTime}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$csbRemark和after$csbRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csbRemark==true">
	${before$csbRemark}
	<dl class="csbRemark " minor  ref="csbRemark" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.csbRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csBug$csbRemark!=null">${csBug$csbRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csbRemark" name="csBug.csbRemark" rows="5">${csBug.csbRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统BUG的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csBug.csbRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csbRemark}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csbStatus和after$csbStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csbStatus==true">
	${before$csbStatus}
	<dl class="csbStatus " major  ref="csbStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csbStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csBug$csbStatus!=null">${csBug$csbStatus}</s:if><s:else>
		 	<select class="narrow" id="csbStatus" name="csBug.csbStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csBug.csbStatus==0?"selected":""}>已提交</option>
				<option value="1" ${csBug.csbStatus==1?"selected":""}>待处理</option>
				<option value="2" ${csBug.csbStatus==2?"selected":""}>已处理</option>
				<option value="3" ${csBug.csbStatus==3?"selected":""}>待商榷</option>
				<option value="4" ${csBug.csbStatus==4?"selected":""}>已否决</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统BUG的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csbStatus">${csBug.csbStatus}</textarea>
		 		<span>
		 	
			 ${csBug.csbStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csbStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csBug.csbId}" name="csBug.csbId" id="csbId" />
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