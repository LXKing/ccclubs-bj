<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/object/carmodel.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/object/carmodel.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csCarModel.cscmId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cscmId,cscmName,cscmType,cscmFile,cscmSeatS,cscmTrunk,cscmVolume,cscmCapacity,cscmThumb,cscmImage,cscmDetails,cscmCars,cscmRemark,cscmStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscmId,cscmName,cscmType,cscmFile,cscmSeatS,cscmTrunk,cscmVolume,cscmCapacity,cscmThumb,cscmImage,cscmDetails,cscmCars,cscmRemark,cscmStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cscmId,cscmName,cscmType,cscmFile,cscmSeatS,cscmTrunk,cscmVolume,cscmCapacity,cscmThumb,cscmImage,cscmDetails,cscmCars,cscmRemark,cscmStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscmId,cscmName,cscmType,cscmFile,cscmSeatS,cscmTrunk,cscmVolume,cscmCapacity,cscmThumb,cscmImage,cscmDetails,cscmCars,cscmUpdateTime,cscmAddTime,cscmRemark,cscmStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"车型编辑":CTRL.title}</title>
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
	$("#carmodelForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cscmId==true">
		${lz:set("haveEditable",true)}
		,"csCarModel.cscmId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscmName==true">
		${lz:set("haveEditable",true)}
		,"csCarModel.cscmName":function(el){
			if(jQuery.trim(el.value)=="")
				return "车型名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "车型名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscmType==true">
		${lz:set("haveEditable",true)}
		,"csCarModel.cscmType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择动力类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscmFile==true">
		${lz:set("haveEditable",true)}
		,"csCarModel.cscmFile":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择档类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscmSeatS==true">
		${lz:set("haveEditable",true)}
		,"csCarModel.cscmSeatS":function(el){
			if(jQuery.trim(el.value)=="")
				return "座位数不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscmTrunk==true">
		${lz:set("haveEditable",true)}
		,"csCarModel.cscmTrunk":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "后备箱容量最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscmVolume==true">
		${lz:set("haveEditable",true)}
		,"csCarModel.cscmVolume":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "排量/续航最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscmCapacity==true">
		${lz:set("haveEditable",true)}
		,"csCarModel.cscmCapacity":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "能量容量最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscmThumb==true">
		${lz:set("haveEditable",true)}
		,"csCarModel.cscmThumb":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscmImage==true">
		${lz:set("haveEditable",true)}
		,"csCarModel.cscmImage":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscmDetails==true">
		${lz:set("haveEditable",true)}
		,"csCarModel.cscmDetails":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "车型介绍最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscmUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csCarModel.cscmUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscmAddTime==true">
		${lz:set("haveEditable",true)}
		,"csCarModel.cscmAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscmRemark==true">
		${lz:set("haveEditable",true)}
		,"csCarModel.cscmRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscmStatus==true">
		${lz:set("haveEditable",true)}
		,"csCarModel.cscmStatus":function(el){
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
	<form class="form editform" ref="CsCarModel" id="carmodelForm" name="carmodelForm" action="${empty CTRL.action?"carmodel_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写车型相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csCarModelToken}" name="csCarModelToken" id="csCarModelToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csCarModel.cscmId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cscmId和after$cscmId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmId==true">
	${before$cscmId}
	<dl class="cscmId ${CTRL.e.cscmId?"hidden":""}" major  ref="cscmId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cscmId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csCarModel$cscmId!=null">${csCarModel$cscmId}</s:if><s:else>
			<input type="hidden" value="${csCarModel.cscmId}" name="csCarModel.cscmId" id="cscmId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscmId">${csCarModel.cscmId}</textarea>
		 		<span>
		 	
			 ${csCarModel.cscmId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmId}
	</s:if>
	
	${lz:set("注释","*****************车型名称字段的输入框代码*****************")}
	${lz:set("注释","before$cscmName和after$cscmName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmName==true">
	${before$cscmName}
	<dl class="cscmName " major  ref="cscmName" >
		<dt>车型名称:</dt>
		<s:if test="#request.CTRL.e.cscmName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCarModel$cscmName!=null">${csCarModel$cscmName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCarModel.cscmName" id="cscmName"  value="${csCarModel.cscmName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入车型的车型名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车型名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscmName">${csCarModel.cscmName}</textarea>
		 		<span>
		 	
			 ${csCarModel.cscmName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmName}
	</s:if>
	
	${lz:set("注释","*****************动力类型字段的输入框代码*****************")}
	${lz:set("注释","before$cscmType和after$cscmType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmType==true">
	${before$cscmType}
	<dl class="cscmType " major  ref="cscmType" >
		<dt>动力类型:</dt>
		<s:if test="#request.CTRL.e.cscmType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCarModel$cscmType!=null">${csCarModel$cscmType}</s:if><s:else>
		 	<select class="narrow" id="cscmType" name="csCarModel.cscmType">
		 		<option value="">请选择</option>
				<option value="0" ${csCarModel.cscmType==0?"selected":""}>汽油车</option>
				<option value="1" ${csCarModel.cscmType==1?"selected":""}>电动车</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车型的动力类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****动力类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscmType">${csCarModel.cscmType}</textarea>
		 		<span>
		 	
			 ${csCarModel.cscmType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmType}
	</s:if>
	
	${lz:set("注释","*****************档类型字段的输入框代码*****************")}
	${lz:set("注释","before$cscmFile和after$cscmFile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmFile==true">
	${before$cscmFile}
	<dl class="cscmFile " major  ref="cscmFile" >
		<dt>档&nbsp;&nbsp;类&nbsp;&nbsp;型:</dt>
		<s:if test="#request.CTRL.e.cscmFile==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCarModel$cscmFile!=null">${csCarModel$cscmFile}</s:if><s:else>
		 	<select class="narrow" id="cscmFile" name="csCarModel.cscmFile">
		 		<option value="">请选择</option>
				<option value="0" ${csCarModel.cscmFile==0?"selected":""}>自动档</option>
				<option value="1" ${csCarModel.cscmFile==1?"selected":""}>手动档</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车型的档类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****档类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscmFile">${csCarModel.cscmFile}</textarea>
		 		<span>
		 	
			 ${csCarModel.cscmFile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmFile}
	</s:if>
	
	${lz:set("注释","*****************座位数字段的输入框代码*****************")}
	${lz:set("注释","before$cscmSeatS和after$cscmSeatS变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmSeatS==true">
	${before$cscmSeatS}
	<dl class="cscmSeatS " major  ref="cscmSeatS" >
		<dt>座&nbsp;&nbsp;位&nbsp;&nbsp;数:</dt>
		<s:if test="#request.CTRL.e.cscmSeatS==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csCarModel$cscmSeatS!=null">${csCarModel$cscmSeatS}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csCarModel.cscmSeatS" id="cscmSeatS"  value="${csCarModel.cscmSeatS}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入车型的座位数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****座位数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscmSeatS">${csCarModel.cscmSeatS}</textarea>
		 		<span>
		 	
			 ${csCarModel.cscmSeatS$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmSeatS}
	</s:if>
	
	${lz:set("注释","*****************后备箱容量字段的输入框代码*****************")}
	${lz:set("注释","before$cscmTrunk和after$cscmTrunk变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmTrunk==true">
	${before$cscmTrunk}
	<dl class="cscmTrunk " major  ref="cscmTrunk" >
		<dt>后备箱容量:</dt>
		<s:if test="#request.CTRL.e.cscmTrunk==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCarModel$cscmTrunk!=null">${csCarModel$cscmTrunk}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCarModel.cscmTrunk" id="cscmTrunk"  value="${csCarModel.cscmTrunk}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车型的后备箱容量</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****后备箱容量字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscmTrunk">${csCarModel.cscmTrunk}</textarea>
		 		<span>
		 	
			 ${csCarModel.cscmTrunk$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmTrunk}
	</s:if>
	
	${lz:set("注释","*****************排量/续航字段的输入框代码*****************")}
	${lz:set("注释","before$cscmVolume和after$cscmVolume变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmVolume==true">
	${before$cscmVolume}
	<dl class="cscmVolume " minor  ref="cscmVolume" >
		<dt>排量/续航:</dt>
		<s:if test="#request.CTRL.e.cscmVolume==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCarModel$cscmVolume!=null">${csCarModel$cscmVolume}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCarModel.cscmVolume" id="cscmVolume"  value="${csCarModel.cscmVolume}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车型的排量/续航</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****排量/续航字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscmVolume">${csCarModel.cscmVolume}</textarea>
		 		<span>
		 	
			 ${csCarModel.cscmVolume$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmVolume}
	</s:if>
	
	${lz:set("注释","*****************能量容量字段的输入框代码*****************")}
	${lz:set("注释","before$cscmCapacity和after$cscmCapacity变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmCapacity==true">
	${before$cscmCapacity}
	<dl class="cscmCapacity " minor  ref="cscmCapacity" >
		<dt>能量容量:</dt>
		<s:if test="#request.CTRL.e.cscmCapacity==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCarModel$cscmCapacity!=null">${csCarModel$cscmCapacity}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csCarModel.cscmCapacity" id="cscmCapacity"  value="${csCarModel.cscmCapacity}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>油箱容量/电池电量</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****能量容量字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscmCapacity">${csCarModel.cscmCapacity}</textarea>
		 		<span>
		 	
			 ${csCarModel.cscmCapacity$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmCapacity}
	</s:if>
	
	${lz:set("注释","*****************缩略图字段的输入框代码*****************")}
	${lz:set("注释","before$cscmThumb和after$cscmThumb变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmThumb==true">
	${before$cscmThumb}
	<dl class="cscmThumb " minor  ref="cscmThumb" style="width:98%;">
		<dt>缩&nbsp;&nbsp;略&nbsp;&nbsp;图:</dt>
		<s:if test="#request.CTRL.e.cscmThumb==true">
		${lz:set("haveEditable",true)}
		<dd input="image">
		<s:if test="#request.csCarModel$cscmThumb!=null">${csCarModel$cscmThumb}</s:if><s:else>
		    <input type="text" class="input" maxlength="128" size="32" name="csCarModel.cscmThumb" id="cscmThumb"  value="${csCarModel.cscmThumb}"/>
			<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#cscmThumb').val(url)}})" class="button">设置图片</button>
			<button type="button" onclick="$('#cscmThumb').val('')" class="button">删除图片</button>
			<button type="button" onclick="$.thumb({url:$('#cscmThumb').val()})" class="button">查看图片</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>图片建议尺寸大于200*200,一定要正放形哦</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****缩略图字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscmThumb">${csCarModel.cscmThumb}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(csCarModel.cscmThumb))}
		 	<s:if test="#request.isVacant==false">
		 	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="${csCarModel.cscmThumb}"/>
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmThumb}
	</s:if>
	
	${lz:set("注释","*****************车型图片字段的输入框代码*****************")}
	${lz:set("注释","before$cscmImage和after$cscmImage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmImage==true">
	${before$cscmImage}
	<dl class="cscmImage " minor  ref="cscmImage" style="width:98%;">
		<dt>车型图片:</dt>
		<s:if test="#request.CTRL.e.cscmImage==true">
		${lz:set("haveEditable",true)}
		<dd input="image">
		<s:if test="#request.csCarModel$cscmImage!=null">${csCarModel$cscmImage}</s:if><s:else>
		    <input type="text" class="input" maxlength="128" size="32" name="csCarModel.cscmImage" id="cscmImage"  value="${csCarModel.cscmImage}"/>
			<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#cscmImage').val(url)}})" class="button">设置图片</button>
			<button type="button" onclick="$('#cscmImage').val('')" class="button">删除图片</button>
			<button type="button" onclick="$.thumb({url:$('#cscmImage').val()})" class="button">查看图片</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>图片建议尺寸500*300像素（比例）</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车型图片字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscmImage">${csCarModel.cscmImage}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(csCarModel.cscmImage))}
		 	<s:if test="#request.isVacant==false">
		 	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="${csCarModel.cscmImage}"/>
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmImage}
	</s:if>
	
	${lz:set("注释","*****************车型介绍字段的输入框代码*****************")}
	${lz:set("注释","before$cscmDetails和after$cscmDetails变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmDetails==true">
	${before$cscmDetails}
	<dl class="cscmDetails " minor  ref="cscmDetails" style="width:98%;">
		<dt>车型介绍:</dt>
		<s:if test="#request.CTRL.e.cscmDetails==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCarModel$cscmDetails!=null">${csCarModel$cscmDetails}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csCarModel.cscmDetails" id="cscmDetails"  value="${csCarModel.cscmDetails}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>html文件地址相对路径</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车型介绍字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscmDetails">${csCarModel.cscmDetails}</textarea>
		 		<span>
		 	
			 ${csCarModel.cscmDetails$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmDetails}
	</s:if>
	
	${lz:set("注释","*****************车辆列表字段的输入框代码*****************")}
	${lz:set("注释","before$cscmCars和after$cscmCars变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmCars==true">
	${before$cscmCars}
	<dl class="cscmCars " minor  ref="cscmCars" style="width:98%;">
		<dt>车辆列表:</dt>
		<s:if test="#request.CTRL.e.cscmCars==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csCarModel$cscmCars!=null">${csCarModel$cscmCars}</s:if><s:else>
		  		<s:if test="csCarModel.cscmId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="cscmCars" name="cscmCars" frameborder='0' src="" url="${basePath}${proname}/object/car.do?inFrame=true&canQuery=false&fields=cscNumber,cscOutlets,cscProvid,cscStatus&cscModel=${csCarModel.cscmId}&ctrl={queryable:false,fields:{cscModel:{visible:true,editable:false,defaultValue:${csCarModel.cscmId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#cscmCars').attr('src',$('#cscmCars').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑车辆列表
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车辆列表字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csCarModel.cscmId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="cscmCars" name="cscmCars" frameborder='0' src="" url="${basePath}${proname}/object/car.do?inFrame=true&canQuery=false&fields=cscNumber,cscOutlets,cscProvid,cscStatus&cscModel=${csCarModel.cscmId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#cscmCars').attr('src',$('#cscmCars').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$cscmCars}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscmUpdateTime和after$cscmUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmUpdateTime==true">
	${before$cscmUpdateTime}
	<dl class="cscmUpdateTime " major  ref="cscmUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cscmUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCarModel$cscmUpdateTime!=null">${csCarModel$cscmUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCarModel.cscmUpdateTime" id="cscmUpdateTime"  value="<s:date name="csCarModel.cscmUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车型的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscmUpdateTime">${csCarModel.cscmUpdateTime}</textarea>
		 		<span>
		 	
			 ${csCarModel.cscmUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscmAddTime和after$cscmAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmAddTime==true">
	${before$cscmAddTime}
	<dl class="cscmAddTime " major  ref="cscmAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cscmAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCarModel$cscmAddTime!=null">${csCarModel$cscmAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCarModel.cscmAddTime" id="cscmAddTime"  value="<s:date name="csCarModel.cscmAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车型的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscmAddTime">${csCarModel.cscmAddTime}</textarea>
		 		<span>
		 	
			 ${csCarModel.cscmAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmAddTime}
	</s:if>
	
	${lz:set("注释","*****************备注字段的输入框代码*****************")}
	${lz:set("注释","before$cscmRemark和after$cscmRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmRemark==true">
	${before$cscmRemark}
	<dl class="cscmRemark " minor  ref="cscmRemark" style="width:98%;">
		<dt>备　　注:</dt>
		<s:if test="#request.CTRL.e.cscmRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csCarModel$cscmRemark!=null">${csCarModel$cscmRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="cscmRemark" name="csCarModel.cscmRemark" rows="5">${csCarModel.cscmRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车型的备注</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csCarModel.cscmRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmRemark}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cscmStatus和after$cscmStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscmStatus==true">
	${before$cscmStatus}
	<dl class="cscmStatus " major  ref="cscmStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cscmStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCarModel$cscmStatus!=null">${csCarModel$cscmStatus}</s:if><s:else>
		 	<select class="narrow" id="cscmStatus" name="csCarModel.cscmStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csCarModel.cscmStatus==1?"selected":""}>正常</option>
				<option value="0" ${csCarModel.cscmStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车型的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscmStatus">${csCarModel.cscmStatus}</textarea>
		 		<span>
		 	
			 ${csCarModel.cscmStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscmStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csCarModel.cscmId}" name="csCarModel.cscmId" id="cscmId" />
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