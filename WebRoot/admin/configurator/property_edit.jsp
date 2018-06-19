<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/configurator/property.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/configurator/property.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty srvProperty.spId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"spId,spName,spParent,spFlag,spNumber,spValue,spText,spStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"spId,spName,spParent,spFlag,spNumber,spValue,spText,spStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"spId,spName,spParent,spFlag,spNumber,spValue,spText,spStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"spId,spName,spParent,spFlag,spNumber,spValue,spText,spStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"系统属性编辑":CTRL.title}</title>
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
<script>
//如果一级属性的文本字段配置了编辑表格格式化信息，则自动渲染为表格
$(function(){
	if(!${empty parentSrvProperty.spText}){
		$("#spText").dynamic(${parentSrvProperty.spText});
	}else if(${parentSrvProperty==null}){
		if(${ccclubs_login.suId==0}){
			var div = $("<div></div>").insertBefore($("#spText").hide()).width(620).css("float","left");
			var grid=div.grid({
				column:[
					["名称",80,"text","title",null],
					["预留",80,"hidden","reserve",null],
					["类型",70,"select","input",[{"value":"number","text":"数字","depict":"","size":8},{"value":"money","text":"小数","depict":"","size":8},{"value":"text","text":"短文本","depict":"","size":32},{"value":"textarea","text":"长文本","depict":"","size":200},{"value":"html","text":"HTML","depict":"","size":65535},{"value":"datetime","text":"日期时间","depict":"","size":19},{"value":"date","text":"日期","depict":"","size":19},{"value":"mobile","text":"手机号码","depict":"","size":32},{"value":"url","text":"网址","depict":"","size":128},{"value":"email","text":"邮箱地址","depict":"","size":128},{"value":"password","text":"密码","depict":"","size":32},{"value":"select","text":"下拉框","depict":"","size":4},{"value":"relate","text":"关联对象","depict":"","size":8},{"value":"checkbox","text":"多选框","depict":"","size":256},{"value":"radio","text":"单选框","depict":"","size":4},{"value":"image","text":"图片","depict":"","size":128},{"value":"imagelist","text":"图片列表","depict":"","size":1024},{"value":"file","text":"文件","depict":"","size":128},{"value":"filelist","text":"文件列表","depict":"","size":1024}]],
					["字段",80,"text","name"],
					["列表项",100,"grid","options",[{"text":"项名称","value":"text"},{"text":"值","value":"value"}]],
					["默认值",50,"text","value",null]
				],
				change:function(input){
					$("#spText").val(grid.getValue(1));
				}
			});
			if(!${empty srvProperty.spText})
				grid.setValue(${lz:or(srvProperty.spText,"[]")});
		}else{
			$("#spText").parents("dd:eq(0)").html($("#spText").val());
		}
	}
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
	$("#propertyForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.spId==true">
		${lz:set("haveEditable",true)}
		,"srvProperty.spId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.spName==true">
		${lz:set("haveEditable",true)}
		,"srvProperty.spName":function(el){
			if(jQuery.trim(el.value)=="")
				return "名称不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.spParent==true">
		${lz:set("haveEditable",true)}
		,"srvProperty.spParent":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.spFlag==true">
		${lz:set("haveEditable",true)}
		,"srvProperty.spFlag":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "标识最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.spNumber==true">
		${lz:set("haveEditable",true)}
		,"srvProperty.spNumber":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.spValue==true">
		${lz:set("haveEditable",true)}
		,"srvProperty.spValue":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "串值最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.spText==true">
		${lz:set("haveEditable",true)}
		,"srvProperty.spText":function(el){
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "文本最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.spStatus==true">
		${lz:set("haveEditable",true)}
		,"srvProperty.spStatus":function(el){
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
	<form class="form editform" ref="SrvProperty" id="propertyForm" name="propertyForm" action="${empty CTRL.action?"property_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写系统属性相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${srvPropertyToken}" name="srvPropertyToken" id="srvPropertyToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty srvProperty.spId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$spId和after$spId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.spId==true">
	${before$spId}
	<dl class="spId ${CTRL.e.spId?"hidden":""}" major  ref="spId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.spId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.srvProperty$spId!=null">${srvProperty$spId}</s:if><s:else>
			<input type="hidden" value="${srvProperty.spId}" name="srvProperty.spId" id="spId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="spId">${srvProperty.spId}</textarea>
		 		<span>
		 	
			 ${srvProperty.spId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$spId}
	</s:if>
	
	${lz:set("注释","*****************名称字段的输入框代码*****************")}
	${lz:set("注释","before$spName和after$spName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.spName==true">
	${before$spName}
	<dl class="spName " major  ref="spName" >
		<dt>名　　称:</dt>
		<s:if test="#request.CTRL.e.spName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvProperty$spName!=null">${srvProperty$spName}</s:if><s:else>
		 	<input type="text" class="input narrow"   name="srvProperty.spName" id="spName"  value="${srvProperty.spName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>属性名称，如html中select的option的text内容</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="spName">${srvProperty.spName}</textarea>
		 		<span>
		 	
			 ${srvProperty.spName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$spName}
	</s:if>
	
	${lz:set("注释","*****************类型字段的输入框代码*****************")}
	${lz:set("注释","before$spParent和after$spParent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.spParent==true">
	${before$spParent}
	<dl class="spParent " major  ref="spParent" >
		<dt>类　　型:</dt>
		<s:if test="#request.CTRL.e.spParent==true">
		${lz:set("haveEditable",true)}
		<dd input="label">
		<s:if test="#request.srvProperty$spParent!=null">${srvProperty$spParent}</s:if><s:else>
			<input type="hidden" name="srvProperty.spParent" id="srvProperty.spParent"  value="${parentSrvProperty==null ? "0" : parentSrvProperty.spId}"/>
			${parentSrvProperty==null ? "顶级节点" : parentSrvProperty.spName$}
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="spParent">${srvProperty.spParent}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${srvProperty.spParent}',{ctrl:{editable:false,visible:true}})">
			 ${srvProperty.spParent$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$spParent}
	</s:if>
	
	${lz:set("注释","*****************标识字段的输入框代码*****************")}
	${lz:set("注释","before$spFlag和after$spFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.spFlag==true">
	${before$spFlag}
	<dl class="spFlag " major  ref="spFlag" >
		<dt>标　　识:</dt>
		<s:if test="#request.CTRL.e.spFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvProperty$spFlag!=null">${srvProperty$spFlag}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="srvProperty.spFlag" id="spFlag"  value="${srvProperty.spFlag}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>程序变量命名规则格式</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="spFlag">${srvProperty.spFlag}</textarea>
		 		<span>
		 	
			 ${srvProperty.spFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$spFlag}
	</s:if>
	
	${lz:set("注释","*****************数值字段的输入框代码*****************")}
	${lz:set("注释","before$spNumber和after$spNumber变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.spNumber==true">
	${before$spNumber}
	<dl class="spNumber " minor  ref="spNumber" >
		<dt>数　　值:</dt>
		<s:if test="#request.CTRL.e.spNumber==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.srvProperty$spNumber!=null">${srvProperty$spNumber}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="srvProperty.spNumber" id="spNumber"  value="${srvProperty.spNumber}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统属性的数值</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****数值字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="spNumber">${srvProperty.spNumber}</textarea>
		 		<span>
		 	
			 ${srvProperty.spNumber$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$spNumber}
	</s:if>
	
	${lz:set("注释","*****************串值字段的输入框代码*****************")}
	${lz:set("注释","before$spValue和after$spValue变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.spValue==true">
	${before$spValue}
	<dl class="spValue " minor  ref="spValue" style="width:98%;">
		<dt>串　　值:</dt>
		<s:if test="#request.CTRL.e.spValue==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.srvProperty$spValue!=null">${srvProperty$spValue}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="256" name="srvProperty.spValue" id="spValue"  value="${srvProperty.spValue}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统属性的串值</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****串值字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="spValue">${srvProperty.spValue}</textarea>
		 		<span>
		 	
			 ${srvProperty.spValue$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$spValue}
	</s:if>
	
	${lz:set("注释","*****************文本字段的输入框代码*****************")}
	${lz:set("注释","before$spText和after$spText变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.spText==true">
	${before$spText}
	<dl class="spText " minor  ref="spText" style="width:98%;">
		<dt>文　　本:</dt>
		<s:if test="#request.CTRL.e.spText==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.srvProperty$spText!=null">${srvProperty$spText}</s:if><s:else>
		 	<textarea class="input wide"  id="spText" name="srvProperty.spText" rows="5">${srvProperty.spText}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入系统属性的文本</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****文本字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${srvProperty.spText$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$spText}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$spStatus和after$spStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.spStatus==true">
	${before$spStatus}
	<dl class="spStatus " major  ref="spStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.spStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.srvProperty$spStatus!=null">${srvProperty$spStatus}</s:if><s:else>
		 	<select class="narrow" id="spStatus" name="srvProperty.spStatus">
		 		<option value="">请选择</option>
				<option value="1" ${srvProperty.spStatus==1?"selected":""}>有效</option>
				<option value="0" ${srvProperty.spStatus==0?"selected":""}>内容</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择系统属性的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="spStatus">${srvProperty.spStatus}</textarea>
		 		<span>
		 	
			 ${srvProperty.spStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$spStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${srvProperty.spId}" name="srvProperty.spId" id="spId" />
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