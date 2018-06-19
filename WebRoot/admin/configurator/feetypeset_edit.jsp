<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/configurator/feetypeset.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/configurator/feetypeset.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csFeeTypeSet.csftsId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csftsId,csftsHost,csftsModel,csftsFeeType,csftsDefault,csftsOutlets,csftsStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csftsId,csftsHost,csftsModel,csftsFeeType,csftsDefault,csftsOutlets,csftsStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csftsId,csftsHost,csftsModel,csftsFeeType,csftsDefault,csftsOutlets,csftsStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csftsId,csftsHost,csftsModel,csftsFeeType,csftsDefault,csftsOutlets,csftsStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"计费策略编辑":CTRL.title}</title>
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
	$("#feetypesetForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csftsId==true">
		${lz:set("haveEditable",true)}
		,"csFeeTypeSet.csftsId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csftsHost==true">
		${lz:set("haveEditable",true)}
		,"csFeeTypeSet.csftsHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csftsModel==true">
		${lz:set("haveEditable",true)}
		,"csFeeTypeSet.csftsModel":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csftsFeeType==true">
		${lz:set("haveEditable",true)}
		,"csFeeTypeSet.csftsFeeType":function(el){
			if(!false && $("input[name='csFeeTypeSet.csftsFeeType']:checked").length==0)
				return "适用计费类型至少选择一个";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csftsDefault==true">
		${lz:set("haveEditable",true)}
		,"csFeeTypeSet.csftsDefault":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csftsOutlets==true">
		${lz:set("haveEditable",true)}
		,"csFeeTypeSet.csftsOutlets":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csftsStatus==true">
		${lz:set("haveEditable",true)}
		,"csFeeTypeSet.csftsStatus":function(el){
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
	<form class="form editform" ref="CsFeeTypeSet" id="feetypesetForm" name="feetypesetForm" action="${empty CTRL.action?"feetypeset_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写计费策略相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csFeeTypeSetToken}" name="csFeeTypeSetToken" id="csFeeTypeSetToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csFeeTypeSet.csftsId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csftsId和after$csftsId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csftsId==true">
	${before$csftsId}
	<dl class="csftsId ${CTRL.e.csftsId?"hidden":""}" major  ref="csftsId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csftsId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csFeeTypeSet$csftsId!=null">${csFeeTypeSet$csftsId}</s:if><s:else>
			<input type="hidden" value="${csFeeTypeSet.csftsId}" name="csFeeTypeSet.csftsId" id="csftsId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csftsId">${csFeeTypeSet.csftsId}</textarea>
		 		<span>
		 	
			 ${csFeeTypeSet.csftsId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csftsId}
	</s:if>
	
	${lz:set("注释","*****************运营城市字段的输入框代码*****************")}
	${lz:set("注释","before$csftsHost和after$csftsHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csftsHost==true">
	${before$csftsHost}
	<dl class="csftsHost " major  ref="csftsHost" >
		<dt>运营城市:</dt>
		<s:if test="#request.CTRL.e.csftsHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csFeeTypeSet$csftsHost!=null">${csFeeTypeSet$csftsHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csftsHost" name="csFeeTypeSet.csftsHost">
		 				<option selected value="${csFeeTypeSet.csftsHost}">
		 					${get:SrvHost(csFeeTypeSet.csftsHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csftsHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csftsHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****运营城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csftsHost">${csFeeTypeSet.csftsHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csFeeTypeSet.csftsHost}',{ctrl:{editable:false,visible:true}})">
			 ${csFeeTypeSet.csftsHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csftsHost}
	</s:if>
	
	${lz:set("注释","*****************适用车型字段的输入框代码*****************")}
	${lz:set("注释","before$csftsModel和after$csftsModel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csftsModel==true">
	${before$csftsModel}
	<dl class="csftsModel " major  ref="csftsModel" >
		<dt>适用车型:</dt>
		<s:if test="#request.CTRL.e.csftsModel==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csFeeTypeSet$csftsModel!=null">${csFeeTypeSet$csftsModel}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/object/carmodel_query.do?size=-1" id="csftsModel" name="csFeeTypeSet.csftsModel">
		 				<option selected value="${csFeeTypeSet.csftsModel}">
		 					${get:CsCarModel(csFeeTypeSet.csftsModel).cscmName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csftsModel').val())==''){return;};window.href('${basePath}${proname}/object/carmodel_details.do?key='+$('#csftsModel').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****适用车型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csftsModel">${csFeeTypeSet.csftsModel}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/carmodel_details.do?key=${csFeeTypeSet.csftsModel}',{ctrl:{editable:false,visible:true}})">
			 ${csFeeTypeSet.csftsModel$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csftsModel}
	</s:if>
	
	${lz:set("注释","*****************适用计费类型字段的输入框代码*****************")}
	${lz:set("注释","before$csftsFeeType和after$csftsFeeType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csftsFeeType==true">
	${before$csftsFeeType}
	<dl class="csftsFeeType " major  ref="csftsFeeType" style="width:98%;">
		<dt>适用计费类型:</dt>
		<s:if test="#request.CTRL.e.csftsFeeType==true">
		${lz:set("haveEditable",true)}
		<dd input="checkbox">
		<s:if test="#request.csFeeTypeSet$csftsFeeType!=null">${csFeeTypeSet$csftsFeeType}</s:if><s:else>
		 	<div class='wide input'>	
		 	&nbsp; 	
		 	<input type="hidden" value="#0#" name="csFeeTypeSet.csftsFeeType" id="csftsFeeType"/>
				    ${lz:set("items", lz:query("ccclubs_system","select * from cs_user_type where 1=1") )}
					<s:iterator value="#request.items" id="item" status="i">
			<label><input type="checkbox" ${lz:idin(csFeeTypeSet.csftsFeeType,item.csut_id)?"checked='checked'":""} value="#${item.csut_id}#" name="csFeeTypeSet.csftsFeeType" id="csftsFeeType"/>
					${item.csut_name}&nbsp;&nbsp;&nbsp;</label>
					</s:iterator>
					${lz:set("items",null)}
		 </div>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择计费策略的适用计费类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****适用计费类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csftsFeeType">${csFeeTypeSet.csftsFeeType}</textarea>
		 		<span>
		 	
			 ${csFeeTypeSet.csftsFeeType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csftsFeeType}
	</s:if>
	
	${lz:set("注释","*****************默认计费类型字段的输入框代码*****************")}
	${lz:set("注释","before$csftsDefault和after$csftsDefault变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csftsDefault==true">
	${before$csftsDefault}
	<dl class="csftsDefault " major  ref="csftsDefault" >
		<dt>默认计费类型:</dt>
		<s:if test="#request.CTRL.e.csftsDefault==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csFeeTypeSet$csftsDefault!=null">${csFeeTypeSet$csftsDefault}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/user/type_query.do?size=-1" id="csftsDefault" name="csFeeTypeSet.csftsDefault">
		 				<option selected value="${csFeeTypeSet.csftsDefault}">
		 					${get:CsUserType(csFeeTypeSet.csftsDefault).csutName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csftsDefault').val())==''){return;};window.href('${basePath}${proname}/user/type_details.do?key='+$('#csftsDefault').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****默认计费类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csftsDefault">${csFeeTypeSet.csftsDefault}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/type_details.do?key=${csFeeTypeSet.csftsDefault}',{ctrl:{editable:false,visible:true}})">
			 ${csFeeTypeSet.csftsDefault$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csftsDefault}
	</s:if>
	
	${lz:set("注释","*****************默认网点字段的输入框代码*****************")}
	${lz:set("注释","before$csftsOutlets和after$csftsOutlets变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csftsOutlets==true">
	${before$csftsOutlets}
	<dl class="csftsOutlets " major  ref="csftsOutlets" >
		<dt>默认网点:</dt>
		<s:if test="#request.CTRL.e.csftsOutlets==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csFeeTypeSet$csftsOutlets!=null">${csFeeTypeSet$csftsOutlets}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/object/outlets_tree.do?parent={param}&host={csftsHost}" id="csftsOutlets" name="csFeeTypeSet.csftsOutlets">
		 				<option selected value="${csFeeTypeSet.csftsOutlets}">
		 					${get:CsOutlets(csFeeTypeSet.csftsOutlets).csoName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csftsOutlets').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?key='+$('#csftsOutlets').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em>默认价格参考网点</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****默认网点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csftsOutlets">${csFeeTypeSet.csftsOutlets}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${csFeeTypeSet.csftsOutlets}',{ctrl:{editable:false,visible:true}})">
			 ${csFeeTypeSet.csftsOutlets$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csftsOutlets}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csftsStatus和after$csftsStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csftsStatus==true">
	${before$csftsStatus}
	<dl class="csftsStatus " major  ref="csftsStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csftsStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csFeeTypeSet$csftsStatus!=null">${csFeeTypeSet$csftsStatus}</s:if><s:else>
		 	<select class="narrow" id="csftsStatus" name="csFeeTypeSet.csftsStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csFeeTypeSet.csftsStatus==1?"selected":""}>正常</option>
				<option value="0" ${csFeeTypeSet.csftsStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择计费策略的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csftsStatus">${csFeeTypeSet.csftsStatus}</textarea>
		 		<span>
		 	
			 ${csFeeTypeSet.csftsStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csftsStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csFeeTypeSet.csftsId}" name="csFeeTypeSet.csftsId" id="csftsId" />
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