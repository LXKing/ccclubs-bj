<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/systematic/transaction.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/systematic/transaction.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty srvTransaction.stId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"stId,stEntry,stDetails,stModels",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"stId,stEntry,stDetails,stModels",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"stId,stEntry,stDetails,stModels",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"stId,stEntry,stDetails,stModels",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"数据事务编辑":CTRL.title}</title>
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
	$("#transactionForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.stId==true">
		${lz:set("haveEditable",true)}
		,"srvTransaction.stId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.stEntry==true">
		${lz:set("haveEditable",true)}
		,"srvTransaction.stEntry":function(el){
			if(jQuery.trim(el.value)=="")
				return "调用入口不能为空";
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "调用入口最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.stDetails==true">
		${lz:set("haveEditable",true)}
		,"srvTransaction.stDetails":function(el){
			if(jQuery.trim(el.value)=="")
				return "调用详情不能为空";
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "调用详情最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.stModels==true">
		${lz:set("haveEditable",true)}
		,"srvTransaction.stModels":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改模块不能为空";
			if(el.value.length>512 && el.value.indexOf("[*]")==-1)
				return "修改模块最大长度为512个字符";
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
	<form class="form editform" ref="SrvTransaction" id="transactionForm" name="transactionForm" action="${empty CTRL.action?"transaction_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写数据事务相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${srvTransactionToken}" name="srvTransactionToken" id="srvTransactionToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty srvTransaction.stId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$stId和after$stId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.stId==true">
	${before$stId}
	<dl class="stId ${CTRL.e.stId?"hidden":""}" major  ref="stId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.stId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.srvTransaction$stId!=null">${srvTransaction$stId}</s:if><s:else>
			<input type="hidden" value="${srvTransaction.stId}" name="srvTransaction.stId" id="stId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="stId">${srvTransaction.stId}</textarea>
		 		<span>
		 	
			 ${srvTransaction.stId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$stId}
	</s:if>
	
	${lz:set("注释","*****************调用入口字段的输入框代码*****************")}
	${lz:set("注释","before$stEntry和after$stEntry变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.stEntry==true">
	${before$stEntry}
	<dl class="stEntry " major  ref="stEntry" style="width:98%;">
		<dt>调用入口:</dt>
		<s:if test="#request.CTRL.e.stEntry==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.srvTransaction$stEntry!=null">${srvTransaction$stEntry}</s:if><s:else>
		 	<textarea class="input wide"  id="stEntry" name="srvTransaction.stEntry" rows="5">${srvTransaction.stEntry}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入数据事务的调用入口</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****调用入口字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${srvTransaction.stEntry$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$stEntry}
	</s:if>
	
	${lz:set("注释","*****************调用详情字段的输入框代码*****************")}
	${lz:set("注释","before$stDetails和after$stDetails变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.stDetails==true">
	${before$stDetails}
	<dl class="stDetails " major  ref="stDetails" style="width:98%;">
		<dt>调用详情:</dt>
		<s:if test="#request.CTRL.e.stDetails==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.srvTransaction$stDetails!=null">${srvTransaction$stDetails}</s:if><s:else>
		 	<textarea class="input wide"  id="stDetails" name="srvTransaction.stDetails" rows="5">${srvTransaction.stDetails}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入数据事务的调用详情</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****调用详情字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${srvTransaction.stDetails$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$stDetails}
	</s:if>
	
	${lz:set("注释","*****************修改模块字段的输入框代码*****************")}
	${lz:set("注释","before$stModels和after$stModels变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.stModels==true">
	${before$stModels}
	<dl class="stModels " major  ref="stModels" style="width:98%;">
		<dt>修改模块:</dt>
		<s:if test="#request.CTRL.e.stModels==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.srvTransaction$stModels!=null">${srvTransaction$stModels}</s:if><s:else>
		 	<textarea class="input wide"  id="stModels" name="srvTransaction.stModels" rows="5">${srvTransaction.stModels}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入数据事务的修改模块</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改模块字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${srvTransaction.stModels$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$stModels}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${srvTransaction.stId}" name="srvTransaction.stId" id="stId" />
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