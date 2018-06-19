<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/configurator/apiclient.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/configurator/apiclient.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty srvApiClient.sacId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"sacId,sacClient,sacSecret,sacDesc,sacStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"sacId,sacClient,sacSecret,sacDesc,sacStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"sacId,sacClient,sacSecret,sacDesc,sacStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"sacId,sacClient,sacSecret,sacDesc,sacStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"API客户端编辑":CTRL.title}</title>
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
	$("#apiclientForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.sacId==true">
		${lz:set("haveEditable",true)}
		,"srvApiClient.sacId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.sacClient==true">
		${lz:set("haveEditable",true)}
		,"srvApiClient.sacClient":function(el){
			if(jQuery.trim(el.value)=="")
				return "客户端ID不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "客户端ID最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.sacSecret==true">
		${lz:set("haveEditable",true)}
		,"srvApiClient.sacSecret":function(el){
			if(jQuery.trim(el.value)=="")
				return "客户密钥不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "客户密钥最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.sacDesc==true">
		${lz:set("haveEditable",true)}
		,"srvApiClient.sacDesc":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "描述最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.sacUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"srvApiClient.sacUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.sacAddTime==true">
		${lz:set("haveEditable",true)}
		,"srvApiClient.sacAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.sacStatus==true">
		${lz:set("haveEditable",true)}
		,"srvApiClient.sacStatus":function(el){
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
	<form class="form editform" ref="SrvApiClient" id="apiclientForm" name="apiclientForm" action="${empty CTRL.action?"apiclient_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写API客户端相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${srvApiClientToken}" name="srvApiClientToken" id="srvApiClientToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty srvApiClient.sacId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$sacId和after$sacId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.sacId==true">
	${before$sacId}
	<dl class="sacId ${CTRL.e.sacId?"hidden":""}" major  ref="sacId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.sacId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.srvApiClient$sacId!=null">${srvApiClient$sacId}</s:if><s:else>
			<input type="hidden" value="${srvApiClient.sacId}" name="srvApiClient.sacId" id="sacId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="sacId">${srvApiClient.sacId}</textarea>
		 		<span>
		 	
			 ${srvApiClient.sacId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$sacId}
	</s:if>
	
	${lz:set("注释","*****************客户端ID字段的输入框代码*****************")}
	${lz:set("注释","before$sacClient和after$sacClient变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.sacClient==true">
	${before$sacClient}
	<dl class="sacClient " major  ref="sacClient" >
		<dt>客户端ID:</dt>
		<s:if test="#request.CTRL.e.sacClient==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvApiClient$sacClient!=null">${srvApiClient$sacClient}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="srvApiClient.sacClient" id="sacClient"  value="${srvApiClient.sacClient}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入API客户端的客户端ID</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****客户端ID字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="sacClient">${srvApiClient.sacClient}</textarea>
		 		<span>
		 	
			 ${srvApiClient.sacClient$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$sacClient}
	</s:if>
	
	${lz:set("注释","*****************客户密钥字段的输入框代码*****************")}
	${lz:set("注释","before$sacSecret和after$sacSecret变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.sacSecret==true">
	${before$sacSecret}
	<dl class="sacSecret " major  ref="sacSecret" >
		<dt>客户密钥:</dt>
		<s:if test="#request.CTRL.e.sacSecret==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvApiClient$sacSecret!=null">${srvApiClient$sacSecret}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="srvApiClient.sacSecret" id="sacSecret"  value="${srvApiClient.sacSecret}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入API客户端的客户密钥</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****客户密钥字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="sacSecret">${srvApiClient.sacSecret}</textarea>
		 		<span>
		 	
			 ${srvApiClient.sacSecret$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$sacSecret}
	</s:if>
	
	${lz:set("注释","*****************描述字段的输入框代码*****************")}
	${lz:set("注释","before$sacDesc和after$sacDesc变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.sacDesc==true">
	${before$sacDesc}
	<dl class="sacDesc " major  ref="sacDesc" style="width:98%;">
		<dt>描　　述:</dt>
		<s:if test="#request.CTRL.e.sacDesc==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.srvApiClient$sacDesc!=null">${srvApiClient$sacDesc}</s:if><s:else>
		 	<textarea class="input wide"  id="sacDesc" name="srvApiClient.sacDesc" rows="5">${srvApiClient.sacDesc}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入API客户端的描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${srvApiClient.sacDesc$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$sacDesc}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$sacUpdateTime和after$sacUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.sacUpdateTime==true">
	${before$sacUpdateTime}
	<dl class="sacUpdateTime " major  ref="sacUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.sacUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.srvApiClient$sacUpdateTime!=null">${srvApiClient$sacUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="srvApiClient.sacUpdateTime" id="sacUpdateTime"  value="<s:date name="srvApiClient.sacUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择API客户端的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="sacUpdateTime">${srvApiClient.sacUpdateTime}</textarea>
		 		<span>
		 	
			 ${srvApiClient.sacUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$sacUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$sacAddTime和after$sacAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.sacAddTime==true">
	${before$sacAddTime}
	<dl class="sacAddTime " major  ref="sacAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.sacAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.srvApiClient$sacAddTime!=null">${srvApiClient$sacAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="srvApiClient.sacAddTime" id="sacAddTime"  value="<s:date name="srvApiClient.sacAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择API客户端的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="sacAddTime">${srvApiClient.sacAddTime}</textarea>
		 		<span>
		 	
			 ${srvApiClient.sacAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$sacAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$sacStatus和after$sacStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.sacStatus==true">
	${before$sacStatus}
	<dl class="sacStatus " major  ref="sacStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.sacStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.srvApiClient$sacStatus!=null">${srvApiClient$sacStatus}</s:if><s:else>
		 	<select class="narrow" id="sacStatus" name="srvApiClient.sacStatus">
		 		<option value="">请选择</option>
				<option value="1" ${srvApiClient.sacStatus==1?"selected":""}>正常</option>
				<option value="0" ${srvApiClient.sacStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择API客户端的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="sacStatus">${srvApiClient.sacStatus}</textarea>
		 		<span>
		 	
			 ${srvApiClient.sacStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$sacStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${srvApiClient.sacId}" name="srvApiClient.sacId" id="sacId" />
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