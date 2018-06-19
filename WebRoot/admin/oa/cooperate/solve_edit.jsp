<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/oa/cooperate/solve.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/oa/cooperate/solve.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty tbSolve.tbsId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"tbsId,tbsHost,tbsDesc,tbsProplem,tbsUser,tbsRedirect,tbsStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"tbsId,tbsHost,tbsDesc,tbsProplem,tbsUser,tbsRedirect,tbsStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"tbsId,tbsHost,tbsDesc,tbsProplem,tbsUser,tbsRedirect,tbsStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"tbsId,tbsHost,tbsDesc,tbsProplem,tbsUser,tbsRedirect,tbsStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"问题处理记录编辑":CTRL.title}</title>
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
	$("#solveForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.tbsId==true">
		${lz:set("haveEditable",true)}
		,"tbSolve.tbsId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsHost==true">
		${lz:set("haveEditable",true)}
		,"tbSolve.tbsHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsDesc==true">
		${lz:set("haveEditable",true)}
		,"tbSolve.tbsDesc":function(el){
			if(jQuery.trim(el.value)=="")
				return "处理描述不能为空";
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "处理描述最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsProplem==true">
		${lz:set("haveEditable",true)}
		,"tbSolve.tbsProplem":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsUser==true">
		${lz:set("haveEditable",true)}
		,"tbSolve.tbsUser":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsRedirect==true">
		${lz:set("haveEditable",true)}
		,"tbSolve.tbsRedirect":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.tbsStatus==true">
		${lz:set("haveEditable",true)}
		,"tbSolve.tbsStatus":function(el){
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
	<form class="form editform" ref="TbSolve" id="solveForm" name="solveForm" action="${empty CTRL.action?"solve_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写问题处理记录相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${tbSolveToken}" name="tbSolveToken" id="tbSolveToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty tbSolve.tbsId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$tbsId和after$tbsId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsId==true">
	${before$tbsId}
	<dl class="tbsId ${CTRL.e.tbsId?"hidden":""}" major  ref="tbsId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.tbsId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.tbSolve$tbsId!=null">${tbSolve$tbsId}</s:if><s:else>
			<input type="hidden" value="${tbSolve.tbsId}" name="tbSolve.tbsId" id="tbsId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsId">${tbSolve.tbsId}</textarea>
		 		<span>
		 	
			 ${tbSolve.tbsId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$tbsHost和after$tbsHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsHost==true">
	${before$tbsHost}
	<dl class="tbsHost " major  ref="tbsHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.tbsHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbSolve$tbsHost!=null">${tbSolve$tbsHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="tbsHost" name="tbSolve.tbsHost">
		 				<option selected value="${tbSolve.tbsHost}">
		 					${get:SrvHost(tbSolve.tbsHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbsHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#tbsHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsHost">${tbSolve.tbsHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${tbSolve.tbsHost}',{ctrl:{editable:false,visible:true}})">
			 ${tbSolve.tbsHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsHost}
	</s:if>
	
	${lz:set("注释","*****************处理描述字段的输入框代码*****************")}
	${lz:set("注释","before$tbsDesc和after$tbsDesc变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsDesc==true">
	${before$tbsDesc}
	<dl class="tbsDesc " major  ref="tbsDesc" style="width:98%;">
		<dt>处理描述:</dt>
		<s:if test="#request.CTRL.e.tbsDesc==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.tbSolve$tbsDesc!=null">${tbSolve$tbsDesc}</s:if><s:else>
		 	<textarea class="input wide"  id="tbsDesc" name="tbSolve.tbsDesc" rows="5">${tbSolve.tbsDesc}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入问题处理记录的处理描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****处理描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${tbSolve.tbsDesc$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsDesc}
	</s:if>
	
	${lz:set("注释","*****************所属问题字段的输入框代码*****************")}
	${lz:set("注释","before$tbsProplem和after$tbsProplem变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsProplem==true">
	${before$tbsProplem}
	<dl class="tbsProplem " major  ref="tbsProplem" >
		<dt>所属问题:</dt>
		<s:if test="#request.CTRL.e.tbsProplem==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.tbSolve$tbsProplem!=null">${tbSolve$tbsProplem}</s:if><s:else>
		 			<input title="请输入问题故障报修问题描述进行查询" class="combox narrow" action="${basePath}${proname}/oa/cooperate/problem_query.do?value={param}&tbpHost={tbsHost}" type="text" id="tbsProplem" name="tbSolve.tbsProplem" text="${get:TbProblem(tbSolve.tbsProplem).tbpProfile}" value="${tbSolve.tbsProplem}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbsProplem').val())==''){return;};window.href('${basePath}${proname}/oa/cooperate/problem_details.do?key='+$('#tbsProplem').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属问题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsProplem">${tbSolve.tbsProplem}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/oa/cooperate/problem_details.do?key=${tbSolve.tbsProplem}',{ctrl:{editable:false,visible:true}})">
			 ${tbSolve.tbsProplem$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsProplem}
	</s:if>
	
	${lz:set("注释","*****************处理人字段的输入框代码*****************")}
	${lz:set("注释","before$tbsUser和after$tbsUser变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsUser==true">
	${before$tbsUser}
	<dl class="tbsUser " major  ref="tbsUser" >
		<dt>处&nbsp;&nbsp;理&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.tbsUser==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.tbSolve$tbsUser!=null">${tbSolve$tbsUser}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="tbsUser" name="tbSolve.tbsUser">
		 				<option selected value="${tbSolve.tbsUser}">
		 					${get:SrvUser(tbSolve.tbsUser).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#tbsUser').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#tbsUser').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****处理人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsUser">${tbSolve.tbsUser}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${tbSolve.tbsUser}',{ctrl:{editable:false,visible:true}})">
			 ${tbSolve.tbsUser$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsUser}
	</s:if>
	
	${lz:set("注释","*****************流转到字段的输入框代码*****************")}
	${lz:set("注释","before$tbsRedirect和after$tbsRedirect变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsRedirect==true">
	${before$tbsRedirect}
	<dl class="tbsRedirect " major  ref="tbsRedirect" >
		<dt>流&nbsp;&nbsp;转&nbsp;&nbsp;到:</dt>
		<s:if test="#request.CTRL.e.tbsRedirect==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.tbSolve$tbsRedirect!=null">${tbSolve$tbsRedirect}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="tbSolve.tbsRedirect" id="tbsRedirect"  value="${tbSolve.tbsRedirect}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入问题处理记录的流转到</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****流转到字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsRedirect">${tbSolve.tbsRedirect}</textarea>
		 		<span>
		 	
			 ${tbSolve.tbsRedirect$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsRedirect}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$tbsStatus和after$tbsStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.tbsStatus==true">
	${before$tbsStatus}
	<dl class="tbsStatus " major  ref="tbsStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.tbsStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.tbSolve$tbsStatus!=null">${tbSolve$tbsStatus}</s:if><s:else>
		 	<select class="narrow" id="tbsStatus" name="tbSolve.tbsStatus">
		 		<option value="">请选择</option>
				<option value="0" ${tbSolve.tbsStatus==0?"selected":""}>未受理</option>
				<option value="1" ${tbSolve.tbsStatus==1?"selected":""}>在处理</option>
				<option value="2" ${tbSolve.tbsStatus==2?"selected":""}>已流转</option>
				<option value="3" ${tbSolve.tbsStatus==3?"selected":""}>已结案</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择问题处理记录的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="tbsStatus">${tbSolve.tbsStatus}</textarea>
		 		<span>
		 	
			 ${tbSolve.tbsStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$tbsStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${tbSolve.tbsId}" name="tbSolve.tbsId" id="tbsId" />
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