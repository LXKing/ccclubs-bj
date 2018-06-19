<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/configurator/wx/wxorder.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/configurator/wx/wxorder.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csWxOrder.cswoId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cswoId,cswoName,cswoType,cswoRtype,cswoContent,cswoNews,cswoClass,cswoPriority,cswoStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cswoId,cswoName,cswoType,cswoRtype,cswoContent,cswoNews,cswoClass,cswoPriority,cswoStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cswoId,cswoName,cswoType,cswoRtype,cswoContent,cswoNews,cswoClass,cswoPriority,cswoStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cswoId,cswoName,cswoType,cswoRtype,cswoContent,cswoNews,cswoClass,cswoPriority,cswoStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"微信指令编辑":CTRL.title}</title>
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
	$("#wxorderForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cswoId==true">
		${lz:set("haveEditable",true)}
		,"csWxOrder.cswoId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswoName==true">
		${lz:set("haveEditable",true)}
		,"csWxOrder.cswoName":function(el){
			if(jQuery.trim(el.value)=="")
				return "指令名称不能为空";
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "指令名称最大长度为256个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("wxorder_query.do",{exists:true,cswoName:el.value});
				if(exists && exists.length>0){
					if(${csWxOrder.cswoId==null} || exists[0].value!=$("#cswoId").val())
						return "指令名称已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswoType==true">
		${lz:set("haveEditable",true)}
		,"csWxOrder.cswoType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择匹配类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswoRtype==true">
		${lz:set("haveEditable",true)}
		,"csWxOrder.cswoRtype":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择回复方式";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswoContent==true">
		${lz:set("haveEditable",true)}
		,"csWxOrder.cswoContent":function(el){
			if(el.value.length>1024 && el.value.indexOf("[*]")==-1)
				return "文字内容最大长度为1024个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswoClass==true">
		${lz:set("haveEditable",true)}
		,"csWxOrder.cswoClass":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswoPriority==true">
		${lz:set("haveEditable",true)}
		,"csWxOrder.cswoPriority":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswoTime==true">
		${lz:set("haveEditable",true)}
		,"csWxOrder.cswoTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "更改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cswoStatus==true">
		${lz:set("haveEditable",true)}
		,"csWxOrder.cswoStatus":function(el){
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
	<form class="form editform" ref="CsWxOrder" id="wxorderForm" name="wxorderForm" action="${empty CTRL.action?"wxorder_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写微信指令相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csWxOrderToken}" name="csWxOrderToken" id="csWxOrderToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csWxOrder.cswoId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cswoId和after$cswoId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswoId==true">
	${before$cswoId}
	<dl class="cswoId ${CTRL.e.cswoId?"hidden":""}" major  ref="cswoId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cswoId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csWxOrder$cswoId!=null">${csWxOrder$cswoId}</s:if><s:else>
			<input type="hidden" value="${csWxOrder.cswoId}" name="csWxOrder.cswoId" id="cswoId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswoId">${csWxOrder.cswoId}</textarea>
		 		<span>
		 	
			 ${csWxOrder.cswoId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswoId}
	</s:if>
	
	${lz:set("注释","*****************指令名称字段的输入框代码*****************")}
	${lz:set("注释","before$cswoName和after$cswoName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswoName==true">
	${before$cswoName}
	<dl class="cswoName " major  ref="cswoName" style="width:98%;">
		<dt>指令名称:</dt>
		<s:if test="#request.CTRL.e.cswoName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csWxOrder$cswoName!=null">${csWxOrder$cswoName}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="256" name="csWxOrder.cswoName" id="cswoName"  value="${csWxOrder.cswoName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>模糊指令时，多个不同的词之前用|分隔</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****指令名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cswoName">${csWxOrder.cswoName}</textarea>
		 		<span>
		 	
			 ${csWxOrder.cswoName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswoName}
	</s:if>
	
	${lz:set("注释","*****************匹配类型字段的输入框代码*****************")}
	${lz:set("注释","before$cswoType和after$cswoType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswoType==true">
	${before$cswoType}
	<dl class="cswoType " major  ref="cswoType" >
		<dt>匹配类型:</dt>
		<s:if test="#request.CTRL.e.cswoType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csWxOrder$cswoType!=null">${csWxOrder$cswoType}</s:if><s:else>
		 	<select class="narrow" id="cswoType" name="csWxOrder.cswoType">
		 		<option value="">请选择</option>
				<option value="0" ${csWxOrder.cswoType==0?"selected":""}>精确匹配</option>
				<option value="1" ${csWxOrder.cswoType==1?"selected":""}>模糊匹配</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择微信指令的匹配类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****匹配类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswoType">${csWxOrder.cswoType}</textarea>
		 		<span>
		 	
			 ${csWxOrder.cswoType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswoType}
	</s:if>
	
	${lz:set("注释","*****************回复方式字段的输入框代码*****************")}
	${lz:set("注释","before$cswoRtype和after$cswoRtype变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswoRtype==true">
	${before$cswoRtype}
	<dl class="cswoRtype " major  ref="cswoRtype" >
		<dt>回复方式:</dt>
		<s:if test="#request.CTRL.e.cswoRtype==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csWxOrder$cswoRtype!=null">${csWxOrder$cswoRtype}</s:if><s:else>
		 	<select class="narrow" id="cswoRtype" name="csWxOrder.cswoRtype">
		 		<option value="">请选择</option>
				<option value="0" ${csWxOrder.cswoRtype==0?"selected":""}>文字内容</option>
				<option value="1" ${csWxOrder.cswoRtype==1?"selected":""}>图文内容</option>
				<option value="2" ${csWxOrder.cswoRtype==2?"selected":""}>调用过程</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择微信指令的回复方式</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****回复方式字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswoRtype">${csWxOrder.cswoRtype}</textarea>
		 		<span>
		 	
			 ${csWxOrder.cswoRtype$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswoRtype}
	</s:if>
	
	${lz:set("注释","*****************文字内容字段的输入框代码*****************")}
	${lz:set("注释","before$cswoContent和after$cswoContent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswoContent==true">
	${before$cswoContent}
	<dl class="cswoContent " minor  ref="cswoContent" style="width:98%;">
		<dt>文字内容:</dt>
		<s:if test="#request.CTRL.e.cswoContent==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csWxOrder$cswoContent!=null">${csWxOrder$cswoContent}</s:if><s:else>
		 	<textarea class="input wide"  id="cswoContent" name="csWxOrder.cswoContent" rows="5">${csWxOrder.cswoContent}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入微信指令的文字内容</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****文字内容字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csWxOrder.cswoContent$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswoContent}
	</s:if>
	
	${lz:set("注释","*****************图文内容字段的输入框代码*****************")}
	${lz:set("注释","before$cswoNews和after$cswoNews变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswoNews==true">
	${before$cswoNews}
	<dl class="cswoNews " minor  ref="cswoNews" style="width:98%;">
		<dt>图文内容:</dt>
		<s:if test="#request.CTRL.e.cswoNews==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csWxOrder$cswoNews!=null">${csWxOrder$cswoNews}</s:if><s:else>
		  		<s:if test="csWxOrder.cswoId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="cswoNews" name="cswoNews" frameborder='0' src="" url="${basePath}${proname}/configurator/wx/wxcontent.do?inFrame=true&canQuery=false&all=true&cswcOrder=${csWxOrder.cswoId}&ctrl={queryable:false,fields:{cswcOrder:{visible:true,editable:false,defaultValue:${csWxOrder.cswoId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#cswoNews').attr('src',$('#cswoNews').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑图文内容
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****图文内容字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csWxOrder.cswoId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="cswoNews" name="cswoNews" frameborder='0' src="" url="${basePath}${proname}/configurator/wx/wxcontent.do?inFrame=true&canQuery=false&all=true&cswcOrder=${csWxOrder.cswoId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#cswoNews').attr('src',$('#cswoNews').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$cswoNews}
	</s:if>
	
	${lz:set("注释","*****************调用过程字段的输入框代码*****************")}
	${lz:set("注释","before$cswoClass和after$cswoClass变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswoClass==true">
	${before$cswoClass}
	<dl class="cswoClass " major  ref="cswoClass" >
		<dt>调用过程:</dt>
		<s:if test="#request.CTRL.e.cswoClass==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csWxOrder$cswoClass!=null">${csWxOrder$cswoClass}</s:if><s:else>
		 	<select class="narrow" id="cswoClass" name="csWxOrder.cswoClass">
		 		<option value="">请选择</option>
				    ${lz:set("items", lz:query$("ccclubs_system","select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)" ,'WX_ORDER_CLASS') )}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.spId}" ${csWxOrder.cswoClass==item.spId?"selected":""}>${item.spName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cswoClass').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?key='+$('#cswoClass').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em>预先设置好的过程处理</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****调用过程字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswoClass">${csWxOrder.cswoClass}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${csWxOrder.cswoClass}',{ctrl:{editable:false,visible:true}})">
			 ${csWxOrder.cswoClass$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswoClass}
	</s:if>
	
	${lz:set("注释","*****************优先级字段的输入框代码*****************")}
	${lz:set("注释","before$cswoPriority和after$cswoPriority变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswoPriority==true">
	${before$cswoPriority}
	<dl class="cswoPriority " major  ref="cswoPriority" >
		<dt>优&nbsp;&nbsp;先&nbsp;&nbsp;级:</dt>
		<s:if test="#request.CTRL.e.cswoPriority==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csWxOrder$cswoPriority!=null">${csWxOrder$cswoPriority}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csWxOrder.cswoPriority" id="cswoPriority"  value="${csWxOrder.cswoPriority}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入微信指令的优先级</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****优先级字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswoPriority">${csWxOrder.cswoPriority}</textarea>
		 		<span>
		 	
			 ${csWxOrder.cswoPriority$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswoPriority}
	</s:if>
	
	${lz:set("注释","*****************更改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cswoTime和after$cswoTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswoTime==true">
	${before$cswoTime}
	<dl class="cswoTime " major  ref="cswoTime" >
		<dt>更改时间:</dt>
		<s:if test="#request.CTRL.e.cswoTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csWxOrder$cswoTime!=null">${csWxOrder$cswoTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csWxOrder.cswoTime" id="cswoTime"  value="<s:date name="csWxOrder.cswoTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择微信指令的更改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****更改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswoTime">${csWxOrder.cswoTime}</textarea>
		 		<span>
		 	
			 ${csWxOrder.cswoTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswoTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cswoStatus和after$cswoStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cswoStatus==true">
	${before$cswoStatus}
	<dl class="cswoStatus " major  ref="cswoStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cswoStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csWxOrder$cswoStatus!=null">${csWxOrder$cswoStatus}</s:if><s:else>
		 	<select class="narrow" id="cswoStatus" name="csWxOrder.cswoStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csWxOrder.cswoStatus==1?"selected":""}>正常</option>
				<option value="0" ${csWxOrder.cswoStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择微信指令的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cswoStatus">${csWxOrder.cswoStatus}</textarea>
		 		<span>
		 	
			 ${csWxOrder.cswoStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cswoStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csWxOrder.cswoId}" name="csWxOrder.cswoId" id="cswoId" />
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