<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/permissions/host.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/permissions/host.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty srvHost.shId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"shId,shName,shOper,shFlag,shFeeType,shOnHour,shOffHour,shLocation,shState,shStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"shId,shName,shOper,shFlag,shFeeType,shOnHour,shOffHour,shLocation,shState,shStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"shId,shName,shOper,shFlag,shFeeType,shOnHour,shOffHour,shLocation,shState,shStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"shId,shName,shOper,shFlag,shFeeType,shOnHour,shOffHour,shLocation,shState,shStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"运营城市编辑":CTRL.title}</title>
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
<%@include file="/admin/map.point.jsp"%>
<script>
$(function(){
	$("#shLocation").focus(function(){
		$(this).blur();		
		var array = $("#shLocation").val().split(",");
		$.getMapPoint(function(Latitude,Longitude){
			$("#shLocation").val(Latitude+","+Longitude);
		},{
			Latitude:array[0],
			Longitude:array[1]
		});
	});
});
</script>
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
	$("#hostForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.shId==true">
		${lz:set("haveEditable",true)}
		,"srvHost.shId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.shName==true">
		${lz:set("haveEditable",true)}
		,"srvHost.shName":function(el){
			if(jQuery.trim(el.value)=="")
				return "城市名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "城市名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.shOper==true">
		${lz:set("haveEditable",true)}
		,"srvHost.shOper":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.shFlag==true">
		${lz:set("haveEditable",true)}
		,"srvHost.shFlag":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "标识最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.shFeeType==true">
		${lz:set("haveEditable",true)}
		,"srvHost.shFeeType":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.shOnHour==true">
		${lz:set("haveEditable",true)}
		,"srvHost.shOnHour":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "上班时间输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.shOffHour==true">
		${lz:set("haveEditable",true)}
		,"srvHost.shOffHour":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "下班时间输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.shLocation==true">
		${lz:set("haveEditable",true)}
		,"srvHost.shLocation":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "坐标位置最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.shState==true">
		${lz:set("haveEditable",true)}
		,"srvHost.shState":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择运营状态";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.shStatus==true">
		${lz:set("haveEditable",true)}
		,"srvHost.shStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择有效状态";
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
	<form class="form editform" ref="SrvHost" id="hostForm" name="hostForm" action="${empty CTRL.action?"host_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写运营城市相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${srvHostToken}" name="srvHostToken" id="srvHostToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty srvHost.shId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$shId和after$shId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.shId==true">
	${before$shId}
	<dl class="shId ${CTRL.e.shId?"hidden":""}" major  ref="shId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.shId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.srvHost$shId!=null">${srvHost$shId}</s:if><s:else>
			<input type="hidden" value="${(srvHost.shId)}" name="srvHost.shId" id="shId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="shId">${srvHost.shId}</textarea>
		 		<span>
		 	
			 ${lz:html(srvHost.shId$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$shId}
	</s:if>
	
	${lz:set("注释","*****************城市名称字段的输入框代码*****************")}
	${lz:set("注释","before$shName和after$shName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.shName==true">
	${before$shName}
	<dl class="shName " major  ref="shName" >
		<dt>城市名称:</dt>
		<s:if test="#request.CTRL.e.shName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvHost$shName!=null">${srvHost$shName}</s:if><s:else>
		 	<input type="text" class="input narrow"   name="srvHost.shName" id="shName"  value="${(srvHost.shName)}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入运营城市的城市名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="shName">${srvHost.shName}</textarea>
		 		<span>
		 	
			 ${lz:html(srvHost.shName$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$shName}
	</s:if>
	
	${lz:set("注释","*****************运营商字段的输入框代码*****************")}
	${lz:set("注释","before$shOper和after$shOper变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.shOper==true">
	${before$shOper}
	<dl class="shOper " major  ref="shOper" >
		<dt>运&nbsp;&nbsp;营&nbsp;&nbsp;商:</dt>
		<s:if test="#request.CTRL.e.shOper==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.srvHost$shOper!=null">${srvHost$shOper}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/configurator/oper_query.do?size=-1" id="shOper" name="srvHost.shOper">
		 				<option selected value="${(srvHost.shOper)}">
		 					${get:CsOper(srvHost.shOper).csoName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#shOper').val())==''){return;};window.href('${basePath}${proname}/configurator/oper_details.do?key='+$('#shOper').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****运营商字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="shOper">${srvHost.shOper}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/oper_details.do?key=${(srvHost.shOper)}',{ctrl:{editable:false,visible:true}})">
			 ${lz:html(srvHost.shOper$)}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$shOper}
	</s:if>
	
	${lz:set("注释","*****************标识字段的输入框代码*****************")}
	${lz:set("注释","before$shFlag和after$shFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.shFlag==true">
	${before$shFlag}
	<dl class="shFlag " major  ref="shFlag" >
		<dt>标　　识:</dt>
		<s:if test="#request.CTRL.e.shFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvHost$shFlag!=null">${srvHost$shFlag}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="srvHost.shFlag" id="shFlag"  value="${(srvHost.shFlag)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入运营城市的标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="shFlag">${srvHost.shFlag}</textarea>
		 		<span>
		 	
			 ${lz:html(srvHost.shFlag$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$shFlag}
	</s:if>
	
	${lz:set("注释","*****************默认计费方式字段的输入框代码*****************")}
	${lz:set("注释","before$shFeeType和after$shFeeType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.shFeeType==true">
	${before$shFeeType}
	<dl class="shFeeType " major  ref="shFeeType" >
		<dt>默认计费方式:</dt>
		<s:if test="#request.CTRL.e.shFeeType==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.srvHost$shFeeType!=null">${srvHost$shFeeType}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/user/type_query.do?size=-1" id="shFeeType" name="srvHost.shFeeType">
		 				<option selected value="${(srvHost.shFeeType)}">
		 					${get:CsUserType(srvHost.shFeeType).csutName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#shFeeType').val())==''){return;};window.href('${basePath}${proname}/user/type_details.do?key='+$('#shFeeType').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****默认计费方式字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="shFeeType">${srvHost.shFeeType}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/type_details.do?key=${(srvHost.shFeeType)}',{ctrl:{editable:false,visible:true}})">
			 ${lz:html(srvHost.shFeeType$)}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$shFeeType}
	</s:if>
	
	${lz:set("注释","*****************上班时间字段的输入框代码*****************")}
	${lz:set("注释","before$shOnHour和after$shOnHour变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.shOnHour==true">
	${before$shOnHour}
	<dl class="shOnHour " major  ref="shOnHour" >
		<dt>上班时间:</dt>
		<s:if test="#request.CTRL.e.shOnHour==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.srvHost$shOnHour!=null">${srvHost$shOnHour}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="srvHost.shOnHour" id="shOnHour"  value="${(srvHost.shOnHour)}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入运营城市的上班时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****上班时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="shOnHour">${srvHost.shOnHour}</textarea>
		 		<span>
		 	
			 ${lz:html(srvHost.shOnHour$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$shOnHour}
	</s:if>
	
	${lz:set("注释","*****************下班时间字段的输入框代码*****************")}
	${lz:set("注释","before$shOffHour和after$shOffHour变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.shOffHour==true">
	${before$shOffHour}
	<dl class="shOffHour " major  ref="shOffHour" >
		<dt>下班时间:</dt>
		<s:if test="#request.CTRL.e.shOffHour==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.srvHost$shOffHour!=null">${srvHost$shOffHour}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="srvHost.shOffHour" id="shOffHour"  value="${(srvHost.shOffHour)}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入运营城市的下班时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****下班时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="shOffHour">${srvHost.shOffHour}</textarea>
		 		<span>
		 	
			 ${lz:html(srvHost.shOffHour$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$shOffHour}
	</s:if>
	
	${lz:set("注释","*****************坐标位置字段的输入框代码*****************")}
	${lz:set("注释","before$shLocation和after$shLocation变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.shLocation==true">
	${before$shLocation}
	<dl class="shLocation " minor  ref="shLocation" style="width:98%;">
		<dt>坐标位置:</dt>
		<s:if test="#request.CTRL.e.shLocation==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvHost$shLocation!=null">${srvHost$shLocation}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="srvHost.shLocation" id="shLocation"  value="${(srvHost.shLocation)}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入运营城市的坐标位置</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****坐标位置字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="shLocation">${srvHost.shLocation}</textarea>
		 		<span>
		 	
			 ${lz:html(srvHost.shLocation$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$shLocation}
	</s:if>
	
	${lz:set("注释","*****************运营状态字段的输入框代码*****************")}
	${lz:set("注释","before$shState和after$shState变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.shState==true">
	${before$shState}
	<dl class="shState " major  ref="shState" >
		<dt>运营状态:</dt>
		<s:if test="#request.CTRL.e.shState==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.srvHost$shState!=null">${srvHost$shState}</s:if><s:else>
		 	<select class="narrow" id="shState" name="srvHost.shState">
		 		<option value="">请选择</option>
				<option value="0" ${srvHost.shState==0?"selected":""}>下线</option>
				<option value="1" ${srvHost.shState==1?"selected":""}>上线</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择运营城市的运营状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****运营状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="shState">${srvHost.shState}</textarea>
		 		<span>
		 	
			 ${lz:html(srvHost.shState$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$shState}
	</s:if>
	
	${lz:set("注释","*****************有效状态字段的输入框代码*****************")}
	${lz:set("注释","before$shStatus和after$shStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.shStatus==true">
	${before$shStatus}
	<dl class="shStatus " major  ref="shStatus" >
		<dt>有效状态:</dt>
		<s:if test="#request.CTRL.e.shStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.srvHost$shStatus!=null">${srvHost$shStatus}</s:if><s:else>
		 	<select class="narrow" id="shStatus" name="srvHost.shStatus">
		 		<option value="">请选择</option>
				<option value="1" ${srvHost.shStatus==1?"selected":""}>正常</option>
				<option value="0" ${srvHost.shStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择运营城市的有效状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****有效状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="shStatus">${srvHost.shStatus}</textarea>
		 		<span>
		 	
			 ${lz:html(srvHost.shStatus$)}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$shStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${srvHost.shId}" name="srvHost.shId" id="shId" />
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