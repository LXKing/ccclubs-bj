<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/configurator/gasstation.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/configurator/gasstation.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csGasStation.csgsId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csgsId,csgsHost,csgsName,csgsArea,csgsLocation,csgsAddress,csgsStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csgsId,csgsHost,csgsName,csgsArea,csgsLocation,csgsAddress,csgsStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csgsId,csgsHost,csgsName,csgsArea,csgsLocation,csgsAddress,csgsStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csgsId,csgsHost,csgsName,csgsArea,csgsLocation,csgsAddress,csgsStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"加油站编辑":CTRL.title}</title>
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



<!--LAZY3Q_JSP_BODY-->
<!-- soso地图api -->
<%@include file="/admin/map.point.jsp"%>
<script>
$(function(){
	$("#csgsLocation").focus(function(){
		$(this).blur();		
		$.getMapPoint(function(Latitude,Longitude){
			$("#csgsLocation").val(Latitude+","+Longitude);
		},$.trim($("#csgsLocation").val())==""?{}:{
			Latitude:Number($("#csgsLocation").val().split(",")[0]),
			Longitude:Number($("#csgsLocation").val().split(",")[1])
		});
	});
});
</script>
<!--LAZY3Q_JSP_BODY-->



<script>
$(function(){	
	showTips("${tips.value}");
});
window["lzFlashUrl"]="${ lz:config("lz.flash.url")==null ? basePath : ""}${ lz:config("lz.flash.url")==null ? "admin/flash/" : lz:config("lz.flash.url")}";
window["uploadUrl"]="${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}";


$(function(){
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#gasstationForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csgsId==true">
		${lz:set("haveEditable",true)}
		,"csGasStation.csgsId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgsHost==true">
		${lz:set("haveEditable",true)}
		,"csGasStation.csgsHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgsName==true">
		${lz:set("haveEditable",true)}
		,"csGasStation.csgsName":function(el){
			if(jQuery.trim(el.value)=="")
				return "名称不能为空";
			if(el.value.length>32 && el.value.indexOf("[*]")==-1)
				return "名称最大长度为32个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgsArea==true">
		${lz:set("haveEditable",true)}
		,"csGasStation.csgsArea":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgsLocation==true">
		${lz:set("haveEditable",true)}
		,"csGasStation.csgsLocation":function(el){
			if(jQuery.trim(el.value)=="")
				return "位置信息不能为空";
			if(el.value.length>64 && el.value.indexOf("[*]")==-1)
				return "位置信息最大长度为64个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgsAddress==true">
		${lz:set("haveEditable",true)}
		,"csGasStation.csgsAddress":function(el){
			if(jQuery.trim(el.value)=="")
				return "地址不能为空";
			if(el.value.length>128 && el.value.indexOf("[*]")==-1)
				return "地址最大长度为128个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgsAddTime==true">
		${lz:set("haveEditable",true)}
		,"csGasStation.csgsAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csgsStatus==true">
		${lz:set("haveEditable",true)}
		,"csGasStation.csgsStatus":function(el){
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
	<form class="form editform" ref="CsGasStation" id="gasstationForm" name="gasstationForm" action="${empty CTRL.action?"gasstation_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写加油站相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csGasStationToken}" name="csGasStationToken" id="csGasStationToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csGasStation.csgsId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csgsId和after$csgsId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgsId==true">
	${before$csgsId}
	<dl class="csgsId ${CTRL.e.csgsId?"hidden":""}" major  ref="csgsId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csgsId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csGasStation$csgsId!=null">${csGasStation$csgsId}</s:if><s:else>
			<input type="hidden" value="${csGasStation.csgsId}" name="csGasStation.csgsId" id="csgsId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgsId">${csGasStation.csgsId}</textarea>
		 		<span>
		 	
			 ${csGasStation.csgsId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgsId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csgsHost和after$csgsHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgsHost==true">
	${before$csgsHost}
	<dl class="csgsHost " major  ref="csgsHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csgsHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csGasStation$csgsHost!=null">${csGasStation$csgsHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csgsHost" name="csGasStation.csgsHost">
		 				<option selected value="${csGasStation.csgsHost}">
		 					${get:SrvHost(csGasStation.csgsHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csgsHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csgsHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgsHost">${csGasStation.csgsHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csGasStation.csgsHost}',{ctrl:{editable:false,visible:true}})">
			 ${csGasStation.csgsHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgsHost}
	</s:if>
	
	${lz:set("注释","*****************名称字段的输入框代码*****************")}
	${lz:set("注释","before$csgsName和after$csgsName变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgsName==true">
	${before$csgsName}
	<dl class="csgsName " major  ref="csgsName" >
		<dt>名　　称:</dt>
		<s:if test="#request.CTRL.e.csgsName==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csGasStation$csgsName!=null">${csGasStation$csgsName}</s:if><s:else>
		 	<input type="text" class="input narrow"  maxlength="32" name="csGasStation.csgsName" id="csgsName"  value="${csGasStation.csgsName}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入加油站的名称</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****名称字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgsName">${csGasStation.csgsName}</textarea>
		 		<span>
		 	
			 ${csGasStation.csgsName$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgsName}
	</s:if>
	
	${lz:set("注释","*****************所在区域字段的输入框代码*****************")}
	${lz:set("注释","before$csgsArea和after$csgsArea变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgsArea==true">
	${before$csgsArea}
	<dl class="csgsArea " major  ref="csgsArea" >
		<dt>所在区域:</dt>
		<s:if test="#request.CTRL.e.csgsArea==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csGasStation$csgsArea!=null">${csGasStation$csgsArea}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/configurator/area_query.do?size=-1&csaHost={csgsHost}&csaHost={csgsHost}" id="csgsArea" name="csGasStation.csgsArea">
		 				<option selected value="${csGasStation.csgsArea}">
		 					${get:CsArea(csGasStation.csgsArea).csaName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csgsArea').val())==''){return;};window.href('${basePath}${proname}/configurator/area_details.do?key='+$('#csgsArea').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****所在区域字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgsArea">${csGasStation.csgsArea}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/configurator/area_details.do?key=${csGasStation.csgsArea}',{ctrl:{editable:false,visible:true}})">
			 ${csGasStation.csgsArea$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgsArea}
	</s:if>
	
	${lz:set("注释","*****************位置信息字段的输入框代码*****************")}
	${lz:set("注释","before$csgsLocation和after$csgsLocation变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgsLocation==true">
	${before$csgsLocation}
	<dl class="csgsLocation " major  ref="csgsLocation" style="width:98%;">
		<dt>位置信息:</dt>
		<s:if test="#request.CTRL.e.csgsLocation==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csGasStation$csgsLocation!=null">${csGasStation$csgsLocation}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="64" name="csGasStation.csgsLocation" id="csgsLocation"  value="${csGasStation.csgsLocation}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入加油站的位置信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****位置信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csgsLocation">${csGasStation.csgsLocation}</textarea>
		 		<span>
		 	
			 ${csGasStation.csgsLocation$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgsLocation}
	</s:if>
	
	${lz:set("注释","*****************地址字段的输入框代码*****************")}
	${lz:set("注释","before$csgsAddress和after$csgsAddress变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgsAddress==true">
	${before$csgsAddress}
	<dl class="csgsAddress " major  ref="csgsAddress" style="width:98%;">
		<dt>地　　址:</dt>
		<s:if test="#request.CTRL.e.csgsAddress==true">
		${lz:set("haveEditable",true)}
		<dd input="text">
		<s:if test="#request.csGasStation$csgsAddress!=null">${csGasStation$csgsAddress}</s:if><s:else>
		 	<input type="text" class="input wide"  maxlength="128" name="csGasStation.csgsAddress" id="csgsAddress"  value="${csGasStation.csgsAddress}"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请输入加油站的地址</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****地址字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<textarea class="" style="display:none;" id="csgsAddress">${csGasStation.csgsAddress}</textarea>
		 		<span>
		 	
			 ${csGasStation.csgsAddress$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgsAddress}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csgsAddTime和after$csgsAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgsAddTime==true">
	${before$csgsAddTime}
	<dl class="csgsAddTime " major  ref="csgsAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csgsAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csGasStation$csgsAddTime!=null">${csGasStation$csgsAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csGasStation.csgsAddTime" id="csgsAddTime"  value="<s:date name="csGasStation.csgsAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择加油站的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgsAddTime">${csGasStation.csgsAddTime}</textarea>
		 		<span>
		 	
			 ${csGasStation.csgsAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgsAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csgsStatus和after$csgsStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csgsStatus==true">
	${before$csgsStatus}
	<dl class="csgsStatus " major  ref="csgsStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csgsStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csGasStation$csgsStatus!=null">${csGasStation$csgsStatus}</s:if><s:else>
		 	<select class="narrow" id="csgsStatus" name="csGasStation.csgsStatus">
		 		<option value="">请选择</option>
				<option value="1" ${csGasStation.csgsStatus==1?"selected":""}>正常</option>
				<option value="0" ${csGasStation.csgsStatus==0?"selected":""}>无效</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择加油站的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csgsStatus">${csGasStation.csgsStatus}</textarea>
		 		<span>
		 	
			 ${csGasStation.csgsStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csgsStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csGasStation.csgsId}" name="csGasStation.csgsId" id="csgsId" />
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