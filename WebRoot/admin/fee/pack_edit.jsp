<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/fee/pack.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/fee/pack.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csPack.cspId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cspId,cspName,cspPrice,cspFlag,cspValidity,cspDay,cspInfos,cspProfile,cspStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cspId,cspName,cspPrice,cspFlag,cspValidity,cspDay,cspInfos,cspProfile,cspStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cspId,cspName,cspPrice,cspFlag,cspValidity,cspDay,cspInfos,cspProfile,cspStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cspId,cspName,cspPrice,cspFlag,cspValidity,cspDay,cspInfos,cspProfile,cspUpdateTime,cspAddTime,cspStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"系统套餐编辑":CTRL.title}</title>
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
		if($("#cspProfile").size()>0){
			 //构造编辑页csPack.cspProfile为html编辑控件
			 KE.init({
				id : 'cspProfile',
				width : '625px',
				height : '300px' , 
				imageUploadJson : '${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}?app=pack_edit&type=img${ lz:config("upload.url")==null ? "" : "&remote="}${ lz:config("upload.url")==null ? "" : basePath}${ lz:config("upload.url")==null ? "" : "callback.jsp"}'
			 });
			 KE.create("cspProfile");
		 }
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#packForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cspId==true">
		${lz:set("haveEditable",true)}
		,"csPack.cspId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspName==true">
		${lz:set("haveEditable",true)}
		,"csPack.cspName":function(el){
			if(jQuery.trim(el.value)=="")
				return "套餐名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "套餐名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspPrice==true">
		${lz:set("haveEditable",true)}
		,"csPack.cspPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "套餐价格输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspFlag==true">
		${lz:set("haveEditable",true)}
		,"csPack.cspFlag":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "套餐标识最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspValidity==true">
		${lz:set("haveEditable",true)}
		,"csPack.cspValidity":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspDay==true">
		${lz:set("haveEditable",true)}
		,"csPack.cspDay":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspReorder==true">
		${lz:set("haveEditable",true)}
		,"csPack.cspReorder":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择可自动续订";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspProfile==true">
		${lz:set("haveEditable",true)}
		,"csPack.cspProfile":function(el){
			if($("#cspProfile").get(0))
			 	KE.sync("cspProfile");		 
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "套餐说明最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csPack.cspUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspAddTime==true">
		${lz:set("haveEditable",true)}
		,"csPack.cspAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspStatus==true">
		${lz:set("haveEditable",true)}
		,"csPack.cspStatus":function(el){
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
	<form class="form editform" ref="CsPack" id="packForm" name="packForm" action="${empty CTRL.action?"pack_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写系统套餐相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csPackToken}" name="csPackToken" id="csPackToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csPack.cspId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cspId和after$cspId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspId==true">
	${before$cspId}
	<dl class="cspId ${CTRL.e.cspId?"hidden":""}" major  ref="cspId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cspId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csPack$cspId!=null">${csPack$cspId}</s:if><s:else>
			<input type="hidden" value="${csPack.cspId}" name="csPack.cspId" id="cspId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspId">${csPack.cspId}</textarea>
		 		<span>
		 	
			 ${csPack.cspId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspId}
	</s:if>
	
	${lz:set("注释","*****************套餐名称字段的输入框代码*****************")}
	${lz:set("注释","before$cspName和after$cspName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspName==true">
	${before$cspName}
	<dl class="cspName " major  ref="cspName" >
		<dt>套餐名称:</dt>
		<s:if test="#request.CTRL.e.cspName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csPack$cspName!=null">${csPack$cspName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csPack.cspName" id="cspName"  value="${csPack.cspName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入系统套餐的套餐名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****套餐名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspName">${csPack.cspName}</textarea>
		 		<span>
		 	
			 ${csPack.cspName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspName}
	</s:if>
	
	${lz:set("注释","*****************套餐价格字段的输入框代码*****************")}
	${lz:set("注释","before$cspPrice和after$cspPrice变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspPrice==true">
	${before$cspPrice}
	<dl class="cspPrice " major  ref="cspPrice" >
		<dt>套餐价格:</dt>
		<s:if test="#request.CTRL.e.cspPrice==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csPack$cspPrice!=null">${csPack$cspPrice}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csPack.cspPrice" id="cspPrice"  value="${csPack.cspPrice}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入系统套餐的套餐价格</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****套餐价格字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspPrice">${csPack.cspPrice}</textarea>
		 		<span>
		 	
			 ${csPack.cspPrice$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspPrice}
	</s:if>
	
	${lz:set("注释","*****************套餐标识字段的输入框代码*****************")}
	${lz:set("注释","before$cspFlag和after$cspFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspFlag==true">
	${before$cspFlag}
	<dl class="cspFlag " major  ref="cspFlag" >
		<dt>套餐标识:</dt>
		<s:if test="#request.CTRL.e.cspFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csPack$cspFlag!=null">${csPack$cspFlag}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csPack.cspFlag" id="cspFlag"  value="${csPack.cspFlag}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统套餐的套餐标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****套餐标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspFlag">${csPack.cspFlag}</textarea>
		 		<span>
		 	
			 ${csPack.cspFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspFlag}
	</s:if>
	
	${lz:set("注释","*****************有效期类型字段的输入框代码*****************")}
	${lz:set("注释","before$cspValidity和after$cspValidity变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspValidity==true">
	${before$cspValidity}
	<dl class="cspValidity " major  ref="cspValidity" >
		<dt>有效期类型:</dt>
		<s:if test="#request.CTRL.e.cspValidity==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csPack$cspValidity!=null">${csPack$cspValidity}</s:if><s:else>
		 	<select class="narrow" id="cspValidity" name="csPack.cspValidity">
		 		<option value="">请选择</option>
				<option value="0" ${csPack.cspValidity==0?"selected":""}>不限</option>
				<option value="1" ${csPack.cspValidity==1?"selected":""}>一月</option>
				<option value="2" ${csPack.cspValidity==2?"selected":""}>一季</option>
				<option value="3" ${csPack.cspValidity==3?"selected":""}>半年</option>
				<option value="4" ${csPack.cspValidity==4?"selected":""}>一年</option>
				<option value="5" ${csPack.cspValidity==5?"selected":""}>天数</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择系统套餐的有效期类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****有效期类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspValidity">${csPack.cspValidity}</textarea>
		 		<span>
		 	
			 ${csPack.cspValidity$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspValidity}
	</s:if>
	
	${lz:set("注释","*****************有效天数字段的输入框代码*****************")}
	${lz:set("注释","before$cspDay和after$cspDay变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspDay==true">
	${before$cspDay}
	<dl class="cspDay " minor  ref="cspDay" >
		<dt>有效天数:</dt>
		<s:if test="#request.CTRL.e.cspDay==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csPack$cspDay!=null">${csPack$cspDay}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csPack.cspDay" id="cspDay"  value="${csPack.cspDay}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>当有效期类型为天数时有效</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****有效天数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspDay">${csPack.cspDay}</textarea>
		 		<span>
		 	
			 ${csPack.cspDay$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspDay}
	</s:if>
	
	${lz:set("注释","*****************可自动续订字段的输入框代码*****************")}
	${lz:set("注释","before$cspReorder和after$cspReorder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspReorder==true">
	${before$cspReorder}
	<dl class="cspReorder " major  ref="cspReorder" >
		<dt>可自动续订:</dt>
		<s:if test="#request.CTRL.e.cspReorder==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csPack$cspReorder!=null">${csPack$cspReorder}</s:if><s:else>
		 	<select class="narrow" id="cspReorder" name="csPack.cspReorder">
		 		<option value="">请选择</option>
				<option value="0" ${csPack.cspReorder==0?"selected":""}>否</option>
				<option value="1" ${csPack.cspReorder==1?"selected":""}>是</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>当有效期类型不为不限时有效</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****可自动续订字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspReorder">${csPack.cspReorder}</textarea>
		 		<span>
		 	
			 ${csPack.cspReorder$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspReorder}
	</s:if>
	
	${lz:set("注释","*****************套餐内容字段的输入框代码*****************")}
	${lz:set("注释","before$cspInfos和after$cspInfos变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspInfos==true">
	${before$cspInfos}
	<dl class="cspInfos " minor  ref="cspInfos" style="width:98%;">
		<dt>套餐内容:</dt>
		<s:if test="#request.CTRL.e.cspInfos==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csPack$cspInfos!=null">${csPack$cspInfos}</s:if><s:else>
		  		<s:if test="csPack.cspId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="cspInfos" name="cspInfos" frameborder='0' src="" url="${basePath}${proname}/fee/packinfo.do?inFrame=true&canQuery=false&fields=cspiName,cspiGoods,cspiOutlets,cspiModel,cspiType,cspiValue&cspiPack=${csPack.cspId}&ctrl={queryable:false,fields:{cspiPack:{visible:true,editable:false,defaultValue:${csPack.cspId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#cspInfos').attr('src',$('#cspInfos').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑套餐内容
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****套餐内容字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csPack.cspId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="cspInfos" name="cspInfos" frameborder='0' src="" url="${basePath}${proname}/fee/packinfo.do?inFrame=true&canQuery=false&fields=cspiName,cspiGoods,cspiOutlets,cspiModel,cspiType,cspiValue&cspiPack=${csPack.cspId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#cspInfos').attr('src',$('#cspInfos').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$cspInfos}
	</s:if>
	
	${lz:set("注释","*****************套餐说明字段的输入框代码*****************")}
	${lz:set("注释","before$cspProfile和after$cspProfile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspProfile==true">
	${before$cspProfile}
	<dl class="cspProfile " minor  ref="cspProfile" style="width:98%;">
		<dt>套餐说明:</dt>
		<s:if test="#request.CTRL.e.cspProfile==true">
		${lz:set("haveEditable",true)}
		<dd input="html">
		<s:if test="#request.csPack$cspProfile!=null">${csPack$cspProfile}</s:if><s:else>
		    <textarea class="input wide"  id="cspProfile" name="csPack.cspProfile" style="height:300px;visibility:hidden;">${csPack.cspProfile}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统套餐的套餐说明</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****套餐说明字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csPack.cspProfile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspProfile}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cspUpdateTime和after$cspUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspUpdateTime==true">
	${before$cspUpdateTime}
	<dl class="cspUpdateTime " major  ref="cspUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cspUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csPack$cspUpdateTime!=null">${csPack$cspUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csPack.cspUpdateTime" id="cspUpdateTime"  value="<s:date name="csPack.cspUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统套餐的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspUpdateTime">${csPack.cspUpdateTime}</textarea>
		 		<span>
		 	
			 ${csPack.cspUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cspAddTime和after$cspAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspAddTime==true">
	${before$cspAddTime}
	<dl class="cspAddTime " major  ref="cspAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cspAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csPack$cspAddTime!=null">${csPack$cspAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csPack.cspAddTime" id="cspAddTime"  value="<s:date name="csPack.cspAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统套餐的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspAddTime">${csPack.cspAddTime}</textarea>
		 		<span>
		 	
			 ${csPack.cspAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cspStatus和after$cspStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspStatus==true">
	${before$cspStatus}
	<dl class="cspStatus " major  ref="cspStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cspStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csPack$cspStatus!=null">${csPack$cspStatus}</s:if><s:else>
		 	<select class="narrow" id="cspStatus" name="csPack.cspStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csPack.cspStatus==1?"selected":""}>正常</option>
				<option value="0" ${csPack.cspStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统套餐的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspStatus">${csPack.cspStatus}</textarea>
		 		<span>
		 	
			 ${csPack.cspStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csPack.cspId}" name="csPack.cspId" id="cspId" />
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