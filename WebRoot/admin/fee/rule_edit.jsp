<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/fee/rule.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/fee/rule.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csRule.csrId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csrId,csrName,csrGoods,csrExpression,csrPriority,csrProfile,csrStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csrId,csrName,csrGoods,csrExpression,csrPriority,csrMeas,csrProfile,csrStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csrId,csrGoods,csrExpression,csrPriority,csrProfile,csrStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csrId,csrName,csrGoods,csrExpression,csrPriority,csrMeas,csrProfile,csrStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"规则编辑":CTRL.title}</title>
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
	$("#ruleForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csrId==true">
		${lz:set("haveEditable",true)}
		,"csRule.csrId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrName==true">
		${lz:set("haveEditable",true)}
		,"csRule.csrName":function(el){
			if(jQuery.trim(el.value)=="")
				return "规则名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "规则名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrExpression==true">
		${lz:set("haveEditable",true)}
		,"csRule.csrExpression":function(el){
			if(jQuery.trim(el.value)=="")
				return "规则表达式不能为空";
			if(el.value.length>64 && el.value.indexOf("[*]")==-1)
				return "规则表达式最大长度为64个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrPriority==true">
		${lz:set("haveEditable",true)}
		,"csRule.csrPriority":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrMeas==true">
		${lz:set("haveEditable",true)}
		,"csRule.csrMeas":function(el){
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "计量表达式最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrProfile==true">
		${lz:set("haveEditable",true)}
		,"csRule.csrProfile":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "规则描述最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csrStatus==true">
		${lz:set("haveEditable",true)}
		,"csRule.csrStatus":function(el){
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
	<form class="form editform" ref="CsRule" id="ruleForm" name="ruleForm" action="${empty CTRL.action?"rule_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写规则相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csRuleToken}" name="csRuleToken" id="csRuleToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csRule.csrId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csrId和after$csrId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrId==true">
	${before$csrId}
	<dl class="csrId ${CTRL.e.csrId?"hidden":""}" major  ref="csrId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csrId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csRule$csrId!=null">${csRule$csrId}</s:if><s:else>
			<input type="hidden" value="${csRule.csrId}" name="csRule.csrId" id="csrId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrId">${csRule.csrId}</textarea>
		 		<span>
		 	
			 ${csRule.csrId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrId}
	</s:if>
	
	${lz:set("注释","*****************规则名称字段的输入框代码*****************")}
	${lz:set("注释","before$csrName和after$csrName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrName==true">
	${before$csrName}
	<dl class="csrName " major  ref="csrName" >
		<dt>规则名称:</dt>
		<s:if test="#request.CTRL.e.csrName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csRule$csrName!=null">${csRule$csrName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csRule.csrName" id="csrName"  value="${csRule.csrName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入规则的规则名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****规则名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrName">${csRule.csrName}</textarea>
		 		<span>
		 	
			 ${csRule.csrName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrName}
	</s:if>
	
	${lz:set("注释","*****************商品列表字段的输入框代码*****************")}
	${lz:set("注释","before$csrGoods和after$csrGoods变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrGoods==true">
	${before$csrGoods}
	<dl class="csrGoods " minor  ref="csrGoods" style="width:98%;">
		<dt>商品列表:</dt>
		<s:if test="#request.CTRL.e.csrGoods==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csRule$csrGoods!=null">${csRule$csrGoods}</s:if><s:else>
		  		<s:if test="csRule.csrId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csrGoods" name="csrGoods" frameborder='0' src="" url="${basePath}${proname}/fee/goods.do?inFrame=true&canQuery=false&fields=csgName,csgProduct,csgUserType,csgStatus&csgRule=${csRule.csrId}&ctrl={queryable:false,fields:{csgRule:{visible:true,editable:false,defaultValue:${csRule.csrId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csrGoods').attr('src',$('#csrGoods').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑商品列表
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****商品列表字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csRule.csrId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="csrGoods" name="csrGoods" frameborder='0' src="" url="${basePath}${proname}/fee/goods.do?inFrame=true&canQuery=false&fields=csgName,csgProduct,csgUserType,csgStatus&csgRule=${csRule.csrId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#csrGoods').attr('src',$('#csrGoods').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$csrGoods}
	</s:if>
	
	${lz:set("注释","*****************规则表达式字段的输入框代码*****************")}
	${lz:set("注释","before$csrExpression和after$csrExpression变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrExpression==true">
	${before$csrExpression}
	<dl class="csrExpression " major  ref="csrExpression" style="width:98%;">
		<dt>规则表达式:</dt>
		<s:if test="#request.CTRL.e.csrExpression==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csRule$csrExpression!=null">${csRule$csrExpression}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="64" name="csRule.csrExpression" id="csrExpression"  value="${csRule.csrExpression}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>[(时间属性)]{min,max}  A-X表示24个小时 w工作日 c双休日 h节假日</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****规则表达式字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csrExpression">${csRule.csrExpression}</textarea>
		 		<span>
		 	
			 ${csRule.csrExpression$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrExpression}
	</s:if>
	
	${lz:set("注释","*****************优先级字段的输入框代码*****************")}
	${lz:set("注释","before$csrPriority和after$csrPriority变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrPriority==true">
	${before$csrPriority}
	<dl class="csrPriority " major  ref="csrPriority" >
		<dt>优&nbsp;&nbsp;先&nbsp;&nbsp;级:</dt>
		<s:if test="#request.CTRL.e.csrPriority==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csRule$csrPriority!=null">${csRule$csrPriority}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csRule.csrPriority" id="csrPriority"  value="${csRule.csrPriority}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入规则的优先级</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****优先级字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrPriority">${csRule.csrPriority}</textarea>
		 		<span>
		 	
			 ${csRule.csrPriority$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrPriority}
	</s:if>
	
	${lz:set("注释","*****************计量表达式字段的输入框代码*****************")}
	${lz:set("注释","before$csrMeas和after$csrMeas变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrMeas==true">
	${before$csrMeas}
	<dl class="csrMeas " major  ref="csrMeas" style="width:98%;">
		<dt>计量表达式:</dt>
		<s:if test="#request.CTRL.e.csrMeas==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csRule$csrMeas!=null">${csRule$csrMeas}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csRule.csrMeas" id="csrMeas"  value="${csRule.csrMeas}"/>
	 	 </s:else>
	 	 算式
	 	 
	 	 <em>{M}:当前分钟 {H}当前小时 {K}当前公里 +加 -减 *乘 /除</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****计量表达式字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csrMeas">${csRule.csrMeas}</textarea>
		 		<span>
		 	
			 ${csRule.csrMeas$}
	 	 算式 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrMeas}
	</s:if>
	
	${lz:set("注释","*****************规则描述字段的输入框代码*****************")}
	${lz:set("注释","before$csrProfile和after$csrProfile变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrProfile==true">
	${before$csrProfile}
	<dl class="csrProfile " minor  ref="csrProfile" style="width:98%;">
		<dt>规则描述:</dt>
		<s:if test="#request.CTRL.e.csrProfile==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csRule$csrProfile!=null">${csRule$csrProfile}</s:if><s:else>
		 	<textarea class="input wide"  id="csrProfile" name="csRule.csrProfile" rows="5">${csRule.csrProfile}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入规则的规则描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****规则描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csRule.csrProfile$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrProfile}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csrStatus和after$csrStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csrStatus==true">
	${before$csrStatus}
	<dl class="csrStatus " major  ref="csrStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csrStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csRule$csrStatus!=null">${csRule$csrStatus}</s:if><s:else>
		 	<select class="narrow" id="csrStatus" name="csRule.csrStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csRule.csrStatus==1?"selected":""}>有效</option>
				<option value="0" ${csRule.csrStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择规则的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csrStatus">${csRule.csrStatus}</textarea>
		 		<span>
		 	
			 ${csRule.csrStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csrStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csRule.csrId}" name="csRule.csrId" id="csrId" />
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