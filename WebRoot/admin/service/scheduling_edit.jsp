<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/service/scheduling.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/service/scheduling.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csScheduling.cssId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cssId,cssHost,cssOutlets,cssCar,cssTime,cssType,cssDir,cssExplain,cssOrder,cssOperator,cssStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cssId,cssHost,cssOutlets,cssCar,cssTime,cssType,cssDir,cssExplain,cssOrder,cssOperator,cssStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cssId,cssHost,cssOutlets,cssCar,cssTime,cssType,cssDir,cssExplain,cssOrder,cssOperator,cssStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cssId,cssHost,cssOutlets,cssCar,cssTime,cssType,cssDir,cssExplain,cssOrder,cssOperator,cssStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"车辆调度编辑":CTRL.title}</title>
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
	$("#schedulingForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cssId==true">
		${lz:set("haveEditable",true)}
		,"csScheduling.cssId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssHost==true">
		${lz:set("haveEditable",true)}
		,"csScheduling.cssHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssOutlets==true">
		${lz:set("haveEditable",true)}
		,"csScheduling.cssOutlets":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssCar==true">
		${lz:set("haveEditable",true)}
		,"csScheduling.cssCar":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssTime==true">
		${lz:set("haveEditable",true)}
		,"csScheduling.cssTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssType==true">
		${lz:set("haveEditable",true)}
		,"csScheduling.cssType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssDir==true">
		${lz:set("haveEditable",true)}
		,"csScheduling.cssDir":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择方向";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssExplain==true">
		${lz:set("haveEditable",true)}
		,"csScheduling.cssExplain":function(el){
			if(jQuery.trim(el.value)=="")
				return "说明不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "说明最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssOrder==true">
		${lz:set("haveEditable",true)}
		,"csScheduling.cssOrder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssOperator==true">
		${lz:set("haveEditable",true)}
		,"csScheduling.cssOperator":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cssStatus==true">
		${lz:set("haveEditable",true)}
		,"csScheduling.cssStatus":function(el){
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
	<form class="form editform" ref="CsScheduling" id="schedulingForm" name="schedulingForm" action="${empty CTRL.action?"scheduling_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写车辆调度相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csSchedulingToken}" name="csSchedulingToken" id="csSchedulingToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csScheduling.cssId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cssId和after$cssId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssId==true">
	${before$cssId}
	<dl class="cssId ${CTRL.e.cssId?"hidden":""}" major  ref="cssId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cssId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csScheduling$cssId!=null">${csScheduling$cssId}</s:if><s:else>
			<input type="hidden" value="${csScheduling.cssId}" name="csScheduling.cssId" id="cssId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssId">${csScheduling.cssId}</textarea>
		 		<span>
		 	
			 ${csScheduling.cssId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cssHost和after$cssHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssHost==true">
	${before$cssHost}
	<dl class="cssHost " major  ref="cssHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cssHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csScheduling$cssHost!=null">${csScheduling$cssHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cssHost" name="csScheduling.cssHost">
		 				<option selected value="${csScheduling.cssHost}">
		 					${get:SrvHost(csScheduling.cssHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cssHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssHost">${csScheduling.cssHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csScheduling.cssHost}',{ctrl:{editable:false,visible:true}})">
			 ${csScheduling.cssHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssHost}
	</s:if>
	
	${lz:set("注释","*****************网点字段的输入框代码*****************")}
	${lz:set("注释","before$cssOutlets和after$cssOutlets变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssOutlets==true">
	${before$cssOutlets}
	<dl class="cssOutlets " major  ref="cssOutlets" >
		<dt>网　　点:</dt>
		<s:if test="#request.CTRL.e.cssOutlets==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csScheduling$cssOutlets!=null">${csScheduling$cssOutlets}</s:if><s:else>
		 			<input title="请输入网点名称进行查询" class="combox narrow" action="${basePath}${proname}/object/outlets_query.do?value={param}&csoHost={cssHost}" type="text" id="cssOutlets" name="csScheduling.cssOutlets" text="${get:CsOutlets(csScheduling.cssOutlets).csoName}" value="${csScheduling.cssOutlets}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssOutlets').val())==''){return;};window.href('${basePath}${proname}/object/outlets_details.do?key='+$('#cssOutlets').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****网点字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssOutlets">${csScheduling.cssOutlets}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/outlets_details.do?key=${csScheduling.cssOutlets}',{ctrl:{editable:false,visible:true}})">
			 ${csScheduling.cssOutlets$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssOutlets}
	</s:if>
	
	${lz:set("注释","*****************车辆字段的输入框代码*****************")}
	${lz:set("注释","before$cssCar和after$cssCar变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssCar==true">
	${before$cssCar}
	<dl class="cssCar " major  ref="cssCar" >
		<dt>车　　辆:</dt>
		<s:if test="#request.CTRL.e.cssCar==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csScheduling$cssCar!=null">${csScheduling$cssCar}</s:if><s:else>
		 			<input title="请输入车辆车牌号进行查询" class="combox narrow" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={cssHost}" type="text" id="cssCar" name="csScheduling.cssCar" text="${get:CsCar(csScheduling.cssCar).cscNumber}" value="${csScheduling.cssCar}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssCar').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?key='+$('#cssCar').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车辆字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssCar">${csScheduling.cssCar}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/car_details.do?key=${csScheduling.cssCar}',{ctrl:{editable:false,visible:true}})">
			 ${csScheduling.cssCar$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssCar}
	</s:if>
	
	${lz:set("注释","*****************时间字段的输入框代码*****************")}
	${lz:set("注释","before$cssTime和after$cssTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssTime==true">
	${before$cssTime}
	<dl class="cssTime " major  ref="cssTime" >
		<dt>时　　间:</dt>
		<s:if test="#request.CTRL.e.cssTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csScheduling$cssTime!=null">${csScheduling$cssTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csScheduling.cssTime" id="cssTime"  value="<s:date name="csScheduling.cssTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆调度的时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssTime">${csScheduling.cssTime}</textarea>
		 		<span>
		 	
			 ${csScheduling.cssTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssTime}
	</s:if>
	
	${lz:set("注释","*****************类型字段的输入框代码*****************")}
	${lz:set("注释","before$cssType和after$cssType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssType==true">
	${before$cssType}
	<dl class="cssType " major  ref="cssType" >
		<dt>类　　型:</dt>
		<s:if test="#request.CTRL.e.cssType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csScheduling$cssType!=null">${csScheduling$cssType}</s:if><s:else>
		 	<select class="narrow" id="cssType" name="csScheduling.cssType">
		 		<option value="">请选择</option>
				<option value="0" ${csScheduling.cssType==0?"selected":""}>车辆投放</option>
				<option value="1" ${csScheduling.cssType==1?"selected":""}>定单调度</option>
				<option value="2" ${csScheduling.cssType==2?"selected":""}>干预调度</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆调度的类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssType">${csScheduling.cssType}</textarea>
		 		<span>
		 	
			 ${csScheduling.cssType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssType}
	</s:if>
	
	${lz:set("注释","*****************方向字段的输入框代码*****************")}
	${lz:set("注释","before$cssDir和after$cssDir变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssDir==true">
	${before$cssDir}
	<dl class="cssDir " major  ref="cssDir" >
		<dt>方　　向:</dt>
		<s:if test="#request.CTRL.e.cssDir==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csScheduling$cssDir!=null">${csScheduling$cssDir}</s:if><s:else>
		 	<select class="narrow" id="cssDir" name="csScheduling.cssDir">
		 		<option value="">请选择</option>
				<option value="0" ${csScheduling.cssDir==0?"selected":""}>调入</option>
				<option value="1" ${csScheduling.cssDir==1?"selected":""}>调出</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆调度的方向</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****方向字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssDir">${csScheduling.cssDir}</textarea>
		 		<span>
		 	
			 ${csScheduling.cssDir$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssDir}
	</s:if>
	
	${lz:set("注释","*****************说明字段的输入框代码*****************")}
	${lz:set("注释","before$cssExplain和after$cssExplain变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssExplain==true">
	${before$cssExplain}
	<dl class="cssExplain " major  ref="cssExplain" >
		<dt>说　　明:</dt>
		<s:if test="#request.CTRL.e.cssExplain==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csScheduling$cssExplain!=null">${csScheduling$cssExplain}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csScheduling.cssExplain" id="cssExplain"  value="${csScheduling.cssExplain}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入车辆调度的说明</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****说明字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssExplain">${csScheduling.cssExplain}</textarea>
		 		<span>
		 	
			 ${csScheduling.cssExplain$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssExplain}
	</s:if>
	
	${lz:set("注释","*****************订单字段的输入框代码*****************")}
	${lz:set("注释","before$cssOrder和after$cssOrder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssOrder==true">
	${before$cssOrder}
	<dl class="cssOrder " minor  ref="cssOrder" >
		<dt>订　　单:</dt>
		<s:if test="#request.CTRL.e.cssOrder==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csScheduling$cssOrder!=null">${csScheduling$cssOrder}</s:if><s:else>
		 			<input title="请输入系统订单订单编号进行查询" class="combox narrow" action="${basePath}${proname}/service/order_query.do?value={param}&csoHost={cssHost}" type="text" id="cssOrder" name="csScheduling.cssOrder" text="${get:CsOrder(csScheduling.cssOrder).csoId}" value="${csScheduling.cssOrder}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cssOrder').val())==''){return;};window.href('${basePath}${proname}/service/order_details.do?key='+$('#cssOrder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssOrder">${csScheduling.cssOrder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/service/order_details.do?key=${csScheduling.cssOrder}',{ctrl:{editable:false,visible:true}})">
			 ${csScheduling.cssOrder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssOrder}
	</s:if>
	
	${lz:set("注释","*****************操作人字段的输入框代码*****************")}
	${lz:set("注释","before$cssOperator和after$cssOperator变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssOperator==true">
	${before$cssOperator}
	<dl class="cssOperator " minor  ref="cssOperator" >
		<dt>操&nbsp;&nbsp;作&nbsp;&nbsp;人:</dt>
		<s:if test="#request.CTRL.e.cssOperator==true">
		${lz:set("haveEditable",true)}
		<dd input="generic">
		<s:if test="#request.csScheduling$cssOperator!=null">${csScheduling$cssOperator}</s:if><s:else>
		 	<input title="请输入动态查询" class="combox narrow" generic="cssOperatorGeneric" size="16" type="text" id="cssOperator" name="csScheduling.cssOperator" text="${csScheduling.cssOperator$}" value="${csScheduling.cssOperator}"/>
		 	${lz:set("models",get:models("SrvUser,CsMember"))}
		 	<select id="cssOperatorGeneric" style="display:none;">
		 		<option value="">--选择模块--</option>
		 		<s:iterator value="#request.models" id="item" status="i">
		 		<option value="${item.name}:${basePath}admin/${item.namespace}_query.do?value={param}">${item.caption}</option>
		 		</s:iterator>
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="lookup('#${NAME}')"></a>
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****操作人字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssOperator">${csScheduling.cssOperator}</textarea>
		 		<span>
		 	
			 ${csScheduling.cssOperator$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssOperator}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cssStatus和after$cssStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cssStatus==true">
	${before$cssStatus}
	<dl class="cssStatus " major  ref="cssStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cssStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csScheduling$cssStatus!=null">${csScheduling$cssStatus}</s:if><s:else>
		 	<select class="narrow" id="cssStatus" name="csScheduling.cssStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csScheduling.cssStatus==0?"selected":""}>待调</option>
				<option value="1" ${csScheduling.cssStatus==1?"selected":""}>已调</option>
				<option value="2" ${csScheduling.cssStatus==2?"selected":""}>取消</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆调度的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cssStatus">${csScheduling.cssStatus}</textarea>
		 		<span>
		 	
			 ${csScheduling.cssStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cssStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csScheduling.cssId}" name="csScheduling.cssId" id="cssId" />
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