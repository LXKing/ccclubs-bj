<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/systematic/oauth.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/systematic/oauth.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty srvOauth.soId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"soId,soToken,soUserId,soApiClient,soTicket",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"soId,soToken,soUserId,soApiClient,soTicket",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"soId,soToken,soUserId,soApiClient,soTicket",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"soId,soToken,soUserId,soApiClient,soTicket",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"用户授权编辑":CTRL.title}</title>
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
	$("#oauthForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.soId==true">
		${lz:set("haveEditable",true)}
		,"srvOauth.soId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.soToken==true">
		${lz:set("haveEditable",true)}
		,"srvOauth.soToken":function(el){
			if(jQuery.trim(el.value)=="")
				return "Token不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "Token最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.soUserId==true">
		${lz:set("haveEditable",true)}
		,"srvOauth.soUserId":function(el){
			if(jQuery.trim(el.value)=="")
				return "帐号ID不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.soApiClient==true">
		${lz:set("haveEditable",true)}
		,"srvOauth.soApiClient":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.soTicket==true">
		${lz:set("haveEditable",true)}
		,"srvOauth.soTicket":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "三方标识最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.soUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"srvOauth.soUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "登录时间不能为空";
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
	<form class="form editform" ref="SrvOauth" id="oauthForm" name="oauthForm" action="${empty CTRL.action?"oauth_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写用户授权相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${srvOauthToken}" name="srvOauthToken" id="srvOauthToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty srvOauth.soId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$soId和after$soId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.soId==true">
	${before$soId}
	<dl class="soId ${CTRL.e.soId?"hidden":""}" major  ref="soId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.soId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.srvOauth$soId!=null">${srvOauth$soId}</s:if><s:else>
			<input type="hidden" value="${srvOauth.soId}" name="srvOauth.soId" id="soId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="soId">${srvOauth.soId}</textarea>
		 		<span>
		 	
			 ${srvOauth.soId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$soId}
	</s:if>
	
	${lz:set("注释","*****************Token字段的输入框代码*****************")}
	${lz:set("注释","before$soToken和after$soToken变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.soToken==true">
	${before$soToken}
	<dl class="soToken " major  ref="soToken" >
		<dt>Token:</dt>
		<s:if test="#request.CTRL.e.soToken==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvOauth$soToken!=null">${srvOauth$soToken}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="srvOauth.soToken" id="soToken"  value="${srvOauth.soToken}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入用户授权的Token</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****Token字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="soToken">${srvOauth.soToken}</textarea>
		 		<span>
		 	
			 ${srvOauth.soToken$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$soToken}
	</s:if>
	
	${lz:set("注释","*****************帐号ID字段的输入框代码*****************")}
	${lz:set("注释","before$soUserId和after$soUserId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.soUserId==true">
	${before$soUserId}
	<dl class="soUserId " major  ref="soUserId" >
		<dt>帐号ID:</dt>
		<s:if test="#request.CTRL.e.soUserId==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.srvOauth$soUserId!=null">${srvOauth$soUserId}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="srvOauth.soUserId" id="soUserId"  value="${srvOauth.soUserId}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入用户授权的帐号ID</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****帐号ID字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="soUserId">${srvOauth.soUserId}</textarea>
		 		<span>
		 	
			 ${srvOauth.soUserId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$soUserId}
	</s:if>
	
	${lz:set("注释","*****************API客户端字段的输入框代码*****************")}
	${lz:set("注释","before$soApiClient和after$soApiClient变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.soApiClient==true">
	${before$soApiClient}
	<dl class="soApiClient " major  ref="soApiClient" >
		<dt>API客户端:</dt>
		<s:if test="#request.CTRL.e.soApiClient==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.srvOauth$soApiClient!=null">${srvOauth$soApiClient}</s:if><s:else>
		 			<input title="请输入API客户端客户端ID进行查询" class="combox narrow" action="${basePath}${proname}/configurator/apiclient_query.do?value={param}" type="text" id="soApiClient" name="srvOauth.soApiClient" text="${get:SrvApiClient(srvOauth.soApiClient).sacClient}" value="${srvOauth.soApiClient}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#soApiClient').val())==''){return;};window.href('${basePath}${proname}/configurator/apiclient_details.do?key='+$('#soApiClient').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****API客户端字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="soApiClient">${srvOauth.soApiClient}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/apiclient_details.do?key=${srvOauth.soApiClient}',{ctrl:{editable:false,visible:true}})">
			 ${srvOauth.soApiClient$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$soApiClient}
	</s:if>
	
	${lz:set("注释","*****************三方标识字段的输入框代码*****************")}
	${lz:set("注释","before$soTicket和after$soTicket变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.soTicket==true">
	${before$soTicket}
	<dl class="soTicket " major  ref="soTicket" >
		<dt>三方标识:</dt>
		<s:if test="#request.CTRL.e.soTicket==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvOauth$soTicket!=null">${srvOauth$soTicket}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="srvOauth.soTicket" id="soTicket"  value="${srvOauth.soTicket}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入用户授权的三方标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****三方标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="soTicket">${srvOauth.soTicket}</textarea>
		 		<span>
		 	
			 ${srvOauth.soTicket$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$soTicket}
	</s:if>
	
	${lz:set("注释","*****************登录时间字段的输入框代码*****************")}
	${lz:set("注释","before$soUpdateTime和after$soUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.soUpdateTime==true">
	${before$soUpdateTime}
	<dl class="soUpdateTime " major  ref="soUpdateTime" >
		<dt>登录时间:</dt>
		<s:if test="#request.CTRL.e.soUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.srvOauth$soUpdateTime!=null">${srvOauth$soUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="srvOauth.soUpdateTime" id="soUpdateTime"  value="<s:date name="srvOauth.soUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择用户授权的登录时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****登录时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="soUpdateTime">${srvOauth.soUpdateTime}</textarea>
		 		<span>
		 	
			 ${srvOauth.soUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$soUpdateTime}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${srvOauth.soId}" name="srvOauth.soId" id="soId" />
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