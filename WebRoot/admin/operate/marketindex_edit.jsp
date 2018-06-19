<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/operate/marketindex.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/operate/marketindex.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csMarketIndex.csmiId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csmiId,csmiHost,csmiName,csmiPlan,csmiType,csmiDesc,csmiTarget,csmiResult,csmiRate,csmiStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csmiId,csmiHost,csmiName,csmiPlan,csmiType,csmiDesc,csmiTarget,csmiResult,csmiRate,csmiStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csmiId,csmiHost,csmiName,csmiPlan,csmiType,csmiDesc,csmiTarget,csmiResult,csmiRate,csmiStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csmiId,csmiHost,csmiName,csmiPlan,csmiType,csmiDesc,csmiTarget,csmiResult,csmiRate,csmiStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"营销指标编辑":CTRL.title}</title>
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
	$("#marketindexForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csmiId==true">
		${lz:set("haveEditable",true)}
		,"csMarketIndex.csmiId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiHost==true">
		${lz:set("haveEditable",true)}
		,"csMarketIndex.csmiHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiName==true">
		${lz:set("haveEditable",true)}
		,"csMarketIndex.csmiName":function(el){
			if(jQuery.trim(el.value)=="")
				return "指标名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "指标名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiPlan==true">
		${lz:set("haveEditable",true)}
		,"csMarketIndex.csmiPlan":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiType==true">
		${lz:set("haveEditable",true)}
		,"csMarketIndex.csmiType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择指标类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiDesc==true">
		${lz:set("haveEditable",true)}
		,"csMarketIndex.csmiDesc":function(el){
			if(jQuery.trim(el.value)=="")
				return "说明不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "说明最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiTarget==true">
		${lz:set("haveEditable",true)}
		,"csMarketIndex.csmiTarget":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "目标设定输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiResult==true">
		${lz:set("haveEditable",true)}
		,"csMarketIndex.csmiResult":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "实际完成输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiRate==true">
		${lz:set("haveEditable",true)}
		,"csMarketIndex.csmiRate":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "完成率输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmiStatus==true">
		${lz:set("haveEditable",true)}
		,"csMarketIndex.csmiStatus":function(el){
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
	<form class="form editform" ref="CsMarketIndex" id="marketindexForm" name="marketindexForm" action="${empty CTRL.action?"marketindex_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写营销指标相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csMarketIndexToken}" name="csMarketIndexToken" id="csMarketIndexToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csMarketIndex.csmiId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csmiId和after$csmiId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiId==true">
	${before$csmiId}
	<dl class="csmiId ${CTRL.e.csmiId?"hidden":""}" major  ref="csmiId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csmiId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csMarketIndex$csmiId!=null">${csMarketIndex$csmiId}</s:if><s:else>
			<input type="hidden" value="${csMarketIndex.csmiId}" name="csMarketIndex.csmiId" id="csmiId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiId">${csMarketIndex.csmiId}</textarea>
		 		<span>
		 	
			 ${csMarketIndex.csmiId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csmiHost和after$csmiHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiHost==true">
	${before$csmiHost}
	<dl class="csmiHost " major  ref="csmiHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csmiHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csMarketIndex$csmiHost!=null">${csMarketIndex$csmiHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csmiHost" name="csMarketIndex.csmiHost">
		 				<option selected value="${csMarketIndex.csmiHost}">
		 					${get:SrvHost(csMarketIndex.csmiHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmiHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csmiHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiHost">${csMarketIndex.csmiHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csMarketIndex.csmiHost}',{ctrl:{editable:false,visible:true}})">
			 ${csMarketIndex.csmiHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiHost}
	</s:if>
	
	${lz:set("注释","*****************指标名称字段的输入框代码*****************")}
	${lz:set("注释","before$csmiName和after$csmiName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiName==true">
	${before$csmiName}
	<dl class="csmiName " major  ref="csmiName" >
		<dt>指标名称:</dt>
		<s:if test="#request.CTRL.e.csmiName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMarketIndex$csmiName!=null">${csMarketIndex$csmiName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMarketIndex.csmiName" id="csmiName"  value="${csMarketIndex.csmiName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入营销指标的指标名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****指标名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiName">${csMarketIndex.csmiName}</textarea>
		 		<span>
		 	
			 ${csMarketIndex.csmiName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiName}
	</s:if>
	
	${lz:set("注释","*****************所属方案字段的输入框代码*****************")}
	${lz:set("注释","before$csmiPlan和after$csmiPlan变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiPlan==true">
	${before$csmiPlan}
	<dl class="csmiPlan " major  ref="csmiPlan" >
		<dt>所属方案:</dt>
		<s:if test="#request.CTRL.e.csmiPlan==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csMarketIndex$csmiPlan!=null">${csMarketIndex$csmiPlan}</s:if><s:else>
		 			<input title="请输入营销方案方案主题进行查询" class="combox narrow" action="${basePath}${proname}/operate/marketplan_query.do?value={param}&csmpHost={csmiHost}" type="text" id="csmiPlan" name="csMarketIndex.csmiPlan" text="${get:CsMarketPlan(csMarketIndex.csmiPlan).csmpName}" value="${csMarketIndex.csmiPlan}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmiPlan').val())==''){return;};window.href('${basePath}${proname}/operate/marketplan_details.do?key='+$('#csmiPlan').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属方案字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiPlan">${csMarketIndex.csmiPlan}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/operate/marketplan_details.do?key=${csMarketIndex.csmiPlan}',{ctrl:{editable:false,visible:true}})">
			 ${csMarketIndex.csmiPlan$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiPlan}
	</s:if>
	
	${lz:set("注释","*****************指标类型字段的输入框代码*****************")}
	${lz:set("注释","before$csmiType和after$csmiType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiType==true">
	${before$csmiType}
	<dl class="csmiType " major  ref="csmiType" >
		<dt>指标类型:</dt>
		<s:if test="#request.CTRL.e.csmiType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMarketIndex$csmiType!=null">${csMarketIndex$csmiType}</s:if><s:else>
		 	<select class="narrow" id="csmiType" name="csMarketIndex.csmiType">
		 		<option value="">请选择</option>
				<option value="0" ${csMarketIndex.csmiType==0?"selected":""}>其它</option>
				<option value="1" ${csMarketIndex.csmiType==1?"selected":""}>PV</option>
				<option value="2" ${csMarketIndex.csmiType==2?"selected":""}>UV</option>
				<option value="3" ${csMarketIndex.csmiType==3?"selected":""}>IP</option>
				<option value="4" ${csMarketIndex.csmiType==4?"selected":""}>注册</option>
				<option value="5" ${csMarketIndex.csmiType==5?"selected":""}>充值</option>
				<option value="6" ${csMarketIndex.csmiType==6?"selected":""}>订单</option>
				<option value="7" ${csMarketIndex.csmiType==7?"selected":""}>维护</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择营销指标的指标类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****指标类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiType">${csMarketIndex.csmiType}</textarea>
		 		<span>
		 	
			 ${csMarketIndex.csmiType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiType}
	</s:if>
	
	${lz:set("注释","*****************说明字段的输入框代码*****************")}
	${lz:set("注释","before$csmiDesc和after$csmiDesc变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiDesc==true">
	${before$csmiDesc}
	<dl class="csmiDesc " major  ref="csmiDesc" >
		<dt>说　　明:</dt>
		<s:if test="#request.CTRL.e.csmiDesc==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csMarketIndex$csmiDesc!=null">${csMarketIndex$csmiDesc}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csMarketIndex.csmiDesc" id="csmiDesc"  value="${csMarketIndex.csmiDesc}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入营销指标的说明</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****说明字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiDesc">${csMarketIndex.csmiDesc}</textarea>
		 		<span>
		 	
			 ${csMarketIndex.csmiDesc$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiDesc}
	</s:if>
	
	${lz:set("注释","*****************目标设定字段的输入框代码*****************")}
	${lz:set("注释","before$csmiTarget和after$csmiTarget变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiTarget==true">
	${before$csmiTarget}
	<dl class="csmiTarget " major  ref="csmiTarget" >
		<dt>目标设定:</dt>
		<s:if test="#request.CTRL.e.csmiTarget==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csMarketIndex$csmiTarget!=null">${csMarketIndex$csmiTarget}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csMarketIndex.csmiTarget" id="csmiTarget"  value="${csMarketIndex.csmiTarget}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入营销指标的目标设定</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****目标设定字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiTarget">${csMarketIndex.csmiTarget}</textarea>
		 		<span>
		 	
			 ${csMarketIndex.csmiTarget$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiTarget}
	</s:if>
	
	${lz:set("注释","*****************实际完成字段的输入框代码*****************")}
	${lz:set("注释","before$csmiResult和after$csmiResult变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiResult==true">
	${before$csmiResult}
	<dl class="csmiResult " major  ref="csmiResult" >
		<dt>实际完成:</dt>
		<s:if test="#request.CTRL.e.csmiResult==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csMarketIndex$csmiResult!=null">${csMarketIndex$csmiResult}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csMarketIndex.csmiResult" id="csmiResult"  value="${csMarketIndex.csmiResult}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入营销指标的实际完成</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实际完成字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiResult">${csMarketIndex.csmiResult}</textarea>
		 		<span>
		 	
			 ${csMarketIndex.csmiResult$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiResult}
	</s:if>
	
	${lz:set("注释","*****************完成率字段的输入框代码*****************")}
	${lz:set("注释","before$csmiRate和after$csmiRate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiRate==true">
	${before$csmiRate}
	<dl class="csmiRate " major  ref="csmiRate" >
		<dt>完&nbsp;&nbsp;成&nbsp;&nbsp;率:</dt>
		<s:if test="#request.CTRL.e.csmiRate==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csMarketIndex$csmiRate!=null">${csMarketIndex$csmiRate}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csMarketIndex.csmiRate" id="csmiRate"  value="${csMarketIndex.csmiRate}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入营销指标的完成率</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****完成率字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiRate">${csMarketIndex.csmiRate}</textarea>
		 		<span>
		 	
			 ${csMarketIndex.csmiRate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiRate}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csmiStatus和after$csmiStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmiStatus==true">
	${before$csmiStatus}
	<dl class="csmiStatus " major  ref="csmiStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csmiStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMarketIndex$csmiStatus!=null">${csMarketIndex$csmiStatus}</s:if><s:else>
		 	<select class="narrow" id="csmiStatus" name="csMarketIndex.csmiStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csMarketIndex.csmiStatus==0?"selected":""}>执行中</option>
				<option value="1" ${csMarketIndex.csmiStatus==1?"selected":""}>已完成</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择营销指标的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmiStatus">${csMarketIndex.csmiStatus}</textarea>
		 		<span>
		 	
			 ${csMarketIndex.csmiStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmiStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csMarketIndex.csmiId}" name="csMarketIndex.csmiId" id="csmiId" />
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