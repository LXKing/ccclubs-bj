<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/service/orderlog.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/service/orderlog.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csOrderLog.csolId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csolId,csolTitle,csolOrder,csolEditor,csolMember,csolFrom,csolSrc,csolRemark",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csolId,csolTitle,csolOrder,csolEditor,csolMember,csolFrom,csolSrc,csolRemark",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csolId,csolTitle,csolOrder,csolEditor,csolMember,csolFrom,csolSrc,csolRemark",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csolId,csolTitle,csolOrder,csolEditor,csolMember,csolFrom,csolSrc,csolRemark",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"订单操作记录编辑":CTRL.title}</title>
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
	$("#orderlogForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csolId==true">
		${lz:set("haveEditable",true)}
		,"csOrderLog.csolId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csolTitle==true">
		${lz:set("haveEditable",true)}
		,"csOrderLog.csolTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "操作项目不能为空";
			if(el.value.length>64 && el.value.indexOf("[*]")==-1)
				return "操作项目最大长度为64个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csolOrder==true">
		${lz:set("haveEditable",true)}
		,"csOrderLog.csolOrder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csolEditor==true">
		${lz:set("haveEditable",true)}
		,"csOrderLog.csolEditor":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csolMember==true">
		${lz:set("haveEditable",true)}
		,"csOrderLog.csolMember":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csolFrom==true">
		${lz:set("haveEditable",true)}
		,"csOrderLog.csolFrom":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csolSrc==true">
		${lz:set("haveEditable",true)}
		,"csOrderLog.csolSrc":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csolRemark==true">
		${lz:set("haveEditable",true)}
		,"csOrderLog.csolRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csolAddTime==true">
		${lz:set("haveEditable",true)}
		,"csOrderLog.csolAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
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
	<form class="form editform" ref="CsOrderLog" id="orderlogForm" name="orderlogForm" action="${empty CTRL.action?"orderlog_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写订单操作记录相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csOrderLogToken}" name="csOrderLogToken" id="csOrderLogToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csOrderLog.csolId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csolId和after$csolId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csolId==true">
	${before$csolId}
	<dl class="csolId ${CTRL.e.csolId?"hidden":""}" major  ref="csolId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csolId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csOrderLog$csolId!=null">${csOrderLog$csolId}</s:if><s:else>
			<input type="hidden" value="${csOrderLog.csolId}" name="csOrderLog.csolId" id="csolId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csolId">${csOrderLog.csolId}</textarea>
		 		<span>
		 	
			 ${csOrderLog.csolId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csolId}
	</s:if>
	
	${lz:set("注释","*****************操作项目字段的输入框代码*****************")}
	${lz:set("注释","before$csolTitle和after$csolTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csolTitle==true">
	${before$csolTitle}
	<dl class="csolTitle " major  ref="csolTitle" style="width:98%;">
		<dt>操作项目:</dt>
		<s:if test="#request.CTRL.e.csolTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOrderLog$csolTitle!=null">${csOrderLog$csolTitle}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="64" name="csOrderLog.csolTitle" id="csolTitle"  value="${csOrderLog.csolTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入订单操作记录的操作项目</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****操作项目字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csolTitle">${csOrderLog.csolTitle}</textarea>
		 		<span>
		 	
			 ${csOrderLog.csolTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csolTitle}
	</s:if>
	
	${lz:set("注释","*****************所属订单字段的输入框代码*****************")}
	${lz:set("注释","before$csolOrder和after$csolOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csolOrder==true">
	${before$csolOrder}
	<dl class="csolOrder " major  ref="csolOrder" >
		<dt>所属订单:</dt>
		<s:if test="#request.CTRL.e.csolOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOrderLog$csolOrder!=null">${csOrderLog$csolOrder}</s:if><s:else>
		 			<input title="请输入系统订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/order_query.do?value={param}" type="text" id="csolOrder" name="csOrderLog.csolOrder" text="${get:CsOrder(csOrderLog.csolOrder).csoId}" value="${csOrderLog.csolOrder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csolOrder').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?key='+$('#csolOrder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csolOrder">${csOrderLog.csolOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?key=${csOrderLog.csolOrder}',{ctrl:{editable:false,visible:true}})">
			 ${csOrderLog.csolOrder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csolOrder}
	</s:if>
	
	${lz:set("注释","*****************操作人字段的输入框代码*****************")}
	${lz:set("注释","before$csolEditor和after$csolEditor变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csolEditor==true">
	${before$csolEditor}
	<dl class="csolEditor " major  ref="csolEditor" >
		<dt>操&nbsp;&nbsp;作&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csolEditor==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOrderLog$csolEditor!=null">${csOrderLog$csolEditor}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csolEditor" name="csOrderLog.csolEditor">
		 				<option selected value="${csOrderLog.csolEditor}">
		 					${get:SrvUser(csOrderLog.csolEditor).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csolEditor').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csolEditor').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****操作人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csolEditor">${csOrderLog.csolEditor}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csOrderLog.csolEditor}',{ctrl:{editable:false,visible:true}})">
			 ${csOrderLog.csolEditor$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csolEditor}
	</s:if>
	
	${lz:set("注释","*****************操作会员字段的输入框代码*****************")}
	${lz:set("注释","before$csolMember和after$csolMember变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csolMember==true">
	${before$csolMember}
	<dl class="csolMember " major  ref="csolMember" >
		<dt>操作会员:</dt>
		<s:if test="#request.CTRL.e.csolMember==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOrderLog$csolMember!=null">${csOrderLog$csolMember}</s:if><s:else>
		 			<input title="请输入会员帐号真实姓名进行查询" class="combox narrow" action="${basePath}${proname}/user/member_query.do?value={param}" type="text" id="csolMember" name="csOrderLog.csolMember" text="${get:CsMember(csOrderLog.csolMember).csmName}" value="${csOrderLog.csolMember}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csolMember').val())==''){return;};window.href('${basePath}${proname}/user/member_details.do?key='+$('#csolMember').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****操作会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csolMember">${csOrderLog.csolMember}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csOrderLog.csolMember}',{ctrl:{editable:false,visible:true}})">
			 ${csOrderLog.csolMember$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csolMember}
	</s:if>
	
	${lz:set("注释","*****************操作平台字段的输入框代码*****************")}
	${lz:set("注释","before$csolFrom和after$csolFrom变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csolFrom==true">
	${before$csolFrom}
	<dl class="csolFrom " major  ref="csolFrom" >
		<dt>操作平台:</dt>
		<s:if test="#request.CTRL.e.csolFrom==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOrderLog$csolFrom!=null">${csOrderLog$csolFrom}</s:if><s:else>
		 	<select class="narrow" id="csolFrom" name="csOrderLog.csolFrom">
		 		<option value="">请选择</option>
				<option value="0" ${csOrderLog.csolFrom==0?"selected":""}>网站</option>
				<option value="1" ${csOrderLog.csolFrom==1?"selected":""}>后台</option>
				<option value="2" ${csOrderLog.csolFrom==2?"selected":""}>微信</option>
				<option value="3" ${csOrderLog.csolFrom==3?"selected":""}>APP</option>
				<option value="4" ${csOrderLog.csolFrom==4?"selected":""}>企业</option>
				<option value="5" ${csOrderLog.csolFrom==5?"selected":""}>支付宝</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择订单操作记录的操作平台</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****操作平台字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csolFrom">${csOrderLog.csolFrom}</textarea>
		 		<span>
		 	
			 ${csOrderLog.csolFrom$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csolFrom}
	</s:if>
	
	${lz:set("注释","*****************操作源信息字段的输入框代码*****************")}
	${lz:set("注释","before$csolSrc和after$csolSrc变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csolSrc==true">
	${before$csolSrc}
	<dl class="csolSrc " major  ref="csolSrc" >
		<dt>操作源信息:</dt>
		<s:if test="#request.CTRL.e.csolSrc==true">
		${lz:set("haveEditable",true)}
		<dd input="generic">
		<s:if test="#request.csOrderLog$csolSrc!=null">${csOrderLog$csolSrc}</s:if><s:else>
		 	<input title="请输入动态查询" class="combox narrow" generic="csolSrcGeneric" size="16" type="text" id="csolSrc" name="csOrderLog.csolSrc" text="${csOrderLog.csolSrc$}" value="${csOrderLog.csolSrc}"/>
		 	${lz:set("models",get:models(""))}
		 	<select id="csolSrcGeneric" style="display:none;">
		 		<option value="">--选择模块--</option>
		 		<s:iterator value="#request.models" id="item" status="i">
		 		<option value="${item.name}:${basePath}admin/${item.namespace}_query.do?value={param}">${item.caption}</option>
		 		</s:iterator>
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="lookup('#${NAME}')"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****操作源信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csolSrc">${csOrderLog.csolSrc}</textarea>
		 		<span>
		 	
			 ${csOrderLog.csolSrc$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csolSrc}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$csolRemark和after$csolRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csolRemark==true">
	${before$csolRemark}
	<dl class="csolRemark " major  ref="csolRemark" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.csolRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csOrderLog$csolRemark!=null">${csOrderLog$csolRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csolRemark" name="csOrderLog.csolRemark" rows="5">${csOrderLog.csolRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入订单操作记录的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csOrderLog.csolRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csolRemark}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csolAddTime和after$csolAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csolAddTime==true">
	${before$csolAddTime}
	<dl class="csolAddTime " major  ref="csolAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csolAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOrderLog$csolAddTime!=null">${csOrderLog$csolAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOrderLog.csolAddTime" id="csolAddTime"  value="<s:date name="csOrderLog.csolAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择订单操作记录的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csolAddTime">${csOrderLog.csolAddTime}</textarea>
		 		<span>
		 	
			 ${csOrderLog.csolAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csolAddTime}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csOrderLog.csolId}" name="csOrderLog.csolId" id="csolId" />
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