<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/csm/complain.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/csm/complain.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csComplain.cscId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cscId,cscHost,cscLevel,cscContent,cscType,cscMember",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscId,cscHost,cscLevel,cscContent,cscType,cscMember",
	</s:if>
	<s:elseif test="#request.CTRL.title=='处理反馈'">
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cscSource,nothing",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscLevel,cscLevel1,cscContent,cscType,cscMember,cscSource,nothing",
	</s:elseif>
	<s:elseif test="#request.CTRL.title=='意见采纳'">
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"nothing",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscLevel,cscLevel1,cscContent,cscType,cscMember,nothing",
	</s:elseif>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cscId,cscHost,cscLevel,cscLevel1,cscContent,cscType,cscMember,cscSource,cscObject",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscId,cscHost,cscLevel,cscLevel1,cscContent,cscType,cscMember,cscSource,cscObject,cscUpdateTime,cscAddTime,cscAccept,cscStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"意见反馈编辑":CTRL.title}</title>
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
			 //构造编辑页csComplain.cscContent为html编辑控件
			 KE.init({
				id : 'cscContent',
				width : '625px',
				height : '300px' , 
				imageUploadJson : '${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}?app=complain_edit&type=img${ lz:config("upload.url")==null ? "" : "&remote="}${ lz:config("upload.url")==null ? "" : basePath}${ lz:config("upload.url")==null ? "" : "callback.jsp"}'
			 });
			 KE.create("cscContent");
		 }
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#complainForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cscId==true">
		${lz:set("haveEditable",true)}
		,"csComplain.cscId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscHost==true">
		${lz:set("haveEditable",true)}
		,"csComplain.cscHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscLevel==true">
		${lz:set("haveEditable",true)}
		,"csComplain.cscLevel":function(el){
			if(jQuery.trim(el.value)=="")
				return "反馈标题不能为空";
			if(el.value.length>64 && el.value.indexOf("[*]")==-1)
				return "反馈标题最大长度为64个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscContent==true">
		${lz:set("haveEditable",true)}
		,"csComplain.cscContent":function(el){
			if($("#cscContent").get(0))
			 	KE.sync("cscContent");		 
			if(jQuery.trim(el.value)=="")
				return "反馈内容不能为空";
			if(el.value.length>512 && el.value.indexOf("[*]")==-1)
				return "反馈内容最大长度为512个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscType==true">
		${lz:set("haveEditable",true)}
		,"csComplain.cscType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscMember==true">
		${lz:set("haveEditable",true)}
		,"csComplain.cscMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscSource==true">
		${lz:set("haveEditable",true)}
		,"csComplain.cscSource":function(el){
			if(el.value.length>512 && el.value.indexOf("[*]")==-1)
				return "处理描述最大长度为512个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscObject==true">
		${lz:set("haveEditable",true)}
		,"csComplain.cscObject":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csComplain.cscUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "处理时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscAddTime==true">
		${lz:set("haveEditable",true)}
		,"csComplain.cscAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscAccept==true">
		${lz:set("haveEditable",true)}
		,"csComplain.cscAccept":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择意见采纳";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscStatus==true">
		${lz:set("haveEditable",true)}
		,"csComplain.cscStatus":function(el){
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
<s:elseif test="#request.CTRL.title=='处理反馈'">
	<form class="form editform" ref="CsComplain" id="complainForm" name="complainForm" action="${empty CTRL.action?"complain_deal.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<div class="prompt">
					温馨提示：请仔细填写意见反馈相关信息，<span class="extrude">"*" 为必填选项。</span>
				</div>
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
				<input type="hidden" value="${csComplainToken}" name="csComplainToken" id="csComplainToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	
	${lz:set("注释","*****************反馈内容字段的输入框代码*****************")}
	${lz:set("注释","before$cscContent和after$cscContent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscContent}
	<dl class="cscContent " major  ref="cscContent" style="width:98%;">
		<dt>反馈内容:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="html">
		<s:if test="#request.csComplain$cscContent!=null">${csComplain$cscContent}</s:if><s:else>
		    <textarea class="input wide"  id="cscContent" name="csComplain.cscContent" style="height:300px;visibility:hidden;">${csComplain.cscContent}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入意见反馈的反馈内容</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****反馈内容字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csComplain.cscContent$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscContent}
	
	${lz:set("注释","*****************反馈标题字段的输入框代码*****************")}
	${lz:set("注释","before$cscLevel和after$cscLevel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscLevel}
	<dl class="cscLevel " major  ref="cscLevel" style="width:98%;">
		<dt>反馈标题:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csComplain$cscLevel!=null">${csComplain$cscLevel}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="64" name="csComplain.cscLevel" id="cscLevel"  value="${csComplain.cscLevel}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入意见反馈的反馈标题</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****反馈标题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscLevel">${csComplain.cscLevel}</textarea>
		 		<span>
		 	
			 ${csComplain.cscLevel$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscLevel}
	
	${lz:set("注释","*****************类型字段的输入框代码*****************")}
	${lz:set("注释","before$cscType和after$cscType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscType}
	<dl class="cscType " major  ref="cscType" >
		<dt>类　　型:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csComplain$cscType!=null">${csComplain$cscType}</s:if><s:else>
		 	<select class="narrow" id="cscType" name="csComplain.cscType">
		 		<option value="">请选择</option>
		 		 <option value="">无法自动构造</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择意见反馈的类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscType">${csComplain.cscType}</textarea>
		 		<span>
		 	
			 ${csComplain.cscType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscType}
	
	
	${lz:set("注释","*****************反馈人字段的输入框代码*****************")}
	${lz:set("注释","before$cscMember和after$cscMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscMember}
	<dl class="cscMember " major  ref="cscMember" >
		<dt>反&nbsp;&nbsp;馈&nbsp;&nbsp;人:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csComplain$cscMember!=null">${csComplain$cscMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={cscHost}" type="text" id="cscMember" name="csComplain.cscMember" text="${get:CsMember(csComplain.cscMember).csmName}" value="${csComplain.cscMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#cscMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****反馈人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscMember">${csComplain.cscMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csComplain.cscMember}',{ctrl:{editable:false,visible:true}})">
			 ${csComplain.cscMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscMember}
	
	
	${lz:set("注释","*****************处理描述字段的输入框代码*****************")}
	${lz:set("注释","before$cscSource和after$cscSource变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscSource}
	<dl class="cscSource " minor  ref="cscSource" style="width:98%;">
		<dt>处理描述:</dt>
		<s:if test="true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csComplain$cscSource!=null">${csComplain$cscSource}</s:if><s:else>
		 	<textarea class="input wide"  id="cscSource" name="csComplain.cscSource" rows="5">${csComplain.cscSource}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入意见反馈的处理描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****处理描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csComplain.cscSource$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscSource}
	
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<input type="hidden" value="${csComplain.cscId}" name="csComplain.cscId" id="cscId" />
					<button title="保存数据,关闭窗口,刷新原列表" class="button" type="submit" name="submiter" value="完成">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-tick.png"/>
						完成&nbsp;&nbsp;</button>
					<button title="不保存数据,关闭窗口,不刷新原列表" class="button" onclick="cancel();" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-multiply.png"/>
						取消&nbsp;&nbsp;</button>
				</center>
			</div>	
		</div>
		<div class="foot"></div>		
	</form>
</s:elseif>
<s:elseif test="#request.CTRL.title=='意见采纳'">
	<form class="form editform" ref="CsComplain" id="complainForm" name="complainForm" action="${empty CTRL.action?"complain_accept.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<div class="prompt">
					温馨提示：请仔细填写意见反馈相关信息，<span class="extrude">"*" 为必填选项。</span>
				</div>
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
				<input type="hidden" value="${csComplainToken}" name="csComplainToken" id="csComplainToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************反馈标题字段的输入框代码*****************")}
	${lz:set("注释","before$cscLevel和after$cscLevel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscLevel}
	<dl class="cscLevel " major  ref="cscLevel" style="width:98%;">
		<dt>反馈标题:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csComplain$cscLevel!=null">${csComplain$cscLevel}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="64" name="csComplain.cscLevel" id="cscLevel"  value="${csComplain.cscLevel}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入意见反馈的反馈标题</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****反馈标题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscLevel">${csComplain.cscLevel}</textarea>
		 		<span>
		 	
			 ${csComplain.cscLevel$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscLevel}
	
	
	${lz:set("注释","*****************反馈内容字段的输入框代码*****************")}
	${lz:set("注释","before$cscContent和after$cscContent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscContent}
	<dl class="cscContent " major  ref="cscContent" style="width:98%;">
		<dt>反馈内容:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="html">
		<s:if test="#request.csComplain$cscContent!=null">${csComplain$cscContent}</s:if><s:else>
		    <textarea class="input wide"  id="cscContent" name="csComplain.cscContent" style="height:300px;visibility:hidden;">${csComplain.cscContent}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入意见反馈的反馈内容</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****反馈内容字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csComplain.cscContent$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscContent}
	
	
	${lz:set("注释","*****************类型字段的输入框代码*****************")}
	${lz:set("注释","before$cscType和after$cscType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscType}
	<dl class="cscType " major  ref="cscType" >
		<dt>类　　型:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csComplain$cscType!=null">${csComplain$cscType}</s:if><s:else>
		 	<select class="narrow" id="cscType" name="csComplain.cscType">
		 		<option value="">请选择</option>
		 		 <option value="">无法自动构造</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择意见反馈的类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscType">${csComplain.cscType}</textarea>
		 		<span>
		 	
			 ${csComplain.cscType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscType}
	
	
	${lz:set("注释","*****************反馈人字段的输入框代码*****************")}
	${lz:set("注释","before$cscMember和after$cscMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	
	${before$cscMember}
	<dl class="cscMember " major  ref="cscMember" >
		<dt>反&nbsp;&nbsp;馈&nbsp;&nbsp;人:</dt>
		<s:if test="false">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csComplain$cscMember!=null">${csComplain$cscMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={cscHost}" type="text" id="cscMember" name="csComplain.cscMember" text="${get:CsMember(csComplain.cscMember).csmName}" value="${csComplain.cscMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#cscMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****反馈人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscMember">${csComplain.cscMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csComplain.cscMember}',{ctrl:{editable:false,visible:true}})">
			 ${csComplain.cscMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscMember}
	
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<input type="hidden" value="${csComplain.cscId}" name="csComplain.cscId" id="cscId" />
						<button class="button" type="submit" name="submiter" value="意见采纳">
						&nbsp;&nbsp;意见采纳&nbsp;&nbsp;</button>
					<button title="不保存数据,关闭窗口,不刷新原列表" class="button" onclick="cancel();" type="button">
						&nbsp;<img align="absmiddle" width="20" src="${basePath}admin/images/icons/Badge-multiply.png"/>
						取消&nbsp;&nbsp;</button>
				</center>
			</div>	
		</div>
		<div class="foot"></div>		
	</form>
</s:elseif>
<s:else>
	<form class="form editform" ref="CsComplain" id="complainForm" name="complainForm" action="${empty CTRL.action?"complain_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写意见反馈相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csComplainToken}" name="csComplainToken" id="csComplainToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csComplain.cscId))}		
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
		<s:if test="#request.csComplain$cscId!=null">${csComplain$cscId}</s:if><s:else>
			<input type="hidden" value="${csComplain.cscId}" name="csComplain.cscId" id="cscId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscId">${csComplain.cscId}</textarea>
		 		<span>
		 	
			 ${csComplain.cscId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cscHost和after$cscHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscHost==true">
	${before$cscHost}
	<dl class="cscHost " major  ref="cscHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cscHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csComplain$cscHost!=null">${csComplain$cscHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cscHost" name="csComplain.cscHost">
		 				<option selected value="${csComplain.cscHost}">
		 					${get:SrvHost(csComplain.cscHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cscHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscHost">${csComplain.cscHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csComplain.cscHost}',{ctrl:{editable:false,visible:true}})">
			 ${csComplain.cscHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscHost}
	</s:if>
	
	${lz:set("注释","*****************反馈内容字段的输入框代码*****************")}
	${lz:set("注释","before$cscContent和after$cscContent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscContent==true">
	${before$cscContent}
	<dl class="cscContent " major  ref="cscContent" style="width:98%;">
		<dt>反馈内容:</dt>
		<s:if test="#request.CTRL.e.cscContent==true">
		${lz:set("haveEditable",true)}
		<dd input="html">
		<s:if test="#request.csComplain$cscContent!=null">${csComplain$cscContent}</s:if><s:else>
		    <textarea class="input wide"  id="cscContent" name="csComplain.cscContent" style="height:300px;visibility:hidden;">${csComplain.cscContent}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入意见反馈的反馈内容</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****反馈内容字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csComplain.cscContent$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscContent}
	</s:if>
	
	${lz:set("注释","*****************星级字段的输入框代码*****************")}
	${lz:set("注释","before$cscLevel和after$cscLevel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscLevel==true">
	${before$cscLevel}
	<dl class="cscLevel " major  ref="cscLevel" style="width:98%;">
		<dt>车辆整洁评分:</dt>
		<s:if test="#request.CTRL.e.cscLevel==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csComplain$cscLevel!=null">${csComplain$cscLevel}</s:if><s:else>
	 	 	<select class="narrow" id="cscType" name="csComplain.cscLevel">
		 		<option value="">请选择</option>
		 		<option value="0" ${csComplain.cscLevel==0?"selected":""}>未评论</option>
		 		<option value="1" ${csComplain.cscLevel==1?"selected":""}>一星</option>
				<option value="2" ${csComplain.cscLevel==2?"selected":""}>二星</option>
				<option value="3" ${csComplain.cscLevel==3?"selected":""}>三星</option>
				<option value="4" ${csComplain.cscLevel==4?"selected":""}>四星</option>
				<option value="4" ${csComplain.cscLevel==5?"selected":""}>五星</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入评论星级</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****评论星级字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscLevel">${csComplain.cscLevel}</textarea>
		 		<span>
		 	
			 ${csComplain.cscLevel$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscLevel}
	</s:if>
	
	
	${lz:set("注释","*****************星级字段的输入框代码*****************")}
	${lz:set("注释","before$cscLevel1和after$cscLevel1变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscLevel1==true">
	${before$cscLevel1}
	<dl class="cscLevel1 " major  ref="cscLevel1" style="width:98%;">
		<dt>车辆性能评分:</dt>
		<s:if test="#request.CTRL.e.cscLevel1==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csComplain$cscLevel1!=null">${csComplain$cscLevel1}</s:if><s:else>
	 	 	<select class="narrow" id="cscType" name="csComplain.cscLevel1">
		 		<option value="">请选择</option>
		 		<option value="0" ${csComplain.cscLevel1==0?"selected":""}>未评论</option>
		 		<option value="1" ${csComplain.cscLevel1==1?"selected":""}>一星</option>
				<option value="2" ${csComplain.cscLevel1==2?"selected":""}>二星</option>
				<option value="3" ${csComplain.cscLevel1==3?"selected":""}>三星</option>
				<option value="4" ${csComplain.cscLevel1==4?"selected":""}>四星</option>
				<option value="4" ${csComplain.cscLevel1==5?"selected":""}>五星</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入评论星级</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****评论星级字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscLevel1">${csComplain.cscLevel1}</textarea>
		 		<span>
		 	
			 ${csComplain.cscLevel1$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscLevel1}
	</s:if>
	
	
	
	${lz:set("注释","*****************类型字段的输入框代码*****************")}
	${lz:set("注释","before$cscType和after$cscType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscType==true">
	${before$cscType}
	<dl class="cscType " major  ref="cscType" >
		<dt>类　　型:</dt>
		<s:if test="#request.CTRL.e.cscType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csComplain$cscType!=null">${csComplain$cscType}</s:if><s:else>
		 	<select class="narrow" id="cscType" name="csComplain.cscType">
		 		<option value="">请选择</option>
				<option value="3" ${csComplain.cscType==3?"selected":""}>产品建议</option>
				<option value="4" ${csComplain.cscType==4?"selected":""}>驾驶体验</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择意见反馈的类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscType">${csComplain.cscType}</textarea>
		 		<span>
		 	
			 ${csComplain.cscType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscType}
	</s:if>
	
	${lz:set("注释","*****************来源字段的输入框代码*****************")}
	${lz:set("注释","before$cscSource和after$cscSource变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscSource==true">
	${before$cscSource}
	<dl class="cscSource " minor  ref="cscSource">
		<dt>来源:</dt>
		<s:if test="#request.CTRL.e.cscSource==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csComplain$cscSource!=null">${csComplain$cscSource}</s:if><s:else>
		 	<select class="narrow" id="cscSource" name="csComplain.cscSource">
		 		<option value="">请选择</option>
				<option value="1" ${csComplain.cscSource==1?"selected":""}>订单评论</option>
				<option value="2" ${csComplain.cscSource==2?"selected":""}>帮助与反馈</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择来源</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****处理描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<span>
		 	
			 ${csComplain.cscSource$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscSource}
	</s:if>
	
	${lz:set("注释","*****************订单字段的输入框代码*****************")}
	${lz:set("注释","before$cscObject和after$cscObject变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscObject==true">
	${before$cscObject}
	<dl class="cscObject " minor  ref="cscObject" >
		<dt>订单编号:</dt>
		<s:if test="#request.CTRL.e.cscObject==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csComplain$cscObject!=null">${csComplain$cscObject}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csComplain.cscObject" id="cscObject"  value="${csComplain.cscObject}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入订单编号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****处理人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscObject">${csComplain.cscObject}</textarea>
		 		<span>
		 	<a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?key=${fn:split(csComplain.cscObject$,"@")[1]}',{ctrl:{editable:false,visible:true}})">
			 ${csComplain.cscObject$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscObject}
	</s:if>
	
	
	${lz:set("注释","*****************反馈人字段的输入框代码*****************")}
	${lz:set("注释","before$cscMember和after$cscMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscMember==true">
	${before$cscMember}
	<dl class="cscMember " major  ref="cscMember" >
		<dt>会员:</dt>
		<s:if test="#request.CTRL.e.cscMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csComplain$cscMember!=null">${csComplain$cscMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_inquire.do?value={param}&csmHost={cscHost}" type="text" id="cscMember" name="csComplain.cscMember" text="${get:CsMember(csComplain.cscMember).csmName}" value="${csComplain.cscMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#cscMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****反馈人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscMember">${csComplain.cscMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csComplain.cscMember}',{ctrl:{editable:false,visible:true}})">
			 ${csComplain.cscMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscMember}
	</s:if>
	
	
	
	
	
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscAddTime和after$cscAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscAddTime==true">
	${before$cscAddTime}
	<dl class="cscAddTime " major  ref="cscAddTime" >
		<dt>评价时间:</dt>
		<s:if test="#request.CTRL.e.cscAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csComplain$cscAddTime!=null">${csComplain$cscAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csComplain.cscAddTime" id="cscAddTime"  value="<s:date name="csComplain.cscAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择意见反馈的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscAddTime">${csComplain.cscAddTime}</textarea>
		 		<span>
		 	
			 ${csComplain.cscAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscAddTime}
	</s:if>
	
				
		
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscUpdateTime和after$cscUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscUpdateTime==true">
	${before$cscUpdateTime}
	<dl class="cscUpdateTime " major  ref="cscUpdateTime" >
		<dt>更新时间:</dt>
		<s:if test="#request.CTRL.e.cscUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csComplain$cscUpdateTime!=null">${csComplain$cscUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csComplain.cscUpdateTime" id="cscUpdateTime"  value="<s:date name="csComplain.cscUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择意见反馈的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscUpdateTime">${csComplain.cscUpdateTime}</textarea>
		 		<span>
		 	
			 ${csComplain.cscUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscUpdateTime}
	</s:if>
			
				
				
				
				
				
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csComplain.cscId}" name="csComplain.cscId" id="cscId" />
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