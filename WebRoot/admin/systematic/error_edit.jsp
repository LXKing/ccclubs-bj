<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/systematic/error.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/systematic/error.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csError.cseId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cseId,cseTitle,cseContent,cseFlag,cseStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cseId,cseTitle,cseContent,cseFlag,cseStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cseId,cseTitle,cseContent,cseFlag,cseStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cseId,cseTitle,cseContent,cseFlag,cseStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"错误信息编辑":CTRL.title}</title>
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
		if($("#cseContent").size()>0){
			 //构造编辑页csError.cseContent为html编辑控件
			 KE.init({
				id : 'cseContent',
				width : '625px',
				height : '300px' , 
				imageUploadJson : '${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}?app=error_edit&type=img${ lz:config("upload.url")==null ? "" : "&remote="}${ lz:config("upload.url")==null ? "" : basePath}${ lz:config("upload.url")==null ? "" : "callback.jsp"}'
			 });
			 KE.create("cseContent");
		 }
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#errorForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cseId==true">
		${lz:set("haveEditable",true)}
		,"csError.cseId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cseTitle==true">
		${lz:set("haveEditable",true)}
		,"csError.cseTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "错误标题不能为空";
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "错误标题最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cseContent==true">
		${lz:set("haveEditable",true)}
		,"csError.cseContent":function(el){
			if($("#cseContent").get(0))
			 	KE.sync("cseContent");		 
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "错误内容最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cseFlag==true">
		${lz:set("haveEditable",true)}
		,"csError.cseFlag":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "标识最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cseUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csError.cseUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cseAddTime==true">
		${lz:set("haveEditable",true)}
		,"csError.cseAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cseStatus==true">
		${lz:set("haveEditable",true)}
		,"csError.cseStatus":function(el){
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
	<form class="form editform" ref="CsError" id="errorForm" name="errorForm" action="${empty CTRL.action?"error_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写错误信息相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csErrorToken}" name="csErrorToken" id="csErrorToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csError.cseId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cseId和after$cseId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cseId==true">
	${before$cseId}
	<dl class="cseId ${CTRL.e.cseId?"hidden":""}" major  ref="cseId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cseId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csError$cseId!=null">${csError$cseId}</s:if><s:else>
			<input type="hidden" value="${csError.cseId}" name="csError.cseId" id="cseId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cseId">${csError.cseId}</textarea>
		 		<span>
		 	
			 ${csError.cseId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cseId}
	</s:if>
	
	${lz:set("注释","*****************错误标题字段的输入框代码*****************")}
	${lz:set("注释","before$cseTitle和after$cseTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cseTitle==true">
	${before$cseTitle}
	<dl class="cseTitle " major  ref="cseTitle" style="width:98%;">
		<dt>错误标题:</dt>
		<s:if test="#request.CTRL.e.cseTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csError$cseTitle!=null">${csError$cseTitle}</s:if><s:else>
		 	<textarea class="input wide"  id="cseTitle" name="csError.cseTitle" rows="5">${csError.cseTitle}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入错误信息的错误标题</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****错误标题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csError.cseTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cseTitle}
	</s:if>
	
	${lz:set("注释","*****************错误内容字段的输入框代码*****************")}
	${lz:set("注释","before$cseContent和after$cseContent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cseContent==true">
	${before$cseContent}
	<dl class="cseContent " major  ref="cseContent" style="width:98%;">
		<dt>错误内容:</dt>
		<s:if test="#request.CTRL.e.cseContent==true">
		${lz:set("haveEditable",true)}
		<dd input="html">
		<s:if test="#request.csError$cseContent!=null">${csError$cseContent}</s:if><s:else>
		    <textarea class="input wide"  id="cseContent" name="csError.cseContent" style="height:300px;visibility:hidden;">${csError.cseContent}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入错误信息的错误内容</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****错误内容字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csError.cseContent$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cseContent}
	</s:if>
	
	${lz:set("注释","*****************标识字段的输入框代码*****************")}
	${lz:set("注释","before$cseFlag和after$cseFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cseFlag==true">
	${before$cseFlag}
	<dl class="cseFlag " major  ref="cseFlag" >
		<dt>标　　识:</dt>
		<s:if test="#request.CTRL.e.cseFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csError$cseFlag!=null">${csError$cseFlag}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csError.cseFlag" id="cseFlag"  value="${csError.cseFlag}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入错误信息的标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cseFlag">${csError.cseFlag}</textarea>
		 		<span>
		 	
			 ${csError.cseFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cseFlag}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cseUpdateTime和after$cseUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cseUpdateTime==true">
	${before$cseUpdateTime}
	<dl class="cseUpdateTime " major  ref="cseUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cseUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csError$cseUpdateTime!=null">${csError$cseUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csError.cseUpdateTime" id="cseUpdateTime"  value="<s:date name="csError.cseUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择错误信息的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cseUpdateTime">${csError.cseUpdateTime}</textarea>
		 		<span>
		 	
			 ${csError.cseUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cseUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cseAddTime和after$cseAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cseAddTime==true">
	${before$cseAddTime}
	<dl class="cseAddTime " major  ref="cseAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cseAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csError$cseAddTime!=null">${csError$cseAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csError.cseAddTime" id="cseAddTime"  value="<s:date name="csError.cseAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择错误信息的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cseAddTime">${csError.cseAddTime}</textarea>
		 		<span>
		 	
			 ${csError.cseAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cseAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cseStatus和after$cseStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cseStatus==true">
	${before$cseStatus}
	<dl class="cseStatus " major  ref="cseStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cseStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csError$cseStatus!=null">${csError$cseStatus}</s:if><s:else>
		 	<select class="narrow" id="cseStatus" name="csError.cseStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csError.cseStatus==1?"selected":""}>正常</option>
				<option value="0" ${csError.cseStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择错误信息的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cseStatus">${csError.cseStatus}</textarea>
		 		<span>
		 	
			 ${csError.cseStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cseStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csError.cseId}" name="csError.cseId" id="cseId" />
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