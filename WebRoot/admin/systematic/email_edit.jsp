<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/systematic/email.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/systematic/email.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csEmail.cseId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cseId,cseTo,cseContent,cseFrom,cseStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cseId,cseTo,cseContent,cseFrom,cseStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cseId,cseTo,cseContent,cseFrom,cseStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cseId,cseTo,cseContent,cseFrom,cseStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"邮件内容编辑":CTRL.title}</title>
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
			 //构造编辑页csEmail.cseContent为html编辑控件
			 KE.init({
				id : 'cseContent',
				width : '625px',
				height : '300px' , 
				imageUploadJson : '${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}?app=email_edit&type=img${ lz:config("upload.url")==null ? "" : "&remote="}${ lz:config("upload.url")==null ? "" : basePath}${ lz:config("upload.url")==null ? "" : "callback.jsp"}'
			 });
			 KE.create("cseContent");
		 }
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#emailForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cseId==true">
		${lz:set("haveEditable",true)}
		,"csEmail.cseId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cseTo==true">
		${lz:set("haveEditable",true)}
		,"csEmail.cseTo":function(el){
			if(jQuery.trim(el.value)=="")
				return "接收人不能为空";
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "接收人最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cseContent==true">
		${lz:set("haveEditable",true)}
		,"csEmail.cseContent":function(el){
			if($("#cseContent").get(0))
			 	KE.sync("cseContent");		 
			if(jQuery.trim(el.value)=="")
				return "邮件内容不能为空";
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "邮件内容最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cseFrom==true">
		${lz:set("haveEditable",true)}
		,"csEmail.cseFrom":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "发送人最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cseSendTime==true">
		${lz:set("haveEditable",true)}
		,"csEmail.cseSendTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cseAddTime==true">
		${lz:set("haveEditable",true)}
		,"csEmail.cseAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cseStatus==true">
		${lz:set("haveEditable",true)}
		,"csEmail.cseStatus":function(el){
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
	<form class="form editform" ref="CsEmail" id="emailForm" name="emailForm" action="${empty CTRL.action?"email_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写邮件内容相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csEmailToken}" name="csEmailToken" id="csEmailToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csEmail.cseId))}		
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
		<s:if test="#request.csEmail$cseId!=null">${csEmail$cseId}</s:if><s:else>
			<input type="hidden" value="${csEmail.cseId}" name="csEmail.cseId" id="cseId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cseId">${csEmail.cseId}</textarea>
		 		<span>
		 	
			 ${csEmail.cseId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cseId}
	</s:if>
	
	${lz:set("注释","*****************接收人字段的输入框代码*****************")}
	${lz:set("注释","before$cseTo和after$cseTo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cseTo==true">
	${before$cseTo}
	<dl class="cseTo " major  ref="cseTo" style="width:98%;">
		<dt>接&nbsp;&nbsp;收&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.cseTo==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csEmail$cseTo!=null">${csEmail$cseTo}</s:if><s:else>
		 	<textarea class="input wide"  id="cseTo" name="csEmail.cseTo" rows="5">${csEmail.cseTo}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入邮件内容的接收人</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****接收人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csEmail.cseTo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cseTo}
	</s:if>
	
	${lz:set("注释","*****************邮件内容字段的输入框代码*****************")}
	${lz:set("注释","before$cseContent和after$cseContent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cseContent==true">
	${before$cseContent}
	<dl class="cseContent " major  ref="cseContent" style="width:98%;">
		<dt>邮件内容:</dt>
		<s:if test="#request.CTRL.e.cseContent==true">
		${lz:set("haveEditable",true)}
		<dd input="html">
		<s:if test="#request.csEmail$cseContent!=null">${csEmail$cseContent}</s:if><s:else>
		    <textarea class="input wide"  id="cseContent" name="csEmail.cseContent" style="height:300px;visibility:hidden;">${csEmail.cseContent}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入邮件内容的邮件内容</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****邮件内容字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csEmail.cseContent$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cseContent}
	</s:if>
	
	${lz:set("注释","*****************发送人字段的输入框代码*****************")}
	${lz:set("注释","before$cseFrom和after$cseFrom变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cseFrom==true">
	${before$cseFrom}
	<dl class="cseFrom " major  ref="cseFrom" style="width:98%;">
		<dt>发&nbsp;&nbsp;送&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.cseFrom==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csEmail$cseFrom!=null">${csEmail$cseFrom}</s:if><s:else>
		 	<textarea class="input wide"  id="cseFrom" name="csEmail.cseFrom" rows="5">${csEmail.cseFrom}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入邮件内容的发送人</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****发送人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csEmail.cseFrom$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cseFrom}
	</s:if>
	
	${lz:set("注释","*****************发送时间字段的输入框代码*****************")}
	${lz:set("注释","before$cseSendTime和after$cseSendTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cseSendTime==true">
	${before$cseSendTime}
	<dl class="cseSendTime " major  ref="cseSendTime" >
		<dt>发送时间:</dt>
		<s:if test="#request.CTRL.e.cseSendTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csEmail$cseSendTime!=null">${csEmail$cseSendTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csEmail.cseSendTime" id="cseSendTime"  value="<s:date name="csEmail.cseSendTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择邮件内容的发送时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****发送时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cseSendTime">${csEmail.cseSendTime}</textarea>
		 		<span>
		 	
			 ${csEmail.cseSendTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cseSendTime}
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
		<s:if test="#request.csEmail$cseAddTime!=null">${csEmail$cseAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csEmail.cseAddTime" id="cseAddTime"  value="<s:date name="csEmail.cseAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择邮件内容的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cseAddTime">${csEmail.cseAddTime}</textarea>
		 		<span>
		 	
			 ${csEmail.cseAddTime$}
	 	  
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
		<s:if test="#request.csEmail$cseStatus!=null">${csEmail$cseStatus}</s:if><s:else>
		 	<select class="narrow" id="cseStatus" name="csEmail.cseStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csEmail.cseStatus==0?"selected":""}>待发送</option>
				<option value="1" ${csEmail.cseStatus==1?"selected":""}>发送成功</option>
				<option value="2" ${csEmail.cseStatus==2?"selected":""}>发送失败</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择邮件内容的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cseStatus">${csEmail.cseStatus}</textarea>
		 		<span>
		 	
			 ${csEmail.cseStatus$}
	 	  
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
					<input type="hidden" value="${csEmail.cseId}" name="csEmail.cseId" id="cseId" />
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