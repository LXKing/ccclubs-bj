<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/operate/activitys/activity/promotion.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/operate/activitys/activity/promotion.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csPromotion.cspId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cspId,cspHost,cspName,cspActivity,cspType,cspRecharge,cspConsume,cspPrice,cspPackage,cspItems,cspPresents,cspStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cspId,cspHost,cspName,cspActivity,cspType,cspRecharge,cspConsume,cspPrice,cspPackage,cspItems,cspPresents,cspStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cspId,cspHost,cspName,cspActivity,cspType,cspRecharge,cspConsume,cspPrice,cspPackage,cspItems,cspPresents,cspStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cspId,cspHost,cspName,cspActivity,cspType,cspRecharge,cspConsume,cspPrice,cspPackage,cspItems,cspPresents,cspStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"促销配置编辑":CTRL.title}</title>
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
	$("#promotionForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cspId==true">
		${lz:set("haveEditable",true)}
		,"csPromotion.cspId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspHost==true">
		${lz:set("haveEditable",true)}
		,"csPromotion.cspHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspName==true">
		${lz:set("haveEditable",true)}
		,"csPromotion.cspName":function(el){
			if(jQuery.trim(el.value)=="")
				return "促销名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "促销名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspActivity==true">
		${lz:set("haveEditable",true)}
		,"csPromotion.cspActivity":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspType==true">
		${lz:set("haveEditable",true)}
		,"csPromotion.cspType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择促销类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspRecharge==true">
		${lz:set("haveEditable",true)}
		,"csPromotion.cspRecharge":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "充值金额输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspConsume==true">
		${lz:set("haveEditable",true)}
		,"csPromotion.cspConsume":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "消费额度输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspPrice==true">
		${lz:set("haveEditable",true)}
		,"csPromotion.cspPrice":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "销售价格输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspPackage==true">
		${lz:set("haveEditable",true)}
		,"csPromotion.cspPackage":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspItems==true">
		${lz:set("haveEditable",true)}
		,"csPromotion.cspItems":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspPresents==true">
		${lz:set("haveEditable",true)}
		,"csPromotion.cspPresents":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csPromotion.cspUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspAddTime==true">
		${lz:set("haveEditable",true)}
		,"csPromotion.cspAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cspStatus==true">
		${lz:set("haveEditable",true)}
		,"csPromotion.cspStatus":function(el){
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
	<form class="form editform" ref="CsPromotion" id="promotionForm" name="promotionForm" action="${empty CTRL.action?"promotion_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写促销配置相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csPromotionToken}" name="csPromotionToken" id="csPromotionToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csPromotion.cspId))}		
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
		<s:if test="#request.csPromotion$cspId!=null">${csPromotion$cspId}</s:if><s:else>
			<input type="hidden" value="${csPromotion.cspId}" name="csPromotion.cspId" id="cspId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspId">${csPromotion.cspId}</textarea>
		 		<span>
		 	
			 ${csPromotion.cspId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cspHost和after$cspHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspHost==true">
	${before$cspHost}
	<dl class="cspHost " major  ref="cspHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cspHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csPromotion$cspHost!=null">${csPromotion$cspHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cspHost" name="csPromotion.cspHost">
		 				<option selected value="${csPromotion.cspHost}">
		 					${get:SrvHost(csPromotion.cspHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cspHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cspHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspHost">${csPromotion.cspHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csPromotion.cspHost}',{ctrl:{editable:false,visible:true}})">
			 ${csPromotion.cspHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspHost}
	</s:if>
	
	${lz:set("注释","*****************促销名称字段的输入框代码*****************")}
	${lz:set("注释","before$cspName和after$cspName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspName==true">
	${before$cspName}
	<dl class="cspName " major  ref="cspName" >
		<dt>促销名称:</dt>
		<s:if test="#request.CTRL.e.cspName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csPromotion$cspName!=null">${csPromotion$cspName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csPromotion.cspName" id="cspName"  value="${csPromotion.cspName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入促销配置的促销名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****促销名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspName">${csPromotion.cspName}</textarea>
		 		<span>
		 	
			 ${csPromotion.cspName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspName}
	</s:if>
	
	${lz:set("注释","*****************所属活动字段的输入框代码*****************")}
	${lz:set("注释","before$cspActivity和after$cspActivity变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspActivity==true">
	${before$cspActivity}
	<dl class="cspActivity " major  ref="cspActivity" >
		<dt>所属活动:</dt>
		<s:if test="#request.CTRL.e.cspActivity==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csPromotion$cspActivity!=null">${csPromotion$cspActivity}</s:if><s:else>
		 			<input title="请输入营销活动活动主题进行查询" class="combox narrow" action="${basePath}${proname}/operate/activitys/activity_query.do?value={param}&csaHost={cspHost}" type="text" id="cspActivity" name="csPromotion.cspActivity" text="${get:CsActivity(csPromotion.cspActivity).csaTitle}" value="${csPromotion.cspActivity}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cspActivity').val())==''){return;};window.href('${basePath}${proname}/operate/activitys/activity_details.do?key='+$('#cspActivity').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属活动字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspActivity">${csPromotion.cspActivity}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/operate/activitys/activity_details.do?key=${csPromotion.cspActivity}',{ctrl:{editable:false,visible:true}})">
			 ${csPromotion.cspActivity$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspActivity}
	</s:if>
	
	${lz:set("注释","*****************促销类型字段的输入框代码*****************")}
	${lz:set("注释","before$cspType和after$cspType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspType==true">
	${before$cspType}
	<dl class="cspType " major  ref="cspType" >
		<dt>促销类型:</dt>
		<s:if test="#request.CTRL.e.cspType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csPromotion$cspType!=null">${csPromotion$cspType}</s:if><s:else>
		 	<select class="narrow" id="cspType" name="csPromotion.cspType">
		 		<option value="">请选择</option>
				<option value="0" ${csPromotion.cspType==0?"selected":""}>充值类</option>
				<option value="1" ${csPromotion.cspType==1?"selected":""}>购买类</option>
				<option value="2" ${csPromotion.cspType==2?"selected":""}>消费类</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择促销配置的促销类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****促销类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspType">${csPromotion.cspType}</textarea>
		 		<span>
		 	
			 ${csPromotion.cspType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspType}
	</s:if>
	
	${lz:set("注释","*****************充值金额字段的输入框代码*****************")}
	${lz:set("注释","before$cspRecharge和after$cspRecharge变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspRecharge==true">
	${before$cspRecharge}
	<dl class="cspRecharge " major  ref="cspRecharge" >
		<dt>充值金额:</dt>
		<s:if test="#request.CTRL.e.cspRecharge==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csPromotion$cspRecharge!=null">${csPromotion$cspRecharge}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csPromotion.cspRecharge" id="cspRecharge"  value="${csPromotion.cspRecharge}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入促销配置的充值金额</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****充值金额字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspRecharge">${csPromotion.cspRecharge}</textarea>
		 		<span>
		 	
			 ${csPromotion.cspRecharge$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspRecharge}
	</s:if>
	
	${lz:set("注释","*****************消费额度字段的输入框代码*****************")}
	${lz:set("注释","before$cspConsume和after$cspConsume变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspConsume==true">
	${before$cspConsume}
	<dl class="cspConsume " major  ref="cspConsume" >
		<dt>消费额度:</dt>
		<s:if test="#request.CTRL.e.cspConsume==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csPromotion$cspConsume!=null">${csPromotion$cspConsume}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csPromotion.cspConsume" id="cspConsume"  value="${csPromotion.cspConsume}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入促销配置的消费额度</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****消费额度字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspConsume">${csPromotion.cspConsume}</textarea>
		 		<span>
		 	
			 ${csPromotion.cspConsume$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspConsume}
	</s:if>
	
	${lz:set("注释","*****************销售价格字段的输入框代码*****************")}
	${lz:set("注释","before$cspPrice和after$cspPrice变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspPrice==true">
	${before$cspPrice}
	<dl class="cspPrice " major  ref="cspPrice" >
		<dt>销售价格:</dt>
		<s:if test="#request.CTRL.e.cspPrice==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csPromotion$cspPrice!=null">${csPromotion$cspPrice}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csPromotion.cspPrice" id="cspPrice"  value="${csPromotion.cspPrice}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入促销配置的销售价格</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****销售价格字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspPrice">${csPromotion.cspPrice}</textarea>
		 		<span>
		 	
			 ${csPromotion.cspPrice$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspPrice}
	</s:if>
	
	${lz:set("注释","*****************关联套餐字段的输入框代码*****************")}
	${lz:set("注释","before$cspPackage和after$cspPackage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspPackage==true">
	${before$cspPackage}
	<dl class="cspPackage " major  ref="cspPackage" >
		<dt>关联套餐:</dt>
		<s:if test="#request.CTRL.e.cspPackage==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csPromotion$cspPackage!=null">${csPromotion$cspPackage}</s:if><s:else>
		 			<input title="请输入系统套餐套餐名称进行查询" class="combox narrow" action="${basePath}${proname}/fee/pack_query.do?value={param}" type="text" id="cspPackage" name="csPromotion.cspPackage" text="${get:CsPack(csPromotion.cspPackage).cspName}" value="${csPromotion.cspPackage}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cspPackage').val())==''){return;};window.href('${basePath}${proname}/fee/pack_details.do?key='+$('#cspPackage').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联套餐字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspPackage">${csPromotion.cspPackage}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/fee/pack_details.do?key=${csPromotion.cspPackage}',{ctrl:{editable:false,visible:true}})">
			 ${csPromotion.cspPackage$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspPackage}
	</s:if>
	
	${lz:set("注释","*****************关联宝贝字段的输入框代码*****************")}
	${lz:set("注释","before$cspItems和after$cspItems变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspItems==true">
	${before$cspItems}
	<dl class="cspItems " major  ref="cspItems" style="width:98%;">
		<dt>关联宝贝:</dt>
		<s:if test="#request.CTRL.e.cspItems==true">
		${lz:set("haveEditable",true)}
		<dd input="list">
		<s:if test="#request.csPromotion$cspItems!=null">${csPromotion$cspItems}</s:if><s:else>
		 	
			    <div name="csPromotion.cspItems" id="cspItems" class="wide input listinput">
					<input type="hidden" name="csPromotion.cspItems" value="0"/>
				<s:generator val="csPromotion.cspItems" separator=",">
					<s:iterator  id="item">
					<s:if test="item!=0">
						<div>
							${get:CsItem(item).csiTitle}							
						<input type="hidden" name="csPromotion.cspItems" value="${item}"/><span onclick="$(this).parent().remove();">x</span></div>
					</s:if>
					</s:iterator>
				</s:generator>
				</div>
				<button type="button" style="float:left;" onclick="$.getInput({type:'combox',title:'商品信息商品名称',action:$(this).attr('action'),callback:function(id,text){$('#cspItems').append('<div>'+text+'<input type=&quot;hidden&quot; name=&quot;csPromotion.cspItems&quot; value=&quot;'+id+'&quot;/><span onclick=&quot;$(this).parent().remove();&quot;>x</span></div>');}});" action="select * from cs_item where 1=1?value={param}" class="button">添加</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联宝贝字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cspItems">${csPromotion.cspItems}</textarea>
		 		<span>
		 	
			 ${csPromotion.cspItems$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspItems}
	</s:if>
	
	${lz:set("注释","*****************关联赠品字段的输入框代码*****************")}
	${lz:set("注释","before$cspPresents和after$cspPresents变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cspPresents==true">
	${before$cspPresents}
	<dl class="cspPresents " major  ref="cspPresents" style="width:98%;">
		<dt>关联赠品:</dt>
		<s:if test="#request.CTRL.e.cspPresents==true">
		${lz:set("haveEditable",true)}
		<dd input="list">
		<s:if test="#request.csPromotion$cspPresents!=null">${csPromotion$cspPresents}</s:if><s:else>
		 	
			    <div name="csPromotion.cspPresents" id="cspPresents" class="wide input listinput">
					<input type="hidden" name="csPromotion.cspPresents" value="0"/>
				<s:generator val="csPromotion.cspPresents" separator=",">
					<s:iterator  id="item">
					<s:if test="item!=0">
						<div>
							${get:CsPresent(item).cspName}							
						<input type="hidden" name="csPromotion.cspPresents" value="${item}"/><span onclick="$(this).parent().remove();">x</span></div>
					</s:if>
					</s:iterator>
				</s:generator>
				</div>
				<button type="button" style="float:left;" onclick="$.getInput({type:'combox',title:'礼品赠品礼品名称',action:$(this).attr('action'),callback:function(id,text){$('#cspPresents').append('<div>'+text+'<input type=&quot;hidden&quot; name=&quot;csPromotion.cspPresents&quot; value=&quot;'+id+'&quot;/><span onclick=&quot;$(this).parent().remove();&quot;>x</span></div>');}});" action="select * from cs_present where 1=1?value={param}&cspHost={cspHost}" class="button">添加</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关联赠品字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cspPresents">${csPromotion.cspPresents}</textarea>
		 		<span>
		 	
			 ${csPromotion.cspPresents$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cspPresents}
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
		<s:if test="#request.csPromotion$cspUpdateTime!=null">${csPromotion$cspUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csPromotion.cspUpdateTime" id="cspUpdateTime"  value="<s:date name="csPromotion.cspUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择促销配置的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspUpdateTime">${csPromotion.cspUpdateTime}</textarea>
		 		<span>
		 	
			 ${csPromotion.cspUpdateTime$}
	 	  
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
		<s:if test="#request.csPromotion$cspAddTime!=null">${csPromotion$cspAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csPromotion.cspAddTime" id="cspAddTime"  value="<s:date name="csPromotion.cspAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择促销配置的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspAddTime">${csPromotion.cspAddTime}</textarea>
		 		<span>
		 	
			 ${csPromotion.cspAddTime$}
	 	  
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
		<s:if test="#request.csPromotion$cspStatus!=null">${csPromotion$cspStatus}</s:if><s:else>
		 	<select class="narrow" id="cspStatus" name="csPromotion.cspStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csPromotion.cspStatus==0?"selected":""}>配置中</option>
				<option value="1" ${csPromotion.cspStatus==1?"selected":""}>运营中</option>
				<option value="2" ${csPromotion.cspStatus==2?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择促销配置的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cspStatus">${csPromotion.cspStatus}</textarea>
		 		<span>
		 	
			 ${csPromotion.cspStatus$}
	 	  
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
					<input type="hidden" value="${csPromotion.cspId}" name="csPromotion.cspId" id="cspId" />
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