<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lz" uri="http://www.lazy3q.com/web/lazy3q.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="get" uri="/get-tags" %>
${lz:set("注释","*****************根据当前项目路径进行权限校验*****************")}
${get:srvlimit("admin/service/maintain.do")}
<!------------------------LAZY3Q_JSP_TOP------------------------>
<!------------------------LAZY3Q_JSP_TOP------------------------>

${lz:set("window",lz:window())}
${lz:set("projectpath","admin/service/maintain.do")}
${lz:set("注释","系统级页面默认配置，当在默认配置后重新设置配置的话，将以新的配置为准，配置结构参考CTRL控制器说明")}
${lz:set("isAddType",(lz:vacant(ids))&&(empty csMaintain.csmId))}
<lz:DefaultCtrl>{
	<s:if test="#request.isAddType==true">
	${lz:set("注释","当处于添加数据时哪些字段可编辑")}
	editables:"csmId,csmHost,csmNumber,csmModel,csmType,csmExpectBy,csmExpectDate,csmDate,csmExpectKm,csmRealKm,csmCurrKm,csmFeeType,csmFee,csmRemark,csmStatus",
	${lz:set("注释","当处于添加数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csmId,csmHost,csmNumber,csmModel,csmType,csmExpectBy,csmExpectDate,csmDate,csmExpectKm,csmRealKm,csmCurrKm,csmFeeType,csmFee,csmRemark,csmStatus",
	</s:if>
	<s:else>
	${lz:set("注释","当处于编辑数据时哪些字段可编辑")}
	editables:"csmId,csmHost,csmNumber,csmModel,csmType,csmExpectBy,csmExpectDate,csmDate,csmExpectKm,csmRealKm,csmCurrKm,csmFeeType,csmFee,csmRemark,csmStatus",
	${lz:set("注释","当处于编辑数据时哪些字段可显示。为什么？因为有些字段可能是只读的")}
	visibles:"csmId,csmHost,csmNumber,csmModel,csmType,csmExpectBy,csmExpectDate,csmDate,csmExpectKm,csmRealKm,csmCurrKm,csmKmHistory,csmFeeType,csmFee,csmRemark,csmStatus",
	</s:else>
}</lz:DefaultCtrl>
${lz:set("注释","***************************************************")}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;" />  
    <title>${empty CTRL.title?"车辆保养编辑":CTRL.title}</title>
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
<s:if test="#request.CTRL.title=='保养处理'">
	
	<s:if test="csMaintain.csmStatus<3">
		<lz:set name="jsonString">
			{
				title:"保养处理",
				editable:false,
				visible:false,
				action:"maintain_dispose.do",
				fields:{
					csmId:{editable:false,visible:true}
					,csmNumber:{editable:false,visible:true}
					,csmType:{editable:false,visible:true}
					,csmDate:{editable:true,visible:true}
					,csmExpectKm:{editable:true,visible:true}
					,csmRealKm:{editable:true,visible:true}
					,csmCurrKm:{editable:true,visible:true}
					,csmFeeType:{editable:true,visible:true}
					,csmFee:{editable:true,visible:true}
					,csmRemark:{editable:true,visible:true}
					,csmStatus:{editable:false,visible:false}
				},
				submits:[
					{name:"进入保养",value="进入保养"},
					{name:"保养完成",value="保养完成"}
				]
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
		<s:if test="csMaintain.csmStatus == 2">
		<script>
		$(function(){
			$("<b>*</b>").insertBefore("dl[ref='csmDate'] dd em");
			$("<b>*</b>").insertBefore("dl[ref='csmRealKm'] dd em");
			$("<b>*</b>").insertBefore("dl[ref='csmCurrKm'] dd em");
			$("<b>*</b>").insertBefore("dl[ref='csmFee'] dd em");
		});
		</script>
		</s:if>
	</s:if>
	<s:else>
		${lz:set("message","当前保养已经完成，不能再进行保养处理")}
		<lz:set name="jsonString">
			{
				title:"保养处理",
				editable:false,
				visible:false
			}
		</lz:set>
		${lz:set("CTRL",lz:CTRL(jsonString))}
	</s:else>
	
</s:if>

<script>
$(function(){
	$("#csmNumber").change(function(){		
		$.getObject("${basePath}admin/object/car_query.do?object=true&cscId="+$(this).val(),{},function(cars){	
			var csCar = cars[0].object;			
			$.getObject("${basePath}admin/object/carmodel_query.do?object=true&cscmId="+csCar.cscModel,{},function(models){
				var csCarModel=models[0].object;
				$("#csmModel").prev().html(csCarModel.cscmName);
				$("#csmModel").val(csCarModel.cscmId);
			});			
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
		if($("#csmKmHistory").size()>0){
			 //构造编辑页csMaintain.csmKmHistory为html编辑控件
			 KE.init({
				id : 'csmKmHistory',
				width : '625px',
				height : '300px' , 
				imageUploadJson : '${ lz:config("upload.url")==null ? basePath : ""}${ lz:config("upload.url")==null ? "upload.do" :  lz:config("upload.url")}?app=maintain_edit&type=img${ lz:config("upload.url")==null ? "" : "&remote="}${ lz:config("upload.url")==null ? "" : basePath}${ lz:config("upload.url")==null ? "" : "callback.jsp"}'
			 });
			 KE.create("csmKmHistory");
		 }
		 
	${lz:set("haveEditable",false)}
	${lz:set("注释","提交时的每个字段的js验证，如果某个字段输入的值非法，返回一个字符串即可（阻止提交与提醒操作人）")}
	$("#maintainForm").form({
		"":function(){}
		<s:if test="#request.CTRL.e.csmId==true">
		${lz:set("haveEditable",true)}
		,"csMaintain.csmId":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmHost==true">
		${lz:set("haveEditable",true)}
		,"csMaintain.csmHost":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmNumber==true">
		${lz:set("haveEditable",true)}
		,"csMaintain.csmNumber":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmModel==true">
		${lz:set("haveEditable",true)}
		,"csMaintain.csmModel":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmType==true">
		${lz:set("haveEditable",true)}
		,"csMaintain.csmType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择保期";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmExpectBy==true">
		${lz:set("haveEditable",true)}
		,"csMaintain.csmExpectBy":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择保养按";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmExpectDate==true">
		${lz:set("haveEditable",true)}
		,"csMaintain.csmExpectDate":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmDate==true">
		${lz:set("haveEditable",true)}
		,"csMaintain.csmDate":function(el){
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmExpectKm==true">
		${lz:set("haveEditable",true)}
		,"csMaintain.csmExpectKm":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmRealKm==true">
		${lz:set("haveEditable",true)}
		,"csMaintain.csmRealKm":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmCurrKm==true">
		${lz:set("haveEditable",true)}
		,"csMaintain.csmCurrKm":function(el){
				if(el.value.length>10)
					return "数字太大了";
					 var pattern = /^-?(0|[1-9][0-9]*)?$/;
					 if(!pattern.test(el.value))
						return "请输入正确格式的数字";			
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmKmHistory==true">
		${lz:set("haveEditable",true)}
		,"csMaintain.csmKmHistory":function(el){
			if($("#csmKmHistory").get(0))
			 	KE.sync("csmKmHistory");		 
			if(el.value.length>65535 && el.value.indexOf("[*]")==-1)
				return "公里数历史最大长度为65535个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmFeeType==true">
		${lz:set("haveEditable",true)}
		,"csMaintain.csmFeeType":function(el){
			if(jQuery.trim(el.value)=="")
				return "请选择费别";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmFee==true">
		${lz:set("haveEditable",true)}
		,"csMaintain.csmFee":function(el){
				var pattern = /^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;
				if(el.value!="" && !pattern.test(el.value))
					return "保养费用输入格式错误";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmRemark==true">
		${lz:set("haveEditable",true)}
		,"csMaintain.csmRemark":function(el){
			if(el.value.length>512 && el.value.indexOf("[*]")==-1)
				return "备注信息最大长度为512个字符";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmAddTime==true">
		${lz:set("haveEditable",true)}
		,"csMaintain.csmAddTime":function(el){
			if(jQuery.trim(el.value)=="")
				return "添加时间不能为空";
		}
		</s:if>	
		<s:if test="#request.CTRL.e.csmStatus==true">
		${lz:set("haveEditable",true)}
		,"csMaintain.csmStatus":function(el){
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
	<form class="form editform" ref="CsMaintain" id="maintainForm" name="maintainForm" action="${empty CTRL.action?"maintain_save.do":CTRL.action}" method="post">
		<div class="head"></div>
		<div class="body">
			<div class="content">
				<s:if test="#request.haveEditable==true">
				<div class="prompt">
					温馨提示：请仔细填写车辆保养相关信息，<span class="extrude">"*" 为必填选项。</span>			
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
				<input type="hidden" value="${csMaintainToken}" name="csMaintainToken" id="csMaintainToken" />
				<textarea name="PARAMS" id="PARAMS" style="display:none">${PARAMS}</textarea>
				${lz:set("isAddType",(lz:vacant(ids))&&(empty csMaintain.csmId))}		
				${lz:set("haveEditable",false)}
				${lz:set("havePrimary",false)}
	
	${lz:set("注释","*****************编号字段的输入框代码*****************")}
	${lz:set("注释","before$csmId和after$csmId变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmId==true">
	${before$csmId}
	<dl class="csmId ${CTRL.e.csmId?"hidden":""}" major  ref="csmId" >
		<dt>编　　号:</dt>
		<s:if test="#request.CTRL.e.csmId==true">
		${lz:set("haveEditable",true)}
		${lz:set("havePrimary",true)}
		<dd input="hidden">
		<s:if test="#request.csMaintain$csmId!=null">${csMaintain$csmId}</s:if><s:else>
			<input type="hidden" value="${csMaintain.csmId}" name="csMaintain.csmId" id="csmId" />
	 	 </s:else>
	 	 
	 	 
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****编号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmId">${csMaintain.csmId}</textarea>
		 		<span>
		 	
			 ${csMaintain.csmId$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmId}
	</s:if>
	
	${lz:set("注释","*****************城市字段的输入框代码*****************")}
	${lz:set("注释","before$csmHost和after$csmHost变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmHost==true">
	${before$csmHost}
	<dl class="csmHost " major  ref="csmHost" >
		<dt>城　　市:</dt>
		<s:if test="#request.CTRL.e.csmHost==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csMaintain$csmHost!=null">${csMaintain$csmHost}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/permissions/host_query.do?size=-1" id="csmHost" name="csMaintain.csmHost">
		 				<option selected value="${csMaintain.csmHost}">
		 					${get:SrvHost(csMaintain.csmHost).shName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmHost').val())==''){return;};window.href('${basePath}${proname}/permissions/host_details.do?key='+$('#csmHost').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****城市字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmHost">${csMaintain.csmHost}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/permissions/host_details.do?key=${csMaintain.csmHost}',{ctrl:{editable:false,visible:true}})">
			 ${csMaintain.csmHost$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmHost}
	</s:if>
	
	${lz:set("注释","*****************车牌号字段的输入框代码*****************")}
	${lz:set("注释","before$csmNumber和after$csmNumber变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmNumber==true">
	${before$csmNumber}
	<dl class="csmNumber " major  ref="csmNumber" >
		<dt>车&nbsp;&nbsp;牌&nbsp;&nbsp;号:</dt>
		<s:if test="#request.CTRL.e.csmNumber==true">
		${lz:set("haveEditable",true)}
		<dd input="query">
		<s:if test="#request.csMaintain$csmNumber!=null">${csMaintain$csmNumber}</s:if><s:else>
		 			<input title="请输入车辆车牌号进行查询" class="combox narrow" action="${basePath}${proname}/object/car_query.do?value={param}&cscHost={csmHost}" type="text" id="csmNumber" name="csMaintain.csmNumber" text="${get:CsCar(csMaintain.csmNumber).cscNumber}" value="${csMaintain.csmNumber}" />
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmNumber').val())==''){return;};window.href('${basePath}${proname}/object/car_details.do?key='+$('#csmNumber').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车牌号字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmNumber">${csMaintain.csmNumber}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/car_details.do?key=${csMaintain.csmNumber}',{ctrl:{editable:false,visible:true}})">
			 ${csMaintain.csmNumber$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmNumber}
	</s:if>
	
	${lz:set("注释","*****************车型字段的输入框代码*****************")}
	${lz:set("注释","before$csmModel和after$csmModel变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmModel==true">
	${before$csmModel}
	<dl class="csmModel " major  ref="csmModel" >
		<dt>车　　型:</dt>
		<s:if test="#request.CTRL.e.csmModel==true">
		${lz:set("haveEditable",true)}
		<dd input="combox">
		<s:if test="#request.csMaintain$csmModel!=null">${csMaintain$csmModel}</s:if><s:else>
		 			<select class="combox narrow" action="${basePath}${proname}/object/carmodel_query.do?size=-1" id="csmModel" name="csMaintain.csmModel">
		 				<option selected value="${csMaintain.csmModel}">
		 					${get:CsCarModel(csMaintain.csmModel).cscmName}
		 				</option>
		 			</select>
		 			<a class="lookup" href="javascript:void(0);" onclick="if($.trim($('#csmModel').val())==''){return;};window.href('${basePath}${proname}/object/carmodel_details.do?key='+$('#csmModel').val(),{ctrl:{editable:false}})"></a>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em></em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****车型字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmModel">${csMaintain.csmModel}</textarea>
		 		<span>
		 	
			 <a href="javascript:void(0);" onclick="window.href('${basePath}${proname}/object/carmodel_details.do?key=${csMaintain.csmModel}',{ctrl:{editable:false,visible:true}})">
			 ${csMaintain.csmModel$}</a>
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmModel}
	</s:if>
	
	${lz:set("注释","*****************保期字段的输入框代码*****************")}
	${lz:set("注释","before$csmType和after$csmType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmType==true">
	${before$csmType}
	<dl class="csmType " major  ref="csmType" >
		<dt>保　　期:</dt>
		<s:if test="#request.CTRL.e.csmType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMaintain$csmType!=null">${csMaintain$csmType}</s:if><s:else>
		 	<select class="narrow" id="csmType" name="csMaintain.csmType">
		 		<option value="">请选择</option>
				<option value="1" ${csMaintain.csmType==1?"selected":""}>首保</option>
				<option value="2" ${csMaintain.csmType==2?"selected":""}>二保</option>
				<option value="3" ${csMaintain.csmType==3?"selected":""}>三保</option>
				<option value="4" ${csMaintain.csmType==4?"selected":""}>四保</option>
				<option value="5" ${csMaintain.csmType==5?"selected":""}>五保</option>
				<option value="6" ${csMaintain.csmType==6?"selected":""}>六保</option>
				<option value="7" ${csMaintain.csmType==7?"selected":""}>七保</option>
				<option value="8" ${csMaintain.csmType==8?"selected":""}>其它</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆保养的保期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****保期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmType">${csMaintain.csmType}</textarea>
		 		<span>
		 	
			 ${csMaintain.csmType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmType}
	</s:if>
	
	${lz:set("注释","*****************保养按字段的输入框代码*****************")}
	${lz:set("注释","before$csmExpectBy和after$csmExpectBy变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmExpectBy==true">
	${before$csmExpectBy}
	<dl class="csmExpectBy " major  ref="csmExpectBy" >
		<dt>保&nbsp;&nbsp;养&nbsp;&nbsp;按:</dt>
		<s:if test="#request.CTRL.e.csmExpectBy==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMaintain$csmExpectBy!=null">${csMaintain$csmExpectBy}</s:if><s:else>
		 	<select class="narrow" id="csmExpectBy" name="csMaintain.csmExpectBy">
		 		<option value="">请选择</option>
				<option value="0" ${csMaintain.csmExpectBy==0?"selected":""}>里程</option>
				<option value="1" ${csMaintain.csmExpectBy==1?"selected":""}>时间</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆保养的保养按</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****保养按字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmExpectBy">${csMaintain.csmExpectBy}</textarea>
		 		<span>
		 	
			 ${csMaintain.csmExpectBy$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmExpectBy}
	</s:if>
	
	${lz:set("注释","*****************预计保养日期字段的输入框代码*****************")}
	${lz:set("注释","before$csmExpectDate和after$csmExpectDate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmExpectDate==true">
	${before$csmExpectDate}
	<dl class="csmExpectDate " major  ref="csmExpectDate" >
		<dt>预计保养日期:</dt>
		<s:if test="#request.CTRL.e.csmExpectDate==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csMaintain$csmExpectDate!=null">${csMaintain$csmExpectDate}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csMaintain.csmExpectDate" id="csmExpectDate"  value="<s:date name="csMaintain.csmExpectDate" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆保养的预计保养日期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****预计保养日期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmExpectDate">${csMaintain.csmExpectDate}</textarea>
		 		<span>
		 	
			 ${csMaintain.csmExpectDate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmExpectDate}
	</s:if>
	
	${lz:set("注释","*****************实际保养日期字段的输入框代码*****************")}
	${lz:set("注释","before$csmDate和after$csmDate变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmDate==true">
	${before$csmDate}
	<dl class="csmDate " minor  ref="csmDate" >
		<dt>实际保养日期:</dt>
		<s:if test="#request.CTRL.e.csmDate==true">
		${lz:set("haveEditable",true)}
		<dd input="date">
		<s:if test="#request.csMaintain$csmDate!=null">${csMaintain$csmDate}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="input narrow" maxlength="19" name="csMaintain.csmDate" id="csmDate"  value="<s:date name="csMaintain.csmDate" format="yyyy-MM-dd"/>"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请选择车辆保养的实际保养日期</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实际保养日期字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmDate">${csMaintain.csmDate}</textarea>
		 		<span>
		 	
			 ${csMaintain.csmDate$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmDate}
	</s:if>
	
	${lz:set("注释","*****************预计保养公里字段的输入框代码*****************")}
	${lz:set("注释","before$csmExpectKm和after$csmExpectKm变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmExpectKm==true">
	${before$csmExpectKm}
	<dl class="csmExpectKm " major  ref="csmExpectKm" >
		<dt>预计保养公里:</dt>
		<s:if test="#request.CTRL.e.csmExpectKm==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csMaintain$csmExpectKm!=null">${csMaintain$csmExpectKm}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csMaintain.csmExpectKm" id="csmExpectKm"  value="${csMaintain.csmExpectKm}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆保养的预计保养公里</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****预计保养公里字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmExpectKm">${csMaintain.csmExpectKm}</textarea>
		 		<span>
		 	
			 ${csMaintain.csmExpectKm$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmExpectKm}
	</s:if>
	
	${lz:set("注释","*****************实际保养公里字段的输入框代码*****************")}
	${lz:set("注释","before$csmRealKm和after$csmRealKm变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmRealKm==true">
	${before$csmRealKm}
	<dl class="csmRealKm " minor  ref="csmRealKm" >
		<dt>实际保养公里:</dt>
		<s:if test="#request.CTRL.e.csmRealKm==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csMaintain$csmRealKm!=null">${csMaintain$csmRealKm}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csMaintain.csmRealKm" id="csmRealKm"  value="${csMaintain.csmRealKm}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆保养的实际保养公里</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****实际保养公里字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmRealKm">${csMaintain.csmRealKm}</textarea>
		 		<span>
		 	
			 ${csMaintain.csmRealKm$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmRealKm}
	</s:if>
	
	${lz:set("注释","*****************当前公里数字段的输入框代码*****************")}
	${lz:set("注释","before$csmCurrKm和after$csmCurrKm变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmCurrKm==true">
	${before$csmCurrKm}
	<dl class="csmCurrKm " major  ref="csmCurrKm" >
		<dt>当前公里数:</dt>
		<s:if test="#request.CTRL.e.csmCurrKm==true">
		${lz:set("haveEditable",true)}
		<dd input="number">
		<s:if test="#request.csMaintain$csmCurrKm!=null">${csMaintain$csmCurrKm}</s:if><s:else>
			<input onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csMaintain.csmCurrKm" id="csmCurrKm"  value="${csMaintain.csmCurrKm}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆保养的当前公里数</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****当前公里数字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmCurrKm">${csMaintain.csmCurrKm}</textarea>
		 		<span>
		 	
			 ${csMaintain.csmCurrKm$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmCurrKm}
	</s:if>
	
	${lz:set("注释","*****************公里数历史字段的输入框代码*****************")}
	${lz:set("注释","before$csmKmHistory和after$csmKmHistory变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmKmHistory==true">
	${before$csmKmHistory}
	<dl class="csmKmHistory " minor  ref="csmKmHistory" style="width:98%;">
		<dt>公里数历史:</dt>
		<s:if test="#request.CTRL.e.csmKmHistory==true">
		${lz:set("haveEditable",true)}
		<dd input="html">
		<s:if test="#request.csMaintain$csmKmHistory!=null">${csMaintain$csmKmHistory}</s:if><s:else>
		    <textarea class="input wide"  id="csmKmHistory" name="csMaintain.csmKmHistory" style="height:300px;visibility:hidden;">${csMaintain.csmKmHistory}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆保养的公里数历史</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****公里数历史字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csMaintain.csmKmHistory$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmKmHistory}
	</s:if>
	
	${lz:set("注释","*****************费别字段的输入框代码*****************")}
	${lz:set("注释","before$csmFeeType和after$csmFeeType变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmFeeType==true">
	${before$csmFeeType}
	<dl class="csmFeeType " major  ref="csmFeeType" >
		<dt>费　　别:</dt>
		<s:if test="#request.CTRL.e.csmFeeType==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMaintain$csmFeeType!=null">${csMaintain$csmFeeType}</s:if><s:else>
		 	<select class="narrow" id="csmFeeType" name="csMaintain.csmFeeType">
		 		<option value="">请选择</option>
				<option value="0" ${csMaintain.csmFeeType==0?"selected":""}>免费</option>
				<option value="1" ${csMaintain.csmFeeType==1?"selected":""}>自费</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆保养的费别</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****费别字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmFeeType">${csMaintain.csmFeeType}</textarea>
		 		<span>
		 	
			 ${csMaintain.csmFeeType$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmFeeType}
	</s:if>
	
	${lz:set("注释","*****************保养费用字段的输入框代码*****************")}
	${lz:set("注释","before$csmFee和after$csmFee变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmFee==true">
	${before$csmFee}
	<dl class="csmFee " major  ref="csmFee" >
		<dt>保养费用:</dt>
		<s:if test="#request.CTRL.e.csmFee==true">
		${lz:set("haveEditable",true)}
		<dd input="menoy">
		<s:if test="#request.csMaintain$csmFee!=null">${csMaintain$csmFee}</s:if><s:else>
		 	<input onchange="onkeyup()" onkeyup="var reg=/^-?(([1-9]\d{0,9})|0)?(\.)?(\d+)?/;this.value=this.value.match(reg)?this.value.match(reg)[0]:''" type="text" class="input narrow"  maxlength="8" name="csMaintain.csmFee" id="csmFee"  value="${csMaintain.csmFee}"/>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆保养的保养费用</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****保养费用字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmFee">${csMaintain.csmFee}</textarea>
		 		<span>
		 	
			 ${csMaintain.csmFee$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmFee}
	</s:if>
	
	${lz:set("注释","*****************备注信息字段的输入框代码*****************")}
	${lz:set("注释","before$csmRemark和after$csmRemark变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmRemark==true">
	${before$csmRemark}
	<dl class="csmRemark " minor  ref="csmRemark" style="width:98%;">
		<dt>备注信息:</dt>
		<s:if test="#request.CTRL.e.csmRemark==true">
		${lz:set("haveEditable",true)}
		<dd input="textarea">
		<s:if test="#request.csMaintain$csmRemark!=null">${csMaintain$csmRemark}</s:if><s:else>
		 	<textarea class="input wide"  id="csmRemark" name="csMaintain.csmRemark" rows="5">${csMaintain.csmRemark}</textarea>
	 	 </s:else>
	 	 
	 	 
	 	 <em>请输入车辆保养的备注信息</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****备注信息字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input wide">
		 		<span>
		 	
			 ${csMaintain.csmRemark$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmRemark}
	</s:if>
	
	${lz:set("注释","*****************添加时间字段的输入框代码*****************")}
	${lz:set("注释","before$csmAddTime和after$csmAddTime变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmAddTime==true">
	${before$csmAddTime}
	<dl class="csmAddTime " major  ref="csmAddTime" >
		<dt>添加时间:</dt>
		<s:if test="#request.CTRL.e.csmAddTime==true">
		${lz:set("haveEditable",true)}
		<dd input="datetime">
		<s:if test="#request.csMaintain$csmAddTime!=null">${csMaintain$csmAddTime}</s:if><s:else>
		 	<input onchange="void(0);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="input narrow" maxlength="19" name="csMaintain.csmAddTime" id="csmAddTime"  value="<s:date name="csMaintain.csmAddTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆保养的添加时间</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****添加时间字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmAddTime">${csMaintain.csmAddTime}</textarea>
		 		<span>
		 	
			 ${csMaintain.csmAddTime$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmAddTime}
	</s:if>
	
	${lz:set("注释","*****************状态字段的输入框代码*****************")}
	${lz:set("注释","before$csmStatus和after$csmStatus变量为预留变量，可以上面使用<lz:set name='变量名'>标签注入html代码")}
	<s:if test="#request.CTRL.v.csmStatus==true">
	${before$csmStatus}
	<dl class="csmStatus " major  ref="csmStatus" >
		<dt>状　　态:</dt>
		<s:if test="#request.CTRL.e.csmStatus==true">
		${lz:set("haveEditable",true)}
		<dd input="select">
		<s:if test="#request.csMaintain$csmStatus!=null">${csMaintain$csmStatus}</s:if><s:else>
		 	<select class="narrow" id="csmStatus" name="csMaintain.csmStatus">
		 		<option value="">请选择</option>
				<option value="0" ${csMaintain.csmStatus==0?"selected":""}>保养预备</option>
				<option value="1" ${csMaintain.csmStatus==1?"selected":""}>保养报警</option>
				<option value="2" ${csMaintain.csmStatus==2?"selected":""}>正在保养</option>
				<option value="3" ${csMaintain.csmStatus==3?"selected":""}>保养完成</option>
		 	</select>
	 	 </s:else>
	 	 
	 	 <b>*</b>
	 	 <em>请选择车辆保养的状态</em>
		</dd>
		</s:if>
		<s:else>
		${lz:set("注释","****状态字段非编辑模式或只读时的显示****")}
		<dd>
		 	<div class="state-input narrow">
		 		<textarea class="" style="display:none;" id="csmStatus">${csMaintain.csmStatus}</textarea>
		 		<span>
		 	
			 ${csMaintain.csmStatus$}
	 	  
	 	 		&nbsp;	
	 	 		</span>
	 	 	</div>
		</dd>
		</s:else>
	</dl>
	${after$csmStatus}
	</s:if>
				
				<div class="line"></div>
				<center class="buttons">
					${lz:set("注释","*****************before$buttons变量为预留变量，可在自定义代码中使用lz:set标签注入代码*****************")}
					${before$buttons}
					<s:if test="#request.havePrimary==false">
					<input type="hidden" value="${csMaintain.csmId}" name="csMaintain.csmId" id="csmId" />
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