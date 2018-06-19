<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/operate/marketplan.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/operate/marketplan.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csMarketPlan.csmpId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csmpId,csmpHost,csmpName,csmpType,csmpMethod,csmpProfile,csmpPlanBook,csmpMindMap,csmpVIndex,indexes,logs,csmpStart,csmpFinish,csmpRemark,csmpStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csmpId,csmpHost,csmpName,csmpType,csmpMethod,csmpProfile,csmpPlanBook,csmpMindMap,csmpVIndex,indexes,logs,csmpStart,csmpFinish,csmpRemark,csmpStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csmpId,csmpHost,csmpName,csmpType,csmpMethod,csmpProfile,csmpPlanBook,csmpMindMap,csmpVIndex,indexes,logs,csmpStart,csmpFinish,csmpRemark,csmpStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csmpId,csmpHost,csmpName,csmpType,csmpMethod,csmpProfile,csmpPlanBook,csmpMindMap,csmpVIndex,indexes,logs,csmpStart,csmpFinish,csmpRemark,csmpStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"营销方案编辑":CTRL.title}</title>
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
	$("#marketplanForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csmpId==true">
		${lz:set("haveEditable",true)}
		,"csMarketPlan.csmpId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmpHost==true">
		${lz:set("haveEditable",true)}
		,"csMarketPlan.csmpHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmpName==true">
		${lz:set("haveEditable",true)}
		,"csMarketPlan.csmpName":function(el){
			if(jQuery.trim(el.value)=="")
				return "方案主题不能为空";
			if(el.value.length>64 && el.value.indexOf("[*]")==-1)
				return "方案主题最大长度为64个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmpType==true">
		${lz:set("haveEditable",true)}
		,"csMarketPlan.csmpType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择营销类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmpMethod==true">
		${lz:set("haveEditable",true)}
		,"csMarketPlan.csmpMethod":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择营销手段";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmpPerson==true">
		${lz:set("haveEditable",true)}
		,"csMarketPlan.csmpPerson":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmpProfile==true">
		${lz:set("haveEditable",true)}
		,"csMarketPlan.csmpProfile":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "方案简介最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmpPlanBook==true">
		${lz:set("haveEditable",true)}
		,"csMarketPlan.csmpPlanBook":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmpMindMap==true">
		${lz:set("haveEditable",true)}
		,"csMarketPlan.csmpMindMap":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "思维导图最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmpVIndex==true">
		${lz:set("haveEditable",true)}
		,"csMarketPlan.csmpVIndex":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmpStart==true">
		${lz:set("haveEditable",true)}
		,"csMarketPlan.csmpStart":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmpFinish==true">
		${lz:set("haveEditable",true)}
		,"csMarketPlan.csmpFinish":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmpUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csMarketPlan.csmpUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmpAddTime==true">
		${lz:set("haveEditable",true)}
		,"csMarketPlan.csmpAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmpRemark==true">
		${lz:set("haveEditable",true)}
		,"csMarketPlan.csmpRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmpStatus==true">
		${lz:set("haveEditable",true)}
		,"csMarketPlan.csmpStatus":function(el){
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
	<form class="form editform" ref="CsMarketPlan" id="marketplanForm" name="marketplanForm" action="${empty CTRL.action?"marketplan_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写营销方案相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csMarketPlanToken}" name="csMarketPlanToken" id="csMarketPlanToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csMarketPlan.csmpId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csmpId和after$csmpId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmpId==true">
	${before$csmpId}
	<dl class="csmpId ${CTRL.e.csmpId?"hidden":""}" major  ref="csmpId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csmpId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csMarketPlan$csmpId!=null">${csMarketPlan$csmpId}</s:if><s:else>
			<input type="hidden" value="${csMarketPlan.csmpId}" name="csMarketPlan.csmpId" id="csmpId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmpId">${csMarketPlan.csmpId}</textarea>
		 		<span>
		 	
			 ${csMarketPlan.csmpId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmpId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csmpHost和after$csmpHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmpHost==true">
	${before$csmpHost}
	<dl class="csmpHost " major  ref="csmpHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csmpHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csMarketPlan$csmpHost!=null">${csMarketPlan$csmpHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csmpHost" name="csMarketPlan.csmpHost">
		 				<option selected value="${csMarketPlan.csmpHost}">
		 					${get:SrvHost(csMarketPlan.csmpHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmpHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csmpHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmpHost">${csMarketPlan.csmpHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csMarketPlan.csmpHost}',{ctrl:{editable:false,visible:true}})">
			 ${csMarketPlan.csmpHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmpHost}
	</s:if>
	
	${lz:set("注释","*****************方案主题字段的输入框代码*****************")}
	${lz:set("注释","before$csmpName和after$csmpName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmpName==true">
	${before$csmpName}
	<dl class="csmpName " major  ref="csmpName" style="width:98%;">
		<dt>方案主题:</dt>
		<s:if test="#request.CTRL.e.csmpName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMarketPlan$csmpName!=null">${csMarketPlan$csmpName}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="64" name="csMarketPlan.csmpName" id="csmpName"  value="${csMarketPlan.csmpName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入营销方案的方案主题</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****方案主题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csmpName">${csMarketPlan.csmpName}</textarea>
		 		<span>
		 	
			 ${csMarketPlan.csmpName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmpName}
	</s:if>
	
	${lz:set("注释","*****************营销类型字段的输入框代码*****************")}
	${lz:set("注释","before$csmpType和after$csmpType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmpType==true">
	${before$csmpType}
	<dl class="csmpType " major  ref="csmpType" >
		<dt>营销类型:</dt>
		<s:if test="#request.CTRL.e.csmpType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMarketPlan$csmpType!=null">${csMarketPlan$csmpType}</s:if><s:else>
		 	<select class="narrow" id="csmpType" name="csMarketPlan.csmpType">
		 		<option value="">请选择</option>
				<option value="0" ${csMarketPlan.csmpType==0?"selected":""}>会员营销</option>
				<option value="1" ${csMarketPlan.csmpType==1?"selected":""}>产品营销</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择营销方案的营销类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****营销类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmpType">${csMarketPlan.csmpType}</textarea>
		 		<span>
		 	
			 ${csMarketPlan.csmpType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmpType}
	</s:if>
	
	${lz:set("注释","*****************营销手段字段的输入框代码*****************")}
	${lz:set("注释","before$csmpMethod和after$csmpMethod变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmpMethod==true">
	${before$csmpMethod}
	<dl class="csmpMethod " major  ref="csmpMethod" >
		<dt>营销手段:</dt>
		<s:if test="#request.CTRL.e.csmpMethod==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMarketPlan$csmpMethod!=null">${csMarketPlan$csmpMethod}</s:if><s:else>
		 	<select class="narrow" id="csmpMethod" name="csMarketPlan.csmpMethod">
		 		<option value="">请选择</option>
 					${lz:set("items", get:propertys("营销手段","market_method"))}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.spId}" ${csMarketPlan.csmpMethod==item.spId?"selected":""}>${item.spName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmpMethod').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?key='+$('#csmpMethod').val(),{ctrl:{editable:false}})"></a>
	 			<a class="reladd" config="{
	 				action:'${basePath}${proname}/configurator/property_edit.do?rd=1&parent=${get:property("market_method").spId}'
	 				,callback:function(object){
	 					$('<option selected value='+object.spId+'>'+object.spName+'</option>').appendTo('#csmpMethod');
	 				}
	 			}" href="javascript:void(0);"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>比如：体验营销、病毒营销、上门营销等等</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****营销手段字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmpMethod">${csMarketPlan.csmpMethod}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${csMarketPlan.csmpMethod}',{ctrl:{editable:false,visible:true}})">
			 ${csMarketPlan.csmpMethod$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmpMethod}
	</s:if>
	
	${lz:set("注释","*****************策划人字段的输入框代码*****************")}
	${lz:set("注释","before$csmpPerson和after$csmpPerson变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmpPerson==true">
	${before$csmpPerson}
	<dl class="csmpPerson " major  ref="csmpPerson" >
		<dt>策&nbsp;&nbsp;划&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csmpPerson==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csMarketPlan$csmpPerson!=null">${csMarketPlan$csmpPerson}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csmpPerson" name="csMarketPlan.csmpPerson">
		 				<option selected value="${csMarketPlan.csmpPerson}">
		 					${get:SrvUser(csMarketPlan.csmpPerson).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmpPerson').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csmpPerson').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****策划人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmpPerson">${csMarketPlan.csmpPerson}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csMarketPlan.csmpPerson}',{ctrl:{editable:false,visible:true}})">
			 ${csMarketPlan.csmpPerson$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmpPerson}
	</s:if>
	
	${lz:set("注释","*****************方案简介字段的输入框代码*****************")}
	${lz:set("注释","before$csmpProfile和after$csmpProfile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmpProfile==true">
	${before$csmpProfile}
	<dl class="csmpProfile " minor  ref="csmpProfile" style="width:98%;">
		<dt>方案简介:</dt>
		<s:if test="#request.CTRL.e.csmpProfile==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csMarketPlan$csmpProfile!=null">${csMarketPlan$csmpProfile}</s:if><s:else>
		 	<textarea class="input wide"  id="csmpProfile" name="csMarketPlan.csmpProfile" rows="5">${csMarketPlan.csmpProfile}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入营销方案的方案简介</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****方案简介字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csMarketPlan.csmpProfile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmpProfile}
	</s:if>
	
	${lz:set("注释","*****************策划书字段的输入框代码*****************")}
	${lz:set("注释","before$csmpPlanBook和after$csmpPlanBook变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmpPlanBook==true">
	${before$csmpPlanBook}
	<dl class="csmpPlanBook " minor  ref="csmpPlanBook" style="width:98%;">
		<dt>策&nbsp;&nbsp;划&nbsp;&nbsp;书:</dt>
		<s:if test="#request.CTRL.e.csmpPlanBook==true">
		${lz:set("haveEditable",true)}
		<dd input="file">
		<s:if test="#request.csMarketPlan$csmpPlanBook!=null">${csMarketPlan$csmpPlanBook}</s:if><s:else>
		 	<input type="text" class="input" maxlength="128" size="32" name="csMarketPlan.csmpPlanBook" id="csmpPlanBook"  value="${csMarketPlan.csmpPlanBook}"/>
			<button type="button" onclick="$.upload({type:'file',callback:function(url){if(url)$('#csmpPlanBook').val(url)}})" class="button">上传文件</button>
			<button type="button" onclick="$('#csmpPlanBook').val('')" class="button">删除文件</button>
			<button type="button" onclick="window.location='${ lz:config("lazy3q.download")==null ? basePath : ""}${ lz:config("lazy3q.download")==null ? "download" :  lz:config("lazy3q.download")}?path='+$('#csmpPlanBook').val()" class="button">下载文件</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传营销方案的策划书</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****策划书字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csmpPlanBook">${csMarketPlan.csmpPlanBook}</textarea>
		 		<span>
		 	
			 ${csMarketPlan.csmpPlanBook$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmpPlanBook}
	</s:if>
	
	${lz:set("注释","*****************思维导图字段的输入框代码*****************")}
	${lz:set("注释","before$csmpMindMap和after$csmpMindMap变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmpMindMap==true">
	${before$csmpMindMap}
	<dl class="csmpMindMap " minor  ref="csmpMindMap" style="width:98%;">
		<dt>思维导图:</dt>
		<s:if test="#request.CTRL.e.csmpMindMap==true">
		${lz:set("haveEditable",true)}
		<dd input="mind">
		<s:if test="#request.csMarketPlan$csmpMindMap!=null">${csMarketPlan$csmpMindMap}</s:if><s:else>
		    <textarea class="input wide mind" mind="" id="csmpMindMap" name="csMarketPlan.csmpMindMap">${csMarketPlan.csmpMindMap}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****思维导图字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csmpMindMap">${csMarketPlan.csmpMindMap}</textarea>
		 		<span>
		 	<textarea class="hidden mind" mind="" id="csmpMindMap">${csMarketPlan.csmpMindMap}</textarea>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmpMindMap}
	</s:if>
	
	${lz:set("注释","*****************启用V指标字段的输入框代码*****************")}
	${lz:set("注释","before$csmpVIndex和after$csmpVIndex变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmpVIndex==true">
	${before$csmpVIndex}
	<dl class="csmpVIndex " major  ref="csmpVIndex" >
		<dt>启用V指标:</dt>
		<s:if test="#request.CTRL.e.csmpVIndex==true">
		${lz:set("haveEditable",true)}
		<dd input="radio">
		<s:if test="#request.csMarketPlan$csmpVIndex!=null">${csMarketPlan$csmpVIndex}</s:if><s:else>
		 	<div class='narrow input'>
		 	&nbsp;
	 	    <label><input type="radio" value="true" name="csMarketPlan.csmpVIndex" id="csmpVIndex" ${csMarketPlan.csmpVIndex==true?"checked":""}/>是&nbsp;&nbsp;&nbsp;</label>
			<label><input type="radio" value="false" name="csMarketPlan.csmpVIndex" id="csmpVIndex" ${csMarketPlan==null || csMarketPlan.csmpVIndex==null || csMarketPlan.csmpVIndex==false?"checked":""}/>否</label>
		 	</div>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>是否开启UV,PV,IP指标</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****启用V指标字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmpVIndex">${csMarketPlan.csmpVIndex}</textarea>
		 		<span>
		 	
			 ${csMarketPlan.csmpVIndex$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmpVIndex}
	</s:if>
	
	${lz:set("注释","*****************指标字段的输入框代码*****************")}
	${lz:set("注释","before$indexes和after$indexes变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.indexes==true">
	${before$indexes}
	<dl class="indexes " minor  ref="indexes" style="width:98%;">
		<dt>指　　标:</dt>
		<s:if test="#request.CTRL.e.indexes==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csMarketPlan$indexes!=null">${csMarketPlan$indexes}</s:if><s:else>
		  		<s:if test="csMarketPlan.csmpId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="indexes" name="indexes" frameborder='0' src="" url="${basePath}${proname}/operate/marketindex.do?inFrame=true&canQuery=false&all=true&csmiPlan=${csMarketPlan.csmpId}&ctrl={queryable:false,fields:{csmiPlan:{visible:true,editable:false,defaultValue:${csMarketPlan.csmpId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#indexes').attr('src',$('#indexes').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑指标
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****指标字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csMarketPlan.csmpId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="indexes" name="indexes" frameborder='0' src="" url="${basePath}${proname}/operate/marketindex.do?inFrame=true&canQuery=false&all=true&csmiPlan=${csMarketPlan.csmpId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#indexes').attr('src',$('#indexes').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$indexes}
	</s:if>
	
	${lz:set("注释","*****************执行维护字段的输入框代码*****************")}
	${lz:set("注释","before$logs和after$logs变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.logs==true">
	${before$logs}
	<dl class="logs " minor  ref="logs" style="width:98%;">
		<dt>执行维护:</dt>
		<s:if test="#request.CTRL.e.logs==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csMarketPlan$logs!=null">${csMarketPlan$logs}</s:if><s:else>
		  		<s:if test="csMarketPlan.csmpId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="logs" name="logs" frameborder='0' src="" url="${basePath}${proname}/operate/marketlog.do?inFrame=true&canQuery=false&all=true&csmlPlan=${csMarketPlan.csmpId}&ctrl={queryable:false,fields:{csmlPlan:{visible:true,editable:false,defaultValue:${csMarketPlan.csmpId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#logs').attr('src',$('#logs').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑执行维护
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****执行维护字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csMarketPlan.csmpId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="logs" name="logs" frameborder='0' src="" url="${basePath}${proname}/operate/marketlog.do?inFrame=true&canQuery=false&all=true&csmlPlan=${csMarketPlan.csmpId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#logs').attr('src',$('#logs').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$logs}
	</s:if>
	
	${lz:set("注释","*****************开始时间字段的输入框代码*****************")}
	${lz:set("注释","before$csmpStart和after$csmpStart变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmpStart==true">
	${before$csmpStart}
	<dl class="csmpStart " major  ref="csmpStart" >
		<dt>开始时间:</dt>
		<s:if test="#request.CTRL.e.csmpStart==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csMarketPlan$csmpStart!=null">${csMarketPlan$csmpStart}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csMarketPlan.csmpStart" id="csmpStart"  value="<s:date name="csMarketPlan.csmpStart" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择营销方案的开始时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****开始时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmpStart">${csMarketPlan.csmpStart}</textarea>
		 		<span>
		 	
			 ${csMarketPlan.csmpStart$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmpStart}
	</s:if>
	
	${lz:set("注释","*****************结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$csmpFinish和after$csmpFinish变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmpFinish==true">
	${before$csmpFinish}
	<dl class="csmpFinish " major  ref="csmpFinish" >
		<dt>结束时间:</dt>
		<s:if test="#request.CTRL.e.csmpFinish==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csMarketPlan$csmpFinish!=null">${csMarketPlan$csmpFinish}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csMarketPlan.csmpFinish" id="csmpFinish"  value="<s:date name="csMarketPlan.csmpFinish" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择营销方案的结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmpFinish">${csMarketPlan.csmpFinish}</textarea>
		 		<span>
		 	
			 ${csMarketPlan.csmpFinish$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmpFinish}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csmpUpdateTime和after$csmpUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmpUpdateTime==true">
	${before$csmpUpdateTime}
	<dl class="csmpUpdateTime " major  ref="csmpUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csmpUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csMarketPlan$csmpUpdateTime!=null">${csMarketPlan$csmpUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csMarketPlan.csmpUpdateTime" id="csmpUpdateTime"  value="<s:date name="csMarketPlan.csmpUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择营销方案的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmpUpdateTime">${csMarketPlan.csmpUpdateTime}</textarea>
		 		<span>
		 	
			 ${csMarketPlan.csmpUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmpUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csmpAddTime和after$csmpAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmpAddTime==true">
	${before$csmpAddTime}
	<dl class="csmpAddTime " major  ref="csmpAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csmpAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csMarketPlan$csmpAddTime!=null">${csMarketPlan$csmpAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csMarketPlan.csmpAddTime" id="csmpAddTime"  value="<s:date name="csMarketPlan.csmpAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择营销方案的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmpAddTime">${csMarketPlan.csmpAddTime}</textarea>
		 		<span>
		 	
			 ${csMarketPlan.csmpAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmpAddTime}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$csmpRemark和after$csmpRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmpRemark==true">
	${before$csmpRemark}
	<dl class="csmpRemark " major  ref="csmpRemark" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.csmpRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csMarketPlan$csmpRemark!=null">${csMarketPlan$csmpRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csmpRemark" name="csMarketPlan.csmpRemark" rows="5">${csMarketPlan.csmpRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入营销方案的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csMarketPlan.csmpRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmpRemark}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csmpStatus和after$csmpStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmpStatus==true">
	${before$csmpStatus}
	<dl class="csmpStatus " major  ref="csmpStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csmpStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMarketPlan$csmpStatus!=null">${csMarketPlan$csmpStatus}</s:if><s:else>
		 	<select class="narrow" id="csmpStatus" name="csMarketPlan.csmpStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csMarketPlan.csmpStatus==0?"selected":""}>策划中</option>
				<option value="1" ${csMarketPlan.csmpStatus==1?"selected":""}>执行中</option>
				<option value="2" ${csMarketPlan.csmpStatus==2?"selected":""}>执行完成</option>
				<option value="3" ${csMarketPlan.csmpStatus==3?"selected":""}>已取消</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择营销方案的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmpStatus">${csMarketPlan.csmpStatus}</textarea>
		 		<span>
		 	
			 ${csMarketPlan.csmpStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmpStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csMarketPlan.csmpId}" name="csMarketPlan.csmpId" id="csmpId" />
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