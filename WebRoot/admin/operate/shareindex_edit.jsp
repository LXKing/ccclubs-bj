<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/operate/shareindex.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/operate/shareindex.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csShareIndex.cssiId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cssiId,cssiHost,cssiSource,cssiWebS,cssiWeiboS,cssiWeixinS,cssiStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cssiId,cssiHost,cssiSource,cssiWebS,cssiWeiboS,cssiWeixinS,cssiStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cssiId,cssiHost,cssiSource,cssiWebS,cssiWeiboS,cssiWeixinS,cssiStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cssiId,cssiHost,cssiSource,cssiWebS,cssiWeiboS,cssiWeixinS,cssiStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"分享指数编辑":CTRL.title}</title>
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
	$("#shareindexForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cssiId==true">
		${lz:set("haveEditable",true)}
		,"csShareIndex.cssiId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssiHost==true">
		${lz:set("haveEditable",true)}
		,"csShareIndex.cssiHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssiSource==true">
		${lz:set("haveEditable",true)}
		,"csShareIndex.cssiSource":function(el){
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("shareindex_query.do",{exists:true,cssiSource:el.value});
				if(exists && exists.length>0){
					if(${csShareIndex.cssiId==null} || exists[0].value!=$("#cssiId").val())
						return "分享人已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssiWebS==true">
		${lz:set("haveEditable",true)}
		,"csShareIndex.cssiWebS":function(el){
			if(jQuery.trim(el.value)=="")
				return "网站次数不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssiWeiboS==true">
		${lz:set("haveEditable",true)}
		,"csShareIndex.cssiWeiboS":function(el){
			if(jQuery.trim(el.value)=="")
				return "微博次数不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssiWeixinS==true">
		${lz:set("haveEditable",true)}
		,"csShareIndex.cssiWeixinS":function(el){
			if(jQuery.trim(el.value)=="")
				return "微信次数不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssiUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csShareIndex.cssiUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssiAddTime==true">
		${lz:set("haveEditable",true)}
		,"csShareIndex.cssiAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssiStatus==true">
		${lz:set("haveEditable",true)}
		,"csShareIndex.cssiStatus":function(el){
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
	<form class="form editform" ref="CsShareIndex" id="shareindexForm" name="shareindexForm" action="${empty CTRL.action?"shareindex_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写分享指数相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csShareIndexToken}" name="csShareIndexToken" id="csShareIndexToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csShareIndex.cssiId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cssiId和after$cssiId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssiId==true">
	${before$cssiId}
	<dl class="cssiId ${CTRL.e.cssiId?"hidden":""}" major  ref="cssiId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cssiId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csShareIndex$cssiId!=null">${csShareIndex$cssiId}</s:if><s:else>
			<input type="hidden" value="${csShareIndex.cssiId}" name="csShareIndex.cssiId" id="cssiId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssiId">${csShareIndex.cssiId}</textarea>
		 		<span>
		 	
			 ${csShareIndex.cssiId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssiId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cssiHost和after$cssiHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssiHost==true">
	${before$cssiHost}
	<dl class="cssiHost " major  ref="cssiHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cssiHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csShareIndex$cssiHost!=null">${csShareIndex$cssiHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cssiHost" name="csShareIndex.cssiHost">
		 				<option selected value="${csShareIndex.cssiHost}">
		 					${get:SrvHost(csShareIndex.cssiHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssiHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cssiHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssiHost">${csShareIndex.cssiHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csShareIndex.cssiHost}',{ctrl:{editable:false,visible:true}})">
			 ${csShareIndex.cssiHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssiHost}
	</s:if>
	
	${lz:set("注释","*****************分享人字段的输入框代码*****************")}
	${lz:set("注释","before$cssiSource和after$cssiSource变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssiSource==true">
	${before$cssiSource}
	<dl class="cssiSource " major  ref="cssiSource" >
		<dt>分&nbsp;&nbsp;享&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.cssiSource==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csShareIndex$cssiSource!=null">${csShareIndex$cssiSource}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}&csmHost={cssiHost}" type="text" id="cssiSource" name="csShareIndex.cssiSource" text="${get:CsMember(csShareIndex.cssiSource).csmName}" value="${csShareIndex.cssiSource}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssiSource').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#cssiSource').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****分享人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssiSource">${csShareIndex.cssiSource}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csShareIndex.cssiSource}',{ctrl:{editable:false,visible:true}})">
			 ${csShareIndex.cssiSource$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssiSource}
	</s:if>
	
	${lz:set("注释","*****************网站次数字段的输入框代码*****************")}
	${lz:set("注释","before$cssiWebS和after$cssiWebS变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssiWebS==true">
	${before$cssiWebS}
	<dl class="cssiWebS " major  ref="cssiWebS" >
		<dt>网站次数:</dt>
		<s:if test="#request.CTRL.e.cssiWebS==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csShareIndex$cssiWebS!=null">${csShareIndex$cssiWebS}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csShareIndex.cssiWebS" id="cssiWebS"  value="${csShareIndex.cssiWebS}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>通过网站分享方式</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****网站次数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssiWebS">${csShareIndex.cssiWebS}</textarea>
		 		<span>
		 	
			 ${csShareIndex.cssiWebS$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssiWebS}
	</s:if>
	
	${lz:set("注释","*****************微博次数字段的输入框代码*****************")}
	${lz:set("注释","before$cssiWeiboS和after$cssiWeiboS变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssiWeiboS==true">
	${before$cssiWeiboS}
	<dl class="cssiWeiboS " major  ref="cssiWeiboS" >
		<dt>微博次数:</dt>
		<s:if test="#request.CTRL.e.cssiWeiboS==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csShareIndex$cssiWeiboS!=null">${csShareIndex$cssiWeiboS}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csShareIndex.cssiWeiboS" id="cssiWeiboS"  value="${csShareIndex.cssiWeiboS}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>通过微博分享方式</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****微博次数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssiWeiboS">${csShareIndex.cssiWeiboS}</textarea>
		 		<span>
		 	
			 ${csShareIndex.cssiWeiboS$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssiWeiboS}
	</s:if>
	
	${lz:set("注释","*****************微信次数字段的输入框代码*****************")}
	${lz:set("注释","before$cssiWeixinS和after$cssiWeixinS变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssiWeixinS==true">
	${before$cssiWeixinS}
	<dl class="cssiWeixinS " major  ref="cssiWeixinS" >
		<dt>微信次数:</dt>
		<s:if test="#request.CTRL.e.cssiWeixinS==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csShareIndex$cssiWeixinS!=null">${csShareIndex$cssiWeixinS}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csShareIndex.cssiWeixinS" id="cssiWeixinS"  value="${csShareIndex.cssiWeixinS}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>通过微信分享方式</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****微信次数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssiWeixinS">${csShareIndex.cssiWeixinS}</textarea>
		 		<span>
		 	
			 ${csShareIndex.cssiWeixinS$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssiWeixinS}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cssiUpdateTime和after$cssiUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssiUpdateTime==true">
	${before$cssiUpdateTime}
	<dl class="cssiUpdateTime " major  ref="cssiUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cssiUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csShareIndex$cssiUpdateTime!=null">${csShareIndex$cssiUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csShareIndex.cssiUpdateTime" id="cssiUpdateTime"  value="<s:date name="csShareIndex.cssiUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择分享指数的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssiUpdateTime">${csShareIndex.cssiUpdateTime}</textarea>
		 		<span>
		 	
			 ${csShareIndex.cssiUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssiUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cssiAddTime和after$cssiAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssiAddTime==true">
	${before$cssiAddTime}
	<dl class="cssiAddTime " major  ref="cssiAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cssiAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csShareIndex$cssiAddTime!=null">${csShareIndex$cssiAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csShareIndex.cssiAddTime" id="cssiAddTime"  value="<s:date name="csShareIndex.cssiAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择分享指数的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssiAddTime">${csShareIndex.cssiAddTime}</textarea>
		 		<span>
		 	
			 ${csShareIndex.cssiAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssiAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cssiStatus和after$cssiStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssiStatus==true">
	${before$cssiStatus}
	<dl class="cssiStatus " major  ref="cssiStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cssiStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csShareIndex$cssiStatus!=null">${csShareIndex$cssiStatus}</s:if><s:else>
		 	<select class="narrow" id="cssiStatus" name="csShareIndex.cssiStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csShareIndex.cssiStatus==1?"selected":""}>正常</option>
				<option value="0" ${csShareIndex.cssiStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择分享指数的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssiStatus">${csShareIndex.cssiStatus}</textarea>
		 		<span>
		 	
			 ${csShareIndex.cssiStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssiStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csShareIndex.cssiId}" name="csShareIndex.cssiId" id="cssiId" />
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