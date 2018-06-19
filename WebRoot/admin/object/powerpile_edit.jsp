<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/object/powerpile.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/object/powerpile.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csPowerPile.csppId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csppId,csppHost,csppNo,csppIndex,csppOutlets,csppCharge,csppPerson,csppTel,csppImage,csppLocation,csppRemark,csppStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csppId,csppHost,csppNo,csppIndex,csppOutlets,csppCharge,csppPerson,csppTel,csppImage,csppLocation,csppRemark,csppStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csppId,csppHost,csppNo,csppIndex,csppOutlets,csppCharge,csppPerson,csppTel,csppImage,csppLocation,csppRemark,csppStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csppId,csppHost,csppNo,csppIndex,csppOutlets,csppCharge,csppPerson,csppTel,csppImage,csppLocation,csppRemark,csppStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"充电桩编辑":CTRL.title}</title>
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
	$("#csppLocation").click(function(){
		//$(this).blur();		
		$.getMapPoint(function(Latitude,Longitude){
			$("#csppLocation").val(Latitude+","+Longitude);
		},$.trim($("#csppLocation").val())==""?{}:{
			Latitude:Number($("#csppLocation").val().split(",")[0]),
			Longitude:Number($("#csppLocation").val().split(",")[1])
		});
	}).mousedown(function(){
		$("#csppLocation").select().focus();
		return false;
	}).blur(function(){
		$(this).val($(this).val().replace("，",","));
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
	$("#powerpileForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csppId==true">
		${lz:set("haveEditable",true)}
		,"csPowerPile.csppId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csppHost==true">
		${lz:set("haveEditable",true)}
		,"csPowerPile.csppHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csppNo==true">
		${lz:set("haveEditable",true)}
		,"csPowerPile.csppNo":function(el){
			if(jQuery.trim(el.value)=="")
				return "标识码不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "标识码最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csppIndex==true">
		${lz:set("haveEditable",true)}
		,"csPowerPile.csppIndex":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csppOutlets==true">
		${lz:set("haveEditable",true)}
		,"csPowerPile.csppOutlets":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csppCharge==true">
		${lz:set("haveEditable",true)}
		,"csPowerPile.csppCharge":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csppPerson==true">
		${lz:set("haveEditable",true)}
		,"csPowerPile.csppPerson":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "负责人最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csppTel==true">
		${lz:set("haveEditable",true)}
		,"csPowerPile.csppTel":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "联系电话最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csppImage==true">
		${lz:set("haveEditable",true)}
		,"csPowerPile.csppImage":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csppLocation==true">
		${lz:set("haveEditable",true)}
		,"csPowerPile.csppLocation":function(el){
			if(jQuery.trim(el.value)=="")
				return "位置信息不能为空";
			if(el.value.length>64 && el.value.indexOf("[*]")==-1)
				return "位置信息最大长度为64个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csppRemark==true">
		${lz:set("haveEditable",true)}
		,"csPowerPile.csppRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csppUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csPowerPile.csppUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csppAddTime==true">
		${lz:set("haveEditable",true)}
		,"csPowerPile.csppAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csppStatus==true">
		${lz:set("haveEditable",true)}
		,"csPowerPile.csppStatus":function(el){
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
	<form class="form editform" ref="CsPowerPile" id="powerpileForm" name="powerpileForm" action="${empty CTRL.action?"powerpile_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写充电桩相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csPowerPileToken}" name="csPowerPileToken" id="csPowerPileToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csPowerPile.csppId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csppId和after$csppId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csppId==true">
	${before$csppId}
	<dl class="csppId ${CTRL.e.csppId?"hidden":""}" major  ref="csppId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csppId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csPowerPile$csppId!=null">${csPowerPile$csppId}</s:if><s:else>
			<input type="hidden" value="${csPowerPile.csppId}" name="csPowerPile.csppId" id="csppId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csppId">${csPowerPile.csppId}</textarea>
		 		<span>
		 	
			 ${csPowerPile.csppId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csppId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csppHost和after$csppHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csppHost==true">
	${before$csppHost}
	<dl class="csppHost " major  ref="csppHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csppHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csPowerPile$csppHost!=null">${csPowerPile$csppHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csppHost" name="csPowerPile.csppHost">
		 				<option selected value="${csPowerPile.csppHost}">
		 					${get:SrvHost(csPowerPile.csppHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csppHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csppHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csppHost">${csPowerPile.csppHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csPowerPile.csppHost}',{ctrl:{editable:false,visible:true}})">
			 ${csPowerPile.csppHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csppHost}
	</s:if>
	
	${lz:set("注释","*****************标识码字段的输入框代码*****************")}
	${lz:set("注释","before$csppNo和after$csppNo变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csppNo==true">
	${before$csppNo}
	<dl class="csppNo " major  ref="csppNo" >
		<dt>标&nbsp;&nbsp;识&nbsp;&nbsp;码:</dt>
		<s:if test="#request.CTRL.e.csppNo==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csPowerPile$csppNo!=null">${csPowerPile$csppNo}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csPowerPile.csppNo" id="csppNo"  value="${csPowerPile.csppNo}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入充电桩的标识码</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****标识码字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csppNo">${csPowerPile.csppNo}</textarea>
		 		<span>
		 	
			 ${csPowerPile.csppNo$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csppNo}
	</s:if>
	
	${lz:set("注释","*****************序号字段的输入框代码*****************")}
	${lz:set("注释","before$csppIndex和after$csppIndex变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csppIndex==true">
	${before$csppIndex}
	<dl class="csppIndex " major  ref="csppIndex" >
		<dt>序　　号:</dt>
		<s:if test="#request.CTRL.e.csppIndex==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csPowerPile$csppIndex!=null">${csPowerPile$csppIndex}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csPowerPile.csppIndex" id="csppIndex"  value="${csPowerPile.csppIndex}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>标识所在网点的序号</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****序号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csppIndex">${csPowerPile.csppIndex}</textarea>
		 		<span>
		 	
			 ${csPowerPile.csppIndex$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csppIndex}
	</s:if>
	
	${lz:set("注释","*****************所在网点字段的输入框代码*****************")}
	${lz:set("注释","before$csppOutlets和after$csppOutlets变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csppOutlets==true">
	${before$csppOutlets}
	<dl class="csppOutlets " major  ref="csppOutlets" >
		<dt>所在网点:</dt>
		<s:if test="#request.CTRL.e.csppOutlets==true">
		${lz:set("haveEditable",true)}
		<dd input="tree">
		<s:if test="#request.csPowerPile$csppOutlets!=null">${csPowerPile$csppOutlets}</s:if><s:else>
		  			<select class="combox narrow"  id="csppOutlets" name="csPowerPile.csppOutlets" action="${basePath}${proname}/object/outlets_tree.do?parent={param}&csoHost={csppHost}">
						<option value="${csPowerPile.csppOutlets}">
								${get:CsOutlets(csPowerPile.csppOutlets).csoName}
						</option>
					</select>			  		
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择充电桩的所在网点</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所在网点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csppOutlets">${csPowerPile.csppOutlets}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${csPowerPile.csppOutlets}',{ctrl:{editable:false,visible:true}})">
			 ${csPowerPile.csppOutlets$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csppOutlets}
	</s:if>
	
	${lz:set("注释","*****************正在充电字段的输入框代码*****************")}
	${lz:set("注释","before$csppCharge和after$csppCharge变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csppCharge==true">
	${before$csppCharge}
	<dl class="csppCharge " major  ref="csppCharge" >
		<dt>正在充电:</dt>
		<s:if test="#request.CTRL.e.csppCharge==true">
		${lz:set("haveEditable",true)}
		<dd input="radio">
		<s:if test="#request.csPowerPile$csppCharge!=null">${csPowerPile$csppCharge}</s:if><s:else>
		 	<div class='narrow input'>
		 	&nbsp;
	 	    <label><input type="radio" value="true" name="csPowerPile.csppCharge" id="csppCharge" ${csPowerPile.csppCharge==true?"checked":""}/>是&nbsp;&nbsp;&nbsp;</label>
			<label><input type="radio" value="false" name="csPowerPile.csppCharge" id="csppCharge" ${csPowerPile==null || csPowerPile.csppCharge==null || csPowerPile.csppCharge==false?"checked":""}/>否</label>
		 	</div>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择充电桩的正在充电</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****正在充电字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csppCharge">${csPowerPile.csppCharge}</textarea>
		 		<span>
		 	
			 ${csPowerPile.csppCharge$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csppCharge}
	</s:if>
	
	${lz:set("注释","*****************负责人字段的输入框代码*****************")}
	${lz:set("注释","before$csppPerson和after$csppPerson变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csppPerson==true">
	${before$csppPerson}
	<dl class="csppPerson " major  ref="csppPerson" >
		<dt>负&nbsp;&nbsp;责&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csppPerson==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csPowerPile$csppPerson!=null">${csPowerPile$csppPerson}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csPowerPile.csppPerson" id="csppPerson"  value="${csPowerPile.csppPerson}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入充电桩的负责人</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****负责人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csppPerson">${csPowerPile.csppPerson}</textarea>
		 		<span>
		 	
			 ${csPowerPile.csppPerson$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csppPerson}
	</s:if>
	
	${lz:set("注释","*****************联系电话字段的输入框代码*****************")}
	${lz:set("注释","before$csppTel和after$csppTel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csppTel==true">
	${before$csppTel}
	<dl class="csppTel " major  ref="csppTel" >
		<dt>联系电话:</dt>
		<s:if test="#request.CTRL.e.csppTel==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csPowerPile$csppTel!=null">${csPowerPile$csppTel}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csPowerPile.csppTel" id="csppTel"  value="${csPowerPile.csppTel}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入充电桩的联系电话</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****联系电话字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csppTel">${csPowerPile.csppTel}</textarea>
		 		<span>
		 	
			 ${csPowerPile.csppTel$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csppTel}
	</s:if>
	
	${lz:set("注释","*****************电桩图片字段的输入框代码*****************")}
	${lz:set("注释","before$csppImage和after$csppImage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csppImage==true">
	${before$csppImage}
	<dl class="csppImage " minor  ref="csppImage" style="width:98%;">
		<dt>电桩图片:</dt>
		<s:if test="#request.CTRL.e.csppImage==true">
		${lz:set("haveEditable",true)}
		<dd input="image">
		<s:if test="#request.csPowerPile$csppImage!=null">${csPowerPile$csppImage}</s:if><s:else>
		    <input type="text" class="input" maxlength="128" size="32" name="csPowerPile.csppImage" id="csppImage"  value="${csPowerPile.csppImage}"/>
			<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#csppImage').val(url)}})" class="button">设置图片</button>
			<button type="button" onclick="$('#csppImage').val('')" class="button">删除图片</button>
			<button type="button" onclick="$.thumb({url:$('#csppImage').val()})" class="button">查看图片</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传充电桩的电桩图片</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****电桩图片字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csppImage">${csPowerPile.csppImage}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(csPowerPile.csppImage))}
		 	<s:if test="#request.isVacant==false">
		 	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="${csPowerPile.csppImage}"/>
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csppImage}
	</s:if>
	
	${lz:set("注释","*****************位置信息字段的输入框代码*****************")}
	${lz:set("注释","before$csppLocation和after$csppLocation变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csppLocation==true">
	${before$csppLocation}
	<dl class="csppLocation " major  ref="csppLocation" style="width:98%;">
		<dt>位置信息:</dt>
		<s:if test="#request.CTRL.e.csppLocation==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csPowerPile$csppLocation!=null">${csPowerPile$csppLocation}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="64" name="csPowerPile.csppLocation" id="csppLocation"  value="${csPowerPile.csppLocation}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入充电桩的位置信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****位置信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csppLocation">${csPowerPile.csppLocation}</textarea>
		 		<span>
		 	
			 ${csPowerPile.csppLocation$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csppLocation}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$csppRemark和after$csppRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csppRemark==true">
	${before$csppRemark}
	<dl class="csppRemark " minor  ref="csppRemark" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.csppRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csPowerPile$csppRemark!=null">${csPowerPile$csppRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csppRemark" name="csPowerPile.csppRemark" rows="5">${csPowerPile.csppRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入充电桩的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csPowerPile.csppRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csppRemark}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csppUpdateTime和after$csppUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csppUpdateTime==true">
	${before$csppUpdateTime}
	<dl class="csppUpdateTime " major  ref="csppUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csppUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csPowerPile$csppUpdateTime!=null">${csPowerPile$csppUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csPowerPile.csppUpdateTime" id="csppUpdateTime"  value="<s:date name="csPowerPile.csppUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择充电桩的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csppUpdateTime">${csPowerPile.csppUpdateTime}</textarea>
		 		<span>
		 	
			 ${csPowerPile.csppUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csppUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csppAddTime和after$csppAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csppAddTime==true">
	${before$csppAddTime}
	<dl class="csppAddTime " major  ref="csppAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csppAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csPowerPile$csppAddTime!=null">${csPowerPile$csppAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csPowerPile.csppAddTime" id="csppAddTime"  value="<s:date name="csPowerPile.csppAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择充电桩的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csppAddTime">${csPowerPile.csppAddTime}</textarea>
		 		<span>
		 	
			 ${csPowerPile.csppAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csppAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csppStatus和after$csppStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csppStatus==true">
	${before$csppStatus}
	<dl class="csppStatus " major  ref="csppStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csppStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csPowerPile$csppStatus!=null">${csPowerPile$csppStatus}</s:if><s:else>
		 	<select class="narrow" id="csppStatus" name="csPowerPile.csppStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csPowerPile.csppStatus==1?"selected":""}>正常</option>
				<option value="2" ${csPowerPile.csppStatus==2?"selected":""}>预设</option>
				<option value="3" ${csPowerPile.csppStatus==3?"selected":""}>已坏</option>
				<option value="0" ${csPowerPile.csppStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择充电桩的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csppStatus">${csPowerPile.csppStatus}</textarea>
		 		<span>
		 	
			 ${csPowerPile.csppStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csppStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csPowerPile.csppId}" name="csPowerPile.csppId" id="csppId" />
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