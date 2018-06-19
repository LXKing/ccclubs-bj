<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/control/elechistory.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/control/elechistory.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csElecHistory.csehId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csehId,csehDid,csehPowerPile,csehStatusCode,csehStatusText,csehEleAmount,csehTimeLong,csehErrorCode,csehData",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csehId,csehDid,csehPowerPile,csehStatusCode,csehStatusText,csehEleAmount,csehTimeLong,csehErrorCode,csehData",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csehId,csehDid,csehPowerPile,csehStatusCode,csehStatusText,csehEleAmount,csehTimeLong,csehErrorCode,csehData",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csehId,csehDid,csehPowerPile,csehStatusCode,csehStatusText,csehEleAmount,csehTimeLong,csehErrorCode,csehCollectTime,csehAddTime,csehData",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"电桩历史数据编辑":CTRL.title}</title>
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
	$("#elechistoryForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csehId==true">
		${lz:set("haveEditable",true)}
		,"csElecHistory.csehId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csehDid==true">
		${lz:set("haveEditable",true)}
		,"csElecHistory.csehDid":function(el){
			if(jQuery.trim(el.value)=="")
				return "设备ID不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "设备ID最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csehPowerPile==true">
		${lz:set("haveEditable",true)}
		,"csElecHistory.csehPowerPile":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csehStatusCode==true">
		${lz:set("haveEditable",true)}
		,"csElecHistory.csehStatusCode":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "状态编码最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csehStatusText==true">
		${lz:set("haveEditable",true)}
		,"csElecHistory.csehStatusText":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "状态描述最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csehEleAmount==true">
		${lz:set("haveEditable",true)}
		,"csElecHistory.csehEleAmount":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "充电量最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csehTimeLong==true">
		${lz:set("haveEditable",true)}
		,"csElecHistory.csehTimeLong":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "充电时长最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csehErrorCode==true">
		${lz:set("haveEditable",true)}
		,"csElecHistory.csehErrorCode":function(el){
			if(el.value.length>10 && el.value.indexOf("[*]")==-1)
				return "错误码最大长度为10个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csehCollectTime==true">
		${lz:set("haveEditable",true)}
		,"csElecHistory.csehCollectTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csehAddTime==true">
		${lz:set("haveEditable",true)}
		,"csElecHistory.csehAddTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csehData==true">
		${lz:set("haveEditable",true)}
		,"csElecHistory.csehData":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "接口数据最大长度为65535个字符";
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
	<form class="form editform" ref="CsElecHistory" id="elechistoryForm" name="elechistoryForm" action="${empty CTRL.action?"elechistory_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写电桩历史数据相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csElecHistoryToken}" name="csElecHistoryToken" id="csElecHistoryToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csElecHistory.csehId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csehId和after$csehId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csehId==true">
	${before$csehId}
	<dl class="csehId ${CTRL.e.csehId?"hidden":""}" major  ref="csehId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csehId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csElecHistory$csehId!=null">${csElecHistory$csehId}</s:if><s:else>
			<input type="hidden" value="${csElecHistory.csehId}" name="csElecHistory.csehId" id="csehId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csehId">${csElecHistory.csehId}</textarea>
		 		<span>
		 	
			 ${csElecHistory.csehId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csehId}
	</s:if>
	
	${lz:set("注释","*****************设备ID字段的输入框代码*****************")}
	${lz:set("注释","before$csehDid和after$csehDid变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csehDid==true">
	${before$csehDid}
	<dl class="csehDid " major  ref="csehDid" >
		<dt>设备ID:</dt>
		<s:if test="#request.CTRL.e.csehDid==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csElecHistory$csehDid!=null">${csElecHistory$csehDid}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csElecHistory.csehDid" id="csehDid"  value="${csElecHistory.csehDid}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入电桩历史数据的设备ID</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****设备ID字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csehDid">${csElecHistory.csehDid}</textarea>
		 		<span>
		 	
			 ${csElecHistory.csehDid$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csehDid}
	</s:if>
	
	${lz:set("注释","*****************所属电桩字段的输入框代码*****************")}
	${lz:set("注释","before$csehPowerPile和after$csehPowerPile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csehPowerPile==true">
	${before$csehPowerPile}
	<dl class="csehPowerPile " major  ref="csehPowerPile" >
		<dt>所属电桩:</dt>
		<s:if test="#request.CTRL.e.csehPowerPile==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csElecHistory$csehPowerPile!=null">${csElecHistory$csehPowerPile}</s:if><s:else>
		 			<input title="请输入充电桩标识码进行查询" class="combox narrow" action="${basePath}${proname}/object/powerpile_query.do?value={param}" type="text" id="csehPowerPile" name="csElecHistory.csehPowerPile" text="${get:CsPowerPile(csElecHistory.csehPowerPile).csppNo}" value="${csElecHistory.csehPowerPile}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csehPowerPile').val())==''){return;};window.href('${basePath}${proname}/object/powerpile_details.do?key='+$('#csehPowerPile').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属电桩字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csehPowerPile">${csElecHistory.csehPowerPile}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/powerpile_details.do?key=${csElecHistory.csehPowerPile}',{ctrl:{editable:false,visible:true}})">
			 ${csElecHistory.csehPowerPile$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csehPowerPile}
	</s:if>
	
	${lz:set("注释","*****************状态编码字段的输入框代码*****************")}
	${lz:set("注释","before$csehStatusCode和after$csehStatusCode变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csehStatusCode==true">
	${before$csehStatusCode}
	<dl class="csehStatusCode " major  ref="csehStatusCode" >
		<dt>状态编码:</dt>
		<s:if test="#request.CTRL.e.csehStatusCode==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csElecHistory$csehStatusCode!=null">${csElecHistory$csehStatusCode}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csElecHistory.csehStatusCode" id="csehStatusCode"  value="${csElecHistory.csehStatusCode}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入电桩历史数据的状态编码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态编码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csehStatusCode">${csElecHistory.csehStatusCode}</textarea>
		 		<span>
		 	
			 ${csElecHistory.csehStatusCode$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csehStatusCode}
	</s:if>
	
	${lz:set("注释","*****************状态描述字段的输入框代码*****************")}
	${lz:set("注释","before$csehStatusText和after$csehStatusText变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csehStatusText==true">
	${before$csehStatusText}
	<dl class="csehStatusText " major  ref="csehStatusText" style="width:98%;">
		<dt>状态描述:</dt>
		<s:if test="#request.CTRL.e.csehStatusText==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csElecHistory$csehStatusText!=null">${csElecHistory$csehStatusText}</s:if><s:else>
		 	<textarea class="input wide"  id="csehStatusText" name="csElecHistory.csehStatusText" rows="5">${csElecHistory.csehStatusText}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入电桩历史数据的状态描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csElecHistory.csehStatusText$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csehStatusText}
	</s:if>
	
	${lz:set("注释","*****************充电量字段的输入框代码*****************")}
	${lz:set("注释","before$csehEleAmount和after$csehEleAmount变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csehEleAmount==true">
	${before$csehEleAmount}
	<dl class="csehEleAmount " major  ref="csehEleAmount" >
		<dt>充&nbsp;&nbsp;电&nbsp;&nbsp;量:</dt>
		<s:if test="#request.CTRL.e.csehEleAmount==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csElecHistory$csehEleAmount!=null">${csElecHistory$csehEleAmount}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csElecHistory.csehEleAmount" id="csehEleAmount"  value="${csElecHistory.csehEleAmount}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入电桩历史数据的充电量</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****充电量字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csehEleAmount">${csElecHistory.csehEleAmount}</textarea>
		 		<span>
		 	
			 ${csElecHistory.csehEleAmount$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csehEleAmount}
	</s:if>
	
	${lz:set("注释","*****************充电时长字段的输入框代码*****************")}
	${lz:set("注释","before$csehTimeLong和after$csehTimeLong变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csehTimeLong==true">
	${before$csehTimeLong}
	<dl class="csehTimeLong " major  ref="csehTimeLong" >
		<dt>充电时长:</dt>
		<s:if test="#request.CTRL.e.csehTimeLong==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csElecHistory$csehTimeLong!=null">${csElecHistory$csehTimeLong}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csElecHistory.csehTimeLong" id="csehTimeLong"  value="${csElecHistory.csehTimeLong}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入电桩历史数据的充电时长</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****充电时长字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csehTimeLong">${csElecHistory.csehTimeLong}</textarea>
		 		<span>
		 	
			 ${csElecHistory.csehTimeLong$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csehTimeLong}
	</s:if>
	
	${lz:set("注释","*****************错误码字段的输入框代码*****************")}
	${lz:set("注释","before$csehErrorCode和after$csehErrorCode变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csehErrorCode==true">
	${before$csehErrorCode}
	<dl class="csehErrorCode " major  ref="csehErrorCode" >
		<dt>错&nbsp;&nbsp;误&nbsp;&nbsp;码:</dt>
		<s:if test="#request.CTRL.e.csehErrorCode==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csElecHistory$csehErrorCode!=null">${csElecHistory$csehErrorCode}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="10" name="csElecHistory.csehErrorCode" id="csehErrorCode"  value="${csElecHistory.csehErrorCode}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入电桩历史数据的错误码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****错误码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csehErrorCode">${csElecHistory.csehErrorCode}</textarea>
		 		<span>
		 	
			 ${csElecHistory.csehErrorCode$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csehErrorCode}
	</s:if>
	
	${lz:set("注释","*****************数据时间字段的输入框代码*****************")}
	${lz:set("注释","before$csehCollectTime和after$csehCollectTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csehCollectTime==true">
	${before$csehCollectTime}
	<dl class="csehCollectTime " major  ref="csehCollectTime" >
		<dt>数据时间:</dt>
		<s:if test="#request.CTRL.e.csehCollectTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csElecHistory$csehCollectTime!=null">${csElecHistory$csehCollectTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csElecHistory.csehCollectTime" id="csehCollectTime"  value="<s:date name="csElecHistory.csehCollectTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择电桩历史数据的数据时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****数据时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csehCollectTime">${csElecHistory.csehCollectTime}</textarea>
		 		<span>
		 	
			 ${csElecHistory.csehCollectTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csehCollectTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csehAddTime和after$csehAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csehAddTime==true">
	${before$csehAddTime}
	<dl class="csehAddTime " major  ref="csehAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csehAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csElecHistory$csehAddTime!=null">${csElecHistory$csehAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csElecHistory.csehAddTime" id="csehAddTime"  value="<s:date name="csElecHistory.csehAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择电桩历史数据的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csehAddTime">${csElecHistory.csehAddTime}</textarea>
		 		<span>
		 	
			 ${csElecHistory.csehAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csehAddTime}
	</s:if>
	
	${lz:set("注释","*****************接口数据字段的输入框代码*****************")}
	${lz:set("注释","before$csehData和after$csehData变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csehData==true">
	${before$csehData}
	<dl class="csehData " minor  ref="csehData" style="width:98%;">
		<dt>接口数据:</dt>
		<s:if test="#request.CTRL.e.csehData==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csElecHistory$csehData!=null">${csElecHistory$csehData}</s:if><s:else>
		 	<textarea class="input wide"  id="csehData" name="csElecHistory.csehData" rows="5">${csElecHistory.csehData}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入电桩历史数据的接口数据</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****接口数据字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csElecHistory.csehData$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csehData}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csElecHistory.csehId}" name="csElecHistory.csehId" id="csehId" />
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