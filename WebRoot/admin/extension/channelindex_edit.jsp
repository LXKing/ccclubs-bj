<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/extension/channelindex.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/extension/channelindex.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csChannelIndex.csciId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csciId,csciHost,csciName,csciPlan,csciType,csciDesc,csciTarget,csciResult,csciRate,csciStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csciId,csciHost,csciName,csciPlan,csciType,csciDesc,csciTarget,csciResult,csciRate,csciStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csciId,csciHost,csciName,csciPlan,csciType,csciDesc,csciTarget,csciResult,csciRate,csciStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csciId,csciHost,csciName,csciPlan,csciType,csciDesc,csciTarget,csciResult,csciRate,csciStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"渠道指标编辑":CTRL.title}</title>
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
	$("#channelindexForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csciId==true">
		${lz:set("haveEditable",true)}
		,"csChannelIndex.csciId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csciHost==true">
		${lz:set("haveEditable",true)}
		,"csChannelIndex.csciHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csciName==true">
		${lz:set("haveEditable",true)}
		,"csChannelIndex.csciName":function(el){
			if(jQuery.trim(el.value)=="")
				return "指标名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "指标名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csciPlan==true">
		${lz:set("haveEditable",true)}
		,"csChannelIndex.csciPlan":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csciType==true">
		${lz:set("haveEditable",true)}
		,"csChannelIndex.csciType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择指标类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csciDesc==true">
		${lz:set("haveEditable",true)}
		,"csChannelIndex.csciDesc":function(el){
			if(jQuery.trim(el.value)=="")
				return "说明不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "说明最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csciTarget==true">
		${lz:set("haveEditable",true)}
		,"csChannelIndex.csciTarget":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "目标设定输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csciResult==true">
		${lz:set("haveEditable",true)}
		,"csChannelIndex.csciResult":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "实际完成输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csciRate==true">
		${lz:set("haveEditable",true)}
		,"csChannelIndex.csciRate":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "完成率输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csciStatus==true">
		${lz:set("haveEditable",true)}
		,"csChannelIndex.csciStatus":function(el){
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
	<form class="form editform" ref="CsChannelIndex" id="channelindexForm" name="channelindexForm" action="${empty CTRL.action?"channelindex_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写渠道指标相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csChannelIndexToken}" name="csChannelIndexToken" id="csChannelIndexToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csChannelIndex.csciId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csciId和after$csciId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csciId==true">
	${before$csciId}
	<dl class="csciId ${CTRL.e.csciId?"hidden":""}" major  ref="csciId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csciId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csChannelIndex$csciId!=null">${csChannelIndex$csciId}</s:if><s:else>
			<input type="hidden" value="${csChannelIndex.csciId}" name="csChannelIndex.csciId" id="csciId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csciId">${csChannelIndex.csciId}</textarea>
		 		<span>
		 	
			 ${csChannelIndex.csciId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csciId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csciHost和after$csciHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csciHost==true">
	${before$csciHost}
	<dl class="csciHost " major  ref="csciHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csciHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csChannelIndex$csciHost!=null">${csChannelIndex$csciHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csciHost" name="csChannelIndex.csciHost">
		 				<option selected value="${csChannelIndex.csciHost}">
		 					${get:SrvHost(csChannelIndex.csciHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csciHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csciHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csciHost">${csChannelIndex.csciHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csChannelIndex.csciHost}',{ctrl:{editable:false,visible:true}})">
			 ${csChannelIndex.csciHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csciHost}
	</s:if>
	
	${lz:set("注释","*****************指标名称字段的输入框代码*****************")}
	${lz:set("注释","before$csciName和after$csciName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csciName==true">
	${before$csciName}
	<dl class="csciName " major  ref="csciName" >
		<dt>指标名称:</dt>
		<s:if test="#request.CTRL.e.csciName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csChannelIndex$csciName!=null">${csChannelIndex$csciName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csChannelIndex.csciName" id="csciName"  value="${csChannelIndex.csciName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入渠道指标的指标名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****指标名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csciName">${csChannelIndex.csciName}</textarea>
		 		<span>
		 	
			 ${csChannelIndex.csciName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csciName}
	</s:if>
	
	${lz:set("注释","*****************所属渠道字段的输入框代码*****************")}
	${lz:set("注释","before$csciPlan和after$csciPlan变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csciPlan==true">
	${before$csciPlan}
	<dl class="csciPlan " major  ref="csciPlan" >
		<dt>所属渠道:</dt>
		<s:if test="#request.CTRL.e.csciPlan==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csChannelIndex$csciPlan!=null">${csChannelIndex$csciPlan}</s:if><s:else>
		 			<input title="请输入访问渠道渠道名称进行查询" class="combox narrow" action="${basePath}${proname}/extension/channel_query.do?value={param}&cscHost={csciHost}" type="text" id="csciPlan" name="csChannelIndex.csciPlan" text="${get:CsChannel(csChannelIndex.csciPlan).cscName}" value="${csChannelIndex.csciPlan}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csciPlan').val())==''){return;};window.href('${basePath}${proname}/extension/channel_details.do?key='+$('#csciPlan').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属渠道字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csciPlan">${csChannelIndex.csciPlan}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/extension/channel_details.do?key=${csChannelIndex.csciPlan}',{ctrl:{editable:false,visible:true}})">
			 ${csChannelIndex.csciPlan$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csciPlan}
	</s:if>
	
	${lz:set("注释","*****************指标类型字段的输入框代码*****************")}
	${lz:set("注释","before$csciType和after$csciType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csciType==true">
	${before$csciType}
	<dl class="csciType " major  ref="csciType" >
		<dt>指标类型:</dt>
		<s:if test="#request.CTRL.e.csciType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csChannelIndex$csciType!=null">${csChannelIndex$csciType}</s:if><s:else>
		 	<select class="narrow" id="csciType" name="csChannelIndex.csciType">
		 		<option value="">请选择</option>
				<option value="0" ${csChannelIndex.csciType==0?"selected":""}>其它</option>
				<option value="1" ${csChannelIndex.csciType==1?"selected":""}>PV</option>
				<option value="2" ${csChannelIndex.csciType==2?"selected":""}>UV</option>
				<option value="3" ${csChannelIndex.csciType==3?"selected":""}>IP</option>
				<option value="4" ${csChannelIndex.csciType==4?"selected":""}>注册</option>
				<option value="5" ${csChannelIndex.csciType==5?"selected":""}>充值</option>
				<option value="6" ${csChannelIndex.csciType==6?"selected":""}>订单</option>
				<option value="7" ${csChannelIndex.csciType==7?"selected":""}>维护</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择渠道指标的指标类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****指标类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csciType">${csChannelIndex.csciType}</textarea>
		 		<span>
		 	
			 ${csChannelIndex.csciType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csciType}
	</s:if>
	
	${lz:set("注释","*****************说明字段的输入框代码*****************")}
	${lz:set("注释","before$csciDesc和after$csciDesc变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csciDesc==true">
	${before$csciDesc}
	<dl class="csciDesc " major  ref="csciDesc" >
		<dt>说　　明:</dt>
		<s:if test="#request.CTRL.e.csciDesc==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csChannelIndex$csciDesc!=null">${csChannelIndex$csciDesc}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csChannelIndex.csciDesc" id="csciDesc"  value="${csChannelIndex.csciDesc}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入渠道指标的说明</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****说明字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csciDesc">${csChannelIndex.csciDesc}</textarea>
		 		<span>
		 	
			 ${csChannelIndex.csciDesc$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csciDesc}
	</s:if>
	
	${lz:set("注释","*****************目标设定字段的输入框代码*****************")}
	${lz:set("注释","before$csciTarget和after$csciTarget变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csciTarget==true">
	${before$csciTarget}
	<dl class="csciTarget " major  ref="csciTarget" >
		<dt>目标设定:</dt>
		<s:if test="#request.CTRL.e.csciTarget==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csChannelIndex$csciTarget!=null">${csChannelIndex$csciTarget}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csChannelIndex.csciTarget" id="csciTarget"  value="${csChannelIndex.csciTarget}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入渠道指标的目标设定</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****目标设定字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csciTarget">${csChannelIndex.csciTarget}</textarea>
		 		<span>
		 	
			 ${csChannelIndex.csciTarget$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csciTarget}
	</s:if>
	
	${lz:set("注释","*****************实际完成字段的输入框代码*****************")}
	${lz:set("注释","before$csciResult和after$csciResult变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csciResult==true">
	${before$csciResult}
	<dl class="csciResult " major  ref="csciResult" >
		<dt>实际完成:</dt>
		<s:if test="#request.CTRL.e.csciResult==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csChannelIndex$csciResult!=null">${csChannelIndex$csciResult}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csChannelIndex.csciResult" id="csciResult"  value="${csChannelIndex.csciResult}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入渠道指标的实际完成</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实际完成字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csciResult">${csChannelIndex.csciResult}</textarea>
		 		<span>
		 	
			 ${csChannelIndex.csciResult$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csciResult}
	</s:if>
	
	${lz:set("注释","*****************完成率字段的输入框代码*****************")}
	${lz:set("注释","before$csciRate和after$csciRate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csciRate==true">
	${before$csciRate}
	<dl class="csciRate " major  ref="csciRate" >
		<dt>完&nbsp;&nbsp;成&nbsp;&nbsp;率:</dt>
		<s:if test="#request.CTRL.e.csciRate==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csChannelIndex$csciRate!=null">${csChannelIndex$csciRate}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csChannelIndex.csciRate" id="csciRate"  value="${csChannelIndex.csciRate}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入渠道指标的完成率</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****完成率字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csciRate">${csChannelIndex.csciRate}</textarea>
		 		<span>
		 	
			 ${csChannelIndex.csciRate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csciRate}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csciStatus和after$csciStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csciStatus==true">
	${before$csciStatus}
	<dl class="csciStatus " major  ref="csciStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csciStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csChannelIndex$csciStatus!=null">${csChannelIndex$csciStatus}</s:if><s:else>
		 	<select class="narrow" id="csciStatus" name="csChannelIndex.csciStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csChannelIndex.csciStatus==0?"selected":""}>执行中</option>
				<option value="1" ${csChannelIndex.csciStatus==1?"selected":""}>已完成</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择渠道指标的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csciStatus">${csChannelIndex.csciStatus}</textarea>
		 		<span>
		 	
			 ${csChannelIndex.csciStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csciStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csChannelIndex.csciId}" name="csChannelIndex.csciId" id="csciId" />
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