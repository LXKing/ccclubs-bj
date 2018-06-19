<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/operate/activitys/activity.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/operate/activitys/activity.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csActivity.csaId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csaId,csaHost,csaTitle,csaFlag,csaType,csaPlan,csaStart,csaFinish,csaPoster,csaZip,actors,csaStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csaId,csaHost,csaTitle,csaFlag,csaType,csaPlan,csaStart,csaFinish,csaPoster,csaZip,actors,csaStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csaId,csaHost,csaTitle,csaFlag,csaType,csaPlan,csaStart,csaFinish,csaPoster,csaZip,actors,csaStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csaId,csaHost,csaTitle,csaFlag,csaType,csaPlan,csaStart,csaFinish,csaPoster,csaZip,actors,csaStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"营销活动编辑":CTRL.title}</title>
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
	$("#activityForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csaId==true">
		${lz:set("haveEditable",true)}
		,"csActivity.csaId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaHost==true">
		${lz:set("haveEditable",true)}
		,"csActivity.csaHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaTitle==true">
		${lz:set("haveEditable",true)}
		,"csActivity.csaTitle":function(el){
			if(jQuery.trim(el.value)=="")
				return "活动主题不能为空";
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "活动主题最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaFlag==true">
		${lz:set("haveEditable",true)}
		,"csActivity.csaFlag":function(el){
			if(jQuery.trim(el.value)=="")
				return "活动标识不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "活动标识最大长度为32个字符";
			if(jQuery.trim(el.value)!=""){
				var exists = $.getObject("activity_query.do",{exists:true,csaFlag:el.value});
				if(exists && exists.length>0){
					if(${csActivity.csaId==null} || exists[0].value!=$("#csaId").val())
						return "活动标识已存在";						
				}
			}
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaType==true">
		${lz:set("haveEditable",true)}
		,"csActivity.csaType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择营销分类";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaPlan==true">
		${lz:set("haveEditable",true)}
		,"csActivity.csaPlan":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaStart==true">
		${lz:set("haveEditable",true)}
		,"csActivity.csaStart":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaFinish==true">
		${lz:set("haveEditable",true)}
		,"csActivity.csaFinish":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaPoster==true">
		${lz:set("haveEditable",true)}
		,"csActivity.csaPoster":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaZip==true">
		${lz:set("haveEditable",true)}
		,"csActivity.csaZip":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaCreater==true">
		${lz:set("haveEditable",true)}
		,"csActivity.csaCreater":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaUpdateTime==true">
		${lz:set("haveEditable",true)}
		,"csActivity.csaUpdateTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "修改时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaAddTime==true">
		${lz:set("haveEditable",true)}
		,"csActivity.csaAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csaStatus==true">
		${lz:set("haveEditable",true)}
		,"csActivity.csaStatus":function(el){
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
	<form class="form editform" ref="CsActivity" id="activityForm" name="activityForm" action="${empty CTRL.action?"activity_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写营销活动相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csActivityToken}" name="csActivityToken" id="csActivityToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csActivity.csaId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csaId和after$csaId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaId==true">
	${before$csaId}
	<dl class="csaId ${CTRL.e.csaId?"hidden":""}" major  ref="csaId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csaId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csActivity$csaId!=null">${csActivity$csaId}</s:if><s:else>
			<input type="hidden" value="${csActivity.csaId}" name="csActivity.csaId" id="csaId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaId">${csActivity.csaId}</textarea>
		 		<span>
		 	
			 ${csActivity.csaId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csaHost和after$csaHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaHost==true">
	${before$csaHost}
	<dl class="csaHost " major  ref="csaHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csaHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csActivity$csaHost!=null">${csActivity$csaHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csaHost" name="csActivity.csaHost">
		 				<option selected value="${csActivity.csaHost}">
		 					${get:SrvHost(csActivity.csaHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csaHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csaHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaHost">${csActivity.csaHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csActivity.csaHost}',{ctrl:{editable:false,visible:true}})">
			 ${csActivity.csaHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaHost}
	</s:if>
	
	${lz:set("注释","*****************活动主题字段的输入框代码*****************")}
	${lz:set("注释","before$csaTitle和after$csaTitle变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaTitle==true">
	${before$csaTitle}
	<dl class="csaTitle " major  ref="csaTitle" style="width:98%;">
		<dt>活动主题:</dt>
		<s:if test="#request.CTRL.e.csaTitle==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csActivity$csaTitle!=null">${csActivity$csaTitle}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csActivity.csaTitle" id="csaTitle"  value="${csActivity.csaTitle}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入营销活动的活动主题</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****活动主题字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csaTitle">${csActivity.csaTitle}</textarea>
		 		<span>
		 	
			 ${csActivity.csaTitle$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaTitle}
	</s:if>
	
	${lz:set("注释","*****************活动标识字段的输入框代码*****************")}
	${lz:set("注释","before$csaFlag和after$csaFlag变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaFlag==true">
	${before$csaFlag}
	<dl class="csaFlag " major  ref="csaFlag" >
		<dt>活动标识:</dt>
		<s:if test="#request.CTRL.e.csaFlag==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csActivity$csaFlag!=null">${csActivity$csaFlag}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csActivity.csaFlag" id="csaFlag"  value="${csActivity.csaFlag}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>只能是英文或者数字哦</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****活动标识字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaFlag">${csActivity.csaFlag}</textarea>
		 		<span>
		 	
			 ${csActivity.csaFlag$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaFlag}
	</s:if>
	
	${lz:set("注释","*****************营销分类字段的输入框代码*****************")}
	${lz:set("注释","before$csaType和after$csaType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaType==true">
	${before$csaType}
	<dl class="csaType " major  ref="csaType" >
		<dt>营销分类:</dt>
		<s:if test="#request.CTRL.e.csaType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csActivity$csaType!=null">${csActivity$csaType}</s:if><s:else>
		 	<select class="narrow" id="csaType" name="csActivity.csaType">
		 		<option value="">请选择</option>
 					${lz:set("items", get:propertys("营销类型","market_type"))}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.spId}" ${csActivity.csaType==item.spId?"selected":""}>${item.spName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csaType').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?key='+$('#csaType').val(),{ctrl:{editable:false}})"></a>
	 			<a class="reladd" config="{
	 				action:'${basePath}${proname}/configurator/property_edit.do?rd=1&parent=${get:property("market_type").spId}'
	 				,callback:function(object){
	 					$('<option selected value='+object.spId+'>'+object.spName+'</option>').appendTo('#csaType');
	 				}
	 			}" href="javascript:void(0);"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>产品(促销) 会员营销 渠道运营 品牌运营</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****营销分类字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaType">${csActivity.csaType}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${csActivity.csaType}',{ctrl:{editable:false,visible:true}})">
			 ${csActivity.csaType$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaType}
	</s:if>
	
	${lz:set("注释","*****************所属策划字段的输入框代码*****************")}
	${lz:set("注释","before$csaPlan和after$csaPlan变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaPlan==true">
	${before$csaPlan}
	<dl class="csaPlan " major  ref="csaPlan" >
		<dt>所属策划:</dt>
		<s:if test="#request.CTRL.e.csaPlan==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csActivity$csaPlan!=null">${csActivity$csaPlan}</s:if><s:else>
		 			<input title="请输入营销方案方案主题进行查询" class="combox narrow" action="${basePath}${proname}/operate/marketplan_query.do?value={param}&csmpHost={csaHost}" type="text" id="csaPlan" name="csActivity.csaPlan" text="${get:CsMarketPlan(csActivity.csaPlan).csmpName}" value="${csActivity.csaPlan}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csaPlan').val())==''){return;};window.href('${basePath}${proname}/operate/marketplan_details.do?key='+$('#csaPlan').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em>注意，该说明仅对内说明，对外不可见</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属策划字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaPlan">${csActivity.csaPlan}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/operate/marketplan_details.do?key=${csActivity.csaPlan}',{ctrl:{editable:false,visible:true}})">
			 ${csActivity.csaPlan$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaPlan}
	</s:if>
	
	${lz:set("注释","*****************开始时间字段的输入框代码*****************")}
	${lz:set("注释","before$csaStart和after$csaStart变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaStart==true">
	${before$csaStart}
	<dl class="csaStart " major  ref="csaStart" >
		<dt>开始时间:</dt>
		<s:if test="#request.CTRL.e.csaStart==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csActivity$csaStart!=null">${csActivity$csaStart}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csActivity.csaStart" id="csaStart"  value="<s:date name="csActivity.csaStart" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择营销活动的开始时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****开始时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaStart">${csActivity.csaStart}</textarea>
		 		<span>
		 	
			 ${csActivity.csaStart$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaStart}
	</s:if>
	
	${lz:set("注释","*****************结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$csaFinish和after$csaFinish变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaFinish==true">
	${before$csaFinish}
	<dl class="csaFinish " major  ref="csaFinish" >
		<dt>结束时间:</dt>
		<s:if test="#request.CTRL.e.csaFinish==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csActivity$csaFinish!=null">${csActivity$csaFinish}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csActivity.csaFinish" id="csaFinish"  value="<s:date name="csActivity.csaFinish" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择营销活动的结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaFinish">${csActivity.csaFinish}</textarea>
		 		<span>
		 	
			 ${csActivity.csaFinish$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaFinish}
	</s:if>
	
	${lz:set("注释","*****************海报设计字段的输入框代码*****************")}
	${lz:set("注释","before$csaPoster和after$csaPoster变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaPoster==true">
	${before$csaPoster}
	<dl class="csaPoster " minor  ref="csaPoster" style="width:98%;">
		<dt>海报设计:</dt>
		<s:if test="#request.CTRL.e.csaPoster==true">
		${lz:set("haveEditable",true)}
		<dd input="filelist">
		<s:if test="#request.csActivity$csaPoster!=null">${csActivity$csaPoster}</s:if><s:else>
		    <div id="csaPoster" class="wide input listinput">
			<s:generator val="csActivity.csaPoster" separator=",">
				<s:iterator id="item">
					${lz:set("isVacant",lz:vacant(item))}
		 			<s:if test="#request.isVacant==false">
					<div><s:property/><input type="hidden" name="csActivity.csaPoster" value="<s:property/>"/><span onclick="$(this).parent().remove();">x</span></div>
					</s:if>
				</s:iterator>
			</s:generator>
			</div>
			<button type="button" style="float:left;" onclick="$.upload({type:'file',count:10,callback:function(url){if(url)$('#csaPoster').append('<div>'+url+'<input type=&quot;hidden&quot; name=&quot;csActivity.csaPoster&quot; value=&quot;'+url+'&quot;/><span onclick=&quot;$(this).parent().remove();&quot;>x</span></div>');}})" class="button">添加</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>海报图片尺寸建议1920*500</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****海报设计字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csaPoster">${csActivity.csaPoster}</textarea>
		 		<span>
		 	
			 ${csActivity.csaPoster$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaPoster}
	</s:if>
	
	${lz:set("注释","*****************页面文件字段的输入框代码*****************")}
	${lz:set("注释","before$csaZip和after$csaZip变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaZip==true">
	${before$csaZip}
	<dl class="csaZip " minor  ref="csaZip" style="width:98%;">
		<dt>页面文件:</dt>
		<s:if test="#request.CTRL.e.csaZip==true">
		${lz:set("haveEditable",true)}
		<dd input="file">
		<s:if test="#request.csActivity$csaZip!=null">${csActivity$csaZip}</s:if><s:else>
		 	<input type="text" class="input" maxlength="128" size="32" name="csActivity.csaZip" id="csaZip"  value="${csActivity.csaZip}"/>
			<button type="button" onclick="$.upload({type:'file',callback:function(url){if(url)$('#csaZip').val(url)}})" class="button">上传文件</button>
			<button type="button" onclick="$('#csaZip').val('')" class="button">删除文件</button>
			<button type="button" onclick="window.location='${ lz:config("lazy3q.download")==null ? basePath : ""}${ lz:config("lazy3q.download")==null ? "download" :  lz:config("lazy3q.download")}?path='+$('#csaZip').val()" class="button">下载文件</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传开发好的html压缩文件，数据获取请通过开放平台获取</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****页面文件字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csaZip">${csActivity.csaZip}</textarea>
		 		<span>
		 	
			 ${csActivity.csaZip$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaZip}
	</s:if>
	
	${lz:set("注释","*****************参与会员字段的输入框代码*****************")}
	${lz:set("注释","before$actors和after$actors变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.actors==true">
	${before$actors}
	<dl class="actors " minor  ref="actors" style="width:98%;">
		<dt>参与会员:</dt>
		<s:if test="#request.CTRL.e.actors==true">
		${lz:set("haveEditable",true)}
		<dd input="frame">
		<s:if test="#request.csActivity$actors!=null">${csActivity$actors}</s:if><s:else>
		  		<s:if test="csActivity.csaId!=null">
				<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="actors" name="actors" frameborder='0' src="" url="${basePath}${proname}/operate/activitys/activity/in.do?inFrame=true&canQuery=false&all=true&csaiActivity=${csActivity.csaId}&ctrl={queryable:false,fields:{csaiActivity:{visible:true,editable:false,defaultValue:${csActivity.csaId}}}}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#actors').attr('src',$('#actors').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
				</s:if>
				<s:else>
				<div class="state-input wide">
				完成或保存表单后即可编辑参与会员
				</div>
				</s:else>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****参与会员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<s:if test="csActivity.csaId!=null">	  		
			<iframe class="state-input iframe-list hidden" onload="$(this).autoHeight()" id="actors" name="actors" frameborder='0' src="" url="${basePath}${proname}/operate/activitys/activity/in.do?inFrame=true&canQuery=false&all=true&csaiActivity=${csActivity.csaId}&ctrl={canAdd:false,canEdit:false,canDel:false,queryable:false}&size=8" height='0'></iframe>
				<div class="iframe-list">
					<a class="button" style="float:right;" onclick="$('#actors').attr('src',$('#actors').show().attr('url'));$(this).parent().remove();" href="javascript:void(0);"><img align="absmiddle" width="20" src="${basePath}admin/images/icons/2008825642250778013.png"/>点击显示记录详情</a>
					<div class="line" style="margin:0px;clear:none;width:450px;"></div>
				</div>
			</s:if>			
		</dd>
		</s:else>
	</dl>
	${after$actors}
	</s:if>
	
	${lz:set("注释","*****************创建人字段的输入框代码*****************")}
	${lz:set("注释","before$csaCreater和after$csaCreater变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaCreater==true">
	${before$csaCreater}
	<dl class="csaCreater " major  ref="csaCreater" >
		<dt>创&nbsp;&nbsp;建&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.csaCreater==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csActivity$csaCreater!=null">${csActivity$csaCreater}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csaCreater" name="csActivity.csaCreater">
		 				<option selected value="${csActivity.csaCreater}">
		 					${get:SrvUser(csActivity.csaCreater).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csaCreater').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csaCreater').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****创建人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaCreater">${csActivity.csaCreater}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csActivity.csaCreater}',{ctrl:{editable:false,visible:true}})">
			 ${csActivity.csaCreater$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaCreater}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$csaUpdateTime和after$csaUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaUpdateTime==true">
	${before$csaUpdateTime}
	<dl class="csaUpdateTime " major  ref="csaUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.csaUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csActivity$csaUpdateTime!=null">${csActivity$csaUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csActivity.csaUpdateTime" id="csaUpdateTime"  value="<s:date name="csActivity.csaUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择营销活动的修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaUpdateTime">${csActivity.csaUpdateTime}</textarea>
		 		<span>
		 	
			 ${csActivity.csaUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csaAddTime和after$csaAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaAddTime==true">
	${before$csaAddTime}
	<dl class="csaAddTime " major  ref="csaAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csaAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csActivity$csaAddTime!=null">${csActivity$csaAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csActivity.csaAddTime" id="csaAddTime"  value="<s:date name="csActivity.csaAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择营销活动的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaAddTime">${csActivity.csaAddTime}</textarea>
		 		<span>
		 	
			 ${csActivity.csaAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csaStatus和after$csaStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csaStatus==true">
	${before$csaStatus}
	<dl class="csaStatus " major  ref="csaStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csaStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csActivity$csaStatus!=null">${csActivity$csaStatus}</s:if><s:else>
		 	<select class="narrow" id="csaStatus" name="csActivity.csaStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csActivity.csaStatus==0?"selected":""}>策划中</option>
				<option value="1" ${csActivity.csaStatus==1?"selected":""}>进行中</option>
				<option value="2" ${csActivity.csaStatus==2?"selected":""}>已结束</option>
				<option value="3" ${csActivity.csaStatus==3?"selected":""}>已放弃</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择营销活动的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csaStatus">${csActivity.csaStatus}</textarea>
		 		<span>
		 	
			 ${csActivity.csaStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csaStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csActivity.csaId}" name="csActivity.csaId" id="csaId" />
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