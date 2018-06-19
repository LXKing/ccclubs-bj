<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/oa/outside/outsideqr.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/oa/outside/outsideqr.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csOutsideQr.csoqId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csoqId,csoqHost,csoqCar,csoqType,csoqUserPosStart,csoqCarPosStart,csoqDistanceStart,csoqUsePosEnd,csoqCarPosEnd,csoqDistanceEnd,csoqImages,csoqEndTime,csoqTimeLong,csoqRemark,csoqStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csoqId,csoqHost,csoqCar,csoqType,csoqUserPosStart,csoqCarPosStart,csoqDistanceStart,csoqUsePosEnd,csoqCarPosEnd,csoqDistanceEnd,csoqImages,csoqEndTime,csoqTimeLong,csoqRemark,csoqStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csoqId,csoqHost,csoqCar,csoqType,csoqUserPosStart,csoqCarPosStart,csoqDistanceStart,csoqUsePosEnd,csoqCarPosEnd,csoqDistanceEnd,csoqImages,csoqEndTime,csoqTimeLong,csoqRemark,csoqStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csoqId,csoqHost,csoqAdder,csoqCar,csoqType,csoqUserPosStart,csoqCarPosStart,csoqDistanceStart,csoqUsePosEnd,csoqCarPosEnd,csoqDistanceEnd,csoqImages,csoqEndTime,csoqTimeLong,csoqRemark,csoqStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"巡检作业编辑":CTRL.title}</title>
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
	$("#outsideqrForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csoqId==true">
		${lz:set("haveEditable",true)}
		,"csOutsideQr.csoqId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoqHost==true">
		${lz:set("haveEditable",true)}
		,"csOutsideQr.csoqHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoqAdder==true">
		${lz:set("haveEditable",true)}
		,"csOutsideQr.csoqAdder":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoqCar==true">
		${lz:set("haveEditable",true)}
		,"csOutsideQr.csoqCar":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoqType==true">
		${lz:set("haveEditable",true)}
		,"csOutsideQr.csoqType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择作业类型";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoqUserPosStart==true">
		${lz:set("haveEditable",true)}
		,"csOutsideQr.csoqUserPosStart":function(el){
			if(jQuery.trim(el.value)=="")
				return "人员位置不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "人员位置最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoqCarPosStart==true">
		${lz:set("haveEditable",true)}
		,"csOutsideQr.csoqCarPosStart":function(el){
			if(jQuery.trim(el.value)=="")
				return "车辆位置不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "车辆位置最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoqDistanceStart==true">
		${lz:set("haveEditable",true)}
		,"csOutsideQr.csoqDistanceStart":function(el){
			if(jQuery.trim(el.value)=="")
				return "人车距离不能为空";
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoqUsePosEnd==true">
		${lz:set("haveEditable",true)}
		,"csOutsideQr.csoqUsePosEnd":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "人员位置最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoqCarPosEnd==true">
		${lz:set("haveEditable",true)}
		,"csOutsideQr.csoqCarPosEnd":function(el){
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "车辆位置最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoqDistanceEnd==true">
		${lz:set("haveEditable",true)}
		,"csOutsideQr.csoqDistanceEnd":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoqImages==true">
		${lz:set("haveEditable",true)}
		,"csOutsideQr.csoqImages":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoqAddTime==true">
		${lz:set("haveEditable",true)}
		,"csOutsideQr.csoqAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "开始时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoqEndTime==true">
		${lz:set("haveEditable",true)}
		,"csOutsideQr.csoqEndTime":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoqTimeLong==true">
		${lz:set("haveEditable",true)}
		,"csOutsideQr.csoqTimeLong":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoqRemark==true">
		${lz:set("haveEditable",true)}
		,"csOutsideQr.csoqRemark":function(el){
			if(el.value.length>256 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为256个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csoqStatus==true">
		${lz:set("haveEditable",true)}
		,"csOutsideQr.csoqStatus":function(el){
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
	<form class="form editform" ref="CsOutsideQr" id="outsideqrForm" name="outsideqrForm" action="${empty CTRL.action?"outsideqr_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写巡检作业相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csOutsideQrToken}" name="csOutsideQrToken" id="csOutsideQrToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csOutsideQr.csoqId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csoqId和after$csoqId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoqId==true">
	${before$csoqId}
	<dl class="csoqId ${CTRL.e.csoqId?"hidden":""}" major  ref="csoqId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csoqId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csOutsideQr$csoqId!=null">${csOutsideQr$csoqId}</s:if><s:else>
			<input type="hidden" value="${csOutsideQr.csoqId}" name="csOutsideQr.csoqId" id="csoqId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoqId">${csOutsideQr.csoqId}</textarea>
		 		<span>
		 	
			 ${csOutsideQr.csoqId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoqId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csoqHost和after$csoqHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoqHost==true">
	${before$csoqHost}
	<dl class="csoqHost " major  ref="csoqHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csoqHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOutsideQr$csoqHost!=null">${csOutsideQr$csoqHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csoqHost" name="csOutsideQr.csoqHost">
		 				<option selected value="${csOutsideQr.csoqHost}">
		 					${get:SrvHost(csOutsideQr.csoqHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csoqHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csoqHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoqHost">${csOutsideQr.csoqHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csOutsideQr.csoqHost}',{ctrl:{editable:false,visible:true}})">
			 ${csOutsideQr.csoqHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoqHost}
	</s:if>
	
	${lz:set("注释","*****************人员字段的输入框代码*****************")}
	${lz:set("注释","before$csoqAdder和after$csoqAdder变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoqAdder==true">
	${before$csoqAdder}
	<dl class="csoqAdder " major  ref="csoqAdder" >
		<dt>人　　员:</dt>
		<s:if test="#request.CTRL.e.csoqAdder==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csOutsideQr$csoqAdder!=null">${csOutsideQr$csoqAdder}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/user_tree.do" id="csoqAdder" name="csOutsideQr.csoqAdder">
		 				<option selected value="${csOutsideQr.csoqAdder}">
		 					${get:SrvUser(csOutsideQr.csoqAdder).suRealName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csoqAdder').val())==''){return;};window.href('${basePath}${proname}/permissions/user_details.do?key='+$('#csoqAdder').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****人员字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoqAdder">${csOutsideQr.csoqAdder}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/user_details.do?key=${csOutsideQr.csoqAdder}',{ctrl:{editable:false,visible:true}})">
			 ${csOutsideQr.csoqAdder$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoqAdder}
	</s:if>
	
	${lz:set("注释","*****************车辆字段的输入框代码*****************")}
	${lz:set("注释","before$csoqCar和after$csoqCar变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoqCar==true">
	${before$csoqCar}
	<dl class="csoqCar " major  ref="csoqCar" >
		<dt>车　　辆:</dt>
		<s:if test="#request.CTRL.e.csoqCar==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csOutsideQr$csoqCar!=null">${csOutsideQr$csoqCar}</s:if><s:else>
		 			<input title="请输入车辆车牌号进行查询" class="combox narrow" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={csoqHost}" type="text" id="csoqCar" name="csOutsideQr.csoqCar" text="${get:CsCar(csOutsideQr.csoqCar).cscNumber}" value="${csOutsideQr.csoqCar}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csoqCar').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?key='+$('#csoqCar').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车辆字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoqCar">${csOutsideQr.csoqCar}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/car_details.do?key=${csOutsideQr.csoqCar}',{ctrl:{editable:false,visible:true}})">
			 ${csOutsideQr.csoqCar$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoqCar}
	</s:if>
	
	${lz:set("注释","*****************作业类型字段的输入框代码*****************")}
	${lz:set("注释","before$csoqType和after$csoqType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoqType==true">
	${before$csoqType}
	<dl class="csoqType " major  ref="csoqType" >
		<dt>作业类型:</dt>
		<s:if test="#request.CTRL.e.csoqType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOutsideQr$csoqType!=null">${csOutsideQr$csoqType}</s:if><s:else>
		 	<select class="narrow" id="csoqType" name="csOutsideQr.csoqType">
		 		<option value="">请选择</option>
 					${lz:set("items", get:propertys("巡检作业类型","outsideqr_type"))}
					<s:iterator value="#request.items" id="item" status="i">
					<option value="${item.spId}" ${csOutsideQr.csoqType==item.spId?"selected":""}>${item.spName}</option>
					</s:iterator>
					${lz:set("items",null)}
		 	</select>
		 	<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csoqType').val())==''){return;};window.href('${basePath}${proname}/configurator/property_details.do?key='+$('#csoqType').val(),{ctrl:{editable:false}})"></a>
	 			<a class="reladd" config="{
	 				action:'${basePath}${proname}/configurator/property_edit.do?rd=1&parent=${get:property("outsideqr_type").spId}'
	 				,callback:function(object){
	 					$('<option selected value='+object.spId+'>'+object.spName+'</option>').appendTo('#csoqType');
	 				}
	 			}" href="javascript:void(0);"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择巡检作业的作业类型</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****作业类型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoqType">${csOutsideQr.csoqType}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/property_details.do?key=${csOutsideQr.csoqType}',{ctrl:{editable:false,visible:true}})">
			 ${csOutsideQr.csoqType$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoqType}
	</s:if>
	
	${lz:set("注释","*****************人员位置字段的输入框代码*****************")}
	${lz:set("注释","before$csoqUserPosStart和after$csoqUserPosStart变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoqUserPosStart==true">
	${before$csoqUserPosStart}
	<dl class="csoqUserPosStart " major  ref="csoqUserPosStart" >
		<dt>人员位置:</dt>
		<s:if test="#request.CTRL.e.csoqUserPosStart==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOutsideQr$csoqUserPosStart!=null">${csOutsideQr$csoqUserPosStart}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOutsideQr.csoqUserPosStart" id="csoqUserPosStart"  value="${csOutsideQr.csoqUserPosStart}"/>
	 	 </s:else>
	 	 开始
	 	 <b>*</b>
	 	 <em>请输入巡检作业的人员位置</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****人员位置字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoqUserPosStart">${csOutsideQr.csoqUserPosStart}</textarea>
		 		<span>
		 	
			 ${csOutsideQr.csoqUserPosStart$}
	 	 开始 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoqUserPosStart}
	</s:if>
	
	${lz:set("注释","*****************车辆位置字段的输入框代码*****************")}
	${lz:set("注释","before$csoqCarPosStart和after$csoqCarPosStart变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoqCarPosStart==true">
	${before$csoqCarPosStart}
	<dl class="csoqCarPosStart " major  ref="csoqCarPosStart" >
		<dt>车辆位置:</dt>
		<s:if test="#request.CTRL.e.csoqCarPosStart==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOutsideQr$csoqCarPosStart!=null">${csOutsideQr$csoqCarPosStart}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOutsideQr.csoqCarPosStart" id="csoqCarPosStart"  value="${csOutsideQr.csoqCarPosStart}"/>
	 	 </s:else>
	 	 开始
	 	 <b>*</b>
	 	 <em>请输入巡检作业的车辆位置</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车辆位置字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoqCarPosStart">${csOutsideQr.csoqCarPosStart}</textarea>
		 		<span>
		 	
			 ${csOutsideQr.csoqCarPosStart$}
	 	 开始 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoqCarPosStart}
	</s:if>
	
	${lz:set("注释","*****************人车距离字段的输入框代码*****************")}
	${lz:set("注释","before$csoqDistanceStart和after$csoqDistanceStart变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoqDistanceStart==true">
	${before$csoqDistanceStart}
	<dl class="csoqDistanceStart " major  ref="csoqDistanceStart" >
		<dt>人车距离:</dt>
		<s:if test="#request.CTRL.e.csoqDistanceStart==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csOutsideQr$csoqDistanceStart!=null">${csOutsideQr$csoqDistanceStart}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOutsideQr.csoqDistanceStart" id="csoqDistanceStart"  value="${csOutsideQr.csoqDistanceStart}"/>
	 	 </s:else>
	 	 米/开始
	 	 <b>*</b>
	 	 <em>请输入巡检作业的人车距离</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****人车距离字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoqDistanceStart">${csOutsideQr.csoqDistanceStart}</textarea>
		 		<span>
		 	
			 ${csOutsideQr.csoqDistanceStart$}
	 	 米/开始 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoqDistanceStart}
	</s:if>
	
	${lz:set("注释","*****************人员位置字段的输入框代码*****************")}
	${lz:set("注释","before$csoqUsePosEnd和after$csoqUsePosEnd变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoqUsePosEnd==true">
	${before$csoqUsePosEnd}
	<dl class="csoqUsePosEnd " minor  ref="csoqUsePosEnd" >
		<dt>人员位置:</dt>
		<s:if test="#request.CTRL.e.csoqUsePosEnd==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOutsideQr$csoqUsePosEnd!=null">${csOutsideQr$csoqUsePosEnd}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOutsideQr.csoqUsePosEnd" id="csoqUsePosEnd"  value="${csOutsideQr.csoqUsePosEnd}"/>
	 	 </s:else>
	 	 完成
	 	 
	 	 <em>请输入巡检作业的人员位置</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****人员位置字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoqUsePosEnd">${csOutsideQr.csoqUsePosEnd}</textarea>
		 		<span>
		 	
			 ${csOutsideQr.csoqUsePosEnd$}
	 	 完成 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoqUsePosEnd}
	</s:if>
	
	${lz:set("注释","*****************车辆位置字段的输入框代码*****************")}
	${lz:set("注释","before$csoqCarPosEnd和after$csoqCarPosEnd变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoqCarPosEnd==true">
	${before$csoqCarPosEnd}
	<dl class="csoqCarPosEnd " minor  ref="csoqCarPosEnd" >
		<dt>车辆位置:</dt>
		<s:if test="#request.CTRL.e.csoqCarPosEnd==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csOutsideQr$csoqCarPosEnd!=null">${csOutsideQr$csoqCarPosEnd}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csOutsideQr.csoqCarPosEnd" id="csoqCarPosEnd"  value="${csOutsideQr.csoqCarPosEnd}"/>
	 	 </s:else>
	 	 完成
	 	 
	 	 <em>请输入巡检作业的车辆位置</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车辆位置字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoqCarPosEnd">${csOutsideQr.csoqCarPosEnd}</textarea>
		 		<span>
		 	
			 ${csOutsideQr.csoqCarPosEnd$}
	 	 完成 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoqCarPosEnd}
	</s:if>
	
	${lz:set("注释","*****************人车距离字段的输入框代码*****************")}
	${lz:set("注释","before$csoqDistanceEnd和after$csoqDistanceEnd变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoqDistanceEnd==true">
	${before$csoqDistanceEnd}
	<dl class="csoqDistanceEnd " major  ref="csoqDistanceEnd" >
		<dt>人车距离:</dt>
		<s:if test="#request.CTRL.e.csoqDistanceEnd==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csOutsideQr$csoqDistanceEnd!=null">${csOutsideQr$csoqDistanceEnd}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOutsideQr.csoqDistanceEnd" id="csoqDistanceEnd"  value="${csOutsideQr.csoqDistanceEnd}"/>
	 	 </s:else>
	 	 米/完成
	 	 
	 	 <em>请输入巡检作业的人车距离</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****人车距离字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoqDistanceEnd">${csOutsideQr.csoqDistanceEnd}</textarea>
		 		<span>
		 	
			 ${csOutsideQr.csoqDistanceEnd$}
	 	 米/完成 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoqDistanceEnd}
	</s:if>
	
	${lz:set("注释","*****************图片列表字段的输入框代码*****************")}
	${lz:set("注释","before$csoqImages和after$csoqImages变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoqImages==true">
	${before$csoqImages}
	<dl class="csoqImages " minor  ref="csoqImages" style="width:98%;">
		<dt>图片列表:</dt>
		<s:if test="#request.CTRL.e.csoqImages==true">
		${lz:set("haveEditable",true)}
		<dd input="imagelist">
		<s:if test="#request.csOutsideQr$csoqImages!=null">${csOutsideQr$csoqImages}</s:if><s:else>
		    <div id="csoqImages" class="wide input imagelist">
			<s:generator val="csOutsideQr.csoqImages" separator=",">
				<s:iterator id="item">
					${lz:set("isVacant",lz:vacant(item))}
		 			<s:if test="#request.isVacant==false">
					<div><a href="${item}" target="_blank"><img src="${item}"/><input type="hidden" name="csOutsideQr.csoqImages" value="${item}"/></a><span onclick="$(this).parent().remove();">x</span></div>
					</s:if>
				</s:iterator>
			</s:generator>
			</div>
			<button type="button" onclick="$.upload({type:'img',count:10,callback:function(url){if(url)$('#csoqImages').append('<div><a href=&quot;'+url+'&quot; target=&quot;_blank&quot;><img src=&quot;'+url+'&quot;/><input type=&quot;hidden&quot; name=&quot;csOutsideQr.csoqImages&quot; value=&quot;'+url+'&quot;/></a><span onclick=&quot;$(this).parent().remove();&quot;>x</span></div>');}})" class="button">添加</button>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请上传巡检作业的图片列表</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****图片列表字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csoqImages">${csOutsideQr.csoqImages}</textarea>
		 		<span>
		 	 <s:generator val="csOutsideQr.csoqImages" separator=",">
		 	 	<s:iterator id="item">
					${lz:set("isVacant",lz:vacant(item))}
		 			<s:if test="#request.isVacant==false">
					<img onclick="$.thumb({url:this.src})" style="margin:10px;padding:1px;border:1px solid;" onload="if(this.width>this.height){this.width=96}else{this.height=96}" src="<s:property/>"/>
					</s:if>
				</s:iterator>
			 </s:generator>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoqImages}
	</s:if>
	
	${lz:set("注释","*****************开始时间字段的输入框代码*****************")}
	${lz:set("注释","before$csoqAddTime和after$csoqAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoqAddTime==true">
	${before$csoqAddTime}
	<dl class="csoqAddTime " major  ref="csoqAddTime" >
		<dt>开始时间:</dt>
		<s:if test="#request.CTRL.e.csoqAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOutsideQr$csoqAddTime!=null">${csOutsideQr$csoqAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOutsideQr.csoqAddTime" id="csoqAddTime"  value="<s:date name="csOutsideQr.csoqAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择巡检作业的开始时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****开始时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoqAddTime">${csOutsideQr.csoqAddTime}</textarea>
		 		<span>
		 	
			 ${csOutsideQr.csoqAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoqAddTime}
	</s:if>
	
	${lz:set("注释","*****************结束时间字段的输入框代码*****************")}
	${lz:set("注释","before$csoqEndTime和after$csoqEndTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoqEndTime==true">
	${before$csoqEndTime}
	<dl class="csoqEndTime " major  ref="csoqEndTime" >
		<dt>结束时间:</dt>
		<s:if test="#request.CTRL.e.csoqEndTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csOutsideQr$csoqEndTime!=null">${csOutsideQr$csoqEndTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csOutsideQr.csoqEndTime" id="csoqEndTime"  value="<s:date name="csOutsideQr.csoqEndTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择巡检作业的结束时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****结束时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoqEndTime">${csOutsideQr.csoqEndTime}</textarea>
		 		<span>
		 	
			 ${csOutsideQr.csoqEndTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoqEndTime}
	</s:if>
	
	${lz:set("注释","*****************作业时长字段的输入框代码*****************")}
	${lz:set("注释","before$csoqTimeLong和after$csoqTimeLong变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoqTimeLong==true">
	${before$csoqTimeLong}
	<dl class="csoqTimeLong " major  ref="csoqTimeLong" >
		<dt>作业时长:</dt>
		<s:if test="#request.CTRL.e.csoqTimeLong==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csOutsideQr$csoqTimeLong!=null">${csOutsideQr$csoqTimeLong}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csOutsideQr.csoqTimeLong" id="csoqTimeLong"  value="${csOutsideQr.csoqTimeLong}"/>
	 	 </s:else>
	 	 分钟
	 	 
	 	 <em>请输入巡检作业的作业时长</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****作业时长字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoqTimeLong">${csOutsideQr.csoqTimeLong}</textarea>
		 		<span>
		 	
			 ${csOutsideQr.csoqTimeLong$}
	 	 分钟 
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoqTimeLong}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$csoqRemark和after$csoqRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoqRemark==true">
	${before$csoqRemark}
	<dl class="csoqRemark " minor  ref="csoqRemark" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.csoqRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csOutsideQr$csoqRemark!=null">${csOutsideQr$csoqRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csoqRemark" name="csOutsideQr.csoqRemark" rows="5">${csOutsideQr.csoqRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入巡检作业的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csOutsideQr.csoqRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoqRemark}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csoqStatus和after$csoqStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csoqStatus==true">
	${before$csoqStatus}
	<dl class="csoqStatus " major  ref="csoqStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csoqStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csOutsideQr$csoqStatus!=null">${csOutsideQr$csoqStatus}</s:if><s:else>
		 	<select class="narrow" id="csoqStatus" name="csOutsideQr.csoqStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csOutsideQr.csoqStatus==1?"selected":""}>正常</option>
				<option value="0" ${csOutsideQr.csoqStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择巡检作业的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csoqStatus">${csOutsideQr.csoqStatus}</textarea>
		 		<span>
		 	
			 ${csOutsideQr.csoqStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csoqStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csOutsideQr.csoqId}" name="csOutsideQr.csoqId" id="csoqId" />
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