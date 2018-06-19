<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/configurator/argument.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/configurator/argument.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csArgument.csaId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csaId,csaValue,csaDescribe",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csaId,csaName,csaFlag,csaValue,csaDescribe",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"系统参数编辑":CTRL.title}</title>
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
	$("#argumentForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csaId==true">
		${lz:set("haveEditable",true)}
		,"csArgument.csaId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaName==true">
		${lz:set("haveEditable",true)}
		,"csArgument.csaName":function(el){
			if(jQuery.trim(el.value)=="")
				return "参数名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "参数名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaFlag==true">
		${lz:set("haveEditable",true)}
		,"csArgument.csaFlag":function(el){
			if(jQuery.trim(el.value)=="")
				return "参数标识不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "参数标识最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("argument_query.do",{exists:true,csaFlag:el.value});
				if(exists && exists.length>0){
					if(${csArgument.csaId==null} || exists[0].value!=$("#csaId").val())
						return "参数标识已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaValue==true">
		${lz:set("haveEditable",true)}
		,"csArgument.csaValue":function(el){
			if(jQuery.trim(el.value)=="")
				return "参数值不能为空";
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "参数值最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaDescribe==true">
		${lz:set("haveEditable",true)}
		,"csArgument.csaDescribe":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "参数描述最大长度为256个字符";
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
	<form class="form editform" ref="CsArgument" id="argumentForm" name="argumentForm" action="${empty CTRL.action?"argument_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写系统参数相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csArgumentToken}" name="csArgumentToken" id="csArgumentToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csArgument.csaId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csaId和after$csaId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaId==true">
	${before$csaId}
	<dl class="csaId ${CTRL.e.csaId?"hidden":""}" major  ref="csaId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csaId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csArgument$csaId!=null">${csArgument$csaId}</s:if><s:else>
			<input type="hidden" value="${csArgument.csaId}" name="csArgument.csaId" id="csaId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaId">${csArgument.csaId}</textarea>
		 		<span>
		 	
			 ${csArgument.csaId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaId}
	</s:if>
	
	${lz:set("注释","*****************参数名称字段的输入框代码*****************")}
	${lz:set("注释","before$csaName和after$csaName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaName==true">
	${before$csaName}
	<dl class="csaName " major  ref="csaName" >
		<dt>参数名称:</dt>
		<s:if test="#request.CTRL.e.csaName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csArgument$csaName!=null">${csArgument$csaName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csArgument.csaName" id="csaName"  value="${csArgument.csaName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入系统参数的参数名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****参数名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaName">${csArgument.csaName}</textarea>
		 		<span>
		 	
			 ${csArgument.csaName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaName}
	</s:if>
	
	${lz:set("注释","*****************参数标识字段的输入框代码*****************")}
	${lz:set("注释","before$csaFlag和after$csaFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaFlag==true">
	${before$csaFlag}
	<dl class="csaFlag " major  ref="csaFlag" >
		<dt>参数标识:</dt>
		<s:if test="#request.CTRL.e.csaFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csArgument$csaFlag!=null">${csArgument$csaFlag}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csArgument.csaFlag" id="csaFlag"  value="${csArgument.csaFlag}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入系统参数的参数标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****参数标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaFlag">${csArgument.csaFlag}</textarea>
		 		<span>
		 	
			 ${csArgument.csaFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaFlag}
	</s:if>
	
	${lz:set("注释","*****************参数值字段的输入框代码*****************")}
	${lz:set("注释","before$csaValue和after$csaValue变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaValue==true">
	${before$csaValue}
	<dl class="csaValue " major  ref="csaValue" style="width:98%;">
		<dt>参&nbsp;&nbsp;数&nbsp;&nbsp;值:</dt>
		<s:if test="#request.CTRL.e.csaValue==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csArgument$csaValue!=null">${csArgument$csaValue}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="256" name="csArgument.csaValue" id="csaValue"  value="${csArgument.csaValue}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入系统参数的参数值</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****参数值字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csaValue">${csArgument.csaValue}</textarea>
		 		<span>
		 	
			 ${csArgument.csaValue$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaValue}
	</s:if>
	
	${lz:set("注释","*****************参数描述字段的输入框代码*****************")}
	${lz:set("注释","before$csaDescribe和after$csaDescribe变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaDescribe==true">
	${before$csaDescribe}
	<dl class="csaDescribe " major  ref="csaDescribe" style="width:98%;">
		<dt>参数描述:</dt>
		<s:if test="#request.CTRL.e.csaDescribe==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csArgument$csaDescribe!=null">${csArgument$csaDescribe}</s:if><s:else>
		 	<textarea class="input wide"  id="csaDescribe" name="csArgument.csaDescribe" rows="5">${csArgument.csaDescribe}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统参数的参数描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****参数描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csArgument.csaDescribe$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaDescribe}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csArgument.csaId}" name="csArgument.csaId" id="csaId" />
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