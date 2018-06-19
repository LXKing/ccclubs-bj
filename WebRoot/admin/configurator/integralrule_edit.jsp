<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/configurator/integralrule.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/configurator/integralrule.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csIntegralRule.csirId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csirId,csirIntegral",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csirId,csirName,csirFlag,csirDepict,csirIntegral,csirStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csirId,csirIntegral,csirStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csirId,csirName,csirFlag,csirDepict,csirIntegral,csirStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"积分规则编辑":CTRL.title}</title>
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
	$("#integralruleForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csirId==true">
		${lz:set("haveEditable",true)}
		,"csIntegralRule.csirId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csirName==true">
		${lz:set("haveEditable",true)}
		,"csIntegralRule.csirName":function(el){
			if(jQuery.trim(el.value)=="")
				return "规则名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "规则名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csirFlag==true">
		${lz:set("haveEditable",true)}
		,"csIntegralRule.csirFlag":function(el){
			if(jQuery.trim(el.value)=="")
				return "标识不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "标识最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csirDepict==true">
		${lz:set("haveEditable",true)}
		,"csIntegralRule.csirDepict":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "规则描述最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csirIntegral==true">
		${lz:set("haveEditable",true)}
		,"csIntegralRule.csirIntegral":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "变动分数输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csirUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csIntegralRule.csirUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csirAddTime==true">
		${lz:set("haveEditable",true)}
		,"csIntegralRule.csirAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csirStatus==true">
		${lz:set("haveEditable",true)}
		,"csIntegralRule.csirStatus":function(el){
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
	<form class="form editform" ref="CsIntegralRule" id="integralruleForm" name="integralruleForm" action="${empty CTRL.action?"integralrule_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写积分规则相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csIntegralRuleToken}" name="csIntegralRuleToken" id="csIntegralRuleToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csIntegralRule.csirId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csirId和after$csirId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csirId==true">
	${before$csirId}
	<dl class="csirId ${CTRL.e.csirId?"hidden":""}" major  ref="csirId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csirId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csIntegralRule$csirId!=null">${csIntegralRule$csirId}</s:if><s:else>
			<input type="hidden" value="${csIntegralRule.csirId}" name="csIntegralRule.csirId" id="csirId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csirId">${csIntegralRule.csirId}</textarea>
		 		<span>
		 	
			 ${csIntegralRule.csirId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csirId}
	</s:if>
	
	${lz:set("注释","*****************规则名称字段的输入框代码*****************")}
	${lz:set("注释","before$csirName和after$csirName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csirName==true">
	${before$csirName}
	<dl class="csirName " major  ref="csirName" >
		<dt>规则名称:</dt>
		<s:if test="#request.CTRL.e.csirName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csIntegralRule$csirName!=null">${csIntegralRule$csirName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csIntegralRule.csirName" id="csirName"  value="${csIntegralRule.csirName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入积分规则的规则名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****规则名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csirName">${csIntegralRule.csirName}</textarea>
		 		<span>
		 	
			 ${csIntegralRule.csirName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csirName}
	</s:if>
	
	${lz:set("注释","*****************标识字段的输入框代码*****************")}
	${lz:set("注释","before$csirFlag和after$csirFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csirFlag==true">
	${before$csirFlag}
	<dl class="csirFlag " major  ref="csirFlag" >
		<dt>标　　识:</dt>
		<s:if test="#request.CTRL.e.csirFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csIntegralRule$csirFlag!=null">${csIntegralRule$csirFlag}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csIntegralRule.csirFlag" id="csirFlag"  value="${csIntegralRule.csirFlag}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入积分规则的标识</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csirFlag">${csIntegralRule.csirFlag}</textarea>
		 		<span>
		 	
			 ${csIntegralRule.csirFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csirFlag}
	</s:if>
	
	${lz:set("注释","*****************规则描述字段的输入框代码*****************")}
	${lz:set("注释","before$csirDepict和after$csirDepict变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csirDepict==true">
	${before$csirDepict}
	<dl class="csirDepict " major  ref="csirDepict" style="width:98%;">
		<dt>规则描述:</dt>
		<s:if test="#request.CTRL.e.csirDepict==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csIntegralRule$csirDepict!=null">${csIntegralRule$csirDepict}</s:if><s:else>
		 	<textarea class="input wide"  id="csirDepict" name="csIntegralRule.csirDepict" rows="5">${csIntegralRule.csirDepict}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入积分规则的规则描述</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****规则描述字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csIntegralRule.csirDepict$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csirDepict}
	</s:if>
	
	${lz:set("注释","*****************变动分数字段的输入框代码*****************")}
	${lz:set("注释","before$csirIntegral和after$csirIntegral变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csirIntegral==true">
	${before$csirIntegral}
	<dl class="csirIntegral " major  ref="csirIntegral" >
		<dt>变动分数:</dt>
		<s:if test="#request.CTRL.e.csirIntegral==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csIntegralRule$csirIntegral!=null">${csIntegralRule$csirIntegral}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csIntegralRule.csirIntegral" id="csirIntegral"  value="${csIntegralRule.csirIntegral}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入积分规则的变动分数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****变动分数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csirIntegral">${csIntegralRule.csirIntegral}</textarea>
		 		<span>
		 	
			 ${csIntegralRule.csirIntegral$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csirIntegral}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csirUpdateTime和after$csirUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csirUpdateTime==true">
	${before$csirUpdateTime}
	<dl class="csirUpdateTime " major  ref="csirUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csirUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csIntegralRule$csirUpdateTime!=null">${csIntegralRule$csirUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csIntegralRule.csirUpdateTime" id="csirUpdateTime"  value="<s:date name="csIntegralRule.csirUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择积分规则的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csirUpdateTime">${csIntegralRule.csirUpdateTime}</textarea>
		 		<span>
		 	
			 ${csIntegralRule.csirUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csirUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csirAddTime和after$csirAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csirAddTime==true">
	${before$csirAddTime}
	<dl class="csirAddTime " major  ref="csirAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csirAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csIntegralRule$csirAddTime!=null">${csIntegralRule$csirAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csIntegralRule.csirAddTime" id="csirAddTime"  value="<s:date name="csIntegralRule.csirAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择积分规则的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csirAddTime">${csIntegralRule.csirAddTime}</textarea>
		 		<span>
		 	
			 ${csIntegralRule.csirAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csirAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csirStatus和after$csirStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csirStatus==true">
	${before$csirStatus}
	<dl class="csirStatus " major  ref="csirStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csirStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csIntegralRule$csirStatus!=null">${csIntegralRule$csirStatus}</s:if><s:else>
		 	<select class="narrow" id="csirStatus" name="csIntegralRule.csirStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csIntegralRule.csirStatus==1?"selected":""}>正常</option>
				<option value="0" ${csIntegralRule.csirStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择积分规则的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csirStatus">${csIntegralRule.csirStatus}</textarea>
		 		<span>
		 	
			 ${csIntegralRule.csirStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csirStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csIntegralRule.csirId}" name="csIntegralRule.csirId" id="csirId" />
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