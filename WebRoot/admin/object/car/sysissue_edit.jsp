<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/object/car/carparts.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/object/car/carparts.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csCarSysIssue.cscsiId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"cscsiHost,cscsiCar,cscsiParts,cscsiContent,cscsiKeywords,cscsiImage,cscsiType,cscsiStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscsiHost,cscsiCar,cscsiParts,cscsiContent,cscsiKeywords,cscsiImage,cscsiType,cscsiStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"cscsiHost,cscsiCar,cscsiParts,cscsiContent,cscsiKeywords,cscsiImage,cscsiType,cscsiStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"cscsiId,cscsiHost,cscsiCar,cscsiParts,cscsiContent,cscsiKeywords,cscsiImage,cscsiType,cscsiStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"车况编辑":CTRL.title}</title>
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
	$("#sysissueForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.cscsiId==true">
		${lz:set("haveEditable",true)}
		,"csCarSysIssue.cscsiId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsiHost==true">
		${lz:set("haveEditable",true)}
		,"csCarSysIssue.cscsiHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsiParts==true">
		${lz:set("haveEditable",true)}
		,"csCarSysIssue.cscsiParts":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsiCar==true">
		${lz:set("haveEditable",true)}
		,"csCarSysIssue.cscsiCar":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.cscsiKeywords==true">
		${lz:set("haveEditable",true)}
		,"csCarSysIssue.cscsiKeywords":function(el){
			if(jQuery.trim(el.value)=="")
				return "请输入标签";
			if(jQuery.trim(el.value).length > 10 )
				return "限制10个字";
		}
		</s:if>
		<s:if test="#request.CTRL.e.cscsiContent==true">
		${lz:set("haveEditable",true)}
		,"csCarSysIssue.cscsiContent":function(el){
			if(jQuery.trim(el.value)=="")
				return "请输入内容";
		}
		</s:if>
		
		<s:if test="#request.CTRL.e.cscsiType==true">
		${lz:set("haveEditable",true)}
		,"csCarSysIssue.cscsiType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择类型";
		}
		</s:if>
		
		<s:if test="#request.CTRL.e.cscsiStatus==true">
		${lz:set("haveEditable",true)}
		,"csCarSysIssue.cscsiStatus":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择数据状态";
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
	<form class="form editform" ref="csCarSysIssue" id="sysissueForm" name="sysissueForm" action="${empty CTRL.action?"sysissue_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写车况相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csCarSysIssueToken}" name="csCarSysIssueToken" id="csCarSysIssueToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csCarSysIssue.cscsiId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$cscsiId和after$cscsiId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsiId==true">
	${before$cscsiId}
	<dl class="cscsiId ${CTRL.e.cscsiId?"hidden":""}" major  ref="cscsiId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.cscsiId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csCarSysIssue$cscsiId!=null">${csCarSysIssue$cscsiId}</s:if><s:else>
			<input type="hidden" value="${csCarSysIssue.cscsiId}" name="csCarSysIssue.cscsiId" id="cscsiId" />
	 	 </s:else>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsiId">${csCarSysIssue.cscsiId}</textarea>
		 		<span>
		 	
			 ${csCarSysIssue.cscsiId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsiId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$cscsiHost和after$cscsiHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsiHost==true">
	${before$cscsiHost}
	<dl class="cscsiHost " major  ref="cscsiHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.cscsiHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCarSysIssue$cscsiHost!=null">${csCarSysIssue$cscsiHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="cscsiHost" name="csCarSysIssue.cscsiHost">
		 				<option selected value="${csCarSysIssue.cscsiHost}">
		 					${get:SrvHost(csCarSysIssue.cscsiHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscsiHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#cscsiHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择城市</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsiHost">${csCar.cscsiHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csCarSysIssue.cscsiHost}',{ctrl:{editable:false,visible:true}})">
			 ${csCarSysIssue.cscsiHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsiHost}
	</s:if>
	
	
	${lz:set("注释","*****************车辆字段的输入框代码*****************")}
	${lz:set("注释","before$cscsiCar和after$cscsiCar变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsiCar==true">
	${before$cscsiCar}
	<dl class="cscsiCar " minor  ref="cscsiCar" >
		<dt>车辆:</dt>
		<s:if test="#request.CTRL.e.cscsiCar==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csCarSysIssue$cscsiCar!=null">${csCarSysIssue$cscsiCar}</s:if><s:else>
		 			<input title="请输入车辆车牌号进行查询" class="combox narrow" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={cscsiHost}" type="text" id="cscsiCar" name="csCarSysIssue.cscsiCar" text="${get:CsCar(csCarSysIssue.cscsiCar).cscNumber}" value="${csCarSysIssue.cscsiCar}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscsiCar').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?key='+$('#cscsiCar').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****预订车辆字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsiCar">${csCarSysIssue.cscsiCar}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/car_details.do?key=${csCarSysIssue.cscsiCar}',{ctrl:{editable:false,visible:true}})">
			 ${csCarSysIssue.cscsiCar$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsiCar}
	</s:if>
	${lz:set("注释","*****************管理员字段的输入框代码*****************")}
	${lz:set("注释","before$cscsiSrvuser和after$cscsiSrvuser变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsiSrvuser==true">
	${before$cscsiSrvuser}
	<dl class="cscsiSrvuser " major  ref="cscsiSrvuser" >
		<dt>操作员:</dt>
		<s:if test="#request.CTRL.e.cscsiSrvuser==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csCarSysIssue$cscsiSrvuser!=null">${csCarSysIssue$cscsiSrvuser}</s:if><s:else>
		 			<input title="请输入车辆车牌号进行查询" class="combox narrow" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={csuoHost}" type="text" id="cscsiSrvuser" name="csCarSysIssue.cscsiSrvuser" text="${get:CsCar(csCarSysIssue.cscsiSrvuser).cscNumber}" value="${csCarSysIssue.cscsiSrvuser}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscsiCar').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?key='+$('#cscsiSrvuser').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****支付帐号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsiSrvuser">${csCarSysIssue.cscsiSrvuser}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/user/member_details.do?key=${csCarSysIssue.cscsiSrvuser}',{ctrl:{editable:false,visible:true}})">
			 ${csCarSysIssue.cscsiSrvuser$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsiSrvuser}
	</s:if>
	
	
	${lz:set("注释","*****************名称字段的输入框代码*****************")}
	${lz:set("注释","before$cscsiParts和after$cscsiParts变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsiParts==true">
	${before$cscsiParts}
	<dl class="cscsiParts " major  ref="cscsiParts" >
		<dt>部位:</dt>
		<s:if test="#request.CTRL.e.cscsiParts==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csCarSysIssue$cscsiParts!=null">${csCarSysIssue$cscsiParts}</s:if><s:else>
		 	<input title="请输入部位名称进行查询" class="combox narrow" action="${basePath}${proname}/object/car/carparts_query.do?value={param}" type="text" id="cscsiParts" name="csCarSysIssue.cscsiParts" text="${get:CsCarParts(csCarParts.cscpParentId).cscpName}" value="${csCarSysIssue.cscsiParts}" />
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#cscpParentId').val())==''){return;};window.href('${basePath}${proname}/object/car/carparts_details.do?key='+$('#cscpParentId').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择部位</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属车辆字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsiParts">${csCarSysIssue.cscsiParts}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/car_details.do?key=${csCarSysIssue.cscsiParts}',{ctrl:{editable:false,visible:true}})">
			 ${csCarSysIssue.cscsiParts$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsiParts}
	</s:if>
	
	${lz:set("注释","*****************名称字段的输入框代码*****************")}
	${lz:set("注释","before$cscsiPartsStr和after$cscsiPartsStr变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsiPartsStr==true">
	${before$cscsiPartsStr}
	<dl class="cscsiPartsStr " major  ref="cscsiPartsStr" >
		<dt>部位名称:</dt>
		<s:if test="#request.CTRL.e.cscsiPartsStr==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		 		<s:if test="#request.csCarSysIssue$cscsiPartsStr!=null">${csCarSysIssue$cscsiPartsStr}</s:if><s:else>
		 		 <input title="请输入部位名称" class="input narrow" type="text" id="cscsiPartsStr" name="csCarSysIssue.cscsiPartsStr" value="${csCarSysIssue.cscsiPartsStr}" />
		 		
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所属车辆字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsiParts">${csCarSysIssue.cscsiPartsStr}</textarea>
		 		<span>
		 	
			 ${csCarSysIssue.cscsiPartsStr$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsiPartsStr}
	</s:if>
	
	${lz:set("注释","*****************内容字段的输入框代码*****************")}
	${lz:set("注释","before$cscsiContent和after$cscsiContent变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsiContent==true">
	${before$cscsiContent}
	<dl class="cscsiContent " major  ref="cscsiContent" style="width:98%;">
		<dt>损坏情况:</dt>
		<s:if test="#request.CTRL.e.cscsiContent==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csCarSysIssue$cscsiContent!=null">${csCarSysIssue$cscsiContent}</s:if><s:else>
		 	<textarea class="input wide"  id="cscsiContent" name="csCarSysIssue.cscsiContent" rows="5">${csCarSysIssue.cscsiContent}</textarea>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入内容</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****内容字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csCarSysIssue.cscsiContent$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsiContent}
	</s:if>
	
	
	${lz:set("注释","*****************关键字字段的输入框代码*****************")}
	${lz:set("注释","before$cscsiKeywords和after$cscsiKeywords变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsiKeywords==true">
	${before$cscsiKeywords}
	<dl class="cscsiKeywords " minor  ref="cscsiKeywords" style="width:98%;">
		<dt>车况标签:</dt>
		<s:if test="#request.CTRL.e.cscsiKeywords==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csCarSysIssue$cscsiKeywords!=null">${csCarSysIssue$cscsiKeywords}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csCarSysIssue.cscsiKeywords" id="cscsiKeywords"  value="${csCarSysIssue.cscsiKeywords}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入简洁明了的文字描述车辆损坏情况（限制10个字）。</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****关键字字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscsiKeywords">${csCarSysIssue.cscsiKeywords}</textarea>
		 		<span>
		 	
			 ${csCarSysIssue.cscsiKeywords$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsiKeywords}
	</s:if>
	
	${lz:set("注释","*****************车型图片字段的输入框代码*****************")}
	${lz:set("注释","before$cscsiImage和after$cscsiImage变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsiImage==true">
	${before$cscsiImage}
	<dl class="cscsiImage " minor  ref="cscsiImage" style="width:98%;">
		<dt>车况图片:</dt>
		<s:if test="#request.CTRL.e.cscsiImage==true">
		${lz:set("haveEditable",true)}
		<dd input="image">
		<s:if test="#request.csCarSysIssue$cscsiImage!=null">${csCarSysIssue$cscsiImage}</s:if><s:else>
		    <input type="text" class="input"  size="70" name="csCarSysIssue.cscsiImage" id="cscsiImage"  value="${csCarSysIssue.cscsiImage}"/>
			<button type="button" onclick="$.upload({type:'img',callback:function(url){if(url)$('#cscsiImage').val(url+','+$('#cscsiImage').val())}})" class="button">上传图片</button>
			<button type="button" onclick="$('#cscsiImage').val('')" class="button">删除图片</button>
			<%--<button type="button" onclick="$.thumb({url:$('#cscsiImage').val()})" class="button">查看图片</button>--%>
		</s:else>
	 	 
	 	 
	 	 <em>图片建议尺寸500*300像素（比例）</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车型图片字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="cscsiImage">${csCarSysIssue.cscsiImage}</textarea>
		 		<span>
		 	${lz:set("isVacant",lz:vacant(csCarSysIssue.cscsiImage))}
		 	<s:if test="#request.isVacant==false">
		 		<s:generator separator="," val="csCarSysIssue.cscsiImage">  
		        <s:iterator> 
		        	<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="<s:property/>"/>
		             
		          
		        </s:iterator>  
		     </s:generator>
		 	  <br/> 
		 	</s:if>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsiImage}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cscsiType和after$cscsiType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsiType==true">
	${before$cscsiType}
	<dl class="cscsiContent " major  ref="cscsiType" >
		<dt>类别:</dt>
		<s:if test="#request.CTRL.e.cscsiType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCarSysIssue$cscsiType!=null">${csCarSysIssue$cscsiType}</s:if><s:else>
		 	<select class="narrow" id="cscsiType" name="csCarSysIssue.cscsiType">
		 		<option value="">请选择</option>
		 		<option value="1" ${csCarSysIssue.cscsiType==1?"selected":""}>刮痕</option>
				<option value="2" ${csCarSysIssue.cscsiType==2?"selected":""}>凹痕</option>
				<option value="3" ${csCarSysIssue.cscsiType==3?"selected":""}>损坏</option>
				<option value="4" ${csCarSysIssue.cscsiType==4?"selected":""}>裂痕</option>
				<option value="5" ${csCarSysIssue.cscsiType==5?"selected":""}>非正常工作</option>
				<option value="6" ${csCarSysIssue.cscsiType==6?"selected":""}>缺失零件</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择类别</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****类别字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsiType">${csCarSysIssue.cscsiType}</textarea>
		 		<span>
		 	
			 ${csCarSysIssue.cscsiType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsiType}
	</s:if>
	
	${lz:set("注释","*****************订单结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscsiAddTime和after$cscsiAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsiAddTime==true">
	${before$cscsiAddTime}
	<dl class="cscsiAddTime " major  ref="cscsiAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.cscsiAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCarSysIssue$cscsiAddTime!=null">${csCarSysIssue$cscsiAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCarSysIssue.cscsiAddTime" id="cscsiAddTime"  value="<s:date name="csCarSysIssue.cscsiAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****订单结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsiAddTime">${csCarSysIssue.cscsiAddTime}</textarea>
		 		<span>
		 	
			 ${csCarSysIssue.cscsiAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsiAddTime}
	</s:if>
	
	${lz:set("注释","*****************修改时间字段的输入框代码*****************")}
	${lz:set("注释","before$cscsiUpdateTime和after$cscsiUpdateTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsiUpdateTime==true">
	${before$cscsiUpdateTime}
	<dl class="cscsiUpdateTime " major  ref="cscsiUpdateTime" >
		<dt>修改时间:</dt>
		<s:if test="#request.CTRL.e.cscsiUpdateTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csCarSysIssue$cscsiUpdateTime!=null">${csCarSysIssue$cscsiUpdateTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csCarSysIssue.cscsiUpdateTime" id="cscsiUpdateTime"  value="<s:date name="csCarSysIssue.cscsiUpdateTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请修改时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****修改时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsiUpdateTime">${csCarSysIssue.cscsiUpdateTime}</textarea>
		 		<span>
		 	
			 ${csCarSysIssue.cscsiUpdateTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsiUpdateTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$cscsiStatus和after$cscsiStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.cscsiStatus==true">
	${before$cscsiStatus}
	<dl class="cscsiStatus " major  ref="cscsiStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.cscsiStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csCarSysIssue$cscsiStatus!=null">${csCarSysIssue$cscsiStatus}</s:if><s:else>
		 	<select class="narrow" id="cscsiStatus" name="csCarSysIssue.cscsiStatus">
		 		<option value="">请选择</option>
		 		<option value="1" ${csCarSysIssue.cscsiStatus==1?"selected":""}>正常</option>
		 		<option value="0" ${csCarSysIssue.cscsiStatus==0?"selected":""}>禁用</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择数据状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="cscsiStatus">${csCarSysIssue.cscsiStatus}</textarea>
		 		<span>
		 	
			 ${csCarSysIssue.cscsiStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$cscsiStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csCarSysIssue.cscsiId}" name="csCarSysIssue.cscsiId" id="cscsiId" />
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